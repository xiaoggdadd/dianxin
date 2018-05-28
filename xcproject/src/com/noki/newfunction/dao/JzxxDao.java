package com.noki.newfunction.dao;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.CTime;

public class JzxxDao extends TimerTask{
	public JzxxDao() {
    }
	   	private String nowtime = "";
	    private String yaerMonth = "";
	    private String nowDay = "";
	    private String nowHour = "";
	    private String preSday = "";//
	    private String maxDate = "";
	    private static final SimpleDateFormat sdfk = new SimpleDateFormat(
	            "yyyy-MM-dd");
	/*	
	  public synchronized void CkeckZd() {
		    DataBase db = new DataBase();
			String sql="create table zz as select zd.id,sum(decode(db.dbqyzt,'0',0,1)) as jj from " +
					"zhandian zd,dianbiao db where zd.id=db.zdid and zd.qyzt='1' and zd.caiji='0' " +
					"and zd.property='zdsx02' group  by zd.id";
			System.out.println("创建表电表开关情况: "+sql.toString());
		    try {
		      db.connectDb();
		      db.update(sql);
		    } catch (DbException de) {
		      try {
		        db.rollback();
		      }catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }finally {
		      try {
		        db.close();
		      }catch (Exception de) {
		        de.printStackTrace();
		      }
		    }
		  } 
	  public synchronized void getJzxx(String bzmonth,String month,String kszlfh1,String jszlfh1,String kszlfh2,String jszlfh2,
				String kszlfh3,String jszlfh3,String kszlfh4,String jszlfh4,String kszlfh5,String jszlfh5,
				String kszlfh6,String jz1,String jz2,String jz3,String jz4,String jz5,String jz6,String jrw1,String jrw2,
				String jrw3,String jrw4,String jrw5,String jrw6,String xzzj1,String xzzj2,String xzzj3,String xzzj4,
				String xzzj5,String xzzj6,String jyjf1,String jyjf2,String jyjf3,String jyjf4,String jyjf5,String jyjf6,
				String qt1,String qt2,String qt3,String qt4,String qt5,String qt6,String idcjf1,String idcjf2,String idcjf3,
				String idcjf4,String idcjf5,String idcjf6,String xs1,String jcxs1,String xs2,String jcxs2,String xs3,
				String jcxs3,String jnxs,String qdxs,String zbxs,String zzxs,String dyxs,String ytxs,String wfxs,String Jnxs,
				String taxs,String whxs,String rzxs,String lwxs,String lyxs,String dzxs,String lcxs,String bzxs,String hzxs) {
		    DataBase db = new DataBase();
		    String sq ="delete jzxx j where j.symonth='"+bzmonth+"' ";
		    String sql ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl) " +
		    		" select '"+bzmonth+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
		    				"bb.qsdbdl*(1+bb."+month+"*bb.zlfhxs+bb.yfxs) as qsdbdl " +
		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <="+jszlfh1+" then "+jz1+" " +
		    						"when aa.zlfh >"+jszlfh1+" and aa.zlfh <= "+jszlfh2+" then "+jz2+" " +
		    						"when aa.zlfh >"+jszlfh2+" and aa.zlfh <="+jszlfh3+" then "+jz3+"" +
		    						" when aa.zlfh >"+jszlfh3+" and aa.zlfh <="+jszlfh4+" then "+jz4+" " +
		    						"when aa.zlfh >"+jszlfh4+" and aa.zlfh <="+jszlfh5+" then "+jz5+" " +
		    						"when aa.zlfh >"+jszlfh2+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,decode(db.scb,0,zd.zlfh*1.52) as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx02') aa) bb,zz " +
		    						" where bb.id=zz.id and zz.jj<>0";
		    String sql1="drop table zz";
		    try {
		      db.connectDb();
		      System.out.println("删除jzxx表中该月份数据"+sq.toString());
		      System.out.println("jzxx表中插入该月数据: "+sql.toString());
		      System.out.println("删除电表开关状态表: "+sql1.toString());
		      db.update(sq);
		      db.update(sql);
		      db.update(sql1);
		    }catch (DbException de) {
		      try {
		        db.rollback();
		      }catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }finally {
		      try {
		        db.close();
		      }catch (Exception de) {
		        de.printStackTrace();
		      }
		    }
	}
	  public synchronized void getJsdl(String bzmonth,String firstday,String lastday,int days) {
		    DataBase db = new DataBase();
		    String sql=" create table jsdbdl as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
		    "(case when substr(am.lastdatetime, 0, 7) = '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) = '"+bzmonth+"' " +
		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
		    " substr(am.lastdatetime, 0, 7) = '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) > '"+bzmonth+"' then " +
		    "(TO_CHAR(CEIL(TO_DATE('"+lastday+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
		    "when substr(am.lastdatetime, 0, 7) < '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) = '"+bzmonth+"' then" +
		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstday+"', 'yyyy-mm-dd'))) + 1) when " +
		    "substr(am.lastdatetime, 0, 7) < '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) > '"+bzmonth+"' then "+days+" else 0 end) as zq1" +
		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
		    " ((substr(am.thisdatetime, 0, 7) >= '"+bzmonth+"' and substr(am.lastdatetime, 0, 7) <= '"+bzmonth+"'))" +
		    " AND zd.xuni = '0' and ef.cityaudit='1'";
		    String sql1=" create table jsdbdl1 as select aa.id,(case when sum(aa.zq1)<>0 then sum(aa.bzmonth1)/sum(aa.zq1) end) as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdl tj) aa group by aa.id ";
		    String sql2="update jxx j set j.jsdl=(select j1.js from js11 j1 where j1.id=j.zdid  ) where j.zdid in (select j1.id from jsdbdl1 j1 where j1.id=j.zdid) and j.symonth='"+bzmonth+"'";
		    String sql3="drop table js1";
		    String sql4="drop table js11";
		    System.out.println("自然月结算电表日均耗电量jsdbdl（市级审核通过）1: "+sql.toString());
		    System.out.println("自然月结算电表日均耗电量jsdbdl1（市级审核通过）2: "+sql1.toString());
		    System.out.println("更新jzxx表中插入数据的结算日均耗电量: "+sql2.toString());
		    System.out.println("删除表jsdbdl: "+sql3.toString());
		    System.out.println("删除jsdbdl1: "+sql4.toString());
		    try {
		      db.connectDb();
		      		db.update(sql);
		      		db.update(sql1);
		      		db.update(sql2);
		      		db.update(sql3);
		        	db.update(sql4);
		    }catch (DbException de) {
		      try {
		        db.rollback();
		      }catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }finally {
		      try {
		        db.close();
		      }catch (Exception de) {
		        de.printStackTrace();
		      }
		    }
	}
	  public synchronized void getJsdl2(String bzmonth,String firstday,String lastday,int days) {
		    DataBase db = new DataBase();
		    String sql=" create table jsdbdl2 as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
		    "(case when substr(am.lastdatetime, 0, 7) = '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) = '"+bzmonth+"' " +
		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
		    " substr(am.lastdatetime, 0, 7) = '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) > '"+bzmonth+"' then " +
		    "(TO_CHAR(CEIL(TO_DATE('"+lastday+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
		    "when substr(am.lastdatetime, 0, 7) < '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) = '"+bzmonth+"' then" +
		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstday+"', 'yyyy-mm-dd'))) + 1) when " +
		    "substr(am.lastdatetime, 0, 7) < '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) > '"+bzmonth+"' then "+days+" else 0 end) as zq1" +
		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
		    " ((substr(am.thisdatetime, 0, 7) >= '"+bzmonth+"' and substr(am.lastdatetime, 0, 7) <= '"+bzmonth+"'))" +
		    " AND zd.xuni = '0' and ef.cityaudit<>'1' and ef.manualauditstatus>='1'";
		    String sql1=" create table jsdbdl21 as select aa.id,(case when sum(aa.zq1)<>0 then sum(aa.bzmonth1)/sum(aa.zq1) end)as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdl2 tj) aa group by aa.id ";
		    String sql2="update jxx j set j.jsdl=0 where j.jsdl is null and j.zdid in (select j1.id from jsdbdl21 j1) and j.symonth='"+bzmonth+"'";
		    String sql3="drop table js2";
		    String sql4="drop table js21";
		    System.out.println("创建jsdbdl2（市级<>1人工>=1）: "+sql.toString());
		    System.out.println("创建jsdbdl21（市级<>1人工>=1）:"+sql1.toString());
		    System.out.println("更新jzxx结算为空的补0:"+sql2.toString());
		    System.out.println("删除jsdbdl2: "+sql3.toString());
		    System.out.println("删除jsdbdl21: "+sql4.toString());
		    try {
		      db.connectDb();
		      		db.update(sql);
		      		db.update(sql1);
		      		db.update(sql2);
		      		db.update(sql3);
		      		db.update(sql4);
		    }catch (DbException de) {
		      try {
		        db.rollback();
		      }catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }finally {
		      try {
		        db.close();
		      }catch (Exception de) {
		        de.printStackTrace();
		      }
		    }
	}
	  public synchronized void getGldl(String bzmonth,String firstday,String lastday,int days) {
		    DataBase db = new DataBase();
		    String sql=" create table gldbdl as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
		    "(case when substr(am.lastdatetime, 0, 7) = '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) = '"+bzmonth+"' " +
		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
		    " substr(am.lastdatetime, 0, 7) = '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) > '"+bzmonth+"' then " +
		    "(TO_CHAR(CEIL(TO_DATE('"+lastday+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
		    "when substr(am.lastdatetime, 0, 7) < '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) = '"+bzmonth+"' then" +
		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstday+"', 'yyyy-mm-dd'))) + 1) when " +
		    "substr(am.lastdatetime, 0, 7) < '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) > '"+bzmonth+"' then "+days+" else 0 end) as zq1" +
		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
		    " ((substr(am.thisdatetime, 0, 7) >= '"+bzmonth+"' and substr(am.lastdatetime, 0, 7) <= '"+bzmonth+"'))" +
		    " AND zd.xuni = '0' and am.manualauditstatus='1' and db.dbyt='dbyt03'";
		    String sql1=" create table gldbdl1 as select aa.id,(case when sum(zq1)<>0 then sum(aa.bzmonth1)/sum(aa.zq1) end)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdl tj) aa group by aa.id ";
		    String sql2="update jzxx j set j.gldl=(select j1.gl from gl11 j1 where j1.id=j.zdid  ) where j.zdid in (select j1.id from gldbdl1 j1 where j1.id=j.zdid ) and j.symonth='"+bzmonth+"'";
		    String sql3="drop table gldbdl";
		    String sql4="drop table gldbdl1";
		    System.out.println("创建gldbdl（人工审核=1）： "+sql.toString());
		    System.out.println("创建gldbdl1（人工审核=1） "+sql1.toString());
		    System.out.println("更新jzxx表管理 "+sql2.toString());
		    System.out.println("删除gldbdl"+sql3.toString());
		    System.out.println("删除gldbdl1"+sql4.toString());
		    try {
		      db.connectDb();
		      		db.update(sql);
		      		db.update(sql1);
		      		db.update(sql2);
		      		db.update(sql3);
		      		db.update(sql4);
		    }catch (DbException de) {
		      try {
		        db.rollback();
		      }catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }finally {
		      try {
		        db.close();
		      }catch (Exception de) {
		        de.printStackTrace();
		      }
		    }
	}
	  public synchronized void getGldl2(String bzmonth,String firstday,String lastday,int days) {
		    DataBase db = new DataBase();
		    String sql=" create table gldbdl2 as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
		    "(case when substr(am.lastdatetime, 0, 7) = '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) = '"+bzmonth+"' " +
		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
		    " substr(am.lastdatetime, 0, 7) = '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) > '"+bzmonth+"' then " +
		    "(TO_CHAR(CEIL(TO_DATE('"+lastday+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
		    "when substr(am.lastdatetime, 0, 7) < '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) = '"+bzmonth+"' then" +
		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstday+"', 'yyyy-mm-dd'))) + 1) when " +
		    "substr(am.lastdatetime, 0, 7) < '"+bzmonth+"' and substr(am.thisdatetime, 0, 7) > '"+bzmonth+"' then "+days+" else 0 end) as zq1" +
		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
		    " ((substr(am.thisdatetime, 0, 7) >= '"+bzmonth+"' and substr(am.lastdatetime, 0, 7) <= '"+bzmonth+"'))" +
		    " AND zd.xuni = '0' and am.manualauditstatus<>'1' and db.dbyt='dbyt03'";
		    String sql1=" create table gldbdl21 as select aa.id,(case when sum(zq1)<>0 then sum(aa.bzmonth1)/sum(aa.zq1) end)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdl2 tj) aa group by aa.id ";
		    String sql2="update jzxx j set j.gldl=0 where j.gldl is null and j.zdid in(select j1.id from gldbdl21 j1) and j.symonth='"+bzmonth+"'";
		    String sql3="drop table gldbdl2";
		    String sql4="drop table gldbdl21";
		    System.out.println("创建表gldbdl2（人工审核<>1）： "+sql.toString());
		    System.out.println("创建表gldbdl21（人工审核<>1）: "+sql1.toString());
		    System.out.println("更新jzxx表管理 为空补0: "+sql2.toString());
		    System.out.println("删除gldbdl2: "+sql3.toString());
		    System.out.println("删除gldbdl21: "+sql4.toString());
		    try {
		      db.connectDb();
		      		db.update(sql);
		      		db.update(sql1);
		      		db.update(sql2);
		      		db.update(sql3);
		      		db.update(sql4);
		    }catch (DbException de) {
		      try {
		        db.rollback();
		      }catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }finally {
		      try {
		        db.close();
		      }catch (Exception de) {
		        de.printStackTrace();
		      }
		    }
	}*/
	 // --------------------------------------------------------------------------
//	public synchronized void getJxx(String yf){
	public void run(){
		    	Calendar cal=Calendar.getInstance();

		        cal.set(Calendar.DATE, 1);     

		        cal.roll(Calendar.DATE, -1);   

		        DecimalFormat mat = new DecimalFormat("0.00");
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				
				CTime ctime = new CTime(); 
	            String thismonth = ctime.formatWholeDate(date);
	            String tm = thismonth.substring(0,7);
	         	Calendar calendar1 = Calendar.getInstance();
	         	System.out.println(thismonth+"-----------"+tm);
				calendar1.setTime(date);
				calendar1.add(Calendar.MONTH, -1);
				String yuefen =ctime.formatWholeDate(calendar1.getTime());
				String yf = yuefen.substring(0,7);
				String sf=yf.substring(5,7);
				
				calendar1.set(Calendar.DAY_OF_MONTH,1);
				String firstDay = format.format(calendar1.getTime());
				Calendar cale = Calendar.getInstance();
				cale.setTime(date);
		        cale.set(Calendar.DAY_OF_MONTH, 0);
				String lastDay = format.format(cale.getTime());
		        int fd=Integer.parseInt(firstDay.substring(8, 10));
		        int ld=Integer.parseInt(lastDay.substring(8, 10));
		        int days=ld-fd+1;
				System.out.println("date:"+date+"yf:------"+yf+"sf:---"+sf+"yuefen:--"+"firstDay:--"+firstDay+"lastDay:-"+lastDay+"days"+days);
		        
		        int max=cal.get(Calendar.DATE);
		        maxDate=String.valueOf(max);
		        
		        nowtime = CTime.formatDate();
		        yaerMonth =CTime.formatWholeDate().toString().substring(0, 10);
		        nowDay = nowtime.substring(6, 8);
		        nowHour = nowtime.substring(8, 10);
		        String s=yaerMonth.substring(5,7);
		        String ss=yaerMonth.substring(8,10);
		        System.out.println("MonthSum_timer run== "+CTime.formatWholeDate()+"  nowDay="+nowDay+"  maxDate"+maxDate+yaerMonth);
		       
		        String fwid = "", fwlx = "", xs = "", jcxs = "", fwbzw = "";
				String fwid1 = "", fwlx1 = "", xs1 = "", jcxs1 = "", fwbzw1 = "";
				String fwid2 = "", fwlx2 = "", xs2 = "", jcxs2 = "", fwbzw2 = "";
				String fwid3 = "", fwlx3 = "", xs3 = "", jcxs3 = "", fwbzw3 = "";
				
				String kszlfh = "", jszlfh = "", jz = "", jrw = "", xzzj = "", jyjf = "", qt = "", idcjf = "", id1 = "", zlfh = "";
				String kszlfh1 = "", jszlfh1 = "", jz1 = "", jrw1 = "", xzzj1 = "", jyjf1 = "", qt1 = "", idcjf1 = "", id11 = "", zlfh1 = "";
				String kszlfh2 = "", jszlfh2 = "", jz2 = "", jrw2 = "", xzzj2 = "", jyjf2 = "", qt2 = "", idcjf2 = "", id12 = "", zlfh2 = "";
				String kszlfh3 = "", jszlfh3 = "", jz3 = "", jrw3 = "", xzzj3 = "", jyjf3 = "", qt3 = "", idcjf3 = "", id13 = "", zlfh3 = "";
				String kszlfh4 = "", jszlfh4 = "", jz4 = "", jrw4 = "", xzzj4 = "", jyjf4 = "", qt4 = "", idcjf4 = "", id14 = "", zlfh4 = "";
				String kszlfh5 = "", jszlfh5 = "", jz5 = "", jrw5 = "", xzzj5 = "", jyjf5 = "", qt5 = "", idcjf5 = "", id15 = "", zlfh5 = "";
				String kszlfh6 = "", jszlfh6 = "", jz6 = "", jrw6 = "", xzzj6 = "", jyjf6 = "", qt6 = "", idcjf6 = "", id16 = "", zlfh6 = "";
				
				String month = "";
				if (sf.equals("01")) {
					month = "ymonth";
				} else if (sf.equals("02")) {
					month = "emonth";
				} else if (sf.equals("03")) {
					month = "smonth";
				} else if (sf.equals("04")) {
					month = "wmonth";
				} else if (sf.equals("06")) {
					month = "lmonth";
				} else if (sf.equals("07")) {
					month = "qmonth";
				} else if (sf.equals("08")) {
					month = "bmonth";
				} else if (sf.equals("09")) {
					month = "jmonth";
				} else if (sf.equals("10")) {
					month = "shimonth";
				} else if (sf.equals("11")) {
					month = "symonth";
				} else if (sf.equals("12")) {
					month = "semonth";
				}    
				
				
		            Calendar calendar = Calendar.getInstance();
		            calendar.add(calendar.DATE, -1);
		            Date perday = calendar.getTime();
		            preSday = sdfk.format(perday);
		            DataBase oracledb = new DataBase();
		          System.out.println(nowHour+"-::::---------------------------------");
		          int i=0;
		            try {
		            	
		            	System.out.println("房屋系数"+"SELECT F.ID,F.YFLXCODE,F.FXXS,F.JCXS,F.SJBZW,F.YFNAME FROM FWXS F");
		                ResultSet rs = oracledb.queryAll("SELECT F.ID,F.YFLXCODE,F.FXXS,F.JCXS,F.SJBZW,F.YFNAME FROM FWXS F");
		                while(rs.next()){
		                	fwid=rs.getString(1);
		                	fwlx=rs.getString(2);
		                	xs=rs.getString(3);
		                	jcxs=rs.getString(4);
		                	fwbzw=rs.getString(5);
		                	xs= mat.format(Double.parseDouble(xs));
		                	jcxs = mat.format(Double.parseDouble(jcxs));
							if (fwbzw.equals("1")) {
								xs1 = xs;
								jcxs1 = jcxs;
							} else if (fwbzw.equals("2")) {
								xs2 = xs;
								jcxs2 = jcxs;
							} else if (fwbzw.equals("3")) {
								xs3 = xs;
								jcxs3 = jcxs;
							}	
		                }
		                System.out.println("空调系数"+"SELECT K.ID,K.KSZLFH,K.JSZLSH,K.JZKTXS,K.JRWKTXS,K.XZZJKTXS,K.JYJFKTXS,K.QTKTXS,K.IDCJFKTXS,K.SJBZW FROM KTXS K ");
		                ResultSet rs1 = oracledb.queryAll("SELECT K.ID,K.KSZLFH,K.JSZLSH,K.JZKTXS,K.JRWKTXS,K.XZZJKTXS,K.JYJFKTXS,K.QTKTXS,K.IDCJFKTXS,K.SJBZW FROM KTXS K ");
		                while(rs1.next()){
		                id1 = rs1.getString(1);
						kszlfh = rs1.getString(2);
						jszlfh = rs1.getString(3);
						jz = rs1.getString(4);
						jrw = rs1.getString(5);
						xzzj = rs1.getString(6);
						jyjf = rs1.getString(7);
						qt = rs1.getString(8);
						idcjf = rs1.getString(9);
						jz = mat.format(Double.parseDouble(jz));
						jrw = mat.format(Double.parseDouble(jrw));
						xzzj = mat.format(Double.parseDouble(xzzj));
						jyjf = mat.format(Double.parseDouble(jyjf));
						qt = mat.format(Double.parseDouble(qt));
						idcjf = mat.format(Double.parseDouble(idcjf));
						if (id1.equals("1")) {
							kszlfh1 = kszlfh;
							jszlfh1 = jszlfh;
							jz1 = jz;
							jrw1 = jrw;
							xzzj1 = xzzj;
							jyjf1 = jyjf;
							qt1 = qt;
							idcjf1 = idcjf;
						} else if (id1.equals("2")) {
							kszlfh2 = kszlfh;
							jszlfh2 = jszlfh;
							jz2 = jz;
							jrw2 = jrw;
							xzzj2 = xzzj;
							jyjf2 = jyjf;
							qt2 = qt;
							idcjf2 = idcjf;
						} else if (id1.equals("3")) {
							kszlfh3 = kszlfh;
							jszlfh3 = jszlfh;
							jz3 = jz;
							jrw3 = jrw;
							xzzj3 = xzzj;
							jyjf3 = jyjf;
							qt3 = qt;
							idcjf3 = idcjf;
						} else if (id1.equals("4")) {
							kszlfh4 = kszlfh;
							jszlfh4 = jszlfh;
							jz4 = jz;
							jrw4 = jrw;
							xzzj4 = xzzj;
							jyjf4 = jyjf;
							qt4 = qt;
							idcjf4 = idcjf;
						} else if (id1.equals("5")) {
							kszlfh5 = kszlfh;
							jszlfh5 = jszlfh;
							jz5 = jz;
							jrw5 = jrw;
							xzzj5 = xzzj;
							jyjf5 = jyjf;
							qt5 = qt;
							idcjf5 = idcjf;
						} else if (id1.equals("6")) {
							kszlfh6 = kszlfh;
							jz6 = jz;
							jrw6 = jrw;
							xzzj6 = xzzj;
							jyjf6 = jyjf;
							qt6 = qt;
							idcjf6 = idcjf;
						}
		                }
		             if (((Integer.parseInt(nowHour)==0))){//zdsx02 基站为0点更新
		            	String sq="select * from khxx h where h.bzmonth='"+yf+"' and h.property='zdsx02'";
		                System.out.println("当前月有没有保存"+sq);
		            	ResultSet r=oracledb.queryAll(sq);
		            	while(r.next()){
		    				i=1;
		    			}
		            if(i==0){	
				        //oracledb.setAutoCommit(false);
		                String sql="create  table  zhandianzt as select zd.id,sum(decode(db.dbqyzt,'0',0,1)) as jj,zd.shsign as zdshenhe from " +
						"zhandian zd,dianbiao db where zd.id=db.zdid and zd.qyzt='1' and zd.caiji='0' " +
						"and zd.property='zdsx02' group  by zd.id,zd.shsign";
		                
		                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx02'";
		    		    String sql3 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
		    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
		    		    				"bb.qsdbdl*(1+bb."+month+"*bb.zlfhxs+bb.yfxs) as qsdbdl,'','zdsx02' " +
		    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
		    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <="+jszlfh1+" then "+jz1+" " +
		    		    						"when aa.zlfh >"+jszlfh1+" and aa.zlfh <= "+jszlfh2+" then "+jz2+" " +
		    		    						"when aa.zlfh >"+jszlfh2+" and aa.zlfh <="+jszlfh3+" then "+jz3+"" +
		    		    						" when aa.zlfh >"+jszlfh3+" and aa.zlfh <="+jszlfh4+" then "+jz4+" " +
		    		    						"when aa.zlfh >"+jszlfh4+" and aa.zlfh <="+jszlfh5+" then "+jz5+" " +
		    		    						"when aa.zlfh >"+jszlfh2+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
		    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
		    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
		    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
		    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
		    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
		    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx02') aa) bb,zhandianzt zz " +
		    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
		    		    String sql31 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
    		    				"bb.qsdbdl*(1+bb."+month+"*bb.zlfhxs+bb.yfxs) as qsdbdl,'','zdsx02' " +
    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <"+kszlfh2+" then "+jz1+" " +
    		    						"when aa.zlfh >="+kszlfh2+" and aa.zlfh < "+kszlfh3+" then "+jz2+" " +
    		    						"when aa.zlfh >="+kszlfh3+" and aa.zlfh < "+kszlfh4+" then "+jz3+"" +
    		    						" when aa.zlfh >="+kszlfh4+" and aa.zlfh < "+kszlfh5+" then "+jz4+" " +
    		    						"when aa.zlfh >="+kszlfh5+" and aa.zlfh <"+kszlfh6+" then "+jz5+" " +
    		    						"when aa.zlfh >="+kszlfh6+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx02') aa) bb,zhandianzt zz " +
    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
		    		    String sql4="drop table zhandianzt";
		  		     
		    		    String sql5=" create table jsdbdl as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
		    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
		    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
		    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
		    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
		    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
		    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
		    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
		    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
		    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
		    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
		    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
		    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
		    		    " AND zd.xuni = '0' and ef.cityaudit='1' and zd.property='zdsx02'";
		    		    String sql6=" create table jsdbdl1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1) as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdl tj) aa group by aa.id ";

		    		    String sql7="update jzxx j set j.jsdl=(select j1.js from jsdbdl1 j1 where to_char(j1.id)=j.zdid  ),j.bzw='1' where j.zdid in (select to_char(j1.id) from jsdbdl1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx02'";

		    		    //String sql7="update jzxx j set j.jsdl=(select j1.js from jsdbdl1 j1 where to_char(j1.id)=j.zdid  ),j.bzw='1' where j.zdid in (select to_char(j1.id) from jsdbdl1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"'";

		    		    String sql8="drop table jsdbdl";
		    		    String sql9="drop table jsdbdl1";
		    		    
		    		    String sql10=" create table jsdbdl2 as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
		    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
		    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
		    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
		    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
		    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
		    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
		    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
		    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
		    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
		    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
		    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
		    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
		    		    " AND zd.xuni = '0' and ef.cityaudit<>'1' and ef.manualauditstatus>='1' and zd.property='zdsx02'";
		    		    String sql11=" create table jsdbdl21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdl2 tj) aa group by aa.id ";

		    		    String sql12="update jzxx j set j.jsdl=0,j.bzw='2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdl21 j1) and j.symonth='"+yf+"' and j.property='zdsx02'";

		    		    //String sql12="update jzxx j set j.jsdl=0,j.bzw='2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdl21 j1) and j.symonth='"+yf+"'";

		    		    String sql13="drop table jsdbdl2";
		    		    String sql14="drop table jsdbdl21";
		    		    
		    		    String sql15=" create table gldbdl as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
		    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
		    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
		    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
		    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
		    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
		    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
		    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
		    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
		    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
		    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
		    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
		    		    " AND zd.xuni = '0' and am.manualauditstatus='1' and db.dbyt='dbyt03' and zd.property='zdsx02'";
		    	        String sql16=" create table gldbdl1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdl tj) aa group by aa.id ";
		    		    String sql17="update jzxx j set j.gldl=(select j1.gl from gldbdl1 j1 where to_char(j1.id)=j.zdid) where j.zdid in (select to_char(j1.id) from gldbdl1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"'and j.property='zdsx02'";
		    		    String sql18="drop table gldbdl";
		    		    String sql19="drop table gldbdl1";
		    		    
		    		    String sql20=" create table gldbdl2 as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
		    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
		    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
		    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
		    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
		    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
		    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
		    		   " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
		    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
		    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
		    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
		    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
		    		    " AND zd.xuni = '0' and am.manualauditstatus<>'1' and db.dbyt='dbyt03' and zd.property='zdsx02'";
		    		    String sql21=" create table gldbdl21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdl2 tj) aa group by aa.id ";
		    		    String sql22="update jzxx j set j.gldl=0 where j.gldl is null and j.zdid in(select to_char(j1.id) from gldbdl21 j1) and j.symonth='"+yf+"' and j.property='zdsx02'";
		    		    String sql23="drop table gldbdl2";
		    		    String sql24="drop table gldbdl21";
		    		    
		    		    System.out.println("创建电表状态表zhandianzt: "+sql.toString());
		    		    oracledb.update(sql);
		    		    System.out.println("删除jzxx表中当前月份数据"+sql2.toString());
		  		      	System.out.println("删除zhandianzt: "+sql4.toString());
		    		    oracledb.update(sql2);
		    		    if(sf.equals("09")||sf.equals("10")||sf.equals("11")||sf.equals("12")){//9-12宽松
		    		    	System.out.println("插入jzxx表当前月(9-12月): "+sql3.toString());
		    		    	oracledb.update(sql3);
		    		    }else{//1-8严格
		    		    	System.out.println("插入jzxx表当前月（1-8月）: "+sql31.toString());
		    		    	oracledb.update(sql31);
		    		    }
		    		    oracledb.update(sql4);
		    		    System.out.println("创建结算日均耗电量（市级审核）jsdbdl: "+sql5.toString());
		    		    System.out.println("创建结算日均耗电量jsdbdl1: "+sql6.toString());
		    		    System.out.println("更新jzxx表结算日均耗电量: "+sql7.toString());
		    		    System.out.println("删除jsdbdl: "+sql8.toString());
		    		    System.out.println("删除jsdbdl1: "+sql9.toString());
		    		    oracledb.update(sql5);
		    		    oracledb.update(sql6);
		    		    oracledb.update(sql7);
		    		    oracledb.update(sql8);
		    		    oracledb.update(sql9);
		    		    System.out.println("创建结算日均耗电量（人工>=1 市级<>1）jsdbdl2: "+sql10.toString());
		    		    System.out.println("创建结算日均耗电量（人工>=1 市级<>1）jsdbdl21:"+sql11.toString());
		    		    System.out.println("更新jzxx表结算为空的补0"+sql12.toString());
		    		    System.out.println("删除jsdbdl2: "+sql13.toString());
		    		    System.out.println("删除jsdbdl21: "+sql14.toString());
		    		    oracledb.update(sql10);
		    		    oracledb.update(sql11);
		    		    oracledb.update(sql12);
		    		    oracledb.update(sql13);
		    		    oracledb.update(sql14);
		    		    System.out.println("创建管理日均耗电量（人工审核=1）gldbdl "+sql15.toString());
		    		    System.out.println("创建管理日均耗电量（人工审核=1）gldbdl1 "+sql16.toString());
		    		    System.out.println("更新jzxx管理日均耗电量"+sql17.toString());
		    		    System.out.println("删除gldbdl"+sql18.toString());
		    		    System.out.println("删除gldbdl1"+sql19.toString());
		    		    oracledb.update(sql15);
		    		    oracledb.update(sql16);
		    		    oracledb.update(sql17);
		    		    oracledb.update(sql18);
		    		    oracledb.update(sql19);
		    		    System.out.println("创建管理日均耗电量（人工审核<>1）gldbdl2"+sql20.toString());
		    		    System.out.println("创建管理日均耗电量（人工审核<>1）gldbdl21: "+sql21.toString());
		    		    System.out.println("更新jzxx管理为空的补0: "+sql22.toString());
		    		    System.out.println("删除gldbdl2: "+sql23.toString());
		    		    System.out.println("删除gldbdl21: "+sql24.toString());
		    		    oracledb.update(sql20);
		    		    oracledb.update(sql21);
		    		    oracledb.update(sql22);
		    		    oracledb.update(sql23);
		    		    oracledb.update(sql24);
		    		    System.out.println("-----------------------基站更新完毕-------------------------------------");
		    		    //oracledb.commit();
		            	}
		             }
		             if (((Integer.parseInt(nowHour)==1))){//zdsx01 局用机房更新 1点
			            	String sq="select * from khxx h where h.bzmonth='"+yf+"' and h.property='zdsx01'";
			                System.out.println("当前月有没有保存"+sq);
			            	ResultSet r=oracledb.queryAll(sq);
			            	while(r.next()){
			    				i=1;
			    			}
			            if(i==0){	
					        //oracledb.setAutoCommit(false);
			                String sql="create  table  zhandianjy as select zd.id,sum(decode(db.dbqyzt,'0',0,1)) as jj,zd.shsign as zdshenhe from " +
							"zhandian zd,dianbiao db where zd.id=db.zdid and zd.qyzt='1' and zd.caiji='0' " +
							"and zd.property='zdsx01' group  by zd.id,zd.shsign";
			                
			                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx01'";
			    		    String sql3 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
			    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
			    		    				"bb.qsdbdl*(1+bb."+month+"*bb.zlfhxs) as qsdbdl,'','zdsx01' " +
			    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
			    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <="+jszlfh1+" then "+jz1+" " +
			    		    						"when aa.zlfh >"+jszlfh1+" and aa.zlfh <= "+jszlfh2+" then "+jz2+" " +
			    		    						"when aa.zlfh >"+jszlfh2+" and aa.zlfh <="+jszlfh3+" then "+jz3+"" +
			    		    						" when aa.zlfh >"+jszlfh3+" and aa.zlfh <="+jszlfh4+" then "+jz4+" " +
			    		    						"when aa.zlfh >"+jszlfh4+" and aa.zlfh <="+jszlfh5+" then "+jz5+" " +
			    		    						"when aa.zlfh >"+jszlfh2+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
			    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
			    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
			    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
			    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
			    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
			    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx01') aa) bb,zhandianjy zz " +
			    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
			    		    String sql31 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
	    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
	    		    				"bb.qsdbdl*(1+bb."+month+"*bb.zlfhxs) as qsdbdl,'','zdsx01' " +
	    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
	    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <"+kszlfh2+" then "+jz1+" " +
	    		    						"when aa.zlfh >="+kszlfh2+" and aa.zlfh < "+kszlfh3+" then "+jz2+" " +
	    		    						"when aa.zlfh >="+kszlfh3+" and aa.zlfh < "+kszlfh4+" then "+jz3+"" +
	    		    						" when aa.zlfh >="+kszlfh4+" and aa.zlfh < "+kszlfh5+" then "+jz4+" " +
	    		    						"when aa.zlfh >="+kszlfh5+" and aa.zlfh <"+kszlfh6+" then "+jz5+" " +
	    		    						"when aa.zlfh >="+kszlfh6+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
	    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
	    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
	    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
	    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
	    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
	    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx01') aa) bb,zhandianjy zz " +
	    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
			    		    String sql4="drop table zhandianjy";
			  		     
			    		    String sql5=" create table jsdbdljy as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and ef.cityaudit='1' and zd.property='zdsx01'";
			    		    String sql6=" create table jsdbdljy1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1) as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdljy tj) aa group by aa.id ";
			    		    String sql7="update jzxx j set j.jsdl=(select j1.js from jsdbdljy1 j1 where to_char(j1.id)=j.zdid  ),j.bzw='1' where j.zdid in (select to_char(j1.id) from jsdbdljy1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx01'";
			    		    String sql8="drop table jsdbdljy";
			    		    String sql9="drop table jsdbdljy1";
			    		    
			    		    String sql10=" create table jsdbdljy2 as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and ef.cityaudit<>'1' and ef.manualauditstatus>='1' and zd.property='zdsx01'";
			    		    String sql11=" create table jsdbdljy21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdljy2 tj) aa group by aa.id ";
			    		    String sql12="update jzxx j set j.jsdl=0,j.bzw='2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdljy21 j1) and j.symonth='"+yf+"' and j.property='zdsx01'";
			    		    String sql13="drop table jsdbdljy2";
			    		    String sql14="drop table jsdbdljy21";
			    		    
			    		    String sql15=" create table gldbdljy as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and am.manualauditstatus='1' and db.dbyt='dbyt03' and zd.property='zdsx01'";
			    	        String sql16=" create table gldbdljy1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdljy tj) aa group by aa.id ";
			    		    String sql17="update jzxx j set j.gldl=(select j1.gl from gldbdljy1 j1 where to_char(j1.id)=j.zdid) where j.zdid in (select to_char(j1.id) from gldbdljy1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx01'";
			    		    String sql18="drop table gldbdljy";
			    		    String sql19="drop table gldbdljy1";
			    		    
			    		    String sql20=" create table gldbdljy2 as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		   " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and am.manualauditstatus<>'1' and db.dbyt='dbyt03' and zd.property='zdsx01'";
			    		    String sql21=" create table gldbdljy21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdljy2 tj) aa group by aa.id ";
			    		    String sql22="update jzxx j set j.gldl=0 where j.gldl is null and j.zdid in(select to_char(j1.id) from gldbdljy21 j1) and j.symonth='"+yf+"' and j.property='zdsx01'";
			    		    String sql23="drop table gldbdljy2";
			    		    String sql24="drop table gldbdljy21";
			    		    
			    		    System.out.println("创建局用机房电表状态表zhandianjy: "+sql.toString());
			    		    oracledb.update(sql);
			    		    System.out.println("删除jzxx表中当前月份数据"+sql2.toString());
			  		      	
			    		    oracledb.update(sql2);
			    		    if(sf.equals("09")||sf.equals("10")||sf.equals("11")||sf.equals("12")){//9-12宽松
			    		    	System.out.println("插入jzxx表当前月(9-12月): "+sql3.toString());
			    		    	oracledb.update(sql3);
			    		    }else{//1-8严格
			    		    	System.out.println("插入jzxx表当前月（1-8月）: "+sql31.toString());
			    		    	oracledb.update(sql31);
			    		    }
			    		    System.out.println("删除局用机房zhandianzt: "+sql4.toString());
			    		    oracledb.update(sql4);
			    		    System.out.println("局用机房-创建结算日均耗电量（市级审核）jsdbdljy: "+sql5.toString());
			    		    System.out.println("局用机房-创建结算日均耗电量jsdbdljy1: "+sql6.toString());
			    		    System.out.println("局用机房-更新jzxx表结算日均耗电量: "+sql7.toString());
			    		    System.out.println("局用机房-删除jsdbdljy: "+sql8.toString());
			    		    System.out.println("局用机房-删除jsdbdljy1: "+sql9.toString());
			    		    oracledb.update(sql5);
			    		    oracledb.update(sql6);
			    		    oracledb.update(sql7);
			    		    oracledb.update(sql8);
			    		    oracledb.update(sql9);
			    		    System.out.println("局用机房-创建结算日均耗电量（人工>=1 市级<>1）jsdbdljy2: "+sql10.toString());
			    		    System.out.println("局用机房-创建结算日均耗电量（人工>=1 市级<>1）jsdbdljy21:"+sql11.toString());
			    		    System.out.println("局用机房-更新jzxx表结算为空的补0"+sql12.toString());
			    		    System.out.println("局用机房-删除jsdbdljy2: "+sql13.toString());
			    		    System.out.println("局用机房-删除jsdbdljy21: "+sql14.toString());
			    		    oracledb.update(sql10);
			    		    oracledb.update(sql11);
			    		    oracledb.update(sql12);
			    		    oracledb.update(sql13);
			    		    oracledb.update(sql14);
			    		    System.out.println("局用机房-创建管理日均耗电量（人工审核=1）gldbdljy "+sql15.toString());
			    		    System.out.println("局用机房-创建管理日均耗电量（人工审核=1）gldbdljy1 "+sql16.toString());
			    		    System.out.println("局用机房-更新jzxx管理日均耗电量"+sql17.toString());
			    		    System.out.println("局用机房-删除gldbdljy"+sql18.toString());
			    		    System.out.println("局用机房-删除gldbdljy1"+sql19.toString());
			    		    oracledb.update(sql15);
			    		    oracledb.update(sql16);
			    		    oracledb.update(sql17);
			    		    oracledb.update(sql18);
			    		    oracledb.update(sql19);
			    		    System.out.println("局用机房-创建管理日均耗电量（人工审核<>1）gldbdljy2"+sql20.toString());
			    		    System.out.println("局用机房-创建管理日均耗电量（人工审核<>1）gldbdljy21: "+sql21.toString());
			    		    System.out.println("局用机房-更新jzxx管理为空的补0: "+sql22.toString());
			    		    System.out.println("局用机房-删除gldbdljy2: "+sql23.toString());
			    		    System.out.println("局用机房-删除gldbdljy21: "+sql24.toString());
			    		    oracledb.update(sql20);
			    		    oracledb.update(sql21);
			    		    oracledb.update(sql22);
			    		    oracledb.update(sql23);
			    		    oracledb.update(sql24);
			    		    System.out.println("-------------------------局用机房更新完毕-----------------------------------");
			    		    //oracledb.commit();
			            	}
			             }
		             if (((Integer.parseInt(nowHour)==2))){//zdsx03营业网点更新 2点
			            	String sq="select * from khxx h where h.bzmonth='"+yf+"' and h.property='zdsx03'";
			                System.out.println("当前月有没有保存"+sq);
			            	ResultSet r=oracledb.queryAll(sq);
			            	while(r.next()){
			    				i=1;
			    			}
			            if(i==0){	
					        //oracledb.setAutoCommit(false);
			                String sql="create  table  zhandianyy as select zd.id,sum(decode(db.dbqyzt,'0',0,1)) as jj,zd.shsign as zdshenhe from " +
							"zhandian zd,dianbiao db where zd.id=db.zdid and zd.qyzt='1' and zd.caiji='0' " +
							"and zd.property='zdsx03' group  by zd.id,zd.shsign";
			                
			                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx03'";
			    		    String sql3 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
			    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
			    		    				"bb.qsdbdl*1+(bb."+month+"*bb.zlfhxs) as qsdbdl,'','zdsx03' " +
			    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
			    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <="+jszlfh1+" then "+jz1+" " +
			    		    						"when aa.zlfh >"+jszlfh1+" and aa.zlfh <= "+jszlfh2+" then "+jz2+" " +
			    		    						"when aa.zlfh >"+jszlfh2+" and aa.zlfh <="+jszlfh3+" then "+jz3+"" +
			    		    						" when aa.zlfh >"+jszlfh3+" and aa.zlfh <="+jszlfh4+" then "+jz4+" " +
			    		    						"when aa.zlfh >"+jszlfh4+" and aa.zlfh <="+jszlfh5+" then "+jz5+" " +
			    		    						"when aa.zlfh >"+jszlfh2+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
			    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
			    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
			    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
			    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
			    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
			    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx03') aa) bb,zhandianyy zz " +
			    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
			    		    String sql31 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
	    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
	    		    				"bb.qsdbdl*1+(bb."+month+"*bb.zlfhxs) as qsdbdl,'','zdsx03' " +
	    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
	    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <"+kszlfh2+" then "+jz1+" " +
	    		    						"when aa.zlfh >="+kszlfh2+" and aa.zlfh < "+kszlfh3+" then "+jz2+" " +
	    		    						"when aa.zlfh >="+kszlfh3+" and aa.zlfh < "+kszlfh4+" then "+jz3+"" +
	    		    						" when aa.zlfh >="+kszlfh4+" and aa.zlfh < "+kszlfh5+" then "+jz4+" " +
	    		    						"when aa.zlfh >="+kszlfh5+" and aa.zlfh <"+kszlfh6+" then "+jz5+" " +
	    		    						"when aa.zlfh >="+kszlfh6+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
	    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
	    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
	    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
	    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
	    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
	    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx03') aa) bb,zhandianyy zz " +
	    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
			    		    String sql4="drop table zhandianyy";
			  		     
			    		    String sql5=" create table jsdbdlyy as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and ef.cityaudit='1' and zd.property='zdsx03'";
			    		    String sql6=" create table jsdbdlyy1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1) as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdlyy tj) aa group by aa.id ";
			    		    String sql7="update jzxx j set j.jsdl=(select j1.js from jsdbdlyy1 j1 where to_char(j1.id)=j.zdid  ),j.bzw='1' where j.zdid in (select to_char(j1.id) from jsdbdlyy1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx03'";
			    		    String sql8="drop table jsdbdlyy";
			    		    String sql9="drop table jsdbdlyy1";
			    		    
			    		    String sql10=" create table jsdbdlyy2 as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and ef.cityaudit<>'1' and ef.manualauditstatus>='1' and zd.property='zdsx03'";
			    		    String sql11=" create table jsdbdlyy21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdlyy2 tj) aa group by aa.id ";
			    		    String sql12="update jzxx j set j.jsdl=0,j.bzw='2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdlyy21 j1) and j.symonth='"+yf+"' and j.property='zdsx03'";
			    		    String sql13="drop table jsdbdlyy2";
			    		    String sql14="drop table jsdbdlyy21";
			    		    
			    		    String sql15=" create table gldbdlyy as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and am.manualauditstatus='1' and db.dbyt='dbyt03' and zd.property='zdsx03'";
			    	        String sql16=" create table gldbdlyy1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdlyy tj) aa group by aa.id ";
			    		    String sql17="update jzxx j set j.gldl=(select j1.gl from gldbdlyy1 j1 where to_char(j1.id)=j.zdid) where j.zdid in (select to_char(j1.id) from gldbdlyy1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx03'";
			    		    String sql18="drop table gldbdlyy";
			    		    String sql19="drop table gldbdlyy1";
			    		    
			    		    String sql20=" create table gldbdlyy2 as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		   " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and am.manualauditstatus<>'1' and db.dbyt='dbyt03' and zd.property='zdsx03'";
			    		    String sql21=" create table gldbdlyy21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdlyy2 tj) aa group by aa.id ";
			    		    String sql22="update jzxx j set j.gldl=0 where j.gldl is null and j.zdid in(select to_char(j1.id) from gldbdlyy21 j1) and j.symonth='"+yf+"' and j.property='zdsx03'";
			    		    String sql23="drop table gldbdlyy2";
			    		    String sql24="drop table gldbdlyy21";
			    		    
			    		    System.out.println("创建营业网点电表状态表zhandianjy: "+sql.toString());
			    		    oracledb.update(sql);
			    		    System.out.println("删除jzxx表中当前月份数据"+sql2.toString());
			  		      	
			    		    oracledb.update(sql2);
			    		    if(sf.equals("09")||sf.equals("10")||sf.equals("11")||sf.equals("12")){//9-12宽松
			    		    	System.out.println("插入jzxx表当前月(9-12月): "+sql3.toString());
			    		    	oracledb.update(sql3);
			    		    }else{//1-8严格
			    		    	System.out.println("插入jzxx表当前月（1-8月）: "+sql31.toString());
			    		    	oracledb.update(sql31);
			    		    }
			    		    System.out.println("删除营业网点zhandianzt: "+sql4.toString());
			    		    oracledb.update(sql4);
			    		    System.out.println("营业网点-创建结算日均耗电量（市级审核）jsdbdlyy: "+sql5.toString());
			    		    System.out.println("营业网点-创建结算日均耗电量jsdbdlyy1: "+sql6.toString());
			    		    System.out.println("营业网点-更新jzxx表结算日均耗电量: "+sql7.toString());
			    		    System.out.println("营业网点-删除jsdbdlyy: "+sql8.toString());
			    		    System.out.println("营业网点-删除jsdbdlyy1: "+sql9.toString());
			    		    oracledb.update(sql5);
			    		    oracledb.update(sql6);
			    		    oracledb.update(sql7);
			    		    oracledb.update(sql8);
			    		    oracledb.update(sql9);
			    		    System.out.println("营业网点-创建结算日均耗电量（人工>=1 市级<>1）jsdbdlyy2: "+sql10.toString());
			    		    System.out.println("营业网点-创建结算日均耗电量（人工>=1 市级<>1）jsdbdlyy21:"+sql11.toString());
			    		    System.out.println("营业网点-更新jzxx表结算为空的补0"+sql12.toString());
			    		    System.out.println("营业网点-删除jsdbdlyy2: "+sql13.toString());
			    		    System.out.println("营业网点-删除jsdbdlyy21: "+sql14.toString());
			    		    oracledb.update(sql10);
			    		    oracledb.update(sql11);
			    		    oracledb.update(sql12);
			    		    oracledb.update(sql13);
			    		    oracledb.update(sql14);
			    		    System.out.println("营业网点-创建管理日均耗电量（人工审核=1）gldbdlyy "+sql15.toString());
			    		    System.out.println("营业网点-创建管理日均耗电量（人工审核=1）gldbdlyy1 "+sql16.toString());
			    		    System.out.println("营业网点-更新jzxx管理日均耗电量"+sql17.toString());
			    		    System.out.println("营业网点-删除gldbdlyy"+sql18.toString());
			    		    System.out.println("营业网点-删除gldbdlyy1"+sql19.toString());
			    		    oracledb.update(sql15);
			    		    oracledb.update(sql16);
			    		    oracledb.update(sql17);
			    		    oracledb.update(sql18);
			    		    oracledb.update(sql19);
			    		    System.out.println("营业网点-创建管理日均耗电量（人工审核<>1）gldbdlyy2"+sql20.toString());
			    		    System.out.println("营业网点-创建管理日均耗电量（人工审核<>1）gldbdlyy21: "+sql21.toString());
			    		    System.out.println("营业网点-更新jzxx管理为空的补0: "+sql22.toString());
			    		    System.out.println("营业网点-删除gldbdlyy2: "+sql23.toString());
			    		    System.out.println("营业网点-删除gldbdlyy21: "+sql24.toString());
			    		    oracledb.update(sql20);
			    		    oracledb.update(sql21);
			    		    oracledb.update(sql22);
			    		    oracledb.update(sql23);
			    		    oracledb.update(sql24);
			    		    System.out.println("-------------------------营业网点更新完毕-----------------------------------");
			    		    //oracledb.commit();
			            	}
			             }
		             if (((Integer.parseInt(nowHour)==3))){//zdsx01 其他更新 1点
			            	String sq="select * from khxx h where h.bzmonth='"+yf+"' and h.property='zdsx04'";
			                System.out.println("当前月有没有保存"+sq);
			            	ResultSet r=oracledb.queryAll(sq);
			            	while(r.next()){
			    				i=1;
			    			}
			            if(i==0){	
					        //oracledb.setAutoCommit(false);
			                String sql="create  table  zhandianqt as select zd.id,sum(decode(db.dbqyzt,'0',0,1)) as jj,zd.shsign as zdshenhe from " +
							"zhandian zd,dianbiao db where zd.id=db.zdid and zd.qyzt='1' and zd.caiji='0' " +
							"and zd.property='zdsx04' group  by zd.id,zd.shsign";
			                
			                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx04'";
			    		    String sql3 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
			    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
			    		    				"bb.qsdbdl*(1+bb."+month+"*bb.zlfhxs) as qsdbdl,'','zdsx04' " +
			    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
			    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <="+jszlfh1+" then "+jz1+" " +
			    		    						"when aa.zlfh >"+jszlfh1+" and aa.zlfh <= "+jszlfh2+" then "+jz2+" " +
			    		    						"when aa.zlfh >"+jszlfh2+" and aa.zlfh <="+jszlfh3+" then "+jz3+"" +
			    		    						" when aa.zlfh >"+jszlfh3+" and aa.zlfh <="+jszlfh4+" then "+jz4+" " +
			    		    						"when aa.zlfh >"+jszlfh4+" and aa.zlfh <="+jszlfh5+" then "+jz5+" " +
			    		    						"when aa.zlfh >"+jszlfh2+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
			    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
			    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
			    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
			    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
			    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
			    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx04') aa) bb,zhandianqt zz " +
			    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
			    		    String sql31 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
	    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
	    		    				"bb.qsdbdl*(1+bb."+month+"*bb.zlfhxs) as qsdbdl,'','zdsx04' " +
	    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
	    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <"+kszlfh2+" then "+jz1+" " +
	    		    						"when aa.zlfh >="+kszlfh2+" and aa.zlfh < "+kszlfh3+" then "+jz2+" " +
	    		    						"when aa.zlfh >="+kszlfh3+" and aa.zlfh < "+kszlfh4+" then "+jz3+"" +
	    		    						" when aa.zlfh >="+kszlfh4+" and aa.zlfh < "+kszlfh5+" then "+jz4+" " +
	    		    						"when aa.zlfh >="+kszlfh5+" and aa.zlfh <"+kszlfh6+" then "+jz5+" " +
	    		    						"when aa.zlfh >="+kszlfh6+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
	    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
	    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
	    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
	    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
	    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
	    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx04') aa) bb,zhandianqt zz " +
	    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
			    		    String sql4="drop table zhandianqt";
			  		     
			    		    String sql5=" create table jsdbdlqt as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and ef.cityaudit='1' and zd.property='zdsx04'";
			    		    String sql6=" create table jsdbdlqt1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1) as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdlqt tj) aa group by aa.id ";
			    		    String sql7="update jzxx j set j.jsdl=(select j1.js from jsdbdlqt1 j1 where to_char(j1.id)=j.zdid  ),j.bzw='1' where j.zdid in (select to_char(j1.id) from jsdbdlqt1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx04'";
			    		    String sql8="drop table jsdbdlqt";
			    		    String sql9="drop table jsdbdlqt1";
			    		    
			    		    String sql10=" create table jsdbdlqt2 as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and ef.cityaudit<>'1' and ef.manualauditstatus>='1' and zd.property='zdsx04'";
			    		    String sql11=" create table jsdbdlqt21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdlqt2 tj) aa group by aa.id ";
			    		    String sql12="update jzxx j set j.jsdl=0,j.bzw='2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdlqt21 j1) and j.symonth='"+yf+"' and j.property='zdsx04'";
			    		    String sql13="drop table jsdbdlqt2";
			    		    String sql14="drop table jsdbdlqt21";
			    		    
			    		    String sql15=" create table gldbdlqt as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and am.manualauditstatus='1' and db.dbyt='dbyt03' and zd.property='zdsx04'";
			    	        String sql16=" create table gldbdlqt1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdlqt tj) aa group by aa.id ";
			    		    String sql17="update jzxx j set j.gldl=(select j1.gl from gldbdlqt1 j1 where to_char(j1.id)=j.zdid) where j.zdid in (select to_char(j1.id) from gldbdlqt1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx04'";
			    		    String sql18="drop table gldbdlqt";
			    		    String sql19="drop table gldbdlqt1";
			    		    
			    		    String sql20=" create table gldbdlqt2 as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		   " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and am.manualauditstatus<>'1' and db.dbyt='dbyt03' and zd.property='zdsx04'";
			    		    String sql21=" create table gldbdlqt21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdlqt2 tj) aa group by aa.id ";
			    		    String sql22="update jzxx j set j.gldl=0 where j.gldl is null and j.zdid in(select to_char(j1.id) from gldbdlqt21 j1) and j.symonth='"+yf+"' and j.property='zdsx04'";
			    		    String sql23="drop table gldbdlqt2";
			    		    String sql24="drop table gldbdlqt21";
			    		    
			    		    System.out.println("创建其他电表状态表zhandianqt: "+sql.toString());
			    		    oracledb.update(sql);
			    		    System.out.println("删除jzxx表中当前月份数据"+sql2.toString());
			  		      	
			    		    oracledb.update(sql2);
			    		    if(sf.equals("09")||sf.equals("10")||sf.equals("11")||sf.equals("12")){//9-12宽松
			    		    	System.out.println("插入jzxx表当前月(9-12月): "+sql3.toString());
			    		    	oracledb.update(sql3);
			    		    }else{//1-8严格
			    		    	System.out.println("插入jzxx表当前月（1-8月）: "+sql31.toString());
			    		    	oracledb.update(sql31);
			    		    }
			    		    System.out.println("删除其他zhandianzt: "+sql4.toString());
			    		    oracledb.update(sql4);
			    		    System.out.println("其他-创建结算日均耗电量（市级审核）jsdbdlqt: "+sql5.toString());
			    		    System.out.println("其他-创建结算日均耗电量jsdbdlqt1: "+sql6.toString());
			    		    System.out.println("其他-更新jzxx表结算日均耗电量: "+sql7.toString());
			    		    System.out.println("其他-删除jsdbdlqt: "+sql8.toString());
			    		    System.out.println("其他-删除jsdbdlqt1: "+sql9.toString());
			    		    oracledb.update(sql5);
			    		    oracledb.update(sql6);
			    		    oracledb.update(sql7);
			    		    oracledb.update(sql8);
			    		    oracledb.update(sql9);
			    		    System.out.println("其他-创建结算日均耗电量（人工>=1 市级<>1）jsdbdlqt2: "+sql10.toString());
			    		    System.out.println("其他-创建结算日均耗电量（人工>=1 市级<>1）jsdbdlqt21:"+sql11.toString());
			    		    System.out.println("其他-更新jzxx表结算为空的补0"+sql12.toString());
			    		    System.out.println("其他-删除jsdbdlqt2: "+sql13.toString());
			    		    System.out.println("其他-删除jsdbdlqt21: "+sql14.toString());
			    		    oracledb.update(sql10);
			    		    oracledb.update(sql11);
			    		    oracledb.update(sql12);
			    		    oracledb.update(sql13);
			    		    oracledb.update(sql14);
			    		    System.out.println("其他-创建管理日均耗电量（人工审核=1）gldbdlqt "+sql15.toString());
			    		    System.out.println("其他-创建管理日均耗电量（人工审核=1）gldbdlqt1 "+sql16.toString());
			    		    System.out.println("其他-更新jzxx管理日均耗电量"+sql17.toString());
			    		    System.out.println("其他-删除gldbdlqt"+sql18.toString());
			    		    System.out.println("其他-删除gldbdlqt1"+sql19.toString());
			    		    oracledb.update(sql15);
			    		    oracledb.update(sql16);
			    		    oracledb.update(sql17);
			    		    oracledb.update(sql18);
			    		    oracledb.update(sql19);
			    		    System.out.println("其他-创建管理日均耗电量（人工审核<>1）gldbdlqt2"+sql20.toString());
			    		    System.out.println("其他-创建管理日均耗电量（人工审核<>1）gldbdlqt21: "+sql21.toString());
			    		    System.out.println("其他-更新jzxx管理为空的补0: "+sql22.toString());
			    		    System.out.println("其他-删除gldbdlqt2: "+sql23.toString());
			    		    System.out.println("其他-删除gldbdlqt21: "+sql24.toString());
			    		    oracledb.update(sql20);
			    		    oracledb.update(sql21);
			    		    oracledb.update(sql22);
			    		    oracledb.update(sql23);
			    		    oracledb.update(sql24);
			    		    System.out.println("-------------------------其他更新完毕-----------------------------------");
			    		    //oracledb.commit();
			            	}
			             }
		             if (((Integer.parseInt(nowHour)==4))){//zdsx06乡镇支局更新 4点
			            	String sq="select * from khxx h where h.bzmonth='"+yf+"' and h.property='zdsx06'";
			                System.out.println("当前月有没有保存"+sq);
			            	ResultSet r=oracledb.queryAll(sq);
			            	while(r.next()){
			    				i=1;
			    			}
			            if(i==0){	
					        //oracledb.setAutoCommit(false);
			                String sql="create  table  zhandianxzzj as select zd.id,sum(decode(db.dbqyzt,'0',0,1)) as jj,zd.shsign as zdshenhe from " +
							"zhandian zd,dianbiao db where zd.id=db.zdid and zd.qyzt='1' and zd.caiji='0' " +
							"and zd.property='zdsx06' group  by zd.id,zd.shsign";
			                
			                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx06'";
			    		    String sql3 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
			    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
			    		    				"bb.qsdbdl*(1+bb."+month+"*bb.zlfhxs) as qsdbdl,'','zdsx06' " +
			    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
			    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <="+jszlfh1+" then "+jz1+" " +
			    		    						"when aa.zlfh >"+jszlfh1+" and aa.zlfh <= "+jszlfh2+" then "+jz2+" " +
			    		    						"when aa.zlfh >"+jszlfh2+" and aa.zlfh <="+jszlfh3+" then "+jz3+"" +
			    		    						" when aa.zlfh >"+jszlfh3+" and aa.zlfh <="+jszlfh4+" then "+jz4+" " +
			    		    						"when aa.zlfh >"+jszlfh4+" and aa.zlfh <="+jszlfh5+" then "+jz5+" " +
			    		    						"when aa.zlfh >"+jszlfh2+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
			    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
			    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
			    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
			    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
			    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
			    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx06') aa) bb,zhandianxzzj zz " +
			    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
			    		    String sql31 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
	    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
	    		    				"bb.qsdbdl*(1+bb."+month+"*bb.zlfhxs) as qsdbdl,'','zdsx06' " +
	    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
	    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <"+kszlfh2+" then "+jz1+" " +
	    		    						"when aa.zlfh >="+kszlfh2+" and aa.zlfh < "+kszlfh3+" then "+jz2+" " +
	    		    						"when aa.zlfh >="+kszlfh3+" and aa.zlfh < "+kszlfh4+" then "+jz3+"" +
	    		    						" when aa.zlfh >="+kszlfh4+" and aa.zlfh < "+kszlfh5+" then "+jz4+" " +
	    		    						"when aa.zlfh >="+kszlfh5+" and aa.zlfh <"+kszlfh6+" then "+jz5+" " +
	    		    						"when aa.zlfh >="+kszlfh6+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
	    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
	    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
	    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
	    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
	    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
	    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx06') aa) bb,zhandianxzzj zz " +
	    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
			    		    String sql4="drop table zhandianxzzj";
			  		     
			    		    String sql5=" create table jsdbdlxzzj as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and ef.cityaudit='1' and zd.property='zdsx06'";
			    		    String sql6=" create table jsdbdlxzzj1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1) as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdlxzzj tj) aa group by aa.id ";
			    		    String sql7="update jzxx j set j.jsdl=(select j1.js from jsdbdlxzzj1 j1 where to_char(j1.id)=j.zdid  ),j.bzw='1' where j.zdid in (select to_char(j1.id) from jsdbdlxzzj1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx06'";
			    		    String sql8="drop table jsdbdlxzzj";
			    		    String sql9="drop table jsdbdlxzzj1";
			    		    
			    		    String sql10=" create table jsdbdlxzzj2 as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and ef.cityaudit<>'1' and ef.manualauditstatus>='1' and zd.property='zdsx06'";
			    		    String sql11=" create table jsdbdlxzzj21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdlxzzj2 tj) aa group by aa.id ";
			    		    String sql12="update jzxx j set j.jsdl=0,j.bzw='2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdlxzzj21 j1) and j.symonth='"+yf+"' and j.property='zdsx06'";
			    		    String sql13="drop table jsdbdlxzzj2";
			    		    String sql14="drop table jsdbdlxzzj21";
			    		    
			    		    String sql15=" create table gldbdlxzzj as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and am.manualauditstatus='1' and db.dbyt='dbyt03' and zd.property='zdsx06'";
			    	        String sql16=" create table gldbdlxzzj1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdlxzzj tj) aa group by aa.id ";
			    		    String sql17="update jzxx j set j.gldl=(select j1.gl from gldbdlxzzj1 j1 where to_char(j1.id)=j.zdid) where j.zdid in (select to_char(j1.id) from gldbdlxzzj1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx06'";
			    		    String sql18="drop table gldbdlxzzj";
			    		    String sql19="drop table gldbdlxzzj1";
			    		    
			    		    String sql20=" create table gldbdlxzzj2 as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		   " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and am.manualauditstatus<>'1' and db.dbyt='dbyt03' and zd.property='zdsx06'";
			    		    String sql21=" create table gldbdlxzzj21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdlxzzj2 tj) aa group by aa.id ";
			    		    String sql22="update jzxx j set j.gldl=0 where j.gldl is null and j.zdid in(select to_char(j1.id) from gldbdlxzzj21 j1) and j.symonth='"+yf+"' and j.property='zdsx06'";
			    		    String sql23="drop table gldbdlxzzj2";
			    		    String sql24="drop table gldbdlxzzj21";
			    		    
			    		    System.out.println("创建乡镇支局电表状态表zhandianxzzj: "+sql.toString());
			    		    oracledb.update(sql);
			    		    System.out.println("删除jzxx表中当前月份数据"+sql2.toString());
			  		      	
			    		    oracledb.update(sql2);
			    		    if(sf.equals("09")||sf.equals("10")||sf.equals("11")||sf.equals("12")){//9-12宽松
			    		    	System.out.println("插入jzxx表当前月(9-12月): "+sql3.toString());
			    		    	oracledb.update(sql3);
			    		    }else{//1-8严格
			    		    	System.out.println("插入jzxx表当前月（1-8月）: "+sql31.toString());
			    		    	oracledb.update(sql31);
			    		    }
			    		    System.out.println("删除乡镇支局zhandianzt: "+sql4.toString());
			    		    oracledb.update(sql4);
			    		    System.out.println("乡镇支局-创建结算日均耗电量（市级审核）jsdbdlxzzj: "+sql5.toString());
			    		    System.out.println("乡镇支局-创建结算日均耗电量jsdbdlxzzj1: "+sql6.toString());
			    		    System.out.println("乡镇支局-更新jzxx表结算日均耗电量: "+sql7.toString());
			    		    System.out.println("乡镇支局-删除jsdbdlxzzj: "+sql8.toString());
			    		    System.out.println("乡镇支局-删除jsdbdlxzzj1: "+sql9.toString());
			    		    oracledb.update(sql5);
			    		    oracledb.update(sql6);
			    		    oracledb.update(sql7);
			    		    oracledb.update(sql8);
			    		    oracledb.update(sql9);
			    		    System.out.println("乡镇支局-创建结算日均耗电量（人工>=1 市级<>1）jsdbdlxzzj2: "+sql10.toString());
			    		    System.out.println("乡镇支局-创建结算日均耗电量（人工>=1 市级<>1）jsdbdlxzzj21:"+sql11.toString());
			    		    System.out.println("乡镇支局-更新jzxx表结算为空的补0"+sql12.toString());
			    		    System.out.println("乡镇支局-删除jsdbdlxzzj2: "+sql13.toString());
			    		    System.out.println("乡镇支局-删除jsdbdlxzzj21: "+sql14.toString());
			    		    oracledb.update(sql10);
			    		    oracledb.update(sql11);
			    		    oracledb.update(sql12);
			    		    oracledb.update(sql13);
			    		    oracledb.update(sql14);
			    		    System.out.println("乡镇支局-创建管理日均耗电量（人工审核=1）gldbdlxzzj "+sql15.toString());
			    		    System.out.println("乡镇支局-创建管理日均耗电量（人工审核=1）gldbdlxzzj1 "+sql16.toString());
			    		    System.out.println("乡镇支局-更新jzxx管理日均耗电量"+sql17.toString());
			    		    System.out.println("乡镇支局-删除gldbdlxzzj"+sql18.toString());
			    		    System.out.println("乡镇支局-删除gldbdlxzzj1"+sql19.toString());
			    		    oracledb.update(sql15);
			    		    oracledb.update(sql16);
			    		    oracledb.update(sql17);
			    		    oracledb.update(sql18);
			    		    oracledb.update(sql19);
			    		    System.out.println("乡镇支局-创建管理日均耗电量（人工审核<>1）gldbdlxzzj2"+sql20.toString());
			    		    System.out.println("乡镇支局-创建管理日均耗电量（人工审核<>1）gldbdlxzzj21: "+sql21.toString());
			    		    System.out.println("乡镇支局-更新jzxx管理为空的补0: "+sql22.toString());
			    		    System.out.println("乡镇支局-删除gldbdlxzzj2: "+sql23.toString());
			    		    System.out.println("乡镇支局-删除gldbdlxzzj21: "+sql24.toString());
			    		    oracledb.update(sql20);
			    		    oracledb.update(sql21);
			    		    oracledb.update(sql22);
			    		    oracledb.update(sql23);
			    		    oracledb.update(sql24);
			    		    System.out.println("-------------------------乡镇支局更新完毕-----------------------------------");
			    		    //oracledb.commit();
			            	}
			             }
		             if (((Integer.parseInt(nowHour)==5))){//zdsx07IDC机房更新 1点
			            	String sq="select * from khxx h where h.bzmonth='"+yf+"' and h.property='zdsx07'";
			                System.out.println("当前月有没有保存"+sq);
			            	ResultSet r=oracledb.queryAll(sq);
			            	while(r.next()){
			    				i=1;
			    			}
			            if(i==0){	
					        //oracledb.setAutoCommit(false);
			                String sql="create  table  zhandianidc as select zd.id,sum(decode(db.dbqyzt,'0',0,1)) as jj,zd.shsign as zdshenhe from " +
							"zhandian zd,dianbiao db where zd.id=db.zdid and zd.qyzt='1' and zd.caiji='0' " +
							"and zd.property='zdsx07' group  by zd.id,zd.shsign";
			                
			                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx07'";
			    		    String sql3 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
			    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
			    		    				"bb.qsdbdl*(1+bb."+month+"*bb.zlfhxs) as qsdbdl,'','zdsx07' " +
			    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
			    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <="+jszlfh1+" then "+jz1+" " +
			    		    						"when aa.zlfh >"+jszlfh1+" and aa.zlfh <= "+jszlfh2+" then "+jz2+" " +
			    		    						"when aa.zlfh >"+jszlfh2+" and aa.zlfh <="+jszlfh3+" then "+jz3+"" +
			    		    						" when aa.zlfh >"+jszlfh3+" and aa.zlfh <="+jszlfh4+" then "+jz4+" " +
			    		    						"when aa.zlfh >"+jszlfh4+" and aa.zlfh <="+jszlfh5+" then "+jz5+" " +
			    		    						"when aa.zlfh >"+jszlfh2+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
			    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
			    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
			    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
			    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
			    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
			    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx07') aa) bb,zhandianidc zz " +
			    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
			    		    String sql31 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
	    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
	    		    				"bb.qsdbdl*(1+bb."+month+"*bb.zlfhxs) as qsdbdl,'','zdsx07' " +
	    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
	    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <"+kszlfh2+" then "+jz1+" " +
	    		    						"when aa.zlfh >="+kszlfh2+" and aa.zlfh < "+kszlfh3+" then "+jz2+" " +
	    		    						"when aa.zlfh >="+kszlfh3+" and aa.zlfh < "+kszlfh4+" then "+jz3+"" +
	    		    						" when aa.zlfh >="+kszlfh4+" and aa.zlfh < "+kszlfh5+" then "+jz4+" " +
	    		    						"when aa.zlfh >="+kszlfh5+" and aa.zlfh <"+kszlfh6+" then "+jz5+" " +
	    		    						"when aa.zlfh >="+kszlfh6+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
	    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
	    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
	    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
	    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
	    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
	    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx07') aa) bb,zhandianidc zz " +
	    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
			    		    String sql4="drop table zhandianidc";
			  		     
			    		    String sql5=" create table jsdbdlidc as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and ef.cityaudit='1' and zd.property='zdsx07'";
			    		    String sql6=" create table jsdbdlidc1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1) as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdlidc tj) aa group by aa.id ";
			    		    String sql7="update jzxx j set j.jsdl=(select j1.js from jsdbdlidc1 j1 where to_char(j1.id)=j.zdid  ),j.bzw='1' where j.zdid in (select to_char(j1.id) from jsdbdlidc1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx07'";
			    		    String sql8="drop table jsdbdlidc";
			    		    String sql9="drop table jsdbdlidc1";
			    		    
			    		    String sql10=" create table jsdbdlidc2 as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and ef.cityaudit<>'1' and ef.manualauditstatus>='1' and zd.property='zdsx07'";
			    		    String sql11=" create table jsdbdlidc21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdlidc2 tj) aa group by aa.id ";
			    		    String sql12="update jzxx j set j.jsdl=0,j.bzw='2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdlidc21 j1) and j.symonth='"+yf+"' and j.property='zdsx07'";
			    		    String sql13="drop table jsdbdlidc2";
			    		    String sql14="drop table jsdbdlidc21";
			    		    
			    		    String sql15=" create table gldbdlidc as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and am.manualauditstatus='1' and db.dbyt='dbyt03' and zd.property='zdsx07'";
			    	        String sql16=" create table gldbdlidc1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdlidc tj) aa group by aa.id ";
			    		    String sql17="update jzxx j set j.gldl=(select j1.gl from gldbdlidc1 j1 where to_char(j1.id)=j.zdid) where j.zdid in (select to_char(j1.id) from gldbdlidc1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx07'";
			    		    String sql18="drop table gldbdlidc";
			    		    String sql19="drop table gldbdlidc1";
			    		    
			    		    String sql20=" create table gldbdlidc2 as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		   " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and am.manualauditstatus<>'1' and db.dbyt='dbyt03' and zd.property='zdsx07'";
			    		    String sql21=" create table gldbdlidc21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdlidc2 tj) aa group by aa.id ";
			    		    String sql22="update jzxx j set j.gldl=0 where j.gldl is null and j.zdid in(select to_char(j1.id) from gldbdlidc21 j1) and j.symonth='"+yf+"' and j.property='zdsx07'";
			    		    String sql23="drop table gldbdlidc2";
			    		    String sql24="drop table gldbdlidc21";
			    		    
			    		    System.out.println("创建IDC机房电表状态表zhandianidc: "+sql.toString());
			    		    oracledb.update(sql);
			    		    System.out.println("删除jzxx表中当前月份数据"+sql2.toString());
			  		      	
			    		    oracledb.update(sql2);
			    		    if(sf.equals("09")||sf.equals("10")||sf.equals("11")||sf.equals("12")){//9-12宽松
			    		    	System.out.println("插入jzxx表当前月(9-12月): "+sql3.toString());
			    		    	oracledb.update(sql3);
			    		    }else{//1-8严格
			    		    	System.out.println("插入jzxx表当前月（1-8月）: "+sql31.toString());
			    		    	oracledb.update(sql31);
			    		    }
			    		    System.out.println("删除IDC机房zhandianzt: "+sql4.toString());
			    		    oracledb.update(sql4);
			    		    System.out.println("IDC机房-创建结算日均耗电量（市级审核）jsdbdlidc: "+sql5.toString());
			    		    System.out.println("IDC机房-创建结算日均耗电量jsdbdlidc1: "+sql6.toString());
			    		    System.out.println("IDC机房-更新jzxx表结算日均耗电量: "+sql7.toString());
			    		    System.out.println("IDC机房-删除jsdbdlidc: "+sql8.toString());
			    		    System.out.println("IDC机房-删除jsdbdlidc1: "+sql9.toString());
			    		    oracledb.update(sql5);
			    		    oracledb.update(sql6);
			    		    oracledb.update(sql7);
			    		    oracledb.update(sql8);
			    		    oracledb.update(sql9);
			    		    System.out.println("IDC机房-创建结算日均耗电量（人工>=1 市级<>1）jsdbdlidc2: "+sql10.toString());
			    		    System.out.println("IDC机房-创建结算日均耗电量（人工>=1 市级<>1）jsdbdlidc21:"+sql11.toString());
			    		    System.out.println("IDC机房-更新jzxx表结算为空的补0"+sql12.toString());
			    		    System.out.println("IDC机房-删除jsdbdlidc2: "+sql13.toString());
			    		    System.out.println("IDC机房-删除jsdbdlidc21: "+sql14.toString());
			    		    oracledb.update(sql10);
			    		    oracledb.update(sql11);
			    		    oracledb.update(sql12);
			    		    oracledb.update(sql13);
			    		    oracledb.update(sql14);
			    		    System.out.println("IDC机房-创建管理日均耗电量（人工审核=1）gldbdlidc "+sql15.toString());
			    		    System.out.println("IDC机房-创建管理日均耗电量（人工审核=1）gldbdlidc1 "+sql16.toString());
			    		    System.out.println("IDC机房-更新jzxx管理日均耗电量"+sql17.toString());
			    		    System.out.println("IDC机房-删除gldbdlidc"+sql18.toString());
			    		    System.out.println("IDC机房-删除gldbdlidc1"+sql19.toString());
			    		    oracledb.update(sql15);
			    		    oracledb.update(sql16);
			    		    oracledb.update(sql17);
			    		    oracledb.update(sql18);
			    		    oracledb.update(sql19);
			    		    System.out.println("IDC机房-创建管理日均耗电量（人工审核<>1）gldbdlidc2"+sql20.toString());
			    		    System.out.println("IDC机房-创建管理日均耗电量（人工审核<>1）gldbdlidc21: "+sql21.toString());
			    		    System.out.println("IDC机房-更新jzxx管理为空的补0: "+sql22.toString());
			    		    System.out.println("IDC机房-删除gldbdlidc2: "+sql23.toString());
			    		    System.out.println("IDC机房-删除gldbdlidc21: "+sql24.toString());
			    		    oracledb.update(sql20);
			    		    oracledb.update(sql21);
			    		    oracledb.update(sql22);
			    		    oracledb.update(sql23);
			    		    oracledb.update(sql24);
			    		    System.out.println("-------------------------IDC机房更新完毕-----------------------------------");
			    		    //oracledb.commit();
			            	}
			             }
		             if (((Integer.parseInt(nowHour)==6))){//zdsx05接入网更新 1点
			            	String sq="select * from khxx h where h.bzmonth='"+yf+"' and h.property='zdsx05'";
			                System.out.println("当前月有没有保存"+sq);
			            	ResultSet r=oracledb.queryAll(sq);
			            	while(r.next()){
			    				i=1;
			    			}
			            if(i==0){	
					        //oracledb.setAutoCommit(false);
			                String sql="create  table  zhandianjrw as select zd.id,sum(decode(db.dbqyzt,'0',0,1)) as jj,zd.shsign as zdshenhe from " +
							"zhandian zd,dianbiao db where zd.id=db.zdid and zd.qyzt='1' and zd.caiji='0' " +
							"and zd.property='zdsx05' group  by zd.id,zd.shsign";
			                
			                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx05'";
			    		    String sql3 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
			    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
			    		    				"bb.qsdbdl*1+(bb."+month+"*bb.zlfhxs) as qsdbdl,'','zdsx05' " +
			    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
			    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <="+jszlfh1+" then "+jz1+" " +
			    		    						"when aa.zlfh >"+jszlfh1+" and aa.zlfh <= "+jszlfh2+" then "+jz2+" " +
			    		    						"when aa.zlfh >"+jszlfh2+" and aa.zlfh <="+jszlfh3+" then "+jz3+"" +
			    		    						" when aa.zlfh >"+jszlfh3+" and aa.zlfh <="+jszlfh4+" then "+jz4+" " +
			    		    						"when aa.zlfh >"+jszlfh4+" and aa.zlfh <="+jszlfh5+" then "+jz5+" " +
			    		    						"when aa.zlfh >"+jszlfh2+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
			    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
			    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
			    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
			    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
			    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
			    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx05') aa) bb,zhandianjrw zz " +
			    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
			    		    String sql31 ="insert into jzxx (symonth,shi,xian,xiaoqu,zdid,jzname,g2,g3,changjia,zp,zs,gxgwsl,jztype,shebei,ydshebei,sfgx,jsdl,gldl,qsdbdl,bzw,property) " +
	    		    		" select '"+yf+"',bb.agname,'','',bb.id,bb.jzname,'','','','','','','','','','','',''," +
	    		    				"bb.qsdbdl*1+(bb."+month+"*bb.zlfhxs) as qsdbdl,'','zdsx05' " +
	    		    				"from (select aa.shi,aa.agname,aa.id,aa.jzname, aa.qsdbdl, aa.jcxs,aa."+month+",aa.shizlfh,aa.edhdl,aa.zlfh," +
	    		    				"(case when aa.zlfh >= "+kszlfh1+" and aa.zlfh <"+kszlfh2+" then "+jz1+" " +
	    		    						"when aa.zlfh >="+kszlfh2+" and aa.zlfh < "+kszlfh3+" then "+jz2+" " +
	    		    						"when aa.zlfh >="+kszlfh3+" and aa.zlfh < "+kszlfh4+" then "+jz3+"" +
	    		    						" when aa.zlfh >="+kszlfh4+" and aa.zlfh < "+kszlfh5+" then "+jz4+" " +
	    		    						"when aa.zlfh >="+kszlfh5+" and aa.zlfh <"+kszlfh6+" then "+jz5+" " +
	    		    						"when aa.zlfh >="+kszlfh6+" then "+jz6+" end) zlfhxs,aa.yflx,aa.yfxs " +
	    		    						"from (select zd.shi,d.agname,zd.id,zd.jzname,db.scb as qsdbdl, "+jcxs1+" as jcxs,dbyf."+month+"," +
	    		    						"zd.zlfh as shizlfh,zd.edhdl,(case when zd.zlfh is not null and to_number(zd.zlfh) <> 0 " +
	    		    						" then zd.zlfh else 0 end) as zlfh, zd.yflx, decode(zd.yflx, '', 0, null, 0, 'null', 0, '0', 0," +
	    		    						" 'fwlx01',"+xs1+", 'fwlx02',"+xs2+",'fwlx03',"+xs3+") as yfxs from zhandian zd left join d_area_grade d " +
	    		    						"on zd.shi=d.agcode  left join yfxs dbyf on zd.shi = dbyf.shicode, " +
	    		    						"scb db where zd.id = db.zdid and zd.caiji='0' and zd.qyzt='1' and zd.property='zdsx05') aa) bb,zhandianjrw zz " +
	    		    						" where bb.id=zz.id and zz.jj<>0 and zz.zdshenhe<>0";
			    		    String sql4="drop table zhandianjrw";
			  		     
			    		    String sql5=" create table jsdbdljrw as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and ef.cityaudit='1' and zd.property='zdsx05'";
			    		    String sql6=" create table jsdbdljrw1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1) as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdljrw tj) aa group by aa.id ";
			    		    String sql7="update jzxx j set j.jsdl=(select j1.js from jsdbdljrw1 j1 where to_char(j1.id)=j.zdid  ),j.bzw='1' where j.zdid in (select to_char(j1.id) from jsdbdljrw1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx05'";
			    		    String sql8="drop table jsdbdljrw";
			    		    String sql9="drop table jsdbdljrw1";
			    		    
			    		    String sql10=" create table jsdbdljrw2 as select zd.shi,zd.id,zd.jzname,ef.manualauditstatus,ef.cityaudit,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and am.ammeterdegreeid = ef.ammeterdegreeid_fk  " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and ef.cityaudit<>'1' and ef.manualauditstatus>='1' and zd.property='zdsx05'";
			    		    String sql11=" create table jsdbdljrw21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as js from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from jsdbdljrw2 tj) aa group by aa.id ";
			    		    String sql12="update jzxx j set j.jsdl=0,j.bzw='2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdljrw21 j1) and j.symonth='"+yf+"' and j.property='zdsx05'";
			    		    String sql13="drop table jsdbdljrw2";
			    		    String sql14="drop table jsdbdljrw21";
			    		    
			    		    String sql15=" create table gldbdljrw as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		    " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and am.manualauditstatus='1' and db.dbyt='dbyt03' and zd.property='zdsx05'";
			    	        String sql16=" create table gldbdljrw1 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdljrw tj) aa group by aa.id ";
			    		    String sql17="update jzxx j set j.gldl=(select j1.gl from gldbdljrw1 j1 where to_char(j1.id)=j.zdid) where j.zdid in (select to_char(j1.id) from gldbdljrw1 j1 where to_char(j1.id)=j.zdid  ) and j.symonth='"+yf+"' and j.property='zdsx05'";
			    		    String sql18="drop table gldbdljrw";
			    		    String sql19="drop table gldbdljrw1";
			    		    
			    		    String sql20=" create table gldbdljrw2 as select zd.shi,zd.id,zd.jzname,am.manualauditstatus,am.lastdatetime," +
			    		    "am.thisdatetime,am.blhdl,(TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) as zq," +
			    		    "(case when substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' " +
			    		    "then (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) when" +
			    		    " substr(am.lastdatetime, 0, 7) = '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then " +
			    		    "(TO_CHAR(CEIL(TO_DATE('"+lastDay+"', 'yyyy-mm-dd') - TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) + 1) " +
			    		    "when substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) = '"+yf+"' then" +
			    		   " (TO_CHAR(CEIL(TO_DATE(am.THISDATETIME, 'yyyy-mm-dd') - TO_DATE('"+firstDay+"', 'yyyy-mm-dd'))) + 1) when " +
			    		    "substr(am.lastdatetime, 0, 7) < '"+yf+"' and substr(am.thisdatetime, 0, 7) > '"+yf+"' then "+days+" else 0 end) as zq1" +
			    		    " from zhandian zd, dianbiao db, dianduview am where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
			    		    "and LENGTH(am.THISDATETIME) = 10 and LENGTH(am.LASTDATETIME) = 10 and" +
			    		    " ((substr(am.thisdatetime, 0, 7) >= '"+yf+"' and substr(am.lastdatetime, 0, 7) <= '"+yf+"'))" +
			    		    " AND zd.xuni = '0' and am.manualauditstatus<>'1' and db.dbyt='dbyt03' and zd.property='zdsx05'";
			    		    String sql21=" create table gldbdljrw21 as select aa.id,sum(aa.bzmonth1)/sum(aa.zq1)as gl from (select tj.id,(tj.blhdl / tj.zq) * tj.zq1 as bzmonth1, tj.zq1 from gldbdljrw2 tj) aa group by aa.id ";
			    		    String sql22="update jzxx j set j.gldl=0 where j.gldl is null and j.zdid in(select to_char(j1.id) from gldbdljrw21 j1) and j.symonth='"+yf+"' and j.property='zdsx05'";
			    		    String sql23="drop table gldbdljrw2";
			    		    String sql24="drop table gldbdljrw21";
			    		    
			    		    System.out.println("创建接入网电表状态表zhandianidc: "+sql.toString());
			    		    oracledb.update(sql);
			    		    System.out.println("删除jzxx表中当前月份数据"+sql2.toString());
			  		      	
			    		    oracledb.update(sql2);
			    		    if(sf.equals("09")||sf.equals("10")||sf.equals("11")||sf.equals("12")){//9-12宽松
			    		    	System.out.println("插入jzxx表当前月(9-12月): "+sql3.toString());
			    		    	oracledb.update(sql3);
			    		    }else{//1-8严格
			    		    	System.out.println("插入jzxx表当前月（1-8月）: "+sql31.toString());
			    		    	oracledb.update(sql31);
			    		    }
			    		    System.out.println("删除接入网zhandianzt: "+sql4.toString());
			    		    oracledb.update(sql4);
			    		    System.out.println("接入网-创建结算日均耗电量（市级审核）jsdbdljrw: "+sql5.toString());
			    		    System.out.println("接入网-创建结算日均耗电量jsdbdlrw1: "+sql6.toString());
			    		    System.out.println("接入网-更新jzxx表结算日均耗电量: "+sql7.toString());
			    		    System.out.println("接入网-删除jsdbdljrw: "+sql8.toString());
			    		    System.out.println("接入网-删除jsdbdljrw1: "+sql9.toString());
			    		    oracledb.update(sql5);
			    		    oracledb.update(sql6);
			    		    oracledb.update(sql7);
			    		    oracledb.update(sql8);
			    		    oracledb.update(sql9);
			    		    System.out.println("接入网-创建结算日均耗电量（人工>=1 市级<>1）jsdbdljrw2: "+sql10.toString());
			    		    System.out.println("接入网-创建结算日均耗电量（人工>=1 市级<>1）jsdbdljrw21:"+sql11.toString());
			    		    System.out.println("接入网-更新jzxx表结算为空的补0"+sql12.toString());
			    		    System.out.println("接入网-删除jsdbdljrw2: "+sql13.toString());
			    		    System.out.println("接入网-删除jsdbdljrw21: "+sql14.toString());
			    		    oracledb.update(sql10);
			    		    oracledb.update(sql11);
			    		    oracledb.update(sql12);
			    		    oracledb.update(sql13);
			    		    oracledb.update(sql14);
			    		    System.out.println("接入网-创建管理日均耗电量（人工审核=1）gldbdljrw "+sql15.toString());
			    		    System.out.println("接入网-创建管理日均耗电量（人工审核=1）gldbdljrw1 "+sql16.toString());
			    		    System.out.println("接入网-更新jzxx管理日均耗电量"+sql17.toString());
			    		    System.out.println("接入网-删除gldbdljrw"+sql18.toString());
			    		    System.out.println("接入网-删除gldbdljrw1"+sql19.toString());
			    		    oracledb.update(sql15);
			    		    oracledb.update(sql16);
			    		    oracledb.update(sql17);
			    		    oracledb.update(sql18);
			    		    oracledb.update(sql19);
			    		    System.out.println("接入网-创建管理日均耗电量（人工审核<>1）gldbdljrw2"+sql20.toString());
			    		    System.out.println("接入网-创建管理日均耗电量（人工审核<>1）gldbdljrw21: "+sql21.toString());
			    		    System.out.println("接入网-更新jzxx管理为空的补0: "+sql22.toString());
			    		    System.out.println("接入网-删除gldbdljrw2: "+sql23.toString());
			    		    System.out.println("接入网-删除gldbdljrw21: "+sql24.toString());
			    		    oracledb.update(sql20);
			    		    oracledb.update(sql21);
			    		    oracledb.update(sql22);
			    		    oracledb.update(sql23);
			    		    oracledb.update(sql24);
			    		    System.out.println("-------------------------接入网更新完毕-----------------------------------");
			    		    //oracledb.commit();
			            	}
			             }
		            } catch (Exception de) {
							try {
								oracledb.rollback();
							} catch (DbException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                de.printStackTrace();
		            }finally {
		        	   
		                try {
		                    oracledb.close();
		                } catch (DbException de) {
		                    de.printStackTrace();
		                }
		            }
		        
			}
}
