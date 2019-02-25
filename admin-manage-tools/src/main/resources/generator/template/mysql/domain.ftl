package ${packageName}.domain;

import java.util.Date;
import java.math.BigDecimal;

/**
 * ${functionName}实体
 * 
 * @author ${classAuthor}
 * @version 1.0.0 初始化
 * @date ${classDate}
 * Copyright 本内容仅限于杭州蒲公英数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
 public class ${ClassName} implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    <#list list as item>
    <#if item.columnName == "id">
    /**
     * 主键Id
     */
    private Long id;
    
    <#elseif item.columnName == "isDelete">

    <#else >
    /**
     * ${item.columnComment}
     */
    private <#if "${item.dataType}" == "Double">BigDecimal<#else> ${item.dataType} </#if> ${item.columnName};

    </#if >
    </#list>

    <#list list as item>
    <#if item.columnName == "id">
    /**
     * 获取主键Id
     *
     * @return id
     */
    public Long getId(){
      return id;
    }

    /**
     * 设置主键Id
     * 
     * @param id 要设置的主键Id
     */
    public void setId(Long id){
      this.id = id;
    }
    
    <#elseif item.columnName == "isDelete">

    <#else >
    /**
     * 获取${item.columnComment}
     *
     * @return ${item.columnComment}
     */
    public <#if "${item.dataType}" == "Double">BigDecimal<#else> ${item.dataType} </#if> get${item.columnNameUpper}(){
      return ${item.columnName};
    }

    /**
     * 设置${item.columnComment}
     * 
     * @param ${item.columnName} 要设置的${item.columnComment}
     */
    public void set${item.columnNameUpper}(<#if "${item.dataType}" == "Double">BigDecimal<#else> ${item.dataType} </#if> ${item.columnName}){
      this.${item.columnName} = ${item.columnName};
    }

    </#if >
    </#list>
}