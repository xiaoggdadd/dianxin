<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
%>
<html>
	<head>
		<title></title>
		<style>
.style1 {
	color: red;
	font-weight: bold;
}

.STYLE6 {
	color: #FFFFFF;
}

.memo {
	border: 1px #C8E1FB solid
}

.style7 {
	font-weight: bold
}

#id1 {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.selected_font {
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 120%;
}

.form {
	width: 130px
}

bttcn {
	color: white;
	font-weight: bold;
}
</style>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>

		<script language="javascript">
var path = '<%=path%>';
function ShowHideSearchRegion(trObject, SelfObject) {
	if (trObject.style.display == "none") {
		trObject.style.display = ""
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

	} else {
		trObject.style.display = "none"
		SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
	}
}
function showIdList() {

	window.open(
					'findBargainList.jsp',
					'',
					'width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
}
function vCode() {
	var accCode = document.form1.workSn.value;
	if (accCode == "") {
		alert("不能为空！")
		return


          }
               window.open('accountCode.jsp?accountId=0&accountCode='+accCode,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }

        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！")
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        function fanhui(){        
               document.form1.action=path+"/web/appJSP/electricmanage/bargainbill/mainBargainBill.jsp";
               document.form1.submit();
        }
 $(function(){
			$("#liulan11").click(function(){
				showIdList();
			});	
			$("#fanhui").click(function(){
				fanhui();
			});
		});       
</script>
	</head>
	<jsp:useBean id="roleBean" scope="page"
		class="com.noki.mobi.sys.javabean.RoleBean">
	</jsp:useBean>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<body class="body" style="overflow-x: hidden;">

		<form action="" name="form1" method="POST">
			<div style="width:700px;height:50px">
				<img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
				<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">添加电费单</span>
			</div>
			<table border="0" align="center" width="97%">
				<tr>
					<td>
						<img src="<%=path%>/images/v.gif" width="15" height="15" /><b><font size="2">基本信息</font></b>
	 					<table width="100%" align="right" CellSpacing="1">
				     		<tr>
								<td colspan="3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<td height="49" bgcolor="#FFFFFF">
											<table width="100%" border="0" cellspacing="1"
												cellpadding="1">

												<tr class="selected_font">
													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															电表ID：

														</div>
													</td>
													<td width="25%">
														<input type="text" name="ammeterdegreeidFk" value=""
															readonly="true" class="selected_font" style="" />
														<span class="style1">&nbsp;*</span>
														<img id="liulan11" alt="浏览"
															src="<%=request.getContextPath()%>/images/liulan1.png"
															style="cursor: pointer; width: 15px; height: 15px;" />
													</td>

													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															费用调整：
														</div>
													</td>
													<td>
														<input type="text" name="floatpay" value=""
															class="selected_font" />
													</td>
												</tr>

												<tr class="selected_font">
													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															本次单价：
														</div>
													</td>
													<td width="35	%">
														<input type="text" name="unitprice" value=""
															class="selected_font" />

													</td>
													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															开票时间：
														</div>
													</td>
													<td>
														<input type="text" name="kptime" value=""
															onFocus="getDateString(this,oCalendarChs)"
															class="selected_font" />
													</td>

												</tr>

												<tr class="selected_font">
													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															实际电费金额：
														</div>
													</td>
													<td>
														<input type="text" name="actualpay" value=""
															class="selected_font" maxlength="11" />

													</td>
													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															票据时间：
														</div>
													</td>
													<td>
														<input type="text" name="notetime" value=""
															onFocus="getDateString(this,oCalendarChs)"
															class="selected_font" maxlength="11" />

													</td>

												</tr>


												<tr class="selected_font">
													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															票据类型：
														</div>
													</td>
													<td>
														<select name="notetypeid" class="selected_font">
															<option value="0">
																请选择
															</option>
															<%
																ArrayList feetypelist = new ArrayList();
																feetypelist = commBean.getNoteType();
																if (feetypelist != null) {
																	String sfid = "", sfnm = "";
																	int scount = ((Integer) feetypelist.get(0)).intValue();
																	for (int i = scount; i < feetypelist.size() - 1; i += scount) {
																		sfid = (String) feetypelist.get(i
																				+ feetypelist.indexOf("CODE"));
																		sfnm = (String) feetypelist.get(i
																				+ feetypelist.indexOf("NAME"));
															%>
															<option value="<%=sfid%>"><%=sfnm%></option>
															<%
																}
																}
															%>
														</select>
													</td>

													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															支付类型：
														</div>
													</td>
													<td>
														<select name="bargain" class="selected_font">
															<option value="0">
																请选择
															</option>
															<%
																ArrayList bartypelist = new ArrayList();
																bartypelist = commBean.getBargainType();
																if (bartypelist != null) {
																	String sfid = "", sfnm = "";
																	int scount = ((Integer) bartypelist.get(0)).intValue();
																	for (int i = scount; i < bartypelist.size() - 1; i += scount) {
																		sfid = (String) bartypelist.get(i
																				+ bartypelist.indexOf("CODE"));
																		sfnm = (String) bartypelist.get(i
																				+ bartypelist.indexOf("NAME"));
															%>
															<option value="<%=sfid%>"><%=sfnm%></option>
															<%
																}
																}
															%>
														</select>
													</td>

												</tr>

												<tr class="selected_font">
													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															交费操作员：
														</div>
													</td>
													<td>
														<input type="text" name="payoperator" value=""
															class="selected_font" />
													</td>
													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															交费时间：
														</div>
													</td>
													<td>
														<input type="text" name="paydatetime" value=""
															onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"
															class="selected_font" />
													</td>
												</tr>
												<tr class="selected_font">
													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															报账月份：
														</div>
													</td>
													<td>
														<input type="text" name="accountmonth" value=""
															onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
															class="selected_font" />
													</td>
													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															票据编号：
														</div>
													</td>
													<td>
														<input type="text" name="noteno" value=""
															class="selected_font" />
													</td>
												</tr>
												<tr class="selected_font">
													<td height="8%" bgcolor="#DDDDDD" width="15%">
														<div align="left">
															备注：
														</div>
													</td>
													<td>
														<input type="text" name="memo" value=""
															class="selected_font" />
													</td>
												</tr>
											</table>
											<br />
											<table width="100%" border="0" cellspacing="1"
												cellpadding="1">
												<tr class="selected_font">

													<td colspan="4" align="right">

														<div id="fanhui"
															style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 260px">
															<img src="<%=path%>/images/quxiao.png" width="100%"
																height="100%">
															<span
																style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
														</div>
													</td>
												</tr>


											</table>
										</td>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br />

		</form>
	</body>
</html>

