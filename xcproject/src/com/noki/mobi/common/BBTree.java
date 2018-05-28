package com.noki.mobi.common;

import java.util.ArrayList;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import com.noki.database.DbException;

public class BBTree {
  public BBTree() {
  }

  public ArrayList zt(String accountId) {
    ArrayList list = new ArrayList();
    DataBase db = new DataBase();
    ResultSet rs = null;
    try {
      db.connectDb();
      rs = null;
      StringBuffer sql = new StringBuffer();
      sql.setLength(0);
      sql.append("SELECT BMDM,BMMC,'0' AS BM FROM BM WHERE BMDM IN (SELECT BMDM FROM PERSON_BM WHERE ACCOUNTID="+accountId+")");
      sql.append("UNION SELECT DWDM,DWMC,GL_BMDM AS BM FROM CN_DWSUB WHERE GL_BMDM IN (SELECT BMDM FROM PERSON_BM WHERE ACCOUNTID="+accountId+") ORDER BY BM");


      rs = db.queryAll(sql.toString());
      ResultSetMetaData rmd = rs.getMetaData();
      int countColum = rmd.getColumnCount();
      list.add(new Integer(countColum));
      for (int i = 1; i <= countColum; i++) {
        list.add(rmd.getColumnName(i).toUpperCase());
      }
      while (rs.next()) {
        for (int i = 1; i <= countColum; i++) {
          list.add(rs.getString(i));
        }
      }

    }
    catch (SQLException se) {
      se.printStackTrace();
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

}
