package com.noki.chaobiaorenyuan.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.chaobiaorenyuan.bean.ChaoBiaoRen_new;
import com.noki.chaobiaorenyuan.bean.QuYu;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;

public class ChaoBiaoRenDao_new {
	/**
	 * �µĳ����˹���Dao
	 * @param city����ѯ��
	 * @return list(���ز�ѯ���ݼ���)
	 *  
	 */
	//QuanXianDao qxDao  = new QuanXianDao();
	//��ȡACCOUNT���ж����У����Է�Ϊ��ҳ
	public int COUNT(String accountid,String accountname,String name,String sheng,String shi,String xian,String xiaoqu,String roleId,String loginId) {
		int a = 0; 		//���干������
		int b = 10;		//����ÿҳ����
		int c = 0;		//���干�м�ҳ
		
		String sql = "SELECT COUNT(0) FROM (select distinct ac.name,ac.accountid,ac.accountname ,ac.PASSWORD, ac.ROLEID ,ac.ROLENAME, ac.DELSIGN from  d_area_grade dag, per_area pa  ,account ac where 1=1 and dag.agcode = pa.agcode  and  pa.agcode in  ( select zd.xiaoqu from zhandian zd, d_area_grade dag, per_area pa  ,account ac where dag.agcode = pa.agcode and zd.xiaoqu = pa.agcode and pa.accountid = ac.accountid and ac.ACCOUNTID = '"+loginId+"'";
		
		if(sheng != null && !sheng.equals("") && !sheng.equals("0")){
			sql= sql + "AND zd.SHENG='"+sheng+"'";   
		}
		if(shi != null && !shi.equals("")&& !shi.equals("0")){
			sql= sql + "AND zd.SHI='"+shi+"'";   
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
			sql= sql + "AND zd.XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
			sql= sql + "AND zd.XIAOQU='"+xiaoqu+"'";
		}
		
		sql = sql	+ ")AND pa.accountid = ac.accountid ";
		
		if(accountid != null && !accountid.equals("")){
			sql= sql + "AND ac.ACCOUNTID LIKE '%"+accountid+"%'";
		}
		if(accountname != null && !accountname.equals("")){
			sql = sql +"AND ac.ACCOUNTNAME LIKE '%"+accountname+"%'";
		}
		if(name != null && !name.equals("")){
			sql = sql +"AND ac.NAME LIKE '%"+name+"%'";
		}
		if(roleId.equals("445")){
			sql = sql+"AND ac.ACCOUNTID = "+loginId+"";
		}
		sql = sql + "and ac.roleid = '445' and ac.delsign = 1)";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			if(rs.next()){
				a = rs.getInt(1);
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
		//�����Ƿ�����
		int x = a % b;

		if(x != 0){
			c = a / b + 1;
		}else{
			c = a / b;
		}
		//���ع��м�ҳ
		return c; 
	}
	//��ҳ��ѯACCOUNT��ȫ������
	public ArrayList<ChaoBiaoRen_new> FenYe(int curpage,String accountid,String accountname,String name,String sheng,String shi,String xian,String xiaoqu,String roleId,String loginId) {
		System.out.println(curpage);
		//����Ӧ��ת������
		int x = curpage * 10;		
		int y = curpage * 10 - 9;
		ArrayList<ChaoBiaoRen_new> al = null;
		String sql = "SELECT * FROM (SELECT ROWNUM R,abc.* FROM(select distinct ac.name,ac.accountid,ac.accountname ,ac.PASSWORD, ac.ROLEID ,ac.ROLENAME, ac.DELSIGN from  d_area_grade dag, per_area pa  ,account ac where 1=1 and dag.agcode = pa.agcode  and  pa.agcode in  ( select zd.xiaoqu from zhandian zd, d_area_grade dag, per_area pa  ,account ac where dag.agcode = pa.agcode and zd.xiaoqu = pa.agcode and pa.accountid = ac.accountid and ac.ACCOUNTID = '"+loginId+"'";
				
				if(sheng != null && !sheng.equals("") && !sheng.equals("0")){
					sql= sql + "AND zd.SHENG='"+sheng+"'";   
				}
				if(shi != null && !shi.equals("")&& !shi.equals("0")){
					sql= sql + "AND zd.SHI='"+shi+"'";   
				}
				if(xian != null && !xian.equals("")&& !xian.equals("0")){
					sql= sql + "AND zd.XIAN='"+xian+"'";
				}
				if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
					sql= sql + "AND zd.XIAOQU='"+xiaoqu+"'";
				}
				sql = sql	+ ") and pa.accountid = ac.accountid ";
				
				if(accountid != null && !accountid.equals("")){
					sql= sql + "AND ac.ACCOUNTID LIKE '%"+accountid+"%'";
				}
				if(accountname != null && !accountname.equals("")){
					sql = sql +"AND ac.ACCOUNTNAME LIKE '%"+accountname+"%'";
				}
				if(name != null && !name.equals("")){
					sql = sql +"AND ac.NAME LIKE '%"+name+"%'";
				}
				if(roleId.equals("445")){
					sql = sql+"AND ac.ACCOUNTID = "+loginId+"";
				}
				sql = sql + "and ac.roleid = '445' and ac.delsign = 1) abc where 1=1 AND ROWNUM <= "+x+") WHERE R >= "+y+"";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ChaoBiaoRen_new>();
			while(rs.next()){
				ChaoBiaoRen_new cbr = new ChaoBiaoRen_new();
				cbr.setAccountid(rs.getInt("ACCOUNTID"));			//ID
				cbr.setAccountname(rs.getString("ACCOUNTNAME"));	//��¼��
				cbr.setName(rs.getString("NAME"));					//����
				cbr.setPassword(rs.getString("PASSWORD"));			//����
				cbr.setRoleid(rs.getString("ROLEID"));				//��ɫID
				cbr.setRolename(rs.getString("ROLENAME"));			//��ɫ����
				cbr.setDelsign(rs.getInt("DELSIGN"));				//�Ƿ���ʾ(1��ʾ0����ʾ)
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
	//###################################################
	public ArrayList<ChaoBiaoRen_new> SHX(String loginid) {
		//����Ӧ��ת������
		ArrayList<ChaoBiaoRen_new> al = null;
		String sql = "SELECT A.SHENG,A.SHI,A.XIAN,A.XIAOQU FROM ACCOUNT A WHERE A.ACCOUNTID='"+loginid+"'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			
			rs = db.queryAll(sql);
			al = new ArrayList<ChaoBiaoRen_new>();
			while(rs.next()){
				ChaoBiaoRen_new cbr = new ChaoBiaoRen_new();
				cbr.setSheng(rs.getString("SHENG"));
				cbr.setShi(rs.getString("SHI"));
				cbr.setXian(rs.getString("XIAN"));
				cbr.setXiaoqu(rs.getString("XIAOQU"));
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
	//��ȡ��������������
	public ArrayList<QuYu> quyu(String chaobianrenID) {
		ArrayList<QuYu> al = null;
		String sql = "select shi,xian,xiaoqu from ACCOUNT where ACCOUNTID = "+chaobianrenID+" and delsign = 1 and roleid = '445'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<QuYu>();
			while(rs.next()){
				QuYu qy = new QuYu();
				qy.setShi(rs.getString("SHI"));			//��
				qy.setXian(rs.getString("XIAN")); 		//��
				qy.setXiaoqu(rs.getString("XIAOQU"));	//С��
				al.add(qy);
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
	//����ID���в�ѯ
	public ArrayList<ChaoBiaoRen_new> SelectID(String accountid) {
		ArrayList<ChaoBiaoRen_new> al = null;
		String sql = "select accountid,accountname,name,password,roleid,rolename,delsign from Account where accountid = "+accountid+" and delsign = 1 and roleid = '445'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ChaoBiaoRen_new>();
			while(rs.next()){
				ChaoBiaoRen_new cbr = new ChaoBiaoRen_new();
				cbr.setAccountid(rs.getInt("ACCOUNTID"));			//ID
				cbr.setAccountname(rs.getString("ACCOUNTNAME"));	//��¼��
				cbr.setName(rs.getString("NAME"));					//����
				cbr.setPassword(rs.getString("PASSWORD"));			//����
				cbr.setRoleid(rs.getString("ROLEID"));				//��ɫID
				cbr.setRolename(rs.getString("ROLENAME"));			//��ɫ����
				cbr.setDelsign(rs.getInt("DELSIGN"));				//�Ƿ���ʾ(1��ʾ0����ʾ)
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
