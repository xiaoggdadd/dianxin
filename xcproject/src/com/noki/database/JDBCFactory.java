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
	
	/*实例化本类的时候读取配置文件中的数据*/
	public JDBCFactory() {
		pro = new Properties();
		try {
			InputStream is = DataBase.class.getClassLoader().getResourceAsStream("jdbc.properties");
			pro.load(is);// 从输入流中读取属性列表（键和元素对）。
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*获得接口*/
	public static Connection getConnection() throws DbException{
		 Connection conn = null;
		 new JDBCFactory();
		try {
			String className = pro.getProperty("ClassName");// 获取键值driver
			Class.forName(className);
			String url = pro.getProperty("url");
			String username = pro.getProperty("username");
			String password = pro.getProperty("password");
			conn = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			throw new DbException("连接数据库出错，error in connectDb()!\r\n错误是" + e);
		}
		if (conn == null) {//
			System.err.print("conn is null");
		}
		return conn;
	}
	
	/*关闭，用于释放内存*/
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
	/*main方法用于检测是否能成功实例化本工厂*/
	public static void main(String[] args) throws DbException {
		JDBCFactory jd = new JDBCFactory();
		System.out.println(jd.getConnection());
	}
}
