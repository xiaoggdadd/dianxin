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
public class JiZhanNengHao {
    public JiZhanNengHao() {
    }

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }
//站的能耗查询
    public synchronized ArrayList getPageData(int start, String begintime,
                                              String endtime, String shi,
                                              String qx, String xiaoqu) {
        ArrayList list = new ArrayList();
        System.out.println(begintime+endtime);
        StringBuffer zd = new StringBuffer();
        if (shi.equals("0")) {
            zd.append("");
        } else if (qx.equals("0")) {
            zd.append(" and z.shi='" + shi + "'");
        } else if (xiaoqu.equals("0")) {
            zd.append(" and z.xian='" + qx + "'");
        } else {
            zd.append(" and z.xiaoqu='" + xiaoqu + "'");
        }

        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        String sql="select z.zdcode,aa.jzname,(case  when z.shi is not null then(select agname from d_area_grade where agcode = z.shi) else '' end) " +
        		"|| (case when z.xian is not null then (select distinct agname from d_area_grade where agcode = z.xian) else '' end) " +
        		"|| (case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = z.xiaoqu)  else '' end) as szdq," +
        		" aa.numdl,aa.numdf,aa.degree, aa.pay " +
        		"from zhandian z, dianbiao d,(select tt.jzname,count(distinct ammeterdegrees.uuid) numdl,count(distinct electricfees.dfuuid) numdf,  sum(ammeterdegrees.blhdl) degree,sum(electricfees.actualpay) pay " +
        		"from  ammeterdegrees,electricfees,(select * from allinfo_view where rownum < 90000) tt " +
        		"where ammeterdegrees.ammeterid_fk = tt.dbid and electricfees.ammeterdegreeid_fk = ammeterdegrees.ammeterdegreeid " +
        		"and ammeterdegrees.manualauditstatus = '1'  and electricfees.manualauditstatus='1'  and to_char(ammeterdegrees.endmonth,'yyyy-mm') >= '"+begintime+"' and to_char(ammeterdegrees.endmonth,'yyyy-mm') <= '"+endtime+"' group by tt.jzname)aa " +
        		"where z.xuni = '0' and aa.jzname=z.jzname and z.id = d.zdid and d.dbyt = 'dbyt01'"+zd.toString();
       
        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println("--"+sql.toString());
        try {
            db.connectDb();
            String strall="select count(*) from (select z.zdcode,aa.jzname,(case  when z.shi is not null then(select agname from d_area_grade where agcode = z.shi) else '' end) " +
    		"|| (case when z.xian is not null then (select distinct agname from d_area_grade where agcode = z.xian) else '' end) " +
    		"|| (case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = z.xiaoqu)  else '' end) as szdq," +
    		" aa.numdl,aa.numdf,aa.degree, aa.pay " +
    		"from zhandian z, dianbiao d,(select tt.jzname,count(distinct ammeterdegrees.uuid) numdl,count(distinct electricfees.dfuuid) numdf,  sum(ammeterdegrees.blhdl) degree,sum(electricfees.actualpay) pay " +
    		"from  ammeterdegrees,electricfees,(select * from allinfo_view where rownum < 90000) tt " +
    		"where ammeterdegrees.ammeterid_fk = tt.dbid and electricfees.ammeterdegreeid_fk = ammeterdegrees.ammeterdegreeid " +
    		"and ammeterdegrees.manualauditstatus = '1'  and electricfees.manualauditstatus='1'  and to_char(ammeterdegrees.endmonth,'yyyy-mm') >= '"+begintime+"' and to_char(ammeterdegrees.endmonth,'yyyy-mm') <= '"+endtime+"' group by tt.jzname)aa " +
    		"where z.xuni = '0' and aa.jzname=z.jzname and z.id = d.zdid and d.dbyt = 'dbyt01'"+zd.toString()+")";
            System.out.println(strall.toString());
            rs = db.queryAll(strall.toString());
            if (rs.next()) {
            	this.setAllPage((rs.getInt(1)+14)/15);
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
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

    //站的能耗查询导出
    
public synchronized ArrayList getPageData(String begintime,
            String endtime, String shi,
            String qx, String xiaoqu) {
ArrayList list = new ArrayList();
System.out.println(begintime+endtime);
StringBuffer zd = new StringBuffer();
if (shi.equals("0")) {
zd.append("");
} else if (qx.equals("0")) {
zd.append(" and z.shi='" + shi + "'");
} else if (xiaoqu.equals("0")) {
zd.append(" and z.xian='" + qx + "'");
} else {
zd.append(" and z.xiaoqu='" + xiaoqu + "'");
}

CTime ct = new CTime();
String kjnd = ct.formatShortDate().substring(0, 4);
String sql="select z.zdcode,aa.jzname,(case  when z.shi is not null then(select agname from d_area_grade where agcode = z.shi) else '' end) " +
"|| (case when z.xian is not null then (select distinct agname from d_area_grade where agcode = z.xian) else '' end) " +
"|| (case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = z.xiaoqu)  else '' end) as szdq," +
" aa.numdl,aa.numdf,aa.degree, aa.pay " +
"from zhandian z, dianbiao d,(select tt.jzname,count(distinct ammeterdegrees.uuid) numdl,count(distinct electricfees.dfuuid) numdf,  sum(ammeterdegrees.blhdl) degree,sum(electricfees.actualpay) pay " +
"from  ammeterdegrees,electricfees,(select * from allinfo_view where rownum < 90000) tt " +
"where ammeterdegrees.ammeterid_fk = tt.dbid and electricfees.ammeterdegreeid_fk = ammeterdegrees.ammeterdegreeid " +
"and ammeterdegrees.manualauditstatus = '1'  and electricfees.manualauditstatus='1'  and to_char(ammeterdegrees.endmonth,'yyyy-mm') >= '"+begintime+"' and to_char(ammeterdegrees.endmonth,'yyyy-mm') <= '"+endtime+"' group by tt.jzname)aa " +
"where z.xuni = '0' and aa.jzname=z.jzname and z.id = d.zdid and d.dbyt = 'dbyt01'"+zd.toString();

DataBase db = new DataBase();
ResultSet rs = null;
System.out.println("--"+sql.toString());
try {
db.connectDb();
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
