package com.noki.mobi.sys.javabean;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import com.noki.database.DataBase;
import com.noki.util.CTime;
import com.noki.database.DbException;
import com.noki.mobi.common.CommonBean;

public class NoticeBean {
  public NoticeBean() {
  }

  /**
   * �����¹���
   * @param title String
   * @param content String
   * @param structName String
   * @param oper String
   * @return String
   */
  public synchronized String addNotic(String title, String content, String oper) {
    String msg = "���湫��ʧ�ܣ�";
    DataBase db = new DataBase();
    CTime time = new CTime();
    try {
      db.connectDb();
      String sql =
          "INSERT INTO NOTICE(TITLE,CONTENT,CREATIME,CREATOR) VALUES('" +
          title + "','" + content + "','" +
          time.formatWholeDate() + "','" + oper + "')";
      //System.out.println(sql);
      if (db.update(sql) > 0) {
        msg = "���湫�� " + title + " �ɹ���";
      }
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

    return msg;
  }

  /**
   * ɾ������servlet���õķ���
   * @param noticeId String[]
   * @return String
   */

  private String[] noticeName;

  public synchronized String delNotice(String[] noticeId, String accountId) {
    String msg = "ɾ������ʧ�ܣ�";
    DataBase db = new DataBase();
    String name1 = "", name0 = "";
    int len = noticeId.length;
    try {
      db.connectDb();
    }
    catch (DbException de) {
      de.printStackTrace();
    }
    noticeName = getNoticesName(noticeId, db);
    String sql = "DELETE FROM NOTICE WHERE NOTICEID IN(";
    int j = 0;

    for (int i = 0; i < len; i++) {
      if (j > 0) {
        sql += "," + noticeId[i];
      }
      else {
        sql += noticeId[i];
      }
      j++;
    }
    sql += ")";

    //System.out.println(sql);
    try {

      if (db.update(sql) > 0) {

        return "ɾ������ " + name1 + " �ɹ���";

      }else{
        return "ɾ������ " + name1 + " ʧ�ܣ�";
      }

    }
    catch (DbException de) {
      msg = "ɾ������ " + name1 + "; " + name0 + " ʧ�ܣ�";
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

    return msg;
  }

  /**
   * ����Ҫɾ���Ĺ��������
   * @param id String[]
   * @return String[]
   */

  private String[] getNoticesName(String[] id, DataBase db) {
    int len = id.length;
    noticeName = new String[len];
    String sql = "SELECT TITLE FROM NOTICE WHERE NOTICEID IN(" +
        id[0];
    for (int i = 1; i < len; i++) {
      sql += "," + id[i];
    }
    sql += ")";
    ResultSet rs = null;
    try {
      rs = db.queryAll(sql);
      int j = 0;
      while (rs.next()) {
        noticeName[j] = rs.getString(1);
      }
    }
    catch (DbException de) {
      de.printStackTrace();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return noticeName;
  }

  /**
   * ���ع���
   * @param title String
   * @return ArrayList
   */
  public synchronized ArrayList getNotes(String title) {
    ArrayList list = new ArrayList();
    String sql = "SELECT N.NOTICEID,N.TITLE,N.CONTENT,N.CREATIME,N.CREATOR FROM NOTICE N ";
    if (title.length() > 0) {
      sql += " WHERE N.TITLE LIKE '%" + title + "%'";
    }
    sql += " ORDER BY N.CREATIME DESC ";
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
          if (i == 4) {
            list.add(rs.getString(i).substring(0, 10));
          }
          else {
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

}
