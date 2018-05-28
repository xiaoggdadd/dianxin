<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.Double"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="com.noki.common.OrgAndRole"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.List"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="com.noki.jizhan.SiteModifyBean"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.io.*"%>
<%
	String path = request.getContextPath();
	String ppath = request.getParameter("ppath");
	System.out.println("ppath?>?>?>?>?>?>" + ppath);
	 try{
		 String file = ppath;
		   FileInputStream in = new FileInputStream(new File(file));
		   OutputStream o = response.getOutputStream();
		   int l = 0;
		   byte[] buffer = new byte[4096];
		   while ((l = in.read(buffer)) != -1) {
		    o.write(buffer, 0, l);
		   }
		   o.flush();
		   in.close();
		   o.close();
		   out.clear();
		   out = pageContext.pushBody();
		   }catch(Exception e){}
%>
