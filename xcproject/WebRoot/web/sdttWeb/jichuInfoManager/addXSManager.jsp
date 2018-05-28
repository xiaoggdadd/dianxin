<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.Double" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.jizhan.JiZhanBean" %>
<%@ page import="com.noki.zwhd.model.DwBean"%>
<%@ page import="com.noki.zwhd.manage.SystemManage" %>
<%@ page import="java.util.List"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.*" %>
<%@ page import="com.noki.database.DataBase" %>
<%@ page import="java.sql.ResultSet" %>
<%
	String path = request.getContextPath();
    Account account=(Account)session.getAttribute("account");
    String accountname = account.getAccountName();
    String sheng = (String) session.getAttribute("accountSheng");
	String loginId=account.getAccountId();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	String xstype = request.getParameter("xstype");
	String id = request.getParameter("id");
	System.out.println("id>>>>>>>>>>>"+id);
	String  addTime_s="", maxzhi="", minzhi="",housetype="0",housetypename="";
	DataBase db = new DataBase();
	StringBuffer sql = new StringBuffer();
	ResultSet rs = null;
	sql.append("select h.ADDTIME,h.MAXZHI,h.MINZHI,h.HOUSETYPE from housexs h where h.id="+id);
	db.connectDb();
 	
 	rs=db.queryAll(sql.toString());
 	while(rs.next()){
		addTime_s = rs.getString("ADDTIME")==null?"":rs.getString("ADDTIME");
		maxzhi = rs.getString("MAXZHI")==null?"":rs.getString("MAXZHI");	
		minzhi = rs.getString("MINZHI")==null?"":rs.getString("MINZHI");	
		housetype = rs.getString("HOUSETYPE")==null?"":rs.getString("HOUSETYPE");	
	
		if(housetype.equals("20")){
			housetypename="板房";
		}
		if(housetype.equals("30")){
			housetypename="非板房";
		}
 	}
 	
 	StringBuffer sql2 = new StringBuffer();
	ResultSet rs2 = null;
	sql2.append("select ht.ID,ht.MIN_T,ht.MAX_T from housexs_t ht where ht.HOUSEID="+id);
 	
 	rs2=db.queryAll(sql2.toString());
 	String  htid="", MIN_T="", MAX_T="";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>

<title>
<%if(xstype.equals("add")){ %>
添加房屋系数
<%}else if(xstype.equals("xiangxi")){ %>
查看房屋系数明细
<%}else if(xstype.equals("update")){%>
修改房屋系数
<%} %>
</title>
<script type="text/javascript">
	var path = '<%=path%>';

function saveAdvance(type){
	if(type=='xiangxi'){
		window.close();
	}else{
		if (
	  			checkNotSelected(document.form1.houseType,"房屋类型")&&
	  			checkNotnull(document.form1.addTime,"时间(年月)")&&
	  			checkNotnull(document.form1.minzhi,"月份系数最小值")&&
	  			checkNotnull(document.form1.maxzhi,"月份系数最小值")
	  		){
			var houseType = document.form1.houseType.value;
			var addTime = document.form1.addTime.value;
			var minzhi = document.form1.minzhi.value;
			var maxzhi = document.form1.maxzhi.value;
			if(type=='add'){
				document.form1.action=path+"/servlet/dianbiao?action=addXSManager";
				document.form1.submit();
				showdiv("请稍等..............");
			}else{
				document.form1.action=path+"/servlet/dianbiao?action=updateXSManager";
				document.form1.submit();
				showdiv("请稍等..............");
			}
			
		}
		
	}

	
		
}
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
	
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				
				<div class="tit01">添加房屋系数</div>
				<div class="content01_1">
					<table width="100px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
					<tr>
				         <td align="right" width="100px">房屋类型:</td>
							<td width="100px">
								<select id="houseType" name="houseType" style="box-sizing: border-box; width: 130px;">
				         			<option value="0" 
				         			<%if(housetype.equals("0")){ %>
				         			selected="selected"
										<%}else{ %> 
										<%} %>
										>全部</option>
				         			<option value="20"
				         			<%if(housetype.equals("20")){ %>
				         			selected="selected"
										<%}else{ %> 
										<%} %>
				         			>板房</option>
				         			<option value="30"
				         			<%if(housetype.equals("30")){ %> 
				         			selected="selected"
				         			<%}else{ %> 
										<%} %>
				         			>非板房</option>
				         		</select>
							</td>
							<td align="right" width="100px">时间(年月):</td>
							<td width="100px">
								<input type="text" name="addTime" value="<%=addTime_s %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" style="box-sizing: border-box; width: 130px;"/>
							</td>
							<td align="right" width="100px">月份系数最小值:</td>
							<td width="100px">
								<input type="text" name="minzhi" value="<%=minzhi %>" style="box-sizing: border-box; width: 130px;"/>
								
							</td>
							<td align="right" width="100px">月份系数最大值:</td>
							<td width="100px">
								<input type="text" name="maxzhi" value="<%=maxzhi %>" style="box-sizing: border-box; width: 130px;"/>
							</td>
				     </tr>
				     <tr>
				     	<td align="left" colspan="8">区间内容  &nbsp;&nbsp;
				     	<%if(xstype.equals("add")){ %>
						<a href="javascript:void(0)" onclick="addTr()">添加</a></td>
						<%}else if(xstype.equals("xiangxi")){ %>
						
						<%}else if(xstype.equals("update")){%>
						<a href="javascript:void(0)" onclick="addTr()">添加</a></td>
						<%} %>
				     	
				     </tr>  				    
				     <tr>
				      <td align="center" colspan="8">
						<table id="costTab" width="96%" border="0" cellspacing="1" cellpadding="0" bgcolor="#a0c6e5">
				     		<%if(xstype.equals("add")){ %>
							<tr>
				     			<td>开始值(不含有):</td>
				     			<td><input type="text" name="beginzhi"></td>
				     			<td>结束值(含有):</td>
				     			<td><input type="text" name="endzhi"></td>
				     			<td></td>
				     		</tr>
							<%}else if(xstype.equals("xiangxi")){
								while(rs2.next()){
									htid = rs2.getString("ID")==null?"":rs2.getString("ID");
									MIN_T = rs2.getString("MIN_T")==null?"":rs2.getString("MIN_T");	
									MAX_T = rs2.getString("MAX_T")==null?"":rs2.getString("MAX_T");	
							 
								%>
							<tr>
				     			<td>开始值(不含有):</td>
				     			<td><input type="text" name="beginzhi" readonly="readonly" value="<%=MIN_T %>"></td>
				     			<td>结束值(含有):</td>
				     			<td><input type="text" name="endzhi" readonly="readonly" value="<%=MAX_T %>"</td>
				     			<td></td>
				     		</tr>
							<%	}
							}else if(xstype.equals("update")){
								while(rs2.next()){
									htid = rs2.getString("ID")==null?"":rs2.getString("ID");
									MIN_T = rs2.getString("MIN_T")==null?"":rs2.getString("MIN_T");	
									MAX_T = rs2.getString("MAX_T")==null?"":rs2.getString("MAX_T");	
							 	
							%>
							<tr>
				     			<td>开始值(不含有):</td>
				     			<td><input type="text" name="beginzhi" value="<%=MIN_T %>"></td>
				     			<td>结束值(含有):</td>
				     			<td><input type="text" name="endzhi"  value="<%=MAX_T %>"</td>
				     			<td></td>
				     		</tr>
							<%	}
							} %>
				     		
				     	</table>
				     </td>
				     </tr> 
				  								
						<tr>
							<td align="right" colspan="8" height="60px">
<!-- 								<input name="button2" type="submit" class="btn_c1" id="button2" value="临时保存" />&nbsp;  -->
							
							<%if(xstype.equals("add")){ %>
								<input onclick="saveAdvance('add')" type="button" class="btn_c1" id="button2" value="添加" />&nbsp; 
							<%}else if(xstype.equals("xiangxi")){ %>
								<input onclick="saveAdvance('xiangxi')" type="button" class="btn_c1" id="button2" value="关闭" />&nbsp; 
							<%}else if(xstype.equals("update")){%>
								<input onclick="saveAdvance('update')" type="button" class="btn_c1" id="button2" value="修改" />&nbsp; 
							<%} %>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td>
		</tr>
		</table>
		<input type="hidden" name="id" value="<%=id %>"/>
			<script type="text/javascript">
var path = '<%=path%>';
	
function addTr(){
    var tr = $("#costTab tr").eq(0).clone();
    tr.attr('id', new Date().getTime());
    var aHTML = '<a href="#" onclick="delTr(this)">删除</a>';
    tr.find("td:last").append(aHTML);
    tr.appendTo("#costTab");
}  
function delTr(obj){
	$(obj).closest('tr').remove();
} 
</script>
	</form>	

</body>

</html>

