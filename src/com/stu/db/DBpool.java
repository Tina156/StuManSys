package com.stu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * ���ݿ������
 * @author hsz
 *
 */
public class DBpool {
	/**
	 * ���ݿ�����
	 */
	private static final String DB_DRIVER ="oracle.jdbc.driver.OracleDriver";	
	/**
	 * ���ݿ�url
	 */
	private static final String DB_URL ="jdbc:oracle:thin:@127.0.0.1:1522:orcl1";
	/**
	 * ���ݿ��û���
	 */
	private static final String DB_USER ="sys as sysdba";
	/**
	 * ���ݿ��û�������
	 */
	private static final String DB_PWD ="hsz123";
	/**
	 * ���ݿ����Ӷ���
	 */
	private static Connection conn = null;
	
	/**
	 * ��ȡ���ݿ�����
	 * @return connection
	 */
	public static Connection getConn(){
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		} catch (ClassNotFoundException e) {
			System.out.println("�������ݿ���������!"+e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("�������ݿ����!"+e.getMessage());
			e.printStackTrace();
		}
		return conn ;
	}
	
	/**
	 * �ر����ݿ�����
	 * @param conn connection
	 * @param pst  PreparedStatement
	 * @param rs   ResultSet
	 */
	public static void closeAll(Connection conn,Statement st,ResultSet rs){	
			try {
				if(rs!=null){
					rs.close();	
				}
				if(st!=null){
					st.close();
				}
				if(conn!=null){
					conn.close();
				}		
			} catch (SQLException e) {
				System.out.println("�ر����ݿ����ӳ���!");
				e.printStackTrace();
			}
		
	}
}
