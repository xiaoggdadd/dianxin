package com.ptac.app.calibstat.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.calibstat.bean.OverSite;
import com.ptac.app.calibstat.bean.OverSiteDetial;
import com.ptac.app.electricmanageUtil.Format;

public class OverSiteDaoImpl implements OverSiteDao {

	@Override
	public List<OverSite> getOverSite(String shi,String month,String property,String zdlx,String cbmc,String cbbl,String cbjdz,String accountid) {

		String sql = "SELECT BB.ZDID ZDID, BB.CITY,BB.XIAN,BB.ZDNAME,BB.PROPERTY,BB.MONTH,BB.SCB,BB.RJHDL,BB.CBBL,BB.STATIONTYPE,BB.QSDBDL,BB.CBJDZ,ROWNUM "
			  +	" FROM (SELECT AA.ZDID,AA.CITY,AA.XIAN,AA.ZDNAME,AA.PROPERTY,AA.MONTH,AA.SCB,AA.RJHDL,AA.CBBL,AA.STATIONTYPE,AA.QSDBDL,AA.CBJDZ " 
			  +	" FROM (SELECT ZD.ID ZDID, RNDIQU(ZD.SHI) CITY, RNDIQU(ZD.XIAN) XIAN, ZD.JZNAME ZDNAME, RTNAME(ZD.PROPERTY) PROPERTY,"
              + " ZL.MONTH MONTH,ZD.SCB SCB, ZL.RJHDL RJHDL, ROUND((ZL.RJHDL - JZ.QSDBDL) / JZ.QSDBDL * 100, 2) CBBL,RTNAME(ZD.STATIONTYPE) STATIONTYPE,"
              +	" JZ.QSDBDL,ABS(ZL.RJHDL - JZ.QSDBDL) CBJDZ FROM ZHANDIAN ZD, T_ZDDBRJDL@UNEBD_81 ZL, JZXX JZ WHERE ZD.ID = ZL.ZDID AND ZL.ZDID = JZ.ZDID AND ZL.MONTH=JZ.SYMONTH "
              +	" AND ZD.SHI = "+shi+" AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+accountid+"'))"
              + (month==""?"":(" AND ZL.MONTH = '"+month+"'")) + (property==""?"":(" AND ZD.PROPERTY = '"+property+"'"))
              + (zdlx==""?"":(" AND ZD.STATIONTYPE IN('" + zdlx + "')"))
              +" ) AA ORDER BY AA.CBBL DESC)BB where 1=1 " + (cbmc==""?"":(" AND ROWNUM <="+cbmc))
			  + (cbbl==""?"":(" AND CBBL <"+cbbl))
			  + (cbjdz==""?"":(" AND CBJDZ <"+cbjdz));

		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		List<OverSite> list = new ArrayList<OverSite>();
		
		db = new DataBase();
		System.out.println("超标站点查询："+sql);
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				OverSite os = new OverSite();
				os.setZdid(rs.getString("ZDID"));
				os.setCity(rs.getString("CITY"));
				os.setXian(rs.getString("XIAN"));
				os.setZdname(rs.getString("ZDNAME"));
				os.setProperty(rs.getString("PROPERTY"));
				os.setMonth(rs.getString("MONTH"));
				os.setRjhdl(rs.getString("RJHDL"));
				os.setCbbl(Format.str2(rs.getString("CBBL")));
				os.setZdlx(rs.getString("STATIONTYPE"));
				os.setQsdbdl(rs.getString("QSDBDL"));
				os.setCbjdz(rs.getString("CBJDZ"));
				list.add(os);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return list;
	}

	@Override
	public OverSiteDetial getOverSiteDetial(String zdid) {

		String sql = "SELECT ZD.JZNAME ZDNAME,ZD.ID ZDID,RNDIQU(ZD.SHI) CITY,RNDIQU(ZD.XIAN) XIAN,RNDIQU(ZD.XIAOQU) XIAOQU," +
				"RTNAME(ZD.PROPERTY) PROPERTY,ZD.ZLFH ZLFH,ZD.JLFH JLFH,ZD.EDHDL EDHDL,ZD.SCB SCB,RTNAME(ZD.STATIONTYPE) ZDLX,RTNAME(ZD.YFLX) FWLX " +
				"FROM ZHANDIAN ZD WHERE ZD.ID = '"+zdid+"'";
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		OverSiteDetial os = new OverSiteDetial();
		db = new DataBase();
		System.out.println("超标站点详情查询之站点基本信息："+sql);
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				os.setZdname(rs.getString("ZDNAME"));
				os.setZdid(rs.getString("ZDID"));
				os.setCity(rs.getString("CITY"));
				os.setXian(rs.getString("XIAN"));
				os.setXiaoqu(rs.getString("XIAOQU"));
				os.setProperty(rs.getString("PROPERTY"));
				os.setZlfh(rs.getString("ZLFH"));
				os.setJlfh(rs.getString("JLFH"));
				os.setEdhdl(rs.getString("EDHDL"));
				os.setScb(rs.getString("SCB"));
				os.setZdlx(rs.getString("ZDLX"));
				os.setFwlx(rs.getString("FWLX"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return os;
	}
	@Override
	public OverSiteDetial getOverSiteSDB(String zdid) {
		String sql = "SELECT ZB.RJDL1 SDB1,ZB.RJDL2 SDB2,ZB.RJDL3 SDB3,ZB.RJDL4 SDB4,ZB.RJDL5 SDB5," 
				+ "ZB.RJDL6 SDB6,ZB.RJDL7 SDB7,ZB.RJDL8 SDB8,ZB.RJDL9 SDB9,ZB.RJDL10 SDB10,ZB.RJDL11 SDB11," 
				+ "ZB.RJDL12 SDB12 FROM T_ZDDBSDB@UNEBD_81 ZB WHERE ZB.ZDID = '"+zdid+"'";
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		OverSiteDetial os = new OverSiteDetial();
		db = new DataBase();
		System.out.println("超标站点详情查询之各月省定标："+sql);
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				os.setSdb1(rs.getString("SDB1"));
				os.setSdb2(rs.getString("SDB2"));
				os.setSdb3(rs.getString("SDB3"));
				os.setSdb4(rs.getString("SDB4"));
				os.setSdb5(rs.getString("SDB5"));
				os.setSdb6(rs.getString("SDB6"));
				os.setSdb7(rs.getString("SDB7"));
				os.setSdb8(rs.getString("SDB8"));
				os.setSdb9(rs.getString("SDB9"));
				os.setSdb10(rs.getString("SDB10"));
				os.setSdb11(rs.getString("SDB11"));
				os.setSdb12(rs.getString("SDB12"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return os;
	}
	@Override
	public List<OverSiteDetial> getOverSiteDL(String zdid) {
		String sql = "SELECT to_char(AA.LASTTIME,'yyyy-mm-dd') LASTTIME,to_char(AA.THISTIME,'yyyy-mm-dd') THISTIME,AA.LASTDEGREE,AA.THISDEGREE,AA.BZDL,AA.BEILV,to_char(AA.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH FROM" 
			+ " (SELECT AM.LASTDATETIME LASTTIME, AM.THISDATETIME THISTIME, AM.LASTDEGREE LASTDEGREE, AM.THISDEGREE THISDEGREE, ROUND(AM.BLHDL,2) BZDL, DB.BEILV BEILV, EF.ACCOUNTMONTH ACCOUNTMONTH"
			+ " FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK"
			+ " AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK AND ZD.ID = '"+zdid+"' AND EF.CITYAUDIT = '1'"
			+ " ORDER BY AM.THISDATETIME DESC)AA WHERE ROWNUM <6";
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		List<OverSiteDetial> list = new ArrayList<OverSiteDetial>();
		db = new DataBase();
		System.out.println("超标站点详情查询之电量信息："+sql);
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				OverSiteDetial os = new OverSiteDetial();
				os.setLasttime(rs.getString("LASTTIME"));
				os.setThistime(rs.getString("THISTIME"));
				os.setLastdegree(rs.getString("LASTDEGREE"));
				os.setThisdegree(rs.getString("THISDEGREE"));
				os.setBzdl(rs.getString("BZDL"));
				os.setBeilv(rs.getString("BEILV"));
				os.setAccountmonth(rs.getString("ACCOUNTMONTH"));
				list.add(os);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return list;
	}

}
