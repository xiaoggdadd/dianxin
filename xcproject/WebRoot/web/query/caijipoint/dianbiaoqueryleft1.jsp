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
String dbcm = request.getParameter("dbmc")!=null?request.getParameter("dbmc"):"";
String zdcode=request.getParameter("zdcode");
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
    
    <title>My JSP 'dianbiaoqueryright.jsp' starting page</title>   
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
bttcn{color:white;font-weight:bold;}
 .form    {width:130px}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style> 
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script language="javascript">
var path = '<%=path%>';    
    function modifyad(ammeterid_fk,startmonth,endmonth){
    	var b=path+"/web/query/caijipoint/dianbiaoqueryright.jsp?ammeterid_fk="+ammeterid_fk+"&startmonth="+startmonth+"&endmonth="+endmonth+"&";			
			b = b.substring(0,b.length-1);
			 var a = " <a href="+b+" target='treeNodeInfo' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
		var c=path+"/web/query/caijipoint/table1.jsp?ammeterid_fk="+ammeterid_fk+"&startmonth="+startmonth+"&endmonth="+endmonth+"&";			
			c = c.substring(0,c.length-1);
			 var a = " <a href="+c+" target='treeNodeInfo1' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click(); 
    } 
</script>
  </head> 
  <body class="body" style="overflow-x:hidden;">
  <table border="0" cellspacing="1" cellpadding="1">
   
     <tr height = "20" class= "relativeTag ">
         <td width="10%" height="23" bgcolor="#DDDDDD"  class="form_label"><div align="center" style="color:black;font-weight:bold">电表名称</div></td>         
         <td width="10%" height="23" bgcolor="#DDDDDD"  class="form_label"><div align="center" style="color:black;font-weight:bold">电表编号</div></td>
         <td width="10%" height="23" bgcolor="#DDDDDD"  class="form_label"><div align="center" style="color:black;font-weight:bold">用电设备</div></td>        
         <td width="10%" height="23" bgcolor="#DDDDDD"  class="form_label"><div align="center" style="color:black;font-weight:bold">电流类型</div></td>
     </tr>
      <%
      String whereStr = "";
      if(null != startmonth  &&!startmonth.equals("") && !startmonth.equals("0")){
			whereStr=whereStr+" and to_char(a.thisdatetime,'yyyy-mm-dd'>='"+startmonth+"'";
		}
		if(null != endmonth  && !endmonth.equals("") && !endmonth.equals("0")){
			whereStr=whereStr+" and to_char(a.lastdatetime,'yyyy-mm-dd')<='"+endmonth+"'";
		}if(null != dbcm  && !dbcm.equals("") && !dbcm.equals("0")){
			whereStr=whereStr+" and db.dbname='"+dbcm+"'";
		}
     
     
      haodianliangBean bean = new haodianliangBean();
     	ArrayList fylist = new ArrayList();

     	fylist = bean.getHaodianliang1(curpage,whereStr,zdcode);
     	allpage=bean.getAllPage();
     	System.out.println("ALLLLLLL"+allpage);
		String last = "",thisd = "",dbid="",ydsb="",dbname="",dllx="";
		int intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		double df=0.0;
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
		     
		    dbname = (String)fylist.get(k+fylist.indexOf("DBNAME"));	
		    dbname = dbname != null ? dbname : "";
		    
		    dbid = (String)fylist.get(k+fylist.indexOf("DBID"));	
		    dbid = dbid != null ? dbid : "";
		    
		    ydsb = (String)fylist.get(k+fylist.indexOf("YDSB"));	
		    ydsb = ydsb != null ? ydsb : "";
		    
		    dllx = (String)fylist.get(k+fylist.indexOf("DLLX"));	
		    dllx = dllx != null ? dllx : "";
		    
			String color=null;
          
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
        intnum++;

     %>
        <tr bgcolor="<%=color%>">
            <td><div align="left"  class="form_label"><a href="javascript:modifyad('<%=dbid%>','<%=startmonth%>','<%=endmonth%>')" ><%=dbid%></div></td>
            <td><div align="center"  class="form_label"><%=dbname %></div></td> 
            <td><div align="center"  class="form_label"><%=ydsb %></div></td>
            <td><div align="center"  class="form_label"><%=dllx %></div></td>      
            <input type="hidden" name="startmonth" value=<%=startmonth %>/>
            <input type="hidden" name="endmonth" value=<%=endmonth %>/>
                  
        </tr>   
        <% }
        }%>     
      
   </table>
  </body>
</html>
