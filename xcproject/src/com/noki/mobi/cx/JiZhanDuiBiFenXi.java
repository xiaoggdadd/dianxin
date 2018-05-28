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
 * <p>Title: 基站对比分析</p>
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
public class JiZhanDuiBiFenXi {
    public JiZhanDuiBiFenXi() {
    }


    public synchronized ArrayList getPageData(String jza,
                                              String jzb,String begintime,String endtime) {
        ArrayList list = new ArrayList();
        CTime ct = new CTime();
        String sql="select z.zdcode,z.jzname,(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)" +
        "|| (case when z.xian is not null then (select distinct agname from d_area_grade where agcode = z.xian) else '' end) " +
		"|| (case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = z.xiaoqu)  else '' end) as szdq," +
        		" aa.numdl,aa.numdf,aa.degree, aa.pay  from zhandian z left join (select tt.jzname, count(distinct ammeterdegrees.uuid) numdl, " +
        		"count(distinct electricfees.dfuuid) numdf, sum(ammeterdegrees.blhdl) degree, sum(electricfees.actualpay) pay" +
        		" from ammeterdegrees,electricfees,(select * from allinfo_view where rownum < 90000) tt where ammeterdegrees.ammeterid_fk = tt.dbid " +
        		"and electricfees.ammeterdegreeid_fk =ammeterdegrees.ammeterdegreeid and ammeterdegrees.manualauditstatus = '1'  and electricfees.manualauditstatus='2' and ammeterdegrees.endmonth >= '"+begintime+"' " +
        		"and ammeterdegrees.endmonth <= '"+endtime+"' group by tt.jzname) aa on z.jzname=aa.jzname where z.zdcode in ('"+jza+"', '"+jzb+"')";
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            System.out.println(sql.toString());
            rs = db.queryAll(sql.toString());
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
