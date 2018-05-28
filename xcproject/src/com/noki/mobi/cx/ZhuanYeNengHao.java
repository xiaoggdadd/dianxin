package com.noki.mobi.cx;

import com.noki.database.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.noki.database.DataBase;
import java.sql.SQLException;
import java.util.ArrayList;
import com.noki.util.Query;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import java.util.Vector;

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
public class ZhuanYeNengHao {
	
	Connection conn = null;
	Statement sta = null;
	
    public ZhuanYeNengHao() {
    }

    private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }

    public synchronized ArrayList getPageData(String begintime,
                                              String endtime, String shi,
                                              String qx, String xiaoqu) {
        
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

        ArrayList list = new ArrayList();
        String sql="select indexs.name,tab.num,tab.degree,tab.pay from indexs left join (select tt.jztype jztype,count(distinct tt.id) num,sum(ammeterdegrees.blhdl) degree,sum(electricfees.actualpay) pay " +
        		"from ammeterdegrees,electricfees,(select a.* from allinfo_view a where a.dbyt='dbyt01' and rownum < 120000) tt " +
        		"where ammeterdegrees.ammeterid_fk = tt.dbid and electricfees.ammeterdegreeid_fk = ammeterdegrees.ammeterdegreeid " +
        		" and electricfees.manualauditstatus>='1'  "+zd.toString()+" and to_char(ammeterdegrees.endmonth,'yyyy-mm') >= '"+begintime+"' and to_char(ammeterdegrees.endmonth,'yyyy-mm') <= '"+endtime+"' group by tt.jztype) tab" +
        		"  on indexs.code = tab.jztype where indexs.type = 'ZDLX'";
       
        DataBase db = new DataBase();
        ResultSet rs = null;
        System.out.println("sql:"+sql);

        try {
        	db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());
            Query query = new Query();
            list = query.query(rs);
        }

        catch (Exception de) {
            de.printStackTrace();
        }

        finally {
        	db.free(rs, sta, conn);
        }
        return list;
    }



    @SuppressWarnings("unchecked")
	public synchronized Vector getPageData_dc(String begintime,
                                              String endtime, String shi, String qx,
                                              String xiaoqu) {
        Vector list = new Vector();
        StringBuffer s2 = new StringBuffer();
        s2.append("select t.name,0 as zdcount,0 as ydcount,0 as jfcount from zdtype t order by t.code");

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
            db.connectDb();
            Vector vt = new Vector();
            vt.add("序号");
            vt.add("集团报表类型名称");
            vt.add("站点数目");
            vt.add("合计用电量");
            vt.add("合计交费数额");
            list.add(vt);
            rs = db.queryAll(s2.toString());
            int i = 1;
            while (rs.next()) {
                Vector vi = new Vector();
                vi.add(i++);
                vi.add(rs.getString(1));
                vi.add(rs.getString(2));
                vi.add(rs.getString(3));
                vi.add(rs.getString(4));
                list.add(vi);
            }

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
