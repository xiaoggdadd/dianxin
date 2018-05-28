<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
	System.out.print(roleId+"   string                        string");
	String sheng = (String) session.getAttribute("accountSheng");
	String loginName = (String) session.getAttribute("loginName");
	String bumen = request.getParameter("bumen") != null ? request
			.getParameter("bumen") : "";
	String bumenid = request.getParameter("bumenid") != null ? request
			.getParameter("bumenid") : "";
	int sign=1;//标志位  查询角色类型
    if(roleId.equals("1")){
	   sign=0;//如果roleid是1管理员权限  查询角色的标志位为0 表示查询全部角色
    }
%>
<html>
	<head>
		<title>addAccount</title>
		<style>
.btn {
	BORDER-RIGHT: #7b9ebd 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7b9ebd 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #cecfde );
	BORDER-LEFT: #7b9ebd 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7b9ebd 1px solid
}

.btn1_mouseout {
	BORDER-RIGHT: #7EBF4F 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7EBF4F 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #B3D997 );
	BORDER-LEFT: #7EBF4F 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7EBF4F 1px solid
}

.btn1_mouseover {
	BORDER-RIGHT: #7EBF4F 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7EBF4F 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #CAE4B6 );
	BORDER-LEFT: #7EBF4F 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #7EBF4F 1px solid
}

.btn2 {
	padding: 2 4 0 4;
	font-size: 12px;
	height: 23;
	background-color: #ece9d8;
	border-width: 1;
}

.btn3_mouseout {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #C3DAF5 );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

.btn3_mouseover {
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

.btn3_mousedown {
	BORDER-RIGHT: #FFE400 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #FFE400 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #C3DAF5 );
	BORDER-LEFT: #FFE400 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #FFE400 1px solid
}

.btn3_mouseup {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #C3DAF5 );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

.btn_2k3 {
	BORDER-RIGHT: #002D96 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #002D96 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #FFFFFF, EndColorStr = #9DBCEA );
	BORDER-LEFT: #002D96 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #002D96 1px solid
}

.style1 {
	color: #FF9900;
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
</style>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/tx.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
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

function saveAccount() {

	if (checkNotnull(document.form1.accountName, "账号")
			&& checkNotnull(document.form1.name, "姓名")) {

		if (document.form1.accountName.value.length < 3
				|| document.form1.accountName.value.length > 18) {
			alert("帐号长度为3-18");
			return;
		}
		
		var phone=/^\d{11}$/;
						if(!phone.exec(document.form1.mobile.value)){
						alert("手机号码应为11位数字！");
						document.form1.mobile.focus();
						return ;
						}

		if (document.form1.role.value == "") {
			alert("请选择角色！");
			return


                     	}
                     	
                     	if(document.form1.xian.value=="0"){
                         	alert("请选择区县！");
                                 return
                     	}
                     	if(document.form1.shi.value=="0"){
                         	alert("请选择市！");
                                 return
                     	}
                     	if(document.form1.xiaoqu.value=="0"){
                         	alert("请选择乡镇！");
                                 return
                     	}
                     	
											
                        if(confirm("您将要添加新账户的信息！确认信息正确！")){
                         	document.form1.action=path+"/servlet/account?action=add"
        			document.form1.submit()
                         }
                     
        	}

	}
        function vName(){
         	var accName = document.form1.accountName.value;
                 if(accName==""){
           	alert("不能为空！")
                   return
          }
               window.open('accountName.jsp?accountId=0&accountName='+accName,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        function vCode(){
          var accCode = document.form1.workSn.value;
          if(accCode==""){
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
        function showBMList(){
        		var accountName = document.getElementById("accountName").value;
        		var name=document.getElementById("name").value;
        		
        		var tel=document.getElementById("tel").value;
        		var mobile=document.getElementById("mobile").value;
        		
        		
        		var zip=document.getElementById("zip").value;
        		var email=document.getElementById("email").value;
        		var position=document.getElementById("position").value;
        		var address=document.getElementById("address").value;
        		var memo=document.getElementById("memo").value;

        		//var canShuStr="showBuMenList.jsp?accountName='"+accountName+"'&name='"+name+"'&tel='"+tel+"'&mobile='"+mobile+"'&zip='"+zip+"'&email='"+email+"'&position='"+position+"'&address='"+address+"'&memo='"+memo+"'";
        		var canShuStr="showBuMenList.jsp?accountName="+accountName+"&name="+name+"&tel="+tel+"&mobile="+mobile+"&zip="+zip+"&email="+email+"&position="+position+"&address="+address+"&memo="+memo;
        		
               window.open(canShuStr,'','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
         function fanhui(){
			document.form1.action=path+"/web/sys/accountList.jsp";
            document.form1.submit()
        }
          $(function(){
			
			$("#resetBtn").click(function(){
				$.each($("form input[type='text']"),function(){
					$(this).val("");
				})
			});
			$("#baocun").click(function(){
				saveAccount();
			});
			$("#liulan11").click(function(){
				//showBMList();
				getbm();
			});
			$("#jiancha").click(function(){
				vName();
			});
			$("#cancelBtn").click(function(){
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
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<%
		String ssagcode = ztcommon.getLastAgcode(loginName);
	%>
	<body class="body" style="overflow-x: hidden;">

		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">

				<tr>

					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="4">
												&nbsp;
											</td>
										</tr>

										<tr>
											<td colspan=1 width="600"
												background="<%=path%>/images/btt2.bmp" height=63>
												<span style="color: black; font-weight: bold;">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加操作员</span>
											</td>

											<td width="380">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="4"></td>
											<td>
												<table width="100%" border="0" align="right" cellpadding="1"
													cellspacing="1">
													<tr>
														<td height="49" bgcolor="#FFFFFF">
															<table width="100%" border="0" cellspacing="1"
																cellpadding="1">
																<br>

																<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>

																<tr>
																	<td height="8%" bgcolor="#DDDDDD" width="15%">
																		<div align="center">
																			账号
																		</div>
																	</td>
																	<%
																		String accountName = request.getParameter("accountName");
																		accountName = accountName == null ? "" : accountName;
																	%>

																	<td width="35%">
																		<input type="text" id="accountName" 
																			style="width: 130px;" name="accountName" 
																			value="<%=accountName%>" class="form"
																			onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" />
																		<span class="style1">&nbsp;*</span>
																		<img id="jiancha" alt=""
																			src="<%=request.getContextPath()%>/images/jiancha.png" />
																	</td>
																	<td height="19" bgcolor="#DDDDDD" width="15%">
																		<div align="center">
																			姓名
																		</div>

																	</td>
																	<%
																		String name = request.getParameter("name");
																		name = name == null ? "" : name;
																	%>
																	<td width="35%">
																		<input type="text" name="name" value="<%=name%>"
																			class="form" style="width: 130px;" maxlength="25" />
																		<span class="style1">&nbsp;*</span>

																	</td>
																</tr>

																<tr>

																	<td height="19" bgcolor="#DDDDDD">
																		<div align="center">
																			市
																		</div>
																	</td>
																	<td>
																		<select name="shi" id="shi" style="width: 130;"
																			onchange="changeShi()">
																			<option value="0">
																				请选择
																			</option>
																			<%
																				ArrayList shilist = new ArrayList();
																				shilist = ztcommon.getAgcode(ssagcode, 5);
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
																	<tr>
																		<tr>
																			<td height="8%" bgcolor="#DDDDDD">
																				<div align="center">
																					区县
																				</div>
																			</td>
																			<td>
																				<select name="xian" id="xian" style="width: 130"
																					onchange="changeXian()">
																					<option value="0">
																						请选择
																					</option>
																				</select>
																			</td>
																			<td height="19" bgcolor="#DDDDDD">
																				<div align="center">
																					乡镇
																				</div>
																			</td>
																			<td>
																				<select name="xiaoqu" id="xiaoqu" style="width: 130">
																					<option value="0">
																						请选择
																					</option>
																				</select>
																			</td>

																			<tr>
																				<tr>
																					<td height="19" bgcolor="#DDDDDD">
																						<div align="center">
																							座机
																						</div>
																					</td>
																					<%
																						String tel = request.getParameter("tel");
																						tel = tel == null ? "" : tel;
																					%>
																					<td>
																						<input type="text" name="tel" value="<%=tel%>" maxlength="11"
																							class="form" style="width: 130px;"/>
																					</td>
																					<td height="19" bgcolor="#DDDDDD">
																						<div align="center">
																							手机hhh
																						</div>
																					</td>
																					<%
																						String mobile = request.getParameter("mobile");
																						mobile = mobile == null ? "" : mobile;
																					%>
																					<td>
																						<input type="text" name="mobile" 
																							value="<%=mobile%>" class="form" 
																							style="width: 130px;" maxlength="1"/>

																					</td>

																					<tr>

																						<%
																							ArrayList list = new ArrayList();
																							list = roleBean.getAllRole(sign);
																							int countColum = ((Integer) list.get(0)).intValue();
																							String roleId2 = "", roleName2 = "";
																						%>
																						<td height="19" bgcolor="#DDDDDD">
																							<div align="center">
																								角色
																							</div>
																						</td>
																						<td>
																							<select name="role" style="width: 130"
																								class="form" multiple="multiple">

																								<%
																									for (int i = countColum; i < list.size() - 1; i += countColum) {
																										roleId2 = (String) list.get(i + list.indexOf("ROLEID"));
																										roleName2 = (String) list.get(i + list.indexOf("NAME"));
																								%>
																								<option value="<%=roleId2%>"><%=roleName2%></option>
																								<%
																									}
																								%>

																							</select>
																							<span class="style1">&nbsp;*</span>
																						</td>

																						<td height="19" bgcolor="#DDDDDD">
																							<div align="center">
																								性别
																							</div>
																						</td>
																						<td>
																							<select name="sex" style="width: 130"
																								class="form">
																								<option value="1">
																									男
																								</option>
																								<option value="0">
																									女
																								</option>
																							</select>
																						</td>
																					</tr>


																					<tr>
																						<td height="19" bgcolor="#DDDDDD">
																							<div align="center">
																								邮编
																							</div>
																						</td>
																						<%
																							String zip = request.getParameter("zip");
																							zip = zip == null ? "" : zip;
																						%>
																						<td>
																							<input type="text" name="zip" value="<%=zip%>" maxlength="6"
																								class="form" align="right" style="width: 130px;" />
																						</td>
																						<td height="19" bgcolor="#DDDDDD">
																							<div align="center">
																								邮箱
																							</div>
																						</td>
																						<%
																							String email = request.getParameter("email");
																							email = email == null ? "" : email;
																						%>
																						<td>
																							<input type="text" name="email"
																								value="<%=email%>" class="form"
																								style="width: 130px;" />
																						</td>
																					</tr>



																					<tr>
																						<td height="19" bgcolor="#DDDDDD">
																							<div align="center">
																								职位
																							</div>
																						</td>
																						<%
																							String position = request.getParameter("position");
																							position = position == null ? "" : position;
																						%>
																							<td>
	     																				<select name="position" class="form" > 
                                                                                            <option value="0">请选择</option>
            																				 <%
	         																					ArrayList stationtype = new ArrayList();
         																						stationtype = ztcommon.getSelctOptions("zw");
	         																					if(stationtype!=null){
	         																						String code="",name1="";
	         																						int cscount = ((Integer)stationtype.get(0)).intValue();
	         																						for(int i=cscount;i<stationtype.size()-1;i+=cscount)
                   																					 {
                    																						code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
                    																						name1=(String)stationtype.get(i+stationtype.indexOf("NAME"));
                    																					%>
                                   																	<option value="<%=code%>"><%=name1%></option>
                   																					 <%}
	         																						}
	         																						%>
                                																</select>
	            																			 </td>

																						<td height="19" bgcolor="#DDDDDD">
																							<div align="center">
																								部门
																							</div>
																						</td>

																						<td>

																							<input type="hidden" name="bumen" value="<%=bumenid%>" class="form" />
																							<input type="text" name="bumenid" value="<%=bumen%>" class="form" style="width: 130px;" maxlength="40"/>
																							<img id="liulan11" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png " style="cursor: pointer" />
																					</tr>

																					<tr>
																						<td height="19" bgcolor="#DDDDDD">
																							<div align="center">
																								地址
																							</div>
																						</td>
																						<%
																							String address = request.getParameter("address");
																							address = address == null ? "" : address;
																						%>
																						<td colspan="2">
																							<input type="text" size="50" name="address"
																								value="<%=address%>" class="form" />
																						</td>
																					</tr>

																					<tr>
																						<td height="19" bgcolor="#DDDDDD">
																							<div align="center">
																								备注
																							</div>
																						</td>
																						<%
																							String memo = request.getParameter("memo");
																							memo = memo == null ? "" : memo;
																						%>
																						<td colspan="2">
																							<input type="text" size="50" name="memo"
																								value="<%=memo%>" class="form" />
																						</td>
																					</tr>

																					<tr>

																						<td>


																						</td>
																					</tr>

																					<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
															</table>

														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<div id="cancelBtn"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 14px">
							<img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
							<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
						</div>

						<div id="resetBtn"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 16px">
							<img src="<%=path%>/images/2chongzhi.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
						</div>
						<div id="baocun"
							style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right: 20px">
							<img alt=""
								src="<%=request.getContextPath()%>/images/baocun.png"
								width="100%" height="100%" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">保存</span>
						</div>
						<input type="hidden" name="sheng" value="<%=sheng%>" />
						</form>
	</body>
</html>
<script language="javascript">
function changeSheng() {
	var sheng = document.form1.sheng.value;
	if (sheng == "0") {
		var shilist = document.all.shi;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/area?action=sheng&pid=" + sheng, "sheng");
	}
}
function updateShi(res) {
	var shilist = document.all.shi;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));

	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
function changeShi() {
	var shi = document.form1.shi.value;
	var ssagcode = '<%=ssagcode%>';
	var xiaoqu = document.all.xiaoqu;
	xiaoqu.options.length = "0";
	xiaoqu.add(new Option("请选项", "0"));
	if (shi == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/area?action=shitoxian&pid=" + shi
				+ "&ssagcode=" + ssagcode, "shitoxian");
	}
}
function changeXian() {
	var shi = document.form1.xian.value;
	var ssagcode = '<%=ssagcode%>';
	if (shi == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选择", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/area?action=xiantoxiaoqu&pid=" + shi
				+ "&ssagcode=" + ssagcode, "xiantoxiaoqu");
	}
}
function updateQx(res) {
	var shilist = document.all.xian;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));

	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
function updateXQ(res) {
	var shilist = document.all.xiaoqu;
	shilist.options.length = "0";
	shilist.add(new Option("请选择", "0"));

	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
</script>
<script language="javascript">
	var path = '<%=path%>';
		function getbm(){
			var divWidth = 800;
			var bodyWidth = $("body").innerWidth();
			var leftSize = 0;//div距离左侧的位置
			if(bodyWidth>divWidth)leftSize = (bodyWidth-divWidth)/2;
			var divStr = "<div id='areaDiv'  style='position:absolute;width:"+divWidth+"px;height:400px;border:5px #cad5db groove;left:"+leftSize+"px;top:100px;background-color:green'>";
			divStr += "<iframe src='<%=path%>/web/sys/showBuMenList.jsp' name='bm' id='bm' width='"+(divWidth-5)+"px' height=100%></iframe>";
			divStr += "</div>";
			$("body").append(divStr);
		}
</script>