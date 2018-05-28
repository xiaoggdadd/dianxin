package com.ptac.app.chinamobile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.mobi.common.Account;
import com.ptac.app.inportuserzibaodian.util.Format;


public class CMCCCheckBillAvailableImp implements CMCCCheckBillAvailable {
    private String dianbiaoid,dianfei,endtime,starttime;
    private double startdegree,endegree,tzdegree,tzdianfei,jsdegree,jsdianfei;
	@Override
	public synchronized Vector<String> inputCheck(Object[] content, Account account) {
		// TODO Auto-generated method stub
		
		Vector<String> row = new Vector<String>();
		row = this.check09(content);
		if(row.isEmpty()){
			row = check01(content, account);
		}
		if(row.isEmpty()){
			row = check12(content);
		}
		if(row.isEmpty()){
			row = check10(content);
		}
		if(row.isEmpty()){
			row = check02(content);
		}
		
		///判断空
		if(row.isEmpty()){
			row = check03(content);
		}
		if(row.isEmpty()){
			row = check04(content);
		}
		if(row.isEmpty()){
			row = check05(content);
		}
		if(row.isEmpty()){
			row = check06(content);
		}
		if(row.isEmpty()){
			row = check07(content);
		}
		if(row.isEmpty()){
			row = check08(content);
		}
		if(row.isEmpty()){
			row = check11(content);
		}
		if(row.isEmpty()){
			row = check13(content);
		}
		if(row.isEmpty()){
			row = check14(content);
		}
		if(row.isEmpty()){
			row = inputConfirm(content);
		}
		return row;
	}

	@Override
	public synchronized Vector<String> check01(Object[] content, Account account) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Vector<String> row = new Vector<String>();
		String sql = "SELECT D.DBID, Z.DIANFEI "
				+ " FROM ZHANDIAN Z, DIANBIAO D  WHERE Z.ID = D.ZDID AND D.DBYT = 'dbyt01'"
				+ " AND D.DBQYZT = '1' AND Z.QYZT = '1' AND D.DBID =? AND D.ZDID=?"
				+ " AND EXISTS (SELECT 'A' FROM PER_AREA P WHERE P.AGCODE = Z.XIAOQU AND P.ACCOUNTID = ?)";

	    DataBase ds = new DataBase();
		try {
			conn = ds.getConnection();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			System.out.println(sql.toString());
			System.out.println(content[2]);
			System.out.println(content[0]);
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, content[2].toString().trim());
			ps.setString(2, content[0].toString().trim());
			ps.setString(3, account.getAccountId());
			rs = ps.executeQuery();
			while (rs.next()) {
				dianbiaoid = rs.getString("DBID");
				dianfei = rs.getString("dianfei").trim();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ds.free(rs, ps, conn);
		}
		if (dianbiaoid == null || "".equals(dianbiaoid)) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			dianbiaoid = content[2].toString().trim();
			row.add("未查到" + content[0].toString() + content[1].toString()
					+ "电表" + dianbiaoid);
		}
		
		return row;
	}

	@Override
	public synchronized Vector<String> check02(Object[] content) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM TB_CMCCDEGREE AMM WHERE AMM.AMMETERID_FK=? AND AMM.THISDEGREE=? AND AMM.THISDATETIME=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Vector<String> row = new Vector<String>();
		DataBase ds = new DataBase();
		try {
			ds.connectDb();
			conn = ds.getConnection();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ps.setString(1, dianbiaoid);
			ps.setString(2, startdegree+"");
			ps.setString(3, starttime);
			rs = ps.executeQuery();
			System.out.println(rs.next());
			if (rs.next()) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				dianbiaoid = content[2].toString().trim();
				row.add(content[0].toString() + content[1].toString() + "电表"
						+ dianbiaoid + "重复导入！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{ds.free(rs, ps, conn);}
		
		
		return row;
	}

	@Override
	public synchronized Vector<String> check03(Object[] content) {
		// TODO Auto-generated method stub
		Vector<String> row = new Vector<String>();
		if (content[5] == null || "".equals(content[5].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[1].toString() + "电表"
					+ dianbiaoid + "终止电表数为空。");
		} else {
			if (!Format.isNumber(content[5].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "终止电表数格式不正确");
			} else {
				endegree = Format.str_d(content[5].toString().trim());
			}
		}
		return row;
	}

	@Override
	public synchronized Vector<String> check04(Object[] content) {
		// TODO Auto-generated method stub
		Vector<String> row = new Vector<String>();
		if (content[6] == null || "".equals(content[6].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[1].toString() + "电表"
					+ dianbiaoid + "用电量调整为空。");
		} else {
			if (!Format.isNumber(content[6].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "用电量调整格式不正确");
			} else {
				tzdegree = Format.str_d(content[6].toString().trim());
			}
		}
		return row;
	}

	@Override
	public synchronized Vector<String> check05(Object[] content) {
		// TODO Auto-generated method stub
		Vector<String> row = new Vector<String>();
		if (content[7] == null || "".equals(content[7].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[1].toString() + "电表"
					+ dianbiaoid + "用电费调整为空。");
		} else {
			if (!Format.isNumber(content[7].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "用电费调整格式不正确");
			} else {
				tzdianfei = Format.str_d(content[7].toString().trim());
			}
		}
		return row;
	}

	@Override
	public Vector<String> check06(Object[] content) {
		// TODO Auto-generated method stub
		Vector<String> row = new Vector<String>();
		if (content[9] == null || "".equals(content[9].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "截止日期   为空");
		} else {
			if (!Format.isTime(content[9].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "截止日期   格式不正确");
			} else {
				if (Format.isTime02(content[9].toString().trim())) {
					endtime = Format.getTime(content[6].toString().trim());
				}else{
					endtime = content[6].toString().trim();
				}
			}
		}
		return row;
	}

	@Override
	public synchronized Vector<String> check07(Object[] content) {
		// TODO Auto-generated method stub
		Vector<String> row = new Vector<String>();
		if (content[11] == null || "".equals(content[11].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[1].toString() + "电表"
					+ dianbiaoid + "结算用电量为空。");
		} else {
			if (!Format.isNumber(content[11].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "结算用电量格式不正确");
			} else {
				jsdegree = Format.str_d(content[11].toString().trim());
			}
		}
		return row;
	}

	@Override
	public synchronized Vector<String> check08(Object[] content) {
		// TODO Auto-generated method stub
		Vector<String> row = new Vector<String>();
		if (content[12] == null || "".equals(content[12].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[1].toString() + "电表"
					+ dianbiaoid + "结算电费为空。");
		} else {
			if (!Format.isNumber(content[12].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "结算电费格式不正确");
			} else {
				jsdianfei = Format.str_d(content[12].toString().trim());
			}
		}
		return row;
	}

	@Override
	public synchronized Vector<String> inputConfirm(Object[] content) {
		// TODO Auto-generated method stub
		Vector<String> row = new Vector<String>();
		String dlsql = "INSERT INTO TB_CMCCDEGREE (AMMETERID_FK,LASTDATETIME,THISDATETIME,FLOATDEGREE,ACTUALDEGREE,UUID)VALUES(?,?,?,?,?,?)";
		String dfsql ="INSERT INTO TB_CMCCBILL (UNITPRICE,FLOATPAY,ACTUALPAY) VALUES(?,?,?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		DataBase ds = new DataBase();
		
		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int) (100 + Math.random()
				* (999 - 100 + 1)));
		String uuid = uuid2 + Long.toString(uuid1).substring(3);
		try {
			ds.connectDb();
			conn = ds.getConnection();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps = conn.prepareStatement(dlsql);
			ps.setString(1, dianbiaoid);
			ps.setString(2, starttime);
			ps.setString(3, endtime);
			ps.setString(4, tzdegree+"");
			ps.setString(5, jsdegree+"");
			ps.setString(6, uuid);
			
			ps1 = conn.prepareStatement(dfsql);
			ps1.setString(1, dianfei);
			ps1.setString(2, tzdianfei+"");
			ps1.setString(3, jsdianfei+"");
			
			conn.setAutoCommit(false);
			ps.execute();
			ps1.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[1].toString() + "电表"
					+ dianbiaoid + "插入失败");
			e.printStackTrace();
			
		}finally{ds.free(null, ps1, conn);ds.free(null, ps, null);}
		
		return row;
		
		
	}

	@Override
	public Vector<String> check09(Object[] content) {
		// TODO Auto-generated method stub
		Vector<String> row = new Vector<String>();
		if (content[0] == null || "".equals(content[0].toString().trim())||content[2]==null||"".equals(content[2].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[1].toString() + "电表"
					+ dianbiaoid + "站点编号或者电表ID 为空");
		} 
		return row;
	}

	@Override
	public synchronized Vector<String> check10(Object[] content) {
		// TODO Auto-generated method stub
		Vector<String> row = new Vector<String>();
		if (content[8] == null || "".equals(content[8].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "起始日期   为空");
		} else {
			if (!Format.isTime(content[8].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "起始日期   格式不正确");
			} else {
				if (Format.isTime02(content[8].toString().trim())) {
					starttime = Format.getTime(content[6].toString().trim());
				}else{
					starttime = content[8].toString().trim();
				}
			}
		}
		return row;
	}

	@Override
	public synchronized Vector<String> check11(Object[] content) {
		// TODO Auto-generated method stub
		Vector<String> row = new Vector<String>();
		if (content[8] != null && !"".equals(content[8].toString().trim())
				&& content[9] != null
				&& !"".equals(content[9].toString().trim())
				&& Format.isTime(content[8].toString().trim())
				&& Format.isTime(content[9].toString().trim())) {
			Date lasttime = Format.String2Time(content[8].toString().trim());
			Date thistime = Format.String2Time(content[9].toString().trim());
			if (lasttime.getTime() > thistime.getTime()) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "起始时间大于截止时间");
			}

		}
		return row;
	}

	@Override
	public synchronized Vector<String> check12(Object[] content) {
		// TODO Auto-generated method stub
		Vector<String> row = new Vector<String>();
		if (content[4] == null || "".equals(content[4].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[1].toString() + "电表"
					+ dianbiaoid + "结算电费为空。");
		} else {
			if (!Format.isNumber(content[4].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "结算电费格式不正确");
			} else {
				startdegree = Format.str_d(content[4].toString().trim());
			}
		}
		return row;
	}

	@Override
	public synchronized Vector<String> check13(Object[] content) {
		// TODO Auto-generated method stub
		double dlcount;
		Vector<String> row = new Vector<String>();
		dlcount = (endegree - startdegree)+tzdegree;
		dlcount = Format.d2(dlcount);
		if(dlcount != Format.d2(Format.str_d(content[11].toString().trim()))){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[1].toString() + "电表"
					+ dianbiaoid + "结算电量计算错误，系统计算为："+dlcount);
		}
		return row;
	}

	@Override
	public synchronized Vector<String> check14(Object[] content) {
		// TODO Auto-generated method stub
		double dfcount;
		Vector<String> row = new Vector<String>();
		double dlcount;
		dlcount = (endegree - startdegree)+tzdegree;
		dfcount = (dlcount * Format.str_d(dianfei))+tzdianfei;
		dfcount = Format.d2(dfcount);
		if(dfcount != Format.d2(Format.str_d(content[12].toString().trim()))){
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[1].toString() + "电表"
					+ dianbiaoid + "结算电费计算错误，系统计算为："+dfcount);
		}
		return row;
	}
	public static void main(String[] args) {
		double a = 1;
		double b = 1.0;
		System.out.println(a==b);
	}
	
}
