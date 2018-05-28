<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet,java.util.List"%>
<%@ page import="java.text.*"%>
<%@page import="com.noki.query.basequery.javabean.StationPointQueryBean,com.noki.mobi.common.CommonBean"%>
<%@ page import="com.noki.tongjichaxu.*" %>
<%
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
    String bzw = request.getParameter("bzw")!=null?request.getParameter("bzw"):"";;
  	String cc =request.getParameter("wen1")!=null?request.getParameter("wen1"):"";
    
    String entryTime1       = request.getParameter("entryTime1") != null ? request.getParameter("entryTime1") : "";
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";

	int intnum=0;
	String color="";
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        String loginName = account.getAccountName();
        String loginId1 = request.getParameter("loginId");
        
        String bgsign = request.getParameter("bgsign")!=null?request.getParameter("bgsign"):"-1";
        String gsf = request.getParameter("gsf")!=null?request.getParameter("gsf"):"0";
        
        String sheng = (String)session.getAttribute("accountSheng");
        String agcode1="";
    	if(request.getParameter("shi")==null){
    		ArrayList shilist = new ArrayList();
    		CommonBean commBean = new CommonBean();
    		shilist = commBean.getAgcode(sheng,account.getAccountId());
			if(shilist!=null){
       		int scount = ((Integer)shilist.get(0)).intValue();
            agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
          }
    	} 
		String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
		String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
		String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
         String sptype = request.getParameter("sptype")!=null?request.getParameter("sptype"):"0";
         String rgshzt1=request.getParameter("manualauditstatus")!=null?request.getParameter("manualauditstatus"):"-1";
         String gdfs=request.getParameter("gdfs")!=null?request.getParameter("gdfs"):"-1";
         String chanquan = request.getParameter("chanquan");
         String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"0";
         String fwlx = request.getParameter("fwlx")!=null?request.getParameter("fwlx"):"0";
         String htmlsql = request.getParameter("htmlsql");
         String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"请选择";
         String zdlx1=request.getParameter("zdlx1")!=null?request.getParameter("zdlx1"):"";
         String entrytimeQS    = request.getParameter("entrytimeQS")!=null?request.getParameter("entrytimeQS"):"";//开始录入时间
         String entrytimeJS    = request.getParameter("entrytimeJS")!=null?request.getParameter("entrytimeJS"):"";//结束录入时间         
         String sydateks=request.getParameter("sydateks")!=null?request.getParameter("sydateks"):"";//投入使用开始日期
         String sydatejs=request.getParameter("sydatejs")!=null?request.getParameter("sydatejs"):"";//投入使用结束日期
         String entrypensonnel = request.getParameter("entrypensonnel")!=null?request.getParameter("entrypensonnel"):"";//录入人员
         String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((sptype!=null)&&(chanquan!=null))||(fwlx!=null)||(zdlx!=null)||(htmlsql!=null)||(zdname!=null)||(rgshzt1!=null)||(entrypensonnel!=null)||(entryTime1!=null)||(gdfs!=null)||((entrytimeQS!=null)&&(entrytimeJS!=null))){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&sptype="+sptype+"&chanquan="+chanquan+"&fwlx="+fwlx+"&htmlsql="+htmlsql+"&zdname="+zdname+"&zdlx="+zdlx+"&rgshzt1="+rgshzt1+"&entrypensonnel="+entrypensonnel+"&entryTime1="+entryTime1+"&gdfs="+gdfs+"&beginTimeQ="+entrytimeQS+"&endTimeQ="+entrytimeJS;
     }
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");

%>

<html>
<head>
<title>

</title>
<style>
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
.selected_font{
		width:110px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}

.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
#div1{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div1 p{ float:left;font-size:12px; width:110px; cursor:pointer;}
#box,#box2,#box3,#box4{padding:0px;border:1px;}
.bttcn{color:BLACK;font-weight:bold;}
 </style>
<style type="text/css" media=print>.noprint{display : none }
a {text-decoration:none}
</style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script >

var oCalendarEnny=new PopupCalendarny("oCalendarEnny");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();


var oCalendarChsny=new PopupCalendarny("oCalendarChsny");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChsny.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChsny.oBtnTodayTitle="确定";
oCalendarChsny.oBtnCancelTitle="取消";
oCalendarChsny.Init();
</script>
<script type="text/javascript">
//=点击展开关闭效果=

function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
var openTip = oOpenTip || "";
var shutTip = oShutTip || "";
if(targetObj.style.display!="none"){
   if(shutAble) return;
   targetObj.style.display="none";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = shutTip; 
   }
} else {
   targetObj.style.display="block";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = openTip; 
   }
}
}
</script>
<script >

var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();


var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init();
</script>
<script language="javascript">
var path = '<%=path%>';
function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

		}
		else
		{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
}

  function chaxun(){
		if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
		
		}else{
        	document.form1.action=path+"/web/query/zhandianchaxun/zidongshenhe.jsp?bzw=1";
	     document.form1.submit();
	     }
	}
	function delLogs(){
		var beginTime = document.form1.delBeginTime.value
                var endTime = document.form1.delEndTime.value

                if(checkNotnull(document.form1.delBeginTime,"开始时间")&&
                   checkNotnull(document.form1.delEndTime,"结束时间")){
                      if(beginTime>endTime){
                 	alert("开始时间不能大于结束时间！")
                         return
               		 }
                      if(confirm("确定要删除，不可恢复！")){
                 	  document.form1.action=path+"/servlet/log?delBeginTime="+beginTime+"&delEndTime="+endTime
                           document.form1.submit()
               		 }
                }

	}
	
		function payfee(degreeid){
          document.form1.action=path+"/web/dianliang/payElectricFees.jsp?degreeid="+degreeid;
          document.form1.submit();
        }
	function adddegree(){
          document.form1.action=path+"/web/dianliang/addDegree.jsp";
          document.form1.submit();
        }
    function delad(ammeterdegreeid){
       if(confirm("您确定删除此电量信息？")){
            document.form1.action=path+"/servlet/ammeterdegree?action=del&degreeid="+ammeterdegreeid
        	document.form1.submit()
       }
    }
    function modifyad(ammeterdegreeid){
         document.form1.action=path+"/web/dianliang/modifyDegree.jsp?degreeid="+ammeterdegreeid;
         document.form1.submit();   
    }
	function checkWenti(){//获取多选框的value
		var wen = document.getElementsByName("wenti");
		var b=document.getElementById("wen");
		b.value="";
		for(var i = 0;i<wen.length;i++){
			if(wen[i].checked){
				b.value+=wen[i].value+",";//将选中的value拼接成字符串放到隐藏域的value中	
			}
		}
	}
    function queryDegree(){
                     
                    if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null)
		          {
	                   alert("城市不能为空");
	   
	           }else{
		           	var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
		   			var et = document.form1.entryTime1.value;
					if(reg1.exec(et)||et==""||et==null){
	                   document.form1.action=path+"/web/query/basequery/stationPointQuery.jsp?command=chaxun";
	                   document.form1.submit();
	                }else{
		        		alert("您输入的录入月份有误，请确认后重新输入！");
		        	}
               }
       
    }
    function getValue(va,sql){
       var general =document.getElementById("general");
       var htmlsql =document.getElementById("htmlsql");
       general.value=va;
       htmlsql.value = sql;    
    }  
   //页面载入方法
    function op(){
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian&tab=jz&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
    }
   $(function(){

	$("#query").click(function(){
		chaxun();
	});
	$("#daochuBtn").click(function(){
		exportad();
	});
	$("#dayinBtn").click(function(){
		printad();
	});
	$("#queding").click(function(){
		queding();		
	});
	$("#quxiao").click(function(){
		quxiao();		
	});
});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0501");

%>
<body  class="body">
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr >
				<td colspan="4" width="50" >
					<div style="width:700px;height:50px">
						<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">自动审核分析</span>	
					</div>
				</td>
		    </tr>	    	
	    	<tr>
	    		<td height="20" colspan="4" >
   					<div id="parent" style="display:inline">
                   		<div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div>
	    		</td>
	    	</tr>
			<tr>
				<td width="1200px">
					<table>
						<tr class="form_label">
		    				<td >城市：</td>
		    				<td ><select name="shi" class="selected_font" onchange="changeCity()">
	         					<option value="0">请选择</option>
	         		<%
			         	ArrayList shilist = new ArrayList();
			         	shilist = commBean.getAgcode(sheng,account.getAccountId());
			         	if(shilist!=null){
			         		String agcode="",agname="";
			         		int scount = ((Integer)shilist.get(0)).intValue();
			         		for(int i=scount;i<shilist.size()-1;i+=scount)
		                    {
		                    	agcode=(String)shilist.get(i+shilist.indexOf("AGCODE"));
		                    	agname=(String)shilist.get(i+shilist.indexOf("AGNAME"));
		                    %>
		                    <option value="<%=agcode%>"><%=agname%></option>
		                    <%}
			         	}
			         %>
	         		</select><span class="style1">&nbsp;*</span></td>
	         		<td>区县：</td><td> <select name="xian" class="selected_font" onchange="changeCountry()">
	         		<option value="0">请选择</option>
	         		 <%
			         	ArrayList xianlist = new ArrayList();
			         	xianlist = commBean.getAgcode(shi,account.getAccountId());
			         	if(xianlist!=null){
			         		String agcode="",agname="";
			         		int scount = ((Integer)xianlist.get(0)).intValue();
			         		for(int i=scount;i<xianlist.size()-1;i+=scount)
		                    {
		                    	agcode=(String)xianlist.get(i+xianlist.indexOf("AGCODE"));
		                    	agname=(String)xianlist.get(i+xianlist.indexOf("AGNAME"));
		                    %>
		                    <option value="<%=agcode%>"><%=agname%></option>
		                    <%}
			         	}
			         %>
	         		</select></td>
					
					<td >起始月份：</td>
		    		<td >
						<input type="text" name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font"/>
					</td>
					<td >结束月份：</td>
					<td>
	         			<input type="text" name="endTime" value="<%if(null!=request.getParameter("endTime")) out.print(request.getParameter("endTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font"/>
	         		</td>
					<td>站点名称：</td>
	         			<td><input type="text" name="zdname" value="<%if (null != request.getParameter("zdname"))out.print(request.getParameter("zdname"));%>" class="selected_font" /></td>
	         			<td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                     </div>
								</font>
							</p>
						</td>
	         		<td><%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
	               <div id="query" style="position:relative;width:60px;height:23px;cursor: pointer;left:50px;float:right;top:0;">
			       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
			       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 			       
					</div>
			       <%}%></td>
					
					</tr>
					</table>
					</td>
					</tr>
					
				
			      <tr>
			     <td colspan="5">
					<div style="width:100%;" > 
					  <p id="box3" style="display:none">
					   <table>
					     <tr class="form_label">
						  <td><input type="checkbox" name="wenti" value="1" onClick="checkWenti()" />票据为空</td>
						  <td><input type="checkbox" name="wenti" value="2" onClick="checkWenti()" />用电费用调整没有说明</td>
						  <td><input type="checkbox" name="wenti" value="3" onClick="checkWenti()" />本次单价与系统单价不符</td>
						  <td><input type="checkbox" name="wenti" value="4" onClick="checkWenti()"/>本次电量上下浮动超过站点额定耗电量</td>
	         			  <td><input type="checkbox" name="wenti" value="5" onClick="checkWenti()" />费用调整无说明</td>
	         			  <td><input type="checkbox" name="wenti" value="6" onClick="checkWenti()" />平均日耗电量大于上次日均量</td>
						  <td><input type="checkbox" name="wenti" value="7" onClick="checkWenti()" />上次电表读数与最后抄表数不一致</td>
					     </tr>
					  </table>
					</p>
				   </div>
				</td>
			  </tr>
		   </table>			       
		<table  height="23">
			<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;错误比例及每月趋势&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
		</table>
		<% 
			if("1".equals(bzw)){
				String p1="";String p2="";String p3="";
				String p4="";String p5="";String p6="";String p7="";	
				String[]c = cc.split(",");	//得到一个String数组,分割页面跳转后得到多选框的值
				System.out.println(c);
				for(String c1:c){//
					if("1".equals(c1)){//遍历String数组,并判断
						p1="票据为空";
					}else if("2".equals(c1)){
						p2="用电费用调整没有说明";
					}else if("3".equals(c1)){
						p3="本次单价与系统单价不符";
					}else if("4".equals(c1)){
						p4="本次电量上下浮动超过站点额定耗电量";					
					}else if("5".equals(c1)){
						p5="费用调整无说明";
					}else if("6".equals(c1)){
						p6="平均日耗电量大于上次日均量";
					}else if("7".equals(c1)){
						p7="上次电表读数与最后抄表数不一致";					
					}
				}
		     	String whereStr = "";
		     	String Str="";
				if (shi != null && !shi.equals("") && !shi.equals("0")) {
					whereStr = whereStr + " and zd.shi='" + shi + "'";
					}
					if (xian != null && !xian.equals("") && !xian.equals("0")) {
					whereStr = whereStr + " and zd.xian='" + xian + "'";
					}
					if (beginTime != null && !beginTime.equals("") && !beginTime.equals("0")) {
					whereStr = whereStr + " and to_char(ad.startmonth,'yyyy-mm')>='" + beginTime + "'";
					}
					if (endTime != null && !endTime.equals("") && !endTime.equals("0")) {
					whereStr = whereStr + " and to_char(ad.endmonth,'yyyy-mm')<='" + endTime + "'";
					}
					if (zdname != null && !zdname.equals("") && !zdname.equals("0")) {
					whereStr = whereStr + " and zd.jzname like'" + zdname + "'";
					}
					if (p1 != null && !p1.equals("") && !p1.equals("0")) {
					Str = Str + " or ef.autoauditdescription like '%" + p1 + "%'";
					}
					if(p2 != null && !p2.equals("") && !p2.equals("0")){
					Str = Str + " or ef.autoauditdescription like '%" + p2 + "%'";
					}
					if(p3 != null && !p3.equals("") && !p3.equals("0")){
					Str = Str + " or ef.autoauditdescription like '%" + p3 + "%'";
					}
					if(p4 != null && !p4.equals("") && !p4.equals("0")){
					Str = Str + " or ad.autoauditdescription like '%" + p4 + "%'";
					}
					if(p5 != null && !p5.equals("") && !p5.equals("0")){
					Str = Str + " or ad.autoauditdescription like '%" + p5 + "%'";
					}
					if(p6 != null && !p6.equals("") && !p6.equals("0")){
					Str = Str + " or ad.autoauditdescription like '%" + p6 + "%'";
					}
					if(p7 != null && !p7.equals("") && !p7.equals("0")){
					Str = Str + " or ad.autoauditdescription like '%" + p7 + "%'";
					}	
					
					FeixiDao dao = new FeixiDao();
					List<FeixiBean> list = dao.getFX(whereStr,Str);
			%>
			
		<table width="100%" border="0" cellspacing="1" cellpadding="1">
			<tr>
			<td height="200px" >
		<%
			//饼形图的输出
			String wenti="";
			String wenti1="";
			int coun=0;int coun1=0;int coun2=0;
			int coun3=0;int coun4=0;int coun5=0;int coun6=0;
				for(FeixiBean f:list){
       			wenti = f.getWenti();//得到关于电费自动审核描述
       			wenti1 = f.getWen();//得到关于电量自动审核描述
       			String[] ss =wenti.split(";");
				String[] s1 = wenti1.split(";");
				for(String s:ss){
       			System.out.println(s);
					if(s.contains("票据为空！")){
						coun++;
					}else if(s.contains("用电费用调整没有说明！")){
						coun1++;
					}else if(s.contains("本次单价与系统单价不符！")){
						coun2++;
					}		
				}
				for(String s2:s1){
					System.out.println(s2+"..");
					if(s2.contains("本次电量上下浮动超过站点额定耗电量")){
						coun3++;
					}else if(s2.contains("费用调整无说明")){
						coun4++;
					}else if(s2.contains("平均日耗电量大于上次日均量")){
						coun5++;
					}else if(s2.contains("上次电表读数与最后抄表数不一致")){
						coun6++;
					}
				}
			}
					
				String hStr = coun + ";" + coun1 + ";" + coun2 + ";" + coun3 + ";" + coun4 + ";" + coun5 + ";" + coun6 + ";";
				String str = "票据为空;用电费用调整没有说明;本次单价与系统单价不符;本次电量上下浮动超过站点额定耗电量;费用调整无说明;平均日耗电量大于上次日均量;上次电表读数与最后抄表数不一致";
				%>	
       		<div>
			<img src="<%=path%>/servlet/huanbi_zd?cityStr=<%=str%>&dataStr=<%=hStr%>&bz=3">
			</div>
			</td>
			
			<td height="200px"></td>
		</tr>
		
		<tr>
			<td>  
				<table width="100%" height="200px" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr>
						<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" >站点ID</div></td>
        				<td width="14%" height="23" bgcolor="#DDDDDD"><div align="center" >站点名称</div></td>        
         				<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" >区县</div></td>
						<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" >缴费日期</div></td>
						<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" >本月起始日期</div></td>
						<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" >本月结束日期</div></td>
						<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center">起始电表数</div></td>
						<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center">终止电表数</div></td>
						<td width="38%" height="23" bgcolor="#DDDDDD"><div align="center">问题描述</div></td>
					</tr> <%
							String jn="";
							String zdid="";
							String xq="";
							String jf="";
							String time1="";
							String time2="";
							String bd1="";
							String bd2="";
							String wen1="";
							for(FeixiBean f:list){
							zdid=f.getZdid();
							jn=f.getJzname();
							xq=f.getQuxian();System.out.println(xq+"..."+jf);
							jf=f.getJftime();
							time1=f.getBybegin();
							time2=f.getByend();
							bd1=f.getDBbegin();
							bd2=f.getDBend();
							wen1=f.getWenti();String wen2=f.getWen();
							if(zdid==null||zdid.equals("")||zdid.equals("null")||zdid.equals(" ")){zdid="";}
							if(jn==null||jn.equals("")||jn.equals("null")||jn.equals(" ")){jn="";}
							if(jf==null||jf.equals("")||jf.equals("null")||jf.equals(" ")){jf="";}
							if(xq==null||xq.equals("")||xq.equals("null")||xq.equals(" ")){xq="";}
							if(time1==null||time1.equals("")||time1.equals("null")||time1.equals(" ")){time1="";}
							if(time2==null||time2.equals("")||time2.equals("null")||time2.equals(" ")){time2="";}
							if(bd1==null||bd1.equals("")||bd1.equals("null")||bd1.equals(" ")){bd1="";}
							if(bd2==null||bd2.equals("")||bd2.equals("null")||bd2.equals(" ")){bd2="";}
							
							if(intnum%2==0){
						   		color="#FFFFFF";
							}else{
						  		color="#DDDDDD" ;
							}
			           		intnum++;
					
					%>
					<tr bgcolor="<%=color%>">
						<td width="6%" ><div align="center" ><%=zdid %></div></td>
        				<td width="14%" ><div align="center" ><%=jn %></div></td>        
         				<td width="8%" ><div align="center" ><%=xq %></div></td>
						<td width="6%" ><div align="center" ><%=jf %></div></td>
						<td width="8%" ><div align="center" ><%=time1 %></div></td>
						<td width="8%" ><div align="center" ><%=time2 %></div></td>
						<td width="6%" ><div align="center" ><%=bd1 %></div></td>
						<td width="6%" ><div align="center" ><%=bd2 %></div></td>
						<td width="38%" ><div align="center" ><%=wenti %><%=wenti1 %></div></td>
					</tr>
					<%}} %>
				</table></td>
		</tr>
	</table> 	
			

	
			
			<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >  	
	</div>
			<div style="width:100%;height:8%;border:1px" >	
			<table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
			
 
  		<tr>  		
   			<td align="right">   			
                               <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                      
                                <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px; " >
	 								 <img src="<%=path %>/images/daoru.png" width="100%" height="100%">
									 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
								</div>
                               <%} %> 
                           </td>  
  					</tr>
  <tr>
  <td>
  <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
  <input type="hidden" name="sptype2" id="sptype2" value=""/>
  <input type="hidden" name="chanquan2" id="chanquan2" value=""/>
  <input type="hidden" name="fwlx2" id="fwlx2" value=""/>
  <input type="hidden" value="" id="htmlsql" name="htmlsql" />
  <input type="hidden" name="bgsign2" id="bgsign2" value=""/>
  <input type="hidden" name="zdlx1" id="zdlx1" value=""/>
  <input type="hidden" name="ccz" id="ccz" value="<%=zdlx1%>"/>
  <input type="hidden" id="wen" name="wen1" value="" />
  </td>
  </tr>
</table>
			</div>
		</form>
	</body>
	<script language="javascript">
	var path = '<%=path%>';
	function gopage()
     {
      document.form1.submit();
     }
     function previouspage()
     {
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage-1)%>';
      		document.form1.action=path+"/web/query/basequery/stationPointQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/query/basequery/stationPointQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/query/basequery/stationPointQuery.jsp?curpage="+pageno+"<%=canshuStr%>";
      document.form1.submit();
     }
     </script>
     

<script type="text/javascript">
<!--
var XMLHttpReq;
	//XMLHttpReq = createXMLHttpRequest();
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	function sendRequest(url,para) {

		createXMLHttpRequest();
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="sheng"){
			XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
	}
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		//alert(XMLHttpReq.status);
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
            	var res = XMLHttpReq.responseText;
              window.alert(res);
             
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    
function processResponse_sheng() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            	
            	//var res = XMLHttpReq.responseText;
            
              updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateQx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZd(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}

function changeSheng(){
	var sid = document.form1.sheng.value;
	document.form1.sheng2.value=document.form1.sheng.value;
	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeCity(){
	var sid = document.form1.shi.value;
	document.form1.shi2.value=document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function changeCountry(){
	var sid = document.form1.xian.value;
	document.form1.xian2.value=document.form1.xian.value;
	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

//-->
</script>
<script type="text/javascript">
window.onload=function()
{
	var oDiv=document.getElementById('div1');
	var oUl=oDiv.getElementsByTagName('ul')[0];
	var oP=oDiv.getElementsByTagName('p')[0];
	var bSwitch=false;
	
	oP.onclick=function()
	{
		if(bSwitch){
			oUl.style.display='none';
			bSwitch=false;
		}else{
			var obj=document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
			var zdlx2 = document.form1.zdlx1.value;
			if(zdlx2!=""&&zdlx2!=null){
				var zdlx3 = zdlx2.split(",");
				for(var i=0;i<obj.length;i++){
					var m = obj[i].value.toString().indexOf(",");
					var bm = obj[i].value.toString().substring(0,m);
					for(var j=0;j<zdlx3.length;j++){
						var zdlx4 = zdlx3[j].toString().substring(1,zdlx3[j].length-1);
						if(bm==zdlx4){
							obj[i].checked = true; 					
						}
					} 
		 
				} 
			} 
			oUl.style.display='block';
			bSwitch=true;
		}
	};

};
function queding(){
	var oDiv=document.getElementById('div1');
	var oUl=oDiv.getElementsByTagName('ul')[0];
	var obj=document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
	var str1="";
	var str2="";
	for(var i=0;i< obj.length;i++){
		if(obj[i].checked){
			var m = obj[i].value.toString().indexOf(",");
			var bm = obj[i].value.toString().substring(0,m);
		    var mc = obj[i].value.toString().substring(m+1,obj[i].value.toString().length);
			str1 = str1+"'"+bm+"',";
			str2 = str2+mc+",";
		}
	}
	str1=str1.substring(0,str1.length-1);
	str2=str2.substring(0,str2.length-1);
	document.form1.zdlx1.value=str1;
	document.form1.zdlx.value=str2;
	oUl.style.display='none';
}
//取消选中   
function quxiao(){ 
	var oDiv=document.getElementById('div1');
	var oUl=oDiv.getElementsByTagName('ul')[0];  
	var obj = document.getElementsByName("CheckboxGroup1");
	if(obj.length){
		for(var i=0;i<obj.length;i++){ 
			obj[i].checked = false;   
		}
		oUl.style.display='none';   
	}else{   
		obj.checked = false; 
		oUl.style.display='none';   
	}   
}   
document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
</script>

</html>