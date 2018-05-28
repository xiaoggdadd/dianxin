<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script>
	<%
        String path = request.getContextPath();
        String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
        String accountname=account.getAccountName();
        String loginId=account.getAccountId();
		String sheng = (String)session.getAttribute("accountSheng");
		String roleId = (String)session.getAttribute("accountRole");
		
		String agcode1="";
    	if(request.getParameter("shi")==null&&request.getParameter("shi")==""&&request.getParameter("shi")=="0"){
    		ArrayList shilist = new ArrayList();
    		CommonBean commBean = new CommonBean();
    		shilist = commBean.getAgcode(sheng,account.getAccountId());
			if(shilist!=null){
       		int scount = ((Integer)shilist.get(0)).intValue();
            agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
          }
    	} 
    	String shi="",xian ="",xiaoqu="",sname = "",szdcode ="",stationtype1 ="",jzproperty1 ="",jztype1 ="",lrrq="";
    	
        shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
        xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
        xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
        String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"";
		sname = request.getParameter("sname")!=null?request.getParameter("sname"):"";
		szdcode = request.getParameter("szdcode")!=null?request.getParameter("szdcode"):"";
		stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
		jzproperty1 = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
		jztype1 = request.getParameter("jztype")!=null?request.getParameter("jztype"):"0";
		lrrq = request.getParameter("lrrq")!=null?request.getParameter("lrrq"):"";
		String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
		int count=0,pagesize=10,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
	          curpage=Integer.parseInt(s_curpage);
	          String permissionRights="";
	          String color=null;
          
%>
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<body>
	<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
   <% 
		JiZhanBean bean = new JiZhanBean();

		
			//String count1=bean.
			String str1=" and z.SHSIGN='1'";
			String str2=" and z.SHSIGN='0'";
			String str3=" and z.SHSIGN='2'";
			
		  	 //更改2012-12-5
        String count1="0";
	    String count2="0";
	    String count3="0";
	    String count4="0";
       	if("chaxun".equals(request.getParameter("command"))||"daochu".equals(request.getParameter("command"))){
            count1=bean.getCountt(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,lrrq);
            count2=bean.getCountt2(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,str1,lrrq);
            count3=bean.getCountt2(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,str2,lrrq);
            count4=bean.getCountt2(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,str3,lrrq);        		if(count1==null||count1==""||count1.equals("")||count1.equals("null")){
		   count1="0";
		}
		if(count2==null||count2==""||count2.equals("")||count2.equals("null")){
		   count2="0";
		}
		if(count3==null||count3==""||count3.equals("")||count3.equals("null")){
		   count3="0";
		}
		if(count4==null||count4==""||count4.equals("")||count4.equals("null")){
		   count4="0";
		}
		%>
		<%} %>
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">基站信息</div>
				<div class="content01_1">
					<table width="800px" border="0" cellpadding="1" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="60px">城市：</td>
							<td align="left" width="60px">
							<select name="shi" id="shi" class="selected_font" onchange="changeCity()">
				         		<option value="0">请选择</option>
				         		<%
					         	ArrayList shilist = new ArrayList();
					         	shilist = commBean.getAgcode(sheng,shi,loginName);
					         	if(shilist!=null){
					         		String agcode="",agname="";
					         		int scount = ((Integer)shilist.get(0)).intValue();
					         		for(int i=scount;i<shilist.size()-1;i+=scount)
				                    {
				                    	agcode=(String)shilist.get(i+shilist.indexOf("AGCODE"));
				                    	agname=(String)shilist.get(i+shilist.indexOf("AGNAME"));
				                    %>
				                    <option value="<%=agcode%>" selected="selected"><%=agname%></option>
				                    <%}
					         	}
					         %>
				         		</select>
				         		</td>
				         		<td align="right" width="60px">区县：</td>
								<td align="left" width="60px">
								<select name="xian" id="xian" class="selected_font" onchange="changeCountry()">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList xianlist = new ArrayList();
						         	shilist = commBean.getAgcode(shi,xian,loginName);
						         	if(shilist!=null){
						         		String agcode="",agname="";
						         		int scount = ((Integer)shilist.get(0)).intValue();
						         		for(int i=scount;i<shilist.size()-1;i+=scount)
					                    {
					                    	agcode=(String)shilist.get(i+shilist.indexOf("AGCODE"));
					                    	agname=(String)shilist.get(i+shilist.indexOf("AGNAME"));
					                    %>
					                    <option value="<%=agcode%>"><%=agname%></option>
					                    <%}
						         	}
						         %>
					         	</select>
				         	</td>
				         	<td align="right" width="60px">乡镇：</td>
							<td align="left" width="60px">
								<select name="xiaoqu" id="xiaoqu" class="selected_font">
				         		<option value="0">请选择</option>
				         		<%
					         	ArrayList xiaoqulist = new ArrayList();
					         	xiaoqulist = commBean.getAgcode(xian,xian,loginName);
					         	if(xiaoqulist!=null){
					         		String agcode="",agname="";
					         		int scount = ((Integer)xiaoqulist.get(0)).intValue();
					         		for(int i=scount;i<xiaoqulist.size()-1;i+=scount)
				                    {
				                    	agcode=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGCODE"));
				                    	agname=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGNAME"));
				                    %>
				                    <option value="<%=agcode%>"><%=agname%></option>
				                    <%}
					         	}
					         %>
				         	</select>
							</td>
							<td align="right" width="60px">关键字：</td>
							<td align="left" width="60px">
							<input type="text" name="txtKeyword" id="txtKeyword" value="" style="width:80px" />
							</td>
						</tr>
						<tr>
							<td align="left" colspan="8">
								※关键字包括：基站名称
							</td>
						</tr>
						<tr>
							<td align="right" colspan="8">
								<input type="button" class="btn_c1" onclick="query()" value="查询" /> 
							</td>
						</tr>
					</table>
					
					<div class="tbtitle01"><b>新建基站审批一览</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
						<tr align="right">
									</tr>
									<tr align="center">
									    <th width="10">序号</th>
										<th width="110">站点名称</th>
										<th width="100">所在地区</th>
										<th width="80">站点属性</th>
										<th width="80">供电方式</th>
										<th width="80">审批状态</th>
										<th width="80">启用状态</th>
										<th width="80">操作</th>
									</tr>
						<%
							 ArrayList fylist = new ArrayList();
					       	//更改2012-12-5
					       	if("chaxun".equals(request.getParameter("command"))||"daochu".equals(request.getParameter("command"))){
					            fylist = bean.getPageData1(curpage,sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,lrrq,keyword);
					       	    allpage=bean.getAllPage();
					        } else{
					            fylist=null;
					        }
					       	 fylist = bean.getPageData1(curpage,sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,lrrq,keyword);
					       	 allpage=bean.getAllPage();
							String jzname = "",spzy="",szdq = "",jzproperty = "", qyzt="", jztype= "",stationtype2= "",yflx = "",jnglmk="",gdfs="",area="",id="",dianfei="",zdcode="",xunisign="",shsignxs="",provauditstatus="";
							int intnum=xh = pagesize*(curpage-1)+1;
							 if(fylist!=null)
							{
								int fycount = ((Integer)fylist.get(0)).intValue();
								for( int k = fycount;k<fylist.size()-1;k+=fycount){
					
							     //num为序号，不同页中序号是连续的
									id = (String)fylist.get(k+fylist.indexOf("ID"));
									session.setAttribute("id",id);
									jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
									jzname = jzname != null ? jzname : "";
							    	szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
							    	qyzt = (String)fylist.get(k+fylist.indexOf("QYZT"));
									szdq = szdq != null ? szdq : "";
							    	jzproperty = (String)fylist.get(k+fylist.indexOf("PROPERTY"));
									jzproperty = jzproperty != null ? jzproperty : "";
							    	jztype = (String)fylist.get(k+fylist.indexOf("JZTYPE"));
									jztype = jztype != null ? jztype : "";
							    	//添加站点类型
							    	stationtype2 = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
									stationtype2 = stationtype2 != null ? stationtype2 : "";
									yflx = (String)fylist.get(k+fylist.indexOf("YFLX"));
									yflx = yflx != null ? yflx : "";
									jnglmk = (String)fylist.get(k+fylist.indexOf("JNGLMK"));
									jnglmk = jnglmk != null ? jnglmk : "";
									
									if("1".equals(jnglmk)){
									 jnglmk="有";
									}else{
									 jnglmk="无";
									}
									
									gdfs = (String)fylist.get(k+fylist.indexOf("GDFS"));
									gdfs = gdfs != null ? gdfs : "";
									area = (String)fylist.get(k+fylist.indexOf("AREA"));
									area = area != null ? area : "";
									dianfei = (String)fylist.get(k+fylist.indexOf("DIANFEI"));
									dianfei = dianfei != null ? dianfei : "0";
									if(dianfei.equals("null")||dianfei.equals("")){
									dianfei="0";
									}
									zdcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
									spzy= (String)fylist.get(k+fylist.indexOf("SPZY"));
									zdcode = zdcode != null ? zdcode : "";
									xunisign = (String)fylist.get(k+fylist.indexOf("XUNISIGN"));
									xunisign = xunisign != null ? xunisign : "";
									shsignxs = (String)fylist.get(k+fylist.indexOf("SHSIGN"));
									shsignxs = shsignxs != null ? shsignxs : "";
									DecimalFormat price = new DecimalFormat("0.0000");
								dianfei = price.format(Double.parseDouble(dianfei));
								
								provauditstatus = (String)fylist.get(k+fylist.indexOf("PROVAUDITSTATUS"));
								provauditstatus = provauditstatus != null ? provauditstatus : "";
					
								if(intnum%2==0){
								    color="#FFFFFF" ;
					
								 }else{
								    color="#DDDDDD";
								}
								if(shsignxs.equals("0")){
									color="#FFFF33" ;
								}else if(shsignxs.equals("2")){
									color="#FF3333" ;
								}else if(provauditstatus.equals("2")){
									color="#00CED1" ;
								}
       %>
      
									<!-- 数据加载  Start-->
									<tr align="center">
										<td width="10"><%=intnum++%></td>
										<td align="left"><%=jzname%></td>
										<td align="left"><%=szdq%></td>
										<td align="center"><%=jzproperty%></td>
										<td align="center"><%=gdfs%></td>
										<td align="center"><%=spzy%></td>
										<td align="center"><%=qyzt%></td>
										<td align="center">
										<% if (qyzt.equals("启用")){											
										%>
										<input type="button" class="btn_c1" onclick="shenpi('<%=spzy%>','<%=id %>')" disabled="disabled" value="审批" />&nbsp;
										<%}else{ %>
			 								<input type="button" class="btn_c1" onclick="shenpi('<%=spzy%>','<%=id %>')" value="审批" />&nbsp; 	
										<%} %>
										</td>										
									</tr>
									<% } %>
									<!-- 数据加载 End -->
									
									<tr bgcolor="#ffffff">
					<td colspan="8" align="left" >
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
		<script language="javascript">
	var path = '<%=path%>';
	function gopage()
     {
      document.form1.submit();
     }
     function previouspage()
     {
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage-1)%>';
      		document.form1.action=path+"/web/sdttWeb/jizan/zhandianshhe.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/sdttWeb/jizan/zhandianshhe.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/sdttWeb/jizan/zhandianshhe.jsp?curpage="+pageno;
      document.form1.submit();
     }
     function delzd(id){
     	<%
			if(permissionRights.indexOf("PERMISSION_DELETE")>=0){
			%>
     		document.form1.action=path+"/servlet/zhandian?action=del&id="+id;
      document.form1.submit();
      <%
    }else{
      %>
      alert("您没有删除站点的权限");
    <%}%>
    }
    function dfinfo(id){
    	<%
			if(permissionRights.indexOf("PERMISSION_EDIT")>=0){
			%>
     		document.form1.action=path+"/web/jizhan/dfinfo.jsp?id="+id;
      document.form1.submit();
      <%
    }else{
      %>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }
    function zlinfo(id){
    	<%
			if(permissionRights.indexOf("PERMISSION_EDIT")>=0){
			%>
     		document.form1.action=path+"/web/jizhan/zlinfo.jsp?id="+id;
      document.form1.submit();
      <%
    }else{
      %>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }
    function modifyjz(id){
    	<%
			if(permissionRights.indexOf("PERMISSION_EDIT")>=0){
			%>
     		document.form1.action=path+"/web/jizhan/modifyjz.jsp?id="+id;
      document.form1.submit();
      <%
    }else{
      %>
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
    function query(){
    	document.form1.action=path+"/web/sdttWeb/jizan/zhandianshhe.jsp?command=chaxun";
		document.form1.submit();
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
		function viewxn(zdid){
			document.form1.action=path+"/web/jizhan/viewxuni.jsp?id="+zdid;
      		document.form1.submit();
		}
		function checkPage(){
         	if(document.form1.pageCheck.checked){
         		for(var j = 0;j < document.form1.elements.length ; j++){
							document.form1.elements[j].checked=true;
						}
         	}else{
         		for(var j = 0;j < document.form1.elements.length ; j++){
							document.form1.elements[j].checked=false;
						}
         	}
        }
        function delsj(shsign){
     		var i = 0;

				for(var j = 0;j < document.form1.elements.length ; j++){
					if (document.form1.elements[j].checked){
						i++;
					}
				}
				if(i>0){
					
		       document.form1.action=path+"/servlet/zhandian?action=shenhe&shsign="+shsign;
		       document.form1.submit();
		       showdiv("请稍等..............");		                       
				}else{
					alert("请选择要审核的站点");
					return;
				}
    }
    function shenpi(a,b){
    window.open("../jizan/SiteShenpi_xx.jsp?id="+b, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
    //	document.form1.action = path+"/servlet/zhandian?action=shenhe&shsign="+a+"&id="+b;
    	document.form1.submit();
    }
        
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.txtKeyword.value='<%=keyword%>';
     </script>
</body>
</html>



