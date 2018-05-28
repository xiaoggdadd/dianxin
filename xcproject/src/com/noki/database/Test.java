package com.noki.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;//连接对象
		Statement stmt = null;//语句对象
		ResultSet rs = null;
		try{
			//DriverManager.registerDriver(new OracleDriver());//注册驱动程序
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			String url="jdbc:oracle:thin:@127.0.0.1:1521:CYCTEST"; //orcl为数据库的SID 

			String user="testcyc"; 

			String password="testcyc"; 

			 conn= DriverManager.getConnection(url,user,password);
			//建立到数据库的连接

			String sql = "select * from zhandian  ";


			stmt = conn.prepareStatement(sql);//通过连接对象获得语句对象
			boolean bool = stmt.execute(sql);
			if(bool){//如果布尔值为true，就意味着执行的是查询语句
				rs = stmt.getResultSet();
				while(rs.next()){
					System.out.println(rs.getString("SHENG"));
					System.out.println(rs.getString("JZNAME"));
				}
			}else{//如果布尔值为FALSE时，就意味着执行的不是查询语句
				int count = stmt.getUpdateCount();
				System.out.println(count);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{//收尾工作.

			//关闭所有对象，注意：后得到的对象先关闭，先得到的对象后关闭。
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
