<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>

<%

String path = request.getContextPath();
String shengId = (String)session.getAttribute("accountSheng");
String loginName = (String)session.getAttribute("loginName");
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	String topstr=request.getParameter("str");
	System.out.println("topstr"+topstr);
	String zdcode=request.getParameter("zdcode");
	String jzname4=request.getParameter("jzname");
   Account account = (Account)session.getAttribute("account");
   
   String loginId = account.getAccountId();
   String shi=request.getParameter("shi")!=null?request.getParameter("shi"):"0";
   String zt=request.getParameter("zt")!=null?request.getParameter("zt"):"-1";
   String jzxlx1=request.getParameter("jzxlx")!=null?request.getParameter("jzxlx"):"0";
   String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
   String jrwtype=request.getParameter("jrwtype")!=null?request.getParameter("jrwtype"):"0";
   String zdname=request.getParameter("stationname");
    //String roleId = account.getRoleId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    //System.out.println("roleId:"+roleId);
%>

<html>
<head>
<title>

</title>
<style>
          
        .form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
		}
		.form{
			width:90px;
			height: 18px;
	
		}
            .style1 {
	color: #FF9900;
	font-weight: bold;
}
.sel{
		width:130px;
		 
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
		}
		.relativeTag{     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px ##888888 solid}
 .style7 {font-weight: bold}
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
 </style>
 <LINK href="<%=path%>/images/css.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0804");
%>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
  <table width="100%" border="0" class="form_label" cellspacing="1" cellpadding="1">
  

          
		<tr>	 
		<td colspan="8"><span style="color:black;font-weight:bold;font-size=14;">采集点电量详细信息表</span></td>
	</tr>
	<tr>
		<td>
		<table  class="form_label" border="0"  cellspacing="0" cellpadding="0">
		<tr>
		<td height="19" colspan="8">
		<div id="parent" style="display:inline"><div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div></div>	
		</td>
		</tr>
		</table>
		</td>
           </tr>
  	 </table> 
  	  <table width="99%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                      <tr  height ="20">
                      <td class="form_label" width="4%" height="20" bgcolor="#DDDDDD"><div align="center" >序号</div>
            			</td>
                        <td  class="form_label"  width="19%" height="20" bgcolor="#DDDDDD"><div align="center" >站点名称</div>
            			</td>
            			<td class="form_label" width="14%" height="20" bgcolor="#DDDDDD"><div align="center">电表ID</div>
            			</td>
            			<td class="form_label" width="9%" height="20" bgcolor="#DDDDDD"><div align="center" >电表名称</div>
            			</td>
            			<td class="form_label" width="7%" height="20" bgcolor="#DDDDDD"><div align="center" >用电设备</div>
            			</td>
            			<td class="form_label" width="10%" height="20" bgcolor="#DDDDDD"><div align="center" >上次电表读数</div>
            			</td>
            			<td class="form_label" width="9%" height="20" bgcolor="#DDDDDD"><div align="center" >本次电表读数</div>
            			</td>
            			<td class="form_label" width="9%" height="20" bgcolor="#DDDDDD"><div align="center" >实际耗电量</div>
            			</td>
            			<td class="form_label" width="10%" height="20" bgcolor="#DDDDDD"><div align="center">上次抄表时间</div>
            			</td>
            			<td class="form_label" width="10%" height="20" bgcolor="#DDDDDD"><div align="center" >本次抄表时间</div>
            			</td>
            			</tr>
                      
       <%
       
      
             String str="",wherestr="";
             if(zdcode!=null&&!zdcode.equals("")){
               str=str+" and zd.zdcode='"+zdcode+"'";
             }
             haodianliangBean bean = new haodianliangBean();
            
             ArrayList fylist = new ArrayList();
       	  fylist = bean.getCollectWarn1(str,wherestr,loginId);
       	
		String jzname = "",flag = "",time = "",zhuangtai="";
		String ld="",td="",ldt="",thdt="";
		String dbid="",dbname="",blhdl="",ydsb="";
		Double df=0.00;
		int intnum=0;
		
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
		     //num为序号，不同页中序号是连续的
		    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		     jzname = jzname != null ? jzname : "";	
		    ld = (String)fylist.get(k+fylist.indexOf("LASTDEGREE"));
		    ld = ld != null ? ld : "";	
		    td = (String)fylist.get(k+fylist.indexOf("THISDEGREE"));
		    td = td != null ? td : "";	
		    ldt = (String)fylist.get(k+fylist.indexOf("LASTDATETIME"));
		    ldt = ldt != null ? ldt : "";	
		    thdt = (String)fylist.get(k+fylist.indexOf("THISDATETIME"));
		    thdt = thdt != null ? thdt : "";
		    	 dbid = (String)fylist.get(k+fylist.indexOf("DBID"));
		    dbid = dbid != null ? dbid : "";
		    dbname = (String)fylist.get(k+fylist.indexOf("DBNAME"));
		    dbname = dbname != null ? dbname : "";
		     blhdl = (String)fylist.get(k+fylist.indexOf("BLHDL"));
		    blhdl = blhdl != null ? blhdl : "";
		    
		    ydsb = (String)fylist.get(k+fylist.indexOf("YDSB"));
		    ydsb = ydsb != null ? ydsb : "";
		     
			String color=null;
			if("null".equals(ld)){
			ld="0";
			}
			if("null".equals(td)){
			td="0";
			}
			if("null".equals(blhdl)){
			blhdl="0";
			}
			
			
            
             DecimalFormat aa=new DecimalFormat("0.0");
             ld = aa.format(Double.parseDouble(ld));
            td = aa.format(Double.parseDouble(td));
            blhdl=aa.format(Double.parseDouble(blhdl));
		    
			
         
if (intnum % 2 == 0) {
        				color = "#FFFFFF";

        			} else {
        				color = "#DDDDDD";
        			}
        			  intnum++;
       %>
 
       <tr bgcolor="<%=color%>">
          <td class="form_label">
       			<div align="center" ><%=intnum%></div>
       		</td>
       		<td class="form_label">
       			<div align="left" ><%=jzname%></div>
       		</td >
       		<td class="form_label">
       			<div align="center" ><%=dbid%></div>
       		</td>
       		<td class="form_label">
       			<div align="left" ><%=dbname%></div>
       		</td>
       		<td class="form_label">
       			<div align="center" ><%=ydsb%></div>
       		</td>
       		<td class="form_label">
       			<div align="right" ><%=ld%></div>
       		</td>
       		<td class="form_label">
       			<div align="right" ><%=td%></div>
       		</td>
       		<td class="form_label">
       			<div align="right" ><%=blhdl%></div>
       		</td>
       		<td class="form_label">
       			<div align="center" ><%=ldt%></div>
       		</td>
       		<td class="form_label">
       			<div align="center" ><%=thdt%></div>
       		</td>
       </tr>
      <%}%>
        <%}%>
        </table>
  	 
</form>
</body>
</html>

