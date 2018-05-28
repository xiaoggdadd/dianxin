 <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean" %>
<%@ page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>

<link href="../css/content.css" rel="stylesheet" type="text/css" />

<script>
	<%
    String path = request.getContextPath();    
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
	String roleId = (String)session.getAttribute("accountRole");    
	
	String cthrnumber = account.getCthrnumber();
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
      		//[2, AGCODE, AGNAME] update by guol2017112
			if(shilist.size()>(scount + 1)){
           		agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
			}
         }
   	} 	
    String zdname_key = request.getParameter("zdname_key")!=null?request.getParameter("zdname_key"):"";    
    String dbname_key = request.getParameter("dbname_key")!=null?request.getParameter("dbname_key"):"";
    String rcname_key = request.getParameter("rcname_key")!=null?request.getParameter("rcname_key"):"";
	String zdcode_key = request.getParameter("zdcode_key")!=null?request.getParameter("zdcode_key"):"";
	String dbcode_key = request.getParameter("dbcode_key")!=null?request.getParameter("dbcode_key"):"";
	String rccode_key = request.getParameter("rccode_key")!=null?request.getParameter("rccode_key"):"";
	String fptype_key = request.getParameter("fptype_key")!=null?request.getParameter("fptype_key"):"0";
    
    String rgsh=request.getParameter("rgsh");//站点审核通过   首页传的值
    String rgsh2=request.getParameter("caiji");// 采集站点
    String xianshibeishu= request.getParameter("xianshibeishu")!=null?request.getParameter("xianshibeishu"):"10";
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
    int count=0,pagesize,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    if(xianshibeishu=="10"||xianshibeishu==""){
    pagesize=10;
    }else{
    pagesize =Integer.parseInt(xianshibeishu);
    }
    curpage=Integer.parseInt(s_curpage);
   	String permissionRights="";         
%>
</script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<body>
<ul class="tab">
  <li class="first"></li>
  <!-- <li><a href="baozhang.jsp">报账信息</a></li> -->
  
  <li class="cur"><a href="allbz.jsp">可报账记录</a></li>
  <li><a href="allcost.jsp">报账查询</a></li>
  <li class="end"></li>
</ul>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<%
  String whereStr="";
			
		if (zdname_key != "") {
			whereStr=whereStr+" and b.zdname like '%" + zdname_key + "%'";
		}
		if (dbname_key != "") {
			whereStr=whereStr+" and b.dbname like '%" + dbname_key + "%'";
		}
		if (rcname_key != "") {
			whereStr=whereStr+" and b.rcname like '%" + rcname_key + "%'";
		}
		if (zdcode_key != "") {
			whereStr=whereStr+" and b.zdcode like '%" + zdcode_key + "%'";
		}
		if (dbcode_key != "") {
			whereStr=whereStr+" and b.dbcode like '%" + dbcode_key + "%'";
		}
		if (rccode_key != "") {
			whereStr=whereStr+" and b.rccode like '%" + rccode_key + "%'";
		}
		if(fptype_key!=""&&!"0".equals(fptype_key)){
		whereStr=whereStr+" and b.fptype ='" + fptype_key + "'";
		}
		if(cthrnumber!=null&&!cthrnumber.equals("")){
			 whereStr=whereStr+" and d.bzr='" + cthrnumber + "'";
			}
   %>
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">可报账记录</div>
				<div class="content01_1">
					<table width="800px" border="0" cellpadding="1" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="60px">实体名称：</td>
							<td align="left" width="60px">
								<input type="text" name="zdname_key" id="zdname_key" style="box-sizing: border-box; width: 130px;"/>
				         	</td>
				         	<td align="right" width="60px">电表名称：</td>
							<td align="left" width="60px">
								<input type="text" name="dbname_key" id="dbname_key" style="box-sizing: border-box; width: 130px;"/>
				         	</td>
				         	<td align="right" width="60px">成本中心名称：</td>
							<td align="left" width="60px">
								<input type="text" name="rcname_key" id="rcname_key" style="box-sizing: border-box; width: 130px;"/>
				         	</td>
				         	<td align="right" width="60px">票据类型：</td>
							<td align="left" width="100px">
								<select name="fptype_key" id="fptype_key" style="box-sizing: border-box; width:130px" class="selected_font">
												<option value="0">请选择</option>
												<%
													ArrayList fp = new ArrayList();
													fp = ztcommon.getSelctOptions("PJLX");
													if (fp != null) {
														String code = "", name = "";
														int cscount = ((Integer) fp.get(0)).intValue();
														for (int i = cscount; i < fp.size() - 1; i += cscount) {
															code = (String) fp.get(i + fp.indexOf("CODE"));
															name = (String) fp.get(i + fp.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
													}
													}
												%>

											</select>
				         	</td>
						</tr>
						<tr>
							<td align="right" width="60px">实体编码：</td>
							<td align="left" width="60px">
								<input type="text" name="zdcode_key" id="zdcode_key" style="box-sizing: border-box; width: 130px;"/>
				         	</td>
				         	<td align="right" width="60px">电表编码：</td>
							<td align="left" width="60px">
								<input type="text" name="dbcode_key" id="dbcode_key" style="box-sizing: border-box; width: 130px;"/>
				         	</td>
				         	<td align="right" width="60px">成本中心编码：</td>
							<td align="left" width="60px">
								<input type="text" name="rccode_key" id="rccode_key" style="box-sizing: border-box; width: 130px;"/>
				         	</td>
				         	<td align="right" width="60px">
								<input type="submit" class="btn_c1" onclick="query()" value="查询" /> 
							</td>
						</tr>
						
					</table>
					
					<div class="tbtitle01"><b>可报账记录查询统计一览</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
						<tr align="left">
							<td colspan="16" align="left">
							<input name="button_td" type="button" onclick="tuidan()" class="btn_c1" value="退单" />&nbsp;&nbsp;
								<input name="button2" type="button" onclick="addmss()" class="btn_c1" value="送往报账系统" />&nbsp;&nbsp;
							</td>
						</tr>
									<tr align="center">
									<th width="10"><div>勾选<input type="checkbox" name="pageCheck" onclick="checkPage()" /></div></th>
									    <th width="10">序号</th>
										<th width="50">状态</th>
										<th width="100">公司主体</th>
										<th width="80">实体名称</th>
										<th width="80">电表编号</th>
										<th width="80">票据类型</th>
										<th width="80">缴费方式</th>
										<th width="50">开始日期</th>
										<th width="50">结束日期</th>
										<th width="80">记账成本中心</th>
										<th width="50">报账金额</th>
										<th width="50">电量</th>
										<th width="50">单价</th>
										<th width="50">金额</th>
										<th width="50">税额</th>
										
									</tr>
						<%
		 SiteManage bean = new SiteManage();
       	 ArrayList fylist = new ArrayList();
         fylist = bean.getAllbz(pagesize,curpage,loginName,loginId,whereStr);
       	 DecimalFormat df1 = new DecimalFormat("0.00");
       	 allpage=bean.getAllPage();
       	 
       	String id="",state="",compname="",dbname="",dbcode="",zdname="",zdcode="";
       	String fptype="",jffs="",starttime="",endtime="",rcname="",rccode="";
       	String money="",allmoney="",faxmoney="",dianliang="",price="",isbz="",isbzname="",compcode="";
       	
       	 
       	 
		int intnum = pagesize*(curpage-1)+1;
		 System.out.println(intnum);
		 if(fylist!=null)
			{
		     int fycount =0;
		     System.out.println("fylist.size():"+fylist.size());
		     if(fylist.size()>0){
		         fycount = ((Integer)fylist.get(0)).intValue();
		        
		     }
		     System.out.println("fycount:"+fycount+fylist.size());
			for( int k = fycount;k<(fylist.size()-1);k+=fycount){
       			
			    id = (String) fylist.get(k + fylist.indexOf("ID"));
			    state = (String) fylist.get(k + fylist.indexOf("STATE"));
			    compname = (String) fylist.get(k + fylist.indexOf("COMPNAME"));
			    compcode = (String) fylist.get(k + fylist.indexOf("COMPCODE"));
			    dbname = (String) fylist.get(k + fylist.indexOf("DBNAME"));
			    dbcode = (String) fylist.get(k + fylist.indexOf("DBCODE"));
			    zdname = (String) fylist.get(k + fylist.indexOf("ZDNAME"));
			    zdcode = (String) fylist.get(k + fylist.indexOf("ZDCODE"));
			    fptype = (String) fylist.get(k + fylist.indexOf("FPTYPE"));
			    jffs = (String) fylist.get(k + fylist.indexOf("JFFS"));
			    starttime = (String) fylist.get(k + fylist.indexOf("STARTTIME"));
			    endtime = (String) fylist.get(k + fylist.indexOf("ENDTIME"));
			    rcname = (String) fylist.get(k + fylist.indexOf("RCNAME"));
		       	rccode = (String) fylist.get(k + fylist.indexOf("RCCODE"));
		       	money = (String) fylist.get(k + fylist.indexOf("MONEY"));
		       	allmoney = (String) fylist.get(k + fylist.indexOf("ALLMONEY"));
		       	faxmoney = (String) fylist.get(k + fylist.indexOf("FAXMONEY"));
		       	dianliang = (String) fylist.get(k + fylist.indexOf("DIANLIANG"));
		       	price = (String) fylist.get(k + fylist.indexOf("PRICE"));
		       	isbz = (String) fylist.get(k + fylist.indexOf("ISBZ"));
       			
				if(state.equals("0")){
				    isbzname="未报账";
				}else if(state.equals("1")){
				    isbzname="已报账";
				}
				else if(state.equals("2")){
				    isbzname="重新报账";
				}
       			if (intnum % 2 == 0) {
       				color = "#DDDDDD";

       			} else {
       				color = "#FFFFFF";
       			}
       		
       			
       %>
      
									<!-- 数据加载  Start-->
									<tr align="center">
									<%if(state.equals("0")){ %>
										<td>
											<input type="checkbox" name="itemSelected"
													value="<%=id%>" />
										</td>
										<%}else{ %>
										<td>
											
										</td>
										<%} %>
										<td width="10"><%=intnum++%>
											<input type="hidden" value="<%=compcode%>"  id="c<%=id%>" name="c<%=id%>" />
											<input type="hidden" value="<%=rccode%>"   id="r<%=id%>" name="r<%=id%>" />
										</td>
										<td align="center"><%=isbzname%></td>
										<td align="center"><%=compname%></td>
										<td align="center"><%=zdname%></td>
										<td align="center"><%=dbcode%></td>
										<td align="center"><%=fptype%></td>
										<td align="center"><%=jffs%></td>
										<td align="center"><%=starttime.substring(0,10)%></td>
										<td align="center"><%=endtime.substring(0,10)%></td>
										<td align="center"><%=rcname%></td>
										<td align="center"><%=money%></td>
										<td align="center"><%=dianliang%></td>
										<td align="center"><%=price%></td>
										<td align="center"><%=allmoney%></td>
										<td align="center"><%=faxmoney%></td>
										
									</tr><% } %>
 
									<!-- 数据加载 End -->
									
									<tr bgcolor="#ffffff">
					<td colspan="16" align="left" >
						<div align="left">
<!-- 							<font color='#1E90FF'>&nbsp;页次:</font> -->
<!-- 							&nbsp;&nbsp; -->
<!-- 							 <b><font color=red><%=curpage%></font></b> -->

<!-- 							 <font color='#1E90FF'>/<b><%=allpage%></b>&nbsp;</font> -->
							 &nbsp;&nbsp;
							 <font color='#1E90FF'>
						     <%
						     	if (curpage != 1) {
						     			out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
						     		}
						     %>
					     </font>
					     &nbsp;&nbsp;
							 <font color='#1E90FF'>
						     <%
						     	if (curpage != 1) {
						     			out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
						     		}
						     %>
						   </font>
						   &nbsp;&nbsp;
						   转到 <select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" style="width:40px;font-family: 宋体;font-size:12px;line-height:120%;" >
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
				    </select>&nbsp;&nbsp;共 <font color='#1E90FF'><b><%=allpage%></b>&nbsp;</font>页 &nbsp;&nbsp;
							<font color='#1E90FF'>
						     <%
						     	if (allpage != 0 && (curpage < allpage)) {
						     			out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");
						     		}
						     %>
             				</font>
							&nbsp;&nbsp;							
							<font color='#1E90FF'>
					     <%
					     	if (allpage != 0 && (curpage < allpage)) {
					     			out.print("<a href='javascript:gopagebyno(" + allpage+ ")'><img src='../images/page-last.gif'></a>");
					     		} else {
					     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
					     		}
					     %>
            </font>
            &nbsp;&nbsp;
           <input name="button2" type="button" onclick="uppageyeshu()" class="btn_c1" value="查询全部" />
<!-- 						<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" style="width:70px;font-family: 宋体;font-size:12px;line-height:120%;" > -->
<!-- 					     < -->
<!-- 					     	for (int i = 1; i <= allpage; i++) { -->
<!-- 					     			if (curpage == i) { -->
<!-- 					     				out.print("<option value='" + i -->
<!-- 					     						+ "' selected='selected'>" + i + "</option>"); -->
<!-- 					     			} else { -->
<!-- 					     				out.print("<option value='" + i + "'>" + i -->
<!-- 					     						+ "</option>"); -->
<!-- 					     			} -->
<!-- 					     		} -->
<!-- 					     %> -->
<!-- 				    </select> -->


						</div>
					</td>
				</tr>
								
								</table>
						<div class="space_h_10"></div>
				</div>
			</div>
		</td></tr>
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
	var path = '<%=path%>';
	function checkPage() {
	if (document.form1.pageCheck.checked) {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = true
		}
	} else {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = false
		}
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
      		document.form1.action=path+"/web/sdttWeb/baozhang/allbz.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      		document.form1.action=path+"/web/sdttWeb/baozhang/allbz.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = pageno;   
      document.form1.action=path+"/web/sdttWeb/baozhang/allbz.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+pageno;
      document.form1.submit();
     }
     function uppageyeshu()
     {
     var xianshibeishu=1024;
      document.form1.action=path+"/web/sdttWeb/baozhang/allbz.jsp?xianshibeishu="+xianshibeishu;
      document.form1.submit();
     }
     function delzd(id){
    // alert(id);
     if(confirm("您确定删除此站点信息？")){
     	//<if (permissionRights.indexOf("PERMISSION_DELETE") >= 0) {%> //权限
     		document.form1.action=path+"/servlet/zhandian?action=delsite&id="+id;
      		document.form1.submit();
      //<} else {%>
      //alert("您没有删除站点的权限");
    //<}%>
    }
    }
    function xiangxi(id){
    	window.open("../jizan/xxquery.jsp?id="+id, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
    	showdiv("请稍等..........");
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
function addjz(){
	window.open("../jizan/addSite.jsp", "newwindow", "height=530, width=1250, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
	showdiv("请稍等..........");
}
function daochu(){
	document.form1.action=path+"/servlet/CbDownload?bz=site";
	document.form1.submit();
}
function addmss(){
	var obj=document.getElementsByName('itemSelected');
	var s='';  var isComp=0; var isRc=0;
	for(var k=0; k<obj.length; k++){ 
		if(s==""){
			if(obj[k].checked) s+=obj[k].value; //如果选中，将value添加到变量s中 
		}else{
			if(obj[k].checked) s+=","+obj[k].value; //如果选中，将value添加到变量s中 
		}
	} 
	if(s!=""){
		var arrs = s.split(",");
		for(var j=1; j<arrs.length; j++){
				if($("#c"+arrs[j]).val() != $("#c"+arrs[j-1]).val()){
					isComp=1;
					break;
				}
				if($("#r"+arrs[j]).val() != $("#r"+arrs[j-1]).val()){
					isRc=1;
					break;
				}
		} 
	}
	
	if(isComp==1){
		alert("公司主体不相同,无法进行推送！");
		return;
	}else if(isRc==1){
		alert("报账成本中心不相同,无法进行推送！");
		return;
	}else if (s!="") {
		if (confirm("确定要对这些信息进行发起报账吗？")) {
		//	document.form1.action=path+"/servlet/zhandian?action=addAllbz";
		//	document.form1.submit();
			window.open("addbz.jsp?ids="+s, "newwindow", "height=530, width=1250, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
			showdiv("请稍等..........");
		} else {
			return;
		}
	} else {
		alert("请选择要发起报账的信息！");
		return;
	}
}
function tuidan(){
	var obj=document.getElementsByName('itemSelected');
	var s='';  var isComp=0; var isRc=0;
	for(var k=0; k<obj.length; k++){ 
		if(s==""){
			if(obj[k].checked) s+=obj[k].value; //如果选中，将value添加到变量s中 
		}else{
			if(obj[k].checked) s+=","+obj[k].value; //如果选中，将value添加到变量s中 
		}
	} 
	
		//退单
		if (s!="") {
		if (confirm("确定要对这些信息进行退单操作吗？")) {
		//	document.form1.action=path+"/servlet/zhandian?action=addAllbz";
		//	document.form1.submit();
			//window.open("addbz.jsp?ids="+s, "newwindow", "height=530, width=1250, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
			//可报账退单操作 
			document.form1.action=path+"/bz_solo_servlet?action=kbztd&id="+s;
      		document.form1.submit();	
		} else {
			return;
		}
	} else {
		alert("请选择要退单的信息！");
		return;
	}
}
function query(){
	document.form1.action=path+"/web/sdttWeb/baozhang/allbz.jsp";
	document.form1.submit();
}

document.form1.zdname_key.value='<%=zdname_key%>';
document.form1.dbname_key.value='<%=dbname_key%>';
document.form1.zdcode_key.value='<%=zdcode_key%>';
document.form1.dbcode_key.value='<%=dbcode_key%>';
document.form1.fptype_key.value='<%=fptype_key%>';
</script>


