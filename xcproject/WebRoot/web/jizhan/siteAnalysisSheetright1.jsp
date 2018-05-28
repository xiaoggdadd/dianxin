<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.noki.database.DataBase"%>
<%@page import="java.sql.ResultSet"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script language="javascript">
function modifyad(stationId){  
var path='<%=path%>';  	
  var url=path+"/web/jizhan/siteAnaly.jsp?stationId="+stationId;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1000px;dialogHeight:1000px;status:auto;scroll:auto');	
    }
</script>
  <head>
  	<link rel="StyleSheet" href="<%=path%>/css/atatonic/zp-compressed.css" type="text/css" />
  </head>
  <body>
  	 <%
  		String stationId= request.getParameter("code");
		if(stationId != null){  		
	  		DataBase db = new DataBase();
	  		StringBuffer sql = new StringBuffer();
	  		sql.append("select (select d2.agname from d_area_grade d2 where d2.agcode = z.shi)as city,");
	        sql.append("(select d.agname from d_area_grade d where d.agcode = z.xian)as country,");
	        sql.append("(select i.name from indexs i where z.jztype = i.code)as stationType,");
	        sql.append("z.jzname,");
	        sql.append("(select i2.name from indexs i2 where i2.code=z.yflx)as yftype,");
	        sql.append("(case z.bgsign when '1' then '是' when '0' then '否' END)as bgsign,");
	        sql.append("z.address,");
	        sql.append("(select i3.name from indexs i3 where i3.code = z.gdfs)as gdtype,");
	        sql.append("z.area,");
	        sql.append("z.jnglmk as isjnmodul,");
	        sql.append("z.fzr,");
	        sql.append("z.memo,");
	        sql.append("to_char(z.cjtime,'yyyy-mm-dd hh24:MI:SS') as createtime,");
	        sql.append("(case z.xuni when '1' then '是' when '0' then '否' END)as isxuni,");
	        sql.append("(select i3.name from indexs i3 where i3.code = z.zdcq)as zdcq,");
	        sql.append("(case z.caiji when '1' then '是' when '0' then '否' END)as iscaiji ");
	  		sql.append("from zhandian z ");
	  		sql.append("where z.zdcode = '"+stationId+"'");
	  		System.out.println("222222222222"+sql);
	  		ResultSet resultSet = db.queryAll(sql.toString()); 
	  		String stationName="",city="",country="",stationType="",yftype="",bgsign="",address="",gdtype="",area="",isjnmodul="",fzr="",memo="",createtime="",isxuni="",zdcq="",iscaiji="";
	  		if(resultSet.next()){
	  			city = resultSet.getString(1)==null?"":resultSet.getString(1);
	  			country = resultSet.getString(2)==null?"":resultSet.getString(2);
	  			stationType = resultSet.getString(3)==null?"":resultSet.getString(3);
	  			stationName = resultSet.getString(4)==null?"":resultSet.getString(4);
	  			yftype = resultSet.getString(5)==null?"":resultSet.getString(5);
	  			bgsign = resultSet.getString(6)==null?"":resultSet.getString(6);
	  			address = resultSet.getString(7)==null?"":resultSet.getString(7);
	  			gdtype = resultSet.getString(8)==null?"":resultSet.getString(8);
	  			area = resultSet.getString(9)==null?"":resultSet.getString(9);
	  			isjnmodul = resultSet.getString(10)==null?"":resultSet.getString(10);
	  			fzr = resultSet.getString(11)==null?"":resultSet.getString(11);
	  			if(fzr.equals("null")){
	  			 fzr="";
	  			}
	  			memo = resultSet.getString(12)==null?"":resultSet.getString(12);
	  			createtime = resultSet.getString(13)==null?"":resultSet.getString(13);
	  			isxuni = resultSet.getString(14)==null?"":resultSet.getString(14);
	  			zdcq = resultSet.getString(15)==null?"":resultSet.getString(15);
	  			iscaiji = resultSet.getString(16)==null?"":resultSet.getString(16);
	  		}
	  		db.close();
	  		System.out.println("2222222222222222222222=============");
  	%>
  			<p style="font-size: 14px;font-weight: 900;font-family: 微软雅黑"><%=stationName %>信息如下</p>
  			<table>
  				<tr>
  					<td>城市：</td>
  					<td><%=city %></td>
  				</tr>
  				<tr>
  					<td>区县：</td>
  					<td><%=country %></td>
  				</tr>
  				<tr>
  					<td>集团报表类型：</td>
  					<td><%=stationType %></td>
  				</tr>
  				<tr>
  					<td>站点名称：</td>
  					<td><a href="javascript:modifyad('<%=stationId%>')" ><%=stationName %></a></td>
  				</tr>
  				<tr>
  					<td>用房类型：</td>
  					<td><%=yftype %></td>
  				</tr>
  				<tr>
  					<td>是否标杆：</td>
  					<td><%=bgsign %></td>
  				</tr>
  				<tr>
  					<td>具体地址：</td>
  					<td><%=address %></td>
  				</tr>
  				<tr>
  					<td>供电类型：</td>
  					<td><%=gdtype %></td>
  				</tr>
  				<tr>
  					<td>面积：</td>
  					<td><%=area %></td>
  				</tr>
  				<tr>
  					<td>是否节能管理模块：</td>
  					<td><%=isjnmodul %></td>
  				</tr>
  				<tr>
  					<td>负责人：</td>
  					<td><%=fzr %></td>
  				</tr>
  				<tr>
  					<td>创建时间：</td>
  					<td><%=createtime %></td>
  				</tr>
  				<tr>
  					<td>是否虚拟站点：</td>
  					<td><%=isxuni %></td>
  				</tr>
  				<tr>
  					<td>站点产权：</td>
  					<td><%=zdcq %></td>
  				</tr>
  				<tr>
  					<td>是否采集点：</td>
  					<td><%=iscaiji %></td>
  				</tr>
  				<tr>
  					<td>备注：</td>
  					<td><%=memo %></td>
  				</tr>
  			</table>
  	<%
  		}
  	%>
  </body>
</html>
