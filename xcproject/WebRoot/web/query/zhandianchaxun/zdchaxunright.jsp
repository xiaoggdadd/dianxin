<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.noki.zhandian.*" %>
<%
String path = request.getContextPath();
String ammeterid= request.getParameter("ammeterid");
System.out.println(ammeterid);


String time = request.getParameter("time");
String amid=request.getParameter("amid");
String bzw=request.getParameter("bzw")!=null?request.getParameter("bzw"):"";
System.out.println("ammeterids1111111111"+ammeterid+"time222222"+time+"bzw"+bzw);
if(bzw.equals("1")){System.out.print("2222");}
/*String time = request.getParameter("");
Syste
String startmonth = request.getParameter("startmonth")!=null?request.getParameter("startmonth"):"";
String endmonth = request.getParameter("endmonth")!=null?request.getParameter("endmonth"):"";
System.out.println(startmonth+"时间");
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
curpage=Integer.parseInt(s_curpage);*/
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String permissionRights="";
String roleId = (String)session.getAttribute("accountRole");

DecimalFormat mat=new DecimalFormat("0.00");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '' starting page</title>   
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
bttcn{color:white;font-weight:bold;}
 .form    {width:130px}
 .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}; 
 </style> 
  </head> 
  <body class="body" style="overflow-x:hidden;">

  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
 
     <tr height = "20" class= "relativeTag">
     	<!-- <td width="1%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">div align="center" > -->
         <td width="14%" height="23" bgcolor="#DDDDDD"><div align="center" >缴费日期</div></td>
         <td width="15%" height="23" bgcolor="#DDDDDD"><div align="center" >本期起始日期</div></td>        
         <td width="15%" height="23" bgcolor="#DDDDDD"><div align="center" >本月结束日期</div></td>
         <td width="13%" height="23" bgcolor="#DDDDDD"><div align="center" >起始电表数</div></td>
         <td width="13%" height="23" bgcolor="#DDDDDD"><div align="center" >终止电表数</div></td>
         <td width="13%" height="23" bgcolor="#DDDDDD"><div align="center" >本期用电量</div></td>
         <td width="17%" height="23" bgcolor="#DDDDDD"><div align="center" >本期用电金额</div></td>
     </tr>
   
     
      <%
         if(bzw.equals("1")){ 
     		String whereStr = "";
		if (ammeterid != null && !ammeterid.equals("") && !ammeterid.equals("0")) {
			whereStr = whereStr + " and zd.id='" + ammeterid + "'";
		}
		if (time != null && !time.equals("") && !time.equals("0")) {
			whereStr = whereStr + " and to_char(el.accountmonth,'yyyy-mm')='" + time + "'";
		}
		if (amid != null && !amid.equals("") && !amid.equals("0")) {
			whereStr = whereStr + " and am.ammeterdegreeid='" + amid + "'";
		}
	      ZhanDianDao dao = new ZhanDianDao();
	      List<FeiyongBean> list = dao.getFYXX(whereStr);
	      System.out.println(list);
	      int intnum=0;
	      String times="";
	      String begin="";
	      String end="";
	      double begins=0.0;
	      double ends=0.0;
	      double dians=0.0;
	      double money=0.0;
      	if(list!=null){
     		for(FeiyongBean f:list){
     			times=f.getTime();
     			System.out.println(times);
		     	 begin=f.getBegin();
		      	System.out.println(begin);
		     	 end = f.getEnd();
		        begins= f.getBegins();
		        ends = f.getEnds();
		        dians =f.getDians();
		        money = f.getJinez();
		        if("null".equals(times)||times==null){
		        times="";
		        }
		        if("null".equals(begin)||begin==null){
		        begin="";
		        }
		        if("null".equals(end)||end==null){
		        end="";
		        }
	            String color=null;          
			if(intnum%2==0){
			    color="#FFFFFF";

			 }else{
			    color="#DDDDDD" ;
			}
       			 intnum++;
     %>
        <tr bgcolor="<%=color%>">
         
            <td height="23"><div align="right" style="font-size:14px"><%=times %></div></td> 
            <td height="23"><div align="right" style="font-size:14px"><%=begin %></div></td>
            <td height="23"><div align="right" style="font-size:14px"><%=end %></div></td>      
            <td height="23"><div align="right" style="font-size:14px"><%=mat.format(begins) %></div></td>
            <td height="23"><div align="right" style="font-size:14px"><%=mat.format(ends) %></div></td>
            <td height="23"><div align="right" style="font-size:14px"><%=mat.format(dians) %></div></td>
            <td height="23"><div align="right" style="font-size:14px"><%=mat.format(money) %></div></td>        
        </tr>   
        <% }}}%>     
   </table>
  </body>
</html>

