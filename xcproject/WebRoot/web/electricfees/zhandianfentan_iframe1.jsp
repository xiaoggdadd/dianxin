<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@page import="com.noki.electricfees.javabean.*"%>
<%
	String sheng = (String)session.getAttribute("accountSheng");
	String roleId = (String)session.getAttribute("accountRole");
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
        String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
       //System.out.println("logManage.jsp>>"+beginTime);
	    String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
	    String zdsx=request.getParameter("sptype")!=null?request.getParameter("sptype"):"0";
	     String whereStr="";
        String path = request.getContextPath();
        String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
       
        
         String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
          
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
    <% if(shi != null && !shi.equals("") && !shi.equals("0")){
    %>
    <table width="100%" border="0" cellspacing="1" cellpadding="1">
           <tr class="relativeTag" >
             <td width="2%" bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn">ID</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">站点名称</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">站点属性</div></td>
             <td  bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn">电表ID</div></td>
           
         </tr>
       <%
         	if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and z.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and z.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" and z.xiaoqu='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("")){
				whereStr=whereStr+" and z.jzname like '%"+zdname+"%'";
			}
			if(zdsx != null && !zdsx.equals("") && !zdsx.equals("0")){
				whereStr=whereStr+" and i.code  ='"+zdsx+"'";
			}
			
		 System.out.println("监测点-whereStr:"+whereStr);
		 Zhandianfentanmx bean=new Zhandianfentanmx();  
		 EquipmentmanageBean bean1=new EquipmentmanageBean();
		 if(shi != null && !shi.equals("") && !shi.equals("0")){
       	ArrayList fylist = bean.getPageData(curpage,whereStr,account.getAccountId());
       	 allpage=bean.getAllPage();
       String str="";
		String jzname = "",dianbiaoid = "";
		String zdsx01="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
			jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    dianbiaoid = (String)fylist.get(k+fylist.indexOf("DIANBIAOID"));
			zdsx01 = (String)fylist.get(k+fylist.indexOf("NAME"));
			String color=null;
            str="dianBiaoId="+dianbiaoid;
            	if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		<td>
       			<%=intnum++%>
       		</td>
       		<td>
       			<%=jzname%>
       		</td>
       		<td>
       			<%=zdsx01%>
       		</td>
       		<td>
       			<a target="treeNodeInfo" name="dianbiao" href=<%=path+"/web/electricfees/zhandianfentan_iframe2.jsp?dianbiaoid="+dianbiaoid%>><%=dianbiaoid%></a>
       		</td>
       		
       		
       </tr>
       <%} }}%>
     
     
  	 </table> 
  	 <%} %>
  </body>
</html>
