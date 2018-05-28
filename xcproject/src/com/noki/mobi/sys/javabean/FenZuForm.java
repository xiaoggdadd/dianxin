package com.noki.mobi.sys.javabean;

import com.noki.database.DataBase;
import java.sql.ResultSet;
import com.noki.database.DbException;
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
public class FenZuForm {
    private String id;
    private String name;
    private String memo;
    private String orderid;
    public FenZuForm() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMemo() {
        return memo;
    }

    public String getOrderid() {
        return orderid;
    }

    public synchronized FenZuForm getAccountInfo(String id) {
        FenZuForm bean = new FenZuForm();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT name,memo,orderid from fenzu WHERE id=" +
                   id);

        DataBase db = new DataBase();
        try {
            db.connectDb();
            ResultSet rs = null;
            rs = db.queryAll(sql.toString());
            while (rs.next()) {
                bean.setName(rs.getString(1));
                bean.setMemo(rs.getString(2));
                bean.setOrderid(rs.getString(3));
                bean.setId(id);
            }
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
        return bean;
    }

}
