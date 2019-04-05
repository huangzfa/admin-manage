package com.duobei.console.web.controller.app;

import com.alibaba.fastjson.JSON;
import com.duobei.common.enums.FunctionSwitchEnum;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.functionSwitch.domain.FunctionSwitchApp;
import com.duobei.core.operation.functionSwitch.domain.criteria.FunctionSwitchCriteria;
import com.duobei.core.operation.functionSwitch.domain.vo.FunctionSwitchAppVo;
import com.duobei.core.operation.functionSwitch.service.FunctionSwitchService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/2
 */

@Controller
@RequestMapping(value = "${authzPath}/app/studentConfirm")
public class StudentConfirmController extends BaseController {
    private final static Logger log = LoggerFactory.getLogger(StudentConfirmController.class);
    private final static String PERMISSIONPRE = "app:studentConfirm:";
    private final static String ADDRESSPRE = "app/studentConfirm/";
    private final static String DESC = "学生身份弹窗";

    @Resource
    FunctionSwitchService functionSwitchService;

    @RequestMapping(value = "list")
    @RequiresPermissions(PERMISSIONPRE + "view")
    public String list(Integer appId , Model model){
        //获取用户产品列表
        List<App> appList = getCredential().getAppList();
        if (appId == null && appList != null && appList.size() > 0){
            //如果未传productId 则赋予初始值
            appId = appList.get(0).getId();
        }
        model.addAttribute("appLists", JSON.toJSONString(appList));
        model.addAttribute("appId",appId);
        return ADDRESSPRE + "studentConfirmForm";
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "studentConfirmList")
    @ResponseBody
    public String switchList(FunctionSwitchCriteria functionSwitchCriteria){
        OperatorCredential credential = getCredential();
        if (credential == null){
            return failJsonResult("用户未登录");
        }
        if (functionSwitchCriteria.getAppId() == null){
            return failJsonResult("请选择应用");
        }else {
            try {
                validAuthData(null,functionSwitchCriteria.getAppId());
            }catch (Exception e){
                log.error("用户{}，应用id:{}，学生身份弹窗查询失败",credential,functionSwitchCriteria.getAppId());
                return failJsonResult(e.getMessage());
            }

        }
        //给与查询code 非学生认证
        functionSwitchCriteria.setFunctionCode(FunctionSwitchEnum.NO_SHTUDENT_AUTH_POPUP.getCode());

        List<FunctionSwitchAppVo> data = functionSwitchService.getListByQuery(functionSwitchCriteria);

        return successJsonResult("success","list",data);
    }

    @RequiresPermissions(PERMISSIONPRE+"edit")
    @ResponseBody
    @RequestMapping(value = "/editState")
    public String updateStatus(FunctionSwitchApp entity){
        OperatorCredential credential = getCredential();
        try {
            entity.setModifyOperatorId(credential.getOpId());
            entity.setModifyTime(new Date());
            if (entity.getId() != null){
                /**
                 * 修改
                 */
                functionSwitchService.updateStatus(entity);
            }else{
                /**
                 * 新增
                 */
                entity.setAddOperatorId(credential.getOpId());
                entity.setAddTime(entity.getModifyTime());
                //默认版本号 ALL
                entity.setVersionNumber("all");
                functionSwitchService.addSwitchApp(entity);
            }
        }catch (Exception e){
            log.error("学生身份认证"+entity+"修改失败");
            return failJsonResult("系统异常");
        }
        Map<String,Object> resultMap = new HashMap<>();
        return successJsonResult(resultMap);
    }


}