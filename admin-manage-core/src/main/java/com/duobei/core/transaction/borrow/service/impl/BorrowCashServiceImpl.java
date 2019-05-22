package com.duobei.core.transaction.borrow.service.impl;

import com.alibaba.fastjson.JSON;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.http.OkHttpUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.config.GlobalConfig;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.manage.sys.utils.DictUtil;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.report.criteria.FinanceReportCriteria;
import com.duobei.core.transaction.borrow.dao.BorrowCashDao;
import com.duobei.core.transaction.borrow.dao.mapper.BorrowCashMapper;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.BorrowCashExample;
import com.duobei.core.transaction.borrow.domain.bo.OverdueDerateBo;
import com.duobei.core.transaction.borrow.domain.criteria.BorrowCashCriteria;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashListVo;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashReportVo;
import com.duobei.core.transaction.borrow.service.BorrowCashService;
import com.duobei.core.transaction.overdue.dao.OverdueDerateRecordDao;
import com.duobei.core.transaction.overdue.domain.OverdueDerateRecord;
import com.duobei.core.transaction.repayment.dao.RepaymentOfflineDao;
import com.duobei.core.user.user.dao.UserDao;
import com.duobei.core.user.user.domain.criteria.UserBorrowCriteria;
import com.duobei.core.user.user.domain.vo.UserAndIdCardVo;
import com.duobei.core.user.user.domain.vo.UserBorrowListVo;
import com.duobei.dic.ZD;
import com.duobei.utils.AmountUtil;
import com.pgy.data.handler.PgyDataHandler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/12
 */
@Service("borrowCashService")
@Slf4j
public class BorrowCashServiceImpl implements BorrowCashService {
    @Resource
    ProductDao productDao;
    @Resource
    BorrowCashDao borrowCashDao;
    @Resource
    BorrowCashMapper borrowCashMapper;
    @Resource
    UserDao userDao;
    @Resource
    OverdueDerateRecordDao overdueDerateRecordDao;


    @Override
    public ListVo<BorrowCashListVo> getListByQuery(BorrowCashCriteria borrowCashCriteria) {
        //查询产品信息
        Product product = productDao.getById(borrowCashCriteria.getProductId());
        //查询借款记录
        BorrowCashExample example = new BorrowCashExample();
        BorrowCashExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0L);
        criteria.andProductIdEqualTo(borrowCashCriteria.getProductId());
        if (StringUtil.isNotEmpty(borrowCashCriteria.getBorrowNo())){
            criteria.andBorrowNoEqualTo(borrowCashCriteria.getBorrowNo());
        }
        Long total = 0L;
        List<BorrowCashListVo> data = null;
        //判断是否存在用户手机号查询，如存在，则先进行用户查询
        if (StringUtil.isNotEmpty(borrowCashCriteria.getUserName())){
            //MD5手机号查询
            UserAndIdCardVo userData = userDao.getUserByMobileAndProductId(PgyDataHandler.md5(borrowCashCriteria.getUserName()),borrowCashCriteria.getProductId());
            if (userData != null){
                criteria.andUserIdEqualTo(userData.getId());
                total = borrowCashMapper.countByExample(example);
                if (total > 0){
                    //查询借款信息
                    borrowCashCriteria.setUserId(userData.getId());
                    data = borrowCashDao.getListVoByQuery(borrowCashCriteria);
                    for (BorrowCashListVo vo : data){
                        //赋值产品名称，解密用户手机号、用户姓名
                        vo.setProductName(product.getProductName());
                        vo.setUserName(PgyDataHandler.decrypt(userData.getUserNameEncrypt()));
                        vo.setRealName(PgyDataHandler.decrypt(userData.getRealNameEncrypt()));
                    }
                }
            }
        }else{
            //如果不存在用户表查询，则先查询借款表
            //查询借款列表
            total = borrowCashMapper.countByExample(example);
            if (total > 0){
                //查询借款信息
                data = borrowCashDao.getListVoByQuery(borrowCashCriteria);
                //查询用户map
                Map<Long,UserAndIdCardVo> userMap = userDao.getUserAndIdCardMapByBorrowUserIds(data);
                for (BorrowCashListVo vo : data){
                    //赋值产品名称，解密用户手机号、用户姓名
                    vo.setProductName(product.getProductName());
                    UserAndIdCardVo userData = userMap.get(vo.getUserId());
                    if (userData != null){
                        vo.setUserName(PgyDataHandler.decrypt(userData.getUserNameEncrypt()));
                        vo.setRealName(PgyDataHandler.decrypt(userData.getRealNameEncrypt()));
                    }



                }

            }
        }
        return new ListVo<BorrowCashListVo>(total.intValue(),data);
    }

    @Override
    public BorrowCash getById(Long borrowId) {
        return borrowCashDao.getById(borrowId);
    }

    @Override
    public ListVo<BorrowCashListVo> getOverdueListByQuery(BorrowCashCriteria borrowCashCriteria) {
        //查询产品信息
        Product product = productDao.getById(borrowCashCriteria.getProductId());
        //查询借款记录
        BorrowCashExample example = new BorrowCashExample();
        BorrowCashExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0L);
        criteria.andProductIdEqualTo(borrowCashCriteria.getProductId());
        //已逾期状态
        criteria.andCurrentOverdueStateEqualTo(1);
        //待还款
        criteria.andBorrowStateEqualTo(ZD.borrowState_waitRepay);
        if (StringUtil.isNotEmpty(borrowCashCriteria.getBorrowNo())){
            criteria.andBorrowNoEqualTo(borrowCashCriteria.getBorrowNo());
        }
        Long total = 0L;
        List<BorrowCashListVo> data = null;
        //判断是否存在用户手机号查询，如存在，则先进行用户查询
        if (StringUtil.isNotEmpty(borrowCashCriteria.getUserName())){
            //MD5手机号查询
            UserAndIdCardVo userData = userDao.getUserByMobileAndProductId(PgyDataHandler.md5(borrowCashCriteria.getUserName()),borrowCashCriteria.getProductId());
            if (userData != null){
                criteria.andUserIdEqualTo(userData.getId());
                total = borrowCashMapper.countByExample(example);
                if (total > 0){
                    //查询借款信息
                    borrowCashCriteria.setUserId(userData.getId());
                    data = borrowCashDao.getOverdueListVoByQuery(borrowCashCriteria);
                    for (BorrowCashListVo vo : data){
                        //赋值产品名称，解密用户手机号、用户姓名
                        vo.setProductName(product.getProductName());
                        vo.setUserName(PgyDataHandler.decrypt(userData.getUserNameEncrypt()));
                        vo.setRealName(PgyDataHandler.decrypt(userData.getRealNameEncrypt()));
                    }
                }
            }
        }else{
            //如果不存在用户表查询，则先查询借款表
            //查询借款列表
            total = borrowCashMapper.countByExample(example);
            if (total > 0){
                //查询借款信息
                data = borrowCashDao.getOverdueListVoByQuery(borrowCashCriteria);
                //查询用户map
                Map<Long,UserAndIdCardVo> userMap = userDao.getUserAndIdCardMapByBorrowUserIds(data);
                for (BorrowCashListVo vo : data){
                    UserAndIdCardVo userData = userMap.get(vo.getUserId());
                    if (userData != null){
                        vo.setUserName(PgyDataHandler.decrypt(userData.getUserNameEncrypt()));
                        vo.setRealName(PgyDataHandler.decrypt(userData.getRealNameEncrypt()));
                    }
                    //赋值产品名称，解密用户手机号、用户姓名
                    vo.setProductName(product.getProductName());
                }

            }
        }
        return new ListVo<BorrowCashListVo>(total.intValue(),data);
    }

    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void overdueDerate(BorrowCash borrowCash, BigDecimal derateAmount, OperatorCredential credential) throws TqException {
        //逾期减免
        Long derateAmountLongVal = derateAmount.multiply(new BigDecimal(100)).longValue();
        borrowCash.setDerateOverdue(borrowCash.getDerateOverdue() + derateAmountLongVal);
        borrowCash.setModifyTime(new Date());
        int count  =  borrowCashDao.overdueAmountDerate(borrowCash);
        if (count == 1) {
            //生成减免记录
            OverdueDerateRecord overdueDerateRecord = new OverdueDerateRecord();
            overdueDerateRecord.setAddOperatorId(credential.getOpId());
            overdueDerateRecord.setAddOperatorName(credential.getRealName());
            overdueDerateRecord.setAddTime(new Date());
            overdueDerateRecord.setProductId(borrowCash.getProductId());
            overdueDerateRecord.setUserId(borrowCash.getUserId());
            overdueDerateRecord.setBorrowCashId(borrowCash.getId());
            //减免费用
            overdueDerateRecord.setDerateAmount(derateAmountLongVal);
            //实际逾期费
            overdueDerateRecord.setRealOverdueAmount(borrowCash.getRealOverdueAmount());
            //当前待还逾期费记录 当期逾期费  - 累计减免逾期费（本次减免已加上） - 累计已还逾期费
            overdueDerateRecord.setOverdueAmount(borrowCash.getOverdueAmount() - borrowCash.getDerateOverdue() - borrowCash.getSumOverdueAmount());
            //累计已还逾期费
            overdueDerateRecord.setSumOverdueAmount(borrowCash.getSumOverdueAmount());
            //当前待还金额 申请金额 - 已还金额 + 待还逾期费
            overdueDerateRecord.setUnpaidAmount(borrowCash.getAmount() - borrowCash.getRepayAmount() - overdueDerateRecord.getOverdueAmount());
            //生成记录
            overdueDerateRecordDao.save(overdueDerateRecord);
            //TODO 同步催收

            OverdueDerateBo bo = new OverdueDerateBo()
                    .setBorrowNo(borrowCash.getBorrowNo())
                    .setDerateAmount(derateAmountLongVal)
                    .setProductId(borrowCash.getProductId())
                    .setMerchantId(1)//随便写
                    .setSign("1231")//随便写
                    .setTimestamp(System.currentTimeMillis()+"");
            String result = OkHttpUtil.okHttpPostJson(GlobalConfig.getCollectionUrl()+"/biz/sync/overdue/derate", JSON.toJSONString(bo));
            log.info("同步催收:"+ result);

        }else{
            throw new TqException("逾期费减免失败");
        }
    }

    @Override
    public List<BorrowCashReportVo> getReportList(FinanceReportCriteria criteria) {

        List<BorrowCashReportVo> data = borrowCashDao.getReportList(criteria);
        //获取其他数据，处理金额信息
        for (BorrowCashReportVo borrowCashListVo : data){
            //所属产品
            borrowCashListVo.setProductName(criteria.getProduct().getProductName());
            //借款状态信息
            borrowCashListVo.setBorrowStateName(DictUtil.getDictLabel(ZD.borrowState,borrowCashListVo.getBorrowState().toString()));
            //风控状态信息
            borrowCashListVo.setRiskStateName(DictUtil.getDictLabel(ZD.riskState,borrowCashListVo.getRiskState().toString()));
            //待还逾期费 = 逾期费 - 已还逾期费 - 减免金额
            Long waitOverdueAmount = borrowCashListVo.getOverdueAmount() - borrowCashListVo.getSumOverdueAmount() - borrowCashListVo.getDerateOverdue();
            borrowCashListVo.setWaitOverdueAmountDeciaml(AmountUtil.getBigDecimal(waitOverdueAmount));
            //剩余待还金额 = 申请金额 + 利息 （手续费-消费金额） + 手续费 + 逾期费 -减免金额 - 已还金额
            Long waitAmount = borrowCashListVo.getAmount() + borrowCashListVo.getPoundage()  - borrowCashListVo.getConsumeAmount() + borrowCashListVo.getOverdueAmount() - borrowCashListVo.getDerateOverdue() - borrowCashListVo.getRepayAmount();
            borrowCashListVo.setWaitAmountDeciaml(AmountUtil.getBigDecimal(waitAmount));
            //金额数据转换
            borrowCashListVo.setAmountDeciaml(AmountUtil.getBigDecimal(borrowCashListVo.getAmount()));
            borrowCashListVo.setActivityAmountDeciaml(AmountUtil.getBigDecimal(borrowCashListVo.getActivityAmount()));
            borrowCashListVo.setArrivalAmountDeciaml(AmountUtil.getBigDecimal(borrowCashListVo.getArrivalAmount()));
            borrowCashListVo.setPoundageDeciaml(AmountUtil.getBigDecimal(borrowCashListVo.getPoundage()));
            borrowCashListVo.setRepayAmountDeciaml(AmountUtil.getBigDecimal(borrowCashListVo.getRepayAmount()));
            borrowCashListVo.setSumOverdueAmountDeciaml(AmountUtil.getBigDecimal(borrowCashListVo.getSumOverdueAmount()));
        }
        return data;
    }

    @Override
    public ListVo<UserBorrowListVo> getStageBorrowByUserIdAndState(UserBorrowCriteria criteria) {
        BorrowCashExample example = new BorrowCashExample();
        BorrowCashExample.Criteria criteria1 = example.createCriteria();
        criteria1.andIsDeleteEqualTo(0L);
        if (criteria.getUserId() != null){
            criteria1.andUserIdEqualTo(criteria.getUserId());
        }
        if (criteria.getBorrowState() != null && criteria.getBorrowState().size() > 0){
            criteria1.andBorrowStateIn(criteria.getBorrowState());
        }
        Long total = borrowCashMapper.countByExample(example);
        List<UserBorrowListVo> data = null;
        if(total >0 ){
            data = borrowCashDao.getStageBorrowByUserIdAndState(criteria);
            for (UserBorrowListVo borrowListVo : data){
                borrowListVo.setBorrowStateName(DictUtil.getDictLabel(ZD.borrowState,borrowListVo.getBorrowState().toString()));
                borrowListVo.setRiskStateName(DictUtil.getDictLabel(ZD.riskState,borrowListVo.getRiskState().toString()));
                //待还逾期费 = 逾期费 - 已还逾期费 - 减免金额
                Long waitOverdueAmount = borrowListVo.getOverdueAmount() - borrowListVo.getSumOverdueAmount() - borrowListVo.getDerateOverdue();
                borrowListVo.setWaitOverdueAmount(AmountUtil.getBigDecimal(waitOverdueAmount));
                //剩余待还金额 = 申请金额 + 利息 （手续费-消费金额）  + 逾期费 -减免金额 - 已还金额
                Long waitAmount = borrowListVo.getAmount() + borrowListVo.getPoundage()  - borrowListVo.getConsumeAmount() + borrowListVo.getOverdueAmount() - borrowListVo.getDerateOverdue() - borrowListVo.getRepayAmount();
                borrowListVo.setWaitAmount(AmountUtil.getBigDecimal(waitAmount));
            }
        }

        return new ListVo(total.intValue(),data);
    }

    /**
     * 即将逾期订单
     * @param map
     * @return
     */
    @Override
    public List<BorrowCash> getBeginOverdue(HashMap<String,Object> map){
        return borrowCashDao.getBeginOverdue(map);
    }

}
