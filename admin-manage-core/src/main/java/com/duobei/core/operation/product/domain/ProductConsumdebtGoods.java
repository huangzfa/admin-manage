package com.duobei.core.operation.product.domain;

import java.io.Serializable;
import java.util.Date;

public class ProductConsumdebtGoods implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 所属产品id
     */
    private Integer productId;

    /**
     * 商品Id
     */
    private Integer goodsId;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 
     */
    private Integer addOperatorId;

    /**
     * 
     */
    private Integer modifyOperatorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_product_consumdebt_goods
     *
     * @mbg.generated Thu Mar 07 17:38:03 CST 2019
     */
    private static final long serialVersionUID = -6149365159489660766L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getAddOperatorId() {
        return addOperatorId;
    }

    public void setAddOperatorId(Integer addOperatorId) {
        this.addOperatorId = addOperatorId;
    }

    public Integer getModifyOperatorId() {
        return modifyOperatorId;
    }

    public void setModifyOperatorId(Integer modifyOperatorId) {
        this.modifyOperatorId = modifyOperatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_product_consumdebt_goods
     *
     * @mbg.generated Thu Mar 07 17:38:03 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", addTime=").append(addTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", addOperatorId=").append(addOperatorId);
        sb.append(", modifyOperatorId=").append(modifyOperatorId);
        sb.append("]");
        return sb.toString();
    }
}