<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- ${tableName}表:${functionName}模块 -->
<mapper namespace="${packageName}.dao.${ClassName}Dao">

    <!--基本的sql查询字段 公共引用...-->
    <sql id="queryFields">
        <#list list as column>${column.typeName}<#if column_index+1 != listSize>,</#if></#list>
    </sql>

    <insert id="saveRecord" parameterType="${packageName}.domain.${ClassName}" keyProperty="id">
        <selectKey resultType="java.lang.Long" keyProperty="id" order="AFTER" >
            SELECT LAST_INSERT_ID()
        </selectKey>

        INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
        <#list list as column>
	        <#if column.columnName == "isDelete">
            <#elseif column.columnName != "id">
	        <if test="${column.columnName} != null">
                ${column.typeName},
            </if>
            </#if>
        </#list>
        </trim>

        <trim prefix="values (" suffix=")" suffixOverrides="," >
      	<#list list as column>
	        <#if column.columnName == "isDelete">
            <#elseif column.columnName != "id">
		    <if test="${column.columnName} != null" >
                #${leftBraces}${column.columnName},jdbcType=${column.jdbcType}${rightBraces},
            </if>
            </#if >
        </#list>
        </trim>
    </insert>

    <update id="updateById"  parameterType="${packageName}.domain.${ClassName}">
        UPDATE ${tableName}
        <set>
            <#list list as column>
            <#if column.columnName == "id">
            <#elseif column.columnName == "isDelete">
            <#else >
            <if test="${column.columnName} != null  <#if column.jdbcType == "VARCHAR"> and ${column.columnName} != ''  </#if> ">
                ${column.typeName} = #${leftBraces}${column.columnName}<#if column.jdbcType == "" >,jdbcType=${column.jdbcType}</#if>${rightBraces}<#if column_index+1 != listSize>,<#else></#if>

            </if>
            </#if >
            </#list>
        </set>
        WHERE is_delete = 0 AND id = #${leftBraces}id ,jdbcType=BIGINT${rightBraces}
    </update>

</mapper>
