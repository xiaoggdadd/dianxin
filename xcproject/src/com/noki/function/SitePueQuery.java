package com.noki.function;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.function.SitePueQueryBean;

public class SitePueQuery {
	 //站点PUE查询
	public synchronized List<SitePueQueryBean> getPageData23(String whereStr,String loginId,String pue,String chaobiao ,String yzchaobiao,String yhdld,String yhdlx,String dbyt,String accountmonth) {
		List<SitePueQueryBean> list = new ArrayList<SitePueQueryBean>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql="SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode=aa.shi)," +
					"COUNT(DISTINCT AA.ID),COUNT(DISTINCT(CASE WHEN to_number(aa.blhdl/(AA.BILI * AA.TS)-'"+pue+"')/'"+pue+"'*100>'"+chaobiao+"' THEN  AA.ID END)), " +
					"COUNT(DISTINCT(CASE WHEN to_number(aa.blhdl/(AA.BILI * AA.TS)-'"+pue+"')/'"+pue+"'*100>'"+yzchaobiao+"' THEN  AA.ID END)),AA.SHI " +
					"FROM (SELECT Z.SHI,Z.ID,Z.ZLFH,Z.JLFH,A.BLHDL,(CASE WHEN (Z.JLFH IS NOT NULL AND Z.JLFH <> 0) OR(Z.ZLFH IS NOT NULL AND Z.ZLFH <> 0) " +
					"THEN ( (Z.ZLFH * 54 + Z.JLFH * 220) / 1000 * 24) END) AS BILI, " +
					"(CEIL(A.THISDATETIME -A.LASTDATETIME) + 1) AS TS  " +
					" FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A, DIANFEIVIEW E   WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK " +
					" AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm')='"+accountmonth+"' and D.DBYT='"+dbyt+"' "+whereStr+" ) AA   WHERE   AA.BILI*30>'"+yhdld+"' AND AA.BILI*30<='"+yhdlx+"'     GROUP BY AA.SHI";
			
			
			String sql1="SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode=aa.shi)," +
			"COUNT(DISTINCT AA.ID),COUNT(DISTINCT(CASE WHEN to_number(aa.blhdl/(AA.BILI * (AA.TS/AA.DBID))-'"+pue+"')/'"+pue+"'*100>'"+chaobiao+"' THEN  AA.ID END)), " +
			"COUNT(DISTINCT(CASE WHEN to_number(aa.blhdl/(AA.BILI * (AA.TS/AA.DBID))-'"+pue+"')/'"+pue+"'*100>'"+yzchaobiao+"' THEN  AA.ID END)),AA.SHI " +
			"FROM (SELECT Z.SHI,Z.ID,Z.ZLFH,Z.JLFH,SUM(A.BLHDL) AS BLHDL,COUNT(DISTINCT D.DBID) AS DBID,(CASE WHEN (Z.JLFH IS NOT NULL AND Z.JLFH <> 0) OR(Z.ZLFH IS NOT NULL AND Z.ZLFH <> 0) " +
			"THEN ( (Z.ZLFH * 54 + Z.JLFH * 220) / 1000 * 24) END) AS BILI, " +
			"COUNT(A.AMMETERDEGREEID) AS TS  " +
			" FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A   WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK " +
			"   AND D.DBYT = '"+dbyt+"' AND TO_CHAR(A.THISDATETIME,'yyyy-mm-dd') LIKE '%"+accountmonth+"%' AND D.YDSB = 'ybsb05' "+whereStr+" GROUP BY Z.SHI, Z.ID, Z.ZLFH, Z.JLFH  ) AA   WHERE   AA.BILI*30>'"+yhdld+"' AND AA.BILI*30<='"+yhdlx+"'     GROUP BY AA.SHI";
			
			String sql2="SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode=aa.shi)," +
			"COUNT(DISTINCT AA.ID),COUNT(DISTINCT(CASE WHEN to_number(aa.blhdl/(AA.BILI * AA.TS)-'"+pue+"')/'"+pue+"'*100>'"+chaobiao+"' THEN  AA.ID END)), " +
			"COUNT(DISTINCT(CASE WHEN to_number(aa.blhdl/(AA.BILI * AA.TS)-'"+pue+"')/'"+pue+"'*100>'"+yzchaobiao+"' THEN  AA.ID END)),AA.SHI " +
			"FROM (SELECT Z.SHI,Z.ID,Z.ZLFH,Z.JLFH,A.BLHDL,(CASE WHEN (Z.JLFH IS NOT NULL AND Z.JLFH <> 0) OR(Z.ZLFH IS NOT NULL AND Z.ZLFH <> 0) " +
			"THEN ( (Z.ZLFH * 54 + Z.JLFH * 220) / 1000 * 24) END) AS BILI, " +
			"(CEIL(A.THISDATETIME -A.LASTDATETIME) + 1) AS TS  " +
			" FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A   WHERE Z.ID = D.ZDID AND D.DBID = A.AMMETERID_FK " +
			" AND D.DBYT='"+dbyt+"' AND TO_CHAR(A.STARTMONTH,'yyyy-mm')='"+accountmonth+"' "+whereStr+" ) AA   WHERE   AA.BILI*30>'"+yhdld+"' AND AA.BILI*30<='"+yhdlx+"'     GROUP BY AA.SHI";
			if(dbyt.equals("dbyt01")){
				System.out.println("站点PUE查询1："+sql.toString());
				rs = db.queryAll(sql.toString());
				while(rs.next()){
					SitePueQueryBean zdbean=new SitePueQueryBean();
					zdbean.setShi(rs.getString(1));
					zdbean.setZdsum(rs.getString(2));
					zdbean.setChaobiaozdsum(rs.getString(3));
					zdbean.setYzchaobiaozdsum(rs.getString(4));
					zdbean.setShicode(rs.getString(5));
					list.add(zdbean);
				}
			}else if(dbyt.equals("dbyt02")){
				System.out.println("站点PUE查询2："+sql1.toString());
				rs = db.queryAll(sql1.toString());
				while(rs.next()){
					SitePueQueryBean zdbean=new SitePueQueryBean();
					zdbean.setShi(rs.getString(1));
					zdbean.setZdsum(rs.getString(2));
					zdbean.setChaobiaozdsum(rs.getString(3));
					zdbean.setYzchaobiaozdsum(rs.getString(4));
					zdbean.setShicode(rs.getString(5));
					list.add(zdbean);
				}
			}else if(dbyt.equals("dbyt03")){
				System.out.println("站点PUE查询3："+sql2.toString());
				rs = db.queryAll(sql2.toString());
				while(rs.next()){
					SitePueQueryBean zdbean=new SitePueQueryBean();
					zdbean.setShi(rs.getString(1));
					zdbean.setZdsum(rs.getString(2));
					zdbean.setChaobiaozdsum(rs.getString(3));
					zdbean.setYzchaobiaozdsum(rs.getString(4));
					zdbean.setShicode(rs.getString(5));
					list.add(zdbean);
				}
			}
			
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
			
				if(db!=null){
				
					try {
						db.close();
					}
				
			 catch (DbException de) {
				de.printStackTrace();
			}

		}
		}
		return list;
	}
	 //站点PUE查询县
	public synchronized List<SitePueQueryBean> getPageData(String whereStr,String loginId,String pue,String chaobiao ,String yhdld,String yhdlx,String dbyt,String accountmonth) {
		List<SitePueQueryBean> list = new ArrayList<SitePueQueryBean>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			String sql="SELECT (SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE = AA.XIAN), AA.JZNAME," +
					"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = AA.PROPERTY), AA.ZLFH," +
					" AA.JLFH,AA.BLHDL, AA.ACTUALPAY,AA.BLHDL / (AA.BILI * AA.TS) AB,AA.BLHDL / (AA.BILI * AA.TS)-'"+pue+"' / '"+pue+"',   " +
					"AA.BILI * 30,AA.DBID,AA.ID,AA.BLHDL/AA.TS*30  FROM (SELECT Z.XIAN,Z.ID, D.DBID,Z.JZNAME, Z.PROPERTY,Z.ZLFH, Z.JLFH, A.BLHDL,E.ACTUALPAY," +
					"(CASE WHEN (Z.JLFH IS NOT NULL AND Z.JLFH <> 0) OR (Z.ZLFH IS NOT NULL AND Z.ZLFH <> 0) " +
					"THEN ((Z.ZLFH * 54 + Z.JLFH * 220) / 1000 * 24) END) AS BILI,  " +
					"(CEIL(A.THISDATETIME - A.LASTDATETIME) + 1) AS TS " +
					" FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A, DIANFEIVIEW E WHERE Z.ID = D.ZDID " +
					" AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK  AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm-dd')='"+accountmonth+"' "+whereStr+") AA  " +
					"WHERE   AA.BILI * 30 > '"+yhdld+"'  AND AA.BILI * 30 <= '"+yhdlx+"'";
			
			String sql1="SELECT (SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE = AA.XIAN), AA.JZNAME," +
			"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = AA.PROPERTY), AA.ZLFH," +
			" AA.JLFH,AA.BLHDL, AA.ACTUALPAY,AA.BLHDL / (AA.BILI * AA.TS) AB,AA.BLHDL / (AA.BILI * AA.TS)-'"+pue+"' / '"+pue+"',   " +
			"AA.BILI * 30,AA.DBID,AA.ID,AA.BLHDL/AA.TS*30  FROM (SELECT Z.XIAN,Z.ID, D.DBID,Z.JZNAME, Z.PROPERTY,Z.ZLFH, Z.JLFH, A.BLHDL,E.ACTUALPAY," +
			"(CASE WHEN (Z.JLFH IS NOT NULL AND Z.JLFH <> 0) OR (Z.ZLFH IS NOT NULL AND Z.ZLFH <> 0) " +
			"THEN ((Z.ZLFH * 54 + Z.JLFH * 220) / 1000 * 24) END) AS BILI,  " +
			"(CEIL(A.THISDATETIME - A.LASTDATETIME) + 1) AS TS " +
			" FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A, DIANFEIVIEW E WHERE Z.ID = D.ZDID " +
			" AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK  "+whereStr+") AA  " +
			"WHERE   AA.BILI * 30 > '"+yhdld+"'  AND AA.BILI * 30 <= '"+yhdlx+"'";
			
			String sql2="SELECT (SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE = AA.XIAN), AA.JZNAME," +
			"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = AA.PROPERTY), AA.ZLFH," +
			" AA.JLFH,AA.BLHDL, AA.ACTUALPAY,AA.BLHDL / (AA.BILI * AA.TS) AB,AA.BLHDL / (AA.BILI * AA.TS)-'"+pue+"' / '"+pue+"',   " +
			"AA.BILI * 30,AA.DBID,AA.ID,AA.BLHDL/AA.TS*30  FROM (SELECT Z.XIAN,Z.ID, D.DBID,Z.JZNAME, Z.PROPERTY,Z.ZLFH, Z.JLFH, A.BLHDL,E.ACTUALPAY," +
			"(CASE WHEN (Z.JLFH IS NOT NULL AND Z.JLFH <> 0) OR (Z.ZLFH IS NOT NULL AND Z.ZLFH <> 0) " +
			"THEN ((Z.ZLFH * 54 + Z.JLFH * 220) / 1000 * 24) END) AS BILI,  " +
			"(CEIL(A.THISDATETIME -A.LASTDATETIME) + 1) AS TS " +
			" FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A, DIANFEIVIEW E WHERE Z.ID = D.ZDID " +
			" AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK  "+whereStr+") AA  " +
			"WHERE   AA.BILI * 30 > '"+yhdld+"'  AND AA.BILI * 30 <= '"+yhdlx+"'";
			//if(dbyt.equals("dbyt01")){
			System.out.println("站点PUE查询县1："+sql.toString());
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				SitePueQueryBean zdbean=new SitePueQueryBean();
				String p=rs.getString(8);
				if(null==p||"".equals(p)){
					p="0";
				}if((Double.parseDouble(p)-Double.parseDouble(pue))/Double.parseDouble(pue)*100>Double.parseDouble(chaobiao)){
				zdbean.setQuxian(rs.getString(1));
				zdbean.setZdname(rs.getString(2));
				zdbean.setZdsx(rs.getString(3));
				zdbean.setZlfh(rs.getString(4));
				zdbean.setJlfh(rs.getString(5));
				zdbean.setBcdl(rs.getString(6));
				zdbean.setBcdf(rs.getString(7));
				zdbean.setPue(rs.getString(8));
				zdbean.setChaobiaobili(rs.getString(9));
				zdbean.setYuehdl(rs.getString(10));
				zdbean.setDbid(rs.getString(11));
				zdbean.setZdid(rs.getString(12));
				zdbean.setSjyuehdl(rs.getString(13));
				list.add(zdbean);
				}
			}
//			}else if(dbyt.equals("dbyt02")){
//				System.out.println("站点PUE查询县2："+sql1.toString());
//				rs = db.queryAll(sql1.toString());
//				while(rs.next()){
//					SitePueQueryBean zdbean=new SitePueQueryBean();
//					String p=rs.getString(8);
//					if(null==p||"".equals(p)){
//						p="0";
//					}if((Double.parseDouble(p)-Double.parseDouble(pue))/Double.parseDouble(pue)*100>Double.parseDouble(chaobiao)){
//					zdbean.setQuxian(rs.getString(1));
//					zdbean.setZdname(rs.getString(2));
//					zdbean.setZdsx(rs.getString(3));
//					zdbean.setZlfh(rs.getString(4));
//					zdbean.setJlfh(rs.getString(5));
//					zdbean.setBcdl(rs.getString(6));
//					zdbean.setBcdf(rs.getString(7));
//					zdbean.setPue(rs.getString(8));
//					zdbean.setChaobiaobili(rs.getString(9));
//					zdbean.setYuehdl(rs.getString(10));
//					zdbean.setDbid(rs.getString(11));
//					zdbean.setZdid(rs.getString(12));
//					zdbean.setSjyuehdl(rs.getString(13));
//					list.add(zdbean);
//					}
//				}
//			}else if(dbyt.equals("dbyt03")){
//				System.out.println("站点PUE查询县3："+sql2.toString());
//				rs = db.queryAll(sql2.toString());
//				while(rs.next()){
//					SitePueQueryBean zdbean=new SitePueQueryBean();
//					String p=rs.getString(8);
//					if(null==p||"".equals(p)){
//						p="0";
//					}if((Double.parseDouble(p)-Double.parseDouble(pue))/Double.parseDouble(pue)*100>Double.parseDouble(chaobiao)){
//					zdbean.setQuxian(rs.getString(1));
//					zdbean.setZdname(rs.getString(2));
//					zdbean.setZdsx(rs.getString(3));
//					zdbean.setZlfh(rs.getString(4));
//					zdbean.setJlfh(rs.getString(5));
//					zdbean.setBcdl(rs.getString(6));
//					zdbean.setBcdf(rs.getString(7));
//					zdbean.setPue(rs.getString(8));
//					zdbean.setChaobiaobili(rs.getString(9));
//					zdbean.setYuehdl(rs.getString(10));
//					zdbean.setDbid(rs.getString(11));
//					zdbean.setZdid(rs.getString(12));
//					zdbean.setSjyuehdl(rs.getString(13));
//					list.add(zdbean);
//					}
//				}
//			}
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
			
				if(db!=null){
				
					try {
						db.close();
					}
				
			 catch (DbException de) {
				de.printStackTrace();
			}

		}
		}
		return list;
	}
	//详细费用信息
	 public synchronized List<ElectricFeesFormBean> getinformationdb(String dbid,String accountmonth) {
			ArrayList list = new ArrayList();
			ArrayList list1 = new ArrayList();
		  
		    ResultSet rs = null;
		   // dfbzw.equals(1);
		   
		    //sql.append("select * from electricfees where ELECTRICFEE_ID="+degreeid);
		    //sql.append("select * from electricfees where ELECTRICFEE_ID="+degreeid);
		        
		  String sql="SELECT  ZD.JZNAME,ZD.ZDCODE,D.DBNAME,AD.MEMO AS YDLBZ, EF.MEMO AS DFTZBZ,D.CSDS,TO_CHAR(D.CSSYTIME,'yyyy-mm-dd') CSSYTIME,D.BEILV," +
		  		"TO_CHAR(AD.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(AD.THISDATETIME,'yyyy-mm-dd') THISDATETIME, AD.FLOATDEGREE,(CASE WHEN ZD.SHI IS NOT NULL THEN " +
		  		"(SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI)  ELSE  '' END) || " +
		  		"(CASE WHEN ZD.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE  WHERE AGCODE = ZD.XIAN)  ELSE  ''  END) ||" +
		  		" (CASE  WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME   FROM D_AREA_GRADE  WHERE AGCODE = ZD.XIAOQU) " +
		  		" ELSE  '' END) AS SZDQ,(SELECT ACC.NAME FROM ACCOUNT ACC WHERE ACC.ACCOUNTNAME = EF.ENTRYPENSONNEL) AS ENTRYPENSONNEL," +
		  		"TO_CHAR(EF.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') ENTRYTIME, AD.LASTDEGREE,AD.THISDEGREE,AD.BLHDL AS ACTUALDEGREE,EF.UNITPRICE,EF.FLOATPAY,EF.ACTUALPAY,TO_CHAR(AD.STARTMONTH,'yyyy-mm') STARTMONTH" +
		  		",TO_CHAR(AD.ENDMONTH,'yyyy-mm') ENDMONTH,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = ZD.JZTYPE AND TYPE = 'ZDLX') AS JZTYPE, " +
		  		"(SELECT NAME  FROM INDEXS  WHERE CODE = D.YDSB  AND TYPE = 'YDSB') AS YDSB, D.DLLX," +
		  		"(SELECT NAME FROM ACCOUNT WHERE ACCOUNTNAME = EF.INPUTOPERATOR) AS INPUTOPERATOR, TO_CHAR(EF.INPUTDATETIME,'yyyy-mm-dd') INPUTDATETIME,TO_CHAR(EF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH, " +
		  		"(SELECT NAME FROM ACCOUNT WHERE ACCOUNTNAME = EF.PAYOPERATOR) AS PAYOPERATOR,EF.AUTOAUDITSTATUS,EF.MANUALAUDITSTATUS," +
		  		"AD.AUTOAUDITSTATUS AS DLSH FROM DIANBIAO D, ZHANDIAN ZD, DIANDUVIEW AD, DIANFEIVIEW EF WHERE ZD.ID = D.ZDID  " +
		  		"AND D.DBID = AD.AMMETERID_FK AND AD.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK  AND TO_CHAR(EF.ACCOUNTMONTH,'yyyy-mm-dd') = '"+accountmonth+"' AND D.DBID = '"+dbid+"'";
		    DataBase db = new DataBase();
		  
		    	 System.out.println("sssssql===:"+sql.toString());
		    try {
				rs = db.queryAll(sql.toString());
				while(rs.next()){
					 ElectricFeesFormBean formbean=new ElectricFeesFormBean();
					  formbean.setJzname(rs.getString(1));//Z.JZNAME
					  formbean.setZdcode(rs.getString(2));//Z.ZDCODE
                   formbean.setDbname(rs.getString(3));//D.DBNAME
                   formbean.setYdlbz(rs.getString(4));//YDLBZ
                   formbean.setDftzbz(rs.getString(5));//DFTZBZ
                   formbean.setCsds(rs.getString(6));//D.CSDS
                   formbean.setCssytime(rs.getString(7));//D.CSSYTIME
                   formbean.setBeilv(rs.getString(8));//D.BEILV
                   formbean.setLastdatetime(rs.getString(9));//ad.LASTDATETIME
                   formbean.setThisdatetime(rs.getString(10));//ad.THISDATETIME
                   formbean.setFloatdegree(rs.getString(11));//ad.FLOATDEGREE
                   formbean.setSzdq(rs.getString(12));//szdq
                   formbean.setEntrypensonnel(rs.getString(13));//entryperson//,,ad.LASTDEGREE,ad.THISDEGREE,ad.BLHDL as ACTUALDEGREE, ef.UNITPRICE,ef.FLOATPAY,ef.ACTUALPAY, ad.startmonth,ad.endmonth,"+
                   formbean.setEntrytime(rs.getString(14));//ef.ENTRYTIME
                   formbean.setLastdegree(rs.getString(15));//d.LASTDEGREE
                   formbean.setThisdegree(rs.getString(16));//d.THISDEGREE
                   formbean.setActualdegree(rs.getString(17));
                   formbean.setUnitprice(rs.getString(18));//ACTUALDEGREE
                   formbean.setFloatpay(rs.getString(19));
                   formbean.setActualpay(rs.getString(20));
                   formbean.setStartmonth(rs.getString(21));
                   formbean.setEndmonth(rs.getString(22));
                   formbean.setJztype(rs.getString(23));
                   formbean.setYdsb(rs.getString(24));
                   formbean.setDllx(rs.getString(25));
                   formbean.setInputoperator(rs.getString(26));
                   formbean.setInputdatetime(rs.getString(27));
                   formbean.setAccountmonth(rs.getString(28));
                   formbean.setPayoperator(rs.getString(29));
                   formbean.setAutoauditstatus(rs.getString(30));
					  formbean.setManualauditstatus(rs.getString(31));
					  formbean.setDlsh(rs.getString(32));
                   list.add(formbean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	finally {
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
