<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.Double" %>
<%@ page import="com.noki.database.DataBase" %>
<%@ page import="java.sql.ResultSet" %>
<%
	String path = request.getContextPath();
	String id = request.getParameter("id");
	String jzname="",property="",address="",gdfs="",jingdu="",weidu="",gxxx="",wlzdbm="",yflx="",jzcode="";
	DataBase db = new DataBase();
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	sql.append("select jzname,jzcode, case property when 'zdsx02' then '基站' end pro,address,case gdfs when 'gdfs05' then '直供电' when 'gdfs06' then '转供电' end gd,longitude,latitude,case gxxx when 'gxxx01' then '移动' when 'gxxx02' then '电信' when 'gxxx03' then '联通' when 'gxxx04' then '移动联通' when 'gxxx05' then '移动电信' when 'gxxx06' then '联通电信' else");
 	sql.append("'移动电信联通' end gx,wlzdbm,(select name from indexs where code =(select yflx from zhandian where id='"+id+"')) as yflx from zhandian where id='"+id+"'");
 	System.out.println(sql);
 	db.connectDb();
 	rs=db.queryAll(sql.toString());
 	while(rs.next()){
 		jzname=rs.getString("jzname")==null?"":rs.getString("jzname");
 		property=rs.getString("pro")==null?"":rs.getString("pro");
 		address=rs.getString("address")==null?"":rs.getString("address");
 		gdfs=rs.getString("gd")==null?"":rs.getString("gd");
 		jingdu=rs.getString("longitude")==null?"":rs.getString("longitude");
 		weidu=rs.getString("latitude")==null?"":rs.getString("latitude");
 		gxxx=rs.getString("gx")==null?"":rs.getString("gx");
 		wlzdbm=rs.getString("wlzdbm")==null?"":rs.getString("wlzdbm");
 		yflx=rs.getString("yflx")==null?"":rs.getString("yflx");
 		jzcode=rs.getString("jzcode")==null?"":rs.getString("jzcode");
 	}
 	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>站点详细查询</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">详细数据</div>
				<div class="content01_1">
					<table width="1000px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >					
						<tr>
							<td align="right" width="100px">站点名称</td>
							<td width="100px">
							<input value='<%=jzname%>'	readonly="readonly"/>						
							</td>
							<td align="right" width="100px">站点编码</td>
							<td width="100px">
								<input readonly="readonly" value='<%=jzcode%>'/>
							</td>
							<td align="right" width="100px">站点属性</td>
							<td width="100px">
								<input readonly="readonly" value='<%=property %>'/>
							</td>
							<td align="right" width="100px">所在地区</td>
							<td width="100px">
								<input value='<%=address %>' readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">供电方式</td>
							<td width="100px">
								<input value='<%=gdfs%>' readonly="readonly"/>
							</td>
							<td align="right" width="100px">经度</td>
							<td width="100px">
								<input value='<%=jingdu %>' readonly="readonly"/>
							</td>
							<td align="right" width="100px">纬度</td>
							<td width="100px">
								<input readonly="readonly" value='<%=weidu %>' />
							</td>
							<td align="right" width="100px">共享信息</td>
							<td width="100px">
								<input readonly="readonly" value='<%=gxxx %>' />&nbsp;
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">物理站点编码</td>
							<td width="100px">
								<input readonly="readonly" value='<%=wlzdbm %>' />&nbsp;
							</td>
							<td align="right" width="100px">用房类型</td>
							<td width="100px">
								<input readonly="readonly" value='<%=yflx %>' />
							</td>
						</tr>			
						<tr>
							<td align="right" colspan="8" height="60px">
								<input onclick="fanhui()" type="submit" class="btn_c1" id="button2" value="返回" />&nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td></tr></table>
		<script>
	function fanhui(){
		window.close();
	}
</script>
</body>
</html>

