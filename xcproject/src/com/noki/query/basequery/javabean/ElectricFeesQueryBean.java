package com.noki.query.basequery.javabean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class ElectricFeesQueryBean {
	//电费缴纳明细查询和导出
	Connection conn = null;
	Statement sta = null;
	public synchronized List<ElectricFeesFormBean> getPageData(int start,String whereStr,String loginId,String bzw) {
		List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    ResultSet rs = null;
	    //调用负责站点条件函数	 
		try {
			fzzdstr = getFuzeZdid(db,loginId);
			if(bzw.equals("1")){
				  sql = "SELECT ZD.JZNAME,ZD.YID,ZD.EDHDL,D.DBZBDYHH,(SELECT ACT.NAME FROM ACCOUNT ACT WHERE ACT.ACCOUNTNAME=EF.CITYAUDITPENSONNEL AND ACT.DELSIGN=1) AS CITYAUDITPENSONNEL,"+
		        	"TO_CHAR(EF.CITYAUDITTIME,'yyyy-mm-dd hh24:mi:ss') CITYAUDITTIME,ZD.ID, (SELECT ACC.NAME FROM ACCOUNT ACC WHERE ACC.ACCOUNTNAME=EF.ENTRYPENSONNEL AND ACC.DELSIGN=1)AS ENTRYPENSONNEL,"+
		        	"TO_CHAR(EF.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') ENTRYTIME,EF.NOTETYPEID,D.DBID,EF.ELECTRICFEE_ID,"+
		        	"EF.UNITPRICE,EF.FLOATPAY,EF.ACTUALPAY,EF.LIUCHENGHAO,(SELECT AT.NAME FROM ACCOUNT AT WHERE AT.ACCOUNTNAME=EF.FINANCIALOPERATOR AND AT.DELSIGN = 1) AS FINANCIALOPERATOR,"+
		        	"D.DFZFLX,"+
		        	"ZD.BGSIGN,D.BIEMING,D.BEILV,"+
		        	"TO_CHAR(AD.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(AD.THISDATETIME,'yyyy-mm-dd') THISDATETIME,AD.FLOATDEGREE,AD.BLHDL,AD.MEMO,TO_CHAR(AD.INPUTDATETIME,'yyyy-mm-dd') INPUTDATETIME,TO_CHAR(EF.NOTETIME,'yyyy-mm-dd') NOTETIME,TO_CHAR(EF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,TO_CHAR(EF.KPTIME,'yyyy-mm-dd') KPTIME,EF.MEMO AS MM," +
			        "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE," +
			        "EF.NOTENO,(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.XIAN) as xian,"+
			        "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.XIAOQU)AS XQ, " +
			        "AD.AMMETERDEGREEID,ZD.ZLFH,AD.LASTDEGREE,AD.THISDEGREE,TO_CHAR(AD.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR( AD.ENDMONTH,'yyyy-mm') ENDMONTH,EF.PAYOPERATOR,TO_CHAR(EF.PAYDATETIME,'yyyy-mm-dd') PAYDATETIME,(CASE WHEN EF.AUTOAUDITSTATUS = '1' AND AD.AUTOAUDITSTATUS = '1' THEN '通过' WHEN EF.AUTOAUDITSTATUS = '0' OR AD.AUTOAUDITSTATUS = '0' THEN '不通过' END ) AUTOAUDITSTATUS,"+
			        "(CASE WHEN LENGTH(TO_CHAR(AD.LASTDATETIME,'yyyy-mm-dd')) = 10 AND LENGTH(TO_CHAR(AD.THISDATETIME,'yyyy-mm-dd')) = 10 AND ((CEIL(AD.THISDATETIME - AD.LASTDATETIME)) >= 0) THEN (CEIL(AD.THISDATETIME - AD.LASTDATETIME + 1)) END) JSZQ,"+
		            "EF.MANUALAUDITSTATUS,TO_CHAR(EF.MANUALAUDITDATETIME,'yyyy-mm-dd hh24:mi:ss') MANUALAUDITDATETIME,(SELECT AOT.NAME FROM ACCOUNT AOT WHERE AOT.ACCOUNTNAME=EF.MANUALAUDITNAME AND AOT.DELSIGN = 1)AS MANUALAUDITNAME,TO_CHAR(EF.FINANCIALDATETIME,'yyyy-mm-dd hh24:mi:ss') FINANCIALDATETIME,"+
		            "CASE  EF.CITYAUDIT WHEN '0' THEN '未通过' WHEN '1' THEN '通过' WHEN '-2' THEN  '不通过' END CITYAUDIT,ZD.GDFS,"+
		            "EF.ADMINISTRATIVEDF,EF.NETWORKDF,EF.MARKETDF,EF.BUILDDF,EF.INFORMATIZATIONDF,TO_CHAR(EF.KJYF,'yyyy-mm') KJYF,ZD.JLFH,AD.DDDF AS DDDFDL,EF.DDDF AS DDDFDF" +",D.DBNAME"+
		          
			        " FROM DIANBIAO D,ZHANDIAN ZD,DIANDUVIEW AD,DIANFEIVIEW EF " +
			        "WHERE ZD.ID=D.ZDID AND D.DBID=AD.AMMETERID_FK " +
			        "AND AD.AMMETERDEGREEID=EF.AMMETERDEGREEID_FK "+whereStr;
				
			}else{
				 sql = "SELECT ZD.JZNAME,ZD.YID,ZD.EDHDL,D.DBZBDYHH,(SELECT ACT.NAME FROM ACCOUNT ACT WHERE ACT.ACCOUNTNAME=EF.CITYAUDITPENSONNEL AND ACT.DELSIGN=1) AS CITYAUDITPENSONNEL,"+
		        	"TO_CHAR(EF.CITYAUDITTIME,'yyyy-mm-dd hh24:mi:ss') CITYAUDITTIME,ZD.ID, (SELECT ACC.NAME FROM ACCOUNT ACC WHERE ACC.ACCOUNTNAME=EF.ENTRYPENSONNEL AND ACC.DELSIGN=1)AS ENTRYPENSONNEL,"+
		        	"TO_CHAR(EF.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') ENTRYTIME,EF.NOTETYPEID,D.DBID,EF.ELECTRICFEE_ID,"+
		        	"EF.UNITPRICE,EF.FLOATPAY,EF.ACTUALPAY,EF.LIUCHENGHAO,(SELECT AT.NAME FROM ACCOUNT AT WHERE AT.ACCOUNTNAME=EF.FINANCIALOPERATOR AND AT.DELSIGN = 1) AS FINANCIALOPERATOR,"+
		        	"D.DFZFLX,"+
		        	"ZD.BGSIGN,D.BIEMING,D.BEILV,"+
		        	"TO_CHAR(AD.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(AD.THISDATETIME,'yyyy-mm-dd') THISDATETIME,AD.FLOATDEGREE,AD.BLHDL,AD.MEMO,TO_CHAR(AD.INPUTDATETIME,'yyyy-mm-dd') INPUTDATETIME,TO_CHAR(EF.NOTETIME,'yyyy-mm-dd') NOTETIME,TO_CHAR(EF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,TO_CHAR(EF.KPTIME,'yyyy-mm-dd') KPTIME,EF.MEMO AS MM," +
			        "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE," +
			        "EF.NOTENO,(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.XIAN) as xian,"+
			        "(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.XIAOQU)AS XQ, " +
			        "AD.AMMETERDEGREEID,ZD.ZLFH,AD.LASTDEGREE,AD.THISDEGREE,TO_CHAR(AD.STARTMONTH,'yyyy-mm') STARTMONTH, TO_CHAR(AD.ENDMONTH,'yyyy-mm') ENDMONTH,EF.PAYOPERATOR,TO_CHAR(EF.PAYDATETIME,'yyyy-mm-dd') PAYDATETIME,(CASE WHEN EF.AUTOAUDITSTATUS = '1' AND AD.AUTOAUDITSTATUS = '1' THEN '通过' WHEN EF.AUTOAUDITSTATUS = '0' OR AD.AUTOAUDITSTATUS = '0' THEN '不通过' END ) AUTOAUDITSTATUS,"+
			        "(CASE WHEN LENGTH(TO_CHAR(AD.LASTDATETIME,'yyyy-mm-dd')) = 10 AND LENGTH(TO_CHAR(AD.THISDATETIME,'yyyy-mm-dd')) = 10 AND ((CEIL(AD.THISDATETIME - AD.LASTDATETIME)) >= 0) THEN (CEIL(AD.THISDATETIME - AD.LASTDATETIME + 1)) END) JSZQ,"+
		            "EF.MANUALAUDITSTATUS,TO_CHAR(EF.MANUALAUDITDATETIME,'yyyy-mm-dd hh24:mi:ss') MANUALAUDITDATETIME,(SELECT AOT.NAME FROM ACCOUNT AOT WHERE AOT.ACCOUNTNAME=EF.MANUALAUDITNAME AND AOT.DELSIGN = 1)AS MANUALAUDITNAME,TO_CHAR(EF.FINANCIALDATETIME,'yyyy-mm-dd hh24:mi:ss') FINANCIALDATETIME,"+
		            "CASE  EF.CITYAUDIT WHEN '0' THEN '未通过' WHEN '1' THEN '通过' WHEN '-2' THEN  '不通过' END CITYAUDIT,ZD.GDFS,"+
		            "EF.ADMINISTRATIVEDF,EF.NETWORKDF,EF.MARKETDF,EF.BUILDDF,EF.INFORMATIZATIONDF,TO_CHAR(EF.KJYF,'yyyy-mm') KJYF,ZD.JLFH,AD.DDDF AS DDDFDL,EF.DDDF AS DDDFDF" +",D.DBNAME"+
		          
			        " FROM DIANBIAO D,ZHANDIAN ZD,DIANDUVIEW AD,DIANFEIVIEW EF " +
			        "WHERE ZD.ID=D.ZDID AND D.DBID=AD.AMMETERID_FK " +
			        "AND AD.AMMETERDEGREEID=EF.AMMETERDEGREEID_FK "+whereStr+
			        "AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+")";
			}
	      	  System.out.println("电费缴纳明细查询和导出:"+sql.toString());
	      	  db.connectDb();	 
	      	  conn = db.getConnection();
	      	  sta = conn.createStatement();
	      	  rs = sta.executeQuery(sql);
		      while(rs.next()){
				ElectricFeesFormBean formbean=new ElectricFeesFormBean();
				formbean.setJzname(rs.getString(1)!=null?rs.getString(1):"");  
				formbean.setYid(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setEdhdl(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setDbzbdyhh(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setCityauditpensonnel(rs.getString(5)!=null?rs.getString(5):"");
				formbean.setCityaudittime(rs.getString(6)!=null?rs.getString(6):"");
				formbean.setZdcode(rs.getString(7)!=null?rs.getString(7):"");
				formbean.setEntrypensonnel(rs.getString(8)!=null?rs.getString(8):"");
				formbean.setEntrytime(rs.getString(9)!=null?rs.getString(9):"");
				formbean.setNotetypeid(rs.getString(10)!=null?rs.getString(10):"");
				formbean.setDbid(rs.getString(11)!=null?rs.getString(11):"");
				formbean.setElectricfeeId(rs.getString(12)!=null?rs.getString(12):"");
				formbean.setUnitprice(rs.getString(13)!=null?rs.getString(13):"");
				formbean.setFloatpay(rs.getString(14)!=null?rs.getString(14):"");
				formbean.setActualpay(rs.getString(15)!=null?rs.getString(15):"");
				formbean.setLiuchenghao(rs.getString(16)!=null?rs.getString(16):"");
				formbean.setFinancialoperator(rs.getString(17)!=null?rs.getString(17):"");
				formbean.setDfzflx(rs.getString(18)!=null?rs.getString(18):"");
				//formbean.setJztype(rs.getString(20)!=null?rs.getString(20):"");
				formbean.setBgsign(rs.getString(19)!=null?rs.getString(19):"");
				formbean.setBieming(rs.getString(20)!=null?rs.getString(20):"");
				//formbean.setDllx(rs.getString(23)!=null?rs.getString(23):"");
				//formbean.setDbyt(rs.getString(24)!=null?rs.getString(24):"");
				//formbean.setCsds(rs.getString(25)!=null?rs.getString(25):"");
				//formbean.setCssytime(rs.getString(26)!=null?rs.getString(26):"");
				formbean.setBeilv(rs.getString(21)!=null?rs.getString(21):"");
				formbean.setLastdatetime(rs.getString(22)!=null?rs.getString(22):"");
				formbean.setThisdatetime(rs.getString(23)!=null?rs.getString(23):"");
				formbean.setFloatdegree(rs.getString(24)!=null?rs.getString(24):"");
				//formbean.setInputoperator(rs.getString(31)!=null?rs.getString(31):"");
				formbean.setBlhdl(rs.getString(25)!=null?rs.getString(25):"");
				formbean.setMemo(rs.getString(26)!=null?rs.getString(26):"");
				formbean.setInputdatetime(rs.getString(27)!=null?rs.getString(27):"");
				formbean.setNotetime(rs.getString(28)!=null?rs.getString(28):"");
				formbean.setAccountmonth(rs.getString(29)!=null?rs.getString(29):"");
				formbean.setKptime(rs.getString(30)!=null?rs.getString(30):"");
				formbean.setMemoam(rs.getString(31)!=null?rs.getString(31):"");
				formbean.setStationtype(rs.getString(32)!=null?rs.getString(32):"");
				formbean.setNoteno(rs.getString(33)!=null?rs.getString(33):"");
				formbean.setXian(rs.getString(34)!=null?rs.getString(34):"");
				formbean.setXiaoqu(rs.getString(35)!=null?rs.getString(35):"");
				formbean.setAmmeterdegreeid(rs.getString(36)!=null?rs.getString(36):"");
				formbean.setZlfh(rs.getString(37)!=null?rs.getString(37):"");
				formbean.setLastdegree(rs.getString(38)!=null?rs.getString(38):"");
				formbean.setThisdegree(rs.getString(39)!=null?rs.getString(39):"");
				formbean.setStartmonth(rs.getString(40)!=null?rs.getString(40):"");
				formbean.setEndmonth(rs.getString(41)!=null?rs.getString(41):"");
				formbean.setPayoperator(rs.getString(42)!=null?rs.getString(42):"");
				formbean.setPaydatetime(rs.getString(43)!=null?rs.getString(43):"");
				formbean.setAutoauditstatus(rs.getString(44)!=null?rs.getString(44):"");
				formbean.setJszq(rs.getString(45)!=null?rs.getString(45):"");
				formbean.setManualauditstatus(rs.getString(46)!=null?rs.getString(46):"");
				formbean.setManualauditdatetime(rs.getString(47)!=null?rs.getString(47):"");
				formbean.setManualauditname(rs.getString(48)!=null?rs.getString(48):"");
				formbean.setFinancialdatetime(rs.getString(49)!=null?rs.getString(49):"");
				formbean.setCityaudit(rs.getString(50)!=null?rs.getString(50):"");
				formbean.setGdfs(rs.getString(51)!=null?rs.getString(51):"");
				formbean.setBgdf(rs.getString(52)!=null?rs.getString(52):"");
				formbean.setScdff(rs.getString(53)!=null?rs.getString(53):"");
				formbean.setYydf(rs.getString(54)!=null?rs.getString(54):"");
				formbean.setJstzdf(rs.getString(55)!=null?rs.getString(55):"");
				formbean.setXxhdf(rs.getString(56)!=null?rs.getString(56):"");
				formbean.setKjyf(rs.getString(57)!=null?rs.getString(57):"");
				formbean.setJlfh(rs.getString(58)!=null?rs.getString(58):"");
				formbean.setDddfdl(rs.getString(59)!=null?rs.getString(59):"");
				formbean.setDddfdf(rs.getString(60)!=null?rs.getString(60):"");
				formbean.setDbname(rs.getString(61)!=null?rs.getString(61):"");
				
				list.add(formbean);
		      }
	    }catch (DbException de) {
	      de.printStackTrace();
	    } catch (SQLException de) {
	      de.printStackTrace();
	    }finally {
	    	db.free(rs,sta,conn);
	    }
	    return list;
	  }

	//电费、电量详细信息查询
	public synchronized ArrayList getPageDatady(int start,String whereStr,String loginId,String blhdl1,String dfdf) {
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    //调用负责站点条件函数
		try {
			 fzzdstr = getFuzeZdid(db,loginId);
	      sql =

	    	  "select distinct (SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE=ZZ.SHI) shi,(SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE=ZZ.XIAN) xiaoqu, "+
	    	  "(select NAME from indexs where CODE = zz.STATIONTYPE and type='stationtype') as stationtype," +
	    	  " aa.ammeterdegreeid,zz.jzname,RTNAME(zz.PROPERTY) as property,aa.blhdl,ee.actualpay, ee.ELECTRICFEE_ID,TO_CHAR(aa.startmonth,'yyyy-mm') STARTMONTH, TO_CHAR(aa.endmonth,'yyyy-mm') ENDMONTH,zz.g2,zz.g3,TO_CHAR(ee.accountmonth,'yyyy-mm') ACCOUNTMONTH,TO_CHAR(AA.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(AA.THISDATETIME,'yyyy-mm-dd') THISDATETIME,"+
	    	//  " (CASE WHEN (TO_DATE(AA.THISDATETIME, 'yyyy-mm-dd') -  TO_DATE(AA.LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE    AA.BLHDL / TO_NUMBER(TO_DATE(AA.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(AA.LASTDATETIME, 'yyyy-mm-dd') + 1) / ZZ.ZPSL END) DZPNH, "+
	    	//  " (CASE WHEN (TO_DATE(AA.THISDATETIME, 'yyyy-mm-dd') -  TO_DATE(AA.LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE  TO_NUMBER(TO_DATE(AA.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(AA.LASTDATETIME, 'yyyy-mm-dd') + 1) END) TS "+
	    	  " zz.zlfh, zz.jlfh,zz.zpsl,zz.zssl,zz.zdcode,dd.dbid "+
	    	   " from  zhandian zz,dianbiao dd,dianduview aa,dianfeiview ee  "+
	    	  " where zz.id = dd.zdid "+
	    	  " and zz.zpsl >0"+
	    	  " and dd.dbid = aa.ammeterid_fk "+
	    	  " and aa.ammeterdegreeid = ee.ammeterdegreeid_fk "+whereStr+dfdf+
		      " and ((zz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ORDER BY AA.blhdl DESC";
	      System.out.println("电费电量详细信息查询:"+sql.toString());

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	    ResultSet rs = null;
	    try {
	    	db.connectDb();	 
	      	  conn = db.getConnection();
	      	  sta = conn.createStatement();
	    	rs = sta.executeQuery(sql);
	      Query query=new Query();
	      list = query.query(rs);
	    }
	   
	    catch (DbException de) {
	      de.printStackTrace();
	    } catch (Exception de) {
	      de.printStackTrace();
	    }

	    finally {
	    	db.free(rs,sta,conn);
	    }
	    return list;
	  }
	//电费电量信息缴纳明细导出
	public synchronized ArrayList getPageDatadc(String whereStr,String loginId,String dfdf) {
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    //调用负责站点条件函数
		try {
			 fzzdstr = getFuzeZdid(db,loginId);
	      sql=

	    	  "select distinct (SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE=ZZ.SHI) shi,(SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE=ZZ.XIAN) xiaoqu, "+
	    	  "(select NAME from indexs where CODE = zz.STATIONTYPE and type='stationtype') as stationtype," +
	    	  " aa.ammeterdegreeid,zz.jzname,RTNAME(zz.PROPERTY) as property,aa.blhdl,ee.actualpay, ee.ELECTRICFEE_ID,TO_CHAR(aa.startmonth,'yyyy-mm') startmonth, TO_CHAR(aa.endmonth,'yyyy-mm') endmonth,zz.g2,zz.g3,to_char(ee.accountmonth,'yyyy-mm') accountmonth,TO_CHAR(AA.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(AA.THISDATETIME,'yyyy-mm-dd') THISDATETIME,"+
	    	//  " (CASE WHEN (TO_DATE(AA.THISDATETIME, 'yyyy-mm-dd') -  TO_DATE(AA.LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE    AA.BLHDL / TO_NUMBER(TO_DATE(AA.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(AA.LASTDATETIME, 'yyyy-mm-dd') + 1) / ZZ.ZPSL END) DZPNH, "+
	    	//  " (CASE WHEN (TO_DATE(AA.THISDATETIME, 'yyyy-mm-dd') -  TO_DATE(AA.LASTDATETIME, 'yyyy-mm-dd') + 1) = 0 THEN 0 ELSE  TO_NUMBER(TO_DATE(AA.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(AA.LASTDATETIME, 'yyyy-mm-dd') + 1) END) TS "+
	    	  " zz.zlfh, zz.jlfh,zz.zpsl,zz.zssl,zz.zdcode,dd.dbid "+
	    	   " from  zhandian zz,dianbiao dd,dianduview aa,dianfeiview ee  "+
	    	  " where zz.qyzt='1' and zz.id = dd.zdid "+
	    	  " and zz.zpsl >0"+
	    	  "  AND DD.DBQYZT='1' "+
	    	  " and dd.dbid = aa.ammeterid_fk "+
	    	  " and aa.ammeterdegreeid = ee.ammeterdegreeid_fk "+whereStr+dfdf+
		      " and ((zz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ORDER BY AA.blhdl DESC";
	      System.out.println("电费电量信息缴纳明细导出："+sql.toString());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	    ResultSet rs = null;
	    try {
	    	db.connectDb();	 
	      	  conn = db.getConnection();
	      	  sta = conn.createStatement();
	    	rs = sta.executeQuery(sql.toString());
		      Query query=new Query();
		      list = query.query(rs);
	    }
	   
	    catch (Exception de) {
	      de.printStackTrace();
	    } 

	    finally {
	    	db.free(rs,sta,conn);
	    }
	    return list;
	  }
	//电费
	public String getCountPay(String whereStr,String loginId,String str){
		String sql = "";
	    String count="";
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
				fzzdstr = getFuzeZdid(db,loginId);
			
			StringBuffer strall = new StringBuffer();
		      strall.append("select sum(actualpay) from dianbiao d,zhandian zd,dianduview ad,dianfeiview ef " +
		        "where zd.id=d.zdid and d.dbid=ad.ammeterid_fk and zd.qyzt='1' " +
		        "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk "+whereStr+str+
		        "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
			System.out.println("电费缴纳明细汇总："+strall.toString());
			db.connectDb();	 
	      	  conn = db.getConnection();
	      	  sta = conn.createStatement();
			rs = sta.executeQuery(strall.toString());
            while(rs.next()){
            	count=rs.getString(1);
            }
		} catch (Exception e) {
					e.printStackTrace();
            }
		}
		catch (DbException de) {
			de.printStackTrace();
		} 
		finally {
			db.free(rs,sta,conn);
		}
		return count;
	}
	//电量
	public String getCountGree(String whereStr,String loginId,String str){
		String sql = "";
	    String count="";
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
				fzzdstr = getFuzeZdid(db,loginId);
			
			StringBuffer strall = new StringBuffer();
		      strall.append("select sum(blhdl) from dianbiao d,zhandian zd,dianduview ad,dianfeiview ef " +
			        "where zd.id=d.zdid and d.dbid=ad.ammeterid_fk and zd.qyzt='1' " +
			        "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk "+whereStr+str+
			        "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
			System.out.println("电费缴纳明细汇总："+strall.toString());
			db.connectDb();	 
	      	  conn = db.getConnection();
	      	  sta = conn.createStatement();
			rs = sta.executeQuery(strall.toString());
            while(rs.next()){
					count=rs.getString(1);
            }
		} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
            }
		}
		catch (DbException de) {
			de.printStackTrace();
		} 
		finally {
			db.free(rs,sta,conn);
		}
		return count;
	}
	
	
	//电费缴纳明细汇总
	public ElectricFeesFormBean getCount(String whereStr,String loginId){
		String zong = "";//总数
		String shen = "";//人工审核通过的
		String weishen = "";//人工审核未审核
		String caiwu = "";//财务审核
		String dl = "";//折算后用电量总和
		String df = "";//电费总和
		String sql = "";
		DataBase db = new DataBase();
		ElectricFeesFormBean bean1 = new ElectricFeesFormBean();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			  fzzdstr = getFuzeZdid(db,loginId);
			  StringBuffer strall = new StringBuffer();
		      strall.append("SELECT COUNT(*) ZONG,  SUM(DECODE(EF.MANUALAUDITSTATUS, '1', 1,'2',1)) SHEN,SUM(DECODE(EF.MANUALAUDITSTATUS, '0', 1,'',1)) WEISHEN, "+
		    		  "SUM(DECODE(EF.MANUALAUDITSTATUS, '2', 1)) CAIWU,SUM(AD.BLHDL) DL,SUM(EF.ACTUALPAY) DF FROM  DIANFEIVIEW EF, DIANDUVIEW AD,ZHANDIAN ZD,DIANBIAO DB "+
		    		  "WHERE AD.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK AND ZD.ID=DB.ZDID AND DB.DBID=AD.AMMETERID_FK "+whereStr+"AND QYZT='1' "+
		              "AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+")");
			System.out.println("电费缴纳明细汇总："+strall.toString());
			db.connectDb();	 
	      	  conn = db.getConnection();
	      	  sta = conn.createStatement();
			rs = sta.executeQuery(strall.toString());
			while(rs.next()){
          		zong = rs.getString(1)!=null?rs.getString(1):"0";
          		shen = rs.getString(2)!=null?rs.getString(2):"0";
          		weishen = rs.getString(3)!=null?rs.getString(3):"0";
          		caiwu = rs.getString(4)!=null?rs.getString(4):"0";
          		dl = rs.getString(5)!=null?rs.getString(5):"0";
          		df = rs.getString(6)!=null?rs.getString(6):"0";
			}
          	bean1.setCount(zong);
          	bean1.setRg(shen);
          	bean1.setRgno(weishen);
          	bean1.setCw(caiwu);
          	bean1.setBlhdl(dl);
          	bean1.setActualpay(df);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
       }finally{
    	   db.free(rs,sta,conn);
       }
	return bean1;
}
	
	  
	  
	  //获取详细信息
	  public synchronized ElectricFeesFormBean getinformationdbxx(String dbid,String dlid) {
			ElectricFeesFormBean bean1 = new ElectricFeesFormBean();
		    StringBuffer sql = new StringBuffer();
		    sql.append("SELECT DF.NETWORKDF,DF.INFORMATIZATIONDF, DF.ADMINISTRATIVEDF,DF.MARKETDF,DF.BUILDDF,DD.NETWORKHDL,DD.INFORMATIZATIONHDL,"+
		    		"DD.ADMINISTRATIVEHDL,DD.MARKETHDL,DD.BUILDHDL,DF.DDDF AS DDDFDF,DD.DDDF AS DDDFDL  FROM DIANFEIVIEW DF, DIANDUVIEW DD"+
		    		" WHERE DF.AMMETERDEGREEID_FK = DD.AMMETERDEGREEID AND DD.AMMETERID_FK='"+dbid+"' AND DF.ELECTRICFEE_ID='"+dlid+"'");		    
		    DataBase db = new DataBase();
		    ResultSet rs = null;
		    try {
		      System.out.println("电费缴纳明细详细信息:"+sql);
		      db.connectDb();	 
		      conn = db.getConnection();
		      sta = conn.createStatement();
		      rs = sta.executeQuery(sql.toString());
		      while(rs.next()){
				bean1.setScdff(rs.getString(1)!=null?rs.getString(1):""); 
				bean1.setXxhdf(rs.getString(2)!=null?rs.getString(2):"");
				bean1.setBgdf(rs.getString(3)!=null?rs.getString(3):"");
				bean1.setYydf(rs.getString(4)!=null?rs.getString(4):"");
				bean1.setJstzdf(rs.getString(5)!=null?rs.getString(5):"");
				bean1.setScdl(rs.getString(6)!=null?rs.getString(6):"");
				bean1.setXxhdl(rs.getString(7)!=null?rs.getString(7):"");
				bean1.setBgdl(rs.getString(8)!=null?rs.getString(8):"");
				bean1.setYydl(rs.getString(9)!=null?rs.getString(9):"");
				bean1.setJstzdl(rs.getString(10)!=null?rs.getString(10):"");
				bean1.setDddfdf(rs.getString(11)!=null?rs.getString(11):"");
				bean1.setDddfdl(rs.getString(12)!=null?rs.getString(12):"");
		      }
		    } catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				db.free(rs,sta,conn);
		    }
		    return bean1;
		  }
	//获取站点详细信息
	  public synchronized ArrayList getinformationzdmsg(String zdid) {
			ArrayList list = new ArrayList();
		    StringBuffer sql = new StringBuffer();
		    sql.append("select z.jzname, z.edhdl,z.bgsign,z.jnglmk,z.kongtiao,d.beilv,(select t.name from indexs t where t.code = z.property and TYPE = 'ZDSX') as property, "+
		            " (select NAME from indexs where CODE = z.STATIONTYPE and type = 'stationtype') as stationtype "+
		            " from zhandian z, dianbiao d where z.id = d.zdid and z.zdcode='"+zdid+"'");
		    DataBase db = new DataBase();
		    ResultSet rs = null;
		    try {
		      System.out.println("站点详细信息:"+sql);
		      db.connectDb();	 
		      conn = db.getConnection();
		      sta = conn.createStatement();
		      rs = sta.executeQuery(sql.toString());
		      Query query = new Query();
		      list = query.query(rs);
		    }
		    catch (Exception de) {
		      de.printStackTrace();
		    }finally {
		    	db.free(rs,sta,conn);
		    }
		    return list;
		  }
	//获取站点电费详细信息
	  public synchronized ArrayList getinformationdfmsg(String zdid,String dlid,String dbid) {
			ArrayList list = new ArrayList();
		    StringBuffer sql = new StringBuffer();
		    sql.append("select z.jzname,d.dbname,to_char(am.lastdatetime,'yyyy-mm-dd') lastdatetime,am.lastdegree,to_char(am.thisdatetime,'yyyy-mm-dd') thisdatetime,am.thisdegree,to_char(am.startmonth,'yyyy-mm') startmonth,to_char(am.endmonth,'yyyy-mm') endmonth,am.Floatdegree,"+
		    		  " am.blhdl,z.Dianfei,ee.FLOATPAY,ee.actualpay,to_char(ee.accountmonth,'yyyy-mm') accountmonth "+
		    		  " from zhandian z, dianfeiview ee, dianbiao d, dianduview am "+
		    		 " where z.id = d.zdid and am.ammeterid_fk = d.dbid and ee.ammeterdegreeid_fk=am.ammeterdegreeid "+ 
		    		 " and z.zdcode ='"+zdid+"' "+
		    		   " and d.dbid ='"+dbid+"' "+
		    		   " and am.ammeterdegreeid ='"+dlid+"'");
		    DataBase db = new DataBase();
		    ResultSet rs = null;
		    try {
		      System.out.println("站点电费详细信息:"+sql);
		      db.connectDb();	 
		      conn = db.getConnection();
		      sta = conn.createStatement();
		      rs = sta.executeQuery(sql.toString());	      
		      Query query = new Query();
		      list = query.query(rs);
		    }
		    catch (Exception de) {
		      de.printStackTrace();
		    }finally {
		    	db.free(rs,sta,conn);
		    }
		    return list;
		  }
	  
  private int allPage;
  public void setAllPage(int allpage ){
	  this.allPage=allpage;
	  
  }
  public int getAllPage(){
	  return this.allPage;
  }

//负责站点条件
	private String getFuzeZdid(DataBase db, String loginid) throws DbException, SQLException {
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
		}catch (Exception de) {
		      de.printStackTrace();
	    }finally {
	    	db.free(rs,sta,conn);
	    }
		
		return cxtj.toString();
	}
	
	
	
	//首页上审核条数的查询 
	public ElectricFeesFormBean getCou(String loginId){
		ElectricFeesFormBean bean = new ElectricFeesFormBean();
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();	 
			conn = db.getConnection();
			sta = conn.createStatement();
			try {
				fzzdstr = getFuzeZdid(db,loginId);
			StringBuffer strall = new StringBuffer();
			strall.append("select count(*)  "+                 //--自动审核通过
                         " ,sum(case when ef.manualauditstatus=1 then 1 else 0 end) "+ //--待审单据
                         ",sum(case when ef.autoauditstatus=0 then 1 else 0 end) "+//--自动审核未通过
                         ",sum(case when ef.manualauditstatus=0 then 1 else 0 end) "+//--区县待审
                         //",sum(case when ef.cityaudit=0 then 1 else 0 end) "+          //--市级待审
                        // ",sum(case when ef.manualauditstatus<>2 then 1 else 0 end) "+//--财务待确认
                         //" ,sum(case when nvl(ef.LIUCHENGHAO,0)=0 then 1 else 0 end) "+//--报账打印
                         " from zhandian zd, dianbiao d,dianduview  ad,dianfeiview ef "+
                         " where zd.id = d.zdid and d.dbid = ad.ammeterid_fk "+
                         " and ad.ammeterdegreeid = ef.ammeterdegreeid_fk " +
                         " and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
			
						System.out.println("++++++"+strall.toString() + "***********************");
						db.connectDb();	 
				      	  conn = db.getConnection();
				      	  sta = conn.createStatement();
						rs = sta.executeQuery(strall.toString());
            		while(rs.next()){
					bean.setCount(rs.getString(1));//总数
					bean.setRg(rs.getString(2));//人工审核通过
					bean.setZdno(rs.getString(3));//自动审核未通过
					bean.setRgno(rs.getString(4));//人工审核未通过
					//bean.setShish(rs.getString(5));//二级审核未通过
					//bean.setCwno(rs.getString(5));//财务审核 未通过
					//bean.setBzdy(rs.getString(5));//报账没打印条数   打印后才会有流程号
            		 }
            		strall.setLength(0);
            		strall.append("select count(*) from ZHANDIAN ZD, DIANBIAO AM, DIANFEIDANDAYIN DY" +
            				" where ZD.ID = AM.ZDID AND AM.DBID = DY.AMMETERID_FK AND DY.MANUALAUDITSTATUS >= '1' and dy.liuchenghao is null" +
            				" and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
            		System.out.println("电费单打印"+strall.toString() + "***********************");
            		rs = sta.executeQuery(strall.toString());
					while(rs.next()){
						bean.setBzdy(rs.getString(1));//报账没打印条数   打印后才会有流程号
					}
            		strall.setLength(0);
            		strall.append("select count(*)"+      //站点总数
            					",sum(case when shsign<>1 then 1 else 0 end) "+//--站点审核未通过的
            					",sum(case when shsign='1' then 1 else 0 end) "+//--站点审核通过 
            					",sum(case when caiji='1' then 1 else 0 end ) "+//采集站点
            					"from zhandian t where " +
            					"((t.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");

					System.out.println("站点审核"+strall.toString() + "***********************");
            		rs = sta.executeQuery(strall.toString());
					while(rs.next()){
						bean.setZdzs(rs.getString(1));//站点总数
						bean.setJdno(rs.getString(2));//站点未审核
						bean.setZd(rs.getString(3));//站点审核通过
						bean.setCaijizd(rs.getString(4));//采集站点
					}
					strall.setLength(0);
            		strall.append("select count(*)"+      //站点总数
            					"from zhandian t where caiji='1' and shsign='1' and " +
            					"((t.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");

					System.out.println("采集"+strall.toString() + "***********************");
            		rs = sta.executeQuery(strall.toString());
					while(rs.next()){
						bean.setCaijizd(rs.getString(1));//采集站点
					}
					
					strall.setLength(0);
					strall.append("select count(*) "+
								",sum(case when dbyt='dbyt01' then 1 else 0 end) "+ //--结算电表数
								",sum(case when dbyt='dbyt03' then 1 else 0 end) "+ //--管理电表
								"from dianbiao d,zhandian zd where zd.id = d.zdid " +
								" and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");

					System.out.println("电表数"+strall.toString() + "***********************");
            		rs = sta.executeQuery(strall.toString());
					while(rs.next()){
						bean.setJsdb(rs.getString(2));//结算电表数
						bean.setGldb(rs.getString(3));//管理电表
					}
					
            		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
            }
		}
		catch (Exception de) {
			de.printStackTrace();
		} 
		finally {
			db.free(rs,sta,conn);
		}
		return bean;
	}
	//首页上审核条数的查询 
	public ElectricFeesFormBean getCaiji(String loginId){
		ElectricFeesFormBean bean = new ElectricFeesFormBean();
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();	 
			conn = db.getConnection();
			sta = conn.createStatement();
			try {
				fzzdstr = getFuzeZdid(db,loginId);
			StringBuffer strall = new StringBuffer();
            		strall.setLength(0);
            		strall.append("select count(*)"+      //站点总数
            					//",sum(case when shsign<>1 then 1 else 0 end) "+//--站点审核未通过的
            					//",sum(case when shsign='1' then 1 else 0 end) "+//--站点审核通过 
            					",sum(case when caiji='1' then 1 else 0 end ) "+//采集站点
            					"from zhandian t where " +
            					"((t.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");

					System.out.println("站点审核"+strall.toString() + "***********************");
					db.connectDb();	 
			      	  conn = db.getConnection();
			      	  sta = conn.createStatement();
					rs = sta.executeQuery(strall.toString());
					while(rs.next()){
						bean.setZdzs(rs.getString(1));//站点总数
						//bean.setJdno(rs.getString(2));//站点未审核
						//bean.setZd(rs.getString(3));//站点审核通过
						bean.setCaijizd(rs.getString(2));//采集站点
					}
					strall.setLength(0);
            		strall.append("select count(*)"+      //站点总数
            					"from zhandian t where caiji='1' and shsign='1' and " +
            					"((t.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");

					System.out.println("采集"+strall.toString() + "***********************");
					rs = sta.executeQuery(strall.toString());
					while(rs.next()){
						bean.setCaijizd(rs.getString(1));//采集站点
					}
					
					strall.setLength(0);
            		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
            }
		}
		catch (Exception de) {
			de.printStackTrace();
		} 
		finally {
			db.free(rs,sta,conn);
		}
		return bean;
	}
}
