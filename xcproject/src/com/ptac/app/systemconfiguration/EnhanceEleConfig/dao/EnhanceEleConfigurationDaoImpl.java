package com.ptac.app.systemconfiguration.EnhanceEleConfig.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;
import com.ptac.app.systemconfiguration.EnhanceEleConfig.bean.EnhanceEleConfigBean;

public class EnhanceEleConfigurationDaoImpl implements EnhanceEleConfigurationDao{
	  private int allPage;
	  public void setAllPage(int allpage ){
		  this.allPage=allpage;
		  
	  }
	  public int getAllPage(){
		  return this.allPage;
	  }
	public synchronized ArrayList getPageData(int start,String whereStr) {
		
	    ArrayList list = new ArrayList();
	    String sql = " SELECT * FROM PERMISSION_CONFIGURATION T WHERE ITEMLLAG = 3 ORDER BY ITEMID";
	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    try {
	      db.connectDb();
//	      StringBuffer strall = new StringBuffer();
//	      strall.append("SELECT COUNT(*) FROM PERMISSION_CONFIGURATION WHERE ITEMLLAG = 3 ");
//	      rs = db.queryAll(strall.toString());
//	      if(rs.next()){
//	    	  this.setAllPage((rs.getInt(1)+14)/15);
//	      }
	      rs=db.queryAll(sql);
	      Query query=new Query();
	      list = query.query(rs);
	    	    }catch (Exception de) {
	      de.printStackTrace();
	    }finally {
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

  /**
   * 修改信息
   * @param accountId String[]
   * @return String
   */
  public synchronized String modifyAutoAudit(EnhanceEleConfigBean formBean) {
	    String msg = "修改加强电费单配置信息失败！";
	    DataBase db = new DataBase();

	    try {
	      db.connectDb();
          
	      String sql = " UPDATE Permission_Configuration SET ItemValue='" + formBean.getItemValue() + "'" +
	      		       " WHERE ItemID="+formBean.getItemID(); 
	      System.out.println(sql.toString());
	      msg = "修改加强电费单配置信息失败！";
	      db.update(sql);
	      msg = "修改加强电费单配置信息成功！";
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
  
  public synchronized EnhanceEleConfigBean getEnhEleConfigInfo(String ItemID) {
	  EnhanceEleConfigBean bean = new EnhanceEleConfigBean();
    StringBuffer sql = new StringBuffer();
    sql.append("select * from Permission_Configuration where itemID="+ItemID);
    
    DataBase db = new DataBase();
    try {
    	System.out.println("getAutoAuditInfo:"+sql);
      db.connectDb();
      ResultSet rs = null;
      rs = db.queryAll(sql.toString());
      while (rs.next()) {
    	bean.setItemID(rs.getString("itemID") != null ? rs.getString("itemID") : "");
    	bean.setItemName(rs.getString("itemName") != null ? rs.getString("itemName") : "");
    	bean.setItemValue(rs.getString("itemValue") != null ? rs.getString("itemValue") : "");
    	bean.setItemDescription(rs.getString("itemDescription") != null ? rs.getString("itemDescription") : "");
    	
      }
      rs.close();
    }
    catch (DbException de) {
      de.printStackTrace();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    finally {
      try {
        db.close();
      }
      catch (DbException de) {
        de.printStackTrace();
      }
    }
    return bean;
  }
  
}
