package com.duobei.console.web.controller.app;

import com.alibaba.fastjson.JSON;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.copywrite.domain.CopywritingConfig;
import com.duobei.core.operation.copywrite.domain.criteria.CopywritingConfigCriteria;
import com.duobei.core.operation.copywrite.service.CopywritingConfigService;
import com.duobei.core.operation.product.domain.Product;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/1
 */
@Controller
@RequestMapping(value = "${authzPath}/app/copywrite")
public class CopywritingConfigController extends BaseController {
    private final static Logger log = LoggerFactory.getLogger(CopywritingConfigController.class);
    private final static String PERMISSIONPRE = "app:copywrite:";
    private final static String ADDRESSPRE = "app/copywrite/";
    private final static String DESC = "文案配置";

    @Resource
    CopywritingConfigService copywritingConfigService;


    @RequestMapping(value = "list")
    @RequiresPermissions(PERMISSIONPRE + "view")
    public String list(Integer productId , Model model){
        //获取用户产品列表
        List<Product> productList = getCredential().getProductList();
        if (productId == null && productList != null && productList.size() > 0){
            //如果未传productId 则赋予初始值
            productId = productList.get(0).getId();
        }
        model.addAttribute("productLists", JSON.toJSONString(productList));
        model.addAttribute("productId",productId);
        return ADDRESSPRE + "copywriteList";
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "copywriteList")
    @ResponseBody
    public String configList(CopywritingConfigCriteria configCriteria){
        OperatorCredential credential = getCredential();
        if (credential == null){
            return failJsonResult("用户未登录");
        }
        if (configCriteria.getProductId() == null){
            return failJsonResult("请选择产品");
        }else {
            try {
                validAuthData(configCriteria.getProductId());
            }catch (Exception e){
                log.error("用户{}，产品id:{}查询文案配置信息失败",credential,configCriteria.getProductId());
                return failJsonResult(e.getMessage());
            }

        }
        ListVo<CopywritingConfig> data = copywritingConfigService.getListByQuery(configCriteria);
        return successJsonResult("success","list",data);
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @ResponseBody
    @RequestMapping(value = "/form")
    public String getInfo(CopywritingConfig copywritingConfig) throws TqException {
        Map<String,Object> dataMap = new HashMap();
        //查询文案配置信息
        copywritingConfig = copywritingConfigService.getById(copywritingConfig.getId());
        dataMap.put("copywriteConfig",copywritingConfig);
        return successJsonResult(dataMap);
    }


    /**
     * 保存或修改
     * @param entity
     * @return
     * @throws TqException
     */
    @RequiresPermissions({ PERMISSIONPRE+"edit" })
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(CopywritingConfig entity) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }else{
                validAuthData(entity.getProductId());
            }
            //验证参数
            validParam(entity);
            //修改人、修改时间
            entity.setModifyTime(new Date());
            entity.setModifyOperatorId(credential.getOpId());
            //修改
            copywritingConfigService.update(entity);
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("修改"+DESC+"信息异常", e);
                return failJsonResult("修改"+DESC+"信息异常");
            }

        }
    }

    private void validParam(CopywritingConfig entity) throws TqException {
        if (entity.getCopywriting1() == null){
            throw new TqException("请输入文案内容");
        }
        if (entity.getCopywriting1().length() > 40){
            throw new TqException("文案长度在40字内");
        }
        //清除开头空格 结尾空格 换行符
        String copyWrite = entity.getCopywriting1();
        StringUtil.trim(copyWrite);
        entity.setCopywriting1(copyWrite);
    }
}
