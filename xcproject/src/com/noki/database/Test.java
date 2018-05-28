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
		Connection conn = null;//���Ӷ���
		Statement stmt = null;//������
		ResultSet rs = null;
		try{
			//DriverManager.registerDriver(new OracleDriver());//ע����������
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

			String url="jdbc:oracle:thin:@127.0.0.1:1521:CYCTEST"; //orclΪ���ݿ��SID 

			String user="testcyc"; 

			String password="testcyc"; 

			 conn= DriverManager.getConnection(url,user,password);
			//���������ݿ������

			String sql = "select * from zhandian  ";


			stmt = conn.prepareStatement(sql);//ͨ�����Ӷ�����������
			boolean bool = stmt.execute(sql);
			if(bool){//�������ֵΪtrue������ζ��ִ�е��ǲ�ѯ���
				rs = stmt.getResultSet();
				while(rs.next()){
					System.out.println(rs.getString("SHENG"));
					System.out.println(rs.getString("JZNAME"));
				}
			}else{//�������ֵΪFALSEʱ������ζ��ִ�еĲ��ǲ�ѯ���
				int count = stmt.getUpdateCount();
				System.out.println(count);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{//��β����.

			//�ر����ж���ע�⣺��õ��Ķ����ȹرգ��ȵõ��Ķ����رա�
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
