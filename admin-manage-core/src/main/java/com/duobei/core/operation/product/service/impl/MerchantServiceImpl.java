package com.duobei.core.operation.product.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.MerchantDao;
import com.duobei.core.operation.product.domain.Merchant;
import com.duobei.core.operation.product.domain.criteria.MerchantCriteria;
import com.duobei.core.operation.product.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/27
 */
@Service("MerchantService")
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantDao merchantDao;
    /**
     * 分页查询
     * @return
     */
    @Override
    public ListVo<Merchant> getPageList(MerchantCriteria criteria){
        int total = merchantDao.countByCriteria(criteria);
        List<Merchant> list = null;
        if (total > 0) {
            list = merchantDao.getPageList(criteria);
        }
        return new ListVo<Merchant>(total, list);
    }

    /**
     * 查询所有有效记录
     * @return
     */
    @Override
    public List<Merchant> getAll(){
        return merchantDao.getAll();
    }

    /**
     * 修改商户
     * @param merchant
     */
    @Override
    public void update(Merchant merchant) throws TqException{
        if( merchantDao.update(merchant) <1){
            throw new TqException("修改失败");
        }
    }

    /**
     * 添加商户
     * @param merchant
     */
    @Override
    public void save(Merchant merchant) throws TqException{
        Merchant one = merchantDao.getLastOne();
        if( one == null ){
            merchant.setMerchantNo(BizConstant.MERCHANT_NO);
        }else{
            //商户号加1
            Integer nextNo = Integer.parseInt(one.getMerchantNo())+1;
            merchant.setMerchantNo(nextNo+"");
        }
        if( merchantDao.save(merchant) <1){
            throw new TqException("添加失败");
        }
    }

    /**
     * 根据商户编号查询
     * @param merchantNo
     * @return
     */
    @Override
    public  Merchant getByMerchantNo(String merchantNo){
        return merchantDao.getByMerchantNo(merchantNo);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Merchant getById(Integer id){
        return merchantDao.getById(id);
    }
}
