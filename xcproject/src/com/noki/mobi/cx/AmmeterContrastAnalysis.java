package com.noki.mobi.cx;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

public class AmmeterContrastAnalysis {
	 public ArrayList getAmmeterContrastAnalysis11(String ammeter1,String begintime,String endtime){
	    	ArrayList list=new ArrayList();
	    	DataBase db=new DataBase();
	    	ResultSet rs=null;
	    	String sql="select d.dbid,z.jzname,z.zdcode,"+
	    	        "(select t.name from indexs t where t.code=d.sszy and t.type='SSZY') as sszy,"+
	    	        "(select t.name from indexs t where t.code=d.dbyt and t.type='DBYT') as dbyt,d.dllx,"+
	    	        "(select t.name from indexs t where t.code=d.ydsb and t.type='YDSB')as ydsb,d.dbname,"+
	    	        "(select t.name from indexs t where t.code=d.linelosstype and t.type='YDSB')as linelosstype,"+
	    	        "(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)"+
	    	        "||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)"+
	    	        "||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq " +
	    			"from dianbiao d, zhandian z,ammeterdegrees a " +
	    			"where d.dbid='"+ammeter1+"' and a.startmonth>='"+begintime+"' and a.startmonth<='"+endtime+"'" +" and z.id=d.zdid"+
	    			" and a.AMMETERID_FK=d.dbid";
	    	try {
				db.connectDb();
				rs=db.queryAll(sql);
				Query query=new Query();
				list=query.query(rs);
				
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println("-1-"+sql.toString());
	    	System.out.println("-1-"+list.toString());
	    	return list;
	    }
    public ArrayList getAmmeterContrastAnalysis1(String ammeter1,String begintime,String endtime){
    	ArrayList list=new ArrayList();
    	DataBase db=new DataBase();
    	ResultSet rs=null;
    	String sql="select d.dbid,a.startmonth,sum(a.actualdegree),sum(e.actualpay) " +
    			"from dianbiao d, ammeterdegrees a, electricfees e " +
    			"where d.dbid = a.ammeterid_fk and a.ammeterdegreeid = e.ammeterdegreeid_fk " +
    			"and d.dbid='"+ammeter1+"' and a.startmonth>='"+begintime+"' and a.startmonth<='"+endtime+"'" +
    			"group by d.dbid,a.startmonth order by a.startmonth asc";
    	try {
			db.connectDb();
			rs=db.queryAll(sql);
			Query query=new Query();
			list=query.query(rs);
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("-1-"+sql.toString());
    	System.out.println("-1-"+list.toString());
    	return list;
    }
    public ArrayList getAmmeterContrastAnalysis2(String ammeter2,String begintime,String endtime){
    	ArrayList list=new ArrayList();
    	DataBase db=new DataBase();
    	ResultSet rs=null;
    	String sql="select d.dbid,a.startmonth,sum(a.actualdegree),sum(e.actualpay) " +
    			"from dianbiao d, ammeterdegrees a, electricfees e " +
    			"where d.dbid = a.ammeterid_fk and a.ammeterdegreeid = e.ammeterdegreeid_fk " +
    			"and d.dbid='"+ammeter2+"' and a.startmonth>='"+begintime+"' and a.startmonth<='"+endtime+"'" +
    			"group by d.dbid,a.startmonth order by a.startmonth asc";
    	try {
			db.connectDb();
			rs=db.queryAll(sql);
			Query query=new Query();
			list=query.query(rs);
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("-2-"+sql.toString());
    	System.out.println("-2-"+list.toString());
    	return list;
    }
}
