package com.noki.mobi.analysis.javabean;

import java.util.ArrayList;
import com.noki.util.CTime;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import com.noki.util.Query;
import java.sql.SQLException;
import com.noki.database.DbException;

public class TopAnalysisBean {
  public TopAnalysisBean() {
  }

  /**
   * topAnalysis
   * @param accountId String
   * @return ArrayList
   */
  public synchronized ArrayList getTopAnalysis(String dwxz, String srzc,
                                               String qijian) {
    ArrayList list = new ArrayList();
    CTime ct = new CTime();
    int month = Integer.parseInt(CTime.formatShortDate().substring(4, 6));
    if (!qijian.equals("0")) {
      month = Integer.parseInt(qijian);

    }

    String kjnd = CTime.formatShortDate().substring(0, 4);
    StringBuffer sql = new StringBuffer();
//    String sql = " AND KJND='" + kjnd + "' ORDER BY DWJC";
    sql.append("SELECT CW.DWMC,(SELECT SUM(ROUND(GY.NCD,2)-ROUND(GY.NCJ,2)");
    for (int i = 1; i <=month; i++) {
      sql.append("+round(GY.yd" + String.valueOf(i) + ",2)-round(GY.yj" +
                 String.valueOf(i) + ",2)");
    }
    sql.append(") FROM GL_YEB GY WHERE GY.CN_DWDM=CW.DWDM AND KJND='" + kjnd +
               "' AND GY.kmdm like '4%') SR,(SELECT SUM(ROUND(GY.NCJ,2)-ROUND(GY.NCD,2)");
    for (int i = 1; i <=month; i++) {
      sql.append("+round(GY.yj" + String.valueOf(i) + ",2)-round(GY.yd" +
                 String.valueOf(i) + ",2)");
    }
    sql.append(" ) FROM GL_YEB GY WHERE GY.CN_DWDM=CW.DWDM AND KJND='" + kjnd +
               "' AND GY.kmdm like '5%') ZC FROM CN_DWSUB CW");
    if (dwxz.equals("1")) {
      sql.append(" WHERE CW.GL_DWXZ='0'");
    }
    else if (dwxz.equals("2")) {
      sql.append(" WHERE CW.GL_DWXZ='1'");
    }
    if (srzc.equals("1")) {
      sql.append(" ORDER BY SR DESC");
    }
    else {
      sql.append(" ORDER BY ZC DESC");
    }

    System.out.println(sql.toString());
    DataBase db = new DataBase();
    ResultSet rs = null;

    try {
      db.connectDb();
      rs = db.queryAll(sql.toString());
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
