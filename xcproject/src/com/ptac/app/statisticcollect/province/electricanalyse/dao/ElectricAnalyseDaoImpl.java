package com.ptac.app.statisticcollect.province.electricanalyse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.ptac.app.inportuserzibaodian.util.Format;
import com.ptac.app.statisticcollect.province.electricanalyse.bean.ElectricAnalyseBean;

public class ElectricAnalyseDaoImpl implements ElectricAnalyseDao{
	private Logger logger = Logger.getLogger(ElectricAnalyseDaoImpl.class);
	@Override
	public List<ElectricAnalyseBean> queryDetails(String[] yearyue,
			String property, String status, String zzs) {
		
		List<ElectricAnalyseBean> list = new ArrayList<ElectricAnalyseBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String dl = "2".equals(status)?"CWDL":"YWDL";
		
		sql.append("SELECT RNDIQU(A.SHI) SHI,D.PAY1,D.PAY2,D.PAY3,DECODE(D.PAY1, 0, 0, ROUND(((D.PAY3 - D.PAY1) / D.PAY1*100),2)) TB1,DECODE(D.PAY2, 0, 0, ROUND(((D.PAY3 - D.PAY2) / D.PAY2*100),2)) HB1,D.PAY4,D.PAY5,"
				+ " DECODE(D.PAY4, 0, 0, ROUND(((D.PAY5 - D.PAY4) / D.PAY4*100),2)) TB2 FROM (SELECT DG.AGCODE SHI FROM D_AREA_GRADE DG WHERE DG.FAGCODE = '137') A,"
				+ " (SELECT ROUND((SUMPAY1 + SUMPAYC1 - SUMPAYC2)/10000,2) PAY1,ROUND((SUMPAY2 + SUMPAYC3 - SUMPAYC4)/10000,2) PAY2,ROUND((SUMPAY3 + SUMPAYC5 - SUMPAYC3)/10000,2) PAY3," 
				+ " ROUND((SUMPAY4 + SUMPAYC1 - SUMPAYC6)/10000,2) PAY4,ROUND((SUMPAY5 + SUMPAYC5 - SUMPAYC7)/10000,2) PAY5,B.SHI SHI"
				+ " FROM (SELECT SUM(DECODE(TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[0]).append("',MH.").append(dl).append(",0)) SUMPAY1," //--去年同月 yearyue[0]
				+ " SUM(DECODE(TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[1]).append("',MH.").append(dl).append(",0)) SUMPAY2,"//--报账月份上个月yearyue[1]
                + " SUM(DECODE(TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[2]).append("',MH.").append(dl).append(",0)) SUMPAY3," //--报账月份yearyue[2]
                + " SUM(CASE WHEN TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM') >= '").append(yearyue[3]).append("' AND"//--去年1月yearyue[3]
                + " TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM') <= '").append(yearyue[0]).append("' THEN MH.").append(dl).append(" ELSE 0 END) SUMPAY4," //--去年报账月份yearyue[0]
                + " SUM(CASE WHEN TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM') >= '").append(yearyue[4]).append("' AND"//--今年1月yearyue[4]
                + " TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM') <= '").append(yearyue[2]).append("' THEN MH.").append(dl).append(" ELSE 0 END) SUMPAY5,MH.SHI FROM MQY_ZH MH WHERE 1=1 ").append("0".equals(property)?"":("AND MH.ZPROPERTY = '"+property+"'")).append("1".equals(zzs)?"":" AND MH.STATIONTYPE <> 'StationType41'").append(" GROUP BY MH.SHI) B,"//--今年报账月份yearyue[2]
                + " (SELECT SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'), '").append(yearyue[0]).append("', NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC1,"//--去年同月yearyue[0]
                + " SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[5]).append("',NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC2,"//--去年同月的上个月yearyue[5]
                + " SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[1]).append("',NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC3,"//--报账月份的上个月yearyue[1]
                + " SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[6]).append("',NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC4,"//--报账月份上个月的上个月yearyue[6]
                + " SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[2]).append("',NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC5,"//--报账月份yearyue[2]
                + " SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[7]).append("',NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC6,"//--去年1月的上个月yearyue[7]
                + " SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[8]).append("',NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC7,"//--当年1月的上个月 yearyue[8]
                + " MZ.SHI FROM MQY_ZH_ZG MZ WHERE 1=1 ").append("0".equals(property)?"":("AND MZ.PROPERTY = '"+property+"'")).append("1".equals(zzs)?"":" AND MZ.STATIONTYPE <> 'StationType41'").append(" GROUP BY MZ.SHI) C WHERE B.SHI = C.SHI) D WHERE A.SHI = D.SHI(+) ORDER BY A.SHI");
		logger.info("电量分析通报查询："+sql.toString());
		
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ElectricAnalyseBean bean = new ElectricAnalyseBean();
				bean.setCity(rs.getString("SHI")==null?"":rs.getString("SHI"));
				bean.setLastsameyuef(rs.getString("PAY1")==null?"0.00":Format.str2(rs.getString("PAY1")));
				bean.setLastyuef(rs.getString("PAY2")==null?"0.00":Format.str2(rs.getString("PAY2")));
				bean.setAccountmonthf(rs.getString("PAY3")==null?"0.00":Format.str2(rs.getString("PAY3")));
				bean.setTb1(rs.getString("TB1")==null?"0.00":Format.str2(rs.getString("TB1")));
				bean.setHb1(rs.getString("HB1")==null?"0.00":Format.str2(rs.getString("HB1")));
				bean.setLast1toaf(rs.getString("PAY4")==null?"0.00":Format.str2(rs.getString("PAY4")));
				bean.setThe1toaf(rs.getString("PAY5")==null?"0.00":Format.str2(rs.getString("PAY5")));
				bean.setTb2(rs.getString("TB2")==null?"0.00":Format.str2(rs.getString("TB2")));
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("电量分析通报查询出错："+e.getMessage());
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}
	public String[] total(List<ElectricAnalyseBean> list) {
		String[] total = new String[8];
		double pay1sum=0,pay2sum=0,pay3sum=0,tb1zb=0,hb1zb=0,pay4sum=0,pay5sum=0,tb2zb=0;
		for (ElectricAnalyseBean bean : list) {
			pay1sum+=Format.str_d(bean.getLastsameyuef());
			pay2sum+=Format.str_d(bean.getLastyuef());
			pay3sum+=Format.str_d(bean.getAccountmonthf());
			pay4sum+=Format.str_d(bean.getLast1toaf());
			pay5sum+=Format.str_d(bean.getThe1toaf());
		}
		pay1sum=Format.d2(pay1sum);
		pay2sum=Format.d2(pay2sum);
		pay3sum=Format.d2(pay3sum);
		pay4sum=Format.d2(pay4sum);
		pay5sum=Format.d2(pay5sum);
		tb1zb = 0==pay1sum?0:Format.d2((pay3sum-pay1sum)/pay1sum*100);
		hb1zb = 0==pay2sum?0:Format.d2((pay3sum-pay2sum)/pay2sum*100);
		tb2zb = 0==pay4sum?0:Format.d2((pay5sum-pay4sum)/pay4sum*100);
		total[0] = String.valueOf(pay1sum);
		total[1] = String.valueOf(pay2sum);
		total[2] = String.valueOf(pay3sum);
		total[3] = String.valueOf(tb1zb); 
		total[4] = String.valueOf(hb1zb);
		total[5] = String.valueOf(pay4sum);
		total[6] = String.valueOf(pay5sum);
		total[7] = String.valueOf(tb2zb);
		return total;
	}

}
