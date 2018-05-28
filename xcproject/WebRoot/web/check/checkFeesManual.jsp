<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="com.ptac.app.electricmanageUtil.Format" %>

<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.regex.Pattern"%>

<%	
	String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginId1 = request.getParameter("loginId");
    String loginName = account.getAccountName();
	String rgsh = request.getParameter("rgsh");
   	String rgsh1 = request.getParameter("rgsh1");
 	String lrrq = request.getParameter("lrrq") != null ? request.getParameter("lrrq"): "";
	String beginTime1 = request.getParameter("beginTime1") != null ? request.getParameter("beginTime1"): "";
	String endTime1 = request.getParameter("endTime1") != null ? request.getParameter("endTime1") : "";
	String bzyf = request.getParameter("bzyf") != null ? request.getParameter("bzyf") : "";//报账月份
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	String gdfstj       = request.getParameter("gdfs")!=null?request.getParameter("gdfs"):"0";//供电方式
	String manualauditstatus1=request.getParameter("manualauditstatus")!=null?request.getParameter("manualauditstatus"):"2";
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
    	String beginTimeQ = request.getParameter("BeginTime")!=null?request.getParameter("BeginTime"):"";
    	String endTimeQ = request.getParameter("EndTime")!=null?request.getParameter("EndTime"):"";
    	String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"0";
  		String jzproperty=request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
		 String sjshzt=request.getParameter("sjshzt") != null ? request.getParameter("sjshzt") : "-1";
		 String qxzrshzt=request.getParameter("qxzrshzt") != null ? request.getParameter("qxzrshzt") : "-1";
		 String getlrbz=request.getParameter("getlrbz") != null ? request.getParameter("getlrbz") : "0";//录入标志位
  
    String canshuStr="";
    String color=null;
    int intnum = 0;
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((beginTimeQ!=null)&&(endTimeQ!=null))||(zdname!=null)||(jzproperty!=null)||(manualauditstatus1!=null)){
         canshuStr= "shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&beginTimeQ="+beginTimeQ+"&endTimeQ="+endTimeQ+"&zdname="+zdname+"&jzproperty="+jzproperty+"&manualauditstatus1="+manualauditstatus1;
     }
    //String roleId = account.getRoleId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    double ds=0.0,dfs=0.0;
    double dfz=0.0,dfzs=0.0;
%>
<html>
<head>
<title>

</title>
<style>
.style1 {
	color: #FF9900;
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
 .memo {border: 1px ##888888 solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.bttcn{color:black;font-weight:bold;}
.form    {width:130px}

#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
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
           	if(checkNotnull(document.form1.beginTime,"开始时间")&&
                   checkNotnull(document.form1.endTime,"结束时间")){
 			document.form1.action=path+"/web/sys/logManage.jsp?beginTime="+beginTime
                        document.form1.submit()
        	}
	}
 </script>
  
<script language="javascript">
function passCheckReady(){
	var m = document.getElementsByName('test[]');
	var l = m.length;
	var manpassmemo = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			var k = m[i].value.toString().indexOf("zzzz=");	
			var autoauditstatus = m[i].value.toString().substring(k + 5, m[i].value.toString().length);
			if(autoauditstatus != 1){
				var manpassmemo = prompt("自动审核不通过，请输入您给予通过的原因：(不超过100字)" , "" );
				if(manpassmemo != "" && manpassmemo != null){
					passCheck(manpassmemo);
					showdiv("请稍等..............");
					return;
				} else {
					return;
				}
			}				
		}
	}	
	passCheck(manpassmemo);
	showdiv("请稍等..............");
}

</script>
  
<script language="javascript">

var path = '<%=path%>';
function passCheck(manpassmemo) {//通过
	var m = document.getElementsByName('test[]');
	var l = m.length;
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var bzw = 1;
	var bzw1 = "";
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
	}
	if (count != 0) {
		if (count % 240 == 0) {
			n = count / 240 - 1;
		} else {
			n = (count - (count % 240)) / 240;
		}
		 var r = 240 * n;
		for ( var i = 0; i < l; i++) {
			if (m[i].checked == true) {
				bz += 1;
				count1 += 1;
				
				var j = m[i].value.toString().indexOf("ssss=");
				var chooseIdStr3 = m[i].value.toString().substring(0, j);
				var zflx1 = m[i].value.toString().substring(j + 5, j + 6);
				
				if (zflx1 == "1") {
					chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
				} else if (zflx1 == "2") {
					chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
				}
			}
			var bz
			bzw1 = document.form1.bzw1.value;
			if (bzw1 == "1") {
				if (count1 <= r
						|| (bz >= 239 && bz <= 241) {
					if ((bz / 240 == 1)
							|| (bz >= 239 && bz <= 241)) {
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						chooseIdStr2 = chooseIdStr2.substring(0,
								chooseIdStr2.length - 1);
						passCheck1(chooseIdStr1, chooseIdStr2, manpassmemo);
						chooseIdStr1 = "";
						chooseIdStr2 = "";
						bz = 0;
					};
				} else if (count == count1 && bzw == 1) {
					chooseIdStr1 = chooseIdStr1.substring(0,
							chooseIdStr1.length - 1);
					chooseIdStr2 = chooseIdStr2.substring(0,
							chooseIdStr2.length - 1);
					bzw = 0;
					manpassmemo = encodeURIComponent(manpassmemo);
					document.form1.action = path + "/servlet/CountryElecServlet?command=checkcity&chooseIdStr1=" + chooseIdStr1 
					                        + "&chooseIdStr2=" + chooseIdStr2 + "&manpassmemo=" + manpassmemo;
					document.form1.submit();
				}
			} else if (bzw1 == "0") {
				document.form1.action = path + "/web/msg.jsp";
				document.form1.submit();
				return;
			}
		}
	} else {
		alert("请选择信息！");
	}
}


//查询站点详细信息
function lookDetailss(statusi) {//查看详情
	document.form1.attributes["target"].value = "_blank";
	document.form1.attributes["action"].value = path
			+ "/servlet/CityMngCheckServlet?command=xiangqing&statusi="+statusi;
	document.form1.submit();
}

function queryDegree(){
       document.form1.action=path+"/web/check/checkFeesManual.jsp?command=chaxun";
       document.form1.submit();
}


    $(function(){
		$("#daochuBtn").click(function(){
		 exportad();
		});

		$("#tongguo").click(function(){
			passCheckReady();
		});

		$("#butongguo").click(function(){
			passCheckNoPass();
		});
		$("#chaxun").click(function(){
			queryDegree();
			showdiv("请稍等..............");
		});
		$("#quxiao").click(function(){
			passCheckNo();
			
			
		});
	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0804");

%>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr><td colspan="4">
			<div style="width:700px;height:50px">
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">区县电量电费审核</span>	
			 </div>
	    	</td></tr>	    	
	    	<tr><td height="19" colspan="4">
   				<div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td></tr>
	    	<tr><td height="8%" width="1200">
	    		<table>
		    		<tr class="form_label">
		    		<td>城市：</td><td><select name="shi" class="selected_font" onchange="changeCity()">
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
		                    <option value="<%=agcode%>" selected="selected"><%=agname%></option>
		                    <%}
			         	}
			         %>
	         		</select></td>
					
					
					<td>区县:</td><td> <select name="xian" class="selected_font" onchange="changeCountry()">
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
					</p></td>
					<td> <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
				             <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;right:25%;float:right;top:0px;right:-240px">
						       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
						       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
						     </div>
				        <%}%>		
					</td>
					</tr>
	    		</table>
	    	</td></tr>		
		</table>
		
		<div style="width:88%;" > 
		<p id="box3" style="display:none">
			<table>
				<tr class="form_label">
				  <td>站点名称：</td>
				  <td>
					  <input type="text" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" 
					  class="selected_font"/>
				  </td>
	    			<td>站点属性：</td><td><select name="jzproperty" class="selected_font" onchange="kzinfo()">
			         		<option value="0">请选择</option>
			         		<%
				         	ArrayList zdsx = new ArrayList();
				         	zdsx = ztcommon.getSelctOptions("zdsx");
				         	if(zdsx!=null){
				         		String code="",name="";
				         		int cscount = ((Integer)zdsx.get(0)).intValue();
				         		for(int i=cscount;i<zdsx.size()-1;i+=cscount)
			                    {
			                    	code=(String)zdsx.get(i+zdsx.indexOf("CODE"));
			                    	name=(String)zdsx.get(i+zdsx.indexOf("NAME"));
			                    %>
			                    <option value="<%=code%>"><%=name%></option>
			                    <%}
				         	}
			         	%>
			       </select></td>
			       
			       <td>报账月份:</td><td><input type="text" name="bzyf" value="<%if (null != request.getParameter("bzyf")) out.print(request.getParameter("bzyf"));%>" 
			       								readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font" /></td>
				
				
				<td>录入日期：</td><td><input type="text" name="lrrq" value="<%if(null!=request.getParameter("lrrq")) out.print(request.getParameter("lrrq")); %>" 
												readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" onpropertychange="endtimezq()" class="selected_font"/></td>
			       
			       
		    	</tr>
	    			<tr class="form_label">	    
	    			
	    			
                   <td>供电方式：</td>
                       <td><select name="gdfs"  class="selected_font" >
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList gdfsb = new ArrayList();
			         	gdfsb = ztcommon.getSelctOptions("GDFS");
			         	if(gdfsb!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)gdfsb.get(0)).intValue();
			         		for(int i=cscount;i<gdfsb.size()-1;i+=cscount)
		                    {
		                    	code=(String)gdfsb.get(i+gdfsb.indexOf("CODE"));
		                    	name=(String)gdfsb.get(i+gdfsb.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
    		         </select></td>	
    		         			
	    			<td>人工审核状态:</td><td><select name="manualauditstatus" class="selected_font" onchange="javascript:document.form1.manauditstatus2.value=document.form1.manauditstatus.value">	         		
		         		<option value="0">请选择</option>
		         		<option value="1">审核通过</option>
		         		<option value="2">审核未通过</option>	
		         		<option value="-2">审核不通过</option>	           		
	         		</select></td>
	         		
			
			
			<td>区县主任审核状态</td>
				<td><select name="qxzrshzt" class="selected_font">
												<option value="-1" selected="selected">请选择</option>
												<option value="0" selected="selected">未审核</option>
												<option value="2" selected="selected">不通过</option>
				</select></td>
					
			
				<td>市级审核状态</td>
				<td><select name="sjshzt" class="selected_font">
												<option value="-1">请选择</option>
												<option value="0">未通过</option>
												<option value="1" selected="selected">通过</option>
												<option value="-2" selected="selected">不通过</option>
					</select></td>
					<td>录入标志位:</td>
				<td><select name="getlrbz" class="selected_font">
												<option value="0" selected="selected">请选择</option>
												<option value="1">电费单</option>
												<option value="2">预付费</option>
												
					</select></td>
					
			</tr>
			</table>
		</p>
	</div>
		
		<table>
			 <tr><td colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
              </div></td></tr>
		</table>
		
			<%	       
		        ElectricFeesBean bean=new ElectricFeesBean();
			
				//如果修改过滤条件  一下四个字段都要修改
        		String whereStr = "";//查询过滤条件 组合（电费单）  
		        String str="";//查询过滤条件 组合（预付费）
		    	String whereStrdc = "";//导出过滤条件 组合（电费单）解决百分号传值造成的问题
		        String strdc="";//导出过滤条件 组合（预付费）解决百分号传值造成的问题
		        
					if(shi != null && !shi.equals("") && !shi.equals("0")){
						whereStr=whereStr+" and zd.shi='"+shi+"'";
						str=str+" and zd.shi='"+shi+"'";
						whereStrdc=whereStrdc+" and zd.shi='"+shi+"'";
						strdc=strdc+" and zd.shi='"+shi+"'";
					}
					
					if(xian != null && !xian.equals("") && !xian.equals("0")){
						whereStr=whereStr+" and zd.xian='"+xian+"'";
						str=str+" and zd.xian='"+xian+"'";
						whereStrdc=whereStrdc+" and zd.xian='"+xian+"'";
						strdc=strdc+" and zd.xian='"+xian+"'";
					}
					
					if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
						whereStr=whereStr+" and zd.xiaoqu='"+xiaoqu+"'";
						str=str+" and zd.xiaoqu='"+xiaoqu+"'";
						whereStrdc=whereStrdc+" and zd.xiaoqu='"+xiaoqu+"'";
						strdc=strdc+" and zd.xiaoqu='"+xiaoqu+"'";
					}
					
					if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
						whereStr=whereStr+" and zd.jzname  like '%"+zdname+"%'";
						str = str+" and zd.jzname  like '%"+zdname+"%'";
					}
					
					if(lrrq != null && !lrrq.equals("") && !lrrq.equals("null")){
						whereStr=whereStr+" and to_char(ef.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
						str=str+" and to_char(ef.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
					}
					
				
					if(bzyf != null && bzyf != "" && !bzyf.equals("0")){
						whereStr=whereStr+" and to_char(ef.ACCOUNTMONTH,'yyyy-mm') ='"+bzyf+"'"; 
						str=str+" and to_char(ef.ACCOUNTMONTH,'yyyy-mm') ='"+bzyf+"'";
						whereStrdc=whereStrdc+" and to_char(ef.ACCOUNTMONTH,'yyyy-mm') ='"+bzyf+"'"; 
						strdc=strdc+" and to_char(ef.ACCOUNTMONTH,'yyyy-mm') ='"+bzyf+"'";
					}
					
					if(gdfstj != null && !gdfstj.equals("") && !gdfstj.equals("0")){
						whereStr=whereStr+" AND ZD.GDFS='"+gdfstj+"'";
						str=str+" AND ZD.GDFS='"+gdfstj+"'";
						whereStrdc=whereStrdc+" AND ZD.GDFS='"+gdfstj+"'";
						strdc=strdc+" AND ZD.GDFS='"+gdfstj+"'";
					}
					
					if(rgsh!=null){//首页传的值
					 manualauditstatus1=rgsh;
					}
					if(rgsh1!=null){
					manualauditstatus1=rgsh1;
					}
					String manualauditstatus3 = manualauditstatus1;
					
					if(rgsh!=null){//首页传的值
					 manualauditstatus3=rgsh;
					}
					if(rgsh1!=null){
					manualauditstatus3=rgsh1;
					}
					if(manualauditstatus3 != null && !manualauditstatus3.equals("") && !manualauditstatus3.equals("0")){
					     if(manualauditstatus3.equals("2")){//页面上2表示未通过  数据库里 0表示未通过   所以要重新赋值
					     	manualauditstatus3=0+"";
					     }
					     if(manualauditstatus3.equals("1")){
					    	 whereStr=whereStr+" and (ef.manualauditstatus='"+manualauditstatus3+"' or ef.manualauditstatus='-1')";
							 str=str+" and (ef.manualauditstatus='"+manualauditstatus3+"')";
							 whereStrdc=whereStrdc+" and (ef.manualauditstatus='"+manualauditstatus3+"' or ef.manualauditstatus='-1')";
							 strdc=strdc+" and (ef.manualauditstatus='"+manualauditstatus3+"')";
					     }else{
						whereStr=whereStr+" and ef.manualauditstatus='"+manualauditstatus3+"'";
						str=str+" and ef.manualauditstatus='"+manualauditstatus3+"'";
						whereStrdc=whereStrdc+" and ef.manualauditstatus='"+manualauditstatus3+"'";
						strdc=strdc+" and ef.manualauditstatus='"+manualauditstatus3+"'";
					     }
					}
					if(jzproperty!=null&&!jzproperty.equals("")&& !jzproperty.equals("0")){
						whereStr=whereStr+" and zd.PROPERTY='"+jzproperty+"'";
						str=str+" and zd.PROPERTY='"+jzproperty+"'";
						whereStrdc=whereStrdc+" and zd.PROPERTY='"+jzproperty+"'";
						strdc=strdc+" and zd.PROPERTY='"+jzproperty+"'";
					}
			if(sjshzt != null && !sjshzt.equals("")&& !sjshzt.equals("-1")){
				whereStr=whereStr+" and EF.CITYAUDIT='"+sjshzt+"'";
				str=str+" and EF.CITYAUDIT='"+sjshzt+"'";
				whereStrdc=whereStrdc+" and EF.CITYAUDIT='"+sjshzt+"'";
				strdc=strdc+" and EF.CITYAUDIT='"+sjshzt+"'";
			}
			if(qxzrshzt != null && !qxzrshzt.equals("")&& !qxzrshzt.equals("-1")){
				whereStr=whereStr+" and EF.COUNTYAUDITSTATUS='"+qxzrshzt+"'";
				str=str+" and EF.COUNTYAUDITSTATUS='"+qxzrshzt+"'";
				whereStrdc=whereStrdc+" and EF.COUNTYAUDITSTATUS='"+qxzrshzt+"'";
				strdc=strdc+" and EF.COUNTYAUDITSTATUS='"+qxzrshzt+"'";
			}
		if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;
       	 	shi="1";
       	 }
		//如果getlrbz==null则两条sql（电费，预付费）均执行（lrbz1="",lrbz2=""）；如果getlrbz==1,则sql1执行，sql2不执行（and 1=2），如果getlrbz==2，则sql2执行，sql1不执行（and 1=2）
		String lrbz1="";
		String lrbz2="";
		if(getlrbz != null && !getlrbz.equals("")&& !getlrbz.equals("0")){
			if("1".equals(getlrbz)){
				lrbz2="AND 1=2";
			}else if("2".equals(getlrbz)){
				lrbz1="AND 1=2";
			}	
		}
		 
  %>
			<div style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="4000px" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">工单提醒</div></td>
						<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
				        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>
                         <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超市标比例</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超省标比例</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市定标电量</div></td>
            			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">省定标电量</div></td>
            			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
                         <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">供电方式</div></td>
                         <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表用电量</div></td> 
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td> 
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账用电量</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量调整</div></td>
                         <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">(电量调整)备注</div></td>
            			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">单价</div></td>                        
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费调整</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">(电费调整)备注</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账电费</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结算周期</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超省定标电费额</div></td> 
                         <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超省定标电费比值</div></td>
            			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核状态</div></td>
            			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市级审核状态</div></td>
            			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务审核状态</div></td>
            			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>    
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入标志</div></td>    
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据金额</div></td> 
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据类型</div></td> 
                         <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
            			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>
            			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期电费</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期电量</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期单价</div></td>
                        <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期余额</div></td> 
				</tr>
				
		<%		         
		       	 List<ElectricFeesFormBean> fylist = null;
		       //更改2012-12-5
       	 if("chaxun".equals(request.getParameter("command"))||"daochu".equals(request.getParameter("command"))){
            fylist = bean.getPageDataCheck123(whereStr,str,loginId,lrbz1,lrbz2);
            allpage=bean.getAllPage();
        } else{
           fylist=null;
        }
				int countxh=1;
				 if(fylist!=null)
				{
					//int fycount = ((Integer)fylist.get(0)).intValue();
					for(ElectricFeesFormBean elecbean:fylist){
						
						String jzname = elecbean.getZdname();
						String dbname = elecbean.getDbname();
						String dfzflx = elecbean.getDfzflx();
						String accountmonth = elecbean.getAccountmonth();
						String gdfs = elecbean.getGdfs();
						String dbydl = elecbean.getDbydl();
						String lastdatetime = elecbean.getLastdatetime();
						String thisdatetime = elecbean.getThisdatetime();
						String bzydl = elecbean.getBzydl();
						String floatdegree = elecbean.getFloatdegree();
						String memo = elecbean.getMemo();
						String edhdl = elecbean.getEdhdl();
						String qsdbdl = elecbean.getQsdbdl();
						String unitprice = elecbean.getUnitprice();
						String floatpay = elecbean.getFloatpay();
						String memo1 = elecbean.getMemo1();
						String actualpay = elecbean.getActualpay();
						String jszq = elecbean.getJszq();
						System.out.print(jszq+"    zhelis  ssssssssssss");
						String csdbdfe = elecbean.getCsdbdfe();
						String csdbdfbz = elecbean.getCsdbdfbz();
						String manualauditstatus = elecbean.getRgshzt();
						String cwsh = elecbean.getCwsh();
						String sjshzt1 = elecbean.getSjshzt();
						String beilv = elecbean.getBeilv();
						String pjsj = elecbean.getPjsj();
						String stationtype1 = elecbean.getStationtype();
						String pjbh = elecbean.getPjbh();
						String pjlx = elecbean.getPjlx();
						String jfsj = elecbean.getJfsj();
						String zdid = elecbean.getZdid();
						String dbid = elecbean.getDbid();
						String blhdl = elecbean.getBlhdl();
						String csbbl = elecbean.getCsbbl();
						String csbl = elecbean.getCsbl();
						System.out.println(csbbl+"fffffffffffffdd");
						String property = elecbean.getProperty();
						String quxian = elecbean.getQuxian();
						String dfbzw = elecbean.getDfbzw();
						String uuid = elecbean.getUuid();
						String biaozhi = elecbean.getBiaozhi();
						String autoauditstatus = elecbean.getAutoauditstatus();
						String dlsh = elecbean.getDlsh();
						String autoauditdescription = elecbean.getAutoauditdescription();
						String dlshzt = elecbean.getDlshzt();
						String sort = elecbean.getSort();
						double pjje = elecbean.getPjje();
						//2014-4-15新增--上期电费-上期电量-上期单价---
						String lastelecfees = elecbean.getLastelecfees();
						String lastelecdegree = elecbean.getLastelecdegree();
						String lastunitprice = elecbean.getLastunitprice();
						String lastyue = elecbean.getLastyue();
						
						
						unitprice = Format.str4(unitprice);
						floatpay = Format.str2(floatpay);
						
						
						String tishi = "";
						
						
						
						if(null==blhdl||blhdl==""||blhdl=="o"||"null".equals(blhdl)||"".equals(blhdl))blhdl="0";
			             dfs=Double.parseDouble(blhdl);
			             if(actualpay==null||"".equals(actualpay)||"o".equals(actualpay)||"null".equals(actualpay))actualpay="0";
			             dfzs=Double.parseDouble(actualpay);
			             
						//定义提示语句框，判断自动审核状态
						 if(autoauditstatus!=null&&autoauditstatus.equals("1")&&dlsh!=null&&dlsh.equals("1")){
						 	tishi="自动审核状态：通过；";
						 }else{
						 	tishi="自动审核状态：不通过；自动审核状态不通过原因："+autoauditdescription+dlshzt;
						 }
						
						if(intnum%2==0){
						    color="#FFFFFF" ;
						 }else{
						    color="#DDDDDD";
						}
						
						intnum++;
		           ds=ds+dfs;
		           dfz=dfz+dfzs;
		           //String col="#FFFF33" ;
		           if(manualauditstatus==null||manualauditstatus.equals("0")){
		          	 color="#00FFFF";
		           }else if(manualauditstatus.equals("-1")||sjshzt1.equals("-2")){
		           	 color="#FFFF33" ;
		           
		           }
		           
		           
		           
		           
		          
		           String count1 = String.valueOf(countxh++);
		       %>
		       
		       <tr bgcolor="<%=color%>" title="<%=tishi%>">
		       
		       		<td>
		       			<%if(manualauditstatus.equals("0")){%>
							<div align="center">待审核</div>
	       				<%}else if( sjshzt1.equals("-2")&& (manualauditstatus.equals("-1")||manualauditstatus.equals("1")||manualauditstatus.equals("2")) ){%>
	       	        		<div align="center">上级退单</div>   
	       				<%}else{ %>
	       	        		<div align="center"></div>     
	       				<%} %>	
		       		</td>
		       		
		       		<td>
		       			<div align="center" ><%=count1%></div>
		       		</td>
		            <td><div align="center">
		              	<input type="checkbox" name="test[]" value="<%=uuid%>ssss=<%=biaozhi%>zzzz=<%=autoauditstatus%>" />
		              	<input type="hidden" type="checkbox" name="test1[]" value="<%=sjshzt1%>" />
		            </div></td>
		            <td><div align="center"><%=quxian%></div></td>
		       		<td>
		       			<div align="center">
								<a href="javascript:lookDetailss('<%=count1%>')"><%=jzname%></a> 
						</div>
		       			<input id="dbid" name="dbid<%=count1%>" type="hidden" value="<%=dbid%>" />
						<input id="dfzflx" name="dfzflx<%=count1%>" type="hidden" value="<%=dfzflx%>" />
						<input id="dfbzw" name="dfbzw<%=count1%>" type="hidden" value="<%=dfbzw%>" />
						<input id="acciuntmonth" name="accountmonth<%=count1%>" type="hidden" value="<%=accountmonth%>" />
		       		</td>
		       		
					<td><div align="right"><%=csbbl%>%</div></td>
					<td><div align="right"><%=csbl%>%</div></td>
					<td><div align="right"><%=edhdl%></div></td>
					<td><div align="right"><%=qsdbdl%></div></td>
					<td><div align="center"><%=accountmonth%></div></td>
					<td><div align="center"><%=gdfs%></div></td>
					<td><div align="right"><%=dbydl%></div></td>
					<td><div align="center"><%=lastdatetime%></div></td>
					<td><div align="center"><%=thisdatetime%></div></td>
					<td><div align="right"><%=blhdl%></div></td>
					<td><div align="right"><%=floatdegree%></div></td>
					<td><div align="center"><%=memo%></div></td>
					<td><div align="right"><%=unitprice%></div></td>
					<td><div align="right"><%=floatpay%></div></td>
					<td><div align="center"><%=memo1%></div></td>
					<td><div align="right"><%=actualpay%></div></td>
					<td><div align="right"><%=jszq%></div></td>
					<td><div align="right"><%=csdbdfe%></div></td>
					<td><div align="right"><%=csdbdfbz%></div></td>
					<td>
					<%if(manualauditstatus.equals("1")||manualauditstatus.equals("-1")||manualauditstatus.equals("2")){ %>
						<div align="center">通过</div>
	       			<%}else if(manualauditstatus.equals("-2")){%>
	       	        	<div align="center">不通过</div>   
	       			<%}else{ %>
	       	        	<div align="center">未审核</div>     
	       			<%} %>	
					</td>
					<td>
					<%if(sjshzt1.equals("1")){ %>
						<div align="center">通过</div>
	       			<%}else if(sjshzt1.equals("-2")){%>
	       	        	<div align="center">不通过</div>   
	       			<%}else{ %>
	       	        	<div align="center">未审核</div>     
	      		 	<%} %>		
					</td>
					<td>
					<%if(cwsh.equals("2")){ %>
						<div align="center">通过</div>
	       			<%}else if(cwsh.equals("-1")){%>
	       	        	<div align="center">不通过</div>   
	       			<%}else{ %>
	       	        	<div align="center">未审核</div>     
	       			<%} %>		
					</td>
					
					
	       			<td><div align="center"><%=dbname%></div></td>
					<td><div align="center"><%=dfzflx%></div></td>
					<td><div align="center"><%=sort%></div></td>
					<td><div align="center"><%=pjje%></div></td>
					<td><div align="center"><%=pjlx%></div></td>
					<td><div align="center"><%=stationtype1%></div></td>
					<td><div align="center"><%=zdid%></div></td>
					<td><div align="center"><%=dbid%></div></td>
					<td><div align="center"><%=property%></div></td>
					<td><div align="center" ><%=lastelecfees%></div></td>
       				<td><div align="center" ><%=lastelecdegree%></div></td>
       				<td><div align="center" ><%=lastunitprice%></div></td>
       				<td><div align="center" ><%=lastyue%></div></td>
		      	 </tr>
		       
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
            <td>&nbsp;</td> 
            
            <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            <td>&nbsp;</td> 

        </tr>
        <% }}%>
          <%
        DecimalFormat price1=new DecimalFormat("0.00");
        String s="";
        String fy="";
        s= price1.format(ds); 
        fy=price1.format(dfz);
        %>
        <tr>
        	<td align="center">合计</td>
        	<td colspan="2" align="center">电量合计：</td>
      		<td><%=s %><font size="2">度 </font></td>
      		<td colspan="2" align="center">电费合计：</td>
      		<td><%=fy %><font size="2">元 </font></td>
        </tr>
			</table>
		</div>
		
		<table width="100%" height="8%" border="0" cellspacing="0" cellpadding="0">
			 <tr><td>
			  <input type="hidden" name="sheng2" id="sheng2" value=""/>
			  <input type="hidden" name="shi2" id="shi2" value=""/>
			  <input type="hidden" name="xian2" id="xian2" value=""/>
			  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
			  <input type="hidden" name="sptype2" id="sptype2" value=""/>
			  <input type="hidden" name="manualauditstatus2" id="manualauditstatus2" value=""/>
			 </td></tr>
		   	 <tr>
                    <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                        <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                    </div></td>
		    </tr>
		    <tr><td align="right">
		      	  <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
							 <img src="<%=path %>/images/daochu.png" width="100%" height="100%">
						 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
				  </div>  
		          <div id="butongguo"
						style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:10px">
						<img alt=""
							src="<%=request.getContextPath()%>/images/baocun.png"
							width="100%" height="100%" />
						<span
							style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">退单</span>
				  </div>      
		          <div id="tongguo"
						style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
						<img src="<%=path%>/images/2chongzhi.png" width="100%"
							height="100%">
						<span
							style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">通过</span>
					</div>
					<div id="quxiao"
						style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: 30px">
						<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
						<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">撤回</span>
					</div>
					<div align="left"><font size="2" color="red">注意：黄色为财务审核不通过或市级审核不通过的单子</font></div>
		      </td></tr>		                         
		</table>		
		<input type="hidden" name="bzw1" id="bzw1" value="1" />
	</form>
</body>
<script language="javascript" >
function exportad(){
var path = '<%=path%>';
            var zdname="<%=zdname%>";
            var whereStrdc ="<%=whereStrdc%>";
            var strdc ="<%=strdc%>";
            var lrrq='<%=lrrq%>';
            var lrbz1 = '<%=lrbz1%>';
            var lrbz2 = '<%=lrbz2%>';
        	document.form1.action=path+"/web/check/人工电费审核信息.jsp?zdname="+zdname+"&whereStrdc="+whereStrdc+"&strdc="+strdc+"&lrrq="+lrrq+"&lrbz1="+lrbz1+"&lrbz2="+lrbz2+"&command=daochu";
        	
            document.form1.submit();
   }
</script>

<script type="text/javascript">
	//查询详细信息
var path = '<%=path%>';


var XMLHttpReq1;
function createXMLHttpRequest1() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq1 = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq1 = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq1 = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}
function sendRequest1(url, para) {

	createXMLHttpRequest1();
	XMLHttpReq1.open("POST", url, true);
	if (para == "checkcity1") {
		XMLHttpReq1.onreadystatechange = processResponse_checkcity1;
	} else if (para == "checkcityno1") {
		XMLHttpReq1.onreadystatechange = processResponse_checkcityno1;
	} else {
		XMLHttpReq1.onreadystatechange = processResponse;//指定响应函数
	}
	XMLHttpReq1.send(null);
}
// 处理返回信息函数
function processResponse() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq1.responseText;
			window.alert(res);
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function passCheck1(chooseIdStr1, chooseIdStr2, manpassmemo) {//通过240
	manpassmemo = encodeURIComponent(manpassmemo);
	sendRequest1(path + "/servlet/CountryElecServlet?command=checkcity&chooseIdStr1=" + chooseIdStr1 
				+ "&chooseIdStr2=" + chooseIdStr2 + "&manpassmemo=" + manpassmemo, "checkcity1");
}
function processResponse_checkcity1() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			document.form1.bzw1.value = XMLHttpReq1.responseText;
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
function passCheckNo1(chooseIdStr1, chooseIdStr2) {//取消通过1
	sendRequest1(path + "/servlet/CountryElecServlet?command=checkcityno1&chooseIdStr1="
			+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2, "checkcityno1");
}
function passCheckNo2(chooseIdStr1, chooseIdStr2) {//审核不通过1
	sendRequest1(path + "/servlet/CountryElecServlet?command=checkcityno11&chooseIdStr1="
			+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2, "checkcityno1");
}
//响应函数
function processResponse_checkcityno1() {
	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
		if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
			document.form1.bzw1.value = XMLHttpReq1.responseText;
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}
}
</script>
	<script type="text/javascript">
	var path = '<%=path%>';
//取消通过
function passCheckNo() {
	var m = document.getElementsByName('test[]');
	var m1 = document.getElementsByName('test1[]');//市级审核标志位
	var m1count = 0;
	var l = m.length;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var bzw = 1;
	var bzw1 = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
			//如果复选框选中 市级审核状态为1（通过）
			if (m1[i].value == '1') {
				m1count++;
			}
		}
	}
	if (count != 0) {
		if (m1count == 0) {
			if (count % 240 == 0) {
				n = count / 240 - 1;
			} else {
				n = (count - (count % 240)) / 240;
			}
			var r = 240 * n;
			for ( var i = 0; i < l; i++) {
				if (m[i].checked == true) {
					bz += 1;
					count1 += 1;
					
					var j = m[i].value.toString().indexOf("ssss=");
					var chooseIdStr3 = m[i].value.toString().substring(0, j);
					var zflx1 = m[i].value.toString().substring(j + 5, j + 6);
					
					if (zflx1 == "1") {
						chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
					} else if (zflx1 == "2") {
						chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
					}
				}
				bzw1 = document.form1.bzw1.value;
				if (bzw1 == "1") {
					if (count1 <= r
							|| (bz >= 239 && bz <= 241) {
						if ((bz/ 240 == 1)
								|| (bz >= 239 && bz <= 241)) {
							chooseIdStr1 = chooseIdStr1.substring(0,
									chooseIdStr1.length - 1);
							chooseIdStr2 = chooseIdStr2.substring(0,
									chooseIdStr2.length - 1);
							passCheckNo1(chooseIdStr1, chooseIdStr2);
							chooseIdStr1 = "";
							chooseIdStr2 = "";
							bz = 0;
							//document.form1.submit();	          	
						}
					} else if (count == count1 && bzw == 1) {
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						chooseIdStr2 = chooseIdStr2.substring(0,
								chooseIdStr2.length - 1);
						bzw = 0;
						document.form1.action = path
								+ "/servlet/CountryElecServlet?command=checkcityno&chooseIdStr1="
								+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
						document.form1.submit();
						showdiv("请稍等..............");
					}
				} else if (bzw1 == "0") {
					document.form1.action = path + "/web/msg.jsp";
					document.form1.submit();
					return;
				}
			}
		} else {
			alert("选中的电费单中含有市级已通过的，不允许此操作");
		}
	} else {
		alert("请选择信息！");
	}
}
//审核不通过
function passCheckNoPass() {
	var m = document.getElementsByName('test[]');
	var m1 = document.getElementsByName('test1[]');//市级审核标志位
	var m1count = 0;
	var l = m.length;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	var bz = 0;
	var n = 0;
	var count = 0;
	var count1 = 0;
	var bzw = 1;
	var bzw1 = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
			//如果复选框选中 市级审核状态为1（通过）
			if (m1[i].value == '1') {
				m1count++;
			}
		}
	}
	if (count != 0) {
		if (m1count == 0) {
			if (count % 240 == 0) {
				n = count / 240 - 1;
			} else {
				n = (count - (count % 240)) / 240;
			}
			var r = 240 * n;
			for ( var i = 0; i < l; i++) {
				if (m[i].checked == true) {
					bz += 1;
					count1 += 1;
					
					var j = m[i].value.toString().indexOf("ssss=");
					var chooseIdStr3 = m[i].value.toString().substring(0, j);
					var zflx1 = m[i].value.toString().substring(j + 5, j + 6);
					
					if (zflx1 == "1") {
						chooseIdStr1 = chooseIdStr1 + "'" + chooseIdStr3 + "',";
					} else if (zflx1 == "2") {
						chooseIdStr2 = chooseIdStr2 + "'" + chooseIdStr3 + "',";
					}
				}
				bzw1 = document.form1.bzw1.value;
				if (bzw1 == "1") {
					if (count1 <= r
							|| (bz >= 239 && bz <= 241) {
						if ((bz / 240 == 1)
								|| (bz >= 239 && bz <= 241)) {
							chooseIdStr1 = chooseIdStr1.substring(0,
									chooseIdStr1.length - 1);
							chooseIdStr2 = chooseIdStr2.substring(0,
									chooseIdStr2.length - 1);
							passCheckNo2(chooseIdStr1, chooseIdStr2);
							chooseIdStr1 = "";
							chooseIdStr2 = "";
							bz = 0;
						}
					} else if (count == count1 && bzw == 1) {
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						chooseIdStr2 = chooseIdStr2.substring(0,
								chooseIdStr2.length - 1);
						bzw = 0;
						document.form1.action = path
								+ "/servlet/CountryElecServlet?command=checkcityno2&chooseIdStr1="
								+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
						document.form1.submit();
						showdiv("请稍等..............");
					}
				} else if (bzw1 == "0") {
					document.form1.action = path + "/web/msg.jsp";
					document.form1.submit();
					return;
				}
			}
		} else {
			alert("选中的电费单中含有市级已通过的，不允许此操作");
		}
	} else {
		alert("请选择信息！");
	}
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
 function chooseAll() { 
        var qm = document.getElementsByName('test');
        //alert(qm[0].checked);  
        var m = document.getElementsByName('test[]');   
        var l = m.length; 
        if(qm[0].checked == true){
          for (var i = 0; i < l; i++) {   
            m[i].checked = true;   
          }  
        }else{
          for (var i = 0; i < l; i++) {   
            //m[i].checked == true ? m[i].checked = false: m[i].checked = true; 
            m[i].checked = false;  
          }   
        }
        
    }   
    	document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.jzproperty.value='<%=jzproperty%>';
		document.form1.manualauditstatus.value='<%=manualauditstatus1%>';
		document.form1.sjshzt.value='<%=sjshzt%>';
		document.form1.qxzrshzt.value='<%=qxzrshzt%>';
		document.form1.gdfs.value='<%=gdfstj%>';
		document.form1.getlrbz.value='<%=getlrbz%>';

 </script>

</html>

