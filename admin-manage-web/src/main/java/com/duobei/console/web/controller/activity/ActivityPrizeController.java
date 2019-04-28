package com.duobei.console.web.controller.activity;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.activity.domain.ActivityPrize;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;
import com.duobei.core.operation.activity.service.ActivityPrizeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/26
 */
@Controller
@RequestMapping(value = "${authzPath}/activity/prize")
@Slf4j
public class ActivityPrizeController extends BaseController {

    @Autowired
    private ActivityPrizeService activityPrizeService;


    /**
     * 奖品列表
     * @param model
     * @return
     */
    @RequiresPermissions("activity:list:view")
    @RequestMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "activity/prize/list";
    }

    /**
     * ajax查询
     *
     * @return
     */
    @RequiresPermissions("activity:list:view")
    @RequestMapping(value = "/getPrizeData")
    @ResponseBody
    public String getProductData(ActivityCriteria criteria) {
        try {
            if (criteria == null) {
                criteria = new ActivityCriteria();
            }
            if (criteria.getPagesize() == 0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<ActivityPrize> list = activityPrizeService.getPageList(criteria);
            return successJsonResult("success", "list", list);
        } catch (Exception e) {
            log.warn("ajax查询奖品列表异常", e);
            return failJsonResult("查询失败");
        }
    }

    @RequiresPermissions("activity:list:edit")
    @RequestMapping(value = "/form")
    public String form(Model model, Integer prizeId) {
        if( prizeId!=null){
            model.addAttribute("prize",activityPrizeService.getByPrizeId(prizeId));
        }
        return "activity/prize/form";
    }

    @RequiresPermissions("activity:list:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(ActivityPrize prize) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if(prize.getPrizeId() == null ){
                prize.setAddOperatorId(credential.getOpId());
                activityPrizeService.save(prize);
            }else{
                prize.setModifyOperatorId(credential.getOpId());
                prize.setModifyTime(new Date());
                activityPrizeService.update(prize);
            }
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("save异常", e);
                return failJsonResult("save异常");
            }
        }
    }

}
