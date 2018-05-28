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
	 * 新的抄表人管理Dao
	 * @param city（查询）
	 * @return list(返回查询数据集合)
	 *  
	 */
	//QuanXianDao qxDao  = new QuanXianDao();
	//获取ACCOUNT表有多少列，可以分为几页
	public int COUNT(String accountid,String accountname,String name,String sheng,String shi,String xian,String xiaoqu,String roleId,String loginId) {
		int a = 0; 		//定义共多少列
		int b = 10;		//定义每页条数
		int c = 0;		//定义共有几页
		
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
		//计算是否余列
		int x = a % b;

		if(x != 0){
			c = a / b + 1;
		}else{
			c = a / b;
		}
		//返回共有几页
		return c; 
	}
	//分页查询ACCOUNT表全部内容
	public ArrayList<ChaoBiaoRen_new> FenYe(int curpage,String accountid,String accountname,String name,String sheng,String shi,String xian,String xiaoqu,String roleId,String loginId) {
		System.out.println(curpage);
		//计算应跳转的内容
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
				cbr.setAccountname(rs.getString("ACCOUNTNAME"));	//登录名
				cbr.setName(rs.getString("NAME"));					//姓名
				cbr.setPassword(rs.getString("PASSWORD"));			//密码
				cbr.setRoleid(rs.getString("ROLEID"));				//角色ID
				cbr.setRolename(rs.getString("ROLENAME"));			//角色名称
				cbr.setDelsign(rs.getInt("DELSIGN"));				//是否显示(1显示0不显示)
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
		//计算应跳转的内容
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
	//获取抄表人所在区域
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
				qy.setShi(rs.getString("SHI"));			//市
				qy.setXian(rs.getString("XIAN")); 		//县
				qy.setXiaoqu(rs.getString("XIAOQU"));	//小区
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
	//根据ID进行查询
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
				cbr.setAccountname(rs.getString("ACCOUNTNAME"));	//登录名
				cbr.setName(rs.getString("NAME"));					//姓名
				cbr.setPassword(rs.getString("PASSWORD"));			//密码
				cbr.setRoleid(rs.getString("ROLEID"));				//角色ID
				cbr.setRolename(rs.getString("ROLENAME"));			//角色名称
				cbr.setDelsign(rs.getInt("DELSIGN"));				//是否显示(1显示0不显示)
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
