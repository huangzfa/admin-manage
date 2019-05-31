package com.duobei.core.operation.consumdebt.service.impl;

import com.duobei.common.constant.BizConstant;
import com.duobei.common.exception.TqException;
import com.duobei.common.util.GuidUtil;
import com.duobei.common.util.lang.StringUtil;
import com.duobei.common.vo.ListVo;
import com.duobei.core.operation.consumdebt.dao.ConsumdebtGoodsDao;
import com.duobei.core.operation.consumdebt.domain.ConsumdebtGoods;
import com.duobei.core.operation.consumdebt.domain.criteria.ConsumdebtGoodsCriteria;
import com.duobei.core.operation.consumdebt.domain.vo.ConsumdebtGoodsVo;
import com.duobei.core.operation.consumdebt.service.ConsumdebtGoodsPicService;
import com.duobei.core.operation.consumdebt.service.ConsumdebtGoodsService;
import com.duobei.core.operation.product.dao.ProductConsumdebtGoodsDao;
import com.duobei.core.operation.product.domain.vo.ProductConsumdebtGoodsVo;
import com.duobei.dic.ZD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/3/6
 */
@Service("ConsumdebtGoodsService")
public class ConsumdebtGoodsServiceImpl implements ConsumdebtGoodsService {
    @Autowired
    private ConsumdebtGoodsDao consumdebtGoodsDao;
    @Autowired
    private ConsumdebtGoodsPicService consumdebtGoodsPicService;
    @Autowired
    private ProductConsumdebtGoodsDao productConsumdebtGoodsDao;

    /**
     * 分页查询
     * @param criteria
     * @return
     */
    @Override
    public ListVo<ConsumdebtGoodsVo> getPageList(ConsumdebtGoodsCriteria criteria) {
        int total = consumdebtGoodsDao.countByCriteria(criteria);
        List<ConsumdebtGoodsVo> list = null;
        if (total > 0) {
            list = consumdebtGoodsDao.getPageList(criteria);
        }
        return new ListVo<ConsumdebtGoodsVo>(total, list);
    }

    /**
     *
     * @param entity
     * @throws TqException
     */
    @Override
    @Transactional(value = "springTransactionManager",rollbackFor = TqException.class)
    public void saveOrUpdate(ConsumdebtGoodsVo entity) throws TqException{
        //
        if( entity.getMinAmountDouble()> entity.getMaxAmountDouble()){
            throw new TqException("上限金额不得大于下限金额");
        }
        //金额存到分
        entity.setMinAmount(new Double(entity.getMinAmountDouble()*1000).longValue());
        entity.setMaxAmount(new Double(entity.getMaxAmountDouble()*1000).longValue());
        entity.setPriceAmount(new Double(entity.getPriceAmountDouble()*1000).longValue());
        entity.setSaleAmount(entity.getPriceAmount());
        if( entity.getId() == null ){
            entity.setGoodsNo(GuidUtil.getGoosNo());
            entity.setAddOperatorId(entity.getModifyOperatorId());
            if( consumdebtGoodsDao.save(entity) <1 ){
                throw new TqException("添加失败");
            }
        }else{
            if( consumdebtGoodsDao.update(entity) <1 ){
                throw new TqException("修改失败");
            }
            //先删除原来的图片
            consumdebtGoodsPicService.deleteByGoodsId(entity.getId());
        }

        if(!StringUtil.isBlank(entity.getBannerUrls())){
            consumdebtGoodsPicService.addPic(entity,entity.getBannerUrls(), ZD.goodsPicType_banner);
        }
        if(!StringUtil.isBlank(entity.getDetailUrls())){
            //保存新图片
            consumdebtGoodsPicService.addPic(entity,entity.getDetailUrls(), ZD.goodsPicType_detail);
        }

    }

    /**
     *
     * @param goodsNo
     * @return
     */
    @Override
    public ConsumdebtGoodsVo getByGoodsNo(String goodsNo){
        return consumdebtGoodsDao.getByGoodsNo(goodsNo);
    }

    /**
     *查询所有商品，并根据产品id，知道哪些商品被关联了产品中
     * @param productId
     * @return
     */
    @Override
    public List<ConsumdebtGoodsVo> getList(Integer productId){
        List<ConsumdebtGoodsVo> gList = consumdebtGoodsDao.getAll();
        List<ProductConsumdebtGoodsVo> pgList = productConsumdebtGoodsDao.getByProductId(productId);
        for( ConsumdebtGoodsVo vo : gList){
            for (ProductConsumdebtGoodsVo gvo :pgList){
                if( vo.getId().equals(gvo.getGoodsId())){
                    vo.setChecked("checked");
                    continue;
                }
            }
        }
        return gList;
    }

    /**
     *
     * @param entity
     */
    @Override
    public void delete(ConsumdebtGoodsVo entity) throws TqException{
        //若果是商品下架
        if( entity.getState().equals(BizConstant.INT_ZERO)){
            if( consumdebtGoodsDao.validCount() == BizConstant.INT_ONE){
                throw new TqException("至少有一个在售商品");
            }
        }
        if( consumdebtGoodsDao.update(entity) < 1){
            throw new TqException("删除失败");
        }
    }

    /**
     *
     * @param entity
     */
    @Override
    public void editState(ConsumdebtGoodsVo entity) throws TqException{
        //若果是商品下架
        if( entity.getState().equals(BizConstant.INT_ZERO)){
            if( consumdebtGoodsDao.validCount() == BizConstant.INT_ONE){
                throw new TqException("至少有一个在售商品");
            }
        }
        if( consumdebtGoodsDao.update(entity) < 1){
            throw new TqException("操作失败");
        }
    }
}
