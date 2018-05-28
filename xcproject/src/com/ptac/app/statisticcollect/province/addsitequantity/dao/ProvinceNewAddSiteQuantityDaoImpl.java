package com.ptac.app.statisticcollect.province.addsitequantity.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.statisticcollect.province.addsitequantity.bean.ProvinceNewAddSiteQuantityBean;



public class ProvinceNewAddSiteQuantityDaoImpl implements ProvinceNewAddSiteQuantityDao {

	/**
	 * @author WangYiXiao 2014-2-15
	 * @update WangYiXiao 2014-5-19 新加默认条件：电表用途 结算
	 */
	@Override
	public String[] addSiteQuantiySum(List<ProvinceNewAddSiteQuantityBean> beanlist) {
		String[] all = new String[5];
		//站点总数
		long sitenum = 0;
		//-----各自新增和
		long newadd = 0;//新增站点数量
		double addfee = 0;//新增电费
		double addelectric = 0;//新增电量
		double feeweifu = 0;//新增电费为负总和
		int size = beanlist.size();
		int i;
		for(i = 0;i<size;i++){
			ProvinceNewAddSiteQuantityBean bean = beanlist.get(i);
			long site = Long.parseLong(bean.getNum());
			sitenum = sitenum + site;
			long num = Long.parseLong(bean.getAddnum());
			newadd = newadd + num;
			double fee = Double.parseDouble(bean.getAddfee());
			addfee = addfee + fee;
			double electric = Double.parseDouble(bean.getAddelectric());
			addelectric = addelectric + electric;
			double weifu = Double.parseDouble(bean.getFeeweifu());
			feeweifu = feeweifu + weifu;
		}
		all[0] = String.valueOf(sitenum);
		all[1] = String.valueOf(newadd);
		all[2] = Format.str2(String.valueOf(addfee));
		all[3] = Format.str2(String.valueOf(addelectric));
		all[4] = Format.str2(String.valueOf(feeweifu));
		return all;
	}
	
	
	/**
	 * @see  查询，导出
	 * @author WangYiXiao 2014-5-20
	 */
	@Override
	public List<ProvinceNewAddSiteQuantityBean> quaryExport(String shi, String beginyue,
			String endyue, String property,String qyzt, String loginId) {
		DataBase db = null;
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		List<ProvinceNewAddSiteQuantityBean> list = new ArrayList<ProvinceNewAddSiteQuantityBean>();
		String whereStr = "";
		String whereStr1 = "";
		if(!"0".equals(shi)){
			whereStr = whereStr + " AND Z.SHI='"+shi+"' ";
			whereStr1 = whereStr1 + " AND ZZ.SHI='"+shi+"' ";
		}
		if(property != null && !"".equals(property) && !"0".equals(property)){
			whereStr = whereStr + " AND Z.PROPERTY='"+property+"' ";
			whereStr1 = whereStr1 + " AND ZZ.PROPERTY='"+property+"' ";
		}
		if(qyzt!=null&&!qyzt.equals("")&& !qyzt.equals("-1")){
		whereStr = whereStr + " AND Z.QYZT = '"+qyzt+"' ";
		whereStr1 = whereStr1 + " AND ZZ.QYZT = '"+qyzt+"' ";
		}
		String sql11= "SELECT XXXX.SHI,XXXX.PROPERTY,COUNT(DISTINCT DECODE(XXXX.ENDZD, '1', XXXX.ZZID)) AS ZDZS,COUNT(DISTINCT DECODE(XXXX.XZZD, '1',XXXX.ZZID)) XZZD,ROUND(SUM(DECODE(XXXX.ZRXZBZ, '1', XXXX.EACTUALPAY, 0)) / 10000, 2) AS ACT,ROUND(SUM(DECODE(XXXX.ZRXZBZ, '1', XXXX.ABLHDL, 0)) / 10000, 2) AS BLHDL,"
					 +"ROUND(SUM((CASE WHEN XXXX.EACTUALPAY < 0 AND XXXX.ZRXZBZ = '1' THEN XXXX.EACTUALPAY END)) / 10000, 2) AS ACTFU FROM ( SELECT  ZZ.ID ZZID,ZZ.SHI SHIID,(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE = ZZ.SHI) AS SHI, (SELECT I.NAME FROM INDEXS I WHERE I.CODE = ZZ.PROPERTY " 
					 +"AND I.TYPE = 'ZDSX') PROPERTY,ZDXX.ABLHDL,ZDXX.EACTUALPAY,ZDXX.ZRXZBZ, (CASE WHEN ZZ.SHSIGN = '1' AND TO_CHAR(ZZ.MANUALAUDITDATETIME_STATION,'yyyy-mm') >= '"+beginyue+"' AND TO_CHAR(ZZ.MANUALAUDITDATETIME_STATION,'yyyy-mm') <= '"+endyue+"' THEN '1' ELSE '0' END) XZZD,(CASE WHEN ZZ.SHSIGN = '1' AND TO_CHAR(ZZ.MANUALAUDITDATETIME_STATION,'yyyy-mm') <='"+endyue+"' THEN '1' ELSE '0' END) ENDZD FROM ZHANDIAN ZZ LEFT JOIN "
                     +"(SELECT Z.ID ZID, A.BLHDL ABLHDL, DN.ACTUALPAY EACTUALPAY,(CASE WHEN Z.SHSIGN = '1' AND TO_CHAR(Z.MANUALAUDITDATETIME_STATION,'yyyy-mm') >= '"+beginyue+"' AND TO_CHAR(Z.MANUALAUDITDATETIME_STATION,'yyyy-mm') <= '"+endyue+"' AND DN.CITYZRAUDITSTATUS = '1' THEN '1' ELSE '0' END) ZRXZBZ FROM ZHANDIAN Z,"
                     +"DIANBIAO D,ELECTRICFEES DN,AMMETERDEGREES A WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = DN.AMMETERDEGREEID_FK AND Z.XUNI = '0' AND Z.CAIJI = '0' AND Z.SHSIGN = '1' AND D.DBYT = 'dbyt01' AND Z.STATIONTYPE<>'StationType41'  AND TO_CHAR(DN.ACCOUNTMONTH,'yyyy-mm')>='"+beginyue+"' AND TO_CHAR(DN.ACCOUNTMONTH,'yyyy-mm')<= '"+endyue+"' AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) "
                     +whereStr+") ZDXX ON ZZ.ID = ZDXX.ZID WHERE ZZ.XUNI = '0' AND ZZ.CAIJI = '0' AND ZZ.SHSIGN = '1' AND ZZ.STATIONTYPE<>'StationType41' "+whereStr1+") XXXX GROUP BY XXXX.SHI,XXXX.SHIID,XXXX.PROPERTY ORDER BY XXXX.SHIID";
		
		
		try {
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.createStatement();
			System.out.println("省级新增站点数量："+sql11);
			rs = ps.executeQuery(sql11);
			while(rs.next()){
				ProvinceNewAddSiteQuantityBean cbean = new ProvinceNewAddSiteQuantityBean();
		        cbean.setShi(rs.getString(1));//市
				cbean.setZdsx(rs.getString(2));//站点属性
				cbean.setNum(rs.getString(3));//站点数量
				cbean.setAddnum(rs.getString(4));//新增站点数量
				cbean.setAddfee(rs.getString(5)!= null?Format.str2(rs.getString(5)):"0.00");//新增电费
				cbean.setAddelectric(rs.getString(6)!= null?Format.str2(rs.getString(6)):"0.00");//新增电量
				cbean.setFeeweifu(rs.getString(7)!= null?Format.str2(rs.getString(7)):"0.00");//新增电费为负值总和
				list.add(cbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		db.free(rs, ps, conn);	
		}
		return list;
	}

}
