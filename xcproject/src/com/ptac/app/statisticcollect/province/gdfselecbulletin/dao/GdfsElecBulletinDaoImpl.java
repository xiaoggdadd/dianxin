package com.ptac.app.statisticcollect.province.gdfselecbulletin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.ptac.app.inportuserzibaodian.util.Format;
import com.ptac.app.statisticcollect.province.gdfselecbulletin.bean.GdfsElecBulletinBean;

public class GdfsElecBulletinDaoImpl implements GdfsElecBulletinDao{
	private Logger logger = Logger.getLogger(GdfsElecBulletinDaoImpl.class);
	@Override
	public List<GdfsElecBulletinBean> queryDetails(String yearyue,
			String property, String status) {
		
		List<GdfsElecBulletinBean> list = new ArrayList<GdfsElecBulletinBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String dl = "2".equals(status)?"MH.CWDL":"MH.YWDL";
		
		sql.append("SELECT RNDIQU(A.SHI) SHI, C.ZDL, C.ZHIGD, C.ZHIZB, C.ZHUANGD, C.ZHUANZB FROM (SELECT DG.AGCODE SHI FROM D_AREA_GRADE DG WHERE DG.FAGCODE = '137') A,"
        +" (SELECT B.SHI SHI, B.ZDL, B.ZHIGD, ROUND(B.ZHIGD / B.ZDL * 100, 2) ZHIZB, B.ZDL - B.ZHIGD ZHUANGD,ROUND((B.ZDL - B.ZHIGD) / B.ZDL * 100, 2) ZHUANZB"
        +" FROM (SELECT ROUND(SUM(").append(dl).append(") / 10000,2) ZDL,ROUND(SUM(DECODE(MH.GDFS,'gdfs06',").append(dl).append(",'gdfs07',").append(dl).append(",0)) / 10000,2) ZHIGD, MH.SHI FROM MQY_ZH MH WHERE TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM') = '").append(yearyue).append("'")
        .append("0".equals(property)?"":(" AND MH.ZPROPERTY = '"+property+"'")).append(" GROUP BY MH.SHI) B) C  WHERE A.SHI = C.SHI(+) ORDER BY A.SHI");
		logger.info("供电方式电量通报查询："+sql.toString());
		
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				GdfsElecBulletinBean bean = new GdfsElecBulletinBean();
				
				bean.setCity(rs.getString("SHI")==null?"":rs.getString("SHI"));
				bean.setZdl(rs.getString("ZDL")==null?"0.00":Format.str2(rs.getString("ZDL")));
				bean.setZhigd(rs.getString("ZHIGD")==null?"0.00":Format.str2(rs.getString("ZHIGD")));
				bean.setZhigdzb(rs.getString("ZHIZB")==null?"0.00":Format.str2(rs.getString("ZHIZB")));
				bean.setZhuangd(rs.getString("ZHUANGD")==null?"0.00":Format.str2(rs.getString("ZHUANGD")));
				bean.setZhuangdzb(rs.getString("ZHUANZB")==null?"0.00":Format.str2(rs.getString("ZHUANZB")));
	
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("供电方式电量通报查询出错："+e.getMessage());
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}
	@Override
	public String[] total(List<GdfsElecBulletinBean> list) {
		String[] total = new String[5];
		double zdlsum = 0,zhigdsum=0,zhuangdsum=0,zhizb=0,zhuanzb=0;
		for (GdfsElecBulletinBean bean : list) {
			zdlsum+=Format.str_d(bean.getZdl());
			zhigdsum+=Format.str_d(bean.getZhigd());
		}
		zdlsum = Format.d2(zdlsum);
		zhigdsum = Format.d2(zhigdsum);
		zhuangdsum  = Format.d2(zdlsum-zhigdsum);
		zhizb = 0==zdlsum?0:Format.d2(zhigdsum/zdlsum*100);
		zhuanzb = 0==zdlsum?0:Format.d2(zhuangdsum/zdlsum*100);
		
		total[0] = String.valueOf(zdlsum);
		total[1] = String.valueOf(zhigdsum);
		total[2] = String.valueOf(zhizb);
		total[3] = String.valueOf(zhuangdsum); 
		total[4] = String.valueOf(zhuanzb);
		return total;
	}

}
