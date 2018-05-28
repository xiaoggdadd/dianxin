<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.AmmeterDegreeQueryBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.text.*"%>
<%
    String beginTime1 = request.getParameter("beginTime1") != null ? request.getParameter("beginTime1"): "";
	String endTime1 = request.getParameter("endTime1") != null ? request.getParameter("endTime1") : "";
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
   	String entryTime1 = request.getParameter("entryTime1") != null ? request.getParameter("entryTime1") : "";
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	 String zdqyzt=request.getParameter("zdqyzt") != null ? request.getParameter("zdqyzt") : "1";
	 String dbqyzt=request.getParameter("dbqyzt") != null ? request.getParameter("dbqyzt") : "1";
	int intnum=0;
	String color="";                   
	//=======
     String path = request.getContextPath();
     Account account = (Account)session.getAttribute("account");
     String loginId = account.getAccountId();
     String loginId1 = request.getParameter("loginId");
     String loginName = account.getAccountName();
    //String roleId = account.getRoleId();
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
	String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"0";
	//String dbyt=request.getParameter("dbyt")!=null?request.getParameter("dbyt"):"0";
	String manualauditstatus=request.getParameter("manualauditstatus")!=null?request.getParameter("manualauditstatus"):"0";
	String entrypensonnel1 = request.getParameter("entrypensonnel1")!=null?request.getParameter("entrypensonnel1"):"";//录入人员
     String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"请选择";
     String zdlx1=request.getParameter("zdlx1")!=null?request.getParameter("zdlx1"):"";
     String entrytimeQS    = request.getParameter("entrytimeQS")!=null?request.getParameter("entrytimeQS"):"";//开始录入时间
     String entrytimeJS    = request.getParameter("entrytimeJS")!=null?request.getParameter("entrytimeJS"):"";//结束录入时间
     String htmlsql = request.getParameter("htmlsql");
     String ammeterid1 = request.getParameter("ammeterid");
     String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||(ammeterid1!=null)||(zdlx1!=null)||manualauditstatus!=null||(entrypensonnel1!=null)||(entryTime1!=null)||((entrytimeQS!=null)&&(entrytimeJS!=null))){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&ammeterid1="+ammeterid1+"&manualauditstatus="+manualauditstatus+"&zdlx1="+zdlx1+"&entrypersonnel1="+entrypensonnel1+"&entryTime1="+entryTime1+"&beginTimeQ1="+entrytimeQS+"&endTimeQ1="+entrytimeJS;
     }
     
     //
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
%>

<html>
<head>
<title>
logMange
</title>
<style>
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
		line-height:120%;
		
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}

 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#div1{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div1 p{ float:left;font-size:12px; width:110px; cursor:pointer;}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.bttcn{color:black;font-weight:bold;}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>

<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
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
           	if(checkNotnull(document.form1.beginTime,"开始时间")&&
                   checkNotnull(document.form1.endTime,"结束时间")){
 			document.form1.action=path+"/web/sys/logManage.jsp?beginTime="+beginTime
                        document.form1.submit()
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
    function queryDegree(){
        if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null)
		{
	                 alert("城市不能为空");
	   
	   }else{
		   		var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
		   		var et = document.form1.entryTime1.value;
				if(reg1.exec(et)||et==""||et==null){
                   document.form1.action=path+"/web/query/basequery/ammeterDegreeQuery.jsp?command=chaxun";
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
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian,ammeters,ammeterdegrees&tab=zd,am,ad,ef&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
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
});
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
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>

<%
permissionRights=commBean.getPermissionRight(roleId,"0503");

%>
<body  class="body" style="overflow-x:hidden;">
<form action="" name="form1" method="POST">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		<tr >
	      <td colspan="4" width="50"  >
	       <div style="width:700px;height:50px">
												 <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电量查询</span>	
											</div>
											</td>
	    </tr>
	    <tr><td height="20" colspan="4">
   				<div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	        </td>
	    </tr>
	   		<tr >
	   		<td width="1200px">
	   		<table>
	   		<tr class="form_label">
		    		<td >城市：</td>
		    		
		    		<td><select name="shi" class="selected_font" onchange="changeCity()">
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
	         			<td >	
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p>
					</td>
	         		<td >
							       <div id="query" style="position:relative;width:59px;height:23px;right:-205px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
							</td>
					
					</tr>
					</table>
					</td>
					</tr>
	    <tr><td colspan="10">
					<div style="width:100%;" > 
					  <p id="box3" style="display:none">
					<table>
		    	<tr class="form_label">
				
				
         					<td>站点名称：</td><td><input type="text" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" class="selected_font"/></td>
         	<td>电表ID：</td><td><input type="text" name="ammeterid" value="<%if(null!=request.getParameter("ammeterid")) out.print(request.getParameter("ammeterid")); %>" class="selected_font"/></td>
         	<td>
											电表启用状态：
										</td>
										<td>
											<select name="dbqyzt" class="selected_font">
												<option value="-1">
													请选择
												</option>
												<option value="0">
													否
												</option>
												<option value="1" selected="selected">
													是
												</option>

											</select>
											</td>
											<td>
											站点启用状态：
										</td>
										<td>
											<select name="zdqyzt" class="selected_font">
												<option value="-1">
													请选择
												</option>
												<option value="0">
													否
												</option>
												<option value="1">
													是
												</option>

											</select>
										</td>
         	</tr>
         	
         	<tr class="form_label">
			   <td>站点类型：</td><td>
		      	<div id="div1">
	      			<p><input type="text" class="selected_font" readonly= "true" name="zdlx" value="请选择"/></p>
	      				<ul>
				          <%
					         	ArrayList stationtype = new ArrayList();
				         		stationtype = ztcommon.getSelctOptions("StationType");
					         	if(stationtype!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)stationtype.get(0)).intValue();
					         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
				                    {
				                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
				                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
				                    %>
				                    <li><input type="checkbox" name="CheckboxGroup1" value="<%=code%>,<%=name%>" id="CheckboxGroup1" /><%=name%></li>
				                    <%}
					         	}
					         %>
					         	<li><input type="button" name="queding" id="queding"value="确定" /><input type="button" name="quxiao" id="quxiao" value="取消" /></li>
                                       </ul>
					
                       </div>	
		      	</td>
	            <td>审核状态：</td><td><select name="manualauditstatus" class="selected_font" onchange="javascript:document.form1.manauditstatus2.value=document.form1.manauditstatus.value">         		
		         		<option value="0">请选择</option>
		         		<option value="1">审核通过</option>
		         		<option value="2">审核未通过</option>         		
	         		</select></td>  
	            
	            <td>起始月份：</td><td><input type="text" class="selected_font" name="beginTime1" value="<%if (null != request.getParameter("beginTime1"))out.print(request.getParameter("beginTime1"));%>" 
	           								 readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /></td>
				<td>结束月份：</td><td><input type="text" name="endTime1" class="selected_font" value="<%if (null != request.getParameter("endTime1")) out.print(request.getParameter("endTime1"));%>" 
											 readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /></td>
	            
         	</tr>
         	<tr class="form_label">
         	<td>录入人员：</td><td><input type="text" class="selected_font" name="entrypensonnel1" value="<%if(null!=request.getParameter("entrypensonnel1")) out.print(request.getParameter("entrypensonnel1")); %>" /></td>
         	<td>录入月份：</td>
			<td><input type="text" name="entryTime1" class="selected_font"  value="<%if (null != request.getParameter("entryTime1")) out.print(request.getParameter("entryTime1"));%>" 
						readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /></td>				
         	<td>开始录入时间：</td>
			<td><input type="text" class="selected_font" name="entrytimeQS" value="<%if(null!=request.getParameter("entrytimeQS")) out.print(request.getParameter("entrytimeQS")); %>" 
						readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /></td>                 
			<td>结束录入时间：</td>
			<td><input type="text" class="selected_font" name="entrytimeJS" value="<%if(null!=request.getParameter("entrytimeJS")) out.print(request.getParameter("entrytimeJS")); %>" 
						readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /> </td>
										
									</tr>
		    </table>
		   </p>
		   </div> 
	    </td></tr>
	</table>
	<table  height=23>
<tr><td height="20"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>
	
	
        <%  
         String whereStr = "";
         String str ="";
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" AND ZD.SHI='"+shi+"'";
				str=str+" AND ZD.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" AND ZD.XIAN='"+xian+"'";
				str=str+" AND ZD.XIAN='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" AND ZD.XIAOQU='"+xiaoqu+"'";
				str=str+" AND ZD.XIAOQU='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
				whereStr=whereStr+" AND ZD.JZNAME LIKE '%"+zdname+"%'";
			}
			if(zdqyzt!= null && zdqyzt != ""&& !zdqyzt.equals("-1")){
				whereStr=whereStr+" AND zd.qyzt='"+zdqyzt+"'";
				str=str+" AND zd.qyzt='"+zdqyzt+"'";
			}if(dbqyzt!= null && dbqyzt != ""&& !dbqyzt.equals("-1")){
				whereStr=whereStr+" AND db.dbqyzt='"+dbqyzt+"'";
				str=str+" AND db.dbqyzt='"+dbqyzt+"'";
			}			
			if(ammeterid1 != null && ammeterid1 != "" && !ammeterid1.equals("0")){
				whereStr=whereStr+" AND AMMETERID_FK like '%"+ammeterid1+"%'";
			}
			//if(dbyt != null && dbyt != "" && !dbyt.equals("0")){
			//	whereStr=whereStr+" and AMMETERUSE ='"+dbyt+"'";
			//	str=str+" and AMMETERUSE ='"+dbyt+"'";
			//}
			if(beginTime1 != null && beginTime1 != "" && !beginTime1.equals("0")){
				whereStr=whereStr+" AND TO_CHAR(AD.STARTMONTH,'yyyy-mm') ='"+beginTime1+"'";
				str=str+" AND TO_CHAR(AD.STARTMONTH,'yyyy-mm') ='"+beginTime1+"'";
			}
			if(endTime1 != null && endTime1 != "" && !endTime1.equals("0")){
				whereStr=whereStr+" AND TO_CHAR(AD.ENDMONTH,'yyyy-mm') ='"+endTime1+"'";
				str=str+" AND TO_CHAR(AD.ENDMONTH,'yyyy-mm') ='"+endTime1+"'";
			}
			//录入月份							
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
				whereStr=whereStr+" AND TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') >='"+entryTime2+"'"+" AND TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') <'"+entryTime3+"'";
				str=str+" AND TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') >='"+entryTime2+"'"+" AND TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') <'"+entryTime3+"'";
			}
			//录入人员
			if(entrypensonnel1 != null && !entrypensonnel1.equals("")&& !entrypensonnel1.equals("0")){
				whereStr=whereStr+" AND (AD.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"+entrypensonnel1+"'))";
				str=str+" AND (AD.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"+entrypensonnel1+"'))";
			}
			if(entrytimeQS != null && !entrytimeQS.equals("") && !entrytimeQS.equals("0")){
				entrytimeQS = entrytimeQS+" 00:00:00";
				whereStr=whereStr+" AND TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')>='"+entrytimeQS+"'";
				str=str+" AND TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')>='"+entrytimeQS+"'";
			}
			if(entrytimeJS != null && !entrytimeJS.equals("") && !entrytimeJS.equals("0")){
				entrytimeJS = entrytimeJS+" 24:00:00";
				whereStr=whereStr+" AND TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')<='"+entrytimeJS+"'";
				str=str+" AND TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')<='"+entrytimeJS+"'";
			}
			String manualauditstatus3 = "";
			if(manualauditstatus != null && !manualauditstatus.equals("") && !manualauditstatus.equals("0")){
			    if(manualauditstatus.equals("2")){
			     	manualauditstatus3=0+"";
					whereStr=whereStr+" AND (AD.MANUALAUDITSTATUS='"+manualauditstatus3+"'"+"OR AD.MANUALAUDITSTATUS IS NULL)";
					str=str+" AND (AD.MANUALAUDITSTATUS='"+manualauditstatus3+"'"+"OR AD.MANUALAUDITSTATUS IS NULL)";
			    }else{
					whereStr=whereStr+" AND AD.MANUALAUDITSTATUS='"+manualauditstatus+"'";
					str=str+" AND AD.MANUALAUDITSTATUS='"+manualauditstatus+"'";

				}
			}
			if(zdlx1!=null&&!zdlx1.equals("")&& !zdlx1.equals("0")){
			  whereStr=whereStr+" AND ZD.STATIONTYPE IN("+zdlx1+")";
			  str=str+" AND ZD.STATIONTYPE IN("+zdlx1+")";
			}
		if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;
       	 	shi="1";
       	 }
		if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){			
			AmmeterDegreeQueryBean bean = new AmmeterDegreeQueryBean();
			ElectricFeesFormBean bean1 = bean.getCount(whereStr,loginId);
			String count1 = bean1.getCount();
			String count2 = bean1.getRgno();
			String count3 = bean1.getRg();
			String count4 = bean1.getCountts();
		if(count4==null||count4==""||"".equals(count4)||"null".equals(count4)){
		   count4="0.0";
		}else{
		    DecimalFormat countdl=new DecimalFormat("0.0");
		    count4=countdl.format(Double.parseDouble(count4));
		}	
  %>		
  			<table><tr>
	  	        <td colspan="10" align="left"><font size="2">总共</font><font size="2" color="red"><%=count1 %></font><font size="2">条  |</font>
	  	        <font size="2">未审核</font><font size="2" color="red"><%=count2 %></font><font size="2">条 |</font>
	  	        <font size="2">人工审核</font><font size="2" color="red"><%=count3 %></font><font size="2">条 |</font>
	  	        <font size="2">实际用电量总和</font><font size="2" color="red">&nbsp;<%=count4 %></font><font size="2">度 |</font></td>  	       
  	      	</tr></table>
  <%}%>
	
	
	<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 	
	 			  <table width="2000px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
				        <tr height = "23" class="relativeTag">
				        	<td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>	                      	
	                        <td nowrap height="23" width="180" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>          
	                        <td nowrap height="23" width="150" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次电表读数</div></td>
	            		    <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td>
	            		    
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次电表读数</div></td>	                        
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量调整</div></td>
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际用电量</div></td> 
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核状态</div></td>
	                        
	                        <td nowrap height="23" width="180" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>	                         
	                        <td nowrap height="23" width="200" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>
	                        <td nowrap height="23" width="200" bgcolor="#DDDDDD"><div align="center" class="bttcn">小区</div></td>
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电流类型</div></td>
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电设备</div></td>
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表用途</div></td>
	                        
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">额定耗电量</div></td>	                        
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">有无空调</div></td>
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">直流负荷</div></td>
	                        <td nowrap height="23" width="150" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入时间</div></td>
	                        <td nowrap height="23" width="100" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入人员</div></td>
	                        <td nowrap height="23" width="100" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始年月</div></td>
	                        
	                        <td nowrap height="23" width="100" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束年月</div></td>	                        
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td> 
	                        <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>                         
                      	</tr>
       <%
         AmmeterDegreeQueryBean bean = new AmmeterDegreeQueryBean();
       	 List<ElectricFeesFormBean> fylist = null;
       	 if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){       	 	
       	 	fylist = bean.getPageData(curpage,whereStr,loginId);
       	 	allpage=bean.getAllPage();
			String jzname="",electriccurrenttype_ammeter = "",shenhe="",usageofelectypeid_ammeter = "",ammeterdegreeid = "",ammeterid = "",lastdegree = "", thisdegree = "",lastdatetime = "",thisdatetime = "",floatdegree = "",actualdegree = "",dbname="",xian2="",xiaoqu2="",zdid="",dbyt1="";
		    String zdl=""; String edhdl=""; String kongtiao="";
		    String kongt1="",edhdl1="",zlfh="";
			String entrytime="",entrypensonnel="",startmonth="",endmonth="";
			int countxh=1;
		 if(fylist!=null){
			for(ElectricFeesFormBean bean1:fylist){		     
		    ammeterdegreeid = bean1.getAmmeterdegreeid();
		    electriccurrenttype_ammeter = bean1.getDllx();
		    electriccurrenttype_ammeter = electriccurrenttype_ammeter != null ? electriccurrenttype_ammeter : "";
		    //添加站点id
		    zdid = bean1.getZdid();
		    zdid = zdid != null ? zdid : "";
		    //站点类型
		    zdl = bean1.getStationtype();
		    zdl=zdl!=null?zdl:"";
		    //添加电表用途
		    dbyt1 = bean1.getDbyt();
		    dbyt1 = dbyt1 != null ? dbyt1 : "";
		    //添加空调
		    kongt1 = bean1.getKongtiao();
		    kongt1=kongt1 !=null ? kongt1 : "";
		    //添加直流负荷
		    zlfh = bean1.getZlfh();
		    zlfh = zlfh !=null ? zlfh : "";
 		    //添加额定功耗
		    edhdl1 = bean1.getEdhdl();
		    edhdl1 = edhdl1 != null ? edhdl1 : "";

		    usageofelectypeid_ammeter = bean1.getYdsb();
		    usageofelectypeid_ammeter = usageofelectypeid_ammeter != null ? usageofelectypeid_ammeter : "";
		    
			ammeterid = bean1.getAmmererid();
			ammeterid = ammeterid != null ? ammeterid : "";
			
			dbname = bean1.getDbname();
			dbname = dbname != null ? dbname : "";
			
			///szdq = bean1.getSzdq();
			//szdq = szdq != null ? szdq : "";
			xian2 = bean1.getXian();
			xian2 = xian2 != null ? xian2 : "";
			
			xiaoqu2 = bean1.getXiaoqu();
			xiaoqu2 = xiaoqu2 != null ? xiaoqu2 : ""; 
			
		    lastdegree = bean1.getLastdegree();
		    lastdegree = lastdegree != null ? lastdegree : "";
		    
		    thisdegree = bean1.getThisdegree();
		    thisdegree = thisdegree != null ? thisdegree : "";
		    
		    lastdatetime = bean1.getLastdatetime();
		    if(lastdatetime=="0"||lastdatetime.equals("0")||lastdatetime.equals("null")){
		   	 	lastdatetime = "";
		    }else{
		    	lastdatetime = lastdatetime != null ? lastdatetime : "";
		    }

			thisdatetime = bean1.getThisdatetime();			
			if(thisdatetime=="0"||thisdatetime.equals("0")||thisdatetime.equals("null")){
		      	thisdatetime="";
		    }else{
		    	thisdatetime = thisdatetime != null ? thisdatetime : "";
		    }
						
			floatdegree = bean1.getFloatdegree();
			floatdegree = floatdegree != null ? floatdegree : "";
					
		    actualdegree = bean1.getActualdegree();
		    actualdegree = actualdegree != null ? actualdegree : "";
		    
		    jzname = bean1.getJzname();
		    jzname = jzname != null ? jzname : "";
		    
		    if(floatdegree==null||floatdegree.equals("")||floatdegree.equals(" ")||floatdegree.equals("null")||floatdegree.equals("o"))floatdegree="0.0";
		    DecimalFormat price = new DecimalFormat("0.0");
			floatdegree = price.format(Double.parseDouble(floatdegree));
		    
		    DecimalFormat p = new DecimalFormat("0.0");
		    if(actualdegree==null||actualdegree.equals("")||actualdegree.equals(" ")||actualdegree.equals("null")||actualdegree.equals("o"))actualdegree="0.0";
			actualdegree = p.format(Double.parseDouble(actualdegree));
			
			//if(lastdatetime==null||lastdatetime.equals(""))lastdatetime="0";
			if(lastdegree==null||lastdegree.equals("")||lastdegree.equals(" ")||lastdegree.equals("null")||lastdegree.equals("o")){
			lastdegree="0.0";
			}
			DecimalFormat l = new DecimalFormat("0.0");
			lastdegree = l.format(Double.parseDouble(lastdegree));	
			if(thisdegree==null||thisdegree.equals("")||thisdegree.equals(" ")||thisdegree.equals("null")||thisdegree.equals("o")){
			thisdegree="0.0";
			}
			DecimalFormat t = new DecimalFormat("0.0");
			thisdegree = t.format(Double.parseDouble(thisdegree));
		    
		    
		    shenhe = bean1.getManualauditstatus();
		    shenhe = shenhe != null ? shenhe : "";
            if(shenhe.equals("1")){
                 shenhe="通过";
            }else if(shenhe.equals("0")||shenhe==""||shenhe=="null"||"null".equals(shenhe)||"".equals(shenhe)||shenhe.equals(" ")||shenhe==null){
            	 shenhe="未通过";
            }
            //添加录入时间、录入人员、起始时间、结束时间
            entrytime = bean1.getEntrytime();		
			if(entrytime=="0"||entrytime.equals("0")||entrytime.equals("null")){
		      entrytime="";
		    }else{
		   		entrytime = entrytime != null ? entrytime : "";
		    }
		    
		    entrypensonnel = bean1.getEntrypensonnel();		
			if(entrypensonnel=="0"||entrypensonnel.equals("0")||entrypensonnel.equals("null")){
		      entrypensonnel="";
		    }else{
		    	entrypensonnel = entrypensonnel != null ? entrypensonnel : "";
		    }
		    
			startmonth = bean1.getStartmonth();			
			if(startmonth=="0"||startmonth.equals("0")||startmonth.equals("null")){
		      startmonth="";
		    }else{
		    	startmonth = startmonth != null ? startmonth : "";
		    }
		    
		    endmonth = bean1.getEndmonth();		
			if(endmonth=="0"||endmonth.equals("0")||endmonth.equals("null")){
		      endmonth="";
		    }else{
		    	endmonth = endmonth != null ? endmonth : "";
		    }

			if(intnum%2==0){
			    color="#FFFFFF" ;

			 }else{
			    color="#DDDDDD";
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
       			<div align="left" ><%=dbname%></div>
       		</td>
       		<td>
       			<div align="right" ><%=lastdegree%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lastdatetime%></div>
       		</td>
       		<td>
       			<div align="right" ><%=thisdegree%></div>
       		</td>

       		<td>
       			<div align="center" ><%=thisdatetime%></div>
       		</td>          
            <td>
       			<div align="right" ><%=floatdegree%></div>
       		</td>
            <td>
       			<div align="right" ><%=actualdegree%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=shenhe%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdl%></div>
       		</td> 
       		<td>
       			<div align="left" ><%=xian2%></div>
       		</td>
       		<td>
       			<div align="left" ><%=xiaoqu2%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=electriccurrenttype_ammeter%></div>
       		</td>
       		<td>
       			<div align="center" ><%=usageofelectypeid_ammeter%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dbyt1%></div>
       		</td>
       		<td>
       		  <div align ="center"><%=edhdl1%></div>
       		</td>
       		<td>
       		  <div align ="center"><%=kongt1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zlfh%></div>
       		</td>
       		<td>
       			<div align="center" ><%=entrytime%></div>
       		</td>
       		<td>
       			<div align="center" ><%=entrypensonnel%></div>
       		</td>
       		<td>
       		  <div align ="center"><%=startmonth%></div>
       		</td>
       		<td>
       		  <div align ="center"><%=endmonth%></div>
       		</td>
     		<td>
       			<div align="left" ><%=zdid%></div>
       		</td>
       		<td>
       			<div align="left" ><%=ammeterid%></div>
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
			<table width="100%" height="8%" border="0" cellspacing="0" cellpadding="0">
		 <tr>
		                      <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
		                          <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
		                      </div></td>
		                    </tr>
		  <tr>
		   <td align="right">          <!--  <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                        
		                             <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
										 <img src="<%=path %>/images/daoru.png" width="100%" height="100%">
			 							 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
									</div>                               
									<%} %>  -->  
									
									
		                               <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                        
		                             <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
										 <img src="<%=path %>/images/daoru.png" width="100%" height="100%">
			 							 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
									</div>                               
									<%} %> 
		                           </td>
		  
		  </tr>
		  <tr><td>
		  <input type="hidden" name="sheng2" id="sheng2" value=""/>
		  <input type="hidden" name="shi2" id="shi2" value=""/>
		  <input type="hidden" name="xian2" id="xian2" value=""/>
		  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
		  <input type="hidden" name="dbyt2" id="dbyt2" value=""/>
		  <input type="hidden" name="zdlx1" id="zdlx1" value=""/>
		  <input type="hidden" name="ccz" id="ccz" value="<%=zdlx1%>"/>
		  <input type="hidden" value="" id="htmlsql" name="htmlsql" />
		  </td>
		  </tr>
		</table>
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
      		document.form1.action=path+"/web/query/basequery/ammeterDegreeQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      //alert(parseInt(document.form1.page.value));
      //alert(document.form1.page.value);
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/query/basequery/ammeterDegreeQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/query/basequery/ammeterDegreeQuery.jsp?curpage="+pageno+"<%=canshuStr%>";
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
	
	///////////////////////////////////////////////////////////
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

</script>
<script type="text/javascript">
function exportad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=str%>";
            var zdname ="<%=zdname%>";
            var ammeterid1 ="<%=ammeterid1%>";
            //alert(canshuStr);
        	document.form1.action=path+"/web/query/basequery/电量信息.jsp?curpage="+curpage+"&whereStr="+whereStr+"&zdname="+zdname+"&ammeterid1="+ammeterid1+"&command=daochu";
            document.form1.submit();
   }
   function printad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=whereStr%>";
            //alert(canshuStr);
            window.open(path+"/web/query/basequery/ammeterdegreesquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr,'','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
      
        	//document.form1.action=path+"/web/query/basequery/ammeterdegreesquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr;
            //document.form1.submit();
   }
   		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.manualauditstatus.value='<%=manualauditstatus%>';
		document.form1.zdlx.value='<%=zdlx%>';
		document.form1.zdqyzt.value='<%=zdqyzt%>';
		document.form1.dbqyzt.value='<%=dbqyzt%>';
		document.form1.zdlx1.value=document.form1.ccz.value;	
 </script>
</html>
