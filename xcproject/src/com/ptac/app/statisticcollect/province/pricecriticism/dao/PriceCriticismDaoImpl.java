package com.ptac.app.statisticcollect.province.pricecriticism.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.statisticcollect.province.accountwithout.bean.AccountWithoutBean;
import com.ptac.app.statisticcollect.province.pricecriticism.bean.PriceCriticismBean;

public class PriceCriticismDaoImpl implements  PriceCriticismDao{
	public List<PriceCriticismBean> checkPrice(String wherestr,String cbbly,String cbble){
		List<PriceCriticismBean> list = new ArrayList<PriceCriticismBean>();
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql.append(" SELECT RNDIQU(HZ.SHI),COUNT(HZ.ID),COUNT(CASE WHEN ((HZ.DJ-HZ.BZDJ)/HZ.BZDJ)*100>'"+cbbly+"' THEN HZ.ID END)BLY," +
				"COUNT(CASE WHEN ((HZ.DJ-HZ.BZDJ)/HZ.BZDJ)*100>'"+cbble+"' THEN HZ.ID END)BLE " +
				"FROM (SELECT Z.SHI,Z.ID,ROUND((SUM(E.ACTUALPAY)/SUM(A.BLHDL)),2)DJ," +
				"(CASE WHEN Z.GDFS='gdfs06' OR Z.GDFS='gdfs07' THEN  " +
				"(CASE WHEN (Z.PROPERTY='zdsx06'AND Z.STATIONTYPE='StationType12') " +
				"OR (Z.PROPERTY='zdsx01'AND Z.STATIONTYPE='StationType20') OR Z.PROPERTY='zdsx02' OR Z.PROPERTY='zdsx05' " +
				" THEN S.ZSZHI ELSE S.ZFZHI END) ELSE (CASE WHEN (Z.PROPERTY='zdsx06'AND Z.STATIONTYPE='StationType12') " +
				" OR (Z.PROPERTY='zdsx01'AND Z.STATIONTYPE='StationType20') OR Z.PROPERTY='zdsx02'OR Z.PROPERTY='zdsx05' " +
				"THEN S.ZSZHUAN ELSE S.ZFZHUAN END)END)AS BZDJ FROM ZHANDIAN  Z,DIANBIAO D,AMMETERDEGREES A,ELECTRICFEES E,STANDARDUNITPRICE S " +
				"WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND Z.SHI=S.CITY " +
				"AND Z.SHSIGN='1' AND Z.XUNI='0' AND Z.CAIJI='0' AND Z.JZNAME NOT LIKE '%回收%' AND Z.STATIONTYPE<>'StationType35' " +
				"AND Z.STATIONTYPE<>'StationType36' AND Z.STATIONTYPE<>'StationType37' AND Z.STATIONTYPE<>'StationType38' AND Z.STATIONTYPE<>'StationType39' " +
				"AND Z.STATIONTYPE<>'StationType40'  AND Z.STATIONTYPE<>'StationType41' AND Z.STATIONTYPE<>'StationType42'" +
				" AND A.BLHDL>0 AND E.ACTUALPAY>0 "+wherestr+" " +
				"GROUP BY Z.ID,Z.PROPERTY,Z.STATIONTYPE,Z.GDFS,S.ZSZHI,S.ZFZHI,S.ZSZHUAN,S.ZFZHUAN,Z.SHI,S.CITY)HZ GROUP BY HZ.SHI ORDER BY HZ.SHI");

		System.out.println("电价超标站点通报:"+sql.toString());
		
		try {
			db.connectDb();//创建数据库连接
			conn = db.getConnection();//获取连接对象
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				

				
				PriceCriticismBean bean = new PriceCriticismBean();
				String bzzds = rs.getString(2)!=null?rs.getString(2):"0";
				String djcby = rs.getString(3)!=null?rs.getString(3):"0";
				String dbcbe = rs.getString(4)!=null?rs.getString(4):"0";
				double cby = 0.0;//超标占比1
				double cbe = 0.0;//超标占比2
				if(bzzds.equals("0")||bzzds==null||bzzds.equals("")){
					cby = 0.0;
					cbe = 0.0;
				}else{
				 cby = (Format.str_d(djcby)/Format.str_d(bzzds))*100;
				 cbe = (Format.str_d(dbcbe)/Format.str_d(bzzds))*100;
				}
				
				
				String cbzdzby = cby+"";
				String cbzdzbe = cbe+"";
				
				bean.setShi(rs.getString(1)!=null?rs.getString(1):"");//城市
				bean.setBzzds(bzzds);//报账站点个数
				bean.setDjcby(djcby);//电价超标1
				bean.setDbcbe(dbcbe);//电价超标2
				bean.setCbzdzby(Format.str2(cbzdzby));//电价超标占比1
				bean.setCbzdzbe(Format.str2(cbzdzbe));//电价超标占比2
				list.add(bean);
				
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public String[] getSum(List<PriceCriticismBean> list) {
		
		String[] sum = new String[5];
		int length = list.size();
		PriceCriticismBean bean = null;
		int zdcountsum = 0;//报账站点数合计
		int cbycountsum = 0;//电价超标20%的站点
		int cbecountsum = 0;//电价超标50%的站点
		Double zbycountsum = 0.00;;//电价超标20%的站点 占比
		Double zbecountsum = 0.00;;//电价超标50%的站点 占比
		
		for( int i = 0 ; i < length ; i++ ){
			bean = list.get(i);
			Double zdcounthj = Double.parseDouble(bean.getBzzds());
			Double cbycounthj = Double.parseDouble(bean.getDjcby());
			Double cbecounthj = Double.parseDouble(bean.getDbcbe());
			Double zbycounthj = Double.parseDouble(bean.getCbzdzby());
			Double zbecounthj = Double.parseDouble(bean.getCbzdzbe());
			zdcountsum += zdcounthj;
			cbycountsum += cbycounthj;
			cbecountsum += cbecounthj;
			zbycountsum += zbycounthj;
			zbecountsum += zbecounthj;
		}
		sum[0] = String.valueOf(zdcountsum);
		sum[1] = String.valueOf(cbycountsum);
		sum[2] = String.valueOf(cbecountsum);
		sum[3] = String.valueOf(Format.d2(zbycountsum));
		sum[4] = String.valueOf(Format.d2(zbecountsum));
		return sum;
	}
}
