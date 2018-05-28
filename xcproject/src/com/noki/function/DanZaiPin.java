package com.noki.function;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.DianfeidandayinBean;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.util.CTime;

public class DanZaiPin {
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
	
//单载频能耗分析查询
	public synchronized List<CityQueryBean>  getDanzaipin(String whereStr,
			String loginId) {		
		List<CityQueryBean>  list = new ArrayList<CityQueryBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
        ResultSet rs = null;
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			sql = " SELECT (CASE WHEN Z.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN) ELSE '' END)AS XIAN," +
					"(CASE WHEN Z.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU) ELSE '' END)AS XIAOQU," +
					"(SELECT NAME FROM INDEXS WHERE CODE = Z.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE," +
					"SUM(Z.ZPSL),COUNT(Z.ID),SUM(A.ACTUALDEGREE)AS DLCOUNT,SUM(E.ACTUALPAY)AS DFCOUNT " +
					" FROM ZHANDIAN Z,DIANBIAO D,AMMETERDEGREES A,ELECTRICFEES E" +
					" WHERE Z.ID=D.ZDID AND D.DBID=A.AMMETERID_FK AND A.AMMETERDEGREEID=E.AMMETERDEGREEID_FK " +
					" AND Z.QYZT='1' AND D.DBQYZT='1' AND E.MANUALAUDITSTATUS>='1' "+whereStr+"" +
					" AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "')) " +
					" GROUP BY Z.XIAN,Z.XIAOQU,Z.STATIONTYPE ";
            System.out.println("单载频能耗分析查询："+sql.toString());
			db.connectDb();		
			rs = db.queryAll(sql.toString());
			//Query query = new Query();
			//list = query.query(rs);
			while(rs.next()){
				CityQueryBean formbean=new CityQueryBean();
				formbean.setXian(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setXiaoqu(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setZdtype(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setZaipins(rs.getString(4)!=null?rs.getString(4):"0");
				formbean.setZdcount(rs.getString(5)!=null?rs.getString(5):"0");
				formbean.setYdl(rs.getString(6)!=null?rs.getString(6):"0");
				formbean.setSfjine(rs.getString(7)!=null?rs.getString(7):"0");
				
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
	
	//摊销
	public synchronized List<CityQueryBean>  getTanXiao(String whereStr,String Str1,
			String loginId,String tr,String Str2,String Str3,String Str11,String Str22,String Str33) {		
		List<CityQueryBean>  list = new ArrayList<CityQueryBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        System.out.println("whereStr:"+whereStr);
        System.out.println("Str:"+Str1+Str2+Str3);
        System.out.println("tr:"+tr);
		try {
			fzzdstr = getFuzeZdid(db, loginId);
/*			sql = "SELECT Z.JZNAME,(CASE WHEN Z.SHI IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI)ELSE''END) || " +
					"(CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN)ELSE''END) || " +
					"(CASE WHEN Z.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU)ELSE''END) AS SZDQ," +
					"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = D.DFZFLX AND TYPE = 'dfzflx') AS DFZFLX," +
					"(case when aa.endmonth="+tr+" then max(p.money) else min(p.money) end)as txje," +
					"P.HTBH,Z.ID,D.DBID,P.ACCOUNTMONTH,aa.startmonth,aa.money,aa.endmonth " +
            		" FROM ZHANDIAN Z, DIANBIAO D,PREPAYMENT P," +
            		"(select y.endmonth,y.startmonth,y.money,d.dbid from yufufeiview y,dianbiao d " +
            		"where y.prepayment_ammeterid = d.dbid AND Y.FINANCEAUDIT = '2' " +
            		"AND Y.LIUCHENGHAO IS NOT NULL AND("+Str1+")aa" +
					" WHERE Z.ID = D.ZDID AND P.PREPAYMENT_AMMETERID = D.DBID and aa.dbid=d.dbid AND P.LIUCHENGHAO IS NOT NULL AND P.FINANCEAUDIT='2'" +
					" AND Z.QYZT = '1' AND Z.XUNI = '0' AND D.DBYT = 'dbyt01' AND D.DFZFLX = 'dfzflx02' "+whereStr+""+
					"  AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "')) " +
					" GROUP BY Z.JZNAME, D.DFZFLX  ,Z.SHI,Z.XIAN,P.HTBH,Z.XIAOQU,Z.ID,D.DBID,P.ACCOUNTMONTH,aa.startmonth,aa.money,aa.endmonth";
            System.out.println("摊销查询："+sql.toString());*/
         
            String sql1="";
            String sql2="";
            String sql3="";
            //  --第一种情况报账月份在起始结束月份中间或相等   
            sql1="SELECT Z.JZNAME,(CASE WHEN Z.SHI IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI)ELSE''END) || " +
					"(CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN)ELSE''END) || " +
					"(CASE WHEN Z.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU)ELSE''END) AS SZDQ," +
					"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = D.DFZFLX AND TYPE = 'dfzflx') AS DFZFLX," +
					"y.HTBH,Z.ID,D.DBID,TO_CHAR(y.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,to_char(y.startmonth,'yyyy-mm') startmonth,y.money,to_char(y.endmonth,'yyyy-mm') endmonth, " +
					"(select (case when to_char(y.endmonth,'yyyy-mm') ="+tr+" then max(to_number(p.money)) else min(to_number(p.money)) end) as txje " +//----------
					"from prepayment p  where  p.prepayment_ammeterid=d.dbid  and p.LIUCHENGHAO IS NOT NULL " +
					"AND p.FINANCEAUDIT = '2' "+Str1+"  ) " +//-------------------
					"FROM ZHANDIAN Z,DIANBIAO D,yufufeiview y WHERE Z.ID = D.ZDID AND y.PREPAYMENT_AMMETERID = D.DBID " +
					"and y.accountmonth >= y.startmonth  and y.accountmonth <= y.endmonth  "+Str11+"" +
					"AND y.LIUCHENGHAO IS NOT NULL AND y.FINANCEAUDIT = '2'" +
					"AND Z.QYZT = '1' AND Z.XUNI = '0' AND D.DBYT = 'dbyt01' AND D.DFZFLX = 'dfzflx02' "+whereStr+"" +//-----------
					"AND (Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "'))" ; 
            //---第二种情况  报账月份  大于起始结束月份
            sql2="SELECT Z.JZNAME,(CASE WHEN Z.SHI IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI)ELSE''END) || " +
					"(CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN)ELSE''END) || " +
					"(CASE WHEN Z.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU)ELSE''END) AS SZDQ," +
					"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = D.DFZFLX AND TYPE = 'dfzflx') AS DFZFLX," +
					"y.HTBH,Z.ID,D.DBID,to_char(y.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,to_char(y.startmonth,'yyyy-mm') startmonth,y.money,to_char(y.endmonth,'yyyy-mm') endmonth, " +
					"(select (case when to_char(y.endmonth,'yyyy-mm') ="+tr+" then max(to_number(p.money)) else min(to_number(p.money)) end) as txje " +//-----
					"from prepayment p  where  p.prepayment_ammeterid=d.dbid  and p.LIUCHENGHAO IS NOT NULL  AND p.FINANCEAUDIT = '2' " +
					"and p.accountmonth>p.endmonth  "+Str2+")" +//
					" FROM ZHANDIAN Z, DIANBIAO D,yufufeiview y WHERE Z.ID = D.ZDID AND y.PREPAYMENT_AMMETERID = D.DBID " +
					"and y.accountmonth>y.endmonth  "+Str22+" AND y.LIUCHENGHAO IS NOT NULL AND y.FINANCEAUDIT = '2' " +
					"AND Z.QYZT = '1' AND Z.XUNI = '0' AND D.DBYT = 'dbyt01' " +
					"AND D.DFZFLX = 'dfzflx02' "+whereStr+" " +//---------
					" AND (Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "'))";
          //---第三种情况     报账月份 小于 起始结束月份 
            sql3="SELECT Z.JZNAME,(CASE WHEN Z.SHI IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI)ELSE''END) || " +
					"(CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN)ELSE''END) || " +
					"(CASE WHEN Z.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU)ELSE''END) AS SZDQ," +
					"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = D.DFZFLX AND TYPE = 'dfzflx') AS DFZFLX," +
					"y.HTBH,Z.ID,D.DBID,to_char(y.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,to_char(y.startmonth,'yyyy-mm') startmonth,y.money,to_char(y.endmonth,'yyyy-mm') endmonth, " +
					"(select (case when to_char(y.endmonth,'yyyy-mm') ="+tr+" then max(to_number(p.money)) else min(to_number(p.money)) end) as txje " +//-----
					"from prepayment p  where  p.prepayment_ammeterid=d.dbid  and p.LIUCHENGHAO IS NOT NULL  AND p.FINANCEAUDIT = '2' " +
					"and p.accountmonth<p.startmonth  "+Str3+")  " +// 
					"FROM ZHANDIAN Z, DIANBIAO D,yufufeiview y WHERE Z.ID = D.ZDID AND y.PREPAYMENT_AMMETERID = D.DBID " +
					"and y.accountmonth<y.startmonth  "+Str33+" AND y.LIUCHENGHAO IS NOT NULL AND y.FINANCEAUDIT = '2' " +
					"AND Z.QYZT = '1' AND Z.XUNI = '0' AND D.DBYT = 'dbyt01' " +
					"AND D.DFZFLX = 'dfzflx02' "+whereStr+" " +//---------
					"AND (Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "'))";
            System.out.println("摊销查询情况一："+sql1.toString());
            System.out.println("摊销查询情况二："+sql2.toString());
            System.out.println("摊销查询情况三："+sql3.toString());
			db.connectDb();		
			rs1 = db.queryAll(sql1.toString());
			//Query query = new Query();
			//list = query.query(rs1);
			while(rs1.next()){
				CityQueryBean formbean=new CityQueryBean();
				formbean.setJzname(rs1.getString(1)!=null?rs1.getString(1):"");
				formbean.setAddress(rs1.getString(2)!=null?rs1.getString(2):"");
				formbean.setDfzflx(rs1.getString(3)!=null?rs1.getString(3):"");
				formbean.setHtbh(rs1.getString(4)!=null?rs1.getString(4):"");
				formbean.setZdid(rs1.getString(5)!=null?rs1.getString(5):"");
				formbean.setDbid(rs1.getString(6)!=null?rs1.getString(6):"");
				formbean.setBzyf(rs1.getString(7)!=null?rs1.getString(7):"");
				formbean.setQsyf(rs1.getString(8)!=null?rs1.getString(8):"");
				formbean.setSfjine(rs1.getString(9)!=null?rs1.getString(9):"0");//本次合同总金额
				formbean.setJsyf(rs1.getString(10)!=null?rs1.getString(10):"");//
				formbean.setTxje(rs1.getString(11)!=null?rs1.getString(11):"0");//本月摊销金额
				list.add(formbean);
				}
			rs2 = db.queryAll(sql2.toString());
			//Query query = new Query();
			//list = query.query(rs1);
			while(rs2.next()){
				CityQueryBean formbean=new CityQueryBean();
				formbean.setJzname(rs2.getString(1)!=null?rs2.getString(1):"");
				formbean.setAddress(rs2.getString(2)!=null?rs2.getString(2):"");
				formbean.setDfzflx(rs2.getString(3)!=null?rs2.getString(3):"");
				formbean.setHtbh(rs2.getString(4)!=null?rs2.getString(4):"");
				formbean.setZdid(rs2.getString(5)!=null?rs2.getString(5):"");
				formbean.setDbid(rs2.getString(6)!=null?rs2.getString(6):"");
				formbean.setBzyf(rs2.getString(7)!=null?rs2.getString(7):"");
				formbean.setQsyf(rs2.getString(8)!=null?rs2.getString(8):"");
				formbean.setSfjine(rs2.getString(9)!=null?rs2.getString(9):"0");//本次合同总金额
				formbean.setJsyf(rs2.getString(10)!=null?rs2.getString(10):"");
				formbean.setTxje(rs2.getString(11)!=null?rs2.getString(11):"0");//本月摊销金额
				list.add(formbean);
				}
			rs3 = db.queryAll(sql3.toString());
			//Query query = new Query();
			//list = query.query(rs1);
			while(rs3.next()){
				CityQueryBean formbean=new CityQueryBean();
				formbean.setJzname(rs3.getString(1)!=null?rs3.getString(1):"");
				formbean.setAddress(rs3.getString(2)!=null?rs3.getString(2):"");
				formbean.setDfzflx(rs3.getString(3)!=null?rs3.getString(3):"");
				formbean.setHtbh(rs3.getString(4)!=null?rs3.getString(4):"");
				formbean.setZdid(rs3.getString(5)!=null?rs3.getString(5):"");
				formbean.setDbid(rs3.getString(6)!=null?rs3.getString(6):"");
				formbean.setBzyf(rs3.getString(7)!=null?rs3.getString(7):"");
				formbean.setQsyf(rs3.getString(8)!=null?rs3.getString(8):"");
				formbean.setSfjine(rs3.getString(9)!=null?rs3.getString(9):"0");//本次合同总金额
				formbean.setJsyf(rs3.getString(10)!=null?rs3.getString(10):"");
				formbean.setTxje(rs3.getString(11)!=null?rs3.getString(11):"0");//本月摊销金额
				list.add(formbean);
				}
			
		}catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rs3 != null) {
				try {
					rs3.close();
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
	//摊销市区分摊费用
	public synchronized List<DianfeidandayinBean> getFentansq(String whereStr,String loginId,String str) {
			List<DianfeidandayinBean> list = new ArrayList<DianfeidandayinBean>();
			String sql = "";
			DataBase db = new DataBase();
			 sql="   SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode = z.shi) AS shi," +
	     		"(SELECT da.agname FROM d_area_grade da WHERE da.agcode=z.xian)AS xian," +
	     		"SUM(g.txje*s.dbili/100*s.xjbili/100)FTJE, " +
	     		"(SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE S.QCBCODE = QC.QCBCODE) AS QCB," +
	     		"(SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE QC.QCBCODE = S.KJKMCODE) AS KJKM," +
	     		"(SELECT NAME FROM INDEXS I WHERE I.CODE = S.ZYMXCODE AND I.TYPE = 'ZYMX') AS ZYMX,S.SHUOSHUZHUANYE " +
	     		" FROM ZHANDIAN Z, JTSHUJUTX G, SBGL S WHERE Z.ID = G.ZDID AND G.DBID = S.DIANBIAOID " +
	     		"AND substr(G.PCH,4)=(SELECT max(substr(G.PCH,4)) from JTSHUJUTX G WHERE 1=1  "+str+" )"+
	     		" "+whereStr+ "AND (Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+
	     		"GROUP BY S.SHUOSHUZHUANYE,S.QCBCODE,S.KJKMCODE, S.ZYMXCODE,Z.SHI,Z.XIAN"; 
			System.out.println("摊销静态查询市区分摊明细："+sql.toString());
			ResultSet rs = null;
			try {
				db.connectDb();
				rs = db.queryAll(sql);
				while(rs.next()){
					DianfeidandayinBean dfdbean=new DianfeidandayinBean();
					dfdbean.setShi(rs.getString(1));
					dfdbean.setXian(rs.getString(2));
					dfdbean.setNETWORKDF(rs.getString(3));
					dfdbean.setQcb(rs.getString(4));
					dfdbean.setKjkm(rs.getString(5));
					dfdbean.setZymx(rs.getString(6));
					dfdbean.setSszy(rs.getString(7));
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
	
	//摊销市分摊费用
	public synchronized List<DianfeidandayinBean> getFentans(String whereStr,String loginId,String str) {
			List<DianfeidandayinBean> list = new ArrayList<DianfeidandayinBean>();
			String sql = "";
			DataBase db = new DataBase();
			sql="   SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode = z.shi) AS shi," +
     		"SUM(g.txje*s.dbili/100*s.xjbili/100)FTJE, " +
     		"(SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE S.QCBCODE = QC.QCBCODE) AS QCB," +
     		"(SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE QC.QCBCODE = S.KJKMCODE) AS KJKM," +
     		"(SELECT NAME FROM INDEXS I WHERE I.CODE = S.ZYMXCODE AND I.TYPE = 'ZYMX') AS ZYMX,S.SHUOSHUZHUANYE " +
     		" FROM ZHANDIAN Z, JTSHUJUTX G, SBGL S WHERE Z.ID = G.ZDID AND G.DBID = S.DIANBIAOID " +
     		"AND substr(G.PCH,4)=(SELECT max(substr(G.PCH,4)) from JTSHUJUTX G WHERE 1=1  "+str+" )"+
     		" "+whereStr+ "AND (Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+
     		"GROUP BY S.SHUOSHUZHUANYE,S.QCBCODE,S.KJKMCODE, S.ZYMXCODE,Z.SHI"; 
		System.out.println("摊销静态查询市分摊明细："+sql.toString());
			ResultSet rs = null;
			try {
				db.connectDb();
				rs = db.queryAll(sql);
				while(rs.next()){
					DianfeidandayinBean dfdbean=new DianfeidandayinBean();
					dfdbean.setShi(rs.getString(1));
					dfdbean.setNETWORKDF(rs.getString(2));
					dfdbean.setQcb(rs.getString(3));
					dfdbean.setKjkm(rs.getString(4));
					dfdbean.setZymx(rs.getString(5));
					dfdbean.setSszy(rs.getString(6));
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
	
	//摊销静态数据查询
	public synchronized List<CityQueryBean>  getTanXiaoCx(String whereStr,String str,
			String loginId) {		
		List<CityQueryBean>  list = new ArrayList<CityQueryBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
        ResultSet rs = null;
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			sql = "SELECT J.ZDNAME,J.SZDQ,J.DFZFLX,J.HTBH,J.TXJE,J.TXYE,J.DBID,J.LRREN,J.QSYF,J.JSYF " +
					" FROM JTSHUJUTX J,ZHANDIAN Z WHERE Z.ID=J.ZDID  " +
					" AND substr(J.PCH,4)=(SELECT max(substr(J.PCH,4)) FROM JTSHUJUTX J,ZHANDIAN Z WHERE Z.ID=J.ZDID "+whereStr+" ) " +whereStr+"" +
					" AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "'))";
            System.out.println("摊销静态数据查询："+sql.toString());
			db.connectDb();		
			rs = db.queryAll(sql.toString());
			//Query query = new Query();
			//list = query.query(rs);
			while(rs.next()){
				CityQueryBean formbean=new CityQueryBean();
				formbean.setJzname(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setAddress(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setDfzflx(rs.getString(3)!=null?rs.getString(3):"");
				formbean.setHtbh(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setTxje(rs.getString(5)!=null?rs.getString(5):"0");
				formbean.setTxye(rs.getString(6)!=null?rs.getString(6):"0");
				formbean.setDbid(rs.getString(7)!=null?rs.getString(7):"");
				formbean.setLrren(rs.getString(8)!=null?rs.getString(8):"");
				formbean.setQsyf(rs.getString(9)!=null?rs.getString(9):"");
				formbean.setJsyf(rs.getString(10)!=null?rs.getString(10):"");
				
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
		return list;
	}
	//合同摊销明细
	public synchronized List<CityQueryBean>  getHtTanXiao(String whereStr,String Str,String where,String wh,
			String loginId,String pd) {		
		List<CityQueryBean>  list = new ArrayList<CityQueryBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		DataBase db = new DataBase();
        ResultSet rs = null;
        StringBuffer sql1=new StringBuffer();
		try {
			sql1.append("select z.jzname,(select i.name from indexs i where i.code=d.dfzflx and i.type='dfzflx')dfzflx,sum(p.money)zje,min(p.startmonth),max(p.endmonth)," +//总金额，最小起始月份，最大结束月份
					"(CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN) ELSE''END) || " +
					"(CASE WHEN Z.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU) ELSE''END) AS SZDQ,bb.money," +//本月摊销金额
					//"(select sum(pp.money) from prepayment pp where d.dbid=pp.prepayment_ammeterid and pp.financeaudit='2'  "+where+" group by d.dbid)bctx," +
					"(select sum(pp.money) from prepayment pp where d.dbid=pp.prepayment_ammeterid and pp.financeaudit='2' "+wh+" group by d.dbid)ljff," +  //累计付费
					"(select sum(pp.money) from prepayment pp where d.dbid=pp.prepayment_ammeterid and pp.financeaudit='2' "+Str+" group by d.dbid)ljtx," +//累计摊销金额
					"(select sum(pp.pjje) from yufufeiview pp where d.dbid=pp.prepayment_ammeterid and pp.notetypeid='pjlx05' "+wh+" group by d.dbid)sj," +//收据金额
					"(select sum(pp.pjje) from yufufeiview pp where d.dbid=pp.prepayment_ammeterid and pp.notetypeid='pjlx03' "+wh+" group by d.dbid)fp," +//发票金额
					"(select sum(pp.fpje) from yufufeiview pp where d.dbid=pp.prepayment_ammeterid and pp.fpsh='1' group by d.dbid)bfp,to_char(aa.startmonth,'yyyy-mm') startmonth,to_char(aa.endmonth,'yyyy-mm') endmonth,to_char(aa.accountmonth,'yyyy-mm') accountmonth,aa.money " +   //补发票金额，本次摊销的起始月份，本次摊销的结束月份，本次摊销的报账月份，本次合同的总金额 
					" from zhandian z, dianbiao d, prepayment p," +
					" (select d.dbid,pp.startmonth,pp.endmonth,pp.accountmonth,pp.money " +
					"  from dianbiao d ,yufufeiview pp where pp.prepayment_ammeterid = d.dbid AND pp.FINANCEAUDIT = '2' "+pd+")aa," +
					" (select pp.money,d.dbid from prepayment pp,dianbiao d where d.dbid = pp.prepayment_ammeterid and pp.financeaudit = '2' "+where+" )bb " +
					" where z.id = d.zdid and d.dbid = p.prepayment_ammeterid and aa.dbid(+)=d.dbid and bb.dbid(+)=d.dbid and z.qyzt = '1' AND D.DFZFLX = 'dfzflx02' " +
					" AND D.DBYT = 'dbyt01' AND Z.XUNI = '0' AND p.cityaudit='1' "+whereStr+" " +
					" AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))" +
					" GROUP BY z.jzname,d.dfzflx,d.dbid,z.xian,z.xiaoqu,aa.startmonth,aa.endmonth,aa.accountmonth,aa.money,bb.money");
			
            System.out.println("合同摊销明细查询："+sql1.toString());
			db.connectDb();		
			rs = db.queryAll(sql1.toString());
			//Query query = new Query();
			//list = query.query(rs);
			while(rs.next()){
				CityQueryBean formbean=new CityQueryBean();
				formbean.setJzname(rs.getString(1)!=null?rs.getString(1):"");
				formbean.setDfzflx(rs.getString(2)!=null?rs.getString(2):"");
				formbean.setHtzje(rs.getString(3)!=null?rs.getString(3):"0");
				formbean.setQsyf(rs.getString(4)!=null?rs.getString(4):"");
				formbean.setJsyf(rs.getString(5)!=null?rs.getString(5):"");
				formbean.setAddress(rs.getString(6)!=null?rs.getString(6):"");
				formbean.setTxje(rs.getString(7)!=null?rs.getString(7):"0");
				formbean.setLjff(rs.getString(8)!=null?rs.getString(8):"0");
				formbean.setLjtxje(rs.getString(9)!=null?rs.getString(9):"0");
				formbean.setLjsj(rs.getString(10)!=null?rs.getString(10):"0");
				formbean.setLjfp(rs.getString(11)!=null?rs.getString(11):"0");
				formbean.setBfp(rs.getString(12)!=null?rs.getString(12):"0");
				formbean.setKsyf1(rs.getString(13)!=null?rs.getString(13):"");
				formbean.setJsyf1(rs.getString(14)!=null?rs.getString(14):"");
				formbean.setBzyf(rs.getString(15)!=null?rs.getString(15):"");
				formbean.setBchtzje(rs.getString(16)!=null?rs.getString(16):"");
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
