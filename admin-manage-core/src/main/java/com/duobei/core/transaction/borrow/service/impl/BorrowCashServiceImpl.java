package com.duobei.core.transaction.borrow.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.manage.auth.domain.credential.OperatorCredential;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.transaction.borrow.dao.BorrowCashDao;
import com.duobei.core.transaction.borrow.dao.mapper.BorrowCashMapper;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.borrow.domain.BorrowCashExample;
import com.duobei.core.transaction.borrow.domain.criteria.BorrowCashCriteria;
import com.duobei.core.transaction.borrow.domain.vo.BorrowCashListVo;
import com.duobei.core.transaction.borrow.service.BorrowCashService;
import com.duobei.core.transaction.overdue.dao.OverdueDerateRecordDao;
import com.duobei.core.transaction.overdue.domain.OverdueDerateRecord;
import com.duobei.core.user.user.dao.UserDao;
import com.duobei.core.user.user.domain.vo.UserAndIdCardVo;
import com.duobei.dic.ZD;
import com.pgy.data.handler.PgyDataHandler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/12
 */
@Service("borrowCashService")
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
        }else{
            throw new TqException("逾期费减免失败");
        }
    }

}
