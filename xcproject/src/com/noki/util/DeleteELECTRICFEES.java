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
			System.out.println("��������룺");
			String DBBM = sc.next();
			String BZID = SelectBZID(DBBM);
			System.out.println("��ȡ���ñ����ѯ���µı�����Ϣ����IDΪ��"+BZID);
			System.out.println("��ʼִ��ɾ��---");
			String Prompt = deleteBZ(BZID);
			System.out.println(Prompt);
		}
	}
	
	//���ݵ������ѯ���µģ����ݱ��˵�endtime�������ã�������Ϣ���ظñ�����Ϣ������ID
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
	//���ݱ��� IDɾ���ñ�����Ϣ
	
	public static String deleteBZ( String BZID) {
		String Prompt = "";
		String sql = "DELETE FROM ELECTRICFEES WHERE ELECTRICFEE_ID = "+BZID+"";
		DataBase db = new DataBase();
		try {
			db.connectDb();
			int rs = db.update(sql);
			Prompt = "ɾ�����";
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
