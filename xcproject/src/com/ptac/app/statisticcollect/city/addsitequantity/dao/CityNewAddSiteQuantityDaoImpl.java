package com.ptac.app.statisticcollect.city.addsitequantity.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.statisticcollect.city.addsitequantity.bean.CityNewAddSiteQuantityBean;
import com.ptac.app.statisticcollect.city.addsitequantity.bean.EleAndFeesBean;


/**
 * @author WangYiXiao 
 *@update  WangYiXiao 2014-5-19 新加默认条件： 电表启用，电表用途 结算
 */
public class CityNewAddSiteQuantityDaoImpl implements CityNewAddSiteQuantityDao {

	/**
	 * @author WangYiXiao 2014-2-15
	 */
	@Override
	public String[] addSiteQuantiySum(List<CityNewAddSiteQuantityBean> beanlist) {
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
			CityNewAddSiteQuantityBean bean = beanlist.get(i);
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
	 * @author WangYiXiao 2014-5-22
	 */
	@Override
	public List<CityNewAddSiteQuantityBean> export(String shi, String beginyue,
			String endyue, String property, String qyzt, String loginId) {
		DataBase db = null;
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		List<CityNewAddSiteQuantityBean> list = new ArrayList<CityNewAddSiteQuantityBean>();
		String whereStr = "";
		String whereStr1 = "";
		if(property != null && !"".equals(property) && !"0".equals(property)){
			whereStr = whereStr + " AND Z.PROPERTY='"+property+"' ";
			whereStr1 = whereStr1 + " AND ZZ.PROPERTY='"+property+"' ";
		}
		if(qyzt!=null&&!qyzt.equals("")&& !qyzt.equals("-1")){
			whereStr = whereStr + " AND Z.QYZT = '"+qyzt+"' ";
			whereStr1 = whereStr1 + " AND ZZ.QYZT = '"+qyzt+"' ";
			}
		String sql11 = "SELECT XXXX.XIAN,XXXX.PROPERTY,COUNT(DISTINCT DECODE(XXXX.ENDZD, '1', XXXX.ZZID)) AS ZDZS," +
				"COUNT(DISTINCT DECODE(XXXX.XZZD, '1',XXXX.ZZID)) XZZD," +
				"ROUND(SUM(DECODE(XXXX.ZRXZBZ, '1', XXXX.EACTUALPAY, 0)) / 10000, 2) AS ACT,"
				+"ROUND(SUM(DECODE(XXXX.ZRXZBZ, '1', XXXX.ABLHDL, 0)) / 10000, 2) AS BLHDL," +
				"ROUND(SUM((CASE WHEN XXXX.EACTUALPAY < 0 AND XXXX.ZRXZBZ = '1' THEN XXXX.EACTUALPAY END)) / 10000, 2) AS ACTFU "
				+"FROM ( SELECT ZZ.ID ZZID,ZZ.XIAN XIANA, (SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE = ZZ.XIAN) AS XIAN,(SELECT I.NAME FROM INDEXS I WHERE I.CODE = ZZ.PROPERTY AND I.TYPE = 'ZDSX') PROPERTY, ZDXX.ABLHDL, ZDXX.EACTUALPAY,"
				+"ZDXX.ZRXZBZ,(CASE WHEN ZZ.SHSIGN = '1' AND TO_CHAR(ZZ.MANUALAUDITDATETIME_STATION,'yyyy-mm') >= '" + beginyue + "' AND TO_CHAR(ZZ.MANUALAUDITDATETIME_STATION,'yyyy-mm') <= '" + endyue + "' THEN '1' ELSE '0' END) XZZD,(CASE WHEN ZZ.SHSIGN = '1' AND TO_CHAR(ZZ.MANUALAUDITDATETIME_STATION,'yyyy-mm') <='"+endyue+"' THEN '1' ELSE '0' END) ENDZD FROM ZHANDIAN ZZ LEFT JOIN "
				+"(SELECT Z.ID ZID, A.BLHDL ABLHDL, DN.ACTUALPAY EACTUALPAY,(CASE WHEN Z.SHSIGN = '1' AND TO_CHAR(Z.MANUALAUDITDATETIME_STATION,'yyyy-mm') >= '" + beginyue + "' AND TO_CHAR(Z.MANUALAUDITDATETIME_STATION,'yyyy-mm') <= '" + endyue + "' AND DN.CITYZRAUDITSTATUS = '1' THEN '1' ELSE '0' END) ZRXZBZ "
				+"FROM ZHANDIAN Z,DIANBIAO D,ELECTRICFEES DN,AMMETERDEGREES A WHERE Z.ID = D.ZDID  AND D.DBID = A.AMMETERID_FK AND DN.AMMETERDEGREEID_FK = A.AMMETERDEGREEID AND Z.XUNI = '0' AND Z.CAIJI = '0' AND Z.SHSIGN = '1' AND D.DBYT = 'dbyt01' AND (Z.XIAOQU IN "
				+"(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '" + loginId + "')) AND Z.STATIONTYPE <> 'StationType41'  AND Z.SHI = '" + shi + "' AND TO_CHAR(DN.ACCOUNTMONTH,'yyyy-mm')>='"+beginyue+"' AND TO_CHAR(DN.ACCOUNTMONTH,'yyyy-mm')<= '"+endyue+"' " + whereStr + ") ZDXX ON ZZ.ID = ZDXX.ZID WHERE ZZ.XUNI = '0' AND ZZ.CAIJI = '0' AND ZZ.SHSIGN = '1' AND (ZZ.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '" + loginId + "')) "
				+" AND ZZ.STATIONTYPE <> 'StationType41' AND ZZ.SHI = '" + shi +"'" + whereStr1 + ") XXXX GROUP BY XXXX.XIAN,XXXX.XIANA,XXXX.PROPERTY ORDER BY XXXX.XIANA";

		try {
			db = new DataBase();
			conn = db.getConnection();
			ps = conn.createStatement();
			System.out.println("地市新增站点数量："+sql11);
			rs = ps.executeQuery(sql11);
			while(rs.next()){
				CityNewAddSiteQuantityBean cbean = new CityNewAddSiteQuantityBean();
		        cbean.setXian(rs.getString(1));//区县
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
			db.free(rs,ps, conn);
		}
		return list;
	}
	@Override
	public List<EleAndFeesBean> quaryEleAndFees(String shi, String beginyue,
			String endyue, String property,String qyzt, String loginId) {
		DataBase db = new DataBase();
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		
		List<EleAndFeesBean> list = new ArrayList<EleAndFeesBean>();
		

		String whereStr = "";
		if(property != null && !"".equals(property) && !"0".equals(property)){
			whereStr = whereStr + " AND ZD.PROPERTY = '"+property+"' ";
		}
		if(qyzt!=null&&!qyzt.equals("")&& !qyzt.equals("-1")){
			whereStr = whereStr + " AND ZD.QYZT = '"+qyzt+"' ";
			}
		String sql = "SELECT RNDIQU(ZD.SHI) SHI, RNDIQU(ZD.XIAN) XIAN,RNDIQU(ZD.XIAOQU) XIAOQU,ZD.JZNAME JZNAME,TO_CHAR(DN.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,DN.LASTDEGREE LASTDEGREE,DN.THISDEGREE THISDEGREE,"
       +"TO_CHAR(DN.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(DN.THISDATETIME,'yyyy-mm-dd') THISDATETIME,DN.BLHDL BLHDL,DN.FLOATDEGREE FLOATDEGREE,DN.AMEMO AMEMO,DN.UNITPRICE UNITPRICE,DN.ACTUALPAY ACTUALPAY,DN.FLOATPAY FLOATPAY,"
       +"DN.EMEMO EMEMO,DN.JSZQ JSZQ,D.BEILV BEILV,RTNAME(ZD.PROPERTY) PROPERTY,(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE = 'stationtype') STATIONTYPE,DN.LIUCHENGHAO LIUCHENGHAO,"
       +"ZD.ID ZDID,D.DBID DBID "
       +"FROM ZHANDIAN ZD, DIANBIAO D, DIANFEIDAN_T02 DN "
       +"WHERE ZD.ID = D.ZDID "
       +"AND D.DBID = DN.AMMETERID_FK "
       +"AND ZD.SHI = '"+shi+"' "
       +whereStr
       +"AND TO_CHAR(ZD.MANUALAUDITDATETIME_STATION,'yyyy-mm') >= '"+beginyue+"' "
       +"AND TO_CHAR(ZD.MANUALAUDITDATETIME_STATION,'yyyy-mm') <= '"+endyue+"' "
       +"AND ZD.SHSIGN = '1' "
       +"AND DN.CITYZRAUDITSTATUS = '1' "
       +"AND D.DBYT = 'dbyt01' " 
       +"AND TO_CHAR(DN.ACCOUNTMONTH,'yyyy-mm')>='"+beginyue+"' " 
       +"AND TO_CHAR(DN.ACCOUNTMONTH,'yyyy-mm')<= '"+endyue+"' "
       +"AND ZD.CAIJI = '0' "
       +"AND ZD.XUNI = '0' "
       +"AND ZD.SHSIGN = '1' AND ZD.STATIONTYPE <> 'StationType41' "
       +"AND (ZD.XIAOQU IN "
       +"(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))  ORDER BY ZD.XIAN";
		
		String shi1 = "";//城市
		String xian = "";//区县
		String xiaoqu = "";//乡镇
		String jzname = "";//站点名称
		String accountmonth = "";//报账月份
		String lastdegree = "";//上次电表读数
		String thisdegree = "";//本次电表读数
		String lastdatetime = "";//上次抄表时间
		String thisdatetime = "";//本次抄表时间
		String blhdl = "";//报账用电量
		String floatdegree = "";//用电量调整
		String amemo = "";//电量调整备注
		String unitprice = "";//本次单价
		String actualpay = "";//报账电费
		String floatpay;//电费调整
		String ememo = "";//电费调整备注
		String jszq = "";//结算周期
		String beilv = "";//倍率
		String property1 = "";//站点属性
		String stationtype = "";//站点类型
		String liuchenghao = "";//流程号
		String zdid = "";//站点ID
		String dbid = "";//电表ID
		
		try {
			conn = db.getConnection();
			st = conn.createStatement();
			System.out.println("新增站点电量电费明细查询，导出："+sql);
			rs = st.executeQuery(sql);
			while(rs.next()){
				EleAndFeesBean efb = new EleAndFeesBean();
				shi1 = rs.getString("SHI");
				xian = rs.getString("XIAN");
				xiaoqu = rs.getString("XIAOQU");
				jzname = rs.getString("JZNAME");
				accountmonth = rs.getString("ACCOUNTMONTH");
				lastdegree = rs.getString("LASTDEGREE");
				thisdegree = rs.getString("THISDEGREE");
				lastdatetime = rs.getString("LASTDATETIME");
				thisdatetime = rs.getString("THISDATETIME");
				blhdl = rs.getString("BLHDL");
				floatdegree = rs.getString("FLOATDEGREE");
				amemo = rs.getString("AMEMO");
				unitprice = rs.getString("UNITPRICE");
				actualpay = rs.getString("ACTUALPAY");
				floatpay = rs.getString("FLOATPAY");
				ememo = rs.getString("EMEMO");
				jszq = rs.getString("JSZQ");
				beilv = rs.getString("BEILV");
				property1 = rs.getString("PROPERTY");
				stationtype = rs.getString("STATIONTYPE");
				liuchenghao = rs.getString("LIUCHENGHAO");
				zdid = rs.getString("ZDID");
				dbid = rs.getString("DBID");
				
				efb.setShi(shi1==null?"":shi1);
				efb.setXian(xian==null?"":xian);
				efb.setXiaoqu(xiaoqu==null?"":xiaoqu);
				efb.setJzname(jzname==null?"":jzname);
				efb.setAccountmonth(accountmonth==null?"":accountmonth);
				efb.setLastdegree(lastdegree==null?"":lastdegree);
				efb.setThisdegree(thisdegree==null?"":thisdegree);
				efb.setLastdatetime(lastdatetime==null?"":lastdatetime);
				efb.setThisdatetime(thisdatetime==null?"":thisdatetime);
				efb.setBlhdl(blhdl==null?"":Format.str2(blhdl));
				efb.setFloatdegree(floatdegree==null?"":Format.str2(floatdegree));
				efb.setAmemo(amemo==null?"":amemo);
				efb.setUnitprice(unitprice==null?"":Format.str4(unitprice));
				efb.setActualpay(actualpay==null?"":Format.str2(actualpay));
				efb.setFloatpay(floatpay==null?"":Format.str2(floatpay));
				efb.setEmemo(ememo==null?"":ememo);
				efb.setJszq(jszq==null?"":jszq);
				efb.setBeilv(beilv==null?"":beilv);
				efb.setProperty(property1==null?"":property1);
				efb.setStationtype(stationtype==null?"":stationtype);
				efb.setLiuchenghao(liuchenghao==null?"":liuchenghao);
				efb.setZdid(zdid==null?"":zdid);
				efb.setDbid(dbid==null?"":dbid);

				list.add(efb);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, conn);
		}	
		return list;
	}
}
