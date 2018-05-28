package com.noki.query.basequery.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jenkov.prizetags.calendar.impl.DateBaseTag;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.MD5;
import com.noki.util.Query;

public class AmmeterDegreeQueryBean {
	// 
	public synchronized List<ElectricFeesFormBean> getPageData(int start,
			String whereStr, String loginId) {
		List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		// 
		try {

			fzzdstr = getFuzeZdid(db, loginId);
			sql = "SELECT ZD.JZNAME ,ZD.EDHDL,ZD.KONGTIAO,AMMETERDEGREEID,DB.DBNAME,ZD.ID,TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') ENTRYTIME,"
					+ "(SELECT NAME FROM ACCOUNT WHERE ACCOUNTNAME=AD.ENTRYPENSONNEL AND DELSIGN = 1) AS ENTRYPENSONNEL,TO_CHAR(AD.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(AD.ENDMONTH,'yyyy-mm') ENDMONTH,"
					+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) as xian,"
					+ "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) as xiaoqu,"
					+ "DB.DLLX ELECTRICCURRENTTYPE_AMMETER,(SELECT NAME FROM INDEXS WHERE CODE=DB.YDSB AND TYPE = 'YDSB') USAGEOFELECTYPEID_AMMETER,"
					+ "(SELECT NAME FROM INDEXS WHERE CODE = DB.DBYT AND TYPE = 'DBYT') AMMETERUSE,"
					+ "AMMETERID_FK,LASTDEGREE,THISDEGREE,TO_CHAR(LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(THISDATETIME,'yyyy-mm-dd'),TO_CHAR(FLOATDEGREE,'FM9999999990.00')AS FLOATDEGREE,"
					+ "TO_CHAR(BLHDL,'FM9999999990.00')AS ACTUALDEGREE,AD.MANUALAUDITSTATUS,ZD.ZLFH FROM DIANDUVIEW AD,DIANBIAO DB,"
					+ "ZHANDIAN ZD WHERE AD.AMMETERID_FK=DB.DBID AND DB.DBYT='dbyt03' AND DB.ZDID=ZD.ID "
					+ whereStr
					+ " "
					+ "AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ") ORDER BY ENTRYTIME DESC,JZNAME DESC";
			System.out.println("电量查询,导出sql:" + sql.toString());
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				ElectricFeesFormBean formbean = new ElectricFeesFormBean();
				formbean.setJzname(rs.getString(1) != null ? rs.getString(1)
						: "");
				formbean.setEdhdl(rs.getString(2) != null ? rs.getString(2)
						: "");
				formbean.setKongtiao(rs.getString(3) != null ? rs.getString(3)
						: "");
				formbean.setAmmeterdegreeid(rs.getString(4) != null ? rs
						.getString(4) : "");
				formbean.setDbname(rs.getString(5) != null ? rs.getString(5)
						: "");
				formbean.setZdid(rs.getString(6) != null ? rs.getString(6)
						: "");
				formbean.setEntrytime(rs.getString(7) != null ? rs.getString(7)
						: "");
				formbean.setEntrypensonnel(rs.getString(8) != null ? rs
						.getString(8) : "");
				formbean.setStartmonth(rs.getString(9) != null ? rs
						.getString(9) : "");
				formbean.setEndmonth(rs.getString(10) != null ? rs
						.getString(10) : "");
				formbean.setStationtype(rs.getString(11) != null ? rs
						.getString(11) : "");

				formbean.setXian(rs.getString(12) != null ? rs.getString(12)
						: "");
				formbean.setXiaoqu(rs.getString(13) != null ? rs.getString(13)
						: "");
				formbean.setDllx(rs.getString(14) != null ? rs.getString(14)
						: "");
				formbean.setYdsb(rs.getString(15) != null ? rs.getString(15)
						: "");
				formbean.setDbyt(rs.getString(16) != null ? rs.getString(16)
						: "");
				formbean.setAmmererid(rs.getString(17) != null ? rs
						.getString(17) : "");
				formbean.setLastdegree(rs.getString(18) != null ? rs
						.getString(18) : "");
				formbean.setThisdegree(rs.getString(19) != null ? rs
						.getString(19) : "");
				formbean.setLastdatetime(rs.getString(20) != null ? rs
						.getString(20) : "");
				formbean.setThisdatetime(rs.getString(21) != null ? rs
						.getString(21) : "");
				formbean.setFloatdegree(rs.getString(22) != null ? rs
						.getString(22) : "");
				formbean.setActualdegree(rs.getString(23) != null ? rs
						.getString(23) : "");
				formbean.setManualauditstatus(rs.getString(24) != null ? rs
						.getString(24) : "");
				formbean.setZlfh(rs.getString(25) != null ? rs.getString(25)
						: "");
				list.add(formbean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DbException e) {
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

	
	// 
	public ElectricFeesFormBean getCount(String whereStr, String loginId) {
		String count1 = "";// 
		String sumwtg = "";// 
		String sumsh = "";// 
		String sum1 = "";// 
		DataBase db = new DataBase();
		ElectricFeesFormBean bean1 = new ElectricFeesFormBean();
		String fzzdstr = "";
		ResultSet rs = null;
		try {
			db.connectDb();
			fzzdstr = getFuzeZdid(db, loginId);
			StringBuffer strall = new StringBuffer();
			strall.append("SELECT COUNT(*) COUNT1,SUM(DECODE(AD.MANUALAUDITSTATUS,'',1,'0',1)) SUMWTG,SUM(DECODE(AD.MANUALAUDITSTATUS,'1',1,'2',1)) SUMSH,"
							+ "SUM(TO_CHAR(BLHDL,'FM9999999990.00')) SUM1 FROM DIANDUVIEW AD,DIANBIAO DB,ZHANDIAN ZD "
							+ "WHERE AD.AMMETERID_FK=DB.DBID  AND DB.DBYT='dbyt03' AND DB.ZDID=ZD.ID "
							+ whereStr
							+ " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
							+ loginId + "'))" + fzzdstr + ")");
			System.out.println("sql" + strall.toString());
			rs = db.queryAll(strall.toString());
			while (rs.next()) {
				count1 = rs.getString(1) != null ? rs.getString(1) : "0";
				sumwtg = rs.getString(2) != null ? rs.getString(2) : "0";
				sumsh = rs.getString(3) != null ? rs.getString(3) : "0";
				sum1 = rs.getString(4) != null ? rs.getString(4) : "0";
			}
			bean1.setCount(count1);
			bean1.setRgno(sumwtg);
			bean1.setRg(sumsh);
			bean1.setCountts(sum1);
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
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
		return bean1;
	}

	
	private int allPage;

	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}

	public int getAllPage() {
		return this.allPage;
	}

	// 
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
		System.out.println("sql" + cxtj);
		return cxtj.toString();
	}
}
