package com.duobei.tools.mgb.onlyone;


public class Create {
	public static void main(String[] args) {
		Create ot=new Create();
		ot.test();
	}
	
	public void test(){

		// 数据库连接信息
		String url = "jdbc:mysql://116.62.143.57:3306/duobei?useUnicode=true&characterEncoding=utf8";
		String MysqlUser = "root";
		String mysqlPassword = "Hjn184736351";
		
		// 数据库及数据表名称
		String database = "duobei";
		String table = "pgy_repayment_offline_upload";
		String tableClass = "repaymentOfflineUpload";

		// 配置作者及Domain说明
		String classAuthor = "ritchey";
		String functionName = "";
		
		// 公共包路径 (例如 BaseDao、 BaseService、 BaseServiceImpl)
		String commonName ="";

		// 如果要创建文件  必须注意包名 一定要填写正确
		String packageName ="com.duobei.core.repayment";

		String moduleName = "";

		//Mapper文件存储地址  默认在resources中
		String batisName = "config/mapping";
		String db="mysql";
		
		try {
			String classNamePrefix = getClassName(tableClass);
			MybatisGenerate.generateCode(db,url, MysqlUser, mysqlPassword, database, table,commonName,packageName,batisName,moduleName,classAuthor,functionName,classNamePrefix);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getClassName(String tableName) {
	    String beanName = tableName.substring(0,1).toUpperCase() + tableName.substring(1);
	    while(beanName.indexOf("_") >=0){
	        int firstSpe = beanName.indexOf("_");
	        beanName = beanName.substring(0,firstSpe) + beanName.substring(firstSpe+1,firstSpe+2).toUpperCase() + beanName.substring(firstSpe+2);
	    }
		return beanName;
	}
	

}
