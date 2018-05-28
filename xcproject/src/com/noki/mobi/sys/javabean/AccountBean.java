package com.noki.mobi.sys.javabean;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.noki.database.*;
import com.noki.util.*;
import com.noki.page.NPageBean;

public class AccountBean {
    private int allPage;
    public AccountBean() {
    }

    /**
     * 添加账户
     * 以下是要添加的信息
     * @param accountName String
     * @param name String
     * @param roleid String
     * @param personType String
     * @param position String
     * @param age String
     * @param address String
     * @param mobile String
     * @param email String
     * @param sex String
     * @param organization String
     * @param code String
     * @param password String
     * @param tel String
     * @return String
     */
    public synchronized String addAccount(String accountName, String name,
                                          String[] roleid, String position,
                                          String zip,
                                          String address, String mobile,
                                          String email, String sex,
                                          String password, String tel,
                                          String memo, String bumen,
                                          String sheng, String shi, String xian,
                                          String xiaoqu,String cthrnumber) {
        //birthday = birthday.length()>0?birthday:null;
        String msg = "保存账户失败！请重试或与管理员联系！";
        MD5 md = new MD5();
        DataBase db = new DataBase();
        try {
            db.connectDb();

            CTime ctime = new CTime();
            String roles = "", rolesname = "";
            for (int k = 0; k < roleid.length; k++) {
                if (k == 0) {
                    roles = roleid[k];
                } else {
                    roles += "," + roleid[k];
                }
            }
            ResultSet rs = db.queryAll(
                    "select itemvalue from permission_configuration where itemname='InitPassword'");
            String newPass = "";
            while (rs.next()) {
                newPass = rs.getString(1).trim();
            }

            rolesname = this.getRolesName(db, roles);
            String pass = md.getMD5ofStr(newPass);
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO ACCOUNT(ACCOUNTNAME,NAME,ROLEID,POSITION,ZIP,ADDRESS,MOBILE,EMAIL,SEX,PASSWORD,TEL,ENABLE,MEMO,BUMEN,DELSIGN,REGISTTIME,SHENG,SHI,XIAN,XIAOQU,ROLENAME,cthrnumber)");
            sql.append(" VALUES('" + accountName + "'");
            sql.append(",'" + name + "','" + roles + "','" + position + "','" +
                       zip +
                       "','" + address + "'");
            sql.append(",'" + mobile + "','" + email + "','" + sex + "','" +
                       pass +
                       "','" + tel + "',1,'" + memo + "','" + bumen + "','1','" +
                       ctime.formatWholeDate(new Date()) + "','" + sheng +
                       "','" +
                       shi + "','" + xian + "','" + xiaoqu + "','" + rolesname +"','" + cthrnumber +
                       "')");
            // sql.append(",0,'"  "')");
            System.out.println(sql.toString());

          //  if (validateName("SELECT ACCOUNTID FROM ACCOUNT WHERE ACCOUNTNAME='" +
                 //   accountName + "'", db)) {
              //  return "此用户名已经存在，请另选择一个！";
         //   }
            db.setAutoCommit(false);
            db.update(sql.toString());
            db.setAutoCommit(true);
         
            msg = "添加账户 " + name + " 成功！";
        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } catch (SQLException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } finally {
            try {

                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }


    private String getRolesName(DataBase db, String roles) throws SQLException,
            DbException {
        String rolesname = "";
        ResultSet rs = db.queryAll("select name from role where roleid in(" +
                                   roles + ")");
        int k = 0;
        while (rs.next()) {
            if (k == 0) {
                rolesname = rs.getString(1);
            } else {
                rolesname += "," + rs.getString(1);
            }
            k++;
        }
        return rolesname;
    }

    /**
     * 删除账户
     * @param accountId String[]
     * @return String
     */

    public synchronized String delAccounts(String[] accountId) {
        String msg = "删除账号失败！";
        int count = accountId.length;
        DataBase db = new DataBase();

        try {
            db.connectDb();

            int k = 0;

            String sql1 = "UPDATE ACCOUNT SET DELSIGN=0 WHERE ACCOUNTID IN("; //删除账号表
            String sql2 = "DELETE FROM TBL_APP_USER_BD WHERE USERID IN (";
            String listAccount = "";
            for (int j = 0; j < count; j++) {

                if (k > 0) {
                    listAccount += "," + accountId[j];
                } else {
                    listAccount += accountId[j];
                }
                k++;

            }
            sql1 += listAccount + ")";
            sql2 += listAccount + ")";
            System.out.println(sql1.toCharArray());
            String delNames = getName(accountId, db);
            msg = "删除账号　" + delNames + "　失败！";
            db.update(sql1);
            db.update(sql2);
            msg = "删除账号　" + delNames + "　成功！";
        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } finally {
            try {

                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }

    /**
     * 删除账户调用，根据id返回名称
     * @param accountid String
     * @param db DataBase
     * @return String
     */
    private String getName(String accountid[], DataBase db) {
        ResultSet rs = null;
        String res = "";
        String sql = "SELECT NAME FROM ACCOUNT WHERE ACCOUNTID IN(";
        int k = 0;
        for (int i = 0; i < accountid.length; i++) {
            if (!accountid[i].equals("0")) {
                if (k == 0) {
                    sql += accountid[i];
                } else {
                    sql += "," + accountid[i];
                }
                k++;
            }
        }
        sql += ")";
        try {
            if (k > 0) {
                rs = db.queryAll(sql);
                while (rs.next()) {
                    res += rs.getString(1) + " ; ";
                }
            } else {
                return "";
            }
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return res;
    }

    public synchronized ArrayList getAllAccount(String whereStr,String usename,String adminsname,int curpage,String ssagcode) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        StringBuffer sb = new StringBuffer();
        if(ssagcode.length()==9){
            sb.append(" and xiaoqu='"+ssagcode+"'");//  ssagcode登录用户所属的区域 编码
        }else if(ssagcode.length()==7){
            sb.append(" and xian='"+ssagcode+"'");
        }else if(ssagcode.length()==5){
            sb.append(" and shi='"+ssagcode+"'");
        }
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ACCOUNTID,ACCOUNTNAME,NAME,MOBILE,TEL,EMAIL");
        sql.append(",(case when a.sheng is not null then (select agname from d_area_grade where agcode=a.sheng) else '' end)");
        sql.append("||(case when a.shi is not null then (select agname from d_area_grade where agcode=a.shi) else '' end)");
        sql.append("||(case when a.xian!='0' then (select agname from d_area_grade where agcode=a.xian) else '' end)");
        sql.append("||(case when a.xiaoqu!='0' then (select agname from d_area_grade where agcode=a.xiaoqu) else '' end) as szdq");
        //sql.append(",CASE WHEN ENABLE=1 THEN '启用 ' ELSE '停用 ' END SIGN");
        sql.append(
                ",ROLEID, ROLENAME");
        sql.append(",A.SHENG,A.SHI,A.XIAN,A.XIAOQU");
        sql.append(" FROM ACCOUNT A WHERE DELSIGN=1 AND ACCOUNTNAME!='admin'");
        sql.append(sb.toString());////  sb登录用户所属的区域 编码
        sql.append(" AND ACCOUNTNAME LIKE '%"+usename+"%' AND "+"NAME LIKE '%"+adminsname+"%'"+whereStr );
        sql.append(" ORDER BY NAME");
        System.out.println("人员："+sql.toString());
        StringBuffer querystr_all = new StringBuffer();
        querystr_all.append("select count(*) from account a where delsign=1 AND ACCOUNTNAME LIKE '%"+usename+"%' AND "+"NAME LIKE '%"+adminsname+"%'"+whereStr);
        querystr_all.append(sb.toString());
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(querystr_all.toString());
            if (rs.next()) {
                if (rs.getInt(1) % 15 == 0) {
                    this.setAllPage(rs.getInt(1) / 15);
                } else {
                    this.setAllPage(rs.getInt(1) / 15 + 1);
                }
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(curpage, sql.toString()));
            Query query = new Query();
            list = query.query(rs);
            System.out.println(querystr_all.toString());

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }
    
    
    //操作员设置导出
    public synchronized ArrayList getAllAccountdc(String whereStr,String ssagcode,String str) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        StringBuffer sb = new StringBuffer();
        if(ssagcode.length()==9){
            sb.append(" and xiaoqu='"+ssagcode+"'");
        }else if(ssagcode.length()==7){
            sb.append(" and xian='"+ssagcode+"'");
        }else if(ssagcode.length()==5){
            sb.append(" and shi='"+ssagcode+"'");
        }
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ACCOUNTID,ACCOUNTNAME,NAME,MOBILE,TEL,EMAIL");
        sql.append(",(case when a.shi is not null then (select agname from d_area_grade where agcode=a.shi) else '' end) as cs");
        sql.append(",(case when a.xian!='0' then (select agname from d_area_grade where agcode=a.xian) else '' end) as qx");
        sql.append(",(case when a.xiaoqu!='0' then (select agname from d_area_grade where agcode=a.xiaoqu) else '' end) as xq");
        sql.append(",ROLEID, ROLENAME");
        sql.append(" FROM ACCOUNT A WHERE DELSIGN=1 AND ACCOUNTNAME!='admin'");
        sql.append(sb.toString());
        sql.append(" "+whereStr );
        sql.append(" "+str );
        sql.append(" ORDER BY NAME");
        System.out.println("操作员设置导出："+sql.toString());
        ResultSet rs = null;
        try {
            db.connectDb();
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(sql.toString());
            Query query = new Query();
            list = query.query(rs);
        } catch (DbException de) {
            de.printStackTrace();
        } catch (Exception de) {
	      de.printStackTrace();
	    }finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }   

    /**
     * 密码重置
     * 把密码重置为 huawei
     * @param accountId String
     * @return intf
     */

    public synchronized String resetPass(String[] accountId, String oper) {
        String msg = "";
        DataBase db = new DataBase();
        MD5 md = new MD5();

        try {

            db.connectDb();
            ResultSet rs = db.queryAll(
                    "select itemvalue from permission_configuration where itemname='InitPassword'");
            String newPass = "";
            while (rs.next()) {
                newPass = rs.getString(1).trim();
            }
            String sql = "UPDATE ACCOUNT SET PASSWORD='" +
                         md.getMD5ofStr(newPass) +
                         "' WHERE ACCOUNTID IN (";
            // + accountId;
            for (int i = 0; i < accountId.length; i++) {
                if (i > 0) {
                    sql += "," + accountId[i];
                } else {
                    sql += accountId[i];
                }
            }
            sql += ")";

            String names = this.getName(accountId, db);
            msg = "给账户 " + names + "　重置秘密失败！";
            if (db.update(sql) > 0) {
                msg = "给账户 " + names + "　重置秘密成功！";
            }
        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } catch (SQLException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }

    /**
     * 账户标记停、启用
     * @param accountId String
     * @param sign Strng on or off
     * @return String
     */
    public synchronized String accountOnOrOff(String[] accountId, String sign) {
        String msg = "";
        int len = accountId.length;

        if (sign.equals("0")) {
            msg = "停用账户失败！";
        } else {
            msg = "启用账户失败！！";
        }

        String sql = "UPDATE ACCOUNT SET ENABLE=" + sign +
                     " WHERE ACCOUNTID IN(";
        String accountList = "";
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                accountList += "," + accountId[i];
            } else {
                accountList += accountId[i];
            }
        }
        sql += accountList + ")";

        DataBase db = new DataBase();
        try {
            db.connectDb();
            String names = this.getName(accountId, db);
            if (sign.equals("0")) {
                msg = "停用账户 " + names + " 失败！";
            } else {
                msg = "启用账户 " + names + " 失败！！";
            }

            db.update(sql);

            if (sign.equals("0")) {
                msg = "停用账户 " + names + " 成功！";
            } else {
                msg = "启用账户 " + names + " 成功！！";
            }

        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }

            de.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }

    /**
     * 修改账户信息，根据id
     * @param accountId String
     * @param accountName String
     * @param name String
     * @param roleId String
     * @param position String
     * @param age String
     * @param address String
     * @param mobile String
     * @param email String
     * @param sex String
     * @param organization String
     * @param tel String
     * @param birthday String
     * @return String
     *
     */
    public synchronized String modifyAccount(String accountId,
                                             String accountName,
                                             String name, String position,
                                             String bumen, String zip,
                                             String[] roleid,
                                             String address, String mobile,
                                             String email, String sex,
                                             String tel, String memo,
                                             String sheng, String shi,
                                             String xian, String xiaoqu) {
        String msg = "修改账户信息失败！";
        StringBuffer sql = new StringBuffer();
        DataBase db = new DataBase();
        try {
            db.connectDb();
            String roles = "", rolesname = "";
            for (int k = 0; k < roleid.length; k++) {
                if (k == 0) {
                    roles = roleid[k];
                } else {
                    roles += "," + roleid[k];
                }
            }
            rolesname = this.getRolesName(db, roles);

            sql.append("UPDATE ACCOUNT SET NAME='" +
                       name + "',");
            sql.append("sheng='" + sheng + "',shi='" + shi + "',xian='" + xian +
                       "',");
            sql.append("ADDRESS='" + address + "',MOBILE='" + mobile +
                       "',EMAIL='" +
                       email + "',SEX='" + sex + "',BUMEN='" + bumen +
                       "',ZIP='" +
                       zip + "',");
            sql.append("TEL='" + tel + "',POSITION='" + position +
                       "',ROLEID='" + roles + "',MEMO='" + memo +
                       "',xiaoqu='" + xiaoqu + "',ROLENAME='" + rolesname +
                       "' WHERE ACCOUNTID=" + accountId);
            System.out.println(sql.toString());
            db.update(sql.toString());

            msg = "修改账户 " + name + " 成功！";

        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }

            de.printStackTrace();
        } catch (SQLException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }

            de.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }

    /**
     * 添加、修改账户时，如果修改登录名
     * 检查是否登录名已经存在
     * @param name String
     * @return boolean
     */
    public synchronized boolean validateName(String sql, DataBase db) {
        boolean sign = false;
        try {
            ResultSet rs = db.queryAll(sql);
            while (rs.next()) {
                return true;
            }
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return sign;
    }

    public synchronized boolean validateMobile(String sql, DataBase db) {
        boolean sign = false;
        try {
            ResultSet rs = db.queryAll(sql);
            while (rs.next()) {
                return true;
            }
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return sign;
    }

    /**
     * 添加、修改账户时，如果修改登录名
     * 检查是否登录名已经存在
     * @param name String
     * @param sign int
     * accountId==0 添加新账户是调用，accountId!=1 修改界面调用
     * @return boolean
     */
    public synchronized String validateName(String accountName,
                                            String accountId) {
        String msg = "恭喜你，此用户名你可以用！";
        String sql = "";
        if (accountId.equals("0")) {
            sql = "SELECT ACCOUNTID FROM ACCOUNT WHERE ACCOUNTNAME='" +accountName+"'";
        } else {
            sql = "SELECT ACCOUNTID FROM ACCOUNT WHERE ACCOUNTID!=" + accountId +" AND ACCOUNTNAME='" + accountName+"'";
        }

        DataBase db = new DataBase();
        try {
            db.connectDb();

            ResultSet rs = db.queryAll(sql);
            while (rs.next()) {
                return "此用户名已经存在，请另选择一个！";
            }
            rs.close();
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }

    /**
     * 检查电话唯一
     * @param name String
     * @param sign int
     * accountId==0 添加新账户是调用，accountId!=0 修改界面调用
     * @return boolean
     */
    public synchronized String validateMobile(String accountMobile,
                                              String accountId) {
        String msg = "此电话号码不重复！";
        String sql = "";
        if (accountId.equals("0")) {
            sql = "SELECT ACCOUNTID FROM ACCOUNT WHERE MOBILE='" +
                  accountMobile +
                  "'";
        } else {
            sql = "SELECT ACCOUNTID FROM ACCOUNT WHERE ACCOUNTID!=" + accountId +
                  " AND MOBILE='" + accountMobile + "'";
        }

        DataBase db = new DataBase();
        try {
            db.connectDb();

            ResultSet rs = db.queryAll(sql);
            while (rs.next()) {
                return "此手机号码已经存在，请确定您的电话号码是否正确！";
            }
            rs.close();
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }

    public int getAllPage() {
        return allPage;
    }
    public synchronized ArrayList getAllinfo(String whereStr,String usename,String adminsname,int start,String ssagcode) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        StringBuffer sb = new StringBuffer();
      
        StringBuffer sql = new StringBuffer();
        
        StringBuffer s2 = new StringBuffer();
		StringBuffer s3 = new StringBuffer();
        System.out.println("人员："+sql.toString());
        StringBuffer querystr_all = new StringBuffer();
        try {
        s2.append(
        "select ef.electricfee_id, to_char(ef.accountmonth, 'yyyy-mm') accountmonth,zd.jzname,am.blhdl,ef.bg,ef.actualpay,EF.ACTUALPAY_D,EF.ACTUALPAY_L,EF.ACTUALPAY_Y,EF.ACTUALPAY_O,ef.manualauditstatus,EF.CB_ALERT "+
        " from zhandian zd, dianbiao db, ammeterdegrees am, electricfees ef "+
       " where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk    "+whereStr+" ");
       
        s3.append("select count(*)  from (" + s2 + ")");
        ResultSet rs = null;
       
        	 System.out.println("字典查询分页"+s3); 
        	 System.out.println("字典查询分页"+s2); 
             ResultSet rs3 = null;
             rs3=db.queryAll(s3.toString());
             if(rs3.next()){
             	this.setAllPage((rs3.getInt(1)+9)/10);
             }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
            rs3.close();

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }
    public synchronized ArrayList getDfquery(String whereStr,String usename,String adminsname,int start,String ssagcode) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        StringBuffer sb = new StringBuffer();
      
        StringBuffer sql = new StringBuffer();
        
        StringBuffer s2 = new StringBuffer();
		StringBuffer s3 = new StringBuffer();
        System.out.println("人员："+sql.toString());
        StringBuffer querystr_all = new StringBuffer();
        try {
        s2.append(
        "select ef.electricfee_id, to_char(ef.accountmonth, 'yyyy-mm') accountmonth,zd.jzname,am.blhdl,ef.bg,ef.actualpay,EF.ACTUALPAY_D,EF.ACTUALPAY_L,EF.ACTUALPAY_Y,EF.ACTUALPAY_O,ef.manualauditstatus "+
        " from zhandian zd, dianbiao db, ammeterdegrees am, electricfees ef "+
       " where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk "+whereStr+" ");
       
        s3.append("select count(*)  from (" + s2 + ")");
        ResultSet rs = null;
       
        	 System.out.println("字典查询分页"+s3); 
        	 System.out.println("字典查询分页"+s2); 
             ResultSet rs3 = null;
             rs3=db.queryAll(s3.toString());
             if(rs3.next()){
             	this.setAllPage((rs3.getInt(1)+9)/10);
             }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
            rs3.close();

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }
    public synchronized ArrayList getYufufei(String whereStr,String usename,String adminsname,int start,String ssagcode) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        StringBuffer sb = new StringBuffer();
      
        StringBuffer sql = new StringBuffer();
        
        StringBuffer s2 = new StringBuffer();
		StringBuffer s3 = new StringBuffer();
        System.out.println("人员："+sql.toString());
        StringBuffer querystr_all = new StringBuffer();
        try {
        s2.append("select am.STARTMONTHOLD,am.ENDMONTHOLD,am.AMMETERDEGREEID, ef.electricfee_id,to_char(ef.accountmonth, 'yyyy-mm') accountmonth, zd.jzname,am.blhdl, ef.bg,");
       s2.append(" ef.actualpay, ef.manualauditstatus  from zhandian zd, dianbiao db, ammeterdegrees am, electricfees ef  where zd.id = db.zdid");
       s2.append("   and db.dbid = am.ammeterid_fk  and am.ammeterdegreeid = ef.ammeterdegreeid_fk"+whereStr);
       s3.append("select count(*)  from (" + s2 + ")");
        ResultSet rs = null;
       
        	 System.out.println("字典查询分页"+s3); 
        	 System.out.println("字典查询分页"+s2); 
             ResultSet rs3 = null;
             rs3=db.queryAll(s3.toString());
             if(rs3.next()){
             	this.setAllPage((rs3.getInt(1)+9)/10);
             }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
            rs3.close();

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }
    public synchronized ArrayList getAllJzbg(String whereStr,String usename,String adminsname,int start,String ssagcode) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        StringBuffer sb = new StringBuffer();
      
        StringBuffer sql = new StringBuffer();
        
        StringBuffer s2 = new StringBuffer();
		StringBuffer s3 = new StringBuffer();
        System.out.println("人员："+sql.toString());
        StringBuffer querystr_all = new StringBuffer();
        try {
        s2.append(" SELECT T.ID,ZD.JZNAME,T.YEARMONTH,T.ZLFH,T.KTXS,T.YDXS,T.BIAOGANVALUE,T.SPZT ,T.SPSJ  FROM  TBL_TT_BIAOGAN_LISHI T,ZHANDIAN ZD WHERE ZD.JZCODE=T.ZDBM "+whereStr+" ");
       
        s3.append("select count(*)  from (" + s2 + ")");
        ResultSet rs = null;
       
        	 System.out.println("字典查询分页"+s3); 
        	 System.out.println("字典查询分页"+s2); 
             ResultSet rs3 = null;
             rs3=db.queryAll(s3.toString());
             if(rs3.next()){
             	this.setAllPage((rs3.getInt(1)+9)/10);
             }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
            rs3.close();

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }
        return list;
    }
    public synchronized ArrayList getAllJzbgsh(String whereStr) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        StringBuffer sb = new StringBuffer();
      
        StringBuffer sql = new StringBuffer();
        
        StringBuffer s2 = new StringBuffer();
		StringBuffer s3 = new StringBuffer();
        System.out.println("人员："+sql.toString());
        StringBuffer querystr_all = new StringBuffer();
        try {
        s2.append(" SELECT T.ID,ZD.JZNAME,T.YEARMONTH,T.ZLFH,T.KTXS,T.YDXS,T.BIAOGANVALUE,T.SPZT   FROM  TBL_TT_BIAOGAN_LISHI T,ZHANDIAN ZD WHERE ZD.JZCODE=T.ZDBM AND T.ID='"+whereStr+"' ");
       
        s3.append("select count(*)  from (" + s2 + ")");
        ResultSet rs = null;
       
        	 System.out.println("基站标杆审核："+s2); 
             ResultSet rs3 = null;
             rs3=db.queryAll(s2.toString());
           
          
            Query query = new Query();
            list = query.query(rs3);
            rs3.close();

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }
    public synchronized String delSh(String accountId,String zt) {
        String msg = "审核失败！";
        DataBase db = new DataBase();

        try {
            db.connectDb();

            int k = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String sql1 = "UPDATE TBL_TT_BIAOGAN_LISHI SET SPZT='"+zt+"',SPSJ = to_date('"+sdf.format(new Date())+"','yyyy-mm-dd hh24:mi:ss') WHERE ID ="+accountId+""; //删除账号表

            System.out.println(sql1.toCharArray());
            db.setAutoCommit(false);
            db.update(sql1);
            db.setAutoCommit(true);
            msg = "审核成功！";
        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } finally {
            try {

                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }
    public synchronized String delShdj(String accountId,String zt,String memo) {
        String msg = "审核失败！";
        DataBase db = new DataBase();

        try {
            db.connectDb();

            int k = 0;

            String sql1 = "UPDATE DIANJIA SET SHENHE_FLAG='"+zt+"',MEMO='"+memo+"' WHERE ID ="+accountId+""; //删除账号表

            System.out.println(sql1.toCharArray());
            db.setAutoCommit(false);
            db.update(sql1);
            db.setAutoCommit(true);
            msg = "审核成功！";
        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } finally {
            try {

                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }
    public synchronized String delShdldf(String accountId,String zt,String memo) {
        String msg = "审核失败！";
        DataBase db = new DataBase();

        try {
            db.connectDb();

            int k = 0;

            String sql1 = "UPDATE ELECTRICFEES T SET T.MANUALAUDITSTATUS='"+zt+"',T.MEMO='"+memo+"' WHERE T.ELECTRICFEE_ID ="+accountId+""; //删除账号表

            System.out.println("sql1:"+sql1.toString());
            
            System.out.println(sql1.toCharArray());
            db.setAutoCommit(false);
            db.update(sql1);
            db.setAutoCommit(true);
            msg = "审核成功！";
        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } finally {
            try {

                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return msg;
    }
    public synchronized ArrayList getAllDjSh(String whereStr,String usename,String adminsname,int start,String ssagcode) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        StringBuffer sb = new StringBuffer();
      
        StringBuffer sql = new StringBuffer();
        
        StringBuffer s2 = new StringBuffer();
		StringBuffer s3 = new StringBuffer();
        System.out.println("人员："+sql.toString());
        StringBuffer querystr_all = new StringBuffer();
        try {
        s2.append(" SELECT DJ.ID, DJ.YEARMONTH,DJ.DBBM,DB.DBNAME,RTNAME(DB.DFZFLX) DFZFLX,DB.BEILV,DJ.DANJIA,DJ.SHENHE_FLAG,ZD.JZNAME  FROM ZHANDIAN ZD,DIANBIAO DB,DIANJIA DJ WHERE ZD.ID=DB.ZDID AND DB.DBBM=DJ.DBBM AND ZD.JZCODE=DJ.ZDBM "+whereStr+" ");
       
        s3.append("select count(*)  from (" + s2 + ")");
        ResultSet rs = null;
       
        	 System.out.println("字典查询分页"+s3); 
        	 System.out.println("字典查询分页"+s2); 
             ResultSet rs3 = null;
             rs3=db.queryAll(s3.toString());
             if(rs3.next()){
             	this.setAllPage((rs3.getInt(1)+9)/10);
             }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
            rs3.close();

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }
    public synchronized ArrayList getAllJzdjsh(String whereStr) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        StringBuffer sb = new StringBuffer();
      
        StringBuffer sql = new StringBuffer();
        
        StringBuffer s2 = new StringBuffer();
		StringBuffer s3 = new StringBuffer();
        System.out.println("人员："+sql.toString());
        StringBuffer querystr_all = new StringBuffer();
        try {
        s2.append(" SELECT DJ.ID, DJ.YEARMONTH,DJ.DBBM,DB.DBNAME,RTNAME(DB.DFZFLX) DFZFLX,DB.BEILV,DJ.DANJIA,DJ.SHENHE_FLAG  FROM ZHANDIAN ZD,DIANBIAO DB,DIANJIA DJ WHERE ZD.ID=DB.ZDID AND DB.DBBM=DJ.DBBM AND ZD.JZCODE=DJ.ZDBM AND DJ.ID='"+whereStr+"' ");
       
        s3.append("select count(*)  from (" + s2 + ")");
        ResultSet rs = null;
       
        	 System.out.println("基站标杆审核："+s2); 
             ResultSet rs3 = null;
             rs3=db.queryAll(s2.toString());
           
          
            Query query = new Query();
            list = query.query(rs3);
            rs3.close();

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }
    public synchronized ArrayList getAllinfoxx(String whereStr) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        StringBuffer sb = new StringBuffer();
      
        StringBuffer sql = new StringBuffer();
        
        StringBuffer s2 = new StringBuffer();
        System.out.println("人员："+sql.toString());
        StringBuffer querystr_all = new StringBuffer();
        try {
        s2.append(
        "select ef.electricfee_id, to_char(ef.accountmonth, 'yyyy-mm') accountmonth,zd.jzname,am.blhdl,ef.bg,ef.actualpay,EF.ACTUALPAY_D,EF.ACTUALPAY_L,EF.ACTUALPAY_Y,EF.ACTUALPAY_O,ef.manualauditstatus "+
        " from zhandian zd, dianbiao db, ammeterdegrees am, electricfees ef "+
       " where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk  and ef.electricfee_id='"+whereStr+"' ");
       
      //  s3.append("select count(*)  from (" + s2 + ")");
       
        	 System.out.println("字典查询分页"+s2); 
             ResultSet rs3 = null;
             rs3=db.queryAll(s2.toString());
            Query query = new Query();
            list = query.query(rs3);
            rs3.close();

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }
    public synchronized ArrayList getAllinfoGj(String whereStr,String usename,String adminsname,int start,String ssagcode) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        StringBuffer sb = new StringBuffer();
      
        StringBuffer sql = new StringBuffer();
        
        StringBuffer s2 = new StringBuffer();
		StringBuffer s3 = new StringBuffer();
        System.out.println("人员："+sql.toString());
        StringBuffer querystr_all = new StringBuffer();
        try {
        s2.append(
        "select ef.electricfee_id, to_char(ef.accountmonth, 'yyyy-mm') accountmonth,zd.jzname,am.blhdl,ef.bg,ef.actualpay,EF.ACTUALPAY_D,EF.ACTUALPAY_L,EF.ACTUALPAY_Y,EF.ACTUALPAY_O,ef.manualauditstatus,EF.CB_ALERT "+
        " from zhandian zd, dianbiao db, ammeterdegrees am, electricfees ef "+
       " where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk AND EF.CB_ALERT='1'   "+whereStr+" ");
       
        s3.append("select count(*)  from (" + s2 + ")");
        ResultSet rs = null;
       
        	 System.out.println("字典查询分页"+s3); 
        	 System.out.println("字典查询分页"+s2); 
             ResultSet rs3 = null;
             rs3=db.queryAll(s3.toString());
             if(rs3.next()){
             	this.setAllPage((rs3.getInt(1)+9)/10);
             }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
            Query query = new Query();
            list = query.query(rs);
            rs3.close();
            rs.close();

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }
    public synchronized ArrayList getAllAccountxyh(String whereStr,String usename,String adminsname,int curpage,String ssagcode) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        StringBuffer sb = new StringBuffer();
        
        if(ssagcode.length()==9){
            sb.append(" and xiaoqu='"+ssagcode+"'");//  ssagcode登录用户所属的区域 编码
        }else if(ssagcode.length()==7){
            sb.append(" and xian='"+ssagcode+"'");
        }else if(ssagcode.length()==5){
            sb.append(" and shi='"+ssagcode+"'");
        }
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ACCOUNTID,ACCOUNTNAME,NAME,MOBILE,TEL,EMAIL");
        sql.append(",(case when a.sheng is not null then (select agname from d_area_grade where agcode=a.sheng) else '' end)");
        sql.append("||(case when a.shi is not null then (select agname from d_area_grade where agcode=a.shi) else '' end)");
        sql.append("||(case when a.xian!='0' then (select agname from d_area_grade where agcode=a.xian) else '' end)");
        sql.append("||(case when a.xiaoqu!='0' then (select agname from d_area_grade where agcode=a.xiaoqu) else '' end) as szdq");
        //sql.append(",CASE WHEN ENABLE=1 THEN '启用 ' ELSE '停用 ' END SIGN");
        sql.append(
                ",ROLEID, ROLENAME");
        sql.append(",A.SHENG,A.SHI,A.XIAN,A.XIAOQU");
        sql.append(" FROM ACCOUNT A WHERE DELSIGN=1 AND ACCOUNTNAME!='admin' "+whereStr+" ");
      //  sql.append(sb.toString());////  登录用户所属的区域 编码
      //  sql.append(" AND ACCOUNTNAME LIKE '%"+usename+"%' AND "+"NAME LIKE '%"+adminsname+"%'"+whereStr );
        sql.append(" ORDER BY NAME");
        System.out.println("人员："+sql.toString());
        StringBuffer querystr_all = new StringBuffer();
        querystr_all.append("select count(*) from ("+sql+") ");// from account a where delsign=1 AND ACCOUNTNAME LIKE '%"+usename+"%' AND "+"NAME LIKE '%"+adminsname+"%'"+whereStr);
       // querystr_all.append(sb.toString());
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(querystr_all.toString());
            if (rs.next()) {
                if (rs.getInt(1) % 10 == 0) {
                    this.setAllPage(rs.getInt(1) / 10);
                } else {
                    this.setAllPage(rs.getInt(1) / 10 + 1);
                }
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(curpage, sql.toString()));
            Query query = new Query();
            list = query.query(rs);
            System.out.println(querystr_all.toString());

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }
}
