package com.noki.mobi.sys.javabean;

import java.util.ArrayList;
import com.noki.database.DbException;
import com.noki.database.DataBase;
import com.noki.util.Query;
import com.noki.util.CTime;
import java.sql.ResultSet;

public class Person_DWBean {
  public Person_DWBean() {
  }

  public ArrayList getAccounts() {
    ArrayList list = new ArrayList();
    StringBuffer sql = new StringBuffer();
    sql.append(
        "SELECT ACCOUNTID,RTRIM(NAME) NAME FROM ACCOUNT WHERE DELSIGN=1 ORDER BY NAME");

    DataBase db = new DataBase();

    try {
      db.connectDb();
      Query quer = new Query();
      list = quer.query(db.queryAll(sql.toString()));

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

    return list;
  }

  public ArrayList getBM() {
    ArrayList list = new ArrayList();
    CTime time = new CTime();
    String kjnd = time.formatDate().substring(0, 4);
    StringBuffer sql = new StringBuffer();
    sql.append(
        "SELECT RTRIM(BMDM) BMDM,RTRIM(BMMC) BMMC FROM BM WHERE DELS=0 AND KJND='" +
        kjnd +
        "' ORDER BY BMMC");

    DataBase db = new DataBase();

    try {
      db.connectDb();
      Query quer = new Query();
      list = quer.query(db.queryAll(sql.toString()));

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

    return list;
  }

  public ArrayList getDW(String accountId, String bm) {
    ArrayList list = new ArrayList();
    CTime time = new CTime();
    String kjnd = time.formatDate().substring(0, 4);
    if (!bm.equals("0")) {
      StringBuffer sql = new StringBuffer();
      sql.append(
          "SELECT RTRIM(D.DWDM) DWDM,RTRIM(D.DWJC) DWJC,(SELECT ACCOUNTID FROM ACCOUNT_DW WHERE KJND='" +
          kjnd + "' AND ACCOUNTID=" + accountId + " AND DWDM=D.DWDM)");
      sql.append(" ACCOUNTID FROM DW D WHERE KJND='" + kjnd + "' AND BM_BMDM='" +
                 bm + "'");
      //System.out.println(sql.toString());
      DataBase db = new DataBase();

      try {
        db.connectDb();
        Query quer = new Query();
        list = quer.query(db.queryAll(sql.toString()));

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

    return list;
  }

  public synchronized int fenpeiAccountDw(String[] dwdm, String bm,
                                          String accountId) {
    int msg = 0;
    CTime time = new CTime();
    String kjnd = time.formatShortDate().substring(0, 4);
    String del = "DELETE FROM ACCOUNT_DW WHERE ACCOUNTID=" + accountId +
        " AND DWDM IN(SELECT DWDM FROM DW WHERE BM_BMDM='" + bm +
        "' AND KJND='" + kjnd + "')";

    DataBase db = new DataBase();
    try {
      String[] sql = null;
      if (dwdm != null) {
        int len = dwdm.length;
        sql = new String[len];

        for (int i = 0; i < len; i++) {
          sql[i] = "INSERT INTO ACCOUNT_DW(ACCOUNTID,DWDM,KJND) VALUES(" +
              accountId + ",'" + dwdm[i] + "','" + kjnd + "')";

        }

      }

      db.connectDb();
      db.setAutoCommit(false);
      db.update(del);
      if (dwdm != null) {
        for (int i = 0; i < dwdm.length; i++) {
          db.update(sql[i]);
        }
      }
      db.commit();
      msg = 1;
    }
    catch (DbException de) {
      msg = 0;
      try {
        System.out.println("rollback()");
        db.rollback();
      }
      catch (DbException ded) {
        ded.printStackTrace();
      }
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

  public ArrayList getPersonDW(String accountId) {
    ArrayList list = new ArrayList();
    CTime time = new CTime();
    String kjnd = time.formatShortDate().substring(0, 4);
    StringBuffer sql = new StringBuffer();
    sql.append(
        "SELECT (SELECT BMMC FROM BM WHERE BMDM=BM_BMDM AND KJND='" + kjnd + "') BMMC,DWJC FROM DW WHERE DWDM IN(SELECT DWDM FROM ACCOUNT_DW WHERE ACCOUNTID=" +
        accountId + " AND KJND='" + kjnd +
        "') AND KJND='2008' AND DELS=0 ORDER BY BM_BMDM");
    //System.out.println(sql.toString());
    DataBase db = new DataBase();

    try {
      db.connectDb();
      Query quer = new Query();
      list = quer.query(db.queryAll(sql.toString()));

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

    return list;
  }

  public String getPersonBM(String accountId) {
    String list = new String();
    list = ",";
    CTime time = new CTime();
    String kjnd = time.formatShortDate().substring(0, 4);
    StringBuffer sql = new StringBuffer();
    sql.append(
        "SELECT RTRIM(BMDM) FROM PERSON_BM WHERE ACCOUNTID=" +
        accountId);
    //System.out.println(sql.toString());
    DataBase db = new DataBase();

    try {
      db.connectDb();
      ResultSet rs = null;
      rs = db.queryAll(sql.toString());
      while (rs.next()) {
        list += rs.getString(1) + ",";
      }
    }

    catch (Exception de) {
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

    return list;
  }

  public synchronized int fenpeiAccountBM(String[] bmdm, String accountId) {
    int msg = 0;
    CTime time = new CTime();
    String kjnd = time.formatShortDate().substring(0, 4);
    String del = "DELETE FROM PERSON_BM WHERE ACCOUNTID=" + accountId;

    DataBase db = new DataBase();
    try {
      String[] sql = null;
      if (bmdm != null) {
        int len = bmdm.length;
        sql = new String[len];

        for (int i = 0; i < len; i++) {
          sql[i] = "INSERT INTO PERSON_BM(ACCOUNTID,BMDM) VALUES(" +
              accountId + ",'" + bmdm[i] + "')";

        }

      }

      db.connectDb();
      db.setAutoCommit(false);
      db.update(del);
      if (bmdm != null) {
        for (int i = 0; i < bmdm.length; i++) {
          db.update(sql[i]);
        }
      }
      db.commit();
      msg = 1;
    }
    catch (DbException de) {
      msg = 0;
      try {
        System.out.println("rollback()");
        db.rollback();
      }
      catch (DbException ded) {
        ded.printStackTrace();
      }
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

}
