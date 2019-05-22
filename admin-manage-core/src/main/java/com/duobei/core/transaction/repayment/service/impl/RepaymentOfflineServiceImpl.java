package com.duobei.core.transaction.repayment.service.impl;

import com.alibaba.fastjson.JSON;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.http.OkHttpUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.dao.OperatorDao;
import com.duobei.core.manage.auth.domain.Operator;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.zfb.domain.ZfbAccount;
import com.duobei.core.transaction.repayment.dao.RepaymentOfflineDao;
import com.duobei.core.transaction.repayment.domain.RepaymentOffline;
import com.duobei.core.transaction.repayment.domain.bo.RepaymentOfflineBo;
import com.duobei.core.transaction.repayment.domain.criteria.RepaymentOfflineCriteria;
import com.duobei.core.transaction.repayment.domain.vo.RepaymentOfflineVo;
import com.duobei.core.transaction.repayment.service.RepaymentOfflineService;
import com.duobei.core.user.user.dao.UserDao;
import com.duobei.core.user.user.domain.User;
import com.duobei.core.user.user.domain.vo.UserAndIdCardVo;
import com.duobei.core.user.user.domain.vo.UserInfoVo;
import com.duobei.utils.BizCacheUtil;
import com.pgy.data.handler.PgyDataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/20
 */
@Service("repaymentOfflineService")
public class RepaymentOfflineServiceImpl  implements RepaymentOfflineService {

    @Autowired
    private RepaymentOfflineDao offlineDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OperatorDao operatorDao;
    @Resource
    private BizCacheUtil bizCacheUtil;


    @Override
    public RepaymentOffline getById(Long id){
        return offlineDao.getById(id);
    }

    @Override
    public ListVo<RepaymentOfflineVo> getPage(RepaymentOfflineCriteria criteria) throws TqException{
        UserInfoVo user = userDao.getUserInfoVoById(criteria.getUserId().longValue());
        if( user == null ){
            throw new TqException("用户不存在");
        }
        List<RepaymentOfflineVo> list = null;
        int total = offlineDao.countByCriteria(criteria);
        if( total > BizConstant.INT_ZERO){
            list = offlineDao.getPage(criteria);
            for(RepaymentOfflineVo vo :list){
                vo.setAmount(vo.getAmount()==null?0:vo.getAmount()/100);
                vo.setRealOverdueAmount(vo.getRealOverdueAmount()==null?0:vo.getRealOverdueAmount()/100);
                vo.setRepaymentAmount(vo.getRepaymentAmount()==null?0:vo.getRepaymentAmount()/100);
                vo.setPhone(PgyDataHandler.decrypt(user.getUserNameEncrypt()));
                vo.setSubmitterName(PgyDataHandler.decrypt(user.getRealNameEncrypt()));
                vo.setRepaymentAmount(vo.getAmount()+vo.getRealOverdueAmount());
                vo.setRepayAmount(vo.getRepayAmount()/100);
                if( vo.getModifyOperatorId()!=null){
                    Operator operator = operatorDao.getById(vo.getModifyOperatorId());
                    if( operator!=null ){
                        vo.setModifyOperatorName(operator.getRealName());
                    }
                }
            }
        }
        return new ListVo<RepaymentOfflineVo>(total, list);
    }

    /**
     * 线下平账
     * @param bo
     * @throws TqException
     */
    @Override
    public void flatAccount(RepaymentOfflineBo bo) throws TqException {
        String result = OkHttpUtil.okHttpPostJson(GlobalConfig.getBusinessUrl() + "/repay/offline", JSON.toJSONString(bo));
        if (result == null || !result.contains("1000")) {
            throw new TqException(result);
        }
    }

    /**
     *批量平账
     * @param list
     */
    @Override
    public HashMap<String,Object> batchFlatAccount(List<RepaymentOfflineBo> list,ZfbAccount account,OperatorCredential credential){
        HashMap<String,Object> param = new HashMap<>();
        List<Map<String, String>> failMap = new ArrayList<>();//发送失败记录
        int failCount = 0;//平账失败数量
        int successeCount = 0;//平账成功数量
        for (RepaymentOfflineBo bo :list){
            if( bo.getRemark()!=null && bo.getRemark().contains("成功")){
                bo.setOperatorName(credential.getRealName());
                bo.setZfbAccountId(account.getId()+"");
                bo.setBalanceType(BizConstant.STRING_ONE);
                bo.setRepayType("2");//支付宝还款
                bo.setOperatorId(credential.getOpId()+"");
                String result = OkHttpUtil.okHttpPostJson(GlobalConfig.getBusinessUrl() + "/repay/offline", JSON.toJSONString(bo));
                if(result == null){
                    Map<String, String> map = new HashMap<>();
                    map.put(bo.getRepayNo(), "未知");
                    failMap.add(map);
                    failCount++;
                }else{
                    Map<String,String> map = (Map<String, String>) JSON.parse(result);
                    if (map.get("code") ==null || !map.get("code").equals("1000")){
                        map.remove("code");
                        map.put(bo.getRepayNo(), map.get("msg"));
                        map.remove("msg");
                        failMap.add(map);
                        failCount++;
                    }else{
                        successeCount++;
                    }
                }
            }else{
                Map<String, String> map = new HashMap<>();
                map.put(bo.getRepayNo(), bo.getRemark());
                failMap.add(map);
                failCount++;
            }
        }
        param.put("successeCount",successeCount);
        param.put("failCount",failCount);
        param.put("failMap",JSON.toJSONString(failMap));
        bizCacheUtil.saveObjectList("flatAccountFail", failMap);//失败记录存入缓存
        return param;
    }
}
