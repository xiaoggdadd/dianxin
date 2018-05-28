package com.noki.jizhan;

import com.noki.database.DbException;
import com.noki.database.DataBase;
import com.noki.util.CTime;
import java.sql.ResultSet;
import java.sql.SQLException;

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
public class ValidateJZBean {
    public ValidateJZBean() {
    }

    public synchronized String vjzname(String jzname,String zdcode) {
        //birthday = birthday.length()>0?birthday:null;
        String msg = "验证站点名称重复失败！请重试或与管理员联系！";
        CTime ctime = new CTime();

        StringBuffer sql = new StringBuffer();
        sql.append("select id from zhandian where jzname='" +
                   jzname.trim() + "'");

        System.out.println(sql.toString());
        DataBase db = new DataBase();
        try {
            db.connectDb();

            ResultSet rs = db.queryAll(sql.toString());
            if (rs.next()) {
                msg = "站点名称重复";
            } else {
                msg = vjzcode(db,zdcode);
            }

        } catch (DbException de) {

            de.printStackTrace();
        } catch (SQLException de) {

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

    public synchronized String vjzcode(DataBase db,String jzcode) {
        //birthday = birthday.length()>0?birthday:null;
        String msg = "验证站点代码重复失败！请重试或与管理员联系！";
        CTime ctime = new CTime();

        StringBuffer sql = new StringBuffer();
        sql.append("select id from zhandian where zdcode='" +
                   jzcode.trim() + "'");
        System.out.println(sql.toString());
        try {
            ResultSet rs = db.queryAll(sql.toString());
            if (rs.next()) {
                msg = "站点代码重复";
            } else {
                msg = "ok";
            }

        } catch (DbException de) {

            de.printStackTrace();
        } catch (SQLException de) {

            de.printStackTrace();
        }
        return msg;
    }


}
