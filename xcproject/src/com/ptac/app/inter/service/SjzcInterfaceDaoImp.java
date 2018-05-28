package com.ptac.app.inter.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormat;

import com.noki.database.DataBase;
import com.ptac.app.inter.bean.AuditBean;
import com.ptac.app.inter.bean.SjInterFace;

public class SjzcInterfaceDaoImp implements SjzcInterfaceDao {
	Logger logger = Logger.getLogger(SjzcInterfaceDaoImp.class);
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	DataBase dataBase = new DataBase();

	/*
	 * 审计数据的取数
	 */
	@Override
	public List<AuditBean> getSjzcData(String month, String btime, String etime) {
		// 时间的处理
		List<AuditBean> sjzcdata = new ArrayList<AuditBean>();
		StringBuffer buffer = new StringBuffer();
		buffer
				.append("SELECT '"
						+ month
						+ "' accountmonth, Z.SHI, RNDIQU(Z.SHI) AS SHINAME, Z.XIAN, RNDIQU(Z.XIAN) AS XIANNAME, Z.XIAOQU, RNDIQU(Z.XIAOQU) AS XIAOQUNAME, Z.ID, Z.JZNAME, Z.PROPERTY, RTNAME(Z.PROPERTY) AS PROPERTYNAME, Z.STATIONTYPE, RTNAME(Z.STATIONTYPE) AS STATIONTYPENAME, D.DFZFLX, RTNAME(D.DFZFLX) AS DFZFLXNAME, Z.GDFS, RTNAME(Z.GDFS) AS GDFSNAME, D.DBID, AM.LASTDEGREE, AM.THISDEGREE, to_char(to_date(AM.LASTDATETIME,'yyyy-mm-dd'),'yyyy-mm-dd')LASTDATETIME, to_char(to_date(AM.THISDATETIME,'yyyy-mm-dd'),'yyyy-mm-dd')THISDATETIME, AM.BLHDL, EF.UNITPRICE, EF.FLOATPAY, EF.ACTUALPAY, EF.JSZQ, to_char(to_date(EF.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss')ENTRYTIME, EF.ENTRYPENSONNEL, to_char(to_date(EF.MANUALAUDITDATETIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss')MANUALAUDITDATETIME, EF.MANUALAUDITNAME, to_char(to_date(EF.FINANCIALDATETIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd hh24:mi:ss')FINANCIALDATETIME, EF.FINANCIALOPERATOR, D.BEILV, EF.NOTETYPEID, RTNAME(EF.NOTETYPEID) AS NOTEYPEIDNAME FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES AM, ELECTRICFEES EF WHERE Z.ID = D.ZDID AND D.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK AND Z.SHSIGN = '1' AND Z.CAIJI = '0' AND Z.XUNI = '0' AND EF.MANUALAUDITSTATUS = '2'"
						+ " AND to_char(to_date(ef.financialdatetime,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd')>'"
						+ btime
						+ "' AND to_char(to_date(ef.financialdatetime,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd')<='"
						+ etime + "'");
		logger.info(buffer.toString());
		try {
			this.dataBase.connectDb();
			this.connection = this.dataBase.getConnection();
			this.preparedStatement = this.connection.prepareStatement(buffer
					.toString());
			this.resultSet = this.preparedStatement.executeQuery();
			while (this.resultSet.next()) {
				AuditBean bean = new AuditBean();
				bean.setMonth_id(this.resultSet.getString("accountmonth"));
				bean.setCity_id(this.resultSet.getString("SHI"));
				bean.setCity_name(this.resultSet.getString("SHINAME"));
				bean.setCounty_id(this.resultSet.getString("XIAN"));
				bean.setCounty_name(this.resultSet.getString("XIANNAME"));
				bean.setTowns_id(this.resultSet.getString("XIAOQU"));
				bean.setTowns_name(this.resultSet.getString("XIAOQUNAME"));
				bean.setJz_id(this.resultSet.getString("ID"));
				bean.setJz_name(this.resultSet.getString("JZNAME"));
				bean.setProperty_id(this.resultSet.getString("PROPERTY"));
				bean.setProperty(this.resultSet.getString("PROPERTYNAME"));
				bean.setStationtype_id(this.resultSet.getString("STATIONTYPE"));
				bean
						.setStationtype(this.resultSet
								.getString("STATIONTYPENAME"));
				bean.setDfzflx_id(this.resultSet.getString("DFZFLX"));
				bean.setDfzflx_name(this.resultSet.getString("DFZFLXNAME"));
				bean.setGdfs_id(this.resultSet.getString("GDFS"));
				bean.setGdfs_name(this.resultSet.getString("GDFSNAME"));
				bean.setDbid(this.resultSet.getString("DBID"));
				bean.setLastdegree(this.resultSet.getString("LASTDEGREE"));
				bean.setThisdegree(this.resultSet.getString("THISDEGREE"));
				bean.setLastdatetime(this.resultSet.getString("LASTDATETIME"));
				bean.setThisdatetime(this.resultSet.getString("THISDATETIME"));
				bean.setPower_count(this.resultSet.getDouble("BLHDL"));
				bean.setUnitprice(this.resultSet.getDouble("UNITPRICE"));
				bean.setFloatpay(this.resultSet.getDouble("FLOATPAY"));
				bean.setPower_fee(this.resultSet.getDouble("ACTUALPAY"));
				bean.setPeriod(this.resultSet.getInt("JSZQ"));
				bean.setEntrytime(this.resultSet.getString("ENTRYTIME"));
				bean.setEntrypensonnel(this.resultSet
						.getString("ENTRYPENSONNEL"));
				bean.setManualauditdatetime(this.resultSet
						.getString("MANUALAUDITDATETIME"));
				bean.setManualauditname(this.resultSet
						.getString("MANUALAUDITNAME"));
				bean.setFinancialdatetime(this.resultSet
						.getString("FINANCIALDATETIME"));
				bean.setFinancialoperator(this.resultSet
						.getString("FINANCIALOPERATOR"));
				bean.setBeilv(this.resultSet.getDouble("BEILV"));
				bean.setNotetype_id(this.resultSet.getString("NOTETYPEID"));
				bean
						.setNotetype_name(this.resultSet
								.getString("NOTEYPEIDNAME"));

				sjzcdata.add(bean);
			}
			return sjzcdata;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			this.dataBase.free(this.resultSet, this.preparedStatement,
					this.connection);
		}

		return sjzcdata;
	}

	@Override
	public SjInterFace getCount(String month, String btime, String etime) {
		StringBuffer buffer = new StringBuffer();
		SjInterFace face = null;
		buffer
				.append("SELECT sum(ef.ACTUALPAY)sumdf,sum(am.BLHDL)sumdl,count(*)countts FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES AM, ELECTRICFEES EF "
						+ "  WHERE Z.ID = D.ZDID AND D.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK"
						+ " AND Z.SHSIGN = '1' AND Z.CAIJI = '0' AND Z.XUNI = '0' AND EF.MANUALAUDITSTATUS = '2' AND to_char(to_date(ef.financialdatetime, 'yyyy-mm-dd hh24:mi:ss'),"
						+ "'yyyy-mm-dd') > '"
						+ btime
						+ "' AND to_char(to_date(ef.financialdatetime, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') <= '"
						+ etime+"'");
		logger.info(buffer.toString());
		try {
			this.dataBase.connectDb();
			this.connection = this.dataBase.getConnection();
			this.preparedStatement = this.connection.prepareStatement(buffer
					.toString());
			this.resultSet = this.preparedStatement.executeQuery();
			while (this.resultSet.next()) {
				face = new SjInterFace();
				face.setTOTALCOST(resultSet.getLong("sumdf"));
				face.setTOTALELECTRIC(resultSet.getLong("sumdl"));
				face.setTOTALNUMBER(resultSet.getInt("countts"));
			}
			return face;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			this.dataBase.free(this.resultSet, this.preparedStatement,
					this.connection);
		}
		return face;
	}

	public static void main(String[] args) {
		SjInterFace face = new SjzcInterfaceDaoImp()
				.getCount("201411", "2014-11-03", "2014-12-03");
		System.out.println(face.getTOTALNUMBER());
		System.out.println(face.getTOTALCOST());
		System.out.println(face.getTOTALELECTRIC());
	}
}
