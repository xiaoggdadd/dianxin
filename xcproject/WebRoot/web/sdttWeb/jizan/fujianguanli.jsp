<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.*"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<!-- 电表文件管理需要导入的dao和bean -->
<%@ page import="com.noki.chaobiaorenyuan.Dao.uploadDao"%>	
<%@ page import="com.noki.chaobiaorenyuan.bean.WenJian"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>附件管理</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
	<%	
		String path = request.getContextPath();
		String id = request.getParameter("id") != null ? request.getParameter("id"):"0"; //电表ID
		System.out.println(id);
		String name = request.getParameter("name") != null ? request.getParameter("name"):"0"; //电表名称
		name = new String(name.getBytes("iso8859-1"), "UTF-8"); //编码
		System.out.println(name);
		String liuchengId = request.getParameter("liuchengId") != null ? request.getParameter("liuchengId"):"0";	//流程编码
		liuchengId = new String(liuchengId.getBytes("iso8859-1"), "UTF-8"); //编码
		System.out.println(liuchengId);
		//获取查询值
		String scrname = request.getParameter("scrname") != null ? request.getParameter("scrname"):"0";		//上传人
		scrname = new String(scrname.getBytes("iso8859-1"), "UTF-8"); //编码
		System.out.println(scrname);
		String wenjianming = request.getParameter("wenjianming") != null ? request.getParameter("wenjianming"):"0";	//文件名称
		wenjianming = new String(wenjianming.getBytes("iso8859-1"), "UTF-8"); //编码
		System.out.println(wenjianming);
		String liuchengxuanze = (String)request.getParameter("liuchengxuanze") != null ? request.getParameter("liuchengxuanze"):"0"; //电表名称
		liuchengxuanze = new String(liuchengxuanze.getBytes("iso8859-1"), "UTF-8"); //编码
		System.out.println(liuchengxuanze);
 	%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
<!-- 文件上传js -->
<script type="text/javascript" src="<%=path%>/javascript/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/ajaxfileupload.js"></script>
<!-- end -->
<script type="text/javascript">
var path = '<%=path%>';
var XMLHttpReq;
//XMLHttpReq = createXMLHttpRequest();
function createXMLHttpRequest() {
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		XMLHttpReq = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
}
///////////////////////////////////////////////////////////
function uploadFile(){ //文件上传方法
	var file = $("#file_input").val();
	var jiaoyanjieguo  = jiaoyan(file);	//校验文件格式并返回结果
	//var str = chuanzhi();
	//电表ID
	var dbID = '<%=id%>';
	//电表名称
	var dbName = '<%=name%>';
	//流程ID
	var liuchengId = '<%=liuchengId%>';
	//alert(str);
	if(jiaoyanjieguo != false){
		if(file != ""){
    		$.ajaxFileUpload
			(
				{
					type: "POST",//请求方式
					url:'<%=path%>/servlet/upload',
					secureuri:false,
					fileElementId:'file_input',
					data: {dbID:dbID,dbName:dbName,liuchengId:liuchengId},
					dataType: 'text/html',
					success: function (data, status,e)
					{
						alert(data);
						location.reload();
					},
					error: function (data, status, e)
					{
						alert(e);
					}
				}
			)
		}else{
				alert("请选择文件！");
		}
	 }else{
 			alert("请选择合适的文件类型！");
 	}
}
//文件上传校验
function jiaoyan(filepath) {
		//为了避免转义反斜杠出问题，这里将对其进行转换
		var re = /(\\+)/g;
		var filename = filepath.replace(re, "#");
		//对路径字符串进行剪切截取
		var one = filename.split("#");
		//获取数组中最后一个，即文件名
		var two = one[one.length - 1];
		//再对文件名进行截取，以取得后缀名
		var three = two.split(".");
		//获取截取的最后一个字符串，即为后缀名
		var last = three[three.length - 1];
		//添加需要判断的后缀名类型
		var tp = "jpg,gif,bmp,JPG,GIF,BMP,xls,xlsx,docx,txt,png";	//可添加或删除验证上传的格式
		//返回符合条件的后缀名在字符串中的位置
		var rs = tp.indexOf(last);
		//如果返回的结果大于或等于0，说明包含允许上传的文件类型
		if(rs >= 0) {
			return true;
		} else {
		return false;
	}
} 
function deleteFile(wenjianid,wenjianname){	//删除文件
	if(confirm("确定删除该文件")){
		$.ajax({
   	 		//直接"post"或者"get",不需要"doPost","doGet"，该函数到后端接收缓冲区会自动匹配
        	type : "post",      
       		//servlet文件名为Calculator，需要提前在web.xml里面注册
        	url :"<%=path%>/servlet/deleteFile",
        	dataType : "text",  //数据类型，可以为json，xml等等，自己百度
        	data :
        	{
            	"wenjianid":wenjianid,				//文件ID
             	"wenjianname":wenjianname,    		//文件名称
        	},
        	success : function(Result)
        	{
               //Result为后端post函数传递来的数据，这里写结果操作代码
                alert("删除成功!");
				location.reload();
        	},
        	error : function(xhr, status, errMsg)
        	{
             	alert("删除失败!");
       		}
    	});
    	
	}
}
function downloadFile(wenjianid,wenjianname){
	 window.location.href = path+"/servlet/downloads?wenjianid="+wenjianid+"&wenjianname="+wenjianname;
}
function query(){
	var id = '<%=id%>';
	var name = '<%=name%>';
	var scrname = document.getElementById("scrname").value;
	var wenjianming = document.getElementById("wenjianming").value;
	var liuchengId = document.getElementById("liuchengId").value;
	document.form1.action=path+"/web/sdttWeb/jizan/fujianguanli.jsp?id="+id+"&name="+name+"&scrname="+scrname+"&wenjianming"+wenjianming+"&liuchengId="+liuchengId;
}
</script>
</head>
<body>
<jsp:useBean id="flowBean" scope="page" class="com.noki.mobi.flow.javabean.FlowBean"></jsp:useBean>
	<form action="" name="form1" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr valign="top">
			<td width="12"><img src="../images/space.gif" width="12" height="17" />
				</td>
				<td>
					<div class="content01">
						<div class="content01_1">
							<table width="800px" border="0" cellpadding="1" cellspacing="0"class="tb_select">
								<tr>
									<td align="right" width="60px">上传人</td>
									<td align="left" width="60px">
										<input type="text" id = "scrname" name="scrname" value="" style="width: 130px;"/>
									</td>
									<td align="right" width="60px">文件名称：</td>
									<td align="left" width="60px">
										<input type="text" id = "wenjianming" name="wenjianming" value="" style="width: 130px;"/>
									</td>
									<td align="right" width="60px">文件对应流程：</td>
									<td align="left" width="60px">
									<!-- 隐藏域用于控制流程选择 -->
									<input type="hidden" name="liuchengxuanze" value="<%=liuchengxuanze%>"> 
										<select name="liuchengId" id="liuchengId">
											<option value="0">请选择: </option>
											<%
												ArrayList flowlist = new ArrayList();
												flowlist = flowBean.getAllFlowsfujian(liuchengxuanze);
												//flowlist = flowBean.getAllFlow();
												String flowName = "", flowId = "0";
												if (flowlist != null) {
													int countColum = ((Integer) flowlist.get(0)).intValue();
													for (int i = countColum; i < flowlist.size() - 1; i += countColum) {
														flowId = (String) flowlist.get(i
																+ flowlist.indexOf("FLOWID"));
														flowName = (String) flowlist.get(i
																+ flowlist.indexOf("FLOWNAME"));
											%>
											<option value="<%=flowId%>"><%=flowName%></option>

											<%
												}
												}
											%>

										</select>
									</td>
									<td>
										<input onclick="query()" type="submit" class="btn_c1" value="查询" />
									</td>
								</tr>
							</table>

							<div class="tbtitle01">
								<b>对应电表：<%=name%></b>
							</div>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="tb_list1">
								<tr align="right">
									<td colspan="8" align="right">
										<input type="file" name="file_input" id="file_input" value="" />
										<input type="button" class="btn_c1" value="点击上传" onclick="uploadFile();"/>
									</td>
								</tr>
								<tr align="center">
									<th width="10">序号</th>
									<th width="90">上传人</th>
									<th width="90">文件名称</th>
									<th width="90">文件对应电表</th>
									<th width="90">文件对应流程</th>
									<th width="90">文件上传时间</th> 
									<th colspan="2">操作</th>
								</tr>
								<%
									//遍历文档
									uploadDao uploaddao = new uploadDao();				//文件DAO层
									ArrayList<WenJian> wjlist = new ArrayList<WenJian>();//接受文件目录的集合
									//wjlist = uploaddao.Selectwj(id,name,liuchengId,scrname,wenjianming,liuchengming);//根据电表筛选文件
									wjlist = uploaddao.Selectwj(id,name,scrname,wenjianming,liuchengId);//根据电表筛选文件
									//遍历文件集合
									int xuhao = 1;
									for(int wj = 0 ; wj < wjlist.size() ; wj++){
									    String wjid =  wjlist.get(wj).getID();			//文件ID
									    String wjName = wjlist.get(wj).getYUAN_NAME();	//文件原名
									    String wjbcName = wjlist.get(wj).getXIAN_NAME();//文件保存名		
									    String wjscrid = wjlist.get(wj).getSCR_ID();	//文件上传人ID
									    String wjscr = wjlist.get(wj).getSCR_NAME();	//文件上传人名称
									    String wjlcId = wjlist.get(wj).getLIUCHENG_ID(); //文件对应流程ID
									    String wjlcName	= wjlist.get(wj).getLIUCHENG_NAME();//文件对用流程名称
									    String wjscTime = wjlist.get(wj).getSC_TIME();		//文件上传时间
									   	String accountName = "";//文件上传人姓名
									   	String wjDBName = wjlist.get(wj).getDIANBIAO_NAME();//文件对应电表名称
									   	accountName = uploaddao.account_name(wjscrid);
								%>
								<tr align="center">
									<td width="10"><%=xuhao++%></td>
									<td align="left"><%=accountName%></td>
									<td align="left"><%=wjName%></td>
									<td align="left"><%=wjDBName%></td>
									<td align="left"><%=wjlcName%></td>
									<td align="left"><%=wjscTime%></td>
									<!-- 下载和删除文件需要该文件的唯一ID和保存名称 -->
									<td width="15"><a href="#" onclick="downloadFile('<%=wjid%>','<%=wjbcName%>')">下载</a></td>
									<td width="15"><a href="#" onclick="deleteFile('<%=wjid%>','<%=wjbcName%>')">删除</a></td>
								</tr>
								<%
									}
								%>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>


