<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%
	        String path = request.getContextPath();
	        String loginName = (String)session.getAttribute("loginName");
	        Account account = (Account)session.getAttribute("account");
	        String accountname=account.getAccountName();
	        String loginId=account.getAccountId();
		String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
		int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
	          curpage=Integer.parseInt(s_curpage);
	          String permissionRights="";
	            String roleId = (String)session.getAttribute("accountRole");
	          String color=null;
	          String zdcode =request.getParameter("zdcode");
	          System.out.println("3333333"+zdcode);
	          
          
%>

<html>
<head>
<title>
站点列表
</title>
<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

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
}.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
 .btt{ bgcolor:#888888;}
  .bttcn{ color:black;font-weight:bold;}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>



</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0101");
JiZhanBean bean = new JiZhanBean();
System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>

<body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
	<table>
	 <tr>
					    	<td colspan="5" width="50">
												 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点详细信息列表</span>	
												</div>
												<input type="hidden" name="accountname" value="<%=accountname %>"/>
											</td>
					    	
	    	        </tr>
	    	        </table>
     <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >   
  		<table width="1080px" height="90px" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
            	
       <%
    
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageDatashow(zdcode);
       	 allpage=bean.getAllPage();
       	 //jztype集团报表类型
		String jzname = "",id="",szdq = "",dianfei="",stationtype2= "",jzproperty = "", jztype= "",yflx = "",jnglmk="",gdfs="",area="",zdcode1="",g2="",countg2="",g3="",countg3="";
		String xuni="",yysb="",yysbsl="",kdsb="",kdsbsl="",kt1="",countkt1="",kt2="",count2kt2="",zgd="";
		
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
id = (String)fylist.get(k+fylist.indexOf("ID"));
		     //num为序号，不同页中序号是连续的
				//id = (String)fylist.get(k+fylist.indexOf("ID"));
				jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
				jzname = jzname != null ? jzname : "";
		    	szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
				szdq = szdq != null ? szdq : "";
		    	jzproperty = (String)fylist.get(k+fylist.indexOf("PROPERTY"));
				jzproperty = jzproperty != null ? jzproperty : "";
		    	jztype = (String)fylist.get(k+fylist.indexOf("JZTYPE"));
				jztype = jztype != null ? jztype : "";
		    	//添加站点类型
		    	stationtype2 = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
				stationtype2 = stationtype2 != null ? stationtype2 : "";
				yflx = (String)fylist.get(k+fylist.indexOf("YFLX"));
				yflx = yflx != null ? yflx : "";
				jnglmk = (String)fylist.get(k+fylist.indexOf("JNGLMK"));
				jnglmk = jnglmk != null ? jnglmk : "";
                if("1".equals(jnglmk)){
				 jnglmk="有";
				}else{
				 jnglmk="无";
				}
				
				xuni = (String)fylist.get(k+fylist.indexOf("XUNI"));
				xuni = xuni != null ? xuni : "";
                if("1".equals(xuni)){
				 xuni="是";
				}else{
				 xuni="否";
				}
				
				
				
				
				gdfs = (String)fylist.get(k+fylist.indexOf("GDFS"));
				gdfs = gdfs != null ? gdfs : "";
				area = (String)fylist.get(k+fylist.indexOf("AREA"));
				area = area != null ? area : "";
				dianfei = (String)fylist.get(k+fylist.indexOf("DIANFEI"));
				dianfei = dianfei != null ? dianfei : "0";
				if(dianfei.equals("null")||dianfei.equals("")){
				dianfei="0";
				}
				zdcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
				zdcode = zdcode != null ? zdcode : "";
				g2 = (String)fylist.get(k+fylist.indexOf("G2"));
				g2 = g2 != null ? g2 : "";
				if("1".equals(g2)){
				 g2="有";
				}else{
				 g2="无";
				}
				g3 = (String)fylist.get(k+fylist.indexOf("G3"));
				g3 = g3 != null ? g3 : "";
				if("1".equals(g3)){
				 g3="有";
				}else{
				 g3="无";
				}
				yysb = (String)fylist.get(k+fylist.indexOf("YYSB"));
				yysb = yysb != null ? yysb : "";
		        yysbsl = (String)fylist.get(k+fylist.indexOf("YYSBSL"));
				yysbsl = yysbsl != null ? yysbsl : "0";
				if("1".equals(yysb)){
				 yysb="有";
				}else{
				 yysb="无";
				}
				 
				 kdsb = (String)fylist.get(k+fylist.indexOf("YYSB"));
				kdsb = kdsb != null ? kdsb : "";
		       kdsbsl = (String)fylist.get(k+fylist.indexOf("KDSBSL"));
				kdsbsl = kdsbsl != null ? kdsbsl : "0";
				if("1".equals(kdsb)){
				 kdsb="有";
				}else{
				 kdsb="无";
				}
					 kt1 = (String)fylist.get(k+fylist.indexOf("KT1"));
				     kt1 = kt1 != null ? kt1 : "";
				     if("1".equals(kt1)){
				      kt1="有";
				     }else{
				      kt1="无";
				     }
				     
				     
			    
				kt2 = (String)fylist.get(k+fylist.indexOf("KT2"));
				     kt2 = kt2 != null ? kt2 : "";
				
				 kt2 = (String)fylist.get(k+fylist.indexOf("KT2"));
				     kt2 = kt2 != null ? kt2 : "";
				     if("1".equals(kt2)){
				      kt2="有";
				     }else{
				      kt2="无";
				     }
				     zgd = (String)fylist.get(k+fylist.indexOf("ZGD"));
				zgd = zgd != null ? zgd : "";
				if("1".equals(zgd)){
				      zgd="是";
				     }else{
				      zgd="否";
				     }
				
				
				DecimalFormat price = new DecimalFormat("0.0000");
			dianfei = price.format(Double.parseDouble(dianfei));
       %>
      
    	        <%
    	        
    	         }}
    	         
    	         %>    
    	  <tr height = "10">
                        <td width="7%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                        
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
                         <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">单价</div></td> 
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td>
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">用房类型</div></td>						

            			<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">节能设备</div></td>
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">供电方式</div></td>                        
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">虚拟站点</div></td>
                                                        
                      </tr>
                     <tr bgcolor="white"> 		
       		<td>
       			<div align="center"  ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="left"  ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="right"  ><%=dianfei%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=stationtype2%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=jzproperty%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=jztype%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=yflx%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=jnglmk%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=gdfs%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=xuni%></div>
       		</td>
       		
       		
       		
       		</tr>
                      
                      
                      
                      <tr>
                      <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点代码</div></td>
                       <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">有无2G设备</div></td> 
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">有无3G设备</div></td> 
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">有无语音设备</div></td> 
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">语音设备数量</div></td> 
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">有无宽带设备</div></td> 
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">宽带设备数量</div></td> 
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">有无空调1</div></td> 
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">有无空调2</div></td> 
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">是否直供电</div></td> 
            </tr>       
    	          <tr bgcolor="white">
    	          <td>
       			<div align="center"><%=zdcode%></div>
       		</td>
       		<td>
       			<div align="center"><%=g2%></div>
       		</td>
       		
       		<td>
       			<div align="center"><%=g3%></div>
       		</td>
       		
       		<td>
       			<div align="center"><%=yysb%></div>
       		</td>
       		<td>
       			<div align="right"><%=yysbsl%></div>
       		</td>
       			<td>
       			<div align="center"><%=kdsb%></div>
       		</td>
       		<td>
       			<div align="right"><%=kdsbsl%></div>
       		</td>
       		<td>
       			<div align="center"><%=kt1%></div>
       		</td>
       		
       		<td>
       			<div align="center"><%=kt2%></div>
       		</td>
       		<td>
       			<div align="center"><%=zgd%></div>
       		</td>
       		
       </tr>
    	         
    	         
    	         
  	 </table> 
  	 </div>
 
</form>
</body>
</html>

