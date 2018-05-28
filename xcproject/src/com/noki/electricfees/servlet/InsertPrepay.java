package com.noki.electricfees.servlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.noki.ammeterdegree.javabean.AmmeterDegreeBean;
import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.ammeterdegree.javabean.Pdtimeform;
import com.noki.daoruelectricfees.javabean.DaoruDianFei;
import com.noki.daoruelectricfees.javabean.DaoruDianLiang;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.electricfees.javabean.PrepaymentBean;
import com.noki.electricfees.javabean.PrepaymentFormBean;
import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.CommonBean;
import com.noki.sysconfig.javabean.AutoAuditBean;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;
import com.ptac.app.inportuserzibaodian.util.Format;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class InsertPrepay {

	StringBuffer sql = new StringBuffer();

	Vector wrongContent = new Vector();
	public DataBase db;

	public InsertPrepay() {
		try {
			db = new DataBase();
			db.connectDb();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeDb() {
		try {
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ResultSet rs = null;

	// 预付费信息导入
	public synchronized Vector getWrong(Vector content, CountForm cform,
			String accountname, String loginId) {
		String lastdatetime = "";
		Date date1 = null, date2 = null;
		String thisdatetime = "", startmonth = "", endmonth = "", inputdatetime = "", accountmonth = "", notetime = "", kptime = "", paydatetime = "";
		String zdid = "";
		String dbid1 = "";
		String actualdegree="";
		String actualpay = "";
		
		SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Object[] a = content.toArray();
		
		PrepaymentFormBean formBean= null;
		AutoAuditBean abean = new AutoAuditBean();
		Pdtimeform pddate = new Pdtimeform();
		ArrayList fylist = new ArrayList();
		fylist = abean.getPageData(1, "");
		for (int i = 1; i < a.length; i++) {
			Vector<String> row = new Vector<String>();
			Object[] b = ((Vector) a[i]).toArray();
			zdid = b[2].toString().trim();
			dbid1 = b[5].toString().trim();
			actualdegree = b[11].toString().trim();
			actualpay = b[15].toString().trim();
			String did = "", dianfei = "", beilv1 = "1", linelosstype1 = "", linelossvalue1 = "", edhdl = "0",qsdbdl="",shicode="",property="",zlfh="",jlfh="",scb="",dfzflx="",stationtype="",gdfs="";
			String zy1 = "", zy2 = "", zy3 = "", zy4 = "", zy5 = "", zy6 = "";
			String sql1 = "SELECT aa.ID,aa.DANJIA,aa.BEILV,aa.LINELOSSTYPE,aa.LINELOSSVALUE,aa.EDHDL,AA.QSDBDL,AA.SHICODE,AA.PROPERTY,AA.ZLFH,AA.JLFH,AA.SCB,AA.DFZFLX,AA.STATIONTYPE,AA.GDFS,"
					+ "max(aa.ZY1),max(aa.ZY2),max(aa.ZY3),max(aa.ZY4),max(aa.ZY5),max(aa.ZY6) "
					+ "from (SELECT D.ID, Z.DIANFEI, D.BEILV, D.LINELOSSTYPE, D.LINELOSSVALUE,Z.EDHDL,D.Danjia,Z.QSDBDL,Z.SHI SHICODE,Z.PROPERTY,Z.ZLFH,Z.JLFH,Z.SCB,D.DFZFLX,Z.STATIONTYPE,Z.GDFS,"
					+ "(CASE WHEN S.SHUOSHUZHUANYE='zylx01' THEN S.DBILI END)AS ZY1,"
					+ "(CASE WHEN S.SHUOSHUZHUANYE='zylx02' THEN S.DBILI END)AS ZY2,"
					+ "(CASE WHEN S.SHUOSHUZHUANYE='zylx03' THEN S.DBILI END)AS ZY3,"
					+ "(CASE WHEN S.SHUOSHUZHUANYE='zylx04' THEN S.DBILI END)AS ZY4,"
					+ "(CASE WHEN S.SHUOSHUZHUANYE='zylx05' THEN S.DBILI END)AS ZY5,  "
					+ "(CASE WHEN S.SHUOSHUZHUANYE='zylx06' THEN S.DBILI END)AS ZY6  "
					+ " FROM ZHANDIAN Z, DIANBIAO D,SBGL S WHERE Z.ID = D.ZDID AND Z.QYZT='1' AND D.DBQYZT='1'  AND D.DBYT = 'dbyt01' "
					+ "  AND S.DIANBIAOID(+)=D.DBID AND Z.ID = ? AND D.DBID=? AND " 
					+ "EXISTS(SELECT 'A' FROM PER_AREA P WHERE P.AGCODE=Z.XIAOQU AND P.ACCOUNTID = ?))aa "
					+ "group by  aa.ID,aa.DANJIA,aa.BEILV,aa.LINELOSSTYPE, aa.LINELOSSVALUE,aa.EDHDL,AA.QSDBDL,AA.SHICODE,AA.PROPERTY,AA.ZLFH,AA.JLFH,AA.SCB,AA.DFZFLX,AA.STATIONTYPE,AA.GDFS";
			ResultSet rs = null;
			//DataBase db = new DataBase();
			
			try {
				// 更改中2013-07-27
				Properties pro = new Properties();
				pro.setProperty("1", zdid);
				pro.setProperty("2", dbid1);
				pro.setProperty("3", loginId);
				//db.connectDb();
				System.out.println("查询分摊比例："+sql1);
		
				rs = db.queryAllyf003(sql1, pro);
				while (rs.next()) {
					did = rs.getString(1);
					dianfei = rs.getString(2);
					beilv1 = rs.getString(3);
					linelosstype1 = rs.getString(4);
					linelossvalue1 = rs.getString(5);
					edhdl = rs.getString(6);
					qsdbdl = rs.getString(7);
					shicode = rs.getString(8);
					property = rs.getString(9);
					zlfh = rs.getString(10);
					jlfh = rs.getString(11);
					scb = rs.getString(12);
					dfzflx = rs.getString(13);
					stationtype = rs.getString(14);
					gdfs = rs.getString(15);
					zy1 = rs.getString(16);
					zy2 = rs.getString(17);
					zy3 = rs.getString(18);
					zy4 = rs.getString(19);
					zy5 = rs.getString(20);
					zy6 = rs.getString(21);
					
				}
				closeDb();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				if(db != null){
					try {
						db.close();
					} catch (DbException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			String sql2 = "SELECT aa.LASTLCH,aa.LASTDF,aa.LASTDL,aa.LASTYUE "
				+ "from (SELECT P.LASTLCH,P.LASTDF,P.LASTDL, round(P.LASTYUE,2) LASTYUE"
				+ " FROM ZHANDIAN Z, DIANBIAO D,PREPAYMENT P WHERE Z.ID = D.ZDID "
				+ " AND D.DBID = P.PREPAYMENT_AMMETERID AND Z.QYZT='1' AND D.DBQYZT='1'  AND D.DBYT = 'dbyt01' "
				+ " AND P.FINANCEAUDIT = '2' AND Z.ID = ? AND D.DBID=? AND " 
				+ "EXISTS(SELECT 'A' FROM PER_AREA P WHERE P.AGCODE=Z.XIAOQU AND P.ACCOUNTID = ?))aa "
				+ "group by aa.LASTLCH,aa.LASTDF,aa.LASTDL,aa.LASTYUE";
		
			ResultSet rs2 = null;
			String lastlch="",lastdf="",lastdl="",lastyue="";
			
			try {
				Properties pro = new Properties();
				pro.setProperty("1", zdid);
				pro.setProperty("2", dbid1);
				pro.setProperty("3", loginId);
				
				System.out.println("查询上期信息："+sql2);
		
				rs2 = db.queryAllyf003(sql2, pro);
				while (rs2.next()) {
					lastlch = rs2.getString(1);
					lastdf = rs2.getString(2);
					lastdl = rs2.getString(3);
					lastyue = rs2.getString(4);
				}
				closeDb();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (rs2 != null) {
					try {
						rs2.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				if(db != null){
					try {
						db.close();
					} catch (DbException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		
			if ("".equals(did) || null == did) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add("未查到" + b[0].toString() + b[2].toString() + "电表"
						+ dbid1);
				wrongContent.add(row);
				continue;
			}
			if (Format.str_d(qsdbdl)==0) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表"
						+ dbid1+"系统中全省定标电量为空！");
				wrongContent.add(row);
				continue;
			}
			if (Format.str_d(edhdl)==0) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表"
						+ dbid1+"系统中额定耗电量电量为空！");
				wrongContent.add(row);
				continue;
			}
			if (Format.str_d(zlfh)==0) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表"
						+ dbid1+"系统中直流负荷为空！");
				wrongContent.add(row);
				continue;
			}
			if (Format.str_d(jlfh)==0) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表"
						+ dbid1+"系统中交流负荷为空！");
				wrongContent.add(row);
				continue;
			}
			if (Format.str_d(scb)==0) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表"
						+ dbid1+"系统中生产标为空！");
				wrongContent.add(row);
				continue;
			}
			if ("".equals(property) || "null".equals(property)||null == property) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表"
						+ dbid1+"系统中站点属性为空！");
				wrongContent.add(row);
				continue;
			}
			String str44 = b[6].toString().trim();
			String str4 = str44.replaceAll(" ", "");

			Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*");
			Pattern pattern1 = Pattern
					.compile("[0-9]*|[0-9]+\\.[0-9]*|-[0-9]*|-[0-9]+\\.[0-9]*");

			if (pattern.matcher(str4).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "上次电表读数格式不正确");
				wrongContent.add(row);
				continue;
			}
			if (str4 == "") {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "上次电表读数为空");
				wrongContent.add(row);
				continue;
			}
			String str55 = b[7].toString().trim();
			String str5 = str55.replaceAll(" ", "");
			if (pattern.matcher(str5).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "本次电表读数格式不正确");
				wrongContent.add(row);
				continue;
			}
			if (str5 == "") {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "本次电表读数为空");
				wrongContent.add(row);
				continue;
			}
		
			if (b[8].toString().trim() == "") {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "起始时间为空");
				wrongContent.add(row);
				continue;
			}
			
			if (b[9].toString().trim() == "") {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "预计结束时间为空");
				wrongContent.add(row);
				continue;
			}
			
			String str88 = b[10].toString().trim();
			String str8 = str88.replaceAll(" ", "");
			if (pattern1.matcher(str8).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "累计电量(度)格式不正确");
				wrongContent.add(row);
				continue;
			} else if (str8.equals("")) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "累计电量(度)为空");
				wrongContent.add(row);
				continue;
			}
			
			String str99 = b[11].toString().trim();
			String str9 = str99.replaceAll(" ", "");
			if (pattern.matcher(str9).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "实际用电量格式不正确");
				wrongContent.add(row);
				continue;
			} else if (str9.equals("")) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "实际用电量为空");
				wrongContent.add(row);
				continue;
			}
			
			if (b[12].toString().trim().equals("")
					|| b[13].toString().trim().equals("")) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "起始结束月份为空");
				wrongContent.add(row);
				continue;
			}
			
			String str155 = b[14].toString().trim();
			String str15 = str155.replaceAll(" ", "");
			if (pattern.matcher(str15).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "单价格式不正确");
				wrongContent.add(row);
				continue;
			} else if (str15.equals("")) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "单价为空");
				wrongContent.add(row);
				continue;
			}
			
			String str177 = b[15].toString().trim();
			String str17 = str177.replaceAll(" ", "");
			if (pattern.matcher(str17).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "实际电费金额格式不正确");
				wrongContent.add(row);
				continue;
			} else if (str17.equals("")) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "实际电费金额为空");
				wrongContent.add(row);
				continue;
			}
			
			if (b[16].toString().trim().equals("")) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "报账月份为空");
				wrongContent.add(row);
				continue;
			}

			Pattern pattern3 = Pattern
					.compile("[0-9]{2}-[0-9]{2}-[0-9]{2}|[0-9]{2}-[0-9]{2}-[0-9]{1}|[0-9]{2}-[0-9]{1}-[0-9]{1}|[0-9]{2}-[0-9]{1}-[0-9]{2}");
			Pattern pattern4 = Pattern
					.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}|[0-9]{4}/[0-9]{2}/[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{2}");
			String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
					+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
					+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
					+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
					+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
					+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
			Pattern pattern5 = Pattern.compile(datePattern2);
			String str66 = b[8].toString().trim();
			lastdatetime = str66.replaceAll(" ", "");
			if ((b[8].toString().trim().contains("/") && pattern4.matcher(
					lastdatetime).matches() == true&&pddate.getDateform1(lastdatetime))
					|| (b[8].toString().trim().contains("-")&&pddate.getDateform(lastdatetime))) {
				if (b[8].toString().trim().contains("/")) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy/MM/dd");
						SimpleDateFormat sdf1 = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date date = sdf.parse(lastdatetime);
						lastdatetime = sdf1.format(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					lastdatetime = str66.replaceAll(" ", "");
				}
			} else {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "起始时间为空   或者   格式不正确");
				wrongContent.add(row);
				continue;
			}
			
			if (pattern3.matcher(lastdatetime).matches() == true) {
				String lastdate = "20" + lastdatetime;
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				try {
					date = sdf1.parse(lastdate);
					lastdatetime = sdf1.format(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pattern5.matcher(lastdatetime).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "起始时间格式不正确!!");
				wrongContent.add(row);
				continue;
			}
			
			String str77 = b[9].toString().trim();
			thisdatetime = str77.replaceAll(" ", "");
			if ((b[9].toString().trim().contains("/") && pattern4.matcher(
					thisdatetime).matches() == true&&pddate.getDateform1(thisdatetime))
					|| (b[9].toString().trim().contains("-")&&pddate.getDateform(thisdatetime))) {
				if (b[9].toString().trim().contains("/")) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy/MM/dd");
						SimpleDateFormat sdf1 = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date date = sdf.parse(thisdatetime);
						thisdatetime = sdf1.format(date);

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					thisdatetime = str77.replaceAll(" ", "");
				}
			} else {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "预计结束时间为空   或者   格式不正确");
				wrongContent.add(row);
				continue;
			}
			
			if (pattern3.matcher(thisdatetime).matches() == true) {
				String lastdate = "20" + thisdatetime;
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				Date date;
				try {
					date = sdf1.parse(lastdate);
					thisdatetime = sdf1.format(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (pattern5.matcher(thisdatetime).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "预计结束时间格式不正确!!");
				wrongContent.add(row);
				continue;
			}
			
			SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date ss = sdf4.parse(lastdatetime);
				Date ee = sdf4.parse(thisdatetime);

				if (ss.getTime() > ee.getTime()) {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
							+ "起始时间大于预计结束时间");
					wrongContent.add(row);
					continue;
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			Pattern pattern2 = Pattern
					.compile("[0-9]{4}-[0-9]{2}|[0-9]{4}/[0-9]{2}|[0-9]{4}-[0-9]{1}|[0-9]{4}/[0-9]{1}");
			String str100 = b[12].toString().trim();
			startmonth = str100.replaceAll(" ", "");
			if ((pattern2.matcher(startmonth).matches() == true && (b[12]
					.toString().trim().contains("/") && b[12].toString().trim()
					.length() <= 7)&&pddate.getMonthform1(startmonth))
					|| (pattern2.matcher(startmonth).matches() == true && (b[12]
							.toString().trim().contains("-") && b[12]
							.toString().trim().length() <= 7)&&pddate.getMonthform(startmonth))) {
				if (b[12].toString().trim().contains("/")) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
						date1 = sdf.parse(startmonth);
						startmonth = sdf1.format(date1);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
					if (b[12].toString().trim().length() < 7) {
						try {
							Date end = sdf1.parse(b[12].toString().trim());
							startmonth = sdf1.format(end);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						startmonth = str100.replaceAll(" ", "");
					}
				}
			} else {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "起始年月为空或者格式不正确");
				wrongContent.add(row);
				continue;
			}
			String str111 = b[13].toString().trim();
			endmonth = str111.replaceAll(" ", "");
			if ((pattern2.matcher(endmonth).matches() == true && (b[13]
					.toString().trim().contains("/") && b[13].toString().trim()
					.length() <= 7)&&pddate.getMonthform1(endmonth))
					|| (pattern2.matcher(endmonth).matches() == true && (b[13]
							.toString().trim().contains("-") && b[13]
							.toString().trim().length() <= 7)&&pddate.getMonthform(endmonth))) {
				if (b[13].toString().trim().contains("/")) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
						date2 = sdf.parse(endmonth);
						endmonth = sdf1.format(date2);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
					if (b[13].toString().trim().length() < 7) {
						try {
							Date end = sdf1.parse(endmonth);
							endmonth = sdf1.format(end);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						endmonth = str111.replaceAll(" ", "");
					}

				}
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
				try {
					Date ss = sdf1.parse(startmonth);
					Date ee = sdf1.parse(endmonth);

					if (ss.getTime() > ee.getTime()) {
						for (int j = 0; j < b.length; j++) {
							row.add(b[j].toString().trim());
						}
						row.add(b[0].toString() + b[2].toString() + "电表"
								+ dbid1 + "起始月份大于结束月份");
						wrongContent.add(row);
						continue;
					}

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "结束年月为空或者格式不正确");
				wrongContent.add(row);
				continue;
			}

			String str188 = b[16].toString().trim();
			accountmonth = str188.replaceAll(" ", "");
			if ((pattern2.matcher(accountmonth).matches() == true && (b[16]
					.toString().trim().contains("/") && b[16].toString().trim()
					.length() <= 7)&&pddate.getMonthform1(accountmonth))
					|| (pattern2.matcher(accountmonth).matches() == true && (b[16]
							.toString().trim().contains("-") && b[16]
							.toString().trim().length() <= 7)&&pddate.getMonthform(accountmonth))) {
				if (b[16].toString().trim().contains("/")) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
						Date date = sdf.parse(accountmonth);
						accountmonth = sdf1.format(date);

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {

					accountmonth = str188.replaceAll(" ", "");
				}

			} else {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "报账月份为空或者格式不正确");
				wrongContent.add(row);
				continue;
			}
			String str21 = "";
			if ("".equals(b[19].toString().trim()) || null == b[19].toString().trim()) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "票据金额为空!");
				wrongContent.add(row);
				continue;
			} else if (pattern.matcher(b[19].toString().trim()).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "票据金额格式不正确");
				wrongContent.add(row);
				continue;
			}else{
				str21 = b[19].toString().trim();
			}

			if (!b[20].toString().trim().equals("")
					&& b[20].toString().trim() != null) {
				String str211 = b[20].toString().trim();
				notetime = str211.replaceAll(" ", "");
				if ((b[20].toString().trim().contains("/") && pattern4.matcher(
						notetime).matches() == true&&pddate.getDateform1(notetime))
						|| (b[20].toString().trim().contains("-")&&pddate.getDateform(notetime))) {
					if (b[20].toString().trim().contains("/")) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy/MM/dd");
							SimpleDateFormat sdf1 = new SimpleDateFormat(
									"yyyy-MM-dd");
							Date date = sdf.parse(b[20].toString().trim());
							notetime = sdf1.format(date);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						notetime = str211.replaceAll(" ", "");
					}
				}else {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
							+ "票据时间格式不正确");
					wrongContent.add(row);
					continue;
				}
				if (pattern3.matcher(notetime).matches() == true) {
					String lastdate = "20" + notetime;
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					Date date;
					try {
						date = sdf1.parse(lastdate);
						notetime = sdf1.format(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (pattern5.matcher(notetime).matches() == false) {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
							+ "票据时间格式不正确!!");
					wrongContent.add(row);
					continue;
				}
			} else {
				notetime = b[20].toString().trim();
			}

			if (!b[21].toString().trim().equals("")
					&& b[21].toString().trim() != null) {
				String str222 = b[21].toString().trim();
				kptime = str222.replaceAll(" ", "");
				if ((b[21].toString().trim().contains("/") && pattern4.matcher(
						kptime).matches() == true&&pddate.getDateform1(kptime))
						|| (b[21].toString().trim().contains("-")&&pddate.getDateform(kptime))) {
					if (b[21].toString().trim().contains("/")) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy/MM/dd");
							SimpleDateFormat sdf1 = new SimpleDateFormat(
									"yyyy-MM-dd");
							Date date = sdf.parse(kptime);
							kptime = sdf1.format(date);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						kptime = str222.replaceAll(" ", "");
					}
				} else {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
							+ "开票时间格式不正确");
					wrongContent.add(row);
					continue;
				}
				if (pattern3.matcher(kptime).matches() == true) {
					String lastdate = "20" + kptime;
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					Date date;
					try {
						date = sdf1.parse(lastdate);
						kptime = sdf1.format(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (pattern5.matcher(kptime).matches() == false) {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
							+ "开票时间格式不正确!!");
					wrongContent.add(row);
					continue;
				}
			} else {
				kptime = b[21].toString().trim();
			}

			if (!b[24].toString().trim().equals("")
					&& b[24].toString().trim() != null) {
				String str244 = b[24].toString().trim();
				paydatetime = str244.replaceAll(" ", "");
				if ((b[24].toString().trim().contains("/") && pattern4.matcher(
						paydatetime).matches() == true&&pddate.getDateform1(paydatetime))
						|| (b[24].toString().trim().contains("-")&&pddate.getDateform(paydatetime))) {
					if (b[24].toString().trim().contains("/")) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy/MM/dd");
							SimpleDateFormat sdf1 = new SimpleDateFormat(
									"yyyy-MM-dd");
							Date date = sdf.parse(paydatetime);
							paydatetime = sdf1.format(date);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						paydatetime = str244.replaceAll(" ", "");
					}
				} else {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
							+ "交费时间格式不正确");
					wrongContent.add(row);
					continue;
				}
				if (pattern3.matcher(paydatetime).matches() == true) {
					String lastdate = "20" + paydatetime;
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					Date date;
					try {
						date = sdf1.parse(lastdate);
						paydatetime = sdf1.format(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (pattern5.matcher(paydatetime).matches() == false) {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
							+ "交费时间格式不正确!!");
					wrongContent.add(row);
					continue;
				}
			} else {
				paydatetime = b[24].toString().trim();
			}

			DecimalFormat dmat = new DecimalFormat("0.0000");
			String danjia1 = b[14].toString().trim();
			if ("".equals(dianfei) || dianfei == null)
				dianfei = "1";
			if ("".equals(danjia1) || danjia1 == null)
				danjia1 = "1";
			danjia1 = dmat.format(Double.parseDouble(danjia1));
			String danjia2 = dmat.format(Double.parseDouble(dianfei));
			if (!danjia1.equals(danjia2)) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add("单价和电表单价不一致!系统单价为：" + danjia2);
				wrongContent.add(row);
				continue;
				// }
			}

			// 比较实际用电量
			if (b[11].toString().trim() != null
					&& !b[11].toString().trim().equals("")) {
				String lastdegrees2 = b[6].toString().trim();
				String thisdegrees2 = b[7].toString().trim();
				String actualdegrees2 = b[11].toString().trim();


				if (lastdegrees2.equals("") || lastdegrees2 == null)
					lastdegrees2 = "0";
				if (thisdegrees2.equals("") || thisdegrees2 == null)
					thisdegrees2 = "0";
				if (actualdegrees2.equals("") || actualdegrees2 == null)
					actualdegrees2 = "0";

				Double lastdegrees = Double.parseDouble(lastdegrees2);
				Double thisdegrees1 = Double.parseDouble(thisdegrees2);
				Double actualdegrees = Double.parseDouble(actualdegrees2);
				
				Double beilv = Format.str_d(beilv1);
				if("".equals(beilv)||"null".equals(beilv)||beilv==null||"0".equals(beilv)||beilv==0){
					 beilv = 1.00;
				}

				if ("".equals(edhdl) || edhdl == null)
					edhdl = "0";

				Double danjia = Double.parseDouble(dianfei);

				Double jsdegree = 0.0;				
				jsdegree = (thisdegrees1 - lastdegrees)*beilv;
				DecimalFormat price = new DecimalFormat("0.0");
				String jsdeg = price.format(jsdegree);
				String actual = price.format(actualdegrees);
				if (actualdegrees > jsdegree + 1
						|| actualdegrees < jsdegree - 1) {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "电表"
							+ dbid1 + "的实际用电量与计算的结果不一致!!系统算出结果为：" + jsdeg);
					wrongContent.add(row);
					continue;
				}
			}
			
//			if(Double.parseDouble(lastyue)>200){
//				for (int j = 0; j < b.length; j++) {
//					row.add(b[j].toString().trim());
//				}
//				row.add(b[0].toString() + b[2].toString()
//						+ "上期余额大于200，不能申请预支!" );
//				wrongContent.add(row);
//				continue;
//			}

			String pjlx = "";
			if (!b[17].toString().trim().equals("")
					&& b[17].toString().trim() != null) {
			
					if (b[17].toString().trim().equals("发票")) {
						pjlx = "pjlx03";
					} else if (b[17].toString().trim().equals("收据")) {
						pjlx = "pjlx05";
					}else if (b[17].toString().trim().equals("增值税发票")) {
						pjlx = "pjlx06";
					}else{
						for (int j = 0; j < b.length; j++) {
							row.add(b[j].toString().trim());
						}
						row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
								+ "票据类型有误!!");
						wrongContent.add(row);
						continue;
					}
			} else {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "票据类型为空!!");
				wrongContent.add(row);
				continue;
			}
			
			String feetype = "";
			if (!b[22].toString().trim().equals("")
					&& b[22].toString().trim() != null) {
				if(b[22].toString().trim().equals("电费")) {
					feetype = "fylx01";
				}else if (b[22].toString().trim().equals("租赁费")) {
					feetype = "fylx02";
				}else if (b[22].toString().trim().equals("水费")) {
					feetype = "fylx03";
				}else if (b[22].toString().trim().equals("维护费")) {
					feetype = "fylx04";
				}else if (b[22].toString().trim().equals("规费")) {
					feetype = "fylx05";
				}else{
						for (int j = 0; j < b.length; j++) {
							row.add(b[j].toString().trim());
						}
						row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
								+ "费用类型有误!!");
						wrongContent.add(row);
						continue;
					}
			} else {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "电表" + dbid1
						+ "费用类型为空!!");
				wrongContent.add(row);
				continue;
			}
			// 判断导入的数据是否重复
			DecimalFormat df1 = new DecimalFormat("0.00");
			String lastdegrees2 = df1.format(Double.parseDouble(b[6].toString()
					.trim().equals("") ? "0" : b[6].toString().trim()));
			String thisdegrees2 = df1.format(Double.parseDouble(b[7].toString()
					.trim().equals("") ? "0" : b[7].toString().trim()));
			String sql = "SELECT 'A'FROM DIANBIAO DB,YUFUFEIVIEW T  " +
					"WHERE DB.DBID=T.PREPAYMENT_AMMETERID AND DB.DBID =? AND T.STARTDEGREE =? AND T.STOPDEGREE =? " +
					"AND TO_CHAR(T.STARTDATE,'yyyy-mm-dd') =? AND TO_CHAR(T.ENDDATE,'yyyy-mm-dd') =? AND TO_CHAR(T.ACCOUNTMONTH,'yyyy-mm') =?";
			System.out.println("判断导入的数据是否重复:" + sql);
			//DataBase db1 = new DataBase();
			ArrayList<String> listzd = new ArrayList<String>();
			rs = null;
			try {
				Properties pro = new Properties();
				pro.setProperty("1", dbid1);
				pro.setProperty("2", lastdegrees2);
				pro.setProperty("3", thisdegrees2);
				pro.setProperty("4", lastdatetime);
				pro.setProperty("5", thisdatetime);
				pro.setProperty("6", accountmonth);
				rs = db.queryAll002(sql, pro);
				if (rs.next()) {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add("不能重复导入!");
					wrongContent.add(row);
					continue;
				}
				closeDb();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
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

			long uuid1 = System.currentTimeMillis();
			String uuid2 = Integer.toString((int) (100 + Math.random()
					* (999 - 100 + 1)));
			String uuid = uuid2 + Long.toString(uuid1).substring(3);

			// 分摊电费
			// 获取电表分摊比例
			String shuoshuzhuanye = "";
			String dbili = "", dbili1 = "", dbili2 = "", dbili3 = "", dbili4 = "", dbili5 = "", dbili6 = "";
			String actualpay1 = "", actualpay2 = "", actualpay3 = "", actualpay4 = "", actualpay5 = "", actualpay6 = "";
			double wucha = 0.0000001;
			DecimalFormat df = new DecimalFormat("0.00");
			dbili1 = zy1;
			dbili2 = zy2;
			dbili3 = zy3;
			dbili4 = zy4;
			dbili5 = zy5;
			dbili6 = zy6;
			if (dbili1 == null || dbili1 == "") {
				dbili1 = "0.00";
			}
			if (dbili2 == null || dbili2 == "") {
				dbili2 = "0.00";
			}
			if (dbili3 == null || dbili3 == "") {
				dbili3 = "0.00";
			}
			if (dbili4 == null || dbili4 == "") {
				dbili4 = "0.00";
			}
			if (dbili5 == null || dbili5 == "") {
				dbili5 = "0.00";
			}
			if (dbili6 == null || dbili6 == "") {
				dbili6 = "0.00";
			}

			// 分摊电费(备注：加0.0000001是为解决DecimalFormat四舍五入碰到小数位处于0.005向下舍的情况)
			if (!"".equals(actualpay) || actualpay != null || actualpay != "0"
					|| !"o".equals(actualpay)) {
				// 生产
				actualpay1 = df.format((new Double(dbili1).doubleValue() / 100)
						* new Double(actualpay).doubleValue() + wucha);
				// 营业
				actualpay2 = df.format((new Double(dbili2).doubleValue() / 100)
						* new Double(actualpay).doubleValue() + wucha);
				// 办公
				actualpay3 = df.format((new Double(dbili3).doubleValue() / 100)
						* new Double(actualpay).doubleValue() + wucha);
				// 信息化
				actualpay4 = df.format((new Double(dbili4).doubleValue() / 100)
						* new Double(actualpay).doubleValue() + wucha);
				// 建设投资
				actualpay5 = df.format((new Double(dbili5).doubleValue() / 100)
						* new Double(actualpay).doubleValue() + wucha);
				// 代垫电费
				actualpay6 = df.format((new Double(dbili6).doubleValue() / 100)
						* new Double(actualpay).doubleValue() + wucha);
/*				System.out.println("代垫电费："
						+ actualpay6
						+ " 生产："
						+ actualpay1
						+ "aaaaaa"
						+ actualpay2
						+ "aaaaaa"
						+ actualpay3
						+ "aaaaaa"
						+ ((new Double(dbili1).doubleValue() / 100)
								* new Double(actualpay).doubleValue() + wucha)
						+ "aaaaaa"
						+ ((new Double(dbili2).doubleValue() / 100)
								* new Double(actualpay).doubleValue() + wucha)
						+ "aaaaaa"
						+ ((new Double(dbili3).doubleValue() / 100)
								* new Double(actualpay).doubleValue() + wucha));*/
			} else {
				actualpay1 = "0.00";
				actualpay2 = "0.00";
				actualpay3 = "0.00";
				actualpay4 = "0.00";
				actualpay5 = "0.00";
				actualpay6 = "0.00";
			}

			formBean= new PrepaymentFormBean();

			formBean.setStationid(zdid);
			
			formBean.setPrepaymentammeterid(dbid1);
			
			formBean.setFeetypeid(feetype);
			
			formBean.setMoney(actualpay);
			
			formBean.setStartdegree(df.format(Double.parseDouble(b[6].toString()
					.trim().equals("") ? "0" : b[6].toString().trim())));
			
			formBean.setStopdegree(df.format(Double.parseDouble(b[7].toString()
					.trim().equals("") ? "0" : b[7].toString().trim())));
			
			formBean.setStartdate(lastdatetime);
			
			formBean.setEnddate(thisdatetime);
			
			formBean.setDianfei(df.format(Double.parseDouble(b[14].toString()
					.trim().equals("") ? "0" : b[14].toString().trim())));
			
			formBean.setSumdegree(df.format(Double.parseDouble(b[10].toString()
					.trim().equals("") ? "0" : b[10].toString().trim())));

			formBean.setNotetypeid(pjlx);
			formBean.setNoteno(b[18].toString().trim());
			formBean.setNotetime(notetime);
			
			formBean.setPayoperator(b[23].toString().trim());
			formBean.setPaydatetime(paydatetime);
			formBean.setKptime(kptime);
			
			formBean.setAccountmonth(accountmonth);
			
			formBean.setMemo(b[25].toString().trim());
			
			formBean.setStartmonth(startmonth);
			formBean.setEndmonth(endmonth);

			formBean.setEntrypensonnel(accountname);
			formBean.setEntrytime(mat.format(new Date()));
			
			double pjje = Double.parseDouble(str21);
			formBean.setPjje(pjje);
			
			String blhdl = df.format(Double.parseDouble(b[11].toString().trim()));
			formBean.setActualdegree(blhdl);
			String bzw = "0";

			// 分摊电费
			// 生产
			formBean.setNetworkdf(actualpay1);
			// 营业
			formBean.setMarketdf(actualpay2);
			// 办公
			formBean.setAdministrativedf(actualpay3);
			// 信息化
			formBean.setInformatizationdf(actualpay4);
			// 建设投资
			formBean.setBuilddf(actualpay5);
			// 代垫电费
			formBean.setDddf(actualpay6);
			PrepaymentBean bean = new PrepaymentBean();
//-----------------------------------------------------------------------------------------------------------		
			 if(Format.str_d(beilv1)==0){
				 beilv1 = "1";
			 }
			 formBean.setBeilv(beilv1);
		     String dbydl = String.valueOf((Format.str_d(formBean.getStopdegree())-Format.str_d(formBean.getStartdegree()))*Format.str_d(beilv1));//电表用电量 = (本次电表读数-上次电表读数)*倍率
		     formBean.setDbydl(dbydl);
		     ElectricTool elecToo = new ElectricTool();
			 String msg = "";
			 String zdshzt = ""; //自动审核状态标志:0不通过 ,1通过
			 String zdshms = "";//自动审核不通过的描述信息
			 
			 String pjje1 = String.valueOf(pjje);
			 String[] d1 = elecToo.checkElectric2(pjje1);//票据金额是否为空
			 String[] d2 = elecToo.checkDayDegree(dbid1, thisdatetime, lastdatetime, actualdegree);//本次交费日均耗电量大于最后一次交费的日均耗电量20%
		     String[] d3 = elecToo.checkBcdl(blhdl, thisdatetime, lastdatetime, null,dbid1, null, "0");//本次电量上下浮动超过站点额定耗电量计算值的20%
		     String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, thisdatetime, lastdatetime, shicode, property, zlfh, jlfh, edhdl, scb, dbid1,blhdl,qsdbdl,stationtype);//2014-10-22超省标比例,合并周期,标准值
		     String[] d4 = elecToo.checkBcdl2(cbbl[0]);//本次电量上下浮动超过全省定标的10%
		     String[] d5 = elecToo.checkExceptAndHigh(dbid1, actualpay, actualdegree, thisdatetime, lastdatetime, String.valueOf(Format.str_d(cbbl[0])/100));//是否异常及高额电费
		     String[] d6 = elecToo.checckSite(dbid1);//是否是1.2万个点

		    if("1".equals(d1[0])&&"1".equals(d2[0])&&"1".equals(d3[0])&&"1".equals(d4[0])){
		    	zdshzt = "1";
		    }else{
		    	zdshzt = "0";
		    }
		     	
		     String qxzr = "1";//区县主任审核状态 0：需要审核,1:不需要审核；审核标志    0：未审核，1：审核通过，2审核不通过
		     String city = "1";//市级审核状态；审核标志
		     String cityzr = "1";//市级主任审核状态；审核标志	

		     if("1".equals(d5[0])){
		     	qxzr = "0";
		     	city = "0";
		     	cityzr = "0";
		     }else if("0".equals(d5[0])){
		     	//qxzr = "1";
		     	if("1".equals(d6[0])){
		     		city = "0";
		     		cityzr = "0";
		     	}else if("0".equals(d6[0])){
		     		//cityzr = "1";
		     		if("0".equals(d3[0]) || "0".equals(d4[0])){
		     			city = "0";
		     		}
		     	}
		     }
		     	
		     zdshms = d1[1] + d2[1] + d3[1] + d4[1] + d5[1] + d6[1];
		     
		     if(lastdf==null||lastdf.equals("null")){
		    	 lastdf="";
		     }
		     if(lastdl==null||lastdl.equals("null")){
		    	 lastdl="";
		     }
		     if(lastlch==null||lastlch.equals("null")){
		    	 lastlch="";
		     }
		     if(lastyue==null||lastyue.equals("null")){
		    	 lastyue="";
		     }
		     System.out.println("上次费用信息lastyue"+lastyue+"  lastdf"+lastdf+"  lastdl"+lastdl+"  lastyue"+lastyue);

		     formBean.setLastdf(lastdf);
		     formBean.setLastdl(lastdl);
		     formBean.setLastlch(lastlch);
		     formBean.setLastyue(lastyue);
		     formBean.setJszq(Format.str_d(d3[4]));
	         String qsdbdlcbbl = cbbl[0];//全省定标电量超标比例
	         String hbzq = cbbl[1];//合并周期
	         String bzz = cbbl[2];//标准值
	         formBean.setZlfh(zlfh);
	         formBean.setJlfh(jlfh);
	         formBean.setPropertycode(property);
	         formBean.setGdfscode(gdfs);
	         formBean.setDfzflxcode(dfzflx);
	         formBean.setStationtypecode(stationtype);
//	-------------------------------------------------------------------------------------------------
			 msg = bean.addPrepayment(formBean,bzw,zdshzt,zdshms,qxzr,city,cityzr,d3[5],qsdbdl,d3[3],qsdbdlcbbl,hbzq,bzz,scb);		
			
			if (msg.equals("添加预付费信息成功！")) {
				
			} else {
				System.out.println(b[9] + "添加预付费信息失败");
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[1].toString() + b[2].toString() + dbid1 + "添加预付费信息出错!");
				wrongContent.add(row);
				continue;
			}
		}
		cform.setCg((content.size() - 1) - wrongContent.size());
		cform.setBcg(wrongContent.size());
		cform.setZg((content.size() - 1));
		return wrongContent;

	}

	@SuppressWarnings("unchecked")
	private ArrayList getDLID(String uuid) {
		String sql = "select ammeterdegreeid from AMMETERDEGREES where uuid='"
				+ uuid + "'";
		// System.out.println("--电表amuuid返回id--"+sql);
		DataBase db = new DataBase();
		ArrayList list = new ArrayList();
		rs = null;
		try {
			rs = db.queryAll(sql);

			while (rs.next()) {
				// System.out.println(rs.getString("ammeterdegreeid"));
				list.add(rs.getString("ammeterdegreeid"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			db.close();
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	/**
	 * 判断时间格式是否正确，符合长度（10）、中间是否是（-）隔开、是否符合时间规则（时间天数是否正确）
	 * @param time：需要验证的时间日期
	 * @return boolean:如果正确返回一个false,错误返回一个true
	 */
/*	public boolean getPdsjgs(String time){
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
			+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
			+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
			+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
			+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
			+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		
		if ((time != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(time);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(time);
				return !match.matches();
			}else {
				return true;
			}
		}
		return true;
	}*/
}
