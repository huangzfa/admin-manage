package com.duobei.console.web.controller.app;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.app.domain.AppExamine;
import com.duobei.core.app.domain.criteria.AppExamineCriteria;
import com.duobei.core.app.service.AppExamineService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/25
 */
@Slf4j
@Controller
@RequestMapping(value = "${authzPath}/app/examine")
public class AppExamineController extends BaseController{

    @Autowired
    private AppExamineService appExamineService;
    /**
     *显示
     * @return
     */
    @RequiresPermissions("app:examine:view")
    @RequestMapping(value = "/list")
    public String list() {
        return "app/examine/list";
    }

    /**
     * 数据查询
     * @param appExamineCriteria
     * @return
     */
    @RequiresPermissions("app:examine:view")
    @ResponseBody
    @RequestMapping(value = "/getPageList")
    public String appExamList(AppExamineCriteria appExamineCriteria) {
        if (appExamineCriteria.getPagesize() == 0) {
            appExamineCriteria.setPagesize(GlobalConfig.getPageSize());
        }
        try {
            ListVo<AppExamine> list = appExamineService.getPageList(appExamineCriteria);
            return successJsonResult("success", "list", list);
        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            } else {
                log.warn("查询列表异常", e);
                return failJsonResult("查询异常，请查看错误日志");
            }
        }
    }
}
