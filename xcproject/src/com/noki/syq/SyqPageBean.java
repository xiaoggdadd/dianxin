package com.noki.syq;

import java.sql.*;
import java.util.*;

import com.noki.database.*;
import com.noki.page.*;
import com.noki.util.*;

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
public class SyqPageBean {
    private int allPage;
    public SyqPageBean() {
    }

    public synchronized ArrayList getPageData(int start, String pagelx,
                                              String s_yuef, String s_xinghao,
                                              String s_bzh, String s_nhyt,
                                              String s_zdid) {
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        StringBuffer cxtj = new StringBuffer();
        if(s_yuef.length()>0){
            cxtj.append(" and yuef='"+s_yuef+"'");
        }
        if(s_xinghao.length()>0){
            cxtj.append(" and xinghao='"+s_xinghao+"'");
        }
        if(s_bzh.length()>0){
            cxtj.append(" and bzh='"+s_bzh+"' ");
        }
        if(!s_nhyt.equals("0")){
            cxtj.append(" and nhyt='"+s_nhyt+"'");
        }
        if(s_zdid.length()>0){
            cxtj.append(" and zdid="+s_zdid);
        }

        DataBase db = new DataBase();
        ResultSet rs = null;
        StringBuffer strall = new StringBuffer();
        strall.append("select count(*) from syq z where 1=1 ");
        strall.append(cxtj.toString());
        StringBuffer querystr = new StringBuffer();
        try {
            db.connectDb();
            querystr.append("select s.ID,s.YUEF,s.BZH,");
            querystr.append(
                    "(SELECT NAME FROM INDEXS T WHERE T.CODE=S.NHLX) AS NHLX,");
            querystr.append(
                    "(SELECT NAME FROM INDEXS T WHERE T.CODE=S.NHYT) AS NHYT");
            querystr.append(
                    ",s.NHSL,s.TZSL,s.SJSL,s.DANJIA,s.NHJE,s.TZJE,s.SJJE,");
            querystr.append(
                    "(SELECT JZNAME FROM ZHANDIAN T WHERE T.ID=S.ZDID) AS ZDNAME,SHSIGN");
            querystr.append(" from syq s");
            querystr.append(" where nhlx like '%" + pagelx + "'");
            querystr.append(cxtj.toString());
            rs = db.queryAll(strall.toString());
            if (rs.next()) {
                if (rs.getInt(1) % 15 == 0) {
                    this.setAllPage(rs.getInt(1) / 15);
                } else {
                    this.setAllPage(rs.getInt(1) / 15 + 1);
                }
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, querystr.toString()));
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException de) {
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

    /**
     * 人工审核界面
     * @param start int
     * @param pagelx String
     * @param shsign String
     * @return ArrayList
     */
    public synchronized ArrayList getPageData_SH(int start, String pagelx,
                                                 String shsign) {
        ArrayList list = new ArrayList();

        StringBuffer cxtj = new StringBuffer();
        if (!pagelx.equals("0")) {
            cxtj.append(" and nhlx='" + pagelx + "'");
        }
        if (!shsign.equals("-1")) {
            cxtj.append(" and shsign='" + shsign + "'");
        }
        DataBase db = new DataBase();
        ResultSet rs = null;
        StringBuffer strall = new StringBuffer();
        strall.append("select count(*) from syq z where 1=1 ");
        strall.append(cxtj.toString());
        StringBuffer querystr = new StringBuffer();
        try {
            db.connectDb();
            querystr.append("select s.ID,s.YUEF,s.BZH,");
            querystr.append(
                    "(SELECT NAME FROM INDEXS T WHERE T.CODE=S.NHLX) AS NHLX,");
            querystr.append(
                    "(SELECT NAME FROM INDEXS T WHERE T.CODE=S.NHYT) AS NHYT");
            querystr.append(
                    ",s.NHSL,s.TZSL,s.SJSL,s.DANJIA,s.NHJE,s.TZJE,s.SJJE,");
            querystr.append(
                    "(SELECT JZNAME FROM ZHANDIAN T WHERE T.ID=S.ZDID) AS ZDNAME,SHSIGN");
            querystr.append(" from syq s");
            querystr.append(" where 1=1 ");
            querystr.append(cxtj.toString());
            rs = db.queryAll(strall.toString());
            if (rs.next()) {
                if (rs.getInt(1) % 15 == 0) {
                    this.setAllPage(rs.getInt(1) / 15);
                } else {
                    this.setAllPage(rs.getInt(1) / 15 + 1);
                }
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, querystr.toString()));
            Query query = new Query();
            list = query.query(rs);
        }

        catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException de) {
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


    public synchronized ArrayList getZbxs() {
        ArrayList list = new ArrayList();

        DataBase db = new DataBase();
        ResultSet rs = null;
        StringBuffer strall = new StringBuffer();
        strall.append(
                "select bh,name,bzunit,bmunit,xishu from zbxs order by bh");
        try {
            db.connectDb();

            rs = db.queryAll(strall.toString());
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


    public void setAllPage(int allPage) {

        this.allPage = allPage;
    }

    public int getAllPage() {

        return allPage;
    }

}
