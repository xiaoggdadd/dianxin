<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>铁塔报账</title>


<link href="../css/content.css" rel="stylesheet" type="text/css" />

<script>
	<%
	//获取PAth
    String path = request.getContextPath();    
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    
	String rolename = account.getRoleName();
    //获取
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
   		CommonBean commBean =new CommonBean();
   		shilist = commBean.getAgcode(sheng,account.getAccountId());
		if(shilist!=null){
      		int scount = ((Integer)shilist.get(0)).intValue();
           agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
        }
   	} 	
   	String baosongtime = request.getParameter("baosongtime") != null ? request
					.getParameter("baosongtime")
					: "";
	String people = request.getParameter("people")!=null?request.getParameter("people"):"";
	String txtDianbiao = request.getParameter("txtDianbiao")!=null?request.getParameter("txtDianbiao"):"";
	String txtKeyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"";
    String rgsh=request.getParameter("rgsh");//站点审核通过   首页传的值
    String rgsh2=request.getParameter("caiji");// 采集站点
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
    int count=0,pagesize=10,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
   	String permissionRights="";     
%>
</script>
<!--[if gt IE 8]><!-->
<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"></script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
<!--<![endif]-->
<!--[if lte IE 8]>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/console.js"></script>
<![endif]-->

<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
</head>
<!-- 显示时间input控件 -->
<%
		//String path = request.getContextPath();
	%>
<script type="text/javascript"
	src="<%=path%>/javascript/PopupCalendar.js">
	</script>

<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
	</script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js">
	</script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
	</script>
<!-- fxl 2018/1/11(时间控件) -->
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page"
	class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<jsp:useBean id="bean" scope="page"
	class="com.noki.mobi.workflow.javabean.WorkFlowBean">
</jsp:useBean>
<body onload="showPLBL()">
	<%-- 
<ul class="tab">
  <li class="first"></li>
  <li><a href="baozhang.jsp">报账信息</a></li>
  <li class="cur"><a href="bzsh.jsp">报账审核</a></li>
  <li><a href="allbz.jsp">可报账记录</a></li>
  <li><a href="allcost.jsp">报账查询</a></li>
  <li class="end"></li>
</ul>--%>
	<form action="" name="form1" method="post">
		<% 
			int l =bean.getACCOUNTrenname(rolename);
         	String ACCOUNTrenname="";
          %>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<%
  String whereStr="";
  String str="";
  String xuni="0";
  
	

  if (txtDianbiao != "") {
	  //电表名称
		whereStr=whereStr+" and d.DBNAME like '%" + txtDianbiao + "%'";
		
	}
  if(!txtKeyword.equals("")){
	  //站点名称
	  whereStr=whereStr+" and z.jzname like '%" + txtKeyword + "%'";		
	}
  if(!people.equals("")){
	  //报送人
	  whereStr=whereStr+" and a.name like '%" + people + "%'";		
	}
if (!baosongtime.equals("")) {
//报送时间
				whereStr = whereStr + " and  to_char (w.applytime,'yyyy-MM-dd')like '%"
						+ baosongtime + "%'";
			}

   %>
			<tr valign="top">
				<td width="12"><img src="../images/space.gif" width="12"
					height="17" />
				</td>
				<td>
					<div class="content01">
						<div class="tit01">铁塔报账待审核统计一览</div>
						<div class="content01_1">
							<table border="0" cellpadding="1" cellspacing="0"
								class="tb_select">
								<tr>
									<td align="right" width="60px">电表名称：</td>
									<td align="left" width="60px"><input type="text"
										name="txtDianbiao" id="txtDianbiao"
										style="box-sizing: border-box; width: 130px;" /></td>
									<td align="right" width="60px">实体名称：</td>
									<td align="left" width="60px"><input type="text"
										name="txtKeyword" id="txtKeyword" value=""
										style="box-sizing: border-box; width: 130px;" /></td>
									<td align="right" width="60px">报送人：</td>
									<td align="left" width="60px"><input type="text"
										name="people" id="people" value=""
										style="box-sizing: border-box; width: 130px;" /></td>
									<td align="right" width="100px">报送时间：</td>
									<td width="100px">
										<!-- 显示时间的input --> <input class="selected_font" type="text"
										name="baosongtime" value="" readonly="readonly" class="Wdate"
										style="background-color: #FFFFFF; color: grey"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
									</td>
									<td align="right" colspan="8"><input type="submit"
										class="btn_c1" onclick="query()" value="查询" /></td>
								</tr>
							</table>

							<div class="tbtitle01">
								<b>铁塔报账信息统计一览</b>
							</div>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="tb_list1">
								<tr align="right" id="strTr" style="display: none">
									<td colspan="14" align="right"><input name="button2"
										type="submit" onclick="plbl(0)" class="btn_c1" value="批量通过" />&nbsp;&nbsp;
										<input name="button2" type="submit" onclick="plbl(1)"
										class="btn_c1" value="批量驳回" /></td>
								</tr>
								<tr align="center">
									<th width="10">序号</th>
									<th width="100">实体名称</th>
									<!--  <th width="80">电表名称</th>  -->
									<th width="80">开始日期</th>
									<th width="80">结束日期</th>
		            				<th width="80">用电量(度)</th>
									<th width="80">单价(元/度)</th>
									<th width="80">电费金额(元)</th>
									<%if(l==1||l==2){%>
									<th width="80">日均用电量</th>
									<th width="80">PUE</th>
									<th width="80">标杆电量</th>
									<th width="80">标杆偏离率</th>
									<%} 
												if(l==2){%>
									<th width="80">去年同期日均</th>
									<th width="80">上期日均用电量</th>
									<%} %>
									<th width="30">操作</th>
									<!--  <th width="10"><div>
											勾选<input type="checkbox" name="pageCheck"
												onclick="checkPage()" />
										</div>
									</th> -->
								</tr>
								<%
       	 ArrayList fylist = new ArrayList();
         fylist =bean.getTietaPageData(curpage,"ELECTRICFEES",loginId,whereStr);
       	 DecimalFormat df1 = new DecimalFormat("0.00");
       	 SiteManage bea = new SiteManage();
       	 allpage=bean.getAllPage();
       	double a=0;
		String workflowId="", dbId="",taskId="", startTime = "",endTime = "",ENTRYTIMEOLD = "", SQRJYDL= "",RJYDL = "";
		String DIANLIANG="",ALLMONEY="",ENTRYPENSONNEL="",zdname="",DBNAME="",currentaction="";
		String price="",bgdl="",bgpll="",qnrj="",PUEZHI="";
		int intnum = pagesize*(curpage-1)+1;
		 System.out.println(intnum);
		 if(fylist!=null)
			{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
       			
				workflowId = (String) fylist.get(k + fylist.indexOf("ID"));
				taskId = (String) fylist.get(k + fylist.indexOf("TASK_ID"));
				startTime = (String) fylist.get(k + fylist.indexOf("STARTTIME"));
				endTime = (String) fylist.get(k + fylist.indexOf("ENDTIME"));
				ENTRYTIMEOLD = (String) fylist.get(k + fylist.indexOf("ENTRYTIMEOLD"));
				SQRJYDL = (String) fylist.get(k + fylist.indexOf("SQRJYDL"));
				RJYDL = (String) fylist.get(k + fylist.indexOf("RJYDL"));
				DIANLIANG = (String) fylist.get(k + fylist.indexOf("DIANLIANG"));//电量
				ALLMONEY = (String) fylist.get(k + fylist.indexOf("ALLMONEY"));//总金额
				ENTRYPENSONNEL = (String) fylist.get(k + fylist.indexOf("ENTRYPENSONNEL"));
				zdname = (String) fylist.get(k + fylist.indexOf("ZDNAME"));//金额
				DBNAME = (String) fylist.get(k + fylist.indexOf("DBNAME"));//税额
				currentaction = (String) fylist.get(k + fylist.indexOf("CURRENTACTION"));
				price = (String) fylist.get(k + fylist.indexOf("PRICE"));
				Double cny = Double.parseDouble(price);
				DecimalFormat df = new DecimalFormat("0.0000");   
				String CNY = df.format(cny);
				bgdl = (String) fylist.get(k + fylist.indexOf("BGDL"));
				qnrj = (String) fylist.get(k + fylist.indexOf("QNRJ"));
				PUEZHI = (String) fylist.get(k + fylist.indexOf("PUEZHI"));
				bgpll = (String) fylist.get(k + fylist.indexOf("BGPLL"));
				if(bgpll != ""||bgpll != null){
				}else{
				if(bgdl!=null && !bgdl.equals("") && DIANLIANG!=null && !DIANLIANG.equals("")){
					bgpll = ""+ df1.format(
							(Double.valueOf(DIANLIANG).doubleValue() -
									Double.valueOf(bgdl).doubleValue())
									/Double.valueOf(bgdl).doubleValue());
				}}
				
				
				if(currentaction==null||currentaction.equals("")){
					currentaction="调整任务";
				}
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
				
				
				String STARTTIMEStr="",ENDTIMEStr="",ENTRYTIMEOLDStr="";
				if(startTime!="" && !startTime.equals("")){
					java.util.Date STARTTIMEDate=sdf.parse(startTime);
					STARTTIMEStr = sdf.format(STARTTIMEDate); //开始日期
				}
				if(endTime!="" && !endTime.equals("")){
					java.util.Date ENDTIMEDate=sdf.parse(endTime);
					ENDTIMEStr = sdf.format(ENDTIMEDate);
				}
				if(ENTRYTIMEOLD!="" && !ENTRYTIMEOLD.equals("")){
					java.util.Date ENTRYTIMEOLDDate=sdf.parse(ENTRYTIMEOLD);
					ENTRYTIMEOLDStr = sdf.format(ENTRYTIMEOLDDate);
				}
				

       			if (intnum % 2 == 0) {
       				color = "#DDDDDD";

       			} else {
       				color = "#FFFFFF";
       			}
       			
       %>
								<!-- 数据加载  Start-->
								<tr align="center">
									<td width="10"><%=intnum++%></td>
									<td align="center"><%=zdname%></td>
									<!-- <td align="center"><%=DBNAME%></td> -->
									<td align="center"><%=STARTTIMEStr%></td>
									<td align="center"><%=ENDTIMEStr%></td>
									<td align="center"><%=DIANLIANG%></td>
									<td align="center"><%=CNY%></td>
									<td align="right"><%=ALLMONEY %></td>
									<%if(l==1||l==2){%>
									<td align="right"><%=RJYDL%></td>
									<td align="right"><%=PUEZHI%></td>
									<td align="right"><%=bgdl%></td>
									<td align="right"><%=bea.number(bgpll)%></td>
									<%} 
											if(l==2){%>
									<td align="right"><%=qnrj%></td>
									<td align="right"><%=SQRJYDL%></td>
									<%} %>
									<td width="80"><a href="#"
										onclick="banli('<%=workflowId%>')"><img height="16"
											width="16" src="../images/accept.png" title="审核" />
									</a></td>
									<!-- <td><input type="checkbox" name="itemSelected"
										value="<%=workflowId%>" /></td>  -->
								</tr>
								<% } %>

								<!-- 数据加载 End -->

								<tr bgcolor="#ffffff">
									<td colspan="16" align="left">
										<div align="left">
											<!-- 							<font color='#1E90FF'>&nbsp;页次:</font> -->
											<!-- 							&nbsp;&nbsp; -->
											<!-- 							 <b><font color=red><%=curpage%></font></b> -->

											<!-- 							 <font color='#1E90FF'>/<b><%=allpage%></b>&nbsp;</font> -->
											&nbsp;&nbsp; <font color='#1E90FF'> <%
						     	if (curpage != 1) {
						     			out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
						     		}
						     %> </font> &nbsp;&nbsp; <font color='#1E90FF'> <%
						     	if (curpage != 1) {
						     			out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
						     		}
						     %> </font> &nbsp;&nbsp; 转到 <select name="page"
												onChange="javascript:gopagebyno(document.form1.page.value)"
												style="width:40px;font-family: 宋体;font-size:12px;line-height:120%;">
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
											</select>&nbsp;&nbsp;共 <font color='#1E90FF'><b><%=allpage%></b>&nbsp;</font>页
											&nbsp;&nbsp; <font color='#1E90FF'> <%
						     	if (allpage != 0 && (curpage < allpage)) {
						     			out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");
						     		}
						     %> </font> &nbsp;&nbsp; <font color='#1E90FF'> <%
					     	if (allpage != 0 && (curpage < allpage)) {
					     			out.print("<a href='javascript:gopagebyno(" + allpage+ ")'><img src='../images/page-last.gif'></a>");
					     		} else {
					     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
					     		}
					     %> </font> &nbsp;&nbsp;


										</div></td>
								</tr>

							</table>
							<div class="space_h_10"></div>
						</div>
					</div></td>
			</tr>
			<% } %><!-- else {%>-->
			<!--  <tr align="center" >
			<td align="left" colspan="9">
			当前无数据。
			</td>
		</tr>-->

		</table>
	</form>
</body>
</html>
<script>
function showPLBL(){
	var currentaction='<%=currentaction%>';
	if(currentaction=='市级主任审核'){
		$("#strTr").attr("style", "display:none;");
	}else{
		$("#strTr").attr("style", "display:none;");
	}
}
var path = '<%=path%>';
function banli(workFlowId,taskType){
		window.open("TietaBZSP.jsp?workFlowId="+workFlowId, "newwindow", "height=650, width=1300, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
}	
function checkPage() {
	if (document.form1.pageCheck.checked) {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = true;
		}
	} else {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = false;
		}
	}
}
function plbl(n) {
	var msg="";
	if(n==0){
		msg="通过";
	}else{
		msg="驳回";
	}
	var i = 0;

	for ( var j = 0; j < document.form1.elements.length; j++) {
		if (document.form1.elements[j].checked) {
			i++;
		}
	}
	if (i > 0) {
		if (confirm("确定要"+msg+"这些任务么？")) {
			document.form1.action = path + "/servlet/workflow?action=plbl&n="+n;
			document.form1.submit();
		} else {
			return;
		}
	} else {
		alert("请选择要办理的任务");
		return;
	}
}
	
	
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
      		document.form1.action=path+"/web/sdttWeb/baozhang/Tietabzsh.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      		document.form1.action=path+"/web/sdttWeb/baozhang/Tietabzsh.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = pageno;   
      document.form1.action=path+"/web/sdttWeb/baozhang/Tietabzsh.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+pageno;
      document.form1.submit();
     }
     function delbz(id){
    // alert(id);
     if(confirm("您确定删除此报账信息？")){
     	//<if (permissionRights.indexOf("PERMISSION_DELETE") >= 0) {%> //权限
     		document.form1.action=path+"/servlet/zhandian?action=delbaozhang&id="+id;
      		document.form1.submit();
      //<} else {%>
      //alert("您没有删除站点的权限");
    //<}%>
    }
    }
    function xiangxi(id){
    	window.open("../baozhang/xxquery.jsp?id="+id, "newwindow", "height=300, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
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
     		document.form1.action=path+"/web/jizhan/modifysite.jsp?id="+id+"&nums="+intnum;
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
		shilist.add(new Option("请选择","0"));
		return;
	}else{
	 //alert("11111");
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

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
function changeCity(){
	var sid = document.form1.shi.value;
	
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
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

function changeCountry(){
	var sid = document.form1.xian.value;
	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function query(){
	document.form1.action=path+"/web/sdttWeb/baozhang/Tietabzsh.jsp";
	document.form1.submit();
}
document.form1.people.value='<%=people%>';
document.form1.txtDianbiao.value='<%=txtDianbiao%>';
document.form1.txtKeyword.value='<%=txtKeyword%>';

</script>


