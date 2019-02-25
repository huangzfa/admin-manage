package ${packageName}.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ${packageName}.dao.${ClassName}Dao;
import ${packageName}.domain.${ClassName};
import ${packageName}.service.${ClassName}Service;



/**
 * ${functionName}ServiceImpl
 * 
 * @author ${classAuthor}
 * @version 1.0.0 初始化
 * @date ${classDate}
 * Copyright 本内容仅限于杭州蒲公英数据有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
 
@Service("${className}Service")
public class ${ClassName}ServiceImpl implements ${ClassName}Service {
	
    private static final Logger logger = LoggerFactory.getLogger(${ClassName}ServiceImpl.class);
   
    @Resource
    private ${ClassName}Dao ${className}Dao;

}