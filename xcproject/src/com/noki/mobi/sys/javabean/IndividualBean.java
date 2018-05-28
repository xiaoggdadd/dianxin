package com.noki.mobi.sys.javabean;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class IndividualBean {
  public IndividualBean() {
  }

  /**
   * 修改个人信息
   * @param accountName String
   * @param name String
   * @param age String
   * @param address String
   * @param mobile String
   * @param email String
   * @param sex String
   * @param accountId String
   * @param tel String
   * @param birthday String
   * @return boolean
   */
  public synchronized String updateInidividual(String name,
                                               String zip,
                                               String address, String mobile,
                                               String email, String sex,
                                               String accountId, String tel) {
    String sign = "修改 " + name + " 个人信息失败！";
    StringBuffer sql = new StringBuffer();
    sql.setLength(0);
    sql.append("UPDATE ACCOUNT SET NAME='" +
               name + "',ZIP='" + zip +
               "',ADDRESS='" + address + "',")
        .append("MOBILE='" + mobile + "',EMAIL='" + email + "',SEX='" + sex +
                "',TEL='" + tel + "'")
        .append(" WHERE ACCOUNTID='" + accountId + "'");

    DataBase db = new DataBase();
    AccountBean bean = new AccountBean();
    try {
      db.connectDb();


      db.update(sql.toString());
      return "修改 " + name + " 个人信息成功！";
    }
    catch (DbException de) {

      de.printStackTrace();
    }
    finally {
      try {

        db.close();
      }
      catch (Exception de) {
        de.printStackTrace();
      }
    }

    return sign;
  }

}
