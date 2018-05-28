package com.ptac.app.lease.query.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.lease.query.bean.Leasebargainfees;
import com.ptac.app.lease.query.bean.LeasefeesQuery;

public class LeaseFeesQueryDaoImpl implements LeaseFeesQueryDao {

	@Override
	public List<LeasefeesQuery> getLeasefeesQuery(String whereStr,
			String loginId) {
		ArrayList<LeasefeesQuery> list = new ArrayList<LeasefeesQuery>();
		String sql = "SELECT RNDIQU(ZD.SHI) SHI ,RNDIQU(ZD.XIAN) XIAN ,RNDIQU(ZD.XIAOQU) XIAOQU," 
			+ " ZD.JZNAME,LG.LEASENAME,LF.ACCOUNTMONTH,LF.PAYMONEY,LF.PAYBEGINTIME,LF.PAYENDTIME,LF.PAYHANDLER,RTNAME(ZD.STATIONTYPE) STATIONTYPE," 
			+ " DECODE(LF.COUNTRYAUDITSTATUS,'0','未审核','1','通过','2','不通过') COUNTRYAUDITSTATUS,LF.COUNTRYAUDITTIME,LF.COUNTRYAUDITOR,DECODE(LF.CITYAUDITSTATUS,'0','未审核','1','通过','2','不通过') CITYAUDITSTATUS,LF.CITYAUDITTIME,LF.CITYAUDITOR"
			+ " FROM ZHANDIAN ZD, DIANBIAO DB, LEASEBARGAIN LG, LEASEBARGAINFEES LF"
			+ " WHERE ZD.ID = DB.ZDID AND DB.DBID = LG.DBID_FK AND LG.LEASEID = LF.LEASEID_FK"
			+ " AND ZD.QYZT = '1' AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND DB.DBQYZT = '1'"
			+ whereStr
			+ " AND EXISTS (SELECT T.AGCODE FROM PER_AREA T WHERE T.AGCODE=ZD.XIAOQU AND T.ACCOUNTID = '"+loginId+"')";
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		System.out.println("租赁费用明细查询：" + sql);
		db = new DataBase();
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				LeasefeesQuery lf = new LeasefeesQuery();
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

}
