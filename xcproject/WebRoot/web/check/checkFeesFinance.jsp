<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean"%>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName(); 
	String loginId1 = request.getParameter("loginId");
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
	String currentmonth = formatter.format(date);
	String currentmonth1 = request.getParameter("currentmonth") != null ? request.getParameter("currentmonth"): currentmonth;
	String bztime = request.getParameter("bztime") != null ? request.getParameter("bztime") : "";
	String bztimes = request.getParameter("bztimes");
	String beginTime = request.getParameter("beginTime") != null ? request.getParameter("beginTime"): CTime.formatRealDate(new Date());
	String endTime = request.getParameter("endTime") != null ? request.getParameter("endTime") : CTime.formatRealDate(new Date());
	String manualauditstatus1 = request.getParameter("manualauditstatus1")!=null?request.getParameter("manualauditstatus1"):"1";
	String zdqyzt=request.getParameter("zdqyzt")!=null?request.getParameter("zdqyzt"):"-1";
	String dbqyzt=request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"-1";
	//System.out.println("logManage.jsp>>"+beginTime);
	String lrrq = request.getParameter("lrrq") != null ? request.getParameter("lrrq") : "";//录入日期
	String title = request.getParameter("title") != null ? request.getParameter("title") : "";
	String operName = request.getParameter("operName") != null ? request.getParameter("operName"): "";
	String pjlx = request.getParameter("pjlx") != null ? request.getParameter("pjlx"): "";
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
	String shi = request.getParameter("shi") != null ? request.getParameter("shi") : agcode1;
	String xian = request.getParameter("xian") != null ? request.getParameter("xian") : "0";
	String xiaoqu = request.getParameter("xiaoqu") != null ? request.getParameter("xiaoqu") : "0";
	String jizhan = request.getParameter("zhandian") != null ? request.getParameter("zhandian") : "";
	String lch = request.getParameter("lch") != null ? request.getParameter("lch") : "";
	String zdlx = request.getParameter("zdlx") != null ? request.getParameter("zdlx") : "0";
	String jzproperty = request.getParameter("jzproperty") != null ? request.getParameter("jzproperty"): "0";
	String canshuStr = "";
	if ((shi != null) || (xian != null) || (xiaoqu != null)
			|| (jizhan != null) || (zdlx != null)
			|| (jzproperty != null)) {
		canshuStr = "&shi=" + shi + "&xian=" + xian + "&xiaoqu="
				+ xiaoqu + "&jizhan=" + jizhan+ "&zdlx=" + zdlx + "&jzproperty=" + jzproperty;
	}

	String s_curpage = request.getParameter("curpage") != null ? request.getParameter("curpage"): "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String permissionRights = "";
	int intnum = 0;
	String color = null;
	String roleId = (String) session.getAttribute("accountRole");
	double dd=0.0,ddf=0.0;
	double ds=0.0,dfs=0.0;
%>

<html>
	<head>
		<title></title>
		<style>

.style1 {
	color: #FF9900;
	font-weight: bold;
}

.style2 {
	color: red;
	font-weight: bold;
}

.STYLE6 {
	color: #FFFFFF;
}

.memo {
	border: 1px # #888888 solid
}

.style7 {
	font-weight: bold
}

.memo {
	border: 1px #888888 solid
}

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
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

.bttcn {
	color: BLACK;
	font-weight: bold;
}

.form {
	width: 130px
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

.selected_font1 {
	width: 100px;
	font-family: 宋体;
	font-size: 10px;
	line-height: 100%;
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}
</style>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>


		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
		<script type="text/javascript">
//=点击展开关闭效果=

function openShutManager(oSourceObj, oTargetObj, shutAble, oOpenTip, oShutTip) {
	var sourceObj = typeof oSourceObj == "string" ? document
			.getElementById(oSourceObj) : oSourceObj;
	var targetObj = typeof oTargetObj == "string" ? document
			.getElementById(oTargetObj) : oTargetObj;
	var openTip = oOpenTip || "";
	var shutTip = oShutTip || "";
	if (targetObj.style.display != "none") {
		if (shutAble)
			return;
		targetObj.style.display = "none";
		if (openTip && shutTip) {
			sourceObj.innerHTML = shutTip;
		}
	} else {
		targetObj.style.display = "block";
		if (openTip && shutTip) {
			sourceObj.innerHTML = openTip;
		}
	}
}
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

function chaxun() {
	var beginTime = document.form1.beginTime.value
	if (checkNotnull(document.form1.beginTime, "开始时间")
			&& checkNotnull(document.form1.endTime, "结束时间")) {
		document.form1.action = path + "/web/sys/logManage.jsp?beginTime="
				+ beginTime
		document.form1.submit()
	}
}
function delLogs() {
	var beginTime = document.form1.delBeginTime.value
	var endTime = document.form1.delEndTime.value

	if (checkNotnull(document.form1.delBeginTime, "开始时间")
			&& checkNotnull(document.form1.delEndTime, "结束时间")) {
		if (beginTime > endTime) {
			alert("开始时间不能大于结束时间！")
			return


               		 }
                      if(confirm("确定要删除，不可恢复！")){
                 	  document.form1.action=path+"/servlet/log?delBeginTime="+beginTime+"&delEndTime="+endTime
                           document.form1.submit()
               		 }
                }

	}
	 //审核通过
/*	function passCheck(){
        var m = document.getElementsByName('test[]');   
        var cuu=document.form1.currentmonth.value;
        var l = m.length; 
        var chooseIdStr1 = ""; 
        var chooseIdStr2 = ""; 
        var bz=0;
        for(var i = 0; i < l; i++){
          if(m[i].checked == true){
             bz+=1; 
             var j = m[i].value.toString().indexOf("ssss=");
             var chooseIdStr3 = m[i].value.toString().substring(0,j);
             var zflx1 = m[i].value.toString().substring(j+5,m[i].value.toString().length);
             if(zflx1=="月结"||zflx1=="预支"){
	             chooseIdStr1 = chooseIdStr1 +"'" +chooseIdStr3 +"',";
             }else if(zflx1=="合同"||zflx1=="插卡"){
             	 chooseIdStr2 = chooseIdStr2 +"'" + chooseIdStr3 +"',";
             } 
          }           
        } 
        chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
        chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
	   if(bz<=240){ 
		    if(bz>=1){
		       if(cuu!=""&&cuu!=null){
		          document.form1.action=path+"/servlet/check?action=checkff&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2;
		          document.form1.submit();
		       }else{
		       alert("会计月份不能为空！");
		       }
	        }else{
	          alert("请选择信息！");
	        }
	   }else{
	      	alert("您选择信息条数超过240条，信息量过大，请确定后重新执行！");
	   }  
    }*/
  function passCheck(){
        var m = document.getElementsByName('test[]');
        var cuu=document.form1.currentmonth.value;
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr1 = ""; 
       	var chooseIdStr2 = ""; 
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       	}
       	if(count!=0){
       		if(cuu!=""&&cuu!=null){
		       	if(count%240==0){
		       		n=count/240-1;
		       	}else{
		       		n=(count-(count%240))/240;
		       	}
		        for(var i = 0; i < l; i++){
		         if(m[i].checked == true){
		             bz+=1;
		             count1+=1;
		             var j = m[i].value.toString().indexOf("ssss=");
		             var chooseIdStr3 = m[i].value.toString().substring(0,j);
		             var zflx1 = m[i].value.toString().substring(j+5,m[i].value.toString().length);
		             if(zflx1=="月结"){
			             chooseIdStr1 = chooseIdStr1 +"'" +chooseIdStr3 +"',";
		             }else if(zflx1=="预支"){
		             	count2+=1;
		             	chooseIdStr1 = chooseIdStr1 +"'"+ chooseIdStr3 +"',";
		             	chooseIdStr2 = chooseIdStr2 +"'"+ chooseIdStr3 +"',";
		             }else if(zflx1=="合同"||zflx1=="插卡"){
		             	 chooseIdStr2 = chooseIdStr2 +"'" + chooseIdStr3 +"',";
		             } 
		        }
		          if(count1<=240*n||((bz+count2)>=239&&(bz+count2)<=241)){
		         	  if(((bz+count2)/240==1)||((bz+count2)>=239&&(bz+count2)<=241)){
				        chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
				        chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
				        document.form1.action=path+"/servlet/check?action=checkff1&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2;
			            chooseIdStr1 = ""; 
		       			chooseIdStr2 = "";
		       			bz=0;
		       			count2=0;
			            document.form1.submit();	          	
			          }
			       }else if(count==count1&&bzw==1){
			          chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
				      chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
				      bzw=0;
			          document.form1.action=path+"/servlet/check?action=checkff&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2;
			          document.form1.submit(); 
			       }            
		        } 
		   }else{
		       alert("会计月份不能为空！");
		   }
        }else{
          alert("请选择信息！");
        }
   }
    function delad(electricfeeId){
       if(confirm("您确定删除此电费信息？")){
              document.form1.action=path+"/servlet/electricfees?action=del&degreeid="+electricfeeId;
       	      document.form1.submit();
       }
    }
    function modifyad(electricfeeId){
               document.form1.action=path+"/web/electricfees/modifyElectricFees.jsp?degreeid="+electricfeeId;
               document.form1.submit();
  
    }
    
    function queryDegree(){
           
            document.form1.action=path+"/web/check/checkFeesFinance.jsp?command=chaxun";
            document.form1.submit();
            showdiv("请稍等..............");
       
    }
    $(function(){
		

		$("#tongguo").click(function(){
			passCheck();
			showdiv("请稍等..............");
		});

		$("#butongguo").click(function(){
			passCheckNo();
			showdiv("请稍等..............");
		});
		$("#chaxun").click(function(){
			queryDegree();
		});
		$("#daochuBtn").click(function(){
			exportad();
		});
	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonAccountBean"></jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
	<%
		permissionRights = commBean.getPermissionRight(roleId, "0806");

	//	System.out.println(">>>>>>>>>>>>>>..." + permissionRights);
	%>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
				<tr>
					<td colspan="4">
							<div style="width:700px;height:50px">
							  
							  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">财务电费审核</span>	
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
											ArrayList shenglist = new ArrayList();
											shenglist = commBean.getShi(loginId);
											if (shenglist != null) {
												String sfid = "", sfnm = "";
												int scount = ((Integer) shenglist.get(0)).intValue();
												for (int i = scount; i < shenglist.size() - 1; i += scount) {
													sfid = (String) shenglist.get(i
															+ shenglist.indexOf("AGCODE"));
													sfnm = (String) shenglist.get(i
															+ shenglist.indexOf("AGNAME"));
										%>
										<option value="<%=sfid%>" selected="selected"><%=sfnm%></option>
										<%
											}
											}
										%>
									</select>
								</td>


								<td>
									区县:
								</td>
								<td>
									<select name="xian" class="selected_font"
										onchange="changeCountry()">
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


								<td>
									乡镇：
								</td>
								<td>
									<select name="xiaoqu" class="selected_font"
										onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList xiaoqulist = new ArrayList();
											xiaoqulist = commBean.getAgcode(xian, account.getAccountId());
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
								</td>
								<td>
									<p>
										<font size="2">
											<div title="您可以进行详细的条件筛选" id="query1"
												onclick="openShutManager(this,'box3',false)"
												style="position: relative; width: 17px; height: 17px; cursor: pointer; top: 10PX">
												<img alt=""
													src="<%=request.getContextPath()%>/images/gaojichaxun.gif"
													width="100%" height="100%" />
												<span
													style="font-size: 12px; position: absolute; left: 2px; top: 0px; color: white">&nbsp;&nbsp;&nbsp;</span>
											</div> </font>
									</p>
								</td>
								<td>
									<%
										if (permissionRights.indexOf("PERMISSION_SEARCH") >= 0) {
										System.out.println("身份-----"+ permissionRights.indexOf("PERMISSION_SEARCH"));
									%>
									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -250px; float: right; top: 0px">
										<img alt="" src="<%=path%>/images/chaxun.png" width="100%"
											height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
									</div>
									<%
										}
									%>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<div style="width: 88%;">
				<p id="box3" style="display: none">
					<table>
						<tr class="form_label">
							<td>
								站点名称：
							</td>
							<td>
								<input type="text" name="zhandian"
									value="<%if (null != request.getParameter("zhandian"))
				out.print(request.getParameter("zhandian"));%>"
									class="selected_font" />
							</td>
							<td>
								站点类型：
							</td>
							<td>
								<select name="zdlx" class="selected_font">
									<option value="0">
										请选择
									</option>
									<%
										ArrayList stationtype = new ArrayList();
										stationtype = ztcommon.getSelctOptions("StationType");
										if (stationtype != null) {
											String code = "", name = "";
											int cscount = ((Integer) stationtype.get(0)).intValue();
											for (int i = cscount; i < stationtype.size() - 1; i += cscount) {
												code = (String) stationtype.get(i
														+ stationtype.indexOf("CODE"));
												name = (String) stationtype.get(i
														+ stationtype.indexOf("NAME"));
									%>
									<option value="<%=code%>"><%=name%></option>
									<%
										}
										}
									%>
								</select>
							</td>
							<td>
								站点属性:
							</td>
							<td>
								<select name="jzproperty" class="selected_font"
									onchange="kzinfo()">
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
							
						</tr>
						<tr class="form_label">
							<td>
								流程号：
							</td>
							<td>
								<input type="text" name="lch"
									value="<%if (null != request.getParameter("lch"))
				out.print(request.getParameter("lch"));%>"
									class="selected_font" />
							</td>
							<td>
								报账月份：
							</td>
							<td>
								<input type="text" class="selected_font" name="bztime"
									value="<%if (null != request.getParameter("bztime"))
				out.print(request.getParameter("bztime"));%>"
									onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
							</td>
							<td>
								录入日期：
							</td>
							<td>
								<input type="text" class="selected_font" name="lrrq"
									value="<%if (null != request.getParameter("lrrq"))
				out.print(request.getParameter("lrrq"));%>"
									onFocus="getDateString(this,oCalendarChs)" class="form" />
							</td>
						</tr>
						<tr class="form_label">
							<td>站点启用状态</td>
							<td><select name="zdqyzt" class="selected_font">
								<option value="-1">请选择</option>
								<option value="1">启用</option>
								<option value="0">未启用</option>
							</select></td>
							<td>电表启用状态</td>
							<td><select name="dbqyzt" class="selected_font">
								<option value="-1">请选择</option>
								<option value="1">启用</option>
								<option value="0">未启用</option>
							</select></td>
							<td>财务审核状态：</td>
						<td><select name="manualauditstatus1" class="selected_font">
			         		<option value="-2">请选择</option>
			         		<option value="2">审核通过</option>
			         		<option value="1">审核未通过</option>    
			         		<option value="-1">审核不通过</option>         		
		         		</select></td>
						</tr>
						<tr class="form_label">
           		<td>票据类型</td>
           		<td><select name="pjlx" class="selected_font" > 
                      <option value="0">请选择</option>
			              <%
			                ArrayList listpjlx = new ArrayList();
			              listpjlx = ztcommon.getSelctOptions("pjlx");
			         		
				         	if(listpjlx!=null){
				         		String code="",name="";
				         		int cscount = ((Integer)listpjlx.get(0)).intValue();
				         		for(int i=cscount;i<listpjlx.size()-1;i+=cscount)
			                    {
			                    	code=(String)listpjlx.get(i+listpjlx.indexOf("CODE"));
			                    	name=(String)listpjlx.get(i+listpjlx.indexOf("NAME"));
			                    %>
			                                   <option value="<%=code%>"><%=name%></option>
			                    <%}
				         	}
				         %>
			                 </select></td>
			           </tr>
					</table>
				</p>
			</div>

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
				ElectricFeesBean bean = new ElectricFeesBean();
				String whereStr = "";
				String str = "";
				String str1 = "";
				String str2 = "";
				//System.out.println("dianliangListjsp:" + jizhan + beginTimeQ + endTimeQ);

				if (shi != null && !shi.equals("") && !shi.equals("0")) {
					whereStr = whereStr + " and zd.shi='" + shi + "'";
					str = str + " and zd.shi='" + shi + "'";
				}
				if (xian != null && !xian.equals("") && !xian.equals("0")) {
					whereStr = whereStr + " and zd.xian='" + xian + "'";
					str = str + " and zd.xian='" + xian + "'";
				}
				if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
					whereStr = whereStr + " and zd.xiaoqu='" + xiaoqu + "'";
					str = str + " and zd.xiaoqu='" + xiaoqu + "'";
				}
				if (bztime != null && bztime != "" && !bztime.equals("0")) {
					whereStr = whereStr + " and to_char(ef.ACCOUNTMONTH,'yyyy-mm') ='" + bztime + "'";
					str = str + " and to_char(ef.ACCOUNTMONTH,'yyyy-mm') ='" + bztime + "'";
				}
				if (jizhan != null && !jizhan.equals("") && !jizhan.equals("0")) {

					whereStr = whereStr + " and zd.jzname like '%" + jizhan + "%'";
				}
				if (lch != null && lch != "" && !lch.equals("0")) {
					whereStr = whereStr + " and ef.liuchenghao='" + lch + "'";
					str = str + " and ef.liuchenghao='" + lch + "'";
				}
				if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
					whereStr = whereStr + " and zd.STATIONTYPE='" + zdlx + "'";
					str = str + " and zd.STATIONTYPE='" + zdlx + "'";
				}
				if (jzproperty != null && !jzproperty.equals("")
						&& !jzproperty.equals("0")) {
					whereStr = whereStr + " and zd.PROPERTY='" + jzproperty + "'";
					str = str + " and zd.PROPERTY='" + jzproperty + "'";
				}
				if (lrrq != null && lrrq != "" && !lrrq.equals("0")) {
					whereStr = whereStr + " and to_char(ef.entrytime,'yyyy-mm-dd') like '%" + lrrq + "%'";
				

				}
            	if(manualauditstatus1 != null && !manualauditstatus1.equals("") && !manualauditstatus1.equals("-2")){
					str1=str1+" AND EF.MANUALAUDITSTATUS = '"+manualauditstatus1+"'";
					str2=str2+" AND EF.FINANCEAUDIT='"+manualauditstatus1+"'";
				}
            	if(zdqyzt!=null && !zdqyzt.equals("") &&!zdqyzt.equals("-1")){
            		str1=str1+"AND ZD.QYZT='"+zdqyzt+"'";
            		str2=str2+"AND ZD.QYZT='"+zdqyzt+"'";
            	}
            	if(dbqyzt!=null && !dbqyzt.equals("") &&!dbqyzt.equals("-1")){
            		str1=str1+"AND D.DBQYZT='"+dbqyzt+"'";
            		str2=str2+"AND D.DBQYZT='"+zdqyzt+"'";
            	}
            	if(pjlx!=null &&!pjlx.equals("") &&!pjlx.equals(" ")){
            		str1=str1+"AND EF.NOTETYPEID='"+pjlx+"'";
            		str2=str2+"AND EF.NOTETYPEID='"+pjlx+"'";
            	}
            	
				//System.out.println("ElectricFeesBean-whereStr:" + whereStr);
				if (loginId1 != null && !loginId1.equals("")) {
					loginId = loginId1;
					shi = "1";
				}
				 //更改2012-12-5
		      /*  String count1="0";
			    String count2="0.00";
		       	if("chaxun".equals(request.getParameter("command"))||"daochu".equals(request.getParameter("command"))){
		       		ElectricFeesFormBean bean1 = bean.getCountcwdfsh(whereStr,str1,str2,loginId);
		            count1= bean1.getCount();
		            count2= bean1.getActualpay();
		        
				//String count1 = bean.getCountcwdfsh(whereStr, loginId, str1);
				//String count2 = bean.getCountGreecwdfsh(whereStr, loginId, str2);

				if (count2 == null || count2 == "" || count2.equals("")
						|| count2.equals("null")) {
					count2 = "0.00";
				} else {
					DecimalFormat countdl = new DecimalFormat("0.00");
					count2 = countdl.format(Double.parseDouble(count2));
				}*/
			%>
			<%--<table height="5%">
				<tr>
					<td>
						<font size="2">总共</font>
					</td>
					<td>
						<font size="2" color="red"><%=count1%></font><font size="2">条
							|</font>
					</td>
					<td>
						<font size="2">实际电费金额总和</font>
					</td>
					<td>
						<font size="2" color="red">&nbsp;<%=count2%></font><font size="2">元</font>
					</td>

				</tr>
			</table>
			<%}  %>

			--%><div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="2300px" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()" />全选</div></td>
						<td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>		
						<td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
						<td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>						
						<td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td>
						<td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次读数</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次读数</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始月份</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束月份</div></td>
						<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">倍率</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电量调整</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">耗电量</div></td>
						<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费单价</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费调整</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际电费金额</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
						<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据类型</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据金额</div></td>
						
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">流程号</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
						<%--<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td>
						--%>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电流类型</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电设备</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据编号</div></td>
						<%--<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交费操作员</div></td>
						<td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交费时间</div></td>
						--%>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">自动审核</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务审核</div></td>
						<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>
					</tr>
					<%
						 List<ElectricFeesFormBean> fylist = null;
					 //更改2012-12-5
			       	if("chaxun".equals(request.getParameter("command"))||"daochu".equals(request.getParameter("command"))){
			       		fylist = bean.getPageDataCheck(curpage, whereStr,str1,str2,loginId);
			       		allpage = bean.getAllPage();
			        } else{
			        	fylist=null;
			        }
						//fylist = bean.getPageDataCheck(curpage, whereStr, loginId);
						//allpage = bean.getAllPage();
						String electricfeeId = "", pjje="",szdq="",floatpay = "", actualpay = "", notetypeid = "", liuchenghao = "", noteno = "", dfzflx = "", bzyf = "", uuid = "";
						String jzname = "", dbid = "", autoauditstatus = "", manualauditstatus = "", dllx = "", ydsb = "";
						String lastdegree="",lastdatetime="",thisdegree="",thisdatetime="",startmonth="",endmonth="",beilv="",floatdegree="",blhdl="",dianjia="",dfzflx2="";
						Double df = 0.00;
						//=================新增==========
						String zdsxx = "";
						String zdlxx = "";
						//===============xin===========		
						int countxh = 1;
						if(fylist!=null){
							//int fycount = ((Integer) fylist.get(0)).intValue();
							for (ElectricFeesFormBean bean1:fylist) {

								//num为序号，不同页中序号是连续的
								jzname = bean1.getJzname();
								jzname = jzname != null ? jzname : "";
								
								dbid = bean1.getDbid();
								dbid = dbid != null ? dbid : "";

								zdsxx = bean1.getProperty();
								zdsxx = zdsxx != null ? zdsxx : "";
								
								szdq = bean1.getSzdq();
								szdq = szdq != null ? szdq : "";
								
								lastdegree=bean1.getLastdegree();
								lastdegree = lastdegree != null ? lastdegree : "";
								
								lastdatetime=bean1.getLastdatetime();
								lastdatetime = lastdatetime != null ? lastdatetime : "";
								
								thisdegree=bean1.getThisdegree();
								thisdegree = thisdegree != null ? thisdegree : "";
								
								thisdatetime=bean1.getThisdatetime();
								thisdatetime = thisdatetime != null ? thisdatetime : "";
								
								startmonth=bean1.getStartmonth();
								startmonth = startmonth != null ? startmonth : "";
								
								endmonth=bean1.getEndmonth();
								endmonth = endmonth != null ? endmonth : "";
								
								beilv=bean1.getBeilv();
								beilv = beilv != null ? beilv : "";
								
								floatdegree=bean1.getFloatdegree();
								floatdegree = floatdegree != null ? floatdegree : "";
								
								blhdl=bean1.getBlhdl();
								blhdl = blhdl != null ? blhdl : "";
								
								dianjia=bean1.getUnitprice();
								dianjia = dianjia != null ? dianjia : "";
								
								pjje=bean1.getPjjedf();
								pjje = pjje != null ? pjje : "";
								
								floatpay=bean1.getFloatpay();
								floatpay = floatpay != null ? floatpay : "";


								bzyf = bean1.getAccountmonth();
								bzyf = bzyf != null ? bzyf : "";

								liuchenghao = bean1.getLiuchenghao();
								liuchenghao = liuchenghao != null ? liuchenghao : "";

								zdlxx = bean1.getStationtype();
								zdlxx = zdlxx != null ? zdlxx : "";

								dllx = bean1.getDllx();
								dllx = dllx != null ? dllx : "";
								if ("null".equals(dllx)) {
									dllx = "";
								}
								
								ydsb = bean1.getYdsb();
								ydsb = ydsb != null ? ydsb : "";

								autoauditstatus = bean1.getAutoauditstatus();
								autoauditstatus = autoauditstatus != null ? autoauditstatus: "";
								
								manualauditstatus = bean1.getManualauditstatus();
								manualauditstatus = manualauditstatus != null ? manualauditstatus: "";
								
								dfzflx2 = bean1.getDfzflx();
								dfzflx2 = dfzflx2 != null ? dfzflx2 : "";
								
								electricfeeId = bean1.getElectricfeeId();
								electricfeeId = electricfeeId != null ? electricfeeId : "";
								
								uuid = bean1.getDfuuid();
								uuid = uuid != null ? uuid : "";

								actualpay = bean1.getActualpay();
								actualpay = actualpay != null ? actualpay : "";
								if (actualpay.equals("null")) {
									actualpay = "0";
								}
								notetypeid = bean1.getNotetypeid();
								notetypeid = notetypeid != null ? notetypeid : "";
								
								noteno = bean1.getNoteno();
								noteno = noteno != null ? noteno : "";
								if (noteno.equals("null")) {
									noteno = "";
								}
								
								
								DecimalFormat mat = new DecimalFormat("0.00");
								DecimalFormat mat1 = new DecimalFormat("0.0");
								if (actualpay == null || actualpay == ""){
									actualpay = "0";
								    df = Double.parseDouble(actualpay);
								    actualpay = mat.format(df);
								}
								//System.out.println("放假吧！"+lastdegree+"ee333");
								if(lastdegree!=null&&lastdegree!=""&&!" ".equals(lastdegree)){
									//System.out.println("放假吧！"+lastdegree);
									double last = Double.parseDouble(lastdegree);
									lastdegree = mat1.format(last);
								}
								if(thisdegree!=null&&thisdegree!=""&&!" ".equals(thisdegree)){
									double last = Double.parseDouble(thisdegree);
									thisdegree = mat1.format(last);
								}
								if(floatdegree!=null&&floatdegree!=""&&!" ".equals(floatdegree)){
									double last = Double.parseDouble(floatdegree);
									floatdegree = mat1.format(last);
								}
								if(floatpay!=null&&floatpay!=""&&!" ".equals(floatpay)){
									double last = Double.parseDouble(floatpay);
									floatpay = mat.format(last);
								}
								if(blhdl!=null&&blhdl!=""&&!"".equals(blhdl)){
									double last = Double.parseDouble(blhdl);
									ddf=Double.parseDouble(blhdl);
									blhdl = mat1.format(last);
								}
								if(actualpay!=null&&actualpay!=""&&!"".equals(actualpay)){
									double last = Double.parseDouble(actualpay);
									dfs=Double.parseDouble(actualpay);
									actualpay = mat.format(last);
								}if(pjje!=null&&pjje!=""&&!"".equals(pjje)){
									double last = Double.parseDouble(pjje);
									pjje = mat.format(last);
								}
 								
								if (intnum % 2 == 0) {
									color = "#FFFFFF";

								} else {
									color = "#DDDDDD";
								}
								intnum++;
								dd=dd+ddf;
								ds=ds+dfs;
								
								if(ydsb.equals("ybsb01")){
									 ydsb="照明用电";
						           }else if(ydsb.equals("ybsb02")){
						        	   ydsb="空调用电";
						           }else if(ydsb.equals("ybsb03")){
						        	   ydsb="设备用电";
						           }else if(ydsb.equals("ybsb04")){
						        	   ydsb="其它用电设备";
						           }else if(ydsb.equals("ybsb05")){
						        	   ydsb="总表";
								   }else if(ydsb.equals("ybsb06")){
									   ydsb="综合";
						           }else{
						        	   ydsb="";
						           }
								if(zdsxx.equals("zdsx01")){
									zdsxx="局用机房";
								   }else if(zdsxx.equals("zdsx02")){
									   zdsxx="基站";
								   }else if(zdsxx.equals("zdsx03")){
									   zdsxx="营业网点";
								   }else if(zdsxx.equals("zdsx04")){
									   zdsxx="其他";
								   }else if(zdsxx.equals("zdsx05")){
									   zdsxx="接入网";
								   }else if(zdsxx.equals("zdsx06")){
									   zdsxx="乡镇支局";
								   }else{
									   zdsxx="";
								   }
								if(notetypeid.equals("pjlx05")){
									notetypeid="收据";	
								}else if(notetypeid.equals("pjlx03")){
									notetypeid="发票";
								}else{
									notetypeid="";
								}
								if(dfzflx2.equals("dfzflx01")){
					   		    	 dfzflx="月结";
					   		     }else if(dfzflx2.equals("dfzflx02")){
					   		    	 dfzflx="合同";
					   		     }else if(dfzflx2.equals("dfzflx03")){
					   		    	 dfzflx="预支";
					   		     }else if(dfzflx2.equals("dfzflx04")){
					   		    	 dfzflx="插卡";
					   		     }else{
					   		      	 dfzflx="";
					   		     }
								//System.out.println("-------"+dfzflx+"----"+dfzflx2);

					%>
					<%
						if (manualauditstatus == null || manualauditstatus == ""
										|| manualauditstatus.equals("0")) {
					%>

					<%
						} else {
					%>
					<tr bgcolor="<%=color%>">
						<td>
							<div align="center"><%=countxh++%></div>
						</td>
						<td>
							<div align="center">
								<input type="checkbox" name="test[]"
									value="<%=uuid%>ssss=<%=dfzflx%>" />
							</div>
						</td>
						<td>
							<div align="left"><%=szdq%></div>
						</td>
						<td>
							<div align="left"><%=jzname%></div>
						</td>
						<td>
							<div align="center"><%=zdlxx%></div>
						</td>
						<td>
							<div align="center"><%=lastdatetime%></div>
						</td>
						<td>
							<div align="center"><%=thisdatetime%></div>
						</td>
						<td>
							<div align="center"><%=lastdegree%></div>
						</td>
						<td>
							<div align="center"><%=thisdegree%></div>
						</td>
						<td>
							<div align="center"><%=startmonth%></div>
						</td>
						<td>
							<div align="center"><%=endmonth%></div>
						</td>
						<td>
							<div align="center"><%=beilv%></div>
						</td>
						<td>
							<div align="center"><%=floatdegree%></div>
						</td>
						<td>
							<div align="center"><%=blhdl%></div>
						</td>
						<td>
							<div align="center"><%=dianjia%></div>
						</td>
						<td>
							<div align="center"><%=floatpay%></div>
						</td>
						<td>
							<div align="right"><%=actualpay%></div>
						</td>
						<td>
							<div align="center"><%=bzyf%></div>
						</td>
						<td>
							<div align="center"><%=dfzflx%></div>
						</td>
						
						
						<td>
							<div align="center"><%=notetypeid%></div>
						</td>
						<td>
							<div align="center"><%=pjje%></div>
						</td>
						<td>
							<div align="center"><%=liuchenghao%></div>
						</td>
						<td>
							<div align="center"><%=zdsxx%></div>
						</td>
						<td>
							<div align="center"><%=dllx%></div>
						</td>
						<td>
							<div align="center"><%=ydsb%></div>
						</td>
						<td>
							<div align="center"><%=noteno%></div>
						</td>
						<%
							if (autoauditstatus != null
												&& autoauditstatus.equals("1")) {
						%>
						<td>
							<div align="center">
								通过
							</div>
						</td>
						<%
							} else {
						%>
						<td>
							<div align="center">
								未通过
							</div>
						</td>
						<%
							}
						%>
						<%
							if (manualauditstatus.equals("1")&&manualauditstatus != null) {
						%>
						<td>
							<div align="center">
								通过
							</div>
						</td>
						<%
							} else {
						%>
						<td>
							<div align="center">
								未通过
							</div>
						</td>
						<%
							}
						%>
						<%
							if (manualauditstatus != null
												&& manualauditstatus.equals("2")) {
						%>
						<td>
							<div align="center">
								通过
							</div>
						</td>
						<%
							} else if(manualauditstatus != null	&& manualauditstatus.equals("1")){
						%>
						<td>
							<div align="center">
								未通过
							</div>
						</td>
						<%
							} else if(manualauditstatus != null	&& manualauditstatus.equals("-1")){
						%>
						<td>
							<div align="center">
								不通过
							</div>
						</td>
						<%
							}
						%>
						<td>
							<div align="left"><%=dbid%></div>
						</td>
					</tr>
					<%
						}
					%>
					<%
						}
					%>
					<%
						}
					%>
					<%
						if (intnum == 0) {
							System.out.println("QQQQ" + intnum);
							for (int i = 0; i < 17; i++) {
								if (i % 2 == 0) {
									color = "#FFFFFF";
								} else {
									color = "#DDDDDD";
								}
					%>

					<tr bgcolor="<%=color%>">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						
					</tr>
					<%
						}
						} else if (!(intnum > 16)) {
							for (int i = ((intnum - 1) % 16); i < 16; i++) {
								if (i % 2 == 0)
									color = "#DDDDDD";
								else
									color = "#FFFFFF";
					%>
					<tr bgcolor="<%=color%>">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						
					</tr>
					<%
						}
						}
					%>
					   <% DecimalFormat we =new DecimalFormat("0.00");
       String s=""; 
       String sjdf="";
       s=we.format(dd);
       sjdf=we.format(ds);
         %>
        	<tr height = "23">
			 	<td  bgcolor="#DDDDDD"><div align="center" class="bttcn">合计:</div></td>
			 	<td  bgcolor="#DDDDDD"><div align="left" class="bttcn">总电量：</div></td>  
			 	<td  bgcolor="#DDDDDD"><div align="left" class="bttcn"><%=s%>度</div></td> 
			<td  bgcolor="#DDDDDD"><div align="left" class="bttcn">实际电费总和：</div></td>  
			 	<td colspan="29" bgcolor="#DDDDDD"><div align="left" class="bttcn"><%=sjdf%>元</div></td> 
			</tr>	
				</table>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<input type="hidden" name="sheng2" id="sheng2" value="" />
						<input type="hidden" name="shi2" id="shi2" value="" />
						<input type="hidden" name="xian2" id="xian2" value="" />
						<input type="hidden" name="xiaoqu2" id="xiaoqu2" value="" />
						<input type="hidden" name="sptype2" id="sptype2" value="" />
						<input type="hidden" name="currentmonth2" id="currentmonth2"
							value="" />
					</td>
				</tr>
				<tr bgcolor="F9F9F9">
					<td align="right" height="19" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<div id="daochuBtn"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 4px">
							<img src="<%=path%>/images/daochu.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导出</span>
						</div>
						<div id="butongguo"
							style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right: 10px">
							<img alt="" src="<%=request.getContextPath()%>/images/baocun.png"
								width="100%" height="100%" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">不通过</span>
						</div>
						<div id="tongguo"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
							<img src="<%=path%>/images/2chongzhi.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">通过</span>
						</div>


						<div style="width: 130px; height: 23px; cursor: pointer; float: right; position: relative; right: 50px">
							<input type="text" class="selected_font1" name="currentmonth"
								value="<%=currentmonth1%>"
								onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
							<span class="style2">&nbsp;*</span>
						</div>
						<div
							style="width: 100px; height: 23px; cursor: pointer; float: right; position: relative; right: 50px">
							<span
								style="font-size: 12px; position: absolute; left: 40px; top: 5px; color: black">会计月份：</span>
						</div>

					</td>
				</tr>
			</table>
		</form>
	</body>
	<script language="javascript">
var path = '<%=path%>';
function gopage() {
	document.form1.submit();
}
function previouspage() {
	if ((parseInt(document.form1.page.value)) < 1)
		document.form1.page.value = 1;
	else {
		document.form1.page.value = parseInt(document.form1.page.value) - 1;
		var curpage = '<%=(curpage - 1)%>';
		document.form1.action = path
				+ "/web/check/checkFeesFinance.jsp?curpage=" + curpage
				+ "<%=canshuStr%>";
		document.form1.submit();
	}
}
function nextpage() {
	document.form1.page.value = parseInt(document.form1.page.value) + 1;
	var curpage = '<%=(curpage + 1)%>';
	document.form1.action = path + "/web/check/checkFeesFinance.jsp?curpage="
			+ curpage + "<%=canshuStr%>";
	document.form1.submit();
}
function gopagebyno(pageno) {
	document.form1.page.value = pageno;

	document.form1.action = path + "/web/check/checkFeesFinance.jsp?curpage="
			+ pageno + "<%=canshuStr%>";
	document.form1.submit();
}
</script>
	<script language="javascript">
function exportad() {
	var jizhan = "<%=jizhan%>";
	var whereStr = "<%=str%>";
	var lrrq = '<%=lrrq%>';
	var str1="<%= str1%>";
	var str2="<%= str2%>";

	document.form1.action = path + "/web/check/财务电费审核信息.jsp?jizhan=" + jizhan
			+ "&whereStr=" + whereStr + "&str1=" + str1 + "&str2=" + str2 + "&lrrq=" + lrrq+"&command=daochu";
	document.form1.submit();
}
</script>
	<script type="text/javascript">
<!--
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

function changeCountry(){
	var sid = document.form1.xian.value;
	document.form1.xian2.value=document.form1.xian.value;
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

//-->
</script>
	<script type="text/javascript">
/*function passCheckNo() {
	var m = document.getElementsByName('test[]');
	var l = m.length;
	var chooseIdStr1 = "";
	var chooseIdStr2 = "";
	var bz = 0;
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			bz += 1;
			var j = m[i].value.toString().indexOf("ssss=");
			var chooseIdStr3 = m[i].value.toString().substring(0, j);
			var zflx1 = m[i].value.toString().substring(j + 5,
					m[i].value.toString().length);
			if (zflx1 == "月结"||zflx1 == "预支") {
				chooseIdStr1 = chooseIdStr1 +"'" +chooseIdStr3 + "',";
			}else if (zflx1 == "合同" || zflx1 == "插卡") {
				chooseIdStr2 = chooseIdStr2 +"'" +chooseIdStr3 + "',";
			}
		}
	}
	chooseIdStr1 = chooseIdStr1.substring(0, chooseIdStr1.length - 1);
	chooseIdStr2 = chooseIdStr2.substring(0, chooseIdStr2.length - 1);
	if (bz <= 240) {
		if (bz >= 1) {
			document.form1.action = path
					+ "/servlet/check?action=checkffno&chooseIdStr1="
					+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
			document.form1.submit();
		} else {
			alert("请选择信息！");
		}
	} else {
		alert("您选择信息条数超过240条，信息量过大，请确定后重新执行！");
	}
}*/
 function passCheckNo(){
	var shbz = '0';
        var m = document.getElementsByName('test[]');
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr1 = ""; 
       	var chooseIdStr2 = ""; 
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       	}
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
	             var j = m[i].value.toString().indexOf("ssss=");
	             var chooseIdStr3 = m[i].value.toString().substring(0,j);
	             var zflx1 = m[i].value.toString().substring(j+5,m[i].value.toString().length);
	             if(zflx1=="月结"){
		             chooseIdStr1 = chooseIdStr1 +"'" +chooseIdStr3 +"',";
	             }else if(zflx1=="预支"){
		             	count2+=1;
		             	chooseIdStr1 = chooseIdStr1 +"'"+ chooseIdStr3 +"',";
		             	chooseIdStr2 = chooseIdStr2 +"'"+ chooseIdStr3 +"',";
		         }else if(zflx1=="合同"||zflx1=="插卡"){
	             	 chooseIdStr2 = chooseIdStr2 +"'" + chooseIdStr3 +"',";
	             } 
	        }
	          if(count1<=240*n||((bz+count2)>=239&&(bz+count2)<=241)){
	         	  if(((bz+count2)/240==1)||((bz+count2)>=239&&(bz+count2)<=241)){
			        chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
			        chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
			        document.form1.action = path+"/servlet/check?shbz=checkffno1&chooseIdStr1="+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
		            chooseIdStr1 = ""; 
	       			chooseIdStr2 = "";
	       			bz=0;
	       			count2=0;
		            document.form1.submit();	          	
		          }
		       }else if(count==count1&&bzw==1){
		          chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
			      chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
			      bzw=0;
		          document.form1.action = path+"/servlet/check?action=checkffno&chooseIdStr1="+ chooseIdStr1 + "&chooseIdStr2=" + chooseIdStr2;
		          document.form1.submit(); 
		       }            
	        } 
        }else{
          alert("请选择信息！");
        }
   }
</script>
	<!--多选框选择 -->
	<script type="text/javascript">
function chooseAll() {
	var qm = document.getElementsByName('test');
	//alert(qm[0].checked);  
	var m = document.getElementsByName('test[]');
	var l = m.length;
	if (qm[0].checked == true) {
		for ( var i = 0; i < l; i++) {
			m[i].checked = true;
		}
	} else {
		for ( var i = 0; i < l; i++) {
			//m[i].checked == true ? m[i].checked = false: m[i].checked = true; 
			m[i].checked = false;
		}
	}

}
document.form1.currentmonth.value = '<%=currentmonth1%>';
document.form1.shi.value = '<%=shi%>';
document.form1.xian.value = '<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
document.form1.zdlx.value = '<%=zdlx%>';
document.form1.jzproperty.value = '<%=jzproperty%>';
document.form1.manualauditstatus1.value = '<%=manualauditstatus1%>';
document.form1.zdqyzt.value='<%=zdqyzt%>';
document.form1.dbqyzt.value='<%=dbqyzt%>';
document.form1.pjlx.value='<%=pjlx%>';
</script>
</html>
