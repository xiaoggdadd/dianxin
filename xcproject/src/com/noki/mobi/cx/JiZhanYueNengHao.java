package com.noki.mobi.cx;

import com.noki.database.DbException;
import java.sql.ResultSet;
import com.noki.database.DataBase;
import java.sql.SQLException;
import java.util.ArrayList;
import com.noki.util.Query;
import com.noki.page.NPageBean;
import com.noki.util.CTime;

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
public class JiZhanYueNengHao {
    public JiZhanYueNengHao() {
    }

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }

    public synchronized ArrayList getPageData(int start, String begintime,
                                              String endtime, String sheng,
                                              String shi, String qx,String xiaoqu) {
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select zdcode,jzname,0 as ydcount,0 as cbcount,0 as jfcount,0 as jfcs from zhandian");

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from zhandian");
            rs = db.queryAll(strall.toString());
            if (rs.next()) {
            	this.setAllPage((rs.getInt(1)+14)/15);
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
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

}
