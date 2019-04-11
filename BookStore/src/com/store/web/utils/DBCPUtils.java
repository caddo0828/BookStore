package com.store.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * 数据库操作连接
 * @author 老腰
 */
public class DBCPUtils {
	//创建资源对象
	private static DataSource ds = null;
	
	//将读取资源时放在静态代码块中，直接随着类的加载而加载，直接读取信息
	static {
		Properties prop = new Properties();
		InputStream in = DBCPUtils.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			prop.load(in);
			try {
				ds = BasicDataSourceFactory.createDataSource(prop);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取连接对象
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void releaseConnection(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
