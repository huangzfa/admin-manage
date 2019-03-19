package com.duobei.console.web.controller.app;

import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.bank.domain.Bank;
import com.duobei.core.operation.bank.domain.criteria.BankCriteria;
import com.duobei.core.operation.bank.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.duobei.console.web.controller.base.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/19
 */
@Controller
@RequestMapping(value = "${authzPath}/app/bank")
public class BankController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(BankController.class);

    private final static String PERMISSIONPRE = "app:bank:";
    private final static String ADDRESSPRE = "app/bank/";
    private final static String DESC = "银行卡";
    @Resource
    BankService bankService;


    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/list")
    public String list() {
        return ADDRESSPRE+"bankList";
    }

    @RequiresPermissions(PERMISSIONPRE+"view")
    @RequestMapping(value = "/bankList")
    @ResponseBody
    public String bankList(BankCriteria bankCriteria){
        OperatorCredential credential = getCredential();
        if( credential == null){
            return failJsonResult("登录过期，请重新登录");
        }
        if (bankCriteria.getPagesize() == 0) {
            bankCriteria.setPagesize(GlobalConfig.getPageSize());
        }
        try {
            Map<String,Object> dataMap = new HashMap<>();
            //查询列表
            ListVo<Bank> list = bankService.getListByQuery(bankCriteria);
            dataMap.put("list",list);
            return successJsonResult(dataMap,"success");
        } catch (Exception e) {
            log.error("查询"+DESC+"列表异常", e);
            return failJsonResult("系统异常");
        }
    }


    /**
     * 跳转编辑页
     * @param model
     * @return
     * @throws TqException
     */
    @RequiresPermissions(PERMISSIONPRE+"edit")
    @RequestMapping(value = "/form")
    public String form(Model model, Integer id) throws TqException {
        if ( id !=null ) {
            //查询轮播图详情
            model.addAttribute("bank", bankService.getById(id));
        }else{
          throw new TqException("系统异常");
        }
        return "app/bank/bankForm";
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
    public String save(Bank entity) throws RuntimeException {
        try {
            OperatorCredential credential = getCredential();
            if (credential == null) {
                throw new TqException("登录过期，请重新登录");
            }
            //验证参数
            validParam(entity);
            //修改人、修改时间
            entity.setModifyTime(new Date());
            entity.setModifyOperatorId(credential.getOpId());
            //修改
            bankService.update(entity);
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

    private void validParam(Bank entity) {
    }

    /**
     * 修改银行状态
     * @param id
     * @param isEnable
     * @return
     * @throws TqException
     */
    @RequiresPermissions({ PERMISSIONPRE+"edit" })
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
            //查询银行是否存在
            Bank bank = bankService.getById(id);
            if( bank == null ){
                throw new TqException("bank不存在");
            }
            bank.setIsEnable(isEnable);
            bank.setModifyTime(new Date());
            bank.setModifyOperatorId(credential.getOpId());
            bankService.updateStatus(bank);
            return simpleSuccessJsonResult("success");

        } catch (Exception e) {
            if (e instanceof TqException) {
                return failJsonResult(e.getMessage());
            }else{
                log.error("editState银行异常", e);
                return failJsonResult("editState轮播图异常");
            }
        }
    }
}
