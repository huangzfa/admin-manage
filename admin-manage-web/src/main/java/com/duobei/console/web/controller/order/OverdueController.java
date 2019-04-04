package com.duobei.console.web.controller.order;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.BigDecimalUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.criteria.BorrowCashCriteria;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashListVo;
import com.duobei.core.transaction.borrow.service.BorrowCashService;
import com.duobei.core.transaction.overdue.domain.OverdueDerateRequest;
import com.duobei.core.transaction.overdue.service.OverdueDerateRequestService;
import com.duobei.core.user.user.domain.vo.UserInfoVo;
import com.duobei.core.user.user.service.UserService;
import com.duobei.dic.ZD;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.duobei.console.web.controller.base.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/15
 */
@Controller
@RequestMapping("${authzPath}/order/overdue")
public class OverdueController extends BaseController {
    private final static Logger log = LoggerFactory.getLogger(OverdueController.class);

    private final static String PERMISSIONPRE = "order:overdue:";
    private final static String ADDRESSPRE = "order/overdue/";
    private final static String DESC = "逾期借款";


    @Resource
    BorrowCashService borrowCashService;

    @Resource
    UserService userService;

    @Resource
    OverdueDerateRequestService overdueDerateRequestService;


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
        return ADDRESSPRE+"overdueList";
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/overdueList")
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
            ListVo<BorrowCashListVo> list = borrowCashService.getOverdueListByQuery(borrowCashCriteria);

            dataMap.put("list",list);

            return successJsonResult(dataMap,"success");
        } catch (Exception e) {
            log.error("查询逾期借款列表异常", e);
            return failJsonResult("系统异常");
        }
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/form")
    public String getInfo(BorrowCash borrowCash) throws TqException {
        Map<String,Object> dataMap = new HashMap();
            //查询借款信息
            borrowCash = borrowCashService.getById(borrowCash.getId());
            if (borrowCash != null) {
                //查询借款人信息、查询产品信息
                UserInfoVo userInfoVo = userService.getUserInfoById(borrowCash.getUserId());

                dataMap.put("borrow",borrowCash);
                dataMap.put("userInfo",userInfoVo);
            }

            return successJsonResult(dataMap);
    }


    @RequiresPermissions(PERMISSIONPRE+"edit")
    @ResponseBody
    @RequestMapping(value = "/derate")
    public String derate(Long id ,BigDecimal derateAmount) throws TqException {
        OperatorCredential credential = getCredential();
        if( credential == null){
            throw new TqException("登录过期，请重新登录");
        }

        Map<String,Object> dataMap = new HashMap();
        //查询借款信息
        BorrowCash borrowCash = borrowCashService.getById(id);
        //根据用户id和借款流水号判断是否存在待审批逾期减免申请
        if (borrowCash != null || ZD.borrowState_waitRepay != borrowCash.getBorrowState()) {
            OverdueDerateRequest overdueDerateRequest = overdueDerateRequestService.getWaitApproveByUserIdAndBorrowNo(borrowCash.getUserId(),borrowCash.getBorrowNo());
            if (overdueDerateRequest != null){
                return failJsonResult("该笔借款订单存在待审核的减免记录，催收系统审核后，再进行减免");
            }else{
                //待还逾期费
                BigDecimal waitOverdue = new BigDecimal( (borrowCash.getOverdueAmount() - borrowCash.getDerateOverdue() - borrowCash.getSumOverdueAmount())/100).setScale(2);
                if (waitOverdue.compareTo(derateAmount) < 0){
                    return failJsonResult("减免逾期费不能大于待还逾期费");
                }else{
                    //逾期减免
                    try{
                        borrowCashService.overdueDerate(borrowCash,derateAmount,credential);
                    }catch (Exception e){
                        log.error("减免异常：{} 订单id：{} 减免金额：{}",e.getMessage(),id,derateAmount);
                        if (e instanceof TqException){
                            return failJsonResult(e.getMessage());
                        }else{
                            return failJsonResult("系统异常");
                        }
                    }

                }
            }
        }else{
            //订单不存在
            return failJsonResult("该笔订单不存在或已还款，不能进行减免");
        }
        return simpleSuccessJsonResult("减免成功");
    }

}
