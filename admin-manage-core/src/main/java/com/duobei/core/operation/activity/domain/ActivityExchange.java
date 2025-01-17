package com.duobei.core.operation.activity.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityExchange implements Serializable {
    /**
     * 
     */
    private Integer actId;

    /**
     * 
     */
    private String timeAxis;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 添加人id
     */
    private Integer addOperatorId;

    /**
     * 修改人id
     */
    private Integer modifyOperatorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yy_activity_exchange
     *
     * @mbg.generated Fri Apr 12 10:45:05 CST 2019
     */
    private static final long serialVersionUID = 3764849351998888139L;


}