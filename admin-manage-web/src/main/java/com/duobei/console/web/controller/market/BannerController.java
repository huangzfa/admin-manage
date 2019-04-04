package com.duobei.console.web.controller.market;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.app.service.AppService;
import com.duobei.core.operation.banner.service.BannerService;
import com.duobei.core.operation.banner.domain.Banner;
import com.duobei.core.operation.banner.domain.criteria.BannerCriteria;
import com.duobei.dic.ZD;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
/**
 * @author litianxiong
 * @description
 * @date 2019/3/1
 */
@Controller
@RequestMapping(value = "${authzPath}/market/banner")
public class BannerController extends com.duobei.console.web.controller.base.BaseController {

    private final static Logger log = LoggerFactory.getLogger(BannerController.class);

    @Resource
    private BannerService bannerService;

    @Resource
    AppService appService;

    /**
     * 轮播列表页
     * @param model
     * @return
     */
    @RequiresPermissions("market:banner:view")
    @RequestMapping(value = "/list")
    public String list(Model model,Integer appId){
        model.addAttribute("bannerType", JSON.toJSONString(DictUtil.getDictList(ZD.bannerType)));
        OperatorCredential credential = getCredential();
        model.addAttribute("appLists", JSON.toJSONString(credential.getAppList()));
        model.addAttribute("appId",appId);
        return "market/banner/bannerList";
    }

    /**
     * ajax查询轮播列表
     * @return
     */
    @RequiresPermissions("market:banner:view")
    @RequestMapping(value = "/bannerList")
    @ResponseBody
    public String getBannerList(BannerCriteria criteria ){
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
            ListVo<Banner> list = bannerService.getPage(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("查询播图列表异常",e);
                return failJsonResult("查询失败");
            }
        }
    }

    /**
     * 跳转编辑页
     * @param model
     * @return
     * @throws TqException
     */
    @RequiresPermissions("market:banner:edit")
    @RequestMapping(value = "/form")
    public String form( Model model,Integer id,Integer appId) throws TqException {
        //验证数据权限
        if( appId !=null ){
            validAuthData(null,appId);
        }else{
            throw  new TqException("应用数据查询失败");
        }
        if ( id !=null ) {
            //查询轮播图详情
            model.addAttribute("banner", bannerService.getById(id));
        }else{
            Banner banner = new Banner();
            banner.setRedirectType(ZD.redirectType_url);
            banner.setBannerType(ZD.bannerType_borrowTop);
            banner.setAppId(appId);
            model.addAttribute("banner",banner);
        }
        model.addAttribute("bannerType", DictUtil.getDictList(ZD.bannerType));
        model.addAttribute("redirectType", DictUtil.getDictList(ZD.redirectType));
        return "market/banner/bannerForm";
    }

    /**
     * 保存或修改
     * @param entity
     * @return
     * @throws TqException
     */
    @RequiresPermissions({ "market:banner:edit" })
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(Banner entity) throws RuntimeException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            //验证数据权限
            if( entity.getAppId() !=null ){
                validAuthData(null,entity.getAppId());
            }else{
                throw new TqException("数据操作权限失败");
            }

            if( entity.getRedirectType().equals(ZD.redirectType_no)){
                entity.setRedirectUrl("");
            }
            if (entity.getSort() == null){
                entity.setSort(0);
            }
            entity.setModifyTime(new Date());
            entity.setModifyOperatorId(credential.getOpId());
            if (entity.getId() == null || entity.getId().equals(0)) {
                /**
                 *  根据应用id查询产品信息
                 */
                App app = appService.getAppById(entity.getAppId());
                entity.setProductId(app.getProductId());
                //默认禁用
                entity.setIsEnable(0);
                entity.setAddOperatorId(credential.getOpId());
                entity.setAddTime(entity.getModifyTime());
                //新增
                bannerService.save(entity);
            } else {
                //修改
                bannerService.update(entity);
            }
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("save轮播图异常", e);
                return failJsonResult("save轮播图异常");
            }

        }

    }


    /**
     * 修改轮播图状态
     * @param id
     * @param isEnable
     * @return
     * @throws TqException
     */
    @RequiresPermissions({ "market:banner:edit" })
    @RequestMapping(value = "/editState")
    @ResponseBody
    public String editState(Integer id ,Integer isEnable) throws RuntimeException{
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if( isEnable == null || id == null){
                throw new TqException("参数为空");
            }
            //查询轮播图是否存在
            Banner banner = bannerService.getById(id);
            if( banner == null ){
                throw new TqException("轮播图不存在");
            }
            Banner banner1 = new Banner();
            banner1.setId(banner.getId());
            banner1.setIsEnable(isEnable);
            banner1.setModifyTime(new Date());
            banner1.setModifyOperatorId(credential.getOpId());
            bannerService.updateStatus(banner1);
            return simpleSuccessJsonResult("success");

        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("editState轮播图异常", e);
                return failJsonResult("editState轮播图异常");
             }
        }
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws TqException
     */
    @RequiresPermissions({ "market:banner:edit" })
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(Integer id) throws RuntimeException{
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if( id == null){
                throw new TqException("参数为空");
            }
            Banner banner = bannerService.getById(id);
            if( banner == null ){
                throw new TqException("轮播图不存在");
            }
            Banner banner1 = new Banner();
            banner1.setId(banner.getId());
            banner1.setModifyTime(new Date());
            banner1.setModifyOperatorId(credential.getOpId());
            banner1.setIsDelete(banner.getId());
            bannerService.delete(banner1);
            return simpleSuccessJsonResult("success");

        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("delete轮播图异常", e);
                return failJsonResult("delete轮播图异常");
            }
        }
    }
}
