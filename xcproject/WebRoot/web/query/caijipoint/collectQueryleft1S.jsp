<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean,com.noki.mobi.common.AccountJzqaS,com.noki.mobi.common.zdbzbeanB" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*" %>

<%
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
	//  id=ArticleID
	String shi = request.getParameter("shi");
	String xian = request.getParameter("xian");
	String xiaoqua = request.getParameter("xiaoqu");
    String bla=request.getParameter("bl");
    String bzmonth=request.getParameter("bzmonth");//标准月份
    
    String yf=request.getParameter("yf");//月份    2 开始月份     3 报账月份
     String sjyf=request.getParameter("sjyf");
     String dyt=request.getParameter("dbyt");//3 结算电表    2管理电表
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
%>

<html>
<head>
<title>
</title>
<style>

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
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>

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
function CloseDiv(){
	closediv();
}

var path = '<%=path%>';   
    function modifyad(zp,zs,cj,g2,g3,shi,xian,bla,bd,yf,bzmonth,dyt,sjyf,gxbzw){
    if(g3=="是"){g3="1";}
     if(g3=="否"){g3="0";}
      if(g2=="是"){g2="1";}
     if(g2=="否"){g2="0";}
    
     
    	var b=path+"/web/query/caijipoint/collectQueryright1S.jsp?zp="+zp+"&zs="+zs+"&cj="+cj+"&g2="+g2+"&g3="+g3+"&shi="+shi+"&xian="+xian+"&bl="+bla+"&bd="+bd+"&bl="+bla+"&yf="+yf+"&bzmonth="+bzmonth+"&dyt="+dyt+"&sjyf="+sjyf+"&gxbzw="+gxbzw+"&";			
			b = b.substring(0,b.length-1);
			 var a = " <a href="+b+" target='treeNodeInfo' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
			// showdiv("请稍等..............");
    } 
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<%
	permissionRights=commBean.getPermissionRight(roleId,"0301");
%>
<body  class="body" >
	
	<form action="" name="form1" method="POST">
  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
<tr  class="relativeTag">
<td bgcolor="#DDDDDD" colspan="10"><div align="center" style="font-weight:bold;">基站标准信息</div></td>
</tr>
     <tr height = "20" class="relativeTag">
      <td width="5%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">序号</div></td>
         <td width="4%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">2G</div></td>     
         <td width="4%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">3G</div></td>
         <td width="8%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">厂家</div></td>                     
         <td width="5%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">载频</div></td>
         <td width="5%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">载扇</div></td>
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">非共享电量(度/天)</div></td>
          <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">超标电表个数</div></td>
          <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">共享电量 (度/天)</div></td>
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">超标电表个数</div></td>
     </tr>
     
       <%
       if(null !=bla&& !bla.equals("")){
       	String whereStr = "";
                    
             if(null!=yf&&!yf.equals("")){
            	 whereStr=whereStr+" and c.khtime='"+sjyf+"'";
	           }
              		
                   AccountJzqaS  bean = new AccountJzqaS();
                   List<zdbzbeanB> fylist = null;
                     	
                    fylist=bean.getPageDatap(whereStr,bla,yf,bzmonth,dyt);
                    
              		String jzname = "",actualdegree="",ammeterid_fk="",actualpay="",zdcode="",dbid="",dl="",time="",zdid="",dbida="";
              		int intnum=xh = pagesize*(curpage-1)+1;
              		int linenum=0;String gxbzw1="",gxbzw2="";
              		int zs,zp;
              		String g2="",g3="",ts="",gx="";
              		String cj="",sb="",jz="",js="",gl="";
              		String shi1="",xian1="",xiaoqu="",zdname="",bl="";
              		double df=0.0,bd=0.0;
              		if(fylist!=null){
              		for(zdbzbeanB bean1:fylist)
              		{
              		g2=bean1.getG2().trim();
              		System.out.println(g2+" <--g2 ");
              		if("1".equals(g2)){
              		 g2="是";
              		}else{
              		 g2="否";
              		}
              		g3=bean1.getG3().trim();
              		System.out.println(g3+" <--g3 ");
              		if("1".equals(g3)){
              		 g3="是";
              		}else{
              		 g3="否";
              		}
              		zp=bean1.getZP();
              		zs=bean1.getZS();
              		cj=bean1.getCHANGJIA();
              		sb=bean1.getSHEBEI();
              		jz=bean1.getJZTYPE();
              		bd=bean1.getEDGL();
              		js=bean1.getJs();
              		if(null==js||"".equals(js)){js="0";}
              		xian1=bean1.getXian();
              		shi1=bean1.getSHI();
              		xiaoqu=bean1.getXiaoqu();
              		jzname=bean1.getZdname();
              		bl=bean1.getBl();
              		dbida=bean1.getDbid();
              		zdid=bean1.getZdid();
              		ts = bean1.getTs();
              		gx = bean1.getGx();
              		if(null==gx||"".equals(gx)){gx="0";}
              		if(js!="0"){gxbzw1="1";}
              		if(gx!="0"){gxbzw2="0";}
              	String color=null;
              	if(intnum%2==0){
              	    color="#DDDDDD";

              	 }else{
              	    color="#FFFFFF" ;
              	}
                        
       %>
       <% if(!"0".equals(js)||!"0".equals(gx)){ intnum++;%>
       <tr bgcolor="<%=color%>" class="form_label">
       <td>
       			<div align="center"><%=intnum-1%></div>
       		</td>
       		<td>
       			<div align="center"><%=g2%></div>
       		</td>
       		<td>
       			<div align="center"><%=g3%></div>
       		</td>
       		<td>
       			<div align="center"><%=cj%></div>
       		</td>
       		<td>
       			<div align="right"><%=zp%></div>
       		</td>
       		<td>
       			<div align="right"><%=zs%></div>
       		</td>
       		 
       		<td>
       			<div align="right"><%=bd%></div>
       		</td>
       		<td>
       			<div align="right"><a href="javascript:modifyad('<%=zp%>','<%=zs%>','<%=cj%>','<%=g2%>','<%=g3%>','<%=shi%>','<%=xian%>','<%=bla%>','<%=bd%>','<%=yf%>','<%=bzmonth%>','<%=dyt%>','<%=sjyf%>','<%=gxbzw1%>')" ><%=js%></a></div>
       		</td>
       		<td>
       			<div align="right"><%=ts%></div>
       		</td>
       		<td>
       		<div align="right"><a href="javascript:modifyad('<%=zp%>','<%=zs%>','<%=cj%>','<%=g2%>','<%=g3%>','<%=shi%>','<%=xian%>','<%=bla%>','<%=bd%>','<%=yf%>','<%=bzmonth%>','<%=dyt%>','<%=sjyf%>','<%=gxbzw2%>')" ><%=gx%></a></div>
       			<div align="right"><%=gx%></div>
       		</td>
       </tr>
				<% }}}}%>
				
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
<script type="text/javascript">
	window.parent.CloseDiv();
</script>
