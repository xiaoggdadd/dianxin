package com.ptac.app.statisticcollect.province.avgpricebulletin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.statisticcollect.province.avgpricebulletin.bean.AvgPriceBean;

public class AvgPriceDaoImp implements AvgPriceDao {

	@Override
	public List<AvgPriceBean> queryExport(String string, String loginId) {
		
		List<AvgPriceBean> list = new ArrayList<AvgPriceBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT RNDIQU(SHI) SHI,(SUM(ACTUALPAY)/SUM(BLHDL)) PJDJ," 
				 + "((SUM(CASE WHEN GDFS IN ('gdfs05', 'gdfs08') THEN ACTUALPAY ELSE NULL END))/"
				 + " (SUM(CASE WHEN GDFS IN ('gdfs05', 'gdfs08') THEN BLHDL ELSE NULL END))) ZGDDJ"
				 + " FROM MQY_DLDF WHERE JZNAME NOT LIKE '%回收%' "
				 + " AND STATIONTYPE NOT IN('StationType35','StationType36','StationType37','StationType38','StationType39','StationType40','StationType41','StationType42')"
				 + " AND ACTUALPAY>0 AND BLHDL>0 AND CAIJI = '0' AND XUNI = '0' AND SHSIGN = '1' " + string + " GROUP BY SHI");
//				 + " AND (XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "')) GROUP BY SHI");
		
		System.out.println("平均单价通报信息查询："+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				AvgPriceBean bean = new AvgPriceBean();
				
				String city = rs.getString("SHI")==null?"":rs.getString("SHI");//地市
				String pjdj = rs.getString("PJDJ")==null?"":rs.getString("PJDJ");//平均电价(元/度) 
				String zgddj = rs.getString("ZGDDJ")==null?"":rs.getString("ZGDDJ");//转供电平均电价(元/度) 
				
				bean.setCity(city);
				bean.setPjdj(Format.str4(pjdj));
				bean.setZgddj(Format.str4(zgddj));
				
				if("济南市".equals(city) || "13701".equals(city)){
					bean.setJcdj("0.8174");//基础电价(元/度)
				}else if("青岛市".equals(city) || "13702".equals(city)){
					bean.setJcdj("0.9048");
				}else if("淄博市".equals(city) || "13703".equals(city)){
					bean.setJcdj("0.8174");
				}else if("枣庄市".equals(city) || "13704".equals(city)){
					bean.setJcdj("0.8174");
				}else if("东营市".equals(city) || "13705".equals(city)){
					bean.setJcdj("0.8174");
				}else if("烟台市".equals(city) || "13706".equals(city)){
					bean.setJcdj("0.8438");
				}else if("潍坊市".equals(city) || "13707".equals(city)){
					bean.setJcdj("0.8438");
				}else if("济宁市".equals(city) || "13708".equals(city)){
					bean.setJcdj("0.8174");
				}else if("泰安市".equals(city) || "13709".equals(city)){
					bean.setJcdj("0.8174");
				}else if("威海市".equals(city) || "13710".equals(city)){
					bean.setJcdj("0.8438");
				}else if("日照市".equals(city) || "13711".equals(city)){
					bean.setJcdj("0.8438");
				}else if("莱芜市".equals(city) || "13712".equals(city)){
					bean.setJcdj("0.8174");
				}else if("临沂市".equals(city) || "13713".equals(city)){
					bean.setJcdj("0.8438");
				}else if("德州市".equals(city) || "13714".equals(city)){
					bean.setJcdj("0.8174");
				}else if("聊城市".equals(city) || "13715".equals(city)){
					bean.setJcdj("0.8174");
				}else if("滨州市".equals(city) || "13716".equals(city)){
					bean.setJcdj("0.8174");
				}else if("菏泽市".equals(city) || "13717".equals(city)){
					bean.setJcdj("0.7748");
				}else{
					bean.setJcdj("0.8174");
				}
				
				Double jd = Format.str_d(bean.getJcdj());
				Double pd = Format.str_d(bean.getPjdj());
				Double zd = Format.str_d(bean.getZgddj());
				
				//平均电价偏离度：(平均电价—基础单价)/基础单价*100%
				Double pdl = (pd - jd)/jd * 100; 
				bean.setPjdjpld(Format.str2(String.valueOf(pdl)));
				
				//转供电平均电价偏离度：(转供电平均电价-基础电价)/基础单价*100%
				Double zdl = (zd - jd)/jd * 100; 
				bean.setZgdpld(Format.str2(String.valueOf(zdl)));
				
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
	public String[] getSum(List<AvgPriceBean> list) {
		
		String[] sum = new String[5];
		int length = list.size();
		AvgPriceBean bean = null;
		double jcdjsum = 0.00;//基础电价合计
		double pjdjsum = 0.00;//平均电价合计
		double pjdjpldsum = 0.00;//平均电价偏离度合计 
		double zgddjsum = 0.00;//转供电平均电价合计
		double zgdpldsum = 0.00;//转供电平均电价偏离度合计
		
		
		for( int i = 0 ; i < length ; i++ ){
			bean = list.get(i);
			Double jcdjhj = Double.parseDouble(bean.getJcdj());
			Double pjdjhj = Double.parseDouble(bean.getPjdj());
			Double zgddjhj = Double.parseDouble(bean.getZgddj());
			jcdjsum += jcdjhj;
			pjdjsum += pjdjhj;
			zgddjsum += zgddjhj;
		}

		sum[0] = length==0?"0":String.valueOf(Format.d4(jcdjsum/length));
		sum[1] = length==0?"0": String.valueOf(Format.d4(pjdjsum/length));
		pjdjpldsum = Format.str_d(sum[0])==0?0:((Format.str_d(sum[1])-Format.str_d(sum[0]))/Format.str_d(sum[0])*100);
		sum[2] = String.valueOf(Format.d2(pjdjpldsum));
		sum[3] = length==0?"0": String.valueOf(Format.d4(zgddjsum/length));
		zgdpldsum = Format.str_d(sum[0])==0?0:((Format.str_d(sum[3])-Format.str_d(sum[0]))/Format.str_d(sum[0])*100);
		sum[4] = String.valueOf(Format.d2(zgdpldsum));
		return sum;
	}

}
