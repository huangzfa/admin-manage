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
import com.duobei.core.operation.activity.domain.ActivityExchangePrize;
import com.duobei.core.operation.activity.domain.ActivityPrize;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;
import com.duobei.core.operation.activity.domain.vo.ActivityExchangePrizeVo;
import com.duobei.core.operation.activity.domain.vo.ActivityHongbaoPrizeVo;
import com.duobei.core.operation.activity.domain.vo.ActivityPrizeRelVo;
import com.duobei.core.operation.activity.service.*;
import com.duobei.dic.ZD;
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
    @Autowired
    private ActivityExchangePrizeService exchangePrizeService;
    @Autowired
    private ActivityPrizeRelService prizeRelService;
    @Autowired
    private ActivityHongbaoPrizeService hongbaoPrizeService;
    @Autowired
    private ActivityResourceService resourceService;
    @Autowired
    private ActivityPrizeService prizeService;


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
        HashMap<String,Object> params = new HashMap<>();
        if(StringUtil.isNotEmpty(code)){
            Activity activity = activityService.getByCode(code);
            if( activity != null ){
                model.addAttribute("activity",JSON.toJSONString(activity));
                //当前环境
                String envioment = Global.getValAsString("environment");
                //静态模板
                if( activity.getAtCode().equals(ActivityTypeEnum.STATIC.getEnv())){
                    model.addAttribute("static",staticService.getById(activity.getActId()));
                }
                //兑换模板
                else if(activity.getAtCode().equals(ActivityTypeEnum.EXCHANGE.getEnv())){
                    ActivityExchange exchangeDo =  exchangeService.getById(activity.getActId());
                    model.addAttribute("exchange",exchangeDo);
                    model.addAttribute("timeAxis",exchangeDo.getTimeAxis().split(","));
                    params.put("actId",activity.getActId());
                    //活动奖品关联数据
                    List<ActivityExchangePrizeVo> przieRelList = exchangePrizeService.getByActId(params);
                    model.addAttribute("przieRelList",JSON.toJSONString(przieRelList));

                }else{//普通模板（红包和转盘）
                    params.put("actId",activity.getActId());
                    //活动奖品关联数据
                    List<ActivityPrizeRelVo> przieRelList = prizeRelService.getByActId(params);
                    model.addAttribute("przieRelList",JSON.toJSONString(przieRelList));
                    //红包活动有额外奖励
                    if( activity.getAtCode().equals(ActivityTypeEnum.HONGBAO.getEnv())){
                        //额外奖品列表
                        List<ActivityHongbaoPrizeVo> prizeHongbaoList = hongbaoPrizeService.getByActId(params);
                        model.addAttribute("prizeHongbaoList", JSON.toJSONString(prizeHongbaoList));
                    }
                }
                model.addAttribute("activity", activity);
                params.put("envirType",envioment);
                params.put("type",activity.getAtCode());
                //查询不同环境下，不同活动的h5访问地址
                model.addAttribute("links",resourceService.getListByEnivr(params));
            }
        }
        //查询优惠券列表
        List<ActivityPrize> prize_list1 = prizeService.getByActId(params);//其他品类和不中奖奖品
        List<ActivityPrize> prize_list2 = prizeService.getCouponByActId(params);//优惠券和借款券
        model.addAttribute("prize_list1",prize_list1);
        model.addAttribute("prize_list2",prize_list2);
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
