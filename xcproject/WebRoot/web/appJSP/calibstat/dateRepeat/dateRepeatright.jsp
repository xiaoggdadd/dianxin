<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.ptac.app.calibstat.daterepeat.dao.DateRepeatImpl,com.ptac.app.calibstat.daterepeat.bean.DateRepeatBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.net.URLEncoder"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String roleId = (String)session.getAttribute("accountRole");
	
	String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
	curpage=Integer.parseInt(s_curpage);
	
	String whereStr = request.getParameter("whereStr");
	String city = request.getParameter("city");
	String month = request.getParameter("month");
	String bzw = request.getParameter("bzw");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <base target="_blank" />
    <title>My JSP 'haodianliangleft.jsp' starting page</title>   
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
bttcn{color:white;font-weight:bold;}
 .form    {width:130px}

#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}; 
 </style> 
  </head> 
  <body class="body" style="overflow-x:hidden;">

 <table border="0" width="100%" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
     <tr height = "10px" class= "relativeTag">
       <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">区县</div></td>
       <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">站点名称</div></td>
       <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">站点类型</div></td>
       <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">条数总和</div></td>
       <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">天数总和</div></td>
     </tr>
		<% 
			if(null !=city  && !city.equals("") && !city.equals("0")){
          		whereStr=whereStr+" AND T.SHI='"+city+"'";
          	}
          	
       		if(whereStr!=null&&!"".equals(whereStr)){
	             	
	            	DateRepeatImpl  bean = new DateRepeatImpl();
	    		    List<DateRepeatBean> fylist = null;
	    		    if(bzw.equals("1")){//匹配比例
	    		    	fylist=bean.getXian1(whereStr,month);
	    		    }else if(bzw.equals("2")){//不足比例
	    		    	fylist=bean.getXian2(whereStr,month);
	    		    }else if(bzw.equals("3")){//超出比例
	    		    	fylist=bean.getXian3(whereStr,month);
	    		    }
	                String xian="",zdname="",zdlx="",zdid="";
            		String numCount="",dayCount="";
            		int intnum=xh = pagesize*(curpage-1)+1;
            		int linenum=0;
            	if(fylist!=null){	
            		for(DateRepeatBean bean1:fylist){
            			xian=bean1.getXian();
            			zdname=bean1.getZdname();
            			zdlx=bean1.getZdlx();
            			numCount=bean1.getNumCount();
            			dayCount=bean1.getDayCount();
	            		zdid = bean1.getId();
            	        String color=null;       
            	    
	            	if(intnum%2==0){
	            	    color="#DDDDDD";
	            	 }else{
	            	    color="#FFFFFF" ;
	            	}
                    intnum++;
      %>
      <tr bgcolor="<%=color%>" class="form_label">
          <td><div align="center" ><%=xian%></div></td> 
          <td>
          	<div align="center" ><a target="_Blank" href="<%=path+"/web/appJSP/calibstat/dateRepeat/xiangxi.jsp?zdid="+zdid+"&whereStr="+whereStr+"&month="+month+"&bzw="+bzw %>" ><%=zdname%></a></div>
          </td> 
          <td><div align="center" ><%=zdlx%></div></td>
          <td><div align="center"><%=numCount%></div></td>
          <td><div align="center" ><%=dayCount%></div></td>
      </tr>   
     <%}}}%> 
   </table>
</body>
<script type="text/javascript"><%--
var path='<%=path%>';  
function lookDetails(zdid,whereStr,month,bzw){ 
	 window.open(path+"/web/appJSP/calibstat/dateRepeat/xiangxi.jsp?zdid="+zdid+"&whereStr="+whereStr+"&month="+month+"&bzw="+bzw,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
}
--%></script>
</html>