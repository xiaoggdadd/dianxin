package com.ptac.app.statisticcollect.province.modifyunitprice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.statisticcollect.city.modifyunitprice.bean.ModifyUnitPriceBean;
import com.ptac.app.statisticcollect.province.modifyunitprice.bean.ProvinceModifyUnitPriceBean;

public class ProvinceModifyUnitPriceDaoImpl implements ProvinceModifyUnitPriceDao {

	@Override
	public String[] getSum(List<ProvinceModifyUnitPriceBean> beanlist) {
		
		String[] sum = new String[5];
		int length = beanlist.size();
		ProvinceModifyUnitPriceBean bean = null;
		long lrtssum = 0;//¼�������ϼ�
		long xgdjtssum = 0;//�޸ĵ��������ϼ�
		long djtgtssum = 0;//���۵��������ϼ�
		double tgzbsum = 0;//������ռ��
		double zbsum = 0;//��ռ��
		
			for( int i = 0 ; i < length ; i++ ){
				bean = beanlist.get(i);
				long lrts = Long.parseLong(bean.getLrts());//��ѵ�¼������
				long xgdjts = Long.parseLong(bean.getXgdjts());//�޸ĵ�������
				long djtgts = Long.parseLong(bean.getTgts());//���۵�������
				
				lrtssum += lrts;
				xgdjtssum += xgdjts;
				djtgtssum += djtgts;
			}
			if(lrtssum != 0){
			tgzbsum = ((double)djtgtssum/lrtssum)*100;
			zbsum = ((double)xgdjtssum/lrtssum)*100;
			}
		sum[0] = String.valueOf(lrtssum);
		sum[1] = String.valueOf(xgdjtssum);
		sum[2] = String.valueOf(djtgtssum);
		sum[3] = Format.str2(String.valueOf(tgzbsum));
		sum[4] = Format.str2(String.valueOf(zbsum));
		
		return sum;
	}

	@Override
	public List<ProvinceModifyUnitPriceBean> quaryModifyUnitPrice(String whereStr,String whereStr1,String loginId,String beginyue,String endyue,String bzw) {
		
		DataBase db = null;
		Connection conn =  null;
		String whereorder = "";
		if("daochu".equals(bzw)){
			whereorder = whereorder + " ORDER BY  XGDJ.SHI";
		}
		String sql="SELECT RNDIQU(XGDJ.SHI) AS SHI,COUNT(DISTINCT DECODE(XGDJ.XZDF, '1', XGDJ.EFID)) AS LRTS,COUNT(DECODE(XGDJ.XZDF, '1', XGDJ.TIAOJIA)) AS XGDJTS,COUNT(DECODE(XGDJ.XZDF, '1', XGDJ.TIAOGAO)) AS TGTS,(CASE WHEN COUNT(DECODE(XGDJ.XZDF, '1', XGDJ.EFID)) = 0 THEN 0 ELSE COUNT(DECODE(XGDJ.XZDF, '1', XGDJ.TIAOGAO)) / COUNT(DECODE(XGDJ.XZDF, '1', XGDJ.EFID)) * 100 END) AS TGZB FROM ( "
        + "SELECT ZHDN.SHI SHI,ABC.ELECTRICFEE_ID EFID,(CASE WHEN TO_NUMBER(ABC.UNITPRICE) <> TO_NUMBER(ABC.DANJIA) THEN ABC.UNITPRICE END) TIAOJIA,(CASE WHEN TO_NUMBER(ABC.UNITPRICE) > TO_NUMBER(ABC.DANJIA) THEN ABC.UNITPRICE END) TIAOGAO, (CASE WHEN ABC.ACCOUNTMONTH >= '"+beginyue+"' AND ABC.ACCOUNTMONTH <= '"+endyue+"' THEN '1' ELSE '0' END) AS XZDF FROM ZHANDIAN ZHDN, (SELECT ZD.ID ZID,"
        +" EF.ELECTRICFEE_ID ELECTRICFEE_ID,EF.UNITPRICE UNITPRICE,DB.DANJIA DANJIA, TO_CHAR(EF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH FROM ZHANDIAN ZD, DIANBIAO DB,AMMETERDEGREES AM, ELECTRICFEES EF WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK "+whereStr+" AND ZD.SHSIGN = '1' AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND (ZD.XIAOQU IN "
        +"(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) ABC WHERE ZHDN.ID = ABC.ZID(+) "+whereStr1+" AND ZHDN.SHSIGN = '1' AND ZHDN.XUNI = '0' AND ZHDN.CAIJI = '0' AND (ZHDN.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) XGDJ GROUP BY XGDJ.SHI " + whereorder;
		 System.out.println("ʡ���޸ĵ��۲�ѯ�������"+sql.toString());
//		if(bzw.equals("chaxun")){//����ǲ�ѯ��ִ�����sql��� ��û������������������иĶ� ����sql��䶼Ҫ�޸�
//		 sql = "SELECT (SELECT D.AGNAME FROM  D_AREA_GRADE D WHERE D.AGCODE=XGDJ.SHI) AS SHI,COUNT(DISTINCT DECODE(XGDJ.XZDF,'1',XGDJ.EFID)) AS LRTS, COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.TIAOJIA)) AS XGDJTS, COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.TIAOGAO)) AS TGTS," 
//			+"(CASE WHEN  COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.EFID)) = 0 THEN 0 ELSE COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.TIAOGAO)) / COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.EFID)) * 100 END) AS TGZB" 
//			+" FROM "
//			+"(SELECT ZD.SHI," 
//			+"EF.ELECTRICFEE_ID EFID,(CASE WHEN TO_NUMBER(EF.UNITPRICE) <> TO_NUMBER(DB.DANJIA) THEN EF.UNITPRICE END) TIAOJIA,(CASE WHEN TO_NUMBER(EF.UNITPRICE) > TO_NUMBER(DB.DANJIA) THEN EF.UNITPRICE END) TIAOGAO, "
//			+"(CASE WHEN SUBSTR(EF.ACCOUNTMONTH, 0, 7) >= '"+beginyue+"' AND SUBSTR(EF.ACCOUNTMONTH, 0, 7) <= '"+endyue+"' THEN '1' ELSE '0' end ) as  XZDF "
//			+"FROM ZHANDIAN ZD LEFT JOIN DIANBIAO DB ON ZD.ID = DB.ZDID LEFT JOIN AMMETERDEGREES AM ON DB.DBID = AM.AMMETERID_FK LEFT JOIN ELECTRICFEES EF ON AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK WHERE "
//			+ whereStr
//			+"AND ZD.SHSIGN = '1' AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) XGDJ GROUP BY XGDJ.SHI";
//		 System.out.println("ʡ���޸ĵ��۲�ѯ���"+sql.toString());
//		}else{//����ǵ�����ִ�����sql��� ��������������������иĶ� ����sql��䶼Ҫ�޸�
//			sql = "SELECT (SELECT D.AGNAME FROM  D_AREA_GRADE D WHERE D.AGCODE=XGDJ.SHI) AS SHI,COUNT(DISTINCT DECODE(XGDJ.XZDF,'1',XGDJ.EFID)) AS LRTS, COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.TIAOJIA)) AS XGDJTS, COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.TIAOGAO)) AS TGTS," 
//			+"(CASE WHEN  COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.EFID)) = 0 THEN 0 ELSE COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.TIAOGAO)) / COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.EFID)) * 100 END) AS TGZB" 
//			+" FROM "
//			+"(SELECT ZD.SHI," 
//			+"EF.ELECTRICFEE_ID EFID,(CASE WHEN TO_NUMBER(EF.UNITPRICE) <> TO_NUMBER(DB.DANJIA) THEN EF.UNITPRICE END) TIAOJIA,(CASE WHEN TO_NUMBER(EF.UNITPRICE) > TO_NUMBER(DB.DANJIA) THEN EF.UNITPRICE END) TIAOGAO, "
//			+"(CASE WHEN SUBSTR(EF.ACCOUNTMONTH, 0, 7) >= '"+beginyue+"' AND SUBSTR(EF.ACCOUNTMONTH, 0, 7) <= '"+endyue+"' THEN '1' ELSE '0' end ) as  XZDF "
//			+"FROM ZHANDIAN ZD LEFT JOIN DIANBIAO DB ON ZD.ID = DB.ZDID LEFT JOIN AMMETERDEGREES AM ON DB.DBID = AM.AMMETERID_FK LEFT JOIN ELECTRICFEES EF ON AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK WHERE "
//			+ whereStr
//			+"AND ZD.SHSIGN = '1' AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) XGDJ GROUP BY XGDJ.SHI ORDER BY XGDJ.SHI";
//			 System.out.println("ʡ���޸ĵ��۵������"+sql.toString());
//		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProvinceModifyUnitPriceBean> list = null;
		

		try {
			list = new ArrayList<ProvinceModifyUnitPriceBean>();
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				ProvinceModifyUnitPriceBean bean = new ProvinceModifyUnitPriceBean();
				bean.setShi(rs.getString("shi"));
				bean.setLrts(rs.getString("lrts"));
				bean.setXgdjts(rs.getString("xgdjts"));
				bean.setTgts(rs.getString("tgts"));
				bean.setTgzb(Format.str2(rs.getString("tgzb")));
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}

		return list;
	}

}
