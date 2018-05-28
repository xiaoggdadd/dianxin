package com.noki.zwhd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.zwhd.model.DianbiaoBean;
import com.noki.zwhd.model.HdjlBean;
import com.noki.zwhd.model.WydfftBean;
import com.noki.zwhd.model.ZhandianBean;

public class WyDaoImpl {
	private static Log log = LogFactory.getLog(WyDaoImpl.class.getName());

	public List<HdjlBean> searchWydffthdjl() {
		List<HdjlBean> hdjlList = new ArrayList<HdjlBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT YEARMONTH FROM TBL_TT_CWWY_HDJL WHERE CWORWY = 'WY' AND yearmonth is not null GROUP BY YEARMONTH order by yearmonth");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String yearmonth = rs.getString("YEARMONTH");

					HdjlBean hdjl = new HdjlBean();
					hdjl.setYEARMONTH(yearmonth);
					hdjlList.add(hdjl);
				}
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
		}
		return hdjlList;
	}

	public boolean saveWydfftHdjl(String loginName, String yearmonth) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO TBL_TT_CWWY_HDJL");
		sql.append("(HDRQ,ID,HDR,HDZT,CWORWY,YEARMONTH)");
		sql.append(" VALUES(");
		sql.append("'" + format.format(new Date())
				+ "',XL_TT_CWWY_HDJL.nextval,'" + loginName + "','0','WY','"
				+ yearmonth + "'");
		sql.append(" )");
		log.info("添加物业电费分摊数据记录SQL:" + sql.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			int i = db.update(sql.toString());
			if (i == 1) {
				return true;
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
		return false;
	}

	public boolean saveWydfft(WydfftBean bean) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO TBL_TT_WY_DFFT");
		sql.append(" (ID,JGJE,SFGS,DSFGS,ZDMC,ZDBM,DH,ZQ,JFJE,JFRQ,XZBS,JFPJLX,GDFMC,KH,FTBL,FSYZ,FTJE,TZHJE,TZYY,TZR,DYCWBXJE,DYCWHXJE,CY,KPLX,BZ,YEARMONTH)");
		sql.append(" VALUES");
		sql.append(" (");
		sql.append("XL_TT_WY_DFFT.nextval,");
		sql.append("'" + bean.getJGJE() + "',");
		sql.append("'" + bean.getSFGS() + "',");
		sql.append("'" + bean.getDSFGS() + "',");
		sql.append("'" + bean.getZDMC() + "',");
		sql.append("'" + bean.getZDBM() + "',");
		sql.append("'" + bean.getDH() + "',");
		sql.append("'" + bean.getZQ() + "',");
		sql.append("'" + bean.getJFJE() + "',");
		sql.append("'" + bean.getJFRQ() + "',");
		sql.append("'" + bean.getXZBS() + "',");
		sql.append("'" + bean.getJFPJLX() + "',");
		sql.append("'" + bean.getGDFMC() + "',");
		sql.append("'" + bean.getKH() + "',");
		sql.append("'" + bean.getFTBL() + "',");
		sql.append("'" + bean.getFSYZ() + "',");
		sql.append("'" + bean.getFTJE() + "',");
		sql.append("'" + bean.getTZHJE() + "',");
		sql.append("'" + bean.getTZYY() + "',");
		sql.append("'" + bean.getTZR() + "',");
		sql.append("'" + bean.getDYCWBXJE() + "',");
		sql.append("'" + bean.getDYCWHXJE() + "',");
		sql.append("'" + bean.getCY() + "',");
		sql.append("'" + bean.getKPLX() + "',");
		sql.append("'" + bean.getBZ()+"',");
		sql.append("'" + bean.getYEARMONTH() + "'");
		sql.append(" )");

		log.info("添加物业单位分摊数据SQL:" + sql.toString());

		DataBase db = new DataBase();
		try {
			db.connectDb();
			int i = db.update(sql.toString());
			System.out.println(i);
			if (i == 1) {
				return true;
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

		return false;
	}

	public List<WydfftBean> searchWydffft_fy(String loginName, String loginId,
			int pageSize, int curpage, String sheng, String shi, String xian,
			String xiaoqu, String _hdzt, String _qrzt, String _pc, String _zdbm,String _dh,String _cy) {
		List<WydfftBean> wydfftList = new ArrayList<WydfftBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT tt.*,ROWNUM AS rowno FROM (");
		sql.append("SELECT A.ID,RNDIQU(A.SFGS) AS SFGS,RNDIQU(A.DSFGS) AS DSFGS,A.ZDMC,A.ZDBM,A.DH,A.ZQ,A.JFJE,A.JFRQ,A.XZBS,A.JFPJLX,A.GDFMC,A.KH,A.FTBL,A.FSYZ,A.FTJE,A.KPLX,A.TZHJE,A.TZYY,A.TZR,A.DYCWBXJE,A.DYCWHXJE,A.JGJE,A.CY,A.YEARMONTH,A.HDZT,A.QRZT,A.BZ");
		sql.append(" FROM TBL_TT_WY_DFFT A,ZHANDIAN B WHERE  A.ZDBM = B.JZCODE ");
		if (!xiaoqu.equals("0")) {
			sql.append("AND B.XIAOQU = '" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			sql.append(" AND B.XIAN='" + xian + "'");
		} else if (!shi.equals("0")) {
			sql.append(" AND B.SHI='" + shi + "'");
		} else if (!sheng.equals("0")) {
			sql.append(" AND B.SHENG='" + sheng + "'");
		}

		if (!_hdzt.equals("0")) {
			sql.append(" and A.HDZT='" + _hdzt + "'");
		}

		if (!_qrzt.equals("0")) {
			sql.append(" AND A.QRZT='" + _qrzt + "'");
		}

		if (_pc != null && !_pc.equals("")) {
			sql.append(" AND A.YEARMONTH='" + _pc + "'");
		}

		if (_zdbm != null && !_zdbm.equals("")) {
			sql.append(" AND A.ZDBM='" + _zdbm + "'");
		}
		
		if(_dh!=null&&!_dh.equals("")){
			sql.append(" AND A.DH='" + _dh + "'");
		}
		if(_cy!=null&&!_cy.equals("")){
			if(_cy.equals("0")){
				
			}else if(_cy.equals("1")){
				sql.append(" AND A.CY='0.00'");
			}else if(_cy.equals("2")){
				sql.append(" AND A.CY!='0.00'");
			}
		}

		sql.append(" ORDER BY A.YEARMONTH DESC");
		sql.append(")tt WHERE ROWNUM <= " + (pageSize * curpage)
				+ ")table_alias WHERE table_alias.rowno >= "
				+ (pageSize * curpage - pageSize));
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					int rownum = rs.getInt("ROWNO");
					String id = rs.getString("ID");
					String sfgs = rs.getString("SFGS");
					String dsfgs = rs.getString("DSFGS");
					String zdmc = rs.getString("ZDMC")==null||rs.getString("ZDMC").equals("")||rs.getString("ZDMC").equals("null")?"":rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					String dh = rs.getString("DH");
					String zq = rs.getString("ZQ")==null||rs.getString("ZQ").equals("")||rs.getString("ZQ").equals("null")?"":rs.getString("ZQ");
					String jfje = rs.getString("JFJE");
					String jfrq = rs.getString("JFRQ")==null||rs.getString("JFRQ").equals("")||rs.getString("JFRQ").equals("null")?"":rs.getString("JFRQ");
					String xzbs = rs.getString("XZBS");
					String jfpjlx = rs.getString("JFPJLX");
					String gdfmc = rs.getString("GDFMC")==null||rs.getString("GDFMC").equals("")||rs.getString("GDFMC").equals("null")?"":rs.getString("GDFMC");
					String kh = rs.getString("KH")==null||rs.getString("KH").equals("")||rs.getString("KH").equals("null")?"":rs.getString("KH");
					String ftbl = rs.getString("FTBL");
					String fsyz = rs.getString("FSYZ");
					String ftje = rs.getString("FTJE");
					String kplx = rs.getString("KPLX");
					String tzhje = rs.getString("TZHJE") == null||rs.getString("TZHJE").equals("null") ? "" : rs
							.getString("TZHJE");
					String tzyy = rs.getString("TZYY") == null ||rs.getString("TZYY").equals("null")? "" : rs
							.getString("TZYY");
					String tzr = rs.getString("TZR") == null ||rs.getString("TZR").equals("null")? "" : rs
							.getString("TZR");
					String dycwbxje = rs.getString("DYCWBXJE") == null
							|| rs.getString("DYCWBXJE").equals("")
							|| rs.getString("DYCWBXJE").equals("null") ? ""
							: rs.getString("DYCWBXJE");
					String dycwhxje = rs.getString("DYCWHXJE") == null
							|| rs.getString("DYCWHXJE").equals("")
							|| rs.getString("DYCWHXJE").equals("null") ? ""
							: rs.getString("DYCWHXJE");
					String jgje = rs.getString("JGJE") == null
							|| rs.getString("JGJE").equals("")
							|| rs.getString("JGJE").equals("null") ? "" : rs
							.getString("JGJE");
					String cy = rs.getString("CY") == null
							|| rs.getString("CY").equals("")
							|| rs.getString("CY").equals("null") ? "" : rs
							.getString("CY");
					String yearmonth = rs.getString("YEARMONTH");
					String hdzt = rs.getString("HDZT");
					String qrzt = rs.getString("QRZT");
					String bz = rs.getString("BZ") == null ? "" : rs
							.getString("BZ");

					WydfftBean wydfft = new WydfftBean();
					wydfft.setBZ(bz);
					wydfft.setKPLX(kplx);
					wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(dsfgs);
					wydfft.setDYCWBXJE(dycwbxje);
					wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setRownum(rownum);
					wydfft.setSFGS(sfgs);
					wydfft.setTZHJE(tzhje);
					wydfft.setTZR(tzr);
					wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfft.setHDZT(hdzt);
					wydfft.setQRZT(qrzt);
					wydfftList.add(wydfft);
				}
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
		}
		return wydfftList;
	}

	public List<WydfftBean> searchWydfft(String wyyearmonth) {
		List<WydfftBean> wydfftList = new ArrayList<WydfftBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,SFGS,DSFGS,ZDMC,ZDBM,DH,ZQ,JFJE,JFRQ,XZBS,JFPJLX,GDFMC,KH,FTBL,FSYZ,FTJE,KPLX,TZHJE,TZYY,TZR,DYCWBXJE,DYCWHXJE,JGJE,CY,YEARMONTH");
		sql.append(" FROM TBL_TT_WY_DFFT WHERE YEARMONTH = '" + wyyearmonth
				+ "'");
		sql.append(" ORDER BY YEARMONTH DESC");
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String sfgs = rs.getString("SFGS");
					String dsfgs = rs.getString("DSFGS");
					String zdmc = rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					String dh = rs.getString("DH");
					String zq = rs.getString("ZQ");
					String jfje = rs.getString("JFJE");
					String jfrq = rs.getString("JFRQ");
					String xzbs = rs.getString("XZBS");
					String jfpjlx = rs.getString("JFPJLX");
					String gdfmc = rs.getString("GDFMC");
					String kh = rs.getString("KH");
					String ftbl = rs.getString("FTBL");
					String fsyz = rs.getString("FSYZ");
					String ftje = rs.getString("FTJE");
					String kplx = rs.getString("KPLX");
					String tzhje = rs.getString("TZHJE");
					String tzyy = rs.getString("TZYY");
					String tzr = rs.getString("TZR");
					String dycwbxje = rs.getString("DYCWBXJE");
					String dycwhxje = rs.getString("DYCWHXJE");
					String jgje = rs.getString("JGJE");
					String cy = rs.getString("CY");
					String yearmonth = rs.getString("YEARMONTH");

					WydfftBean wydfft = new WydfftBean();
					wydfft.setKPLX(kplx);
					wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(dsfgs);
					wydfft.setDYCWBXJE(dycwbxje);
					wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setSFGS(sfgs);
					wydfft.setTZHJE(tzhje);
					wydfft.setTZR(tzr);
					wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfftList.add(wydfft);
				}
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
		}
		return wydfftList;
	}

	public boolean updateWydfft_shzt(String yearmonth) {
		boolean flag = false;
		DataBase db = new DataBase();
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_WY_DFFT SET HDZT = '已核对' WHERE YEARMONTH = '"
				+ yearmonth + "'");
		log.info("物业数据电费分摊与财务已报销核对SQL:[" + sql.toString() + "]");
		try {
			db.getConnection();
			db.update(sql.toString());
			flag = true;
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
		return true;
	}

	public boolean updateWydfft(String wydfft_id, String wydfft_dybxdje,
			String wydfft_dyhxdje, String wydfft_jgje, String wydfft_cy,
			String wydfft_bz) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_WY_DFFT SET HDZT = '已核对', JGJE = '"
				+ wydfft_jgje + "',CY='" + wydfft_cy + "',DYCWBXJE = '"
				+ wydfft_dybxdje + "',DYCWHXJE = '" + wydfft_dyhxdje
				+ "',BZ = '" + wydfft_bz + "' WHERE ID = '" + wydfft_id + "'");
		log.info("物业数据电费分摊与财务已报销核对SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.update(sql.toString());
			flag = true;
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
		return true;
	}

	public boolean updateWydffthdjl(String wydfft_yearmonth) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_CWWY_HDJL SET HDZT = '已核对' WHERE YEARMONTH = '"
				+ wydfft_yearmonth + "'");
		log.info("物业数据电费分摊与财务已报销核对记录SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.update(sql.toString());
			flag = true;
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
		return true;
	}

	public WydfftBean searchWydfftById(String _id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,SFGS,DSFGS,ZDMC,ZDBM,DH,ZQ,JFJE,JFRQ,XZBS,JFPJLX,GDFMC,KH,FTBL,FSYZ,FTJE,TZHJE,TZYY,TZR,DYCWBXJE,DYCWHXJE,CY,YEARMONTH,KPLX,JGJE,HDZT,QRZT,CB_ZZLX,CB_YCQDW,DBBM,CB_SH,CB_NYGLXTID,CB_ZZS,CB_SZS,CB_QM,CB_ZM,LASTCBSJ,THISCBSJ,CB_DLIANG,BCDJ,CB_JFQSRQ,CB_JFZZRQ");
		sql.append(" FROM TBL_TT_WY_DFFT WHERE ID = " + _id + "");
		log.info("物业电费分摊修改SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String sfgs = rs.getString("SFGS");
					String dsfgs = rs.getString("DSFGS");
					String zdmc = rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					String dh = rs.getString("DH");
					String zq = rs.getString("ZQ");
					String jfje = rs.getString("JFJE");
					String jfrq = rs.getString("JFRQ");
					String xzbs = rs.getString("XZBS");
					String jfpjlx = rs.getString("JFPJLX");
					String gdfmc = rs.getString("GDFMC");
					String kh = rs.getString("KH");
					String ftbl = rs.getString("FTBL");
					String fsyz = rs.getString("FSYZ");
					String ftje = rs.getString("FTJE");
					String kplx = rs.getString("KPLX");
					String tzhje = rs.getString("TZHJE");
					String tzyy = rs.getString("TZYY");
					String tzr = rs.getString("TZR");
					String dycwbxje = rs.getString("DYCWBXJE");
					String dycwhxje = rs.getString("DYCWHXJE");
					String jgje = rs.getString("JGJE");
					String cy = rs.getString("CY");
					String yearmonth = rs.getString("YEARMONTH");
					String hdzt = rs.getString("HDZT");
					String qrzt = rs.getString("QRZT");
					String cb_zzlx = rs.getString("CB_ZZLX");
					String dbbm = rs.getString("DBBM");
					String cb_sh =  rs.getString("CB_SH");
					String cb_byglxtid =  rs.getString("CB_NYGLXTID");
					String cb_zzs =  rs.getString("CB_ZZS");
					String cb_szs =  rs.getString("CB_SZS");
					String qm =  rs.getString("CB_QM");
					String zm =  rs.getString("CB_ZM");
					String sccbsj =  rs.getString("LASTCBSJ");
					String bccbsj =  rs.getString("THISCBSJ");
					String dliang =  rs.getString("CB_DLIANG");
					String bcdj =  rs.getString("BCDJ");
					String jfqsrq = rs.getString("CB_JFQSRQ");
					String jfzzrq = rs.getString("CB_JFZZRQ");
					
					WydfftBean wydfft = new WydfftBean();
					wydfft.setDFDJ(bcdj);
					wydfft.setDLIANG(dliang);
					wydfft.setQM(qm);
					wydfft.setZM(zm);
					wydfft.setSccbsj(sccbsj);
					wydfft.setBccbsj(bccbsj);
					wydfft.setCB_SZS(cb_szs);
					wydfft.setCB_ZZS(cb_zzs);
					wydfft.setCB_NYGLXTID(cb_byglxtid);
					wydfft.setSh(cb_sh);
					wydfft.setDbbm(dbbm);
					wydfft.setCB_ZZLX(cb_zzlx);
					wydfft.setKPLX(kplx);
					wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(dsfgs);
					wydfft.setDYCWBXJE(dycwbxje);
					wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setSFGS(sfgs);
					wydfft.setTZHJE(tzhje);
					wydfft.setTZR(tzr);
					wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfft.setHDZT(hdzt);
					wydfft.setQRZT(qrzt);
					wydfft.setJfqsrq(jfqsrq);
					wydfft.setJfzzrq(jfzzrq);

					return wydfft;
				}
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
		}
		return null;
	}

	public boolean updateWydfft_qx(String id, String zthje, String ztyy,
			String tzr, String bz) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_WY_DFFT SET QRZT = '需修改', TZHJE = '" + zthje
				+ "',TZYY='" + ztyy + "',TZR = '" + tzr + "',BZ = '" + bz
				+ "' WHERE ID = '" + id + "'");
		log.info("区县电费分摊确认修改SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.update(sql.toString());
			flag = true;
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
		return true;
	}

	public boolean updateWydfft_yxg(WydfftBean bean) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_WY_DFFT");
		sql.append(" SET QRZT = '已修改',");
		sql.append(" JGJE = '" + bean.getJGJE() + "',");
		sql.append(" SFGS = '" + bean.getSFGS() + "',");
		sql.append(" DSFGS = '" + bean.getDSFGS() + "',");
		sql.append(" ZDMC = '" + bean.getZDMC() + "',");
		sql.append(" ZDBM = '" + bean.getZDBM() + "',");
		sql.append(" DH = '" + bean.getDH() + "',");
		sql.append(" ZQ = '" + bean.getZQ() + "',");
		sql.append(" JFJE = '" + bean.getJFJE() + "',");
		sql.append(" JFRQ = '" + bean.getJFRQ() + "',");
		sql.append(" XZBS = '" + bean.getXZBS() + "',");
		sql.append(" JFPJLX = '" + bean.getJFPJLX() + "',");
		sql.append(" GDFMC = '" + bean.getGDFMC() + "',");
		sql.append(" KH = '" + bean.getKH() + "',");
		sql.append(" FTBL = '" + bean.getFTBL() + "',");
		sql.append(" FSYZ = '" + bean.getFSYZ() + "',");
		sql.append(" FTJE = '" + bean.getFTJE() + "',");
		sql.append(" DYCWBXJE = '" + bean.getDYCWBXJE() + "',");
		sql.append(" DYCWHXJE = '" + bean.getDYCWHXJE() + "',");
		sql.append(" CY = '" + bean.getCY() + "',");
		sql.append(" KPLX = '" + bean.getKPLX() + "',");
		sql.append(" YEARMONTH = '" + bean.getYEARMONTH() + "'");
		sql.append(" WHERE ID = " + bean.getID() + "");

		log.info("修改物业单位分摊数据SQL:" + sql.toString());

		DataBase db = new DataBase();
		try {
			db.connectDb();
			int i = db.update(sql.toString());
			if (i == 0) {
				return true;
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

		return false;
	}

	public boolean updateDxdfft_yqr(String ids) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_WY_DFFT SET YYSZT = '已确认' WHERE ID IN(" + ids
				+ ")");
		log.info("电信电费分摊确认修改SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		try {
			db.connectDb();
			int i = db.update(sql.toString());
			if (i == 0) {
				return true;
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
		return false;
	}

	public boolean updateWydfft_yqr(String ids) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_WY_DFFT SET QRZT = '已确认' WHERE ID IN(" + ids
				+ ")");
		log.info("市级电费分摊确认修改SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		try {
			db.connectDb();
			int i = db.update(sql.toString());
			if (i == 0) {
				return true;
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
		return false;
	}

	public List<WydfftBean> searchWydfft_dxdfftqr(String loginName,
			String loginId, int pageSize, int curpage, String sheng,
			String shi, String xian, String xiaoqu, String _yysqrzt,String pc) {
		List<WydfftBean> wydfftList = new ArrayList<WydfftBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT tt.*,ROWNUM AS rowno FROM (");
		sql.append("SELECT A.ID,A.SFGS,A.DSFGS,A.ZDMC,A.ZDBM,A.DH,A.BCDJ,A.ZQ,A.JFJE,A.JFRQ,A.XZBS,A.JFPJLX,A.GDFMC,A.CB_JFQSRQ,A.CB_JFZZRQ,A.KH,A.FTBL,A.FSYZ,A.FTJE,A.KPLX,A.TZHJE,A.TZYY,A.TZR,A.DYCWBXJE,A.DYCWHXJE,A.JGJE,A.CY,A.YEARMONTH,A.HDZT,A.QRZT,A.BZ,A.YYSZT,");
		sql.append("B.sheng, rndiqu(B.sheng) SHENG_NAME,B.shi,rndiqu(B.shi) SHI_NAME,B.xian,rndiqu(B.xian) XIAN_NAME,A.CB_QM,A.CB_ZM,A.CB_DLIANG,A.CB_DLIU,");
		sql.append("RTNAME(B.gdfs) as gdfs,'抄表结算' JSFS,C.danjia,B.gdfname");
		sql.append(" FROM TBL_TT_WY_DFFT A,ZHANDIAN B,dianbiao C WHERE  A.ZDBM = B.JZCODE AND c.zdid = b.id AND A.QRZT = '已确认' AND KH = '电信' AND YYSXGZT = '已修改' ");
		if (xiaoqu!=null&&!xiaoqu.equals("0")) {
			sql.append("AND B.XIAOQU = '" + xiaoqu + "'");
		} else if (xian!=null&&!xian.equals("0")) {
			sql.append(" AND B.XIAN='" + xian + "'");
		} else if (shi!=null&&!shi.equals("0")) {
			sql.append(" AND B.SHI='" + shi + "'");
		} else if (!sheng.equals("0")) {
			sql.append(" AND B.SHENG='" + sheng + "'");
		}

		if (_yysqrzt!=null&&!_yysqrzt.equals("0")) {
			sql.append(" and A.YYSZT='" + _yysqrzt + "'");
		}
		if (pc!=null&&!pc.equals("0")) {
			sql.append(" and A.YEARMONTH='" + pc + "'");
		}
		sql.append(" ORDER BY A.YEARMONTH DESC");
		sql.append(")tt WHERE ROWNUM <= " + (pageSize * curpage)
				+ ")table_alias WHERE table_alias.rowno >= "
				+ (pageSize * curpage - pageSize));
		log.info("电信电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					int rownum = rs.getInt("ROWNO");
					String id = rs.getString("ID");
					String sfgs = rs.getString("SFGS");
					String dsfgs = rs.getString("DSFGS");
					String zdmc = rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					String dh = rs.getString("DH");
					String zq = rs.getString("ZQ");
					String dj = rs.getString("BCDJ")==null?"":rs.getString("BCDJ");
					String jfje = rs.getString("JFJE");
					String jfrq = rs.getString("JFRQ")==null?"":rs.getString("JFRQ");
					String xzbs = rs.getString("XZBS");
					String jfpjlx = rs.getString("JFPJLX");
					String gdfmc = rs.getString("GDFMC")==null?"": rs.getString("GDFMC");
					String kh = rs.getString("KH");
					String ftbl = rs.getString("FTBL");
					String fsyz = rs.getString("FSYZ");
					String ftje = rs.getString("FTJE");
					String kplx = rs.getString("KPLX");
					String tzhje = rs.getString("TZHJE");
					String tzyy = rs.getString("TZYY");
					String tzr = rs.getString("TZR");
					String dycwbxje = rs.getString("DYCWBXJE");
					String dycwhxje = rs.getString("DYCWHXJE");
					String jgje = rs.getString("JGJE");
					String cy = rs.getString("CY");
					String yearmonth = rs.getString("YEARMONTH");
					String hdzt = rs.getString("HDZT");
					String qrzt = rs.getString("QRZT");
					String bz = rs.getString("BZ");
					String yyszt = rs.getString("YYSZT");
					String xian_name = rs.getString("XIAN_NAME");
					String shi_name = rs.getString("SHI_NAME");
					String qm = rs.getString("CB_QM")==null?"":rs.getString("CB_QM");
					String zm = rs.getString("CB_ZM")==null?"":rs.getString("CB_ZM");
					String dliang = rs.getString("CB_DLIANG")==null?"":rs.getString("CB_DLIANG");
					String dliu = rs.getString("CB_DLIU")==null?"":rs.getString("CB_DLIU");
					String jsfs = rs.getString("JSFS");
					String gdfs = rs.getString("GDFS");
					//String danjia = rs.getString("DANJIA");
					String jfqs = rs.getString("CB_JFQSRQ");
					String jfzz  =rs.getString("CB_JFZZRQ");
					//String gdfname = rs.getString("GDFNAME")==null?"":rs.getString("GDFNAME");

					WydfftBean wydfft = new WydfftBean();
					wydfft.setGDFMC(gdfmc);
					//wydfft.setDFDJ(danjia);
					wydfft.setJSFS(jsfs);
					wydfft.setGDFS(gdfs);
					wydfft.setDLIU(dliu);
					wydfft.setDLIANG(dliang);
					wydfft.setQM(qm);
					wydfft.setZM(zm);
					wydfft.setQX(xian_name);
					wydfft.setYYSZT(yyszt);
					wydfft.setBZ(bz);
					wydfft.setKPLX(kplx);
					wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(xian_name);
					wydfft.setDYCWBXJE(dycwbxje);
					wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setRownum(rownum);
					wydfft.setSFGS(sfgs);
					wydfft.setTZHJE(tzhje);
					wydfft.setTZR(tzr);
					wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfft.setHDZT(hdzt);
					wydfft.setQRZT(qrzt);
					wydfft.setDFDJ(dj);
					wydfft.setJfqsrq(jfqs);
					wydfft.setJfzzrq(jfzz);
					wydfftList.add(wydfft);
				}
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
		}
		return wydfftList;
	}

	public List<WydfftBean> searchWydfft_ltdfftqr(String loginName,
			String loginId, int pageSize, int curpage, String sheng,
			String shi, String xian, String xiaoqu, String _yysqrzt,String _pc,String _zdbm,String _yysxgzt) {
		List<WydfftBean> wydfftList = new ArrayList<WydfftBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT tt.*,ROWNUM AS rowno FROM (");
		sql.append("SELECT A.ID,A.SFGS,rndiqu(A.DSFGS)DSFGS,A.ZDMC,A.ZDBM,A.DH,A.ZQ,A.JFJE,A.JFRQ,A.XZBS,A.JFPJLX,A.GDFMC,A.KH,A.FTBL,A.FSYZ,A.FTJE,A.KPLX,A.TZHJE,A.TZYY,A.TZR,A.DYCWBXJE,A.DYCWHXJE,A.JGJE,A.CY,A.YEARMONTH,A.HDZT,A.QRZT,A.BZ,A.YYSZT,");
		sql.append("B.sheng, rndiqu(B.sheng) SHENG_NAME,B.shi,rndiqu(B.shi) SHI_NAME,B.xian,rndiqu(B.xian) XIAN_NAME,A.CB_QM,A.CB_ZM,A.CB_DLIANG,A.CB_DLIU,");
		sql.append("RTNAME(B.gdfs) as gdfs,'抄表结算' JSFS,C.danjia,B.gdfname,B.ADDRESS,B.LONGITUDE,B.LATITUDE,'' AS ZCJJZHBM,C.dbbm,'' AS SH,A.CB_JFQSRQ,A.CB_JFZZRQ,");
		sql.append("A.THISCBSJ,A.LASTCBSJ,A.CB_NYGLXTID,A.CB_ZZS,A.CB_SZS,A.CB_ZCJJZSBM,A.CB_YCQDW,A.CB_ZZLX,B.WLZDBM,A.BCDJ,RTNAME(B.YCQ) YCQ");
		sql.append(" FROM TBL_TT_WY_DFFT A,ZHANDIAN B,dianbiao C WHERE  A.ZDBM = B.JZCODE AND c.zdid = b.id AND A.QRZT = '已确认' AND KH = '联通' AND YYSXGZT = '"+_yysxgzt+"' ");
		if (xiaoqu!=null&&!xiaoqu.equals("0")) {
			sql.append("AND B.XIAOQU = '" + xiaoqu + "'");
		} else if (xian!=null&&!xian.equals("0")) {
			sql.append(" AND B.XIAN='" + xian + "'");
		} else if (shi!=null&&!shi.equals("0")) {
			sql.append(" AND B.SHI='" + shi + "'");
		} else if (sheng!=null&&!sheng.equals("0")) {
			sql.append(" AND B.SHENG='" + sheng + "'");
		}else{
			sql.append("AND B.XIAOQU IN (select AGCODE from per_area t where t.accountid='"+loginId+"')");
		}

		if (_yysqrzt!=null&&!_yysqrzt.equals("0")) {
			sql.append(" and A.YYSZT='" + _yysqrzt + "'");
		}
		
		if (_pc!=null&&!_pc.equals("")) {
			sql.append(" and A.YEARMONTH='" + _pc + "'");
		}
		
		if (_zdbm!=null&&!_zdbm.equals("")) {
			sql.append(" and A.ZDBM='" + _zdbm + "'");
		}

		sql.append(" ORDER BY A.YEARMONTH DESC");
		sql.append(")tt WHERE ROWNUM <= " + (pageSize * curpage)
				+ ")table_alias WHERE table_alias.rowno >= "
				+ (pageSize * curpage - pageSize));
		log.info("联通电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					int rownum = rs.getInt("ROWNO");
					String yearmonth = rs.getString("YEARMONTH");
					String yyszt = rs.getString("YYSZT");
					String dsfgs = rs.getString("DSFGS");
					String ycqdw = rs.getString("CB_YCQDW")==null||rs.getString("CB_YCQDW").equals("")||rs.getString("CB_YCQDW").equals("null")?"":rs.getString("CB_YCQDW");
					String zdmc = rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					//物理站址
					String wlzdbm = rs.getString("WLZDBM")==null||rs.getString("WLZDBM").equals("")||rs.getString("WLZDBM").equals("null")?"":rs.getString("WLZDBM");
					String dh = rs.getString("DH");
					String gdfmc = rs.getString("GDFMC");
					String jfpjlx = rs.getString("JFPJLX");
					//站址类型
					String zzlx = rs.getString("CB_ZZLX")==null||rs.getString("CB_ZZLX").equals("")||rs.getString("CB_ZZLX").equals("null")?"":rs.getString("CB_ZZLX");
					
					String danjia = rs.getString("DANJIA")==null||rs.getString("DANJIA").equals("")||rs.getString("DANJIA").equals("null")?"0":rs.getString("DANJIA");
					DecimalFormat df = new DecimalFormat("#0.00");
					danjia = df.format(Double.parseDouble(danjia));
					
					String thiscbsj = rs.getString("THISCBSJ")==null||rs.getString("THISCBSJ").equals("")||rs.getString("THISCBSJ").equals("null")?"":rs.getString("THISCBSJ");
					String lastcbsj = rs.getString("LASTCBSJ")==null||rs.getString("LASTCBSJ").equals("")||rs.getString("LASTCBSJ").equals("null")?"":rs.getString("LASTCBSJ");
					String qm = rs.getString("CB_QM")==null||rs.getString("CB_QM").equals("")||rs.getString("CB_QM").equals("null")?"":rs.getString("CB_QM");
					String zm = rs.getString("CB_ZM")==null||rs.getString("CB_ZM").equals("")||rs.getString("CB_ZM").equals("null")?"":rs.getString("CB_ZM");
					String dliang = rs.getString("CB_DLIANG")==null||rs.getString("CB_DLIANG").equals("")||rs.getString("CB_DLIANG").equals("null")?"":rs.getString("CB_DLIANG");
					String jfje = rs.getString("JFJE");
					String ftbl = rs.getString("FTBL");
					String ftje = rs.getString("FTJE");
					String bz = rs.getString("BZ")==null||rs.getString("BZ").equals("")||rs.getString("BZ").equals("null")?"":rs.getString("BZ");
					String nyglxtid = rs.getString("CB_NYGLXTID")==null||rs.getString("CB_NYGLXTID").equals("")||rs.getString("CB_NYGLXTID").equals("null")?"":rs.getString("CB_NYGLXTID");
					String zzs = rs.getString("CB_ZZS")==null||rs.getString("CB_ZZS").equals("")||rs.getString("CB_ZZS").equals("null")?"":rs.getString("CB_ZZS");
					String szs = rs.getString("CB_SZS")==null||rs.getString("CB_SZS").equals("")||rs.getString("CB_SZS").equals("null")?"":rs.getString("CB_SZS");
					String bcdj = rs.getString("BCDJ")==null||rs.getString("BCDJ").equals("")||rs.getString("BCDJ").equals("null")?"":rs.getString("BCDJ");
					String ycq = rs.getString("YCQ")==null||rs.getString("YCQ").equals("")||rs.getString("YCQ").equals("null")?"":rs.getString("YCQ");
					String dbbm = rs.getString("DBBM")==null||rs.getString("DBBM").equals("")||rs.getString("DBBM").equals("null")?"":rs.getString("DBBM");
					String jfqsrq = rs.getString("CB_JFQSRQ")==null||rs.getString("CB_JFQSRQ").equals("")||rs.getString("CB_JFQSRQ").equals("null")?"":rs.getString("CB_JFQSRQ");;
					String jfzzrq = rs.getString("CB_JFZZRQ")==null||rs.getString("CB_JFZZRQ").equals("")||rs.getString("CB_JFZZRQ").equals("null")?"":rs.getString("CB_JFZZRQ");;
					
					
					WydfftBean wydfft = new WydfftBean();
					wydfft.setJfqsrq(jfqsrq);
					wydfft.setJfzzrq(jfzzrq);
					wydfft.setDbbm(dbbm);
					wydfft.setYCQ(ycq);
					wydfft.setBCDJ(bcdj);
					wydfft.setID(id);
					wydfft.setRownum(rownum);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setYYSZT(yyszt);
					wydfft.setDSFGS(dsfgs);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfft.setCB_WLDZBMBM(wlzdbm);
					wydfft.setDH(dh);
					wydfft.setGDFMC(gdfmc);
					wydfft.setJFPJLX(jfpjlx);
					//站址类型
					wydfft.setCB_ZZLX(zzlx);
					wydfft.setDFDJ(danjia);
					wydfft.setBccbsj(thiscbsj);
					wydfft.setSccbsj(lastcbsj);
					wydfft.setQM(qm);
					wydfft.setZM(zm);
					wydfft.setDLIANG(dliang);
					wydfft.setJFJE(jfje);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setBZ(bz);
					wydfft.setCB_NYGLXTID(nyglxtid);
					wydfft.setCB_ZZS(zzs);
					wydfft.setCB_SZS(szs);
					wydfftList.add(wydfft);
				}
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
		}
		return wydfftList;
	}
	
	public List<WydfftBean> searchWydfft_yddfftqr(String loginName,
			String loginId, int pageSize, int curpage, String sheng,
			String shi, String xian, String xiaoqu, String _yysqrzt,String zdbma,String _pc) {
		System.out.println("批次:"+_pc);
		
		List<WydfftBean> wydfftList = new ArrayList<WydfftBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT tt.*,ROWNUM AS rowno FROM (");
		sql.append("SELECT A.ID,A.SFGS,A.DSFGS,A.ZDMC,A.ZDBM,A.DH,A.JSZQ AS ZQ,A.JFJE,A.JFRQ,A.XZBS,A.JFPJLX,A.GDFMC,A.KH,A.FTBL,A.FSYZ,A.FTJE,A.KPLX,A.TZHJE,A.TZYY,A.TZR,A.DYCWBXJE,A.DYCWHXJE,A.JGJE,A.CY,A.YEARMONTH,A.HDZT,A.QRZT,A.BZ,A.YYSZT,");
		sql.append("B.sheng, rndiqu(B.sheng) SHENG_NAME,B.shi,rndiqu(B.shi) SHI_NAME,B.xian,rndiqu(B.xian) XIAN_NAME,A.CB_QM,A.CB_ZM,A.CB_DLIANG,A.CB_DLIU,");
		sql.append("RTNAME(B.gdfs) as gdfs,'抄表结算' JSFS,A.BCDJ AS DANJIA,B.gdfname,B.ADDRESS,B.LONGITUDE,B.LATITUDE,'' AS ZCJJZHBM,C.dbbm,A.SUNHAO AS SH,A.CB_JFQSRQ,A.CB_JFZZRQ,A.LASTCBSJ SCCBSJ,A.THISCBSJ BCCBSJ,A.JSZQ ");
		sql.append(" FROM TBL_TT_WY_DFFT A,ZHANDIAN B,dianbiao C WHERE  A.ZDBM = B.JZCODE AND c.zdid = b.id AND A.QRZT = '已确认' AND KH = '移动' AND YYSXGZT = '"+_yysqrzt+"' ");
		sql.append(" AND B.XIAOQU in (select t.agcode from per_area t where t.accountid = '"+loginId+"') ");
		if (!xiaoqu.equals("0")) {
			sql.append("AND B.XIAOQU = '" + xiaoqu + "'");
		}  if (!xian.equals("0")) {
			sql.append(" AND B.XIAN='" + xian + "'");
		}  if (!shi.equals("0")) {
			sql.append(" AND B.SHI='" + shi + "'");
		}  if (_pc!=null&&!_pc.equals("")) {
			sql.append(" AND A.YEARMONTH='" +_pc+"'");
		}  if (zdbma!=null&&!zdbma.equals("")) {
			sql.append(" AND A.ZDBM='" +zdbma+"'");
		}

		//if (!_yysqrzt.equals("0")) {
			//sql.append(" and A.YYSZT='" + _yysqrzt + "'");
		//}

		sql.append(" ORDER BY A.YEARMONTH DESC");
		sql.append(")tt WHERE ROWNUM <= " + (pageSize * curpage)
				+ ")table_alias WHERE table_alias.rowno >= "
				+ (pageSize * curpage - pageSize));
		log.info("移动电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					int rownum = rs.getInt("ROWNO");
					String id = rs.getString("ID");
					String sfgs = rs.getString("SFGS")!=null?rs.getString("SFGS"):"";
					String zdmc = rs.getString("ZDMC")!=null?rs.getString("ZDMC"):"";
					String zdbm = rs.getString("ZDBM")!=null?rs.getString("ZDBM"):"";
					String dh = rs.getString("DH")!=null?rs.getString("DH"):"";
					String zq = rs.getString("ZQ")!=null?rs.getString("ZQ"):"";
					String jfje = rs.getString("JFJE")!=null?rs.getString("JFJE"):"";
					String jfrq = rs.getString("JFRQ")!=null?rs.getString("JFRQ"):"";
					String xzbs = rs.getString("XZBS")!=null?rs.getString("XZBS"):"";
					String jfpjlx = rs.getString("JFPJLX")!=null?rs.getString("JFPJLX"):"";
					String gdfmc = rs.getString("GDFMC")!=null?rs.getString("GDFMC"):"";
					String kh = rs.getString("KH")!=null?rs.getString("KH"):"";
					String ftbl = rs.getString("FTBL")!=null?rs.getString("FTBL"):"";
					String fsyz = rs.getString("FSYZ")!=null?rs.getString("FSYZ"):"";
					String ftje = rs.getString("FTJE")!=null?rs.getString("FTJE"):"";
					String kplx = rs.getString("KPLX")!=null?rs.getString("KPLX"):"";
					String tzhje = rs.getString("TZHJE")!=null?rs.getString("TZHJE"):"";
					String tzyy = rs.getString("TZYY")!=null?rs.getString("TZYY"):"";
					String tzr = rs.getString("TZR")!=null?rs.getString("TZR"):"";
					String dycwbxje = rs.getString("DYCWBXJE")!=null?rs.getString("DYCWBXJE"):"";
					String dycwhxje = rs.getString("DYCWHXJE")!=null?rs.getString("DYCWHXJE"):"";
					String jgje = rs.getString("JGJE")!=null?rs.getString("JGJE"):"";
					String cy = rs.getString("CY")!=null?rs.getString("CY"):"";
					String yearmonth = rs.getString("YEARMONTH")!=null?rs.getString("YEARMONTH"):"";
					String hdzt = rs.getString("HDZT")!=null?rs.getString("HDZT"):"";
					String qrzt = rs.getString("QRZT")!=null?rs.getString("QRZT"):"";
					String bz = rs.getString("BZ")!=null?rs.getString("BZ"):"";
					String yyszt = rs.getString("YYSZT")!=null?rs.getString("YYSZT"):"";
					String shi_name = rs.getString("SHI_NAME")!=null?rs.getString("SHI_NAME"):"";
					String xian_name = rs.getString("XIAN_NAME")!=null?rs.getString("XIAN_NAME"):"";
					String qm = rs.getString("CB_QM")!=null?rs.getString("CB_QM"):"";
					String zm = rs.getString("CB_ZM")!=null?rs.getString("CB_ZM"):"";
					String dliang = rs.getString("CB_DLIANG")!=null?rs.getString("CB_DLIANG"):"";
					String dliu = rs.getString("CB_DLIU")!=null?rs.getString("CB_DLIU"):"";
					String jsfs = rs.getString("JSFS")!=null?rs.getString("JSFS"):"";
					String gdfs = rs.getString("GDFS")!=null?rs.getString("GDFS"):"";
					String danjia = rs.getString("DANJIA")!=null?rs.getString("DANJIA"):"";
					String address = rs.getString("ADDRESS")!=null?rs.getString("ADDRESS"):"";
					String lnglat = rs.getString("LONGITUDE") + ","
							+ rs.getString("LATITUDE");
					String zcjjzhbm = rs.getString("ZCJJZHBM")!=null?rs.getString("ZCJJZHBM"):"";
					String dbbm = rs.getString("dbbm")!=null?rs.getString("dbbm"):"";
					String sh = rs.getString("SH")!=null?rs.getString("SH"):"";
					String sccbsj=rs.getString("SCCBSJ")!=null?rs.getString("SCCBSJ"):"";
					String bccbsj=rs.getString("BCCBSJ")!=null?rs.getString("BCCBSJ"):"";
					String jszq=rs.getString("JSZQ")!=null?rs.getString("JSZQ"):"";

					WydfftBean wydfft = new WydfftBean();
					wydfft.setSh(sh);
					wydfft.setDbbm(dbbm);
					wydfft.setZcjjzhbm(zcjjzhbm);
					wydfft.setLnglat(lnglat);
					wydfft.setAddress(address);
					wydfft.setGDFMC(gdfmc);
					wydfft.setDFDJ(danjia);
					wydfft.setJSFS(jsfs);
					wydfft.setGDFS(gdfs);
					wydfft.setDLIU(dliu);
					wydfft.setDLIANG(dliang);
					wydfft.setQM(qm);
					wydfft.setZM(zm);
					
					wydfft.setQX(shi_name);
					wydfft.setYYSZT(yyszt);
					wydfft.setBZ(bz);
					wydfft.setKPLX(kplx);
					wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(xian_name);
					wydfft.setDYCWBXJE(dycwbxje);
					wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setRownum(rownum);
					wydfft.setSFGS(sfgs);
					wydfft.setTZHJE(tzhje);
					wydfft.setTZR(tzr);
					wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfft.setHDZT(hdzt);
					wydfft.setQRZT(qrzt);
					wydfft.setSccbsj(sccbsj);
					wydfft.setBccbsj(bccbsj);
					wydfft.setJszq(jszq);
					wydfftList.add(wydfft);
				}
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
		}
		return wydfftList;
	}

	public boolean isWydfftCf(String yearmonth, String zdbm, String dh,
			String kh, String jfje, String jfrq) {
		boolean flag = true;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM TBL_TT_WY_DFFT WHERE YEARMONTH = '"
				+ yearmonth + "' AND ZDBM = '" + zdbm + "' AND DH = '" + dh
				+ "' AND KH = '" + kh + "' AND JFJE = '" + jfje + "'");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					flag = false;
				}
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
		}
		return true;
	}

	public String searchZdmcByZdbm(String zdbm) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT JZNAME FROM ZHANDIAN WHERE JZCODE = '" + zdbm + "'");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					return rs.getString("JZNAME");
				}
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
		}
		return null;
	}
	public boolean updateWydfftyd(ZhandianBean bean) {
		boolean flag = false;
		

//		dbBean.setDj(wydfftBean.getDj());
//		dbBean.setSh(wydfftBean.getSh());
//		dbBean.setJfrq(wydfftBean.getJfrq());
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_WY_DFFT");
		sql.append(" SET ");
		
		if(bean.getJszq()!=null){
			sql.append(" JSZQ = '" + bean.getJszq() + "',");
		}
		
		if(bean.getThiscbsj()!=null){
			sql.append(" THISCBSJ = '" + bean.getThiscbsj() + "',");
		}
		
		if(bean.getLastcbsj()!=null){
			sql.append(" LASTCBSJ = '" + bean.getLastcbsj() + "',");
		}
		
		if(bean.getQm()!=null){
			sql.append(" CB_QM = '" + bean.getQm() + "',");
		}
		
		if(bean.getZm()!=null){
			sql.append(" CB_ZM = '" + bean.getZm() + "',");
		}
		
		if(bean.getDliang()!=null){
			sql.append(" CB_DLIANG = '" + bean.getDliang() + "',");
		}
		
		if(bean.getDj()!=null){
			sql.append(" BCDJ = '" + bean.getDj() + "',");
		}
		
		if(bean.getSh()!=null){
			sql.append(" SUNHAO = '"+bean.getSh()+"',");
		}
		
		if(bean.getJfrq()!=null){
			sql.append(" JFRQ = '" + bean.getJfrq() + "',");
		}
		sql.append(" YYSXGZT = '已修改' ");
		sql.append(" WHERE ID = " + bean.getID() + "");

		log.info("修改物业单位分摊数据SQL:" + sql.toString());

		DataBase db = new DataBase();
		try {
			db.connectDb();
			int i = db.update(sql.toString());
			if(i==0){
				flag = true;
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

		return true;
	}
	public boolean updateWydfft(WydfftBean bean) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_WY_DFFT");
		sql.append(" SET ");
		
		if(bean.getJGJE()!=null){
			sql.append(" JGJE = '" + bean.getJGJE() + "',");
		}
		
		if(bean.getSFGS()!=null){
			sql.append(" SFGS = '" + bean.getSFGS() + "',");
		}
		
		if(bean.getDSFGS()!=null){
			sql.append(" DSFGS = '" + bean.getDSFGS() + "',");
		}
		
		if(bean.getZDMC()!=null){
			sql.append(" ZDMC = '" + bean.getZDMC() + "',");
		}
		
		if(bean.getZDBM()!=null){
			sql.append(" ZDBM = '" + bean.getZDBM() + "',");
		}
		
		if(bean.getDH()!=null){
			sql.append(" DH = '" + bean.getDH() + "',");
		}
		
		if(bean.getZQ()!=null){
			sql.append(" ZQ = '" + bean.getZQ() + "',");
		}
		
		if(bean.getJFJE()!=null){
			sql.append(" JFJE = '" + bean.getJFJE() + "',");
		}
		
		if(bean.getJFRQ()!=null){
			sql.append(" JFRQ = '" + bean.getJFRQ() + "',");
		}
		
		if(bean.getXZBS()!=null){
			sql.append(" XZBS = '" + bean.getXZBS() + "',");
		}
		
		if(bean.getJFPJLX()!=null){
			sql.append(" JFPJLX = '" + bean.getJFPJLX() + "',");
		}
		
		if(bean.getGDFMC()!=null){
			sql.append(" GDFMC = '" + bean.getGDFMC() + "',");
		}
		
		if(bean.getKH()!=null){
			sql.append(" KH = '" + bean.getKH() + "',");
		}
		
		if(bean.getFTBL()!=null){
			sql.append(" FTBL = '" + bean.getFTBL() + "',");
		}
		
		if(bean.getFSYZ()!=null){
			sql.append(" FSYZ = '" + bean.getFSYZ() + "',");
		}
		
		if(bean.getFTJE()!=null){
			sql.append(" FTJE = '" + bean.getFTJE() + "',");
		}
		
		if(bean.getDYCWBXJE()!=null){
			sql.append(" DYCWBXJE = '" + bean.getDYCWBXJE() + "',");
		}
		
		if(bean.getDYCWHXJE()!=null){
			sql.append(" DYCWHXJE = '" + bean.getDYCWHXJE() + "',");
		}
		
		if(bean.getCY()!=null){
			sql.append(" CY = '" + bean.getCY() + "',");
		}
		
		if(bean.getKPLX()!=null){
			sql.append(" KPLX = '" + bean.getKPLX() + "',");
		}
		
		if(bean.getBccbsj()!=null){
			sql.append(" THISCBSJ = '" + bean.getBccbsj() + "',");
		}
		
		if(bean.getSccbsj()!=null){
			sql.append(" LASTCBSJ = '" + bean.getSccbsj() + "',");
		}
		
		if(bean.getQM()!=null){
			sql.append(" CB_QM = '" + bean.getQM() + "',");
		}
		
		if(bean.getZM()!=null){
			sql.append(" CB_ZM = '" + bean.getZM() + "',");
		}
		
		if(bean.getDLIANG()!=null){
			sql.append(" CB_DLIANG = '" + bean.getDLIANG() + "',");
		}
		
		if(bean.getSh()!=null){
			sql.append(" CB_SH = '" + bean.getSh() + "',");
		}
		
		if(bean.getZcjjzhbm()!=null){
			sql.append(" CB_ZCJJZSBM = '" + bean.getZcjjzhbm() + "',");
		}
		
		if(bean.getCB_ZZS()!=null){
			sql.append(" CB_ZZS = '" + bean.getCB_ZZS() + "',");
		}
		
		if(bean.getCB_SZS()!=null){
			sql.append(" CB_SZS = '" + bean.getCB_SZS() + "',");
		}
		
		if(bean.getCB_NYGLXTID()!=null){
			sql.append(" CB_NYGLXTID = '" + bean.getCB_NYGLXTID() + "',");
		}
		
		if(bean.getCB_ZZLX()!=null){
			sql.append(" CB_ZZLX = '" + bean.getCB_ZZLX() + "',");
		}
		
		if(bean.getBCDJ()!=null){
			sql.append(" BCDJ = '"+bean.getBCDJ()+"', ");
		}
		
		if(bean.getJfqsrq()!=null){
			sql.append(" CB_JFQSRQ = '"+bean.getJfqsrq()+"', ");
		}
		
		if(bean.getJfzzrq()!=null){
			sql.append(" CB_JFZZRQ = '"+bean.getJfzzrq()+"', ");
		}
		
		if(bean.getYYSXGZT()!=null){
			sql.append(" YYSXGZT = '"+bean.getYYSXGZT()+"', ");
		}
		
		sql.append(" YEARMONTH = '" + bean.getYEARMONTH() + "'");
		sql.append(" WHERE ID = " + bean.getID() + "");

		log.info("修改物业单位分摊数据SQL:" + sql.toString());

		DataBase db = new DataBase();
		try {
			db.connectDb();
			int i = db.update(sql.toString());
			if(i!=0){
				flag = true;
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

		return true;
	}

	public boolean deleteWydfftById(String id) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE TBL_TT_WY_DFFT WHERE ID =" + id + "");
		log.info("物业电费分摊删除SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.update(sql.toString());
			flag = true;
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
		return true;
	}

	public List<WydfftBean> searchWydfftBy(String wyYearMonth,
			String wydfft_zdbm) {
		List<WydfftBean> wydfftList = new ArrayList<WydfftBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,SFGS,DSFGS,ZDMC,ZDBM,DH,ZQ,JFJE,JFRQ,XZBS,JFPJLX,GDFMC,KH,FTBL,FSYZ,FTJE,KPLX,TZHJE,TZYY,TZR,DYCWBXJE,DYCWHXJE,JGJE,CY,YEARMONTH");
		sql.append(" FROM TBL_TT_WY_DFFT WHERE YEARMONTH = '" + wyYearMonth
				+ "' AND ZDBM='" + wydfft_zdbm + "'");
		sql.append(" ORDER BY YEARMONTH DESC");
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String sfgs = rs.getString("SFGS");
					String dsfgs = rs.getString("DSFGS");
					String zdmc = rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					String dh = rs.getString("DH");
					String zq = rs.getString("ZQ");
					String jfje = rs.getString("JFJE");
					String jfrq = rs.getString("JFRQ");
					String xzbs = rs.getString("XZBS");
					String jfpjlx = rs.getString("JFPJLX");
					String gdfmc = rs.getString("GDFMC");
					String kh = rs.getString("KH");
					String ftbl = rs.getString("FTBL");
					String fsyz = rs.getString("FSYZ");
					String ftje = rs.getString("FTJE");
					String kplx = rs.getString("KPLX");
					String tzhje = rs.getString("TZHJE");
					String tzyy = rs.getString("TZYY");
					String tzr = rs.getString("TZR");
					String dycwbxje = rs.getString("DYCWBXJE");
					String dycwhxje = rs.getString("DYCWHXJE");
					String jgje = rs.getString("JGJE");
					String cy = rs.getString("CY");
					String yearmonth = rs.getString("YEARMONTH");

					WydfftBean wydfft = new WydfftBean();
					wydfft.setKPLX(kplx);
					wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(dsfgs);
					wydfft.setDYCWBXJE(dycwbxje);
					wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setSFGS(sfgs);
					wydfft.setTZHJE(tzhje);
					wydfft.setTZR(tzr);
					wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfftList.add(wydfft);
				}
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
		}
		return wydfftList;
	}

	public boolean searchWydfftBy(String pc) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE TBL_TT_WY_DFFT");
		sql.append(" WHERE ");
		sql.append(" YEARMONTH = '" + pc + "'");
		log.info("按批次删除物业电费分摊数据记录SQL:" + sql.toString());
		
		StringBuffer sql2 = new StringBuffer();
		sql2.append("DELETE TBL_TT_CWWY_HDJL");
		sql2.append(" WHERE ");
		sql2.append(" YEARMONTH = '" + pc + "'");
		log.info("按批次删除物业电费分摊数据记录SQL:" + sql2.toString());
		
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.update(sql.toString());
			db.update(sql2.toString());
			flag = true;
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
		return true;
	}

	public boolean updateForQx(String id, String zthje, String ztyy,
			String loginId) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_WY_DFFT SET QRZT = '需修改', TZHJE = '" + zthje
				+ "' , TZYY = '" + ztyy + "',TZR = '" + loginId + "'");
		sql.append(" WHERE ");
		sql.append(" ID = " + id + "");
		log.info("区县物业电费分摊修改SQL:" + sql.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.update(sql.toString());
			flag = true;
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
		return true;
	}

	public List<WydfftBean> searchWydfft_fy(String _zdbm) {
		List<WydfftBean> wydfftList = new ArrayList<WydfftBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT A.ID,RNDIQU(A.SFGS) AS SFGS,RNDIQU(A.DSFGS) AS DSFGS,A.ZDMC,A.ZDBM,A.DH,A.ZQ,A.JFJE,A.JFRQ,A.XZBS,A.JFPJLX,A.GDFMC,A.KH,A.FTBL,A.FSYZ,A.FTJE,A.KPLX,A.TZHJE,A.TZYY,A.TZR,A.DYCWBXJE,A.DYCWHXJE,A.JGJE,A.CY,A.YEARMONTH,A.HDZT,A.QRZT,A.BZ");
		sql.append(" FROM TBL_TT_WY_DFFT A,ZHANDIAN B WHERE  A.ZDBM = B.JZCODE ");

		if (_zdbm != null && !_zdbm.equals("")) {
			sql.append(" AND A.ZDBM = " + _zdbm + "");
		}

		sql.append(" ORDER BY A.YEARMONTH DESC");
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String sfgs = rs.getString("SFGS");
					String dsfgs = rs.getString("DSFGS");
					String zdmc = rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					String dh = rs.getString("DH");
					String zq = rs.getString("ZQ");
					String jfje = rs.getString("JFJE");
					String jfrq = rs.getString("JFRQ");
					String xzbs = rs.getString("XZBS");
					String jfpjlx = rs.getString("JFPJLX");
					String gdfmc = rs.getString("GDFMC");
					String kh = rs.getString("KH");
					String ftbl = rs.getString("FTBL");
					String fsyz = rs.getString("FSYZ");
					String ftje = rs.getString("FTJE");
					String kplx = rs.getString("KPLX");
					String tzhje = rs.getString("TZHJE") == null ? "" : rs
							.getString("TZHJE");
					String tzyy = rs.getString("TZYY") == null ? "" : rs
							.getString("TZYY");
					String tzr = rs.getString("TZR") == null ? "" : rs
							.getString("TZR");
					String dycwbxje = rs.getString("DYCWBXJE") == null
							|| rs.getString("DYCWBXJE").equals("null") ? ""
							: rs.getString("DYCWBXJE");
					String dycwhxje = rs.getString("DYCWHXJE") == null
							|| rs.getString("DYCWHXJE").equals("null") ? ""
							: rs.getString("DYCWHXJE");
					String jgje = rs.getString("JGJE") == null
							|| rs.getString("JGJE").equals("null") ? "" : rs
							.getString("JGJE");
					String cy = rs.getString("CY") == null
							|| rs.getString("CY").equals("null") ? "" : rs
							.getString("CY");
					String yearmonth = rs.getString("YEARMONTH");
					String hdzt = rs.getString("HDZT");
					String qrzt = rs.getString("QRZT");
					String bz = rs.getString("BZ") == null ? "" : rs
							.getString("BZ");
					WydfftBean wydfft = new WydfftBean();
					wydfft.setBZ(bz);
					wydfft.setKPLX(kplx);
					wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(dsfgs);
					wydfft.setDYCWBXJE(dycwbxje);
					wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setSFGS(sfgs);
					wydfft.setTZHJE(tzhje);
					wydfft.setTZR(tzr);
					wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfft.setHDZT(hdzt);
					wydfft.setQRZT(qrzt);
					wydfftList.add(wydfft);
				}
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
		}
		return wydfftList;
	}

	public boolean deleteWydfftByZdbm(String zdbm) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE TBL_TT_WY_DFFT WHERE ZDBM =" + zdbm + "");
		log.info("物业电费分摊删除按站点编码SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.update(sql.toString());
			flag = true;
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
		return true;
	}

	public ZhandianBean searchZdByWyid(String wyid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,");
		sql.append("RNDIQU(SHENG)SHENG,");
		sql.append("RNDIQU(SHI)SHI,");
		sql.append("RNDIQU(XIAN)XIAN,");
		sql.append("JZTYPE,");
		sql.append("JZNAME,");
		sql.append("JZCODE,");
		sql.append("RTNAME(PROPERTY)PROPERTY,");
		sql.append("BIEMING,");
		sql.append("BGSIGN,");
		sql.append("RTNAME(YFLX)YFLX,");
		sql.append("ADDRESS,");
		sql.append("RTNAME(GDFS)GDFS,");
		sql.append("AREAOLD,");
		sql.append("JNGLMK,");
		sql.append("FZR,");
		sql.append("MEMO,");
		sql.append("CJR,");
		sql.append("CJTIME,");
		sql.append("DIANFEIOLD,");
		sql.append("LONGITUDE,");
		sql.append("LATITUDE,");
		sql.append("LO_DFM,");
		sql.append("LA_DFM,");
		sql.append("GOOGLEID,");
		sql.append("ZDCODE,");
		sql.append("RTNAME(MANUALAUDITSTATUS_STATION)MANUALAUDITSTATUS_STATION,");
		sql.append("MANUALAUDITNAME_STATION,");
		sql.append("MANUALAUDITDATETIME_STATIONOLD,");
		sql.append("ERPCODE,");
		sql.append("SYDATEOLD,");
		sql.append("XIAOQU,");
		sql.append("PUE,");
		sql.append("ZZJGBM,");
		sql.append("XUNI,");
		sql.append("GCZT,");
		sql.append("CZZD,");
		sql.append("GCXM,");
		sql.append("ZDCQ,");
		sql.append("ZDCB,");
		sql.append("ZLFH,");
		sql.append("JNJSMS,");
		sql.append("CZZDID,");
		sql.append("NHJCDY,");
		sql.append("ERPBH,");
		sql.append("DHID,");
		sql.append("ZHWGID,");
		sql.append("DZYWID,");
		sql.append("CAIJI,");
		sql.append("SHSIGN,");
		sql.append("EDHDLOLD,");
		sql.append("XLX,");
		sql.append("JFLX,");
		sql.append("SFJF,");
		sql.append("DSLXA,");
		sql.append("DSLXB,");
		sql.append("JITUAN,");
		sql.append("BJFYY,");
		sql.append("RTNAME(JRWTYPE)JRWTYPE,");
		sql.append("GSF,");
		sql.append("KONGTIAO,");
		sql.append("ENTRYPENSONNEL,");
		sql.append("ENTRYTIMEOLD,");
		sql.append("RTNAME(STATIONTYPE)STATIONTYPE,");
		sql.append("SIGNTYPENUM,");
		sql.append("JLFH,");
		sql.append("RTNAME(DYTYPE)DYTYPE,");
		sql.append("G2,");
		sql.append("G3,");
		sql.append("KDSB,");
		sql.append("YYSB,");
		sql.append("ZPSL,");
		sql.append("ZSSL,");
		sql.append("KDSBSL,");
		sql.append("YYSBSL,");
		sql.append("KT1,");
		sql.append("KT2,");
		sql.append("ZGD,");
		sql.append("YID,");
		sql.append("QYZT,");
		sql.append("ZDBZW,");
		sql.append("LYJHJGS,");
		sql.append("GXXX,");
		sql.append("JSKSSJOLD,");
		sql.append("JSJSSJOLD,");
		sql.append("XMH,");
		sql.append("YGD,");
		sql.append("YSD,");
		sql.append("CHANGJIA,");
		sql.append("JZLX,");
		sql.append("SHEBEI,");
		sql.append("BBU,");
		sql.append("RRU,");
		sql.append("YDSHEBEI,");
		sql.append("GXGWSL,");
		sql.append("TWGX,");
		sql.append("BM,");
		sql.append("DZYF,");
		sql.append("DZBM,");
		sql.append("G2XQS,");
		sql.append("G3SQSL,");
		sql.append("YDGXSBSL,");
		sql.append("DXGXSBSL,");
		sql.append("QSDBDL,");
		sql.append("KTS,");
		sql.append("KTZGL,");
		sql.append("YSJTS,");
		sql.append("WJTS,");
		sql.append("YYBGKT,");
		sql.append("JFSCKT,");
		sql.append("KTYPS,");
		sql.append("KTEPS,");
		sql.append("KTPS,");
		sql.append("YFXS,");
		sql.append("KTXS,");
		sql.append("FWXS,");
		sql.append("JCXS,");
		sql.append("SCB,");
		sql.append("SHBZW,");
		sql.append("ZYBYQLX,");
		sql.append("BDDBSCB,");
		sql.append("BZW,");
		sql.append("COUNTBZW,");
		sql.append("CSLRR,");
		sql.append("CSLRSJ,");
		sql.append("AREA,");
		sql.append("DIANFEI,");
		sql.append("MANUALAUDITDATETIME_STATION,");
		sql.append("SYDATE,");
		sql.append("EDHDL,");
		sql.append("ENTRYTIME,");
		sql.append("JSKSSJ,");
		sql.append("JSJSSJ,");
		sql.append("PROVAUDITSTATUS,");
		sql.append("PROVAUDITNAME,");
		sql.append("PROVAUDITTIME,");
		sql.append("YDTX,");
		sql.append("ZRRLXFS,");
		sql.append("YDSX,");
		sql.append("YDLX,");
		sql.append("WDBG,");
		sql.append("KTPZ,");
		sql.append("JFDJ,");
		sql.append("SFFS,");
		sql.append("DJZGWD,");
		sql.append("ZGRY,");
		sql.append("LYFS,");
		sql.append("SZRSHBZ,");
		sql.append("SZRSHR,");
		sql.append("SZRSHSJ,");
		sql.append("SBBL,");
		sql.append("EDGL,");
		sql.append("DXGL,");
		sql.append("QXSHBZ,");
		sql.append("QXSHNAME,");
		sql.append("QXSHSJ,");
		sql.append("SBTGLY,");
		sql.append("SHENGBTGLY,");
		sql.append("YWGXBBUS,");
		sql.append("YWGXRRUS,");
		sql.append("CKKD,");
		sql.append("JGS,");
		sql.append("UGS,");
		sql.append("IDCJFXJ,");
		sql.append("YSYJGS,");
		sql.append("YSYUS,");
		sql.append("PQS,");
		sql.append("GXLZDSB,");
		sql.append("DLCQJ,");
		sql.append("SGLLY,");
		sql.append("QXLY,");
		sql.append("ZANCUNBZW,");
		sql.append("SFZSHBZ,");
		sql.append("SFZSHR,");
		sql.append("SFZSHSJ,");
		sql.append("SFZLY,");
		sql.append("XLBZW,");
		sql.append("LSQFBZW,");
		sql.append("ZDCHSJ,");
		sql.append("ZDCHR,");
		sql.append("TTYJBZ,");
		sql.append("TTYJSJ,");
		sql.append("TTYJR,");
		sql.append("TTDJBZ,");
		sql.append("TTDJSJ,");
		sql.append("TTDJR,");
		sql.append("SBSZBL,");
		sql.append("WYZDID,");
		sql.append("WYZDNAME,");
		sql.append("KTSPS,");
		sql.append("KTWPS,");
		sql.append("KTSHIPS,");
		sql.append("KTJMPS,");
		sql.append("KTYPZGL,");
		sql.append("KTYWZGL,");
		sql.append("KTEPZGL,");
		sql.append("KTSZGL,");
		sql.append("KTWZGL,");
		sql.append("KTSHIPZGL,");
		sql.append("KTJMZGL,");
		sql.append("SXD_A,");
		sql.append("SXD_B,");
		sql.append("SXD_C,");
		sql.append("ZLDY,");
		sql.append("POWERPOLE,");
		sql.append("ZZLX,");
		sql.append("WLZDBM,");
		sql.append("LTQX,");
		sql.append("YDQX,");
		sql.append("NHXTID,");
		sql.append("FZRPHONE,");
		sql.append("YCQ,");
		sql.append("DBBM,");
		sql.append("GDFNAME");
		sql.append(" FROM ZHANDIAN");
		sql.append(" WHERE JZCODE =");
		sql.append("(SELECT ZDBM FROM TBL_TT_WY_DFFT WHERE ID = '" + wyid
				+ "')");
		log.info("[区县抄表站点查询:]" + sql);

		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					ZhandianBean zhandian = new ZhandianBean();
					zhandian.setID(rs.getString("ID"));
					zhandian.setSHENG(rs.getString("SHENG"));
					zhandian.setSHI(rs.getString("SHI"));
					zhandian.setXIAN(rs.getString("XIAN"));
					zhandian.setJZTYPE(rs.getString("JZTYPE") == null
							|| rs.getString("JZTYPE").equals("")
							|| rs.getString("JZTYPE").equals("null") ? "" : rs
							.getString("JZTYPE"));
					zhandian.setJZNAME(rs.getString("JZNAME"));
					zhandian.setJZCODE(rs.getString("JZCODE"));
					zhandian.setWLZDBM(rs.getString("WLZDBM") == null
							|| rs.getString("WLZDBM").equals("")
							|| rs.getString("WLZDBM").equals("null") ? "" : rs
							.getString("WLZDBM"));
					zhandian.setADDRESS(rs.getString("ADDRESS") == null
							|| rs.getString("ADDRESS").equals("")
							|| rs.getString("ADDRESS").equals("null") ? "" : rs
							.getString("ADDRESS"));
					zhandian.setGSF(rs.getString("GSF") == null
							|| rs.getString("GSF").equals("")
							|| rs.getString("GSF").equals("null") ? "" : rs
							.getString("GSF"));
					zhandian.setGDFS(rs.getString("GDFS") == null
							|| rs.getString("GDFS").equals("")
							|| rs.getString("GDFS").equals("null") ? "" : rs
							.getString("GDFS"));
					zhandian.setGDFNAME(rs.getString("GDFNAME") == null
							|| rs.getString("GDFNAME").equals("")
							|| rs.getString("GDFNAME").equals("null") ? "" : rs
							.getString("GDFNAME"));
					return zhandian;
				}
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
		}
		return null;
	}

	public DianbiaoBean searchDbByWyid(String wyid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ID,");
		sql.append("ZDNAME,");
		sql.append("ZDID,");
		sql.append("AREA,");
		sql.append("JZTYPE,");
		sql.append("BGSIGN,");
		sql.append("BIEMING,");
		sql.append("DBID,");
		sql.append("SSZY,");
		sql.append("DBYT,");
		sql.append("DLLX,");
		sql.append("YDSB,");
		sql.append("CSDSOLD,");
		sql.append("CSSYTIMEOLD,");
		sql.append("BEILVOLD,");
		sql.append("DBXH,");
		sql.append("MEMO,");
		sql.append("SCODE,");
		sql.append("SHICODE,");
		sql.append("XCODE,");
		sql.append("NETPOINT_ID,");
		sql.append("NETPOINT_NAME,");
		sql.append("DBNAME,");
		sql.append("YHDLOLD,");
		sql.append("DFZFLX,");
		sql.append("LINELOSSTYPE,");
		sql.append("LINELOSSVALUEOLD,");
		sql.append("ENTRYPENSONNEL,");
		sql.append("BZW,");
		sql.append("DBQYZT,");
		sql.append("DBZBDYHH,");
		sql.append("YDBID,");
		sql.append("DANJIAOLD,");
		sql.append("changevalueold");
		sql.append("DBDS,");
		sql.append("XGBZW,");
		sql.append("ZGDJ,");
		sql.append("ZZSL,");
		sql.append("CSLRR,");
		sql.append("CSLRSJ,");
		sql.append("CSDS,");
		sql.append("CSSYTIME,");
		sql.append("BEILV,");
		sql.append("YHDL,");
		sql.append("LINELOSSVALUE,");
		sql.append("ENTRYTIME,");
		sql.append("DANJIA,");
		sql.append("CHANGEVALUE,");
		sql.append("SHISHBZ,");
		sql.append("SHISHSJ,");
		sql.append("SHISHR,");
		sql.append("SHISHLY,");
		sql.append("SHENGSHBZ,");
		sql.append("SCCBSJ,");
		sql.append("CBSJBZW,");
		sql.append("DBBM");
		sql.append(" from dianbiao");
		sql.append(" where zdid =");
		sql.append("(select zdid");
		sql.append("from zhandian");
		sql.append("where jzcode =");
		sql.append("(select zdbm from tbl_tt_wy_dfft where id = '"+wyid+"'");
		sql.append(wyid);
		sql.append("))");
		log.info("[区县抄表站点查询:]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					DianbiaoBean dianbao = new DianbiaoBean();
					dianbao.setID(rs.getString("ID"));
					dianbao.setZDNAME(rs.getString("ZDNAME"));
					dianbao.setZDID(rs.getString("ZDID"));
					dianbao.setAREA(rs.getString("AREA"));
					dianbao.setJZTYPE(rs.getString("JZTYPE"));
					dianbao.setBGSIGN(rs.getString("BGSIGN"));
					dianbao.setBIEMING(rs.getString("BIEMING"));
					dianbao.setDBID(rs.getString("DBID"));
					dianbao.setSSZY(rs.getString("SSZY"));
					dianbao.setDBYT(rs.getString("DBYT"));
					dianbao.setDLLX(rs.getString("DLLX"));
					dianbao.setYDSB(rs.getString("YDSB"));
					dianbao.setCSDSOLD(rs.getString("CSDSOLD"));
					dianbao.setCSSYTIMEOLD(rs.getString("CSSYTIMEOLD"));
					dianbao.setBEILVOLD(rs.getString("BEILVOLD"));
					dianbao.setDBXH(rs.getString("DBXH"));
					dianbao.setMEMO(rs.getString("MEMO"));
					dianbao.setSCODE(rs.getString("SCODE"));
					dianbao.setSHICODE(rs.getString("SHICODE"));
					dianbao.setXCODE(rs.getString("XCODE"));
					dianbao.setNETPOINT_ID(rs.getString("NETPOINT_ID"));
					dianbao.setNETPOINT_NAME(rs.getString("NETPOINT_NAME"));
					dianbao.setDBNAME(rs.getString("DBNAME"));
					dianbao.setYHDLOLD(rs.getString("YHDLOLD"));
					dianbao.setDFZFLX(rs.getString("DFZFLX"));
					dianbao.setLINELOSSTYPE(rs.getString("LINELOSSTYPE"));
					dianbao.setLINELOSSVALUEOLD(rs.getString("LINELOSSVALUEOLD"));
					dianbao.setENTRYPENSONNEL(rs.getString("ENTRYPENSONNEL"));
					dianbao.setBZW(rs.getString("BZW"));
					dianbao.setDBQYZT(rs.getString("DBQYZT"));
					dianbao.setDBZBDYHH(rs.getString("DBZBDYHH"));
					dianbao.setYDBID(rs.getString("YDBID"));
					dianbao.setDANJIAOLD(rs.getString("DANJIAOLD"));
					dianbao.setDBDS(rs.getString("DBDS"));
					dianbao.setXGBZW(rs.getString("XGBZW"));
					dianbao.setZGDJ(rs.getString("ZGDJ"));
					dianbao.setZZSL(rs.getString("ZZSL"));
					dianbao.setCSLRR(rs.getString("CSLRR"));
					dianbao.setCSLRSJ(rs.getString("CSLRSJ"));
					dianbao.setCSDS(rs.getString("CSDS"));
					dianbao.setCSSYTIME(rs.getString("CSSYTIME"));
					dianbao.setBEILV(rs.getString("BEILV"));
					dianbao.setYHDL(rs.getString("YHDL"));
					dianbao.setLINELOSSVALUE(rs.getString("LINELOSSVALUE"));
					dianbao.setENTRYTIME(rs.getString("ENTRYTIME"));
					dianbao.setDANJIA(rs.getString("DANJIA"));
					dianbao.setCHANGEVALUE(rs.getString("CHANGEVALUE"));
					dianbao.setSHISHBZ(rs.getString("SHISHBZ"));
					dianbao.setSHISHSJ(rs.getString("SHISHSJ"));
					dianbao.setSHISHR(rs.getString("SHISHR"));
					dianbao.setSHISHLY(rs.getString("SHISHLY"));
					dianbao.setSHENGSHBZ(rs.getString("SHENGSHBZ"));
					dianbao.setSCCBSJ(rs.getString("SCCBSJ"));
					dianbao.setCBSJBZW(rs.getString("CBSJBZW"));
					dianbao.setDBBM(rs.getString("DBBM"));
					return dianbao;
				}
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
		}
		return null;
	}

	public WydfftBean searchWydfft(String _yearmonth, String _zdbm, String _kh) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,SFGS,DSFGS,ZDMC,ZDBM,DH,ZQ,JFJE,JFRQ,XZBS,JFPJLX,GDFMC,KH,FTBL,FSYZ,FTJE,KPLX,TZHJE,TZYY,TZR,DYCWBXJE,DYCWHXJE,JGJE,CY,YEARMONTH,THISCBSJ,LASTCBSJ,CB_ZM");
		sql.append(" FROM TBL_TT_WY_DFFT WHERE YEARMONTH = '" + _yearmonth+ "' AND ZDBM = '"+_zdbm+"' AND KH = '"+_kh+"' AND QRZT = '已确认'");
		sql.append(" ORDER BY YEARMONTH DESC");
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String sfgs = rs.getString("SFGS");
					String dsfgs = rs.getString("DSFGS");
					String zdmc = rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					String dh = rs.getString("DH");
					String zq = rs.getString("ZQ");
					String jfje = rs.getString("JFJE");
					String jfrq = rs.getString("JFRQ");
					String xzbs = rs.getString("XZBS");
					String jfpjlx = rs.getString("JFPJLX");
					String gdfmc = rs.getString("GDFMC");
					String kh = rs.getString("KH");
					String ftbl = rs.getString("FTBL");
					String fsyz = rs.getString("FSYZ");
					String ftje = rs.getString("FTJE");
					String kplx = rs.getString("KPLX");
					String tzhje = rs.getString("TZHJE");
					String tzyy = rs.getString("TZYY");
					String tzr = rs.getString("TZR");
					String dycwbxje = rs.getString("DYCWBXJE");
					String dycwhxje = rs.getString("DYCWHXJE");
					String jgje = rs.getString("JGJE");
					String cy = rs.getString("CY");
					String yearmonth = rs.getString("YEARMONTH");
					String thiscbsj = rs.getString("THISCBSJ");
					String lastcbsj = rs.getString("LASTCBSJ");
					String zm = rs.getString("CB_ZM");

					WydfftBean wydfft = new WydfftBean();
					wydfft.setZM(zm);
					wydfft.setBccbsj(thiscbsj);
					wydfft.setSccbsj(lastcbsj);
					wydfft.setKPLX(kplx);
					wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(dsfgs);
					wydfft.setDYCWBXJE(dycwbxje);
					wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setSFGS(sfgs);
					wydfft.setTZHJE(tzhje);
					wydfft.setTZR(tzr);
					wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					return wydfft;
				}
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
		}
		return null;
	}
	public WydfftBean searchWydfftyd(String _yearmonth, String _zdbm, String _kh,String xgzt) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,SFGS,DSFGS,ZDMC,ZDBM,DH,ZQ,JFJE,JFRQ,XZBS,JFPJLX,GDFMC,KH,FTBL,FSYZ,FTJE,KPLX,TZHJE,TZYY,TZR,DYCWBXJE,DYCWHXJE,JGJE,CY,YEARMONTH,THISCBSJ,LASTCBSJ");
		sql.append(" FROM TBL_TT_WY_DFFT WHERE YEARMONTH = '" + _yearmonth+ "' AND ZDBM = '"+_zdbm+"' AND KH = '"+_kh+"' AND QRZT = '已确认' AND (YYSXGZT='"+xgzt+"' OR YYSXGZT IS NULL )");
		sql.append(" ORDER BY YEARMONTH DESC");
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String sfgs = rs.getString("SFGS");
					String dsfgs = rs.getString("DSFGS");
					String zdmc = rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					String dh = rs.getString("DH");
					String zq = rs.getString("ZQ");
					String jfje = rs.getString("JFJE");
					String jfrq = rs.getString("JFRQ");
					String xzbs = rs.getString("XZBS");
					String jfpjlx = rs.getString("JFPJLX");
					String gdfmc = rs.getString("GDFMC");
					String kh = rs.getString("KH");
					String ftbl = rs.getString("FTBL");
					String fsyz = rs.getString("FSYZ");
					String ftje = rs.getString("FTJE");
					String kplx = rs.getString("KPLX");
					String tzhje = rs.getString("TZHJE");
					String tzyy = rs.getString("TZYY");
					String tzr = rs.getString("TZR");
					String dycwbxje = rs.getString("DYCWBXJE");
					String dycwhxje = rs.getString("DYCWHXJE");
					String jgje = rs.getString("JGJE");
					String cy = rs.getString("CY");
					String yearmonth = rs.getString("YEARMONTH");
					String thiscbsj = rs.getString("THISCBSJ");
					String lastcbsj = rs.getString("LASTCBSJ");

					WydfftBean wydfft = new WydfftBean();
					wydfft.setBccbsj(thiscbsj);
					wydfft.setSccbsj(lastcbsj);
					wydfft.setKPLX(kplx);
					wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(dsfgs);
					wydfft.setDYCWBXJE(dycwbxje);
					wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setSFGS(sfgs);
					wydfft.setTZHJE(tzhje);
					wydfft.setTZR(tzr);
					wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					return wydfft;
				}
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
		}
		return null;
	}
	public WydfftBean searchWydfft(String _yearmonth, String _zdbm,String _dh, String _kh) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,SFGS,DSFGS,ZDMC,ZDBM,DH,ZQ,JFJE,JFRQ,XZBS,JFPJLX,GDFMC,KH,FTBL,FSYZ,FTJE,KPLX,TZHJE,TZYY,TZR,DYCWBXJE,DYCWHXJE,JGJE,CY,YEARMONTH,THISCBSJ,LASTCBSJ");
		sql.append(" FROM TBL_TT_WY_DFFT WHERE YEARMONTH = '" + _yearmonth+ "' AND ZDBM = '"+_zdbm+"' AND KH = '"+_kh+"' AND DH = '"+_dh+"' AND QRZT = '已确认'");
		sql.append(" ORDER BY YEARMONTH DESC");
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String sfgs = rs.getString("SFGS");
					String dsfgs = rs.getString("DSFGS");
					String zdmc = rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					String dh = rs.getString("DH");
					String zq = rs.getString("ZQ");
					String jfje = rs.getString("JFJE");
					String jfrq = rs.getString("JFRQ");
					String xzbs = rs.getString("XZBS");
					String jfpjlx = rs.getString("JFPJLX");
					String gdfmc = rs.getString("GDFMC");
					String kh = rs.getString("KH");
					String ftbl = rs.getString("FTBL");
					String fsyz = rs.getString("FSYZ");
					String ftje = rs.getString("FTJE");
					String kplx = rs.getString("KPLX");
					String tzhje = rs.getString("TZHJE");
					String tzyy = rs.getString("TZYY");
					String tzr = rs.getString("TZR");
					String dycwbxje = rs.getString("DYCWBXJE");
					String dycwhxje = rs.getString("DYCWHXJE");
					String jgje = rs.getString("JGJE");
					String cy = rs.getString("CY");
					String yearmonth = rs.getString("YEARMONTH");
					String thiscbsj = rs.getString("THISCBSJ");
					String lastcbsj = rs.getString("LASTCBSJ");

					WydfftBean wydfft = new WydfftBean();
					wydfft.setBccbsj(thiscbsj);
					wydfft.setSccbsj(lastcbsj);
					wydfft.setKPLX(kplx);
					wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(dsfgs);
					wydfft.setDYCWBXJE(dycwbxje);
					wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setSFGS(sfgs);
					wydfft.setTZHJE(tzhje);
					wydfft.setTZR(tzr);
					wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					return wydfft;
				}
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
		}
		return null;
	}
	public String searchYddj(String dbbm) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DANJIA ");
		sql.append(" FROM DIANBIAO WHERE DBBM = '" + dbbm+ "'");
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		String dj="";
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
				 dj= rs.getString("DANJIA");
					

					return dj;
				}
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
		}
		return null;
	}

	public List<WydfftBean> searchWydfftBy(String wyYearMonth, String cw_zdbm,
			String cw_dh) {
		List<WydfftBean> wydfftList = new ArrayList<WydfftBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,SFGS,DSFGS,ZDMC,ZDBM,DH,ZQ,JFJE,JFRQ,XZBS,JFPJLX,GDFMC,KH,FTBL,FSYZ,FTJE,KPLX,TZHJE,TZYY,TZR,DYCWBXJE,DYCWHXJE,JGJE,CY,YEARMONTH");
		sql.append(" FROM TBL_TT_WY_DFFT WHERE YEARMONTH = '" + wyYearMonth
				+ "' AND ZDBM='" + cw_zdbm + "'"+ " AND DH='" + cw_dh + "'");
		sql.append(" ORDER BY YEARMONTH DESC");
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String sfgs = rs.getString("SFGS");
					String dsfgs = rs.getString("DSFGS");
					String zdmc = rs.getString("ZDMC");
					String zdbm = rs.getString("ZDBM");
					String dh = rs.getString("DH");
					String zq = rs.getString("ZQ");
					String jfje = rs.getString("JFJE");
					String jfrq = rs.getString("JFRQ");
					String xzbs = rs.getString("XZBS");
					String jfpjlx = rs.getString("JFPJLX");
					String gdfmc = rs.getString("GDFMC");
					String kh = rs.getString("KH");
					String ftbl = rs.getString("FTBL");
					String fsyz = rs.getString("FSYZ");
					String ftje = rs.getString("FTJE");
					String kplx = rs.getString("KPLX");
					String tzhje = rs.getString("TZHJE");
					String tzyy = rs.getString("TZYY");
					String tzr = rs.getString("TZR");
					String dycwbxje = rs.getString("DYCWBXJE");
					String dycwhxje = rs.getString("DYCWHXJE");
					String jgje = rs.getString("JGJE");
					String cy = rs.getString("CY");
					String yearmonth = rs.getString("YEARMONTH");

					WydfftBean wydfft = new WydfftBean();
					wydfft.setKPLX(kplx);
					wydfft.setCY(cy);
					wydfft.setDH(dh);
					wydfft.setDSFGS(dsfgs);
					wydfft.setDYCWBXJE(dycwbxje);
					wydfft.setDYCWHXJE(dycwhxje);
					wydfft.setFSYZ(fsyz);
					wydfft.setFTBL(ftbl);
					wydfft.setFTJE(ftje);
					wydfft.setGDFMC(gdfmc);
					wydfft.setID(id);
					wydfft.setJFJE(jfje);
					wydfft.setJFPJLX(jfpjlx);
					wydfft.setJFRQ(jfrq);
					wydfft.setJGJE(jgje);
					wydfft.setKH(kh);
					wydfft.setSFGS(sfgs);
					wydfft.setTZHJE(tzhje);
					wydfft.setTZR(tzr);
					wydfft.setTZYY(tzyy);
					wydfft.setXZBS(xzbs);
					wydfft.setYEARMONTH(yearmonth);
					wydfft.setZQ(zq);
					wydfft.setZDMC(zdmc);
					wydfft.setZDBM(zdbm);
					wydfftList.add(wydfft);
				}
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
		}
		return wydfftList;
	}

	public String searchWydfftId() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MAX(ID) AS　ID FROM TBL_TT_WY_DFFT");
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		String dj="";
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
				 dj= rs.getString("ID");
					return dj;
				}
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
		}
		return null;
	}
	public WydfftBean searchWydfftydzjds(String _yearmonth, String _zdbm, String _dbbm,String xgzt,String _kh) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MAX(A.THISCBSJ)TH,MAX(A.LASTCBSJ)LS,MAX(A.CB_QM)QM,MAX(A.CB_ZM)ZM  FROM TBL_TT_WY_DFFT A");
		sql.append(" WHERE  ZDBM = '"+_zdbm+"' AND DBBM='"+_dbbm+"' AND KH = '"+_kh+"' AND QRZT = '已确认' AND YYSXGZT='"+xgzt+"'");
		
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					
					String zm = rs.getString("ZM");
					String qm = rs.getString("QM");
					String thiscbsj = rs.getString("TH");
					String lastcbsj = rs.getString("LS");
					
					
					WydfftBean wydfft = new WydfftBean();
					
					wydfft.setZDBM(_zdbm);
					wydfft.setDbbm(_dbbm);
					wydfft.setBccbsj(thiscbsj);
					wydfft.setSccbsj(lastcbsj);
					
					System.out.println("thiscbsj:"+thiscbsj);
					if(thiscbsj!=null&&!"".equals(thiscbsj)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					
					Date dt=sdf.parse(thiscbsj);
					Calendar ca=new GregorianCalendar();
					ca.setTime(dt);
					ca.add(ca.DATE, 1);
					dt=ca.getTime();
					String sc=sdf.format(dt);
					System.out.print("本次抄表时间+1："+sc);
					
					wydfft.setBccbsj(sc);
				}
					wydfft.setZM(zm);
					wydfft.setQM(qm);
					return wydfft;
				}
			if(rs.next()==false){
				WydfftBean wydfft = new WydfftBean();
				
				wydfft.setZDBM(_zdbm);
				wydfft.setDbbm(_dbbm);
				wydfft.setBccbsj("");
				wydfft.setSccbsj("");
				wydfft.setZM("");
				wydfft.setQM("");
				return wydfft;
				}
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
		}
		return null;
	}
	
	public WydfftBean searchWydfftltzjds(String _yearmonth, String _zdbm, String _dbbm,String xgzt,String _kh) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MAX(A.THISCBSJ)TH,MAX(A.LASTCBSJ)LS,MAX(A.CB_QM)QM,MAX(A.CB_ZM)ZM  FROM TBL_TT_WY_DFFT A");
		sql.append(" WHERE  ZDBM = '"+_zdbm+"' AND DBBM='"+_dbbm+"' AND KH = '"+_kh+"' AND QRZT = '已确认' AND YYSXGZT='"+xgzt+"'");
		
		log.info("物业数据电费分摊记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					
					String zm = rs.getString("ZM");
					String qm = rs.getString("QM");
					String thiscbsj = rs.getString("TH");
					String lastcbsj = rs.getString("LS");
					
					
					WydfftBean wydfft = new WydfftBean();
					
					wydfft.setZDBM(_zdbm);
					wydfft.setDbbm(_dbbm);
					wydfft.setBccbsj(thiscbsj);
					wydfft.setSccbsj(lastcbsj);
					
					System.out.println("thiscbsj:"+thiscbsj);
					if(thiscbsj!=null&&!"".equals(thiscbsj)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					
					Date dt=sdf.parse(thiscbsj);
					Calendar ca=new GregorianCalendar();
					ca.setTime(dt);
					ca.add(ca.DATE, 1);
					dt=ca.getTime();
					String sc=sdf.format(dt);
					System.out.print("本次抄表时间+1："+sc);
					
					wydfft.setBccbsj(sc);
				}
					wydfft.setZM(zm);
					wydfft.setQM(qm);
					return wydfft;
				}
			if(rs.next()==false){
				WydfftBean wydfft = new WydfftBean();
				
				wydfft.setZDBM(_zdbm);
				wydfft.setDbbm(_dbbm);
				wydfft.setBccbsj("");
				wydfft.setSccbsj("");
				wydfft.setZM("");
				wydfft.setQM("");
				return wydfft;
				}
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
		}
		return null;
	}
}
