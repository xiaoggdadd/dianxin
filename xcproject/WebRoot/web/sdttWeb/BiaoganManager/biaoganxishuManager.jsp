<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.noki.biaogan.BiaoganManage"%>
<%@ page import="com.noki.biaogan.model.BiaoganBean"%>
<%@ page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script>
	
<%
			String path = request.getContextPath();
			String loginName = (String) session.getAttribute("loginName");
			Account account = (Account) session.getAttribute("account");
			String loginId = account.getAccountId();
			String roleId = (String) session.getAttribute("accountRole");
			System.out.println("################站点" + roleId);
			String jztype1 = request.getParameter("jztype") != null ? request
					.getParameter("jztype") : "0";
			String jzproperty1 = request.getParameter("jzproperty") != null
					? request.getParameter("jzproperty")
					: "0";
			String bgsign = request.getParameter("bgsign") != null ? request
					.getParameter("bgsign") : "-1";
			String qyzt = request.getParameter("qyzt") != null ? request
					.getParameter("qyzt") : "-1";//站点启用状态

			String caijid = request.getParameter("caijidian") != null ? request
					.getParameter("caijidian") : "-1";
			String sitetype = request.getParameter("stationtype") != null
					? request.getParameter("stationtype")
					: "0";
			String color = null;
			String sheng = (String) session.getAttribute("accountSheng");
			String agcode1 = "";
			if (request.getParameter("shi") == null) {
				ArrayList shilist = new ArrayList();
				CommonBean commBean = new CommonBean();
				shilist = commBean.getAgcode(sheng, account.getAccountId());
				if (shilist != null) {
					int scount = ((Integer) shilist.get(0)).intValue();
					agcode1 = (String) shilist.get(scount
							+ shilist.indexOf("AGCODE"));
				}
			}
			String shi = request.getParameter("shi") != null ? request
					.getParameter("shi") : agcode1;
			String xian = request.getParameter("xian") != null ? request
					.getParameter("xian") : "0";
			String xiaoqu = request.getParameter("xiaoqu") != null ? request
					.getParameter("xiaoqu") : "0";
			String sname = request.getParameter("sname") != null ? request
					.getParameter("sname") : "";
			String szdcode = request.getParameter("szdcode") != null ? request
					.getParameter("szdcode") : "";
			String keyword = request.getParameter("txtKeyword") != null
					? request.getParameter("txtKeyword")
					: "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			String fistYearmonth = sdf.format(new Date());
					
			String yearmonth = request.getParameter("yearmonth")==null?fistYearmonth:request.getParameter("yearmonth");
			String gjz = request.getParameter("gjz")==null?"":request.getParameter("gjz");
			
			String rgsh = request.getParameter("rgsh");//站点审核通过   首页传的值
			String rgsh2 = request.getParameter("caiji");// 采集站点
			String s_curpage = request.getParameter("curpage") != null
					? request.getParameter("curpage")
					: "1";//分页
			int count = 0, pagesize = 10, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
			curpage = Integer.parseInt(s_curpage);
			String permissionRights = "";%>
	
</script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	
</head>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page"
	class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
	<form action="" name="form1" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<%
			String whereStr = "";
			String str = "";
			String xuni = "0";
			/*  if(sheng != null && !sheng.equals("")&& !sheng.equals("0")){
				whereStr=whereStr+" and JZTYPE='"+sheng+"'";
				str=str+" and JZTYPE='"+sheng+"'";
			}
			if(sheng != null && !sheng.equals("")&& !sheng.equals("0")){
				 whereStr=whereStr+" and JZ.JZNAME like '%"+sheng+"%'";
				
			}*/

			if (xuni.equals("3")) {
				whereStr = whereStr + " and shsign!='1'";
			}
			if (!xiaoqu.equals("0")) {
				whereStr = whereStr + " and z.xiaoqu='" + xiaoqu + "'";
				str = str + " and z.xiaoqu='" + xiaoqu + "'";
			} else if (!xian.equals("0")) {
				whereStr = whereStr + " and z.xian='" + xian + "'";
				str = str + " and z.xian='" + xian + "'";
			} else if (!shi.equals("0")) {
				whereStr = whereStr + " and z.shi='" + shi + "'";
				str = str + " and z.shi='" + shi + "'";
			} else if (!sheng.equals("0")) {
				whereStr = whereStr + " and z.sheng='" + sheng + "'";
				str = str + " and z.sheng='" + sheng + "'";
			}
			if (sname.length() > 0 && sname != null) {
				whereStr = whereStr + " and z.jzname like '%" + sname + "%'";
				str = str + " and z.jzname like '%" + sname + "%'";
			}
			if (szdcode.length() > 0 && szdcode != null) {
				whereStr = whereStr + " and z.id='" + szdcode + "'";
				str = str + " and z.id='" + szdcode + "'";
			}

			if (!jztype1.equals("0")) {
				whereStr = whereStr + " and z.jztype='" + jztype1 + "'";
				str = str + " and z.jztype='" + jztype1 + "'";
			}
			if (!jzproperty1.equals("0")) {
				whereStr = whereStr + " and z.property='" + jzproperty1 + "'";
				str = str + " and z.property='" + jzproperty1 + "'";
			}
			if (!bgsign.equals("-1")) {
				whereStr = whereStr + " and z.bgsign='" + bgsign + "'";
				str = str + " and z.bgsign='" + bgsign + "'";
			}
			if (!qyzt.equals("-1")) {//站点启用状态
				whereStr = whereStr + " and z.qyzt='" + qyzt + "'";
				str = str + " and z.qyzt='" + qyzt + "'";
			}

			if (rgsh != null && !rgsh.equals("null")) {//首页传的值 审核通过的
				whereStr = whereStr + " and z.SHSIGN='" + rgsh + "'";
				str = str + " and z.SHSIGN='" + rgsh + "'";
			}
			System.out.println("采集" + rgsh2);
			if (rgsh2 != null && !rgsh2.equals("null")) {//首页传的值 采集站点
				whereStr = whereStr + " and z.caiji='" + rgsh2 + "'";
				str = str + " and z.caiji='" + rgsh2 + "'";
			}

			if (caijid != null && !caijid.equals("") && !caijid.equals("-1")) {
				whereStr = whereStr + " and CAIJI='" + caijid + "'";
				str = str + " and CAIJI='" + caijid + "'";
			}
			if (sitetype != null && !sitetype.equals("")
					&& !sitetype.equals("0")) {
				whereStr = whereStr + " and STATIONTYPE='" + sitetype + "'";
				str = str + " and STATIONTYPE='" + sitetype + "'";
			}

			if (yearmonth != null && !yearmonth.equals("")) {
				whereStr = whereStr + " and c.YEARMONTH = '"+yearmonth+"'";
				str = whereStr + " and c.YEARMONTH = '"+yearmonth+"'";
			}
			
			if(gjz !=null&&!gjz.equals("")){
				whereStr = whereStr + " and (z.JZNAME like '%"+gjz+"%' or z.JZCODE like '%"+gjz+"%')";
				str = whereStr + " and (z.JZNAME = '%"+gjz+"%' or z.JZCODE like '%"+gjz+"%')";
			}
		%>
		<tr valign="top">
			<td width="12"><img src="../images/space.gif" width="12"
				height="17" /></td>
			<td>
				<div class="content01">
					<div class="tit01">基站标杆管理</div>
					<div class="content01_1">
						<table width="100%" border="0" cellpadding="2" cellspacing="0"
							class="tb_select">
							<tr>
								<td>城市&nbsp; <select name="shi" id="shi"
									style="width:80px;" onchange="changeCity()">
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
								</select>&nbsp;&nbsp;&nbsp; 区县&nbsp; <select name="xian" id="xian"
									onchange="changeCountry()" style="width:80px;">
										<option value="0">请选择</option>
										<%
											ArrayList xianlist = new ArrayList();
											xianlist = commBean.getAgcode(shi, xian, loginName);
											if (xianlist != null) {
												String agcode = "", agname = "";
												int scount = ((Integer) xianlist.get(0)).intValue();
												for (int i = scount; i < xianlist.size() - 1; i += scount) {
													agcode = (String) xianlist.get(i
															+ xianlist.indexOf("AGCODE"));
													agname = (String) xianlist.get(i
															+ xianlist.indexOf("AGNAME"));
										%>
										<option value="<%=agcode%>"><%=agname%></option>
										<%
											}
											}
										%>
								</select>&nbsp;&nbsp;&nbsp; 
								年月<input type="text" id="yearmonth" name="yearmonth" value="<%=fistYearmonth %>" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" style="width:80px;"/>
								&nbsp;&nbsp; 
								关键字：&nbsp;<input name="gjz" type="text" id="gjz"
									style="width:80px" value="<%=gjz %>" /> ※ 关键字只包括基站名称</td>
							</tr>
							<tr>
								<td  align="right"><input name="button2" type="submit"
									class="btn_c1" id="button2" onclick="query()" value="查询" />&nbsp;
								</td>
							</tr>
						</table>
						<div class="tbtitle01">
							<b>基站标杆查询一览</b>
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="tb_list1">
							<tr align="right">
								<td colspan="5" align="left" valign="middle">&nbsp;</td>
								<td colspan="2" align="right" valign="middle">
									<input name="daochuExcel" type="button"  class="btn_c1" id="daochuExcel" value="导出Excel" onclick="doDaochuExcel();"/>&nbsp;
								</td>
							</tr>
							<tr align="center">
								<th width="180">基站名称</th>
								<th>年度月份</th>
								<th >单日直流负荷(A)</th>
								<th>空调系数</th>
								<th >用电系数</th>
								<th>当月耗电量（度）</th>
							</tr>
							<%
								BiaoganManage manage = new BiaoganManage();
								ArrayList<BiaoganBean> biaoganlist = new ArrayList<BiaoganBean>();
								biaoganlist = manage.getPageData(curpage, loginName, loginId,
										whereStr);
								DecimalFormat df1 = new DecimalFormat("0.00");
								allpage = manage.getAllPage();
								double a=0,b=0,c=0,d=0;
								int intnum = pagesize * (curpage - 1) + 1;
								//System.out.println(intnum);
								if (biaoganlist != null) {
									//int fycount = biaoganlist.size();
									for (int k = 0; k < biaoganlist.size(); k++) {
										BiaoganBean biaogan = biaoganlist.get(k);
										String id = biaogan.getJZID();
										String jzname = biaogan.getJZNAME() == null|| biaogan.getJZNAME().equals("") ? "" : biaogan.getJZNAME();
										String dbbm = biaogan.getDBBM() == null|| biaogan.getDBBM().equals("") ? "" : biaogan.getDBBM();
										String biaoganvalue = biaogan.getBIAOGANVALUE() == null|| biaogan.getBIAOGANVALUE().equals("") ? "" : biaogan.getBIAOGANVALUE();
										String _yearmonth = biaogan.getYEARMONTH() == null|| biaogan.getYEARMONTH().equals("") ? "" : biaogan.getYEARMONTH();
										String zlfh = biaogan.getZLFH()== null|| biaogan.getZLFH().equals("") ? "" : biaogan.getZLFH();
										String ydxs = biaogan.getYDXS()== null|| biaogan.getYDXS().equals("") ? "" : biaogan.getYDXS();
										String ktxs = biaogan.getKTXS()== null|| biaogan.getKTXS().equals("") ? "" : biaogan.getKTXS();
										a=Double.valueOf(zlfh);
										b=Double.valueOf(ydxs);
										c=Double.valueOf(ktxs);
										d=Double.valueOf(biaoganvalue);
							%>
							<tr align="center">
										<td><%=jzname %></td>
										<td>&nbsp;<%=_yearmonth %></td>
										<td  align="right">&nbsp;<%=df1.format(a) %></td>
										<td  align="right">&nbsp;<%=df1.format(b) %></td>
										<td  align="right">&nbsp;<%=df1.format(c) %></td>
										<td  align="right">&nbsp;<%=df1.format(d) %></td>
							</tr>
							<%
								}
							%>
							<tr bgcolor="#ffffff">
									<td colspan="10" align="left">
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
 			out.print("<a href='javascript:gopagebyno(" + allpage
 					+ ")'><img src='../images/page-last.gif'></a>");
 		} else {
 			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
 		}
 %> </font> &nbsp;&nbsp;
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


										</div></td>
								</tr>

							</table>
							<div class="space_h_10"></div>
						</div>
					</div></td>
			</tr>
			<%
				}
			%><!-- else {%>-->
			<!--  <tr align="center" >
			<td align="left" colspan="9">
			当前无数据。
			</td>
		</tr>-->
						</table>
						<div class="space_h_10"></div>
					</div>
				</div>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
<script>
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
      		document.form1.action=path+"/web/sdttWeb/BiaoganManager/biaoganxishuManager.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      		document.form1.action=path+"/web/sdttWeb/BiaoganManager/biaoganxishuManager.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = pageno;   
      document.form1.action=path+"/web/sdttWeb/BiaoganManager/biaoganxishuManager.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+pageno;
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
	window.open("../jizan/addSite.jsp", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}

function update(id){
	window.open("../jizan/UpdateSite.jsp?id="+id, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}

function query(){
	document.form1.action=path+"/web/sdttWeb/BiaoganManager/biaoganxishuManager.jsp";
}

function addBiaogan(){
	window.open("addBiaogan.jsp", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
}

function doDaochuExcel(){
	document.form1.action = path+ "/servlet/BiaoganDownload";
	document.form1.submit();
}

document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.yearmonth.value = '<%=yearmonth%>';
document.form1.gjz.value = '<%=gjz%>'
</script>