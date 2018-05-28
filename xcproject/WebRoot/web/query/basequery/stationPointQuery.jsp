<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet,java.util.List"%>
<%@ page import="java.text.*"%>
<%@page import="com.noki.query.basequery.javabean.StationPointQueryBean,com.noki.mobi.common.CommonBean"%>

<%
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
    String entryTime1       = request.getParameter("entryTime1") != null ? request.getParameter("entryTime1") : "";
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	
    String zdqyzt = request.getParameter("zdqyzt")!= null ? request.getParameter("zdqyzt") : "1";
    String sbzw = request.getParameter("sbzw")!= null ? request.getParameter("sbzw") : "0";
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
         String zdsx1 = request.getParameter("zdsx1")!=null?request.getParameter("zdsx1"):"";//站点属性,隐藏域
		 String jzproperty = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"请选择";
         String entrytimeQS    = request.getParameter("entrytimeQS")!=null?request.getParameter("entrytimeQS"):"";//开始录入时间
         String entrytimeJS    = request.getParameter("entrytimeJS")!=null?request.getParameter("entrytimeJS"):"";//结束录入时间         
         String sydateks=request.getParameter("sydateks")!=null?request.getParameter("sydateks"):"";//投入使用开始日期
         String sydatejs=request.getParameter("sydatejs")!=null?request.getParameter("sydatejs"):"";//投入使用结束日期
         String entrypensonnel = request.getParameter("entrypensonnel")!=null?request.getParameter("entrypensonnel"):"";//录入人员
         String caiji = request.getParameter("caiji")!= null ? request.getParameter("caiji") : "-1";//是否采集
         String xuni = request.getParameter("xuni")!= null ? request.getParameter("xuni") : "-1";//是否虚拟
         String beginyue    = request.getParameter("beginyue")!=null?request.getParameter("beginyue"):"";//站点审核月份，起
         String endyue    = request.getParameter("endyue")!=null?request.getParameter("endyue"):"";//站点审核月份，止
         
 		String zdid1 = request.getParameter("zdid1")!=null?request.getParameter("zdid1"):"";//站点ID //2014-4-9新增

         String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((sptype!=null)&&(chanquan!=null))||(fwlx!=null)||(zdlx!=null)||(htmlsql!=null)||(zdname!=null)||(rgshzt1!=null)||(entrypensonnel!=null)||(entryTime1!=null)||(gdfs!=null)||((entrytimeQS!=null)&&(entrytimeJS!=null))){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&sptype="+sptype+"&chanquan="+chanquan+"&fwlx="+fwlx+"&htmlsql="+htmlsql+"&zdname="+zdname+"&zdlx="+zdlx+"&rgshzt1="+rgshzt1+"&entrypensonnel="+entrypensonnel+"&entryTime1="+entryTime1+"&gdfs="+gdfs+"&beginTimeQ="+entrytimeQS+"&endTimeQ="+entrytimeJS;
     }
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    
    System.out.println("dfdfdsfsd"+zdqyzt);
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
		width:130px;
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

#div2{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div2 p{float:left;font-size:12px; width:110px; cursor:pointer;}


#box,#box2,#box3,#box4{padding:0px;border:1px;}
.bttcn{color:BLACK;font-weight:bold;}
 </style>
<style type="text/css" media=print>.noprint{display : none }
a {text-decoration:none}
</style>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
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
			var beginTime = document.form1.beginTime.value
           	if(checkNotnull(document.form1.beginTime,"开始时间")&&checkNotnull(document.form1.endTime,"结束时间")){
		 			document.form1.action=path+"/web/sys/logManage.jsp?beginTime="+beginTime
		            document.form1.submit()
		             showdiv("请稍等..............");
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
    function queryDegree(){
                     
                
		          // 	var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
		   			//var et = document.form1.entryTime1.value;
					//if(reg1.exec(et)||et==""||et==null){
	                   document.form1.action=path+"/web/query/basequery/stationPointQuery.jsp?command=chaxun";
	                   document.form1.submit();
	               // }else{
		        	//	alert("您输入的录入月份有误，请确认后重新输入！");
		        	//}
               
       
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
		queryDegree();
		showdiv("请稍等..........");
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
	$("#queding2").click(function(){
		queding2();		
	});
	$("#quxiao2").click(function(){
		quxiao2();		
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
											  <!--  -->
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点查询</span>	
												</div>
											</td>
	    	        </tr>	    	
	    	<tr><td height="20" colspan="4" >
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
		    		
		    		<td ><select name="shi" id="shi" class="selected_font" onchange="changeCity()">
	         		<option value="">请选择</option>
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
	         		</select></td>
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
					
					
					<td>乡镇：</td><td><select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
	         		<option value="0">请选择</option>
	         		<%
			         	ArrayList xiaoqulist = new ArrayList();
			         	xiaoqulist = commBean.getAgcode(xian,account.getAccountId());
			         	if(xiaoqulist!=null){
			         		String agcode="",agname="";
			         		int scount = ((Integer)xiaoqulist.get(0)).intValue();
			         		for(int i=scount;i<xiaoqulist.size()-1;i+=scount)
		                    {
		                    	agcode=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGCODE"));
		                    	agname=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGNAME"));
		                    %>
		                    <option value="<%=agcode%>"><%=agname%></option>
		                    <%}
			         	}
			         %>
	         		</select></td>
	         		
	         			<td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							</font>
					</p>
				</td>
	         		<td >
							       <div id="query" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
							</td>
					
					</tr>
					</table>
					</td>
					</tr>
					
					
				
			      <tr>
			     <td colspan="5">
					<div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table>
					<tr class="form_label">
					
	         		<!--  <td>集团报表类型：</td>
							<td><select name="sptype" class="selected_font" onchange="javascript:document.form1.sptype2.value=document.form1.sptype.value">
					         		<option value="0">请选择</option>
						         <%
						         	ArrayList sptypelist = new ArrayList();
						         	sptypelist = commBean.getStationPointType();
						         	if(sptypelist!=null){
						         		String sfid="",sfnm="";
						         		int scount = ((Integer)sptypelist.get(0)).intValue();
						         		for(int i=scount;i<sptypelist.size()-1;i+=scount)
					                    {
					                    	sfid=(String)sptypelist.get(i+sptypelist.indexOf("CODE"));
					                    	sfnm=(String)sptypelist.get(i+sptypelist.indexOf("NAME"));
					                    %>
					                    <option value="<%=sfid%>"><%=sfnm%></option>
					                    <%}
						         	}
						         %>
						      </select></td>
						      -->
						      <td>站点名称：</td><td><input type="text" class="selected_font" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" /></td>
						      
						      
                                <td>房屋类型：</td><td><select name="fwlx" class="selected_font" onchange="javascript:document.form1.fwlx2.value=document.form1.fwlx.value">
							         		<option value="0">请选择</option>
								         <%
								         	ArrayList yftypelist = new ArrayList();
								         	yftypelist = commBean.getUseHouseType();
								         	if(yftypelist!=null){
								         		String sfid="",sfnm="";
								         		int scount = ((Integer)yftypelist.get(0)).intValue();
								         		for(int i=scount;i<yftypelist.size()-1;i+=scount)
							                    {
							                    	sfid=(String)yftypelist.get(i+yftypelist.indexOf("CODE"));
							                    	sfnm=(String)yftypelist.get(i+yftypelist.indexOf("NAME"));
							                    %>
							                    <option value="<%=sfid%>"><%=sfnm%></option>
							                    <%}
								         	}
								         %>
         	                 </select></td>
         	                 <!-- 
						 	<td>是否标杆：</td><td><select name="bgsign" class="selected_font" onchange="javascript:document.form1.bgsign2.value=document.form1.bgsign.value">
							         		<option value="-1">请选择</option>
							         		<option value="0">否</option>
							         		<option value="1">是</option>							         		
							      </select></td>
							      -->
						 </tr>
						 
						 <tr class="form_label">
						 	<!-- 
						 	<td>归属方：</td><td><select name="gsf" class="selected_font" >
								         		<option value="0">请选择</option>
								         		<%
									         	ArrayList gsflist = new ArrayList();
								         		gsflist = ztcommon.getSelctOptions("gsf");
									         	if(gsflist!=null){
									         		String code="",name="";
									         		int cscount = ((Integer)gsflist.get(0)).intValue();
									         		for(int i=cscount;i<gsflist.size()-1;i+=cscount)
								                    {
								                    	code=(String)gsflist.get(i+gsflist.indexOf("CODE"));
								                    	name=(String)gsflist.get(i+gsflist.indexOf("NAME"));
								                    %>
								                    <option value="<%=code%>"><%=name%></option>
								                    <%}
									         	}
									         %>
						       </select></td>
						 	 -->
						 	<td>
								站点属性：
							</td>
							<td>
								<div id="div2">
			      					<p><input type="text" class="selected_font" readonly= "readonly" name="jzproperty" value="请选择"/></p>
			      					<ul>
										<%
											ArrayList zdsx = new ArrayList();
											zdsx = ztcommon.getSelctOptions("ZDSX");
											if (zdsx != null) {
												String code = "", name = "";
												int cscount = ((Integer) zdsx.get(0)).intValue();
												for (int i = cscount; i < zdsx.size() - 1; i += cscount) {
													code = (String) zdsx.get(i + zdsx.indexOf("CODE"));
													name = (String) zdsx.get(i + zdsx.indexOf("NAME"));
										%>
										<li><input type="checkbox" name="CheckboxGroup2" value="<%=code%>,<%=name%>" id="CheckboxGroup2" /><%=name%></li>
										<%	}
										  }
										%>
										<li><input type="button" name="queding2" id="queding2"value="确定" /><input type="button" name="quxiao2" id="quxiao2" value="取消" /></li>
									</ul>
                         		</div>
                         	</td>
                         	<td>
								站点类型：
							</td>
							<td>
								<div id="div1">
			      					<p><input type="text" class="selected_font" readonly= "readonly" name="zdlx" value="请选择"/></p>
			      					<ul>
										<%
											ArrayList stationtype = new ArrayList();
											stationtype = "".equals(zdsx1)?null:ztcommon.getZdlx2(zdsx1);
											//stationtype = ztcommon.getZdlx(zdshuxing);
											//stationtype = ztcommon.getSelctOptions("StationType");
											if (stationtype != null) {
												String code = "", name = "";
												int cscount = ((Integer) stationtype.get(0)).intValue();
												int size = stationtype.size() - 1;
												int i;
												for (i = cscount; i < size; i += cscount) {
													code = (String) stationtype.get(i
														 + stationtype.indexOf("CODE"));
													name = (String) stationtype.get(i
														 + stationtype.indexOf("NAME"));
										%>
										<li><input type="checkbox" name="CheckboxGroup1" value="<%=code%>,<%=name%>" id="CheckboxGroup1" /><%=name%></li>
										<%	}
										  }
										%>
										<li><input type="button" name="queding" id="queding"value="确定" /><input type="button" name="quxiao" id="quxiao" value="取消" /></li>
									</ul>
                         		</div>	
							</td>
						 <td>审核状态：</td><td><select name="manualauditstatus" class="selected_font" >         		
			         		<option value="-1">请选择</option>
			         		<option value="1">审核通过</option>
			         		<option value="0">审核未通过</option>  
			         		<option value="2">审核不通过</option>         		
		         			</select></td>
		         			</tr>
						 <tr class="form_label">
		         		<!--  
		         		<td>录入人员：</td><td><input type="text" class="selected_font" name="entrypensonnel" value="<%if(null!=request.getParameter("entrypensonnel")) out.print(request.getParameter("entrypensonnel")); %>" /></td> 
						 
						 	<td>投入使用初始日期：</td>
						 	<td><input type="text" class="selected_font" name="sydateks" value="<%if(null!=request.getParameter("sydateks")) out.print(request.getParameter("sydateks")); %>" 
						 				readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /> </td>
						 	<td>投入使用结束日期：</td>
						 	<td><input type="text" class="selected_font" name="sydatejs" value="<%if(null!=request.getParameter("sydatejs")) out.print(request.getParameter("sydatejs")); %>" 
						 				readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /> </td>						 							 	
						 	<td>录入月份：</td>
							<td><input type="text" name="entryTime1" class="selected_font"  value="<%if (null != request.getParameter("entryTime1")) out.print(request.getParameter("entryTime1"));%>" 
										readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /></td>						 	
						    -->
						    
						     </tr>
						 <tr class="form_label">
						    <td>供电方式：</td>
						    <td width="25%">
					         	<select name="gdfs" style="width:130" title="必填项，根据下拉值选择填写">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList gdfs1 = new ArrayList();
						         	gdfs1 = ztcommon.getSelctOptions("GDFS");
						         	if(gdfs1!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)gdfs1.get(0)).intValue();
						         		for(int i=cscount;i<gdfs1.size()-1;i+=cscount)
					                    {
					                    	code=(String)gdfs1.get(i+gdfs1.indexOf("CODE"));
					                    	name=(String)gdfs1.get(i+gdfs1.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
					         </td>
<%--						    <td><select name="gdfs" class="selected_font" onchange="javascript:document.form1.manauditstatus2.value=document.form1.manauditstatus.value">         		--%>
<%--			         		<option value="-1">请选择</option>--%>
<%--			         		<option value="gdfs01">供电局</option>--%>
<%--			         		<option value="gdfs02">业主</option>  --%>
<%--			         		<option value="gdfs03">其他</option>   --%>
<%--			         		<option value="gdfs04">直供电</option>--%>
<%--			         		<option value="gdfs05">转供电</option>		--%>
<%--		         			</select></td>--%>
						
						
						<!--  
							<td>开始录入时间：</td>
							<td><input type="text" class="selected_font" name="entrytimeQS" value="<%if(null!=request.getParameter("entrytimeQS")) out.print(request.getParameter("entrytimeQS")); %>" 
										readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /></td>                 
							<td>结束录入时间：</td>
							<td><input type="text" class="selected_font" name="entrytimeJS" value="<%if(null!=request.getParameter("entrytimeJS")) out.print(request.getParameter("entrytimeJS")); %>"
							 			 readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /> </td> 
						 	-->
						 	
						 	<td>站点启用状态：</td>
         					<td><select name="zdqyzt" class="selected_font">
         	 				<option value="1">是</option>
         	   				<option value="0">否</option>
         					<option value="-1">请选择</option>
         					</select></td>
						 </tr>
						 <tr class="form_label">
						 <!-- 
						 <td>是否采集站点：</td>
         					<td><select name="caiji" class="selected_font">
         					<option value="-1">请选择</option>
         	 				<option value="1">是</option>
         	   				<option value="0">否</option>
         					</select>
         					</td>
         					
         					
         					<td>是否虚拟站点：</td>
         					<td><select name="xuni" class="selected_font">
         					<option value="-1">请选择</option>
         	 				<option value="1">是</option>
         	   				<option value="0">否</option>
         					</select>
         					</td>
         					<td>站点审核月份：</td>
   		    				<td><input type="text" name="beginyue" class="selected_font"  value="<%if (null != request.getParameter("beginyue")) out.print(request.getParameter("beginyue"));%>"
   		    				   			 readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /></td>
							<td>至</td>
							<td><input type="text" name="endyue" class="selected_font"  value="<%if (null != request.getParameter("endyue")) out.print(request.getParameter("endyue"));%>" 
									     readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /></td>
						 -->
						 </tr>
						 <tr class="form_label">
						 	<td>站点ID：</td>
                   			<td>
                   				<input type="text" name="zdid1" class="selected_font" 
                   					value="<%if(null!=request.getParameter("zdid1")) out.print(request.getParameter("zdid1")); %>"/>
                   			</td>
                          </tr>
					</table>
			</p>
					</div>
				</td>
				</tr>
				
					</table>			       
<table  height="23">
<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>
			
       <%
         String whereStr = "";
         String str="";
         StationPointQueryBean bean = new StationPointQueryBean();
			if(shi != null && !shi.equals("")&& !shi.equals("0")){
				whereStr=whereStr+" AND SHI='"+shi+"'";
				str=str+" AND SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("")&& !xian.equals("0")){
				whereStr=whereStr+" AND XIAN='"+xian+"'";
				str=str+" AND XIAN='"+xian+"'";
			}
			if(sptype != null && !sptype.equals("")&& !sptype.equals("0")){
				whereStr=whereStr+" AND JZTYPE='"+sptype+"'";
				str=str+" AND JZTYPE='"+sptype+"'";
			}
			if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
				whereStr=whereStr+" AND JZ.JZNAME LIKE '%"+zdname+"%'";
				
			}
			//录入人员
			if(entrypensonnel != null && !entrypensonnel.equals("")&& !entrypensonnel.equals("0")){
				whereStr=whereStr+" AND (JZ.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"+entrypensonnel+"'))";
				str=str+" AND (JZ.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"+entrypensonnel+"'))";
			}
			if(fwlx != null && !fwlx.equals("")&& !fwlx.equals("0")){
				whereStr=whereStr+" AND YFLX='"+fwlx+"'";
				str=str+" AND YFLX='"+fwlx+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
				whereStr=whereStr+" AND XIAOQU='"+xiaoqu+"'";
				str=str+" AND XIAOQU='"+xiaoqu+"'";
			}
			
			if(bgsign != null && !bgsign.equals("")&& !bgsign.equals("-1")){
				whereStr=whereStr+" AND JZ.BGSIGN='"+bgsign+"'";
				str=str+" AND JZ.BGSIGN='"+bgsign+"'";
			}
			if(gsf != null && !gsf.equals("")&& !gsf.equals("0")){
				whereStr=whereStr+" AND JZ.GSF= '"+gsf+"'";
				str=str+" AND JZ.GSF= '"+gsf+"'";
			}
			if(zdlx!=null&&!zdlx.equals("")&& !zdlx.equals("0")&&!zdlx.equals("请选择")){
			whereStr=whereStr+" AND JZ.STATIONTYPE IN("+zdlx1+")";
			  str=str+" AND JZ.STATIONTYPE IN("+zdlx1+")";
			}
			if(jzproperty!=null&&!jzproperty.equals("")&&!jzproperty.equals("0")&&!jzproperty.equals("请选择")){
				whereStr=whereStr+" AND JZ.property in ("+zdsx1+")";
				str=str+" AND JZ.property in ("+zdsx1+")";
			}
			if(rgshzt1 != null && !rgshzt1.equals("") && !rgshzt1.equals("-1")){
			    if(rgshzt1.equals("0")){
					whereStr=whereStr+" AND (JZ.SHSIGN='"+rgshzt1+"'"+"OR JZ.SHSIGN IS NULL)";
					str=str+" AND (JZ.SHSIGN='"+rgshzt1+"'"+"OR JZ.SHSIGN IS NULL)";
			    }else{
					whereStr=whereStr+" AND JZ.SHSIGN='"+rgshzt1+"'";
					str=str+" AND JZ.SHSIGN='"+rgshzt1+"'";
				}
			}
			if(gdfs != null && !gdfs.equals("")&& !gdfs.equals("-1")&&!gdfs.equals("0")){
			    if(gdfs.equals("0")){
					//whereStr=whereStr+" (AND JZ.GDFS='"+gdfs+"'"+"OR JZ.GDFS IS NULL)";
					//str=str+" (AND JZ.GDFS='"+gdfs+"'"+"OR JZ.GDFS IS NULL)";
			    }else{
					whereStr=whereStr+" AND JZ.GDFS='"+gdfs+"'";
					str=str+" AND JZ.GDFS='"+gdfs+"'";
				}
			}
			if(entrytimeQS != null && !entrytimeQS.equals("") && !entrytimeQS.equals("0")){
				entrytimeQS = entrytimeQS+" 00:00:00";
				whereStr=whereStr+" AND TO_CHAR(JZ.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')>='"+entrytimeQS+"'";
				str=str+" AND TO_CHAR(JZ.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')>='"+entrytimeQS+"'";
			}
			if(entrytimeJS != null && !entrytimeJS.equals("") && !entrytimeJS.equals("0")){
				entrytimeJS = entrytimeJS+" 24:00:00";
				whereStr=whereStr+" AND TO_CHAR(JZ.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')<='"+entrytimeJS+"'";
				str=str+" AND TO_CHAR(JZ.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')<='"+entrytimeJS+"'";
			}
			if(entryTime1 != null && entryTime1 != "" && !entryTime1.equals("0")){
						int months =0;
						int years =0;
						int month1 = Integer.parseInt(entryTime1.substring(5,7))+1;
						int year1 = Integer.parseInt(entryTime1.substring(0,4));
						if(month1>12){
				        	 years = year1+1;
				        	 months = month1 - 12;
				         }else{
				         	years = year1;
				         	months = month1;
				         }
				         String month = "";
				         if(months<10){
				        	 month="0"+Integer.toString(months);				        	 
				         }else{
				         	 month=Integer.toString(months);
				         }
				         String year=Integer.toString(years);        
         		String entryTime2 = entryTime1 + "-01 00:00:00";
         		String entryTime3 = year+"-" + month + "-01 00:00:00";
				whereStr=whereStr+" AND TO_CHAR(JZ.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') >='"+entryTime2+"'"+" AND TO_CHAR(JZ.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') <'"+entryTime3+"'";
				str=str+" AND TO_CHAR(JZ.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') >='"+entryTime2+"'"+" AND TO_CHAR(JZ.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') <'"+entryTime3+"'";
			}			
			if(sydateks!=null&&!sydateks.equals("")&& !sydateks.equals("0")){
			whereStr=whereStr+" AND TO_CHAR(JZ.SYDATE,'yyyy-mm-dd')>='"+sydateks+"-01"+"'";
			  str=str+" AND TO_CHAR(JZ.SYDATE,'yyyy-mm-dd')>='"+sydateks+"-01"+"'";
			}
			if(sydatejs!=null&&!sydatejs.equals("")&& !sydatejs.equals("0")){
				whereStr=whereStr+" AND TO_CHAR(JZ.SYDATE,'yyyy-mm-dd')<='"+sydatejs+"-31"+"'";
			    str=str+" AND TO_CHAR(JZ.SYDATE,'yyyy-mm-dd')<='"+sydatejs+"-31"+"'";
			}
			if(zdqyzt!=null&&!zdqyzt.equals("")&& !zdqyzt.equals("-1")){
				whereStr=whereStr+" AND JZ.QYZT='"+zdqyzt+"'";
			    str=str+" AND JZ.QYZT='"+zdqyzt+"'";
			}
			
		if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;
       	 	 shi="1";
       	 }
		if(caiji!=null&&!caiji.equals("")&& !caiji.equals("-1")){
			whereStr=whereStr+" AND JZ.CAIJI='"+caiji+"'";
			str=str+" AND JZ.CAIJI='"+caiji+"'";
		}
		if(xuni!=null&&!xuni.equals("")&& !xuni.equals("-1")){
			whereStr=whereStr+" AND JZ.XUNI='"+xuni+"'";
			str=str+" AND JZ.XUNI='"+xuni+"'";
		}
		
		
		if (zdid1 != null && !zdid1.equals("") && !zdid1.equals("0")) {
			whereStr = whereStr + " AND JZ.ID='" + zdid1 + "' ";
			str = str+ " AND JZ.ID='" + zdid1 + "' ";
		}
		
		
		if(beginyue != null && !beginyue.equals("")){
			whereStr=whereStr+" AND TO_CHAR(JZ.MANUALAUDITDATETIME_STATION,'yyyy-mm')>='"+beginyue+"'";
			str = str+" AND TO_CHAR(JZ.MANUALAUDITDATETIME_STATION,'yyyy-mm')>='"+beginyue+"'";
		}
		if(endyue != null && !endyue.equals("")){
			whereStr=whereStr+" AND TO_CHAR(JZ.MANUALAUDITDATETIME_STATION,'yyyy-mm')<='"+endyue+"'";
			str=str+" AND TO_CHAR(JZ.MANUALAUDITDATETIME_STATION,'yyyy-mm')<='"+endyue+"'";
		}
		//市级新增站点查询 标志位 
		if(sbzw.equals("2")){
			whereStr = whereStr + " AND JZ.STATIONTYPE <> 'StationType41' ";
			str = str+ " AND JZ.STATIONTYPE <> 'StationType41' ";
		}
		if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
			ElectricFeesFormBean bean1 = bean.getCountt(whereStr,loginId);
			String count1 = bean1.getCount();
			String sumcj = bean1.getCaijizd();
			String sumwtg = bean1.getJdno();
			String sumsh = bean1.getZd();
		%>
		
			<% }%>
			
			
			<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >  	
			
					
				      <table width="2000px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                  		<tr height = "10" class="relativeTag">
                  	   <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>                      
                       <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                       <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点别名</div></td>
                       <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>
                       <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">乡镇</div></td>
                       <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
                       <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
                       
				       <!--  <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td>
				        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市定标电量</div></td>				       
				       <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">省定标电量</div></td>	
				        -->
				       <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用房类型</div></td>
				      <!--
            		   <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">节能设备</div></td>
            		    -->
                       <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">供电方式</div></td>
                       <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">占地面积</div></td>
                       <!--
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">饮水机台数</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">微机台数</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">营业办公空调</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">机房生产空调</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">空调数</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">空调1匹数</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">空调2匹数</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">空调总功率</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">远供rru数量</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">移动共享设备数量</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电信共享设备数量</div></td>
                        -->
                          
                       <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">负责人</div></td>                       
                        <!-- 新加入的字段 乡镇，站点类型，归属方，是否标杆，直流负荷，额定耗电量，有无空调，录入人员，录入时间  -->
                        <!--
                        <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">归属方</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">是否标杆</div></td>
                        <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">直流负荷</div></td>
                        <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交流负荷</div></td>
                        <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">生产标</div></td>
                        <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">额定耗电量</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">有无空调</div></td> 
                        -->                   
                        <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入人员</div></td>
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入时间</div></td>
                        <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市级审核状态</div></td>
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市级审核时间</div></td>
                        <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市级审核人员</div></td>                        
                         <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>
                           <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点启用状态</div></td>
                  </tr>
	<%
       	 List<ElectricFeesFormBean> fylist = null;
       	if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
       	 fylist = bean.getPageDatap(whereStr,loginId);
       	 
       	 allpage=bean.getAllPage();
		String id = "";
		String shengl = "";
		String shil = "";
		String xianl = "";
		String jzname = "";
		String jzcode = "";
		String szdq="",jztype="",property="",bieming="",yflx="",jnglmk="",fzr="",area="",xiangzhen="";
		
		String jlfh="",zlfh="",sdbdl="",ysjts="",wjts="",yybgkt="",jfsckt="",rru="",ydgxsbsl="",dxgxsbsl="",kts="",ktzgl="",ktyps="",kteps="";
	/**
	        12-08-10修改
		乡镇(XIAOQU)，站点类型(STATIONTYPE)，归属方(GSF)，
	          是否标杆(BGSIGN)，直流负荷(ZLFH)，
		额定耗电量(EDHDL)，有无空调(KONGTIAO)，
	           录入人员(ENTRYPENSONNEL)，录入时间(ENTRYTIME)
	           人工审核状态 rgshzt   SHSIGN
	           人工审核时间 rgshsj   MANUALAUDITDATETIME_STATION
	           人工审核人员 rgshry   MANUALAUDITNAME_STATION
	           ef.manualauditstatus,ef.manualauditdatetime,ef.manualauditname,
	           */
	           
		String zdl="",gsf1="",isbg="",zl="",edhd="",kt="",lrry="",lrsj="",scb="";

		String rgshzt="",rgshsj="",rgshry="",zdqyzt1="";
		int countxh=1;
		 if(fylist!=null)
		{
			for(ElectricFeesFormBean bean1:fylist){
DecimalFormat lab =new DecimalFormat("0.00");
		     //num为序号，不同页中序号是连续的
		    jlfh=bean1.getJlfh();
		    jlfh=jlfh!=null?jlfh:"";
			if(jlfh.equals("null")){
				jlfh="";
			}
		    zlfh=bean1.getZlfh();
		    
		    scb=bean1.getScb();
		    scb=scb!=null?scb:"";
			if(scb.equals("null")){
				scb="0";
			}
		    
		    sdbdl=bean1.getSdbdl();
		    if(null==sdbdl||"".equals(sdbdl)||"null".equals(sdbdl)){
		    	/*
		    if(null!=zlfh&&!"".equals(zlfh)&&!"null".equals(zlfh)){
		    //System.out.println("直流负荷："+zlfh);
		   sdbdl=lab.format((Double.parseDouble(zlfh)*54*24*1.2)/1000);
		   //System.out.println("定标电量："+sdbdl);
		    }else if(null!=jlfh&&!"".equals(jlfh)&&!"null".equals(jlfh)&&(null==zlfh||"".equals(zlfh)||"null".equals(zlfh))){
		    sdbdl=lab.format(((Double.parseDouble(jlfh)*220*24)/1000));
		    //System.out.println("交流负荷 ："+jlfh);
		    }*/
		    	sdbdl="";
		    }
		    id = bean1.getStationid();
		    id = id != null ? id : "";
		    if("null".equals(id)){
		    	id="";
		    }	
		    zdqyzt1 = bean1.getZdqyzt();
			if(zdqyzt1.equals("0")){
				zdqyzt1="不启用";
			}else if(zdqyzt1.equals("1")){
				zdqyzt1="启用";
			}else {
				zdqyzt1="";
			}
			szdq = bean1.getXian();
			xiangzhen=bean1.getXiaoqu();
		    jzname = bean1.getJzname();
		    jzname = jzname != null ? jzname : "";	
		    	    
		    rgshzt = bean1.getFeebz();
		    rgshzt = rgshzt != null ? rgshzt : "";
		    if(rgshzt.equals("null")){
		    	rgshzt="";
		    }
		    if(rgshzt.equals("0")){
		    	rgshzt="未通过";
		    }else if(rgshzt.equals("1")){
		    	rgshzt="通过";
		    }else if(rgshzt.equals("2")){
		    	rgshzt="不通过";
		    }
		    
		    rgshsj = bean1.getManualauditdatetime();
		    rgshsj = rgshsj != null ? rgshsj : "";
		    if(rgshsj.equals("null")){
		    	rgshsj="";
		    }
		    
		    
		    rgshry = bean1.getManualauditname();
		    rgshry = rgshry != null ? rgshry : "";		    
		    if(rgshry.equals("null")){
		    	rgshry="";
		    }
		  
		    jnglmk = bean1.getJnglmk();
			jnglmk=jnglmk!=null?jnglmk:"";
			if(jnglmk.equals("0")){
			 jnglmk="否";
			}else if(jnglmk.equals("1")){
			jnglmk="是";
			}
		
		    
		    
		    jzcode = bean1.getZdcode();
		    jzcode = jzcode != null ? jzcode : "";
		    if("null".equals(jzcode)){
		    	jzcode="";
		    }
		    
		    bieming = bean1.getBieming();
		    bieming = bieming != null ? bieming : "";
		    if("null".equals(bieming)){
		    	bieming="";
		    }
		    
		    gdfs = bean1.getGdfs();
		    gdfs = gdfs != null ? gdfs : "";
		    if("null".equals(gdfs)){
		    	gdfs="";
		    }
		    
			area = bean1.getArea();
			area = area != null ? area : "";
			if("null".equals(area)){
		    	area="";
		    }
					
			fzr = bean1.getFzr();
			fzr = fzr != null ? fzr : "";
			if(fzr.equals("null")){
				fzr="";
			}
			
			property = bean1.getProperty();
			property = property != null ? property : "";
			if(property.equals("null")){
				property="";
			}
			
			jztype = bean1.getJztype();
			jztype = jztype != null ? jztype : "";
			if(jztype.equals("null")){
				jztype="";
			}
			
			yflx = bean1.getYflx();
			yflx = yflx != null ? yflx : "";
			if(yflx.equals("null")){
				yflx="";
			}
		//==================================================================================
			/**
			乡镇(XIAOQU)，站点类型(STATIONTYPE)，归属方(GSF)，
	          是否标杆(BGSIGN)，直流负荷(ZLFH)，
		额定耗电量(EDHDL)，有无空调(KONGTIAO)，
	           录入人员(ENTRYPENSONNEL)，录入时间(ENTRYTIME)
			xz="",zdl="",gsf1="",isbg="",zl="",edhd="",kt="",lrry="",lrsj=""*/		
			
			zdl = bean1.getStationtype();
			zdl=zdl!=null?zdl:"";
			if(zdl.equals("null")){
				zdl="";
			}			
			
			gsf1 = bean1.getGsf();
			gsf1=gsf1!=null?gsf1:"";
			
			isbg = bean1.getBzdy();
			isbg=isbg!=null?isbg:"";
			if(isbg.equals("0")){
			 	isbg="否";
			}else if(isbg.equals("1")){
				isbg="是";
			}
			
			zl = bean1.getZlfh();
			zl=zl!=null?zl:"";
			if(zl.equals("null")){
			 	zl="";
			}
			
			DecimalFormat la =new DecimalFormat("0.00");
			edhd = bean1.getEdhdl();
			edhd=edhd!=null?edhd:"";
			if(edhd==null||edhd==""||"".equals(edhd)||"o".equals(edhd)){
				edhd="0.00";
			}
            edhd=la.format(Double.parseDouble(edhd));
			
			kt = bean1.getKongtiao();
			kt=kt!=null?kt:"";
			if(kt.equals("null")){
			kt="";
			}
			if(kt.equals("0")){
			kt="无";
			}else if(kt.equals("1")){
			kt="有";
			}
			
			
			lrry = bean1.getEntrypensonnel();
			lrry=lrry!=null?lrry:"";
			if(lrry.equals("null")){
			lrry="";
			}
			
			lrsj = bean1.getEntrytime();
			lrsj=lrsj!=null?lrsj:"";
			if(lrsj.equals("null")){
			lrsj="";
			}
			
			ysjts = bean1.getYsjts();
			ysjts=ysjts!=null?ysjts:"0";
			if(ysjts.equals("null")){
			ysjts="0";
			}
			
			wjts = bean1.getWjts();
			wjts = wjts!=null?wjts:"0";
			if(wjts.equals("null")){
			wjts="0";
			}
			
			yybgkt = bean1.getYybgkt();
			yybgkt = yybgkt!=null?yybgkt:"0";
			if(yybgkt.equals("null")){
			yybgkt="0";
			}
			
			
			jfsckt = bean1.getJfsckt();
			jfsckt = jfsckt!=null?jfsckt:"0";
			if(jfsckt.equals("null")){
			jfsckt="0";
			}
			
			rru = bean1.getRru();
			rru = rru!=null?rru:"0";
			if(rru.equals("null")){
			rru="0";
			}
			
			ydgxsbsl = bean1.getYdgxsbsl();
			ydgxsbsl = ydgxsbsl!=null?ydgxsbsl:"0";
			if(ydgxsbsl.equals("null")){
			ydgxsbsl="0";
			}
			
			dxgxsbsl = bean1.getDxgxsbsl();
			dxgxsbsl = dxgxsbsl!=null?dxgxsbsl:"0";
			if(dxgxsbsl.equals("null")){
			dxgxsbsl="0";
			}
			kts = bean1.getKts();
			kts = kts!=null?kts:"";
			if(kts.equals("null")){
			kts="";
			}
			ktyps = bean1.getKtyps();
			ktyps = ktyps!=null?ktyps:"0";
			if(ktyps.equals("null")){
			ktyps="0";
			}	
			kteps = bean1.getKteps();
			kteps = ktyps!=null?kteps:"0";
			if(kteps.equals("null")){
			kteps="0";
			}		
			ktzgl = bean1.getKtzgl();
			ktzgl = ktzgl!=null?ktzgl:"";
			if(ktzgl.equals("null")){
			ktzgl="";
			}
			
			if(intnum%2==0){
			    color="#FFFFFF";

			 }else{
			    color="#DDDDDD" ;
			}
           intnum++;
           

       %>
       <tr bgcolor="<%=color%>">
            <td>
       			<div align="center" ><%=countxh++%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="left" ><%=bieming%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=szdq%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=xiangzhen%></div>
       		</td>
       		
       		
       		<td>
       			<div align="center" ><%=property%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdl%></div>
       		</td>
       		<!--  
       		<td>
       			<div align="center" ><%=jztype%></div>
       		</td>
       		<td>
       			<div align="right" ><%=edhd%></div>
       		</td>
       		<td>
       			<div align="right" ><%=sdbdl%></div>
       		</td>
       		-->
       		<td>
       			<div align="center" ><%=yflx%></div>
       		</td>
       		<!--
       			<td>
       			<div align="center" ><%=jnglmk%></div>
       		</td>
       		-->
       		<td>
       			<div align="center" ><%=gdfs%></div>
       		</td>
       		<td>
       			<div align="right" ><%=area%></div>
       		</td>
       		<!-- 
       		<td>
       			<div align="right" ><%=ysjts%></div>
       		</td>
       		<td>
       			<div align="right" ><%=wjts%></div>
       		</td>
       		<td>
       			<div align="right" ><%=yybgkt%></div>
       		</td>
       		<td>
       			<div align="right" ><%=jfsckt%></div>
       		</td>
       		<td>
       			<div align="right" ><%=kts%></div>
       		</td>
       		<td>
       			<div align="right" ><%=ktyps%></div>
       		</td>
       		<td>
       			<div align="right" ><%=kteps%></div>
       		</td>
       		<td>
       			<div align="right" ><%=ktzgl%></div>
       		</td>
       		<td>
       			<div align="right" ><%=rru%></div>
       		</td>
       		<td>
       			<div align="right" ><%=ydgxsbsl%></div>
       		</td>
       		<td>
       			<div align="right" ><%=dxgxsbsl%></div>
       		</td>
       		 -->
       		<td>
       			<div align="center" ><%=fzr%></div>
       		</td>
       		<!--  
       		<td>
       			<div align="center" ><%=gsf1%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=isbg%></div>
       		</td>
       		
       		<td>
       			<div align="right" ><%=zl%></div>
       		</td>
       		
       		<td>
       			<div align="right" ><%=jlfh%></div>
       		</td>
       		
       		<td>
       			<div align="right" ><%=scb%></div>
       		</td>
       		
       		<td>
       			<div align="right" ><%=edhd%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=kt%></div>
       		</td>
       		-->
       		<td>
       			<div align="center" ><%=lrry%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=lrsj%></div>
       		</td>

             <td>
       			<div align="center" ><%=rgshzt%></div>
       		</td>
       		<td>
       			<div align="center" ><%=rgshsj%></div>
       		</td>
       		<td>
       			<div align="center" ><%=rgshry%></div>
       		</td>
 			<td>
       			<div align="left" ><%=id%></div>
       		</td>
       			<td>
       			<div align="left" ><%=zdqyzt1%></div>
       		</td>
       </tr>
       
       <%} %>
       <%} %>
       <%}%>
       <% if (intnum==0){
         for(int i=0;i<17;i++){
          if(i%2==0){
			    color="#FFFFFF" ;
          }else{
			    color="#DDDDDD";
			}
         %>

        <tr bgcolor="<%=color%>">
             <td>&nbsp;</td>
             <td>&nbsp;</td>  
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>                   
            <td>&nbsp;</td>
            <td>&nbsp;</td>                   
            <td>&nbsp;</td>
           
			 <td>&nbsp;</td>                   
            <td>&nbsp;</td>
            
            <td>&nbsp;</td> 
            
            </tr>
      <% }}else if(!(intnum > 16)){
    	  for(int i=((intnum-1)%16);i<16;i++){
            if(i%2==0)
			    color="#DDDDDD";
            else
			    color="#FFFFFF" ;
        %>
        <tr bgcolor="<%=color%>">
            <td>&nbsp;</td>  
            <td>&nbsp;</td>  
             <td>&nbsp;</td>
             <td>&nbsp;</td> 
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
            <td>&nbsp;</td>
            <td>&nbsp;</td>                   
            <td>&nbsp;</td>
             
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>                   
            <td>&nbsp;</td>
              <td>&nbsp;</td> 
              <td>&nbsp;</td>                   
            <td>&nbsp;</td>
            <td>&nbsp;</td>               
        </tr>
        <% }}%>
     
  	 </table> 
	</div>
			<div style="width:100%;height:8%;border:1px" >	
			<table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
               		<div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
          		</div></td>
			</tr>
 
  		<tr>  		
   			<td align="right">   			
             <!-- <%if(permissionRights.indexOf("PERMISSION_PRINT")>=0){%>                   
                                <div id="dayinBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
	 								<img src="<%=path %>/images/dayin.png" width="100%" height="100%">
	 								<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">打印</span>      
								</div> 
                               
                               <%} %>   -->
                               
                               <!--    <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                        
		                             <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
										 <img src="<%=path %>/images/daoru.png" width="100%" height="100%">
			 							 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
									</div>                               
									<%} %> -->
                               
                               
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
   <input type="hidden" name="manauditstatus2" id="manauditstatus2" value=""/>
  <input type="hidden" name="sptype2" id="sptype2" value=""/>
  <input type="hidden" name="chanquan2" id="chanquan2" value=""/>
  <input type="hidden" name="fwlx2" id="fwlx2" value=""/>
  <input type="hidden" value="" id="htmlsql" name="htmlsql" />
  <input type="hidden" name="bgsign2" id="bgsign2" value=""/>
  <input type="hidden" name="zdlx1" id="zdlx1" value="<%=zdlx1%>"/>
  <input type="hidden" name="ccz" id="ccz" value="<%=zdlx1%>"/>
  <input type="hidden" name="zdsx1" id="zdsx1" value="<%=zdsx1%>"/>
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

//载入执行，站点属性，站点类型的加载
		var bSwitch2=false;
		var bSwitch=false;
		window.onload=function(){
			var oDiv2=document.getElementById('div2');
			var oUl2=oDiv2.getElementsByTagName('ul')[0];
			var oP2=oDiv2.getElementsByTagName('p')[0];
			oP2.onclick=function(){
				if(bSwitch2){
					oUl2.style.display='none';
					bSwitch2=false;
				}else{
					var obj=document.getElementsByName("CheckboxGroup2");//根据自己的多选框名称修改下
					var zdlx2 =document.form1.zdsx1.value;//获取隐藏域等站点属性值
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
					oUl2.style.display='block';
					bSwitch2=true;
				}
			};
			
			var oDiv=document.getElementById('div1');
			var oUl=oDiv.getElementsByTagName('ul')[0];
			var oP=oDiv.getElementsByTagName('p')[0];
			oP.onclick=function(){
				if(bSwitch){
					oUl.style.display='none';
					bSwitch=false;
				}else{
					var obj=document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
					var zdlx2 = document.form1.zdlx1.value;//获取隐藏域等站点类型值
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
		//确定选中(站点类型)
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
			
			if(str2==""){
				str2="请选择";
			}
			document.form1.zdlx.value=str2;
			oUl.style.display='none';
			bSwitch=false;
		}
		//取消选中 (站点类型) 
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
			document.form1.zdlx.value="请选择";
			document.form1.zdlx1.value="";
			bSwitch=false;
		} 
		//确定选中(站点属性)
		function queding2(){
			var oDiv=document.getElementById('div2');
			var oUl=oDiv.getElementsByTagName('ul')[0];
			var obj=document.getElementsByName("CheckboxGroup2");//根据自己的多选框名称修改下
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
			if(str2==""){
				str2="请选择";
			}
			document.form1.zdsx1.value=str1;
			document.form1.jzproperty.value=str2;
			zdsxCheck(str1);
			oUl.style.display='none';
			bSwitch2=false;
		}
		//取消选中 (站点属性) 
		function quxiao2(){ 
			zdsxCheck("");
			var oDiv=document.getElementById('div2');
			var oUl=oDiv.getElementsByTagName('ul')[0];  
			var obj = document.getElementsByName("CheckboxGroup2");
			if(obj.length){
				for(var i=0;i<obj.length;i++){ 
					obj[i].checked = false;   
				}
				oUl.style.display='none';   
			}else{   
				obj.checked = false; 
				oUl.style.display='none';   
			}  
			document.form1.jzproperty.value="请选择";
			document.form1.zdsx1.value="";
			bSwitch2=false;
		} 

</script>

<script type="text/javascript">
//站点属性
function zdsxCheck(obj) {
	var sid = obj;
	document.form1.zdlx1.value="";
	sendRequest2(path + "/servlet/garea?action=zdsxa&pid=" + sid,
				"jzproperty");
}

function sendRequest2(url, para) {

	createXMLHttpRequest();

	XMLHttpReq.open("GET", url, true);
	if(para=="jzproperty"){
	 	XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数
	}
	XMLHttpReq.send(null);
}

function processResponse_zdlx() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			updateZdlx(res);
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}

}

function updateZdlx(res) {

	var str="<p><input type='text' class='selected_font' readonly= 'readonly' name='zdlx' value='请选择'/></p><ul>";
	for ( var i = 0; i < res.length; i += 2) {
		str+="<li><input type='checkbox' name='CheckboxGroup1' value='"+res[i].firstChild.data+","+res[i + 1].firstChild.data+"' id='CheckboxGroup1' />"+res[i + 1].firstChild.data+"</li>";
	}
		str+="<li><input type='button' onclick='queding();' value='确定' /><input type='button'  onclick='quxiao();'  value='取消' /></li></ul>";
	
	document.getElementById("div1").innerHTML=str;
	var oDiv=document.getElementById('div1');
	var oUl=oDiv.getElementsByTagName('ul')[0];
	var oP=oDiv.getElementsByTagName('p')[0];
	bSwitch=false;
	oP.onclick=function(){
		if(bSwitch){
			oUl.style.display='none';
			bSwitch=false;
		}else{
			oUl.style.display='block';
			bSwitch=true;
		}
	};
}
</script>
<script type="text/javascript">
function exportad(){
            var zdname="<%=zdname%>";
            var whereStr ="<%=str%>";
            
        	document.form1.action=path+"/web/query/basequery/站点信息.jsp?zdname="+zdname+"&whereStr="+whereStr+"&command=daochu";
            document.form1.submit();
   }
   function printad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=whereStr%>";
            //alert(canshuStr);
            window.open(path+"/web/query/basequery/stationpointquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr,'','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
      
        	//document.form1.action=path+"/web/query/basequery/ammeterdegreesquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr;
            //document.form1.submit();
   }
        document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.fwlx.value='<%=fwlx%>';
		//document.form1.sptype.value='<%=sptype%>';
		//document.form1.bgsign.value='<%=bgsign%>';
		document.form1.jzproperty.value='<%=jzproperty%>';
		//document.form1.gsf.value='<%=gsf%>';
		document.form1.zdlx.value='<%=zdlx%>';
		document.form1.zdlx1.value=document.form1.ccz.value;
		document.form1.manualauditstatus.value='<%=rgshzt1%>';
		document.form1.gdfs.value='<%=gdfs%>';
		document.form1.zdqyzt.value='<%=zdqyzt%>';
		//document.form1.caiji.value='<%=caiji%>';
		//document.form1.xuni.value='<%=xuni%>';
 </script>
</html>
