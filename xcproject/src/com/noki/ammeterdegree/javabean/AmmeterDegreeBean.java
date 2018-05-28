package com.noki.ammeterdegree.javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.sysconfig.javabean.AutoAuditBean;
import com.noki.util.CTime;
import com.noki.util.MD5;
import com.noki.util.Query;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;

public class AmmeterDegreeBean {
	// 电量管理 电量列表
	public synchronized ArrayList getPageData3(String whereStr, String loginId) {

		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			sql = "select distinct AMMETERDEGREEID,DB.DLLX electriccurrenttype_ammeter,zd.jzname,(select name from indexs where code=DB.YDSB and type = 'YDSB') usageofelectypeid_ammeter,"
					+ "AMMETERID_FK,LASTDEGREE,THISDEGREE,TO_CHAR(LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(THISDATETIME,'yyyy-mm-dd') THISDATETIME,FLOATDEGREE,"
					+ "(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"
					+ "ACTUALDEGREE,ad.autoauditstatus,ad.autoauditdescription,ad.manualauditstatus,DB.DBYT,ad.feesbz,ad.blhdl from dianduview ad,DIANBIAO DB,zhandian zd "
					+ "where ad.ammeterid_fk=DB.DBID and ad.feesbz='0' and zd.qyzt='1'and DB.ZDID=zd.id and AD.MANUALAUDITSTATUS <> '1' and DB.DBYT='dbyt03' and ad.amuuid is null "
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ") order by AMMETERDEGREEID desc";
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("电量管理：" + sql.toString());
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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

	// 电量列表里的统计总条数
	public String getCountt1(String whereStr, String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String count = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select count(*) from dianduview ad,DIANBIAO DB,zhandian zd "
					+ "where ad.ammeterid_fk=DB.DBID and ad.feesbz='0' and zd.qyzt='1' and DB.ZDID=zd.id and DB.DBYT='dbyt03' and ad.amuuid is null "
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr
					+ ") order by AMMETERDEGREEID desc";

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("电量管理汇总：" + sql.toString());

		ResultSet rs = null;
		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				count = rs.getString(1);
			}
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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

	// 电量列表里统计总电量数
	public String getCountt2(String whereStr, String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String count = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select sum(ad.blhdl) from dianduview ad,DIANBIAO DB,zhandian zd "
					+ "where ad.ammeterid_fk=DB.DBID and ad.feesbz='0' and zd.qyzt='1' and DB.ZDID=zd.id and DB.DBYT='dbyt03' and ad.amuuid is null "
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ") order by AMMETERDEGREEID desc";

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("电量管理汇总：" + sql.toString());

		ResultSet rs = null;
		try {
			db.connectDb();

			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				count = rs.getString(1);
			}
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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

	// 下载模板时--带出的站点和其他的信息
	/*
	 * update 2014.06.10 zhouxue 内容：把默认条件‘电费支付类型为预支和月结 ’去掉 所以电费支付类型都能下载出数据
	 * 都可以走电费单流程
	 */
	public synchronized ArrayList getPageDataModel(int start, String whereStr,
			String loginId) {

		System.out
				.println("AmmeterDegreeBean-getPageDataModel下载模板时--带出的站点和其他的信息:"
						+ whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// 调用负责站点条件函数
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select distinct db.dbid, db.danjia,db.CSDS,TO_CHAR(db.CSSYTIME,'yyyy-mm-dd') CSSYTIME,"
					+ " (select NAME from indexs where CODE = zd.STATIONTYPE and type = 'stationtype') as stationtype,"
					+ "(select agname from d_area_grade where agcode = zd.xian) as xian,"
					+ "(select agname from d_area_grade where agcode = zd.xiaoqu) as agname,zd.jzname,"
					+ "decode(am.cityzrauditstatus,'1',am.thisdegree,'')thisdegree,"
					+ "decode(am.cityzrauditstatus,'1',TO_CHAR(am.thisdatetime,'yyyy-mm-dd'),'')thisdatetime,"
					+ "(select name from indexs where type='xslx' and code = db.linelosstype) as linelosstype,"
					+ " db.linelossvalue,db.beilv,DB.DBDS,DB.XGBZW "
					+ " from (select distinct dd.ammeterid_fk,dd.thisdegree,dd.thisdatetime,df.cityzrauditstatus from dianduview dd,dianfeiview df,"
					+ "(select  max(amm.thisdatetime) thisdatetime,amm.ammeterid_fk from dianduview amm,dianfeiview ef  WHERE  ef.CITYAUDIT = '1' and ef.cityzrauditstatus = '1' and amm.ammeterdegreeid = ef.ammeterdegreeid_fk group by amm.ammeterid_fk )b"
					+ " where dd.ammeterid_fk = b.ammeterid_fk and dd.thisdatetime=b.thisdatetime and dd.ammeterdegreeid = df.ammeterdegreeid_fk and df.cityzrauditstatus = '1') am,dianbiao db,zhandian zd"
					+ " where db.dbid = am.ammeterid_fk(+)  "
					+ whereStr
					+ "and zd.id = db.zdid(+) and db.dbyt='dbyt01' and zd.qyzt='1' and zd.shsign='1' and zd.PROVAUDITSTATUS='1' and db.DBQYZT='1' AND ZD.XUNI = '0' AND ZD.CAIJI = '0' "
					+ " and((zd.xiaoqu in (select t.agcode from per_area t where  t.accountid = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ") order by xian,agname,jzname";
			System.out.println("电费单下载模板时--带出的站点和其他的信息:" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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

	// 加强版下载模板时--带出的站点和其他的信息
	public synchronized ArrayList getPageDataModelEnhance(int start,
			String whereStr, String loginId) {

		System.out
				.println("AmmeterDegreeBean-getPageDataModelEnhance下载模板时--带出的站点和其他的信息:"
						+ whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// 调用负责站点条件函数
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "SELECT DISTINCT DB.DBID,(CASE WHEN DB.CHANGEVALUE IS NULL THEN 0 ELSE DB.CHANGEVALUE END) CHANGEVALUE,LTRIM(TO_CHAR(FF.HJ,'9990.0000')) DYJDANJIA,LTRIM(TO_CHAR(FF.JFZB*100,'9990.00')) JFZB,LTRIM(TO_CHAR(FF.JFZ,'9990.0000')) JFZ,LTRIM(TO_CHAR(FF.BFZB*100,'9990.00')) BFZB,LTRIM(TO_CHAR(FF.BFZ,'9990.0000')) BFZ,LTRIM(TO_CHAR(FF.BPZB*100,'9990.00')) BPZB,LTRIM(TO_CHAR(FF.BPZ,'9990.0000')) BPZ,LTRIM(TO_CHAR(FF.BGZB*100,'9990.00')) BGZB,LTRIM(TO_CHAR(FF.BGZ,'9990.0000')) BGZ,DB.DANJIA ZGDDANJIA,DB.CSDS,TO_CHAR(DB.CSSYTIME,'yyyy-mm-dd') CSSYTIME,"
					+ " (SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE = 'stationtype') AS STATIONTYPE,"
					+ "(SELECT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) AS XIAN,"
					+ "(SELECT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) AS AGNAME,ZD.JZNAME,"
					+ "(SELECT NAME FROM INDEXS WHERE TYPE = 'GDFS' AND CODE = ZD.GDFS) AS GDFS,"
					+ "DECODE(AM.CITYZRAUDITSTATUS,'1',AM.THISDEGREE,'')THISDEGREE,"
					+ "DECODE(AM.CITYZRAUDITSTATUS,'1',TO_CHAR(AM.THISDATETIME,'yyyy-mm-dd'),'')THISDATETIME,"
					+ "(SELECT NAME FROM INDEXS WHERE TYPE='xslx' AND CODE = DB.LINELOSSTYPE) AS LINELOSSTYPE,"
					+ " (CASE WHEN DB.LINELOSSVALUE IS NULL THEN 0 ELSE DB.LINELOSSVALUE END) LINELOSSVALUE,DB.BEILV"
					+ " FROM (SELECT DISTINCT DD.AMMETERID_FK,DD.THISDEGREE,DD.THISDATETIME,DF.CITYZRAUDITSTATUS FROM DIANDUVIEW DD,DIANFEIVIEW DF,"
					+ "(SELECT  MAX(AMM.THISDATETIME) THISDATETIME,AMM.AMMETERID_FK FROM DIANDUVIEW AMM,DIANFEIVIEW EF  WHERE  EF.CITYAUDIT = '1' AND EF.CITYZRAUDITSTATUS = '1' AND AMM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK GROUP BY AMM.AMMETERID_FK )B"
					+ " WHERE DD.AMMETERID_FK = B.AMMETERID_FK AND DD.THISDATETIME=B.THISDATETIME AND DD.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK AND DF.CITYZRAUDITSTATUS = '1') AM,DIANBIAO DB,ZHANDIAN ZD"
					+ ",(SELECT DAG.AGCODE AGCODE,FGP.JFZB,FGP.JFZ,FGP.BFZB,FGP.BFZ,FGP.BPZB,FGP.BPZ,FGP.BGZB,FGP.BGZ,FGP.JFZB*FGP.JFZ+FGP.BFZB*FGP.BFZ+FGP.BPZB*FGP.BPZ+FGP.BGZB*FGP.BGZ HJ FROM D_AREA_GRADE DAG , FGP WHERE DAG.AGCODE = FGP.CITY) FF"
					+ " WHERE DB.DBID = AM.AMMETERID_FK(+)  "
					+ whereStr
					+ "AND ZD.ID = DB.ZDID(+) AND ZD.SHI = FF.AGCODE(+) AND DB.DBYT='dbyt01' AND ZD.QYZT='1' AND ZD.SHSIGN='1' AND ZD.PROVAUDITSTATUS='1' AND DB.DBQYZT='1' AND ZD.XUNI = '0' AND ZD.CAIJI = '0' "
					+ " AND((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE  T.ACCOUNTID = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ") ORDER BY XIAN,AGNAME,JZNAME";
			System.out.println("加强版电费单下载模板时--带出的站点和其他的信息:" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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

	/**
	 * 预付费信息下载模板时--带出的站点和其他的信息
	 * 
	 * @param start
	 * @param whereStr
	 * @param loginId
	 * @return list:站点和站点的电费信息
	 */

	public synchronized ArrayList getPageDataModelpp(int start,
			String whereStr, String loginId) {
		System.out
				.println("AmmeterDegreeBean-getPageDataModel预付费信息下载模板时--带出的站点和其他的信息:"
						+ whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// 调用负责站点条件函数
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "SELECT DISTINCT DB.DBID,DB.CSDS,TO_CHAR(DB.CSSYTIME,'yyyy-mm-dd') CSSYTIME,DB.DANJIA,ST.NAME AS STATIONTYPE,X.AGNAME AS XIAN,XQ.AGNAME AS XIAOQU,ZD.ID,ZD.JZNAME,"
					+ "DECODE(AMM.CITYAUDIT, '1', AMM.LASTDEGREE, '') THISDEGREE,DECODE(AMM.CITYAUDIT, '1', TO_CHAR(AMM.LASTDATETIME,'yyyy-mm-dd'), '') THISDATETIME,"
					+ "TO_CHAR(AMM.ENDMONTH,'yyyy-mm') ENDMONTH FROM ZHANDIAN ZD LEFT JOIN D_AREA_GRADE X ON ZD.XIAN = X.AGCODE "
					+ "LEFT JOIN D_AREA_GRADE XQ ON ZD.XIAOQU = XQ.AGCODE LEFT JOIN INDEXS ST ON ZD.STATIONTYPE = ST.CODE AND ST.TYPE = 'stationtype' "
					+ "LEFT JOIN DIANBIAO DB ON ZD.ID = DB.ZDID "
					+ "LEFT JOIN (SELECT DISTINCT DD.PREPAYMENT_AMMETERID,B.LASTDATETIME,DD.STOPDEGREE AS LASTDEGREE,DD.ENDMONTH,DD.CITYAUDIT "
					+ "FROM YUFUFEIVIEW DD,(SELECT MAX(AMM.ENDDATE) LASTDATETIME,AMM.PREPAYMENT_AMMETERID "
					+ "FROM YUFUFEIVIEW AMM WHERE AMM.CITYAUDIT = '1' GROUP BY AMM.PREPAYMENT_AMMETERID) B "
					+ "WHERE DD.PREPAYMENT_AMMETERID = B.PREPAYMENT_AMMETERID AND DD.ENDDATE = B.LASTDATETIME AND DD.CITYAUDIT = '1') AMM "
					+ "ON DB.DBID = AMM.PREPAYMENT_AMMETERID WHERE DB.DBYT = 'dbyt01' AND DB.DBQYZT = '1' "
					+ "AND ZD.QYZT = '1' AND ZD.SHSIGN = '1' AND ZD.PROVAUDITSTATUS='1'  AND ZD.XUNI = '0' AND ZD.CAIJI = '0' "
					+ whereStr
					+ " AND (DB.DFZFLX = 'dfzflx03' OR DB.DFZFLX = 'dfzflx04') "
					+ "AND((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE  T.ACCOUNTID = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ") ORDER BY XIAN,XIAOQU,JZNAME";
			System.out.println("预付费信息电费单下载模板时--带出的站点和其他的信息:" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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

	// 下载v3表格时 带出的数据
	public synchronized ArrayList getzhandianmodifg(int start, String whereStr,
			String loginId, String biaoji) {

		System.out
				.println("AmmeterDegreeBean-getPageDataModel下载模板时--带出的站点和其他的信息:"
						+ whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// 调用负责站点条件函数
		String station = "";
		if ("2".equals(biaoji)) {
			station = "and z.stationtype in('StationType02','StationType03','StationType12')";
		} else {
			station = "";
		}
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select (select d.agname from d_area_grade d where d.agcode = z.sheng ) sheng,(select d.agname from d_area_grade d where d.agcode = z.shi) shi,(select d.agname from d_area_grade d where d.agcode = z.xian) xian,"
					+ "(select d.agname from d_area_grade d where d.agcode = z.xiaoqu) xiaoqu,z.jzname,z.id,z.yid,z.bieming,z.area,z.address,(select i.name from indexs i where i.code=z. property and i.type='ZDSX')property,"
					+ "(select i.name from indexs i where i.code=z. jztype and i.type='ZDLX')jztype,(select i.name from indexs i where i.code=z. stationtype and i.type='stationtype')stationtype,"
					+ "(select i.name from indexs i where i.code=z. gxxx and i.type='gxxx')gxxx,(select i.name from indexs i where i.code=z. zdcq and i.type='ZDCQ') zdcq,(select i.name from indexs i where i.code=z. yflx and i.type='FWLX') yflx,(select i.name from indexs i where i.code=z. gsf  and i.type='gsf') gsf,"
					+ "z.fzr,z.sydate,case when z.qyzt='1' then '是' else '否' end qyzt,z.lyjhjgs,(select i.name from indexs i where i.code=z. dytype and i.type='dytype') dytype,case when g2='1' then '有' else '无' end g2,case when g3='1' then '有' else '无' end g3,case when kdsb='1' then '有' else '无' end kdsb,"
					+ " case when yysb='1' then '有' else '无' end yysb,z.zpsl, z.zssl, z.kdsbsl,z.yysbsl,case when kt1='1' then '有' else '无' end kt1,case when kt2='1' then '有' else '无' end kt2, z.zlfh,z.jlfh,z.memo,z.dianfei,"
					+ "case when z.BGSIGN='1' then '是' else '否' end BGSIGN, case when jnglmk='1' then '是' else '否' end jnglmk, case when zgd='1' then '是' else '否' end zgd, "
					+ "(select i.name from indexs i where i.code=z. gdfs and i.type='GDFS')gdfs,z.edhdl,(select i.name from indexs i where i.code=info.fkzq and i.type='FKZQ')fkzq,(select i.name from indexs i where i.code=d.dfzflx and i.type='dfzflx')dfzflx,"
					+ " d.beilv,d.dbid,(select i.name from indexs i where i.code=d.dbyt and i.type='DBYT')dbyt,(select i.name from indexs i where i.code=info.fkfs and i.type='FKFS')fkfs,"
					+ " d.dbzbdyhh,case when info.watchcost='2' then '是' else '否' end watchcost,(select i.name from indexs i where i.code=d.linelosstype and i.type='xslx')linelosstype,d.linelossvalue,d.csds, to_char(d.cssytime,'yyyy-mm-dd') cssytime,"
					+ "  sum(decode(s.dd, 'zylx01', bili, 0)) sc, sum(decode(s.dd, 'zylx02', bili, 0)) yy,sum(decode(s.dd, 'zylx03', bili, 0)) bg,sum(decode(s.dd, 'zylx04', bili, 0)) xxh,sum(decode(s.dd, 'zylx05', bili, 0)) jstz,SUM(DECODE(S.DD, 'zylx06', BILI, 0)) DDDF "
					+ " from zhandian z, dianbiao d,zddfinfo info,(SELECT sss.dianbiaoid, SUM(sss.dbili) AS bili,sss.dd from (SELECT DISTINCT s.dianbiaoid AS dianbiaoid, S.DBILI,s.shuoshuzhuanye dd FROM sbgl s) sss GROUP BY sss.dianbiaoid,sss.dd) s where z.id = d.zdid(+) and z.id=info.zdid(+) and d.dbid=s.dianbiaoid(+) "
					+ "   AND Z.QYZT='1' AND D.DBQYZT='1' and d.dbyt='dbyt01'"
					+ station
					+ " and((z.xiaoqu in (select t.agcode from per_area t where  t.accountid = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ")"
					+

					"group by z.sheng,z.shi,z.xian,z.xiaoqu,z.jzname,z.id,z.yid,z.bieming,z.area,z.address, z.property,z.jztype,z.stationtype,z.gxxx,z.zdcq,z.yflx,z.gsf,z.fzr,z.sydate,z.qyzt,z.lyjhjgs,z.dytype,z.g2,z.g3,z.kdsb,z.yysb,z.zpsl,z.zssl,z.kdsbsl,z.yysbsl,z.kt1,z.kt2,z.zlfh,z.jlfh,z.zgd,z.memo,z.dianfei,"
					+ "z.BGSIGN,z.jnglmk,z.gdfs,z.edhdl,info.fkzq,d.dfzflx,d.beilv,d.dbyt,d.dbid,info.fkfs,d.dbzbdyhh,info.watchcost,d.linelosstype,d.linelossvalue,d.csds,d.cssytime order by z.xian,z.xiaoqu,z.jzname";
			System.out.println("v3信息下载模板时--带出的站点和其他的信息:" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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

	// 电量管理 电量打印(暂不用)（ammeters）
	public synchronized ArrayList getPageData(String whereStr, String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select distinct AMMETERDEGREEID,am.DLLX electriccurrenttype_ammeter,zd.jzname,(select name from indexs where code=am.YDSB and type = 'YDSB') usageofelectypeid_ammeter,"
					+ "AMMETERID_FK,LASTDEGREE,THISDEGREE,TO_CHAR(LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(THISDATETIME,'yyyy-mm-dd') THISDATETIME,FLOATDEGREE,"
					+ "(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"
					+ "ACTUALDEGREE,ad.autoauditstatus,ad.autoauditdescription,ad.manualauditstatus,am.dbyt,ad.feesbz,ad.blhdl from dianduview ad,dianbiao am,zhandian zd "
					+ "where ad.ammeterid_fk=am.DBID and ad.feesbz='0' and zd.qyzt='1' and am.ZDID=zd.id and AD.MANUALAUDITSTATUS <> '1' and am.dbyt='dbyt03' and ad.amuuid is null "
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ") order by AMMETERDEGREEID desc";

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("电量管理导出：" + sql.toString());

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

		return list;
	}

	// 下载模板时--带出的站点和其他的信息

	// 合同单--模版下载
	public synchronized ArrayList getPageDataModelhtd(int start,
			String whereStr, String loginId) {

		System.out.println("AmmeterDegreeBean-getPageDataModel:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// 调用负责站点条件函数
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			// sql =
			// "select zd.id, zd.zdcode,zd.jzname,p.prepayment_ammeterid,d.dbname,p.money,p.startmonth,p.endmonth,p.id,"
			// +" (select agname from d_area_grade where agcode = zd.xian) as xian, "+
			// " (select agname from d_area_grade where agcode = zd.xiaoqu) as agname, "+
			// " (select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype "
			// + " from yufufeiview p,dianbiao d,zhandian zd"
			// +
			// " where d.dbid=p.prepayment_ammeterid and zd.id=p.stationid and zd.qyzt='1' and p.cityaudit='0' and d.dfzflx='dfzflx02'"
			sql = "select zd.id, zd.zdcode,zd.jzname,d.dbid,(select agname from d_area_grade where agcode = zd.xian) as xian,(select agname from d_area_grade where agcode = zd.xiaoqu) as agname, "
					+ " (select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype "
					+ " from  dianbiao d, zhandian zd  where  zd.id = d.zdid and zd.qyzt = '1' and zd.SHSIGN='1' and zd.PROVAUDITSTATUS='1'  and d.dfzflx = 'dfzflx02' "
					+ whereStr
					+ " and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ")";

			String sql1 = "select distinct zd.id,zd.zdcode, zd.jzname,db.dbid,"
					+ "(select agname from d_area_grade where agcode = zd.xian) as xian,"
					+ "(select agname from d_area_grade where agcode = zd.xiaoqu) as agname,"
					+ "(select NAME from indexs where CODE = zd.STATIONTYPE and type = 'stationtype') as stationtype,"
					+ " decode(amm.financeaudit, '2', to_char(amm.endmonth,'yyyy-mm'),'') as endmonth, to_char(db.cssytime,'yyyy-mm-dd') cssytime from "
					+ "(select distinct dd.prepayment_ammeterid, dd.endmonth, dd.financeaudit from yufufeiview dd,"
					+ "(select max(amm.endmonth) endmonth, amm.prepayment_ammeterid from "
					+ "yufufeiview amm  WHERE AMM.financeaudit = '2' group by amm.prepayment_ammeterid) b "
					+ "where dd.prepayment_ammeterid = b.prepayment_ammeterid and to_char(dd.endmonth,'yyyy-mm') = to_char(b.endmonth,'yyyy-mm')"
					+ " and dd.financeaudit = '2') amm,dianbiao db,zhandian zd "
					+ " where db.dbid = amm.prepayment_ammeterid(+) and zd.id = db.zdid(+)"
					+ " and zd.qyzt = '1' and zd.xuni = '0' and zd.caiji = '0' and zd.shsign = '1' and zd.PROVAUDITSTATUS='1'  and db.dfzflx = 'dfzflx02' and db.dbyt='dbyt01' AND DB.DBQYZT='1' "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))) order by xian, agname, jzname";
			// sql =
			// "select (select agname from d_area_grade where agcode = zd.xian) as xian, db.beilv,(select name from indexs where code = db.linelosstype) as linelosstype,db.linelossvalue,dag.agname,zd.jzname,zd.dianfei,am.ammeterid,a.LASTDEGREE, max(a.LASTDATETIME)as lastdatetime from  ammeterdegrees a,ammeters am,dianbiao db, zhandian zd,d_area_grade dag where am.stationid = zd.id  and am.AMMETERUSE='dbyt01' and zd.xiaoqu = dag.agcode "+whereStr+" "
			// +
			// " and db.zdid = zd.id and a.ammeterid_fk(+)=am.ammeterid and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")  group by xian,dag.agname,zd.jzname,am.ammeterid,a.lastdegree,zd.dianfei,db.beilv,db.linelosstype,db.linelossvalue";
			System.out.println("合同单导入下载模版时的sql语句:" + sql1);
			db.connectDb();
			rs = db.queryAll(sql1.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	// 批量修改单价
	public synchronized ArrayList getPageDataModeldj(int start,
			String whereStr, String loginId) {

		System.out.println("AmmeterDegreeBean-getPageDataModel:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		// 调用负责站点条件函数
		try {
			sql = "select zdcode,jzname, (case  when z.shi is not null then (select agname from d_area_grade where agcode = z.shi) "
					+ " else  '' end) || (case when z.xian is not null then (select distinct agname from d_area_grade where agcode = z.xian) "
					+ " else  '' end) || (case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = z.xiaoqu) "
					+ " else  '' end) as szdq,dianfei,"
					+ " (select NAME from indexs  where CODE = z.STATIONTYPE and type = 'stationtype') as stationtype "
					+ " from zhandian z  where   z.qyzt = '1' and z.shsign = '1' "
					+ whereStr
					+ " and (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))";
			System.out.println("站点单价批量修改下载模版时的sql语句:" + sql);
			db.connectDb();
			conn = db.getConnection();
			try {
				ps = conn.prepareStatement(sql.toString());
				rs = ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.free(rs, ps, conn);

		}

		return list;
	}

	// 电量自动审核列表信息
	public synchronized ArrayList getPageDataCheck(int start, String whereStr,
			String loginId) {

		System.out.println("AmmeterDegreeBean-getPageData:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select distinct AMMETERDEGREEID,zd.jzname,db.dllx electriccurrenttype_ammeter,(select name from indexs where code=db.ydsb and type = 'YDSB') usageofelectypeid_ammeter,"
					+ "AMMETERID_FK,LASTDEGREE,THISDEGREE,to_char(LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,to_char(THISDATETIME,'yyyy-mm-dd') THISDATETIME,FLOATDEGREE,"
					+ "(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"
					+ "(select name from indexs where code=zd.property and type = 'ZDSX') as property,"
					+ "(select name from indexs where code=zd.jztype and type = 'ZDLX') as jztype,"
					+ "ACTUALDEGREE,ad.autoauditstatus,ad.autoauditdescription,ad.manualauditstatus,case when ad.ammeterdegreeid  in (select t.ammeterdegreeid_fk from dianfeiview t) or ad.manualauditstatus='0' or ad.manualauditstatus is null then '1' else '0' end feesbz,ad.entrytime,ad.blhdl from dianduview ad,dianbiao db,zhandian zd "
					+ "where ad.ammeterid_fk=db.dbid and db.dbyt<>'dbyt02' and db.zdid=zd.id and zd.qyzt='1' and ad.amuuid is null and (ad.manualauditstatus = '0' or ad.manualauditstatus is null) "
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ")  ";
			System.out.println("自动电量审核:" + sql.toString());

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

			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
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

		return list;
	}

	/**
	 * 电量导入下载模版时的sql语句
	 * 
	 * @param start
	 * @param whereStr
	 * @param loginId
	 * @return
	 */

	public synchronized ArrayList getPageDataModel1(int start, String whereStr,
			String loginId) {

		System.out.println("AmmeterDegreeBean-getPageDataModel:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// 调用负责站点条件函数
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			sql = "select db.dbid, zd.dianfei,"
					+ "(select agname from d_area_grade where agcode = zd.xian) as xian,"
					+ "(select agname from d_area_grade where agcode = zd.xiaoqu) as agname,zd.jzname,"
					+ "decode(amm.manualauditstatus,'1',amm.thisdegree,'')thisdegree,"
					+ "decode(amm.manualauditstatus,'1',to_char(amm.thisdatetime,'yyyy-mm-dd'),'')thisdatetime,"
					+ "(select name from indexs where type='xslx' and code = db.linelosstype) as linelosstype,"
					+ " db.linelossvalue,db.beilv,"
					+ " (select NAME from indexs where CODE = zd.STATIONTYPE and type = 'stationtype') as stationtype "
					+ " from (select distinct dd.ammeterid_fk,dd.thisdegree,dd.thisdatetime,dd.manualauditstatus from dianduview dd,"
					+ "(select  max(amm.thisdatetime) thisdatetime,amm.ammeterid_fk from dianduview amm group by amm.ammeterid_fk )b"
					+ " where dd.ammeterid_fk = b.ammeterid_fk and dd.thisdatetime=b.thisdatetime) amm,dianbiao db,zhandian zd"
					+ " where db.dbid = amm.ammeterid_fk(+)  "
					+ whereStr
					+ "and zd.id = db.zdid(+) and db.dbyt='dbyt03' and zd.qyzt='1' and zd.shsign='1' and db.DBQYZT='1' "
					+ " and((zd.xiaoqu in (select t.agcode from per_area t where  t.accountid = '"
					+ loginId + "'))" + fzzdstr + ")";

			System.out.println("电量导入下载模版时的sql语句:" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	// 自动电量审核条数
	public String getCountt(String whereStr, String loginId, String str) {
		String sql = "";
		String count = "";
		DataBase db = new DataBase();
		String fzzdstr = "";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
				fzzdstr = getFuzeZdid(db, loginId);
				StringBuffer strall = new StringBuffer();
				strall
						.append(" select count(*) "
								+ "from dianduview ad,dianbiao db,zhandian zd "
								+ "where ad.ammeterid_fk=db.dbid and db.dbyt<>'dbyt02' and db.zdid=zd.id and zd.qyzt='1' and ad.amuuid is null and (ad.manualauditstatus = '0' or ad.manualauditstatus is null) "
								+ whereStr
								+ str
								+ " "
								+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
								+ loginId + "'))" + fzzdstr + ")");
				System.out.println("自动电量审核条数" + strall.toString());
				rs = db.queryAll(strall.toString());

				while (rs.next()) {
					count = rs.getString(1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DbException de) {
			de.printStackTrace();
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
		return count;
	}

	// 自动电量审核折算后用电量总和统计
	public String getCountGree(String whereStr, String loginId, String str) {
		String sql = "";
		String count = "";
		DataBase db = new DataBase();
		String fzzdstr = "";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
				fzzdstr = getFuzeZdid(db, loginId);

				StringBuffer strall = new StringBuffer();
				strall
						.append("select sum(to_char(blhdl,'fm9999999990.00')) "
								+ "from dianduview ad,dianbiao db,zhandian zd "
								+ "where ad.ammeterid_fk=db.dbid and db.dbyt<>'dbyt02' and db.zdid=zd.id and zd.qyzt='1' and ad.amuuid is null and (ad.manualauditstatus = '0' or ad.manualauditstatus is null) "
								+ whereStr
								+ str
								+ " "
								+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
								+ loginId + "'))" + fzzdstr + ")");
				System.out.println("自动电量审核折算后用电量总和统计：" + strall.toString());
				rs = db.queryAll(strall.toString());

				while (rs.next()) {
					count = rs.getString(1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DbException de) {
			de.printStackTrace();
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
		return count;
	}

	// 自动电量审核导出
	public synchronized ArrayList getPageDatash(String whereStr, String loginId) {

		System.out.println("AmmeterDegreeBean-getPageData:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select distinct AMMETERDEGREEID,zd.jzname,db.dllx electriccurrenttype_ammeter,(select name from indexs where code=db.ydsb and type = 'YDSB') usageofelectypeid_ammeter,"
					+ "AMMETERID_FK,LASTDEGREE,THISDEGREE,to_char(LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,to_char(THISDATETIME,'yyyy-mm-dd') THISDATETIME,FLOATDEGREE,"
					+ "(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"
					+ "(select name from indexs where code=zd.property and type = 'ZDSX') as property,"
					+ "(select name from indexs where code=zd.jztype and type = 'ZDLX') as jztype,"
					+ "ACTUALDEGREE,ad.autoauditstatus,ad.autoauditdescription,ad.manualauditstatus,case when ad.ammeterdegreeid  in (select t.ammeterdegreeid_fk from dianfeiview t) or ad.manualauditstatus='0' or ad.manualauditstatus is null then '1' else '0' end feesbz,ad.entrytime,ad.blhdl from dianduview ad,dianbiao db,zhandian zd "
					+ "where ad.ammeterid_fk=db.dbid and db.dbyt<>'dbyt02' and db.zdid=zd.id and zd.qyzt='1' and ad.amuuid is null and (ad.manualauditstatus = '0' or ad.manualauditstatus is null) "
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ")  order by ENTRYTIME desc,JZNAME desc";
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("自动电量审核导出" + sql.toString());

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

		return list;
	}

	// 增加电费显示电量列表(暂不用)（ammeters）

	public synchronized ArrayList getPageDataList(int start, String whereStr,
			String loginId) {

		System.out.println("AmmeterDegreeBean-getPageData:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// 调用负责站点条件函数
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select zd.jzname,AMMETERDEGREEID,am.electriccurrenttype_ammeter electriccurrenttype_ammeter,(select name from indexs where code=am.usageofelectypeid_ammeter and type = 'YDSB') usageofelectypeid_ammeter,"
					+ "AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,THISDATETIME,FLOATDEGREE,"
					+ "ACTUALDEGREE,ad.autoauditstatus,ad.autoauditdescription from dianduview ad,ammeters am,zhandian zd "
					+ "where ad.ammeterid_fk=am.ammeterid and ad.ammeterdegreeid not in(select ammeterdegreeid_fk  from electricfees ) "
					+ "and am.stationid=zd.id and am.ammeteruse ='dbyt01' and ad.manualauditstatus>='1' "
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ") order by THISDATETIME desc";
			System.out.println("--" + sql.toString());
		} catch (DbException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		ResultSet rs = null;
		try {
			db.connectDb();
			StringBuffer strall = new StringBuffer();
			strall
					.append("select count(*) from ammeterdegrees ad,ammeters am,zhandian zd where ad.ammeterid_fk=am.ammeterid and ad.ammeterdegreeid not in(select ammeterdegreeid_fk  from electricfees ) "
							+ "and am.stationid=zd.id and am.ammeteruse ='dbyt01' and ad.manualauditstatus>='1' "
							+ whereStr
							+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
							+ loginId + "'))" + fzzdstr + ") ");
			rs = db.queryAll(strall.toString());
			if (rs.next()) {
				this.setAllPage((rs.getInt(1) + 14) / 15);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
			Query query = new Query();
			list = query.query(rs);
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

	private int allPage;

	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}

	public int getAllPage() {
		return this.allPage;
	}

	// 负责站点条件
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
		if (rs != null) {
			rs.close();
		}
		if (db != null) {
			db.close();
		}
		System.out.println("负责站点条件：" + cxtj);
		return cxtj.toString();
	}

	/**
	 * 添加电量 以下是要添加的信息 导入 新增
	 */
	public synchronized String addDegree(AmmeterDegreeFormBean formBean,
			String bzw) {
		String msg = "保存电量失败！请重试或与管理员联系！";
		// System.out.println(msg + "***************************************");
		MD5 md = new MD5();
		CTime ctime = new CTime();
		DataBase db = new DataBase();
		int flag = 1;
		// 判断需要保存的电量信息是否重复
		// 获取页面上次电表读数
		String lastderee1 = formBean.getLastdegree();
		// 获取页面本次电表读数
		String thisdegree = formBean.getThisdegree();
		// 获取页面本次抄表时间
		String thisdatetime = formBean.getThisdatetime();
		// 获取页面电表ID
		String ammeterid_fk = formBean.getAmmeteridFk();

		String sqlbj = "select lastdegree,thisdegree,thisdatetime,ammeterid_fk from dianduview where lastdegree='"
				+ lastderee1
				+ "' and ammeterid_fk='"
				+ ammeterid_fk
				+ "' and thisdegree='"
				+ thisdegree
				+ "' and to_char(thisdatetime,'yyyy-mm-dd')='"
				+ thisdatetime
				+ "'";
		// msg = "电量信息重复，请确认后重新输入！";
		PreparedStatement psbj = null;
		ResultSet rs1 = null;
		boolean b = false;
		try {
			db.connectDb();
			System.out.println(sqlbj.toString()
					+ "******************************");
			psbj = db.getPreparedStatement(sqlbj);
			rs1 = psbj.executeQuery();
			b = rs1.next();
		} catch (DbException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		// 添加电量
		String start = formBean.getStartmonth();
		String end = formBean.getEndmonth();
		// int startn = Integer.parseInt(start.substring(0, 4));
		// int starty = Integer.parseInt(start.substring(5, 7));
		// int endn = Integer.parseInt(end.substring(0, 4));
		// int endy = Integer.parseInt(end.substring(5, 7));
		// int time = (endn - startn) * 12 + endy - starty + 1;
		// System.out.println("--" + startn + "**" + starty + "**" + time +
		// "****"
		// + formBean.getBlhdl());

		// double yushu = Double.parseDouble(formBean.getBlhdl()) % time;
		// double yushusj = Double.parseDouble(formBean.getActualdegree())%
		// time;

		// int dianduPermonth = (int) ((Double.parseDouble(formBean.getBlhdl())
		// - yushu) / time);
		// int actualdegreemonth = (int)
		// ((Double.parseDouble(formBean.getActualdegree()) - yushusj) / time);

		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int) (100 + Math.random()
				* (999 - 100 + 1)));
		String uuid = uuid2 + Long.toString(uuid1).substring(3);
		// String[] sqlBatch = new String[time];

		List year_month = new ArrayList();
		List diandu = new ArrayList();
		List actualdegree1 = new ArrayList();
		String s = "sysdate";
		// for (int i = 0; i < time; i++) {
		// String yue = String.valueOf(starty);
		// if (yue.length() == 1)
		// yue = "0" + yue;
		// String year_month_tmp = startn + "-" + yue;
		// starty++;
		// if (starty > 12) {
		// starty = 1;
		// startn++;
		// }
		// year_month.add(year_month_tmp);
		// System.out.println("--" + year_month_tmp);
		// if (i == time - 1) {
		// diandu.add(dianduPermonth + yushu);
		// actualdegree1.add(actualdegreemonth + yushusj);
		// } else {
		// diandu.add(dianduPermonth);
		// actualdegree1.add(actualdegreemonth);
		// }
		// }
		// for (int i = 0; i < time; i++) {

		// 2014-7-21 管理表结算周期
		long glzq = new ElectricTool().getQuot(formBean.getLastdatetime(),
				formBean.getThisdatetime()) + 1;
		// StringBuffer sql = new StringBuffer();
		String sqltx = "INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,"
				+ "THISDATETIME,FLOATDEGREE,ACTUALDEGREE,BLHDL,"
				+ "INPUTOPERATOR,INPUTDATETIME,STARTMONTH,ENDMONTH,"
				+ "MEMO,AUTOAUDITSTATUS,MANUALAUDITSTATUS,UUID,"
				+ "FEESBZ,flag,entrypensonnel,entrytime,dlbzw,GLZQ,BEILV,HBZQ,BZZ,CSDB,SCB,DBYDL) VALUES('"
				+ formBean.getAmmeteridFk()
				+ "','"
				+ formBean.getLastdegree()
				+ "','"
				+ formBean.getThisdegree()
				+ "',TO_DATE('"
				+ formBean.getLastdatetime()
				+ "','yyyy-mm-dd'),"
				+ "TO_DATE('"
				+ formBean.getThisdatetime()
				+ "','yyyy-mm-dd'),'"
				+ formBean.getFloatdegree()
				+ "','"
				+ formBean.getActualdegree()
				+ "','"
				+ formBean.getBlhdl()
				+ "',"
				+ "'"
				+ formBean.getInputoperator()
				+ "',TO_DATE('"
				+ formBean.getInputdatetime()
				+ "','yyyy-mm-dd'),TO_DATE('"
				+ start
				+ "','yyyy-mm'),TO_DATE('"
				+ end
				+ "','yyyy-mm'),"
				+ "'"
				+ formBean.getMemo()
				+ "','1','0','"
				+ uuid
				+ "',"
				+ "'0','"
				+ flag
				+ "','"
				+ formBean.getEntrypensonnel()
				+ "',"
				+ s
				+ ",'"
				+ bzw
				+ "',"
				+ glzq
				+ ","
				+ formBean.getBeilv()
				+ ","
				+ formBean.getHbzq()
				+ ","
				+ formBean.getBzz()
				+ ","
				+ formBean.getScbbl()
				+ ","
				+ formBean.getScb()
				+ ","
				+ formBean.getDbydl() + ")";

		System.out.println("增加电量sql：" + sqltx.toString());
		// sql
		// .append("INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,THISDATETIME,FLOATDEGREE,ACTUALDEGREE,BLHDL,INPUTOPERATOR,INPUTDATETIME,STARTMONTH,ENDMONTH,MEMO,AUTOAUDITSTATUS,MANUALAUDITSTATUS,UUID,FEESBZ,flag,entrypensonnel,entrytime,dlbzw)");
		// sql.append(" VALUES('" + formBean.getAmmeteridFk() + "'");
		// sql.append(",'" + formBean.getLastdegree() + "','"
		// + formBean.getThisdegree() + "','"
		// + formBean.getLastdatetime() + "','"
		// + formBean.getThisdatetime() + "','"
		// + formBean.getFloatdegree() + "'");
		// sql.append(",'" + actualdegree1.get(i) + "','"
		// + diandu.get(i) + "','" + formBean.getInputoperator()
		// + "','" + formBean.getInputdatetime() + "'" + ",'"
		// + year_month.get(i) + "','" + year_month.get(i) + "','"
		// + formBean.getMemo() + "','1','" + "0" + "','" + uuid
		// + "','0','" + flag + "','" + formBean.getEntrypensonnel()
		// + "'," + s + ",'" + bzw + "' )");
		// System.out.println(sql.toString());
		// sqlBatch[i] = sql.toString();

		// }

		try {
			db.connectDb();
			if (b) {
				msg = "电量信息重复，请确认后重新输入！";
			} else {
				db.update(sqltx.toString());
				msg = "添加电量成功！";
			}
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		return msg;
	}

	public synchronized ArrayList selectXS01(String dbid) {
		AmmeterDegreeFormBean formBean = new AmmeterDegreeFormBean();
		String sql = "select linelosstype,linelossvalue from dianbiao where dbid=(select db.dbid from zhandian zd , dianbiao db  where zd.zdcode='qd_19090' and zd.id=db.zdid(+))";

		System.out.println("--" + sql.toString());
		ResultSet rs = null;
		DataBase db = new DataBase();
		ArrayList list = new ArrayList();
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			Query query = new Query();
			list = query.query(rs);

		} catch (Exception e) {
			// TODO Auto-generated catch block
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

		return list;
	}

	// 获得线损类型 ，线损值
	public synchronized ArrayList selectXS(String dbid) {
		AmmeterDegreeFormBean formBean = new AmmeterDegreeFormBean();
		String sql = "select linelosstype,linelossvalue from dianbiao where dbid='"
				+ dbid + "'";

		System.out.println("--" + sql.toString());
		ResultSet rs = null;
		DataBase db = new DataBase();
		ArrayList list = new ArrayList();
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			Query query = new Query();
			list = query.query(rs);

		} catch (Exception e) {
			// TODO Auto-generated catch block
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

		return list;
	}

	public synchronized ArrayList selectXSX(String dbid) {
		AmmeterDegreeFormBean formBean = new AmmeterDegreeFormBean();
		String sql = "select db.beilv,db.linelosstype,db.linelossvalue,db.bgsign,zd.zlfh,zd.jlfh,zd.property,zd.edhdl,zd.scb,zd.shi,zd.qsdbdl,zd.stationtype from dianbiao db,zhandian zd where db.zdid = zd.id and db.dbid='"
				+ dbid + "'";
		System.out.println("电量列表--添加电量带出信息：" + sql.toString());
		ResultSet rs = null;
		DataBase db = new DataBase();
		ArrayList list = new ArrayList();
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			Query query = new Query();
			list = query.query(rs);

		} catch (Exception e) {
			// TODO Auto-generated catch block
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

		return list;
	}

	/**
	 * 添加电量电费 以下是要添加的信息
	 */
	public synchronized String addDegreeFees(AmmeterDegreeFormBean formBean, String uuid, String bzw) {
		System.out.println("=======2012====电费单添加方法执行=====");
		String msg = "保存电费单失败！请重试或与管理员联系！";
		int flag = 1;//自动审核标识,通过1 不需要市级审核    0   自动审核不通过需要市级审核
		String bbll = "";//(倍率耗电量-本地标电量)/本地标电量
		String qbl = "";//(倍率耗电量-全省标电量)/全省标电量
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt;
		String lastadtime = "";
		try {
			dt = sdf.parse(formBean.getLastdatetime());// 上次抄表时间
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			rightNow.add(Calendar.DAY_OF_YEAR, -1);// 日期加1天     上次抄表时间的真实值
			Date dt1 = rightNow.getTime();
			lastadtime = sdf.format(dt1);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
 
			System.out.println("输出电表的idformBean.getAmmeteridFk()        " + formBean.getAmmeteridFk());
		long lastdaydegree = formBean.getLastDayAmmeterDegree(formBean .getAmmeteridFk(), lastadtime);//前个表单     日耗电量
			System.out.println(lastdaydegree + "  上次");
		// 计算本次日耗电量
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		  
		double thisdaydegree;// 本次表单       日耗电量
		double daydegereerate = 0;// 这次与前次表单           日耗电量比值((thisdaydegree - lastdaydegree) / lastdaydegree) * 100;
		try {
			long diff = sd.parse(formBean.getThisdatetime()).getTime() - sd.parse(formBean.getLastdatetime()).getTime();
			long day = (diff / 1000 * 24 * 60 * 60) + 1;// 计算差多少天
			thisdaydegree = Double.parseDouble(formBean.getBlhdl()) / day;// 本次表单       日耗电量 
			daydegereerate = ((thisdaydegree - lastdaydegree) / lastdaydegree) * 100;
		} catch (ParseException e1) { 
			e1.printStackTrace();
		}
		/***以上计算出这次与前次表单           日耗电量比值*/
		
		
		AutoAuditBean bean = new AutoAuditBean();
		ArrayList fylist = new ArrayList();
		fylist = bean.getPageData(1, "");//获取  自动审核标识项的集合 permission_configuration t where ITEMLLAG = 2
		String ItemID = ""; //自动审核项的ID
		String ItemName = ""; //自动审核项的标识符
		String ItemValue = "";//自动审核项的配置值
		String ItemDescription = "";//自动审核项的功能描述
		String beilv = "";//电表的倍率值
		String 	autoauditstatus = "1",//自动审核状态  1 默认通过 
				autoauditdescription = "",
				autoauditdescription1 = "",
				autoauditdescription2 = "",
				autoauditdescription3 = "",
				autoauditdescription4 = "",
				autoauditdescription5 = "";
		String auditfee1 = "";
		String auditfee2 = "";
		String auditfee3 = "";
		String auditfee4 = "";
		String auditfee5 = "";

		String auditfee6 = "";
		String auditfee7 = "";
		String auditfee8 = "";
		String auditfee9 = "";
		String auditfee10 = "";

		String auditfee11 = "";
		String auditfee12 = "";
		String auditfee13 = "";
		String auditfee14 = "";
		String auditfee15 = "";

		String auditfee16 = "";
		String auditfee17 = "";
		String auditfee18 = "";
		String auditfee19 = "";
		String auditfee20 = "";

		String auditfee21 = "";
		String auditfee22 = "";
		String auditfee23 = "";
		String auditfee24 = "";
		String auditfee25 = "";

		String auditfee26 = "";
		String auditfee27 = "";
		String auditfee28 = "";
	 
		String floatdegree = formBean.getFloatdegree();//获取电量调整值
		String memo = formBean.getMemo();//电量备注
		String memo_bz = "1";
		double floatt = 0.0;//电度调整数
		if (null != floatdegree && !floatdegree.equals("")
				&& !floatdegree.equals(" ")) {
			floatt = Double.parseDouble(floatdegree);
		}  
		if (floatt!=0.0&&null== memo &&  memo.equals("") &&  memo.equals(" ")) {
			memo_bz = "0";// 用电量有调整说明
		}   
		DataBase db = new DataBase();
		
		int bzw1 = 0;// 循环判断标识,自动审核共9项
		if (fylist != null) {
			int fycount = ((Integer) fylist.get(0)).intValue();//
			
			for (int k = fycount; k < fylist.size() - 1; k += fycount) {
				bzw1++;
				ItemID = (String) fylist.get(k + fylist.indexOf("ITEMID"));
				ItemName = (String) fylist.get(k + fylist.indexOf("ITEMNAME"));
				ItemValue = (String) fylist
						.get(k + fylist.indexOf("ITEMVALUE"));
				ItemDescription = (String) fylist.get(k
						+ fylist.indexOf("ITEMDESCRIPTION"));
				/*一,验证是否有用电调整说明    没有则自动审核不通过*/
				if (ItemName.equals("AuditDegree1") && (!ItemValue.equals("0"))
						&& memo_bz.equals("0")) {
					autoauditstatus = "0";
					autoauditdescription1 = "用电调整没有说明！";
					auditfee1 = "0";
				} 
				
				
				// 验证本次日耗电量 多于上次日耗电量 的比重（本次日耗电量-上次日耗电量）/上次日耗电量 是否大于配置值
				/*二,验证本次日耗电量 多于上次日耗电量 的比重    否则则自动审核不通过*/
				if (ItemName.equals("AuditDegree4") && (!ItemValue.equals("0"))
						&& (daydegereerate > Double.parseDouble(ItemValue))) {
					autoauditstatus = "0";
					autoauditdescription4 = "平均日耗电量大于上次日均量" + ItemValue
							+ "%（本次交费日均耗电量大于最后一次交费的日均耗电量百分比）！";
					auditfee4 = "0";
				}
				
				
				
				String edsql = "select z.edhdl,d.beilv,z.qsdbdl,z.property,z.ktxs,z.shi,z.zlfh,z.jlfh,z.jcxs,z.fwxs,z.yfxs,z.yflx from zhandian z,dianbiao d "
						+ "where z.id=d.zdid and d.dbid='"
						+ formBean.getAmmeteridFk() + "'";
				ResultSet rs = null;
				String edhdl = "0";// 额定耗电量
				String qsdbdl = "0";// 全省定标电量
				String property = "";// 站点属性
				String ktxs = "";// 空调系数
				String shi = "";// 市
				String zlfh = "";// 直流负荷
				String jlfh = "";// 交流负荷
				String jcxs = "";// 基础系数
				String yfxs = "";// 月份系数
				String fwxs = "";// 房屋系数
				String fwlx = "";// 房屋类型
				try {
					DataBase db1 = new DataBase();
					db1.connectDb();
					rs = db1.queryAll(edsql);
					System.out.println("edsql:" + edsql);
					if (rs.next()) {
						edhdl = (null == rs.getString(1) ? "0" : rs
								.getString(1));
						beilv = (null == rs.getString(2) ? "1" : rs
								.getString(2));
						qsdbdl = (null == rs.getString(3) ? "0" : rs
								.getString(3));
						if ("".equals(qsdbdl)) {

							qsdbdl = "0";
						}
						property = (null == rs.getString(4) ? "" : rs
								.getString(4));
						ktxs = (null == rs.getString(5) ? "0" : rs.getString(5));
						if ("".equals(rs.getString(5))) {

							ktxs = "0";
						}
						System.out.println("ktxs开始:" + ktxs);
						shi = (null == rs.getString(6) ? "" : rs.getString(6));
						zlfh = (null == rs.getString(7) ? "0" : rs.getString(7));

						if ("".equals(zlfh)) {

							zlfh = "0";
						}
						jlfh = (null == rs.getString(8) ? "0" : rs.getString(8));
						if ("".equals(jlfh)) {

							jlfh = "0";
						}
						jcxs = (null == rs.getString(9) ? "0" : rs.getString(9));
						fwxs = (null == rs.getString(10) ? "0" : rs
								.getString(10));
						yfxs = (null == rs.getString(11) ? "0" : rs
								.getString(11));
						if ("".equals(yfxs)) {

							yfxs = "0";
						}
						fwlx = (null == rs.getString(12) ? "" : rs
								.getString(12));
						if ("".equals(fwlx)) {

							fwlx = "";
						}
					}
					db1.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 查找月份的系数

				String endm = formBean.getEndmonth();
				String endmo = endm.substring(5, 7);
				String yymonth = "";
				Double yf = null;// 站点的月份系数
				if (endmo.equals("01")) {
					yymonth = "YMONTH";
				}
				if (endmo.equals("02")) {
					yymonth = "EMONTH";
				}
				if (endmo.equals("03")) {
					yymonth = "SMONTH";
				}
				if (endmo.equals("04")) {
					yymonth = "SIMONTH";
				}
				if (endmo.equals("05")) {
					yymonth = "WMONTH";
				}
				if (endmo.equals("06")) {
					yymonth = "LMONTH";
				}
				if (endmo.equals("07")) {
					yymonth = "QMONTH";
				}
				if (endmo.equals("08")) {
					yymonth = "BMONTH";
				}
				if (endmo.equals("09")) {
					yymonth = "JMONTH";
				}
				if (endmo.equals("10")) {
					yymonth = "SHIMONTH";
				}
				if (endmo.equals("11")) {
					yymonth = "SYMONTH";
				}
				if (endmo.equals("12")) {
					yymonth = "SEMONTH";
				}
				String sqlyf = "select y." + yymonth
						+ " from zhandian zd, yfxs y where zd.shi=y.shicode";
				System.out.println("月份" + sqlyf.toString());
				ResultSet rsy = null;
				DataBase dbb = new DataBase();
				try {
					dbb.connectDb();
					rsy = dbb.queryAll(sqlyf);
					if (rsy.next()) {
						yf = Double.parseDouble(rsy.getString(1));
					}
					dbb.close();
				} catch (DbException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// 查找空调系数
				String sss = "SELECT K.ID,K.KSZLFH,K.JSZLSH,K.JZKTXS,K.JRWKTXS,K.XZZJKTXS,K.JYJFKTXS,K.QTKTXS,K.IDCJFKTXS,K.SJBZW FROM KTXS K ";
				ResultSet r = null;
				String id1 = "";
				String kszlfh = "", kszlfh1 = "", kszlfh2 = "", kszlfh3 = "", kszlfh4 = "", kszlfh5 = "", kszlfh6 = "";// 开始直流负荷
				String jszlfh = "", jszlfh1 = "", jszlfh2 = "", jszlfh3 = "", jszlfh4 = "", jszlfh5 = "", jszlfh6 = "";// 结束直流负荷
				String jz = "", jz1 = "", jz2 = "", jz3 = "", jz4 = "", jz5 = "", jz6 = "";// 基站空调系数
				String jrw = "", jrw1 = "", jrw2 = "", jrw3 = "", jrw4 = "", jrw5 = "", jrw6 = "";// 接入网空调系数
				String xzzj = "", xzzj1 = "", xzzj2 = "", xzzj3 = "", xzzj4 = "", xzzj5 = "", xzzj6 = "";// 乡镇支局空调系数
				String jyjf = "", jyjf1 = "", jyjf2 = "", jyjf3 = "", jyjf4 = "", jyjf5 = "", jyjf6 = "";// 局域机房空调系数
				String qt = "", qt1 = "", qt2 = "", qt3 = "", qt4 = "", qt5 = "", qt6 = "";// 其他空调系数
				String idcjf = "", idcjf1 = "", idcjf2 = "", idcjf3 = "", idcjf4 = "", idcjf5 = "", idcjf6 = "";// idc机房空调系数
				DataBase d = new DataBase();

				try {
					d.connectDb();
					r = d.queryAll(sss);
					while (r.next()) {
						id1 = r.getString(1);
						kszlfh = r.getString(2);
						jszlfh = r.getString(3);
						jz = r.getString(4);
						jrw = r.getString(5);
						xzzj = r.getString(6);
						jyjf = r.getString(7);
						qt = r.getString(8);
						idcjf = r.getString(9);

						if (id1.equals("1")) {
							kszlfh1 = kszlfh;
							jszlfh1 = jszlfh;
							jz1 = jz;
							jrw1 = jrw;
							xzzj1 = xzzj;
							jyjf1 = jyjf;
							qt1 = qt;
							idcjf1 = idcjf;
						} else if (id1.equals("2")) {
							kszlfh2 = kszlfh;
							jszlfh2 = jszlfh;
							jz2 = jz;
							jrw2 = jrw;
							xzzj2 = xzzj;
							jyjf2 = jyjf;
							qt2 = qt;
							idcjf2 = idcjf;
						} else if (id1.equals("3")) {
							kszlfh3 = kszlfh;
							jszlfh3 = jszlfh;
							jz3 = jz;
							jrw3 = jrw;
							xzzj3 = xzzj;
							jyjf3 = jyjf;
							qt3 = qt;
							idcjf3 = idcjf;
						} else if (id1.equals("4")) {
							kszlfh4 = kszlfh;
							jszlfh4 = jszlfh;
							jz4 = jz;
							jrw4 = jrw;
							xzzj4 = xzzj;
							jyjf4 = jyjf;
							qt4 = qt;
							idcjf4 = idcjf;
						} else if (id1.equals("5")) {
							kszlfh5 = kszlfh;
							jszlfh5 = jszlfh;
							jz5 = jz;
							jrw5 = jrw;
							xzzj5 = xzzj;
							jyjf5 = jyjf;
							qt5 = qt;
							idcjf5 = idcjf;
						} else if (id1.equals("6")) {
							kszlfh6 = kszlfh;
							jz6 = jz;
							jrw6 = jrw;
							xzzj6 = xzzj;
							jyjf6 = jyjf;
							qt6 = qt;
							idcjf6 = idcjf;
						}

					}

					d.close();
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 查找房屋系数
				String jcdxsql = "select f.id,f.jcxs from fwxs f ";
				ResultSet jr = null;
				String id = "", jcxsr = "", jcxsr1 = "", jcxsr2 = "", jcxsr3 = "";
				DataBase dbs = new DataBase();

				try {
					dbs.connectDb();
					jr = dbs.queryAll(jcdxsql);
					try {
						while (jr.next()) {
							id = jr.getString(1);
							jcxsr = jr.getString(2);
							if (id.equals("1")) {
								jcxsr1 = jcxsr;
							}
							if (id.equals("2")) {
								jcxsr2 = jcxsr;
							}
							if (id.equals("3")) {
								jcxsr3 = jcxsr;
							}
						}
						dbs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				CTime time = new CTime();
				// ts 为 本次抄表时间 和上次 抄表时间 之间的 天数
				long ts = time.getQuot(formBean.getLastdatetime(), formBean
						.getThisdatetime()) + 1;
				DecimalFormat fomcate = new DecimalFormat("0.00");
				// 本地定标 计算本月的本地定标 耗电量
				Double hdl = Double.parseDouble(edhdl) * ts;

				// 省定标 计算本月的本地全省定标 耗电量
				Double qsdbdll = Double.parseDouble(qsdbdl) * ts;

				Double ktxss = Double.parseDouble(ktxs);//基站的空调系数
				Double bchdl = Double.parseDouble(formBean.getBlhdl());//本月的倍率耗电量
				bbll = fomcate.format((bchdl - hdl) / hdl * 100);//(倍率耗电量-本地标电量)/本地标电量
				qbl = fomcate.format((bchdl - qsdbdll) / qsdbdll * 100);//(倍率耗电量-全省标电量)/全省标电量
				Double zlfhh = Double.parseDouble(zlfh);//站点直流负荷
				Double jlfhh = Double.parseDouble(jlfh);//站点交流负荷
				Double jcxss = Double.parseDouble(jcxs);//站点基础系数
				Double fwxss = Double.parseDouble(fwxs);//站点的房屋系数
				Double yfxss = Double.parseDouble(yfxs);//站点的月份系数
						System.out.println("bzw1:" + bzw1);
						System.out.println("自动审核没进来前    判断是否超出省定标的上下浮动的10%");
						System.out.println("qsdbdl:" + qsdbdl);
						System.out.println("ts:" + ts);
						System.out.println("bchdl:" + bchdl + "qsdbdll:" + qsdbdll);
				if (bzw1 == 9) {// 开始select * from permission_configuration t
								// where ITEMLLAG = 2 执行自动审核配置审核 共九条数据 不等于九则不审核
					System.out.println("自动审核进来执行开始____判断是否超出省定标的上下浮动的10%");
					if (!fomcate.format(qsdbdll).equals("0.00")) {// 如果全省定标电量不为零
						// 验证倍率耗电量是否超全省定标的 上下浮动 10%
						if ((bchdl < qsdbdll * (1 - 0.1) || bchdl > qsdbdll
								* (1 + 0.1))) {// 如果倍率耗电量小于全省定标耗电量90% 或者
												// 如果倍率耗电量大于全省定标耗电量110% 
							flag = 0;
							autoauditstatus = "0";
							autoauditdescription5 = autoauditdescription5
									+ "本次电量上下浮动超过全省定标的10%";
							auditfee5 = "0";
						}
						// 如果省定标为零 站点类型为基站
					} else if (fomcate.format(qsdbdll).equals("0.00")
							&& property.equals("zdsx02")) {// property.equals("zdsx02")表示站点类型是基站
															// (其他还有机房zdsx01,网络箱zdsx07)
						/**全省标不为零,站点属性是基站类型,本地标不为零**/
						if (hdl != 0) {// 如果本地标不等于零
							if ((bchdl < hdl * 0.9
									* (jcxss + yfxss * ktxss + fwxss)
									* (1 - 0.1) || bchdl > hdl * 0.9
									* (jcxss + yfxss * ktxss + fwxss)
									* (1 + 0.1))) {
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee21 = "0";
							}
						} else {
							if (zlfhh != 0) {
								if ((bchdl < zlfhh * 0.9
										* (jcxss + yfxss * ktxss + fwxss)
										* (1 - 0.1) || bchdl > zlfhh * 0.9
										* (jcxss + yfxss * ktxss + fwxss)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee22 = "0";
								}
							} else {
								if ((bchdl < jlfhh * 0.9
										* (jcxss + yfxss * ktxss + fwxss)
										* (1 - 0.1) || bchdl > jlfhh * 0.9
										* (jcxss + yfxss * ktxss + fwxss)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee23 = "0";
								}
							}

						}
					} else if (fomcate.format(qsdbdll).equals("0.00")
							&& property.equals("zdsx05")) {//本数据库中index静态表中没有说明zdsx05   站点属性 是什么类型
						Double jrwktxs = null;
						if (zlfhh >= Double.parseDouble(kszlfh1)
								&& zlfhh < Double.parseDouble(kszlfh2)) {
							jrwktxs = Double.parseDouble(jrw1);
						} else if (zlfhh >= Double.parseDouble(kszlfh2)
								&& zlfhh < Double.parseDouble(kszlfh3)) {
							jrwktxs = Double.parseDouble(jrw2);
						} else if (zlfhh >= Double.parseDouble(kszlfh3)
								&& zlfhh < Double.parseDouble(kszlfh4)) {
							jrwktxs = Double.parseDouble(jrw3);
						} else if (zlfhh >= Double.parseDouble(kszlfh4)
								&& zlfhh < Double.parseDouble(kszlfh5)) {
							jrwktxs = Double.parseDouble(jrw4);
						} else if (zlfhh >= Double.parseDouble(kszlfh5)
								&& zlfhh < Double.parseDouble(kszlfh6)) {
							jrwktxs = Double.parseDouble(jrw5);
						} else if (zlfhh >= Double.parseDouble(kszlfh6)) {
							jrwktxs = Double.parseDouble(jrw6);
						}
						Double jcxsjc = null;
						if (fwlx.equals("fwlx01")) {
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if (fwlx.equals("fwlx02")) {
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if (fwlx.equals("fwlx03")) {
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						System.out.println("jcxs:__________" + jcxsjc);
						System.out.println("jrwktxs:__________" + jrwktxs);
						if (hdl != 0) {

							if ((bchdl < hdl * 0.9 * (jcxsjc + yf * jrwktxs)
									* (1 - 0.1) || bchdl > hdl * 0.9
									* (jcxsjc + yf * jrwktxs) * (1 + 0.1))) {
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee6 = "0";
							}
						} else {
							if (zlfhh != 0) {
								if ((bchdl < zlfhh * 0.9
										* (jcxsjc + yf * jrwktxs) * (1 - 0.1) || bchdl > zlfhh
										* 0.9
										* (jcxsjc + yf * jrwktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee11 = "0";
								}
							} else {
								if ((bchdl < jlfhh * 0.9
										* (jcxsjc + yf * jrwktxs) * (1 - 0.1) || bchdl > jlfhh
										* 0.9
										* (jcxsjc + yf * jrwktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee12 = "0";
								}

							}

						}
					} else if (property.equals("zdsx01")
							&& qsdbdll.toString().equals("0.00")) {//全省标 为零 站点属性是机房
						Double jyjfktxs = null;//局域网的空调系数
						if (zlfhh >= Double.parseDouble(kszlfh1)
								&& zlfhh < Double.parseDouble(kszlfh2)) {//根据基站的的直流负荷,选定空调系数
							jyjfktxs = Double.parseDouble(jyjf1);
						} else if (zlfhh >= Double.parseDouble(kszlfh2)
								&& zlfhh < Double.parseDouble(kszlfh3)) {
							jyjfktxs = Double.parseDouble(jyjf2);
						} else if (zlfhh >= Double.parseDouble(kszlfh3)
								&& zlfhh < Double.parseDouble(kszlfh4)) {
							jyjfktxs = Double.parseDouble(jyjf3);
						} else if (zlfhh >= Double.parseDouble(kszlfh4)
								&& zlfhh < Double.parseDouble(kszlfh5)) {
							jyjfktxs = Double.parseDouble(jyjf4);
						} else if (zlfhh >= Double.parseDouble(kszlfh5)
								&& zlfhh < Double.parseDouble(kszlfh6)) {
							jyjfktxs = Double.parseDouble(jyjf5);
						} else if (zlfhh >= Double.parseDouble(kszlfh6)) {
							jyjfktxs = Double.parseDouble(jyjf6);
						}
						Double jcxsjc = null;//基础系数
						if (fwlx.equals("fwlx01")) {//根据站点房屋类型定基础系数
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if (fwlx.equals("fwlx02")) {
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if (fwlx.equals("fwlx03")) {
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						if (hdl != 0) {
							if (shi == "13701" || shi == "13702"
									|| shi == "13705") {
								if ((bchdl < hdl * 0.75
										* (jcxsjc + yf * jyjfktxs) * (1 - 0.1) || bchdl > hdl
										* 0.75
										* (jcxsjc + yf * jyjfktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee7 = "0";
								}
							} else if (shi == "13710" || shi == "13717") {
								if ((bchdl < hdl * 0.9
										* (jcxsjc + yf * jyjfktxs) * (1 - 0.1) || bchdl > hdl
										* 0.9
										* (jcxsjc + yf * jyjfktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee8 = "0";

								}
							} else if (shi == "13704") {
								if ((bchdl < hdl * 1 * (jcxsjc + yf * jyjfktxs)
										* (1 - 0.1) || bchdl > hdl * 1
										* (jcxsjc + yf * jyjfktxs) * (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee9 = "0";

								}
							} else {
								if ((bchdl < hdl * 0.85
										* (jcxsjc + yf * jyjfktxs) * (1 - 0.1) || bchdl > hdl
										* 0.85
										* (jcxsjc + yf * jyjfktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee10 = "0";

								}
							}
						} else {
							if (zlfhh != 0) {
								if (shi == "13701" || shi == "13702"
										|| shi == "13705") {
									if ((bchdl < zlfhh * 0.75
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > zlfhh * 0.75
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee13 = "0";
									}
								} else if (shi == "13710" || shi == "13717") {
									if ((bchdl < zlfhh * 0.9
											* (1 + yf * jyjfktxs)
											* (jcxsjc - 0.1) || bchdl > zlfhh
											* 0.9 * (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee14 = "0";

									}
								} else if (shi == "13704") {
									if ((bchdl < zlfhh * 1
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > zlfhh * 1
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee15 = "0";

									}
								} else {
									if ((bchdl < zlfhh * 0.85
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > zlfhh * 0.85
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee16 = "0";

									}
								}
							} else {
								if (shi == "13701" || shi == "13702"
										|| shi == "13705") {
									if ((bchdl < jlfhh * 0.75
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > jlfhh * 0.75
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee17 = "0";
									}
								} else if (shi == "13710" || shi == "13717") {
									if ((bchdl < jlfhh * 0.9
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > jlfhh * 0.9
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee18 = "0";

									}
								} else if (shi == "13704") {
									if ((bchdl < jlfhh * 1
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > jlfhh * 1
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee19 = "0";

									}
								} else {
									if ((bchdl < jlfhh * 0.85
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > jlfhh * 0.85
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee20 = "0";

									}
								}
							}
						}
					} else if (property.equals("zdsx06")
							&& qsdbdll.toString().equals("0.00")) {//本数据库中不存在该属性
						Double xzzjktxs = null;
						if (zlfhh >= Double.parseDouble(kszlfh1)
								&& zlfhh < Double.parseDouble(kszlfh2)) {
							xzzjktxs = Double.parseDouble(xzzj1);
						} else if (zlfhh >= Double.parseDouble(kszlfh2)
								&& zlfhh < Double.parseDouble(kszlfh3)) {
							xzzjktxs = Double.parseDouble(xzzj2);
						} else if (zlfhh >= Double.parseDouble(kszlfh3)
								&& zlfhh < Double.parseDouble(kszlfh4)) {
							xzzjktxs = Double.parseDouble(xzzj3);
						} else if (zlfhh >= Double.parseDouble(kszlfh4)
								&& zlfhh < Double.parseDouble(kszlfh5)) {
							xzzjktxs = Double.parseDouble(xzzj4);
						} else if (zlfhh >= Double.parseDouble(kszlfh5)
								&& zlfhh < Double.parseDouble(kszlfh6)) {
							xzzjktxs = Double.parseDouble(xzzj5);
						} else if (zlfhh >= Double.parseDouble(kszlfh6)) {
							xzzjktxs = Double.parseDouble(xzzj6);
						}
						Double jcxsjc = null;
						if (fwlx.equals("fwlx01")) {
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if (fwlx.equals("fwlx02")) {
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if (fwlx.equals("fwlx03")) {
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						if (hdl != 0) {
							if ((bchdl < hdl * 0.9 * (jcxsjc + yf * xzzjktxs)
									* (1 - 0.1) || bchdl > hdl * 0.9
									* (jcxsjc + yf * xzzjktxs) * (1 + 0.1))) {
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee21 = "0";
							}
						} else {
							if (zlfhh != 0) {
								if ((bchdl < zlfhh * 0.9
										* (jcxsjc + yf * xzzjktxs) * (1 - 0.1) || bchdl > zlfhh
										* 0.9
										* (jcxsjc + yf * xzzjktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee22 = "0";
								}
							} else {
								if ((bchdl < jlfhh * 0.9
										* (jcxsjc + yf * xzzjktxs) * (1 - 0.1) || bchdl > jlfhh
										* 0.9
										* (jcxsjc + yf * xzzjktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee23 = "0";
								}

							}

						}
					} else if (property.equals("zdsx04")
							&& qsdbdll.toString().equals("0.00")) {//这个属性 本数据库中不存在
						Double qtktxs = null;
						if (zlfhh >= Double.parseDouble(kszlfh1)
								&& zlfhh < Double.parseDouble(kszlfh2)) {
							qtktxs = Double.parseDouble(qt1);
						} else if (zlfhh >= Double.parseDouble(kszlfh2)
								&& zlfhh < Double.parseDouble(kszlfh3)) {
							qtktxs = Double.parseDouble(qt2);
						} else if (zlfhh >= Double.parseDouble(kszlfh3)
								&& zlfhh < Double.parseDouble(kszlfh4)) {
							qtktxs = Double.parseDouble(qt3);
						} else if (zlfhh >= Double.parseDouble(kszlfh4)
								&& zlfhh < Double.parseDouble(kszlfh5)) {
							qtktxs = Double.parseDouble(qt4);
						} else if (zlfhh >= Double.parseDouble(kszlfh5)
								&& zlfhh < Double.parseDouble(kszlfh6)) {
							qtktxs = Double.parseDouble(qt5);
						} else if (zlfhh >= Double.parseDouble(kszlfh6)) {
							qtktxs = Double.parseDouble(qt6);
						}
						Double jcxsjc = null;
						if (fwlx.equals("fwlx01")) {
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if (fwlx.equals("fwlx02")) {
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if (fwlx.equals("fwlx03")) {
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						if (hdl != 0) {
							if ((bchdl < hdl * 0.9 * (jcxsjc + yf * qtktxs)
									* (1 - 0.1) || bchdl > hdl * 0.9
									* (jcxsjc + yf * qtktxs) * (1 + 0.1))) {
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee24 = "0";
							}
						} else {
							if (zlfhh != 0) {
								if ((bchdl < zlfhh * 0.9
										* (jcxsjc + yf * qtktxs) * (1 - 0.1) || bchdl > zlfhh
										* 0.9
										* (jcxsjc + yf * qtktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee25 = "0";
								}
							} else {
								if ((bchdl < jlfhh * 0.9
										* (jcxsjc + yf * qtktxs) * (1 - 0.1) || bchdl > jlfhh
										* 0.9
										* (jcxsjc + yf * qtktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee26 = "0";
								}

							}

						}
					} else if (property.equals("zdsx07")
							&& qsdbdll.toString().equals("0.00")) {//全省标为零 站点属性是网络箱
						Double idcjfktxs = null;//idc机房的空调系数
						if (zlfhh >= Double.parseDouble(kszlfh1)
								&& zlfhh < Double.parseDouble(kszlfh2)) {
							idcjfktxs = Double.parseDouble(idcjf1);
						} else if (zlfhh >= Double.parseDouble(kszlfh2)
								&& zlfhh < Double.parseDouble(kszlfh3)) {
							idcjfktxs = Double.parseDouble(idcjf2);
						} else if (zlfhh >= Double.parseDouble(kszlfh3)
								&& zlfhh < Double.parseDouble(kszlfh4)) {
							idcjfktxs = Double.parseDouble(idcjf3);
						} else if (zlfhh >= Double.parseDouble(kszlfh4)
								&& zlfhh < Double.parseDouble(kszlfh5)) {
							idcjfktxs = Double.parseDouble(idcjf4);
						} else if (zlfhh >= Double.parseDouble(kszlfh5)
								&& zlfhh < Double.parseDouble(kszlfh6)) {
							idcjfktxs = Double.parseDouble(idcjf5);
						} else if (zlfhh >= Double.parseDouble(kszlfh6)) {
							idcjfktxs = Double.parseDouble(idcjf6);
						}
						Double jcxsjc = null;//IDC机房的基础系数
						if (fwlx.equals("fwlx01")) {
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if (fwlx.equals("fwlx02")) {
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if (fwlx.equals("fwlx03")) {
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						if (hdl != 0) {
							if ((bchdl < hdl * 0.9 * (jcxsjc + yf * idcjfktxs)
									* (1 - 0.1) || bchdl > hdl * 0.9
									* (jcxsjc + yf * idcjfktxs) * (1 + 0.1))) {
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee27 = "0";
							}
						} else {
							if (zlfhh != 0) {
								if ((bchdl < zlfhh * 0.9
										* (jcxsjc + yf * idcjfktxs) * (1 - 0.1) || bchdl > zlfhh
										* 0.9
										* (jcxsjc + yf * idcjfktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee28 = "0";
								}
							} else {
								if ((bchdl < jlfhh * 0.9
										* (jcxsjc + yf * idcjfktxs) * (1 - 0.1) || bchdl > jlfhh
										* 0.9
										* (jcxsjc + yf * idcjfktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									 
								}

							}

						}
					}
					System.out.println("自动审核进来执行if(bzw1==9)___结束");
				}// if(bzw1==9)结束select * from permission_configuration t where
					// ITEMLLAG = 2 执行自动审核配置审核 共九条数据 不等于九则不审核

				if (ItemName.equals("AuditDegree6")) {

					if (bchdl < hdl * (1 - Double.parseDouble(ItemValue) / 100)
							|| bchdl > hdl
									* (1 + Double.parseDouble(ItemValue) / 100)) {
						flag = 0;
						autoauditstatus = "0";
						autoauditdescription = autoauditdescription
								+ "本次电量上下浮动超过站点额定耗电量计算值的" + ItemValue + "%"
								+ ";";
					}
				}
			}//for循环结束
			if (auditfee1.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription1 + ";";
			}
			if (auditfee2.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription2 + ";";
			}
			if (auditfee3.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription3 + ";";
			}
			if (auditfee4.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription4 + ";";
			}
			if (auditfee5.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee6.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee7.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee8.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee9.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee10.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee11.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee12.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee13.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee14.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee15.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee16.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee17.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee18.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee19.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee20.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
		}
		
		String start = formBean.getStartmonth();//起始年月
		String end = formBean.getEndmonth();//结束年月
		String aa = formBean.getScdl();//生产用电量
		String bb = formBean.getBgdl();//办公用电量
		String cc = formBean.getYydl();//营业用电量
		String dd = formBean.getXxhdl();//信息化支撑耗电量
		String ee = formBean.getJstzdl();//建设投资耗电量
		String ff = formBean.getDddfdl();// 代垫电量
		if ("".equals(aa) || aa == null)
			aa = "0";
		if ("".equals(bb) || bb == null)
			bb = "0";
		if ("".equals(cc) || cc == null)
			cc = "0";
		if ("".equals(dd) || dd == null)
			dd = "0";
		if ("".equals(ee) || ee == null)
			ee = "0";
		if ("".equals(ff) || ff == null)
			ff = "0";
		

		String s = "to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";

		// ======================2013-10-09新模式==================================
		String sqlx = "INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,"
				+ "THISDATETIME,FLOATDEGREE,ACTUALDEGREE,INPUTOPERATOR,"
				+ "STARTMONTH,ENDMONTH,MEMO,AUTOAUDITSTATUS,"
				+ "AUTOAUDITDESCRIPTION,UUID,BLHDL,flag,"
				+ "entrypensonnel,entrytime,NETWORKHDL,ADMINISTRATIVEHDL,"
				+ "MARKETHDL,INFORMATIZATIONHDL,BUILDHDL,DLBZW,"
				+ "MANUALAUDITSTATUS,DEDHDL,DDDF,csdb,LASTELECFEES,LASTELECDEGREE,LASTUNITPRICEOLD)"
				+ " VALUES('"
				+ formBean.getAmmeteridFk()
				+ "','"
				+ formBean.getLastdegree()
				+ "','"
				+ formBean.getThisdegree()
				+ "',TO_DATE('"
				+ formBean.getLastdatetime()
				+ "','yyyy-mm-dd'),"
				+ " TO_DATE('"
				+ formBean.getThisdatetime()
				+ "','yyyy-mm-dd'),'"
				+ formBean.getFloatdegree()
				+ "','"
				+ formBean.getActualdegree()
				+ "','"
				+ formBean.getInputoperator()
				+ "',"
				+ " TO_DATE('"
				+ start
				+ "','yyyy-mm'),TO_DATE('"
				+ end
				+ "','yyyy-mm'),'"
				+ formBean.getMemo()
				+ "','"
				+ autoauditstatus
				+ "',"
				+ " '"
				+ autoauditdescription
				+ "','"
				+ uuid
				+ "','"
				+ formBean.getBlhdl()
				+ "','"
				+ flag
				+ "',"
				+ " '"
				+ formBean.getEntrypensonnel()
				+ "',"
				+ s
				+ ",'"
				+ Double.parseDouble(aa)
				+ "','"
				+ Double.parseDouble(bb)
				+ "',"
				+ " '"
				+ Double.parseDouble(cc)
				+ "','"
				+ Double.parseDouble(dd)
				+ "','"
				+ Double.parseDouble(ee)
				+ "','"
				+ bzw
				+ "',"
				+ " '0','"
				+ bbll
				+ "','"
				+ Double.parseDouble(ff)
				+ "',"
				+ qbl
				+ ","
				+ Double.valueOf(formBean.getLastdf())
				+ ","
				+ Double.valueOf(formBean.getLastdl())
				+ ","
				+ formBean.getScdj() + ")";

		System.out.println("新模式增加电量sql：" + sqlx);
		

		try {

			db.connectDb();
			db.update(sqlx);// 最新模式
			// db.updateBatch(sqlBatch);原始模式
			msg = "添加电量成功!";
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	/**
	 * 删除电量
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String delAmmeterDegree(String degreeid) {
		String msg = "删除账号失败！";
		DataBase db = new DataBase();

		try {
			db.connectDb();

			String sql1 = "DELETE ammeterdegrees WHERE uuid=(select a.uuid from ammeterdegrees a where a.ammeterdegreeid="
					+ degreeid + ")"; // 删除账号表

			System.out.println(sql1.toCharArray());
			// String delNames = getName(accountId, db);
			msg = "删除失败！";
			db.update(sql1);
			msg = "删除成功！";
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	/**
	 * 电量批量删除
	 */
	public synchronized String deletesElectricFees(String ammeterid_fk) {

		String msg = "删除电量失败！";
		DataBase db = new DataBase();
		String uuid = "";

		try {
			db.connectDb();
			String que = "select am.uuid from ammeterdegrees am where am.ammeterdegreeid in("
					+ ammeterid_fk + ")";
			ResultSet rs = null;
			System.out.println("查询uuid:" + que);
			rs = db.queryAll(que.toString());

			while (rs.next()) {
				String uuid1 = rs.getString("uuid");
				uuid = uuid1 + "," + uuid;
			}
			rs.close();// rsg

			String uu = uuid.substring(0, uuid.length() - 1);

			String delete = "delete from ammeterdegrees where uuid in(" + uu
					+ ")";
			System.out.println("删除:" + delete.toString());
			msg = "删除电量失败！";
			db.update(delete);

			msg = "删除电量成功！";

		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
		return msg;
	}

	/**
	 * 修改电量信息
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String modifyAmmeterDegree(
			AmmeterDegreeFormBean formBean, String idStr, String id) {
		String msg = "修改电量信息失败！";
		DataBase db = new DataBase();
		int flag = 1;

		/*
		 * // 自动审核判断 // 计算这次与上次日耗电量比值 long lastdaydegree =
		 * formBean.getLastDayAmmeterModfiyDegree(formBean .getAmmeteridFk(),
		 * formBean.getAmmeterdegreeid()); // System.out.println(lastdaydegree);
		 * // 计算本次日耗电量 SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		 * long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数 // 获得两个时间的毫秒时间差异 long diff,
		 * day; double thisdaydegree, daydegereerate = 0; try { //
		 * System.out.println
		 * ("222222:"+sd.parse(formBean.getThisdatetime()).getTime()); diff =
		 * sd.parse(formBean.getThisdatetime()).getTime() -
		 * sd.parse(formBean.getLastdatetime()).getTime(); day = diff / nd;//
		 * 计算差多少天 thisdaydegree = Double.parseDouble(formBean.getBlhdl()) /
		 * day;// 本次日耗电量 daydegereerate = ((thisdaydegree - lastdaydegree) /
		 * lastdaydegree) * 100;// 这次与上次日耗电量比值 //
		 * System.out.println("222222:"+thisdaydegree
		 * +"****"+lastdaydegree+"*****"+daydegereerate); } catch
		 * (ParseException e1) {
		 * 
		 * e1.printStackTrace(); } AutoAuditBean bean = new AutoAuditBean();
		 * ArrayList fylist = new ArrayList(); fylist = bean.getPageData(1, "");
		 * String ItemID = "", beilv = "1", ItemName = "", ItemValue = "",
		 * ItemDescription = ""; String autoauditstatus = "1",
		 * autoauditdescription = "", autoauditdescription1 = "",
		 * autoauditdescription2 = "", autoauditdescription3 = "",
		 * autoauditdescription4 = ""; String auditfee1 = ""; String auditfee2 =
		 * ""; String auditfee3 = ""; String auditfee4 = ""; String ad2_bz =
		 * formBean.getAd2_bz(); System.out.println(ad2_bz); String floatdegree
		 * = formBean.getFloatdegree(); String memo = formBean.getMemo(); String
		 * ad1_bz = "0"; String floatdegree_bz = ""; String memo_bz = ""; if
		 * (floatdegree == null || floatdegree == "" || floatdegree.equals("0"))
		 * { floatdegree_bz = "0"; } else { floatdegree_bz = "1"; } if (memo ==
		 * null || memo == "") { memo_bz = "0"; } else { memo_bz = "1"; } if
		 * (memo_bz.equals(floatdegree_bz)) { ad1_bz = "1"; } //
		 * System.out.println(floatdegree_bz+"*****"+memo_bz+"****"+ad1_bz); if
		 * (fylist != null) { int fycount = ((Integer)
		 * fylist.get(0)).intValue(); for (int k = fycount; k < fylist.size() -
		 * 1; k += fycount) {
		 * 
		 * ItemID = (String) fylist.get(k + fylist.indexOf("ITEMID")); ItemName
		 * = (String) fylist.get(k + fylist.indexOf("ITEMNAME")); ItemValue =
		 * (String) fylist .get(k + fylist.indexOf("ITEMVALUE"));
		 * ItemDescription = (String) fylist.get(k +
		 * fylist.indexOf("ITEMDESCRIPTION"));
		 * 
		 * if (ItemName.equals("AuditDegree1") && (!ItemValue.equals("0")) &&
		 * ad1_bz.equals("0")) { //
		 * System.out.println(ItemName+"*****"+ItemValue+"****");
		 * autoauditstatus = "0"; autoauditdescription1 = "用电调整没有说明！"; auditfee1
		 * = "0"; } if (ItemName.equals("AuditDegree2") &&
		 * (!ItemValue.equals("0")) && ad2_bz.equals("1")) { //
		 * System.out.println(ItemName+"*****"+ItemValue+"****");
		 * autoauditstatus = "0"; autoauditdescription2 = "上次电表读数与最后抄表数不一致！";
		 * auditfee2 = "0"; }
		 * 
		 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); int
		 * result = 0; try { result = (format.parse(formBean.getLastdatetime()
		 * .toString())).compareTo(format.parse(formBean
		 * .getThisdatetime().toString())); //
		 * System.out.println("****"+result); } catch (ParseException e) {
		 * 
		 * e.printStackTrace(); } if (ItemName.equals("AuditDegree3") &&
		 * (!ItemValue.equals("0")) && (result > 0)) {
		 * System.out.println(ItemName + "*****" + ItemValue + "****" + result);
		 * autoauditstatus = "0"; autoauditdescription3 = "抄表时间小于本次抄表时间！";
		 * auditfee3 = "0"; } if (ItemName.equals("AuditDegree4") &&
		 * (daydegereerate > Double.parseDouble(ItemValue))) {
		 * System.out.println(ItemName + "*****" + ItemValue + "****");
		 * autoauditstatus = "0"; autoauditdescription4 = "平均日耗电量大于上次日均量" +
		 * ItemValue + "%（本次交费日均耗电量大于最后一次交费的日均耗电量百分比）！"; auditfee4 = "0"; }
		 * String edsql =
		 * "select z.edhdl,d.beilv  from zhandian z,dianbiao d where z.id=d.zdid and d.dbid='"
		 * + formBean.getAmmeteridFk() + "'"; ResultSet rs = null; String edhdl
		 * = "0"; try { db.connectDb(); rs = db.queryAll(edsql); if (rs.next())
		 * { edhdl = (null == rs.getString(1) ? "0" : rs .getString(1)); beilv =
		 * (null == rs.getString(2) ? "1" : rs .getString(2)); } } catch
		 * (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } CTime time = new CTime(); long ts =
		 * time.getQuot(formBean.getLastdatetime(), formBean
		 * .getThisdatetime()); Double hdl = Double.parseDouble(edhdl) * ts;
		 * Double bchdl = Double.parseDouble(formBean.getBlhdl()); if
		 * (ItemName.equals("AuditDegree6")) {
		 * 
		 * if (bchdl < hdl * (1 - Double.parseDouble(ItemValue) / 100) || bchdl
		 * > hdl (1 + Double.parseDouble(ItemValue) / 100)) { flag = 0;
		 * autoauditstatus = "0"; autoauditdescription = autoauditdescription +
		 * "本次电量上下浮动超过站点额定耗电量计算值的" + ItemValue + "%" + ";"; } }
		 * 
		 * } if (auditfee1.equals("0")) { autoauditdescription =
		 * autoauditdescription + autoauditdescription1 + ";"; } if
		 * (auditfee2.equals("0")) { autoauditdescription = autoauditdescription
		 * + autoauditdescription2 + ";"; } if (auditfee3.equals("0")) {
		 * autoauditdescription = autoauditdescription + autoauditdescription3 +
		 * ";"; } if (auditfee4.equals("0")) { autoauditdescription =
		 * autoauditdescription + autoauditdescription4 + ";"; }
		 * 
		 * } formBean.setAutoauditstatus(autoauditstatus);
		 * formBean.setAutoauditdescription(autoauditdescription); // 自动审核判断
		 */

		String start = formBean.getStartmonth();
		String end = formBean.getEndmonth();
		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int) (100 + Math.random()
				* (999 - 100 + 1)));
		String uuid = uuid2 + Long.toString(uuid1).substring(3);

		String s = "sysdate";
		try {
			db.connectDb();

			String delete = "delete from ammeterdegrees where uuid="
					+ "(select b.uuid from ammeterdegrees b where b.ammeterdegreeid='"
					+ id + "')";

			
			String ssl = "";
			// 2014-7-21 管理表结算周期
			long glzq = new ElectricTool().getQuot(formBean.getLastdatetime(),
					formBean.getThisdatetime()) + 1;

			ssl = "INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,"
					+ "THISDATETIME,FLOATDEGREE,ACTUALDEGREE,INPUTOPERATOR,"
					+ "INPUTDATETIME,STARTMONTH,ENDMONTH,MEMO,"
					+ "AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,AMUUID,UUID,"
					+ "FEESBZ,BLHDL,flag,entrypensonnel,entrytime,dlbzw,MANUALAUDITSTATUS,GLZQ,CSDB,HBZQ,BZZ,SCB,DBYDL,BEILV) VALUES('"
					+ formBean.getAmmeteridFk()
					+ "','"
					+ formBean.getLastdegree()
					+ "','"
					+ formBean.getThisdegree()
					+ "',TO_DATE('"
					+ formBean.getLastdatetime()
					+ "','yyyy-mm-dd'),"
					+ "TO_DATE('"
					+ formBean.getThisdatetime()
					+ "','yyyy-mm-dd'),'"
					+ formBean.getFloatdegree()
					+ "','"
					+ formBean.getActualdegree()
					+ "','"
					+ formBean.getInputoperator()
					+ "',"
					+ "TO_DATE('"
					+ formBean.getInputdatetime()
					+ "','yyyy-mm-dd'),TO_DATE('"
					+ start
					+ "','yyyy-mm'),TO_DATE('"
					+ end
					+ "','yyyy-mm'),'"
					+ formBean.getMemo()
					+ "',"
					+ "'1','"
					+ " "
					+ "','"
					+ idStr
					+ "','"
					+ uuid
					+ "',"
					+ "'0',"
					+ formBean.getBlhdl()
					+ ",'"
					+ flag
					+ "','"
					+ formBean.getEntrypensonnel()
					+ "',"
					+ s
					+ ",'1','0',"
					+ glzq
					+ ","
					+ formBean.getScbbl()
					+ ","
					+ formBean.getHbzq()
					+ ","
					+ formBean.getBzz()
					+ ","
					+ formBean.getScb()
					+ ","
					+ formBean.getDbydl()
					+ ","
					+ formBean.getBeilv() + ")";
			// sql.append(" VALUES('" + formBean.getAmmeteridFk() + "'");
			// sql.append(",'" + formBean.getLastdegree() + "','"
			// + formBean.getThisdegree() + "','"
			// + formBean.getLastdatetime() + "','"
			// + formBean.getThisdatetime() + "','" +formBean.getFloatdegree()
			// + "'");
			// sql.append(",'" + dianduydl.get(i) + "','"
			// + formBean.getInputoperator() + "','"
			// + formBean.getInputdatetime() + "'" + ",'"
			// + year_month.get(i) + "','" + year_month.get(i) + "','"
			// + formBean.getMemo() + "'," + "'1'" + ",'"+ " " + "','" + idStr +
			// "','" + uuid
			// + "','0','"
			// + Double.parseDouble(diandu.get(i).toString()) + "','"
			// + flag + "','" + formBean.getEntrypensonnel() + "',"
			// + s + ",'1','0')");
			System.out.println("修改电量：" + ssl.toString());
			// sqlBatch[i] = sql.toString();
			// System.out.println("--------"+formBean.getAmmeterdegreeid());
			// String sql = " UPDATE ammeterdegrees SET AMMETERID_FK='" +
			// formBean.getAmmeteridFk() + "'," +
			// "LASTDEGREE='" + formBean.getLastdegree() + "'," +
			// "THISDEGREE='" + formBean.getThisdegree() + "'," +
			// "LASTDATETIME='" + formBean.getLastdatetime() + "'," +
			// "THISDATETIME='" + formBean.getThisdatetime()+ "'," +
			// "FLOATDEGREE='" + formBean.getFloatdegree() + "'," +
			// "ACTUALDEGREE='" + formBean.getBlhdl() + "'," +
			// "INPUTOPERATOR='" + formBean.getInputoperator() + "'," +
			// "INPUTDATETIME='" + formBean.getInputdatetime() + "'," +
			// "STARTMONTH='" + formBean.getStartmonth() + "'," +
			// "ENDMONTH='" + formBean.getEndmonth() + "'," +
			// "MEMO='" + formBean.getMemo() + "'," +
			// "AUTOAUDITSTATUS='" + formBean.getAutoauditstatus() + "'," +
			// "AUTOAUDITDESCRIPTION='" + formBean.getAutoauditdescription() +
			// "'" +
			// " WHERE AMMETERDEGREEID="+formBean.getAmmeterdegreeid();
			// //修改电量信息表

			msg = "修改电量信息失败！";
			db.update(delete);
			db.update(ssl);
			msg = "修改电量信息成功！";
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	/**
	 * 修改电费单电量信息
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String modify(AmmeterDegreeFormBean formBean,
			String uuid, String id) {
		String msg = "修改电量信息失败！";
		DataBase db = new DataBase();
		int flag = 1;

		String bbll = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt;
		String lastadtime = "";
		try {
			dt = sdf.parse(formBean.getLastdatetime());
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			rightNow.add(Calendar.DAY_OF_YEAR, -1);// 日期加1天
			Date dt1 = rightNow.getTime();
			lastadtime = sdf.format(dt1);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// 自动审核判断
		// 计算这次与上次日耗电量比值
		long lastdaydegree = formBean.getLastDayAmmeterDegree(formBean
				.getAmmeteridFk(), lastadtime);
		// System.out.println(lastdaydegree);
		// 计算本次日耗电量
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		// 获得两个时间的毫秒时间差异
		long diff, day;
		double thisdaydegree, daydegereerate = 0;
		try {
			// System.out.println("222222:"+sd.parse(formBean.getThisdatetime()).getTime());
			diff = sd.parse(formBean.getThisdatetime()).getTime()
					- sd.parse(formBean.getLastdatetime()).getTime();
			day = diff / nd;// 计算差多少天
			thisdaydegree = Double.parseDouble(formBean.getBlhdl()) / day;// 本次日耗电量
			daydegereerate = ((thisdaydegree - lastdaydegree) / lastdaydegree) * 100;// 这次与上次日耗电量比值
			// System.out.println("222222:"+thisdaydegree+"****"+lastdaydegree+"*****"+daydegereerate);
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		AutoAuditBean bean = new AutoAuditBean();
		ArrayList fylist = new ArrayList();
		fylist = bean.getPageData(1, "");
		String ItemID = "", beilv = "1", ItemName = "", ItemValue = "", ItemDescription = "";
		String autoauditstatus = "1", autoauditdescription = "", autoauditdescription1 = "", autoauditdescription2 = "", autoauditdescription3 = "", autoauditdescription4 = "", autoauditdescription5 = "";
		String auditfee1 = "";
		String auditfee2 = "";
		String auditfee3 = "";
		String auditfee4 = "";
		String auditfee5 = "";

		String auditfee6 = "";
		String auditfee7 = "";
		String auditfee8 = "";
		String auditfee9 = "";
		String auditfee10 = "";

		String auditfee11 = "";
		String auditfee12 = "";
		String auditfee13 = "";
		String auditfee14 = "";
		String auditfee15 = "";

		String auditfee16 = "";
		String auditfee17 = "";
		String auditfee18 = "";
		String auditfee19 = "";
		String auditfee20 = "";

		String auditfee21 = "";
		String auditfee22 = "";
		String auditfee23 = "";
		String auditfee24 = "";
		String auditfee25 = "";

		String auditfee26 = "";
		String auditfee27 = "";
		String auditfee28 = "";
		String auditfee29 = "";

		String ad2_bz = formBean.getAd2_bz();
		System.out.println(ad2_bz);
		String floatdegree = formBean.getFloatdegree();
		String memo = formBean.getMemo();
		String ad1_bz = "0";
		String floatdegree_bz = "";
		String memo_bz = "";
		System.out.println(floatdegree + "*****" + memo + "****");
		double floatt = 0.0;
		if (null != floatdegree && !floatdegree.equals("")
				&& !floatdegree.equals(" ")) {
			floatt = Double.parseDouble(floatdegree);
		} else {
			floatdegree_bz = "0";
		}
		if (floatt == 0.0) {
			floatdegree_bz = "0";
		} else {
			floatdegree_bz = "1";
		}
		if (null != memo && !memo.equals("") && !memo.equals(" ")) {
			memo_bz = "1";
		} else {
			memo_bz = "0";
		}
		if (memo_bz.equals(floatdegree_bz)
				|| (floatdegree_bz.equals("0") && memo_bz.equals("1"))) {
			ad1_bz = "1";
		}

		/*
		 * if (floatdegree == null || floatdegree == "" ||
		 * floatdegree.equals("0") || floatdegree.equals("0.0") ||
		 * floatdegree.equals("0.00")) { floatdegree_bz = "0"; } else {
		 * 
		 * floatdegree_bz = "1"; } if (memo == null || memo == "") { memo_bz =
		 * "0"; } else { memo_bz = "1"; } if (memo_bz.equals(floatdegree_bz)) {
		 * ad1_bz = "1"; }
		 */
		// System.out.println(floatdegree_bz+"*****"+memo_bz+"****"+ad1_bz);
		int bzw1 = 0;
		if (fylist != null) {
			int fycount = ((Integer) fylist.get(0)).intValue();
			for (int k = fycount; k < fylist.size() - 1; k += fycount) {
				bzw1++;
				ItemID = (String) fylist.get(k + fylist.indexOf("ITEMID"));
				ItemName = (String) fylist.get(k + fylist.indexOf("ITEMNAME"));
				ItemValue = (String) fylist
						.get(k + fylist.indexOf("ITEMVALUE"));
				ItemDescription = (String) fylist.get(k
						+ fylist.indexOf("ITEMDESCRIPTION"));

				if (ItemName.equals("AuditDegree1") && (!ItemValue.equals("0"))
						&& ad1_bz.equals("0")) {
					// System.out.println(ItemName+"*****"+ItemValue+"****");
					autoauditstatus = "0";
					autoauditdescription1 = "用电调整没有说明！";
					auditfee1 = "0";
				}
				if (ItemName.equals("AuditDegree2") && (!ItemValue.equals("0"))
						&& ad2_bz.equals("1")) {
					// System.out.println(ItemName+"*****"+ItemValue+"****");
					autoauditstatus = "0";
					autoauditdescription2 = "上次电表读数与最后抄表数不一致！";
					auditfee2 = "0";
				}

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				int result = 0;
				try {
					result = (format.parse(formBean.getLastdatetime()
							.toString())).compareTo(format.parse(formBean
							.getThisdatetime().toString()));
					// System.out.println("****"+result);
				} catch (ParseException e) {

					e.printStackTrace();
				}
				/*
				 * if (ItemName.equals("AuditDegree3") &&
				 * (!ItemValue.equals("0")) && (result > 0)) {
				 * System.out.println(ItemName + "*****" + ItemValue + "****" +
				 * result); autoauditstatus = "0"; autoauditdescription3 =
				 * "抄表时间小于本次抄表时间！"; auditfee3 = "0"; }
				 */
				if (ItemName.equals("AuditDegree4") && (!ItemValue.equals("0"))
						&& (daydegereerate > Double.parseDouble(ItemValue))) {
					System.out.println(ItemName + "*****" + ItemValue + "****");
					autoauditstatus = "0";
					autoauditdescription4 = "平均日耗电量大于上次日均量" + ItemValue
							+ "%（本次交费日均耗电量大于最后一次交费的日均耗电量百分比）！";
					auditfee4 = "0";
				}

				String edsql = "select z.edhdl,d.beilv,z.qsdbdl,z.property,z.ktxs,z.shi,z.zlfh,z.jlfh,z.jcxs,z.fwxs,z.yfxs,z.yflx from zhandian z,dianbiao d where z.id=d.zdid and d.dbid='"
						+ formBean.getAmmeteridFk() + "'";
				ResultSet rs = null;
				String edhdl = "0";
				String qsdbdl = "0";
				String property = "";
				String ktxs = "";
				String shi = "";
				String zlfh = "";
				String jlfh = "";
				String jcxs = "";
				String yfxs = "";
				String fwxs = "";
				String fwlx = "";
				try {
					DataBase db1 = new DataBase();
					db1.connectDb();
					rs = db1.queryAll(edsql);
					System.out.println("edsql:" + edsql);
					if (rs.next()) {
						edhdl = (null == rs.getString(1) ? "0" : rs
								.getString(1));
						beilv = (null == rs.getString(2) ? "1" : rs
								.getString(2));
						qsdbdl = (null == rs.getString(3) ? "0" : rs
								.getString(3));
						if ("".equals(qsdbdl)) {

							qsdbdl = "0";
						}
						property = (null == rs.getString(4) ? "" : rs
								.getString(4));
						ktxs = (null == rs.getString(5) ? "0" : rs.getString(5));
						if ("".equals(rs.getString(5))) {

							ktxs = "0";
						}
						System.out.println("ktxs开始:" + ktxs);
						shi = (null == rs.getString(6) ? "" : rs.getString(6));
						zlfh = (null == rs.getString(7) ? "0" : rs.getString(7));

						if ("".equals(zlfh)) {

							zlfh = "0";
						}
						jlfh = (null == rs.getString(8) ? "0" : rs.getString(8));
						if ("".equals(jlfh)) {

							jlfh = "0";
						}
						jcxs = (null == rs.getString(9) ? "0" : rs.getString(9));
						fwxs = (null == rs.getString(10) ? "0" : rs
								.getString(10));
						yfxs = (null == rs.getString(11) ? "0" : rs
								.getString(11));
						if ("".equals(yfxs)) {

							yfxs = "0";
						}
						fwlx = (null == rs.getString(12) ? "" : rs
								.getString(12));
						if ("".equals(fwlx)) {

							fwlx = "";
						}
					}
					db1.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 通过结束月份取值

				String endm = formBean.getEndmonth();
				String endmo = endm.substring(5, 7);
				String yymonth = "";
				Double yf = null;
				if (endmo.equals("01")) {
					yymonth = "YMONTH";
				}
				if (endmo.equals("02")) {
					yymonth = "EMONTH";
				}
				if (endmo.equals("03")) {
					yymonth = "SMONTH";
				}
				if (endmo.equals("04")) {
					yymonth = "SIMONTH";
				}
				if (endmo.equals("05")) {
					yymonth = "WMONTH";
				}
				if (endmo.equals("06")) {
					yymonth = "LMONTH";
				}
				if (endmo.equals("07")) {
					yymonth = "QMONTH";
				}
				if (endmo.equals("08")) {
					yymonth = "BMONTH";
				}
				if (endmo.equals("09")) {
					yymonth = "JMONTH";
				}
				if (endmo.equals("10")) {
					yymonth = "SHIMONTH";
				}
				if (endmo.equals("11")) {
					yymonth = "SYMONTH";
				}
				if (endmo.equals("12")) {
					yymonth = "SEMONTH";
				}
				String sqlyf = "select y." + yymonth
						+ " from zhandian zd, yfxs y where zd.shi=y.shicode";
				System.out.println("月份" + sqlyf.toString());
				ResultSet rsy = null;
				DataBase dbb = new DataBase();
				try {
					dbb.connectDb();
					rsy = dbb.queryAll(sqlyf);
					if (rsy.next()) {
						yf = Double.parseDouble(rsy.getString(1));
					}
					dbb.close();
				} catch (DbException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String sss = "SELECT K.ID,K.KSZLFH,K.JSZLSH,K.JZKTXS,K.JRWKTXS,K.XZZJKTXS,K.JYJFKTXS,K.QTKTXS,K.IDCJFKTXS,K.SJBZW FROM KTXS K ";
				ResultSet r = null;
				String id1 = "";
				String kszlfh = "", kszlfh1 = "", kszlfh2 = "", kszlfh3 = "", kszlfh4 = "", kszlfh5 = "", kszlfh6 = "";
				String jszlfh = "", jszlfh1 = "", jszlfh2 = "", jszlfh3 = "", jszlfh4 = "", jszlfh5 = "", jszlfh6 = "";
				String jz = "", jz1 = "", jz2 = "", jz3 = "", jz4 = "", jz5 = "", jz6 = "";
				String jrw = "", jrw1 = "", jrw2 = "", jrw3 = "", jrw4 = "", jrw5 = "", jrw6 = "";
				String xzzj = "", xzzj1 = "", xzzj2 = "", xzzj3 = "", xzzj4 = "", xzzj5 = "", xzzj6 = "";
				String jyjf = "", jyjf1 = "", jyjf2 = "", jyjf3 = "", jyjf4 = "", jyjf5 = "", jyjf6 = "";
				String qt = "", qt1 = "", qt2 = "", qt3 = "", qt4 = "", qt5 = "", qt6 = "";
				String idcjf = "", idcjf1 = "", idcjf2 = "", idcjf3 = "", idcjf4 = "", idcjf5 = "", idcjf6 = "";
				DataBase d = new DataBase();

				try {
					d.connectDb();
					r = d.queryAll(sss);
					while (r.next()) {
						id1 = r.getString(1);
						kszlfh = r.getString(2);
						jszlfh = r.getString(3);
						jz = r.getString(4);
						jrw = r.getString(5);
						xzzj = r.getString(6);
						jyjf = r.getString(7);
						qt = r.getString(8);
						idcjf = r.getString(9);

						if (id1.equals("1")) {
							kszlfh1 = kszlfh;
							jszlfh1 = jszlfh;
							jz1 = jz;
							jrw1 = jrw;
							xzzj1 = xzzj;
							jyjf1 = jyjf;
							qt1 = qt;
							idcjf1 = idcjf;
						} else if (id1.equals("2")) {
							kszlfh2 = kszlfh;
							jszlfh2 = jszlfh;
							jz2 = jz;
							jrw2 = jrw;
							xzzj2 = xzzj;
							jyjf2 = jyjf;
							qt2 = qt;
							idcjf2 = idcjf;
						} else if (id1.equals("3")) {
							kszlfh3 = kszlfh;
							jszlfh3 = jszlfh;
							jz3 = jz;
							jrw3 = jrw;
							xzzj3 = xzzj;
							jyjf3 = jyjf;
							qt3 = qt;
							idcjf3 = idcjf;
						} else if (id1.equals("4")) {
							kszlfh4 = kszlfh;
							jszlfh4 = jszlfh;
							jz4 = jz;
							jrw4 = jrw;
							xzzj4 = xzzj;
							jyjf4 = jyjf;
							qt4 = qt;
							idcjf4 = idcjf;
						} else if (id1.equals("5")) {
							kszlfh5 = kszlfh;
							jszlfh5 = jszlfh;
							jz5 = jz;
							jrw5 = jrw;
							xzzj5 = xzzj;
							jyjf5 = jyjf;
							qt5 = qt;
							idcjf5 = idcjf;
						} else if (id1.equals("6")) {
							kszlfh6 = kszlfh;
							jz6 = jz;
							jrw6 = jrw;
							xzzj6 = xzzj;
							jyjf6 = jyjf;
							qt6 = qt;
							idcjf6 = idcjf;
						}

					}

					d.close();
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String jcdxsql = "select f.id,f.jcxs from fwxs f ";
				ResultSet jr = null;
				String idi = "", jcxsr = "", jcxsr1 = "", jcxsr2 = "", jcxsr3 = "";
				DataBase dbs = new DataBase();

				try {
					dbs.connectDb();
					jr = dbs.queryAll(jcdxsql);
					try {
						while (jr.next()) {
							idi = jr.getString(1);
							jcxsr = jr.getString(2);
							if (idi.equals("1")) {
								jcxsr1 = jcxsr;
							}
							if (idi.equals("2")) {
								jcxsr2 = jcxsr;
							}
							if (idi.equals("3")) {
								jcxsr3 = jcxsr;
							}
						}
						dbs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				CTime time = new CTime();
				long ts = time.getQuot(formBean.getLastdatetime(), formBean
						.getThisdatetime()) + 1;

				// 本地定标
				Double hdl = Double.parseDouble(edhdl) * ts;
				// 省定标
				Double qsdbdll = Double.parseDouble(qsdbdl) * ts;

				Double ktxss = Double.parseDouble(ktxs);
				Double bchdl = Double.parseDouble(formBean.getBlhdl());
				DecimalFormat fomcate = new DecimalFormat("0.00");
				bbll = fomcate.format((bchdl - hdl) / hdl * 100);
				Double zlfhh = Double.parseDouble(zlfh);
				Double jlfhh = Double.parseDouble(jlfh);
				Double jcxss = Double.parseDouble(jcxs);
				Double fwxss = Double.parseDouble(fwxs);
				Double yfxss = Double.parseDouble(yfxs);
				System.out.println("没进来");
				System.out.println("qsdbdl:" + qsdbdl);
				System.out.println("ts:" + ts);
				System.out.println("bchdl:" + bchdl + "qsdbdll:" + qsdbdll);
				if (bzw1 == 9) {
					System.out.println("进来了");
					if (!fomcate.format(qsdbdll).equals("0.00")) {
						if ((bchdl < qsdbdll * (1 - 0.1) || bchdl > qsdbdll
								* (1 + 0.1))) {
							System.out.println("qsdbdl:" + qsdbdl);
							System.out.println("ts:" + ts);
							System.out.println("bchdl:" + bchdl + "qsdbdll:"
									+ qsdbdll);
							flag = 0;
							autoauditstatus = "0";
							autoauditdescription5 = autoauditdescription5
									+ "本次电量上下浮动超过全省定标的10%";
							auditfee5 = "0";
						}
					} else if (fomcate.format(qsdbdll).equals("0.00")
							&& property.equals("zdsx02")) {
						if (hdl != 0) {
							if ((bchdl < hdl * 0.9
									* (jcxss + yfxss * ktxss + fwxss)
									* (1 - 0.1) || bchdl > hdl * 0.9
									* (jcxss + yfxss * ktxss + fwxss)
									* (1 + 0.1))) {
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee21 = "0";
							}
						} else {
							if (zlfhh != 0) {
								if ((bchdl < zlfhh * 0.9
										* (jcxss + yfxss * ktxss + fwxss)
										* (1 - 0.1) || bchdl > zlfhh * 0.9
										* (jcxss + yfxss * ktxss + fwxss)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee22 = "0";
								}
							} else {
								if ((bchdl < jlfhh * 0.9
										* (jcxss + yfxss * ktxss + fwxss)
										* (1 - 0.1) || bchdl > jlfhh * 0.9
										* (jcxss + yfxss * ktxss + fwxss)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee23 = "0";
								}
							}

						}
					} else if (fomcate.format(qsdbdll).equals("0.00")
							&& property.equals("zdsx05")) {
						Double jrwktxs = null;
						if (zlfhh >= Double.parseDouble(kszlfh1)
								&& zlfhh < Double.parseDouble(kszlfh2)) {
							jrwktxs = Double.parseDouble(jrw1);
						} else if (zlfhh >= Double.parseDouble(kszlfh2)
								&& zlfhh < Double.parseDouble(kszlfh3)) {
							jrwktxs = Double.parseDouble(jrw2);
						} else if (zlfhh >= Double.parseDouble(kszlfh3)
								&& zlfhh < Double.parseDouble(kszlfh4)) {
							jrwktxs = Double.parseDouble(jrw3);
						} else if (zlfhh >= Double.parseDouble(kszlfh4)
								&& zlfhh < Double.parseDouble(kszlfh5)) {
							jrwktxs = Double.parseDouble(jrw4);
						} else if (zlfhh >= Double.parseDouble(kszlfh5)
								&& zlfhh < Double.parseDouble(kszlfh6)) {
							jrwktxs = Double.parseDouble(jrw5);
						} else if (zlfhh >= Double.parseDouble(kszlfh6)) {
							jrwktxs = Double.parseDouble(jrw6);
						}
						Double jcxsjc = null;
						if (fwlx.equals("fwlx01")) {
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if (fwlx.equals("fwlx02")) {
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if (fwlx.equals("fwlx03")) {
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						System.out.println("jcxs:__________" + jcxsjc);
						System.out.println("jrwktxs:__________" + jrwktxs);
						if (hdl != 0) {

							if ((bchdl < hdl * 0.9 * (jcxsjc + yf * jrwktxs)
									* (1 - 0.1) || bchdl > hdl * 0.9
									* (jcxsjc + yf * jrwktxs) * (1 + 0.1))) {
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee6 = "0";
							}
						} else {
							if (zlfhh != 0) {
								if ((bchdl < zlfhh * 0.9
										* (jcxsjc + yf * jrwktxs) * (1 - 0.1) || bchdl > zlfhh
										* 0.9
										* (jcxsjc + yf * jrwktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee11 = "0";
								}
							} else {
								if ((bchdl < jlfhh * 0.9
										* (jcxsjc + yf * jrwktxs) * (1 - 0.1) || bchdl > jlfhh
										* 0.9
										* (jcxsjc + yf * jrwktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee12 = "0";
								}

							}

						}
					} else if (property.equals("zdsx01")
							&& qsdbdll.toString().equals("0.00")) {
						Double jyjfktxs = null;
						if (zlfhh >= Double.parseDouble(kszlfh1)
								&& zlfhh < Double.parseDouble(kszlfh2)) {
							jyjfktxs = Double.parseDouble(jyjf1);
						} else if (zlfhh >= Double.parseDouble(kszlfh2)
								&& zlfhh < Double.parseDouble(kszlfh3)) {
							jyjfktxs = Double.parseDouble(jyjf2);
						} else if (zlfhh >= Double.parseDouble(kszlfh3)
								&& zlfhh < Double.parseDouble(kszlfh4)) {
							jyjfktxs = Double.parseDouble(jyjf3);
						} else if (zlfhh >= Double.parseDouble(kszlfh4)
								&& zlfhh < Double.parseDouble(kszlfh5)) {
							jyjfktxs = Double.parseDouble(jyjf4);
						} else if (zlfhh >= Double.parseDouble(kszlfh5)
								&& zlfhh < Double.parseDouble(kszlfh6)) {
							jyjfktxs = Double.parseDouble(jyjf5);
						} else if (zlfhh >= Double.parseDouble(kszlfh6)) {
							jyjfktxs = Double.parseDouble(jyjf6);
						}
						Double jcxsjc = null;
						if (fwlx.equals("fwlx01")) {
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if (fwlx.equals("fwlx02")) {
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if (fwlx.equals("fwlx03")) {
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						if (hdl != 0) {
							if (shi == "13701" || shi == "13702"
									|| shi == "13705") {
								if ((bchdl < hdl * 0.75
										* (jcxsjc + yf * jyjfktxs) * (1 - 0.1) || bchdl > hdl
										* 0.75
										* (jcxsjc + yf * jyjfktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee7 = "0";
								}
							} else if (shi == "13710" || shi == "13717") {
								if ((bchdl < hdl * 0.9
										* (jcxsjc + yf * jyjfktxs) * (1 - 0.1) || bchdl > hdl
										* 0.9
										* (jcxsjc + yf * jyjfktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee8 = "0";

								}
							} else if (shi == "13704") {
								if ((bchdl < hdl * 1 * (jcxsjc + yf * jyjfktxs)
										* (1 - 0.1) || bchdl > hdl * 1
										* (jcxsjc + yf * jyjfktxs) * (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee9 = "0";

								}
							} else {
								if ((bchdl < hdl * 0.85
										* (jcxsjc + yf * jyjfktxs) * (1 - 0.1) || bchdl > hdl
										* 0.85
										* (jcxsjc + yf * jyjfktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee10 = "0";

								}
							}
						} else {
							if (zlfhh != 0) {
								if (shi == "13701" || shi == "13702"
										|| shi == "13705") {
									if ((bchdl < zlfhh * 0.75
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > zlfhh * 0.75
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee13 = "0";
									}
								} else if (shi == "13710" || shi == "13717") {
									if ((bchdl < zlfhh * 0.9
											* (1 + yf * jyjfktxs)
											* (jcxsjc - 0.1) || bchdl > zlfhh
											* 0.9 * (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee14 = "0";

									}
								} else if (shi == "13704") {
									if ((bchdl < zlfhh * 1
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > zlfhh * 1
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee15 = "0";

									}
								} else {
									if ((bchdl < zlfhh * 0.85
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > zlfhh * 0.85
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee16 = "0";

									}
								}
							} else {
								if (shi == "13701" || shi == "13702"
										|| shi == "13705") {
									if ((bchdl < jlfhh * 0.75
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > jlfhh * 0.75
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee17 = "0";
									}
								} else if (shi == "13710" || shi == "13717") {
									if ((bchdl < jlfhh * 0.9
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > jlfhh * 0.9
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee18 = "0";

									}
								} else if (shi == "13704") {
									if ((bchdl < jlfhh * 1
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > jlfhh * 1
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee19 = "0";

									}
								} else {
									if ((bchdl < jlfhh * 0.85
											* (jcxsjc + yf * jyjfktxs)
											* (1 - 0.1) || bchdl > jlfhh * 0.85
											* (jcxsjc + yf * jyjfktxs)
											* (1 + 0.1))) {
										flag = 0;
										autoauditstatus = "0";
										autoauditdescription5 = autoauditdescription5
												+ "本次电量上下浮动超过全省定标的10%";
										auditfee20 = "0";

									}
								}
							}
						}
					} else if (property.equals("zdsx06")
							&& qsdbdll.toString().equals("0.00")) {
						Double xzzjktxs = null;
						if (zlfhh >= Double.parseDouble(kszlfh1)
								&& zlfhh < Double.parseDouble(kszlfh2)) {
							xzzjktxs = Double.parseDouble(xzzj1);
						} else if (zlfhh >= Double.parseDouble(kszlfh2)
								&& zlfhh < Double.parseDouble(kszlfh3)) {
							xzzjktxs = Double.parseDouble(xzzj2);
						} else if (zlfhh >= Double.parseDouble(kszlfh3)
								&& zlfhh < Double.parseDouble(kszlfh4)) {
							xzzjktxs = Double.parseDouble(xzzj3);
						} else if (zlfhh >= Double.parseDouble(kszlfh4)
								&& zlfhh < Double.parseDouble(kszlfh5)) {
							xzzjktxs = Double.parseDouble(xzzj4);
						} else if (zlfhh >= Double.parseDouble(kszlfh5)
								&& zlfhh < Double.parseDouble(kszlfh6)) {
							xzzjktxs = Double.parseDouble(xzzj5);
						} else if (zlfhh >= Double.parseDouble(kszlfh6)) {
							xzzjktxs = Double.parseDouble(xzzj6);
						}
						Double jcxsjc = null;
						if (fwlx.equals("fwlx01")) {
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if (fwlx.equals("fwlx02")) {
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if (fwlx.equals("fwlx03")) {
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						if (hdl != 0) {
							if ((bchdl < hdl * 0.9 * (jcxsjc + yf * xzzjktxs)
									* (1 - 0.1) || bchdl > hdl * 0.9
									* (jcxsjc + yf * xzzjktxs) * (1 + 0.1))) {
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee21 = "0";
							}
						} else {
							if (zlfhh != 0) {
								if ((bchdl < zlfhh * 0.9
										* (jcxsjc + yf * xzzjktxs) * (1 - 0.1) || bchdl > zlfhh
										* 0.9
										* (jcxsjc + yf * xzzjktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee22 = "0";
								}
							} else {
								if ((bchdl < jlfhh * 0.9
										* (jcxsjc + yf * xzzjktxs) * (1 - 0.1) || bchdl > jlfhh
										* 0.9
										* (jcxsjc + yf * xzzjktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee23 = "0";
								}

							}

						}
					} else if (property.equals("zdsx04")
							&& qsdbdll.toString().equals("0.00")) {
						Double qtktxs = null;
						if (zlfhh >= Double.parseDouble(kszlfh1)
								&& zlfhh < Double.parseDouble(kszlfh2)) {
							qtktxs = Double.parseDouble(qt1);
						} else if (zlfhh >= Double.parseDouble(kszlfh2)
								&& zlfhh < Double.parseDouble(kszlfh3)) {
							qtktxs = Double.parseDouble(qt2);
						} else if (zlfhh >= Double.parseDouble(kszlfh3)
								&& zlfhh < Double.parseDouble(kszlfh4)) {
							qtktxs = Double.parseDouble(qt3);
						} else if (zlfhh >= Double.parseDouble(kszlfh4)
								&& zlfhh < Double.parseDouble(kszlfh5)) {
							qtktxs = Double.parseDouble(qt4);
						} else if (zlfhh >= Double.parseDouble(kszlfh5)
								&& zlfhh < Double.parseDouble(kszlfh6)) {
							qtktxs = Double.parseDouble(qt5);
						} else if (zlfhh >= Double.parseDouble(kszlfh6)) {
							qtktxs = Double.parseDouble(qt6);
						}
						Double jcxsjc = null;
						if (fwlx.equals("fwlx01")) {
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if (fwlx.equals("fwlx02")) {
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if (fwlx.equals("fwlx03")) {
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						if (hdl != 0) {
							if ((bchdl < hdl * 0.9 * (jcxsjc + yf * qtktxs)
									* (1 - 0.1) || bchdl > hdl * 0.9
									* (jcxsjc + yf * qtktxs) * (1 + 0.1))) {
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee24 = "0";
							}
						} else {
							if (zlfhh != 0) {
								if ((bchdl < zlfhh * 0.9
										* (jcxsjc + yf * qtktxs) * (1 - 0.1) || bchdl > zlfhh
										* 0.9
										* (jcxsjc + yf * qtktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee25 = "0";
								}
							} else {
								if ((bchdl < jlfhh * 0.9
										* (jcxsjc + yf * qtktxs) * (1 - 0.1) || bchdl > jlfhh
										* 0.9
										* (jcxsjc + yf * qtktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee26 = "0";
								}

							}

						}
					} else if (property.equals("zdsx07")
							&& qsdbdll.toString().equals("0.00")) {
						Double idcjfktxs = null;
						if (zlfhh >= Double.parseDouble(kszlfh1)
								&& zlfhh < Double.parseDouble(kszlfh2)) {
							idcjfktxs = Double.parseDouble(idcjf1);
						} else if (zlfhh >= Double.parseDouble(kszlfh2)
								&& zlfhh < Double.parseDouble(kszlfh3)) {
							idcjfktxs = Double.parseDouble(idcjf2);
						} else if (zlfhh >= Double.parseDouble(kszlfh3)
								&& zlfhh < Double.parseDouble(kszlfh4)) {
							idcjfktxs = Double.parseDouble(idcjf3);
						} else if (zlfhh >= Double.parseDouble(kszlfh4)
								&& zlfhh < Double.parseDouble(kszlfh5)) {
							idcjfktxs = Double.parseDouble(idcjf4);
						} else if (zlfhh >= Double.parseDouble(kszlfh5)
								&& zlfhh < Double.parseDouble(kszlfh6)) {
							idcjfktxs = Double.parseDouble(idcjf5);
						} else if (zlfhh >= Double.parseDouble(kszlfh6)) {
							idcjfktxs = Double.parseDouble(idcjf6);
						}
						Double jcxsjc = null;
						if (fwlx.equals("fwlx01")) {
							jcxsjc = Double.parseDouble(jcxsr1);
						}
						if (fwlx.equals("fwlx02")) {
							jcxsjc = Double.parseDouble(jcxsr2);
						}
						if (fwlx.equals("fwlx03")) {
							jcxsjc = Double.parseDouble(jcxsr3);
						}
						if (hdl != 0) {
							if ((bchdl < hdl * 0.9 * (jcxsjc + yf * idcjfktxs)
									* (1 - 0.1) || bchdl > hdl * 0.9
									* (jcxsjc + yf * idcjfktxs) * (1 + 0.1))) {
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "本次电量上下浮动超过全省定标的10%";
								auditfee27 = "0";
							}
						} else {
							if (zlfhh != 0) {
								if ((bchdl < zlfhh * 0.9
										* (jcxsjc + yf * idcjfktxs) * (1 - 0.1) || bchdl > zlfhh
										* 0.9
										* (jcxsjc + yf * idcjfktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee28 = "0";
								}
							} else {
								if ((bchdl < jlfhh * 0.9
										* (jcxsjc + yf * idcjfktxs) * (1 - 0.1) || bchdl > jlfhh
										* 0.9
										* (jcxsjc + yf * idcjfktxs)
										* (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "本次电量上下浮动超过全省定标的10%";
									auditfee29 = "0";
								}

							}

						}
					}

				}
				if (ItemName.equals("AuditDegree6")) {

					if (bchdl < hdl * (1 - Double.parseDouble(ItemValue) / 100)
							|| bchdl > hdl
									* (1 + Double.parseDouble(ItemValue) / 100)) {
						flag = 0;
						autoauditstatus = "0";
						autoauditdescription = autoauditdescription
								+ "本次电量上下浮动超过站点额定耗电量计算值的" + ItemValue + "%"
								+ ";";
					}
				}
			}
			if (auditfee1.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription1 + ";";
			}
			if (auditfee2.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription2 + ";";
			}
			if (auditfee3.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription3 + ";";
			}
			if (auditfee4.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription4 + ";";
			}
			if (auditfee5.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee6.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee7.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee8.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee9.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee10.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee11.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee12.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee13.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee14.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee15.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee16.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee17.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee18.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee19.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}
			if (auditfee20.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription5 + ";";
			}

		}
		formBean.setAutoauditstatus(autoauditstatus);
		formBean.setAutoauditdescription(autoauditdescription);
		// 自动审核判断

		String start = formBean.getStartmonth();
		String end = formBean.getEndmonth();
		/*
		 * int startn = Integer.parseInt(start.substring(0, 4)); int starty =
		 * Integer.parseInt(start.substring(5, 7)); int endn =
		 * Integer.parseInt(end.substring(0, 4)); int endy =
		 * Integer.parseInt(end.substring(5, 7)); int time = (endn - startn) *
		 * 12 + endy - starty + 1; System.out.println("--" + startn + "**" +
		 * starty + "**" + time); double yushu =
		 * Double.parseDouble(formBean.getBlhdl()) % time; int dianduPermonth =
		 * (int) ((Double.parseDouble(formBean.getBlhdl()) - yushu) / time);
		 * 
		 * double yu = Double.parseDouble(formBean.getActualdegree()) % time;
		 * int dian = (int) ((Double.parseDouble(formBean.getActualdegree()) -
		 * yu) / time); // 生产电量分到每个月 double scdlyushu =
		 * Double.parseDouble(formBean.getScdl()) % time; int scdl = (int)
		 * ((Double.parseDouble(formBean.getScdl()) - scdlyushu) / time); //
		 * 办公电量分到每个月 double bgdlyushu = Double.parseDouble(formBean.getBgdl()) %
		 * time; int bgdl = (int) ((Double.parseDouble(formBean.getBgdl()) -
		 * bgdlyushu) / time); // 营业电量分到每个月 double yydlyushu =
		 * Double.parseDouble(formBean.getYydl()) % time; int yydl = (int)
		 * ((Double.parseDouble(formBean.getYydl()) - yydlyushu) / time); //
		 * 信息化电量分到每个月 double xxhdlyushu =
		 * Double.parseDouble(formBean.getXxhdl()) % time; int xxhdl = (int)
		 * ((Double.parseDouble(formBean.getXxhdl()) - xxhdlyushu) / time); //
		 * 建设投资电量分到每个月 double jstzdlyushu =
		 * Double.parseDouble(formBean.getJstzdl()) % time; int jstzdl = (int)
		 * ((Double.parseDouble(formBean.getJstzdl()) - jstzdlyushu) / time); //
		 * 代垫电量分到每个月 double dddfdlyushu =
		 * Double.parseDouble(formBean.getDddfdl()) % time; int dddfdl = (int)
		 * ((Double.parseDouble(formBean.getDddfdl()) - dddfdlyushu) / time);
		 * 
		 * //long uuid = System.currentTimeMillis(); String[] sqlBatch = new
		 * String[time];
		 * 
		 * List year_month = new ArrayList(); List diandu = new ArrayList();
		 * List dianl = new ArrayList(); List scdlfentan = new ArrayList(); List
		 * bgdlfentan = new ArrayList(); List yydlfentan = new ArrayList(); List
		 * xxhdlfentan = new ArrayList(); List jstzdlfentan = new ArrayList();
		 * List dddfdlfentan = new ArrayList();// 代垫电量
		 * 
		 * for (int i = 0; i < time; i++) { String yue = String.valueOf(starty);
		 * if (yue.length() == 1) yue = "0" + yue; String year_month_tmp =
		 * startn + "-" + yue; starty++; if (starty > 12) { starty = 1;
		 * startn++; } year_month.add(year_month_tmp); if (i == time - 1) {
		 * diandu.add(dianduPermonth + yushu); dianl.add(dian + yu);
		 * scdlfentan.add(scdl + scdlyushu); bgdlfentan.add(bgdl + bgdlyushu);
		 * yydlfentan.add(yydl + yydlyushu); xxhdlfentan.add(xxhdl +
		 * xxhdlyushu); jstzdlfentan.add(jstzdl + jstzdlyushu);
		 * dddfdlfentan.add(dddfdl + dddfdlyushu);//代垫电量 } else {
		 * diandu.add(dianduPermonth); dianl.add(dian); scdlfentan.add(scdl);
		 * bgdlfentan.add(bgdl); yydlfentan.add(yydl); xxhdlfentan.add(xxhdl);
		 * jstzdlfentan.add(jstzdl); dddfdlfentan.add(dddfdl);//代垫电量 } }
		 */
		String s = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		try {
			db.connectDb();

			String delete = "delete from ammeterdegrees where uuid="
					+ "(select b.uuid from ammeterdegrees b where b.ammeterdegreeid='"
					+ id + "')";
			// ================2013-10-09===新模式
			String sqlx = "INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,"
					+ "THISDATETIME,FLOATDEGREE,ACTUALDEGREE,INPUTOPERATOR,"
					+ "STARTMONTH,ENDMONTH,MEMO,AUTOAUDITSTATUS,"
					+ "AUTOAUDITDESCRIPTION,UUID,FEESBZ,BLHDL,"
					+ "flag,entrypensonnel,entrytime,NETWORKHDL,"
					+ "ADMINISTRATIVEHDL,MARKETHDL,INFORMATIZATIONHDL,BUILDHDL,"
					+ "dlbzw,MANUALAUDITSTATUS,DEDHDL,DDDF) VALUES(" + "'"
					+ formBean.getAmmeteridFk()
					+ "','"
					+ formBean.getLastdegree()
					+ "','"
					+ formBean.getThisdegree()
					+ "',TO_DATE('"
					+ formBean.getLastdatetime()
					+ "','yyyy-mm-dd'),"
					+ "TO_DATE('"
					+ formBean.getThisdatetime()
					+ "','yyyy-mm-dd'),'"
					+ floatdegree
					+ "','"
					+ formBean.getActualdegree()
					+ "','"
					+ formBean.getInputoperator()
					+ "',"
					+ "TO_DATE('"
					+ formBean.getStartmonth()
					+ "','yyyy-mm'),TO_DATE('"
					+ formBean.getEndmonth()
					+ "','yyyy-mm'),'"
					+ formBean.getMemo()
					+ "','"
					+ autoauditstatus
					+ "',"
					+ "'"
					+ autoauditdescription
					+ "','"
					+ uuid
					+ "','1','"
					+ formBean.getBlhdl()
					+ "',"
					+ "'"
					+ flag
					+ "','"
					+ formBean.getEntrypensonnel()
					+ "',"
					+ s
					+ ",'"
					+ Double.parseDouble(formBean.getScdl())
					+ "',"
					+ "'"
					+ Double.parseDouble(formBean.getBgdl())
					+ "','"
					+ Double.parseDouble(formBean.getYydl())
					+ "','"
					+ Double.parseDouble(formBean.getXxhdl())
					+ "','"
					+ Double.parseDouble(formBean.getJstzdl())
					+ "',"
					+ "'1','0','"
					+ bbll
					+ "','"
					+ Double.parseDouble(formBean.getDddfdl()) + "')";
			// ===================2013-10-09==新模式
			System.out.println("修改电费单电量的删除sql:" + delete.toString());
			System.out.println("修改电费单电量新模式的sql：" + sqlx);

			// =====2013-10-09原始模式===============
			// for (int i = 0; i < time; i++) {
			// StringBuffer sql = new StringBuffer();
			// System.out.println("diandu:" + diandu.get(i));
			// sql
			// .append("INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,THISDATETIME,FLOATDEGREE,ACTUALDEGREE,INPUTOPERATOR,STARTMONTH,ENDMONTH,MEMO,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,UUID,FEESBZ,BLHDL,flag,entrypensonnel,entrytime,NETWORKHDL,ADMINISTRATIVEHDL,MARKETHDL,INFORMATIZATIONHDL,BUILDHDL,dlbzw,MANUALAUDITSTATUS,DEDHDL,DDDF)");
			// sql.append(" VALUES('" + formBean.getAmmeteridFk() + "'");
			// sql.append(",'" + formBean.getLastdegree() + "','"
			// + formBean.getThisdegree() + "','"
			// + formBean.getLastdatetime() + "','"
			// + formBean.getThisdatetime() + "','" + floatdegree
			// + "'");
			// sql.append(",'" + dianl.get(i) + "','"
			// + formBean.getInputoperator() + "','"
			// + year_month.get(i) + "','" + year_month.get(i) + "','"
			// + formBean.getMemo() + "','" + autoauditstatus + "','"
			// + autoauditdescription + "','" + uuid
			// + "','1','"
			// + Double.parseDouble(diandu.get(i).toString()) + "','"
			// + flag + "','" + formBean.getEntrypensonnel() + "',"
			// + s + ",'" + scdlfentan.get(i)
			// + "','" + bgdlfentan.get(i) + "','" + yydlfentan.get(i)
			// + "','" + xxhdlfentan.get(i) + "','"
			// + jstzdlfentan.get(i) + "','1','0','"+bbll+ "','" +
			// dddfdlfentan.get(i)+"')");
			// System.out.println("--999-" + sql.toString());
			// sqlBatch[i] = sql.toString();
			// }
			// ====2013-10-09原始模式====================

			// System.out.println("--------"+formBean.getAmmeterdegreeid());
			// String sql = " UPDATE ammeterdegrees SET AMMETERID_FK='" +
			// formBean.getAmmeteridFk() + "'," +
			// "LASTDEGREE='" + formBean.getLastdegree() + "'," +
			// "THISDEGREE='" + formBean.getThisdegree() + "'," +
			// "LASTDATETIME='" + formBean.getLastdatetime() + "'," +
			// "THISDATETIME='" + formBean.getThisdatetime()+ "'," +
			// "FLOATDEGREE='" + formBean.getFloatdegree() + "'," +
			// "ACTUALDEGREE='" + formBean.getBlhdl() + "'," +
			// "INPUTOPERATOR='" + formBean.getInputoperator() + "'," +
			// "INPUTDATETIME='" + formBean.getInputdatetime() + "'," +
			// "STARTMONTH='" + formBean.getStartmonth() + "'," +
			// "ENDMONTH='" + formBean.getEndmonth() + "'," +
			// "MEMO='" + formBean.getMemo() + "'," +
			// "AUTOAUDITSTATUS='" + formBean.getAutoauditstatus() + "'," +
			// "AUTOAUDITDESCRIPTION='" + formBean.getAutoauditdescription() +
			// "'" +
			// " WHERE AMMETERDEGREEID="+formBean.getAmmeterdegreeid();
			// //修改电量信息表

			// System.out.println(sql.toString());
			msg = "修改电量信息失败！";
			db.setAutoCommit(false);
			db.update(delete);
			db.update(sqlx);// 新模式2013-10-09
			// db.updateBatch(sqlBatch);原始模式2013-10-09
			msg = "修改电量信息成功！";
			db.commit();
			db.setAutoCommit(true);
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	/**
	 * 添加电量 显示电表信息列表
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized ArrayList getPageDataAmmeter(int start,
			String whereStr, String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select  DB.DBID,jz.jzname,rndiqu(jz.xian) as xian,"
					+ "rndiqu(jz.shi) as shi,"
					+ "rndiqu(jz.sheng) as sheng,"
					+ "(select name from indexs where code = DB.SSZY and type = 'SSZY') professionaltypeid,(select name from indexs where code = DB.DBYT and type = 'DBYT') ammeteruse,"
					+ "DB.CSDS,TO_CHAR(DB.CSSYTIME,'yyyy-mm-dd') CSSYTIME,"
					+ "DB.BEILV,DB.DBXH from DIANBIAO DB,zhandian jz "
					+ "where jz.qyzt='1' and jz.shsign='1' and DB.dbqyzt='1' and DB.ZDID=jz.id "
					+ whereStr
					+ " "
					+ "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ")order by DB.DBID  ";

			System.out.println("电量列表添加电量显示电表信息列表:" + sql.toString());
		} catch (DbException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		ResultSet rs = null;
		try {
			db.connectDb();
			StringBuffer strall = new StringBuffer();
			strall
					.append("select count(*) from DIANBIAO DB,zhandian jz "
							+ "where jz.qyzt='1' and jz.shsign='1' and DB.ZDID=jz.id and DB.dbqyzt='1' "
							+ whereStr
							+ " "
							+ "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
							+ loginId + "'))" + fzzdstr + ") ");
			rs = db.queryAll(strall.toString());
			if (rs.next()) {
				this.setAllPage((rs.getInt(1) + 14) / 15);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
			Query query = new Query();
			list = query.query(rs);
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

	// 电表电量趋势/异常报道 电表查询
	public synchronized ArrayList getPageDataAmmeterM(int start,
			String whereStr, String loginId) {
		System.out.println("AmmeterDegreeBean-getPageDataAmmeter:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select  am.ammeterid,am.ammetername,am.ELECTRICCURRENTTYPE_AMMETER,jz.zdcode,jz.jzname,dag.agname as xian,"
					+ "(select agname from d_area_grade where agcode = substr(dag.agcode, 0, 5)) as shi,"
					+ "(select agname from d_area_grade where agcode = substr(dag.agcode, 0, 3)) as sheng,"
					+

					"(select name from indexs where code = am.professionaltypeid and type = 'SSZY') professionaltypeid,(select name from indexs where code = am.ammeteruse and type = 'DBYT') ammeteruse,"
					+

					"am.initialdegree,am.initialdate,"
					+ "am.multiplyingpower,am.ammetertype from ammeters am,zhandian jz, "
					+ "d_area_grade dag where am.ammeteruse='dbyt02'  and jz.xian = dag.agcode and am.stationid=jz.id "
					+ whereStr
					+ " "
					+ "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ")order by am.ammeterid  ";

			System.out.println("--" + sql.toString());
		} catch (DbException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		ResultSet rs = null;
		try {
			db.connectDb();
			StringBuffer strall = new StringBuffer();
			strall
					.append("select count(*) from ammeters am,zhandian jz, "
							+ "d_area_grade dag where am.ammeteruse='dbyt02' and jz.xian = dag.agcode and am.stationid=jz.id "
							+ whereStr
							+ " "
							+ "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
							+ loginId + "'))" + fzzdstr + ") ");
			rs = db.queryAll(strall.toString());
			if (rs.next()) {
				this.setAllPage((rs.getInt(1) + 14) / 15);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
			Query query = new Query();
			list = query.query(rs);
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

	// 电表电量警告对比
	public synchronized ArrayList getDlJg(String whereStr, String loginId,
			String str) {

		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		ResultSet rs = null;
		// 调用负责站点条件函数
		try {
			sql
					.append("SELECT distinct Z.JZNAME,(CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE"
							+ " WHERE AGCODE = Z.XIAN)ELSE''END) || (CASE WHEN Z.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME "
							+ " FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU)ELSE'' END) AS SZDQ,TO_CHAR(bb.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(bb.ENDMONTH,'yyyy-mm') ENDMONTH,"
							+ "TO_CHAR(bb.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(bb.THISDATETIME,'yyyy-mm-dd') THISDATETIME, AA.js, bb.gl "
							+ " FROM ZHANDIAN Z,(SELECT zd.id, dbid, SUM(am.blhdl) js"
							+ " FROM zhandian zd, dianbiao db, ammeterdegrees am where zd.id = db.zdid and db.dbid = am.ammeterid_fk  "
							+ "and db.dbyt = 'dbyt01'  "
							+ whereStr
							+ "  group by zd.id, dbid) AA,"
							+ "(SELECT zz.id, dbid, SUM(aa.blhdl) gl,min(STARTMONTH)as STARTMONTH,max(ENDMONTH)as ENDMONTH,"
							+ "min(LASTDATETIME)as LASTDATETIME,max(THISDATETIME)as THISDATETIME "
							+ " FROM zhandian zz, dianbiao dd, ammeterdegrees aa "
							+ " where zz.id = dd.zdid and dd.dbid = aa.ammeterid_fk and dd.dbyt = 'dbyt03'  "
							+ whereStr
							+ "  group by zz.id, dbid) bb"
							+ " WHERE  Z.ID = AA.id and Z.ID  = bb.id  "
							+ str
							+ " "
							+ "  and (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
							+ loginId + "')) order by jzname ");//

			System.out.println("警告对比" + sql.toString());
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {

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

		return list;
	}

	// 获取电费的电量ID
	public synchronized ArrayList getAmmeterFeesDegreeId() {
		AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		StringBuffer sql = new StringBuffer();
		sql
				.append("select t.ammeterdegreeid_fk ammeterdegreeid from electricfees t");
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		try {
			System.out.println("getAmmeterFeesDegreeId:" + sql);
			db.connectDb();
			ResultSet rs = null;
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
			rs.close();
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return list;
	}

	// 获取比对重复电量电费信息的值
	public synchronized ArrayList getPageDataN() {

		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// 调用负责站点条件函数
		try {
			// fzzdstr = getFuzeZdid(db,loginId);

			sql = "select  ad.ammeterid_fk,ad.lastdatetime,ad.lastdegree,ad.thisdatetime,ad.thisdegree,ad.actualdegree,ef.ACTUALPAY,ef.NOTENO from  ammeterdegrees ad, electricfees   ef where ad.ammeterdegreeid = ef.ammeterdegreeid_fk";
			System.out.println("AmmeterDegreeBean-getPageDataCityA:" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {

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

		return list;
	}

	// 获取比对重复电量信息的值(王代军)
	public synchronized ArrayList getPageDataN(String con) {

		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// 调用负责站点条件函数
		try {
			// fzzdstr = getFuzeZdid(db,loginId);

			sql = "select  ad.ammeterid_fk,ad.lastdatetime,ad.lastdegree,ad.thisdatetime,ad.thisdegree,ad.actualdegree from  ammeterdegrees ad where ad.ammeterid_fk = '"
					+ con.trim() + "'";
			System.out.println("AmmeterDegreeBean-getPageDataCityA:" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {

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

		return list;
	}

	/**
	 * 超标站点测试描述模板和超标基站完成整改信息模板时--带出的站点和其他的信息
	 * 
	 * @param start
	 * @param id
	 *            :页面查询站点的唯一标识
	 * @return list:站点信息信息
	 */

	public synchronized ArrayList getPageDataModelzdcbglxx(int start, String id) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// 调用负责站点条件函数
		try {
			sql = "SELECT CB.ZDID,CB.ZDNAME,CB.SHI,CB.XIAN,CB.XIAOQU FROM CBZD CB "
					+ "WHERE CB.ID = '" + id + "'";
			System.out.println("超标站点测试描述模板和超标基站完成整改信息模板时--带出的站点和其他的信息:" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
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

	public synchronized ArrayList getPageDataModelzdxgsq(int start, String id,
			String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// 调用负责站点条件函数
		try {
			sql = "SELECT (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI) AS SHI,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN) AS XIAN,"
					+ " (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU) AS XIAOQU,"
					+ "Z.ID,Z.JZNAME FROM ZHANDIAN Z WHERE "
					+ "Z.SHSIGN = '1'  "
					+ "AND Z.ID = '"
					+ id
					+ "' AND XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='"
					+ loginId + "')";
			System.out.println("对标管理区县申请模板时--带出的站点和其他的信息:" + sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
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
		return list;
	}
}
