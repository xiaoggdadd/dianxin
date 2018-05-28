<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.noki.database.DataBase"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.noki.jizhan.StationRelationshipTreeBean,com.noki.util.Query" %>
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
  		String shi= request.getParameter("countryGrade");
  	    String xian= request.getParameter("cityGrade");
  	    String property= request.getParameter("property");
	    String gsf= request.getParameter("gsf");
	    String bgsign= request.getParameter("bgsign");
  	    String dbyt= request.getParameter("dbyt");
  	    String gdfs= request.getParameter("gdfs");
	    String zdcq= request.getParameter("zdcq");
	    String g2= request.getParameter("g2");
  	    String g3= request.getParameter("g3");
  	    String kt1= request.getParameter("kt1");
	    String kt2= request.getParameter("kt2");
	    String kdsb= request.getParameter("kdsb");
	    String yysb= request.getParameter("yysb");
	    
	    System.out.println("shi:"+shi+"xian:"+xian+"property:"+property+"gsf:"+gsf+"bgsign:"+bgsign+"dbyt:"+"gdfs:"+gdfs+"zdcq:"+zdcq+"g2:"+g2+"g3:"+g3+"kt1:"+kt1+"kt2:"+kt2+"kdsb:"+kdsb+"yysb:"+yysb);
		if(shi != null&&xian!=null){  		
	  		DataBase db = new DataBase();
	  		StringBuffer sql = new StringBuffer();
	  		sql.append("select distinct fzr from zhandian z where shi='"+shi+"' and xian='"+xian+"'");
	  		if(property != null && !property.equals("-1"))sql.append(" and property = '"+property+"'");
			if(gsf != null && !gsf.equals("-1"))sql.append(" and gsf = '"+gsf+"'");
			if(bgsign != null && !bgsign.equals("-1"))sql.append(" and z.bgsign = '"+bgsign+"'");
			if(dbyt != null && !dbyt.equals("-1"))sql.append(" and d.dbyt = '"+dbyt+"'");
			if(gdfs != null && !gdfs.equals("-1"))sql.append(" and z.gdfs = '"+gdfs+"'");
			if(zdcq != null && !zdcq.equals("-1"))sql.append(" and z.zdcq = '"+zdcq+"'");
			if(g2 != null && !g2.equals("-1"))sql.append(" and z.g2 = '"+g2+"'");
			if(g3 != null && !g3.equals("-1"))sql.append(" and z.g3 = '"+g3+"'");
			if(kt1 != null && !kt1.equals("-1"))sql.append(" and z.kt1 = '"+kt1+"'");
			if(kt2 != null && !kt2.equals("-1"))sql.append(" and z.kt2 = '"+kt2+"'");
			if(kdsb != null && !kdsb.equals("-1"))sql.append(" and z.kdsb = '"+kdsb+"'");
			if(yysb != null && !yysb.equals("-1"))sql.append(" and z.yysb = '"+yysb+"'");
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
  			<p style="font-size: 14px;font-weight: 900;font-family: 微软雅黑">负责人</p>
  			<table>
               
  			 <%
		String fzr="";
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

       			//num为序号，不同页中序号是连续的
       			fzr = (String) fylist.get(k + fylist.indexOf("FZR"));
       			if(fzr.equals("null")||fzr==null){
       				fzr="";
       			}
       			
       %>
       <tr>
       		<td>
       			<div align="left"  ><a href="javascript:zhandian('<%=fzr%>','<%=shi %>','<%=xian %>')"><%=fzr%></a></div>
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
  function zhandian(fzr,shi,xian){	
	  var property='<%=property%>';
	  var gsf='<%=gsf%>';
	  var bgsign='<%=bgsign%>';
	  var dbyt='<%=dbyt%>';
	  var gdfs='<%=gdfs%>';
	  var zdcq='<%=zdcq%>';
	  var g2='<%=g2%>';
	  var g3='<%=g3%>';
	  var kt1='<%=kt1%>';
	  var kt2='<%=kt2%>';
	  var kdsb='<%=kdsb%>';
	  var yysb='<%=yysb%>';
	  
	 	var b=path+"/web/jizhan/fuzeren_iframe_nodeInfo.jsp?shi="+shi+"&xian="+xian+"&fzr="+fzr+"&property="+property+"&gsf="+gsf+"&bgsign="+bgsign+"&dbyt="+dbyt+"&gdfs="+gdfs+"&zdcq="+zdcq+"&g2="+g2+"&g3="+g3+"&kt1="+kt1+"&kt2="+kt2+"&kdsb="+kdsb+"&yysb="+yysb;			
			 var a = " <a href="+b+" target='treeNodeInfo2' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();
  }
  </script>
</html>
