package com.duobei.core.operation.coupon.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.RegExpValidatorUtils;
import com.duobei.common.util.lang.DateUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.coupon.dao.CouponDao;
import com.duobei.core.operation.coupon.dao.CouponSendRecordDao;
import com.duobei.core.operation.coupon.dao.CouponUserDao;
import com.duobei.core.operation.coupon.dao.mapper.CouponSendRecordMapper;
import com.duobei.core.operation.coupon.domain.Coupon;
import com.duobei.core.operation.coupon.domain.CouponSendRecord;
import com.duobei.core.operation.coupon.domain.CouponUser;
import com.duobei.core.operation.coupon.domain.criteria.CouponSendRecordCriteria;
import com.duobei.core.operation.coupon.domain.vo.CouponSendRecordVo;
import com.duobei.core.operation.coupon.service.CouponSendRecordService;
import com.duobei.core.user.user.dao.UserDao;
import com.duobei.core.user.user.domain.User;
import com.duobei.dic.ZD;
import com.duobei.utils.BizCacheUtil;
import com.pgy.data.handler.service.PgyDataHandlerService;
import com.pgy.data.handler.service.impl.PgyDataHandlerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/5/10
 */
@Service("couponSendRecordService")
@Slf4j
public class CouponSendRecordServiceImpl implements CouponSendRecordService {
    @Autowired
    private CouponDao couponDao;
    @Autowired
    private CouponUserDao couponUserDao;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private BizCacheUtil bizCacheUtil;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CouponSendRecordMapper recordMapper;
    @Autowired
    private CouponSendRecordDao sendRecordDao;

    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);

    private PgyDataHandlerService pgyDataHandlerService = new PgyDataHandlerServiceImpl();
    /**
     * 分页查询优惠券
     * @param criteria
     * @return
     */
    @Override
    public ListVo<CouponSendRecordVo> getPage(CouponSendRecordCriteria criteria){
        int total = sendRecordDao.countByCriteria(criteria);
        List<CouponSendRecordVo> list = null;
        if (total > BizConstant.INT_ZERO) {
            list = sendRecordDao.getPage(criteria);
        }
        return new ListVo<CouponSendRecordVo>(total, list);
    }

    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void sendCouponByPhone(String phone, Long couponId,  OperatorCredential credential) throws TqException {
        Coupon coupon = couponDao.getCouponById(couponId);
        if (coupon == null) {
            throw new TqException("优惠券不存在");
        }
        if (coupon.getExpiryType().equals(BizConstant.INT_ONE ) && DateUtil.compareDate(new Date(), coupon.getGmtEnd())) {
            throw new TqException("优惠券已过期");
        }
        if (!coupon.getQuota().equals(BizConstant.MINUS_ONE) && coupon.getQuotaSend() >= coupon.getQuota()) {
            throw new TqException("优惠券已使用完");
        }
        User user = userDao.getByUserNameEncrypt(pgyDataHandlerService.encrypt(phone),coupon.getProductId());
        if (user == null) {
            throw new TqException("用户不存在");
        }
        if (coupon.getPersonLimitCount() > BizConstant.INT_ZERO) {
            int count = couponUserDao.countByUserId(user.getId(), couponId,coupon.getProductId());
            if (count >= coupon.getPersonLimitCount()) {
                throw new TqException("用户已达到限制领取张数");
            }
        }
        CouponUser record = new CouponUser()
                .setProductId(coupon.getProductId())
                .setUserId(user.getId())
                .setCouponId(couponId.intValue())
                .setAddTime(new Date())
                .setCouponType(coupon.getCouponType())
                .setProductId(coupon.getProductId())
                .setFromType(ZD.fromType_sys)
                .setFromRef("sys")
                .setState(ZD.couponState_new)
                .setTimeFlag(BizConstant.INT_ONE);

        //固定天数
        if (coupon.getExpiryType().equals(BizConstant.INT_ZERO)) {
            record.setStartTime(record.getAddTime());
            record.setEndTime(DateUtil.addDays(record.getAddTime(), coupon.getValidDays()));
        } else {
            record.setStartTime(coupon.getGmtStart());
            record.setEndTime(coupon.getGmtEnd());
        }
        if (couponUserDao.save(record) != BizConstant.INT_ONE) {
            throw new TqException("发送失败");

        }
        Coupon coupon1 = new Coupon();
        coupon1.setId(coupon.getId());
        coupon1.setQuotaSend(coupon.getQuotaSend() + BizConstant.INT_ONE);
        coupon1.setModifyOperatorId(credential.getOpId());
        coupon1.setModifyTime(new Date());
        if (couponDao.updateSend(coupon1) != BizConstant.INT_ONE) {
            throw new TqException("优惠券更新失败");
        }
        CouponSendRecord sendrecord = new CouponSendRecord()
                .setProductId(coupon.getProductId())
                .setSendType(BizConstant.INT_ONE)//单个
                .setCouponId(couponId)
                .setUserVal(phone)
                .setSuccessCount(BizConstant.INT_ONE)
                .setAddOperatorId(credential.getOpId());
        recordMapper.insertSelective(sendrecord);
    }

    /**
     * 批量发送优惠券
     *
     * @param listob
     * @param couponId
     * @param credential
     * @return
     * @throws TqException
     */
    @Override
    public HashMap<String, Object> batchSendCoupon(String path, List<List<Object>> listob, Long couponId, OperatorCredential credential) throws TqException {
        List<Map<String, String>> failUser = new ArrayList<>();//发送失败用户
        Coupon coupon = couponDao.getCouponById(couponId);
        int failCount = 0;//发送失败用户数量
        if (coupon == null) {
            throw new TqException("优惠券不存在");
        }
        if (coupon.getExpiryType().equals(BizConstant.INT_ONE ) && DateUtil.compareDate(new Date(), coupon.getGmtEnd())) {
            throw new TqException("优惠券已过期");
        }
        if (!coupon.getQuota().equals(BizConstant.MINUS_ONE) && coupon.getQuotaSend() >= coupon.getQuota()) {
            throw new TqException("优惠券已使用完");
        }
        List<Long> userIds = new ArrayList<>();
        // 第一遍筛选
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i); //第行数据
            //删除空列
            if( lo.size() == BizConstant.INT_ZERO ){
                continue;
            }
            String userId = String.valueOf(lo.get(0)).replace(" ", "");//第一列
            //删除空格
            if( StringUtil.isBlank(userId) ){
                continue;
            }
            if (!RegExpValidatorUtils.IsIntNumber(userId)) {
                Map<String, String> map = new HashMap<>();
                map.put(userId, "id格式错误");
                failUser.add(map);
                continue;
            }
            userIds.add(Long.parseLong(userId));
        }
        failCount = listob.size() - userIds.size();
        // 第二遍筛选，用户是否存在
        List<User> successUser = userDao.getByIds(coupon.getProductId(),userIds);
        //提取出list对象中的一个属性
        List<Long> userIdList = successUser.stream()
                .map(User::getId)
                .collect(Collectors.toList());

        for (int i = 0; i < userIds.size(); i++) {
            if (!userIdList.contains(userIds.get(i))) {
                Map<String, String> map = new HashMap<>();
                map.put(userIds.get(i)+"", "用户不存在");
                failUser.add(map);
                failCount++;//计算发送失败用户
                userIds.remove(userIds.get(i));
                i--;
            }
        }
        if (userIdList.size() == BizConstant.INT_ZERO) {
            throw new TqException("都是无效用户");
        }
        // 第三遍筛选，优惠券足够的用户
        List<Long> e_list_id = userIdList;//可以发送的用户
        List<Long> i_list_id = new ArrayList<>();//不能发送的用户id
        if (!coupon.getQuota().equals(BizConstant.MINUS_ONE)){
            // 优惠券已发送完
            if(coupon.getQuotaSend() >= coupon.getQuota()) {
                i_list_id = userIdList;
            }else {
                int surplus = coupon.getQuota() - coupon.getQuotaSend();
                if (surplus < userIdList.size()) {
                    e_list_id = userIdList.subList(BizConstant.INT_ZERO, surplus);//一部分可以发用户
                    i_list_id = userIdList.subList(surplus, userIdList.size());//一部分不能发送用户id
                }
            }
        }
        if (e_list_id.size() == BizConstant.INT_ZERO) {
            throw new TqException("都是无效用户");
        }

        failCount += i_list_id.size();//计算发送失败用户
        if( i_list_id.size() > BizConstant.INT_ZERO){
            for (Long s : i_list_id) {
                Map<String, String> map = new HashMap<>();
                map.put(s+"", "优惠券不足");
                failCount++;
                failUser.add(map);
            }
        }

        // 第四遍筛选，优惠券有领取限制
        if (coupon.getPersonLimitCount() > BizConstant.INT_ZERO) {
            List<Map<String, Object>> userCountMap = couponUserDao.getCountGroupUserId(coupon.getProductId(), couponId,e_list_id);
            for (int m = 0; m < userCountMap.size(); m++) {
                Map<String, Object> map = userCountMap.get(m);
                Long userId = map.get("userId") == null ? 0 : Long.parseLong(map.get("userId").toString());
                int count = map.get("count") == null ? 0 : Integer.parseInt(map.get("count").toString());
                if (count >= coupon.getPersonLimitCount()) {
                    Map<String, String> failMap = new HashMap<>();
                    failMap.put(userId+"", "已达到优惠券发送次数限制");
                    failUser.add(failMap);
                    e_list_id.remove(userId);
                    userCountMap.remove(userCountMap.get(m));
                    failCount++;//计算发送失败用户
                    m--;
                }
            }
        }

        List<CouponUser> userCoupons = new ArrayList<>();
        for (Long userId : e_list_id) {
            CouponUser record = new CouponUser()
                    .setProductId(coupon.getProductId())
                    .setUserId(userId)
                    .setCouponId(couponId.intValue())
                    .setAddTime(new Date())
                    .setCouponType(coupon.getCouponType())
                    .setProductId(coupon.getProductId())
                    .setFromType(ZD.fromType_sys)
                    .setFromRef("sys")
                    .setState(ZD.couponState_new)
                    .setTimeFlag(BizConstant.INT_ONE);

            //固定天数
            if (coupon.getExpiryType().equals(BizConstant.INT_ZERO)) {
                record.setStartTime(record.getAddTime());
                record.setEndTime(DateUtil.addDays(record.getAddTime(), coupon.getValidDays()));
            } else {
                record.setStartTime(coupon.getGmtStart());
                record.setEndTime(coupon.getGmtEnd());
            }
            userCoupons.add(record);
        }

        //拆分list，多线程发送任务
        List<List<CouponUser>> list_list = averageAssign(userCoupons, 100);
        HashMap<String, Object> map = new HashMap<>();
        Coupon coupon1 = new Coupon()
                .setId(coupon.getId())
                .setQuotaSend(coupon.getQuotaSend() + e_list_id.size())
                .setModifyOperatorId(credential.getOpId())
                .setModifyTime(new Date());
        map.put("failCount", failCount);
        map.put("successCount", e_list_id.size());
        if(!coupon.getQuota().equals(BizConstant.MINUS_ONE)){
            map.put("couponCount", coupon.getQuota() - coupon1.getQuotaSend());
        }else{
            map.put("couponCount", "无限");
        }
        bizCacheUtil.saveObjectList("sendFailUser", failUser);//失败用户存入缓存
        if (userCoupons.size() ==BizConstant.INT_ZERO) {
            return map;
        }
        return transactionTemplate.execute(new TransactionCallback<HashMap<String, Object>>() {
            @Override
            public HashMap<String, Object> doInTransaction(TransactionStatus status) {

                for (List<CouponUser> temp : list_list) {
                    //启动线程并返回执行结果
                    SendCouponThread thread = new SendCouponThread();
                    thread.setTemp(temp);
                    Future<String> future = executorService.submit(thread);
                    try {
                        //返回结果不为空，有异常
                        if (future.get() != null) {
                            map.put("msg", future.get());
                            status.setRollbackOnly();
                            break;
                        }
                    } catch (InterruptedException e) {
                        map.put("msg", "优惠券发送异常");
                        e.printStackTrace();
                        status.setRollbackOnly();

                    } catch (ExecutionException e) {
                        map.put("msg", "优惠券发送异常");
                        e.printStackTrace();
                        status.setRollbackOnly();
                    }
                }
                if (map.get("msg") == null) {
                    try {
                        if (couponDao.updateSend(coupon1) != BizConstant.INT_ONE) {
                            map.put("msg", "优惠券更新失败");
                            status.setRollbackOnly();
                        }
                        CouponSendRecord sendrecord = new CouponSendRecord()
                                .setProductId(coupon.getProductId())
                                .setSendType(2)//批量
                                .setCouponId(couponId)
                                .setUserVal(path)
                                .setSuccessCount(Integer.parseInt(map.get("successCount").toString()))
                                .setFailCount(Integer.parseInt(map.get("failCount").toString()))
                                .setAddOperatorId(credential.getOpId());
                        recordMapper.insertSelective(sendrecord);
                    } catch (Exception e) {
                        map.put("msg", "batchSendCoupon优惠券更新失败");
                        status.setRollbackOnly();
                        log.warn("batchSendCoupon优惠券更新失败", e);
                    }
                }
                return map;
            }
        });
    }

    /**
     * 拆分list,如50个用户一个集合，
     *
     * @param list
     * @param n
     * @return
     */
    public List<List<CouponUser>> averageAssign(List<CouponUser> list, int n) {
        List<List<CouponUser>> newList = new ArrayList<>();//定义List<list<>>
        if (list.size() <= n) {
            newList.add(list);
        } else {
            int index_1 = 0, index_2 = 0;
            while (index_2 < list.size()) {
                List<CouponUser> value = null;
                index_2 = index_2 + n;
                if (index_2 >= list.size()) {
                    index_2 = list.size();
                }
                value = list.subList(index_1, index_2);
                index_1 = index_2;
                newList.add(value);
            }
        }
        return newList;
    }
}
