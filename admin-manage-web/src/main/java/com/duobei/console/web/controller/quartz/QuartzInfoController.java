package com.duobei.console.web.controller.quartz;

import com.alibaba.fastjson.JSON;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.GuidUtil;
import com.duobei.common.util.lang.NumberUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.console.web.controller.base.BaseController;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.push.domain.PushRecord;
import com.duobei.core.operation.push.domain.QuartzInfo;
import com.duobei.core.operation.push.domain.criteria.PushRecordCriteria;
import com.duobei.core.operation.push.domain.criteria.QuartzInfoCriteria;
import com.duobei.core.operation.push.domain.vo.PushRecordVo;
import com.duobei.core.operation.push.service.QuartzInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/16
 */
@Controller
@RequestMapping(value = "${authzPath}/quartz")
@Slf4j
public class QuartzInfoController extends BaseController {

    @Autowired
    private QuartzInfoService quartzInfoService;

    /**
     * 列表页
     * @param model
     * @return
     */
    @RequiresPermissions("quartz:list:view")
    @RequestMapping(value = "/list")
    public String list(Model model, Integer appId){
        return "quartz/list";
    }

    /**
     * ajax查询列表
     * @return
     */
    @RequiresPermissions("quartz:list:view")
    @RequestMapping(value = "/getData")
    @ResponseBody
    public String getMessageData(QuartzInfoCriteria criteria ){
        try {
            OperatorCredential credential = getCredential();
            if( credential == null){
                return failJsonResult("登录过期，请重新登录");
            }

            ListVo<QuartzInfo> list = quartzInfoService.getPageList(criteria);
            return successJsonResult("success", "list", list);
        }catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("查询列表异常",e);
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
    @RequiresPermissions("quartz:list:edit")
    @RequestMapping(value = "/form")
    public String form( Model model,String code)  {
        if(!StringUtil.isBlank(code)){
            model.addAttribute("quartz", quartzInfoService.getByCode(code));
        }
        return "quartz/form";
    }

    /**
     * 保存定时任务
     * @param info
     * @return
     */
    @RequiresPermissions("quartz:list:edit")
    @RequestMapping(value = "/save")
    @ResponseBody
    public String save(QuartzInfo info) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                return failJsonResult("登录过期，请重新登录");
            }
            if( info.getId()== null){
                info.setCode(GuidUtil.getSerialNumber());
                info.setAddOperatorId(credential.getOpId());
                quartzInfoService.save(info);
            }else{
                info.setModifyTime(new Date());
                info.setModifyOperatorId(credential.getOpId());
                quartzInfoService.update(info);
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

    @RequiresPermissions("quartz:list:edit")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(String code) {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                return failJsonResult("登录过期，请重新登录");
            }
            if(!StringUtil.isBlank(code)){
                return failJsonResult("参数错误");
            }
            QuartzInfo info = quartzInfoService.getByCode(code);
            if( info == null ){
                return failJsonResult("任务不存在");
            }
            QuartzInfo record = new QuartzInfo()
                    .setIsDelete(info.getId())
                    .setId(info.getId())
                    .setModifyTime(new Date())
                    .setModifyOperatorId(credential.getOpId());
            quartzInfoService.delete(record);
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

}
