package com.duobei.console.web.controller.market;

import com.alibaba.fastjson.JSON;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.dic.ZD;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/7
 */
@Slf4j
@Controller
@RequestMapping(value = "${authzPath}/market/userCoupon")
public class UserCouponController extends BaseController {

    /**
     * 优惠券列表页
     * @param model
     * @return
     */
    @RequiresPermissions("market:userCoupon:view")
    @RequestMapping(value = "/list")
    public String index(Model model){
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "market/userCoupon/list";
    }
}
