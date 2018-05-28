package com.noki.zwhd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.zwhd.model.DianbiaoBean;
import com.noki.zwhd.model.DwBean;
import com.noki.zwhd.model.ZhandianBean;

public class SystemDaoImpl {

	private static Log log = LogFactory.getLog(SystemDaoImpl.class.getName());

	public List<ZhandianBean> searchZhandian(String _dw, String _qx) {
		List<ZhandianBean> zhandianList = new ArrayList<ZhandianBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,JZNAME,JZCODE FROM ZHANDIAN WHERE 1=1 ");

		if (_dw != null && !_dw.equals("") && !_dw.equals("null")
				&& !_dw.equals("NULL") && !_dw.equals("0")) {
			sql.append("AND SHI = '" + _dw + "'");
		}

		if (_qx != null && !_qx.equals("") && !_qx.equals("null")
				&& !_qx.equals("NULL") && !_qx.equals("0")) {
			sql.append("AND XIAN = '" + _qx + "'");
		}

		log.info(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					ZhandianBean zhandian = new ZhandianBean();
					zhandian.setID(rs.getString("ID"));
					zhandian.setJZNAME(rs.getString("JZNAME"));
					zhandian.setJZCODE(rs.getString("JZCODE"));
					zhandianList.add(zhandian);
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
		return zhandianList;
	}

	public List<DwBean> searchAGR(String AGRADE, String _dw, String userid) {
		List<DwBean> dwBeanList = new ArrayList<DwBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT AGID,AGCODE,AGNAME,FAGCODE,AGRADE,GOOGLEID FROM D_AREA_GRADE WHERE AGRADE = '"
				+ AGRADE + "'");
		if (userid != null && !userid.equals("") && !userid.equals("null")
				&& !userid.equals("NULL") && !userid.equals("0")) {
			sql.append(" AND AGCODE IN (select t.agcode from per_area t where t.accountid='"
					+ userid + "')");
		}
		if (_dw != null && !_dw.equals("") && !_dw.equals("null")
				&& !_dw.equals("NULL") && !_dw.equals("0")) {
			sql.append(" AND FAGCODE = '" + _dw + "'");
		}
		log.info(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					DwBean dwBean = new DwBean();
					dwBean.setAGID(rs.getString("AGID"));
					dwBean.setAGCODE(rs.getString("AGCODE"));
					dwBean.setAGNAME(rs.getString("AGNAME"));
					dwBean.setFAGCODE(rs.getString("FAGCODE"));
					dwBean.setAGRADE(rs.getString("AGRADE"));
					dwBean.setGOOGLEID(rs.getString("GOOGLEID"));
					dwBeanList.add(dwBean);
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
		return dwBeanList;
	}

	public ZhandianBean searchJzByCode(String zdbm) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,JZNAME,GDFS,'抄表结算' AS JSFS,JZCODE FROM ZHANDIAN WHERE 1=1");
		if (zdbm != null && !zdbm.equals("") && !zdbm.equals("null")
				&& !zdbm.equals("NULL") && !zdbm.equals("0")) {
			sql.append("AND JZCODE = '" + zdbm + "'");
		}
		log.info(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					ZhandianBean zhandian = new ZhandianBean();
					zhandian.setID(rs.getString("ID"));
					zhandian.setJZNAME(rs.getString("JZNAME"));
					zhandian.setGDFS(rs.getString("GDFS"));
					zhandian.setJSFS(rs.getString("JSFS"));
					zhandian.setJZCODE(rs.getString("JZCODE"));
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

	public List<String> searchPc() {
		List<String> pcList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT YEARMONTH FROM TBL_TT_CWWY_HDJL GROUP BY YEARMONTH");
		log.info(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					pcList.add(rs.getString("YEARMONTH"));
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
		return pcList;
	}

	public String searchDwNameByDwCode(String t_dsfgs) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT AGNAME FROM D_AREA_GRADE WHERE AGCODE = '" + t_dsfgs
				+ "'");
		log.info(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					return rs.getString("AGNAME");
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

	public ZhandianBean searchJzNameByJzCode(String jzcode) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ZHANDIAN WHERE JZCODE = '" + jzcode + "'");
		log.info(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					ZhandianBean zhandian = new ZhandianBean();
					zhandian.setSHENG(rs.getString("SHENG"));
					zhandian.setSHI(rs.getString("SHI"));
					zhandian.setXIAN(rs.getString("XIAN"));
					zhandian.setJZNAME(rs.getString("JZNAME"));
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

	public ZhandianBean searchZhandianByJcode(String jcode) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,SHENG,SHI,XIAN,SHI AS　DW,XIAN AS QX,JZNAME,WLZDBM,JZCODE,LONGITUDE,LATITUDE,ADDRESS,RTNAME(GDFS) GDFS,'抄表结算' AS JSFS,RTNAME(YCQ) YCQ FROM ZHANDIAN WHERE JZCODE = '"
				+ jcode + "' ");
		log.info(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					ZhandianBean zhandian = new ZhandianBean();
					zhandian.setSHENG(rs.getString("SHENG"));
					zhandian.setSHI(rs.getString("SHI"));
					zhandian.setXIAN(rs.getString("XIAN"));
					zhandian.setDw(rs.getString("DW"));
					zhandian.setQx(rs.getString("QX"));
					zhandian.setWLZDBM(rs.getString("WLZDBM"));
					zhandian.setID(rs.getString("ID"));
					zhandian.setJZNAME(rs.getString("JZNAME"));
					zhandian.setJZCODE(rs.getString("JZCODE"));
					zhandian.setGDFS(rs.getString("GDFS"));
					zhandian.setLONGITUDE(rs.getString("LONGITUDE"));
					zhandian.setLATITUDE(rs.getString("LATITUDE"));
					zhandian.setADDRESS(rs.getString("ADDRESS"));
					zhandian.setJSFS(rs.getString("JSFS"));
					zhandian.setYCQ(rs.getString("YCQ")==null||rs.getString("YCQ").equals("")||rs.getString("YCQ").equals("null")||rs.getString("YCQ").equals("NULL")?"":rs.getString("YCQ"));
					System.out.println("->->->->->->"+zhandian.getYCQ());
					
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

	public List<DianbiaoBean> searchDianbaoList(String zdid) {
		List<DianbiaoBean> dianbiaoList = new ArrayList<DianbiaoBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DBBM,DANJIA FROM DIANBIAO WHERE ZDID = '" + zdid + "' ");
		log.info("[获取电表信息:]"+sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					DianbiaoBean dianbiao = new DianbiaoBean();
					dianbiao.setDBBM(rs.getString("DBBM"));
					String danjia = rs.getString("DANJIA");
					DecimalFormat   df   = new   DecimalFormat("#0.00"); 
					danjia = df.format(Double.parseDouble(danjia));
					dianbiao.setDANJIA(danjia);
					dianbiaoList.add(dianbiao);
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
		return dianbiaoList;
	}

	public List<ZhandianBean> searchZhandian(String _qx) {
		List<ZhandianBean> zhandianList = new ArrayList<ZhandianBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,JZNAME,JZCODE FROM ZHANDIAN WHERE 1=1 ");

		if (_qx != null && !_qx.equals("") && !_qx.equals("null")
				&& !_qx.equals("NULL") && !_qx.equals("0")) {
			sql.append("AND XIAN = '" + _qx + "'");
		}

		log.info(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					ZhandianBean zhandian = new ZhandianBean();
					zhandian.setID(rs.getString("ID"));
					zhandian.setJZNAME(rs.getString("JZNAME"));
					zhandian.setJZCODE(rs.getString("JZCODE"));
					zhandianList.add(zhandian);
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
		return zhandianList;
	}

	public List<ZhandianBean> searchZhandianyd(String _shi,String _qx) {
		List<ZhandianBean> zhandianList = new ArrayList<ZhandianBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT ZHANDIAN.ID,ZHANDIAN.JZNAME,ZHANDIAN.JZCODE FROM ZHANDIAN,TBL_TT_WY_DFFT T WHERE T.ZDBM=JZCODE AND 1=1 ");

		if (_qx != null && !_qx.equals("") && !_qx.equals("null")&& !_qx.equals("NULL") && !_qx.equals("0")) {
			sql.append("AND XIAN = '" + _qx + "'");
		}
		
		if (_shi != null && !_shi.equals("") && !_shi.equals("null")&& !_shi.equals("NULL") && !_shi.equals("0")) {
			sql.append("AND SHI = '" + _shi + "'");
		}

		log.info("[基站查询]"+sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					ZhandianBean zhandian = new ZhandianBean();
					zhandian.setID(rs.getString("ID"));
					zhandian.setJZNAME(rs.getString("JZNAME"));
					zhandian.setJZCODE(rs.getString("JZCODE"));
					zhandianList.add(zhandian);
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
		return zhandianList;
	}
	public List<ZhandianBean> searchZhandianZX(String _shi,String _qx) {
		List<ZhandianBean> zhandianList = new ArrayList<ZhandianBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT ZHANDIAN.ID,ZHANDIAN.JZNAME,ZHANDIAN.JZCODE FROM ZHANDIAN,tbl_tt_biaogan_lishi T WHERE T.ZDBM=JZCODE AND 1=1 ");

		if (_qx != null && !_qx.equals("") && !_qx.equals("null")&& !_qx.equals("NULL") && !_qx.equals("0")) {
			sql.append("AND XIAN = '" + _qx + "'");
		}
		
		if (_shi != null && !_shi.equals("") && !_shi.equals("null")&& !_shi.equals("NULL") && !_shi.equals("0")) {
			sql.append("AND SHI = '" + _shi + "'");
		}

		log.info("[基站查询]"+sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					ZhandianBean zhandian = new ZhandianBean();
					zhandian.setID(rs.getString("ID"));
					zhandian.setJZNAME(rs.getString("JZNAME"));
					zhandian.setJZCODE(rs.getString("JZCODE"));
					zhandianList.add(zhandian);
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
		return zhandianList;
	}
}
