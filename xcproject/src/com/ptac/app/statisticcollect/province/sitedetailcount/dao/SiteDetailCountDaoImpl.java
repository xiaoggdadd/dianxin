package com.ptac.app.statisticcollect.province.sitedetailcount.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.statisticcollect.province.sitedetailcount.bean.SiteDetailCountBean;
import com.ptac.app.statisticcollect.province.sitedetailcount.bean.SiteDetailCountBean1;

/**
 * @author lijing
 * @see 报账单审核统计dao层实现类
 */
public class SiteDetailCountDaoImpl implements SiteDetailCountDao {

	/**
	 * @author lijing
	 * @see 报账单审核统计审核通过信息查询
	 * @param whereStr String 过滤条件
	 * @return list 返回一个SiteDetailCountBean的list
	 */
	@Override
	public List<SiteDetailCountBean> quarySiteDetailCount(
			StringBuffer whereStr,StringBuffer str, String loginId) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SiteDetailCountBean> list = new ArrayList<SiteDetailCountBean>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(ZD.ID) ZHAN,B.DIANBIAO,C.JIAOFEI,(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=B.SHI) AS SHI,C.ZIDONG,C.RENGONG,C.SHIJI,C.CAIWU,C.LIUCHENGHAO,B.SHI,C.QXZR,C.SZR,C.RG,C.diszdid "
				+" FROM ZHANDIAN ZD,(SELECT Z.SHI,COUNT(*) DIANBIAO FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND Z.SHSIGN='1' AND Z.QYZT='1' AND D.DBQYZT = '1' AND D.DBYT='dbyt01' and z.xiaoqu in (select t.agcode from per_area t where t.accountid = '263') GROUP BY SHI)B,"
				+"(SELECT Z.SHI,COUNT(DISTINCT(CONCAT(Z.ID, yf.ACCOUNTMONTH))) AS diszdid,COUNT(*) JIAOFEI, SUM(CASE WHEN YF.CITYAUDIT='1' THEN 1 ELSE 0 END) SHIJI,"
				+"SUM(CASE WHEN YF.COUNTYAUDITSTATUS = '1' THEN 1 ELSE 0 END) QXZR,"
				+"SUM(CASE WHEN YF.CITYZRAUDITSTATUS = '1' THEN 1 ELSE 0 END) SZR,"		
				+"SUM(DECODE(YF.caiwu, '2', 1)) CAIWU,"
				+"COUNT(YF.LIUCHENGHAO) LIUCHENGHAO,SUM(DECODE(YF.AUTOAUDITSTATUS, '1', 1)) ZIDONG,SUM(DECODE(YF.MANUALAUDITSTATUS, '1', 1)) RENGONG,SUM(DECODE(YF.MANUALAUDITSTATUS, '-1', 1)) RG"
				+" FROM ZHANDIAN Z,DIANBIAO D,dianfeidandayin YF WHERE Z.ID=D.ZDID AND D.DBID=yf.ammeterid_fk AND Z.SHSIGN='1' AND Z.QYZT='1' AND D.DBYT='dbyt01' AND D.DBQYZT = '1' "+whereStr+" GROUP BY SHI)C"
				+" WHERE B.SHI(+)=ZD.SHI AND C.SHI(+)=ZD.SHI AND ZD.SHSIGN='1' AND ZD.QYZT='1' "+str+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')"
				+" GROUP BY B.DIANBIAO,C.JIAOFEI,C.ZIDONG,C.RENGONG,C.SHIJI,C.CAIWU,C.LIUCHENGHAO,B.SHI,C.QXZR,C.SZR,C.RG,C.diszdid");
		
		System.out.println("报账单审核统计审核通过:"+sql);
		DataBase db = new DataBase();
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				SiteDetailCountBean bean=new SiteDetailCountBean();
				
				String passzdcount = rs.getString(1)!=null?rs.getString(1):"0";//审核通过站点总数
				bean.setPasszdcount(passzdcount);
				String xtjsdbs = rs.getString(2)!=null?rs.getString(2):"0";//系统结算电表数
				bean.setXtjsdbs(xtjsdbs);
				String bzts = rs.getString(3)!=null?rs.getString(3):"0";//报账条数（交费）
				bean.setBzts(bzts);
				String city = rs.getString(4)!=null?rs.getString(4):"";//城市
				bean.setCity(city);
				String zdshts = rs.getString(5)!=null?rs.getString(5):"0";//自动审核条数
				bean.setZdshts(zdshts);
				String xglyshts5 = rs.getString(6)!=null?rs.getString(6):"0";//县管理员审核条数（人工）
				
				String sglyshts = rs.getString(7)!=null?rs.getString(7):"0";//市管理员审核条数（市级） 
				bean.setSglyshts(sglyshts);
				String cwshts = rs.getString(8)!=null?rs.getString(8):"0";//财务审核条数	
				bean.setCwshts(cwshts);
				String dyts = rs.getString(9)!=null?rs.getString(9):"0";//打印条数
				bean.setDyts(dyts);
				String code = rs.getString(10)!=null?rs.getString(10):"";//编号
				bean.setCode(code);
				String qxzrshts = rs.getString(11)!=null?rs.getString(11):"";;//区县主任审核条数
				bean.setQxzrshts(qxzrshts);
				String szrshts = rs.getString(12)!=null?rs.getString(12):"";;//市级主任审核条数
				bean.setSzrshts(szrshts);
				
				String rg = rs.getString(13)!=null?rs.getString(13):"";
				
				String diszdsl = rs.getString(14)!=null?rs.getString(14):"";;//交过费用的站点数量（每个站点按照报账月份去重复）
				bean.setDiszdsl(diszdsl);
				
				double xglyshts = Format.str_d(cwshts)+Format.str_d(rg)+Format.str_d(xglyshts5);
				
				bean.setXglyshts(String.valueOf(xglyshts));
	           
				Double xtjsdbs1 = 0.0,bzts1 = 0.0,zdshts1 = 0.0,sglyshts1 = 0.0;
				Double cwshts1 = 0.0,dyts1 = 0.0,qxzrshts1 = 0.0,szrshts1 = 0.0;
				
				if(xtjsdbs!=""&&xtjsdbs!=" "&&xtjsdbs!=null&&xtjsdbs!="null"){
					xtjsdbs1 = Format.str_d(xtjsdbs);
				} 
	            if(bzts!=""&&bzts!=" "&&bzts!=null&&bzts!="null"){
	           	  	bzts1 = Double.parseDouble(bzts); 
	            }
	            if(zdshts!=""&&zdshts!=" "&&zdshts!=null&&zdshts!="null"){
	            	zdshts1 = Format.str_d(zdshts);
	            }
//	            if(xglyshts!=""&&xglyshts!=" "&&xglyshts!=null&&xglyshts!="null"){
//	            	xglyshts1 = Format.str_d(xglyshts);
//	            }
	            if(sglyshts!=""&&sglyshts!=" "&&sglyshts!=null&&sglyshts!="null"){
	            	sglyshts1 = Format.str_d(sglyshts);
	            }
	            if(cwshts!=""&&cwshts!=" "&&cwshts!=null&&cwshts!="null"){
	            	cwshts1 = Format.str_d(cwshts);
	            }
	            if(dyts!=""&&dyts!=" "&&dyts!=null&&dyts!="null"){
	            	dyts1 = Format.str_d(dyts);
	            }
	            if(qxzrshts!=""&&qxzrshts!=" "&&qxzrshts!=null&&qxzrshts!="null"){
	            	qxzrshts1 = Double.parseDouble(qxzrshts); 
	            }
	            if(szrshts!=""&&szrshts!=" "&&szrshts!=null&&szrshts!="null"){
	            	szrshts1 = Double.parseDouble(szrshts); 
	            }
	            
				DecimalFormat pay = new DecimalFormat("0.00");
				String bzl="",zdshl="",cwshl="",dyl="",xglyshl="",sglyshl="",qxzrshl="",szrshl=""; 
				
				if(bzts1 == 0){
					bzl = "0.00";
					zdshl = "0.00";
					cwshl = "0.00";
					dyl = "0.00";
					xglyshl = "0.00";
					sglyshl = "0.00";
					qxzrshl="0.00";
					szrshl="0.00";
				}else{
					Double avg = bzts1/xtjsdbs1*100;
					bzl = pay.format(avg);//报账率（缴费率）
					bean.setBzl(bzl);
		           
					Double avg1 = zdshts1/bzts1*100;
					zdshl = pay.format(avg1);//自动审核率
					bean.setZdshl(zdshl);
					
					Double avg2 = xglyshts/bzts1*100;
					xglyshl = pay.format(avg2);
					bean.setXglyshl(xglyshl);//县管理员审核率
					
					Double avg6 = qxzrshts1/bzts1*100;
					qxzrshl = pay.format(avg6);
					bean.setQxzrshl(qxzrshl);//区县主任审核率
					
					Double avg3 = sglyshts1/bzts1*100;
					sglyshl = pay.format(avg3);
					bean.setSglyshl(sglyshl);//市管理员审核率
					
					Double avg7 = szrshts1/bzts1*100;
					szrshl = pay.format(avg7);
					bean.setSzrshl(szrshl);//市级主任审核率	
		           
					Double avg4 = cwshts1/bzts1*100;
					cwshl = pay.format(avg4);//财务审核率
					bean.setCwshl(cwshl);
		           
					Double avg5 = dyts1/bzts1*100;
					dyl = pay.format(avg5);//打印率
					bean.setDyl(dyl);
				}
				
	          	list.add(bean);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @author lijing
	 * @see 报账单审核不通过统计信息查询
	 * @param whereStr String 过滤条件
	 * @return list 返回一个SiteDetailCountBean1的list
	 */
	@Override
	public List<SiteDetailCountBean1> quarySiteDetailCount1(
			StringBuffer whereStr, StringBuffer str, String loginId) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SiteDetailCountBean1> list1 = new ArrayList<SiteDetailCountBean1>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(ZD.ID) ZHAN,B.DIANBIAO,C.JIAOFEI,(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE=B.SHI) AS SHI,C.ZIDONG,C.RENGONG,C.SHIJI,C.CAIWU,B.SHI,C.QXZR,C.SZR"
				+" FROM ZHANDIAN ZD,(SELECT Z.SHI,COUNT(*) DIANBIAO FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND Z.SHSIGN='1' AND Z.QYZT='1' AND D.DBYT='dbyt01' GROUP BY SHI)B,"
				+"(SELECT Z.SHI,COUNT(*) JIAOFEI, SUM(CASE WHEN YF.CITYAUDIT='-2' or YF.CITYAUDIT = '0' THEN 1 ELSE 0 END) SHIJI,"
				+"SUM(CASE WHEN YF.COUNTYAUDITSTATUS = '2' or YF.COUNTYAUDITSTATUS = '0' THEN 1 ELSE 0 END) QXZR,"
				+"SUM(CASE WHEN YF.CITYZRAUDITSTATUS = '2' or YF.CITYZRAUDITSTATUS = '0' THEN 1 ELSE 0 END) SZR,"		
				+"SUM(DECODE(YF.caiwu, '-1', 1,'-2',1,'1',1,'0',1)) CAIWU,"
				+"SUM(DECODE(YF.AUTOAUDITSTATUS, '0', 1)) ZIDONG,SUM(DECODE(YF.MANUALAUDITSTATUS, '-2', 1,'0',1)) RENGONG"
				+" FROM ZHANDIAN Z,DIANBIAO D,dianfeidandayin YF WHERE Z.ID=D.ZDID AND D.DBID=yf.ammeterid_fk AND Z.SHSIGN='1' AND Z.QYZT='1' AND D.DBYT='dbyt01' "+whereStr+" GROUP BY SHI)C"
				+" WHERE B.SHI(+)=ZD.SHI AND C.SHI(+)=ZD.SHI AND ZD.SHSIGN='1' AND ZD.QYZT='1' "+str+" and zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"')"
				+" GROUP BY B.DIANBIAO,C.JIAOFEI,C.ZIDONG,C.RENGONG,C.SHIJI,C.CAIWU,B.SHI,C.QXZR,C.SZR");
		
		System.out.println("报账单审核不通过统计:"+sql);
		DataBase db = new DataBase();
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				SiteDetailCountBean1 bean1 = new SiteDetailCountBean1();
				
				String passzdcount1 = rs.getString(1)!=null?rs.getString(1):"0";//审核通过站点总数
				bean1.setPasszdcount1(passzdcount1);
				String xtjsdbs1 = rs.getString(2)!=null?rs.getString(2):"0";//系统结算电表数
				bean1.setXtjsdbs1(xtjsdbs1);
				String bzts1 = rs.getString(3)!=null?rs.getString(3):"0";//报账条数（交费）
				bean1.setBzts1(bzts1);
				String city1 = rs.getString(4)!=null?rs.getString(4):"";//城市
				bean1.setCity1(city1);
				String zdshts1 = rs.getString(5)!=null?rs.getString(5):"0";//自动审核条数
				bean1.setZdshts1(zdshts1);
				String xglyshts1 = rs.getString(6)!=null?rs.getString(6):"0";//县管理员审核条数（人工）
				bean1.setXglyshts1(xglyshts1);
				String sglyshts1 = rs.getString(7)!=null?rs.getString(7):"0";//市管理员审核条数（市级） 
				bean1.setSglyshts1(sglyshts1);
				String cwshts1 = rs.getString(8)!=null?rs.getString(8):"0";//财务审核条数	
				bean1.setCwshts1(cwshts1);
//				String dyts = rs.getString(9)!=null?rs.getString(9):"0";//打印条数
//				bean1.setDyts1(dyts);
				String code1 = rs.getString(9)!=null?rs.getString(9):"";//编号
				bean1.setCode1(code1);
				String qxzrshts1 = rs.getString(10)!=null?rs.getString(10):"";;//区县主任审核条数
				bean1.setQxzrshts1(qxzrshts1);
				String szrshts1 = rs.getString(11)!=null?rs.getString(11):"";;//市级主任审核条数
				bean1.setSzrshts1(szrshts1);
	           
				Double xtjsdbs2 = 0.0,bzts2 = 0.0,zdshts2= 0.0,xglyshts2 = 0.0,sglyshts2 = 0.0;
				Double cwshts2 = 0.0,qxzrshts2 = 0.0,szrshts2 = 0.0;
//				Double dyts1 = 0.0;
				
				if(xtjsdbs1!=""&&xtjsdbs1!=" "&&xtjsdbs1!=null&&xtjsdbs1!="null"){
					xtjsdbs2 = Format.str_d(xtjsdbs1);
				} 
	            if(bzts1!=""&&bzts1!=" "&&bzts1!=null&&bzts1!="null"){
	           	  	bzts2 = Double.parseDouble(bzts1); 
	            }
	            if(zdshts1!=""&&zdshts1!=" "&&zdshts1!=null&&zdshts1!="null"){
	            	zdshts2 = Format.str_d(zdshts1);
	            }
	            if(xglyshts1!=""&&xglyshts1!=" "&&xglyshts1!=null&&xglyshts1!="null"){
	            	xglyshts2 = Format.str_d(xglyshts1);
	            }
	            if(sglyshts1!=""&&sglyshts1!=" "&&sglyshts1!=null&&sglyshts1!="null"){
	            	sglyshts2 = Format.str_d(sglyshts1);
	            }
	            if(cwshts1!=""&&cwshts1!=" "&&cwshts1!=null&&cwshts1!="null"){
	            	cwshts2 = Format.str_d(cwshts1);
	            }
//	            if(dyts!=""&&dyts!=" "&&dyts!=null&&dyts!="null"){
//	            	dyts1 = Format.str_d(dyts);
//	            }
	            if(qxzrshts1!=""&&qxzrshts1!=" "&&qxzrshts1!=null&&qxzrshts1!="null"){
	            	qxzrshts2 = Double.parseDouble(qxzrshts1); 
	            }
	            if(szrshts1!=""&&szrshts1!=" "&&szrshts1!=null&&szrshts1!="null"){
	            	szrshts2 = Double.parseDouble(szrshts1); 
	            }
	            
				DecimalFormat pay = new DecimalFormat("0.00");
				String bzl1="",zdshl1="",cwshl1="",xglyshl1="",sglyshl1="",qxzrshl1="",szrshl1=""; 
//				String dyl="";
				if(bzts2 == 0){
					bzl1 = "0.00";
					zdshl1 = "0.00";
					cwshl1 = "0.00";
//					dyl = "0.00";
					xglyshl1 = "0.00";
					sglyshl1 = "0.00";
					qxzrshl1 = "0.00";
					szrshl1 = "0.00";
				}else{
					Double avg = bzts2/xtjsdbs2*100;
					bzl1 = pay.format(avg);//报账率（缴费率）
					bean1.setBzl1(bzl1);
		           
					Double avg1 = zdshts2/bzts2*100;
					zdshl1 = pay.format(avg1);//自动审核率
					bean1.setZdshl1(zdshl1);
					
					Double avg2 = xglyshts2/bzts2*100;
					xglyshl1 = pay.format(avg2);
					bean1.setXglyshl1(xglyshl1);//县管理员审核率
					
					Double avg6 = qxzrshts2/bzts2*100;
					qxzrshl1 = pay.format(avg6);
					bean1.setQxzrshl1(qxzrshl1);//区县主任审核率
					
					Double avg3 = sglyshts2/bzts2*100;
					sglyshl1 = pay.format(avg3);
					bean1.setSglyshl1(sglyshl1);//市管理员审核率
					
					Double avg7 = szrshts2/bzts2*100;
					szrshl1 = pay.format(avg7);
					bean1.setSzrshl1(szrshl1);//市级主任审核率	
		           
					Double avg4 = cwshts2/bzts2*100;
					cwshl1 = pay.format(avg4);//财务审核率
					bean1.setCwshl1(cwshl1);
		           
//					Double avg5 = dyts1/bzts1*100;
//					dyl = pay.format(avg5);//打印率
//					bean1.setDyl1(dyl);
				}
				
	          	list1.add(bean1);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list1;
	}

}
