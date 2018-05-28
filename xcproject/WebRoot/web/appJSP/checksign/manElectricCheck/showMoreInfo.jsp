<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<% 
		int intnum = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;//条数 ,查出数据的条数，用于颜色设置
		String color="";
%>
<head>
<title>详细电费信息
</title>
<style>
            .btn {
             BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7b9ebd 1px solid
            }
            .btn1_mouseout {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#B3D997); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn1_mouseover {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#CAE4B6); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn2 {padding: 2 4 0
            4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;}
            .btn3_mouseout {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mouseover {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mousedown
            {
             BORDER-RIGHT: #FFE400 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #FFE400 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #FFE400
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #FFE400 1px solid
            }
            .btn3_mouseup {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn_2k3 {
             BORDER-RIGHT: #002D96 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #002D96 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); BORDER-LEFT: #002D96
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #002D96 1px solid
            }
            .style1 {
	color: #FF9900;
	font-weight: bold;
}
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
			height:23px
}

.form {width:130px}
.bttcn{color:black;font-weight:bold;}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
</head>
<jsp:useBean id="bean" scope="page" class="com.noki.electricfees.javabean.ElectricFeesBean">
</jsp:useBean>
<body  class="body">
	<table>
		<tr><td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;历史信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
	</table>  	
  <table width="2800px" height="400px"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
         	<tr height = "23" class="relativeTag ">
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>
            <td width="2.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td> 
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际用电量</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际电费</div></td>           
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始电表数</div></td>
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束电表数</div></td>
  			<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td> 
            <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电量调整</div></td>  
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电量调整备注</div></td>  
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费调整</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费调整备注</div></td>
            <td width="2.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属地区</div></td>
        </tr>
     <c:forEach items="${list}"  var="list" varStatus="status">
		<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       		<td>
       			<div align="center" >${list.zdid}</div>
       		</td>
       		<td>
				<div align="left">${list.jzname}</div>
			</td>
       		<td>
       			<div align="left" >${list.dbname}</div>
       		</td>   		
        	<td>
       			<div align="right" >${list.accountmonth}</div>
       		</td>
       		<td>
				<div align="right">${list.blhdl}</div>
			</td>
			<td>
				<div align="right">${list.actualpay}</div>
			</td>
			<td>
       			<div align="center" >${list.lastdegree}</div>
       		</td>
       		  	<td>
       			<div align="center" >${list.thisdegree}</div>
       		</td>  
       		<td>
       			<div align="center" >${list.lastdatetime}</div>
       		</td>
       		  	<td>
       			<div align="center" >${list.thisdatetime}</div>
       		</td>   	
       		<td>
       			<div align="right" >${list.floatdegree}</div>
       		</td>   
       		<td>
       			<div align="right" >${list.ammemo}</div>
       		</td> 
       		<td>
       			<div align="right" >${list.floatpay}</div>
       		</td>   
       		<td>
       			<div align="right" >${list.efmemo}</div>
       		</td>
       		<td>
       			<div align="right" >${list.ssdq}</div>
       		</td>    
    	</tr>
    </c:forEach>
       
        <% if (intnum==0){
	         for(int i=0;i<5;i++){
	          if(i%2==0){
				    color="#FFFFFF";
	          }else{
				    color="#DDDDDD";
				}
	         %>
	
	        <tr bgcolor="<%=color%>">
	             <td>&nbsp;</td>  
	             <td>&nbsp;</td> 
	             <td>&nbsp;</td>  
	             <td>&nbsp;</td> 
	             <td>&nbsp;</td> 
	             
	             <td>&nbsp;</td> 
	             <td>&nbsp;</td>  
	             <td>&nbsp;</td> 
	             <td>&nbsp;</td>                   
	             <td>&nbsp;</td>
	             
	             <td>&nbsp;</td>
	             <td>&nbsp;</td>
	             <td>&nbsp;</td> 
	             <td>&nbsp;</td>   
	             <td>&nbsp;</td>    
	                            
	        </tr>
	       		 <% }}%>
  </table>

</body>
</html>




