package com.noki.mobi.sys.javabean;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import com.noki.database.DataBase;
import com.noki.database.DbException;

public class RightBean {
  public RightBean() {
  }

  /**
   * ����ɫ����Ȩ��
   * @param roleId String
   * @param rightId String[]
   * @return String
   */
  public synchronized String matchRoleRight(String roleId, String[] rightId,String rws) {
    String msg = "����ɫ����Ȩ��ʧ�ܣ�";
    String del = "DELETE FROM ROLE_RIGHT WHERE ROLEID=" + roleId;

    DataBase db = new DataBase();
    try {
      db.connectDb();
      msg = "����ɫ " + getRoleName(db, roleId) + " ����Ȩ��ʧ��";
      db.setAutoCommit(false);
      db.update(del);
      System.out.println("roleid:"+roleId+" rightid:"+rightId);
      if (rightId != null) {
        String[] sql = new String[rightId.length];
        for (int i = 0; i < rightId.length; i++) {
          sql[i] =
              "INSERT INTO ROLE_RIGHT(ROLEID,RIGHTID,OPERATIONRIGHT) VALUES(" +
              roleId + ",'" + rightId[i] + "',"+rws+")";
         System.out.println("���ܣ�"+rightId[i]);
         System.out.println("sql��"+sql[i]);
        }
      
        
        db.updateBatch(sql);
        System.out.println("���书��Ȩ��sql:"+sql.toString());
      }
      db.commit();
      msg = "����ɫ " + getRoleName(db, roleId) + " ����Ȩ�޳ɹ���";
    }
    catch (DbException de) {
      try {
        db.rollback();
      }
      catch (DbException dee) {
        dee.printStackTrace();
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

  private String getRoleName(DataBase db, String roleId) {
    ResultSet rs = null;
    String returnmsg = "";
    try {
      rs = db.queryAll("SELECT NAME FROM ROLE WHERE ROLEID=" + roleId);
      while (rs.next()) {
        returnmsg = rs.getString(1);
      }
    }
    catch (DbException d) {
      d.printStackTrace();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    return returnmsg;
  }

  /**
   * ��ɫ��д���ʲ���
   * @param roleId String
   * @param rRight String[]
   * @param wRight String[]
   * @param wdRight String[]
   * @return String
   */
  public synchronized String matchRWD(String roleId, String[] rRight,
                                      String[] wRight, String[] wdRight,String[] shRight) {
    String msg = "��ɫ��д���ʲ���ʧ�ܣ�";
    StringBuffer sql = new StringBuffer(), sql2 = new StringBuffer(),
        sql3 = new StringBuffer();

    DataBase db = new DataBase();
    try {
      db.connectDb();
      if (rRight != null) {
        sql.setLength(0);
        sql.append("UPDATE ROLE_RIGHT SET OPERATIONRIGHT=0 WHERE ROLEID=" +
                   roleId + " AND RIGHTID IN('");
        for (int i = 0; i < rRight.length; i++) {
          if (i != 0) {
            sql.append(",'" + rRight[i] + "'");
          }
          else {
            sql.append(rRight[i] + "'");
          }
        }
        sql.append(")");
        db.update(sql.toString());
      }
      if (wRight != null) {
        sql2.setLength(0);
        sql2.append("UPDATE ROLE_RIGHT SET OPERATIONRIGHT=1 WHERE ROLEID=" +
                    roleId + " AND RIGHTID IN('");
        for (int i = 0; i < wRight.length; i++) {
          if (i != 0) {
            sql2.append(",'" + wRight[i] + "'");
          }
          else {
            sql2.append(wRight[i] + "'");
          }
        }
        sql2.append(")");
        db.update(sql2.toString());
      }
      if (wdRight != null) {
        sql3.setLength(0);
        sql3.append("UPDATE ROLE_RIGHT SET OPERATIONRIGHT=2 WHERE ROLEID=" +
                    roleId + " AND RIGHTID IN('");
        for (int i = 0; i < wdRight.length; i++) {
          if (i != 0) {
            sql3.append(",'" + wdRight[i] + "'");
          }
          else {
            sql3.append(wdRight[i] + "'");
          }
        }
        sql3.append(")");
        db.update(sql3.toString());
      }
      if (shRight != null) {
        sql3.setLength(0);
        sql3.append("UPDATE ROLE_RIGHT SET OPERATIONRIGHT=3 WHERE ROLEID=" +
                    roleId + " AND RIGHTID IN('");
        for (int i = 0; i < shRight.length; i++) {
          if (i != 0) {
            sql3.append(",'" + shRight[i] + "'");
          }
          else {
            sql3.append(shRight[i] + "'");
          }
        }
        sql3.append(")");
        db.update(sql3.toString());
      }


      msg = "��ɫ��д���ʲ����ɹ���";
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
   * ���ݽ�ɫID�����Ѿ������Ȩ��
   * @param roleId
   * @return ArrayList
   */
  public synchronized ArrayList getRightByRoleId(String roleId) {
    ArrayList list = new ArrayList();
    StringBuffer sql = new StringBuffer();
    sql.setLength(0);
    sql.append("SELECT RR.RIGHTID,(SELECT R.NAME FROM [RIGHT] R WHERE R.RIGHTID=RR.RIGHTID) RIGHTNAME, OPERATIONRIGHT ");
    sql.append(
        " FROM ROLE_RIGHT RR WHERE RR.RIGHTID NOT LIKE '__00' AND RR.ROLEID=" +
        roleId + " ORDER BY RIGHTID");
    DataBase db = new DataBase();
    ResultSet rs = null;
    try {
      db.connectDb();
      rs = db.queryAll(sql.toString());
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

  /**
   * ��������Ȩ��
   * ����Ľ�ɫroleid�������ж�ѡ���벻ѡ�е�
   * @return ArrayList
   * update �����Ƿ���ò�ѯ���������ؿ���Ȩ�޲˵�  
   * �޸�ʱ�䣺2017��11��18��
   * �޸��ˣ� ����
   */
  @SuppressWarnings("unchecked")
public synchronized ArrayList getAllRights(String roleId) {
    ArrayList list = new ArrayList();
    StringBuffer sql = new StringBuffer();
    sql.setLength(0);
    sql.append("SELECT T.RIGHTID,T.NAME,T.WAPORWEB,T.URL,T.BLOCK,T.ICON ");
    sql.append(
        ",CASE WHEN T.RIGHTID IN (SELECT R.RIGHTID FROM ROLE_RIGHT R WHERE R.ROLEID IN(" +
        roleId + ")) THEN '1' ELSE '0' END CHECKED ");
    sql.append(
        " FROM RIGHT T  WHERE (T.WAPORWEB=1 OR T.WAPORWEB=0) AND T.ISUSED=1 ORDER BY T.RIGHTID");
    DataBase db = new DataBase();
    System.out.println("======"+sql);
    ResultSet rs = null;
    try {
      db.connectDb();
      rs = db.queryAll(sql.toString());
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

}
