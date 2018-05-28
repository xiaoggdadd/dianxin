<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.noki.database.DataBase"%>
<%@page import="java.sql.ResultSet"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style>
 .btt{color:black;font-weight:bold;}
 .form_label{
		text-align: left;
		font-family: 宋体;
		font-size: 12px;
		height:23px;
}
 </style>
  </head>
  <body>
  	 <%
  		String id= request.getParameter("id");
  	   
		if(id != null){  		
	  		DataBase db = new DataBase();
	  		StringBuffer sql = new StringBuffer();
	  		sql.append("select  z.jzname,(case when z.shi is not null then (select distinct agname from d_area_grade where agcode = z.shi) else ''end) ||(case when z.xian is not null then (select distinct agname from d_area_grade where agcode = z.xian) else ''end) || (case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = z.xiaoqu) else  '' end) as szdq,");
	        sql.append("(select t.name from indexs t where t.code=z.property and t.type='ZDSX') as property,(select t.name from indexs t where t.code=z.jztype and t.type='ZDLX') as jztype,");
	        sql.append("(select t.name from indexs t where t.code=z.yflx and t.type='FWLX') as yflx,(select t.name from indexs t where t.code=z.gdfs  and t.type='GDFS') as gdfs,  ");
	        sql.append(" (select t.name from indexs t where t.code=z.zdcq  and t.type='ZDCQ') as zdcq,");
	        sql.append("(select t.name from indexs t where t.code=z.stationtype  and t.type='stationtype') as stationtype,(case when  z.bgsign='0' then '否' else '是' end) as bgsign,");
	        sql.append("(case when  z.jnglmk='0' then '否' else '是' end) as jnglmk,(case when  z.CAIJI='0' then '否' else '是' end) as CAIJI,(case when  z.SHSIGN='0' then '未通过' else '通过' end) as SHSIGN,");
	        sql.append("(case when  z.g2='0' then '否' else '是' end) as g2,(case when  z.g3='0' then '否' else '是' end) as g3,  (case when  z.zgd='0' then '否' else '是' end) as zgd,");
	        sql.append("(case when  z.kt1='0' then '否' else '是' end) as kt1,(case when  z.kt2='0' then '否' else '是' end) as kt2,(case when  z.qyzt='0' then '否' else '是' end) as qyzt,    ");
	        sql.append("z.address, z.area,z.memo,z.cjr,z.cjtime,z.dianfei, z.manualauditstatus_station,z.manualauditname_station,z.manualauditdatetime_station,z.pue, z.SYDATE, z.ZLFH,    ");
	        sql.append("z.EDHDL,z.entrypensonnel,z.entrypensonnel,z.jlfh,z.bieming,z.kdsb,z.yysb,z.kdsbsl,z.yysbsl,z.zssl,z.zpsl,z.yid,z.lyjhjgs,z.gxxx,(select t.name from indexs t where t.code=z.gsf  and t.type='gsf') as gsf from zhandian z ");	      
	  		sql.append("where z.id = '"+id+"'");
	  		System.out.println(sql);
	  		ResultSet resultSet = db.queryAll(sql.toString()); 
	  		String jznamea="",szdq="",property="",jztype="",yflx="",gdfs="",gsf="",zdcq="",stationtype="",bgsign="",jnglmk="",
	  		caiji="",shsign="",g2="",g3="",zgd="",kt1="",kt2="",qyzt="",address="",area="",memo="",cjr="",cjtime="",dianfei="",kdsb="",
	  		yysb="",kdsbsl="",yysbsl="",zssl="",zpsl="",yid="",lyjhjgs="",gxxx="";
	  		if(resultSet.next()){
	  			jznamea = resultSet.getString(1)==null?"":resultSet.getString(1);
	  			szdq = resultSet.getString(2)==null?"":resultSet.getString(2);
	  			property = resultSet.getString(3)==null?"":resultSet.getString(3); 
	  			jztype = resultSet.getString(4)==null?"":resultSet.getString(4);
	  			yflx = resultSet.getString(5)==null?"":resultSet.getString(5);
	  			gdfs = resultSet.getString(6)==null?"":resultSet.getString(6);
	  			zdcq = resultSet.getString(7)==null?"":resultSet.getString(7);
	  			stationtype = resultSet.getString(8)==null?"":resultSet.getString(8);
	  			bgsign = resultSet.getString(9)==null?"":resultSet.getString(9);
	  			jnglmk = resultSet.getString(10)==null?"":resultSet.getString(10);
	  			caiji = resultSet.getString(11)==null?"":resultSet.getString(11);
	  			shsign = resultSet.getString(12)==null?"":resultSet.getString(12);
	  			g2 = resultSet.getString(13)==null?"":resultSet.getString(13);
	  			g3 = resultSet.getString(14)==null?"":resultSet.getString(14);
	  			zgd = resultSet.getString(15)==null?"":resultSet.getString(15);
	  			kt1 = resultSet.getString(16)==null?"":resultSet.getString(16);
	  			kt2 = resultSet.getString(17)==null?"":resultSet.getString(17);
	  			qyzt = resultSet.getString(18)==null?"":resultSet.getString(18);
	  			address = resultSet.getString(19)==null?"":resultSet.getString(19); 
	  			area = resultSet.getString(20)==null?"":resultSet.getString(20);
	  			memo = resultSet.getString(21)==null?"":resultSet.getString(21);
	  			cjr = resultSet.getString(22)==null?"":resultSet.getString(22);
	  			cjtime = resultSet.getString(23)==null?"":resultSet.getString(23);
	  			dianfei = resultSet.getString(24)==null?"":resultSet.getString(24);
	  			kdsb = resultSet.getString(25)==null?"":resultSet.getString(25);
	  			yysb = resultSet.getString(26)==null?"":resultSet.getString(26);
	  			kdsbsl = resultSet.getString(27)==null?"":resultSet.getString(27);
	  			yysbsl = resultSet.getString(28)==null?"":resultSet.getString(28);
	  			zssl = resultSet.getString(29)==null?"":resultSet.getString(29);
	  			zpsl= resultSet.getString(30)==null?"":resultSet.getString(30);
	  			yid = resultSet.getString(31)==null?"":resultSet.getString(31);
	  			lyjhjgs = resultSet.getString(32)==null?"":resultSet.getString(32);
	  			gxxx = resultSet.getString(33)==null?"":resultSet.getString(33);
	  			gsf = resultSet.getString(33)==null?"":resultSet.getString(34);
	  		}
	  		db.close();
  	%>
  			<p style="font-size: 14px;font-weight: 900;font-family: 微软雅黑"><%=jznamea %>&nbsp;&nbsp;信息如下</p>
  			<table  width="100%"  border="0" cellpadding="1" cellspacing="1" class="form_label" bgcolor="#cbd5de">
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">站点名称：</td>
  					<td><%=jznamea %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">所属地区：</td>
  					<td><%=szdq %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">站点属性：</td>
  					<td><%=property %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">集团报表类型：</td>
  					<td><%=jztype %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">站点类型：</td>
  					<td><%=stationtype %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">单价：</td>
  					<td><%=dianfei %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">用房类型：</td>
  					<td><%=yflx %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">供电方式：</td>
  					<td><%=gdfs %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">归属方：</td>
  					<td><%=gsf %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">站点产权：</td>
  					<td><%=zdcq %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">是否标杆：</td>
  					<td><%=bgsign %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">是否采集：</td>
  					<td><%=caiji %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">审核状态：</td>
  					<td><%=shsign %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">2G：</td>
  					<td><%=g2 %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">3G：</td>
  					<td><%=g3 %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">kt1：</td>
  					<td><%=kt1 %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">kt2：</td>
  					<td><%=kt2 %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">是否节能：</td>
  					<td><%=jnglmk %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">楼宇交换机数量：</td>
  					<td><%=lyjhjgs %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">宽带设备：</td>
  					<td><%=kdsb %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">语音设备：</td>
  					<td><%=yysb %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">宽带设备数量：</td>
  					<td><%=kdsbsl %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">语音设备数量：</td>
  					<td><%=yysbsl %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">载扇数量：</td>
  					<td><%=zssl %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">载频数量：</td>
  					<td><%=zpsl %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">原站点id：</td>
  					<td><%=yid %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">面积：</td>
  					<td><%=area %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">共享信息：</td>
  					<td><%=gxxx %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">创建人：</td>
  					<td><%=cjr %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">创建时间：</td>
  					<td><%=cjtime %></td>
  				</tr>
  				<tr bgcolor="#DDDDDD">
  					<td class="btt">站点启用状态：</td>
  					<td><%=qyzt %></td>
  				</tr>
  				<tr bgcolor="#FFFFFF">
  					<td class="btt">备注：</td>
  					<td><%=memo %></td>
  				</tr>
  				
  			</table>
  	<%
  		}
  	%>
  </body>
</html>
