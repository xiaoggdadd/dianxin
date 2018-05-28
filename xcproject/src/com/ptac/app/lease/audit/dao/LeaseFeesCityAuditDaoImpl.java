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
			+ " DECODE(LF.COUNTRYAUDITSTATUS,'0','δ���','1','ͨ��','2','��ͨ��') COUNTRYAUDITSTATUS,LF.COUNTRYAUDITTIME,LF.COUNTRYAUDITOR,DECODE(LF.CITYAUDITSTATUS,'0','δ���','1','ͨ��','2','��ͨ��') CITYAUDITSTATUS,LF.CITYAUDITTIME,LF.CITYAUDITOR"
			+ " FROM ZHANDIAN ZD, DIANBIAO DB, LEASEBARGAIN LG, LEASEBARGAINFEES LF"
			+ " WHERE ZD.ID = DB.ZDID AND DB.DBID = LG.DBID_FK AND LG.LEASEID = LF.LEASEID_FK"
			+ " AND LF.COUNTRYAUDITSTATUS = '1' AND ZD.QYZT = '1' AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND DB.DBQYZT = '1'"
			+ whereStr
			+ " AND EXISTS (SELECT T.AGCODE FROM PER_AREA T WHERE T.AGCODE=ZD.XIAOQU AND T.ACCOUNTID = '"+accountid+"')";
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		System.out.println("���޷����м���˲�ѯ��" + sql);
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
				lf.setLeasename(rs.getString("LEASENAME"));//��ͬ����
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
		String msg = "��˵����Ϣʧ�ܣ�";
		String time = "to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";// ϵͳʱ��
		DataBase db = new DataBase();
		Connection conn = null;
		Statement st = null;
		StringBuffer sql1 = new StringBuffer();
		sql1.append("UPDATE LEASEBARGAINFEES LF SET LF.CITYAUDITSTATUS = '" + auditstatus + "'," 
				+ "LF.CITYAUDITOR = '" + personnal + "',LF.CITYAUDITTIME = " 
				+ time + " WHERE LF.LEASEFEEID IN (" + chooseIdStr1 + ")");
		try {
			conn = db.getConnection();
			if (chooseIdStr1 != "" && chooseIdStr1 != null) {// ���id��Ϊ����ִ��sql
				System.out.println("���޷����м���ˣ�"+sql1.toString());
				st = conn.createStatement();
				st.executeUpdate(sql1.toString());
			}
			if (bz.equals("1")) {
				msg = "���޷����м����ͨ���ɹ���";
			} else if (bz.equals("2")) {
				msg = "���޷����м���˲�ͨ���ɹ���";
			} else {
				msg = "���޷����м����ȡ����˳ɹ���";
			}
		} catch (Exception e) {// �쳣����
			e.printStackTrace();
		} finally {// �ͷ���Դ
			db.free(null, st, conn);
		}
		return msg;
	}

}
