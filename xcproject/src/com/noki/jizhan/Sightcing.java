package com.noki.jizhan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.CTime;
import com.noki.util.Query;


public class Sightcing {
    public Sightcing() {
    }

    public synchronized ArrayList getleft(String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		DataBase db = new DataBase();
		try {
			sql = "select i.name,i.code stationtype,sum(case when caiji=1 then 1 else 0 end) caiji ,sum(case when caiji=0 then 1 else 0 end) fou from zhandian  a,indexs i"+
			" where i.code = a.stationtype(+) and i.type = 'stationtype' group by i.name,i.code order by i.code";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}

		ResultSet rs = null;
		try {
			db.connectDb();
			
			System.out.println(sql.toString() + "***********************");
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
    public synchronized ArrayList getright(String loginId,String stationtype) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		DataBase db = new DataBase();
		try {
			sql = "select i.name,(select m.name from indexs m where type='stationtype' and m.code=i.stationtypeid) zdlx," +
				  "i.stationtypeid,i.region,i.g2,i.g3,i.olt,i.air_condition,sum(case when caiji = 1 then 1 else 0 end) caiji,sum(case when caiji = 0 then 1 else  0 end) fou from zhandian a,benchmarking i "+
			      "where i.stationtypeid=a.stationtype and i.id=a.signtypenum and i.stationtypeid='"+stationtype+"'" +
			      " group by i.name,i.stationtypeid,i.region,i.g2,i.g3,i.olt,i.air_condition,i.id order by i.id";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}

		ResultSet rs = null;
		try {
			db.connectDb();
			
			System.out.println(sql.toString() + "***********************");
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
    public synchronized ArrayList getright1(String loginId,String stationtype,String name) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		DataBase db = new DataBase();
		try {
			sql = "select count(a.lastdatetime) tian,sum(a.lastdegree) du,d.dbid,a.startmonth from zhandian z, dianbiao d, ammeterdegrees a, benchmarking b "+
			 " where z.id = d.zdid  and z.stationtype = b.stationtypeid(+) and a.ammeterid_fk = d.dbid and d.dbyt = 'dbyt02'  and b.id=z.signtypenum " +
			 "and z.stationtype='" +stationtype+"' and b.name='"+name+"'"+
			 " group by d.dbid,a.startmonth";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}

		ResultSet rs = null;
		try {
			db.connectDb();
			
			System.out.println(sql.toString() + "***********************");
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
    public synchronized ArrayList gettan(String loginId,String station,String name) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String sql = "";
		DataBase db = new DataBase();
		try {
			sql = "select distinct z.jzcode,jzname,(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)" +
					"||(case when z.shi is not null then (select agname from d_area_grade where agcode=z.xian) else '' end)" +
					"||(case when z.shi is not null then (select agname from d_area_grade where agcode=z.xiaoqu) else '' end)  as szdq," +
					"(select name from indexs i where type='ZDSX' and code=z.property) PROPERTY," +
					"(select name from indexs i where type='stationtype' and code=z.stationtype) stationtype," +
					"(select name from indexs i where type='ZDLX' and code=z.jztype) JZTYPE " +
					" from zhandian z,benchmarking b " +
					"where z.stationtype='"+station+"' and b.name='"+name+"' and z.caiji='1' and b.stationtypeid=z.stationtype and b.id=z.signtypenum order by szdq";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}

		ResultSet rs = null;
		try {
			db.connectDb();
			
			System.out.println(sql.toString() + "***********************");
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

}
