package com.noki.function;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class zanguservlet {
	// 负责站点条件
	private String getFuzeZdid(DataBase db, String loginid) throws DbException,
			SQLException {
		ResultSet rs = null;

		rs = db
				.queryAll("select begincode,endcode from per_zhandian where accountid='"
						+ loginid
						+ "' and begincode is not null and endcode is not null");
		String cxtj = new String("");
		while (rs.next()) {
			cxtj = cxtj + " or (zdcode>='" + rs.getString(1)
					+ "' and zdcode <='" + rs.getString(2) + "')";
		}
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
		return cxtj.toString();
	}
	
//暂估查询语句
/**
 * 	update zhouxue 2014.8.1 sql语句加默认提交    站点类型是‘增值税站点’的站点不进行暂估 ;
 * 	update zhouxue 2014.8.1 传值变量添加 shi  用来做判断  济南做试点 外租回收的站点 进行暂估
 * 暂估的电表：月结，预支，合同； 插卡的没有进行暂估 ，合同的没有判断外租回收站点不进行暂估（第二个SQL语句）
 */
	public synchronized ArrayList<CityQueryBean>  getPageData(String whereStr,
			String loginId,String Str,String Wstr,String Wdfshi,String Wyfshi,String shi) {		
		ArrayList<CityQueryBean> list = new ArrayList<CityQueryBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "",sql1="";
		DataBase db = new DataBase();
        ResultSet rs = null;
        ResultSet rs1 = null;
		try {
	   //地市的外租回收站点不进行暂估
	
			sql="SELECT DISTINCT ZD.JZNAME,DB.DANJIA,TO_CHAR(DB.CSSYTIME,'yyyy-mm-dd') CSSYTIME,TO_CHAR(AMM.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(AMM.THISDATETIME,'yyyy-mm-dd') THISDATETIME,AMM.ACTUALPAY, "+
					"(SELECT T.NAME FROM INDEXS T  WHERE T.CODE = ZD.STATIONTYPE  AND T.TYPE = 'stationtype') STATIONTYPE,"+
					" (SELECT T.NAME  FROM INDEXS T  WHERE T.CODE = DB.DFZFLX  AND T.TYPE = 'dfzflx') DFZFLX," +
					"(CASE WHEN ZD.SHI IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) ELSE  '' END)||"+
					"(CASE WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE  '' END) "+
					"|| (CASE  WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE   WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ,"+
					"  ZD.EDHDL,ZD.ID,DB.DBID, DB.DBNAME,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = ZD.PROPERTY AND T.TYPE = 'ZDSX') AS PROPERTY,ZD.SCB " +
					"  FROM (SELECT DISTINCT DD.AMMETERID_FK, DD.LASTDATETIME,DD.THISDATETIME, DF.ACTUALPAY, DD.MANUALAUDITSTATUS, B.DATETIME"+
					" FROM DIANDUVIEW DD,DIANFEIVIEW DF, (SELECT MAX(AMM.THISDATETIME)  AS DATETIME, AMM.AMMETERID_FK   FROM DIANDUVIEW AMM,DIANFEIVIEW DF"+
					" WHERE 1=1 "+Wdfshi+"  AND AMM.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK   GROUP BY AMM.AMMETERID_FK) B"+
					" WHERE DD.AMMETERID_FK = B.AMMETERID_FK  AND DD.THISDATETIME = B.DATETIME   AND DD.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK  "+Wdfshi+" "+Str+") AMM, DIANBIAO DB,  ZHANDIAN ZD"+
					" WHERE DB.DBID = AMM.AMMETERID_FK(+) AND ZD.ID = DB.ZDID AND DB.DBYT = 'dbyt01' and db.DBQYZT='1' " +
					"AND (DB.DFZFLX='dfzflx01' OR DB.DFZFLX='dfzflx03') AND ZD.QYZT = '1'  AND ZD.SHSIGN = '1'  AND (zd.property<>'zdsx04' AND zd.jzname NOT LIKE '%回收%') AND ZD.STATIONTYPE<>'StationType41' "+whereStr+"   " +
					"AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))";//ORDER BY  JZNAME
            
				System.out.println("暂估查询或导出1："+sql.toString());
            sql1="SELECT DISTINCT ZD.JZNAME,DB.DANJIA,TO_CHAR(DB.CSSYTIME,'yyyy-mm-dd') CSSYTIME,TO_CHAR(AMM.STARTMONTH,'yyyy-mm')||'-01',TO_CHAR(AMM.ENDMONTH,'yyyy-mm') ENDMONTH,AMM.MONEY,"+
            "(SELECT T.NAME FROM INDEXS T  WHERE T.CODE = ZD.STATIONTYPE  AND T.TYPE = 'stationtype') STATIONTYPE,"+
            " (SELECT T.NAME  FROM INDEXS T  WHERE T.CODE = DB.DFZFLX  AND T.TYPE = 'dfzflx') DFZFLX,"+
            "(CASE WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE  '' END) "+
            "|| (CASE  WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE   WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ,"+
            "  ZD.EDHDL,ZD.ID,DB.DBID,DB.DBNAME,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = ZD.PROPERTY AND T.TYPE = 'ZDSX') AS PROPERTY ,ZD.SCB" +
            " FROM (SELECT DISTINCT YY.PREPAYMENT_AMMETERID,YY.STARTMONTH,YY.ENDMONTH,YY.MONEY FROM YUFUFEIVIEW YY, " +
            "(SELECT MAX(YY.ENDMONTH) AS DATETIME, YY.PREPAYMENT_AMMETERID FROM YUFUFEIVIEW YY  WHERE 1=1 "+Wyfshi+"  GROUP BY YY.PREPAYMENT_AMMETERID) B " +
            "WHERE YY.PREPAYMENT_AMMETERID = B.PREPAYMENT_AMMETERID AND YY.ENDMONTH = B.DATETIME  "+Wyfshi+" "+Wstr+") AMM, DIANBIAO DB,  ZHANDIAN ZD"+
             " WHERE DB.DBID = AMM.PREPAYMENT_AMMETERID(+) AND ZD.ID = DB.ZDID AND DB.DBYT = 'dbyt01' and db.DBQYZT='1' AND DB.DFZFLX='dfzflx02' AND ZD.QYZT = '1'  AND ZD.SHSIGN = '1' AND ZD.STATIONTYPE<>'StationType41' "+whereStr+"  " +
             " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))"; //ORDER BY  JZNAME
            System.out.println("暂估查询或导出2："+sql1.toString());
            
            db.connectDb();	
			
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				CityQueryBean formbean=new CityQueryBean();
				formbean.setJzname(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setDianfei(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setCssytime(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setStarttime(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setEndtime(rs.getString(5)!=null?rs.getString(5):"");
				formbean.setActualpay(rs.getString(6)!=null?rs.getString(6):"");
				formbean.setStationtype(rs.getString(7)!=null?rs.getString(7):"");
				formbean.setDfzflx(rs.getString(8)!=null?rs.getString(8):"");
				formbean.setAddress(rs.getString(9)!=null?rs.getString(9):"");
				formbean.setEdhdl(rs.getString(10)!=null?rs.getString(10):"");
				formbean.setZdid(rs.getString(11)!=null?rs.getString(11):"");
				formbean.setDbid(rs.getString(12)!=null?rs.getString(12):"");
				formbean.setDbname(rs.getString(13)!=null?rs.getString(13):"");
				formbean.setProperty(rs.getString(14)!=null?rs.getString(14):"");
				formbean.setQsdbdl(rs.getString(15)!=null?rs.getString(15):"");
				//formbean.setTianshu(rs.getString(11)!=null?rs.getString(11):"");
				list.add(formbean);
				}
			
			rs1 = db.queryAll(sql1.toString());
			
			while(rs1.next()){
				CityQueryBean formbean=new CityQueryBean();
				formbean.setJzname(rs1.getString(1)!=null?rs1.getString(1):"");
				formbean.setDianfei(rs1.getString(2)!=null?rs1.getString(2):"");
				formbean.setCssytime(rs1.getString(3)!=null?rs1.getString(3):"");
				formbean.setStarttime(rs1.getString(4)!=null?rs1.getString(4):"");
				formbean.setEndtime(rs1.getString(5)!=null?rs1.getString(5):"");
				formbean.setActualpay(rs1.getString(6)!=null?rs1.getString(6):"");
				formbean.setStationtype(rs1.getString(7)!=null?rs1.getString(7):"");
				formbean.setDfzflx(rs1.getString(8)!=null?rs1.getString(8):"");
				formbean.setAddress(rs1.getString(9)!=null?rs1.getString(9):"");
				formbean.setEdhdl(rs1.getString(10)!=null?rs1.getString(10):"");
				formbean.setZdid(rs1.getString(11)!=null?rs1.getString(11):"");
				formbean.setDbid(rs1.getString(12)!=null?rs1.getString(12):"");
				formbean.setDbname(rs1.getString(13)!=null?rs1.getString(13):"");
				formbean.setProperty(rs1.getString(14)!=null?rs1.getString(14):"");
				formbean.setQsdbdl(rs1.getString(15)!=null?rs1.getString(15):"");
				//formbean.setTianshu(rs1.getString(11)!=null?rs1.getString(11):"");
				list.add(formbean);
				}
			
			
		}catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
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
		//System.out.println(list.toString() + "------------------");
		return list;
	}
	
	//暂估静态数据查询
	public synchronized ArrayList<CityQueryBean>  getPageDataZgjt(String whereStr,
			String loginId,String Str) {		
		ArrayList<CityQueryBean> list = new ArrayList<CityQueryBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		DataBase db = new DataBase();
        ResultSet rs = null;
        
		try {
			db.connectDb();
			sql="SELECT G.JZNAME,G.ADDRESS,G.STATIONTYPE,G.DFZFLX,G.ZANGUSTARTMONTH,G.ZANGUENDMONTH,G.TIANSHU,G.DANJIA," +
					"G.ZANGUMONEY,G.ZDID,G.ENTRYPENSONNEL FROM ZHANDIAN Z, ZANGU G WHERE Z.ID = G.ZDID " +
					"  "+whereStr+" AND ((Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')))";
		
        		
			System.out.println("暂估静态数据查询"+sql);
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				CityQueryBean formbean=new CityQueryBean();
				formbean.setJzname(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setAddress(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setStationtype(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setDfzflx(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setZangustartmonth(rs.getString(5)!=null?rs.getString(5):"");
				formbean.setZanguendmonth(rs.getString(6)!=null?rs.getString(6):"");
				formbean.setTianshu(rs.getString(7)!=null?rs.getString(7):"");
				formbean.setDianfei(rs.getString(8)!=null?rs.getString(8):"");
				formbean.setZangumoney(rs.getString(9)!=null?rs.getString(9):"");
				formbean.setZdid(rs.getString(10)!=null?rs.getString(10):"");
				formbean.setLrren(rs.getString(11)!=null?rs.getString(11):"");
				
				list.add(formbean);
				}

			
		}catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
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
		//System.out.println(list.toString() + "------------------");
		return list;
	}
	

}
