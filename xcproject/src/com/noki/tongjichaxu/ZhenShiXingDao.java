package com.noki.tongjichaxu;


import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.tongjichaxu.javabean.cbJzBean;

public class ZhenShiXingDao {
	private int allPage;

	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}

	public int getAllPage() {
		return this.allPage;
	}
    /***
     * @author GT
     * 
     * */
	public synchronized ArrayList<cbJzBean> getData(String whereStr,String zsxstr,String property,String whereyf,String whereyf01,String whereyf02,String month,Properties pro,String bzyf,String loginId,String rolename){
		
 
		DateFormat fmt = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();// 日历对象  sd_lingjin

		String temp = "2013-04";
		String temp2 = "2013-05";
		String temp8 = "2013-08";
		String temp9 = "2013-09";
		String bzyf02 = "";
		Date date1 = null;
		Date date2 = null;
		Date date8 = null;
		Date date9 = null;
		Date bzdate = null;
		try {
			bzdate = fmt.parse(bzyf);
			
			calendar.setTime(bzdate);  
			calendar.add(Calendar.MONTH,-2);   
	        Date d2 = calendar.getTime(); 
	        bzyf02 = fmt.format(d2);
	        
			date1 = fmt.parse(temp);
			date2 = fmt.parse(temp2);
			date8= fmt.parse(temp8);
			date9= fmt.parse(temp9);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		


		
		ArrayList<cbJzBean> list = new ArrayList<cbJzBean>();
		
		double zdsxcw = Double.valueOf(pro.get("zdsxcw").toString());
		double scnhft = Double.valueOf(pro.get("scnhft").toString());
		double mycbgk = Double.valueOf(pro.get("mycbgk").toString());
		double cbzd = Double.valueOf(pro.get("cbzd").toString());
		double mycbjscb = Double.valueOf(pro.get("mycbjscb").toString());
		double lxcb = Double.valueOf(pro.get("lxcb").toString());
		double lxcbdlzj = Double.valueOf(pro.get("lxcbdlzj").toString());
		
		double pld = Double.valueOf(pro.get("pld").toString());
		double cbbl = Double.valueOf(pro.get("cbbl").toString())/100;
		double cbjzzg = Double.valueOf(pro.get("cbjzzg").toString());
		
		double cbblsyc = Double.valueOf(pro.get("cbblsyc").toString())/100;
		/*
		 * month 转换
		 * */
		//System.out.println(month);
		String bmonth=month.substring(5,7);
		String smonth=month.substring(5,7);
	    if(bmonth.equals("01")){bmonth="month1";smonth="month12";}
	    if(bmonth.equals("02")){bmonth="month2";smonth="month1";}
	    if(bmonth.equals("03")){bmonth="month3";smonth="month2";}
	    if(bmonth.equals("04")){bmonth="month4";smonth="month3";}
	    if(bmonth.equals("05")){bmonth="month5";smonth="month4";}
	    if(bmonth.equals("06")){bmonth="month6";smonth="month5";}
	    if(bmonth.equals("07")){bmonth="month7";smonth="month6";}
	    if(bmonth.equals("08")){bmonth="month8";smonth="month7";}
	    if(bmonth.equals("09")){bmonth="month9";smonth="month8";}
	    if(bmonth.equals("10")){bmonth="month10";smonth="month9";}
	    if(bmonth.equals("11")){bmonth="month11";smonth="month10";}
	    if(bmonth.equals("12")){bmonth="month12";smonth="month11";}
	    
		StringBuffer zdsxsql = new StringBuffer();
		StringBuffer scnhsql = new StringBuffer();
		StringBuffer scnhsql1 = new StringBuffer();
		StringBuffer cbgksql = new StringBuffer();
		StringBuffer cbzdsql = new StringBuffer();
		StringBuffer cbzdsql01 = new StringBuffer();
		StringBuffer cbzdsql02 = new StringBuffer();
		StringBuffer lxcbsql = new StringBuffer();
		StringBuffer lxcbsql01 = new StringBuffer();
		StringBuffer lxcbsql02 = new StringBuffer();
		StringBuffer lxcbsql03 = new StringBuffer();
		StringBuffer lxzzcbsql = new StringBuffer();
		StringBuffer lxzzcbsql01 = new StringBuffer();
		StringBuffer lxzzcbsql02 = new StringBuffer();
		StringBuffer lxzzcbsql03 = new StringBuffer();
		StringBuffer jsdbsql = new StringBuffer();
		StringBuffer jsdbsql01 = new StringBuffer();
		StringBuffer jsdbsql02 = new StringBuffer();
		StringBuffer jsdbsql03 = new StringBuffer();
		StringBuffer jsdbsql0334 = new StringBuffer();
		StringBuffer bzdlsql = new StringBuffer();
		StringBuffer bzdlsql01 = new StringBuffer();
		StringBuffer waqzgsql = new StringBuffer();
		StringBuffer waqzgsql01 = new StringBuffer();
		StringBuffer waqzgsql02 = new StringBuffer();
		
		StringBuffer lxcbsql033 = new StringBuffer();
		StringBuffer lxzzcbsql033 = new StringBuffer();
		StringBuffer jsdbsql033 = new StringBuffer();
		//2013-09-10以前使用计算站点属性错误的方法，2013-09-11更换
		/*zdsxsql.append("select d.agcode, d.agname,aa.zdsxcucount from d_area_grade d left join (select zd.shi, s.agname, count(*)zdsxcucount  from zhandian zd" +
				" left join d_area_grade s  on zd.shi = s.agcode,  (select zdid from jzxx  where 1=1 "+whereyf01+" group by zdid) t" +
				" where to_char(zd.id) = t.zdid and zd.property <> 'zdsx02' AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T " +
				"WHERE T.ACCOUNTID = '"+loginId+"')) and zd.qyzt = '1'"+whereStr+" group by zd.shi, s.agname) aa on d.agcode = aa.shi" +
				"  where d.agcode like '137__'  AND (d.agcode IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) order by d.agcode");*/
	/**	zdsxsql.append("select d.agcode, d.agname,aa.zdsxcucount from d_area_grade d left join (select zd.shi, count(*) as zdsxcucount " +
				"from zhandian zd left join zdsx zs on zd.property = zs.code and zs.fjcode = '00' " +
				"left join zdsx st on zd.stationtype = st.code and zd.property = st.fjcode where zs.name is null or st.name is null " +
				"and zd.qyzt = '1' AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T " +
				"WHERE T.ACCOUNTID = '"+loginId+"')) "+whereStr+" group by zd.shi) aa on d.agcode = aa.shi" +
				"  where d.agcode like '137__'  AND (d.agcode IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) order by d.agcode");
		
		*/
		//2014年属性错误新sql
		zdsxsql.append("select da.agcode, da.agname,d.agcode as agcode1, d.agname as agname1,aa.zdsxcucount from d_area_grade d left join d_area_grade da" +
				" on d.fagcode = da.agcode left join (select zd.xian, count(*) as zdsxcucount from zhandian zd left join zdsx zs" +
				" on zd.property = zs.code and zs.fjcode = '00' left join zdsx st on zd.stationtype = st.code and zd.property = st.fjcode " +
				"where zs.name is null or st.name is null and zd.qyzt = '1' AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T " +
				"WHERE T.ACCOUNTID = '"+loginId+"'))  group by zd.xian) aa on d.agcode = aa.xian where d.agrade='3' and da.agrade = '2' "+zsxstr+" order by d.agcode");
		/*scnhsql.append("select  ft.shiname agname, count(*) scnhcount from (select zd.shi, (select agname from d_area_grade where agcode = zd.shi) as shiname, (select agname" +
				" from d_area_grade where agcode = zd.xian) as xian, (select agname from d_area_grade where agcode = zd.xiaoqu) as xiaoqu, zd.id, zd.jzname," +
				" (select name from indexs where code = zd.property) as zdsx, (select name  from indexs where code = zd.stationtype) as zdlx," +
				" max(decode(s.shuoshuzhuanye, 'zylx01', s.dbili)) as sc from zhandian zd, dianbiao db, sbgl s" +
				" where zd.property in ('zdsx03', 'zdsx06', 'zdsx01') and zd.stationtype not in ('StationType12','StationType20') and zd.id = db.zdid  and zd.qyzt = '1'  and db.dbqyzt = '1' " +
				"AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and db.dbid = s.dianbiaoid and db.dbyt = 'dbyt01'"+whereStr +
				"  group by zd.shi, zd.xian,  zd.xiaoqu,  zd.id, zd.jzname,  zd.property,  zd.stationtype) ft  where to_number(ft.sc) = 100  group by  ft.shiname");
		*/
		//2013年9月前分摊sql
		scnhsql.append("select da.agcode, da.agname, d.agcode as agcode1, d.agname as agname1, count(*) scnhcount  from d_area_grade d" +
				" left join d_area_grade da on d.fagcode = da.agcode left join (select zd.shi,zd.xian xiancode," +
				"(select agname from d_area_grade where agcode = zd.shi) as shiname,(select agname from d_area_grade" +
				" where agcode = zd.xian) as xian, (select agname from d_area_grade where agcode = zd.xiaoqu) as xiaoqu," +
				" zd.id, zd.jzname,(select name from indexs where code = zd.property) as zdsx,(select name from indexs " +
				"where code = zd.stationtype) as zdlx, max(decode(s.shuoshuzhuanye, 'zylx01', s.dbili)) as sc  from" +
				" zhandian zd, dianbiao db, sbgl s  where zd.property in ('zdsx06', 'zdsx01')  " +
				" and zd.id = db.zdid and zd.qyzt = '1' and db.dbqyzt = '1' AND" +
				" (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and db.dbid = s.dianbiaoid" +
				" and db.dbyt = 'dbyt01' group by zd.shi,zd.xian,zd.xiaoqu,zd.id, zd.jzname, zd.property, zd.stationtype)" +
				" ft on d.agcode = ft.xiancode where to_number(ft.sc) >= 100 "+zsxstr+"" +
				" and d.agrade = '3' and da.agrade = '2' group by da.agcode, da.agname, d.agcode, d.agname");
		//2014年分摊新sql
		scnhsql1.append("select da.agcode, da.agname, d.agcode as agcode1, d.agname as agname1, count(*) scnhcount  from d_area_grade d" +
				" left join d_area_grade da on d.fagcode = da.agcode left join (select zd.shi,zd.xian xiancode," +
				"(select agname from d_area_grade where agcode = zd.shi) as shiname,(select agname from d_area_grade" +
				" where agcode = zd.xian) as xian, (select agname from d_area_grade where agcode = zd.xiaoqu) as xiaoqu," +
				" zd.id, zd.jzname,(select name from indexs where code = zd.property) as zdsx,(select name from indexs " +
				"where code = zd.stationtype) as zdlx, max(decode(s.shuoshuzhuanye, 'zylx01', s.dbili)) as sc  from" +
				" zhandian zd, dianbiao db, sbgl s  where zd.property in ('zdsx06', 'zdsx01')  " +
				" and zd.id = db.zdid and zd.qyzt = '1' and db.dbqyzt = '1' AND" +
				" (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and db.dbid = s.dianbiaoid" +
				" and db.dbyt = 'dbyt01' group by zd.shi,zd.xian,zd.xiaoqu,zd.id, zd.jzname, zd.property, zd.stationtype)" +
				" ft on d.agcode = ft.xiancode where to_number(ft.sc) >= 55 "+zsxstr+"" +
				" and d.agrade = '3' and da.agrade = '2' group by da.agcode, da.agname, d.agcode, d.agname");
		/**
		scnhsql1.append("select  ft.shiname agname, count(*) scnhcount from (select zd.shi, (select agname from d_area_grade where agcode = zd.shi) as shiname, (select agname" +
				" from d_area_grade where agcode = zd.xian) as xian, (select agname from d_area_grade where agcode = zd.xiaoqu) as xiaoqu, zd.id, zd.jzname," +
				" (select name from indexs where code = zd.property) as zdsx, (select name  from indexs where code = zd.stationtype) as zdlx," +
				" max(decode(s.shuoshuzhuanye, 'zylx01', s.dbili)) as sc from zhandian zd, dianbiao db, sbgl s" +
				" where zd.property in ('zdsx03', 'zdsx06', 'zdsx01') and zd.stationtype not in ('StationType12','StationType20') and zd.id = db.zdid  and zd.qyzt = '1'  and db.dbqyzt = '1' " +
				"AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and db.dbid = s.dianbiaoid and db.dbyt = 'dbyt01'"+whereStr +
				"  group by zd.shi, zd.xian,  zd.xiaoqu,  zd.id, zd.jzname,  zd.property,  zd.stationtype) ft  where to_number(ft.sc) >= 55  group by  ft.shiname");
		
		cbgksql.append("select  cbqk.agname, count(*) cbgkcount from (select zd.shi,  s.agname, zd.id, zd.jzname, dl.jsdl   as dl1, dl.gldl   as dl2" +
				"  from zhandian zd left join d_area_grade s on zd.shi = s.agcode,(select zdid, jsdl, gldl from jzxx " +
				"  where 1=1 "+whereyf01+") dl  where to_char(zd.id) = dl.zdid AND zd.property='"+property+"' " +
				"AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+") cbqk " +
				"where cbqk.dl1 is null and cbqk.dl2 is null  group by cbqk.shi, cbqk.agname");
		//-----------
		 **/
		 //2014年抄表管控新sql
		cbgksql.append("select da.agcode, da.agname, d.agcode as agcode1, d.agname as agname1, cbqk.cbgkcount from d_area_grade d " +
				"left join d_area_grade da on d.fagcode = da.agcode left join (select zd.shi, zd.xian," +
				" dl.dbydl as dl1,dl.dbgldl as dl2, count(*) cbgkcount from zhandian zd, (select zdid,  dbydl,dbgldl from jzxx" +
				" where 1 = 1 "+whereyf01+") dl where to_char(zd.id) = dl.zdid AND zd.property = '"+property+"' AND (zd.xiaoqu" +
				" IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by zd.shi,zd.xian," +
				"dl.dbydl,dl.DBGLDL ) cbqk on d.agcode = cbqk.xian where cbqk.dl1 is null  and cbqk.dl2 is null "+zsxstr+"" +
				" and d.agrade = '3' and da.agrade = '2' order by d.agcode");
		/*
		cbzdsql.append("select bb.shi agname ,sum(case when bb.countbz>="+cbbl+" then 1 else  0 end) cbzdcount from(select ab.shi,(ab.dl-ab.bz)/ab.bz as countbz from" +
				" (select jz.zdid,jz.sfgx,jz.shi, decode(jz.jsdl,'',jz.gldl,'0',jz.gldl,jz.jsdl)dl,decode(trim(jz.sfgx),'1',zd."+bmonth+"+(23*jz.gxgwsl),'0',zd."+bmonth+",'',zd."+bmonth+",0) bz" +
				" from jzxx jz,zdnhbz zd,zhandian zd1 where jz.changjia=zd.changjia and jz.g2=zd.g2 and jz.g3=zd.g3 and jz.zp=zd.zp and jz.zs=zd.zs " +

				"AND jz.zdid=to_char(zd1.id) AND zd1.property='"+property+"' AND (zd1.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+" "+whereyf01+")ab)bb " +
		" group by bb.shi");
				//"AND jz.zdid=to_char(zd1.id) AND zd1.property='zdsx02' and jz.jsdl>0 AND (zd1.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) "+whereyf01+")ab)bb " +
		*/
		//超标站点2013-03、4月sql新
		cbzdsql.append(" select da.agcode,da.agname,d.agcode as agcode1,d.agname as agname1, cbzd.cbzdcount" +
				" from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join " +
				"(select zd1.xian, sum(case when bb.countbz >= "+cbbl+" then 1 else 0 end) cbzdcount from " +
				"(select ab.shi,ab.zdid,(ab.dl - ab.bz) / ab.bz as countbz from (select jz.zdid ,jz.sfgx," +
				"jz.shi, JZ.DBYDL AS DL, JZ.BZZ AS BZ, decode(trim(jz.sfgx), '1'," +
				"zd."+bmonth+" + (23 * jz.gxgwsl),'0', zd."+bmonth+", '',zd."+bmonth+",  0) bz from jzxx jz, zdnhbz zd " +
				"where jz.changjia = zd.changjia and jz.g2 = zd.g2 and jz.g3 = zd.g3 and jz.zp = zd.zp " +
				"and jz.zs = zd.zs "+whereyf01+")ab) bb, zhandian zd1  where bb.zdid = to_char(zd1.id)" +
				" AND zd1.property = '"+property+"' group by zd1.xian) cbzd on d.agcode = cbzd.xian where " +
				"d.agrade = '3'  and da.agrade = '2' "+zsxstr+" order by d.agcode");
				
		//二期整改数据 2013年 五月以后的数据使用  （艹）
		/*cbzdsql01.append("select bb.shi agname, sum(case when bb.countbz >= "+cbbl+" then 1 else  0  end) cbzdcount from (select ab.shi," +
				" decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid," +
				" jz.shi,  decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl) dl," +
				"jz.qsdbdl as bz  from jzxx jz,zhandian zd where 1=1 AND jz.zdid=to_char(zd.id) " +

				"AND zd.property='"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+" "+whereyf01+") ab) bb group by bb.shi");
		*/
		//2013-08数据查询
		cbzdsql01.append("select da.agcode, da.agname,d.agcode as agcode1, d.agname as agname1," +
				" cbzd.cbzdcount from d_area_grade d left join d_area_grade da on d.fagcode = " +
				"da.agcode left join (select zd.shi,zd.xian,sum(case  when bb.countbz >= "+cbbl+" then 1" +
				" else 0 end) cbzdcount from (select ab.shi,ab.zdid,  decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) " +
				"as countbz  from (select jz.zdid, jz.shi,JZ.DBYDL AS DL, JZ.BZZ AS BZ " +
				"  from jzxx jz where 1 = 1 " +
				""+whereyf01+") ab)bb, zhandian zd   where to_char(zd.id) = bb.zdid AND" +
				" zd.property = '"+property+"'  AND (zd.xiaoqu IN (SELECT T.AGCODE  FROM PER_AREA T " +
				"WHERE T.ACCOUNTID = '"+loginId+"'))  group by zd.shi, zd.xian)cbzd on d.agcode = " +
				"cbzd.xian  where d.agrade = '3' and da.agrade = '2' "+zsxstr+" " +
				"order by d.agcode");
				//"AND zd.property='zdsx02' and jz.jsdl>0 AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) "+whereyf01+") ab) bb group by bb.shi");

		//超标站点2013-9月份之后数据去掉管理
		/*cbzdsql02.append("select bb.shi agname, sum(case when bb.countbz >= "+cbbl+" then 1 else  0  end) cbzdcount from (select ab.shi," +
				" decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid," +
				" jz.shi,jz.jsdl as dl," +
				"jz.qsdbdl as bz  from jzxx jz,zhandian zd where 1=1 AND jz.zdid=to_char(zd.id) " +

				"AND zd.property='"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+" "+whereyf01+") ab) bb group by bb.shi");

				//"AND zd.property='zdsx02' AND jz.jsdl>0 AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) "+whereyf01+") ab) bb group by bb.shi");

		//----------------
		 */
		 //2014年超标站点新sql
		cbzdsql02.append("select da.agcode, da.agname, d.agcode as agcode1, d.agname as agname1, cbqk.cbzdcount " +
				"from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join " +
				"(select zd.shi,zd.xian,sum(case when decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) >= "+cbbl+" " +
				"then  1 else  0 end) cbzdcount from zhandian zd ,(select jz.zdid, JZ.DBYDL AS DL, JZ.BZZ AS BZ " +
				"from jzxx jz  where 1 = 1 "+whereyf01+") ab where to_char(zd.id) = ab.zdid  " +
				"AND zd.property = '"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))" +
				"  group by zd.shi,zd.xian) cbqk on d.agcode = cbqk.xian where d.agrade = '3' and da.agrade='2' "+zsxstr+" order by d.agcode");
		/*
		lxcbsql.append("select a.shi agname,count(a.countbz) lxcbcount from(select ab.shi,ab.zdid,(ab.dl-ab.bz)/ab.bz as countbz from" +
				" (select jz.zdid,jz.sfgx,jz.shi, decode(jz.jsdl,'',jz.gldl,'0',jz.gldl,jz.jsdl)dl,decode(trim(jz.sfgx),'1',zd."+smonth+"+(23*jz.gxgwsl),'0',zd."+smonth+",'',zd."+smonth+",0) bz" +
				" from jzxx jz,zdnhbz zd where jz.changjia=zd.changjia and jz.g2=zd.g2 and jz.g3=zd.g3 and jz.zp=zd.zp and jz.zs=zd.zs "+whereyf02+")ab)a," +
				"(select ab.zdid,ab.shi,(ab.dl-ab.bz)/ab.bz as countbz from(select jz.zdid,jz.sfgx,jz.shi, " +
				"decode(jz.jsdl,'',jz.gldl,'0',jz.gldl,jz.jsdl)dl,decode(trim(jz.sfgx),'1',zd."+bmonth+"+(23*jz.gxgwsl),'0',zd."+bmonth+",'',zd."+bmonth+",0) bz" +
				"  from jzxx jz,zdnhbz zd where jz.changjia=zd.changjia and jz.g2=zd.g2 and jz.g3=zd.g3 and jz.zp=zd.zp and jz.zs=zd.zs "+whereyf01+")ab)b,zhandian zd" +
				"  where a.zdid=b.zdid AND b.zdid=to_char(zd.id) " +
				"AND zd.property='"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+"  " +
				"and a.countbz>="+cbblsyc+" and b.countbz>="+cbbl+" group by a.shi");
		*/
		//连续超标sql2013-03、04新
		lxcbsql.append("select da.agcode,da.agname,d.agcode as agcode1,d.agname as agname1, cc.lxcbcount" +
				" from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join (select " +
				"zd.xian, count(*) lxcbcount from (select ab.shi, ab.zdid, (ab.dl - ab.bz) / ab.bz as countbz" +
				" from (select jz.zdid,jz.sfgx, jz.shi, decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl) dl," +
				" decode(trim(jz.sfgx), '1', zd."+smonth+" + (23 * jz.gxgwsl), '0', zd."+smonth+", '',  zd."+smonth+", 0) bz" +
				"  from jzxx jz, zdnhbz zd  where jz.changjia = zd.changjia and jz.g2 = zd.g2  and jz.g3 = zd.g3" +
				" and jz.zp = zd.zp and jz.zs = zd.zs "+whereyf02+") ab) a, (select ab.zdid, ab.shi," +
				" (ab.dl - ab.bz) / ab.bz as countbz from (select jz.zdid,jz.sfgx,jz.shi,decode(jz.jsdl, '', " +
				"jz.gldl, '0', jz.gldl, jz.jsdl) dl,decode(trim(jz.sfgx),'1',zd."+bmonth+" + (23 * jz.gxgwsl), '0'," +
				"zd."+bmonth+", '', zd."+bmonth+",0) bz from jzxx jz, zdnhbz zd where jz.changjia = zd.changjia and " +
				"jz.g2 = zd.g2 and jz.g3 = zd.g3 and jz.zp = zd.zp and jz.zs = zd.zs "+whereyf01+") ab) " +
				"b, zhandian zd where a.zdid = b.zdid AND b.zdid = to_char(zd.id) AND zd.property = '"+property+"' AND" +
				" (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) " +
				"and a.countbz >= "+cbblsyc+" and b.countbz >= "+cbbl+" group by zd.xian)cc on d.agcode = cc.xian " +
				"where d.agrade = '3' and da.agrade = '2' "+zsxstr+" order by d.agcode");
		/*lxcbsql02.append("select a.shi agname,count(a.countbz) lxcbcount from(select ab.shi,ab.zdid,(ab.dl-ab.bz)/ab.bz as countbz from" +
				" (select jz.zdid,jz.sfgx,jz.shi, decode(jz.jsdl,'',jz.gldl,'0',jz.gldl,jz.jsdl)dl,decode(trim(jz.sfgx),'1',zd."+smonth+"+(23*jz.gxgwsl),'0',zd."+smonth+",'',zd."+smonth+",0) bz" +
				" from jzxx jz,zdnhbz zd where jz.changjia=zd.changjia and jz.g2=zd.g2 and jz.g3=zd.g3 and jz.zp=zd.zp and jz.zs=zd.zs "+whereyf02+")ab)a," +
				" (select ab.zdid, ab.shi,decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz" +
				"  from (select jz.zdid,jz.shi, decode(jz.jsdl,'',jz.gldl,'0',jz.gldl,jz.jsdl)dl,jz.qsdbdl as bz from jzxx jz" +
				"  where  1=1 "+whereyf01+") ab) b,zhandian zd" +
				"  where a.zdid=b.zdid AND b.zdid=to_char(zd.id) " +
				"AND zd.property='"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+"  " +
				"and a.countbz>="+cbblsyc+" and b.countbz>="+cbbl+" group by a.shi");
		*/
		//2013-05月份的连续超标sql
		lxcbsql02.append("select da.agcode,da.agname, d.agcode as agcode1,d.agname as agname1,  " +
				"cc.lxcbcount from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode " +
				"left join (select zd.xian, count(*) lxcbcount from (select ab.shi, ab.zdid, (ab.dl - ab.bz)" +
				" / ab.bz as countbz from (select jz.zdid,jz.sfgx,jz.shi,decode(jz.jsdl, '', jz.gldl, '0', " +
				"jz.gldl, jz.jsdl) dl, decode(trim(jz.sfgx),'1', zd."+smonth+" + (23 * jz.gxgwsl), '0', zd."+smonth+"," +
				" '', zd."+smonth+", 0) bz  from jzxx jz, zdnhbz zd,zhandian z where jz.changjia = zd.changjia " +
				"and z.id=jz.zdid and jz.g2 = zd.g2 and jz.g3 = zd.g3 and jz.zp = zd.zp and jz.zs = zd.zs " +
				""+whereyf02+" and z.property='"+property+"') ab) a, (select ab.zdid, ab.shi,decode(ab.bz," +
				" '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid,jz.shi,decode(jz.jsdl, " +
				"'', jz.gldl, '0', jz.gldl, jz.jsdl) dl, jz.qsdbdl as bz from jzxx jz,zhandian z where 1 = 1 " +
				"and z.id=jz.zdid "+whereyf01+" and z.property='"+property+"') ab) b, zhandian zd where a" +
				".zdid = b.zdid AND b.zdid = to_char(zd.id) AND zd.property = '"+property+"' AND (zd.xiaoqu " +
				"IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and a.countbz >= "+cbblsyc+" and" +
				" b.countbz >= "+cbbl+" group by zd.xian) cc on d.agcode = cc.xian where d.agrade = '3'  " +
				"and da.agrade = '2' "+zsxstr+" order by d.agcode");
		//二期整改数据 2013年 五月以后的数据使用  （艹）
	/*	lxcbsql01.append("select a.shi agname, count(a.countbz) lxcbcount from (select ab.shi,ab.zdid," +
				" decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi,decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl) dl, jz.qsdbdl bz  from jzxx jz" +
				"   where 1=1 "+whereyf02+") ab) a, (select ab.zdid, ab.shi,decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz" +
				"  from (select jz.zdid,jz.shi,decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl) dl,jz.qsdbdl as bz from jzxx jz" +
				"   where  1=1 "+whereyf01+") ab) b,zhandian zd where a.zdid = b.zdid AND b.zdid=to_char(zd.id) " +
				"AND zd.property='"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+"  " +
				"and a.countbz >= "+cbblsyc+" and b.countbz >= "+cbbl+" group by a.shi");
		*/
		//2013-08连续超标sql
		lxcbsql01.append("select da.agcode,da.agname, d.agcode as agcode1,d.agname as agname1,cc.lxcbcount from " +
				"d_area_grade d  left join d_area_grade da on d.fagcode = da.agcode left join (select zd.xian, count(*) " +
				"lxcbcount  from (select ab.shi, ab.zdid, decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz" +
				" from (select jz.zdid,  jz.shi, decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl) dl, jz.qsdbdl bz  " +
				"from jzxx jz,zhandian z where 1 = 1 and jz.zdid = z.id "+whereyf02+" and z.property='"+property+"') ab) a," +
				" (select ab.zdid,ab.shi, decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz from" +
				" (select jz.zdid, jz.shi, decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl) dl, jz.qsdbdl" +
				" as bz from jzxx jz,zhandian z where 1 = 1 and jz.zdid = z.id "+whereyf01+" and" +
				" z.property='"+property+"') ab) b, zhandian zd where a.zdid = b.zdid AND b.zdid = to_char(zd.id) " +
				" AND zd.property = '"+property+"' AND (zd.xiaoqu IN  (SELECT T.AGCODE FROM PER_AREA T WHERE" +
				" T.ACCOUNTID = '"+loginId+"'))  and a.countbz >= "+cbblsyc+" and b.countbz >= "+cbbl+" group by zd.xian) cc on" +
				" d.agcode = cc.xian where d.agrade = '3' and da.agrade = '2' "+zsxstr+" order by d.agcode");
		
		//2013-9月份之后的
		/*lxcbsql03.append("select a.shi agname, count(a.countbz) lxcbcount from (select ab.shi,ab.zdid," +
				" decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi,jz.jsdl as dl, jz.qsdbdl bz  from jzxx jz" +
				"   where 1=1 "+whereyf02+") ab) a, (select ab.zdid, ab.shi,decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz" +
				"  from (select jz.zdid,jz.shi,jz.jsdl as dl,jz.qsdbdl as bz from jzxx jz" +
				"   where  1=1 "+whereyf01+") ab) b,zhandian zd where a.zdid = b.zdid AND b.zdid=to_char(zd.id) " +
				"AND zd.property='"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+"  " +
				"and a.countbz >= "+cbblsyc+" and b.countbz >= "+cbbl+" group by a.shi");
		*/
		//2014连续超标新sql
		lxcbsql03.append("  select da.agcode, da.agname,d.agcode as agcode1,d.agname as agname1,cc.lxcbcount " +
				" from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join( select  " +
				"zd.xian,count(*)lxcbcount from(select ab.shi,ab.zdid, decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz" +
				" from (select jz.zdid, jz.shi, jz.dbydl as dl, jz.bzz bz from jzxx jz where 1 = 1 "+whereyf02+") ab) a," +
				" (select ab.zdid, ab.shi, decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi," +
				" jz.dbydl as dl, jz.bzz as bz  from jzxx jz where 1 = 1 "+whereyf01+") ab) b, zhandian zd where" +
				" a.zdid = b.zdid AND b.zdid = to_char(zd.id) AND zd.property = '"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE" +
				" FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and a.countbz >= "+cbblsyc+" and b.countbz >= "+cbbl+" group by zd.xian )cc " +
				"on d.agcode = cc.xian where d.agrade = '3' and da.agrade = '2' "+zsxstr+" order by d.agcode");
		
		/*lxcbsql033.append("select a.shi agname, count(a.countbz) lxcbcount from (select ab.shi,ab.zdid," +
				" decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi,jz.jsdl as dl, jz.qsdbdl bz  from jzxx jz" +
				"   where 1=1 "+whereyf02+") ab) a, (select ab.zdid, ab.shi,decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz" +
				"  from (select jz.zdid,jz.shi,jz.jsdl as dl,jz.qsdbdl as bz from jzxx jz" +
				"   where  1=1 "+whereyf01+") ab) b,zhandian zd where a.zdid = b.zdid AND b.zdid=to_char(zd.id) " +
				"AND zd.property='"+property+"' " +
				"and a.countbz >= "+cbblsyc+" and b.countbz >= "+cbbl+" group by a.shi");
		*/
		//2014连续超标新sql(针对admins和凌奶奶用户直接去掉权限sql)
		lxcbsql033.append("  select da.agcode, da.agname,d.agcode as agcode1,d.agname as agname1,cc.lxcbcount " +
				" from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join( select  " +
				"zd.xian,count(*)lxcbcount from(select ab.shi,ab.zdid, decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz" +
				" from (select jz.zdid, jz.shi, jz.dbydl as dl, jz.bzz bz from jzxx jz,zhandian z where 1 = 1 and z.id=jz.zdid "+whereyf02+" and z.property = '"+property+"') ab) a," +
				" (select ab.zdid, ab.shi, decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi," +
				" jz.dbydl as dl, jz.bzz as bz  from jzxx jz,zhandian z where 1 = 1 and z.id=jz.zdid "+whereyf01+" and z.property = '"+property+"') ab) b, zhandian zd where" +
				" a.zdid = b.zdid AND b.zdid = to_char(zd.id) AND zd.property = '"+property+"'" +
				" and a.countbz >= "+cbblsyc+" and b.countbz >= "+cbbl+" group by zd.xian )cc " +
				"on d.agcode = cc.xian where d.agrade = '3' and da.agrade = '2' "+zsxstr+" order by d.agcode");
		/*
		lxzzcbsql.append("select a.shi agname,count(a.countbz) lxzzcbcount from(select ab.shi,ab.zdid,ab.dl,(ab.dl-ab.bz)/ab.bz as countbz from" +
				" (select jz.zdid,jz.sfgx,jz.shi,decode(jz.jsdl,'',jz.gldl,'0',jz.gldl,jz.jsdl)dl,decode(trim(jz.sfgx),'1',zd."+smonth+"+(23*jz.gxgwsl),'0',zd."+smonth+",'',zd."+smonth+",0) bz" +
				"  from jzxx jz,zdnhbz zd where jz.changjia=zd.changjia and jz.g2=zd.g2 and jz.g3=zd.g3 and jz.zp=zd.zp and jz.zs=zd.zs "+whereyf02+")ab)a," +
				" (select ab.zdid,ab.shi,ab.dl,(ab.dl-ab.bz)/ab.bz as countbz from(select jz.zdid,jz.sfgx,jz.shi, " +
				" decode(jz.jsdl,'',jz.gldl,'0',jz.gldl,jz.jsdl)dl,decode(trim(jz.sfgx),'1',zd."+bmonth+"+(23*jz.gxgwsl),'0',zd."+bmonth+",'',zd."+bmonth+",0) bz" +
				"  from jzxx jz,zdnhbz zd where jz.changjia=zd.changjia and jz.g2=zd.g2 and jz.g3=zd.g3 and jz.zp=zd.zp and jz.zs=zd.zs  "+whereyf01+")ab)b,zhandian zd" +
				" where a.zdid=b.zdid AND b.zdid=to_char(zd.id) " +
				"AND zd.property='"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) " +
				"and a.countbz>="+cbblsyc+" and b.countbz>="+cbbl+" and b.dl>a.dl  group by a.shi" +
				"");
				*/
		//连续增长超标sql2013-03、04新
		lxzzcbsql.append("select da.agcode,da.agname,d.agcode as agcode1,d.agname as agname1, cc.lxzzcbcount" +
				" from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join (select " +
				"zd.xian, count(*) lxzzcbcount from (select ab.shi, ab.zdid,ab.dl, (ab.dl - ab.bz) / ab.bz as countbz" +
				" from (select jz.zdid,jz.sfgx, jz.shi, decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl) dl," +
				" decode(trim(jz.sfgx), '1', zd."+smonth+" + (23 * jz.gxgwsl), '0', zd."+smonth+", '',  zd."+smonth+", 0) bz" +
				"  from jzxx jz, zdnhbz zd  where jz.changjia = zd.changjia and jz.g2 = zd.g2  and jz.g3 = zd.g3" +
				" and jz.zp = zd.zp and jz.zs = zd.zs "+whereyf02+") ab) a, (select ab.zdid, ab.shi,ab.dl," +
				" (ab.dl - ab.bz) / ab.bz as countbz from (select jz.zdid,jz.sfgx,jz.shi,decode(jz.jsdl, '', " +
				"jz.gldl, '0', jz.gldl, jz.jsdl) dl,decode(trim(jz.sfgx),'1',zd."+bmonth+" + (23 * jz.gxgwsl), '0'," +
				"zd."+bmonth+", '', zd."+bmonth+",0) bz from jzxx jz, zdnhbz zd where jz.changjia = zd.changjia and " +
				"jz.g2 = zd.g2 and jz.g3 = zd.g3 and jz.zp = zd.zp and jz.zs = zd.zs "+whereyf01+") ab) " +
				"b, zhandian zd where a.zdid = b.zdid AND b.zdid = to_char(zd.id) AND zd.property = '"+property+"' AND" +
				" (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) " +
				"and a.countbz >= "+cbblsyc+" and b.countbz >= "+cbbl+" and b.dl > a.dl group by zd.xian)cc on d.agcode = cc.xian " +
				"where d.agrade = '3' and da.agrade = '2' "+zsxstr+" order by d.agcode");
		/*lxzzcbsql02.append("select a.shi agname,count(a.countbz) lxzzcbcount from(select ab.shi,ab.zdid,ab.dl,(ab.dl-ab.bz)/ab.bz as countbz from" +
				" (select jz.zdid,jz.sfgx,jz.shi,decode(jz.jsdl,'',jz.gldl,'0',jz.gldl,jz.jsdl)dl,decode(trim(jz.sfgx),'1',zd."+smonth+"+(23*jz.gxgwsl),'0',zd."+smonth+",'',zd."+smonth+",0) bz" +
				"  from jzxx jz,zdnhbz zd where jz.changjia=zd.changjia and jz.g2=zd.g2 and jz.g3=zd.g3 and jz.zp=zd.zp and jz.zs=zd.zs "+whereyf02+")ab)a," +
				"  (select ab.zdid, ab.shi,ab.dl,decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz  from (select jz.zdid," +
				"  jz.shi, decode(jz.jsdl,'',jz.gldl,'0',jz.gldl,jz.jsdl)dl,jz.qsdbdl as bz " +
				"  from jzxx jz  where  1=1 "+whereyf01+") ab) b,zhandian zd" +
				" where a.zdid=b.zdid AND b.zdid=to_char(zd.id) " +
				"AND zd.property='"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) " +
				"and a.countbz>="+cbblsyc+" and b.countbz>="+cbbl+" and b.dl>a.dl  group by a.shi" +
				"");
		*/
		//连续增长超标2013-05月的sql
		lxzzcbsql02.append("select da.agcode, da.agname,  d.agcode as agcode1, d.agname as agname1, " +
				" cc.lxzzcbcount from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode " +
				"left join (select zd.xian, count(*) lxzzcbcount from (select ab.shi, ab.zdid, ab.dl, " +
				"(ab.dl - ab.bz) / ab.bz as countbz from (select jz.zdid,jz.sfgx,jz.shi,decode(jz.jsdl, ''," +
				" jz.gldl, '0', jz.gldl, jz.jsdl) dl, decode(trim(jz.sfgx), '1',zd."+smonth+" + (23 * jz.gxgwsl), " +
				"'0', zd."+smonth+", '', zd."+smonth+",  0) bz from jzxx jz, zdnhbz zd,zhandian z  where " +
				"jz.changjia = zd.changjia and z.id=jz.zdid  and jz.g2 = zd.g2  and jz.g3 = zd.g3 and" +
				" jz.zp = zd.zp and jz.zs = zd.zs  "+whereyf02+" and z.property='"+property+"') ab) a," +
				"(select ab.zdid, ab.shi,ab.dl, decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz" +
				" from (select jz.zdid,jz.shi,decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl) dl, " +
				"jz.qsdbdl as bz  from jzxx jz,zhandian z  where 1 = 1 and z.id=jz.zdid "+whereyf01+" " +
				"and z.property='"+property+"') ab) b, zhandian zd where a.zdid = b.zdid AND b.zdid = to_char(zd.id) " +
				"AND zd.property = '"+property+"' AND (zd.xiaoqu IN(SELECT T.AGCODE FROM PER_AREA T" +
				" WHERE T.ACCOUNTID = '"+loginId+"')) and a.countbz >= "+cbblsyc+"  and b.countbz >= "+cbbl+" and b.dl > a.dl " +
				"group by zd.xian)cc on d.agcode = cc.xian where d.agrade = '3' and da.agrade = '2'" +
				" "+zsxstr+" order by d.agcode");
		
		//二期整改数据 2013 五月以后的数据使用 （艹）
		/*lxzzcbsql01.append("select a.shi agname, count(a.countbz) lxzzcbcount from (select ab.shi,ab.zdid,ab.dl,decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz" +
				"  from (select jz.zdid,jz.shi,decode(jz.jsdl,'',jz.gldl,'0',jz.gldl,jz.jsdl)dl,jz.qsdbdl as bz from jzxx jz where 1=1 "+whereyf02+") ab) a, (select ab.zdid," +
				"  ab.shi,ab.dl,decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz  from (select jz.zdid," +
				"  jz.shi,decode(jz.jsdl,'',jz.gldl,'0',jz.gldl,jz.jsdl)dl,jz.qsdbdl as bz " +
				"  from jzxx jz  where  1=1 "+whereyf01+") ab) b,zhandian zd where a.zdid = b.zdid AND b.zdid=to_char(zd.id) " +
				"AND zd.property='"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) " +
				"  and a.countbz >= "+cbblsyc+" and b.countbz >= "+cbbl+" and b.dl > a.dl group by a.shi");
		*/
		//2013-09连续增长超标sql
		lxzzcbsql01.append("select da.agcode,da.agname,d.agcode as agcode1,d.agname as agname1, cc.lxzzcbcount " +
				"from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join(select zd.xian," +
				" count(*) lxzzcbcount from (select ab.shi,ab.zdid,ab.dl,decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) /" +
				" ab.bz) as countbz  from (select jz.zdid, jz.shi,decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl)" +
				" dl, jz.qsdbdl as bz from jzxx jz,zhandian z where 1 = 1 and jz.zdid = z.id  "+whereyf02+"" +
				" and z.property='"+property+"') ab) a, (select ab.zdid,  ab.shi,ab.dl, decode(ab.bz, '0', ab.dl, (ab.dl - " +
				"ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi, decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, " +
				"jz.jsdl) dl, jz.qsdbdl as bz from jzxx jz,zhandian z where 1 = 1 and z.id=jz.zdid " +
				"and z.property='"+property+"' "+whereyf01+" ) ab) b, zhandian zd where a.zdid = b.zdid  " +
				"AND b.zdid = to_char(zd.id) AND zd.property = '"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM " +
				"PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and a.countbz >="+cbblsyc+" and b.countbz >=  "+cbbl+" and b.dl > a.dl" +
				" group by zd.xian)cc on d.agcode = cc.xian where d.agrade = '3' and da.agrade = '2' "+zsxstr+" order by d.agcode");
		
		
		//连续增长超标2013-9之后sql
		/*lxzzcbsql03.append("select a.shi agname, count(a.countbz) lxzzcbcount from (select ab.shi,ab.zdid,ab.dl,decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz" +
				"  from (select jz.zdid,jz.shi,jz.jsdl as dl,jz.qsdbdl as bz from jzxx jz where 1=1 "+whereyf02+") ab) a, (select ab.zdid," +
				"  ab.shi,ab.dl,decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz  from (select jz.zdid," +
				"  jz.shi,jz.jsdl as dl,jz.qsdbdl as bz " +
				"  from jzxx jz  where  1=1 "+whereyf01+") ab) b,zhandian zd where a.zdid = b.zdid AND b.zdid=to_char(zd.id) " +
				"AND zd.property='"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) " +
				"  and a.countbz >= "+cbblsyc+" and b.countbz >= "+cbbl+" and b.dl > a.dl group by a.shi");
		*/
		//连续增长超标2014年新sql
		lxzzcbsql03.append("select da.agcode,  da.agname, d.agcode as agcode1,d.agname as agname1, cc.lxzzcbcount " +
				"from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join " +
				"(select zd.xian, count(*) lxzzcbcount from (select ab.shi,ab.zdid,  ab.dl, decode" +
				"(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi, jz.dbydl as dl," +
				" jz.bzz as bz from jzxx jz where 1 = 1 "+whereyf02+") ab) a,(select ab.zdid,ab.shi,ab.dl," +
				"  decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi, " +
				"jz.dbydl as dl, jz.bzz as bz from jzxx jz  where 1 = 1 "+whereyf01+") ab) b, zhandian zd " +
				"where a.zdid = b.zdid  AND b.zdid = to_char(zd.id) AND zd.property = '"+property+"' and a.countbz >= "+cbblsyc+" " +
				"and b.countbz >= "+cbbl+" and b.dl > a.dl  AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))" +
				" group by zd.xian)cc on d.agcode = cc.xian where d.agrade = '3' " +
				"and da.agrade = '2' "+zsxstr+" order by d.agcode");
		
		//连续增长超标2014年新sql(针对admins和凌奶奶用户直接去掉权限)
		lxzzcbsql033.append("select da.agcode,  da.agname, d.agcode as agcode1,d.agname as agname1, cc.lxzzcbcount " +
				"from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join " +
				"(select zd.xian, count(*) lxzzcbcount from (select ab.shi,ab.zdid,  ab.dl, decode" +
				"(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi, jz.dbydl as dl," +
				" jz.bzz as bz from jzxx jz where 1 = 1 "+whereyf02+") ab) a,(select ab.zdid,ab.shi,ab.dl," +
				"  decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi, " +
				"jz.dbydl as dl, jz.bzz as bz from jzxx jz  where 1 = 1 "+whereyf01+") ab) b, zhandian zd " +
				"where a.zdid = b.zdid  AND b.zdid = to_char(zd.id) AND zd.property = '"+property+"' and a.countbz >= "+cbblsyc+" " +
				"and b.countbz >= "+cbbl+" and b.dl > a.dl group by zd.xian)cc on d.agcode = cc.xian where d.agrade = '3' " +
				"and da.agrade = '2' "+zsxstr+" order by d.agcode");
		
		/*lxzzcbsql033.append("select a.shi agname, count(a.countbz) lxzzcbcount from (select ab.shi,ab.zdid,ab.dl,decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz" +
				"  from (select jz.zdid,jz.shi,jz.jsdl as dl,jz.qsdbdl as bz from jzxx jz where 1=1 "+whereyf02+") ab) a, (select ab.zdid," +
				"  ab.shi,ab.dl,decode(ab.bz,'0',ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz  from (select jz.zdid," +
				"  jz.shi,jz.jsdl as dl,jz.qsdbdl as bz " +
				"  from jzxx jz  where  1=1 "+whereyf01+") ab) b,zhandian zd where a.zdid = b.zdid AND b.zdid=to_char(zd.id) " +
				"AND zd.property='"+property+"' " +
				"  and a.countbz >= "+cbblsyc+" and b.countbz >= "+cbbl+" and b.dl > a.dl group by a.shi");
		//----------------------
		*/
		/*
		jsdbsql.append("select aa.shi agname,count(*) jsdbcount from  (select dl.shi,dl.zdid,dl.jzname,dl.zp,dl.zs,dl.changjia,dl.g2,dl.g3,dl.jsdl,dl."+bmonth +
				" from (select t.shi,t.zdid,t.jzname,t.zp,t.zs,t.changjia,t.g2,t.g3,t.jsdl," +
				" decode(trim(t.sfgx),'1',z."+bmonth+"+(23*t.gxgwsl),'0',z."+bmonth+",'',z."+bmonth+",0) as "+bmonth+" from jzxx t, zdnhbz z where 1=1 "+whereyf01+" and t.zp = z.zp" +
				" and t.zs = z.zs and t.changjia = z.changjia and t.g2 = z.g2 and t.g3 = z.g3 ) dl where (dl.jsdl-dl."+bmonth+")/dl."+bmonth+">="+cbbl+")aa," +
				" (select dl.shi,dl.zdid,dl.jzname,dl.zp,dl.zs,dl.changjia,dl.g2,dl.g3,dl.gldl,dl."+smonth +
				" from (select t.shi,t.zdid,t.jzname,t.zp,t.zs,t.changjia,t.g2,t.g3,decode(t.gldl, '', t.jsdl, '0', t.jsdl,t.gldl) gldl," +
				" decode(trim(t.sfgx),'1',z."+smonth+"+(23*t.gxgwsl),'0',z."+smonth+",'',z."+smonth+",0) as "+smonth+" from jzxx t,zdnhbz z where 1=1 "+whereyf02+
				" and t.zp = z.zp and t.zs = z.zs and t.changjia = z.changjia and t.g2 = z.g2 and t.g3 = z.g3) dl where (dl.gldl-dl."+smonth+")/dl."+smonth+"<"+cbblsyc+")bb,zhandian zd " +
				" where aa.zdid=bb.zdid AND bb.zdid=to_char(zd.id) " +
				"AND zd.property='"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) " +
				"group by aa.shi");
		*/
		//结算电量超标sql2013-03、04新
		jsdbsql.append(" select da.agcode, da.agname,d.agcode as agcode1,d.agname as agname2, cc.jsdbcount" +
				" from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join " +
				"(select zd.xian, count(*) jsdbcount from (select dl.shi, dl.zdid,dl.jzname,dl.zp, dl.zs, " +
				"dl.changjia,dl.g2,dl.g3, dl.jsdl, dl."+bmonth+" from (select t.shi, t.zdid, t.jzname, t.zp, t.zs," +
				" t.changjia, t.g2, t.g3, t.jsdl, decode(trim(t.sfgx), '1', z."+bmonth+" + (23 * t.gxgwsl), '0'," +
				" z."+bmonth+", '', z."+bmonth+", 0) as "+bmonth+"  from jzxx t, zdnhbz z   where 1 = 1 " +
				""+whereyf01+" and t.zp = z.zp  and t.zs = z.zs and t.changjia = z.changjia " +
				"and t.g2 = z.g2 and t.g3 = z.g3) dl where (dl.jsdl - dl."+bmonth+") / dl."+bmonth+" >= "+cbbl+") aa, " +
				"(select dl.shi, dl.zdid, dl.jzname,dl.zp, dl.zs, dl.changjia, dl.g2,dl.g3, dl.gldl, " +
				"dl."+smonth+" from (select t.shi,t.zdid, t.jzname,t.zp,t.zs,t.changjia, t.g2, t.g3," +
				"decode(t.gldl, '', t.jsdl, '0', t.jsdl, t.gldl) gldl, decode(trim(t.sfgx), '1',z."+smonth+" +" +
				" (23 * t.gxgwsl),'0', z."+smonth+", '', z."+smonth+", 0) as "+smonth+" from jzxx t, zdnhbz z " +
				"where 1 = 1 "+whereyf02+" and t.zp = z.zp and t.zs = z.zs and t.changjia = z.changjia" +
				" and t.g2 = z.g2 and t.g3 = z.g3) dl where (dl.gldl - dl."+smonth+") / dl."+smonth+" < "+cbblsyc+") bb, " +
				"zhandian zd where aa.zdid = bb.zdid AND bb.zdid = to_char(zd.id) AND zd.property = '"+property+"'" +
				" AND (zd.xiaoqu IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID =  '"+loginId+"'))group by zd.xian)cc" +
				" on d.agcode = cc.xian where d.agrade = '3' and da.agrade = '2' "+zsxstr+" order by d.agcode");
		
		
		/*jsdbsql02.append("select aa.shi agname,count(*) jsdbcount from  (select dl.shi,dl.zdid,dl.jzname, dl.jsdl, dl.qsdbdl  " +
				"from (select t.shi, t.zdid, t.jzname, t.jsdl, t.qsdbdl  from jzxx t  where 1 = 1" +
				"  "+whereyf01+") dl where  (dl.jsdl - dl.qsdbdl) / decode( dl.qsdbdl,'0','1',dl.qsdbdl) >= "+cbbl+") aa," +
				" (select dl.shi,dl.zdid,dl.jzname,dl.zp,dl.zs,dl.changjia,dl.g2,dl.g3,dl.gldl,dl."+smonth +
				" from (select t.shi,t.zdid,t.jzname,t.zp,t.zs,t.changjia,t.g2,t.g3,decode(t.gldl, '', t.jsdl, '0', t.jsdl,t.gldl) gldl," +
				" decode(trim(t.sfgx),'1',z."+smonth+"+(23*t.gxgwsl),'0',z."+smonth+",'',z."+smonth+",0) as "+smonth+" from jzxx t,zdnhbz z where 1=1 "+whereyf02+
				"  and t.zp = z.zp and t.zs = z.zs and t.changjia = z.changjia and t.g2 = z.g2 and t.g3 = z.g3 " +
				") dl where (dl.gldl-dl."+smonth+")/dl."+smonth+"<"+cbblsyc+") bb,zhandian zd where aa.zdid=bb.zdid AND bb.zdid=to_char(zd.id) " +
				"AND zd.property='"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) " +
				"group by aa.shi");
		*/
		//结算电量超标2013-05月sql新
		jsdbsql02.append("select da.agcode, da.agname, d.agcode as agcode1, d.agname as agname2, cc.jsdbcount " +
				"from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join (select zd.xian," +
				" count(*) jsdbcount from (select dl.shi, dl.zdid, dl.jzname, round(dl.jsdl,4), dl.qsdbdl from " +
				"(select t.shi, t.zdid, t.jzname, t.jsdl, t.qsdbdl from jzxx t ,zhandian z where 1 = 1 " +
				"and t.zdid=z.id "+whereyf01+" and z.property='"+property+"') dl where (dl.jsdl - dl.qsdbdl)" +
				" /decode(dl.qsdbdl, '0', '1', dl.qsdbdl) >= "+cbbl+") aa, (select dl.shi, dl.zdid,dl.jzname,dl.zp," +
				"dl.zs,dl.changjia,dl.g2,dl.g3,dl.gldl, dl."+smonth+" from (select t.shi,t.zdid,t.jzname,t.zp,t.zs," +
				"t.changjia,t.g2,t.g3,decode(t.gldl, '', t.jsdl, '0', t.jsdl, t.gldl) gldl, decode(trim(t.sfgx)," +
				" '1',z."+smonth+" + (23 * t.gxgwsl),'0',z."+smonth+", '',z."+smonth+", 0) as "+smonth+" from jzxx t, zdnhbz z," +
				"zhandian zz where 1 = 1 and zz.id=t.zdid  "+whereyf02+" and zz.property='"+property+"' and " +
				"t.zp = z.zp and t.zs = z.zs and t.changjia = z.changjia and t.g2 = z.g2 and t.g3 = z.g3) dl " +
				"where (dl.gldl - dl."+smonth+") / dl."+smonth+" < "+cbblsyc+") bb,zhandian zd where aa.zdid = bb.zdid AND" +
				" bb.zdid = to_char(zd.id) AND zd.property = '"+property+"'  AND (zd.xiaoqu IN (SELECT T.AGCODE " +
				"FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by zd.xian) cc on d.agcode = cc.xian where" +
				" d.agrade = '3' and da.agrade = '2' "+zsxstr+" order by d.agcode");
		
		//二期整改数据 2013 五月以后的数据使用 （艹）
		/*jsdbsql01.append("select aa.shi agname, count(*) jsdbcount from (select dl.shi,dl.zdid,dl.jzname, dl.jsdl, dl.qsdbdl  from (select t.shi, t.zdid, t.jzname, t.jsdl, t.qsdbdl  from jzxx t  where 1 = 1" +
				"  "+whereyf01+") dl where  (dl.jsdl - dl.qsdbdl) / decode( dl.qsdbdl,'0','1',dl.qsdbdl) >= "+cbbl+") aa,(select dl.shi,  dl.zdid,  dl.jzname, dl.gldl, dl.qsdbdl from (select t.shi, t.zdid,  t.jzname," +
				"  decode(t.gldl, '', t.jsdl, '0', t.jsdl,t.gldl) gldl, t.qsdbdl from jzxx t where 1 = 1 "+whereyf02+") dl where  (dl.gldl -dl.qsdbdl ) / decode( dl.qsdbdl,'0','1',dl.qsdbdl) < "+cbblsyc+") bb,zhandian zd " +
				"where aa.zdid = bb.zdid AND bb.zdid = to_char(zd.id) AND zd.property = '"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by aa.shi");
		*/
		//2013-08结算电量超标sql
		jsdbsql01.append("select da.agcode, da.agname, d.agcode as agcode1, d.agname as agname2, cc.jsdbcount" +
				" from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join (select zd.xian," +
				" count(*) jsdbcount from(select dl.shi, dl.zdid, dl.jzname, dl.jsdl, dl.qsdbdl from (select t.shi," +
				" t.zdid, t.jzname, t.jsdl, t.qsdbdl from jzxx t,zhandian z   where 1 = 1 and z.id=t.zdid  " +
				""+whereyf01+" and z.property='"+property+"') dl where (dl.jsdl - dl.qsdbdl) /decode(dl.qsdbdl, " +
				"'0', '1', dl.qsdbdl) >= "+cbbl+") aa, (select dl.shi, dl.zdid, dl.jzname, dl.gldl, dl.qsdbdl from " +
				"(select t.shi, t.zdid, t.jzname, decode(t.gldl, '', t.jsdl, '0', t.jsdl, t.gldl) gldl, t.qsdbdl" +
				" from jzxx t,zhandian z  where 1 = 1 and z.id=t.zdid  "+whereyf02+" and z.property=" +
				"'"+property+"') dl where (dl.gldl - dl.qsdbdl) / decode(dl.qsdbdl, '0', '1', dl.qsdbdl) < "+cbblsyc+") bb, " +
				"zhandian zd where aa.zdid = bb.zdid AND bb.zdid = to_char(zd.id) AND zd.property = '"+property+"' AND" +
				" (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by zd.xian)cc" +
				" on cc.xian = d.agcode  where d.agrade = '3'  and da.agrade = '2' "+zsxstr+" order by d.agcode");
		
		//二期整改数据 2013 九月以后的数据使用 （艹）
		/*jsdbsql03.append("select aa.shi agname, count(*) jsdbcount from (select dl.shi,dl.zdid,dl.jzname, dl.jsdl, dl.qsdbdl  from (select t.shi, t.zdid, t.jzname, t.jsdl, t.qsdbdl  from jzxx t  where 1 = 1" +
				"  "+whereyf01+") dl where  (dl.jsdl - dl.qsdbdl) / decode( dl.qsdbdl,'0','1',dl.qsdbdl) >= "+cbbl+") aa,(select dl.shi,  dl.zdid,  dl.jzname, dl.gldl, dl.qsdbdl from (select t.shi, t.zdid,  t.jzname," +
				"  t.gldl gldl, t.qsdbdl from jzxx t where 1 = 1 "+whereyf02+") dl where  (dl.gldl -dl.qsdbdl ) / decode( dl.qsdbdl,'0','1',dl.qsdbdl) < "+cbblsyc+") bb,zhandian zd " +
				"where aa.zdid = bb.zdid AND bb.zdid = to_char(zd.id) AND zd.property = '"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by aa.shi");
		*/
		//2014年结算电量超标sql
		jsdbsql03.append("select da.agcode, da.agname, d.agcode as agcode1, d.agname as agname2, cc.jsdbcount from " +
				"d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join ( select zd.xian,count(*) " +
				"jsdbcount from (select dl.shi,dl.xian, dl.zdid, dl.jzname, dl.dbydl, dl.bzz from (select t.shi,t.xian," +
				" t.zdid, t.jzname, t.dbydl, t.bzz  from jzxx t where 1 = 1 "+whereyf01+") dl where " +
				"(dl.dbydl - dl.bzz) /  DECODE(DL.BZZ, '0', '1', DL.BZZ) >= "+cbbl+") aa, (select dl.shi,dl.xian," +
				" dl.zdid, dl.jzname, dl.gldl, dl.qsdbdl from (select t.shi,t.xian, t.zdid, t.jzname, t.gldl gldl," +
				" t.qsdbdl from jzxx t where 1 = 1 "+whereyf02+") dl where " +
				"(dl.gldl - dl.qsdbdl) /decode(dl.qsdbdl, '0', '1', dl.qsdbdl) < "+cbblsyc+") bb, zhandian zd " +
				"where aa.zdid = bb.zdid AND bb.zdid = to_char(zd.id) AND zd.property = '"+property+"'" +
				" AND (zd.xiaoqu IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by zd.xian )cc" +
				" on d.agcode =cc.xian where d.agrade = '3' and da.agrade='2' "+zsxstr+" order by d.agcode");
		
		
		/*jsdbsql033.append("select aa.shi agname, count(*) jsdbcount from (select dl.shi,dl.zdid,dl.jzname, dl.jsdl, dl.qsdbdl  from (select t.shi, t.zdid, t.jzname, t.jsdl, t.qsdbdl  from jzxx t  where 1 = 1" +
				"  "+whereyf01+") dl where  (dl.jsdl - dl.qsdbdl) / decode( dl.qsdbdl,'0','1',dl.qsdbdl) >= "+cbbl+") aa,(select dl.shi,  dl.zdid,  dl.jzname, dl.gldl, dl.qsdbdl from (select t.shi, t.zdid,  t.jzname," +
				"  t.gldl gldl, t.qsdbdl from jzxx t where 1 = 1 "+whereyf02+") dl where  (dl.gldl -dl.qsdbdl ) / decode( dl.qsdbdl,'0','1',dl.qsdbdl) < "+cbblsyc+") bb,zhandian zd " +
				"where aa.zdid = bb.zdid AND bb.zdid = to_char(zd.id) AND zd.property = '"+property+"' group by aa.shi");
		*/
		//2014年结算电量超标sql(针对于凌奶奶和admins用户直接去掉权限sql)
		jsdbsql033.append("select da.agcode, da.agname, d.agcode as agcode1, d.agname as agname2, cc.jsdbcount from " +
				"d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join ( select zd.xian,count(*) " +
				"jsdbcount from (select dl.shi,dl.xian, dl.zdid, dl.jzname, dl.dbydl, dl.bzz from (select t.shi,t.xian," +
				" t.zdid, t.jzname, T.Dbydl,t.bzz  from jzxx t where 1 = 1 and t.bzz>0  "+whereyf01+") dl where " +
				"(dl.Dbydl - dl.bzz) /  dl.bzz >= "+cbbl+") aa, (select dl.shi,dl.xian," +
				" dl.zdid, dl.jzname, dl.DBGLDL, dl.bzz from (select t.shi,t.xian, t.zdid, t.jzname, t.dbgldl," +
				" t.bzz from jzxx t where 1 = 1 and t.bzz>0  "+whereyf02+") dl where " +
				"(dl.dbgldl - dl.bzz) /dl.bzz < "+cbblsyc+") bb, zhandian zd " +
				"where aa.zdid = bb.zdid AND bb.zdid = to_char(zd.id) AND zd.property = '"+property+"'" +
				" group by zd.xian )cc" +
				" on d.agcode =cc.xian where d.agrade = '3' and da.agrade='2' "+zsxstr+" order by d.agcode");
		
		
		
		//---------------------
	/*	bzdlsql.append("select dsdl.shi agname, sum(dsdl."+bmonth+") as bzdlcount, sum(dl1) as sjdlcount, (sum(dl1) - sum(dsdl."+bmonth+")) / sum(dsdl."+bmonth+") as bzpld" +
				" from (select dl.shi, dl.zdid,dl.jzname, dl.zp,  dl.zs, dl.changjia,dl.g2, dl.g3, dl.dl1," +
				"decode(trim(dl.sfgx),'1',z."+bmonth+"+(23*dl.gxgwsl),'0',z."+bmonth+",'',z."+bmonth+",0) as "+bmonth+" from  (select zd.shi, zd.zdid," +
				" zd.jzname, zd.zp, zd.zs, zd.changjia, zd.g2, zd.g3,zd.sfgx,zd.gxgwsl,sum(zd.jsdl)/count(zd.zdid) as dl1 from jzxx zd,zhandian zd1 where 1=1 " +
				" AND zd.zdid = to_char(zd1.id) AND zd1.property = '"+property+"' AND (zd1.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"
				+whereyf01+" and zd.jsdl is not null and zd.jsdl>0 and zd.bzw<>'2'" +
				" group by zd.shi, zd.zdid,zd.jzname, zd.zp, zd.zs, zd.changjia, zd.g2, zd.g3,zd.sfgx,zd.gxgwsl) dl, zdnhbz z where dl.zp = z.zp and dl.zs = z.zs and dl.changjia = z.changjia" +
				" and dl.g2 = z.g2 and dl.g3 = z.g3) dsdl group by dsdl.shi");
		*/
		//标准电量sql2013-03、04新
		bzdlsql.append("select da.agcode, da.agname, d.agcode as agcode1, d.agname as agname1, dsdl.bzdlcount," +
				" dsdl.sjdlcount, dsdl.bzpld from d_area_grade d left join d_area_grade da on d.fagcode = " +
				"da.agcode left join (select aa.xian,sum(aa."+bmonth+") as bzdlcount,sum(dl1) as sjdlcount, " +
				"(sum(dl1) - sum(aa."+bmonth+")) / sum(aa."+bmonth+") as bzpld from (select dl.shi,dl.xian,dl.zdid," +
				"dl.jzname, dl.zp, dl.zs,dl.changjia,dl.g2,dl.g3,dl.dl1, decode(trim(dl.sfgx),'1',z."+bmonth+" " +
				"+ (23 * dl.gxgwsl),'0',z."+bmonth+", '',z."+bmonth+", 0) as "+bmonth+" from (select zd.shi,zd.xian,zd." +
				"zdid,zd.jzname, zd.zp, zd.zs, zd.changjia, zd.g2,zd.g3,zd.sfgx, zd.gxgwsl, sum(zd.jsdl) / " +
				"count(zd.zdid) as dl1 from jzxx zd, zhandian zd1 where 1 = 1 AND zd.zdid = to_char(zd1.id)" +
				" AND zd1.property = '"+property+"'  AND (zd1.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE " +
				"T.ACCOUNTID = '"+loginId+"')) "+whereyf01+" and zd.jsdl is not null and zd.jsdl > 0 and " +
				"zd.bzw <> '2' group by zd.shi,zd.xian, zd.zdid, zd.jzname, zd.zp, zd.zs, zd.changjia, zd.g2," +
				" zd.g3, zd.sfgx, zd.gxgwsl) dl,  zdnhbz z where dl.zp = z.zp  and dl.zs = z.zs  and" +
				" dl.changjia = z.changjia and dl.g2 = z.g2 and dl.g3 = z.g3)aa group by aa.xian)dsdl " +
				"on d.agcode = dsdl.xian where d.agrade = '3' and da.agrade = '2' "+zsxstr+" order by d.agcode");
		
		
		
		//二期整改数据 2013 五月以后的数据使用 （艹）
		/*bzdlsql01.append("select dsdl.shi agname, sum(dsdl.qsdbdl) as bzdlcount, sum(dl1) as sjdlcount,decode(sum(dsdl.qsdbdl),'0',sum(dl1),(sum(dl1) - sum(dsdl.qsdbdl)) / sum(dsdl.qsdbdl)) as bzpld from (select dl.shi," +
				"  dl.zdid, dl.jzname, dl.dl1, dl.qsdbdl from (select zd.shi,  zd.zdid,  zd.jzname, sum(zd.jsdl) / count(zd.zdid) as dl1,zd.qsdbdl as qsdbdl" +
				"   from jzxx zd,zhandian zd1 where 1 = 1 AND zd.zdid=to_char(zd1.id) AND zd1.property='"+property+"' " +
				"AND (zd1.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) "
				+whereyf01+" and zd.jsdl is not null and zd.jsdl>0 and zd.bzw<>'2' group by zd.shi,  zd.zdid,   zd.jzname," +
				"    zd.qsdbdl) dl  ) dsdl group by dsdl.shi");
		//---------------------
		  */
		 
		//2014年之后标准电量sql
		bzdlsql01.append("select da.agcode, da.agname, d.agcode as agcode1, d.agname as agname1, dsdl.bzdlcount," +
				" dsdl.sjdlcount, dsdl.bzpld from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode " +
				"left join ( select d.xian, sum(d.bzz) as bzdlcount, sum(dl1) as sjdlcount, decode(sum(d.bzz)," +
				" '0', sum(dl1),(sum(dl1) - sum(d.bzz)) / sum(d.bzz)) as bzpld from (select dl.shi,dl.xian, " +
				"dl.zdid, dl.jzname, dl.dl1, dl.bzz from (select zd1.shi,zd1.xian,zd1.id zdid,zd1.jzname, " +
				"sum(zd.dbydl) as dl1, zd.bzz  from jzxx zd, zhandian zd1 where 1 = 1 " +
				"AND zd.zdid = to_char(zd1.id) and zd1.property= '"+property+"'  AND (zd1.xiaoqu IN " +
				"(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) "+whereyf01+" and " +
				"zd.dbydl is not null and zd.dbydl > 0 and zd.bzw <> '2' group by zd1.shi,  zd1.xian,zd1.id, zd1.jzname," +
				" zd.bzz)dl)d group by d.xian ) dsdl on d.agcode = dsdl.xian  where d.agrade = '3' and da.agrade = '2' "+zsxstr+"" +
				" order by d.agcode");

		/*waqzgsql.append("select aa.shi agname,count(distinct aa.zdid) waqzgcount from (select dls.* from cbzd mdz,(select dl3.*," +
				"(case when (to_number(substr(dl3.yf, 6, 2)) - 2) <= 0 then to_number(substr(dl3.yf, 1, 4)) - 1 ||'-'||(to_number(substr(dl3.yf,6, 2))+12-2) " +
				"when (to_number(substr(dl3.yf, 6, 2)) - 2) < 10 then substr(dl3.yf, 1, 4)|| '-0'||(to_number(substr(dl3.yf, 6, 2))- 2) " +
				"when (to_number(substr(dl3.yf, 6, 2)) - 2) >= 10 then substr(dl3.yf, 1, 4)|| '-'||(to_number(substr(dl3.yf, 6, 2))- 2) end) as cbyf"+
                " from(select dl.shi,dl.zdid,dl.jzname,dl.zp,dl.zs,dl.changjia,dl.g2,dl.g3,'"+month+"' as yf," +
				"(case when dl.dl1 is not null then dl.dl1 when dl.dl1 is null and dl.dl2 is not null then dl.dl2  end) as dl3," +
				"decode(trim(dl.sfgx),'1',z."+bmonth+"+(23*dl.gxgwsl),'0',z."+bmonth+",'',z."+bmonth+",0) as "+bmonth+
				" from (select zd.shi, zd.zdid, zd.jzname, zd.zp, zd.zs, zd.changjia, zd.g2, zd.g3,zd.sfgx,zd.gxgwsl," +
				"sum(zd.jsdl)/count(zd.zdid) as dl1,sum(zd.gldl)/count(zd.zdid) as dl2 from jzxx zd,zhandian zd1" +
				" where 1=1 AND zd.zdid=to_char(zd1.id) AND zd1.property='"+property+"' " +
				"AND (zd1.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) "+whereyf01+" group by zd.shi," +
				" zd.zdid,zd.jzname,  zd.zp,zd.zs,zd.changjia, zd.g2,zd.g3,zd.sfgx,zd.gxgwsl) dl, zdnhbz z where dl.zp = z.zp" +
				" and dl.zs = z.zs and dl.changjia = z.changjia and dl.g2 = z.g2  and dl.g3 = z.g3) dl3 where 1=1 " +
				" and (dl3.dl3-dl3."+bmonth+")/dl3."+bmonth+">="+cbbl+") dls where mdz.zdid=dls.zdid and mdz.cbsj<=dls.cbyf)aa group by aa.shi");
		*/
		//未按期整改sql 2013-03、04月份新
		
		waqzgsql.append("  select da.agcode, da.agname, d.agcode as agcode1,  d.agname as agname1," +
				"  dl.waqzgcount from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode  " +
				"left join  (select aa.xian, count(*) waqzgcount  from (select dls.xian,dls.shi,dls.cbyf," +
				"dls.zdid from cbzd mdz,  (select dl3.shi, dl3.xian,dl3.zdid,dl3.dl3,dl3.month3,  (case  " +
				"  when (to_number(substr(dl3.yf, 6, 2)) - 2) <= 0 then  to_number(substr(dl3.yf, 1, 4)) - 1" +
				" || '-' || (to_number(substr(dl3.yf, 6, 2)) + 12 - 2) when (to_number(substr(dl3.yf, 6, 2)) - 2)" +
				" < 10 then substr(dl3.yf, 1, 4) || '-0' || (to_number(substr(dl3.yf, 6, 2)) - 2) when " +
				"(to_number(substr(dl3.yf, 6, 2)) - 2) >= 10 then substr(dl3.yf, 1, 4) || '-' ||" +
				" (to_number(substr(dl3.yf, 6, 2)) - 2) end) as cbyf from (select dl.shi,dl.xian," +
				" dl.zdid, dl.jzname,dl.zp,dl.zs,dl.changjia,dl.g2,dl.g3,'"+month+"' as yf, (case when dl.dl1" +
				" is not null then dl.dl1 when dl.dl1 is null and dl.dl2 is not null then dl.dl2 end) as dl3," +
				" decode(trim(dl.sfgx),'1',z."+bmonth+"+ (23 * dl.gxgwsl),'0', z."+bmonth+",'', z."+bmonth+", 0) as "+bmonth+"" +
				"  from (select zd.shi,zd1.xian, zd.zdid,zd.jzname, zd.zp,zd.zs, zd.changjia, zd.g2, zd.g3," +
				" zd.sfgx, zd.gxgwsl,sum(zd.jsdl) / count(zd.zdid) as dl1, sum(zd.gldl) / count(zd.zdid) as dl2 " +
				" from jzxx zd, zhandian zd1  where 1 = 1 AND zd.zdid = to_char(zd1.id) " +
				"AND zd1.property = '"+property+"' AND (zd1.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE " +
				"T.ACCOUNTID = '"+loginId+"')) "+whereyf01+" group by zd.shi,zd1.xian,zd.zdid,zd.jzname," +
				"zd.zp,zd.zs, zd.changjia, zd.g2, zd.g3,zd.sfgx, zd.gxgwsl) dl, zdnhbz z where dl.zp = z.zp " +
				" and dl.zs = z.zs  and dl.changjia = z.changjia and dl.g2 = z.g2 and dl.g3 = z.g3) dl3 " +
				" where 1 = 1 and (dl3.dl3 - dl3."+bmonth+") / dl3."+bmonth+" >= "+cbbl+") dls  where mdz.zdid = dls.zdid" +
				" and mdz.cbsj <= dls.cbyf) aa group by aa.xian)dl on d.agcode = dl.xian where d.agrade = '3'" +
				" and da.agrade = '2' "+zsxstr+" order by d.agcode");
		
		//二期整改数据 2013 五月以后的数据使用 （艹）
		/*waqzgsql01.append("select aa.shi agname, count(distinct aa.zdid) waqzgcount from (select dls.* from cbzd mdz, (select dl3.*, (case when (to_number(substr(dl3.yf, 6, 2)) - 2) <= 0 then" +
				" to_number(substr(dl3.yf, 1, 4)) - 1 || '-' ||  (to_number(substr(dl3.yf, 6, 2)) + 12 - 2) when (to_number(substr(dl3.yf, 6, 2)) - 2) < 10 then" +
				"   substr(dl3.yf, 1, 4) || '-0' || (to_number(substr(dl3.yf, 6, 2)) - 2)  when (to_number(substr(dl3.yf, 6, 2)) - 2) >= 10 then substr(dl3.yf, 1, 4) || '-' ||" +
				"   (to_number(substr(dl3.yf, 6, 2)) - 2) end) as cbyf from (select dl.shi,  dl.zdid,  dl.jzname, '"+month+"' as yf, (case  when dl.dl1 is not null then" +
				"  dl.dl1 when dl.dl1 is null and dl.dl2 is not null then dl.dl2 end) as dl3,dl.qsdbdl as month3 from (select zd.shi, zd.zdid,  zd.jzname," +
				"   sum(zd.jsdl) / count(zd.zdid) as dl1, sum(zd.gldl) / count(zd.zdid) as dl2, zd.qsdbdl  from jzxx zd,zhandian zd1 where 1 = 1 " +
				"AND zd.zdid=to_char(zd1.id) AND zd1.property='zdsx02' AND (zd1.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) "+whereyf01+"" +
				"   group by zd.shi, zd.zdid,zd.jzname,zd.qsdbdl) dl  ) dl3 where 1 = 1 and decode(dl3.month3,'0',dl3.dl3,(dl3.dl3 - dl3.month3) / dl3.month3) >= "+cbbl+") dls" +
				"   where mdz.zdid = dls.zdid and mdz.cbsj <= dls.cbyf) aa group by aa.shi");*/
	/*	waqzgsql01.append("select aa.shi agname, count(distinct aa.zdid) waqzgcount from (select dls.* from " +
				" (select c.* from cbzd c where c.cbsj<='"+bzyf02+"') mdz,(select dl3.* " +
				" from (select zd.shi,zd.zdid,zd.jzname,decode(zd.jsdl,null,zd.gldl,'',zd.gldl,'0',zd.gldl,zd.jsdl) as dl3," +
				" zd.qsdbdl as month3 from jzxx zd, zhandian zd1 where 1 = 1 AND zd.zdid = to_char(zd1.id) AND zd1.property = '"+property+"'" +
				" AND (zd1.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) "+whereyf01+") dl3 where 1 = 1 " +
				" and decode(dl3.month3,'0',dl3.dl3,(dl3.dl3 - dl3.month3) / dl3.month3) >= "+cbbl+") dls" +
				" where mdz.zdid = dls.zdid) aa group by aa.shi");
		*/
		//2013-08月未按期整改sql
		waqzgsql01.append("select da.agcode,da.agname,d.agcode as agcode1, d.agname as agname1, dl.waqzgcount" +
				" from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join " +
				"(select zd.xian, count(distinct zd.id) waqzgcount from (select c.* from cbzd c" +
				" where c.cbsj <= '"+bzyf02+"') mdz,(select dl3.shi,dl3.zdid,dl3.dl3,dl3.month3 from " +
				"(select zd.shi,zd.zdid,  zd.dbydl " +
				"as dl3, zd.bzz as month3 from jzxx zd where 1 = 1  "+whereyf01+") dl3 " +
				" where 1 = 1 and decode(dl3.month3, '0',  dl3.dl3,(dl3.dl3 - dl3.month3) / dl3.month3) >= "+cbbl+")" +
				" dls, zhandian zd  where mdz.zdid = dls.zdid AND dls.zdid = to_char(zd.id) " +
				"AND zd.property = '"+property+"' AND (zd.xiaoqu IN  (SELECT T.AGCODE  FROM PER_AREA T WHERE" +
				" T.ACCOUNTID = '"+loginId+"')) group by zd.xian) dl on d.agcode = dl.xian where d.agrade = '3' and" +
				" da.agrade = '2' "+zsxstr+" order by d.agcode");
		
		//未按期整改9月份
		/*waqzgsql02.append("select aa.shi agname, count(distinct aa.zdid) waqzgcount from (select dls.* from " +
				" (select c.* from cbzd c where c.cbsj<='"+bzyf02+"') mdz,(select dl3.* " +
				" from (select zd.shi,zd.zdid,zd.jzname,zd.jsdl as dl3," +
				" zd.qsdbdl as month3 from jzxx zd, zhandian zd1 where 1 = 1 AND zd.zdid = to_char(zd1.id) AND zd1.property = '"+property+"'" +
				" AND (zd1.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) "+whereyf01+") dl3 where 1 = 1 " +
				" and decode(dl3.month3,'0',dl3.dl3,(dl3.dl3 - dl3.month3) / dl3.month3) >= "+cbbl+") dls" +
				" where mdz.zdid = dls.zdid) aa group by aa.shi");
		*/
		//2014年之后的新未按期整改sql
		waqzgsql02.append(" select da.agcode,da.agname, d.agcode as agcode1, d.agname as agname1, dl.waqzgcount " +
				"from d_area_grade d left join d_area_grade da on d.fagcode = da.agcode left join " +
				"(select zd.xian,count(distinct zd.id) waqzgcount from (select c.zdid from cbzd c where c.cbsj <= '"+bzyf02+"') mdz, " +
				"(select jz.shi, jz.zdid,jz.dbydl,jz.bzz from jzxx jz where 1 = 1 "+whereyf01+") dls, zhandian zd " +
				"where  dls.zdid = to_char(zd.id) and  mdz.zdid = dls.zdid AND zd.property = '"+property+"' AND " +
				"(zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T  WHERE T.ACCOUNTID = '"+loginId+"'))  and " +
				"decode(dls.bzz, '0', dls.dbydl,(dls.dbydl - dls.bzz) / dls.bzz) >= "+cbbl+" group by zd.xian) dl" +
				" on d.agcode = dl.xian  where d.agrade = '3'  and da.agrade = '2' "+zsxstr+" order by d.agcode");
		Date now1 = new Date(); 
		SimpleDateFormat dateFormat =null;
		dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		
		
		
		DataBase db = new DataBase();
		DataBase db1 = new DataBase();
		
		ResultSet jzsxrs = null;
		ResultSet scnhsrs = null;
		ResultSet cbgkrs = null;
		ResultSet cbzdrs = null;
		ResultSet lxcbrs = null;
		ResultSet lxzzcbrs = null;
		ResultSet jsdbrs =null;
		ResultSet bzdlrs = null;
		ResultSet waqzgrs = null;

		//System.out.println("基站属性错误：" + zdsxsql.toString());
		//System.out.println("生产能耗分摊:"+scnhsql.toString());
		//System.out.println("每月抄表管控"+cbgksql.toString());		

		

		if(bzdate.after(date9)){
			System.out.println("-------------这是2013-09月之后(不包括9月份)的3个sql喔-----------------");
			System.out.println("基站属性错误：" + zdsxsql.toString());
			System.out.println("生产能耗分摊10月份后:"+scnhsql1.toString());
			System.out.println("每月抄表管控"+cbgksql.toString());
		}else{
			System.out.println("-------------这是2013-09月之前的3个sql喔-----------------");
			System.out.println("基站属性错误：" + zdsxsql.toString());
			System.out.println("生产能耗分摊10月份前:"+scnhsql.toString());
			System.out.println("每月抄表管控"+cbgksql.toString());
		}

        if(bzdate.after(date1)){
        	System.out.println("---------这都是继2013-04月之后的sql，但是还分5月的sql、8月之前的sql、9月之后的sql喔---------");
        	if(bzdate.after(date8)){
        		if(rolename.equals("sd_lingjin")||rolename.equals("admins")){
        			System.out.println("-----------------------嗨、这是admins用户和凌奶奶账户登陆，所以我要给小伙伴们个惊喜o(^▽^)o-----------------------------");
        			System.out.println("超标站点cbzdsql02："+cbzdsql02.toString());
        			System.out.println("结算电量超标jsdbsql033："+jsdbsql033.toString());
        			System.out.println("连续超标lxcbsql033："+lxcbsql033.toString());
        			System.out.println("连续超标且电量增加lxzzcbsql033："+lxzzcbsql033.toString());
        			System.out.println("未按期整改waqzgsql02："+waqzgsql02.toString());
        			System.out.println("标准电量bzdlsql01："+bzdlsql01.toString());
				}else{
					System.out.println("---------------------------友情提示：这是普通用户登陆，所以小伙伴没有惊喜喔------------------------");
					System.out.println("超标站点cbzdsql02："+cbzdsql02.toString());
					System.out.println("结算电量超标jsdbsql03："+jsdbsql03.toString());
					System.out.println("连续超标lxcbsql03："+lxcbsql03.toString());
					System.out.println("连续超标且电量增加lxzzcbsql03："+lxzzcbsql03.toString());
					System.out.println("未按期整改waqzgsql02："+waqzgsql02.toString());
					System.out.println("标准电量bzdlsql01："+bzdlsql01.toString());
        		}
        		
    		}else{
    			System.out.println("------------------------------2013年8月之前的sql执行------------------------------");
    			System.out.println("超标站点(2013年8月之前)cbzdsql01:"+cbzdsql01.toString());
	        	System.out.println("标准电量(2013年8月之前)bzdlsql01:"+bzdlsql01.toString());
	    		System.out.println("未按期整改(2013年8月之前)waqzgsql01："+waqzgsql01.toString());
	    		if(bzdate.equals(date2)){
	    			System.out.println("------------------------------2013年5月的sql--------------------------");
	    			System.out.println("连续增长超标(2013年5月)lxzzcbsql02："+lxzzcbsql02.toString());
	    			System.out.println("每月抄表但结算电表超标02(2013年5月)jsdbsql02："+jsdbsql02.toString());
	    			System.out.println("连续超标(2013年5月)lxcbsql02："+lxcbsql02.toString());
	    		}else{
	    			System.out.println("------------------------------非2013年5月的sql但是2013-08月之前用的sql--------------------------");
	    			System.out.println("连续增长超标01："+lxzzcbsql01.toString());
	    			System.out.println("每月抄表但结算电表超标01："+jsdbsql01.toString());
	    			System.out.println("连续超标01："+lxcbsql01.toString());
	    		}    		
    		}
		}else{
			
			System.out.println("超标站点:"+cbzdsql.toString());
			System.out.println("连续超标："+lxcbsql.toString());
			System.out.println("连续增长超标："+lxzzcbsql.toString());
			System.out.println("每月抄表但结算电表超标："+jsdbsql.toString());
			System.out.println("标准电量:"+bzdlsql.toString());
			System.out.println("未按期整改："+waqzgsql.toString());
		}
		
		try{
			db.connectDb();
			if(bzdate.after(date9)){
				jzsxrs = db.queryAll(zdsxsql.toString());
				scnhsrs = db.queryAll(scnhsql1.toString());
				cbgkrs = db.queryAll(cbgksql.toString());
			}else{
				jzsxrs = db.queryAll(zdsxsql.toString());
				scnhsrs = db.queryAll(scnhsql.toString());
				cbgkrs = db.queryAll(cbgksql.toString());
			}
			if(bzdate.after(date1)){
				if(bzdate.after(date8)){
					if(rolename.equals("sd_lingjin")||rolename.equals("admins")){
						cbzdrs = db.queryAll(cbzdsql02.toString());
						jsdbrs = db.queryAll(jsdbsql033.toString());
						lxcbrs = db.queryAll(lxcbsql033.toString());
						lxzzcbrs = db.queryAll(lxzzcbsql033.toString());
						waqzgrs = db.queryAll(waqzgsql02.toString());
						bzdlrs = db.queryAll(bzdlsql01.toString());
					}else{
					cbzdrs = db.queryAll(cbzdsql02.toString());
					jsdbrs = db.queryAll(jsdbsql03.toString());
					lxcbrs = db.queryAll(lxcbsql03.toString());
					lxzzcbrs = db.queryAll(lxzzcbsql03.toString());
					waqzgrs = db.queryAll(waqzgsql02.toString());
					bzdlrs = db.queryAll(bzdlsql01.toString());
					}
	    		}else{
					cbzdrs = db.queryAll(cbzdsql01.toString());
					bzdlrs = db.queryAll(bzdlsql01.toString());
					waqzgrs = db.queryAll(waqzgsql01.toString());					
					if(bzdate.equals(date2)){
						lxzzcbrs = db.queryAll(lxzzcbsql02.toString());
						jsdbrs = db.queryAll(jsdbsql02.toString());
						lxcbrs = db.queryAll(lxcbsql02.toString());
					}else{
						lxzzcbrs = db.queryAll(lxzzcbsql01.toString());
						jsdbrs = db.queryAll(jsdbsql01.toString());
						lxcbrs = db.queryAll(lxcbsql01.toString());
					}				
	    		}				
			}
			else{
				cbzdrs = db.queryAll(cbzdsql.toString());
				lxcbrs = db.queryAll(lxcbsql.toString());
				lxzzcbrs = db.queryAll(lxzzcbsql.toString());
				jsdbrs = db.queryAll(jsdbsql.toString());
				bzdlrs = db.queryAll(bzdlsql.toString());
				waqzgrs = db.queryAll(waqzgsql.toString());
			}
			
			while (jzsxrs.next()) {
				cbJzBean bean = new cbJzBean();
				bean.setShicode(jzsxrs.getString("agcode") != null ? jzsxrs.getString("agcode")
						: "0");
				bean.setShiname(jzsxrs.getString("agname") != null ? jzsxrs.getString("agname")
						: "0");
				bean.setXiancode(jzsxrs.getString("agcode1") != null ? jzsxrs.getString("agcode1")
						: "0");
				bean.setXian(jzsxrs.getString("agname1") != null ? jzsxrs.getString("agname1")
						: "0");
				bean.setJzsxcount(jzsxrs.getString("zdsxcucount") != null ? jzsxrs.getString("zdsxcucount")
						: "0");
				list.add(bean);
			}
			while(scnhsrs.next()){
			for(cbJzBean cb:list){
				if(cb.getShiname().equals(scnhsrs.getString("agname")) && cb.getXian().equals(scnhsrs.getString("agname1") )){
					cb.setScnhcount(scnhsrs.getString("scnhcount"));
				}
			}
			}
			while(cbgkrs.next()){
				for(cbJzBean cb:list){
					if(cb.getShiname().equals(cbgkrs.getString("agname"))&& cb.getXian().equals(cbgkrs.getString("agname1") )){
						cb.setCbgkcount(cbgkrs.getString("cbgkcount"));
					}
				}
		    }
			while(cbzdrs.next()){
				for(cbJzBean cb:list){
					
					if(cb.getShiname().equals(cbzdrs.getString("agname"))&& cb.getXian().equals(cbzdrs.getString("agname1"))){
						cb.setCbzdcount(cbzdrs.getString("cbzdcount"));
					}
				}
		    }
			while(jsdbrs.next()){
				for(cbJzBean cb:list){
				
					if(cb.getShiname().equals(jsdbrs.getString("agname"))&& cb.getXian().equals(jsdbrs.getString("agname2"))){
						cb.setJsdbcount(jsdbrs.getString("jsdbcount"));
					}
				}
		    }
			while(lxcbrs.next()){
				for(cbJzBean cb:list){
					if(cb.getShiname().equals(lxcbrs.getString("agname"))&& cb.getXian().equals(lxcbrs.getString("agname1"))){
						cb.setLxcbcount(lxcbrs.getString("lxcbcount"));
					}
				}
		    }
			while(lxzzcbrs.next()){
				for(cbJzBean cb:list){
					if(cb.getShiname().equals(lxzzcbrs.getString("agname"))&& cb.getXian().equals(lxzzcbrs.getString("agname1"))){
						cb.setLxzzcbcount(lxzzcbrs.getString("lxzzcbcount"));
					}
				}
		    }
			
			while(bzdlrs.next()){
				for(cbJzBean cb:list){
					if(cb.getShiname().equals(bzdlrs.getString("agname"))&& cb.getXian().equals(bzdlrs.getString("agname1"))){
						cb.setBzdlcount(bzdlrs.getString("bzdlcount"));
						cb.setSzdlcount(bzdlrs.getString("sjdlcount"));
						cb.setBzpldu(bzdlrs.getString("bzpld"));
					}
				}
		    }
			while(waqzgrs.next()){
				for(cbJzBean cb:list){
					if(cb.getShiname().equals(waqzgrs.getString("agname"))&& cb.getXian().equals(waqzgrs.getString("agname1"))){
						cb.setWaqzgcount(waqzgrs.getString("waqzgcount"));
						
					}
				}
		    }
			for(cbJzBean cb:list){
				double yzdsxcw =0;
				double yscnhft =0;
				double ymycbgk =0;
				double ycbzd =0;
				double ymycbjscb =0;
				double ylxcb =0;
				double ylxcbdlzj =0;
				double ybzpld =0;
				double ycbjzzg =0;
			//真实性的分分
				 yzdsxcw = Double.valueOf(cb.getJzsxcount()!=null?cb.getJzsxcount():"0");
				 yscnhft = Double.valueOf(cb.getScnhcount()!=null?cb.getScnhcount():"0");
				 ymycbgk = Double.valueOf(cb.getCbgkcount()!=null?cb.getCbgkcount():"0");
			     ycbzd   = Double.valueOf(cb.getCbzdcount()!=null?cb.getCbzdcount():"0");
				 ymycbjscb = Double.valueOf(cb.getJsdbcount()!=null?cb.getJsdbcount():"0");
				 ylxcb   = Double.valueOf(cb.getLxcbcount()!=null?cb.getLxcbcount():"0");
			     ylxcbdlzj = Double.valueOf(cb.getLxzzcbcount()!=null?cb.getLxzzcbcount():"0");
			    //偏离度 
			     ybzpld = Double.valueOf(cb.getBzpldu()!=null?cb.getBzpldu():"0");
			     //分数
			    cb.setZsxcount(String.valueOf(Math.ceil((yzdsxcw*(zdsxcw/100)+yscnhft*(scnhft/100)+ymycbgk*(mycbgk/100)+ycbzd*(cbzd/100)+ymycbjscb*(mycbjscb/100)+ylxcb*(lxcb/100)+ylxcbdlzj*(lxcbdlzj/100)))));
				cb.setZsx(String.valueOf(10-Math.ceil(yzdsxcw*(zdsxcw/100)+yscnhft*(scnhft/100)+ymycbgk*(mycbgk/100)+ycbzd*(cbzd/100)+ymycbjscb*(mycbjscb/100)+ylxcb*(lxcb/100)+ylxcbdlzj*(lxcbdlzj/100))));
				
				cb.setPld(String.valueOf(Double.valueOf(cb.getBzpldu()!=null?cb.getBzpldu():"0")*100-pld));
				ycbjzzg = Double.valueOf(cb.getWaqzgcount()!=null?cb.getWaqzgcount():"0");
				//cb.setCbjzzg(String.valueOf((40-ycbjzzg*cbjzzg)))
				
				if(ycbjzzg>=cbjzzg){ 
					if(40-(ycbjzzg-cbjzzg)<0){
						cb.setCbjzzg("0");
					}else{
						cb.setCbjzzg(String.valueOf(40-(ycbjzzg-cbjzzg)));
					}
					
				}else{
					cb.setCbjzzg(String.valueOf(40));
				}
				
			}
			for(cbJzBean cb:list){
				if(Double.valueOf(cb.getZsx())<0){
					cb.setZsx("0");
				}
				
				if(Double.valueOf(cb.getPld())<0){
					cb.setPld("10");
				}else{
					if(10-Double.valueOf(cb.getPld())<0){
						cb.setPld("0");
					}else{
						cb.setPld(String.valueOf(10-Double.valueOf(cb.getPld())));
					}
				}
				if(Double.valueOf(cb.getCbjzzg())<0){
					cb.setCbjzzg("0");
				}
				
				
			}
		}catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (jzsxrs != null) {
				try {
					jzsxrs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (scnhsrs != null) {
				try {
					scnhsrs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (cbgkrs != null) {
				try {
					cbgkrs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (cbzdrs != null) {
				try {
					cbzdrs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (lxcbrs != null) {
				try {
					lxcbrs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (lxzzcbrs != null) {
				try {
					lxzzcbrs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (jsdbrs != null) {
				try {
					jsdbrs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (bzdlrs != null) {
				try {
					bzdlrs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (waqzgrs != null) {
				try {
					waqzgrs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
			try {
				db1.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return list;
	}
	//基站属性错误查询
	public synchronized ArrayList getJzcw(String shi,String xian,String loginId ) {
		
		
		ArrayList list = new ArrayList();
		
		String sql = "";
		
		DataBase db = new DataBase();
		try {
			//2013-09-10以前使用的统计站点属性错误的方式
		/*sql = " select s.agname,d.agname,g.agname,t.zdid,zd.jzname,i.name " +
				"from zhandian zd left join d_area_grade s on zd.shi = s.agcode," +
				" d_area_grade d,d_area_grade g, indexs i,(select zdid from jzxx where 1 = 1 AND symonth ='"+bzyf+"' group by zdid) t " +
				"where to_char(zd.id) = t.zdid and zd.property <> 'zdsx02'  " +
				"AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '263')) " +
				"and zd.qyzt = '1' and zd.shi='"+shi+"' and zd.property = i.code and d.agcode = zd.xian " +
				"AND (d.agcode IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '263')) " +
				"and g.agcode=zd.xiaoqu group by s.agname, t.zdid, i.name, d.agname,g.agname,zd.jzname";*/
		sql = "select d.agname as shi,x.agname as xian,xq.agname as xiaoqu,zd.id," +
				"zd.jzname,zs.name as property,s.name as stationtype from zhandian zd left join d_area_grade d on zd.shi=d.agcode" +
				" left join d_area_grade x on zd.xian=x.agcode left join d_area_grade xq on zd.xiaoqu=xq.agcode " +
				"left join indexs s on s.code=zd.stationtype " +
				"left join zdsx zs on zd.property = zs.code and zs.fjcode = '00' " +
				"left join zdsx st on zd.stationtype = st.code and zd.property = st.fjcode " +
				"where zs.name is null or st.name is null and zd.qyzt = '1' and zd.shi = '"+shi+"' and zd.xian = '"+xian+"'" +
				"AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))";

		ResultSet rs = null;
		System.out.println("基站错误"+sql.toString());
			db.connectDb();
			rs = db.queryAll(sql);
            while(rs.next()){
            	cbJzBean c = new cbJzBean();
            	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
            	c.setXian(null==rs.getString(2)?"":rs.getString(2));
            	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
            	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
            	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
            	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
            	c.setStationtype(null==rs.getString(7)?"":rs.getString(7));
            	list.add(c);
            }
			
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}
		return list;
	}
	//生产分摊查询
public synchronized ArrayList getScft(String shi,String xian,String loginId,String bzyf) {
		
	DateFormat fmt = new SimpleDateFormat("yyyy-MM");
	String scft = "2013-09";
	Date scftt = null;
	Date bzyff = null;
	try {
		bzyff = fmt.parse(bzyf);
		scftt= fmt.parse(scft);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		ArrayList list = new ArrayList();
		
		String sql = "";
		DataBase db = new DataBase();
		if(bzyff.after(scftt)){
			try {

				sql = " select  ft.shiname,ft.xian,ft.xiaoqu,ft.zdid,ft.jzname,ft.zdsx,ft.zdlx,ft.sc " +
						"from (select zd.shi, (select agname from d_area_grade where agcode = zd.shi) as shiname," +
						" (select agname from d_area_grade where agcode = zd.xian) as xian," +
						" (select agname from d_area_grade where agcode = zd.xiaoqu) as xiaoqu, (zd.id) as zdid, (zd.jzname) as jzname," +
						" (select name from indexs where code = zd.property) as zdsx, (select name  from indexs where code = zd.stationtype) as zdlx," +
						"max(decode(s.shuoshuzhuanye, 'zylx01', s.dbili)) as sc from zhandian zd, dianbiao db, " +
						"sbgl s where zd.property in ('zdsx06', 'zdsx01') " +
						" and zd.id = db.zdid  " +
						"and zd.qyzt = '1'  and db.dbqyzt = '1' and db.dbid = s.dianbiaoid  and db.dbyt = 'dbyt01' and zd.shi='"+shi+"' and zd.xian = '"+xian+"' " +
						"AND (zd.xiaoqu IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by zd.shi, zd.xian," +
						"  zd.xiaoqu,  zd.id, zd.jzname,  zd.property,zd.stationtype) ft  where to_number(ft.sc) >= 55 ";

								

					ResultSet rs = null;
					System.out.println("生产分摊10月份后"+sql.toString());
						db.connectDb();
						rs = db.queryAll(sql);
			            while(rs.next()){
			            	cbJzBean c = new cbJzBean();
			            	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
			            	c.setXian(null==rs.getString(2)?"":rs.getString(2));
			            	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
			            	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
			            	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
			            	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
			            	//c.setDbid(null==rs.getString(7)?"":rs.getString(7));
			            	//c.setDbname(null==rs.getString(8)?"":rs.getString(8));
			            	c.setStationtype(null==rs.getString(7)?"":rs.getString(7));
			            	c.setScft(null==rs.getString(8)?"":rs.getString(8));
			            	list.add(c);
			            }
						
					}

					catch (DbException de) {
						de.printStackTrace();
					} catch (SQLException de) {
						de.printStackTrace();
					}

					finally {
						try {
							db.close();
						} catch (DbException de) {
							de.printStackTrace();
						}

					}
					return list;
					
		}else{
		try {

	sql = " select  ft.shiname,ft.xian,ft.xiaoqu,ft.zdid,ft.jzname,ft.zdsx,ft.zdlx,ft.sc " +
			"from (select zd.shi, (select agname from d_area_grade where agcode = zd.shi) as shiname," +
			" (select agname from d_area_grade where agcode = zd.xian) as xian," +
			" (select agname from d_area_grade where agcode = zd.xiaoqu) as xiaoqu, (zd.id) as zdid, (zd.jzname) as jzname," +
			" (select name from indexs where code = zd.property) as zdsx, (select name  from indexs where code = zd.stationtype) as zdlx," +
			"max(decode(s.shuoshuzhuanye, 'zylx01', s.dbili)) as sc from zhandian zd, dianbiao db, " +
			"sbgl s where zd.property in ('zdsx06', 'zdsx01') " +
			"  and zd.id = db.zdid  " +
			"and zd.qyzt = '1'  and db.dbqyzt = '1' and db.dbid = s.dianbiaoid  and db.dbyt = 'dbyt01' and zd.shi='"+shi+"' and zd.xian = '"+xian+"' " +
			"AND (zd.xiaoqu IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by zd.shi, zd.xian," +
			"  zd.xiaoqu,  zd.id, zd.jzname,  zd.property,zd.stationtype) ft  where to_number(ft.sc) >=55 ";

					

		ResultSet rs = null;
		System.out.println("生产分摊10月份前"+sql.toString());
			db.connectDb();
			rs = db.queryAll(sql);
            while(rs.next()){
            	cbJzBean c = new cbJzBean();
            	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
            	c.setXian(null==rs.getString(2)?"":rs.getString(2));
            	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
            	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
            	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
            	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
            	//c.setDbid(null==rs.getString(7)?"":rs.getString(7));
            	//c.setDbname(null==rs.getString(8)?"":rs.getString(8));
            	c.setStationtype(null==rs.getString(7)?"":rs.getString(7));
            	c.setScft(null==rs.getString(8)?"":rs.getString(8));
            	list.add(c);
            }
			
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}
		return list;
		}
}
//超标管控查询
public synchronized ArrayList getCbgk(String shi,String xian,String bzyf,String loginId,String property ) {
	
	
	ArrayList list = new ArrayList();
	
	String sql = "";
	
	DataBase db = new DataBase();
	try {
	sql = "  select cbqk.agname,cbqk.dagname,cbqk.gagname,cbqk.zdid,cbqk.jzname,cbqk.property" +
			" from (select zd.shi,zd.xian,s.agname as agname,d.agname as dagname,g.agname as gagname," +
			"zd.id as zdid,zd.jzname as jzname,i.name as property,dl.dbydl as dl1,dl.dbgldl as dl2 " +
			"from zhandian zd left join d_area_grade s on zd.shi = s.agcode left join d_area_grade" +
			" d on zd.xian = d.agcode left join d_area_grade g on zd.xiaoqu = g.agcode left join " +
			"indexs i on i.code=zd.property,(select zdid, dbydl, dbgldl from jzxx where 1 = 1 AND" +
			" symonth = '"+bzyf+"') dl where to_char(zd.id) = dl.zdid AND " +
			"zd.property = '"+property+"' AND (zd.xiaoqu IN" +
			"(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) cbqk " +
			"where cbqk.dl1 is null and cbqk.dl2 is null and cbqk.shi = '"+shi+"' and cbqk.xian = '"+xian+"'";
				

	ResultSet rs = null;
	System.out.println("抄表管控"+sql.toString());	
		db.connectDb();
		rs = db.queryAll(sql);
        while(rs.next()){
        	cbJzBean c = new cbJzBean();
        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
        	list.add(c);
        }
		
	}

	catch (DbException de) {
		de.printStackTrace();
	} catch (SQLException de) {
		de.printStackTrace();
	}

	finally {
		try {
			db.close();
		} catch (DbException de) {
			de.printStackTrace();
		}

	}
	return list;
}
//超标站点
public synchronized ArrayList getCbzd(String shi,String xian,String bzyf,String cbbl,String loginId,String property ) {
	
	
	ArrayList list = new ArrayList();
	double cbbll = Double.valueOf(cbbl.toString())/100;
	//System.out.println("--------------------------------->"+cbblsyc_zd);
	String sql = "";
	String sql1 = "";
	String sql2 = "";
	DataBase db = new DataBase();
	
	//超标站点03、04查询
    if(bzyf.equals("2013-03")||bzyf.equals("2013-04")){
    	String bmonth=bzyf.substring(5,7);
    	System.out.println(bmonth);
    	if(bmonth.equals("03")){bmonth="month3";}
    	if(bmonth.equals("04")){bmonth="month4";}
    	try{
    	sql1=" select bb.shi,bb.xian,bb.xiaoqu,bb.zdid,bb.jzname,bb.property,bb.dl,bb.bz," +
    			"round(((bb.dl-bb.bz)/bb.bz),4)*100 || '%'as bl from " +
    			"(select ab.*,(ab.dl - ab.bz) / ab.bz countbz from " +
    			"(select jz.sfgx, d.agname as shi, s.agname as xian, a.agname as xiaoqu," +
    			" jz.zdid,zd1.jzname, i.name as property, round(decode(jz.jsdl, '', jz.gldl, " +
    			"'0', jz.gldl, jz.jsdl),4)dl, decode( trim(jz.sfgx), '1', zd."+bmonth+" + " +
    			"(23 * jz.gxgwsl), '0', zd."+bmonth+", '', zd."+bmonth+", 0) bz " +
    			"from jzxx jz, zdnhbz zd, zhandian zd1 left join D_AREA_GRADE d " +
    			"on zd1.shi=d.agcode left join D_AREA_GRADE s on " +
    			"zd1.xian=s.agcode left join D_AREA_GRADE a on zd1.xiaoqu=a.agcode" +
    			" left join indexs i on zd1.property=i.code where jz.changjia = " +
    			"zd.changjia and jz.g2 = zd.g2 and jz.g3 = zd.g3 and jz.zp = zd.zp and jz.zs" +
    			" = zd.zs AND jz.zdid = to_char(zd1.id) AND zd1.property = '"+property+"' AND" +
    			" (zd1.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) AND symonth " +
    			"= '"+bzyf+"' AND zd1.shi='"+shi+"' and zd1.xian='"+xian+"') ab) bb where bb.countbz >= "+cbbll+" ";
    	ResultSet rs = null;
    	System.out.println("超标站点0304"+sql1.toString());
    		db.connectDb();
    		rs = db.queryAll(sql1);
            while(rs.next()){
            	cbJzBean c = new cbJzBean();
            	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
            	c.setXian(null==rs.getString(2)?"":rs.getString(2));
            	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
            	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
            	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
            	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
            	c.setSjydl(null==rs.getString(7)?"":rs.getString(7));
            	c.setQsdbdl(null==rs.getString(8)?"":rs.getString(8));
            	c.setCbbl(null==rs.getString(9)?"":rs.getString(9));
            	list.add(c);
            }
    }catch (DbException de) {
		de.printStackTrace();
	} catch (SQLException de) {
		de.printStackTrace();
	}

	finally {
		try {
			db.close();
		} catch (DbException de) {
			de.printStackTrace();
		}

	}
	return list;
    }
    //超标站点05月份查询
    if(bzyf.equals("2013-05")){
    	
    	try{
    	sql2=" select bb.shi,bb.xian,bb.xiaoqu,bb.zdid,bb.jzname,bb.property,bb.dl,bb.bz," +
    			"round(bb.countbz, 4) * 100 || '%' as bl from (select ab.*, decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz" +
    			" from (select d.agname as shi, s.agname as xian,a.agname as xiaoqu,jz.zdid,zd.jzname," +
    			"i.name as property,round( decode(jz.jsdl,'',jz.gldl,'0',jz.gldl,jz.jsdl),4) dl," +
    			" jz.qsdbdl as bz from jzxx jz, zhandian zd left join D_AREA_GRADE d on zd.shi=d.agcode " +
    			"left join D_AREA_GRADE s on zd.xian=s.agcode left join D_AREA_GRADE a on zd.xiaoqu=a.agcode " +
    			"left join indexs i on zd.property=i.code where 1 = 1 AND jz.zdid = to_char(zd.id) " +
    			"AND zd.property = '"+property+"' AND zd.shi='"+shi+"' AND zd.xian = '"+xian+"' AND (zd.xiaoqu IN(SELECT T.AGCODE " +
    			"FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) AND symonth = '"+bzyf+"') ab) bb " +
    			"where bb.countbz >= "+cbbll+"";
    	ResultSet rs = null;
    	System.out.println("超标站点05"+sql2.toString());
    		db.connectDb();
    		rs = db.queryAll(sql2);
            while(rs.next()){
            	cbJzBean c = new cbJzBean();
            	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
            	c.setXian(null==rs.getString(2)?"":rs.getString(2));
            	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
            	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
            	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
            	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
            	c.setSjydl(null==rs.getString(7)?"":rs.getString(7));
            	c.setQsdbdl(null==rs.getString(8)?"":rs.getString(8));
            	c.setCbbl(null==rs.getString(9)?"":rs.getString(9));
            	list.add(c);
            }
    }catch (DbException de) {
		de.printStackTrace();
	} catch (SQLException de) {
		de.printStackTrace();
	}

	finally {
		try {
			db.close();
		} catch (DbException de) {
			de.printStackTrace();
		}

	}
	return list;
    }
    //超标站点06、07、08月份查询
    if(bzyf.equals("2013-06")|| bzyf.equals("2013-07")|| bzyf.equals("2013-08")){
	try {
	sql = " select aa.shi,aa.xian,aa.xiaoqu, aa.zdid,aa.jzname, aa.property, aa.dl,aa.qsdbdl," +
			"(round(decode(aa.qsdbdl,'0',aa.dl,(aa.dl - aa.qsdbdl) / aa.qsdbdl), 4)) * 100 || '%' as cbbl " +
			"from (select jz.shi as shi,(select agname from d_area_grade where agcode = zd.xian) as xian" +
			",(select agname from d_area_grade where agcode = zd.xiaoqu) as xiaoqu,zd.id as zdid," +
			"zd.jzname as jzname, (select i.name from indexs i where i.code = zd.property) as property," +
			"round(decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl), 4) dl,jz.qsdbdl as qsdbdl from" +
			" jzxx jz left join zhandian zd on jz.zdid = zd.id where 1 = 1 AND symonth = '"+bzyf+"' " +
			"AND zd.shi = '"+shi+"' and zd.xian = '"+xian+"' AND jz.zdid = to_char(zd.id) AND zd.property = '"+property+"' " +
			"AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) aa " +
			"where decode(aa.qsdbdl,'0',aa.dl, (aa.dl - aa.qsdbdl) / aa.qsdbdl) >= "+cbbll+"";
				

	ResultSet rs = null;
	System.out.println("超标站点0607"+sql.toString());
		db.connectDb();
		rs = db.queryAll(sql);
        while(rs.next()){
        	cbJzBean c = new cbJzBean();
        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
        	c.setSjydl(null==rs.getString(7)?"":rs.getString(7));
        	c.setQsdbdl(null==rs.getString(8)?"":rs.getString(8));
        	c.setCbbl(null==rs.getString(9)?"":rs.getString(9));
        	list.add(c);
        }
		
	}

	catch (DbException de) {
		de.printStackTrace();
	} catch (SQLException de) {
		de.printStackTrace();
	}

	finally {
		try {
			db.close();
		} catch (DbException de) {
			de.printStackTrace();
		}

	}
	return list;
}
    //超标站点9月份之后
    try {
    	sql = " select aa.shi,aa.xian,aa.xiaoqu, aa.zdid,aa.jzname, aa.property, aa.dl,aa.bzz,(round(decode(aa.bzz,'0',aa.dl," +
    			"(aa.dl - aa.bzz) / aa.bzz), 4)) * 100 || '%' as cbbl from (select jz.shi as shi,(select agname from d_area_grade where agcode = zd.xian) as xian," +
    			"(select agname from d_area_grade where agcode = zd.xiaoqu) as xiaoqu,zd.id as zdid,zd.jzname as jzname, (select i.name from indexs i where i.code = zd.property) as property," +
    			"round( jz.dbydl, 4) dl,jz.bzz from jzxx jz left join zhandian zd on jz.zdid = zd.id where 1 = 1 and jz.bzz>0  AND symonth = '"+bzyf+"' AND zd.shi = '"+shi+"' AND zd.xian = '"+xian+"' AND jz.zdid = to_char(zd.id) " +
    		    "AND zd.property = '"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) aa where decode(aa.bzz,'0',aa.dl, (aa.dl - aa.bzz) / aa.bzz) >= "+cbbll+"";
    				

    	ResultSet rs = null;
    	System.out.println("超标站点09月之后"+sql.toString());
    		db.connectDb();
    		rs = db.queryAll(sql);
            while(rs.next()){
            	cbJzBean c = new cbJzBean();
            	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
            	c.setXian(null==rs.getString(2)?"":rs.getString(2));
            	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
            	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
            	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
            	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
            	c.setSjydl(null==rs.getString(7)?"":rs.getString(7));
            	c.setQsdbdl(null==rs.getString(8)?"":rs.getString(8));
            	c.setCbbl(null==rs.getString(9)?"":rs.getString(9));
            	list.add(c);
            }
    		
    	}

    	catch (DbException de) {
    		de.printStackTrace();
    	} catch (SQLException de) {
    		de.printStackTrace();
    	}

    	finally {
    		try {
    			db.close();
    		} catch (DbException de) {
    			de.printStackTrace();
    		}

    	}
    	return list;
    }
    

//结算电量超标
public synchronized ArrayList getJsdlcb(String shi,String xian,String bzyf,String bzyf02,String cbbl,String cbblsyc,String loginId ,String property) {
	
	double cbblsyc_zd = Double.valueOf(cbblsyc.toString())/100;
	double cbbll = Double.valueOf(cbbl.toString())/100;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM");
	Date bzdate = null;
	Date da = null;
	String yf = "2013-08";
	
	try {
		bzdate = fmt.parse(bzyf);
		da = fmt.parse(yf);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	ArrayList list = new ArrayList();
	
	String sql = "";
	
	String sql1="";
	//String sql2="";
	String sql3="";
	String sql4="";
	DataBase db = new DataBase();
	//结算电量超标2013-03、04明细
	if(bzyf.equals("2013-03")||bzyf.equals("2013-04")){
		String bmonth=bzyf.substring(5,7);
		String smonth=bzyf.substring(5,7);
	    if(bmonth.equals("03")){bmonth="month3";smonth="month2";}
	    if(bmonth.equals("04")){bmonth="month4";smonth="month3";}
		try {
			/*sql2 = "select d.agname, s.agname, a.agname, zd.id, zd.jzname, i.name, round(aa.jsdl, 4), aa.qsdb," +
					" round((aa.jsdl - aa.qsdb) / aa.qsdb, 4) * 100,bb.gldl,bb.sdb, round((bb.gldl - bb.sdb) / bb.sdb, 4) * 100 from" +
					" (select dl.shi, dl.zdid,  dl.jzname,  dl.zp, dl.zs, dl.changjia, dl.g2, dl.g3, dl.jsdl, z."+bmonth+" as qsdb from " +
					"" +
					"(select t.shi, t.zdid, t.jzname,t.zp, t.zs, t.changjia, t.g2,t.g3, t.jsdl from jzxx t where 1 = 1 AND symonth = '"+bzyf+"') dl," +
					" zdnhbz z where dl.zp = z.zp and dl.zs = z.zs and dl.changjia = z.changjia and dl.g2 = z.g2 and dl.g3 = z.g3 and" +
					" (dl.jsdl - z."+bmonth+") / z."+bmonth+" > "+cbblsyc_zd+") aa,(select dl.shi, dl.zdid,dl.jzname,dl.zp, dl.zs,dl.changjia, dl.g2,dl.g3,dl.gldl," +
					" z."+smonth+" as sdb from (select t.shi, t.zdid,t.jzname, t.zp, t.zs,t.changjia, t.g2, t.g3, round(decode(t.gldl, '', t.jsdl, '0', t.jsdl, t.gldl), 4) gldl" +
					" from jzxx t where 1 = 1 AND symonth = '"+bzyf02+"') dl, zdnhbz z where dl.zp = z.zp and dl.zs = z.zs and dl.changjia = z.changjia " +
					"and dl.g2 = z.g2 and dl.g3 = z.g3 and (dl.gldl - z."+smonth+") / z."+smonth+" < "+cbblsyc_zd+") bb, zhandian zd left join d_area_grade d on zd.shi = d.agcode" +
					" left join d_area_grade s on zd.xian = s.agcode left join d_area_grade a on zd.xiaoqu = a.agcode " +
					"left join indexs i on zd.property = i.code where aa.zdid = bb.zdid AND bb.zdid = to_char(zd.id)AND zd.shi = '"+shi+"' AND " +
					"zd.property = 'zdsx02' AND (zd.xiaoqu IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '263'))";*/
						
			sql3="select d.agname,s.agname,a.agname,zd.id, zd.jzname,i.name,round(aa.jsdl, 4),aa.bz,round((aa.jsdl - aa.bz) / aa.bz, 4) * 100," +
					"bb.gldl, bb.bz, round((bb.gldl - bb.bz) / bb.bz, 4) * 100 from (select dl.shi,dl.zdid,dl.jzname,dl.zp,dl.zs,dl.changjia,dl.g2,dl.g3,dl.jsdl, dl.bz " +
					"from (select t.shi,t.zdid, t.jzname,t.zp,t.zs,t.changjia,t.g2,t.g3,t.jsdl,t.sfgx, t.gxgwsl, " +
					"decode(trim(t.sfgx),'1', z."+bmonth+" + (23 * t.gxgwsl), '0',z."+bmonth+",'',z."+bmonth+",0) bz " +
					"from jzxx t, zdnhbz z where 1 = 1 and t.zp = z.zp and t.zs = z.zs and t.changjia = z.changjia and t.g2 = z.g2 and t.g3 = z.g3 " +
					"AND symonth = '"+bzyf+"') dl where (dl.jsdl - dl.bz) / dl.bz >= "+cbbll+") aa, (select dl.shi,dl.zdid, dl.jzname, dl.zp, dl.zs, dl.changjia, dl.g2, dl.g3, dl.gldl, dl.bz " +
					"from (select t.shi, t.zdid, t.jzname, t.zp, t.zs,t.changjia,t.g2,t.g3, t.sfgx,t.gxgwsl,round(decode(t.gldl, '', t.jsdl, '0', t.jsdl, t.gldl),4) gldl," +
					"decode(trim(t.sfgx),'1',z."+smonth+" + (23 * t.gxgwsl), '0', z."+smonth+",'', z."+smonth+", 0) bz from jzxx t, zdnhbz z where 1 = 1 and t.zp = z.zp and t.zs = z.zs" +
					" and t.changjia = z.changjia and t.g2 = z.g2 and t.g3 = z.g3 AND symonth = '"+bzyf02+"') dl where (dl.gldl - dl.bz) / dl.bz < "+cbblsyc_zd+") bb,zhandian zd left join d_area_grade d on zd.shi = d.agcode " +
					"left join d_area_grade s on zd.xian = s.agcode left join d_area_grade a on zd.xiaoqu = a.agcode left join indexs i on zd.property = i.code where aa.zdid = bb.zdid AND bb.zdid = to_char(zd.id) " +
					"AND zd.shi = '"+shi+"' and zd.xian = '"+xian+"' AND zd.property = '"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))";
			ResultSet rs = null;
			System.out.println("结算电量超标0304"+sql3.toString());
				db.connectDb();
				rs = db.queryAll(sql3);
		        while(rs.next()){
		        	cbJzBean c = new cbJzBean();
		        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
		        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
		        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
		        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
		        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
		        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
		        	c.setJsrjhdl(null==rs.getString(7)?"":rs.getString(7));
		        	c.setJssdb(null==rs.getString(8)?"":rs.getString(8));
		        	c.setJscbbl(null==rs.getString(9)?"":rs.getString(9));
		        	c.setGlrjhdl(null==rs.getString(10)?"":rs.getString(10));
		        	c.setGlsdb(null==rs.getString(11)?"":rs.getString(11));
		        	c.setGlcbbl(null==rs.getString(12)?"":rs.getString(12));
		        	list.add(c);
		        }
				
			}	catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException de) {
				de.printStackTrace();
			}

			finally {
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}

			}
			return list;
		
		
	}
	//结算电量超标2013-05明细查询
	if(bzyf.equals("2013-05")){
		String bmonth=bzyf.substring(5,7);
		
		if(bmonth.equals("05")){
			bmonth="month4";
			}
		try {
			/*sql1 = "select d.agname as shi,s.agname as xian, a.agname as xiaoqu,zd.id,zd.jzname as jzname,i.name as property,aa.jsdl," +
					"aa.qsdbdl,round(aa.bl,4)*100,bb.gldl,bb.month4,round((bb.gldl-bb.month4)/bb.month4*100,4)from (select dl.shi, dl.zdid, dl.jzname," +
					" dl.jsdl, dl.qsdbdl,(dl.jsdl - dl.qsdbdl) / decode(dl.qsdbdl, '0', '1', dl.qsdbdl)as bl from (select t.shi, t.zdid, t.jzname, " +
					"t.jsdl, t.qsdbdl from jzxx t where 1 = 1 AND symonth = '"+bzyf+"') dl where (dl.jsdl - dl.qsdbdl) / decode(dl.qsdbdl, '0', '1'," +
					" dl.qsdbdl) > "+cbblsyc_zd+") aa,(select dl.shi, dl.zdid,dl.jzname, dl.zp, dl.zs, dl.changjia, dl.g2, dl.g3, dl.gldl, z.month4 from " +
					"(select t.shi, t.zdid,t.jzname, t.zp,t.zs,t.changjia,t.g2,t.g3, round(decode(t.gldl, '', t.jsdl, '0', t.jsdl, t.gldl),4) gldl from" +
					" jzxx t where 1 = 1 AND symonth = '"+bzyf02+"') dl, zdnhbz z where dl.zp = z.zp and dl.zs = z.zs and dl.changjia = z.changjia and dl.g2 = z.g2" +
					" and dl.g3 = z.g3 and (dl.gldl - z."+bmonth+") / z."+bmonth+" < "+cbblsyc_zd+") bb, zhandian zd left join d_area_grade d on zd.shi=d.agcode left join d_area_grade s" +
					" on zd.xian=s.agcode left join d_area_grade a on zd.xiaoqu=a.agcode left join indexs i on zd.property=i.code where aa.zdid = bb.zdid AND zd.shi='"+shi+"'AND bb.zdid = to_char(zd.id) AND zd.property = 'zdsx02' AND (zd.xiaoqu IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '263'))";*/
						
			sql1 = "select d.agname as shi,s.agname as xian,a.agname as xiaoqu,zd.id,zd.jzname as jzname,i.name as property,aa.jsdl,aa.qsdbdl," +
					"round(aa.bl, 4) * 100,bb.gldl,bb.bz,round((bb.gldl - bb.bz) / bb.bz * 100, 4)" +
					" from (select dl.shi, dl.zdid, dl.jzname, dl.jsdl, dl.qsdbdl, (dl.jsdl - dl.qsdbdl) / decode(dl.qsdbdl, '0', '1', dl.qsdbdl) as bl" +
					" from (select t.shi, t.zdid, t.jzname, t.jsdl, t.qsdbdl from jzxx t where 1 = 1 AND symonth = '"+bzyf+"') dl" +
					" where (dl.jsdl - dl.qsdbdl) /decode(dl.qsdbdl, '0', '1', dl.qsdbdl) >= "+cbbll+") aa,(select dl.shi,dl.zdid,dl.jzname,dl.zp,dl.zs,dl.changjia,dl.g2,dl.g3,dl.gldl,dl.bz" +
					" from (select t.shi,t.zdid,t.jzname,t.zp,t.zs,t.changjia,t.g2,t.g3,t.sfgx,t.gxgwsl,round(decode(t.gldl,'',t.jsdl,'0',t.jsdl,t.gldl),4) gldl," +
					"decode(trim(t.sfgx),'1',z."+bmonth+" + (23 * t.gxgwsl),'0',z."+bmonth+",'',z."+bmonth+",0) bz from jzxx t,zdnhbz z" +
					" where 1 = 1 and t.zp = z.zp and t.zs = z.zs and t.changjia = z.changjia and t.g2 = z.g2 and t.g3 = z.g3 AND symonth = '"+bzyf02+"') dl" +
					" where (dl.gldl - dl.bz) / dl.bz < "+cbblsyc_zd+") bb,zhandian zd left join d_area_grade d on zd.shi = d.agcode" +
					" left join d_area_grade s on zd.xian = s.agcode left join d_area_grade a on zd.xiaoqu = a.agcode " +
					"left join indexs i on zd.property = i.code where aa.zdid = bb.zdid AND zd.shi = '"+shi+"' and zd.xian = '"+xian+"' AND bb.zdid = to_char(zd.id)" +
					" AND zd.property = '"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))";
			ResultSet rs = null;
			System.out.println("结算电量超标05"+sql1.toString());
				db.connectDb();
				rs = db.queryAll(sql1);
		        while(rs.next()){
		        	cbJzBean c = new cbJzBean();
		        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
		        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
		        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
		        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
		        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
		        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
		        	c.setJsrjhdl(null==rs.getString(7)?"":rs.getString(7));
		        	c.setJssdb(null==rs.getString(8)?"":rs.getString(8));
		        	c.setJscbbl(null==rs.getString(9)?"":rs.getString(9));
		        	c.setGlrjhdl(null==rs.getString(10)?"":rs.getString(10));
		        	c.setGlsdb(null==rs.getString(11)?"":rs.getString(11));
		        	c.setGlcbbl(null==rs.getString(12)?"":rs.getString(12));
		        	list.add(c);
		        }
				
			}

			catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException de) {
				de.printStackTrace();
			}

			finally {
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}

			}
			return list;
		
		
	}
	if(bzyf.equals("2013-06")||bzyf.equals("2013-07")||bzyf.equals("2013-08")){
	//结算电量超标07、06、08查询
	try {
	sql = "select d.agname,s.agname,a.agname,zd.id,zd.jzname,i.name,round(aa.jsdl,4),aa.qsdbdl,round((aa.jsdl-aa.qsdbdl)/aa.qsdbdl,4)*100," +
			"round(bb.gldl,4),bb.qsdbdl,round((bb.gldl-bb.qsdbdl)/bb.qsdbdl,4)*100 from (select dl.shi, dl.zdid, dl.jzname, dl.jsdl, dl.qsdbdl " +
			"from (select t.shi, t.zdid, t.jzname, t.jsdl, t.qsdbdl from jzxx t where 1 = 1  AND symonth = '"+bzyf+"') dl" +
			" where (dl.jsdl - dl.qsdbdl) / decode(dl.qsdbdl, '0', dl.jsdl, dl.qsdbdl) >= "+cbbll+") aa,(select dl.shi, dl.zdid, dl.jzname, dl.gldl, dl.qsdbdl " +
			"from (select t.shi, t.zdid, t.jzname, decode(t.gldl, '', t.jsdl, '0', t.jsdl, t.gldl) gldl, t.qsdbdl  from jzxx t where 1 = 1 AND" +
			" symonth = '"+bzyf02+"') dl where (dl.gldl - dl.qsdbdl) / decode(dl.qsdbdl, '0', dl.gldl, dl.qsdbdl) < "+cbblsyc_zd+") bb,zhandian zd left join d_area_grade d " +
			"on zd.shi = d.agcode left join d_area_grade s on zd.xian = s.agcode left join d_area_grade a on zd.xiaoqu = a.agcode left join indexs i on" +
			" zd.property = i.code where aa.zdid = bb.zdid AND bb.zdid = to_char(zd.id)AND zd.property = '"+property+"' AND zd.shi = '"+shi+"' and zd.xian = '"+xian+"' AND (zd.xiaoqu IN " +
			"(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))";
				

	ResultSet rs = null;
	System.out.println("结算电量超标06-08以及之后"+sql.toString());
		db.connectDb();
		rs = db.queryAll(sql);
        while(rs.next()){
        	cbJzBean c = new cbJzBean();
        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
        	c.setJsrjhdl(null==rs.getString(7)?"":rs.getString(7));
        	c.setJssdb(null==rs.getString(8)?"":rs.getString(8));
        	c.setJscbbl(null==rs.getString(9)?"":rs.getString(9));
        	c.setGlrjhdl(null==rs.getString(10)?"":rs.getString(10));
        	c.setGlsdb(null==rs.getString(11)?"":rs.getString(11));
        	c.setGlcbbl(null==rs.getString(12)?"":rs.getString(12));
        	list.add(c);
        }
		
	}

	catch (DbException de) {
		de.printStackTrace();
	} catch (SQLException de) {
		de.printStackTrace();
	}

	finally {
		try {
			db.close();
		} catch (DbException de) {
			de.printStackTrace();
		}

	}
	}
	
	//结算电量超标9月以后查询
	if(bzdate.after(da)){
	try {
	sql4 = "select d.agname,s.agname,a.agname,zd.id,zd.jzname,i.name,round(aa.dbydl,4),aa.bzz,round((aa.dbydl-aa.bzz)/aa.bzz,4)*100," +
			"round(bb.dbgldl,4),bb.bzz,round((bb.dbgldl-bb.bzz)/bb.bzz,4)*100 from (select dl.shi, dl.zdid, dl.jzname, dl.dbydl, dl.bzz " +
			"from (select t.shi, t.zdid, t.jzname, t.dbydl, t.bzz from jzxx t where 1 = 1 and t.bzz>0  AND symonth = '"+bzyf+"') dl" +
			" where (dl.dbydl - dl.bzz) / decode(dl.bzz, '0', dl.dbydl, dl.bzz) >= "+cbbll+") aa,(select dl.shi, dl.zdid, dl.jzname, dl.dbgldl, dl.bzz " +
			"from (select t.shi, t.zdid, t.jzname,t.dbgldl, t.bzz  from jzxx t where 1 = 1 and t.bzz>0  AND" +
			" symonth = '"+bzyf02+"') dl where (dl.dbgldl - dl.bzz) / decode(dl.bzz, '0', dl.dbgldl, dl.bzz) < "+cbblsyc_zd+") bb,zhandian zd left join d_area_grade d " +
			"on zd.shi = d.agcode left join d_area_grade s on zd.xian = s.agcode left join d_area_grade a on zd.xiaoqu = a.agcode left join indexs i on" +
			" zd.property = i.code where aa.zdid = bb.zdid AND bb.zdid = to_char(zd.id)AND zd.property = '"+property+"' AND zd.shi = '"+shi+"' and zd.xian = '"+xian+"' AND zd.xian = '"+xian+"' AND (zd.xiaoqu IN " +
			"(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))";
				

	ResultSet rs = null;
	System.out.println("结算电量超标9月以及之后"+sql4.toString());
		db.connectDb();
		rs = db.queryAll(sql4);
        while(rs.next()){
        	cbJzBean c = new cbJzBean();
        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
        	c.setJsrjhdl(null==rs.getString(7)?"":rs.getString(7));
        	c.setJssdb(null==rs.getString(8)?"":rs.getString(8));
        	c.setJscbbl(null==rs.getString(9)?"":rs.getString(9));
        	c.setGlrjhdl(null==rs.getString(10)?"":rs.getString(10));
        	c.setGlsdb(null==rs.getString(11)?"":rs.getString(11));
        	c.setGlcbbl(null==rs.getString(12)?"":rs.getString(12));
        	list.add(c);
        }
		
	}

	catch (DbException de) {
		de.printStackTrace();
	} catch (SQLException de) {
		de.printStackTrace();
	}

	finally {
		try {
			db.close();
		} catch (DbException de) {
			de.printStackTrace();
		}
	}
	}
	return list;
}
//连续超标
public synchronized ArrayList getLxcb(String shi,String xian,String bzyf,String bzyf02,String cbbl,String cbblsyc,String loginId,String property ) {
	
	double cbblsyc_zd = Double.valueOf(cbblsyc.toString())/100;
	double cbbll= Double.valueOf(cbbl.toString())/100;
	ArrayList list = new ArrayList();
	
	String sql = "";
	String sql1="";
	String sql2="";
	DataBase db = new DataBase();
	//连续超标03、04月份查询
	if(bzyf.equals("2013-03")||bzyf.equals("2013-04")){
		String bmonth=bzyf.substring(5,7);
		String smonth=bzyf.substring(5,7);
	    if(bmonth.equals("03")){bmonth="month3";smonth="month2";}
	    if(bmonth.equals("04")){bmonth="month4";smonth="month3";}
	    
		try {
			sql2 = " select d.agname, s.agname, a.agname, zd.id, zd.jzname, i.name," +
					"b.dl,b.bz,round((b.dl-b.bz)/b.bz,4)*100||'%' as bbl,a.dl,a.bz,round((a.dl-a.bz)/a.bz,4)*100||'%' as sbl " +
					"from (select ab.shi, ab.zdid, ab.dl,ab.bz," +
					" (ab.dl - ab.bz) / ab.bz countbz " +
					"from (select jz.zdid, jz.sfgx, jz.shi, round(decode(jz.dbydl, '', jz.dbgldl, '0', jz.dbgldl, jz.dbydl),4) dl," +
					" decode(trim(jz.sfgx), '1', zd."+smonth+" + (23 * jz.gxgwsl),'0',zd."+smonth+",'',zd."+smonth+", 0) bz from jzxx jz, zdnhbz zd " +
					"where jz.changjia = zd.changjia and jz.g2 = zd.g2 and jz.g3 = zd.g3 and jz.zp = zd.zp and jz.zs = zd.zs" +
					" AND symonth = '"+bzyf02+"') ab) a, (select ab.zdid, ab.shi, ab.dl,ab.bz, " +
					"(ab.dl - ab.bz) / ab.bz countbz from (select jz.zdid,jz.sfgx,jz.shi,round( decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl),4) dl," +
					"decode(trim(jz.sfgx), '1',zd."+bmonth+" + (23 * jz.gxgwsl),'0', zd."+bmonth+",'',zd."+bmonth+", 0) bz from jzxx jz, zdnhbz zd where jz.changjia = zd.changjia and jz.g2 = zd.g2" +
					" and jz.g3 = zd.g3 and jz.zp = zd.zp and jz.zs = zd.zs AND symonth = '"+bzyf+"') ab) b,zhandian zd left join d_area_grade d on zd.shi = d.agcode " +
					"left join d_area_grade s on zd.xian = s.agcode left join d_area_grade a on zd.xiaoqu = a.agcode left join indexs i on zd.property = i.code where a.zdid = b.zdid" +
					" AND b.zdid = to_char(zd.id) AND zd.shi = '"+shi+"' and zd.xian = '"+xian+"' AND zd.property = '"+property+"' AND (zd.xiaoqu IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and a.countbz >= "+cbblsyc_zd+" and b.countbz >= "+cbbll+"";
						

			ResultSet rs = null;
			System.out.println("连续超标0304"+sql2.toString());
				db.connectDb();
				rs = db.queryAll(sql2);
		        while(rs.next()){
		        	cbJzBean c = new cbJzBean();
		        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
		        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
		        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
		        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
		        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
		        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
		        	c.setJsrjhdl(null==rs.getString(7)?"":rs.getString(7));
		        	c.setJssdb(null==rs.getString(8)?"":rs.getString(8));
		        	c.setJscbbl(null==rs.getString(9)?"":rs.getString(9));
		        	c.setGlrjhdl(null==rs.getString(10)?"":rs.getString(10));
		        	c.setGlsdb(null==rs.getString(11)?"":rs.getString(11));
		        	c.setGlcbbl(null==rs.getString(12)?"":rs.getString(12));
		        	list.add(c);
		        }
				
			}

			catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException de) {
				de.printStackTrace();
			}

			finally {
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}

			}
			return list;
		}
	//连续超标05月份查询
	if(bzyf.equals("2013-05")){
		String bmonth=bzyf.substring(5,7);
	
	    if(bmonth.equals("05")){bmonth="month4";}
	    
		try {
			sql1 = "select  d.agname, s.agname, a.agname, zd.id, zd.jzname, i.name," +
					" b.dl,b.bz,round((b.dl-b.bz)/b.bz,4)*100||'%'as bbl,a.dl,a.bz,round((a.dl-a.bz)/a.bz,4)*100||'%'as sbl" +
					" from (select ab.shi,ab.zdid, ab.dl,ab.bz, (ab.dl - ab.bz) / ab.bz countbz" +
					" from (select jz.zdid,jz.sfgx, jz.shi, round(decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl),4) dl, " +
					"decode(trim(jz.sfgx), '1', zd."+bmonth+"+ (23 * jz.gxgwsl), '0', zd."+bmonth+",'',zd."+bmonth+", 0) bz from jzxx jz, zdnhbz zd " +
					"where jz.changjia = zd.changjia and jz.g2 = zd.g2 and jz.g3 = zd.g3  and jz.zp = zd.zp and jz.zs = zd.zs AND symonth = '"+bzyf02+"') ab) a," +
					" (select ab.zdid, ab.shi, ab.dl,ab.bz,(ab.dl - ab.bz) / ab.bz as countbz " +
					"from (select jz.zdid,jz.shi,round(decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl),4)dl, decode(jz.qsdbdl, '0', '1', jz.qsdbdl) bz" +
					" from jzxx jz where 1 = 1 AND symonth = '"+bzyf+"') ab) b, zhandian zd left join d_area_grade d on zd.shi = d.agcode left join d_area_grade s " +
					"on zd.xian = s.agcode left join d_area_grade a on zd.xiaoqu = a.agcode left join indexs i on zd.property = i.code where a.zdid = b.zdid " +
					"AND b.zdid = to_char(zd.id)AND zd.shi='"+shi+"' and zd.xian='"+xian+"' AND zd.property = '"+property+"' AND (zd.xiaoqu IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and a.countbz >= "+cbblsyc_zd+" and b.countbz >= "+cbbll+"";
						

			ResultSet rs = null;
			System.out.println("连续超标05"+sql1.toString());
				db.connectDb();
				rs = db.queryAll(sql1);
		        while(rs.next()){
		        	cbJzBean c = new cbJzBean();
		        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
		        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
		        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
		        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
		        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
		        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
		        	c.setJsrjhdl(null==rs.getString(7)?"":rs.getString(7));
		        	c.setJssdb(null==rs.getString(8)?"":rs.getString(8));
		        	c.setJscbbl(null==rs.getString(9)?"":rs.getString(9));
		        	c.setGlrjhdl(null==rs.getString(10)?"":rs.getString(10));
		        	c.setGlsdb(null==rs.getString(11)?"":rs.getString(11));
		        	c.setGlcbbl(null==rs.getString(12)?"":rs.getString(12));
		        	list.add(c);
		        }
				
			}

			catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException de) {
				de.printStackTrace();
			}

			finally {
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}

			}
			return list;
		}
		
		
	//连续超标6、7、8月份查询
	if(bzyf.equals("2013-06")||bzyf.equals("2013-07")||bzyf.equals("2013-08")){
	try {
	sql = "select d.agname,a.agname,s.agname,zd.id,zd.jzname,i.name,round(b.dl,4),b.bz," +
			"round(b.countbz,4)*100 || '%' as thismonth,round(a.dl,4),a.bz,round(a.countbz,4)*100 || '%'" +
			" as lastmonth from (select ab.shi, ab.zdid,ab.dl,ab.bz,decode(ab.bz, '0', ab.dl, " +
			"(ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi, decode(jz.jsdl, '', " +
			"jz.gldl, '0', jz.gldl, jz.jsdl) dl,jz.qsdbdl as bz from jzxx jz where 1 = 1 AND " +
			"symonth = '"+bzyf02+"') ab) a,(select ab.zdid, ab.shi, ab.dl,ab.bz,(ab.dl - ab.bz) / ab.bz as" +
			" countbz from (select jz.zdid,jz.shi,decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl) dl," +
			" jz.qsdbdl as bz from jzxx jz where 1 = 1 AND symonth = '"+bzyf+"') ab) b, zhandian zd " +
			"left join  D_AREA_GRADE d on zd.shi=d.agcode left join  D_AREA_GRADE a on zd.xian=a.agcode " +
			"left join  D_AREA_GRADE s on zd.xiaoqu=s.agcode left join  indexs i on zd.property=i.code " +
			"where a.zdid = b.zdid AND b.zdid = to_char(zd.id) AND zd.property = '"+property+"' " +
			"AND zd.shi='"+shi+"' and zd.xian='"+xian+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE " +
			"T.ACCOUNTID = '"+loginId+"')) and a.countbz >= "+cbblsyc_zd+" and b.countbz >= "+cbbll+"";
				

	ResultSet rs = null;
	System.out.println("连续超标06-08月份"+sql.toString());
		db.connectDb();
		rs = db.queryAll(sql);
        while(rs.next()){
        	cbJzBean c = new cbJzBean();
        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
        	c.setJsrjhdl(null==rs.getString(7)?"":rs.getString(7));
        	c.setJssdb(null==rs.getString(8)?"":rs.getString(8));
        	c.setJscbbl(null==rs.getString(9)?"":rs.getString(9));
        	c.setGlrjhdl(null==rs.getString(10)?"":rs.getString(10));
        	c.setGlsdb(null==rs.getString(11)?"":rs.getString(11));
        	c.setGlcbbl(null==rs.getString(12)?"":rs.getString(12));
        	list.add(c);
        }
		
	}

	catch (DbException de) {
		de.printStackTrace();
	} catch (SQLException de) {
		de.printStackTrace();
	}

	finally {
		try {
			db.close();
		} catch (DbException de) {
			de.printStackTrace();
		}

	}
	return list;
}
//连续超标9月份之后
	try {
		sql = "select d.agname,a.agname,s.agname,zd.id,zd.jzname,i.name,round(b.dl,4),b.bz,round(b.countbz,4)*100 || '%' as thismonth," +
				"round(a.dl,4),a.bz,round(a.countbz,4)*100 || '%' as lastmonth from (select ab.shi, ab.zdid,ab.dl,ab.bz," +
				"decode(ab.bz, '0', ab.dl, (ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi, jz.dbydl as dl,jz.bzz as bz from jzxx jz " +
				"where 1 = 1 and jz.bzz>0  AND symonth = '"+bzyf02+"') ab) a,(select ab.zdid, ab.shi, ab.dl,ab.bz,(ab.dl - ab.bz) / ab.bz as countbz from" +
				" (select jz.zdid,jz.shi,jz.dbydl as dl, jz.bzz as bz from jzxx jz where 1 = 1 and jz.bzz>0  AND symonth = '"+bzyf+"') ab) b, zhandian zd " +
				"left join  D_AREA_GRADE d on zd.shi=d.agcode left join  D_AREA_GRADE a on zd.xian=a.agcode left join  D_AREA_GRADE s on zd.xiaoqu=s.agcode " +
				"left join  indexs i on zd.property=i.code where a.zdid = b.zdid AND b.zdid = to_char(zd.id) AND zd.property = '"+property+"' AND zd.shi='"+shi+"' AND zd.xian='"+xian+"'" +
				" AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and a.countbz >= "+cbblsyc_zd+" and b.countbz >= "+cbbll+"";
					

		ResultSet rs = null;
		System.out.println("连续超标9月份"+sql.toString());
			db.connectDb();
			rs = db.queryAll(sql);
	        while(rs.next()){
	        	cbJzBean c = new cbJzBean();
	        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
	        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
	        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
	        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
	        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
	        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
	        	c.setJsrjhdl(null==rs.getString(7)?"":rs.getString(7));
	        	c.setJssdb(null==rs.getString(8)?"":rs.getString(8));
	        	c.setJscbbl(null==rs.getString(9)?"":rs.getString(9));
	        	c.setGlrjhdl(null==rs.getString(10)?"":rs.getString(10));
	        	c.setGlsdb(null==rs.getString(11)?"":rs.getString(11));
	        	c.setGlcbbl(null==rs.getString(12)?"":rs.getString(12));
	        	list.add(c);
	        }
			
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}
		return list;
	}
//连续超标且电量增加
public synchronized ArrayList getLxcbzj(String shi,String xian,String bzyf,String bzyf02,String cbbl,String cbblsyc,String loginId,String property) {
	
	double cbblsyc_zd = Double.valueOf(cbblsyc.toString())/100;
	double cbbll = Double.valueOf(cbbl.toString())/100;
	ArrayList list = new ArrayList();
	
	String sql = "";
	String sql1="";
	String sql2="";
	DataBase db = new DataBase();
	//连续超标增加05月份查询
	if(bzyf.equals("2013-05")){
		String bmonth=bzyf.substring(5,7);
		if(bmonth.equals("05")){
			
			bmonth="month4";
		}
		try {
	    	sql2 = "select d.agname, s.agname, a.agname, zd.id, zd.jzname, i.name,b.dl,b.bz,round((b.dl-b.bz)/b.bz,4)*100||'%'," +
	    			"a.dl,a.bz,round((a.dl-a.bz)/a.bz,4)*100||'%' from (select ab.shi,ab.zdid, ab.dl, ab.bz," +
	    			" (ab.dl - ab.bz) / ab.bz countbz " +
	    			"from (select jz.zdid, jz.sfgx, jz.shi, round(decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl),4)dl, " +
	    			"decode(trim(jz.sfgx), '1', zd."+bmonth+" + (23 * jz.gxgwsl), '0', zd."+bmonth+", '', zd."+bmonth+", 0) bz" +
	    			" from jzxx jz, zdnhbz zd where jz.changjia = zd.changjia and jz.g2 = zd.g2 and jz.g3 = zd.g3 and jz.zp = zd.zp" +
	    			" and jz.zs = zd.zs AND symonth = '"+bzyf02+"') ab) a,(select ab.zdid, ab.shi, ab.dl, ab.bz,(ab.dl - ab.bz) / ab.bz as countbz " +
	    			"from (select jz.zdid, jz.shi, round( decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl),4) dl, " +
	    			"decode(jz.qsdbdl, '0', '1', jz.qsdbdl) bz from jzxx jz where 1 = 1 AND symonth = '"+bzyf+"') ab) b, zhandian zd " +
	    			"left join d_area_grade d on zd.shi = d.agcode left join d_area_grade s on zd.xian = s.agcode " +
	    			"left join d_area_grade a on zd.xiaoqu = a.agcode left join indexs i on zd.property = i.code where a.zdid = b.zdid " +
	    			"AND b.zdid = to_char(zd.id) AND zd.shi='"+shi+"' and zd.xian='"+xian+"' AND zd.property = '"+property+"' " +
	    			"AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and a.countbz >= "+cbblsyc_zd+" and b.countbz >= "+cbbll+" and b.dl > a.dl";
	    				

	    	ResultSet rs = null;
	    	System.out.println("连续超标增加05"+sql2.toString());
	    		db.connectDb();
	    		rs = db.queryAll(sql2);
	            while(rs.next()){
	            	cbJzBean c = new cbJzBean();
	            	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
	            	c.setXian(null==rs.getString(2)?"":rs.getString(2));
	            	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
	            	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
	            	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
	            	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
	            	c.setJsrjhdl(null==rs.getString(7)?"":rs.getString(7));
	            	c.setJssdb(null==rs.getString(8)?"":rs.getString(8));
	            	c.setJscbbl(null==rs.getString(9)?"":rs.getString(9));
	            	c.setGlrjhdl(null==rs.getString(10)?"":rs.getString(10));
	            	c.setGlsdb(null==rs.getString(11)?"":rs.getString(11));
	            	c.setGlcbbl(null==rs.getString(12)?"":rs.getString(12));
	            	list.add(c);
	            }
	    		
	    	}

	    	catch (DbException de) {
	    		de.printStackTrace();
	    	} catch (SQLException de) {
	    		de.printStackTrace();
	    	}

	    	finally {
	    		try {
	    			db.close();
	    		} catch (DbException de) {
	    			de.printStackTrace();
	    		}

	    	}
	    	return list;
	    }
		
	//连续超标增加0304月份查询明细
	if(bzyf.equals("2013-03")||bzyf.equals("2013-04")){
		String bmonth=bzyf.substring(5,7);
		String smonth=bzyf.substring(5,7);
	    if(bmonth.equals("03")){bmonth="month3";smonth="month2";}
	    if(bmonth.equals("04")){bmonth="month4";smonth="month3";}
	    try {
	    	sql1 = "select d.agname, s.agname, a.agname, zd.id, zd.jzname, i.name," +
	    			"b.dl,b.bz,round((b.dl-b.bz)/b.bz,4)*100||'%' as bbl,a.dl,a.bz,round((a.dl-a.bz)/a.bz,4)*100||'%' as sbl " +
	    			"from (select ab.shi,ab.zdid,ab.dl,ab.bz,(ab.dl - ab.bz) / ab.bz countbz" +
	    			" from (select jz.zdid, jz.sfgx,jz.shi,round( decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl),4) dl," +
	    			" decode(trim(jz.sfgx),'1',zd."+smonth+" + (23 * jz.gxgwsl), '0',zd."+smonth+",'',zd."+smonth+",0) bz from jzxx jz, zdnhbz zd where jz.changjia = zd.changjia" +
	    			" and jz.g2 = zd.g2 and jz.g3 = zd.g3 and jz.zp = zd.zp and jz.zs = zd.zs AND symonth = '"+bzyf02+"') ab) a,(select ab.zdid,ab.shi,ab.dl, ab.bz, " +
	    			" (ab.dl - ab.bz) / ab.bz countbz " +
	    			"from (select jz.zdid,jz.sfgx,jz.shi, round(decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl),4) dl, decode(trim(jz.sfgx), '1',zd."+bmonth+" + (23 * jz.gxgwsl), '0',zd."+bmonth+", '', zd."+bmonth+",0) bz " +
	    			"from jzxx jz, zdnhbz zd where jz.changjia = zd.changjia and jz.g2 = zd.g2 and jz.g3 = zd.g3 and jz.zp = zd.zp and jz.zs = zd.zs AND" +
	    			" symonth = '"+bzyf+"') ab) b, zhandian zd left join d_area_grade d on zd.shi = d.agcode left join d_area_grade s on zd.xian = s.agcode left join d_area_grade a on zd.xiaoqu = a.agcode" +
	    			" left join indexs i on zd.property = i.code where a.zdid = b.zdid AND b.zdid = to_char(zd.id) AND zd.shi = '"+shi+"' and zd.xian = '"+xian+"' AND zd.property = '"+property+"'" +
	    			" AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and a.countbz >= "+cbblsyc_zd+"  and b.countbz >= "+cbbll+" and b.dl > a.dl";
	    				

	    	ResultSet rs = null;
	    	System.out.println("连续超标增加0304"+sql1.toString());
	    		db.connectDb();
	    		rs = db.queryAll(sql1);
	            while(rs.next()){
	            	cbJzBean c = new cbJzBean();
	            	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
	            	c.setXian(null==rs.getString(2)?"":rs.getString(2));
	            	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
	            	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
	            	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
	            	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
	            	c.setJsrjhdl(null==rs.getString(7)?"":rs.getString(7));
	            	c.setJssdb(null==rs.getString(8)?"":rs.getString(8));
	            	c.setJscbbl(null==rs.getString(9)?"":rs.getString(9));
	            	c.setGlrjhdl(null==rs.getString(10)?"":rs.getString(10));
	            	c.setGlsdb(null==rs.getString(11)?"":rs.getString(11));
	            	c.setGlcbbl(null==rs.getString(12)?"":rs.getString(12));
	            	list.add(c);
	            }
	    		
	    	}

	    	catch (DbException de) {
	    		de.printStackTrace();
	    	} catch (SQLException de) {
	    		de.printStackTrace();
	    	}

	    	finally {
	    		try {
	    			db.close();
	    		} catch (DbException de) {
	    			de.printStackTrace();
	    		}

	    	}
	    	return list;
	    }
	    
	    
	//连续超标增加6、7、8月份查询
	if(bzyf.equals("2013-06")||bzyf.equals("2013-07")||bzyf.equals("2013-08")){
	try {
	sql = "select d.agname, a.agname, s.agname, zd.id, zd.jzname, i.name, round(b.dl, 4), b.bz, " +
		"round(b.countbz, 4) * 100 || '%' as thismonth, round(a.dl, 4),a.bz,round(a.countbz, 4) * 100 || '%' as " +
		"lastmonth from (select ab.shi, ab.zdid, ab.dl,ab.bz,decode(ab.bz, '0', ab.dl,(ab.dl - ab.bz) / ab.bz )as" +
		" countbz from (select jz.zdid,jz.shi, decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl) dl,jz.qsdbdl as" +
		" bz from jzxx jz where 1 = 1 AND symonth = '"+bzyf02+"') ab) a, (select ab.zdid, ab.shi, ab.dl,ab.bz," +
		" decode(ab.bz, '0', ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz from (select jz.zdid, jz.shi, " +
		"decode(jz.jsdl, '', jz.gldl, '0', jz.gldl, jz.jsdl) dl, jz.qsdbdl as bz from jzxx jz where 1 = 1 AND" +
		" symonth = '"+bzyf+"') ab) b,zhandian zd left join D_AREA_GRADE d on zd.shi = d.agcode left" +
		" join D_AREA_GRADE a on zd.xian = a.agcode left join D_AREA_GRADE s on zd.xiaoqu = s.agcode" +
		" left join indexs i on zd.property = i.code where a.zdid = b.zdid AND zd.shi='"+shi+"' and zd.xian='"+xian+"' AND" +
		" b.zdid = to_char(zd.id) AND zd.property = '"+property+"' AND (zd.xiaoqu IN " +
		"(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and a.countbz >= "+cbblsyc_zd+" " +
		"and b.countbz >= "+cbbll+" and b.dl > a.dl";
				

	ResultSet rs = null;
	System.out.println("连续超标增加6、7、8"+sql.toString());
		db.connectDb();
		rs = db.queryAll(sql);
        while(rs.next()){
        	cbJzBean c = new cbJzBean();
        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
        	c.setJsrjhdl(null==rs.getString(7)?"":rs.getString(7));
        	c.setJssdb(null==rs.getString(8)?"":rs.getString(8));
        	c.setJscbbl(null==rs.getString(9)?"":rs.getString(9));
        	c.setGlrjhdl(null==rs.getString(10)?"":rs.getString(10));
        	c.setGlsdb(null==rs.getString(11)?"":rs.getString(11));
        	c.setGlcbbl(null==rs.getString(12)?"":rs.getString(12));
        	list.add(c);
        }
		
	}

	catch (DbException de) {
		de.printStackTrace();
	} catch (SQLException de) {
		de.printStackTrace();
	}

	finally {
		try {
			db.close();
		} catch (DbException de) {
			de.printStackTrace();
		}

	}
	return list;
}
	try {
		sql = "select d.agname, a.agname, s.agname, zd.id, zd.jzname, i.name, round(b.dl, 4)," +
			" b.bz, round(b.countbz, 4) * 100 || '%' as thismonth, round(a.dl, 4),a.bz," +
			"round(a.countbz, 4) * 100 || '%' as lastmonth from (select ab.shi, ab.zdid, " +
			"ab.dl,ab.bz,decode(ab.bz, '0', ab.dl,(ab.dl - ab.bz) / ab.bz )as countbz " +
			"from (select jz.zdid,jz.shi,jz.dbydl as dl,jz.bzz as bz from jzxx jz,zhandian z where jz.zdid=z.id and jz.bzz>0  and 1 = 1"  +
			" AND symonth = '"+bzyf02+"' and z.property='"+property+"') ab) a, (select ab.zdid, ab.shi, ab.dl,ab.bz, " +
			"decode(ab.bz, '0', ab.dl,(ab.dl - ab.bz) / ab.bz) as countbz from " +
			"(select jz.zdid, jz.shi,jz.dbydl as dl, jz.bzz as bz from jzxx jz,zhandian z where z.id=jz.zdid" +
			" and 1 = 1 and jz.bzz>0  AND symonth = '"+bzyf+"' and z.property='"+property+"') ab) b,zhandian zd left join D_AREA_GRADE d on" +
			" zd.shi = d.agcode left join D_AREA_GRADE a on zd.xian = a.agcode left join D_AREA_GRADE s on zd.xiaoqu = s.agcode" +
			" left join indexs i on zd.property = i.code where a.zdid = b.zdid AND zd.shi='"+shi+"' AND zd.xian='"+xian+"' AND b.zdid = to_char(zd.id) AND zd.property = '"+property+"' AND" +
			" (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) and a.countbz >= "+cbblsyc_zd+" and b.countbz >= "+cbbll+" and b.dl > a.dl";
					

		ResultSet rs = null;
		System.out.println("连续超标增加9月份之后"+sql.toString());
			db.connectDb();
			rs = db.queryAll(sql);
	        while(rs.next()){
	        	cbJzBean c = new cbJzBean();
	        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
	        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
	        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
	        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
	        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
	        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
	        	c.setJsrjhdl(null==rs.getString(7)?"":rs.getString(7));
	        	c.setJssdb(null==rs.getString(8)?"":rs.getString(8));
	        	c.setJscbbl(null==rs.getString(9)?"":rs.getString(9));
	        	c.setGlrjhdl(null==rs.getString(10)?"":rs.getString(10));
	        	c.setGlsdb(null==rs.getString(11)?"":rs.getString(11));
	        	c.setGlcbbl(null==rs.getString(12)?"":rs.getString(12));
	        	list.add(c);
	        }
			
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}
		return list;
	}
/**
 * 基础数据真实性--页面权重、调整值查询
 * @return
 */
	public synchronized ArrayList<String> getQztz(String bzmonth) {		
		ArrayList<String> list = new ArrayList<String>();		
		String sql = "";
		String sql1 = "";
		String bz = "0";
		
		DataBase db = new DataBase();
		try {					
		sql = "SELECT Q.BZMONTH,Q.SXCWQZ,Q.FTQZ,Q.CBGKQZ,Q.CBZDQZ," +
				"Q.JSDLCBQZ,Q.LXCBQZ,Q.LXDLZJQZ,Q.PLDTZ,Q.CBBLTZ,Q.CBJZXGTZ " +
				"FROM QZTZ Q WHERE Q.BZMONTH = (SELECT MAX(QT.BZMONTH) FROM QZTZ QT)";
		sql1 = "SELECT Q.BZMONTH,Q.SXCWQZ,Q.FTQZ,Q.CBGKQZ,Q.CBZDQZ," +
				"Q.JSDLCBQZ,Q.LXCBQZ,Q.LXDLZJQZ,Q.PLDTZ,Q.CBBLTZ,Q.CBJZXGTZ " +
				"FROM QZTZ Q WHERE Q.BZMONTH = '"+bzmonth+"'";
		ResultSet rs = null;
			db.connectDb();
			if(bzmonth==""||bzmonth==null||"".equals(bzmonth)){
				rs = db.queryAll(sql);
				bz = "0";
				System.out.println("基础数据真实性--页面权重、调整值查询："+sql.toString());
			}else{
				rs = db.queryAll(sql1);
				bz = "1";
				System.out.println("基础数据真实性--页面权重、调整值查询："+sql1.toString());				
			}
			
	        while(rs.next()){
	        	list.add(null==rs.getString("BZMONTH")?"":rs.getString("BZMONTH"));
	        	list.add(null==rs.getString("SXCWQZ")?"":rs.getString("SXCWQZ"));
	        	list.add(null==rs.getString("FTQZ")?"":rs.getString("FTQZ"));
	        	list.add(null==rs.getString("CBGKQZ")?"":rs.getString("CBGKQZ"));
	        	list.add(null==rs.getString("CBZDQZ")?"":rs.getString("CBZDQZ"));
	        	list.add(null==rs.getString("JSDLCBQZ")?"":rs.getString("JSDLCBQZ"));
	        	list.add(null==rs.getString("LXCBQZ")?"":rs.getString("LXCBQZ"));
	        	list.add(null==rs.getString("LXDLZJQZ")?"":rs.getString("LXDLZJQZ"));
	        	list.add(null==rs.getString("PLDTZ")?"":rs.getString("PLDTZ"));
	        	list.add(null==rs.getString("CBBLTZ")?"":rs.getString("CBBLTZ"));
	        	list.add(null==rs.getString("CBJZXGTZ")?"":rs.getString("CBJZXGTZ"));
	        	if(bz=="0"||"0".equals(bz)){
	        		list.add("0");
	        	}else if(bz=="1"||"1".equals(bz)){
	        		list.add("1");
	        	}
	        }
			
		}	
		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}	
		finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
	
		}
		return list;
	}
	/**
	 * 基础数据真实性--页面权重、调整值查询上一个月超表比例
	 * @return
	 */
		public synchronized ArrayList<String> getQztzsyc(String bzmonth) {		
			ArrayList<String> list = new ArrayList<String>();		
			String sql = "";
			
			String bzmonthsyc = "";
			if(bzmonth!=null||bzmonth!=""){
				String year = bzmonth.substring(0,4);
				String month = bzmonth.substring(5,7);
				if(Integer.parseInt(month)==1){
					bzmonthsyc = String.valueOf(Integer.parseInt(year)-1)+"-12";
				}else if(Integer.parseInt(month)<=10){
					bzmonthsyc = year+"-0"+String.valueOf(Integer.parseInt(month)-1);
				}else{
					bzmonthsyc = year+"-"+String.valueOf(Integer.parseInt(month)-1);
				}
			}
			DataBase db = new DataBase();
			try {					
				sql = "SELECT Q.CBBLTZ FROM QZTZ Q WHERE Q.BZMONTH = '"+bzmonthsyc+"'";
			ResultSet rs = null;
				db.connectDb();
				rs = db.queryAll(sql);
				System.out.println("基础数据真实性--页面权重、调整值查询上一个月超表比例："+sql.toString());
				
		        while(rs.next()){
		        	list.add(null==rs.getString("CBBLTZ")?"":rs.getString("CBBLTZ"));
		        }
				
			}	
			catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException de) {
				de.printStackTrace();
			}	
			finally {
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}
		
			}
			return list;
		}
	/**
	 * 基础数据真实性--是否保存过历史记录
	 * @return b : true：有；false:否
	 */
		public synchronized boolean getJcsjzsxHistory(String bzmonth,String property) {	
			boolean b = false ;		
			String sql = "";
			
			DataBase db = new DataBase();
			try {					
				sql = "SELECT * FROM KHXX K WHERE K.BZMONTH='"+bzmonth+"' AND K.PROPERTY='"+property+"'";
				ResultSet rs = null;
				db.connectDb();
				
				rs = db.queryAll(sql);
				System.out.println("基础数据真实性--是否保存过历史记录："+sql.toString());					
		        while(rs.next()){
		        	b = true;
		        }				
			}	
			catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException de) {
				de.printStackTrace();
			}	
			finally {
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}
		
			}
			return b;
		}
/**
 * 基础数据真实性--保存过的历史记录
 * @param bzyf：保存的月份
 * @return list : 详细信息
 */
		public synchronized ArrayList<cbJzBean> getJcsjzsxHistoryData(String bzyf,String loginId,String property) {			
			ArrayList<cbJzBean> list = new ArrayList<cbJzBean>();			
			String sql = "";			
			DataBase db = new DataBase();
			try {	
				sql = "SELECT BZMONTH,SHICODE,SHINAME,SXCW,FT,CBGK,CBZD,JSDLCB,LXCB," +
						"CBDLZJ,ZSXL,CBZDHCWTG,TCSJYZCW,SCDLYW,BZDLH,SJDLH,BZPLD,WAQZG," +
						"ZSX,PLD,CBJZZG,CBDLZZ,XZSGSDLDB,ZF FROM KHXX WHERE BZMONTH='"+bzyf+"' AND PROPERTY='"+property+"' " +
						"AND (SHICODE IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) ORDER BY SHICODE ASC";
				ResultSet rs = null;
				System.out.println("基础数据真实性--保存过的历史记录"+sql.toString());
				db.connectDb();
				rs = db.queryAll(sql);
		        while(rs.next()){
		        	cbJzBean bean = new cbJzBean();
		        	bean.setShicode(null==rs.getString("SHICODE")?"":rs.getString("SHICODE"));
		        	bean.setShiname(null==rs.getString("SHINAME")?"":rs.getString("SHINAME"));
		        	bean.setJzsxcount(null==rs.getString("SXCW")?"":rs.getString("SXCW"));
		        	bean.setScnhcount(null==rs.getString("FT")?"":rs.getString("FT"));
		        	bean.setCbgkcount(null==rs.getString("CBGK")?"":rs.getString("CBGK"));
		        	bean.setCbzdcount(null==rs.getString("CBZD")?"":rs.getString("CBZD"));
		        	bean.setJsdbcount(null==rs.getString("JSDLCB")?"":rs.getString("JSDLCB"));
		        	bean.setLxcbcount(null==rs.getString("LXCB")?"":rs.getString("LXCB"));
		        	bean.setLxzzcbcount(null==rs.getString("CBDLZJ")?"":rs.getString("CBDLZJ"));
		        	bean.setZsxcount(null==rs.getString("ZSXL")?"":rs.getString("ZSXL"));
		        	bean.setBzdlcount(null==rs.getString("BZDLH")?"":rs.getString("BZDLH"));
		        	bean.setSzdlcount(null==rs.getString("SJDLH")?"":rs.getString("SJDLH"));
		        	bean.setBzpldu(null==rs.getString("BZPLD")?"":rs.getString("BZPLD"));
		        	bean.setWaqzgcount(null==rs.getString("WAQZG")?"":rs.getString("WAQZG"));
		        	bean.setZsx(null==rs.getString("ZSX")?"":rs.getString("ZSX"));
		        	bean.setPld(null==rs.getString("PLD")?"":rs.getString("PLD"));
		        	bean.setCbjzzg(null==rs.getString("CBJZZG")?"":rs.getString("CBJZZG"));
		        	bean.setCbdlzz(null==rs.getString("CBDLZZ")?"":rs.getString("CBDLZZ"));
		        	bean.setXzsgsdldb(null==rs.getString("XZSGSDLDB")?"":rs.getString("XZSGSDLDB"));
		        	bean.setZf(null==rs.getString("ZF")?"":rs.getString("ZF"));
		        	list.add(bean);
		        }				
			}

			catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException de) {
				de.printStackTrace();
			}

			finally {
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}

			}
			return list;
		}
//未按期整改
public synchronized ArrayList getWaq(String shi,String xian,String bzyf,String cbbl,String loginId,String property) {
	
	double cbbll = Double.valueOf(cbbl.toString())/100;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM");
	Calendar calendar = Calendar.getInstance();// 日历对象
	String bzyf02 = "";
	Date date1 = null;
	Date date2 = null;
	Date bzdate = null;
	try {
		bzdate = fmt.parse(bzyf);
		
		calendar.setTime(bzdate);  
		calendar.add(Calendar.MONTH,-2);   
        Date d2 = calendar.getTime(); 
        bzyf02 = fmt.format(d2);
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	ArrayList list = new ArrayList();
	
	String sql = "";
	String sql1 = "";
	String sql2 = "";
	DataBase db = new DataBase();
	//0304月份查询明细
	if(bzyf.equals("2013-03")||bzyf.equals("2013-04")){
		String bmonth=bzyf.substring(5,7);
	    if(bmonth.equals("03")){bmonth="month3";}
	    if(bmonth.equals("04")){bmonth="month4";}
	    try {
			sql2 = "select aa.shi,aa.xian,aa.xiaoqu,aa.zdid,aa.jzname,aa.property,aa.yf,aa.dl3,aa."+bmonth+",aa.bbl,aa.cbsj,aa.sjdl,aa.nhbz,aa.bzpld " +
					"from (select dls.*,mdz.cbsj,mdz.sjdl,mdz.nhbz,mdz.bzpld from cbzd mdz,(select dl3.*,round((dl3.dl3-dl3."+bmonth+")/dl3."+bmonth+",4)*100||'%' as bbl," +
					"(case when (to_number(substr(dl3.yf, 6, 2)) - 2) <= 0 then to_number(substr(dl3.yf, 1, 4)) - 1 || '-' ||(to_number(substr(dl3.yf, 6, 2)) + 12 - 2) when (to_number(substr(dl3.yf, 6, 2)) - 2) < 10 " +
					"then substr(dl3.yf, 1, 4) || '-0' || (to_number(substr(dl3.yf, 6, 2)) - 2) when (to_number(substr(dl3.yf, 6, 2)) - 2) >= 10 then substr(dl3.yf, 1, 4) || '-' ||(to_number(substr(dl3.yf, 6, 2)) - 2) end)" +
					" as cbyf from (select dl.shi,dl.xian,dl.xiaoqu,dl.zdid,dl.jzname,dl.property,dl.zp,dl.zs, dl.changjia, dl.g2,dl.g3, '"+bzyf+"' as yf, " +
					"round((case when dl.dl1 is not null then dl.dl1 when dl.dl1 is null and dl.dl2 is not null then dl.dl2 end),4) as dl3," +
					" round( decode(trim(dl.sfgx), '1',z."+bmonth+" + (23 * dl.gxgwsl), '0', z."+bmonth+", '', z."+bmonth+", 0),4) as "+bmonth+" " +
					"from (select d.agname as shi,s.agname as xian,a.agname as xiaoqu, zd.zdid, zd1.jzname,i.name as property, zd.zp,zd.zs,zd.changjia,zd.g2,zd.g3,zd.sfgx,zd.gxgwsl," +
					"sum(zd.jsdl) / count(zd.zdid) as dl1, sum(zd.gldl) / count(zd.zdid) as dl2 from jzxx zd, zhandian zd1 left join d_area_grade d " +
					"on zd1.shi = d.agcode left join d_area_grade s on zd1.xian = s.agcode left join d_area_grade a on zd1.xiaoqu = a.agcode " +
					"left join indexs i on zd1.property = i.code where 1 = 1 AND zd.zdid = to_char(zd1.id) AND zd1.property = '"+property+"' AND zd1.shi='"+shi+"' AND zd1.xian='"+xian+"' " +
					"AND (zd1.xiaoqu IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) AND symonth = '"+bzyf+"' group by d.agname,s.agname," +
					" a.agname,zd.zdid, zd1.jzname,i.name,zd.zp, zd.zs,zd.changjia,zd.g2,zd.g3,zd.sfgx,zd.gxgwsl) dl, zdnhbz z where dl.zp = z.zp" +
					" and dl.zs = z.zs and dl.changjia = z.changjia and dl.g2 = z.g2 and dl.g3 = z.g3) dl3 where 1 = 1 and (dl3.dl3 - dl3."+bmonth+") / dl3."+bmonth+" >= "+cbbll+") dls where mdz.zdid = dls.zdid and mdz.cbsj <= dls.cbyf) aa  order by aa.zdid desc";
						

			ResultSet rs = null;
			System.out.println("未按期整改0304"+sql2.toString());
				db.connectDb();
				rs = db.queryAll(sql2);
		        while(rs.next()){
		        	cbJzBean c = new cbJzBean();
		        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
		        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
		        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
		        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
		        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
		        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
		        	c.setCxyf(null==rs.getString(7)?"":rs.getString(7));
		        	c.setCxrhdl(null==rs.getString(8)?"":rs.getString(8));
		        	c.setCxsdb(null==rs.getString(9)?"":rs.getString(9));
		        	c.setCxbl(null==rs.getString(10)?"":rs.getString(10));
		        	c.setSsyyf(null==rs.getString(11)?"":rs.getString(11));
		        	c.setSsyrhdl(null==rs.getString(12)?"":rs.getString(12));
		        	c.setSsysdb(null==rs.getString(13)?"":rs.getString(13));
		        	c.setSsybl(null==rs.getString(14)?"":rs.getString(14));
		        	list.add(c);
		        }
	    }
		        catch (DbException de) {
		    		de.printStackTrace();
		    	} catch (SQLException de) {
		    		de.printStackTrace();
		    	}

		    	finally {
		    		try {
		    			db.close();
		    		} catch (DbException de) {
		    			de.printStackTrace();
		    		}

		    	}
		    	return list;
		    }
	//5月份查询明细
	if(bzyf.equals("2013-05")){
		try {
			sql1 = "select aa.shi,aa.xian,aa.xiaoqu,aa.zdid,aa.jzname,aa.property,aa.yf,aa.dl3,aa.month3,aa.bbl,aa.cbsj,aa.sjdl,aa.nhbz,aa.ssbl" +
					" from (select dls.*,mdz.cbsj,mdz.sjdl,mdz.nhbz,mdz.bzpld*100||'%' as ssbl from cbzd mdz, " +
					"(select dl3.*, round((dl3-month3)/month3,4)*100||'%' as bbl, (case when (to_number(substr(dl3.yf, 6, 2)) - 2) <= 0" +
					" then to_number(substr(dl3.yf, 1, 4)) - 1 || '-' ||(to_number(substr(dl3.yf, 6, 2)) + 12 - 2)" +
					" when (to_number(substr(dl3.yf, 6, 2)) - 2) < 10 then substr(dl3.yf, 1, 4) || '-0' || (to_number(substr(dl3.yf, 6, 2)) - 2)" +
					" when (to_number(substr(dl3.yf, 6, 2)) - 2) >= 10 then substr(dl3.yf, 1, 4) || '-' || (to_number(substr(dl3.yf, 6, 2)) - 2) end) as cbyf " +
					"from (select dl.shi, dl.xian,dl.xiaoqu,dl.zdid,dl.jzname,dl.property,'2013-05' as yf, round((case when dl.dl1 is not null" +
					" then dl.dl1 when dl.dl1 is null and dl.dl2 is not null then dl.dl2 end),4) as dl3, decode(dl.qsdbdl, '0', '1', dl.qsdbdl) as month3" +
					" from (select d.agname as shi, s.agname as xian, a.agname as xiaoqu, zd1.id as zdid, zd1.jzname, i.name as property," +
					" sum(zd.jsdl) / count(zd.zdid) as dl1, sum(zd.gldl) / count(zd.zdid) as dl2, zd.qsdbdl from jzxx zd, zhandian zd1 " +
					"left join d_area_grade d on zd1.shi = d.agcode left join d_area_grade s on zd1.xian = s.agcode left join d_area_grade a on" +
					" zd1.xiaoqu = a.agcode left join indexs i on zd1.property = i.code where 1 = 1 AND zd.zdid = to_char(zd1.id) AND zd1.shi='"+shi+"'" +
					" AND zd1.xian='"+xian+"' AND zd1.property = '"+property+"' AND (zd1.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))" +
					" AND symonth = '"+bzyf+"' group by d.agname, s.agname, a.agname, zd1.id,zd1.jzname,i.name, zd.qsdbdl) dl) dl3 " +
					"where 1 = 1 and (dl3.dl3 - dl3.month3) / dl3.month3 >= "+cbbll+") dls where mdz.zdid = dls.zdid and mdz.cbsj <= dls.cbyf) aa order by aa.zdid desc";
						

			ResultSet rs = null;
			System.out.println("未按期整改05"+sql1.toString());
				db.connectDb();
				rs = db.queryAll(sql1);
		        while(rs.next()){
		        	cbJzBean c = new cbJzBean();
		        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
		        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
		        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
		        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
		        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
		        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
		        	c.setCxyf(null==rs.getString(7)?"":rs.getString(7));
		        	c.setCxrhdl(null==rs.getString(8)?"":rs.getString(8));
		        	c.setCxsdb(null==rs.getString(9)?"":rs.getString(9));
		        	c.setCxbl(null==rs.getString(10)?"":rs.getString(10));
		        	c.setSsyyf(null==rs.getString(11)?"":rs.getString(11));
		        	c.setSsyrhdl(null==rs.getString(12)?"":rs.getString(12));
		        	c.setSsysdb(null==rs.getString(13)?"":rs.getString(13));
		        	c.setSsybl(null==rs.getString(14)?"":rs.getString(14));
		        	list.add(c);
		        }
				
			}

			catch (DbException de) {
				de.printStackTrace();
			} catch (SQLException de) {
				de.printStackTrace();
			}

			finally {
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}

			}
			return list;
		}	
		
	//6、7、8月份查询
	if(bzyf.equals("2013-06")||bzyf.equals("2013-07")||bzyf.equals("2013-08")){
	try {
		sql = "select b.agname, a.agname,s.agname,zd.id,zd.jzname,i.name, aa.yf,round(aa.dl3,2) dl3,aa.month3,aa.cbbl*100||'%',aa.cbsj,aa.sjdl,aa.nhbz,aa.bzpld *100||'%'" +
		" from zhandian zd left join (select dls.zdid   as zdid,dls.yf as yf,dls.dl3 as dl3, dls.month3 as month3,dls.cbbl as cbbl,mdz.cbsj   as cbsj," +
		" mdz.sjdl   as sjdl,mdz.nhbz   as nhbz, mdz.bzpld  as bzpld from (select c.zdid,c.cbsj as cbsj,c.sjdl as sjdl,c.nhbz as nhbz,c.bzpld as bzpld from cbzd c where c.cbsj <= '"+bzyf02+"') mdz," +
		"(select dl3.* from (select jz.zdid as zdid,'"+bzyf+"' as yf,decode(jz.jsdl,'',jz.gldl,0,jz.gldl,'0',jz.gldl,jz.jsdl )as dl3,jz.qsdbdl as month3,round((jz.jsdl - jz.qsdbdl) / jz.qsdbdl, 4) as cbbl from jzxx jz  where 1 = 1 AND symonth = '"+bzyf+"') dl3) dls" +
		" where mdz.zdid = dls.zdid) aa on zd.id = aa.zdid left join D_AREA_GRADE b on zd.shi = b.agcode left join D_AREA_GRADE a on zd.xian = a.agcode left join D_AREA_GRADE s on zd.xiaoqu = s.agcode left join indexs i on zd.property = i.code " +
		"where zd.shi = '"+shi+"' AND zd.xian='"+xian+"' AND zd.property = '"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) AND decode(aa.month3, '0', aa.dl3, (aa.dl3 - aa.month3) / aa.month3) >= "+cbbll+"";		
				

	ResultSet rs = null;
	System.out.println("未按期整改060708"+sql.toString());
		db.connectDb();
		rs = db.queryAll(sql);
        while(rs.next()){
        	cbJzBean c = new cbJzBean();
        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
        	c.setCxyf(null==rs.getString(7)?"":rs.getString(7));
        	c.setCxrhdl(null==rs.getString(8)?"":rs.getString(8));
        	c.setCxsdb(null==rs.getString(9)?"":rs.getString(9));
        	c.setCxbl(null==rs.getString(10)?"":rs.getString(10));
        	c.setSsyyf(null==rs.getString(11)?"":rs.getString(11));
        	c.setSsyrhdl(null==rs.getString(12)?"":rs.getString(12));
        	c.setSsysdb(null==rs.getString(13)?"":rs.getString(13));
        	c.setSsybl(null==rs.getString(14)?"":rs.getString(14));
        	list.add(c);
        }
		
	}

	catch (DbException de) {
		de.printStackTrace();
	} catch (SQLException de) {
		de.printStackTrace();
	}

	finally {
		try {
			db.close();
		} catch (DbException de) {
			de.printStackTrace();
		}

	}
	return list;
}
	//9月份之后的sql
	try {
		sql = "select b.agname, a.agname,s.agname,zd.id,zd.jzname,i.name, aa.yf,round(aa.dl3,2) dl3,aa.month3,aa.cbbl*100||'%',aa.cbsj,aa.sjdl,aa.nhbz,aa.bzpld *100||'%'" +
				" from zhandian zd left join (select dls.zdid   as zdid,dls.yf as yf,dls.dl3 as dl3, dls.month3 as month3,dls.cbbl as cbbl,mdz.cbsj   as cbsj," +
				" mdz.sjdl   as sjdl,mdz.nhbz   as nhbz, mdz.bzpld  as bzpld from (select c.zdid,c.cbsj as cbsj,c.sjdl as sjdl,c.nhbz as nhbz,c.bzpld as bzpld from cbzd c where c.cbsj <= '"+bzyf02+"') mdz," +
				"(select dl3.* from (select jz.zdid as zdid,'"+bzyf+"' as yf,jz.dbydl as dl3,jz.bzz as month3,round((jz.dbydl - jz.bzz) / jz.bzz, 4) as cbbl from jzxx jz  where jz.bzz>0 and 1 = 1 AND symonth = '"+bzyf+"') dl3) dls" +
				" where mdz.zdid = dls.zdid) aa on zd.id = aa.zdid left join D_AREA_GRADE b on zd.shi = b.agcode left join D_AREA_GRADE a on zd.xian = a.agcode left join D_AREA_GRADE s on zd.xiaoqu = s.agcode left join indexs i on zd.property = i.code " +
				"where zd.shi = '"+shi+"' and zd.xian='"+xian+"' AND zd.property = '"+property+"' AND (zd.xiaoqu IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) AND decode(aa.month3, '0', aa.dl3, (aa.dl3 - aa.month3) / aa.month3) >= "+cbbll+"";		
		ResultSet rs = null;
		System.out.println("未按期整改09之后"+sql.toString());
			db.connectDb();
			rs = db.queryAll(sql);
	        while(rs.next()){
	        	cbJzBean c = new cbJzBean();
	        	c.setShiname(null==rs.getString(1)?"":rs.getString(1));
	        	c.setXian(null==rs.getString(2)?"":rs.getString(2));
	        	c.setXiaoqu(null==rs.getString(3)?"":rs.getString(3));
	        	c.setZdid(null==rs.getString(4)?"":rs.getString(4));
	        	c.setJzname(null==rs.getString(5)?"":rs.getString(5));
	        	c.setProperty(null==rs.getString(6)?"":rs.getString(6));
	        	c.setCxyf(null==rs.getString(7)?"":rs.getString(7));
	        	c.setCxrhdl(null==rs.getString(8)?"":rs.getString(8));
	        	c.setCxsdb(null==rs.getString(9)?"":rs.getString(9));
	        	c.setCxbl(null==rs.getString(10)?"":rs.getString(10));
	        	c.setSsyyf(null==rs.getString(11)?"":rs.getString(11));
	        	c.setSsyrhdl(null==rs.getString(12)?"":rs.getString(12));
	        	c.setSsysdb(null==rs.getString(13)?"":rs.getString(13));
	        	c.setSsybl(null==rs.getString(14)?"":rs.getString(14));
	        	list.add(c);
	        }
			
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}
		return list;
	}
}
