package com.noki.mobi.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class AccountJzqa {
	public synchronized List<zdbzbeanB> getPageDatap(String whereStr,String bl,String yy,String month,String dyt) {		
		List<zdbzbeanB> list = new ArrayList<zdbzbeanB>();
	    CTime ct = new CTime();
	    String sql = "",sql1="";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    //调用负责站点条件函数 str=" and db.dbyt='dbyt01' ";
		//  str1=",dianfeiview w ";
  		//  str2=" AND am.ammeterdegreeid=w.ammeterdegreeid_fk ";
	    String s=month.substring(5,7);
	    if(s.equals("01")){s="month1";}
	    if(s.equals("02")){s="month2";}
	    if(s.equals("03")){s="month3";}
	    if(s.equals("04")){s="month4";}
	    if(s.equals("05")){s="month5";}
	    if(s.equals("06")){s="month6";}
	    if(s.equals("07")){s="month7";}
	    if(s.equals("08")){s="month8";}
	    if(s.equals("09")){s="month9";}
	    if(s.equals("10")){s="month10";}
	    if(s.equals("11")){s="month11";}
	    if(s.equals("12")){s="month12";}
	    String sql2="";
	    ResultSet rs = null;
		try {
           if(yy.equals("3")&&"3".equals(dyt)){
			//sql1="SELECT CCC.EDGLXZA EDGL,ZBZ.G2, ZBZ.G3,ZBZ.CHANGJIA,ZBZ.JZTYPE,ZBZ.ZP,ZBZ.ZS,ZBZ.SHEBEI,COUNT(CCC.DBID) JS"+
              //   " FROM ZDBZ ZBZ,(SELECT ZDZ.EDGLXZA,DW.THISDATETIME, DW.LASTDATETIME, DBB.DBID,ZZ.G2,ZZ.G3,ZZ.G3CJ,"+
              //    " ZZ.JZTYPE,ZZ.ZP11,ZZ.ZS11, ZZ.XINGHAO, ((CASE WHEN LENGTH(DW.THISDATETIME) = 10 AND LENGTH(DW.LASTDATETIME) = 10 THEN  decode(NVL(CEIL(TO_DATE(DW.THISDATETIME, 'yyyy-mm-dd') -TO_DATE(DW.LASTDATETIME, 'yyyy-mm-dd')),1),0,0,DW.BLHDL/NVL(CEIL(TO_DATE(DW.THISDATETIME, 'yyyy-mm-dd') -TO_DATE(DW.LASTDATETIME, 'yyyy-mm-dd')),1)) END)-ZDZ.EDGLXZA) / ZDZ.EDGLXZA * 100 CBBL"+
              //    " FROM ZHAND1 ZZ,ZHANDIAN ZD,DIANDUVIEW  DW,DIANBIAO DBB,DIANFEIVIEW E, ZDBZ ZDZ "+
              //    " WHERE DBB.DBID = DW.AMMETERID_FK AND ZZ.ZDID = TO_CHAR(ZD.ID) "+
              //    " AND ZD.ID = DBB.ZDID AND ZD.ZDCQ = 'zdcq01' AND E.AMMETERDEGREEID_FK = DW.AMMETERDEGREEID AND ZDZ.G2=ZZ.G2 AND ZDZ.G3=ZZ.G3 AND ZDZ.CHANGJIA=ZZ.G3CJ AND ZDZ.ZP=ZZ.ZP11 AND ZDZ.ZS=ZZ.ZS11 AND ZDZ.JZTYPE=ZZ.JZTYPE AND ZDZ.SHEBEI=ZZ.XINGHAO "+whereStr+""+
              //    " AND DBB.DBYT = 'dbyt01' "+
              //    " )  CCC "+
              ////    " WHERE ZBZ.G2 = CCC.G2  AND ZBZ.G3 = CCC.G3 AND ZBZ.CHANGJIA = CCC.G3CJ AND ZBZ.JZTYPE = CCC.JZTYPE "+
              //    " AND ZBZ.SHEBEI = CCC.XINGHAO AND ZBZ.ZP = CCC.ZP11 AND ZBZ.ZS = CCC.ZS11 AND CCC.CBBL > '"+bl+"' "+
               //   " GROUP BY  ZBZ.G2,ZBZ.G3,ZBZ.CHANGJIA,ZBZ.JZTYPE,ZBZ.ZP,ZBZ.ZS,ZBZ.SHEBEI,CCC.EDGLXZA ORDER BY ZBZ.ZP,ZBZ.ZS";
          sql2="SELECT G2,G3,CHANGJIA,ZP,ZS,"+s+", SUM(decode(tab.fgx,'1',1)) js, "+s+"||'+23*套数' ts,SUM(decode(tab.gx,'2',1)) gx "+
             " FROM (SELECT ZD1.ID, TO_CHAR(THISDATETIME,'yyyy-mm-dd') THISDATETIME, TO_CHAR(LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,A.G2, A.G3,A.CHANGJIA,A.ZP,A.ZS,THISDATETIME - LASTDATETIME + 1,BLHDL,"+
             "  (case when zd.sfgx='0 ' and (( "+
             " (((CASE  WHEN (THISDATETIME -  LASTDATETIME + 1) = 0 THEN  0 ELSE  BLHDL /  TO_NUMBER(THISDATETIME - "+
             " LASTDATETIME + 1)  END))))-"+s+")/"+s+"*100>'"+bl+"' then '1' ELSE '0' end) fgx,"+
             " (case when zd.sfgx='1 ' and (( "+
             "  (((CASE  WHEN (THISDATETIME -  LASTDATETIME + 1) = 0 THEN  0  ELSE  BLHDL /  TO_NUMBER(THISDATETIME - "+
             "  LASTDATETIME + 1)  END))))-("+s+"+zd.ydshebei*23))/("+s+"+zd.ydshebei*23)*100>'"+bl+"' then '2' ELSE '0' end) gx, "+
			 " "+s+" FROM ZHANDIAN  ZD1, DIANBIAO  DB, DIANDUVIEW  AM,DIANFEIVIEW B,JZXX ZD,ZDNHBZ A "+
			" WHERE ZD1.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = B.AMMETERDEGREEID_FK AND ZD.ZDID = TO_CHAR(ZD1.ID) "+whereStr+" "+
			 " AND DB.DBYT = 'dbyt01' AND ZD1.QYZT = '1' AND  ZD1.ZDCQ = 'zdcq01' AND DB.DBQYZT = '1' "+
			 " AND ZD.G2 = A.G2  AND ZD.G3 = A.G3  AND ZD.CHANGJIA= A.CHANGJIA AND ZD.ZP = A.ZP  AND ZD.ZS = A.ZS) TAB "+
			 " GROUP BY G2, G3, CHANGJIA, ZP, ZS,"+s+" ORDER BY ZP,ZS";
			
			System.out.println("超标基站管理查询："+sql2);
	      db.connectDb();
	   
	      rs = db.queryAll(sql2.toString());
	      while(rs.next()){
	    	  zdbzbeanB zb=new zdbzbeanB();
	    	    zb.setG2(rs.getString("G2"));
				zb.setG3(rs.getString("G3"));
				zb.setZP(rs.getInt("ZP"));
				zb.setZS(rs.getInt("ZS"));
				zb.setCHANGJIA(rs.getString("CHANGJIA"));
				String sss=rs.getString(s);
   				if(null==sss||"".equals(s)){sss="0";}
   				zb.setEDGL(Double.parseDouble(sss));
   				String sd=rs.getString("JS");
   				 if(null==sd||sd.equals("")){sd="0";}
   				zb.setJs(sd);
   				zb.setTs(rs.getString("ts"));
   				String gxx=rs.getString("gx");
   				if(null==gxx||gxx.equals("")){gxx="0";}
   				zb.setGx(gxx);
				list.add(zb);
	      }}
           if(yy.equals("2")&&"2".equals(dyt)){
        	   System.out.println("开始月份：管理类电表");
   			sql1="SELECT G2, G3, CHANGJIA, ZP, ZS, "+s+",SUM(decode(tab.fgx,'1',1)) js, "+s+"||'+23*套数' ts,SUM(decode(tab.gx,'2',1)) gx "+
                    " FROM (SELECT ZD1.ID, TO_CHAR(THISDATETIME,'yyyy-mm-dd') THISDATETIME,TO_CHAR(LASTDATETIME,'yyyy-mm-dd') LASTDATETIME, A.G2,A.G3, A.CHANGJIA, A.ZP, A.ZS,THISDATETIME - LASTDATETIME + 1, BLHDL,"+
                    "  (case when zd.sfgx='0 ' and (( "+
                    " (((CASE  WHEN (THISDATETIME -  LASTDATETIME + 1) = 0 THEN  0 ELSE  BLHDL /  TO_NUMBER(THISDATETIME - "+
                    " LASTDATETIME + 1)  END))))-"+s+")/"+s+"*100>'"+bl+"' then '1' ELSE '0' end) fgx,"+
                   " (case when zd.sfgx='1 ' and (( "+
                    "        (((CASE  WHEN (THISDATETIME -  LASTDATETIME + 1) = 0 THEN  0  ELSE  BLHDL /  TO_NUMBER(THISDATETIME - "+
                     " LASTDATETIME + 1)  END))))-("+s+"+zd.ydshebei*23))/("+s+"+zd.ydshebei*23)*100>'"+bl+"' then '2' ELSE '0' end) gx, "+
                    " "+s+" FROM ZHANDIAN ZD1, DIANBIAO DB, DIANDUVIEW B, JZXX ZD, ZDNHBZ A "+
                    "  WHERE ZD.ZP = A.ZP AND ZD1.QYZT = '1'  AND DB.DBQYZT = '1' AND ZD.ZS = A.ZS AND ZD1.ID = DB.ZDID  AND DB.DBID = B.AMMETERID_FK  AND ZD.ZDID = TO_CHAR(ZD1.ID)  AND DB.DBYT = 'dbyt03' AND ZD.G2 = A.G2  AND ZD.G3 = A.G3  AND ZD.CHANGJIA = A.CHANGJIA AND ZD1.ZDCQ = 'zdcq01' "+whereStr+" ) TAB"+
   			        "  GROUP BY G2, G3, CHANGJIA, ZP, ZS,"+s+" ORDER BY ZP, ZS";
   			System.out.println("开始月份：管理类电表："+sql1);
   	      db.connectDb();	    
   	      rs = db.queryAll(sql1.toString());
   	      while(rs.next()){
   	    	  zdbzbeanB zb=new zdbzbeanB();
   	    	    zb.setG2(rs.getString("G2"));
   				zb.setG3(rs.getString("G3"));
   				zb.setZP(rs.getInt("ZP"));
   				zb.setZS(rs.getInt("ZS"));
   				zb.setCHANGJIA(rs.getString("CHANGJIA"));
   				String sss=rs.getString(s);
   				if(null==sss||"".equals(s)){sss="0";}
   				zb.setEDGL(Double.parseDouble(sss));
   				String sd=rs.getString("JS");
   				 if(null==sd||sd.equals("")){sd="0";}
   				zb.setJs(sd);
   				zb.setTs(rs.getString("ts"));
   				String gxx=rs.getString("gx");
   				if(null==gxx||gxx.equals("")){gxx="0";}
   				zb.setGx(gxx);
   				list.add(zb);
   	      }}
           if(yy.equals("2")&&"3".equals(dyt)){
        	   sql1="SELECT G2, G3, CHANGJIA, ZP, ZS, "+s+", SUM(decode(tab.fgx,'1',1)) js, "+s+"||'+23*套数' ts,SUM(decode(tab.gx,'2',1)) gx "+
               " FROM (SELECT ZD1.ID, TO_CHAR(THISDATETIME,'yyyy-mm-dd') THISDATETIME,TO_CHAR(LASTDATETIME,'yyyy-mm-dd') LASTDATETIME, A.G2,A.G3, A.CHANGJIA, A.ZP, A.ZS,THISDATETIME - LASTDATETIME + 1, BLHDL,"+
              "  (case when zd.sfgx='0 ' and (( "+
              " (((CASE  WHEN (THISDATETIME -  LASTDATETIME + 1) = 0 THEN  0 ELSE  BLHDL /  TO_NUMBER(THISDATETIME - "+
              " LASTDATETIME + 1)  END))))-"+s+")/"+s+"*100>'"+bl+"' then '1' ELSE '0' end) fgx,"+
             " (case when zd.sfgx='1 ' and (( "+
              "        (((CASE  WHEN (THISDATETIME -  LASTDATETIME + 1) = 0 THEN  0  ELSE  BLHDL /  TO_NUMBER(THISDATETIME - "+
               " LASTDATETIME + 1)  END))))-("+s+"+zd.ydshebei*23))/("+s+"+zd.ydshebei*23)*100>'"+bl+"' then '2' ELSE '0' end) gx, "+
               " "+s+" FROM ZHANDIAN ZD1, DIANBIAO DB, DIANDUVIEW B, JZXX ZD, ZDNHBZ A  "+
               " WHERE  ZD.ZP = A.ZP AND ZD.ZS = A.ZS AND ZD1.ID = DB.ZDID AND ZD1.QYZT = '1'  AND DB.DBQYZT = '1' AND DB.DBID = B.AMMETERID_FK  AND ZD.ZDID = TO_CHAR(ZD1.ID)  AND DB.DBYT = 'dbyt01' AND ZD.G2 = A.G2  AND ZD.G3 = A.G3  AND ZD.CHANGJIA = A.CHANGJIA AND ZD1.ZDCQ = 'zdcq01' "+whereStr+" ) TAB"+
			        "  GROUP BY G2, G3, CHANGJIA, ZP, ZS,"+s+" ORDER BY ZP, ZS";
   			
   		  System.out.println("开始月份：结算类电表："+sql1);
   	      db.connectDb();	    
   	      rs = db.queryAll(sql1.toString());
   	      while(rs.next()){
   	    	  zdbzbeanB zb=new zdbzbeanB();
   	    	    zb.setG2(rs.getString("G2"));
   				zb.setG3(rs.getString("G3"));
   				zb.setZP(rs.getInt("ZP"));
   				zb.setZS(rs.getInt("ZS"));
   				zb.setCHANGJIA(rs.getString("CHANGJIA"));
   				String sss=rs.getString(s);
   				if(null==sss||"".equals(s)){sss="0";}
   				zb.setEDGL(Double.parseDouble(sss));
   				String sd=rs.getString("JS");
   				 if(null==sd||sd.equals("")){sd="0";}
   				zb.setJs(sd);
   				zb.setTs(rs.getString("ts"));
   				String gxx=rs.getString("gx");
   				if(null==gxx||gxx.equals("")){gxx="0";}
   				zb.setGx(gxx);
   				
   				list.add(zb);
   	      }}
	    }catch (DbException de) {
	      de.printStackTrace();
	    } catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	      if (rs != null) {
	        try {
	          rs.close();
	        }
	        catch (SQLException se) {
	          se.printStackTrace();
	        }
	      }
	      try {
	    	  if(db!=null){
	        db.close();
	      }}
	      catch (DbException de) {
	        de.printStackTrace();
	      }

	    }
		 
	    return list;
	  }
	//whereStr,str1,bla,yf,bzmonth,dbyt  whereStr,str1,bla,yf,bzmonth,dbyt
	public synchronized List<zdbzbeanB> getPageDatapa(String whereStr,String str,String bl,String yf,String bzmonth,String dbyt,String gxbzw) {		
		List<zdbzbeanB> list = new ArrayList<zdbzbeanB>();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    System.out.println("yf:"+yf+" dbyt:"+dbyt);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    String s=bzmonth.substring(5,7);
	    if(s.equals("01")){s="month1";}
	    if(s.equals("02")){s="month2";}
	    if(s.equals("03")){s="month3";}
	    if(s.equals("04")){s="month4";}
	    if(s.equals("05")){s="month5";}
	    if(s.equals("06")){s="month6";}
	    if(s.equals("07")){s="month7";}
	    if(s.equals("08")){s="month8";}
	    if(s.equals("09")){s="month9";}
	    if(s.equals("10")){s="month10";}
	    if(s.equals("11")){s="month11";}
	    if(s.equals("12")){s="month12";}
	    //调用负责站点条件函数
	    ResultSet rs = null;
	    
		try {
			if("3".equals(yf)&&"3".equals(dbyt)&&"0".equals(gxbzw)){
			
	       sql="SELECT ZD1.ID,ZD1.SHI,ZD1.XIAN,ZD1.XIAOQU,DB.DBID,ZD1.JZNAME,"+
                " (CASE WHEN ZD1.SHI IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.SHI)  END) SHI1,"+
     " (CASE WHEN ZD1.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.XIAN)  END) XIAN1,"+
       " (CASE WHEN ZD1.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.XIAOQU)  END) XIAOQU1,"+
         " TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1, BLHDL,B.ENTRYTIME,"+
         "  ((((CASE  WHEN (TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE BLHDL / TO_NUMBER(TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) "+
	       " END))-"+s+")/"+s+")*100 bl "+
          " FROM ZHANDIAN ZD1,DIANBIAO DB, DIANDUVIEW AM, DIANFEIVIEW B, JZXX ZD, ZDNHBZ A "+
          " WHERE ZD1.ID = DB.ZDID  AND DB.DBID = AM.AMMETERID_FK AND  AM.AMMETERDEGREEID = B.AMMETERDEGREEID_FK  AND ZD.ZDID = TO_CHAR(ZD1.ID) "+
         " AND ZD.SFGX='0' AND  DB.DBYT = 'dbyt01' AND ZD1.QYZT = '1'  AND DB.DBQYZT = '1' AND ZD1.ZDCQ = 'zdcq01'  AND ZD.G2 = A.G2  AND ZD.G3 = A.G3  AND ZD.CHANGJIA = A.CHANGJIA  AND ZD.ZP = A.ZP AND ZD.ZS = A.ZS "+whereStr+" "+str+" "+
	      " AND ((((CASE  WHEN (TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE BLHDL / TO_NUMBER(TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) "+
	       " END))-"+s+")/"+s+")*100> "+bl+" ORDER BY ZD1.SHI,ZD1.XIAN,ZD1.XIAOQU";
	       System.out.println("详细信息的 报账月份："+sql);
	      db.connectDb();	    
	      rs = db.queryAll( sql.toString());
	      while(rs.next()){
	    	  zdbzbeanB zb=new zdbzbeanB();
	    	  zb.setDbid(rs.getString("DBID"));
	    	 // zb.setAccountmonth(rs.getString("ACCOUNTMONTH"));
	    	    zb.setSHI(rs.getString("SHI"));
	    	    zb.setXian(rs.getString("XIAN"));
	    	    zb.setXiaoqu(rs.getString("XIAOQU"));
	    	    zb.setZdname(rs.getString("JZNAME"));
	    	    zb.setXian1(rs.getString("XIAN1"));
	    	    zb.setShi1(rs.getString("SHI1"));
	    	    zb.setXiaoqu1(rs.getString("XIAOQU1"));
	    	    zb.setBlhdl(rs.getString("BLHDL"));
	    	    zb.setEntrytime(rs.getString("ENTRYTIME"));
	    	    zb.setBl(rs.getString("bl"));
	    	    zb.setId(rs.getString("ID"));
				list.add(zb);
	      }}
			if("2".equals(yf)&&"2".equals(dbyt)&&"0".equals(gxbzw)){
				
				   sql="SELECT ZD1.ID,ZD1.SHI,ZD1.XIAN,ZD1.XIAOQU,DB.DBID,ZD1.JZNAME,"+
	                " (CASE WHEN ZD1.SHI IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.SHI)  END) SHI1,"+
	     " (CASE WHEN ZD1.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.XIAN)  END) XIAN1,"+
	       " (CASE WHEN ZD1.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.XIAOQU)  END) XIAOQU1,"+
	         " TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1, BLHDL,AM.ENTRYTIME,"+
	         "  ((((CASE  WHEN (TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE BLHDL / TO_NUMBER(TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) "+
		       " END))-"+s+")/"+s+")*100 bl"+
	          " FROM ZHANDIAN ZD1,DIANBIAO DB, DIANDUVIEW AM, JZXX ZD, ZDNHBZ A "+
	          " WHERE ZD1.ID = DB.ZDID  AND DB.DBID = AM.AMMETERID_FK  AND ZD.ZDID = TO_CHAR(ZD1.ID) "+
	         " AND ZD.SFGX='0' AND DB.DBYT = 'dbyt03' AND ZD1.QYZT = '1' AND ZD1.ZDCQ = 'zdcq01'  AND DB.DBQYZT = '1'  AND ZD.G2 = A.G2  AND ZD.G3 = A.G3  AND ZD.CHANGJIA= A.CHANGJIA  AND ZD.ZP = A.ZP AND ZD.ZS = A.ZS "+whereStr+" "+str+" "+
		      " AND ((((CASE  WHEN (TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE BLHDL / TO_NUMBER(TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) "+
		       " END))-"+s+")/"+s+")*100> "+bl+" ORDER BY ZD1.SHI,ZD1.XIAN,ZD1.XIAOQU";
			     
				   System.out.println("right开始月份 详细信息： 管理类电表："+sql);
			      db.connectDb();	    
			      rs = db.queryAll( sql.toString());
			      while(rs.next()){
			    	  zdbzbeanB zb=new zdbzbeanB();
			    	  zb.setDbid(rs.getString("DBID"));
			    	//  zb.setAccountmonth(rs.getString("ACCOUNTMONTH"));
			    	    zb.setSHI(rs.getString("SHI"));
			    	    zb.setXian(rs.getString("XIAN"));
			    	    zb.setXiaoqu(rs.getString("XIAOQU"));
			    	    zb.setZdname(rs.getString("JZNAME"));
			    	   zb.setXian1(rs.getString("XIAN1"));
			    	   zb.setShi1(rs.getString("SHI1"));
			    	   zb.setXiaoqu1(rs.getString("XIAOQU1"));
			    	   zb.setBlhdl(rs.getString("BLHDL"));
			    	   zb.setEntrytime(rs.getString("ENTRYTIME"));
			    	    zb.setBl(rs.getString("bl"));
			    	    zb.setId(rs.getString("ID"));
			    	 //   zb.setDbname(rs.getString("DBNAME"));
						list.add(zb);
			      }}
			if("2".equals(yf)&&"3".equals(dbyt)&&"0".equals(gxbzw)){
				
				  sql="SELECT ZD1.ID,ZD1.SHI,ZD1.XIAN,ZD1.XIAOQU,DB.DBID,ZD1.JZNAME,"+
	                " (CASE WHEN ZD1.SHI IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.SHI)  END) SHI1,"+
	     " (CASE WHEN ZD1.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.XIAN)  END) XIAN1,"+
	       " (CASE WHEN ZD1.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.XIAOQU)  END) XIAOQU1,"+
	         " TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1, BLHDL,AM.ENTRYTIME,"+
	         "  ((((CASE  WHEN (TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE BLHDL / TO_NUMBER(TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) "+
		       " END))-"+s+")/"+s+")*100 bl "+
	          " FROM ZHANDIAN ZD1,DIANBIAO DB, DIANDUVIEW AM, JZXX ZD, ZDNHBZ A "+
	          " WHERE ZD1.ID = DB.ZDID  AND DB.DBID = AM.AMMETERID_FK  AND ZD.ZDID = TO_CHAR(ZD1.ID) "+
	         " AND ZD.SFGX='0' AND DB.DBYT = 'dbyt01' AND ZD1.QYZT = '1' AND ZD1.ZDCQ = 'zdcq01'  AND DB.DBQYZT = '1' AND ZD.G2 = A.G2  AND ZD.G3 = A.G3  AND ZD.CHANGJIA = A.CHANGJIA  AND ZD.ZP = A.ZP AND ZD.ZS = A.ZS "+whereStr+" "+str+" "+
		      " AND ((((CASE  WHEN (TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE BLHDL / TO_NUMBER(TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) "+
		       " END))-"+s+")/"+s+")*100> "+bl+" ORDER BY ZD1.SHI,ZD1.XIAN,ZD1.XIAOQU";
			       System.out.println("right 开始月份  结算类电表："+sql);
			      db.connectDb();	    
			      rs = db.queryAll( sql.toString());
			      while(rs.next()){
			    	  zdbzbeanB zb=new zdbzbeanB();
			    	  zb.setDbid(rs.getString("DBID"));
			    	// zb.setAccountmonth(rs.getString("ACCOUNTMONTH"));
			    	    zb.setSHI(rs.getString("SHI"));
			    	    zb.setXian(rs.getString("XIAN"));
			    	    zb.setXiaoqu(rs.getString("XIAOQU"));
			    	    zb.setZdname(rs.getString("JZNAME"));
			    	   zb.setXian1(rs.getString("XIAN1"));
			    	   zb.setShi1(rs.getString("SHI1"));
			    	   zb.setXiaoqu1(rs.getString("XIAOQU1"));
			    	   zb.setBlhdl(rs.getString("BLHDL"));
			    	   zb.setEntrytime(rs.getString("ENTRYTIME"));
			    	    zb.setBl(rs.getString("bl"));
			    	    zb.setId(rs.getString("ID"));
			    	  //  zb.setDbname(rs.getString("DBNAME"));
						list.add(zb);
			      }}
			if("2".equals(yf)&&"3".equals(dbyt)&&"1".equals(gxbzw)){
				
				  sql="SELECT ZD1.ID,ZD1.SHI,ZD1.XIAN,ZD1.XIAOQU,DB.DBID,ZD1.JZNAME,"+
	                " (CASE WHEN ZD1.SHI IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.SHI)  END) SHI1,"+
	     " (CASE WHEN ZD1.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.XIAN)  END) XIAN1,"+
	       " (CASE WHEN ZD1.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.XIAOQU)  END) XIAOQU1,"+
	         " TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1, BLHDL,AM.ENTRYTIME,"+
	         "  ((((CASE  WHEN (TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE BLHDL / TO_NUMBER(TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) "+
		       " END))-("+s+"+23*zd.ydshebei))/("+s+"+23*zd.ydshebei))*100 bl "+
	          " FROM ZHANDIAN ZD1,DIANBIAO DB, DIANDUVIEW AM, JZXX ZD, ZDNHBZ A "+
	          " WHERE ZD1.ID = DB.ZDID  AND DB.DBID = AM.AMMETERID_FK  AND ZD.ZDID = TO_CHAR(ZD1.ID) "+
	         " AND ZD.SFGX='1' AND DB.DBYT = 'dbyt01' AND ZD1.QYZT = '1' AND ZD1.ZDCQ = 'zdcq01'  AND DB.DBQYZT = '1' AND ZD.G2 = A.G2  AND ZD.G3 = A.G3  AND ZD.CHANGJIA = A.CHANGJIA  AND ZD.ZP = A.ZP AND ZD.ZS = A.ZS "+whereStr+" "+str+" "+
		      " AND ((((CASE  WHEN (TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE BLHDL / TO_NUMBER(TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) "+
		       " END))-("+s+"+23*zd.ydshebei))/("+s+"+23*zd.ydshebei))*100> "+bl+" ORDER BY ZD1.SHI,ZD1.XIAN,ZD1.XIAOQU";
			       System.out.println("right 开始月份  结算类电表："+sql);
			      db.connectDb();	    
			      rs = db.queryAll( sql.toString());
			      while(rs.next()){
			    	  zdbzbeanB zb=new zdbzbeanB();
			    	  zb.setDbid(rs.getString("DBID"));
			    	// zb.setAccountmonth(rs.getString("ACCOUNTMONTH"));
			    	    zb.setSHI(rs.getString("SHI"));
			    	    zb.setXian(rs.getString("XIAN"));
			    	    zb.setXiaoqu(rs.getString("XIAOQU"));
			    	    zb.setZdname(rs.getString("JZNAME"));
			    	   zb.setXian1(rs.getString("XIAN1"));
			    	   zb.setShi1(rs.getString("SHI1"));
			    	   zb.setXiaoqu1(rs.getString("XIAOQU1"));
			    	   zb.setBlhdl(rs.getString("BLHDL"));
			    	   zb.setEntrytime(rs.getString("ENTRYTIME"));
			    	    zb.setBl(rs.getString("bl"));
			    	    zb.setId(rs.getString("ID"));
			    	  //  zb.setDbname(rs.getString("DBNAME"));
						list.add(zb);
			      }}
			if("2".equals(yf)&&"2".equals(dbyt)&&"1".equals(gxbzw)){
				
				   sql="SELECT ZD1.ID,ZD1.SHI,ZD1.XIAN,ZD1.XIAOQU,DB.DBID,ZD1.JZNAME,"+
	                " (CASE WHEN ZD1.SHI IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.SHI)  END) SHI1,"+
	     " (CASE WHEN ZD1.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.XIAN)  END) XIAN1,"+
	       " (CASE WHEN ZD1.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.XIAOQU)  END) XIAOQU1,"+
	         " TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1, BLHDL,AM.ENTRYTIME,"+
	         "  ((((CASE  WHEN (TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE BLHDL / TO_NUMBER(TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) "+
		       " END))-("+s+"+23*zd.ydshebei))/("+s+"+23*zd.ydshebei))*100 bl"+
	          " FROM ZHANDIAN ZD1,DIANBIAO DB, DIANDUVIEW AM, JZXX ZD, ZDNHBZ A "+
	          " WHERE ZD1.ID = DB.ZDID  AND DB.DBID = AM.AMMETERID_FK  AND ZD.ZDID = TO_CHAR(ZD1.ID) "+
	         " AND ZD.SFGX='1' AND DB.DBYT = 'dbyt03' AND ZD1.QYZT = '1' AND ZD1.ZDCQ = 'zdcq01'  AND DB.DBQYZT = '1'  AND ZD.G2 = A.G2  AND ZD.G3 = A.G3  AND ZD.CHANGJIA= A.CHANGJIA  AND ZD.ZP = A.ZP AND ZD.ZS = A.ZS "+whereStr+" "+str+" "+
		      " AND ((((CASE  WHEN (TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE BLHDL / TO_NUMBER(TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) "+
		       " END))-("+s+"+23*zd.ydshebei))/("+s+"+23*zd.ydshebei))*100> "+bl+" ORDER BY ZD1.SHI,ZD1.XIAN,ZD1.XIAOQU";
			     
				   System.out.println("right开始月份 详细信息： 管理类电表："+sql);
			      db.connectDb();	    
			      rs = db.queryAll( sql.toString());
			      while(rs.next()){
			    	  zdbzbeanB zb=new zdbzbeanB();
			    	  zb.setDbid(rs.getString("DBID"));
			    	//  zb.setAccountmonth(rs.getString("ACCOUNTMONTH"));
			    	    zb.setSHI(rs.getString("SHI"));
			    	    zb.setXian(rs.getString("XIAN"));
			    	    zb.setXiaoqu(rs.getString("XIAOQU"));
			    	    zb.setZdname(rs.getString("JZNAME"));
			    	   zb.setXian1(rs.getString("XIAN1"));
			    	   zb.setShi1(rs.getString("SHI1"));
			    	   zb.setXiaoqu1(rs.getString("XIAOQU1"));
			    	   zb.setBlhdl(rs.getString("BLHDL"));
			    	   zb.setEntrytime(rs.getString("ENTRYTIME"));
			    	    zb.setBl(rs.getString("bl"));
			    	    zb.setId(rs.getString("ID"));
			    	 //   zb.setDbname(rs.getString("DBNAME"));
						list.add(zb);
			      }}
			if("3".equals(yf)&&"3".equals(dbyt)&&"1".equals(gxbzw)){
				
			       sql="SELECT ZD1.ID,ZD1.SHI,ZD1.XIAN,ZD1.XIAOQU,DB.DBID,ZD1.JZNAME,"+
		                " (CASE WHEN ZD1.SHI IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.SHI)  END) SHI1,"+
		     " (CASE WHEN ZD1.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.XIAN)  END) XIAN1,"+
		       " (CASE WHEN ZD1.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD1.XIAOQU)  END) XIAOQU1,"+
		         " TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1, BLHDL,B.ENTRYTIME,"+
		         "  ((((CASE  WHEN (TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE BLHDL / TO_NUMBER(TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) "+
			       " END))-("+s+"+23*zd.ydshebei))/("+s+"+23*zd.ydshebei))*100 bl "+
		          " FROM ZHANDIAN ZD1,DIANBIAO DB, DIANDUVIEW AM, DIANFEIVIEW B, JZXX ZD, ZDNHBZ A "+
		          " WHERE ZD1.ID = DB.ZDID  AND DB.DBID = AM.AMMETERID_FK AND  AM.AMMETERDEGREEID = B.AMMETERDEGREEID_FK  AND ZD.ZDID = TO_CHAR(ZD1.ID) "+
		         " AND ZD.SFGX='1' AND  DB.DBYT = 'dbyt01' AND ZD1.QYZT = '1'  AND DB.DBQYZT = '1' AND ZD1.ZDCQ = 'zdcq01'  AND ZD.G2 = A.G2  AND ZD.G3 = A.G3  AND ZD.CHANGJIA = A.CHANGJIA  AND ZD.ZP = A.ZP AND ZD.ZS = A.ZS "+whereStr+" "+str+" "+
			      " AND ((((CASE  WHEN (TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE BLHDL / TO_NUMBER(TO_DATE(THISDATETIME, 'yyyy-mm-dd') - TO_DATE(LASTDATETIME, 'yyyy-mm-dd') + 1) "+
			       " END))-("+s+"+23*zd.ydshebei))/("+s+"+23*zd.ydshebei))*100> "+bl+" ORDER BY ZD1.SHI,ZD1.XIAN,ZD1.XIAOQU";
			       System.out.println("详细信息的 报账月份："+sql);
			      db.connectDb();	    
			      rs = db.queryAll( sql.toString());
			      while(rs.next()){
			    	  zdbzbeanB zb=new zdbzbeanB();
			    	  zb.setDbid(rs.getString("DBID"));
			    	 // zb.setAccountmonth(rs.getString("ACCOUNTMONTH"));
			    	    zb.setSHI(rs.getString("SHI"));
			    	    zb.setXian(rs.getString("XIAN"));
			    	    zb.setXiaoqu(rs.getString("XIAOQU"));
			    	    zb.setZdname(rs.getString("JZNAME"));
			    	    zb.setXian1(rs.getString("XIAN1"));
			    	    zb.setShi1(rs.getString("SHI1"));
			    	    zb.setXiaoqu1(rs.getString("XIAOQU1"));
			    	    zb.setBlhdl(rs.getString("BLHDL"));
			    	    zb.setEntrytime(rs.getString("ENTRYTIME"));
			    	    zb.setBl(rs.getString("bl"));
			    	    zb.setId(rs.getString("ID"));
						list.add(zb);
			      }}
	    }catch (DbException de) {
	      de.printStackTrace();
	    } catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	      if (rs != null) {
	        try {
	          rs.close();
	        }
	        catch (SQLException se) {
	          se.printStackTrace();
	        }
	      }
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }

	    }

	    return list;
	  }
	public synchronized ArrayList getPageData8(String whereStr) {
		ArrayList list = new ArrayList();	
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql="select DISTINCT zd.zdcode,zd.jzname, (case  when zd.shi is not null then  (select agname from d_area_grade where agcode = zd.shi) else ''end) "+
				       " || (case  when zd.xian is not null then (select distinct agname from d_area_grade where agcode = zd.xian) else  ''end) "+
				       " || (case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = zd.xiaoqu) else '' end) as szdq,"+
				       " (select name from indexs i where i.code = zd.stationtype and i.type = 'stationtype') as stationtype,"+      
				       " (select name from indexs i where i.code = zd.property and i.type = 'ZDSX') as property,"+
				       " (case when zd.bgsign = '0' then '否' else  '是' end) bgsign,"+
				       " (select name from indexs i where i.code = zd.yflx and i.type = 'FWLX') as yflx,"+
				       " (select name from indexs i where i.code = zd.gdfs and i.type = 'GDFS') as gdfs," +
				       "zd.dianfei from zhandian zd where zd.id='"+whereStr+"'";		  
			System.out.println("22222222222"+sql.toString());
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (Exception de) {
			de.printStackTrace();
		}

		finally {
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

		return list;
	}
	public synchronized List<zdbzbeanB> getPageDatapaa(String dbid,String zdid) {		
		List<zdbzbeanB> list = new ArrayList<zdbzbeanB>();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    System.out.println("站点查询和导出："+sql);
	    //调用负责站点条件函数
	    ResultSet rs = null;
		try {
	       sql="SELECT  DISTINCT ZD.ID,ZD.JZNAME,ZD.SHI SHI1,ZD.XIAN XIAN1,ZD.XIAOQU XIAOQU1,zd.zdcode,zd.ENTRYTIME,"+
	       "(select t.name from indexs t where t.code=zd.stationtype ) as stationtype"+
         	",(select t.name from indexs t where t.code=zd.property and type = 'ZDSX') as property"+		
        	",(select t.name from indexs t where t.code=zd.jztype and type = 'ZDLX') as jztype"+
           " ,(CASE WHEN Zd.SHI IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI)  END) SHI,"+
           " (CASE  WHEN ZD.XIAN IS NOT NULL THEN  (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) END) XIAN,"+
           " (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) END) XIAOQU "+
           " FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM WHERE ZD.ID = DB.ZDID  AND DB.DBID = AM.AMMETERID_FK "+
	       " AND ZD.ID='"+zdid+"'  AND DB.DBID='"+dbid+"'";
	       System.out.println("站点查询和导出："+sql);
	      db.connectDb();	    
	      rs = db.queryAll( sql.toString());
	      while(rs.next()){
	    	  zdbzbeanB zb=new zdbzbeanB();
	    	    zb.setSHI(rs.getString("SHI"));
	    	    zb.setXian(rs.getString("XIAN"));
	    	    zb.setXiaoqu(rs.getString("XIAOQU"));
	    	    zb.setZdname(rs.getString("JZNAME"));
	    	    zb.setXian1(rs.getString("XIAN1"));
	    	    zb.setShi1(rs.getString("SHI1"));
	    	    zb.setXiaoqu1(rs.getString("XIAOQU1"));
	    	    zb.setProperty("property");
	    	    zb.setJZTYPE(rs.getString("jztype"));
	    	    zb.setStationtype(rs.getString("stationtype"));
	    	   zb.setZdcode(rs.getString("zdcode"));
	    	   zb.setLrrq(rs.getString("ENTRYTIME"));
	    	   zb.setId(rs.getString("ID"));
	    	    
				list.add(zb);
	      }
	    }catch (DbException de) {
	      de.printStackTrace();
	    } catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	      if (rs != null) {
	        try {
	          rs.close();
	        }
	        catch (SQLException se) {
	          se.printStackTrace();
	        }
	      }
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }

	    }

	    return list;
	  }
	public synchronized List<zdbzbeanB> getPageDatapadl(String whereStr,String yf,String dbyt) {		
		List<zdbzbeanB> list = new ArrayList<zdbzbeanB>();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    System.out.println("===电表信息：");
	    //调用负责站点条件函数
	    ResultSet rs = null;
		try {
			if("3".equals(yf)&&"3".equals(dbyt)){
	       sql="SELECT DW.THISDATETIME,DW.LASTDATETIME,DW.THISDEGREE,DW.LASTDEGREE,ZD.JZNAME,DBB.DBNAME,DBB.DBID,DW.BLHDL,E.ACCOUNTMONTH "+
                " FROM ZHANDIAN ZD, DIANDUVIEW DW, DIANBIAO DBB, DIANFEIVIEW E "+
                 " WHERE DBB.DBID = DW.AMMETERID_FK  AND zd.id=dbb.zdid AND e.ammeterdegreeid_fk=dw.ammeterdegreeid "+whereStr+"";
	       System.out.println("结算电表 报账月份："+sql);
	      db.connectDb();	    
	      rs = db.queryAll( sql.toString());
	      while(rs.next()){
	    	  zdbzbeanB zb=new zdbzbeanB();
	    	    zb.setZdname(rs.getString("JZNAME"));
	    	    zb.setDbname(rs.getString("DBNAME"));
	    	    zb.setSjydl(rs.getString("BLHDL"));
	    	    zb.setBccbsj(rs.getString("THISDATETIME"));
	    	    zb.setSccbsj(rs.getString("LASTDATETIME"));
	    	    zb.setBccb(rs.getString("THISDEGREE"));
	    	    zb.setSccb(rs.getString("LASTDEGREE"));
	    	    zb.setAccountmonth(rs.getString("ACCOUNTMONTH"));
				list.add(zb);
	      }}
			if("2".equals(yf)&&"2".equals(dbyt)){
			       sql="SELECT DW.THISDATETIME,DW.LASTDATETIME,DW.THISDEGREE,DW.LASTDEGREE,DW.STARTMONTH ACCOUNTMONTH,ZD.JZNAME,DBB.DBNAME,DBB.DBID,DW.BLHDL "+
		                " FROM ZHANDIAN ZD, DIANDUVIEW DW, DIANBIAO DBB"+
		                 " WHERE DBB.DBID = DW.AMMETERID_FK  AND zd.id=dbb.zdid AND DBB.DBYT='dbyt03' "+whereStr+"";
			       System.out.println("站点查询和导出："+sql);
			      db.connectDb();	    
			      rs = db.queryAll( sql.toString());
			      while(rs.next()){
			    	  zdbzbeanB zb=new zdbzbeanB();
			    	    zb.setZdname(rs.getString("JZNAME"));
			    	    zb.setDbname(rs.getString("DBNAME"));
			    	    zb.setSjydl(rs.getString("BLHDL"));
			    	    zb.setBccbsj(rs.getString("THISDATETIME"));
			    	    zb.setSccbsj(rs.getString("LASTDATETIME"));
			    	    zb.setBccb(rs.getString("THISDEGREE"));
			    	    zb.setSccb(rs.getString("LASTDEGREE"));
			    	    zb.setAccountmonth(rs.getString("ACCOUNTMONTH"));
						list.add(zb);
			      }}
			if("2".equals(yf)&&"3".equals(dbyt)){
			       sql="SELECT DW.THISDATETIME,DW.LASTDATETIME,DW.THISDEGREE,DW.LASTDEGREE,DW.STARTMONTH ACCOUNTMONTH,ZD.JZNAME,DBB.DBNAME,DBB.DBID,DW.BLHDL "+
		                " FROM ZHANDIAN ZD, DIANDUVIEW DW, DIANBIAO DBB"+
		                 " WHERE DBB.DBID = DW.AMMETERID_FK  AND zd.id=dbb.zdid AND DBB.DBYT='dbyt01' "+whereStr+"";
			       System.out.println("站点查询和导出："+sql);
			      db.connectDb();	    
			      rs = db.queryAll( sql.toString());
			      while(rs.next()){
			    	  zdbzbeanB zb=new zdbzbeanB();
			    	    zb.setZdname(rs.getString("JZNAME"));
			    	    zb.setDbname(rs.getString("DBNAME"));
			    	    zb.setSjydl(rs.getString("BLHDL"));
			    	    zb.setBccbsj(rs.getString("THISDATETIME"));
			    	    zb.setSccbsj(rs.getString("LASTDATETIME"));
			    	    zb.setBccb(rs.getString("THISDEGREE"));
			    	    zb.setSccb(rs.getString("LASTDEGREE"));
			    	    zb.setAccountmonth(rs.getString("ACCOUNTMONTH"));
						list.add(zb);
			      }}
	    }catch (DbException de) {
	      de.printStackTrace();
	    } catch (Exception de) {
	      de.printStackTrace();
	    }finally {
	      if (rs != null) {
	        try {
	          rs.close();
	        }
	        catch (SQLException se) {
	          se.printStackTrace();
	        }
	      }
	      try {
	        db.close();
	      }
	      catch (DbException de) {
	        de.printStackTrace();
	      }

	    }

	    return list;
	  }
//查询电费详细 信息
	  public synchronized AmmeterDegreeFormBean getAmmeterDegreeAllInfo1(String code) {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    String sql1="SELECT T.ITEMVALUE FROM PERMISSION_CONFIGURATION T WHERE T.ITEMNAME='AuditDegree6'";
		    sql.append(
		    		"select (select a.agname from d_area_grade a where a.agcode=zd.shi) ||" +
		    		"(select a.agname from d_area_grade a where a.agcode=zd.xian) ||" +
		    		"(select a.agname from d_area_grade a where a.agcode=zd.xiaoqu) diqu,d.bgsign bb," +
		    		" (select name from indexs where code = zd.stationtype and type = 'stationtype') stationtype," +
		    		"(select name from indexs where code = zd.gsf and type = 'gsf') gsf,"+
                  "(select name from indexs where code = zd.jztype and type = 'ZDLX') jztype," +		    		
		             "(select ind.name from indexs ind where ind.code = d.LINELOSSTYPE and type = 'xslx') xslx,"+
		            "d.linelossvalue xsz,"+
		            "zd.dianfei,"+
		            "zd.Zlfh zlfh,"+
		            "zd.jlfh jlfh,zd.edhdl edhdl,"+
		            "zd.pue pue,"+
		            "(select ind.name from indexs ind where ind.code = zdinfo.fkzq and type = 'FKZQ') fkzq,"+
		    		
		    		"zd.bieming, case when zd.bgsign='1' then '是' else '否' end bgsign,d.dbid, " +
		    		"(select name from indexs where code = d.dbyt and type = 'DBYT') dbyt," +
		    		"d.csds,to_char(d.cssytime,'yyyy-mm-dd') cssytime,d.beilv," +
		    		"(select name from indexs where code = d.ydsb and type = 'YDSB') ydsb," +
		    		"d.dllx,(select name from indexs where code=d.dfzflx and type='dfzflx')as dfzflx from ZDDFINFO  zdinfo, dianbiao d,zhandian zd," +
		    		" d_area_grade dag where d.zdid=zd.id and zd.xian=dag.agcode " +
		    		"  and zd.id =zdinfo.zdid(+)"+code);
		    
		    DataBase db = new DataBase();
		    try {
		    	System.out.println("超标站点管理站点详细信息:"+sql);
		      db.connectDb();
		      ResultSet rs = null,rs1=null;
		      rs1=db.queryAll(sql1.toString());
		      while(rs1.next()){
			    	bean.setItemvalue(rs1.getString("itemvalue") != null ? rs1.getString("itemvalue") : "");
		      }
		      rs = db.queryAll(sql.toString());
		      while (rs.next()) {
		    	bean.setAmmeterid(rs.getString("dbid") != null ? rs.getString("dbid") : "");
		        bean.setDiqu(rs.getString("diqu") != null ? rs.getString("diqu") : "");		        
		        bean.setShifou(rs.getString(2) != null ? rs.getString(2) : "");
		        bean.setFkzq(rs.getString("fkzq") != null ? rs.getString("fkzq") : "");
		        bean.setLinelosstype(rs.getString("xslx") != null ? rs.getString("xslx") : "");
		        bean.setLinelossvalue(rs.getString("xsz") != null ? rs.getString("xsz") : "");
		        bean.setUsageofelectypeid_ammeter(rs.getString("ydsb") != null ? rs.getString("ydsb") : "");
		        bean.setMultiplyingpower(rs.getString("beilv") != null ? rs.getString("beilv") : "");
		        bean.setElectriccurrenttype_ammeter(rs.getString("dllx") != null ? rs.getString("dllx") : "");
		        bean.setAmmeteruse(rs.getString("dbyt") != null ? rs.getString("dbyt") : "");
		        bean.setDfzflx(rs.getString("dfzflx")!=null?rs.getString("dfzflx"):"");
		        bean.setStationtype(rs.getString("stationtype")!=null?rs.getString("stationtype"):"");
		        bean.setGsf(rs.getString("gsf")!=null?rs.getString("gsf"):"");
		        bean.setJztype(rs.getString("jztype")!=null?rs.getString("jztype"):"");
		        bean.setDfzflx(rs.getString("dfzflx") != null ? rs.getString("dfzflx") : "");		        
		        bean.setDianfei(rs.getString("dianfei") != null ? rs.getString("dianfei") : "");			        
		        bean.setXsz(rs.getString("xsz") != null ? rs.getString("xsz") : "");
		        bean.setZlfh(rs.getString("zlfh") != null ? rs.getString("zlfh") : ""); 
		        bean.setJlfh(rs.getString("jlfh") != null ? rs.getString("jlfh") : ""); 
		        bean.setCsds(rs.getString("csds") != null ? rs.getString("csds") : "");
		        bean.setCssytime(rs.getString("cssytime")!=null ? rs.getString("cssytime") : "");
		        bean.setEdhdl(rs.getString("edhdl")!=null ? rs.getString("edhdl") :"");
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
	}
	  //获得最近的电表读数---以报账月份查询
	  public synchronized AmmeterDegreeFormBean getLastAmmeterDegree1(String zdcode,String dbid,String sjyf,String entrytime) throws ParseException {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    //to_number(t.thisdegree) 转换成数字
		    sql.append("SELECT S.LASTDEGREE,TO_CHAR(S.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,S.THISDEGREE,TO_CHAR(S.THISDATETIME,'yyyy-mm-dd') THISDATETIME,TO_CHAR(S.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(S.ENDMONTH,'yyyy-mm') ENDMONTH" +
		    		",S.ACTUALDEGREE YDL,E.ACTUALPAY,S.BLHDL,S.FLOATDEGREE,TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,S.MEMO AS MEMODL,E.MEMO AS MEMODF" +
		    		",TO_CHAR(E.ENTRYTIME,'yyyy-mm-dd') ENTRYTIME,(SELECT NAME FROM INDEXS WHERE E.NOTETYPEID=CODE) AS NOTETYPEID,E.NOTENO,TO_CHAR(E.NOTETIME,'yyyy-mm-dd') NOTETIME,E.INPUTOPERATOR,TO_CHAR(E.PAYDATETIME,'yyyy-mm-dd') PAYDATETIME,E.PAYOPERATOR,E.PJJE" +
		    		",S.NETWORKHDL,S.INFORMATIZATIONHDL,S.ADMINISTRATIVEHDL,S.MARKETHDL,S.BUILDHDL,S.DDDF AS DDDFDL"+
		    		",E.NETWORKDF,E.INFORMATIZATIONDF,E.ADMINISTRATIVEDF,E.MARKETDF,E.BUILDDF,E.DDDF"+
		    		",ZD.PUE PUE,ZD.JLFH JLFH" +
		    		",ZD.ZLFH ZLFH,E.FLOATPAY" +
		    		" FROM DIANDUVIEW S,DIANFEIVIEW E,DIANBIAO DB,ZHANDIAN ZD WHERE " +
		    		" S.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND S.AMMETERID_FK = DB.DBID  AND DB.ZDID = ZD.ID"+
		    		" AND ZD.ID='"+zdcode+"' AND DB.DBID='"+dbid+"' AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm')='"+sjyf+"' AND TO_CHAR(E.ENTRYTIME,'yyyy-mm-dd')='"+entrytime+"'");
		    DataBase db = new DataBase();
		    try {
		     System.out.println("超标基站管理电量电费信息:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      if(rs.next()==true){
		      
		    	//站点信息
		        bean.setLastdegree(rs.getString("LASTDEGREE") != null ? rs.getString("LASTDEGREE") : "");
		        bean.setLastdatetime(rs.getString("LASTDATETIME") != null ? rs.getString("LASTDATETIME") : "");
		        bean.setThisdegree(rs.getString("THISDEGREE") != null ? rs.getString("THISDEGREE") : "");
		        bean.setThisdatetime(rs.getString("THISDATETIME") != null ? rs.getString("THISDATETIME") : "");
		        bean.setStartmonth(rs.getString("STARTMONTH") != null ? rs.getString("STARTMONTH") : "");
		        bean.setEndmonth(rs.getString("ENDMONTH") != null ? rs.getString("ENDMONTH") : "");
		        bean.setYdl(rs.getString("YDL") != null ? rs.getString("YDL") : "");
		        bean.setActualpay(rs.getString("ACTUALPAY") != null ? rs.getString("ACTUALPAY") : "");		        
		        bean.setBlhdl(rs.getString("BLHDL") != null ? rs.getString("BLHDL") : "");
		        bean.setFloatdegree(rs.getString("FLOATDEGREE") != null ? rs.getString("FLOATDEGREE") : "");
		        bean.setAccountmonth(rs.getString("ACCOUNTMONTH") != null ? rs.getString("ACCOUNTMONTH") : "");
		        bean.setMemo(rs.getString("MEMODL") != null ? rs.getString("MEMODL") : "");
		        bean.setMemodf(rs.getString("MEMODF") != null ? rs.getString("MEMODF") : "");
		        bean.setEntrytime(rs.getString("ENTRYTIME") != null ? rs.getString("ENTRYTIME") : "");
		        bean.setNotetypeid(rs.getString("NOTETYPEID") != null ? rs.getString("NOTETYPEID") : "");
		        bean.setNoteno(rs.getString("NOTENO") != null ? rs.getString("NOTENO") : "");
		        bean.setNotetime(rs.getString("NOTETIME") != null ? rs.getString("NOTETIME") : "");
		        bean.setInputoperator(rs.getString("INPUTOPERATOR") != null ? rs.getString("INPUTOPERATOR") : "");
		        bean.setPaydatetime(rs.getString("PAYDATETIME") != null ? rs.getString("PAYDATETIME") : "");
		        bean.setPayoperator(rs.getString("PAYOPERATOR") != null ? rs.getString("PAYOPERATOR") : "");
		        bean.setPjje(rs.getDouble("PJJE"));
		        bean.setScdl(rs.getString("NETWORKHDL") != null ? rs.getString("NETWORKHDL") : "");
		        bean.setXxhdl(rs.getString("INFORMATIZATIONHDL") != null ? rs.getString("INFORMATIZATIONHDL") : "");
		        bean.setBgdl(rs.getString("ADMINISTRATIVEHDL") != null ? rs.getString("ADMINISTRATIVEHDL") : "");
		        bean.setYydl(rs.getString("MARKETHDL") != null ? rs.getString("MARKETHDL") : "");
		        bean.setJstzdl(rs.getString("BUILDHDL") != null ? rs.getString("BUILDHDL") : "");
		        bean.setDddfdl(rs.getString("DDDFDL") != null ? rs.getString("DDDFDL") : "");
		        bean.setScdf(rs.getString("NETWORKDF") != null ? rs.getString("NETWORKDF") : "");
		        bean.setXxhdf(rs.getString("INFORMATIZATIONDF") != null ? rs.getString("INFORMATIZATIONDF") : "");
		        bean.setBgdf(rs.getString("ADMINISTRATIVEDF") != null ? rs.getString("ADMINISTRATIVEDF") : "");
		        bean.setYydf(rs.getString("MARKETDF") != null ? rs.getString("MARKETDF") : "");
		        bean.setJstzdf(rs.getString("BUILDDF") != null ? rs.getString("BUILDDF") : "");
		        bean.setDddfdf(rs.getString("DDDF") != null ? rs.getString("DDDF") : "");
		        bean.setZlfh(rs.getString("ZLFH") != null ? rs.getString("ZLFH") : "");
		        bean.setJlfh(rs.getString("JLFH") != null ? rs.getString("JLFH") : "");
		        bean.setPue(rs.getString("PUE") != null ? rs.getString("PUE") : "");
		        bean.setFloatpay(rs.getString("FLOATPAY") != null ? rs.getString("FLOATPAY") : "");
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  //获得最近的电表读数---以开始月份查询
	  public synchronized AmmeterDegreeFormBean getLastAmmeterDegree2(String zdcode,String dbid,String sjyf,String entrytime) throws ParseException {
			AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		    StringBuffer sql = new StringBuffer();
		    //to_number(t.thisdegree) 转换成数字
		    sql.append("SELECT S.LASTDEGREE,TO_CHAR(S.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,S.THISDEGREE,TO_CHAR(S.THISDATETIME,'yyyy-mm-dd') THISDATETIME,TO_CHAR(S.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(S.ENDMONTH,'yyyy-mm') ENDMONTH, " +
		    		"S.ACTUALDEGREE YDL,'' AS ACTUALPAY,S.BLHDL,S.FLOATDEGREE,'' AS ACCOUNTMONTH,S.MEMO AS MEMODL,'' AS MEMODF" +
		    		",TO_CHAR(S.ENTRYTIME,'yyyy-mm-dd') ENTRYTIME,'' AS NOTETYPEID,'' AS NOTENO,'' AS NOTETIME,'' AS INPUTOPERATOR,'' AS PAYDATETIME,'' AS PAYOPERATOR,'' AS PJJE," +
		    		"S.NETWORKHDL,S.INFORMATIZATIONHDL,S.ADMINISTRATIVEHDL,S.MARKETHDL,S.BUILDHDL,S.DDDF AS DDDFDL,"+
		    		"'' AS NETWORKDF,'' AS INFORMATIZATIONDF,'' AS ADMINISTRATIVEDF,'' AS MARKETDF,'' AS BUILDDF,'' AS DDDF"+
		    		",ZD.PUE PUE,ZD.JLFH JLFH" +
		    		",ZD.ZLFH ZLFH,'' AS FLOATPAY" +
		    		" FROM DIANDUVIEW S,DIANBIAO DB,ZHANDIAN ZD WHERE " +
		    		" S.AMMETERID_FK = DB.DBID  AND DB.ZDID = ZD.ID"+
		    		" AND ZD.ID='"+zdcode+"' AND DB.DBID='"+dbid+"' AND TO_CHAR(S.STARTMONTH,'yyyy-mm')='"+sjyf+"' AND TO_CHAR(S.ENTRYTIME,'yyyy-mm-dd')='"+entrytime+"'");
		    DataBase db = new DataBase();
		    try {
		     System.out.println("超标基站管理电量电费信息:"+sql);
		      db.connectDb();
		      ResultSet rs = null;
		      rs = db.queryAll(sql.toString());
		      if(rs.next()==true){
		      
		    	//站点信息
			        bean.setLastdegree(rs.getString("LASTDEGREE") != null ? rs.getString("LASTDEGREE") : "");
			        bean.setLastdatetime(rs.getString("LASTDATETIME") != null ? rs.getString("LASTDATETIME") : "");
			        bean.setThisdegree(rs.getString("THISDEGREE") != null ? rs.getString("THISDEGREE") : "");
			        bean.setThisdatetime(rs.getString("THISDATETIME") != null ? rs.getString("THISDATETIME") : "");
			        bean.setStartmonth(rs.getString("STARTMONTH") != null ? rs.getString("STARTMONTH") : "");
			        bean.setEndmonth(rs.getString("ENDMONTH") != null ? rs.getString("ENDMONTH") : "");
			        bean.setYdl(rs.getString("YDL") != null ? rs.getString("YDL") : "");
			        bean.setActualpay(rs.getString("ACTUALPAY") != null ? rs.getString("ACTUALPAY") : "");
			        bean.setBlhdl(rs.getString("BLHDL") != null ? rs.getString("BLHDL") : "");
			        bean.setFloatdegree(rs.getString("FLOATDEGREE") != null ? rs.getString("FLOATDEGREE") : "");
			        bean.setAccountmonth(rs.getString("ACCOUNTMONTH") != null ? rs.getString("ACCOUNTMONTH") : "");
			        bean.setMemo(rs.getString("MEMODL") != null ? rs.getString("MEMODL") : "");
			        bean.setMemodf(rs.getString("MEMODF") != null ? rs.getString("MEMODF") : "");
			        bean.setEntrytime(rs.getString("ENTRYTIME") != null ? rs.getString("ENTRYTIME") : "");
			        bean.setNotetypeid(rs.getString("NOTETYPEID") != null ? rs.getString("NOTETYPEID") : "");
			        bean.setNoteno(rs.getString("NOTENO") != null ? rs.getString("NOTENO") : "");
			        bean.setNotetime(rs.getString("NOTETIME") != null ? rs.getString("NOTETIME") : "");
			        bean.setInputoperator(rs.getString("INPUTOPERATOR") != null ? rs.getString("INPUTOPERATOR") : "");
			        bean.setPaydatetime(rs.getString("PAYDATETIME") != null ? rs.getString("PAYDATETIME") : "");
			        bean.setPayoperator(rs.getString("PAYOPERATOR") != null ? rs.getString("PAYOPERATOR") : "");
			        bean.setPjje(rs.getDouble("PJJE"));
			        bean.setScdl(rs.getString("NETWORKHDL") != null ? rs.getString("NETWORKHDL") : "");
			        bean.setXxhdl(rs.getString("INFORMATIZATIONHDL") != null ? rs.getString("INFORMATIZATIONHDL") : "");
			        bean.setBgdl(rs.getString("ADMINISTRATIVEHDL") != null ? rs.getString("ADMINISTRATIVEHDL") : "");
			        bean.setYydl(rs.getString("MARKETHDL") != null ? rs.getString("MARKETHDL") : "");
			        bean.setJstzdl(rs.getString("BUILDHDL") != null ? rs.getString("BUILDHDL") : "");
			        bean.setDddfdl(rs.getString("DDDFDL") != null ? rs.getString("DDDFDL") : "");
			        bean.setScdf(rs.getString("NETWORKDF") != null ? rs.getString("NETWORKDF") : "");
			        bean.setXxhdf(rs.getString("INFORMATIZATIONDF") != null ? rs.getString("INFORMATIZATIONDF") : "");
			        bean.setBgdf(rs.getString("ADMINISTRATIVEDF") != null ? rs.getString("ADMINISTRATIVEDF") : "");
			        bean.setYydf(rs.getString("MARKETDF") != null ? rs.getString("MARKETDF") : "");
			        bean.setJstzdf(rs.getString("BUILDDF") != null ? rs.getString("BUILDDF") : "");
			        bean.setDddfdf(rs.getString("DDDF") != null ? rs.getString("DDDF") : "");
			        bean.setZlfh(rs.getString("ZLFH") != null ? rs.getString("ZLFH") : "");
			        bean.setJlfh(rs.getString("JLFH") != null ? rs.getString("JLFH") : "");
			        bean.setPue(rs.getString("PUE") != null ? rs.getString("PUE") : "");
			        bean.setFloatpay(rs.getString("FLOATPAY") != null ? rs.getString("FLOATPAY") : "");
		      }
		      rs.close();
		    }
		    catch (DbException de) {
		      de.printStackTrace();
		    }
		    catch (SQLException se) {
		      se.printStackTrace();
		    }
		    finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
		    }
		    return bean;
		  }
	  //历史信息
		public synchronized List<zdbzbeanB> getlishixinxi(String zdid,String dbid,String yf,String entrytime) {		
			List<zdbzbeanB> list = new ArrayList<zdbzbeanB>();
		    CTime ct = new CTime();
		    String kjnd = ct.formatShortDate().substring(0, 4);
		    String sql = "";
		    String fzzdstr = "";
		    DataBase db = new DataBase();
		    //调用负责站点条件函数
		    ResultSet rs = null;
			try {
				if("2".equals(yf)){
				       sql="SELECT A.JZNAME,A.ID,A.DBNAME,A.DBID,TO_CHAR(A.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,A.LASTDEGREE,TO_CHAR(A.THISDATETIME,'yyyy-mm-dd') THISDATETIME,A.THISDEGREE,A.ZQ,A.BLHDL,A.RJDL,TO_CHAR(A.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(A.ENDMONTH,'yyyy-mm') ENDMONTH" +
			       		" FROM(SELECT ZD.JZNAME,ZD.ID,DB.DBNAME,DB.DBID,AM.LASTDATETIME,AM.LASTDEGREE,AM.THISDATETIME,AM.THISDEGREE," +
			       		"(THISDATETIME) - LASTDATETIME + 1) AS ZQ, AM.BLHDL," +
			       		"((CASE  WHEN (THISDATETIME - LASTDATETIME + 1) = 0 THEN 0 " +
			       		"ELSE BLHDL / TO_NUMBER(THISDATETIME - LASTDATETIME + 1) END)) AS RJDL,AM.STARTMONTH," +
			       		"AM.ENDMONTH FROM ZHANDIAN ZD, DIANBIAO DB, DIANDUVIEW AM WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK" +
			       		" AND DB.ZDID='"+zdid+"' AND DB.DBID='"+dbid+"' AND TO_CHAR(AM.ENTRYTIME,'yyyy-mm-dd')<'"+entrytime+"' ORDER BY AM.ENTRYTIME DESC) A WHERE ROWNUM<4";
				}else if("3".equals(yf)){
				       sql="SELECT A.JZNAME,A.ID,A.DBNAME,A.DBID,TO_CHAR(A.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,A.LASTDEGREE,TO_CHAR(A.THISDATETIME,'yyyy-mm-dd') THISDATETIME,A.THISDEGREE,A.ZQ,A.BLHDL,A.RJDL,,TO_CHAR(A.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(A.ENDMONTH,'yyyy-mm') ENDMONTH" +
			       		" FROM(SELECT ZD.JZNAME,ZD.ID,DB.DBNAME,DB.DBID,AM.LASTDATETIME,AM.LASTDEGREE,AM.THISDATETIME,AM.THISDEGREE," +
			       		"(THISDATETIME - LASTDATETIME + 1) AS ZQ, AM.BLHDL," +
			       		"((CASE  WHEN (THISDATETIME - LASTDATETIME + 1) = 0 THEN 0 " +
			       		"ELSE BLHDL / TO_NUMBER(THISDATETIME - LASTDATETIME + 1) END)) AS RJDL,AM.STARTMONTH," +
			       		"AM.ENDMONTH FROM ZHANDIAN ZD, DIANBIAO DB, DIANDUVIEW AM,DIANFEIVIEW EF WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID=EF.AMMETERDEGREEID_FK" +
			       		" AND DB.ZDID='"+zdid+"' AND DB.DBID='"+dbid+"' AND TO_CHAR(EF.ENTRYTIME,'yyyy-mm')<'"+entrytime+"' ORDER BY EF.ENTRYTIME DESC) A WHERE ROWNUM<4";
				}

		      System.out.println("历史信息："+sql);
		      db.connectDb();	    
		      rs = db.queryAll( sql.toString());
		      while(rs.next()){
		    	  	zdbzbeanB zb=new zdbzbeanB();
		    	    zb.setZdname(rs.getString("JZNAME") != null ? rs.getString("JZNAME") : "");
		    	    zb.setId(rs.getString("ID") != null ? rs.getString("ID") : "");
		    	    zb.setDbname(rs.getString("DBNAME") != null ? rs.getString("DBNAME") : "");
		    	    zb.setDbid(rs.getString("DBID") != null ? rs.getString("DBID") : "");
		    	    zb.setSccbsj(rs.getString("LASTDATETIME") != null ? rs.getString("LASTDATETIME") : "");
		    	    zb.setSccb(rs.getString("LASTDEGREE") != null ? rs.getString("LASTDEGREE") : "");
		    	    zb.setBccbsj(rs.getString("THISDATETIME") != null ? rs.getString("THISDATETIME") : "");
		    	    zb.setBccb(rs.getString("THISDEGREE") != null ? rs.getString("THISDEGREE") : "");
		    	    zb.setJs(rs.getString("ZQ") != null ? rs.getString("ZQ") : "");
		    	    zb.setBlhdl(rs.getString("BLHDL") != null ? rs.getString("BLHDL") : "");
		    	    zb.setRjdl(rs.getString("RJDL") != null ? rs.getString("RJDL") : "");
		    	    zb.setStartmonth(rs.getString("STARTMONTH") != null ? rs.getString("STARTMONTH") : "");
		    	    zb.setEndmonth(rs.getString("ENDMONTH") != null ? rs.getString("ENDMONTH") : "");
					list.add(zb);
		      }
		    }catch (DbException de) {
		      de.printStackTrace();
		    } catch (Exception de) {
		      de.printStackTrace();
		    }finally {
		      if (rs != null) {
		        try {
		          rs.close();
		        }
		        catch (SQLException se) {
		          se.printStackTrace();
		        }
		      }
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }

		    }

		    return list;
		  }
	//超标基站管理信息导出
	public synchronized List<zdbzbeanB> getPageDatapdc(String whereStr,String bl,String yy,String month,String dyt) {		
			List<zdbzbeanB> list = new ArrayList<zdbzbeanB>();
		    CTime ct = new CTime();
		    String sql = "",sql1="";
		    String fzzdstr = "";
		    DataBase db = new DataBase();
		    String s=month.substring(5,7);
		    if(s.equals("01")){s="month1";}
		    if(s.equals("02")){s="month2";}
		    if(s.equals("03")){s="month3";}
		    if(s.equals("04")){s="month4";}
		    if(s.equals("05")){s="month5";}
		    if(s.equals("06")){s="month6";}
		    if(s.equals("07")){s="month7";}
		    if(s.equals("08")){s="month8";}
		    if(s.equals("09")){s="month9";}
		    if(s.equals("10")){s="month10";}
		    if(s.equals("11")){s="month11";}
		    if(s.equals("12")){s="month12";}
		    String sql2="";
		    ResultSet rs = null;
			try {
	           if(yy.equals("3")&&"3".equals(dyt)){
	          sql2="SELECT TAB.G2,TAB.G3,TAB.CHANGJIA,TAB.ZP,TAB.ZS,"+s+",TAB.SHI,TAB.XIAN,TAB.XIAOQU,TAB.ID,TAB.JZNAME,TO_CHAR(TAB.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME," +
	          	"TAB.LASTDEGREE,TO_CHAR(TAB.THISDATETIME,'yyyy-mm-dd') THISDATETIME,TAB.THISDEGREE,TAB.BLHDL,TAB.ZQ,TAB.RJDL,TAB.BL FROM (SELECT S.AGNAME AS SHI,X.AGNAME AS XIAN," +
	          	"XQ.AGNAME AS XIAOQU,ZD1.JZNAME,ZD1.ID,AM.LASTDATETIME,AM.LASTDEGREE, AM.THISDATETIME,AM.THISDEGREE,A.G2, A.G3,A.CHANGJIA," +
	          	"A.ZP,A.ZS,(AM.THISDATETIME, - AM.LASTDATETIME + 1) AS ZQ,AM.BLHDL,"+
	            " (((CASE WHEN (AM.THISDATETIME - AM.LASTDATETIME + 1) = 0 "+
	            " THEN  0 ELSE BLHDL /TO_NUMBER(THISDATETIME - LASTDATETIME + 1) END ))) AS RJDL,"+
	            "((((CASE  WHEN (AM.THISDATETIME - AM.LASTDATETIME) + 1) = 0 THEN 0 " +
	            "ELSE AM.BLHDL / TO_NUMBER(AM.THISDATETIME - AM.LASTDATETIME) + 1) END))-"+s+")/"+s+")*100 bl, "+
				" "+s+" FROM ZHANDIAN  ZD1, DIANBIAO  DB, DIANDUVIEW  AM,DIANFEIVIEW B,ZHAND1 ZD,ZDNHBZ A,D_AREA_GRADE S,D_AREA_GRADE X,D_AREA_GRADE XQ "+
				" WHERE ZD1.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = B.AMMETERDEGREEID_FK AND ZD.ZDID = TO_CHAR(ZD1.ID) "+whereStr+" "+
				" AND ZD1.SHI=S.AGCODE AND ZD1.XIAN=X.AGCODE AND ZD1.XIAOQU=XQ.AGCODE AND DB.DBYT = 'dbyt01' AND  ZD1.ZDCQ = 'zdcq01'"+
				" AND ZD.G2 = A.G2  AND ZD.G3 = A.G3  AND ZD.G3CJ = A.CHANGJIA AND ZD.ZP11 = A.ZP  AND ZD.ZS11 = A.ZS) TAB "+
				" WHERE ((TAB.RJDL-"+s+")/"+s+")*100>"+bl+""+
				" ORDER BY ZP,ZS";				
			  System.out.println("超标基站管理信息导出--报账月份："+sql2);
		      db.connectDb();	    
		      rs = db.queryAll(sql2.toString());
		      while(rs.next()){
		    	  zdbzbeanB zb=new zdbzbeanB();
		    	  	zb.setZdname(rs.getString("JZNAME") != null ? rs.getString("JZNAME") : "");
		    	    zb.setG2(rs.getString("G2") != null ? rs.getString("G2") : "");
					zb.setG3(rs.getString("G3") != null ? rs.getString("G3") : "");
					zb.setCHANGJIA(rs.getString("CHANGJIA") != null ? rs.getString("CHANGJIA") : "");
					zb.setZP(rs.getInt("ZP"));
					zb.setZS(rs.getInt("ZS"));					
					zb.setEDGL(Double.parseDouble(rs.getString(s) != null ? rs.getString(s) : ""));
					zb.setSHI(rs.getString("SHI") != null ? rs.getString("SHI") : "");
					zb.setXian(rs.getString("XIAN") != null ? rs.getString("XIAN") : "");
					zb.setXiaoqu(rs.getString("XIAOQU") != null ? rs.getString("XIAOQU") : "");
					zb.setId(rs.getString("ID") != null ? rs.getString("ID") : "");
					zb.setZdname(rs.getString("JZNAME") != null ? rs.getString("JZNAME") : "");
					zb.setSccbsj(rs.getString("LASTDATETIME") != null ? rs.getString("LASTDATETIME") : "");
					zb.setSccb(rs.getString("LASTDEGREE") != null ? rs.getString("LASTDEGREE") : "");
					zb.setBccbsj(rs.getString("THISDATETIME") != null ? rs.getString("THISDATETIME") : "");
					zb.setBccb(rs.getString("THISDEGREE") != null ? rs.getString("THISDEGREE") : "");
					zb.setBlhdl(rs.getString("BLHDL") != null ? rs.getString("BLHDL") : "");
					zb.setJs(rs.getString("ZQ") != null ? rs.getString("ZQ") : "");
					zb.setRjdl(rs.getString("RJDL") != null ? rs.getString("RJDL") : "");
					zb.setBl(rs.getString("BL") != null ? rs.getString("BL") : "");
					list.add(zb);
		      }}
	           if(yy.equals("2")&&"2".equals(dyt)){
	   				sql1="SELECT TAB.G2,TAB.G3,TAB.CHANGJIA,TAB.ZP,TAB.ZS,"+s+",TAB.SHI,TAB.XIAN,TAB.XIAOQU,TAB.ID,TAB.JZNAME,TO_CHAR(TAB.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME," +
	   					"TAB.LASTDEGREE,TO_CHAR(TAB.THISDATETIME,'yyyy-mm-dd') THISDATETIME,TAB.THISDEGREE,TAB.BLHDL,TAB.ZQ,TAB.RJDL,TAB.BL FROM (SELECT S.AGNAME AS SHI,X.AGNAME AS XIAN," +
	   					"XQ.AGNAME AS XIAOQU,ZD1.JZNAME,ZD1.ID,B.LASTDATETIME,B.LASTDEGREE, B.THISDATETIME,B.THISDEGREE, A.G2,A.G3,A.CHANGJIA," +
	   					"A.ZP, A.ZS,(B.THISDATETIME - B.LASTDATETIME + 1) AS ZQ, BLHDL,"+
	                    "(((CASE WHEN (B.THISDATETIME - B.LASTDATETIME + 1) = 0 "+
	                    " THEN  0 ELSE B.BLHDL /TO_NUMBER(B.THISDATETIME - B.LASTDATETIME + 1) END ))) AS RJDL,"+
	    	            "((((CASE  WHEN (B.THISDATETIME - B.LASTDATETIME + 1) = 0 THEN 0 " +
	    	            "ELSE B.BLHDL / TO_NUMBER(B.THISDATETIME - B.LASTDATETIME + 1) END))-"+s+")/"+s+")*100 bl, "+
	                    " "+s+" FROM ZHANDIAN ZD1, DIANBIAO DB, DIANDUVIEW B, ZHAND1 ZD, ZDNHBZ A,D_AREA_GRADE S,D_AREA_GRADE X,D_AREA_GRADE XQ "+
	                    "  WHERE ZD.ZP11 = A.ZP AND ZD.ZS11 = A.ZS AND ZD1.ID = DB.ZDID  AND DB.DBID = B.AMMETERID_FK  AND ZD.ZDID = TO_CHAR(ZD1.ID) " +
	                    "AND ZD1.SHI=S.AGCODE AND ZD1.XIAN=X.AGCODE AND ZD1.XIAOQU=XQ.AGCODE AND DB.DBYT = 'dbyt03' AND ZD.G2 = A.G2  AND ZD.G3 = A.G3 " +
	                    "AND ZD.G3CJ = A.CHANGJIA AND ZD1.ZDCQ = 'zdcq01' "+whereStr+" ) TAB WHERE ((TAB.RJDL-"+s+")/"+s+")*100>"+bl+"  ORDER BY ZP,ZS";
	   			System.out.println("超标基站管理信息导出--开始月份、管理电表："+sql1);
	   	      db.connectDb();	    
	   	      rs = db.queryAll(sql1.toString());
	   	      while(rs.next()){
	   	    	  zdbzbeanB zb=new zdbzbeanB();
		    	  	zb.setZdname(rs.getString("JZNAME") != null ? rs.getString("JZNAME") : "");
		    	    zb.setG2(rs.getString("G2") != null ? rs.getString("G2") : "");
					zb.setG3(rs.getString("G3") != null ? rs.getString("G3") : "");
					zb.setCHANGJIA(rs.getString("CHANGJIA") != null ? rs.getString("CHANGJIA") : "");
					zb.setZP(rs.getInt("ZP"));
					zb.setZS(rs.getInt("ZS"));					
					zb.setEDGL(Double.parseDouble(rs.getString(s) != null ? rs.getString(s) : ""));
					zb.setSHI(rs.getString("SHI") != null ? rs.getString("SHI") : "");
					zb.setXian(rs.getString("XIAN") != null ? rs.getString("XIAN") : "");
					zb.setXiaoqu(rs.getString("XIAOQU") != null ? rs.getString("XIAOQU") : "");
					zb.setId(rs.getString("ID") != null ? rs.getString("ID") : "");
					zb.setZdname(rs.getString("JZNAME") != null ? rs.getString("JZNAME") : "");
					zb.setSccbsj(rs.getString("LASTDATETIME") != null ? rs.getString("LASTDATETIME") : "");
					zb.setSccb(rs.getString("LASTDEGREE") != null ? rs.getString("LASTDEGREE") : "");
					zb.setBccbsj(rs.getString("THISDATETIME") != null ? rs.getString("THISDATETIME") : "");
					zb.setBccb(rs.getString("THISDEGREE") != null ? rs.getString("THISDEGREE") : "");
					zb.setBlhdl(rs.getString("BLHDL") != null ? rs.getString("BLHDL") : "");
					zb.setJs(rs.getString("ZQ") != null ? rs.getString("ZQ") : "");
					zb.setRjdl(rs.getString("RJDL") != null ? rs.getString("RJDL") : "");
					zb.setBl(rs.getString("BL") != null ? rs.getString("BL") : "");
	   				list.add(zb);
	   	      }}
	           if(yy.equals("2")&&"3".equals(dyt)){
	        	   sql1="SELECT TAB.G2,TAB.G3,TAB.CHANGJIA,TAB.ZP,TAB.ZS,"+s+",TAB.SHI,TAB.XIAN,TAB.XIAOQU,TAB.ID,TAB.JZNAME,TO_CHAR(TAB.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME," +
	        	   "TAB.LASTDEGREE,TO_CHAR(TAB.THISDATETIME,'yyyy-mm-dd') THISDATETIME,TAB.THISDEGREE,TAB.BLHDL,TAB.ZQ,TAB.RJDL,TAB.BL FROM (SELECT S.AGNAME AS SHI,X.AGNAME AS XIAN," +
	        	   "XQ.AGNAME AS XIAOQU,ZD1.JZNAME,ZD1.ID,B.LASTDATETIME,B.LASTDEGREE, B.THISDATETIME,B.THISDEGREE, A.G2,A.G3,A.CHANGJIA," +
	        	   "A.ZP, A.ZS,(B.THISDATETIME - B.LASTDATETIME + 1) AS ZQ, BLHDL,"+
                   " (((CASE WHEN (B.THISDATETIME - B.LASTDATETIME + 1) = 0 "+
                   " THEN  0 ELSE B.BLHDL /TO_NUMBER(TB.THISDATETIME - B.LASTDATETIME + 1) END ))) AS RJDL,"+
   	               "((((CASE  WHEN (B.THISDATETIME - B.LASTDATETIME + 1) = 0 THEN 0 " +
   	               "ELSE B.BLHDL / TO_NUMBER(B.THISDATETIME - B.LASTDATETIME + 1) END))-"+s+")/"+s+")*100 bl, "+
	               " "+s+" FROM ZHANDIAN ZD1, DIANBIAO DB, DIANDUVIEW B, ZHAND1 ZD, ZDNHBZ A,D_AREA_GRADE S,D_AREA_GRADE X,D_AREA_GRADE XQ "+
	               " WHERE  ZD.ZP11 = A.ZP AND ZD.ZS11 = A.ZS AND ZD1.ID = DB.ZDID  AND DB.DBID = B.AMMETERID_FK  AND ZD.ZDID = TO_CHAR(ZD1.ID) " +
	               "AND ZD1.SHI=S.AGCODE AND ZD1.XIAN=X.AGCODE AND ZD1.XIAOQU=XQ.AGCODE  AND DB.DBYT = 'dbyt01' AND ZD.G2 = A.G2  AND ZD.G3 = A.G3  " +
	               "AND ZD.G3CJ = A.CHANGJIA AND ZD1.ZDCQ = 'zdcq01' "+whereStr+" ) TAB WHERE ((TAB.RJDL-"+s+")/"+s+")*100>"+bl+" ORDER BY ZP, ZS";
	   			
	   		  System.out.println("超标基站管理信息导出--开始月份、结算电表"+sql1);
	   	      db.connectDb();	    
	   	      rs = db.queryAll(sql1.toString());
	   	      while(rs.next()){
	   	    	  zdbzbeanB zb=new zdbzbeanB();
		    	  	zb.setZdname(rs.getString("JZNAME") != null ? rs.getString("JZNAME") : "");
		    	    zb.setG2(rs.getString("G2") != null ? rs.getString("G2") : "");
					zb.setG3(rs.getString("G3") != null ? rs.getString("G3") : "");
					zb.setCHANGJIA(rs.getString("CHANGJIA") != null ? rs.getString("CHANGJIA") : "");
					zb.setZP(rs.getInt("ZP"));
					zb.setZS(rs.getInt("ZS"));					
					zb.setEDGL(Double.parseDouble(rs.getString(s) != null ? rs.getString(s) : ""));
					zb.setSHI(rs.getString("SHI") != null ? rs.getString("SHI") : "");
					zb.setXian(rs.getString("XIAN") != null ? rs.getString("XIAN") : "");
					zb.setXiaoqu(rs.getString("XIAOQU") != null ? rs.getString("XIAOQU") : "");
					zb.setId(rs.getString("ID") != null ? rs.getString("ID") : "");
					zb.setZdname(rs.getString("JZNAME") != null ? rs.getString("JZNAME") : "");
					zb.setSccbsj(rs.getString("LASTDATETIME") != null ? rs.getString("LASTDATETIME") : "");
					zb.setSccb(rs.getString("LASTDEGREE") != null ? rs.getString("LASTDEGREE") : "");
					zb.setBccbsj(rs.getString("THISDATETIME") != null ? rs.getString("THISDATETIME") : "");
					zb.setBccb(rs.getString("THISDEGREE") != null ? rs.getString("THISDEGREE") : "");
					zb.setBlhdl(rs.getString("BLHDL") != null ? rs.getString("BLHDL") : "");
					zb.setJs(rs.getString("ZQ") != null ? rs.getString("ZQ") : "");
					zb.setRjdl(rs.getString("RJDL") != null ? rs.getString("RJDL") : "");
					zb.setBl(rs.getString("BL") != null ? rs.getString("BL") : "");
	   				list.add(zb);
	   	      }}
		    }catch (DbException de) {
		      de.printStackTrace();
		    } catch (Exception de) {
		      de.printStackTrace();
		    }finally {
		      if (rs != null) {
		        try {
		          rs.close();
		        }
		        catch (SQLException se) {
		          se.printStackTrace();
		        }
		      }
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }

		    }
			 
		    return list;
		  }

/**
 * 超标站点查询
 * @param whereStr：地市、区县、小区、站点属性
 * @param str：超标比例
 * @param str1：报账月份
 * @return：查询出来的站点的信息
 */
	public synchronized List<zdbzbeanB> getPageDatacbcx(String whereStr,String str,String str1,String str2,String str3,StringBuffer str4,String str5,String loginId) {		
			List<zdbzbeanB> list = new ArrayList<zdbzbeanB>();
		    DataBase db = new DataBase();
		    String sql = "";
		    String sql1 = "";
		    String sql2="";
		    String sql3="";
		    ResultSet rs = null;
			try {
				//以前取数SQL语句  已不用
			 sql1 = "SELECT ZD.ID,ZD.JZNAME,P.NAME AS PROPERTY,S.AGNAME AS SHI,X.AGNAME AS XIAN," +
			 		"XQ.AGNAME AS XIAOQU,ZD.ZLFH,ZD.JLFH,ZD.EDHDL,ZDDL.BZ AS QSDBDL,ZDDL.DL,ZDDL.BL" +
			 		",DECODE(CB.ZDID,NULL,1,'',1,0) AS BZ, ZDDL.DLCB,(ZDDL.DLCB * 0.9) AS DFCB FROM ZHANDIAN ZD LEFT JOIN INDEXS P ON ZD.PROPERTY = P.CODE" +
			 		" AND P.TYPE = 'ZDSX' LEFT JOIN D_AREA_GRADE S ON ZD.SHI = S.AGCODE " +
			 		"LEFT JOIN D_AREA_GRADE X ON ZD.XIAN = X.AGCODE LEFT JOIN D_AREA_GRADE XQ ON ZD.XIAOQU = XQ.AGCODE" +
			 		" LEFT JOIN CBZD CB ON ZD.ID = CB.ZDID " +str2+
			 		",(SELECT AB.ZDID, AB.DL,AB.BZ,DECODE(AB.BZ,'0',AB.DL,(AB.DL - AB.BZ) / AB.BZ) AS BL , " +
			 		" DECODE(AB.BZ, '0', AB.DL, AB.DL - AB.BZ) AS DLCB " +
			 		"FROM (SELECT JZ.ZDID,DECODE(JZ.JSDL, '', JZ.GLDL, '0', JZ.GLDL, JZ.JSDL) DL," +
			 		"JZ.QSDBDL AS BZ FROM JZXX JZ WHERE 1=1 "+str5+") AB) ZDDL" +
			 		" WHERE ZD.ID = ZDDL.ZDID AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+str4;
	         //现有SQL 
			 sql2 = "SELECT ZD.ID,ZD.JZNAME,P.NAME AS PROPERTY,S.AGNAME AS SHI,X.AGNAME AS XIAN," +
		 		"XQ.AGNAME AS XIAOQU,ZDDL.ZLFH,ZDDL.JLFH,ZDDL.EDHDL,ZDDL.BZ AS QSDBDL,ZDDL.DL,ZDDL.BL" +
		 		",DECODE(CB.ZDID,NULL,1,'',1,0) AS BZ, ZDDL.DLCB,(ZDDL.DLCB * 0.9) AS DFCB,zddl.bzz  FROM ZHANDIAN ZD LEFT JOIN INDEXS P ON ZD.PROPERTY = P.CODE" +
		 		" AND P.TYPE = 'ZDSX' LEFT JOIN D_AREA_GRADE S ON ZD.SHI = S.AGCODE " +
		 		"LEFT JOIN D_AREA_GRADE X ON ZD.XIAN = X.AGCODE LEFT JOIN D_AREA_GRADE XQ ON ZD.XIAOQU = XQ.AGCODE" +
		 		" LEFT JOIN CBZD CB ON ZD.ID = CB.ZDID " +str2+
		 		",(SELECT AB.ZDID, AB.DL,AB.BZ,(CASE WHEN AB.BZZ<>0 THEN (AB.DL-AB.BZZ)/AB.BZZ END) AS BL," +
		 		" (CASE WHEN ab.hbzq>0 THEN (ab.dl/ab.hbzq-ab.scb) END)dlcb,ab.edhdl,ab.jlfh,ab.zlfh,ab.hbzq,ab.scb,ab.bzz " +
		 		"FROM (SELECT  ZD.ID ZDID,SUM(AM.BLHDL) DL,SUM(AM.BZZ)BZZ,AM.SCB,SUM(AM.HBZQ) HBZQ,EF.EDHDL,EF.JLFH,EF.ZLFH,EF.QSDBDL BZ" +
		 		" FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF " +
		 		" WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK " +
		 		" AND DB.DBYT = 'dbyt01' AND EF.MANUALAUDITSTATUS ='2'  AND ZD.STATIONTYPE<>'StationType41' AND ZD.JZNAME NOT LIKE '%回收%' "+str5+" GROUP BY ZD.ID,AM.SCB, EF.EDHDL, EF.JLFH, EF.ZLFH, EF.QSDBDL) AB) ZDDL" +
		 		" WHERE ZD.ID = ZDDL.ZDID  AND ZD.JZNAME NOT LIKE '%回收%' AND ZD.STATIONTYPE <> 'StationType41' AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+str4;
			//以前取数SQL语句  已不用
			 sql3 = "SELECT ZD.ID,ZD.JZNAME,P.NAME AS PROPERTY,S.AGNAME AS SHI,X.AGNAME AS XIAN," +
		 		"XQ.AGNAME AS XIAOQU,ZDDL.ZLFH,ZDDL.JLFH,ZDDL.EDHDL,ZDDL.BZ AS QSDBDL,ZDDL.DL,ZDDL.BL" +
		 		",DECODE(CB.ZDID,NULL,1,'',1,0) AS BZ, ZDDL.DLCB,(ZDDL.DLCB * 0.9) AS DFCB,zddl.bzz  FROM ZHANDIAN ZD LEFT JOIN INDEXS P ON ZD.PROPERTY = P.CODE" +
		 		" AND P.TYPE = 'ZDSX' LEFT JOIN D_AREA_GRADE S ON ZD.SHI = S.AGCODE " +
		 		"LEFT JOIN D_AREA_GRADE X ON ZD.XIAN = X.AGCODE LEFT JOIN D_AREA_GRADE XQ ON ZD.XIAOQU = XQ.AGCODE" +
		 		" LEFT JOIN CBZD CB ON ZD.ID = CB.ZDID " +str2+
		 		",(SELECT AB.ZDID, AB.DL,AB.BZ,(CASE WHEN AB.BZZ<>0 THEN (AB.DL-AB.BZZ)/AB.BZZ END) AS BL," +
		 		" (CASE WHEN ab.hbzq>0 THEN (ab.dl/ab.hbzq-ab.scb) END)dlcb,ab.edhdl,ab.jlfh,ab.zlfh,ab.hbzq,ab.scb,ab.bzz " +
		 		"FROM (SELECT JZ.ZDID,DECODE(JZ.dbydl,'',JZ.dbgldl,'0',JZ.dbgldl,jz.dbydl) DL,jz.bzz,JZ.SCB,jz.daycount hbzq,jz.EDHDL,jz.zlfh,jz.jlfh," +
		 		"JZ.zdqsdbdl AS BZ FROM JZXX JZ WHERE 1=1 "+str5+") AB) ZDDL" +
		 		" WHERE ZD.ID = ZDDL.ZDID AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+str4;
			 
			 
			 
			//以前取数SQL语句  已不用
			 sql = "SELECT ZD.ID,ZD.JZNAME,P.NAME AS PROPERTY,S.AGNAME AS SHI,X.AGNAME AS XIAN,XQ.AGNAME AS XIAOQU," +
	          		"ZD.ZLFH,ZD.JLFH,ZD.EDHDL,ZD.QSDBDL,ZDDL.DL,DECODE(TO_NUMBER(ZD.QSDBDL),0,ZDDL.DL,(ZDDL.DL - ZD.QSDBDL) / ZD.QSDBDL) AS BL," +
	          		"DECODE(CB.ZDID,NULL,1,'',1,0) AS BZ"+
	          		" FROM ZHANDIAN ZD LEFT JOIN INDEXS P ON ZD.PROPERTY = P.CODE AND P.TYPE = 'ZDSX' " +
	          		"LEFT JOIN D_AREA_GRADE S ON ZD.SHI = S.AGCODE LEFT JOIN D_AREA_GRADE X ON ZD.XIAN = X.AGCODE " +
	          		"LEFT JOIN D_AREA_GRADE XQ ON ZD.XIAOQU = XQ.AGCODE " +
	          		"LEFT JOIN (SELECT ZD.ID,SUM(AM.BLHDL /" +
	          		" (CASE WHEN LENGTH(AM.THISDATETIME) = 10 AND LENGTH(AM.LASTDATETIME) = 10 " +
	          		"THEN TO_CHAR(CEIL(TO_DATE(AM.THISDATETIME, 'YYYY-MM-DD') - TO_DATE(AM.LASTDATETIME, 'YYYY-MM-DD')) + 1) END)) / COUNT(ZD.ID) AS DL" +
	          		" FROM ZHANDIAN ZD, DIANBIAO DB, DIANDUVIEW AM, DIANFEIVIEW EF " +
	          		"WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK" +
	          		" AND DB.DBYT = 'dbyt01' "+whereStr+str1+" AND EF.MANUALAUDITSTATUS > '0' GROUP BY ZD.ID) ZDDL" +
	          		" ON ZD.ID = ZDDL.ID LEFT JOIN CBZD CB ON ZD.ID=CB.ZDID "+str2+"WHERE ZD.QSDBDL IS NOT NULL " +
	          		"AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+whereStr+str;				
		      db.connectDb();
		     // System.out.println("sql2:"+sql2.toString());
		     // if("zdsx02".equals(str3)||str3 == "zdsx02"){
		    	  System.out.println("超标站点查询sql2："+sql2.toString());
		    	  rs = db.queryAll(sql2.toString());	
		     // }else{
		    	//  System.out.println("超标站点查询："+sql);
		    //	  rs = db.queryAll(sql.toString());		    	  
		    //  }
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
					zb.setQsdbdl(rs.getString("QSDBDL") != null ? rs.getString("QSDBDL") : "");
					zb.setRjdl(rs.getString("DL") != null ? rs.getString("DL") : "");
					zb.setBl(rs.getString("BL") != null ? rs.getString("BL") : "");
					zb.setBz(rs.getString("BZ") != null ? rs.getString("BZ") : "0");
					zb.setDlcb(rs.getString("DLCB") != null ? rs.getString("DLCB") : "0");
					zb.setDfcb(rs.getString("DFCB") != null ? rs.getString("DFCB") : "0");
					zb.setBzz(rs.getString("BZZ")!=null?rs.getString("BZZ"):"0");
					list.add(zb);
		      }
		    }catch (DbException de) {
		      de.printStackTrace();
		    } catch (Exception de) {
		      de.printStackTrace();
		    }finally {
		      if (rs != null) {
		        try {
		          rs.close();
		        }
		        catch (SQLException se) {
		          se.printStackTrace();
		        }
		      }
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }

		    }
			 
		    return list;
		  }
}
