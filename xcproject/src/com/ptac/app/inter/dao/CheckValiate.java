package com.ptac.app.inter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.ptac.app.inter.bean.SjInterFace;
import com.ptac.app.inter.ftp.FileWriterUtil;

public class CheckValiate {
	Logger logger = Logger.getLogger(CheckValiate.class);
	private Connection connection;
	private PreparedStatement preparedStatement;
	private PreparedStatement preparedStatement2;
	private ResultSet resultSet;
	private ResultSet resultSet2;
	private DataBase dataBase;
	private Date date = new Date(new java.util.Date().getTime());
	private static CheckValiate checkValiate = null;

	private CheckValiate() {
	}

	public static CheckValiate getInstance() {
		if (checkValiate == null) {
			checkValiate = new CheckValiate();
		}
		return checkValiate;
	}

	/**
	 * 审计数据校验，标识：山东审计 唯一标识， 时间：账期为接收的状态为N的最大账期，保证上传为单一账期数据
	 * 
	 * @param month
	 * @return
	 */
	public boolean isSjValidate(String month) {
		boolean flage = false;
		String sql = "SELECT MAX(T.ACCOUNTSDATE)ACCOUNTSDATE FROM INTERFACE_USER.INTERFACE_TABLE@UNEBD_81 T WHERE T.STATION='N' AND T.INITIATOR='山东审计'";
		dataBase = new DataBase();
		try {
			connection = dataBase.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (month.equals(resultSet.getString("ACCOUNTSDATE"))) {
					flage = true;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			dataBase.free(resultSet, preparedStatement, connection);
		}

		return flage;
	}

	/**
	 * 完成审计指定账期的“接收并执行”
	 * 
	 * @param month
	 * @return
	 */
	public boolean updateSjR(String month) {
		boolean flage = false;
		String sql = "UPDATE INTERFACE_USER.INTERFACE_TABLE@UNEBD_81 T SET T.STATION='R',T.RECEIVEDATE=? WHERE T.ACCOUNTSDATE=? AND T.INITIATOR='山东审计'";
		dataBase = new DataBase();
		int i = 0;
		try {
			connection = dataBase.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, date);
			preparedStatement.setString(2, month);
			i = preparedStatement.executeUpdate();
			if (i > 0) {
				flage = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			dataBase.free(resultSet, preparedStatement, connection);
		}
		return flage;
	}

	/**
	 * 完成审计指定账期的“发送完成”
	 * 
	 * @param month
	 * @return
	 */
	public boolean updateSjS(String month, SjInterFace face) {
		boolean flage = false;
		String sql = "UPDATE INTERFACE_USER.INTERFACE_TABLE@UNEBD_81 T SET T.STATION='S',T.TOTALNUMBER=?,T.TOTALCOST=?,T.TOTALELECTRIC=?  WHERE T.ACCOUNTSDATE=? AND T.INITIATOR='山东审计'";
		dataBase = new DataBase();
		int i = 0;
		try {
			connection = dataBase.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, face.getTOTALNUMBER());
			preparedStatement.setLong(2, face.getTOTALCOST());
			preparedStatement.setLong(3, face.getTOTALELECTRIC());
			preparedStatement.setString(4, month);
			i = preparedStatement.executeUpdate();
			if (i > 0) {
				flage = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			dataBase.free(resultSet, preparedStatement, connection);
		}
		return flage;
	}

	/**
	 * 完成审计指定账期的“数据异常重新发送” 等待发送指令
	 * 
	 * @param month
	 * @return
	 */
	public boolean updateSjU(String month) {
		boolean flage = false;
		String sql = "UPDATE INTERFACE_USER.INTERFACE_TABLE@UNEBD_81 T SET T.STATION='U' WHERE T.ACCOUNTSDATE=? AND T.INITIATOR='山东审计'";
		dataBase = new DataBase();
		int i = 0;
		try {
			connection = dataBase.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, month);
			i = preparedStatement.executeUpdate();
			if (i > 0) {
				flage = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			dataBase.free(resultSet, preparedStatement, connection);
		}
		return flage;
	}

	/**
	 * 科大数据校验，标识：山东科大 唯一标识， 时间：账期为接收的状态为N的最大报账月份，保证上传为单一报账月份的数据
	 * 
	 * @param month
	 * @return
	 */
	public boolean isKdValidate(String month) {
		boolean flage = false;
		String sql = "SELECT MAX(T.ACCOUNTSDATE)ACCOUNTSDATE FROM INTERFACE_USER.INTERFACE_TABLE@UNEBD_81 T WHERE T.STATION='N' AND T.INITIATOR='综分'";
		dataBase = new DataBase();
		try {
			connection = dataBase.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (month.endsWith(resultSet.getString("ACCOUNTSDATE"))) {
					flage = true;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			dataBase.free(resultSet, preparedStatement, connection);
		}

		return flage;
	}

	/**
	 * 完成科大指定报账月份数据的“接收并执行”
	 * 
	 * @param month
	 * @return
	 */
	public boolean updateKdR(String month) {
		boolean flage = false;
		String sql = "UPDATE INTERFACE_USER.INTERFACE_TABLE@UNEBD_81 T SET T.STATION='R',T.RECEIVEDATE=? WHERE T.ACCOUNTSDATE=? AND T.INITIATOR='综分'";
		dataBase = new DataBase();
		int i = 0;
		try {
			connection = dataBase.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDate(1, date);
			preparedStatement.setString(2, month);
			i = preparedStatement.executeUpdate();
			if (i > 0) {
				flage = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			dataBase.free(resultSet, preparedStatement, connection);
		}
		return flage;
	}

	/**
	 * 完成科大制定报账月的数据的“发送完成”
	 * 
	 * @param month
	 * @return
	 */
	public boolean updateKdS(String month, SjInterFace face) {
		boolean flage = false;
		String sql = "UPDATE INTERFACE_USER.INTERFACE_TABLE@UNEBD_81 T SET T.STATION='S',T.TOTALNUMBER=?,T.TOTALCOST=?,T.TOTALELECTRIC=?  WHERE T.ACCOUNTSDATE=? AND T.INITIATOR='综分'";
		dataBase = new DataBase();
		logger.info(sql);
		logger.info(face.getTOTALNUMBER()+"  "+face.getTOTALCOST()+"  "+face.getTOTALELECTRIC()+"  "+month);
		int i = 0;
		try {
			connection = dataBase.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, face.getTOTALNUMBER());
			preparedStatement.setLong(2, face.getTOTALCOST());
			preparedStatement.setLong(3, face.getTOTALELECTRIC());
			preparedStatement.setString(4, month);
			i = preparedStatement.executeUpdate();
			
			if (i > 0) {
				flage = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			dataBase.free(resultSet, preparedStatement, connection);
		}
		return flage;
	}

	/**
	 * 完成科大指定报账月份的数据 “数据异常重新发送”状态，等待二次传送
	 * 
	 * @param month
	 * @return
	 */
	public boolean updateKdU(String month) {
		boolean flage = false;
		String sql = "UPDATE INTERFACE_USER.INTERFACE_TABLE@UNEBD_81 T SET T.STATION='U' WHERE T.ACCOUNTSDATE=? AND T.INITIATOR='综分'";
		dataBase = new DataBase();
		int i = 0;
		try {
			connection = dataBase.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, month);
			i = preparedStatement.executeUpdate();
			if (i > 0) {
				flage = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			dataBase.free(resultSet, preparedStatement, connection);
		}
		return flage;
	}

	public static void main(String[] args) {
		System.out.println(CheckValiate.getInstance().isSjValidate("201411"));
	}
}
