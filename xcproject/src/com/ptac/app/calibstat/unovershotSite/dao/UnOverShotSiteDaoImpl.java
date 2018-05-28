package com.ptac.app.calibstat.unovershotSite.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.mobi.common.zdbzbeanB;

public class UnOverShotSiteDaoImpl implements UnOverShotSiteDao{

	/**@author WangYiXiao 2014-9-28
	 * 未超标站点查询
	 * 参考方法：超标站点查询：List<zdbzbeanB> com.noki.mobi.common.AccountJzqa.getPageDatacbcx(String whereStr, String str, String str1, String str2, String str3, String str4, String str5, String loginId)
	 * @param whereStr：地市、区县、小区、站点属性
	 * @param str：超标比例
	 * @param str1：报账月份
	 * @return：查询出来的站点的信息
	 */
		public synchronized List<zdbzbeanB> getPageDatauncbcx(String whereStr,String str,String str1,String str2,String str3,String str4,String str5,String loginId) {		
				List<zdbzbeanB> list = new ArrayList<zdbzbeanB>();
			    DataBase db = new DataBase();
			    Connection connc = null;
			    Statement st = null;
			    String sql1 = "";
			    ResultSet rs = null;
				try {
				 sql1 = "SELECT ZD.ID,ZD.JZNAME,P.NAME AS PROPERTY,S.AGNAME AS SHI,X.AGNAME AS XIAN," +
				 		"XQ.AGNAME AS XIAOQU,ZD.ZLFH,ZD.JLFH,ZD.EDHDL,ZDDL.BL,ZDDL.DBYDL,ZDDL.BZZ," +
				 		" DECODE(CB.ZDID,NULL,1,'',1,0) AS BZ, ZDDL.DLCB,(ZDDL.DLCB * 0.9) AS DFCB FROM ZHANDIAN ZD LEFT JOIN INDEXS P ON ZD.PROPERTY = P.CODE" +
				 		" AND P.TYPE = 'ZDSX' LEFT JOIN D_AREA_GRADE S ON ZD.SHI = S.AGCODE " +
				 		"LEFT JOIN D_AREA_GRADE X ON ZD.XIAN = X.AGCODE LEFT JOIN D_AREA_GRADE XQ ON ZD.XIAOQU = XQ.AGCODE" +
				 		" LEFT JOIN CBZD CB ON ZD.ID = CB.ZDID " +str2+
				 		",(SELECT AB.ZDID, AB.DBYDL,AB.BZZ,((AB.DBYDL - AB.BZZ)/AB.BZZ) AS BL , " +
				 		" (AB.DBYDL/AB.DAYCOUNT-AB.SCB) AS DLCB " +
				 		" FROM (SELECT JZ.ZDID,JZ.BZZ,JZ.DBYDL,JZ.SCB,JZ.DAYCOUNT " +
				 		" FROM JZXX JZ WHERE 1=1 "+str5+") AB) ZDDL" +
				 		" WHERE ZD.ID = ZDDL.ZDID AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+str4;			
				 System.out.println("未超标站点查询sql1："+sql1);
			      connc = db.getConnection();
			      st = connc.createStatement();
			      rs = st.executeQuery(sql1);
			      while(rs.next()){
			    	  zdbzbeanB zb=new zdbzbeanB();
			    	  	zb.setZdid(rs.getString("ID") != null ? rs.getString("ID") : "");
			    	    zb.setZdname(rs.getString("JZNAME") != null ? rs.getString("JZNAME") : "");
						zb.setProperty(rs.getString("PROPERTY") != null ? rs.getString("PROPERTY") : "");
						zb.setShi1(rs.getString("SHI") != null ? rs.getString("SHI") : "");
						zb.setXian(rs.getString("XIAN") != null ? rs.getString("XIAN") : "");
						zb.setXiaoqu(rs.getString("XIAOQU") != null ? rs.getString("XIAOQU") : "");
						zb.setZlfh(rs.getString("ZLFH") != null ? rs.getString("ZLFH") : "");
						zb.setJlfh(rs.getString("JLFH") != null ? rs.getString("JLFH") : "");
						zb.setEdhdl(rs.getString("EDHDL") != null ? rs.getString("EDHDL") : "");
						zb.setBl(rs.getString("BL") != null ? rs.getString("BL") : "");
						zb.setDbydl(rs.getString("DBYDL") != null ? rs.getString("DBYDL") : "");//电表用电量
						zb.setBzz(rs.getString("BZZ") != null ? rs.getString("BZZ") : "");//标准值(合并电量)
						zb.setBz(rs.getString("BZ") != null ? rs.getString("BZ") : "0");
						zb.setDlcb(rs.getString("DLCB") != null ? rs.getString("DLCB") : "0");
						zb.setDfcb(rs.getString("DFCB") != null ? rs.getString("DFCB") : "0");
						list.add(zb);
			      }
			    } catch (Exception de) {
			      de.printStackTrace();
			    }finally {
			    	db.free(rs, st, connc);
			    }
			    return list;
			  }
}
