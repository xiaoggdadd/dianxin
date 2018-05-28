package com.ptac.app.calibstat.movefixedelec.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.calibstat.movefixedelec.bean.MoveFixedElec;
import com.ptac.app.calibstat.movefixedelec.util.DateUtil;

public class MoveFixedElecDaoImpl implements MoveFixedElecDao {
	/**
	 * @param list  (List(MoveFixedElec))
	 * @return 
	 * @author WangYiXiao 2014-10-14
	 */
	public BigDecimal[] total(List<MoveFixedElec> list) {
		BigDecimal[] total = new BigDecimal[6];
		BigDecimal fixedfee = new BigDecimal("0.00");//固网费用和
		BigDecimal movefee = new BigDecimal("0.00");//移网费用和
		BigDecimal fee = new BigDecimal("0.00");//费用小计和
		BigDecimal fixedelec = new BigDecimal("0.00");//固网电量和
		BigDecimal moveelec = new BigDecimal("0.00");//移网电量和
		BigDecimal elec = new BigDecimal("0.00");//电量小计和
		int i;
		int size = list.size();// 数据条数
		for (i = 0; i < size; i++) {// 遍历求和
			MoveFixedElec bean = list.get(i);// 获得一个对象
			fixedfee = fixedfee.add(bean.getFixedfees());
			movefee = movefee.add(bean.getMovefees());
			fee = fee.add(bean.getSumfees());
			fixedelec = fixedelec.add(bean.getFixedelec());
			moveelec = moveelec.add(bean.getMoveelec());
			elec = elec.add(bean.getSumelec());
		}
		total[0] = fixedfee;
		total[1] = movefee;
		total[2] = fee;
		total[3] = fixedelec;
		total[4] = moveelec;
		total[5] = elec;
		return total;
	}

	@Override
	public List<MoveFixedElec> getMoveFixedFee(String sheng,String shi, String begintime,
			String endtime,String[] time,String loginid) {
		List<MoveFixedElec> list = new ArrayList<MoveFixedElec>(); 
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		
		String sql = " SELECT "+("0".equals(shi)?("RNDIQU('"+sheng+"')"):"B.SHI")+" SHI,A.NAME ACCOUNTSUBJECT,DECODE(B.FIXEDFEE,NULL,0,B.FIXEDFEE) FIXEDFEE,DECODE(B.MOVEFEE,NULL,0,B.MOVEFEE) MOVEFEE,DECODE(B.FIXEDFEE,NULL,0,B.FIXEDFEE)+DECODE(B.MOVEFEE,NULL,0,B.MOVEFEE) SUMFEE,"
	  + " DECODE(B.FIXEDELEC,NULL,0,B.FIXEDELEC) FIXEDELEC,DECODE(B.MOVEELEC,NULL,0,B.MOVEELEC) MOVEELEC, DECODE(B.FIXEDELEC,NULL,0,B.FIXEDELEC)+DECODE(B.MOVEELEC,NULL,0,B.MOVEELEC) SUMELEC FROM (SELECT T.CODE,T.NAME FROM INDEXS T WHERE T.TYPE = 'YGDL')A,(SELECT "+("0".equals(shi)?"":"RNDIQU(ZD.SHI) SHI,")+" BBZ.KJKMCODE ACCOUNTSUBJECT,"
      + " SUM(DECODE(BBZ.BZ, 'gw', DF.ACTUALPAY * BBZ.BL)) / 10000 FIXEDFEE, SUM(DECODE(BBZ.BZ, 'yw', DF.ACTUALPAY * BBZ.BL)) / 10000 MOVEFEE,"
      + " SUM(CASE WHEN BBZ.BZ = 'gw' AND to_char(DF.ACCOUNTMONTH,'yyyy-mm') >= '" + time[0] + "' AND to_char(DF.ACCOUNTMONTH,'yyyy-mm') <= '" + time[1] + "' THEN DW.BLHDL * BBZ.BL / 10000 END) FIXEDELEC,"
      + " SUM(CASE WHEN BBZ.BZ = 'yw' AND to_char(DF.ACCOUNTMONTH,'yyyy-mm') >= '" + time[0] + "' AND to_char(DF.ACCOUNTMONTH,'yyyy-mm') <= '" + time[1] + "' THEN DW.BLHDL * BBZ.BL / 10000 END) MOVEELEC"
      + " FROM ZHANDIAN ZD, DIANBIAO DB,DIANDUVIEW DW,DIANFEIVIEW DF,"
      + "(SELECT SG.SHUOSHUZHUANYE,SG.DIANBIAOID, SG.KJKMCODE,SG.DBILI * SG.XJBILI / 10000 BL,(CASE WHEN SG.SHUOSHUZHUANYE = 'zylx01' AND SG.ZYMXCODE = '19' THEN 'yw'"
      + " WHEN SG.SHUOSHUZHUANYE = 'zylx01' AND SG.ZYMXCODE = '12' THEN 'yw' WHEN SG.SHUOSHUZHUANYE = 'zylx01' AND SG.ZYMXCODE = '11' THEN 'yw'"
      + " WHEN SG.SHUOSHUZHUANYE = 'zylx01' AND SG.ZYMXCODE = '09' THEN 'gw' WHEN SG.SHUOSHUZHUANYE = 'zylx01' AND SG.ZYMXCODE = '21' THEN 'gw' END) BZ FROM SBGL SG) BBZ"
      + " WHERE BBZ.DIANBIAOID = DB.DBID AND DB.DBID = DW.AMMETERID_FK AND DW.AMMETERDEGREEID = DF.AMMETERDEGREEID_FK AND ZD.ID = DB.ZDID " + ("0".equals(shi)?"":("AND ZD.SHI = '"+shi+"'"))
      + " AND to_char(DF.ACCOUNTMONTH,'yyyy-mm') >= '" + begintime + "' AND to_char(DF.ACCOUNTMONTH,'yyyy-mm') <= '" + endtime + "' AND DB.DBYT = 'dbyt01' AND DF.CITYZRAUDITSTATUS = '1'"
      + " AND ZD.STATIONTYPE <> 'StationType41' AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginid+"'))) AND BBZ.SHUOSHUZHUANYE IN ('zylx01', 'zylx06')"
      + " AND BBZ.KJKMCODE IN (SELECT T.CODE FROM INDEXS T WHERE T.TYPE = 'YGDL') GROUP BY BBZ.KJKMCODE"+("0".equals(shi)?"":",ZD.SHI")+")B WHERE A.CODE = B.ACCOUNTSUBJECT(+)";
		 DataBase db = new DataBase();
		 System.out.println("固网移网电量统计:"+sql);
		 try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				MoveFixedElec mfe = new MoveFixedElec();
				mfe.setShi(rs.getString("SHI"));
				mfe.setAccountsubject(rs.getString("ACCOUNTSUBJECT"));
				mfe.setFixedfees(rs.getBigDecimal("FIXEDFEE").setScale(2, BigDecimal.ROUND_HALF_UP));
				mfe.setMovefees(rs.getBigDecimal("MOVEFEE").setScale(2, BigDecimal.ROUND_HALF_UP));
				mfe.setSumfees(rs.getBigDecimal("SUMFEE").setScale(2, BigDecimal.ROUND_HALF_UP));
				mfe.setFixedelec(rs.getBigDecimal("FIXEDELEC").setScale(2, BigDecimal.ROUND_HALF_UP));
				mfe.setMoveelec(rs.getBigDecimal("MOVEELEC").setScale(2, BigDecimal.ROUND_HALF_UP));
				mfe.setSumelec(rs.getBigDecimal("SUMELEC").setScale(2, BigDecimal.ROUND_HALF_UP));
				list.add(mfe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return list;
	}

}
