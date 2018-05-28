package com.noki.electricfees.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

public class WentDutchStatisticsBean {
	
       //地市电费分摊汇总
	public ArrayList getStatistics(String loginId,String str){
		
		ArrayList list=new ArrayList();
		String sql="SELECT COUNT(Z.ID) AS SUMJZ, Z.SHI AS SSS,(SELECT A.AGNAME FROM D_AREA_GRADE A WHERE A.AGCODE = Z.SHI) AS SHI,SUM(E.ACTUALPAY)AS ACTUALPAY ,SUM(E.NETWORKDF)AS NETWORKDF,SUM(E.INFORMATIZATIONDF)AS INFORMATIZATIONDF,SUM(E.ADMINISTRATIVEDF)AS ADMINISTRATIVEDF,SUM(E.MARKETDF)AS MARKETDF,SUM(E.BUILDDF)AS BUILDDF " +
				"FROM ZHANDIAN Z, DIANBIAO D, ELECTRICVIEW E WHERE Z.ID = D.ZDID  AND D.DBID = E.DBID " +
				" AND e.manualauditstatus='2' "+str+" AND Z.XIAOQU IN (SELECT P.AGCODE FROM PER_AREA P WHERE P.ACCOUNTID = '"+loginId+"') GROUP BY Z.SHI";
		DataBase db=new DataBase();
		ResultSet rs=null;
		try {
			db.connectDb();
			rs=db.queryAll(sql);
			Query query=new Query();
			list=query.query(rs);
			System.out.println("地市电费分摊汇总-"+sql.toString());
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
public ArrayList getStatistics3(String loginId,String str){
		
		ArrayList list=new ArrayList();
		String sql="SELECT COUNT(Z.ID) AS SUMJZ,(select a.agcode from D_AREA_GRADE A where  a.agcode=z.xian) AS codedd, Z.xian AS SSS,(SELECT A.AGNAME FROM D_AREA_GRADE A WHERE A.AGCODE = Z.xian) AS xian,SUM(E.ACTUALPAY)AS ACTUALPAY ,SUM(E.NETWORKDF)AS NETWORKDF,SUM(E.INFORMATIZATIONDF)AS INFORMATIZATIONDF,SUM(E.ADMINISTRATIVEDF)AS ADMINISTRATIVEDF,SUM(E.MARKETDF)AS MARKETDF,SUM(E.BUILDDF)AS BUILDDF " +
				"FROM ZHANDIAN Z, DIANBIAO D, ELECTRICVIEW E WHERE Z.ID = D.ZDID  AND D.DBID = E.DBID " +
				" AND e.manualauditstatus='2' "+str+" AND Z.XIAOQU IN (SELECT P.AGCODE FROM PER_AREA P WHERE P.ACCOUNTID = '"+loginId+"') GROUP BY Z.xian";
		DataBase db=new DataBase();
		ResultSet rs=null;
		try {
			db.connectDb();
			rs=db.queryAll(sql);
			Query query=new Query();
			list=query.query(rs);
			System.out.println("地市电费分摊汇总-"+sql.toString());
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
public ArrayList getStatistics1(String loginId,String str){
		
		ArrayList list=new ArrayList();
		String sql="SELECT Z.JZNAME,E.STARTMONTH,E.ENDMONTH,E.ACTUALPAY,E.NETWORKDF,E.ADMINISTRATIVEDF,E.BUILDDF,E.INFORMATIZATIONDF,E.MARKETDF "+
				"FROM ZHANDIAN Z, DIANBIAO D, ELECTRICVIEW E WHERE Z.ID = D.ZDID  AND D.DBID = E.DBID " +
				" AND e.manualauditstatus='2' "+str+" AND Z.XIAOQU IN (SELECT P.AGCODE FROM PER_AREA P WHERE P.ACCOUNTID = '"+loginId+"')";
		DataBase db=new DataBase();
		ResultSet rs=null;
		System.out.println("地市电费分摊汇总-"+sql.toString());
		try {
			db.connectDb();
			rs=db.queryAll(sql);
			Query query=new Query();
			list=query.query(rs);
			
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
