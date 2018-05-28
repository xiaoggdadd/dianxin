package com.noki.electricfees.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class DingqitanxiaoBean {
	private int allPage;
    public void setAllPage(int allpage) {
        this.allPage = allpage;

    }

    public int getAllPage() {
        return this.allPage;
    }
//定期摊销 查询
    public synchronized ArrayList getPageData(int start, String begintime,
                                              String endtime, String shi,
                                              String qx, String xiaoqu) {
        ArrayList list = new ArrayList();
        ArrayList list1 = new ArrayList();
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
        StringBuffer s2 = new StringBuffer();
        StringBuffer s3=new StringBuffer();
        s3.append("select  distinct z.zdcode,z.jzname,o.dbid,");
        s3.append("(select tt.name from indexs tt where o.dfzflx=tt.code and tt.type='dfzflx')as dfzflx,");
        s3.append("(select sum(e.money) from prepayment e,dianbiao d where  e.prepayment_ammeterid=d.dbid");
        s3.append(" and to_char(e.endmonth,'yyyy-mm') >='" +begintime + "' ");
        s3.append(" and to_char(e.endmonth,'yyyy-mm') <='" + endtime +"'  and e.prepayment_ammeterid=d.dbid and d.dbyt='dbyt01' and d.zdid=z.id) as jfcount,");
        s3.append("o.yhdl,");
        s3.append("(select ee.money from prepayment ee  where ee.id in (select  t.id from prepayment t ");
        s3.append("where t.startmonth=(select max(t1.startmonth)from prepayment t1,dianbiao d where to_char(t1.startmonth,'yyyy-mm')<'"+begintime+"' and t1.prepayment_ammeterid=t.prepayment_ammeterid ");
        s3.append("and t.prepayment_ammeterid=d.dbid   and d.zdid=z.id  and d.dbyt='dbyt01' ))) as jf");
        s3.append("  from zhandian z,dianbiao o  where z.xuni='0' and  o.dbyt='dbyt01' and z.qyzt='1' and o.zdid=z.id and o.dfzflx<>'dfzflx01'"+zd.toString());
        System.out.println("定期摊销："+s3.toString());

        
        DataBase db = new DataBase();
        ResultSet rs = null;
        
       
        try {
            db.connectDb();
            StringBuffer strall = new StringBuffer();
            strall.append("select count(*) from zhandian z,dianbiao o where z.xuni='0' and o.dbyt = 'dbyt01'  and z.qyzt='1'  and o.zdid = z.id and o.dfzflx <> 'dfzflx01' "+zd.toString());
            rs = db.queryAll(strall.toString());
            if (rs.next()) {
            	this.setAllPage((rs.getInt(1)+14)/15);
            }
            NPageBean nbean = new NPageBean();
            rs = db.queryAll(nbean.getQueryStr(start, s3.toString()));
           
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
