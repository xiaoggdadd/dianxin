package com.noki.electricfees.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class TanxiaoyujingBean {
	private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }
//摊销报警
    public synchronized ArrayList getPageData(int start,String whereStr,String loginId) {
        ArrayList list = new ArrayList();
       
		String sql="select  z.jzname,z.ZDCODE,d.dbid,p.money,i.name,p.endmonth " +
				"from prepayment p,zhandian z,dianbiao d,indexs i,(select  r.prepayment_ammeterid,max(r.endmonth)as endmonth from prepayment r group by r.prepayment_ammeterid) e " +
				"where   p.prepayment_ammeterid=d.dbid and e.endmonth=p.endmonth and e.prepayment_ammeterid=p.prepayment_ammeterid and d.zdid=z.id and z.qyzt='1' and i.code=d.dfzflx "+whereStr+" " +
						" and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))) order by p.endmonth,z.jzname";
       
        
        DataBase db = new DataBase();
        ResultSet rs = null;
        
        System.out.println("摊销报警:"+sql.toString());
        try {
            db.connectDb();
            String strall="select count(*) from prepayment p,zhandian z,dianbiao d,indexs i,(select  r.prepayment_ammeterid,max(r.endmonth)as endmonth from prepayment r group by r.prepayment_ammeterid) e " +
            		"where p.prepayment_ammeterid=d.dbid and e.endmonth=p.endmonth and e.prepayment_ammeterid=p.prepayment_ammeterid and d.zdid=z.id and z.qyzt='1' and i.code=d.dfzflx"+whereStr+" and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"')))";
            rs = db.queryAll(strall.toString());
            if (rs.next()) {
            	this.setAllPage((rs.getInt(1)+14)/15);
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
           
            Query query = new Query();
            list = query.query(rs);
            //System.out.println(list.toString());
            
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
