<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@page import="com.noki.electricfees.javabean.*"%>
<%@page import="com.noki.function.*"%>
<%@ page import="java.text.*"%>

<%
	String sheng = (String)session.getAttribute("accountSheng");
	String roleId = (String)session.getAttribute("accountRole");
    String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";
    String str = request.getParameter("str")!=null?request.getParameter("str"):"";


	     
	//String whereStr="";
    String path = request.getContextPath();
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
       
        
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
          
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
 <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
  <head>
   
  </head>
  
  <body>
  <form action="" name="form1" method="POST">
    <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">
       
          <tr class="relativeTag">
            <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">成本中心</div></td>
       		<td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">会计科目</div> </td>
       		<td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">专业明细</div></td>
			<td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">全成本</div></td>   
       		<td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">分摊费用</div></td>          
       	  </tr>
        <% 
        int intnum=xh = pagesize*(curpage-1)+1;
         DanZaiPin bean = new DanZaiPin();
         String shi1="";
    	 String xian="";
    	 String NETWORKDF="";            //网络运营线电费（生产）
    	// String INFORMATIZATIONDF="";     //信息化支撑线电费
    	// String ADMINISTRATIVEDF="";     //行政综合线电费（办公）
    	// String MARKETDF="";             //市场营销线电费（营业）
    	// String BUILDDF="";               //建设投资线电费
    	 String qcb="";
    	 String kjkm="";
    	 String zymx="";
    	 String sszy="";
    	 String ftfy="",heji="";
    	 double hj=0;
    	 DecimalFormat mat =new DecimalFormat("0.00");
        List<DianfeidandayinBean> listt=bean.getFentansq(whereStr,loginId,str);
        for(DianfeidandayinBean ftbean:listt){
        	shi1=ftbean.getShi();
        	xian=ftbean.getXian();
        	ftfy=ftbean.getNETWORKDF();
       
            qcb=ftbean.getQcb();
            kjkm=ftbean.getKjkm();
            zymx=ftbean.getZymx();
            sszy=ftbean.getSszy();

            if(null==qcb||qcb.equals("")||qcb.equals(" "))qcb="";
            if(null==kjkm||kjkm.equals("")||kjkm.equals(" "))kjkm="";
            if(null==zymx||zymx.equals("")||zymx.equals(" "))zymx="";
            if(null==ftfy||ftfy.equals("")||ftfy.equals(" "))ftfy="0";
            
            ftfy=mat.format(Double.parseDouble(ftfy));
            
            String color=null;
       		if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr bgcolor="<%=color%>" class="form_label">
       
         <td>
       		  <div align="center" ><%=shi1+xian%></div>  
       		</td>
       		<td>                                      
       			<div align="center" ><%=kjkm%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zymx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=qcb%></div>
       		</td>   
       		<td>
       			<div align="center" ><%=ftfy%></div>
       		</td>          
            <input type="hidden" name="num" value="<%= intnum++%>"/>
       </tr>
       <%
       hj+=Double.parseDouble(ftfy);
        }
        heji=mat.format(hj);
       %>
       <tr class="form_label">
         <td colspan="2">
       		  <div align="center" >合计</div>
       		</td>
       		
       		<td colspan="3">
       			<div align="center" ><%=heji%></div>
       		</td>          
            
       </tr>
      <tr>
     	<td>
        <div id="daochu" style="position:relative;width:59px;height:23px;cursor: pointer;left:450%;TOP:200PX">
		 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
		 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">导出</span>
		</div>
		 <div id="fanhui1" style="position:relative;width:59px;height:23px;cursor: pointer;left:500%;TOP:172PX">
		 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
		 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">返回</span>
		</div>
		</td>
     
      </tr>
     
  	 </table> 
  	 </form>
  </body>
</html>
<script>
	var path = '<%=path%>';
	$(function(){
		$("#daochu").click(function(){
			daochu1();
		});
		$("#fanhui1").click(function(){
			fanhui();
		
		});
	});
	function fanhui(){
		window.close();
	}
	function daochu1(){
			var whereStr="<%=whereStr%>";
			var str="<%=str%>";
			//alert(whereStr);
			document.form1.action=path+"/web/newgn/市区分摊.jsp?str="+str+"&whereStr="+whereStr;
		    document.form1.submit();
    }
</script>