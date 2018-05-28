<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean,com.noki.mobi.common.AccountJzqa,com.noki.mobi.common.zdbzbeanB,com.noki.tongjichaxu.javabean.dqeletriCount,com.noki.tongjichaxu.javabean.dqbean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*" %>

<%
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
	
	String beginTime = request.getParameter("beginTime");
	String endTime = request.getParameter("endTime");
    String shi = request.getParameter("shi");
    String xian = request.getParameter("xian");
    String zdname = request.getParameter("zdname");
    String zdlx = request.getParameter("zdlx");
    String gdfs = request.getParameter("gdfs");
    String gsf = request.getParameter("gsf");
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    int intnum=0;
%>

<html>
<head>
<title>
</title>
<style>
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
			height:20px

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
    function modifyad(zp,zs,cj,g2,g3,shi,xian,bla,bd,yf,bzmonth,dyt,sjyf){
    if(g3=="是"){g3="1";}
     if(g3=="否"){g3="0";}
      if(g2=="是"){g2="1";}
     if(g2=="否"){g2="0";}
    	var b=path+"/web/query/caijipoint/collectQueryright1.jsp?zp="+zp+"&zs="+zs+"&cj="+cj+"&g2="+g2+"&g3="+g3+"&shi="+shi+"&xian="+xian+"&bl="+bla+"&bd="+bd+"&bl="+bla+"&yf="+yf+"&bzmonth="+bzmonth+"&dyt="+dyt+"&sjyf="+sjyf+"&";			
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
%>
<body  class="body" >
	
<form action="" name="form1" method="POST">
 <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
  <table width="100%" border="0" height="100%"  cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
<tr  class="relativeTag">
<td bgcolor="#DDDDDD" colspan="4"><div align="center" style="font-weight:bold;">地区电费汇总</div></td>
</tr>
     <tr height = "20" class="relativeTag">
     <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">序号</div></td>
     <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">地区</div></td>
     <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">总电费</div></td>
     <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">电费占比</div></td>
     </tr>
     
       <%
       if(null !=beginTime&& !beginTime.equals("")){
       	String whereStr = "";
       	String gstr ="";
       	String qstr ="";
                     String str="";
                     
              		if(null !=shi  && !shi.equals("") && !shi.equals("0")){
              	whereStr=whereStr+" AND ZD.SHI='"+shi+"'";
              	qstr ="|| (CASE WHEN ZD.XIAN IS NOT NULL THEN(SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAN) ELSE '' END)";
              	gstr =", zd.xian";
              		}
              		if(null != xian && !xian.equals("") && !xian.equals("0")){
              	
              	whereStr=whereStr+" AND ZD.XIAN='"+xian+"'";
              	qstr = "|| (CASE  WHEN ZD.XIAOQU IS NOT NULL THEN (SELECT DISTINCT AGNAME FROM D_AREA_GRADE WHERE AGCODE = ZD.XIAOQU) ELSE '' END)";
              	gstr =",zd.xiaoqu";
              		}
              		if(null != zdlx && !zdlx.equals("") && !zdlx.equals("0")&&!zdlx.equals("请选择")){
              	
              	whereStr=whereStr+" AND ZD.STATIONTYPE='"+zdlx+"'";
              		}
              		if(null != gdfs && !gdfs.equals("") && !gdfs.equals("0")&&!gdfs.equals("-1")){
              	
              	whereStr=whereStr+" AND ZD.GDFS='"+gdfs+"'";
              		}
              		if(null != gsf && !gdfs.equals("") && !gsf.equals("0")&&!gsf.equals("-1")){
              	
              	whereStr=whereStr+" AND ZD.gsf='"+gsf+"'";
              		}
              		if(null != beginTime && !beginTime.equals("") && !beginTime.equals("0")){
              	
              	whereStr=whereStr+" AND to_char(DW.STARTMONTH,'yyyy-mm')>='"+beginTime+"'";
              		}
              		if(null != endTime && !endTime.equals("") && !endTime.equals("0")){
              	
              	whereStr=whereStr+" AND to_char(DW.ENDMONTH,'yyyy-mm')<='"+endTime+"'";
              		}		
             
	           
              	System.out.println("---");
                   dqeletriCount  bean = new dqeletriCount();
                   List<dqbean> fylist = null;
                   DecimalFormat pay=new DecimalFormat("0.00");
                   fylist = bean.getShiCount(whereStr,qstr,gstr);
              	   String szdq = "",actry="",shibili="",gdgdj="",gdyz="",gdqt="";
              	   String hStr ="";
              	   String sstr ="";
              	   double gdj =0.0000;
              	   double yz =0.0000;
              	   double qt =0.0000;
              	   
              	   String hstr1="";
              	   String sstr1="";
              	   
              		//int intnum=xh = pagesize*(curpage-1)+1;
              		System.out.println("--2-");
              		for(dqbean bean1:fylist)
              		{  
              		   szdq  = bean1.getSZDQ();
              		   actry  = bean1.getACTUALPAY();
              		   hStr += actry+";";
              		   sstr += szdq+";";
              		   //System.out.println("======"+bean1.getGDJ());
              		   //System.out.println("======"+Double.parseDouble(bean1.getGDJ()));
              		   gdj +=Double.parseDouble(bean1.getGDJ());
              		   yz  +=Double.parseDouble(bean1.getYZ());
              		   qt  +=Double.parseDouble(bean1.getGsQT());
              		   
              		   shibili = bean1.getShibili();
              		 //  System.out.println(gdj+"------"+yz+"-------"+qt);
              	String color=null;
              	if(intnum%2==0){
              	    color="#FFFFFF";

              	 }else{
              	    color="#DDDDDD" ;
              	}
                        intnum++;
       %>
       
       <tr bgcolor="<%=color%>" class="form_label">
       <td>
       		<div align="center"><%=intnum%></div>
       		</td>
       		<td>
       			<div align="center"><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center"><%=actry%></div>
       		</td>
       		<td>
       			<div align="center"><%=shibili%></div>
       		</td>  
       		                 		
       </tr>
	
  	  	
								
			<%} %>
			
			</table>
			</div>
			<table>
			<tr>
			<td>
			<div>
			<img width="400px" height="400px" src="<%=path%>/servlet/MapdqServlet?cityStr=<%=sstr%>&dataStr=<%=hStr%>&bz=2">
			</div>
			</td>
			<td>
			<div>
			<% 
			 gdgdj =  pay.format(gdj);
			 gdyz  =  pay.format(yz);
			 gdqt  = pay.format(qt);
			 hstr1 = gdgdj + ";" + gdyz + ";" + gdqt + ";";
			 System.out.println("::::"+hstr1);
       		 sstr1 = "供电局;业主;其他";%>
			<img width="400px" height="400px" src="<%=path%>/servlet/MapdqServlet?cityStr=<%=sstr1%>&dataStr=<%=hstr1%>&bz=2">
			</div>
			</td>
			</tr>
			</table>
			<%	}%>
					
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
