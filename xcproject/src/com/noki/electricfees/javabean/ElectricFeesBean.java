package com.noki.electricfees.javabean;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.ammeterdegree.javabean.BargainBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.function.CityQueryBean;
import com.noki.jizhan.ZhanDianForm;
import com.noki.page.NPageBean;
import com.noki.sysconfig.javabean.AutoAuditBean;
import com.noki.util.CTime;
import com.noki.util.MD5;
import com.noki.util.Query;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;
import com.ptac.app.inportuserzibaodian.util.Format;

public class ElectricFeesBean {
	
	//电费信息查询(暂不用)
	public synchronized ArrayList getPageData(int start, String whereStr,
			String loginId) {
		System.out.println("ElectricFeesBean-getPageData:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select zd.jzname,zd.zdcode,am.ammeterid,ef.ELECTRICFEE_ID,ef.UNITPRICE,ef.FLOATPAY,ef.ACTUALPAY,ef.AUTOAUDITSTATUS,"
				    +"(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"
					+ "ind.name NOTETYPEID,ef.NOTENO,ef.PAYOPERATOR,ef.PAYDATETIME,ad.AMMETERDEGREEID,ef.autoauditdescription,ef.manualauditstatus "
					+ "from zhandian zd,ammeters am,dianduview ad,dianfeiview ef,indexs ind  "
					+ "where zd.id=am.stationid and am.ammeterid=ad.ammeterid_fk "
					+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk  and ind.code=ef.notetypeid and ind.type='PJLX'"
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ") order by ef.ELECTRICFEE_ID desc ";

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
			StringBuffer strall = new StringBuffer();
			strall
					.append("select count(*) from zhandian zd,ammeters am,dianduview ad,dianfeiview ef,indexs ind  "
							+ "where zd.id=am.stationid and am.ammeterid=ad.ammeterid_fk "
							+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk  and ind.code=ef.notetypeid and ind.type='PJLX' "
							+ whereStr
							+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
							+ loginId + "'))" + fzzdstr + ") ");
			rs = db.queryAll(strall.toString());
			System.out.println("选*" + strall.toString());// //////////////////////////////////////////////////
			if (rs.next()) {
				this.setAllPage((rs.getInt(1) + 14) / 15);
			}
			NPageBean nbean = new NPageBean();
			System.out.println(sql.toString() + "***********************");
			rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));

			 
			
		    Query query = new Query();
			list = query.query(rs);
			db.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		}
		catch (SQLException de) {
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
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
	
	//电费信息导出
	
	public synchronized ArrayList getPageData(String whereStr,
			String loginId) {
		System.out.println("ElectricFeesBean-getPageData:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select zd.jzname,zd.zdcode,am.ammeterid,ef.ELECTRICFEE_ID,ef.UNITPRICE,ef.FLOATPAY,ef.ACTUALPAY,ef.AUTOAUDITSTATUS,"
			    +"(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"
				+ "ind.name NOTETYPEID,ef.NOTENO,ef.PAYOPERATOR,ef.PAYDATETIME,ad.AMMETERDEGREEID,ef.autoauditdescription,ef.manualauditstatus "
				+ "from zhandian zd,ammeters am,dianduview ad,dianfeiview ef,indexs ind  "
				+ "where zd.id=am.stationid and am.ammeterid=ad.ammeterid_fk "
				+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk  and ind.code=ef.notetypeid and ind.type='PJLX'"
				+ whereStr
				+ " "
				+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
				+ loginId
				+ "'))"
				+ fzzdstr
				+ ") order by ef.ELECTRICFEE_ID desc ";
			System.out.println(sql.toString() + "111111111111111111111");
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
		      rs = db.queryAll(sql.toString());
		      Query query=new Query();
		      list = query.query(rs);
		}

		catch (DbException de) {
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
		System.out.println(list.toString() + "+++++++++++++");
		return list;
	}
	/*
	 * 单条电费单打印的数据（暂不用）
	 */
	public synchronized ArrayList getPageData(String whereStr) {
		System.out.println("ElectricFeesBean-getPageData:" + whereStr);
		
		ArrayList list = new ArrayList();
		String sql = "";
		
		DataBase db = new DataBase();
		try {
		sql = "select zd.jzname,am.ammeterid,ad.LASTDEGREE,ad.THISDEGREE,ad.FLOATDEGREE,ad.ACTUALDEGREE," +
				"am.MULTIPLYINGPOWER,ad.lastdatetime,thisdatetime,ef.UNITPRICE,ef.FLOATPAY,ef.ACTUALPAY,ef.AUTOAUDITSTATUS," +
				"ef.PAYOPERATOR,ef.PAYDATETIME,ef.autoauditdescription,ef.manualauditstatus "
					+ "from zhandian zd,ammeters am,dianduview ad,dianfeiview ef,indexs ind  "
					+ "where zd.id=am.stationid and am.ammeterid=ad.ammeterid_fk "
					+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk  and ind.code=ef.notetypeid and ind.type='PJLX'"
					+"and ef.ELECTRICFEE_ID='"+whereStr+"'";
					

		ResultSet rs = null;
		System.out.println("-"+sql.toString());
			db.connectDb();
			rs = db.queryAll(sql);
            while(rs.next()){
            	list.add(null==rs.getString(1)?"":rs.getString(1));
            	list.add(null==rs.getString(2)?"":rs.getString(2));
            	list.add(null==rs.getString(3)?"0":rs.getString(3));
            	list.add(null==rs.getString(4)?"0":rs.getString(4));
            	list.add(null==rs.getString(5)?"0":rs.getString(5));
            	list.add(null==rs.getString(6)?"0":rs.getString(6));
            	list.add(null==rs.getString(7)?"0":rs.getString(7));
            	list.add(null==rs.getString(8)?"":rs.getString(8));
            	list.add(null==rs.getString(9)?"":rs.getString(9));
            	list.add(null==rs.getString(10)?"0":rs.getString(10));
            	list.add(null==rs.getString(11)?"0":rs.getString(11));
            	list.add(null==rs.getString(12)?"0":rs.getString(12));
            	list.add(null==rs.getString(13)?"0":rs.getString(13));
            	list.add(null==rs.getString(14)?"":rs.getString(14));
            	list.add(null==rs.getString(15)?"":rs.getString(15));
            	list.add(null==rs.getString(16)?"":rs.getString(16));
            	list.add(null==rs.getString(17)?"0":rs.getString(17));
            	
            }
            rs.close();//rsg
			
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
			de.printStackTrace();
		}

		finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}
		return list;
	}

	/*
	 * 电费单查询
	 */
	public synchronized ArrayList getPageDataDegreeFees(
			String whereStr, String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select zd.jzname,zd.zdcode,ad.ammeterdegreeid,ef.ELECTRICFEE_ID,ef.UNITPRICE,ef.FLOATPAY,ef.ACTUALPAY,ef.AUTOAUDITSTATUS," +
					"(decode(zd.xian,null,'',(select distinct agname from d_area_grade where agcode = zd.xian))||" +
					"decode(zd.xiaoqu,null,'',(select distinct agname from d_area_grade where agcode = zd.xiaoqu))) as szdq,"
				    + "(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"
					+ "(select name from indexs where code = ef.notetypeid and type = 'PJLX') NOTETYPEID,ef.NOTENO,ef.PAYOPERATOR,to_char(ef.PAYDATETIME,'yyyy-mm-dd') PAYDATETIME,ad.AMMETERDEGREEID,ef.autoauditdescription,ef.manualauditstatus "
					+ "from zhandian zd,dianbiao am,dianduview ad,dianfeiview ef  "
					+ "where zd.id=am.zdid and am.dbid=ad.ammeterid_fk and zd.qyzt='1' "
					+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and (ef.manualauditstatus='0' or ef.manualauditstatus = '-2' or ef.manualauditstatus is null) "
					+ whereStr
					+ " "
					+ "and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"
					+ loginId + "')" + fzzdstr + " ORDER BY szdq, ef.PAYDATETIME DESC";
                        System.out.println("电费单查询："+sql.toString());
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
			StringBuffer strall = new StringBuffer();
			strall
					.append("select count(*) from zhandian zd,dianbiao am,dianduview ad,dianfeiview ef,indexs ind  "
							+ "where zd.id=am.zdid and am.dbid=ad.ammeterid_fk and zd.qyzt='1' "
							+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and ef.manualauditstatus<'1' and ind.code=ef.notetypeid and ind.type='PJLX' "
							+ whereStr
							+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
							+ loginId + "'))" + fzzdstr + ") ");
			System.out.println("电费单查询分页："+strall.toString());
			rs = db.queryAll(strall.toString());
			
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
			db.close();
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
	
	/*
	 * 电费单导出
	 */
	public synchronized ArrayList getPageDataDegreeFeesDF(
			String whereStr, String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select " +
			" (case when zd.xian is not null then (select distinct agname from d_area_grade where agcode=zd.xian) else '' end)" +
			" ||(case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=zd.xiaoqu) else '' end) as szdq,"+
			" zd.jzname,zd.zdcode,ef.ELECTRICFEE_ID,ef.UNITPRICE,ef.FLOATPAY,ef.ACTUALPAY,ef.AUTOAUDITSTATUS," +
		     "(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"
			+ "(select name from indexs where code = ef.notetypeid and type = 'PJLX') NOTETYPEID,ef.NOTENO,ef.PAYOPERATOR,TO_CHAR(ef.PAYDATETIME,'yyyy-mm-dd') PAYDATETIME,ad.AMMETERDEGREEID,ef.autoauditdescription,ef.manualauditstatus "
			+ "from zhandian zd,dianbiao am,dianduview ad,dianfeiview ef  "
			+ "where zd.id=am.zdid and am.dbid=ad.ammeterid_fk and zd.qyzt='1' "
			+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and (ef.manualauditstatus<'1' or ef.manualauditstatus is null) "
			+ whereStr
			+ " "
			+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
			+ loginId + "'))" + fzzdstr + ") ORDER BY zd.shi,zd.xian, ef.PAYDATETIME DESC";
            System.out.println("电费单导出："+sql.toString());
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
		      rs = db.queryAll(sql.toString());
		      Query query=new Query();
		      list = query.query(rs);
		}

		catch (DbException de) {
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
	/*
	 * 预付费单打印查询
	 */
	public synchronized List<ElectricFeesFormBean> getPageDataYff(String Str,
			String whereStr, String loginId) {
		List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql1 = "";
		String sql2 = "";
		String fzzdstr = "";
		ResultSet rs1 = null;
		DataBase db = new DataBase();
		StringBuffer sql=new StringBuffer();
		try {
			
			fzzdstr = getFuzeZdid(db, loginId);
			//预付费
			sql.append("SELECT ZD.JZNAME,DY.UUID AS DFUUID, " +
					"(CASE WHEN ZD.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN)ELSE''END) || " +
					"(CASE WHEN ZD.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU)ELSE''END) AS SZDQ," +
					"(SELECT I.NAME FROM INDEXS I WHERE  ZD.STATIONTYPE = I.CODE AND I.TYPE = 'stationtype') AS JZTYPE," +
					"TO_CHAR(DY.ACCOUNTMONTH,'yyyy-mm'),DY.ID AS ELECTRICFEE_ID,DY.MONEY," +
					"(SELECT I.NAME FROM INDEXS I WHERE AM.DFZFLX = I.CODE AND AM.ZDID = ZD.ID AND I.TYPE = 'dfzflx') AS DFZFLX," +
					"DY.MEMO,(SELECT I.NAME FROM INDEXS I WHERE I.CODE = DY.NOTETYPEID AND I.TYPE = 'PJLX')PJLX," +
					"DY.PJJE,ROUND(((SELECT SUM( TO_NUMBER(Y.MONEY)) FROM YUFUFEIVIEW Y WHERE AM.DBID = Y.prepayment_ammeterid  AND Y.financeaudit='2'  "+whereStr+" " +
					"GROUP BY DBID ) - (SELECT SUM(ACTUALPAY)FROM DIANFEIDAN WHERE AM.DBID = AMMETERID_FK AND MANUALAUDITSTATUS = '2' "+whereStr+" GROUP BY DBID)),2)YE" +
					" FROM ZHANDIAN ZD, DIANBIAO AM, YUFUFEIVIEW DY WHERE ZD.ID = AM.ZDID " +
					" AND AM.DBID = DY.PREPAYMENT_AMMETERID and ZD.QYZT = '1' AND AM.DBQYZT='1' " +
					"AND DY.MANUALAUDITSTATUS = '1' AND DY.CITYAUDIT = '1' AND DY.financeaudit<>'2'  AND DY.CITYZRAUDITSTATUS='1' "+whereStr+" " +Str+"" +
					" AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))) " +
					" GROUP BY ZD.JZNAME,ZD.ZDCODE,DY.UUID,DY.PJJE,ZD.ID,ZD.STATIONTYPE,DY.NOTETYPEID,AM.DBID," +
					"DY.ACCOUNTMONTH,ZD.XIAN,ZD.XIAOQU,DY.MEMO,ZD.SHI,DY.ID,DY.MONEY,AM.ZDID,AM.DFZFLX,DY.entrypensonnel");
            db.connectDb();
            System.out.println("预付费打印查询111："+sql.toString());
			rs1 = db.queryAll(sql.toString());
           
			while(rs1.next()){
				//ZD.JZNAME,DY.UUID AS DFUUID,AS SZDQ,AS JZTYPE,DY.ACCOUNTMONTH,DY.ID AS ELECTRICFEE_ID,
				//DY.MONEY AS ACTUALPAY,  FFFS, DY.MEMO,PJLX,DY.PJJE,YE
				ElectricFeesFormBean formbean=new ElectricFeesFormBean();
				formbean.setJzname(rs1.getString(1)!=null?rs1.getString(1):"");
				formbean.setDfuuid(rs1.getString(2)!=null?rs1.getString(2):"");
				formbean.setSzdq(rs1.getString(3)!=null?rs1.getString(3):"");
				formbean.setJztype(rs1.getString(4)!=null?rs1.getString(4):"");
				formbean.setAccountmonth(rs1.getString(5)!=null?rs1.getString(5):"");
				formbean.setElectricfeeId(rs1.getString(6)!=null?rs1.getString(6):"");
				formbean.setMoney(rs1.getString(7)!=null?rs1.getString(7):"");
				formbean.setDfzflx(rs1.getString(8)!=null?rs1.getString(8):"");
				formbean.setMemo(rs1.getString(9)!=null?rs1.getString(9):"");
				formbean.setNotetypeid(rs1.getString(10)!=null?rs1.getString(10):"");
				formbean.setPjjeyf(rs1.getString(11)!=null?rs1.getString(11):"");
				formbean.setYffye(rs1.getString(12)!=null?rs1.getString(12):"");
				
				list.add(formbean);
				
			}  
			db.close();
		}catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
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
	
	/*
	 * 预付费单打印数据
	 * update  2014.07.15 zhouxue 把添加在预付费表里的合同单，插卡单  都在预付费打印里打印 
	 */
	public synchronized List<List<yufufeidayinBean>> getPageDataYfdy(
			String whereStr) {
		
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		ResultSet rs1 = null;
		DataBase db = new DataBase();
		StringBuffer sql=new StringBuffer();
		List<List<yufufeidayinBean>> list = new ArrayList<List<yufufeidayinBean>>();
		try {
			//预付费
			sql.append("SELECT ZD.JZNAME, " +
					"(CASE WHEN ZD.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN)ELSE''END) || " +
					"(CASE WHEN ZD.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU)ELSE''END) AS SZDQ," +
					"(SELECT I.NAME FROM ZHANDIAN Z, INDEXS I WHERE Z.ID = ZD.ID AND Z.STATIONTYPE = I.CODE AND I.TYPE = 'stationtype') AS JZTYPE," +
					"TO_CHAR(DY.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,DY.MONEY," +
					"(SELECT I.NAME FROM INDEXS I WHERE AM.DFZFLX = I.CODE AND AM.ZDID = ZD.ID AND I.TYPE = 'dfzflx') AS DFZFLX," +
					"DY.MEMO,(SELECT I.NAME FROM INDEXS I WHERE I.CODE = DY.NOTETYPEID AND I.TYPE = 'PJLX')PJLX," +
					"DY.PJJE,ROUND(((SELECT SUM(Y.MONEY) FROM YUFUFEIVIEW Y WHERE AM.DBID = Y.prepayment_ammeterid  AND Y.financeaudit='2' " +
					"GROUP BY DBID ) - (SELECT SUM(ACTUALPAY)FROM DIANFEIDAN WHERE AM.DBID = AMMETERID_FK AND MANUALAUDITSTATUS = '2'  GROUP BY DBID)),2) YE" +
					" FROM ZHANDIAN ZD, DIANBIAO AM, YUFUFEIVIEW DY WHERE ZD.ID = AM.ZDID " +
					" AND AM.DBID = DY.PREPAYMENT_AMMETERID and ZD.QYZT = '1' AND AM.DBQYZT='1' " +
					"AND DY.MANUALAUDITSTATUS = '1' AND DY.CITYZRAUDITSTATUS='1' AND DY.CITYAUDIT = '1'   and dy.UUID in ("+whereStr+") " +
					" GROUP BY ZD.JZNAME,ZD.ZDCODE,DY.UUID,DY.PJJE,ZD.ID,STATIONTYPE,DY.NOTETYPEID,AM.DBID," +
					"DY.ACCOUNTMONTH,ZD.XIAN,ZD.XIAOQU,DY.MEMO,ZD.SHI,DY.ID,DY.MONEY,AM.ZDID,AM.DFZFLX  ORDER BY JZTYPE ");
            db.connectDb();	
            System.out.println("预付费打印数据22："+sql.toString());
			rs1 = db.queryAll(sql.toString());
			int number=0;
			String name="";
			while(rs1.next()){
				//ZD.JZNAME,AS SZDQ,AS JZTYPE,DY.ACCOUNTMONTH,
				//DY.MONEY ,  FFFS, DY.MEMO,PJLX,DY.PJJE,YE
				if(!rs1.getString(3).equals(name)){
					name=rs1.getString(3);
					List<yufufeidayinBean> dfdlist=new ArrayList<yufufeidayinBean>();
					list.add(dfdlist);
					number++;
				}
				yufufeidayinBean bean=new yufufeidayinBean();
				bean.setJzname(rs1.getString(1)!=null?rs1.getString(1):"");
				bean.setSzdq(rs1.getString(2)!=null?rs1.getString(2):"");
				bean.setJztype(rs1.getString(3)!=null?rs1.getString(3):"");
				bean.setAccountmonth(rs1.getString(4)!=null?rs1.getString(4):"");
				bean.setMoney(rs1.getString(5)!=null?rs1.getString(5):"");
				bean.setDfzflx(rs1.getString(6)!=null?rs1.getString(6):"");
				bean.setMemo(rs1.getString(7)!=null?rs1.getString(7):"");
				bean.setPjlx(rs1.getString(8)!=null?rs1.getString(8):"");
				bean.setPjje(rs1.getString(9)!=null?rs1.getString(9):"");
				bean.setYe(rs1.getString(10)!=null?rs1.getString(10):"");
				
				list.get(number-1).add(bean);
			}
			db.close();
		}

		catch (Exception de) {
			de.printStackTrace();
		}

		finally {
			if (rs1 != null) {
				try {
					rs1.close();
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
	/*
	 * 财务电费单打印查询
	 * update 2014-07-14 zhouxue  去掉从预付费表里查询的数据，电费单打印只打印从电费表查询出的数据
	 */
	public synchronized List<ElectricFeesFormBean> getPageDataCaiwu(
			String whereStr,String Str, String loginId) {
		List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql1 = "";
		String sql2 = "";
		String fzzdstr = "";
		ResultSet rs = null;
		ResultSet rs1 = null;
		DataBase db = new DataBase();
		try {
			//电费单
			sql1="SELECT ZD.JZNAME,DY.DFUUID,(CASE WHEN ZD.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE''END) " +
    		"||(CASE WHEN ZD.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ," +
    		"(SELECT I.NAME FROM ZHANDIAN Z,INDEXS I WHERE Z.ID=ZD.ID AND Z.STATIONTYPE=I.CODE  AND I.TYPE='stationtype')AS JZTYPE, " +
    		"TO_CHAR(DY.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(DY.THISDATETIME,'yyyy-mm-dd') THISDATETIME,TO_CHAR(DY.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,DY.LASTDEGREE,DY.THISDEGREE,DY.BLHDL,DY.FLOATDEGREE,DY.ELECTRICFEE_ID," +
    		"DY.UNITPRICE,DY.FLOATPAY,DY.ACTUALPAY,(SELECT INS.NAME  FROM INDEXS INS WHERE AM.DFZFLX = INS.CODE  AND AM.ZDID = ZD.ID  AND INS.TYPE = 'dfzflx') AS FFFS," +
    		"DY.MEMO,(SELECT I.NAME FROM INDEXS I WHERE I.CODE=DY.NOTETYPEID AND I.TYPE='PJLX'),DY.PJJE,DY.Manualauditstatus, " +
    		" DY.LASTELECFEES, DY.LASTELECDEGREE, DY.LASTUNITPRICE,'1' AS BZW," +
    		"(SELECT I.NAME FROM INDEXS I WHERE I.CODE=ZD.GDFS AND I.TYPE='GDFS') GDFS "+
    		" FROM ZHANDIAN ZD, DIANBIAO AM, eleprint_view DY WHERE AM.DBQYZT='1' AND ZD.ID = AM.ZDID AND AM.DBID = DY.AMMETERID_FK and ZD.QYZT='1' and DY.CITYZRAUDITSTATUS='1' " +
    		" AND AM.DBQYZT='1' AND DY.CITYAUDIT='1'  "+whereStr+"  " +
    		"AND (ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))  ORDER BY ZD.JZNAME,ZD.XIAOQU,AM.DFZFLX" ;
            
            System.out.println("电费单打印查询和导出："+sql1.toString());
            db.connectDb();			
			rs = db.queryAll(sql1);
            
			//Query query = new Query();
			//list = query.query(rs);
			while(rs.next()){
				ElectricFeesFormBean formbean=new ElectricFeesFormBean();
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
				formbean.setDfzflx(rs.getString(16)!=null?rs.getString(16):"");
				formbean.setMemo(rs.getString(17)!=null?rs.getString(17):"");
				formbean.setNotetypeid(rs.getString(18)!=null?rs.getString(18):"");
				formbean.setPjjedy(rs.getString(19)!=null?rs.getString(19):"");
				formbean.setManualauditstatus(rs.getString(20)!=null?rs.getString(20):"");
				formbean.setLastelecfees(rs.getString("LASTELECFEES")!=null?rs.getString("LASTELECFEES"):""); //上期电费
				formbean.setLastelecdegree(rs.getString("LASTELECDEGREE")!=null?rs.getString("LASTELECDEGREE"):""); //上期电量
				formbean.setLastunitprice(rs.getString("LASTUNITPRICE")!=null?rs.getString("LASTUNITPRICE"):""); //上期单价
				formbean.setBzw(rs.getString(24));
				formbean.setGdfs(rs.getString(25));
				list.add(formbean);
			}
			/*  System.out.println("预付费打印查询："+sql2.toString());
			rs1 = db.queryAll(sql2);
          
			while(rs1.next()){
				ElectricFeesFormBean formbean=new ElectricFeesFormBean();
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
				formbean.setDfzflx(rs1.getString(14)!=null?rs1.getString(14):"");
				formbean.setMemo(rs1.getString(15)!=null?rs1.getString(15):"");
				formbean.setNotetypeid(rs1.getString(16)!=null?rs1.getString(16):"");
				formbean.setPjjedy(rs1.getString(17)!=null?rs1.getString(17):"");
				formbean.setManualauditstatus(rs1.getString(18)!=null?rs1.getString(18):"");
				formbean.setBzw(rs1.getString(19));
				list.add(formbean);
			}  */          
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
	/*		if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException see) {
					see.printStackTrace();
				}
			}*/
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	//电费单批量打印数据
	public synchronized List<List<DianfeidandayinBean>> getPageDataCaiwu(String whereStr,String dystr) {
		
		List<List<DianfeidandayinBean>> list = new ArrayList<List<DianfeidandayinBean>>();
		String sql = "";
		String name="";
		DataBase db = new DataBase();

		 sql="select zd.jzname," +
 		" (case when zd.xian is not null then(select distinct agname from d_area_grade where agcode = zd.xian) else''end) " +
 		"|| (case when zd.xiaoqu is not null then(select distinct agname from d_area_grade where agcode = zd.xiaoqu) else '' end) as szdq," +
 		"(select i.name from zhandian z,indexs i where z.id=zd.id and z.STATIONTYPE=i.code  and i.type='stationtype')as jztype, " +
 		"TO_CHAR(DY.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,TO_CHAR(DY.THISDATETIME,'yyyy-mm-dd') THISDATETIME,TO_CHAR(DY.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,DY.LASTDEGREE,DY.THISDEGREE,DY.blhdl,DY.FLOATDEGREE,DY.ELECTRICFEE_ID," +
 		"DY.UNITPRICE,DY.FLOATPAY,DY.actualpay,(SELECT INS.NAME  FROM INDEXS INS WHERE AM.DFZFLX = INS.CODE  AND AM.ZDID = ZD.ID  AND INS.TYPE = 'dfzflx') AS FFFS,am.beilv,dy.memo," +
 		"(select i.name  from  indexs i where i.code=dy.notetypeid and i.type='PJLX'),DY.PJJE, " +
		" DY.LASTELECFEES, DY.LASTELECDEGREE, DY.LASTUNITPRICE," +
		"(SELECT I.NAME FROM INDEXS I WHERE I.CODE=ZD.GDFS AND I.TYPE='GDFS') GDFS "+
 		" FROM ZHANDIAN ZD, DIANBIAO AM, ELEPRINT_VIEW DY WHERE ZD.ID = AM.ZDID AND AM.DBID = DY.AMMETERID_FK" +
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
					List<DianfeidandayinBean> dfdlist=new ArrayList<DianfeidandayinBean>();
					list.add(dfdlist);
					number++;
				}
				DianfeidandayinBean dfdbean=new DianfeidandayinBean();
				dfdbean.setJzname(rs.getString(1));
				dfdbean.setSzdq(rs.getString(2));
				dfdbean.setJztype(rs.getString(3));
				//dfdbean.setStatmonth(rs.getString(4));
				//dfdbean.setEndmonth(rs.getString(5));
				dfdbean.setLastdatetime(rs.getString(4));
				dfdbean.setThisdatetime(rs.getString(5));
				dfdbean.setAccountmonth(rs.getString(6));
				dfdbean.setLastdegree(rs.getString(7));
				dfdbean.setThisdegree(rs.getString(8));
				dfdbean.setActualdegree(rs.getString(9));
				dfdbean.setFloatdegree(rs.getString(10));
				dfdbean.setElectricfee_id(rs.getString(11));
				dfdbean.setUnitprice(rs.getString(12));
				dfdbean.setFloatpay(rs.getString(13));
				dfdbean.setActualpay(rs.getString(14));
				dfdbean.setFffs(rs.getString(15));
				dfdbean.setBeilv(rs.getString(16));
				dfdbean.setMemo(rs.getString(17));
				dfdbean.setNotetypeid(rs.getString(18));
				dfdbean.setPjje(rs.getString(19));
				dfdbean.setLastelecfees(rs.getString("LASTELECFEES")!=null?rs.getString("LASTELECFEES"):""); //上期电费
				dfdbean.setLastelecdegree(rs.getString("LASTELECDEGREE")!=null?rs.getString("LASTELECDEGREE"):""); //上期电量
				dfdbean.setLastunitprice(rs.getString("LASTUNITPRICE")!=null?rs.getString("LASTUNITPRICE"):""); //上期单价
				dfdbean.setGdfs(rs.getString(23));
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
	//电费单打印分摊费用
public synchronized List<DianfeidandayinBean> getFentan(String whereStr) {
		List<DianfeidandayinBean> list = new ArrayList<DianfeidandayinBean>();
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
				"FROM ZHANDIAN Z, DIANBIAO D, SBGL S, CAIWUDAYIN_DDV A, CAIWUDAYIN_DDF E WHERE Z.ID = D.ZDID AND D.DBID = S.DIANBIAOID" +
				" AND D.DBID = A.AMMETERID_FK  AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND e.dfuuid IN("+whereStr+") " +
				"GROUP BY S.SHUOSHUZHUANYE, S.QCBCODE, S.KJKMCODE, S.ZYMXCODE,z.shi,z.xian";
		System.out.println("电费单打印分摊费用："+sql.toString());
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				DianfeidandayinBean dfdbean=new DianfeidandayinBean();
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
//暂估的分摊有中心成本
public synchronized List<DianfeidandayinBean> getFentan1(String whereStr,String Str,String loginId) {
	List<DianfeidandayinBean> list = new ArrayList<DianfeidandayinBean>();
	String sql = "";
	DataBase db = new DataBase();
	     sql="   SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode = z.shi) AS shi," +
	     		"(SELECT da.agname FROM d_area_grade da WHERE da.agcode=z.xian)AS xian," +
	     		"SUM(g.zangumoney*s.dbili/100*s.xjbili/100)FTJE, " +
	     		"(SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE S.QCBCODE = QC.QCBCODE) AS QCB," +
	     		"(SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE QC.QCBCODE = S.KJKMCODE) AS KJKM," +
	     		"(SELECT NAME FROM INDEXS I WHERE I.CODE = S.ZYMXCODE AND I.TYPE = 'ZYMX') AS ZYMX,S.SHUOSHUZHUANYE " +
	     		" FROM ZHANDIAN Z, ZANGU G, SBGL S WHERE Z.ID = G.ZDID AND G.DBID = S.DIANBIAOID " +
	     		" "+whereStr+ "AND (Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+
	     		"GROUP BY S.SHUOSHUZHUANYE,S.QCBCODE,S.KJKMCODE, S.ZYMXCODE,Z.SHI,Z.XIAN"; 
	System.out.println("暂估的分摊有中心成本："+sql.toString());
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
//暂估的分摊无中心成本
public synchronized List<DianfeidandayinBean> getFentan2(String whereStr,String Str,String loginId) {
	List<DianfeidandayinBean> list = new ArrayList<DianfeidandayinBean>();
	String sql = "";
	DataBase db = new DataBase();
	sql="   SELECT (SELECT da.agname FROM d_area_grade da WHERE da.agcode = z.shi) AS shi," +
			"SUM(g.zangumoney*s.dbili/100*s.xjbili/100)FTJE, " +
			"(SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE S.QCBCODE = QC.QCBCODE) AS QCB," +
			"(SELECT DISTINCT QCBNAME FROM QCBDF QC WHERE QC.QCBCODE = S.KJKMCODE) AS KJKM," +
			"(SELECT NAME FROM INDEXS I WHERE I.CODE = S.ZYMXCODE AND I.TYPE = 'ZYMX') AS ZYMX,S.SHUOSHUZHUANYE " +
			" FROM ZHANDIAN Z, ZANGU G, SBGL S WHERE Z.ID = G.ZDID AND G.DBID = S.DIANBIAOID " +
			" "+whereStr+ "AND (Z.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+
			"GROUP BY S.SHUOSHUZHUANYE,S.QCBCODE,S.KJKMCODE, S.ZYMXCODE,Z.SHI";
	System.out.println("暂估的分摊无中心成本："+sql.toString());
	ResultSet rs = null;
	try {
		db.connectDb();
		rs = db.queryAll(sql);
		while(rs.next()){
			DianfeidandayinBean dfdbean=new DianfeidandayinBean();
			dfdbean.setShi(rs.getString(1));
			dfdbean.setNETWORKDF(rs.getString(2));//分摊金额
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

	
	
//流程号
  public int getMaxlch(String shi){
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
	  
	  return lch;
  }
	/*//流程号预支
  public String getMaxlchyz(){
	  String lch="";
	  String sql="select max(liuchenghao) from yufufeiview ";
	  DataBase db = new DataBase();
	  ResultSet rs = null;
	  try {
		rs=db.queryAll(sql);
		while(rs.next()){
			lch=(null==rs.getString(1)?"1000":rs.getString(1));
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
	  
	  return lch;
  }*/
//流程号
  public boolean insertlch(String str1,String str2,int lch,String loginName,String shi){
	  boolean flag=false;
	  //String sql="update electricfees e set e.liuchenghao='"+lch+"' " +
	  //		"where e.dfuuid in(select v.dfuuid from dianfeiview v where v.electricfee_id in("+str+"))";	
	  SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dayintime = mat.format(new Date());
	  String sql1="update electricfees e set e.liuchenghao='"+lch+"' " +",e.dayinren='"+loginName+"' "+",e.dayintime=to_date('"+dayintime+"','yyyy-mm-dd hh24:mi:ss') " +"where dfuuid in("+str1+")"; 
	  String sql2="update prepayment p set p.liuchenghao='"+lch+"' " +",p.dayinren='"+loginName+"' "+",p.dayintime=to_date('"+dayintime+"','yyyy-mm-dd hh24:mi:ss') " +"where uuid in("+str2+")"; 
	  String sql3="update liuchenghao set liuchenghao='"+lch+"' where shi='"+shi+"'";
	  DataBase db = new DataBase();
	  try {
		  boolean flag1=false;
		  boolean flag2=false;
		  if(str1!=""&&str1!=null){
			System.out.println("流程号月结:"+sql1);  
		    if(db.update(sql1)>=1){
		    	flag1=true;
		    };
		  }
		  if(str2!=""&&str2!=null){
			System.out.println("流程号预支:"+sql2);
		    if(db.update(sql2)>=1){
		    	flag2=true;
		    };  
		  }
		  db.update(sql3);
			System.out.println("流程号表修改:"+sql3);
		  flag=(flag1||flag2);
	} catch (Exception e) {
		try {
		db.rollback();
		} catch (DbException dee) {
		dee.printStackTrace();
		}
		e.printStackTrace();
	}finally {
		      try {
		        db.close();
		      }
		      catch (DbException de) {
		        de.printStackTrace();
		      }
	}	  
	  return flag;
  }
	// /////////////////////////////////////////////////

	// 财务审核电费列表查询
	public synchronized List<ElectricFeesFormBean> getPageDataCheck(int start, String whereStr,String str1,String str2,
			String loginId) {
		List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql1 = "";
		String sql2 = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql1="SELECT EF.LIUCHENGHAO,ZD.JZNAME,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),D.DBID,D.DLLX,D.YDSB,ZD.PROPERTY," +			
			"D.DFZFLX," +
			"(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"+
			"EF.NOTETYPEID,"+
			"EF.ELECTRICFEE_ID,EF.DFUUID,EF.ACTUALPAY, EF.AUTOAUDITSTATUS,EF.NOTENO,EF.ENTRYTIME,EF.MANUALAUDITSTATUS," +
			"(CASE  WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END) "+
            " || (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE ''END) AS SZDQ,"+
			"AD.LASTDEGREE,to_char(AD.LASTDATETIME,'yyyy-mm-dd'),AD.THISDEGREE,to_char(AD.THISDATETIME,'yyyy-mm-dd'),to_char(AD.STARTMONTH,'yyyy-mm'),to_char(AD.ENDMONTH,'yyyy-mm'),D.BEILV,AD.FLOATDEGREE,AD.BLHDL, EF.UNITPRICE, EF.FLOATPAY,EF.PJJE " +
			"FROM  financecheckdf_ddf EF,ZHANDIAN ZD,DIANBIAO D,financecheckdf_ddv AD WHERE ZD.ID=D.ZDID  AND EF.LIUCHENGHAO IS NOT NULL AND D.DBID=AD.AMMETERID_FK AND AD.AMMETERDEGREEID =EF.AMMETERDEGREEID_FK AND EF.CITYAUDIT='1'  "+whereStr+str1+" " +
			"AND (D.DFZFLX='dfzflx01' OR D.DFZFLX='dfzflx03') AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+")";
			
			sql2="SELECT EF.LIUCHENGHAO,ZD.JZNAME,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),D.DBID,D.DLLX,D.YDSB,ZD.PROPERTY," +
			"D.DFZFLX," +
			"(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"+
			"EF.NOTETYPEID,"+
			"EF.UUID,EF.MONEY,EF.NOTENO,EF.ENTRYTIME,EF.FINANCEAUDIT," +
			"(CASE  WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END)  "+
            "|| (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE ''END) AS SZDQ, " +
            "EF.STARTDEGREE,EF.STOPDEGREE,to_char(EF.STARTDATE,'yyyy-mm-dd'),to_char(EF.ENDDATE,'yyyy-mm-dd'),to_char(EF.STARTMONTH,'yyyy-mm'),to_char(EF.ENDMONTH,'yyyy-mm'),D.BEILV,EF.DIANLIANG,EF.MONEY,EF.DANJIA,EF.PJJE " +
			"FROM  financecheck_yff EF,ZHANDIAN ZD,DIANBIAO D WHERE ZD.ID=D.ZDID AND EF.LIUCHENGHAO IS NOT NULL AND D.DBID=EF.PREPAYMENT_AMMETERID AND EF.CITYAUDIT='1'  "+whereStr+str2+" " +
			"AND (D.DFZFLX='dfzflx02' OR D.DFZFLX='dfzflx04' OR D.DFZFLX = 'dfzflx03' ) AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+")";

			db.connectDb();
			System.out.println("财务审核电费列表查询-----月结："+sql1.toString());
			rs = db.queryAll(sql1.toString());
//			Query query = new Query();
//			list = query.query(rs);
			while(rs.next()){
				ElectricFeesFormBean feesbeana=new ElectricFeesFormBean();
				feesbeana.setLiuchenghao(rs.getString(1)!=null?rs.getString(1):"");
				feesbeana.setJzname(rs.getString(2)!=null?rs.getString(2):"");
				feesbeana.setAccountmonth(rs.getString(3)!=null?rs.getString(3):"");
				feesbeana.setDbid(rs.getString(4)!=null?rs.getString(4):"");
				feesbeana.setDllx(rs.getString(5)!=null?rs.getString(5):"");
				feesbeana.setYdsb(rs.getString(6)!=null?rs.getString(6):"");
				feesbeana.setProperty(rs.getString(7)!=null?rs.getString(7):"");
				feesbeana.setDfzflx(rs.getString(8)!=null?rs.getString(8):"");
				feesbeana.setStationtype(rs.getString(9)!=null?rs.getString(9):"");
				feesbeana.setNotetypeid(rs.getString(10)!=null?rs.getString(10):"");
				feesbeana.setElectricfeeId(rs.getString(11)!=null?rs.getString(11):"");
				feesbeana.setDfuuid(rs.getString(12)!=null?rs.getString(12):"");
				feesbeana.setActualpay(rs.getString(13)!=null?rs.getString(13):"");
				feesbeana.setAutoauditstatus(rs.getString(14)!=null?rs.getString(14):"");
				feesbeana.setNoteno(rs.getString(15)!=null?rs.getString(15):"");
				feesbeana.setEntrytime(rs.getString(16)!=null?rs.getString(16):"");
				feesbeana.setManualauditstatus(rs.getString(17)!=null?rs.getString(17):"");
				feesbeana.setSzdq(rs.getString(18)!=null?rs.getString(18):"");
				feesbeana.setLastdegree(rs.getString(19)!=null?rs.getString(19):"");
				feesbeana.setLastdatetime(rs.getString(20)!=null?rs.getString(20):"");
				feesbeana.setThisdegree(rs.getString(21)!=null?rs.getString(21):"");
				feesbeana.setThisdatetime(rs.getString(22)!=null?rs.getString(22):"");
				feesbeana.setStartmonth(rs.getString(23)!=null?rs.getString(23):"");
				feesbeana.setEndmonth(rs.getString(24)!=null?rs.getString(24):"");
				feesbeana.setBeilv(rs.getString(25)!=null?rs.getString(25):"");
				feesbeana.setFloatdegree(rs.getString(26)!=null?rs.getString(26):"");
				feesbeana.setBlhdl(rs.getString(27)!=null?rs.getString(27):"");
				feesbeana.setUnitprice(rs.getString(28)!=null?rs.getString(28):"");
				feesbeana.setFloatpay(rs.getString(29)!=null?rs.getString(29):"");
				feesbeana.setPjjedf(rs.getString(30)!=null?rs.getString(30):"");
				list.add(feesbeana);
			}
			System.out.println("财务审核电费列表查询-----预付费："+sql2.toString());
			rs1 = db.queryAll(sql2.toString());
			while(rs1.next()){
				ElectricFeesFormBean feesbeana=new ElectricFeesFormBean();
				feesbeana.setLiuchenghao(rs1.getString(1)!=null?rs1.getString(1):"");
				feesbeana.setJzname(rs1.getString(2)!=null?rs1.getString(2):"");
				feesbeana.setAccountmonth(rs1.getString(3)!=null?rs1.getString(3):"");
				feesbeana.setDbid(rs1.getString(4)!=null?rs1.getString(4):"");
				feesbeana.setDllx(rs1.getString(5)!=null?rs1.getString(5):"");
				feesbeana.setYdsb(rs1.getString(6)!=null?rs1.getString(6):"");
				feesbeana.setProperty(rs1.getString(7)!=null?rs1.getString(7):"");
				feesbeana.setDfzflx(rs1.getString(8)!=null?rs1.getString(8):"");
				feesbeana.setStationtype(rs1.getString(9)!=null?rs1.getString(9):"");
				feesbeana.setNotetypeid(rs1.getString(10)!=null?rs1.getString(10):"");
				feesbeana.setDfuuid(rs1.getString(11)!=null?rs1.getString(11):"");
				feesbeana.setActualpay(rs1.getString(12)!=null?rs1.getString(12):"");
				feesbeana.setNoteno(rs1.getString(13)!=null?rs1.getString(13):"");
				feesbeana.setEntrytime(rs1.getString(14)!=null?rs1.getString(14):"");
				feesbeana.setManualauditstatus(rs1.getString(15)!=null?rs1.getString(15):"");
				feesbeana.setSzdq(rs1.getString(16)!=null?rs1.getString(16):"");
				feesbeana.setLastdegree(rs1.getString(17)!=null?rs1.getString(17):"");
				feesbeana.setThisdegree(rs1.getString(18)!=null?rs1.getString(18):"");
				feesbeana.setLastdatetime(rs1.getString(19)!=null?rs1.getString(19):"");
				feesbeana.setThisdatetime(rs1.getString(20)!=null?rs1.getString(20):"");
				feesbeana.setStartmonth(rs1.getString(21)!=null?rs1.getString(21):"");
				feesbeana.setEndmonth(rs1.getString(22)!=null?rs1.getString(22):"");
				feesbeana.setBeilv(rs1.getString(23)!=null?rs1.getString(23):"");
				feesbeana.setBlhdl(rs1.getString(24)!=null?rs1.getString(24):"");
				feesbeana.setActualpay(rs1.getString(25)!=null?rs1.getString(25):"");
				feesbeana.setUnitprice(rs1.getString(26)!=null?rs1.getString(26):"");
				feesbeana.setPjjedf(rs1.getString(27)!=null?rs1.getString(27):"");
				list.add(feesbeana);
			}
			System.out.println("老ls的长度是："+list.size());
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
	
	//财务电费审核条数、实际电费金额总和
	public ElectricFeesFormBean getCountcwdfsh(String whereStr,String str1,String str2,String loginId){
	    String count="";
	    String sum="";
		DataBase db = new DataBase();
		ElectricFeesFormBean bean1 = new ElectricFeesFormBean();
		String fzzdstr="";
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		DecimalFormat df1 = new DecimalFormat("#.0000");
		try {
			db.connectDb();
			fzzdstr = getFuzeZdid(db,loginId);
			
			StringBuffer strall1 = new StringBuffer();
			StringBuffer strall2 = new StringBuffer();
		    strall1.append("SELECT COUNT(*),SUM(ACTUALPAY) "+
		  			"FROM  DIANFEIVIEW EF,ZHANDIAN ZD,DIANBIAO D,DIANDUVIEW AD WHERE ZD.ID=D.ZDID AND ZD.QYZT='1' AND EF.LIUCHENGHAO IS NOT NULL AND D.DBID=AD.AMMETERID_FK AND AD.AMMETERDEGREEID =EF.AMMETERDEGREEID_FK AND EF.CITYAUDIT='1'  "+whereStr+str1+" " +
					"AND (D.DFZFLX='dfzflx01' OR D.DFZFLX='dfzflx03') AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+")");
		    
		    strall2.append("SELECT COUNT(*),SUM(EF.MONEY) "+
		    		"FROM  YUFUFEIVIEW EF,ZHANDIAN ZD,DIANBIAO D WHERE ZD.ID=D.ZDID AND ZD.QYZT='1' AND EF.LIUCHENGHAO IS NOT NULL AND D.DBID=EF.PREPAYMENT_AMMETERID AND EF.CITYAUDIT='1'  "+whereStr+str2+" " +
		    		"AND (D.DFZFLX='dfzflx02' OR D.DFZFLX='dfzflx04' OR D.DFZFLX = 'dfzflx03') AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+")");

			rs1 = db.queryAll(strall1.toString());
		    System.out.println("财务电费审核条数、实际电费金额总和----月结"+strall1.toString());
		    String count1="",count2="";
		    String sum1="",sum2="";
            while(rs1.next()){
            	count1 = rs1.getString(1)!=null?rs1.getString(1):"0";
            	sum1 = rs1.getString(2)!=null?rs1.getString(2):"0";
            }		    
			rs2 = db.queryAll(strall2.toString());
		    System.out.println("财务电费审核条数、实际电费金额总和----预付费"+strall2.toString());
            while(rs2.next()){
            	count2 = rs2.getString(1)!=null?rs2.getString(1):"0";
            	sum2 = rs2.getString(2)!=null?rs2.getString(2):"0";
            }
            count =  (new Integer(Integer.parseInt(count1)+Integer.parseInt(count2))).toString();
            sum = df1.format(Double.parseDouble(sum1)+Double.parseDouble(sum2));
            
            bean1.setCount(count);
            bean1.setActualpay(sum);
		}
		catch (DbException de) {
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
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return bean1;
	}
/*	//财务电费审核实际电费金额总和
	public String getCountGreecwdfsh(String whereStr,String loginId,String str){
	    String count="";
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
				fzzdstr = getFuzeZdid(db,loginId);
			
			StringBuffer strall = new StringBuffer();
		     strall.append("select sum(ACTUALPAY)" 
			  			+"from  electricview ef,zhandian zd,dianbiao d,dianduview ad where zd.id=d.zdid and zd.qyzt='1' and ef.liuchenghao is not null and d.dbid=ef.dbid and ef.AMMETERDEGREEID_FK=ad.AMMETERDEGREEID(+) and ef.manualauditstatus = '1' and ef.cityaudit='1'  "+whereStr+str+" " +
						"and ((zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")");
			System.out.println("财务电费审核实际电费金额总和"+strall.toString());
			rs = db.queryAll(strall.toString());
           
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
	}*/
	
	//财务电费审核导出
	public synchronized List<ElectricFeesFormBean> getPageDataCheckdaochu(String whereStr,
			String loginId,String str1,String str2) {		
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql1 = "";
		String sql2 = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql1="SELECT EF.LIUCHENGHAO,ZD.JZNAME,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),D.DBID,D.DLLX,(SELECT NAME FROM INDEXS WHERE CODE=D.YDSB AND TYPE = 'YDSB') YDSB,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = ZD.PROPERTY AND TYPE='ZDSX') AS PROPERTY," +
			"(SELECT I.NAME FROM INDEXS  I WHERE I.CODE=D.DFZFLX AND I.TYPE='dfzflx')AS DFZFLX," +
			"(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"+
			"(SELECT NAME FROM INDEXS WHERE CODE=EF.NOTETYPEID AND TYPE = 'PJLX') AS NOTETYPEID,"+
			"EF.ELECTRICFEE_ID,EF.DFUUID,EF.ACTUALPAY, EF.AUTOAUDITSTATUS,EF.NOTENO,EF.ENTRYTIME,EF.MANUALAUDITSTATUS, " +
			"(CASE  WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END) AS xian,"+
            " (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE ''END) AS xiaoqu,EF.PJJE "+
			"FROM  DIANFEIVIEW EF,ZHANDIAN ZD,DIANBIAO D,DIANDUVIEW AD WHERE ZD.ID=D.ZDID AND ZD.QYZT='1' AND EF.LIUCHENGHAO IS NOT NULL AND D.DBID=AD.AMMETERID_FK AND AD.AMMETERDEGREEID =EF.AMMETERDEGREEID_FK  AND EF.CITYAUDIT='1'  "+whereStr+str1+" " +
			" AND (D.DFZFLX = 'dfzflx01' OR D.DFZFLX = 'dfzflx03') AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+")";
			
			sql2="SELECT EF.LIUCHENGHAO,ZD.JZNAME,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),D.DBID,D.DLLX,(SELECT NAME FROM INDEXS WHERE CODE=D.YDSB AND TYPE = 'YDSB') YDSB,(SELECT T.NAME FROM INDEXS T WHERE T.CODE = ZD.PROPERTY AND TYPE='ZDSX') AS PROPERTY," +
			"(SELECT I.NAME FROM INDEXS  I WHERE I.CODE=D.DFZFLX AND I.TYPE='dfzflx')AS DFZFLX," +
			"(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"+
			"(SELECT NAME FROM INDEXS WHERE CODE=EF.NOTETYPEID AND TYPE = 'PJLX') AS NOTETYPEID,"+
			"EF.UUID,EF.MONEY,EF.NOTENO,EF.ENTRYTIME,EF.FINANCEAUDIT, " +
			"(CASE  WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END) AS Xian, "+
            " (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE ''END) AS xiaoqu,EF.PJJE "+
			"FROM  YUFUFEIVIEW EF,ZHANDIAN ZD,DIANBIAO D WHERE ZD.ID=D.ZDID AND ZD.QYZT='1' AND EF.LIUCHENGHAO IS NOT NULL AND D.DBID=EF.PREPAYMENT_AMMETERID  AND EF.CITYAUDIT='1'  "+whereStr+str2+" " +
			"AND (D.DFZFLX = 'dfzflx02' OR D.DFZFLX = 'dfzflx04' OR D.DFZFLX = 'dfzflx03') AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+")";

			db.connectDb();
			System.out.println("财务审核电费列表导出-----月结："+sql1.toString());
			rs = db.queryAll(sql1.toString());
//			Query query = new Query();
//			list = query.query(rs);
			while(rs.next()){
				ElectricFeesFormBean feesbeana=new ElectricFeesFormBean();
				feesbeana.setLiuchenghao(rs.getString(1)!=null?rs.getString(1):"");
				feesbeana.setJzname(rs.getString(2)!=null?rs.getString(2):"");
				feesbeana.setAccountmonth(rs.getString(3)!=null?rs.getString(3):"");
				feesbeana.setDbid(rs.getString(4)!=null?rs.getString(4):"");
				feesbeana.setDllx(rs.getString(5)!=null?rs.getString(5):"");
				feesbeana.setYdsb(rs.getString(6)!=null?rs.getString(6):"");
				feesbeana.setProperty(rs.getString(7)!=null?rs.getString(7):"");
				feesbeana.setDfzflx(rs.getString(8)!=null?rs.getString(8):"");
				feesbeana.setStationtype(rs.getString(9)!=null?rs.getString(9):"");
				feesbeana.setNotetypeid(rs.getString(10)!=null?rs.getString(10):"");
				feesbeana.setElectricfeeId(rs.getString(11)!=null?rs.getString(11):"");
				feesbeana.setDfuuid(rs.getString(12)!=null?rs.getString(12):"");
				feesbeana.setActualpay(rs.getString(13)!=null?rs.getString(13):"");
				feesbeana.setAutoauditstatus(rs.getString(14)!=null?rs.getString(14):"");
				feesbeana.setNoteno(rs.getString(15)!=null?rs.getString(15):"");
				feesbeana.setEntrytime(rs.getString(16)!=null?rs.getString(16):"");
				feesbeana.setManualauditstatus(rs.getString(17)!=null?rs.getString(17):"");
				feesbeana.setXian(rs.getString(18)!=null?rs.getString(18):"");
				feesbeana.setXiaoqu(rs.getString(19)!=null?rs.getString(19):"");
				feesbeana.setPjjedf(rs.getString("PJJE")!=null?rs.getString("PJJE"):"");
				list.add(feesbeana);
			}
			rs1 = db.queryAll(sql2.toString());
			System.out.println("财务审核电费列表导出-----预付费："+sql2.toString());
			while(rs1.next()){
				ElectricFeesFormBean feesbeana=new ElectricFeesFormBean();
				feesbeana.setLiuchenghao(rs1.getString(1)!=null?rs1.getString(1):"");
				feesbeana.setJzname(rs1.getString(2)!=null?rs1.getString(2):"");
				feesbeana.setAccountmonth(rs1.getString(3)!=null?rs1.getString(3):"");
				feesbeana.setDbid(rs1.getString(4)!=null?rs1.getString(4):"");
				feesbeana.setDllx(rs1.getString(5)!=null?rs1.getString(5):"");
				feesbeana.setYdsb(rs1.getString(6)!=null?rs1.getString(6):"");
				feesbeana.setProperty(rs1.getString(7)!=null?rs1.getString(7):"");
				feesbeana.setDfzflx(rs1.getString(8)!=null?rs1.getString(8):"");
				feesbeana.setStationtype(rs1.getString(9)!=null?rs1.getString(9):"");
				feesbeana.setNotetypeid(rs1.getString(10)!=null?rs1.getString(10):"");
				feesbeana.setDfuuid(rs1.getString(11)!=null?rs1.getString(11):"");
				feesbeana.setActualpay(rs1.getString(12)!=null?rs1.getString(12):"");
				feesbeana.setNoteno(rs1.getString(13)!=null?rs1.getString(13):"");
				feesbeana.setEntrytime(rs1.getString(14)!=null?rs1.getString(14):"");
				feesbeana.setManualauditstatus(rs1.getString(15)!=null?rs1.getString(15):"");
				feesbeana.setXian(rs1.getString(16)!=null?rs1.getString(16):"");
				feesbeana.setXiaoqu(rs1.getString(17)!=null?rs1.getString(17):"");
				feesbeana.setPjjedf(rs1.getString("PJJE")!=null?rs1.getString("PJJE"):"");
				list.add(feesbeana);
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	// 自动审核电费列表
	public synchronized List<ElectricFeesFormBean> getPageDataCheck2(String whereStr,
			String loginId) {
		List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
		System.out.println("ElectricFeesBean审核电费列表条件:" + whereStr);
		//ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		ResultSet rs = null;
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select zd.jzname,to_char(ef.accountmonth,'yyyy-mm'),db.dbid,db.dllx electriccurrenttype_ammeter,(select name from indexs where code=db.ydsb and type = 'YDSB') usageofelectypeid_ammeter,ef.ELECTRICFEE_ID, ef.unitprice,ef.FLOATPAY,ef.ACTUALPAY,ef.AUTOAUDITSTATUS,"
		        	+"(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"+
		        	"(select name from indexs where code=zd.property and type = 'ZDSX') as property,"+
		        	"(select name from indexs where code=zd.jztype and type = 'ZDLX') as jztype,"+
		        	"(select name from indexs where code=ef.notetypeid and type = 'PJLX') as NOTETYPEID,"+
					 "ef.NOTENO,ef.PAYOPERATOR,to_char(ef.PAYDATETIME,'yyyy-mm-dd'),ef.entrytime,ad.AMMETERDEGREEID,ef.autoauditdescription,ad.autoauditdescription as dlshenhe,ad.AUTOAUDITSTATUS as dlshenhezhuangtai,ef.manualauditstatus, case when ad.amuuid is null then '0' else  '1' end feebz "
					+ "from zhandian zd,dianbiao db,dianduview ad,dianfeiview ef  "
					+ "where zd.id=db.zdid and zd.qyzt='1' and db.dbid=ad.ammeterid_fk "
					+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and (ef.manualauditstatus = '0' or ef.manualauditstatus is null)"
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ") ";
               System.out.println("自动审核电费列表"+sql.toString());
               rs=db.queryAll(sql);
               while(rs.next()){
            	   ElectricFeesFormBean formbean=new ElectricFeesFormBean();
            	   formbean.setJzname(rs.getString(1));
            	   formbean.setAccountmonth(rs.getString(2));
            	   formbean.setDbid(rs.getString(3));
            	   formbean.setDllx(rs.getString(4));
            	   formbean.setYdsb(rs.getString(5));
            	   formbean.setElectricfeeId(rs.getString(6));
            	   formbean.setUnitprice(rs.getString(7));
            	   formbean.setFloatpay(rs.getString(8));
            	   formbean.setActualpay(rs.getString(9));
            	   formbean.setAutoauditstatus(rs.getString(10));
            	   formbean.setStationtype(rs.getString(11));
            	   formbean.setProperty(rs.getString(12));
            	   formbean.setJztype(rs.getString(13));
            	   formbean.setNotetypeid(rs.getString(14));
            	   formbean.setNoteno(rs.getString(15));
            	   formbean.setPayoperator(rs.getString(16));
            	   formbean.setPaydatetime(rs.getString(17));
            	   formbean.setEntrytime(rs.getString(18));
            	   formbean.setAmmeterdegreeid(rs.getString(19));
            	   formbean.setAutoauditdescription(rs.getString(20));
            	   formbean.setDlshenhe(rs.getString(21));
            	   formbean.setDlshenhezhuangtai(rs.getString(22));
            	   formbean.setManualauditstatus(rs.getString(23));
            	  list.add(formbean);
            	 
               }
               
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	//自动电费审核条数
	public synchronized List<ElectricFeesFormBean> getCountt(String whereStr,String loginId){
		String sql = "";
	    String count="";
	    List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
		DataBase db = new DataBase();
		String fzzdstr="";
		ResultSet rs = null;
		try {
			db.connectDb();
			try {
			fzzdstr = getFuzeZdid(db,loginId);		
			StringBuffer strall = new StringBuffer();
		      strall.append(" select count(*),sum(ef.actualpay) "+
						"from zhandian zd,dianbiao db,dianduview ad,dianfeiview ef  "
						+ "where zd.id=db.zdid and zd.qyzt='1' and db.dbid=ad.ammeterid_fk "
						+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and (ef.manualauditstatus = '0' or ef.manualauditstatus is null)"
						+ whereStr
						+ " "
						+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
						+ loginId + "'))" + fzzdstr + ")");
			System.out.println("自动电费审核条数"+strall.toString());
			rs = db.queryAll(strall.toString());
	     
	      		 while(rs.next()){
	      			 ElectricFeesFormBean formbean=new ElectricFeesFormBean();
	      			  formbean.setCount(rs.getString(1));
	      			 formbean.setActualpay(rs.getString(2));
	      			 list.add(formbean);
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
	
	//自动电费审核实际用电费金额总和	
	public String getCountGreezddf(String whereStr,String loginId,String str){
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
		      strall.append("select sum(ACTUALPAY) " +
						"from zhandian zd,dianbiao db,dianduview ad,dianfeiview ef  "
						+ "where zd.id=db.zdid and zd.qyzt='1' and db.dbid=ad.ammeterid_fk "
						+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and (ef.manualauditstatus = '0' or ef.manualauditstatus is null)"
						+ whereStr+str
						+ " "
						+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
						+ loginId + "'))" + fzzdstr + ")");
			System.out.println("自动电费审核实际用电费金额总和	"+strall.toString());
			rs = db.queryAll(strall.toString());
	       
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
	
	//自动电费审核导出
	public synchronized List<ElectricFeesFormBean> getPageDatadfdc(String whereStr,String loginId) {
		
		System.out.println("AmmeterDegreeBean-getPageData:"+whereStr);
	    ArrayList list = new ArrayList();
	    CTime ct = new CTime();
	    String kjnd = ct.formatShortDate().substring(0, 4);
	    String sql = "";
	    ResultSet rs = null;
	    String fzzdstr = "";
	    DataBase db = new DataBase();
	    //调用负责站点条件函数
	    try {
			fzzdstr = getFuzeZdid(db,loginId);
			
			sql = "select zd.jzname,to_char(ef.accountmonth,'yyyy-mm'),db.dbid,db.dllx electriccurrenttype_ammeter,(select name from indexs where code=db.ydsb and type = 'YDSB') usageofelectypeid_ammeter,ef.ELECTRICFEE_ID, ef.unitprice,ef.FLOATPAY,ef.ACTUALPAY,ef.AUTOAUDITSTATUS,"
	        	+"(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"+
	        	"(select name from indexs where code=zd.property and type = 'ZDSX') as property,"+
	        	"(select name from indexs where code=zd.jztype and type = 'ZDLX') as jztype,"+
	        	"(select name from indexs where code=ef.notetypeid and type = 'PJLX') as NOTETYPEID,"+
				 "ef.NOTENO,ef.PAYOPERATOR,to_char(ef.PAYDATETIME,'yyyy-mm-dd'),ef.entrytime,ad.AMMETERDEGREEID,ef.autoauditdescription,ad.autoauditdescription as dlshenhe,ad.AUTOAUDITSTATUS as dlshenhezhuangtai,ef.manualauditstatus, case when ad.amuuid is null then '0' else  '1' end feebz "
				+ "from zhandian zd,dianbiao db,dianduview ad,dianfeiview ef  "
				+ "where zd.id=db.zdid and zd.qyzt='1' and db.dbid=ad.ammeterid_fk "
				+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and (ef.manualauditstatus = '0' or ef.manualauditstatus is null)"
				+ whereStr
				+ " "
				+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
				+ loginId + "'))" + fzzdstr + ") order by ENTRYTIME desc,JZNAME desc";
			System.out.println("自动电费审核导出"+sql.toString());
			rs=db.queryAll(sql);
			while(rs.next()){
				 ElectricFeesFormBean formbean=new ElectricFeesFormBean();
          	   formbean.setJzname(rs.getString(1));
          	   formbean.setAccountmonth(rs.getString(2));
          	   formbean.setDbid(rs.getString(3));
          	   formbean.setDllx(rs.getString(4));
          	   formbean.setYdsb(rs.getString(5));
          	   formbean.setElectricfeeId(rs.getString(6));
          	   formbean.setUnitprice(rs.getString(7));
          	   formbean.setFloatpay(rs.getString(8));
          	   formbean.setActualpay(rs.getString(9));
          	   formbean.setAutoauditstatus(rs.getString(10));
          	   formbean.setStationtype(rs.getString(11));
          	   formbean.setProperty(rs.getString(12));
          	   formbean.setJztype(rs.getString(13));
          	   formbean.setNotetypeid(rs.getString(14));
          	   formbean.setNoteno(rs.getString(15));
          	   formbean.setPayoperator(rs.getString(16));
          	   formbean.setPaydatetime(rs.getString(17));
          	   formbean.setEntrytime(rs.getString(18));
          	   formbean.setAmmeterdegreeid(rs.getString(19));
          	   formbean.setAutoauditdescription(rs.getString(20));
          	   formbean.setDlshenhe(rs.getString(21));
          	   formbean.setDlshenhezhuangtai(rs.getString(22));
          	   formbean.setManualauditstatus(rs.getString(23));
          	  list.add(formbean);
				
			}
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally {
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

	
	
	
	
	// 人工审核电费列表
	/**
	 * @param whereStr
	 * @param loginId
	 * @return
	 * @update WangYiXiao 2014-4-21 电表用电量=（本次读数-上次读数）*倍率 
	 * @update WangYiXiao2014-8-8 不能查出 加强单子tbtzsq != 1 or tbtzsq is null
	 * 2update WangYiXiao 2014-8-11 去掉 tbtzsq != 1 or tbtzsq is null
	 */
	public synchronized List<ElectricFeesFormBean>  getPageDataCheck123(String whereStr,
			String str,String loginId,String lrbz1,String lrbz2) {		
		List<ElectricFeesFormBean>  list = new ArrayList<ElectricFeesFormBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		Connection conn = null;
		Statement sta = null;
		ResultSet rs = null;
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			sql = "SELECT ZD.ID,ZD.JZNAME,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),AD.BLHDL,EF.ACTUALPAY,EF.UNITPRICE,EF.QSDBDL,EF.EDHDL,"
				+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				+ "RTNAME(ZD.PROPERTY),AM.DBID,AM.DBNAME,AD.LASTDEGREE,AD.THISDEGREE,to_char(AD.LASTDATETIME,'yyyy-mm-dd'),to_char(AD.THISDATETIME,'yyyy-mm-dd'),"
				+ "AD.FLOATDEGREE,AD.MEMO,EF.FLOATPAY,EF.MEMO,"
				+ "(CASE WHEN ZD.XIAN IS NOT NULL THEN RNDIQU(ZD.XIAN) ELSE '' END) || (CASE WHEN ZD.XIAOQU IS NOT NULL THEN RNDIQU(ZD.XIAOQU) ELSE '' END) AS SZDQ,"
				+ "EF.ELECTRICFEE_ID,EF.DFUUID,EF.CITYZRAUDITSTATUS,RTNAME(AM.DFZFLX),AD.DEDHDL,AD.CSDB,'1' AS DFBZW,"
				+ " rndiqu(ZD.xian),rtname(ZD.gdfs),EF.JSZQ,EF.MANUALAUDITSTATUS,EF.CITYAUDIT,AM.BEILV,EF.NOTETIME,EF.NOTENO,RTNAME(EF.NOTETYPEID)," 
				+ " EF.PAYDATETIME,AD.AUTOAUDITSTATUS AS DLSH, EF.AUTOAUDITDESCRIPTION, AD.AUTOAUDITDESCRIPTION AS DLSHZT, EF.AUTOAUDITSTATUS, AD.ACTUALDEGREE, EF.PJJE, AD.TBTZSQ, AD.AMMETERDEGREEID,"
				+ " AD.LASTELECFEES, AD.LASTELECDEGREE,AD.LASTUNITPRICEOLD,AD.LASTFLOATDEGREE,AD.LASTACTUALDEGREE,EF.CHANGEVALUE,EF.ACTUALLINELOSSVALUE,AD.BLHDL/EF.JSZQ BZRJ"
				+ " FROM ZHANDIAN ZD,DIANBIAO AM,AMMETERDEGREES AD,ELECTRICFEES EF  "
				+ " WHERE ZD.ID=AM.ZDID  AND AM.DBID=AD.AMMETERID_FK "
				+ " AND AD.AMMETERDEGREEID=EF.AMMETERDEGREEID_FK AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "
				+ whereStr+lrbz1
				+ " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
				+ loginId + "'))" + fzzdstr + ") ";
			
			sql2 = "SELECT ZD.ID,ZD.JZNAME,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),EF.DIANLIANG,EF.MONEY,EF.DANJIA,EF.QSDBDL,EF.EDHDL,"
				+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				+ "RTNAME(ZD.PROPERTY),AM.DBID,AM.DBNAME,EF.CITYZRAUDITSTATUS,RTNAME(AM.DFZFLX),EF.ID,EF.UUID,EF.DEDHDL,EF.CSDB,'2' as dfbzw,"
				+ " rndiqu(ZD.xian),rtname(ZD.gdfs),EF.JSZQ,EF.MANUALAUDITSTATUS,EF.CITYAUDIT,AM.BEILV,EF.NOTETIME,EF.NOTENO,RTNAME(EF.NOTETYPEID),"
				+ " EF.PAYDATETIME,to_char(EF.STARTDATE,'yyyy-mm-dd'),to_char(EF.ENDDATE,'yyyy-mm-dd'),'1' AS DLSH, EF.AUTOAUDITDESCRIPTION, '' AS DLSHZT, EF.AUTOAUDITSTATUS, EF.DIANLIANG, EF.PJJE, EF.AMMETERDEGREEID_FK, EF.LASTYUE,EF.DIANLIANG/EF.JSZQ BZRJ,EF.FINANCEAUDIT"				
				+ " FROM ZHANDIAN ZD, DIANBIAO AM, YUFUFEIVIEW EF"
		        + " WHERE ZD.ID = AM.ZDID AND AM.DBID = EF.PREPAYMENT_AMMETERID  AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1'  "
		        + str+lrbz2
		        + " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
		        + loginId + "'))" + fzzdstr + ") ";//
		       
            System.out.println("人工审核电费列表查询"+sql.toString());
            System.out.println("人工审核电费列表查询"+sql2.toString());
            db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			
			rs = sta.executeQuery(sql.toString());//运行sql
			while(rs.next()){
				System.out.println("sql    执行");
				String zdid = rs.getString(1) != null ? rs.getString(1) : "";// 站点ID
				
				/*sql1 = "SELECT ROUND(SUM(AM.THISDEGREE - AM.LASTDEGREE) / SUM(AM.GLZQ),2) GLBRJL FROM DIANBIAO D, AMMETERDEGREES AM, (SELECT TO_CHAR(MAX(TO_DATE((T.THISDATETIME), 'YYYY-MM-DD')), 'YYYY-MM-DD') THISDATETIME,"
		               +"T.AMMETERID_FK FROM AMMETERDEGREES T WHERE T.MANUALAUDITSTATUS = '1' AND T.AMMETERID_FK IN (SELECT DBID FROM ZHANDIAN Z, DIANBIAO D WHERE Z.ID = D.ZDID AND D.DBYT = 'dbyt03' AND Z.ID = '"
		               +zdid+"')GROUP BY AMMETERID_FK) DD WHERE D.DBID = AM.AMMETERID_FK AND DD.AMMETERID_FK = D.DBID AND AM.THISDATETIME = DD.THISDATETIME AND AM.MANUALAUDITSTATUS = '1'";
				DataBase dba = new DataBase();
				Connection conna = dba.getConnection();
				Statement staa = conna.createStatement();
				 System.out.println("人工审核电费管理日均电量查询"+sql1.toString());
				ResultSet rsa = staa.executeQuery(sql1);
				String glbrjl="";
				if(rsa.next()){
					glbrjl=rsa.getString("GLBRJL");//管理表日均电量
				}
				dba.free(rsa, staa, conna);*/
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";// 站点名称
				String accountmonth = rs.getString(3) != null ? rs.getString(3)
						: "";// 报账月份
				String blhdl = rs.getString(4) != null ? rs.getString(4) : "";// 实际用电量double
				String actualpay = rs.getString(5) != null ? rs.getString(5)
						: "";// 实际电费
				String unitprice = rs.getString(6) != null ? rs.getString(6)
						: "";// 单价
				String qsdbdl = rs.getString(7) != null ? rs.getString(7) : "";// 省定标
				String edhdl = rs.getString(8) != null ? rs.getString(8) : "";// 市定标
				String stationtype = rs.getString(9) != null ? rs.getString(9)
						: "";// 站点类型
				String property = rs.getString(10) != null ? rs.getString(10)
						: "";// 站点属性
				String dbid = rs.getString(11) != null ? rs.getString(11) : "";// 电表id
				String dbname = rs.getString(12) != null ? rs.getString(12)
						: "";// 电表名称
				String lastdegree = rs.getString(13) != null ? rs.getString(13)
						: "";// 起始电表数
				String thisdegree = rs.getString(14) != null ? rs.getString(14)
						: "";// 结束电表数
				String lastdatetime = rs.getString(15) != null ? rs
						.getString(15) : "";// 上次抄表时间
				String thisdatetime = rs.getString(16) != null ? rs
						.getString(16) : "";// 本次抄表时间
				String floatdegree = rs.getString(17) != null ? rs
						.getString(17) : "";// 电量调整
				String memo = rs.getString(18) != null ? rs.getString(18) : "";// 电量调整备注
				String floatpay = rs.getString(19) != null ? rs.getString(19)
						: "";// 电费调整
				String memo1 = rs.getString(20) != null ? rs.getString(20) : "";// 电费调整备注
				String szdq = rs.getString(21) != null ? rs.getString(21) : "";// 所在地区
				String electricfee_id = rs.getString(22) != null ? rs
						.getString(22) : "";// 电费id
				String uuid = rs.getString(23) != null ? rs.getString(23) : "";// uuid
				String cityzrauditstatus = rs.getString(24) != null ? rs
						.getString(24) : "";// 市级主任审核状态
				String dfzflx = rs.getString(25) != null ? rs.getString(25)
						: "";// 电费支付类型
				String dedhdl = rs.getString(26) != null ? rs.getString(26)
						: "";// 超市定标
				String csdb = rs.getString(27) != null ? rs.getString(27) : "";// 超省定标
				String dfbzw = rs.getString(28) != null ? rs.getString(28) : "";// 电费标志位

				String quxian = rs.getString(29) != null ? rs.getString(29)
						: "";// 区县
				String gdfs = rs.getString(30) != null ? rs.getString(30) : "";// 供电方式
				String jszq = rs.getString(31) != null ? rs.getString(31) : "";// 结算周期
				System.out.println(jszq+"  ffffffff");
				String rgshzt = rs.getString(32) != null ? rs.getString(32)
						: "";// 人工审核状态
				String sjshzt = rs.getString(33) != null ? rs.getString(33)
						: "";// 市级审核状态
				String beilv = rs.getString(34) != null ? rs.getString(34) : "";// 倍率
				String pjsj = rs.getString(35) != null ? rs.getString(35) : "";// 票据时间
				String pjbh = rs.getString(36) != null ? rs.getString(36) : "";// 票据编号
				String pjlx = rs.getString(37) != null ? rs.getString(37) : "";// 票据类型
				String jfsj = rs.getString(38) != null ? rs.getString(38) : "";// 交费时间
				String dlsh = rs.getString("DLSH") != null ? rs.getString("DLSH") : "";
				String autoauditdescription = rs.getString("AUTOAUDITDESCRIPTION") != null ? rs.getString("AUTOAUDITDESCRIPTION") : "";
				String dlshzt = rs.getString("DLSHZT") != null ? rs.getString("DLSHZT") : "";
				String autoauditstatus = rs.getString("AUTOAUDITSTATUS") != null ? rs.getString("AUTOAUDITSTATUS") : "";
				String actualdegree = rs.getString("ACTUALDEGREE") != null ? rs.getString("ACTUALDEGREE") : "";
				String pjje = rs.getString("PJJE") != null ? rs.getString("PJJE") : "";
				String tbtzsq = rs.getString("TBTZSQ") != null ? rs.getString("TBTZSQ") : "";//特别调整申请
				String dlid = rs.getString("AMMETERDEGREEID") != null ? rs.getString("AMMETERDEGREEID") : "";//电量ID
				String lastelecfees = rs.getString("LASTELECFEES")!=null?rs.getString("LASTELECFEES"):""; //上期电费
				
				String lastelecdegree = rs.getString("LASTELECDEGREE")!=null?rs.getString("LASTELECDEGREE"):""; //上期电量
				String lastunitprice = rs.getString("LASTUNITPRICEOLD")!=null?rs.getString("LASTUNITPRICEOLD"):""; //上期单价
				String lastactualdegree = rs.getString("LASTACTUALDEGREE")!=null?rs.getString("LASTACTUALDEGREE"):"0.00"; //上期电表用电量
				String lastfloatdegree = rs.getString("LASTFLOATDEGREE")!=null?rs.getString("LASTFLOATDEGREE"):"0.00"; //上期电量调整
				String changevalue = rs.getString("CHANGEVALUE")!=null?rs.getString("CHANGEVALUE"):"0"; //变损值
				String actuallinelossvalue = rs.getString("ACTUALLINELOSSVALUE")!=null?rs.getString("ACTUALLINELOSSVALUE"):"0"; //实际线损值
				String bzrj = rs.getString("BZRJ")!=null?rs.getString("BZRJ"):"0"; //报账日均电量
				
				String dbydl = "";// 电表用电量
				String bzydl = "";// 报账用电量


				/* 新字段的一些计算 */
				//本次电表读数 - 上次电表读数
				double actdegree = Format.d2(Double.parseDouble(thisdegree) - Double.parseDouble(lastdegree)); 
				// 计算电表用电量
				if(beilv==null||"".equals(beilv)){
					beilv="1";
				}
				Double dbdl = 0.0;
				//double sjydl = Double.parseDouble(Format.str2(actualdegree));
				dbdl = actdegree * Double.parseDouble(beilv);
				dbydl = Double.toString(Format.d2(dbdl));
				
				//超省标电量度：报账电量-省定标电量；
				//超市标电量度：报账电量-市定标电量
				String csbdld = String.valueOf(Format.d2(Format.str_d(blhdl) - Format.str_d(qsdbdl) *  Format.str_d(jszq)));
				String cshibdld = String.valueOf(Format.d2(Format.str_d(blhdl) - Format.str_d(edhdl) *  Format.str_d(jszq)));

				//------计算 省定标电费=省定标电量*单价*结算周期------
				qsdbdl = Format.str2(qsdbdl);
				unitprice = Format.str4(unitprice);
				String jszq2 = Format.str2(jszq);
				double sdbdf = Double.parseDouble(qsdbdl) * Double.parseDouble(unitprice) * Double.parseDouble(jszq2);
				sdbdf = Format.d2(sdbdf);

				//------计算 超省定标电费额=实际电费-省定标电费------
				actualpay = Format.str2(actualpay);
				double csdbdfe = Double.parseDouble(actualpay) - sdbdf;
				csdbdfe = Format.d2(csdbdfe);
				//--超省标电费绝对值
				double csdbdfjdz = Math.abs(csdbdfe);
				

				//------计算 超省定标电费比值=超省定标电费额/省定标电费*100------
				
				double csdbdfbz = 0;
				try {
					csdbdfbz = csdbdfe / sdbdf * 100;
					csdbdfbz = Format.d2(csdbdfbz);
				} catch (Exception e) {
					csdbdfbz = 0.00;
				}
				
				//------计算 市定标电费=市定标电量*单价*结算周期------
				edhdl = Format.str2(edhdl);
				double sdbd = Double.parseDouble(edhdl) * Double.parseDouble(unitprice) * Double.parseDouble(jszq2);
				sdbd = Format.d2(sdbd);

				//------计算 超市定标电费额=实际电费-市定标电费------
				actualpay = Format.str2(actualpay);
				double csdbdf = Double.parseDouble(actualpay) - sdbd;
				csdbdf = Format.d2(csdbdf);
				//--超市标电费绝对值
				double cshibdfjdz = Math.abs(csdbdf);
				

				//------计算 超市定标电费比值=超市定标电费额/市定标电费*100------
				
				double csdbdfb = 0;
				try {
					csdbdfb = csdbdf / sdbd * 100;
					csdbdfb = Format.d2(csdbdfb);
				} catch (Exception e) {
					csdbdfb = 0.00;
				}
				
				//电量调增*倍率、上期电表用电量、上期电量调整*倍率列
				String bv = "";
				if(Format.str_d(beilv)==0){//倍率为空 或 为 0
					bv="1";
				}else{
					bv = beilv;
				}
				String floatdegreeandbl = String.valueOf(Format.d2(Format.str_d(floatdegree)*Format.str_d(bv)));//电量调增*倍率
				String lastfloatdegreeandbl = String.valueOf(Format.d2(Format.str_d(lastfloatdegree)*Format.str_d(bv)));//上期电量调整*倍率列
				String changeandlineandbl = String.valueOf(Format.d2((Format.str_d(changevalue) + Format.str_d(actuallinelossvalue)) * Format.str_d(bv)));//线变损电量
				
				ElectricFeesFormBean bean=new ElectricFeesFormBean();
				
				bean.setGlbrjl("");
				bean.setZdid(zdid);
				bean.setZdname(zdname);
				bean.setAccountmonth(accountmonth);
				bean.setBlhdl(Format.str2(blhdl));
				bean.setActualpay(Format.str2(actualpay));
				bean.setUnitprice(unitprice);
				bean.setCsbl(Format.str2(csdb));
				bean.setCsbbl(Format.str2(dedhdl));
				bean.setQsdbdl(qsdbdl);
				bean.setEdhdl(edhdl);
				System.out.println(edhdl+"   "+qsdbdl+"  ffffffffffffff");
				bean.setStationtype(stationtype);
				bean.setProperty(property);
				bean.setDbid(dbid);
				bean.setDbname(dbname);
				bean.setLastdegree(lastdegree);
				bean.setThisdegree(thisdegree);
				bean.setLastdatetime(lastdatetime);
				bean.setThisdatetime(thisdatetime);
				bean.setFloatdegree(floatdegree);
				bean.setMemo(memo);
				bean.setFloatpay(floatpay);
				bean.setMemo1(memo1);
				bean.setSzdq(szdq);
				bean.setElectricfeeid(electricfee_id);
				bean.setUuid(uuid);
				bean.setCityzrauditstatus(cityzrauditstatus);
				bean.setDfzflx(dfzflx);
				bean.setDfbzw(dfbzw);

				bean.setQuxian(quxian);
				bean.setGdfs(gdfs);
				bean.setJszq(jszq);
				bean.setRgshzt(rgshzt);
				bean.setCwsh(rgshzt);
				bean.setSjshzt(sjshzt);
				bean.setBeilv(beilv);
				bean.setPjsj(pjsj);
				bean.setPjbh(pjbh);
				bean.setPjlx(pjlx);
				bean.setJfsj(jfsj);
				bean.setDbydl(dbydl);
				bean.setBzydl(bzydl);
				bean.setCsdbdfe(Double.toString(csdbdfe));
				bean.setCsdbdfbz(csdbdfbz + "%");
				bean.setDlsh(dlsh);
				bean.setAutoauditdescription(autoauditdescription);
				bean.setDlshzt(dlshzt);
				bean.setAutoauditstatus(autoauditstatus);
				bean.setPjje(Double.parseDouble(Format.str2(pjje)));
				bean.setLastelecfees(lastelecfees); //上期电费
				bean.setLastelecdegree(lastelecdegree); //上期电量
				bean.setLastunitprice(lastunitprice); //上期单价
				System.out.println(lastunitprice+"       //上期单价");
				bean.setLastyue("");//上期余额   不是预付费就不添加
				bean.setLastfloatdegreeandbl(Format.str2(lastfloatdegreeandbl));//上期电量调整*倍率
				bean.setLastactualdegree(Format.str2(lastactualdegree));//上期电表用电量
				bean.setFloatdegreeandbl(Format.str2(floatdegreeandbl));//电量调整*倍率
				bean.setCshibdfbl(csdbdfb + "%");//超市标电费比例
				bean.setCsbdfjdz(String.valueOf(csdbdfjdz));//超省标电费绝对值
				bean.setCshibdjdz(String.valueOf(cshibdfjdz));//超市标电费绝对值
				bean.setCsbdld(csbdld);//超省标电量
				bean.setCshibdld(cshibdld);//超市标电量
				bean.setLineandchangeandbl(changeandlineandbl);//线变损电量
				bean.setBzrj(Format.str2(bzrj));//报账日均电量
				
				
				
				bean.setBiaozhi("1");
				bean.setSort("电费");
				
				bean.setTbtzsq(tbtzsq);
				bean.setDlid(dlid);
				
				list.add(bean);
				}
			
			rs = sta.executeQuery(sql2.toString());//运行sql
			while(rs.next()){
				System.out.println("sql2    执行");
				String glbrjl="";//管理表日均电量
				String zdid1 = rs.getString(1) != null ? rs.getString(1) : "";// 站点ID
				String zdname1 = rs.getString(2) != null ? rs.getString(2)
						: "";// 站点名称
				String accountmonth1 = rs.getString(3) != null ? rs
						.getString(3) : "";// 报账月份
				String blhdl1 = rs.getString(4) != null ? rs.getString(4)
						: "";// 实际用电量double
				String actualpay1 = rs.getString(5) != null ? rs.getString(5)
						: "";// 实际电费
				String unitprice1 = rs.getString(6) != null ? rs.getString(6)
						: "";// 单价
				String qsdbdl1 = rs.getString(7) != null ? rs.getString(7)
						: "";// 省定标
				String edhdl1 = rs.getString(8) != null ? rs.getString(8)
						: "";// 市定标
				String stationtype1 = rs.getString(9) != null ? rs
						.getString(9) : "";// 站点类型
				String property1 = rs.getString(10) != null ? rs
						.getString(10) : "";// 站点属性
				String dbid1 = rs.getString(11) != null ? rs.getString(11)
						: "";// 电表id
				String dbname1 = rs.getString(12) != null ? rs.getString(12)
						: "";// 电表名称
				String cityzrauditstatus1 = rs.getString(13) != null ? rs
						.getString(13) : "";// 市级主任审核状态
				String dfzflx1 = rs.getString(14) != null ? rs.getString(14)
						: "";// 电费支付类型
				String id1 = rs.getString(15) != null ? rs.getString(15) : "";
				String uuid1 = rs.getString(16) != null ? rs.getString(16)
						: "";
				String dedhdl1 = rs.getString(17) != null ? rs.getString(17)
						: "";// 超市定标
				String csdb1 = rs.getString(18) != null ? rs.getString(18)
						: "";// 超省定标
				String dfbzw1 = rs.getString(19) != null ? rs.getString(19)
						: "";// 电费标志位

				String quxian = rs.getString(20) != null ? rs.getString(20)
						: "";// 区县
				String gdfs = rs.getString(21) != null ? rs.getString(21) : "";// 供电方式
				String jszq = rs.getString(22) != null ? rs.getString(22) : "";// 结算周期
				String rgshzt = rs.getString(23) != null ? rs.getString(23)
						: "";// 人工审核状态
				String sjshzt = rs.getString(24) != null ? rs.getString(24)
						: "";// 市级审核状态
				String beilv = rs.getString(25) != null ? rs.getString(25) : "";// 倍率
				String pjsj = rs.getString(26) != null ? rs.getString(26) : "";// 票据时间
				String pjbh = rs.getString(27) != null ? rs.getString(27) : "";// 票据编号
				String pjlx = rs.getString(28) != null ? rs.getString(28) : "";// 票据类型
				String jfsj = rs.getString(29) != null ? rs.getString(29) : "";// 交费时间
				
				String startTime = rs.getString(30) != null ? rs.getString(30) : "";// 起始时间
				String endTime = rs.getString(31) != null ? rs.getString(31) : "";// 结束时间
				String dlsh = rs.getString("DLSH") != null ? rs.getString("DLSH") : "";
				String autoauditdescription = rs.getString("AUTOAUDITDESCRIPTION") != null ? rs.getString("AUTOAUDITDESCRIPTION") : "";
				String dlshzt = rs.getString("DLSHZT") != null ? rs.getString("DLSHZT") : "";
				String autoauditstatus = rs.getString("AUTOAUDITSTATUS") != null ? rs.getString("AUTOAUDITSTATUS") : "";
				String dianliang = rs.getString("DIANLIANG") != null ? rs.getString("DIANLIANG") : "";
				String pjje = rs.getString("PJJE") != null ? rs.getString("PJJE") : "";
				String dlid1 = rs.getString("AMMETERDEGREEID_FK") != null ? rs.getString("AMMETERDEGREEID_FK") : "";
				String lastyue = rs.getString("LASTYUE") != null ? rs.getString("LASTYUE") : "";
				String bzrj = rs.getString("BZRJ")!=null?rs.getString("BZRJ"):"0"; //报账日均电量
				String cw = rs.getString("FINANCEAUDIT")!=null?rs.getString("FINANCEAUDIT"):""; //报账日均电量

				String dbydl = "";// 电表用电量
				String bzydl = "";// 报账用电量

				/* 新字段的一些计算 */



				// 计算电表用电量
				Double dbdl = 0.0;
				double sjydl = Double.parseDouble(Format.str2(dianliang));

				dbdl = sjydl * Double.parseDouble(beilv);

				dbydl = Double.toString(Format.d2(dbdl));
				
				
				//超省标电量度：报账电量-省定标电量；
				//超市标电量度：报账电量-市定标电量
				String csbdld = String.valueOf(Format.d2(Format.str_d(blhdl1) - Format.str_d(qsdbdl1) *  Format.str_d(jszq)));
				String cshibdld = String.valueOf(Format.d2(Format.str_d(blhdl1) - Format.str_d(edhdl1) *  Format.str_d(jszq)));

				
				//------计算 省定标电费=省定标电量*单价*结算周期------
				qsdbdl1 = Format.str2(qsdbdl1);
				unitprice1 = Format.str2(unitprice1);
				String jszq2 = Format.str2(jszq);
				double sdbdf = Double.parseDouble(qsdbdl1) * Double.parseDouble(unitprice1) * Double.parseDouble(jszq2);
				sdbdf = Format.d2(sdbdf);

				//------计算 超省定标电费额=实际电费-省定标电费------
				actualpay1 = Format.str2(actualpay1);
				double csdbdfe = Double.parseDouble(actualpay1) - sdbdf;
				csdbdfe = Format.d2(csdbdfe);
				//--超省标电费绝对值
				double csdbdfjdz = Math.abs(csdbdfe);
				

				//------计算 超省定标电费比值=超省定标电费额/省定标电费*100------
				double csdbdfbz = 0;
				try {
					csdbdfbz = csdbdfe / sdbdf * 100;
					csdbdfbz = Format.d2(csdbdfbz);
				} catch (Exception e) {
					csdbdfbz = 0.00;
				}
				
				//------计算 市定标电费=市定标电量*单价*结算周期------
				edhdl1 = Format.str2(edhdl1);
				double sdbd = Double.parseDouble(edhdl1) * Double.parseDouble(unitprice1) * Double.parseDouble(jszq2);
				sdbd = Format.d2(sdbd);

				//------计算 超市定标电费额=实际电费-市定标电费------
				actualpay1 = Format.str2(actualpay1);
				double csdbdf = Double.parseDouble(actualpay1) - sdbd;
				csdbdf = Format.d2(csdbdf);
				//--超市标电费绝对值
				double cshibdfjdz = Math.abs(csdbdf);
				

				//------计算 超市定标电费比值=超市定标电费额/市定标电费*100------
				
				double csdbdfb = 0;
				try {
					csdbdfb = csdbdf / sdbd * 100;
					csdbdfb = Format.d2(csdbdfb);
				} catch (Exception e) {
					csdbdfb = 0.00;
				}
				
				ElectricFeesFormBean bean=new ElectricFeesFormBean();
				bean.setGlbrjl(glbrjl);
				bean.setZdid(zdid1);
				bean.setZdname(zdname1);
				bean.setAccountmonth(accountmonth1);
				bean.setBlhdl(Format.str2(blhdl1));
				bean.setActualpay(Format.str2(actualpay1));
				bean.setUnitprice(unitprice1);
				bean.setQsdbdl(qsdbdl1);
				bean.setEdhdl(edhdl1);
				bean.setStationtype(stationtype1);
				bean.setProperty(property1);
				bean.setDbid(dbid1);
				bean.setDbname(dbname1);
				bean.setCityzrauditstatus(cityzrauditstatus1);
				bean.setDfzflx(dfzflx1);
				bean.setElectricfeeid(id1);
				bean.setUuid(uuid1);
				bean.setCsbbl(dedhdl1);
				bean.setCsbl(csdb1);
				bean.setDfbzw(dfbzw1);
				
				bean.setFloatdegree("");
				bean.setMemo("");
				bean.setFloatpay("");
				bean.setMemo1("");


				bean.setQuxian(quxian);
				bean.setGdfs(gdfs);
				bean.setJszq(jszq);
				bean.setRgshzt(rgshzt);
				bean.setCwsh(cw);
				bean.setSjshzt(sjshzt);
				bean.setBeilv(beilv);
				bean.setPjsj(pjsj);
				bean.setPjbh(pjbh);
				bean.setPjlx(pjlx);
				bean.setJfsj(jfsj);
				bean.setDbydl(dbydl);
				bean.setBzydl(bzydl);
				bean.setCsdbdfe(Double.toString(csdbdfe));
				bean.setCsdbdfbz(csdbdfbz + "%");
				bean.setLastdatetime(startTime);
				bean.setThisdatetime(endTime);
				bean.setDlsh(dlsh);
				bean.setAutoauditdescription(autoauditdescription);
				bean.setDlshzt(dlshzt);
				bean.setAutoauditstatus(autoauditstatus);
				bean.setPjje(Double.parseDouble(Format.str2(pjje)));
				bean.setLastelecfees(""); //上期电费
				bean.setLastelecdegree(""); //上期电量
				bean.setLastunitprice(""); //上期单价
				
				bean.setLastyue(lastyue);//上期余额
				
				bean.setLastfloatdegreeandbl("");//上期电量调整*倍率
				bean.setLastactualdegree("");//上期电表用电量
				bean.setFloatdegreeandbl("");//电量调整*倍率
				bean.setCshibdfbl(csdbdfb + "%");//超市标电费比例
				bean.setCsbdfjdz(String.valueOf(csdbdfjdz));//超省标电费绝对值
				bean.setCshibdjdz(String.valueOf(cshibdfjdz));//超市标电费绝对值
				bean.setCsbdld(csbdld);//超省标电量
				bean.setCshibdld(cshibdld);//超市标电量
				bean.setLineandchangeandbl("");//线变损电量
				bean.setBzrj(Format.str2(bzrj));//报账日均电量

				bean.setBiaozhi("2");
				bean.setSort("预付费");
				
				bean.setTbtzsq("");
				bean.setDlid(dlid1);
				
				list.add(bean);
				}
		}catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,sta,conn);
		}
		//System.out.println(list.toString() + "------------------");
		return list;
	}
	
	// 人工审核电费列表
	/**
	 * @param whereStr
	 * @param loginId
	 * @return
	 * @update WangYiXiao 2014-4-21 电表用电量=（本次读数-上次读数）*倍率 
	 * @update WangYiXiao 2014-8-8  加强版专用  tbtzsq = 1
	 * @update WangYiXiao 2014-8-11 去掉 tbtzsq = 1 ,sql2可用
	 */
	public synchronized List<ElectricFeesFormBean>  getPageDataCheckjn(String whereStr,
			String loginId,String lrbz1,String lrbz2) {		
		List<ElectricFeesFormBean>  list = new ArrayList<ElectricFeesFormBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		Connection conn = null;
		Statement sta = null;
		ResultSet rs = null;
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			sql = "SELECT ZD.ID,ZD.JZNAME,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),AD.BLHDL,EF.ACTUALPAY,EF.UNITPRICE,EF.QSDBDL,EF.EDHDL,"
				+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				+ "RTNAME(ZD.PROPERTY),AM.DBID,AM.DBNAME,AD.LASTDEGREE,AD.THISDEGREE,to_char(AD.LASTDATETIME,'yyyy-mm-dd'),to_char(AD.THISDATETIME,'yyyy-mm-dd'),"
				+ "AD.FLOATDEGREE,AD.MEMO,EF.FLOATPAY,EF.MEMO,"
				+ "(CASE WHEN ZD.XIAN IS NOT NULL THEN RNDIQU(ZD.XIAN) ELSE '' END) || (CASE WHEN ZD.XIAOQU IS NOT NULL THEN RNDIQU(ZD.XIAOQU) ELSE '' END) AS SZDQ,"
				+ "EF.ELECTRICFEE_ID,EF.DFUUID,EF.CITYZRAUDITSTATUS,RTNAME(AM.DFZFLX),AD.DEDHDL,AD.CSDB,'1' AS DFBZW,"
				+ " rndiqu(ZD.xian),rtname(ZD.gdfs),EF.JSZQ,EF.MANUALAUDITSTATUS,EF.CITYAUDIT,AM.BEILV,EF.NOTETIME,EF.NOTENO,RTNAME(EF.NOTETYPEID)," 
				+ " EF.PAYDATETIME,AD.AUTOAUDITSTATUS AS DLSH, EF.AUTOAUDITDESCRIPTION, AD.AUTOAUDITDESCRIPTION AS DLSHZT, EF.AUTOAUDITSTATUS, AD.ACTUALDEGREE, EF.PJJE, AD.TBTZSQ, AD.AMMETERDEGREEID,"
				+ " AD.LASTELECFEES, AD.LASTELECDEGREE,AD.LASTUNITPRICE,AD.LASTFLOATDEGREE,AD.LASTACTUALDEGREE,EF.CHANGEVALUE,EF.ACTUALLINELOSSVALUE,AD.BLHDL/EF.JSZQ BZRJ"
				+ " FROM ZHANDIAN ZD,DIANBIAO AM,AMMETERDEGREES AD,ELECTRICFEES EF "
				+ " WHERE ZD.ID=AM.ZDID  AND AM.DBID=AD.AMMETERID_FK "
				+ " AND AD.AMMETERDEGREEID=EF.AMMETERDEGREEID_FK AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "
				+ whereStr+lrbz1
				+ " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
				+ loginId + "'))" + fzzdstr + ") ";
			
			sql2 = "SELECT ZD.ID,ZD.JZNAME,to_char(EF.ACCOUNTMONTH,'yyyy-mm'),EF.DIANLIANG,EF.MONEY,EF.DANJIA,EF.QSDBDL,EF.EDHDL,"
				+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				+ "RTNAME(ZD.PROPERTY),AM.DBID,AM.DBNAME,EF.CITYZRAUDITSTATUS,RTNAME(AM.DFZFLX),EF.ID,EF.UUID,EF.DEDHDL,EF.CSDB,'2' as dfbzw,"
				+ " rndiqu(ZD.xian),rtname(ZD.gdfs),EF.JSZQ,EF.MANUALAUDITSTATUS,EF.CITYAUDIT,AM.BEILV,EF.NOTETIME,EF.NOTENO,RTNAME(EF.NOTETYPEID),"
				+ " EF.PAYDATETIME,to_char(EF.STARTDATE,'yyyy-mm-dd'),to_char(EF.ENDDATE,'yyyy-mm-dd'),'1' AS DLSH, EF.AUTOAUDITDESCRIPTION, '' AS DLSHZT, EF.AUTOAUDITSTATUS, EF.DIANLIANG, EF.PJJE, EF.AMMETERDEGREEID_FK, EF.LASTYUE,EF.DIANLIANG/EF.JSZQ BZRJ"				
				+ " FROM ZHANDIAN ZD, DIANBIAO AM, YUFUFEIVIEW EF"
		        + " WHERE ZD.ID = AM.ZDID AND AM.DBID = EF.PREPAYMENT_AMMETERID  AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "
		        + whereStr+lrbz2
		        + " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
		        + loginId + "'))" + fzzdstr + ") ";//
		       
            System.out.println("人工审核电费列表查询"+sql.toString());
            System.out.println("人工审核电费列表查询"+sql2.toString());
            db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			
			rs = sta.executeQuery(sql.toString());//运行sql
			while(rs.next()){
				
				String zdid = rs.getString(1) != null ? rs.getString(1) : "";// 站点ID
				
				/*sql1 = "SELECT ROUND(SUM(AM.THISDEGREE - AM.LASTDEGREE) / SUM(AM.GLZQ),2) GLBRJL FROM DIANBIAO D, AMMETERDEGREES AM, (SELECT TO_CHAR(MAX(TO_DATE((T.THISDATETIME), 'YYYY-MM-DD')), 'YYYY-MM-DD') THISDATETIME,"
		               +"T.AMMETERID_FK FROM AMMETERDEGREES T WHERE T.MANUALAUDITSTATUS = '1' AND T.AMMETERID_FK IN (SELECT DBID FROM ZHANDIAN Z, DIANBIAO D WHERE Z.ID = D.ZDID AND D.DBYT = 'dbyt03' AND Z.ID = '"
		               +zdid+"')GROUP BY AMMETERID_FK) DD WHERE D.DBID = AM.AMMETERID_FK AND DD.AMMETERID_FK = D.DBID AND AM.THISDATETIME = DD.THISDATETIME AND AM.MANUALAUDITSTATUS = '1'";
				DataBase dba = new DataBase();
				Connection conna = dba.getConnection();
				Statement staa = conna.createStatement();
				 System.out.println("人工审核电费管理日均电量查询"+sql1.toString());
				ResultSet rsa = staa.executeQuery(sql1);
				String glbrjl="";
				if(rsa.next()){
					glbrjl=rsa.getString("GLBRJL");//管理表日均电量
				}
				dba.free(rsa, staa, conna);*/
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";// 站点名称
				String accountmonth = rs.getString(3) != null ? rs.getString(3)
						: "";// 报账月份
				String blhdl = rs.getString(4) != null ? rs.getString(4) : "";// 实际用电量double
				String actualpay = rs.getString(5) != null ? rs.getString(5)
						: "";// 实际电费
				String unitprice = rs.getString(6) != null ? rs.getString(6)
						: "";// 单价
				String qsdbdl = rs.getString(7) != null ? rs.getString(7) : "";// 省定标
				String edhdl = rs.getString(8) != null ? rs.getString(8) : "";// 市定标
				String stationtype = rs.getString(9) != null ? rs.getString(9)
						: "";// 站点类型
				String property = rs.getString(10) != null ? rs.getString(10)
						: "";// 站点属性
				String dbid = rs.getString(11) != null ? rs.getString(11) : "";// 电表id
				String dbname = rs.getString(12) != null ? rs.getString(12)
						: "";// 电表名称
				String lastdegree = rs.getString(13) != null ? rs.getString(13)
						: "";// 起始电表数
				String thisdegree = rs.getString(14) != null ? rs.getString(14)
						: "";// 结束电表数
				String lastdatetime = rs.getString(15) != null ? rs
						.getString(15) : "";// 上次抄表时间
				String thisdatetime = rs.getString(16) != null ? rs
						.getString(16) : "";// 本次抄表时间
				String floatdegree = rs.getString(17) != null ? rs
						.getString(17) : "";// 电量调整
				String memo = rs.getString(18) != null ? rs.getString(18) : "";// 电量调整备注
				String floatpay = rs.getString(19) != null ? rs.getString(19)
						: "";// 电费调整
				String memo1 = rs.getString(20) != null ? rs.getString(20) : "";// 电费调整备注
				String szdq = rs.getString(21) != null ? rs.getString(21) : "";// 所在地区
				String electricfee_id = rs.getString(22) != null ? rs
						.getString(22) : "";// 电费id
				String uuid = rs.getString(23) != null ? rs.getString(23) : "";// uuid
				String cityzrauditstatus = rs.getString(24) != null ? rs
						.getString(24) : "";// 市级主任审核状态
				String dfzflx = rs.getString(25) != null ? rs.getString(25)
						: "";// 电费支付类型
				String dedhdl = rs.getString(26) != null ? rs.getString(26)
						: "";// 超市定标
				String csdb = rs.getString(27) != null ? rs.getString(27) : "";// 超省定标
				String dfbzw = rs.getString(28) != null ? rs.getString(28) : "";// 电费标志位

				String quxian = rs.getString(29) != null ? rs.getString(29)
						: "";// 区县
				String gdfs = rs.getString(30) != null ? rs.getString(30) : "";// 供电方式
				String jszq = rs.getString(31) != null ? rs.getString(31) : "";// 结算周期
				String rgshzt = rs.getString(32) != null ? rs.getString(32)
						: "";// 人工审核状态
				String sjshzt = rs.getString(33) != null ? rs.getString(33)
						: "";// 市级审核状态
				String beilv = rs.getString(34) != null ? rs.getString(34) : "";// 倍率
				String pjsj = rs.getString(35) != null ? rs.getString(35) : "";// 票据时间
				String pjbh = rs.getString(36) != null ? rs.getString(36) : "";// 票据编号
				String pjlx = rs.getString(37) != null ? rs.getString(37) : "";// 票据类型
				String jfsj = rs.getString(38) != null ? rs.getString(38) : "";// 交费时间
				String dlsh = rs.getString("DLSH") != null ? rs.getString("DLSH") : "";
				String autoauditdescription = rs.getString("AUTOAUDITDESCRIPTION") != null ? rs.getString("AUTOAUDITDESCRIPTION") : "";
				String dlshzt = rs.getString("DLSHZT") != null ? rs.getString("DLSHZT") : "";
				String autoauditstatus = rs.getString("AUTOAUDITSTATUS") != null ? rs.getString("AUTOAUDITSTATUS") : "";
				String actualdegree = rs.getString("ACTUALDEGREE") != null ? rs.getString("ACTUALDEGREE") : "";
				String pjje = rs.getString("PJJE") != null ? rs.getString("PJJE") : "";
				String tbtzsq = rs.getString("TBTZSQ") != null ? rs.getString("TBTZSQ") : "";//特别调整申请
				String dlid = rs.getString("AMMETERDEGREEID") != null ? rs.getString("AMMETERDEGREEID") : "";//电量ID
				String lastelecfees = rs.getString("LASTELECFEES")!=null?rs.getString("LASTELECFEES"):""; //上期电费
				String lastelecdegree = rs.getString("LASTELECDEGREE")!=null?rs.getString("LASTELECDEGREE"):""; //上期电量
				String lastunitprice = rs.getString("LASTUNITPRICE")!=null?rs.getString("LASTUNITPRICE"):""; //上期单价
				String lastactualdegree = rs.getString("LASTACTUALDEGREE")!=null?rs.getString("LASTACTUALDEGREE"):"0.00"; //上期电表用电量
				String lastfloatdegree = rs.getString("LASTFLOATDEGREE")!=null?rs.getString("LASTFLOATDEGREE"):"0.00"; //上期电量调整
				String changevalue = rs.getString("CHANGEVALUE")!=null?rs.getString("CHANGEVALUE"):"0"; //变损值
				String actuallinelossvalue = rs.getString("ACTUALLINELOSSVALUE")!=null?rs.getString("ACTUALLINELOSSVALUE"):"0"; //实际线损值
				String bzrj = rs.getString("BZRJ")!=null?rs.getString("BZRJ"):"0"; //报账日均电量
				
				String dbydl = "";// 电表用电量
				String bzydl = "";// 报账用电量


				/* 新字段的一些计算 */
				//本次电表读数 - 上次电表读数
				double actdegree = Format.d2(Double.parseDouble(thisdegree) - Double.parseDouble(lastdegree)); 
				// 计算电表用电量
				if(beilv==null||"".equals(beilv)){
					beilv="1";
				}
				Double dbdl = 0.0;
				//double sjydl = Double.parseDouble(Format.str2(actualdegree));
				dbdl = actdegree * Double.parseDouble(beilv);
				dbydl = Double.toString(Format.d2(dbdl));
				
				//超省标电量度：报账电量-省定标电量；
				//超市标电量度：报账电量-市定标电量
				String csbdld = String.valueOf(Format.d2(Format.str_d(blhdl) - Format.str_d(qsdbdl) *  Format.str_d(jszq)));
				String cshibdld = String.valueOf(Format.d2(Format.str_d(blhdl) - Format.str_d(edhdl) *  Format.str_d(jszq)));

				//------计算 省定标电费=省定标电量*单价*结算周期------
				qsdbdl = Format.str2(qsdbdl);
				unitprice = Format.str4(unitprice);
				String jszq2 = Format.str2(jszq);
				double sdbdf = Double.parseDouble(qsdbdl) * Double.parseDouble(unitprice) * Double.parseDouble(jszq2);
				sdbdf = Format.d2(sdbdf);

				//------计算 超省定标电费额=实际电费-省定标电费------
				actualpay = Format.str2(actualpay);
				double csdbdfe = Double.parseDouble(actualpay) - sdbdf;
				csdbdfe = Format.d2(csdbdfe);
				//--超省标电费绝对值
				double csdbdfjdz = Math.abs(csdbdfe);
				

				//------计算 超省定标电费比值=超省定标电费额/省定标电费*100------
				
				double csdbdfbz = 0;
				try {
					csdbdfbz = csdbdfe / sdbdf * 100;
					csdbdfbz = Format.d2(csdbdfbz);
				} catch (Exception e) {
					csdbdfbz = 0.00;
				}
				
				//------计算 市定标电费=市定标电量*单价*结算周期------
				edhdl = Format.str2(edhdl);
				double sdbd = Double.parseDouble(edhdl) * Double.parseDouble(unitprice) * Double.parseDouble(jszq2);
				sdbd = Format.d2(sdbd);

				//------计算 超市定标电费额=实际电费-市定标电费------
				actualpay = Format.str2(actualpay);
				double csdbdf = Double.parseDouble(actualpay) - sdbd;
				csdbdf = Format.d2(csdbdf);
				//--超市标电费绝对值
				double cshibdfjdz = Math.abs(csdbdf);
				

				//------计算 超市定标电费比值=超市定标电费额/市定标电费*100------
				
				double csdbdfb = 0;
				try {
					csdbdfb = csdbdf / sdbd * 100;
					csdbdfb = Format.d2(csdbdfb);
				} catch (Exception e) {
					csdbdfb = 0.00;
				}
				
				//电量调增*倍率、上期电表用电量、上期电量调整*倍率列
				String bv = "";
				if(Format.str_d(beilv)==0){//倍率为空 或 为 0
					bv="1";
				}else{
					bv = beilv;
				}
				String floatdegreeandbl = String.valueOf(Format.d2(Format.str_d(floatdegree)*Format.str_d(bv)));//电量调增*倍率
				String lastfloatdegreeandbl = String.valueOf(Format.d2(Format.str_d(lastfloatdegree)*Format.str_d(bv)));//上期电量调整*倍率列
				String changeandlineandbl = String.valueOf(Format.d2((Format.str_d(changevalue) + Format.str_d(actuallinelossvalue)) * Format.str_d(bv)));//线变损电量
				
				ElectricFeesFormBean bean=new ElectricFeesFormBean();
				
				bean.setGlbrjl("");
				bean.setZdid(zdid);
				bean.setZdname(zdname);
				bean.setAccountmonth(accountmonth);
				bean.setBlhdl(Format.str2(blhdl));
				bean.setActualpay(Format.str2(actualpay));
				bean.setUnitprice(unitprice);
				bean.setCsbl(Format.str2(csdb));
				bean.setCsbbl(Format.str2(dedhdl));
				bean.setQsdbdl(qsdbdl);
				bean.setEdhdl(edhdl);
				bean.setStationtype(stationtype);
				bean.setProperty(property);
				bean.setDbid(dbid);
				bean.setDbname(dbname);
				bean.setLastdegree(lastdegree);
				bean.setThisdegree(thisdegree);
				bean.setLastdatetime(lastdatetime);
				bean.setThisdatetime(thisdatetime);
				bean.setFloatdegree(floatdegree);
				bean.setMemo(memo);
				bean.setFloatpay(floatpay);
				bean.setMemo1(memo1);
				bean.setSzdq(szdq);
				bean.setElectricfeeid(electricfee_id);
				bean.setUuid(uuid);
				bean.setCityzrauditstatus(cityzrauditstatus);
				bean.setDfzflx(dfzflx);
				bean.setDfbzw(dfbzw);

				bean.setQuxian(quxian);
				bean.setGdfs(gdfs);
				bean.setJszq(jszq);
				bean.setRgshzt(rgshzt);
				bean.setSjshzt(sjshzt);
				bean.setBeilv(beilv);
				bean.setPjsj(pjsj);
				bean.setPjbh(pjbh);
				bean.setPjlx(pjlx);
				bean.setJfsj(jfsj);
				bean.setDbydl(dbydl);
				bean.setBzydl(bzydl);
				bean.setCsdbdfe(Double.toString(csdbdfe));
				bean.setCsdbdfbz(csdbdfbz + "%");
				bean.setDlsh(dlsh);
				bean.setAutoauditdescription(autoauditdescription);
				bean.setDlshzt(dlshzt);
				bean.setAutoauditstatus(autoauditstatus);
				bean.setPjje(Double.parseDouble(Format.str2(pjje)));
				bean.setLastelecfees(lastelecfees); //上期电费
				bean.setLastelecdegree(lastelecdegree); //上期电量
				bean.setLastunitprice(lastunitprice); //上期单价
				bean.setLastyue("");//上期余额
				bean.setLastfloatdegreeandbl(Format.str2(lastfloatdegreeandbl));//上期电量调整*倍率
				bean.setLastactualdegree(Format.str2(lastactualdegree));//上期电表用电量
				bean.setFloatdegreeandbl(Format.str2(floatdegreeandbl));//电量调整*倍率
				bean.setCshibdfbl(csdbdfb + "%");//超市标电费比例
				bean.setCsbdfjdz(String.valueOf(csdbdfjdz));//超省标电费绝对值
				bean.setCshibdjdz(String.valueOf(cshibdfjdz));//超市标电费绝对值
				bean.setCsbdld(csbdld);//超省标电量
				bean.setCshibdld(cshibdld);//超市标电量
				bean.setLineandchangeandbl(changeandlineandbl);//线变损电量
				bean.setBzrj(Format.str2(bzrj));//报账日均电量
				
				
				
				bean.setBiaozhi("1");
				bean.setSort("电费");
				
				bean.setTbtzsq(tbtzsq);
				bean.setDlid(dlid);
				
				list.add(bean);
				}
			
			rs = sta.executeQuery(sql2.toString());//运行sql
			while(rs.next()){
				String glbrjl="";//管理表日均电量
				String zdid1 = rs.getString(1) != null ? rs.getString(1) : "";// 站点ID
				String zdname1 = rs.getString(2) != null ? rs.getString(2)
						: "";// 站点名称
				String accountmonth1 = rs.getString(3) != null ? rs
						.getString(3) : "";// 报账月份
				String blhdl1 = rs.getString(4) != null ? rs.getString(4)
						: "";// 实际用电量double
				String actualpay1 = rs.getString(5) != null ? rs.getString(5)
						: "";// 实际电费
				String unitprice1 = rs.getString(6) != null ? rs.getString(6)
						: "";// 单价
				String qsdbdl1 = rs.getString(7) != null ? rs.getString(7)
						: "";// 省定标
				String edhdl1 = rs.getString(8) != null ? rs.getString(8)
						: "";// 市定标
				String stationtype1 = rs.getString(9) != null ? rs
						.getString(9) : "";// 站点类型
				String property1 = rs.getString(10) != null ? rs
						.getString(10) : "";// 站点属性
				String dbid1 = rs.getString(11) != null ? rs.getString(11)
						: "";// 电表id
				String dbname1 = rs.getString(12) != null ? rs.getString(12)
						: "";// 电表名称
				String cityzrauditstatus1 = rs.getString(13) != null ? rs
						.getString(13) : "";// 市级主任审核状态
				String dfzflx1 = rs.getString(14) != null ? rs.getString(14)
						: "";// 电费支付类型
				String id1 = rs.getString(15) != null ? rs.getString(15) : "";
				String uuid1 = rs.getString(16) != null ? rs.getString(16)
						: "";
				String dedhdl1 = rs.getString(17) != null ? rs.getString(17)
						: "";// 超市定标
				String csdb1 = rs.getString(18) != null ? rs.getString(18)
						: "";// 超省定标
				String dfbzw1 = rs.getString(19) != null ? rs.getString(19)
						: "";// 电费标志位

				String quxian = rs.getString(20) != null ? rs.getString(20)
						: "";// 区县
				String gdfs = rs.getString(21) != null ? rs.getString(21) : "";// 供电方式
				String jszq = rs.getString(22) != null ? rs.getString(22) : "";// 结算周期
				String rgshzt = rs.getString(23) != null ? rs.getString(23)
						: "";// 人工审核状态
				String sjshzt = rs.getString(24) != null ? rs.getString(24)
						: "";// 市级审核状态
				String beilv = rs.getString(25) != null ? rs.getString(25) : "";// 倍率
				String pjsj = rs.getString(26) != null ? rs.getString(26) : "";// 票据时间
				String pjbh = rs.getString(27) != null ? rs.getString(27) : "";// 票据编号
				String pjlx = rs.getString(28) != null ? rs.getString(28) : "";// 票据类型
				String jfsj = rs.getString(29) != null ? rs.getString(29) : "";// 交费时间
				
				String startTime = rs.getString(30) != null ? rs.getString(30) : "";// 起始时间
				String endTime = rs.getString(31) != null ? rs.getString(31) : "";// 结束时间
				String dlsh = rs.getString("DLSH") != null ? rs.getString("DLSH") : "";
				String autoauditdescription = rs.getString("AUTOAUDITDESCRIPTION") != null ? rs.getString("AUTOAUDITDESCRIPTION") : "";
				String dlshzt = rs.getString("DLSHZT") != null ? rs.getString("DLSHZT") : "";
				String autoauditstatus = rs.getString("AUTOAUDITSTATUS") != null ? rs.getString("AUTOAUDITSTATUS") : "";
				String dianliang = rs.getString("DIANLIANG") != null ? rs.getString("DIANLIANG") : "";
				String pjje = rs.getString("PJJE") != null ? rs.getString("PJJE") : "";
				String dlid1 = rs.getString("AMMETERDEGREEID_FK") != null ? rs.getString("AMMETERDEGREEID_FK") : "";
				String lastyue = rs.getString("LASTYUE") != null ? rs.getString("LASTYUE") : "";
				String bzrj = rs.getString("BZRJ")!=null?rs.getString("BZRJ"):"0"; //报账日均电量

				String dbydl = "";// 电表用电量
				String bzydl = "";// 报账用电量

				/* 新字段的一些计算 */



				// 计算电表用电量
				Double dbdl = 0.0;
				double sjydl = Double.parseDouble(Format.str2(dianliang));

				dbdl = sjydl * Double.parseDouble(beilv);

				dbydl = Double.toString(Format.d2(dbdl));
				
				
				//超省标电量度：报账电量-省定标电量；
				//超市标电量度：报账电量-市定标电量
				String csbdld = String.valueOf(Format.d2(Format.str_d(blhdl1) - Format.str_d(qsdbdl1) *  Format.str_d(jszq)));
				String cshibdld = String.valueOf(Format.d2(Format.str_d(blhdl1) - Format.str_d(edhdl1) *  Format.str_d(jszq)));

				
				//------计算 省定标电费=省定标电量*单价*结算周期------
				qsdbdl1 = Format.str2(qsdbdl1);
				unitprice1 = Format.str2(unitprice1);
				String jszq2 = Format.str2(jszq);
				double sdbdf = Double.parseDouble(qsdbdl1) * Double.parseDouble(unitprice1) * Double.parseDouble(jszq2);
				sdbdf = Format.d2(sdbdf);

				//------计算 超省定标电费额=实际电费-省定标电费------
				actualpay1 = Format.str2(actualpay1);
				double csdbdfe = Double.parseDouble(actualpay1) - sdbdf;
				csdbdfe = Format.d2(csdbdfe);
				//--超省标电费绝对值
				double csdbdfjdz = Math.abs(csdbdfe);
				

				//------计算 超省定标电费比值=超省定标电费额/省定标电费*100------
				double csdbdfbz = 0;
				try {
					csdbdfbz = csdbdfe / sdbdf * 100;
					csdbdfbz = Format.d2(csdbdfbz);
				} catch (Exception e) {
					csdbdfbz = 0.00;
				}
				
				//------计算 市定标电费=市定标电量*单价*结算周期------
				edhdl1 = Format.str2(edhdl1);
				double sdbd = Double.parseDouble(edhdl1) * Double.parseDouble(unitprice1) * Double.parseDouble(jszq2);
				sdbd = Format.d2(sdbd);

				//------计算 超市定标电费额=实际电费-市定标电费------
				actualpay1 = Format.str2(actualpay1);
				double csdbdf = Double.parseDouble(actualpay1) - sdbd;
				csdbdf = Format.d2(csdbdf);
				//--超市标电费绝对值
				double cshibdfjdz = Math.abs(csdbdf);
				

				//------计算 超市定标电费比值=超市定标电费额/市定标电费*100------
				
				double csdbdfb = 0;
				try {
					csdbdfb = csdbdf / sdbd * 100;
					csdbdfb = Format.d2(csdbdfb);
				} catch (Exception e) {
					csdbdfb = 0.00;
				}
				
				ElectricFeesFormBean bean=new ElectricFeesFormBean();
				bean.setGlbrjl(glbrjl);
				bean.setZdid(zdid1);
				bean.setZdname(zdname1);
				bean.setAccountmonth(accountmonth1);
				bean.setBlhdl(Format.str2(blhdl1));
				bean.setActualpay(Format.str2(actualpay1));
				bean.setUnitprice(unitprice1);
				bean.setQsdbdl(qsdbdl1);
				bean.setEdhdl(edhdl1);
				bean.setStationtype(stationtype1);
				bean.setProperty(property1);
				bean.setDbid(dbid1);
				bean.setDbname(dbname1);
				bean.setCityzrauditstatus(cityzrauditstatus1);
				bean.setDfzflx(dfzflx1);
				bean.setElectricfeeid(id1);
				bean.setUuid(uuid1);
				bean.setCsbbl(dedhdl1);
				bean.setCsbl(csdb1);
				bean.setDfbzw(dfbzw1);
				
				bean.setFloatdegree("");
				bean.setMemo("");
				bean.setFloatpay("");
				bean.setMemo1("");


				bean.setQuxian(quxian);
				bean.setGdfs(gdfs);
				bean.setJszq(jszq);
				bean.setRgshzt(rgshzt);
				bean.setSjshzt(sjshzt);
				bean.setBeilv(beilv);
				bean.setPjsj(pjsj);
				bean.setPjbh(pjbh);
				bean.setPjlx(pjlx);
				bean.setJfsj(jfsj);
				bean.setDbydl(dbydl);
				bean.setBzydl(bzydl);
				bean.setCsdbdfe(Double.toString(csdbdfe));
				bean.setCsdbdfbz(csdbdfbz + "%");
				bean.setLastdatetime(startTime);
				bean.setThisdatetime(endTime);
				bean.setDlsh(dlsh);
				bean.setAutoauditdescription(autoauditdescription);
				bean.setDlshzt(dlshzt);
				bean.setAutoauditstatus(autoauditstatus);
				bean.setPjje(Double.parseDouble(Format.str2(pjje)));
				bean.setLastelecfees(""); //上期电费
				bean.setLastelecdegree(""); //上期电量
				bean.setLastunitprice(""); //上期单价
				
				bean.setLastyue(lastyue);//上期余额
				
				bean.setLastfloatdegreeandbl("");//上期电量调整*倍率
				bean.setLastactualdegree("");//上期电表用电量
				bean.setFloatdegreeandbl("");//电量调整*倍率
				bean.setCshibdfbl(csdbdfb + "%");//超市标电费比例
				bean.setCsbdfjdz(String.valueOf(csdbdfjdz));//超省标电费绝对值
				bean.setCshibdjdz(String.valueOf(cshibdfjdz));//超市标电费绝对值
				bean.setCsbdld(csbdld);//超省标电量
				bean.setCshibdld(cshibdld);//超市标电量
				bean.setLineandchangeandbl("");//线变损电量
				bean.setBzrj(Format.str2(bzrj));//报账日均电量

				bean.setBiaozhi("2");
				bean.setSort("预付费");
				
				bean.setTbtzsq("");
				bean.setDlid(dlid1);
				
				list.add(bean);
				}
		}catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,sta,conn);
		}
		//System.out.println(list.toString() + "------------------");
		return list;
	}
	
	  //获取电表前三次电费信息
	/*
	 * update zhouxue 2014.06.10 判断修改：当dfbzw为1时执行电费表查询   为2时执行预付费表查询  其他判断去掉
	 */
	// public synchronized List<ElectricFeesFormBean> getCounttt
	  public synchronized List<ElectricFeesFormBean> getinformationdb(String dbid,String bzyf,String dbyt,String dfbzw) {
			ArrayList list = new ArrayList();
			ArrayList list1 = new ArrayList();
		    StringBuffer sql = new StringBuffer();
		    StringBuffer sql1 = new StringBuffer();
		    ResultSet rs = null;
		   // dfbzw.equals(1);
		   
		    //sql.append("select * from electricfees where ELECTRICFEE_ID="+degreeid);
		    //sql.append("select * from electricfees where ELECTRICFEE_ID="+degreeid);
		    
		    sql.append("select * from (select distinct zd.jzname,zd.zdcode,d.dbname,ad.memo as ydlbz,ef.memo as dftzbz,d.csds, to_char(d.cssytime,'yyyy-mm-dd'), d.beilv,to_char(ad.LASTDATETIME,'yyyy-mm-dd'),to_char(ad.THISDATETIME,'yyyy-mm-dd'), ad.FLOATDEGREE,"+
		    			"(case when zd.shi is not null then(select distinct agname from d_area_grade where agcode = zd.shi)else ''end) ||(case when zd.xian is not null then(select distinct agname from d_area_grade where agcode = zd.xian)else ''end) || (case when zd.xiaoqu is not null then(select distinct agname from d_area_grade where agcode = zd.xiaoqu)else ''end) as szdq,"+
		    			"(select acc.name from account acc where acc.accountname = ef.entrypensonnel) as ENTRYPENSONNEL,to_char(ef.ENTRYTIME,'yyyy-mm-dd hh24:MI:SS'),ad.LASTDEGREE,ad.THISDEGREE,ad.BLHDL as ACTUALDEGREE, ef.UNITPRICE,ef.FLOATPAY,ef.ACTUALPAY, to_char(ad.startmonth,'yyyy-mm'),to_char(ad.endmonth,'yyyy-mm'),"+
		    			"(select t.name from indexs t where t.code = zd.jztype and type='ZDLX') as jztype,(select name from indexs where code=d.YDSB and type = 'YDSB') as ydsb,d.DLLX,(select name from account where accountname = ef.INPUTOPERATOR) as INPUTOPERATOR,ef.INPUTDATETIME,to_char(ef.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,(select name from account where accountname = ef.PAYOPERATOR) as PAYOPERATOR,ef.AUTOAUDITSTATUS,ef.MANUALAUDITSTATUS,ad.AUTOAUDITSTATUS as dlsh,"+
		    			" zd.SHI,rtname(property) as property,zd.QSDBDL,ef.jszq"+
		    			" from dianbiao d, zhandian zd, dianduview ad, dianfeiview ef where zd.id = d.zdid and d.dbid = ad.ammeterid_fk and ad.ammeterdegreeid = ef.ammeterdegreeid_fk"+
					       " and d.dbid ='"+dbid+"' AND to_char(EF.ACCOUNTMONTH,'yyyy-mm')<='"+bzyf+"'order by ACCOUNTMONTH DESC) WHERE ROWNUM<5");		    
		    sql1.append("select * from (SELECT distinct Z.JZNAME,Z.ZDCODE,D.DBNAME,'' AS YDLBZ,DF.MEMO AS DFTZBZ,D.CSDS,to_char(D.CSSYTIME,'yyyy-mm-dd'),D.BEILV,to_char(DF.STARTDATE,'yyyy-mm-dd'),to_char(DF.ENDDATE,'yyyy-mm-dd'),'' AS FLOATDEGREE,"+
		    "(CASE WHEN Z.SHI IS NOT NULL THEN (SELECT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI) ELSE '' END) || (CASE WHEN Z.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN) ELSE '' END) || (CASE WHEN Z.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU) ELSE '' END) AS SZDQ,"+
		    "(SELECT ACC.NAME FROM ACCOUNT ACC WHERE ACC.ACCOUNTNAME = DF.ENTRYPENSONNEL) AS ENTRYPENSONNEL,to_char(DF.ENTRYTIME,'yyyy-mm-dd hh24:MI:SS'), DF.STARTDEGREE,DF.STOPDEGREE,DF.DIANLIANG,"+
		    "DF.DANJIA AS UNITPRICE,'' AS FLOATPAY,DF.MONEY AS ACTUALPAY,to_char(DF.STARTMONTH,'yyyy-mm'),to_char(DF.ENDMONTH,'yyyy-mm'),(SELECT II.NAME FROM INDEXS II WHERE Z.JZTYPE = II.CODE) AS JZTYPE,(SELECT YDSB.NAME FROM INDEXS YDSB  WHERE YDSB.CODE = D.YDSB AND TYPE = 'YDLX') AS YDSB,"+
		    "(SELECT DLLX.NAME FROM INDEXS DLLX WHERE DLLX.CODE = D.DLLX) AS DLLX,'' AS INPUTOPERATOR,'' AS INPUTDATETIME,to_char(DF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,DF.PAYOPERATOR,DF.AUTOAUDITSTATUS AS AUTOAUDITSTATUS,DF.AUTOAUDITSTATUS AS DLSH,"+
		    "DF.MANUALAUDITSTATUS AS MANUALAUDITSTATUS,Z.SHI,rtname(property) as property,Z.QSDBDL,DF.JSZQ FROM ZHANDIAN Z, DIANBIAO D, YUFUFEIVIEW DF WHERE Z.ID = D.ZDID AND D.DBID = DF.PREPAYMENT_AMMETERID AND Z.QYZT = '1' AND D.DBID = '"+dbid+"' AND to_char(DF.ACCOUNTMONTH,'yyyy-mm')<='"+bzyf+"' order by ACCOUNTMONTH DESC) WHERE ROWNUM<5");
		    DataBase db = new DataBase();
		    System.out.println("月结预支历史记录查询:"+sql.toString());
		    if(dfbzw.equals("1")){
		    	 System.out.println("月结预支历史记录查询:"+sql.toString());
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
                      String lastdatetime = rs.getString(9);
                      formbean.setLastdatetime(lastdatetime);//ad.LASTDATETIME
                      String thisdatetime = rs.getString(10);
                      formbean.setThisdatetime(thisdatetime);//ad.THISDATETIME
                      formbean.setFloatdegree(rs.getString(11));//ad.FLOATDEGREE
                      formbean.setSzdq(rs.getString(12));//szdq
                      formbean.setEntrypensonnel(rs.getString(13));//entryperson//,,ad.LASTDEGREE,ad.THISDEGREE,ad.BLHDL as ACTUALDEGREE, ef.UNITPRICE,ef.FLOATPAY,ef.ACTUALPAY, ad.startmonth,ad.endmonth,"+
                      formbean.setEntrytime(rs.getString(14));//ef.ENTRYTIME
                      formbean.setLastdegree(rs.getString(15));//d.LASTDEGREE
                      formbean.setThisdegree(rs.getString(16));//d.THISDEGREE
                      formbean.setActualdegree(rs.getString(17));
                      String danjia = rs.getString(18);
                      formbean.setUnitprice(danjia);//ACTUALDEGREE
                      formbean.setFloatpay(rs.getString(19));
                      String actualpay = rs.getString(20);
                      formbean.setActualpay(actualpay);
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
					  formbean.setShi(rs.getString(33));
					  formbean.setProperty(rs.getString(34));
					  String qsdbdl = rs.getString(35);
					  formbean.setQsdbdl(qsdbdl);
					  String jszq = rs.getString(36);
					  formbean.setJszq(jszq);//周期
					  
					  if("".equals(jszq)||"null".equals(jszq)||jszq == null){
						  jszq = "0";
						}
					  
					  double sdbdf = Format.str_d(qsdbdl) * Format.str_d(danjia) * Format.str_d(jszq);//省定标电费
					  formbean.setSdbdf(Format.str2(String.valueOf(sdbdf)));
					  double csdbdfe = Format.str_d(actualpay) - sdbdf;
					  formbean.setCsdbdfe(Format.str2(String.valueOf(csdbdfe)));//超省定标电费额
					  	
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
		    }
		    if(dfbzw.equals("2")){
			  try {
				    System.out.println("合同插卡预支历史记录查询:"+sql1.toString());
					rs = db.queryAll(sql1.toString());
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
	                      String lastdatetime = rs.getString(9);
	                      formbean.setLastdatetime(lastdatetime);//ad.LASTDATETIME
	                      String thisdatetime = rs.getString(10);
	                      formbean.setThisdatetime(thisdatetime);//ad.THISDATETIME
	                      formbean.setFloatdegree(rs.getString(11));//ad.FLOATDEGREE
	                      formbean.setSzdq(rs.getString(12));//szdq
	                      formbean.setEntrypensonnel(rs.getString(13));//entryperson//,,ad.LASTDEGREE,ad.THISDEGREE,ad.BLHDL as ACTUALDEGREE, ef.UNITPRICE,ef.FLOATPAY,ef.ACTUALPAY, ad.startmonth,ad.endmonth,"+
	                      formbean.setEntrytime(rs.getString(14));//ef.ENTRYTIME
	                      formbean.setLastdegree(rs.getString(15));//d.LASTDEGREE
	                      formbean.setThisdegree(rs.getString(16));//d.THISDEGREE
	                      formbean.setActualdegree(rs.getString(17));
	                      String danjia = rs.getString(18);
	                      formbean.setUnitprice(danjia);//ACTUALDEGREE
	                      formbean.setFloatpay(rs.getString(19));
	                      String actualpay = rs.getString(20);
	                      formbean.setActualpay(actualpay);
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
	                      formbean.setDlsh(rs.getString(31));
						  formbean.setManualauditstatus(rs.getString(32));
						  formbean.setShi(rs.getString(33));
						  formbean.setProperty(rs.getString(34));
						  String qsdbdl = rs.getString(35);
						  formbean.setQsdbdl(qsdbdl);
						  String jszq = rs.getString(36);
						  formbean.setJszq(jszq);//周期
						  
						  if("".equals(jszq)||"null".equals(jszq)||jszq == null){
							  jszq = "0";
							}
						  
						  double sdbdf = Format.str_d(qsdbdl) * Format.str_d(danjia) * Format.str_d(jszq);//省定标电费
						  formbean.setSdbdf(Format.str2(String.valueOf(sdbdf)));
						  double csdbdfe = Format.str_d(actualpay) - sdbdf;
						  formbean.setCsdbdfe(Format.str2(String.valueOf(csdbdfe)));//超省定标电费额
	                      list.add(formbean);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
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
		    }
	
		    return list;
		  }	  
	  
		//人工电费审核总条数、未审核条数、自动审核条数、人工审核条数、实际电费金额总和、实际用电量总和
		public ElectricFeesFormBean getCount(String whereStr,String loginId){
			DataBase db = new DataBase();
			ElectricFeesFormBean bean1 = new ElectricFeesFormBean();
			String fzzdstr="";
			ResultSet rs = null;
			try {
				db.connectDb();
				fzzdstr = getFuzeZdid(db,loginId);				
				StringBuffer strall = new StringBuffer();
			    strall.append("SELECT COUNT(*),SUM(DECODE(EF.MANUALAUDITSTATUS, '0', 1)),SUM(DECODE(EF.AUTOAUDITSTATUS, '1', 1)),SUM(DECODE(EF.MANUALAUDITSTATUS, '1', 1))," +
			      			"SUM(TO_CHAR(ACTUALPAY,'FM9999999990.00')),SUM(TO_CHAR(BLHDL, 'FM9999999990.00')),SUM(DECODE(EF.MANUALAUDITSTATUS, '-1', 1)) "
							+ "FROM ZHANDIAN ZD,DIANBIAO AM,DIANDUVIEW AD,DIANFEIVIEW EF "
							+ "WHERE ZD.ID=AM.ZDID AND ZD.QYZT='1' AND AM.DBID=AD.AMMETERID_FK "
							+ "AND AD.AMMETERDEGREEID=EF.AMMETERDEGREEID_FK AND EF.MANUALAUDITSTATUS < '2' "
							+ whereStr
							+ " "
							+ "AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
							+ loginId + "'))" + fzzdstr + ")");
				System.out.println("人工电费审核信息汇总"+strall.toString());
				rs = db.queryAll(strall.toString());
				while(rs.next()){
					bean1.setCount(rs.getString(1)!=null?rs.getString(1):"0");
					bean1.setRgno(rs.getString(2)!=null?rs.getString(2):"0");
					bean1.setZd(rs.getString(3)!=null?rs.getString(3):"0");
					bean1.setRg(rs.getString(4)!=null?rs.getString(4):"0");
					bean1.setActualpay(rs.getString(5)!=null?rs.getString(5):"0");
					bean1.setBlhdl(rs.getString(6)!=null?rs.getString(6):"0");
					bean1.setCwno(rs.getString(7)!=null?rs.getString(7):"0");
				}
			}
			catch (DbException de) {
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
			return bean1;
		}
	
		
		//人工电费审核实际电费金额总和
/*		public String getCountGree(String whereStr,String loginId,String str){
		    String count="";
			DataBase db = new DataBase();
			String fzzdstr="";
			ResultSet rs = null;
			try {
				db.connectDb();
				try {
					fzzdstr = getFuzeZdid(db,loginId);
				
				StringBuffer strall = new StringBuffer();
			      strall.append("select sum(to_char(ACTUALPAY,'fm9999999990.00'))" 
			    		  	+ "from zhandian zd,dianbiao am,dianduview ad,dianfeiview ef "
							+ "where zd.id=am.ZDID and zd.qyzt='1' and am.DBID=ad.ammeterid_fk "
							+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and ef.manualauditstatus < '2' "
							+ whereStr+str
							+ " "
							+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
							+ loginId + "'))" + fzzdstr + ")");
				System.out.println("人工电费审核实际电费金额总和"+strall.toString() );
				rs = db.queryAll(strall.toString());
	           
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
		
		
		//人工电费审核实际用电量总和
		public String getCountGreedl(String whereStr,String loginId,String str){
		    String count="";
			DataBase db = new DataBase();
			String fzzdstr="";
			ResultSet rs = null;
			try {
				db.connectDb();
				try {
					fzzdstr = getFuzeZdid(db,loginId);
				
				StringBuffer strall = new StringBuffer();
			      strall.append("select sum(to_char(blhdl, 'fm9999999990.00'))" 
			    		  	+ "from zhandian zd,dianbiao am,dianduview ad,dianfeiview ef "
							+ "where zd.id=am.ZDID and zd.qyzt='1' and am.DBID=ad.ammeterid_fk "
							+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and ef.manualauditstatus < '2' "
							+ whereStr+str
							+ " "
							+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
							+ loginId + "'))" + fzzdstr + ")");
				System.out.println("人工电费审核实际用电量总和"+strall.toString());
				rs = db.queryAll(strall.toString());
	           
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
		}*/
	
	
//人工审核电费导出
	public synchronized List<ElectricFeesFormBean> getPageDataCheckdaochu123(String whereStr,
			String loginId) {
		List<ElectricFeesFormBean>  list = new ArrayList<ElectricFeesFormBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String sql2 = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		Connection conn = null;
		Statement sta = null;
		ResultSet rs = null;
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			sql = "SELECT ZD.JZNAME,ZD.EDHDL,AD.LASTDATETIME,AD.THISDATETIME,AM.DBID AS AMMETERID,EF.ELECTRICFEE_ID,EF.DFUUID,"
					+ " EF.AUTOAUDITDESCRIPTION,AD.AUTOAUDITSTATUS AS DLSH, AD.AUTOAUDITDESCRIPTION AS DLSHZT," 
					+ " (SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE," 
					+ " EF.UNITPRICE,EF.FLOATPAY,EF.ACTUALPAY,EF.AUTOAUDITSTATUS,EF.ENTRYTIME,EF.ACCOUNTMONTH,"
					+ " (SELECT NAME FROM INDEXS WHERE CODE = EF.NOTETYPEID AND TYPE = 'PJLX') NOTETYPEID,"
					+ " EF.MANUALAUDITSTATUS," 
					+ " CASE WHEN AD.AMUUID IS NULL THEN '0' ELSE  '1' END FEEBZ,"
					+ " TO_CHAR(BLHDL, 'FM9999999990.00') AS BLHDL,"
					+ "(SELECT NAME FROM INDEXS WHERE CODE = AM.LINELOSSTYPE AND TYPE = 'xslx') LINELOSSTYPE,AM.LINELOSSVALUE,AM.BEILV, "	
					
					
					+ " (SELECT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.SHI) AS SHI,(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.XIAN) AS XIAN,(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.XIAOQU) AS XIAOQU, "
					+ " (case when zd.edhdl > 0 and length(AD.lastdatetime) = 10 and length(AD.thisdatetime) = 10 and ((ceil(TO_DATE(AD.Thisdatetime, 'yyyy-mm-dd') - "+
				      " TO_DATE(AD.LASTDATETIME, 'yyyy-mm-dd'))) >= 0) then ((AD.blhdl-zd.edhdl)/(ceil(TO_DATE(AD.Thisdatetime, 'yyyy-mm-dd') - TO_DATE(AD.LASTDATETIME, 'yyyy-mm-dd') + 1)))/zd.edhdl end)  as cbbly,ZD.QSDBDL,EF.PJJE,AD.DEDHDL,ZD.ID AS ZDID "
					+ " FROM ZHANDIAN ZD,DIANBIAO AM,DIANDUVIEW AD,DIANFEIVIEW EF "
					+ " WHERE ZD.ID=AM.ZDID  AND AM.DBID=AD.AMMETERID_FK "
					+ " AND AD.AMMETERDEGREEID=EF.AMMETERDEGREEID_FK AND EF.MANUALAUDITSTATUS < '2' "
					+ whereStr
					+ " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
					+ loginId + "'))" + fzzdstr + ") ";
	
				sql2 = " SELECT ZD.JZNAME, ZD.EDHDL, EF.STARTDATE,EF.ENDDATE, AM.DBID AS AMMETERID, EF.ID, EF.UUID, "
				   + " EF.AUTOAUDITDESCRIPTION, '', '', "
			       + " (SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE = 'stationtype') AS STATIONTYPE, "
			       + " EF.DANJIA, '',  EF.MONEY, EF.AUTOAUDITSTATUS, EF.INPUTDATETIME,EF.ACCOUNTMONTH,"
			       + " (SELECT NAME FROM INDEXS WHERE CODE = EF.NOTETYPEID AND TYPE = 'PJLX') NOTETYPEID,"
			       + " EF.MANUALAUDITSTATUS,'',"
			       + " EF.DIANLIANG,"
			       + " (SELECT NAME FROM INDEXS WHERE CODE = AM.LINELOSSTYPE AND TYPE = 'xslx') LINELOSSTYPE, AM.LINELOSSVALUE, AM.BEILV, "
			       + " (SELECT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.SHI) AS SHI,(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.XIAN) AS XIAN,(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE=ZD.XIAOQU) AS XIAOQU, "
			       + " '',ZD.QSDBDL,EF.PJJE,EF.DEDHDL,ZD.ID AS ZDID"
			       + " FROM ZHANDIAN ZD, DIANBIAO AM, YUFUFEIVIEW EF"
			       + " WHERE ZD.ID = AM.ZDID AND AM.DBID = EF.PREPAYMENT_AMMETERID "
			       + whereStr
			       + " AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"
			       + loginId + "'))" + fzzdstr + ") ";//
				

				
			
				  System.out.println("人工审核电费列表导出："+sql.toString());
		            db.connectDb();
					conn = db.getConnection();
					sta = conn.createStatement();
					
					rs = sta.executeQuery(sql.toString());//运行sql
					
				while(rs.next()){
					ElectricFeesFormBean formbean=new ElectricFeesFormBean();
					formbean.setJzname(rs.getString(1)!=null?rs.getString(1):"");
					formbean.setEdhdl(rs.getString(2)!=null?rs.getString(2):"");
					formbean.setLastdatetime(rs.getString(3)!=null?rs.getString(3):"");
					formbean.setThisdatetime(rs.getString(4)!=null?rs.getString(4):"");
					formbean.setDbid(rs.getString(5)!=null?rs.getString(5):"");
					formbean.setElectricfeeId(rs.getString(6)!=null?rs.getString(6):"");
					formbean.setDfuuid(rs.getString(7)!=null?rs.getString(7):"");
					formbean.setAutoauditdescription(rs.getString(8)!=null?rs.getString(8):"");
					formbean.setDlsh(rs.getString(9)!=null?rs.getString(9):"");
					formbean.setDlshzt(rs.getString(10)!=null?rs.getString(10):"");
					formbean.setStationtype(rs.getString(11)!=null?rs.getString(11):"");
					formbean.setUnitprice(rs.getString(12)!=null?rs.getString(12):"");
					formbean.setFloatpay(rs.getString(13)!=null?rs.getString(13):"");
					formbean.setActualpay(rs.getString(14)!=null?rs.getString(14):"");
					formbean.setAutoauditstatus(rs.getString(15)!=null?rs.getString(15):"");
					formbean.setEntrytime(rs.getString(16)!=null?rs.getString(16):"");
					formbean.setAccountmonth(rs.getString(17)!=null?rs.getString(17):"");
					formbean.setNotetypeid(rs.getString(18)!=null?rs.getString(18):"");
					formbean.setManualauditstatus(rs.getString(19)!=null?rs.getString(19):"");
					formbean.setFeebz(rs.getString(20)!=null?rs.getString(20):"");
					formbean.setBlhdl(rs.getString(21)!=null?rs.getString(21):"");
					formbean.setLinelosstype(rs.getString(22)!=null?rs.getString(22):"");
					formbean.setLinelossvalue(rs.getString(23)!=null?rs.getString(23):"");
					formbean.setBeilv(rs.getString(24)!=null?rs.getString(24):"");
					formbean.setShi(rs.getString(25)!=null?rs.getString(25):"");
					formbean.setXian(rs.getString(26)!=null?rs.getString(26):"");
					formbean.setXiaoqu(rs.getString(27)!=null?rs.getString(27):"");
					formbean.setCbbl(rs.getString(28)!=null?rs.getString(28):"");
					formbean.setQsdbdl(rs.getString(29)!=null?rs.getString(29):"");
					formbean.setPjjedf(rs.getString("PJJE")!=null?rs.getString("PJJE"):"");
					formbean.setDedhdl(rs.getString("DEDHDL")!=null?rs.getString("DEDHDL"):"");
					formbean.setZdid(rs.getString("ZDID")!=null?rs.getString("ZDID"):"");
	
					list.add(formbean);
					
				}
				
				  System.out.println("人工审核电费列表导出2："+sql2.toString());
					rs = sta.executeQuery(sql2.toString());//运行sql
					while(rs.next()){
						ElectricFeesFormBean formbean=new ElectricFeesFormBean();
						formbean.setJzname(rs.getString(1)!=null?rs.getString(1):"");
						formbean.setEdhdl(rs.getString(2)!=null?rs.getString(2):"");
						formbean.setLastdatetime(rs.getString(3)!=null?rs.getString(3):"");
						formbean.setThisdatetime(rs.getString(4)!=null?rs.getString(4):"");
						formbean.setDbid(rs.getString(5)!=null?rs.getString(5):"");
						formbean.setElectricfeeId(rs.getString(6)!=null?rs.getString(6):"");
						formbean.setDfuuid(rs.getString(7)!=null?rs.getString(7):"");
						formbean.setAutoauditdescription(rs.getString(8)!=null?rs.getString(8):"");
						formbean.setDlsh(rs.getString(9)!=null?rs.getString(9):"");
						formbean.setDlshzt(rs.getString(10)!=null?rs.getString(10):"");
						formbean.setStationtype(rs.getString(11)!=null?rs.getString(11):"");
						formbean.setUnitprice(rs.getString(12)!=null?rs.getString(12):"");
						formbean.setFloatpay(rs.getString(13)!=null?rs.getString(13):"");
						formbean.setActualpay(rs.getString(14)!=null?rs.getString(14):"");
						formbean.setAutoauditstatus(rs.getString(15)!=null?rs.getString(15):"");
						formbean.setEntrytime(rs.getString(16)!=null?rs.getString(16):"");
						formbean.setAccountmonth(rs.getString(17)!=null?rs.getString(17):"");
						formbean.setNotetypeid(rs.getString(18)!=null?rs.getString(18):"");
						formbean.setManualauditstatus(rs.getString(19)!=null?rs.getString(19):"");
						formbean.setFeebz(rs.getString(20)!=null?rs.getString(20):"");
						formbean.setBlhdl(rs.getString(21)!=null?rs.getString(21):"");
						formbean.setLinelosstype(rs.getString(22)!=null?rs.getString(22):"");
						formbean.setLinelossvalue(rs.getString(23)!=null?rs.getString(23):"");
						formbean.setBeilv(rs.getString(24)!=null?rs.getString(24):"");
						formbean.setShi(rs.getString(25)!=null?rs.getString(25):"");
						formbean.setXian(rs.getString(26)!=null?rs.getString(26):"");
						formbean.setXiaoqu(rs.getString(27)!=null?rs.getString(27):"");
						formbean.setCbbl(rs.getString(28)!=null?rs.getString(28):"");
						formbean.setQsdbdl(rs.getString(29)!=null?rs.getString(29):"");
						formbean.setPjjedf(rs.getString("PJJE")!=null?rs.getString("PJJE"):"");
						formbean.setDedhdl(rs.getString("DEDHDL")!=null?rs.getString("DEDHDL"):"");
						formbean.setZdid(rs.getString("ZDID")!=null?rs.getString("ZDID"):"");
		
						list.add(formbean);
					}
			}catch (DbException de) {
				de.printStackTrace();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			//System.out.println(list.toString() + "------------------");
			return list;
		}
	//市级电费审核总条数，审核通过条数，审核未通过条数
	  public synchronized List<ElectricFeesFormBean> getCounttt(String whereStr,String loginId){
			String sql = "",sql1="",sql2="",sql3="";
			List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
		    String count="";
			DataBase db = new DataBase();
			String fzzdstr="";
			ResultSet rs = null,rs1=null,rs2=null,rs3=null;
			try {
				db.connectDb();
				try {
				
				
					fzzdstr = getFuzeZdid(db, loginId);
				sql=" select count(*) countts,sum(ef.actualpay) summoney,count(decode(ef.cityaudit, '1', 1)) tg,count(decode(ef.cityaudit, '0', 1)) wtg "+
				"from  dianfeiview ef,zhandian zd,dianbiao d,dianduview am  where  d.dbid=am.ammeterid_fk and zd.id=d.zdid and zd.qyzt='1' and am.ammeterdegreeid=ef.ammeterdegreeid_fk  and ef.manualauditstatus = '1' "+whereStr+" " +
				"and ef.cityaudit <= '1' and (d.dfzflx='dfzflx01' or d.dfzflx='dfzflx03' ) " +
				"and ((zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")";
				sql1=" select count(*) countts,sum(ef.money) summoney,count(decode(ef.cityaudit, '1', 1)) tg,count(decode(ef.cityaudit, '0', 1)) wtg"+
				" from yufufeiview ef, zhandian zd, dianbiao d where zd.id = d.zdid  and zd.qyzt = '1' and ef.prepayment_ammeterid=d.dbid "+whereStr+
				" and (d.dfzflx='dfzflx02' or d.dfzflx='dfzflx03' or d.dfzflx='dfzflx04')"+
				" and ef.cityaudit <= '1' AND EF.FINANCEAUDIT = '1'  and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+")";
		
				 System.out.println("市级审核条数1"+sql.toString());
	          		System.out.println("市级审核条数2"+sql1.toString());
				
	         String  s1="",s2="",s3="",s4="",s5="",s6="",s7="",s8="",s9="";
	         int n=0;double d=0;int m=0,s=0;
	         rs = db.queryAll(sql.toString());
	          		 while(rs.next()){
	          			ElectricFeesFormBean formbean=new ElectricFeesFormBean();
	          			s2=rs.getString(1)!=null?rs.getString(1):"0";
	          			s3=rs.getString(2)!=null?rs.getString(2):"0";
	          			s4=rs.getString(3)!=null?rs.getString(3):"0";
	          			s5=rs.getString(4)!=null?rs.getString(4):"0";
	          			
	          		 }
	          		rs1 = db.queryAll(sql1.toString());
	          		 while(rs1.next()){
	          			 s9=rs1.getString(1)!=null?rs1.getString(1):"0";
	          			s6=rs1.getString(2)!=null?rs1.getString(2):"0";
	          			s7=rs1.getString(3)!=null?rs1.getString(3):"0";
	          			s8=rs1.getString(4)!=null?rs1.getString(4):"0";
	          		 }
	          		n=(Integer.parseInt(s2)+Integer.parseInt(s9));
	          		 d=(Double.parseDouble(s3)+Double.parseDouble(s6));
	          		 m=(Integer.parseInt(s4)+Integer.parseInt(s7));
	          		s=(Integer.parseInt(s5)+Integer.parseInt(s8));
	          		DecimalFormat df=new DecimalFormat("0.00");
	          		d=Double.parseDouble(df.format(d));
	          		//System.out.println("m:"+m+"n: "+n+"d: "+d+"s: "+s);
	          		ElectricFeesFormBean formbean=new ElectricFeesFormBean();
	          		 formbean.setCountts(n+"");
	          			formbean.setSummoney(d+"");
						formbean.setTg(m+"");
						formbean.setWtg(s+"");
						list.add(formbean);
	          		
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
	          }
			}
			catch (DbException de) {
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
				if (rs1 != null) {
					try {
						rs1.close();
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
	
	//=========市级电费审核 实际电费金额====
		public String getCountGreed2(String whereStr,String loginId,String str){
		    String count="";
			DataBase db = new DataBase();
			String fzzdstr="";
			ResultSet rs = null;
			try {
				db.connectDb();
				try {
					fzzdstr = getFuzeZdid(db,loginId);
				
				StringBuffer strall = new StringBuffer();
			      strall.append("select sum(to_char(ACTUALPAY, 'fm9999999990.00')) " +
			    		  " from  electricview ef,zhandian zd,dianbiao d,dianduview am  where  am.ammeterdegreeid(+)=ef.ammeterdegreeid_fk and zd.id=d.zdid and zd.qyzt='1' and d.dbid=ef.dbid "+whereStr+" " +
							" and ef.cityaudit < '2' " +
							" and ef.manualauditstatus = '1' " +str+
							" and ((zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+ fzzdstr + ")");
				System.out.println("市级电费审核 实际电费金额"+strall.toString());
				rs = db.queryAll(strall.toString());
	           
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
	
	
	
	// 市级审核电费查询
	public synchronized List<ElectricFeesFormBean> getPageDataCheckCity(String whereStr,
			String loginId) {
		
		System.out.println("ElectricFeesBean审核电费列表条件:" + whereStr);
		List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "",sql1="";
		String fzzdstr = "";
		ResultSet rs = null;
		ResultSet rs1 = null;
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			sql="SELECT ZD.ZDCODE,to_char(EF.ACCOUNTMONTH,'yyyy-mm'), ZD.JZNAME,"+
			"D.DBID,ZD.PROPERTY,D.DFZFLX," +
			"(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"+
			"(CASE WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END) || (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ,"+
			"EF.NOTETYPEID,D.LINELOSSTYPE,D.LINELOSSVALUE,D.BEILV,"+
			"EF.ELECTRICFEE_ID,EF.DFUUID,EF.ACTUALPAY, EF.AUTOAUDITSTATUS,EF.NOTENO,EF.MANUALAUDITSTATUS,EF.CITYAUDIT,ZD.EDHDL,AM.lastdatetime,AM.thisdatetime,EF.UNITPRICE,'1' as dfbzw,AM.BLHDL,AM.DEDHDL, zd.zlfh,zd.jlfh," +
			" (CASE WHEN LENGTH(AM.LASTDATETIME) = 10 AND LENGTH(AM.THISDATETIME) = 10 AND ((CEIL(TO_DATE(AM.THISDATETIME, 'yyyy-mm-dd') - TO_DATE(AM.LASTDATETIME, 'yyyy-mm-dd'))) >= 0) THEN "+
            " (CEIL(TO_DATE(AM.THISDATETIME, 'yyyy-mm-dd') -  TO_DATE(AM.LASTDATETIME, 'yyyy-mm-dd') + 1)) END) JSZQ,ZD.QSDBDL,EF.PJJE " +
			" FROM citycheck_ddf EF,ZHANDIAN ZD,DIANBIAO D,citycheck_ddv AM  WHERE  D.DBID=AM.AMMETERID_FK AND ZD.ID=D.ZDID AND AM.AMMETERDEGREEID=EF.AMMETERDEGREEID_FK  AND (EF.MANUALAUDITSTATUS = '-1' OR EF.MANUALAUDITSTATUS = '1')  " +
			" AND (D.DFZFLX='dfzflx01' OR D.DFZFLX='dfzflx03' ) "+whereStr+" " +
			" AND ((ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+")"; 
			
			sql1="SELECT ZD.ZDCODE,to_char(EF.ACCOUNTMONTH,'yyyy-mm'), ZD.JZNAME,"+
			"D.DBID,ZD.PROPERTY,D.DFZFLX,"+
			"(SELECT NAME  FROM INDEXS WHERE CODE = ZD.STATIONTYPE  AND TYPE = 'stationtype') AS STATIONTYPE,"+
			"(CASE WHEN ZD.XIAN IS NOT NULL THEN  (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) "+
			"ELSE  ''  END) || (CASE  WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ,"+
			"EF.NOTETYPEID,D.LINELOSSTYPE,"+
			"D.LINELOSSVALUE,D.BEILV, EF.UUID, EF.MONEY, EF.NOTENO, EF.CITYAUDIT,EF.ID,ZD.EDHDL,'2' as dfbzw , zd.zlfh,zd.jlfh," +
			"(CASE WHEN LENGTH(ef.startdate) = 10 AND LENGTH(ef.enddate) = 10 AND ((CEIL(TO_DATE(ef.enddate, 'yyyy-mm-dd') - TO_DATE(ef.startdate, 'yyyy-mm-dd'))) >= 0) THEN"+
            " (CEIL(TO_DATE(ef.enddate, 'yyyy-mm-dd') -  TO_DATE(ef.startdate, 'yyyy-mm-dd') + 1)) END) JSZQ,ZD.QSDBDL,EF.PJJE "+
			" FROM citycheck_yff EF, ZHANDIAN ZD, DIANBIAO D WHERE ZD.ID = D.ZDID AND EF.PREPAYMENT_AMMETERID=D.DBID "+whereStr+
			" AND (D.DFZFLX='dfzflx02' OR D.DFZFLX='dfzflx03' OR D.DFZFLX='dfzflx04')"+
			" AND EF.CITYAUDIT <= '1' AND EF.FINANCEAUDIT <= '1'  AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))"+fzzdstr+") ";	
			db.connectDb();
			   System.out.println("市级审核查询1"+sql.toString());
			    System.out.println("市级审核查询2"+sql1.toString());
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				ElectricFeesFormBean feesbeana=new ElectricFeesFormBean();
				feesbeana.setZdcode(rs.getString(1));
				feesbeana.setAccountmonth(rs.getString(2));
		        feesbeana.setJzname(rs.getString(3));
		        feesbeana.setDbid(rs.getString(4));
		        feesbeana.setProperty(rs.getString(5));
		        feesbeana.setDfzflx(rs.getString(6));
		        feesbeana.setStationtype(rs.getString(7));
		        feesbeana.setSzdq(rs.getString(8));
		        feesbeana.setNotetypeid(rs.getString(9));
		        feesbeana.setLinelosstype(rs.getString(10));
		        feesbeana.setLinelossvalue(rs.getString(11));
		        feesbeana.setBeilv(rs.getString(12));
		        feesbeana.setElectricfeeId(rs.getString(13));
		        feesbeana.setDfuuid(rs.getString(14));
		       feesbeana.setActualpay(rs.getString(15));
		       feesbeana.setAutoauditstatus(rs.getString(16));
		       feesbeana.setNoteno(rs.getString(17));
		       feesbeana.setManualauditstatus(rs.getString(18));
		       feesbeana.setCityaudit(rs.getString(19));
		       feesbeana.setEdhdl(rs.getString(20));
		       feesbeana.setLastdatetime(rs.getString(21));
		       feesbeana.setThisdatetime(rs.getString(22));
		       feesbeana.setUnitprice(rs.getString(23));
		       feesbeana.setDfbzw(rs.getString(24));
		       feesbeana.setBlhdl(rs.getString(25));
		       feesbeana.setDedhdl(rs.getString(26));
		       feesbeana.setZlfh(rs.getString(27));
		       feesbeana.setJlfh(rs.getString(28));
		       feesbeana.setJszq(rs.getString(29));
		       feesbeana.setQsdbdl(rs.getString(30));
		       feesbeana.setPjjedf(rs.getString("PJJE"));
		       list.add(feesbeana);
			}
			rs1=db.queryAll(sql1.toString());
			while(rs1.next()){
				ElectricFeesFormBean feesbeana=new ElectricFeesFormBean();
				feesbeana.setZdcode(rs1.getString(1));
				feesbeana.setAccountmonth(rs1.getString(2));
		        feesbeana.setJzname(rs1.getString(3));
		        feesbeana.setDbid(rs1.getString(4));
		        feesbeana.setProperty(rs1.getString(5));
		        feesbeana.setDfzflx(rs1.getString(6));
		        feesbeana.setStationtype(rs1.getString(7));
		        feesbeana.setSzdq(rs1.getString(8));
		        feesbeana.setNotetypeid(rs1.getString(9));
		        feesbeana.setLinelosstype(rs1.getString(10));
		        feesbeana.setLinelossvalue(rs1.getString(11));
		        feesbeana.setBeilv(rs1.getString(12));
		        feesbeana.setDfuuid(rs1.getString(13));
			    feesbeana.setActualpay(rs1.getString(14));
			    feesbeana.setNoteno(rs1.getString(15));
			    feesbeana.setCityaudit(rs1.getString(16));
			    feesbeana.setElectricfeeId(rs1.getString(17));
			    feesbeana.setEdhdl(rs1.getString(18));
			    feesbeana.setDfbzw(rs1.getString(19));
			    feesbeana.setZlfh(rs1.getString(20));
			    feesbeana.setJlfh(rs1.getString(21));
			    feesbeana.setJszq(rs1.getString(22));
			    feesbeana.setQsdbdl(rs1.getString(23));
			    feesbeana.setPjjedf(rs1.getString("PJJE"));
			    list.add(feesbeana);
			}
		
   // System.out.println("市级审核查询1"+sql.toString());
    //System.out.println("市级审核查询2"+sql1.toString());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// System.out.println("市级11审核"+sql.toString());
		//System.out.println(list.toString() + "------------------");
		return list;
	}
	//人工电费二级审核导出
	public synchronized List<ElectricFeesFormBean> getPageDataCheckCitydaochu(String whereStr,
			String loginId) {
		
		List<ElectricFeesFormBean> list = new ArrayList<ElectricFeesFormBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "",sql1="";
		ResultSet rs = null;
		ResultSet rs1 = null;
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			//1207
			sql="select zd.zdcode,ef.accountmonth, zd.jzname,(select t.name from indexs t where t.code = zd.jztype and type='ZDLX') as jztype,d.dbid,(select t.name from indexs t where t.code = zd.property and TYPE='ZDSX') as property,(select i.name from indexs  i where i.code=d.dfzflx and i.type='dfzflx')as dfzflx," +
			" (case when zd.edhdl>0 and  length(am.lastdatetime)=10 and length(am.thisdatetime)=10 and ((ceil(TO_DATE(am.Thisdatetime, 'yyyy-mm-dd') -TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd')))>=0) "+
		      " then (am.blhdl/(ceil(TO_DATE(am.Thisdatetime, 'yyyy-mm-dd') -TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd')+1))) "+
		      " end )/zd.edhdl as cbbl, "+
			"(select NAME from indexs where CODE = zd.STATIONTYPE and type='stationtype') as stationtype,"+
			" (case  when zd.xian is not null then (select distinct agname from d_area_grade where agcode = zd.xian) else '' end) || (case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = zd.xiaoqu) else '' end) as szdq, "+
			"(select NAME from indexs where CODE = ef.notetypeid and type = 'PJLX') as NOTETYPEID,"+
			    "(select name from indexs where code = d.LINELOSSTYPE and type = 'xslx') LINELOSSTYPE,d.LINELOSSVALUE,d.BEILV,"+
				"ef.ELECTRICFEE_ID,ef.DFUUID,ef.ACTUALPAY, ef.AUTOAUDITSTATUS,ef.NOTENO,ef.PAYOPERATOR,ef.PAYDATETIME,ef.manualauditstatus,ef.cityaudit " +
				"from  dianfeiview ef,zhandian zd,dianbiao d,dianduview am  where  d.dbid=am.ammeterid_fk and zd.id=d.zdid and zd.qyzt='1' and am.ammeterdegreeid=ef.ammeterdegreeid_fk  and ef.manualauditstatus = '1' "+whereStr+" " +
				"and ef.cityaudit <= '1' " +
				"and ((zd.xiaoqu in(select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") order by  (case when zd.edhdl > 0 and length(am.lastdatetime) = 10 and length(am.thisdatetime) = 10 and ((ceil(TO_DATE(am.Thisdatetime, 'yyyy-mm-dd') -  TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd'))) >= 0) then "+
       "  (am.blhdl /(ceil(TO_DATE(am.Thisdatetime, 'yyyy-mm-dd') -TO_DATE(am.LASTDATETIME, 'yyyy-mm-dd') + 1))) end) / zd.edhdl ";
//1207
			sql1="select zd.zdcode,ef.accountmonth, zd.jzname,(select t.name from indexs t where t.code = zd.jztype and type = 'ZDLX') as jztype, "+
			  " d.dbid, (select t.name from indexs t where t.code = zd.property and TYPE = 'ZDSX') as property, "+
			  " (select i.name from indexs i where i.code = d.dfzflx and i.type = 'dfzflx') as dfzflx,"+
			  " (select NAME  from indexs where CODE = zd.STATIONTYPE  and type = 'stationtype') as stationtype,"+
			   " (case when zd.xian is not null then  (select distinct agname from d_area_grade where agcode = zd.xian) "+
			    " else  ''  end) || (case  when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = zd.xiaoqu) else '' end) as szdq, "+
			" (select NAME  from indexs  where CODE = ef.notetypeid and type = 'PJLX') as NOTETYPEID, "+
			" (select name from indexs  where code = d.LINELOSSTYPE and type = 'xslx') LINELOSSTYPE, "+
			" d.LINELOSSVALUE,  d.BEILV, ef.uuid, ef.money, ef.NOTENO,ef.PAYOPERATOR,ef.PAYDATETIME, ef.cityaudit "+
			" from yufufeiview ef, zhandian zd, dianbiao d where zd.id = d.zdid  and zd.qyzt = '1' and ef.prepayment_ammeterid=d.dbid "+whereStr+
			" and ef.cityaudit <= '1' AND EF.FINANCEAUDIT = '1'  and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"'))"+fzzdstr+") ";
			rs=db.queryAll(sql.toString());
			while(rs.next()){
				ElectricFeesFormBean feesbeana=new ElectricFeesFormBean();
				feesbeana.setZdcode(rs.getString(1));
				feesbeana.setAccountmonth(rs.getString(2));
		        feesbeana.setJzname(rs.getString(3));
		        feesbeana.setJztype(rs.getString(4));
		        feesbeana.setDbid(rs.getString(5));
		        feesbeana.setProperty(rs.getString(6));
		        feesbeana.setDfzflx(rs.getString(7));
		        feesbeana.setCbbl(rs.getString(8));
		        feesbeana.setStationtype(rs.getString(9));
		        feesbeana.setSzdq(rs.getString(10));
		        feesbeana.setNotetypeid(rs.getString(11));
		        feesbeana.setLinelosstype(rs.getString(12));
		        feesbeana.setLinelossvalue(rs.getString(13));
		        feesbeana.setBeilv(rs.getString(14));
		        feesbeana.setElectricfeeId(rs.getString(15));
		        feesbeana.setDfuuid(rs.getString(16));
		       feesbeana.setActualpay(rs.getString(17));
		       feesbeana.setAutoauditstatus(rs.getString(18));
		       feesbeana.setNoteno(rs.getString(19));
		       feesbeana.setPayoperator(rs.getString(20));
		       feesbeana.setPaydatetime(rs.getString(21));
		       feesbeana.setManualauditstatus(rs.getString(22));
		       feesbeana.setCityaudit(rs.getString(23));
		       list.add(feesbeana);
				
			}
			rs1=db.queryAll(sql1.toString());
			while(rs1.next()){
				ElectricFeesFormBean feesbeana=new ElectricFeesFormBean();
				feesbeana.setZdcode(rs1.getString(1));
				feesbeana.setAccountmonth(rs1.getString(2));
		        feesbeana.setJzname(rs1.getString(3));
		        feesbeana.setJztype(rs1.getString(4));
		        feesbeana.setDbid(rs1.getString(5));
		        feesbeana.setProperty(rs1.getString(6));
		        feesbeana.setDfzflx(rs1.getString(7));
		        feesbeana.setStationtype(rs1.getString(8));
		        feesbeana.setSzdq(rs1.getString(9));
		        feesbeana.setNotetypeid(rs1.getString(10));
		        feesbeana.setLinelosstype(rs1.getString(11));
		        feesbeana.setLinelossvalue(rs1.getString(12));
		        feesbeana.setBeilv(rs1.getString(13));
		        feesbeana.setDfuuid(rs1.getString(14));
			    feesbeana.setActualpay(rs1.getString(15));
			    feesbeana.setPayoperator(rs1.getString(16));
			    feesbeana.setPaydatetime(rs1.getString(17));
			    feesbeana.setCityaudit(rs1.getString(18));
			    list.add(feesbeana);
				
			}
    System.out.println("市级审核导出1"+sql.toString());
    System.out.println("市级审核导出2"+sql1.toString());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	//人工电费二级审核电费单详细信息
	public synchronized ArrayList getPageDataCheck1(String dfid,String zdcode) {
        int j = dfid.indexOf("ssss=");
        String dfid1 = dfid.substring(0,j);
        String zflx1 = dfid.substring(j+5,dfid.length());
		ArrayList<String> list = new ArrayList<String>();
		String sql = "";
		String sql1 = "";
		DataBase db = new DataBase();
			sql="SELECT (CASE WHEN Z.SHI IS NOT NULL THEN(SELECT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI)ELSE''END) " +
			"|| (CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN) ELSE''END)" +
			" || (CASE WHEN Z.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU) ELSE '' END) AS SZDQ," +
			"Z.JZNAME,(SELECT II.NAME FROM INDEXS II WHERE Z.JZTYPE=II.CODE ) AS JZTYPE,DD.INPUTOPERATOR,DD.INPUTDATETIME,D.DBID," +
			"(SELECT YDSB.NAME FROM INDEXS YDSB WHERE YDSB.CODE=D.YDSB AND TYPE='YDLX') AS YDSB,(SELECT DLLX.NAME FROM INDEXS DLLX WHERE DLLX.CODE=D.DLLX ) AS DLLX," +
			"DD.AMMETERDEGREEID,DD.LASTDATETIME, DD.THISDATETIME,DD.LASTDEGREE,DD.THISDEGREE,DD.FLOATDEGREE,D.BEILV,DD.ACTUALDEGREE," +
			"DF.ACCOUNTMONTH,DF.UNITPRICE,DF.FLOATPAY, DF.ACTUALPAY,(SELECT FFFS.NAME FROM INDEXS FFFS WHERE FFFS.CODE=D.DFZFLX)FFFS," +
			"(SELECT PJLX.NAME FROM INDEXS PJLX WHERE PJLX.CODE=DF.NOTETYPEID) AS PJLX,DF.NOTENO,DF.PAYOPERATOR,DF.AUTOAUDITSTATUS,DF.MANUALAUDITSTATUS " +
			"FROM ZHANDIAN Z,DIANBIAO D,DIANDUVIEW DD,DIANFEIVIEW DF  WHERE Z.ID=D.ZDID  AND Z.QYZT='1'  AND  D.DBID=DD.AMMETERID_FK " +
			"AND DD.AMMETERDEGREEID=DF.AMMETERDEGREEID_FK AND DF.ELECTRICFEE_ID='"+dfid1+"'"+" AND Z.ZDCODE='"+zdcode+"'";
			sql1="SELECT (CASE WHEN Z.SHI IS NOT NULL THEN(SELECT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.SHI)ELSE''END) " +
			"|| (CASE WHEN Z.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAN) ELSE''END)" +
			" || (CASE WHEN Z.XIAOQU IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = Z.XIAOQU) ELSE '' END) AS SZDQ," +
			"Z.JZNAME,(SELECT II.NAME FROM INDEXS II WHERE Z.JZTYPE=II.CODE ) AS JZTYPE,'' AS INPUTOPERATOR,'' AS INPUTDATETIME,D.DBID," +
			"(SELECT YDSB.NAME FROM INDEXS YDSB WHERE YDSB.CODE=D.YDSB AND TYPE='YDLX') AS YDSB,(SELECT DLLX.NAME FROM INDEXS DLLX WHERE DLLX.CODE=D.DLLX ) AS DLLX," +
			"DF.AMMETERDEGREEID_FK AS AMMETERDEGREEID,'' AS LASTDATETIME,'' AS THISDATETIME,'' AS LASTDEGREE,'' AS THISDEGREE,'' AS FLOATDEGREE,D.BEILV,'' AS ACTUALDEGREE," +
			"DF.ACCOUNTMONTH,DF.DANJIA AS UNITPRICE,'' AS FLOATPAY, DF.MONEY AS ACTUALPAY,(SELECT FFFS.NAME FROM INDEXS FFFS WHERE FFFS.CODE=D.DFZFLX) AS FFFS," +
			"(SELECT PJLX.NAME FROM INDEXS PJLX WHERE PJLX.CODE=DF.NOTETYPEID) AS PJLX,DF.NOTENO,DF.PAYOPERATOR,'1' AS AUTOAUDITSTATUS,'1' AS MANUALAUDITSTATUS  " +
			"FROM ZHANDIAN Z,DIANBIAO D,YUFUFEIVIEW DF  WHERE Z.ID=D.ZDID  AND D.DBID= DF.PREPAYMENT_AMMETERID " +
			"AND Z.QYZT='1' AND DF.ID='"+dfid1+"'"+" AND Z.ZDCODE='"+zdcode+"'";

		ResultSet rs = null;
		try {
			db.connectDb();
			if(db.queryAll(sql.toString()).next()){
				rs = db.queryAll(sql.toString());
				System.out.println("人工电费二级审核电费单详细信息:"+sql.toString());
			}else{
				rs = db.queryAll(sql1.toString());
				System.out.println("人工电费二级审核电费单详细信息1:"+sql1.toString());
			}
			while(rs.next()){
				list.add(null==rs.getString(1)?"":rs.getString(1));
				list.add(null==rs.getString(2)?"":rs.getString(2));
				list.add(null==rs.getString(3)?"":rs.getString(3));
				list.add(null==rs.getString(4)?"":rs.getString(4));
				list.add(null==rs.getString(5)?"":rs.getString(5));
				list.add(null==rs.getString(6)?"":rs.getString(6));
				list.add(null==rs.getString(7)?"":rs.getString(7));
				list.add(null==rs.getString(8)?"":rs.getString(8));
				list.add(null==rs.getString(9)?"":rs.getString(9));
				list.add(null==rs.getString(10)?"":rs.getString(10));
				list.add(null==rs.getString(11)?"":rs.getString(11));
				list.add(null==rs.getString(12)?"":rs.getString(12));
				list.add(null==rs.getString(13)?"":rs.getString(13));
				list.add(null==rs.getString(14)?"":rs.getString(14));
				list.add(null==rs.getString(15)?"":rs.getString(15));
				list.add(null==rs.getString(16)?"":rs.getString(16));
				list.add(null==rs.getString(17)?"":rs.getString(17));
				list.add(null==rs.getString(18)?"":rs.getString(18));
				list.add(null==rs.getString(19)?"":rs.getString(19));
				list.add(null==rs.getString(20)?"":rs.getString(20));
				list.add(null==rs.getString(21)?"":rs.getString(21));
				list.add(null==rs.getString(22)?"":rs.getString(22));
				list.add(null==rs.getString(23)?"":rs.getString(23));
				list.add(null==rs.getString(24)?"":rs.getString(24));
				list.add(null==rs.getString(25)?"":rs.getString(25));
				list.add(null==rs.getString(26)?"":rs.getString(26));
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
	//人工电费二级审核预付费详细信息
	public synchronized ArrayList getPageCityaudit(String dfid) {
		
		ArrayList<String> list = new ArrayList<String>();
		String sql = "";
		DataBase db = new DataBase();
		sql="select  (case when z.shi is not null then(select agname from d_area_grade where agcode = z.shi)else''end)" +
				" || (case when z.xian is not null then(select distinct agname from d_area_grade where agcode = z.xian) else''end)" +
				" || (case when z.xiaoqu is not null then(select distinct agname from d_area_grade where agcode = z.xiaoqu) else '' end) as szdq," +
				"z.jzname,(select ii.name from indexs ii where z.jztype=ii.code ) as jztype,dd.inputoperator,dd.inputdatetime,d.dbid," +
				"(select ydsb.name from indexs ydsb where ydsb.code=d.ydsb)ydsb,(select dllx.name from indexs dllx where dllx.code=d.dllx)dllx," +
				" dd.ammeterdegreeid,dd.lastdatetime, dd.thisdatetime,dd.lastdegree,dd.thisdegree,dd.floatdegree,d.beilv,dd.actualdegree," +
				"df.accountmonth,df.unitprice,df.floatpay, df.actualpay,(select fffs.name from indexs fffs where fffs.code=d.dfzflx)fffs, " +
				"(select pjlx.name from indexs pjlx where pjlx.code=df.notetypeid)pjlx,df.noteno,df.payoperator, df.autoauditstatus,df.manualauditstatus " +
				" from zhandian z,dianbiao d,dianduview dd,dianfeiview df  where z.id=d.zdid  and z.qyzt='1' and  d.dbid=dd.ammeterid_fk  " +
				"and  dd.ammeterdegreeid=df.ammeterdegreeid_fk and  df.electricfee_id='"+dfid+"'";

		ResultSet rs = null;
		try {
			db.connectDb();
		
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				list.add(null==rs.getString(1)?"":rs.getString(1));
				list.add(null==rs.getString(2)?"":rs.getString(2));
				list.add(null==rs.getString(3)?"":rs.getString(3));
				list.add(null==rs.getString(4)?"":rs.getString(4));
				list.add(null==rs.getString(5)?"":rs.getString(5));
				list.add(null==rs.getString(6)?"":rs.getString(6));
				list.add(null==rs.getString(7)?"":rs.getString(7));
				list.add(null==rs.getString(8)?"":rs.getString(8));
				list.add(null==rs.getString(9)?"":rs.getString(9));
				list.add(null==rs.getString(10)?"":rs.getString(10));
				list.add(null==rs.getString(11)?"":rs.getString(11));
				list.add(null==rs.getString(12)?"":rs.getString(12));
				list.add(null==rs.getString(13)?"":rs.getString(13));
				list.add(null==rs.getString(14)?"":rs.getString(14));
				list.add(null==rs.getString(15)?"":rs.getString(15));
				list.add(null==rs.getString(16)?"":rs.getString(16));
				list.add(null==rs.getString(17)?"":rs.getString(17));
				list.add(null==rs.getString(18)?"":rs.getString(18));
				list.add(null==rs.getString(19)?"":rs.getString(19));
				list.add(null==rs.getString(20)?"":rs.getString(20));
				list.add(null==rs.getString(21)?"":rs.getString(21));
				list.add(null==rs.getString(22)?"":rs.getString(22));
				list.add(null==rs.getString(23)?"":rs.getString(23));
				list.add(null==rs.getString(24)?"":rs.getString(24));
				list.add(null==rs.getString(25)?"":rs.getString(25));
				list.add(null==rs.getString(26)?"":rs.getString(26));
			}
			System.out.println("人工电费二级审核预付费详细信息 "+sql.toString());
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
		//System.out.println(list.toString() + "------------------");
		return list;
	}

	private int allPage;

	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}

	public int getAllPage() {
		return this.allPage;
	}

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

	/**
	 * 添加电费
	 */
	public synchronized String addElectricFees(ElectricFeesFormBean formBean,String start,String end) {
		// birthday = birthday.length()>0?birthday:null;
		String msg = "保存电费失败！请重试或与管理员联系！";
		MD5 md = new MD5();
		CTime ctime = new CTime();

		// 自动审核判断
		String sysprice = formBean
				.getJizhanPrice(formBean.getAmmeterdegreeid()).getSysprice();
		if (sysprice == null) {// 电费电量入录
			sysprice = formBean.getJizhanPriceAm(formBean.getAmmererid());
		}
		System.out.println("系统单价:" + sysprice);
		AutoAuditBean bean = new AutoAuditBean();
		ArrayList fylist = new ArrayList();
		fylist = bean.getPageData(1, "");
		String ItemID = "", ItemName = "", ItemValue = "", ItemDescription = "";
		String autoauditstatus = "1", autoauditdescription = "", autoauditdescription1 = "", autoauditdescription2 = "", autoauditdescription3 = "";
		String auditfee1 = "";
		String auditfee2 = "";
		String auditfee3 = "";
		String floatpay = formBean.getFloatpay();
		String memo = formBean.getMemo();
		String af3_bz = "0";
		String floatpay_bz = "";
		String memo_bz = "";
		if (floatpay == null || floatpay == "" || floatpay.equals("0")|| floatpay.equals("0.0")|| floatpay.equals("0.00")) {
			floatpay_bz = "0";
		} else {
			floatpay_bz = "1";
		}
		if (memo == null || memo == "") {
			memo_bz = "0";
		} else {
			memo_bz = "1";
		}
		if (memo_bz.equals(floatpay_bz)) {
			af3_bz = "1";
		}
		if (fylist != null) {
			int fycount = ((Integer) fylist.get(0)).intValue();
			for (int k = fycount; k < fylist.size() - 1; k += fycount) {

				ItemID = (String) fylist.get(k + fylist.indexOf("ITEMID"));
				ItemName = (String) fylist.get(k + fylist.indexOf("ITEMNAME"));
				ItemValue = (String) fylist
						.get(k + fylist.indexOf("ITEMVALUE"));
				ItemDescription = (String) fylist.get(k
						+ fylist.indexOf("ITEMDESCRIPTION"));
				if (ItemName.equals("AuditFee1") && (!ItemValue.equals("0"))
						&& (formBean.getNoteno().equals(""))) {
					System.out.println(ItemName + "*****" + ItemValue + "****"
							+ formBean.getNoteno());
					autoauditstatus = "0";
					autoauditdescription1 = "票据为空！";
					auditfee1 = "0";
				}

				if (ItemName.equals("AuditFee2") && (!ItemValue.equals("0"))
						&& (!formBean.getUnitprice().equals(sysprice))) {
					System.out.println(ItemName + "*****" + ItemValue + "****"
							+ formBean.getUnitprice());
					autoauditstatus = "0";
					autoauditdescription2 = "本次单价与系统单价不符！";
					auditfee2 = "0";
				}

				if (ItemName.equals("AuditFee3") && (!ItemValue.equals("0"))
						&& af3_bz.equals("0")) {
					System.out.println(ItemName + "*****" + ItemValue + "****"
							+ formBean.getUnitprice());
					autoauditstatus = "0";
					autoauditdescription3 = "用电费用调整没有说明！";
					auditfee3 = "0";
				}

			}
			if (auditfee1.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription1 + ";";
			}
			if (auditfee2.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription2 + ";";
			}
			if (auditfee3.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription3 + ";";
			}
		}
		System.out.println("电费自动审核描述：" + autoauditdescription);
		// 自动审核判断
		int startn = Integer.parseInt(start.substring(0, 4));
		int starty = Integer.parseInt(start.substring(5, 7));
		int endn = Integer.parseInt(end.substring(0, 4));
		int endy = Integer.parseInt(end.substring(5, 7));
		int time = (endn - startn) * 12 + endy - starty + 1;
		long uuid = System.currentTimeMillis();
		String df = formBean.getActualpay();
		double dfyu = Double.parseDouble(df)%time;
		int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;
		List dflist = new ArrayList();

		String[] sqlBatch = new String[time];
		for (int i = 0; i < time; i++) {
			if (i == time - 1)
				dflist.add(dfPermonth + dfyu);
			else
				dflist.add(dfPermonth);
		}
		String s="to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		System.out.println("shijian:" + time + "-" + start + "-" + end);
		String update="update ammeterdegrees set feesbz='1' where uuid=(select d.uuid from ammeterdegrees d where d.ammeterdegreeid='"+formBean.getAmmeterdegreeid()+"')";
		for (int i = 0; i < time; i++) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO electricfees(AMMETERDEGREEID_FK,UNITPRICE,FLOATPAY,ACTUALPAY,NOTETYPEID,NOTENO,PAYOPERATOR,PAYDATETIME,ACCOUNTMONTH,MEMO,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,manualauditstatus,NOTETIME,KPTIME,dfuuid,cityaudit,entrypensonnel,entrytime)");
		sql.append(" VALUES('" + formBean.getAmmeterdegreeid() + "'");
		sql.append(",'" + formBean.getUnitprice() + "','"
				+ formBean.getFloatpay() + "','" + dflist.get(i)
				+ "','" + formBean.getNotetypeid() + "','"
				+ formBean.getNoteno() + "'");
		sql.append(",'" + formBean.getPayoperator() + "','"
				+ formBean.getPaydatetime() + "','"
				+ formBean.getAccountmonth() + "'" + ",'" + formBean.getMemo()
				+ "','" + autoauditstatus + "','" + autoauditdescription
				+ "','0','" + formBean.getNotetime() + "','" + formBean.getKptime()
				+ "','"+uuid+"','"+formBean.getFlag()+"','"+formBean.getEntrypensonnel()+"',"+s+")");
		System.out.println(sql.toString());
		sqlBatch[i] = sql.toString();
		}
		DataBase db = new DataBase();
		try {
			db.connectDb();
            
			db.updateBatch(sqlBatch);
			db.update(update);
			msg = "添加电费成功";
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	
	//添加合同单
	public synchronized String addBargain(BargainBean formBean,String start,String end) {
		// birthday = birthday.length()>0?birthday:null;
		String msg="";
		String kptime=formBean.getKptime();
		String notetime=formBean.getNotetime();
		String memo=formBean.getMemo();
		String notetypeid=formBean.getNotetypeid();
		
		String thisdegree=formBean.getThisdegree();
		String thisdatetime=formBean.getThisdatetime();
		String linelosstype = formBean.getLinelosstype();
		String floatpay= formBean.getFloatpay();
		String inputtoperator = formBean.getInputoperator();
		String paydatetime=formBean.getPaydatetime();
		String payoperator= formBean.getPayoperator();
		String danjia=formBean.getDanjia();
		if(formBean.getThisdegree()==null){
			thisdegree="";
		}
		if(formBean.getDanjia()==null){
			danjia="";
		}
		if(formBean.getThisdatetime()==null){
			thisdatetime="";
		}
		if(formBean.getLinelosstype()==null){
			linelosstype="";
		}
		if(formBean.getFloatpay()==null){
			floatpay="";
		}
		if(formBean.getInputoperator()==null){
			inputtoperator="";
			
		}
		if(formBean.getPaydatetime()==null){
			paydatetime="";
		}
		if(formBean.getPayoperator()==null){
			payoperator="";
		}
		if(formBean.getKptime()==null ){
			kptime=" ";			
		}else{
			kptime=formBean.getKptime();
		}
		if(formBean.getNotetime()==null ){
			notetime=" ";
		}else{
			notetime=formBean.getNotetime();
		}
		if(formBean.getMemo()==null){
			memo=" ";			
		}else{
			memo=formBean.getMemo();
		}
		if(formBean.getNotetypeid()==null){
			notetypeid=" ";
		}else{
			notetypeid=formBean.getNotetypeid();
		}
		int startn = Integer.parseInt(start.substring(0, 4));
		int starty = Integer.parseInt(start.substring(5, 7));
		int endn = Integer.parseInt(end.substring(0, 4));
		int endy = Integer.parseInt(end.substring(5, 7));
		int time = (endn - startn) * 12 + endy - starty + 1;
		
//		long uuid = System.currentTimeMillis();
		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
		String uuid = uuid2+Long.toString(uuid1).substring(3);
		
		String df = formBean.getMoney();
		double dfyu = Double.parseDouble(df)%time;
		int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;
	    //网络运营线电费(生产)分到每个月
	    double networkdfys=Double.parseDouble(formBean.getNetworkdf())%time;
	    int networkdf = (int) ((Double.parseDouble(formBean.getNetworkdf())-networkdfys)/time);
	    //信息化支撑线电费分到每个月
	    double informatizationdfys=Double.parseDouble(formBean.getInformatizationdf())%time;
	    int informatizationdf = (int) ((Double.parseDouble(formBean.getInformatizationdf())-informatizationdfys)/time);	    
	    //行政综合线电费（办公）分到每个月
	    double administrativedfys=Double.parseDouble(formBean.getAdministrativedf())%time;
	    int administrativedf = (int) ((Double.parseDouble(formBean.getAdministrativedf())-administrativedfys)/time);
	    //市场营销线电费(营业)分到每个月
	    double marketdfys=Double.parseDouble(formBean.getMarketdf())%time;
	    int marketdf = (int) ((Double.parseDouble(formBean.getMarketdf())-marketdfys)/time);
	    //建设投资线电费分到每个月
	    double builddfys=Double.parseDouble(formBean.getBuilddf())%time;
	    int builddf = (int) ((Double.parseDouble(formBean.getBuilddf())-builddfys)/time); 
	  //代垫电费线分到每个月
	    double dddfys=Double.parseDouble(formBean.getDddf())%time;
	    int dddf = (int) ((Double.parseDouble(formBean.getDddf())-dddfys)/time); 
	   	 List nwdffentan = new ArrayList();
		 List imdffentan = new ArrayList();
		 List asdffentan = new ArrayList();
		 List mkdffentan = new ArrayList();
		 List bldffentan = new ArrayList();
		 List dddffentan=new ArrayList();
		List dflist = new ArrayList();
		 List year_month = new ArrayList();
		String[] sqlBatch = new String[time];
		for (int i = 0; i < time; i++) {
			String yue = String.valueOf(starty);
	     	if(yue.length()==1)yue = "0"+yue;
	     	String year_month_tmp = startn+"-"+yue;
	     	starty ++;
	     	if(starty>12){
	     		starty = 1;
	     		startn ++;
	     	}
	     	year_month.add(year_month_tmp);
			if (i == time - 1){
				dflist.add(dfPermonth + dfyu);
				nwdffentan.add(networkdf+networkdfys);
				imdffentan.add(informatizationdf+informatizationdfys);
				asdffentan.add(administrativedf+administrativedfys);
				mkdffentan.add(marketdf+marketdfys);
				bldffentan.add(builddf+builddfys);
				dddffentan.add(dddf+dddfys);
			}else{
				dflist.add(dfPermonth);
				nwdffentan.add(networkdf);
				imdffentan.add(informatizationdf);
				asdffentan.add(administrativedf);
				mkdffentan.add(marketdf);
				bldffentan.add(builddf);
				dddffentan.add(dddf);
			}
		}
		String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		
		for (int i = 0; i < time; i++) {
			StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO PREPAYMENT(ID,STATIONID,PJJE,HTBH,PREPAYMENT_AMMETERID,FEETYPEID,MONEY,STARTDEGREE,STARTDATE,ENDDATE,INPUTDATETIME,INPUTOPERATOR,AUDITDATE,AUDITOPERATOR,FINANCIALDATE,FINANCIALOPERATOR,MEMO,NOTETYPEID,NOTETIME,KPTIME,PAYOPERATOR,PAYDATETIME,UUID,ACCOUNTMONTH,STARTMONTH,ENDMONTH,financeaudit,cityaudit,entrypensonnel,entrytime,NETWORKDF,INFORMATIZATIONDF,ADMINISTRATIVEDF,MARKETDF,BUILDDF,DDDF,DANJIA,YFFBZW)");
		sql.append(" VALUES('"+i+"','"+formBean.getStationid()+"','"+formBean.getPjje()+"','"+formBean.getHtbianhao()+"','"+ formBean.getPrepayment_ammeterid()+"',"+"NULL"+",'"+dflist.get(i)+"'," );
		sql.append("NULL"+","+"NULL"+","+"NULL"+","+"NULL"+","+"NULL"+","+"NULL"+","+"NULL"+","+"NULL"+","+"NULL"+",'"+memo+"','"+notetypeid+"',to_date('"+notetime+"','yyyy-mm-dd'),to_date('"+kptime+"','yyyy-mm-dd'),"+"NULL"+","+"NULL"+",'"+uuid+"',to_date('"+formBean.getAccountmonth()+"','yyyy-mm'),to_date('" + year_month.get(i) + "','yyyy-mm'),to_date('"
				+year_month.get(i)+"','yyyy-mm'),'1','0','"+formBean.getEntrypensonnel()+"',"+s+",'"+nwdffentan.get(i)+"','"+imdffentan.get(i)+"','"+asdffentan.get(i)+"','"+mkdffentan.get(i)+"','"+bldffentan.get(i)+"','"+dddffentan.get(i)+"','"+danjia+"','1')");
		System.out.println(sql.toString());
		sqlBatch[i] = sql.toString();
		}
		DataBase db = new DataBase();
		try {
			db.connectDb();

			db.setAutoCommit(false);
			db.updateBatch(sqlBatch);
			 msg = "添加合同成功!";
			 db.commit();
			 db.setAutoCommit(true);
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

	return msg;
	}

	/**
	 * 添加电费电量 以下是要添加的信息1111
	 */
	public synchronized String addFeesDegree(ElectricFeesFormBean formBean,String uuid,
			String start, String end, List ammeterdegreeid,String bzw) {
		String msg = "保存账户失败！请重试或与管理员联系！";
		MD5 md = new MD5();
		CTime ctime = new CTime();
		String shi=formBean.getShi();
		String st=" and shi='"+shi+"'";
		 
		AutoAuditBean bean = new AutoAuditBean();
		ArrayList fylist = new ArrayList();
		fylist = bean.getPageData(1, st);//获取自动审核项集合   st 参数没有用 方法里没有使用
		String ItemID = "", ItemName = "", ItemValue = "", ItemDescription = "";
		String autoauditstatus = "1", autoauditdescription = "", autoauditdescription1 = "", autoauditdescription2 = "", autoauditdescription3 = "";
		String auditfee1 = "";
		String auditfee2 = "";
		String auditfee3 = "";
		String floatpay = formBean.getFloatpay();
		String memo = formBean.getMemo();
		String af3_bz = "0";
		String floatpay_bz = "";
		String memo_bz = "";
		
		System.out.println("formBean.getAutoauditstatus()进之前-->"+formBean.getAutoauditstatus());
		if(formBean.getAutoauditstatus().equals("0")){//判断电量表自动审核
			autoauditstatus = "0";//电量表自动审核不通过,电费表的自动审核同值
			System.out.println("formBean.getAutoauditstatus()进来-->"+formBean.getAutoauditstatus());
			 
		}

	double floatt=0.0;//调整电费数目
	if( null!= floatpay && !floatpay.equals("")&& !floatpay.equals(" ")){
		floatt=Double.parseDouble(floatpay);
	}
	else{
		floatpay_bz ="0";
	}
	if(floatt==0.0){
		floatpay_bz ="0";
	}else{
		floatpay_bz ="1";
	}
	if( null!= memo && !memo.equals("")&& !memo.equals(" ")){
		memo_bz = "1";
	}
	else{
		memo_bz = "0";
	}
	if (memo_bz.equals(floatpay_bz)||(floatpay_bz.equals("0")&&memo_bz .equals("1"))) {
		af3_bz = "1";
	}
		if (fylist != null) {
			int fycount = ((Integer) fylist.get(0)).intValue();
			for (int k = fycount; k < fylist.size() - 1; k += fycount) {

				ItemID = (String) fylist.get(k + fylist.indexOf("ITEMID"));
				ItemName = (String) fylist.get(k + fylist.indexOf("ITEMNAME"));
				ItemValue = (String) fylist.get(k + fylist.indexOf("ITEMVALUE"));
				ItemDescription = (String) fylist.get(k	+ fylist.indexOf("ITEMDESCRIPTION"));
				if (ItemName.equals("AuditFee1") && (!ItemValue.equals("0"))
						&& (formBean.getNoteno().equals(""))) {//票据不能为空审核: 票据编号不能为空
					System.out.println(ItemName + "*****" + ItemValue + "****" + formBean.getNoteno());
					autoauditstatus = "0";
					autoauditdescription1 = "票据为空！";
					auditfee1 = "0";
				}



				if (ItemName.equals("AuditFee3") && (!ItemValue.equals("0"))
						&& af3_bz.equals("0")) {//电费调整说明审核:如果该审核启用,有电费调整单没有电费调整说明
					System.out.println(ItemName + "*****" + ItemValue + "****"
							+ formBean.getUnitprice());
					autoauditstatus = "0";
					autoauditdescription3 = "用电费用调整没有说明！";
					auditfee3 = "0";
				}
				

			}
			if (auditfee1.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription1 + ";";
			}

			if (auditfee3.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription3 + ";";
			}
		}
		System.out.println("电费自动审核描述：" + autoauditdescription);
		// 自动审核判断

		int startn = Integer.parseInt(start.substring(0, 4));
		int starty = Integer.parseInt(start.substring(5, 7));
		
		
		    String aa=formBean.getScdff();
		    String bb=formBean.getBgdf();
		    String cc=formBean.getYydf();
		    String dd=formBean.getXxhdf();
		    String ee=formBean.getJstzdf();
		    String ff=formBean.getDddfdf();//代垫电费
		    if("".equals(aa)||aa==null)aa="0";
		    if("".equals(bb)||bb==null)bb="0";
		    if("".equals(cc)||cc==null)cc="0";
		    if("".equals(dd)||dd==null)dd="0";
		    if("".equals(ee)||ee==null)ee="0";
		    if("".equals(ff)||ff==null)ff="0";
	
		//=========2013-10-09新模式===================
		    //电费bean里面保存的是电表的id、 所以这么 写 sql   查询是定标 和省定标耗电量
		    String sqld="select edhdl,qsdbdl from zhandian where id=(select zdid from dianbiao where id= "+formBean.getAmmererid() +")";
		    DataBase db = new DataBase();
		    ResultSet rs=null;
		    Double eddl=0.0;Double qsdbdl=0.0;
			try {
				db.connectDb();
				rs=db.queryAll(sqld);
				try {
					while(rs.next()){
						eddl=rs.getDouble(1);
						qsdbdl=rs.getDouble(2);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (DbException de) {
				try {
					db.rollback();
				} catch (DbException dee) {
					dee.printStackTrace();
				}
				de.printStackTrace();
			} finally {
				try {

					db.close();
				} catch (Exception de) {
					de.printStackTrace();
				}
			}
			System.out.println(eddl+"   "+qsdbdl+"sfs   sfsf           fsff");
		String update = "";
		String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		StringBuffer idStr =new  StringBuffer();
		String sqlx="INSERT INTO electricfees(AMMETERDEGREEID_FK,UNITPRICE,PJJE,FLOATPAY," +
				"ACTUALPAY,NOTETYPEID,NOTENO,PAYOPERATOR," +
				"PAYDATETIME,ACCOUNTMONTH,MEMO,AUTOAUDITSTATUS," +
				"manualauditstatus,AUTOAUDITDESCRIPTION,NOTETIME,DFUUID," +
				"cityaudit,entrypensonnel,entrytime,NETWORKDF," +
				"ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF,DFBZW,DDDF,LIUCHENGHAO,jszq,EDHDL,QSDBDL) VALUES(" +
				"'"+ammeterdegreeid.get(0)+"','"+formBean.getUnitprice()+"','"+formBean.getPjje()+"','"+formBean.getFloatpay()+"'," +
				"'"+formBean.getActualpay()+"','"+formBean.getNotetypeid()+"','"+formBean.getNoteno()+"','"+formBean.getPayoperator()+"'," +
			    "TO_DATE('"+formBean.getPaydatetime()+"','yyyy-mm-dd'),TO_DATE('"+formBean.getAccountmonth()+"','yyyy-mm'),'"+formBean.getMemo()+"','"+autoauditstatus+"'," +
			    "'0','"+autoauditdescription+"',TO_DATE('"+formBean.getNotetime()+"','yyyy-mm-dd'),'"+uuid+"'," +
			    "'"+formBean.getFlag()+"','"+formBean.getEntrypensonnel()+"',"+s+",'"+Double.parseDouble(aa)+"'," +
			    "'"+Double.parseDouble(bb)+"','"+Double.parseDouble(cc)+"','"+Double.parseDouble(dd)+"','"+Double.parseDouble(ee)+"','"+bzw+"','"+Double.parseDouble(ff)+"','',"+Integer.parseInt(formBean.getJszq())+"," +
			    		"" + eddl+","+qsdbdl+")";
		//===============2013-10-09==========
		System.out  .println("电费新增新模式sql："+sqlx);
		
	//====2013-10-09原始模式===	
//		for (int i = 0; i < time; i++) {
//			  nn(ammeterdegreeid.get(i)+",");
//			 
//		//	 update="update ammeterdegrees set feesbz='1',manualauditstatus='1'  where ammeterdegreeid in("+idStr.toString().substring(0,idStr.length()-1)+")";
//
//			StringBuffer sql = new StringBuffer();
//			sql.append("INSERT INTO electricfees(AMMETERDEGREEID_FK,UNITPRICE,PJJE,FLOATPAY,ACTUALPAY,NOTETYPEID,NOTENO,PAYOPERATOR,PAYDATETIME,ACCOUNTMONTH,MEMO,AUTOAUDITSTATUS,manualauditstatus,AUTOAUDITDESCRIPTION,NOTETIME,DFUUID,cityaudit,entrypensonnel,entrytime,NETWORKDF,ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF,DFBZW,DDDF)");
//			sql.append(" VALUES('" + ammeterdegreeid.get(i) + "'");
//			sql.append(",'" + formBean.getUnitprice() + "','"+formBean.getPjje()+"','"
//					+ formBean.getFloatpay() + "','" + dflist.get(i) + "','"
//					+ formBean.getNotetypeid() + "','" + formBean.getNoteno()
//					+ "'");
//			sql.append(",'" + formBean.getPayoperator() + "','"
//					+ formBean.getPaydatetime() + "','"
//					+ formBean.getAccountmonth() + "'" + ",'"
//					+ formBean.getMemo() + "','" + autoauditstatus + "','0','"
//					+ autoauditdescription + "','" + formBean.getNotetime()
//					+"','" + uuid + "','"+formBean.getFlag()+"','"
//					+formBean.getEntrypensonnel()+"',"+s+",'"+scdfffentan.get(i)+"','"+bgdffentan.get(i)+"','"+yydffentan.get(i)+"','"+
//					xxhdffentan.get(i)+"','"+jstzdffentan.get(i)+"','"+bzw+"','"+dddfdffentan.get(i)+"') ");
//			System.out.println(sql.toString());
//			sqlBatch[i] = sql.toString();
//		}
//===原始模式2013-10-09
	
		try {
			db.connectDb();

			// db.updateBatch(sqlBatch);//2013-10-09原始模式
			db.update(sqlx);//2013-10-09新模式
			//db.update(update);
			msg = "添加电费成功!";
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	public synchronized String addFeesDegreehtd(ElectricFeesFormBean formBean,
			String bzw,String start,String end) {
		int startn = Integer.parseInt(start.substring(0, 4));
		int starty = Integer.parseInt(start.substring(5, 7));
		int endn = Integer.parseInt(end.substring(0, 4));
		int endy = Integer.parseInt(end.substring(5, 7));
		int time = (endn - startn) * 12 + endy - starty + 1;
		
//		long uuid = System.currentTimeMillis();
		long uuid1 = System.currentTimeMillis();
		String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
		String uuid = uuid2+Long.toString(uuid1).substring(3);
		
		String df = formBean.getActualpay();
		System.out.println(df+"qianaaa");
		double dfyu = Double.parseDouble(df)%time;
		int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;
	    //网络运营线电费(生产)分到每个月
	    double networkdfys=Double.parseDouble(formBean.getScdff())%time;
	    int networkdf = (int) ((Double.parseDouble(formBean.getScdff())-networkdfys)/time);
	    //信息化支撑线电费分到每个月
	    double informatizationdfys=Double.parseDouble(formBean.getXxhdf())%time;
	    int informatizationdf = (int) ((Double.parseDouble(formBean.getXxhdf())-informatizationdfys)/time);	    
	    //行政综合线电费（办公）分到每个月
	    double administrativedfys=Double.parseDouble(formBean.getBgdf())%time;
	    int administrativedf = (int) ((Double.parseDouble(formBean.getBgdf())-administrativedfys)/time);
	    //市场营销线电费(营业)分到每个月
	    double marketdfys=Double.parseDouble(formBean.getYydf())%time;
	    int marketdf = (int) ((Double.parseDouble(formBean.getYydf())-marketdfys)/time);
	    //建设投资线电费分到每个月
	    double builddfys=Double.parseDouble(formBean.getJstzdl())%time;
	    int builddf = (int) ((Double.parseDouble(formBean.getJstzdl())-builddfys)/time); 
	    //代垫电费线电费分到每个月
	    double dddfys=Double.parseDouble(formBean.getDddf())%time;
	    int dddf = (int) ((Double.parseDouble(formBean.getDddf())-dddfys)/time); 
	   	 List nwdffentan = new ArrayList();
		 List imdffentan = new ArrayList();
		 List asdffentan = new ArrayList();
		 List mkdffentan = new ArrayList();
		 List bldffentan = new ArrayList();
		 List dddffentan=new ArrayList();
		List dflist = new ArrayList();
		 List year_month = new ArrayList();
		String[] sqlBatch = new String[time];
		for (int i = 0; i < time; i++) {
			String yue = String.valueOf(starty);
	     	if(yue.length()==1)yue = "0"+yue;
	     	String year_month_tmp = startn+"-"+yue;
	     	starty ++;
	     	if(starty>12){
	     		starty = 1;
	     		startn ++;
	     	}
	     	year_month.add(year_month_tmp);
			if (i == time - 1){
				dflist.add(dfPermonth + dfyu);
				nwdffentan.add(networkdf+networkdfys);
				imdffentan.add(informatizationdf+informatizationdfys);
				asdffentan.add(administrativedf+administrativedfys);
				mkdffentan.add(marketdf+marketdfys);
				bldffentan.add(builddf+builddfys);
				dddffentan.add(dddf+dddfys);
			}else{
				dflist.add(dfPermonth);
				nwdffentan.add(networkdf);
				imdffentan.add(informatizationdf);
				asdffentan.add(administrativedf);
				mkdffentan.add(marketdf);
				bldffentan.add(builddf);
				dddffentan.add(dddf);
			}
		}
		
		
		
		// birthday = birthday.length()>0?birthday:null;
		String msg = "保存账户失败！请重试或与管理员联系！";
		MD5 md = new MD5();
		String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		CTime ctime = new CTime();
		ArrayList fylist = new ArrayList();
		for(int i=0;i<time;i++){
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO PREPAYMENT(ID,PREPAYMENT_AMMETERID,STATIONID,PJJE,DIANLIANG,STARTMONTH,ENDMONTH,ACCOUNTMONTH,NOTETIME,KPTIME,NOTETYPEID,NOTENO,FINANCEAUDIT,CITYAUDIT,ENTRYPENSONNEL,ENTRYTIME,UUID,MONEY,NETWORKDF,ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF,DDDF,YFFBZW,MEMO)");
			sql.append(" VALUES ('"+i+"','"+formBean.getAmmererid()+"','"+formBean.getStationid()+"','"+formBean.getPjje()+"','"+formBean.getDianliang()+ "',TO_DATE('"+ year_month.get(i) + "','yyyy-mm'),TO_DATE('" + year_month.get(i) + "','yyyy-mm'),TO_DATE('"
					+ formBean.getAccountmonth() + "','yyyy-mm'),TO_DATE('" + formBean.getNotetime()+ "','yyyy-mm-dd')");
			sql.append(",TO_DATE('" + formBean.getKptime() + "','yyyy-mm-dd'),'"+ formBean.getNotetypeid() + "','"+ formBean.getNoteno() +"','1','0','"+formBean.getEntrypensonnel()+"',"+s+",'"+uuid+"','"+dflist.get(i)+"','"+nwdffentan.get(i)+"','"+asdffentan.get(i)+"','"+mkdffentan.get(i)+"','"+
					imdffentan.get(i)+"','"+bldffentan.get(i)+"','"+dddffentan.get(i)+"','"+bzw+"','"+formBean.getMemo() + "')");
			sqlBatch[i] = sql.toString();
			System.out.println(sql.toString());
		
		}
		DataBase db = new DataBase();
		try {
			
			db.updateBatch(sqlBatch);
			msg = "添加合同单成功!";
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	//修改站点单价
	public synchronized String addFeesDegreedj(ZhanDianForm formBean,
			String bzw,String start,String end) {
		
		String msg = "保存账户失败！请重试或与管理员联系！";
		MD5 md = new MD5();
		CTime ctime = new CTime();
		ArrayList fylist = new ArrayList();
			String sql="";
			sql="update zhandian z set z.dianfei='"+formBean.getDianfei()+"',z.zdbzw='"+bzw+"' where z.zdcode='"+formBean.getZdcode()+"'";
			System.out.println(sql.toString());
		DataBase db = new DataBase();
		ResultSet rs=null;
		
		try {
			db.setAutoCommit(false);	
			rs=db.queryAll(sql);
			db.commit();
			db.setAutoCommit(true);
			msg = "修改站点单价成功!";
			db.close();
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {
                if(rs!=null){
                	rs.close();
                }
                if(db!=null)
				{
                	db.close();
				}
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	// 获取电量ID单通过电量自动生成主键获取)
	public List getAmUuid(String amuuid) {
		// ElectricFeesFormBean bean = new ElectricFeesFormBean();
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select ad.ammeterdegreeid from ammeterdegrees ad where ad.uuid='"
						+ amuuid + "'");
		// String ammeterdegreeid = "";
		List ammeter = new ArrayList();
		DataBase db = new DataBase();
		try {
			db.connectDb();
			ResultSet rs = null;
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				// ammeterdegreeid = rs.getString("ammeterdegreeid") != null ?
				// rs.getString("ammeterdegreeid") : "";
				ammeter.add(rs.getString("ammeterdegreeid"));
			}
			rs.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return ammeter;
	}
	public List getAmmeter(String amuuid) {
		// ElectricFeesFormBean bean = new ElectricFeesFormBean();
		 
		String sql=" select ad.ammeterdegreeid from ammeterdegrees ad where ad.uuid=(select a.uuid from ammeterdegrees a where a.ammeterdegreeid="+amuuid+")";
		List ammeter = new ArrayList();
		DataBase db = new DataBase();
		try {
			System.out.println("getAmUuid:" + sql);
			db.connectDb();
			ResultSet rs = null;
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				ammeter.add(rs.getString("ammeterdegreeid"));
			}
			rs.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return ammeter;
	}
	public String getMonth(String degreeid) {
		// ElectricFeesFormBean bean = new ElectricFeesFormBean();
		 
		String sql=" select ad.startmonth,ad.endmonth from ammeterdegrees ad where ad.ammeterdegreeid="+degreeid+"";
	
		DataBase db = new DataBase();
		String start="",end="",str="";
		try {
			System.out.println("getAmUuid:" + sql);
			db.connectDb();
			ResultSet rs = null;
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				start=rs.getString(1);
				end=rs.getString(2);
			}
			str=start+","+end;
			rs.close();
		}
   
		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return str;
	}
	/**
	 * 删除电费
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String delElectricFees1(String degreeid) {
		String msg = "删除电费信息失败！";
		DataBase db = new DataBase();

		try {
			db.connectDb();
			String update="";
  			 update="update ammeterdegrees set feesbz='0' where uuid=(select d.uuid from ammeterdegrees d,electricfees e where d.ammeterdegreeid=e.AMMETERDEGREEID_FK and e.ELECTRICFEE_ID='"+degreeid+"')";

//            String delete1="delete from ammeterdegrees where uuid=" +
//          		"(select b.uuid from ammeterdegrees b where b.ammeterdegreeid=(select e.ammeterdegreeid_fk from electricfees e where e.electricfee_id='"+degreeid+"'))";
			String delete="delete from electricfees where dfuuid=(select dfuuid from electricfees where ELECTRICFEE_ID='"+degreeid+"')";
			
//            String sql1 = "DELETE electricfees WHERE ELECTRICFEE_ID="
//					+ degreeid; // 删除电费信息表

			System.out.println(delete.toString());
			//System.out.println(delete1.toString());
			// String delNames = getName(accountId, db);
			msg = "删除电费信息失败！";
			//db.update(delete1);
			db.update(delete);
			db.update(update);
			msg = "删除电费信息成功！";
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	/**
	 * 删除电费单
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String delElectricFees(String degreeid) {
		String msg = "删除电费单信息失败！";
		DataBase db = new DataBase();

		try {
			db.connectDb();
            String delete1="delete from ammeterdegrees where uuid=" +
          		"(select b.uuid from ammeterdegrees b where b.ammeterdegreeid=(select e.ammeterdegreeid_fk from electricfees e where e.electricfee_id='"+degreeid+"'))";
			String delete="delete from electricfees where dfuuid=(select dfuuid from electricfees where ELECTRICFEE_ID='"+degreeid+"')";
			
//            String sql1 = "DELETE electricfees WHERE ELECTRICFEE_ID="
//					+ degreeid; // 删除电费信息表

			System.out.println(delete.toString());
			System.out.println(delete1.toString());
			// String delNames = getName(accountId, db);
			msg = "删除电费单信息失败！";
			db.update(delete1);
			db.update(delete);
			
			msg = "删除电费单信息成功！";
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	/**
	 * 电费单批量删除
	 * 
	
	 */
	public synchronized String deletesElectricFees(String ammeterid_fk) {
		String msg = "删除电费单信息失败！";
		DataBase db = new DataBase();
		String uuid="",dfuuid="";

		try {
			db.connectDb();
			String que="select am.uuid,e.dfuuid from ammeterdegrees am,electricfees e where am.ammeterdegreeid=e.ammeterdegreeid_fk and am.ammeterdegreeid in("+ammeterid_fk+")";
			ResultSet rs = null;
			System.out.println("查询uuid"+que);
			rs = db.queryAll(que.toString());
			while (rs.next()) {
				String uuid1=rs.getString("uuid");
				uuid=uuid1+","+uuid;
				String dfuuid1=rs.getString("dfuuid");
				dfuuid=dfuuid1+","+dfuuid;
			}
			rs.close();//rsg
			String uu=uuid.substring(0,uuid.length()-1);
			System.out.println("uuid"+uuid+"uu"+uu);
			String df=dfuuid.substring(0,dfuuid.length()-1);
			System.out.println("dfuuid"+dfuuid+"df"+df);
			String delete1="delete from ammeterdegrees where uuid in("+uu+")";
			String delete="delete from electricfees where dfuuid in("+df+")";
			System.out.println(delete.toString());
			System.out.println(delete1.toString()); 		    
			msg = "批量删除电费单信息失败！";
			db.update(delete1);
			db.update(delete);
			
			msg = "批量删除电费单信息成功！";
		} catch (Exception de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	/**
	 * 电费单修改信息
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String modifyElectricFees(ElectricFeesFormBean formBean,List ammeterdegreeid,String start,String end,String id) {
		String msg = "修改电费信息失败！";
		DataBase db = new DataBase();
		// 自动审核判断
		String sysprice = formBean.getJizhanPrice(ammeterdegreeid.get(0).toString()).getSysprice();
		System.out.println("系统单价:" + sysprice);
		AutoAuditBean bean = new AutoAuditBean();
		ArrayList fylist = new ArrayList();
		String shi=formBean.getShi();
		String st=" and shi='"+shi+"'";
		fylist = bean.getPageData(1, st);
		String ItemID = "", ItemName = "", ItemValue = "", ItemDescription = "";
		String autoauditstatus = "1", autoauditdescription = "", autoauditdescription1 = "", autoauditdescription2 = "", autoauditdescription3 = "";
		String auditfee1 = "";
		String auditfee2 = "";
		String auditfee3 = "";
		String floatpay = formBean.getFloatpay();
		String memo = formBean.getMemo();
		String af3_bz = "0";
		String floatpay_bz = "";
		String memo_bz = "";
		double floatt=0.0;
		//判断电量表自动审核
		if(formBean.getAutoauditstatus().equals("0")){
			autoauditstatus = "0";
		}
		if( null!= floatpay && !floatpay.equals("")&& !floatpay.equals(" ")){
			floatt=Double.parseDouble(floatpay);
		}
		else{
			floatpay_bz ="0";
		}
		if(floatt==0.0){
			floatpay_bz ="0";
		}else{
			floatpay_bz ="1";
		}
		if( null!= memo && !memo.equals("")&& !memo.equals(" ")){
			memo_bz = "1";
		}
		else{
			memo_bz = "0";
		}
		if (memo_bz.equals(floatpay_bz)||(floatpay_bz.equals("0")&&memo_bz .equals("1"))) {
			af3_bz = "1";
		}
		
		/*if (floatpay == null || floatpay == "" || floatpay.equals("0")) {
			floatpay_bz = "0";
		} else {
			floatpay_bz = "1";
		}
		if (memo == null || memo == "") {
			memo_bz = "0";
		} else {
			memo_bz = "1";
		}
		if (memo_bz.equals(floatpay_bz)) {
			af3_bz = "1";
		}*/
		if (fylist != null) {
			int fycount = ((Integer) fylist.get(0)).intValue();
			for (int k = fycount; k < fylist.size() - 1; k += fycount) {

				ItemID = (String) fylist.get(k + fylist.indexOf("ITEMID"));
				ItemName = (String) fylist.get(k + fylist.indexOf("ITEMNAME"));
				ItemValue = (String) fylist
						.get(k + fylist.indexOf("ITEMVALUE"));
				ItemDescription = (String) fylist.get(k
						+ fylist.indexOf("ITEMDESCRIPTION"));
				if (ItemName.equals("AuditFee1") && (!ItemValue.equals("0"))
						&& (formBean.getNoteno().equals(""))) {
					System.out.println(ItemName + "*****" + ItemValue + "****"
							+ formBean.getNoteno());
					autoauditstatus = "0";
					autoauditdescription1 = "票据为空！";
					auditfee1 = "0";
				}

				if (ItemName.equals("AuditFee2") && (!ItemValue.equals("0"))
						&& (!formBean.getUnitprice().equals(sysprice))) {
					System.out.println(ItemName + "*****" + ItemValue + "****"
							+ formBean.getUnitprice());
					autoauditstatus = "0";
					autoauditdescription2 = "本次单价与系统单价不符！";
					auditfee2 = "0";
				}

				if (ItemName.equals("AuditFee3") && (!ItemValue.equals("0"))
						&& af3_bz.equals("0")) {
					System.out.println(ItemName + "*****" + ItemValue + "****"
							+ formBean.getUnitprice());
					autoauditstatus = "0";
					autoauditdescription3 = "用电费用调整没有说明！";
					auditfee3 = "0";
				}

			}
			if (auditfee1.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription1 + ";";
			}
			if (auditfee2.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription2 + ";";
			}
			if (auditfee3.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription3 + ";";
			}
		}
		System.out.println("电费自动审核描述：" + autoauditdescription);
		// 自动审核判断
		
		formBean.setAutoauditstatus(autoauditstatus);
		formBean.setAutoauditdescription(autoauditdescription);
	/*	int startn = Integer.parseInt(start.substring(0, 4));
		int starty = Integer.parseInt(start.substring(5, 7));
		int endn = Integer.parseInt(end.substring(0, 4));
		int endy = Integer.parseInt(end.substring(5, 7));
		int time = (endn - startn) * 12 + endy - starty + 1;
		//long uuid = System.currentTimeMillis();
		String df = formBean.getActualpay();
		double dfyu = Double.parseDouble(df)%time;
		int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;
		
		//生产电费分到每个月
	    double scdffyushu=Double.parseDouble(formBean.getScdff())%time;
	    int scdff = (int) ((Double.parseDouble(formBean.getScdff())-scdffyushu)/time);
	    //办公电费分到每个月
	    double bgdfyushu=Double.parseDouble(formBean.getBgdf())%time;
	    int bgdf = (int) ((Double.parseDouble(formBean.getBgdf())-bgdfyushu)/time);
	    //营业电费分到每个月
	    double yydfyushu=Double.parseDouble(formBean.getYydf())%time;
	    int yydf = (int) ((Double.parseDouble(formBean.getYydf())-yydfyushu)/time);
	    //信息化电费分到每个月
	    double xxhdfyushu=Double.parseDouble(formBean.getXxhdf())%time;
	    int xxhdf = (int) ((Double.parseDouble(formBean.getXxhdf())-xxhdfyushu)/time);
	    //建设投资电费分到每个月
	    double jstzdfyushu=Double.parseDouble(formBean.getJstzdf())%time;
	    int jstzdf = (int) ((Double.parseDouble(formBean.getJstzdf())-jstzdfyushu)/time);
	    //代垫电费分到每个月
	    double dddfdfyushu=Double.parseDouble(formBean.getDddfdf())%time;
	    int dddfdf = (int) ((Double.parseDouble(formBean.getDddfdf())-dddfdfyushu)/time);
	    
		List dflist = new ArrayList();
		 List scdfffentan = new ArrayList();
    	 List bgdffentan = new ArrayList();
    	 List yydffentan = new ArrayList();
    	 List xxhdffentan = new ArrayList();
    	 List jstzdffentan = new ArrayList();
    	 List dddfdffentan = new ArrayList();//代垫电费

		String[] sqlBatch = new String[time];
		for (int i = 0; i < time; i++) {
			if (i == time - 1){
				dflist.add(dfPermonth + dfyu);
				scdfffentan.add(scdff+scdffyushu);
         		bgdffentan.add(bgdf+bgdfyushu);
         		yydffentan.add(yydf+yydfyushu);
         		xxhdffentan.add(xxhdf+xxhdfyushu);
         		jstzdffentan.add(jstzdf+jstzdfyushu);
         		dddfdffentan.add(dddfdf+dddfdfyushu);
			}else{
				dflist.add(dfPermonth);
				scdfffentan.add(scdff);
         		bgdffentan.add(bgdf);
         		yydffentan.add(yydf);
         		xxhdffentan.add(xxhdf);
         		jstzdffentan.add(jstzdf);
         		dddfdffentan.add(dddfdf);
			}
		}
		
		*/
		
		String s="to_date(to_char(sysdate,'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		System.out.println("---formBean.getAmmeterdegreeid():"+id);
		try {
			db.connectDb();
			String update="";
			String delete="delete from electricfees where dfuuid=(select dfuuid from electricfees where ammeterdegreeid_fk='"+id+"')";
           String sqlx="INSERT INTO electricfees(AMMETERDEGREEID_FK,UNITPRICE,PJJE,FLOATPAY," +
           		"ACTUALPAY,NOTETYPEID,NOTENO,PAYOPERATOR," +
           		"PAYDATETIME,ACCOUNTMONTH,MEMO,AUTOAUDITSTATUS," +
           		"manualauditstatus,AUTOAUDITDESCRIPTION,NOTETIME,DFUUID," +
           		"cityaudit,entrypensonnel,entrytime,NETWORKDF," +
           		"ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF," +
           		"dfbzw,DDDF,LIUCHENGHAO,jszq) VALUES('"+ammeterdegreeid.get(0)+"','"+formBean.getUnitprice()+"','"+formBean.getPjje()+"','"+formBean.getFloatpay()+"'," +
           		"'"+formBean.getActualpay()+"','"+formBean.getNotetypeid()+"','"+formBean.getNoteno()+"','"+formBean.getPayoperator()+"'," +
           		"TO_DATE('"+formBean.getPaydatetime()+"','yyyy-mm-dd'),TO_DATE('"+formBean.getAccountmonth()+"','yyyy-mm'),'"+formBean.getMemo()+"','"+autoauditstatus+"'," +
           	    "'0','"+autoauditdescription+"',TO_DATE('"+formBean.getNotetime()+"','yyyy-mm-dd'),'"+formBean.getDfuuid()+"'," +
           	    "'"+formBean.getFlag()+"','"+formBean.getEntrypensonnel()+"',"+s+",'"+Double.parseDouble(formBean.getScdff())+"'," +
           	    "'"+Double.parseDouble(formBean.getBgdf())+"','"+Double.parseDouble(formBean.getYydf())+"','"+Double.parseDouble(formBean.getXxhdf())+"','"+Double.parseDouble(formBean.getJstzdf())+"'," +
           	    "'1','"+Double.parseDouble(formBean.getDddfdf())+"','',"+Integer.parseInt(formBean.getJszq())+")";
       	System.out.println("电费单修改执行删除电费sql："+delete.toString());
			System.out.println("电费单修改新模式电费sql:"+sqlx);
			
			
//			for (int i = 0; i < time; i++) {
//            	StringBuffer sql = new StringBuffer();
//   			// update="update ammeterdegrees set feesbz='1' where uuid=(select d.uuid from ammeterdegrees d where d.ammeterdegreeid='"+ammeterdegreeid.get(i)+"')";
//
//    			sql.append("INSERT INTO electricfees(AMMETERDEGREEID_FK,UNITPRICE,PJJE,FLOATPAY,ACTUALPAY,NOTETYPEID,NOTENO,PAYOPERATOR,PAYDATETIME,ACCOUNTMONTH,MEMO,AUTOAUDITSTATUS,manualauditstatus,AUTOAUDITDESCRIPTION,NOTETIME,DFUUID,cityaudit,entrypensonnel,entrytime,NETWORKDF,ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF,dfbzw,DDDF)");
//    			sql.append(" VALUES('" + ammeterdegreeid.get(i) + "'");
//    			sql.append(",'" + formBean.getUnitprice() + "','"+formBean.getPjje()+"','"
//    					+ formBean.getFloatpay() + "','" + dflist.get(i) + "','"
//    					+ formBean.getNotetypeid() + "','" + formBean.getNoteno()
//    					+ "'");
//    			sql.append(",'" + formBean.getPayoperator() + "','"
//    					+ formBean.getPaydatetime() + "','"
//    					+ formBean.getAccountmonth() + "'" + ",'"
//    					+ formBean.getMemo() + "','" + autoauditstatus + "','0','"
//    					+ autoauditdescription + "','" + formBean.getNotetime()
//    					+ "','" + formBean.getDfuuid() + "','"+formBean.getFlag()+"','"+formBean.getEntrypensonnel()+"',"+s
//    					+",'"+scdfffentan.get(i)+"','"+bgdffentan.get(i)+"','"+yydffentan.get(i)+"','"+
//    					xxhdffentan.get(i)+"','"+jstzdffentan.get(i)+"','1','"+dddfdffentan.get(i)+"')");
//    			System.out.println(sql.toString());
//    			sqlBatch[i] = sql.toString();
//
//            }
		
			msg = "修改电费信息失败！";

			db.setAutoCommit(false);
			db.update(delete);
			//db.update(update);
		//	db.updateBatch(sqlBatch);//原始模式
			db.update(sqlx);//2013-10-09新模式
			msg = "修改电费信息成功！";
			db.commit();
			db.setAutoCommit(true);
			
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	/**
	 * 电费修改信息
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized String modifyElectricFees1(ElectricFeesFormBean formBean,List ammeterdegreeid,String id,String start,String end) {
		String msg = "修改电费信息失败！";
		DataBase db = new DataBase();
		// 自动审核判断
		String sysprice = formBean.getJizhanPrice(
				formBean.getAmmeterdegreeidFk()).getSysprice();
		System.out.println("系统单价:" + sysprice);
		AutoAuditBean bean = new AutoAuditBean();
		ArrayList fylist = new ArrayList();
		fylist = bean.getPageData(1, "");
		String ItemID = "", ItemName = "", ItemValue = "", ItemDescription = "";
		String autoauditstatus = "1", autoauditdescription = "", autoauditdescription1 = "", autoauditdescription2 = "", autoauditdescription3 = "";
		String auditfee1 = "";
		String auditfee2 = "";
		String auditfee3 = "";
		String floatpay = formBean.getFloatpay();
		String memo = formBean.getMemo();
		String af3_bz = "0";
		String floatpay_bz = "";
		String memo_bz = "";
		if (floatpay == null || floatpay == "" || floatpay.equals("0")) {
			floatpay_bz = "0";
		} else {
			floatpay_bz = "1";
		}
		if (memo == null || memo == "") {
			memo_bz = "0";
		} else {
			memo_bz = "1";
		}
		if (memo_bz.equals(floatpay_bz)) {
			af3_bz = "1";
		}
		if (fylist != null) {
			int fycount = ((Integer) fylist.get(0)).intValue();
			for (int k = fycount; k < fylist.size() - 1; k += fycount) {

				ItemID = (String) fylist.get(k + fylist.indexOf("ITEMID"));
				ItemName = (String) fylist.get(k + fylist.indexOf("ITEMNAME"));
				ItemValue = (String) fylist
						.get(k + fylist.indexOf("ITEMVALUE"));
				ItemDescription = (String) fylist.get(k
						+ fylist.indexOf("ITEMDESCRIPTION"));
				if (ItemName.equals("AuditFee1") && (!ItemValue.equals("0"))
						&& (formBean.getNoteno().equals(""))) {
					System.out.println(ItemName + "*****" + ItemValue + "****"
							+ formBean.getNoteno());
					autoauditstatus = "0";
					autoauditdescription1 = "票据为空！";
					auditfee1 = "0";
				}

				if (ItemName.equals("AuditFee2") && (!ItemValue.equals("0"))
						&& (!formBean.getUnitprice().equals(sysprice))) {
					System.out.println(ItemName + "*****" + ItemValue + "****"
							+ formBean.getUnitprice());
					autoauditstatus = "0";
					autoauditdescription2 = "本次单价与系统单价不符！";
					auditfee2 = "0";
				}

				if (ItemName.equals("AuditFee3") && (!ItemValue.equals("0"))
						&& af3_bz.equals("0")) {
					System.out.println(ItemName + "*****" + ItemValue + "****"
							+ formBean.getUnitprice());
					autoauditstatus = "0";
					autoauditdescription3 = "用电费用调整没有说明！";
					auditfee3 = "0";
				}

			}
			if (auditfee1.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription1 + ";";
			}
			if (auditfee2.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription2 + ";";
			}
			if (auditfee3.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription3 + ";";
			}
		}
		System.out.println("电费自动审核描述：" + autoauditdescription);
		// 自动审核判断
		
		formBean.setAutoauditstatus(autoauditstatus);
		formBean.setAutoauditdescription(autoauditdescription);
		int startn = Integer.parseInt(start.substring(0, 4));
		int starty = Integer.parseInt(start.substring(5, 7));
		int endn = Integer.parseInt(end.substring(0, 4));
		int endy = Integer.parseInt(end.substring(5, 7));
		int time = (endn - startn) * 12 + endy - starty + 1;
		long uuid = System.currentTimeMillis();
		String df = formBean.getActualpay();
		double dfyu = Double.parseDouble(df)%time;
		int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;
		List dflist = new ArrayList();

		String[] sqlBatch = new String[time];
		for (int i = 0; i < time; i++) {
			if (i == time - 1)
				dflist.add(dfPermonth + dfyu);
			else
				dflist.add(dfPermonth);
		}
		//System.out.println("shijian:" + time + "-" + start + "-" + end);
		//System.out.println("---formBean.getAmmeterdegreeid():"+id);
		try {
			db.connectDb();
			String update="";
			String delete="delete from electricfees where dfuuid=(select dfuuid from electricfees where electricfee_id='"+id+"')";
            for (int i = 0; i < time; i++) {
            	StringBuffer sql = new StringBuffer();
      			 update="update ammeterdegrees set feesbz='1' where uuid=(select d.uuid from ammeterdegrees d where d.ammeterdegreeid='"+ammeterdegreeid.get(i)+"')";

    			sql.append("INSERT INTO electricfees(AMMETERDEGREEID_FK,UNITPRICE,FLOATPAY,ACTUALPAY,NOTETYPEID,NOTENO,PAYOPERATOR,PAYDATETIME,ACCOUNTMONTH,MEMO,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,manualauditstatus,KPTIME,NOTETIME,DFUUID,cityaudit)");
    			sql.append(" VALUES('" + ammeterdegreeid.get(i) + "'");
    			sql.append(",'" + formBean.getUnitprice() + "','"
    					+ formBean.getFloatpay() + "','" + dflist.get(i) + "','"
    					+ formBean.getNotetypeid() + "','" + formBean.getNoteno()
    					+ "'");
    			sql.append(",'" + formBean.getPayoperator() + "','"
    					+ formBean.getPaydatetime() + "','"
    					+ formBean.getAccountmonth() + "'" + ",'"
    					+ formBean.getMemo() + "','" + autoauditstatus + "','"
    					+ autoauditdescription + "','0','"+formBean.getKptime()+"','" + formBean.getNotetime()
    					+ "','" + uuid + "','" + formBean.getFlag() + "')");
    			System.out.println(sql.toString());
    			sqlBatch[i] = sql.toString();
            }
			System.out.println(delete.toString());
			msg = "修改电费信息失败！";
			db.update(delete);
			db.update(update);
			db.updateBatch(sqlBatch);
			msg = "修改电费信息成功！";
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}

	// 获取电表初始读数
	public synchronized String getStartDegree(String amid) {
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select t.csds from dianbiao t where t.dbid='"
						+ amid + "'");
		String startdegree = "";
		DataBase db = new DataBase();
		try {
			System.out.println("getStartDegree:" + sql);
			db.connectDb();
			ResultSet rs = null;
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				startdegree = rs.getString("csds") != null ? rs
						.getString("csds") : "";

			}
			rs.close();
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return startdegree;
	}
   /**
    * 合同单里面浏览按钮调用的方法
    * 
    * */
	public synchronized ArrayList getPageDataAmmeter11(int start,
			String whereStr, String loginId) {
		System.out.println("AmmeterDegreeBean-getPageDataAmmeter:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select  db.dbid,jz.jzname,rndiqu(jz.xian) as xian,(select name from indexs where code = db.dfzflx and type = 'dfzflx') as dfzflx,"
					+ "rndiqu(jz.shi) as shi,"
					+ "rndiqu(jz.sheng) as sheng,"
					+"(select name from indexs where code = db.sszy and type = 'SSZY') professionaltypeid,(select name from indexs where code = db.dbyt and type = 'DBYT') ammeteruse,"
					+"db.csds,to_char(db.cssytime,'yyyy-mm-dd') cssytime,"
					+ "db.beilv,db.dbxh from zhandian jz,dianbiao db "
					+ "where db.zdid=jz.id and jz.shsign='1' and jz.PROVAUDITSTATUS='1'  and jz.qyzt='1' and jz.xuni = '0' and jz.caiji = '0' and db.dbyt  ='dbyt01' and db.dbqyzt='1' and db.dfzflx  ='dfzflx02'"
					+ whereStr
					+ " "
					+ "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ")  order by jz.jzname";

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("合同单电表信息查询："+sql.toString());
		ResultSet rs = null;
		try {
			db.connectDb();
			StringBuffer strall = new StringBuffer();
			strall
					.append("select count(*) from zhandian jz,dianbiao db "
					+ " where db.zdid=jz.id and jz.shsign='1' and jz.PROVAUDITSTATUS='1'  and jz.qyzt='1' and jz.xuni = '0' and jz.caiji = '0' and db.dbqyzt='1' and db.dbyt  ='dbyt01' and db.dfzflx  ='dfzflx02'"
					+ whereStr
					+ " "
					+ "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ")  order by jz.jzname");
			System.out.println("合同单电表信息查询分页：" + strall.toString());
			rs = db.queryAll(strall.toString());
			if (rs.next()) {
				this.setAllPage((rs.getInt(1) + 14) / 15);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
			Query query = new Query();
			list = query.query(rs);
			db.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
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
	
	
	/**
	 * 预付费管理  添加时   电表的浏览按钮 调用的方法
	 * 
	 * */
	/**
	 * @param start
	 * @param whereStr
	 * @param loginId
	 * @return
	 */
	public synchronized ArrayList getPageDataAmmeter12(int start,
			String whereStr, String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			
	       
			sql = "select  db.dbid,jz.jzname," 
				+"(select name from indexs where code = db.dfzflx and type = 'dfzflx') dfzflx,"
				+"(case when jz.xian is not null then rndiqu(jz.xian) else '' end)"+
					" ||(case when jz.xiaoqu is not null then rndiqu(jz.xiaoqu) else '' end) as szdq ," +
					" (select name from indexs where code = db.sszy and type = 'SSZY') professionaltypeid,(select name from indexs where code = db.dbyt and type = 'DBYT') ammeteruse,"
					+
					"db.csds,to_char(db.cssytime,'yyyy-mm-dd') cssytime,"
					+ "db.beilv,db.dbxh from zhandian jz,dianbiao db "
					+ " where jz.qyzt='1' and jz.shsign='1' and jz.PROVAUDITSTATUS='1'  and jz.xuni = '0' and jz.caiji = '0' and db.dbyt  ='dbyt01' and db.dbqyzt='1' and db.zdid=jz.id and (db.dfzflx='dfzflx03' or db.dfzflx='dfzflx04')"
					+ whereStr
					+ " "
					+ "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ")  order by jz.jzname";

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("预付费管理电表信息查询："+sql.toString());
		ResultSet rs = null;
		try {
			db.connectDb();
			StringBuffer strall = new StringBuffer();
			strall
					.append("select count(*) from zhandian jz,dianbiao db "
					+ " where jz.qyzt='1' and jz.shsign='1' and jz.PROVAUDITSTATUS='1'  and jz.xuni = '0' and jz.caiji = '0' and db.dbyt ='dbyt01' and db.dbqyzt='1'  and db.zdid=jz.id and (db.dfzflx='dfzflx03' or db.dfzflx='dfzflx04')"
					+ whereStr
					+ " "
					+ "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ")  order by jz.jzname");
			rs = db.queryAll(strall.toString());
			System.out.println("预付费管理电表信息查询分页：" + strall.toString());
			if (rs.next()) {
				this.setAllPage((rs.getInt(1) + 14) / 15);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
			Query query = new Query();
			list = query.query(rs);
			db.close();
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
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

	/**
	 * 电费单添加时   电表的浏览按  调用的方法
	 * 
	 * 
	 * */
	public synchronized ArrayList getPageDataAmmeter13(int start,
			String whereStr, String loginId) {
		System.out.println("AmmeterDegreeBean-getPageDataAmmeter:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select  db.dbid,db.dbname,jz.jzname,dag.agname as xian,"
				    +"(select name from indexs where code = db.dfzflx and type = 'dfzflx') dfzflx,"
					+ "(select agname from d_area_grade where agcode = substr(dag.agcode, 0, 5)) as shi,"
					+ "(select agname from d_area_grade where agcode = substr(dag.agcode, 0, 3)) as sheng,"
					+

					"(select name from indexs where code = db.sszy and type = 'SSZY') as professionaltypeid,(select name from indexs where code = db.dbyt and type = 'DBYT') as ammeteruse,"
					+ " db.csds,db.cssytime, db.beilv, db.dbxh from zhandian jz,dianbiao db, "
					+ "d_area_grade dag where jz.xian = dag.agcode  and jz.shsign='1' and db.dbyt  ='dbyt01' and db.zdid=jz.id and (db.dfzflx='dfzflx01' or db.dfzflx='dfzflx03') "
					+ whereStr
					+ " "
					+ "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ")  order by jz.jzname";

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
System.out.println("--"+sql.toString());
		ResultSet rs = null;
		try {
			db.connectDb();
			StringBuffer strall = new StringBuffer();
			strall
					.append("select count(jz.id) from zhandian jz,dianbiao db, "
						+ "d_area_grade dag where jz.xian = dag.agcode  and jz.shsign='1' and db.dbyt  ='dbyt01' and db.zdid=jz.id and (db.dfzflx='dfzflx01' or db.dfzflx='dfzflx03') "
						+ whereStr
						+ " "
						+ "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
						+ loginId + "'))" + fzzdstr + ")  order by jz.jzname");
			rs = db.queryAll(strall.toString());
			System.out.println("getALL" + strall.toString());// ///////////
			if (rs.next()) {
				this.setAllPage((rs.getInt(1) + 14) / 15);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
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

	/**
	 * 显示电表信息列表
	 * 
	 * @param accountId
	 *            String[]
	 * @return String
	 */
	public synchronized ArrayList getPageDataAmmeter(int start,
			String whereStr, String loginId) {
		System.out.println("AmmeterDegreeBean-getPageDataAmmeter:" + whereStr);
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select  am.ammeterid,jz.jzname,dag.agname as xian,"
					+ "(select agname from d_area_grade where agcode = substr(dag.agcode, 0, 5)) as shi,"
					+ "(select agname from d_area_grade where agcode = substr(dag.agcode, 0, 3)) as sheng,"
					+

					"(select name from indexs where code = am.professionaltypeid and type = 'SSZY') as professionaltypeid,(select name from indexs where code = am.ammeteruse and type = 'DBYT') as ammeteruse,"
					+

					"am.initialdegree,am.initialdate,"
					+ "am.multiplyingpower,am.ammetertype from ammeters am,zhandian jz, "
					+ "d_area_grade dag where jz.xian = dag.agcode and am.stationid=jz.id and am.ammeteruse  ='dbyt01' "
					+ whereStr
					+ " "
					+ "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ")  order by jz.jzname";

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
System.out.println("--"+sql.toString());
		ResultSet rs = null;
		try {
			db.connectDb();
			StringBuffer strall = new StringBuffer();
			strall
					.append("select count(*) from ammeters am,zhandian jz, "
							+ "d_area_grade dag where jz.xian = dag.agcode and am.stationid=jz.id and am.ammeteruse = 'dbyt01' "
							+ whereStr
							+ " "
							+ "and ((jz.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
							+ loginId + "'))" + fzzdstr + ") ");
			rs = db.queryAll(strall.toString());
			System.out.println("getALL" + strall.toString());// ///////////
			if (rs.next()) {
				this.setAllPage((rs.getInt(1) + 14) / 15);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, sql.toString()));
			Query query = new Query();
			list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException de) {
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

	// //////////////////////////////////////////////////////////////////
	//电费暂估查询
	public synchronized ArrayList getZanguPageData(int start, String whereStr,
			String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select B.*,to_char(lastdatetime,'yyyy-mm-dd') lastdatetimea,to_char(thisdatetime,'yyyy-mm-dd') thisdatetimea from (select zd.jzname,d.dbname,zd.zdcode,ad.lastdatetime,ad.thisdatetime,ef.ACTUALPAY,ad.ammeterid_fk,"
					+"(case when zd.xian is not null then (select distinct agname from d_area_grade where agcode=zd.xian) else '' end)"
					+"||(case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=zd.xiaoqu) else '' end) as szdq,"
					+"(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype "
				    +"from zhandian zd,dianbiao d,dianduview ad,dianfeiview ef  "
					+ "where zd.id=d.zdid and d.dbid=ad.ammeterid_fk and zd.qyzt = '1' and ef.MANUALAUDITSTATUS='2' "
					+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk  "
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ "))B"
					+ " ,(select  ammeterid_fk,MAX(thisdatetime)as datetime from  (select zd.jzname,zd.zdcode,ad.lastdatetime,ad.thisdatetime,ef.ACTUALPAY,ad.ammeterid_fk "
					+

					"from zhandian zd,dianbiao d,dianduview ad,dianfeiview ef  "
					+ "where zd.id=d.zdid and d.dbid=ad.ammeterid_fk and zd.qyzt='1' "
					+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk"
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ")) group by ammeterid_fk) C  where C.ammeterid_fk=B.ammeterid_fk and B.thisdatetime=C.datetime";
			System.out.println( "电费暂估查询："+sql);
           db.connectDb();
			
			rs = db.queryAll(sql);
		
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	//电费暂估导出	
	public synchronized ArrayList getPageDatazangu(String whereStr,
			String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select B.*,to_char(lastdatetime,'yyyy-mm-dd') lastdatetimea,to_char(thisdatetime,'yyyy-mm-dd') thisdatetimea from (select zd.jzname,d.dbname,zd.zdcode,ad.lastdatetime,ad.thisdatetime,ef.ACTUALPAY,ad.ammeterid_fk,(case when zd.shi is not null then (select agname from d_area_grade where agcode=zd.shi) else '' end) "
					+"||(case when zd.xian is not null then (select distinct agname from d_area_grade where agcode=zd.xian) else '' end)"
					+"||(case when zd.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=zd.xiaoqu) else '' end) as szdq,"
					+"(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype "
				    +"from zhandian zd,dianbiao d,dianduview ad,dianfeiview ef  "
					+ "where zd.id=d.zdid and d.dbid=ad.ammeterid_fk and zd.qyzt = '1' and ef.MANUALAUDITSTATUS='2' "
					+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk  "
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ "))B"
					+ " ,(select  ammeterid_fk,MAX(thisdatetime)as datetime from  (select zd.jzname,zd.zdcode,ad.lastdatetime,ad.thisdatetime,ef.ACTUALPAY,ad.ammeterid_fk "
					+

					"from zhandian zd,dianbiao d,dianduview ad,dianfeiview ef  "
					+ "where zd.id=d.zdid and d.dbid=ad.ammeterid_fk and zd.qyzt='1' "
					+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk"
					+ whereStr
					+ " "
					+ "and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId
					+ "'))"
					+ fzzdstr
					+ ")) group by ammeterid_fk) C  where C.ammeterid_fk=B.ammeterid_fk and B.thisdatetime=C.datetime";
			System.out.println( "电费暂估导出："+sql);
           db.connectDb();
			
			rs = db.queryAll(sql);
		
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	//合同单查询
	public synchronized ArrayList getBargainDan(int start,
			String whereStr, String loginId) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
		try {
			fzzdstr = getFuzeZdid(db, loginId);

			sql = "select p.pjje,p.htbh,zd.zdcode,zd.jzname,to_char(p.accountmonth,'yyyy-mm') accountmonth,p.prepayment_ammeterid,d.dbname,p.money,to_char(p.startmonth,'yyyy-mm') startmonth,to_char(p.endmonth,'yyyy-mm') endmonth,p.id,"
				    +"(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype"
					+ " from yufufeiview p,dianbiao d,zhandian zd"
					+ " where d.dbid=p.prepayment_ammeterid and zd.id=d.zdid and zd.qyzt='1' and p.cityaudit<='0' and d.dfzflx='dfzflx02'"				
					+ whereStr
					+ " "
			    	+"and ((zd.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
					+ loginId + "'))" + fzzdstr + ")";
            System.out.println("合同单查询："+sql.toString());
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
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
			db.close();
		}catch (DbException de) {
			de.printStackTrace();
		}catch (Exception de) {
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
	//取消电费单打印       电费单打印和预付费打印 都调用这个方法
  public synchronized String qxdfddy(ElectricFeesFormBean formBean,String chooseIdStr1,String chooseIdStr2) {
	      String msg = "取消打印失败！";
	      String s="to_char(sysdate,'yyyy-mm-dd hh24:MI:SS')";
	      DataBase db = new DataBase();
	      boolean flag = false;
		  String sql1="update electricfees e set e.liuchenghao='' " +",e.dayinren='"+formBean.getDayinren()+"' "+",e.dayintime=to_date('"+formBean.getDayintime()+"','yyyy-mm-dd hh24:MI:SS') " +"where e.dfuuid in("+chooseIdStr1+")"; 
		  String sql2="update prepayment p set p.liuchenghao='' " +",p.dayinren='"+formBean.getDayinren()+"' "+",p.dayintime=to_date('"+formBean.getDayintime()+"','yyyy-mm-dd hh24:MI:SS') " +"where p.uuid in("+chooseIdStr2+")"; 
		  try {
			  boolean flag1=false;
			  boolean flag2=false;
			  if(chooseIdStr1!=""&&chooseIdStr1!=null){
				  System.out.println("取消流程号月结:"+sql1);
			    if(db.update(sql1)>=1){
			    	flag1=true;
			    };
			  }
			  if(chooseIdStr2!=""&&chooseIdStr2!=null){
				  System.out.println("取消流程号预支:"+sql2);
				    if(db.update(sql2)>=1){
				    	flag2=true;
				    };  
				  }
			  
			  flag=(flag1||flag2);
			  if(flag){
				  msg = "取消打印成功！";
			  }
			} catch (Exception e) {
				try {
				db.rollback();
				} catch (DbException dee) {
				dee.printStackTrace();
				}
				e.printStackTrace();
			}finally {
				      try {
				        db.close();
				      }
				      catch (DbException de) {
				        de.printStackTrace();
				      }
			}
		    return msg;
	  }
  
  
  //合同补发票
	public synchronized List<PrepaymentFormBean>  getHtd(String whereStr,
			String loginId) {		
		List<PrepaymentFormBean>  list = new ArrayList<PrepaymentFormBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
        ResultSet rs = null;
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			sql = " SELECT DISTINCT P.PJJE, P.HTBH,ZD.ZDCODE,ZD.JZNAME,TO_CHAR(P.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,P.PREPAYMENT_AMMETERID,D.DBNAME,P.MONEY,TO_CHAR(P.STARTMONTH,'yyyy-mm') STARTMONTH,TO_CHAR(P.ENDMONTH,'yyyy-mm') ENDMONTH,P.ID," +
			    " (CASE WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE ''  END) || (CASE  WHEN ZD.XIAOQU IS NOT NULL THEN  " +
			    " (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ, " +
				" (SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE = 'stationtype') STATIONTYPE, " +
				" (SELECT I.NAME FROM INDEXS I WHERE I.CODE=D.DFZFLX AND I.TYPE='dfzflx') DFLX,P.UUID,ZD.SHI "+
				"  FROM YUFUFEIVIEW P, DIANBIAO D, ZHANDIAN ZD "+ 
				"  WHERE D.DBID = P.PREPAYMENT_AMMETERID AND ZD.ID = D.ZDID  "+whereStr+
				"  AND ZD.QYZT = '1' AND D.DBQYZT='1'  AND P.FINANCEAUDIT = '2' AND D.DFZFLX = 'dfzflx02' "+
					" AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "')) ORDER BY ZD.SHI,ZD.JZNAME,TO_CHAR(P.ACCOUNTMONTH, 'yyyy-mm')" ;
            System.out.println("合同单补发票："+sql.toString());
			db.connectDb();		
			rs = db.queryAll(sql.toString());
			//Query query = new Query();
			//list = query.query(rs);
			while(rs.next()){
				PrepaymentFormBean formbean=new PrepaymentFormBean();
				String s=rs.getString(1);
				if("".equals(s)||null==s){
					s="0";
				}
				formbean.setPjje(Double.parseDouble(s));
				formbean.setHtbh(rs.getString(2));
				formbean.setZdcode(rs.getString(3));
				formbean.setJzname(rs.getString(4));
				//P.ACCOUNTMONTH,P.PREPAYMENT_AMMETERID,D.DBNAME,P.MONEY,P.STARTMONTH,P.ENDMONTH,P.ID," +
				formbean.setAccountmonth(rs.getString(5));
				formbean.setPrepaymentammeterid(rs.getString(6));
				formbean.setDbname(rs.getString(7));
				formbean.setMoney(rs.getString(8));
				formbean.setStartmonth(rs.getString(9));
				formbean.setEndmonth(rs.getString(10));
				formbean.setId(rs.getString(11));
				formbean.setSzdq(rs.getString(12));
				formbean.setStationtype(rs.getString(13));
				formbean.setDflx(rs.getString(14));
				formbean.setUuid(rs.getString(15));
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
			if(db!=null){
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
			}
		}
		//System.out.println(list.toString() + "------------------");
		return list;
	}
	//合同单补录发票审核 
	public synchronized List<PrepaymentFormBean>  getHtdsh(String whereStr,
			String loginId) {		
		List<PrepaymentFormBean>  list = new ArrayList<PrepaymentFormBean>();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		String sql = "";
		String fzzdstr = "";
		DataBase db = new DataBase();
        ResultSet rs = null;
		try {
			fzzdstr = getFuzeZdid(db, loginId);
			sql = " SELECT DISTINCT P.PJJE, P.HTBH,ZD.ZDCODE,ZD.JZNAME,to_char(P.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH,P.PREPAYMENT_AMMETERID,D.DBNAME,P.MONEY,to_char(P.STARTMONTH,'yyyy-mm'),to_char(P.ENDMONTH,'yyyy-mm'),P.ID," +
			    " (CASE WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE ''  END) || (CASE  WHEN ZD.XIAOQU IS NOT NULL THEN  " +
			    " (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ, " +
				" (SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE = 'stationtype') STATIONTYPE, " +
				" (SELECT I.NAME FROM INDEXS I WHERE I.CODE=D.DFZFLX AND I.TYPE='dfzflx') DFLX,P.UUID,ZD.SHI, "+
				" (SELECT I.NAME FROM INDEXS I WHERE I.CODE=P.NOTETYPEID AND I.TYPE='PJLX') PJLX,P.FPJE,P.FPSH"+
				"  FROM YUFUFEIVIEW P, DIANBIAO D, ZHANDIAN ZD "+ 
				"  WHERE D.DBID = P.PREPAYMENT_AMMETERID AND ZD.ID = D.ZDID "+whereStr+
				"  AND ZD.QYZT = '1' AND D.DBQYZT='1' AND P.FINANCEAUDIT = '2' AND D.DFZFLX = 'dfzflx02' "+
					" AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "')) ORDER BY ZD.SHI,ZD.JZNAME,ACCOUNTMONTH" ;
            System.out.println("合同单补录发票审核查询："+sql.toString());
			db.connectDb();		
			rs = db.queryAll(sql.toString());
			//Query query = new Query();
			//list = query.query(rs);
			while(rs.next()){
				PrepaymentFormBean formbean=new PrepaymentFormBean();
				String s=rs.getString(1);
				if("".equals(s)||null==s){
					s="0";
				}
				formbean.setPjje(Double.parseDouble(s));
				formbean.setHtbh(rs.getString(2));
				formbean.setZdcode(rs.getString(3));
				formbean.setJzname(rs.getString(4));
				//P.ACCOUNTMONTH,P.PREPAYMENT_AMMETERID,D.DBNAME,P.MONEY,P.STARTMONTH,P.ENDMONTH,P.ID," +
				formbean.setAccountmonth(rs.getString(5));
				formbean.setPrepaymentammeterid(rs.getString(6));
				formbean.setDbname(rs.getString(7));
				formbean.setMoney(rs.getString(8));
				formbean.setStartmonth(rs.getString(9));
				formbean.setEndmonth(rs.getString(10));
				formbean.setId(rs.getString(11));
				formbean.setSzdq(rs.getString(12));
				formbean.setStationtype(rs.getString(13));
				formbean.setDflx(rs.getString(14));
				formbean.setUuid(rs.getString(15));
				formbean.setPjlx(rs.getString(17));
				formbean.setFpje(rs.getString(18));
				formbean.setFpsh(rs.getString(19));
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
			if(db!=null){
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
			}
		}
		return list;
	}
	
}
