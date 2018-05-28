package com.noki.mobi.cx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.noki.ammeterdegree.javabean.newAmmeterDegreeFormBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.CTime;
import com.noki.util.Query;

public class SiteBeanchmark {
	private String zdcode;
	private String zdname;
	private String szdq;
	private String zdsx;
	private String zdlx;
	private String yflx;
	private String gdfs;
	private String hdl;
	private String fkzq;
	private String df;
	private String ratio;
	private String beilv;
	private String xslx;
	private String xsz;
	private String jztype;
	private String jlfh;
	private String zlfh;
	private String dytype;
	private String g2;
	private String g3;
	private String kdsb;
	private String yysb;
	private String zpsl;
	private String zssl;
	private String kdsbsl;
	private String yysbsl;
	private String kt1;
	private String kt2;
	private String zgd;
	private String gsf;
	private String dbid;
	
	//站点对标分析---类型编码、采集站点数、采集站点月耗电量、结算站点数、结算月耗电量
	private String code;
	private String numcj;
	private String sumcj1;
	private String numjs;
	private String sumjs1;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNumcj() {
		return numcj;
	}
	public void setNumcj(String numcj) {
		this.numcj = numcj;
	}
	public String getSumcj1() {
		return sumcj1;
	}
	public void setSumcj1(String sumcj1) {
		this.sumcj1 = sumcj1;
	}
	public String getNumjs() {
		return numjs;
	}
	public void setNumjs(String numjs) {
		this.numjs = numjs;
	}
	public String getSumjs1() {
		return sumjs1;
	}
	public void setSumjs1(String sumjs1) {
		this.sumjs1 = sumjs1;
	}
	
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}



	private String dbname;
	public String getJztype() {
		return jztype;
	}
	public void setJztype(String jztype) {
		this.jztype = jztype;
	}
	public String getJlfh() {
		return jlfh;
	}
	public void setJlfh(String jlfh) {
		this.jlfh = jlfh;
	}
	public String getZlfh() {
		return zlfh;
	}
	public void setZlfh(String zlfh) {
		this.zlfh = zlfh;
	}
	public String getDytype() {
		return dytype;
	}
	public void setDytype(String dytype) {
		this.dytype = dytype;
	}
	public String getG2() {
		return g2;
	}
	public void setG2(String g2) {
		this.g2 = g2;
	}
	public String getG3() {
		return g3;
	}
	public void setG3(String g3) {
		this.g3 = g3;
	}
	public String getKdsb() {
		return kdsb;
	}
	public void setKdsb(String kdsb) {
		this.kdsb = kdsb;
	}
	public String getYysb() {
		return yysb;
	}
	public void setYysb(String yysb) {
		this.yysb = yysb;
	}
	public String getZpsl() {
		return zpsl;
	}
	public void setZpsl(String zpsl) {
		this.zpsl = zpsl;
	}
	public String getZssl() {
		return zssl;
	}
	public void setZssl(String zssl) {
		this.zssl = zssl;
	}
	public String getKdsbsl() {
		return kdsbsl;
	}
	public void setKdsbsl(String kdsbsl) {
		this.kdsbsl = kdsbsl;
	}
	public String getYysbsl() {
		return yysbsl;
	}
	public void setYysbsl(String yysbsl) {
		this.yysbsl = yysbsl;
	}
	public String getKt1() {
		return kt1;
	}
	public void setKt1(String kt1) {
		this.kt1 = kt1;
	}
	public String getKt2() {
		return kt2;
	}
	public void setKt2(String kt2) {
		this.kt2 = kt2;
	}
	public String getZgd() {
		return zgd;
	}
	public void setZgd(String zgd) {
		this.zgd = zgd;
	}
	public void setGsf(String gsf) {
		this.gsf = gsf;
	}
	public String getGsf() {
		return gsf;
	}
	
	
	
	private String lasttime;
	private String thistime;
	private String lastdegree;
	private String thisdegree;
	private String startmonth;
	private String endmonth;
	private String floatdegree;
	private String blhdl;
	private String floatpay;
	private String actualpay;
	private String accountmonth;
	private String pue;
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public String getThistime() {
		return thistime;
	}
	public void setThistime(String thistime) {
		this.thistime = thistime;
	}
	public String getLastdegree() {
		return lastdegree;
	}
	public void setLastdegree(String lastdegree) {
		this.lastdegree = lastdegree;
	}
	public String getThisdegree() {
		return thisdegree;
	}
	public void setThisdegree(String thisdegree) {
		this.thisdegree = thisdegree;
	}
	public String getStartmonth() {
		return startmonth;
	}
	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}
	public String getEndmonth() {
		return endmonth;
	}
	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}
	public String getFloatdegree() {
		return floatdegree;
	}
	public void setFloatdegree(String floatdegree) {
		this.floatdegree = floatdegree;
	}
	public String getBlhdl() {
		return blhdl;
	}
	public void setBlhdl(String blhdl) {
		this.blhdl = blhdl;
	}
	public String getFloatpay() {
		return floatpay;
	}
	public void setFloatpay(String floatpay) {
		this.floatpay = floatpay;
	}
	public String getActualpay() {
		return actualpay;
	}
	public void setActualpay(String actualpay) {
		this.actualpay = actualpay;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	
	public String getZdcode() {
		return zdcode;
	}
	public void setZdcode(String zdcode) {
		this.zdcode = zdcode;
	}
	public String getZdname() {
		return zdname;
	}
	public void setZdname(String zdname) {
		this.zdname = zdname;
	}
	public String getSzdq() {
		return szdq;
	}
	public void setSzdq(String szdq) {
		this.szdq = szdq;
	}
	public String getZdsx() {
		return zdsx;
	}
	public void setZdsx(String zdsx) {
		this.zdsx = zdsx;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public String getYflx() {
		return yflx;
	}
	public void setYflx(String yflx) {
		this.yflx = yflx;
	}
	public String getGdfs() {
		return gdfs;
	}
	public void setGdfs(String gdfs) {
		this.gdfs = gdfs;
	}
	public String getHdl() {
		return hdl;
	}
	public void setHdl(String hdl) {
		this.hdl = hdl;
	}
	public String getDf() {
		return df;
	}
	public void setDf(String df) {
		this.df = df;
	}
	public void setFkzq(String fkzq) {
		this.fkzq = fkzq;
	}
	public String getFkzq() {
		return fkzq;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public String getRatio() {
		return ratio;
	}
	public void setPue(String pue) {
		this.pue = pue;
	}
	public String getPue() {
		return pue;
	}
	public String getBeilv() {
		return beilv;
	}
	public void setBeilv(String beilv) {
		this.beilv = beilv;
	}
	public String getXslx() {
		return xslx;
	}
	public void setXslx(String xslx) {
		this.xslx = xslx;
	}
	public String getXsz() {
		return xsz;
	}
	public void setXsz(String xsz) {
		this.xsz = xsz;
	}
	
	Connection conn = null;
	Statement sta = null;
	//采集点站点对标
   public ArrayList getlist(int start,String whereStr,String loginId,String dbyt){
	   ArrayList list=new ArrayList();
	   String sql="";
	   if(dbyt.equals("dbyt01")){
		    sql="select zz.zdcode,zz.jzname," +
	   		"(case when zz.shi is not null then(select agname from d_area_grade where agcode = zz.shi)else ''end) " +
	   		"|| (case when zz.xian is not null then(select distinct agname from d_area_grade where agcode = zz.xian) else '' end)" +
	   		" || (case when zz.xiaoqu is not null then(select distinct agname from d_area_grade where agcode = zz.xiaoqu)else''end) as szdq," +
	   		"(select t.name from indexs t where t.code = zz.property) as property," +
	   		"(select t.name from indexs t where t.code = zz.jztype) as jztype," +
	   		"(select t.name from indexs t where t.code = zz.yflx) as yflx," +
	   		"(select t.name from indexs t where t.code = zz.gdfs) as gdfs,aa.hdl,aa.df" +
	   		" from zhandian zz, " +
	   		"(select z.zdcode,sum(a.actualdegree) as hdl,sum(e.actualpay)as df " +
	   		"from zhandian z, dianbiao d, ammeterdegrees a, electricfees e " +
	   		"where z.id = d.zdid and d.dbid = a.ammeterid_fk and dbyt='"+dbyt+"' and a.ammeterdegreeid = e.ammeterdegreeid_fk  "+whereStr+" " +
	   		" group by z.zdcode)aa where aa.zdcode=zz.zdcode";
	   }else{
		   sql="select zz.zdcode,zz.jzname," +
	   		"(case when zz.shi is not null then(select agname from d_area_grade where agcode = zz.shi)else ''end) " +
	   		"|| (case when zz.xian is not null then(select distinct agname from d_area_grade where agcode = zz.xian) else '' end)" +
	   		" || (case when zz.xiaoqu is not null then(select distinct agname from d_area_grade where agcode = zz.xiaoqu)else''end) as szdq," +
	   		"(select t.name from indexs t where t.code = zz.property) as property," +
	   		"(select t.name from indexs t where t.code = zz.jztype) as jztype," +
	   		"(select t.name from indexs t where t.code = zz.yflx) as yflx," +
	   		"(select t.name from indexs t where t.code = zz.gdfs) as gdfs,aa.hdl,aa.df" +
	   		" from zhandian zz, " +
	   		"(select z.zdcode,sum(a.actualdegree) as hdl,''as df " +
	   		"from zhandian z, dianbiao d, ammeterdegrees a " +
	   		"where z.id = d.zdid and d.dbid = a.ammeterid_fk and dbyt='"+dbyt+"'  "+whereStr+" " +
	   		" group by z.zdcode)aa where aa.zdcode=zz.zdcode";
	   }
	  
	   
	   System.out.println("--"+sql.toString());
	   DataBase db=new DataBase();
	   ResultSet rs=null;
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   rs = sta.executeQuery(sql);
			Query query=new Query();
		list=query.query(rs);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		db.free(rs,sta,conn);
	}	   
	   return list;
   }
   //==============抄表统计率查询==============
   public ArrayList getlistchaobiao(String whereStr,String zdd,String bett, String loginId){
	   ArrayList list=new ArrayList();
		String sql2=" SELECT AA.name,AA.XIAN ID,DBID, AAA.DLID,aaaa.yxcs "+
  " FROM (SELECT DISTINCT Z.XIAN, (select distinct agname from d_area_grade where agcode = z.xian) as name,COUNT(D.DBID) AS DBID "+
         " FROM ZHANDIAN Z, DIANBIAO D "+
        " WHERE Z.ID = D.ZDID "+
        " and z.qyzt='1' "+
        " and D.dbqyzt='1' "+
          " AND D.DBYT = 'dbyt03'"+
          whereStr+zdd+
         " GROUP BY Z.XIAN) AA, "+
      "(SELECT DISTINCT Z.XIAN, COUNT(AA.AMMETERDEGREEID) AS DLID "+
         " FROM ZHANDIAN Z, DIANBIAO D, ammeterdegrees AA "+
         " WHERE Z.ID = D.ZDID "+
         " AND D.DBQYZT='1' "+
         " and z.qyzt='1' "+
          " AND AA.AMMETERID_FK(+) = D.DBID "+
          " AND D.DBYT = 'dbyt03' "+
          whereStr+
          bett+
        zdd+
          "  AND Aa.lastdatetime IS NOT NULL "+ 
          " AND Aa.thisdatetime IS NOT NULL "+
          "    and z.SHSIGN='1' "+
        "  GROUP BY Z.XIAN) AAA,"+
        "(select z.xian,sum(zz.aaa)as yxcs from (select cb.ammeterid_fk,count(*)as aaa from (SELECT a.ammeterid_fk,nvl(ceil(A.Thisdatetime-A.LASTDATETIME), 0) AS SSS "+
          " FROM (SELECT distinct aa.ammeterid_fk,Aa.Thisdatetime, Aa.LASTDATETIME "+
                 " FROM ammeterdegrees aa, DIANBIAO D "+
                  " WHERE D.DBID = aa.AMMETERID_FK "+
                  " AND D.DBQYZT='1' "+
                  " AND D.DBYT = 'dbyt03' "+
                  bett+
                  " and length(to_char(aa.Thisdatetime,'yyyy-mm-dd')) = 10 "+
                  " and length(to_char(aa.LASTDATETIME,'yyyy-mm-dd')) = 10) A WHERE rownum>0 "+
        " ) cb "+
    " where cb.sss > '15' "+
   " group  by cb.ammeterid_fk)zz,zhandian z,dianbiao d where z.id=d.zdid and d.dbqyzt='1' and z.qyzt='1'" +zdd+
   " and d.dbid=zz.ammeterid_fk(+) and d.dbyt='dbyt03'    and z.SHSIGN='1' group  by z.xian)aaaa "+
" WHERE AA.XIAN = AAA.XIAN(+) and aaa.xian=aaaa.xian(+)";
		//=============
		
	   System.out.println("--"+sql2.toString());
	   DataBase db=new DataBase();
	   ResultSet rs=null;
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   rs = sta.executeQuery(sql2);
		Query query=new Query();
		list=query.query(rs);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		db.free(rs,sta,conn);
	}
	   return list;
   }
   //=========================================
   //点击系统zaiyong
   public ArrayList getlistchaobiao2(String kkt,String loginId,String idd,String zdd){
	   ArrayList list=new ArrayList();
		String sql4="SELECT DISTINCT Z.XIAN, "+
		"(select NAME from indexs where CODE = z.STATIONTYPE and type='stationtype') as stationtype,"+
		"(select t.name from indexs t where t.code = z.property and TYPE='ZDSX') as property, "+
		"(select t.name from indexs t where t.code = z.jztype and type='ZDLX') as jztype, "+
		"(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end) "+
				        "||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdqq, "+
		               " d.beilv, "+
		               " d.dbid, "+
		               " d.dbname, "+
		               " d.csds, "+
		               "  z.jzname, "+
		               " to_char(d.cssytime,'yyyy-mm-dd') cssytime "+
		 " FROM ZHANDIAN Z, DIANBIAO D,DIANDUVIEW A "+ 
		 " WHERE Z.ID = D.ZDID "+
		 " AND Z.QYZT='1' "+
		 " AND D.DBQYZT='1' "+
		 " AND A.AMMETERID_FK(+) = D.DBID "+
		 " AND D.DBYT = 'dbyt03' "+
		 kkt+
		idd+
		zdd+
		 " GROUP BY Z.XIAN, "+
		 "z.xiaoqu, "+
		 "d.beilv, "+
		 "z.property, "+
		 "z.jztype, "+
		 "z.stationtype, "+
		 " d.dbid, "+
		 " d.dbname, "+
		 " d.csds, "+
		 " z.jzname, "+
		 " d.cssytime "+
		   " ORDER BY to_char(D.CSSYTIME,'yyyy-mm-dd') DESC";		
	   System.out.println("在用电表数--"+sql4.toString());
		ResultSet rs=null;
	   DataBase db=new DataBase();
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   rs = sta.executeQuery(sql4);
		Query query=new Query();
		list=query.query(rs);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		db.free(rs,sta,conn);
	}
	   return list;
   }
   //=======抄表率统计===========
   public ArrayList getlistchaobiao4(String kkt,String zdd,String loginId,String ist){
	   ArrayList list=new ArrayList();
		ResultSet rs=null;
			String sql5="SELECT DISTINCT Z.XIAN, d.dbid, d.dbname, d.csds, to_char(d.cssytime,'yyyy-mm-dd') cssytime, z.jzname, "+
			"(select NAME from indexs where CODE = z.STATIONTYPE and type='stationtype') as stationtype,"+
			"(select t.name from indexs t where t.code = z.property and TYPE='ZDSX') as property, "+
			"(select t.name from indexs t where t.code = z.jztype and type='ZDLX') as jztype, "+
			"(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end) "+
		        "||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdqq, "+
               " d.beilv "+
			  " FROM ZHANDIAN Z, DIANBIAO D "+
			  " WHERE Z.ID = D.ZDID "+ 
			  " AND D.DBQYZT='1' "+
			  " AND Z.QYZT='1'"+
			  " AND D.DBYT = 'dbyt03' "+
			 kkt+ist+zdd+
			  " GROUP BY Z.XIAN, "+
			  "z.xiaoqu, "+
			  "d.beilv, "+
			  "z.property, "+
			  "z.jztype, "+
			  "z.stationtype, "+
			         " d.dbid, "+
			          "d.dbname, "+
			          " d.csds, "+
			          " d.cssytime, "+
			          " z.jzname "+
			           " ORDER BY TO_CHAR(D.CSSYTIME,'yyyy-mm-dd') DESC";
	   System.out.println("系统在用电表总数合计："+sql5.toString());
	   DataBase db=new DataBase();
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   rs = sta.executeQuery(sql5);
		Query query=new Query();
		list=query.query(rs);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		db.free(rs,sta,conn);
	}
	   return list;
   }
   //===========================
   public ArrayList getlistchaobiao1(String kkt,String zdd,String bett, String loginId,String ist){
	   ArrayList list=new ArrayList();
	   ResultSet rs=null;
	   String sql3=" SELECT DISTINCT z.xian, "+
                       " z.jzname, "+ 
                       " d.dbid, "+
                       " a.lastdegree, "+
                       " a.thisdegree , "+
                       " to_char(a.lastdatetime,'yyyy-mm-dd') lastdatetime, "+
                       " to_char(a.thisdatetime,'yyyy-mm-dd') thisdatetime, "+
                       " a.blhdl, "+
                       " to_char(a.startmonth,'yyyy-mm') startmonth, "+
                       " to_char(a.endmonth,'yyyy-mm') endmonth, "+
                      "  a.ammeterdegreeid "+
       " FROM ZHANDIAN Z, DIANBIAO D, ammeterdegrees A "+
                    " WHERE Z.ID = D.ZDID "+
                    " AND D.DBQYZT='1' "+
                     " AND A.AMMETERID_FK(+) = D.DBID "+
                     "    and z.SHSIGN='1' "+
                     "    and z.qyzt='1' "+
                    "  AND D.DBYT = 'dbyt03' "+
                    zdd+
                    kkt+
                    bett+
                    ist+
                     " and a.ammeterdegreeid is not null "+
                     " AND A.lastdatetime IS NOT NULL "+
   " AND A.thisdatetime IS NOT NULL "+
         " GROUP BY Z.XIAN, "+
                "a.blhdl,z.jzname, d.dbid,a.lastdegree,a.thisdegree ,"+
                " a.lastdatetime,a.thisdatetime,a.ammeterdegreeid,a.startmonth,a.endmonth "+
             " ORDER BY TO_CHAR(a.thisdatetime,'yyyy-mm-dd') desc";
	   DataBase db=new DataBase();
	   try {
		System.out.println("抄表总数总数："+sql3);
		db.connectDb();	 
		conn = db.getConnection();
		sta = conn.createStatement();
		rs = sta.executeQuery(sql3);
		Query query=new Query();
		list=query.query(rs);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		db.free(rs,sta,conn);
	}
	   return list;
   }
   //=================
   public ArrayList getlistchaobiao7(String kkt,String zdd,String bett, String loginId,String ist){
	   ArrayList list=new ArrayList();
	   String sql3=" select distinct TO_CHAR(AA.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(AA.ENDMONTH,'yyyy-mm') ENDMONTH, z.xian,z.jzname,AA.BLHDL,d.dbid,AA.lastdegree,TO_CHAR(AA.lastdatetime,'yyyy-mm-dd') lastdatetime,AA.thisdegree,TO_CHAR(AA.thisdatetime,'yyyy-mm-dd') thisdatetime "+
          " from (select cb.ammeterid_fk, count(*) as aaa "+
                 " from (SELECT a.ammeterid_fk, "+
                               " nvl(ceil(A.Thisdatetime - "+
                                       " A.LASTDATETIME), "+
                                  "  0) AS SSS "+
                         "  FROM (SELECT distinct aa.ammeterid_fk, "+
                                      " Aa.Thisdatetime, "+
                                      "  Aa.LASTDATETIME "+
                                 "  FROM ammeterdegrees aa, DIANBIAO D "+
                                 " WHERE D.DBID = aa.AMMETERID_FK "+
                                  "  AND D.DBYT = 'dbyt03' "+
                                  " AND D.DBQYZT='1' "+
                                  bett+
                                  "  and length(TO_CHAR(aa.Thisdatetime,'yyyy-mm-dd')) = 10 "+
                                  " and length(TO_CHAR(aa.LASTDATETIME,'yyyy-mm-dd')) = 10) A where rownum>0 "+
                        " ) cb "+
                 " where cb.sss > '15' "+
                
                " group by cb.ammeterid_fk) zz, "+
               " zhandian z, "+
               " dianbiao d, "+
               " ammeterdegrees AA "+ 
        "  where z.id = d.zdid "+
        "    and z.SHSIGN='1' "+
        "    and z.qyzt='1' "+
        "  AND AA.ammeterid_fk(+)=D.DBID "+
        " AND D.DBQYZT='1' "+
         "  and d.dbid = zz.ammeterid_fk(+) "+
          " and d.dbyt = 'dbyt03' "+
         kkt+bett+zdd+ist+
         " AND AA.LASTDATETIME IS NOT NULL "+
         " AND AA.THISDATETIME IS NOT NULL "+
         "  and zz.aaa is not null "+
        " group by AA.STARTMONTH, AA.ENDMONTH,AA.BLHDL,z.xian,z.jzname,d.dbid,AA.lastdegree,AA.lastdatetime,AA.thisdegree,AA.thisdatetime ORDER BY TO_CHAR(AA.THISDATETIME,'yyyy-mm-dd') DESC";
	   System.out.println("有效抄表次数："+sql3);
		ResultSet rs=null;
	   DataBase db=new DataBase();
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   rs = sta.executeQuery(sql3);

		rs=db.queryAll(sql3);
		Query query=new Query();
		list=query.query(rs);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		db.free(rs,sta,conn);
	}
	   return list;
   }
   //=======抄表率统计===========
   public SiteBeanchmark getlist(String zdname){
	  
	   String sql="select zz.zdcode,zz.jzname," +
  		"(case when zz.shi is not null then(select agname from d_area_grade where agcode = zz.shi)else ''end) " +
  		"|| (case when zz.xian is not null then(select distinct agname from d_area_grade where agcode = zz.xian) else '' end)" +
  		" || (case when zz.xiaoqu is not null then(select distinct agname from d_area_grade where agcode = zz.xiaoqu)else''end) as szdq," +
  		"(select t.name from indexs t where t.code = zz.property) as property," +
  		"(select t.name from indexs t where t.code = zz.jztype) as jztype," +
  		"(select t.name from indexs t where t.code = zz.yflx) as yflx," +
  		"(select t.name from indexs t where t.code = zz.gdfs) as gdfs" +
  		" from zhandian zz where zz.jzname='"+zdname+"'";
	   System.out.println("抄表率统计:"+sql.toString());
		ResultSet rs=null;
	   DataBase db=new DataBase();
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   rs = sta.executeQuery(sql);	
		if(rs.next()){
			this.setZdcode(rs.getString(1)!=null?rs.getString(1):"");
			this.setZdname(rs.getString(2)!=null?rs.getString(2):"");
			this.setSzdq(rs.getString(3)!=null?rs.getString(3):"");
			this.setZdsx(rs.getString(4)!=null?rs.getString(4):"");
			this.setZdlx(rs.getString(5)!=null?rs.getString(5):"");
			this.setYflx(rs.getString(6)!=null?rs.getString(6):"");
			this.setGdfs(rs.getString(7)!=null?rs.getString(7):"");
		}else{
			this.setZdcode("");
			this.setZdname("");
			this.setSzdq("");
			this.setZdsx("");
			this.setZdlx("");
			this.setYflx("");
			this.setGdfs("");
		}		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		db.free(rs,sta,conn);
	}
	   return this;
   }
   public SiteBeanchmark getInformation(String str){
		  
	   String sql="select z.zdcode,sum(a.actualdegree) as hdl,sum(e.actualpay)as df " +
  		"from zhandian z, dianbiao d, ammeterdegrees a, electricfees e " +
  		"where z.id = d.zdid and z.qyzt='1' and  d.dbid = a.ammeterid_fk and a.ammeterdegreeid = e.ammeterdegreeid_fk  "+str+"" +
  		" group by z.zdcode";	   
	   System.out.println("--"+sql.toString());
		ResultSet rs=null;
	   DataBase db=new DataBase();
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   rs = sta.executeQuery(sql);		
		if(rs.next()){
			this.setZdcode(rs.getString(1)!=null?rs.getString(1):"");
			this.setHdl(rs.getString(2)!=null?rs.getString(2):"");
			this.setDf(rs.getString(3)!=null?rs.getString(3):"");
		}else{
			this.setZdcode("");
			this.setHdl("");
			this.setDf("");
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		db.free(rs,sta,conn);
	}
	   return this;
   }
   //采集站点对标
   public SiteBeanchmark getInfor(String str){
		  
	   String sql="select sum(a.actualdegree) as hdl " +
  		"from zhandian z, dianbiao d, ammeterdegrees a " +
  		"where z.id = d.zdid and d.dbid = a.ammeterid_fk and d.dbyt='dbyt02'  "+str+"";
	   		
	   
	   System.out.println("采集站点对标:"+sql.toString());
	   ResultSet rs=null;
	   DataBase db=new DataBase();
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   rs = sta.executeQuery(sql);		
		if(rs.next()){			
			this.setHdl(rs.getString(1)!=null?rs.getString(1):"");
		}else{
			this.setHdl("");
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		db.free(rs,sta,conn);
	}
	   return this;
   }
   public String getcount(String str){
	   String count="";
	   ResultSet rs=null;
	   String sql="select count(*) from(select z.zdcode,sum(a.actualdegree) as hdl " +
 		"from zhandian z, dianbiao d, ammeterdegrees a " +
 		"where z.id = d.zdid and d.dbid = a.ammeterid_fk and d.dbyt='dbyt02'  "+str+" group by z.zdcode)";
	   System.out.println("--"+sql.toString());
	   DataBase db=new DataBase();
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   rs = sta.executeQuery(sql);	
		if(rs.next()){
	          count=rs.getString(1);
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		db.free(rs,sta,conn);
	}
	   return count;
   }
   //站点对标分析
   public List<SiteBeanchmark> getsite1(String str,String loginId,Double bili,String zdlx,Double hdl,String str1){
	   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
	   ResultSet rs=null;
	   DataBase db=new DataBase();
	   try {
	   String fzzdstr="";
	   fzzdstr = getFuzeZdid(db,loginId);
	   String sql="SELECT  count(Z.ZDCODE),SUM(A.BLHDL / (NVL(CEIL(A.THISDATETIME - A.LASTDATETIME), 0) + 1) * 30) FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A " +
	   		" WHERE Z.ID = D.ZDID   AND D.DBID = A.AMMETERID_FK   AND Z.CAIJI = '0' AND Z.BGSIGN = '0'  "+str+" "+str1+" AND D.DBYT='dbyt01'  " +
	   		"AND z.stationtype='"+zdlx+"' AND A.MANUALAUDITSTATUS = '1'   AND " +
	   				"(CASE  WHEN LENGTH(TO_CHAR(A.THISDATETIME,'yyyy-mm-dd')) = 10 AND LENGTH(TO_CHAR(A.LASTDATETIME,'yyyy-mm-dd')) = 10 " +
	   				"AND CEIL(A.THISDATETIME - A.LASTDATETIME)>=0 " +
	   				"THEN  A.BLHDL / (NVL(CEIL(A.THISDATETIME - A.LASTDATETIME), 0) + 1) * 30 / '"+hdl+"'  END  )> '"+bili+"' and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")";

	   System.out.println("站点对标分析:"+sql.toString());
	   db.connectDb();	 
	   conn = db.getConnection();
	   sta = conn.createStatement();
	   rs = sta.executeQuery(sql);
	   while(rs.next()){
		   SiteBeanchmark bean =new SiteBeanchmark();
		  
		   bean.setZdcode(rs.getString(1));
		   bean.setHdl(rs.getString(2));
		   list.add(bean);
	   }
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs,sta,conn);
		}
	   return list;
   }
   //站点对标分析地市标杆
   public List<SiteBeanchmark> getsite1(String str,String loginId,String zdlx,String str1){
	   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
	   ResultSet rs=null;
	   DataBase db=new DataBase();
	  try {
	   String fzzdstr="";
	   fzzdstr = getFuzeZdid(db,loginId);
	   String sql="SELECT  count(Z.ZDCODE),SUM( A.BLHDL),(SELECT I.NAME FROM INDEXS I WHERE I.CODE = Z.STATIONTYPE  AND I.TYPE = 'stationtype') FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A " +
	   		" WHERE Z.ID = D.ZDID   AND D.DBID = A.AMMETERID_FK   AND Z.CAIJI = '0' AND Z.BGSIGN = '1'  "+str+" "+str1+" AND D.DBYT='dbyt01'  " +
	   		"AND z.stationtype='"+zdlx+"' AND A.MANUALAUDITSTATUS = '1' and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") GROUP BY Z.STATIONTYPE";

	   System.out.println("--"+sql.toString());
		  db.connectDb();	 
		  conn = db.getConnection();
		  sta = conn.createStatement();
	   rs = sta.executeQuery(sql);
	   while(rs.next()){
		   SiteBeanchmark bean =new SiteBeanchmark();		  
		   bean.setZdcode(rs.getString(1));
		   bean.setHdl(rs.getString(2));
		   bean.setZdlx(rs.getString(3));
		   list.add(bean);
	   }
	   } catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	   } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   } finally {
			db.free(rs,sta,conn);
		}
	   return list;
   }
   //站点对标分析采集点
   public List<SiteBeanchmark> getsite2(String str,String loginId,String zdlx,String str2){
	   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
	   ResultSet rs=null;
	   DataBase db=new DataBase();
	   try {
		   String fzzdstr="";
		   fzzdstr = getFuzeZdid(db,loginId);
		  // String sql="SELECT Z.STATIONTYPE, COUNT(Z.ZDCODE), SUM(A.BLHDL), I.NAME " +
		   	//	"FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES A, INDEXS I WHERE Z.ID = D.ZDID " +
		   		//"AND D.DBID = A.AMMETERID_FK AND Z.CAIJI = '1'  AND I.CODE = Z.STATIONTYPE "+str+" AND Z.STATIONTYPE = '"+zdlx+"'" +
		   		//" and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") GROUP BY Z.STATIONTYPE, I.NAME";
		   String sql="SELECT AA.STATIONTYPE, AA.NAME, COUNT(AA.ZDCODE), SUM(AA.HDL) " +
		   		"FROM (SELECT Z.STATIONTYPE,Z.ZDCODE,(SUM(A.BLHDL) / COUNT(A.AMMETERDEGREEID)) AS HDL,I.NAME  FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES A, INDEXS I " +
		   		"WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND Z.CAIJI = '1'  and d.ydsb='ybsb05' AND I.CODE = Z.STATIONTYPE  "+str+" "+str2+" AND Z.STATIONTYPE = '"+zdlx+"' AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))  GROUP BY Z.STATIONTYPE, I.NAME, Z.ZDCODE) AA GROUP BY AA.STATIONTYPE, AA.NAME";
		   System.out.println("站点对标分析采集点"+sql.toString());
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   rs = sta.executeQuery(sql);	
		   while(rs.next()){
			   SiteBeanchmark bean   =new SiteBeanchmark();
			   bean.setZdlx(rs.getString(1));
			   bean.setZdsx(rs.getString(2));
			   bean.setZdcode(rs.getString(3));
			   bean.setHdl(rs.getString(4));		   
			   list.add(bean);
		   }
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs,sta,conn);
		}
	   return list;
   }
   //站点对标分析采集点详细
   public List<SiteBeanchmark> getsite2(String loginId,String zdlx,String start,String end,String str2){
	   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
	   ResultSet rs=null;
	   DataBase db=new DataBase();
	   try {
		   String fzzdstr="";
		   fzzdstr = getFuzeZdid(db,loginId);
		  // String sql="SELECT Z.STATIONTYPE, COUNT(Z.ZDCODE), SUM(A.BLHDL), I.NAME " +
		   	//	"FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES A, INDEXS I WHERE Z.ID = D.ZDID " +
		   		//"AND D.DBID = A.AMMETERID_FK AND Z.CAIJI = '1'  AND I.CODE = Z.STATIONTYPE "+str+" AND Z.STATIONTYPE = '"+zdlx+"'" +
		   		//" and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") GROUP BY Z.STATIONTYPE, I.NAME";
		   String sql=" SELECT Z.STATIONTYPE,Z.ZDCODE,Z.JZNAME,SUM(A.BLHDL) , COUNT(A.AMMETERDEGREEID) AS HDL,I.NAME," +
		   "(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)" +
	  		"||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)" +
	  		"||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq, TO_CHAR(MIN(a.lastdatetime),'yyyy-mm-dd'), TO_CHAR(MAX(a.thisdatetime),'yyyy-mm-dd'),( sum(A.BLHDL) / (Z.ZLFH * 54 + Z.JLFH * 220)) "+
		   		"  FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES A, INDEXS I " +
		   		"WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND Z.CAIJI = '1' and d.ydsb='ybsb05' and to_char(a.lastdatetime,'yyyy-mm-dd')>='"+start+"-01' and  to_char(a.thisdatetime,'yyyy-mm-dd')<='"+end+"-31'    AND I.CODE = Z.STATIONTYPE   "+str2+" AND Z.STATIONTYPE = '"+zdlx+"' AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))  GROUP BY Z.STATIONTYPE, I.NAME, Z.ZDCODE,z.shi,z.xian,z.xiaoqu,z.jzname,z.zlfh,z.jlfh order by z.shi,z.jzname";
		   System.out.println("站点对标分析采集点详细:"+sql.toString());
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   rs = sta.executeQuery(sql);
		   while(rs.next()){
			   SiteBeanchmark bean   =new SiteBeanchmark();
			   bean.setZdcode(rs.getString(2));
			   bean.setZdname(rs.getString(3));
			   bean.setSzdq(rs.getString(6));
			   bean.setHdl(rs.getString(4));
			   bean.setAccountmonth(rs.getString(5));
			   bean.setSzdq(rs.getString(7));
			   bean.setLasttime(rs.getString(8));
			   bean.setThistime(rs.getString(9));
			   bean.setPue(rs.getString(10));			   
			   list.add(bean);
		  }
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs,sta,conn);
		}
	   return list;
   }
 //站点对标分析采集站点电量详细信息
   public List<SiteBeanchmark> getsite5(String zdcode,String startmonth,String endmonth){
	   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
	   ResultSet rs=null;	   
	   DataBase db=new DataBase();
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   String sql="SELECT  DB.DBID, DB.DBNAME,  ZD.JZNAME, AM.LASTDEGREE, AM.THISDEGREE,  TO_CHAR(AM.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(AM.THISDATETIME,'yyyy-mm-dd') THISDATETIME,  AM.BLHDL, (SELECT NAME FROM INDEXS   WHERE CODE = DB.YDSB AND TYPE = 'YDSB') AS YDSB " +
	   		"FROM AMMETERDEGREES AM, ZHANDIAN ZD, DIANBIAO DB WHERE ZD.CAIJI = '1' AND ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK  AND DB.YDSB = 'ybsb05'  " +
	   		"  AND ZD.ZDCODE='"+zdcode+"' AND TO_CHAR(AM.LASTDATETIME,'yyyy-mm-dd')>='"+startmonth+"-01"+"' AND TO_CHAR(AM.THISDATETIME,'yyyy-mm-dd')<='"+endmonth+"-31"+"' ORDER BY AM.THISDATETIME DESC ";

	   System.out.println("站点对标分析采集站点电量详细信息:"+sql.toString());
	   rs = sta.executeQuery(sql);
	   while(rs.next()){
		   SiteBeanchmark bean   =new SiteBeanchmark();
	       bean.setZdname(rs.getString(3));
	       bean.setDbid(rs.getString(1));
	       bean.setDbname(rs.getString(2));
	       bean.setLastdegree(rs.getString(4));
	       bean.setThisdegree(rs.getString(5));
	       bean.setLasttime(rs.getString(6));
	       bean.setThistime(rs.getString(7));
	       bean.setBlhdl(rs.getString(8));
		   list.add(bean);
	   }
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs,sta,conn);
		}
	   return list;
   }
 //站点对标分析超标站点详细
   public List<SiteBeanchmark> getsite3(String loginId,String zdlx,String hdl,Double bili,String startmonth,String endmonth,String pue,String wherestr,String site){
	   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
	   DataBase db=new DataBase();
	   String fzzdstr="";
	   fzzdstr = getFuzeZdid(db,loginId);
	   String sql="";
	   ResultSet rs=null;	
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
			if(site.equals("0")){
			    sql="SELECT Z.JZNAME,Z.ZDCODE, A.BLHDL, A.STARTMONTH, A.ENDMONTH,(a.blhdl/(NVL(CEIL(TO_DATE(A.THISDATETIME, 'yyyy-mm-dd') -TO_DATE(A.LASTDATETIME, 'yyyy-mm-dd')), 0) + 1) * 30/"+hdl+")AS bili,z.dianfei,(SELECT i.name  FROM indexs i WHERE i.code=zd.fkzq AND i.type='FKZQ')," +
		   		" (SELECT i.name  FROM indexs i WHERE i.code=z.jztype AND i.type='ZDLX'), " +
		   		"(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)" +
		   		"||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)" +
		   		"||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq,A.BLHDL / (Z.ZLFH * 54 + Z.JLFH * 220),D.BEILV,(SELECT I.NAME  FROM INDEXS I WHERE I.CODE = D.LINELOSSTYPE AND I.TYPE = 'xslx'), D.LINELOSSVALUE  " +
		   		"FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A,zddfinfo zd WHERE Z.ID = D.ZDID  AND D.DBID = A.AMMETERID_FK " +
		   		" AND z.id=zd.id(+)  AND Z.CAIJI = '0' AND Z.BGSIGN  = '"+site+"' AND A.MANUALAUDITSTATUS = '1'  AND D.DBYT = 'dbyt01' AND A.STARTMONTH>= '" +startmonth+"' AND A.ENDMONTH<='"+endmonth+"'"+
		   		" AND z.stationtype='"+zdlx+"' "+wherestr+pue+" AND (CASE  WHEN LENGTH(A.THISDATETIME) = 10 AND LENGTH(A.LASTDATETIME) = 10 " +
		   				"AND CEIL(TO_DATE(A.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(A.LASTDATETIME, 'yyyy-mm-dd'))>=0 " +
		   				"THEN  A.BLHDL / (NVL(CEIL(TO_DATE(A.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(A.LASTDATETIME, 'yyyy-mm-dd')), 0) + 1) * 30 / '"+hdl+"'  END  )> '"+bili+"' and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ORDER BY bili DESC";
		   }else if(site.equals("1")){
			    sql="SELECT Z.JZNAME,Z.ZDCODE, A.BLHDL, A.STARTMONTH, A.ENDMONTH,'',z.dianfei,(SELECT i.name  FROM indexs i WHERE i.code=zd.fkzq AND i.type='FKZQ')," +
		   		" (SELECT i.name  FROM indexs i WHERE i.code=z.jztype AND i.type='ZDLX'), " +
		   		"(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)" +
		   		"||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)" +
		   		"||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq,A.BLHDL / (Z.ZLFH * 54 + Z.JLFH * 220),D.BEILV,(SELECT I.NAME  FROM INDEXS I WHERE I.CODE = D.LINELOSSTYPE AND I.TYPE = 'xslx'), D.LINELOSSVALUE  " +
		   		"FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A,zddfinfo zd WHERE Z.ID = D.ZDID  AND D.DBID = A.AMMETERID_FK " +
		   		" AND z.id=zd.id(+)  AND Z.CAIJI = '0'  AND Z.BGSIGN  = '"+site+"' AND D.DBYT = 'dbyt01' AND A.MANUALAUDITSTATUS = '1'  AND A.STARTMONTH>= '" +startmonth+"' AND A.ENDMONTH<='"+endmonth+"'"+
		   		" AND z.stationtype='"+zdlx+"' "+wherestr+pue+" and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")  ORDER BY Z.JZNAME DESC";
		   }
		   System.out.println("站点对标分析超标站点详细:"+sql.toString());
		   rs = sta.executeQuery(sql);
		   while(rs.next()){
			   SiteBeanchmark bean   =new SiteBeanchmark();
			   bean.setZdname(rs.getString(1));
			   bean.setZdcode(rs.getString(2));
			   bean.setRatio(rs.getString(6));
			   bean.setDf(rs.getString(7));
			   bean.setFkzq(rs.getString(8));
			   bean.setZdlx(rs.getString(9));
			   bean.setSzdq(rs.getString(10));
			   bean.setPue(rs.getString(11));
			   bean.setBeilv(rs.getString(12));
			   bean.setXslx(rs.getString(13));
			   bean.setXsz(rs.getString(14));
			   list.add(bean);
		   }
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs,sta,conn);
		}
	   return list;
   }
   //站点对标分析超标站点电费明细详细
   public List<SiteBeanchmark> getsite4(String zdcode,String startmonth,String endmonth){
	   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
	   ResultSet rs=null;
	   DataBase db=new DataBase();
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
			String sql="SELECT Z.JZNAME,A.LASTDATETIME,A.THISDATETIME,A.LASTDEGREE,A.THISDEGREE,A.STARTMONTH,A.ENDMONTH,A.FLOATDEGREE,A.BLHDL,E.FLOATPAY,E.ACTUALPAY,E.ACCOUNTMONTH " +
		   		  "  FROM ZHANDIAN Z,DIANBIAO D,DIANDUVIEW A,DIANFEIVIEW E," +
		   		  "(SELECT AA.UUID FROM AMMETERDEGREES AA, ELECTRICFEES EE WHERE AA.AMMETERDEGREEID = EE.AMMETERDEGREEID_FK AND AA.STARTMONTH >= '"+startmonth+"' AND AA.ENDMONTH <= '"+endmonth+"') AAA " +
		   		  "WHERE Z.ID = D.ZDID  AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK  AND Z.ZDCODE = '"+zdcode+"' AND AAA.UUID = A.UUID";
		   System.out.println("站点对标分析超标站点电费明细详细:"+sql.toString());
		   rs = sta.executeQuery(sql);
		   while(rs.next()){
			   SiteBeanchmark bean   =new SiteBeanchmark();
			   bean.setZdname(rs.getString(1));
			   bean.setLasttime(rs.getString(2));
			   bean.setThistime(rs.getString(3));
			   bean.setLastdegree(rs.getString(4));
			   bean.setThisdegree(rs.getString(5));
			   bean.setStartmonth(rs.getString(6));
			   bean.setEndmonth(rs.getString(7));
			   bean.setFloatdegree(rs.getString(8));
			   bean.setBlhdl(rs.getString(9));
			   bean.setFloatpay(rs.getString(10));
			   bean.setActualpay(rs.getString(11));
			   bean.setAccountmonth(rs.getString(12));
			  
			   list.add(bean);
		   }
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs,sta,conn);
		}
	   return list;
   }
 //站点对标分析超标站点明细详细
   public List<SiteBeanchmark> getsite4(String zdcode){
	   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
	   ResultSet rs=null;
	   DataBase db=new DataBase();
	   try {
		   db.connectDb();	 
		   conn = db.getConnection();
		   sta = conn.createStatement();
		   String sql="SELECT JZ.JZNAME,JZ.ZDCODE, JZ.ZLFH,jz.jlfh,JZ.BGSIGN, JZ.EDHDL,(SELECT T.NAME FROM INDEXS T  WHERE T.CODE = JZ.PROPERTY AND TYPE = 'ZDSX') AS PROPERTY,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = JZ.JZTYPE  AND TYPE = 'ZDLX') AS JZTYPE,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = JZ.YFLX) AS YFLX,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = JZ.GDFS) AS GDFS, JZ.FZR, JZ.AREA,JZ.DIANFEI,(SELECT NAME FROM INDEXS  WHERE CODE = JZ.STATIONTYPE AND TYPE = 'stationtype') AS STATIONTYPE, (SELECT NAME FROM INDEXS WHERE CODE = JZ.GSF  AND TYPE = 'gsf') AS GSF,(CASE WHEN JZ.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = JZ.XIAN) ELSE'' END) || (CASE WHEN JZ.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = JZ.XIAOQU) ELSE  '' END) AS SZDQ,(SELECT  i.name FROM  indexs i WHERE i.code=jz.dytype AND i.type='dytype'),jz.g2,jz.g3,jz.kdsb,jz.yysb,jz.zpsl,jz.zssl,jz.kdsbsl,jz.yysbsl,jz.kt1,jz.kt2,jz.zgd FROM ZHANDIAN JZ where jz.zdcode='"+zdcode+"'";
		   System.out.println("站点对标分析超标站点明细详细:"+sql.toString());
		   rs = sta.executeQuery(sql);
		   while(rs.next()){
			  SiteBeanchmark bean   =new SiteBeanchmark();
			  bean.setZdname(rs.getString(1));
			  bean.setZlfh(rs.getString(3));
			  bean.setJlfh(rs.getString(4));
			  bean.setZdsx(rs.getString(7));
			  bean.setJztype(rs.getString(8));
			  bean.setYflx(rs.getString(9));
			  bean.setGdfs(rs.getString(10));
			  bean.setDf(rs.getString(13));
			  bean.setZdlx(rs.getString(14));
			  bean.setGsf(rs.getString(15));
			  bean.setSzdq(rs.getString(16));
			  bean.setDytype(rs.getString(17));
			  bean.setG2(rs.getString(18));
			  bean.setG3(rs.getString(19));
			  bean.setKdsb(rs.getString(20));
			  bean.setYysb(rs.getString(21));
			  bean.setZpsl(rs.getString(22));
			  bean.setZssl(rs.getString(23));
			  bean.setKdsbsl(rs.getString(24));
			  bean.setYysbsl(rs.getString(25));
			  bean.setKt1(rs.getString(26));
			  bean.setKt2(rs.getString(27));
			  bean.setZgd(rs.getString(28));
			   list.add(bean);
		   }
	} catch (DbException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		db.free(rs,sta,conn);
	}
	   return list;
   }
 //负责站点条件
	private String getFuzeZdid(DataBase db, String loginid){
		ResultSet rs = null;
		String cxtj = new String("");
		try {
			db.connectDb();	 
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery("select begincode,endcode from per_zhandian where accountid='"
					+ loginid
					+ "' and begincode is not null and endcode is not null");
			while (rs.next()) {			
					cxtj=cxtj+" or (zdcode>='" + rs.getString(1)
					+ "' and zdcode <='" + rs.getString(2) + "')";			
			}	
			System.out.println("负责站点条件："+cxtj);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs,sta,conn);
		}
		return cxtj.toString();
	}

  //站点对标分析基站查询
	   public List<SiteBeanchmark> getsitecx(String str,String loginId,String str1,String str2,int time,long quot,Double bili){
          String fzzdstr="";          
          DecimalFormat mat=new DecimalFormat("0.0");
		   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
		   SiteBeanchmark bean1 =new SiteBeanchmark();	
		   DataBase db=new DataBase(); 
		   ResultSet rs=null;
		   try {	   
			   DataBase db1=new DataBase();	
				fzzdstr = getFuzeZdid(db1,loginId);
				  db.connectDb();	 
				   conn = db.getConnection();
				   sta = conn.createStatement();
				String sql ="select LX.code,LX.name,SUMCJ.numgcj, SUMCJ.sumcj1,sumcj.xlx " +
					"from (select code, name from indexs where type = 'XLX') LX left join "+
					"(SELECT COUNT(distinct Z.ZDCODE) numgcj,(SUM(A.BLHDL) / COUNT(A.AMMETERDEGREEID)) AS sumcj1,Z.XLX FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES A WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND Z.CAIJI = '1' and z.qyzt='1' and d.ydsb='ybsb05' "+str+" "+str2+" AND Z.STATIONTYPE = 'StationType02' AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) GROUP BY Z.XLX) sumcj on LX.code=sumcj.xlx order by LX.CODE";		   				
				System.out.println("站点对标分析基站采集查询:"+sql.toString());
				rs = sta.executeQuery(sql);
			    while(rs.next()){
				   SiteBeanchmark bean   =new SiteBeanchmark();
				   bean.setCode(rs.getString(1));
				   bean.setZdlx(rs.getString(2));
				   bean.setNumcj(rs.getString(3));				   
				   bean1 = this.getsitecxjs(str, loginId,bili,rs.getString(1),rs.getString(3),rs.getString(4),str1,time,quot);
				   bean.setSumcj1(bean1.getSumcj1());
				   bean.setNumjs(bean1.getNumjs());
				   bean.setSumjs1(bean1.getSumjs1());
				   list.add(bean);
			    }
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.free(rs,sta,null);
			}
		   return list;
	   }
	   //站点对标分析基站结算站点信息查询
	   public SiteBeanchmark getsitecxjs(String str,String loginId,Double bili,String zdlx1,String numgcj1,String hdl2,String str1,int time,long quot){
          Double averagehdl=0.0;
          String fzzdstr="";
          DecimalFormat mat=new DecimalFormat("0.0");
          SiteBeanchmark bean   =new SiteBeanchmark();
		  if(numgcj1==null||"null".equals(numgcj1)||"o".equals(numgcj1)||"".equals(numgcj1))numgcj1="0";
		  if(hdl2==null||"null".equals(hdl2)||"o".equals(hdl2)||"".equals(hdl2))hdl2="0";
		  //averagehdl=Double.parseDouble(hdl2)/Double.parseDouble(numgcj1)*quot/time;
		  averagehdl=Double.parseDouble(hdl2)*quot/time;
		  String dl=mat.format(averagehdl);
		  if(hdl2=="0"){
			  averagehdl=1.0;
		  }
		   DataBase db=new DataBase();
		   ResultSet rs=null;
		   try {
			fzzdstr = getFuzeZdid(db,loginId);
			  String sql ="SELECT  count(distinct Z.ZDCODE) numgjs,(SUM(A.BLHDL / (NVL(CEIL(A.THISDATETIME - A.LASTDATETIME), 0) + 1) * 30)/count(Z.ZDCODE)) sumjs1,z.xlx FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK  AND Z.CAIJI = '0' and z.qyzt='1' AND Z.BGSIGN = '0' "+str+" "+str1+" AND D.DBYT='dbyt01' "+
 				"AND z.stationtype='StationType02' AND Z.XLX='"+zdlx1+"'AND A.MANUALAUDITSTATUS = '1' "+
 				" and ( case when LENGTH(TO_CHAR(A.THISDATETIME,'yyyy-mm-dd')) = 10 AND LENGTH(TO_CHAR(A.LASTDATETIME,'yyyy-mm-dd')) = 10 and CEIL(A.THISDATETIME - A.LASTDATETIME) >=0 then a.blhdl/ (NVL(CEIL(A.THISDATETIME - A.LASTDATETIME), 0) + 1) * 30/"+averagehdl+" end) >"+bili+
 				" and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") GROUP BY Z.XLX"; 
 				//"AND LENGTH(A.THISDATETIME) = 10 AND LENGTH(A.LASTDATETIME) = 10 AND a.blhdl/ (NVL(CEIL(TO_DATE(A.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(A.LASTDATETIME, 'yyyy-mm-dd')), 0) + 1) * 30/"+averagehdl+">"+bili+"  and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") GROUP BY Z.XLX";
			System.out.println("站点对标分析基站结算站点信息查询:"+sql.toString());
			 db.connectDb();	 
			   conn = db.getConnection();
			   sta = conn.createStatement();
			rs = sta.executeQuery(sql);
			bean.setSumcj1(dl);
			while(rs.next()){
			   bean.setNumjs(rs.getString(1));
			   bean.setSumjs1(rs.getString(2));
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs,sta,conn);
		}
		   return bean;
	   }
	   
	   
	   //站点对标分析接入网查询
	   public List<SiteBeanchmark> getsitecx1(String str,String loginId,String str1,String str2,int time,long quot,Double bili){
          String fzzdstr="";          
          DecimalFormat mat=new DecimalFormat("0.0");
		   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
		   SiteBeanchmark bean1 =new SiteBeanchmark();	
		   DataBase db=new DataBase();		   
		   ResultSet rs=null;
		   try {
				fzzdstr = getFuzeZdid(db,loginId);
				   String sql ="select LX.code,LX.name,SUMCJ.numgcj, SUMCJ.sumcj1,sumcj.JRWTYPE " +
					"from (select code, name from indexs where type = 'jrwlx') LX left join "+
					"(SELECT COUNT(distinct Z.ZDCODE) numgcj,(SUM(A.BLHDL) / COUNT(A.AMMETERDEGREEID)) AS sumcj1,Z.JRWTYPE FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES A WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK AND Z.CAIJI = '1' and z.qyzt='1' and d.ydsb='ybsb05' "+str+" "+str2+" AND Z.STATIONTYPE = 'StationType03' AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) GROUP BY Z.JRWTYPE) sumcj on LX.code=sumcj.JRWTYPE order by LX.CODE";		   				
				System.out.println("站点对标分析接入网采集查询:"+sql.toString());
				db.connectDb();	 
				   conn = db.getConnection();
				   sta = conn.createStatement();
				rs = sta.executeQuery(sql);
			    while(rs.next()){
				   SiteBeanchmark bean   =new SiteBeanchmark();	
				   bean.setCode(rs.getString(1));
				   bean.setZdlx(rs.getString(2));
				   bean.setNumcj(rs.getString(3));				   
				   bean1 = this.getsitecxjs1(str, loginId,bili,rs.getString(1),rs.getString(3),rs.getString(4),str1,time,quot);
				   bean.setSumcj1(bean1.getSumcj1());
				   bean.setNumjs(bean1.getNumjs());
				   bean.setSumjs1(bean1.getSumjs1());
				   list.add(bean);
			    }
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.free(rs,sta,conn);
			}
		   return list;
	   }
	   //站点对标分析接入网结算站点信息查询
	   public SiteBeanchmark getsitecxjs1(String str,String loginId,Double bili,String zdlx1,String numgcj1,String hdl2,String str1,int time,long quot){
          Double averagehdl=0.0;
          String fzzdstr="";
          DecimalFormat mat=new DecimalFormat("0.0");
          SiteBeanchmark bean   =new SiteBeanchmark();
		  if(numgcj1==null||"null".equals(numgcj1)||"o".equals(numgcj1)||"".equals(numgcj1))numgcj1="0";
		  if(hdl2==null||"null".equals(hdl2)||"o".equals(hdl2)||"".equals(hdl2))hdl2="0";
		  //averagehdl=Double.parseDouble(hdl2)/Double.parseDouble(numgcj1)*quot/time;
		  averagehdl=Double.parseDouble(hdl2)*quot/time;
		  String dl=mat.format(averagehdl);
		  if(hdl2=="0"){
			  averagehdl=1.0;
		  }
		   ResultSet rs=null;
		   DataBase db=new DataBase();
		   try {
			fzzdstr = getFuzeZdid(db,loginId);
			  String sql ="SELECT  count(distinct Z.ZDCODE) numgjs,(SUM(A.BLHDL / (NVL(CEIL(A.THISDATETIME - A.LASTDATETIME), 0) + 1) * 30)/count(Z.ZDCODE)) sumjs1,z.JRWTYPE FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK  AND Z.CAIJI = '0' and z.qyzt='1' AND Z.BGSIGN = '0' "+str+" "+str1+" AND D.DBYT='dbyt01' "+
 				"AND z.stationtype='StationType03' AND Z.JRWTYPE='"+zdlx1+"'AND A.MANUALAUDITSTATUS = '1'"+
 				" and ( case when LENGTH(TO_CHAR(A.THISDATETIME,'yyyy-mm-dd')) = 10 AND LENGTH(TO_CHAR(A.LASTDATETIME,'yyyy-mm-dd')) = 10 and CEIL(A.THISDATETIME - A.LASTDATETIME) >=0 then a.blhdl/ (NVL(CEIL(A.THISDATETIME - A.LASTDATETIME), 0) + 1) * 30/"+averagehdl+" end) >"+bili+
 				" and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") GROUP BY Z.JRWTYPE";
 				//"AND LENGTH(A.THISDATETIME) = 10 AND LENGTH(A.LASTDATETIME) = 10 AND a.blhdl/ (NVL(CEIL(TO_DATE(A.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(A.LASTDATETIME, 'yyyy-mm-dd')), 0) + 1) * 30/"+averagehdl+">"+bili+"  and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") GROUP BY Z.JRWTYPE";
			System.out.println("站点对标分析接入网结算站点信息查询:"+sql.toString());
			 db.connectDb();	 
			   conn = db.getConnection();
			   sta = conn.createStatement();
			rs = sta.executeQuery(sql);
			bean.setSumcj1(dl);
			while(rs.next()){
			   bean.setNumjs(rs.getString(1));
			   bean.setSumjs1(rs.getString(2));
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs,sta,conn);
		}
		   return bean;
	   }
	   
	   //站点对标分析超标结算站点详细
	   public List<SiteBeanchmark> getsitec3(String loginId,String zdlx,String hdl,Double bili,String startmonth,String endmonth,String wherestr,String site,String code){
		  String str="";
		   ResultSet rs=null;
		  if(zdlx.equals("StationType02")){
			  str=" and Z.XLX='"+code+"'";
		  }else if(zdlx.equals("StationType03")){
			  str=" and Z.JRWTYPE='"+code+"'";
		  }
		  if(hdl=="0"||"0".equals(hdl)||hdl=="0.0"||"0.0".equals(hdl)){
			  hdl="1.0";
		  }
		   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
		   DataBase db=new DataBase();
		try {
			db.connectDb();	 
			conn = db.getConnection();
			sta = conn.createStatement();
			   String sql="";
			   if(site.equals("0")){
				    sql="SELECT Z.JZNAME,Z.ZDCODE, A.BLHDL, TO_CHAR(A.STARTMONTH,'yyyy-mm') STARTMONTH, TO_CHAR(A.ENDMONTH,'yyyy-mm') ENDMONTH,(a.blhdl/(NVL(CEIL(A.THISDATETIME -A.LASTDATETIME), 0) + 1) * 30/"+hdl+")AS bili,z.dianfei,(SELECT i.name  FROM indexs i WHERE i.code=zd.fkzq AND i.type='FKZQ')," +
			   		" (SELECT i.name  FROM indexs i WHERE i.code=z.jztype AND i.type='ZDLX'), " +
			   		"(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)" +
			   		"||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)" +
			   		"||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq,A.BLHDL / (Z.ZLFH * 54 + Z.JLFH * 220),D.BEILV,(SELECT I.NAME  FROM INDEXS I WHERE I.CODE = D.LINELOSSTYPE AND I.TYPE = 'xslx'), D.LINELOSSVALUE  " +
			   		"FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A,zddfinfo zd WHERE Z.ID = D.ZDID AND Z.QYZT='1' AND D.DBID = A.AMMETERID_FK " +
			   		//" AND z.id=zd.id(+)  AND Z.CAIJI = '0' AND Z.BGSIGN  = '"+site+"' AND A.MANUALAUDITSTATUS = '1' AND LENGTH(A.THISDATETIME) = 10 AND LENGTH(A.LASTDATETIME) = 10  AND D.DBYT = 'dbyt01' AND A.STARTMONTH>= '" +startmonth+"' AND A.ENDMONTH<='"+endmonth+"'"+
			   		//" AND z.stationtype='"+zdlx+"' "+wherestr+str+" AND a.blhdl/ (NVL(CEIL(TO_DATE(A.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(A.LASTDATETIME, 'yyyy-mm-dd')),0) + 1) * 30 /"+hdl+">"+bili+" ORDER BY bili DESC";
			   		" AND z.id=zd.id(+)  AND Z.CAIJI = '0' AND Z.BGSIGN  = '"+site+"' AND z.stationtype='"+zdlx+"' "+wherestr+str+" AND D.DBYT = 'dbyt01' AND TO_CHAR(A.STARTMONTH,'yyyy-mm')>= '" +startmonth+"' AND TO_CHAR(A.ENDMONTH,'yyyy-mm')<='"+endmonth+"'"+
			   		" and ( case when LENGTH(TO_CHAR(A.THISDATETIME,'yyyy-mm-dd')) = 10 AND LENGTH(TO_CHAR(A.LASTDATETIME,'yyyy-mm-dd')) = 10 and CEIL(A.THISDATETIME - A.LASTDATETIME) >=0 then a.blhdl/ (NVL(CEIL(A.THISDATETIME - A.LASTDATETIME), 0) + 1) * 30/"+hdl+" end) >"+bili+" ORDER BY bili DESC";
			   }else if(site.equals("1")){
				    sql="SELECT Z.JZNAME,Z.ZDCODE, A.BLHDL, TO_CHAR(A.STARTMONTH,'yyyy-mm') STARTMONTH, TO_CHAR(A.ENDMONTH,'yyyy-mm') ENDMONTH,'',z.dianfei,(SELECT i.name  FROM indexs i WHERE i.code=zd.fkzq AND i.type='FKZQ')," +
			   		" (SELECT i.name  FROM indexs i WHERE i.code=z.jztype AND i.type='ZDLX'), " +
			   		"(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)" +
			   		"||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)" +
			   		"||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq,A.BLHDL / (Z.ZLFH * 54 + Z.JLFH * 220),D.BEILV,(SELECT I.NAME  FROM INDEXS I WHERE I.CODE = D.LINELOSSTYPE AND I.TYPE = 'xslx'), D.LINELOSSVALUE  " +
			   		"FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A,zddfinfo zd WHERE Z.ID = D.ZDID AND Z.QYZT='1' AND D.DBID = A.AMMETERID_FK " +
			   		" AND z.id=zd.id(+)  AND Z.CAIJI = '0'  AND Z.BGSIGN  = '"+site+"' AND D.DBYT = 'dbyt01' AND TO_CHAR(A.STARTMONTH,'yyyy-mm')>= '" +startmonth+"' AND TO_CHAR(A.ENDMONTH,'yyyy-mm')<='"+endmonth+"'"+
			   		" AND z.stationtype='"+zdlx+"' "+wherestr+str+"  ORDER BY Z.JZNAME DESC";
			   }
			   System.out.println("站点对标分析超标结算站点详细："+sql.toString());
			   rs = sta.executeQuery(sql);
			   while(rs.next()){
				   SiteBeanchmark bean   =new SiteBeanchmark();
				   bean.setZdname(rs.getString(1));
				   bean.setZdcode(rs.getString(2));
				   bean.setRatio(rs.getString(6));
				   bean.setDf(rs.getString(7));
				   bean.setFkzq(rs.getString(8));
				   bean.setZdlx(rs.getString(9));
				   bean.setSzdq(rs.getString(10));
				   bean.setPue(rs.getString(11));
				   bean.setBeilv(rs.getString(12));
				   bean.setXslx(rs.getString(13));
				   bean.setXsz(rs.getString(14));
				   list.add(bean);
			   }
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs,sta,conn);
		}
		   return list;
	   }
	   //站点对标分析采集站点详细
	   public List<SiteBeanchmark> getsitec2(String loginId,String zdlx,String start,String end,String str2,String code){
		   String str="";
		   String fzzdstr="";
		   if(zdlx.equals("StationType02")){
			  str=" and Z.XLX='"+code+"' ";
		   }else if(zdlx.equals("StationType03")){
			  str=" and Z.JRWTYPE='"+code+"' ";
		   }
		   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
		   DataBase db=new DataBase();
		   ResultSet rs=null;
		   try {
			   fzzdstr = getFuzeZdid(db,loginId);
			   String sql=" SELECT Z.STATIONTYPE,Z.ZDCODE,Z.JZNAME,SUM(A.BLHDL) , COUNT(A.AMMETERDEGREEID) AS HDL,I.NAME," +
			   "(case when z.shi is not null then (select agname from d_area_grade where agcode=z.shi) else '' end)" +
		  		"||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end)" +
		  		"||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as szdq, TO_CHAR(MIN(a.lastdatetime),'yyyy-mm-dd'), TO_CHAR(MAX(a.thisdatetime),'yyyy-mm-dd'),( sum(A.BLHDL) / (Z.ZLFH * 54 + Z.JLFH * 220)) "+
			   		"  FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES A, INDEXS I " +
			   		"WHERE Z.ID = D.ZDID AND Z.QYZT='1' AND D.DBID = A.AMMETERID_FK AND Z.CAIJI = '1' and d.ydsb='ybsb05' and to_char(a.lastdatetime,'yyyy-mm-dd')>='"+start+"-01' and  to_char(a.thisdatetime,'yyyy-mm-dd')<='"+end+"-31' AND I.CODE = Z.STATIONTYPE "+str+str2+" AND Z.STATIONTYPE = '"+zdlx+"' AND ((Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))  GROUP BY Z.STATIONTYPE, I.NAME, Z.ZDCODE,z.shi,z.xian,z.xiaoqu,z.jzname,z.zlfh,z.jlfh order by z.shi,z.jzname";

			   System.out.println("站点对标分析采集站点详细："+sql.toString());
			   db.connectDb();	 
			   conn = db.getConnection();
			   sta = conn.createStatement();
			   rs = sta.executeQuery(sql);
			   while(rs.next()){
				   SiteBeanchmark bean   =new SiteBeanchmark();
				   bean.setZdcode(rs.getString(2));
				   bean.setZdname(rs.getString(3));
				   bean.setSzdq(rs.getString(6));
				   bean.setHdl(rs.getString(4));
				   bean.setAccountmonth(rs.getString(5));
				   bean.setSzdq(rs.getString(7));
				   bean.setLasttime(rs.getString(8));
				   bean.setThistime(rs.getString(9));
				   bean.setPue(rs.getString(10));
				   
				   list.add(bean);
			   }
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs,sta,conn);
		}
		   return list;
	   }
	 //站点对标分析采集站点电量详细信息
	   public List<SiteBeanchmark> getsitec5(String zdcode,String startmonth,String endmonth){
		   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
		   String sql="SELECT  DB.DBID, DB.DBNAME,  ZD.JZNAME, AM.LASTDEGREE, AM.THISDEGREE,  TO_CHAR(AM.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(AM.THISDATETIME,'yyyy-mm-dd') THISDATETIME,  AM.BLHDL, (SELECT NAME FROM INDEXS   WHERE CODE = DB.YDSB AND TYPE = 'YDSB') AS YDSB " +
	   		"FROM AMMETERDEGREES AM, ZHANDIAN ZD, DIANBIAO DB WHERE ZD.CAIJI = '1' AND ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK and zd.qyzt='1' AND DB.YDSB = 'ybsb05'  " +
	   		"  AND ZD.ZDCODE='"+zdcode+"' AND TO_CHAR(AM.LASTDATETIME,'yyyy-mm-dd')>='"+startmonth+"-01"+"' AND TO_CHAR(AM.THISDATETIME,'yyyy-mm-dd')<='"+endmonth+"-31"+"' ORDER BY AM.THISDATETIME DESC ";
		   ResultSet rs=null;
		   System.out.println("站点对标分析采集站点电量详细信息："+sql.toString());
		   DataBase db=new DataBase();
		   try {
			   db.connectDb();	 
			   conn = db.getConnection();
			   sta = conn.createStatement();
			   rs = sta.executeQuery(sql);
			   while(rs.next()){
				   SiteBeanchmark bean   =new SiteBeanchmark();
			       bean.setZdname(rs.getString(3));
			       bean.setDbid(rs.getString(1));
			       bean.setDbname(rs.getString(2));
			       bean.setLastdegree(rs.getString(4));
			       bean.setThisdegree(rs.getString(5));
			       bean.setLasttime(rs.getString(6));
			       bean.setThistime(rs.getString(7));
			       bean.setBlhdl(rs.getString(8));				  
				   list.add(bean);
			   }
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs,sta,conn);
		}
		   return list;
	   }
	   //站点对标分析超标结算站点电费明细详细
	   public List<SiteBeanchmark> getsitec4(String zdcode,String startmonth,String endmonth){
		   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
		   DataBase db=new DataBase();
		   String sql="SELECT Z.JZNAME,TO_CHAR(A.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(A.THISDATETIME,'yyyy-mm-dd') THISDATETIME,A.LASTDEGREE,A.THISDEGREE,TO_CHAR(A.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(A.ENDMONTH,'yyyy-mm') ENDMONTH,A.FLOATDEGREE,A.BLHDL,E.FLOATPAY,E.ACTUALPAY,TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH " +
	   		  "  FROM ZHANDIAN Z,DIANBIAO D,DIANDUVIEW A,DIANFEIVIEW E," +
	   		  "(SELECT AA.UUID FROM AMMETERDEGREES AA, ELECTRICFEES EE WHERE AA.AMMETERDEGREEID = EE.AMMETERDEGREEID_FK AND TO_CHAR(AA.STARTMONTH,'yyyy-mm') >= '"+startmonth+"' AND TO_CHAR(AA.ENDMONTH,'yyyy-mm') <= '"+endmonth+"') AAA " +
	   		  "WHERE Z.ID = D.ZDID  AND D.DBID = A.AMMETERID_FK  and z.qyzt='1' AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK  AND Z.ZDCODE = '"+zdcode+"' AND AAA.UUID = A.UUID";
		   ResultSet rs=null;
		   System.out.println("站点对标分析超标结算站点电费明细详细："+sql.toString());
		   try {
			   db.connectDb();	 
			   conn = db.getConnection();
			   sta = conn.createStatement();
			   rs = sta.executeQuery(sql);
			   while(rs.next()){
				   SiteBeanchmark bean   =new SiteBeanchmark();
				   bean.setZdname(rs.getString(1));
				   bean.setLasttime(rs.getString(2));
				   bean.setThistime(rs.getString(3));
				   bean.setLastdegree(rs.getString(4));
				   bean.setThisdegree(rs.getString(5));
				   bean.setStartmonth(rs.getString(6));
				   bean.setEndmonth(rs.getString(7));
				   bean.setFloatdegree(rs.getString(8));
				   bean.setBlhdl(rs.getString(9));
				   bean.setFloatpay(rs.getString(10));
				   bean.setActualpay(rs.getString(11));
				   bean.setAccountmonth(rs.getString(12));
				  
				   list.add(bean);
			   }
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.free(rs,sta,conn);
			}
		   return list;
	   }
	 //站点对标分析超标结算站点明细详细
	   public List<SiteBeanchmark> getsitec4(String zdcode){
		   List<SiteBeanchmark> list=new ArrayList<SiteBeanchmark>();
		   DataBase db=new DataBase();
		   String sql="SELECT JZ.JZNAME,JZ.ZDCODE, JZ.ZLFH,jz.jlfh,JZ.BGSIGN, JZ.EDHDL,(SELECT T.NAME FROM INDEXS T  WHERE T.CODE = JZ.PROPERTY AND TYPE = 'ZDSX') AS PROPERTY,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = JZ.JZTYPE  AND TYPE = 'ZDLX') AS JZTYPE,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = JZ.YFLX) AS YFLX,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = JZ.GDFS) AS GDFS, JZ.FZR, JZ.AREA,JZ.DIANFEI,(SELECT NAME FROM INDEXS  WHERE CODE = JZ.STATIONTYPE AND TYPE = 'stationtype') AS STATIONTYPE, (SELECT NAME FROM INDEXS WHERE CODE = JZ.GSF  AND TYPE = 'gsf') AS GSF,(CASE WHEN JZ.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = JZ.XIAN) ELSE'' END) || (CASE WHEN JZ.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = JZ.XIAOQU) ELSE  '' END) AS SZDQ,(SELECT  i.name FROM  indexs i WHERE i.code=jz.dytype AND i.type='dytype'),jz.g2,jz.g3,jz.kdsb,jz.yysb,jz.zpsl,jz.zssl,jz.kdsbsl,jz.yysbsl,jz.kt1,jz.kt2,jz.zgd FROM ZHANDIAN JZ where  jz.qyzt='1' and  jz.zdcode='"+zdcode+"'";
		   ResultSet rs=null;
		   System.out.println("站点对标分析超标结算站点明细详细："+sql.toString());
		   try {
			   db.connectDb();	 
			   conn = db.getConnection();
			   sta = conn.createStatement();
			   rs = sta.executeQuery(sql);
			 while(rs.next()){
				   SiteBeanchmark bean   =new SiteBeanchmark();
				   bean.setZdname(rs.getString(1));
				  bean.setZlfh(rs.getString(3));
				  bean.setJlfh(rs.getString(4));
				  bean.setZdsx(rs.getString(7));
				  bean.setJztype(rs.getString(8));
				  bean.setYflx(rs.getString(9));
				  bean.setGdfs(rs.getString(10));
				  bean.setDf(rs.getString(13));
				  bean.setZdlx(rs.getString(14));
				  bean.setGsf(rs.getString(15));
				  bean.setSzdq(rs.getString(16));
				  bean.setDytype(rs.getString(17));
				  bean.setG2(rs.getString(18));
				  bean.setG3(rs.getString(19));
				  bean.setKdsb(rs.getString(20));
				  bean.setYysb(rs.getString(21));
				  bean.setZpsl(rs.getString(22));
				  bean.setZssl(rs.getString(23));
				  bean.setKdsbsl(rs.getString(24));
				  bean.setYysbsl(rs.getString(25));
				  bean.setKt1(rs.getString(26));
				  bean.setKt2(rs.getString(27));
				  bean.setZgd(rs.getString(28));
				   list.add(bean);
			  }
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			} finally {
				db.free(rs,sta,conn);
			}
		   return list;
	   }

}
