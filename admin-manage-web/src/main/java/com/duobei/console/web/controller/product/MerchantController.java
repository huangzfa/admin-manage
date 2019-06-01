package com.duobei.console.web.controller.product;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.product.domain.Merchant;
import com.duobei.core.operation.product.domain.criteria.MerchantCriteria;
import com.duobei.core.operation.product.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author huangzhongfa
 * @description 商户管理
 * @date 2019/3/1
 */
@Slf4j
@Controller
@RequestMapping(value = "${authzPath}/merchant")
public class MerchantController extends BaseController {

    @Autowired
    private MerchantService merchantService;

    /**
     * 商户管理列表页
     *
     * @param model
     * @return
     */
    @RequiresPermissions("merchant:list:view")
    @RequestMapping(value = "/list")
    public String index(Model model) {
        return "merchant/list";
    }

    /**
     * ajax查询列表
     *
     * @return
     */
    @RequiresPermissions("merchant:list:view")
    @RequestMapping(value = "/getMerchantData")
    @ResponseBody
    public String getMerchantData(MerchantCriteria criteria) {
        try {
            if (criteria == null) {
                criteria = new MerchantCriteria();
            }
            if (criteria.getPagesize() == 0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<Merchant> list = merchantService.getPageList(criteria);
            return successJsonResult("success", "list", list);
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("ajax查询列表异常", e);
                return failJsonResult("查询失败");
            }
        }
    }
    /**
     * 表单添加页
     *
     * @param model
     * @return
     */
    @RequiresPermissions("merchant:list:edit")
    @RequestMapping(value = "/form")
    public String form(Model model, String merchantNo) {
        if (!StringUtil.isBlank(merchantNo)) {
            model.addAttribute("merchant", merchantService.getByMerchantNo(merchantNo));
        }
        return "merchant/form";
    }

    /**
     * 保存修改操作
     *
     * @param request
     * @param merchant
     * @return
     * @throws TqException
     */
    @RequiresPermissions("merchant:list:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request, Merchant merchant) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if (merchant.getId() == null) {
                merchant.setAddOperatorId(credential.getOpId());
                merchantService.save(merchant);
            } else {
                merchant.setModifyOperatorId(credential.getOpId());
                merchant.setModifyTime(new Date());
                merchantService.update(merchant);
            }
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("save商户异常", e);
                return failJsonResult("save商户异常");
            }

        }
    }
    /**
     * 启用禁用
     * @param merchantNo
     * @param state
     * @return
     * @throws TqException
     */
    @RequiresPermissions("merchant:list:edit")
    @RequestMapping(value = "/editState")
    @ResponseBody
    public String editState(String merchantNo,Integer state) throws TqException{
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if( StringUtil.isEmpty(merchantNo) || state == null ){
                throw new TqException("参数为空");
            }
            Merchant merchant = merchantService.getByMerchantNo(merchantNo);
            if( merchant == null){
                throw new TqException("商户不存在");
            }
            Merchant record = new Merchant();
            record.setState(state);
            record.setMerchantNo(merchantNo);
            record.setId(merchant.getId());
            record.setModifyTime(new Date());
            record.setModifyOperatorId(credential.getOpId());
            merchantService.update(record);
            return simpleSuccessJsonResult("success");

        }catch (Exception e){
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("editState产品失败", e);
                return failJsonResult("修改失败");
            }

        }
    }
}
