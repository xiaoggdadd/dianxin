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
	// �������� �����б�
	public synchronized ArrayList getPageData3(String whereStr, String loginId) {

		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// ���ø���վ����������
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
		System.out.println("��������" + sql.toString());
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

	// �����б����ͳ��������
	public String getCountt1(String whereStr, String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String count = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// ���ø���վ����������
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
		System.out.println("����������ܣ�" + sql.toString());

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

	// �����б���ͳ���ܵ�����
	public String getCountt2(String whereStr, String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String count = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// ���ø���վ����������
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
		System.out.println("����������ܣ�" + sql.toString());

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

	// ����ģ��ʱ--������վ�����������Ϣ
	/*
	 * update 2014.06.10 zhouxue ���ݣ���Ĭ�����������֧������ΪԤ֧���½� ��ȥ�� ���Ե��֧�����Ͷ������س�����
	 * �������ߵ�ѵ�����
	 */
	public synchronized ArrayList getPageDataModel(int start, String whereStr,
			String loginId) {

		System.out
				.println("AmmeterDegreeBean-getPageDataModel����ģ��ʱ--������վ�����������Ϣ:"
						+ whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// ���ø���վ����������
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
			System.out.println("��ѵ�����ģ��ʱ--������վ�����������Ϣ:" + sql);
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

	// ��ǿ������ģ��ʱ--������վ�����������Ϣ
	public synchronized ArrayList getPageDataModelEnhance(int start,
			String whereStr, String loginId) {

		System.out
				.println("AmmeterDegreeBean-getPageDataModelEnhance����ģ��ʱ--������վ�����������Ϣ:"
						+ whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// ���ø���վ����������
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
			System.out.println("��ǿ���ѵ�����ģ��ʱ--������վ�����������Ϣ:" + sql);
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
	 * Ԥ������Ϣ����ģ��ʱ--������վ�����������Ϣ
	 * 
	 * @param start
	 * @param whereStr
	 * @param loginId
	 * @return list:վ���վ��ĵ����Ϣ
	 */

	public synchronized ArrayList getPageDataModelpp(int start,
			String whereStr, String loginId) {
		System.out
				.println("AmmeterDegreeBean-getPageDataModelԤ������Ϣ����ģ��ʱ--������վ�����������Ϣ:"
						+ whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// ���ø���վ����������
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
			System.out.println("Ԥ������Ϣ��ѵ�����ģ��ʱ--������վ�����������Ϣ:" + sql);
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

	// ����v3���ʱ ����������
	public synchronized ArrayList getzhandianmodifg(int start, String whereStr,
			String loginId, String biaoji) {

		System.out
				.println("AmmeterDegreeBean-getPageDataModel����ģ��ʱ--������վ�����������Ϣ:"
						+ whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// ���ø���վ����������
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
					+ "z.fzr,z.sydate,case when z.qyzt='1' then '��' else '��' end qyzt,z.lyjhjgs,(select i.name from indexs i where i.code=z. dytype and i.type='dytype') dytype,case when g2='1' then '��' else '��' end g2,case when g3='1' then '��' else '��' end g3,case when kdsb='1' then '��' else '��' end kdsb,"
					+ " case when yysb='1' then '��' else '��' end yysb,z.zpsl, z.zssl, z.kdsbsl,z.yysbsl,case when kt1='1' then '��' else '��' end kt1,case when kt2='1' then '��' else '��' end kt2, z.zlfh,z.jlfh,z.memo,z.dianfei,"
					+ "case when z.BGSIGN='1' then '��' else '��' end BGSIGN, case when jnglmk='1' then '��' else '��' end jnglmk, case when zgd='1' then '��' else '��' end zgd, "
					+ "(select i.name from indexs i where i.code=z. gdfs and i.type='GDFS')gdfs,z.edhdl,(select i.name from indexs i where i.code=info.fkzq and i.type='FKZQ')fkzq,(select i.name from indexs i where i.code=d.dfzflx and i.type='dfzflx')dfzflx,"
					+ " d.beilv,d.dbid,(select i.name from indexs i where i.code=d.dbyt and i.type='DBYT')dbyt,(select i.name from indexs i where i.code=info.fkfs and i.type='FKFS')fkfs,"
					+ " d.dbzbdyhh,case when info.watchcost='2' then '��' else '��' end watchcost,(select i.name from indexs i where i.code=d.linelosstype and i.type='xslx')linelosstype,d.linelossvalue,d.csds, to_char(d.cssytime,'yyyy-mm-dd') cssytime,"
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
			System.out.println("v3��Ϣ����ģ��ʱ--������վ�����������Ϣ:" + sql);
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

	// �������� ������ӡ(�ݲ���)��ammeters��
	public synchronized ArrayList getPageData(String whereStr, String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// ���ø���վ����������
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
		System.out.println("������������" + sql.toString());

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

	// ����ģ��ʱ--������վ�����������Ϣ

	// ��ͬ��--ģ������
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
		// ���ø���վ����������
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
			System.out.println("��ͬ����������ģ��ʱ��sql���:" + sql1);
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

	// �����޸ĵ���
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
		// ���ø���վ����������
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
			System.out.println("վ�㵥�������޸�����ģ��ʱ��sql���:" + sql);
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

	// �����Զ�����б���Ϣ
	public synchronized ArrayList getPageDataCheck(int start, String whereStr,
			String loginId) {

		System.out.println("AmmeterDegreeBean-getPageData:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// ���ø���վ����������
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
			System.out.println("�Զ��������:" + sql.toString());

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
	 * ������������ģ��ʱ��sql���
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
		// ���ø���վ����������
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

			System.out.println("������������ģ��ʱ��sql���:" + sql);
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

	// �Զ������������
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
				System.out.println("�Զ������������" + strall.toString());
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

	// �Զ��������������õ����ܺ�ͳ��
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
				System.out.println("�Զ��������������õ����ܺ�ͳ�ƣ�" + strall.toString());
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

	// �Զ�������˵���
	public synchronized ArrayList getPageDatash(String whereStr, String loginId) {

		System.out.println("AmmeterDegreeBean-getPageData:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// ���ø���վ����������
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
		System.out.println("�Զ�������˵���" + sql.toString());

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

	// ���ӵ����ʾ�����б�(�ݲ���)��ammeters��

	public synchronized ArrayList getPageDataList(int start, String whereStr,
			String loginId) {

		System.out.println("AmmeterDegreeBean-getPageData:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		// ���ø���վ����������
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

	// ����վ������
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
		System.out.println("����վ��������" + cxtj);
		return cxtj.toString();
	}

	/**
	 * ��ӵ��� ������Ҫ��ӵ���Ϣ ���� ����
	 */
	public synchronized String addDegree(AmmeterDegreeFormBean formBean,
			String bzw) {
		String msg = "�������ʧ�ܣ������Ի������Ա��ϵ��";
		// System.out.println(msg + "***************************************");
		MD5 md = new MD5();
		CTime ctime = new CTime();
		DataBase db = new DataBase();
		int flag = 1;
		// �ж���Ҫ����ĵ�����Ϣ�Ƿ��ظ�
		// ��ȡҳ���ϴε�����
		String lastderee1 = formBean.getLastdegree();
		// ��ȡҳ�汾�ε�����
		String thisdegree = formBean.getThisdegree();
		// ��ȡҳ�汾�γ���ʱ��
		String thisdatetime = formBean.getThisdatetime();
		// ��ȡҳ����ID
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
		// msg = "������Ϣ�ظ�����ȷ�Ϻ��������룡";
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
		// ��ӵ���
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

		// 2014-7-21 ������������
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

		System.out.println("���ӵ���sql��" + sqltx.toString());
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
				msg = "������Ϣ�ظ�����ȷ�Ϻ��������룡";
			} else {
				db.update(sqltx.toString());
				msg = "��ӵ����ɹ���";
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

	// ����������� ������ֵ
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
		System.out.println("�����б�--��ӵ���������Ϣ��" + sql.toString());
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
	 * ��ӵ������ ������Ҫ��ӵ���Ϣ
	 */
	public synchronized String addDegreeFees(AmmeterDegreeFormBean formBean, String uuid, String bzw) {
		System.out.println("=======2012====��ѵ���ӷ���ִ��=====");
		String msg = "�����ѵ�ʧ�ܣ������Ի������Ա��ϵ��";
		int flag = 1;//�Զ���˱�ʶ,ͨ��1 ����Ҫ�м����    0   �Զ���˲�ͨ����Ҫ�м����
		String bbll = "";//(���ʺĵ���-���ر����)/���ر����
		String qbl = "";//(���ʺĵ���-ȫʡ�����)/ȫʡ�����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt;
		String lastadtime = "";
		try {
			dt = sdf.parse(formBean.getLastdatetime());// �ϴγ���ʱ��
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			rightNow.add(Calendar.DAY_OF_YEAR, -1);// ���ڼ�1��     �ϴγ���ʱ�����ʵֵ
			Date dt1 = rightNow.getTime();
			lastadtime = sdf.format(dt1);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
 
			System.out.println("�������idformBean.getAmmeteridFk()        " + formBean.getAmmeteridFk());
		long lastdaydegree = formBean.getLastDayAmmeterDegree(formBean .getAmmeteridFk(), lastadtime);//ǰ����     �պĵ���
			System.out.println(lastdaydegree + "  �ϴ�");
		// ���㱾���պĵ���
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		  
		double thisdaydegree;// ���α�       �պĵ���
		double daydegereerate = 0;// �����ǰ�α�           �պĵ�����ֵ((thisdaydegree - lastdaydegree) / lastdaydegree) * 100;
		try {
			long diff = sd.parse(formBean.getThisdatetime()).getTime() - sd.parse(formBean.getLastdatetime()).getTime();
			long day = (diff / 1000 * 24 * 60 * 60) + 1;// ����������
			thisdaydegree = Double.parseDouble(formBean.getBlhdl()) / day;// ���α�       �պĵ��� 
			daydegereerate = ((thisdaydegree - lastdaydegree) / lastdaydegree) * 100;
		} catch (ParseException e1) { 
			e1.printStackTrace();
		}
		/***���ϼ���������ǰ�α�           �պĵ�����ֵ*/
		
		
		AutoAuditBean bean = new AutoAuditBean();
		ArrayList fylist = new ArrayList();
		fylist = bean.getPageData(1, "");//��ȡ  �Զ���˱�ʶ��ļ��� permission_configuration t where ITEMLLAG = 2
		String ItemID = ""; //�Զ�������ID
		String ItemName = ""; //�Զ������ı�ʶ��
		String ItemValue = "";//�Զ�����������ֵ
		String ItemDescription = "";//�Զ������Ĺ�������
		String beilv = "";//���ı���ֵ
		String 	autoauditstatus = "1",//�Զ����״̬  1 Ĭ��ͨ�� 
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
	 
		String floatdegree = formBean.getFloatdegree();//��ȡ��������ֵ
		String memo = formBean.getMemo();//������ע
		String memo_bz = "1";
		double floatt = 0.0;//��ȵ�����
		if (null != floatdegree && !floatdegree.equals("")
				&& !floatdegree.equals(" ")) {
			floatt = Double.parseDouble(floatdegree);
		}  
		if (floatt!=0.0&&null== memo &&  memo.equals("") &&  memo.equals(" ")) {
			memo_bz = "0";// �õ����е���˵��
		}   
		DataBase db = new DataBase();
		
		int bzw1 = 0;// ѭ���жϱ�ʶ,�Զ���˹�9��
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
				/*һ,��֤�Ƿ����õ����˵��    û�����Զ���˲�ͨ��*/
				if (ItemName.equals("AuditDegree1") && (!ItemValue.equals("0"))
						&& memo_bz.equals("0")) {
					autoauditstatus = "0";
					autoauditdescription1 = "�õ����û��˵����";
					auditfee1 = "0";
				} 
				
				
				// ��֤�����պĵ��� �����ϴ��պĵ��� �ı��أ������պĵ���-�ϴ��պĵ�����/�ϴ��պĵ��� �Ƿ��������ֵ
				/*��,��֤�����պĵ��� �����ϴ��պĵ��� �ı���    �������Զ���˲�ͨ��*/
				if (ItemName.equals("AuditDegree4") && (!ItemValue.equals("0"))
						&& (daydegereerate > Double.parseDouble(ItemValue))) {
					autoauditstatus = "0";
					autoauditdescription4 = "ƽ���պĵ��������ϴ��վ���" + ItemValue
							+ "%�����ν����վ��ĵ����������һ�ν��ѵ��վ��ĵ����ٷֱȣ���";
					auditfee4 = "0";
				}
				
				
				
				String edsql = "select z.edhdl,d.beilv,z.qsdbdl,z.property,z.ktxs,z.shi,z.zlfh,z.jlfh,z.jcxs,z.fwxs,z.yfxs,z.yflx from zhandian z,dianbiao d "
						+ "where z.id=d.zdid and d.dbid='"
						+ formBean.getAmmeteridFk() + "'";
				ResultSet rs = null;
				String edhdl = "0";// ��ĵ���
				String qsdbdl = "0";// ȫʡ�������
				String property = "";// վ������
				String ktxs = "";// �յ�ϵ��
				String shi = "";// ��
				String zlfh = "";// ֱ������
				String jlfh = "";// ��������
				String jcxs = "";// ����ϵ��
				String yfxs = "";// �·�ϵ��
				String fwxs = "";// ����ϵ��
				String fwlx = "";// ��������
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
						System.out.println("ktxs��ʼ:" + ktxs);
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
				// �����·ݵ�ϵ��

				String endm = formBean.getEndmonth();
				String endmo = endm.substring(5, 7);
				String yymonth = "";
				Double yf = null;// վ����·�ϵ��
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
				System.out.println("�·�" + sqlyf.toString());
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

				// ���ҿյ�ϵ��
				String sss = "SELECT K.ID,K.KSZLFH,K.JSZLSH,K.JZKTXS,K.JRWKTXS,K.XZZJKTXS,K.JYJFKTXS,K.QTKTXS,K.IDCJFKTXS,K.SJBZW FROM KTXS K ";
				ResultSet r = null;
				String id1 = "";
				String kszlfh = "", kszlfh1 = "", kszlfh2 = "", kszlfh3 = "", kszlfh4 = "", kszlfh5 = "", kszlfh6 = "";// ��ʼֱ������
				String jszlfh = "", jszlfh1 = "", jszlfh2 = "", jszlfh3 = "", jszlfh4 = "", jszlfh5 = "", jszlfh6 = "";// ����ֱ������
				String jz = "", jz1 = "", jz2 = "", jz3 = "", jz4 = "", jz5 = "", jz6 = "";// ��վ�յ�ϵ��
				String jrw = "", jrw1 = "", jrw2 = "", jrw3 = "", jrw4 = "", jrw5 = "", jrw6 = "";// �������յ�ϵ��
				String xzzj = "", xzzj1 = "", xzzj2 = "", xzzj3 = "", xzzj4 = "", xzzj5 = "", xzzj6 = "";// ����֧�ֿյ�ϵ��
				String jyjf = "", jyjf1 = "", jyjf2 = "", jyjf3 = "", jyjf4 = "", jyjf5 = "", jyjf6 = "";// ��������յ�ϵ��
				String qt = "", qt1 = "", qt2 = "", qt3 = "", qt4 = "", qt5 = "", qt6 = "";// �����յ�ϵ��
				String idcjf = "", idcjf1 = "", idcjf2 = "", idcjf3 = "", idcjf4 = "", idcjf5 = "", idcjf6 = "";// idc�����յ�ϵ��
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
				// ���ҷ���ϵ��
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
				// ts Ϊ ���γ���ʱ�� ���ϴ� ����ʱ�� ֮��� ����
				long ts = time.getQuot(formBean.getLastdatetime(), formBean
						.getThisdatetime()) + 1;
				DecimalFormat fomcate = new DecimalFormat("0.00");
				// ���ض��� ���㱾�µı��ض��� �ĵ���
				Double hdl = Double.parseDouble(edhdl) * ts;

				// ʡ���� ���㱾�µı���ȫʡ���� �ĵ���
				Double qsdbdll = Double.parseDouble(qsdbdl) * ts;

				Double ktxss = Double.parseDouble(ktxs);//��վ�Ŀյ�ϵ��
				Double bchdl = Double.parseDouble(formBean.getBlhdl());//���µı��ʺĵ���
				bbll = fomcate.format((bchdl - hdl) / hdl * 100);//(���ʺĵ���-���ر����)/���ر����
				qbl = fomcate.format((bchdl - qsdbdll) / qsdbdll * 100);//(���ʺĵ���-ȫʡ�����)/ȫʡ�����
				Double zlfhh = Double.parseDouble(zlfh);//վ��ֱ������
				Double jlfhh = Double.parseDouble(jlfh);//վ�㽻������
				Double jcxss = Double.parseDouble(jcxs);//վ�����ϵ��
				Double fwxss = Double.parseDouble(fwxs);//վ��ķ���ϵ��
				Double yfxss = Double.parseDouble(yfxs);//վ����·�ϵ��
						System.out.println("bzw1:" + bzw1);
						System.out.println("�Զ����û����ǰ    �ж��Ƿ񳬳�ʡ��������¸�����10%");
						System.out.println("qsdbdl:" + qsdbdl);
						System.out.println("ts:" + ts);
						System.out.println("bchdl:" + bchdl + "qsdbdll:" + qsdbdll);
				if (bzw1 == 9) {// ��ʼselect * from permission_configuration t
								// where ITEMLLAG = 2 ִ���Զ����������� ���������� �����ھ������
					System.out.println("�Զ���˽���ִ�п�ʼ____�ж��Ƿ񳬳�ʡ��������¸�����10%");
					if (!fomcate.format(qsdbdll).equals("0.00")) {// ���ȫʡ���������Ϊ��
						// ��֤���ʺĵ����Ƿ�ȫʡ����� ���¸��� 10%
						if ((bchdl < qsdbdll * (1 - 0.1) || bchdl > qsdbdll
								* (1 + 0.1))) {// ������ʺĵ���С��ȫʡ����ĵ���90% ����
												// ������ʺĵ�������ȫʡ����ĵ���110% 
							flag = 0;
							autoauditstatus = "0";
							autoauditdescription5 = autoauditdescription5
									+ "���ε������¸�������ȫʡ�����10%";
							auditfee5 = "0";
						}
						// ���ʡ����Ϊ�� վ������Ϊ��վ
					} else if (fomcate.format(qsdbdll).equals("0.00")
							&& property.equals("zdsx02")) {// property.equals("zdsx02")��ʾվ�������ǻ�վ
															// (�������л���zdsx01,������zdsx07)
						/**ȫʡ�겻Ϊ��,վ�������ǻ�վ����,���ر겻Ϊ��**/
						if (hdl != 0) {// ������ر겻������
							if ((bchdl < hdl * 0.9
									* (jcxss + yfxss * ktxss + fwxss)
									* (1 - 0.1) || bchdl > hdl * 0.9
									* (jcxss + yfxss * ktxss + fwxss)
									* (1 + 0.1))) {
								flag = 0;
								autoauditstatus = "0";
								autoauditdescription5 = autoauditdescription5
										+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
									auditfee23 = "0";
								}
							}

						}
					} else if (fomcate.format(qsdbdll).equals("0.00")
							&& property.equals("zdsx05")) {//�����ݿ���index��̬����û��˵��zdsx05   վ������ ��ʲô����
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
										+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
									auditfee12 = "0";
								}

							}

						}
					} else if (property.equals("zdsx01")
							&& qsdbdll.toString().equals("0.00")) {//ȫʡ�� Ϊ�� վ�������ǻ���
						Double jyjfktxs = null;//�������Ŀյ�ϵ��
						if (zlfhh >= Double.parseDouble(kszlfh1)
								&& zlfhh < Double.parseDouble(kszlfh2)) {//���ݻ�վ�ĵ�ֱ������,ѡ���յ�ϵ��
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
						Double jcxsjc = null;//����ϵ��
						if (fwlx.equals("fwlx01")) {//����վ�㷿�����Ͷ�����ϵ��
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
									auditfee8 = "0";

								}
							} else if (shi == "13704") {
								if ((bchdl < hdl * 1 * (jcxsjc + yf * jyjfktxs)
										* (1 - 0.1) || bchdl > hdl * 1
										* (jcxsjc + yf * jyjfktxs) * (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
										auditfee20 = "0";

									}
								}
							}
						}
					} else if (property.equals("zdsx06")
							&& qsdbdll.toString().equals("0.00")) {//�����ݿ��в����ڸ�����
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
										+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
									auditfee23 = "0";
								}

							}

						}
					} else if (property.equals("zdsx04")
							&& qsdbdll.toString().equals("0.00")) {//������� �����ݿ��в�����
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
										+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
									auditfee26 = "0";
								}

							}

						}
					} else if (property.equals("zdsx07")
							&& qsdbdll.toString().equals("0.00")) {//ȫʡ��Ϊ�� վ��������������
						Double idcjfktxs = null;//idc�����Ŀյ�ϵ��
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
						Double jcxsjc = null;//IDC�����Ļ���ϵ��
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
										+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
									 
								}

							}

						}
					}
					System.out.println("�Զ���˽���ִ��if(bzw1==9)___����");
				}// if(bzw1==9)����select * from permission_configuration t where
					// ITEMLLAG = 2 ִ���Զ����������� ���������� �����ھ������

				if (ItemName.equals("AuditDegree6")) {

					if (bchdl < hdl * (1 - Double.parseDouble(ItemValue) / 100)
							|| bchdl > hdl
									* (1 + Double.parseDouble(ItemValue) / 100)) {
						flag = 0;
						autoauditstatus = "0";
						autoauditdescription = autoauditdescription
								+ "���ε������¸�������վ���ĵ�������ֵ��" + ItemValue + "%"
								+ ";";
					}
				}
			}//forѭ������
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
		
		String start = formBean.getStartmonth();//��ʼ����
		String end = formBean.getEndmonth();//��������
		String aa = formBean.getScdl();//�����õ���
		String bb = formBean.getBgdl();//�칫�õ���
		String cc = formBean.getYydl();//Ӫҵ�õ���
		String dd = formBean.getXxhdl();//��Ϣ��֧�źĵ���
		String ee = formBean.getJstzdl();//����Ͷ�ʺĵ���
		String ff = formBean.getDddfdl();// �������
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

		// ======================2013-10-09��ģʽ==================================
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

		System.out.println("��ģʽ���ӵ���sql��" + sqlx);
		

		try {

			db.connectDb();
			db.update(sqlx);// ����ģʽ
			// db.updateBatch(sqlBatch);ԭʼģʽ
			msg = "��ӵ����ɹ�!";
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
	 * ɾ������
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String delAmmeterDegree(String degreeid) {
		String msg = "ɾ���˺�ʧ�ܣ�";
		DataBase db = new DataBase();

		try {
			db.connectDb();

			String sql1 = "DELETE ammeterdegrees WHERE uuid=(select a.uuid from ammeterdegrees a where a.ammeterdegreeid="
					+ degreeid + ")"; // ɾ���˺ű�

			System.out.println(sql1.toCharArray());
			// String delNames = getName(accountId, db);
			msg = "ɾ��ʧ�ܣ�";
			db.update(sql1);
			msg = "ɾ���ɹ���";
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
	 * ��������ɾ��
	 */
	public synchronized String deletesElectricFees(String ammeterid_fk) {

		String msg = "ɾ������ʧ�ܣ�";
		DataBase db = new DataBase();
		String uuid = "";

		try {
			db.connectDb();
			String que = "select am.uuid from ammeterdegrees am where am.ammeterdegreeid in("
					+ ammeterid_fk + ")";
			ResultSet rs = null;
			System.out.println("��ѯuuid:" + que);
			rs = db.queryAll(que.toString());

			while (rs.next()) {
				String uuid1 = rs.getString("uuid");
				uuid = uuid1 + "," + uuid;
			}
			rs.close();// rsg

			String uu = uuid.substring(0, uuid.length() - 1);

			String delete = "delete from ammeterdegrees where uuid in(" + uu
					+ ")";
			System.out.println("ɾ��:" + delete.toString());
			msg = "ɾ������ʧ�ܣ�";
			db.update(delete);

			msg = "ɾ�������ɹ���";

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
	 * �޸ĵ�����Ϣ
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String modifyAmmeterDegree(
			AmmeterDegreeFormBean formBean, String idStr, String id) {
		String msg = "�޸ĵ�����Ϣʧ�ܣ�";
		DataBase db = new DataBase();
		int flag = 1;

		/*
		 * // �Զ�����ж� // ����������ϴ��պĵ�����ֵ long lastdaydegree =
		 * formBean.getLastDayAmmeterModfiyDegree(formBean .getAmmeteridFk(),
		 * formBean.getAmmeterdegreeid()); // System.out.println(lastdaydegree);
		 * // ���㱾���պĵ��� SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		 * long nd = 1000 * 24 * 60 * 60;// һ��ĺ����� // �������ʱ��ĺ���ʱ����� long diff,
		 * day; double thisdaydegree, daydegereerate = 0; try { //
		 * System.out.println
		 * ("222222:"+sd.parse(formBean.getThisdatetime()).getTime()); diff =
		 * sd.parse(formBean.getThisdatetime()).getTime() -
		 * sd.parse(formBean.getLastdatetime()).getTime(); day = diff / nd;//
		 * ���������� thisdaydegree = Double.parseDouble(formBean.getBlhdl()) /
		 * day;// �����պĵ��� daydegereerate = ((thisdaydegree - lastdaydegree) /
		 * lastdaydegree) * 100;// ������ϴ��պĵ�����ֵ //
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
		 * autoauditstatus = "0"; autoauditdescription1 = "�õ����û��˵����"; auditfee1
		 * = "0"; } if (ItemName.equals("AuditDegree2") &&
		 * (!ItemValue.equals("0")) && ad2_bz.equals("1")) { //
		 * System.out.println(ItemName+"*****"+ItemValue+"****");
		 * autoauditstatus = "0"; autoauditdescription2 = "�ϴε���������󳭱�����һ�£�";
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
		 * autoauditstatus = "0"; autoauditdescription3 = "����ʱ��С�ڱ��γ���ʱ�䣡";
		 * auditfee3 = "0"; } if (ItemName.equals("AuditDegree4") &&
		 * (daydegereerate > Double.parseDouble(ItemValue))) {
		 * System.out.println(ItemName + "*****" + ItemValue + "****");
		 * autoauditstatus = "0"; autoauditdescription4 = "ƽ���պĵ��������ϴ��վ���" +
		 * ItemValue + "%�����ν����վ��ĵ����������һ�ν��ѵ��վ��ĵ����ٷֱȣ���"; auditfee4 = "0"; }
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
		 * "���ε������¸�������վ���ĵ�������ֵ��" + ItemValue + "%" + ";"; } }
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
		 * formBean.setAutoauditdescription(autoauditdescription); // �Զ�����ж�
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
			// 2014-7-21 ������������
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
			System.out.println("�޸ĵ�����" + ssl.toString());
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
			// //�޸ĵ�����Ϣ��

			msg = "�޸ĵ�����Ϣʧ�ܣ�";
			db.update(delete);
			db.update(ssl);
			msg = "�޸ĵ�����Ϣ�ɹ���";
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
	 * �޸ĵ�ѵ�������Ϣ
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String modify(AmmeterDegreeFormBean formBean,
			String uuid, String id) {
		String msg = "�޸ĵ�����Ϣʧ�ܣ�";
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
			rightNow.add(Calendar.DAY_OF_YEAR, -1);// ���ڼ�1��
			Date dt1 = rightNow.getTime();
			lastadtime = sdf.format(dt1);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// �Զ�����ж�
		// ����������ϴ��պĵ�����ֵ
		long lastdaydegree = formBean.getLastDayAmmeterDegree(formBean
				.getAmmeteridFk(), lastadtime);
		// System.out.println(lastdaydegree);
		// ���㱾���պĵ���
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		long nd = 1000 * 24 * 60 * 60;// һ��ĺ�����
		// �������ʱ��ĺ���ʱ�����
		long diff, day;
		double thisdaydegree, daydegereerate = 0;
		try {
			// System.out.println("222222:"+sd.parse(formBean.getThisdatetime()).getTime());
			diff = sd.parse(formBean.getThisdatetime()).getTime()
					- sd.parse(formBean.getLastdatetime()).getTime();
			day = diff / nd;// ����������
			thisdaydegree = Double.parseDouble(formBean.getBlhdl()) / day;// �����պĵ���
			daydegereerate = ((thisdaydegree - lastdaydegree) / lastdaydegree) * 100;// ������ϴ��պĵ�����ֵ
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
					autoauditdescription1 = "�õ����û��˵����";
					auditfee1 = "0";
				}
				if (ItemName.equals("AuditDegree2") && (!ItemValue.equals("0"))
						&& ad2_bz.equals("1")) {
					// System.out.println(ItemName+"*****"+ItemValue+"****");
					autoauditstatus = "0";
					autoauditdescription2 = "�ϴε���������󳭱�����һ�£�";
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
				 * "����ʱ��С�ڱ��γ���ʱ�䣡"; auditfee3 = "0"; }
				 */
				if (ItemName.equals("AuditDegree4") && (!ItemValue.equals("0"))
						&& (daydegereerate > Double.parseDouble(ItemValue))) {
					System.out.println(ItemName + "*****" + ItemValue + "****");
					autoauditstatus = "0";
					autoauditdescription4 = "ƽ���պĵ��������ϴ��վ���" + ItemValue
							+ "%�����ν����վ��ĵ����������һ�ν��ѵ��վ��ĵ����ٷֱȣ���";
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
						System.out.println("ktxs��ʼ:" + ktxs);
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
				}// ͨ�������·�ȡֵ

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
				System.out.println("�·�" + sqlyf.toString());
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

				// ���ض���
				Double hdl = Double.parseDouble(edhdl) * ts;
				// ʡ����
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
				System.out.println("û����");
				System.out.println("qsdbdl:" + qsdbdl);
				System.out.println("ts:" + ts);
				System.out.println("bchdl:" + bchdl + "qsdbdll:" + qsdbdll);
				if (bzw1 == 9) {
					System.out.println("������");
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
									+ "���ε������¸�������ȫʡ�����10%";
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
										+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
										+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
									auditfee8 = "0";

								}
							} else if (shi == "13704") {
								if ((bchdl < hdl * 1 * (jcxsjc + yf * jyjfktxs)
										* (1 - 0.1) || bchdl > hdl * 1
										* (jcxsjc + yf * jyjfktxs) * (1 + 0.1))) {
									flag = 0;
									autoauditstatus = "0";
									autoauditdescription5 = autoauditdescription5
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
												+ "���ε������¸�������ȫʡ�����10%";
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
										+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
										+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
										+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
											+ "���ε������¸�������ȫʡ�����10%";
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
								+ "���ε������¸�������վ���ĵ�������ֵ��" + ItemValue + "%"
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
		// �Զ�����ж�

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
		 * yu) / time); // ���������ֵ�ÿ���� double scdlyushu =
		 * Double.parseDouble(formBean.getScdl()) % time; int scdl = (int)
		 * ((Double.parseDouble(formBean.getScdl()) - scdlyushu) / time); //
		 * �칫�����ֵ�ÿ���� double bgdlyushu = Double.parseDouble(formBean.getBgdl()) %
		 * time; int bgdl = (int) ((Double.parseDouble(formBean.getBgdl()) -
		 * bgdlyushu) / time); // Ӫҵ�����ֵ�ÿ���� double yydlyushu =
		 * Double.parseDouble(formBean.getYydl()) % time; int yydl = (int)
		 * ((Double.parseDouble(formBean.getYydl()) - yydlyushu) / time); //
		 * ��Ϣ�������ֵ�ÿ���� double xxhdlyushu =
		 * Double.parseDouble(formBean.getXxhdl()) % time; int xxhdl = (int)
		 * ((Double.parseDouble(formBean.getXxhdl()) - xxhdlyushu) / time); //
		 * ����Ͷ�ʵ����ֵ�ÿ���� double jstzdlyushu =
		 * Double.parseDouble(formBean.getJstzdl()) % time; int jstzdl = (int)
		 * ((Double.parseDouble(formBean.getJstzdl()) - jstzdlyushu) / time); //
		 * ��������ֵ�ÿ���� double dddfdlyushu =
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
		 * List dddfdlfentan = new ArrayList();// �������
		 * 
		 * for (int i = 0; i < time; i++) { String yue = String.valueOf(starty);
		 * if (yue.length() == 1) yue = "0" + yue; String year_month_tmp =
		 * startn + "-" + yue; starty++; if (starty > 12) { starty = 1;
		 * startn++; } year_month.add(year_month_tmp); if (i == time - 1) {
		 * diandu.add(dianduPermonth + yushu); dianl.add(dian + yu);
		 * scdlfentan.add(scdl + scdlyushu); bgdlfentan.add(bgdl + bgdlyushu);
		 * yydlfentan.add(yydl + yydlyushu); xxhdlfentan.add(xxhdl +
		 * xxhdlyushu); jstzdlfentan.add(jstzdl + jstzdlyushu);
		 * dddfdlfentan.add(dddfdl + dddfdlyushu);//������� } else {
		 * diandu.add(dianduPermonth); dianl.add(dian); scdlfentan.add(scdl);
		 * bgdlfentan.add(bgdl); yydlfentan.add(yydl); xxhdlfentan.add(xxhdl);
		 * jstzdlfentan.add(jstzdl); dddfdlfentan.add(dddfdl);//������� } }
		 */
		String s = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		try {
			db.connectDb();

			String delete = "delete from ammeterdegrees where uuid="
					+ "(select b.uuid from ammeterdegrees b where b.ammeterdegreeid='"
					+ id + "')";
			// ================2013-10-09===��ģʽ
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
			// ===================2013-10-09==��ģʽ
			System.out.println("�޸ĵ�ѵ�������ɾ��sql:" + delete.toString());
			System.out.println("�޸ĵ�ѵ�������ģʽ��sql��" + sqlx);

			// =====2013-10-09ԭʼģʽ===============
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
			// ====2013-10-09ԭʼģʽ====================

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
			// //�޸ĵ�����Ϣ��

			// System.out.println(sql.toString());
			msg = "�޸ĵ�����Ϣʧ�ܣ�";
			db.setAutoCommit(false);
			db.update(delete);
			db.update(sqlx);// ��ģʽ2013-10-09
			// db.updateBatch(sqlBatch);ԭʼģʽ2013-10-09
			msg = "�޸ĵ�����Ϣ�ɹ���";
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
	 * ��ӵ��� ��ʾ�����Ϣ�б�
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

			System.out.println("�����б���ӵ�����ʾ�����Ϣ�б�:" + sql.toString());
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

	// ����������/�쳣���� ����ѯ
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

	// ����������Ա�
	public synchronized ArrayList getDlJg(String whereStr, String loginId,
			String str) {

		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		ResultSet rs = null;
		// ���ø���վ����������
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

			System.out.println("����Ա�" + sql.toString());
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

	// ��ȡ��ѵĵ���ID
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

	// ��ȡ�ȶ��ظ����������Ϣ��ֵ
	public synchronized ArrayList getPageDataN() {

		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// ���ø���վ����������
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

	// ��ȡ�ȶ��ظ�������Ϣ��ֵ(������)
	public synchronized ArrayList getPageDataN(String con) {

		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// ���ø���վ����������
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
	 * ����վ���������ģ��ͳ����վ���������Ϣģ��ʱ--������վ�����������Ϣ
	 * 
	 * @param start
	 * @param id
	 *            :ҳ���ѯվ���Ψһ��ʶ
	 * @return list:վ����Ϣ��Ϣ
	 */

	public synchronized ArrayList getPageDataModelzdcbglxx(int start, String id) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// ���ø���վ����������
		try {
			sql = "SELECT CB.ZDID,CB.ZDNAME,CB.SHI,CB.XIAN,CB.XIAOQU FROM CBZD CB "
					+ "WHERE CB.ID = '" + id + "'";
			System.out.println("����վ���������ģ��ͳ����վ���������Ϣģ��ʱ--������վ�����������Ϣ:" + sql);
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
		// ���ø���վ����������
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
			System.out.println("�Ա������������ģ��ʱ--������վ�����������Ϣ:" + sql);
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
