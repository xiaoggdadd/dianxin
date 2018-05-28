package com.noki.zdqxkz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.CTime;
import com.noki.zdqxkz.javabean.XxxgBean;
import com.noki.zdqxkz.javabean.Zdqxkz;
import com.noki.zdqxkz.util.ZdqxkzUtil;
import com.ptac.app.inportuserzibaodian.util.Format;
import com.sun.xml.internal.bind.v2.TODO;

public class ShiQuery {
	// 市审核
	public synchronized List<Zdqxkz> seachData(String bean, String loginId) {
		List<Zdqxkz> retList = new ArrayList<Zdqxkz>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		String fzzdstr = "";
		try {
			// fzzdstr = getFuzeZdid(db,loginId);
			String sql = "  SELECT RNDIQU(ZD.SHI) SHI,RNDIQU(ZD.XIAN) XIAN,RNDIQU(ZD.XIAOQU) XIAOQU,"
					+ " ZD.JZNAME,ZD.ID,KZ.SHISHBZ,TO_CHAR(KZ.SHISHSJ,'yyyy-mm-dd hh24:mi:ss') SHISHSJ,KZ.ID AS IID,KZ.QXLY,KZ.SHENGSHBZ,KZ.SHENGSHR,KZ.DBID," +
					" (CASE WHEN KZ.BGDJ='1' THEN 'A' WHEN KZ.BGDJ='2' THEN 'B' WHEN KZ.BGDJ='3' THEN 'C' WHEN KZ.BGDJ='4' THEN 'D' END)DJ,KZ.NOREASON "
					+ " FROM ZHANDIAN ZD, QSKZ KZ  WHERE ZD.ID=KZ.ZDID AND KZ.QXTJBZ='1' "
					+ bean
					+ " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
					+ loginId + "'))) ORDER BY ZD.JZNAME";
			System.out.println("市审核sql：" + sql);
			db.connectDb();
			rs = db.queryAll(sql);

			while (rs.next()) {
				Zdqxkz ret = new Zdqxkz();
				ret.setShi(rs.getString("SHI"));
				ret.setXian(rs.getString("XIAN"));
				ret.setXiaoqu(rs.getString("XIAOQU"));
				ret.setId(rs.getString("ID"));
				ret.setShishsj(rs.getString("SHISHSJ"));// 市审核时间
				ret.setShishbz(rs.getString("SHISHBZ"));// 市审核标志
				ret.setZdname(rs.getString("JZNAME"));
				ret.setQskzid(rs.getString("IID"));
				ret.setQxly(rs.getString("QXLY"));
				ret.setShengshbz(rs.getString("SHENGSHBZ"));
				ret.setDbid(rs.getString("DBID"));
				ret.setBghs(rs.getString("DJ")!=null?rs.getString("DJ"):"");
				ret.setNoreason(rs.getString("NOREASON")!=null?rs.getString("NOREASON"):"");
				ret.setShengshr(rs.getString("SHENGSHR")!=null?rs.getString("SHENGSHR"):"");
				retList.add(ret);
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
		return retList;
	}

	// 省审核
	public synchronized List<Zdqxkz> seachSheng(String bean, String loginId) {
		List<Zdqxkz> retList = new ArrayList<Zdqxkz>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		String fzzdstr = "";
		try {
			// fzzdstr = getFuzeZdid(db,loginId);
			String sql = "  SELECT RNDIQU(ZD.SHI) SHI,RNDIQU(ZD.XIAN) XIAN,RNDIQU(ZD.XIAOQU) XIAOQU,"
					+ " ZD.JZNAME,ZD.ID,KZ.SHENGSHBZ,TO_CHAR(KZ.SHENGSHSJ,'yyyy-mm-dd hh24:mi:ss') SHENGSHSJ,KZ.ID AS IID,(CASE WHEN KZ.BGDJ='1' THEN 'A' WHEN KZ.BGDJ='2' THEN 'B' WHEN KZ.BGDJ='3' THEN 'C' WHEN KZ.BGDJ='4' THEN 'D' END)DJ,KZ.DBID,KZ.NOREASON "
					+ " FROM ZHANDIAN ZD, QSKZ KZ  WHERE ZD.ID=KZ.ZDID "
					+ bean
					+ " AND KZ.QXPDBZ='2' AND KZ.SHISHBZ='1' AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
					+ loginId + "'))) ORDER BY ZD.JZNAME";
			System.out.println("省审核sql：" + sql);
			db.connectDb();
			rs = db.queryAll(sql);

			while (rs.next()) {
				Zdqxkz ret = new Zdqxkz();
				ret.setShi(rs.getString("SHI"));
				ret.setXian(rs.getString("XIAN"));
				ret.setXiaoqu(rs.getString("XIAOQU"));
				ret.setId(rs.getString("ID"));
				ret.setShengshsj(rs.getString("SHENGSHSJ"));// 市审核时间
				ret.setShengshbz(rs.getString("SHENGSHBZ"));// 市审核标志
				ret.setZdname(rs.getString("JZNAME"));
				ret.setQskzid(rs.getString("IID"));
				ret.setDbid(rs.getString("DBID"));
				ret.setBghs(rs.getString("DJ")!=null?rs.getString("DJ"):"");
				ret.setNoreason(rs.getString("NOREASON")!=null?rs.getString("NOREASON"):"");
				retList.add(ret);
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
		return retList;
	}

	// 省审核
	public synchronized List<Zdqxkz> seachcxxx(String bean, String loginId) {
		List<Zdqxkz> retList = new ArrayList<Zdqxkz>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		String fzzdstr = "";
		try {
			// fzzdstr = getFuzeZdid(db,loginId);
			String sql = "  SELECT (CASE WHEN ZD.SHI IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) ELSE ''  END) SHI,"
					+ " (CASE WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END) XIAN,"
					+ " (CASE  WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END) XIAOQU,"
					+ " ZD.JZNAME,ZD.ID,KZ.SHENGSHBZ,TO_CHAR(KZ.SHENGSHSJ,'yyyy-mm-dd hh24:mi:ss') SHENGSHSJ,TO_CHAR(KZ.SHISHSJ,'yyyy-mm-dd hh24:mi:ss') SHISHSJ,KZ.SHISHBZ,KZ.ID AS IID,KZ.QXLY,KZ.DBID,(case when kz.bgdj=1 then 'A' WHEN KZ.BGDJ=2 THEN 'B' WHEN KZ.BGDJ='3' THEN 'C' WHEN KZ.BGDJ='4' THEN 'D' END)DJ,KZ.NOREASON  "
					+ " FROM ZHANDIAN ZD, QSKZ KZ  WHERE ZD.ID=KZ.ZDID"
					+ bean
					+ "  AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
					+ loginId + "'))) ORDER BY ZD.JZNAME";
			System.out.println("省审核sql：" + sql);
			db.connectDb();
			rs = db.queryAll(sql);

			while (rs.next()) {
				Zdqxkz ret = new Zdqxkz();
				ret.setShi(rs.getString("SHI"));
				ret.setXian(rs.getString("XIAN"));
				ret.setXiaoqu(rs.getString("XIAOQU"));
				ret.setId(rs.getString("ID"));
				ret.setShengshsj(rs.getString("SHENGSHSJ"));// 省审核时间
				ret.setShengshbz(rs.getString("SHENGSHBZ"));// 省审核标志
				ret.setShishbz(rs.getString("SHISHBZ"));// 市审核标志
				ret.setShishsj(rs.getString("SHISHSJ"));// 市审核时间
				ret.setZdname(rs.getString("JZNAME"));
				ret.setQskzid(rs.getString("IID"));
				ret.setQxly(rs.getString("QXLY"));
				ret.setDbid(rs.getString("DBID"));
				ret.setBghs(rs.getString("DJ"));
				ret.setNoreason(rs.getString("noreason")!=null?rs.getString("noreason"):"");
				retList.add(ret);
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
		return retList;
	}

	public synchronized List<Zdqxkz> seachDatasshi(String bean) {
		List<Zdqxkz> retList = new ArrayList<Zdqxkz>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		String fzzdstr = "";
		try {
			// fzzdstr = getFuzeZdid(db,loginId);
			String sql = " SELECT KZ.ID,KZ.NEWBGSIGN, KZ.OLDBGSIGN,KZ.NEWDIANFEI, KZ.OLDDIANFEI,KZ.NEWEDHDL,KZ.OLDEDHDL,KZ.NEWJLFH,KZ.OLDJLFH,KZ.NEWZLFH,KZ.OLDZLFH,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWPROPERTY AND TYPE = 'ZDSX') AS NEWPROPERTY,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDPROPERTY AND TYPE = 'ZDSX') AS OLDPROPERTY,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE=KZ.NEWSTATIONTYPE AND TYPE='stationtype')AS NEWSTATIONTYPE,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE=KZ.OLDSTATIONTYPE AND TYPE='stationtype')AS OLDSTATIONTYPE,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWYFLX AND T.TYPE = 'FWLX') AS NEWYFLX,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDYFLX AND T.TYPE = 'FWLX') AS OLDYFLX,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWGXXX AND T.TYPE = 'gxxx') AS NEWGXXX,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDGXXX AND T.TYPE = 'gxxx') AS OLDGXXX,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWGDFS AND T.TYPE = 'GDFS') AS NEWGDFS,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDGDFS AND T.TYPE = 'GDFS') AS OLDGDFS,"
					+ "   KZ.NEWZGD,KZ.OLDZGD,KZ.NEWAREA,KZ.OLDAREA,KZ.OLDQSDB,KZ.NEWQSDB,KZ.NEWQYZT,KZ.OLDQYZT,KZ.NEWG2, KZ.OLDG2,"
					+ "   KZ.NEWG3,KZ.OLDG3,KZ.NEWZPSL,KZ.OLDZPSL,KZ.NEWZSSL,KZ.OLDZSSL,"
					+ "    (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWCHANGJIA AND T.TYPE = 'cj') AS NEWCHANGJIA,"
					+ "   (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDCHANGJIA AND T.TYPE = 'cj') AS OLDCHANGJIA,"
					+ "   (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWJZLX AND T.TYPE = 'jzlx2') AS NEWJZLX,"
					+ "   (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDJZLX AND T.TYPE = 'jzlx2') AS OLDJZLX,"
					+ "    (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWSHEBEI AND T.TYPE = 'sblx') AS NEWSHEBEI,"
					+ "   (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDSHEBEI AND T.TYPE = 'sblx') AS OLDSHEBEI,"
					+ " (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWDFZFLX AND T.TYPE = 'dfzflx') AS NEWDFZFLX,"
					+ " (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDDFZFLX AND T.TYPE = 'dfzflx') AS OLDDFZFLX,"
					+ "    KZ.NEWBBU,KZ.OLDBBU, KZ.NEWRRU,KZ.OLDRRU, KZ.NEWYDSHEBEI, KZ.OLDYDSHEBEI,KZ.NEWGXGWSL,KZ.OLDGXGWSL,KZ.XGSM,KZ.XGYJ,KZ.FJMC,KZ.DBDS FROM QSKZ KZ WHERE KZ.ID='"
					+ bean + "'";
			System.out.println("站点字段权限控制连接sql：" + sql);
			db.connectDb();
			rs = db.queryAll(sql);

			while (rs.next()) {
				Zdqxkz ret = new Zdqxkz();
				ret.setId(rs.getString("ID"));
				ret.setNewbbu(rs.getString("NEWBBU"));
				ret.setOldbbu(rs.getString("OLDBBU"));
				ret.setNewrru(rs.getString("NEWRRU"));
				ret.setOldrru(rs.getString("OLDRRU"));
				ret.setNewydshebei(rs.getString("NEWYDSHEBEI"));
				ret.setOldydshebei(rs.getString("OLDYDSHEBEI"));
				ret.setNewgxgwsl(rs.getString("NEWGXGWSL"));
				ret.setOldgxgwsl(rs.getString("OLDGXGWSL"));

				ret.setNewchangjia(rs.getString("NEWCHANGJIA"));
				ret.setOldchangjia(rs.getString("OLDCHANGJIA"));
				ret.setNewjzlx(rs.getString("NEWJZLX"));
				ret.setOldjzlx(rs.getString("OLDJZLX"));
				ret.setNewshebei(rs.getString("NEWSHEBEI"));
				ret.setOldshebei(rs.getString("OLDSHEBEI"));
				ret.setOldqsdbdl(rs.getString("OLDQSDB") != null ? rs
						.getString("OLDQSDB") : "");
				ret.setNewqsdbdl(rs.getString("NEWQSDB") != null ? rs
						.getString("NEWQSDB") : "");

				ret.setNewbgsign(rs.getString("NEWBGSIGN"));
				ret.setOldbgsign(rs.getString("OLDBGSIGN"));
				ret.setNewdianfei(rs.getString("NEWDIANFEI"));
				ret.setOlddianfei(rs.getString("OLDDIANFEI"));
				ret.setNewedhdl(rs.getString("NEWEDHDL"));
				ret.setOldedhdl(rs.getString("OLDEDHDL"));
				ret.setNewjlfh(rs.getString("NEWJLFH"));
				ret.setOldjlfh(rs.getString("OLDJLFH"));
				ret.setNewzlfh(rs.getString("NEWZLFH"));
				ret.setOldzlfh(rs.getString("OLDZLFH"));
				ret.setNewproperty(rs.getString("NEWPROPERTY"));
				ret.setOldproperty(rs.getString("OLDPROPERTY"));
				ret.setNewstationtype(rs.getString("NEWSTATIONTYPE"));
				ret.setOldstationtype(rs.getString("OLDSTATIONTYPE"));
				ret.setNewyflx(rs.getString("NEWYFLX"));
				ret.setOldyflx(rs.getString("OLDYFLX"));
				ret.setNewgxxx(rs.getString("NEWGXXX"));
				ret.setOldgxxx(rs.getString("OLDGXXX"));
				ret.setNewgdfs(rs.getString("NEWGDFS"));
				ret.setOldgdfs(rs.getString("OLDGDFS"));
				// "   KZ.NEWZGD,KZ.OLDZGD,KZ.NEWAREA,KZ.OLDAREA,KZ.NEWQYZT,KZ.OLDQYZT,KZ.NEWG2, KZ.OLDG2,"
				// +
				// KZ.NEWG3,KZ.OLDG3,KZ.NEWZPSL,KZ.OLDZPSL,KZ.NEWZSSL,KZ.OLDZSSL,";
				ret.setNewzgd(rs.getString("NEWZGD"));
				ret.setOldzgd(rs.getString("OLDZGD"));
				ret.setNewarea(rs.getString("NEWAREA"));
				ret.setOldarea(rs.getString("OLDAREA"));
				ret.setNewqyzt(rs.getString("NEWQYZT"));
				ret.setOldqyzt(rs.getString("OLDQYZT"));
				ret.setNewg2(rs.getString("NEWG2"));
				ret.setOldg2(rs.getString("OLDG2"));
				ret.setNewg3(rs.getString("NEWG3"));
				ret.setOldg3(rs.getString("OLDG3"));
				ret.setNewzpsl(rs.getString("NEWZPSL"));
				ret.setOldzpsl(rs.getString("OLDZPSL"));
				ret.setNewzssl(rs.getString("NEWZSSL"));
				ret.setOldzssl(rs.getString("OLDZSSL"));
				ret.setXgsm(rs.getString("XGSM"));
				ret.setXgyj(rs.getString("XGYJ"));
				ret.setFjmc(rs.getString("FJMC") != null ? rs.getString("FJMC")
						: "");
				ret.setDbds(rs.getString("DBDS")!=null?rs.getString("DBDS"):""); 
				ret.setNewdfzflx(rs.getString("NEWDFZFLX")); 
				ret.setOlddfzflx(rs.getString("OLDDFZFLX"));
				retList.add(ret);
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
		return retList;
	}

	public synchronized List<Zdqxkz> seachDatassh(String bean) {
		List<Zdqxkz> retList = new ArrayList<Zdqxkz>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		String fzzdstr = "";
		try {
			// fzzdstr = getFuzeZdid(db,loginId);
			String sql = " SELECT KZ.ID,KZ.NEWBGSIGN, KZ.OLDBGSIGN,KZ.NEWDIANFEI, KZ.OLDDIANFEI,KZ.NEWEDHDL,KZ.OLDEDHDL,KZ.NEWJLFH,KZ.OLDJLFH,KZ.NEWZLFH,KZ.OLDZLFH,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWPROPERTY AND TYPE = 'ZDSX') AS NEWPROPERTY,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDPROPERTY AND TYPE = 'ZDSX') AS OLDPROPERTY,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE=KZ.NEWSTATIONTYPE AND TYPE='stationtype')AS NEWSTATIONTYPE,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE=KZ.OLDSTATIONTYPE AND TYPE='stationtype')AS OLDSTATIONTYPE,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWYFLX AND T.TYPE = 'FWLX') AS NEWYFLX,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDYFLX AND T.TYPE = 'FWLX') AS OLDYFLX,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWGXXX AND T.TYPE = 'gxxx') AS NEWGXXX,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDGXXX AND T.TYPE = 'gxxx') AS OLDGXXX,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWGDFS AND T.TYPE = 'GDFS') AS NEWGDFS,"
					+ "  (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDGDFS AND T.TYPE = 'GDFS') AS OLDGDFS,"
					+ "   KZ.NEWZGD,KZ.OLDZGD,KZ.NEWAREA,KZ.OLDAREA,KZ.OLDQSDB,KZ.NEWQSDB,KZ.NEWQYZT,KZ.OLDQYZT,KZ.NEWG2, KZ.OLDG2,"
					+ "   KZ.NEWG3,KZ.OLDG3,KZ.NEWZPSL,KZ.OLDZPSL,KZ.NEWZSSL,KZ.OLDZSSL,"
					+ "    (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWCHANGJIA AND T.TYPE = 'cj') AS NEWCHANGJIA,"
					+ "   (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDCHANGJIA AND T.TYPE = 'cj') AS OLDCHANGJIA,"
					+ "   (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWJZLX AND T.TYPE = 'jzlx2') AS NEWJZLX,"
					+ "   (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDJZLX AND T.TYPE = 'jzlx2') AS OLDJZLX,"
					+ "    (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWSHEBEI AND T.TYPE = 'sblx') AS NEWSHEBEI,"
					+ "   (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDSHEBEI AND T.TYPE = 'sblx') AS OLDSHEBEI,"
					+ " (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.NEWDFZFLX AND T.TYPE = 'dfzflx') AS NEWDFZFLX,"
					+ " (SELECT T.NAME FROM INDEXS T WHERE T.CODE = KZ.OLDDFZFLX AND T.TYPE = 'dfzflx') AS OLDDFZFLX,"
					+ "    KZ.NEWBBU,KZ.OLDBBU, KZ.NEWRRU,KZ.OLDRRU, KZ.NEWYDSHEBEI, KZ.OLDYDSHEBEI,KZ.NEWGXGWSL,KZ.OLDGXGWSL,KZ.XGSM,KZ.XGYJ,KZ.FJMC,KZ.ZDID,KZ.DBID,KZ.DBDS,KZ.SHISHBZ,KZ.BFTG,KZ.BFBTG FROM QSKZ KZ WHERE KZ.ID='"
					+ bean + "'";
			System.out.println("站点字段权限控制连接sql：" + sql);
			db.connectDb();
			rs = db.queryAll(sql);

			while (rs.next()) {
				Zdqxkz ret = new Zdqxkz();
				ret.setId(rs.getString("ID"));
				ret.setNewbbu(rs.getString("NEWBBU"));
				ret.setOldbbu(rs.getString("OLDBBU"));
				ret.setNewrru(rs.getString("NEWRRU"));
				ret.setOldrru(rs.getString("OLDRRU"));
				ret.setNewydshebei(rs.getString("NEWYDSHEBEI"));
				ret.setOldydshebei(rs.getString("OLDYDSHEBEI"));
				ret.setNewgxgwsl(rs.getString("NEWGXGWSL"));
				ret.setOldgxgwsl(rs.getString("OLDGXGWSL"));

				ret.setNewchangjia(rs.getString("NEWCHANGJIA"));
				ret.setOldchangjia(rs.getString("OLDCHANGJIA"));
				ret.setNewjzlx(rs.getString("NEWJZLX"));
				ret.setOldjzlx(rs.getString("OLDJZLX"));
				ret.setNewshebei(rs.getString("NEWSHEBEI"));
				ret.setOldshebei(rs.getString("OLDSHEBEI"));
				ret.setOldqsdbdl(rs.getString("OLDQSDB") != null ? rs
						.getString("OLDQSDB") : "");
				ret.setNewqsdbdl(rs.getString("NEWQSDB") != null ? rs
						.getString("NEWQSDB") : "");

				ret.setNewbgsign(rs.getString("NEWBGSIGN"));
				ret.setOldbgsign(rs.getString("OLDBGSIGN"));
				ret.setNewdianfei(rs.getString("NEWDIANFEI"));
				ret.setOlddianfei(rs.getString("OLDDIANFEI"));
				ret.setNewedhdl(rs.getString("NEWEDHDL"));
				ret.setOldedhdl(rs.getString("OLDEDHDL"));
				ret.setNewjlfh(rs.getString("NEWJLFH"));
				ret.setOldjlfh(rs.getString("OLDJLFH"));
				ret.setNewzlfh(rs.getString("NEWZLFH"));
				ret.setOldzlfh(rs.getString("OLDZLFH"));
				ret.setNewproperty(rs.getString("NEWPROPERTY"));
				ret.setOldproperty(rs.getString("OLDPROPERTY"));
				ret.setNewstationtype(rs.getString("NEWSTATIONTYPE"));
				ret.setOldstationtype(rs.getString("OLDSTATIONTYPE"));
				ret.setNewyflx(rs.getString("NEWYFLX"));
				ret.setOldyflx(rs.getString("OLDYFLX"));
				ret.setNewgxxx(rs.getString("NEWGXXX"));
				ret.setOldgxxx(rs.getString("OLDGXXX"));
				ret.setNewgdfs(rs.getString("NEWGDFS"));
				ret.setOldgdfs(rs.getString("OLDGDFS"));
				// "   KZ.NEWZGD,KZ.OLDZGD,KZ.NEWAREA,KZ.OLDAREA,KZ.NEWQYZT,KZ.OLDQYZT,KZ.NEWG2, KZ.OLDG2,"
				// +
				// KZ.NEWG3,KZ.OLDG3,KZ.NEWZPSL,KZ.OLDZPSL,KZ.NEWZSSL,KZ.OLDZSSL,";
				ret.setNewzgd(rs.getString("NEWZGD"));
				ret.setOldzgd(rs.getString("OLDZGD"));
				ret.setNewarea(rs.getString("NEWAREA"));
				ret.setOldarea(rs.getString("OLDAREA"));
				ret.setNewqyzt(rs.getString("NEWQYZT"));
				ret.setOldqyzt(rs.getString("OLDQYZT"));
				ret.setNewg2(rs.getString("NEWG2"));
				ret.setOldg2(rs.getString("OLDG2"));
				ret.setNewg3(rs.getString("NEWG3"));
				ret.setOldg3(rs.getString("OLDG3"));
				ret.setNewzpsl(rs.getString("NEWZPSL"));
				ret.setOldzpsl(rs.getString("OLDZPSL"));
				ret.setNewzssl(rs.getString("NEWZSSL"));
				ret.setOldzssl(rs.getString("OLDZSSL"));
				ret.setXgsm(rs.getString("XGSM"));
				ret.setXgyj(rs.getString("XGYJ"));
				ret.setFjmc(rs.getString("FJMC") != null ? rs.getString("FJMC")
						: "");
				ret.setZdid(rs.getString("ZDID")!=null?rs.getString("ZDID"):"");
				ret.setDbid(rs.getString("DBID")!=null?rs.getString("DBID"):"");
				ret.setDbds(rs.getString("DBDS")!=null?rs.getString("DBDS"):""); 
				ret.setNewdfzflx(rs.getString("NEWDFZFLX")); 
				ret.setOlddfzflx(rs.getString("OLDDFZFLX"));
				ret.setShishbz(rs.getString("SHISHBZ")!=null?rs.getString("SHISHBZ"):"0");
				ret.setBftg(rs.getString("BFTG")!=null?rs.getString("BFTG"):"");
				ret.setBfbtg(rs.getString("BFBTG")!=null?rs.getString("BFBTG"):"");
				retList.add(ret);
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
		return retList;
	}
//查询信息修改表的最新数据信息
	public synchronized List<Zdqxkz> seachShi(String bean, String id) {
		List<Zdqxkz> retList = new ArrayList<Zdqxkz>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		String fzzdstr = "";
		try {
			// fzzdstr = getFuzeZdid(db,loginId);
			String sql = " SELECT KZ.ZDID,KZ.NEWBGSIGN, KZ.NEWDIANFEI, KZ.NEWEDHDL, KZ.NEWJLFH, KZ.NEWZLFH,KZ.NEWPROPERTY,KZ.NEWQSDB,KZ.OLDQSDB, "
					+ " KZ.NEWSTATIONTYPE,KZ.NEWYFLX, KZ.NEWGXXX, KZ.NEWGDFS, KZ.NEWZGD,KZ.NEWAREA, KZ.NEWQYZT,KZ.NEWG2, KZ.NEWG3,"
					+ " KZ.NEWZPSL,KZ.NEWZSSL, KZ.NEWCHANGJIA,KZ.NEWJZLX,KZ.NEWSHEBEI,KZ.NEWBBU,KZ.NEWRRU,KZ.NEWYDSHEBEI, KZ.NEWGXGWSL," 
					+ " KZ.DBID,KZ.DBDS,KZ.XGBZW,KZ.NEWDFZFLX,KZ.QXPDBZ,KZ.ID KZID,KZ.FLGG,KZ.BFTG,KZ.BFBTG  "
					+ " FROM QSKZ KZ WHERE KZ.ZDID IN ("
					+ bean
					+ ") AND KZ.ID in(" + id + ")";
			System.out.println("市审核查询新信息sql：" + sql);
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				Zdqxkz ret = new Zdqxkz();
				ret.setZdid(rs.getString("ZDID") != null ? rs.getString("ZDID")
						: "");
				ret.setNewbgsign(rs.getString("NEWBGSIGN") != null ? rs
						.getString("NEWBGSIGN") : "0");
				ret.setNewdianfei(rs.getString("NEWDIANFEI") != null ? rs
						.getString("NEWDIANFEI") : "0");
				ret.setNewedhdl(rs.getString("NEWEDHDL") != null ? rs
						.getString("NEWEDHDL") : "0");
				ret.setNewjlfh(rs.getString("NEWJLFH") != null ? rs
						.getString("NEWJLFH") : "0");
				ret.setNewzlfh(rs.getString("NEWZLFH") != null ? rs
						.getString("NEWZLFH") : "0");
				ret.setNewproperty(rs.getString("NEWPROPERTY") != null ? rs
						.getString("NEWPROPERTY") : "");// 站点属性
				ret.setOldqsdb(rs.getString("OLDQSDB") != null ? rs
						.getString("OLDQSDB") : "0");// 旧的省定标(不含空调)
				ret.setNewqsdbdl(rs.getString("NEWQSDB") != null ? rs
						.getString("NEWQSDB") : "0");// 新的省定标(不含空调)

				ret.setNewg2(rs.getString("NEWG2") != null ? rs
						.getString("NEWG2") : "0");
				ret.setNewg3(rs.getString("NEWG3") != null ? rs
						.getString("NEWG3") : "0");
				ret.setNewzpsl(rs.getString("NEWZPSL") != null ? rs
						.getString("NEWZPSL") : "0");
				ret.setNewzssl(rs.getString("NEWZSSL") != null ? rs
						.getString("NEWZSSL") : "0");
				ret.setNewchangjia(rs.getString("NEWCHANGJIA") != null ? rs
						.getString("NEWCHANGJIA") : "");
				ret.setNewjzlx(rs.getString("NEWJZLX") != null ? rs
						.getString("NEWJZLX") : "");
				ret.setNewshebei(rs.getString("NEWSHEBEI") != null ? rs
						.getString("NEWSHEBEI") : "");
				ret.setNewbbu(rs.getString("NEWBBU") != null ? rs
						.getString("NEWBBU") : "0");
				ret.setNewrru(rs.getString("NEWRRU") != null ? rs
						.getString("NEWRRU") : "0");
				ret.setNewydshebei(rs.getString("NEWYDSHEBEI") != null ? rs
						.getString("NEWYDSHEBEI") : "0");
				ret.setNewgxgwsl(rs.getString("NEWGXGWSL") != null ? rs
						.getString("NEWGXGWSL") : "0");
				ret
						.setNewstationtype(rs.getString("NEWSTATIONTYPE") != null ? rs
								.getString("NEWSTATIONTYPE")
								: "");
				ret.setNewyflx(rs.getString("NEWYFLX") != null ? rs
						.getString("NEWYFLX") : "");
				ret.setNewgxxx(rs.getString("NEWGXXX") != null ? rs
						.getString("NEWGXXX") : "");
				ret.setNewgdfs(rs.getString("NEWGDFS") != null ? rs
						.getString("NEWGDFS") : "");
				ret.setNewzgd(rs.getString("NEWZGD") != null ? rs
						.getString("NEWZGD") : "0");
				ret.setNewarea(rs.getString("NEWAREA") != null ? rs
						.getString("NEWAREA") : "0");
				ret.setNewqyzt(rs.getString("NEWQYZT") != null ? rs
						.getString("NEWQYZT") : "0");
				ret.setDbid(rs.getString("DBID")!=null?rs.getString("DBID"):"");
				ret.setDbds(rs.getString("DBDS")!=null?rs.getString("DBDS"):"");
				ret.setDsbzw(rs.getString("XGBZW")!=null?rs.getString("XGBZW"):"");
				ret.setNewdfzflx(rs.getString("NEWDFZFLX")!=null?rs.getString("NEWDFZFLX"):"");
				ret.setQxpdbz(rs.getString("QXPDBZ"));
				ret.setId(rs.getString("KZID"));
				ret.setFlgg(rs.getString("FLGG") != null ? rs.getString("FLGG") : "");
				ret.setBftg(rs.getString("BFTG") != null ? rs.getString("BFTG") : "");
				ret.setBfbtg(rs.getString("BFBTG")!= null ? rs.getString("BFBTG") : "");
				retList.add(ret);
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
		return retList;
	}

	public synchronized List<Zdqxkz> QXserchShi(String bean, String id) {
		List<Zdqxkz> retList = new ArrayList<Zdqxkz>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			String sql = " SELECT q.ZDID,q.OLDBGSIGN, q.OLDDIANFEI, Q.OLDEDHDL, Q.OLDJLFH, Q.OLDZLFH,Q.OLDDFZFLX,Q.DBID,Q.OLDDBDS,Q.OLDXGBZW,Q.SHENGSHR,Q.SHENGSHBZ,Q.ID,Q.OLDQSDBDL,Q.OLDQSDB FROM QSKZ q WHERE q.zdid IN ("
					+ bean + ") AND Q.ID IN (" + id + ") ";
			System.out.println("市审核查询新信息sql：" + sql);
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				Zdqxkz ret = new Zdqxkz();
				ret.setZdid(rs.getString("ZDID") != null ? rs.getString("ZDID")
						: "");
				ret.setOldbgsign(rs.getString("OLDBGSIGN") != null ? rs
						.getString("OLDBGSIGN") : "0");
				ret.setOlddianfei(rs.getString("OLDDIANFEI") != null ? rs
						.getString("OLDDIANFEI") : "0");
				ret.setOldedhdl(rs.getString("OLDEDHDL") != null ? rs
						.getString("OLDEDHDL") : "0");
				ret.setOldjlfh(rs.getString("OLDJLFH") != null ? rs
						.getString("OLDJLFH") : "0");
				ret.setOldzlfh(rs.getString("OLDZLFH") != null ? rs
						.getString("OLDZLFH") : "0");
				ret.setOlddfzflx(rs.getString("OLDDFZFLX") != null ? rs
						.getString("OLDDFZFLX") : "");
				ret.setDbid(rs.getString("DBID") != null ? rs
						.getString("DBID") : "");
				ret.setOlddbds(rs.getString("OLDDBDS") != null ? rs
						.getString("OLDDBDS") : "");
				ret.setOldxgbzw(rs.getString("OLDXGBZW") != null ? rs
						.getString("OLDXGBZW") : "");
				ret.setShengshr(rs.getString("SHENGSHR") != null ? rs.getString("SHENGSHR") : "");
				ret.setShengshbz(rs.getString("SHENGSHBZ")  != null ? rs.getString("SHENGSHBZ") : "");
				ret.setId(rs.getString("ID"));
				ret.setOldqsdbdl(rs.getString("OLDQSDBDL") != null ? rs.getString("OLDQSDBDL") : "");
				ret.setOldqsdb(rs.getString("OLDQSDB") != null ? rs.getString("OLDQSDB") : "");
				retList.add(ret);
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
		return retList;
	}
//查询信息修改申请时的老数据
	public synchronized List<Zdqxkz> old(String bean, String idd) {
		List<Zdqxkz> retList = new ArrayList<Zdqxkz>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			String sql = " SELECT q.zdid,q.oldproperty, q.oldstationtype, Q.Oldyflx, Q.Oldgxxx, Q.Oldgdfs,q.oldzgd,q.oldarea,q.oldqyzt,q.oldg2,q.oldg3,"
					+ "q.oldzpsl,q.oldzssl,q.oldchangjia,q.oldjzlx,q.oldshebei,q.oldbbu,q.oldrru,q.oldydshebei,q.oldgxgwsl,q.oldqsdbdl,q.oldqsdb,q.id qid FROM QSKZ q WHERE q.zdid IN ("
					+ bean + ") and q.id in (" + idd + ")";
			System.out.println("省级审核查询旧信息sql：" + sql);
			db.connectDb();
			rs = db.queryAll(sql);
			while (rs.next()) {
				Zdqxkz ret = new Zdqxkz();
				ret.setZdid(rs.getString("zdid") != null ? rs.getString("zdid")
						: "");
				ret.setOldproperty(rs.getString("oldproperty") != null ? rs
						.getString("oldproperty") : "");
				ret
						.setOldstationtype(rs.getString("oldstationtype") != null ? rs
								.getString("oldstationtype")
								: "");
				ret.setOldyflx(rs.getString("Oldyflx") != null ? rs
						.getString("Oldyflx") : "");
				ret.setOldgxxx(rs.getString("Oldgxxx") != null ? rs
						.getString("Oldgxxx") : "");
				ret.setOldgdfs(rs.getString("Oldgdfs") != null ? rs
						.getString("Oldgdfs") : "");
				ret.setOldzgd(rs.getString("oldzgd") != null ? rs
						.getString("oldzgd") : "0");
				ret.setOldarea(rs.getString("oldarea") != null ? rs
						.getString("oldarea") : "0");
				ret.setOldqyzt(rs.getString("oldqyzt") != null ? rs
						.getString("oldqyzt") : "");
				ret.setOldg2(rs.getString("oldg2") != null ? rs
						.getString("oldg2") : "0");
				ret.setOldg3(rs.getString("oldg3") != null ? rs
						.getString("oldg3") : "0");
				ret.setOldzpsl(rs.getString("oldzpsl") != null ? rs
						.getString("oldzpsl") : "0");
				ret.setOldzssl(rs.getString("oldzssl") != null ? rs
						.getString("oldzssl") : "0");
				ret.setOldchangjia(rs.getString("oldchangjia") != null ? rs
						.getString("oldchangjia") : "");
				ret.setOldjzlx(rs.getString("oldjzlx") != null ? rs
						.getString("oldjzlx") : "");
				ret.setOldshebei(rs.getString("oldshebei") != null ? rs
						.getString("oldshebei") : "");
				ret.setOldbbu(rs.getString("oldbbu") != null ? rs
						.getString("oldbbu") : "0");
				ret.setOldrru(rs.getString("oldrru") != null ? rs
						.getString("oldrru") : "0");
				ret.setOldydshebei(rs.getString("oldydshebei") != null ? rs
						.getString("oldydshebei") : "0");
				ret.setOldgxgwsl(rs.getString("oldgxgwsl") != null ? rs
						.getString("oldgxgwsl") : "0");
				ret.setOldqsdbdl(rs.getString("oldqsdbdl") != null ? rs
						.getString("oldqsdbdl") : "");
				ret.setOldqsdb(rs.getString("oldqsdb") != null ? rs
						.getString("oldqsdb") : "");
				ret.setId(rs.getString("qid") != null ? rs
						.getString("qid") : "");
				retList.add(ret);
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

		return retList;
	}
//信息修改取消审核时 把站点信息改为申请前的老数据
	public synchronized int fgzd(String id, List<Zdqxkz> ls, String loginName, String idd) {
		Connection connc = null;
		PreparedStatement ps =null;
		PreparedStatement ps1 = null;
		Statement st = null;
		
		int msg = 0;
		String sql = "UPDATE ZHANDIAN ZD SET ZD.PROPERTY=?,ZD.STATIONTYPE=?,ZD.GXXX=?,ZD.GDFS=?,ZD.ZGD=?,ZD.AREA=?,ZD.QYZT=? " 
			+ ", ZD.G2=?,ZD.G3=?,ZD.ZPSL=?,ZD.ZSSL=?,ZD.CHANGJIA=?,ZD.JZLX=?,ZD.SHEBEI=?,ZD.BBU=?,ZD.RRU=?,ZD.YDSHEBEI=?,ZD.GXGWSL=?,ZD.YFLX=?,ZD.QSDBDL=?,ZD.SCB=? WHERE ZD.ID=?";
		String sql1a = "UPDATE SCB S SET S.SCB=? WHERE S.ZDID=?";
		String sql1 = "update QSKZ c set c.SHENGSHBZ='0',c.SHENGSHR='" + loginName
		+ "',c.SHENGSHSJ=sysdate,c.QXLY=NULL,C.BFTG=NULL,C.BFBTG=NULL where  c.zdid in (" + id
		+ ") and c.id in (" + idd + ")";
		    DataBase db = new DataBase();
		    try {
		    	connc = db.getConnection();
		    	connc.setAutoCommit(false);
				ps = connc.prepareStatement(sql);
				ps1 = connc.prepareStatement(sql1a);
				st = connc.createStatement();
				if (ls != null) {
					for (Zdqxkz lst : ls) {
						ps.setString(1, lst.getOldproperty());
						ps.setString(2, lst.getOldstationtype());
						ps.setString(3, lst.getOldgxxx());
						ps.setString(4, lst.getOldgdfs());
						ps.setString(5, lst.getOldzgd());
						ps.setString(6, lst.getOldarea());
						ps.setString(7, lst.getOldqyzt());
						ps.setString(8, lst.getOldg2());
						ps.setString(9, lst.getOldg3());
						ps.setString(10, lst.getOldzpsl());
						ps.setString(11, lst.getOldzssl());
						ps.setString(12, lst.getOldchangjia());
						ps.setString(13, lst.getOldjzlx());
						ps.setString(14, lst.getOldshebei());
						ps.setString(15, lst.getOldbbu());
						ps.setString(16, lst.getOldrru());
						ps.setString(17, lst.getOldydshebei());
						ps.setString(18, lst.getOldgxgwsl());
						ps.setString(19, lst.getOldyflx());
						ps.setString(20, lst.getOldqsdbdl());
						ps.setString(21, lst.getOldqsdb());
						ps.setString(22, lst.getZdid());
						
						ps.addBatch();
						
						ps1.setString(1, lst.getOldqsdb());
						ps1.setString(2, lst.getZdid());
						
						ps1.addBatch();
					}
					ps.executeBatch();
					ps1.executeBatch();
				}
				st.executeUpdate(sql1);
				connc.commit();
				msg = 1;
			} catch (Exception e) {
				e.printStackTrace();
				try {
					db.rollback();
				} catch (DbException e1) {
					e1.printStackTrace();
				}
			}finally{
			try {
				connc.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.free(null, ps, null);
			db.free(null, ps1, null);
			db.free(null, st, connc);
			}
			
			

		return msg;
	}

	public synchronized List<Zdqxkz> seachSheng(String bean) {
		List<Zdqxkz> retList = new ArrayList<Zdqxkz>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		String fzzdstr = "";
		try {
			// fzzdstr = getFuzeZdid(db,loginId);
			String sql = " SELECT KZ.ZDID,KZ.NEWPROPERTY,KZ.NEWSTATIONTYPE, KZ.NEWYFLX,KZ.NEWGXXX,KZ.NEWGDFS,KZ.NEWZGD,KZ.NEWAREA,KZ.NEWQYZT,"
					+ " kz.newg2,kz.newg3,kz.newzpsl,kz.newzssl,kz.newchangjia,kz.newjzlx,kz.newshebei,kz.newbbu,kz.newrru,kz.newydshebei," 
					+ "kz.newgxgwsl,kz.newqsdb,kz.newjlfh,kz.newzlfh,KZ.FLGG,KZ.ID KZID "
					+ " FROM QSKZ KZ WHERE KZ.ID IN (" + bean + ")";

			System.out.println("省审核查询新信息插入数据库sql：" + sql);
			db.connectDb();
			rs = db.queryAll(sql);

			while (rs.next()) {
				Zdqxkz ret = new Zdqxkz();
				ret.setZdid(rs.getString("ZDID") != null ? rs.getString("ZDID")
						: "");
				ret.setNewg2(rs.getString("NEWG2") != null ? rs
						.getString("NEWG2") : "0");
				ret.setNewg3(rs.getString("NEWG3") != null ? rs
						.getString("NEWG3") : "0");
				ret.setNewzpsl(rs.getString("NEWZPSL") != null ? rs
						.getString("NEWZPSL") : "0");
				ret.setNewzssl(rs.getString("NEWZSSL") != null ? rs
						.getString("NEWZSSL") : "0");
				ret.setNewchangjia(rs.getString("NEWCHANGJIA") != null ? rs
						.getString("NEWCHANGJIA") : "");
				ret.setNewjzlx(rs.getString("NEWJZLX") != null ? rs
						.getString("NEWJZLX") : "");
				ret.setNewshebei(rs.getString("NEWSHEBEI") != null ? rs
						.getString("NEWSHEBEI") : "");
				ret.setNewbbu(rs.getString("NEWBBU") != null ? rs
						.getString("NEWBBU") : "0");
				ret.setNewrru(rs.getString("NEWRRU") != null ? rs
						.getString("NEWRRU") : "0");
				ret.setNewydshebei(rs.getString("NEWYDSHEBEI") != null ? rs
						.getString("NEWYDSHEBEI") : "0");
				ret.setNewgxgwsl(rs.getString("NEWGXGWSL") != null ? rs
						.getString("NEWGXGWSL") : "0");

				ret.setNewproperty(rs.getString("NEWPROPERTY") != null ? rs
						.getString("NEWPROPERTY") : "");
				ret
						.setNewstationtype(rs.getString("NEWSTATIONTYPE") != null ? rs
								.getString("NEWSTATIONTYPE")
								: "");
				ret.setNewyflx(rs.getString("NEWYFLX") != null ? rs
						.getString("NEWYFLX") : "");
				ret.setNewgxxx(rs.getString("NEWGXXX") != null ? rs
						.getString("NEWGXXX") : "");
				ret.setNewgdfs(rs.getString("NEWGDFS") != null ? rs
						.getString("NEWGDFS") : "");
				ret.setNewzgd(rs.getString("NEWZGD") != null ? rs
						.getString("NEWZGD") : "0");
				ret.setNewarea(rs.getString("NEWAREA") != null ? rs
						.getString("NEWAREA") : "0");
				ret.setNewqyzt(rs.getString("NEWQYZT") != null ? rs
						.getString("NEWQYZT") : "0");
				ret.setNewqsdbdl(rs.getString("NEWQSDB") != null ? rs
						.getString("NEWQSDB") : "");
				ret.setNewjlfh(rs.getString("NEWJLFH") != null ? rs
						.getString("NEWJLFH") : "0");
				ret.setNewzlfh(rs.getString("NEWZLFH") != null ? rs
						.getString("NEWZLFH") : "0");
				ret.setFlgg(rs.getString("FLGG") != null ? rs
						.getString("FLGG"):"");
				ret.setId(rs.getString("KZID") != null ? rs
						.getString("KZID"):"");
				retList.add(ret);
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
		return retList;
	}

	public synchronized int SHIUpdatebtg(String id,List<Zdqxkz> ls, String loginName,
			String cause, String idd) {
		Connection connc = null;
		PreparedStatement ps = null;
		PreparedStatement psa = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		Statement st = null;
		int msg = 0;
		String sql = " UPDATE ZHANDIAN ZD SET ZD.BGSIGN=?,ZD.DIANFEI=?,ZD.EDHDL=?,ZD.JLFH=?,ZD.ZLFH=?,ZD.MEMO=?,ZD.QSDBDL=?,ZD.SCB=? WHERE ZD.ID=?";
		String sql1a = "UPDATE SCB S SET S.SCB=? WHERE S.ZDID=?";
		String ssqll = "UPDATE DIANBIAO D SET D.DANJIA=?,D.DBDS=?,D.XGBZW=?,D.DFZFLX=?  WHERE D.DBID=?";  //修改电表的 单价，电表修改读数，读数修改标志位
		String sql2 = "update QSKZ c set c.SHISHBZ='2',c.SHISHR='" + loginName + "',c.sjly='" + cause
		+ "',c.SHISHSJ=sysdate where  c.zdid in (" + id + ") AND C.ID in (" + idd + ") ";
		String sql3 = "UPDATE QSKZ C SET C.SHENGSHBZ='0',C.BFTG=NULL,C.BFBTG=NULL where C.ID=?";
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = "市级审核--" + sdf.format(d);
		DataBase db = new DataBase();
		try {
			connc = db.getConnection();
			connc.setAutoCommit(false);
			ps = connc.prepareStatement(sql);
			psa = connc.prepareStatement(sql1a);
			ps1 = connc.prepareStatement(ssqll);
			ps2 = connc.prepareStatement(sql3);
			st = connc.createStatement();
			if (ls != null) {
				for (Zdqxkz lst : ls) {
					ps.setString(1, lst.getOldbgsign());
					ps.setString(2, lst.getOlddianfei());
					ps.setString(3, lst.getOldedhdl());
					ps.setString(4, lst.getOldjlfh());
					ps.setString(5, lst.getOldzlfh());
					ps.setString(6, s);
					ps.setString(7, lst.getOldqsdbdl());
					ps.setString(8, lst.getOldqsdb());
					ps.setString(9, lst.getZdid());
					
					ps.addBatch();
					
					psa.setString(1, lst.getOldqsdb());
					psa.setString(2, lst.getZdid());
					
					psa.addBatch();
					
					ps1.setString(1, lst.getOlddianfei());
					ps1.setString(2, lst.getOlddbds());
					ps1.setString(3, lst.getOldxgbzw());
					ps1.setString(4, lst.getOlddfzflx());
					ps1.setString(5, lst.getDbid());
					
					ps1.addBatch();
					
					ps2.setString(1, lst.getId());
					if("1".equals(lst.getShengshbz()) && "".equals(lst.getShengshr())){
						ps2.executeUpdate();
					}
				}
				ps.executeBatch();
				psa.executeBatch();
				ps1.executeBatch();
			}
			st.executeUpdate(sql2);
			connc.commit();
			msg = 1;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connc.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				connc.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.free(null, ps, null);
			db.free(null, psa, null);
			db.free(null, ps1, null);
			db.free(null, ps2, null);
			db.free(null, st, connc);
		}
		
		return msg;
	}

	public synchronized int SHIUpdateZD(String id, List<Zdqxkz> ls, String syf,
			String b1, String b2, String b3, String bb5, String bb6,
			String bb7, String bb8, String bb9, String bb10, String bb11,
			String bb12, String bb13, String bb14, String bb15, String bb16,
			String bb17, String bb18, String bb19, String bb20, String bb22,
			String dd5, String dd6, String dd7, String dd8, String dd9,
			String dd10, String dd11, String dd12, String dd13, String dd14,
			String dd15, String dd16, String dd17, String dd18, String dd19,
			String dd20, String dd22, String xx5, String xx6, String xx7,
			String xx8, String xx9, String xx10, String xx11, String xx12,
			String xx13, String xx14, String xx15, String xx16, String xx17,
			String xx18, String xx19, String xx20, String xx22, String yy5,
			String yy6, String yy7, String yy8, String yy9, String yy10,
			String yy11, String yy12, String yy13, String yy14, String yy15,
			String yy16, String yy17, String yy18, String yy19, String yy20,
			String yy22, String qq5, String qq6, String qq7, String qq8,
			String qq9, String qq10, String qq11, String qq12, String qq13,
			String qq14, String qq15, String qq16, String qq17, String qq18,
			String qq19, String qq20, String qq22, String ii5, String ii6,
			String ii7, String ii8, String ii9, String ii10, String ii11,
			String ii12, String ii13, String ii14, String ii15, String ii16,
			String ii17, String ii18, String ii19, String ii20, String ii22,
			String jj5, String jj6, String jj7, String jj8, String jj9,
			String jj10, String jj11, String jj12, String jj13, String jj14,
			String jj15, String jj16, String jj17, String jj18, String jj19,
			String jj20, String jj22, String lg, List<XxxgBean> lsbean,String loginName, String idd) {
		int msg = 0;
		String sql = "";
		String ssqll = "";
		DataBase db = new DataBase();
		Connection connc = null;
		Statement st = null;
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//精确时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");// 设置日期格式
		System.out.println(df.format(date));// new Date()为获取当前系统时间
		String sff = df.format(date);
		String ssyf = sff.toString().substring(5, 7);
		int j = Integer.parseInt(ssyf);
		System.out.println("月份：" + sff.toString());
		String nowtime = sf.format(date);
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		ResultSet rs5 = null;
		ResultSet rs6 = null;
		ResultSet rs7 = null;
		String sj = "TO_DATE('"+nowtime+"','yyyy-mm-dd hh24:mi:ss')";

		try {
			connc = db.getConnection();
			connc.setAutoCommit(false);
			st = connc.createStatement();
			if (ls != null) {
				for (Zdqxkz lst : ls) {
					String oldqsdb = lst.getOldqsdb();
					String newqsdb = lst.getNewqsdbdl();
					if (!oldqsdb.equals(newqsdb)) {//判断是否申请省定标（不含空调）的条件
						String sqlkongtiao = "SELECT ZD.KTS FROM ZHANDIAN ZD WHERE ZD.ID = '"+ lst.getZdid()+"'";//查询 有无 空调
						rs = st.executeQuery(sqlkongtiao);
						int kts = 0;
						if(rs.next()){
							kts = rs.getInt("KTS");
						}
						if ("zdsx01".equals(lst.getNewproperty())) {//局用机房
							String zlfh = "", jlfh = "";
							String sqlsb = "SELECT X.ZLFH,X.JLFH FROM XXXG X WHERE X.ZDSX='zdsx01'";//信息修改表查每种属性对应的交直流负荷标准
							rs1 = st.executeQuery(sqlsb);
							while (rs1.next()) {
								zlfh = rs1.getString(1);//直流负荷
								jlfh = rs1.getString(2);//交流负荷
							}
							double dzl = 0, djl = 0;
							dzl = Format.str_d(lst.getNewzlfh());//新申请的直流负荷
							djl = Format.str_d(lst.getNewjlfh());//新申请的交流负荷
							if (dzl <= Format.str_d(zlfh) && djl <= Format.str_d(jlfh)) {//在范围之内
								String ssql = "", sql3 = "", sql12 = "";
								String s = lst.getNewqsdbdl();//新申请的省定标（不含空调）
								double d = 0.0, dd = 0.0;
								if ("0".equals(s) || null == s || "".equals(s)) {
									//如果新申请的省定标（不含空调） 为空 则 通过 直流负荷 交流负荷 计算（取小的）
									d = (dzl * 54 * 24 / 1000 / 0.85);
									dd = (djl * 220 * 24 / 1000);
									if (d >= dd) {
										s = String.valueOf(dd);
									} else if (d <= dd) {
										s = String.valueOf(d);
									}
								}
								sql12 = "SELECT S.SCB FROM SCB S WHERE S.ZDID = '" + lst.getZdid()+"'";
								rs1 = st.executeQuery(sql12);
								//从scb中查询 scb，如果有记录 则更新，如果没值 则 插入一条
								if(rs1.next()) {
									sql3 = "UPDATE SCB S SET S.SCB=" + s + " WHERE S.ZDID ='" + lst.getZdid()+"'";
									st.executeUpdate(sql3);
								}else{
									String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12','" + lst.getZdid() + "'," + s + ",'" + lg + "'," + sj + "," + 0 + ")";
									st.executeUpdate(sql22);
								}
								if (kts>0){//有空调
									//月份判断
									if (j >= 9) {//9月之后
										ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + jj5 + " AND aa.ZLFH <=" + jj6
											+ " THEN " + jj7 + " WHEN aa.ZLFH >" + jj6 + " AND aa.ZLFH <=" + jj9 + " THEN " + jj10 + " " + "WHEN aa.ZLFH > " + jj9
											+ " AND aa.ZLFH <= " + jj12 + " THEN " + jj13 + " WHEN aa.ZLFH > " + jj12 + " AND aa.ZLFH <= " + jj15 + " THEN " + jj16
											+ " WHEN aa.ZLFH > " + jj15 + " AND aa.ZLFH <= " + jj18 + " THEN " + jj19 + " WHEN aa.ZLFH > " + jj18 + " THEN " + jj22
											+ " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf 
											+ " from yfxs y where y.shicode = q.shi)"
											+" ),q.scb=" + s + " where q.id='" + lst.getZdid() + "'";
									} else {
										ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + jj5 + " AND aa.ZLFH <" + jj6
											+ " THEN " + jj7 + " WHEN aa.ZLFH >=" + jj6 + " AND aa.ZLFH <" + jj8 + " THEN " + jj10 + " WHEN aa.ZLFH >= " + jj8
											+ " AND aa.ZLFH <" + jj11 + " THEN " + jj13 + " WHEN aa.ZLFH >= " + jj11 + " AND aa.ZLFH < " + jj14 + " THEN " + jj16
											+ " WHEN aa.ZLFH >= " + jj14 + " AND aa.ZLFH < " + jj17 + " THEN " + jj19 + " WHEN aa.ZLFH >= " + jj17 + " THEN "
											+ jj22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."+ syf
											+ " from yfxs y where y.shicode = q.shi)"
											+" ),q.scb=" + s + " where q.id='"+ lst.getZdid() + "'";
									}
								}else{
									ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s + " where ZD.ID='" + lst.getZdid()+"'";
								}
								
								st.executeUpdate(ssql);
//								String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.PROPERTY='" + lst.getNewproperty() + "',ZD.STATIONTYPE='" + lst.getNewstationtype() + "',ZD.GXXX='" + lst.getNewgxxx()
//										+ "',ZD.GDFS='" + lst.getNewgdfs() + "',ZD.ZGD='" + lst.getNewzgd() + "',ZD.AREA='" + lst.getNewarea() + "',ZD.QYZT='" + lst.getNewqyzt() 
//										+ "' " + ", ZD.G2='" + lst.getNewg2() + "',ZD.G3='" + lst.getNewg3() + "',ZD.ZPSL='" + lst.getNewzpsl() + "',ZD.ZSSL='" + lst.getNewzssl()
//										+ "',ZD.CHANGJIA='" + lst.getNewchangjia() + "',ZD.JZLX='" + lst.getNewjzlx() + "',ZD.SHEBEI='" + lst.getNewshebei() + "',ZD.BBU='"
//										+ lst.getNewbbu() + "',ZD.RRU='" + lst.getNewrru() + "',ZD.YDSHEBEI='" + lst.getNewydshebei() + "',ZD.GXGWSL='" + lst.getNewgxgwsl()
//										+ "',ZD.YFLX='" + lst.getNewyflx() + "' WHERE ZD.ID='" + lst.getZdid() + "'";
//								st.executeUpdate(sql2);
								ZdqxkzUtil util = new ZdqxkzUtil();
								String flgg = lst.getFlgg();
								String bftg = lst.getBftg();
								String bfbtg = lst.getBfbtg();
								String sbz = "";
								bftg = util.getBftg(bftg, "9");
								if(util.getShengbz(flgg, bftg)){
									sbz = "1";
								}else{
									sbz = util.checkKong(bfbtg)?"3":"5";
								}
								String sql34 = "update QSKZ c set c.SHENGSHBZ='"+sbz+"',c.shengshsj=TO_DATE('"+nowtime+"','yyyy-mm-dd hh24:mi:ss'),c.BFTG='"+bftg+"'  where  c.zdid = '" + lst.getZdid() + "' and c.id = '"+lst.getId()+"'";
								st.executeUpdate(sql34);
								
							}
						} else if ("zdsx02".equals(lst.getNewproperty())) {//基站
							String zlfh = "";
							String sqlsb = "SELECT X.ZLFH FROM XXXG X WHERE X.ZDSX='zdsx02'";
							rs2 = st.executeQuery(sqlsb);
							while (rs2.next()) {
								zlfh = rs2.getString(1);
							}
							double dzl = 0,djl = 0;
							dzl = Format.str_d(lst.getNewzlfh());
							djl = Format.str_d(lst.getNewjlfh());
							if (dzl <= Format.str_d(zlfh)) {
								String ssql = "", sql3 = "", sql12 = "";
								String s = lst.getNewqsdbdl();
								double d = 0.0, dd = 0.0;
								if ("0".equals(s) || null == s || "".equals(s)) {
									d = dzl * 1.52;
									dd = djl * 5.28;
									if (d >= dd) {
										s = String.valueOf(dd);
									} else if (d <= dd) {
										s = String.valueOf(d);
									}
								}
								sql12 = "SELECT S.SCB FROM SCB S WHERE S.ZDID = '" + lst.getZdid()+"'";
								rs2 = st.executeQuery(sql12);
								if (rs2.next()) {
									sql3 = "UPDATE SCB S SET S.SCB=" + s + " WHERE S.ZDID ='" + lst.getZdid()+"'";
									st.executeUpdate(sql3);
								}else{
									String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-08','" + lst.getZdid() + "'," + s + ",'" + lg + "'," + sj + "," + 0 + ")";
									st.executeUpdate(sql22);
								}
								if(kts>0){//有空调
									if (j >= 9) {
										ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + bb5 + " AND aa.ZLFH <=" + bb6
												+ " THEN " + bb7 + " WHEN aa.ZLFH >" + bb6 + " AND aa.ZLFH <=" + bb9 + " THEN " + bb10 + " WHEN aa.ZLFH > " + bb9+ " AND aa.ZLFH <= "
												+ bb12 + " THEN " + bb13 + " WHEN aa.ZLFH > " + bb12 + " AND aa.ZLFH <= " + bb15 + " THEN " + bb16 + " WHEN aa.ZLFH > "
												+ bb15 + " AND aa.ZLFH <= " + bb18 + " THEN " + bb19 + " WHEN aa.ZLFH > " + bb18 + " THEN " + bb22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
												+ syf + " from yfxs y where y.shicode = q.shi)" + " +(SELECT DECODE(aa.YFLX, 'fwlx01', " + b1 + ", 'fwlx02'," + b2 + ", 'fwlx03',"
												+ b3 + ") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " + " ),q.scb=" + s + " where q.id='" + lst.getZdid() + "'";
									} else {
										ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + bb5 + " AND aa.ZLFH <" + bb6 + " THEN " + bb7
												+ " WHEN aa.ZLFH >=" + bb6 + " AND aa.ZLFH <" + bb8 + " THEN " + bb10 + " WHEN aa.ZLFH >= " + bb8 + " AND aa.ZLFH <" + bb11
												+ " THEN " + bb13 + " WHEN aa.ZLFH >= " + bb11 + " AND aa.ZLFH < " + bb14 + " THEN " + bb16 + " WHEN aa.ZLFH >= " + bb14
												+ " AND aa.ZLFH < " + bb17 + " THEN " + bb19 + " WHEN aa.ZLFH >= " + bb17 + " THEN " + bb22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
												+ syf + " from yfxs y where y.shicode = q.shi)" + " +(SELECT DECODE(aa.YFLX, 'fwlx01', " + b1 + ", 'fwlx02'," + b2
												+ ", 'fwlx03'," + b3 + ") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) " + " ),q.scb=" + s + " where q.id='" + lst.getZdid() + "'";
									}
								}else{
									ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s + " where ZD.ID='" + lst.getZdid()+"'";
								}
								st.executeUpdate(ssql);
//								String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.PROPERTY='" + lst.getNewproperty() + "',ZD.STATIONTYPE='" + lst.getNewstationtype() + "',ZD.GXXX='" + lst.getNewgxxx()
//										+ "',ZD.GDFS='" + lst.getNewgdfs() + "',ZD.ZGD='" + lst.getNewzgd() + "',ZD.AREA='" + lst.getNewarea() + "',ZD.QYZT='" + lst.getNewqyzt() 
//										+ "' " + ", ZD.G2='" + lst.getNewg2() + "',ZD.G3='" + lst.getNewg3() + "',ZD.ZPSL='" + lst.getNewzpsl() + "',ZD.ZSSL='" + lst.getNewzssl()
//										+ "',ZD.CHANGJIA='" + lst.getNewchangjia() + "',ZD.JZLX='" + lst.getNewjzlx() + "',ZD.SHEBEI='" + lst.getNewshebei() + "',ZD.BBU='"
//										+ lst.getNewbbu() + "',ZD.RRU='" + lst.getNewrru() + "',ZD.YDSHEBEI='" + lst.getNewydshebei() + "',ZD.GXGWSL='" + lst.getNewgxgwsl()
//										+ "',ZD.YFLX='" + lst.getNewyflx() + "' WHERE ZD.ID='" + lst.getZdid() + "'";
//								st.executeUpdate(sql2);
								ZdqxkzUtil util = new ZdqxkzUtil();
								String flgg = lst.getFlgg();
								String bftg = lst.getBftg();
								String bfbtg = lst.getBfbtg();
								String sbz = "";
								bftg = util.getBftg(bftg, "9");
								if(util.getShengbz(flgg, bftg)){
									sbz = "1";
								}else{
									sbz = util.checkKong(bfbtg)?"3":"5";
								}
								String sql34 = "update QSKZ c set c.SHENGSHBZ='"+sbz+"',c.shengshsj=TO_DATE('"+nowtime+"','yyyy-mm-dd hh24:mi:ss'),c.BFTG='"+bftg+"'  where  c.zdid = '" + lst.getZdid() + "' and c.id = '"+lst.getId()+"'";
								st.executeUpdate(sql34);
							}
						} else if ("zdsx03".equals(lst.getNewproperty())) {// 营业网点判断
							String  zlfh = "", jlfh = "";
							String sqlsb = "SELECT X.ZLFH,X.JLFH FROM XXXG X WHERE X.ZDSX='zdsx03'";
							rs3 = st.executeQuery(sqlsb);
							while (rs3.next()) {
								zlfh = rs3.getString(1);
								jlfh = rs3.getString(2);
							}
							double dzl = 0, djl = 0;
							dzl = Format.str_d(lst.getNewzlfh());//新申请直流负荷
							djl = Format.str_d(lst.getNewjlfh());//新申请交流负荷
							if (dzl <= Format.str_d(zlfh)
									&& djl <= Format.str_d(jlfh)) {
								// ===宽松
								String ssql = "", sql3 = "", sql12 = "";
								String s = lst.getNewqsdbdl();
								double d = 0.0, dd = 0.0;
								if ("0".equals(s) || null == s || "".equals(s)) {
									d = dzl * 54 * 24 / 1000 / 0.85;
									dd = djl * 220 * 24 / 1000;
									if (d >= dd) {
										s = String.valueOf(dd);
									} else if (d <= dd) {
										s = String.valueOf(d);
									}
								}
								sql12 = "select s.scb from scb s where s.zdid = '" + lst.getZdid() + "'";
								rs3 = st.executeQuery(sql12);
								if(rs3.next()) {
									sql3 = "UPDATE SCB S SET S.SCB=" + s + " WHERE S.ZDID ='" + lst.getZdid()+"'";
									st.executeUpdate(sql3);
								}else{
									String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12','" + lst.getZdid() + "'," + s + ",'" + lg + "'," + sj + "," + 0 + ")";
									st.executeUpdate(sql22);
								}
								if(kts>0){
									if (j >= 9) {
										ssql = " update zhandian q set q.qsdbdl= " + s + " * 1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + yy5 + " AND aa.ZLFH <=" + yy6 + " THEN "
												+ yy7 + " WHEN aa.ZLFH >" + yy6 + " AND aa.ZLFH <=" + yy9 + " THEN " + yy10 + " WHEN aa.ZLFH > " + yy9 + " AND aa.ZLFH <= "
												+ yy12 + " THEN " + yy13 + " WHEN aa.ZLFH > " + yy12 + " AND aa.ZLFH <= " + yy15 + " THEN " + yy16 + " WHEN aa.ZLFH > "
												+ yy15 + " AND aa.ZLFH <= " + yy18 + " THEN " + yy19 + " WHEN aa.ZLFH > " + yy18 + " THEN " + yy22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
												+ syf + " from yfxs y where y.shicode = q.shi)"
												+",q.scb=" + s + "  where q.id='" + lst.getZdid() + "'";

									} else {
										ssql = "update zhandian q set q.qsdbdl=" + s + " *1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + yy5 + " AND aa.ZLFH <" + yy6
												+ " THEN " + yy7 + " WHEN aa.ZLFH >=" + yy6 + " AND aa.ZLFH <" + yy8 + " THEN " + yy10 + " WHEN aa.ZLFH >= "
												+ yy8 + " AND aa.ZLFH <" + yy11 + " THEN " + yy13 + " WHEN aa.ZLFH >= " + yy11 + " AND aa.ZLFH < " + yy14 + " THEN "
												+ yy16 + " WHEN aa.ZLFH >= " + yy14 + " AND aa.ZLFH < " + yy17 + " THEN " + yy19 + " WHEN aa.ZLFH >= " + yy17
												+ " THEN " + yy22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
												+ syf + " from yfxs y where y.shicode = q.shi)"
												+",q.scb=" + s + "  where q.id='"+ lst.getZdid() + "'";
									}
								}else{
									ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s + " where ZD.ID='" + lst.getZdid()+"'";
								}
								System.out.println("999--:"+ssql);
								st.executeUpdate(ssql);
//								String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.PROPERTY='" + lst.getNewproperty() + "',ZD.STATIONTYPE='" + lst.getNewstationtype() + "',ZD.GXXX='" + lst.getNewgxxx()
//									+ "',ZD.GDFS='" + lst.getNewgdfs() + "',ZD.ZGD='" + lst.getNewzgd() + "',ZD.AREA='" + lst.getNewarea() + "',ZD.QYZT='" + lst.getNewqyzt() 
//									+ "' " + ", ZD.G2='" + lst.getNewg2() + "',ZD.G3='" + lst.getNewg3() + "',ZD.ZPSL='" + lst.getNewzpsl() + "',ZD.ZSSL='" + lst.getNewzssl()
//									+ "',ZD.CHANGJIA='" + lst.getNewchangjia() + "',ZD.JZLX='" + lst.getNewjzlx() + "',ZD.SHEBEI='" + lst.getNewshebei() + "',ZD.BBU='"
//									+ lst.getNewbbu() + "',ZD.RRU='" + lst.getNewrru() + "',ZD.YDSHEBEI='" + lst.getNewydshebei() + "',ZD.GXGWSL='" + lst.getNewgxgwsl()
//									+ "',ZD.YFLX='" + lst.getNewyflx() + "' WHERE ZD.ID='" + lst.getZdid() + "'";
//								st.executeUpdate(sql2);
								
								ZdqxkzUtil util = new ZdqxkzUtil();
								String flgg = lst.getFlgg();
								String bftg = lst.getBftg();
								String bfbtg = lst.getBfbtg();
								String sbz = "";
								bftg = util.getBftg(bftg, "9");
								if(util.getShengbz(flgg, bftg)){
									sbz = "1";
								}else{
									sbz = util.checkKong(bfbtg)?"3":"5";
								}
								String sql34 = "update QSKZ c set c.SHENGSHBZ='"+sbz+"',c.shengshsj=TO_DATE('"+nowtime+"','yyyy-mm-dd hh24:mi:ss'),c.BFTG='"+bftg+"'  where  c.zdid = '" + lst.getZdid() + "' and c.id = '"+lst.getId()+"'";
								st.executeUpdate(sql34);
							}

						} else if ("zdsx04".equals(lst.getNewproperty())) {//其他
							String  zlfh = "", jlfh = "";
							String sqlsb = "SELECT X.ZLFH,X.JLFH FROM XXXG X WHERE X.ZDSX='zdsx04'";
							rs4 = st.executeQuery(sqlsb);
							while (rs4.next()) {
								zlfh = rs4.getString(1);
								jlfh = rs4.getString(2);
							}
							double dzl = 0, djl = 0;
							dzl = Format.str_d(lst.getNewzlfh());
							djl = Format.str_d(lst.getNewjlfh());
							if (dzl <= Format.str_d(zlfh) && djl <= Format.str_d(jlfh)) {
								String ssql = "", sql3 = "", sql12 = "";
								String s = lst.getNewqsdbdl();
								double d = 0.0, dd = 0.0;
								if ("0".equals(s) || null == s || "".equals(s)) {
									d = dzl * 54 * 24 / 1000 / 0.85;
									dd = djl * 220 * 24 / 1000;
									if (d >= dd) {
										s = String.valueOf(dd);
									} else if (d <= dd) {
										s = String.valueOf(d);
									}
								}
								sql12 = "select s.scb from scb s where s.zdid = '" + lst.getZdid() + "'";
								rs4 = st.executeQuery(sql12);
								if (rs4.next()) {
									sql3 = "UPDATE SCB S SET S.SCB=" + s + " WHERE S.ZDID ='" + lst.getZdid()+"'";
									st.executeUpdate(sql3);
								}else{
									String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-08','" + lst.getZdid() + "'," + s + ",'" + lg + "'," + sj + "," + 0 + ")";
									st.executeUpdate(sql22);
								}
								if(kts>0){
									if (j >= 9) {
										ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + qq5 + " AND aa.ZLFH <="
												+ qq6 + " THEN " + qq7 + " WHEN aa.ZLFH >" + qq6 + " AND aa.ZLFH <=" + qq9 + " THEN " + qq10 + " WHEN aa.ZLFH > "
												+ qq9 + " AND aa.ZLFH <= " + qq12 + " THEN " + qq13 + " WHEN aa.ZLFH > " + qq12 + " AND aa.ZLFH <= " + qq15 + " THEN "
												+ qq16 + " WHEN aa.ZLFH > " + qq15 + " AND aa.ZLFH <= " + qq18 + " THEN " + qq19 + " WHEN aa.ZLFH > " + qq18
												+ " THEN " + qq22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
												+ syf + " from yfxs y where y.shicode = q.shi)" + " ),q.scb=" + s + " where q.id='" + lst.getZdid() + "'";

									} else {
										ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + qq5 + " AND aa.ZLFH <" + qq6
												+ " THEN " + qq7 + " WHEN aa.ZLFH >=" + qq6 + " AND aa.ZLFH <" + qq8 + " THEN " + qq10 + " WHEN aa.ZLFH >= " + qq8
												+ " AND aa.ZLFH <" + qq11 + " THEN " + qq13 + " WHEN aa.ZLFH >= " + qq11 + " AND aa.ZLFH < " + qq14 + " THEN "
												+ qq16 + " WHEN aa.ZLFH >= " + qq14 + " AND aa.ZLFH < " + qq17 + " THEN " + qq19 + " WHEN aa.ZLFH >= " + qq17
												+ " THEN " + qq22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
												+ syf + " from yfxs y where y.shicode = q.shi)" + " ),q.scb=" + s + " where q.id='" + lst.getZdid() + "'";
									}
								}else{
									ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s + " where ZD.ID='" + lst.getZdid()+"'";
								}
								st.executeUpdate(ssql);
//								String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.PROPERTY='" + lst.getNewproperty() + "',ZD.STATIONTYPE='" + lst.getNewstationtype() + "',ZD.GXXX='" + lst.getNewgxxx()
//										+ "',ZD.GDFS='" + lst.getNewgdfs() + "',ZD.ZGD='" + lst.getNewzgd() + "',ZD.AREA='" + lst.getNewarea() + "',ZD.QYZT='" + lst.getNewqyzt() 
//										+ "' " + ", ZD.G2='" + lst.getNewg2() + "',ZD.G3='" + lst.getNewg3() + "',ZD.ZPSL='" + lst.getNewzpsl() + "',ZD.ZSSL='" + lst.getNewzssl()
//										+ "',ZD.CHANGJIA='" + lst.getNewchangjia() + "',ZD.JZLX='" + lst.getNewjzlx() + "',ZD.SHEBEI='" + lst.getNewshebei() + "',ZD.BBU='"
//										+ lst.getNewbbu() + "',ZD.RRU='" + lst.getNewrru() + "',ZD.YDSHEBEI='" + lst.getNewydshebei() + "',ZD.GXGWSL='" + lst.getNewgxgwsl()
//										+ "',ZD.YFLX='" + lst.getNewyflx() + "' WHERE ZD.ID='" + lst.getZdid() + "'";
//								st.executeUpdate(sql2);
								ZdqxkzUtil util = new ZdqxkzUtil();
								String flgg = lst.getFlgg();
								String bftg = lst.getBftg();
								String bfbtg = lst.getBfbtg();
								String sbz = "";
								bftg = util.getBftg(bftg, "9");
								if(util.getShengbz(flgg, bftg)){
									sbz = "1";
								}else{
									sbz = util.checkKong(bfbtg)?"3":"5";
								}
								String sql34 = "update QSKZ c set c.SHENGSHBZ='"+sbz+"',c.shengshsj=TO_DATE('"+nowtime+"','yyyy-mm-dd hh24:mi:ss'),c.BFTG='"+bftg+"'  where  c.zdid = '" + lst.getZdid() + "' and c.id = '"+lst.getId()+"'";
								st.executeUpdate(sql34);
							}
						} else if ("zdsx05".equals(lst.getNewproperty())) {//接入网
							String zlfh = "";
							String sqlsb = "SELECT X.ZLFH FROM XXXG X WHERE X.ZDSX='zdsx05'";
							rs5 = st.executeQuery(sqlsb);
							while (rs5.next()) {
								zlfh = rs5.getString(1);
							}
							double dzl = 0, djl = 0;
							dzl = Format.str_d(lst.getNewzlfh());
							djl = Format.str_d(lst.getNewjlfh());
							if (dzl <= Format.str_d(zlfh)) {
								String ssql = "", sql3 = "", sql12 = "";
								String s = lst.getNewqsdbdl();
								double d = 0.0, dd = 0.0;
								if ("0".equals(s) || null == s || "".equals(s)) {
									d = dzl * 54 * 24 / 1000 / 0.85;
									dd = djl * 220 * 24 / 1000;
									if (d >= dd) {
										s = String.valueOf(dd);
									} else if (d <= dd) {
										s = String.valueOf(d);
									}
								}
								sql12 = "select s.scb from scb s where s.zdid = '" + lst.getZdid() + "'";
								rs5 = st.executeQuery(sql12);
								if (rs5.next()) {
									sql3 = "UPDATE SCB S SET S.SCB=" + s + " WHERE S.ZDID ='" + lst.getZdid()+"'";
									st.executeUpdate(sql3);
								}else{
									String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-11','" + lst.getZdid() + "'," + s + ",'" + lg + "'," + sj + "," + 0 + ")";
									st.executeUpdate(sql22);
								}
								if(kts>0){
									if (j >= 9) {
										ssql = " update zhandian q set q.qsdbdl= " + s + " * 1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + dd5 + " AND aa.ZLFH <="
											+ dd6 + " THEN " + dd7 + " WHEN aa.ZLFH >" + dd6 + " AND aa.ZLFH <=" + dd9 + " THEN " + dd10 + " WHEN aa.ZLFH > " + dd9
											+ " AND aa.ZLFH <= " + dd12 + " THEN " + dd13 + " WHEN aa.ZLFH > " + dd12 + " AND aa.ZLFH <= " + dd15 + " THEN "
											+ dd16 + " WHEN aa.ZLFH > " + dd15 + " AND aa.ZLFH <= " + dd18 + " THEN " + dd19 + " WHEN aa.ZLFH > " + dd18 + " THEN "
											+ dd22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf + " from yfxs y where y.shicode = q.shi)"
											+",q.scb=" + s + " where q.id='" + lst.getZdid() + "'";
									} else {
										ssql = "update zhandian q set q.qsdbdl=" + s + "*1 " + "+(SELECT (CASE WHEN aa.ZLFH >=" + bb5 + " AND aa.ZLFH <" + bb6
												+ " THEN " + bb7 + " WHEN aa.ZLFH >=" + bb6 + " AND aa.ZLFH <" + bb8 + " THEN " + bb10 + " WHEN aa.ZLFH >= " + bb8
												+ " AND aa.ZLFH <" + bb11 + " THEN " + bb13 + " WHEN aa.ZLFH >= " + bb11 + " AND aa.ZLFH < " + bb14 + " THEN "
												+ bb16 + " WHEN aa.ZLFH >= " + bb14 + " AND aa.ZLFH < " + bb17 + " THEN " + bb19 + " WHEN aa.ZLFH >= " + bb17
												+ " THEN " + bb22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf
												+ " from yfxs y where y.shicode = q.shi),q.scb=" + s + " " + " where q.id='" + lst.getZdid() + "'";
									}
								}else{
									ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s + " where ZD.ID='" + lst.getZdid()+"'";
								}
								st.executeUpdate(ssql);
//								String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.PROPERTY='" + lst.getNewproperty() + "',ZD.STATIONTYPE='" + lst.getNewstationtype() + "',ZD.GXXX='" + lst.getNewgxxx()
//										+ "',ZD.GDFS='" + lst.getNewgdfs() + "',ZD.ZGD='" + lst.getNewzgd() + "',ZD.AREA='" + lst.getNewarea() + "',ZD.QYZT='" + lst.getNewqyzt() 
//										+ "' " + ", ZD.G2='" + lst.getNewg2() + "',ZD.G3='" + lst.getNewg3() + "',ZD.ZPSL='" + lst.getNewzpsl() + "',ZD.ZSSL='" + lst.getNewzssl()
//										+ "',ZD.CHANGJIA='" + lst.getNewchangjia() + "',ZD.JZLX='" + lst.getNewjzlx() + "',ZD.SHEBEI='" + lst.getNewshebei() + "',ZD.BBU='"
//										+ lst.getNewbbu() + "',ZD.RRU='" + lst.getNewrru() + "',ZD.YDSHEBEI='" + lst.getNewydshebei() + "',ZD.GXGWSL='" + lst.getNewgxgwsl()
//										+ "',ZD.YFLX='" + lst.getNewyflx() + "' WHERE ZD.ID='" + lst.getZdid() + "'";
//								st.executeUpdate(sql2);
								
								ZdqxkzUtil util = new ZdqxkzUtil();
								String flgg = lst.getFlgg();
								String bftg = lst.getBftg();
								String bfbtg = lst.getBfbtg();
								String sbz = "";
								bftg = util.getBftg(bftg, "9");
								if(util.getShengbz(flgg, bftg)){
									sbz = "1";
								}else{
									sbz = util.checkKong(bfbtg)?"3":"5";
								}
								String sql34 = "update QSKZ c set c.SHENGSHBZ='"+sbz+"',c.shengshsj=TO_DATE('"+nowtime+"','yyyy-mm-dd hh24:mi:ss'),c.BFTG='"+bftg+"'  where  c.zdid = '" + lst.getZdid() + "' and c.id = '"+lst.getId()+"'";
								st.executeUpdate(sql34);
							}

						} else if ("zdsx06".equals(lst.getNewproperty())) {//乡镇支局
							String zlfh = "", jlfh = "";
							String sqlsb = "SELECT X.ZLFH,X.JLFH FROM XXXG X WHERE X.ZDSX='zdsx06'";
							rs6 = st.executeQuery(sqlsb);
							while (rs6.next()) {
								zlfh = rs6.getString(1);
								jlfh = rs6.getString(2);
							}
							double dzl = 0, djl = 0;
							dzl = Format.str_d(lst.getNewzlfh());
							djl = Format.str_d(lst.getNewjlfh());

							if (dzl <= Double.parseDouble(zlfh) && djl <= Double.parseDouble(jlfh)) {
								String ssql = "", sql3 = "", sql12 = "";
								String s = lst.getNewqsdbdl();
								double d = 0.0, dd = 0.0;
								if ("0".equals(s) || null == s || "".equals(s)) {
									d = dzl * 54 * 24 / 1000 / 0.85;
									dd = djl * 220 * 24 / 1000;
									if (d >= dd) {
										s = String.valueOf(dd);
									} else if (d <= dd) {
										s = String.valueOf(d);
									}
								}
								sql12 = "select s.scb from scb s where s.zdid = '" + lst.getZdid() + "'";
								rs6 = st.executeQuery(sql12);
								if (rs6.next()) {
									sql3 = "UPDATE SCB S SET S.SCB=" + s + " WHERE S.ZDID ='" + lst.getZdid()+"'";
									st.executeUpdate(sql3);
								}else{
									String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12','" + lst.getZdid() + "'," + s + ",'" + lg + "'," + sj + "," + 0 + ")";
									st.executeUpdate(sql22);
								}
								if(kts>0){
									if (j >= 9) {
										ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + xx5 + " AND aa.ZLFH <=" + xx6 + " THEN "
												+ xx7 + " WHEN aa.ZLFH >" + xx6 + " AND aa.ZLFH <=" + xx9 + " THEN " + xx10 + "WHEN aa.ZLFH > " + xx9 + " AND aa.ZLFH <= "
												+ xx12 + " THEN " + xx13 + " WHEN aa.ZLFH > " + xx12 + " AND aa.ZLFH <= " + xx15 + " THEN " + xx16 + " WHEN aa.ZLFH > "
												+ xx15 + " AND aa.ZLFH <= " + xx18 + " THEN " + xx19 + " WHEN aa.ZLFH > " + xx18 + " THEN " + xx22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
												+ syf + " from yfxs y where y.shicode = q.shi)" + " ),q.scb=" + s + " where q.id='" + lst.getZdid() + "'";

									} else {
										ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + xx5 + " AND aa.ZLFH <" + xx6 + " THEN "
												+ xx7 + " WHEN aa.ZLFH >=" + xx6 + " AND aa.ZLFH <" + xx8 + " THEN " + xx10 + " WHEN aa.ZLFH >= " + xx8 + " AND aa.ZLFH <"
												+ xx11 + " THEN " + xx13 + " WHEN aa.ZLFH >= " + xx11 + " AND aa.ZLFH < " + xx14 + " THEN " + xx16 + " WHEN aa.ZLFH >= "
												+ xx14 + " AND aa.ZLFH < " + xx17 + " THEN " + xx19 + " WHEN aa.ZLFH >= " + xx17 + " THEN " + xx22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
												+ syf + " from yfxs y where y.shicode = q.shi)" +" ),q.scb=" + s + " where q.id='" + lst.getZdid() + "'";

									}
								}else{
									ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s + " where ZD.ID='" + lst.getZdid()+"'";
								}
								st.executeUpdate(ssql);
//								String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.PROPERTY='" + lst.getNewproperty() + "',ZD.STATIONTYPE='" + lst.getNewstationtype() + "',ZD.GXXX='" + lst.getNewgxxx()
//										+ "',ZD.GDFS='" + lst.getNewgdfs() + "',ZD.ZGD='" + lst.getNewzgd() + "',ZD.AREA='" + lst.getNewarea() + "',ZD.QYZT='" + lst.getNewqyzt() 
//										+ "' " + ", ZD.G2='" + lst.getNewg2() + "',ZD.G3='" + lst.getNewg3() + "',ZD.ZPSL='" + lst.getNewzpsl() + "',ZD.ZSSL='" + lst.getNewzssl()
//										+ "',ZD.CHANGJIA='" + lst.getNewchangjia() + "',ZD.JZLX='" + lst.getNewjzlx() + "',ZD.SHEBEI='" + lst.getNewshebei() + "',ZD.BBU='"
//										+ lst.getNewbbu() + "',ZD.RRU='" + lst.getNewrru() + "',ZD.YDSHEBEI='" + lst.getNewydshebei() + "',ZD.GXGWSL='" + lst.getNewgxgwsl()
//										+ "',ZD.YFLX='" + lst.getNewyflx() + "' WHERE ZD.ID='" + lst.getZdid() + "'";
//								st.executeUpdate(sql2);
								
								ZdqxkzUtil util = new ZdqxkzUtil();
								String flgg = lst.getFlgg();
								String bftg = lst.getBftg();
								String bfbtg = lst.getBfbtg();
								String sbz = "";
								bftg = util.getBftg(bftg, "9");
								if(util.getShengbz(flgg, bftg)){
									sbz = "1";
								}else{
									sbz = util.checkKong(bfbtg)?"3":"5";
								}
								String sql34 = "update QSKZ c set c.SHENGSHBZ='"+sbz+"',c.shengshsj=TO_DATE('"+nowtime+"','yyyy-mm-dd hh24:mi:ss'),c.BFTG='"+bftg+"'  where  c.zdid = '" + lst.getZdid() + "' and c.id = '"+lst.getId()+"'";
								st.executeUpdate(sql34);
							}

						} else if ("zdsx07".equals(lst.getNewproperty())) {// 站点属性为IDC机房zdsx07
							String ssql = "",sql3 = "", sql12 = "";
							String s = lst.getNewqsdbdl();
							double d = 0.0, dd = 0.0;
							if ("0".equals(s) || null == s || "".equals(s)) {
								d = Format.str_d(lst.getNewzlfh()) * 54 * 24 / 1000 / 0.85;
								dd = Format.str_d(lst.getNewjlfh()) * 220 * 24 / 1000;
								if (d >= dd) {
									s = String.valueOf(dd);
								} else if (d <= dd) {
									s = String.valueOf(d);
								}
							}
							sql12 = "select s.scb from scb s where s.zdid = '" + lst.getZdid() + "'";
							rs7 = st.executeQuery(sql12);
							if (rs7.next()) {
								sql3 = "UPDATE SCB S SET S.SCB=" + s + " WHERE S.ZDID ='" + lst.getZdid() + "'";
								st.executeUpdate(sql3);
							}else{
								String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2013-12','" + lst.getZdid() + "'," + s + ",'" + lg + "'," + sj + "," + 0 + ")";
								st.executeUpdate(sql22);
							}
							if(kts>0){
								if (j >= 9) {
									ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + ii5 + " AND aa.ZLFH <=" + ii6
											+ " THEN " + ii7 + " WHEN aa.ZLFH >" + ii6 + " AND aa.ZLFH <=" + ii9 + " THEN " + ii10 + " WHEN aa.ZLFH > " + ii9
											+ " AND aa.ZLFH <= " + ii12 + " THEN " + ii13 + " WHEN aa.ZLFH > " + ii12 + " AND aa.ZLFH <= " + ii15 + " THEN " + ii16
											+ " WHEN aa.ZLFH > " + ii15 + " AND aa.ZLFH <= " + ii18 + " THEN " + ii19 + " WHEN aa.ZLFH > " + ii18 + " THEN " + ii22
											+ " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf + " from yfxs y where y.shicode = q.shi)"
											+ " ),q.scb=" + s + " where q.id='" + lst.getZdid() + "'";
								} else {
									ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + ii5 + " AND aa.ZLFH <" + ii6
											+ " THEN " + ii7 + " WHEN aa.ZLFH >=" + ii6 + " AND aa.ZLFH <" + ii8 + " THEN " + ii10 + " WHEN aa.ZLFH >= " + ii8
											+ " AND aa.ZLFH <" + ii11 + " THEN " + ii13 + " WHEN aa.ZLFH >= " + ii11 + " AND aa.ZLFH < " + ii14 + " THEN " + ii16
											+ " WHEN aa.ZLFH >= " + ii14 + " AND aa.ZLFH < " + ii17 + " THEN " + ii19 + " WHEN aa.ZLFH >= " + ii17 + " THEN "
											+ ii22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
											+ syf + " from yfxs y where y.shicode = q.shi)" + " ),q.scb=" + s + " where q.id='" + lst.getZdid() + "'";
								}
							}else{
								ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s + " where ZD.ID='" + lst.getZdid()+"'";
							}
							st.executeUpdate(ssql);
//							String	sql2 = " UPDATE ZHANDIAN ZD SET ZD.PROPERTY='" + lst.getNewproperty() + "',ZD.STATIONTYPE='" + lst.getNewstationtype() + "',ZD.GXXX='" + lst.getNewgxxx()
//									+ "',ZD.GDFS='" + lst.getNewgdfs() + "',ZD.ZGD='" + lst.getNewzgd() + "',ZD.AREA='" + lst.getNewarea() + "',ZD.QYZT='" + lst.getNewqyzt() 
//									+ "' " + ", ZD.G2='" + lst.getNewg2() + "',ZD.G3='" + lst.getNewg3() + "',ZD.ZPSL='" + lst.getNewzpsl() + "',ZD.ZSSL='" + lst.getNewzssl()
//									+ "',ZD.CHANGJIA='" + lst.getNewchangjia() + "',ZD.JZLX='" + lst.getNewjzlx() + "',ZD.SHEBEI='" + lst.getNewshebei() + "',ZD.BBU='"
//									+ lst.getNewbbu() + "',ZD.RRU='" + lst.getNewrru() + "',ZD.YDSHEBEI='" + lst.getNewydshebei() + "',ZD.GXGWSL='" + lst.getNewgxgwsl()
//									+ "',ZD.YFLX='" + lst.getNewyflx() + "' WHERE ZD.ID='" + lst.getZdid() + "'";
//							st.executeUpdate(sql2);
							
							ZdqxkzUtil util = new ZdqxkzUtil();
							String flgg = lst.getFlgg();
							String bftg = lst.getBftg();
							String bfbtg = lst.getBfbtg();
							String sbz = "";
							bftg = util.getBftg(bftg, "9");
							if(util.getShengbz(flgg, bftg)){
								sbz = "1";
							}else{
								sbz = util.checkKong(bfbtg)?"3":"5";
							}
							String sql34 = "update QSKZ c set c.SHENGSHBZ='"+sbz+"',c.shengshsj=TO_DATE('"+nowtime+"','yyyy-mm-dd hh24:mi:ss'),c.BFTG='"+bftg+"'  where  c.zdid = '" + lst.getZdid() + "' and c.id = '"+lst.getId()+"'";
							st.executeUpdate(sql34);
						}
					}

					sql = " UPDATE ZHANDIAN ZD SET ZD.BGSIGN='" + lst.getNewbgsign() + "',ZD.DIANFEI='" + lst.getNewdianfei() + "',ZD.EDHDL='" + lst.getNewedhdl() + "',ZD.JLFH='"
							+ lst.getNewjlfh() + "',ZD.ZLFH='" + lst.getNewzlfh() + "' WHERE ZD.ID='" + lst.getZdid() + "'";
					ssqll = "UPDATE DIANBIAO D SET D.DANJIA='" + lst.getNewdianfei() + "',D.DBDS='" +lst.getDbds()+"',D.XGBZW='" +lst.getDsbzw()+"',D.DFZFLX='" +lst.getNewdfzflx()+"'  WHERE D.DBID IN ('" + lst.getDbid() + "')";  //修改电表的 单价，电表修改读数，读数修改标志位
					String sqlsheng = "update QSKZ c set c.SHENGSHBZ='1',c.shengshsj=TO_DATE('"+nowtime+"','yyyy-mm-dd hh24:mi:ss'), c.BFTG=NULL,c.BFBTG=NULL  where c.id = '"+lst.getId()+"'";

					st.executeUpdate(sql);
					st.executeUpdate(ssqll);
					if("1".equals(lst.getQxpdbz())){//只到市级不到省级
					st.executeUpdate(sqlsheng);
					}
				}
			}
			//市级审核成功
			String 	sqlqskz = "update QSKZ c set c.SHISHBZ='1',c.SHISHR='" + loginName + "',c.SHISHSJ=TO_DATE('"+nowtime+"','yyyy-mm-dd hh24:mi:ss') where  c.zdid in (" + id + ") and c.id in (" + idd + ")";
			st.executeUpdate(sqlqskz);
			connc.commit();
			msg = 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connc.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connc.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.free(rs, null, null);
			db.free(rs1, null, null);
			db.free(rs2, null, null);
			db.free(rs3, null, null);
			db.free(rs4, null, null);
			db.free(rs5, null, null);
			db.free(rs6, null, null);
			db.free(rs7, st, connc);
		}

		return msg;
	}
	
	public synchronized int SHIUpdateZDssh(String id, List<Zdqxkz> ls) {
		int msg = 0;
		CTime ctime = new CTime();
		String inserttime = ctime.formatWholeDate(new Date());
		String sql = "";

		DataBase db = new DataBase();

		if (ls != null) {
			for (Zdqxkz lst : ls) {
				// lst.getNewbgsign(),
				sql = " UPDATE ZHANDIAN ZD SET ZD.BGSIGN='"
						+ lst.getOldbgsign() + "',ZD.DIANFEI='"
						+ lst.getOlddianfei() + "',ZD.EDHDL='"
						+ lst.getOldedhdl() + "',ZD.JLFH='" + lst.getOldjlfh()
						+ "',ZD.ZLFH='" + lst.getOldzlfh() + "' WHERE ZD.ID='"
						+ lst.getZdid() + "'";
				System.out.println("市级审核更新表sql：" + sql.toString());
				try {

					db.connectDb();
					db.update(sql.toString());

					// db.commit();
					msg = 1;
				} catch (DbException e) {
					e.printStackTrace();
				} finally {
					try {

						db.close();
					} catch (Exception de) {
						de.printStackTrace();
					}
				}
			}
		}
		return msg;
	}
	public synchronized int QXShiUpdateZD(String id, List<Zdqxkz> ls,String loginName,String cause,String idd) {
		Connection connc = null;
		PreparedStatement ps = null;
		PreparedStatement psa = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		Statement st = null;
		int msg = 0;
		String sql = " UPDATE ZHANDIAN ZD SET ZD.BGSIGN=?,ZD.DIANFEI=?,ZD.EDHDL=?,ZD.JLFH=?,ZD.ZLFH=?,ZD.MEMO=?,ZD.QSDBDL=?,ZD.SCB=? WHERE ZD.ID=?";
		String sql1a = "UPDATE SCB S SET S.SCB=? WHERE S.ZDID=?";
		String ssqll = "UPDATE DIANBIAO D SET D.DANJIA=?,D.DBDS=?,D.XGBZW=?,D.DFZFLX=?  WHERE D.DBID=?";  //修改电表的 单价，电表修改读数，读数修改标志位
		String sql2 = "update QSKZ c set c.SHISHBZ='0',c.SHISHR='" + loginName + "',c.sjly='" + cause
		+ "',c.SHISHSJ=sysdate where  c.zdid in (" + id + ") AND C.ID in (" + idd + ") ";
		String sql3 = "UPDATE QSKZ C SET C.SHENGSHBZ='0',C.BFTG=NULL,C.BFBTG=NULL where C.ID=?";
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = "市级审核--" + sdf.format(d);
		DataBase db = new DataBase();
		try {
			connc = db.getConnection();
			connc.setAutoCommit(false);
			ps = connc.prepareStatement(sql);
			psa = connc.prepareStatement(sql1a);
			ps1 = connc.prepareStatement(ssqll);
			ps2 = connc.prepareStatement(sql3);
			st = connc.createStatement();
			if (ls != null) {
				for (Zdqxkz lst : ls) {
					ps.setString(1, lst.getOldbgsign());
					ps.setString(2, lst.getOlddianfei());
					ps.setString(3, lst.getOldedhdl());
					ps.setString(4, lst.getOldjlfh());
					ps.setString(5, lst.getOldzlfh());
					ps.setString(6, s);
					ps.setString(7, lst.getOldqsdbdl());
					ps.setString(8, lst.getOldqsdb());
					ps.setString(9, lst.getZdid());
					
					ps.addBatch();
					
					psa.setString(1, lst.getOldqsdb());
					psa.setString(2, lst.getZdid());
					
					psa.addBatch();
					
					ps1.setString(1, lst.getOlddianfei());
					ps1.setString(2, lst.getOlddbds());
					ps1.setString(3, lst.getOldxgbzw());
					ps1.setString(4, lst.getOlddfzflx());
					ps1.setString(5, lst.getDbid());
					
					ps1.addBatch();
					
					ps2.setString(1, lst.getId());
					if("1".equals(lst.getShengshbz()) && "".equals(lst.getShengshr())){
						ps2.executeUpdate();
					}
				}
				ps.executeBatch();
				psa.executeBatch();
				ps1.executeBatch();
			}
			st.executeUpdate(sql2);
			connc.commit();
			msg = 1;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connc.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				connc.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.free(null, ps, null);
			db.free(null, psa, null);
			db.free(null, ps1, null);
			db.free(null, ps2, null);
			db.free(null, st, connc);
		}
		
		return msg;
	}

	public synchronized int SHENGUpdateZD(String id, List<Zdqxkz> ls,
			String syf, String b1, String b2, String b3, String bb5,
			String bb6, String bb7, String bb8, String bb9, String bb10,
			String bb11, String bb12, String bb13, String bb14, String bb15,
			String bb16, String bb17, String bb18, String bb19, String bb20,
			String bb22, String dd5, String dd6, String dd7, String dd8,
			String dd9, String dd10, String dd11, String dd12, String dd13,
			String dd14, String dd15, String dd16, String dd17, String dd18,
			String dd19, String dd20, String dd22, String xx5, String xx6,
			String xx7, String xx8, String xx9, String xx10, String xx11,
			String xx12, String xx13, String xx14, String xx15, String xx16,
			String xx17, String xx18, String xx19, String xx20, String xx22,
			String yy5, String yy6, String yy7, String yy8, String yy9,
			String yy10, String yy11, String yy12, String yy13, String yy14,
			String yy15, String yy16, String yy17, String yy18, String yy19,
			String yy20, String yy22, String qq5, String qq6, String qq7,
			String qq8, String qq9, String qq10, String qq11, String qq12,
			String qq13, String qq14, String qq15, String qq16, String qq17,
			String qq18, String qq19, String qq20, String qq22, String ii5,
			String ii6, String ii7, String ii8, String ii9, String ii10,
			String ii11, String ii12, String ii13, String ii14, String ii15,
			String ii16, String ii17, String ii18, String ii19, String ii20,
			String ii22, String jj5, String jj6, String jj7, String jj8,
			String jj9, String jj10, String jj11, String jj12, String jj13,
			String jj14, String jj15, String jj16, String jj17, String jj18,
			String jj19, String jj20, String jj22, String lg, String loginName, String idd) {
		int msg = 0;
		DataBase db = new DataBase();
		Connection connc = null;
		Statement st = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");// 设置日期格式
		String sff = df.format(new Date());
		String ssyf = sff.toString().substring(5, 7);
		int j = Integer.parseInt(ssyf);
		ResultSet rs = null;
		String sj = "SYSDATE";
		try {
			connc = db.getConnection();
			connc.setAutoCommit(false);
			st = connc.createStatement();
			if (ls != null) {
				for (Zdqxkz lst : ls) {
					String sqlkongtiao = "SELECT ZD.KTS FROM ZHANDIAN ZD WHERE ZD.ID = '"+ lst.getZdid()+"'";//查询 有无 空调
					rs = st.executeQuery(sqlkongtiao);
					int kts = 0;
					if(rs.next()){
						kts = rs.getInt("KTS");
					}
					String ssql = "", sql2 = "", sql3 = "", sql12 = "";
					String s = lst.getNewqsdbdl();
					double d = 0.0, dd = 0.0;
					if ("0".equals(s) || null == s || "".equals(s)) {
						d = Format.str_d(lst.getNewzlfh()) * 54 * 24 / 1000 / 0.85;
						dd = Format.str_d(lst.getNewjlfh()) * 220 * 24 / 1000;
						if (d >= dd) {
							s = String.valueOf(dd);
						} else if (d <= dd) {
							s = String.valueOf(d);
						}
					}
					sql12 = "(SELECT S.SCB FROM SCB S WHERE S.ZDID = " + lst.getZdid() + ")";
					rs = st.executeQuery(sql12);
					if (rs.next()) {
						sql3 = "UPDATE SCB S SET S.SCB=" + s + " WHERE S.ZDID =" + lst.getZdid() + "";
						st.executeUpdate(sql3);
					}else{
						String sql22 = "INSERT INTO SCB(DBMONTH,ZDID,SCB,MODIFIER,MODIFICATIONTIME,YSCB) VALUES('2014-08'," + lst.getZdid() + "," + s + ",'" + lg + "'," + sj + "," + 0 + ")";
						st.executeUpdate(sql22);
					}
					if ("zdsx02".equals(lst.getNewproperty())) {//基站
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + bb5 + " AND aa.ZLFH <=" + bb6
										+ " THEN " + bb7 + " WHEN aa.ZLFH >" + bb6 + " AND aa.ZLFH <=" + bb9 + " THEN " + bb10 + " WHEN aa.ZLFH > " + bb9
										+ " AND aa.ZLFH <= " + bb12 + " THEN " + bb13 + " WHEN aa.ZLFH > " + bb12 + " AND aa.ZLFH <= " + bb15 + " THEN " + bb16
										+ " WHEN aa.ZLFH > " + bb15 + " AND aa.ZLFH <= " + bb18 + " THEN " + bb19
										+ " WHEN aa.ZLFH > " + bb18 + " THEN " + bb22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " +(SELECT DECODE(aa.YFLX, 'fwlx01', " + b1 + ", 'fwlx02',"
										+ b2 + ", 'fwlx03'," + b3 + ") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID ) ), q.scb = " + s + " where q.id=(" + lst.getZdid() + ")";
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + bb5 + " AND aa.ZLFH <" + bb6
										+ " THEN " + bb7 + " WHEN aa.ZLFH >=" + bb6 + " AND aa.ZLFH <" + bb8 + " THEN " + bb10 + " WHEN aa.ZLFH >= " + bb8
										+ " AND aa.ZLFH <" + bb11 + " THEN " + bb13 + " WHEN aa.ZLFH >= " + bb11 + " AND aa.ZLFH < " + bb14 + " THEN " + bb16
										+ " WHEN aa.ZLFH >= " + bb14 + " AND aa.ZLFH < " + bb17 + " THEN " + bb19 + " WHEN aa.ZLFH >= " + bb17 + " THEN "
										+ bb22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf + " from yfxs y where y.shicode = q.shi)"
										+ " +(SELECT DECODE(aa.YFLX, 'fwlx01', " + b1 + ", 'fwlx02'," + b2 + ", 'fwlx03'," + b3
										+ ") as jcxs FROM ZHANDIAN aa WHERE aa.ID=Q.ID )), q.scb = " + s + " where q.id=" + lst.getZdid() + "";
							}
						}else{
							ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = " + lst.getZdid();
						}
						st.executeUpdate(ssql);
					} else if ("zdsx05".equals(lst.getNewproperty())) {//接入网
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * 1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + dd5 + " AND aa.ZLFH <=" + dd6
										+ " THEN " + dd7 + " WHEN aa.ZLFH >" + dd6 + " AND aa.ZLFH <=" + dd9 + " THEN " + dd10 + " WHEN aa.ZLFH > " + dd9
										+ " AND aa.ZLFH <= " + dd12 + " THEN " + dd13 + " WHEN aa.ZLFH > " + dd12 + " AND aa.ZLFH <= " + dd15 + " THEN " + dd16
										+ " WHEN aa.ZLFH > " + dd15 + " AND aa.ZLFH <= " + dd18 + " THEN " + dd19 + " WHEN aa.ZLFH > " + dd18 + " THEN " + dd22
										+ " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf + " from yfxs y where y.shicode = q.shi)" 
										+ ",q.scb = " + s + " where q.id=(" + lst.getZdid() + ")";
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + "*1 " + "+(SELECT (CASE WHEN aa.ZLFH >=" + bb5 + " AND aa.ZLFH <" + bb6 + " THEN "
										+ bb7 + " WHEN aa.ZLFH >=" + bb6 + " AND aa.ZLFH <" + bb8 + " THEN " + bb10 + " WHEN aa.ZLFH >= " + bb8 + " AND aa.ZLFH <"
										+ bb11 + " THEN " + bb13 + " WHEN aa.ZLFH >= " + bb11 + " AND aa.ZLFH < " + bb14 + " THEN " + bb16 + " WHEN aa.ZLFH >= "
										+ bb14 + " AND aa.ZLFH < " + bb17 + " THEN " + bb19 + " WHEN aa.ZLFH >= " + bb17 + " THEN " + bb22
										+ " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf + " from yfxs y where y.shicode = q.shi)"
										+ ", q.scb = " + s + " where q.id=" + lst.getZdid() + "";
							}
						}else{
							ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = " + lst.getZdid();
						}
						st.executeUpdate(ssql);

					} else if ("zdsx06".equals(lst.getNewproperty())) {
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + xx5 + " AND aa.ZLFH <="
										+ xx6 + " THEN " + xx7 + " WHEN aa.ZLFH >" + xx6 + " AND aa.ZLFH <=" + xx9 + " THEN " + xx10 + " WHEN aa.ZLFH > "
										+ xx9 + " AND aa.ZLFH <= " + xx12 + " THEN " + xx13 + " WHEN aa.ZLFH > " + xx12 + " AND aa.ZLFH <= " + xx15
										+ " THEN " + xx16 + " WHEN aa.ZLFH > " + xx15 + " AND aa.ZLFH <= " + xx18 + " THEN " + xx19 + " WHEN aa.ZLFH > "
										+ xx18 + " THEN " + xx22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id=(" + lst.getZdid() + ")";
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + xx5 + " AND aa.ZLFH <"
										+ xx6 + " THEN " + xx7 + " WHEN aa.ZLFH >=" + xx6 + " AND aa.ZLFH <" + xx8 + " THEN " + xx10 + "WHEN aa.ZLFH >= "
										+ xx8 + " AND aa.ZLFH <" + xx11 + " THEN " + xx13 + " WHEN aa.ZLFH >= " + xx11 + " AND aa.ZLFH < " + xx14
										+ " THEN " + xx16 + " WHEN aa.ZLFH >= " + xx14 + " AND aa.ZLFH < " + xx17 + " THEN " + xx19 + " WHEN aa.ZLFH >= "
										+ xx17 + " THEN " + xx22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf
										+ " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id=" + lst.getZdid() + "";
							}
					}else{
						ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = " + lst.getZdid();
					}
						st.executeUpdate(ssql);

					} else if ("zdsx03".equals(lst.getNewproperty())) {
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * 1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + yy5 + " AND aa.ZLFH <=" + yy6 + " THEN "
										+ yy7 + " WHEN aa.ZLFH >" + yy6 + " AND aa.ZLFH <=" + yy9 + " THEN " + yy10 + " WHEN aa.ZLFH > " + yy9 + " AND aa.ZLFH <= "
										+ yy12 + " THEN " + yy13 + " WHEN aa.ZLFH > " + yy12 + " AND aa.ZLFH <= " + yy15 + " THEN " + yy16 + " WHEN aa.ZLFH > "
										+ yy15 + " AND aa.ZLFH <= " + yy18 + " THEN " + yy19 + " WHEN aa.ZLFH > " + yy18 + " THEN " + yy22
										+ " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf + " from yfxs y where y.shicode = q.shi)"
										+ ", q.scb = " + s + " where q.id=(" + lst.getZdid() + ")";
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + " *1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + yy5 + " AND aa.ZLFH <" + yy6
										+ " THEN " + yy7 + " WHEN aa.ZLFH >=" + yy6 + " AND aa.ZLFH <" + yy8 + " THEN " + yy10 + " WHEN aa.ZLFH >= "
										+ yy8 + " AND aa.ZLFH <" + yy11 + " THEN " + yy13 + " WHEN aa.ZLFH >= " + yy11 + " AND aa.ZLFH < " + yy14
										+ " THEN " + yy16 + " WHEN aa.ZLFH >= " + yy14 + " AND aa.ZLFH < " + yy17 + " THEN " + yy19 + " WHEN aa.ZLFH >= " + yy17
										+ " THEN " + yy22  + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " , q.scb = " + s + " where q.id=" + lst.getZdid() + "";
							}
						}else{
							ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = " + lst.getZdid();
						}
						st.executeUpdate(ssql);

					} else if ("zdsx04".equals(lst.getNewproperty())) {//其他
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + qq5 + " AND aa.ZLFH <="
										+ qq6 + " THEN " + qq7 + " WHEN aa.ZLFH >" + qq6 + " AND aa.ZLFH <=" + qq9 + " THEN " + qq10 + " WHEN aa.ZLFH > "
										+ qq9 + " AND aa.ZLFH <= " + qq12 + " THEN " + qq13 + " WHEN aa.ZLFH > " + qq12 + " AND aa.ZLFH <= " + qq15
										+ " THEN " + qq16 + " WHEN aa.ZLFH > " + qq15 + " AND aa.ZLFH <= " + qq18 + " THEN " + qq19 + " WHEN aa.ZLFH > "
										+ qq18 + " THEN " + qq22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id=(" + lst.getZdid() + ")";
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + qq5 + " AND aa.ZLFH <"
										+ qq6 + " THEN " + qq7 + " WHEN aa.ZLFH >=" + qq6 + " AND aa.ZLFH <" + qq8 + " THEN " + qq10 + " WHEN aa.ZLFH >= "
										+ qq8 + " AND aa.ZLFH <" + qq11 + " THEN " + qq13 + " WHEN aa.ZLFH >= " + qq11 + " AND aa.ZLFH < " + qq14
										+ " THEN " + qq16 + " WHEN aa.ZLFH >= " + qq14 + " AND aa.ZLFH < " + qq17 + " THEN " + qq19 + " WHEN aa.ZLFH >= "
										+ qq17 + " THEN " + qq22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id=" + lst.getZdid() + "";
							}
					}else{
						ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = " + lst.getZdid();
					}
						st.executeUpdate(ssql);

					} else if ("zdsx07".equals(lst.getNewproperty())) {//idc机房
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + ii5 + " AND aa.ZLFH <=" + ii6
										+ " THEN " + ii7 + " WHEN aa.ZLFH >" + ii6 + " AND aa.ZLFH <=" + ii9 + " THEN " + ii10 + " WHEN aa.ZLFH > " + ii9
										+ " AND aa.ZLFH <= " + ii12 + " THEN " + ii13 + " WHEN aa.ZLFH > " + ii12 + " AND aa.ZLFH <= " + ii15 + " THEN "
										+ ii16 + " WHEN aa.ZLFH > " + ii15 + " AND aa.ZLFH <= " + ii18 + " THEN " + ii19 + " WHEN aa.ZLFH > " + ii18
										+ " THEN " + ii22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id=(" + lst.getZdid() + ")";
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + ii5 + " AND aa.ZLFH <" + ii6
										+ " THEN " + ii7 + " WHEN aa.ZLFH >=" + ii6 + " AND aa.ZLFH <" + ii8 + " THEN " + ii10 + " WHEN aa.ZLFH >= " + ii8
										+ " AND aa.ZLFH <" + ii11 + " THEN " + ii13 + " WHEN aa.ZLFH >= " + ii11 + " AND aa.ZLFH < " + ii14 + " THEN " + ii16
										+ " WHEN aa.ZLFH >= " + ii14 + " AND aa.ZLFH < " + ii17 + " THEN " + ii19 + " WHEN aa.ZLFH >= " + ii17 + " THEN "
										+ ii22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y." + syf
										+ " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id=" + lst.getZdid() + "";
							}
						}else{
							ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = " + lst.getZdid();
						}
						st.executeUpdate(ssql);

					} else if ("zdsx01".equals(lst.getNewproperty())) {
						if(kts > 0){
							if (j >= 9) {
								ssql = " update zhandian q set q.qsdbdl= " + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + jj5 + " AND aa.ZLFH <=" + jj6 + " THEN " + jj7 + " WHEN aa.ZLFH >" + jj6
										+ " AND aa.ZLFH <=" + jj9 + " THEN " + jj10 + " WHEN aa.ZLFH > " + jj9 + " AND aa.ZLFH <= " + jj12 + " THEN " + jj13 + " WHEN aa.ZLFH > "
										+ jj12 + " AND aa.ZLFH <= " + jj15 + " THEN " + jj16 + " WHEN aa.ZLFH > " + jj15 + " AND aa.ZLFH <= " + jj18 + " THEN " + jj19
										+ " WHEN aa.ZLFH > " + jj18 + " THEN " + jj22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id=(" + lst.getZdid() + ")";
	
							} else {
								ssql = "update zhandian q set q.qsdbdl=" + s + " * (1 + " + "(SELECT (CASE WHEN aa.ZLFH >=" + jj5 + " AND aa.ZLFH <" + jj6
										+ " THEN " + jj7 + " WHEN aa.ZLFH >=" + jj6 + " AND aa.ZLFH <" + jj8 + " THEN " + jj10 + " WHEN aa.ZLFH >= " + jj8
										+ " AND aa.ZLFH <" + jj11 + " THEN " + jj13 + " WHEN aa.ZLFH >= " + jj11 + " AND aa.ZLFH < " + jj14 + " THEN " + jj16
										+ " WHEN aa.ZLFH >= " + jj14 + " AND aa.ZLFH < " + jj17 + " THEN " + jj19 + " WHEN aa.ZLFH >= " + jj17 + " THEN "
										+ jj22 + " ELSE 0 END) ZLFHXS FROM ZHANDIAN aa WHERE aa.ID = q.id) * (select y."
										+ syf + " from yfxs y where y.shicode = q.shi)" + " ), q.scb = " + s + " where q.id=" + lst.getZdid() + "";
							}
					}else{
						ssql = "UPDATE ZHANDIAN ZD SET ZD.QSDBDL = " + s +", ZD.SCB = " + s +" WHERE ZD.ID = " + lst.getZdid();
					}
						st.executeUpdate(ssql);

					}
					sql2 = " UPDATE ZHANDIAN ZD SET ZD.PROPERTY='" + lst.getNewproperty() + "',ZD.STATIONTYPE='" + lst.getNewstationtype() + "',ZD.GXXX='"
							+ lst.getNewgxxx() + "',ZD.GDFS='" + lst.getNewgdfs() + "',ZD.ZGD='" + lst.getNewzgd() + "',ZD.AREA='" + lst.getNewarea() + "',ZD.QYZT='"
							+ lst.getNewqyzt() + "' " + ", ZD.G2='" + lst.getNewg2() + "',ZD.G3='" + lst.getNewg3() + "',ZD.ZPSL='" + lst.getNewzpsl()
							+ "',ZD.ZSSL='" + lst.getNewzssl() + "',ZD.CHANGJIA='" + lst.getNewchangjia() + "',ZD.JZLX='" + lst.getNewjzlx() + "',ZD.SHEBEI='" + lst.getNewshebei()
							+ "',ZD.BBU='" + lst.getNewbbu() + "',ZD.RRU='" + lst.getNewrru() + "',ZD.YDSHEBEI='" + lst.getNewydshebei() + "',ZD.GXGWSL='"
							+ lst.getNewgxgwsl() + "',ZD.YFLX='" + lst.getNewyflx() + "' WHERE ZD.ID='" + lst.getZdid() + "'";
					st.executeUpdate(sql2);
					
					
					String sqlshengtg = "update QSKZ c set c.SHENGSHBZ='1',c.QXLY='',c.SHENGSHR='" + loginName + "',c.SHENGSHSJ=sysdate,C.BFBTG=NULL,C.BFTG='"+lst.getFlgg()+"' where  c.zdid = '" + lst.getZdid()
					+ "' and c.id  ='" + lst.getId() + "'";
					st.executeUpdate(sqlshengtg);
				}
			}
			connc.commit();
			msg = 1;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connc.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connc.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.free(rs, st, connc);
		}

		return msg;
	}

	public synchronized List<Zdqxkz> QXseachSheng(String bean) {
		List<Zdqxkz> retList = new ArrayList<Zdqxkz>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		String fzzdstr = "";
		try {
			// fzzdstr = getFuzeZdid(db,loginId);
			String sql = " SELECT KZ.ID KZID, KZ.ZDID,KZ.OLDPROPERTY,KZ.OLDSTATIONTYPE, KZ.OLDYFLX,KZ.OLDGXXX,KZ.OLDGDFS,KZ.OLDZGD,KZ.OLDAREA,KZ.OLDQYZT,"
					+ " kz.OLDg2,kz.OLDg3,kz.OLDzpsl,kz.OLDzssl,kz.OLDchangjia,kz.OLDjzlx,kz.OLDshebei,kz.OLDbbu,kz.OLDrru,kz.OLDydshebei,kz.OLDgxgwsl,kz.OLDQSDBDL,kz.oldqsdb,KZ.FLGG "
					+ " FROM QSKZ KZ WHERE KZ.ID IN (" + bean + ")";

			System.out.println("省审核查询新信息插入数据库sql：" + sql);
			db.connectDb();
			rs = db.queryAll(sql);

			while (rs.next()) {
				Zdqxkz ret = new Zdqxkz();
				ret.setId(rs.getString("KZID") != null ? rs.getString("KZID")
						: "");
				ret.setZdid(rs.getString("ZDID") != null ? rs.getString("ZDID")
						: "");
				ret.setOldg2(rs.getString("OLDG2") != null ? rs
						.getString("OLDG2") : "0");
				ret.setOldg3(rs.getString("OLDG3") != null ? rs
						.getString("OLDG3") : "0");
				ret.setOldzpsl(rs.getString("OLDZPSL") != null ? rs
						.getString("OLDZPSL") : "0");
				ret.setOldzssl(rs.getString("OLDZSSL") != null ? rs
						.getString("OLDZSSL") : "0");
				ret.setOldchangjia(rs.getString("OLDCHANGJIA") != null ? rs
						.getString("OLDCHANGJIA") : "");
				ret.setOldjzlx(rs.getString("OLDJZLX") != null ? rs
						.getString("OLDJZLX") : "");
				ret.setOldshebei(rs.getString("OLDSHEBEI") != null ? rs
						.getString("OLDSHEBEI") : "");
				ret.setOldbbu(rs.getString("OLDBBU") != null ? rs
						.getString("OLDBBU") : "0");
				ret.setOldrru(rs.getString("OLDRRU") != null ? rs
						.getString("OLDRRU") : "0");
				ret.setOldydshebei(rs.getString("OLDYDSHEBEI") != null ? rs
						.getString("OLDYDSHEBEI") : "0");
				ret.setOldgxgwsl(rs.getString("OLDGXGWSL") != null ? rs
						.getString("OLDGXGWSL") : "0");

				ret.setOldproperty(rs.getString("OLDPROPERTY") != null ? rs
						.getString("OLDPROPERTY") : "");
				ret
						.setOldstationtype(rs.getString("OLDSTATIONTYPE") != null ? rs
								.getString("OLDSTATIONTYPE")
								: "");
				ret.setOldyflx(rs.getString("OLDYFLX") != null ? rs
						.getString("OLDYFLX") : "");
				ret.setOldgxxx(rs.getString("OLDGXXX") != null ? rs
						.getString("OLDGXXX") : "");
				ret.setOldgdfs(rs.getString("OLDGDFS") != null ? rs
						.getString("OLDGDFS") : "");
				ret.setOldzgd(rs.getString("OLDZGD") != null ? rs
						.getString("OLDZGD") : "0");
				ret.setOldarea(rs.getString("OLDAREA") != null ? rs
						.getString("OLDAREA") : "0");
				ret.setOldqyzt(rs.getString("OLDQYZT") != null ? rs
						.getString("OLDQYZT") : "0");
				ret.setOldqsdbdl(rs.getString("OLDQSDBDL") != null ? rs
						.getString("OLDQSDBDL") : "");
				ret.setOldqsdb(rs.getString("OLDQSDB") != null ? rs
						.getString("OLDQSDB") : "0");
				ret.setFlgg(rs.getString("FLGG") != null ? rs
						.getString("FLGG") : "");
				retList.add(ret);
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
		return retList;
	}
	//全省定标定量 省级不通过时不做修改 2014-8-18 WangYiXiao
	public synchronized int QXSHENGUpdateZD(String id, List<Zdqxkz> ls,String loginName,String cause,String idd) {
		int msg = 0;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		DataBase db = new DataBase();
		Connection connc = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		Statement st = null;
		sql = " UPDATE ZHANDIAN ZD SET ZD.PROPERTY=?,ZD.STATIONTYPE=?,ZD.GXXX=?,ZD.GDFS=?,ZD.ZGD=?,ZD.AREA=?,ZD.QYZT=? " 
			+ ", ZD.G2=?,ZD.G3=?,ZD.ZPSL=?,ZD.ZSSL=?,ZD.CHANGJIA=?,ZD.JZLX=?,ZD.SHEBEI=?,ZD.BBU=?,ZD.RRU=?,ZD.YDSHEBEI=?"
			+ ",ZD.GXGWSL=?,ZD.yflx=?,ZD.QSDBDL=?,ZD.SCB=? WHERE ZD.ID=?";
		sql1 = "UPDATE SCB S SET S.SCB=? WHERE S.ZDID=?";
		
		try {
			connc = db.getConnection();
			connc.setAutoCommit(false);
			ps = connc.prepareStatement(sql);
			ps1 = connc.prepareStatement(sql1);
			st = connc.createStatement();
			
			if (ls != null) {
				for (Zdqxkz lst : ls) {
					ps.setString(1, lst.getOldproperty());
					ps.setString(2, lst.getOldstationtype());
					ps.setString(3, lst.getOldgxxx());
					ps.setString(4, lst.getOldgdfs());
					ps.setString(5, lst.getOldzgd());
					ps.setString(6, lst.getOldarea());
					ps.setString(7, lst.getOldqyzt());
					ps.setString(8, lst.getOldg2());
					ps.setString(9, lst.getOldg3());
					ps.setString(10, lst.getOldzpsl());
					ps.setString(11, lst.getOldzssl());
					ps.setString(12, lst.getOldchangjia());
					ps.setString(13, lst.getOldjzlx());
					ps.setString(14, lst.getOldshebei());
					ps.setString(15, lst.getOldbbu());
					ps.setString(16, lst.getOldrru());
					ps.setString(17, lst.getOldydshebei());
					ps.setString(18, lst.getOldgxgwsl());
					ps.setString(19, lst.getOldyflx());
					ps.setString(20, lst.getOldqsdbdl());
					ps.setString(21, lst.getOldqsdb());
					ps.setString(22, lst.getZdid());
					
					ps1.setString(1, lst.getOldqsdb());
					ps1.setString(2, lst.getZdid());
					
					System.out.println("省级审核更新表sql：" + sql.toString());
					System.out.println("不通过sql：" + sql1.toString());
					ps.executeUpdate();
					ps1.executeUpdate();
					sql2 = "update QSKZ c set c.SHENGSHBZ='2',c.SHENGSHR='" + loginName + "',c.QXLY='" + cause 
					+ "',c.SHENGSHSJ=sysdate,C.BFTG=NULL,C.BFBTG='"+lst.getFlgg()+"' where  c.zdid = '" + lst.getZdid() + "' and c.id = '" + lst.getId() + "'";
					System.out.println("省审核不通过的标志位：" + sql2);
					st.executeUpdate(sql2);
				}
			}
			connc.commit();
			msg = 1;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connc.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connc.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.free(null, ps, null);
			db.free(null, ps1, null);
			db.free(null, st, connc);
		}

		return msg;
	}

	// 区县申请附件查询
	public synchronized int CheckQs(String id) {
		String sql = "select t.FJNR from qskz t where t.id='" + id
				+ "' and t.FJMC is not null";
		System.out.println("区县申请附件查询:" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		int i = 0;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {

				i = 1;
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
		System.out.println("idddd" + i);
		return i;

	}

}
