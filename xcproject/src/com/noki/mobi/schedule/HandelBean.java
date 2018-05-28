package com.noki.mobi.schedule;

import java.util.TimerTask;
import com.noki.database.DbException;
import com.noki.log.DbLog;
import java.text.SimpleDateFormat;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import com.noki.util.CTime;

/**
 * <p>手动解析Tab1到电量表 </p>
 * @author not attributable 修改：王代军
 * @version 1.0 
 * @date 2012.3.7
 */
public class HandelBean {
    public HandelBean() {
    }

    private String nowtime = "";
    private String nowDay = "";
    private String nowHour = "";
    private String preSday = "";
    private String preSday_1 = "";
    private TempDb tempdb = null;
    private String tabname = "";
    private String dbid2 = "";
    private String ldata = "";
    private String bdata2 = "";
    private String ltime = "";
    private String btime2 = "";
    private String sjdata = "";
    private String zdcb = "";
    private int jgday = 1;
    private String msg = "手动解析" + preSday + "至" +
                         preSday_1 + " 的数据失败！";
    //private String pre2Sday = "";
    private static final SimpleDateFormat sdfk = new SimpleDateFormat(
            "yyyy-MM-dd");

    public String handeljx(String kstime, String jstime) {
        this.preSday = kstime;
        this.preSday_1 = jstime;
        msg = "手动解析" + preSday + "至" +
              preSday_1 + " 的数据失败！";
        System.out.println("timer run");
        nowtime = CTime.formatDate();

        DataBase db = null;

        int k = 0;
        db = new DataBase();
        boolean remotdb = false, remottab = false;

        try {
            db.connectDb();
            ResultSet rs2 = db.queryAll("select dbip,dbname,dbuser,dbpassword,tabname,dbid,ldata,bdata,ltime,btime,sjdata,zdcb,jgday from tab_schedule");
            if (rs2.next()) {
//                if (rs2.getString(1) != null && !rs2.getString(1).equals("")) {
//                    remotdb = true;
//                    tempdb = new TempDb(rs2.getString(1), rs2.getString(2),
//                                        rs2.getString(3), rs2.getString(4));
//                    tempdb.connectDb();
//                }
//                if (rs2.getString(5) != null && !rs2.getString(5).equals("")) {
//                    remottab = true;
//                    this.tabname = rs2.getString(5);
//                    this.dbid2 = rs2.getString(6);
//                    this.ldata = rs2.getString(7);
//                    this.bdata2 = rs2.getString(8);
//                    this.ltime = rs2.getString(9);
//                    this.btime2 = rs2.getString(10);
//                    this.sjdata = rs2.getString(11);
//                    this.zdcb = rs2.getString(12);
//                }

            }
           // System.out.println(">>>>>>>>>22222222222222");
            StringBuffer st = new StringBuffer();
            st.append("select distinct(stname) from tab1 where ");
            st.append(" getdatetime>'" + preSday + "'");
            st.append(" and getdatetime<='" + preSday_1 +
                      " 23:59:59'");
            System.out.println(st.toString());

            ResultSet rs = null;
            if (remotdb) {
                rs = db.queryAll(st.toString());
            } else {
                rs = tempdb.queryAll(st.toString());
            }
            StringBuffer sub = new StringBuffer();
            String ftime = "";
            String btime = "";
            double fdata = 0;
            double bdata = 0;
            double chae = 0;
            String dbid = "";
            while (rs.next()) {
                dbid = rs.getString(1);
                msg = "手动解析电表id为" + dbid + "，" + preSday + "至" +
                      preSday_1 + " 的数据失败！";
                sub.setLength(0);
                sub.append("SELECT A.*, ROWNUM RN FROM (");
                sub.append(
                        "select datavalue,getdatetime from tab1 where stname='" +
                        dbid + "'");
                sub.append(" and getdatetime<'" + preSday +
                           "' and flag!=0 and datavalue!=0 order by getdatetime desc");
                sub.append(" ) A WHERE ROWNUM =1");
                System.out.println(sub.toString());
                ResultSet rsp = null;
                if (remotdb) {
                    rsp = tempdb.queryAll(sub.toString());
                } else {
                    rsp = db.queryAll(sub.toString());
                }
                if (rsp.next()) {
                    ftime = rsp.getString("getdatetime").substring(0, 10);
                    fdata = rsp.getDouble("datavalue");
                } else {
                    ftime = preSday;
                    fdata = 0;
                }
                System.out.println(">>>>>>>>>33333333333333");
                sub.setLength(0);
                sub.append("SELECT A.*, ROWNUM RN FROM (");
                sub.append(
                        "select datavalue,getdatetime from tab1 where stname='" +
                        dbid + "'");
                sub.append(" and getdatetime>'" + preSday_1 +
                           "' and getdatetime<'" +
                           preSday_1 +
                           " 23:59:59' and flag!=0 and datavalue!=0 order by getdatetime desc");
                sub.append(" ) A WHERE ROWNUM =1");
                System.out.println(sub.toString());
                rsp = db.queryAll(sub.toString());
                if (rsp.next()) {
                    btime = rsp.getString("getdatetime").substring(0, 10);
                    bdata = rsp.getDouble("datavalue");
                } else {
                    btime = preSday_1;
                    bdata = 0;
                }
                chae = bdata - fdata;
                DecimalFormat df = new DecimalFormat(".##");
                sub.setLength(0);
                if (remottab) {
                    sub.append("insert into " + tabname + "(" + dbid2 + "," +
                               ldata + "," + bdata2 + "," + ltime + "," +
                               btime2 + "," + sjdata + "," + zdcb + ")");
                } else {
                    sub.append("insert into AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,THISDATETIME,ACTUALDEGREE,ZDCB)");

                }
                sub.append("values(");
                sub.append("'" + dbid + "','" + fdata + "','" + bdata +
                           "','" + ftime + "','" + btime + "','" +
                           df.format(chae) + "','1'");
                sub.append(")");
                System.out.println(sub.toString());
                k = db.update(sub.toString());
                if (k == 1) {
                    msg = "手动解析电表id为" + dbid + "，" + preSday + "至" +
                          preSday_1 + " 的数据成功！";
                    //log.write(msg, "system", req.getRemoteAddr(), "账户维护");
                }
                System.out.println(msg);
            }
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException de) {
            de.printStackTrace();
        } finally {
            try {
                db.close();
                if (tempdb != null) {
                    tempdb.close();
                }
            } catch (DbException de) {
                de.printStackTrace();
            }

        }
        //log.write(msg, account.getAccountName(), req.getRemoteAddr(), "账户维护");


        return msg;
    }

}
