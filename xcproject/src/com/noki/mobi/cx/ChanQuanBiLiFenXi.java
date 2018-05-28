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
 * <p>Title: 站点产权比例分析查询</p>
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
public class ChanQuanBiLiFenXi {
    public ChanQuanBiLiFenXi() {
    }

    public synchronized ArrayList getPageData(String whereStr) {
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
        s2.append("select A.zdcq,A.zds,(A.zds/B.zs)*100 as zdbl from (select count(id) as zds ,(select i.name from indexs i where i.code=zd.zdcq and i.type='ZDCQ') as zdcq  from zhandian zd where 1=1 "+whereStr+" group by zd.zdcq) A ,(select count(id) as zs from zhandian zd where 1=1 "+whereStr+") B");

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            System.out.println(s2.toString());///////////
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

}
