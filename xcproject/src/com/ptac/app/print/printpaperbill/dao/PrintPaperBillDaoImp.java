package com.ptac.app.print.printpaperbill.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.print.printpaperbill.bean.PrintPaperBill;

public class PrintPaperBillDaoImp implements PrintPaperBillDao{

	
	//电费单批量打印数据
		public List<List<PrintPaperBill>> getPageDataCaiwu(String whereStr,String dystr) {
			
			List<List<PrintPaperBill>> list = new ArrayList<List<PrintPaperBill>>();
			String sql = "";
			String name="";
			DataBase db = new DataBase();
			 sql="select zd.jzname," +
	 		" (case when zd.xian is not null then(select distinct agname from d_area_grade where agcode = zd.xian) else''end) " +
	 		"|| (case when zd.xiaoqu is not null then(select distinct agname from d_area_grade where agcode = zd.xiaoqu) else '' end) as szdq," +
	 		"(select i.name from zhandian z,indexs i where z.id=zd.id and z.STATIONTYPE=i.code  and i.type='stationtype')as jztype, " +
	 		"TO_CHAR(DY.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(DY.ENDMONTH,'yyyy-mm') ENDMONTH,TO_CHAR(DY.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(DY.THISDATETIME,'yyyy-mm-dd') THISDATETIME,TO_CHAR(DY.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,DY.LASTDEGREE,DY.THISDEGREE,DY.blhdl,DY.FLOATDEGREE,DY.ELECTRICFEE_ID," +
	 		"DY.UNITPRICE,DY.FLOATPAY,DY.actualpay,(SELECT INS.NAME  FROM INDEXS INS WHERE AM.DFZFLX = INS.CODE  AND AM.ZDID = ZD.ID  AND INS.TYPE = 'dfzflx') AS FFFS,am.beilv,dy.memo," +
	 		"(select i.name  from  indexs i where i.code=dy.notetypeid and i.type='PJLX'),DY.PJJE, " +
			" DY.LASTELECFEES, DY.LASTELECDEGREE, DY.LASTUNITPRICE "+
	 		" FROM ZHANDIAN ZD, DIANBIAO AM, eleprintdayin_view DY WHERE ZD.ID = AM.ZDID AND AM.DBID = DY.AMMETERID_FK" +
	 		" "+dystr+" and dy.DFUUID in ("+whereStr+")  ORDER BY JZTYPE,ZD.JZNAME ";
	 		
			
			System.out.println("电费单打印："+sql.toString());

			ResultSet rs = null;
			try {
				db.connectDb();
				
				rs = db.queryAll(sql);
				Integer number=0;
				while(rs.next()){
					if(!rs.getString(3).equals(name)){
						name=rs.getString(3);
						List<PrintPaperBill> dfdlist=new ArrayList<PrintPaperBill>();
						list.add(dfdlist);
						number++;
					}
					PrintPaperBill dfdbean=new PrintPaperBill();
					dfdbean.setJzname(rs.getString(1));
					dfdbean.setSzdq(rs.getString(2));
					dfdbean.setJztype(rs.getString(3));
					dfdbean.setStatmonth(rs.getString(4));
					dfdbean.setEndmonth(rs.getString(5));
					dfdbean.setLastdatetime(rs.getString(6));
					dfdbean.setThisdatetime(rs.getString(7));
					dfdbean.setAccountmonth(rs.getString(8));
					dfdbean.setLastdegree(rs.getString(9));
					dfdbean.setThisdegree(rs.getString(10));
					dfdbean.setActualdegree(rs.getString(11));
					dfdbean.setFloatdegree(rs.getString(12));
					dfdbean.setElectricfee_id(rs.getString(13));
					dfdbean.setUnitprice(rs.getString(14));
					dfdbean.setFloatpay(rs.getString(15));
					dfdbean.setActualpay(rs.getString(16));
					dfdbean.setFffs(rs.getString(17));
					dfdbean.setBeilv(rs.getString(18));
					dfdbean.setMemo(rs.getString(19));
					dfdbean.setNotetypeid(rs.getString(20));
					dfdbean.setPjje(rs.getString(21));
					dfdbean.setLastelecfees(rs.getString("LASTELECFEES")!=null?rs.getString("LASTELECFEES"):""); //上期电费
					dfdbean.setLastelecdegree(rs.getString("LASTELECDEGREE")!=null?rs.getString("LASTELECDEGREE"):""); //上期电量
					dfdbean.setLastunitprice(rs.getString("LASTUNITPRICE")!=null?rs.getString("LASTUNITPRICE"):""); //上期单价

					list.get(number-1).add(dfdbean);
				}
				db.close();
			}

			catch (Exception de) {
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
		
		
		public List<PrintPaperBill> getFentan(String whereStr) {
			List<PrintPaperBill> list = new ArrayList<PrintPaperBill>();
			String sql = "";
			DataBase db = new DataBase();
			sql="SELECT  (SELECT da.agname FROM d_area_grade da WHERE da.agcode=z.shi)AS shi, " +
					"(SELECT da.agname FROM d_area_grade da WHERE da.agcode=z.xian)AS xian," +
					"SUM(E.NETWORKDF * S.XJBILI / 100) E,SUM(E.INFORMATIZATIONDF * S.XJBILI / 100) A," +
					"SUM(E.ADMINISTRATIVEDF * S.XJBILI / 100) B,SUM(E.MARKETDF * S.XJBILI / 100) C, " +
					"SUM(E.BUILDDF * S.XJBILI / 100) D, (SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE S.QCBCODE = QC.QCBCODE) AS QCB, " +
					"(SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE QC.QCBCODE = S.KJKMCODE) AS KJKM, " +
					"(SELECT NAME FROM INDEXS I WHERE I.CODE = S.ZYMXCODE AND I.TYPE = 'ZYMX') AS ZYMX, " +
					" S.SHUOSHUZHUANYE,SUM(E.DDDf * S.XJBILI / 100) F " +
					"FROM ZHANDIAN Z, DIANBIAO D, SBGL S, caiwudayin_view E WHERE Z.ID = D.ZDID AND D.DBID = S.DIANBIAOID" +
					" AND D.DBID = E.AMMETERID_FK AND d.dfzflx<>'dfzflx02' AND e.dfuuid IN("+whereStr+") " +
					"GROUP BY S.SHUOSHUZHUANYE, S.QCBCODE, S.KJKMCODE, S.ZYMXCODE,z.shi,z.xian";
			System.out.println("电费单打印分摊费用："+sql.toString());
			ResultSet rs = null;
			try {
				db.connectDb();
				rs = db.queryAll(sql);
				while(rs.next()){
					PrintPaperBill dfdbean=new PrintPaperBill();
					dfdbean.setShi(rs.getString(1));
					dfdbean.setXian(rs.getString(2));
					dfdbean.setNETWORKDF(rs.getString(3));
					dfdbean.setINFORMATIZATIONDF(rs.getString(4));
					dfdbean.setADMINISTRATIVEDF(rs.getString(5));
					dfdbean.setMARKETDF(rs.getString(6));
					dfdbean.setBUILDDF(rs.getString(7));
					dfdbean.setQcb(rs.getString(8));
					dfdbean.setKjkm(rs.getString(9));
					dfdbean.setZymx(rs.getString(10));
					dfdbean.setSszy(rs.getString(11));
					dfdbean.setDddf(rs.getString(12));
					list.add(dfdbean);
				}
			}
			catch (Exception de) {
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
		
	
	//流程号
	  public String getMaxlch(String shi){
		  int lch=0;
		  String sql="select liuchenghao from liuchenghao where shi= '"+shi+"'";

		  DataBase db = new DataBase();
		  ResultSet rs = null;
		  try {
			rs=db.queryAll(sql);
			while(rs.next()){
				lch=rs.getInt(1)+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		  
		  return Integer.toString(lch);
	  }
	  
}
