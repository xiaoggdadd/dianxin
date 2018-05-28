package com.noki.mobi.sys.javabean;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

public class FenzuBean {
    public synchronized String addRole(String name, String memo, String orderid) {
        String msg = "添加分组信息失败！";
        DataBase db = new DataBase();
        String sql = "INSERT INTO fenzu(NAME,MEMO,orderid) VALUES('" + name +
                     "','" +
                     memo + "'," + orderid + ")";
        try {
            db.connectDb();
            if (sameName(name, db)) {
                db.update(sql);
                return "添加分组信息成功！";
            } else {
                return "分组名称 " + name + " 已经存在，请另选择一个名称！";
            }

        } catch (DbException de) {
            de.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return msg;
    }

    public synchronized String modifyRole(String id, String name, String memo,
                                          String orderid) {
        String msg = "修改分组信息失败！";
        DataBase db = new DataBase();
        StringBuffer sql = new StringBuffer();
        sql.append("update fenzu set name='" + name + "',memo='" + memo +
                   "',orderid=" + orderid);
        sql.append(" where id=" + id);

        try {
            db.connectDb();
            if (sameName(name, db, id)) {
                db.update(sql.toString());
                return "修改分组信息成功！";
            } else {
                return "分组名称 " + name + " 已经存在，请另选择一个名称！";
            }

        } catch (DbException de) {
            de.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return msg;
    }


    public synchronized String addBiaoshi(String name, String biaoshi,
                                          String memo, String orderid) {
        String msg = "添加标识信息失败！";
        DataBase db = new DataBase();
        String sql = "INSERT INTO BIAOSHI(NAME,MEMO,orderid,biaoshi) VALUES('" +
                     name + "','" +
                     memo + "'," + orderid + ",'" + biaoshi + "')";
        System.out.println(sql);
        try {
            db.connectDb();
            if (sameBiaoshi(name, db)) {
                db.update(sql);
                return "添加标识信息成功！";
            } else {
                return "标识名称 " + name + " 已经存在，请另选择一个名称！";
            }

        } catch (DbException de) {
            de.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return msg;
    }

    private boolean sameName(String name, DataBase db) {
        boolean sign = true;
        String sql = "SELECT ID FROM ROLE WHERE NAME='" + name + "'";
        try {
            ResultSet rs = db.queryAll(sql);
            while (rs.next()) {
                return false;
            }
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return sign;
    }

    private boolean sameName(String name, DataBase db, String id) {
        boolean sign = true;
        String sql = "SELECT ID FROM ROLE WHERE NAME='" + name + "' and id!=" +
                     id;
        try {
            ResultSet rs = db.queryAll(sql);
            while (rs.next()) {
                return false;
            }
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return sign;
    }


    private boolean sameBiaoshi(String name, DataBase db) {
        boolean sign = true;
        String sql = "SELECT ID FROM BIAOSHI WHERE NAME='" + name + "'";
        try {
            ResultSet rs = db.queryAll(sql);
            while (rs.next()) {
                return false;
            }
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return sign;
    }


    public synchronized ArrayList getAllFenzu() {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        String sql = "";
        sql +=
                "SELECT ID,NAME,MEMO,ORDERID FROM FENZU ORDER BY ORDERID";

        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            Query query = new Query();
            list = query.query(rs);
        } catch (DbException de) {
            de.printStackTrace();
        }

        finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }

    public synchronized ArrayList getAllBiaoshi() {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        String sql = "";
        sql +=
                "SELECT ID,NAME,BIAOSHI,MEMO,ORDERID FROM BIAOSHI ORDER BY ORDERID";

        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            Query query = new Query();
            list = query.query(rs);
        } catch (DbException de) {
            de.printStackTrace();
        }

        finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return list;
    }

    public synchronized int delBiaoshi(String[] biaoshi) {
        int msg = 0;
        DataBase db = new DataBase();
        StringBuffer sbs = new StringBuffer();
        for (int i = 0; i < biaoshi.length; i++) {
            if (i == 0) {
                sbs.append(biaoshi[i]);
            } else {
                sbs.append("," + biaoshi[i]);
            }
        }
        StringBuffer sql = new StringBuffer();
        sql.append("delete from biaoshi where id in(" + sbs.toString() + ")");
        StringBuffer s2 = new StringBuffer();
        s2.append("delete from role_permission");
        //System.out.println(sql);
        try {
            db.connectDb();
            s2.append(" where permission in ('" +
                      this.getPermissions(db, sbs.toString()) + "')");
            db.setAutoCommit(false);
            db.update(sql.toString());
            db.update(s2.toString());
            db.commit();
            db.setAutoCommit(true);
            msg = 1;

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException de) {
            de.printStackTrace();
        }

        finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return msg;
    }

    private synchronized String getPermissions(DataBase db, String ids) throws
            DbException, SQLException {
        StringBuffer permissions = new StringBuffer();
        ResultSet rs = db.queryAll("select biaoshi from biaoshi where id in (" +
                                   ids + ")");
        while (rs.next()) {
            permissions.append(rs.getString(1));
        }
        return permissions.toString();
    }

    public synchronized int delFenzu(String[] ids) {
        int msg = 0;
        DataBase db = new DataBase();
        StringBuffer sbs = new StringBuffer();
        for (int i = 0; i < ids.length; i++) {
            if (i == 0) {
                sbs.append(ids[i]);
            } else {
                sbs.append("," + ids[i]);
            }
        }
        StringBuffer sql = new StringBuffer();
        sql.append("delete from fenzu where id in(" + sbs.toString() + ")");
        StringBuffer s2 = new StringBuffer();
        s2.append("select * from role where fenzu in(" + sbs.toString() + ")");
        //System.out.println(sql);
        try {
            db.connectDb();
            ResultSet rs = db.queryAll(s2.toString());
            if (rs.next()) {
                return 2;
            } else {
                db.update(sql.toString());
            }
            msg = 1;

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException de) {
            de.printStackTrace();
        }

        finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }

        return msg;
    }


}
