 <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*" %>

<%
	
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    
    
    String dltype = request.getParameter("dltype");
    String ydsbb = request.getParameter("ydsb");
    String dbmc = request.getParameter("dbname")!=null?request.getParameter("dbname"):"";
	String startmonth = request.getParameter("startmonth")!=null?request.getParameter("startmonth"):"";
	String endmonth = request.getParameter("endmonth")!=null?request.getParameter("endmonth"):"";
	String stationname = request.getParameter("stationname")!=null?request.getParameter("stationname"):"";
	String shi = request.getParameter("shi");
	String xian = request.getParameter("xian");
	String jztype = request.getParameter("jztype");
	System.out.println(shi+" 1"+xian+"2 "+stationname+" 3"+startmonth+" 4"+endmonth+"77777777777777");
     String canshuStr="";
     if((shi!=null)||(xian!=null)||((startmonth!=null)&&(endmonth!=null))||(jztype!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian+"&startmonth="+startmonth+"&endmonth="+endmonth+"&jztype="+jztype;
     }
    
      System.out.println("shijian!!!!!!:"+startmonth);
     
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
    function modifyad(zdcode,startmonth,endmonth,jzname){
    	var b=path+"/web/query/caijipoint/collectQueryright.jsp?zdcode="+zdcode+"&startmonth="+startmonth+"&endmonth="+endmonth+"&";			
			b = b.substring(0,b.length-1);
			 var a = " <a href="+b+" target='treeNodeInfo' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
		var c=path+"/web/query/caijipoint/collectQueryMap.jsp?zdcode="+zdcode+"&startmonth="+startmonth+"&endmonth="+endmonth+"&jzname="+jzname+"&";			
			c = c.substring(0,c.length-1);
			 var a = " <a href="+c+" target='treeMap1' id='tmpTree'></a>";
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
         <td width="8%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">站点编号</div></td>     
         <td width="15%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">站点名称</div></td>
         <td width="8%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">电表数</div></td>                     
         <td width="8%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">总耗电量</div></td>
         <td width="8%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">天数</div></td>
         <td width="10%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">日均耗电量</div></td>
     </tr>
       <%
        
       String whereStr = "";
       String str="";
		if(null !=shi  && !shi.equals("") && !shi.equals("-1")){
			whereStr=whereStr+" and z.shi='"+shi+"'";
		}
		if(null != xian && !xian.equals("") && !xian.equals("-1")){
			
			whereStr=whereStr+" and z.xian='"+xian+"'";
		}			
		if(null != startmonth  &&!startmonth.equals("") && !startmonth.equals("0")){
			str=str+" and to_char(a.thisdatetime,'yyyy-mm-dd')>='"+startmonth+"'";
		}
		if(null != endmonth  && !endmonth.equals("") && !endmonth.equals("0")){
			str=str+" and to_char(a.lastdatetime,'yyyy-mm-dd')<='"+endmonth+"'";
		}
		if( null != stationname && !stationname.equals("") && !stationname.equals(" ")){
			whereStr=whereStr+" and z.jzname like '%"+stationname+"%'";
		}
		if( null != jztype && !jztype.equals("") && !jztype.equals(" ")){
			whereStr=whereStr+" and z.jztype ='"+jztype+"'";
		}

		
		if(null !=shi  && !shi.equals("") && !shi.equals("-1")){
			
		
		System.out.println("whereStr"+whereStr);
        haodianliangBean bean = new haodianliangBean();
       	ArrayList fylist = new ArrayList();
       	fylist = bean.getCollect(curpage,whereStr,loginId,str);
       	allpage=bean.getAllPage();
       	System.out.println("ALLLLLLL"+allpage);
		String jzname = "",actualdegree="",ammeterid_fk="",actualpay="",zdcode="",dbid="",dl="",time="";
		int intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		double df=0.0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
             linenum++;
		     //num为序号，不同页中序号是连续的
		    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    jzname = jzname != null ? jzname : "";
		    
		    actualdegree = (String)fylist.get(k+fylist.indexOf("CO"));	
		    actualdegree = actualdegree != null ? actualdegree : "";
		    
		    time = (String)fylist.get(k+fylist.indexOf("SO"));	
		    time = time != null ? time : "";
		    
		    zdcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));	
		    zdcode = zdcode != null ? zdcode : "";
		    
		    dbid=(String)fylist.get(k+fylist.indexOf("DBID"));
			String color=null;
            
            DecimalFormat mat=new DecimalFormat("0.00");
            DecimalFormat mat1=new DecimalFormat("0");
            df=Double.parseDouble(actualdegree);
            actualdegree=mat.format(df);
            
           
          
		    
		    Double hdl=Double.parseDouble(actualdegree)/Double.parseDouble(time);
		    dl=mat.format(hdl);
		    
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
          intnum++;

       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		<td>
       			<div align="left"><%=zdcode%></div>
       		</td>
       		<td>
       			<div align="center"><a href="javascript:modifyad('<%=zdcode%>','<%=startmonth%>','<%=endmonth%>','<%=jzname%>')" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="right"><%=dbid%></div>
       		</td>
       		<td>
       			<div align="right"><%=actualdegree%></div>
       		</td>
       		<td>
       			<div align="right"><%=time%></div>
       		</td>
       		<td>
       			<div align="right"><%=dl%></div>
       		</td>
       		                          		
           
       </tr>
       <%}%>
				<% }}%>
				
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
