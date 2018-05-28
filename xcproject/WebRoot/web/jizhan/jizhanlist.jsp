 <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>

<%
    String jztype1 = request.getParameter("jztype")!=null?request.getParameter("jztype"):"0";
    String jzproperty1 = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
    String bgsign = request.getParameter("bgsign")!=null?request.getParameter("bgsign"):"-1";
  
 
  
	String sheng = (String)session.getAttribute("accountSheng");
	String roleId = (String)session.getAttribute("accountRole");
        String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
        String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
        String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
	String sname = request.getParameter("sname")!=null?request.getParameter("sname"):"";
	String szdcode = request.getParameter("szdcode")!=null?request.getParameter("szdcode"):"";
        String path = request.getContextPath();
        
        String rgsh=request.getParameter("rgsh");//站点审核通过   首页传的值
        String rgsh2=request.getParameter("caiji");/// 采集站点
      
      
       // String cai=request.getParameter("cai");/// 翻页采集站点
        String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
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
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 .btt{ bgcolor:#888888;}
  .bttcn{ color:white;font-weight:bold;}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
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
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

		}
		else
		{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
}

  function chaxun(){
					document.form1.action=path+"/web/jizhan/jizhanlist.jsp";
					document.form1.submit();
	}
	function delLogs(){
		

	}
	function addJz(){
		document.form1.action=path+"/web/jizhan/addjz.jsp";
		document.form1.submit();
	}
	function daorujz(){
		document.form1.action=path+"/web/jizhan/zhandiandaoru.jsp?servletname=zhandiandaoru&action=upzhandian";
		document.form1.submit();
	}
	$(function(){
		$("#add").click(function(){
			addJz();
		});
		$("#import").click(function(){
			daorujz();
		});
		$("#query").click(function(){
			chaxun();
		});
	});
</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<%
	permissionRights = commBean.getPermissionRight(roleId, "0101");

%>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="../../images/css.css" type=text/css rel=stylesheet>
	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">


	<tr> <td width="10" height="500">&nbsp;</td>
   
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4"></td>
						</tr>
						<tr>
											<td colspan=1 width="700"
												background="<%=path%>/images/btt.bmp" height=63>
												<span style="color: black; font-weight: bold;">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;站点信息列表</span>
											</td>

											<td width="380">&nbsp;</td>
						</tr>
					</table>
					<br>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="4"></td>
								<td>
									<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" bgcolor="7C92B7">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <div style="width:50px;display:inline;"><hr></div>
                          &nbsp;过滤条件&nbsp;
                          <div style="width:300px;display:inline;"><hr></div>
				<tr>
         <td colspan=3>
         
         城市： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <select name="shi" id="shi" style="width:130;" onchange="changeShi()">
         		<option value="0">请选择</option>
         		<%
         			ArrayList shilist = new ArrayList();
         			shilist = commBean.getAgcode(sheng, shi, loginName);
         			if (shilist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) shilist.get(0)).intValue();
         				for (int i = scount; i < shilist.size() - 1; i += scount) {
         					agcode = (String) shilist
         							.get(i + shilist.indexOf("AGCODE"));
         					agname = (String) shilist
         							.get(i + shilist.indexOf("AGNAME"));
         		%>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    	}
                    %>
         		</select>
         区县： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <select name="xian" id="xian" style="width:130;"  onchange="changeXian()">
         		<option value="0">请选择</option>
         		<%
         			ArrayList xianlist = new ArrayList();
         			shilist = commBean.getAgcode(shi, xian, loginName);
         			if (shilist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) shilist.get(0)).intValue();
         				for (int i = scount; i < shilist.size() - 1; i += scount) {
         					agcode = (String) shilist
         							.get(i + shilist.indexOf("AGCODE"));
         					agname = (String) shilist
         							.get(i + shilist.indexOf("AGNAME"));
         		%>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    	}
                    %>
         	</select>
         	乡镇： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         	<select name="xiaoqu" id="xiaoqu" style="width:130">
         		<option value="0">请选择</option>             
         		<%
         			ArrayList xiaoqulist = new ArrayList();   //下拉列表
         			xiaoqulist = commBean.getAgcode(xian, xian, loginName);
         			if (xiaoqulist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) xiaoqulist.get(0)).intValue();
         				for (int i = scount; i < xiaoqulist.size() - 1; i += scount) {
         					agcode = (String) xiaoqulist.get(i
         							+ xiaoqulist.indexOf("AGCODE"));
         					agname = (String) xiaoqulist.get(i
         							+ xiaoqulist.indexOf("AGNAME"));
         		%>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    	}
                    %>
         	</select>
         	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;站点属性：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         	<select name="jzproperty" style="width:130" onchange="kzinfo()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = ztcommon.getSelctOptions("zdsx");
	         	if(zdsx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdsx.get(0)).intValue();
	         		for(int i=cscount;i<zdsx.size()-1;i+=cscount)
                    {
                    	code=(String)zdsx.get(i+zdsx.indexOf("CODE"));
                    	name=(String)zdsx.get(i+zdsx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
         	%>
         	</select><br/>
         	
         	是否标杆：	
         	<select name="bgsign" style="width:130">
         	  <option value="-1">请选择</option>
         	   <option value="0">否</option>
         		<option value="1">是</option>
         		
         	</select>
         	
         	
         	
         	
         		
         			站点名称：
         <input type="text" name="sname" value="<%=sname%>"  style="width:130px"/>
         站点代码：
         <input type="text" name="szdcode" value="<%=szdcode%>" style="width:130px"/>
         &nbsp;&nbsp;&nbsp;&nbsp;
        
         	集团报表类型：
         	<select name="jztype" style="width:130"  onchange="kzinfo()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdtype = new ArrayList();
	         	zdtype = ztcommon.getSelctOptions("zdlx");
	         	if(zdtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdtype.get(0)).intValue();
	         		for(int i=cscount;i<zdtype.size()-1;i+=cscount)
                    {
                    	code=(String)zdtype.get(i+zdtype.indexOf("CODE"));
                    	name=(String)zdtype.get(i+zdtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         
         
         
         
         
         <%
         	if (permissionRights.indexOf("PERMISSION_SEARCH") >= 0) {//返回该字符串第一次出现的索引       如果这个角色有查询的权限  就执行下面的
         %>
         <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:90%;TOP:-23PX">
						<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
						<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span><!-- 在图片上写字 -->
					</div>
         		<%
         			} 
         		%>
         </td>
      </tr>
  </table>
                      <div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>

  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#666666">
         <tr ><td width="35" height="23" bgcolor="#888888"><div align="center" class="bttcn">序号</div></td>
              <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点代码</div></td>
              <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点名称</div></td>
              <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">所在地区</div></td>
              <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点属性</div></td>
              <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">集团报表类型</div></td>
  			  <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">用房类型</div></td>
              <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">节能设备</div></td>
              <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">供电方式</div></td>
              <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点面积</div></td>
              <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">电费</div></td>
              <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">租赁</div></td>
              <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">删除</div></td>
              <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">修改</div></td>
        </tr>
       <%
       JiZhanBean bean = new JiZhanBean();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageData(curpage,sheng,shi,xian,xiaoqu,sname,szdcode,loginName,"0",loginId,jztype1,jzproperty1,bgsign,rgsh,rgsh2);
       	 allpage=bean.getAllPage();
		String jzname = "",szdq = "",jzproperty = "", jztype= "",yflx = "",jnglmk="",gdfs="",area="",id="",dianfei="",zdcode="",xunisign="",shsignxs="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

       			//num为序号，不同页中序号是连续的
       			id = (String) fylist.get(k + fylist.indexOf("ID"));
       			jzname = (String) fylist.get(k + fylist.indexOf("JZNAME"));
       			szdq = (String) fylist.get(k + fylist.indexOf("SZDQ"));
       			jzproperty = (String) fylist.get(k
       					+ fylist.indexOf("PROPERTY"));
       			jztype = (String) fylist.get(k + fylist.indexOf("JZTYPE"));
       			yflx = (String) fylist.get(k + fylist.indexOf("YFLX"));
       			jnglmk = (String) fylist.get(k + fylist.indexOf("JNGLMK"));
       			if(jnglmk.equals("0")){
					 jnglmk="否";
					}else if(jnglmk.equals("1")){
					jnglmk="是";
					}
       			gdfs = (String) fylist.get(k + fylist.indexOf("GDFS"));
       			area = (String) fylist.get(k + fylist.indexOf("AREA"));
       			dianfei = (String) fylist
       					.get(k + fylist.indexOf("DIANFEI"));
       			zdcode = (String) fylist.get(k + fylist.indexOf("ZDCODE"));
       			xunisign = (String) fylist.get(k
       					+ fylist.indexOf("XUNISIGN"));
       			shsignxs = (String) fylist
       					.get(k + fylist.indexOf("SHSIGN"));
       			if (zdcode==null||zdcode.equals("")||zdcode.equals("null"))
       				zdcode = "";
       			if (area==null||area.equals("")||area.equals("null"))
       				area = "";
       			if (dianfei==null||dianfei.equals("")||dianfei.equals("null"))
       				dianfei = "";
       			if (jztype==null||jztype.equals("")||jztype.equals("null"))
       				jztype = "";
       			if (jzproperty==null||jzproperty.equals("")||jzproperty.equals("null"))
       				jzproperty = "";
       			if (yflx==null||yflx.equals("")||yflx.equals("null"))
       				yflx = "";
       			if (gdfs==null||gdfs.equals("")||gdfs.equals("null"))
       				gdfs = "";
       			String color = null;

       			if (intnum % 2 == 0) {
       				color = "#DDDDDD";

       			} else {
       				color = "#FFFFFF";
       			}
       			if (shsignxs.equals("0")) {
       				color = "#FFFF33";
       			} else if (shsignxs.equals("2")) {
       				color = "#FF3333";
       			}
       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=intnum++%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=zdcode%></div>
       		</td>
       		<td>
       			<div align="left"  ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=jzproperty%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=jztype%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=yflx%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=jnglmk%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=gdfs%></div>
       		</td>
       		<td>
       			<div align="right"  ><%=area%> </div>
       		</td>
       	
       	<td>
       			<div align="center"  ><a href="#" onclick="dfinfo('<%=id%>')">电费</a></div>
       		</td>
       		<td>
       			<div align="center"  ><a href="#" onclick="zlinfo('<%=id%>')">租赁</a></div>
       		</td>
       		<td>
       			<div align="center" ><a href="#" onclick="delzd('<%=id%>')">删除</a></div>
       		</td>
       		<td>
       			<div align="center" ><a href="#" onclick="modifyjz('<%=id%>')">修改</a></div>
       		</td>

       </tr>
       <%
       	}
       %>
       <tr bgcolor="#ffffff"  >
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
						<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" class="form" >
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
  	 
  	 
  	 
											</td>
										</tr>
										<tr bgcolor="#FFFFFF">
											
											<td align="right">
												<div id="parent" style="display:inline" align="right">
                          <div style="width:300px;display:inline;" align="right"><hr></div>
                      </div>
											</td>
											
										</tr>
										
										<tr bgcolor="#FFFFFF">
											
											<td>
												

												<div align="right">
													<%
														if (permissionRights.indexOf("PERMISSION_ADD") >= 0) {
													%>
													<div id="import" style="position:relative;width:60px;height:23px;cursor: pointer;right:3px;float:right;">
							                            <img alt="" src="<%=request.getContextPath() %>/images/daoru.png" width="100%" height="100%" />
							                            <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">导入</span>
													</div>
													<div id="add" style="position:relative;width:60px;height:23px;cursor: pointer;float:right;right:6px">
							                            <img alt="" src="<%=request.getContextPath() %>/images/xinzeng.png" width="100%" height="100%" />
							                            <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">新增</span>
													</div>
													
							        	<%
							        		}
							        	%>
							        </div>
											</td>
										</tr>
									</table>
								</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
      <br />
     
    </td>
    <td background="../../images/img_13.gif">&nbsp;</td>
  </tr>
 
</table>
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
        var curpage='<%=(curpage - 1)%>';
      		document.form1.action=path+"/web/jizhan/jizhanlist.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      		document.form1.action=path+"/web/jizhan/jizhanlist.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/jizhan/jizhanlist.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+pageno;
      document.form1.submit();
     }
     function delzd(id){
          	<%if (permissionRights.indexOf("PERMISSION_DELETE") >= 0) {%>
          		if(confirm("您确认要删除本条站点信息？")){
	     			document.form1.action=path+"/servlet/zhandian?action=del&id="+id
	      			document.form1.submit();
	      		}
      		<%} else {%>
      			alert("您没有删除站点的权限");
    		<%}%>
    }
    function dfinfo(id){
    	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/dfinfo.jsp?id="+id
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }
    function zlinfo(id){
    	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/zlinfo.jsp?id="+id
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }
    function modifyjz(id){
    	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/modifyjz.jsp?id="+id
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
    function changeSheng(){
			var sheng = document.form1.sheng.value;
			var xianlist = document.all.xian;
				xianlist.options.length="0";
				xianlist.add(new Option("请选择","0"));
			if(sheng=="0"){
				var shilist = document.all.shi;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				
				return;
			}else{
				sendRequest(path+"/servlet/area?action=sheng&pid="+sheng,"sheng");
			}
		}
		function updateShi(res){
			var shilist = document.all.shi;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		function changeShi(){
			var shi = document.form1.shi.value;
			var xiaoqu = document.all.xiaoqu;
				xiaoqu.options.length="0";
				xiaoqu.add(new Option("请选项","0"));
			if(shi=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=shi&pid="+shi,"shi");
			}
		}
		function updateQx(res){
			var shilist = document.all.xian;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
			function changeXian(){
			var shi = document.form1.xian.value;
			if(shi=="0"){
				var shilist = document.all.xiaoqu;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=xian&pid="+shi,"xian");
			}
		}
		
		function updateXQ(res){
			var shilist = document.all.xiaoqu;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
document.form1.jzproperty.value='<%=jzproperty1%>';
document.form1.jztype.value='<%=jztype1%>';
document.form1.bgsign.value='<%=bgsign%>';

     </script>


