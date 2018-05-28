<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@page import="com.noki.electricfees.javabean.*"%>
<%@page import="com.noki.tongjichaxu.javabean.fybzCountBean"%>
<%@page import="com.noki.tongjichaxu.javabean.fybzbean"%>
<%@page import="com.noki.function.*"%>
<%@page import="com.noki.tongjichaxu.javabean.cbJzBean"%>
<%@page import="com.noki.tongjichaxu.cbJzDao"%>
<%@page import="java.util.*" %>
<% 
    
	String shiname=request.getParameter("shiname")!=null?request.getParameter("shiname"):"";
	String bzyf=request.getParameter("bzyf")!=null?request.getParameter("bzyf"):"";
	String loginId=request.getParameter("loginId")!=null?request.getParameter("loginId"):"";
	String property1=request.getParameter("property")!=null?request.getParameter("property"):"";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
  </head>
  <style>
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:20px

		}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
.text_font {
	width: 50px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
}
.dext_font {
	width: 50px;
	font-family: 宋体;
	font-size: 12px;
	text-align:right;
	line-height: 100%;
}
.stext_font {
	width: 30px;
	font-family: 宋体;
	font-size: 12px;
	text-align:right;
	line-height: 100%;
}

.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
};
 .memo {border: 1px #C8E1FB solid}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}

.bttcn{color:black;font-weight:bold;}

.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.form_la{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}
</style>

 
  <body>
   <form action="" name="form1" >
    <% 
    %><% %>
    
    <table width="1100px" border="0" cellspacing="1" cellpadding="1" align="center">
           <tr class="relativeTag" >
            <td width="5%" bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn">序号</div></td>
             <td width="10%" bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn">地市</div></td>
              <td width="20%" bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">区县</div></td>
             <td width="20%" bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">乡镇</div></td>
             <td width="10%" bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">站点id</div></td>
             <td width="20%" bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">站点名称</div></td>
              <td width="15%" bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">站点属性</div></td>
               
         </tr>
       <% 		 
		cbJzDao bean=new cbJzDao();  
		List<cbJzBean> fylist = null;
		
		int intnum=0;
		 int countt=1;       
        String shi="",xian="",xiaoqu="",zdid="",jzname="",property=""; 
       
        fylist = bean.getCbgk(shiname,bzyf,loginId,property1);
		 
		  if(fylist!=null){
             for (cbJzBean beans:fylist) {   
             shi = beans.getShiname();
             xian = beans.getXian();
             xiaoqu = beans.getXiaoqu() ;
             zdid = beans.getZdid();
             jzname = beans.getJzname();
             property = beans.getProperty();
         
       
         
			
			 String color=null;
			 
            if(intnum%2==0){
			    color="#FFFFFF";
			 }else{
			    color="#DDDDDD";
			}
			intnum++;
            	
       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		
       		<td><div align="center">
       			<%=countt++%>
       			</div>
       		</td>
       		<td><div align="left">
       			<%=shi%>
       			</div>
       		</td>
       		<td><div align="left">
       			<%=xian%>
       			</div>
       		</td>
       		<td><div align="left">
       			<%=xiaoqu%>
       			</div>
       		</td>
       		<td><div align="left">
       			<%=zdid%>
       			</div>
       		</td>
       		<td><div align="left">
       			<%=jzname%>
       			</div>
       		</td>
       		<td><div align="center">
       			<%=property%>
       			</div>
       		</td>
       		
       </tr>    
   <%}} %>
  	 </table> 
  	 
  	 </form>
  </body>

</html>

</script>
