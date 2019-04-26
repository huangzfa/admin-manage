package com.duobei.console.web.controller.product;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.product.domain.Merchant;
import com.duobei.core.operation.product.domain.criteria.BusinessCriteria;
import com.duobei.core.operation.product.domain.vo.BusinessServiceVo;
import com.duobei.core.operation.product.domain.vo.BusinessVo;
import com.duobei.core.operation.product.domain.vo.ServiceVo;
import com.duobei.core.operation.product.service.BusinessService;
import com.duobei.core.operation.product.service.BusinessServiceService;
import com.duobei.core.operation.product.service.ServiceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/4/25
 */
@Slf4j
@Controller
@RequestMapping(value = "${authzPath}/business")
public class BusinessController extends BaseController {

    @Autowired
    private BusinessService businessService;
    @Autowired
    private BusinessServiceService businessServiceService;
    @Autowired
    private ServiceService serviceService;

    /**
     * 商户管理列表页
     *
     * @param model
     * @return
     */
    @RequiresPermissions("business:list:view")
    @RequestMapping(value = "/list")
    public String index(Model model) {
        return "business/list";
    }

    /**
     * ajax查询列表
     *
     * @return
     */
    @RequiresPermissions("business:list:view")
    @RequestMapping(value = "/getBusinessData")
    @ResponseBody
    public String getMerchantData(BusinessCriteria criteria) {
        try {
            if (criteria == null) {
                criteria = new BusinessCriteria();
            }
            if (criteria.getPagesize() == 0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<BusinessVo> list = businessService.getPageList();
            return successJsonResult("success", "list", list);
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("ajax查询列表异常", e);
                return failJsonResult("查询失败");
            }
        }
    }

    /**
     * 表单添加页
     *
     * @param model
     * @return
     */
    @RequiresPermissions("business:list:edit")
    @RequestMapping(value = "/form")
    public String form(Model model, String bizCode) {
        if (!StringUtil.isBlank(bizCode)) {
            //所有服务类型
            List<ServiceVo> services = serviceService.getAll();
            List<BusinessServiceVo> list = businessServiceService.getByBizCode(bizCode);
            if( list.size() >0){
                for(BusinessServiceVo business:list){
                    for(ServiceVo service :services){
                        if(business.getServiceCode().equals(service.getServiceCode())){
                            service.setChecked("checked");
                        }
                    }
                }
            }
            model.addAttribute("services",services);
        }
        model.addAttribute("bizCode",bizCode);
        return "business/form";
    }

    /**
     * 保存修改操作
     *
     * @param request
     * @param bizCode
     * @return
     * @throws TqException
     */
    @RequiresPermissions("business:list:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(HttpServletRequest request,String bizCode) throws TqException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            if (StringUtil.isBlank(bizCode)) {
                throw new TqException("业务编码不能为空");
            }
            String serviceCodes = request.getParameter("serviceCodes");
            businessServiceService.save(bizCode,serviceCodes,credential.getOpId());
            return simpleSuccessJsonResult("success");
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("save业务服务异常", e);
                return failJsonResult("save异常");
            }

        }
    }

}
