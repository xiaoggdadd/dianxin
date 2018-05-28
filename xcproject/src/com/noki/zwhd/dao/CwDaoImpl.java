package com.noki.zwhd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.zwhd.model.CwybxBean;
import com.noki.zwhd.model.HdjlBean;

public class CwDaoImpl {
	private static Log log = LogFactory.getLog(CwDaoImpl.class.getName());

	public List<HdjlBean> searchCwbxhdjl() {
		List<HdjlBean> hdjlList = new ArrayList<HdjlBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT YEARMONTH FROM TBL_TT_CWWY_HDJL WHERE CWORWY = 'CW' GROUP BY YEARMONTH order by YEARMONTH");
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

	public boolean saveCwybxHdjl(String loginName, String yearmonth) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO TBL_TT_CWWY_HDJL");
		sql.append("(HDRQ,ID,HDR,HDZT,CWORWY,YEARMONTH)");
		sql.append(" VALUES(");
		sql.append("'" + format.format(new Date())
				+ "',XL_TT_CWWY_HDJL.nextval,'" + loginName + "','0','CW','"
				+ yearmonth + "'");
		sql.append(" )");

		log.info("添加财务已报销数据记录SQL:" + sql.toString());
		DataBase db = new DataBase();
		try {
			db.connectDb();
			int i = db.update(sql.toString());
			System.out.println(i);
			if(i==1){
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

	public boolean saveCwybx(CwybxBean cwybx) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO TBL_TT_CW_YBX");
		sql.append("(ID,RQ,BXLX,DJPH,ZY,DJZT,BM,SFJE,FPHSJE,FPBHSJE,JSDH,ZDMC,ZDBH,CWYFTHYDCWBXJE,CY,FSYZ,BZ,YWFTJE,YEARMONTH)");
		sql.append("VALUES(");
		sql.append("XL_TT_CW_YBX.nextval,");
		sql.append("'" + cwybx.getRQ() + "',");
		sql.append("'" + cwybx.getBXLX() + "',");
		sql.append("'" + cwybx.getDJPH() + "',");
		sql.append("'" + cwybx.getZY() + "',");
		sql.append("'" + cwybx.getDJZT() + "',");
		sql.append("'" + cwybx.getBM() + "',");
		sql.append("'" + cwybx.getSFJE() + "',");
		sql.append("'" + cwybx.getFPHSJE() + "',");
		sql.append("'" + cwybx.getFPBHSJE() + "',");
		sql.append("'" + cwybx.getJSDH() + "',");
		sql.append("'" + cwybx.getZDMC() + "',");
		sql.append("'" + cwybx.getZDBH() + "',");
		sql.append("'" + cwybx.getCWYFTHYDCWBXJE() + "',");
		sql.append("'" + cwybx.getCY() + "',");
		sql.append("'" + cwybx.getFSYZ() + "',");
		sql.append("'" + cwybx.getBZ() + "',");
		sql.append("'" + cwybx.getYWFTJE() + "',");
		sql.append("'" + cwybx.getYEARMONTH() + "'");
		sql.append(")");
		log.info("添加财务已报销数据SQL:" + sql.toString());

		DataBase db = new DataBase();
		try {
			db.connectDb();
			int i = db.update(sql.toString());
			System.out.println(i);
			if(i==1){
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

	public List<CwybxBean> searchCwybx_fy(String loginName, String loginId,
			int pageSize, int curpage, String sheng, String shi, String xian,
			String xiaoqu, String _hdzt, String _qrzt, String _pc,String _zdbm,String _dh,String _cy) {
		List<CwybxBean> cwybxList = new ArrayList<CwybxBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (SELECT tt.*,ROWNUM AS rowno FROM (");
		sql.append("SELECT A.ID,A.RQ,A.BXLX,A.DJPH,A.ZY,A.DJZT,A.BM,A.SFJE,A.FPHSJE,A.FPBHSJE,A.JSDH,A.ZDMC,A.ZDBH,A.CWYFTHYDCWBXJE,A.CY,A.FSYZ,A.BZ,A.YWFTJE,A.YEARMONTH,A.HDZT,A.QRZT");
		sql.append(" FROM TBL_TT_CW_YBX A,ZHANDIAN B WHERE A.ZDBH = B.JZCODE");
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
			sql.append(" AND A.HDZT='" + _hdzt + "'");
		}

		if (_pc != null && !_pc.equals("")) {
			sql.append(" AND A.YEARMONTH='" + _pc + "'");
		}
		
		if (_zdbm != null && !_zdbm.equals("")) {
			sql.append(" AND A.ZDBH='" + _zdbm + "'");
		}
		

		if (!_qrzt.equals("0")) {
			sql.append(" AND A.QRZT='" + _qrzt + "'");
		}
		
		if(_dh!=null&&!_dh.equals("")){
			sql.append(" AND A.JSDH='" + _dh + "'");
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
		log.info("财务已报销记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					int rownum = rs.getInt("ROWNO");
					String id = rs.getString("ID");
					String rq = rs.getString("RQ");
					String bxlx = rs.getString("BXLX");
					String djph = rs.getString("DJPH");
					String zy = rs.getString("ZY");
					String djzt = rs.getString("DJZT");
					String bm = rs.getString("BM");
					String sfje = rs.getString("SFJE");
					String fphsje = rs.getString("FPHSJE");
					String fpbhsje = rs.getString("FPBHSJE");
					String jsdh = rs.getString("JSDH");
					String zdmc = rs.getString("ZDMC");
					String zdbh = rs.getString("ZDBH");
					String cwyfthydcwbxje = rs.getString("CWYFTHYDCWBXJE") == null||rs.getString("CWYFTHYDCWBXJE").equals("")||rs.getString("CWYFTHYDCWBXJE").equals("null") ? "": rs.getString("CWYFTHYDCWBXJE");
					String cy = rs.getString("CY") == null||rs.getString("CY").equals("")||rs.getString("CY").equals("null")?"" : rs.getString("CY");
					String fsyz = rs.getString("FSYZ") == null||rs.getString("FSYZ").equals("")||rs.getString("FSYZ").equals("null") ? "" : rs.getString("FSYZ");
					String bz = rs.getString("BZ") == null ||rs.getString("BZ").equals("")||rs.getString("BZ").equals("null")? "" : rs.getString("BZ");
					String ywftje = rs.getString("YWFTJE")==null||rs.getString("YWFTJE").equals("")||rs.getString("YWFTJE").equals("null")?"":rs.getString("YWFTJE");
					String yearmonth = rs.getString("YEARMONTH");
					String hdzt = rs.getString("HDZT");
					String qrzt = rs.getString("QRZT");
					
					CwybxBean cwybx = new CwybxBean();
					cwybx.setRownum(rownum);
					cwybx.setID(id);
					cwybx.setRQ(rq);
					cwybx.setBXLX(bxlx);
					cwybx.setDJPH(djph);
					cwybx.setZY(zy);
					cwybx.setDJZT(djzt);
					cwybx.setBM(bm);
					cwybx.setSFJE(sfje);
					cwybx.setFPHSJE(fphsje);
					cwybx.setFPBHSJE(fpbhsje);
					cwybx.setJSDH(jsdh);
					cwybx.setZDMC(zdmc);
					cwybx.setZDBH(zdbh);
					cwybx.setCWYFTHYDCWBXJE(cwyfthydcwbxje);
					cwybx.setCY(cy);
					cwybx.setFSYZ(fsyz);
					cwybx.setBZ(bz);
					cwybx.setYWFTJE(ywftje);
					cwybx.setYEARMONTH(yearmonth);
					cwybx.setHDZT(hdzt);
					cwybx.setQRZT(qrzt);
					cwybxList.add(cwybx);

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
		return cwybxList;
	}

	public List<CwybxBean> searchCwybx(String cwyearmonth) {
		List<CwybxBean> cwybxList = new ArrayList<CwybxBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,RQ,BXLX,DJPH,ZY,DJZT,BM,SFJE,FPHSJE,FPBHSJE,JSDH,ZDMC,ZDBH,CWYFTHYDCWBXJE,CY,FSYZ,BZ,YWFTJE,YEARMONTH");
		sql.append(" FROM TBL_TT_CW_YBX WHERE YEARMONTH = '" + cwyearmonth
				+ "'");
		sql.append(" ORDER BY YEARMONTH DESC");
		log.info("财务已报销记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String rq = rs.getString("RQ");
					String bxlx = rs.getString("BXLX");
					String djph = rs.getString("DJPH");
					String zy = rs.getString("ZY");
					String djzt = rs.getString("DJZT");
					String bm = rs.getString("BM");
					String sfje = rs.getString("SFJE");
					String fphsje = rs.getString("FPHSJE");
					String fpbhsje = rs.getString("FPBHSJE");
					String jsdh = rs.getString("JSDH");
					String zdmc = rs.getString("ZDMC");
					String zdbh = rs.getString("ZDBH");
					String cwyfthydcwbxje = rs.getString("CWYFTHYDCWBXJE");
					String cy = rs.getString("CY");
					String fsyz = rs.getString("FSYZ");
					String bz = rs.getString("BZ");
					String ywftje = rs.getString("YWFTJE");
					String yearmonth = rs.getString("YEARMONTH");
					CwybxBean cwybx = new CwybxBean();
					cwybx.setID(id);
					cwybx.setRQ(rq);
					cwybx.setBXLX(bxlx);
					cwybx.setDJPH(djph);
					cwybx.setZY(zy);
					cwybx.setDJZT(djzt);
					cwybx.setBM(bm);
					cwybx.setSFJE(sfje);
					cwybx.setFPHSJE(fphsje);
					cwybx.setFPBHSJE(fpbhsje);
					cwybx.setJSDH(jsdh);
					cwybx.setZDMC(zdmc);
					cwybx.setZDBH(zdbh);
					cwybx.setCWYFTHYDCWBXJE(cwyfthydcwbxje);
					cwybx.setCY(cy);
					cwybx.setFSYZ(fsyz);
					cwybx.setBZ(bz);
					cwybx.setYWFTJE(ywftje);
					cwybx.setYEARMONTH(yearmonth);
					cwybxList.add(cwybx);

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
		return cwybxList;
	}

	public boolean updateCwybx_shzt(String yearmonth) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_CW_YBX SET HDZT = '已核对' WHERE YEARMONTH = '"
				+ yearmonth + "'");
		log.info("核对财务已报销数据SQL:" + sql.toString());
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
		return flag;
	}

	public boolean updateCwybx(String cwybx_id, String cwybx_cwyfthydcwybxje,
			String cwybx_cy, String cwybx_wyftje, String cwybx_fsyz,String cwybx_bz) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_CW_YBX SET HDZT = '已核对',BZ = '"+cwybx_bz+"', CWYFTHYDCWBXJE = '"
				+ cwybx_cwyfthydcwybxje + "',CY='" + cwybx_cy + "',YWFTJE = '"
				+ cwybx_wyftje + "',FSYZ = '" + cwybx_fsyz + "' WHERE ID = '"
				+ cwybx_id + "'");
		log.info("核对财务已报销数据SQL:" + sql.toString());

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
		return flag;
	}

	public boolean isCwybxCf(String yearmonth, String zdbm, String jsdh,
			String djph, String fphsje, String fpbhsje) {
		boolean flag = true;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM TBL_TT_CW_YBX WHERE YEARMONTH = '"
				+ yearmonth + "' AND ZDBH = '" + zdbm + "' AND JSDH ='" + jsdh
				+ "' AND DJPH = '" + djph + "' AND FPHSJE = '" + fphsje
				+ "' AND FPBHSJE = '" + fpbhsje + "'");
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
		return flag;
	}

	public boolean updateCwdfft(CwybxBean cwybx) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE TBL_TT_CW_YBX");
		sql.append(" SET ");
		sql.append("RQ = '" + cwybx.getRQ() + "',");
		sql.append("BXLX = '" + cwybx.getBXLX() + "',");
		sql.append("DJPH = '" + cwybx.getDJPH() + "',");
		sql.append("ZY = '" + cwybx.getZY() + "',");
		sql.append("DJZT='" + cwybx.getDJZT() + "',");
		sql.append("BM='" + cwybx.getBM() + "',");
		sql.append("SFJE='" + cwybx.getSFJE() + "',");
		sql.append("FPHSJE='" + cwybx.getFPHSJE() + "',");
		sql.append("FPBHSJE='" + cwybx.getFPBHSJE() + "',");
		sql.append("JSDH='" + cwybx.getJSDH() + "',");
		sql.append("ZDMC='" + cwybx.getZDMC() + "',");
		sql.append("ZDBH='" + cwybx.getZDBH() + "',");
		sql.append("CWYFTHYDCWBXJE='" + cwybx.getCWYFTHYDCWBXJE() + "',");
		sql.append("CY='" + cwybx.getCY() + "',");
		sql.append("FSYZ='" + cwybx.getFSYZ() + "',");
		sql.append("BZ='" + cwybx.getBZ() + "',");
		sql.append("YWFTJE='" + cwybx.getYWFTJE() + "',");
		sql.append("YEARMONTH='" + cwybx.getYEARMONTH() + "'");
		sql.append("WHERE ID = " + cwybx.getID() + "");
		log.info("添加财务已报销数据SQL:" + sql.toString());

		DataBase db = new DataBase();
		try {
			db.connectDb();
			int i = db.update(sql.toString());
			System.out.println(i);
			if(i==0){
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

	public CwybxBean searchCwybxByWyid(String _id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,RQ,BXLX,DJPH,ZY,DJZT,BM,SFJE,FPHSJE,FPBHSJE,JSDH,ZDMC,ZDBH,CWYFTHYDCWBXJE,CY,FSYZ,BZ,YWFTJE,YEARMONTH");
		sql.append(" FROM TBL_TT_CW_YBX WHERE ID = "
				+ _id + "");
		log.info("财务已报销记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String rq = rs.getString("RQ");
					String bxlx = rs.getString("BXLX");
					String djph = rs.getString("DJPH");
					String zy = rs.getString("ZY");
					String djzt = rs.getString("DJZT");
					String bm = rs.getString("BM");
					String sfje = rs.getString("SFJE");
					String fphsje = rs.getString("FPHSJE");
					String fpbhsje = rs.getString("FPBHSJE");
					String jsdh = rs.getString("JSDH");
					String zdmc = rs.getString("ZDMC");
					String zdbh = rs.getString("ZDBH");
					String cwyfthydcwbxje = rs.getString("CWYFTHYDCWBXJE");
					String cy = rs.getString("CY");
					String fsyz = rs.getString("FSYZ");
					String bz = rs.getString("BZ");
					String ywftje = rs.getString("YWFTJE");
					String yearmonth = rs.getString("YEARMONTH");
					CwybxBean cwybx = new CwybxBean();
					cwybx.setID(id);
					cwybx.setRQ(rq);
					cwybx.setBXLX(bxlx);
					cwybx.setDJPH(djph);
					cwybx.setZY(zy);
					cwybx.setDJZT(djzt);
					cwybx.setBM(bm);
					cwybx.setSFJE(sfje);
					cwybx.setFPHSJE(fphsje);
					cwybx.setFPBHSJE(fpbhsje);
					cwybx.setJSDH(jsdh);
					cwybx.setZDMC(zdmc);
					cwybx.setZDBH(zdbh);
					cwybx.setCWYFTHYDCWBXJE(cwyfthydcwbxje);
					cwybx.setCY(cy);
					cwybx.setFSYZ(fsyz);
					cwybx.setBZ(bz);
					cwybx.setYWFTJE(ywftje);
					cwybx.setYEARMONTH(yearmonth);
					return cwybx;

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

	public boolean deleteCwybxById(String id) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE TBL_TT_CW_YBX WHERE ID =" + id + "");
		log.info("财务电费分摊删除SQL:[" + sql.toString() + "]");
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
		return flag;
	}

	public List<CwybxBean> searchCwybxByWydfft(String cwYearMonth,
			String wydfft_dh, String wydfft_zdbm) {
		List<CwybxBean> cwybxList = new ArrayList<CwybxBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,RQ,BXLX,DJPH,ZY,DJZT,BM,SFJE,FPHSJE,FPBHSJE,JSDH,ZDMC,ZDBH,CWYFTHYDCWBXJE,CY,FSYZ,BZ,YWFTJE,YEARMONTH");
		sql.append(" FROM TBL_TT_CW_YBX WHERE YEARMONTH = '" + cwYearMonth
				+ "' AND ZDBH = '"+wydfft_zdbm+"' AND JSDH = '"+wydfft_dh+"'");
		sql.append(" ORDER BY YEARMONTH DESC");
		log.info("财务已报销记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String rq = rs.getString("RQ");
					String bxlx = rs.getString("BXLX");
					String djph = rs.getString("DJPH");
					String zy = rs.getString("ZY");
					String djzt = rs.getString("DJZT");
					String bm = rs.getString("BM");
					String sfje = rs.getString("SFJE");
					String fphsje = rs.getString("FPHSJE");
					String fpbhsje = rs.getString("FPBHSJE");
					String jsdh = rs.getString("JSDH");
					String zdmc = rs.getString("ZDMC");
					String zdbh = rs.getString("ZDBH");
					String cwyfthydcwbxje = rs.getString("CWYFTHYDCWBXJE");
					String cy = rs.getString("CY");
					String fsyz = rs.getString("FSYZ");
					String bz = rs.getString("BZ");
					String ywftje = rs.getString("YWFTJE");
					String yearmonth = rs.getString("YEARMONTH");
					CwybxBean cwybx = new CwybxBean();
					cwybx.setID(id);
					cwybx.setRQ(rq);
					cwybx.setBXLX(bxlx);
					cwybx.setDJPH(djph);
					cwybx.setZY(zy);
					cwybx.setDJZT(djzt);
					cwybx.setBM(bm);
					cwybx.setSFJE(sfje);
					cwybx.setFPHSJE(fphsje);
					cwybx.setFPBHSJE(fpbhsje);
					cwybx.setJSDH(jsdh);
					cwybx.setZDMC(zdmc);
					cwybx.setZDBH(zdbh);
					cwybx.setCWYFTHYDCWBXJE(cwyfthydcwbxje);
					cwybx.setCY(cy);
					cwybx.setFSYZ(fsyz);
					cwybx.setBZ(bz);
					cwybx.setYWFTJE(ywftje);
					cwybx.setYEARMONTH(yearmonth);
					cwybxList.add(cwybx);

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
		return cwybxList;
	}

	public boolean deleteCwybxByPc(String pc) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE TBL_TT_CW_YBX ");
		sql.append(" WHERE YEARMONTH = '"+pc+"'");

		log.info("删除财务已报销数据记录SQL:" + sql.toString());
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
		return flag;
	}

	public List<CwybxBean> searchCwybx_fy(String _zdbm) {
		List<CwybxBean> cwybxList = new ArrayList<CwybxBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT A.ID,A.RQ,A.BXLX,A.DJPH,A.ZY,A.DJZT,A.BM,A.SFJE,A.FPHSJE,A.FPBHSJE,A.JSDH,A.ZDMC,A.ZDBH,A.CWYFTHYDCWBXJE,A.CY,A.FSYZ,A.BZ,A.YWFTJE,A.YEARMONTH,A.HDZT,A.QRZT");
		sql.append(" FROM TBL_TT_CW_YBX A,ZHANDIAN B WHERE A.ZDBH = B.JZCODE");
		
		if (_zdbm != null && !_zdbm.equals("")) {
			sql.append(" AND A.ZDBH=" + _zdbm + "");
		}
		

		sql.append(" ORDER BY A.YEARMONTH DESC");
		log.info("财务已报销记录查询SQL:[" + sql.toString() + "]");
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String rq = rs.getString("RQ");
					String bxlx = rs.getString("BXLX");
					String djph = rs.getString("DJPH");
					String zy = rs.getString("ZY");
					String djzt = rs.getString("DJZT");
					String bm = rs.getString("BM");
					String sfje = rs.getString("SFJE");
					String fphsje = rs.getString("FPHSJE");
					String fpbhsje = rs.getString("FPBHSJE");
					String jsdh = rs.getString("JSDH");
					String zdmc = rs.getString("ZDMC");
					String zdbh = rs.getString("ZDBH");
					String cwyfthydcwbxje = rs.getString("CWYFTHYDCWBXJE") == null ? "0"
							: rs.getString("CWYFTHYDCWBXJE");
					String cy = rs.getString("CY") == null ? "0" : rs
							.getString("CY");
					String fsyz = rs.getString("FSYZ") == null ? "0" : rs
							.getString("FSYZ");
					String bz = rs.getString("BZ") == null ? "0" : rs
							.getString("BZ");
					String ywftje = rs.getString("YWFTJE");
					String yearmonth = rs.getString("YEARMONTH");
					String hdzt = rs.getString("HDZT");
					String qrzt = rs.getString("QRZT");
					CwybxBean cwybx = new CwybxBean();
					cwybx.setID(id);
					cwybx.setRQ(rq);
					cwybx.setBXLX(bxlx);
					cwybx.setDJPH(djph);
					cwybx.setZY(zy);
					cwybx.setDJZT(djzt);
					cwybx.setBM(bm);
					cwybx.setSFJE(sfje);
					cwybx.setFPHSJE(fphsje);
					cwybx.setFPBHSJE(fpbhsje);
					cwybx.setJSDH(jsdh);
					cwybx.setZDMC(zdmc);
					cwybx.setZDBH(zdbh);
					cwybx.setCWYFTHYDCWBXJE(cwyfthydcwbxje);
					cwybx.setCY(cy);
					cwybx.setFSYZ(fsyz);
					cwybx.setBZ(bz);
					cwybx.setYWFTJE(ywftje);
					cwybx.setYEARMONTH(yearmonth);
					cwybx.setHDZT(hdzt);
					cwybx.setQRZT(qrzt);
					cwybxList.add(cwybx);

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
		return cwybxList;
	}

}
