<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.noki.database.DataBase"%>
<%@page import="java.sql.ResultSet,com.noki.util.Query"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
  	<link rel="StyleSheet" href="<%=path%>/css/atatonic/zp-compressed.css" type="text/css" />
  </head>
  <body>
  	 <%
  		String stationId= request.getParameter("stationType");
  		String shi= request.getParameter("cityGrade");
  		String xian= request.getParameter("countryGrade");
  		String bgsign= request.getParameter("bgsign");
  		String cjdsign= request.getParameter("cjdsign");
  		String gdfs= request.getParameter("gdfs");
  		String jnsign= request.getParameter("jnsign");
  		String yflx= request.getParameter("yflx");
  		String zdcq= request.getParameter("zdcq");
		if(stationId != null){  		
	  		DataBase db = new DataBase(); 
	  		StringBuffer sql = new StringBuffer();
	  		sql.append("select z.jzname,z.id from zhandian z");
	  		sql.append(" where z.jztype = '"+stationId+"' and z.shi='"+shi+"' and z.xian='"+xian+"'");
	  		if(bgsign != null && !bgsign.equals("-1"))sql.append(" and z.bgsign = '"+bgsign+"'");
			if(cjdsign != null && !cjdsign.equals("-1"))sql.append(" and z.caiji = '"+cjdsign+"'");
			if(gdfs != null && !gdfs.equals("-1"))sql.append(" and z.gdfs = '"+gdfs+"'");
			if(jnsign != null && !jnsign.equals("-1"))sql.append(" and z.jnglmk = '"+jnsign+"'");			
			if(yflx != null && !yflx.equals("-1"))sql.append(" and z.yflx = '"+yflx+"'");
			if(zdcq != null && !zdcq.equals("-1"))sql.append(" and z.zdcq = '"+zdcq+"'");
	  		System.out.println(sql);
	  		ResultSet rs=null;
	  		rs = db.queryAll(sql.toString()); 
	       	ArrayList fylist = new ArrayList();
	  		if (rs.next()) {
		    	  Query query = new Query();
		    	  fylist = query.query(rs);
		    	
		      }
	  		db.close();
  	%>
  	<p style="font-size: 14px;font-weight: 900;font-family: 微软雅黑">站点名称</p>
  			<table>
               
   <%
  	String jzname="",id="";
  	if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

       			//num为序号，不同页中序号是连续的
       			jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
       			id = (String) fylist.get(k + fylist.indexOf("ID"));
       			if(jzname.equals("null")||jzname==null){
       				jzname="";
       			}
       			
       %>
       <tr>
       		<td>
       			<div align="left"  ><a href="javascript:zhandian('<%=id%>')"><%=jzname%></a></div>
       		</td>
       
       </tr>
  			
  			</table>
  	<%
  		}
		}
		}
  	%>
  	
  	
  			
  </body>
  <script type="text/javascript">
  var path='<%=path%>';
  function zhandian(id){	
	 	var b=path+"/web/jizhan/zhandianDetailed.jsp?id="+id;			
			 var a = " <a href="+b+" target='treeNodeInfo2' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();
  }
  </script>
</html>
