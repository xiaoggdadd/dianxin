<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.ptac.app.calibstat.daterepeat.dao.DateRepeatImpl,com.ptac.app.calibstat.daterepeat.bean.DateRepeatBean" %>

<html>
<% 
		String zdid = request.getParameter("zdid");
		String whereStr = request.getParameter("whereStr");
		String month = request.getParameter("month");
		String bzw = request.getParameter("bzw");
		
		String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
		int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
		curpage=Integer.parseInt(s_curpage);
%>
<head>
<title>
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

<body  class="body">
	<table>
		<tr><td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;电费单详单&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
	</table>  	
  <table width="100%" height="400px"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
         	<tr height="23" class="relativeTag ">
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始日期</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束日期</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始码</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束码</div></td> 
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电量</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">调整电量</div></td> 
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">单价</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费</div></td>           
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">调整电费</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入员</div></td>
  			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入日期</div></td>
  			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">审核员</div></td> 
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">审核日期</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务审批员</div></td>  
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务审批日期</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账单号</div></td>
        </tr>
        <% 
       				String sql1 = "";
			        if(zdid != null && !zdid.equals("") && !zdid.equals("0")){
						sql1 = sql1+" AND t.ZDID ='" + zdid + "'";
					}
					//if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
					//	sql1 = sql1+" AND Z.JZNAME LIKE '%" + zdname + "%'";
					//}
	             	DateRepeatImpl  bean = new DateRepeatImpl();
	    			List<DateRepeatBean> list = null;
	    			list = bean.getXiangXi(sql1,whereStr,month,bzw);
	            	
	                String beginTime="",endTime="",beginCode="",endCode="",dl="",tzdl="",danjia="";
	                String df="",tzdf="",lry="",lrtime="",shy="",shtime="",spy="",sptime="",bzdh="";
            		double numCount=0.0,dayCount=0.0;
            		int intnum=xh = pagesize*(curpage-1)+1;
            		int linenum=0;
            	if(list!=null){	
            		for(DateRepeatBean bean1:list){
            			beginTime = bean1.getBeginTime();
            			endTime = bean1.getEndTime();
            			beginCode = bean1.getBeginCode();
            			endCode = bean1.getEndCode();
            			dl = bean1.getDl();
            			tzdl = bean1.getTzdl();
            			danjia = bean1.getDanjia();
            			df = bean1.getDf();
						tzdf = bean1.getTzdf();
						lry = bean1.getLry();
						lrtime = bean1.getLrtime();
						shy = bean1.getShy();
						shtime = bean1.getShtime();
						spy = bean1.getSpy();
						sptime = bean1.getSptime();
						bzdh = bean1.getBzdh();
						
            	        String color=null;       
            	    
	            	if(intnum%2==0){
	            	    color="#DDDDDD";
	            	 }else{
	            	    color="#FFFFFF" ;
	            	}
                    intnum++;
      %>
      <tr bgcolor="<%=color%>" class="form_label">
          <td><div align="center" ><%=beginTime%></div></td> 
          <td><div align="center" ><%=endTime%></div></td> 
          <td><div align="center" ><%=beginCode%></div></td>
          <td><div align="center"><%=endCode%></div></td>
          <td><div align="right" ><%=dl%></div></td>
          <td><div align="center" ><%=tzdl%></div></td> 
          <td><div align="center" ><%=danjia%></div></td> 
          <td><div align="center" ><%=df%></div></td>
          <td><div align="center"><%=tzdf%></div></td>
          <td><div align="right" ><%=lry%></div></td>
          <td><div align="center" ><%=lrtime%></div></td> 
          <td><div align="center" ><%=shy%></div></td> 
          <td><div align="center" ><%=shtime%></div></td>
          <td><div align="center"><%=spy%></div></td>
          <td><div align="right" ><%=sptime%></div></td>
          <td><div align="right" ><%=bzdh%></div></td>
      </tr>   
     <%}}%> 
   </table>
</body>
</html>
