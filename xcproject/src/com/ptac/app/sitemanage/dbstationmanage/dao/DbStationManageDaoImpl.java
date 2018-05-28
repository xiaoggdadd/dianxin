package com.ptac.app.sitemanage.dbstationmanage.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.ptac.app.sitemanage.dbstationmanage.bean.DbStationManageBean;

public class DbStationManageDaoImpl implements DbStationManageDao {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private DataBase dataBase;
	private ArrayList<DbStationManageBean> arrayList;
	private Logger logger  = Logger.getLogger(DbStationManageDaoImpl.class);

	@Override
	public List<DbStationManageBean> getStationInfo(String whereStr,String loginId) {
		dataBase = new DataBase();
		StringBuffer sql= new StringBuffer(
				"SELECT ZD.ID ZDID,RNDIQU(ZD.SHI) SHI,ZD.JZNAME JZNAME," +
				"RTNAME(ZD.PROPERTY) PROPERTY,RTNAME(ZD.STATIONTYPE) STATIONTYPE," +
				"RTNAME(ZD.GDFS) GDFS FROM ZHANDIAN ZD WHERE 1=1 ");
		arrayList = new ArrayList<DbStationManageBean>();
		if (whereStr != null && !"".equals(whereStr)) {
			sql.append(whereStr);
		}
		sql.append(" AND ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA t WHERE T.ACCOUNTID = '").append(loginId).append("')");
		try {
			logger.info("���վ��ά����ѯ:"+sql.toString());
			connection = dataBase.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			while (resultSet.next()) {
				DbStationManageBean bean = new DbStationManageBean();
				bean.setZdid(resultSet.getString("ZDID"));
				bean.setCity(resultSet.getString("SHI"));
				bean.setZdname(resultSet.getString("JZNAME"));
				bean.setProperty(resultSet.getString("PROPERTY"));
				bean.setStationtype(resultSet.getString("STATIONTYPE"));
				bean.setGdfs(resultSet.getString("GDFS"));
				arrayList.add(bean);
			}
		} catch (Exception e) {
			logger.error("���վ��ά����ѯʧ��:"+e.getMessage());
			e.printStackTrace();
		} finally {
			dataBase.free(resultSet, statement, connection);
		}
		return arrayList;
	}

	@Override
	public String checkStatus(String wherezdid, String status) {
		String msg = "���Ķ���ʶʧ�ܣ�";
		dataBase = new DataBase();
		StringBuffer sql= new StringBuffer(
				"UPDATE ZHANDIAN ZD SET ZD.COUNTBZW = '");
		sql.append(status).append("'").append(" WHERE ZD.ID IN(");
		try {
			connection = dataBase.getConnection();
			connection.setAutoCommit(false);

			if (wherezdid != "" && wherezdid != null) {// ���id��Ϊ����ִ��sql
				sql.append(wherezdid).append(")");
				logger.info("���վ��ά�����ı�ʶ:"+sql.toString());
				statement = connection.createStatement();
				statement.executeUpdate(sql.toString());
				connection.commit();
				connection.setAutoCommit(true);
			}
			if (status.equals("1")) {
				msg = "������ɹ���";
			} else if (status.equals("0")) {
				msg = "ȡ�����ɹ���";
			} 
		} catch (Exception e) {
			logger.error("���վ��ά�����ı�ʶʧ��:"+e.getMessage());
			e.printStackTrace();
			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			dataBase.free(resultSet, statement, connection);
		}
		return msg;
	}

}
