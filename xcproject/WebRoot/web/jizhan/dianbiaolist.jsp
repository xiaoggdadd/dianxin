<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.DianBiaoBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<% 	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
	String loginId=account.getAccountId();
	String bgsign=request.getParameter("bgsign")!=null?request.getParameter("bgsign"):"-1";//获取当前页 是否标杆 
     String dfzflx1=request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";//获取当前页面 电费支付类型 dfzflx的值
     String dbyt=request.getParameter("dbyt")!=null?request.getParameter("dbyt"):"0";//获取当前页 电表的 用途 dbyt
     
    String jzproperty=request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";//获取站点属性 
    String sptype = request.getParameter("sptype")!=null?request.getParameter("sptype"):"0";//
    String dbqyzt = request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"-1";//电表启用状态  
    String keyword="";
    String zdqyzt= request.getParameter("zdqyzt")!=null?request.getParameter("zdqyzt"):"1";//站点启用状态
    
	String sheng = (String)session.getAttribute("accountSheng"); 
	//String sheng = "137";
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
       //System.out.println("logManage.jsp>>"+beginTime);
	String sdbid = request.getParameter("sdbid")!=null?request.getParameter("sdbid"):"";
  	String zdmc=request.getParameter("zdmc")!=null?request.getParameter("zdmc"):"";
  	String dbyt01 = request.getParameter("dbyt01");//结算电表
   	String dbyt03 = request.getParameter("dbyt03");//管理电表
   	System.out.println("结算"+dbyt01+"管理"+dbyt03);
    if(dbyt01!=null){
	   	dbyt=dbyt01;
	}
	if(dbyt03!=null){
	   	dbyt=dbyt03;
	}

	

		   String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
		   int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
		   String color=null;
           curpage=Integer.parseInt(s_curpage);
           String loginName = (String)session.getAttribute("loginName");
		   String roleId = (String)session.getAttribute("accountRole");
		   System.out.println("@@@@@@@@@@@@@@电表"+roleId);
           String permissionRights="";
%>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0104");
System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>

<html>
<head>
<title>电表列表</title>
<style>
            .btn {
             BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7b9ebd 1px solid
            }
            .btn1_mouseout {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#B3D997); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn1_mouseover {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#CAE4B6); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn2 {padding: 2 4 0
            4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;}
            .btn3_mouseout {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mouseover {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mousedown
            {
             BORDER-RIGHT: #FFE400 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #FFE400 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #FFE400
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #FFE400 1px solid
            }
            .btn3_mouseup {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn_2k3 {
             BORDER-RIGHT: #002D96 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #002D96 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); BORDER-LEFT: #002D96
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #002D96 1px solid
            }
            
            .style1 {
	color: #FF9900;
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
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px

		}
.buttonstyle{width:80px; height:24px; color:#fff;box-shadow: 0 0 0 15px #1E90FF inset;border-style:none;border-radius:5px;font-size:14px;font-weight:bold;font-family:微软雅黑}
.buttonstyle:hover{box-shadow: 0 0 0 12px #0B6AFF inset;cursor:pointer;transition:box-shadow 1.5s;}

a:active {color:#0000ff; text-decoration:none;font-size:12px;}
a:hover {color:#0000ff; text-decoration: none;font-size:12px;}
a:link {color: #1E90FF; text-decoration: none;font-size:12px;}
a:visited {color: #0000ff; text-decoration: none;font-size:12px;}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
}
.bttcn{color:BLACK;font-weight:bold;}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
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
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">";

		}
		else
		{
			trObject.style.display = "none";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">";
		}
}

  function chaxun(){
					document.form1.action=path+"/web/jizhan/dianbiaolist.jsp";
					document.form1.submit();//提交按钮  input
	}
	function delLogs(){
		

	}
	function addJz(){
		document.form1.action=path+"/web/jizhan/adddianbiao.jsp";
		document.form1.submit();
	}
	function daorujz(){
		document.form1.action=path+"/web/jizhan/zhandiandaoru.jsp?servletname=zhandiandaoru&action=updianbiao";
		document.form1.submit();
	}
	function dianbiaodaochu(){
		var sheng = '<%=sheng%>';
		var shi = '<%=shi%>';
		var xian = '<%=xian%>';
		var xiaoqu = '<%=xiaoqu%>';
		var dbid = '<%=sdbid%>';
		
		var tt = 'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no';
		
		var url = path+"/web/jizhan/dc_dianbiao.jsp?sheng="+sheng+"&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&dbid="+dbid;
		window.open(url,"dianbiaodc",tt);
	}
	$(function(){

		$("#query").click(function(){
			chaxun();
		});
		$("#xinzengBtn").click(function(){
			addJz();
			showdiv("请稍等..............");
		});
		$("#daoruBtn").click(function(){
			daorujz();
			showdiv("请稍等..............");
		});
	});

</script>

</head>

<body class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
			<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		<tr>
			<td style="font-size:14px;height:30px;font-famliy: 微软雅黑;color:#ffffff;font-weight:bold;background-color:#C91717;">&nbsp;电表管理
	    	</td>
	    </tr>	
<!-- 		<tr> -->
<!-- 		<td height="30" colspan="4"> -->
<!--    				<div id="parent" style="display:inline"> -->
<!--                      <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div> -->
<!--                 </div> -->
<!-- 	        </td> -->
<!-- 	    </tr> -->
	    <tr><td width="1200">
	    	<table border='0'>
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
	                    <option value="<%=agcode%>"><%=agname%></option>
	                    <%}
		         	}
		         %>
         		</select>
         		</td>        						
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
				<td>乡镇：</td><td><select name="xiaoqu" class="selected_font">
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
			<td> <%
												if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){
												%>
<!--          		<div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;right:-225px;float:right;top:0;"> -->
<!-- 				<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" /> -->
<!-- 				<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> -->
<!-- 			</div> -->
			<button id="query" class="buttonstyle">查询</button>
         		<%}%>   
 				
			</td>
			</tr>
	</table>
	
	
	
	
	</td></tr>	
 </table>
 
 <div style="width:88%;" > 
		<p id="box3" style="display:none">
			<table border='0'>
			<tr class="form_label">
 
         	<td>电表ID：</td>
            <td><input type="text" name="sdbid" value="<%=sdbid%>" class="selected_font"/></td>
        	<td>站点名称：</td>
           <td><input type="text" name="zdmc" value="<%=zdmc%>" class="selected_font"/></td>    
         
          <!-- 添加 -->      	
  
<!--   			<td>集团报表类型：</td> -->
<!--          	<td><select name="sptype" class="selected_font" onchange="javascript:document.form1.sptype2.value=document.form1.sptype.value"> -->
<!--          		<option value="0">请选择</option> -->
<!-- 	          -->
<!-- 	         	ArrayList sptypelist = new ArrayList(); -->
<!-- 	         	sptypelist = commBean.getStationPointType(); -->
<!-- 	         	if(sptypelist!=null){ -->
<!-- 	         		String sfid="",sfnm=""; -->
<!-- 	         		int scount = ((Integer)sptypelist.get(0)).intValue(); -->
<!-- 	         		for(int i=scount;i<sptypelist.size()-1;i+=scount) -->
<!--                     { -->
<!--                     	sfid=(String)sptypelist.get(i+sptypelist.indexOf("CODE")); -->
<!--                     	sfnm=(String)sptypelist.get(i+sptypelist.indexOf("NAME")); -->
<!--                     %> -->
<!--                     <option value="=sfid%>">=sfnm%></option> -->
<!--                     } -->
<!-- 	         	} -->
<!-- 	         </select>-->
<!--               </td>         -->
         	
<!--          	<td>站点属性：</td> -->
<!--          	<td><select name="jzproperty" class="selected_font" onchange="kzinfo()"> -->
<!--          		<option value="0">请选择</option> -->
<!--          		 -->
<!-- 	         	ArrayList zdsx = new ArrayList(); -->
<!-- 	         	zdsx = ztcommon.getSelctOptions("zdsx"); -->
<!-- 	         	if(zdsx!=null){ -->
<!-- 	         		String code="",name=""; -->
<!-- 	         		int cscount = ((Integer)zdsx.get(0)).intValue(); -->
<!-- 	         		for(int i=cscount;i<zdsx.size()-1;i+=cscount) -->
<!--                     { -->
<!--                     	code=(String)zdsx.get(i+zdsx.indexOf("CODE")); -->
<!--                     	name=(String)zdsx.get(i+zdsx.indexOf("NAME")); -->
<!--                     %> -->
<!--                     <option value="=code%>">=%></option> -->
<!--                     } -->
<!-- 	         	} -->
<!-- 	          -->
<!--          	</select></td> -->
         	</tr>
         	<tr class="form_label">
<!--          	<td>是否标杆：</td>	 -->
<!--          	<td><select name="bgsign" class="selected_font"> -->
<!--          	  <option value="-1">请选择</option> -->
<!--          	   <option value="0">否</option> -->
<!--          		<option value="1">是</option> -->
         		
<!--          	</select></td> -->
<!--          	<td>电表用途：</td>        	 -->
<!--          	<td><select name="dbyt" class="selected_font"> -->
<!--          	<option value="0">请选择</option> -->
<!--          		 -->
<!-- 	         	ArrayList dbyt1 = new ArrayList(); -->
<!-- 	         	dbyt1 = ztcommon.getSelctOptions("DBYT"); -->
<!-- 	         	if(dbyt1!=null){ -->
<!-- 	         		String code="",name=""; -->
<!-- 	         		int cscount = ((Integer)dbyt1.get(0)).intValue(); -->
<!-- 	         		for(int i=cscount;i<dbyt1.size()-1;i+=cscount) -->
<!--                     { -->
<!--                     	code=(String)dbyt1.get(i+dbyt1.indexOf("CODE")); -->
<!--                     	name=(String)dbyt1.get(i+dbyt1.indexOf("NAME")); -->
<!--                     %> -->
<!--                     <option value="=code%>">=%></option> -->
<!--                     } -->
<!-- 	         	} -->
<!-- 	          -->
         	
<!--          	</select></td> -->
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
         	<td>电表启用状态：</td>
         	<td><select name="dbqyzt" class="selected_font">
         	  <option value="-1">请选择</option>
         	   <option value="0">否</option>
         		<option value="1">是</option>
         		
         	</select></td>
         	  </tr>
         	  <tr class="form_label">
         	  <td>站点启用状态：</td>
         	<td><select name="zdqyzt" class="selected_font">
         	<option value="1">是</option>
         	  <option value="-1">请选择</option>
         	   <option value="0">否</option>
         		
         	</select></td></tr>
			</table>
	</div>	
       
  					 <div id="parent" style="display:inline">
                          <div style="font-size:14px;height:30px;font-famliy: 微软雅黑;color:#000000;font-weight:bold;background-color:#F5F5F5;">&nbsp;电表信息一览&nbsp;</div>
                      </div>
  					<button id="xinzengBtn" class="buttonstyle">新增</button>&nbsp;&nbsp;&nbsp;
  					<button id="daoruBtn" class="buttonstyle">导入</button>
  					<br><br>
  					<table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                           <tr  bgcolor="#DDDDDD"><td width="5%" height="23"><div align="center"  class="bttcn">序号</div></td>                               
                                <td nowrap height="23"><div align="center" class="bttcn">站点名称</div></td>
                                <td nowrap height="23"><div align="center" class="bttcn">站点ID</div></td>
                                <td nowrap height="23"><div align="center" class="bttcn">所在地区</div></td>
                                <td nowrap height="23"><div align="center" class="bttcn">站点类型</div></td>
<!--                                 <td nowrap height="23"><div align="center" class="bttcn">所属专业</div></td> -->
								<td nowrap height="23"><div align="center" class="bttcn">电表用途</div></td>
            					<td nowrap height="23"><div align="center" class="bttcn">电费支付类型</div></td>
								<td nowrap height="23"><div align="center" class="bttcn">初始读数</div></td>
            					<td nowrap height="23"><div align="center" class="bttcn">初始使用时间</div></td>
                                <td nowrap height="23"><div align="center" class="bttcn">倍率</div> </td>
                                <td nowrap height="23"><div align="center" class="bttcn">电表型号</div></td>
                                <td nowrap height="23"><div align="center" class="bttcn">备注</div></td>
                                <td nowrap height="23" ><div align="center" class="bttcn">电表ID</div></td>
                                <td nowrap height="23"><div align="center" class="bttcn">编辑</div></td>
                                <td nowrap height="23"><div align="center" class="bttcn">删除</div></td>
           				 </tr>
       <%
       DianBiaoBean bean = new DianBiaoBean();
       	 ArrayList fylist = new ArrayList();
       	 System.out.println("--------------"+zdqyzt);
       	 fylist = bean.getPageData(curpage,sheng,shi,xian,xiaoqu,sdbid,loginName,zdmc,loginId,sptype,jzproperty,dbyt,dfzflx1,bgsign,dbqyzt,zdqyzt,keyword);
       	 allpage=bean.getAllPage();
		String dbid = "",zdname = "",area = "", sszy= "",dbyt3="",csds="",cssytime="",beilv="",id="",dbxh="",memo="",dfzflx2="",zdlx="",zdid="";
		int intnum=xh = pagesize*(curpage-1)+1;
		Double bl=0.00;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     	//num为序号，不同页中序号是连续的
		     	id = (String)fylist.get(k+fylist.indexOf("ID"));
				dbid = (String)fylist.get(k+fylist.indexOf("DBID"));
		    	zdname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    	zdid=(String)fylist.get(k+fylist.indexOf("ZDID"));
		    	area = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		    	zdlx = (String)fylist.get(k+fylist.indexOf("ZDLX"));
		  		//  System.out.println("所在地区..."+area);
		    	sszy = (String)fylist.get(k+fylist.indexOf("SSZY"));
				dbyt3 = (String)fylist.get(k+fylist.indexOf("DBYT"));
				csds = (String)fylist.get(k+fylist.indexOf("CSDS"));
				cssytime = (String)fylist.get(k+fylist.indexOf("CSSYTIME"));
				beilv = (String)fylist.get(k+fylist.indexOf("BEILV"));
				dbxh = (String)fylist.get(k+fylist.indexOf("DBXH"));
				memo = (String)fylist.get(k+fylist.indexOf("MEMO"));
				dfzflx2 = (String)fylist.get(k+fylist.indexOf("DFZFLX"));
				
				if(csds==null||csds.equals(""))csds="0";
				DecimalFormat price = new DecimalFormat("0.0");
				csds = price.format(Double.parseDouble(csds));			
			
				if(cssytime==null)cssytime="";
				if(beilv==null)beilv="";
				DecimalFormat mat=new DecimalFormat("0.00");
			    if(beilv==null||beilv==""||beilv=="o")beilv="0";
	            bl=Double.parseDouble(beilv);
	            beilv=mat.format(bl);
				if(dbxh==null)dbxh="";
				if(memo==null)memo="";
				if(sszy==null)sszy="";
				if(dbyt3==null)dbyt3="";
				if(dfzflx2==null)dfzflx2="";
				if(intnum%2==0){
				    color="#DDDDDD";
	
				 }else{
				    color="#FFFFFF" ;
				}

       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=intnum++%></div>
       		</td>
       		<td>
       			<div align="left" ><%=zdname%></div>
       		</td>
       		<td>
       			<div align="left"><%=zdid %></div>
       		</td>
       		<td>
       			<div align="left" ><%=area%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdlx%></div>
       		</td>
<!--        		<td> -->
<!--        			<div align="center" >=sszy%></div> -->
<!--        		</td> -->
       		<td>
       			<div align="center" ><%=dbyt3%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dfzflx2%></div>
       		</td>
       		<td>
       			<div align="right" ><%=csds%></div>
       		</td>
       		<td>
       			<div align="center" ><%=cssytime%></div>
       		</td>
       		<td>
       			<div align="right" ><%=beilv%></div>
       		</td>
       	<td>
       			<div align="center" ><%=dbxh%></div>
       		</td>
       		<td>
       			<div align="center" ><%=memo%></div>
       		</td>
       		<td>
       			<div align="left" ><%=dbid%></div>
       		</td>
       		<td>
       			<div align="center" ><a href="#" onclick="bianji('<%=id%>','<%=zdid%>')"><font style="font-size: 12px;">编辑</font></a></div>
       		</td>
       		<td>
       			<div align="center" ><a href="#" onclick="delzd('<%=id%>')"><font style="font-size: 12px;">删除</font></a></div>
       		</td>

       </tr>
       <%} %>
     <% if (intnum==0){
    	  System.out.println("QQQQ"+intnum);
         for(int i=0;i<15;i++){
          if(i%2==0){
			    color="#DDDDDD" ;
          }else{
			    color="#FFFFFF";
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
					
            </tr>
      <% }}else if(!(intnum > 15)){
    	  for(int i=((intnum-1)%15);i<15;i++){
            if(i%2==0)
			    color="#FFFFFF";
            else
			    color="#DDDDDD";
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
			 	           
                         
        </tr>
        <% }}%>
       <tr bgcolor="#ffffff"  >
					<td colspan="16" >
						<div align="left">
							<font color='#1E90FF'>&nbsp;页次:</font>
							&nbsp;&nbsp;
							 <b><font color=red><%=curpage%></font></b>

							 <font color='#1E90FF'>/<b><%=allpage%></b>&nbsp;</font>
							 &nbsp;&nbsp;
							 <font color='#1E90FF'>
						     <% if (curpage!=1)
						       {out.print("<a href='javascript:gopagebyno(1)'>首页</a>");}
						      else
						      {out.print("首页");}
						      %>
					     </font>
					     &nbsp;&nbsp;
							 <font color='#1E90FF'>
						     <%if(curpage!=1)
						          {out.print("<a href='javascript:previouspage()'>上页</a>");}
						         else
						       {out.print("上页");}
						      %>
						   </font>
						   &nbsp;&nbsp;
							<font color='#1E90FF'>
						     <% if(allpage!=0&&(curpage<allpage))
						         {out.print("<a href='javascript:nextpage()'>下页</a>");}
						         else
						        {out.print("下页");}
						     %>
             </font>
							&nbsp;&nbsp;
							<font color='#1E90FF'>
					     <% if(allpage!=0&&(curpage<allpage))
					         {out.print("<a href='javascript:gopagebyno("+allpage+")'>尾页</a>");}
					        else
					        {out.print("尾页");}
					     %>
            </font>
            &nbsp;&nbsp;
						<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" style="width:70px;font-family: 宋体;font-size:12px;line-height:120%;">
					     <%for(int i=1;i<=allpage;i++)
					         {if(curpage==i){out.print("<option value='"+i+"' selected='selected'>"+i+"</option>");}
					      else{out.print("<option value='"+i+"'>"+i+"</option>");}
					         }
					     %>
				    </select>


						</div>
					</td>
				</tr>
       <%}%>


  	 </table> 
  	  <table width="60%"  border="0" cellspacing="0" cellpadding="0" align="right" height="5%">
  			<tr align="right">
                      <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"></div><div style="width:300px;display:inline;"></div>
                      </div></td>
                    </tr>
			<tr><td>							
			<%if(!roleId.equals("101")){
				if(permissionRights.indexOf("PERMISSION_ADD")>=0){
			%>
<!-- 				<button id="daoruBtn" class="buttonstyle">导入</button> -->
<!--  				<div id="daoruBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px"> -->
<!--  					 <img src="<%=path %>/images/daoru.png" width="100%" height="100%"> -->
<!-- 					 <span style="font-size:12px;position: absolute;left:27px;top:3px;color:white">导入</span>  -->
<!-- 				</div>  -->
<!-- 				<div id="xinzengBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:15px"> -->
<!-- 					 <img src="<%=path %>/images/xinzeng.png" width="100%" height="100%"> -->
<!-- 					 <span style="font-size:12px;position: absolute;left:27px;top:3px;color:white">新增</span>       -->
<!-- 				</div> -->
<!-- 				<button id="xinzengBtn" class="buttonstyle">新增</button> -->
        	<%	}
        	  }%>
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
      		document.form1.action=path+"/web/jizhan/dianbiaolist.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/jizhan/dianbiaolist.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/jizhan/dianbiaolist.jsp?curpage="+pageno;
      document.form1.submit();
     }
     function delzd(id){
     	<%
			if(permissionRights.indexOf("PERMISSION_DELETE")>=0){
		%>		if(confirm("您确认要删除本条电表信息？")){
		     		document.form1.action=path+"/servlet/dianbiao?action=del&id="+id
		      		document.form1.submit();
		      		showdiv("请稍等..............");
		      	}
      <%
    	}else{
      %>
      		alert("您没有删除站点的权限");
      <%}%>
     		
    }
    function bianji(id,zdid){
    	<%
			if(permissionRights.indexOf("PERMISSION_EDIT")>=0){
			%>
     		document.form1.action=path+"/web/jizhan/modifydianbiao.jsp?id="+id+"&zdid="+zdid
      		document.form1.submit();
      <%
    }else{
      %>
      alert("您没有编辑站点信息的权限");
    <%}%>
    	
    }

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
				}catch (e){}
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
            };
        };
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
	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
	 //alert("11111");
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
	//alert(sid);
	if(sid=="0"){
	alert(sid);
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
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
//document.form1.sptype.value='<%=sptype%>';
//document.form1.jzproperty.value='<%=jzproperty%>';
//document.form1.dbyt.value='<%=dbyt%>';
document.form1.dfzflx.value='<%=dfzflx1%>';
//document.form1.bgsign.value='<%=bgsign%>';
document.form1.dbqyzt.value='<%=dbqyzt%>';
document.form1.zdqyzt.value='<%=zdqyzt%>';

 </script>
</html>


