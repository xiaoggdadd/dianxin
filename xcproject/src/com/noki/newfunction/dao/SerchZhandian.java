package com.noki.newfunction.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jsx3.net.Request;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;

public class SerchZhandian {
	// 无电量站点查询
	public synchronized List<ElectricFeesFormBean> getWudianliang(
			String whereStr, String zcxstr1, String zcxstr2) {
		List<ElectricFeesFormBean> ls = null;
		ResultSet rs = null;
		String sql = "";
		DataBase db = new DataBase();
		try {
			sql = "select "
					+ "RNDIQU(ZD.SHI) as shi,"
					+ "RNDIQU(ZD.XIAN) as xian,"
					+ "RNDIQU(ZD.XIAOQU) as xiaoqu,"
					+ "zd.jzname,"
					+ "RTNAME(ZD.STATIONTYPE) AS STATIONTYPE,"
					+ "db.zdid,db.id,RTNAME(ZD.PROPERTY) AS PROPERTY"
					+ " from zhandian zd left join dianbiao db on zd.id=db.zdid "
					+ "where zd.shsign='1'  AND db.dbyt='dbyt03' AND zd.caiji='0' "
					+ whereStr
					+ "and NOT EXISTS"
					+ "(select z.id"
					+ " from zhandian z, dianbiao db, dianduview am "
					+ " where z.id = db.zdid  and db.dbid = am.ammeterid_fk "
					+ " and z.id=zd.id  "
					
					+ zcxstr1
					+ "  AND z.xuni = '0' AND db.dbyt='dbyt03' AND z.caiji='0')"
					+ " group by zd.shi,zd.xian,zd.xiaoqu,zd.jzname,PROPERTY,STATIONTYPE,db.zdid,db.id ";
			System.out.println("无电量电表查询:" + sql);
			rs = db.queryAll(sql);
			ls = new ArrayList<ElectricFeesFormBean>();
			while (rs.next()) {
				ElectricFeesFormBean effb = new ElectricFeesFormBean();
				effb.setShi(rs.getString("shi") != null ? rs.getString("shi")
						: "");
				effb.setXian(rs.getString("xian") != null ? rs
						.getString("xian") : "");
				effb.setXiaoqu(rs.getString("xiaoqu") != null ? rs
						.getString("xiaoqu") : "");
				effb.setJzname(rs.getString("jzname") != null ? rs
						.getString("jzname") : "");
				effb.setJztype(rs.getString("STATIONTYPE") != null ? rs
						.getString("STATIONTYPE") : "");
				effb
						.setId(rs.getString("zdid") != null ? rs.getString("zdid")
								: "");
				effb.setDbid(rs.getString("id") != null ? rs.getString("id")
						: "");
				effb.setProperty(rs.getString("PROPERTY") != null ? rs.getString("PROPERTY")
						: "");
				ls.add(effb);
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return ls;
	}

	// 当月没交费站点查询
	public synchronized List<ElectricFeesFormBean> getWudianfei(String whereStr,String zcxstr1,String zcxstr2,String flag_cx){
		List<ElectricFeesFormBean> ls = null;
		ResultSet rs = null;
		String sql = "";
		DataBase db = new DataBase();
		try{
			if("dfzflx01".equals(flag_cx)){
				sql="select " 
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) as shi,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) as xian,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) as xiaoqu,"
					+ "zd.jzname,"
					+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
					+"rtname(db.dfzflx) dfzflx,zd.id,db.dbid "
					+"from zhandian zd left join dianbiao db on zd.id = db.zdid "
					+"where zd.qyzt = '1'"
					+" and zd.shsign = '1'"
					+whereStr
					+"and NOT exists (select z.id "
					+"from zhandian z join dianbiao db on z.id = db.zdid "
					+"join dianduview am on db.dbid = am.ammeterid_fk "
					+"join dianfeiview df on am.ammeterdegreeid = df.ammeterdegreeid_fk "
					+"where z.id = zd.id "+zcxstr1
					+" and df.manualauditstatus > '0')"
					+" group by zd.shi,zd.xian,zd.xiaoqu,zd.jzname,STATIONTYPE,zd.id,dfzflx,dbid";
			}else if("dfzflx02".equals(flag_cx)||"dfzflx03".equals(flag_cx)||"dfzflx04".equals(flag_cx)){
				sql="select" 
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) as shi,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) as xian,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) as xiaoqu,"
					+ "zd.jzname,"
					+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
					+"rtname(db.dfzflx) dfzflx,zd.id,db.dbid "
					+"from zhandian zd left join dianbiao db on zd.id=db.zdid " 
					+"where zd.qyzt = '1'"
					+" and zd.shsign = '1'"
					+whereStr
					+ "and not exists( select z.id "
					+"from zhandian z left join dianbiao db on z.id = db.zdid "
					+"RIGHT join yufufeiview yff on db.dbid =yff.prepayment_ammeterid "
					+"where z.id=zd.id " +zcxstr2
					+")"
					+" group by zd.shi,zd.xian,zd.xiaoqu,zd.jzname,STATIONTYPE,zd.id,dfzflx,dbid";
			}else if("".equals(flag_cx)||"0".equals(flag_cx)||flag_cx==null){
				sql="select " 
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) as shi,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) as xian,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) as xiaoqu,"
					+ "zd.jzname,"
					+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
					+"rtname(db.dfzflx) dfzflx,zd.id,db.dbid "
					+"from zhandian zd left join dianbiao db on zd.id = db.zdid "
					+"where zd.qyzt = '1'"
					+" and zd.shsign = '1'"
					+whereStr
					+ " and not exists( select z.id "
					+"from zhandian z left join dianbiao db on z.id = db.zdid "
					+"RIGHT join yufufeiview yff on db.dbid =yff.prepayment_ammeterid "
					+"where z.id=zd.id " +zcxstr2
					+")"
					+"and NOT exists (select z.id "
					+"from zhandian z join dianbiao db on z.id = db.zdid "
					+"join dianduview am on db.dbid = am.ammeterid_fk "
					+"join dianfeiview df on am.ammeterdegreeid = df.ammeterdegreeid_fk "
					+"where z.id = zd.id  "+zcxstr1
					+" and df.manualauditstatus > '0')"
					+" group by zd.shi,zd.xian,zd.xiaoqu,zd.jzname,STATIONTYPE,zd.id,dfzflx,dbid";
			}
			System.out.println("无电费：      "+sql);
			rs = db.queryAll(sql);
			ls = new ArrayList<ElectricFeesFormBean>();
			while(rs.next()){
				ElectricFeesFormBean effb = new ElectricFeesFormBean();

				effb.setShi(rs.getString("shi") != null ? rs.getString("shi"): "");

				effb.setXian(rs.getString("xian") != null ? rs.getString("xian") : "");

				effb.setXiaoqu(rs.getString("xiaoqu") != null ? rs.getString("xiaoqu") : "");

				effb.setJzname(rs.getString("jzname") != null ? rs.getString("jzname") : "");

				effb.setJztype(rs.getString("STATIONTYPE") != null ? rs.getString("STATIONTYPE") : "");

				effb.setId(rs.getString("id") != null ? rs.getString("id"): "");

				effb.setDfzflx(rs.getString("dfzflx") != null ? rs.getString("dfzflx"): "");

				effb.setDbid(rs.getString("dbid") != null ? rs.getString("dbid"): "");

				ls.add(effb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		return ls;
	}
//站点的最后一次缴费时间
	public synchronized List<ElectricFeesFormBean> getLastPay(String whereStr,String zcxstr1,String zcxstr2,String flag_cx){
		List<ElectricFeesFormBean> ls = null;
		ResultSet rs = null;
		String sql = "";
		DataBase db = new DataBase();
		try{
			if("dfzflx01".equals(flag_cx)){
				sql=
					"select  "
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) as shi,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) as xian,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) as xiaoqu,"
					+ "zd.jzname,"
					+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
					+"rtname(db.dfzflx) dfzflx,zd.id,db.dbid,MAX(df.PAYDATETIME)jfsj,df.ACCOUNTMONTH "
					+"from zhandian zd join dianbiao db on zd.id = db.zdid "
					+"join dianduview am on db.dbid = am.ammeterid_fk "
					+"join dianfeiview df on am.ammeterdegreeid = df.ammeterdegreeid_fk "
					+"where zd.qyzt = '1' and zd.shsign = '1'  "+zcxstr1
					+" "+whereStr
					+" and df.manualauditstatus > '0'"
					+" group by zd.shi,zd.xian,zd.xiaoqu,zd.jzname,STATIONTYPE,zd.id,dfzflx,dbid,ACCOUNTMONTH"
					+"  Order by accountMonth";
				
				System.out.println("电费最后一次缴费时间：      "+sql);
				rs = db.queryAll(sql);
				ls = new ArrayList<ElectricFeesFormBean>();
				while(rs.next()){
					ElectricFeesFormBean effb = new ElectricFeesFormBean();

					effb.setShi(rs.getString("shi") != null ? rs.getString("shi"): "");

					effb.setXian(rs.getString("xian") != null ? rs.getString("xian") : "");

					effb.setXiaoqu(rs.getString("xiaoqu") != null ? rs.getString("xiaoqu") : "");

					effb.setJzname(rs.getString("jzname") != null ? rs.getString("jzname") : "");

					effb.setJztype(rs.getString("STATIONTYPE") != null ? rs.getString("STATIONTYPE") : "");

					effb.setId(rs.getString("id") != null ? rs.getString("id"): "");

					effb.setDfzflx(rs.getString("dfzflx") != null ? rs.getString("dfzflx"): "");

					effb.setDbid(rs.getString("dbid") != null ? rs.getString("dbid"): "");
					
					effb.setAccountmonth(rs.getString("ACCOUNTMONTH") != null ? rs.getString("ACCOUNTMONTH"): "");
					
					effb.setPaydatetime(rs.getString("jfsj") != null ? rs.getString("jfsj"): "");
					
					ls.add(effb);
				}
				
			}else if("dfzflx02".equals(flag_cx)||"dfzflx03".equals(flag_cx)||"dfzflx04".equals(flag_cx)){
				sql="select "
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) as shi,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) as xian,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) as xiaoqu,"
					+ "zd.jzname,"
					+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
					+"rtname(db.dfzflx) dfzflx,zd.id,db.dbid,MAX(yff.PAYDATETIME)jfsj,yff.ACCOUNTMONTH "
					+"from zhandian zd left join dianbiao db on zd.id = db.zdid "
					+"RIGHT join yufufeiview yff on db.dbid =yff.prepayment_ammeterid "
					+"where zd.qyzt = '1' and zd.shsign = '1' " +zcxstr2+whereStr
					+" group by zd.shi,zd.xian,zd.xiaoqu,zd.jzname,STATIONTYPE,zd.id,dfzflx,dbid,ACCOUNTMONTH"
					+"  Order by accountMonth";
				
				System.out.println("预付费最后一次缴费时间：      "+sql);
				rs = db.queryAll(sql);
				ls = new ArrayList<ElectricFeesFormBean>();
				while(rs.next()){
					ElectricFeesFormBean effb = new ElectricFeesFormBean();

					effb.setShi(rs.getString("shi") != null ? rs.getString("shi"): "");

					effb.setXian(rs.getString("xian") != null ? rs.getString("xian") : "");

					effb.setXiaoqu(rs.getString("xiaoqu") != null ? rs.getString("xiaoqu") : "");

					effb.setJzname(rs.getString("jzname") != null ? rs.getString("jzname") : "");

					effb.setJztype(rs.getString("STATIONTYPE") != null ? rs.getString("STATIONTYPE") : "");

					effb.setId(rs.getString("id") != null ? rs.getString("id"): "");

					effb.setDfzflx(rs.getString("dfzflx") != null ? rs.getString("dfzflx"): "");

					effb.setDbid(rs.getString("dbid") != null ? rs.getString("dbid"): "");
					
					effb.setAccountmonth(rs.getString("ACCOUNTMONTH") != null ? rs.getString("ACCOUNTMONTH"): "");
					
					effb.setPaydatetime(rs.getString("jfsj") != null ? rs.getString("jfsj"): "");
					
					ls.add(effb);
				}
				
			}else if("".equals(flag_cx)||"0".equals(flag_cx)||flag_cx==null){
				sql=
					"select  "
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) as shi,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) as xian,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) as xiaoqu,"
					+ "zd.jzname,"
					+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
					+"rtname(db.dfzflx) dfzflx,zd.id,db.dbid,MAX(df.PAYDATETIME)jfsj,df.ACCOUNTMONTH "
					+"from zhandian zd join dianbiao db on zd.id = db.zdid "
					+"join dianduview am on db.dbid = am.ammeterid_fk "
					+"join dianfeiview df on am.ammeterdegreeid = df.ammeterdegreeid_fk "
					+"where zd.qyzt = '1' and zd.shsign = '1'  "+zcxstr1
					+" "+whereStr
					+" and df.manualauditstatus > '0'"
					+" group by zd.shi,zd.xian,zd.xiaoqu,zd.jzname,STATIONTYPE,zd.id,dfzflx,dbid,ACCOUNTMONTH"
					+"  Order by accountMonth";
				
				System.out.println("--电费：      "+sql);
				rs = db.queryAll(sql);
				ls = new ArrayList<ElectricFeesFormBean>();
				while(rs.next()){
					ElectricFeesFormBean effb = new ElectricFeesFormBean();

					effb.setShi(rs.getString("shi") != null ? rs.getString("shi"): "");

					effb.setXian(rs.getString("xian") != null ? rs.getString("xian") : "");

					effb.setXiaoqu(rs.getString("xiaoqu") != null ? rs.getString("xiaoqu") : "");

					effb.setJzname(rs.getString("jzname") != null ? rs.getString("jzname") : "");

					effb.setJztype(rs.getString("STATIONTYPE") != null ? rs.getString("STATIONTYPE") : "");

					effb.setId(rs.getString("id") != null ? rs.getString("id"): "");

					effb.setDfzflx(rs.getString("dfzflx") != null ? rs.getString("dfzflx"): "");

					effb.setDbid(rs.getString("dbid") != null ? rs.getString("dbid"): "");
					
					effb.setAccountmonth(rs.getString("ACCOUNTMONTH") != null ? rs.getString("ACCOUNTMONTH"): "");
					
					effb.setPaydatetime(rs.getString("jfsj") != null ? rs.getString("jfsj"): "");
					
					ls.add(effb);
				}
				
				sql="select "
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) as shi,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) as xian,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) as xiaoqu,"
					+ "zd.jzname,"
					+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
					+"rtname(db.dfzflx) dfzflx,zd.id,db.dbid,MAX(yff.PAYDATETIME)jfsj,yff.ACCOUNTMONTH "
					+"from zhandian zd left join dianbiao db on zd.id = db.zdid "
					+"RIGHT join yufufeiview yff on db.dbid =yff.prepayment_ammeterid "
					+"where zd.qyzt = '1' and zd.shsign = '1' " +zcxstr2+whereStr
					+" group by zd.shi,zd.xian,zd.xiaoqu,zd.jzname,STATIONTYPE,zd.id,dfzflx,dbid,ACCOUNTMONTH"
					+"  Order by accountMonth";
				
				System.out.println("--预付费：      "+sql);
				rs = db.queryAll(sql);
				while(rs.next()){
					ElectricFeesFormBean effb = new ElectricFeesFormBean();

					effb.setShi(rs.getString("shi") != null ? rs.getString("shi"): "");

					effb.setXian(rs.getString("xian") != null ? rs.getString("xian") : "");

					effb.setXiaoqu(rs.getString("xiaoqu") != null ? rs.getString("xiaoqu") : "");

					effb.setJzname(rs.getString("jzname") != null ? rs.getString("jzname") : "");

					effb.setJztype(rs.getString("STATIONTYPE") != null ? rs.getString("STATIONTYPE") : "");

					effb.setId(rs.getString("id") != null ? rs.getString("id"): "");

					effb.setDfzflx(rs.getString("dfzflx") != null ? rs.getString("dfzflx"): "");

					effb.setDbid(rs.getString("dbid") != null ? rs.getString("dbid"): "");
					
					effb.setAccountmonth(rs.getString("ACCOUNTMONTH") != null ? rs.getString("ACCOUNTMONTH"): "");
					
					effb.setPaydatetime(rs.getString("jfsj") != null ? rs.getString("jfsj"): "");
					
					ls.add(effb);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		return ls;
	}

	
	//无管理电表的站点查询
	public synchronized List<ElectricFeesFormBean> getWuguanli(String whereStr){
		List<ElectricFeesFormBean> ls = null;
		ResultSet rs = null;
		DataBase db = new DataBase();
		String sql = "";
		try{
			sql="select "
				+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) as shi,"
				+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) as xian,"
				+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) as xiaoqu,"
				+ "zd.jzname,"
				+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				+ "zd.id "
				+"from zhandian zd join dianbiao db on zd.id=db.zdid  "
				+"where not exists " 
				+"(select z.id from zhandian z right join dianbiao db on z.id = db.zdid "
				+"where db.dbyt = 'dbyt03' and zd.id = z.id) "
				+whereStr;
			System.out.println("无管理电表：      "+sql);
			rs = db.queryAll(sql);
			ls = new ArrayList<ElectricFeesFormBean>();
			while(rs.next()){
				ElectricFeesFormBean effb = new ElectricFeesFormBean();
				effb.setShi(rs.getString("shi") != null ? rs.getString("shi")
						: "");
				effb.setXian(rs.getString("xian") != null ? rs
						.getString("xian") : "");
				effb.setXiaoqu(rs.getString("xiaoqu") != null ? rs
						.getString("xiaoqu") : "");
				effb.setJzname(rs.getString("jzname") != null ? rs
						.getString("jzname") : "");
				effb.setJztype(rs.getString("STATIONTYPE") != null ? rs
						.getString("STATIONTYPE") : "");
				effb.setId(rs.getString("id") != null ? rs.getString("id")
								: "");
				ls.add(effb);
			}
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		
		return ls;
	}
	
}
