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

public class NewJzxxDao extends TimerTask {
	   	private String nowtime = "";
	    private String yaerMonth = "";
	    private String nowDay = "";
	    private String nowHour = "";
	    private String preSday = "";//
	    private String maxDate = "";
	    private static final SimpleDateFormat sdfk = new SimpleDateFormat(
	            "yyyy-MM-dd");
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
		       
		       /* String fwid = "", fwlx = "", xs = "", jcxs = "", fwbzw = "";
				String fwid1 = "", fwlx1 = "", xs1 = "", jcxs1 = "", fwbzw1 = "";
				String fwid2 = "", fwlx2 = "", xs2 = "", jcxs2 = "", fwbzw2 = "";
				String fwid3 = "", fwlx3 = "", xs3 = "", jcxs3 = "", fwbzw3 = "";
				
				String kszlfh = "", jszlfh = "", jz = "", jrw = "", xzzj = "", jyjf = "", qt = "", idcjf = "", yywd = "", id1 = "", zlfh = "";
				String kszlfh1 = "", jszlfh1 = "", jz1 = "", jrw1 = "", xzzj1 = "", jyjf1 = "", qt1 = "", idcjf1 = "", yywd1 = "", id11 = "", zlfh1 = "";
				String kszlfh2 = "", jszlfh2 = "", jz2 = "", jrw2 = "", xzzj2 = "", jyjf2 = "", qt2 = "", idcjf2 = "", yywd2 = "", id12 = "", zlfh2 = "";
				String kszlfh3 = "", jszlfh3 = "", jz3 = "", jrw3 = "", xzzj3 = "", jyjf3 = "", qt3 = "", idcjf3 = "", yywd3 = "", id13 = "", zlfh3 = "";
				String kszlfh4 = "", jszlfh4 = "", jz4 = "", jrw4 = "", xzzj4 = "", jyjf4 = "", qt4 = "", idcjf4 = "", yywd4 = "", id14 = "", zlfh4 = "";
				String kszlfh5 = "", jszlfh5 = "", jz5 = "", jrw5 = "", xzzj5 = "", jyjf5 = "", qt5 = "", idcjf5 = "", yywd5 = "", id15 = "", zlfh5 = "";
				String kszlfh6 = "", jszlfh6 = "", jz6 = "", jrw6 = "", xzzj6 = "", jyjf6 = "", qt6 = "", idcjf6 = "", yywd6 = "", id16 = "", zlfh6 = "";
			
				String month = "";
				if (sf.equals("01")) {
					month = "ymonth";
				} else if (sf.equals("02")) {
					month = "emonth";
				} else if (sf.equals("03")) {
					month = "smonth";
				} else if (sf.equals("04")) {
					month = "wmonth";
				} else if (sf.equals("05")) {
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
				**/	
				
		            Calendar calendar = Calendar.getInstance();
		            calendar.add(calendar.DATE, -1);
		            Date perday = calendar.getTime();
		            preSday = sdfk.format(perday);
		            DataBase oracledb = new DataBase();
		          System.out.println(nowHour+"-::::---------------------------------");
		          int i=0;
		            try {
		         /*   	
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
		                System.out.println("空调系数"+"SELECT K.ID,K.KSZLFH,K.JSZLSH,K.JZKTXS,K.JRWKTXS,K.XZZJKTXS,K.JYJFKTXS,K.QTKTXS,K.IDCJFKTXS,K.YYWDKTXS,K.SJBZW FROM KTXS K ");
		                ResultSet rs1 = oracledb.queryAll("SELECT K.ID,K.KSZLFH,K.JSZLSH,K.JZKTXS,K.JRWKTXS,K.XZZJKTXS,K.JYJFKTXS,K.QTKTXS,K.IDCJFKTXS,K.YYWDKTXS,K.SJBZW FROM KTXS K ");
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
						yywd = rs1.getString(10);
						jz = mat.format(Double.parseDouble(jz));
						jrw = mat.format(Double.parseDouble(jrw));
						xzzj = mat.format(Double.parseDouble(xzzj));
						jyjf = mat.format(Double.parseDouble(jyjf));
						qt = mat.format(Double.parseDouble(qt));
						idcjf = mat.format(Double.parseDouble(idcjf));
						yywd = mat.format(Double.parseDouble(yywd));
						if (id1.equals("21")) {
							kszlfh1 = kszlfh;
							jszlfh1 = jszlfh;
							jz1 = jz;
							jrw1 = jrw;
							xzzj1 = xzzj;
							jyjf1 = jyjf;
							qt1 = qt;
							idcjf1 = idcjf;
							yywd1 = yywd;
						} else if (id1.equals("22")) {
							kszlfh2 = kszlfh;
							jszlfh2 = jszlfh;
							jz2 = jz;
							jrw2 = jrw;
							xzzj2 = xzzj;
							jyjf2 = jyjf;
							qt2 = qt;
							idcjf2 = idcjf;
							yywd2 = yywd;
						} else if (id1.equals("23")) {
							kszlfh3 = kszlfh;
							jszlfh3 = jszlfh;
							jz3 = jz;
							jrw3 = jrw;
							xzzj3 = xzzj;
							jyjf3 = jyjf;
							qt3 = qt;
							idcjf3 = idcjf;
							yywd3 = yywd;
						} else if (id1.equals("24")) {
							kszlfh4 = kszlfh;
							jszlfh4 = jszlfh;
							jz4 = jz;
							jrw4 = jrw;
							xzzj4 = xzzj;
							jyjf4 = jyjf;
							qt4 = qt;
							idcjf4 = idcjf;
							yywd4 = yywd;
						} else if (id1.equals("25")) {
							kszlfh5 = kszlfh;
							jszlfh5 = jszlfh;
							jz5 = jz;
							jrw5 = jrw;
							xzzj5 = xzzj;
							jyjf5 = jyjf;
							qt5 = qt;
							idcjf5 = idcjf;
							yywd5 = yywd;
						} else if (id1.equals("26")) {
							kszlfh6 = kszlfh;
							jz6 = jz;
							jrw6 = jrw;
							xzzj6 = xzzj;
							jyjf6 = jyjf;
							qt6 = qt;
							idcjf6 = idcjf;
							yywd6 = yywd;
						}
		                }**/
		            //	yf="2014-12";
		            	System.out.println("----------------------------------------------开始----------------------------------------------");
		             if (((Integer.parseInt(nowHour)==0))){//zdsx02 基站为0点更新
		            	String sq="select * from khxx h where h.bzmonth='"+yf+"' and h.property='zdsx02'";
		                System.out.println("当前月有没有保存"+sq);
		            	ResultSet r=oracledb.queryAll(sq);
		            	while(r.next()){
		    				i=1;
		    			}
		            if(i==0){	
				        //oracledb.setAutoCommit(false);
		                String sql="create  table  zhandianzt as select zd.id, sum(decode(db.dbqyzt, '0', 0, 1)) as jj from zhandian zd, dianbiao db " +
		                		"where zd.id = db.zdid and zd.qyzt = '1' and zd.caiji = '0' AND zd.shsign='1' and zd.property = 'zdsx02' group by zd.ID";
		                
		                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx02'";
		    		    String sql3 ="insert into jzxx(symonth,  shi, xian, xiaoqu, zdid, jzname, g2, g3,changjia,zp, zs, gxgwsl,jztype, shebei," +
		    		    		" ydshebei,sfgx, dbydl, dbgldl, zdqsdbdl, daycount, scb, edhdl, bzw, property, bzz, zdscb,zdedhdl, zddqsdbdl, " +
		    		    		"zlfh, jlfh, zdjlfh, zdzlfh) select '"+yf+"', bb.agname, '', '', bb.id, bb.jzname, '', '','', '', '','', ''," +
		    		    		" '', '','', '', '', bb.qsdbdl, '', bb.scb,  bb.edhdl, '','zdsx02','', bb.scb, bb.edhdl, bb.qsdbdl, bb.zlfh, bb.jlfh,  bb.jlfh,  bb.zlfh " +
		    		    		" from (select aa.shi, aa.agname, aa.id, aa.jzname, aa.qsdbdl, aa.edhdl,aa.scb,aa.zlfh,aa.jlfh from " +
		    		    		"(select zd.shi, d.agname,  zd.id, zd.jzname,db.scb, zd.qsdbdl,  zd.edhdl,zd.zlfh,zd.jlfh from zhandian zd " +
		    		    		"left join d_area_grade d on zd.shi = d.agcode left join yfxs dbyf on zd.shi = dbyf.shicode, scb db  " +
		    		    		"where zd.id = db.zdid and zd.caiji = '0' and zd.qyzt = '1' AND zd.xuni='0' AND zd.shsign='1' and " +
		    		    		"zd.property = 'zdsx02') aa) bb, zhandianzt zz where bb.id = zz.id and zz.jj <> 0";
		    		    String sql4="create table jzxx1dl as SELECT  zd.ID,ef.accountmonth,min(ef.edhdl)edhdl,min(ef.qsdbdl)qsdbdl,min(am.scb)scb," +
		    		    		"MIN(ef.zlfh)zlfh,MIN(ef.jlfh) jlfh from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where " +
		    		    		"zd.id = db.zdid  and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk AND " +
		    		    		"to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
		    		    		" and zd.property = 'zdsx02' GROUP BY zd.ID,ef.accountmonth";
		    		    String sql5="UPDATE jzxx J SET J.SCB      = (SELECT D.SCB FROM jzxx1DL D WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"'), " +
		    		    		"J.ZDQSDBDL = (SELECT D.QSDBDL FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
		    		    		"J.EDHDL    = (SELECT D.EDHDL FROM jzxx1DL D  WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
		    		    		"J.zlfh    = (SELECT D.zlfh  FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
		    		    		"J.jlfh    = (SELECT D.jlfh FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"') " +
		    		    		"WHERE J.ZDID IN (SELECT D.ID FROM jzxx1DL D  WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"') AND J.PROPERTY='zdsx02' AND J.SYMONTH = '"+yf+"'";
		    		    String sql6="drop table zhandianzt";
		    		    String sql7="drop table jzxx1dl";
		    		    String sql8="create table jsdbdl as SELECT zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am, " +
		    		    		"dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk" +
		    		    		" AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"'  AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
		    		    		" and zd.property = 'zdsx02'";
		    		    String sql9="create table jsdbdl1 as SELECT AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz  FROM (SELECT TJ.ID," +
		    		    		"tj.dbydl,tj.hbzq,tj.bzz FROM JSDBDL TJ ) AA GROUP BY AA.ID";
		    		    String sql10="update jzxx j SET j.dbydl=(SELECT j1.js FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.daycount=(SELECT j1.hbzq " +
		    		    		"FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzz=(SELECT j1.bzz FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzw  = '1'" +
		    		    		" where j.zdid in(select to_char(j1.id) from jsdbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"' and j.property = 'zdsx02'";
		    		    
		    		    String sql11="drop table jsdbdl";
		    		    String sql12="drop table jsdbdl1";
		    		    
		    		    String sql13="create table jsdbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am," +
		    		    		" dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk " +
		    		    		"AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus <> '1'" +
		    		    		" and ef.manualauditstatus >= '1' and zd.property = 'zdsx02'";
		    		    String sql14="create table jsdbdl21 as select AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (" +
		    		    		"select TJ.ID,tj.dbydl,tj.hbzq,tj.bzz from jsdbdl2 tj) aa group by aa.id";
                        String sql15="update jzxx j set j.jsdl = 0, j.bzw = '2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdl21 j1)" +
		    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx02'";
		    		    
		    		    String sql16="drop table jsdbdl2";
		    		    String sql17="drop table jsdbdl21";
		    		    
		    		    String sql18="create table gldbdl as select  zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am " +
		    		    		"where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and am.manualauditstatus = '1' " +
		    		    		"and db.dbyt = 'dbyt03' AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"' and zd.property = 'zdsx02'";
		    	        String sql19="create table gldbdl1 as select aa.id,  SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from " +
		    	        		"(select tj.id, tj.dbydl,tj.hbzq,tj.bzz from gldbdl tj) aa group by aa.id";
		    		    String sql20="update jzxx j set j.dbgldl = (select j1.gl from gldbdl1 j1 where to_char(j1.id) = j.zdid),j.daycount = (select j1.hbzq " +
		    		    		"from gldbdl1 j1 where to_char(j1.id) = j.zdid), j.bzz = (select j1.bzz from gldbdl1 j1 where to_char(j1.id) = j.zdid) " +
		    		    		"where j.zdid in (select to_char(j1.id) from gldbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"'" +
		    		    		" and j.property = 'zdsx02'";
		    		    
		    		    String sql21="drop table gldbdl";
		    		    String sql22="drop table gldbdl1";
		    		    
		    		    String sql23="create table gldbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am" +
		    		    		" where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' " +
		    		    		"AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"'  and am.manualauditstatus <> '1' and db.dbyt = 'dbyt03' " +
		    		    		"and zd.property = 'zdsx02'";
		    		    String sql24="create table gldbdl21 as select aa.ID, SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (select tj.id," +
		    		    		"tj.dbydl,tj.hbzq,tj.bzz from gldbdl2 tj) aa group by aa.id";
		    		    String sql25="update jzxx j set j.gldl = 0 where j.gldl is null and j.zdid in (select to_char(j1.id) from gldbdl21 j1)" +
		    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx02'";
		    		    
		    		    String sql26="drop table gldbdl2";
		    		    String sql27="drop table gldbdl21";
		    		    
		    		    System.out.println("创建电表状态表zhandianzt: "+sql.toString());
		    		    oracledb.update(sql);
		    		    System.out.println("删除jzxx1表中当前月份数据"+sql2.toString());
		    		    oracledb.update(sql2);
		    		    System.out.println("插入jzxx1表当前月: "+sql3.toString());
		    		    oracledb.update(sql3);
		    		    System.out.println(" 创建jzxx1关于电费单全省定标电量、生产标、本地定标的中间表 +"+sql4.toString());
		    		    oracledb.update(sql4);
		    		    System.out.println(" 更新jzxx1  电费单对应生产标、本地定标、全省定标电量 +"+sql5.toString());
		    		    oracledb.update(sql5);
		    		    System.out.println(" 删除zhandianzt: +"+sql6.toString());
		    		    oracledb.update(sql6);
		    		    System.out.println(" 删除jzxx1dl: +"+sql7.toString());
		    		    oracledb.update(sql7);
		    		    System.out.println("创建电表用电量表: "+sql8.toString());
		    		    oracledb.update(sql8);
		    		    System.out.println("创建电表用电量jsdbdl1: "+sql9.toString());
		    		    oracledb.update(sql9);
		    		    System.out.println("更新jzxx1表电表用电量: "+sql10.toString());
		    		    oracledb.update(sql10);
		    		    System.out.println("删除jsdbdl: "+sql11.toString());
		    		    oracledb.update(sql11);
		    		    System.out.println("删除jsdbdl1: "+sql12.toString());
		    		    oracledb.update(sql12);
		    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl2: "+sql13.toString());
		    		    oracledb.update(sql13);
		    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl21:"+sql14.toString());
		    		    oracledb.update(sql14);
		    		    System.out.println("更新jzxx1表电表用电量为空的补0"+sql15.toString());
		    		    oracledb.update(sql15);
		    		    System.out.println("删除jsdbdl2: "+sql16.toString());
		    		    oracledb.update(sql16);
		    		    System.out.println("删除jsdbdl21: "+sql17.toString());
		    		    oracledb.update(sql17);
		    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl "+sql18.toString());
		    		    oracledb.update(sql18);
		    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl1 "+sql19.toString());
		    		    oracledb.update(sql19);
		    		    System.out.println("更新jzxx1管理电表用电量"+sql20.toString());
		    		    oracledb.update(sql20);
		    		    System.out.println("删除gldbdl"+sql21.toString());
		    		    oracledb.update(sql21);
		    		    System.out.println("删除gldbdl1"+sql22.toString());
		    		    oracledb.update(sql22);
		    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl2"+sql23.toString());
		    		    oracledb.update(sql23);
		    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl21: "+sql24.toString());
		    		    oracledb.update(sql24);
		    		    System.out.println("更新jzxx1管理为空的补0: "+sql25.toString());
		    		    oracledb.update(sql25);
		    		    System.out.println("删除gldbdl2: "+sql26.toString());
		    		    oracledb.update(sql26);
		    		    System.out.println("删除gldbdl21: "+sql27.toString());
		    		    oracledb.update(sql27);
		    		    System.out.println("-----------------------基站更新完毕-------------------------------------");
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
			        String sql="create  table  zhandianjyjf as select zd.id, sum(decode(db.dbqyzt, '0', 0, 1)) as jj from zhandian zd, dianbiao db " +
	                "where zd.id = db.zdid and zd.qyzt = '1' and zd.caiji = '0' AND zd.shsign='1' and zd.property = 'zdsx01' group by zd.ID";
	                
	                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx01'";
	    		    String sql3 ="insert into jzxx(symonth,  shi, xian, xiaoqu, zdid, jzname, g2, g3,changjia,zp, zs, gxgwsl,jztype, shebei," +
	    		    		" ydshebei,sfgx, dbydl, dbgldl, zdqsdbdl, daycount, scb, edhdl, bzw, property, bzz, zdscb,zdedhdl, zddqsdbdl, " +
	    		    		"zlfh, jlfh, zdjlfh, zdzlfh) select '"+yf+"', bb.agname, '', '', bb.id, bb.jzname, '', '','', '', '','', ''," +
	    		    		" '', '','', '', '',  bb.qsdbdl, '', bb.scb,  bb.edhdl, '', 'zdsx01',  '', bb.scb, bb.edhdl, bb.qsdbdl, bb.zlfh, bb.jlfh,  bb.jlfh,  bb.zlfh " +
	    		    		" from (select aa.shi, aa.agname, aa.id, aa.jzname, aa.qsdbdl, aa.edhdl,aa.scb,aa.zlfh,aa.jlfh from " +
	    		    		"(select zd.shi, d.agname,  zd.id, zd.jzname,db.scb, zd.qsdbdl,  zd.edhdl,zd.zlfh,zd.jlfh from zhandian zd " +
	    		    		"left join d_area_grade d on zd.shi = d.agcode left join yfxs dbyf on zd.shi = dbyf.shicode, scb db  " +
	    		    		"where zd.id = db.zdid and zd.caiji = '0' and zd.qyzt = '1' AND zd.xuni='0' AND zd.shsign='1' and " +
	    		    		"zd.property = 'zdsx01') aa) bb, zhandianjyjf zz where bb.id = zz.id and zz.jj <> 0";
	    		    String sql4="create table jzxx1dl as SELECT  zd.ID,ef.accountmonth,min(ef.edhdl)edhdl,min(ef.qsdbdl)qsdbdl,min(am.scb)scb," +
	    		    		"MIN(ef.zlfh)zlfh,MIN(ef.jlfh) jlfh from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where " +
	    		    		"zd.id = db.zdid  and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk AND " +
	    		    		" to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
	    		    		" and zd.property = 'zdsx01' GROUP BY zd.ID,ef.accountmonth";
	    		    String sql5="UPDATE jzxx J SET J.SCB      = (SELECT D.SCB FROM jzxx1DL D WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"'), " +
	    		    		"J.ZDQSDBDL = (SELECT D.QSDBDL FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
	    		    		"J.EDHDL    = (SELECT D.EDHDL FROM jzxx1DL D  WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
	    		    		"J.zlfh    = (SELECT D.zlfh  FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
	    		    		"J.jlfh    = (SELECT D.jlfh FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"') " +
	    		    		"WHERE J.ZDID IN (SELECT D.ID FROM jzxx1DL D  WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"') AND J.PROPERTY='zdsx01'  AND  J.SYMONTH = '"+yf+"'";
	    		    String sql6="drop table zhandianjyjf";
	    		    String sql7="drop table jzxx1dl";
	    		    String sql8="create table jsdbdl as SELECT zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am, " +
	    		    		"dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk" +
	    		    		" AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"'  AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
	    		    		" and zd.property = 'zdsx01'";
	    		    String sql9="create table jsdbdl1 as SELECT AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz  FROM (SELECT TJ.ID," +
	    		    		"tj.dbydl,tj.hbzq,tj.bzz FROM JSDBDL TJ ) AA GROUP BY AA.ID";
	    		    String sql10="update jzxx j SET j.dbydl=(SELECT j1.js FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.daycount=(SELECT j1.hbzq " +
	    		    		"FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzz=(SELECT j1.bzz FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzw  = '1'" +
	    		    		" where j.zdid in(select to_char(j1.id) from jsdbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"' and j.property = 'zdsx01'";
	    		    
	    		    String sql11="drop table jsdbdl";
	    		    String sql12="drop table jsdbdl1";
	    		    
	    		    String sql13="create table jsdbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am," +
	    		    		" dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk " +
	    		    		"AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus <> '1'" +
	    		    		" and ef.manualauditstatus >= '1' and zd.property = 'zdsx01'";
	    		    String sql14="create table jsdbdl21 as select AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (" +
	    		    		"select TJ.ID,tj.dbydl,tj.hbzq,tj.bzz from jsdbdl2 tj) aa group by aa.id";
                    String sql15="update jzxx j set j.jsdl = 0, j.bzw = '2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdl21 j1)" +
	    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx01'";
	    		    
	    		    String sql16="drop table jsdbdl2";
	    		    String sql17="drop table jsdbdl21";
	    		    
	    		    String sql18="create table gldbdl as select  zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am " +
	    		    		"where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and am.manualauditstatus = '1' " +
	    		    		"and db.dbyt = 'dbyt03' AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"' and zd.property = 'zdsx01'";
	    	        String sql19="create table gldbdl1 as select aa.id,  SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from " +
	    	        		"(select tj.id, tj.dbydl,tj.hbzq,tj.bzz from gldbdl tj) aa group by aa.id";
	    		    String sql20="update jzxx j set j.dbgldl = (select j1.gl from gldbdl1 j1 where to_char(j1.id) = j.zdid),j.daycount = (select j1.hbzq " +
	    		    		"from gldbdl1 j1 where to_char(j1.id) = j.zdid), j.bzz = (select j1.bzz from gldbdl1 j1 where to_char(j1.id) = j.zdid) " +
	    		    		"where j.zdid in (select to_char(j1.id) from gldbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"'" +
	    		    		" and j.property = 'zdsx01'";
	    		    
	    		    String sql21="drop table gldbdl";
	    		    String sql22="drop table gldbdl1";
	    		    
	    		    String sql23="create table gldbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am" +
	    		    		" where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' " +
	    		    		"AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"'  and am.manualauditstatus <> '1' and db.dbyt = 'dbyt03' " +
	    		    		"and zd.property = 'zdsx01'";
	    		    String sql24="create table gldbdl21 as select aa.ID, SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (select tj.id," +
	    		    		"tj.dbydl,tj.hbzq,tj.bzz from gldbdl2 tj) aa group by aa.id";
	    		    String sql25="update jzxx j set j.gldl = 0 where j.gldl is null and j.zdid in (select to_char(j1.id) from gldbdl21 j1)" +
	    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx01'";
	    		    
	    		    String sql26="drop table gldbdl2";
	    		    String sql27="drop table gldbdl21";
	    		    
	    		    System.out.println("创建电表状态表zhandianjyjf: "+sql.toString());
	    		    oracledb.update(sql);
	    		    System.out.println("删除jzxx1表中当前月份数据"+sql2.toString());
	    		    oracledb.update(sql2);
	    		    System.out.println("插入jzxx1表当前月: "+sql3.toString());
	    		    oracledb.update(sql3);
	    		    System.out.println(" 创建jzxx1关于电费单全省定标电量、生产标、本地定标的中间表 +"+sql4.toString());
	    		    oracledb.update(sql4);
	    		    System.out.println(" 更新jzxx1  电费单对应生产标、本地定标、全省定标电量 +"+sql5.toString());
	    		    oracledb.update(sql5);
	    		    System.out.println(" 删除zhandianjyjf: +"+sql6.toString());
	    		    oracledb.update(sql6);
	    		    System.out.println(" 删除jzxx1dl: +"+sql7.toString());
	    		    oracledb.update(sql7);
	    		    System.out.println("创建电表用电量表: "+sql8.toString());
	    		    oracledb.update(sql8);
	    		    System.out.println("创建电表用电量jsdbdl1: "+sql9.toString());
	    		    oracledb.update(sql9);
	    		    System.out.println("更新jzxx1表电表用电量: "+sql10.toString());
	    		    oracledb.update(sql10);
	    		    System.out.println("删除jsdbdl: "+sql11.toString());
	    		    oracledb.update(sql11);
	    		    System.out.println("删除jsdbdl1: "+sql12.toString());
	    		    oracledb.update(sql12);
	    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl2: "+sql13.toString());
	    		    oracledb.update(sql13);
	    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl21:"+sql14.toString());
	    		    oracledb.update(sql14);
	    		    System.out.println("更新jzxx1表电表用电量为空的补0"+sql15.toString());
	    		    oracledb.update(sql15);
	    		    System.out.println("删除jsdbdl2: "+sql16.toString());
	    		    oracledb.update(sql16);
	    		    System.out.println("删除jsdbdl21: "+sql17.toString());
	    		    oracledb.update(sql17);
	    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl "+sql18.toString());
	    		    oracledb.update(sql18);
	    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl1 "+sql19.toString());
	    		    oracledb.update(sql19);
	    		    System.out.println("更新jzxx1管理电表用电量"+sql20.toString());
	    		    oracledb.update(sql20);
	    		    System.out.println("删除gldbdl"+sql21.toString());
	    		    oracledb.update(sql21);
	    		    System.out.println("删除gldbdl1"+sql22.toString());
	    		    oracledb.update(sql22);
	    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl2"+sql23.toString());
	    		    oracledb.update(sql23);
	    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl21: "+sql24.toString());
	    		    oracledb.update(sql24);
	    		    System.out.println("更新jzxx1管理为空的补0: "+sql25.toString());
	    		    oracledb.update(sql25);
	    		    System.out.println("删除gldbdl2: "+sql26.toString());
	    		    oracledb.update(sql26);
	    		    System.out.println("删除gldbdl21: "+sql27.toString());
	    		    oracledb.update(sql27);
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
			            	 String sql="create  table  zhandianyywd as select zd.id, sum(decode(db.dbqyzt, '0', 0, 1)) as jj from zhandian zd, dianbiao db " +
				                "where zd.id = db.zdid and zd.qyzt = '1' and zd.caiji = '0' AND zd.shsign='1' and zd.property = 'zdsx03' group by zd.ID";
				                
				                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx03'";
				    		    String sql3 ="insert into jzxx(symonth,  shi, xian, xiaoqu, zdid, jzname, g2, g3,changjia,zp, zs, gxgwsl,jztype, shebei," +
				    		    		" ydshebei,sfgx, dbydl, dbgldl, zdqsdbdl, daycount, scb, edhdl, bzw, property, bzz, zdscb,zdedhdl, zddqsdbdl, " +
				    		    		"zlfh, jlfh, zdjlfh, zdzlfh) select '"+yf+"', bb.agname, '', '', bb.id, bb.jzname, '', '','', '', '','', ''," +
				    		    		" '', '','', '', '', bb.qsdbdl, '', bb.scb,  bb.edhdl, '', 'zdsx03',  '', bb.scb, bb.edhdl, bb.qsdbdl, bb.zlfh, bb.jlfh,  bb.jlfh,  bb.zlfh " +
				    		    		" from (select aa.shi, aa.agname, aa.id, aa.jzname, aa.qsdbdl, aa.edhdl,aa.scb,aa.zlfh,aa.jlfh from " +
				    		    		"(select zd.shi, d.agname,  zd.id, zd.jzname,db.scb, zd.qsdbdl,  zd.edhdl,zd.zlfh,zd.jlfh from zhandian zd " +
				    		    		"left join d_area_grade d on zd.shi = d.agcode left join yfxs dbyf on zd.shi = dbyf.shicode, scb db  " +
				    		    		"where zd.id = db.zdid and zd.caiji = '0' and zd.qyzt = '1' AND zd.xuni='0' AND zd.shsign='1' and " +
				    		    		"zd.property = 'zdsx03') aa) bb, zhandianyywd zz where bb.id = zz.id and zz.jj <> 0";
				    		    String sql4="create table jzxx1dl as SELECT  zd.ID,ef.accountmonth,min(ef.edhdl)edhdl,min(ef.qsdbdl)qsdbdl,min(am.scb)scb," +
				    		    		"MIN(ef.zlfh)zlfh,MIN(ef.jlfh) jlfh from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where " +
				    		    		"zd.id = db.zdid  and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk AND " +
				    		    		" to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
				    		    		" and zd.property = 'zdsx03' GROUP BY zd.ID,ef.accountmonth";
				    		    String sql5="UPDATE jzxx J SET J.SCB      = (SELECT D.SCB FROM jzxx1DL D WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"'), " +
				    		    		"J.ZDQSDBDL = (SELECT D.QSDBDL FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
				    		    		"J.EDHDL    = (SELECT D.EDHDL FROM jzxx1DL D  WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
				    		    		"J.zlfh    = (SELECT D.zlfh  FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
				    		    		"J.jlfh    = (SELECT D.jlfh FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"') " +
				    		    		"WHERE J.ZDID IN (SELECT D.ID FROM jzxx1DL D  WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"')  AND J.PROPERTY='zdsx03'  AND J.SYMONTH = '"+yf+"'";
				    		    String sql6="drop table zhandianyywd";
				    		    String sql7="drop table jzxx1dl";
				    		    String sql8="create table jsdbdl as SELECT zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am, " +
				    		    		"dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk" +
				    		    		" AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"'  AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
				    		    		" and zd.property = 'zdsx03'";
				    		    String sql9="create table jsdbdl1 as SELECT AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz  FROM (SELECT TJ.ID," +
				    		    		"tj.dbydl,tj.hbzq,tj.bzz FROM JSDBDL TJ ) AA GROUP BY AA.ID";
				    		    String sql10="update jzxx j SET j.dbydl=(SELECT j1.js FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.daycount=(SELECT j1.hbzq " +
				    		    		"FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzz=(SELECT j1.bzz FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzw  = '1'" +
				    		    		" where j.zdid in(select to_char(j1.id) from jsdbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"' and j.property = 'zdsx03'";
				    		    
				    		    String sql11="drop table jsdbdl";
				    		    String sql12="drop table jsdbdl1";
				    		    
				    		    String sql13="create table jsdbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am," +
				    		    		" dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk " +
				    		    		"AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus <> '1'" +
				    		    		" and ef.manualauditstatus >= '1' and zd.property = 'zdsx03'";
				    		    String sql14="create table jsdbdl21 as select AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (" +
				    		    		"select TJ.ID,tj.dbydl,tj.hbzq,tj.bzz from jsdbdl2 tj) aa group by aa.id";
			                    String sql15="update jzxx j set j.jsdl = 0, j.bzw = '2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdl21 j1)" +
				    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx03'";
				    		    
				    		    String sql16="drop table jsdbdl2";
				    		    String sql17="drop table jsdbdl21";
				    		    
				    		    String sql18="create table gldbdl as select  zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am " +
				    		    		"where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and am.manualauditstatus = '1' " +
				    		    		"and db.dbyt = 'dbyt03' AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"' and zd.property = 'zdsx03'";
				    	        String sql19="create table gldbdl1 as select aa.id,  SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from " +
				    	        		"(select tj.id, tj.dbydl,tj.hbzq,tj.bzz from gldbdl tj) aa group by aa.id";
				    		    String sql20="update jzxx j set j.dbgldl = (select j1.gl from gldbdl1 j1 where to_char(j1.id) = j.zdid),j.daycount = (select j1.hbzq " +
				    		    		"from gldbdl1 j1 where to_char(j1.id) = j.zdid), j.bzz = (select j1.bzz from gldbdl1 j1 where to_char(j1.id) = j.zdid) " +
				    		    		"where j.zdid in (select to_char(j1.id) from gldbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"'" +
				    		    		" and j.property = 'zdsx03'";
				    		    
				    		    String sql21="drop table gldbdl";
				    		    String sql22="drop table gldbdl1";
				    		    
				    		    String sql23="create table gldbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am" +
				    		    		" where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' " +
				    		    		"AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"'  and am.manualauditstatus <> '1' and db.dbyt = 'dbyt03' " +
				    		    		"and zd.property = 'zdsx03'";
				    		    String sql24="create table gldbdl21 as select aa.ID, SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (select tj.id," +
				    		    		"tj.dbydl,tj.hbzq,tj.bzz from gldbdl2 tj) aa group by aa.id";
				    		    String sql25="update jzxx j set j.gldl = 0 where j.gldl is null and j.zdid in (select to_char(j1.id) from gldbdl21 j1)" +
				    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx03'";
				    		    
				    		    String sql26="drop table gldbdl2";
				    		    String sql27="drop table gldbdl21";
				    		    
				    		    System.out.println("创建电表状态表zhandianyywd: "+sql.toString());
				    		    oracledb.update(sql);
				    		    System.out.println("删除jzxx1表中当前月份数据"+sql2.toString());
				    		    oracledb.update(sql2);
				    		    System.out.println("插入jzxx1表当前月: "+sql3.toString());
				    		    oracledb.update(sql3);
				    		    System.out.println(" 创建jzxx1关于电费单全省定标电量、生产标、本地定标的中间表 +"+sql4.toString());
				    		    oracledb.update(sql4);
				    		    System.out.println(" 更新jzxx1  电费单对应生产标、本地定标、全省定标电量 +"+sql5.toString());
				    		    oracledb.update(sql5);
				    		    System.out.println(" 删除zhandianyywd: +"+sql6.toString());
				    		    oracledb.update(sql6);
				    		    System.out.println(" 删除jzxx1dl: +"+sql7.toString());
				    		    oracledb.update(sql7);
				    		    System.out.println("创建电表用电量表: "+sql8.toString());
				    		    oracledb.update(sql8);
				    		    System.out.println("创建电表用电量jsdbdl1: "+sql9.toString());
				    		    oracledb.update(sql9);
				    		    System.out.println("更新jzxx1表电表用电量: "+sql10.toString());
				    		    oracledb.update(sql10);
				    		    System.out.println("删除jsdbdl: "+sql11.toString());
				    		    oracledb.update(sql11);
				    		    System.out.println("删除jsdbdl1: "+sql12.toString());
				    		    oracledb.update(sql12);
				    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl2: "+sql13.toString());
				    		    oracledb.update(sql13);
				    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl21:"+sql14.toString());
				    		    oracledb.update(sql14);
				    		    System.out.println("更新jzxx1表电表用电量为空的补0"+sql15.toString());
				    		    oracledb.update(sql15);
				    		    System.out.println("删除jsdbdl2: "+sql16.toString());
				    		    oracledb.update(sql16);
				    		    System.out.println("删除jsdbdl21: "+sql17.toString());
				    		    oracledb.update(sql17);
				    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl "+sql18.toString());
				    		    oracledb.update(sql18);
				    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl1 "+sql19.toString());
				    		    oracledb.update(sql19);
				    		    System.out.println("更新jzxx1管理电表用电量"+sql20.toString());
				    		    oracledb.update(sql20);
				    		    System.out.println("删除gldbdl"+sql21.toString());
				    		    oracledb.update(sql21);
				    		    System.out.println("删除gldbdl1"+sql22.toString());
				    		    oracledb.update(sql22);
				    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl2"+sql23.toString());
				    		    oracledb.update(sql23);
				    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl21: "+sql24.toString());
				    		    oracledb.update(sql24);
				    		    System.out.println("更新jzxx1管理为空的补0: "+sql25.toString());
				    		    oracledb.update(sql25);
				    		    System.out.println("删除gldbdl2: "+sql26.toString());
				    		    oracledb.update(sql26);
				    		    System.out.println("删除gldbdl21: "+sql27.toString());
				    		    oracledb.update(sql27);
			    		    System.out.println("-------------------------营业网点更新完毕-----------------------------------");
			    		    //oracledb.commit();
			            	}
			             }
		             if (((Integer.parseInt(nowHour)==1))){//zdsx01 其他更新 1点
			            	String sq="select * from khxx h where h.bzmonth='"+yf+"' and h.property='zdsx04'";
			                System.out.println("当前月有没有保存"+sq);
			            	ResultSet r=oracledb.queryAll(sq);
			            	while(r.next()){
			    				i=1;
			    			}
			            if(i==0){	
					        //oracledb.setAutoCommit(false);
			            	 String sql="create  table  zhandianqt as select zd.id, sum(decode(db.dbqyzt, '0', 0, 1)) as jj from zhandian zd, dianbiao db " +
				                "where zd.id = db.zdid and zd.qyzt = '1' and zd.caiji = '0' AND zd.shsign='1' and zd.property = 'zdsx04' group by zd.ID";
				                
				                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx04'";
				    		    String sql3 ="insert into jzxx(symonth,  shi, xian, xiaoqu, zdid, jzname, g2, g3,changjia,zp, zs, gxgwsl,jztype, shebei," +
				    		    		" ydshebei,sfgx, dbydl, dbgldl, zdqsdbdl, daycount, scb, edhdl, bzw, property, bzz, zdscb,zdedhdl, zddqsdbdl, " +
				    		    		"zlfh, jlfh, zdjlfh, zdzlfh) select '"+yf+"', bb.agname, '', '', bb.id, bb.jzname, '', '','', '', '','', ''," +
				    		    		" '', '','', '', '',  bb.qsdbdl, '', bb.scb,  bb.edhdl, '', 'zdsx04',  '', bb.scb, bb.edhdl, bb.qsdbdl, bb.zlfh, bb.jlfh,  bb.jlfh,  bb.zlfh " +
				    		    		" from (select aa.shi, aa.agname, aa.id, aa.jzname, aa.qsdbdl, aa.edhdl,aa.scb,aa.zlfh,aa.jlfh from " +
				    		    		"(select zd.shi, d.agname,  zd.id, zd.jzname,db.scb, zd.qsdbdl,  zd.edhdl,zd.zlfh,zd.jlfh from zhandian zd " +
				    		    		"left join d_area_grade d on zd.shi = d.agcode left join yfxs dbyf on zd.shi = dbyf.shicode, scb db  " +
				    		    		"where zd.id = db.zdid and zd.caiji = '0' and zd.qyzt = '1' AND zd.xuni='0' AND zd.shsign='1' and " +
				    		    		"zd.property = 'zdsx04') aa) bb, zhandianqt zz where bb.id = zz.id and zz.jj <> 0";
				    		    String sql4="create table jzxx1dl as SELECT  zd.ID,ef.accountmonth,min(ef.edhdl)edhdl,min(ef.qsdbdl)qsdbdl,min(am.scb)scb," +
				    		    		"MIN(ef.zlfh)zlfh,MIN(ef.jlfh) jlfh from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where " +
				    		    		"zd.id = db.zdid  and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk AND " +
				    		    		" to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
				    		    		" and zd.property = 'zdsx04' GROUP BY zd.ID,ef.accountmonth";
				    		    String sql5="UPDATE jzxx J SET J.SCB      = (SELECT D.SCB FROM jzxx1DL D WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"'), " +
				    		    		"J.ZDQSDBDL = (SELECT D.QSDBDL FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
				    		    		"J.EDHDL    = (SELECT D.EDHDL FROM jzxx1DL D  WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
				    		    		"J.zlfh    = (SELECT D.zlfh  FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
				    		    		"J.jlfh    = (SELECT D.jlfh FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"') " +
				    		    		"WHERE J.ZDID IN (SELECT D.ID FROM jzxx1DL D  WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"') AND J.PROPERTY='zdsx04'  AND J.SYMONTH = '"+yf+"'";
				    		    String sql6="drop table zhandianqt";
				    		    String sql7="drop table jzxx1dl";
				    		    String sql8="create table jsdbdl as SELECT zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am, " +
				    		    		"dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk" +
				    		    		" AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"'  AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
				    		    		" and zd.property = 'zdsx04'";
				    		    String sql9="create table jsdbdl1 as SELECT AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz  FROM (SELECT TJ.ID," +
				    		    		"tj.dbydl,tj.hbzq,tj.bzz FROM JSDBDL TJ ) AA GROUP BY AA.ID";
				    		    String sql10="update jzxx j SET j.dbydl=(SELECT j1.js FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.daycount=(SELECT j1.hbzq " +
				    		    		"FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzz=(SELECT j1.bzz FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzw  = '1'" +
				    		    		" where j.zdid in(select to_char(j1.id) from jsdbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"' and j.property = 'zdsx04'";
				    		    
				    		    String sql11="drop table jsdbdl";
				    		    String sql12="drop table jsdbdl1";
				    		    
				    		    String sql13="create table jsdbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am," +
				    		    		" dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk " +
				    		    		"AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus <> '1'" +
				    		    		" and ef.manualauditstatus >= '1' and zd.property = 'zdsx04'";
				    		    String sql14="create table jsdbdl21 as select AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (" +
				    		    		"select TJ.ID,tj.dbydl,tj.hbzq,tj.bzz from jsdbdl2 tj) aa group by aa.id";
			                    String sql15="update jzxx j set j.jsdl = 0, j.bzw = '2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdl21 j1)" +
				    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx04'";
				    		    
				    		    String sql16="drop table jsdbdl2";
				    		    String sql17="drop table jsdbdl21";
				    		    
				    		    String sql18="create table gldbdl as select  zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am " +
				    		    		"where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and am.manualauditstatus = '1' " +
				    		    		"and db.dbyt = 'dbyt03' AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"' and zd.property = 'zdsx04'";
				    	        String sql19="create table gldbdl1 as select aa.id,  SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from " +
				    	        		"(select tj.id, tj.dbydl,tj.hbzq,tj.bzz from gldbdl tj) aa group by aa.id";
				    		    String sql20="update jzxx j set j.dbgldl = (select j1.gl from gldbdl1 j1 where to_char(j1.id) = j.zdid),j.daycount = (select j1.hbzq " +
				    		    		"from gldbdl1 j1 where to_char(j1.id) = j.zdid), j.bzz = (select j1.bzz from gldbdl1 j1 where to_char(j1.id) = j.zdid) " +
				    		    		"where j.zdid in (select to_char(j1.id) from gldbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"'" +
				    		    		" and j.property = 'zdsx04'";
				    		    
				    		    String sql21="drop table gldbdl";
				    		    String sql22="drop table gldbdl1";
				    		    
				    		    String sql23="create table gldbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am" +
				    		    		" where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' " +
				    		    		"AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"'  and am.manualauditstatus <> '1' and db.dbyt = 'dbyt03' " +
				    		    		"and zd.property = 'zdsx04'";
				    		    String sql24="create table gldbdl21 as select aa.ID, SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (select tj.id," +
				    		    		"tj.dbydl,tj.hbzq,tj.bzz from gldbdl2 tj) aa group by aa.id";
				    		    String sql25="update jzxx j set j.gldl = 0 where j.gldl is null and j.zdid in (select to_char(j1.id) from gldbdl21 j1)" +
				    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx04'";
				    		    
				    		    String sql26="drop table gldbdl2";
				    		    String sql27="drop table gldbdl21";
				    		    
				    		    System.out.println("创建电表状态表zhandianqt: "+sql.toString());
				    		    oracledb.update(sql);
				    		    System.out.println("删除jzxx1表中当前月份数据"+sql2.toString());
				    		    oracledb.update(sql2);
				    		    System.out.println("插入jzxx1表当前月: "+sql3.toString());
				    		    oracledb.update(sql3);
				    		    System.out.println(" 创建jzxx1关于电费单全省定标电量、生产标、本地定标的中间表 +"+sql4.toString());
				    		    oracledb.update(sql4);
				    		    System.out.println(" 更新jzxx1  电费单对应生产标、本地定标、全省定标电量 +"+sql5.toString());
				    		    oracledb.update(sql5);
				    		    System.out.println(" 删除zhandianqt: +"+sql6.toString());
				    		    oracledb.update(sql6);
				    		    System.out.println(" 删除jzxx1dl: +"+sql7.toString());
				    		    oracledb.update(sql7);
				    		    System.out.println("创建电表用电量表: "+sql8.toString());
				    		    oracledb.update(sql8);
				    		    System.out.println("创建电表用电量jsdbdl1: "+sql9.toString());
				    		    oracledb.update(sql9);
				    		    System.out.println("更新jzxx1表电表用电量: "+sql10.toString());
				    		    oracledb.update(sql10);
				    		    System.out.println("删除jsdbdl: "+sql11.toString());
				    		    oracledb.update(sql11);
				    		    System.out.println("删除jsdbdl1: "+sql12.toString());
				    		    oracledb.update(sql12);
				    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl2: "+sql13.toString());
				    		    oracledb.update(sql13);
				    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl21:"+sql14.toString());
				    		    oracledb.update(sql14);
				    		    System.out.println("更新jzxx1表电表用电量为空的补0"+sql15.toString());
				    		    oracledb.update(sql15);
				    		    System.out.println("删除jsdbdl2: "+sql16.toString());
				    		    oracledb.update(sql16);
				    		    System.out.println("删除jsdbdl21: "+sql17.toString());
				    		    oracledb.update(sql17);
				    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl "+sql18.toString());
				    		    oracledb.update(sql18);
				    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl1 "+sql19.toString());
				    		    oracledb.update(sql19);
				    		    System.out.println("更新jzxx1管理电表用电量"+sql20.toString());
				    		    oracledb.update(sql20);
				    		    System.out.println("删除gldbdl"+sql21.toString());
				    		    oracledb.update(sql21);
				    		    System.out.println("删除gldbdl1"+sql22.toString());
				    		    oracledb.update(sql22);
				    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl2"+sql23.toString());
				    		    oracledb.update(sql23);
				    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl21: "+sql24.toString());
				    		    oracledb.update(sql24);
				    		    System.out.println("更新jzxx1管理为空的补0: "+sql25.toString());
				    		    oracledb.update(sql25);
				    		    System.out.println("删除gldbdl2: "+sql26.toString());
				    		    oracledb.update(sql26);
				    		    System.out.println("删除gldbdl21: "+sql27.toString());
				    		    oracledb.update(sql27);
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
			            	String sql="create  table  zhandianxzzj as select zd.id, sum(decode(db.dbqyzt, '0', 0, 1)) as jj from zhandian zd, dianbiao db " +
			                "where zd.id = db.zdid and zd.qyzt = '1' and zd.caiji = '0' AND zd.shsign='1' and zd.property = 'zdsx06' group by zd.ID";
			                
			                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx06'";
			    		    String sql3 ="insert into jzxx(symonth,  shi, xian, xiaoqu, zdid, jzname, g2, g3,changjia,zp, zs, gxgwsl,jztype, shebei," +
			    		    		" ydshebei,sfgx, dbydl, dbgldl, zdqsdbdl, daycount, scb, edhdl, bzw, property, bzz, zdscb,zdedhdl, zddqsdbdl, " +
			    		    		"zlfh, jlfh, zdjlfh, zdzlfh) select '"+yf+"', bb.agname, '', '', bb.id, bb.jzname, '', '','', '', '','', ''," +
			    		    		" '', '','', '', '',  bb.qsdbdl, '', bb.scb,  bb.edhdl, '', 'zdsx06',  '', bb.scb, bb.edhdl, bb.qsdbdl, bb.zlfh, bb.jlfh,  bb.jlfh,  bb.zlfh " +
			    		    		" from (select aa.shi, aa.agname, aa.id, aa.jzname, aa.qsdbdl, aa.edhdl,aa.scb,aa.zlfh,aa.jlfh from " +
			    		    		"(select zd.shi, d.agname,  zd.id, zd.jzname,db.scb, zd.qsdbdl,  zd.edhdl,zd.zlfh,zd.jlfh from zhandian zd " +
			    		    		"left join d_area_grade d on zd.shi = d.agcode left join yfxs dbyf on zd.shi = dbyf.shicode, scb db  " +
			    		    		"where zd.id = db.zdid and zd.caiji = '0' and zd.qyzt = '1' AND zd.xuni='0' AND zd.shsign='1' and " +
			    		    		"zd.property = 'zdsx06') aa) bb, zhandianxzzj zz where bb.id = zz.id and zz.jj <> 0";
			    		    String sql4="create table jzxx1dl as SELECT  zd.ID,ef.accountmonth,min(ef.edhdl)edhdl,min(ef.qsdbdl)qsdbdl,min(am.scb)scb," +
			    		    		"MIN(ef.zlfh)zlfh,MIN(ef.jlfh) jlfh from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where " +
			    		    		"zd.id = db.zdid  and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk AND " +
			    		    		" to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
			    		    		" and zd.property = 'zdsx06' GROUP BY zd.ID,ef.accountmonth";
			    		    String sql5="UPDATE jzxx J SET J.SCB      = (SELECT D.SCB FROM jzxx1DL D WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"'), " +
			    		    		"J.ZDQSDBDL = (SELECT D.QSDBDL FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
			    		    		"J.EDHDL    = (SELECT D.EDHDL FROM jzxx1DL D  WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
			    		    		"J.zlfh    = (SELECT D.zlfh  FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
			    		    		"J.jlfh    = (SELECT D.jlfh FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"') " +
			    		    		"WHERE J.ZDID IN (SELECT D.ID FROM jzxx1DL D  WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"') AND J.PROPERTY='zdsx06' AND J.SYMONTH = '"+yf+"'";
			    		    String sql6="drop table zhandianxzzj";
			    		    String sql7="drop table jzxx1dl";
			    		    String sql8="create table jsdbdl as SELECT zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am, " +
			    		    		"dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk" +
			    		    		" AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"'  AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
			    		    		" and zd.property = 'zdsx06'";
			    		    String sql9="create table jsdbdl1 as SELECT AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz  FROM (SELECT TJ.ID," +
			    		    		"tj.dbydl,tj.hbzq,tj.bzz FROM JSDBDL TJ ) AA GROUP BY AA.ID";
			    		    String sql10="update jzxx j SET j.dbydl=(SELECT j1.js FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.daycount=(SELECT j1.hbzq " +
			    		    		"FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzz=(SELECT j1.bzz FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzw  = '1'" +
			    		    		" where j.zdid in(select to_char(j1.id) from jsdbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"' and j.property = 'zdsx06'";
			    		    
			    		    String sql11="drop table jsdbdl";
			    		    String sql12="drop table jsdbdl1";
			    		    
			    		    String sql13="create table jsdbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am," +
			    		    		" dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk " +
			    		    		"AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus <> '1'" +
			    		    		" and ef.manualauditstatus >= '1' and zd.property = 'zdsx06'";
			    		    String sql14="create table jsdbdl21 as select AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (" +
			    		    		"select TJ.ID,tj.dbydl,tj.hbzq,tj.bzz from jsdbdl2 tj) aa group by aa.id";
		                    String sql15="update jzxx j set j.jsdl = 0, j.bzw = '2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdl21 j1)" +
			    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx06'";
			    		    
			    		    String sql16="drop table jsdbdl2";
			    		    String sql17="drop table jsdbdl21";
			    		    
			    		    String sql18="create table gldbdl as select  zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am " +
			    		    		"where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and am.manualauditstatus = '1' " +
			    		    		"and db.dbyt = 'dbyt03' AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"' and zd.property = 'zdsx06'";
			    	        String sql19="create table gldbdl1 as select aa.id,  SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from " +
			    	        		"(select tj.id, tj.dbydl,tj.hbzq,tj.bzz from gldbdl tj) aa group by aa.id";
			    		    String sql20="update jzxx j set j.dbgldl = (select j1.gl from gldbdl1 j1 where to_char(j1.id) = j.zdid),j.daycount = (select j1.hbzq " +
			    		    		"from gldbdl1 j1 where to_char(j1.id) = j.zdid), j.bzz = (select j1.bzz from gldbdl1 j1 where to_char(j1.id) = j.zdid) " +
			    		    		"where j.zdid in (select to_char(j1.id) from gldbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"'" +
			    		    		" and j.property = 'zdsx06'";
			    		    
			    		    String sql21="drop table gldbdl";
			    		    String sql22="drop table gldbdl1";
			    		    
			    		    String sql23="create table gldbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am" +
			    		    		" where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' " +
			    		    		"AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"'  and am.manualauditstatus <> '1' and db.dbyt = 'dbyt03' " +
			    		    		"and zd.property = 'zdsx06'";
			    		    String sql24="create table gldbdl21 as select aa.ID, SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (select tj.id," +
			    		    		"tj.dbydl,tj.hbzq,tj.bzz from gldbdl2 tj) aa group by aa.id";
			    		    String sql25="update jzxx j set j.gldl = 0 where j.gldl is null and j.zdid in (select to_char(j1.id) from gldbdl21 j1)" +
			    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx06'";
			    		    
			    		    String sql26="drop table gldbdl2";
			    		    String sql27="drop table gldbdl21";
			    		    
			    		    System.out.println("创建电表状态表zhandianxzzj: "+sql.toString());
			    		    oracledb.update(sql);
			    		    System.out.println("删除jzxx1表中当前月份数据"+sql2.toString());
			    		    oracledb.update(sql2);
			    		    System.out.println("插入jzxx1表当前月: "+sql3.toString());
			    		    oracledb.update(sql3);
			    		    System.out.println(" 创建jzxx1关于电费单全省定标电量、生产标、本地定标的中间表 +"+sql4.toString());
			    		    oracledb.update(sql4);
			    		    System.out.println(" 更新jzxx1  电费单对应生产标、本地定标、全省定标电量 +"+sql5.toString());
			    		    oracledb.update(sql5);
			    		    System.out.println(" 删除zhandianxzzj: +"+sql6.toString());
			    		    oracledb.update(sql6);
			    		    System.out.println(" 删除jzxx1dl: +"+sql7.toString());
			    		    oracledb.update(sql7);
			    		    System.out.println("创建电表用电量表: "+sql8.toString());
			    		    oracledb.update(sql8);
			    		    System.out.println("创建电表用电量jsdbdl1: "+sql9.toString());
			    		    oracledb.update(sql9);
			    		    System.out.println("更新jzxx1表电表用电量: "+sql10.toString());
			    		    oracledb.update(sql10);
			    		    System.out.println("删除jsdbdl: "+sql11.toString());
			    		    oracledb.update(sql11);
			    		    System.out.println("删除jsdbdl1: "+sql12.toString());
			    		    oracledb.update(sql12);
			    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl2: "+sql13.toString());
			    		    oracledb.update(sql13);
			    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl21:"+sql14.toString());
			    		    oracledb.update(sql14);
			    		    System.out.println("更新jzxx1表电表用电量为空的补0"+sql15.toString());
			    		    oracledb.update(sql15);
			    		    System.out.println("删除jsdbdl2: "+sql16.toString());
			    		    oracledb.update(sql16);
			    		    System.out.println("删除jsdbdl21: "+sql17.toString());
			    		    oracledb.update(sql17);
			    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl "+sql18.toString());
			    		    oracledb.update(sql18);
			    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl1 "+sql19.toString());
			    		    oracledb.update(sql19);
			    		    System.out.println("更新jzxx1管理电表用电量"+sql20.toString());
			    		    oracledb.update(sql20);
			    		    System.out.println("删除gldbdl"+sql21.toString());
			    		    oracledb.update(sql21);
			    		    System.out.println("删除gldbdl1"+sql22.toString());
			    		    oracledb.update(sql22);
			    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl2"+sql23.toString());
			    		    oracledb.update(sql23);
			    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl21: "+sql24.toString());
			    		    oracledb.update(sql24);
			    		    System.out.println("更新jzxx1管理为空的补0: "+sql25.toString());
			    		    oracledb.update(sql25);
			    		    System.out.println("删除gldbdl2: "+sql26.toString());
			    		    oracledb.update(sql26);
			    		    System.out.println("删除gldbdl21: "+sql27.toString());
			    		    oracledb.update(sql27);
			    		    System.out.println("-------------------------乡镇支局更新完毕-----------------------------------");
			    		    //oracledb.commit();
			            	}
			             }
		             if (((Integer.parseInt(nowHour)==1))){//zdsx07IDC机房更新 1点
			            	String sq="select * from khxx h where h.bzmonth='"+yf+"' and h.property='zdsx07'";
			                System.out.println("当前月有没有保存"+sq);
			            	ResultSet r=oracledb.queryAll(sq);
			            	while(r.next()){
			    				i=1;
			    			}
			            if(i==0){	
					        //oracledb.setAutoCommit(false);
			            	String sql="create  table  zhandianidc as select zd.id, sum(decode(db.dbqyzt, '0', 0, 1)) as jj from zhandian zd, dianbiao db " +
			                "where zd.id = db.zdid and zd.qyzt = '1' and zd.caiji = '0' AND zd.shsign='1' and zd.property = 'zdsx07' group by zd.ID";
			                
			                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx07'";
			    		    String sql3 ="insert into jzxx(symonth,  shi, xian, xiaoqu, zdid, jzname, g2, g3,changjia,zp, zs, gxgwsl,jztype, shebei," +
			    		    		" ydshebei,sfgx, dbydl, dbgldl, zdqsdbdl, daycount, scb, edhdl, bzw, property, bzz, zdscb,zdedhdl, zddqsdbdl, " +
			    		    		"zlfh, jlfh, zdjlfh, zdzlfh) select '"+yf+"', bb.agname, '', '', bb.id, bb.jzname, '', '','', '', '','', ''," +
			    		    		" '', '','', '', '',  bb.qsdbdl, '', bb.scb,  bb.edhdl, '', 'zdsx07',  '', bb.scb, bb.edhdl, bb.qsdbdl, bb.zlfh, bb.jlfh,  bb.jlfh,  bb.zlfh " +
			    		    		" from (select aa.shi, aa.agname, aa.id, aa.jzname, aa.qsdbdl, aa.edhdl,aa.scb,aa.zlfh,aa.jlfh from " +
			    		    		"(select zd.shi, d.agname,  zd.id, zd.jzname,db.scb, zd.qsdbdl,  zd.edhdl,zd.zlfh,zd.jlfh from zhandian zd " +
			    		    		"left join d_area_grade d on zd.shi = d.agcode left join yfxs dbyf on zd.shi = dbyf.shicode, scb db  " +
			    		    		"where zd.id = db.zdid and zd.caiji = '0' and zd.qyzt = '1' AND zd.xuni='0' AND zd.shsign='1' and " +
			    		    		"zd.property = 'zdsx07') aa) bb, zhandianidc zz where bb.id = zz.id and zz.jj <> 0";
			    		    String sql4="create table jzxx1dl as SELECT  zd.ID,ef.accountmonth,min(ef.edhdl)edhdl,min(ef.qsdbdl)qsdbdl,min(am.scb)scb," +
			    		    		"MIN(ef.zlfh)zlfh,MIN(ef.jlfh) jlfh from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where " +
			    		    		"zd.id = db.zdid  and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk AND " +
			    		    		"to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
			    		    		" and zd.property = 'zdsx07' GROUP BY zd.ID,ef.accountmonth";
			    		    String sql5="UPDATE jzxx J SET J.SCB      = (SELECT D.SCB FROM jzxx1DL D WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"'), " +
			    		    		"J.ZDQSDBDL = (SELECT D.QSDBDL FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
			    		    		"J.EDHDL    = (SELECT D.EDHDL FROM jzxx1DL D  WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
			    		    		"J.zlfh    = (SELECT D.zlfh  FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
			    		    		"J.jlfh    = (SELECT D.jlfh FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"') " +
			    		    		"WHERE J.ZDID IN (SELECT D.ID FROM jzxx1DL D  WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"') AND J.PROPERTY='zdsx07'  AND J.SYMONTH = '"+yf+"'";
			    		    String sql6="drop table zhandianidc";
			    		    String sql7="drop table jzxx1dl";
			    		    String sql8="create table jsdbdl as SELECT zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am, " +
			    		    		"dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk" +
			    		    		" AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"'  AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
			    		    		" and zd.property = 'zdsx07'";
			    		    String sql9="create table jsdbdl1 as SELECT AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz  FROM (SELECT TJ.ID," +
			    		    		"tj.dbydl,tj.hbzq,tj.bzz FROM JSDBDL TJ ) AA GROUP BY AA.ID";
			    		    String sql10="update jzxx j SET j.dbydl=(SELECT j1.js FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.daycount=(SELECT j1.hbzq " +
			    		    		"FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzz=(SELECT j1.bzz FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzw  = '1'" +
			    		    		" where j.zdid in(select to_char(j1.id) from jsdbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"' and j.property = 'zdsx07'";
			    		    
			    		    String sql11="drop table jsdbdl";
			    		    String sql12="drop table jsdbdl1";
			    		    
			    		    String sql13="create table jsdbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am," +
			    		    		" dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk " +
			    		    		"AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus <> '1'" +
			    		    		" and ef.manualauditstatus >= '1' and zd.property = 'zdsx07'";
			    		    String sql14="create table jsdbdl21 as select AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (" +
			    		    		"select TJ.ID,tj.dbydl,tj.hbzq,tj.bzz from jsdbdl2 tj) aa group by aa.id";
		                    String sql15="update jzxx j set j.jsdl = 0, j.bzw = '2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdl21 j1)" +
			    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx07'";
			    		    
			    		    String sql16="drop table jsdbdl2";
			    		    String sql17="drop table jsdbdl21";
			    		    
			    		    String sql18="create table gldbdl as select  zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am " +
			    		    		"where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and am.manualauditstatus = '1' " +
			    		    		"and db.dbyt = 'dbyt03' AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"' and zd.property = 'zdsx07'";
			    	        String sql19="create table gldbdl1 as select aa.id,  SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from " +
			    	        		"(select tj.id, tj.dbydl,tj.hbzq,tj.bzz from gldbdl tj) aa group by aa.id";
			    		    String sql20="update jzxx j set j.dbgldl = (select j1.gl from gldbdl1 j1 where to_char(j1.id) = j.zdid),j.daycount = (select j1.hbzq " +
			    		    		"from gldbdl1 j1 where to_char(j1.id) = j.zdid), j.bzz = (select j1.bzz from gldbdl1 j1 where to_char(j1.id) = j.zdid) " +
			    		    		"where j.zdid in (select to_char(j1.id) from gldbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"'" +
			    		    		" and j.property = 'zdsx07'";
			    		    
			    		    String sql21="drop table gldbdl";
			    		    String sql22="drop table gldbdl1";
			    		    
			    		    String sql23="create table gldbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am" +
			    		    		" where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' " +
			    		    		"AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"'  and am.manualauditstatus <> '1' and db.dbyt = 'dbyt03' " +
			    		    		"and zd.property = 'zdsx07'";
			    		    String sql24="create table gldbdl21 as select aa.ID, SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (select tj.id," +
			    		    		"tj.dbydl,tj.hbzq,tj.bzz from gldbdl2 tj) aa group by aa.id";
			    		    String sql25="update jzxx j set j.gldl = 0 where j.gldl is null and j.zdid in (select to_char(j1.id) from gldbdl21 j1)" +
			    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx07'";
			    		    
			    		    String sql26="drop table gldbdl2";
			    		    String sql27="drop table gldbdl21";
			    		    
			    		    System.out.println("创建电表状态表zhandianidc: "+sql.toString());
			    		    oracledb.update(sql);
			    		    System.out.println("删除jzxx1表中当前月份数据"+sql2.toString());
			    		    oracledb.update(sql2);
			    		    System.out.println("插入jzxx1表当前月: "+sql3.toString());
			    		    oracledb.update(sql3);
			    		    System.out.println(" 创建jzxx1关于电费单全省定标电量、生产标、本地定标的中间表 +"+sql4.toString());
			    		    oracledb.update(sql4);
			    		    System.out.println(" 更新jzxx1  电费单对应生产标、本地定标、全省定标电量 +"+sql5.toString());
			    		    oracledb.update(sql5);
			    		    System.out.println(" 删除zhandianidc: +"+sql6.toString());
			    		    oracledb.update(sql6);
			    		    System.out.println(" 删除jzxx1dl: +"+sql7.toString());
			    		    oracledb.update(sql7);
			    		    System.out.println("创建电表用电量表: "+sql8.toString());
			    		    oracledb.update(sql8);
			    		    System.out.println("创建电表用电量jsdbdl1: "+sql9.toString());
			    		    oracledb.update(sql9);
			    		    System.out.println("更新jzxx1表电表用电量: "+sql10.toString());
			    		    oracledb.update(sql10);
			    		    System.out.println("删除jsdbdl: "+sql11.toString());
			    		    oracledb.update(sql11);
			    		    System.out.println("删除jsdbdl1: "+sql12.toString());
			    		    oracledb.update(sql12);
			    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl2: "+sql13.toString());
			    		    oracledb.update(sql13);
			    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl21:"+sql14.toString());
			    		    oracledb.update(sql14);
			    		    System.out.println("更新jzxx1表电表用电量为空的补0"+sql15.toString());
			    		    oracledb.update(sql15);
			    		    System.out.println("删除jsdbdl2: "+sql16.toString());
			    		    oracledb.update(sql16);
			    		    System.out.println("删除jsdbdl21: "+sql17.toString());
			    		    oracledb.update(sql17);
			    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl "+sql18.toString());
			    		    oracledb.update(sql18);
			    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl1 "+sql19.toString());
			    		    oracledb.update(sql19);
			    		    System.out.println("更新jzxx1管理电表用电量"+sql20.toString());
			    		    oracledb.update(sql20);
			    		    System.out.println("删除gldbdl"+sql21.toString());
			    		    oracledb.update(sql21);
			    		    System.out.println("删除gldbdl1"+sql22.toString());
			    		    oracledb.update(sql22);
			    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl2"+sql23.toString());
			    		    oracledb.update(sql23);
			    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl21: "+sql24.toString());
			    		    oracledb.update(sql24);
			    		    System.out.println("更新jzxx1管理为空的补0: "+sql25.toString());
			    		    oracledb.update(sql25);
			    		    System.out.println("删除gldbdl2: "+sql26.toString());
			    		    oracledb.update(sql26);
			    		    System.out.println("删除gldbdl21: "+sql27.toString());
			    		    oracledb.update(sql27);
			    		    System.out.println("-------------------------IDC机房更新完毕-----------------------------------");
			    		    //oracledb.commit();
			            	}
			             }
		             if (((Integer.parseInt(nowHour)==5))){//zdsx05接入网更新5点
			            	String sq="select * from khxx h where h.bzmonth='"+yf+"' and h.property='zdsx05'";
			                System.out.println("当前月有没有保存"+sq);
			            	ResultSet r=oracledb.queryAll(sq);
			            	while(r.next()){
			    				i=1;
			    			}
			            if(i==0){	
					        //oracledb.setAutoCommit(false);
			            	String sql="create  table  zhandianjrw as select zd.id, sum(decode(db.dbqyzt, '0', 0, 1)) as jj from zhandian zd, dianbiao db " +
			                "where zd.id = db.zdid and zd.qyzt = '1' and zd.caiji = '0' AND zd.shsign='1' and zd.property = 'zdsx05' group by zd.ID";
			                
			                String sql2 ="delete jzxx j where j.symonth='"+yf+"' and j.property='zdsx05'";
			    		    String sql3 ="insert into jzxx(symonth,  shi, xian, xiaoqu, zdid, jzname, g2, g3,changjia,zp, zs, gxgwsl,jztype, shebei," +
			    		    		" ydshebei,sfgx, dbydl, dbgldl, zdqsdbdl, daycount, scb, edhdl, bzw, property, bzz, zdscb,zdedhdl, zddqsdbdl, " +
			    		    		"zlfh, jlfh, zdjlfh, zdzlfh) select '"+yf+"', bb.agname, '', '', bb.id, bb.jzname, '', '','', '', '','', ''," +
			    		    		" '', '','', '', '', bb.qsdbdl, '', bb.scb,  bb.edhdl, '', 'zdsx05',  '', bb.scb, bb.edhdl, bb.qsdbdl, bb.zlfh, bb.jlfh,  bb.jlfh,  bb.zlfh " +
			    		    		" from (select aa.shi, aa.agname, aa.id, aa.jzname, aa.qsdbdl, aa.edhdl,aa.scb,aa.zlfh,aa.jlfh from " +
			    		    		"(select zd.shi, d.agname,  zd.id, zd.jzname,db.scb, zd.qsdbdl,  zd.edhdl,zd.zlfh,zd.jlfh from zhandian zd " +
			    		    		"left join d_area_grade d on zd.shi = d.agcode left join yfxs dbyf on zd.shi = dbyf.shicode, scb db  " +
			    		    		"where zd.id = db.zdid and zd.caiji = '0' and zd.qyzt = '1' AND zd.xuni='0' AND zd.shsign='1' and " +
			    		    		"zd.property = 'zdsx05') aa) bb, zhandianjrw zz where bb.id = zz.id and zz.jj <> 0";
			    		    String sql4="create table jzxx1dl as SELECT  zd.ID,ef.accountmonth,min(ef.edhdl)edhdl,min(ef.qsdbdl)qsdbdl,min(am.scb)scb," +
			    		    		"MIN(ef.zlfh)zlfh,MIN(ef.jlfh) jlfh from zhandian zd, dianbiao db, dianduview am, dianfeiview ef where " +
			    		    		"zd.id = db.zdid  and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk AND " +
			    		    		"to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
			    		    		" and zd.property = 'zdsx05' GROUP BY zd.ID,ef.accountmonth";
			    		    String sql5="UPDATE jzxx J SET J.SCB      = (SELECT D.SCB FROM jzxx1DL D WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"'), " +
			    		    		"J.ZDQSDBDL = (SELECT D.QSDBDL FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
			    		    		"J.EDHDL    = (SELECT D.EDHDL FROM jzxx1DL D  WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
			    		    		"J.zlfh    = (SELECT D.zlfh  FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"'), " +
			    		    		"J.jlfh    = (SELECT D.jlfh FROM jzxx1DL D WHERE D.ID = J.ZDID  AND J.SYMONTH = '"+yf+"') " +
			    		    		"WHERE J.ZDID IN (SELECT D.ID FROM jzxx1DL D  WHERE D.ID = J.ZDID AND J.SYMONTH = '"+yf+"') AND J.PROPERTY='zdsx05'  AND J.SYMONTH = '"+yf+"'";
			    		    String sql6="drop table zhandianjrw";
			    		    String sql7="drop table jzxx1dl";
			    		    String sql8="create table jsdbdl as SELECT zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am, " +
			    		    		"dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk" +
			    		    		" AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"'  AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus = '1'" +
			    		    		" and zd.property = 'zdsx05'";
			    		    String sql9="create table jsdbdl1 as SELECT AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz  FROM (SELECT TJ.ID," +
			    		    		"tj.dbydl,tj.hbzq,tj.bzz FROM JSDBDL TJ ) AA GROUP BY AA.ID";
			    		    String sql10="update jzxx j SET j.dbydl=(SELECT j1.js FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.daycount=(SELECT j1.hbzq " +
			    		    		"FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzz=(SELECT j1.bzz FROM jsdbdl1 j1 WHERE to_char(j1.id) = j.zdid ),j.bzw  = '1'" +
			    		    		" where j.zdid in(select to_char(j1.id) from jsdbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"' and j.property = 'zdsx05'";
			    		    
			    		    String sql11="drop table jsdbdl";
			    		    String sql12="drop table jsdbdl1";
			    		    
			    		    String sql13="create table jsdbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am," +
			    		    		" dianfeiview ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk " +
			    		    		"AND to_char(ef.accountmonth,'yyyy-mm')='"+yf+"' AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and ef.cityzrauditstatus <> '1'" +
			    		    		" and ef.manualauditstatus >= '1' and zd.property = 'zdsx05'";
			    		    String sql14="create table jsdbdl21 as select AA.ID, SUM(aa.dbydl) AS JS,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (" +
			    		    		"select TJ.ID,tj.dbydl,tj.hbzq,tj.bzz from jsdbdl2 tj) aa group by aa.id";
		                    String sql15="update jzxx j set j.jsdl = 0, j.bzw = '2' where j.jsdl is null and j.zdid in (select to_char(j1.id) from jsdbdl21 j1)" +
			    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx05'";
			    		    
			    		    String sql16="drop table jsdbdl2";
			    		    String sql17="drop table jsdbdl21";
			    		     
			    		    String sql18="create table gldbdl as select  zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am " +
			    		    		"where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' and am.manualauditstatus = '1' " +
			    		    		"and db.dbyt = 'dbyt03' AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"' and zd.property = 'zdsx05'";
			    	        String sql19="create table gldbdl1 as select aa.id,  SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from " +
			    	        		"(select tj.id, tj.dbydl,tj.hbzq,tj.bzz from gldbdl tj) aa group by aa.id";
			    		    String sql20="update jzxx j set j.dbgldl = (select j1.gl from gldbdl1 j1 where to_char(j1.id) = j.zdid),j.daycount = (select j1.hbzq " +
			    		    		"from gldbdl1 j1 where to_char(j1.id) = j.zdid), j.bzz = (select j1.bzz from gldbdl1 j1 where to_char(j1.id) = j.zdid) " +
			    		    		"where j.zdid in (select to_char(j1.id) from gldbdl1 j1 where to_char(j1.id) = j.zdid) and j.symonth = '"+yf+"'" +
			    		    		" and j.property = 'zdsx05'";
			    		    
			    		    String sql21="drop table gldbdl";
			    		    String sql22="drop table gldbdl1";
			    		    
			    		    String sql23="create table gldbdl2 as select zd.shi,zd.id, am.dbydl, am.HBZQ, am.bzz from zhandian zd, dianbiao db, dianduview am" +
			    		    		" where zd.id = db.zdid and db.dbid = am.ammeterid_fk AND zd.xuni = '0' AND zd.caiji='0' AND zd.shsign='1' " +
			    		    		"AND to_char(am.STARTMONTH,'yyyy-mm')>='"+yf+"' AND to_char(am.ENDMONTH,'yyyy-mm')<='"+yf+"'  and am.manualauditstatus <> '1' and db.dbyt = 'dbyt03' " +
			    		    		"and zd.property = 'zdsx05'";
			    		    String sql24="create table gldbdl21 as select aa.ID, SUM(aa.dbydl) AS gl,SUM(aa.hbzq) AS hbzq,SUM(aa.bzz) bzz from (select tj.id," +
			    		    		"tj.dbydl,tj.hbzq,tj.bzz from gldbdl2 tj) aa group by aa.id";
			    		    String sql25="update jzxx j set j.gldl = 0 where j.gldl is null and j.zdid in (select to_char(j1.id) from gldbdl21 j1)" +
			    		    		" and j.symonth = '"+yf+"' and j.property = 'zdsx05'";
			    		    
			    		    String sql26="drop table gldbdl2";
			    		    String sql27="drop table gldbdl21";
			    		    
			    		    System.out.println("创建电表状态表zhandianjrw: "+sql.toString());
			    		    oracledb.update(sql);
			    		    System.out.println("删除jzxx1表中当前月份数据"+sql2.toString());
			    		    oracledb.update(sql2);
			    		    System.out.println("插入jzxx1表当前月: "+sql3.toString());
			    		    oracledb.update(sql3);
			    		    System.out.println(" 创建jzxx1关于电费单全省定标电量、生产标、本地定标的中间表 +"+sql4.toString());
			    		    oracledb.update(sql4);
			    		    System.out.println(" 更新jzxx1  电费单对应生产标、本地定标、全省定标电量 +"+sql5.toString());
			    		    oracledb.update(sql5);
			    		    System.out.println(" 删除zhandianjrw: +"+sql6.toString());
			    		    oracledb.update(sql6);
			    		    System.out.println(" 删除jzxx1dl: +"+sql7.toString());
			    		    oracledb.update(sql7);
			    		    System.out.println("创建电表用电量表: "+sql8.toString());
			    		    oracledb.update(sql8);
			    		    System.out.println("创建电表用电量jsdbdl1: "+sql9.toString());
			    		    oracledb.update(sql9);
			    		    System.out.println("更新jzxx1表电表用电量: "+sql10.toString());
			    		    oracledb.update(sql10);
			    		    System.out.println("删除jsdbdl: "+sql11.toString());
			    		    oracledb.update(sql11);
			    		    System.out.println("删除jsdbdl1: "+sql12.toString());
			    		    oracledb.update(sql12);
			    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl2: "+sql13.toString());
			    		    oracledb.update(sql13);
			    		    System.out.println("创建电表用电量（人工>=1 市级<>1）jsdbdl21:"+sql14.toString());
			    		    oracledb.update(sql14);
			    		    System.out.println("更新jzxx1表电表用电量为空的补0"+sql15.toString());
			    		    oracledb.update(sql15);
			    		    System.out.println("删除jsdbdl2: "+sql16.toString());
			    		    oracledb.update(sql16);
			    		    System.out.println("删除jsdbdl21: "+sql17.toString());
			    		    oracledb.update(sql17);
			    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl "+sql18.toString());
			    		    oracledb.update(sql18);
			    		    System.out.println("创建管理电表用电量（人工审核=1）gldbdl1 "+sql19.toString());
			    		    oracledb.update(sql19);
			    		    System.out.println("更新jzxx1管理电表用电量"+sql20.toString());
			    		    oracledb.update(sql20);
			    		    System.out.println("删除gldbdl"+sql21.toString());
			    		    oracledb.update(sql21);
			    		    System.out.println("删除gldbdl1"+sql22.toString());
			    		    oracledb.update(sql22);
			    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl2"+sql23.toString());
			    		    oracledb.update(sql23);
			    		    System.out.println("创建管理电表用电量（人工审核<>1）gldbdl21: "+sql24.toString());
			    		    oracledb.update(sql24);
			    		    System.out.println("更新jzxx1管理为空的补0: "+sql25.toString());
			    		    oracledb.update(sql25);
			    		    System.out.println("删除gldbdl2: "+sql26.toString());
			    		    oracledb.update(sql26);
			    		    System.out.println("删除gldbdl21: "+sql27.toString());
			    		    oracledb.update(sql27);
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
