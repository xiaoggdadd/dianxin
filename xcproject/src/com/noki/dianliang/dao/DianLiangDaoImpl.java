package com.noki.dianliang.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.LogFactory;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.dianliang.model.AmmeterdegreesBean;
import com.noki.dianliang.model.DianbiaoBean;
import com.noki.dianliang.model.ElectricfeesBean;
import com.noki.dianliang.model.TopBaozhangBean;
import com.noki.dianliang.model.ZhandianBean;

public class DianLiangDaoImpl {
	private static org.apache.commons.logging.Log log = LogFactory
			.getLog(DianLiangDaoImpl.class.getName());

	public ArrayList<AmmeterdegreesBean> searchDianLiangDaoru(int start,
			String loginName, String loginId, String whereStr) {

		ArrayList<AmmeterdegreesBean> ammeterdegreesList = new ArrayList<AmmeterdegreesBean>();
		StringBuffer sql_s_ammerts = new StringBuffer();
		sql_s_ammerts
				.append("select "
						+ "a.AMMETERDEGREEID,a.AMMETERID_FK,a.LASTDEGREE,a.THISDEGREE,a.LASTDATE,a.THISDATE,a.LASTDATETIME,a.THISDATETIME,a.BLHDL,"
						+ "b.ELECTRICFEE_ID,b.ACTUALPAY,b.NOTETYPEID,b.CB_ALERT,b.BG,b.LINELOSS,b.CREATEDATE,b.UNITPRICE,b.YDDF,b.ACTUALPAY_L,b.ACTUALPAY_Y,b.ACTUALPAY_D,b.ACTUALPAY_O,b.FTBL_L,b.FTBL_Y,b.FTBL_D,b.FTBL_O,to_char(b.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,"
						+ "c.DBBM,c.DBID,c.DBZBDYHH,"
						+ "d.SHENG,d.SHI,d.XIAN,d.JZNAME,d.JZCODE "
						+ "from ammeterdegrees a,electricfees b,dianbiao c,zhandian d "
						+ "where b.AMMETERDEGREEID_FK = a.AMMETERDEGREEID and a.AMMETERID_FK = c.dbid and c.zdid = d.id");
		sql_s_ammerts.append(whereStr);
		log.info("[电量批量导入查询]" + sql_s_ammerts);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql_s_ammerts.toString());
			while (rs.next()) {
				AmmeterdegreesBean ammeterdegrees = new AmmeterdegreesBean();
				ammeterdegrees.setAMMETERDEGREEID(rs.getString("AMMETERDEGREEID"));
				ammeterdegrees.setAMMETERID_FK(rs.getString("AMMETERID_FK"));
				ammeterdegrees.setLASTDEGREE(rs.getString("LASTDEGREE"));
				ammeterdegrees.setTHISDEGREE(rs.getString("THISDEGREE"));
				ammeterdegrees.setLASTDATE(rs.getString("LASTDATE"));
				ammeterdegrees.setTHISDATE(rs.getString("THISDATE"));
				ammeterdegrees.setLASTDATETIME(rs.getString("LASTDATETIME"));
				ammeterdegrees.setTHISDATETIME(rs.getString("THISDATETIME"));
				ammeterdegrees.setBLHDL(rs.getString("BLHDL"));
				
				ElectricfeesBean electricfees = new ElectricfeesBean();
				electricfees.setELECTRICFEE_ID(rs.getString("ELECTRICFEE_ID"));
				electricfees.setACTUALPAY(rs.getString("ACTUALPAY"));
				electricfees.setNOTETYPEID(rs.getString("NOTETYPEID"));
				electricfees.setCB_ALERT(rs.getString("CB_ALERT"));
				electricfees.setYDDF(rs.getString("YDDF"));
				electricfees.setACTUALPAY_L(rs.getString("ACTUALPAY_L"));
				electricfees.setACTUALPAY_Y(rs.getString("ACTUALPAY_Y"));
				electricfees.setACTUALPAY_D(rs.getString("ACTUALPAY_D"));
				electricfees.setACTUALPAY_O(rs.getString("ACTUALPAY_O"));
				electricfees.setFTBL_L(rs.getString("FTBL_L"));
				electricfees.setFTBL_Y(rs.getString("FTBL_Y"));
				electricfees.setFTBL_D(rs.getString("FTBL_D"));
				electricfees.setFTBL_O(rs.getString("FTBL_O"));
				electricfees.setBG(rs.getString("BG"));
				electricfees.setLINELOSS(rs.getString("LINELOSS"));
				electricfees.setCREATEDATE(rs.getString("CREATEDATE"));
				electricfees.setUNITPRICE(rs.getString("UNITPRICE"));
				electricfees.setACCOUNTMONTH(rs.getString("ACCOUNTMONTH"));
				
				DianbiaoBean dianbiao = new DianbiaoBean();
				dianbiao.setDBBM(rs.getString("DBBM"));
				dianbiao.setDBID(rs.getString("DBID"));
				dianbiao.setDBZBDYHH(rs.getString("DBZBDYHH"));
				
				ZhandianBean zhandian = new ZhandianBean();
				zhandian.setSHENG(rs.getString("SHENG"));
				zhandian.setSHI(rs.getString("SHI"));
				zhandian.setXIAN(rs.getString("XIAN"));
				zhandian.setJZNAME(rs.getString("JZNAME"));
				zhandian.setJZCODE(rs.getString("JZCODE"));
				
				dianbiao.setZhandian(zhandian);
				ammeterdegrees.setDianbiao(dianbiao);
				ammeterdegrees.setElectricffess(electricfees);
				ammeterdegreesList.add(ammeterdegrees);
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

		return ammeterdegreesList;
	}
	public ArrayList<TopBaozhangBean> searchTopBaozhang(int start,
			String loginName, String loginId, String whereStr) {

		ArrayList<TopBaozhangBean> topBaozhangBeanList = new ArrayList<TopBaozhangBean>();
		StringBuffer sql_top = new StringBuffer();
		sql_top.append("select (SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=a.shicode ) SHINAME,a.shicode SHICODE," +
				"sum(a.dianliang) SUMDIANLIANG, sum(a.allmoney) SUMALLMONEY," +
				"sum(a.faxmoney) SUMFAXMONEY,ac.costtime COSTTIME " +
				"from AC_SOLO  a join ALLCOST ac on a.allcostid=ac.id  " +
				"where 1=1  "+whereStr+
				 "  and ((a.shicode in(select t.agcode from per_area t where t.accountid = '"
				+ loginId
				+ "'))) " +
				" group by a.shicode,ac.costtime " +
				"order by a.shicode ");
		System.out.println("报账地市,本月的期间查询sql:"+sql_top.toString());
//		log.info("[报账地市,本月的期间查询]" + sql_top);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql_top.toString());
			while (rs.next()) {
				TopBaozhangBean topBaozhangBean = new TopBaozhangBean();
				topBaozhangBean.setCosttime(rs.getString("COSTTIME"));
				topBaozhangBean.setShi(rs.getString("SHINAME"));
				topBaozhangBean.setSumAllmoney(rs.getString("SUMALLMONEY"));
				topBaozhangBean.setSumDianliang(rs.getString("SUMDIANLIANG"));
				topBaozhangBean.setSumFaxmoney(rs.getString("SUMFAXMONEY"));
				
				topBaozhangBeanList.add(topBaozhangBean);
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

		return topBaozhangBeanList;
	}
	public DianbiaoBean searchDianbiaoByDbzbdyhh(String dbzbdyhh) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ZDID,DBID,DBBM FROM DIANBIAO WHERE DBZBDYHH = '"+dbzbdyhh+"' ");
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				DianbiaoBean dianbiao = new DianbiaoBean();
				dianbiao.setZDID(rs.getString("ZDID"));
				dianbiao.setDBID(rs.getString("DBID"));
				dianbiao.setDBBM(rs.getString("DBBM"));
				return dianbiao;
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

	public ZhandianBean searchZhandianByZdid(String zdid){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SHENG,RNDIQU(SHENG) SHENGNAME,SHI,RNDIQU(SHI) SHINAME,XIAN,RNDIQU(XIAN) XIANNAME,JZNAME,JZCODE FROM ZHANDIAN WHERE ID = '"+zdid+"' ");
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				ZhandianBean zhandian = new ZhandianBean();
				zhandian.setSHENG(rs.getString("SHENGNAME"));
				zhandian.setSHI(rs.getString("SHINAME"));
				zhandian.setXIAN(rs.getString("XIANNAME"));
				zhandian.setJZNAME(rs.getString("JZNAME"));
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
		return null;
	}
}
