<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.PrepaymentQueryBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.text.*"%>

<%
    String bzyf = request.getParameter("bzyf") != null ? request.getParameter("bzyf") : "";//报账月份
    String dfzflx1=request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";
    String entryTime1 = request.getParameter("entryTime1") != null ? request.getParameter("entryTime1") : "";
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
    String liuch = request.getParameter("liuch")!=null?request.getParameter("liuch"):"";//流程号
    String gdfstj = request.getParameter("gdfs")!=null?request.getParameter("gdfs"):"0";//供电方式
    String cwyf=request.getParameter("cwyf")!=null?request.getParameter("cwyf"):"";
    String countryheadstatus1 = request.getParameter("countryheadstatus1")!=null?request.getParameter("countryheadstatus1"):"5";//区县审核状态
    String cityzrauditstatus1 = request.getParameter("cityzrauditstatus1")!=null?request.getParameter("cityzrauditstatus1"):"5";//市主任审核状态

	String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        String loginId1 = request.getParameter("loginId");
        String loginName = account.getAccountName();
        int intnum=0;
    	String color="";
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
		String manu = request.getParameter("manauditstatus")!=null?request.getParameter("manauditstatus"):"0";

         //String beginTimeQ = request.getParameter("beginTimeQ")!=null?request.getParameter("beginTimeQ"):"";
         //String endTimeQ = request.getParameter("endTimeQ")!=null?request.getParameter("endTimeQ"):"";
         String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
         String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"请选择";
         String zdlx1=request.getParameter("zdlx1")!=null?request.getParameter("zdlx1"):"";
         String entrypensonnel1 = request.getParameter("entrypensonnel1")!=null?request.getParameter("entrypensonnel1"):"";//录入人员
         String entrytimeQS    = request.getParameter("entrytimeQS")!=null?request.getParameter("entrytimeQS"):"";//开始录入时间
         String entrytimeJS    = request.getParameter("entrytimeJS")!=null?request.getParameter("entrytimeJS"):"";//结束录入时间
         String zdqyzt = request.getParameter("zdqyzt")!=null?request.getParameter("zdqyzt"):"-1";//结束录入时间
          String dbqyzt = request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"-1";//结束录入时间
         
         String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||(zdlx!=null)||(dfzflx1!=null)||(entrypensonnel1!=null)||((entrytimeQS!=null)&&(entrytimeJS!=null))){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&dfzflx1="+dfzflx1+"&zdlx="+zdlx+"&entrypensonnel1="+entrypensonnel1+"&beginTimeQ="+entrytimeQS+"&endTimeQ="+entrytimeJS;
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
logMange
</title>
<style>
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
.style1 {
	color: red;
	font-weight: bold;
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
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.bttcn{color:black;font-weight:bold;}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
#div1{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div1 p{ float:left;font-size:12px; width:110px; cursor:pointer;}
.form_la{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>


<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
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


	function adddegree(){
          document.form1.action=path+"/web/electricfees/addPrepayment.jsp";
                                document.form1.submit();
        }
    function delad(electricfeeId){
       if(confirm("您确定删除此预付费信息？")){
                    document.form1.action=path+"/servlet/prepayment?action=del&degreeid="+electricfeeId
        			document.form1.submit()
       }
    }
    function modifyad(prepayId){
                    //alert(prepayId);
                    document.form1.action=path+"/web/electricfees/modifyPrepayment.jsp?prepayId="+prepayId;
                    document.form1.submit();
       
    }
    
    function queryDegree(){
      if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null)
		{
	                 alert("城市不能为空");
	   
	   }else{
		   		var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
		   		var et = document.form1.entryTime1.value;
				if(reg1.exec(et)||et==""||et==null){
                   document.form1.action=path+"/web/query/basequery/prepaymentQuery.jsp?command=chaxun";
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
 
 function dfinfo5(dbid,dlid){
	 window.open(path+"/web/query/basequery/yfftan.jsp?dbid="+dbid+"&dlid="+dlid,'','width=500,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
  
}	
   //页面载入方法
    function op(){
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian,prepayment&tab=zd,pr&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
    }
    $(function(){

	$("#query").click(function(){
		queryDegree();
		 showdiv("请稍等..............");
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
permissionRights=commBean.getPermissionRight(roleId,"0505");

%>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="20%">
			<tr>
				<td>
				</td>
			</tr>
		<tr>
			<td colspan="4" width="50">
				<div style="width:700px;height:50px">
					
					<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">预付费查询</span>	
				</div>
			</td>
		</tr>
		<tr>
		   <td height="20" colspan="4"><div id="parent" style="display:inline">
               <div style="width:50px;display:inline;"><hr></div><font size="2">过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
           </div></td>
		</tr>
		<tr >
		<td width="1200px">
		<table border='0'>
		<tr class="form_label">
		    		<td>城市：</td>
		    		
		    		<td><select name="shi" class="selected_font"  id="shi" onchange="changeCity()">
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
	         		 <td>区县：  </td>          
                      <td> <select name="xian" class="selected_font" onchange="changeCountry()">
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
         	                            </select>   </td>     
                                  <td> 乡镇：</td>         
                                       <td> <select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
							       <div id="query" style="position:relative;width:59px;height:23px;right:-250px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
							</td>
					
					</tr>
					</table>
					</td>
					</tr>
   			 <tr >
		
		
   		 <td colspan="8">
					<div style="width:80%;" > 
					  <p id="box3" style="display:none">
					<table width="100%" border="0">
   		 		<tr class="form_label">
   		 			<td>电费支付类型：</td>
   		 				<td><select name="dfzflx" class="selected_font">
				         		<option value="0">请选择</option>
				         		<%
					         	ArrayList dfzflx = new ArrayList();
					         	dfzflx = ztcommon.getSelctOptions("DFZFLX");
					         	if(dfzflx!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)dfzflx.get(0)).intValue();
					         		for(int i=cscount;i<dfzflx.size()-1;i+=cscount)
				                    {
				                    	code=(String)dfzflx.get(i+dfzflx.indexOf("CODE"));
				                    	name=(String)dfzflx.get(i+dfzflx.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         %>
				         	
				         	</select></td>
				    <td>站点名称：</td><td><input type="text" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname"));%>" class="selected_font"/></td>
				<td>供电方式：</td>
   		 				<td><select name="gdfs" class="selected_font">
				         		<option value="0">请选择</option>
				         		<%
					         	ArrayList gdfb = new ArrayList();
					         	gdfb = ztcommon.getSelctOptions("GDFS");
					         	if(gdfb!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)gdfb.get(0)).intValue();
					         		for(int i=cscount;i<gdfb.size()-1;i+=cscount)
				                    {
				                    	code=(String)gdfb.get(i+gdfb.indexOf("CODE"));
				                    	name=(String)gdfb.get(i+gdfb.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         %>
				         	
				 	</select></td>
				 	<td>财务月份：</td>
				 	 <td><input type="text" name="cwyf" class="selected_font"  value="<%if (null != request.getParameter("cwyf")) out.print(request.getParameter("cwyf"));%>" 
				 	 			readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /></td>
				</tr>   		 		
    			<tr class="form_label">
				    <td>财务审核状态：</td>
                    <td><select name="manauditstatus" class="selected_font" onchange="javascript:document.form1.manauditstatus2.value=document.form1.manauditstatus.value">
	                         <option value="0">请选择</option>	                        
	                         <option value="2">财务通过</option>   
	                         <option value="-1">财务不通过</option>       		                       
	                         <option value="1">财务未审核</option>
                         </select></td>     			
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
					  <td>录入月份：</td>
                      <td><input type="text" name="entryTime1" class="selected_font"  value="<%if (null != request.getParameter("entryTime1")) out.print(request.getParameter("entryTime1"));%>" 
                      				readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /></td>
   		 			  <td>录入人员：</td><td><input type="text" class="selected_font" name="entrypensonnel1" value="<%if(null!=request.getParameter("entrypensonnel1")) out.print(request.getParameter("entrypensonnel1")); %>" /></td>
   		 		</tr>
   		 		<tr class="form_label">
   		 		<td>报账月份:</td><td><input type="text" name="bzyf" value="<%if (null != request.getParameter("bzyf")) out.print(request.getParameter("bzyf"));%>" 
   		 									readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font" /></td>
   		 		<td> 流程号：</td><td><input type="text" name="liuch" value="<%if(null!=request.getParameter("liuch")) out.print(request.getParameter("liuch")); %>" class="selected_font"/></td>
			 	<td>开始录入时间：</td>
				<td><input type="text" class="selected_font" name="entrytimeQS" value="<%if(null!=request.getParameter("entrytimeQS")) out.print(request.getParameter("entrytimeQS")); %>" 
							readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /></td>                 
				<td>结束录入时间：</td>
				<td><input type="text" class="selected_font" name="entrytimeJS" value="<%if(null!=request.getParameter("entrytimeJS")) out.print(request.getParameter("entrytimeJS")); %>" 
							readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" /> </td>   		 		
				</tr>
   		 		<tr class="form_label">
   		 		<td>站点启用状态：</td>
         			<td><select name="zdqyzt" class="selected_font">
         	 			<option value="1">是</option>
         	   			<option value="0">否</option>
         				<option value="-1">请选择</option>
         			</select></td>
         			<td>电表启用状态：</td>
         			<td><select name="dbqyzt" class="selected_font">
         			<option value="-1">请选择</option>
         	 			<option value="1">是</option>
         	   			<option value="0">否</option>
         				
         			</select></td>
         			
         			<td>
						区县审核状态：
					</td>
					<td>
						<select name="countryheadstatus1" class="selected_font">
							<option value="5">
								请选择
							</option>
							<option value="0">
								未审核
							</option>
							<option value="1">
								通过
							</option>
							<option value="2">
								不通过
							</option>
						</select>
					</td>	
	       			<td>
						市主任审核状态：
					</td>
					<td>
						<select name="cityzrauditstatus1" class="selected_font">
							<option value="5">
								请选择
							</option>
							<option value="0">
								未审核
							</option>
							<option value="1">
								通过
							</option>
							<option value="2">
								不通过
							</option>
						</select>
					</td>
   		 		</tr>
   		 	</table>
   		 	</p>
   		 	</div>
   		 </td>
   		</tr> 
	</table>
	          	<table  height=23>
<tr><td height="20"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>
	<%	       
         String whereStr = "";
         String str="";
         PrepaymentQueryBean bean = new PrepaymentQueryBean();
          
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
			if(bzyf != null && bzyf != ""){
				whereStr=whereStr+" AND TO_CHAR(PR.ACCOUNTMONTH,'yyyy-mm') ='"+bzyf+"'"; 
				str=str+" AND TO_CHAR(PR.ACCOUNTMONTH,'yyyy-mm')='"+bzyf+"'";
			}
			if(cwyf != null && cwyf != ""){
				whereStr=whereStr+" AND TO_CHAR(PR.KJYF,'yyyy-mm') ='"+cwyf+"'"; 
				str=str+" AND TO_CHAR(PR.KJYF,'yyyy-mm')='"+cwyf+"'";
			}
			
			//录入人员
			if(entrypensonnel1 != null && !entrypensonnel1.equals("")&& !entrypensonnel1.equals("0")){
				whereStr=whereStr+" AND (PR.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"+entrypensonnel1+"'))";
				str=str+" AND (PR.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"+entrypensonnel1+"'))";
			}
			if(entrytimeQS != null && !entrytimeQS.equals("") && !entrytimeQS.equals("0")){
				entrytimeQS = entrytimeQS+" 00:00:00";
				whereStr=whereStr+" AND TO_CHAR(PR.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')>='"+entrytimeQS+"'";
				str=str+" AND TO_CHAR(PR.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')>='"+entrytimeQS+"'";
			}
			if(entrytimeJS != null && !entrytimeJS.equals("") && !entrytimeJS.equals("0")){
				entrytimeJS = entrytimeJS+" 24:00:00";
				whereStr=whereStr+" AND TO_CHAR(PR.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')<='"+entrytimeJS+"'";
				str=str+" AND TO_CHAR(PR.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')<='"+entrytimeJS+"'";
			}
			if(liuch != null && liuch != "" && !liuch.equals("0")){
				whereStr=whereStr+" AND PR.LIUCHENGHAO = '"+liuch+"'";
				str=str+" AND PR.LIUCHENGHAO = '"+liuch+"'";
		}
		
			if(dfzflx1 != null && !dfzflx1.equals("") && !dfzflx1.equals("0")){
				whereStr=whereStr+" AND D.DFZFLX='"+dfzflx1+"'";
				str=str+" AND D.DFZFLX='"+dfzflx1+"'";
			}
			if(gdfstj != null && !gdfstj.equals("") && !gdfstj.equals("0")){
				whereStr=whereStr+" AND ZD.GDFS='"+gdfstj+"'";
				str=str+" AND ZD.GDFS='"+gdfstj+"'";
			}
			if(zdqyzt != null && !zdqyzt.equals("") && !zdqyzt.equals("-1")){
				whereStr=whereStr+" AND ZD.QYZT='"+zdqyzt+"'";
				str=str+" AND ZD.QYZT='"+zdqyzt+"'";
			}
			if(dbqyzt != null && !dbqyzt.equals("") && !dbqyzt.equals("-1")){
				whereStr=whereStr+" AND D.DBQYZT='"+dbqyzt+"'";
				str=str+" AND D.DBQYZT='"+dbqyzt+"'";
			}
			if(zdlx1!=null&&!zdlx1.equals("")&& !zdlx1.equals("0")){
			  whereStr=whereStr+" AND ZD.STATIONTYPE IN("+zdlx1+")";
			  str=str+" AND ZD.STATIONTYPE IN("+zdlx1+")";
			}if(manu!=null&&!manu.equals("")&& !manu.equals("0")){
				whereStr=whereStr+" AND PR.FINANCEAUDIT='"+manu+"'";
				str=str+" AND PR.FINANCEAUDIT='"+manu+"'";
			}
			
			if (countryheadstatus1 != null && !countryheadstatus1.equals("") && !countryheadstatus1.equals("5")) {
				whereStr = whereStr + " AND PR.COUNTYAUDITSTATUS='" + countryheadstatus1 + "'";
			}
			
			if (cityzrauditstatus1 != null && !cityzrauditstatus1.equals("") && !cityzrauditstatus1.equals("5")) {
				whereStr = whereStr + " AND PR.CITYZRAUDITSTATUS='" + cityzrauditstatus1 + "'";
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
				whereStr=whereStr+" AND TO_CHAR(PR.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') >='"+entryTime2+"'"+" AND TO_CHAR(PR.ENTRYTIME,'yyyy-mm-ss hh24:mi:ss') <'"+entryTime3+"'";
				str=str+" AND TO_CHAR(PR.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') >='"+entryTime2+"'"+" AND TO_CHAR(PR.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') <'"+entryTime3+"'";
			}
		
		if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;
       	 	shi="1";
       	 }
		if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
			String zong="",shen="",weishen="",caiwu="",count5="";
			ElectricFeesFormBean bean1 = bean.getCount(whereStr,loginId);
			 zong = bean1.getCount();
			 zong = zong != null ? zong : "0";
			 
			 shen = bean1.getShish();
			 shen = shen != null ? shen : "0";
			 if("".equals(shen)){shen="0";}
			 
			 weishen = bean1.getWtg();
			 weishen = weishen != null ? weishen : "0";
			 if("".equals(weishen)){weishen="0";}
			 
			 caiwu = bean1.getCw();
			 caiwu = caiwu != null ? caiwu : "0";
			 if("".equals(caiwu)){caiwu="0";}
			 
			 count5 = bean1.getMoney();
			 count5 = count5 != null ? count5 : "0.0";
		if(count5==null||count5==""||"".equals(count5)||"null".equals(count5)){
		   count5="0.00";
		}else{
		    DecimalFormat countdl=new DecimalFormat("0.00");
		    count5=countdl.format(Double.parseDouble(count5));
		}		
  %>

		<table height="5%">
          <tr>
  	        <td><font size="2">总共</font></td><td><font size="2" color="red"><%=zong%></font><font size="2">条  |</font></td>
  	        <td><font size="2">未审核</font></td><td><font size="2" color="red"><%=weishen%></font><font size="2">条 |</font></td>
  	        <td><font size="2">市级审核</font></td><td><font size="2" color="red"><%=shen%></font><font size="2">条 |</font></td>
  	        <td><font size="2">财务审核</font></td><td><font size="2" color="red"><%=caiwu%></font><font size="2">条 |</font></td>
  	        <td><font size="2">金额总和</font></td><td><font size="2" color="red"><%=count5 %></font><font size="2">元</font></td>
  	       
  	      </tr>
		</table>
	
<%} %>
	 
			<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 	
	 			  <table width="4000px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
   	                 <tr height = "23" class="relativeTag ">
                       	<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td> 
                       	 <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属区县</div></td> 
                       	 <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属乡镇</div></td> 
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"> 电表名称</div></td> 
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"> 电费支付类型</div></td>   
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">金额</div> </td> 
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">供电方式</div> </td>          			
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始电表数</div></td>
                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"> 终止电表数</div></td>                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始时间</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">预计结束时间</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务月份</div></td>
                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核状态</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县主任审核状态 </div></td>
                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市级审核</div></td>
                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市主任审核状态 </div></td>
                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务审核</div></td>
                        
                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"> 费用类型</div></td>     
                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">流程号</div></td>
                                              
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入时间</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入人员</div></td>
                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"> 起始年月</div></td>                             
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束年月</div></td>                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>
						<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">备注</div></td>
						
						<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核人</div></td>
						<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核时间</div></td>
						<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县主任审核人</div></td>
						<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县主任审核时间</div></td>
						<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市主任审核人</div></td>
						<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市主任审核时间</div></td>

                    </tr>
       <%
       	 List<ElectricFeesFormBean> fylist = null;
       	 if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
       	  fylist = bean.getPageData(curpage,whereStr,loginId);
       	 allpage=bean.getAllPage();
		String Id = "",
		feetypeid = "",
		memo="",
		ammeterdegreeidFk = "", 
		money = "",
		money2="",
		cwyf1="",
		startdegree = "",
		stopdegree = "",
		startdate = "",
		enddate = "",
		gdfs="",
		prepaymentammeterid = "",
		dbname="",
		szxian="",
		szxq="",
		dfzflx2="";
		//新增变量=====
		//zdid站点id，sjsh市级审核，cwsh财务审核,zdl站点类型//
		
		String zdid="",sjsh="",cwsh="",jzname="",zdl="";
		
		String entrytime="",entrypensonnel="",startmonth="",endmonth="",accountmonth="",liuchenghao="";
		String countyauditstatus="",countyauditname="",countyaudittime="";
		String cityzrauditstatus="",cityzrauditname="",cityzraudittime="";
		String manualauditstatus="",manualauditname="",manualauditdatetime="";
	
		int countxh=1;
       double money1=0;
		/*int intnum=xh = pagesize*(curpage-1)+1;*/
		 if(fylist!=null){ 
			for(ElectricFeesFormBean bean1:fylist){

		     //num为序号，不同页中序号是连续的
		    Id = bean1.getStationid();	
		    prepaymentammeterid = bean1.getPrepayment_ammeterid();
		    prepaymentammeterid = prepaymentammeterid != null ? prepaymentammeterid : "";
		    
		    liuchenghao = bean1.getLiuchenghao();
		    liuchenghao = liuchenghao != null ? liuchenghao : "";		    
		    
		   	zdid = bean1.getZdcode();
		    zdid = zdid != null ? zdid : "";	
		    
		    accountmonth = bean1.getAccountmonth();
		    accountmonth = accountmonth != null ? accountmonth : "";
		    if(accountmonth=="0"||accountmonth.equals("0")||accountmonth.equals("null")){
		      accountmonth="";
		    }
		    cwyf1=bean1.getKjyf();
		    cwyf1 = cwyf1 != null ? cwyf1 : "";
		    if(cwyf1=="0"||cwyf1.equals("0")||cwyf1.equals("null")){
		      cwyf1="";
		    }
		    
			ammeterdegreeidFk = bean1.getStationid();
			ammeterdegreeidFk = ammeterdegreeidFk != null ? ammeterdegreeidFk : "";
			
			dbname = bean1.getDbname();
		    dbname = dbname != null ? dbname : "";
		    
		    szxian = bean1.getXian();
		    szxian = szxian != null ? szxian : "";
		    
		    szxq = bean1.getXiaoqu();
		    szxq = szxq != null ? szxq : "";
		    
		    zdl =  bean1.getStationtype();
			zdl = zdl!=null?zdl:"";
			
			dfzflx2=bean1.getDfzflx();
			if(dfzflx2=="0"||dfzflx2.equals("0")||dfzflx2.equals("null")||dfzflx2==null){
		      dfzflx2="";
		    }
			
		    
		    
		    feetypeid = bean1.getFeebz();	
		    feetypeid = feetypeid != null ? feetypeid : "";
		    
		    jzname = bean1.getJzname();
		    jzname = jzname!=null?jzname:"";
		    
		    sjsh = bean1.getCityaudit();
		    sjsh=sjsh!=null?sjsh:"";
		    if(sjsh.equals("0")||sjsh==""||sjsh.equals("")||sjsh==null){
		         sjsh="未通过";
		    }else if(sjsh.equals("1")){
		          sjsh="通过";
		    }else if(sjsh.equals("-2")){
		          sjsh="不通过";
		    }else if(sjsh.equals("2")){
		          sjsh="不通过";
		    }
		    
		    countyauditstatus = bean1.getCountyauditstatus();
		    countyauditstatus=countyauditstatus!=null?countyauditstatus:"";
		    if(countyauditstatus.equals("0")||countyauditstatus==""||countyauditstatus.equals("")||countyauditstatus==null){
		    	countyauditstatus="未审核";
		    }else if(countyauditstatus.equals("1")){
		    	countyauditstatus="审核通过";
		    }else if(countyauditstatus.equals("2")){
		    	countyauditstatus="审核不通过";
		    }
		   
		    countyauditname = bean1.getCountyauditname();			
			if(countyauditname=="0"||countyauditname.equals("0")||countyauditname.equals("null")){
				countyauditname="";
			}else{
			    countyauditname = countyauditname != null ? countyauditname : "";
			}
			countyaudittime = bean1.getCountyaudittime();	
		 	if(countyaudittime=="0"||countyaudittime.equals("0")||countyaudittime.equals("null")){
		 		countyaudittime="";
			}else{
				countyaudittime = countyaudittime != null ? countyaudittime : "";
			}
		    
			cityzrauditstatus = bean1.getCityzrauditstatus();
			cityzrauditstatus=cityzrauditstatus!=null?cityzrauditstatus:"";
		    if(cityzrauditstatus.equals("0")||cityzrauditstatus==""||cityzrauditstatus.equals("")||cityzrauditstatus==null){
		    	cityzrauditstatus="未审核";
		    }else if(cityzrauditstatus.equals("1")){
		    	cityzrauditstatus="审核通过";
		    }else if(cityzrauditstatus.equals("2")){
		    	cityzrauditstatus="审核不通过";
		    }
		    
		    cityzrauditname = bean1.getCityzrauditname();			
			if(cityzrauditname=="0"||cityzrauditname.equals("0")||cityzrauditname.equals("null")){
				cityzrauditname="";
			}else{
				cityzrauditname = cityzrauditname != null ? cityzrauditname : "";
			}
			cityzraudittime = bean1.getCityzraudittime();	
		 	if(cityzraudittime=="0"||cityzraudittime.equals("0")||cityzraudittime.equals("null")){
		 		cityzraudittime="";
			}else{
				cityzraudittime = cityzraudittime != null ? cityzraudittime : "";
			}
		    
			manualauditstatus = bean1.getManualauditstatus();
			manualauditstatus=manualauditstatus!=null?manualauditstatus:"";
		    if(manualauditstatus.equals("0")||manualauditstatus==""||manualauditstatus.equals("")||manualauditstatus==null){
		    	manualauditstatus="未审核";
		    }else if(manualauditstatus.equals("1")){
		    	manualauditstatus="通过";
		    }else if(manualauditstatus.equals("2")){//可加可不加
		    	manualauditstatus="不通过";
		    }else if(manualauditstatus.equals("-2")){
		    	manualauditstatus="不通过";
		    }
		    
		    manualauditname = bean1.getManualauditname();			
			if(manualauditname=="0"||manualauditname.equals("0")||manualauditname.equals("null")){
				manualauditname="";
			}else{
				manualauditname = manualauditname != null ? manualauditname : "";
			}
			manualauditdatetime = bean1.getManualauditdatetime();	
		 	if(manualauditdatetime=="0"||manualauditdatetime.equals("0")||manualauditdatetime.equals("null")){
		 		manualauditdatetime="";
			}else{
				manualauditdatetime = manualauditdatetime != null ? manualauditdatetime : "";
			}
		   
		   cwsh = bean1.getCw();
		   cwsh=cwsh!=null?cwsh:"";
		   if(cwsh.equals("null")){
		   		cwsh="";
		   }
					
			if(cwsh.equals("0")||cwsh==""||cwsh.equals("")||cwsh==null){
		         cwsh="未通过";
		    }else if(cwsh.equals("1")){
		          cwsh="未通过";
		    }else if(cwsh.equals("2")){
		         cwsh="通过";
		    }else if(cwsh.equals("-1")){
		         cwsh="不通过";
		    }		
					    
		    DecimalFormat mat=new DecimalFormat("0.00");
		    money = bean1.getMoney();
		    money = money != null ? money : "";
		    if(money==null||money==""||money.equals("")||money.equals("null")||money.equals("o")){
			money="0.0";
			}
		    money1=Double.parseDouble(money);
		    money2=mat.format(money1);
		    
		    startdegree = bean1.getStartdegree();
		    startdegree = startdegree != null ? startdegree : "";
		    
		    stopdegree = bean1.getStopdegree();
		    stopdegree = stopdegree != null ? stopdegree : "";
		    
		    startdate = bean1.getStartdate();
		    startdate = startdate != null ? startdate : "";
		    memo=bean1.getMemo();
		    memo=memo!=null?memo:"";
		    
			enddate = bean1.getStopdate();
			enddate = enddate != null ? enddate : "";	
			
			gdfs = bean1.getGdfs();	
			gdfs = gdfs != null ? gdfs : "";	
			
			if(startdegree==null||startdegree==""||startdegree.equals("")||startdegree.equals(" ")||startdegree.equals("null")||startdegree.equals("o")){
				startdegree="0";
			}
			 DecimalFormat s=new DecimalFormat("0.0");
			 startdegree=s.format(Double.parseDouble(startdegree));

			 
			 if(stopdegree==null||stopdegree==""||stopdegree.equals("")||stopdegree.equals(" ")||stopdegree.equals("null")||stopdegree.equals("o")){
			 	 stopdegree="0";
			 }
			 //DecimalFormat s=new DecimalFormat("0.0");
			 stopdegree=s.format(Double.parseDouble(stopdegree));
			 
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
			    color="#FFFFFF";
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
       			<div align="left" ><%=szxian%></div>
       		</td>
       		<td>
       			<div align="left" ><%=szxq%></div>
       		</td>
       		<td>
       			<div align="left" ><a href="#" onclick="dfinfo5('<%=prepaymentammeterid %>','<%=ammeterdegreeidFk %>')"><%=jzname%></a></div>
       			
       		</td>	
       		<td>
       			<div align="left" ><%=dbname%></div>
       		</td>
       		<td>
       			<div align="left" ><%=dfzflx2%></div>
       		</td>
       		
       		<td>
       			<div align="right" ><%=money2%></div>
       		</td>
       		<td>
       			<div align="right" ><%=gdfs%></div>
       		</td>       		
       		<td>
       			<div align="right" ><%=startdegree%></div>
       		</td> 
       		
       		<td>
       			<div align="right" ><%=stopdegree%></div>
       		</td> 
       		         
            <td>
       			<div align="center" ><%=startdate%></div>
       		</td>
            <td>
       			<div align="center" ><%=enddate%></div>
       		</td>
       		<td>
       			<div align="center" ><%=accountmonth%></div>
       		</td>
       		<td>
       			<div align="center" ><%=cwyf1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=manualauditstatus%></div>
       		</td>
       		<td>
       			<div align="center" ><%=countyauditstatus%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sjsh%></div>
       		</td>
       		<td>
       			<div align="center" ><%=cityzrauditstatus%></div>
       		</td>
       		<td>
       			<div align="center" ><%=cwsh%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdl%></div>
       		</td>
       		<td>
       			<div align="center" ><%=feetypeid%></div>
       		</td> 
       		 
       		<td>
       		    <div align="center"><%=liuchenghao%></div>
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
       			<div align="left" ><%=prepaymentammeterid%></div>
       		</td>
       		<td>
       			<div align="center" ><%=memo%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=manualauditname	%></div>
       		</td>
       		<td>
       			<div align="center" ><%=manualauditdatetime%></div>
       		</td>
       		<td>
       			<div align="center" ><%=countyauditname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=countyaudittime%></div>
       		</td>
       		<td>
       			<div align="center" ><%=cityzrauditname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=cityzraudittime%></div>
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
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
                      <td align="right" height="19" colspan="4">
                      	  <div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>
                          <div style="width:300px;display:inline;"><hr></div></div>
                      </td>
   </tr>
  <tr>
   <td align="right">          
			<!-- <%if(permissionRights.indexOf("PERMISSION_PRINT")>=0){%>                      
                           <div id="dayinBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
					<img src="<%=path %>/images/dayin.png" width="100%" height="100%">
					<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">打印</span>      
			</div> 
                          <%} %>   -->  
               <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                       
                         <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
					 <img src="<%=path %>/images/daochu.png" width="100%" height="100%">
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
  <input type="hidden" name="zdlx1" id="zdlx1" value=""/>
  <input type="hidden" name="ccz" id="ccz" value="<%=zdlx1%>"/>
  <input type="hidden" value="" id="htmlsql" name="htmlsql" />  
  </td></tr>  
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
      		document.form1.action=path+"/web/query/basequery/prepaymentQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
      		
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/query/basequery/prepaymentQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/query/basequery/prepaymentQuery.jsp?curpage="+pageno+"<%=canshuStr%>";
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
            var zdname="<%=zdname%>";
            //alert(canshuStr);
        	document.form1.action=path+"/web/query/basequery/预付费信息.jsp?curpage="+curpage+"&whereStr="+whereStr+"&zdname="+zdname+"&command=daochu";
            document.form1.submit();
   }
   function printad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=whereStr%>";
            //alert(canshuStr);
            window.open(path+"/web/query/basequery/prepaymentquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr,'','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
      
        	//document.form1.action=path+"/web/query/basequery/ammeterdegreesquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr;
            //document.form1.submit();
   }
  		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>'; 
		document.form1.dfzflx.value='<%=dfzflx1%>'; 
		document.form1.zdlx.value='<%=zdlx%>';
		document.form1.zdlx1.value=document.form1.ccz.value;
		document.form1.gdfs.value='<%=gdfstj%>';
		document.form1.manauditstatus.value='<%=manu%>';
		document.form1.zdqyzt.value='<%=zdqyzt%>';
		document.form1.dbqyzt.value='<%=dbqyzt%>';
		document.form1.countryheadstatus1.value='<%=countryheadstatus1%>';
		document.form1.cityzrauditstatus1.value='<%=cityzrauditstatus1%>';
		
 </script>
</html>
