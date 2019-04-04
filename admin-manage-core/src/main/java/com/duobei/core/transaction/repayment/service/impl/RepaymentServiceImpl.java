package com.duobei.core.transaction.repayment.service.impl;

import com.duobei.common.exception.TqException;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.transaction.borrow.dao.BorrowCashDao;
import com.duobei.core.transaction.borrow.domain.BorrowCash;
import com.duobei.core.transaction.repayment.dao.BorrowCashRepaymentDao;
import com.duobei.core.transaction.repayment.dao.mapper.BorrowCashRepaymentMapper;
import com.duobei.core.transaction.repayment.domain.BorrowCashRepayment;
import com.duobei.core.transaction.repayment.domain.BorrowCashRepaymentExample;
import com.duobei.core.transaction.repayment.domain.criteria.RepaymentCriteria;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentListVo;
import com.duobei.core.transaction.repayment.domain.vo.BorrowCashRepaymentVo;
import com.duobei.core.transaction.repayment.service.RepaymentService;
import com.duobei.core.user.user.dao.UserDao;
import com.duobei.core.user.user.domain.vo.UserAndIdCardVo;
import com.pgy.data.handler.PgyDataHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author litianxiong
 * @description
 * @date 2019/3/13
 */
@Service("repaymentService")
public class RepaymentServiceImpl implements RepaymentService {
    @Resource
    BorrowCashRepaymentDao repaymentDao;

    @Resource
    BorrowCashRepaymentMapper repaymentMapper;

    @Resource
    BorrowCashDao borrowCashDao;

    @Resource
    UserDao userDao;

    @Resource
    ProductDao productDao;

    @Override
    public ListVo<BorrowCashRepaymentListVo> getListByQuery(RepaymentCriteria repaymentCriteria) {
        BorrowCashRepaymentExample example = new BorrowCashRepaymentExample();
        BorrowCashRepaymentExample.Criteria criteria = example.createCriteria();
        //赋值基础查询条件
        criteria.andIsDeleteEqualTo(0L);
        criteria.andProductIdEqualTo(repaymentCriteria.getProductId());
        if (StringUtil.isNotEmpty(repaymentCriteria.getRepayNo())){
            criteria.andRepayNoEqualTo(repaymentCriteria.getRepayNo());
        }
        //查询产品信息
        Product product = productDao.getById(repaymentCriteria.getProductId());
        Long total = 0L;
        List<BorrowCashRepaymentListVo> data = null;
        if (StringUtil.isNotEmpty(repaymentCriteria.getBorrowNo()) && StringUtil.isEmpty(repaymentCriteria.getUserName())){
            //借款表查询条件存在，用户表查询条件不存在
            //查询借款表
            List<Long>  borrowIds = borrowCashDao.getIdByBorrowNo(repaymentCriteria.getBorrowNo());
            if (borrowIds != null && borrowIds.size() > 0 ){
                criteria.andBorrowCashIdIn(borrowIds);
                total = repaymentMapper.countByExample(example);
                if (total > 0) {
                    repaymentCriteria.setBorrowIds(borrowIds);
                    //查询还款列表
                    data = repaymentDao.getListVoByQuery(repaymentCriteria);
                    //查询用户
                    Map<Long,UserAndIdCardVo> userMap = userDao.getUserAndIdCardMapByRepaymentUserIds(data);
                    //赋值借款编号、用户姓名（数据处理）、手机号（数据处理）、产品名称
                    for (BorrowCashRepaymentListVo vo : data){
                        UserAndIdCardVo userInfo = userMap.get(vo.getUserId());
                        vo.setBorrowNo(repaymentCriteria.getBorrowNo());
                        if (userInfo != null){
                            vo.setRealName(PgyDataHandler.decrypt(userInfo.getRealNameEncrypt()));
                            vo.setUserName(PgyDataHandler.decrypt(userInfo.getUserNameEncrypt()));
                        }
                        vo.setProductName(product.getProductName());
                    }
                }
            }
        }else if (StringUtil.isNotEmpty(repaymentCriteria.getUserName()) && StringUtil.isEmpty(repaymentCriteria.getBorrowNo())){
            //用户表查询条件存在，借款表查询条件不存在
            UserAndIdCardVo userInfo = userDao.getUserByMobileAndProductId(PgyDataHandler.md5(repaymentCriteria.getUserName()),repaymentCriteria.getProductId());
            if (userInfo != null){
                criteria.andUserIdEqualTo(userInfo.getId());
                total = repaymentMapper.countByExample(example);
                if (total > 0){
                    //查询还款列表
                    data = repaymentDao.getListVoByQuery(repaymentCriteria);
                    //查询借款信息
                    Map<Long,BorrowCash> borrowCashMap = borrowCashDao.getBorrowCashMapByRepaymentBorrowIds(data);
                    //赋值借款编号、用户姓名（数据处理）、手机号（数据处理）、产品名称
                    for (BorrowCashRepaymentListVo vo : data){
                        BorrowCash borrowCash = borrowCashMap.get(vo.getBorrowCashId());
                        if (borrowCash != null) {
                            vo.setBorrowNo(borrowCash.getBorrowNo());
                        }
                        vo.setRealName(PgyDataHandler.decrypt(userInfo.getRealNameEncrypt()));
                        vo.setUserName(PgyDataHandler.decrypt(userInfo.getUserNameEncrypt()));
                        vo.setProductName(product.getProductName());
                    }
                }

            }
        }else if (StringUtil.isNotEmpty(repaymentCriteria.getUserName()) && StringUtil.isNotEmpty(repaymentCriteria.getBorrowNo())){
            //三表查询
            //优先查询用户表
            UserAndIdCardVo userInfo = userDao.getUserByMobileAndProductId(PgyDataHandler.md5(repaymentCriteria.getUserName()),repaymentCriteria.getProductId());
            //查询借款表
            if (userInfo != null ){
                List<Long>  borrowIds = borrowCashDao.getIdByBorrowNoAndUserId(repaymentCriteria.getBorrowNo(),userInfo.getId());
                if ( borrowIds != null && borrowIds.size() > 0){
                    criteria.andBorrowCashIdIn(borrowIds);
                    criteria.andUserIdEqualTo(userInfo.getId());
                    total = repaymentMapper.countByExample(example);
                    if (total > 0){
                        //查询还款列表
                        data = repaymentDao.getListVoByQuery(repaymentCriteria);
                        //赋值借款编号、用户姓名（数据处理）、手机号（数据处理）、产品名称
                        for (BorrowCashRepaymentListVo vo : data){
                            vo.setBorrowNo(repaymentCriteria.getBorrowNo());
                            vo.setRealName(PgyDataHandler.decrypt(userInfo.getRealNameEncrypt()));
                            vo.setUserName(PgyDataHandler.decrypt(userInfo.getUserNameEncrypt()));
                            vo.setProductName(product.getProductName());
                        }
                    }
                }
            }
        }else{
            //不存在其他表条件查询时
            total = repaymentMapper.countByExample(example);
            if (total > 0) {
                //查询还款列表
                data = repaymentDao.getListVoByQuery(repaymentCriteria);
                //查询用户
                Map<Long, UserAndIdCardVo> userMap = userDao.getUserAndIdCardMapByRepaymentUserIds(data);
                //查询借款信息
                Map<Long, BorrowCash> borrowCashMap = borrowCashDao.getBorrowCashMapByRepaymentBorrowIds(data);
                //赋值借款编号、用户姓名（数据处理）、手机号（数据处理）、产品名称
                for (BorrowCashRepaymentListVo vo : data) {
                    UserAndIdCardVo userInfo = userMap.get(vo.getUserId());
                    BorrowCash borrowCash = borrowCashMap.get(vo.getBorrowCashId());
                    if (borrowCash != null) {
                        vo.setBorrowNo(borrowCash.getBorrowNo());
                    }
                    if (userInfo != null){
                        vo.setRealName(PgyDataHandler.decrypt(userInfo.getRealNameEncrypt()));
                        vo.setUserName(PgyDataHandler.decrypt(userInfo.getUserNameEncrypt()));
                    }
                    vo.setProductName(product.getProductName());
                }
            }
        }
        return new ListVo(total.intValue(),data);
    }


    @Override
    public BorrowCashRepaymentVo getById(Long id){
        BorrowCashRepaymentVo vo = new BorrowCashRepaymentVo();
        //查询还款信息
        BorrowCashRepayment borrowCashRepayment = repaymentDao.getById(id);
        if (borrowCashRepayment != null){
            //查询借款信息
            BorrowCash borrowCash = borrowCashDao.getById(borrowCashRepayment.getBorrowCashId());
            //赋值
            BeanUtils.copyProperties(borrowCashRepayment,vo) ;
            vo.setBorrowCash(borrowCash);

        }
        return vo;
    }
}
