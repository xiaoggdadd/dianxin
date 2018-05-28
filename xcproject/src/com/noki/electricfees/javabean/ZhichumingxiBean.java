package com.noki.electricfees.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class ZhichumingxiBean {
	 private int allPage;
	    public void setAllPage(int allpage) {
	        this.allPage = allpage;

	    }

	    public int getAllPage() {
	        return this.allPage;
	    }
//支出明细 站点查询
	    public synchronized ArrayList getPageData(int start, String begintime,
	                                              String endtime, String shi,
	                                              String qx, String xiaoqu,String zdsx) {
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
	         if(!zdsx.equals("0")){
	        	 zd.append("and z.PROPERTY='"+zdsx+"'");
	         }

	        CTime ct = new CTime();
	        String kjnd = ct.formatShortDate().substring(0, 4);
	        StringBuffer s2 = new StringBuffer();
	        StringBuffer s3=new StringBuffer();
	        s3.append("select z.zdcode,z.jzname,count(o.id)as dbcount,count(y.id)as jfcs, sum(y.money)as jfcount, " +
	        		"(select tt.name from indexs tt where o.dfzflx = tt.code  and tt.type = 'dfzflx') as dfzflx  " +
	        		"from zhandian z, dianbiao o,yufufeiview y where z.xuni = '0' and o.dbyt = 'dbyt01' and z.qyzt='1' " +
	        		"and o.zdid = z.id and y.prepayment_ammeterid=o.dbid and to_char(y.endmonth,'yyyy-mm')>= '" +begintime + "' " +
	        		"and to_char(y.startmonth,'yyyy-mm')<= '" + endtime +"' and o.dfzflx = 'dfzflx03'  "+zd.toString()+" " +
	        		"group by z.zdcode,z.jzname,dfzflx");
	        s2.append("select z.zdcode,z.jzname,count( distinct o.id) as dbcount,count(distinct df.electricfee_id) as jfcs,sum(df.actualpay) as JFCOUNT," +
	        		"(select tt.name from indexs tt where o.dfzflx=tt.code and tt.type='dfzflx')as dfzflx  " +
	        		"from zhandian z, dianbiao o,dianfeiview df,dianduview dl " +
	        		"where z.xuni = '0'  and o.dbyt = 'dbyt01' and z.qyzt='1' and o.zdid = z.id " +
	        		" and o.dfzflx = 'dfzflx01'  and o.dbid=dl.ammeterid_fk  " +
	        		"and dl.ammeterdegreeid=df.ammeterdegreeid_fk " +
	        		"and to_char(dl.endmonth,'yyyy-mm') >= '" +begintime + "' and to_char(dl.endmonth,'yyyy-mm') <= '" + endtime +"'" +
	        		"  and o.dfzflx='dfzflx01' "+zd.toString()+" group by z.zdcode,z.jzname,dfzflx");
            
	        DataBase db = new DataBase();
	        ResultSet rs = null;
	        ResultSet rs1 = null;
	        
	        System.out.println("支出明细："+s3.toString());
	        System.out.println("支出明细2："+s2.toString());
	        try {
	            db.connectDb();
	            StringBuffer strall = new StringBuffer();
	            StringBuffer strall1 = new StringBuffer();
	            strall.append("select count(distinct z.zdcode) " +
		        		"from zhandian z, dianbiao o,yufufeiview y where z.xuni = '0' and o.dbyt = 'dbyt01' and z.qyzt='1' " +
		        		"and o.zdid = z.id and y.prepayment_ammeterid=o.dbid and to_char(y.endmonth,'yyyy-mm')>= '" +begintime + "' " +
		        		"and to_char(y.startmonth,'yyyy-mm')<= '" + endtime +"' and o.dfzflx = 'dfzflx03'  "+zd.toString()+"");
	            strall1.append("select count(distinct z.zdcode)" +
		        		"from zhandian z, dianbiao o,dianfeiview df,dianduview dl " +
		        		"where z.xuni = '0'  and o.dbyt = 'dbyt01' and z.qyzt='1' and o.zdid = z.id " +
		        		" and o.dfzflx = 'dfzflx01'  and o.dbid=dl.ammeterid_fk  " +
		        		"and dl.ammeterdegreeid=df.ammeterdegreeid_fk " +
		        		"and to_char(dl.endmonth,'yyyy-mm') >= '" +begintime + "' and to_char(dl.endmonth,'yyyy-mm') <= '" + endtime +"'" +
		        		"  and o.dfzflx='dfzflx01' "+zd.toString()+" ");
	            System.out.println("--"+strall.toString());
	            System.out.println("--"+strall1.toString());
	            rs = db.queryAll(strall.toString());
	            int ii=0;
	            if (rs.next()) {
	            	ii=rs.getInt(1);
	            }
	            rs = db.queryAll(strall1.toString());
	            if (rs.next()) {
	            	ii=ii+rs.getInt(1);
	            	
	            }
	            //System.out.println("--"+ii);
	            this.setAllPage((ii+14)/15);
	            NPageBean nbean = new NPageBean();
	            rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
	            rs1 = db.queryAll(nbean.getQueryStr(start, s3.toString()));
	           
	            Query query = new Query();
	            list = query.query(rs);
	            list1=query.query(rs1);
	            for(int i=8;i<list1.size();i++){
	            	list.add(list1.get(i));
	            }
	            
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
