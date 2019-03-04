package com.duobei.core.operation.consume.dao.mapper;

import com.duobei.core.operation.consume.domain.ConsumdebtGoodsPic;
import com.duobei.core.operation.consume.domain.ConsumdebtGoodsPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConsumdebtGoodsPicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_pic
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    long countByExample(ConsumdebtGoodsPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_pic
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int deleteByExample(ConsumdebtGoodsPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_pic
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_pic
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int insert(ConsumdebtGoodsPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_pic
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int insertSelective(ConsumdebtGoodsPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_pic
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    List<ConsumdebtGoodsPic> selectByExample(ConsumdebtGoodsPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_pic
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    ConsumdebtGoodsPic selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_pic
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int updateByExampleSelective(@Param("record") ConsumdebtGoodsPic record, @Param("example") ConsumdebtGoodsPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_pic
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int updateByExample(@Param("record") ConsumdebtGoodsPic record, @Param("example") ConsumdebtGoodsPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_pic
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int updateByPrimaryKeySelective(ConsumdebtGoodsPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yy_consumdebt_goods_pic
     *
     * @mbg.generated Sat Mar 02 14:39:00 CST 2019
     */
    int updateByPrimaryKey(ConsumdebtGoodsPic record);
}