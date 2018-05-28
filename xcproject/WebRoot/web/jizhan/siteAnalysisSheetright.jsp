<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.noki.jizhan.SiteAnalysisSheet" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*" %>

<%
	
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    
    String xlx=request.getParameter("code")!=null?request.getParameter("code"):"";
    String zdsx=request.getParameter("zdsx")!=null?request.getParameter("zdsx"):"";
    String shi=request.getParameter("shi")!=null?request.getParameter("shi"):"";
    String xian=request.getParameter("xian")!=null?request.getParameter("xian"):"";
    
    String gsf = request.getParameter("gsf")!=null?request.getParameter("gsf"):"";
String dfzflx=request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"";
 String bgsign = request.getParameter("bgsign")!=null?request.getParameter("bgsign"):"";
 String caiji=request.getParameter("caiji")!=null?request.getParameter("caiji"):"";
    
    
     
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    //System.out.println("roleId:"+roleId);
%>

<html>
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
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
.bttcn{color:BLACK;font-weight:bold;}
 .form    {width:130px}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script language="javascript">
 var path='<%=path%>';
/**function modifyad(code){
   var b=path+"/web/jizhan/siteAnalysisSheetright1.jsp?code="+code;	
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(b,obj,'dialogWidth:1000px;dialogHeight:500px;status:auto;scroll:auto');	
}*/


   function modifyad(code){
   
    	var b=path+"/web/jizhan/siteAnalysisSheetright1.jsp?code="+code;			
		
			 var a = " <a href="+b+" target='treeInfo' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
    } 
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0301");

System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<body  class="body" >
	
	<form action="" name="form1" method="POST">
  	
  <table width="100%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">

     <tr height = "20" class="relativeTag">
     <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
            
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
         <td width="15%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
         <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属地区</div></td>     
      <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点编号</div></td> 
     </tr>
       <%
       String str="",whereStr="";
       if(zdsx.equals("zdsx01")){
    	   str="  z.property = 'zdsx01'  and z.jflx = '"+xlx+"' ";
       }else if(zdsx.equals("zdsx02")){
    	   str="  z.property = 'zdsx02'  and z.xlx = '"+xlx+"' "; 
       }else if(zdsx.equals("zdsx05")){
    	   str="  z.property = 'zdsx05'  and z.jrwtype = '"+xlx+"' "; 
       }
       if(null !=shi  && !shi.equals("") && !shi.equals("-1")){
			whereStr=whereStr+"and z.shi='"+shi+"'";
		}
		if(null != xian && !xian.equals("") && !xian.equals("-1")){
			
			whereStr=whereStr+"and z.xian='"+xian+"'";
		}if(null != gsf && !gsf.equals("") && !gsf.equals("0")){
				
				whereStr=whereStr+"and z.gsf='"+gsf+"'";
			}
			if(null != bgsign && !bgsign.equals("") && !bgsign.equals("-1")){
				
				whereStr=whereStr+"and z.bgsign='"+bgsign+"'";
			}
			if(null != caiji && !caiji.equals("") && !caiji.equals("-1")){
				
				whereStr=whereStr+"and z.caiji='"+caiji+"'";
			}
			if(null != dfzflx && !dfzflx.equals("") && !dfzflx.equals("0")){
				
				whereStr=whereStr+"and D.DFZFLX='"+dfzflx+"'";
			}	
       
		System.out.println("--*--*"+str);
        SiteAnalysisSheet bean=new SiteAnalysisSheet();
        
       	ArrayList fylist = new ArrayList();
       	if(null !=zdsx  && !zdsx.equals("") && !zdsx.equals("-1")){
       		
       	
       	fylist = bean.getCountinformation(whereStr,loginId,str);
       
		String zdname="",szdq="",code="",stationtype="";
		int intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		double df=0.0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
             linenum++;
		     //num为序号，不同页中序号是连续的
		    zdname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    zdname = zdname != null ? zdname : "";
		    
		    szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));	
		    szdq = szdq != null ? szdq : "";
		     stationtype = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));	
		    stationtype = stationtype != null ? stationtype : "";
		    
		    code = (String)fylist.get(k+fylist.indexOf("ZDCODE"));	
		    code = code != null ? code : "";
		    
			String color=null;
            
		    
		  
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
          intnum++;

       %>
       <tr bgcolor="<%=color%>">
       <td>
       			<div align="center" ><%=intnum-1%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><a href="javascript:modifyad('<%=code%>')" ><%=zdname%></a></div>
       		</td>
       		<td>
       			<div align="center" ><%=stationtype%></div>
       		</td>
       		<td>
       			<div align="left" ><%=szdq%></div>
       		</td>
           <td>
       			<div align="left" ><%=code%></div>
       		</td>
       </tr>
       <%}%>
				<% }}%>
				
  	 </table> 										
							
</form>
</body>
</html>
