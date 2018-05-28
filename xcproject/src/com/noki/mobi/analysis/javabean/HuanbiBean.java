package com.noki.mobi.analysis.javabean;

import com.noki.util.CTime;
import com.noki.database.DataBase;
import java.util.Vector;
import java.sql.ResultSet;
import com.noki.database.DbException;
import java.sql.SQLException;
import java.math.BigDecimal;

public class HuanbiBean {
  private CTime time = new CTime();
  private String kjnd = time.formatShortDate().substring(0, 4);
  private int month = Integer.parseInt(time.formatShortDate().substring(4, 6));
  private DataBase db;
  private String skjnd = kjnd;
  private int smonth = month - 1;

  public HuanbiBean() {
    if (smonth == 0) {
      skjnd = "2999";
      smonth = 1;
    }
  }

  public synchronized Vector getHuanbi_all(String dwdm, String qijian) {
    Vector v = new Vector();
    for (int i = 0; i < 24; i++) {
      v.add(i, "0");
    }
    if (!qijian.equals("0")) {
      month = Integer.parseInt(qijian);
      smonth = month - 1;
      if (smonth == 0) {
        skjnd = "2999";
        smonth = 1;
      }
    }
    String l = "";
    if (!dwdm.equals("0")) {
      l = " AND CN_DWDM='" + dwdm + "'";
    }
    ResultSet rs = null;
    String sql_sub1 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '4%'" + l;

    String sql_sub2 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
        ",2)-ROUND(yj" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '4%'" + l;
    String sql_sub3 = "select sum(round(yj" + String.valueOf(month) +
        ",2)-ROUND(yd" + String.valueOf(month) +
        ",2)) from gl_yeb where kjnd='" + kjnd + "' and kmdm like '5%'" + l;
    //String sql_sub4 = "select sum(round(yj"+String.valueOf(smonth)+")-ROUND(yd"+String.valueOf(smonth)+")) from gl_yeb where kjnd='"+skjnd+"' and kmdm like '4%'";
    String sql_t1 = "SELECT (" + sql_sub1 + "),(" + sql_sub2 + "),(" + sql_sub3 +
        "),sum(round(yj" + String.valueOf(smonth) + ",2)-ROUND(yd" +
        String.valueOf(smonth) + ",2)) FROM GL_YEB  where kjnd='" + skjnd +
        "' and kmdm like '5%'" + l;
    try {
      System.out.println(sql_t1);
      db = new DataBase();
      db.connectDb();
      rs = db.queryAll(sql_t1);
      String t1 = "", t2 = "";
      BigDecimal d = new BigDecimal("00000000000000.00");
      BigDecimal d2 = new BigDecimal("00000000000000.00");
      if (rs.next()) {
        t1 = rs.getString(1) != null ? rs.getString(1) : "0";
        t2 = rs.getString(2) != null ? rs.getString(2) : "0";
        v.set(0, t1);
        v.set(1, t2);
        d = d.add(new BigDecimal(Double.parseDouble(t1)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t2)));

        v.set(2, d.toString());
        //v.set(3, String.valueOf(rs.getLong(1) % rs.getLong(2)));

        d = new BigDecimal("00000000000000.00");
        t1 = rs.getString(3) != null ? rs.getString(3) : "0";
        t2 = rs.getString(4) != null ? rs.getString(4) : "0";
        v.set(12, t1);
        v.set(13, t2);
        d = d.add(new BigDecimal(Double.parseDouble(t1)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t2)));
        v.set(14, d.toString());
        //v.set(15, String.valueOf(rs.getLong(3) % rs.getLong(4)));
      }
      String sqlx_sub1 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
          ",2)-ROUND(yj" + String.valueOf(month) +
          ",2)) FROM GL_YEB WHERE KJND='" + kjnd +
          "' AND (kmdm like '401%' or kmdm like '404%' or kmdm like '407%') AND CN_DWDM IN (SELECT DWDM FROM CN_DWSUB WHERE GL_DWXZ='0')" +
          l;
      String sqlx_sub2 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
          ",2)-ROUND(yj" + String.valueOf(smonth) +
          ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND (kmdm like '401%' or kmdm like '404%' or kmdm like '407%') AND CN_DWDM IN (SELECT DWDM FROM CN_DWSUB WHERE GL_DWXZ='0')" +
          l;

      /*
       String sqlx_sub2 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
          ",2)-ROUND(yj" + String.valueOf(smonth) +
          ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND (kmdm like '401%' or kmdm like '403%' or kmdm like '404%' or kmdm like '405%' or kmdm like '409%' or kmdm like '412%' or kmdm like '413%')";*/
      String sqls_sub3 = "select sum(round(yd" + String.valueOf(month) +
          ",2)-ROUND(yj" + String.valueOf(month) +
          ",2)) from gl_yeb where  CN_DWDM IN (SELECT DWDM FROM CN_DWSUB WHERE GL_DWXZ='1') AND kjnd='" +
          kjnd + "' and (kmdm like '401%' or kmdm like '403%' or kmdm like '404%' or kmdm like '405%' or kmdm like '409%' or kmdm like '412%' or kmdm like '413%')" +
          l;
      //String sql_sub4 = "select sum(round(yj"+String.valueOf(smonth)+")-ROUND(yd"+String.valueOf(smonth)+")) from gl_yeb where kjnd='"+skjnd+"' and kmdm like '4%'";
      String sqls_t1 = "SELECT (" + sqlx_sub1 + "),(" + sqlx_sub2 + "),(" +
          sqls_sub3 + "),sum(round(yd" + String.valueOf(smonth) +
          ",2)-ROUND(yj" + String.valueOf(smonth) +
          ",2)) FROM GL_YEB  where   CN_DWDM IN (SELECT DWDM FROM CN_DWSUB WHERE GL_DWXZ='1') AND kjnd='" +
          skjnd + "' and (kmdm like '401%' or kmdm like '403%' or kmdm like '404%' or kmdm like '405%' or kmdm like '409%' or kmdm like '412%' or kmdm like '413%')" +
          l;
      System.out.println(sqls_t1);
      rs = db.queryAll(sqls_t1);
      if (rs.next()) {
        d = new BigDecimal("00000000000000.00");
        t1 = rs.getString(1) != null ? rs.getString(1) : "0";
        t2 = rs.getString(2) != null ? rs.getString(2) : "0";

        d = d.add(new BigDecimal(Double.parseDouble(t1)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t2)));

        v.set(4, t1);
        v.set(5, t2);
        v.set(6, d.toString());
        //v.set(7, String.valueOf(rs.getLong(1) % rs.getLong(2)));
        v.set(7, "0");
        d = new BigDecimal("00000000000000.00");
        t1 = rs.getString(3) != null ? rs.getString(3) : "0";
        t2 = rs.getString(4) != null ? rs.getString(4) : "0";

        d = d.add(new BigDecimal(Double.parseDouble(t1)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t2)));

        v.set(8, t1);
        v.set(9, t2);
        v.set(10, d.toString());
        //v.set(11, String.valueOf(rs.getLong(3) % rs.getLong(4)));
        v.set(11, "0");
      }

      String sqlzx_sub1 = "SELECT SUM(ROUND(YJ" + String.valueOf(month) +
          ",2)-ROUND(yd" + String.valueOf(month) +
          ",2)) FROM GL_YEB WHERE KJND='" + kjnd +
          "' AND kmdm like '501%' AND CN_DWDM IN (SELECT DWDM FROM CN_DWSUB WHERE GL_DWXZ='0')" +
          l;
      String sqlzx_sub2 = "SELECT SUM(ROUND(Yj" + String.valueOf(smonth) +
          ",2)-ROUND(yd" + String.valueOf(smonth) +
          ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND kmdm like '501%' AND CN_DWDM IN (SELECT DWDM FROM CN_DWSUB WHERE GL_DWXZ='0')" +
          l;

      /*
       String sqlx_sub2 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
          ",2)-ROUND(yj" + String.valueOf(smonth) +
          ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND (kmdm like '401%' or kmdm like '403%' or kmdm like '404%' or kmdm like '405%' or kmdm like '409%' or kmdm like '412%' or kmdm like '413%')";*/
      String sqlzs_sub3 = "select sum(round(yj" + String.valueOf(month) +
          ",2)-ROUND(yd" + String.valueOf(month) +
          ",2)) from gl_yeb where  CN_DWDM IN (SELECT DWDM FROM CN_DWSUB WHERE GL_DWXZ='1') AND kjnd='" +
          kjnd + "' and kmdm like '504%'" + l;
      //String sql_sub4 = "select sum(round(yj"+String.valueOf(smonth)+")-ROUND(yd"+String.valueOf(smonth)+")) from gl_yeb where kjnd='"+skjnd+"' and kmdm like '4%'";
      String sqlzs_t1 = "SELECT (" + sqlzx_sub1 + "),(" + sqlzx_sub2 + "),(" +
          sqlzs_sub3 + "),sum(round(yj" + String.valueOf(smonth) +
          ",2)-ROUND(yd" + String.valueOf(smonth) +
          ",2)) FROM GL_YEB  where   CN_DWDM IN (SELECT DWDM FROM CN_DWSUB WHERE GL_DWXZ='1') AND kjnd='" +
          skjnd + "' and kmdm like '504%'" + l;
      System.out.println(sqlzs_t1);
      rs = db.queryAll(sqlzs_t1);
      if (rs.next()) {
        d = new BigDecimal("00000000000000.00");
        t1 = rs.getString(1) != null ? rs.getString(1) : "0";
        t2 = rs.getString(2) != null ? rs.getString(2) : "0";

        d = d.add(new BigDecimal(Double.parseDouble(t1)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t2)));

        v.set(16, t1);
        v.set(17, t2);
        v.set(18, d.toString());
        //v.set(7, String.valueOf(rs.getLong(1) % rs.getLong(2)));
        v.set(19, "0");
        d = new BigDecimal("00000000000000.00");
        t1 = rs.getString(3) != null ? rs.getString(3) : "0";
        t2 = rs.getString(4) != null ? rs.getString(4) : "0";

        d = d.add(new BigDecimal(Double.parseDouble(t1)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t2)));
        System.out.println(d.toString());
        v.set(20, t1);
        v.set(21, t2);
        v.set(22, d.toString());
        //v.set(11, String.valueOf(rs.getLong(3) % rs.getLong(4)));
        v.set(23, "0");

      }

    }
    catch (DbException de) {
      de.printStackTrace();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return v;
  }

  /**
   * 对应行政单位
   * @param dwdm String
   * @param qijian String
   * @return Vector
   */
  public synchronized Vector getHuanbi_xz(String dwdm, String qijian) {
    Vector v = new Vector();
    for (int i = 0; i < 44; i++) {
      v.add(i, "0");
    }
    if (!qijian.equals("0")) {
      month = Integer.parseInt(qijian);
      smonth = month - 1;
      if (smonth == 0) {
        skjnd = "2999";
        smonth = 1;
      }

    }
    String l = " AND CN_DWDM IN (select dwdm from cn_dwsub where gl_dwxz='0')";
    if (!dwdm.equals("0")) {
      l = " AND CN_DWDM='" + dwdm + "'";
    }
    ResultSet rs = null;
    String sql_sub1 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '4%'" + l;

    String sql_sub12 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
        ",2)-ROUND(yj" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '4%'" + l;
    String sql_sub2 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '401%'" + l;
    String sql_sub22 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
        ",2)-ROUND(yj" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '401%'" + l;
    String sql_sub3 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '404%'" + l;
    String sql_sub32 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
        ",2)-ROUND(yj" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '404%'" + l;
    String sql_sub4 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd +
        "' AND (KMDM NOT LIKE '401%' or KMDM not LIKE '404%')" + l;
    String sql_t1 = "SELECT (" + sql_sub1 + "),(" + sql_sub12 + "),(" +
        sql_sub2 + "),(" + sql_sub22 + "),(" + sql_sub3 +
        "),(" + sql_sub32 + "),(" + sql_sub4 + "),sum(round(yd" +
        String.valueOf(smonth) + ",2)-ROUND(yj" +
        String.valueOf(smonth) + ",2)) FROM GL_YEB  where kjnd='" + skjnd +
        "' and  (KMDM NOT LIKE '401%' or KMDM NOT LIKE '404%')" + l;
    try {
      System.out.println(sql_t1);
      db = new DataBase();
      db.connectDb();
      rs = db.queryAll(sql_t1);
      String t1 = "", t11 = "", t2 = "", t22 = "", t3 = "", t33 = "", t4 = "",
          t44 = "";
      BigDecimal d = new BigDecimal("00000000000000.00");
      BigDecimal d2 = new BigDecimal("00000000000000.00");
      BigDecimal d3 = new BigDecimal("00000000000000.00");
      BigDecimal d4 = new BigDecimal("00000000000000.00");
      if (rs.next()) {
        t1 = rs.getString(1) != null ? rs.getString(1) : "0";
        t11 = rs.getString(2) != null ? rs.getString(2) : "0";
        t2 = rs.getString(3) != null ? rs.getString(3) : "0";
        t22 = rs.getString(4) != null ? rs.getString(4) : "0";
        t3 = rs.getString(5) != null ? rs.getString(5) : "0";
        t33 = rs.getString(6) != null ? rs.getString(6) : "0";
        t4 = rs.getString(7) != null ? rs.getString(7) : "0";
        t44 = rs.getString(8) != null ? rs.getString(8) : "0";
        v.set(0, t1);
        v.set(1, t11);
        d = d.add(new BigDecimal(Double.parseDouble(t1)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t1)));

        v.set(2, d.toString());
        //v.set(3, String.valueOf(rs.getLong(1) % rs.getLong(2)));
        v.set(4, t2);
        v.set(5, t22);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t2)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t22)));
        v.set(6, d.toString());
        v.set(8, t3);
        v.set(9, t33);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t3)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t33)));
        v.set(10, d.toString());
        v.set(12, t4);
        v.set(13, t44);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t4)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t44)));
        v.set(14, d.toString());

        //v.set(15, String.valueOf(rs.getLong(3) % rs.getLong(4)));

      }
      String sqlzx_subc1 = "SELECT SUM(ROUND(YJ" + String.valueOf(month) +
          ",2)-ROUND(yd" + String.valueOf(month) +
          ",2)) FROM GL_YEB WHERE KJND='" + kjnd +
          "' AND kmdm like '5%'" + l;
      String sqlzx_subc11 = "SELECT SUM(ROUND(Yj" + String.valueOf(smonth) +
          ",2)-ROUND(yd" + String.valueOf(smonth) +
          ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND kmdm like '5%'" + l;
      String sqlzx_subc2 = "SELECT SUM(ROUND(YJ" + String.valueOf(month) +
          ",2)-ROUND(yd" + String.valueOf(month) +
          ",2)) FROM GL_YEB WHERE KJND='" + kjnd +
          "' AND kmdm like '501%'" + l;
      String sqlzx_subc22 = "SELECT SUM(ROUND(Yj" + String.valueOf(smonth) +
          ",2)-ROUND(yd" + String.valueOf(smonth) +
          ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND kmdm like '501%'" +
          l;
      String sqlzx_subc3 = "SELECT SUM(ROUND(YJ" + String.valueOf(month) +
          ",2)-ROUND(yd" + String.valueOf(month) +
          ",2)) FROM GL_YEB WHERE KJND='" + kjnd +
          "' AND kmdm like '502%'" + l;
      String sqlzx_subc33 = "SELECT SUM(ROUND(Yj" + String.valueOf(smonth) +
          ",2)-ROUND(yd" + String.valueOf(smonth) +
          ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND kmdm like '502%'" +
          l;
      String sqlzx_subc4 = "SELECT SUM(ROUND(YJ" + String.valueOf(month) +
          ",2)-ROUND(yd" + String.valueOf(month) +
          ",2)) FROM GL_YEB WHERE KJND='" + kjnd +
          "' AND kmdm like '505%'" + l;

      String sql_tc = "SELECT (" + sqlzx_subc1 + "),(" + sqlzx_subc11 + "),(" +
          sqlzx_subc2 + "),(" + sqlzx_subc22 + "),(" + sqlzx_subc3 +
          "),(" + sqlzx_subc33 + "),(" + sqlzx_subc4 + "),sum(round(yj" +
          String.valueOf(smonth) + ",2)-ROUND(yd" +
          String.valueOf(smonth) + ",2)) FROM GL_YEB  where kjnd='" + skjnd +
          "' and kmdm like '505%'" + l;
      System.out.println(sql_tc);
      rs = db.queryAll(sql_tc);
      if (rs.next()) {
        t1 = rs.getString(1) != null ? rs.getString(1) : "0";
        t11 = rs.getString(2) != null ? rs.getString(2) : "0";
        t2 = rs.getString(3) != null ? rs.getString(3) : "0";
        t22 = rs.getString(4) != null ? rs.getString(4) : "0";
        t3 = rs.getString(5) != null ? rs.getString(5) : "0";
        t33 = rs.getString(6) != null ? rs.getString(6) : "0";
        t4 = rs.getString(7) != null ? rs.getString(7) : "0";
        t44 = rs.getString(8) != null ? rs.getString(8) : "0";
        v.set(16, t1);
        v.set(17, t11);
        d = d.add(new BigDecimal(Double.parseDouble(t1)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t2)));

        v.set(18, d.toString());
        //v.set(3, String.valueOf(rs.getLong(1) % rs.getLong(2)));
        v.set(20, t2);
        v.set(21, t22);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t2)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t22)));
        v.set(22, d.toString());
        v.set(24, t3);
        v.set(25, t33);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t3)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t33)));
        v.set(26, d.toString());
        v.set(28, t4);
        v.set(29, t44);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t4)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t44)));
        v.set(30, d.toString());

        //v.set(15, String.valueOf(rs.getLong(3) % rs.getLong(4)));

      }
      String sql_suby1 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
          ",2)-ROUND(yj" + String.valueOf(month) +
          ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '3%'" + l;

      String sql_suby12 = "SELECT  (" + sql_suby1 + "),SUM(ROUND(YD" +
          String.valueOf(smonth) +
          ",2)-ROUND(yj" + String.valueOf(smonth) +
          ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '3%'" + l;
      rs = db.queryAll(sql_suby12);
      if (rs.next()) {
        t1 = rs.getString(1) != null ? rs.getString(1) : "0";
        t11 = rs.getString(2) != null ? rs.getString(2) : "0";
        v.set(32, t1);
        v.set(33, t11);
        d = d.add(new BigDecimal(Double.parseDouble(t1)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t2)));

        v.set(34, d.toString());

      }

    }
    catch (DbException de) {
      de.printStackTrace();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return v;
  }

  /**
   * 对应事业单位
   * @param dwdm String
   * @param qijian String
   * @return Vector
   */
  public synchronized Vector getHuanbi_sy(String dwdm, String qijian) {
    Vector v = new Vector();
    for (int i = 0; i < 40; i++) {
      v.add(i, "0");
    }
    if (!qijian.equals("0")) {
      month = Integer.parseInt(qijian);
      smonth = month - 1;
      if (smonth == 0) {
        skjnd = "2999";
        smonth = 1;
      }

    }
    String l = " AND CN_DWDM IN (select dwdm from cn_dwsub where gl_dwxz='1')";
    if (!dwdm.equals("0")) {
      l = " AND CN_DWDM='" + dwdm + "'";
    }
    ResultSet rs = null;
    String sql_sub1 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '4%'" + l;

    String sql_sub12 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
        ",2)-ROUND(yj" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '4%'" + l;
    String sql_sub2 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '404%'" + l;
    String sql_sub22 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
        ",2)-ROUND(yj" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '404%'" + l;
    String sql_sub3 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '401%'" + l;
    String sql_sub32 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
        ",2)-ROUND(yj" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '401%'" + l;
    String sql_sub4 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '403%'" + l;
    String sql_sub42 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
        ",2)-ROUND(yj" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '403%'" + l;
    String sql_sub5 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '412%'" + l;
    String sql_sub52 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
        ",2)-ROUND(yj" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '412%'" + l;
    String sql_sub6 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '405%'" + l;
    String sql_sub62 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
        ",2)-ROUND(yj" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '405%'" + l;
    String sql_sub7 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '40501%'" + l;
    String sql_sub72 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
        ",2)-ROUND(yj" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '40501%'" +
        l;

    String sql_sub9 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '409%'" + l;
    String sql_sub92 = "SELECT SUM(ROUND(YD" + String.valueOf(smonth) +
        ",2)-ROUND(yj" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '409%'" + l;
    String sql_sub10 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
        ",2)-ROUND(yj" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND (KMDM NOT LIKE '401%' or KMDM NOT LIKE '403%' or KMDM NOT LIKE '404%' or KMDM NOT LIKE '405%' or KMDM NOT LIKE '409%' or KMDM NOT LIKE '412%')" +
        l;

    String sql_t1 = "SELECT (" + sql_sub1 + "),(" + sql_sub12 + "),(" +
        sql_sub2 + "),(" + sql_sub22 + "),(" + sql_sub3 +
        "),(" + sql_sub32 + "),(" + sql_sub4 + "),(" + sql_sub42 + "),(" +
        sql_sub5 + "),(" + sql_sub52 + "),(" + sql_sub6 + "),(" + sql_sub62 +
        "),(" + sql_sub7 + "),(" + sql_sub72 + "),(" + sql_sub9 +
        "),(" + sql_sub92 + "),(" + sql_sub10 + "),sum(round(yd" +
        String.valueOf(smonth) + ",2)-ROUND(yj" +
        String.valueOf(smonth) + ",2)) FROM GL_YEB  where kjnd='" +
        skjnd + "' and  (KMDM NOT LIKE '401%' or KMDM NOT LIKE '403%' or KMDM NOT LIKE '404%' or KMDM NOT LIKE '405%' or KMDM NOT LIKE '409%' or KMDM NOT LIKE '412%')" +
        l;
    try {
      System.out.println(sql_t1);
      db = new DataBase();
      db.connectDb();
      rs = db.queryAll(sql_t1);
      String t1 = "", t11 = "", t2 = "", t22 = "", t3 = "", t33 = "", t4 = "",
          t44 = "", t5 = "", t52 = "", t6 = "", t62 = "", t7 = "", t72 = "",
          t8 = "", t82 = "", t9 = "", t92 = "";
      BigDecimal d = new BigDecimal("00000000000000.00");

      if (rs.next()) {
        t1 = rs.getString(1) != null ? rs.getString(1) : "0";

        t11 = rs.getString(2) != null ? rs.getString(2) : "0";

        t2 = rs.getString(3) != null ? rs.getString(3) : "0";

        t22 = rs.getString(4) != null ? rs.getString(4) : "0";

        t3 = rs.getString(5) != null ? rs.getString(5) : "0";

        t33 = rs.getString(6) != null ? rs.getString(6) : "0";

        t4 = rs.getString(7) != null ? rs.getString(7) : "0";

        t44 = rs.getString(8) != null ? rs.getString(8) : "0";

        t5 = rs.getString(9) != null ? rs.getString(9) : "0";

        t52 = rs.getString(10) != null ? rs.getString(10) : "0";

        t6 = rs.getString(11) != null ? rs.getString(11) : "0";

        t62 = rs.getString(12) != null ? rs.getString(12) : "0";

        t7 = rs.getString(13) != null ? rs.getString(13) : "0";

        t72 = rs.getString(14) != null ? rs.getString(14) : "0";

        t8 = rs.getString(15) != null ? rs.getString(15) : "0";

        t82 = rs.getString(16) != null ? rs.getString(16) : "0";

        t9 = rs.getString(17) != null ? rs.getString(17) : "0";

        t92 = rs.getString(18) != null ? rs.getString(18) : "0";

        v.set(0, t1);
        v.set(1, t11);
        d = d.add(new BigDecimal(Double.parseDouble(t1)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t11)));

        v.set(2, d.toString());
        //v.set(3, String.valueOf(rs.getLong(1) % rs.getLong(2)));
        v.set(4, t2);
        v.set(5, t22);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t2)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t22)));
        v.set(6, d.toString());
        v.set(8, t3);
        v.set(9, t33);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t3)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t33)));
        v.set(10, d.toString());
        v.set(12, t4);
        v.set(13, t44);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t4)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t44)));
        v.set(14, d.toString());
        d = new BigDecimal("00000000000000.00");
        v.set(16, t5);
        v.set(17, t52);
        d = d.add(new BigDecimal(Double.parseDouble(t5)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t52)));

        v.set(18, d.toString());
        //v.set(3, String.valueOf(rs.getLong(1) % rs.getLong(2)));
        v.set(20, t6);
        v.set(21, t62);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t6)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t62)));
        v.set(22, d.toString());
        v.set(24, t7);
        v.set(25, t72);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t7)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t72)));
        v.set(26, d.toString());
        v.set(28, t8);
        v.set(29, t82);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t8)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t82)));
        v.set(30, d.toString());
        v.set(32, t9);
        v.set(33, t92);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t9)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t92)));
        v.set(34, d.toString());

        //v.set(15, String.valueOf(rs.getLong(3) % rs.getLong(4)));

      }
      /**
       * 结余
       */

      String sql_suby1 = "SELECT SUM(ROUND(YD" + String.valueOf(month) +
          ",2)-ROUND(yj" + String.valueOf(month) +
          ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '306%'" + l;

      String sql_suby12 = "SELECT  (" + sql_suby1 + "),SUM(ROUND(YD" +
          String.valueOf(smonth) +
          ",2)-ROUND(yj" + String.valueOf(smonth) +
          ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '306%'" +
          l;
      System.out.println(sql_suby12);
      rs = db.queryAll(sql_suby12);
      if (rs.next()) {
        t1 = rs.getString(1) != null ? rs.getString(1) : "0";
        t11 = rs.getString(2) != null ? rs.getString(2) : "0";
        v.set(36, t1);
        v.set(37, t11);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t1)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t11)));

        v.set(38, d.toString());

      }

    }
    catch (DbException de) {
      de.printStackTrace();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return v;

  }

  public synchronized Vector getHuanbi_sy_zc(String dwdm, String qijian) {
    Vector v = new Vector();
    for (int i = 0; i < 44; i++) {
      v.add(i, "0");
    }
    if (!qijian.equals("0")) {
      month = Integer.parseInt(qijian);
      smonth = month - 1;
      if (smonth == 0) {
        skjnd = "2999";
        smonth = 1;
      }

    }
    String l = " AND CN_DWDM IN (select dwdm from cn_dwsub where gl_dwxz='1')";
    if (!dwdm.equals("0")) {
      l = " AND CN_DWDM='" + dwdm + "'";
    }
    ResultSet rs = null;
    String sql_sub1 = "SELECT SUM(ROUND(yj" + String.valueOf(month) +
        ",2)-ROUND(yd" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '5%'" + l;

    String sql_sub12 = "SELECT SUM(ROUND(Yj" + String.valueOf(smonth) +
        ",2)-ROUND(yd" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + skjnd + "' AND KMDM LIKE '5%'" + l;
    String sql_sub2 = "SELECT SUM(ROUND(yj" + String.valueOf(month) +
        ",2)-ROUND(yd" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '503%'" + l;
    String sql_sub22 = "SELECT SUM(ROUND(yj" + String.valueOf(smonth) +
        ",2)-ROUND(yd" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '503%'" + l;
    String sql_sub3 = "SELECT SUM(ROUND(yj" + String.valueOf(month) +
        ",2)-ROUND(yd" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '501%'" + l;
    String sql_sub32 = "SELECT SUM(ROUND(yj" + String.valueOf(smonth) +
        ",2)-ROUND(yd" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '501%'" + l;
    //拨出专款
    String sql_sub4 = "SELECT SUM(ROUND(yj" + String.valueOf(month) +
        ",2)-ROUND(yd" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '502%'" + l;
    String sql_sub42 = "SELECT SUM(ROUND(yj" + String.valueOf(smonth) +
        ",2)-ROUND(yd" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '502%'" + l;
    //上缴上级支出
    String sql_sub5 = "SELECT SUM(ROUND(yj" + String.valueOf(month) +
        ",2)-ROUND(yd" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '506%'" + l;
    String sql_sub52 = "SELECT SUM(ROUND(yj" + String.valueOf(smonth) +
        ",2)-ROUND(yd" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '506%'" + l;
    //对附属单位补助
    String sql_sub6 = "SELECT SUM(ROUND(yj" + String.valueOf(month) +
        ",2)-ROUND(yd" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '517%'" + l;
    String sql_sub62 = "SELECT SUM(ROUND(yj" + String.valueOf(smonth) +
        ",2)-ROUND(yd" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '517%'" + l;
    //事业支出
    String sql_sub7 = "SELECT SUM(ROUND(yj" + String.valueOf(month) +
        ",2)-ROUND(yd" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '504%'" + l;
    String sql_sub72 = "SELECT SUM(ROUND(yj" + String.valueOf(smonth) +
        ",2)-ROUND(yd" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '504%'" + l;
    //事业支出-基本支出
    String sql_sub8 = "SELECT SUM(ROUND(yj" + String.valueOf(month) +
        ",2)-ROUND(yd" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '50401%'" + l;
    String sql_sub82 = "SELECT SUM(ROUND(yj" + String.valueOf(smonth) +
        ",2)-ROUND(yd" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '50401%'" + l;

//事业支出-专项支出
    String sql_sub9 = "SELECT SUM(ROUND(yj" + String.valueOf(month) +
        ",2)-ROUND(yd" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '50405%'" + l;
    String sql_sub92 = "SELECT SUM(ROUND(yj" + String.valueOf(smonth) +
        ",2)-ROUND(yd" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '50405%'" + l;
    //经营支出
    String sql_sub10 = "SELECT SUM(ROUND(yj" + String.valueOf(month) +
        ",2)-ROUND(yd" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '505%'" + l;
    String sql_sub102 = "SELECT SUM(ROUND(yj" + String.valueOf(smonth) +
        ",2)-ROUND(yd" + String.valueOf(smonth) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '505%'" + l;
    //结转自筹基建
    String sql_sub103 = "SELECT SUM(ROUND(yj" + String.valueOf(month) +
        ",2)-ROUND(yd" + String.valueOf(month) +
        ",2)) FROM GL_YEB WHERE KJND='" + kjnd + "' AND KMDM LIKE '520%'" + l;

    String sql_t1 = "SELECT (" + sql_sub1 + "),(" + sql_sub12 + "),(" +
        sql_sub2 + "),(" + sql_sub22 + "),(" + sql_sub3 +
        "),(" + sql_sub32 + "),(" + sql_sub4 + "),(" + sql_sub42 + "),(" +
        sql_sub5 + "),(" + sql_sub52 + "),(" + sql_sub6 + "),(" + sql_sub62 +
        "),(" + sql_sub7 + "),(" + sql_sub72 + "),(" + sql_sub8 + "),(" +
        sql_sub82 + "),(" +
        sql_sub9 + "),(" + sql_sub92 + "),(" + sql_sub10 + "),(" + sql_sub102 +
        "),(" + sql_sub103 + "),sum(round(yj" + String.valueOf(smonth) +
        ",2)-ROUND(yd" +
        String.valueOf(smonth) + ",2)) FROM GL_YEB  where kjnd='" +
        skjnd + "' and  KMDM LIKE '520%'" + l;
    try {
      System.out.println(sql_t1);
      db = new DataBase();
      db.connectDb();
      rs = db.queryAll(sql_t1);
      String t1 = "", t11 = "", t2 = "", t22 = "", t3 = "", t33 = "", t4 = "",
          t44 = "", t5 = "", t52 = "", t6 = "", t62 = "", t7 = "", t72 = "",
          t8 = "", t82 = "", t9 = "", t92 = "", t10 = "", t102 = "", t103 = "",
          t1032 = "";
      BigDecimal d = new BigDecimal("00000000000000.00");

      if (rs.next()) {
        t1 = rs.getString(1) != null ? rs.getString(1) : "0";
        t11 = rs.getString(2) != null ? rs.getString(2) : "0";
        t2 = rs.getString(3) != null ? rs.getString(3) : "0";
        t22 = rs.getString(4) != null ? rs.getString(4) : "0";
        t3 = rs.getString(5) != null ? rs.getString(5) : "0";
        t33 = rs.getString(6) != null ? rs.getString(6) : "0";
        t4 = rs.getString(7) != null ? rs.getString(7) : "0";
        t44 = rs.getString(8) != null ? rs.getString(8) : "0";
        t5 = rs.getString(9) != null ? rs.getString(9) : "0";
        t52 = rs.getString(10) != null ? rs.getString(10) : "0";
        t6 = rs.getString(11) != null ? rs.getString(11) : "0";
        t62 = rs.getString(12) != null ? rs.getString(12) : "0";
        t7 = rs.getString(13) != null ? rs.getString(13) : "0";
        t72 = rs.getString(14) != null ? rs.getString(14) : "0";
        t8 = rs.getString(15) != null ? rs.getString(15) : "0";
        t82 = rs.getString(16) != null ? rs.getString(16) : "0";
        t9 = rs.getString(17) != null ? rs.getString(17) : "0";
        t92 = rs.getString(18) != null ? rs.getString(18) : "0";
        t10 = rs.getString(19) != null ? rs.getString(19) : "0";
        t102 = rs.getString(20) != null ? rs.getString(20) : "0";
        t103 = rs.getString(21) != null ? rs.getString(21) : "0";
        t1032 = rs.getString(22) != null ? rs.getString(22) : "0";

        v.set(0, t1);
        v.set(1, t11);
        d = d.add(new BigDecimal(Double.parseDouble(t1)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t11)));

        v.set(2, d.toString());
        //v.set(3, String.valueOf(rs.getLong(1) % rs.getLong(2)));
        v.set(4, t2);
        v.set(5, t22);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t2)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t22)));
        v.set(6, d.toString());
        v.set(8, t3);
        v.set(9, t33);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t3)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t33)));
        v.set(10, d.toString());
        v.set(12, t4);
        v.set(13, t44);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t4)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t44)));
        v.set(14, d.toString());
        d = new BigDecimal("00000000000000.00");
        v.set(16, t5);
        v.set(17, t52);
        d = d.add(new BigDecimal(Double.parseDouble(t5)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t52)));

        v.set(18, d.toString());
        //v.set(3, String.valueOf(rs.getLong(1) % rs.getLong(2)));
        v.set(20, t6);
        v.set(21, t62);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t6)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t62)));
        v.set(22, d.toString());
        v.set(24, t7);
        v.set(25, t72);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t7)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t72)));
        v.set(26, d.toString());
        v.set(28, t8);
        v.set(29, t82);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t8)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t82)));
        v.set(30, d.toString());
        v.set(32, t9);
        v.set(33, t92);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t9)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t92)));
        v.set(34, d.toString());
        v.set(36, t10);
        v.set(37, t102);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t10)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t102)));
        v.set(38, d.toString());
        v.set(40, t103);
        v.set(41, t1032);
        d = new BigDecimal("00000000000000.00");
        d = d.add(new BigDecimal(Double.parseDouble(t103)));
        d = d.subtract(new BigDecimal(Double.parseDouble(t1032)));
        v.set(42, d.toString());

      }

    }
    catch (DbException de) {
      de.printStackTrace();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return v;

  }

}
