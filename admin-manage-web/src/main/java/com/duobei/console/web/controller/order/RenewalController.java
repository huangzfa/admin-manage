package com.duobei.console.web.controller.order;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.transaction.renewal.domain.criteria.BorrowCashRenewalCriteria;
import com.duobei.core.transaction.renewal.domain.vo.BorrowCashRenewalListVo;
import com.duobei.core.transaction.renewal.domain.vo.BorrowCashRenewalVo;
import com.duobei.core.transaction.renewal.service.RenewalService;
import com.duobei.core.user.user.domain.vo.UserInfoVo;
import com.duobei.core.user.user.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/14
 */
@Controller
@RequestMapping("${authzPath}/order/renewal")
public class RenewalController extends BaseController{
    private final static Logger log = LoggerFactory.getLogger(RenewalController.class);

    private final static String PERMISSIONPRE = "order:renewal:";
    private final static String ADDRESSPRE = "order/renewal/";
    private final static String DESC = "续借";

    @Resource
    RenewalService renewalService;
    @Resource
    UserService userService;

    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/list")
    public String list(Integer productId, Model model) {
        OperatorCredential credential = getCredential();
        model.addAttribute("productLists", JSON.toJSONString(credential.getProductList()));
        model.addAttribute("productId",productId);
        return ADDRESSPRE+"renewalList";
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/renewalList")
    public String getList(BorrowCashRenewalCriteria renewalCriteria) throws TqException {
        OperatorCredential credential = getCredential();
        if( credential == null){
            throw new TqException("登录过期，请重新登录");
        }
        //验证数据权限
        if( renewalCriteria.getProductId() !=null ){
            validAuthData(renewalCriteria.getProductId());
        }else{
            throw  new TqException("产品数据查询失败");

        }
        if (renewalCriteria.getPagesize() == 0) {
            renewalCriteria.setPagesize(GlobalConfig.getPageSize());
        }
        try {
            Map<String,Object> dataMap = new HashMap<>();

            //查询列表
            ListVo<BorrowCashRenewalListVo> list = renewalService.getListByQuery(renewalCriteria);

            dataMap.put("list",list);
            return successJsonResult(dataMap,"success");
        } catch (Exception e) {
            log.warn("查询续借列表异常", e);
            return failJsonResult("系统异常");
        }
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/form")
    public String getInfo(BorrowCashRenewalVo borrowCashRenewalVo, Model model) throws TqException {

        try {
            //查询续借信息
            borrowCashRenewalVo = renewalService.getById(borrowCashRenewalVo.getId());
            if (borrowCashRenewalVo != null) {
                //查询续借人信息
                UserInfoVo userAndIdCardVo = userService.getUserInfoById(borrowCashRenewalVo.getUserId());

                model.addAttribute("userInfo",userAndIdCardVo);
                model.addAttribute("renewal",borrowCashRenewalVo);
            }
        } catch (Exception e) {
            log.error("查询续借信息异常", e);
            throw new TqException("系统异常");
        }
        return ADDRESSPRE+"renewalForm";
    }
}
