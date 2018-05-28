package com.noki.mobi.sys.javabean;

import com.noki.database.DbException;
import java.sql.ResultSet;
import com.noki.database.DataBase;
import java.sql.SQLException;
import java.util.ArrayList;
import com.noki.util.Query;
import com.noki.page.NPageBean;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Per_zhandian {
    public Per_zhandian() {
    }

    public synchronized ArrayList getPer_Zhandian(String accountid) {
        ArrayList list = new ArrayList();

        StringBuffer s2 = new StringBuffer();
        s2.append("select z.id,z.accountid,z.accountname,z.begincode,z.endcode,t.name");
        s2.append(
                " from per_zhandian z,account t where z.accountid=t.accountid and z.accountid=" +
                accountid );
        System.out.println(s2.toString());
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();

            rs = db.queryAll(s2.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
            de.printStackTrace();
        }

        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }

        }

        return list;
    }

    public synchronized String AddPer_Zhandian(String accountid,
                                               String begincode[],
                                               String endcode[]) {
        StringBuffer list = new StringBuffer();
        list.append("no");

        DataBase db = new DataBase();
        StringBuffer sql = new StringBuffer();

        System.out.println(sql.toString());
        try {
            db.connectDb();
            db.setAutoCommit(false);
            for (int i = 0; i < begincode.length; i++) {
                sql.setLength(0);
                sql.append(
                        "insert into per_zhandian(accountid,accountname,begincode,endcode)");
                sql.append(" select " + accountid + ",accountname,'" +
                           begincode[i] +
                           "','" + endcode[i] +
                           "' from account where accountid=" +
                           accountid);
                System.out.println(sql.toString());
                db.update(sql.toString());
            }
            db.commit();
            db.setAutoCommit(true);

            list.setLength(0);
            list.append("ok");

        }

        catch (DbException de) {
            de.printStackTrace();
        }

        finally {

            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }

        }

        return list.toString();
    }

    public synchronized int delPer_Zhandian(String id) {
        int ret = 0;

        DataBase db = new DataBase();
        StringBuffer sql = new StringBuffer();

        System.out.println(sql.toString());
        try {
            db.connectDb();

                sql.setLength(0);
                sql.append("delete from per_zhandian where id="+id);
                System.out.println(sql.toString());
               ret =  db.update(sql.toString());



        }

        catch (DbException de) {
            de.printStackTrace();
        }

        finally {

            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }

        }

        return ret;
    }



}
