package com.noki.tongjichaxu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class FeiyongbaozhangDao {
	//预付费
/**
 * @see全省费用及报账对照统计功能---查询预付费数据
 * @update zhouxue 2014.05.20 添加 默认条件：站点非虚拟，非采集，站点人工审核通过
 * @update zhouxue 2014.06.09 添加 默认条件：电费支付类型不能是预支的
 */
	public HashMap<String,YuefufeiBean> getYFF(String whereStr1 ,String str1){
		//List list = null;
		String sql ="SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode=zz.shi), COUNT(ee.prepayment_ammeterid), " +
				"SUM(ee.money/10000),SUM(CASE WHEN ee.financeaudit='2' THEN ee.money/10000 END)AS cwmoney," +
				"SUM( ee.NETWORKDF/10000 )AS wlyy, SUM( ee.MARKETDF/10000 )AS scjy, SUM( ee.ADMINISTRATIVEDF/10000 )AS xzzh," +
				" SUM( ee.INFORMATIZATIONDF/10000 )AS xxhzc, SUM( ee.BUILDDF/10000 )AS tzjs, SUM(ee.dddf / 10000) AS dddf," +
				" ab.ydzy1,ab.ydzy2,ab.gwzy,ab.gygtzy,ab.ydgtzy,ab.bkftzy,ab.diszdid,SUM(ee.jszq) " +
				" FROM  (SELECT shi, SUM(CASE WHEN s.zymxcode='11' THEN e.money*s.dbili*s.xjbili/100000000 END)AS ydzy1," +
				" SUM(CASE WHEN s.zymxcode='12' THEN e.money*s.dbili*s.xjbili/100000000 END)AS ydzy2, " +
				"SUM(CASE WHEN s.zymxcode='21' THEN e.money*s.dbili*s.xjbili/100000000 END)AS gwzy," +
				" SUM(CASE WHEN s.zymxcode='09' THEN e.money*s.dbili*s.xjbili/100000000 END)AS gygtzy, " +
				"SUM(CASE WHEN s.zymxcode='19' THEN e.money*s.dbili*s.xjbili/100000000 END)AS ydgtzy, " +
				"SUM(CASE WHEN s.zymxcode='00' THEN e.money*s.dbili*s.xjbili/100000000 END)AS bkftzy,COUNT(DISTINCT (CONCAT (Z.ID,E.ACCOUNTMONTH))) AS DISZDID  " +
				"FROM ZHANDIAN Z, DIANBIAO D, SBGL S,yufufeiview e WHERE Z.ID = D.ZDID AND  d.dfzflx IN('dfzflx02','dfzflx04') AND " +
				"d.dbid=s.dianbiaoid AND D.DBID = e.prepayment_ammeterid  AND D.DFZFLX<>'dfzflx03' AND Z.Xuni = '0'  AND Z.CAIJI='0' AND Z.SHSIGN='1'  " +
				"AND e.cityzrauditstatus = '1' "+whereStr1+" GROUP BY z.shi)ab," +
			    "zhandian zz,dianbiao dd,yufufeiview ee  WHERE ab.shi=zz.shi AND zz.id=dd.zdid  AND dd.dbid=ee.prepayment_ammeterid AND DD.DFZFLX<>'dfzflx03' " +
			    " AND  zz.xuni = '0'  AND ZZ.CAIJI='0' AND ZZ.SHSIGN='1' AND dd.dfzflx IN('dfzflx02','dfzflx04') AND ee.cityzrauditstatus = '1' "+str1+" GROUP  BY zz.shi,ab.ydzy1,ab.ydzy2,ab.gwzy,ab.gygtzy,ab.ydgtzy,ab.bkftzy,ab.diszdid ORDER BY zz.shi";
		 System.out.println("预付费:"+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		ResultSet rs1 = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			HashMap<String,YuefufeiBean> hs = new HashMap<String,YuefufeiBean>();
			
			while(rs.next()){
				YuefufeiBean yf = new YuefufeiBean();
				yf.setCityname(rs.getString(1));
				yf.setCount(rs.getInt(2));
				
				String s=rs.getString(3);
				if(null==s||"".equals(s)){s="0";}
				yf.setYwmoney(Double.parseDouble(s));
				
				String s1=rs.getString(4);
				if(null==s1||"".equals(s1)){s1="0";}
				yf.setCwmoney(Double.parseDouble(s1));
				
				String s2=rs.getString(5);
				if(null==s2||"".equals(s2)){s2="0";}
				yf.setWlyy(Double.parseDouble(s2));
				
				String s3=rs.getString(6);
				if(null==s3||"".equals(s3)){s3="0";}
				yf.setScjy(Double.parseDouble(s3));
				
				String s4=rs.getString(7);
				if(null==s4||"".equals(s4)){s4="0";}
				yf.setXzzh(Double.parseDouble(s4));
				
				String s5=rs.getString(8);
				if(null==s5||"".equals(s5)){s5="0";}
				yf.setXxhzc(Double.parseDouble(s5));
				
				String s6=rs.getString(9);
				if(null==s6||"".equals(s6)){s6="0";}
				yf.setTzjs(Double.parseDouble(s6));
				
				String s7=rs.getString(10);
				if(null==s7||"".equals(s7)){s7="0";}
				yf.setDddf(Double.parseDouble(s7));
				
				yf.setQt(Double.parseDouble(s2)+Double.parseDouble(s3)+Double.parseDouble(s4)+Double.parseDouble(s5)+Double.parseDouble(s6)+Double.parseDouble(s7));
				
				String s8=rs.getString(11);
				if(null==s8||"".equals(s8)){s8="0";}
				yf.setYdzy1(Double.parseDouble(s8));
			
				String s9=rs.getString(12);
				if(null==s9||"".equals(s9)){s9="0";}
				yf.setYdzy2(Double.parseDouble(s9));
				
				String s10=rs.getString(13);
				if(null==s10||"".equals(s10)){s10="0";}
				yf.setGwzy(Double.parseDouble(s10));
				
				String s11=rs.getString(14);
				if(null==s11||"".equals(s11)){s11="0";}
				yf.setGygtzy(Double.parseDouble(s11));
			
				String s12=rs.getString(15);
				if(null==s12||"".equals(s12)){s12="0";}
				yf.setYdgtzy(Double.parseDouble(s12));
			
				String s13=rs.getString(16);
				if(null==s13||"".equals(s13)){s13="0";}
				yf.setBkftzy(Double.parseDouble(s13));
				yf.setDiszdid(rs.getInt(17));
				yf.setJszq(rs.getInt(18));

				hs.put(rs.getString(1),yf);
			}
			return hs;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return null;
		}
	//月结
	/**
	 * @see全省费用及报账对照统计功能---查询月结数据
	 * @update zhouxue 2014.05.20 添加 默认条件：站点非虚拟，非采集，站点人工审核通过
	 */
	public HashMap<String,YuejieBean> getYJ(String whereStr,String str){
		
		String sql = "SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode=zz.shi), " +
				"COUNT(ee.electricfee_id), SUM(ee.actualpay/10000), SUM(CASE WHEN ee.manualauditstatus='2' THEN ee.actualpay/10000 END)AS cwmoney, SUM( ee.NETWORKDF/10000 )AS wlyy, SUM( ee.MARKETDF/10000 )AS scjy, SUM( ee.ADMINISTRATIVEDF/10000 )AS xzzh, SUM( ee.INFORMATIZATIONDF/10000 )AS xxhzc, SUM( ee.BUILDDF/10000 )AS tzjs, SUM( ee.dddf/10000 )AS dddf, " +
				"ab.ydzy1,ab.ydzy2,ab.gwzy,ab.gygtzy,ab.ydgtzy,ab.bkftzy,ab.diszdid,SUM(ee.jszq),zzs.se  " +
				" FROM  (SELECT shi, SUM(CASE WHEN s.zymxcode='11' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS ydzy1, SUM(CASE WHEN s.zymxcode='12' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS ydzy2, SUM(CASE WHEN s.zymxcode='21' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS gwzy, SUM(CASE WHEN s.zymxcode='09' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS gygtzy, SUM(CASE WHEN s.zymxcode='19' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS ydgtzy, " +
				"SUM(CASE WHEN s.zymxcode='00' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS bkftzy, COUNT(DISTINCT (CONCAT (Z.ID,E.ACCOUNTMONTH))) AS diszdid " +
				" FROM ZHANDIAN Z, DIANBIAO D, SBGL S, AMMETERDEGREES A, ELECTRICFEES E " +
				" WHERE Z.ID = D.ZDID AND d.dbid=s.dianbiaoid AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK  " +
				"AND  Z.Xuni = '0' AND Z.CAIJI='0' AND Z.SHSIGN='1' AND Z.stationtype not in ('StationType41') "+whereStr+"  GROUP  BY z.shi)ab," +
				 "( select z.shi,sum(e.actualpay)as se from  ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES A,ELECTRICFEES   E" +
				 " WHERE Z.ID = D.ZDID AND D.DBID = A.ammeterid_fk AND A.ammeterdegreeid = E.ammeterdegreeid_fk " +
				 " AND Z.Xuni = '0' AND Z.CAIJI = '0'  AND Z.SHSIGN = '1'  AND Z.stationtype='StationType41'  "+whereStr+" GROUP BY z.shi )zzs,"+
				"zhandian zz,dianbiao dd,AMMETERDEGREES aa,ELECTRICFEES ee " +
				" WHERE ab.shi=zz.shi AND zz.shi=zzs.shi(+) AND zz.id=dd.zdid  AND dd.dbid=aa.ammeterid_fk  AND aa.ammeterdegreeid=ee.ammeterdegreeid_fk  " +
				"AND  zz.xuni = '0' AND ZZ.CAIJI='0' AND ZZ.SHSIGN='1'  AND zz.stationtype not in ('StationType41')  "+str+" GROUP  BY zz.shi,ab.ydzy1,ab.ydzy2,ab.gwzy,ab.gygtzy,ab.ydgtzy,ab.bkftzy,ab.diszdid,zzs.se ORDER BY zz.shi";
		 System.out.println("月结电费:"+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			HashMap<String,YuejieBean> hh = new HashMap<String,YuejieBean>();
			while(rs.next()){
				YuejieBean yj = new YuejieBean();
				yj.setCityname(rs.getString(1));
				yj.setCount(rs.getInt(2));
	
				String s=rs.getString(3);
				if(null==s||"".equals(s)){s="0";}
				yj.setYwmoney(Double.parseDouble(s));
				
				String s1=rs.getString(4);
				if(null==s1||"".equals(s1)){s1="0";}
				yj.setCwmoney(Double.parseDouble(s1));
				
				String s2=rs.getString(5);
				if(null==s2||"".equals(s2)){s2="0";}
				yj.setWlyy(Double.parseDouble(s2));
				
				String s3=rs.getString(6);
				if(null==s3||"".equals(s3)){s3="0";}
				yj.setScjy(Double.parseDouble(s3));
				
				String s4=rs.getString(7);
				if(null==s4||"".equals(s4)){s4="0";}
				yj.setXzzh(Double.parseDouble(s4));
				
				String s5=rs.getString(8);
				if(null==s5||"".equals(s5)){s5="0";}
				yj.setXxhzc(Double.parseDouble(s5));
				
				String s6=rs.getString(9);
				if(null==s6||"".equals(s6)){s6="0";}
				yj.setTzjs(Double.parseDouble(s6));
				
				String s7=rs.getString(10);
				if(null==s7||"".equals(s7)){s7="0";}
				yj.setDddf(Double.parseDouble(s7));
				
				yj.setQt(Double.parseDouble(s2)+Double.parseDouble(s3)+Double.parseDouble(s4)+Double.parseDouble(s5)+Double.parseDouble(s6)+Double.parseDouble(s7));
				
				String s8=rs.getString(11);
				if(null==s8||"".equals(s8)){s8="0";}
				yj.setYdzy1(Double.parseDouble(s8));
			
				String s9=rs.getString(12);
				if(null==s9||"".equals(s9)){s9="0";}
				yj.setYdzy2(Double.parseDouble(s9));
				
				String s10=rs.getString(13);
				if(null==s10||"".equals(s10)){s10="0";}
				yj.setGwzy(Double.parseDouble(s10));
				
				String s11=rs.getString(14);
				if(null==s11||"".equals(s11)){s11="0";}
				yj.setGygtzy(Double.parseDouble(s11));
			
				String s12=rs.getString(15);
				if(null==s12||"".equals(s12)){s12="0";}
				yj.setYdgtzy(Double.parseDouble(s12));
			
				String s13=rs.getString(16);
				if(null==s13||"".equals(s13)){s13="0";}
				yj.setBkftzy(Double.parseDouble(s13));
				
				yj.setDiszdid(rs.getInt(17));
				yj.setJszq(rs.getInt(18));
				
				String s14=rs.getString(19);
				if(null==s14||"".equals(s14)){s14="0";}
				yj.setZzsje(Double.parseDouble(s14));
				
				hh.put(rs.getString(1), yj);
			}
		return 	hh;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return null;
		}
	public ArrayList<String> getShi(){
		
		String sql = "select d.agname from d_area_grade d where d.agcode like '137__' ORDER BY d.agcode";
		System.out.println("获取地市:"+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			ArrayList<String> hh = new ArrayList<String>();
			while(rs.next()){
				hh.add(rs.getString(1));
			}
		return 	hh;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return null;
		}
	
}
