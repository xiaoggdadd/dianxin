package com.ptac.app.inter.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.ptac.app.inter.bean.KdgcElectrictypeBean;
import com.ptac.app.inter.bean.KdgcZhandianBean;
import com.ptac.app.inter.bean.SjInterFace;

public class KdgcInterfaceDaoImp implements KdgcInterfaceDao {
	Logger logger = Logger.getLogger(KdgcInterfaceDaoImp.class);
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	DataBase dataBase = new DataBase();

	@Override
	public List<KdgcElectrictypeBean> getElectrictypeInterface(String entertime) {
		List<KdgcElectrictypeBean> electrictypeBeans = new ArrayList<KdgcElectrictypeBean>();
		StringBuffer buffer = new StringBuffer();
		buffer
				.append("SELECT Z.SHI,Z.ID,Z.ZLFH,Z.XLX,D.BEILV,D.DANJIA,F.ACCOUNTMONTH,V.THISDEGREE,V.LASTDEGREE,V.THISDATETIME,"
						+ "V.LASTDATETIME,V.FLOATDEGREE,V.BLHDL,F.ACTUALPAY,F.FLOATPAY,"
						+ "  V.NETWORKHDL * 0.06 ZMQTDL,"
						+ "  V.NETWORKHDL * Z.KTXS KTDL, "
						+ "  V.ADMINISTRATIVEHDL,"
						+ "  V.MARKETHDL YYTSHDL,"
						+ "   (V.INFORMATIZATIONHDL + V.BUILDHDL) QTDL,"
						+ " F.ACCOUNTMONTH,F.ENTRYTIME,Z.SHI||Z.XIAN||Z.XIAOQU danwei,F.ENTRYPENSONNEL,Z.JLFH,"
						+ "D.DBID FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW V, DIANFEIVIEW F,ZHANDADD A "
						+ " WHERE Z.ID = D.ZDID AND A.ES_ID IS NOT NULL AND Z.ID=A.ZDID AND D.DBID = V.AMMETERID_FK AND V.AMMETERDEGREEID = F.AMMETERDEGREEID_FK AND F.ACCOUNTMONTH=to_date('"
						+ entertime + "','yyyy-mm') ");
		try {
			dataBase.connectDb();
			connection = dataBase.getConnection();
			preparedStatement = connection.prepareStatement(buffer.toString());
			logger.info(buffer.toString());
			resultSet = preparedStatement.executeQuery();
			logger.info("科大电费查询");
			
			while (resultSet.next()) {
				KdgcElectrictypeBean bean = new KdgcElectrictypeBean();
				bean.setShi(resultSet.getString("shi"));
				bean.setId(resultSet.getInt("id"));
				bean.setZlfh(resultSet.getInt("zlfh"));
				bean.setXlx1(resultSet.getString("xlx"));
				bean.setBeilv(resultSet.getString("beilv"));
				bean.setDanjia(resultSet.getString("danjia"));
				bean.setAccountmonth(resultSet.getString("accountmonth"));
				bean.setThisdegree(resultSet.getString("thisdegree"));
				bean.setLastdegree(resultSet.getString("lastdegree"));
				bean.setThisdatetime(resultSet.getString("thisdatetime"));
				bean.setLastdatetime(resultSet.getString("lastdatetime"));
				bean.setFloatdegree(resultSet.getString("floatdegree"));
				bean.setBlhdl(resultSet.getInt("blhdl"));
				bean.setActualpay(resultSet.getInt("actualpay"));
				bean.setFloatpay(resultSet.getString("floatpay"));
				bean.setZmqtdl(resultSet.getString("zmqtdl"));
				bean.setKtdl(resultSet.getString("ktdl"));
				bean.setNetworkhdl(resultSet.getString("ADMINISTRATIVEHDL"));
				bean.setYytshdl(resultSet.getString("yytshdl"));
				bean.setQtdl(resultSet.getString("qtdl"));
				bean.setAccountmonth(resultSet.getString("accountmonth"));
				bean.setEntrytime(resultSet.getString("entrytime"));
				bean.setLrdw(resultSet.getString("danwei"));
				bean.setEntrypensonnel(resultSet.getString("entrypensonnel"));
				bean.setJlfh(resultSet.getString("jlfh"));
				bean.setDbi(resultSet.getString("dbid"));
				electrictypeBeans.add(bean);
			}
			return electrictypeBeans;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			dataBase.free(resultSet, preparedStatement, connection);
		}

		return electrictypeBeans;
	}

	@Override
	public List<KdgcZhandianBean> getZhandianInterface() {
		List<KdgcZhandianBean> kdgcZhandianBeans = new ArrayList<KdgcZhandianBean>();
		StringBuffer buffer = new StringBuffer();
		buffer
				.append("SELECT '中国联合网络通信公司山东分公司' SHENGID,Z.SHENG,Z.SHI,Z.XIAN,Z.XIAOQU,Z.JZNAME,Z.ID,"
						+ "Z.PROPERTY, Z.STATIONTYPE,z.xlx,Z.YFLX,A.ROOMTYPE,A.ELECTRICTYPE,A.ROOMAREA,A.USERAREA,"
						+ "A.CABINETNUM,A.USECNUM,A.UNUM,A.USEUNUM,Z.ADDRESS,Z.JFLX,A.USEESDATE,"
						+ " A.POLE,Z.ENTRYTIME,Z.GDFS,Z.JZTYPE,Z.QYZT,Z.KTS,A.ES_ID FROM ZHANDIAN Z,"
						+ "ZHANDADD A WHERE Z.ID = A.ZDID AND A.ES_ID IS NOT NULL"
						+ "");
		try {
			dataBase.connectDb();
			connection = dataBase.getConnection();
			preparedStatement = (PreparedStatement) connection
					.prepareStatement(buffer.toString());
			resultSet = preparedStatement.executeQuery();
			logger.info("科大站点查询");
			logger.info(buffer.toString());
			while (resultSet.next()) {
				KdgcZhandianBean bean = new KdgcZhandianBean();
				bean.setShengid((resultSet.getString("shengid").toString())
						.trim());
				bean.setSheng(resultSet.getString("sheng"));
				bean.setShi(resultSet.getString("shi"));
				bean.setXian(resultSet.getString("xian"));
				bean.setXiaoqu(resultSet.getString("xiaoqu"));
				bean.setJzname(resultSet.getString("jzname"));
				bean.setId(resultSet.getInt("id"));
				bean.setProperty(resultSet.getString("property"));
				bean.setStationtype(resultSet.getString("stationtype"));
				bean.setJzlx(resultSet.getString("xlx"));
				bean.setYflx(resultSet.getString("yflx"));
				bean.setRoomtype(resultSet.getString("roomtype"));
				bean.setElectrictype(resultSet.getString("electrictype"));
				bean.setRoomarea(resultSet.getInt("roomarea"));
				bean.setUserarea(resultSet.getInt("userarea"));
				bean.setCabinetnum(resultSet.getInt("cabinetnum"));
				bean.setUsecnum(resultSet.getInt("usecnum"));
				bean.setUnum(resultSet.getInt("unum"));
				bean.setUseunum(resultSet.getInt("useunum"));
				bean.setAddress(resultSet.getString("address"));
				bean.setJflx(resultSet.getString("jflx"));
				bean
						.setUseesdate(resultSet.getDate("useesdate") != null ? resultSet
								.getDate("useesdate").toString()
								: "");
				bean.setBgsign(resultSet.getString("pole"));
				bean.setEntrytime(resultSet.getString("entrytime"));
				bean.setGdfs(resultSet.getString("gdfs"));
				bean.setJztype(resultSet.getString("jztype"));
				bean.setQyzt(resultSet.getString("qyzt"));
				bean.setKts(resultSet.getString("kts"));
				bean.setEs_id(resultSet.getString("es_id"));
				kdgcZhandianBeans.add(bean);
			}
			return kdgcZhandianBeans;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			dataBase.free(resultSet, preparedStatement, connection);
		}

		return kdgcZhandianBeans;
	}
	public static void main(String[] args) {
		SjInterFace face = new KdgcInterfaceDaoImp().getCountKd("2014-10");
		System.out.println(face.getTOTALNUMBER());
		System.out.println(face.getTOTALCOST());
		System.out.println(face.getTOTALELECTRIC());
	}

	@Override
	public SjInterFace getCountKd(String entertime) {
		SjInterFace kdgcZhandianBeans = new SjInterFace();
		StringBuffer buffer1 = new StringBuffer();
		StringBuffer buffer2 = new StringBuffer();
		Long l1 = null;
		Long l2 = null;
		buffer1.append("SELECT count(*)countzd FROM ZHANDIAN Z, ZHANDADD A WHERE Z.ID = A.ZDID AND A.ES_ID IS NOT NULL ");
		buffer2.append("SELECT COUNT(*)COUNTDF FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW V, DIANFEIVIEW F, ZHANDADD A" +
				" WHERE Z.ID = D.ZDID AND A.ES_ID IS NOT NULL AND Z.ID = A.ZDID AND D.DBID = V.AMMETERID_FK" +
				" AND V.AMMETERDEGREEID = F.AMMETERDEGREEID_FK AND F.ACCOUNTMONTH = to_date('"+entertime+"','yyyy-mm')");
		try {
			dataBase.connectDb();
			connection = dataBase.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(buffer1.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				l1 = new Long(resultSet.getLong("countzd"));
				kdgcZhandianBeans.setTOTALCOST(l1);
			}
			preparedStatement = (PreparedStatement) connection.prepareStatement(buffer2.toString());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				l2 = new Long(resultSet.getLong("COUNTDF"));
				kdgcZhandianBeans.setTOTALELECTRIC(l2);
			}
			Long l3 = new Long(l1+l2);
			kdgcZhandianBeans.setTOTALNUMBER(l3.intValue());
			return kdgcZhandianBeans;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			dataBase.free(resultSet, preparedStatement, connection);
		}

		return kdgcZhandianBeans;
	}

}
