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
int d=0;
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
   	<tr >
	                     <td bgcolor='#DDDDDD' width="9%"><div align="center" class="bttcn">区县</div></td>   
	                      <td bgcolor='#DDDDDD' width="5%"><div align="center" class="bttcn">邮编</div></td>  
				   	    <td bgcolor='#DDDDDD'  width="5%"><div align="center" class="bttcn">姓名</div></td> 
				   		<td bgcolor='#DDDDDD'  width="7%"><div align="center" class="bttcn">完成站点数</div></td> 
				   		<td bgcolor='#DDDDDD'  width="3%"><div align="center" class="bttcn">性别</div></td>
				   		<td bgcolor='#DDDDDD'  width="7%"><div align="center" class="bttcn">账号</div></td>
				   		<td bgcolor='#DDDDDD'  width="5%"><div align="center" class="bttcn">角色</div></td>
				   		<td bgcolor='#DDDDDD'  width="7%"><div align="center" class="bttcn">部门</div></td>
				   		<td bgcolor='#DDDDDD'  width="7%"><div align="center" class="bttcn">职位</div></td>
				   		
				   		<td bgcolor='#DDDDDD'  width="6%"><div align="center" class="bttcn">手机</div></td>
				   		<td bgcolor='#DDDDDD'  width="6%"><div align="center" class="bttcn">座机</div></td>
				   		<td bgcolor='#DDDDDD'  width="7%"><div align="center" class="bttcn">邮箱</div></td>
				   		<td bgcolor='#DDDDDD'  width="15%"><div align="center" class="bttcn">地址</div></td>
				   		<td bgcolor='#DDDDDD'  width="7%"><div align="center" class="bttcn">备注</div></td>
				   	</tr>	
				   		
  	 	<%	
  	 	String whereStr="",iii="",ff1="",ff2="",ff3="",ff4="",qq="",yy="",nn="";
  	 	    if(shi != null && !shi.equals("")&&!shi.equals("0")){
				whereStr=whereStr+" AND ACC.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("")&&!xian.equals("0")){
				whereStr=whereStr+" AND ACC.XIAN='"+xian+"'";
			}
		    if(zdlx!=null&&!zdlx.equals("")&&!zdlx.equals("0")){
		       whereStr=whereStr+" AND ZD.STATIONTYPE='"+zdlx+"'";
			}
			if (jzproperty1!=null&&!jzproperty1.equals("")&&!jzproperty1.equals("0")){
			   whereStr=whereStr+" AND ZD.PROPERTY='" + jzproperty1 + "'";
			}
			if (bztime!=null&&!bztime.equals("")) {
			whereStr=whereStr+" and BB.DIANBIAOID IN (select du.ammeterid_fk from dianfeiview df,dianduview du where  df.ammeterdegreeid_fk=du.ammeterdegreeid and to_char(df.accountmonth,'yyyy-mm-dd')='" + bztime + "')";
			}
		System.out.println("whereStr"+whereStr);
		System.out.println("shishishis"+shi);
		List<ZhanDianForm> list=null;
   	     JiZhanBean bean = new JiZhanBean();
   	      String zywf="",zyyc="",xxwc="",xxwf="",xxyc="",shi12="",xian123="";
   	      String xiana="",xq="",zip="",name="",sex="",acname="",rolname="",tel="",zw="",bm="",mobile="",address="",email="",memo="";
         double zybl,xxbl;
         int zynum;
       //int intnum=xh = pagesize*(curpage-1)+1;
       	 list = bean.getPageData2345(whereStr,loginId,loginName);
        String s="0",ss="0";
 			for(ZhanDianForm listt:list){
 			xiana=listt.getXian();
 			if("".equals(xiana)||null==xiana||"null".equals(xiana)){
 			 xiana="";
 			}
 			xq=listt.getXiaoqu();
 			if("".equals(xq)||null==xq||"null".equals(xq)){
 			 xq="";
 			}
 			zip=listt.getZhan();
 			if("".equals(zip)||null==zip||"null".equals(zip)){
 			 zip="";
 			}
 			name=listt.getEntrypensonnel();
 			if("".equals(name)||null==name||"null".equals(name)){
 			 name="";
 			}
 			sex=listt.getSex();
 			if("".equals(sex)||null==sex||"null".equals(sex)){
 			 sex="";
 			} if("1".equals(sex)){
 			 sex="男";
 			}else if("0".equals(sex)){
 			 sex="女";
 			}
 			acname=listt.getAccname();
 			if("".equals(acname)||null==acname||"null".equals(acname)){
 			 acname="";
 			}
 			rolname=listt.getRolename();
 			if("".equals(rolname)||null==rolname||"null".equals(rolname)){
 			 rolname="";
 			}
 			mobile=listt.getMOBILE();
 			if("".equals(mobile)||null==mobile||"null".equals(mobile)){
 			 mobile="";
 			}
 			email=listt.getEMAIL();
 			if("".equals(email)||null==email||"null".equals(email)){
 			 email="";
 			}
 			zw=listt.getPOSITION();
 			if("".equals(zw)||null==zw||"null".equals(zw)){
 			 zw="";
 			}
 			bm=listt.getBUMEN();
 			if("".equals(bm)||null==bm||"null".equals(bm)){
 			 bm="";
 			}
 			tel=listt.getBieming();
 			if("".equals(tel)||null==tel||"null".equals(tel)){
 			 tel="";
 			}
 			
 			address=listt.getAddress();
 			memo=listt.getMemo();
 			if("".equals(address)||null==address||"null".equals(address)){
 			 address="";
 			}
 			if("".equals(memo)||null==memo||"null".equals(memo)){
 			 memo="";
 			}
 			xxwc=listt.getXxwc();
        		d+=Double.parseDouble(xxwc);
                    DecimalFormat pay4=new DecimalFormat("0.00");
                // s=pay4.format(zybl);
                //  ss= pay4.format(xxbl);
                intnum++;
        			if (intnum % 2 == 0) {
        				color = "#FFFFFF";
} else {
        				color = "#DDDDDD";
        			}
        			if(!xxwc.equals("0")){
        %>  	
   	<tr bgcolor="#FFFFFF">
   	    <td><div align="center"><%=xiana%></div></td>	
   	    <td><div align="left"><%=zip%></div></td>   
   	    <td><div align="center"><%=name%></div></td>
	   	<td><div align="right"><%=xxwc%></div></td>
   	    <td><div align="center"><%=sex%></div></td>
   	    <td><div align="left"><%=acname%></div></td>
   	    <td><div align="center"><%=rolname%></div></td>
   	    <td><div align="left"><%=bm%></div></td>
   	    <td><div align="left"><%=zw%></div></td>
   	    <td><div align="center"><%=mobile%></div></td>
   	    <td><div align="center"><%=email%></div></td>
   	    <td><div align="center"><%=tel%></div></td>
   	    <td><div align="left"><%=address%></div></td>
   	    <td><div align="left"><%=memo%></div></td>
   	</tr>
   	 <%
 		}}
       %>
       <tr bgcolor="#DDDDDD">
       <td><div align="center" >合计：</div></td>
       <td><div align="center" ></div></td>
       <td><div align="center" ></div></td>
       <td><div align="right" ><%=d%></div></td>
      <td><div align="center" ></div></td>
       <td><div align="center" ></div></td>
       <td><div align="center" ></div></td>
       <td><div align="center" ></div></td>
       <td><div align="center" ></div></td>
       <td><div align="center" ></div></td>
       <td><div align="center" ></div></td>
       <td><div align="center" ></div></td>
       <td><div align="center" ></div></td>
       <td><div align="center" ></div></td>
       </tr>
  </table>
  </div>
</body>
</html>
