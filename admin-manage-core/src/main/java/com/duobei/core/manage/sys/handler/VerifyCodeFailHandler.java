package com.duobei.core.manage.sys.handler;

import com.duobei.common.util.lang.DateUtil;
import com.duobei.core.manage.auth.domain.VerifyCodeFail;
import com.duobei.core.manage.auth.service.VerifyCodeFailService;
import com.duobei.common.exception.TqException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

/**短信验证码输入错误次数handler
 * @author huangzhongfa
 * @description
 * @date 2018/12/26
 */
@Component
public class VerifyCodeFailHandler {

    @Autowired
    private VerifyCodeFailService verifyCodeFailService;


    /**
     *短信验证码出入错误记录
     * @param loginName
     * @throws TqException
     */
    public void updateVerifyCodeFail(String loginName,int smsBizType) throws TqException{

        HashMap<String,Object> params = new HashMap<>();
        Integer gmtTime = Integer.parseInt(DateUtil.format_yyyyMMdd(new Date()));
        params.put("loginName",loginName);
        params.put("gmtTime", gmtTime);
        VerifyCodeFail fail = verifyCodeFailService.getByParam(params);
        if( fail ==null ){
            fail = new VerifyCodeFail();
            fail.setGmtTime(gmtTime);
            fail.setLoginName(loginName);
            fail.setFailCount(1);
            fail.setSmsBizType(smsBizType);
            verifyCodeFailService.save(fail);
        }else{
            if( fail.getFailCount() >=5){
                throw new TqException("验证码错误次数超限，请联系管理员");
            }
            fail.setFailCount(fail.getFailCount()+1);
            verifyCodeFailService.update(fail);
        }
    }
}
