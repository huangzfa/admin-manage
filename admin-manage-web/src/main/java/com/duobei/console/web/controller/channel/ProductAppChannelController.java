package com.duobei.console.web.controller.channel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.domain.Dict;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.service.AppService;
import com.duobei.core.operation.channel.domain.ProductAppChannel;
import com.duobei.core.operation.channel.domain.PromotionChannelStyle;
import com.duobei.core.operation.channel.domain.criteria.ProductAppChannelCriteria;
import com.duobei.core.operation.channel.domain.vo.ProductAppChannelListVo;
import com.duobei.core.operation.channel.service.ProductAppChannelService;
import com.duobei.core.operation.channel.service.PromotionChannelStyleService;

import java.util.*;

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

/**
 * @author litianxiong
 * @description
 * @date 2019/3/8
 */
@Controller
@RequestMapping(value = "${authzPath}/channel/productApp")
public class ProductAppChannelController extends BaseController {

    @Resource
    ProductAppChannelService productAppChannelService;

    @Resource
    PromotionChannelStyleService promotionChannelStyleService;

    @Resource
    AppService appService;

    private final static Logger log = LoggerFactory.getLogger(
            ProductAppChannelController.class);

    private final static String PERMISSIONPRE = "channel:productApp:";
    private final static String ADDRESSPRE = "channel/productApp/";
    private final static String DESC = "H5注册页链接";

    /**
     * 轮播列表页
     * @param model
     * @return
     */
    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/list")
    public String list(Model model, Integer appId){
        OperatorCredential credential = getCredential();
        model.addAttribute("appLists", JSON.toJSONString(credential.getAppList()));
        model.addAttribute("appId",appId);
        return ADDRESSPRE+"productAppChannelList";
    }

    /**
     * ajax查询轮播列表
     * @return
     */
    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/productAppChannelList")
    @ResponseBody
    public String getList(ProductAppChannelCriteria criteria ){
        OperatorCredential credential = getCredential();
        //验证用户权限
        try {
            if( credential == null){
                return failJsonResult("登录过期，请重新登录");
            }
            //验证数据权限
            if( criteria.getAppId() !=null ){
                try {
                    validAuthData(null,criteria.getAppId());
                }catch (Exception e){
                    return failJsonResult(e.getMessage());
                }
            }else{
                return failJsonResult("应用数据查询失败");

            }
            if (criteria.getPagesize()==0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<ProductAppChannelListVo> list = productAppChannelService.getListByQuery(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            log.error("查询"+DESC+"异常",e);
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                return failJsonResult("查询失败");
            }
        }
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/form")
    public String getInfo(Integer channelId, Integer appId) {
        //查询样式列表
        List<PromotionChannelStyle> styleList = promotionChannelStyleService.getAllList();
        //查询是否存在配置
        ProductAppChannel config = productAppChannelService.getByChannelAndAppId(channelId,appId);
        if (config == null){
            config = new ProductAppChannel();
            //给默认值，启用
            config.setIsEnable(ZD.isEnable_yes);
        }
        // 启用/禁用
        List<Dict> isEnableList = DictUtil.getDictList(ZD.isEnable);
        Map<String,Object> dataMap = new HashMap();
        dataMap.put("styleList",styleList);
        dataMap.put("config",config);
        dataMap.put("isEnableList",isEnableList);

        return successJsonResult(dataMap);
    }

    @RequiresPermissions(PERMISSIONPRE+"edit")
    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(ProductAppChannel productAppChannel) {
        OperatorCredential credential = getCredential();
        try {
            checkParam(productAppChannel);
            //修改时间、修改人
            productAppChannel.setModifyOperatorId(credential.getOpId());
            productAppChannel.setModifyTime(new Date());
            if (productAppChannel.getId() == null){
                //新增
                //查询产品id
                App app = appService.getAppById(productAppChannel.getAppId());
                productAppChannel.setProductId(app.getProductId());
                productAppChannel.setAddTime(productAppChannel.getModifyTime());
                productAppChannel.setAddOperatorId(credential.getOpId());
                productAppChannelService.save(productAppChannel);
            }else{
                //修改
                productAppChannelService.update(productAppChannel);
            }

        }catch (Exception e){
            if (e instanceof TqException){
                return failJsonResult(e.getMessage());
            }else{
                log.error(e.getMessage());
                return failJsonResult("系统异常");
            }
        }

        return simpleSuccessJsonResult();
    }

    private void checkParam(ProductAppChannel productAppChannel) throws TqException {
        if (productAppChannel.getChannelStyleId() == null){
            throw new TqException("请选择样式");
        }
    }
}
