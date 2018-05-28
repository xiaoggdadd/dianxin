package com.noki.mobi.sys.javabean;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.page.PageBean;
import com.noki.page.OraclePageBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class LogBean {
  public LogBean() {
  }

  /**
   * 删除日志根据时间段
   * @param beginTime String
   * @param endTime String
   * @return boolean
   */
  public synchronized boolean delLogs(String beginTime, String endTime) {
    boolean sign = false;
    String end = endTime + " 23:59:59";
    String sql = "DELETE FROM LOGS WHERE LOGTIME>='" + beginTime +
        "' AND LOGTIME<='" + end + "'";

    DataBase db = new DataBase();
    try {
      db.connectDb();

      db.update(sql);
      sign = true;

    }
    catch (DbException de) {
      de.printStackTrace();
    }
    finally {
      try {
        db.close();
      }
      catch (DbException de) {
        de.printStackTrace();
      }
    }

    return sign;
  }

  /**
   * 日志查询
   * @param beginTime String
   * @param endTime String
   * @param pagenumber int
   * @return PageBean
   */
  public synchronized PageBean getLogs(String beginTime, String endTime,
                                       String title, String operName,
                                       int pagenumber) {

	  String sql =
	         "SELECT L.TITLE,L.CONTENT,L.ACCOUNTID,L.IPADDRESS,L.LOGTIME,L.ACCOUNTID AS NAME FROM LOGS L ";

	     if (beginTime.length() > 0) {

	       sql += " WHERE LOGTIME>=TO_DATE('" + beginTime + "','YYYY-MM-DD')";
	     }
	     if (beginTime.length() > 0) {

	       sql += " AND LOGTIME<=to_date('" + endTime + " 23:59:59','YYYY-MM-DD HH24:MI:SS')";
	     }

	     if (title.length() > 0) {
	       sql += " AND L.TITLE LIKE '%" + title + "%'";
	     }
	     if (operName.length() > 0) {
	       sql +=
	           " AND L.OPER  LIKE '%" +
	           operName + "%'";
	     }
	     sql += " ORDER BY L.LOGTIME DESC";
    System.out.println(sql.toString());
    PageBean pageBean = new OraclePageBean();
    pageBean = pageBean.listData(sql.toString(), Integer.toString(pagenumber));
    return pageBean;

  }

  public synchronized ArrayList getLogs(int start,String beginTime,String endTime,String title,String operName) {
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
            StringBuffer cxtj = new StringBuffer();
            if(beginTime!=null&&beginTime.length()>0){
                cxtj.append(" and l.logtime>=to_date('"+beginTime+"','yyyy-mm-dd')");
            }
            if(endTime!=null&&endTime.length()>0){
                cxtj.append(" and l.logtime<=to_date('" + endTime + " 23:59:59','YYYY-MM-DD HH24:MI:SS')");
            }
            if(title!=null&&title.length()>0){
                cxtj.append(" and l.title like '%"+title+"%'");
            }
            if(operName!=null&&operName.length()>0){
                cxtj.append(" and l.accountid in (select accountname from account where name like '%"+operName+"%')");
            }



	    StringBuffer sql = new StringBuffer();
            sql.append("select l.title,l.content,l.logtime,l.ipaddress,a.name from logs l,account a where l.accountid=a.accountname");
            sql.append(cxtj.toString());
           sql.append(" order by l.logtime desc");

	    DataBase db = new DataBase();
	    ResultSet rs = null;
     System.out.println("--"+sql.toString());
	    try {
	      db.connectDb();
	      StringBuffer strall = new StringBuffer();
	      strall.append("select count(*) from logs l ,account a where l.accountid=a.accountname ");
              strall.append(cxtj.toString());
	      rs = db.queryAll(strall.toString());
	      if(rs.next()){
                  if (rs.getInt(1) % 15 == 0) {
                  this.setAllPage(rs.getInt(1) / 15);
              } else {
                  this.setAllPage(rs.getInt(1) / 15 + 1);
              }

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
}
