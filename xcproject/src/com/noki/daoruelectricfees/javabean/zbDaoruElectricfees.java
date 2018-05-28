package com.noki.daoruelectricfees.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.noki.ammeterdegree.javabean.AmmeterDegreeBean;
import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.ammeterdegree.javabean.Pdtimeform;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesBean;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.jizhan.daoru.CountForm;
import com.noki.sysconfig.javabean.AutoAuditBean;

public class zbDaoruElectricfees {
	String sqlnote = "";
	Vector wrongContent = new Vector();
	public DataBase db;
	public zbDaoruElectricfees() {
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

	// 通过自报电用户号批量导入电量电费
	public synchronized Vector getWrong(Vector content, CountForm cform,
			String accountname, String loginId, String biaoji) {
		String lastdatetime = "";
		Date date1 = null, date2 = null;
		String thisdatetime = "", startmonth = "", endmonth = "", inputdatetime = "", accountmonth = "", notetime = "", kptime = "", paydatetime = "";
		String dbid1 = "";
		String blhdl = "";
		String actualpay = "";

		SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Object[] a = content.toArray();
		AmmeterDegreeFormBean formBean = null;
		ElectricFeesFormBean feesformBean = null;
		DaoruDianLiang bean = new DaoruDianLiang();
		DaoruDianFei fees = new DaoruDianFei();
		AutoAuditBean abean = new AutoAuditBean();
		Pdtimeform pddate = new Pdtimeform();
		ArrayList fylist = new ArrayList();
		fylist = abean.getPageData(1, "");
		for (int i = 1; i < a.length; i++) {
			Vector<String> row = new Vector<String>();
			Object[] b = ((Vector) a[i]).toArray();

			String dbzbdyhh = "", did = "", id = "", dianfei = "", beilv1 = "1", linelosstype1 = "", linelossvalue1 = "", edhdl = "0";
			String zy1 = "", zy2 = "", zy3 = "", zy4 = "", zy5 = "", ladatetime = "", cssytime = "";
			//System.out.println("_-" + dbid1 + "--" + b[8].toString().trim()+ "--");
			dbzbdyhh = b[3].toString().trim();
			blhdl = b[8].toString().trim();
			actualpay = b[14].toString().trim();
			// 查询分摊
			String sql1 = "SELECT D.id, Z.DIANFEI, D.BEILV, D.LINELOSSTYPE, D.LINELOSSVALUE,Z.EDHDL,"
					+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx01' THEN S.DBILI else 0 END))AS ZY1,"
					+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx02' THEN S.DBILI else 0 END))AS ZY2,"
					+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx03' THEN S.DBILI else 0 END))AS ZY3,"
					+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx04' THEN S.DBILI else 0 END))AS ZY4,"
					+ "MAX((CASE WHEN S.SHUOSHUZHUANYE='zylx05' THEN S.DBILI else 0 END))AS ZY5,  "
					+ "d.dbid,TO_CHAR(d.CSSYTIME,'yyyy-mm-dd') CSSYTIME FROM ZHANDIAN Z, DIANBIAO D,SBGL S  WHERE Z.ID = D.ZDID   AND D.DBYT = 'dbyt01' AND D.DBQYZT='1' AND Z.QYZT='1' "
					+ "  AND S.DIANBIAOID(+)=D.DBID AND D.DBZBDYHH=? AND EXISTS(SELECT 'A' FROM PER_AREA P WHERE P.AGCODE=Z.XIAOQU AND P.ACCOUNTID = ?)"
					+ " GROUP BY D.ID,Z.DIANFEI, D.BEILV, D.LINELOSSTYPE, D.LINELOSSVALUE,Z.EDHDL,d.dbid,d.CSSYTIME";
			ResultSet rs = null;
			DataBase db = new DataBase();
			try {
				// 更改中2012-12-3
				Properties pro = new Properties();
				pro.setProperty("1", dbzbdyhh);
				pro.setProperty("2", loginId);
				db.connectDb();
				//System.out.println("=======>>>"+pro.getProperty("1"));
				//System.out.println("=======>>>"+pro.getProperty("2"));
				System.out.println("通过自报电用户号批量导入电量电费,查询自 报电用户号对应分摊比例:" + sql1);
				rs = db.queryAll001(sql1, pro);
				while (rs.next()) {
					id = rs.getString(1);
					dianfei = rs.getString(2);
					beilv1 = rs.getString(3);
					linelosstype1 = rs.getString(4);
					linelossvalue1 = rs.getString(5);
					edhdl = rs.getString(6);
					zy1 = rs.getString(7);
					zy2 = rs.getString(8);
					zy3 = rs.getString(9);
					zy4 = rs.getString(10);
					zy5 = rs.getString(11);
					did = rs.getString(12);
					cssytime = rs.getString(13);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try{
					if(rs!=null){
					rs.close();
				}
				}catch(SQLException e){
					e.printStackTrace();
					}finally{
						try{
							if(db!=null){
								db.close();
							}
						}catch(DbException e){
							e.printStackTrace();
							}
					}
			}
			if (biaoji.equals("2")) {
				// 查询出本次抄表时间 结束月份
				String sql2 = "SELECT T.*,ROWNUM FROM(SELECT TO_CHAR(AM.THISDATETIME,'yyyy-mm-dd') THISDATETIME,TO_CHAR(AM.ENDMONTH,'yyyy-mm') ENDMONTH FROM ZHANDIAN Z,DIANBIAO D,AMMETERDEGREES AM "
						+ "WHERE Z.ID=D.ZDID AND D.DBID=AM.AMMETERID_FK AND AM.MANUALAUDITSTATUS='1'  "
						+ " AND D.DBZBDYHH=? AND EXISTS(SELECT 'A' FROM PER_AREA P WHERE"
						+ " P.AGCODE=Z.XIAOQU AND P.ACCOUNTID = ?) ORDER BY AM.ENDMONTH desc)t where rownum=1";
				try {
					Properties pro = new Properties();
					pro.setProperty("1", dbzbdyhh);
					pro.setProperty("2", loginId);
					db.connectDb();
					System.out.println("通过自报电用户号批量导入电量电费,查询出本次抄表时间 结束月份:"
							+ sql2);
					rs = db.queryAll001(sql2, pro);
					while (rs.next()) {
						lastdatetime = rs.getString(1);
						startmonth = rs.getString(2);
						//System.out.println(lastdatetime+"_+_+_+_+_+_+_+_+>>>>>11111111111111111111--"+startmonth);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					try{
						if(rs!=null){
						rs.close();
					}
					}catch(SQLException e){
						e.printStackTrace();
						}finally{
							try{
								if(db!=null){
									db.close();
								}
							}catch(DbException e){
								e.printStackTrace();
								}
						}
				}
				if (lastdatetime == "") {
					lastdatetime = cssytime;
				}

				if (startmonth == "") {
					//System.out.println(lastdatetime+"_+_+_+_+_+_+_+_+>>>>>22222222222222222222---"+startmonth);
					if("".equals(lastdatetime)||lastdatetime==null){
						System.out.println("没有上次抄表时间");
						for (int j = 0; j < b.length; j++) {
							row.add(b[j].toString().trim());
						}
						row.add("未查到自报电用户号" +dbzbdyhh  + "的上次抄表时间：该电表没有交过费用，请到电表管理把电表的'初始使用时间'填上"
								 );
						wrongContent.add(row);
						continue;
					}else{
						startmonth = lastdatetime.substring(0, 7);
					}
					
				}
			}
			if ("".equals(id) || null == id) {
				System.out.println("未查到自报电用户号：" + dbzbdyhh);
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add("未查到自报电用户号："
						+ dbzbdyhh);
				wrongContent.add(row);
				continue;
			}
			String str44 = b[4].toString().trim();
			String str4 = str44.replaceAll(" ", "");

			Pattern pattern = Pattern.compile("[0-9]*|[0-9]*+\\.+[0-9]*");
			Pattern pattern1 = Pattern
					.compile("[0-9]*|[0-9]+\\.[0-9]*|-[0-9]*|-[0-9]+\\.[0-9]*");

			if (pattern.matcher(str4).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add("自报电用户号："
						+ dbzbdyhh + "上次电表读数格式不正确");
				wrongContent.add(row);
				continue;
			}
			if (str4 == "") {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add("自报电用户号："
						+ dbzbdyhh + "上次电表读数为空");
				wrongContent.add(row);
				continue;
			}
			String str55 = b[5].toString().trim();
			String str5 = str55.replaceAll(" ", "");
			if (pattern.matcher(str5).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "自报电用户号："
						+ dbzbdyhh + "本次电表读数格式不正确");
				wrongContent.add(row);
				continue;
			}
			if (str5 == "") {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add("自报电用户号："
						+ dbzbdyhh + "本次电表读数为空");
				wrongContent.add(row);
				continue;
			}
			if (biaoji.equals("1")) {
				if (b[6].toString().trim() == "") {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "上次抄表时间为空");
					wrongContent.add(row);
					continue;
				}
			}
			if (b[7].toString().trim() == "") {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "自报电用户号："
						+ dbzbdyhh + "本次抄表时间为空");
				wrongContent.add(row);
				continue;
			}

			String str99 = b[8].toString().trim();
			String str9 = str99.replaceAll(" ", "");
			//System.out.println("-1-" + str9 + "--"+ pattern.matcher(str9).matches());
			if (pattern.matcher(str9).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				//System.out.println("-2-" + str9 + "--");
				row.add(b[0].toString() + b[2].toString() + "自报电用户号："
						+ dbzbdyhh + "实际用电量格式不正确");
				wrongContent.add(row);
				continue;
			} else if (str9.equals("")) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				//System.out.println("-3-" + str9 + "--");
				row.add(b[0].toString() + b[2].toString() + "自报电用户号："
						+ dbzbdyhh + "实际用电量为空");
				wrongContent.add(row);
				continue;
			}
			if (biaoji.equals("1")) {
				if (b[9].toString().trim().equals("")) {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "起始月份为空");
					wrongContent.add(row);
					continue;
				}
			}
			if (b[10].toString().trim().equals("")) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "自报电用户号："
						+ dbzbdyhh + "结束月份为空");
				wrongContent.add(row);
				continue;
			}

			String str177 = b[14].toString().trim();
			String str17 = str177.replaceAll(" ", "");
			if (pattern.matcher(str17).matches() == false) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "自报电用户号："
						+ dbzbdyhh + "实际电费金额格式不正确");
				wrongContent.add(row);
				continue;
			} else if (str17.equals("")) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "自报电用户号："
						+ dbzbdyhh + "实际电费金额为空");
				wrongContent.add(row);
				continue;
			}
			if (b[15].toString().trim().equals("")) {
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[2].toString() + "自报电用户号："
						+ dbzbdyhh + "报账月份为空");
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
			if (biaoji.equals("1")) {
				String str66 = b[6].toString().trim();
				lastdatetime = str66.replaceAll(" ", "");
				if ((b[6].toString().trim().contains("/") && pattern4.matcher(
						lastdatetime).matches() == true&&pddate.getDateform1(lastdatetime))
						|| (b[6].toString().trim().contains("-")&&isValidDate(lastdatetime)&&pddate.getDateform(lastdatetime))) {
					if (b[6].toString().trim().contains("/")) {
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
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "上次抄表时间为空   或者   格式不正确");
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
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "上次抄表时间格式不正确!!");
					wrongContent.add(row);
					continue;
				}
			}
			String str77 = b[7].toString().trim();
			thisdatetime = str77.replaceAll(" ", "");
			if ((b[7].toString().trim().contains("/") && pattern4.matcher(
					thisdatetime).matches() == true&&pddate.getDateform1(thisdatetime))	
					|| (b[7].toString().trim().contains("-")&&pddate.getDateform(thisdatetime)&&isValidDate(thisdatetime))) {
				if (b[7].toString().trim().contains("/")) {
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
				row.add(b[0].toString() + b[2].toString() + "自报电用户号："
						+ dbzbdyhh + "本次抄表时间为空   或者   格式不正确");
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
				row.add(b[0].toString() + b[2].toString() + "自报电用户号："
						+ dbzbdyhh + "本次抄表时间格式不正确!!");
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
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "上次抄表时间大于本次抄表时间");
					wrongContent.add(row);
					continue;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Pattern pattern2 = Pattern
					.compile("[0-9]{4}-[0-9]{2}|[0-9]{4}/[0-9]{2}|[0-9]{4}-[0-9]{1}|[0-9]{4}/[0-9]{1}");
			if (biaoji.equals("1")) {
				String str100 = b[9].toString().trim();
				startmonth = str100.replaceAll(" ", "");
				if ((pattern2.matcher(startmonth).matches() == true && (b[9]
						.toString().trim().contains("/") && b[9].toString()
						.trim().length() <= 7&&pddate.getMonthform1(startmonth)))
						|| (pattern2.matcher(startmonth).matches() == true && (b[9]
								.toString().trim().contains("-") && b[9]
								.toString().trim().length() <= 7)&&pddate.getMonthform(startmonth))) {
					if (b[9].toString().trim().contains("/")) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy/MM");
							SimpleDateFormat sdf1 = new SimpleDateFormat(
									"yyyy-MM");
							date1 = sdf.parse(startmonth);
							startmonth = sdf1.format(date1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
						if (b[9].toString().trim().length() < 7) {
							try {
								Date end = sdf1.parse(b[9].toString().trim());
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
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "起始年月为空或者格式不正确");
					wrongContent.add(row);
					continue;
				}
			}
			String str111 = b[10].toString().trim();
			endmonth = str111.replaceAll(" ", "");
			if ((pattern2.matcher(endmonth).matches() == true && (b[10]
					.toString().trim().contains("/") && b[10].toString().trim()
					.length() <= 7)&&pddate.getMonthform1(endmonth))
					|| (pattern2.matcher(endmonth).matches() == true && (b[10]
							.toString().trim().contains("-") && b[10]
							.toString().trim().length() <= 7)&&pddate.getMonthform(endmonth))) {
				if (b[10].toString().trim().contains("/")) {
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
					if (b[10].toString().trim().length() < 7) {
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
						row.add(b[0].toString() + b[2].toString() + "自报电用户号："
								+ dbzbdyhh + "起始月份大于结束月份");
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
				row.add(b[0].toString() + b[2].toString() + "自报电用户号："
						+ dbzbdyhh + "结束年月为空或者格式不正确");
				wrongContent.add(row);
				continue;
			}
			if (!b[12].toString().trim().equals("")
					&& b[12].toString().trim() != null) {
				String str133 = b[12].toString().trim();
				inputdatetime = str133.replaceAll(" ", "");
				if ((b[12].toString().trim().contains("/") && pattern4.matcher(
						inputdatetime).matches() == true&&pddate.getDateform1(inputdatetime))
						|| (b[12].toString().trim().contains("-")&&pddate.getDateform(inputdatetime))) {
					/*if (b[12].toString().trim().length() != 10) {
						for (int j = 0; j < b.length; j++) {
							row.add(b[j].toString().trim());
						}
						row.add(b[0].toString() + b[2].toString() + "自报电用户号："
								+ dbzbdyhh + "抄表时间格式不正确!!");
						wrongContent.add(row);
						continue;
					}*/
					if (b[12].toString().trim().contains("/")) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy/MM/dd");
							SimpleDateFormat sdf1 = new SimpleDateFormat(
									"yyyy-MM-dd");
							Date date = sdf.parse(inputdatetime);
							inputdatetime = sdf1.format(date);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {

						inputdatetime = str133.replaceAll(" ", "");
					}
					
				} else {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "抄表时间格式不正确");
					wrongContent.add(row);
					continue;
				}
				if (pattern3.matcher(inputdatetime).matches() == true) {
					String lastdate = "20" + inputdatetime;
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					Date date;
					try {
						date = sdf1.parse(lastdate);
						inputdatetime = sdf1.format(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (pattern5.matcher(inputdatetime).matches() == false) {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "抄表时间格式不正确!!");
					wrongContent.add(row);
					continue;
				}
			} else {
				inputdatetime = b[12].toString().trim();
			}

			String str188 = b[15].toString().trim();
			accountmonth = str188.replaceAll(" ", "");
			if ((pattern2.matcher(accountmonth).matches() == true && (b[15]
					.toString().trim().contains("/") && b[15].toString().trim()
					.length() <= 7)&&pddate.getMonthform1(accountmonth))
					|| (pattern2.matcher(accountmonth).matches() == true && (b[15]
							.toString().trim().contains("-") && b[15]
							.toString().trim().length() <= 7)&&pddate.getMonthform(accountmonth))) {
				if (b[15].toString().trim().contains("/")) {
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
				row.add(b[0].toString() + b[2].toString() + "自报电用户号："
						+ dbzbdyhh + "报账月份为空或者格式不正确");
				wrongContent.add(row);
				continue;
			}

			if (!b[18].toString().trim().equals("")
					&& b[18].toString().trim() != null) {
				String str211 = b[18].toString().trim();
				notetime = str211.replaceAll(" ", "");
				if ((b[18].toString().trim().contains("/") && pattern4.matcher(
						notetime).matches() == true&&pddate.getDateform1(notetime))
						|| (b[18].toString().trim().contains("-")&&pddate.getDateform(notetime))) {
					if (b[18].toString().trim().contains("/")) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy/MM/dd");
							SimpleDateFormat sdf1 = new SimpleDateFormat(
									"yyyy-MM-dd");
							Date date = sdf.parse(b[18].toString().trim());
							notetime = sdf1.format(date);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else {
						notetime = str211.replaceAll(" ", "");
					}
				} else {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "票据时间格式不正确");
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
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "票据时间格式不正确!!");
					wrongContent.add(row);
					continue;
				}
			} else {
				notetime = b[18].toString().trim();
			}

			if (!b[19].toString().trim().equals("")
					&& b[19].toString().trim() != null) {
				String str222 = b[19].toString().trim();
				kptime = str222.replaceAll(" ", "");
				if ((b[19].toString().trim().contains("/") && pattern4.matcher(
						kptime).matches() == true&&pddate.getDateform1(kptime))
						|| (b[19].toString().trim().contains("-")&&pddate.getDateform(kptime))) {
					if (b[19].toString().trim().contains("/")) {
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
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "开票时间格式不正确");
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
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "开票时间格式不正确!!");
					wrongContent.add(row);
					continue;
				}
			} else {
				kptime = b[19].toString().trim();
			}

			if (!b[21].toString().trim().equals("")
					&& b[21].toString().trim() != null) {
				String str244 = b[21].toString().trim();
				paydatetime = str244.replaceAll(" ", "");
				if ((b[21].toString().trim().contains("/") && pattern4.matcher(
						paydatetime).matches() == true&&pddate.getDateform1(paydatetime))
						|| (b[21].toString().trim().contains("-")&&pddate.getDateform(paydatetime))) {
					if (b[21].toString().trim().contains("/")) {
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
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "交费时间格式不正确");
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
					row.add(b[0].toString() + b[2].toString() + "自报电用户号："
							+ dbzbdyhh + "交费时间格式不正确!!");
					wrongContent.add(row);
					continue;
				}
			} else {
				paydatetime = b[21].toString().trim();
			}

			// 判断导入的数据是否重复
			DecimalFormat df1 = new DecimalFormat("0.00");
			String lastdegrees2 = df1.format(Double.parseDouble(b[4].toString()
					.trim().equals("") ? "0" : b[4].toString().trim()));
			String thisdegrees2 = df1.format(Double.parseDouble(b[5].toString()
					.trim().equals("") ? "0" : b[5].toString().trim()));
			String sql = "SELECT 'A'FROM dbtemp_09_view T, DIANBIAO DB, DFTEMP_011_VIEW E "
					+ "WHERE DB.DBID = T.AMMETERID_FK AND DB.DBZBDYHH =? AND TO_NUMBER(T.LASTDEGREE) =? AND TO_NUMBER(T.THISDEGREE) =? "
					+ "AND TO_CHAR(T.LASTDATETIME,'yyyy-mm-dd') =? AND TO_CHAR(T.THISDATETIME,'yyyy-mm-dd') =? AND T.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm') =?";

			DataBase db1 = new DataBase();
			ArrayList<String> listzd = new ArrayList<String>();
			rs = null;
			try {
				Properties pro = new Properties();
				pro.setProperty("1", dbzbdyhh);
				pro.setProperty("2", lastdegrees2);
				pro.setProperty("3", thisdegrees2);
				pro.setProperty("4", lastdatetime);
				pro.setProperty("5", thisdatetime);
				pro.setProperty("6", accountmonth);
				rs = db1.queryAll002(sql, pro);
				System.out.println("判断导入的数据是否重复:" + sql);
				if (rs.next()) {
					for (int j = 0; j < b.length; j++) {
						row.add(b[j].toString().trim());
					}
				//	System.out.println("1212121不能重复导入21" + rs.toString());
					row.add("不能重复导入!");
					wrongContent.add(row);
					continue;
				}
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
					db1.close();//db改为db1
				} catch (DbException de) {
					de.printStackTrace();
				}
			}
			//System.out.println("thisdatetime" + lastdatetime + "thisdatetime"+ thisdatetime + "startmonth" + startmonth + "endmonth"
					//+ endmonth + "inputdatetime" + inputdatetime
					//+ "accountmonth" + accountmonth + "notetime" + notetime
					//+ "kptime" + kptime + "paydatetime" + paydatetime);
			// String amuuid =
			// UUID.randomUUID().toString().trim().replace("-","");
			long uuid1 = System.currentTimeMillis();
			String uuid2 = Integer.toString((int) (100 + Math.random()
					* (999 - 100 + 1)));
			String amuuid = uuid2 + Long.toString(uuid1).substring(3);

			// 分摊电量、电费
			// 获取电表分摊比例
			// AmmeterDegreeFormBean beanm=new AmmeterDegreeFormBean();
			// ArrayList list = new ArrayList();
			// list = beanm.getStationMhchexkt(dbid1);
			String shuoshuzhuanye = "";
			String dbili = "", dbili1 = "", dbili2 = "", dbili3 = "", dbili4 = "", dbili5 = "";
			String blhdl1 = "", blhdl2 = "", blhdl3 = "", blhdl4 = "", blhdl5 = "";
			String actualpay1 = "", actualpay2 = "", actualpay3 = "", actualpay4 = "", actualpay5 = "";
			double wucha = 0.0000001;
			DecimalFormat df = new DecimalFormat("#.00");
			dbili1 = zy1;
			dbili2 = zy2;
			dbili3 = zy3;
			dbili4 = zy4;
			dbili5 = zy5;
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

			// 分摊电量(备注：加0.0000001是为解决DecimalFormat四舍五入碰到小数位处于0.005向下舍的情况)
			if (!"".equals(blhdl) || blhdl != null || blhdl != "0"
					|| !"o".equals(blhdl)) {
				// 生产
				blhdl1 = df.format((new Double(dbili1).doubleValue() / 100)
						* new Double(blhdl).doubleValue() + wucha);
				// 营业
				blhdl2 = df.format((new Double(dbili2).doubleValue() / 100)
						* new Double(blhdl).doubleValue() + wucha);
				// 办公
				blhdl3 = df.format((new Double(dbili3).doubleValue() / 100)
						* new Double(blhdl).doubleValue() + wucha);
				// 信息化
				blhdl4 = df.format((new Double(dbili4).doubleValue() / 100)
						* new Double(blhdl).doubleValue() + wucha);
				// 建设投资
				blhdl5 = df.format((new Double(dbili5).doubleValue() / 100)
						* new Double(blhdl).doubleValue() + wucha);
			} else {
				blhdl1 = "0.00";
				blhdl2 = "0.00";
				blhdl3 = "0.00";
				blhdl4 = "0.00";
				blhdl5 = "0.00";
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
				//System.out.println("生产："+ actualpay1+ "aaaaaa"+ actualpay2+ "aaaaaa"+ actualpay3+ "aaaaaa"
						//+ ((new Double(dbili1).doubleValue() / 100)
								//* new Double(actualpay).doubleValue() + wucha)
						//+ "aaaaaa"
						//+ ((new Double(dbili2).doubleValue() / 100)
						//		* new Double(actualpay).doubleValue() + wucha)
						//+ "aaaaaa"
						//+ ((new Double(dbili3).doubleValue() / 100)
							//	* new Double(actualpay).doubleValue() + wucha));
			} else {
				actualpay1 = "0.00";
				actualpay2 = "0.00";
				actualpay3 = "0.00";
				actualpay4 = "0.00";
				actualpay5 = "0.00";
			}

			formBean = new AmmeterDegreeFormBean();

			formBean.setAmmeteridFk(did);
			formBean.setLastdegree(df.format(Double.parseDouble(b[4].toString()
					.trim().equals("") ? "0" : b[4].toString().trim())));
			formBean.setThisdegree(df.format(Double.parseDouble(b[5].toString()
					.trim().equals("") ? "0" : b[5].toString().trim())));
			formBean.setThisdatetime(thisdatetime);
			formBean.setLastdatetime(lastdatetime);
			formBean.setStartmonth(startmonth);
			formBean.setBlhdl(df.format(Double.parseDouble(b[8].toString()
					.trim())));
			formBean.setEndmonth(endmonth);
			formBean.setActualdegree(df.format(Double.parseDouble(b[8]
					.toString().trim())));
			formBean.setInputoperator(b[11].toString().trim());
			formBean.setInputdatetime(inputdatetime);
			System.out.println("0--0" + formBean.getActualdegree());
			formBean.setMemo(b[13].toString().trim());
			formBean.setEntrypensonnel(accountname);
			formBean.setEntrytime(mat.format(new Date()));
			formBean.setEdhdl(edhdl);
			formBean.setBeilv(beilv1);
			formBean.setFloatdegree("0");
			// 分摊电量
			// 生产
			formBean.setScdl(blhdl1);
			// 办公
			formBean.setYydl(blhdl2);
			// 营业
			formBean.setBgdl(blhdl3);
			// 信息化
			formBean.setXxhdl(blhdl4);
			// 建设投资
			formBean.setJstzdl(blhdl5);

			String bzw = "0";
			String msg = bean.addDegreeFees(formBean, amuuid, bzw, fylist);
			if (msg.equals("添加电量成功!")) {
				// System.out.println(b[9]+"添加电量成功");
			} else {
				System.out.println(b[3] + "添加电量失败");
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[0].toString() + b[1].toString() + b[2].toString()
						+ dbid1 + "添加电量出错!");

				wrongContent.add(row);
				continue;

			}

			feesformBean = new ElectricFeesFormBean();
			feesformBean.setActualpay(b[14].toString().trim() != null ? b[14]
					.toString().trim() : "0");
			String pjlx = "";
			if (b[16].toString().trim().equals("发票")) {
				pjlx = "pjlx03";
			} else if (b[16].toString().trim().equals("支票")) {
				pjlx = "pjlx02";
			} else if (b[16].toString().trim().equals("电费分割单")) {
				pjlx = "pjlx04";
			} else {
				pjlx = b[16].toString().trim();
			}
			feesformBean.setNotetypeid(pjlx.equals("") ? "pjlx03" : pjlx);
			// System.out.println(req.getParameter("notetypeid"));
			feesformBean.setNoteno(b[17].toString().trim());
			feesformBean.setNotetime(notetime);
			feesformBean.setPayoperator(b[20].toString().trim());
			feesformBean.setPaydatetime(paydatetime);
			feesformBean.setAccountmonth(accountmonth);
			feesformBean.setMemo(b[22].toString().trim());
			feesformBean.setAmmererid(dbid1);
			feesformBean.setKptime(kptime);
			feesformBean.setEntrypensonnel(accountname);
			feesformBean.setEntrytime(mat.format(new Date()));
			feesformBean.setUnitprice(dianfei);
			feesformBean.setFloatpay("0");
			feesformBean.setLastdatetime(ladatetime);
			feesformBean.setDfuuid(amuuid);

			// 分摊电费
			// 生产
			feesformBean.setScdff(actualpay1);
			// 营业
			feesformBean.setYydf(actualpay2);
			// 办公
			feesformBean.setBgdf(actualpay3);
			// 信息化
			feesformBean.setXxhdf(actualpay4);
			// 建设投资
			feesformBean.setJstzdl(actualpay5);

			// System.out.println(start+"/*****"+end);
			
			 ElectricFeesBean beana = new ElectricFeesBean();
		      List ammeterdegreeid=new ArrayList();
		      ammeterdegreeid =beana.getAmUuid(amuuid);
		     // System.out.println("主键生成查询id:"+ammeterdegreeid); 
		      int flag=feesformBean.getFlag(ammeterdegreeid.get(0).toString());
		     // System.out.println("flag------>>>:"+flag);
		      feesformBean.setFlag(flag);
		      //取电量表中自动审核标志
		       String autoauditstatus =feesformBean.getZdshbz(ammeterdegreeid.get(0).toString());
		       feesformBean.setAutoauditstatus(autoauditstatus);
		      // System.out.println("autoauditstatus----->>>"+autoauditstatus);
			msg = fees.addFeesDegree(feesformBean, startmonth, endmonth, this
					.getDLID(amuuid), bzw, fylist);
			if (msg.equals("添加电费成功!")) {
				// System.out.println(b[9]+"添加电费成功");
			} else {
				System.out.println(b[8] + "添加电费失败");
				for (int j = 0; j < b.length; j++) {
					row.add(b[j].toString().trim());
				}
				row.add(b[1].toString() + b[2].toString() + dbid1 + "添加电费出错!");
				wrongContent.add(row);
				continue;

			}
		}
		// System.out.println(content.size()+"  "+wrongContent.toString()+" "+row.size());
		cform.setCg((content.size() - 1) - wrongContent.size());
		cform.setBcg(wrongContent.size());
		cform.setZg((content.size() - 1));
		return wrongContent;

	}

	@SuppressWarnings("unchecked")
	private ArrayList getDLID(String amuuid) {
		String sql = "select ammeterdegreeid from AMMETERDEGREES where uuid='"
				+ amuuid + "'";
		System.out.println("--电表amuuid返回id--" + sql);
		DataBase db11 = new DataBase();
		ResultSet rss = null;
		ArrayList list = new ArrayList();
		rss = null;
		try {
			rss = db11.queryAll(sql);

			while (rss.next()) {
				// System.out.println(rs.getString("ammeterdegreeid"));
				list.add(rss.getString("ammeterdegreeid"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				if(rss!=null){
				rss.close();
			}
			}catch(SQLException e){
				e.printStackTrace();
				}finally{
					try{
						if(db11!=null){
							db11.close();
						}
					}catch(DbException e){
						e.printStackTrace();
						}
				}
		}
		return list;

	}

	/*
	 * 
	 * 电费批量导入
	 */
	public synchronized Vector getWrongFee(Vector content, String idStr) {
		StringBuffer sql = new StringBuffer();
		int m = 0, i = 0;
		ArrayList fylistn = new ArrayList();
		AmmeterDegreeBean abean = new AmmeterDegreeBean();
		Vector row = new Vector();
		System.out.println("电费" + content);
		return row;
		/*
		 * catch (Exception e) { e.printStackTrace(); row.add(e.toString());
		 * wrongContent.add(row); } }//m条数>1 }
		 * System.out.println("wrongContent:"+wrongContent); return
		 * wrongContent;
		 */
	}

	// 导入判断日期格式
	public static boolean isValidDate(String sDate) {
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}
}
