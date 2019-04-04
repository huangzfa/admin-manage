package com.duobei.console.web.controller.order;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.consumdebt.domain.ConsumdebtOrder;
import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;
import com.duobei.core.transaction.repayment.domain.criteria.RepaymentCriteria;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentListVo;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentVo;
import com.duobei.core.transaction.repayment.service.RepaymentService;
import com.duobei.core.user.user.domain.vo.UserInfoVo;
import com.duobei.core.user.user.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.duobei.console.web.controller.base.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
@Controller
@RequestMapping("${authzPath}/order/repayment")
public class RepaymentController extends BaseController {
    private final static Logger log = LoggerFactory.getLogger(RepaymentController.class);

    private final static String PERMISSIONPRE = "order:repayment:";
    private final static String ADDRESSPRE = "order/repayment/";
    private final static String DESC = "还款";

    @Resource
    RepaymentService repaymentService;
    @Resource
    UserService userService;

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
        return ADDRESSPRE+"repaymentList";
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/repaymentList")
    public String getList(RepaymentCriteria repaymentCriteria) {
        OperatorCredential credential = getCredential();
        if( credential == null){
            return failJsonResult("登录过期，请重新登录");
        }
        //验证数据权限
        if( repaymentCriteria.getProductId() !=null ){
            try {
                validAuthData(repaymentCriteria.getProductId());
            }catch (Exception e){
                return failJsonResult(e.getMessage());
            }
        }else{
            return failJsonResult("产品数据查询失败");
        }
        if (repaymentCriteria.getPagesize() == 0) {
            repaymentCriteria.setPagesize(GlobalConfig.getPageSize());
        }
        try {
            Map<String,Object> dataMap = new HashMap<>();

            //查询列表
            ListVo<BorrowCashRepaymentListVo> list = repaymentService.getListByQuery(repaymentCriteria);

            dataMap.put("list",list);

            return successJsonResult(dataMap,"success");
        } catch (Exception e) {
            log.error("查询还款列表异常", e);
            return failJsonResult("系统异常");
        }
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/form")
    public String getInfo(BorrowCashRepaymentVo borrowCashRepaymentVo, Model model) throws TqException {

        try {
            //查询还款信息
            borrowCashRepaymentVo = repaymentService.getById(borrowCashRepaymentVo.getId());
            if (borrowCashRepaymentVo != null) {
                //查询还款人信息
                UserInfoVo userAndIdCardVo = userService.getUserInfoById(borrowCashRepaymentVo.getUserId());

                model.addAttribute("userInfo",userAndIdCardVo);
                model.addAttribute("repayment",borrowCashRepaymentVo);
            }
        } catch (Exception e) {
            log.error("查询还款信息异常", e);
            throw new TqException("系统异常");
            /*   return "redirect:" + this.authzPath  + ADDRESSPRE+"list";*/
        }
        return ADDRESSPRE+"repaymentForm";
    }
}
