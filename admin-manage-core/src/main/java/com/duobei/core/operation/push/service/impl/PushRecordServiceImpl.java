package com.duobei.core.operation.push.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.enums.IdWorker;
import com.duobei.common.exception.ManageExceptionCode;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.GuidUtil;
import com.duobei.common.util.ImportExcelUtil;
import com.duobei.common.util.QuartzManager;
import com.duobei.common.util.RegExpValidatorUtils;
import com.duobei.common.util.lang.DateUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.common.vo.ReturnParamsVo;
import com.duobei.core.manage.auth.dao.OperatorDao;
import com.duobei.core.manage.auth.domain.Operator;
import com.duobei.core.operation.app.dao.AppDao;
import com.duobei.core.operation.app.domain.App;
import com.duobei.core.operation.push.dao.PushFailUserDao;
import com.duobei.core.operation.push.dao.PushRecordDao;
import com.duobei.core.operation.push.dao.QuartzInfoDao;
import com.duobei.core.operation.push.domain.PushRecord;
import com.duobei.core.operation.push.domain.QuartzInfo;
import com.duobei.core.operation.push.domain.criteria.PushRecordCriteria;
import com.duobei.core.operation.push.domain.vo.PushRecordVo;
import com.duobei.core.operation.push.service.PushRecordService;
import com.duobei.core.operation.push.service.QuartzInfoService;
import com.duobei.core.user.user.dao.UserDao;
import com.duobei.core.user.user.domain.User;
import com.duobei.dic.ZD;
import com.pgy.data.handler.PgyDataHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.*;
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
    private QuartzInfoService quartzInfoService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AppDao appDao;
    @Autowired
    private PushFailUserDao failUserDao;
    @Resource
    private TransactionTemplate transactionTemplate;

    private ExecutorService executorService = new ThreadPoolExecutor(0,Runtime.getRuntime().availableProcessors() + 1,60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());


    @Override
    public PushRecord getById(Long id){
        return pushRecordDao.getById(id);
    }

    @Override
    public void update(PushRecord record) throws TqException{
        if( pushRecordDao.update(record) <BizConstant.INT_ONE){
            throw new TqException("操作失败");
        }
    }

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
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void save(PushRecord record,List<List<Object>> listob) throws TqException{
        //待推送人数
        if( record.getPlatform().equals(ZD.platform_user_id)){
            record.setPushCount(listob.size()-BizConstant.INT_ONE);
        }else{
            HashMap<String,Object> param = new HashMap<>();
            param.put("productId",record.getProductId());
            param.put("appId",record.getAppId());
            record.setPushCount(userDao.countByAppId(param));
        }
        if( pushRecordDao.save(record) < BizConstant.INT_ONE){
            throw new TqException("添加失败");
        }
        ReturnParamsVo result = new ReturnParamsVo();
        if( record.getPushType().equals(ZD.pushType_jg)){
            result = jgPush(record,listob);//极光
        }else {
            result = smsPush(record,listob);//短信
        }
        if(!result.getCode().equals(ManageExceptionCode.SUCCESS.getErrorCode())){
            PushRecord entity = new PushRecord()
                    .setId(record.getId())
                    .setRemark(result.getMsg())
                    .setState(ZD.pushState_fail);
            pushRecordDao.update(entity);
            throw new TqException(result.getMsg());
        }
    }


    /**
     * 定时推送
     * @param record
     * @return
     */
    @Override
    public ReturnParamsVo saveQuartzInfo(PushRecord record,List<List<Object>> listob){
        return transactionTemplate.execute(new TransactionCallback<ReturnParamsVo>() {
            @Override
            public ReturnParamsVo doInTransaction(TransactionStatus status) {
                ReturnParamsVo paramsDo = new ReturnParamsVo();
                try {
                    if(record.getId()!=null){
                        return paramsDo;
                    }
                    //待推送人数
                    if( record.getPlatform().equals(ZD.platform_user_id)){
                        record.setPushCount(listob.size()-BizConstant.INT_ONE);
                    }else{
                        HashMap<String,Object> param = new HashMap<>();
                        param.put("productId",record.getProductId());
                        param.put("appId",record.getAppId());
                        record.setPushCount(userDao.countByAppId(param));
                    }
                    if( pushRecordDao.save(record) < BizConstant.INT_ONE){
                        paramsDo.setCode(ManageExceptionCode.SYSTEM_ERROR.getErrorCode());
                        paramsDo.setMsg("添加失败");
                        return paramsDo;
                    }
                    //推送时间
                    String quarzRule = DateUtil.getQuarzRule(DateUtil.format_yyyy_MM_ddHHmmss(record.getPushTime()));
                    //添加一个定时任务对象
                    QuartzInfo qzInfo = new QuartzInfo()
                            .setCode("push_"+record.getId())
                            .setCycle(quarzRule)//秒 分 时 日 月 周 年
                            .setClassName("com.duobei.console.web.controller.job.QuartzMessagePush")
                            .setName(record.getPushTitle())
                            .setState(BizConstant.INT_ONE);

                    quartzInfoService.save(qzInfo);
                    paramsDo.setCode(ManageExceptionCode.SUCCESS.getErrorCode());
                }catch (Exception e){
                    log.error("添加定时任务异常 deleteQuartzInfo",e);
                    status.setRollbackOnly();
                    paramsDo.setCode(ManageExceptionCode.SYSTEM_ERROR.getErrorCode());
                    paramsDo.setMsg("添加定时任务异常"+e.getMessage());
                }
                return paramsDo;
            }
        });
    }

    /**
     * 获取极光推送人员名单
     * @param record
     * @param listob
     * @return
     */
    public List<Object> getJgPushUser(PushRecord record,List<List<Object>> listob){
        List<Map<String, Object>> failUser = new ArrayList<>();//发送失败用户
        List<Object> userIds = new ArrayList<>();//待发送用户id
        if( record.getPlatform().equals(ZD.platform_user_id) ){//导入excel推送
            if( listob == null ){
                File file = new File(record.getPath());
                if(file.isFile() && file.exists()) {
                    try {
                        InputStream in = new FileInputStream(file);
                        listob = ImportExcelUtil.getBankListByExcel(in,record.getPath());
                    }catch (Exception e){
                        log.error("xml解析异常",e);
                    }
                }
            }
            for (int i = 1; i < listob.size(); i++) {
                List<Object> lo = listob.get(i);
                //删除空列
                if( lo.size() == BizConstant.INT_ZERO ){
                    continue;
                }
                String userId = String.valueOf(lo.get(0)).replace(" ", "");
                if( StringUtil.isBlank(userId) ){
                    continue;
                }
                if (!RegExpValidatorUtils.IsIntNumber(userId)) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("userId",userId);
                    map.put("reason", "id格式错误");
                    failUser.add(map);
                    continue;
                }
                userIds.add(Long.parseLong(userId));
            }
            // 第二遍筛选，用户是否存在
            HashMap<String,Object> param = new HashMap<>();
            param.put("productId",record.getProductId());
            param.put("appId",record.getAppId());
            param.put("userList",userIds);
            List<User> successUser = userDao.getByAppId(param);
            List<Long> userIdList = successUser.stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
            for(int i=0;i<userIds.size();i++){
                if(!userIdList.contains(userIds.get(i))){
                    Map<String, Object> map = new HashMap<>();
                    map.put("userId",userIds.get(i));
                    map.put("reason", "用户不存在");
                    failUser.add(map);
                    userIds.remove(i);
                    i--;
                }else{
                    userIdList.remove(userIds.get(i));
                }
            }
            //推送失败名单入库
            if(failUser.size() >BizConstant.INT_ZERO){
                param.put("pushId",record.getId());
                param.put("failUser",failUser);
                failUserDao.batchInsert(param);
            }
        }else{
            HashMap<String,Object> param = new HashMap<>();
            param.put("productId",record.getProductId());
            param.put("appId",record.getAppId());
            List<User> userList = userDao.getByAppId(param);
            userIds = userList.stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
        }
        return userIds;
    }


    /**
     * 短信推送名单
     * @param record
     * @param listob
     * @return
     */
    public List<Object> getSmsPushUser(PushRecord record,List<List<Object>> listob){
        List<Map<String, Object>> failUser = new ArrayList<>();//发送失败用户
        List<Object> userIds = new ArrayList<>();//待发送用户id
        List<Object> userPhones = new ArrayList<>();//待发送用户手机号
        if( record.getPlatform().equals(ZD.platform_user_id) ){//导入excel推送
            if( listob == null ){
                File file = new File(record.getPath());
                if(file.isFile() && file.exists()) {
                    try {
                        InputStream in = new FileInputStream(file);
                        listob = ImportExcelUtil.getBankListByExcel(in,record.getPath());
                    }catch (Exception e){
                        log.error("xml解析异常",e);
                    }
                }
            }
            for (int i = 1; i < listob.size(); i++) {
                List<Object> lo = listob.get(i);
                //删除空列
                if( lo.size() == BizConstant.INT_ZERO ){
                    continue;
                }
                String userId = String.valueOf(lo.get(0)).replace(" ", "");
                if( StringUtil.isBlank(userId) ){
                    continue;
                }
                if (!RegExpValidatorUtils.IsIntNumber(userId)) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("userId",userId);
                    map.put("reason", "id格式错误");
                    failUser.add(map);
                    continue;
                }
                userIds.add(Long.parseLong(userId));
            }
            // 第二遍筛选，用户是否存在
            HashMap<String,Object> param = new HashMap<>();
            param.put("productId",record.getProductId());
            param.put("appId",record.getAppId());
            param.put("userList",userIds);
            List<User> successUser = userDao.getByAppId(param);
            List<Long> userIdList = successUser.stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
            for (int i = 0; i < userIds.size(); i++) {
                int index = userIdList.indexOf(userIds.get(i));
                if ( index == BizConstant.MINUS_ONE) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("userId",userIds.get(i));
                    map.put("reason", "用户不存在");
                    failUser.add(map);
                }else{
                    userPhones.add(PgyDataHandler.decrypt(successUser.get(index).getUserNameEncrypt()));
                    userIdList.remove(index);
                }
            }
            //推送失败名单入库
            if(failUser.size() >BizConstant.INT_ZERO){
                param.put("pushId",record.getId());
                param.put("failUser",failUser);
                failUserDao.batchInsert(param);
            }

        }else{
            HashMap<String,Object> param = new HashMap<>();
            param.put("productId",record.getProductId());
            param.put("appId",record.getAppId());
            List<User> userList = userDao.getByAppId(param);
            for(User user: userList){
                userPhones.add(PgyDataHandler.decrypt(user.getUserNameEncrypt()));
            }
        }
        return userPhones;
    }

    /**
     * 极光推送
     * @param record  推送内容
     * @param listob  推送人员名单
     * @return
     */
    @Override
    public ReturnParamsVo jgPush(PushRecord record,List<List<Object>> listob) {
        ReturnParamsVo paramsDo = new ReturnParamsVo();//返回结果

        App app = appDao.getById(record.getAppId());
        if( app == null ){
            paramsDo.setCode(ManageExceptionCode.SYSTEM_ERROR.getErrorCode());
            paramsDo.setMsg("应用不存在");
            return paramsDo;
        }
        List<Object> userIds = getJgPushUser(record,listob);
        List<List<Object>> list_list = averageAssign(userIds, 100);
        List<Future<Map<String, Object>>> resultList = new ArrayList<>();
        PushRecord entity = new PushRecord()
                .setId(record.getId())
                .setPushStartTime(new Date())
                .setSuccessCount(userIds.size())
                .setState(ZD.pushState_success);
        try {
            PushSysThread thread = null;
            for (List<Object> temp : list_list) {
                thread = new PushSysThread();
                thread.setUserList(temp);
                thread.setAppKey(app.getAppKey());
                thread.setRecord(record);
                //启动线程并返回执行结果
                Future<Map<String, Object>> future = executorService.submit(thread);
                resultList.add(future);
            }
            paramsDo.setCode(ManageExceptionCode.SUCCESS.getErrorCode());
        } catch (Exception e) {
            paramsDo.setCode(ManageExceptionCode.SYSTEM_ERROR.getErrorCode());
            paramsDo.setMsg("多线程发送失败"+e);
            entity.setState(ZD.pushState_fail);
        }
        pushRecordDao.update(entity);
        return paramsDo;
    }
    /**
     * 短信推送
     * @param record  推送内容
     * @param listob  推送人员名单
     * @return
     */
    @Override
    public ReturnParamsVo smsPush(PushRecord record,List<List<Object>> listob) {
        ReturnParamsVo paramsDo = new ReturnParamsVo();//返回结果
        App app = appDao.getById(record.getAppId());
        if( app == null ){
            paramsDo.setCode(ManageExceptionCode.SYSTEM_ERROR.getErrorCode());
            paramsDo.setMsg("应用不存在");
            return paramsDo;
        }
        List<Object> userPhones = getSmsPushUser(record,listob);
        List<List<Object>> list_list = averageAssign(userPhones, 100);
        List<Future<Map<String, Object>>> resultList = new ArrayList<>();
        PushRecord entity = new PushRecord()
                .setId(record.getId())
                .setPushStartTime(new Date())
                .setSuccessCount(userPhones.size())
                .setState(ZD.pushState_success);
        try {
            PushSmsThread thread = null;
            for (List<Object> temp : list_list) {
                thread = new PushSmsThread();
                thread.setPhoneList(userPhones);
                thread.setAppKey(app.getAppKey());
                thread.setRecord(record);
                //启动线程并返回执行结果
                Future<Map<String, Object>> future = executorService.submit(thread);
                resultList.add(future);
            }
            paramsDo.setCode(ManageExceptionCode.SUCCESS.getErrorCode());
        } catch (Exception e) {
            paramsDo.setCode(ManageExceptionCode.SYSTEM_ERROR.getErrorCode());
            paramsDo.setMsg("多线程发送失败"+e);
            entity.setState(ZD.pushState_fail);
        }
        pushRecordDao.update(entity);
        return paramsDo;
    }


    /**
     * 将list 拆分List<list<>>
     * @param source   要拆分的list
     * @param n        每个list元素个数
     * @return
     */
    public  List<List<Object>> averageAssign(List<Object> source,int n){
        List<List<Object>> result=new ArrayList<>();
        if( source.size()<= n){
            result.add(source);
        }else{
            int index_1 = 0,index_2 = 0;
            while( index_2< source.size() ){
                List<Object> value=null;
                index_2 = index_2 + n;
                if( index_2 >= source.size()){
                    index_2 = source.size();
                }
                value=source.subList(index_1 , index_2);
                index_1 = index_2;
                result.add(value);
            }
        }
        return result;
    }


}
