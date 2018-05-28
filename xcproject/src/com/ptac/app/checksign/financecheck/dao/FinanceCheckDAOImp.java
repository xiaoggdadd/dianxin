package com.ptac.app.checksign.financecheck.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;

public class FinanceCheckDAOImp implements FinanceCheckDao {

	public synchronized String modifyCheckFees(ElectricFeesFormBean formBean,
			String chooseIdStr1, String chooseIdStr2, String bz,String kjyf) {//财务审核数据 审核方法
		String s = "to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		
		DataBase db = new DataBase();
		Connection connc = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		String msg = "审核电费信息失败！";

		StringBuffer sql3 = new StringBuffer();
		StringBuffer sql4 = new StringBuffer();
		try {
			connc = db.getConnection();
			connc.setAutoCommit(false);
			//旧的执行sql方式用它，新的执行sql方式不能使用否则报错
		/*	if(chooseIdStr1.split(",").length>10||chooseIdStr2.split(",").length>10){
	        	  sql3.append("execute");
	        	  sql4.append("execute");
	          }*/
			
			sql3.append(" UPDATE ELECTRICFEES SET MANUALAUDITSTATUS='"
					+ bz + "',"
					+ "FINANCIALDATETIME=" + s + "," + "FINANCIALOPERATOR='"
					+ formBean.getFinancialoperator() + "'," + "KJYF=to_date('"
					+ kjyf + "','yyyy-mm')" + " WHERE DFUUID IN ("
					+ chooseIdStr1 + ")");
			sql4.append(" UPDATE prepayment SET financeaudit='"
					+ bz + "'," + "FINANCIALDATE="
					+ s + "," + "FINANCIALOPERATOR='"
					+ formBean.getFinancialoperator() + "'," + "KJYF=to_date('"
					+ kjyf + "','yyyy-mm')" + " WHERE UUID IN ("
					+ chooseIdStr2 + ")");
			msg = "审核电费信息失败！";

			if (chooseIdStr1 != null && !"".equals(chooseIdStr1)) {
				ps1 = connc.prepareStatement(sql3.toString());
				System.out.println("电费的SQL"+sql3.toString());
				ps1.executeUpdate();
			}
			if (chooseIdStr2 != null && !"".equals(chooseIdStr2)) {
				ps2 = connc.prepareStatement(sql4.toString());
				System.out.println("预付费的SQL"+sql4.toString());
				ps2.executeUpdate();
			}

			if (bz.equals("2")) {
				msg = "审核电费信息通过成功！";
			} else if (bz.equals("-1")) {
				msg = "审核电费信息不通过成功！";
			} else {
				msg = "审核电费信息失败！";
			}
			 if(!"".equals(chooseIdStr1) || !"".equals(chooseIdStr2)){
				 connc.commit();
			 }
			 connc.setAutoCommit(true);
		} catch (Exception de) {
			try {
				connc.rollback();
				connc.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			de.printStackTrace();
			msg = "审核电费信息失败！";
		} finally {
			db.free(null, ps1, null);
			db.free(null, ps2, connc);
		}

		return msg;
	}
}
