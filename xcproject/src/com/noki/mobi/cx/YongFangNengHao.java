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
 * <p>Title: 用房类型能耗统计</p>
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
public class YongFangNengHao {
    public YongFangNengHao() {
    }

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }

    public synchronized ArrayList getPageData(int start, String begintime,
                                              String endtime, String shi,
                                              String qx, String xiaoqu) {
    	
        ArrayList list = new ArrayList();
        
        StringBuffer zd = new StringBuffer();
        if (shi.equals("0")) {
            zd.append("");
        } else if (qx.equals("0")) {
            zd.append(" and tt.shi='"+shi+"'");
        } else if(xiaoqu.equals("0")){
            zd.append(" and tt.xian='"+qx+"'");
        }else{
            zd.append(" and tt.xiaoqu='"+xiaoqu+"'");
        }
        
        
         System.out.println("zd.22222222222222222"+zd.toString());
        CTime ct = new CTime();
        String kjnd = ct.formatShortDate().substring(0, 4);
        String sql="select indexs.name,tab.degree,tab.pay from indexs left join ( select tt.yflx yflx,count(*) num,sum(ammeterdegrees.blhdl) degree,sum(electricfees.actualpay) pay " +
        		"from ammeterdegrees,electricfees, (select * from allinfo_view where rownum < 120000) tt " +
        		"where  ammeterdegrees.ammeterid_fk=tt.dbid and electricfees.ammeterdegreeid_fk=ammeterdegrees.ammeterdegreeid " +
        		"and ammeterdegrees.manualauditstatus>='1' AND electricfees.manualauditstatus>='1' "+zd.toString()+" and to_char(ammeterdegrees.endmonth,'yyyy-mm')>='"+begintime+"'  " +
        		"and to_char(ammeterdegrees.endmonth,'yyyy-mm') <='"+endtime+"' and tt.dbyt='dbyt01' group by tt.yflx) tab on indexs.code=tab.yflx where indexs.type='FWLX'";
        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            System.out.println(sql.toString()+"11111111111111111");
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
