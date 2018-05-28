package com.noki.jizhan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

public class SiteAnalysisSheet {
	
	//站点分析表   站点查询  机房个数 ，基站个数，接入网个数
	public String getCount(String whereStr,String loginId,String str){
		String sql = "";
	    String count="";
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
				fzzdstr = getFuzeZdid(db,loginId);
			
			StringBuffer strall = new StringBuffer();
			strall.append("select distinct  count(*) from zhandian z,dianbiao D where   ((z.xiaoqu in (select  t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")"+whereStr+str+" and z.id=d.zdid and z.qyzt='1' ");
			System.out.println(strall.toString() + "***********************");
			rs = db.queryAll(strall.toString());
           
            		 while(rs.next()){
					count=rs.getString(1);
            		 }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
            }
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
		return count;
	}
	//站点分析表   站点查询
	public ArrayList getCountinfor(String whereStr,String loginId,String str){
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
				fzzdstr = getFuzeZdid(db,loginId);
			
			StringBuffer strall = new StringBuffer();
			strall.append("select  I.NAME,i.code,count(distinct z.zdcode)as acount from zhandian z,dianbiao D,indexs i  where "+str+" and z.id=d.zdid  and z.qyzt='1' and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")"+whereStr+" group by i.name,i.code ");
			System.out.println(strall.toString() + "***********************");
			rs = db.queryAll(strall.toString());

			Query query = new Query();
			list = query.query(rs);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
            }
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
	//站点分析表  站点二级查询
	public ArrayList getCountinformation(String whereStr,String loginId,String str){
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
				fzzdstr = getFuzeZdid(db,loginId);
			
			StringBuffer strall = new StringBuffer();
			strall.append("select distinct " +
					" (select NAME from indexs where CODE = z.STATIONTYPE and type='stationtype') as stationtype,"+
					"  z.zdcode,z.jzname,(case  when z.xian is not null then (select distinct agname from d_area_grade where agcode = z.xian)  else ''  end) || (case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = z.xiaoqu) else '' end) as szdq  from zhandian z,dianbiao d  where "+str+"  and z.id=d.zdid  and z.qyzt='1' and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")"+whereStr+" ");
			System.out.println(strall.toString() + "***********************");
			rs = db.queryAll(strall.toString());

			Query query = new Query();
			list = query.query(rs);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
            }
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
	//负责站点条件
	private String getFuzeZdid(DataBase db, String loginid) throws DbException, SQLException {
		ResultSet rs = null;

		rs = db
				.queryAll("select begincode,endcode from per_zhandian where accountid='"
						+ loginid
						+ "' and begincode is not null and endcode is not null");
		String cxtj = new String("");
		while (rs.next()) {			
				cxtj=cxtj+" or (zdcode>='" + rs.getString(1)
						+ "' and zdcode <='" + rs.getString(2) + "')";			
		}	
    System.out.println("负责站点条件："+cxtj);
		return cxtj.toString();
	}
	 //获取电表前三次电费信息
	  public synchronized ArrayList getinformationdb1(String dbid) {
			ArrayList list = new ArrayList();
		    StringBuffer sql = new StringBuffer();
		    sql.append("  select zd.jzname,zd.zdcode,d.dbname,d.csds, to_char(d.cssytime,'yyyy-mm-dd') cssytime, d.beilv,to_char(ad.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,to_char(ad.THISDATETIME,'yyyy-mm-dd') THISDATETIME, ad.FLOATDEGREE,"+
		    			"(case when d.dbyt='dbyt03'  then '是'else '否'end) as dbytpd,"+
		    			"(case when zd.shi is not null then(select distinct agname from d_area_grade where agcode = zd.shi)else ''end) ||(case when zd.xian is not null then(select distinct agname from d_area_grade where agcode = zd.xian)else ''end) || (case when zd.xiaoqu is not null then(select distinct agname from d_area_grade where agcode = zd.xiaoqu)else ''end) as szdq,"+
		    			"(select acc.name from account acc where acc.accountname = ef.entrypensonnel) as ENTRYPENSONNEL,to_char(ef.ENTRYTIME,'yyyy-mm-dd hh24:MI:SS') ENTRYTIME,ad.LASTDEGREE,ad.THISDEGREE,ad.BLHDL as ACTUALDEGREE, ef.UNITPRICE,ef.FLOATPAY,ef.ACTUALPAY, to_char(ad.startmonth,'yyyy-mm') startmonth,to_char(ad.endmonth,'yyyy-mm') endmonth,"+
		    			"(select t.name from indexs t where t.code = zd.jztype and type='ZDLX') as jztype,(select name from indexs where code=d.YDSB and type = 'YDSB') as ydsb,d.DLLX,(select name from account where accountname = ef.INPUTOPERATOR) as INPUTOPERATOR,ef.INPUTDATETIME,to_char(ef.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,(select name from account where accountname = ef.PAYOPERATOR) as PAYOPERATOR,ef.AUTOAUDITSTATUS,ef.MANUALAUDITSTATUS"+
		    			" from dianbiao d, zhandian zd, dianduview ad, dianfeiview ef where zd.id = d.zdid and d.dbid = ad.ammeterid_fk and ad.ammeterdegreeid = ef.ammeterdegreeid_fk"+
					       " and rownum<5 and zd.zdcode ='"+dbid+"' order by ENTRYTIME");
		    
		    DataBase db = new DataBase();
		    ResultSet rs = null;
		    try {
		      System.out.println("getinformationdb:"+sql);
		      db.connectDb();		      
		      rs = db.queryAll(sql.toString());
		      Query query = new Query();
		      list = query.query(rs);
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return list;
		  }	 
	
	
}
