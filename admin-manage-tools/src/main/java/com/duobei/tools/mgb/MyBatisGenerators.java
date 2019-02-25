package com.duobei.tools.mgb;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * java类简单作用描述
 *
 * @ProjectName: tianqing-console
 * @Package: com.pgy.tianqing.tools.mgb
 * @ClassName: MyBatisGenerators.java
 * @Description: 反向生成mechant表
 * @Author: Fu·Hao
 * @CreateDate: 2018/11/13 11:15
 * @UpdateUser: Fu·Hao
 * @UpdateDate: 2018/11/13 11:15
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class MyBatisGenerators {
    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String path = MyBatisGenerators.class.getResource("/").getPath()+"../../";
        File configFile = new File(path+"mybatis/dao/mechant.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        System.out.println("====================【MyBatis Generator反向生成完成】=======================");
        System.out.println("====================【MyBatis Generator Reverse generation complete】=======================");
    }
}
