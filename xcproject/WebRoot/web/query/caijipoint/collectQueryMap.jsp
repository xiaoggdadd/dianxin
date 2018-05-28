<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.net.URLEncoder" %>
<%@page import="com.noki.query.caijipoint.javabean.*;"%>
<%
String path = request.getContextPath();

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String startmonth = request.getParameter("startmonth")!=null?request.getParameter("startmonth"):"";
String endmonth = request.getParameter("endmonth")!=null?request.getParameter("endmonth"):"";
String zdid = request.getParameter("zdcode")!=null?request.getParameter("zdcode"):"";
String zdname = request.getParameter("jzname")!=null?request.getParameter("jzname"):"";
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
int curpage=1;
curpage=Integer.parseInt(s_curpage);
String permissionRights="";
String roleId = (String)session.getAttribute("accountRole");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>My JSP 'haodianliangleft.jsp' starting page</title> 
  </head> 
  <body class="body">

  <table border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
      <%
      String whereStr = "";
      StringBuilder paiming=new StringBuilder();
      if(null != startmonth  &&!startmonth.equals("") && !startmonth.equals("0")){
			whereStr=whereStr+" and to_char(a.thisdatetime,'yyyy-mm-dd')>='"+startmonth+"'";
		}
		if(null != endmonth  && !endmonth.equals("") && !endmonth.equals("0")){
			whereStr=whereStr+" and to_char(a.lastdatetime,'yyyy-mm-dd')<='"+endmonth+"'";
		}
      	haodianliangBean bean = new haodianliangBean();
     	ArrayList fylist = new ArrayList();
     	if(zdid!=null&&!"null".equals(zdid)&&!"".equals(zdid)){
     	fylist = bean.getCollectQuery(curpage,whereStr,zdid);
        int j=1;
        int fycount = ((Integer)fylist.get(0)).intValue();
          for(int k = fycount;k<fylist.size()-1;k+=fycount){
          	String str = (String)fylist.get(k+fylist.indexOf("LLHDL")); 
          	if("".equals(str)||str==null||"null".equals(str)){
          		str="0.00";
          	}  
	         if(j%2==0){           
	           paiming.append((String)fylist.get(k+fylist.indexOf("LASTDATETIME"))+",");
	           paiming.append((String)fylist.get(k+fylist.indexOf("ACTUALDEGREE"))+",");
	           paiming.append(str+";");
	         }else{
	           paiming.append((String)fylist.get(k+fylist.indexOf("LASTDATETIME"))+",");
	           paiming.append((String)fylist.get(k+fylist.indexOf("ACTUALDEGREE"))+",");
	           paiming.append(str+";");
	         }
	           j++;
	    }
	    paiming.append(zdname);
	    if(fylist.size()>0) {%>
  	  		<div><img src="<%=path%>/servlet/GuanlidbServlet?bz=2&fylist1=<%=URLEncoder.encode(paiming.toString(),"utf-8") %>"/></div>
       <%}}%>
      
   </table>
  </body>
<script type="text/javascript">
var path = '<%=path%>';
function dfinfo1(zdid,last){
    var url=path+"/web/query/caijipoint/collectQuerytan.jsp?zdid="+zdid+"&last="+last;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}
</script>
</html>

