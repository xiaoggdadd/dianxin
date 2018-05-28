package com.noki.mobi.analysis.javabean;

import java.sql.SQLException;
import com.noki.database.DbException;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.noki.util.CTime;
import com.noki.util.Query;

public class AnalysisBean {
  public AnalysisBean() {
  }

  public synchronized ArrayList getDw(String dwxz) {
    ArrayList list = new ArrayList();
    CTime ct = new CTime();
    String kjnd = ct.formatShortDate().substring(0, 4);
    String sql = "SELECT DWDM,DWMC FROM CN_DWSUB WHERE KJND='" + kjnd + "'";
    if (!dwxz.equals("0")) {
      sql += " AND GL_DWXZ='" + dwxz + "'";
    }
    sql += " ORDER BY DWMC";

    //System.out.println(sql);
    DataBase db = new DataBase();
    ResultSet rs = null;

    try {
      db.connectDb();
      rs = db.queryAll(sql);
      Query query = new Query();
      list = query.query(rs);
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
