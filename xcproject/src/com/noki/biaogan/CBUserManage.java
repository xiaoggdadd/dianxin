package com.noki.biaogan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noki.biaogan.model.AmmertdegreeBean;
import com.noki.biaogan.model.CbUserBean;
import com.noki.biaogan.model.VersionInfo;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;
import com.noki.zwhd.model.DianbiaoBean;
import com.noki.zwhd.model.ZhandianBean;

public class CBUserManage {
	private static Log log = LogFactory.getLog(CBUserManage.class.getName());

	public CbUserBean doLogin(String loginName, String passWord) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.ACCOUNTID,a.ACCOUNTNAME,a.NAME,a.PASSWORD from account a where a.ACCOUNTNAME = '"
				+ loginName + "' AND PASSWORD = '" + passWord + "'");

		// sql.append("SELECT ID,USERNAME,PASSWORD,LOGINNAME,TO_CHAR(CREATETIME,'yyyy-mm-dd hh24:mi:ss') AS CREATETIME,CREATEUSER,TO_CHAR(UPDATETIME,'yyyy-mm-dd hh24:mi:ss') AS UPDATETIME,UPDATEUSER,DELETEFLAG,ISAUTOGPS FROM TBL_APP_USER WHERE LOGINNAME = '"
		// + loginName
		// + "' AND PASSWORD = '"
		// + passWord
		// + "' AND DELETEFLAG = '0'");
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				String id = rs.getString("ACCOUNTID");
				String username = rs.getString("NAME");
				String password = rs.getString("PASSWORD");
				String loginname = rs.getString("ACCOUNTNAME");
				

				CbUserBean user = new CbUserBean();
				user.setID(id);
				user.setUSERNAME(username);
				user.setPASSWORD(password);
				user.setLOGINNAME(loginname);
			
			
				return user;
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
		return null;
	}
	/**
	 * shuo
	 * @param loginName
	 * @return
	 */
	public String doLoginrunphone(String loginName) {
		StringBuffer sql = new StringBuffer();
		String stf = "";
		sql.append("select a.NAME,a.ACCOUNTNAME,a.PASSWORD from account a where a.MOBILE = '"
				+ loginName + "'");
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()) {
				stf = rs.getString("ACCOUNTNAME");
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stf;
	}
////////////////////////////////
	public boolean doLogin(String loginName) {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.ACCOUNTID,a.ACCOUNTNAME,a.NAME,a.PASSWORD from account a where a.ACCOUNTNAME = '"
				+ loginName + "'");
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				return true;
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
		return false;
		
	}

	public List<ZhandianBean> searchZd(String sheng, String shi, String xian) {
		List<ZhandianBean> zdList = new ArrayList<ZhandianBean>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,JZNAME FROM ZHANDIAN WHERE SHENG = '" + sheng
				+ "' and SHI = '" + shi + "' and XIAN = '" + xian + "'");
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				String id = rs.getString("ID");
				String jzname = rs.getString("JZNAME");

				ZhandianBean zd = new ZhandianBean();
				zd.setID(id);
				zd.setJZNAME(jzname);
				zdList.add(zd);
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
		return zdList;
	}

	// 获取数据库的图片存储路径的方法
	public String searchZdByUserurl() throws SQLException {

		String imagepath = "";
		String sql = "SELECT IMAGEPATH FROM TBL_APP_IMAGEPATH WHERE 1=1";

		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				imagepath = rs.getString("IMAGEPATH");
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imagepath;

	}

	public List<AmmertdegreeBean> searchdbByUserid(String zdid) {
		List<AmmertdegreeBean> zdList = new ArrayList<AmmertdegreeBean>();
		String sql = "SELECT BEILV,DANJIAOLD,DLONGITUDE,DLATITUDE,ELECTRIC_SUPPLY_WAY FROM DIANBIAO WHERE ID = '"
				+ zdid + "'";
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				AmmertdegreeBean zd = new AmmertdegreeBean();
				zd.setBEILV(rs.getString("BEILV"));
				zd.setDANJIAOLD(rs.getString("DANJIAOLD"));
				zd.setDlongitude(rs.getString("DLONGITUDE"));
				zd.setDlatitude(rs.getString("DLATITUDE"));
				zd.setELECTRICCURRENTTYPE(rs.getString("ELECTRIC_SUPPLY_WAY"));
				zdList.add(zd);
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
		return zdList;
	}

	public List<ZhandianBean> searchZdByUserid(String userid) {
		List<ZhandianBean> zdList = new ArrayList<ZhandianBean>();
		String sql = "SELECT Z.JZNAME FROM " +
				"( SELECT P.AGCODE FROM ACCOUNT A , PER_AREA P WHERE A.ACCOUNTID = P.ACCOUNTID AND A.ACCOUNTID   ='"+ userid + "' ) " +
				"AF LEFT JOIN ZHANDIAN Z ON AF.AGCODE = Z.XIAN";
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				if(rs.getString("JZNAME")!=null || rs.getString("JZNAME")!=""){
				ZhandianBean zd = new ZhandianBean();
				zd.setJZNAME(rs.getString("JZNAME"));
				zdList.add(zd);
				}
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
		return zdList;
	}

	public List<ZhandianBean> searchZd(String zdids) {
		List<ZhandianBean> zdList = new ArrayList<ZhandianBean>();
		String[] zdbmList = zdids.split(",");
		for (int i = 0; i < zdbmList.length; i++) {
			String zdid = zdbmList[i];
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ID,JZNAME FROM ZHANDIAN WHERE ID = '" + zdid
					+ "'");
			log.info(sql);
			DataBase db = new DataBase();
			ResultSet rs = null;
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String id = rs.getString("ID");
					String jzname = rs.getString("JZNAME");

					ZhandianBean zd = new ZhandianBean();
					zd.setID(id);
					zd.setJZNAME(jzname);
					zdList.add(zd);
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
		}
		return zdList;
	}

	public List<DianbiaoBean> searchDb(String jzId) {
		List<DianbiaoBean> dbList = new ArrayList<DianbiaoBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,DBNAME,DLONGITUDE,DLATITUDE FROM DIANBIAO WHERE ZDID = '"
				+ jzId + "'");
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				String id = rs.getString("ID");
				String dbname = rs.getString("DBNAME");
				String dlongitude = rs.getString("DLONGITUDE");
				String dlatitude = rs.getString("DLATITUDE");

				DianbiaoBean dianbiao = new DianbiaoBean();
				// dianbiao.setDBID(dbid);
				// dianbiao.setDBBM(dbbm);
				dianbiao.setDLONGITUDE(dlongitude);
				dianbiao.setDLATITUDE(dlatitude);
				dianbiao.setID(id);
				dianbiao.setDBNAME(dbname);
				dbList.add(dianbiao);
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
		return dbList;
	}

	public String searchZdbms(String userid) {
		String zdids = new String();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ZDID FROM TBL_APP_USER_BD WHERE USERID = '" + userid
				+ "'");
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				String zdid = rs.getString("ZDID");
				zdids += zdid + ",";
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
		log.info(zdids);
		if (!zdids.equals("")) {
			zdids = zdids.substring(0, zdids.length() - 1);
		}
		return zdids;
	}

	// 位置保存的保存方法
	public boolean saveSetValue(AmmertdegreeBean ammertset) {
		System.out.println(ammertset.getLongitude() + ","
				+ ammertset.getLatitude());
		StringBuffer sql_i_dl = new StringBuffer();
		if (ammertset.getDlongitude() != null
				&& ammertset.getDlongitude().length() > 0) {
			sql_i_dl.append("UPDATE DIANBIAO SET DLONGITUDE='"
					+ ammertset.getDlongitude() + "',DLATITUDE='"
					+ ammertset.getDlatitude() + "' WHERE ID='"
					+ ammertset.getDBID() + "'");
			log.info("[站点位置入库]" + sql_i_dl);
			/**
			 * 1.查询电表电费支付类型 2.预付 DRBG//单日标杆金额->不乘周期的值 单日标杆(金额)：单日标杆*电价
			 * 入库电费表DRBG字段 3.插卡 一样
			 * 
			 */

			DataBase db = new DataBase();
			try {
				db.connectDb();
				db.setAutoCommit(false);
				db.update(sql_i_dl.toString());
				db.commit();
				return true;
			} catch (Exception de) {
				de.printStackTrace();
				try {
					db.rollback();
				} catch (DbException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean saveCbValue(AmmertdegreeBean ammertdegree,String userid) {
		StringBuffer sql_i_dl = new StringBuffer();

		if (ammertdegree.getELECTRICCURRENTTYPE() == "gdfs05") {	//直供电
			String st = "to_date('" + ammertdegree.getSTARTTIME()
					+ "','yyyy-mm-dd hh24:mi:ss')";
			String et = "to_date('" + ammertdegree.getENDTIME()
					+ "','yyyy-mm-dd hh24:mi:ss')";
			String en = "to_date('" + ammertdegree.getENDTIME()
					+ "','yyyy-mm-dd hh24:mi:ss')";
			sql_i_dl.append("INSERT INTO ELECTRICFEES (EAO_C,ELECTRICFEE_ID,STARTTIME_C,ENDTIME_C,DIANBIAOID,SQDS_C,BQDS_C,DIANLIANG_C,DBIMAGEPATH1,DBIMAGEPATH2,DBIMAGEPATH3,ROOMJFXX,ROOMDBCH,DAYNUM_C,CREATEDATE,BEILV,ENTRYPENSONNEL,ENTRYTIMEOLD) VALUES ('ea',ELECTRICFEES_SEQ.nextval,"
					+ ""
					+ st
					+ ","
					+ ""
					+ et
					+ ","
					+ "'"
					+ ammertdegree.getDIANBIAOID()
					+ "',"
					+ "'"
					+ ammertdegree.getSQDS()
					+ "',"
					+ "'"
					+ ammertdegree.getBQDS()
					+ "',"
					+ "'"
					+ ammertdegree.getDIANLIANG()
					+ "',"
					+ "'"
					+ ammertdegree.getDBIMAGEPATH1()
					+ "',"
					+ "'"
					+ ammertdegree.getDBIMAGEPATH2()
					+ "',"
					+ "'"
					+ ammertdegree.getDBIMAGEPATH3()
					+ "',"
					+ "'"
					+ ammertdegree.getJfxx()
					+ "',"
					+ "'"
					+ ammertdegree.getDbch()
					+ "',"
					+ "'"
					+ ammertdegree.getDAYNUM()
					+ "',"
					+ "'"
					+ ammertdegree.getCREATEDATE()
					+ "',"
					+ "'"
					+ ammertdegree.getBEILV() + "','" +userid+ "'," + en + "" + ")"

			);
		} else if (ammertdegree.getDANJIAOLD() == null
				|| "".equals(ammertdegree.getDANJIAOLD())) {
			String st = "to_date('" + ammertdegree.getSTARTTIME()
					+ "','yyyy-mm-dd hh24:mi:ss')";
			String et = "to_date('" + ammertdegree.getENDTIME()
					+ "','yyyy-mm-dd hh24:mi:ss')";
			String en = "to_date('" + ammertdegree.getENDTIME()
					+ "','yyyy-mm-dd hh24:mi:ss')";
			sql_i_dl.append("INSERT INTO ELECTRICFEES (EAO_C,ELECTRICFEE_ID,STARTTIME_C,ENDTIME_C,DIANBIAOID,SQDS_C,BQDS_C,DIANLIANG_C,DBIMAGEPATH1,DBIMAGEPATH2,DBIMAGEPATH3,ROOMJFXX,ROOMDBCH,DAYNUM_C,CREATEDATE,BEILV,ENTRYPENSONNEL,ENTRYTIMEOLD) VALUES ('ea',ELECTRICFEES_SEQ.nextval,"
					+ ""
					+ st
					+ ","
					+ ""
					+ et
					+ ","
					+ "'"
					+ ammertdegree.getDIANBIAOID()
					+ "',"
					+ "'"
					+ ammertdegree.getSQDS()
					+ "',"
					+ "'"
					+ ammertdegree.getBQDS()
					+ "',"
					+ "'"
					+ ammertdegree.getDIANLIANG()
					+ "',"
					+ "'"
					+ ammertdegree.getDBIMAGEPATH1()
					+ "',"
					+ "'"
					+ ammertdegree.getDBIMAGEPATH2()
					+ "',"
					+ "'"
					+ ammertdegree.getDBIMAGEPATH3()
					+ "',"
					+ "'"
					+ ammertdegree.getJfxx()
					+ "',"
					+ "'"
					+ ammertdegree.getDbch()
					+ "',"
					+ "'"
					+ ammertdegree.getDAYNUM()
					+ "',"
					+ "'"
					+ ammertdegree.getCREATEDATE()
					+ "',"
					+ "'"
					+ ammertdegree.getBEILV() + "','"+userid + "'," + en + "" + ")"

			);
		} else {
			String st = "to_date('" + ammertdegree.getSTARTTIME()
					+ "','yyyy-mm-dd hh24:mi:ss')";
			String et = "to_date('" + ammertdegree.getENDTIME()
					+ "','yyyy-mm-dd hh24:mi:ss')";
			String en = "to_date('" + ammertdegree.getENDTIME()
					+ "','yyyy-mm-dd hh24:mi:ss')";

			sql_i_dl.append("INSERT INTO ELECTRICFEES (EAO_C,ELECTRICFEE_ID,STARTTIME_C,ENDTIME_C,DIANBIAOID,SQDS_C,BQDS_C,DIANLIANG_C,DBIMAGEPATH1,DBIMAGEPATH2,DBIMAGEPATH3,ROOMJFXX,ROOMDBCH,DAYNUM_C,PRICE,CREATEDATE,BEILV,ENTRYPENSONNEL,ENTRYTIMEOLD) VALUES ('ea',ELECTRICFEES_SEQ.nextval,"
					+ ""
					+ st
					+ ","
					+ ""
					+ et
					+ ","
					+ "'"
					+ ammertdegree.getDIANBIAOID()
					+ "',"
					+ "'"
					+ ammertdegree.getSQDS()
					+ "',"
					+ "'"
					+ ammertdegree.getBQDS()
					+ "',"
					+ "'"
					+ ammertdegree.getDIANLIANG()
					+ "',"
					+ "'"
					+ ammertdegree.getDBIMAGEPATH1()
					+ "',"
					+ "'"
					+ ammertdegree.getDBIMAGEPATH2()
					+ "',"
					+ "'"
					+ ammertdegree.getDBIMAGEPATH3()
					+ "',"
					+ "'"
					+ ammertdegree.getJfxx()
					+ "',"
					+ "'"
					+ ammertdegree.getDbch()
					+ "',"
					+ "'"
					+ ammertdegree.getDAYNUM()
					+ "',"
					+ "'"
					+ ammertdegree.getDANJIAOLD()
					+ "',"
					+ "'"
					+ ammertdegree.getCREATEDATE()
					+ "',"
					+ "'"
					+ ammertdegree.getBEILV() + "','"+userid + "'," + en + "" + ")"

			);
		}

		log.info("[手机抄表入库入库]" + sql_i_dl);

		/**
		 * 1.查询电表电费支付类型 2.预付 DRBG//单日标杆金额->不乘周期的值 单日标杆(金额)：单日标杆*电价 入库电费表DRBG字段
		 * 3.插卡 一样
		 * 
		 */

		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.setAutoCommit(false);
			db.update(sql_i_dl.toString());

			// double zlfh = 0;
			// double ydxs = 1.44;
			// double ktxs = 0;
			// double zq = 0;
			// double bgValue = 0;

			// BiaoganManage biaoganManage = new BiaoganManage();
			// String lastCbDate = biaoganManage.searchLastCbDate(ammertdegree
			// .getAMMETERID_FK());
			// if (lastCbDate != null) {
			// Date lastCbTime = sdf_bg.parse(lastCbDate);
			// Date thisCbTime = sdf_bg.parse(ammertdegree.getTHISDATETIME());
			// zq = (thisCbTime.getTime() - lastCbTime.getTime()) / 1000 / 60
			// / 60 / 24;
			// }
			//
			// int month =
			// Integer.parseInt(ammertdegree.getTHISDATETIME().split(
			// " ")[0].split("-")[1]);
			//
			// if (month == 1 || month == 2 || month == 11 || month == 12) {
			// ktxs = 0.2;
			// } else if (month == 3 || month == 4 || month == 9 || month == 10)
			// {
			// ktxs = 0.4;
			// } else if (month == 5 || month == 6 || month == 7 || month == 8)
			// {
			// ktxs = 0.6;
			// }
			//
			// StringBuffer sql_s_zlfh = new StringBuffer();
			// sql_s_zlfh.append("SELECT ZDL FROM FTBL WHERE YEARMONTH = '"
			// + yearmonth + "' AND DBID = '"
			// + ammertdegree.getAMMETERID_FK() + "' ");
			// ResultSet rs = db.queryAll(sql_s_zlfh.toString());
			// while (rs.next()){
			// zlfh = Double.parseDouble(rs.getString("ZDL"));
			// }
			//
			// bgValue = zlfh * ydxs * (1 + ktxs) * zq;
			//
			// BiaoganManage manage = new BiaoganManage();
			// com.noki.biaogan.model.DianbiaoBean dianbiao =
			// manage.searchDbByDbid(ammertdegree.getAMMETERID_FK());
			// com.noki.biaogan.model.ZhandianBean zhandian =
			// manage.searchZdByZdid(dianbiao.getZDID());
			//
			// String dbbm =
			// dianbiao==null||dianbiao.getDBBM()==null?"":dianbiao.getDBBM();
			// String zdbm =
			// zhandian==null||zhandian.getJZCODE()==null?"":zhandian.getJZCODE();
			// String zdid =
			// zhandian==null||zhandian.getID()==null?"":zhandian.getID();
			// StringBuffer sql_i_bg = new StringBuffer();
			// sql_i_bg.append("INSERT INTO TBL_TT_BIAOGAN_LISHI (ID,YEARMONTH,DBID,ZLFH,YDXS,KTXS,ZQ,BIAOGANVALUE,DBBM,ZDBM,ZDID) VALUES(XL_TT_BIAOGAN_LISHI.nextval,'"
			// + yearmonth
			// + "','"
			// + ammertdegree.getAMMETERID_FK()
			// + "','"
			// + zlfh
			// + "','"
			// + ydxs
			// + "','"
			// + ktxs
			// + "','"
			// + zq
			// + "','" + bgValue + "','"+dbbm+"','"+zdbm+"','"+zdid+"')");
			// log.info("[录入标杆值]"+sql_i_bg);
			// db.update(sql_i_bg.toString());

			// StringBuffer sql_s_dl = new StringBuffer();
			// sql_s_dl.append("select AMMETERDEGREEID from AMMETERDEGREES where AMMETERID_FK = '"
			// + ammertdegree.getAMMETERID_FK()
			// + "' and THISDATE = to_date('"
			// + ammertdegree.getTHISDATE()
			// + "','yyyy-mm-dd') ");
			// log.info("[电量主键查询]" + sql_s_dl);
			//
			// rs = db.queryAll(sql_s_dl.toString());
			// while (rs.next()) {
			// int dlid = rs.getInt("AMMETERDEGREEID");
			// double dj = 0;
			//
			// double ftbl_yd = 0;
			// double ftbl_lt = 0;
			// double ftbl_dx = 0;
			// double ftbl_other = 0;
			// double bg = 0;
			// double fz = 0;
			// // StringBuffer sql_s_dbbm = new StringBuffer();
			// //
			// sql_s_dbbm.append("select DBBM from dianbiao where id = '"+ammertdegree.getAMMETERID_FK()+"'");
			// // log.info("[根据电表id获取电表编码]"+sql_s_dbbm);
			//
			// StringBuffer sql_s_dj = new StringBuffer();
			// sql_s_dj.append("select DANJIA from dianjia where DBID = '"
			// + ammertdegree.getAMMETERID_FK()
			// + "' and YEARMONTH = '" + yearmonth + "' ");
			// log.info("[电价查询]" + sql_s_dj);
			// rs = db.queryAll(sql_s_dj.toString());
			// while (rs.next()) {
			// dj = Double.parseDouble(rs.getString("DANJIA"));
			// }
			//
			// double t_dl = Double.parseDouble(ammertdegree.getTHISDEGREE());
			// double l_dl = Double.parseDouble(ammertdegree.getLASTDEGREE());
			// double dlc = t_dl - l_dl;
			// double df = (dlc) * dj;
			//
			// String notetypeid = ammertdegree.getNOTETYPEID();
			// if (notetypeid.equals("pjlx05")) {
			// df = df * (0.1964 + 1);
			// }
			//
			// StringBuffer sql_s_ftbl = new StringBuffer();
			// sql_s_ftbl
			// .append("select DIANXIN,LIANTONG,YIDONG,QITA from ftbl where dbid = '"
			// + ammertdegree.getAMMETERID_FK()
			// + "' and YEARMONTH = '" + yearmonth + "' ");
			// log.info("[分摊比例查询]" + sql_s_ftbl);
			// rs = db.queryAll(sql_s_ftbl.toString());
			// while (rs.next()) {
			// String dianxin = rs.getString("DIANXIN") == null ? "0.0"
			// : rs.getString("DIANXIN");
			// ftbl_dx = Double.parseDouble(dianxin);
			// String liantong = rs.getString("LIANTONG") == null ? "0.0"
			// : rs.getString("LIANTONG");
			// ftbl_lt = Double.parseDouble(liantong);
			// String yidong = rs.getString("YIDONG") == null ? "0.0" : rs
			// .getString("YIDONG");
			// ftbl_yd = Double.parseDouble(yidong);
			// String qita =
			// rs.getString("QITA")==null?"0.0":rs.getString("QITA");
			// ftbl_other = Double.parseDouble(qita);
			// }
			//
			// double ftje_yd = df * ftbl_yd / 100;
			// double ftje_lt = df * ftbl_lt / 100;
			// double ftje_dx = df * ftbl_dx / 100;
			// double ftje_other = df * ftbl_other / 100;
			//
			// df = ftje_yd+ftje_lt+ftje_dx+ftje_other;
			//
			// StringBuffer sql_s_bg = new StringBuffer();
			// sql_s_bg.append("select BIAOGANVALUE from tbl_tt_biaogan_lishi where YEARMONTH = '"
			// + yearmonth
			// + "' and DBID = '"
			// + ammertdegree.getAMMETERID_FK() + "' ");
			// log.info("[标杆查询]" + sql_s_bg);
			// rs = db.queryAll(sql_s_bg.toString());
			// while (rs.next()) {
			// bg = Double.parseDouble(rs.getString("BIAOGANVALUE"));
			// }
			//
			// StringBuffer sql_s_fz = new StringBuffer();
			// sql_s_fz.append("select code from indexs where type = 'CBGFZ'");
			// log.info("[标杆阀值查询]" + sql_s_fz);
			// rs = db.queryAll(sql_s_fz.toString());
			// while (rs.next()) {
			// fz = Double.parseDouble(rs.getString("code"));
			// }
			//
			// int isBgAlert = 0;
			// if (dlc > bg) {
			// if (((dlc - bg) / bg) * 100 > fz) {
			// isBgAlert = 1;
			// }
			// }
			//
			// log.info("[与标杆的比例]" + (dlc - bg) + "[比例]" + (dlc - bg) / bg);
			//
			//
			// Double drBgValue = zlfh * ydxs * (1 + ktxs)*dj;
			//
			// StringBuffer sql_i_df = new StringBuffer();
			// sql_i_df.append("INSERT INTO ELECTRICFEES (ELECTRICFEE_ID,DRBG,ACTUALPAY,AMMETERDEGREEID_FK,UNITPRICE,ENTRYPENSONNEL,ENTRYTIME,ACTUALPAY_L,ACTUALPAY_Y,ACTUALPAY_D,ACTUALPAY_O,FTBL_L,FTBL_Y,FTBL_D,FTBL_O,CB_ALERT,ACCOUNTMONTH,BG,NOTETYPEID,LINELOSS,DFBZW,CREATEDATE)");
			// sql_i_df.append(" VALUES(ELECTRICFEES_SEQ.nextval,'" + drBgValue
			// + "','"+(ftje_yd+ftje_lt+ftje_dx+ftje_other)+"','" + dlid + "','"
			// + dj + "','"
			// + ammertdegree.getCBRSJH() + "',to_date('"
			// + ammertdegree.getTHISDATE() + "','yyyy-mm-dd'),'"
			// + ftje_lt + "','" + ftje_yd + "','" + ftje_dx + "','"
			// + ftje_other + "','" + ftbl_lt + "','" + ftbl_yd
			// + "','" + ftbl_dx + "','" + ftbl_other + "','"
			// + isBgAlert + "',to_date('" + yearmonth
			// + "','yyyy-mm'),'" + bg + "','"
			// + ammertdegree.getNOTETYPEID() + "','"
			// + ammertdegree.getLINELOSS() + "','"
			// + ammertdegree.getDFBZW() + "','"
			// + sdf_bg.format(new Date()) + "')");
			// log.info("[电费入库]" + sql_i_df);
			//
			// db.update(sql_i_df.toString());
			// }
			db.commit();
			return true;
		} catch (Exception de) {
			de.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 查询系统版本号
	 * 
	 * @return
	 */
	public VersionInfo searchVersion() {
		VersionInfo vsersion = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DOWNLOADURL,ID,VERSIONCODE,VERSIONDESC,VERSIONNAME,VERSIONSIZE FROM TBL_APP_VERSION");
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				vsersion = new VersionInfo();
				vsersion.setDownloadUrl(rs.getString("DOWNLOADURL"));
				vsersion.setId(rs.getString("ID"));
				vsersion.setVersionCode(rs.getString("VERSIONCODE"));
				vsersion.setVersionDesc(rs.getString("VERSIONDESC"));
				vsersion.setVersionName(rs.getString("VERSIONNAME"));
				vsersion.setVersionSize(rs.getString("VERSIONSIZE"));
				return vsersion;
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

		return vsersion;
	}

	public List<ZhandianBean> searchZhandianListByLocation(String userid,
			String longitude, String latitude) {
		List<ZhandianBean> zhandianList = new ArrayList<ZhandianBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  Z.ID,Z.JZNAME,Z.JZCODE,to_number(nvl(Z.longitude,'0')) AS LONGITUDE,to_number(nvl(Z.latitude,'0')) AS LATITUDE FROM ZHANDIAN Z,TBL_APP_USER_BD T WHERE Z.id = T.ZDID");
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				ZhandianBean zhandian = new ZhandianBean();
				zhandian.setID(rs.getString("ID"));
				zhandian.setJZNAME(rs.getString("JZNAME"));
				zhandian.setJZCODE(rs.getString("JZCODE"));
				zhandian.setLONGITUDE(rs.getString("LONGITUDE"));
				zhandian.setLATITUDE(rs.getString("LATITUDE"));
				zhandian.setHASCHILD("true");
				zhandianList.add(zhandian);
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

		return zhandianList;
	}

	public List<ZhandianBean> searchZdByUserid(String userid, String zdid) {
		List<ZhandianBean> zdList = new ArrayList<ZhandianBean>();
		String sql = "SELECT DISTINCT Z.JZNAME,Z.LONGITUDE,Z.LATITUDE FROM ZHANDIAN Z,TBL_APP_USER_BD C WHERE Z.ID ='"
				+ zdid + "'  AND C.USERID = '" + userid + "'";
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				ZhandianBean zd = new ZhandianBean();
				zd.setLONGITUDE(rs.getString("LONGITUDE"));
				zd.setLATITUDE(rs.getString("LATITUDE"));
				zd.setJZNAME(rs.getString("JZNAME"));
				zdList.add(zd);
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
		return zdList;
	}

	public String searchZDID2(String userid,String zdname) {
		ZhandianBean zd = new ZhandianBean();
		String zdid = null;
		String sql = "SELECT DISTINCT Z.ID FROM ( SELECT P.AGCODE FROM ACCOUNT A , PER_AREA P WHERE A.ACCOUNTID = P.ACCOUNTID AND A.ACCOUNTID ='"+userid+"' ) AF LEFT JOIN ZHANDIAN Z ON AF.AGCODE = Z.XIAN where Z.JZNAME = '"+zdname+"'";
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			try {
				while (rs.next()) {
					try {
						zdid = rs.getString("ID");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return zdid;
	}
/**
 * 根据名字查id
 * @param createdate
 * @param thisdate
 * @return
 */
	public ZhandianBean searchZdByUserid2(String userid, String zdid) {
		ZhandianBean zd = new ZhandianBean();
		String sql = "SELECT DISTINCT Z.JZNAME,Z.LONGITUDE,Z.LATITUDE FROM ZHANDIAN Z,TBL_APP_USER_BD C WHERE Z.ID ='"
				+ zdid + "'  AND C.USERID = '" + userid + "'";
		log.info(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				zd.setLONGITUDE(rs.getString("LONGITUDE"));
				zd.setLATITUDE(rs.getString("LATITUDE"));
				zd.setJZNAME(rs.getString("JZNAME"));
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
		return zd;
	}
	// 直接传入电表最后的抄表日期和当前日期对比，判断是否可以插入抄表数据
	@SuppressWarnings({ "unused", "null" })
	public boolean XiangChaYiYue_new(String createdate, String thisdate) {
		boolean dt = false; // 要返回的值
		// 获取服务器当前时间
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM");
		String dqtime = df.format(day);
		String createtime = date.format(day);
		if (createdate.equals(createtime)) {
			// 当上次抄表时间为空时可以直接添加抄表数据
			if (thisdate == null && thisdate.equals("")) {
				dt = true;
				System.out.println(dt);
			} else { // 上次抄表时间不为空时，与当前系统时间对比，如果月份相同则不能添加

				dt = compare_monuthAndDay(thisdate, dqtime);
				/**
				 * name : fuxiuli 注意：这里只比较的月份，没有考虑年份因素， 故如果上次抄表时间与系统时间正好相隔一年
				 * 例：2017-12-01 和 2018-12-01 此方法结果也为false
				 */
				System.out.println(dt);
			}
		} else if(createdate==null && createdate.equals("")){
			dt = false;
		}else {
			dt = true;
		}
		return dt;

	}

	// 根据电表ID查询该电表最后的抄表日期和当前日期对比，判断是否可以插入抄表数据
	public boolean XiangChaYiYue(String dbid) {
		boolean dt = false; // 要返回的值

		// 获取服务器当前时间
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dqtime = df.format(day);

		// 将电表ID插入SQL返回该电表
		ArrayList<AmmertdegreeBean> al = null;
		String sql = "SELECT ENDTIME FROM (SELECT ENDTIME FROM ELECTRICFEES WHERE DIANBIAOID = "
				+ dbid + " ORDER BY ENDTIME DESC) WHERE ROWNUM = 1";
		System.out.println(sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			al = new ArrayList<AmmertdegreeBean>();
			while (rs.next()) {
				AmmertdegreeBean a = new AmmertdegreeBean();
				a.setENDTIME(rs.getString("ENDTIME"));
				al.add(a);
			}
			// 上次抄表时间
			String sctime = al.get(0).getENDTIME();
			// 当上次抄表时间为空时可以直接添加抄表数据
			if (sctime == null && sctime.equals("")) {
				dt = true;
				System.out.println(dt);
			} else { // 上次抄表时间不为空时，与当前系统时间对比，如果月份相同则不能添加

				dt = compare_monuthAndDay(sctime, dqtime);
				/**
				 * name : fuxiuli 注意：这里只比较的月份，没有考虑年份因素， 故如果上次抄表时间与系统时间正好相隔一年
				 * 例：2017-12-01 和 2018-12-01 此方法结果也为false
				 */
				System.out.println(dt);
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
		return dt;
	}

	// 月份对比
	public boolean compare_monuthAndDay(String date1, String date2) {
		int month1 = Integer.parseInt(date1.substring(5, 7));
		int month2 = Integer.parseInt(date2.substring(5, 7));
		if (month1 == month2) {
			return false;
		} else {
			return true;
		}
	}

	public boolean saveLoginLog(String ip, String loginname, String password,
			boolean flag, String message, String jsonContent) {
		boolean dt = false; // 要返回的值

		// 获取服务器当前时间
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dqtime = df.format(day);

		// 将电表ID插入SQL返回该电表
		String sql = "INSERT INTO TBL_APP_USER_LOG(CREATE_DATE,CREATE_IP,LOGIN_NAME,PASSWORD,FLAG,MESSAGE,JSON_STRING)VALUES(to_date('"
				+ dqtime
				+ "','yyyy-mm-dd hh24:mi:ss'),'"
				+ ip
				+ "','"
				+ loginname
				+ "','"
				+ password
				+ "','"
				+ flag
				+ "','"
				+ message
				+ "','" + jsonContent + "')";
		System.out.println(sql);
		DataBase db = new DataBase();
		try {
			db.connectDb();
			db.setAutoCommit(false);
			int i = db.update(sql);
			db.commit();
			if (i > 0) {
				dt = true;
			}
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return dt;
	}

	//将手机抄表数据添加到ELECTRICFEES_C表
	public int addELECTRICFEES_C(String EAO_C,String ELECTRICFEE_ID,String STARTTIME_C,String ENDTIME_C,String DIANBIAOID,String SQDS_C,String BQDS_C,String DIANLIANG_C,
			String DBIMAGEPATH1,String DBIMAGEPATH2,String DBIMAGEPATH3,String ROOMJFXX,String ROOMDBCH,String DAYNUM_C,String CREATEDATE,String BEILV,String ENTRYTIMEOLD) {
		String ZHANDIANID = SelectZHANDIANID(DIANBIAOID);	//根据电表ID获取站点ID
		String DIANBIAOBM = SelectDIANBIAOBM(DIANBIAOID);	//根据电表ID获取电表编码
		//将报账数据和获取的站点ID和电表ID填入数据库
		String sql = "INSERT INTO ELECTRICFEES_C" +
				"(EAO_C,ELECTRICFEE_ID,STARTTIME_C,ENDTIME_C,DIANBIAOID,SQDS_C,BQDS_C,DIANLIANG_C,DBIMAGEPATH1,DBIMAGEPATH2,DBIMAGEPATH3,ROOMJFXX,ROOMDBCH,DAYNUM_C,CREATEDATE,BEILV,ENTRYTIMEOLD,ZHANDIANID,DIANBIAOBM)" +
				"values(" +
				"'"+EAO_C+"','"+ELECTRICFEE_ID+"','"+STARTTIME_C+"','"+ENDTIME_C+"','"+DIANBIAOID+"','"+SQDS_C+"','"+BQDS_C+"','"+DIANLIANG_C+"'," +
				"'"+DBIMAGEPATH1+"','"+DBIMAGEPATH2+"','"+DBIMAGEPATH3+"','"+ROOMJFXX+"','"+ROOMDBCH+"','"+DAYNUM_C+"','"+CREATEDATE+"','"+BEILV+"','"+ENTRYTIMEOLD+"','"+ZHANDIANID+"','"+DIANBIAOBM+"')";		
		DataBase db = new DataBase();
		int rs = 0;
		try {
			db.connectDb();
			System.out.println("将手机抄表数据添加到ELECTRICFEES_C表"+sql);
			rs = db.update(sql);
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return rs;
	}
	//根据电表ID查询电表编码给addELECTRICFEES_C()方法使用
	public String SelectDIANBIAOBM(String DIANBIAOID){
		String DIANBIAOBM = "";
		String sql = "SELECT DBBM,ZDID FROM  DIANBIAO WHERE ID = '"+DIANBIAOID+"'";
		System.out.println("根据电表ID查询电表编码sql："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				DIANBIAOBM = rs.getString("DBBM");
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
		System.out.println("获取电表编码为："+DIANBIAOBM);
		return DIANBIAOBM;
	}
	//根据电表ID查询站点ID给addELECTRICFEES_C()方法使用
	public String SelectZHANDIANID(String DIANBIAOID){
		String ZHANDIANID = "";
		String sql = "SELECT ZDID FROM  DIANBIAO WHERE ID = '"+DIANBIAOID+"'";
		System.out.println("根据电表ID查询电表编码sql："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				ZHANDIANID = rs.getString("ZDID");
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
		System.out.println("获取站点ID为："+ZHANDIANID);
		return ZHANDIANID;
	}
}
