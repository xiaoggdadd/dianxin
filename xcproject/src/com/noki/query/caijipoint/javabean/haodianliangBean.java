package com.noki.query.caijipoint.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;
import com.noki.mobi.schedule.CaijiDB;

public class haodianliangBean {
	private String shi;
	private String quxian;
	private String zdname;
	private String zdtype;
	private String startmonth;
	private String endmonth;
	private int allPage;
	private String zhengchang;
	private String zhongduan;
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getQuxian() {
		return quxian;
	}
	public void setQuxian(String quxian) {
		this.quxian = quxian;
	}
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public String getZdtype() {
		return zdtype;
	}
	public void setZdtype(String zdtype) {
		this.zdtype = zdtype;
	}
	public String getStartmonth() {
		return startmonth;
	}
	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}
	public String getEndmonth() {
		return endmonth;
	}
	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}


	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}
	public int getAllPage() {
		return this.allPage;
	}	
	//采集点电表耗电量明细left
	public synchronized ArrayList getHaodian(int start, String whereStr,
			String loginId) {
		System.out.println("ElectricFeesBean-getPageData:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select zd.jzname,zd.STATIONTYPE,zd.PROPERTY,d.dllx,d.dbid,i.name,zd.zdcode,a.ammeterid_fk,d.dbname,sum(a.blhdl) as co from dianbiao d,zhandian zd,ammeterdegrees a,indexs i "
					+ "  where zd.id=d.zdid and zd.qyzt='1' and d.dbqyzt='1' and d.dbid=a.ammeterid_fk and i.code=d.ydsb and i.type='YDSB' and d.dbyt='dbyt02' "
					+ whereStr
					+ " group by zd.jzname,d.dbname,a.ammeterid_fk,d.dllx,zd.zdcode,i.name,d.dbid,zd.STATIONTYPE,zd.PROPERTY";
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet rs = null;
		try {
			db.connectDb();
			
			System.out.println("采集点电表耗电量明细left:"+sql.toString());
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
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
	//采集点电表耗电量明细  站点查询  1
	public synchronized ArrayList getHaodian1(int start, String whereStr,
			String loginId) {
		System.out.println("ElectricFeesBean-getPageData:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select distinct zd.jzname,(select t.name from indexs t where t.code = zd.STATIONTYPE) as STATIONTYPE,(select t.name from indexs t where t.code = zd.property) as property,zd.zdcode from zhandian zd,dianbiao db,ammeterdegrees a "
					+ "  where zd.id=db.zdid and zd.qyzt='1' and db.dbid=a.ammeterid_fk and db.dbyt='dbyt02'"
					+ whereStr+" order by zdcode";
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet rs = null;
		try {
			db.connectDb();
			
			System.out.println("采集点电表耗电量明细 1:"+sql.toString());
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
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
	//采集点电表耗电量明细rigth
	public synchronized ArrayList getHaodianliang(int start,String whereStr,String ammeterid_fk) {
		
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		DataBase db = new DataBase();
		sql = "select to_char(a.thisdatetime,'yyyy-mm-dd') thisdatetime,to_char(a.lastdatetime,'yyyy-mm-dd') lastdatetime,a.blhdl,d.dbname from ammeterdegrees a,dianbiao d "
				+ " where d.dbid=a.ammeterid_fk and ammeterid_fk='"+ammeterid_fk+"'"
				+ whereStr
				+"order by a.thisdatetime desc";

		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			System.out.println("采集点电表耗电量明细rigth"+sql.toString());
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
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
	//采集点电表耗电量明细3
public synchronized ArrayList getHaodianliang1(int start,String whereStr,String zdcode) {
		
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		DataBase db = new DataBase();
		sql = "select distinct db.dbid,db.dbname,dllx,(select t.name from indexs t where t.code = db.ydsb) as ydsb from dianbiao db,zhandian zd,ammeterdegrees a where zd.id=db.zdid and zd.qyzt='1' and db.dbqyzt='1' and zd.zdcode='"
				+zdcode+"' and db.dbid=a.ammeterid_fk"
				+ whereStr;
				

		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			System.out.println("采集点电表耗电量明细电表查询："+sql.toString());
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
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
public synchronized ArrayList getPageData_table1(String whereStr,String ammeterid_fk) {	

		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		DataBase db1 = new DataBase();
		CaijiDB db = new CaijiDB();
		
		//if(ammeterid_fk!=null){
		//	ammeterid_fk=ammeterid_fk.substring(0,14);
		//}
		if(ammeterid_fk!=null&&ammeterid_fk.length()==9){
			sql = "select d.dbname,t.getdatetime,t.datavalue,t.stname,case t.flag  when 0 then  '网络中断' when 1 then '正常' end flag from tabcjqd t,dianbiao d where d.dbid(+)=stname and stname like '%"+ammeterid_fk+"%'"+ whereStr+" order by getdatetime desc,stname";
		}else{
			sql = "select d.dbname,t.getdatetime,t.datavalue,t.stname,case t.flag  when 0 then  '网络中断' when 1 then '正常' end flag from tab1view t,dianbiao d where d.dbid(+)=stname and stname like '%"+ammeterid_fk+"%'"+ whereStr+" order by getdatetime desc,stname";
		}
		ResultSet rs = null;
		try {
			if(ammeterid_fk!=null&&ammeterid_fk.length()==9){
				db1.connectDb();
				NPageBean nbean = new NPageBean();
				System.out.println("采集详细信息查询："+sql.toString());
				rs = db1.queryAll(sql.toString());
			}else{
				db.connectDb();
				NPageBean nbean = new NPageBean();
				System.out.println("采集详细信息查询："+sql.toString());
				rs = db.queryAll(sql.toString());
			}

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
				db1.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
	private String getFuzeZdid(DataBase db, String loginid) throws DbException,
	SQLException {
		ResultSet rs = null;
		
		rs = db
				.queryAll("select begincode,endcode from per_zhandian where accountid='"
						+ loginid
						+ "' and begincode is not null and endcode is not null");
		String cxtj = new String("");
		while (rs.next()) {
			cxtj = cxtj + " or (zdcode>='" + rs.getString(1)
					+ "' and zdcode <='" + rs.getString(2) + "')";
		}
		rs.close();
		db.close();
		System.out.println("负责站点条件：" + cxtj);
		return cxtj.toString();
	}
	//采集点电量查询
	public synchronized ArrayList getCollect(int start, String whereStr,
			String loginId,String str) {
		System.out.println("ElectricFeesBean-getPageData:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select z.id,z.jzname,z.zdcode,count(aa.dbid)as dbid,sum(aa.co) as co,bb.so " +
					"from (select d.zdid,d.dbid,sum(a.blhdl) as co  " +
					"from dianbiao d, ammeterdegrees a where  d.dbid = a.ammeterid_fk  " +
					" and d.ydsb='ybsb05'  and d.dbyt = 'dbyt02'"+str+"  group by d.dbid,d.zdid) aa," +
					"(select D.DBID,count(a.ammeterdegreeid) so  " +
					"from dianbiao d, ammeterdegrees a where  d.dbid = a.ammeterid_fk  " +
					" and d.ydsb='ybsb05'  and d.dbyt = 'dbyt02'"+str+"  group by d.dbid) bb," +
					"zhandian z where z.id=aa.zdid and z.qyzt='1'  AND BB.DBID=AA.DBID "+whereStr+" group by z.id,z.jzname,z.zdcode,bb.so";
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet rs = null;
		try {
			db.connectDb();
			
			System.out.println("left采集点电量查询:"+sql.toString());
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
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
	//采集点电量查询详细信息 right
public synchronized ArrayList getCollectQuery(int start,String whereStr,String zdid) {
		
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		DataBase db = new DataBase();
		sql = "select z.id,to_char(a.thisdatetime,'yyyy-mm-dd') thisdatetime, to_char(a.lastdatetime,'yyyy-mm-dd') lastdatetime, sum(a.blhdl) as actualdegree,sum(a.llhdl) as llhdl " +
				"from ammeterdegrees a, dianbiao d, zhandian z " +
				"where z.id = d.zdid  and z.qyzt='1' "+whereStr+" and z.zdcode='"+zdid+"' and d.dbid = a.ammeterid_fk   " +
				"and d.ydsb = 'ybsb05'  group by z.id,a.thisdatetime,a.lastdatetime order by a.thisdatetime desc";

		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			System.out.println("采集点电量查询 right"+sql.toString());
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
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
	//采集点详细信息

public synchronized ArrayList getCollectQuery1(String last,String zdid) throws ParseException {
	
	ArrayList list = new ArrayList();
	CTime ct = new CTime();
	String sql = "";
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	Date dt=sdf.parse(last);		
    Calendar rightNow = Calendar.getInstance();
    rightNow.setTime(dt);
    rightNow.add(Calendar.DAY_OF_YEAR,1);
    Date dt1=rightNow.getTime();
    String last1 = sdf.format(dt1);
    CaijiDB db = new CaijiDB();
	sql ="select t.datavalue,t.getdatetime,t.stname,db.dbname,(select name from indexs i where i.code= db.ydsb and i.type='YDSB') ydsb from tab1view t,zhandian zd,dianbiao db "+
		 "where zd.id=db.zdid and db.dbid=t.stname  and db.ydsb='ybsb05' and zd.zdcode='"+zdid+"' " +
		 		"and t.getdatetime >= '"+last+" 00:00:00' and t.getdatetime<= '"+last1+" 00:00:00' "+" order by stname,t.getdatetime";

	ResultSet rs = null;
	try {
		db.connectDb();
		NPageBean nbean = new NPageBean();
		System.out.println("----"+sql.toString() + "***********************");
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
	System.out.println(list.toString() + "+++++++++++++");
	return list;
}
    //采集警告
    public synchronized ArrayList getCollectWarn(String str,String wherestr,String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		String fzzdstr = "";
		CaijiDB db = new CaijiDB();

		sql="select aa.* from(select z.jzname,z.zdcode, (SELECT NAME  FROM INDEXS  WHERE CODE = Z.STATIONTYPE AND TYPE = 'stationtype') AS STATIONTYPE," +
				"(SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = Z.shi)as shi,case when flag>='1' then '正常' when (flag='0' or flag is null) then '网络中断' end flag," +
				"case when flag>='1' then getdatetime when (flag='0' or flag is null) then '' end getdatetime " +
				"from zhandian z left JOIN (select c.zdid,max(flag) flag,max(getdatetime) getdatetime from tab1 a,dianbiao c where a.stname=c.dbid group by c.zdid) d on z.id = d.zdid " +
				"where z.caiji='1' and z.qyzt='1' "+str+" AND z.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')ORDER by z.shi,z.jzname) aa where 1=1 "+wherestr;
		System.out.println("采集警告"+sql.toString());
		ResultSet rs = null;
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
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
    //采集告警站点数
    public synchronized String getWarn(String str,String wherestr,String loginId) {
		
		String sql = "";
	    String count="",count1="";
	    String sum="";
	    CaijiDB db = new CaijiDB();
//		sql = "select count(a.jzname) from (select aa.* from( select z.jzname,z.zdcode,max(t.flag)as flag,max(t.getdatetime)as time " +
//				"from tab1view t, dianbiao d, zhandian z where z.id = d.zdid  " +
//				"and d.dbid = t.stname "+str+" and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))) group by z.jzname,z.zdcode)aa "+wherestr+" )a";
//       sql="select count(a.jzname) from (select aa.* from(select z.jzname,z.zdcode,max(t.flag) as flag,ta.getdatetime as time " +
//		"from tab1view t, dianbiao d, zhandian z,(select aa.stname,max(aa.getdatetime)as getdatetime from tab1view aa  group by aa.stname)ta where z.id = d.zdid  " +
//		"and d.dbid = t.stname and ta.stname=d.dbid  "+str+" and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))) group by z.jzname,z.zdcode,ta.getdatetime)aa "+wherestr+")a";
		sql="SELECT SUM(CASE WHEN FLAG >= '1' THEN 1 ELSE  0 END) YY, SUM(CASE WHEN (FLAG = '0' OR FLAG IS NULL) THEN 1 ELSE 0 END) NN FROM ZHANDIAN Z LEFT JOIN (SELECT C.ZDID, MAX(FLAG) FLAG, MAX(GETDATETIME) GETDATETIME FROM TAB1 A, DIANBIAO C WHERE A.STNAME = C.DBID  GROUP BY C.ZDID) D ON Z.ID = D.ZDID WHERE Z.CAIJI = '1' "+str+" AND Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')";
		
		ResultSet rs = null;
		try {
			db.connectDb();
			
			System.out.println(sql.toString() + "***********************");
			rs = db.queryAll(sql.toString());
           
            	try {
            		 while(rs.next()){
					count=rs.getString(1);
					count1=rs.getString(2);
					
            		 }
            		 if(count==null||count.equals(""))count="0";
            		 if(count1==null||count1.equals(""))count1="0";
            		 if(wherestr.equals("1")){
            			 sum=count;
            		 }else if(wherestr.equals("0")){
            			 sum=count1;
            		 }else {
            			 sum=Integer.parseInt(count)+Integer.parseInt(count1)+"";
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
		
		return sum;
	}
    //采集点详细信息
    public synchronized ArrayList getCollectWarn1(String str,String wherestr,String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		String sql1="";
		sql1="select  rownum,db.dbid,db.dbname,zd.jzname,am.lastdegree, am.thisdegree, to_char(am.lastdatetime,'yyyy-mm-dd') lastdatetime, to_char(am.thisdatetime,'yyyy-mm-dd') thisdatetime,am.blhdl, "+
		" (select NAME from indexs where CODE = db.ydsb and type='YDSB') as ydsb "+
		"  from ammeterdegrees am, zhandian zd,dianbiao db"+
		 " where zd.caiji = '1' and zd.id=db.zdid and db.dbid=am.ammeterid_fk and rownum<31 and db.ydsb='ybsb05'" +str+" ORDER BY to_char(am.thisdatetime,'yyyy-mm-dd') DESC";
		   

		ResultSet rs = null;
		try {
			db.connectDb();
			
			System.out.println(sql1.toString() + "***********************");
			rs = db.queryAll(sql1.toString());

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
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
    //主页面采集告警站点数
    public synchronized ArrayList<haodianliangBean> getWarnCount(String loginId) {
		
		String sql = "";
	    String count="";
	    CaijiDB db = new CaijiDB();
		ArrayList<haodianliangBean> list=new ArrayList<haodianliangBean>();
//		sql = "select count(a.jzname) from (select aa.* from( select z.jzname,z.zdcode,max(t.flag)as flag,max(t.getdatetime)as time " +
//				"from TAB1 t, dianbiao d, zhandian z where z.id = d.zdid  " +
//				"and d.dbid = t.stname  and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"')))group by z.jzname,z.zdcode)aa "+str+")a";
//		sql="select count(a.jzname) from (select aa.* from(select z.jzname,z.zdcode,max(t.flag) as flag,ta.getdatetime as time " +
//		"from tab1view t, dianbiao d, zhandian z,(select aa.stname,max(aa.getdatetime)as getdatetime from tab1view  aa   group by aa.stname)ta where z.id = d.zdid  " +
//		"and d.dbid = t.stname and ta.stname=d.dbid   and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))) group by z.jzname,z.zdcode,ta.getdatetime)aa "+str+")a";
		sql="SELECT SUM(CASE WHEN FLAG >= '1' THEN 1 ELSE  0 END) YY, SUM(CASE WHEN (FLAG = '0' OR FLAG IS NULL) THEN 1 ELSE 0 END) NN FROM ZHANDIAN Z LEFT JOIN (SELECT C.ZDID, MAX(FLAG) FLAG, MAX(GETDATETIME) GETDATETIME FROM TAB1 A, DIANBIAO C WHERE A.STNAME = C.DBID  GROUP BY C.ZDID) D ON Z.ID = D.ZDID WHERE Z.CAIJI = '1' AND Z.SHSIGN='1' AND Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')";
		ResultSet rs = null;
		try {
			db.connectDb();
			
			System.out.println( "***********************"+sql.toString() );
			rs = db.queryAll(sql.toString());
           
            	try {
            		 while(rs.next()){
            			 haodianliangBean bean=new haodianliangBean();
            			 bean.setZhengchang(rs.getString(1));
            			 bean.setZhongduan(rs.getString(2));
            			 list.add(bean);
					
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
		
		return list;
	}
	public void setZhengchang(String zhengchang) {
		this.zhengchang = zhengchang;
	}
	public String getZhengchang() {
		return zhengchang;
	}
	public void setZhongduan(String zhongduan) {
		this.zhongduan = zhongduan;
	}
	public String getZhongduan() {
		return zhongduan;
	}
}
