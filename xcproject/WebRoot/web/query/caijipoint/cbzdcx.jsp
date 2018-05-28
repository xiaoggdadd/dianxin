<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesFormBean,com.noki.mobi.common.AccountJzqa,com.noki.mobi.common.zdbzbeanB"%>
<%@ page import="java.sql.ResultSet,java.util.List"%>
<%@ page import="java.text.*,java.util.Calendar"%>
<%@page import="com.noki.query.basequery.javabean.StationPointQueryBean,com.noki.mobi.common.CommonBean"%>

<%
	java.text.SimpleDateFormat fordate = new java.text.SimpleDateFormat(
			"yyyy-MM");
	java.util.Date currentTime = new java.util.Date();
	Calendar calendar = Calendar.getInstance();
	//日历对象
	calendar.setTime(currentTime);
	//设置当前日期
	calendar.add(Calendar.MONTH, -1);
	//月份减一
	String crym = fordate.format(calendar.getTime());//输出格式化的日期
	String title = request.getParameter("title") != null ? request
			.getParameter("title") : "";
	String operName = request.getParameter("operName") != null ? request
			.getParameter("operName")
			: "";
	int intnum = 0;
	String color = "";
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
	String loginId1 = request.getParameter("loginId");
	String sheng = (String) session.getAttribute("accountSheng");
	String agcode1 = "";

	String shi = request.getParameter("shi") != null ? request
			.getParameter("shi") : "0";
	String xian = request.getParameter("xian") != null ? request
			.getParameter("xian") : "0";
	String bl = request.getParameter("bl") != null ? request
			.getParameter("bl") : "10";
	String bzmonth = request.getParameter("bzmonth") != null ? request
			.getParameter("bzmonth") : crym;
	String property = request.getParameter("property") != null ? request
			.getParameter("property")
			: "zdsx02";
	String pld = request.getParameter("pld") != null ? request.getParameter("pld") : "";
    String dlcb = request.getParameter("dlcb") != null ? request.getParameter("dlcb") : "";
    String dfcb = request.getParameter("dfcb") != null ? request.getParameter("dfcb") : "";
	String canshuStr = "";
	String permissionRights = "";
	String roleId = (String) session.getAttribute("accountRole");
%>

<html>
	<head>
		<title></title>
		<style>
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.style1 {
	color: red;
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
	line-height: 100%;
}

.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}

#id1 {
	BORDER-RIGHT: #2C59AA 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #2C59AA 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid :   DXImageTransform.Microsoft.Gradient (   GradientType
		=   0, StartColorStr =   #ffffff, EndColorStr =   #D7E7FA );
	BORDER-LEFT: #2C59AA 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #2C59AA 1px solid
}

#div1 {
	width: 130px;
	height: 18px;
	border: 1px solid #0000FF;
	position: relative;
}

ul {
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	top: 30px;
	left: -1px;
	background-color: white;
	border: 1px solid #0000FF;
	width: 130px;
	display: none;
}

#div1 p {
	float: left;
	font-size: 12px;
	width: 110px;
	cursor: pointer;
}

#box,#box2,#box3,#box4 {
	padding: 0px;
	border: 1px;
}

.bttcn {
	color: BLACK;
	font-weight: bold;
}
</style>
		<style type="text/css" media=print>
.noprint {
	display: none
}

a {
	text-decoration: none
}
</style>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>

		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
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
<script language="javascript">
	var path = '<%=path%>';
	function queryDegree() {
		var bl = document.form1.bl.value;
		if (isNaN(bl) == false && bl != null && bl != "" && bl != " ") {
			if(checkNotnull(document.form1.bzmonth,"开始时间")){
				document.form1.action = path + "/web/query/caijipoint/cbzdcx.jsp?chaxun=1";
				document.form1.submit();
			}
		} else {
			alert("超标比例必须是数字！");
		}
	}
	function passCheck(){

			var zdsx = document.form1.property.value;
			var bzmonth = document.form1.bzmonth.value;
			
	        var m = document.getElementsByName('test[]');   
	        var l = m.length; 
	        var chooseIdStr = ""; 
	        var bz=0; 
	        var count = 0;
	        var n = 0;
	        var count1 = 0;
	        var count2 = 0;
	        var bzw = 1;
			var bzw1 = "";
	         
       for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
	   }
	   if (count != 0) {
		if(confirm("您确定提交此次信息？")){
			if (count % 240 == 0) {
				n = count / 240 - 1;
			} else {
				n = (count - (count % 240)) / 240;
			}
			
			for ( var i = 0; i < l; i++) {
				if (m[i].checked == true) {
					bz += 1;
					count1 += 1;
					chooseIdStr = chooseIdStr +"'" +m[i].value +"',"; 
				}
				bzw1 = document.form1.bzw1.value;
				if (bzw1 == "1") {
					if (count1 <= 240 * n
							|| ((bz + count2) >= 239 && (bz + count2) <= 241)) {
						if (((bz + count2) / 240 == 1)
								|| ((bz + count2) >= 239 && (bz + count2) <= 241)) {
							chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
							passCheck1(chooseIdStr,bzmonth,zdsx);
							chooseIdStr = "";
							bz = 0;
							count2 = 0;       	
						}
					} else if (count == count1 && bzw == 1) {
						chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
						bzw = 0;
						document.form1.action = path
								+"/UploadB?action=cbzdcx&chooseIdStr="
								+ chooseIdStr+"&bzmonth="+bzmonth+"&zdsx="+zdsx;
						document.form1.submit();
						show("请稍等......");
					}
				} else if (bzw1 == "0") {
					document.form1.action = path + "/web/msg.jsp";
					document.form1.submit();
					return;
				}
			}
	         
			/* for(var i = 0; i < l; i++){
	          if(m[i].checked == true){
	             bz+=1;
	             chooseIdStr = chooseIdStr +"'" +m[i].value +"',"; 
	          }             
	        } 
	        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);*/
	        
	        //if(bz>=1){
	        //  document.form1.action=path+"/UploadB?action=cbzdcx&chooseIdStr="+chooseIdStr+"&bzmonth="+bzmonth+"&zdsx="+zdsx;
	        //  document.form1.submit();
	        }
	        closediv();
        }else{
          alert("请选择信息！");
          closediv();
        }

    }
		function daochuBtn(){
            var bl="<%=bl%>";
            var bzmonth ="<%=bzmonth%>";
            var property='<%=property%>';
            var shi='<%=shi%>';
            var xian ='<%=xian%>';
            var dlcb ='<%=dlcb%>';
            var dfcb ='<%=dfcb%>';
            
        	document.form1.action=path+"/web/jzcbnewfunction/查询超标站点.jsp?bl="+bl+"&bzmonth="+bzmonth+"&property="+property+"&shi="+shi+"&xian="+xian+"&dlcb="+dlcb+"&dfcb="+dfcb+"&command=daochu";
        	
            document.form1.submit();
   }
	
$(function() {
	$("#query").click(function() {
		queryDegree();
		showdiv("请稍等..........");		
	});
	$("#save").click(function(){
		passCheck();
		showdiv("请稍等..........");
	});
	$("#daochuBtn").click(function(){
		daochuBtn();
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
		permissionRights = commBean.getPermissionRight(roleId, "0501");
	%>
	<body class="body">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
  				<tr>
					<td colspan="4" width="50" >
						<div style="width:700px;height:50px">
						
						<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">超标站点查询</span>												</div>					  </td>
	    	    </tr>	
				<tr>
					<td height="20" colspan="4">
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
					<td width="1200px">
						<table>
							<tr class="form_label">
								<td>
									超标比例：
								</td>
								<td>
									<input class="selected_font" type="text" name="bl" value="10" />
									%
									<font color="red">*</font>
								</td>

								<td>
									报账月份：
								</td>
								<td>
									<input type="text" name="bzmonth" class="selected_font"
										value="<%=crym%>"
										onFocus="getDatenyString(this,oCalendarChsny)" />
									<font color="red">*</font>
								</td>
								<td>
									站点属性：
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
									<div id="query"
										style="position: relative; width: 59px; height: 23px; cursor: pointer; float: left; left: 20px;">
										<img alt=""
											src="<%=request.getContextPath()%>/images/chaxun.png"
											width="100%" height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<div style="width: 90%;">
							<p id="box3" style="display: none">
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
										<td>偏离度大于</td>
										<td><input class="selected_font" type="text" name="pld" value="600" />度</td>
										<%--
										<td>电量超标绝对值</td>
										<td><input class="selected_font" type="text" name="dlcb" value="" />
										（度/天）
										</td>
										<td>电费超标绝对值</td>
										<td><input class="selected_font" type="text" name="dfcb" value="" />
										（元/天）
										</td>
									--%>
									</tr>
								</table>
							</p>
						</div>
					</td>
				</tr>
			</table>
			<table height="23">
				<tr>
					<td height="5" colspan="4">
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
			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="1500px" height="60%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="10" class="relativeTag">
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<input type="checkbox" name="test" onclick="chooseAll()"/>
									全选
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点ID
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								本地定标(度)
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电表用电量(度)
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								标准值(合并周期*生产标)
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								超标比例
							</div>
						</td>						
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								直流负荷
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								交流负荷
							</div>
						</td>		
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点属性
							</div>
						</td>
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								城市
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								乡镇
							</div>
						</td>
							<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电量超标值(度/天)
							</div>
						</td>
							<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电费超标值(元/天)
							</div>
						</td>
					</tr>
					<%
					String whereStr = "";
		            String str = "";
		            String str1 = "";
		            String str2 = "";
		            String str3 = "";
		           // String str4 = "";
		            StringBuffer str4=new StringBuffer();
		            String str5 = "";
		           /* if(!"".equals(bl)&& !"".equals(dlcb)&&!"".equals(dfcb)){
		             	str4.append(" AND ((ABS(ZDDL.BL*100) >='"+bl+"') AND( ABS(ZDDL.DLCB)>=ABS('"+dlcb+"') OR ( ABS(ZDDL.DLCB * 0.9))>=ABS('"+dfcb+"')))");
		             }
		             if(!"".equals(bl)&& !"".equals(dlcb)&&"".equals(dfcb)){
		            	 str4.append(" AND ((ABS(ZDDL.BL*100) >='"+bl+"') AND ABS(ZDDL.DLCB)>=ABS('"+dlcb+"'))");
		             }if(!"".equals(bl)&& "".equals(dlcb)&&!"".equals(dfcb)){
		            	 str4.append(" AND ((ABS(ZDDL.BL*100) >='"+bl+"') AND ABS(ZDDL.DLCB * 0.9)>=ABS('"+dfcb+"'))");
		             }if(!"".equals(bl)&& "".equals(dlcb)&&"".equals(dfcb)){
		            	 str4.append(" AND ((ABS(ZDDL.BL*100) >='"+bl+"'))");
		             }
		             **/
		            if(!"".equals(bl)){
		            	 str4.append(" AND ((ZDDL.BL*100) >='"+bl+"')");
		             }
		            if(!"".equals(pld)){
		            	 str4.append(" AND ((ZDDL.DL-ZDDL.BZZ) >'"+pld+"')");
		             }
		            System.out.println("str4:"+str4);
		            if(null !=bzmonth  && !bzmonth.equals("") && !bzmonth.equals("0")){
		              	str1 = " AND to_char(EF.ACCOUNTMONTH,'yyyy-mm') ='"+bzmonth+"'";
		              	str2 = " AND CB.CBSJ = '"+bzmonth+"'";
		              //	str5 = " AND SYMONTH = '"+bzmonth+"'";
		              	str5 = " AND to_char(EF.ACCOUNTMONTH,'yyyy-mm') ='"+bzmonth+"'";
		              
		            } 
		            
		            if(null != property && !property.equals("") && !property.equals("0")){		              	
		              	whereStr=whereStr+" AND ZD.PROPERTY ='"+property+"'";
		              	str5=str5+" AND ZD.PROPERTY ='"+property+"'";
		              	str3 = property;
		            }	
		                    
		            if(null !=shi  && !shi.equals("") && !shi.equals("0")){
		              	whereStr=whereStr+" AND ZD.SHI ='"+shi+"'";
		              	str5=str5+" AND ZD.SHI ='"+shi+"'";
		            }
		            
		            if(null != xian && !xian.equals("") && !xian.equals("0")){		              	
		              	whereStr=whereStr+" AND ZD.XIAN ='"+xian+"'";
		              	str5=str5+" AND ZD.XIAN ='"+xian+"'";
		            }	
		           // if(null !=dlcb&& !"".equals(dlcb)&& !" ".equals(dlcb)&& dlcb!=" " && dlcb!=""&& null !=dfcb&& !"".equals(dfcb)&& !" ".equals(dfcb)&& dfcb!=" " && dfcb!=""){
		           // 	str4.append( "AND (ZDDL.DLCB>=ABS('"+dlcb+"') OR (ZDDL.DLCB * 0.9)>=ABS('"+dfcb+"'))");
		          //  }else{
		         //   	if(null !=dlcb&& !"".equals(dlcb)&& !" ".equals(dlcb)&& dlcb!=" " && dlcb!=""){
		         //   		str4.append( "AND ZDDL.DLCB>=ABS('"+dlcb+"')");
		         //   	}
		        //    	if(null !=dfcb&& !"".equals(dfcb)&& !" ".equals(dfcb)&& dfcb!=" " && dfcb!=""){
		            //		str4.append("AND (ZDDL.DLCB * 0.9)>=ABS('"+dfcb+"')");
		          //  	}
		            
		         //   }
		            System.out.println("444444:"+str4);
                   	AccountJzqa  bean = new AccountJzqa();
                   	List<zdbzbeanB> fylist = null;
					if ("1".equals(request.getParameter("chaxun"))
								|| "chaxun".equals(request.getParameter("command"))) {
						fylist=bean.getPageDatacbcx(whereStr,str,str1,str2,str3,str4,str5,loginId);
							String zdid = "";
							String jzname = "";
							String zdsx1 = "";
							String shiname = "";
							String xianname = "";
							String xiaoquname = "";
							String zlfh = "";
							String jlfh = "";
							String edhdl = "";
							String qsdbdl = "";
							String rjdl = "";
							String bl1 = "";
							String bz = "",dlcb1 = "",dfcb1 = "";
							String bzz="";

							String rgshzt = "", rgshsj = "", rgshry = "";
							int countxh = 1;
							if (fylist != null) {
								for (zdbzbeanB bean1 : fylist) {
									zdid = bean1.getZdid();
									zdid = zdid != null ? zdid : "";

									jzname = bean1.getZdname();
									jzname = jzname != null ? jzname : "";
									
									zdsx1 = bean1.getProperty();
									zdsx1 = zdsx1 != null ? zdsx1 : "";
									
									shiname = bean1.getShi1();
									shiname = shiname != null ? shiname : "";
									
									xianname = bean1.getXian();
									xianname = xianname != null ? xianname : "";
									
									xiaoquname = bean1.getXiaoqu();
									xiaoquname = xiaoquname != null ? xiaoquname : "";									
									bzz=bean1.getBzz();
									
									
									DecimalFormat formmat = new DecimalFormat("0.00");
									if (null==bzz || bzz == "" || bzz == " " || "".equals(bzz)||"null".equals(bzz)|| "o".equals(bzz)|| " ".equals(bzz)) {
										bzz = "0.00";
											}
									bzz = formmat.format(Double.parseDouble(bzz));

									zlfh = bean1.getZlfh();
									if (zlfh == null || zlfh == "" || zlfh == " " || "".equals(zlfh)
									||"null".equals(zlfh)|| "o".equals(zlfh)|| " ".equals(zlfh)) {
										zlfh = "0.00";
									}
									zlfh = formmat.format(Double.parseDouble(zlfh));

									jlfh = bean1.getJlfh();
									if (jlfh == null || jlfh == "" || jlfh == " " || "".equals(jlfh)
									||"null".equals(jlfh)|| "o".equals(jlfh)|| " ".equals(jlfh)) {
										jlfh = "0.00";
									}									
									jlfh = formmat.format(Double.parseDouble(jlfh));
									
									edhdl = bean1.getEdhdl();
									if (edhdl == null || edhdl == "" || edhdl == " " || "".equals(edhdl)
									||"null".equals(edhdl)|| "o".equals(edhdl)|| " ".equals(edhdl)) {
										edhdl = "0.00";
									}
									edhdl = formmat.format(Double.parseDouble(edhdl));

									qsdbdl = bean1.getQsdbdl();
									if (qsdbdl == null || qsdbdl == "" || qsdbdl == " " || "".equals(qsdbdl)
									||"null".equals(qsdbdl)|| "o".equals(qsdbdl)|| " ".equals(qsdbdl)) {
										qsdbdl = "0.00";
									}									
									qsdbdl = formmat.format(Double.parseDouble(qsdbdl));
									
									rjdl = bean1.getRjdl();
									if (rjdl == null || rjdl == "" || rjdl == " " || "".equals(rjdl)
									||"null".equals(rjdl)|| "o".equals(rjdl)|| " ".equals(rjdl)) {
										rjdl = "0.00";
									}
									rjdl = formmat.format(Double.parseDouble(rjdl));
									
									bl1 = bean1.getBl();
									if (bl1 == null || bl1 == "" || bl1 == " " || "".equals(bl1)
									||"null".equals(bl1)|| "o".equals(bl1)|| " ".equals(bl1)) {
										bl1 = "0.00";
									}
									dlcb1 = bean1.getDlcb();
									if (dlcb1 == null || dlcb1 == "" || dlcb1 == " " || "".equals(dlcb1)
											||"null".equals(dlcb1)|| "o".equals(dlcb1)|| " ".equals(dlcb1)) {
										dlcb1 = "0.00";
									}
									dfcb1 = bean1.getDfcb();
									if (dfcb1 == null || dfcb1 == "" || dfcb1 == " " || "".equals(dfcb1)
											||"null".equals(dfcb1)|| "o".equals(dfcb1)|| " ".equals(dfcb1)) {
										dfcb1 = "0.00";
									}
									bl1 = formmat.format(Double.parseDouble(bl1)*100);
									dfcb1 = formmat.format(Double.parseDouble(dfcb1));
									dlcb1 = formmat.format(Double.parseDouble(dlcb1));
									
									bz = bean1.getBz();
									bz = bz != null ? bz : "0";
																																				
									if (intnum % 2 == 0) {
										color = "#FFFFFF";

									} else {
										color = "#DDDDDD";
									}
									intnum++;
					%>
					<% if(bz=="1"||"1".equals(bz)){%>
					<tr bgcolor="<%=color%>">
						<td>
							<div align="center"><%=countxh++%></div>
						</td>
						<td>
		              		<div align="center">
		              			<input type="checkbox" name="test[]" value="<%=zdid%>"/>
		              		</div>
		            	</td>
						<td>
							<div align="left"><%=zdid%></div>
						</td>
						<td>
							<div align="left"><%=jzname%></div>
						</td>
						<td>
							<div align="right"><%=edhdl%></div>
						</td>
						<td>
							<div align="right"><%=rjdl%></div>
						</td>
						<td>
							<div align="right"><%=bzz%></div>
						</td>
						<td>
							<div align="right"><%=bl1%>%</div>
						</td>						
						<td>
							<div align="right"><%=zlfh%></div>
						</td>
						<td>
							<div align="right"><%=jlfh%></div>
						</td>						
						<td>
							<div align="center"><%=zdsx1%></div>
						</td>
						<td>
							<div align="center"><%=shiname%></div>
						</td>
						<td>
							<div align="center"><%=xianname%></div>
						</td>
						<td>
							<div align="center"><%=xiaoquname%></div>
						</td>
						<td>
							<div align="center"><%=dlcb1%></div>
						</td>
						<td>
							<div align="center"><%=dfcb1%></div>
						</td>
					</tr>
					<%}else{ %>
					<tr bgcolor="<%=color%>" title="以生成超标站点整改工单，请确认">
						<td>
							<div align="center"><%=countxh++%></div>
						</td>
						<td>
		              		<div align="center">
		              		</div>
		            	</td>
						<td>
							<div align="left"><%=zdid%></div>
						</td>
						<td>
							<div align="left"><%=jzname%></div>
						</td>
						<td>
							<div align="right"><%=edhdl%></div>
						</td>
						<td>
							<div align="right"><%=rjdl%></div>
						</td>
						<td>
							<div align="right"><%=bzz%></div>
						</td>
						<td>
							<div align="right"><%=bl1%>%</div>
						</td>
						<td>
							<div align="right"><%=zlfh%></div>
						</td>
						<td>
							<div align="right"><%=jlfh%></div>
						</td>
						<td>
							<div align="center"><%=zdsx1%></div>
						</td>
						<td>
							<div align="center"><%=shiname%></div>
						</td>
						<td>
							<div align="center"><%=xianname%></div>
						</td>
						<td>
							<div align="center"><%=xiaoquname%></div>
						</td>
						<td>
							<div align="center"><%=dlcb1%></div>
						</td>
						<td>
							<div align="center"><%=dfcb1%></div>
						</td>
					</tr>					
					<%
						}}
					%>
					<%
						}
					%>
					<%
						}
					%>
					<%
						if (intnum == 0) {
							for (int i = 0; i < 17; i++) {
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
					</tr>
					<%
						}
						}
					%>

				</table>
			</div>
			<table align="right">
			   	 <tr>
	                <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
	                     <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
	                </div></td>
			    </tr>

				<tr>
					<td align="right"> 
					 <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:left;position:relative;left:280px">
								 <img src="<%=path %>/images/daochu.png" width="100%" height="100%">
							 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
					  </div></td>     

			    <%if("1".equals(roleId)||roleId=="1"){ %>
				<td align="right"> 
		          <div id="save"
						style="width: 60px; height: 23px; cursor: pointer; float: left; position: relative; left: 40px">
						<img src="<%=path%>/images/2chongzhi.png" width="100%"
							height="100%">
						<span
							style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">保存</span>
					</div>
		     	</td>
		     	<%} %>
		     	</tr>
		     	
			</table>
			<tr>
			   		<td align="left">
			   			<font size="2" color="red">说明： 超标比例:((电表用电量—标准值)/标准值)*100%<br>
			   					       电表用电量(度)：(本次读数-上次读数)*倍率<br>
								       标准值:合并周期*生产标<br>
								       电量超标值(度/天):(电表用电量/合并周期—生产标)<br>    
								       电费超标值(元/天):电量超标值*0.9<br>
						</font>                                                                  
                   	</td>
  				</tr>
			<input type="hidden" name="sheng2" id="sheng2" value="" />
			<input type="hidden" name="shi2" id="shi2" value="" />
			<input type="hidden" name="xian2" id="xian2" value="" />
			<input type="hidden" name="bzw1" id="bzw1" value="1" />
		</form>
	</body>
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
	function sendRequest(url,para) {

		createXMLHttpRequest();
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="sheng"){
			XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
	}
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		//alert(XMLHttpReq.status);
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
            	var res = XMLHttpReq.responseText;        
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
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
</script>
<script type="text/javascript">
	var XMLHttpReq1;
	function createXMLHttpRequest1() {
		if (window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq1 = new XMLHttpRequest();
		} else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq1 = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq1 = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
	}
	function sendRequest1(url, para) {
	
		createXMLHttpRequest1();
		XMLHttpReq1.open("POST", url, true);
		if (para == "checkcbzdcx") {
			XMLHttpReq1.onreadystatechange = processResponse_checkcbzdcx;
		} else {
			XMLHttpReq1.onreadystatechange = processResponse;//指定响应函数
		}
		XMLHttpReq1.send(null);
	}
	// 处理返回信息函数
	function processResponse() {
		if (XMLHttpReq1.readyState == 4) { // 判断对象状态
			if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
				var res = XMLHttpReq1.responseText;
				window.alert(res);
			} else { //页面不正常
				window.alert("您所请求的页面有异常。");
			}
		}
	}
	function passCheck1(chooseIdStr,bzmonth,zdsx) {
		sendRequest1(path +"/UploadB?action=cbzdcx1&chooseIdStr="
				+ chooseIdStr+"&bzmonth="+bzmonth+"&zdsx="+zdsx, "checkcbzdcx");
	}
	function processResponse_checkcbzdcx() {
		if (XMLHttpReq1.readyState == 4) { // 判断对象状态
			if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
				document.form1.bzw1.value = XMLHttpReq1.responseText;
			} else { //页面不正常
				window.alert("您所请求的页面有异常。");
			}
		}
	}
</script>

<script type="text/javascript">
 function chooseAll() { 
       var qm = document.getElementsByName('test');
       var m = document.getElementsByName('test[]');   
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
    
	document.form1.shi.value = '<%=shi%>';
	document.form1.xian.value = '<%=xian%>';
	document.form1.bl.value = '<%=bl%>';
	document.form1.bzmonth.value = '<%=bzmonth%>';
	document.form1.property.value = '<%=property%>';
	document.form1.dlcb.value = '<%=dlcb%>';
	document.form1.dfcb.value = '<%=dfcb%>';
</script>
</html>
