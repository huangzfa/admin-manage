package com.duobei.core.operation.consume.domain.vo;

import com.duobei.core.operation.consume.domain.ConsumeLoanConfig;

import java.math.BigDecimal;

/**
 * @author litianxiong
 * @description
 * @date 2019/4/4
 */
public class BorrowShowConfigVo extends ConsumeLoanConfig {
    private Integer day1;
    private Integer day2;
    private Integer day3;
    private BigDecimal saveMinAmount;
    private BigDecimal saveMaxAmount;

    public Integer getDay1() {
        return day1;
    }

    public void setDay1(Integer day1) {
        this.day1 = day1;
    }

    public Integer getDay2() {
        return day2;
    }

    public void setDay2(Integer day2) {
        this.day2 = day2;
    }

    public Integer getDay3() {
        return day3;
    }

    public void setDay3(Integer day3) {
        this.day3 = day3;
    }

    public BigDecimal getSaveMinAmount() {
        return saveMinAmount;
    }

    public void setSaveMinAmount(BigDecimal saveMinAmount) {
        this.saveMinAmount = saveMinAmount;
    }

    public BigDecimal getSaveMaxAmount() {
        return saveMaxAmount;
    }

    public void setSaveMaxAmount(BigDecimal saveMaxAmount) {
        this.saveMaxAmount = saveMaxAmount;
    }
}
