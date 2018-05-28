 <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean" %>
<%@ page import="java.sql.ResultSet"%>
<%
    String path = request.getContextPath();    
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
	String roleId = (String)session.getAttribute("accountRole");    
	System.out.println("################站点"+roleId);
	String jztype1 = request.getParameter("jztype")!=null?request.getParameter("jztype"):"0";
    String jzproperty1 = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
    String bgsign = request.getParameter("bgsign")!=null?request.getParameter("bgsign"):"-1";
    String qyzt = request.getParameter("qyzt")!=null?request.getParameter("qyzt"):"-1";//站点启用状态

    String caijid = request.getParameter("caijidian")!=null?request.getParameter("caijidian"):"-1";
    String sitetype = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
    String color = null;
	String sheng = (String)session.getAttribute("accountSheng");
   	String agcode1="";
   	if(request.getParameter("shi")==null){
   		ArrayList shilist = new ArrayList();
   		CommonBean commBean = new CommonBean();
   		shilist = commBean.getAgcode(sheng,account.getAccountId());
		if(shilist!=null){
      	   int scount = ((Integer)shilist.get(0)).intValue();
           agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
         }
   	} 	
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;    
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
    String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
	String sname = request.getParameter("sname")!=null?request.getParameter("sname"):"";
	String szdcode = request.getParameter("szdcode")!=null?request.getParameter("szdcode"):"";
    String rgsh=request.getParameter("rgsh");//站点审核通过   首页传的值
    String rgsh2=request.getParameter("caiji");/// 采集站点
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
   	String permissionRights="";         
%>
<html>
<head>
<title>
站点列表
</title>
<style type="text/css">
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
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.form    {width:130px}
 .btt{ bgcolor:#888888;}
  .bttcn{ color:black;font-weight:bold;}
  .btt{font-weight:bold;}
/*  .form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height: 10;
			line-height: 1.2;
		}
.form_la{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}		
		
		
		
		*/
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
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
  
  
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
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
function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = "";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">";

		}
		else
		{
			trObject.style.display = "none";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">";
		}
}

  function chaxun(){
					document.form1.action=path+"/web/electricfees/yezhu_search.jsp";
					document.form1.submit();
	}
	function delLogs(){
		

	}
	function addJz(){
		document.form1.action=path+"/web/electricfees/addyezhu.jsp";
		document.form1.submit();
	}
	//新导入
	function daorujz(){
		document.form1.action=path+"/web/appJSP/sitemanage/stationmanage/stationinput.jsp?servletname=zhandiandaoru&action=upzhandian";
		document.form1.submit();
	}
	//原导入 2014-9-19
	//function daorujz(){
	//	document.form1.action=path+"/web/jizhan/zhandiandaoru.jsp?servletname=zhandiandaoru&action=upzhandian";
	//	document.form1.submit();
	//}
	  function vName(){//电表Id
         	var accName = document.form1.dbid.value;
                 if(accName==""){
           	alert("电表ID不能为空！");
                   return
          }
               window.open('validateDbid.jsp?dbid='+accName+'&id=0','','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
        }
	$(function(){
		$("#add").click(function(){
			addJz();
			showdiv("请稍等..............");
		});
		$("#import").click(function(){
			daorujz();
			showdiv("请稍等..............");
		});
		$("#query").click(function(){
			chaxun();
			showdiv("请稍等..............");
		});
	});
</script>
<script type="text/javascript">
//=点击展开关闭效果=

function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
var openTip = oOpenTip || "";
var shutTip = oShutTip || "";
if(targetObj.style.display!="none"){
   if(shutAble) return;
   targetObj.style.display="none";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = shutTip; 
   }
} else {
   targetObj.style.display="block";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = openTip; 
   }
}
}
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<%
	permissionRights = commBean.getPermissionRight(roleId, "0111");	
	System.out.println("permissionRights:"+permissionRights.indexOf("PERMISSION_ADD"));
%>
<body class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
	
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		<tr><td colspan="4">
			<div style="width:700px;height:50px">
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点信息列表</span>	
			 </div>
	    	</td></tr>	
		
		
		<!--   <tr> --> 
					    <!--  	<td colspan="4" width="50" >-->
										<!--	 <div style="width:700px;height:50px">-->
											 <!--   -->
											 <!-- <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点信息列表</span>-->	
											<!--	</div>-->
										<!--	</td>-->

	    	    <!--    </tr>	-->
	    	          	
	<!-- 	<tr><td height="20" colspan="4" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td>
	    	</tr>
	    	-->
	    	<tr><td height="19" colspan="4">
   				<div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td></tr>
	    	
			<tr>
				<td height="8%" width="1200">
					<table>
						<tr class="form_label">
							<td >城市：</td>
                   <td> <select name="shi" id="shi" style="width:130;" onchange="changeCity()" class="selected_font">
         		<option value="0">请选择</option>
         		<%
         			ArrayList shilist = new ArrayList();
         			shilist = commBean.getAgcode(sheng, shi, loginName);
         			if (shilist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) shilist.get(0)).intValue();
         				for (int i = scount; i < shilist.size() - 1; i += scount) {
         					agcode = (String) shilist.get(i + shilist.indexOf("AGCODE"));
         					agname = (String) shilist.get(i + shilist.indexOf("AGNAME"));
         		%>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    	}
                    %>
         		</select></td>
    <td>区县： </td>
        <td> <select name="xian" id="xian" style="width:130;"  onchange="changeCountry()" class="selected_font">
         		<option value="0">请选择</option>
         		<%
         			ArrayList xianlist = new ArrayList();
         		xianlist = commBean.getAgcode(shi, xian, loginName);
         			if (xianlist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) xianlist.get(0)).intValue();
         				for (int i = scount; i < xianlist.size() - 1; i += scount) {
         					agcode = (String) xianlist.get(i + xianlist.indexOf("AGCODE"));
         					agname = (String) xianlist.get(i + xianlist.indexOf("AGNAME"));
         		%>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    	}
                    %>
         	</select>
         	</td>
         <td>乡镇：</td>
         	<td><select name="xiaoqu" id="xiaoqu" style="width:130" class="selected_font">
         		<option value="0">请选择</option>             
         		<%
         			ArrayList xiaoqulist = new ArrayList();   //下拉列表
         			xiaoqulist = commBean.getAgcode(xian, xian, loginName);
         			if (xiaoqulist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) xiaoqulist.get(0)).intValue();
         				for (int i = scount; i < xiaoqulist.size() - 1; i += scount) {
         					agcode = (String) xiaoqulist.get(i+ xiaoqulist.indexOf("AGCODE"));
         					agname = (String) xiaoqulist.get(i+ xiaoqulist.indexOf("AGNAME"));
         		%>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    	}
                    %>
         	</select></td>
         			<td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>							
							</font>
					</p>
				</td>
	         		<td >
							       <div id="query" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
							</td>
							</table>
							</td>
							</tr>
							 <tr>
			     <td colspan="5">
					<div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table>
							<tr class="form_label">
         <td>站点名称：</td>
         <td><input type="text" name="sname" value="<%=sname%>"  style="width:130px" class="selected_font"/></td>
         <td>站点ID：</td>
         <td><input type="text" name="szdcode" value="<%=szdcode%>" style="width:130px" class="selected_font"/></td>
         </tr>
         <tr class="form_label">
         <td>站点类型：</td>
         <td>
         	<select name="stationtype"  class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList stationtype = new ArrayList();
         		stationtype = ztcommon.getSelctOptions("StationType");
	         	if(stationtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)stationtype.get(0)).intValue();
	         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
                    {
                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         </td>
           <td>站点启用状态：</td>	
         	<td><select name="qyzt" style="width:130" class="selected_font">
         	  <option value="-1">请选择</option>
         	  <option value="0">否</option>
         	  <option value="1">是</option>
         		
         	</select>
         	</td> 
       </tr>
      </table>
      </div>
      </td>
      </tr>
  </table>
 
             <table height="23">
<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>

</table>
  <%
  String whereStr="";
  String str="";
  String xuni="0";
         /*  if(sheng != null && !sheng.equals("")&& !sheng.equals("0")){
				whereStr=whereStr+" and JZTYPE='"+sheng+"'";
				str=str+" and JZTYPE='"+sheng+"'";
			}
			if(sheng != null && !sheng.equals("")&& !sheng.equals("0")){
				whereStr=whereStr+" and JZ.JZNAME like '%"+sheng+"%'";
				
			}*/
		if (xuni.equals("3")) {
			whereStr=whereStr+" and shsign!='1'";
		}
		if (!xiaoqu.equals("0")) {
			whereStr=whereStr+" and z.xiaoqu='" + xiaoqu + "'";
			str=str+" and z.xiaoqu='" + xiaoqu + "'";
		    } else if (!xian.equals("0")) {
			whereStr=whereStr+" and z.xian='" + xian + "'";
			str=str+" and z.xian='" + xian + "'";
		    } else if (!shi.equals("0")) {
			whereStr=whereStr+" and z.shi='" + shi + "'";
			str=str+" and z.shi='" + shi + "'";
		    } else if (!sheng.equals("0")) {
			whereStr=whereStr+" and z.sheng='" + sheng + "'";
			str=str+" and z.sheng='" + sheng + "'";
		    }
		if (sname.length() > 0 && sname != null) {
			whereStr=whereStr+" and z.name like '%" + sname + "%'";
			str=str+" and z.name like '%" + sname + "%'";
			System.out.println("01233210"+sname);
		}
		if (szdcode.length() > 0 && szdcode != null) {
			whereStr=whereStr+" and z.id='" + szdcode + "'";
			str=str+" and z.id='" + szdcode + "'";
		}
		
		if (!jztype1.equals("0")) {
			whereStr=whereStr+" and z.jztype='" + jztype1 + "'";
			str=str+" and z.jztype='" + jztype1 + "'";
			}
		if (!jzproperty1.equals("0")) {
			whereStr=whereStr+" and z.property='" + jzproperty1 + "'";
			str=str+" and z.property='" + jzproperty1 + "'";
			}
		if (!bgsign.equals("-1")) {
			whereStr=whereStr+" and z.bgsign='" + bgsign + "'";
			str=str+" and z.bgsign='" + bgsign + "'";
			}
		if (!qyzt.equals("-1")) {//站点启用状态
			whereStr=whereStr+" and z.qyzt='" + qyzt + "'";
			str=str+" and z.qyzt='" + qyzt + "'";
			}		
		if (rgsh!=null&&!rgsh.equals("null")) {//首页传的值 审核通过的
			whereStr=whereStr+" and z.SHSIGN='" + rgsh + "'";
			str=str+" and z.SHSIGN='" + rgsh + "'";
			}
		System.out.println("采集"+rgsh2);
		if (rgsh2!=null&&!rgsh2.equals("null")) {//首页传的值 采集站点
			whereStr=whereStr+" and z.caiji='" + rgsh2 + "'";
			str=str+" and z.caiji='" + rgsh2 + "'";
			}
  		if(caijid != null && !caijid.equals("")&&!caijid.equals("-1")){
				whereStr=whereStr+" and CAIJI='"+caijid+"'";
				str=str+" and CAIJI='"+caijid+"'";
			}
		if(sitetype != null && !sitetype.equals("")&&!sitetype.equals("0")){
				whereStr=whereStr+" and STATIONTYPE='"+sitetype+"'";
				str=str+" and STATIONTYPE='"+sitetype+"'";
			}
   %>	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">

             <tr height="23">
		          <td  height="23" width="3%"  bgcolor="#DDDDDD"><div align="center" class="btt" >序号</div></td>
                  <td  height="23" width="13%" bgcolor="#DDDDDD" ><div align="center" class="btt">所在地区</div></td>
                  <td  height="23" width="10%" bgcolor="#DDDDDD" ><div align="center" class="btt">业主名称</div></td>
                  <td  height="23" width="5%" bgcolor="#DDDDDD"><div align="center" class="btt">删除</div></td>
                  <td  height="23" width="5%" bgcolor="#DDDDDD"><div align="center" class="btt">修改</div></td>
            </tr>
       <%
		 SiteManage bean = new SiteManage();
       	 ArrayList fylist = new ArrayList();
         fylist = bean.getYeZhuData(curpage,loginName,loginId,whereStr);
       	 DecimalFormat df1 = new DecimalFormat("0.00");
       	 allpage=bean.getAllPage();
		String zlfh="",jlfh="", name = "",szdq = "",jzproperty = "", jztype= "",yflx = "",jnglmk="",gdfs="",area="",dianfei="",zdcode="",xunisign="",shsignxs="",dbid="",xlx="";
		String dlbg="",zldy="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

       			//num为序号，不同页中序号是连续的
       			String qwid =(String)fylist.get(k + fylist.indexOf("ID"));
       			int id = Integer.parseInt(qwid);       			
       			name = (String) fylist.get(k + fylist.indexOf("NAME"));
       			szdq = (String) fylist.get(k + fylist.indexOf("SZDQ"));
       %>
       <tr>
       		<td >
       			<div align="center" ><%=intnum++%></div>
       		</td>
       		
       		<td >
       			<div align="left"  ><%=szdq%></div>
       		</td>
       		<td >
       			<div align="left"  ><%=name%></div>
       		</td>
       		<td >
       			<div align="center" ><a href="#" onclick="delzd('<%=id%>')"><font  style="font-size: 12px;">删除</font></a></div>
       		</td>
       		<td >
       			<div align="center" ><a href="#" onclick="modifyjz('<%=id%>','<%=intnum-2%>')"><font  style="font-size: 12px;">修改</font></a></div>       			
       		</td>      
       </tr>
       <%
       	}
       %>
        <% if (intnum==0){
    	  System.out.println("QQQQ"+intnum);
         for(int i=0;i<15;i++){
          if(i%2==0){
			    color="#DDDDDD" ;
          }else{
			    color="#FFFFFF";
			}
         %>
        <tr >            
            <td>&nbsp;</td> 
            <td>&nbsp;</td>             
            <td>&nbsp;</td>             
            </tr>
      <% }}else if(!(intnum > 15)){
    	  for(int i=((intnum-1)%15);i<15;i++){
            if(i%2==0)
			    color="#FFFFFF";
            else
			    color="#DDDDDD";
        %>
        <tr bgcolor="<%=color%>">            
             <td>&nbsp;</td> 
             <td>&nbsp;</td>           
             <td>&nbsp;</td>              
      </tr>
        <% }}%>
       <tr bgcolor="#ffffff">
					<td colspan="15" >
						<div align="center">
							<font color='#000080'>&nbsp;页次:</font>
							&nbsp;&nbsp;
							 <b><font color=red><%=curpage%></font></b>

							 <font color='#000080'>/<b><%=allpage%></b>&nbsp;</font>
							 &nbsp;&nbsp;
							 <font color='#000080'>
						     <%
						     	if (curpage != 1) {
						     			out.print("<a href='javascript:gopagebyno(1)'>首页</a>");
						     		} else {
						     			out.print("首页");
						     		}
						     %>
					     </font>
					     &nbsp;&nbsp;
							 <font color='#000080'>
						     <%
						     	if (curpage != 1) {
						     			out.print("<a href='javascript:previouspage()'>上页</a>");
						     		} else {
						     			out.print("上页");
						     		}
						     %>
						   </font>
						   &nbsp;&nbsp;
							<font color='#000080'>
						     <%
						     	if (allpage != 0 && (curpage < allpage)) {
						     			out.print("<a href='javascript:nextpage()'>下页</a>");
						     		} else {
						     			out.print("下页");
						     		}
						     %>
             </font>
							&nbsp;&nbsp;
							<font color='#000080'>
					     <%
					     	if (allpage != 0 && (curpage < allpage)) {
					     			out.print("<a href='javascript:gopagebyno(" + allpage
					     					+ ")'>尾页</a>");
					     		} else {
					     			out.print("尾页");
					     		}
					     %>
            </font>
            &nbsp;&nbsp;
						<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" style="width:70px;font-family: 宋体;font-size:12px;line-height:120%;" >
					     <%
					     	for (int i = 1; i <= allpage; i++) {
					     			if (curpage == i) {
					     				out.print("<option value='" + i
					     						+ "' selected='selected'>" + i + "</option>");
					     			} else {
					     				out.print("<option value='" + i + "'>" + i
					     						+ "</option>");
					     			}
					     		}
					     %>
				    </select>


						</div>
					</td>
				</tr>
      
       <%
       	}
       %>


  	 </table> 		
										<table align="right">
										<tr>
			<td >							
												<div id="parent" style="display:inline" align="right">
                          <div style="width:300px;display:inline;" align="right"><hr></div>
                      </div>											
								
									</td>
									</tr>
										<tr>
										<td>
												<div align="right">
													<%if(!roleId.equals("101")){
															if (permissionRights.indexOf("PERMISSION_ADD") >= 0) {
													%>
													
													<!-- 
													<div id="import" style="position:relative;width:60px;height:23px;cursor: pointer;right:3px;float:right;">
							                            <img alt="" src="<%=request.getContextPath() %>/images/daoru.png" width="100%" height="100%" />
							                            <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">导入</span>
													</div>
													 -->
													
													<div id="add" style="position:relative;width:60px;height:23px;cursor: pointer;float:right;right:6px">
							                            <img alt="" src="<%=request.getContextPath() %>/images/xinzeng.png" width="100%" height="100%" />
							                            <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">新增</span>
													</div>
													
										        	<%
															}
										        		}
										        	%>
							        		</div>
										</td>
										</tr>
										</table>
										<input type="hidden" name="idd" value="">
										
</form>

</body>
</html>

<script language="javascript">
	var path = '<%=path%>';
	function gopage()
     {
      document.form1.submit();
     }
     function previouspage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage-1)%>';
        document.form1.action=path+"/web/electricfees/yezhu_search.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      		document.form1.action=path+"/web/electricfees/yezhu_search.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/electricfees/yezhu_search.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+pageno;
      document.form1.submit();
     }
     function delzd(id){
    // alert(id);
     if(confirm("您确定删除此业主信息？")){
     	<%if (permissionRights.indexOf("PERMISSION_DELETE") >= 0) {%> //权限
     		document.form1.action=path+"/servlet/DFDLservlet?action=delsite&id="+id;
      document.form1.submit();
      <%} else {%>
      alert("您没有删除站点的权限");
    <%}%>
    }
    }
    function dfinfo(id){
    	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/dfinfo.jsp?id="+id;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }
    function zlinfo(id){
    	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/zlinfo.jsp?id="+id;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }

    function modifyjz(id,intnum){
    //alert(intnum);
  	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/electricfees/updateyezhu.jsp?id="+id;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
       
    }
    function getValue(va,sql){
       var general =document.getElementById("general");
       var htmlsql =document.getElementById("htmlsql");
       general.value=va;
       htmlsql.value = sql;    
    }
    //页面载入方法
    function op(){
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian,ammeters,ammeterdegrees,electricfees&tab=zd,am,ad,ef&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
    }

var XMLHttpReq;
	//XMLHttpReq = createXMLHttpRequest();
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	
	///////////////////////////////////////////////////////////
	function sendRequest(url,para) {

		createXMLHttpRequest();
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="sheng"){
			XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
	}
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		//alert(XMLHttpReq.status);
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
            	var res = XMLHttpReq.responseText;
              window.alert(res);
             
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    
function processResponse_sheng() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            	
            	//var res = XMLHttpReq.responseText;
            
              updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateQx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZd(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}

function changeSheng(){
	var sid = document.form1.sheng.value;

	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
	 //alert("11111");
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeCity(){
	var sid = document.form1.shi.value;

	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
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

		
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
//document.form1.jzproperty.value='<%=jzproperty1%>';
//document.form1.jztype.value='<%=jztype1%>';
//document.form1.bgsign.value='<%=bgsign%>'; 
//document.form1.caijidian.value='<%=caijid%>';
document.form1.stationtype.value='<%=sitetype%>';
document.form1.qyzt.value='<%=qyzt%>';
     </script>


