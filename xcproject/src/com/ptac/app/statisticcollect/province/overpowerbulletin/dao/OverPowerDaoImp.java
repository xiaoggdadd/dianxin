package com.ptac.app.statisticcollect.province.overpowerbulletin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.statisticcollect.province.overpowerbulletin.bean.OverPowerBean;

public class OverPowerDaoImp implements OverPowerDao {

	@Override
	public List<OverPowerBean> queryExport(String string, String loginId) {
		
		List<OverPowerBean> list = new ArrayList<OverPowerBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT RNDIQU(AA.SHI) SHI,AA.ZBZDL,AA.CBDL,ROUND(DECODE(ZBZDL,0,0,AA.CBDL/ZBZDL*100),2) CBZB FROM " 
				 + "(SELECT SHI SHI,ROUND(SUM(BLHDL)/10000,2) ZBZDL,ROUND((SUM(BLHDL)-SUM(BZZ))/10000,2) CBDL" 
				 + " FROM MQY_DLDF WHERE JZNAME NOT LIKE '%回收%' "
				 + " AND STATIONTYPE NOT IN('StationType35','StationType36','StationType37','StationType38','StationType39','StationType40','StationType41','StationType42')"
				 + " AND ACTUALPAY>0  AND BLHDL>0  AND CAIJI = '0' AND XUNI = '0' AND SHSIGN = '1' " + string + " GROUP BY SHI ORDER BY SHI) AA ");
//				 + " AND (XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "')) GROUP BY SHI");
		
		System.out.println("超标电量通报信息查询："+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				OverPowerBean bean = new OverPowerBean();
				
				String city = rs.getString("SHI")==null?"":rs.getString("SHI");//地市
				String zbzdl = rs.getString("ZBZDL")==null?"":rs.getString("ZBZDL");//总报账电量(万度) 
				String cbdl = rs.getString("CBDL")==null?"":rs.getString("CBDL");//超标电量(万度) 
				String cbzb = rs.getString("CBZB")==null?"":rs.getString("CBZB");//超标电量占比(%) 
				
				bean.setCity(city);
				bean.setZbzdl(Format.str2(zbzdl));
				bean.setCbdl(Format.str2(cbdl));
				bean.setCbzb(Format.str2(cbzb));
				
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
	public String[] getSum(List<OverPowerBean> list) {
		
		String[] sum = new String[5];
		int length = list.size();
		OverPowerBean bean = null;
		Double zbzdlsum = 0.00;//总报账电量合计
		Double cbdlsum = 0.00;//超标电量合计
		Double cbzbsum = 0.00;//超标电量占比合计
		
		for( int i = 0 ; i < length ; i++ ){
			bean = list.get(i);
			Double zbzdlhj = Double.parseDouble(bean.getZbzdl());//总报账电量(万度) 
			Double cbdlhj = Double.parseDouble(bean.getCbdl());//超标电量(万度) 
			zbzdlsum += zbzdlhj;
			cbdlsum += cbdlhj;
		}
			cbzbsum = zbzdlsum.equals(0)?0:(cbdlsum/zbzdlsum*100);
		sum[0] = String.valueOf(Format.d2(zbzdlsum));
		sum[1] = String.valueOf(Format.d2(cbdlsum));
		sum[2] = String.valueOf(Format.d2(cbzbsum));
		return sum;
	}
}
