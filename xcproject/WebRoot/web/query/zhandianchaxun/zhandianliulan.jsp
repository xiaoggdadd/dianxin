<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.regex.Pattern"%>
<%@ page import="com.noki.zhandian.*"%>

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
       //System.out.println("logManage.jsp>>"+beginTime);
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
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
    	String sptype=request.getParameter("sptype")!=null?request.getParameter("sptype"):"0";
   		String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
  		String jzproperty=request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
  
  
    String canshuStr="";
    String color=null;
    int intnum = 0;
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((beginTimeQ!=null)&&(endTimeQ!=null))||(zdname!=null)||(sptype!=null)||(zdlx!=null)||(jzproperty!=null)||(manualauditstatus1!=null)){
         canshuStr= "shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&beginTimeQ="+beginTimeQ+"&endTimeQ="+endTimeQ+"&zdname="+zdname+"&sptype="+sptype+"&zdlx="+zdlx+"&jzproperty="+jzproperty+"&manualauditstatus1="+manualauditstatus1;
     }
    //String roleId = account.getRoleId();
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

    
    function queding() {
	if (document.getElementById("ammeterid2").value == "") {
		alert("请选择一个站点！");
	} else {

		//window.opener.document.all.ammeterdegreeidFk.value = document.getElementsByName("degreeid").value;
		//window.opener.document.form1.action=path+"/servlet/electricfees?action=showall";
		window.opener.document.form1.action = path
				+ "/web/query/zhandianchaxun/zhandianchaxun.jsp?ammeterid="
				+ document.getElementById("ammeterid2").value;
		window.opener.document.form1.submit();
		window.close();
	}
}
    function queryDegree(){
			document.form1.action=path+"/web/query/zhandianchaxun/zhandianliulan.jsp?command=chaxun";
             document.form1.submit();
    }
    
    
    
    $(function(){
		$("#chaxun").click(function(){
			queryDegree();
			//showdiv("请稍等..........");
		});
		$("#queding").click(function() {
			queding();
		});
		$("#cancelBtn").click(function() {
			javascript: window.close();
		
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
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点查询</span>	
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
  					<td>站点名称：</td>
  					<td><input type="text" name="zdname" value="" class="form" /></td>
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
		
		
		<table>
			 <tr><td colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
              </div></td></tr>
		</table>
		
		<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px">
			<table width="100%" height="70%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
			    <tr height = "23" class="relativeTag">
			    <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/></div></td>
			    <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>
                <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">地区</div></td>
                <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类别</div></td> 
                <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点归属</div></td>
                <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">供电方式</div></td>
                <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">初始使用时间</div></td>
                <td width="11%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表倍率</div></td>
                <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">负责人</div></td>
			</tr>
				
		<%		         
		List<ZhandianBean> fylist=null;
		     
       	 if("chaxun".equals(request.getParameter("command"))){
       		String whereStr = "";
						//whereStr = whereStr + " and DB.DBYT='dbyt03'";
			if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " and shi='" + shi + "'";
			}
			if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " and xian='" + xian + "'";
			}
			if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
			whereStr = whereStr + " and xiaoqu='" + xiaoqu + "'";
			}
			if (zdname != null && zdname != "" && !zdname.equals("0")) {
			whereStr = whereStr + " and jzname like '%" + zdname + "%'";
			}
			ZhanDianDao dao = new ZhanDianDao();
			fylist= dao.getZD(whereStr);
        } else{
           fylist=null;
        }
			int countxh=1;
			String jzname = "";
			String zdid = "";
			String diqu = "";
			String zdtype = "";
			String zdgs = "";
			String gdfs = "";
			String begin = "";
			String dbbl = "";
			String fzr = "";
			if(fylist!=null){
			  for(ZhandianBean z:fylist){
				  	jzname = z.getZdname();
					zdid = z.getZhandianID();
					diqu = z.getXian();
					zdtype = z.getZdtype();
					zdgs = z.getZdgs();
					gdfs = z.getGdfs();
			        begin = z.getBegin();
				    dbbl = z.getDbbl();
					fzr = z.getFzr();
					if(fzr==null||"".equals(fzr)){fzr="";}
					System.out.println("jzname:" + jzname + " zdid:" + zdid);
				
					 if(intnum%2==0){
						
					    color="#FFFFFF" ;
		
					 }else{
					    color="#DDDDDD";
					}
		           intnum++;
		
		       %>
		      
		       <tr bgcolor="<%=color%>" title="">
		       		
		       		 <td align="center">
						<input type="checkbox" name="cbox" value="<%=zdid%>" onClick="chooseOne(this);" />
					</td>
		            <td>
		       			<div align="center" ><%=zdid %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=jzname %></div>
		       		</td>
					 <td>
		       			<div align="center" ><%=diqu %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=zdtype %></div>
		       		</td>	
		       		<td>
		       			<div align="center" ><%=zdgs %></div>
		       		</td>	       		
		       		<td>
		       			<div align="center" ><%=gdfs %></div>
		       		</td>
		       			<td>
		       			<div align="center" ><%=begin %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=dbbl %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=fzr %></div>
		       		</td>
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
         </tr>
      <% }}else if(!(intnum > 17)){
    	  for(int i=((intnum-1)%17);i<17;i++){
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
        <% }}%>
			</table>
		</div>
		<table width="100%" height="8%" border="0" cellspacing="0" cellpadding="0">
			 <tr><td>
			  <input type="hidden" name="sheng2" id="sheng2" value=""/>
			  <input type="hidden" name="shi2" id="shi2" value=""/>
			  <input type="hidden" name="xian2" id="xian2" value=""/>
			  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
			  <input type="hidden" id="ammeterid2" value="" />
			 </td></tr>
			  <tr>
                  <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                        <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                    </div></td>
		    </tr>
		    <tr><td align="right">
				  <div id="cancelBtn"  style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 2px">
					<img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
						 <span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">取消</span>
					</div>
                    <div id="queding" style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right: 7px">
					 <img alt="" src="<%=path%>/images/tijiao.png" width="100%" height="100%" />
							<span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">确定</span>
					</div>       	
		      </td></tr>
		</table>
		
			
	</form>
</body>


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

  <!--多选框选择 -->
 <script type="text/javascript">
 function chooseOne(cb) {
	//先取得同name的chekcBox的集合物件   
	var obj = document.getElementsByName("cbox");
	for (i = 0; i < obj.length; i++) {
		//判斷obj集合中的i元素是否為cb，若否則表示未被點選   
		if (obj[i] != cb) {
			obj[i].checked = false;
		} else {
			obj[i].checked = true;
			document.getElementById("ammeterid2").value = obj[i].value;

		}
	}
	// alert(document.getElementById("ammeterid2").value);   
}   
    
    	document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
 </script>

</html>

