package com.noki.page;

import com.noki.database.*;
import com.sun.rowset.CachedRowSetImpl;

import java.util.ArrayList;
import java.util.Vector;
import java.sql.*;
import java.util.*;
import javax.sql.RowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

abstract public class PageBean<OracleCachedRowSet> {
  public int curPage; //当前是第几页
  public int maxPage; //一共有多少页
  public int maxRowCount; //一共有多少行
  public int rowsPerPage = 15; //每页多少行
  public ArrayList data = null; //以arrayList封装rs
  //public CachedRowSetImpl crs=null;
  public ResultSet crs = null;
  public String sql = "";
  public PageBean() {
  }

  protected void countMaxPage() { //根据总行数计算总页数
    if (this.maxRowCount % this.rowsPerPage == 0) {
      this.maxPage = this.maxRowCount / this.rowsPerPage;
    }
    else {
      this.maxPage = this.maxRowCount / this.rowsPerPage + 1;
    }
  }

  /**
   * 设置每页多少行
   * @param rowsPerPage
   */
  public void setRowsPerPage(int rowsPerPage) {
    this.rowsPerPage = rowsPerPage;
  }

  /**
   * 查询一共有多少行
   * @param sql
   * @return
   */
  protected int getAvailableCount(String sql) {
    int ret = 0;
    sql = "select count(*) from (" + sql + ")c";
    DataBase db = null;
    try {
      db = new DataBase();
      db.connectDb();
      System.out.println("sql in:" + sql);
      ResultSet rs = db.queryAll(sql);
      while (rs.next()) {
        ret = rs.getInt(1);
      }
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
    return ret;
  }

  protected ArrayList resultSetToList(ResultSet rs) {
    ArrayList list = new ArrayList();
    try {
      ResultSetMetaData rsmd = rs.getMetaData();
      int m = rsmd.getColumnCount();
      //添加0字段元素
      list.add(new Integer(m));
      //添加每个字段名称
      for (int i = 1; i <= m; i++) {
        list.add(rsmd.getColumnName(i).toUpperCase());
      }
      //添加具体数据
      while (rs.next()) {
        for (int i = 1; i <= m; i++) {
          list.add(rs.getString(i));
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  protected ArrayList resultSetToList(ResultSet rs, int begin) {
    ArrayList list = new ArrayList();
    try {
      ResultSetMetaData rsmd = rs.getMetaData();
      int m = rsmd.getColumnCount();
      //添加0字段元素
      list.add(new Integer(m));
      //添加每个字段名称
      for (int i = 1; i <= m; i++) {
        list.add(rsmd.getColumnName(i).toUpperCase());
      }
      //添加具体数据
      int n = 1;
      while (rs.next()) {
        if (n >= begin) {
          for (int i = 1; i <= m; i++) {
            list.add(rs.getString(i));
          }
        }
        n++;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  protected ArrayList resultSetToRowSet(ResultSet rs) {
    ArrayList list = new ArrayList();
    try {
      // create CachedRowSet and populate
      // crs = new CachedRowSetImpl();
      //crs.populate(rs);
      crs = rs;

    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  protected ArrayList resultSetToRowSet(ResultSet rs, int begin) {
    ArrayList list = new ArrayList();
    try {
     //  create CachedRowSet and populate
      crs=rs;
      //crs.populate(rs,begin);
    	//OracleCachedRowSet  orcss =new OracleCachedRowSet();
    	
     
    
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public ArrayList getResult() {
    return this.data;
  }

  public ResultSet getRsResult() {
    return this.crs;
  }

  abstract public PageBean listData(String sql, String page);

  /**
   * 根据list里的id查询出所有id的内容
   * @param list
   * @return
   */
  abstract public ArrayList listData(HttpServletRequest request);

  /**
   * 全选，并把主关键子存入selectedlist中
   * @param request
   * @return
   */
  public boolean selectAll(HttpServletRequest request,
                           HttpServletResponse response) {
    HttpSession session = request.getSession();

    String keyforpage = (String) session.getAttribute("keyforpage");
    String keytype = (String) session.getAttribute("keyforpagetype");
    if (keyforpage == null || keyforpage.trim().equals("")) {
      return false;
    }
    if (keytype == null || keyforpage == null) {
      return false;
    }
    String listname = (String) session.getAttribute("listname");
    if (listname == null || listname.trim().equals("")) {
      listname = "selectedlist";
      session.setAttribute("listname", listname);
    }

    ArrayList list = (ArrayList) session.getAttribute(listname);
    if (list == null) {
      list = new ArrayList();
    }

    list.clear();
    String sql = "select * from (" + this.sql + ") c ";

    System.out.println(sql);
    DataBase db = null;
    ResultSet rs = null;
    try {
      db = new DataBase();
      db.connectDb();
      rs = db.queryAll(sql);
      while (rs.next()) {
        String id = (String) rs.getString(keyforpage);
        list.add(id);
      }
      session.setAttribute(listname, list);

    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    finally {
      if (db != null) {
        try {
          db.close();
        }
        catch (Exception e2) {
          e2.printStackTrace();
          return false;
        }
      }
    }
    return true;
  }

  public boolean removeAll(HttpServletRequest request,
                           HttpServletResponse response) {
    HttpSession session = request.getSession();

    String listname = (String) session.getAttribute("listname");
    if (listname == null || listname.trim().equals("")) {
      listname = "selectedlist";
      session.setAttribute("listname", listname);
    }

    ArrayList list = (ArrayList) session.getAttribute(listname);

    if (list == null) {
      list = new ArrayList();
    }
    list.clear();
    session.setAttribute(listname, list);
    return true;
  }

  /**
   * 当数据提交到servlet,进行处理时，必须调用此方法，获得提交的ids
   * @param request
   * @param response
   * @return 没有没有提交
   */
  public static String[] getSubmitIds(HttpServletRequest request,
                                      HttpServletResponse response,
                                      boolean sessiondel) {
    HttpSession session = request.getSession();

    PageBean page_for_pageCtl = (PageBean) session.getAttribute("pageCtl");
    if (sessiondel) {
      session.removeAttribute("pageCtl");
    }

    String page_for_keyforpage = (String) session.getAttribute("keyforpage");
    String page_for_controlname = (String) session.getAttribute("controlname");
    String[] page_ids = request.getParameterValues("page_for_ids");
    String[] actual_ids = request.getParameterValues(page_for_controlname);

    String listname = (String) session.getAttribute("listname");
    if (listname == null || listname.trim().equals("")) {
      listname = "selectedlist";
      session.setAttribute("listname", listname);
    }
    System.out.println("listname==" + listname);
    ArrayList selectedlist = (ArrayList) session.getAttribute(listname);

    String canfenyesubmit = (String) session.getAttribute("canfenyesubmit");
    if (selectedlist == null) {
      selectedlist = new ArrayList();
    }

    if (sessiondel) {
      session.removeAttribute("keyforpage");
      session.removeAttribute("keyforpagetype");
      session.removeAttribute("formname");
      session.removeAttribute("controlname");
      session.removeAttribute(listname);
      session.removeAttribute("canfenyesubmit");
      session.removeAttribute("listname");
    }
    //System.out.println("actual_ids:"+actual_ids+"controlname:"+page_for_controlname);
    //System.out.println(actual_ids.length);

    if (page_for_controlname == null || page_for_controlname.trim().equals("") ||
        page_for_keyforpage == null || page_for_keyforpage.trim().equals("")) {
      session.removeAttribute("pageCtl");
      session.removeAttribute("keyforpage");
      session.removeAttribute("keyforpagetype");
      session.removeAttribute("formname");
      session.removeAttribute("controlname");
      session.removeAttribute(listname);
      session.removeAttribute("canfenyesubmit");
      session.removeAttribute("listname");
      return null;
    }

    if (page_for_pageCtl != null && page_for_pageCtl.maxPage > 1) { //如果是多页显示
      //System.out.println("ddddddd777777777");
      if (page_for_keyforpage != null && !page_for_keyforpage.trim().equals("")
          && canfenyesubmit != null && canfenyesubmit.trim().equals("1")) { //分页提交功能

        ArrayList actual_ids_list = new ArrayList();
        //System.out.println(page_ids.length+" "+actual_ids.length);
        for (int i = 0; actual_ids != null && i < actual_ids.length; i++) {
          String id = actual_ids[i];
          if (!selectedlist.contains(id)) {
            selectedlist.add(id);
          }
          actual_ids_list.add(id);
        }

        for (int i = 0; page_ids != null && i < page_ids.length; i++) {
          String id = page_ids[i];
          if (selectedlist.contains(id) && !actual_ids_list.contains(id)) {
            selectedlist.remove(selectedlist.indexOf(id));
          }
        }
        //System.out.println()
      }
      else { //没有分页提交功能,把当前提交的页的ids放入seletedlist
        selectedlist.clear();

        for (int i = 0; actual_ids != null && i < actual_ids.length; i++) {
          selectedlist.add(actual_ids[i]);
        }
      }
    }
    else { //如果一页显示
      //System.out.println("dddddddddddddddd");
      selectedlist.clear();

      for (int i = 0; actual_ids != null && i < actual_ids.length; i++) {
        selectedlist.add(actual_ids[i]);

      }
    }
    String[] result = null;
    if (selectedlist != null) {
      result = new String[selectedlist.size()];
      for (int i = 0; selectedlist != null && i < selectedlist.size(); i++) {
        result[i] = (String) selectedlist.get(i);
      }
    }
    if (!sessiondel) {
      session.setAttribute(listname, selectedlist);
    }
    return result;

  }

  public static String[] getSubmitIds(HttpServletRequest request,
                                      HttpServletResponse response) {
    HttpSession session = request.getSession();

    PageBean page_for_pageCtl = (PageBean) session.getAttribute("pageCtl");

    session.removeAttribute("pageCtl");

    String page_for_keyforpage = (String) session.getAttribute("keyforpage");
    String page_for_controlname = (String) session.getAttribute("controlname");
    String[] page_ids = request.getParameterValues("page_for_ids");
    String[] actual_ids = request.getParameterValues(page_for_controlname);

    String listname = (String) session.getAttribute("listname");
    if (listname == null || listname.trim().equals("")) {
      listname = "selectedlist";
      session.setAttribute("listname", listname);
    }
    System.out.println("listname==" + listname);
    ArrayList selectedlist = (ArrayList) session.getAttribute(listname);

    String canfenyesubmit = (String) session.getAttribute("canfenyesubmit");
    if (selectedlist == null) {
      selectedlist = new ArrayList();
    }

    session.removeAttribute("keyforpage");
    session.removeAttribute("keyforpagetype");
    session.removeAttribute("formname");
    session.removeAttribute("controlname");
    session.removeAttribute(listname);
    session.removeAttribute("canfenyesubmit");
    session.removeAttribute("listname");

    //System.out.println("actual_ids:"+actual_ids+"controlname:"+page_for_controlname);
    //System.out.println(actual_ids.length);

    if (page_for_controlname == null || page_for_controlname.trim().equals("") ||
        page_for_keyforpage == null || page_for_keyforpage.trim().equals("")) {
      session.removeAttribute("pageCtl");
      session.removeAttribute("keyforpage");
      session.removeAttribute("keyforpagetype");
      session.removeAttribute("formname");
      session.removeAttribute("controlname");
      session.removeAttribute(listname);
      session.removeAttribute("canfenyesubmit");
      session.removeAttribute("listname");
      return null;
    }

    selectedlist.clear();

    for (int i = 0; actual_ids != null && i < actual_ids.length; i++) {
      selectedlist.add(actual_ids[i]);

    }

    String[] result = null;
    if (selectedlist != null) {
      result = new String[selectedlist.size()];
      for (int i = 0; selectedlist != null && i < selectedlist.size(); i++) {
        result[i] = (String) selectedlist.get(i);
      }
    }

    return result;

  }

  public boolean reverse(HttpServletRequest request,
                         HttpServletResponse response) {
    HttpSession session = request.getSession();

    String keyforpage = (String) session.getAttribute("keyforpage");
    String keytype = (String) session.getAttribute("keyforpagetype");
    if (keyforpage == null || keyforpage.trim().equals("")) {
      return false;
    }
    if (keytype == null || keyforpage == null) {
      return false;
    }
    String listname = (String) session.getAttribute("listname");
    if (listname == null || listname.trim().equals("")) {
      listname = "selectedlist";
      session.setAttribute("listname", listname);
    }

    ArrayList list = (ArrayList) session.getAttribute(listname);
    if (list == null) {
      list = new ArrayList();
    }

    String sql = "select * from (" + this.sql + ") where 1=1";
    for (int i = 0; i < list.size(); i++) {
      String id = (String) list.get(i);
      if ("String".toUpperCase().equals(keytype.toUpperCase())) {
        sql = sql + " and " + keyforpage + "!='" + id + "'";
      }
      else if ("int".toUpperCase().equals(keytype.toUpperCase())) {
        sql = sql + " and " + keyforpage + "!=" + id;
      }
      else {
        return false;
      }

    }
    System.out.println(sql);
    DataBase db = null;
    ResultSet rs = null;
    try {
      list.clear();
      db = new DataBase();
      db.connectDb();
      rs = db.queryAll(sql);
      while (rs.next()) {
        String id = (String) rs.getString(keyforpage);
        list.add(id);
      }
      session.setAttribute(listname, list);

    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    finally {
      if (db != null) {
        try {
          db.close();
        }
        catch (Exception e2) {
          e2.printStackTrace();
          return false;
        }
      }
    }
    return true;
  }

}
