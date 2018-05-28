<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.PrepaymentQueryBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>

<%	   
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId=account.getAccountId();
        String roleId = account.getRoleId();
        String loginName = (String)session.getAttribute("loginName");
        
  
%>

<html>
<head>
<base target=_self> 
<title>
</title>
<style>
.relativeTag   {   
    z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);      
}; 
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
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
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.bttcn{color:black;font-weight:bold;}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
<%
      String dbid1=request.getParameter("dbid");
      if(dbid1!=null&&!"null".equals(dbid1)){
 %>
	<form action="" name="form1" id="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
    <td valign="top">														                    	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
   	                 <tr height = "23" class="relativeTag ">
                       	<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td> 
                        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属地区</div></td>
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始年月</div></td>                             
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束年月</div></td>
                        
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账年月</div></td>   
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">预付费金额</div> </td>
                        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入时间</div> </td>                        
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入人员</div> </td>  
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>                        
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>
                    </tr>
       <%  

       PrepaymentQueryBean bean = new PrepaymentQueryBean();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getlist(dbid1);
       
		String Id = "",ammeterdegreeidFk = "",money = "",prepaymentammeterid = "",szdq="";
		String zdid="",jzname="",bzmonth="",entrytime="",entrypensonnel="",startmonth="",endmonth="";
		int countxh=1;
      	 double money1=0;
		int intnum=0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();  
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		    Id = (String)fylist.get(k+fylist.indexOf("ID"));	
		    prepaymentammeterid = (String)fylist.get(k+fylist.indexOf("PREPAYMENT_AMMETERID"));	
		    prepaymentammeterid = prepaymentammeterid != null ? prepaymentammeterid : "";
		    
		    zdid=(String)fylist.get(k+fylist.indexOf("ZDCODE")); 
		    zdid = zdid != null ? zdid : "";	
		    			    
			ammeterdegreeidFk = (String)fylist.get(k+fylist.indexOf("ID"));
			ammeterdegreeidFk = ammeterdegreeidFk != null ? ammeterdegreeidFk : "";
			
			szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		    szdq = szdq != null ? szdq : "";
		    
		    jzname=(String)fylist.get(k+fylist.indexOf("JZNAME"));
		    jzname=jzname!=null?jzname:"";

			DecimalFormat mat=new DecimalFormat("0.00");
		    money = (String)fylist.get(k+fylist.indexOf("MONEY"));
		    money = money != null ? money : "";
		    if(money==null||money==""||money.equals("")||money.equals("null")||money.equals("o")){
				money="0.00";
			}
			money=mat.format(Double.parseDouble(money));
			 
            //添加录入时间、录入人员、起始时间、结束时间、报账月份
            entrytime = (String)fylist.get(k+fylist.indexOf("ENTRYTIME"));			
			if(entrytime=="0"||entrytime.equals("0")||entrytime.equals("null")){
		      entrytime="";
		    }else{
		   		entrytime = entrytime != null ? entrytime : "";
		    }
		    
		    entrypensonnel = (String)fylist.get(k+fylist.indexOf("ENTRYPENSONNEL"));			
			if(entrypensonnel=="0"||entrypensonnel.equals("0")||entrypensonnel.equals("null")){
		      entrypensonnel="";
		    }else{
		    	entrypensonnel = entrypensonnel != null ? entrypensonnel : "";
		    }

			startmonth = (String)fylist.get(k+fylist.indexOf("STARTMONTH"));			
			if(startmonth=="0"||startmonth.equals("0")||startmonth.equals("null")){
		      startmonth="";
		    }else{
		    	startmonth = startmonth != null ? startmonth : "";
		    }
		    
		    endmonth = (String)fylist.get(k+fylist.indexOf("ENDMONTH"));			
			if(endmonth=="0"||endmonth.equals("0")||endmonth.equals("null")){
		      endmonth="";
		    }else{
		    	endmonth = endmonth != null ? endmonth : "";
		    }
		    
		   	bzmonth = (String)fylist.get(k+fylist.indexOf("ACCOUNTMONTH"));			
			if(bzmonth=="0"||bzmonth.equals("0")||bzmonth.equals("null")){
		      bzmonth="";
		    }else{
		    	bzmonth = bzmonth != null ? bzmonth : "";
		    }
		    String color="";
			if(intnum%2==0){
			    color="#FFFFFF";
			 }else{
			    color="#DDDDDD";
			}
           intnum++;

       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		<td>
       			<div align="center" ><%=countxh++%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="left" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center" ><%=startmonth%></div>
       		</td>
       		<td>
       			<div align="center" ><%=endmonth%></div>
       		</td>
       		<td>
       			<div align="center" ><%=bzmonth%></div>
       		</td>
       		<td>
       			<div align="right" ><%=money%></div>
       		</td>
       		<td>
       			<div align="center" ><%=entrytime%></div>
       		</td>
       		<td>
       			<div align="center" ><%=entrypensonnel%></div>
       		</td>
       		<td>
       			<div align="left" ><%=zdid%></div>
       		</td>
       		<td>
       			<div align="left" ><%=prepaymentammeterid%></div>
       		</td>
       		
       </tr>
       <%} %>						
       <%}}%>
  	 </table>   	 

    </td>
  </tr>
</table>
</form>
</body>
</html>
