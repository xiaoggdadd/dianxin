package com.noki.page;

import java.sql.*;

import com.noki.database.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


;

public class OraclePageBean
    extends PageBean {

  /**
   * page 为0或""时返回全部
   */
  public PageBean listData(String sql, String page) {
    // TODO Auto-generated method stub

    this.sql = sql;

    if (page == null || page.trim().equals("")) {
      return listData(sql);
    }

    DataBase db = null;
    try {

      this.maxRowCount = this.getAvailableCount(sql);
      this.countMaxPage();
      int intPage = Integer.parseInt(page);
      this.curPage = intPage;
      if (intPage <= 0) {
        return listData(sql);
      }

      int start = this.rowsPerPage * (intPage - 1) + 1;
      int end = this.rowsPerPage * (intPage);

      db = new DataBase();
      db.connectDb();
      String s = "select * from " +
          "(select c.*,rownum num from " +
          "(" + sql + ") c" +
          ")d where num>=" + start + " and num<=" + end;
      System.out.println("sql:" + s);
      ResultSet rs = db.queryAlls(s);
     
      ArrayList list = this.resultSetToList(rs);
      System.out.println("2");
      this.data = list;
      

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (db != null) {
        try {
          db.close();
        }
        catch (Exception e2) {
          e2.printStackTrace();
        }
      }
    }
    return this;
  }

  /**
   * 返回sql的查询结果集
   * @param sql
   * @return
   */
  private PageBean listData(String sql) {
    // TODO Auto-generated method stub
    DataBase db = null;
    try {
      this.maxPage = 1;
      this.curPage = 1;
      this.maxRowCount = this.getAvailableCount(sql);
      this.rowsPerPage = this.maxRowCount;

      db = new DataBase();
      db.connectDb();

      String s = sql;
      s = "select c.*,rownum num from (" + sql + ")c";
      ResultSet rs = db.queryAlls(s);

      ArrayList list = this.resultSetToList(rs);
      this.data = list;
      rs.beforeFirst();
      this.resultSetToRowSet(rs);

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (db != null) {
        try {
          db.close();
        }
        catch (Exception e2) {
          e2.printStackTrace();
        }
      }
    }
    return this;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    PageBean bean = new OraclePageBean();
    String sql = "select diologtype.name,diolog.diologtype,diolog.diologid,diolog.content from diolog,diologtype where diologtype.code=diolog.diologtype";
    //bean.setRowsPerPage(10);
    bean = bean.listData(sql, "1");
    ArrayList alist = bean.getResult();
    int k = ( (Integer) alist.get(0)).intValue();
    for (int i = k; i < alist.size() - 1; i += k) {
      String id = (String) alist.get(i + alist.indexOf("num"));
      System.out.println(id);
    }
    System.out.println("maxpage:" + bean.maxPage);
    System.out.println("curpage:" + bean.curPage);
    System.out.println("maxRowCount" + bean.maxRowCount);

    ArrayList list = new ArrayList();

    String e1 = "2345";
    String e2 = "123";
    String e3 = "6";
    String e4 = "123";
    list.add(e1);
    list.add(e2);
    list.add(e3);
    System.out.println(list.size());
    if (list.contains("123")) {
      System.out.println("ok");
      System.out.println(list.remove(list.indexOf("123")));
    }

    //System.out.println(ss[0]);


  }

  /**
   * 返回session中selectlist中所有id指示的行
   * @param request
   * @return
   */
  public ArrayList listData(HttpServletRequest request) {
    HttpSession session = request.getSession();

    String keyforpage = (String) session.getAttribute("keyforpage");
    String keytype = (String) session.getAttribute("keyforpagetype");
    if (keytype == null || keyforpage == null) {
      return null;
    }
    //ArrayList list=(ArrayList)session.getAttribute("selectedlist");
    String listname = (String) session.getAttribute("listname");
    if (listname == null || listname.trim().equals("")) {
      listname = "selectedlist";
      //session.setAttribute("listname",listname);
    }

    ArrayList list = (ArrayList) session.getAttribute(listname);
    ArrayList relist = new ArrayList();
    String sql = "select c.* from (" + this.sql + ") c where ";
    if (list == null || list.size() == 0) {
      return relist;
    }
    if (keytype == null || keytype.trim().equals("") || keyforpage == null ||
        keyforpage.trim().equals("")) {
      return relist;
    }
    for (int i = 0; i < list.size(); i++) {
      String id = (String) list.get(i);
      if ("String".toUpperCase().equals(keytype.toUpperCase())) {
        sql = sql + " " + keyforpage + "='" + id + "'";
      }
      else if ("int".toUpperCase().equals(keytype.toUpperCase())) {
        sql = sql + " " + keyforpage + "=" + id;
      }
      else {
        return relist;
      }
      if (i != list.size() - 1) {
        sql = sql + " or ";
      }
    }
    sql = "select d.*,rownum num from (" + sql + ") d ";
    System.out.println(sql);
    DataBase db = null;
    ResultSet rs = null;
    try {
      db = new DataBase();
      db.connectDb();
      rs = db.queryAll(sql, ResultSet.
                       TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      relist = this.resultSetToList(rs);
      rs.beforeFirst();
      this.resultSetToRowSet(rs);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (db != null) {
        try {
          db.close();
        }
        catch (Exception e2) {
          e2.printStackTrace();
        }
      }
    }
    return relist;
  }
}
