<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.noki.biaogan.BiaoganManage"%>
<%@ page import="com.noki.biaogan.model.BiaoganBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.noki.dianliang.manage.DianLiangManage" %>
<%@ page import="com.noki.dianliang.model.TopBaozhangBean" %>
<%@ page import="com.noki.dianliang.model.TopAvgPUEBean" %>
<%@ page import="com.noki.dianliang.model.AmmeterdegreesBean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
			String path = request.getContextPath();
			String loginName = (String) session.getAttribute("loginName");
			Account account = (Account) session.getAttribute("account");
			String loginId = account.getAccountId();
			String roleId = (String) session.getAttribute("accountRole");
			/**
			
			System.out.println("################站点" + roleId);
			String jztype1 = request.getParameter("jztype") != null ? request
					.getParameter("jztype") : "0";
			String jzproperty1 = request.getParameter("jzproperty") != null
					? request.getParameter("jzproperty")
					: "0";
			String bgsign = request.getParameter("bgsign") != null ? request
					.getParameter("bgsign") : "-1";
			String qyzt = request.getParameter("qyzt") != null ? request
					.getParameter("qyzt") : "-1";//站点启用状态

			String caijid = request.getParameter("caijidian") != null ? request
					.getParameter("caijidian") : "-1";
			String sitetype = request.getParameter("stationtype") != null
					? request.getParameter("stationtype")
					: "0";
			String color = null;
			String sheng = (String) session.getAttribute("accountSheng");
			String agcode1 = "";
			if (request.getParameter("shi") == null) {
				ArrayList shilist = new ArrayList();
				CommonBean commBean = new CommonBean();
				shilist = commBean.getAgcode(sheng, account.getAccountId());
				if (shilist != null) {
					int scount = ((Integer) shilist.get(0)).intValue();
					//[2, AGCODE, AGNAME] update by guol20171118
					if(shilist.size()>(scount + 1)){
						agcode1 = (String) shilist.get(scount
							+ shilist.indexOf("AGCODE"));
					}
				}
			}
			String shi = request.getParameter("shi") != null ? request
					.getParameter("shi") : agcode1;
			String xian = request.getParameter("xian") != null ? request
					.getParameter("xian") : "0";
			String xiaoqu = request.getParameter("xiaoqu") != null ? request
					.getParameter("xiaoqu") : "0";
			String sname = request.getParameter("sname") != null ? request
					.getParameter("sname") : "";
			String szdcode = request.getParameter("szdcode") != null ? request
					.getParameter("szdcode") : "";
			String keyword = request.getParameter("txtKeyword") != null
					? request.getParameter("txtKeyword")
					: "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			String fistYearmonth = sdf.format(new Date());
					
			String yearmonth = request.getParameter("yearmonth")==null?fistYearmonth:request.getParameter("yearmonth");
			String bgState = request.getParameter("bgState")==null?"2":request.getParameter("bgState");			
			String rgsh = request.getParameter("rgsh");//站点审核通过   首页传的值
			String rgsh2 = request.getParameter("caiji");// 采集站点
			String s_curpage = request.getParameter("curpage") != null
					? request.getParameter("curpage")
					: "1";//分页
			int count = 0, pagesize = 10, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
			curpage = Integer.parseInt(s_curpage);
			**/
			String permissionRights = "";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
			int year = c.get(Calendar.YEAR); 
			int month = c.get(Calendar.MONTH)+1; 
			int date = c.get(Calendar.DATE); 
			int hour = c.get(Calendar.HOUR_OF_DAY); 
			int minute = c.get(Calendar.MINUTE); 
			int second = c.get(Calendar.SECOND); 
			System.out.println("月份："+year+month);
			
 %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->

	

<script type="text/javascript"src="<%=path%>/javascript/echarts.js"></script>
</head>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page"
	class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
<table style="width:98%;" border="0" cellspacing="2" cellpadding="0">
<%
DecimalFormat df1 = new DecimalFormat("0.00");
DecimalFormat df2 = new DecimalFormat("0.0000");
String whereStr = " and b.CB_ALERT='1'";
String whereStr2 ="";
String time_year="";
if(month<10){
	 whereStr2 = whereStr2 + " and ac.costtime='"+year+"-0"+month+"' ";
	 time_year = year+"-0"+month;
}else{
	 whereStr2 = whereStr2 + " and ac.costtime='"+year+"-"+month+"' ";
	 time_year = year+"-"+month;
}
CommonBean commonBean = new CommonBean();
//获取17地市
List<TopAvgPUEBean> topAvgPUEBeanList = commonBean.get17Shi();
List<TopAvgPUEBean> topAvgPUEBeanList2 = new ArrayList<TopAvgPUEBean>();
List<TopAvgPUEBean> topAvgPUEBeanList3 = new ArrayList<TopAvgPUEBean>();
//获取17地市的本期平均pue
	ArrayList fylist = new ArrayList();
	fylist = commonBean.getAvgAnalysis(time_year);
for(int t=0;t<topAvgPUEBeanList.size();t++){
	TopAvgPUEBean topAvgPUEBean = new TopAvgPUEBean();
	topAvgPUEBean = topAvgPUEBeanList.get(t);
	if(fylist!=null)
	{
		int fycount = ((Integer)fylist.get(0)).intValue();
		String AGCODE="",pue_month="";
		for( int k = fycount;k<fylist.size()-1;k+=fycount){
			AGCODE = (String) fylist.get(k + fylist.indexOf("AGCODE"));
			pue_month = (String) fylist.get(k + fylist.indexOf("PUE"));
			
			if(AGCODE.equals(topAvgPUEBean.getShicode())){
				topAvgPUEBean.setAvgPUE_month(pue_month);
			}
			
		}
	}
	topAvgPUEBeanList2.add(topAvgPUEBean);
}
//获取17地市的本年平均pue
	ArrayList fylist2 = new ArrayList();
	fylist2 = commonBean.getAvgAnalysis2(""+year);
	for(int t=0;t<topAvgPUEBeanList2.size();t++){
		TopAvgPUEBean topAvgPUEBean = new TopAvgPUEBean();
		topAvgPUEBean = topAvgPUEBeanList2.get(t);
		if(fylist2!=null)
		{
			int fycount = ((Integer)fylist2.get(0)).intValue();
			String AGCODE="",pue_month="";
			for( int k = fycount;k<fylist2.size()-1;k+=fycount){
				AGCODE = (String) fylist2.get(k + fylist2.indexOf("AGCODE"));
				pue_month = (String) fylist2.get(k + fylist2.indexOf("PUE"));
				
				if(AGCODE.equals(topAvgPUEBean.getShicode())){
					topAvgPUEBean.setAvgPUE_year(pue_month);
				}
				
			}
		}
		topAvgPUEBeanList3.add(topAvgPUEBean);
	}
	List<String> x_shi=new ArrayList<String>();//17地市名称
	List<String> y_month=new ArrayList<String>();//月平均pue
	List<String> y_year=new ArrayList<String>();//年平均pue
	for(int t=0;t<topAvgPUEBeanList3.size();t++){
		TopAvgPUEBean topAvgPUEBean = new TopAvgPUEBean();
		topAvgPUEBean = topAvgPUEBeanList3.get(t);
		String shiname_s = topAvgPUEBean.getShiname();
		String pueMonth_s = topAvgPUEBean.getAvgPUE_month();
		if(pueMonth_s==null || pueMonth_s.equals("") ){
			pueMonth_s="0";
		}else{
			pueMonth_s = ""+df2.format(Double.valueOf(pueMonth_s).doubleValue());
		}
		String pueYear_s = topAvgPUEBean.getAvgPUE_year();
		if(pueYear_s==null || pueYear_s.equals("") ){
			pueYear_s="0";
		}else{
			pueYear_s = ""+df2.format(Double.valueOf(pueYear_s).doubleValue());
		}
		x_shi.add(shiname_s);
		y_month.add(pueMonth_s);
		y_year.add(pueYear_s);
		
	}
//whereStr2 =  " and ac.costtime='2017-12'";
/**
DianLiangManage dianliang = new DianLiangManage();

ArrayList<TopBaozhangBean> topBaozhangBeanList = dianliang.searchTopBaozhang(curpage, loginName, loginId,whereStr2);

List<String> x_shi=new ArrayList<String>();//图标
List<String> y_dl=new ArrayList<String>();//电量
List<String> y_am=new ArrayList<String>();//总金额 
List<String> y_fm=new ArrayList<String>();//税额
if(topBaozhangBeanList!=null && topBaozhangBeanList.size()>0){
	for(int i=0;i<topBaozhangBeanList.size();i++){
		TopBaozhangBean tbBean =  topBaozhangBeanList.get(i);
		String costtime = tbBean.getCosttime();
		String costshi =  tbBean.getShi();
		String sumDianliang ="";String sumAllmoney ="";String sumFaxmoney ="";
		if(!tbBean.getSumDianliang().equals("") && tbBean.getSumDianliang()!=null){
			sumDianliang =  ""+(df1.format(Double.valueOf(tbBean.getSumDianliang())));
		}
		if(!tbBean.getSumAllmoney().equals("") && tbBean.getSumAllmoney()!=null){
			 sumAllmoney =  ""+(df1.format(Double.valueOf(tbBean.getSumAllmoney())));
		}
		if(!tbBean.getSumFaxmoney().equals("") && tbBean.getSumFaxmoney()!=null){
			 sumFaxmoney =  ""+(df1.format(Double.valueOf(tbBean.getSumFaxmoney())));
		}
			 
		x_shi.add(costshi);
		y_dl.add(sumDianliang);
		y_am.add(sumAllmoney);
		y_fm.add(sumFaxmoney);
	}
}
int size = topBaozhangBeanList.size();  
String arr_shi[]= (String[])x_shi.toArray(new String[size]);
String arr_dl[]=(String[])y_dl.toArray(new String[size]);
String arr_am[]=(String[])y_am.toArray(new String[size]);
String arr_fm[]=(String[])y_fm.toArray(new String[size]);
**/
%>
<tr valign="top">
		<td >
			<div class="content01" style="padding:2px 2px;width:100%;" >
				<div class="tit01"><%=year%>年<%=month%>月PUE</div>
				<div class="content01_1" style="padding:0px 0px;width:100%;height:350px; ">
				  <div id="chart" style="width:100%;height:350px;"></div>
				</div>
			</div>
		</td>
		<%--
		<td >
			<div class="content01" style="padding:2px 2px;width:500px;height:220px " >
				<div class="tit01">2016年10月预存卡表预警</div>
				<div class="content01_1" style="padding:0px 0px;width:500px">
					<table width="500" border="0" cellpadding="0" cellspacing="0" class="tb_select" >
					       <tr><td align="left" style="padding:0px 0px; ">
					       <table width="100%"  border="0" cellpadding="1" cellspacing="0" style="background-color:#CCCCCC">
							<td align="left" width="220">基站名称</td>
							<td align="left" width="100">预存卡号</td>
							<td align="left" width="80">剩余电量(度)</td>
							<td align="left" width="100">截止日期</td>
						</table>
						</td>
						<tr>
							<td  align="left" style="padding:0px 0px; height:160px" valign="top">
								<marquee style="WIDTH: 100%; HEIGHT: 150px" scrollamount="2" direction="up" >
									<table width="500"  border="0" cellpadding="1" cellspacing="0">
											<font style="text-align: center">暂无信息</>
									</table>
								</marquee >
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td>

		<tr valign="top">
		<td >
			<div class="content01" style="padding:2px 2px;width:100%;" >
				<div class="tit01"><%=year%>年<%=month%>月电费</div>
				<div class="content01_1" style="padding:0px 0px;width:100%;height:215px; ">
				  <div id="chart" style="width:100%;height:215px;"></div>
				</div>
			</div>
		</td>
		<td >
			<div class="content01" style="padding:2px 2px;width:500px;height:220px " >
				<div class="tit01">2016年10月预存卡表预警</div>
				<div class="content01_1" style="padding:0px 0px;width:500px">
					<table width="500" border="0" cellpadding="0" cellspacing="0" class="tb_select" >
					       <tr><td align="left" style="padding:0px 0px; ">
					       <table width="100%"  border="0" cellpadding="1" cellspacing="0" style="background-color:#CCCCCC">
							<td align="left" width="220">基站名称</td>
							<td align="left" width="100">预存卡号</td>
							<td align="left" width="80">剩余电量(度)</td>
							<td align="left" width="100">截止日期</td>
						</table>
						</td>
						<tr>
							<td  align="left" style="padding:0px 0px; height:160px" valign="top">
								<marquee style="WIDTH: 100%; HEIGHT: 150px" scrollamount="2" direction="up" >
									<table width="500"  border="0" cellpadding="1" cellspacing="0">
											<font style="text-align: center">暂无信息</>
									</table>
								</marquee >
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td>
		
		
		<td >
			<div class="content01" style="padding:2px 2px;width:500px;height:220px " >
				<div class="tit01">2016年10月电费支付预警</div>
				<div class="content01_1" style="padding:0px 0px;width:500px">
					<table width="500" border="0" cellpadding="0" cellspacing="0" class="tb_select" >
					       <tr><td align="left" style="padding:0px 0px; ">
					       <table width="100%"  border="0" cellpadding="1" cellspacing="0" style="background-color:#CCCCCC">
							<td align="left" width="220">基站名称</td>
							<td align="left" width="80"  >支付卡号</td>
							<td align="left" width="100">应付金额(元)</td>
							<td align="left" width="100">支付截止日期</td>
						</table>
						</td>
						<tr>
							<td  align="left" style="padding:0px 0px; height:160px">
								<marquee style="WIDTH: 100%; HEIGHT: 150px" scrollamount="2" direction="up" >
									<table width="500"  border="0" cellpadding="1" cellspacing="0">
											<font style="text-align: center">暂无信息</>
									</table>
								</marquee >
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td>
	</tr>
	
	<tr valign="top">
	<td >
			<div class="content01" style="padding:2px 2px;width:100%;height:100% " >
				<div class="tit01"><%=year%>年<%=month%>月电费信息</div>
				<div class="content01_1" style="padding:0px 0px;width:100%;">
					<table style="width:100%;" border="0" cellpadding="0" cellspacing="0" class="tb_select" >
					       <tr><td align="left" style="padding:0px 0px; ">
					       <table width="100%"  border="0" cellpadding="1" cellspacing="0" style="background-color:#CCCCCC">
							<tr><td align="left" width="20%">报账期间</td>
							<td align="left" width="20%">地市</td>
							<td align="left" width="20%">电量(度)</td>
							<td align="left" width="20%">总金额(元)</td>
							<td align="left" width="20%">税额(元)</td>
							</tr>
						</table>
						</td>
						</tr>
						<tr>
							<td  align="left" style="padding:0px 0px; height:160px" valign="top">
								<marquee id="go1" onMouseOver=go1.stop(); onMouseOut=go1.start(); style="WIDTH: 100%; HEIGHT: 150px" scrollamount="2" direction="up" >
									<table style="width:100%;" border="0" cellpadding="1" cellspacing="0">
									<% 
									
										if(topBaozhangBeanList!=null && topBaozhangBeanList.size()>0){
											for(int i=0;i<topBaozhangBeanList.size();i++){
												TopBaozhangBean tbBean = topBaozhangBeanList.get(i);
												String costtime = tbBean.getCosttime();
												String costshi =  tbBean.getShi();
												String sumDianliang =  ""+(df1.format(Double.valueOf(tbBean.getSumDianliang())));
												String sumAllmoney =  ""+(df1.format(Double.valueOf(tbBean.getSumAllmoney())));
												String sumFaxmoney =  ""+(df1.format(Double.valueOf(tbBean.getSumFaxmoney())));
												int intnum = pagesize*(curpage - 1) + 1;
									%>
										<tr align="center">
											<td align="left" width="20%"><%=costtime%></td>
											<td align="left" width="20%"><%=costshi%></td>
											<td align="left" width="20%"><%=sumDianliang%></td>
											<td align="left" width="20%"><%=sumAllmoney%></td>
											<td align="left" width="20%"><%=sumFaxmoney%></td>
										</tr>
									<%		
										}
										}else{
									%>
										<tr align="center">
											<td colspan="5"><font size="5" color="blue">暂无此月电费信息</font></td>
										</tr>
										<%} %>
									</table>
								</marquee >
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td>
		--%>
		<%--<td >
			<div class="content01" style="padding:2px 2px;width:500px;height:220px " >
				<div class="tit01"><%=year%>年<%=month%>月电费超标报警</div>
				<div class="content01_1" style="padding:0px 0px;width:500px">
					<table width="500" border="0" cellpadding="0" cellspacing="0" class="tb_select" >
					       <tr><td align="left" style="padding:0px 0px; ">
					       <table width="100%"  border="0" cellpadding="1" cellspacing="0" style="background-color:#CCCCCC">
							<tr><td align="left" width="220">基站名称</td>
							<td align="left" width="80">电量(度)</td>
							<td align="left" width="100">基站标植(度)</td>
							<td align="left" width="100">超标幅度</td>
							</tr>
						</table>
						</td>
						</tr>
						<tr>
							<td  align="left" style="padding:0px 0px; height:160px" valign="top">
								<marquee id="go1" onMouseOver=go1.stop(); onMouseOut=go1.start(); style="WIDTH: 100%; HEIGHT: 150px" scrollamount="2" direction="up" >
									<table width="500"  border="0" cellpadding="1" cellspacing="0">
									<% 
										DianLiangManage dianliang = new DianLiangManage();
										DecimalFormat df1 = new DecimalFormat("0.00");
										ArrayList<AmmeterdegreesBean> ammertdegreeList = dianliang.searchDianLiangDaoru(curpage, loginName, loginId,whereStr);
										if(ammertdegreeList.size()>0){
										for(int i=0;i<ammertdegreeList.size();i++){
											AmmeterdegreesBean ammertdegree = ammertdegreeList.get(i);
											String ammertId = ammertdegree.getAMMETERDEGREEID();
											String alertState = ammertdegree.getElectricffess().getCB_ALERT();
											Double dl = Double.parseDouble(ammertdegree.getBLHDL());
											Double bg = Double.parseDouble(ammertdegree.getElectricffess().getBG());
											Double cbbl = 0.0;
											if(bg==0){
												
											}else{
												cbbl = ((dl-bg)/bg)*100;
											}
											 
											int intnum = pagesize*(curpage - 1) + 1;
									%>
										<tr align="center">
											<td align="left" width="220"><a href="SiteCbYj.jsp?ammertId=<%=ammertId%>"><%=ammertdegree.getDianbiao().getZhandian().getJZNAME() %></a></td>
											<td align="left" width="80"><%=df1.format(dl)%></td>
											<td align="left" width="100"><%=df1.format(bg)%></td>
											<td align="left" width="100"><%=cbbl%></td>
										</tr>
									<%		
										}
										}else{
									%>
										<tr align="center">
											<td colspan="4"><font size="5" color="blue">无报警消息</font></td>
										</tr>
										<%} %>
									</table>
								</marquee >
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td>
		<td >
			<div class="content01" style="padding:2px 2px;width:500px;height:220px ">
				<div class="tit01"><%=year%>年<%=month%>月基站抄表提醒</div>
				<div class="content01_1" style="padding:0px 0px;width:500px">
					
					<table width="500" border="0" cellpadding="0" cellspacing="0" class="tb_select" >
					       <tr><td align="left" style="padding:0px 0px; ">
					       <table width="100%"  border="0" cellpadding="1" cellspacing="0" style="background-color:#CCCCCC">
							<td align="left" width="220">基站名称</td>
							<td align="left" width="80">抄表员</td>
							<td align="left" width="100">联系电话</td>
							<td align="left" width="100">预定抄表日期</td>
						</table>
						</td>
						
						<tr>
							<td  align="left" style="padding:0px 0px; height:160px" valign="top">
								<marquee id="go2" onMouseOver=go2.stop(); onMouseOut=go2.start(); style="WIDTH: 100%; HEIGHT: 150px" scrollamount="2" direction="up" >
									<table width="500"  border="0" cellpadding="1" cellspacing="0">
									<% 
										 SiteManage bean = new SiteManage();
       	 								 ArrayList fylist = new ArrayList();
        								 fylist = bean.getCbjihua1(curpage,loginName,loginId,whereStr);
       									 DecimalFormat df11 = new DecimalFormat("0.00");
       	 								 allpage=bean.getAllPage();
       	
										 String id="",jzname="",accountname="",cbjhrq="",mobile="";
										 Double cbbl = 0.0;
										 int intnum = pagesize*(curpage-1)+1;
		
		  								 if(fylist!=null)
										{
											int fycount = ((Integer)fylist.get(0)).intValue();
											for( int k=fycount; k<fylist.size()-1; k+=fycount){
	       										id = (String) fylist.get(k+ fylist.indexOf("ID"));
	       										accountname = (String) fylist.get(k+ fylist.indexOf("NAME"));
	       										jzname = (String) fylist.get(k+ fylist.indexOf("JZNAME"));
	       										mobile = (String) fylist.get(k+ fylist.indexOf("MOBILE"));
	       										cbjhrq = (String) fylist.get(k+ fylist.indexOf("CBJHRQ"));
	       										//System.out.println("id="+id+", name="+accountname+", jzname="+jzname+",mobile="+mobile+", cbjhrq="+cbjhrq);
										%>
										<tr align="center">
											<td align="left" width="220"><a href="SiteCbYj.jsp?ammertId=<%=id%>"><%=jzname%></a></td>
											<td align="left" width="100"><%=accountname%></td>
											<td align="left" width="100"><%=mobile%></td>
											<td align="left" width="100"><%=cbjhrq%></td>
										</tr>  
										<%		
											}
											}else{
										%>
										<tr align="center">
											<td colspan="4"><font size="5" color="blue">无报警消息</font></td>
										</tr>
										<%} %>
									</table>
								</marquee >
							</td>
						</tr>
						
					</table>
				</div>
			</div>
		</td>
	--%></tr>
	
	
	
	
	</table>
</body>
<%--

<script type="text/javascript">
var arr_shi_js=new Array();
<%
	for(int j=0;j<x_shi.size();j++){
		String s = x_shi.get(j);
		%>
		arr_shi_js[<%=j%>]='<%=s%>';
		<%
	}
%>

var arr_dl_js=new Array();
<%
	for(int j=0;j<y_dl.size();j++){
		String s = y_dl.get(j);
		%>
		arr_dl_js[<%=j%>]='<%=s%>';
		<%
	}
%>

var arr_am_js=new Array();
<%
	for(int j=0;j<y_am.size();j++){
		String s = y_am.get(j);
		%>
		arr_am_js[<%=j%>]='<%=s%>';
		<%
	}
%>

var arr_fm_js=new Array();
<%
	for(int j=0;j<y_fm.size();j++){
		String s = y_fm.get(j);
		%>
		arr_fm_js[<%=j%>]='<%=s%>';
		<%
	}
%>

    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart'));
    var options={
        //定义一个标题
        title:{
            text:''
        },
        legend:{
            data:['电量(度)','总金额(元)','税额(元)']
        },
        tooltip : {//鼠标悬浮弹窗提示  
        	 trigger : "axis",
               show:true,  
               showDelay: 0,  
               hideDelay: 0,  
              transitionDuration:0,   
              backgroundColor : '#000',  
              borderColor : '#4ad2ff',  
              borderRadius : 8,  
              borderWidth: 2,  
              padding: 10,    // [5, 10, 15, 20]  
             
         },  
        //X轴设置
        xAxis:{
            data:arr_shi_js,
			        axisLabel: {                      // x轴的字体样式
			            show: true,
			            textStyle: {
			            fontSize:12,          // xAxis fontSize                                 
			                color: '#000000'
			            }
			        }
			
        },
        yAxis:{
        	  axisLabel: {
                  show: true,
                  textStyle: {
                	  fontSize:12,          //  fontSize                                 
		                color: '#000000'
                  }
             }
        },
        //name=legend.data的时候才能显示图例
        series:[
                {
            name:'电量(度)',
            type:'bar',
            data:arr_dl_js,
          //  barWidth: 30,
            itemStyle:{
                normal:{
                    color:'#4ad2ff'
                }
            },
        },
        {
            name:'总金额(元)',
            type:'bar',
            data:arr_am_js,
          //  barWidth: 30,
            itemStyle:{
                normal:{
                    color:'#7FFFD4'
                }
            },
        },
        {
            name:'税额(元)',
            type:'bar',
            data:arr_fm_js,
          //  barWidth: 30,
            itemStyle:{
                normal:{
                    color:'#00CD66'
                }
            },
        }
       ]

    };
    myChart.setOption(options);
</script>
--%>

<script type="text/javascript">
var arr_shi_js=new Array();
<%
	for(int j=0;j<x_shi.size();j++){
		String s = x_shi.get(j);
		%>
		arr_shi_js[<%=j%>]='<%=s%>';
		<%
	}
%>

var arr_month_js=new Array();
<%
	for(int j=0;j<y_month.size();j++){
		String s = y_month.get(j);
		%>
		arr_month_js[<%=j%>]='<%=s%>';
		<%
	}
%>

var arr_year_js=new Array();
<%
	for(int j=0;j<y_year.size();j++){
		String s = y_year.get(j);
		%>
		arr_year_js[<%=j%>]='<%=s%>';
		<%
	}
%>


    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart'));
    var options={
        //定义一个标题
        title:{
            text:''
        },
        legend:{
            data:['当期平均PUE','当年平均PUE']
        },
        tooltip : {//鼠标悬浮弹窗提示  
        	 trigger : "axis",
               show:true,  
               showDelay: 0,  
               hideDelay: 0,  
              transitionDuration:0,   
              backgroundColor : '#000',  
              borderColor : '#4ad2ff',  
              borderRadius : 8,  
              borderWidth: 2,  
              padding: 10,    // [5, 10, 15, 20]  
             
         },  
        //X轴设置
        xAxis:{
            data:arr_shi_js,
			        axisLabel: {                      // x轴的字体样式
			            show: true,
			            textStyle: {
			            fontSize:12,          // xAxis fontSize                                 
			                color: '#000000'
			            }
			        }
			
        },
        yAxis:{
        	  axisLabel: {
                  show: true,
                  textStyle: {
                	  fontSize:12,          //  fontSize                                 
		                color: '#000000'
                  }
             }
        },
        //name=legend.data的时候才能显示图例
        series:[
                {
            name:'当期平均PUE',
            type:'bar',
            data:arr_month_js,
          //  barWidth: 30,
            itemStyle:{
                normal:{
                    color:'#4ad2ff'
                }
            },
        },
        {
            name:'当年平均PUE',
            type:'bar',
            data:arr_year_js,
          //  barWidth: 30,
            itemStyle:{
                normal:{
                    color:'#7FFFD4'
                }
            },
        }
       ]

    };
    myChart.setOption(options);
</script>
</html>
