<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.newfunction.dao.PdTestBewrite,com.noki.newfunction.javabean.Zdinfo"%>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.regex.Pattern"%>

<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
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
	String loginId1 = request.getParameter("loginId");
	String accountmonth = request.getParameter("accountmonth") != null ? request
			.getParameter("accountmonth")
			: "";//报账月份
	int intnum = 0;
	String permissionRights = "";
	String color = null;
	String roleId = (String) session.getAttribute("accountRole");
	
    String shenhe = request.getParameter("shenhe")!=null?request.getParameter("shenhe"):"5";
    String qxshenhe = request.getParameter("qxshenhe")!=null?request.getParameter("qxshenhe"):"5";
    String zdmc = request.getParameter("zdmc")!=null?request.getParameter("zdmc"):"";
    String property1 = request.getParameter("property")!=null?request.getParameter("property"):"0";
%>

<html>
<head>
	<title></title>
	<style>
		.style1 {
			color: #FF9900;
			font-weight: bold;
		}		
		.style1 {
			color: red;
			font-weight: bold;
		}
		
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		
		.bttcn {
			color: BLACK;
			font-weight: bold;
		}
		
		.form_label {
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height: 23px
		}
		
		.selected_font {
			width: 130px;
			font-family: 宋体;
			font-size: 12px;
			line-height: 120%;
		}
		
		.relativeTag {
			z-index: 10;
			position: relative;
			top: expression(this.offsetParent.scrollTop);
		}
	</style>
			<script type="text/javascript" src="<%=path%>/javascript/wait.js">
	</script>
			<script type="text/javascript"
				src="<%=path%>/javascript/jquery-1.4.2.js">
	</script>
			<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
	</script>
			<script src="<%=path%>/javascript/PopupCalendar.js">
	</script>
			<script src="<%=path%>/javascript/jquery-1.4.2.js">
	</script>
			<script src="<%=path%>/javascript/PopupCalendar_ny.js">
	</script>
	<script>
		var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
		oCalendarEnny.Init();
		
		var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
		oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
		oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
				"八月", "九月", "十月", "十一月", "十二月");
		oCalendarChsny.oBtnTodayTitle = "确定";
		oCalendarChsny.oBtnCancelTitle = "取消";
		oCalendarChsny.Init();
	</script>
	<script>
		
		var oCalendarEn = new PopupCalendar("oCalendarEn"); //初始化控件时,请给出实例名称如:oCalendarEn
		oCalendarEn.Init();
		
		var oCalendarChs = new PopupCalendar("oCalendarChs"); //初始化控件时,请给出实例名称:oCalendarChs
		oCalendarChs.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
		oCalendarChs.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
				"八月", "九月", "十月", "十一月", "十二月");
		oCalendarChs.oBtnTodayTitle = "今天";
		oCalendarChs.oBtnCancelTitle = "取消";
		oCalendarChs.Init();
		</script>
	<script language="javascript">
		path = '<%=path%>';
		function queryDegree() {
		
				document.form1.action = path
						+ "/web/jzcbnewfunction/pdtestbewrite.jsp?command=chaxun";
				document.form1.submit();
		
		}
		$(function() {
			$("#chaxun").click(function() {
				queryDegree();
			});
			$("#chexiao").click(function() {
				chexiao();
			});
		});
	</script>

</head>

	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
<%
	permissionRights = commBean.getPermissionRight(roleId, "0804");
%>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
				<tr>
			    	<td colspan="4" width="50" >
						 <div style="width:700px;height:50px">
						  
						  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">工单处理</span>	
						 </div>
					</td>
				</tr>
				<tr>
					<td height="30" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;过滤条件&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="8%" width="1200">
						<table>
							<tr class="form_label">
								<td>
									城市：
								</td>
								<td>
									<select name="shi" class="selected_font"
										onchange="changeCity()">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList shilist = new ArrayList();
											shilist = commBean.getAgcode(sheng, account.getAccountId());
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
								<td>
									区县：
								</td>
								<td>
									<select name="xian" class="selected_font">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList xianlist = new ArrayList();
											xianlist = commBean.getAgcode(shi, account.getAccountId());
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
								<td>站点名称</td>
								<td>
									<input type="text" name="zdmc" id="zdmc" class="selected_font" />
								</td>
								<td>站点属性：
								</td>
								<td>
									<select name="property" style="width: 130"
										class="selected_font">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList zdsx = new ArrayList();
											zdsx = ztcommon.getSelctOptions("zdsx");
											if (zdsx != null) {
												String code = "", name = "";
												int cscount = ((Integer) zdsx.get(0)).intValue();
												for (int i = cscount; i < zdsx.size() - 1; i += cscount) {
													code = (String) zdsx.get(i + zdsx.indexOf("CODE"));
													name = (String) zdsx.get(i + zdsx.indexOf("NAME"));
										%>
										<option value="<%=code%>"><%=name%></option>
										<%
											}
											}
										%>
									</select>
								</td>

								<td>
									<%
										if (permissionRights.indexOf("PERMISSION_SEARCH") >= 0) {
									%>
									<%
										}
									%>
									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -80px; float: right; top: 0px">
										<img alt="" src="<%=path%>/images/chaxun.png" width="100%"
											height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>

									</div>
								</td>
							</tr>
							<tr class="form_label">
							
							<td>
									对标月份：
								</td>
								<td>
									<input type="text" name="accountmonth"
										value="<%if (null != request.getParameter("accountmonth"))
											out.print(request.getParameter("accountmonth"));%>"
										onFocus="getDatenyString(this,oCalendarChsny)"
										class="selected_font" />
								</td>
							
							<td>区县核实状态：</td>
         						<td><select name="qxshenhe" class="selected_font">
         							<option value="5">请选择</option>
         							<option value="0">未核实</option>
         							<option value="1">已核实</option>
         							</select></td>

								<td>市级审核状态：</td>
         						<td><select name="shenhe" class="selected_font">
         							<option value="5">请选择</option>
         							<option value="0">未审核</option>
         							<option value="2">审核通过</option>
         							<option value="1">审核不通过</option>
         							</select></td>

								<td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td height="23" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;信息列表&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<%
				PdTestBewrite bean = new PdTestBewrite();

				String whereStr = "";
				String str = "";

				if (shi != null && !shi.equals("") && !shi.equals("0")) {
					whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
					str = str + " AND ZD.SHI='" + shi + "'";
				}
				if (xian != null && !xian.equals("") && !xian.equals("0")) {
					whereStr = whereStr + " AND ZD.XIAN='" + xian + "'";
					str = str + " AND ZD.XIAN='" + xian + "'";
				}
				if (zdmc != null && !zdmc.equals("") ) {
					whereStr = whereStr + " AND CB.ZDNAME like'%" + zdmc + "%'";
					str = str + " AND CB.ZDNAME like'%" + zdmc + "%'";
				}
				if (accountmonth != null && accountmonth != ""
						&& !accountmonth.equals("0")) {
					whereStr = whereStr + " AND CB.CBSJ='" + accountmonth + "'";
					str = str + " AND  CB.CBSJ='" + accountmonth + "'";
				}
				
				if(shenhe!=null && shenhe!="" && !shenhe.equals("5")){
					whereStr=whereStr+" AND CB.SHIJSH ='"+shenhe+"'";
				}
				if(qxshenhe!=null && qxshenhe!="" && !qxshenhe.equals("5")){
					if("0".equals(qxshenhe)){
					whereStr=whereStr+" AND (CB.QXTJSH ='0' OR CB.QXTJSH IS NULL )";	
					}else{
						whereStr=whereStr+" AND CB.QXTJSH ='"+qxshenhe+"'";						
					}
				}
				if(property1 != null && property1 != "" && !property1.equals("0")){
					whereStr=whereStr+" AND zd.property='"+property1+"'";
				}
				if (loginId1 != null && !loginId1.equals("")) {
					loginId = loginId1;
					shi = "1";
				}
			%>
			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="100%" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="4%" height="23"0 bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点编号
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="12%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								所在区域
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								超标月份
							</div>
						</td>
						<td width="10%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								超标比例
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								录入人
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								录入时间
							</div>
						</td>
						
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								操作
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县核实状态	
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级审核状态	
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市级审核不通过原因
							</div>
						</td>
					</tr>
	<%
		List<Zdinfo> list = null;
		if ("daochu".equals(request.getParameter("command"))
				|| "chaxun".equals(request.getParameter("command"))) {
			list = bean.getPageDatap(whereStr, loginId);
		} else {
			list = null;
		}
		String id = "";//站点唯一标识id
		String zdid = "";//站点编号
		String zdname = "";//站点名称
		String szdq = "";//所属地域
		String cbmonth = "";//超标月份(报账月份、对标月份)
		String cbbili = "";//超表比例
		String entryperson = "";//录入人
		String entrytime = "";//录入时间
		String status = "";//状态
		String cid="";//cbzd表唯一主键
		String sjtdyy;//市级不通过原因
		String shishenhe;//市级审核状态
		String shishenhe1;//市级审核状态
		if (list != null) {
			for (Zdinfo bean1 : list) {
				id = bean1.getWjid();
				id = id != null ? id : "";			
			//System.out.println("dddddd"+id);
				zdid = bean1.getZdid();
				zdid = zdid != null ? zdid : "";
				
				zdname = bean1.getZdname();
				zdname = zdname != null ? zdname : "";
				
				szdq = bean1.getSzdq();
				szdq = szdq != null ? szdq : "";
				
				cbmonth = bean1.getCbsj();
				cbmonth = cbmonth != null ? cbmonth : "";
				
				cbbili = bean1.getCbbl();
				cbbili = cbbili != null ? cbbili : "0.00";
				if(cbbili==null||"".equals(cbbili)||" ".equals(cbbili)||"null".equals(cbbili)||"o".equals(cbbili))cbbili="0.00";
				DecimalFormat cb = new DecimalFormat("0.00");
				cbbili = cb.format(Double.parseDouble(cbbili));
				
				entryperson = bean1.getLrr();
				entryperson = entryperson != null ? entryperson : "";
				
				entrytime = bean1.getLrsj();
				entrytime = entrytime != null ? entrytime : "";
				
				sjtdyy=bean1.getSjtdyy();
				shishenhe=bean1.getSjshbz();
				shishenhe1=bean1.getSjshbz();
				
				status = bean1.getQxtjsh();
				status = status != null ? status : "";
				if("0".equals(status.trim())||status==""||status==null){
					status = "未核实";
				}else if("1".equals(status.trim())){
					status = "已核实";
				}
				if("0".equals(shishenhe1)||shishenhe1==""||shishenhe1==null){
					shishenhe1 = "未审核";
				}else if("1".equals(shishenhe1)){
					shishenhe1 = "不通过";
				}else if("2".equals(shishenhe1)){
					shishenhe1="已通过";
				}
				
				if (intnum % 2 == 0) {
					color = "#FFFFFF";

				} else {
					color = "#DDDDDD";
				}
				intnum++;
	%>
					<%if("1".equals(shishenhe)){ %>
						<tr bgcolor = "yellow">
					<%}else{ %>
						<tr bgcolor = "<%=color%>">
					<%} %>
					
						<td>
							<div align="center"><%=intnum%></div>
						</td>
						<td>
		              		<div align="center"><input type="checkbox" name="zdid[]" value="<%=id%>" /></div>
		              		<input type="hidden" type="checkbox"  name="test1[]" value="<%=shishenhe%>" />
		            	</td>
						<td>
							<div align="left"><%=zdid%></div>
						</td>
						<td>
							<div align="left"><%=zdname%></div>
						</td>
						<td>
							<div align="left"><%=szdq%></div>
						</td>
						<td>
							<div align="center"><%=cbmonth%></div>
						</td>
						<td>
							<div align="right"><%=cbbili%>%</div>
						</td>
						<td>
							<div align="center"><%=entryperson%>
							</div>
						</td>
						<td>
							<div align="center"><%=entrytime%></div>
						</td>
						
						<%if("2".equals(shishenhe)){ %>
							<td></td>
						<%}else{ %>
							<td>
							<div align="center">
								<a
									href="javascript:lookDetailssz('<%=id%>')">测试描述</a>
							</div>
						</td>
						<%} %>
						<td>
							<div align="center"><%=status%></div>
						</td>
						<td>
							<div align="center"><%=shishenhe1%></div>
						</td>
						<td>
							<div align="center"><%=sjtdyy%></div>
						</td>
					</tr>
		 <%
		 	}
		 %>
       <%
       	}
       %>
	<%
		if (intnum == 0) {
			for (int i = 0; i < 15; i++) {
				if (i % 2 == 0) {
					color = "#FFFFFF";
				} else {
					color = "#DDDDDD";
				}
	%>

					<tr bgcolor="<%=color%>">
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td><td>
							&nbsp;
						</td>
					</tr>
		<%
			}
			} else if (!(intnum > 15)) {
				for (int i = ((intnum - 1) % 15); i < 15; i++) {
					if (i % 2 == 0)
						color = "#DDDDDD";
					else
						color = "#FFFFFF";
		%>
					<tr bgcolor="<%=color%>">
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td><td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td><td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<%
						}
						}
					%>
				</table>
			</div>

			<table width="100%" height="8%" border="0" cellspacing="0" cellpadding="0">
			 <tr><td>
			  <input type="hidden" name="sheng2" id="sheng2" value=""/>
			  <input type="hidden" name="shi2" id="shi2" value=""/>
			  <input type="hidden" name="xian2" id="xian2" value=""/>
			  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
			  <input type="hidden" name="sptype2" id="sptype2" value=""/>
			  <input type="hidden" name="manualauditstatus2" id="manualauditstatus2" value=""/>
			 </td></tr>
		   	 <tr>
                    <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                        <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                    </div></td>
		    </tr>
		    <tr class="form_label">
		    	<td><font color="red">* 黄色区域数据代表市级审核不通过</font></td>
		    <td align="right">
				  <div id="chexiao" style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:16px">
						<img alt="" src="<%=request.getContextPath()%>/images/shangchuan.png" width="100%" height="100%" />
						<span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">撤销</span>
				  </div>       	
		      </td></tr>		                         
		</table>
		</form>
	</body>
<script type="text/javascript">	
	function lookDetailssz(id) {
	
		var path = '<%=path%>';
		window
				.open(path + "/web/jzcbnewfunction/pdtestbewritexx.jsp?id=" + id
						, '',
						'width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
	}
</script>
<script type="text/javascript">
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
		XMLHttpReq.send(null);  
	}
	// 处理返回信息函数
    function processResponse() {
    	
	    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
	            var res = XMLHttpReq.responseText;
	            window.alert(res);
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
    }
    
	function processResponse_sheng() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
              	updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

	function processResponse_shi() {
	
		if (XMLHttpReq.readyState == 4) { // 判断对象状态
	    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
	          	updateQx(res);                   
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
	function processResponse_xian() {
		
		if (XMLHttpReq.readyState == 4) { // 判断对象状态
	    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
	          	updateZd(res);
	                   
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
	
	function changeSheng(){
		var sid = document.form1.sheng.value;
		document.form1.sheng2.value=document.form1.sheng.value;
		if(sid=="0"){
			var shilist = document.all.shi;
			shilist.options.length="0";
			shilist.add(new Option("请选项","0"));
			return;
		}else{
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
		document.form1.shi2.value=document.form1.shi.value;
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
	
	function updateZhandian(res){
		var shilist = document.all.zhandian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		for(var i = 0;i<res.length;i+=2){
			shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
		}
	}
	
	function updateXiaoqu(res){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		for(var i = 0;i<res.length;i+=2){
			shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
		}
	}
	
	function chooseAll() { 
        var qm = document.getElementsByName('test');
        var m = document.getElementsByName('zdid[]');   
        var l = m.length; 
        if(qm[0].checked == true){
          for (var i = 0; i < l; i++) {   
            m[i].checked = true;   
          }  
        }else{
          for (var i = 0; i < l; i++) {   
            m[i].checked = false;  
          }   
        }        
    } 
	function chexiao(){ //测试撤销
		var m = document.getElementsByName('zdid[]');
		var mc = document.getElementsByName('test1[]'); 
        var arr = new Array
        var l = m.length;
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr = "";
       	var chooseIdStr1="";
       	var con=0;
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       		if(m[i].checked == true){
       			if(mc[i].value=='2'){
       			con++;
       			}
       		}
       	}
       	
       	if(con!=0){
       		alert("市级审核已通过不允许撤销！");
       	return;
       	}else{
       	if(count!=0){
	       	if(count%240==0){
	       		n=count/240-1;
	       	}else{
	       		n=(count-(count%240))/240;
	       	}
	        for(var i = 0; i < l; i++){
	          if(m[i].checked == true){
	             bz+=1;
	             count1+=1;
	             chooseIdStr = chooseIdStr +"'" +m[i].value +"',"; 
	          }
       	
	          if(count1<=240*n||((bz+count2)>=239&&(bz+count2)<=241)){
	         	  if(((bz+count2)/240==1)||((bz+count2)>=239&&(bz+count2)<=241)){
			        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
			       // alert(chooseIdStr+"222");
			        document.form1.action=path+"/servlet/qxfj?action=chexiao&chooseIdStr="+chooseIdStr;
		            chooseIdStr = ""; 
	       			bz=0;
	       			count2=0;
		            document.form1.submit();	          	
		          }
		       }else if(count==count1&&bzw==1){
		          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
		          //alert(chooseIdStr+"111");
			      bzw=0;
		          document.form1.action=path+"/servlet/qxfj?action=chexiao&chooseIdStr="+chooseIdStr;
		          document.form1.submit(); 
		       }            
	        } 
        }else{
          alert("请选择信息！");
        }
       	}
		}
</script>
<script type="text/javascript">		
		document.form1.shi.value = '<%=shi%>';
		document.form1.xian.value = '<%=xian%>';
		document.form1.accountmonth.value = '<%=accountmonth%>';
		document.form1.zdmc.value='<%=zdmc%>';
		document.form1.qxshenhe.value='<%=qxshenhe%>';
		document.form1.shenhe.value='<%=shenhe%>';
		document.form1.property.value='<%= property1%>';
	</script>
</html>

