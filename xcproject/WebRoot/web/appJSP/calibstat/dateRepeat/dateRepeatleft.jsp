<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.ptac.app.calibstat.daterepeat.dao.DateRepeatImpl,com.ptac.app.calibstat.daterepeat.bean.DateRepeatBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();

    String month = request.getParameter("month");
	String zdlx = request.getParameter("zdlx");
	String property = request.getParameter("jzproperty");
	String zdqyzt = request.getParameter("zdqyzt");
	String dfzflx=request.getParameter("dfzflx");
	String month1 = "";
	if(null != month  && !month.equals("") && !month.equals("0")){
		month1 = month.substring(5,7);
	}
	
    String roleId = (String)session.getAttribute("accountRole");
    String permissionRights="";
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
%>

<html>
<head>
<title>
</title>
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
bttcn{color:white;font-weight:bold;font-size:14px}
 .form    {width:130px}
 .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.relativeTag   {     
	z-index:5;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop); 
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px    
}; 
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
	permissionRights=commBean.getPermissionRight(roleId,"0301");
%>
<body  class="body" >
	
	<form action="" name="form1" method="POST">
	 <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label" >
     <tr class="relativeTag">
      	<td width="" class="relativeTag" bgcolor='#DDDDDD' rowspan="2"><div align="center" class="bttcn">地市</div></td> 
        <td width="" class="relativeTag" bgcolor='#DDDDDD'colspan="4" ><div align="center" class="bttcn"><%=month1%>月</div></td>     
     </tr>
	<tr class="relativeTag">
      	<td width="" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">站点数</div></td>
        <td width="" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">匹配比例</div></td>   
        <td width="" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">不足比例</div></td>
        <td width="" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">超出比例</div></td>     
     </tr>
          
       <%
       if(null !=month&& !month.equals("")&&!month.equals("0")){
       		String whereStr = "";
            if(null != month  && !month.equals("") && !month.equals("0")){
            	whereStr=whereStr+" AND to_char(t.YFMONTH,'yyyy-mm')='"+month+"'";
            }
            if (property != null && !property.equals("") && !property.equals("0")) {
            	whereStr = whereStr + " AND z.PROPERTY='" + property + "'";
    		}
            if(null != zdlx && !zdlx.equals("") && !zdlx.equals("0")){
	           	whereStr=whereStr+" AND z.STATIONTYPE IN('"+zdlx+"')";
            }
           	if(null != zdqyzt && !zdqyzt.equals("") && !zdqyzt.equals("-1")){
           		whereStr=whereStr+" AND z.QYZT ='"+zdqyzt+"'";
           	}
           	if(null != dfzflx && !dfzflx.equals("") && !dfzflx.equals("0")){
           		whereStr=whereStr+" AND d.DFZFLX='"+dfzflx+"'";
           	}
            
           	DateRepeatImpl  bean = new DateRepeatImpl();
		    List<DateRepeatBean> fylist = null;
		    fylist=bean.getShi(whereStr,month);
            String city = "", shi = "";
           	String zdcount="",bili="",bzbl="",cbl="";
            int intnum=xh = pagesize*(curpage-1)+1;
            if(fylist!=null){
            	for(DateRepeatBean bean1:fylist){
            		city = bean1.getCity();
              	    shi = bean1.getShi();
              	    zdcount = bean1.getZdcount();
					cbl = bean1.getCbl();
              	    bili = bean1.getBili();
					bzbl = bean1.getBzbl();
              	    
              		String color=null;
              		if(intnum%2==0){
              	    	color="#DDDDDD";
              	 	}else{
              	    	color="#FFFFFF" ;
              		}
                    intnum++;
                   
                        
       %>
       
        <tr bgcolor="<%=color%>" class="form_label">
       		<td>
       			<div align="center"><%=shi%></div>
       		</td>
       		<td>
       			<div align="center"><%=zdcount%></div>
       		</td>
       		<%--<td>
       			<div align="center"><a href="javascript:query('<%=whereStr%>','<%=city%>','<%=month%>','1')" /><%=bili%>%</a></div>
       		</td>
       			--%>
       		<td>
       			<div align="center"><a target="treeNodeInfo" href="<%=path+"/web/appJSP/calibstat/dateRepeat/dateRepeatright.jsp?whereStr="+whereStr+"&city="+city+"&month="+month+"&bzw=1" %>" /><%=bili%>%</a></div>
       		</td>
       		<td>
       			<div align="center"><a target="treeNodeInfo" href="<%=path+"/web/appJSP/calibstat/dateRepeat/dateRepeatright.jsp?whereStr="+whereStr+"&city="+city+"&month="+month+"&bzw=2" %>" /><%=bzbl%>%</a></div>
       		</td>
       		<td>
       			<div align="center"><a target="treeNodeInfo" href="<%=path+"/web/appJSP/calibstat/dateRepeat/dateRepeatright.jsp?whereStr="+whereStr+"&city="+city+"&month="+month+"&bzw=3" %>" /><%=cbl%>%</a></div>
       		</td>
       </tr>
	<%}}}%>
  	 </table> 										
</form>
</body>
<script language="javascript"><%--
var path = '<%=path%>';   
function query(whereStr,city,month,bzw){
	alert(00000000000000);
    var b=path+"/web/appJSP/calibstat/dateRepeat/dateRepeatright.jsp?whereStr="+whereStr+"&city="+city+"&month="+month+"&bzw="+bzw;			
		b = b.substring(0,b.length-1);
	var a = " <a href="+b+" target='treeNodeInfo' id='tmpTree'></a>";
		$("#tmpTree").remove();
		$("body").append(a);
		$("#tmpTree")[0].click();   
} 
--%></script>
</html>


