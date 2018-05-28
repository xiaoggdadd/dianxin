package com.ptac.app.calibstat.huopiaolv.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.calibstat.huopiaolv.bean.HuoPiaoLv;
import com.ptac.app.calibstat.movefixedelec.bean.MoveFixedElec;

public class HuoPiaoLvDaoImpl implements HuoPiaoLvDao {

	/**求和
	 * @param list  (List(HuoPiaoLv))
	 * @return 
	 * @author WangYiXiao 2014-10-15
	 */
	public BigDecimal[] total(List<HuoPiaoLv> list) {
		BigDecimal[] total = new BigDecimal[3];
		BigDecimal sumtax = new BigDecimal("0.00");//税额和
		BigDecimal sumelecfees = new BigDecimal("0.00");//财务总电费和
		BigDecimal sumvat = new BigDecimal("0.00");//增值税发票金额和
		int i;
		int size = list.size();// 数据条数
		for (i = 0; i < size; i++) {// 遍历求和
			HuoPiaoLv bean = list.get(i);// 获得一个对象
			sumtax = sumtax.add(bean.getTax());
			sumelecfees = sumelecfees.add(bean.getElecfees());
			sumvat = sumvat.add(bean.getVat());
		}
		total[0] = sumtax;
		total[1] = sumelecfees;
		total[2] = sumvat;

		return total;
	}
	@Override
	public List<HuoPiaoLv> getHuoPiaoLv(String sheng,String shi, String begintime,
			String endtime, String loginId) {
		List<HuoPiaoLv> list = new ArrayList<HuoPiaoLv>();
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		DataBase db = new DataBase();
		StringBuffer sql = new StringBuffer();
			sql.append("SELECT RNDIQU(A.AGCODE) SHI,DECODE(B.TAX,NULL,0,B.TAX) TAX,DECODE(B.ELECFEES,NULL,0,B.ELECFEES) ELECFEES,DECODE(B.VAT,NULL,0,B.VAT) VAT,DECODE(B.RATIO,NULL,0,B.RATIO*100) RATIO"
			+ " FROM (SELECT T.AGCODE FROM D_AREA_GRADE T WHERE T.FAGCODE = '").append(sheng).append("'").append("0".equals(shi)?"":(" AND T.AGCODE = '"+shi+"'")).append(" AND T.AGRADE = '2') A,"
			+ " (SELECT AA.SHI, SUM(DECODE(AA.SE,NULL,0,AA.SE)) / 10000 TAX, SUM(DECODE(AA.FM,NULL,0,AA.FM)) / 10000 ELECFEES, SUM(DECODE(AA.ZZSFPJE,NULL,0,AA.ZZSFPJE)) / 10000 VAT,"
            + " (ABS(SUM(DECODE(AA.SE,NULL,0,AA.SE)) / 10000) + SUM(DECODE(AA.ZZSFPJE,NULL,0,AA.ZZSFPJE)) / 10000) / (CASE WHEN ((ABS(SUM(DECODE(AA.SE,NULL,0,AA.SE)) / 10000) + SUM(DECODE(AA.FM,NULL,0,AA.FM)) / 10000)) <> 0 THEN ((ABS(SUM(DECODE(AA.SE,NULL,0,AA.SE)) / 10000) + SUM(DECODE(AA.FM,NULL,0,AA.FM)) / 10000)) END) RATIO"
            + " FROM (SELECT Z.SHI,(CASE WHEN Z.STATIONTYPE = 'StationType41' AND to_char(E.ACCOUNTMONTH,'yyyy-mm') >= '").append(begintime).append("' AND to_char(E.ACCOUNTMONTH,'yyyy-mm') <= '").append(endtime).append("' AND E.MANUALAUDITSTATUS = '2' THEN E.ACTUALPAY END) SE,"
            + " (CASE WHEN Z.STATIONTYPE <> 'StationType41' AND E.NOTETYPEID = 'pjlx06' AND E.MANUALAUDITSTATUS = '2' AND to_char(E.ACCOUNTMONTH,'yyyy-mm') >= '").append(begintime).append("' AND to_char(E.ACCOUNTMONTH,'yyyy-mm') <= '").append(endtime).append("' THEN E.ACTUALPAY END) ZZSFPJE,"
            + " (CASE WHEN Z.STATIONTYPE <> 'StationType41' AND E.MANUALAUDITSTATUS = '2' AND to_char(E.ACCOUNTMONTH,'yyyy-mm') >= '").append(begintime).append("' AND to_char(E.ACCOUNTMONTH,'yyyy-mm') <= '").append(endtime).append("' THEN E.ACTUALPAY END) FM"
            + " FROM ZHANDIAN Z,DIANBIAO D,AMMETERDEGREES A,ELECTRICFEES E WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK"
            + " AND to_char(E.ACCOUNTMONTH,'yyyy-mm') >= '").append(begintime).append("' AND to_char(E.ACCOUNTMONTH,'yyyy-mm') <= '").append(endtime).append("' AND Z.CAIJI = '0' AND Z.XUNI = '0' AND Z.SHSIGN = '1'").append("0".equals(shi)?"":(" AND Z.SHI = '"+shi+"'")).append(" AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '").append(loginId).append("')))  AND E.MANUALAUDITSTATUS = '2') AA"
            + " GROUP BY AA.SHI) B WHERE A.AGCODE = B.SHI(+) ORDER BY A.AGCODE");
		System.out.println("获票率统计:"+sql.toString());
		 try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				HuoPiaoLv hpl = new HuoPiaoLv();
				hpl.setShi(rs.getString("SHI"));
				hpl.setTax(rs.getBigDecimal("TAX").setScale(2, BigDecimal.ROUND_HALF_UP));
				hpl.setElecfees(rs.getBigDecimal("ELECFEES").setScale(2, BigDecimal.ROUND_HALF_UP));
				hpl.setVat(rs.getBigDecimal("VAT").setScale(2, BigDecimal.ROUND_HALF_UP));
				hpl.setRatio(rs.getBigDecimal("RATIO").setScale(2, BigDecimal.ROUND_HALF_UP));
				list.add(hpl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return list;
	}

}
