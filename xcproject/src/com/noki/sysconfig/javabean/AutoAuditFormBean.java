package com.noki.sysconfig.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.mobi.sys.javabean.AccountFormBean;


/**
 * Ammeterdegrees entity. @author MyEclipse Persistence Tools
 */

public class AutoAuditFormBean  implements java.io.Serializable {


    // Fields    
	//��վ��Ϣ
    private String ItemID ;
    private String ItemName;
    private String ItemValue;
    private String ItemDescription;
    

     public AutoAuditFormBean() {
     }
    // Property accessors

	
	/**
	   * �˻���ϸ���޸Ľ�����ô˷���
	   * @param accountId String
	   * @return AccountFormBeangetAmmeterDegreeAllInfo
	   */
	  public synchronized AutoAuditFormBean getAutoAuditInfo(String ItemID) {
		  AutoAuditFormBean bean = new AutoAuditFormBean();
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


	public String getItemID() {
		return ItemID;
	}


	public void setItemID(String itemID) {
		ItemID = itemID;
	}


	public String getItemName() {
		return ItemName;
	}


	public void setItemName(String itemName) {
		ItemName = itemName;
	}


	public String getItemValue() {
		return ItemValue;
	}


	public void setItemValue(String itemValue) {
		ItemValue = itemValue;
	}


	public String getItemDescription() {
		return ItemDescription;
	}


	public void setItemDescription(String itemDescription) {
		ItemDescription = itemDescription;
	}
	  
	 }