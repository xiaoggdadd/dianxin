package com.noki.sysconfig.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.MD5;
import com.noki.util.Query;

public class AutoAuditBean {
	
public synchronized String getPowerPoleXiShu() {
		
		
	    String sql = "select t.itemvalue from permission_configuration t where ITEMNAME = 'powerpolexishu'";
	    String a="0";
	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    try {

	      rs=db.queryAll(sql);
	     try {
			while (rs.next()){
				a=rs.getString("itemvalue"); 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	   
	    catch (DbException de) {
	      de.printStackTrace();
	    }

	    finally {
	      if (rs != null) {
	        try {
	          rs.close();
	        }
	        catch (SQLException se) {
	          se.printStackTrace();
	        }
	      }
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }

	    }

	    return a;
	  }
	
	
	
	public synchronized ArrayList getPageData(int start,String whereStr) {
		
		System.out.println("SysSetBean-getPageData:"+whereStr);
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = " select * from permission_configuration t where ITEMLLAG = 2";

	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    try {
	      db.connectDb();
//	      StringBuffer strall = new StringBuffer();
//	      strall.append("select count(*) from permission_configuration where ITEMLLAG = 2 ");
//	      rs = db.queryAll(strall.toString());
//	      if(rs.next()){
//	    	  this.setAllPage((rs.getInt(1)+14)/15);
//	      }
//	      NPageBean nbean=new NPageBean();
	      //rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
	      rs=db.queryAll(sql);
	      Query query=new Query();
	      list = query.query(rs);
	      //System.out.println("-list-"+list.toString());
	    }
	   
	    catch (DbException de) {
	      de.printStackTrace();
	    }

	    finally {
	      if (rs != null) {
	        try {
	          rs.close();
	        }
	        catch (SQLException se) {
	          se.printStackTrace();
	        }
	      }
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }

	    }

	    return list;
	  }
  private int allPage;
  public void setAllPage(int allpage ){
	  this.allPage=allpage;
	  
  }
  public int getAllPage(){
	  return this.allPage;
  }
  

  /**
   * 修改电量信息
   * @param accountId String[]
   * @return String
   */
  public synchronized String modifyAutoAudit(AutoAuditFormBean formBean) {
	    String msg = "修改电量信息失败！";
	    DataBase db = new DataBase();

	    try {
	      db.connectDb();
          
	      String sql = " UPDATE Permission_Configuration SET ItemValue='" + formBean.getItemValue() + "'" +
	      		       " WHERE ItemID="+formBean.getItemID(); //修改电量信息表

	      System.out.println(sql.toString());
	      msg = "修改电量信息失败！";
	      db.update(sql);
	      msg = "修改电量信息成功！";
	    }
	    catch (DbException de) {
	      try {
	        db.rollback();
	      }
	      catch (DbException dee) {
	        dee.printStackTrace();
	      }
	      de.printStackTrace();
	    }
	    finally {
	      try {

	        db.close();
	      }
	      catch (Exception de) {
	        de.printStackTrace();
	      }
	    }

	    return msg;
	  }
  
}
