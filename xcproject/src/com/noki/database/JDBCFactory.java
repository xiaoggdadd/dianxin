package com.noki.database;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCFactory {
	
	
	private static Properties pro = null;
	
	/*ʵ���������ʱ���ȡ�����ļ��е�����*/
	public JDBCFactory() {
		pro = new Properties();
		try {
			InputStream is = DataBase.class.getClassLoader().getResourceAsStream("jdbc.properties");
			pro.load(is);// ���������ж�ȡ�����б�����Ԫ�ضԣ���
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*��ýӿ�*/
	public static Connection getConnection() throws DbException{
		 Connection conn = null;
		 new JDBCFactory();
		try {
			String className = pro.getProperty("ClassName");// ��ȡ��ֵdriver
			Class.forName(className);
			String url = pro.getProperty("url");
			String username = pro.getProperty("username");
			String password = pro.getProperty("password");
			conn = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			throw new DbException("�������ݿ����error in connectDb()!\r\n������" + e);
		}
		if (conn == null) {//
			System.err.print("conn is null");
		}
		return conn;
	}
	
	/*�رգ������ͷ��ڴ�*/
	public static void free(ResultSet rs,Statement stmt,Connection conn){
		
			try {if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{

				try {if(stmt!=null){
						stmt.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{

					try {if(conn!=null){
							conn.close();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
	}
	/*main�������ڼ���Ƿ��ܳɹ�ʵ����������*/
	public static void main(String[] args) throws DbException {
		JDBCFactory jd = new JDBCFactory();
		System.out.println(jd.getConnection());
	}
}
