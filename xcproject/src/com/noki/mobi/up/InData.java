package com.noki.mobi.up;

import com.noki.database.DbException;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class InData {
  public InData() {
  }

  public synchronized ArrayList getAllAccount() {
    ArrayList list = new ArrayList();
    DataBase db = new DataBase();
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT ID,PNAME,PCOUNT FROM INDATA ORDER BY PNAME");

    ResultSet rs = null;
    try {
      db.connectDb();
      rs = db.queryAll(sql.toString());
      ResultSetMetaData rmd = rs.getMetaData();
      int count = rmd.getColumnCount();
      list.add(new Integer(count));
      for (int i = 1; i <= count; i++) {
        list.add(rmd.getColumnName(i).toUpperCase());
      }
      while (rs.next()) {
        for (int i = 1; i <= count; i++) {
          list.add(rs.getString(i));
        }
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

    return list;
  }

  public synchronized void delAllAccount() {

    DataBase db = new DataBase();
    StringBuffer sql = new StringBuffer();
    sql.append("delete FROM INDATA");

    ResultSet rs = null;
    try {
      db.connectDb();
      db.update(sql.toString());
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

  }

}
