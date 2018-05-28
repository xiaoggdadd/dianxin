package com.noki.mobi.common;

import java.util.ArrayList;
import com.noki.database.DbException;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import com.noki.util.CTime;
import com.noki.util.Query;

public class ShouruXMBean {
  public ShouruXMBean() {
  }

  CTime time = new CTime();
  String kjnd = time.formatShortDate().substring(0, 4);

  public synchronized ArrayList getSrxm(String dwdm) {
    ArrayList list = new ArrayList();
    String sql_dg =
        "SELECT SRXMDM GNFLDMHEAD FROM DW_SRXM WHERE DWDM='" +
        dwdm + "' AND KJND='" + kjnd + "'";

    Query quer = new Query();
    DataBase db = new DataBase();
    ResultSet rs = null;
    try {
      db.connectDb();
      rs = db.queryAlls(sql_dg);
      if (rs.next()) {
        rs.last();
        int row = rs.getRow();
        rs.beforeFirst();
        String[] dm = new String[row];
        int jj = 0;
        while (rs.next()) {
          dm[jj++] = rs.getString(1);
        }
        rs = null;

        StringBuffer sql = null;

        sql = new StringBuffer();
        sql.append(
            "SELECT DISTINCT(ID),SRXMDM,SRXMMC,SFMX,'['+RTRIM(SRXMDM)+']'+RTRIM(SRXMMC) SFMX_CN,");
        sql.append("SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM SRXM WHERE KJND='" +
                   kjnd +
                   "' AND DELS=0 AND (");
        int len = 0;
        int kk = 0;
        for (int j = 0; j < row; j++) {
          len = dm[j].length();
          kk = 0;
          if (j == 0) {
            sql.append("SRXMDM IN (select  srxmdm from srxm where kjnd='" +
                       kjnd + "' and(");
            while (len > 2) {
              if (kk > 0) {
                sql.append(" or ");
              }
              sql.append(" SRXMDM=SUBSTRING('" + dm[j] + "',1," + len + ")");
              len = len - 2;
              kk++;
            }
            sql.append("))");
          }
          else {
            sql.append("or SRXMDM IN (select  srxmdm from srxm where kjnd='" +
                       kjnd + "' and(");
            while (len > 2) {
              if (kk > 0) {
                sql.append(" or ");
              }
              sql.append(" SRXMDM=SUBSTRING('" + dm[j] + "',1," + len + ")");
              len = len - 2;
              kk++;
            }
            sql.append("))");

          }
        } //end for

        sql.append(") order by srxmdm,sjdm");
        //System.out.println(sql);
        rs = db.queryAlls(sql.toString());

        list = quer.query(rs);
      }
      else {
        String sql =
            "SELECT ID,SRXMDM,SRXMMC,SFMX,'['+RTRIM(SRXMDM)+']'+RTRIM(SRXMMC) SFMX_CN,";
        sql += "SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM SRXM WHERE KJND='" +
            kjnd + "' AND DELS=0 AND PXH=0 ORDER BY SRXMDM,SJDM";
        //System.out.println(sql);
        rs = null;
        rs = db.queryAlls(sql);
        list = quer.query(rs);
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

  public synchronized ArrayList getGnfl(String dwdm) {
    ArrayList list = new ArrayList();
    /*
         String sql =
        "SELECT ID,GNFLDM,SFMX,'['+RTRIM(GNFLDM)+']'+RTRIM(GNFLMC) GNFL_CN,";
         sql += "SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM GNFL WHERE KJND='" +
        kjnd + "' AND DELS=0 AND PXH=0 AND GNFLDM<'208' ORDER BY GNFLDM,SJDM";
     */

    //System.out.println(sql);
    DataBase db = new DataBase();
    ResultSet rs = null;

    try {
      db.connectDb();

      /////////////////////////////////////////////////////////////
      /*
             String sql_dg =
       "SELECT DISTINCT(SUBSTRING(GNFLDM,1,3)) GNFLDMHEAD FROM DW_GNFL WHERE DWDM='" +
          dwdm + "'";
       */
      String sql_t =
          "SELECT DISTINCT(rtrim(GNFLDM)) GNFLDMHEAD FROM DW_GNFL WHERE DWDM='" +
          dwdm + "' AND KJND='" + kjnd + "'";
      String sql_dg =
          "SELECT DISTINCT(rtrim(GNFLDM)) GNFLDMHEAD FROM DW_GNFL WHERE DWDM='" +
          dwdm + "' AND KJND='" + kjnd + "' and len(gnfldm)=3";

      //System.out.println(sql_dg);
      rs = db.queryAlls(sql_t);
      if (rs.next()) {
        rs = null;
        rs = db.queryAlls(sql_dg);

        if (rs.next()) {
          ///////////////////////////////////////////////////////
          rs.last();
          int row = rs.getRow();
          rs.beforeFirst();
          String[] dm = new String[row];
          int jj = 0;

          while (rs.next()) {
            dm[jj++] = rs.getString(1);

          }
          rs = null;
          String sql_dg2 =
              "SELECT distinct(rtrim(gnfldm)),substring(gnfldm,1,3)  FROM DW_GNFL WHERE DWDM='" +
              dwdm + "' AND KJND='" + kjnd + "' and len(gnfldm)=5 and (";
          for (int k = 0; k < row; k++) {
            if (k == 0) {
              sql_dg2 += "gnfldm not like '" + dm[k] + "%'";
            }
            else {
              sql_dg2 += " and gnfldm not like '" + dm[k] + "%'";
            }
          }
          sql_dg2 += ")";
          //System.out.println("sql_dg2==" + sql_dg2);
          rs = db.queryAlls(sql_dg2);
          if (rs.next()) {
            rs.last();
            int row2 = rs.getRow();
            rs.beforeFirst();
            String[] dms = new String[row2 + row];
            int mm = 0;
            while (rs.next()) {
              dms[mm++] = rs.getString(1);
            }
            int jjj = 0;
            if (row2 > 1) {
              for (int i = 0; i < row2; i++) {
                for (int k = 1; k < row2; k++) {
                  if (i != k) {
                    if (dms[i].equals(dms[k])) {
                      dms[k] = "0";
                      jjj++;
                    }
                  }
                }
              }
            }
            //System.out.println("fuck:" + mm + "/row+row2==" + (row + row2 - 1));
            String[] dmd = new String[row + row2 - jjj];
            for (int k = 0; k < row + row2 - jjj; k++) {
              if (k < row) {
                dmd[k] = dm[k];
              }
              else {
                if (!dms[k - row].equals("0")) {
                  dmd[k - jjj] = dms[k - row];
                }
              }
            }

            rs = null;
            int countColum = 0;
            for (int j = 0; j < row + row2 - jjj; j++) {
              rs = null;
              String sql_ =
                  "SELECT ID,GNFLDM,SFMX,'['+RTRIM(GNFLDM)+']'+RTRIM(GNFLMC) GNFL_CN,";
              sql_ += "SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM GNFL WHERE KJND='" +
                  kjnd +
                  "' AND DELS=0 AND (GNFLDM IN (SELECT GNFLDM FROM DW_GNFL WHERE GNFLDM LIKE '" +
                  dmd[j] +
                  "%' AND KJND='" + kjnd + "') OR GNFLDM='" +
                  dmd[j].substring(0, 3) +
                  "') ORDER BY GNFLDM,SJDM";
              //System.out.println(sql_);
              rs = db.queryAll(sql_);
              if (j == 0) {
                ResultSetMetaData rmd = rs.getMetaData();
                countColum = rmd.getColumnCount();
                list.add(new Integer(countColum));

                for (int i = 1; i <= countColum; i++) {
                  list.add(rmd.getColumnName(i).toUpperCase());
                }
              }
              while (rs.next()) {
                for (int i = 1; i <= countColum; i++) {
                  list.add(rs.getString(i));
                }
              }

            }
          }
          else {
            int countColum = 0;
            for (int j = 0; j < row; j++) {
              rs = null;
              String sql_ =
                  "SELECT ID,GNFLDM,SFMX,'['+RTRIM(GNFLDM)+']'+RTRIM(GNFLMC) GNFL_CN,";
              sql_ += "SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM GNFL WHERE KJND='" +
                  kjnd +
                  "' AND DELS=0 AND (GNFLDM IN (SELECT GNFLDM FROM DW_GNFL WHERE GNFLDM LIKE '" +
                  dm[j] +
                  "%' AND KJND='" + kjnd + "') OR GNFLDM='" +
                  dm[j].substring(0, 3) +
                  "') ORDER BY GNFLDM,SJDM";
              System.out.println(sql_);
              rs = db.queryAll(sql_);
              if (j == 0) {
                ResultSetMetaData rmd = rs.getMetaData();
                countColum = rmd.getColumnCount();
                list.add(new Integer(countColum));

                for (int i = 1; i <= countColum; i++) {
                  list.add(rmd.getColumnName(i).toUpperCase());
                }
              }
              while (rs.next()) {
                for (int i = 1; i <= countColum; i++) {
                  list.add(rs.getString(i));
                }
              }

            }

          }
        }
        else { //////////*************************************

          String sql_dg2 =
              "SELECT distinct(rtrim(gnfldm))  FROM DW_GNFL WHERE DWDM='" +
              dwdm + "' AND KJND='" + kjnd + "' and len(gnfldm)=5 ";

          rs = db.queryAlls(sql_dg2);
          rs.last();
          int row2 = rs.getRow();
          rs.beforeFirst();
          String[] dms = new String[row2];
          int mm = 0;
          while (rs.next()) {
            dms[mm++] = rs.getString(1);
          }
          int ddd = 0;
          if (row2 > 1) {
            for (int k = 0; k < row2; k++) {
              for (int j = 1; j < row2; j++) {
                if (k != j) {
                  if (dms[k].equals(dms[j])) {
                    dms[j] = "0";
                    ddd++;
                  }
                }
              }
            }
          }
          rs = null;
          int countColum = 0;
          for (int j = 0; j < row2 - ddd; j++) {
            if (dms[j].equals("0")) {
              continue;
            }
            rs = null;
            String sql_ =
                "SELECT ID,GNFLDM,SFMX,'['+RTRIM(GNFLDM)+']'+RTRIM(GNFLMC) GNFL_CN,";
            sql_ += "SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM GNFL WHERE KJND='" +
                kjnd +
                "' AND DELS=0 AND (GNFLDM IN (SELECT GNFLDM FROM DW_GNFL WHERE GNFLDM LIKE '" +
                dms[j] +
                "%' AND KJND='" + kjnd + "') OR GNFLDM='" +
                dms[j].substring(0, 3) +
                "') ORDER BY GNFLDM,SJDM";
            System.out.println(sql_);
            rs = db.queryAll(sql_);
            if (j == 0) {
              ResultSetMetaData rmd = rs.getMetaData();
              countColum = rmd.getColumnCount();
              list.add(new Integer(countColum));

              for (int i = 1; i <= countColum; i++) {
                list.add(rmd.getColumnName(i).toUpperCase());
              }
            }
            while (rs.next()) {
              for (int i = 1; i <= countColum; i++) {
                list.add(rs.getString(i));
              }
            }

          }

        }
        //////////////////////////////////////////////////
      }
      else {
        /////////////////////////////////////////////////////////////
        rs = null;
        String sql =
            "SELECT ID,GNFLDM,SFMX,'['+RTRIM(GNFLDM)+']'+RTRIM(GNFLMC) GNFL_CN,";
        sql += "SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM GNFL WHERE KJND='" +
            kjnd + "' AND DELS=0 ORDER BY GNFLDM,SJDM";

        rs = db.queryAll(sql);
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

  public boolean getG(String dwdm) {
    boolean sign = false;
    DataBase db = new DataBase();
    ResultSet rs = null;
    try {
      db.connectDb();

      String sql_dg =
          "SELECT DISTINCT(SUBSTRING(GNFLDM,1,5)) GNFLDMHEAD FROM DW_GNFL WHERE DWDM='" +
          dwdm + "' AND KJND='" + kjnd + "'";
      //System.out.println(sql_dg);
      rs = db.queryAlls(sql_dg);
      if (rs.next()) {
        return true;
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

    return sign;
  }

  /**
   *  public synchronized ArrayList getGnfl() {
    ArrayList list = new ArrayList();


    String sql =
        "SELECT ID,GNFLDM,SFMX,'['+RTRIM(GNFLDM)+']'+RTRIM(GNFLMC) GNFL_CN,";
    sql += "SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM GNFL WHERE KJND='" +
        kjnd + "' AND DELS=0 AND PXH=0 ORDER BY SJDM";

    //System.out.println(sql);
    DataBase db = new DataBase();
    ResultSet rs = null;

    try {
      db.connectDb();

      /////////////////////////////////////////////////////////////



      /////////////////////////////////////////////////////////////

      rs = db.queryAll(sql);
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

   * @return ArrayList
   */

  public synchronized ArrayList getGnfl_() {
    ArrayList list = new ArrayList();
    String sql =
        "SELECT ID,GNFLDM,SFMX,'['+RTRIM(GNFLDM)+']'+RTRIM(GNFLMC) GNFL_CN,";
    sql += "SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM GNFL WHERE KJND='" +
        kjnd + "' AND DELS=0 AND PXH=0 AND GNFLDM>='208' ORDER BY GNFLDM,SJDM";

    //System.out.println(sql);
    DataBase db = new DataBase();
    ResultSet rs = null;

    try {
      db.connectDb();
      rs = db.queryAll(sql);
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

  public synchronized ArrayList getGnfl_zc(String dw) {
    CTime time = new CTime();
    String kjnd = time.formatShortDate().substring(0, 4);
    ArrayList list = new ArrayList();
    String sql_dm = "SELECT  DISTINCT(RTRIM(GNFL)) FROM YSZBZC Y WHERE Y.DELS=0 AND Y.SHZT=1 AND Y.KJND='" +
        kjnd +
        "' AND DW='" + dw + "'";
   // System.out.println(sql_dm);
    /*
         String sql =
        "SELECT DISTINCT(RTRIM(G.GNFLDM)) GNFLDM,SFMX,'['+RTRIM(G.GNFLDM)+']'+RTRIM(G.GNFLMC) GNFL_CN,";
         sql += "CASE WHEN (G.SJDM IS NULL OR G.SJDM='') THEN '0' ELSE G.SJDM END SJDM_CN FROM YSZBZC Y,GNFL G WHERE Y.GNFL=G.GNFLDM";
         sql += " AND Y.DELS=0 AND Y.SHZT=1 AND Y.KJND='" + kjnd +
        "' AND Y.PXH=0 AND DW='"+dw+"' ORDER BY GNFLDM";
     */
    DataBase db = new DataBase();

    ResultSet rs = null;

    try {
      db.connectDb();
      rs = db.queryAlls(sql_dm);
      if (rs != null) {
        rs.last();
        int row = rs.getRow();
        rs.beforeFirst();
        String[] dm = new String[row];
        int jj = 0;
        while (rs.next()) {
          dm[jj++] = rs.getString(1);
        }
        rs = null;
        int countColum = 0;
        int len = 0;
        int kk = 0;
        StringBuffer sql = new StringBuffer();
        sql.append(
            "SELECT DISTINCT(ID),GNFLDM,GNFLMC,SFMX,'['+RTRIM(GNFLDM)+']'+RTRIM(GNFLMC) GNFL_CN,");
        sql.append("SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM GNFL WHERE KJND='" +
                   kjnd +
                   "' AND DELS=0 AND (");

        for (int j = 0; j < row; j++) {
          len = dm[j].length();
          kk = 0;
          if (j == 0) {
            sql.append("GNFLDM IN (select  GNFLDM from GNFL where kjnd='" +
                       kjnd + "' and(");
            while (len > 2) {
              if (kk > 0) {
                sql.append(" or ");
              }
              sql.append(" GNFLDM=SUBSTRING('" + dm[j] + "',1," + len + ")");
              len = len - 2;
              kk++;
            }
            sql.append("))");
          }
          else {
            sql.append("or GNFLDM IN (select  GNFLDM from GNFL where kjnd='" +
                       kjnd + "' and(");
            while (len > 2) {
              if (kk > 0) {
                sql.append(" or ");
              }
              sql.append(" GNFLDM=SUBSTRING('" + dm[j] + "',1," + len + ")");
              len = len - 2;
              kk++;
            }
            sql.append("))");

          }
        } //end for
        sql.append(") order by gnfldm,sjdm");
        System.out.println(sql.toString());
        rs = db.queryAlls(sql.toString());
        Query quer = new Query();
        list = quer.query(rs);

        /*
                 for (int j = 0; j < row; j++) {
          rs = null;
          String sql =
         "SELECT ID,GNFLDM,SFMX,'['+RTRIM(GNFLDM)+']'+RTRIM(GNFLMC) GNFL_CN,";
          sql += "SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM GNFL WHERE KJND='" +
              kjnd + "' AND DELS=0 AND PXH=0 AND GNFLDM LIKE '" +
              dm[j].substring(0, 3) + "%' ORDER BY GNFLDM,SJDM";
          ///System.out.println(sql);
          rs = db.queryAll(sql);
          if (j == 0) {
            ResultSetMetaData rmd = rs.getMetaData();
            countColum = rmd.getColumnCount();
            list.add(new Integer(countColum));

            for (int i = 1; i <= countColum; i++) {
              list.add(rmd.getColumnName(i).toUpperCase());
            }
          }
          while (rs.next()) {
            for (int i = 1; i <= countColum; i++) {
              list.add(rs.getString(i));
            }
          }

                 }//end for
         */
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

  public synchronized ArrayList getJjfl() {
    ArrayList list = new ArrayList();
    String sql =
        "SELECT ID,JJFLDM,SFMX,'['+RTRIM(JJFLDM)+']'+RTRIM(JJFLMC) JJFL_CN,";
    sql += "SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM JJFL WHERE KJND='" +
        kjnd + "' AND DELS=0 AND PXH=0 ORDER BY JJFLDM";
    //System.out.println("jjfl:::" + sql.toString());
    DataBase db = new DataBase();
    ResultSet rs = null;

    try {
      db.connectDb();
      rs = db.queryAll(sql);
      Query quer = new Query();
      list = quer.query(rs);

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

  public synchronized ArrayList getJjfl_yb() {
    ArrayList list = new ArrayList();
    String sql =
        "SELECT ID,JJFLDM,SFMX,'['+RTRIM(JJFLDM)+']'+RTRIM(JJFLMC) JJFL_CN,";
    sql += "SJDM,CASE WHEN (SJDM IS NULL OR SJDM='') THEN '0' ELSE SJDM END SJDM_CN FROM JJFL WHERE JBZC=1 AND KJND='" +
        kjnd + "' AND DELS=0 AND PXH=0 ORDER BY JJFLDM";
    //System.out.println("jjfl:::" + sql.toString());
    DataBase db = new DataBase();
    ResultSet rs = null;

    try {
      db.connectDb();
      rs = db.queryAll(sql);
      Query quer = new Query();
      list = quer.query(rs);

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
