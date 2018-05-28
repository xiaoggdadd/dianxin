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
 * <p>Title: 区县能耗比例分析</p>
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
public class QuXianNengHaoFenXi {
    public QuXianNengHaoFenXi() {
    }

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }

    public synchronized ArrayList getPageData(int start, String begintime,
                                              String endtime, String shi) {
        ArrayList list = new ArrayList();
        
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        StringBuffer s2 = new StringBuffer();
//        s2.append("select tt.*,ttt.* from" +
//        		"(select sum(df_total)as sum_df,sum(dl_total)as sum_dl from(select t.xiancode, sum(t.df_total) as df_total, sum(t.dl_total) as dl_total from MONTHSUMMARY t " +
//        		"where shi = '"+shi+"' and t.summarymonth >= '"+begintime+"' and t.summarymonth <= '"+endtime+"' group by t.xiancode)) ttt," +
//        		"(select t.xiancode, sum(t.df_total) as df_total, sum(t.dl_total) as dl_total from MONTHSUMMARY t " +
//        		"where shi = '"+shi+"' and t.summarymonth >= '"+begintime+"' and t.summarymonth <= '"+endtime+"' group by t.xiancode)tt");
        //s2.append("select t.xiancode,sum(t.df_total)as df_total,sum(t.dl_total)as dl_total  from MONTHSUMMARY t " +
        //		"where shi='"+shi+"'and t.summarymonth>='"+begintime+"' and t.summarymonth<='"+endtime+"' group by t.xiancode");
        s2.append("SELECT (SELECT AA.AGNAME FROM D_AREA_GRADE AA WHERE AA.AGCODE = Z.XIAN)AS xian,SUM(A.BLHDL)AS dl,SUM(E.ACTUALPAY)AS DF  FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES A, ELECTRICFEES E WHERE Z.ID = D.ZDID  AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK  AND Z.SHI = '"+shi+"'  AND D.DBYT = 'dbyt01' AND TO_CHAR(A.STARTMONTH,'yyyy-mm') >= '"+begintime+"' AND TO_CHAR(A.STARTMONTH,'yyyy-mm') <= '"+endtime+"' AND E.MANUALAUDITSTATUS >= '1' GROUP BY Z.XIAN");
        System.out.println(s2.toString());
        DataBase db = new DataBase();
        ResultSet rs = null;

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
