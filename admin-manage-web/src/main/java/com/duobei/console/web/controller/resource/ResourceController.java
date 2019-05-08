package com.duobei.console.web.controller.resource;


import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.biz.domain.BizResource;
import com.duobei.core.operation.biz.domain.criteria.BizResourceCriteria;
import com.duobei.core.operation.biz.service.BizResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**资源配置类
 * Created by huangzhongfa on 2018/12/18.
 */
@Controller
@RequestMapping(value = "${authzPath}/resource")
@Slf4j
public class ResourceController extends BaseController {


    @Autowired
    private BizResourceService resourceService;

    /**
     * 资源列表页
     * @param model
     * @return
     */
    @RequiresPermissions("resource:list:view")
    @RequestMapping(value = "/list")
    public String index(Model model){
        model.addAttribute("productList", JSON.toJSONString(getCredential().getProductList()));
        return "resource/list";
    }

    /**
     * ajax查询资源列表
     * @return
     */
    @RequiresPermissions("resource:list:view")
    @RequestMapping(value = "/getResourceData")
    @ResponseBody
    public String getResourceData(BizResourceCriteria criteria){
        try {
            if( criteria == null ){
                criteria = new BizResourceCriteria();
            }
            if (criteria.getPagesize()==0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<BizResource> list = resourceService.getPage(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("查询资源列表异常",e);
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
    @RequiresPermissions("resource:list:edit")
    @RequestMapping(value = "/form")
    public String form(Model model, Integer id,Integer productId) throws TqException {
        model.addAttribute("productId", productId);
        model.addAttribute("appList", getAppListByProductId(productId));
        if ( id !=null ) {
            model.addAttribute("resource", resourceService.getById(id));
        }else{
            model.addAttribute("resource", new BizResource());
        }
        return "resource/form";
    }

    /**
     * 保存或修改
     * @param entity
     * @return
     * @throws TqException
     */
    @RequiresPermissions({ "resource:list:edit" })
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(BizResource entity) throws RuntimeException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new RuntimeException("登录过期，请重新登录");
            }
            entity.setModifyTime(new Date());
            entity.setModifyOperatorId(credential.getOpId());
            if (entity.getId() == null || entity.getId().equals(0)) {
                entity.setAddOperatorId(credential.getOpId());
                resourceService.save(entity);
            } else {
                resourceService.update(entity);
            }
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("save资源配置异常", e);
                return failJsonResult("save资源配置异常");
            }
        }
    }
    /**
     * 保存或修改
     * @param id
     * @return
     * @throws TqException
     */
    @RequiresPermissions({ "resource:list:edit" })
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(Integer id) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if( id == null || id.equals(0)){
                throw new TqException("参数错误");
            }
            BizResource entity = resourceService.getById(id);
            if( entity == null ){
                throw new TqException("资源不存在");
            }
            BizResource record = new BizResource();
            record.setIsDelete(entity.getId());
            record.setId(entity.getId());
            record.setModifyTime(new Date());
            record.setResType(entity.getResType());
            record.setResTypeSec(entity.getResTypeSec());
            record.setProductId(entity.getProductId());
            record.setAppId(entity.getAppId());
            record.setModifyOperatorId(credential.getOpId());
            resourceService.delete(record);
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("delete资源配置异常", e);
                return failJsonResult("delete资源配置异常");
            }
        }
    }
}
