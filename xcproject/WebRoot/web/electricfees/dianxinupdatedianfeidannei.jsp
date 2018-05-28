dianxinupdatedianfeidannei.jsp<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean"%>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="com.noki.database.DbException"%>
<%@ page import="com.noki.util.Query"%>
<%
	//如果电量管理的合理，在添加电费单的的时候  电量，本次抄表时间会自动带出
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
	String accountid = account.getAccountName();

	String stationname = request.getParameter("stationname");
	String id = request.getParameter("id");
	String ftje= request.getParameter("ftje");
	HttpSession session1= request.getSession();
	session1.setAttribute("id", id);
	session1.setAttribute("ftje", ftje);
	String dbid = "", zdcode = "";
	StringBuffer sql = new StringBuffer();
	StringBuffer sql2 = new StringBuffer();
	sql.append("select id from zhandian where jzname="+stationname+"");	
	DataBase db = new DataBase();
	ResultSet rs = null;	
	if (sql.length() > 0) {
			try {
				db.connectDb();
				System.out.println(sql.toString());
				rs = db.queryAll(sql.toString());
				while (rs.next()) {
					zdcode = rs.getString("id");
					sql2.append("select dbid from dianbiao where zdid ="+zdcode+"");
				}
			} catch (Exception de) {
				de.printStackTrace();
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
	}
	
	if (sql2.length() > 0) {
			try {
				db.connectDb();
				System.out.println(sql2.toString());
				rs = db.queryAll(sql2.toString());
				while (rs.next()) {
					dbid = rs.getString("dbid");
				}
			} catch (Exception de) {
				de.printStackTrace();
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
	}
	if (dbid != null) {
		// 站点信息
		AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
		/*
		 获取站点和电表的信息
		
		 **/
		bean = bean.getAmmeterDegreeAllInfo1(dbid);//查询 站点 电表  站点电费信息表
		String diqu = bean.getDiqu();
		String stationtype = bean.getStationtype();
		String gsf = bean.getYcq(); // 原产权
		String jzname=bean.getJzname();//站点名称
		String jzcode=bean.getJzcode();//站点编码
		String wlzdbm=bean.getWlzdbm();//物理站点编码
		String zfdh="";//支付单号
		String gdfname=bean.getGdfname();//供电方名称
		String dbbm=bean.getDbbm();//电表户号
		String zzlx=bean.getZzlx();//站址类型
		String jingdu = bean.getLongitude();
		String weidu =bean.getLatitude();
		String gdfs=bean.getGdfs();
		
		
		
		
		String jztype = bean.getJztype();
		String dfzflx = bean.getDfzflx();
		String beilv = bean.getMultiplyingpower();
		String linelosstype = bean.getLinelosstype();
		String linelossvalue = bean.getLinelossvalue();
		String fkzq = bean.getFkzq();
		String dianfei = bean.getDianfei();
		String ammeterid = bean.getAmmeterid();

		String edhdl = bean.getEdhdl();
		String itemvalue = bean.getItemvalue();
		String shifou = bean.getShifou();
		String qsdbdl = bean.getQsdbdl();
		System.out.println(qsdbdl + "   fffff");
		System.out.println("额定耗电量" + edhdl + "超额" + itemvalue + "标志位"
				+ shifou + " mm");
		AmmeterDegreeFormBean beanad = new AmmeterDegreeFormBean();
		AmmeterDegreeFormBean beanbc = new AmmeterDegreeFormBean();

		if (ammeterid != null) {
			/*
			     获取上次电表读数
			 **/
			beanad = beanad.getLastAmmeterDegree1(ammeterid);

			/*
			      获取管理电表的本次电表读数 
			 **/
			beanbc = beanbc.getLastAmmeterDegree2(zdcode);
			//本次电表读数
			String bcdbds = beanbc.getThisdegree();
			System.out.println("本次电表读数： " + bcdbds);
			bcdbds = bcdbds != null ? bcdbds : "0";
			//本次抄表时间
			String bcdbsj = beanbc.getThisdatetime();
			bcdbsj = bcdbsj != null ? bcdbsj : "";
			System.out.println("本次抄表时间 ：" + bcdbsj);
			//上次电表读数
			String scdbds = beanad.getLastdegree();
			System.out.println("上次电表读数：" + scdbds);
			scdbds = scdbds != null ? scdbds : "0";
			//实际电费
			String actualpay = beanad.getActualpay();
			actualpay = actualpay != null ? actualpay : "0.00";

			if ("".equals(actualpay) || "null".equals(actualpay)) {
				actualpay = "0";
			}

			double dd = beanad.getPjje();
			if (dd == 0 || dd == 0.00) {
				dd = Double.parseDouble(actualpay);

			}

			//上次用电量   
			String sjydl = beanad.getYdl();
			if (sjydl == null || "".equals(sjydl))
				sjydl = "0";

			DecimalFormat mat1 = new DecimalFormat("0.00");
			sjydl = mat1.format(Double.parseDouble(sjydl));

			//上次电费
			String scdf = beanad.getActualpay();
			if (scdf == null || "".equals(scdf))
				scdf = "0";

			DecimalFormat mat = new DecimalFormat("0.00");
			scdf = mat.format(Double.parseDouble(scdf));

			//上次录入时间
			String entime = beanad.getEntrytime();
			entime = entime != null ? entime : "";
			//直流负荷
			String zlfh = bean.getZlfh();
			if (zlfh == null || "".equals(zlfh))
				zlfh = "0";
			DecimalFormat mat2 = new DecimalFormat("0.00");
			zlfh = mat2.format(Double.parseDouble(zlfh));
			//交流负荷
			String jlfh = bean.getJlfh();
			String sdbdl = bean.getQsdbdl();
			if (null == sdbdl) {
				sdbdl = "";
			}
			if (jlfh == null || "".equals(jlfh))
				jlfh = "0";
			DecimalFormat mat3 = new DecimalFormat("0.00");
			jlfh = mat3.format(Double.parseDouble(jlfh));
			//pue
			String pue = beanad.getPue();
			if (pue == null || "".equals(pue))
				pue = "0";
			DecimalFormat mat4 = new DecimalFormat("0.00");
			pue = mat4.format(Double.parseDouble(pue));
			//上次抄表时间
			String lastadtime = beanad.getLastdatetime();
			lastadtime = lastadtime != null ? lastadtime : "";
			System.out.println("上次抄表时间： " + lastadtime);
			//日期转换
			if (!lastadtime.equals("")) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd");
				String str = lastadtime;
				Date dt = sdf.parse(str);
				Calendar rightNow = Calendar.getInstance();
				rightNow.setTime(dt);
				//rightNow.add(Calendar.YEAR,-1);//日期减1年
				//rightNow.add(Calendar.MONTH,3);//日期加3个月
				rightNow.add(Calendar.DAY_OF_YEAR, 1);//日期加1天
				Date dt1 = rightNow.getTime();
				lastadtime = sdf.format(dt1);
			}
			if (scdbds.equals("")) {
				scdbds = bean.getCsds();
				scdbds = scdbds != null ? scdbds : "";
				lastadtime = bean.getCssytime();
				lastadtime = lastadtime != null ? lastadtime : "";

			}
			AmmeterDegreeFormBean beanm = new AmmeterDegreeFormBean();
			ArrayList listt = new ArrayList();

			/*
			               获取六条分摊
			 **/
			listt = beanm.getStationMhchexkt(ammeterid);

			String shuoshuzhuanye = "";
			String dbili = "", dbili1 = "", dbili2 = "", dbili3 = "", dbili4 = "", dbili5 = "", dbili6 = "", zh = "0";
			double hj = 0.0;
			//   String xjbl="",xjbl1="",xjbl2="",xjbl3="",xjbl4="",xjbl5="",xjbl6="";

			if (listt != null) {
				int fycount = ((Integer) listt.get(0)).intValue();
				for (int k = fycount; k < listt.size() - 1; k += fycount) {
					shuoshuzhuanye = (String) listt.get(k
							+ listt.indexOf("SHUOSHUZHUANYE"));
					shuoshuzhuanye = shuoshuzhuanye != null ? shuoshuzhuanye
							: "";
					dbili = (String) listt.get(k
							+ listt.indexOf("DBILI"));
					dbili = dbili != null ? dbili : "0";
					if (shuoshuzhuanye.equals("zylx01")) {
						dbili1 = dbili;

					}
					if (shuoshuzhuanye.equals("zylx02")) {
						dbili2 = dbili;

					}
					if (shuoshuzhuanye.equals("zylx03")) {

						dbili3 = dbili;
					}
					if (shuoshuzhuanye.equals("zylx04")) {
						dbili4 = dbili;

					}
					if (shuoshuzhuanye.equals("zylx05")) {
						dbili5 = dbili;

					}
					if (shuoshuzhuanye.equals("zylx06")) {
						dbili6 = dbili;

					}
				}
			}
			if (dbili1 == null || dbili1.equals("null")
					|| dbili1.equals("") || dbili1 == "") {
				dbili1 = "0";
			}
			if (dbili2 == null || dbili2.equals("null")
					|| dbili2.equals("") || dbili2 == "") {
				dbili2 = "0";
			}
			if (dbili3 == null || dbili3.equals("null")
					|| dbili3.equals("") || dbili3 == "") {
				dbili3 = "0";
			}
			if (dbili4 == null || dbili4.equals("null")
					|| dbili4.equals("") || dbili4 == "") {
				dbili4 = "0";
			}
			if (dbili5 == null || dbili5.equals("null")
					|| dbili5.equals("") || dbili5 == "") {
				dbili5 = "0";
			}
			if (dbili6 == null || dbili6.equals("null")
					|| dbili6.equals("") || dbili6 == "") {
				dbili6 = "0";
			}
			DecimalFormat mat7 = new DecimalFormat("0.0000000");
			System.out.println(dbili1 + " " + dbili2 + " " + dbili3
					+ " " + dbili4 + " " + dbili5 + " " + dbili6);
			hj = Double.parseDouble(dbili1)
					+ Double.parseDouble(dbili2)
					+ Double.parseDouble(dbili3)
					+ Double.parseDouble(dbili4)
					+ Double.parseDouble(dbili5)
					+ Double.parseDouble(dbili6);
			System.out.println(Double.parseDouble(dbili1) + "  "
					+ Double.parseDouble(dbili2) + "  "
					+ Double.parseDouble(dbili3) + " "
					+ Double.parseDouble(dbili4) + " "
					+ Double.parseDouble(dbili5) + " "
					+ Double.parseDouble(dbili6));

			zh = mat7.format(hj);

			System.out.println("zh-:" + zh);
			String bzw = "1";
			if (!zh.equals("100.0000000")) {
				bzw = "2";
			}
			//获取分摊详细信息
			String dbili9 = "", sszy = "", qcb = "", kjkm = "", zymx = "", xjbl = "";
			String bzw9 = "";
			ArrayList listtxjbl = new ArrayList();
			listtxjbl = beanm.getStationMhchexktxjbl(ammeterid);
			System.out.println("listtxjbl:" + listtxjbl);
			if (listtxjbl != null) {
				int fycountxjbl = ((Integer) listtxjbl.get(0))
						.intValue();
				for (int kk = fycountxjbl; kk < listtxjbl.size() - 1; kk += fycountxjbl) {

					dbili9 = (String) listtxjbl.get(kk
							+ listtxjbl.indexOf("DBILI"));
					dbili9 = dbili9 != null ? dbili9 : "";
					System.out.println("dbili9：" + dbili9);

					sszy = (String) listtxjbl.get(kk
							+ listtxjbl.indexOf("SHUOSHUZHUANYE"));
					sszy = sszy != null ? sszy : "";
					System.out.println("sszy：" + sszy);
					qcb = (String) listtxjbl.get(kk
							+ listtxjbl.indexOf("QCBCODE"));
					qcb = qcb != null ? qcb : "";
					System.out.println("qcb：" + qcb);
					kjkm = (String) listtxjbl.get(kk
							+ listtxjbl.indexOf("KJKMCODE"));
					kjkm = kjkm != null ? kjkm : "";
					System.out.println("kjkm：" + kjkm);

					zymx = (String) listtxjbl.get(kk
							+ listtxjbl.indexOf("ZYMXCODE"));
					zymx = zymx != null ? zymx : "";
					System.out.println("zymx：" + zymx);
					xjbl = (String) listtxjbl.get(kk
							+ listtxjbl.indexOf("XJBILI"));
					xjbl = xjbl != null ? xjbl : "";
					System.out.println("xjbl：" + xjbl);
					//  dbili9="",sszy="",qcb="",kjkm="",zymx="",xjbl="";
					if ("".equals(dbili9) || "".equals(sszy)
							|| "".equals(kjkm) || "".equals(qcb)
							|| "".equals(zymx) || "".equals(xjbl)) {
						bzw9 = "0";
					}
				}
			}

			System.out.println("标志位：" + bzw9);
%>
<html>
	<head>
		<title></title>

		<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}

.form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	width: 130px;
}

.form_labell {
	font-family: 宋体;
	font-size: 12px;
	width: 70px;
}

.form {
	height: 19px;
	width: 130px;
}

.formm {
	text-align: right;
	height: 19px;
	width: 80px;
}

.formr {
	text-align: right;
	height: 19px;
	width: 130px;
}

.form1 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	height: 19px;
	width: 130px;
}

.form2 {
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	text-align: right;
	height: 19px;
	width: 130px;
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script>
window.onload = function() {

	var bz = '<%=bzw9%>';
	var fthj = '<%=zh%>';
	//if (fthj != "100.0000000" || bz == "0") {
		//alert("该电表没有做分摊或者分摊有异常，请到监测点管理进行修改");
		//return;

	//}
	var shifou = '<%=shifou%>';
	if (shifou == "0") {

	} else {
		getHaodianliang();
	}
	endmonthzq();
}
</script>
		<script>

var oCalendarEn = new PopupCalendar("oCalendarEn"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();

var oCalendarChs = new PopupCalendar("oCalendarChs"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChs.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChs.oBtnTodayTitle = "今天";
oCalendarChs.oBtnCancelTitle = "取消";
oCalendarChs.Init();
</script>
		<!-- 年月日期控件 -->
		<script>

var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();

var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChsny.oBtnTodayTitle = "确定";
oCalendarChsny.oBtnCancelTitle = "取消";
oCalendarChsny.Init();
</script>

		<script language="javascript">

var path = '<%=path%>';
function fun() {

	var lastdate = document.getElementById('lastdatetime').value;
	var thisdate = document.getElementById('thisdatetime').value;

	var qsdbdl = '<%=qsdbdl%>';
	var edhdl = '<%=edhdl%>';
	var itemvalue = '<%=itemvalue%>'
	var nd = 1000 * 24 * 60 * 60;//一天多少毫秒
	if (thisdate != null && thisdate != "") {
		var blhdl = document.getElementById('blhdl').value;
		// alert("blhdl"+blhdl);
		var date1 = new Date(Date.parse(lastdate.replace(/-/g, "/"))); //转换成Data();
		var date2 = new Date(Date.parse(thisdate.replace(/-/g, "/"))); //转换成Data();
		lastdate = date1.getTime();
		thisdate = date2.getTime();
		//两个时间之间的天数
		var ld = (thisdate - lastdate) / nd + 1;//相差天数
		//alert(ld);
		var bb = parseFloat(blhdl);//倍率耗电量
		var ee = parseFloat(edhdl) * parseFloat(ld);
		var qq = parseFloat(qsdbdl) * parseFloat(ld);//全省定标
		if (qsdbdl != "" && qsdbdl != null) {
			//alert(bb+"  "+qq+"  "+ee);
			//if(bb-qq>0){
			var qqbili = (((bb - qq) / qq) * 100).toFixed(2);

			document.form1.kongbai1.value = "超全省定标的" + qqbili + "%!";

			//}else{
			//	var qqbili=(((qq-bb)/qq)*100).toFixed(2);
			//	document.form1.kongbai1.value="超全省定标的"+qqbili+"%!";
			//}
		} else {
			document.form1.kongbai1.value = "系统中的全省 定标电量是空值！！！";
		}
		if (edhdl != "" && edhdl != null) {
			if (bb - ee > 0) {
				var bili = (((bb - ee) / ee) * 100).toFixed(2);
				//系统中的电量上下浮动超过站点额定耗电量计算值为"+itemvalue+"%,本次的比系统的超过了"+bili+"%!"
				document.form1.kongbai.value = "该记录超本地标" + bili + "%!";

			} else {
				var bili = (((bb - ee) / ee) * 100).toFixed(2);
				document.form1.kongbai.value = "该记录超本地标" + bili + "%!";

			}
		} else {
			document.form1.kongbai.value = "系统中的额定耗电量是空值！！！";

		}

	}
}
function DBgh() {
	var qsagree = document.form1.lastdegree.value;
	if (document.form1.dbql.checked) {

		document.form1.lastgree01.value = qsagree;
		document.form1.lastdegree.value = "0.0";
	} else {

		document.form1.lastdegree.value = document.form1.lastgree01.value;
	}

}
function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = ""
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

	} else {
		trObject.style.display = "none"
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
	}
}
function saveDegree() {

// 	var thisdatetime = document.form1.thisdatetime.value;
 	//var lastdatetime = document.form1.lastdatetime.value;
// 	var startmonth = document.form1.startmonth.value;
	
// 	if (lastdatetime == null || lastdatetime == "") {
// 		alert("您输入的信息不完善，上次抄表时间不能为空！");
// 	} else {
		//if(payzq==""||payzq==null||payzq==0)payzq=1;
// 		var month = startmonth.substring(5, 7) - 0;
// 		var year = startmonth.substring(0, 4) - 0;
// 		var bmonth = thisdatetime.substring(5, 7) - 0;
// 		var byear = thisdatetime.substring(0, 4) - 0;
// 		var bdate = thisdatetime.substring(8, 10) - 0;

// 		var smonth = lastdatetime.substring(5, 7) - 0;
// 		var syear = lastdatetime.substring(0, 4) - 0;
// 		var sdate = lastdatetime.substring(8, 10) - 0;

// 		var d1 = new Date(syear, smonth, sdate);
// 		var d2 = new Date(byear, bmonth, bdate);
// 		var s = (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
// 		var days = Math.round(s);

// 		if (days < 1) {
// 			alert("您输入的信息有误，上次抄表时间不能大于或等于本次抄表时间！");
// 		}
// 	}

	//var pje = document.form1.pjje.value;
	//var moneyqk1 = document.form1.pjje.value.replace(/[ ]/g, "");
// 	var zh = (parseFloat(document.form1.scdl.value)
// 			+ parseFloat(document.form1.bgdl.value)
// 			+ parseFloat(document.form1.yydl.value)
// 			+ parseFloat(document.form1.xxhdl.value)
// 			+ parseFloat(document.form1.jstzdl.value) + parseFloat(document.form1.dddfdl.value))
// 			.toFixed(2);

	//sif (zh == (parseFloat(document.form1.blhdl.value)).toFixed(2)) {
		
// 		var zf = (parseFloat((parseFloat(parseFloat(document.form1.scdff.value)
// 				+ parseFloat(document.form1.bgdf.value)
// 				+ parseFloat(document.form1.yydf.value)
// 				+ parseFloat(document.form1.xxhdf.value)
// 				+ parseFloat(document.form1.jstzdf.value)
// 				+ parseFloat(document.form1.dddfdf.value))).toFixed(2)))
// 				.toFixed(2);
		//alert(zf+"---"+(parseFloat((parseFloat(document.form1.actualpay.value)).toFixed(2))).toFixed(2));
		//alert(zf);
// 		if (zf == (parseFloat((parseFloat(document.form1.actualpay.value))
// 				.toFixed(2))).toFixed(2)) {
			//alert(lastdatetime);
			var ad2_bz = "";//AuditDegree2状态标志
			//var ad2_bz1 = "";//AuditDegree5状态标志 
// 			if (document.form1.lastdegree.value != document.form1.thisdegree.value) {
// 				ad2_bz = "1";
// 			}
// 			if (document.form1.lastdatetime.value != document.form1.thisdatetime.value) {
// 				ad2_bz1 = "1";
// 			}

			//if (checkNotnull(document.form1.lastdegree, "上次电表读数")
					//&& checkNotnull(document.form1.actualpay, "实际电费金额")
					//&& checkNotnull(document.form1.startmonth, "起始年月")
					//&& checkNotnull(document.form1.endmonth, "结束年月")
					//&& checkNotnull(document.form1.thisdatetime, "本次抄表的时间")
					//&& checkNotnull(document.form1.thisdegree, "本次电表读数")
					//&& checkNotnull(document.form1.accountmonth, "报账月份")
					//&& checkNotSelected(document.form1.notetypeid, "票据类型")
					//&& checkNotnull(document.form1.pjje, "票据金额")
					//&& checkNotnull(document.form1.unitprice, "单价")
					//&& checkNotnull(document.form1.lastdatetime, "上次抄表时间")
					 {
				var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
				//var ldt = document.form1.lastdatetime.value;
				//var tdt = document.form1.thisdatetime.value;

// 				var ntt = document.form1.notetime.value;
// 				var pdt = document.form1.paydatetime.value;
// 				var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
// 				var sm = document.form1.startmonth.value;
// 				var em = document.form1.endmonth.value;
// 				var am = document.form1.accountmonth.value;//
// 				var dftz = document.form1.floatpay.value;//电费调整
// 				var dftz1 = document.form1.floatpay.value.replace(/[ ]/g, "");
// 				var dltz = document.form1.floatdegree.value;//电量调整
// 				var dltz1 = document.form1.floatdegree.value
// 						.replace(/[ ]/g, "");
// 				var sszy = document.form1.shuoshuzhuanye.value;
// 				var vb = "";

				//if (isDate(pdt) == true || pdt == "" || pdt == null) {
				//	if (isDate(ntt) == true || ntt == "" || ntt == null) {

						//if (reg.exec(ldt)) {
// 							if (isDate(tdt) == true) {
// 								if (reg1.exec(sm)) {
// 									if (reg1.exec(em)) {

// 										if (reg1.exec(am)) {
// 											if (isNaN(document.form1.actualpay.value) == false) {
// 												if (sm <= em) {
// 													if (tdt >= ldt) {
// 														if (isNaN(pje) == false
// 																&& moneyqk1 != "") {
// 															if (isNaN(dltz) == false
// 																	&& dltz1 != "") {
 																//if (isNaN(dftz) == false
 																//		&& dftz1 != "") {
																	if (confirm("您将要修改信息！确认信息正确！")) {
																		document.form1.action = path
																				+ "/servlet/electricfees?action=dianxin";
																		document.form1
																				.submit();
																		showdiv("请稍等..............");
																	}
																}
// 																 else {
// 																	alert("您输入的信息有误，费用调整必须为数字！");
// 																}
															}
// 															 else {
// 																alert("您输入的信息有误，用电量调整必须为数字！");
// 															}

// 														} else {
// 															alert("您输入的信息有误，票据金额必须为数字！");
// 														}
// 													} else {
// 														alert("您输入的信息有误，本次抄表时间必须大于等于上次抄表时间 ！");
// 													}
// 												} else {
// 													alert("您输入的信息有误，起始时间必须小于等于结束时间！");
// 												}
// 											} else {
// 												alert("您输入的信息有误，实际电费金额必须为数字！");
// 											}
// 										} else {
// 											alert("您输入的报账月份有误，请确认后重新输入！");
// 										}

// 									} else {
// 										alert("您输入的结束时间有误，请确认后重新输入！");
// 									}
// 								} else {
// 									alert("您输入的起始时间有误，请确认后重新输入！");
// 								}
// 							} else {
// 								alert("您输入的本次抄表时间有误，请确认后重新输入！");
// 							}
// 						} else {
// 							alert("您输入的上次抄表时间有误，请确认后重新输入！");
// 						}

				//	} 
// 				//	else {
// 						alert("您输入的票据时间有误，请确认后重新输入！");
// 					}
				//}
			//	 else {
 					//alert("您输入的交费时间有误，请确认后重新输入！");
 				//}

			//}
		//} 
// 		else {
// 			alert("分摊电费之和不等于实际电费金额,请确认！！！");
// 		}
// 	}
// 	 else {
// 		alert("分摊电量之和不等于实际用电量,请确认！！！000");
// 	}
//}
function isDate(str) {
	var reg = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
	if (reg.test(str)) {
		return true;
	} else {

		return false;

	}
}
function showIdList() {

	window
			.open('showAmmeterIdList.jsp', '',
					'width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
}

function getHaodianliang() {
	var bl = document.form1.mpower.value; //倍率值     	 
	if (bl == null || bl == "")
		bl = "1"
	var thisdegree = document.form1.thisdegree.value;//本次电表读数 
	var lastdegree = document.form1.lastdegree.value;//上次电表读数 
	var floatdegree = document.form1.floatdegree.value;//用电量调整 
	var linelossvalue = document.form1.linelossvalue.value;//线损值 
	var actualdegree = document.form1.actualdegree.value;//用电量
	//用电量赋值 
	document.form1.actualdegree.value = ((parseFloat(Number(document.form1.thisdegree.value)
			- Number(document.form1.lastdegree.value)) + parseFloat(Number(document.form1.floatdegree.value))))
			.toFixed(2);
	if (document.form1.linelosstype.value == "线损调整") {
		//实际用电量赋值 
		document.form1.blhdl.value = ((Number(thisdegree) - Number(lastdegree)
				+ Number(linelossvalue) + Number(floatdegree)) * bl).toFixed(2);
	} else {

		if (linelossvalue == null || linelossvalue == ""
				|| linelossvalue == "0")
			linelossvalue = "1";

		document.form1.blhdl.value = ((parseFloat(Number(thisdegree)
				- Number(lastdegree))
				* parseFloat(Number(linelossvalue)) + parseFloat(Number(floatdegree))) * bl)
				.toFixed(2);
	}

	// document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.blhdl.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2); 

	//  alert("haodianliang");

	//alert(parseFloat(Number(document.form1.floatpay.value).toFixed(2)));
	// alert(document.form1.unitprice.value*document.form1.blhdl.value+parseFloat(Number(document.form1.floatpay.value).toFixed(2)));
	// alert(document.form1.unitprice.value*document.form1.blhdl.value);
	var aaa = document.form1.unitprice.value * document.form1.blhdl.value;
	var bbb = document.form1.floatpay.value;
	var ccc = Number(Number(aaa) + Number(bbb)).toFixed(2);
	//var ccc=Number(Number(aaa).toFixed(2))+Number(Number(bbb).toFixed(2));
	// alert(ccc);
	// document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.blhdl.value)+parseFloat(Number(document.form1.floatpay.value).toFixed(2))).toFixed(2); 
	document.form1.actualpay.value = ccc;
	fun();

}

function vCode() {
	var accCode = document.form1.workSn.value;
	if (accCode == "") {
		alert("不能为空！")
		return


          }
               window.open('accountCode.jsp?accountId=0&accountCode='+accCode,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }

        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！")
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
       //计算结束年月
        function endmonthzq(){
          qsyear();
         var thisdatetime = document.form1.thisdatetime.value;
         //alert(thisdatetime)
         var lastdatetime = document.form1.lastdatetime.value;
           //alert(lastdatetime)
         var startmonth   = document.form1.startmonth.value;
       //alert(startmonth)
         if(lastdatetime==null||lastdatetime==""){
          
         }else{
           // if(payzq==""||payzq==null||payzq==0)payzq=1;
            var month = startmonth.substring(5,7)-0;
            //alert(month)
            var year  = startmonth.substring(0,4)-0;
            // alert(year)
            var bmonth=thisdatetime.substring(5,7)-0;
           // alert(bmonth)
            var byear=thisdatetime.substring(0,4)-0;
            //alert(byear)
            var bdate=thisdatetime.substring(8,10)-0;
            //alert(bdate)
            var smonth=lastdatetime.substring(5,7)-0;
           // alert(smonth)
            var syear=lastdatetime.substring(0,4)-0;
            //alert(syear)
            var sdate=lastdatetime.substring(8,10)-0;
            //alert(sdate)
            var d1=new Date(syear,smonth,sdate);  
           // alert(d1)
            var d2=new Date(byear,bmonth,bdate);//alert(d2)
            var s=(d2.getTime()-d1.getTime ())/(1000*60*60*24);//alert(s)
            var days=Math.round(s);//alert(days)
            if(days<1){
			
			}else{
  if(syear==byear){
    if(smonth==bmonth){
      if(month<10){
         month="0"+month;
	 year=byear;
       }
         var s;
         s=year+"-"+month;
         document.form1.endmonth.value=s; 
    }
    else{
       if(sdate<16){
         if(bdate<16){
          month=(bmonth-smonth)-1+smonth;
          if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
         }
         if(bdate>15){
           month=(bmonth-smonth)+smonth;
           if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
         }
       }
       if(sdate>15){
         if(bdate<16){
           month=(bmonth-smonth)+smonth;
           if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
         }
         if(bdate>15){
           month=(bmonth-smonth)+smonth;
           if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
         }
       }
    }
    
  }
  if(syear<byear){
     if(smonth==bmonth){ 
     month=bmonth;
       if(month<10){
         month="0"+month;
	 year=byear;
       }
         var s;
         s=year+"-"+month;
         document.form1.endmonth.value=s; 
     }
     else{
        if(sdate<16){
          if(bdate<16){
            month=(12-smonth)+bmonth-1+smonth;
	    year=syear;
		if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
          }
          if(bdate>15){
            month=(12-smonth)+bmonth+smonth;
            year=syear;
		if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
          }
        }
        if(sdate>15){
          if(bdate<16){
           month=(12-smonth)+bmonth+smonth;
           year=syear;
		if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
          }
          if(bdate>15){
            month=(12-smonth)+bmonth+smonth;
            year=syear;
		if(month>12){
        	 year=(year+1);
        	 month=month-12;
            }
            if(month<10){
        	 month="0"+month;
			 year=byear;
            }
            var s;
            s=year+"-"+month;
            document.form1.endmonth.value=s;
          }
        }
     }
  }
  
  
}     
            
            
         }
         fun();//选择抄 表日期后   进行 电费超标 数据计算
         // alert("nianiannai");
        }
        //计算起始年月
        function qsyear(){
          var lastdatetime = document.form1.lastdatetime.value;
         // if(payzq==""||payzq==null||payzq==0)payzq=1;
          var month=lastdatetime.substring(5,7)-0;
          //alert(month)
          var year=lastdatetime.substring(0,4)-0;
         // alert(year)
          var date=lastdatetime.substring(8,10)-0;
          //alert(date)
          var s;
        
          if(date<16){
           if(month<10){
        	 month="0"+month;
           }
           s=year+"-"+month;
             document.form1.startmonth.value=s;
            
          }
          else{
            month=(month+1);
             if(month>12){
        	  year=(year+1);
        	  month=month-12;
              }
             if(month<10){
        	  month="0"+month;
            }
              s=year+"-"+month;
             document.form1.startmonth.value=s;
          }
          //设置一下当前时间给报账月份
           var today=new Date(); 
           var tyear=today.getYear();
           var tmonth=today.getMonth()+1;
           var s1;
          
          if(tmonth<10){
        	 tmonth="0"+tmonth;
           }
           s1=tyear+"-"+tmonth;
             document.form1.accountmonth.value=s1;
          
        }
        
        function getMoney(){
        	var dbili1=document.form1.dbili1.value;
        	var dbili2=document.form1.dbili2.value;
        	var dbili3=document.form1.dbili3.value;
        	var dbili4=document.form1.dbili4.value;
        	var dbili5=document.form1.dbili5.value;
        	var dbili6=document.form1.dbili6.value;
            var actualpay= document.form1.actualpay.value;
            if(actualpay!="NaN"){
             if(dbili1==null||dbili1==""){
        	 dbili1="0.00";
        	 }
        	   if(dbili2==null||dbili2==""){
        	 dbili2="0.00";
        	 }
        	    if(dbili3==null||dbili3==""){
        	 dbili3="0.00";
        	 }
        	    if(dbili4==null||dbili4==""){
        	 dbili4="0.00";
        	 }
        	 if(dbili5==null||dbili5==""){
        	 dbili5="0.00";
        	 }
        	 if(dbili6==null||dbili6==""){
        	 	dbili6="0.00";
        	 }
        	// alert(dbili1+"|"+dbili2+"|"+dbili3+"|"+dbili4+"|"+dbili5+"|"+actualpay); 
        	 //生产
        	    // alert(dbili1);
        	    // alert(actualpay);
        	     //alert((parseFloat(dbili1)/100)*parseFloat(actualpay).toPrecision(2));
        	     
        	   //  alert((parseFloat(dbili1)/100)*parseFloat(actualpay));
        	   //  alert((parseFloat(dbili3)/100)*parseFloat(actualpay));
        	   
        	 /*  
               if(dbili1!="0.00"&&dbili2=="0.00"&& dbili3="0.00"&& dbili4="0.00"&& dbili5="0.00"&& dbili6="0.00"){
        	   
        	    document.form1.scdff.value=((parseFloat(dbili1)/100)*parseFloat(actualpay)).toFixed(2);
        	    
        	   }  
        	    if(dbili2!="0.00"&&dbili1=="0.00"&& dbili3="0.00"&& dbili4="0.00"&& dbili5="0.00"&& dbili6="0.00"){
        	   
        	    document.form1.bgdf.value=((parseFloat(dbili2)/100)*parseFloat(actualpay)).toFixed(2);
        	    
        	   }  
        	    if(dbili3!="0.00"&&dbili2=="0.00"&& dbili1="0.00"&& dbili4="0.00"&& dbili5="0.00"&& dbili6="0.00"){
        	   
        	    document.form1.yydf.value=((parseFloat(dbili3)/100)*parseFloat(actualpay)).toFixed(2);
        	    
        	   }  
        	    if(dbili4!="0.00"&&dbili2=="0.00"&& dbili3="0.00"&& dbili1="0.00"&& dbili5="0.00"&& dbili6="0.00"){
        	   
        	    document.form1.xxhdf.value=((parseFloat(dbili4)/100)*parseFloat(actualpay)).toFixed(2);
        	    
        	   }  
        	    if(dbili5!="0.00"&&dbili2=="0.00"&& dbili3="0.00"&& dbili4="0.00"&& dbili1="0.00"&& dbili6="0.00"){
        	   
        	    document.form1.jstzdf.value=((parseFloat(dbili5)/100)*parseFloat(actualpay)).toFixed(2);
        	    
        	   }  
        	    if(dbili6!="0.00"&&dbili2=="0.00"&& dbili3="0.00"&& dbili4="0.00"&& dbili5="0.00"&& dbili1="0.00"){
        	   
        	    document.form1.dddfdf.value=((parseFloat(dbili6)/100)*parseFloat(actualpay)).toFixed(2);
        	    
        	   }  
        		*/
        		var bz_1 = 0;
        		var bz_2 = 0;
        		var bz_3 = 0;
        		var bz_4 = 0;
        		var bz_5 = 0;
        		var bz_6 = 0;
        		if(dbili6!=0){
        			bz_6=1;
        		}
        		if(dbili5!=0){
        			bz_5=1;
        		}
        		if(dbili4!=0){
        			bz_4=1;
        		}
        		if(dbili3!=0){
        			bz_3=1;
        		}
        		if(dbili2!=0){
        			bz_2=1;
        		}
        		if(dbili1!=0){
        			bz_1=1;
        		}
        		var df = parseFloat(actualpay);
        		var df1 = ((parseFloat(dbili1)/100)*parseFloat(actualpay)).toFixed(2);
        		var df2=	((parseFloat(dbili2)/100)*parseFloat(actualpay)).toFixed(2);
        		var df3=((parseFloat(dbili3)/100)*parseFloat(actualpay)).toFixed(2);
        		var df4=((parseFloat(dbili4)/100)*parseFloat(actualpay)).toFixed(2);
        		var df5= ((parseFloat(dbili5)/100)*parseFloat(actualpay)).toFixed(2);
        		var df6= ((parseFloat(dbili6)/100)*parseFloat(actualpay)).toFixed(2);
        		
//         		document.form1.scdff.value=df1;
//         		document.form1.yydf.value=	df2;
//         		document.form1.bgdf.value=df3;
//         		document.form1.xxhdf.value=df4;
//         		document.form1.jstzdf.value= df5;
//         		document.form1.dddfdf.value= df6;
//         		if(bz_6==1){
//         			document.form1.dddfdf.value=(df-df1-df2-df3-df4-df5).toFixed(2);
//         			return;
//         		}
//         		if(bz_5==1){
//         			document.form1.jstzdf.value=(df-df1-df2-df3-df4-df6).toFixed(2);
//         			return;
//         		}
//         		if(bz_4==1){
//         			document.form1.xxhdf.value=(df-df1-df2-df3-df6-df5).toFixed(2);
//         			return;
//         		}
//         		if(bz_3==1){
//         			document.form1.bgdf.value=(df-df1-df2-df6-df4-df5).toFixed(2);
//         			return;
//         		}
//         		if(bz_2==1){
//         			document.form1.yydf.value=(df-df1-df6-df3-df4-df5).toFixed(2);
//         			return;
//         		}
//         		if(bz_1==1){
//         			document.form1.scdff.value=(df-df6-df2-df3-df4-df5).toFixed(2);
//         			return;
//         		}
        
        	 }
        	 
        }
        
        
          function getHaodianliangg(){
        	var dbili1=document.form1.dbili1.value;
        	var dbili2=document.form1.dbili2.value;
        	var dbili3=document.form1.dbili3.value;
        	var dbili4=document.form1.dbili4.value;
        	var dbili5=document.form1.dbili5.value;
        	var dbili6=document.form1.dbili6.value;
        	var blhdl= document.form1.blhdl.value;
        	
        	 if(dbili1==null||dbili1==""){
        	 dbili1="0.00";
        	 }
        	   if(dbili2==null||dbili2==""){
        	 dbili2="0.00";
        	 }
        	    if(dbili3==null||dbili3==""){
        	 dbili3="0.00";
        	 }
        	    if(dbili4==null||dbili4==""){
        	 dbili4="0.00";
        	 }
        	    if(dbili5==null||dbili5==""){
        	 dbili5="0.00";
        	 }
        	 if(dbili6==null||dbili6==""){
        	 	dbili6="0.00";
        	 }
        		var v = parseFloat(blhdl);
        		var v1 = ((parseFloat(dbili1)/100)*parseFloat(blhdl)).toFixed(2);
        		var v2 = ((parseFloat(dbili3)/100)*parseFloat(blhdl)).toFixed(2);
        		var v3 = ((parseFloat(dbili2)/100)*parseFloat(blhdl)).toFixed(2);
        		var v4 = ((parseFloat(dbili4)/100)*parseFloat(blhdl)).toFixed(2);
        		var v5 = ((parseFloat(dbili5)/100)*parseFloat(blhdl)).toFixed(2);
        		var v6 = ((parseFloat(dbili6)/100)*parseFloat(blhdl)).toFixed(2);
        		
        			
        	 //生产
//         		 document.form1.scdl.value=v1;
        	  办公
//         		 document.form1.bgdl.value=v2;	
        	 营业
//         		 document.form1.yydl.value=	v3;
        	 信息化	
//         		 document.form1.xxhdl.value=v4;
        	 建设投资
//         		 document.form1.jstzdl.value= v5;
        	代垫电量
//         		document.form1.dddfdl.value= v6;
//         	if(v6!=0){
//         		document.form1.dddfdl.value=(v-v1-v2-v3-v4-v5).toFixed(2);
//         		return;
//         	}
//         	if(v5!=0){
//         		document.form1.jstzdl.value=(v-v1-v2-v3-v4-v6).toFixed(2);
//         		return;
//         	}
//         	if(v4!=0){
//         		document.form1.xxhdl.value=(v-v1-v2-v3-v6-v5).toFixed(2);
//         		return;
//         	}
//         	if(v3!=0){
//         		document.form1.yydl.value=(v-v1-v2-v6-v4-v5).toFixed(2);
//         		return;
//         	}
//         	if(v2!=0){
//         		document.form1.bgdl.value=(v-v1-v6-v3-v4-v5).toFixed(2);
//         		return;
//         	}
//         	if(v1!=0){
//         		document.form1.scdl.value=(v-v6-v2-v3-v4-v5).toFixed(2);
//         		return;
//         	}
        	
         }

	$(function(){
        $("#saveBtn").click(function(){
		   saveDegree();
		});
        $("#resetBtn").click(function() {	
        	$("#form")[0].reset();
        });
         $("#liulan").click(function(){
		   showIdList();
		});
         
		$("#cancelBtn").click(function(){
			window.history.back();
		});
        
	});
	
	
	
	
</script>
	</head>
	<jsp:useBean id="roleBean" scope="page"
		class="com.noki.mobi.sys.javabean.RoleBean"></jsp:useBean>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean"></jsp:useBean>
	<body class="body" style="overflow-x: hidden;">
		<br>
		<LINK href="../../images/css.css" type="text/css" rel=stylesheet>
		<form action="" name="form1" method="POST" id="form">
			<%
				//如果一级分摊不为100不显示
						//if (bzw.equals("1") && !bzw9.equals("0")) {
			%>
			<table border="0" width="100%">
				<tr>
					<td width="83%" scope="2">
						<table width="100%" border="0" cellspacing="1" cellpadding="1">
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<input type="hidden" name="accountid" value="<%=accountid%>"
										class="form1" />
									<div>
										地区：
									</div>
								</td>
								<td>
									<!-- 页面保存的是电表deID -->
									<input type="hidden" name="ammeteridFk" value="<%=ammeterid%>" />
									<input type="text" name="shi" readonly="true" value="<%=diqu%>"
										class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										站点类型：
									</div>
								</td>
								<td>
									<input type="text" name="stationtype" readonly="true"
										value="<%=stationtype%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										原产权单位：
									</div>
								</td>
								<td>
									<input type="text" name="ycq" readonly="true" value="<%=gsf%>"
										class="form1" />
								</td>
							</tr>
							<tr>

								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										站点名称：
									</div>
								</td>
								<td>
									<input type="text" name="jzname" readonly="true"
										value="<%=jzname%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										站点编码：
									</div>
								</td>
								<td>
									<input type="text" name="jzcode" readonly="true" value="<%=jzcode%>"
										class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										物理站点编码：
									</div>
								</td>
								<td>
									<input type="text" name="wlzdbm" readonly="true"
										value="<%=wlzdbm%>" class="form1" />
								</td>

							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										支付单号：
									</div>
								</td>
								<td>
									<input type="text" name="zfdh" readonly="true"
										value="<%=zfdh%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										供电方名称：
									</div>
								</td>
								<td>
									<input type="text" name="gdfname" readonly="true"
										value="<%=gdfname%>" class="form1" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										电表户号：
									</div>
								</td>
								<td>
									<input type="text" name="dbbm" readonly="true"
										value="<%=dbbm%>" class="form2" />
								</td>
							</tr>
							<tr>
							<td bgcolor="#DDDDDD" class="form_label">
									<div>
										站址类型：
									</div>
								</td>
								<td>
									<input type="text" name="zzlx" readonly="true"
										value="<%=zzlx%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
								       供电方式：
									</div>
								</td>
								<td>
									<input type="text" name="gdfs" readonly="true"
										value="<%=gdfs%>" class="form2" />
								</td>
								
							</tr>
							<tr>
								<td colspan="6" class="form_label">
									<img src="../../images/v.gif" width="15" height="15" />
									电费信息
								</td>
							</tr>

							<tr>
								<%
									if (shifou.equals("0")) {
								%>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										上次电表读数：
									</div>
								</td>
								<td>
									<input type="text" name="lastdegree" value="0" readonly="true"
										class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										本次电表读数：
									</div>
								</td>
								<td>
									<input type="text" name="thisdegree" value="0" readonly="true"
										class="form2" />
									<%
										} else {
									%>
									<td bgcolor="#DDDDDD" class="form_label">
										<div>
											上次电表读数：
										</div>
									</td>
									<td>
										<input type="text" name="lastdegree" value="<%=scdbds%>"
											readonly="true" class="form2" />
									</td>
									<td bgcolor="#DDDDDD" class="form_label">
										<div>
											本次电表读数：
										</div>
									</td>
									<td>
										<input type="text" name="thisdegree" value="<%=bcdbds%>"
											onChange="getHaodianliang()" class="formr" />
										<span style="color: #FF0000; font-weight: bold">*</span>
										<%
											}
										%>
										<br>
										<input type="checkbox" name="dbql" onclick="DBgh()">
										<font size="2">是/否更换电表</font>
									</td>							
								<td bgcolor="#DDDDDD" class="form_label">
										<div>
											用电量：
										</div>
									</td>
									<td>
										<input type="text" name="actualdegree"/>
									</td>								
								
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										上次抄表时间：
									</div>
								</td>
								<td>
									<input type="text" id="thisdatetime" name="lastdatetime"
										value="<%=bcdbsj%>" onFocus="getDateString(this,oCalendarChs)"
										class="form" onpropertychange="endmonthzq()" align="right" />
									<span style="color: #FF0000; font-weight: bold">*</span>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										本次抄表时间：
									</div>
								</td>
								<td>
									<input type="text" id="thisdatetime" name="thisdatetime"
										value="<%=bcdbsj%>" onFocus="getDateString(this,oCalendarChs)"
										class="form" onpropertychange="endmonthzq()" align="right" />
									<span style="color: #FF0000; font-weight: bold">*</span>
								</td>


<!-- 									if (shifou.equals("0")) { -->
<!-- 								%> -->
<!-- 								<td class="form_label"> -->
<!-- 									<div></div> -->
<!-- 								</td> -->
<!-- 								<td> -->
<!-- 									<input type="hidden" name="floatdegree" value="0" class="formr" /> -->
<!-- 								</td> -->
<!-- 								< -->
<!-- 									} else { -->
<!-- 								%> -->
<!-- 								<td bgcolor="#DDDDDD" class="form_label"> -->
<!-- 									<div> -->
<!-- 										用电量调整： -->
<!-- 									</div> -->
<!-- 								</td> -->
<!-- 								<td> -->
<!-- 									<input type="text" name="floatdegree" value="0" -->
<!-- 										onChange="getHaodianliang()" class="formr" /> -->
<!-- 								</td> -->
								<%
									
								%>
							<td bgcolor="#DDDDDD" class="form_label">
									<div>
										客户：
									</div>
								</td>
							<td>
							<input type="text" name="kehu">
							</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										本次单价：
									</div>
								</td>
								<td>
									<input type="text" name="unitprice" 
										id="unitprice" value="<%=dianfei%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										结束月份：
									</div>
								</td>
								<td>
									<input type="text" name="endmonth" value=""
										onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
									<span style="color: #FF0000; font-weight: bold">*</span>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										倍率耗电量：
									</div>
								</td>
								<td>
									<input type="text" name="blhdl" 
										id="unitprice" value="<%=dianfei%>" class="form2" />
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										缴费金额：
									</div>
								</td>
								<td>
									<input type="text" name="unitprice" 
										id="unitprice" value="<%=dianfei%>" class="form2" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										缴费日期：
									</div>
								</td>
								<td>
									<input type="text" id="jiaofeitime" name="jiaofeitime"
										value="<%=bcdbsj%>" onFocus="getDateString(this,oCalendarChs)"
										class="form" onpropertychange="endmonthzq()" align="right" />
									<span style="color: #FF0000; font-weight: bold">*</span>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										税负因子：
									</div>
								</td>
								<td>
								<input type="text" name="sfyz">
								</td>
							</tr>
							<tr>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										票据类型：
									</div>
								</td>
								<td>
									<div>
										<select name="notetypeid" style="width: 130">
											<option value="0">
												请选择
											</option>
											<%
												ArrayList feetypelist = new ArrayList();
															feetypelist = commBean.getNoteType();
															if (feetypelist != null) {
																String sfid = "", sfnm = "";
																int scount = ((Integer) feetypelist.get(0))
																		.intValue();
																for (int i = scount; i < feetypelist.size() - 1; i += scount) {
																	sfid = (String) feetypelist.get(i
																			+ feetypelist.indexOf("CODE"));
																	sfnm = (String) feetypelist.get(i
																			+ feetypelist.indexOf("NAME"));
											%>
											<option value="<%=sfid%>"><%=sfnm%></option>
											<%
												}
															}
											%>
										</select>
										<span style="color: #FF0000; font-weight: bold">*</span>
									</div>
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										分摊比例：
									</div>
								</td>
								<td>
									<input type="text" name="fentanbili" value="" class="form" />
								</td>
								<td bgcolor="#DDDDDD" class="form_label">
									<div>
										分摊金额：
									</div>
								</td>
								<td>
									<input type="text" name="fentaanjine" value=""
									 class="form" />
								</td>
							</tr>
							
							<tr>
								<td>
									<input type="hidden" name="lastgree01" id="lastgree01" value="" />
								</td>
								<td colspan="6">
									<div id="cancelBtn"
										style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 0px">
										<img src="<%=path%>/images/quxiao.png" width="100%"
											height="100%">
										<span
											style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
									</div>
									<div id="resetBtn"
										style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 11px">
										<img src="<%=path%>/images/2chongzhi.png" width="100%"
											height="100%">
										<span
											style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
									</div>
									<%
										if (!"0".equals(bzw)) {
									%>
									<div id="saveBtn"
										style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 22px">
										<img src="<%=path%>/images/baocun.png" width="100%"
											height="100%">
										<span
											style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">保存</span>
									</div> 
									<%
										}
									%>

								</td>
							</tr>
						</table>
					</td>
<!-- 					<td width="17%"> -->
<!-- 						<table border="0" width="100%"> -->
<!-- 							<tr> -->
<!-- 								<td> -->
<!-- 									<fieldset id="regist"> -->
<!-- 										<table border="0" width="100%"> -->
<!-- 											<tr> -->
<!-- 												<td colspan="2"> -->
<!-- 													<div align="center"> -->
<!-- 														<b><font size="2">电量分摊</font> </b> -->
<!-- 														<hr /> -->
<!-- 													</div> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td class="form_labell"> -->
<!-- 													生产 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<input type="text" name="scdl" value="0.00" class="formm" /> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td class="form_labell"> -->
<!-- 													办公 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<input type="text" name="bgdl" value="0.00" class="formm" /> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td class="form_labell"> -->
<!-- 													营业 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<input type="text" name="yydl" value="0.00" class="formm" /> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td class="form_labell"> -->
<!-- 													信息化支撑 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<input type="text" name="xxhdl" value="0.00" class="formm" /> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td class="form_labell"> -->
<!-- 													建设投资 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<input type="text" name="jstzdl" value="0.00" class="formm" /> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td class="form_labell"> -->
<!-- 													代垫电量 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<input type="text" name="dddfdl" value="0.00" class="formm" /> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 										</table> -->
<!-- 									</fieldset> -->
<!-- 								</td> -->
							</tr>
<!-- 							<tr> -->
<!-- 								<td> -->
<!-- 									<fieldset id="regist"> -->
<!-- 										<table border="0" width="100%"> -->
<!-- 											<tr align="center"> -->
<!-- 												<td colspan="2"> -->
<!-- 													<div align="center"> -->
<!-- 														<b><font size="2">电费分摊</font> </b> -->
<!-- 														<hr /> -->
<!-- 													</div> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td class="form_labell"> -->
<!-- 													生产 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<input type="text" name="scdff" value="0.00" class="formm" /> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td class="form_labell"> -->
<!-- 													办公 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<input type="text" name="bgdf" value="0.00" class="formm" /> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td class="form_labell"> -->
<!-- 													营业 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<input type="text" name="yydf" value="0.00" class="formm" /> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td class="form_labell"> -->
<!-- 													信息化支撑 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<input type="text" name="xxhdf" value="0.00" class="formm" /> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td class="form_labell"> -->
<!-- 													建设投资 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<input type="text" name="jstzdf" value="0.00" class="formm" /> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 												<td class="form_labell"> -->
<!-- 													代垫电费 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<input type="text" name="dddfdf" value="0.00" class="formm" /> -->
<!-- 												</td> -->
<!-- 											</tr> -->
<!-- 										</table> -->
<!-- 									</fieldset> -->
<!-- 								</td> -->
<!-- 							</tr> -->
<!-- 						</table> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
			<%
				//}
			%>
		</form>
		<%
			}
			}
		%>
	</body>
</html>