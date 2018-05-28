package com.ptac.app.inter.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.ptac.app.inter.bean.SjInterFace;

public class InterfaceCheck {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private DataBase dataBase;
	private ArrayList<SjInterFace> arrayList;

	public ArrayList<SjInterFace> getSjMessage(String month) {
		dataBase = new DataBase();
		StringBuffer sql = new StringBuffer(
				"SELECT T.ACCOUNTSDATE,T.INITIDATE,T.RECEIVEDATE,T.STATION,T.TOTALNUMBER,T.INITIATOR FROM INTERFACE_USER.INTERFACE_TABLE@UNEBD_81 T ");
	//"SELECT T.ACCOUNTSDATE,T.INITIDATE,T.RECEIVEDATE,T.STATION,T.TOTALNUMBER,T.INITIATOR FROM INTERFACE_USER.INTERFACE_TABLE@UNEBD_81 T ");
		arrayList = new ArrayList<SjInterFace>();
		if (month != null && !"".equals(month)) {
			sql.append("WHERE T.ACCOUNTSDATE='" + month+"'");
		}
		try {
			connection = dataBase.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			while (resultSet.next()) {
				SjInterFace interFace = new SjInterFace();
				interFace.setACCOUNTSDATE(resultSet.getString("ACCOUNTSDATE"));
				interFace.setINITIDATE(resultSet.getDate("INITIDATE"));
				interFace.setRECEIVEDATE(resultSet.getDate("RECEIVEDATE"));
				interFace.setSTATION(resultSet.getString("STATION"));
				interFace.setTOTALNUMBER(resultSet.getInt("TOTALNUMBER"));
				interFace.setINITIATOR(resultSet.getString("INITIATOR"));

				arrayList.add(interFace);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dataBase.free(resultSet, statement, connection);
		}
		return arrayList;
	}
}
