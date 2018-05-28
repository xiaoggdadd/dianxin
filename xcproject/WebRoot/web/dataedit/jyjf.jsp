<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesFormBean,com.noki.dataedit.bean.*"%>
<%@ page
	import="java.sql.ResultSet,java.util.List,java.util.Map,java.util.HashMap,java.util.Iterator"%>
<%@ page import="java.text.*"%>
<%@page
	import="com.noki.query.basequery.javabean.StationPointQueryBean,com.noki.mobi.common.CommonBean,com.noki.mobi.common.ZtCommon"%>

<%
	String operName = request.getParameter("operName") != null ? request
			.getParameter("operName")
			: "";

	String zdqyzt = request.getParameter("zdqyzt") != null ? request
			.getParameter("zdqyzt") : "1";

	String yflx = "";

	int intnum = 0;
	String color = "";
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
	String loginId1 = request.getParameter("loginId");

	String bgsign = request.getParameter("bgsign") != null ? request
			.getParameter("bgsign") : "-1";
	String gsf = request.getParameter("gsf") != null ? request
			.getParameter("gsf") : "0";

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
	String sptype = request.getParameter("sptype") != null ? request
			.getParameter("sptype") : "0";
	String rgshzt1 = request.getParameter("manualauditstatus") != null ? request
			.getParameter("manualauditstatus")
			: "-1";
	String gdfs = request.getParameter("gdfs") != null ? request
			.getParameter("gdfs") : "-1";
	String chanquan = request.getParameter("chanquan");
	String zdname = request.getParameter("zdname") != null ? request
			.getParameter("zdname") : "0";
	String fwlx = request.getParameter("fwlx") != null ? request
			.getParameter("fwlx") : "0";
	String htmlsql = request.getParameter("htmlsql");
	String zdlx = request.getParameter("zdlx") != null ? request
			.getParameter("zdlx") : "请选择";
	String zdlx1 = request.getParameter("zdlx1") != null ? request
			.getParameter("zdlx1") : "";


	String canshuStr = "";
	String s_curpage = request.getParameter("curpage") != null ? request
			.getParameter("curpage")
			: "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	curpage = Integer.parseInt(s_curpage);
	String permissionRights = "";
	String roleId = (String) session.getAttribute("accountRole");
	HttpSession se = request.getSession();

	// ls=(List)se.getAttribute("lst");
	Map<String, zhandianbean> map = new HashMap<String, zhandianbean>();

	map = (HashMap) se.getAttribute("lstjyjf");

System.out.println("map值："+map);


	HttpSession ss = request.getSession();

	ss.setAttribute("maplst", map);

	String yfllxx = "";

	ArrayList gsflist = new ArrayList();
	ArrayList gdfsaa = new ArrayList();

	ArrayList zdlxaa = new ArrayList();
	ArrayList zdsxaa = new ArrayList();

	gsflist = new ZtCommon().getSelctOptions("FWLX");
	gdfsaa = new ZtCommon().getSelctOptions("GDFS");
	
	zdlxaa = new ZtCommon().getSelctOptions("stationtype");
	zdsxaa = new ZtCommon().getSelctOptions("ZDSX");
	
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
	width: 80px;
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
	FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
		StartColorStr = #ffffff, EndColorStr = #D7E7FA );
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
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
		<script type='text/javascript' src='/energy/dwr/util.js'>
</script>
		<script type='text/javascript'
			src='/energy/dwr/interface/dataUpdateService.js'>
</script>
		<script type='text/javascript' src='/energy/dwr/engine.js'>
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
		showdiv("请稍等..............");
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
        function insert(){
        var count = document.getElementsByTagName("table")[2].getElementsByTagName("tr").length-1 ;
        
        var liecount = document.getElementsByTagName("table")[2].getElementsByTagName("td").length ;
        var allinput = document.getElementsByTagName("table")[2].getElementsByTagName("input").length ;
        var allselect = document.getElementsByTagName("table")[2].getElementsByTagName("select").length ;
        var lie = (allinput+allselect)/count;
        //alert(liecount+" "+allinput+" "+allselect+" "+count);
        if(lie!=18){
        		alert("数据信息有误，核实后保存!"+lie);
        }
         var arr1 = Array();var i1="";//var i2="";
         var arr2 = Array();var s1="";
         var temp = 1;
         var tempcount =1;
         
         var stemp = 1;
         var stempcount = 1;
         //alert(allinput + " " +allselect);
         //alert(allinput);
         for(var i =1 ;i<=allinput;i++){
        	if(temp<=13){
        	  var atnt = i-1;
        	   i1 +=document.getElementsByTagName("table")[2].getElementsByTagName("input")[atnt].value+"の";
        	   temp++;
        	}else{
        		arr1.push(i1);
        		//alert(temp+"-----"+i1);
        		//i2+=i1;
        		temp = 1;i--;tempcount++;i1="";
        		
        	}
         }
         for(var i =1 ;i<=allselect;i++){
        	if(stemp<=5){
        	  var atnt = i-1;
        	   s1 +=document.getElementsByTagName("table")[2].getElementsByTagName("select")[atnt].value+"の";
        	   stemp++;
        	}else{
        		arr2.push(s1);
        		stemp = 1;i--;stempcount++;s1="";
        	}
         }
        
        	dataUpdateService.dataSaveJyjf(arr1,arr2,callback); 
        
        }
        function callback(data){
        	var strs= new Array(); //定义一数组
            var mess = new Array();
            strs=data.split("い"); //字符分割      
            mess = strs[0].split("の");
             //错误数据
            var  messdata = "";
             if(mess.length>0){
            	 for(var a =0;a <mess.length; a++){
            		 messdata+=" "+mess[a];
            	 }
             }
            
            // 正确个数
             var sucount = strs[1];
             if(sucount==""){
          	 succount=0;
             }
             //错误个数
               var ercount = strs[2];
               if(ercount==""){
            	   ercount = 0;
               }
               alert("成功："+sucount+"条，失败："+ercount+"条");
               
               
             if(messdata==0||messdata==""){
            	 
             }else{
            	 alert("错误数据站点ID（以下数据尚未更改）:"+messdata);
             }
        }
          
       
       
        
  
   //页面载入方法
  
      function exportFData(){
          document.form1.action=path+"/web/dataedit/inputjyjf.jsp";
                                document.form1.submit();
  }
  function qk(){
  	if (confirm("您确定清空数据？")) {
  document.form1.action=path+"/servlet/DeleteId?action=deljyjf";
 document.form1.submit();
  }
  }
   $(function(){
   $("#daoru").click(function(){
			exportFData();
		});
     $("#qk").click(function(){
			qk();
		});
   

	$("#baocun").click(function(){
		insert();
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

					<td colspan="4" width="50">
						<div style="width: 700px;">
							<img alt="" src="<%=path%>/images/btt.bmp" width="100%"
								height="100%" />
							<span
								style="font-size: 14px; font-weight: bold; position: absolute; left: 25px; top: 30px; color: black">数据更新</span>
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


				<table width="2300px" height="60%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="10" class="relativeTag">
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								乡镇
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点代码
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="5%" height="23" bgcolor="yellow">
							<div align="center" class="bttcn">
								是否启用
							</div>
						</td>
						<td width="5%" height="23" bgcolor="yellow">
							<div align="center" class="bttcn">
								用房类型
							</div>
						</td>
						<td width="5%" height="23" bgcolor="yellow">
							<div align="center" class="bttcn">
								供电方式
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								单价
							</div>
						</td>
						
						<td width="5%" height="23" bgcolor="yellow">
							<div align="center" class="bttcn">
								交流负荷
							</div>
						</td>
						<td width="5%" height="23" bgcolor="yellow">
							<div align="center" class="bttcn">
								直流负荷
							</div>
						</td>
						<td width="3%" height="23" bgcolor="yellow">
							<div align="center" class="bttcn">
								站点属性
							</div>
						</td>
						<td width="3%" height="23" bgcolor="yellow">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
									全省定标电量
							</div>
						</td>
						<td width="5%" height="23" bgcolor="yellow">
							<div align="center" class="bttcn">
							额定耗电量
							</div>
						</td>
						<td width="5%" height="23" bgcolor="red">
							<div align="center" class="bttcn">
								交流电量占比
							</div>
						</td>
						<td width="5%" height="23" bgcolor="red">
							<div align="center" class="bttcn">
								直流电量占比
							</div>
						</td>
						<td width="3%" height="23" bgcolor="red">
							<div align="center" class="bttcn">
								备注
							</div>
						</td>
					</tr>
					<%
						int countxh = 0;
						//彩钢板/砖混/其他
						String shia, xiana, xiaoqua, jzname, qyzt, gdfsa, dianfei, zdcq, zlfh, jlfh, gxxx, ed, id;
						double dka = 0, yydk = 0, ktyp = 0, ktep = 0, qsdb, rru = 0, ydgxsb = 0, dxgxsb = 0;
						String zdsx, zdlxa, qs;
						String d, y, kty, kte, rru1, yd1, dx1;
						String dj;
						double jlbl = 0;
						String jl;
						double zlbl = 0;
						String zl;

						if (map != null) {
							Iterator iter = map.entrySet().iterator();
							while (iter.hasNext()) {

								Map.Entry entry = (Map.Entry) iter.next();
								String key = (String) entry.getKey();
								zhandianbean zd = (zhandianbean) entry.getValue();

								id = zd.getId();
								shia = zd.getShi();
								xiana = zd.getXian();
								xiaoqua = zd.getXiaoqu();
								jzname = zd.getJzname();
								qyzt = zd.getQyzt();
								yflx = zd.getYflx();
								gdfsa = zd.getGdfs();
								DecimalFormat df = new DecimalFormat("0.0000");
								dianfei = zd.getDianfei();
								dianfei = df.format(Double.parseDouble(dianfei));
								zdcq = zd.getZdcq();
								DecimalFormat dff = new DecimalFormat("0.00");

								zlfh = zd.getZlfh();
								jlfh = zd.getJflx();
								zlfh = dff.format(Double.parseDouble(zlfh));
								jlfh = dff.format(Double.parseDouble(jlfh));
								//计算直流电量    直流负荷*54*24/1000/0.85     (（直流电量-本地定标）/本地定标)*100%小于20%
								double dd = 0;
								dd = (Double.parseDouble(zlfh)) * 54 * 24 / 1000 / 0.85;

								//交流电量               交流负荷*220*24/1000           (（交流电量-本地定标）/本地定标)*100%小于25%
								double ff = 0;
								ff = Double.parseDouble(jlfh) * 220 * 24 / 1000;

								DecimalFormat dfff = new DecimalFormat("0");
								dka = zd.getKdsbdk();
								yydk = zd.getYysbdk();
								d = dfff.format(dka);
								y = dfff.format(yydk);
								DecimalFormat ktk = new DecimalFormat("0.0");
								
								DecimalFormat ktkt = new DecimalFormat("0");

								zdsx = zd.getZdsx();
								zdlxa = zd.getZdlx();
								qsdb = zd.getQsdbdl();
								ed = zd.getEdhdl();
								ed = dff.format(Double.parseDouble(ed));
								zlbl = ((dd - Double.parseDouble(ed)) / (Double
										.parseDouble(ed))) * 100;

								zl = dff.format(zlbl);
								jlbl = ((ff - Double.parseDouble(ed)) / (Double
										.parseDouble(ed))) * 100;
								jl = dff.format(jlbl);

								qs = dff.format(qsdb);
								if (intnum % 2 == 0) {
									color = "#FFFFFF";

								} else {
									color = "#DDDDDD";
								}
								intnum++;
								countxh++;
					%>
					<tr bgcolor="<%=color%>">
						<td>
							<input type="text" id="xh" name="xh" value="<%=countxh%>"
								class="selected_font" />
						</td>
						<td>
							<input type="text" id="shi" name="shi" value="<%=shia%>"
								class="selected_font" />
						</td>
						<td>
							<input type="text" id="xian" name="xian" value="<%=xiana%>"
								class="selected_font" />
						</td>
						<td>
							<input type="text" id="xq" name="xq" value="<%=xiaoqua%>"
								class="selected_font" />
						</td>
						<td>
							<input type="text" id="zdid" name="zdid" value="<%=key%>"
								class="selected_font" />
						</td>


						<td>
							<input type="text" id="jzname" name="jzname" value="<%=jzname%>" />
						</td>
						<td>
							<select class="selected_font" id="qyzt" name="qyzt">
								<option value="<%=qyzt%>">
								<% if(qyzt.equals("1")){%>
								是
								<%}else{ %>
								否
								<%} %>
								</option>
								<%
									if ("1".equals(qyzt)) {
								%>
								<option value="0">
									否
								</option>
								<%
									} else {
								%>
								<option value="1">
									是
								</option>
								<%
									}
								%>
							</select>
						</td>
						<td>
							<select class="selected_font" id="yf<%=countxh%>" name="yflx">

								<%
									if (gsflist != null) {
												String code = "", name = "";
												int cscount = ((Integer) gsflist.get(0)).intValue();
												for (int i = cscount; i < gsflist.size() - 1; i += cscount) {
													code = (String) gsflist.get(i
															+ gsflist.indexOf("CODE"));
													name = (String) gsflist.get(i
															+ gsflist.indexOf("NAME"));
								%>
								<%
									if (yflx.equals(name)) {
								%>
								<option value="<%=code%>" selected="selected"><%=name%></option>

								<%
									} else {
								%>
								<option value="<%=code%>"><%=name%></option>
								<%
									}
								%>
								<%
									}
											}
								%>
							</select>
						</td>
						<td>
							<select class="selected_font" id="gdfs" name="gdfs">
								<%
									if (gdfsaa != null) {
												String code = "", name = "";
												int cscount = ((Integer) gdfsaa.get(0)).intValue();
												for (int i = cscount; i < gdfsaa.size() - 1; i += cscount) {
													code = (String) gdfsaa.get(i
															+ gdfsaa.indexOf("CODE"));
													name = (String) gdfsaa.get(i
															+ gdfsaa.indexOf("NAME"));
								%>
								<%
									if (gdfsa.equals(name)) {
								%>
								<option value="<%=code%>" selected="selected"><%=name%></option>

								<%
									} else {
								%>
								<option value="<%=code%>"><%=name%></option>
								<%
									}
								%>
								<%
									}
											}
								%>
							</select>
						</td>
						<td>
							<input type="text" id="d<%=key%>" value="<%=dianfei%>"
								name="dianfei" class="selected_font" />
						</td>
				
						<td>
							<input width="5%" type="text" id="j<%=countxh%>" name="jlfh"
								value="<%=jlfh%>" class="selected_font"
								onchange="countzf01(<%=countxh%>)" />
						</td>
						<td>
							<input type="text" id="z<%=countxh%>" name="zlfh"
								value="<%=zlfh%>" class="selected_font"
								onchange="countzf01(<%=countxh%>)" />
						</td>
						<td>
							<select class="selected_font" id="zdsx" name="zdsx">
								<option value="zdsx01" selected="selected">局用机房</option>
								
							</select>
						</td>

						<td>
							<select class="selected_font" id="zdlx" name="zdlx">
								<%
									if (zdlxaa != null) {
												String code = "", name = "";
												int cscount = ((Integer) zdlxaa.get(0)).intValue();
												for (int i = cscount; i < zdlxaa.size() - 1; i += cscount) {
													code = (String) zdlxaa.get(i
															+ zdlxaa.indexOf("CODE"));
													name = (String) zdlxaa.get(i
															+ zdlxaa.indexOf("NAME"));
								%>
								<%
									if (zdlxa.equals(name)) {
								%>
								<option value="<%=code%>" selected="selected"><%=name%></option>

								<%
									} else {
								%>
								<option value="<%=code%>"><%=name%></option>
								<%
									}
								%>
								<%
									}
											}
								%>
							</select>
						</td>
						<td>
							<input type="text" id="qs<%=key%>" value="<%=qs%>" name="qs"
								class="selected_font" readonly="readonly"/ >
						</td>
						<td>
							<input type="text" id="e<%=countxh%>" name="" value="<%=ed%>"
								class="selected_font" onchange="countzf01(<%=countxh%>)" />
						</td>
						<td>
							<input type="text" id="jl<%=countxh%>" name="" value="<%=jl%>%"
								class="selected_font" />
						</td>
						<td>
							<input type="text" id="zl<%=countxh%>" name="" value="<%=zl%>%"
								class="selected_font" />
						</td>
						<td id="sc" name="sc">
							<div align="center">
								<a href="#" onclick="deletea(<%=key%>)">删除</a>
							</div>
						</td>
					</tr>
					<%
						}
						}
					%>



				</table>
			</div>
			<div style="width: 100%; height: 8%; border: 1px">
				<table width="100%" height="20%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
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
							<%
								if (permissionRights.indexOf("PERMISSION_SEARCH") >= 0) {
							%>
							<div id="baocun"
								style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 2px">
								<img src="<%=path%>/images/daoru.png" width="100%"
									height="100%">
								<span
									style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">保存</span>
							</div>
							<%
								}
							%>


							<%
								if (permissionRights.indexOf("PERMISSION_SEARCH") >= 0) {
							%>
							<div id="daoru"
								style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 6px;">
								<img src="<%=path%>/images/daoru.png" width="100%"
									height="100%">
								<span
									style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">导入</span>
							</div>
							<%
								}
							%>

                            <%
								if (permissionRights.indexOf("PERMISSION_SEARCH") >= 0) {
							%>
							<div id="qk"
								style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 10px;">
								<img src="<%=path%>/images/daoru.png" width="100%"
									height="100%">
								<span
									style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">清空</span>
							</div>
							<%
								}
							%>

						</td>
					</tr>
					<tr>
						<td>

						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
	<script type="text/javascript">
function exportad() {
	var zdname = "<%=zdname%>";
	document.form1.action = path + "/web/query/basequery/站点信息.jsp?zdname="
			+ zdname + "&whereStr=" + whereStr + "&command=daochu";
	document.form1.submit();
}
function printad() {
	var curpage
= <%=curpage%>;
            window.open(path+"/web/query/basequery/stationpointquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr,'','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
   }
 </script>
	<script type="text/javascript">
function countzf01(num) {
	var jl = "j" + num;//交流负荷id 
	var ed = "e" + num; //额定耗电量id
	var jlbl = "jl" + num;//交流比例的id
	var zl = "z" + num;//直流负荷的id
	var zlbl = "zl" + num;
	var zlfh = document.getElementById(zl);
	var edhdl = document.getElementById(ed);
	var jlfh = document.getElementById(jl);
	var jlbl = document.getElementById(jlbl);
	var zlbl = document.getElementById(zlbl);

	Number(jlfh.value) * 220 * 24 / 1000//交流电量
	Number(edhdl.value)//额定耗电量
	jlbl.value = ((((Number(jlfh.value) * 220 * 24 / 1000) - (Number(edhdl.value))) / ((Number(edhdl.value)))) * 100).toFixed(2)+ "%";
	Number(zlfh.value) * 54 * 24 / 1000 / 0.85 //直流电量
	zlbl.value = ((((Number(zlfh.value) * 54 * 24 / 1000 / 0.85) - (Number(edhdl.value))) / (Number(edhdl.value))) * 100).toFixed(2)+ "%";
}
</script>
	<script type="text/javascript">
function deletea(num) {
	if (confirm("您确定删除此条信息？")) {
		var path = '<%=path%>';
		var c = path + "/servlet/DeleteId?zdid=" + num;
		document.form1.action = path + "/servlet/DeleteId?zdid=" + num;
		document.form1.submit();
	}
}
function Map() {
	this.elements = new Array();

	//获取MAP元素个数
	this.size = function() {
		return this.elements.length;
	};

	//判断MAP是否为空
	this.isEmpty = function() {
		return (this.elements.length < 1);
	};

	//删除MAP所有元素
	this.clear = function() {
		this.elements = new Array();
	};

	//向MAP中增加元素（key, value) 
	this.put = function(_key, _value) {
		this.elements.push( {
			key : _key,
			value : _value
		});
	};

	//删除指定KEY的元素，成功返回True，失败返回False
	this.removeByKey = function(_key) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};

	//删除指定VALUE的元素，成功返回True，失败返回False
	this.removeByValue = function(_value) {//removeByValueAndKey
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value) {
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};

	//删除指定VALUE的元素，成功返回True，失败返回False
	this.removeByValueAndKey = function(_key, _value) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value
						&& this.elements[i].key == _key) {
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};

	//获取指定KEY的元素值VALUE，失败返回NULL
	this.get = function(_key) {
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					return this.elements[i].value;
				}
			}
		} catch (e) {
			return false;
		}
		return false;
	};

	//获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
	this.element = function(_index) {
		if (_index < 0 || _index >= this.elements.length) {
			return null;
		}
		return this.elements[_index];
	};

	//判断MAP中是否含有指定KEY的元素
	this.containsKey = function(_key) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};

	//判断MAP中是否含有指定VALUE的元素
	this.containsValue = function(_value) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};

	//判断MAP中是否含有指定VALUE的元素
	this.containsObj = function(_key, _value) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value
						&& this.elements[i].key == _key) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	};

	//获取MAP中所有VALUE的数组（ARRAY）
	this.values = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].value);
		}
		return arr;
	};

	//获取MAP中所有VALUE的数组（ARRAY）
	this.valuesByKey = function(_key) {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			if (this.elements[i].key == _key) {
				arr.push(this.elements[i].value);
			}
		}
		return arr;
	};

	//获取MAP中所有KEY的数组（ARRAY）
	this.keys = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].key);
		}
		return arr;
	};

	//获取key通过value
	this.keysByValue = function(_value) {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			if (_value == this.elements[i].value) {
				arr.push(this.elements[i].key);
			}
		}
		return arr;
	};

	//获取MAP中所有KEY的数组（ARRAY）
	this.keysRemoveDuplicate = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			var flag = true;
			for ( var j = 0; j < arr.length; j++) {
				if (arr[j] == this.elements[i].key) {
					flag = false;
					break;
				}
			}
			if (flag) {
				arr.push(this.elements[i].key);
			}
		}
		return arr;
	};
}
</script>



</html>
