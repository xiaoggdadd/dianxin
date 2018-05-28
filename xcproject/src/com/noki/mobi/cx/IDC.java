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
public class IDC {
    public IDC() {
    }

    public synchronized ArrayList getPageData_IDC(String shi,
                                              String month) {
        ArrayList list = new ArrayList();
        String begintime = month + "-01";
        String endtime = "";
        int eyue = Integer.parseInt(month.substring(5, 7));
        int enian = Integer.parseInt(month.substring(0, 4));
        if (eyue == 12) {
            endtime = String.valueOf(enian + 1) + "-01-01";
        } else {
            endtime = String.valueOf(enian) + "-" + String.valueOf(eyue + 1) +
                      "-01";
        }
        StringBuffer zd = new StringBuffer();

        CTime ct = new CTime();
        StringBuffer s2 = new StringBuffer();
        s2.append("select z.jzname,");
        s2.append(
                "(select agname from d_area_grade where agcode=z.shi) as szdq,");
        s2.append(
                "(select name from indexs where code=z.gdfs and type='GDFS') as gdfs,");
        s2.append(
                "z.jnglmk,z.area,(select ysymj from zhandiankz where zdid=z.id) as ysymj,");
        s2.append("(select jggs from zhandiankz where zdid=z.id) as jggs");
        s2.append(",(select ysygs from zhandiankz where zdid=z.id) as ysygs,");
        s2.append("'U个数' as ugs,'已使用U个数' as ysyugs");
        s2.append(",(select sum(t.actualdegree) from ammeterdegrees t where t.zdcb='0' and t.inputdatetime >='" +
                  begintime + "' ");
        s2.append(" and t.inputdatetime <'" + endtime +
                  "'  and t.ammeterid_fk in");
        s2.append(
                " (select a.ammeterid from ammeters a where a.stationid=z.id)) as ydcount,");
        s2.append("'设备' as shebei,'空调' as kongtiao,'照明' as zhaoming,");

        s2.append(
                "(select sum(e.actualpay) from electricfees e where e.ammeterdegreeid_fk in(");
        s2.append("select t.ammeterdegreeid from ammeterdegrees t where t.zdcb='0' and t.inputdatetime >='" +
                  begintime + "' ");
        s2.append(" and t.inputdatetime <'" + endtime +
                  "'  and t.ammeterid_fk in");
        s2.append(
                " (select a.ammeterid from ammeters a where a.stationid=z.id))) as jfcount");

        s2.append("  from zhandian z");

        s2.append(" where z.shi='" + shi + "'  and z.jztype='zdlx01'");

        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println(s2.toString());
        try {
            db.connectDb();

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
