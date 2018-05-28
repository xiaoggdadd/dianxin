package com.noki.mobi.sys.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class AccountFormBean {
  private String accountName;
  private String name;
  private String mobile;
  private String tel;
  private String roleName;
  private String address;
  private String sex;
  private String position;
  private String email;
  private String memo;
  private int accountId;
  private String roleId;
  private String buMen;
  private String zip;
  private String CTHRNUMBER;
    public String getCTHRNUMBER() {
	return CTHRNUMBER;
}

public void setCTHRNUMBER(String cTHRNUMBER) {
	CTHRNUMBER = cTHRNUMBER;
}

	private String xiaoqu;
    public AccountFormBean() {
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }



  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }



  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  /**
   * 账户明细与修改界面调用此方法
   * @param accountId String
   * @return AccountFormBean
   */
  public synchronized AccountFormBean getAccountInfo(String accountId) {
    AccountFormBean bean = new AccountFormBean();
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT A.ACCOUNTID,A.ACCOUNTNAME,A.ZIP,A.NAME,A.POSITION,A.ADDRESS,A.MOBILE,A.TEL,A.EMAIL,A.SEX");

    sql.append(",A.ROLEID,A.ROLENAME, A.bumen,A.MEMO,A.XIAOQU,A.CTHRNUMBER FROM ACCOUNT A WHERE  A.ACCOUNTID=" +
               accountId);

    DataBase db = new DataBase();
    
    System.out.println("sql>>>>>>>>>"+sql);
    try {
      db.connectDb();
      ResultSet rs = null;
      rs = db.queryAll(sql.toString());
      while (rs.next()) {

        bean.setAccountId(rs.getInt(1));
        bean.setAccountName(rs.getString(2));
        bean.setZip(rs.getString(3) != null ? rs.getString(3) : "");
        bean.setName(rs.getString(4));

        bean.setPosition(rs.getString(5) != null ? rs.getString(5) : "");
        bean.setAddress(rs.getString(6) != null ? rs.getString(6) : "");
        bean.setMobile(rs.getString(7) != null ? rs.getString(7) : "");
        bean.setTel(rs.getString(8) != null ? rs.getString(8) : "");
        bean.setEmail(rs.getString(9) != null ? rs.getString(9) : "");
        bean.setSex(rs.getString(10));

        bean.setRoleId(rs.getString(11));
        bean.setRoleName(rs.getString(12));
        bean.setBuMen(rs.getString(13) != null ? rs.getString(13) : "");
        bean.setMemo(rs.getString(14) != null ? rs.getString(14) : "");
        bean.setXiaoqu(rs.getString(15));
        bean.setCTHRNUMBER(rs.getString(16) != null ? rs.getString(16) : "");
      }
      rs.close();
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
    return bean;
  }

  public String getBuMen() {
    return buMen;
  }

  public void setBuMen(String buMen) {
    this.buMen = buMen;
  }

  public String getZip() {
    return zip;
  }

    public String getXiaoqu() {
        return xiaoqu;
    }

    public void setZip(String zip) {
    this.zip = zip;
  }

    public void setXiaoqu(String xiaoqu) {
        this.xiaoqu = xiaoqu;
    }

}
