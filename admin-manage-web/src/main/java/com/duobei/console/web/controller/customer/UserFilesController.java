package com.duobei.console.web.controller.customer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.sys.domain.Dict;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashListVo;
import com.duobei.core.transaction.borrow.service.BorrowCashService;
import com.duobei.core.user.user.domain.UserAddress;
import com.duobei.core.user.user.domain.UserBankcard;
import com.duobei.core.user.user.domain.UserLoginLog;
import com.duobei.core.user.user.domain.criteria.UserCriteria;
import com.duobei.core.user.user.domain.vo.*;
import com.duobei.core.user.user.service.*;
import com.duobei.dic.ZD;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.duobei.console.web.controller.base.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/15
 */
@Controller
@RequestMapping(value ="${authzPath}/customer/userFiles")
public class UserFilesController extends BaseController {
    private final static Logger log = LoggerFactory.getLogger(UserFilesController.class);
    private final static String PERMISSIONPRE = "customer:userFiles:";
    private final static String ADDRESSPRE = "customer/userFiles/";
    private final static String DESC = "用户档案";
    @Resource
    UserService userService;

    @Resource
    UserBankcardService userBankcardService;

    @Resource
    UserAuthService userAuthService;

    @Resource
    UserCouponService userCouponService;

    @Resource
    UserLoginLogService userLoginLogService;

    @Resource
    BorrowCashService borrowCashService;

    @Resource
    UserAddressService userAddressService;

    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/list")
    public String list() {
        return ADDRESSPRE+"userFilesList";
    }


    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/userList")
    public String userList(UserCriteria userCriteria) {
        if (userCriteria.getPagesize() == 0) {
            userCriteria.setPagesize(GlobalConfig.getPageSize());
        }
        if (StringUtil.isEmpty(userCriteria.getUserName()) && StringUtil.isEmpty(userCriteria.getRealName()) && StringUtil.isEmpty(userCriteria.getGlobalUserId()) && userCriteria.getUserId() == null)
        {
            return failJsonResult("请输入查询条件后进行查询");
        }
        try {
            List<Product> productList = getCredential().getProductList();
            if (productList != null){
                userCriteria.setProductList(productList);
            }else{
                return failJsonResult("没有产品查询权限");
            }

            //查询列表
            ListVo<UserListVo> list = userService.getListByQuery(userCriteria);
            return successJsonResult("success", "list", list);
        } catch (Exception e) {
            log.warn("查询"+DESC+"列表异常", e);
            return failJsonResult("系统异常");
        }
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/form")
    public String form(UserInfoVo userVo, Model model) {
        // OperatorCredential credential=getCredential();
        if (userVo.getId() == null){
            return failJsonResult("用户未存在");
        }
        try {
            /**
             * 查询用户信息
             */
            userVo = userService.getUserInfoById(userVo.getId());
            if (userVo == null){
                return failJsonResult("查询用户失败");
            }
            /**
             * 查询收货地址
             */
            List<UserAddress> addressList = userAddressService.getByUserId(userVo.getId());
            /**
             * 查询银行卡信息
             */
            List<UserBankcard> bankList = userBankcardService.getByUserId(userVo.getId());

            /**
             * 借款查询条件
             */
            List<Dict> stageBorrowQueryList = DictUtil.getDictList(ZD.borrowState);

            //查询借款信息 默认显示(申请/未审核，已结清，打款中，已经打款/待还款)状态借款信息
            List<Integer> borrowState = new ArrayList<>();
            borrowState.add(ZD.borrowState_ing);
            borrowState.add(ZD.borrowState_waitRepay);
            borrowState.add(ZD.borrowState_new);
            List<BorrowCashListVo> stageBorrowVoList = borrowCashService.getStageBorrowByUserIdAndState(userVo.getId(),borrowState);
            /**
             * 用户认证信息
             */
             List<UserAuthListVo> userAuthList = userAuthService.getAuthListVoByUserId(userVo.getId());

            /**
             * 用户优惠券(默认已使用)
             */
            List <UserCouponVo> userCouponList = userCouponService.getByUserIdAndState(userVo.getId(),ZD.couponState_used);
             /**
             * 优惠券查询条件
             */
            List<Dict> userCouponStateList = DictUtil.getDictList(ZD.couponState);

            model.addAttribute("user", userVo);
            model.addAttribute("stageBorrowQuery", stageBorrowQueryList);
            model.addAttribute("bankList", bankList);
            model.addAttribute("userAuthList", userAuthList);
            model.addAttribute("userCouponStateList", userCouponStateList);
            model.addAttribute("userCouponList", userCouponList);
            model.addAttribute("addressList",addressList);
            model.addAttribute("stageBorrowVoList",stageBorrowVoList);
        } catch (Exception e) {
            log.warn("查询"+DESC+"信息异常", e);
            return failJsonResult("查询"+userVo.getId()+"用户失败");
        }

        return ADDRESSPRE+"userFilesForm";
    }

    @RequiresPermissions(PERMISSIONPRE+"loginLog:view")
    @ResponseBody
    @RequestMapping(value = "/loginList")
    public String getLoginList( Long id) {

        List<UserLoginLastLogVo> list = userLoginLogService.getByUserId(id);
        return JSON.toJSONString(list);
    }


    @RequiresPermissions(PERMISSIONPRE+"setMainBank:edit")
    @ResponseBody
    @RequestMapping(value = "/setMainBank")
    public String setMainBank(HttpServletRequest request , Long id,Long userId) {
        if (id == null || userId == null){
            return  null;
        }
        try {
            /**
             * 修改银行卡信息
             */
            userBankcardService.setMainBank(id,userId);
        }catch (Exception e){
            log.warn("修改用户"+userId+"银行卡信息失败", e);
        } finally {
            /**
             * 获取银行卡信息
             */
            List<UserBankcard> list = userBankcardService.getByUserId(userId);
            return JSONObject.toJSONString(list);
        }
    }


    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/stageBorrowListQuery")
    public String stageBorrowListQuery(HttpServletRequest request ,Long userId,Integer state) {
        List<Integer> borrowState = new ArrayList<>();
        if (state == 1){
            borrowState.add(ZD.borrowState_new);
            borrowState.add(ZD.borrowState_ing);
            borrowState.add(ZD.borrowState_waitRepay);
        }else if(state == 2){
            borrowState.add(ZD.borrowState_finish);
            borrowState.add(ZD.borrowState_close);
            borrowState.add(ZD.borrowState_fail);
        }
        List<BorrowCashListVo> list = borrowCashService.getStageBorrowByUserIdAndState(userId,borrowState);
        return JSONObject.toJSONString(list);
    }


    @RequiresPermissions(PERMISSIONPRE+"auth:edit")
    @ResponseBody
    @RequestMapping(value = "/resetAuthState")
    public String resetAuthState(HttpServletRequest request , String authCode,Long userId) {
        if (StringUtils.isBlank(authCode) || userId == null){
            return null;
        }
        try {
            /**
             * 重置用户认证状态
             */
            userAuthService.resetUserAuthByUserId(authCode,userId);
        }catch (Exception e){
            log.warn("重置用户"+userId+"认证状态失败", e);
        } finally {
            /**
             * 用户认证信息
             */
            List<UserAuthListVo> list = userAuthService.getAuthListVoByUserId(userId);
            return JSONObject.toJSONString(list);
        }
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/userCouponListQuery")
    public String userCouponListQuery(HttpServletRequest request ,Long userId,Integer state) {
        List <UserCouponVo> list = userCouponService.getByUserIdAndState(userId,state);
        return JSONObject.toJSONString(list);
    }

}
