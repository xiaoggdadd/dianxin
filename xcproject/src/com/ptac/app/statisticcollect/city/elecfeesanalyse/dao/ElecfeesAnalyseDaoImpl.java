package com.ptac.app.statisticcollect.city.elecfeesanalyse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.ptac.app.inportuserzibaodian.util.Format;
import com.ptac.app.statisticcollect.city.elecfeesanalyse.bean.ElecFeesAnalyseBean;

public class ElecfeesAnalyseDaoImpl implements ElecfeesAnalyseDao{
	private Logger logger = Logger.getLogger(ElecfeesAnalyseDaoImpl.class);
	@Override
	public List<ElecFeesAnalyseBean> queryDetails(String[] yearyue,
			String property, String status, String zzs,String shicode,String loginid) {
		
		List<ElecFeesAnalyseBean> list = new ArrayList<ElecFeesAnalyseBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String dl = "2".equals(status)?"CWDF":"YWDF";
		
		sql.append("SELECT RNDIQU(A.SHI) SHI, RNDIQU(A.XIAN) XIAN,D.PAY1,D.PAY2,D.PAY3,DECODE(D.PAY1, 0, 0, ROUND(((D.PAY3 - D.PAY1) / D.PAY1*100),2)) TB1,DECODE(D.PAY2, 0, 0, ROUND(((D.PAY3 - D.PAY2) / D.PAY2*100),2)) HB1,D.PAY4,D.PAY5,"
				+ " DECODE(D.PAY4, 0, 0, ROUND(((D.PAY5 - D.PAY4) / D.PAY4*100),2)) TB2 FROM (SELECT FAGCODE SHI, AGCODE XIAN FROM D_AREA_GRADE WHERE FAGCODE = '").append(shicode).append("' AND AGCODE IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '").append(loginid).append("') ORDER BY AGCODE) A,"
				+ " (SELECT ROUND((SUMPAY1 + SUMPAYC1 - SUMPAYC2)/10000,2) PAY1,ROUND((SUMPAY2 + SUMPAYC3 - SUMPAYC4)/10000,2) PAY2,ROUND((SUMPAY3 + SUMPAYC5 - SUMPAYC3)/10000,2) PAY3," 
				+ " ROUND((SUMPAY4 + SUMPAYC1 - SUMPAYC6)/10000,2) PAY4,ROUND((SUMPAY5 + SUMPAYC5 - SUMPAYC7)/10000,2) PAY5,B.XIAN XIAN"
				+ " FROM (SELECT SUM(DECODE(TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[0]).append("',MH.").append(dl).append(",0)) SUMPAY1," //--ȥ��ͬ�� yearyue[0]
				+ " SUM(DECODE(TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[1]).append("',MH.").append(dl).append(",0)) SUMPAY2,"//--�����·��ϸ���yearyue[1]
                + " SUM(DECODE(TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[2]).append("',MH.").append(dl).append(",0)) SUMPAY3," //--�����·�yearyue[2]
                + " SUM(CASE WHEN TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM') >= '").append(yearyue[3]).append("' AND"//--ȥ��1��yearyue[3]
                + " TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM') <= '").append(yearyue[0]).append("' THEN MH.").append(dl).append(" ELSE 0 END) SUMPAY4," //--ȥ�걨���·�yearyue[0]
                + " SUM(CASE WHEN TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM') >= '").append(yearyue[4]).append("' AND"//--����1��yearyue[4]
                + " TO_CHAR(MH.ACCOUNTMONTH, 'YYYY-MM') <= '").append(yearyue[2]).append("' THEN MH.").append(dl).append(" ELSE 0 END) SUMPAY5,MH.XIAN FROM MQY_ZH MH WHERE 1=1 ")//--���걨���·�yearyue[2]
                .append("0".equals(property)?"":("AND MH.ZPROPERTY = '"+property+"'")).append("1".equals(zzs)?"":" AND MH.STATIONTYPE <> 'StationType41'")
                .append(" AND MH.XIAN IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '").append(loginid).append("')GROUP BY MH.XIAN) B,"
                + " (SELECT SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'), '").append(yearyue[0]).append("', NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC1,"//--ȥ��ͬ��yearyue[0]
                + " SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[5]).append("',NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC2,"//--ȥ��ͬ�µ��ϸ���yearyue[5]
                + " SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[1]).append("',NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC3,"//--�����·ݵ��ϸ���yearyue[1]
                + " SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[6]).append("',NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC4,"//--�����·��ϸ��µ��ϸ���yearyue[6]
                + " SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[2]).append("',NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC5,"//--�����·�yearyue[2]
                + " SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[7]).append("',NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC6,"//--ȥ��1�µ��ϸ���yearyue[7]
                + " SUM(DECODE(TO_CHAR(MZ.ACCOUNTMONTH, 'YYYY-MM'),'").append(yearyue[8]).append("',NVL(MZ.").append(dl).append(", 0),0)) SUMPAYC7,"//--����1�µ��ϸ��� yearyue[8]
                + " MZ.XIAN FROM MQY_ZH_ZG MZ WHERE 1=1 ").append("0".equals(property)?"":("AND MZ.PROPERTY = '"+property+"'")).append("1".equals(zzs)?"":" AND MZ.STATIONTYPE <> 'StationType41'")
                .append(" AND MZ.XIAN IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '").append(loginid).append("') GROUP BY MZ.XIAN) C WHERE B.XIAN = C.XIAN) D WHERE A.XIAN = D.XIAN(+) ORDER BY A.XIAN");
		logger.info("�м���ѷ���ͨ����ѯ��"+sql.toString());
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ElecFeesAnalyseBean bean = new ElecFeesAnalyseBean();
				
				bean.setShi(rs.getString("SHI")==null?"":rs.getString("SHI"));
				bean.setCity(rs.getString("XIAN")==null?"":rs.getString("XIAN"));
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
			logger.error("�м���ѷ���ͨ����ѯ����"+e.getMessage());
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}
	public String[] total(List<ElecFeesAnalyseBean> list) {
		String[] total = new String[8];
		double pay1sum=0,pay2sum=0,pay3sum=0,tb1zb=0,hb1zb=0,pay4sum=0,pay5sum=0,tb2zb=0;
		for (ElecFeesAnalyseBean bean : list) {
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
		pay5sum=Format.d1(pay5sum);
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
