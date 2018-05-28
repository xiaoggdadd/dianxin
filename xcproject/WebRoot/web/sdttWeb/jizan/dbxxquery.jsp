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
	String dbid="",jzname="",jztype="",dfzflx="",sszy="",dbyt="",zdlx="",csds="",cssytime="",beilv="",dbxh="",memo="",szdq="";
	DataBase db = new DataBase();
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	sql.append("select d.id,d.dbid,z.jzname,z.jztype,(select t.name from indexs t where t.code=d.dfzflx and t.type='dfzflx') as dfzflx,"
							+ // 与indexs表进行关联
							"(select t.name from indexs t where t.code=d.sszy and t.type='SSZY') as sszy"
							+ ",(select t.name from indexs t where t.code=d.dbyt and t.type='DBYT') as dbyt"
							+ ",(select t.name from indexs t where t.code=z.STATIONTYPE and t.type='stationtype') as zdlx"
							+ ",d.csds,to_char(d.cssytime,'yyyy-mm-dd') cssytime,d.beilv,d.dbxh,d.memo"
							+ ",(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian and fagcode=z.shi) else '' end)"
							+ "||(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu and fagcode=z.xian) else '' end) as szdq,z.id as zdid"
							+ " from dianbiao d,zhandian z where d.zdid=z.id and d.id='"+id+"'  AND z.PROVAUDITSTATUS = '1'"		
							+ " order by z.sheng,z.shi,z.xian,xiaoqu,z.jzname");
 	System.out.println(sql);
 	db.connectDb();
 	rs=db.queryAll(sql.toString());
 	while(rs.next()){
 		jzname=rs.getString("jzname")==null?"":rs.getString("jzname");
 		dbid=rs.getString("dbid")==null?"":rs.getString("dbid");
 		jztype=rs.getString("jztype")==null?"":rs.getString("jztype");
 		dfzflx=rs.getString("dfzflx")==null?"":rs.getString("dfzflx");
 		sszy=rs.getString("sszy")==null?"":rs.getString("sszy");
 		dbyt=rs.getString("dbyt")==null?"":rs.getString("dbyt");
 		zdlx=rs.getString("zdlx")==null?"":rs.getString("zdlx");
 		csds=rs.getString("csds")==null?"":rs.getString("csds");
 		cssytime=rs.getString("cssytime")==null?"":rs.getString("cssytime");
 		beilv=rs.getString("beilv")==null?"":rs.getString("beilv");
 		dbxh=rs.getString("dbxh")==null?"":rs.getString("dbxh");
 		memo=rs.getString("memo")==null?"":rs.getString("memo");
 		szdq=rs.getString("szdq")==null?"":rs.getString("szdq");
 	}
 	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电表详细查询</title>
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
							<td align="right" width="100px">电表id</td>
							<td width="100px">
							<input value='<%=dbid%>'	readonly="readonly"/>						
							</td>
							<td align="right" width="100px">站点名称</td>
							<td width="100px">
								<input readonly="readonly" value='<%=jzname%>'/>
							</td>
							<td align="right" width="100px">基站类型</td>
							<td width="100px">
								<input readonly="readonly" value='<%=jztype %>'/>
							</td>
							<td align="right" width="100px">电费支付类型</td>
							<td width="100px">
								<input value='<%=dfzflx %>' readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">所属专业</td>
							<td width="100px">
								<input value='<%=sszy%>' readonly="readonly"/>
							</td>
							<td align="right" width="100px">电表用途</td>
							<td width="100px">
								<input value='<%=dbyt %>' readonly="readonly"/>
							</td>
							<td align="right" width="100px">站点类型</td>
							<td width="100px">
								<input readonly="readonly" value=<%=zdlx %> />
							</td>
							<td align="right" width="100px">初始读数</td>
							<td width="100px">
								<input readonly="readonly" value='<%=csds %>' />&nbsp;
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">初始使用时间</td>
							<td width="100px">
								<input readonly="readonly" value='<%=cssytime %>' />&nbsp;
							</td>
							<td align="right" width="100px">倍率</td>
							<td width="100px">
								<input readonly="readonly" value='<%=beilv %>' />
							</td>
							<td align="right" width="100px">电表型号</td>
							<td width="100px">
								<input readonly="readonly" value='<%=dbxh %>' />
							</td>
							<td align="right" width="100px">备注</td>
							<td width="100px">
								<input readonly="readonly" value='<%=memo %>' />
							</td>
						</tr>	
						<tr>
							<td align="right" width="100px">所在地区</td>
							<td width="100px">
								<input readonly="readonly" value='<%=szdq %>' />
							</td>
						</tr>		
						<tr>
							<td align="right" colspan="8" height="60px">
								<input onclick="fanhui()" type="button" class="btn_c1" id="button2" value="返回" />&nbsp; 
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

