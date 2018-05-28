<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.ZhanDianForm,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.text.*"%>
<%
String path = request.getContextPath();
Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
int intnum=0;
String color="";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sheng = (String)session.getAttribute("accountSheng");
 String bztime  = request.getParameter("bztime")!= null ? request.getParameter("bztime"): "";
String shi = request.getParameter("sss");
String xian = request.getParameter("xian");
String jzproperty1=request.getParameter("jzproperty1");
String zdlx=request.getParameter("zdlx");
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
		return;
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
   	    <td width="5%" class="relativeTag" bgcolor='#DDDDDD' rowspan="2"><div align="center" class="bttcn">地市</div></td>         
	    <td width="5%" class="relativeTag" bgcolor='#DDDDDD' rowspan="2"><div align="center" class="bttcn">站点数</div></td>    
	    <td width="5%" class="relativeTag" bgcolor='#DDDDDD' rowspan="2"><div align="center" class="bttcn">区县</div></td>    
				   	    <td bgcolor='#DDDDDD' class="relativeTag" width="5%"  colspan="4"><div align="center" class="bttcn">分摊专业线</div></td> 
				   		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"  colspan="4"><div align="center" class="bttcn">详细分摊</div></td> 	
				   		
		   		</tr> 
		   		   <tr   class="relativeTag">
				   		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">完成</div></td>
				   		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">未分</div></td>
				   		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">异常</div></td>
				   		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">完成比例</div></td>
				   		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">完成</div></td>
				   		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">未分</div></td>
				   		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">异常</div></td>
				   		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">完成比例</div></td>
				   	</tr>	
				   		
  	 	<%	
  	 	String whereStr="",iii="",ff1="",ff2="",ff3="",ff4="",qq="",yy="",nn="";
  	 	    if(shi != null && !shi.equals("")&&!shi.equals("0")){
				whereStr=whereStr+" AND Z.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("")&&!xian.equals("0")){
				whereStr=whereStr+" AND Z.XIAN='"+xian+"'";
			}
		    if(zdlx!=null&&!zdlx.equals("")&&!zdlx.equals("0")){
		        whereStr=whereStr+" AND Z.STATIONTYPE='"+zdlx+"'";
			}
			if (jzproperty1!=null&&!jzproperty1.equals("")&&!jzproperty1.equals("0")){
			    whereStr=whereStr+" AND Z.PROPERTY='" + jzproperty1 + "'";
			}
			if (bztime!=null&&!bztime.equals("")) {
			whereStr=whereStr+" and d.dbid in (select du.ammeterid_fk from dianfeiview df,dianduview du where  df.ammeterdegreeid_fk=du.ammeterdegreeid and to_char(df.accountmonth,'yyyy-mm')='" + bztime + "')";
			}
		System.out.println("whereStr"+whereStr);
		System.out.println("shishishis"+shi);
		List<ZhanDianForm> list=null;
   	     JiZhanBean bean = new JiZhanBean();
   	      String zywc="",zywf="",zyyc="",xxwc="",xxwf="",xxyc="",shi12="",xian123="";
         double zybl,xxbl;
         int zynum;
       //int intnum=xh = pagesize*(curpage-1)+1;
       	 list = bean.getPageData234(whereStr,loginId,loginName);
        String s="0",ss="0";
 			for(ZhanDianForm listt:list){
 			// String zywc="",zywf="",zyyc="",xxwc="",xxwf="",xxyc="";
        // double zybl,xxbl;
        shi12=listt.getShi();
        zynum=listt.getZynum();
        xian123=listt.getXian();
        zywc=listt.getZywc();zywf=listt.getZywf();zyyc=listt.getZyyc(); zybl=listt.getZybl();
        xxwc=listt.getXxwc();xxwf=listt.getXxwf();xxyc=listt.getXxyc(); xxbl=listt.getXxbl();
        			//num为序号，不同页中序号是连续的
        		
                    DecimalFormat pay4=new DecimalFormat("0.00");
                 s=pay4.format(zybl);
                  ss= pay4.format(xxbl);
        			if (intnum % 2 == 0) {
        				color = "#FFFFFF";

        			} else {
        				color = "#DDDDDD";
        			}
        			  intnum++;
        %>  		
   	<tr bgcolor="<%=color%>">
   	    <td><div align="center" ><%=shi12%></div></td>	
   	    	<td><div align="right" ><%=zynum%></div></td>   
	   	<td><div align="center" ><%=xian123%></div></td>
   	    <td><div align="right" ><%=zywc%></div></td>
   	    <td><div align="right" ><%=zywf%></div></td>
   	    <td><div align="right" ><%=zyyc%></div></td>
   	    <td><div align="right" ><%=s%>%</div></td>
   	    <td><div align="right" ><%=xxwc%></div></td>
   	    <td><div align="right" ><%=xxwf%></div></td>
   	<td><div align="right" ><%=xxyc%></div></td>
   	<td><div align="right" ><%=ss%>%</div></td>
   	</tr>
   
  
   	 <%
 		}
       %>
       
  </table>
  </div>
</body>
</html>
