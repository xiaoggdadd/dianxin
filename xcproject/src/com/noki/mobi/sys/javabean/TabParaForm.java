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
public class TabParaForm {
    private String jgday;
    private String tabname;
    private String dbid;
    private String ldata;
    private String bdata;
    private String ltime;
    private String btime;
    private String sjdata;
    private String zdcb;
    private String dbip;
    private String dbname;
    private String dbuser;
    private String dbpass;
    public TabParaForm() {
    }

    public void setJgday(String jgday) {
        this.jgday = jgday;
    }

    public void setTabname(String tabname) {
        this.tabname = tabname;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    public void setLdata(String ldata) {
        this.ldata = ldata;
    }

    public void setBdata(String bdata) {
        this.bdata = bdata;
    }

    public void setLtime(String ltime) {
        this.ltime = ltime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public void setSjdata(String sjdata) {
        this.sjdata = sjdata;
    }

    public void setZdcb(String zdcb) {
        this.zdcb = zdcb;
    }

    public void setDbip(String dbip) {
        this.dbip = dbip;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    public void setDbpass(String dbpass) {
        this.dbpass = dbpass;
    }

    public String getJgday() {
        return jgday;
    }

    public String getTabname() {
        return tabname;
    }

    public String getDbid() {
        return dbid;
    }

    public String getLdata() {
        return ldata;
    }

    public String getBdata() {
        return bdata;
    }

    public String getLtime() {
        return ltime;
    }

    public String getBtime() {
        return btime;
    }

    public String getSjdata() {
        return sjdata;
    }

    public String getZdcb() {
        return zdcb;
    }

    public String getDbip() {
        return dbip;
    }

    public String getDbname() {
        return dbname;
    }

    public String getDbuser() {
        return dbuser;
    }

    public String getDbpass() {
        return dbpass;
    }

    public synchronized TabParaForm getAccountInfo() {
        TabParaForm bean = new TabParaForm();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT JGDAY,TABNAME,DBID,LDATA,BDATA,LTIME,BTIME,SJDATA,ZDCB,DBIP,DBNAME,DBUSER,DBPASSWORD FROM TAB_SCHEDULE");

        DataBase db = new DataBase();
        try {
            db.connectDb();
            ResultSet rs = null;
            rs = db.queryAll(sql.toString());
            if (rs.next()) {
                System.out.println("hava data");
                bean.setJgday(rs.getString(1)!=null?rs.getString(1):"");
                bean.setTabname(rs.getString(2)!=null?rs.getString(2):"");
                bean.setDbid(rs.getString(3)!=null?rs.getString(3):"");
                bean.setLdata(rs.getString(4)!=null?rs.getString(4):"");
                bean.setBdata(rs.getString(5)!=null?rs.getString(5):"");
                bean.setLtime(rs.getString(6)!=null?rs.getString(6):"");
                bean.setBtime(rs.getString(7)!=null?rs.getString(7):"");
                bean.setSjdata(rs.getString(8)!=null?rs.getString(8):"");
                bean.setZdcb(rs.getString(9)!=null?rs.getString(9):"");
                bean.setDbip(rs.getString(10)!=null?rs.getString(10):"");
                bean.setDbname(rs.getString(11)!=null?rs.getString(11):"");
                bean.setDbuser(rs.getString(12)!=null?rs.getString(12):"");
                bean.setDbpass(rs.getString(13)!=null?rs.getString(13):"");

            }else{
                bean.setJgday("1");
               bean.setTabname("");
               bean.setDbid("");
               bean.setLdata("");
               bean.setBdata("");
               bean.setLtime("");
               bean.setBtime("");
               bean.setSjdata("");
               bean.setZdcb("");
               bean.setDbip("");
               bean.setDbname("");
               bean.setDbuser("");
               bean.setDbpass("");

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
