package com.duobei.console.web.controller.order;

import com.alibaba.fastjson.JSON;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.service.ProductService;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.criteria.BorrowCashCriteria;
import com.duobei.core.transaction.borrow.service.BorrowCashService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bouncycastle.math.raw.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.duobei.console.web.controller.base.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/12
 */
@Controller
@RequestMapping("${authzPath}/order/borrow")
public class BorrowCashController extends BaseController {
    private final static Logger log = LoggerFactory.getLogger(BorrowCashController.class);

    private final static String PERMISSIONPRE = "order:borrow:";
    private final static String ADDRESSPRE = "order/borrow/";
    private final static String DESC = "借款";

    @Resource
    BorrowCashService borrowCashService;
    @Resource
    ProductService productService;


    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/list")
    public String list(Integer productId, Model model) {
        OperatorCredential credential = getCredential();
        model.addAttribute("productLists", JSON.toJSONString(credential.getProductList()));
        model.addAttribute("productId",productId);
        return ADDRESSPRE+"borrowCashList";
    }
    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/borrowCashList")
    public String getList(BorrowCashCriteria borrowCashCriteria) {
        if (borrowCashCriteria.getPagesize() == 0) {
            borrowCashCriteria.setPagesize(GlobalConfig.getPageSize());
        }
        try {
            Map<String,Object> dataMap = new HashMap<>();

            //查询产品信息
            Product product = productService.getById(borrowCashCriteria.getProductId());

            //查询列表
            ListVo<BorrowCash> list = borrowCashService.getListByQuery(borrowCashCriteria);

            dataMap.put("product",product);
            dataMap.put("list",list);

            return successJsonResult(dataMap,"success");
        } catch (Exception e) {
            log.warn("查询借款列表异常", e);
            return failJsonResult("系统异常");
        }
    }


}
