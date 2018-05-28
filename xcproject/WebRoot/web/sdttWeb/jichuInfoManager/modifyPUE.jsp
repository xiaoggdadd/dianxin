<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%@page import="com.ptac.app.station.bean.*,com.ptac.app.station.dao.*"%>
<%
	String path = request.getContextPath();
	String sheng = (String)session.getAttribute("accountSheng");
	String xian = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	Account account=(Account)session.getAttribute("account");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script src="<%=path%>/javascript/PopupCalendar.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
	<script src="<%=path%>/javascript/tx.js"></script>
		<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	

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
	
	
    $(function(){
		
		$("#resetBtn").click(function(){
			$.each($("form input[type='text']"),function(){
				$(this).val("");
			})
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
</head>
<%
String id = request.getParameter("id");
String station_no="", station_name="", electricity01="",updatetime="";
if(id !=null && !id.isEmpty()){
	ArrayList eleclist = commBean.getElectricOne(id);	
	int count = ((Integer)eleclist.get(0)).intValue();
	if(eleclist.size()>count+1){
		//id = eleclist.get(count+ eleclist.indexOf("ID"));
		station_no = (String)eleclist.get(count + eleclist.indexOf("STATION_NO"));
		station_name = (String)eleclist.get(count + eleclist.indexOf("STATION_NAME"));
		updatetime = (String)eleclist.get(count + eleclist.indexOf("UPDATETIME"));
		electricity01 = (String)eleclist.get(count + eleclist.indexOf("ELECTRICITY"));
	}
	
}
%>
<body>
<form action="" name="form1" method="post">
<input type="hidden" name="id" value="<%=id %>"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">电量修改</div>
				<div class="content01_1">
					<table border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr bgcolor="F9F9F9">
										<td height="19" colspan="4">
											<img src="../../images/v.gif" width="15" height="15" />
										         电量信息
										</td>
						</tr>
							<%--<tr>
							<td align="right" width="100px">地市</td>
							<td width="100px">
								<select name="shi" style="width: 130px" onchange="changeCity()">
									<option value="0">请选择</option>
									<%
				         			ArrayList shilist = new ArrayList();
				         			shilist = commBean.getAgcode(sheng, "0", loginName);
				         			if (shilist != null) {
				         				String agcode = "", agname = "";
				         				int scount = ((Integer) shilist.get(0)).intValue();
				         				for (int i = scount; i < shilist.size() - 1; i += scount) {
					         			 	agcode = (String) shilist.get(i + shilist.indexOf("AGCODE"));
					         				agname = (String) shilist.get(i + shilist.indexOf("AGNAME"));
				         			%>
				                    	<option value="<%=agcode%>"><%=agname%></option>
				                    <%
				                    	}
				                    }
				                    %>
								</select>
								<span >&nbsp;*</span>
							</td>
							<td align="right" width="100px">区县</td>
							<td width="100px">
								<select name="xian" style="width: 130px" onchange="changeCountry()">
									<option value="0">请选择</option>
									<%
					         			ArrayList xianlist = new ArrayList();
										xianlist = commBean.getAgcode(station.getShi(), station.getXian(), loginName);
					         			if (xianlist != null) {
					         				String agcode = "", agname = "";
					         				int scount = ((Integer) xianlist.get(0)).intValue();
					         				for (int i = scount; i < xianlist.size() - 1; i += scount) {
						         			 	agcode = (String) xianlist.get(i + xianlist.indexOf("AGCODE"));
						         				agname = (String) xianlist.get(i + xianlist.indexOf("AGNAME"));
				         			%>
				                    	<option value="<%=agcode%>"><%=agname%></option>
				                    <%
				                    	}
				                    }
				                    %>
								</select>
								<span class="style1">&nbsp;*</span>
							</td>
							<td align="right" width="100px">乡镇</td>
							<td width="100px">
								<select name="xiaoqu" style="width: 130px">
									<option value="0">请选择</option>
									<%
					         			ArrayList xiaoqulist = new ArrayList();
										xiaoqulist = commBean.getAgcode(station.getXian(), station.getXiaoqu(), loginName);
					         			if (xiaoqulist != null) {
					         				String agcode = "", agname = "";
					         				int scount = ((Integer) xiaoqulist.get(0)).intValue();
					         				for (int i = scount; i < xiaoqulist.size() - 1; i += scount) {
						         			 	agcode = (String) xiaoqulist.get(i + xiaoqulist.indexOf("AGCODE"));
						         				agname = (String) xiaoqulist.get(i + xiaoqulist.indexOf("AGNAME"));
				         			%>
				                    	<option value="<%=agcode%>"><%=agname%></option>
				                    <%
				                    	}
				                    }
				                    %>
								</select>
								<span class="style1">&nbsp;*</span>
							</td>
							<td align="right" width="100px">实体名称</td>
							<td>
							   <input type="text" name="jzName" value="<%=station.getJzName() %>" maxlength="20" style="width: 130px;"/>
							   <span class="style1">&nbsp;*</span>
							</td>
						</tr>--%>
						<tr>
						<td align="right" width="100px">局站名称</td>
							<td width="100px">
								<input type="text" name="station_name" value="<%=station_name %>" disabled="disabled" style="box-sizing: border-box; width: 130px"/>
								<span>&nbsp;*</span>
							</td>
						    <td align="right" width="100px">电量</td>
							<td width="100px">
							<%if(electricity01 !=null && !electricity01.isEmpty()){%>
								<input type="text" name=electricity value="<%=electricity01 %>" disabled="disabled"  style="box-sizing: border-box; width: 130px"/>
						    <% }else{%>
								<input type="text" name=electricity value="" maxlength="20" style="box-sizing: border-box; width: 130px"/>
								<span>&nbsp;*</span>
							<% }%>
							</td>
							
						</tr>
						<tr>
						    <td align="right" colspan="8" height="60px">
								<input name="baocun" type="button" class="btn_c1" onclick="saveElectric()" id="baocun" value="保存" />&nbsp; 
								<input name="resetBtn" type="button" class="btn_c1" id="resetBtn" value="重置" />&nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td></tr></table>
		</form>
</body>
<script language="javascript">
function saveElectric() {
	debugger;
		if(!(checkNotSelected(document.form1.shi, "电量") )){
			return;
		}
		if(confirm("您将要设置电量的信息！确认信息正确！")){
             document.form1.action=path+"/servlet/dianbiao?action=savePue";
        	 document.form1.submit()
        }
	}


	/**临时****下拉框选中*/
</script>
</html>
