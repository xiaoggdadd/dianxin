package com.noki.chaobiaorenyuan.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.chaobiaorenyuan.bean.ChaoBiaoRen;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;

public class ChaoBiaoRenDao {
	/**
	 * �Գ�����Ա����й���
	 * @param city����ѯ��
	 * @return list(���ز�ѯ���ݼ���)
	 *  
	 */
	//����TBL_APP_USER��ȫ������
	public ArrayList<ChaoBiaoRen> Select() {
		ArrayList<ChaoBiaoRen> al = null;
		String sql = "select * from TBL_APP_USER where DELETEFLAG = 0";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ChaoBiaoRen>();
			while(rs.next()){
				ChaoBiaoRen cbr = new ChaoBiaoRen();
				cbr.setId(rs.getString("ID"));	//ID
				cbr.setUsername(rs.getString("USERNAME"));	//�û���
				cbr.setPassword(rs.getString("PASSWORD"));	//����
				cbr.setLoginname(rs.getString("LOGINNAME"));//��¼��
				cbr.setDeleteflag(rs.getInt("DELETEFLAG"));//�Ƿ���ʾ0��ʾ1����ʾ
				al.add(cbr);
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
		return al;
	}
	//�����û�������ģ����ѯ
	public ArrayList<ChaoBiaoRen> SelectName(String name) {
		ArrayList<ChaoBiaoRen> al = null;
		String sql = "select * from TBL_APP_USER where USERNAME like '%"+name+"%' and DELETEFLAG = 0";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ChaoBiaoRen>();
			while(rs.next()){
				ChaoBiaoRen cbr = new ChaoBiaoRen();
				cbr.setId(rs.getString("ID"));	//ID
				cbr.setUsername(rs.getString("USERNAME"));	//�û���
				cbr.setPassword(rs.getString("PASSWORD"));	//����
				cbr.setLoginname(rs.getString("LOGINNAME"));//��¼��
				cbr.setDeleteflag(rs.getInt("DELETEFLAG"));//�Ƿ���ʾ0��ʾ1����ʾ
				al.add(cbr);
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
		return al;
	}
	//�ж��û����Ƿ��ظ�
	public int SelectName(String uuid,String userName,String loginname,String password) {
		String sql = "select * from TBL_APP_USER where USERNAME = '"+userName+"'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		int abc = 0;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			System.out.println(rs);
			if(rs.next()){	//�ж���ֵ
				abc = -10;
			}else{
				abc = addCaoBiaoRen( uuid, userName, loginname, password);//�ж�û���ظ���ִ����ӷ���
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
		return abc;
	}
	//ִ����ӷ���
	public int addCaoBiaoRen(String uuid,String userName,String loginname,String password) {
		String sql = "insert into TBL_APP_USER(ID,USERNAME,PASSWORD,LOGINNAME)values('"+uuid+"','"+userName+"','"+loginname+"','"+password+"')";		
		DataBase db = new DataBase();
		int rs = 0;
		try {
			db.connectDb();
			rs = db.update(sql);
			
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return rs;
	}
	//ִ���޸ķ���
	public int update(String id,String userName,String loginname,String password) {
		String sql = "UPDATE  TBL_APP_USER set USERNAME = '"+userName+"',LOGINNAME= '"+loginname+"',PASSWORD= '"+password+"' WHERE  ID = '"+id+"'";		
		DataBase db = new DataBase();
		int rs = 0;
		try {
			db.connectDb();
			rs = db.update(sql);
			
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return rs;
	}
	//ִ��ɾ������(��ɾ��)
	public int delete(String id) {
		String sql = "UPDATE  TBL_APP_USER set DELETEFLAG = 1 WHERE  ID = '"+id+"'";		
		DataBase db = new DataBase();
		int rs = 0;
		try {
			db.connectDb();
			rs = db.update(sql);
			
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return rs;
	}
	//����ID���в�ѯ
	public ArrayList<ChaoBiaoRen> SelectID(String id) {
		ArrayList<ChaoBiaoRen> al = null;
		String sql = "select * from TBL_APP_USER WHERE ID ='"+id+"'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ChaoBiaoRen>();
			while(rs.next()){
				ChaoBiaoRen cbr = new ChaoBiaoRen();
				cbr.setId(rs.getString("ID"));	//ID
				cbr.setUsername(rs.getString("USERNAME"));	//�û���
				cbr.setPassword(rs.getString("PASSWORD"));	//����
				cbr.setLoginname(rs.getString("LOGINNAME"));//��¼��
				al.add(cbr);
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
		return al;
	}
}
