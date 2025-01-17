package com.duobei.tools.dic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

	private Connection conn;

	public Connection getConnection() throws SQLException {

		if (null != conn && conn.isValid(1000)) {
			return conn;
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://47.99.143.121/pgy_platform_manage?useUnicode=true&amp;characterEncoding=utf-8";

		conn = DriverManager.getConnection(url, "root", "Hjn184736351");

		return conn;

	}

	public List<DictVo> selectTopCode() throws SQLException {

		String sql = "SELECT a.pid ,a.dic_code,a.dic_var,a.dic_val,a.des ,b.dic_code AS pDic_code ,b.dic_val AS pVal,a.data_type AS dataType  FROM sys_dict a  LEFT JOIN sys_dict b ON  a.pid =  b.id ORDER BY a.pid,a.dic_sort";

		Connection conn = getConnection();
		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery(sql);

		List<DictVo> list = new ArrayList<DictVo>();

		while (rs.next()) {
			DictVo dictVo = new DictVo();
			dictVo.setDicCode(rs.getString("dic_code"));
			dictVo.setDicVal(rs.getString("dic_val"));
			dictVo.setDicVar(rs.getString("dic_var"));
			dictVo.setPid(rs.getInt("pid"));
			dictVo.setDes(rs.getString("des"));
			dictVo.setpVal(rs.getString("pVal"));
			dictVo.setpDicCode(rs.getString("pDic_code"));
			dictVo.setDataType(rs.getString("dataType"));
			list.add(dictVo);
		}

		return list;
	}

}
