<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.noki.util.CTime,com.noki.function.SitePueQuery,com.noki.function.SitePueQueryBean,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.text.*"%>
<%
String path = request.getContextPath();
Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
int intnum=0;
String color="";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sheng = (String)session.getAttribute("accountSheng");
String dbyt = request.getParameter("dbyt")!=null?request.getParameter("dbyt"):"0";
 String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
 String accountmonth=request.getParameter("accountmonth")!=null?request.getParameter("accountmonth"):"";
 String pue=request.getParameter("pue")!=null?request.getParameter("pue"):"";
 String chaobiao=request.getParameter("chaobiao")!=null?request.getParameter("chaobiao"):"";

 String yhdld=request.getParameter("yhdld")!=null?request.getParameter("yhdld"):"";
 String yhdlx=request.getParameter("yhdlx")!=null?request.getParameter("yhdlx"):"";
 
  String jzproperty1=request.getParameter("jzproperty1")!=null?request.getParameter("jzproperty1"):"";
  String kongtiao1=request.getParameter("kongtiao1")!=null?request.getParameter("kongtiao1"):"";
  String kongtiao2=request.getParameter("kongtiao2")!=null?request.getParameter("kongtiao2"):"";

	
String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
String loginName = (String)session.getAttribute("loginName");
      String permissionRights="";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stationfees.jsp' starting page</title>
<style type="text/css">
   .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
    }.style1 {
	color: red;
	font-weight: bold;
}.bttcn{color:BLACK;font-weight:bold;}
.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}
    		.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
</style>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript">
var path = '<%=path%>';
	var oCalendarEnny=new PopupCalendarny("oCalendarEnny");	//初始化控件时,请给出实例名称如:oCalendarEn
	oCalendarEnny.Init();
	 
	var oCalendarChsny=new PopupCalendarny("oCalendarChsny");	//初始化控件时,请给出实例名称:oCalendarChs
	oCalendarChsny.weekDaySting=new Array("日","一","二","三","四","五","六");
	oCalendarChsny.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
	oCalendarChsny.oBtnTodayTitle="确定";
	oCalendarChsny.oBtnCancelTitle="取消";
	oCalendarChsny.Init();
	
	 function queryDegree(){	 
		 if(checkNotSelected(document.form1.shi,"市")&&checkNotnull(document.form1.yue,"月份")){
			  document.form1.action=path+"/web/cx/stationfees.jsp";
                   document.form1.submit();      
         }
      }
	  $(function(){
		$("#chaxun").click(function(){
			queryDegree();
		});
	});
	  function changeCity(){
	var sid = document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;s
	}
}
	
	
		
</script>
</head>
 <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean> 
 <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
  <div>
   <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
   	<tr  class="relativeTag">
   	<td width="5%" class="relativeTag" bgcolor='#DDDDDD' ><div align="center" class="bttcn">编号</div></td> 
   	    <td width="5%" class="relativeTag" bgcolor='#DDDDDD' ><div align="center" class="bttcn">区县</div></td>         
	    <td width="5%" class="relativeTag" bgcolor='#DDDDDD' ><div align="center" class="bttcn">站点名称</div></td>    
	    <td width="5%" class="relativeTag" bgcolor='#DDDDDD' ><div align="center" class="bttcn">站点属性</div></td>    
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"  ><div align="center" class="bttcn">直流负荷</div></td> 
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"  ><div align="center" class="bttcn">交流负荷</div></td> 
		 <td width="5%" class="relativeTag" bgcolor='#DDDDDD' ><div align="center" class="bttcn">本次电量</div></td>  
			  <td width="5%" class="relativeTag" bgcolor='#DDDDDD' ><div align="center" class="bttcn">本次电费</div></td> 	
			 <td width="5%" class="relativeTag" bgcolor='#DDDDDD' ><div align="center" class="bttcn">pue值</div></td>  
			  <td width="5%" class="relativeTag" bgcolor='#DDDDDD' ><div align="center" class="bttcn">超标比例</div></td>  
			  <td width="5%" class="relativeTag" bgcolor='#DDDDDD' ><div align="center" class="bttcn">实际月耗电量</div></td> 
			   <td width="5%" class="relativeTag" bgcolor='#DDDDDD' ><div align="center" class="bttcn">额定月耗电量</div></td> 
			   <td width="5%" class="relativeTag" bgcolor='#DDDDDD' ><div align="center" class="bttcn">详细</div></td>  	   		
		   		</tr> 
		   		  
				   		
  	 	<%	
  	 	String whereStr="",iii="",ff1="",ff2="",ff3="",ff4="",qq="",yy="",nn="";
  	 	if(shi != null && !shi.equals("")&& !shi.equals("0")){
			whereStr=whereStr+" AND Z.SHI='"+shi+"'";
		}
		if(xian != null && !xian.equals("")&& !xian.equals("0")){
		whereStr=whereStr+" AND Z.XIAN='"+xian+"'";
		}
		if(zdlx!=null&&!zdlx.equals("")&& !zdlx.equals("0")){
		whereStr=whereStr+" AND Z.STATIONTYPE='"+zdlx+"'";
		}
		if (jzproperty1!=null&&!jzproperty1.equals("")&& !jzproperty1.equals("0")) {
			whereStr=whereStr+" AND Z.PROPERTY='" + jzproperty1 + "'";
		}	
		if (kongtiao1!=null&&!kongtiao1.equals("")&& !kongtiao1.equals("-1")) {
			whereStr=whereStr+" AND Z.KT1='" + kongtiao1 + "'";
		}
		if (kongtiao2!=null&&!kongtiao2.equals("")&& !kongtiao2.equals("-1")) {
			whereStr=whereStr+" AND Z.KT2='" + kongtiao2 + "'";
		}
		System.out.println("whereStr"+whereStr);
		System.out.println("shishishis"+shi);
		List<SitePueQueryBean> list=null;
		SitePueQuery bean = new SitePueQuery();
   	      String xian1="",zdname="",zdsx="",zlfh="",jlfh="",puez="",chaobiaobl="",yhdl="",bcdl="",bcdf="",dbid="",zdid="",sjyuehdl="";
         double zybl,xxbl;
         int zynum;
       //int intnum=xh = pagesize*(curpage-1)+1;
       	 list = bean.getPageData(whereStr,loginId,pue,chaobiao,yhdld,yhdlx,dbyt,accountmonth);
        String s="0",ss="0";
 			for(SitePueQueryBean listt:list){
 			// String zywc="",zywf="",zyyc="",xxwc="",xxwf="",xxyc="";
        // double zybl,xxbl;
        xian1=listt.getQuxian();
       zdname=listt.getZdname();
       zdsx=listt.getZdsx();
       zlfh=listt.getZlfh();
       jlfh=listt.getJlfh();
       puez=listt.getPue();
       bcdl=listt.getBcdl();
       bcdf=listt.getBcdf();
       chaobiaobl=listt.getChaobiaobili();
       yhdl=listt.getYuehdl();
       dbid=listt.getDbid();
       zdid=listt.getZdid();
       sjyuehdl=listt.getSjyuehdl();
        			//num为序号，不同页中序号是连续的
        		
                    DecimalFormat pay4=new DecimalFormat("0.00");
                    DecimalFormat pay=new DecimalFormat("0.0");
                    zlfh=pay.format(Double.parseDouble(zlfh));
                    jlfh=pay.format(Double.parseDouble(jlfh));
                    puez=pay.format(Double.parseDouble(puez));
                    yhdl=pay.format(Double.parseDouble(yhdl));
                    bcdl=pay.format(Double.parseDouble(bcdl));
                    sjyuehdl=pay.format(Double.parseDouble(sjyuehdl));
                    bcdf=pay4.format(Double.parseDouble(bcdf));
                    chaobiaobl=pay4.format(Double.parseDouble(chaobiaobl)*100);
                
        			if (intnum % 2 == 0) {
        				color = "#FFFFFF";

        			} else {
        				color = "#DDDDDD";
        			}
        			  intnum++;
        %>  		
   	<tr bgcolor="<%=color%>">
   	<td><div align="center" ><%=intnum%></div></td>	
   	    <td><div align="center" ><%=xian1%></div></td>	
   	    	<td><div align="left" ><a href="javascript:infor1('<%=zdid%>')"><%=zdname%></a></div></td>   
	   	<td><div align="center" ><%=zdsx%></div></td>
   	    <td><div align="right" ><%=zlfh%></div></td>
   	    <td><div align="right" ><%=jlfh%></div></td>
   	    <td><div align="right" ><%=bcdl%></div></td>
   	    <td><div align="right" ><%=bcdf%></div></td>
   	    <td><div align="right" ><%=puez%></div></td>
   	    <td><div align="right" ><%=chaobiaobl%>%</div></td>
   	    <td><div align="right" ><%=sjyuehdl%></div></td>
   	    <td><div align="right" ><%=yhdl%></div></td>
   	    <td><div align="center" ><a href="javascript:information('<%=dbid%>','<%=accountmonth%>')">详细</a></div></td>
   	   
   	</tr>
   
  
   	 <%
 		}
       %>
       
  </table>
  </div>
</body>
<script type="text/javascript">
var path='<%=path%>';
 function information(dbid,accountmonth){    	
    	window.open(path+"/web/newgn/sitepuequery02.jsp?dbid="+dbid+"&accountmonth="+accountmonth,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
    }
 function infor1(zdid){    	
 window.open(path+"/web/newgn/sitepuequery03.jsp?zdid="+zdid,'','width=1100,height=600px,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
}
</script>
</html>

