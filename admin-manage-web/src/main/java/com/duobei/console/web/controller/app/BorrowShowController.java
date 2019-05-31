package com.duobei.console.web.controller.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.BeanUtil;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.service.AppService;
import com.duobei.core.operation.consume.domain.ConsumeLoanConfig;
import com.duobei.core.operation.consume.domain.vo.BorrowShowConfigVo;
import com.duobei.core.operation.consume.service.ConsumeLoanConfigService;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.startupPage.domain.StartupPage;
import com.duobei.core.operation.startupPage.service.StartupPageService;
import com.duobei.dic.ZD;
import com.google.gson.JsonArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author litianxiong
 * @description 启动页配置控制器
 * @date 2019/3/2
 */
@Controller
@RequestMapping(value = "${authzPath}/app/borrowShow")
public class BorrowShowController extends BaseController {
    Logger log = LoggerFactory.getLogger(BorrowShowController.class);
    private final static String PERMISSIONPRE = "app:borrowShow:";
    private final static String ADDRESSPRE = "app/borrowShow/";
    private final static String DESC = "借钱页默认配置";
    @Resource
    ConsumeLoanConfigService consumeLoanConfigService;

    /**
     * 启动页配置获取
     * @param model
     *
     * @throws TqException
     */
    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/form")
    public String borrowShowForm( Model model,Integer productId)  {
        OperatorCredential credential = getCredential();
        //获取用户产品权限
        List<Product> productList = credential.getProductList();
        if (productList == null || productList.size() <= 0){
            return failJsonResult("无产品权限");
        }

        if (productId == null){
            productId = productList.get(0).getId();
        }
        //查询应用
        ConsumeLoanConfig consumeLoanConfig = consumeLoanConfigService.getByProductId(productId);
        BorrowShowConfigVo borrowShowConfigVo = new BorrowShowConfigVo();
        if (consumeLoanConfig == null){
            model.addAttribute("borrowShow",borrowShowConfigVo);
        }else{
            BeanUtils.copyProperties(consumeLoanConfig,borrowShowConfigVo);
            List<Integer> dayList = JSON.parseArray(consumeLoanConfig.getShowBorrowDays(),Integer.class);
           if (dayList != null && dayList.size() > BizConstant.INT_ZERO){
               borrowShowConfigVo.setDay1(dayList.get(0));
               borrowShowConfigVo.setDay2(dayList.size()>1?dayList.get(1):null);
               borrowShowConfigVo.setDay3(dayList.size()>2?dayList.get(2):null);
           }else{
               borrowShowConfigVo.setDay1(null);
               borrowShowConfigVo.setDay2(null);
               borrowShowConfigVo.setDay3(null);
           }

            model.addAttribute("borrowShow",borrowShowConfigVo);
        }
        model.addAttribute("productList",productList);
        model.addAttribute("productId",productId);

        return "app/borrowShow/borrowShowForm";
    }
    @RequiresPermissions(PERMISSIONPRE + "edit")
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update (BorrowShowConfigVo borrowShowConfigVo){

        //获取用户产品权限
        try {
             OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            //验证数据权限
            if( borrowShowConfigVo.getProductId() != null ){
                validAuthData(borrowShowConfigVo.getProductId());
            }else{
                throw new TqException("数据操作权限不足");
            }

            ConsumeLoanConfig consumeLoanConfig = new ConsumeLoanConfig();
            //id 产品Id
            consumeLoanConfig.setProductId(borrowShowConfigVo.getProductId());
            consumeLoanConfig.setId(borrowShowConfigVo.getId());
            //修改时间、修改人
            consumeLoanConfig.setModifyOperatorId(credential.getOpId());
            consumeLoanConfig.setModifyTime(new Date());

            //显示最小、最大可借金额 元--->分
            consumeLoanConfig.setShowMinAmount(borrowShowConfigVo.getSaveMinAmount().multiply(new BigDecimal(100)).longValue());
            consumeLoanConfig.setShowMaxAmount(borrowShowConfigVo.getSaveMaxAmount().multiply(new BigDecimal(100)).longValue());
            //天数转成 JSON数组
            List dayList = new ArrayList();
            dayList.add(borrowShowConfigVo.getDay1());
            dayList.add(borrowShowConfigVo.getDay2());
            dayList.add(borrowShowConfigVo.getDay3());
            consumeLoanConfig.setShowBorrowDays(JSON.toJSONString(dayList));
            if (consumeLoanConfig.getId() == null){
                //新增
                //添加人、添加时间
                consumeLoanConfig.setAddOperatorId(credential.getOpId());
                consumeLoanConfig.setAddTime(consumeLoanConfig.getModifyTime());
                //
                consumeLoanConfigService.saveBorrowShow(consumeLoanConfig);
            }else {
               /* startupPage.setId(data.getId());*/
                //修改
               //查询数据
                ConsumeLoanConfig oldData = consumeLoanConfigService.getById(consumeLoanConfig.getId());
                consumeLoanConfig.setDataVersion(oldData.getDataVersion());
                consumeLoanConfigService.updateBorrowShowById(consumeLoanConfig);
            }
            return simpleSuccessJsonResult ("success",1);
        } catch (Exception e) {
            log.error("编辑"+DESC+"异常", e);
            if (e instanceof TqException){
                return failJsonResult(e.getMessage());
            }else {
                return failJsonResult("编辑"+DESC+"异常");
            }
        }
    }

}
