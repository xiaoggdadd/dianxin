package com.noki.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class DeleteELECTRICFEES {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("输入电表编码：");
			String DBBM = sc.next();
			String BZID = SelectBZID(DBBM);
			System.out.println("获取到该编码查询最新的报账信息主键ID为："+BZID);
			System.out.println("开始执行删除---");
			String Prompt = deleteBZ(BZID);
			System.out.println(Prompt);
		}
	}
	
	//根据电表编码查询最新的（根据报账的endtime列排序获得）报账信息返回该报账信息的主键ID
	public static String SelectBZID(String DBBM) {
		String BZID = "";
		String sql = "SELECT A.*,ROWNUM FROM (SELECT E.ELECTRICFEE_ID FROM  ELECTRICFEES E LEFT JOIN DIANBIAO D ON E.DIANBIAOID = D.ID WHERE D.DBBM = '"+DBBM+"' ORDER BY E.ENDTIME DESC) A WHERE  ROWNUM = 1";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			if(rs.next()){
				BZID = rs.getString(1);
			}
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return BZID; 
	}
	//根据报账 ID删除该报账信息
	
	public static String deleteBZ( String BZID) {
		String Prompt = "";
		String sql = "DELETE FROM ELECTRICFEES WHERE ELECTRICFEE_ID = "+BZID+"";
		DataBase db = new DataBase();
		try {
			db.connectDb();
			int rs = db.update(sql);
			Prompt = "删除完成";
		} catch (Exception de) {
			de.printStackTrace();
		} finally {

			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return Prompt;
	}
}
