package com.offcn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class BaseDao {

	public static DataSource ds = new ComboPooledDataSource();

	// ������ȡ�������ݿ���󷽷�
	public static Connection getconnectiong() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// ���������󷽷�
	public static Statement getstatement(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}

	// ����prepareStatementԤ�������
	public static PreparedStatement getprepareStatement(Connection conn, String sql) {
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prep;
	}

	// ����Ԥ�����ѯ�ķ���
	public static List<Map<String, Object>> prepQuery(String sql) {
		Connection conn = BaseDao.getconnectiong();
		PreparedStatement prep = BaseDao.getprepareStatement(conn, sql);
		ResultSet rs = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			rs = prep.executeQuery();
			rs = prep.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= count; i++) {
					Object value = rs.getObject(i);
					String key = rsmd.getColumnName(i);
					map.put(key, value);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, prep, rs);
		}
		return list;
	}

	// ������ѯ�ķ���
	public static List<Map<String, Object>> executeQuery(String sql) {
		Connection conn = getconnectiong();
		Statement stmt = getstatement(conn);
		ResultSet rs = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= count; i++) {
					String colname = rsmd.getColumnName(i);
					Object value = rs.getObject(colname);
					map.put(colname, value);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		return list;
	}

	public static int executeUpdate(String sql) {
		Connection conn = getconnectiong();
		Statement stmt = getstatement(conn);
		int n = -1;
		try {
			n = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt);
		}
		return n;
	}

	// ������� ɾ��
	public static int[] executeBatch(List<String> sqllist) {
		Connection conn = getconnectiong();
		Statement stmt = getstatement(conn);
		int[] arr = null;
		for (String sql : sqllist) {
			try {
				stmt.addBatch(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				arr = stmt.executeBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return arr;
	}

	// �ر���Դ�ķ���
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// �ر���Դ
	public static void close(Connection conn, Statement stmt) {
		if (conn != null) {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
