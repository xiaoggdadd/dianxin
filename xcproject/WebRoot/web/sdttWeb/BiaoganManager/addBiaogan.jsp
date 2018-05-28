<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="com.noki.biaogan.CBUserManage" %>
<%@ page import="com.noki.zwhd.model.ZhandianBean" %>
<%@ page import="com.noki.zwhd.model.DianbiaoBean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="../../../javascript/jquery-1.4.2.js"></script>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
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
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			
			String rgsh = request.getParameter("rgsh");//站点审核通过   首页传的值
			String rgsh2 = request.getParameter("caiji");// 采集站点
			String s_curpage = request.getParameter("curpage") != null
					? request.getParameter("curpage")
					: "1";//分页
			int count = 0, pagesize = 10, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
			curpage = Integer.parseInt(s_curpage);
			String permissionRights = "";
			
			String zdid = request.getParameter("zdid");
%>
			
</script>
</head>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page"
	class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
<form action="" name="form1" method="post">
<table width="700" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">新增基站标杆数据</div>
				<div class="content01_1">
					<table width="500px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="100px">城市：</td>
							<td width="100px">
								<select name="shi" id="shi" style="width:80px;" onchange="changeCity();">
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
							</td>
							<td align="right" width="100px">区县：</td>
							<td width="100px">
								<select name="xian" id="xian" style="width:80px;" onchange="changeCountry();">
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
								</select>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">基站名称：</td>
							<td width="100px" colspan="3">
								<select name="zdid" id="zdid" style="width:220px;" onchange="changeZdid();">
									<option value="0">请选择</option>
									<%
										CBUserManage manage = new CBUserManage();
										List<ZhandianBean> zhandianList = manage.searchZd(sheng,shi,xian);
										for(int i=0;i<zhandianList.size();i++){
										ZhandianBean zhandian = zhandianList.get(i);
										String id = zhandian.getID();
										String name = zhandian.getJZNAME();
									%>
										<option value="<%=id%>"><%=name%></option>
									<%} %>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">电表名称：</td>
							<td width="100px" colspan="3">
								<select name="dbid" id="dbid" style="width:220px;" onchange="changeDbid();">
									<option value="0">请选择</option>
									<%
										List<DianbiaoBean> dianbiaoList = manage.searchDb(zdid);
										for(int i=0;i<dianbiaoList.size();i++){
											DianbiaoBean dianbiao = dianbiaoList.get(i);
											String dbid = dianbiao.getDBID();
											String dbbm = dianbiao.getDBBM();
									%>
										<option value="<%=dbid%>"><%=dbbm%></option>
									<%} %>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">上次抄表时间：</td>
							<td width="100px">
								<input type="text" id="lastCbDate" name="lastCbDate"/>
							</td>
							<td align="right" width="100px">年月：</td>
							<td width="100px">
								<select name="year" id="year" style="width:60px;">
									<option>2013</option>
									<option>2014</option>
									<option>2015</option>
									<option selected="selected">2016</option>
									<option>2017</option>
									<option>2018</option>
									<option>2019</option>
									<option>2020</option>
									<option>2021</option>
								</select>年
								<select name="month" id="month" style="width:40px;" onchange="changeMonth();">
									<option>01</option>
									<option>02</option>
									<option>03</option>
									<option>04</option>
									<option>05</option>
									<option>06</option>
									<option>07</option>
									<option>08</option>
									<option>09</option>
									<option checked>10</option>
									<option>11</option>
									<option>12</option>
								</select>月
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">周期：</td>
							<td width="100px">
								<input type="text" id="zq" name="zq"/>
							</td>
							<td align="right" width="100px">空调系数：</td>
							<td width="100px">
								<input type="text" id="ktxs" name="ktxs"/>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">用电系数：</td>
							<td width="100px">
								<input type="text" id="ydxs" name="ydxs" value="1.44"/>
							</td>
							<td align="right" width="100px">单日直流负荷：</td>
							<td width="100px">
								<input name="startDate" type="text"  id="startDate"  style="width:80px" value="21" />&nbsp;A
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">本月标杆值：</td>
							<td width="100px" colspan="3">
								<input name="startDate" type="text"  id="startDate"  style="width:80px" value="568.0" />&nbsp;度
							</td>
						</tr>
						<tr>
							<td align="right" colspan="4" height="60px">
								<input name="button2" type="submit" class="btn_c1" id="button2" value="临时保存" />&nbsp; 
								<input name="button2" type="submit" class="btn_c1" id="button2" value="提交审批" />&nbsp; 
								<input name="button2" type="submit" class="btn_c1" id="button2" value="重置" />&nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td></tr></table>
</form>
</body>
</html>
<script>
	var path = '<%=path%>';
	
	var XMLHttpReq;
	
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
	
	function sendRequest(url,para) {
		createXMLHttpRequest();
		XMLHttpReq.open("GET", url, true);
		if(para=="sheng"){
			XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
		}else if(para=="quxian2"){
			XMLHttpReq.onreadystatechange = processResponse_jzname;
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
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
	
	function processResponse_jzname(){
		if (XMLHttpReq.readyState == 4) { // 判断对象状态
			//alert(XMLHttpReq.status);
	    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
	        	//var res = XMLHttpReq.responseText;
	        	//alert(XMLHttpReq.responseText);
	        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
	          updateZd(res);
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

	function updateQx(res){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		
		for(var i = 0;i<res.length;i+=2){
			shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
		}
	}
	
	function changeCountry(){
		var xian = document.form1.xian.value;
		$.ajax({ // 请求登录处理页
			url : "/tieta/CBUserServlet", // 登录处理页
			dataType : "json",
			// 传送请求数据
			data : {
				'method' : 'searchZdByXian',
				'xian':xian
			},
			success : function(jsonValue) { // 登录成功后返回的数据
				var zdhtml = "";
				for ( var i = 0; i < jsonValue.length; i++) {
					var json = jsonValue[i];
					zdhtml += "<option value=\""+json.ID+"\">" + json.JZNAME
							+ "</option>";
				}
				$("#zdid").html(zdhtml);
			}
		});
	}
	
	function updateZd(res){
	
		var zdidlist = document.all.zdid;
		zdidlist.options.length="0";
		zdidlist.add(new Option("请选项","0"));
		
		for(var i = 0;i<res.length;i+=2){
			zdidlist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
		}
	}
	
	function changeZdid(){
		var zdid = document.form1.zdid.value;
		$.ajax({ // 请求登录处理页
			url : "/tieta/CBUserServlet", // 登录处理页
			dataType : "json",
			// 传送请求数据
			data : {
				'method' : 'searchDbByzdid',
				'zdid':zdid
			},
			success : function(jsonValue) { // 登录成功后返回的数据
				var dbhtml = "";
				for ( var i = 0; i < jsonValue.length; i++) {
					var json = jsonValue[i];
					dbhtml += "<option value=\""+json.DBID+"\">" + json.DBBM
							+ "</option>";
				}
				$("#dbid").html(dbhtml);
				changeDbid();
			}
		});
	}
	
	function changeDbid(){
		var dbid = document.form1.dbid.value;
		$.ajax({ // 请求登录处理页
			url : "/tieta/CBUserServlet", // 登录处理页
			dataType : "json",
			// 传送请求数据
			data : {
				'method' : 'searchLastCbDate',
				'dbid':dbid
			},
			success : function(jsonValue) { // 登录成功后返回的数据
				var lastCbDate = jsonValue.LastCbDate;
				$("#lastCbDate").val(lastCbDate);
			}
		});
	}
	
	function changeMonth(){
		var month = document.form1.month.value;
		var lastCbDate = document.form1.lastCbDate.value;
		if(lastCbDate!=undefined&&lastCbDate!=""&&lastCbDate!="null"){
			var _month = lastCbDate.split(" ")[0].split("-")[1];
			var _day = lastCbDate.split(" ")[0].split("-")[2];
		
			if(month=="01"||month=="02"||month=="11"||month=="12"){
				$("#ktxs").val("0.2");
			}else {
				$("#ktxs").val("0.6");
			}
		}else{
			alert("该电表尚未抄表！");
		}
	}
	
	 //计算天数差的函数，通用 
   function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式 
       var  aDate,  oDate1,  oDate2,  iDays ;
       aDate  =  sDate1.split("-");
       oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]); //转换为12-18-2006格式 
       aDate  =  sDate2.split("-");
       oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]); 
       iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24); //把相差的毫秒数转换为天数 
       return  iDays;
   }   
	
	document.form1.shi.value='<%=shi%>';
	document.form1.xian.value='<%=xian%>';
	document.form1.xiaoqu.value='<%=xiaoqu%>';
	document.form1.stationtype.value='<%=sitetype%>';
	document.form1.qyzt.value='<%=qyzt%>';
</script>