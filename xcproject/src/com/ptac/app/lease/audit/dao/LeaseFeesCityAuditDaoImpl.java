package com.ptac.app.lease.audit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.lease.audit.bean.LeasefeesCityAudit;

public class LeaseFeesCityAuditDaoImpl implements LeaseFeesCityAuditDao{

	@Override
	public List<LeasefeesCityAudit> getCityAuditLeaseFees(
			String whereStr, String accountid) {
		List<LeasefeesCityAudit> list = new ArrayList<LeasefeesCityAudit>();
		String sql = "SELECT LF.LEASEFEEID, RNDIQU(ZD.SHI) SHI ,RNDIQU(ZD.XIAN) XIAN ,RNDIQU(ZD.XIAOQU) XIAOQU," 
			+ " ZD.JZNAME,LG.LEASENAME,LF.ACCOUNTMONTH,LF.PAYMONEY,LF.PAYBEGINTIME,LF.PAYENDTIME,LF.PAYHANDLER,RTNAME(ZD.STATIONTYPE) STATIONTYPE," 
			+ " DECODE(LF.COUNTRYAUDITSTATUS,'0','未审核','1','通过','2','不通过') COUNTRYAUDITSTATUS,LF.COUNTRYAUDITTIME,LF.COUNTRYAUDITOR,DECODE(LF.CITYAUDITSTATUS,'0','未审核','1','通过','2','不通过') CITYAUDITSTATUS,LF.CITYAUDITTIME,LF.CITYAUDITOR"
			+ " FROM ZHANDIAN ZD, DIANBIAO DB, LEASEBARGAIN LG, LEASEBARGAINFEES LF"
			+ " WHERE ZD.ID = DB.ZDID AND DB.DBID = LG.DBID_FK AND LG.LEASEID = LF.LEASEID_FK"
			+ " AND LF.COUNTRYAUDITSTATUS = '1' AND ZD.QYZT = '1' AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND DB.DBQYZT = '1'"
			+ whereStr
			+ " AND EXISTS (SELECT T.AGCODE FROM PER_AREA T WHERE T.AGCODE=ZD.XIAOQU AND T.ACCOUNTID = '"+accountid+"')";
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		System.out.println("租赁费用市级审核查询：" + sql);
		db = new DataBase();
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				LeasefeesCityAudit lf = new LeasefeesCityAudit();
				lf.setLeasefeeid(rs.getString("LEASEFEEID"));
				lf.setShi(rs.getString("SHI"));
				lf.setXian(rs.getString("XIAN"));
				lf.setXiaoqu(rs.getString("XIAOQU"));
				lf.setZdname(rs.getString("JZNAME"));
				lf.setLeasename(rs.getString("LEASENAME"));//合同名称
				lf.setAccountmonth(rs.getString("ACCOUNTMONTH"));
				lf.setPaymoney(rs.getDouble("PAYMONEY"));
				lf.setPaybegintime(rs.getString("PAYBEGINTIME"));
				lf.setPayendtime(rs.getString("PAYENDTIME"));
				lf.setPayhandler(rs.getString("PAYHANDLER"));
				lf.setStationtype(rs.getString("STATIONTYPE"));
				lf.setCountryauditstatus(rs.getString("COUNTRYAUDITSTATUS"));
				lf.setCountryaudittime(rs.getString("COUNTRYAUDITTIME"));
				lf.setCountryauditor(rs.getString("COUNTRYAUDITOR"));
				lf.setCityauditstatus(rs.getString("CITYAUDITSTATUS"));
				lf.setCityaudittime(rs.getString("CITYAUDITTIME"));
				lf.setCityauditor(rs.getString("CITYAUDITOR"));
				list.add(lf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return list;
	}

	@Override
	public String CheckCityFees(String personnal, String auditstatus,
			String chooseIdStr1,String bz) {
		String msg = "审核电费信息失败！";
		String time = "to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";// 系统时间
		DataBase db = new DataBase();
		Connection conn = null;
		Statement st = null;
		StringBuffer sql1 = new StringBuffer();
		sql1.append("UPDATE LEASEBARGAINFEES LF SET LF.CITYAUDITSTATUS = '" + auditstatus + "'," 
				+ "LF.CITYAUDITOR = '" + personnal + "',LF.CITYAUDITTIME = " 
				+ time + " WHERE LF.LEASEFEEID IN (" + chooseIdStr1 + ")");
		try {
			conn = db.getConnection();
			if (chooseIdStr1 != "" && chooseIdStr1 != null) {// 如果id不为空则执行sql
				System.out.println("租赁费用市级审核："+sql1.toString());
				st = conn.createStatement();
				st.executeUpdate(sql1.toString());
			}
			if (bz.equals("1")) {
				msg = "租赁费用市级审核通过成功！";
			} else if (bz.equals("2")) {
				msg = "租赁费用市级审核不通过成功！";
			} else {
				msg = "租赁费用市级审核取消审核成功！";
			}
		} catch (Exception e) {// 异常处理
			e.printStackTrace();
		} finally {// 释放资源
			db.free(null, st, conn);
		}
		return msg;
	}

}
