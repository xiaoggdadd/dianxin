package com.noki.biaogan;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.jfree.util.Log;

import com.noki.biaogan.model.AmmertdegreeBean;
import com.noki.biaogan.model.BiaoganBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.Query;
import com.noki.zwhd.model.DianbiaoBean;
import com.noki.zwhd.model.ZhandianBean;

public class BiaoganManage {
	private static org.apache.commons.logging.Log log = LogFactory
			.getLog(BiaoganManage.class.getName());

	private int allPage;

	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}

	public int getAllPage() {
		return this.allPage;
	}

	public ArrayList<BiaoganBean> searchBgList(int start, String loginName,
			String loginId, String whereStr) {
		System.out.println("------------前台进入后台");
		ArrayList<BiaoganBean> biaoganList = new ArrayList<BiaoganBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM  tbl_tt_biaogan_lishi a,dianbiao b,zhandian c where a.dbid = b.dbid and b.zdid = c.id "
				+ whereStr);
		log.info("[标杆查询]" + sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				BiaoganBean biaogan = new BiaoganBean();
				String JZID = rs.getString("ID") == null
						|| rs.getString("ID").equals("null") ? "" : rs
						.getString("ID");
				String JZCODE = rs.getString("JZCODE") == null
						|| rs.getString("JZCODE").equals("null") ? "" : rs
						.getString("JZCODE");
				String JZNAME = rs.getString("JZNAME") == null
						|| rs.getString("JZNAME").equals("null") ? "" : rs
						.getString("JZNAME");
				String DBID = rs.getString("DBID") == null
						|| rs.getString("DBID").equals("null") ? "" : rs
						.getString("DBID");
				String DBBM = rs.getString("DBBM") == null
						|| rs.getString("DBBM").equals("null") ? "" : rs
						.getString("DBBM");
				String biaoganvalue = rs.getString("biaoganvalue") == null
						|| rs.getString("biaoganvalue").equals("null") ? ""
						: rs.getString("biaoganvalue");
				String YEARMONTH = rs.getString("YEARMONTH") == null
						|| rs.getString("YEARMONTH").equals("null") ? "" : rs
						.getString("YEARMONTH");

				String ZLFH = rs.getString("ZLFH") == null
						|| rs.getString("ZLFH").equals("null") ? "" : rs
						.getString("ZLFH");
				String YDXS = rs.getString("YDXS") == null
						|| rs.getString("YDXS").equals("null") ? "" : rs
						.getString("YDXS");
				String KTXS = rs.getString("KTXS") == null
						|| rs.getString("KTXS").equals("null") ? "" : rs
						.getString("KTXS");
				
				biaogan.setJZID(JZID);
				biaogan.setJZCODE(JZCODE);
				biaogan.setJZNAME(JZNAME);
				biaogan.setDBID(DBID);
				biaogan.setDBBM(DBBM);
				biaogan.setBIAOGANVALUE(biaoganvalue);
				biaogan.setYEARMONTH(YEARMONTH);
				biaogan.setZLFH(ZLFH);
				biaogan.setYDXS(YDXS);
				biaogan.setKTXS(KTXS);
				System.out.println("================"+biaogan.getDBID());
				biaoganList.add(biaogan);
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
		return biaoganList;
	}
	
	public ArrayList<BiaoganBean> searchBgList2(int start, String loginName,
			String loginId, String whereStr) {
		System.out.println("------------前台进入后台2");
		ArrayList<BiaoganBean> biaoganList = new ArrayList<BiaoganBean>();
		StringBuffer sql = new StringBuffer();
		StringBuffer s3 = new StringBuffer();
//		sql.append("SELECT * FROM  tbl_tt_biaogan_lishi a,dianbiao b,zhandian c where a.dbid = b.dbid and b.zdid = c.id "
//				+ whereStr);
		sql.append("SELECT a.id,c.JZNAME,a.YEARMONTH,c.JZCODE,b.DBID,a.DBBM,a.biaoganvalue,a.ZLFH,a.YDXS,a.KTXS FROM  tbl_tt_biaogan_lishi a,dianbiao b,zhandian c where a.dbid = b.dbid and b.zdid = c.id "
				+ whereStr);
		log.info("[标杆查询]" + sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			
			   s3.append("select count(*)  from (" + sql + ")");
               System.out.println("站点查询分页"+s3); 
               ResultSet rs3 = null;
               rs3=db.queryAll(s3.toString());
               if(rs3.next()){
               	this.setAllPage((rs3.getInt(1)+9)/10);
               }
               NPageBean nbean = new NPageBean();
	              rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
//	              Query query = new Query();
//	              biaoganList = query.query(rs);
              
			while (rs.next()) {
				BiaoganBean biaogan = new BiaoganBean();
				String JZID = rs.getString("ID") == null
						|| rs.getString("ID").equals("null") ? "" : rs
						.getString("ID");
				String JZCODE = rs.getString("JZCODE") == null
						|| rs.getString("JZCODE").equals("null") ? "" : rs
						.getString("JZCODE");
				String JZNAME = rs.getString("JZNAME") == null
						|| rs.getString("JZNAME").equals("null") ? "" : rs
						.getString("JZNAME");
				String DBID = rs.getString("DBID") == null
						|| rs.getString("DBID").equals("null") ? "" : rs
						.getString("DBID");
				String DBBM = rs.getString("DBBM") == null
						|| rs.getString("DBBM").equals("null") ? "" : rs
						.getString("DBBM");
				String biaoganvalue = rs.getString("biaoganvalue") == null
						|| rs.getString("biaoganvalue").equals("null") ? ""
						: rs.getString("biaoganvalue");
				String YEARMONTH = rs.getString("YEARMONTH") == null
						|| rs.getString("YEARMONTH").equals("null") ? "" : rs
						.getString("YEARMONTH");

				String ZLFH = rs.getString("ZLFH") == null
						|| rs.getString("ZLFH").equals("null") ? "" : rs
						.getString("ZLFH");
				String YDXS = rs.getString("YDXS") == null
						|| rs.getString("YDXS").equals("null") ? "" : rs
						.getString("YDXS");
				String KTXS = rs.getString("KTXS") == null
						|| rs.getString("KTXS").equals("null") ? "" : rs
						.getString("KTXS");
				
				biaogan.setJZID(JZID);
				biaogan.setJZCODE(JZCODE);
				biaogan.setJZNAME(JZNAME);
				biaogan.setDBID(DBID);
				biaogan.setDBBM(DBBM);
				biaogan.setBIAOGANVALUE(biaoganvalue);
				biaogan.setYEARMONTH(YEARMONTH);
				biaogan.setZLFH(ZLFH);
				biaogan.setYDXS(YDXS);
				biaogan.setKTXS(KTXS);
				System.out.println("================"+biaogan.getDBID());
				biaoganList.add(biaogan);
			}
			 rs3.close();		
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
		return biaoganList;
	}

	// 查找站点
	public ArrayList<BiaoganBean> getPageData(int start, String loginName,
			String loginId, String whereStr) {
		ArrayList<BiaoganBean> biaoganList = new ArrayList<BiaoganBean>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		StringBuffer strall = new StringBuffer();
		strall.append("select z.id from zhandian z where 1=1 " + whereStr);
		String fzzdstr = "";
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			System.out.println("负责站点" + fzzdstr);
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("SELECT z.ID,z.JZCODE,z.JZNAME,b.DBID,b.DBBM,c.biaoganvalue,c.YEARMONTH,c.ZLFH,c.YDXS,c.KTXS"
					+ " FROM zhandian z,dianbiao b left join tbl_tt_biaogan_lishi c on b.dbid = c.dbid"
					+ " WHERE b.ZDID = z.ID "
					+ whereStr
					+ " and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ") ORDER BY c.YEARMONTH desc");
			System.out.println("站点查询" + s2);

			s3.append("select count(*)  from (" + s2 + ")");
			System.out.println("站点查询分页" + s3);
			ResultSet rs3 = null;
			rs3 = db.queryAll(s3.toString());
			if (rs3.next()) {
				this.setAllPage((rs3.getInt(1) + 14) / 15);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
			while (rs.next()) {
				BiaoganBean biaogan = new BiaoganBean();
				String JZID = rs.getString("ID") == null
						|| rs.getString("ID").equals("null") ? "" : rs
						.getString("ID");
				String JZCODE = rs.getString("JZCODE") == null
						|| rs.getString("JZCODE").equals("null") ? "" : rs
						.getString("JZCODE");
				String JZNAME = rs.getString("JZNAME") == null
						|| rs.getString("JZNAME").equals("null") ? "" : rs
						.getString("JZNAME");
				String DBID = rs.getString("DBID") == null
						|| rs.getString("DBID").equals("null") ? "" : rs
						.getString("DBID");
				String DBBM = rs.getString("DBBM") == null
						|| rs.getString("DBBM").equals("null") ? "" : rs
						.getString("DBBM");
				String biaoganvalue = rs.getString("biaoganvalue") == null
						|| rs.getString("biaoganvalue").equals("null") ? ""
						: rs.getString("biaoganvalue");
				String YEARMONTH = rs.getString("YEARMONTH") == null
						|| rs.getString("YEARMONTH").equals("null") ? "" : rs
						.getString("YEARMONTH");
				String ZLFH = rs.getString("ZLFH") == null
						|| rs.getString("ZLFH").equals("null") ? "" : rs
						.getString("ZLFH");
				String YDXS = rs.getString("YDXS") == null
						|| rs.getString("YDXS").equals("null") ? "" : rs
						.getString("YDXS");
				String KTXS = rs.getString("KTXS") == null
						|| rs.getString("KTXS").equals("null") ? "" : rs
						.getString("KTXS");

				biaogan.setJZID(JZID);
				biaogan.setJZCODE(JZCODE);
				biaogan.setJZNAME(JZNAME);
				biaogan.setDBID(DBID);
				biaogan.setDBBM(DBBM);
				biaogan.setBIAOGANVALUE(biaoganvalue);
				biaogan.setYEARMONTH(YEARMONTH);
				biaogan.setZLFH(ZLFH);
				biaogan.setYDXS(YDXS);
				biaogan.setKTXS(KTXS);
				biaoganList.add(biaogan);

			}

			rs3.close();
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

		return biaoganList;
	}

	// 负责站点条件
	private String getFuzeZdid(DataBase db, String loginid) throws DbException,
			SQLException {
		ResultSet rs = null;

		rs = db.queryAll("select begincode,endcode from per_zhandian where accountid='"
				+ loginid
				+ "' and begincode is not null and endcode is not null");
		String cxtj = new String("");
		while (rs.next()) {
			cxtj = cxtj + " or (zdcode>='" + rs.getString(1)
					+ "' and zdcode <='" + rs.getString(2) + "')";
		}
		System.out.println("负责站点条件：" + cxtj);
		if (rs != null) {
			rs.close();
		}
		if (db != null) {
			db.close();
		}

		return cxtj.toString();
	}

	public List<BiaoganBean> searchBiaoganChartByZdid(String zdid, String year) {
		List<BiaoganBean> biaoganList = new ArrayList<BiaoganBean>();
		for (int i = 1; i <= 12; i++) {
			BiaoganBean biaogan = new BiaoganBean();
			biaogan.setBIAOGANVALUE("0");
			String month = "";
			if (i < 10) {
				month = "0" + i;
			}else{
				month = ""+i;
			}
			biaogan.setYEARMONTH(year + "-" + month);
			biaoganList.add(biaogan);
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select BIAOGANVALUE,YEARMONTH from tbl_tt_biaogan_lishi where zdbm = (select jzcode from zhandian where jzcode = '"
				+ zdid + "') and yearmonth like '" + year + "%'");
		System.out.println("标杆图表sql:"+sql.toString());
		Log.info("[标杆图表]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		if (sql.length() > 0) {
			try {
				db.connectDb();
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					String BIAOGANVALUE = rs.getString("BIAOGANVALUE");
					String yearmonth = rs.getString("YEARMONTH");
					for (int i = 0; i < biaoganList.size(); i++) {
						BiaoganBean _biaogan = biaoganList.get(i);
						String _yearmonth = _biaogan.getYEARMONTH();
						@SuppressWarnings("unused")
						String _BIAOGANVALUE = _biaogan.getBIAOGANVALUE();
						if (_yearmonth.equals(yearmonth)) {
							biaoganList.remove(_biaogan);
							_biaogan.setBIAOGANVALUE(BIAOGANVALUE);
							biaoganList.add(_biaogan);
						}
					} 
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

		return biaoganList;
	}

	/**
	 * ls
	 * 获取标杆电量天数平均值
	 * @throws DbException 
	 * @throws SQLException 
	 */
	public int searchLastDbdlbgdlpjz(String dbid) throws DbException, SQLException {
		StringBuffer sql = new StringBuffer();
		int mam=0;
		int i=0;
		sql.append("SELECT C.BGDL,C.DAYNUM FROM ELECTRICFEES c where C.STATE='2' and C.DIANBIAOID='"+dbid+"'");
		log.info("[整体的bgdl与天数]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		db.connectDb();
		rs = db.queryAll(sql.toString());
		while (rs.next()) {
			if(rs.getString("BGDL")!=null||rs.getString("BGDL")!=""){
				mam=(Integer.parseInt(rs.getString("BGDL"))/Integer.parseInt(rs.getString("DAYNUM")))+mam;
				i++;
			}
		}
		if(mam!=0||i!=0){
			return mam/i;
		}else{
			return 1;
		}
	}
	/**
	 * 获取上次抄表电量与状态
	 * @param dbid
	 * @param yearmonth
	 * @return
	 */
	public AmmertdegreeBean searchLastDbdl(String dbid, String yearmonth) {
		StringBuffer sql = new StringBuffer();
		AmmertdegreeBean bean = new AmmertdegreeBean();
		sql.append("SELECT E.CREATEDATE,E.BQDS_C,E.ENDTIME_C FROM ELECTRICFEES E ,(SELECT MAX(E.ENDTIME_C)ET,E.DIANBIAOID FROM ELECTRICFEES E WHERE  E.STATE='2'AND DIANBIAOID ='"+dbid+"' GROUP BY E.DIANBIAOID)M WHERE E.DIANBIAOID=M.DIANBIAOID AND E.ENDTIME_C=M.ET");
		log.info("[获取上次抄表读数和上次抄表时间]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				
				bean.setCREATEDATE(rs.getString("CREATEDATE"));
				bean.setTHISDEGREE(rs.getString("BQDS_C"));
				bean.setTHISDATETIME(rs.getString("ENDTIME_C"));
				
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
		return bean;
	}
/**
 * 查询 
 * @param shuo
 * @return
 */
	
	public DianbiaoBean searchLastDbdldianbiao(String dbid, String yearmonth) {
		StringBuffer sql = new StringBuffer();
		DianbiaoBean bean = new DianbiaoBean();
		sql.append("SELECT CSDS,CSSYTIME from dianbiao where id='"+dbid+"' ");
		log.info("[获取初始电表读数和时间]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				bean.setCSDS(rs.getString("CSDS"));
				bean.setCSSYTIME(rs.getString("CSSYTIME"));
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
		return  bean;
	}
	
	
	
	
	///////
	public List<ZhandianBean> searchZdListByXian(String xian) {
		List<ZhandianBean> zhandians = new ArrayList<ZhandianBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select id,jzname,jzcode from zhandian where xian = '" + xian + "'");
		log.info("[根据县获取站点列表]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				ZhandianBean zhandian = new ZhandianBean();
				zhandian.setID(rs.getString("id"));
				zhandian.setJZNAME(rs.getString("jzname"));
				zhandian.setJZCODE(rs.getString("jzcode"));
				zhandians.add(zhandian);
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
		return zhandians;
	}

	public List<DianbiaoBean> searchDbListByZd(String zdid) {
		List<DianbiaoBean> dianbiaos = new ArrayList<DianbiaoBean>();
		StringBuffer sql = new StringBuffer();
		sql.append("select DBID,DBBM from dianbiao where ZDID = '" + zdid + "'");
		log.info("[根据县获取站点列表]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				DianbiaoBean dianbiao = new DianbiaoBean();
				dianbiao.setDBID(rs.getString("DBID"));
				dianbiao.setDBBM(rs.getString("DBBM"));
				dianbiaos.add(dianbiao);
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
		return dianbiaos;
	}

	public String searchLastCbDate(String dbid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select max(THISDATETIME) as THISDATETIME from AMMETERDEGREES where AMMETERID_FK = '"
				+ dbid + "'");
		log.info("[根据县获取站点列表]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				return rs.getString("THISDATETIME");
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
		return null;
	}

	public ArrayList<BiaoganBean> getBiaoganListForExcl(String shi,
			String xian, String yearmonth, String gjz) {
		ArrayList<BiaoganBean> biaoganList = new ArrayList<BiaoganBean>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT z.ID,z.JZCODE,z.JZNAME,b.DBID,b.DBBM,c.biaoganvalue,c.YEARMONTH,c.ZLFH,c.YDXS,c.KTXS,c.ZQ"
				+ " FROM zhandian z,dianbiao b left join tbl_tt_biaogan_lishi c on b.dbid = c.dbid"
				+ " WHERE b.ZDID = z.ID");

		if (xian == null || xian.equals("0")) {
			if (shi == null || shi.equals("0")) {

			} else {
				sql.append(" and z.shi = '" + shi + "'");
			}
		} else {
			sql.append(" and z.xian = '" + xian + "'");
		}

		if (yearmonth == null || yearmonth.equals("")) {

		} else {
			sql.append(" and c.YEARMONTH = '" + yearmonth + "'");
		}

		if (gjz == null || gjz.equals("")) {

		} else {
			sql.append(" and (z.JZNAME = '" + gjz + "' or z.JZCODE = '" + gjz
					+ "')");
		}

		sql.append(" ORDER BY c.YEARMONTH desc");
		log.info("[标杆导出]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				BiaoganBean biaogan = new BiaoganBean();
				String JZID = rs.getString("ID") == null
						|| rs.getString("ID").equals("null") ? "" : rs
						.getString("ID");
				String JZCODE = rs.getString("JZCODE") == null
						|| rs.getString("JZCODE").equals("null") ? "" : rs
						.getString("JZCODE");
				String JZNAME = rs.getString("JZNAME") == null
						|| rs.getString("JZNAME").equals("null") ? "" : rs
						.getString("JZNAME");
				String DBID = rs.getString("DBID") == null
						|| rs.getString("DBID").equals("null") ? "" : rs
						.getString("DBID");
				String DBBM = rs.getString("DBBM") == null
						|| rs.getString("DBBM").equals("null") ? "" : rs
						.getString("DBBM");
				String biaoganvalue = rs.getString("biaoganvalue") == null
						|| rs.getString("biaoganvalue").equals("null") ? ""
						: rs.getString("biaoganvalue");
				String YEARMONTH = rs.getString("YEARMONTH") == null
						|| rs.getString("YEARMONTH").equals("null") ? "" : rs
						.getString("YEARMONTH");

				String ZLFH = rs.getString("ZLFH") == null
						|| rs.getString("ZLFH").equals("null") ? "" : rs
						.getString("ZLFH");
				String YDXS = rs.getString("YDXS") == null
						|| rs.getString("YDXS").equals("null") ? "" : rs
						.getString("YDXS");
				String KTXS = rs.getString("KTXS") == null
						|| rs.getString("KTXS").equals("null") ? "" : rs
						.getString("KTXS");

				String ZQ = rs.getString("ZQ") == null
						|| rs.getString("ZQ").equals("null") ? "" : rs
						.getString("ZQ");

				biaogan.setJZID(JZID);
				biaogan.setJZCODE(JZCODE);
				biaogan.setJZNAME(JZNAME);
				biaogan.setDBID(DBID);
				biaogan.setDBBM(DBBM);
				biaogan.setBIAOGANVALUE(biaoganvalue);
				biaogan.setYEARMONTH(YEARMONTH);
				biaogan.setZLFH(ZLFH);
				biaogan.setYDXS(YDXS);
				biaogan.setKTXS(KTXS);
				biaogan.setZQ(ZQ);
				biaoganList.add(biaogan);
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
		return biaoganList;
	}

	public List<Object> doSqlSelect(String beanType, String sql) {
		ArrayList<Object> objectList = new ArrayList<Object>();
		log.info("[Excel导出SQL]" + sql);
		sql=indextof(sql);
		DataBase db = new DataBase();

		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			log.info("[SQL列总数]" + count);
			List<String> columnNames = new ArrayList<String>();
			for (int i = 1; i < count + 1; i++) {
				String element = rsmd.getColumnLabel(i);
				log.info("[列名]" + element);
				columnNames.add(element);
			}
			log.info("[SQL列总数矫正]" + columnNames.size());

			while (rs.next()) {
				Class objClass = Class.forName(beanType);
				Object obj = objClass.newInstance();
				for (int i = 0; i < columnNames.size(); i++) {
					String name = columnNames.get(i);
					String value = rs.getString(name);
					Method m = null;
					try {
						m = obj.getClass()
								.getMethod("set" + name, String.class);
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					log.info("set" + name + " " + value);
					m.invoke(obj, value);
				}
				objectList.add(obj);
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
		return objectList;
	}

	public String searchZdl(String dbid, String yearmonth) {
		StringBuffer sql_s_zlfh = new StringBuffer();
		sql_s_zlfh.append("SELECT ZDL FROM FTBL WHERE YEARMONTH = '"
				+ yearmonth + "' AND DBID = '" + dbid + "' ");
		log.info("[直流负荷查询]" + sql_s_zlfh);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql_s_zlfh.toString());
			while (rs.next()) {
				return rs.getString("ZDL");
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
		return null;
	}

	public String searchDj(String dbid, String yearmonth) {
		StringBuffer sql_s_dj = new StringBuffer();
		sql_s_dj.append("select DANJIA from dianjia where DBID = '" + dbid
				+ "' and YEARMONTH = '" + yearmonth + "' ");
		log.info("[单价查询]" + sql_s_dj);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql_s_dj.toString());
			while (rs.next()) {
				return rs.getString("DANJIA");
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
		return null;
	}

	public com.noki.biaogan.model.DianbiaoBean searchDbByDbid(
			String ammeterid_FK) {
		StringBuffer sql_s_dj = new StringBuffer();
		sql_s_dj.append("select DBBM,DBID,ZDID from dianbiao where dbid = '" + ammeterid_FK
				+ "'");
		log.info("[电表查询]" + sql_s_dj);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql_s_dj.toString());
			while (rs.next()) {
				com.noki.biaogan.model.DianbiaoBean bean = new com.noki.biaogan.model.DianbiaoBean();
				bean.setDBBM(rs.getString("DBBM"));
				bean.setDBID(rs.getString("DBID"));
				bean.setZDID(rs.getString("ZDID"));
				return bean;
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
		return null;
	}

	public com.noki.biaogan.model.ZhandianBean searchZdByZdid(String zdid) {
		StringBuffer sql_s_dj = new StringBuffer();
		sql_s_dj.append("select ID,JZCODE from zhandian where id = '"+zdid+"'");
		log.info("[站点查询]" + sql_s_dj);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql_s_dj.toString());
			while (rs.next()) {
				com.noki.biaogan.model.ZhandianBean bean = new com.noki.biaogan.model.ZhandianBean();
				bean.setID(rs.getString("ID"));
				bean.setJZCODE(rs.getString("JZCODE"));
				return bean;
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
		return null;
	}
//电表的第二次查询只进行这一次的查询
	public List<AmmertdegreeBean> searchLastDbdl(String dbId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT BEILV,DANJIAOLD,DLONGITUDE,DLATITUDE,ELECTRIC_SUPPLY_WAY FROM DIANBIAO WHERE ID = '"
				+ dbId+"'");
		log.info("[电表的基础数据]" + sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		List<AmmertdegreeBean> bean1 = new ArrayList<AmmertdegreeBean>();
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				AmmertdegreeBean bean = new AmmertdegreeBean();
				bean.setBEILV(rs.getString("BEILV"));
				bean.setDANJIAOLD(rs.getString("DANJIAOLD"));
				bean.setDlongitude(rs.getString("DLONGITUDE"));
				bean.setDlatitude(rs.getString("DLATITUDE"));
				bean.setELECTRICCURRENTTYPE(rs.getString("ELECTRIC_SUPPLY_WAY"));
				bean1.add(bean);
				return bean1;
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
		return bean1;
	}
	///
	public  String indextof (String sql){
		System.out.println("替换前"+sql);
		       sql= sql.replace("‘", "'");
		       System.out.println("替换后"+sql);
		  return sql;
	}	
		
			
		

}
