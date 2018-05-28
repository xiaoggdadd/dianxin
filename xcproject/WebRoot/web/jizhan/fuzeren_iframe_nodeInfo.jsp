<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.noki.database.DataBase"%>
<%@page import="java.sql.ResultSet,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@page import="com.noki.jizhan.StationRelationshipTreeBean,com.noki.util.Query" %>
<%
	String path = request.getContextPath();
	int intnum=1;
	String color=null;
	String shi= request.getParameter("shi");
	String xian= request.getParameter("xian");
	String fzr1= request.getParameter("fzr");
	 String property1= request.getParameter("property");
	    String gsf= request.getParameter("gsf");
	    String bgsign1= request.getParameter("bgsign");
	    String dbyt= request.getParameter("dbyt");
	    String gdfs= request.getParameter("gdfs");
	    String zdcq1= request.getParameter("zdcq");
	    String g2= request.getParameter("g2");
	    String g3= request.getParameter("g3");
	    String kt1= request.getParameter("kt1");
	    String kt2= request.getParameter("kt2");
	    String kdsb= request.getParameter("kdsb");
	    String yysb= request.getParameter("yysb");
	
	String jztype = "",property = "", jzname="",stationtype="",manualauditname_station="",
	yftype= "",gdtype = "",fzr="",memo="",zdcq="",bgsign="",dbid="",dfzflx="",dfbzw="",id="";
			 ElectricFeesFormBean bean = new ElectricFeesFormBean();
			 List<ElectricFeesFormBean> fylist = null;
	       	 fylist = bean.getzhandian(shi,xian,fzr1,property1,gsf,bgsign1,dbyt,gdfs,zdcq1,g2,g3,kt1,kt2,kdsb,yysb);	 
	 if(fylist.size()!=0)
	{
	
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
			height:23px
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
 </style>
  </head>
  <body>
  <p style="font-size: 14px;font-weight: 900;font-family: 微软雅黑">站点信息如下</p>
  	<form action="">
  		<div style="width:100%;height:330px;overflow-x:auto;overflow-y:auto;border:1px" >  
  			<table width="100%"  border="0" cellpadding="1" cellspacing="1" class="form_label" bgcolor="#cbd5de">
  				<tr bgcolor="#DDDDDD" class="relativeTag">
	                <td width="23%"><div align="center" class="btt">站点名称</div></td>
	                <td width="7%"><div align="center" class="btt">站点类型</div></td>
	                <td width="7%"><div align="center"  class="btt">站点属性</div></td>
	                <td width="7%"><div align="center" class="btt">集团报表类型</div></td>
	                <td width="6%"><div align="center" class="btt">站点产权</div></td>
		            <td width="5%"><div align="center" class="btt">是否标杆</div></td>
	                <td width="6%"><div align="center" class="btt">用房类型</div></td>
	                <td width="6%"><div align="center" class="btt">供电方式</div></td>
	                <td width="6%"><div align="center" class="btt">站点审核人</div></td>
	                <td width="5%"><div align="center" class="btt">详细</div></td>
            	</tr>
  			 <%
			 for(ElectricFeesFormBean bean1:fylist){

       			//num为序号，不同页中序号是连续的
       			jztype =bean1.getJztype();
       			property = bean1.getProperty();
       			jzname =bean1.getJzname();
       			stationtype =bean1.getStationtype();
       			manualauditname_station = bean1.getManualauditname();
       			yftype = bean1.getYflx();
       			gdtype = bean1.getGdfs();
       			zdcq =bean1.getZdcq();
       			bgsign = bean1.getBgsign();
       			dbid =bean1.getDbid();
       			dfzflx = bean1.getDfzflx();
       			id = bean1.getId();
       			if(bgsign.equals("1")){
       				bgsign="是";
       			}else {
       				bgsign="否";
       			}
       			intnum++;
       			if(intnum%2==0){
				    color="#FFFFFF";
	
				 }else{
				    color="#DDDDDD" ;
				}
       			if(dfzflx.equals("月结")||dfzflx.equals("预支")){
       				dfbzw="1";
       			}else{
       				dfbzw="2";
       			}
       			
       %>
       <tr bgcolor='<%=color %>'>    		
       		<td >
       			<div align="left"  ><a href="javascript:zhandian('<%=id %>')" ><%=jzname%></a></div>
       		</td>
       		<td >
       			<div align="left"  ><%=stationtype%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=property%></div>
       		</td>
       		<td >
       			<div align="center"  ><%=jztype%></div>
       		</td>
       		<td >
       			<div align="center"  ><%=zdcq%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=bgsign%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=yftype%></div>
       		</td>
       		<td >
       			<div align="center"  ><%=gdtype%></div>
       		</td>
            <td >
       			<div align="left"  ><%=manualauditname_station%></div>
       		</td>
       		<td>
       			<div align="center"  ><a href="javascript:lookDetailss('<%=dbid%>','<%=dfzflx%>','<%=dfbzw%>')">详细</a></div>
       		</td>
       
       </tr>
  			
  		
  	<%
  		}
		}
  	%>
  		</table>
  	</div>
    </form>
  </body>
  <script type="text/javascript">
  var path='<%=path%>';
  function zhandian (id){
	  window.open(path+"/web/jizhan/zhandianDetailed.jsp?id="+id,'','width=500,height=600,status=yes,scrollbars=yes,resizable=yes,left=300,top=100')
	  
  }
  function lookDetailss(dbid,dfzflx,dfbzw){ 
	 var path='<%=path%>';   
	 window.open(path+"/web/check/showdfxx.jsp?dbid="+dbid+"&dfzflx="+dfzflx+"&dfbzw="+dfbzw,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
    }
  </script>
</html>
