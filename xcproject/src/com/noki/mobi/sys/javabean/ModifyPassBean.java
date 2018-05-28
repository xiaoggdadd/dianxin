package com.noki.mobi.sys.javabean;

import java.sql.SQLException;
import java.sql.ResultSet;
import com.noki.database.DataBase;
import com.noki.util.MD5;
import com.noki.database.DbException;

public class ModifyPassBean {
  public ModifyPassBean() {
  }

  /**
   * �޸�����
   * @param oldPass String
   * @param newPass String
   * @return String
   */
  public synchronized String modifyPass(String oldPass, String newPass,
                                        String accountid) {
    String msg = "";
    DataBase db = new DataBase();
    MD5 md = new MD5();
    try {
      db.connectDb();

      if (validate(accountid, oldPass, md, db)) {
        String pass = md.getMD5ofStr(newPass);
        String sql = "UPDATE ACCOUNT SET PASSWORD='" + pass +
            "' WHERE ACCOUNTID='" + accountid + "'";
        if (db.update(sql) > 0) {
          return "�������޸ģ����ס���޸ĵ����룡";
        }
        else {
          return "�޸�����ʧ�ܣ������ԣ������ҹ���Ա��ϵ";
        }
      }
      else {
        //db.update(sql);
        return "����ľ����벻��ȷ��";
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
   * �ڲ�����
   * �ж�����ľ������Ƿ����¼��Ա���
   * @param accountid String
   * @param oldPass String
   * @param des DES
   * @param db DataBase
   * @return boolean
   */
  private boolean validate(String accountid, String oldPass, MD5 md,
                           DataBase db) {
    boolean sign = false;
    String sql = "SELECT PASSWORD FROM ACCOUNT WHERE ACCOUNTID=" + accountid;
    String pass = md.getMD5ofStr(oldPass);

    ResultSet rs = null;

    try {
      rs = db.queryAll(sql);
      while (rs.next()) {
        if (pass.equals(rs.getString(1))) {
          return true;
        }
      }
      rs.close();
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    catch (DbException dee) {
      dee.printStackTrace();
    }

    return sign;
  }

}
