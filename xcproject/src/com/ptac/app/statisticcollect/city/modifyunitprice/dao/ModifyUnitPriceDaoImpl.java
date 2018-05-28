package com.ptac.app.statisticcollect.city.modifyunitprice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.statisticcollect.city.modifyunitprice.bean.ModifyUnitPriceBean;
import com.ptac.app.statisticcollect.city.modifyunitprice.bean.UnitPriceDetailsBean;

public class ModifyUnitPriceDaoImpl implements ModifyUnitPriceDao {

	@Override
	public String[] getSum(List<ModifyUnitPriceBean> beanlist) {
		
		String[] sum = new String[5];
		int length = beanlist.size();
		ModifyUnitPriceBean bean = null;
		long lrtssum = 0;//录入条数合计
		long xgdjtssum = 0;//修改单价条数合计
		long djtgtssum = 0;//单价调高条数合计
		double tgzbsum = 0;//调高总占比
		for( int i = 0 ; i < length ; i++ ){
			bean = beanlist.get(i);
			long lrts = Long.parseLong(bean.getLrts());//电费单录入条数
			long xgdjts = Long.parseLong(bean.getXgdjts());//修改单价条数
			long djtgts = Long.parseLong(bean.getTgts());//单价调高条数
			
			lrtssum += lrts;
			xgdjtssum += xgdjts;
			djtgtssum += djtgts;
		}
		if(lrtssum != 0){
		tgzbsum = ((double)djtgtssum/lrtssum)*100;
		}
		sum[0] = String.valueOf(lrtssum);
		sum[1] = String.valueOf(xgdjtssum);
		sum[2] = String.valueOf(djtgtssum);
		sum[3] = Format.str2(String.valueOf(tgzbsum));
		
		return sum;
	}

	@Override
	public List<ModifyUnitPriceBean> quaryModifyUnitPrice(String whereStr,String whereStr1,String loginId,String beginyue,String endyue,String command) {
		
		DataBase db = null;
		Connection conn =  null;
		String whereorder = "";
		if("daochu".equals(command)){
			whereorder = whereorder + " ORDER BY XGDJ.XIAN";
		}
		String sql1 = "SELECT RNDIQU(XGDJ.SHI) AS SHI, RNDIQU(XGDJ.XIAN) AS XIAN, COUNT(DISTINCT DECODE(XGDJ.XZDF, '1', XGDJ.EFID)) AS LRTS, COUNT(DECODE(XGDJ.XZDF, '1', XGDJ.TIAOJIA)) AS XGDJTS, COUNT(DECODE(XGDJ.XZDF, '1', XGDJ.TIAOGAO)) AS TGTS,"
       +"(CASE WHEN COUNT(DECODE(XGDJ.XZDF, '1', XGDJ.EFID)) = 0 THEN 0 ELSE COUNT(DECODE(XGDJ.XZDF, '1', XGDJ.TIAOGAO)) / COUNT(DECODE(XGDJ.XZDF, '1', XGDJ.EFID)) * 100 END) AS TGZB FROM ( "
       +"SELECT ZHDN.SHI SHI,ZHDN.XIAN XIAN, ABC.ELECTRICFEE_ID EFID,(CASE WHEN TO_NUMBER(ABC.UNITPRICE) <> TO_NUMBER(ABC.DANJIA) THEN ABC.UNITPRICE END) TIAOJIA,(CASE WHEN TO_NUMBER(ABC.UNITPRICE) > TO_NUMBER(ABC.DANJIA) THEN ABC.UNITPRICE END) TIAOGAO,"
       +"(CASE WHEN TO_CHAR(ABC.ACCOUNTMONTH,'yyyy-mm') >= '"+beginyue+"' AND TO_CHAR(ABC.ACCOUNTMONTH,'yyyy-mm') <= '"+endyue+"' THEN '1' ELSE '0' END) AS XZDF "
       +"FROM ZHANDIAN ZHDN,(SELECT ZD.ID ZID,EF.ELECTRICFEE_ID ELECTRICFEE_ID, EF.UNITPRICE UNITPRICE, DB.DANJIA DANJIA, EF.ACCOUNTMONTH ACCOUNTMONTH FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF WHERE ZD.ID = DB.ZDID "
       +"AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK "+whereStr+" AND ZD.SHSIGN = '1' AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND (ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) ABC "
       +"WHERE ZHDN.ID = ABC.ZID(+) "+whereStr1+" AND ZHDN.SHSIGN = '1' AND ZHDN.XUNI = '0' AND ZHDN.CAIJI = '0' AND (ZHDN.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) XGDJ GROUP BY XGDJ.SHI, XGDJ.XIAN" + whereorder;

//		String sql = "SELECT XGDJ.SHI AS SHI, XGDJ.XIAN AS XIAN,COUNT(DISTINCT DECODE(XGDJ.XZDF,'1',XGDJ.EFID)) AS LRTS, COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.TIAOJIA)) AS XGDJTS, COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.TIAOGAO)) AS TGTS," 
//			+"(CASE WHEN  COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.EFID)) = 0 THEN 0 ELSE COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.TIAOGAO)) / COUNT(DECODE(XGDJ.XZDF,'1',XGDJ.EFID)) * 100 END) AS TGZB" 
//			+" FROM "
//			+"(SELECT (SELECT D.AGNAME FROM  D_AREA_GRADE D WHERE D.AGCODE=ZD.SHI) AS SHI,(SELECT D.AGNAME FROM  D_AREA_GRADE D WHERE D.AGCODE=ZD.XIAN) AS XIAN," 
//			+"EF.ELECTRICFEE_ID EFID,(CASE WHEN TO_NUMBER(EF.UNITPRICE) <> TO_NUMBER(DB.DANJIA) THEN EF.UNITPRICE END) TIAOJIA,(CASE WHEN TO_NUMBER(EF.UNITPRICE) > TO_NUMBER(DB.DANJIA) THEN EF.UNITPRICE END) TIAOGAO, "
//			+"(CASE WHEN SUBSTR(EF.ACCOUNTMONTH, 0, 7) >= '"+beginyue+"' AND SUBSTR(EF.ACCOUNTMONTH, 0, 7) <= '"+endyue+"' THEN '1' ELSE '0' end ) as  XZDF "
//			+"FROM ZHANDIAN ZD LEFT JOIN DIANBIAO DB ON ZD.ID = DB.ZDID LEFT JOIN AMMETERDEGREES AM ON DB.DBID = AM.AMMETERID_FK LEFT JOIN ELECTRICFEES EF ON AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK WHERE "
//			+ whereStr
//			+"AND ZD.SHSIGN = '1' AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) XGDJ GROUP BY XGDJ.SHI,XGDJ.XIAN";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ModifyUnitPriceBean> list = null;
		

		try {
			list = new ArrayList<ModifyUnitPriceBean>();
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql1);
			System.out.println("sql:"+sql1);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ModifyUnitPriceBean bean = new ModifyUnitPriceBean();
				bean.setShi(rs.getString("shi"));
				bean.setXian(rs.getString("xian"));
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

	@Override
	public List<UnitPriceDetailsBean> getModifyUnitPriceDetails(String beginyue,String endyue,String whereStr,String command) {
		DataBase db = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<UnitPriceDetailsBean> list = new ArrayList<UnitPriceDetailsBean>();
		String whereorder = "";
		String whereequal = "";
		
		if("xiugai".equals(command) || "xiugaidaochu".equals(command)){//修改单价
			whereequal = "<>";
		}else if("tiaogao".equals(command) || "tiaogaodaochu".equals(command)){//调高单价
			whereequal = ">";
		}
		if("xiugaidaochu".equals(command) || "tiaogaodaochu".equals(command)){//导出
			whereorder = whereorder + " ORDER BY ZD.XIAN";
		}
		String sql = "SELECT RNDIQU(ZD.SHI) AS SHI,"
        + "RNDIQU(ZD.XIAN) AS XIAN,"
        + "RNDIQU(ZD.XIAOQU) AS XIAOQU,"
        + "ZD.JZNAME AS JZNAME,"
        + "(SELECT I.NAME FROM INDEXS I WHERE I.TYPE = 'ZDSX' AND I.CODE = ZD.PROPERTY) AS PROPERTY,"
        + "(SELECT I.NAME FROM INDEXS I WHERE I.TYPE = 'stationtype' AND I.CODE =ZD.STATIONTYPE) AS STATIONTYPE,"
        + "ZD.ID AS ZDID,"
        + "DB.DBID AS DBID,"
        + "DB.DANJIA PREVIOUSUNITPRICE,"
        + "EF.UNITPRICE LATERUNITPRICE  "
        + "FROM ZHANDIAN ZD,DIANBIAO DB,AMMETERDEGREES AM,ELECTRICFEES EF "
        + "WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK "
        + whereStr
        + "AND ZD.SHSIGN = '1' "
        + "AND ZD.XUNI = '0' "
        + "AND ZD.CAIJI = '0' "
        + "AND TO_NUMBER(EF.UNITPRICE) " + whereequal + " TO_NUMBER(DB.DANJIA) "
        + "AND TO_CHAR(EF.ACCOUNTMONTH,'yyyy-mm') >= '"+beginyue+"' "
        + "AND TO_CHAR(EF.ACCOUNTMONTH,'yyyy-mm') <= '"+endyue+"' "
        + "GROUP BY ZD.SHI,ZD.XIAN,ZD.XIAOQU,ZD.JZNAME,ZD.PROPERTY,ZD.STATIONTYPE,ZD.ID,DB.DBID,EF.UNITPRICE,DB.DANJIA,AM.AMMETERDEGREEID,EF.ELECTRICFEE_ID"
        + whereorder;
		
		
		try {
			db = new DataBase();
			conn = db.getConnection();
			st = conn.createStatement();
			System.out.println("修改单价详情：" + sql);
			rs = st.executeQuery(sql);
			UnitPriceDetailsBean bean = null;
			while(rs.next()){
				bean = new UnitPriceDetailsBean();
				bean.setShi(rs.getString("SHI") == null ? "" : rs.getString("SHI"));
				bean.setXian(rs.getString("XIAN") == null ? "" : rs.getString("XIAN"));
				bean.setXiaoqu(rs.getString("XIAOQU") == null ? "" : rs.getString("XIAOQU"));
				bean.setJzname(rs.getString("JZNAME") == null ? "" : rs.getString("JZNAME"));
				bean.setProperty(rs.getString("PROPERTY") == null ? "" : rs.getString("PROPERTY"));
				bean.setStationtype(rs.getString("STATIONTYPE") == null ? "" : rs.getString("STATIONTYPE"));
				bean.setZdid(rs.getString("ZDID") == null ? "" : rs.getString("ZDID"));
				bean.setDbid(rs.getString("DBID") == null ? "" : rs.getString("DBID"));
				bean.setPreviousunitprice(rs.getString("PREVIOUSUNITPRICE") == null ? "" : Format.str4(rs.getString("PREVIOUSUNITPRICE")));
				bean.setLaterunitprice(rs.getString("LATERUNITPRICE") == null ? "" :  Format.str4(rs.getString("LATERUNITPRICE")));
				list.add(bean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, conn);
		}
		return list;
	}

}
