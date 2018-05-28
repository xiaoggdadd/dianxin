package com.ptac.app.collectanalysis.siteeveryclose.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.collectanalysis.siteeveryclose.bean.ApplyCloseBean;
import com.ptac.app.collectanalysis.siteeveryclose.bean.PassCloseBean;
import com.ptac.app.collectanalysis.siteeveryclose.bean.PassCloseFeesBean;
import com.ptac.app.collectanalysis.siteeveryclose.bean.SiteEveryCloseBean;
import com.ptac.app.inportuserzibaodian.util.Format;

public class SiteEveryCoseDaoImpl implements SiteEveryCloseDao {

	@Override
	public List<SiteEveryCloseBean> getSiteEveryClose(String shi,String zdsx,String zdlx,String beginyue,String endyue,String loginId) {
		DataBase db = null;
		Connection conn =  null;
		String sql = "SELECT ZD.SHI CITYID,RNDIQU(ZD.SHI) CITYNAME,ZD.PROPERTY PROPERTYCODE, RTNAME(ZD.PROPERTY) SITEPROPERTY,ZD.STATIONTYPE STATIONCODE,RTNAME(ZD.STATIONTYPE) SITETYPE,'"+beginyue+"' STARTTIME,'"+endyue+"' ENDTIME,COUNT(DISTINCT(CASE WHEN ZD.ID IN (XXX.XXZZ) THEN ZD.ID END)) APPLYCLOSECOUNT,"
        +"COUNT(DISTINCT(CASE WHEN ZD.ID IN (XXX.XXZZZ) THEN ZD.ID END)) CLOSECOUNT,SUM(CASE WHEN ZD.ID IN (XXX.XXZZZ) "
    	+"AND EF.COUNTYAUDITSTATUS = '1' AND EF.CITYAUDIT = '1' AND EF.CITYZRAUDITSTATUS = '1' "
    	+"THEN EF.ACTUALPAY ELSE 0 END) POWERRATE,SUM(CASE  WHEN ZD.ID IN (XXX.XXZZZ) AND "
        +"EF.COUNTYAUDITSTATUS = '1' AND EF.CITYAUDIT = '1' AND EF.CITYZRAUDITSTATUS = '1' THEN  AM.BLHDL ELSE 0 END) ELECTRICITYQUANTITY,"
        +"SUM(CASE WHEN ZD.ID IN (XXX.XXZZZ) AND EF.COUNTYAUDITSTATUS = '1' AND EF.CITYAUDIT = '1' AND "
        +"EF.CITYZRAUDITSTATUS = '1' THEN EF.JSZQ ELSE 0 END) CYCLE FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF,"
        +"(SELECT XX.ZZ XXZZ, XX.ZZZ XXZZZ FROM (SELECT QZ.ZDID ZZ, DECODE(QZ.SHENGSHBZ, '1', QZ.ZDID) ZZZ FROM QSKZ QZ "
        +" WHERE TO_CHAR(QZ.QXTJTIME, 'YYYY-MM') >= '"+beginyue+"' AND TO_CHAR(QZ.QXTJTIME, 'YYYY-MM') <= '"+endyue+"' AND QZ.NEWQYZT = '0' AND QZ.NEWQYZT <> QZ.OLDQYZT) XX "
        +"GROUP BY XX.ZZ, XX.ZZZ) XXX WHERE ZD.ID = DB.ZDID(+) AND DB.DBID = AM.AMMETERID_FK(+) AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK(+) AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "+("0".equals(shi)?"":("AND ZD.SHI='"+shi+"'"))
        +("".equals(zdsx)?"":("AND ZD.PROPERTY IN ('"+zdsx+"')"))+("".equals(zdlx)?"":(" AND ZD.STATIONTYPE IN ('"+zdlx+"')"))+" AND (ZD.XIAOQU IN "
        +"(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) GROUP BY ZD.SHI, ZD.PROPERTY,ZD.STATIONTYPE ORDER BY ZD.SHI";
		System.out.println("sql:"+sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SiteEveryCloseBean> list = null;

		try {
			list = new ArrayList<SiteEveryCloseBean>();
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				SiteEveryCloseBean bean = new SiteEveryCloseBean();
				bean.setCityid(rs.getString("CITYID"));
				bean.setCityname(rs.getString("CITYNAME"));
				bean.setSitepropertycode(rs.getString("PROPERTYCODE"));
				bean.setSiteproperty(rs.getString("SITEPROPERTY"));
				bean.setSitetypecode(rs.getString("STATIONCODE"));
				bean.setSitetype(rs.getString("SITETYPE"));
				bean.setStarttime(rs.getString("STARTTIME"));
				bean.setEndtime(rs.getString("ENDTIME"));
				bean.setApplyclosecount(rs.getString("APPLYCLOSECOUNT"));
				bean.setClosecount(rs.getString("CLOSECOUNT"));
				bean.setPowerrate(Format.str2(rs.getString("POWERRATE")));
				bean.setElectricityquantity(Format.str2(rs.getString("ELECTRICITYQUANTITY")));
				bean.setCycle(rs.getString("CYCLE"));
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}

		return list;
	}

	@Override
	public List<ApplyCloseBean> getSiteApplyClose(String shi, String zdsx,
			String zdlx, String beginyue, String endyue, String loginId) {
		DataBase db = null;
		Connection conn =  null;
		String sql = "SELECT RNDIQU(ZD.SHI) SHI,RNDIQU(ZD.XIAN) XIAN,RNDIQU(ZD.XIAOQU) XIAOQU,RTNAME(ZD.PROPERTY) PROPERTY,RTNAME(ZD.STATIONTYPE) STATIONTYPE,"
			+"ZD.JZNAME ZDNAME,ZD.ID ZDID,RTNAME(ZD.GDFS) GDFS,ZD.ZLFH ZLFH,ZD.JLFH JLFH,ZD.EDHDL BDB,ZD.SCB SCB,ZD.JFSCKT JFSCKT,ZD.YYBGKT YYBGKT FROM ZHANDIAN ZD "
			+"WHERE ZD.ID IN (SELECT XX.ZZ ZDID FROM (SELECT QZ.ZDID ZZ FROM QSKZ QZ "
			+"WHERE TO_CHAR(QZ.QXTJTIME, 'YYYY-MM') >= '"+beginyue+"' "
			+"AND TO_CHAR(QZ.QXTJTIME, 'YYYY-MM') <= '"+endyue+"' "
			+"AND QZ.NEWQYZT = '0' AND QZ.NEWQYZT <> QZ.OLDQYZT) XX GROUP BY XX.ZZ) "
			+"AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "
			+("0".equals(shi)?"":("AND ZD.SHI='"+shi+"'"))
	        +("".equals(zdsx)?"":("AND ZD.PROPERTY IN ('"+zdsx+"')"))
	        +("".equals(zdlx)?"":(" AND ZD.STATIONTYPE IN ('"+zdlx+"')"))
            +"AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))";
		System.out.println("sql:"+sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ApplyCloseBean> list = null;

		try {
			list = new ArrayList<ApplyCloseBean>();
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				ApplyCloseBean bean = new ApplyCloseBean();
				bean.setCity(rs.getString("SHI"));
				bean.setXian(rs.getString("XIAN"));
				bean.setXiaoqu(rs.getString("XIAOQU"));
				bean.setProperty(rs.getString("PROPERTY"));
				bean.setStationtype(rs.getString("STATIONTYPE"));
				bean.setZdname(rs.getString("ZDNAME"));
				bean.setZdid(rs.getString("ZDID"));
				bean.setGdfs(rs.getString("GDFS"));
				bean.setZlfh(Format.str2(rs.getString("ZLFH")));
				bean.setJlfh(Format.str2(rs.getString("JLFH")));
				bean.setBdb(Format.str2(rs.getString("BDB")));
				bean.setScb(Format.str2(rs.getString("SCB")));
				bean.setSckt(rs.getString("JFSCKT"));
				bean.setYybgkt(rs.getString("YYBGKT"));
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}

		return list;
	}

	@Override
	public List<PassCloseBean> getSitePassClose(String shi, String zdsx,
			String zdlx, String beginyue, String endyue, String loginId) {
		DataBase db = null;
		Connection conn =  null;
		String sql = "SELECT RNDIQU(ZD.SHI) SHI,RNDIQU(ZD.XIAN) XIAN,RNDIQU(ZD.XIAOQU) XIAOQU,RTNAME(ZD.PROPERTY) PROPERTY,RTNAME(ZD.STATIONTYPE) STATIONTYPE,"
			+"ZD.JZNAME ZDNAME,ZD.ID ZDID,RTNAME(ZD.GDFS) GDFS,ZD.ZLFH ZLFH,ZD.JLFH JLFH,ZD.EDHDL BDB,ZD.SCB SCB,ZD.JFSCKT JFSCKT,ZD.YYBGKT YYBGKT FROM ZHANDIAN ZD "
			+"WHERE ZD.ID IN (SELECT XX.ZZ ZDID FROM (SELECT QZ.ZDID ZZ FROM QSKZ QZ "
			+"WHERE TO_CHAR(QZ.QXTJTIME, 'YYYY-MM') >= '"+beginyue+"' "
			+"AND TO_CHAR(QZ.QXTJTIME, 'YYYY-MM') <= '"+endyue+"' "
			+"AND QZ.NEWQYZT = '0' AND QZ.NEWQYZT <> QZ.OLDQYZT AND QZ.SHENGSHBZ='1') XX GROUP BY XX.ZZ) "
			+"AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "
			+("0".equals(shi)?"":("AND ZD.SHI='"+shi+"'"))
	        +("".equals(zdsx)?"":("AND ZD.PROPERTY IN ('"+zdsx+"')"))
	        +("".equals(zdlx)?"":(" AND ZD.STATIONTYPE IN ('"+zdlx+"')"))
            +"AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))";
		System.out.println("sql:"+sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PassCloseBean> list = null;

		try {
			list = new ArrayList<PassCloseBean>();
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				PassCloseBean bean = new PassCloseBean();
				bean.setCity(rs.getString("SHI"));
				bean.setXian(rs.getString("XIAN"));
				bean.setXiaoqu(rs.getString("XIAOQU"));
				bean.setProperty(rs.getString("PROPERTY"));
				bean.setStationtype(rs.getString("STATIONTYPE"));
				bean.setZdname(rs.getString("ZDNAME"));
				bean.setZdid(rs.getString("ZDID"));
				bean.setGdfs(rs.getString("GDFS"));
				bean.setZlfh(Format.str2(rs.getString("ZLFH")));
				bean.setJlfh(Format.str2(rs.getString("JLFH")));
				bean.setBdb(Format.str2(rs.getString("BDB")));
				bean.setScb(Format.str2(rs.getString("SCB")));
				bean.setSckt(rs.getString("JFSCKT"));
				bean.setYybgkt(rs.getString("YYBGKT"));
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}

		return list;
	}

	@Override
	public List<PassCloseFeesBean> getSitePassCloseFees(String shi,
			String zdsx, String zdlx, String beginyue, String endyue,
			String loginId) {
		DataBase db = null;
		Connection conn =  null;
		String sql = "SELECT RNDIQU(ZD.SHI) SHI,RNDIQU(ZD.XIAN) XIAN,RNDIQU(ZD.XIAOQU) XIAOQU,RTNAME(ZD.PROPERTY) PROPERTY,RTNAME(ZD.STATIONTYPE) STATIONTYPE,"
       +"ZD.JZNAME ZDNAME,ZD.ID ZDID,DB.DANJIA DANJIA,AM.LASTDEGREE LASTDEGREE,AM.THISDEGREE THISDEGREE,AM.LASTDATETIME LASTDATETIME,AM.THISDATETIME THISDATETIME,"
       +"AM.BLHDL BLHDL,EF.ACTUALPAY ACTUALPAY,EF.ACCOUNTMONTH ACCOUNTMONTH,EF.JSZQ JSZQ,AM.DBYDL DBYDL FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF "
       +"WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' AND EF.COUNTYAUDITSTATUS = '1' AND EF.CITYAUDIT = '1' AND EF.CITYZRAUDITSTATUS = '1' "
       +"AND ZD.ID IN (SELECT XX.ZZ ZDID FROM (SELECT QZ.ZDID ZZ FROM QSKZ QZ WHERE TO_CHAR(QZ.QXTJTIME, 'YYYY-MM') >= '"+beginyue+"' AND TO_CHAR(QZ.QXTJTIME, 'YYYY-MM') <= '"+endyue+"' "
       +"AND QZ.NEWQYZT = '0' AND QZ.NEWQYZT <> QZ.OLDQYZT AND QZ.SHENGSHBZ = '1') XX GROUP BY XX.ZZ) " 
       +("0".equals(shi)?"":("AND ZD.SHI='"+shi+"'"))
	   +("".equals(zdsx)?"":("AND ZD.PROPERTY IN ('"+zdsx+"')"))
	   +("".equals(zdlx)?"":(" AND ZD.STATIONTYPE IN ('"+zdlx+"')"))
       +"AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '263'))";

		System.out.println("sql:"+sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PassCloseFeesBean> list = null;

		try {
			list = new ArrayList<PassCloseFeesBean>();
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				PassCloseFeesBean bean = new PassCloseFeesBean();
				bean.setCity(rs.getString("SHI"));
				bean.setXian(rs.getString("XIAN"));
				bean.setXiaoqu(rs.getString("XIAOQU"));
				bean.setZdname(rs.getString("ZDNAME"));
				bean.setZdid(rs.getString("ZDID"));
				bean.setLastdegree(rs.getString("LASTDEGREE"));
				bean.setThisdegree(rs.getString("THISDEGREE"));
				bean.setLastdatetime(rs.getString("LASTDATETIME"));
				bean.setThisdatetime(rs.getString("THISDATETIME"));
				bean.setBlhdl(Format.str2(rs.getString("BLHDL")));
				bean.setBzdf(Format.str2(rs.getString("ACTUALPAY")));
				bean.setAccountmonth(rs.getString("ACCOUNTMONTH"));
				bean.setJfzq(rs.getString("JSZQ"));
				bean.setDbydl(Format.str2(rs.getString("DBYDL")));
				bean.setProperty(rs.getString("PROPERTY"));
				bean.setStationtype(rs.getString("STATIONTYPE"));
				bean.setDanjia(Format.str4(rs.getString("DANJIA")));
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}

		return list;
	}


}
