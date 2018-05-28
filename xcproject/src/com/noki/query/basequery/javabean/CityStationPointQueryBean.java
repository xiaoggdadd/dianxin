package com.noki.query.basequery.javabean;

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

public class CityStationPointQueryBean {
	public synchronized ArrayList getPageData(int start,String whereStr,String loginId) {
		
		System.out.println("StationPointQueryBean-getPageData:"+whereStr);
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    //调用负责站点条件函数
	 
		try {
			 fzzdstr = getFuzeZdid(db,loginId);
	       sql =
	        " select jz.ID,jz.JZNAME,jz.ZDCODE,(select t.name from indexs t where t.code = jz.property) as property,(select t.name from indexs t where t.code = jz.jztype) as jztype,(select t.name from indexs t where t.code = jz.yflx) as yflx,(select t.name from indexs t where t.code = jz.gdfs) as gdfs,jz.SYDATE,jz.ERPCODE,jz.FZR,jz.jnglmk,jz.area,jz.dianfei,dag.agname as shi," +	       
	        "(select agname from d_area_grade where agcode =substr( dag.agcode, 0, 3 )) as sheng" +
	        " from zhandian jz,d_area_grade dag where jz.shi=dag.agcode and dag.agrade='2' "+whereStr+
	        "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")";

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ResultSet rs = null;
	    try {
	      db.connectDb();
	      StringBuffer strall = new StringBuffer();
	      strall.append("select count(*) from zhandian jz,d_area_grade dag where jz.shi=dag.agcode and dag.agrade='2' "+whereStr+"and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
	      rs = db.queryAll(strall.toString());
	      System.out.println("strall.toString():"+strall.toString());
	      if(rs.next()){
	    	  this.setAllPage((rs.getInt(1)+14)/15);
	      }
	      NPageBean nbean=new NPageBean();
	      rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
	      Query query=new Query();
	      list = query.query(rs);
	    }
	   
	    catch (DbException de) {
	      de.printStackTrace();
	    } catch (SQLException de) {
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
//负责站点条件
	private String getFuzeZdid(DataBase db, String loginid) throws DbException, SQLException {
		ResultSet rs = null;

		rs = db
				.queryAll("select begincode,endcode from per_zhandian where accountid='"
						+ loginid
						+ "' and begincode is not null and endcode is not null");
		String cxtj = new String("");
		while (rs.next()) {			
				cxtj=cxtj+" or (zdcode>='" + rs.getString(1)
						+ "' and zdcode <='" + rs.getString(2) + "')";			
		}	
  System.out.println("负责站点条件："+cxtj);
		return cxtj.toString();
	}
  
}
