package com.duobei.console.web.controller.channel;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.channel.domain.ProductAppChannel;
import com.duobei.core.operation.channel.domain.criteria.ProductAppChannelCriteria;
import com.duobei.core.operation.channel.domain.vo.ProductAppChannelListVo;
import com.duobei.core.operation.channel.service.ProductAppChannelService;
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
                throw new TqException("登录过期，请重新登录");
            }
            //验证数据权限
            if( criteria.getAppId() !=null ){
                validAuthData(null,criteria.getAppId());
            }else{
                throw  new TqException("应用数据查询失败");

            }
            if (criteria.getPagesize()==0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<ProductAppChannelListVo> list = productAppChannelService.getListByQuery(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("查询"+DESC+"异常",e);
                return failJsonResult("查询失败");
            }
        }
    }


}
