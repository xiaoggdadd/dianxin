package com.noki.mobi.sys.javabean;

import com.noki.database.DbException;
import com.noki.database.DataBase;
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
public class JgBean {
    public JgBean() {
    }

    public synchronized String modifyDanJia(String day) {

        String retstr = "修改解析电表数据间隔时间失败！";
        StringBuffer sql = new StringBuffer();
        sql.append("select * from tab_schedule");

        DataBase db = new DataBase();
        try {
            db.connectDb();
            ResultSet rs = db.queryAll(sql.toString());
            if (rs.next()) {
                sql.setLength(0);
                sql.append("update tab_schedule set jgday=" + day);
            } else {
                sql.setLength(0);
                sql.append("insert into tab_schedule(jgday) values(" + day + ")");
            }
            db.update(sql.toString());
            retstr = "修改解析电表数据间隔时间成功！";
        } catch (SQLException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
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

        return retstr;
    }

    public synchronized String addtab(String tabname,String dbid,String ldata,String bdata,String ltime,String btime,String sjdata,String zdcb) {

        String retstr = "修改电量表参数失败！";
        StringBuffer sql = new StringBuffer();
        sql.append("select * from tab_schedule");

        DataBase db = new DataBase();
        try {
            db.connectDb();
            ResultSet rs = db.queryAll(sql.toString());
            if (rs.next()) {
                sql.setLength(0);
                sql.append("update tab_schedule set tabname='" + tabname+"',dbid='"+dbid+"',ldata='"+ldata+"',bdata='"+bdata+"',ltime='" + ltime+"',btime='"+btime+"',sjdata='"+sjdata+"',zdcb='"+zdcb+"'");
            } else {
                sql.setLength(0);
                sql.append("insert into tab_schedule(tabname,dbid,ldata,bdata,ltime,btime,sjdata,zdcb) values('"+tabname+"','"+dbid+"','"+ldata+"','"+bdata+"','"+ltime+"','"+btime+"','"+sjdata+"','"+zdcb+"')");
            }

            db.update(sql.toString());
            retstr = "修改电量表参数成功！";
        } catch (SQLException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
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

        return retstr;
    }

    public synchronized String adddb(String dbip,String dbname,String dbuser,String dbpass) {

        String retstr = "修改数据库配置参数失败！";
        StringBuffer sql = new StringBuffer();
        sql.append("select * from tab_schedule");

        DataBase db = new DataBase();
        try {
            db.connectDb();
            ResultSet rs = db.queryAll(sql.toString());
            if (rs.next()) {
                sql.setLength(0);
                sql.append("update tab_schedule set dbip='" + dbip+"',dbname='"+dbname+"',dbuser='"+dbuser+"',dbpassword='"+dbpass+"'");
            } else {
                sql.setLength(0);
                sql.append("insert into tab_schedule(dbip,dbname,dbuser,dbpassword) values('"+dbip+"','"+dbname+"','"+dbuser+"','"+dbpass+"')");
            }
            db.update(sql.toString());
            retstr = "修改数据库配置参数成功！";
        } catch (SQLException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
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

        return retstr;
    }



}
