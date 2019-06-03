package com.duobei.core.message.sms.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.enums.SmsUserfulCodeEnums;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.message.sms.dao.SmsAppChannelConfigDao;
import com.duobei.core.message.sms.dao.SmsAppDao;
import com.duobei.core.message.sms.domain.SmsApp;
import com.duobei.core.message.sms.domain.SmsAppChannelConfig;
import com.duobei.core.message.sms.domain.criteria.SmsAppChannelConfigCriteria;
import com.duobei.core.message.sms.domain.vo.SmsAppChannelConfigVo;
import com.duobei.core.message.sms.service.SmsAppChannelConfigService;
import com.duobei.core.operation.app.dao.AppDao;
import com.duobei.core.operation.app.domain.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/14
 */
@Service("smsAppChannelConfigService")
public class SmsAppChannelConfigServiceImpl implements SmsAppChannelConfigService{

    @Autowired
    private SmsAppChannelConfigDao channelConfigDao;
    @Autowired
    private AppDao appDao;
    @Autowired
    private SmsAppDao smsAppDao;


    private static String[] channelCode = {"NORMAL","COLLECTION","MARKETING"};//短信类别



    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public ListVo<SmsAppChannelConfigVo> getPage(SmsAppChannelConfigCriteria criteria){
        int total = channelConfigDao.countByCriteria(criteria);
        List<SmsAppChannelConfigVo> list = null;
        if (total > BizConstant.INT_ZERO) {
            list = channelConfigDao.getPageList(criteria);
            List<App> appList = appDao.getAll();
            for(SmsAppChannelConfigVo channelConfig :list){
                if( channelConfig.getBusinessCode().contains(SmsUserfulCodeEnums.COLLECTION.getCode())){
                    channelConfig.setChannelCodeName(SmsUserfulCodeEnums.COLLECTION.getMsg());
                }else if( channelConfig.getBusinessCode().contains(SmsUserfulCodeEnums.NORMAL.getCode())){
                    channelConfig.setChannelCodeName(SmsUserfulCodeEnums.NORMAL.getMsg());
                }else if( channelConfig.getBusinessCode().contains(SmsUserfulCodeEnums.MARKETING.getCode())){
                    channelConfig.setChannelCodeName(SmsUserfulCodeEnums.MARKETING.getMsg());
                }
                for (App app:appList){
                    if( app.getAppKey().equals(channelConfig.getAppKey())){
                        channelConfig.setAppName(app.getAppName());
                        break;
                    }
                }
            }
        }
        return new ListVo<SmsAppChannelConfigVo>(total, list);
    }

    @Override
    public  void editState(SmsAppChannelConfig config) throws TqException {
        if( channelConfigDao.update(config) < BizConstant.INT_ONE){
            throw new TqException("操作失败");
        }
    }

    @Override
    public List<SmsAppChannelConfig> getByAppKey(String appKey){
        return channelConfigDao.getByAppkey(appKey);
    }

    @Override
    public int countByAppKey(SmsAppChannelConfig config){
        return channelConfigDao.countByAppKey(config);
    }

    @Override
    public SmsAppChannelConfig getByRecord(SmsAppChannelConfig config){
        return channelConfigDao.getByRecord(config);
    }

    /**
     *
     * @param config
     * @throws TqException
     */
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void save(SmsAppChannelConfig config) throws TqException{
        App app = new App();
        app.setAppKey(config.getAppKey());
        if( appDao.countByAppKey(app) == BizConstant.INT_ZERO){
            throw new TqException("appKey不存在");
        }
        SmsAppChannelConfig record = new SmsAppChannelConfig()
                .setAppKey(config.getAppKey())
                .setBusinessCode(config.getBusinessCode());
        if( channelConfigDao.countByAppKey(record) > BizConstant.INT_ZERO){
            throw new TqException("不能添加重复类别");
        }
        if( channelConfigDao.save(config) < BizConstant.INT_ONE){
            throw new TqException("添加失败");
        }
        SmsApp smsApp = smsAppDao.getByAppkey(config.getAppKey());
        if( smsApp == null ){
            smsApp = new SmsApp();
            smsApp.setAppKey(config.getAppKey());
            smsApp.setMerchantNo("1001");
            smsApp.setDeleted(BizConstant.INT_ZERO.byteValue());
            smsAppDao.save(smsApp);
        }
    }

    /**
     *
     * @param config
     * @throws TqException
     */
    @Override
    public void update(SmsAppChannelConfig config) throws TqException{
        App app = new App();
        app.setAppKey(config.getAppKey());
        if( appDao.countByAppKey(app) == BizConstant.INT_ZERO){
            throw new TqException("appKey不存在");
        }
        SmsAppChannelConfig record = new SmsAppChannelConfig()
                .setId(config.getId())
                .setAppKey(config.getAppKey())
                .setBusinessCode(config.getBusinessCode());
        if( channelConfigDao.countByAppKey(record) > BizConstant.INT_ZERO){
            throw new TqException("不能添加重复类别");
        }
        if( channelConfigDao.update(config) < BizConstant.INT_ONE){
            throw new TqException("操作失败");
        }
    }

    @Override
    public SmsAppChannelConfig getById(Long id){
        return channelConfigDao.getById(id);
    }
}
