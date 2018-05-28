package com.noki.comm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.comm.bean.SeachItemBean;
import com.noki.comm.bean.SearchBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;

/**
 * 查询工具dao类
 * @author xuwangyu
 *
 */
public class SearchDAO {
	
	public List toGetDate(String tablename) {
		List list = null;
		String sql = "SELECT USER_TAB_COLS.TABLE_NAME,USER_TAB_COLS.COLUMN_NAME,user_col_comments.comments FROM USER_TAB_COLS inner join user_col_comments on user_col_comments.TABLE_NAME=USER_TAB_COLS.TABLE_NAME and user_col_comments.COLUMN_NAME=USER_TAB_COLS.COLUMN_NAME";
		
		String flg = "";
		if (tablename != null && tablename.length()>0) {
			if (tablename.indexOf(",")>0){
				String[] tables = tablename.split(",");
				String subSql = "";
				for(String aa:tables){
					flg= "1";
					subSql = subSql+",'"+aa.toUpperCase()+"'";
				}
				sql = sql+" and USER_TAB_COLS.TABLE_NAME in("+subSql.substring(1)+")";
				
			}else{
			  sql = sql + " and USER_TAB_COLS.TABLE_NAME='" + tablename.toUpperCase()+"'";
			}
		
		}
		sql = sql +" where user_col_comments.comments is not null and USER_TAB_COLS.COLUMN_NAME not in ('MEMO','SHENG','SHI','XIAN','XIAOQU','JZTYPE','YFLX','PROPERTY','BGSIGN','GDFS','JNGLMK','PROFESSIONALTYPEID','AMMETERUSE','USAGEOFELECTYPEID_AMMETER')";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			list = new ArrayList();
			while(rs.next()){
				SearchBean searchbean = new SearchBean();
				searchbean.setColum_name(rs.getString("COLUMN_NAME"));
				if ("1".equals(flg)){
					searchbean.setComments(rs.getString("TABLE_NAME")+":"+rs.getString("COMMENTs"));
				}else{
					searchbean.setComments(rs.getString("COMMENTs"));
				}
				
				list.add(searchbean);
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
		return list;
	}
	
	 //检索数据
	public List<SeachItemBean> seachData(SeachItemBean bean){
		List<SeachItemBean> retList=new ArrayList<SeachItemBean>();
		SeachItemBean ret = new SeachItemBean();
		String sql = "select * from seachitem t where 1=1";
		if (!"".equals(bean.getUserId())){
			sql = sql+"and t.userid = '"+bean.getUserId()+"'";
		}
		String userTable = bean.getUserTable();
		if (userTable!=null&&!"".equals(userTable)){
			sql = sql+"and t.userteable = '"+bean.getUserTable()+"'";
		}		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				ret.setUserTable(rs.getString("USERTEABLE"));
				ret.setUserId(rs.getString("USERID"));
				ret.setLeftId(rs.getString("LEFTITEM"));
				ret.setItem(rs.getString("ITEM"));
				ret.setCompareItem(rs.getString("COMPAREITEM"));
				ret.setCompareData(rs.getString("COMPAREDATA"));
				ret.setRightId(rs.getString("RIGHTITEM"));
				ret.setConettion(rs.getString("CONETTION"));
				retList.add(ret);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
		return retList;
	}
	
	//插入数据
	public void insertData(SeachItemBean bean){		
	String sql = "select * from seachitem t where 1=1";
	if (!"".equals(bean.getUserId())){
		sql = sql+"and t.userid = '"+bean.getUserId()+"'";
	}
	if (!"".equals(bean.getUserTable())){
		sql = sql+"and t.userteable = '"+bean.getUserTable()+"'";
	}
	if (!"".equals(bean.getItem())){
		sql = sql+"and t.item = '"+bean.getItem()+"'";
	}		
	DataBase db = new DataBase();
	ResultSet rs = null;
	try{
		db.connectDb();
		rs = db.queryAll(sql);
		if (!rs.next()){
			String insertSql= " insert into seachitem t (t.userteable,t.userid,t.leftitem,t.item,t.compareitem,t.comparedata,t.rightitem,t.conettion)values( ";
			insertSql = insertSql+"'"+bean.getUserTable()+"','"+bean.userId+"','"+bean.leftId+"','"+bean.item+"','"+bean.compareItem
			+"','"+bean.getCompareData()+"','"+bean.conettion+"','"+bean.getRightId()+"')";
			db.update(insertSql);
		}else{
			String updateSql = "update seachitem set leftitem='"+bean.getLeftId()+"',compareitem='"
			+bean.getCompareItem()+"',comparedata='"+bean.getCompareData()+"',rightitem='"+bean.getRightId()+"', conettion = '"+bean.getConettion()+"' where 1=1 ";
			updateSql = updateSql+" and  userteable ='" + bean.getUserTable()+"' and userid ='"+bean.getUserId()+
			"' and item ='"+bean.getItem()+"'";
			db.update(updateSql.toString());
		}	
		}catch(Exception e){
		      try {
			        db.rollback();
			      }
			      catch (DbException dee) {
			        dee.printStackTrace();
			      }
			      e.printStackTrace();
		}finally {
		      try {
			        db.close();
			      }
			      catch (Exception de) {
			        de.printStackTrace();
			      }
			}
	}
	
	//
	public void searchdel(SeachItemBean bean){
		String sql = "DELETE from seachitem t where 1=1";
		if (!"".equals(bean.getUserId())){
			sql = sql+"and t.userid = '"+bean.getUserId()+"'";
		}
		if (!"".equals(bean.getUserTable())){
			sql = sql+"and t.userteable = '"+bean.getUserTable()+"'";
		}	
		DataBase db = new DataBase();
		try {
			db.connectDb();
			 db.update(sql);
		} catch (DbException e) {
	      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      e.printStackTrace();
		}finally {
		      try {
		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		}
	}
}
