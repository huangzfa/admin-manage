package com.duobei.console.web.controller.resource;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;

import com.duobei.core.operation.biz.domain.BizResourceLog;
import com.duobei.core.operation.biz.domain.criteria.ResourceLogCriteria;
import com.duobei.core.operation.biz.service.BizResourceLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**资源配置日志管理
 * Created by huangzhognfa on 2018/12/18.
 */
@Controller
@RequestMapping(value = "${authzPath}/resource/log")
@Slf4j
public class ResourceLogController extends BaseController {

    @Autowired
    private BizResourceLogService resourceLogService;

    /**
     * 资源列表页
     * @param model
     * @return
     */
    @RequiresPermissions("resource:log:view")
    @RequestMapping(value = "/list")
    public String index(Model model){
        return "resource/logList";
    }

    /**
     * ajax查询资源列表
     * @return
     */
    @RequiresPermissions("resource:log:view")
    @RequestMapping(value = "/getResourceLogData")
    @ResponseBody
    public String getResourceLogData(ResourceLogCriteria criteria){
        try {
            if( criteria == null ){
                criteria = new ResourceLogCriteria();
            }
            if (criteria.getPagesize()==0) {
                criteria.setPagesize(GlobalConfig.getPageSize());
            }
            ListVo<BizResourceLog> list = resourceLogService.getPage(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.warn("查询资源日志列表异常",e);
                return failJsonResult("查询失败");
            }
        }
    }

}
