package com.duobei.console.web.controller.activity;

import com.alibaba.fastjson.JSON;
import com.duobei.common.config.Global;
import com.duobei.common.enums.ActivityTypeEnum;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.activity.domain.Activity;
import com.duobei.core.operation.activity.domain.ActivityExchange;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;
import com.duobei.core.operation.activity.service.ActivityExchangeService;
import com.duobei.core.operation.activity.service.ActivityService;
import com.duobei.core.operation.activity.service.ActivityStaticService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/11
 */
@Controller
@RequestMapping(value = "${authzPath}/activity")
@Slf4j
public class ActivityController extends BaseController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityStaticService staticService;
    @Autowired
    private ActivityExchangeService exchangeService;


    /**
     * 活动列表
     * @param model
     * @return
     */
    @RequiresPermissions("activity:list:view")
    @RequestMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "activity/list";
    }

    /**
     * ajax查询
     *
     * @return
     */
    @RequiresPermissions("activity:list:view")
    @RequestMapping(value = "/getActivityData")
    @ResponseBody
    public String getProductData(ActivityCriteria criteria) {
        try {
            if (criteria == null) {
                criteria = new ActivityCriteria();
            }
            if (criteria.getPagesize() == 0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<Activity> list = activityService.getLists(criteria);
            return successJsonResult("success", "list", list);
        } catch (Exception e) {
                log.warn("ajax查询活动列表异常", e);
                return failJsonResult("查询失败");
        }
    }

    /**
     * 活动表单添加页
     * @param model
     * @return
     */
    @RequiresPermissions("activity:list:edit")
    @RequestMapping(value = "/form")
    public void form(Model model, String code) {
        if(StringUtil.isNotEmpty(code)){
            Activity activity = activityService.getByCode(code);
            if( activity == null ){
                model.addAttribute("activity",JSON.toJSONString(activity));
            }
            HashMap<String,Object> params = new HashMap<>();
            String envioment = Global.getValAsString("environment");
            //静态模板
            /*if( activity.getAtCode().equals(ActivityTypeEnum.STATIC.getEnv())){
                model.addAttribute("static",staticService.getById(activity.getActId()));
            }else if(activity.getAtCode().equals(ActivityTypeEnum.EXCHANGE.getEnv())){
                ActivityExchange exchangeDo =  exchangeService.getById(activity.getActId());
                model.addAttribute("exchange",exchangeDo);
                model.addAttribute("timeAxis",exchangeDo.getTimeAxis().split(","));
                params.put("actId",activity.getActId());
                //活动奖品关联数据
                List<LsdActivityExchangePrizeDto> przie_rel_list = exchangePrizeService.getByActId(params);
                model.put("prize_exchange_list",przie_rel_list);

            }else{
                params.put("actId",actId);
                //活动奖品关联数据
                List<LsdActivityPrizeRelDto> prize_exchange_list = prizeRelService.getByActId(params);
                model.put("przie_rel_list",prize_exchange_list);

                if( activityDo.getAtCode().equals(ActivityTypeEnum.HONGBAO.getEnv())){
                    //额外奖品列表
                    List<LsdActivityOtherHongbaoDto> prize_hongbao_list = otherHongbaoService.getByActId(params);
                    model.put("prize_hongbao_list", prize_hongbao_list);
                    //活动红包表
                    model.put("hongbao",hongbaoService.getById((long)actId));
                    activityType = ResourceType.ACTIVITY_HONGBAO_LINK.getCode();
                    //活动转盘表
                }else if(activityDo.getAtCode().equals(ActivityTypeEnum.ZHUANPAN.getEnv())){
                    model.put("dazhuanpan",dazhuanpanService.getById((long)actId));
                    activityType = ResourceType.ACTIVITY_DAZHUANPAN_LINK.getCode();
                }
            }*/
        }
    }

    @RequiresPermissions("activity:list:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(Activity activity) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
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
