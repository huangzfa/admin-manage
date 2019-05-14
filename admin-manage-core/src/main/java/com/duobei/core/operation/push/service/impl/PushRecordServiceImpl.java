package com.duobei.core.operation.push.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.enums.IdWorker;
import com.duobei.common.exception.ManageExceptionCode;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.GuidUtil;
import com.duobei.common.util.QuartzManager;
import com.duobei.common.util.lang.DateUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.common.vo.ReturnParamsVo;
import com.duobei.core.manage.auth.dao.OperatorDao;
import com.duobei.core.manage.auth.domain.Operator;
import com.duobei.core.manage.auth.domain.criteria.UserCriteria;
import com.duobei.core.operation.push.dao.PushRecordDao;
import com.duobei.core.operation.push.dao.QuartzInfoDao;
import com.duobei.core.operation.push.domain.PushRecord;
import com.duobei.core.operation.push.domain.QuartzInfo;
import com.duobei.core.operation.push.domain.criteria.PushRecordCriteria;
import com.duobei.core.operation.push.domain.vo.PushRecordVo;
import com.duobei.core.operation.push.service.PushRecordService;
import com.duobei.dic.ZD;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/13
 */
@Service("pushRecordService")
@Slf4j
public class PushRecordServiceImpl implements PushRecordService {

    @Autowired
    private PushRecordDao pushRecordDao;
    @Autowired
    private OperatorDao operatorDao;
    @Autowired
    private QuartzInfoDao quartzInfoDao;
    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    @Override
    public ListVo<PushRecordVo> getPageList(PushRecordCriteria criteria){
        if(!StringUtil.isBlank(criteria.getAddOperatorName())){
            Operator operator = operatorDao.getByRealName(criteria.getAddOperatorName());
            if( operator == null ){
                return new ListVo<PushRecordVo>(BizConstant.INT_ZERO, new ArrayList<>());
            }else{
                criteria.setAddOperatorId(operator.getAddOperatorId());
            }
        }
        int total = pushRecordDao.countByCriteria(criteria);
        List<PushRecordVo> list = null;
        if (total > BizConstant.INT_ZERO) {
            list = pushRecordDao.getPage(criteria);
            List<Integer> userIds = list.stream().map(PushRecord::getAddOperatorId).collect(Collectors.toList());
            List<Operator> operatorList = operatorDao.getByOpIds(userIds);
            for(PushRecordVo vo:list){
                for(Operator operator:operatorList){
                    if(vo.getAddOperatorId().equals(operator.getOpId())){
                        vo.setAddOperatorName(operator.getRealName());
                        break;
                    }
                }
            }
        }
        return new ListVo<PushRecordVo>(total, list);
    }

    /**
     * 立即推送
     * @param record
     * @throws TqException
     */
    public void save(PushRecord record) throws TqException{
        if( pushRecordDao.save(record) < BizConstant.INT_ONE){
            throw new TqException("添加失败");
        }

    }



    /**
     * 定时推送
     * @param record
     * @return
     */
    public ReturnParamsVo saveQuartzInfo(PushRecord record){
        return transactionTemplate.execute(new TransactionCallback<ReturnParamsVo>() {
            @Override
            public ReturnParamsVo doInTransaction(TransactionStatus status) {
                ReturnParamsVo paramsDo = new ReturnParamsVo();
                try {
                    if(record.getId()!=null){
                        return paramsDo;
                    }
                    if( pushRecordDao.save(record) < BizConstant.INT_ONE){
                        paramsDo.setCode(ManageExceptionCode.SYSTEM_ERROR.getErrorCode());
                        paramsDo.setMsg("添加失败");
                        return paramsDo;
                    }
                    //推送时间
                    String quarzRule = getQuarzRule(DateUtil.format_yyyy_MM_ddHHmmss(record.getPushTime()));
                    //添加一个定时任务对象
                    QuartzInfo qzInfo = new QuartzInfo();
                    qzInfo.setCode("PUSH_"+record.getId());
                    qzInfo.setCycle(quarzRule);//秒 分 时 日 月 周 年
                    qzInfo.setClassName("");
                    qzInfo.setName(record.getPushTitle());
                    qzInfo.setJobGroupName("yy_gorup_"+record.getId());//任务组组
                    qzInfo.setTriggerGroupName("yy_trigger_group_"+record.getId());//触发器组
                    qzInfo.setTriggerName("yy_trigger_"+ GuidUtil.getSerialNumber());//触发器名
                    if( quartzInfoDao.save(qzInfo) < BizConstant.INT_ONE){
                        paramsDo.setCode(ManageExceptionCode.SYSTEM_ERROR.getErrorCode());
                        paramsDo.setMsg("添加失败");
                        status.setRollbackOnly();
                        return paramsDo;
                    }
                    //添加任务到定时器
                    Object cl = Class.forName(qzInfo.getClassName()).newInstance();
                    JobDataMap jobDataMap = new JobDataMap();
                    jobDataMap.put("code",qzInfo.getCode());
                    QuartzManager.addJob(qzInfo.getCode(),jobDataMap,qzInfo.getJobGroupName(),qzInfo.getTriggerName(),qzInfo.getTriggerGroupName(),cl.getClass(),qzInfo.getCycle());
                    paramsDo.setCode(ManageExceptionCode.SUCCESS.getErrorCode());
                }catch (Exception e){
                    log.error("删除定时任务异常 deleteQuartzInfo",e);
                    status.setRollbackOnly();
                    paramsDo.setCode(ManageExceptionCode.SYSTEM_ERROR.getErrorCode());
                    paramsDo.setMsg("删除定时任务异常"+e.getMessage());
                }
                return paramsDo;
            }
        });
    }

    public static String getQuarzRule(String startTime){
        String ss = startTime.substring(17,19);
        String ff = startTime.substring(14,16);
        String hh = startTime.substring(11,13);
        String dd = startTime.substring(8,10);
        String mm = startTime.substring(5,7);
        String yy = startTime.substring(0,4);
        return ss + " " + ff + " " + hh + " " + dd + " " + mm + " ? " +yy;
    }
}
