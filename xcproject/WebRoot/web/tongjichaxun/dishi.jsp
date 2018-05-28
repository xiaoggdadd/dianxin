<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean,com.noki.mobi.common.zdbzbean,com.noki.tongjichaxu.Jzhdl" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*" %>

<%
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    
  //  String bzw=request.getParameter("bzw");
   // System.out.println("bzw"+bzw);
	
	String shi = request.getParameter("shi");
	String xian = request.getParameter("xian");
	String zdname=request.getParameter("zdname");
	String qsyf=request.getParameter("qsyf");
	String jsyf=request.getParameter("jsyf");
	String jzlx=request.getParameter("jzlx");
	String name=request.getParameter("zdname");
    String gsf=request.getParameter("gsf");
    String gdfs=request.getParameter("gdfs");	
	
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
   // System.out.println("yy555555:"+yy+" bl:");
    
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
bttcn{color:white;font-weight:bold;font-size:14px}
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
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px    
}; 
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>

<script >

var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();


var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init();
</script>
<script language="javascript">
var path = '<%=path%>';   
    function modifyad(id,dbid){
    	var b=path+"/web/tongjichaxun/collectQueryright1.jsp?id="+id+"&dbid="+dbid+"&";			
			b = b.substring(0,b.length-1);
			 var a = " <a href="+b+" target='treeNodeInfo' id='tmpTree'></a>";
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
  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
     <tr height = "20" class="relativeTag">
      <td width="2%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">序号</div></td>
         <td width="4%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">区县</div></td>     
         <td width="4%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">年月</div></td>
         <td width="4%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">金额</div></td> 
         <td width="4%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">耗电量</div></td>                     
       <!--   <td width="5%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">载频</div></td>
         <td width="5%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">载扇</div></td>
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">标准电量(度/天)</div></td>
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">超标电表个数</div></td>
    -->
     </tr>
     
       <%
       if(null !=shi&& !shi.equals("")&&!shi.equals("0")){
       	                  String whereStr = "";
                     String str="",str1="",str2="",month="";
                     
              		if(null !=shi  && !shi.equals("") && !shi.equals("0")){
              	whereStr=whereStr+" and zd.shi='"+shi+"'";
              		}
              		if(null != xian && !xian.equals("") && !xian.equals("0")){
              	
              	whereStr=whereStr+" and zd.xian='"+xian+"'";
              		}
              		if(null != zdname && !zdname.equals("") && !zdname.equals("0")){
              	
              	whereStr=whereStr+" and zd.zdname like '%"+zdname+"%'";
              		}			
              		if(null != qsyf && !qsyf.equals("") && !qsyf.equals("0")){
              	
              	whereStr=whereStr+" and to_char(DW.STARTMONTH,'yyyy-mm') >='"+qsyf+"'";
              		}
              			if(null != jsyf && !jsyf.equals("") && !jsyf.equals("0")){
              	
              	whereStr=whereStr+" and to_char(DW.ENDMONTH,'yyyy-mm') <='"+jsyf+"'";
              		}
              				if(null != gsf && !gsf.equals("") && !gsf.equals("0")){
              	
              	whereStr=whereStr+" and ZD.GSF= '"+gsf+"'";
              		}
              					if(null != gdfs && !gdfs.equals("") && !gdfs.equals("-1")){
              	
              	whereStr=whereStr+" and ZD.GDFS= '"+gdfs+"'";
              		}
              		
              		System.out.println("whereStr"+whereStr);
                     Jzhdl  bean = new Jzhdl();
                     	List<zdbzbean> fylist = null;
                     	
                     	fylist=bean.getPageDatapapp(whereStr);
                     	System.out.println("ALLLLLLL"+allpage);
              		String jzname = "",actualdegree="",ammeterid_fk="",actualpay="",zdcode="",dbid="",dl="",time="",zdid="",dbida="";
              		int intnum=xh = pagesize*(curpage-1)+1;
              		int linenum=0;
              		int zs,zp;
              		 DecimalFormat mat=new DecimalFormat("0.00");
              		String g2="",g3="";
              		String cj="",sb="",jz="",js="",gl="";
              		String shi1="",xian1="",xiaoqu="",bl="",money="",hdl="";
              		String jzname1="",jzid="",xian2="";
              		double df=0.0,bd=0.0;
              		if(fylist!=null){
              		for(zdbzbean bean1:fylist)
              		{
              	     xian1=bean1.getXian();
                     month=bean1.getMonth();
                     money=bean1.getMoney();
                     hdl=bean1.getHdl();
                     if("".equals(money)||null==money){money="0";}
                     else{
                     money=mat.format(Double.parseDouble(money));
                     
                     }
                      if("".equals(hdl)||null==hdl){hdl="0";}
                     else{
                     hdl=mat.format(Double.parseDouble(hdl));
                     
                     }
              	String color=null;
              	if(intnum%2==0){
              	    color="#DDDDDD";

              	 }else{
              	    color="#FFFFFF" ;
              	}
                        intnum++;
                        String hStr =jzid+";";
                        
       %>
       
       <tr bgcolor="<%=color%>" class="form_label">
       <td>
       			<div align="center"><%=intnum-1%></div>
       		</td>
       		<td>
       			<div align="center"><%=xian1%></div>
       		</td>
       		<td>
       			<div align="center"><%=month%></div>
       		</td>
       		<td>
       			<div align="right"><%=money%></div>
       		</td>
       		<td>
       			<div align="right"><%=hdl%></div>
       		</td>
       		<!--  <td>
       		<div align="center"><a href="huanbi_hdl_city.jsp?dataStr=<%=hStr%>" target="chart"><%=jzname1%></a></div>
       		</td>
       		
       		 <td>
       		
       			<div align="center"><a href="javascript:modifyad('<%=jzid%>','<%=dbid%>')" /><%=jzname1%></div>
       		</td>
       		-->
       </tr>
				<%}}}%>
				
  	 </table> 										
							
</form>
</body>
</html>
<script type="text/javascript">

function changeCity(){
	var sid = document.form1.shi.value;
	document.form1.shi2.value=document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;s
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function changeCountry(){
	var sid = document.form1.xian.value;
	document.form1.xian2.value=document.form1.xian.value;
	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}


</script>
<script language="javascript">
var path = '<%=path%>';   
    function modifyad(id,dbid){
    	var b=path+"/web/tongjichaxun/collectQueryright1.jsp?id="+id+"&dbid="+dbid+"&";			
			b = b.substring(0,b.length-1);
			 var a = " <a href="+b+" target='treeNodeInfo' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
    } 
</script>


