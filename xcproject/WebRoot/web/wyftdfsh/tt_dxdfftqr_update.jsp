<%@page import="com.noki.zwhd.manage.WyManage"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.noki.zwhd.model.WydfftBean"%>
<%@ page import="com.noki.database.DataBase" %>
<%@ page import="java.sql.ResultSet" %>

<%
	String path = request.getContextPath();
	String id = request.getParameter("id");
	session.setAttribute("id", id);
	String sql = "select * from tbl_tt_wy_dfft where id="+id+"";
	String dw="",qx="",zdmc="",zdid="",danjia="",zdbm="",dh="",jfqs="",jfzz="",gdfs="",qm="",zm="",dl="",jfje="",jfrq="",jfpjlx="",gdfmc="",kh="",dliu="",ftbl="",fsyz="",ftje="";
	String jsfs="抄表结算";
	DataBase db = new DataBase();
	ResultSet rs = null;
	db.connectDb();
	rs=db.queryAll(sql.toString());
	while(rs.next()){
		dw="聊城";
		qx="阳谷";
		zdmc=rs.getString("zdmc");
		zdbm=rs.getString("zdbm");
		dh=rs.getString("dh");
		jfqs=rs.getString("cb_jfqsrq");
		jfzz=rs.getString("cb_jfzzrq");
		qm=rs.getString("cb_qm");
		zm=rs.getString("cb_zm");
		dl=rs.getString("cb_dliang");
		jfje=rs.getString("jfje");
		jfrq=rs.getString("jfrq");
		jfpjlx=rs.getString("jfpjlx");
		gdfmc=rs.getString("gdfmc");
		kh=rs.getString("kh");
		dliu=rs.getString("cb_dliu");
		ftbl=rs.getString("ftbl");
		ftje=rs.getString("ftje");
		fsyz=rs.getString("fsyz");
		danjia=rs.getString("bcdj");
	}
	DataBase db1 = new DataBase();
	ResultSet rs1 = null;
	String sql2="select case gdfs when 'gdfs05' then '直供电' when 'gdfs06' then '转供电' end gdf,id from zhandian where jzcode="+zdbm+"";
	db1.connectDb();
	rs1=db1.queryAll(sql2.toString());
	while(rs1.next()){
		gdfs=rs1.getString("gdf");
		zdid=rs1.getString("id");
	}
// 	DataBase db2 = new DataBase();
// 	ResultSet rs2 = null;
// 	String sql3 = "select danjia from dianbiao where zdid="+zdid+"";
// 	db2.connectDb();
// 	rs2=db2.queryAll(sql3.toString());
// 	while(rs2.next()){
// 		danjia=rs2.getString("danjia");
// 	}
%>
<html>
<head>
<title></title>
<style>
.btn {
	BORDER-RIGHT: #7b9ebd 1px solid;
	PADDING-RIGHT: 2px;
	BORDER-TOP: #7b9ebd 1px solid;
	PADDING-LEFT: 2px;
	FONT-SIZE: 12px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#cecfde );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#B3D997 );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#CAE4B6 );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#C3DAF5 );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#D7E7FA );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#C3DAF5 );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#C3DAF5 );
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#FFFFFF, EndColorStr=#9DBCEA );
	BORDER-LEFT: #002D96 1px solid;
	CURSOR: hand;
	COLOR: black;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #002D96 1px solid
}

.style1 {
	color: #FF0000;
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
	FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
		StartColorStr=#ffffff, EndColorStr=#D7E7FA );
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
	width: 130px;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
	border-left-width: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
}

.form_label1 {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height: 23px
}

.selected_font13 {
	width: 130px;
	text-align: right;
	font-family: 宋体;
	font-size: 12px;
	line-height: 100%;
}

.bttcn {
	color: BLACK;
	font-weight: bold;
}
</style>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
	
</script>
<script type="text/javascript">
var path = '<%=path%>';
$(function(){
	$("#saveBtn").click(function(){
		save();
	});
	$("#cancelBtn").click(function(){
	    fanhui();
	});
	$("#resetBtn").click(function(){
		$.each($("form input[type='text']"),function(){
		  $(this).val("");
	          });
		});
	});

function save(){
if(
	checkNotnull(document.form1.t_jfqs,"缴费起始日期")&&
	checkNotnull(document.form1.t_jfzz,"缴费终止日期")&&
	checkNotnull(document.form1.t_qm,"起码")&&
	checkNotnull(document.form1.t_zm,"止码")&&
	checkNotnull(document.form1.t_dliang,"电量")&&
	checkNotnull(document.form1.t_danjia,"单价")&&
	checkNotnull(document.form1.t_dliu,"电流")
	)
	{
	var qs = <%="document.form1.t_jfqs.value"%>;
	var zz = <%="document.form1.t_jfzz.value"%>;
	var qm =  parseInt(document.form1.t_qm.value);
	var zm =  parseInt(document.form1.t_zm.value);
	var danjia = parseInt(document.form1.t_danjia.value);
	var dliu = document.form1.t_dliu.value;
	if(qs>zz){
		alert("缴费起始日期大于等于缴费终止日期");
	}else if(qm>zm|qm<=0|zm<0){
		alert("起码必须小于止码且都要大于0");
	}else if(danjia<0){
		alert("单价要大于0");
	}else if(dliu<0){
		alert("电流要大于0");
	}
	else{
	if(confirm("您将要修改电信电费信息！确认信息正确！")){
	document.form1.action=path+"/servlet/DxdfftqrAdd?action=dxupdate";
    document.form1.submit();
    }
    }
    }
}
function jfrqchange(){
	document.form1.t_jfrq.value=document.form1.t_jfzz.value;
}
function jisdl(){
	var qima = parseInt(document.form1.t_qm.value);
	var zhima = parseInt(document.form1.t_zm.value);
	document.form1.t_dliang.value=zhima-qima;
	if(document.form1.t_dliang.value!=null){
		danjiaa();
	}
}
function danjiaa(){

	var dl = parseInt(document.form1.t_dliang.value);
	
	var jfje = parseInt(document.form1.t_jfje.value);
	
	var gdfs = (document.form1.t_gdfs.value);
	//alert(gdfs);
	if(gdfs=="转供电"){
	document.form1.t_danjia.value='<%=danjia%>';	
	}else{
	document.form1.t_danjia.value=(jfje/dl).toFixed(4);	
	}
}
function fanhui(){        
	document.form1.action=path+"/web/wyftdfsh/tt_dxdfftqr_search.jsp";
    document.form1.submit();
}
</script>
</head>
<body class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="form_label">
			<tr>
				<td>
					<div style="width:700px;height:50px">
						<img alt="" src="<%=path%>/images/btt.bmp" width="100%"
							height="100%" /> <span
							style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电信电费单修改</span>
					</div></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="form_label">

			<tr>
				<td colspan="3">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="form_label">
						<tr>
							<td></td>
							<td>
								<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="form_label">
									<tr height="23px">
										<td>&nbsp;</td>
									</tr>
									<tr bgcolor="F9F9F9" class="selected_font1">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />基本信息</td>
									</tr>
									<tr class="selected_font1">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">单位：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_sfgs" readonly="readonly" value="<%=dw %>" class="selected_font"  />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">区县：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_dsfgs" readonly="readonly" value="<%=qx %>" class="selected_font"  />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点名称：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_zdmc" readonly="readonly" value="<%=zdmc %>" class="selected_font"  />
										</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">站点编码：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_zdbm" readonly="readonly" value="<%=zdbm %>" class="selected_font"  />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">供电方式：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_gdfs" readonly="readonly" value="<%=gdfs %>" class="selected_font"  />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">结算方式：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_jsfs" readonly="readonly" value="<%=jsfs %>" class="selected_font"  />
										</td>
									</tr>
									<tr bgcolor="F9F9F9" class="selected_font">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />铁塔缴费信息</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr class="selected_font">
										<td bgcolor="#DDDDDD" width="15%"><div align="left">单号：</div>
										</td>
										<td width="25%"><input type="text" readonly="readonly" name="t_dh" value="<%=dh %>" class="selected_font" /></td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费起始日期：</div>
										</td>
										<td width="25%"><input type="text" name="t_jfqs" value="<%=jfqs %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="selected_font" />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费终止日期：</div>
										</td>
										<td width="25%">
											<input type="text" name="t_jfzz" value="<%=jfzz %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" onChange="jfrqchange()" class="selected_font" />
										</td>
									</tr>
									<tr>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">起码：</div>
										<td width="25%">
											<input type="text" name="t_qm" value="<%=qm %>" onChange="jisdl()"  class="selected_font" />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">止码：</div>
										<td width="25%">
											<input type="text" name="t_zm" value="<%=zm %>" onChange="jisdl()" class="selected_font" />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">电量：</div>
										<td width="25%">
											<input type="text" name="t_dliang" value="<%=dl %>" onChange="danjiaa()" class="selected_font" />
										</td>
									</tr>
									<tr>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">电费单价(元/度)：</div>
										<td width="25%">
											<input type="text" name="t_danjia"  value="<%=danjia %>" onChange="danjiaa()" class="selected_font" />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费金额：</div>
										<td width="25%">
											<input type="text" name="t_jfje" readonly="readonly" value="<%=jfje %>" class="selected_font" />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费日期：</div>
										<td width="25%">
											<input type="text" name="t_jfrq"  value="<%=jfrq %>" class="selected_font" />
										</td>
									</tr>
									<tr>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">缴费票据类型：</div>
										<td width="25%">
											<input type="text" name="t_jfpjlx" readonly="readonly" value="<%=jfpjlx %>" class="selected_font" />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">供电方/业主名称：</div>
										<td width="25%">
											<input type="text" name="t_gdfmc" readonly="readonly" value="<%=gdfmc %>" class="selected_font" />
										</td>
										<td bgcolor="#DDDDDD" width="15%"><div align="left">客户：</div>
										<td width="25%">
											<input type="text" name="t_kh" readonly="readonly" value="<%=kh %>" class="selected_font" />
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr bgcolor="F9F9F9">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />运营商分摊信息</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电流（A）：</div></td>
										<td width="25%">
											<input type="text"  name="t_dliu" value="<%=dliu %>" class="selected_font" />
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">分摊比例(%)：</div></td>
										<td width="25%">
											<input type="text"  name="t_ftbl" readonly="readonly" value="<%=ftbl %>" class="selected_font"/>
										</td>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">税负因子(%)：</div></td>
										<td width="25%">
											<select  name="t_fsyz" value='<%=fsyz%>'>
											<option value="0">0</option>
											<option value="19.64">19.64</option>
											</select>
										</td>
									</tr>
									<tr>
										<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">分摊金额：</div></td>
										<td width="25%">
											<input type="text"  name="t_ftje" readonly="readonly" value="<%=ftje %>" class="selected_font"/>
										</td>
									</tr>
									<tr>
										<td colspan="6">
											<div id="cancelBtn"
												style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
												<img src="<%=path%>/images/quxiao.png" width="100%"
													height="100%"> <span
													style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
											</div>
											<div id="resetBtn"
												style="position:relative;width:60px;height:23px;cursor: pointer;right:15px;float:right;">
												<img alt="" src="<%=path%>/images/2chongzhi.png"
													width="100%" height="100%" /> <span
													style="font-size:12px;position: absolute;left:28px;top:5px;color:white">重置</span>
											</div>
											<div id="saveBtn"
												style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:26px">
												<img src="<%=path%>/images/baocun.png" width="100%"
													height="100%"> <span
													style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>
											</div></td>
									</tr>
								</table></td>
						</tr>
					</table>
					</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td>&nbsp;</td>
				<td></td>
			</tr>
		</table>
		<input type="hidden" name="zdid" value="" />
	</form>
</body>
</html>


