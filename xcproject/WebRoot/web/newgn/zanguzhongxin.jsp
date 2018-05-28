<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.function.*" %>
<%@ page import="java.text.*,java.util.regex.Pattern"%>
<%@ page import="com.noki.electricfees.javabean.*"%>


<%
String path = request.getContextPath();
//String whereStr = request.getParameter("whereStr")!=null?request.getParameter("whereStr"):"";
String Wstr = request.getParameter("Wstr")!=null?request.getParameter("Wstr"):"";
String lrsj = request.getParameter("lrsj")!=null?request.getParameter("lrsj"):"";
String zgjssj = request.getParameter("zgjssj")!=null?request.getParameter("zgjssj"):"";
Account account = (Account)session.getAttribute("account");
String loginId  = account.getAccountId();
String color="";
int intnum =0;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<style type="text/css">
.style1 {
	color: red;
	font-weight: bold;
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
}
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
.bttcn{color:black;font-weight:bold;}
</style>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript">
var path='<%=path%>';
 function fanhui(){
	 window.close();
       //document.form1.action=path+"/web/newgn/zangu.jsp";
      //  document.form1.submit();
 }
  function exportad(){	  
        var Wstr ="<%=Wstr%>"; 
       var lrsj ="<%=lrsj%>";
       var zgjssj="<%=zgjssj%>";
     //alert(whereStr+"Wstr:"+Wstr+"Str:"+Str+"endzgsj"+endzgsj);
       document.form1.action=path+"/web/query/basequery/暂估电费分摊信息.jsp?zgjssj="+zgjssj+"&Wstr="+Wstr+"&lrsj="+lrsj+"&command=daochu";         
       document.form1.submit();
}
	   
 $(function(){
	$("#cancelBtn").click(function(){
		fanhui();
	});	
	$("#daochuBtn").click(function(){
	    exportad();
	});
});
		

</script>
 </head>
 <body>
   <form action="" name="form1" method="POST">
	<table border="0" >	
	    <tr>
			<td colspan="8" width="50" ><div style="width:700px;height:50px"><img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:30px;color:black">电费分摊信息</span></div>
	        </td>
        </tr>
        <tr><td height="5"  colspan="4"  class="form_label">
	         <div id="parent" style="display:inline"></div>
	         <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
            </td>
       </tr>
    </table>
<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 	
    
 <table width="1000" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">        
        <tr class="relativeTag">
           <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">成本中心</div></td>	
           <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">会计科目</div></td>	
           <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">专业明细</div></td>	               
           <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">全成本</div></td>	             
           <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">分摊费用</div></td>	  
       </tr>
        <% 
            String Str="";
            if(lrsj != null && !lrsj.equals("")){
			    Wstr=Wstr+" AND G.SAVETIME LIKE '"+lrsj+"%'";
				Str=Str+" AND G.SAVETIME LIKE '"+lrsj+"%'";//录入时间  
				
			}
			if(zgjssj != null && !zgjssj.equals("")){
			    Wstr=Wstr+" AND G.ZGMONTH='"+zgjssj+"'";//
				Str=Str+" AND G.ZGMONTH='"+zgjssj+"'";//暂估结束月份
			}
         String shi="";
    	 String xian="";
    	 String qcb="";
    	 String kjkm="";
    	 String zymx="";
    	 String sszy="";
    	 String ftfy="",heji="";
    	 double hj=0;
    	DecimalFormat mat =new DecimalFormat("0.00");
  	    ElectricFeesBean bean = new ElectricFeesBean();
  	    
        List<DianfeidandayinBean> listt=bean.getFentan1(Wstr,Str,loginId);
        for(DianfeidandayinBean ftbean:listt){
        	shi=ftbean.getShi();
        	xian=ftbean.getXian();
        	ftfy=ftbean.getNETWORKDF();
            if(null==ftfy||ftfy.equals("")||ftfy.equals(" "))ftfy="0";
            ftfy=mat.format(Double.parseDouble(ftfy));//小数位影响数据
       
            qcb=ftbean.getQcb();
            kjkm=ftbean.getKjkm();
            zymx=ftbean.getZymx();
            sszy=ftbean.getSszy();

            if(null==qcb||qcb.equals("")||qcb.equals(" "))qcb="";
            if(null==kjkm||kjkm.equals("")||kjkm.equals(" "))kjkm="";
            if(null==zymx||zymx.equals("")||zymx.equals(" "))zymx="";
           
            
            if(intnum%2==0){
			    color="#FFFFFF" ;

			 }else{
			    color="#DDDDDD";
			}
           intnum++; 
    	 
 	  %>
 	   <tr bgcolor="<%=color%>">
            <td>
       		  <div align="center" ><%=shi+xian%></div>
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
            
       </tr>
       <%
       if(intnum%2==0){
		    color="#FFFFFF" ;

		 }else{
		    color="#DDDDDD";
		}
       hj+=Double.parseDouble(ftfy);
        }
        heji=mat.format(hj);
       %>
       <tr bgcolor="<%=color%>">
         <td><div align="center" >合计</div></td>
         <td colspan="3"><div >&nbsp;</div></td>
         <td ><div align="center" ><%=heji%></div></td>                      
       </tr>
   </table>
   </div>
   <table width="100%">
       <tr>
       	 <td >
       	    <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:20px">
	           <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
            </div>
            <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:31px; " >
		      <img src="<%=path %>/images/daoru.png" width="100%" height="100%">
			  <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
		    </div>
        </td>
       </tr>
</table>

 </form>           
</body>
</html>
