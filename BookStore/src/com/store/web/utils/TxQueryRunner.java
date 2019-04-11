package com.store.web.utils;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
/**
 * 简化jdbc数据库连接池的查询的辅助类
 * @author 老腰
 */
public class TxQueryRunner extends QueryRunner {
	
	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		Connection con = DBCPUtils.getConnection();
		int[] result = super.batch(con, sql, params);
		DBCPUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		Connection con = DBCPUtils.getConnection();
		T result = super.query(con, sql, rsh, params);
		DBCPUtils.releaseConnection(con);
		return result;
	}
	
	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = DBCPUtils.getConnection();
		T result = super.query(con, sql, rsh);
		DBCPUtils.releaseConnection(con);
		return result;
	}
	

	@Override
	public int update(String sql) throws SQLException {
		Connection con = DBCPUtils.getConnection();
		int result = super.update(con, sql);
		DBCPUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection con = DBCPUtils.getConnection();
		int result = super.update(con, sql, param);
		DBCPUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection con = DBCPUtils.getConnection();
		int result = super.update(con, sql, params);
		DBCPUtils.releaseConnection(con);
		return result;
	}
}
