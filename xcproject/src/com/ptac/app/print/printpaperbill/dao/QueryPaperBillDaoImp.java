package com.ptac.app.print.printpaperbill.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.print.printpaperbill.bean.QueryPaperBill;

public class QueryPaperBillDaoImp implements QueryPaperBillDao{

	/*
	 * 打印纸质单据 查询
	 */
	public List<QueryPaperBill> queryData(String whereStr,String Str, String loginId) {
		List<QueryPaperBill> list = new ArrayList<QueryPaperBill>();
		String sql1 = "";
		String sql2 = "";
		ResultSet rs = null;
		ResultSet rs1 = null;
		DataBase db = new DataBase();
		try {
			//电费单
			sql1="SELECT ZD.JZNAME,DY.DFUUID,(CASE WHEN ZD.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE''END) " +
    		"||(CASE WHEN ZD.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ," +
    		"(SELECT I.NAME FROM ZHANDIAN Z,INDEXS I WHERE Z.ID=ZD.ID AND Z.STATIONTYPE=I.CODE  AND I.TYPE='stationtype')AS JZTYPE, " +
    		"TO_CHAR(DY.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(DY.THISDATETIME,'yyyy-mm-dd') THISDATETIME,TO_CHAR(DY.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,DY.LASTDEGREE,DY.THISDEGREE,DY.BLHDL,DY.FLOATDEGREE,DY.ELECTRICFEE_ID," +
    		"DY.UNITPRICE,DY.FLOATPAY,DY.ACTUALPAY,TO_CHAR(DY.STARTMONTH,'yyy-mm') STARTMONTH,TO_CHAR(DY.ENDMONTH,'yyyy-mm') ENDMONTH,(SELECT INS.NAME  FROM INDEXS INS WHERE AM.DFZFLX = INS.CODE  AND AM.ZDID = ZD.ID  AND INS.TYPE = 'dfzflx') AS FFFS," +
    		"DY.MEMO,(SELECT I.NAME FROM INDEXS I WHERE I.CODE=DY.NOTETYPEID AND I.TYPE='PJLX'),DY.PJJE,DY.Manualauditstatus, " +
    		" DY.LASTELECFEES, DY.LASTELECDEGREE, DY.LASTUNITPRICE "+
    		" FROM ZHANDIAN ZD, DIANBIAO AM, eleprint_view DY WHERE AM.DBQYZT='1' AND ZD.ID = AM.ZDID AND AM.DBID = DY.AMMETERID_FK and ZD.QYZT='1' and DY.CITYZRAUDITSTATUS='1' " +
    		" AND AM.DBQYZT='1' AND DY.CITYAUDIT='1' AND (AM.DFZFLX ='dfzflx01' OR AM.DFZFLX ='dfzflx03') "+whereStr+"  " +
    		"AND (ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))  ORDER BY ZD.JZNAME,ZD.XIAOQU,AM.DFZFLX" ;
            //预付费
            sql2="SELECT ZD.JZNAME,DY.UUID AS DFUUID,(CASE WHEN ZD.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE''END) " +
    		"||(CASE WHEN ZD.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ," +
    		"(SELECT I.NAME FROM ZHANDIAN Z,INDEXS I WHERE Z.ID=ZD.ID AND Z.STATIONTYPE=I.CODE  AND I.TYPE='stationtype')AS JZTYPE, " +
    		"TO_CHAR(DY.STARTDATE,'yyyy-mm-dd') AS LASTDATETIME,TO_CHAR(DY.ENDDATE,'yyyy-mm-dd') AS THISDATETIME,TO_CHAR(DY.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,DY.STARTDEGREE AS LASTDEGREE,DY.STOPDEGREE AS THISDEGREE,DY.DIANLIANG AS BLHDL,DY.ID AS ELECTRICFEE_ID," +
    		"DY.DANJIA AS UNITPRICE,DY.MONEY AS ACTUALPAY,TO_CHAR(DY.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(DY.ENDMONTH,'yyyy-mm') ENDMONTH,(SELECT INS.NAME  FROM INDEXS INS WHERE AM.DFZFLX = INS.CODE  AND AM.ZDID = ZD.ID  AND INS.TYPE = 'dfzflx') AS FFFS," +
    		"DY.MEMO,(SELECT I.NAME FROM INDEXS I WHERE I.CODE=DY.NOTETYPEID AND I.TYPE='PJLX'),DY.PJJE,DY.financeaudit" +
    		" FROM ZHANDIAN ZD, DIANBIAO AM, eleprint_yff DY WHERE AM.DBQYZT='1' AND ZD.ID = AM.ZDID AND AM.DBID = DY.PREPAYMENT_AMMETERID and ZD.QYZT='1' " +
    		" AND DY.CITYAUDIT='1' AND (AM.DFZFLX='dfzflx02' OR AM.DFZFLX='dfzflx04') and DY.CITYZRAUDITSTATUS='1'  "+Str+"  " +
    		"AND (ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))  ORDER BY ZD.JZNAME,ZD.XIAOQU,AM.DFZFLX" ;
            System.out.println("电费单打印查询和导出："+sql1.toString());
            db.connectDb();			
			rs = db.queryAll(sql1);
            
			//Query query = new Query();
			//list = query.query(rs);
			while(rs.next()){
				QueryPaperBill formbean=new QueryPaperBill();
				formbean.setJzname(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setDfuuid(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setSzdq(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setJztype(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setLastdatetime(rs.getString(5)!=null?rs.getString(5):"");
				formbean.setThisdatetime(rs.getString(6)!=null?rs.getString(6):"");
				formbean.setAccountmonth(rs.getString(7)!=null?rs.getString(7):"");
				formbean.setLastdegree(rs.getString(8)!=null?rs.getString(8):"");
				formbean.setThisdegree(rs.getString(9)!=null?rs.getString(9):"");
				formbean.setBlhdl(rs.getString(10)!=null?rs.getString(10):"");
				formbean.setFloatdegree(rs.getString(11)!=null?rs.getString(11):"");
				formbean.setElectricfeeId(rs.getString(12)!=null?rs.getString(12):"");
				formbean.setUnitprice(rs.getString(13)!=null?rs.getString(13):"");
				formbean.setFloatpay(rs.getString(14)!=null?rs.getString(14):"");
				formbean.setActualpay(rs.getString(15)!=null?rs.getString(15):"");
				formbean.setStartmonth(rs.getString(16)!=null?rs.getString(16):"");
				formbean.setEndmonth(rs.getString(17)!=null?rs.getString(17):"");
				formbean.setDfzflx(rs.getString(18)!=null?rs.getString(18):"");
				formbean.setMemo(rs.getString(19)!=null?rs.getString(19):"");
				formbean.setNotetypeid(rs.getString(20)!=null?rs.getString(20):"");
				formbean.setPjjedy(rs.getString(21)!=null?rs.getString(21):"");
				formbean.setManualauditstatus(rs.getString(22)!=null?rs.getString(22):"");
				formbean.setLastelecfees(rs.getString("LASTELECFEES")!=null?rs.getString("LASTELECFEES"):""); //上期电费
				formbean.setLastelecdegree(rs.getString("LASTELECDEGREE")!=null?rs.getString("LASTELECDEGREE"):""); //上期电量
				formbean.setLastunitprice(rs.getString("LASTUNITPRICE")!=null?rs.getString("LASTUNITPRICE"):""); //上期单价

				list.add(formbean);
			}
			  System.out.println("预付费打印查询："+sql2.toString());
			rs1 = db.queryAll(sql2);
          
			while(rs1.next()){
				QueryPaperBill formbean=new QueryPaperBill();
				formbean.setJzname(rs1.getString(1)!=null?rs1.getString(1):"");
				formbean.setDfuuid(rs1.getString(2)!=null?rs1.getString(2):"");
				formbean.setSzdq(rs1.getString(3)!=null?rs1.getString(3):"");
				formbean.setJztype(rs1.getString(4)!=null?rs1.getString(4):"");
				formbean.setLastdatetime(rs1.getString(5)!=null?rs1.getString(5):"");
				formbean.setThisdatetime(rs1.getString(6)!=null?rs1.getString(6):"");
				formbean.setAccountmonth(rs1.getString(7)!=null?rs1.getString(7):"");
				formbean.setLastdegree(rs1.getString(8)!=null?rs1.getString(8):"");
				formbean.setThisdegree(rs1.getString(9)!=null?rs1.getString(9):"");
				formbean.setBlhdl(rs1.getString(10)!=null?rs1.getString(10):"");
				formbean.setFloatdegree("");
				formbean.setElectricfeeId(rs1.getString(11)!=null?rs1.getString(11):"");
				formbean.setUnitprice(rs1.getString(12)!=null?rs1.getString(12):"");
				formbean.setFloatpay("");
				formbean.setActualpay(rs1.getString(13)!=null?rs1.getString(13):"");
				formbean.setStartmonth(rs1.getString(14)!=null?rs1.getString(14):"");
				formbean.setEndmonth(rs1.getString(15)!=null?rs1.getString(15):"");
				formbean.setDfzflx(rs1.getString(16)!=null?rs1.getString(16):"");
				formbean.setMemo(rs1.getString(17)!=null?rs1.getString(17):"");
				formbean.setNotetypeid(rs1.getString(18)!=null?rs1.getString(18):"");
				formbean.setPjjedy(rs1.getString(19)!=null?rs1.getString(19):"");
				formbean.setManualauditstatus(rs1.getString(20)!=null?rs1.getString(20):"");
				list.add(formbean);
			}            
		}catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException see) {
					see.printStackTrace();
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
