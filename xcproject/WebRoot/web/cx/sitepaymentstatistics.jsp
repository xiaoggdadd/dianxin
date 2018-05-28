<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@page import="com.noki.mobi.cx.SitePaymentStatistics;"%>

<%
	String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
       //System.out.println("logManage.jsp>>"+beginTime);
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	int intnum=0;
	String color="";
	System.out.println("operName="+operName);
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        String loginId1 = request.getParameter("loginId");
        System.out.println("权限："+loginId+"权限2"+loginId1);
        System.out.println("权限;"+loginId1);
        String loginName = account.getAccountName();
        
           String sheng = (String)session.getAttribute("accountSheng");
		String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
		String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
		String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
         String sptype = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
         String chanquan = request.getParameter("chanquan");
         String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
        String  sfjf=request.getParameter("sfjf")!=null?request.getParameter("sfjf"):"-1";
         String dbyt=request.getParameter("dbyt")!=null?request.getParameter("dbyt"):"0";
         String htmlsql = request.getParameter("htmlsql");
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
	color: #FF9900;
	font-weight: bold;
}
.relativeTag   {   
    z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop); 
	text-align: left;
	font-family: 宋体;
	font-size: 12px;     
}; 
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
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
 </style>
<style type="text/css" media=print>.noprint{display : none }</style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
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

 
    function queryDegree(){
                    if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null)
		          {
	                   alert("城市不能为空");
	   
	           }else{
                   document.form1.action=path+"/web/cx/sitepaymentstatistics.jsp";
                   document.form1.submit();
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
		queryDegree();
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
permissionRights=commBean.getPermissionRight(roleId,"0501");

System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<body  class="body">
<form action="" name="form1" method="POST">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
	<tr><td colspan="4">
		 <div style="width:700px;height:50px">
		       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点交费情况统计</span>	
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
	                    <option value="<%=agcode%>"><%=agname%></option>
	                    <%}
		         	}
		         %>
         </select><span style="color: #FF0000;font-weight: bold">&nbsp;*</span></td>
		<td>区县:</td><td><select name="xian" class="selected_font" onchange="changeCountry()">
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
		<td>乡镇:</td><td><select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
		  <td>是否交费：</td><td><select name="sfjf" class="selected_font">
         	  <option value="-1">请选择</option>
         	   <option value="0">否</option>
         		<option value="1">是</option>
         </select></td>
		
		<td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							</font>
					</p></td>
			<td><%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
                <div id="query" style="position:relative;width:60px;height:23px;cursor: pointer;right:-100px;float:right;top:0;">
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
		  <tr class="selected_font">
		   <td>站点类型：</td><td><select name=jzproperty class="selected_font" onchange="kzinfo()">
         		<option value="0">请选择</option>
	         <%
	         	ArrayList sptypelist = new ArrayList();
	         	sptypelist = ztcommon.getSelctOptions("StationType");
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
		<td>站点名称：</td><td><input type="text" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" class="selected_font"/></td>
		<td>报账月份：</td><td><input type="text" name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font"/></td>
		<td>交费月份:</td><td><input type="text" name="endTime" value="<%if(null!=request.getParameter("endTime")) out.print(request.getParameter("endTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font"/></td>
		
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
  		 SitePaymentStatistics bean = new SitePaymentStatistics();
         String whereStr = "";
         String str="";
         System.out.println("dianliangListjsp--------------:"+sptype+chanquan);
			if(shi != null && !shi.equals("")&& !shi.equals("0")){
				whereStr=whereStr+" and Z.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("")&& !xian.equals("0")){
				whereStr=whereStr+" and Z.XIAN='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
				whereStr=whereStr+" and Z.XIAOQU='"+xiaoqu+"'";
			}
			if(sptype != null && !sptype.equals("")&& !sptype.equals("0")){
				whereStr=whereStr+" and Z.STATIONTYPE='"+sptype+"'";
			}
			if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
				whereStr=whereStr+" and Z.JZNAME like '%"+zdname+"%'";
			}if( beginTime!= null && !beginTime.equals("")&& !beginTime.equals("0")){
				//whereStr=whereStr+" and E.ACCOUNTMONTH = '"+beginTime+"'";
				str=str+" and TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm') = '"+beginTime+"'";
			}if(endTime != null && !endTime.equals("")&& !endTime.equals("0")){
				//whereStr=whereStr+" and E.PAYDATETIME >'"+endTime+"-01"+"'   and E.PAYDATETIME <'"+endTime+"-31"+"' ";
				str=str+" and TO_CHAR(A.ENDMONTH,'yyyy-mm') <='"+endTime+"'";
			}
		if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;
       	 	System.out.println("权限："+loginId+"权限2"+loginId1);
       	 	shi="1";
       	 }
		if(shi != null && !shi.equals("") && !shi.equals("0")){
			
			String count1=bean.getCount(whereStr,loginId,sfjf,str);		
  		%>		
  			<div><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总共</font><font size="2" color="red"><%=count1 %></font><font size="2">条  |</font> 	       
  	      	</div>
  		<%}%>
		<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px">
		<table width="1100px" height="80%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
					   <tr height = "20" class="relativeTag">
	                      <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center"  class="bttcn">编号</div></td>
	                         <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center"  class="bttcn">站点名称</div></td>
	                         <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center"  class="bttcn">所在地区</div></td>
	                         <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center"  class="bttcn">站点类型</div></td>
					         <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center"  class="bttcn">电费支付类型</div></td>
					         <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center"  class="bttcn">缴费周期</div></td>
	                         <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center"  class="bttcn">最后交费时间</div></td>
	                         <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center"  class="bttcn">交费次数</div></td>
	                         <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center"  class="bttcn">站点代码</div></td>
	                    </tr>
	                
	          <%
	                      DecimalFormat mat=new DecimalFormat("0.0");
	          		      DecimalFormat mat1=new DecimalFormat("0.00");
	        
	       	 ArrayList fylist = new ArrayList();
	       	 if(shi != null && !shi.equals("")&& !shi.equals("0")){
	       	 fylist = bean.getlist(whereStr,loginId,sfjf,str);
	       	
			
			String jzname = "";
			String jzcode = "";
			String szdq="",stationtype="",dfzflx="",fkzq="",endmonth1="",paycount="";
			intnum=xh = pagesize*(curpage-1)+1;
			 if(fylist!=null)
			{
				int fycount = ((Integer)fylist.get(0)).intValue();
				for( int k = fycount;k<fylist.size()-1;k+=fycount){
	
			     //num为序号，不同页中序号是连续的
			     
			    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
			    jzname = jzname != null ? jzname : "";
			    
			    jzcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
			    jzcode = jzcode != null ? jzcode : "";
			    
			    szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
			    szdq = szdq != null ? szdq : "";
			    
			    stationtype = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
			    stationtype = stationtype != null ? stationtype : "";
			    
			    dfzflx = (String)fylist.get(k+fylist.indexOf("DFZFLX"));
			    dfzflx = dfzflx != null ? dfzflx : "";
			    
			    fkzq = (String)fylist.get(k+fylist.indexOf("FKZQ"));
			    fkzq = fkzq != null ? fkzq : "";
			    
			    endmonth1 = (String)fylist.get(k+fylist.indexOf("ENDMONTH"));
			    endmonth1 = endmonth1 != null ? endmonth1 : "";
			    
			    paycount = (String)fylist.get(k+fylist.indexOf("PAYCOUNT"));
			    paycount = paycount != null ? paycount : "";
	           
				if(intnum%2==0){
					color="#DDDDDD";
	
				 }else{
					 color="#FFFFFF" ;
				    
				}
	           intnum++;
	           
	
	       %>
	       <tr bgcolor="<%=color%>" class="form_label">
	            <td>
	       			<div align="center" ><%=intnum-1%></div>
	       		</td>
	            
	       		<td>
	       			<div align="left"><a href="javascript:modifyad('<%=jzcode%>','<%=beginTime%>','<%=endTime%>')" ><%=jzname%></a></div>
	       		</td>
	       		 <td>
	       			<div align="left"><%=szdq%></div>
	       		</td>
	       		
	       		<td>
	       			<div align="center"><%=stationtype%></div>
	       		</td>
	       		<td>
	       			<div align="center"><%=dfzflx%></div>
	       		</td>
	       		<td>
	       			<div align="right"><%=fkzq%></div>
	       		</td>
	       		<td>
	       			<div align="center"><%=endmonth1%></div>
	       		</td>
	       		<td>
	       			<div align="right"><%=paycount%></div>
	       		</td>
	       		<td>
	       			<div align="center"><%=jzcode%></div>
	       		</td>
	       		
	
	       </tr>
	       
	       <%} %>
	       <%} %>
	       <%}%>
	      <% if (intnum==0){
	    	  System.out.println("QQQQ"+intnum);
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
	        </tr>
	      <% }}
	         
	        %>
		</table>	
	</div>
	
	<table width="100%" height="8%" border="0" cellspacing="0" cellpadding="0">
			<tr></tr>
		   	<tr>
                    <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                        <div style="width:300px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                    </div></td>
		    </tr>
		  <tr><td>
		  <input type="hidden" name="sheng2" id="sheng2" value=""/>
		  <input type="hidden" name="shi2" id="shi2" value=""/>
		  <input type="hidden" name="xian2" id="xian2" value=""/>
		  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
		  <input type="hidden" name="sptype2" id="sptype2" value=""/>
		  </td></tr>
	</table>
	<iframe name="treeMap" width="20%" height="400px" frameborder="0" src="<%=path %>/web/cx/sitepaymentstatisticscentre.jsp"></iframe>
  		<span style="width:20px"></span>
    <iframe name="treeNodeInfo" width="75%" height="400px" frameborder="0" src="<%=path %>/web/cx/sitepaymentstatisticsright.jsp"></iframe>
</form>
</body>
<script type="text/javascript">
 function modifyad(jzcode,startmonth,endmonth){
    	var b=path+"/web/cx/sitepaymentstatisticscentre.jsp?jzcode="+jzcode+"&";			
			b = b.substring(0,b.length-1);
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();
			
			var c=path+"/web/cx/sitepaymentstatisticsright.jsp?jzcode="+jzcode+"&startmonth="+startmonth+"&endmonth="+endmonth+"&";			
			c = c.substring(0,c.length-1);
			 var a = " <a href="+c+" target='treeNodeInfo' id='tmpTree1'></a>";
			$("#tmpTree1").remove();
			$("body").append(a);
			$("#tmpTree1")[0].click();  
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
var path = '<%=path%>';
	
        document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.jzproperty.value='<%=sptype%>';
		document.form1.sfjf.value='<%=sfjf%>';

		
 </script>
</html>
