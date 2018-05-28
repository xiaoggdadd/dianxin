package com.noki.tongjichaxu.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.function.CityQueryBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class fybzCountBean {
	private int allPage;

	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}

	public int getAllPage() {
		return this.allPage;
	}

	public ArrayList getData(String dbid) {

		ArrayList list = new ArrayList();
		String sql = "select t.sheiebanid,t.mingcheng,t.guige,t.shuoshuzhuanye, t.shuoshuwangyuan,t.kjkmcode,t.qcbcode,t.zymxcode,t.dbili, t.sccj,t.zcbh,t.bccd,t.beizhu "
				+ "from sbgl t, dianbiao d, zhandian z,indexs i "
				+ "where i.code=z.property and z.id = d.zdid and t.dianbiaoid = d.dbid  and d.dbid='"
				+ dbid
				+ "' and  (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '263'))";
		DataBase db = new DataBase();
		ResultSet set = null;
		System.out.println("站点分摊明细：" + sql.toString());
		try {
			set = db.queryAll(sql);
			Query query = new Query();
			list = query.query(set);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	// 站点分摊明细
	public synchronized ArrayList getPageData(int start, String whereStr,
			String loginId) {
		ArrayList list = new ArrayList();
		ArrayList list1 = new ArrayList();
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		StringBuffer sql = new StringBuffer();
		sql
				.append("select distinct z.jzname, i.name,t.dianbiaoid "
						+ "from sbgl t, dianbiao d, zhandian z,indexs i"
						+ " where i.code=z.property and z.id = d.zdid and z.qyzt='1' and t.dianbiaoid = d.dbid "
						+ whereStr
						+ " and  (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
						+ loginId + "'))");

		DataBase db = new DataBase();
		ResultSet rs = null;

		System.out.println("--" + sql.toString());
		try {
			db.connectDb();
			/*
			 * StringBuffer strall = new StringBuffer();strall.append(
			 * "select count(*) from sbgl t, dianbiao d, zhandian z,indexs i" +
			 * " where i.code=z.property and z.id = d.zdid and z.qyzt='1' and t.dianbiaoid = d.dbid "
			 * +whereStr+
			 * " and  (z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"
			 * +loginId+"'))"); rs = db.queryAll(strall.toString()); if
			 * (rs.next()) { this.setAllPage((rs.getInt(1)+14)/15); }
			 */
			// NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql.toString());

			Query query = new Query();
			list = query.query(rs);
			System.out.println(list.toString());

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

	/**
	 *@author GT
	 *费用及报账对照统计数据查询
	 * */
	public synchronized ArrayList getFt(String whereStr, String loginId,
			String whereyc, String wherezc,String fysxyjStr1,String fysxyjStr,String fysxyzStr,String fysxyzStr1) {
		ArrayList list = new ArrayList(); 
		double allmonery = 0.00;
		double ballmonery = 0.00;
		CTime ct = new CTime();
		String kjnd = ct.formatShortDate().substring(0, 4);
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlyf = new StringBuffer();
		sql.append("SELECT ABC.ID, ABC.SZDQ, ABC.JZNAME, ABC.DBNAME,ABC.electricfee_id, ABC.DFZFLX, to_char(ABC.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH, ABC.ZSHYDL, ABC.ACTUALPAY, ABC.ENTRYPENSONNEL, ABC.ENTRYTIME,  ABC.YJ01, ABC.YJ02, ABC.YJ03, ABC.YJ04, ABC.YJ05, ABC.YJ06, ABC.DFALL, ABC.ERJ01, ABC.ERJ02,ABC.ERJ03,ABC.ERJ04,  ABC.ERJ05, ABC.ERJ06, ABC.JSZQ, ABC.WC, ABC.WF, ABC.YC, ABC.WC1, ABC.WF1, ABC.YC1, ABC.DBID" +
				" FROM (SELECT (CASE WHEN ZD.SHI IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI)  ELSE  '' END) || (CASE  WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END) || (CASE WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME  FROM D_AREA_GRADE  WHERE AGCODE = ZD.XIAOQU)" +
				" ELSE '' END) AS SZDQ, DB.DBID,DFV.electricfee_id, ZD.JZNAME, ZD.ID, DB.DBNAME, (SELECT NAME FROM INDEXS I WHERE I.CODE = DB.DFZFLX) AS DFZFLX, DFV.ACCOUNTMONTH, DDV.BLHDL ZSHYDL,  DFV.ACTUALPAY, (SELECT distinct NAME FROM ACCOUNT A  WHERE A.ACCOUNTNAME = DFV.ENTRYPENSONNEL) AS ENTRYPENSONNEL, DFV.ENTRYTIME, (DFV.NETWORKDF / 10000) AS YJ01, (DFV.MARKETDF / 10000) AS YJ02, (DFV.ADMINISTRATIVEDF / 10000) AS YJ03, (DFV.INFORMATIZATIONDF / 10000) AS YJ04, (DFV.BUILDDF / 10000) AS YJ05," +
				"  (DFV.DDDF / 10000) AS YJ06, (DFV.ACTUALPAY / 10000) AS DFALL, ab.ERJ01, ab.ERJ02, ab.ERJ03,  ab.ERJ04,  ab.ERJ05,  ab.ERJ06,        (DDV.THISDATETIME - DDV.LASTDATETIME) JSZQ, COUNT(DISTINCT(CASE WHEN AA.BILI = 100 THEN DB.ID END)) AS WC,  COUNT(DISTINCT(CASE WHEN AA.BILI = 0 OR AA.BILI IS NULL THEN  DB.ID END)) AS WF, COUNT(DISTINCT(CASE  WHEN AA.BILI <> 0 AND AA.BILI <> 100 AND AA.BILI IS NOT NULL THEN DB.ID" +
				"  END)) AS YC,COUNT(DISTINCT(CASE  WHEN BB.KJKMCODE IS NOT NULL AND BB.QCBCODE IS NOT NULL AND BB.ZYMXCODE IS NOT NULL THEN DB.ID END)) AS WC1, COUNT(DISTINCT DB.ID) - COUNT(DISTINCT(CASE WHEN BB.KJKMCODE IS NOT NULL AND BB.QCBCODE IS NOT NULL AND  BB.ZYMXCODE IS NOT NULL THEN DB.ID END)) - COUNT(DISTINCT(CASE WHEN CC.BILI <> 0 AND CC.BILI <> 100 AND CC.BILI IS NOT NULL THEN DB.ID END)) AS WF1, COUNT(DISTINCT(CASE WHEN CC.BILI <> 0 AND CC.BILI <> 100 AND CC.BILI IS NOT NULL THEN DB.ID" +
				"  END)) AS YC1 FROM DIANFEIVIEW DFV,DIANDUVIEW DDV,  DIANBIAO DB, ZHANDIAN ZD, (SELECT d.dbid,e.entrytime,e.actualpay, SUM(CASE WHEN s.zymxcode='11' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS ERJ01, SUM(CASE WHEN s.zymxcode='12' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS ERJ02, SUM(CASE WHEN s.zymxcode='21' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS ERJ03, SUM(CASE WHEN s.zymxcode='09' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS ERJ04, SUM(CASE WHEN s.zymxcode='19' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS ERJ05, SUM(CASE WHEN s.zymxcode='00' THEN e.actualpay*s.dbili*s.xjbili/100000000 END)AS ERJ06" +
				"  FROM ZHANDIAN Z,DIANBIAO  D, SBGL  S, dianduview A, dianfeiview   E WHERE Z.ID = D.ZDID AND d.dbid=s.dianbiaoid(+) AND D.DBID = A.AMMETERID_FK AND A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND Z.QYZT = '1' AND D.DBQYZT = '1'  AND Z.XUNI = '0'  AND E.manualauditstatus >='1'" +wherezc+fysxyjStr1+
				"   GROUP  BY D.DBID,e.entrytime,e.actualpay,e.electricfee_id)ab, (SELECT SSS.DIANBIAOID, SUM(SSS.DBILI) AS BILI FROM (SELECT DISTINCT S.DIANBIAOID AS DIANBIAOID, S.SHEIEBANID, S.DBILI FROM SBGL S) SSS GROUP BY SSS.DIANBIAOID) AA, (SELECT S.DIANBIAOID, S.SHEIEBANID, " +
				"  S.XJID, S.SHUOSHUZHUANYE, S.KJKMCODE, S.QCBCODE, S.ZYMXCODE, S.XJBILI FROM SBGL S) BB, (SELECT S.DIANBIAOID,  SUM(S.XJBILI) / COUNT(DISTINCT S.SHEIEBANID) AS BILI  FROM SBGL S GROUP BY S.DIANBIAOID) CC WHERE ZD.ID = DB.ZDID AND DB.DBID = AB.dbid and ab.entrytime=dfv.entrytime and ab.actualpay=dfv.actualpay AND DB.DBID = DDV.AMMETERID_FK AND DDV.AMMETERDEGREEID = DFV.AMMETERDEGREEID_FK   AND DB.DBID = AA.DIANBIAOID(+) AND DB.DBID = BB.DIANBIAOID(+) AND DB.DBID = CC.DIANBIAOID(+)  AND ZD.QYZT = '1'  AND DB.DBQYZT = '1' AND DFV.manualauditstatus >= '1'  AND ZD.XUNI='0'" +whereStr+fysxyjStr+
				"   GROUP BY ZD.SHI,  ZD.XIAN, ZD.XIAOQU, ZD.JZNAME,ZD.ID, DB.DBNAME, DB.DBID, DB.DFZFLX, DFV.ACCOUNTMONTH, DDV.BLHDL, DFV.ACTUALPAY, DFV.ENTRYPENSONNEL,  DFV.ENTRYTIME,  DDV.THISDATETIME, DDV.LASTDATETIME,DFV.NETWORKDF ,DFV.MARKETDF, DFV.ADMINISTRATIVEDF,DFV.INFORMATIZATIONDF ,DFV.BUILDDF,DFV.DDDF  ,ab.ERJ01, ab.ERJ02,  ab.ERJ03,  ab.ERJ04,  ab.ERJ05, ab.ERJ06,DFV.electricfee_id ) ABC" +
				"  WHERE 1 = 1 "+whereyc+"" );
		sqlyf.append("SELECT ABC.ID, ABC.ammeterdegreeid_fk,ABC.SZDQ, ABC.JZNAME, ABC.DBNAME, ABC.DFZFLX, to_char(ABC.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH, ABC.ZSHYDL,ABC.ACTUALPAY, ABC.ENTRYPENSONNEL, ABC.ENTRYTIME,ABC.YJ01, ABC.YJ02, ABC.YJ03, ABC.YJ04, ABC.YJ05, ABC.YJ06, ABC.DFALL, ABC.ERJ01, ABC.ERJ02, ABC.ERJ03, ABC.ERJ04, ABC.ERJ05, ABC.ERJ06, ABC.JSZQ, ABC.WC, ABC.WF, ABC.YC,  ABC.WC1, ABC.WF1, ABC.YC1, ABC.DBID FROM (SELECT (CASE WHEN ZD.SHI IS NOT NULL THEN" +
				"(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.SHI) ELSE '' END) || (CASE  WHEN ZD.XIAN IS NOT NULL THEN (SELECT DISTINCT AGNAME   FROM D_AREA_GRADE   WHERE AGCODE = ZD.XIAN) ELSE '' END) || (CASE  WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE  WHERE AGCODE = ZD.XIAOQU) ELSE '' END) AS SZDQ,  DB.DBID,DFV.ammeterdegreeid_fk, ZD.JZNAME, ZD.ID, DB.DBNAME, (SELECT NAME FROM INDEXS I WHERE I.CODE = DB.DFZFLX) AS DFZFLX, DFV.ACCOUNTMONTH," +
				"DFV.dianliang ZSHYDL, DFV.money ACTUALPAY, (SELECT distinct NAME  FROM ACCOUNT A  WHERE A.ACCOUNTNAME = DFV.ENTRYPENSONNEL) AS ENTRYPENSONNEL, DFV.ENTRYTIME,(DFV.NETWORKDF / 10000) AS YJ01, (DFV.MARKETDF / 10000) AS YJ02,(DFV.ADMINISTRATIVEDF / 10000) AS YJ03, (DFV.INFORMATIZATIONDF / 10000) AS YJ04, (DFV.BUILDDF / 10000) AS YJ05, (DFV.DDDF / 10000) AS YJ06, (DFV.money / 10000) AS DFALL, ab.ERJ01, ab.ERJ02, ab.ERJ03, ab.ERJ04, ab.ERJ05, ab.ERJ06, 'no data' JSZQ,  COUNT(DISTINCT(CASE  WHEN AA.BILI = 100 THEN DB.ID" +
				" END)) AS WC, COUNT(DISTINCT(CASE  WHEN AA.BILI = 0 OR AA.BILI IS NULL THEN  DB.ID END)) AS WF, COUNT(DISTINCT(CASE WHEN AA.BILI <> 0 AND AA.BILI <> 100 AND AA.BILI IS NOT NULL THEN DB.ID  END)) AS YC, COUNT(DISTINCT(CASE WHEN BB.KJKMCODE IS NOT NULL AND BB.QCBCODE IS NOT NULL AND  BB.ZYMXCODE IS NOT NULL THEN DB.ID  END)) AS WC1, COUNT(DISTINCT DB.ID) -  COUNT(DISTINCT(CASE  WHEN BB.KJKMCODE IS NOT NULL AND BB.QCBCODE IS NOT NULL AND  BB.ZYMXCODE IS NOT NULL THEN  DB.ID END)) -  COUNT(DISTINCT(CASE WHEN CC.BILI <> 0 AND CC.BILI <> 100 AND CC.BILI IS NOT NULL THEN" +
				" DB.ID END)) AS WF1, COUNT(DISTINCT(CASE WHEN CC.BILI <> 0 AND CC.BILI <> 100 AND CC.BILI IS NOT NULL THEN  DB.ID  END)) AS YC1  FROM yufufeiview DFV, DIANBIAO DB, ZHANDIAN ZD, (SELECT d.dbid, e.entrytime,e.money,SUM(CASE  WHEN s.zymxcode = '11' THEN e.money * s.dbili * s.xjbili / 100000000 END) AS ERJ01, SUM(CASE WHEN s.zymxcode = '12' THEN  e.money * s.dbili * s.xjbili / 100000000  END) AS ERJ02, SUM(CASE  WHEN s.zymxcode = '21' THEN e.money * s.dbili * s.xjbili / 100000000" +
				" END) AS ERJ03, SUM(CASE  WHEN s.zymxcode = '09' THEN  e.money * s.dbili * s.xjbili / 100000000  END) AS ERJ04, SUM(CASE  WHEN s.zymxcode = '19' THEN e.money * s.dbili * s.xjbili / 100000000 END) AS ERJ05, SUM(CASE  WHEN s.zymxcode = '00' THEN e.money * s.dbili * s.xjbili / 100000000 END) AS ERJ06 FROM ZHANDIAN   Z,  DIANBIAO    D, SBGL   S,  yufufeiview  e WHERE Z.ID = D.ZDID  AND d.dbid=s.dianbiaoid(+) AND D.DBID = e.prepayment_ammeterid   AND Z.QYZT = '1' AND D.DBQYZT ='1' AND E.cityaudit = '1'  AND Z.XUNI ='0'" +
				wherezc+fysxyzStr1+
				" " +
				" GROUP BY D.DBID,e.entrytime,e.money) ab, (SELECT SSS.DIANBIAOID, SUM(SSS.DBILI) AS BILI FROM (SELECT DISTINCT S.DIANBIAOID AS DIANBIAOID, S.SHEIEBANID, S.DBILI FROM SBGL S) SSS GROUP BY SSS.DIANBIAOID) AA, (SELECT S.DIANBIAOID,  S.SHEIEBANID, S.XJID, S.SHUOSHUZHUANYE, S.KJKMCODE, S.QCBCODE, S.ZYMXCODE, S.XJBILI FROM SBGL S) BB, (SELECT S.DIANBIAOID,  SUM(S.XJBILI) / COUNT(DISTINCT S.SHEIEBANID) AS BILI  FROM SBGL S" +
				" GROUP BY S.DIANBIAOID) CC WHERE ZD.ID = DB.ZDID  and ab.entrytime=dfv.entrytime  and ab.money = dfv.money AND DB.DBID = AB.dbid AND DB.DBID = DFV.prepayment_ammeterid   AND DB.DBID = AA.DIANBIAOID(+) AND DB.DBID = BB.DIANBIAOID(+) AND DB.DBID = CC.DIANBIAOID(+) AND ZD.QYZT = '1' AND DB.DBQYZT ='1' AND ZD.XUNI = '0' AND DFV.cityaudit = '1'" +whereStr +fysxyzStr+
				" " +
				" GROUP BY ZD.SHI, ZD.XIAN, ZD.XIAOQU, ZD.JZNAME, ZD.ID, DB.DBNAME,  DB.DBID, DB.DFZFLX,  DFV.accountmonth, DFV.dianliang, DFV.money, DFV.ENTRYPENSONNEL, DFV.ENTRYTIME, DFV.startdate, DFV.enddate,  DFV.NETWORKDF, DFV.MARKETDF, DFV.ADMINISTRATIVEDF,  DFV.INFORMATIZATIONDF,  DFV.BUILDDF," +
				"  DFV.DDDF, ab.ERJ01, ab.ERJ02, ab.ERJ03, ab.ERJ04, ab.ERJ05,ab.ERJ06,DFV.ammeterdegreeid_fk) ABC WHERE 1 = 1" + whereyc +"");
		DataBase db = new DataBase();
		ResultSet rs = null;
		ResultSet rsyf = null;
		DecimalFormat df = new DecimalFormat("#0.00");
		System.out.println("费用及报账对照统计数据查询 月结：" + sql.toString());
		System.out.println("费用及报账对照统计数据查询 预付: "+sqlyf.toString());
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			rsyf = db.queryAll(sqlyf.toString());
			//月结
			while (rs.next()) {
				fybzbean bean = new fybzbean();
				bean.setZdid(rs.getString("ID") != null ? rs.getString("ID")
						: "");
				bean.setArea(rs.getString("SZDQ") != null ? rs
						.getString("SZDQ") : "");
				bean.setZdname(rs.getString("JZNAME") != null ? rs
						.getString("JZNAME") : "");
				bean.setDianbiaoid(rs.getString("DBID") != null ? rs
						.getString("DBID") : "");
				bean.setDlid(rs.getString("electricfee_id") != null ? rs
						.getString("electricfee_id") : "");
				bean.setDbname(rs.getString("DBNAME") != null ? rs
						.getString("DBNAME") : "");
				bean.setDfzflx(rs.getString("DFZFLX") != null ? rs
						.getString("DFZFLX") : "");
				bean.setBzyf(rs.getString("ACCOUNTMONTH") != null ? rs
						.getString("ACCOUNTMONTH") : "");
				double zshydl = rs.getDouble("ZSHYDL");
				bean.setZshdl(rs.getString("ZSHYDL") != null ? df
						.format(zshydl) : "");
				double actrypay = rs.getDouble("ACTUALPAY");
				bean.setActrypay(rs.getString("ACTUALPAY") != null ? df
						.format(actrypay) : "");
				bean.setEnterper(rs.getString("ENTRYPENSONNEL") != null ? rs
						.getString("ENTRYPENSONNEL") : "");
				bean.setEntertime(rs.getString("ENTRYTIME") != null ? rs
						.getString("ENTRYTIME") : "");
				bean.setCountmonth(rs.getString("JSZQ") != null ? rs
						.getString("JSZQ") : "");
				
				
				/**
				 * 一级
				 * 
				 * */
				double temp = 0.0000;
				double a1 = 0.00000000;
				a1 = rs.getDouble("YJ01") + rs.getDouble("YJ02")
						+ rs.getDouble("YJ03") + rs.getDouble("YJ04")
						+ rs.getDouble("YJ05") + rs.getDouble("YJ06");
				double a2 = 0.00000000;
				a2 = rs.getDouble("DFALL");
				temp = a1 / a2 * 100;
				
				
                if(temp>100.010){
                	System.out.println(a1);
                	System.out.println(a2);
                	System.out.println(temp);
                	System.out.println(df.format(temp) + "%");
                	System.out.println(bean.getZdid());
                }
				
				bean.setYijibili(df.format(temp) + "%"

				);
				
				allmonery+=a1;
				ballmonery+=a2;
//				System.out.println("a1一级:"+a1);
//				System.out.println("b1all:"+a2);
//				System.out.println(a1/a2);
				

				//System.out.println("++++++"+a1/a2);
				//System.out.println("----:"+bean.getYijibili().toString());
				/**
				 * 二级
				 * 
				 * */
				
				double tempt = 0.0000;
				double b1 = 0.0000000000;
				double b2 = 0.0000000000;

				b1 = (rs.getDouble("ERJ01") + rs.getDouble("ERJ02")
						+ rs.getDouble("ERJ03") + rs.getDouble("ERJ04")
						+ rs.getDouble("ERJ05") + rs.getDouble("ERJ06"));
				b2 = rs.getDouble("DFALL");

				tempt = b1 / b2 * 100;
				bean.setErjibili(df.format(tempt) + "%"

				);
				
				double t = 0.0000000000000000;
				t = rs.getDouble("DFALL");
     
               if ("100.00%".equals(bean.getYijibili().toString())) {
            	   if("100.00%".equals(bean.getErjibili().toString())){
            		  
            	   }else{
            		   list.add(bean);
            		  
            	   }
				}else{
					list.add(bean);
					
				}
			}
			
			//预付
			while (rsyf.next()) {
				fybzbean bean = new fybzbean();
				bean.setZdid(rsyf.getString("ID") != null ? rsyf.getString("ID")
						: "");
				bean.setArea(rsyf.getString("SZDQ") != null ? rsyf
						.getString("SZDQ") : "");
				bean.setZdname(rsyf.getString("JZNAME") != null ? rsyf
						.getString("JZNAME") : "");
				bean.setDianbiaoid(rsyf.getString("DBID") != null ? rsyf
						.getString("DBID") : "");
				bean.setDlid(rsyf.getString("ammeterdegreeid_fk") != null ? rsyf
						.getString("ammeterdegreeid_fk") : "");
				bean.setDbname(rsyf.getString("DBNAME") != null ? rsyf
						.getString("DBNAME") : "");
				bean.setDfzflx(rsyf.getString("DFZFLX") != null ? rsyf
						.getString("DFZFLX") : "");
				bean.setBzyf(rsyf.getString("ACCOUNTMONTH") != null ? rsyf
						.getString("ACCOUNTMONTH") : "");
				double zshydl = rsyf.getDouble("ZSHYDL");
				bean.setZshdl(rsyf.getString("ZSHYDL") != null ? df
						.format(zshydl) : "");
				double actrypay = rsyf.getDouble("ACTUALPAY");
				bean.setActrypay(rsyf.getString("ACTUALPAY") != null ? df
						.format(actrypay) : "");
				bean.setEnterper(rsyf.getString("ENTRYPENSONNEL") != null ? rsyf
						.getString("ENTRYPENSONNEL") : "");
				bean.setEntertime(rsyf.getString("ENTRYTIME") != null ? rsyf
						.getString("ENTRYTIME") : "");
				bean.setCountmonth(rsyf.getString("JSZQ") != null ? rsyf
						.getString("JSZQ") : "");
				
				
				/***
				 * 一级
				 * 
				 * */
				double temp = 0.0000;
				double a1 = 0.00000000;
				a1 = rsyf.getDouble("YJ01") + rsyf.getDouble("YJ02")
						+ rsyf.getDouble("YJ03") + rsyf.getDouble("YJ04")
						+ rsyf.getDouble("YJ05") + rsyf.getDouble("YJ06");
				double a2 = 0.00000000;
				a2 = rsyf.getDouble("DFALL");
				temp = a1 / a2 * 100;
				
				bean.setYijibili(df.format(temp) + "%"

				);
				
				allmonery+=a1;
				ballmonery+=a2;
//				System.out.println("预付a1一级:"+a1);
//				System.out.println("预付b1all:"+a2);
//				System.out.println(a1/a2);
				/***
				 * 二级
				 * 
				 * 
				 * */
				double tempt = 0.0000;
				double b1 = 0.0000000000;
				double b2 = 0.0000000000;

				b1 = (rsyf.getDouble("ERJ01") + rsyf.getDouble("ERJ02")
						+ rsyf.getDouble("ERJ03") + rsyf.getDouble("ERJ04")
						+ rsyf.getDouble("ERJ05") + rsyf.getDouble("ERJ06"));
				b2 = rsyf.getDouble("DFALL");

				tempt = b1 / b2 * 100;
				DecimalFormat s=new DecimalFormat("0.0000");
				s.format(tempt);
				bean.setErjibili(df.format(tempt) + "%"

				);
				
				if ("100.00%".equals(bean.getYijibili().toString())) {
					if("100.00%".equals(bean.getErjibili().toString())){
						
					}else{
						list.add(bean);
						
					}
				}else{
					list.add(bean);
					
				}
			}
			System.out.println(allmonery);
            System.out.println(ballmonery);
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
			if (rsyf != null) {
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

	// 监测点详细分信息
	public ArrayList getFtxx(String dbid, String loginId) {

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql
				.append("SELECT D.DBID,T.XJID,T.MINGCHENG,T.GUIGE,"
						+ "(SELECT QCBNAME FROM QCBDF Q WHERE Q.QCBCODE=T.SHUOSHUZHUANYE)shuoshuzhuanye,"
						+ "(SELECT distinct QCBNAME FROM QCBDF Q WHERE Q.QCBCODE=T.KJKMCODE)kjkmcode,"
						+ "(SELECT distinct QCBNAME FROM QCBDF Q WHERE Q.QCBCODE=T.QCBCODE)qcbcode,"
						+ "(SELECT distinct QCBNAME FROM QCBDF Q WHERE Q.QCBCODE=T.ZYMXCODE)zymxcode,T.DBILI,T.XJBILI,T.SHUOSHUWANGYUAN,T.BCCD   "
						+ "FROM ZHANDIAN Z,DIANBIAO D,SBGL T WHERE Z.ID=D.ZDID AND D.DBID=T.DIANBIAOID "
						+ "AND T.DIANBIAOID='"
						+ dbid
						+ "' AND  (Z.XIAOQU IN (SELECT P.AGCODE FROM PER_AREA P WHERE P.ACCOUNTID='"
						+ loginId + "'))");
		DataBase db = new DataBase();
		ResultSet rs = null;
		System.out.println("站点分摊明细：" + sql.toString());
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while (rs.next()) {
				CityQueryBean bean = new CityQueryBean();
				bean.setDbid(rs.getString("dbid") != null ? rs
						.getString("dbid") : "");
				bean.setXjid(rs.getString("xjid") != null ? rs
						.getString("xjid") : "");
				bean.setMingcheng(rs.getString("mingcheng") != null ? rs
						.getString("mingcheng") : "");
				bean.setGuige(rs.getString("guige") != null ? rs
						.getString("guige") : "");
				bean.setSszy(rs.getString("shuoshuzhuanye") != null ? rs
						.getString("shuoshuzhuanye") : "");
				bean.setKjkm(rs.getString("kjkmcode") != null ? rs
						.getString("kjkmcode") : "");
				bean.setQcb(rs.getString("qcbcode") != null ? rs
						.getString("qcbcode") : "");
				bean.setZymx(rs.getString("zymxcode") != null ? rs
						.getString("zymxcode") : "");
				bean.setDbili(rs.getString("dbili") != null ? rs
						.getString("dbili") : "");
				bean.setXjbili(rs.getString("xjbili") != null ? rs
						.getString("xjbili") : "");
				bean.setSswy(rs.getString("shuoshuwangyuan") != null ? rs
						.getString("shuoshuwangyuan") : "");
				bean.setBchd(rs.getString("bccd") != null ? rs
						.getString("bccd") : "");
				list.add(bean);
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

		return list;
	}
}
