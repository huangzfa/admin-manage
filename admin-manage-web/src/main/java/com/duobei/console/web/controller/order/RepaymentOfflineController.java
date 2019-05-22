package com.duobei.console.web.controller.order;

import com.alibaba.fastjson.JSON;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.Constants;
import com.duobei.common.util.ImportExcelUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.zfb.domain.ZfbAccount;
import com.duobei.core.operation.zfb.domain.criteria.ZfbAccountCriteria;
import com.duobei.core.operation.zfb.service.ZfbAccountService;
import com.duobei.core.transaction.borrow.service.BorrowCashService;
import com.duobei.core.transaction.repayment.domain.RepaymentOffline;
import com.duobei.core.transaction.repayment.domain.bo.RepaymentOfflineBo;
import com.duobei.core.transaction.repayment.domain.criteria.RepaymentOfflineCriteria;
import com.duobei.core.transaction.repayment.domain.vo.RepaymentOfflineVo;
import com.duobei.core.transaction.repayment.service.RepaymentOfflineService;
import com.duobei.core.user.user.domain.vo.UserInfoVo;
import com.duobei.core.user.user.service.UserService;
import com.pgy.data.handler.PgyDataHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;


/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/20
 */
@Controller
@RequestMapping("${authzPath}/order/repayment/offline")
@Slf4j
public class RepaymentOfflineController extends BaseController{

    @Autowired
    private RepaymentOfflineService offlineService;
    @Autowired
    private BorrowCashService cashService;
    @Autowired
    private ZfbAccountService zfbAccountService;
    @Autowired
    private UserService userService;


    @RequiresPermissions("repayment:offline:view")
    @RequestMapping(value = "/list")
    public String list(Integer productId, Model model) {
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "order/repayment/offline/list";
    }

    /**
     * a
     * @return
     */
    @RequiresPermissions("repayment:offline:view")
    @RequestMapping(value = "/getData")
    @ResponseBody
    public String getMessageData(RepaymentOfflineCriteria criteria ){
        try {
            OperatorCredential credential = getCredential();
            if( criteria.getUserId() == null ){
                return failJsonResult("请输入用户id");
            }
            //验证数据权限
            validAuthData(criteria.getProductId());
            if (criteria.getPagesize()== BizConstant.INT_ZERO) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<RepaymentOfflineVo> list = offlineService.getPage(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("查询列表异常",e);
                return failJsonResult("查询失败");
            }
        }
    }

    /**
     * 跳转编辑页
     * @param model
     * @return
     * @throws TqException
     */
    @RequiresPermissions("repayment:offline:edit")
    @RequestMapping(value = "/form")
    public String form( Model model,Long id)  {
        if( id!=null ){
            RepaymentOffline offline = offlineService.getById(id);
            if( offline!=null ){
                offline.setRepayAmount(offline.getRepayAmount()==null?null:offline.getRepayAmount()/100);
                offline.setDerateAmount(offline.getDerateAmount()==null?null:offline.getDerateAmount()/100);
                offline.setAmount(offline.getAmount()==null?null:offline.getAmount()/100);
                model.addAttribute("borrow",cashService.getById(offline.getBorrowCashId()));
                UserInfoVo user = userService.getUserInfoById(offline.getUserId());
                if( user!=null){
                    offline.setSubmitterName(PgyDataHandler.decrypt(user.getRealNameEncrypt()));
                    model.addAttribute("phone",PgyDataHandler.decrypt(user.getUserNameEncrypt()));
                }
                ZfbAccountCriteria criteria = new ZfbAccountCriteria();
                criteria.setPagesize(100);
                criteria.setProductId(offline.getProductId());
                model.addAttribute("zfbList",zfbAccountService.queryZfbAccountList(criteria).getRows());

            }
            model.addAttribute("offline",offline);
        }

        return "order/repayment/offline/form";
    }

    /**
     * 保存或修改
     * @param entity
     * @return
     * @throws TqException
     */
    @RequiresPermissions({ "repayment:offline:edit" })
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(RepaymentOfflineBo entity) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            entity.setOperatorId(credential.getOpId()+"");
            entity.setOperatorName(credential.getRealName());
            ZfbAccount account = zfbAccountService.queryZfbAccountById(Integer.parseInt(entity.getZfbAccountId()));
            if( account == null || account.getIsEnable().equals(BizConstant.INT_ZERO)){
                throw new TqException("支付宝账号不存在或已被禁用");
            }
            entity.setZfbAccount(account.getAccount());
            offlineService.flatAccount(entity);
            return simpleSuccessJsonResult("success");

        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("平账失败", e);
                return failJsonResult("平账失败"+e.getMessage());
            }
        }
    }


    /**
     * 批量平账
     * @param file
     * @return
     * @throws Exception
     */
    @RequiresPermissions("repayment:offline:edit")
    @RequestMapping("/uploadOfflineExcel")
    @ResponseBody
    public String uploadOfflineExcel(@RequestParam(value="importexcel", required=false) MultipartFile file) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if( file == null || file.getSize()== BizConstant.INT_ZERO){
                throw new TqException("请上传文件");
            }
            String orgname = file.getOriginalFilename();
            if( !orgname.contains(".xlsx") && !orgname.contains(".xls")){
                throw new TqException("请选择正确模板文件");
            }
            if( file.getSize()>1048576){
                throw new TqException("文件太大,请分批导入");
            }
            String curProjectPath = "/home/admin/file/offline";
            String fileName = curProjectPath + System.currentTimeMillis()+orgname.substring(orgname.lastIndexOf("."),orgname.length());
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fileName));
            InputStream in = file.getInputStream();
            List<RepaymentOfflineBo> list = ImportExcelUtil.covertExcelToClass(in, orgname,
                    Constants.BILLING_DETAIL_IMPORT, RepaymentOfflineBo.class, 3, 1);
            String afbAccount = ImportExcelUtil.getAlipayNo(file.getInputStream(),orgname);
            ZfbAccount account = zfbAccountService.getByAccount(afbAccount);
            if( account == null ){
                throw new TqException("支付宝账户不存在");
            }
            HashMap<String,Object> map = offlineService.batchFlatAccount(list,account,credential);
            return successJsonResult("success","map",map);
        }catch (Exception e){
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("平账失败", e);
                return failJsonResult("平账失败"+e.getMessage());
            }
        }
    }
}
