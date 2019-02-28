package com.duobei.core.operation.app.service.impl;

import com.duobei.common.config.Global;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.dao.mapper.OperatorMapper;
import com.duobei.core.manage.auth.domain.Operator;
import com.duobei.core.operation.app.dao.AppExamineDao;
import com.duobei.core.operation.app.domain.AppExamine;
import com.duobei.core.operation.app.domain.criteria.AppExamineCriteria;
import com.duobei.core.operation.app.service.AppExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/26
 */
@Service("AppExamineService")
public class AppExamineServiceImpl implements AppExamineService {

    @Autowired
    private AppExamineDao appExamineDao;
    @Autowired
    private OperatorMapper operatorMapper;

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    @Override
    public ListVo<AppExamine> getPageList(AppExamineCriteria criteria) throws Exception{
        Long total = appExamineDao.countCriteria(criteria);
        List<AppExamine> appExamineVos = null;
        if (total > 0) {
            appExamineVos = appExamineDao.getPageList(criteria);
        }
        AppExamine examine =new AppExamine();
        examine.setProductId(1);
        examine.setAddOperatorId(1);
        examine.setChannelId(1);
        examine.setAppOsType("ios");
        examine.setAppId(1);
        appExamineDao.save(examine);
        Operator operator = new Operator();
        operator.setLoginName("张三");
        operator.setRealName("张三");
        operator.setSessionId("123sd1f56asdf");
        operator.setLoginPwd("12332");
        operator.setOperatorState("100");
        operator.setIsDelete(Global.delete_not);
        operator.setAddOperatorId(1);
        operator.setAddTime(new Date());
        operatorMapper.insertSelective(operator);



        return new ListVo<AppExamine>(total.intValue(),appExamineVos);


    }


}
