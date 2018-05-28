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
	 * 对抄表人员表进行管理
	 * @param city（查询）
	 * @return list(返回查询数据集合)
	 *  
	 */
	//遍历TBL_APP_USER表全部内容
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
				cbr.setUsername(rs.getString("USERNAME"));	//用户名
				cbr.setPassword(rs.getString("PASSWORD"));	//密码
				cbr.setLoginname(rs.getString("LOGINNAME"));//登录名
				cbr.setDeleteflag(rs.getInt("DELETEFLAG"));//是否显示0显示1不显示
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
	//根据用户名进行模糊查询
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
				cbr.setUsername(rs.getString("USERNAME"));	//用户名
				cbr.setPassword(rs.getString("PASSWORD"));	//密码
				cbr.setLoginname(rs.getString("LOGINNAME"));//登录名
				cbr.setDeleteflag(rs.getInt("DELETEFLAG"));//是否显示0显示1不显示
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
	//判断用户名是否重复
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
			if(rs.next()){	//判断有值
				abc = -10;
			}else{
				abc = addCaoBiaoRen( uuid, userName, loginname, password);//判断没有重复，执行添加方法
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
	//执行添加方法
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
	//执行修改方法
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
	//执行删除方法(假删除)
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
	//根据ID进行查询
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
				cbr.setUsername(rs.getString("USERNAME"));	//用户名
				cbr.setPassword(rs.getString("PASSWORD"));	//密码
				cbr.setLoginname(rs.getString("LOGINNAME"));//登录名
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
