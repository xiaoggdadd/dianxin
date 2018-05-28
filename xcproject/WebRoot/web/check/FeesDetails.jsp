<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.electricfees.javabean.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>

<html>
<head>
<%
	String path = request.getContextPath();
%>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
	<style>
	 .bttcn{ color:black;font-weight:bold;}
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
	</style>
</head>
<body class="body" style="overflow-x:hidden;">
<form action="" name="form1" method="POST">
<table>
	 <tr>
					    	<td colspan="5" width="50">
												 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电量电费信息列表</span>	
												</div>
											</td>
					    	
	    	        </tr>
	    	        </table>

  		<table width="1100px" height="100px" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
   <%
   
    String szdq="",jzname="",jztype="",inputoperator="",inputdatetime="",dbid="",ydsb="",dllx="",dlid="",lastdatetime="",thisdatatime="",floatdegree="",
    beilv="",actualdegree="",accountmonth="",unitprice="",floatpay="",actualpay="",fffs="",pllx="",noteno="",payoperator,autoauditstatus="",manualaudittatus="";   	     
    String dfid = request.getParameter("electricfeeId")!=null?request.getParameter("electricfeeId"):"";
   	String zdcode=request.getParameter("zdcode");
        	
        	ElectricFeesBean bean = new ElectricFeesBean();
        	List list = bean.getPageDataCheck1(dfid,zdcode);
        	String zt=list.get(24).toString();
        	if(zt.equals("1")){
        	 zt="通过";
        	}else{
        	 zt="未通过";
        	}
        	
        		String ztt=list.get(25).toString().trim();
        	if(ztt.equals("1")){
        	 ztt="通过";
        	}else{
        	 ztt="未通过";
        	}
        	DecimalFormat ma=new DecimalFormat("0.00");
        	DecimalFormat maa=new DecimalFormat("0.0000");
        	String s11=list.get(11).toString().trim();
        	String s12=list.get(12).toString().trim();
        	if(s11.equals("")||s11==null){
        	 s11="0";
        	}
        	s11=ma.format(Double.parseDouble(s11));
        		if(s12.equals("")||s12==null){
        	 s12="0";
        	}
        	s12=ma.format(Double.parseDouble(s12));
        	String s13=list.get(13).toString().trim();
        	
        	if(s13.equals("")||s13==null){
        	 s13="0";
        	}
        	s13=ma.format(Double.parseDouble(s13));
        	
        	
            String s15=list.get(15).toString().trim();
            if(s15.equals("")||s15==null){
        	 s15="0";
        	}
        	s15=ma.format(Double.parseDouble(s15));
            
            String s17=list.get(17).toString().trim();
               if(s17.equals("")||s17==null){
        	 s17="0";
        	}
        	s17=maa.format(Double.parseDouble(s17));
            
            
        	String s19=list.get(19).toString().trim();
        	 if(s19.equals("")||s19==null){
        	 s19="0";
        	}
        	s19=ma.format(Double.parseDouble(s19));
        	
        	
        	String s18=list.get(18).toString().trim();
        	 if(s18.equals("")||s18==null){
        	 s18="0";
        	}
        	s18=ma.format(Double.parseDouble(s18));
 
         	String pjbh=list.get(22).toString().trim();
        	if("null".equals(pjbh)||pjbh==null){
        	 	pjbh="";
        	}       	
        	
      %>
        <tr height = "10">
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                         <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td> 
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">抄表人</div></td>
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">抄表时间</div></td>
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电设备</div></td>
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">电流类型</div></td>						

            			<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">电量流水号</div></td>
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td>                        
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
                        
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表数</div></td>
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表数</div></td>                        
                        
                                                        
                      </tr>
                      
                      <tr bgcolor="white">
                     <td> 	<div align="left"><%=list.get(0)%></div>
       		</td>
       		<td>
       			<div align="left"  ><%=list.get(1)%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(2)%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(3)%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(4)%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(5)%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(6)%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(7)%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(8)%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(9)%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(10)%></div>
       		</td>
       		<td>
       			<div align="right"  ><%=s11%></div>
       		</td>
       		<td>
       			<div align="right"  ><%=s12%></div>
       		</td>
                      </tr>
                      
                       <tr height = "10">
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量调整</div></td>
                        
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">倍率</div></td>
                         <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际用电量</div></td> 
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费单价</div></td>
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际电费</div></td>
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费调整</div></td>						

            			<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">付费方式</div></td>
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据类型</div></td>                        
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据编号</div></td>
                        
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">交费操作员</div></td>
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">自动审核状态</div></td>                        
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核状态</div></td>
                                                        
                      </tr>
                      
                       <tr bgcolor="white">
                     <td> 	<div align="right"><%=s13%></div>
       		</td>
       		<td>
       			<div align="right"  ><%=list.get(14)%></div>
       		</td>
       		<td>
       			<div align="right"  ><%=s15%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(16)%></div>
       		</td>
       		<td>
       			<div align="right"  ><%=s17%></div>
       		</td>
       		<td>
       			<div align="right"  ><%=s19%></div>
       		</td>
       		<td>
       			<div align="right"  ><%=s18%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(20)%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(21)%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=pjbh%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=list.get(23)%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=zt%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=ztt%></div>
       		</td>
                      </tr>
</table>
</body>
</html>
