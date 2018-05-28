<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ptac.app.noadvicescb.city.*"%>
<%@ page import="java.util.Calendar" %>
<%
    String path = request.getContextPath();
    String loginName = (String)session.getAttribute("loginName");
   	Account account = (Account)session.getAttribute("account"); 
    String loginId = account.getAccountId();
	String sheng = (String)session.getAttribute("accountSheng");
	String roleId = (String)session.getAttribute("accountRole");
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
	String jzproperty = request.getParameter("zdsx")!=null?request.getParameter("zdsx"):"0";
	String bzw = request.getParameter("bzwx")!=null?request.getParameter("bzwx"):"";
	
	String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";         
%>

<html>
<head>
<title>
市级无建议生产标原因详情
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
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
}

.bttcn{color:BLACK;font-weight:bold;}

.form    {width:130px}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}

#div1{ 
float:left;/*左置*/ 
width:40%;/*宽*/ 
height:100px;/*高*/ 
margin-top:20px;/*其顶部与包含此元素容器的距离为20像素*/ 
} 
#div2{ 
float:right;/*右置*/ 
width:60%; 
height:100px; 
margin-top:20px; 
} 

 </style>
 
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
  <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
 <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>

<script language="javascript">
var path = '<%=path%>';

function exportad(){
	var shi = <%=shi%>;
  	var jzproperty = '<%=jzproperty%>';
  	var bzwq = <%=bzw%>;
  	document.form1.action=path+"/web/appJSP/noadvicescb/city/cityReasXqExportad.jsp?shi="+shi+"&jzproperty="+jzproperty+"&bzwq="+bzwq;
	document.form1.submit();
}

$(function(){
		$("#daochuBtn").click(function(){
	   		exportad();
		});
});
</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
        permissionRights=commBean.getPermissionRight(roleId,"0101");
%>
<body  class="body" >
		
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" >
	      <tr>
           <td width="50" >
             <div style="width:700px;height:50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">省级无建议生产标详情</span>
	         </div>
	        </td>
		  </tr>
 </table>
  <table height="23">
     <tr><td height="5"  colspan="4" >
	         <div id="parent" style="display:inline">
             </div><div style="width:50px;display:inline;"><hr></div>
             <font size="2">&nbsp;信息列表&nbsp;</font>
             <div style="width:300px;display:inline;"><hr></div>
          </td></tr>
 </table>

 </form> 
 
 <%
 	String whereStr="";
 	if(shi != null && !shi.equals("") && !shi.equals("0")){
		whereStr=whereStr+" and z.shi='"+shi+"'";
	}
	if (!jzproperty.equals("0")) {
		whereStr=whereStr+" and z.property='" + jzproperty + "'";
	}
	NoAdvScbCityReason bean=new NoAdvScbCityReason();
	ArrayList<NoAdvScbCityReason> fylist = null;
	if("1".equals(bzw)){
		fylist = bean.getXian1(whereStr,loginId);
	}else if("2".equals(bzw)){
		fylist = bean.getXian2(whereStr,loginId);
	}else if("3".equals(bzw)){
		fylist = bean.getXian3(whereStr,loginId);
	}
 %>
 
 <div id="div1">
	<table width="100%" border="0" cellspacing="1" cellpadding="1">       
           <tr class="relativeTag" >
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">序号</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">原因说明</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点总数</div></td>
         </tr>
       <%
			String xian = "",num = "0";
			int intnum=xh = pagesize*(curpage-1)+1;
			if(fylist!=null){
				for(NoAdvScbCityReason bean1:fylist){
					xian = bean1.getXian();
					num = bean1.getNum();
					
					String color=null;
		            if(intnum%2==0){
					    color="#DDDDDD";
					 }else{
					    color="#FFFFFF" ;
					}
       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		<td align="center">
       			<%=intnum++%>
       		</td>
       		<td align="center">
       			<%=xian%>
       		</td>
       		<td align="center">
       			<%=num%>
       		</td>
       </tr>
       	<%}}%>
  	 </table> 
 </div>
 
 <div id="div2">
 <% 
		String xian1 = "",num1 = "";
		String hStr = "",str = "";
		 if(fylist!=null){
			for(NoAdvScbCityReason bean3:fylist ){
				xian1 = bean3.getXian();
				num1 = bean3.getNum();
				hStr += num1+ ";";
	        	str += xian1 + ";";
       		} 
	%>
    	<div>
			<img src="<%=path%>/servlet/PieChartServlet?cityStr=<%=str%>&dataStr=<%=hStr%>">
		</div>
      <%} %>
 </div>

<table width="100%"  border="0" cellspacing="0" cellpadding="0" align="right" height="5%">
  	<tr align="right">
        <td align="right" height="19" colspan="4">
       		<div id="parent" style="display:inline">
            	<div style="width:50px;display:inline;"><hr></div>
            	<div style="width:300px;display:inline;"><hr></div>
            </div>
        </td>
    </tr>
    <tr>
		<td align="right">                                                                  
            <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
	 			<img src="<%=path%>/images/daochu.png" width="100%" height="100%">
				<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
			</div>
        </td>
  	</tr>
</table>

</body>
</html>

