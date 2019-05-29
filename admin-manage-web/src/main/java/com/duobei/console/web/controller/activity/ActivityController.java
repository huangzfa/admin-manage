package com.duobei.console.web.controller.activity;

import com.alibaba.fastjson.JSON;
import com.duobei.common.config.Global;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.enums.ActivityTypeEnum;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.activity.domain.*;
import com.duobei.core.operation.activity.domain.criteria.ActivityCriteria;
import com.duobei.core.operation.activity.domain.vo.ActivityExchangePrizeVo;
import com.duobei.core.operation.activity.domain.vo.ActivityHongbaoPrizeVo;
import com.duobei.core.operation.activity.domain.vo.ActivityPrizeRelVo;
import com.duobei.core.operation.activity.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private ActivityTypeService activityTypeService;


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
            //验证数据权限
            validAuthData(criteria.getProductId());
            
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
    public String form(Model model, String code) {
        HashMap<String,Object> params = new HashMap<>();
        if(StringUtil.isNotEmpty(code)){
            Activity activity = activityService.getByCode(code);
            if( activity != null ){
                model.addAttribute("activity",JSON.toJSONString(activity));
                //当前环境
                String envioment = Global.getValAsString("environment");
                //静态模板
                if( activity.getAtCode().equals(ActivityTypeEnum.STATIC.getEnv())){
                    model.addAttribute("statics",JSON.toJSONString(staticService.getById(activity.getActId())));
                }
                //兑换模板
                else if(activity.getAtCode().equals(ActivityTypeEnum.EXCHANGE.getEnv())){
                    ActivityExchange exchangeDo =  exchangeService.getById(activity.getActId());
                    model.addAttribute("exchanges",JSON.toJSONString(exchangeDo));
                    model.addAttribute("timeAxis",JSON.toJSONString(exchangeDo.getTimeAxis().split(",")));
                    params.put("actId",activity.getActId());
                    //活动奖品关联数据
                    List<ActivityExchangePrizeVo> przieRelList = exchangePrizeService.getByActId(params);
                    model.addAttribute("prizeExchangeLists",JSON.toJSONString(przieRelList));

                }else{//普通模板（红包和转盘）
                    params.put("actId",activity.getActId());
                    //活动奖品关联数据
                    List<ActivityPrizeRelVo> przieRelList = prizeRelService.getByActId(params);
                    model.addAttribute("prizeRelLists",JSON.toJSONString(przieRelList));
                    //红包活动有额外奖励
                    if( activity.getAtCode().equals(ActivityTypeEnum.HONGBAO.getEnv())){
                        //额外奖品列表
                        List<ActivityHongbaoPrizeVo> prizeHongbaoList = hongbaoPrizeService.getByActId(params);
                        model.addAttribute("prizeHongbaoLists", JSON.toJSONString(prizeHongbaoList));
                    }
                }
                params.put("envirType",envioment);
                params.put("type",activity.getAtCode());
                //查询不同环境下，不同活动的h5访问地址
                model.addAttribute("links",resourceService.getListByEnivr(params));
            }
        }
        //查询优惠券列表
        List<ActivityPrize> prize_list1 = prizeService.getByActId(params);//其他品类和不中奖奖品
        List<ActivityPrize> prize_list2 = prizeService.getCouponByActId(params);//优惠券和借款券
        prize_list1.addAll(prize_list2);
        model.addAttribute("prizeLists",JSON.toJSONString(prize_list1));
        model.addAttribute("types",JSON.toJSONString(activityTypeService.getAll()));
        return "activity/form";
    }

    /**
     * 红包模板和转盘模板保存
     * @param request
     * @return
     */
    @RequiresPermissions("activity:list:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            String activitys = request.getParameter("activity");
            if( StringUtil.isEmpty(activitys)){
                throw new TqException("活动内容不能为空");
            }
            Activity activity = JSON.parseObject(activitys,Activity.class);
            //活动奖品
            String prizeRelLists = request.getParameter("prizeRelList");
            if( StringUtil.isEmpty(prizeRelLists)){
                throw new TqException("请添加活动奖品");
            }
            List<ActivityPrizeRel> prizeRelList = JSON.parseArray(prizeRelLists,ActivityPrizeRel.class);
            int total = BizConstant.INT_ZERO;
            for( ActivityPrizeRel rel : prizeRelList){
                total+= rel.getChance();
            }
            if( total!=100){
                throw new TqException("奖品概率之和不为100");
            }
            if( activity.getActId() == null){
                activity.setAddOperatorId(credential.getOpId());
            }else{
                activity.setModifyOperatorId(credential.getOpId());
                activity.setModifyTime(new Date());
            }
            if( activity.getAtCode().equals(ActivityTypeEnum.HONGBAO.getCode())){
                String prizeHongbaoLists = request.getParameter("prizeHongbaoList");
                if( StringUtil.isEmpty(prizeHongbaoLists)){
                    throw new TqException("请添加额外活动奖品");
                }
                List<ActivityHongbaoPrize> prizeHongbaoList = JSON.parseArray(prizeRelLists,ActivityHongbaoPrize.class);
                activityService.saveHongbao(activity,prizeRelList,prizeHongbaoList);
            }else{
                activityService.saveZhuanpan(activity,prizeRelList);
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

    /**
     *
     * @param request
     * @return
     */
    @RequiresPermissions("activity:list:edit")
    @RequestMapping(value = "/toEditStatic")
    @ResponseBody
    public String toEditStatic(HttpServletRequest request) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            String activitys = request.getParameter("activity");
            if( StringUtil.isEmpty(activitys)){
                throw new TqException("活动内容不能为空");
            }
            Activity activity = JSON.parseObject(activitys,Activity.class);
            String statics = request.getParameter("static");
            if( StringUtil.isEmpty(activitys)){
                throw new TqException("参数错误");
            }
            ActivityStatic activityStatic = JSON.parseObject(statics,ActivityStatic.class);
            if( activity.getActId() == null){
                activity.setAddOperatorId(credential.getOpId());
                activityStatic.setAddOperatorId(credential.getOpId());
            }else{
                activity.setModifyOperatorId(credential.getOpId());
                activity.setModifyTime(new Date());
                activityStatic.setModifyOperatorId(credential.getOpId());
                activityStatic.setModifyTime(new Date());
            }
            activityService.saveStatic(activity,activityStatic);
            return simpleSuccessJsonResult("success");
        }catch (Exception e){
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("save异常", e);
                return failJsonResult("save异常");
            }
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @RequiresPermissions("activity:list:edit")
    @RequestMapping(value = "/toEditExchange")
    @ResponseBody
    public String toEditExchange(HttpServletRequest request) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            String activitys = request.getParameter("activity");
            if( StringUtil.isEmpty(activitys)){
                throw new TqException("活动内容不能为空");
            }
            Activity activity = JSON.parseObject(activitys,Activity.class);
            String exchanges = request.getParameter("exchange");
            if( StringUtil.isEmpty(exchanges)){
                throw new TqException("参数错误");
            }
            ActivityExchange activityExchange = JSON.parseObject(exchanges,ActivityExchange.class);
            //兑换活动奖品
            String prizeExchangeLists = request.getParameter("prizeExchangeList");
            if( StringUtil.isEmpty(prizeExchangeLists)){
                throw new TqException("请添加兑换活动奖品");
            }
            List<ActivityExchangePrize> prizeExchangeList = JSON.parseArray(prizeExchangeLists,ActivityExchangePrize.class);
            //时间轴
            String timeAxiss = request.getParameter("timeAxiss");
            if( StringUtil.isEmpty(timeAxiss)){
                throw new TqException("请添加时间轴");
            }
            String[] timeAxis = JSON.parseObject(timeAxiss,String[].class);
            for(String axis : timeAxis){
                if( axis.indexOf(",") < BizConstant.INT_ONE){
                    throw new TqException("请填写正确格式的时间轴");
                }
            }
            activityExchange.setTimeAxis(StringUtils.join(timeAxis,","));
            if( activity.getActId() == null){
                activity.setAddOperatorId(credential.getOpId());
                activityExchange.setAddOperatorId(credential.getOpId());
            }else{
                activity.setModifyOperatorId(credential.getOpId());
                activity.setModifyTime(new Date());
                activityExchange.setModifyTime(new Date());
                activityExchange.setModifyOperatorId(credential.getOpId());
            }
            activityService.saveExchange(activity,prizeExchangeList,activityExchange);
            return simpleSuccessJsonResult("success");
        }catch (Exception e){
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("save异常", e);
                return failJsonResult("save异常");
            }
        }
    }
}
