package com.ptac.app.lease.query.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;
import com.ptac.app.lease.query.bean.Leasebargainfees;

public class LeaseFeesDaoImpl implements LeaseFeesDao {

	@Override
	public ArrayList getZhandianAndHetong(String loginId, String jzname,
			String str) {
		DataBase db = new DataBase();
		Connection connt = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT Z.JZNAME,Z.ID ZDID,LB.LEASENAME,LB.LEASEID FROM ZHANDIAN Z, DIANBIAO D,LEASEBARGAIN LB "
		 + " WHERE Z.ID = D.ZDID AND D.DBID = LB.DBID_FK AND Z.SHSIGN = '1' AND Z.QYZT = '1' AND Z.XUNI = '0' "
		 + " AND Z.CAIJI = '0' AND D.DBYT = 'dbyt01' AND D.DBQYZT = '1' AND LB.AUDITSTATUS = '1' "
		 + str
		 + " AND XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"') "
		 + "  AND Z.JZNAME LIKE '%"+ jzname +"%' ORDER BY JZNAME");
		try {
			System.out.println("模糊查询站点:"+sql);
			connt = db.getConnection();
			st = connt.createStatement();
			rs = st.executeQuery(sql.toString());
			Query query = new Query();
			list = query.query(rs);	
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			db.free(rs, st, connt);
		}
		return list;
	}
	@Override
	public Leasebargainfees getBaseInfo(String zdid,String leaseid){
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		Leasebargainfees  lbf = new Leasebargainfees();
		String sql = "SELECT RNDIQU(ZD.SHI) SHI, RNDIQU(ZD.XIAN) XIAN , RNDIQU(ZD.XIAOQU) XIAOQU, RTNAME(ZD.STATIONTYPE) STATIONTYPE,"
			       + " ZD.JZNAME, ZD.AREA, ZD.ADDRESS, LB.LEASEID, LB.LEASENUM, LB.LEASENAME, LB.BEGINTIME, LB.ENDTIME, LB.MONEY,"
			       + " LB.AGELIMIT FROM ZHANDIAN ZD,DIANBIAO DB,LEASEBARGAIN LB "
			       + " WHERE ZD.ID = DB.ZDID AND DB.DBID = LB.DBID_FK AND ZD.ID = '"
			       + zdid +"' AND LB.LEASEID = '"+leaseid+"'";
		DataBase db = new DataBase();
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			System.out.println("租赁合同基本信息查询："+sql);
			rs = st.executeQuery(sql);
			if(rs.next()){
				lbf.setShi(rs.getString("SHI"));
				lbf.setXian(rs.getString("XIAN"));
				lbf.setXiaoqu(rs.getString("XIAOQU"));
				lbf.setStationtype(rs.getString("STATIONTYPE"));
				lbf.setZdname(rs.getString("JZNAME"));
				lbf.setArea(rs.getDouble("AREA"));
				lbf.setAddress(rs.getString("ADDRESS"));
				lbf.setLeaseid_fk(rs.getString("LEASEID"));
				lbf.setLeasenum(rs.getString("LEASENUM"));
				lbf.setLeasename(rs.getString("LEASENAME"));
				lbf.setBegintime(rs.getString("BEGINTIME"));
				lbf.setEndtime(rs.getString("ENDTIME"));
				lbf.setMoney(rs.getDouble("MONEY"));
				lbf.setAgelimit(rs.getDouble("AGELIMIT"));
				lbf.setPaybegintime(rs.getString("BEGINTIME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return lbf;
	}
	public String addLeaseFees(Leasebargainfees leasefees){
		String sql = "INSERT INTO LEASEBARGAINFEES(LEASEID_FK,PAYBEGINTIME,PAYENDTIME,PAYMONEY,PAYHANDLER,ACCOUNTMONTH,COUNTRYAUDITSTATUS,CITYAUDITSTATUS,INPUTTIME) " 
				+" VALUES("+leasefees.getLeaseid_fk()+",'"+leasefees.getPaybegintime()+"','"+leasefees.getPayendtime()+"',"+leasefees.getPaymoney()+",'"+leasefees.getPayhandler()+"','"
				+leasefees.getAccountmonth()+"','0','0',to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'))";
		String msg = "保存租赁费用失败！请重试或与管理员联系！";
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		int count = 0;
		db = new DataBase();
		System.out.println("保存租赁费用："+sql);
		try {
			connc  = db.getConnection();
			st = connc.createStatement();
			count = st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(null, st, connc);
		}
		return count>0?"保存租赁费用成功！":msg;
	}
	public String getLastPayendtime(String leaseid_fk){
		String sql = "SELECT PE.PAYENDTIME FROM (SELECT LF.PAYENDTIME FROM LEASEBARGAINFEES LF WHERE LF.CITYAUDITSTATUS = '1' AND LF.LEASEID_FK = '"+leaseid_fk+"' ORDER BY LF.INPUTTIME DESC) PE WHERE ROWNUM = 1";
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		String payendtime=null;
		System.out.println("上次缴费的结束时间（本次缴费开始时间）：" + sql);
		db = new DataBase();
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				payendtime = rs.getString("PAYENDTIME");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		
		return payendtime;
	}
	public List<Leasebargainfees> selectLeaseFees(String whereStr,String loginId){
		ArrayList<Leasebargainfees> list = new ArrayList<Leasebargainfees>();
		String sql = "SELECT  LF.LEASEFEEID,RNDIQU(ZD.SHI) SHI ,RNDIQU(ZD.XIAN) XIAN ,RNDIQU(ZD.XIAOQU) XIAOQU," 
			+ " ZD.JZNAME,LG.LEASENAME,LF.ACCOUNTMONTH,LF.PAYMONEY,LF.PAYBEGINTIME,LF.PAYENDTIME,LF.PAYHANDLER,RTNAME(ZD.STATIONTYPE) STATIONTYPE" 
			+ " FROM ZHANDIAN ZD, DIANBIAO DB, LEASEBARGAIN LG, LEASEBARGAINFEES LF"
			+ " WHERE ZD.ID = DB.ZDID AND DB.DBID = LG.DBID_FK AND LG.LEASEID = LF.LEASEID_FK"
			+ " AND ZD.QYZT = '1' AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND DB.DBQYZT = '1'"
			+ " AND LF.COUNTRYAUDITSTATUS <> '1'"
			+ whereStr
			+ " AND EXISTS (SELECT T.AGCODE FROM PER_AREA T WHERE T.AGCODE=ZD.XIAOQU AND T.ACCOUNTID = '263')";
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		System.out.println("租赁费用查询：" + sql);
		db = new DataBase();
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Leasebargainfees lf = new Leasebargainfees();
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
	public String delLeaseFee(String leasefeeid) {
		String msg = "删除租赁合同费用信息失败！";
		String sql = "DELETE FROM LEASEBARGAINFEES LF WHERE LF.LEASEFEEID IN("+leasefeeid+")";
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		int count = 0;
		System.out.println("删除租赁合同费用信息：" + sql);
		db = new DataBase();
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			count = st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(null, st, connc);
		}
		return count>0?"删除租赁合同费用信息成功！":msg;
	}
	@Override
	public Leasebargainfees getLeaseFeeInfo(String leasefeeid) {
		
		Leasebargainfees lf = new Leasebargainfees();
		String sql = "SELECT LF.LEASEFEEID,RNDIQU(ZD.SHI) SHI, RNDIQU(ZD.XIAN) XIAN , RNDIQU(ZD.XIAOQU) XIAOQU, RTNAME(ZD.STATIONTYPE) STATIONTYPE," 
				+ " ZD.JZNAME, ZD.AREA, ZD.ADDRESS, LB.LEASENUM, LB.LEASENAME, LB.BEGINTIME, LB.ENDTIME, LB.MONEY, LB.AGELIMIT," 
				+ " LF.PAYMONEY,LF.PAYBEGINTIME,LF.PAYENDTIME,LF.PAYHANDLER,RTNAME(ZD.STATIONTYPE) STATIONTYPE"
				+ " FROM ZHANDIAN ZD,DIANBIAO DB,LEASEBARGAIN LB,LEASEBARGAINFEES LF  WHERE ZD.ID = DB.ZDID AND DB.DBID = LB.DBID_FK AND LB.LEASEID=LF.LEASEID_FK AND LF.LEASEFEEID = '"+leasefeeid+"'";
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		System.out.println("租赁费用修改查询：" + sql);
		db = new DataBase();
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				lf.setLeasefeeid(rs.getString("LEASEFEEID"));
				lf.setShi(rs.getString("SHI"));
				lf.setXian(rs.getString("XIAN"));
				lf.setXiaoqu(rs.getString("XIAOQU"));
				lf.setZdname(rs.getString("JZNAME"));
				lf.setArea(rs.getDouble("AREA"));
				lf.setAddress(rs.getString("ADDRESS"));
				lf.setLeasenum(rs.getString("LEASENUM"));
				lf.setLeasename(rs.getString("LEASENAME"));
				lf.setBegintime(rs.getString("BEGINTIME"));
				lf.setEndtime(rs.getString("ENDTIME"));
				lf.setMoney(rs.getDouble("MONEY"));
				lf.setAgelimit(rs.getDouble("AGELIMIT"));
				lf.setPaymoney(rs.getDouble("PAYMONEY"));
				lf.setPaybegintime(rs.getString("PAYBEGINTIME"));
				lf.setPayendtime(rs.getString("PAYENDTIME"));
				lf.setPayhandler(rs.getString("PAYHANDLER"));
				lf.setStationtype(rs.getString("STATIONTYPE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return lf;
	}
	public String updateLeaseFees(Leasebargainfees leasefees){
		String sql = "UPDATE LEASEBARGAINFEES LF SET PAYBEGINTIME='" + leasefees.getPaybegintime() + "', PAYENDTIME='"
		+ leasefees.getPayendtime() + "', PAYMONEY=" + leasefees.getPaymoney() + ", PAYHANDLER='" + leasefees.getPayhandler()
				+ "',ACCOUNTMONTH='" + leasefees.getAccountmonth() + "', INPUTTIME= to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS') WHERE LF.LEASEFEEID='" + leasefees.getLeasefeeid() + "'";
		String msg = "修改租赁费用失败！请重试或与管理员联系！";
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		int count = 0;
		db = new DataBase();
		System.out.println("修改租赁费用："+sql);
		try {
			connc  = db.getConnection();
			st = connc.createStatement();
			count = st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(null, st, connc);
		}
		return count>0?"修改租赁费用成功！":msg;
	}
}
