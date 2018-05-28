<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%
String path = request.getContextPath();

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String startmonth = request.getParameter("startmonth")!=null?request.getParameter("startmonth"):"";
String endmonth = request.getParameter("endmonth")!=null?request.getParameter("endmonth"):"";
System.out.println(startmonth+"时间");
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
curpage=Integer.parseInt(s_curpage);
String permissionRights="";
String roleId = (String)session.getAttribute("accountRole");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'haodianliangleft.jsp' starting page</title>   
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

  <table border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
   
     <tr height = "20" class= "relativeTag">
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">起始时间</div></td>
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">结束时间</div></td>        
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">耗电量</div></td>
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">额定耗电量</div></td>
     </tr>
      <%
      String whereStr = "";
      if(null != startmonth  &&!startmonth.equals("") && !startmonth.equals("0")){
			whereStr=whereStr+" and to_char(a.thisdatetime,'yyyy-mm-dd')>='"+startmonth+"'";
		}
		if(null != endmonth  && !endmonth.equals("") && !endmonth.equals("0")){
			whereStr=whereStr+" and to_char(a.lastdatetime,'yyyy-mm-dd')<='"+endmonth+"'";
		}
      String zdid=request.getParameter("zdcode");
      System.out.println(zdid+"id====");
      haodianliangBean bean = new haodianliangBean();
     	ArrayList fylist = new ArrayList();

     	fylist = bean.getCollectQuery(curpage,whereStr,zdid);
     	allpage=bean.getAllPage();
     	System.out.println("ALLLLLLL"+allpage);
		String last = "",thisd = "",actualdegree="",actualpay="",llhdl="";
		int intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		double df=0.0;
		double df1=0.0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
           linenum++;
		     //num为序号，不同页中序号是连续的		    	
		    last = (String)fylist.get(k+fylist.indexOf("LASTDATETIME"));	
		    last = last != null ? last : "";
		    
		    thisd = (String)fylist.get(k+fylist.indexOf("THISDATETIME"));	
		    thisd = thisd != null ? thisd : "";
		     
		    actualdegree = (String)fylist.get(k+fylist.indexOf("ACTUALDEGREE"));	
		    actualdegree = actualdegree != null ? actualdegree : "";
		    
		   	llhdl = (String)fylist.get(k+fylist.indexOf("LLHDL"));	
		    llhdl = llhdl != null ? llhdl : "";
		    
	        DecimalFormat mat=new DecimalFormat("0.00");
	        if(actualdegree==null||actualdegree==""||"".equals(actualdegree)||"null".equals(actualdegree)){
			   actualpay="0.00";
			}else{
		        df=Double.parseDouble(actualdegree);
		        actualpay=mat.format(df);
			}
	        
	       if(llhdl==null||llhdl==""||"".equals(llhdl)||"null".equals(llhdl)){
			   llhdl="";
			}else{
		       	df1=Double.parseDouble(llhdl);
		        llhdl=mat.format(df1);
			}

	        
	        String color=null;          
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
        intnum++;

     %>
        <tr bgcolor="<%=color%>">
         
            <td><div align="center" style="font-size:14px"><%=last %></div></td> 
            <td><div align="center" style="font-size:14px"><%=thisd %></div></td>
            <td><div align="center" style="font-size:14px"><a href="javascript:dfinfo1('<%=zdid %>','<%=last %>')"><%=actualdegree %></a></div></td>      
            <td><div align="center" style="font-size:14px"><%=llhdl %></div></td>        
        </tr>   
        <% }
        }%>     
      
   </table>
  </body>
</html>
<script type="text/javascript">
var path = '<%=path%>';
function dfinfo1(zdid,last){
    var url=path+"/web/query/caijipoint/collectQuerytan.jsp?zdid="+zdid+"&last="+last;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}
</script>
