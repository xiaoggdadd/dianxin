package com.noki.page;

import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.noki.database.DataBase;

;

public class MsSqlPageBean
    extends PageBean {

  /**
   * page Ϊ0��""ʱ����ȫ��
   */
  /*
   //��������
    SELECT   IDENTITY(int,   1,1)   AS   IDd, id,name
    INTO   #Temp
    FROM   test
    SELECT   *   FROM   #Temp
    drop table #temp

    //ms sql��ҳ��sql���
    select top ҳ��С * from table1
        where id>
        (select max (id) from
        (select top ((ҳ��-1)*ҳ��С) id from table1 order by id) as T
        )
        order by id
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
      String a = "select top " + end + " * from " +
          "(" + sql + ") t";
      //System.out.println("fuck a =="+a);
      ResultSet rs = db.queryAlls(a);

      ArrayList list = this.resultSetToList(rs, start);
      this.data = list;
      rs.beforeFirst(); //��������Ҫ����ҳ�Ŀ�ʼ
      this.resultSetToRowSet(rs, start);

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
   * ����sql�Ĳ�ѯ�����
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
      s = "select c.* from (" + sql + ")c";
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
    PageBean bean = new MsSqlPageBean();
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
   * ����session��selectlist������idָʾ����
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
      session.setAttribute("listname", listname);
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
    sql = "select d.* from (" + sql + ") d ";
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
