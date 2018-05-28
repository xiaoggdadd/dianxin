<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.noki.mobi.cx.*" %>
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
    String startmonth=request.getParameter("startmonth");
    String endmonth=request.getParameter("endmonth");
   Account account = (Account)session.getAttribute("account");
   String loginId = account.getAccountId();
    //String roleId = account.getRoleId();

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
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
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

<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
</head>
<script type="text/javascript">
var path='<%=path%>';
    $(function(){
			$("#cancelBtn").click(function(){
				cancel();
			});
	});

</script>
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
		<td colspan="8"><span style="color:black;font-weight:bold;font-size=14;">站点电费明细</span></td>
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
  	  <table width="100%" height="30%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
      	
       <%
             String str="",wherestr="";
             if(zdcode!=null&&!zdcode.equals("")){
               str=str+" and zd.zdcode='"+zdcode+"'";
             }
             SiteBeanchmark bean = new SiteBeanchmark();
            
            String color="";
             List<SiteBeanchmark> list = bean.getsitec4(zdcode,startmonth,endmonth);
       	     String zdname="",lasttime="",thistime="",lastdegree="",thisdegree="",start="",end="",floatdegree="",blhdl="",floatpay="",actualpay="",accountmonth="";
	
		     int intnum=0;
		    for(SiteBeanchmark bean1:list){
		    	zdname=bean1.getZdname();
		    	lasttime=bean1.getLasttime();
		    	thistime=bean1.getThistime();
		    	lastdegree=bean1.getLastdegree();
		    	thisdegree=bean1.getThisdegree();
		    	start=bean1.getStartmonth();
		    	end=bean1.getEndmonth();
		    	floatdegree=bean1.getFloatdegree();
		    	blhdl=bean1.getBlhdl();
		    	floatpay=bean1.getFloatpay();
		    	actualpay=bean1.getActualpay();
		    	accountmonth=bean1.getAccountmonth();
		    	if(lasttime==null)lasttime="";
		    	DecimalFormat mat=new DecimalFormat("0.0");
		    	DecimalFormat mat1=new DecimalFormat("0.00");
		    	lastdegree=mat.format(Double.parseDouble(lastdegree));
		    	thisdegree=mat.format(Double.parseDouble(thisdegree));
		    	floatdegree=mat.format(Double.parseDouble(floatdegree));
		    	blhdl=mat.format(Double.parseDouble(blhdl));
		    	floatpay=mat1.format(Double.parseDouble(floatpay));
		    	actualpay=mat1.format(Double.parseDouble(actualpay));
           if (intnum % 2 == 0) {
        				color = "#FFFFFF";
        			} else {
        				color = "#DDDDDD";
        			}
        			  intnum++;
       %>
     <tr height = "23" class="relativeTag ">
                        <td width="15%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div>
            			</td>
            			<td width="10%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div>
            			</td>
            			<td width="10%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次电表读数</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次电表读数</div>
            			</td>
            			<td width="9%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始月份</div>
            			</td>
            			<td width="9%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束月份</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量调整</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际用电量</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费调整</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际电费</div>
            			</td>
            			<td width="9%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div>
            			</td>
            			</tr>
             <tr bgcolor="<%=color%>">
       		<td>
       			<div align="left" ><%=zdname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lasttime%></div>
       		</td>
       		<td>
       			<div align="center" ><%=thistime%></div>
       		</td>
       		<td>
       			<div align="right" ><%=lastdegree%></div>
       		</td>
       		<td>
       			<div align="right" ><%=thisdegree%></div>
       		</td>
       		<td>
       			<div align="center" ><%=start%></div>
       		</td>
       		<td>
       			<div align="center" ><%=end%></div>
       		</td>
       		<td>
       			<div align="right" ><%=floatdegree%></div>
       		</td>
       		<td>
       			<div align="right" ><%=blhdl%></div>
       		</td>
       		<td>
       			<div align="right" ><%=floatpay%></div>
       		</td>
       		<td>
       			<div align="right" ><%=actualpay%></div>
       		</td>
       		<td>
       			<div align="center" ><%=accountmonth%></div>
       		</td>
       </tr>
      <%}%>
        
        </table>
</form>
</body>
</html>

