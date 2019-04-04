package com.duobei.console.web.controller.order;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.service.ProductService;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.criteria.BorrowCashCriteria;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashListVo;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashVo;
import com.duobei.core.transaction.borrow.service.BorrowCashService;
import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrder;
import com.duobei.core.transaction.consumdebt.service.ConsumdebtOrderService;
import com.duobei.core.user.user.domain.vo.UserAndIdCardVo;
import com.duobei.core.user.user.domain.vo.UserInfoVo;
import com.duobei.core.user.user.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bouncycastle.math.raw.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.duobei.console.web.controller.base.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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
    UserService userService;
    @Resource
    ConsumdebtOrderService consumdebtOrderService;

    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/list")
    public String list(Integer productId, Model model) {
        List<Product> productList = getCredential().getProductList();
        if (productId == null && productList != null && productList.size() > 0){
            //如果未传productId 则赋予初始值
            productId = productList.get(0).getId();
        }
        model.addAttribute("productLists", JSON.toJSONString(productList));
        model.addAttribute("productId",productId);
        return ADDRESSPRE+"borrowCashList";
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/borrowCashList")
    public String getList(BorrowCashCriteria borrowCashCriteria) {
        OperatorCredential credential = getCredential();
        if( credential == null){
            return failJsonResult("登录过期，请重新登录");
        }
        //验证数据权限
        if( borrowCashCriteria.getProductId() !=null ){
            try {
                validAuthData(borrowCashCriteria.getProductId());
            }catch (Exception e){
                return failJsonResult(e.getMessage());
            }

        }else{
            return failJsonResult("产品数据查询失败");

        }
        if (borrowCashCriteria.getPagesize() == 0) {
            borrowCashCriteria.setPagesize(GlobalConfig.getPageSize());
        }
        try {
            Map<String,Object> dataMap = new HashMap<>();

            //查询列表
            ListVo<BorrowCashListVo> list = borrowCashService.getListByQuery(borrowCashCriteria);

            dataMap.put("list",list);

            return successJsonResult(dataMap,"success");
        } catch (Exception e) {
            log.warn("查询借款列表异常", e);
            return failJsonResult("系统异常");
        }
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/form")
    public String getInfo(BorrowCash borrowCash,Model model) throws TqException {

        try {
            //查询借款信息
            borrowCash = borrowCashService.getById(borrowCash.getId());
            if (borrowCash != null) {
                //查询借款人信息
                UserInfoVo userInfoVo = userService.getUserInfoById(borrowCash.getUserId());
                //查询订单信息
                ConsumdebtOrder consumdebtOrder = consumdebtOrderService.getByUserIdAndBorrowId(userInfoVo.getId(),borrowCash.getId());
                model.addAttribute("userInfo",userInfoVo);
                model.addAttribute("consumdebtOrder",consumdebtOrder);
                model.addAttribute("borrowCash",borrowCash);
            }
        } catch (Exception e) {
            log.error("查询借款信息异常", e);
            throw new TqException("系统异常");
         /*   return "redirect:" + this.authzPath  + ADDRESSPRE+"list";*/
        }
        return ADDRESSPRE+"borrowCashForm";
    }

}
