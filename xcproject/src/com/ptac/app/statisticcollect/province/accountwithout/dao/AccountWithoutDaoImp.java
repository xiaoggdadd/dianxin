package com.ptac.app.statisticcollect.province.accountwithout.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.statisticcollect.province.accountwithout.bean.AccountWithoutBean;

public class AccountWithoutDaoImp implements AccountWithoutDao {

	@Override
	public List<AccountWithoutBean> queryExport(String string, String loginId) {
		
		List<AccountWithoutBean> list = new ArrayList<AccountWithoutBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT RNDIQU(Z.SHI) SHI,COUNT(DISTINCT Z.ZDID) ZDCOUNT,"
				 + "COUNT(DISTINCT(CASE WHEN TIANSHU > 180 THEN ZDID ELSE NULL END)) COUNTL,"
				 + "((COUNT(DISTINCT(CASE WHEN TIANSHU > 180 THEN ZDID ELSE NULL END)))/(COUNT(DISTINCT Z.ZDID))*100) COUNTLZB,"
				 + "COUNT(DISTINCT(CASE WHEN TIANSHU > 365 THEN ZDID ELSE NULL END)) COUNTS,"
				 + "((COUNT(DISTINCT(CASE WHEN TIANSHU > 365 THEN ZDID ELSE NULL END)))/(COUNT(DISTINCT Z.ZDID))*100) COUNTSZB"
				 + " FROM ZANGU Z WHERE 1 = 1 "
				 + string +" GROUP BY SHI");
//				 + " AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "')) GROUP BY SHI");
		
		System.out.println("����δ���˻�����Ϣ��ѯ��"+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				AccountWithoutBean bean = new AccountWithoutBean();
				
				String city = rs.getString("SHI")==null?"":rs.getString("SHI");//����
				String zdcount = rs.getString("ZDCOUNT")==null?"":rs.getString("ZDCOUNT");//����վ����(��)
				String countl = rs.getString("COUNTL")==null?"":rs.getString("COUNTL");//����(6��������)δ����վ����(��)
				String countlzb = rs.getString("COUNTLZB")==null?"":rs.getString("COUNTLZB");//δ����վռ��(%) 
				String counts = rs.getString("COUNTS")==null?"":rs.getString("COUNTS");//����(12��������)δ����վ����(��)
				String countszb = rs.getString("COUNTSZB")==null?"":rs.getString("COUNTSZB");//δ����վռ��(%)
				
				bean.setCity(city);
				bean.setZdcount(zdcount);
				bean.setCountl(countl);
				bean.setCountlzb(Format.str2(countlzb));
				bean.setCounts(counts);
				bean.setCountszb(Format.str2(countszb));
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public String[] getSum(List<AccountWithoutBean> list) {
		
		String[] sum = new String[5];
		int length = list.size();
		AccountWithoutBean bean = null;
		long zdcountsum = 0;//����վ�����ϼ�
		long countlsum = 0;//����(6��������)δ����վ�����ϼ�
		double countlzbsum = 0.00;//δ����վռ�Ⱥϼ�
		long countssum = 0;//����(12��������)δ����վ�����ϼ�
		double countszbsum = 0.00;//δ����վռ�Ⱥϼ�
		
		for( int i = 0 ; i < length ; i++ ){
			bean = list.get(i);
			long zdcounthj = Long.parseLong(bean.getZdcount());
			long countlhj = Long.parseLong(bean.getCountl());
			long countshj = Long.parseLong(bean.getCounts());
			zdcountsum += zdcounthj;
			countlsum += countlhj;
			countssum += countshj;
		}
		countlzbsum = zdcountsum == 0 ? 0 : ((double)countlsum/(double)zdcountsum*100);
		countszbsum = zdcountsum == 0 ? 0 : ((double)countssum/(double)zdcountsum*100);
		sum[0] = String.valueOf(zdcountsum);
		sum[1] = String.valueOf(countlsum);
		sum[2] = String.valueOf(Format.d2(countlzbsum));
		sum[3] = String.valueOf(countssum);
		sum[4] = String.valueOf(Format.d2(countszbsum));
		return sum;
	}
}
