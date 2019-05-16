package com.duobei.core.operation.product.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.http.OkHttpUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.product.dao.MerchantDao;
import com.duobei.core.operation.product.dao.ProductDao;
import com.duobei.core.operation.product.dao.ServiceDao;
import com.duobei.core.operation.product.domain.Merchant;
import com.duobei.core.operation.product.domain.Product;
import com.duobei.core.operation.product.domain.criteria.MerchantCriteria;
import com.duobei.core.operation.product.domain.criteria.ProductCriteria;
import com.duobei.core.operation.product.domain.vo.ProductVo;
import com.duobei.core.operation.product.domain.vo.ServiceVo;
import com.duobei.core.operation.product.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/2/27
 */
@Slf4j
@Service("MerchantService")
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantDao merchantDao;
    @Autowired
    private ServiceDao serviceDao;
    @Autowired
    private ProductDao productDao;

    private static ExecutorService service =new ThreadPoolExecutor(0,Runtime.getRuntime().availableProcessors() + 1,60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());
    /**
     * 分页查询
     * @return
     */
    @Override
    public ListVo<Merchant> getPageList(MerchantCriteria criteria){
        int total = merchantDao.countByCriteria(criteria);
        List<Merchant> list = null;
        if (total > BizConstant.INT_ZERO) {
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
        if( merchantDao.update(merchant) <BizConstant.INT_ONE){
            throw new TqException("修改失败");
        }
        noticeByMerchant(merchant);
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
            Integer nextNo = Integer.parseInt(one.getMerchantNo())+BizConstant.INT_ONE;
            merchant.setMerchantNo(nextNo+"");
        }
        if( merchantDao.save(merchant) <BizConstant.INT_ONE){
            throw new TqException("添加失败");
        }
        noticeByMerchant(merchant);
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

    /**
     * 根据产品推送
     */
    @Override
    public  void noticeByProduct(Product product) throws TqException{
        Merchant merchant = merchantDao.getById(product.getMerchantId());
        if( merchant == null ){
            return;
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("merchant_id",merchant.getId());
        map.put("merchant_no",merchant.getMerchantNo());
        map.put("merchant_name",merchant.getMerchantName());
        map.put("merchant_state",merchant.getState());
        List<JSONObject> products = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("product_id",product.getId());
        object.put("product_code",product.getProductCode());
        object.put("product_state",product.getState());
        object.put("product_name",product.getProductName());
        products.add(object);
        map.put("product_list",products);
        push(map);
    }

    /**
     * 根据商户来推
     * @param merchant
     */
    @Override
    public void noticeByMerchant(Merchant merchant) throws TqException{
        HashMap<String,Object> map = new HashMap<>();
        map.put("merchant_id",merchant.getId());
        map.put("merchant_no",merchant.getMerchantNo());
        map.put("merchant_name",merchant.getMerchantName());
        map.put("merchant_state",merchant.getState());
        ProductCriteria criteria = new ProductCriteria();
        criteria.setPagesize(100);
        List<ProductVo> list = productDao.getPageList(criteria);
        List<JSONObject> products = new ArrayList<>();
        for(ProductVo product : list){
            JSONObject object = new JSONObject();
            object.put("product_id",product.getId());
            object.put("product_code",product.getProductCode());
            object.put("product_state",product.getState());
            object.put("product_name",product.getProductName());
            products.add(object);
        }
        map.put("product_list",products);
        push(map);
    }

    /**
     *
     * @param map
     */
    public void push(HashMap<String,Object> map) throws TqException{

        List<ServiceVo> list = serviceDao.getAll();
        for(ServiceVo entity:list){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    String result = OkHttpUtil.okHttpPostJson(entity.getMerchantDatasynUrl(),JSONObject.toJSONString(map));
                    log.info(entity.getServiceName()+"推送结果："+result);
                }
            };
            try {
                service.execute(runnable);
            }catch (RejectedExecutionException e){
                log.error("请勿频繁操作",e);
                throw new TqException("请勿频繁操作");
            }
        }
    }
}
