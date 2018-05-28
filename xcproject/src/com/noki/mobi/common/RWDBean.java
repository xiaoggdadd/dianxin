package com.noki.mobi.common;

import com.noki.database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.noki.database.DbException;

public class RWDBean {
  public RWDBean() {
  }

  /**
   * 返回页面的读写权限编码
   * @param pageName String
   * @param accountId String
   * @return int
   */
  public synchronized int getRWD(String pageName, String roleId) {
    int j = 0;
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT R.OPERATIONRIGHT FROM ROLE_RIGHT R WHERE ROLEID='"+roleId+"'");
    sql.append(
        " AND R.RIGHTID=(SELECT RI.RIGHTID FROM [RIGHT] RI WHERE RI.URL LIKE '%" +
        pageName + ".jsp')");
    //System.out.println(sql.toString());
    DataBase db = new DataBase();
    ResultSet rs = null;
    try {
      db.connectDb();
      rs = db.queryAll(sql.toString());
      while (rs.next()) {
        j = rs.getInt(1);
      }
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
    return j;
  }

}
