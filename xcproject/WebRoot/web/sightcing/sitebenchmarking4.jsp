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
		<td colspan="8"><span style="color:black;font-weight:bold;font-size=14;">站点明细</span></td>
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
            DecimalFormat mat=new DecimalFormat("0.0000");
            String color="";
             List<SiteBeanchmark> list = bean.getsite4(zdcode);
	         String zdname="",zdlx="",zdsx="",jztype="",df="",yflx="",gdfs="",szdq="",zlfh="",jlfh="",g2="",g3="",dytype="",kt1="",kt2="",kdsb="",yysb="",zgd="",zpsl="",zssl="",kdsbsl="",yysbsl="";
		     int intnum=0;
		    for(SiteBeanchmark bean1:list){
		    	zdname=bean1.getZdname();
		    	zdlx=bean1.getZdlx();
		    	zdsx=bean1.getZdsx();
		    	jztype=bean1.getJztype();
		    	df=bean1.getDf();
		    	yflx=bean1.getYflx();
		    	gdfs=bean1.getGdfs();
		    	szdq=bean1.getSzdq();
		    	zlfh=bean1.getZlfh();
		    	jlfh=bean1.getJlfh();
		    	g2=bean1.getG2();
		    	g3=bean1.getG3();
		    	dytype=bean1.getDytype();
		    	kt1=bean1.getKt1();
		    	kt2=bean1.getKt2();
		    	kdsb=bean1.getKdsb();
		    	yysb=bean1.getYysb();
		    	zgd=bean1.getZgd();
		    	zpsl=bean1.getZpsl();
		    	zssl=bean1.getZssl();
		    	yysbsl=bean1.getYysbsl();
		    	kdsbsl=bean1.getKdsbsl();
		    	if(zdname==null||zdname.equals("null"))zdname="";
		    	if(zdlx==null||zdlx.equals("null"))zdlx="";
		    	if(zdsx==null||zdsx.equals("null"))zdsx="";
		    	if(jztype==null||jztype.equals("null"))jztype="";
		    	if(df==null||df.equals("null")){df="";}else{df=mat.format(Double.parseDouble(df));};
		    	if(yflx==null||yflx.equals("null"))yflx="";
		    	if(gdfs==null||gdfs.equals("null"))gdfs="";
		    	if(szdq==null||szdq.equals("null"))szdq="";
		    	if(zlfh==null||zlfh.equals("null"))zlfh="";
		    	if(jlfh==null||jlfh.equals("null"))jlfh="";
		    	if(g2==null||g2.equals("null"))g2="";
		    	if(g3==null||g3.equals("null"))g3="";
		    	if(dytype==null||dytype.equals("null"))dytype="";
		    	if(kt1==null||kt1.equals("null"))kt1="";
		    	if(kt2==null||kt2.equals("null"))kt2="";
		    	if(kdsb==null||kdsb.equals("null"))kdsb="";
		    	if(yysb==null||yysb.equals("null"))yysb="";
		    	if(zgd==null||zgd.equals("null"))zgd="";
		    	if(zpsl==null||zpsl.equals("null"))zpsl="";
		    	if(zssl==null||zssl.equals("null"))zssl="";
		    	if(yysbsl==null||yysbsl.equals("null"))yysbsl="";
		    	if(kdsbsl==null||kdsbsl.equals("null"))kdsbsl="";
		    	if(g2.equals("1")){g2="有";}if(g2.equals("0")){g2="无";}
		    	if(g3.equals("1")){g3="有";}if(g3.equals("0")){g3="无";}
		    	if(kt1.equals("1")){kt1="有";}if(kt1.equals("0")){kt1="无";}
		    	if(kt2.equals("1")){kt2="有";}if(kt2.equals("0")){kt2="无";}
		    	if(kdsb.equals("1")){kdsb="有";}if(kdsb.equals("0")){kdsb="无";}
		    	if(yysb.equals("1")){yysb="有";}if(yysb.equals("0")){yysb="无";}
		    	if(zgd.equals("1")){zgd="是";}if(zgd.equals("0")){zgd="否";}
		    	
		    
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
            			<td width="10%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div>
            			</td>
            			<td width="10%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">单价</div>
            			</td>
            			<td width="9%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">房屋类型</div>
            			</td>
            			<td width="9%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">供电方式</div>
            			</td>
            			<td width="9%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">交流负荷</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">直流负荷</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">2G</div>
            			</td>
            			
            			
            			</tr>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="left" ><%=zdname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdlx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdsx%></div>
       		</td>
       		<td>
       			<div align="right" ><%=jztype%></div>
       		</td>
       		<td>
       			<div align="right" ><%=df%></div>
       		</td>
       		<td>
       			<div align="center" ><%=yflx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=gdfs%></div>
       		</td>
       		<td>
       			<div align="right" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="right" ><%=jlfh%></div>
       		</td>
       		<td>
       			<div align="right" ><%=zlfh%></div>
       		</td>
       		<td>
       			<div align="right" ><%=g2%></div>
       		</td>
       		
       </tr>
        <tr height = "23" class="relativeTag ">
        	            <td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">地域属性</div>
            			</td>
                        <td width="15%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">空调1</div>
            			</td>
            			<td width="10%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">空调2</div>
            			</td>
            			<td width="10%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">宽带设备</div>
            			</td>
            		
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">语音设备</div>
            			</td>
            			<td width="9%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">直供电</div>
            			</td>
            			<td width="9%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">载频数量</div>
            			</td>
            			<td width="9%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">载扇数量</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">宽带设备数量</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">3G</div>
            			</td>
            			<td width="6%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn"></div>
            			</td>
            			
            			</tr>
            			<tr bgcolor="<%=color%>">
       		<td>
       			<div align="left" ><%=dytype%></div>
       		</td>
       		<td>
       			<div align="center" ><%=kt1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=kt2%></div>
       		</td>
       		<td>
       			<div align="right" ><%=kdsb%></div>
       		</td>
       		<td>
       			<div align="right" ><%=yysb%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zgd%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zpsl%></div>
       		</td>
       		<td>
       			<div align="right" ><%=zssl%></div>
       		</td>
       		<td>
       			<div align="right" ><%=kdsbsl%></div>
       		</td>
       	<td>
       			<div align="center" ><%=g3%></div>
       		</td>
       		<td>
       			<div align="right" ></div>
       		</td>
       		
       </tr>
      <%}%>
        
        </table>
</form>
</body>
</html>

