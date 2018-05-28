package com.noki.mobi.cx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

public class SitePaymentStatistics {
	 //站点缴费情况统计
	public ArrayList getlist(String whereStr,String loginId,String payment,String str){
		   ArrayList list=new ArrayList();
		   String sql="";
		   System.out.println("-----"+payment);
		   if(payment.equals("")||payment.equals("-1")||payment==null){
			    sql="SELECT DISTINCT Z.ZDCODE,Z.JZNAME," +
		   		" (CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN) ELSE '' END) " +
		   		"|| (CASE WHEN Z.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU) ELSE ''  END) AS SZDQ," +
		   		" (SELECT I.NAME  FROM INDEXS I WHERE I.CODE = Z.STATIONTYPE AND I.TYPE = 'stationtype') AS STATIONTYPE, " +
		   		" (SELECT I.NAME   FROM INDEXS I  WHERE I.CODE = D.DFZFLX  AND I.TYPE = 'dfzflx') AS DFZFLX," +
		   		"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = ZD.FKZQ AND I.TYPE = 'FKZQ') AS FKZQ, TO_CHAR(MAX(E.PAYDATETIME),'yyyy-mm-dd') AS PAYDATETIME,TO_CHAR(MAX(A.ENDMONTH),'yyyy-mm') AS ENDMONTH,COUNT(E.ELECTRICFEE_ID)AS PAYCOUNT  " +
		   		"FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A, DIANFEIVIEW E, ZDDFINFO ZD WHERE Z.ID = D.ZDID " +
		   		" AND Z.ID = ZD.ZDID(+) AND D.DBYT = 'dbyt01'  "+whereStr+str+"  AND D.DBID = A.AMMETERID_FK(+) AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK(+) " +
		   		"AND Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"') GROUP BY Z.ZDCODE,Z.JZNAME,Z.SHI,Z.XIAN,Z.XIAOQU,STATIONTYPE,DFZFLX, FKZQ";
		   }else{
			   if(payment.equals("0")){
				   sql="SELECT DISTINCT Z.ZDCODE,Z.JZNAME," +
			   		"(CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN) ELSE '' END) " +
			   		"|| (CASE WHEN Z.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU) ELSE ''  END) AS SZDQ," +
			   		" (SELECT I.NAME  FROM INDEXS I WHERE I.CODE = Z.STATIONTYPE AND I.TYPE = 'stationtype') AS STATIONTYPE, " +
			   		" (SELECT I.NAME   FROM INDEXS I  WHERE I.CODE = D.DFZFLX  AND I.TYPE = 'dfzflx') AS DFZFLX," +
			   		"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = ZD.FKZQ AND I.TYPE = 'FKZQ') AS FKZQ, TO_CHAR(MAX(E.PAYDATETIME),'yyyy-mm-dd') AS PAYDATETIME,TO_CHAR(MAX(A.ENDMONTH),'yyyy-mm') AS ENDMONTH,COUNT(E.ELECTRICFEE_ID)AS PAYCOUNT  " +
			   		"FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A, DIANFEIVIEW E, ZDDFINFO ZD WHERE Z.ID = D.ZDID " +
			   		" AND Z.ID = ZD.ZDID(+) AND D.DBYT = 'dbyt01'  "+whereStr+"  AND D.DBID = A.AMMETERID_FK(+) AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK(+) " +
			   				" AND D.DBID NOT IN  (SELECT A.AMMETERID_FK  FROM DIANDUVIEW A, DIANFEIVIEW E  WHERE A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK "+str+")" +
			   		"AND Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"') GROUP BY Z.ZDCODE,Z.JZNAME,Z.SHI,Z.XIAN,Z.XIAOQU,STATIONTYPE,DFZFLX, FKZQ";
			   }else{
				   sql="SELECT DISTINCT Z.ZDCODE,Z.JZNAME," +
			   		" (CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN) ELSE '' END) " +
			   		"|| (CASE WHEN Z.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU) ELSE ''  END) AS SZDQ," +
			   		" (SELECT I.NAME  FROM INDEXS I WHERE I.CODE = Z.STATIONTYPE AND I.TYPE = 'stationtype') AS STATIONTYPE, " +
			   		" (SELECT I.NAME   FROM INDEXS I  WHERE I.CODE = D.DFZFLX  AND I.TYPE = 'dfzflx') AS DFZFLX," +
			   		"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = ZD.FKZQ AND I.TYPE = 'FKZQ') AS FKZQ, TO_CHAR(MAX(E.PAYDATETIME),'yyyy-mm-dd') AS PAYDATETIME,TO_CHAR(MAX(A.ENDMONTH),'yyyy-mm') AS ENDMONTH,COUNT(E.ELECTRICFEE_ID)AS PAYCOUNT  " +
			   		"FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A, DIANFEIVIEW E, ZDDFINFO ZD WHERE Z.ID = D.ZDID " +
			   		" AND Z.ID = ZD.ZDID(+) AND D.DBYT = 'dbyt01'  "+whereStr+str+"  AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK " +
			   		"AND Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"') GROUP BY Z.ZDCODE,Z.JZNAME,Z.SHI,Z.XIAN,Z.XIAOQU,STATIONTYPE,DFZFLX, FKZQ";
			   }
		   }
		 
		   
		   System.out.println("站点缴费情况统计："+sql.toString());
		   DataBase db=new DataBase();
		   try {
			db.connectDb();
			ResultSet rs=null;
			rs=db.queryAll(sql);
			Query query=new Query();
			list=query.query(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   return list;
	   }
	
	//站点缴费情况统计详细电费信息条数
	public String getCount(String whereStr,String loginId,String payment,String str){
	    	String count="";
		   ArrayList list=new ArrayList();
		   String sql="";
		   System.out.println("-----"+payment);
		   if(payment.equals("")||payment.equals("-1")||payment==null){
			    sql="select count(*) from (SELECT DISTINCT Z.ZDCODE,Z.JZNAME," +
		   		"(CASE WHEN Z.SHI IS NOT NULL THEN (SELECT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI)ELSE '' END)" +
		   		" || (CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN) ELSE '' END) " +
		   		"|| (CASE WHEN Z.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU) ELSE ''  END) AS SZDQ," +
		   		" (SELECT I.NAME  FROM INDEXS I WHERE I.CODE = Z.STATIONTYPE AND I.TYPE = 'stationtype') AS STATIONTYPE, " +
		   		" (SELECT I.NAME   FROM INDEXS I  WHERE I.CODE = D.DFZFLX  AND I.TYPE = 'dfzflx') AS DFZFLX," +
		   		"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = ZD.FKZQ AND I.TYPE = 'FKZQ') AS FKZQ, TO_CHAR(MAX(E.PAYDATETIME),'yyyy-mm-dd') AS PAYDATETIME,TO_CHAR(MAX(A.ENDMONTH),'yyyy-mm') AS ENDMONTH,COUNT(E.ELECTRICFEE_ID)AS PAYCOUNT  " +
		   		"FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A, DIANFEIVIEW E, ZDDFINFO ZD WHERE Z.ID = D.ZDID " +
		   		" AND Z.ID = ZD.ZDID(+) AND D.DBYT = 'dbyt01'  "+whereStr+str+"  AND D.DBID = A.AMMETERID_FK(+) AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK(+) " +
		   		"AND Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"') GROUP BY Z.ZDCODE,Z.JZNAME,Z.SHI,Z.XIAN,Z.XIAOQU,STATIONTYPE,DFZFLX, FKZQ)";
		   }else{
			   if(payment.equals("0")){
				   sql="select count(*) from(SELECT DISTINCT Z.ZDCODE,Z.JZNAME," +
			   		"(CASE WHEN Z.SHI IS NOT NULL THEN (SELECT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI)ELSE '' END)" +
			   		" || (CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN) ELSE '' END) " +
			   		"|| (CASE WHEN Z.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU) ELSE ''  END) AS SZDQ," +
			   		" (SELECT I.NAME  FROM INDEXS I WHERE I.CODE = Z.STATIONTYPE AND I.TYPE = 'stationtype') AS STATIONTYPE, " +
			   		" (SELECT I.NAME   FROM INDEXS I  WHERE I.CODE = D.DFZFLX  AND I.TYPE = 'dfzflx') AS DFZFLX," +
			   		"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = ZD.FKZQ AND I.TYPE = 'FKZQ') AS FKZQ, TO_CHAR(MAX(E.PAYDATETIME),'yyyy-mm-dd') AS PAYDATETIME,TO_CHAR(MAX(A.ENDMONTH),'yyyy-mm') AS ENDMONTH,COUNT(E.ELECTRICFEE_ID)AS PAYCOUNT  " +
			   		"FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A, DIANFEIVIEW E, ZDDFINFO ZD WHERE Z.ID = D.ZDID " +
			   		" AND Z.ID = ZD.ZDID(+) AND D.DBYT = 'dbyt01'  "+whereStr+"  AND D.DBID = A.AMMETERID_FK(+) AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK(+) " +
			   				" AND D.DBID NOT IN  (SELECT A.AMMETERID_FK  FROM DIANDUVIEW A, DIANFEIVIEW E  WHERE A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK "+str+")" +
			   		"AND Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"') GROUP BY Z.ZDCODE,Z.JZNAME,Z.SHI,Z.XIAN,Z.XIAOQU,STATIONTYPE,DFZFLX,FKZQ)";
			   }else{
				   sql="select count(*) from (SELECT DISTINCT Z.ZDCODE,Z.JZNAME," +
			   		"(CASE WHEN Z.SHI IS NOT NULL THEN (SELECT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI)ELSE '' END)" +
			   		" || (CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN) ELSE '' END) " +
			   		"|| (CASE WHEN Z.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU) ELSE ''  END) AS SZDQ," +
			   		" (SELECT I.NAME  FROM INDEXS I WHERE I.CODE = Z.STATIONTYPE AND I.TYPE = 'stationtype') AS STATIONTYPE, " +
			   		" (SELECT I.NAME   FROM INDEXS I  WHERE I.CODE = D.DFZFLX  AND I.TYPE = 'dfzflx') AS DFZFLX," +
			   		"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = ZD.FKZQ AND I.TYPE = 'FKZQ') AS FKZQ, TO_CHAR(MAX(E.PAYDATETIME),'yyyy-mm-dd') AS PAYDATETIME,TO_CHAR(MAX(A.ENDMONTH),'yyyy-mm') AS ENDMONTH,COUNT(E.ELECTRICFEE_ID)AS PAYCOUNT  " +
			   		"FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A, DIANFEIVIEW E, ZDDFINFO ZD WHERE Z.ID = D.ZDID " +
			   		" AND Z.ID = ZD.ZDID(+) AND D.DBYT = 'dbyt01'  "+whereStr+str+"  AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK " +
			   		"AND Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"') GROUP BY Z.ZDCODE,Z.JZNAME,Z.SHI,Z.XIAN,Z.XIAOQU,STATIONTYPE,DFZFLX, FKZQ)";
			   }
		   }
		 
		   
		   System.out.println("--"+sql.toString());
		   DataBase db=new DataBase();
			ResultSet rs=null;
		try {
			db.connectDb();
			rs=db.queryAll(sql);
			try {
				while(rs.next()){
					count=rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DbException de) {
			// TODO Auto-generated catch block
			de.printStackTrace();
		}finally {
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
		return count;		
	}
	
	
	 //站点缴费情况统计详细电费信息
	public ArrayList getlist(String whereStr){
		   ArrayList list=new ArrayList();
		   String sql="SELECT * FROM (SELECT A.*, ROWNUM RN FROM (SELECT d.dbid, to_char(a.lastdatetime,'yyyy-mm-dd') lastdatetime,to_char(a.thisdatetime,'yyyy-mm-dd') thisdatetime,a.lastdegree,a.thisdegree,a.floatdegree,a.blhdl,to_char(a.startmonth,'yyyy-mm') startmonth,to_char(a.endmonth,'yyyy-mm') endmonth,e.floatpay,e.actualpay,to_char(e.accountmonth,'yyyy-mm') accountmonth FROM ZHANDIAN Z, DIANBIAO D, DIANDUVIEW A, DIANFEIVIEW E WHERE Z.ID = D.Zdid AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK "+whereStr+"  ORDER  BY e.electricfee_id DESC) A    WHERE ROWNUM < 7) WHERE RN >= 1";
		   System.out.println("--"+sql.toString());
		   DataBase db=new DataBase();
		   Connection conn = null;
		   Statement sta = null;
		   ResultSet rs=null;
		   try {
			db.connectDb();
			conn = db.getConnection();
			System.out.println("站点缴费情况统计详细电费信息 :"+sql.toString());
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());
			Query query=new Query();
			list=query.query(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
        	db.free(rs, sta, conn);
        }
		   
		   return list;
	   }
}
