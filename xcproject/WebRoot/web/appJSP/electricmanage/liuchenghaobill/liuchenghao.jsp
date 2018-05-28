<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean,com.noki.zdqxkz.dao.ShiQuery,com.noki.zdqxkz.javabean.Zdqxkz"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String roleId = (String)session.getAttribute("accountRole");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
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
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
 	String xian =request.getParameter("xian")!=null?request.getParameter("xian"):"0";
    String liuch = request.getParameter("liuch")!=null?request.getParameter("liuch"):"";
 	String bztime = request.getParameter("bztime")!=null?request.getParameter("bztime"):"";
 	String shengzt = request.getParameter("shengzt")!=null?request.getParameter("shengzt"):"0";
 	
    String permissionRights="";
    String color;//颜色
	int intnum = request.getAttribute("num") != null ? (Integer) request.getAttribute("num"): 0;//条数 ,查出数据的条数，用于颜色设置
	if (intnum % 2 == 0) {
		color = "#DDDDDD";
	} else {
		color = "#FFFFFF";
	}
%>

<html>
<head>
<title>

</title>
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
.bttcn{color:BLACK;font-weight:bold;}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px

		}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
</style>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js"></script>

<script language="javascript">
var path = '<%=path%>';
function ShowHideSearchRegion(trObject,SelfObject){
		if(trObject.style.display == "none"){
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"
		}else{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
}
    
    function queryDegree(){
    	if (document.getElementById("shi").value == ""
			|| document.getElementById("shi").value == "0"
			|| document.getElementById("shi").value == null) {
			alert("城市不能为空");
	 	}else{
              document.form1.action=path+"/servlet/LiuchServlet?command=chaxun";
              document.form1.submit();
              showdiv("请稍等..............");
        }
    }
    
    $(function(){
		$("#chaxun").click(function(){
			queryDegree();
		});
	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0804");
%>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		<tr>
			<td colspan="4">
			<div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">省申请改标审核</span>	
			 </div>
		    </td>
			</tr>		
			<tr><td height="30" colspan="4">
	   				<div id="parent" style="display:inline">
	                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
	                </div>
		        </td>
		    </tr>
		    <tr><td height="8%" width="1200">
		    <table>
		    	<tr class="form_label">
		    	<td>城市：</td><td><select name="shi" class="selected_font" onchange="changeCity()">
         		<option value="0">请选择</option>
         		<%
		         	ArrayList shilist = new ArrayList();
		         	shilist = commBean.getAgcode(sheng,account.getAccountId());
		         	if(shilist!=null){
		         		String agcode="",agname="";
		         		int scount = ((Integer)shilist.get(0)).intValue();
		         		for(int i=scount;i<shilist.size()-1;i+=scount)
	                    {
	                    	agcode=(String)shilist.get(i+shilist.indexOf("AGCODE"));
	                    	agname=(String)shilist.get(i+shilist.indexOf("AGNAME"));
	                    %>
	                    <option value="<%=agcode%>"><%=agname%></option>
	                    <%}
		         	}
		         %>
         		</select><span class="style1">&nbsp;*</span></td>
         		<td>区县：</td>
				<td>
					<select name="xian" class="selected_font" >
						<option value="0">
							请选择
						</option>
						<%
							ArrayList xianlist = new ArrayList();
							xianlist = commBean.getAgcode(shi, account.getAccountId());
							if (xianlist != null) {
								String agcode = "", agname = "";
								int scount = ((Integer) xianlist.get(0)).intValue();
								int size = xianlist.size() - 1;
								for (int i = scount; i < size; i += scount) {
									agcode = (String) xianlist.get(i + xianlist.indexOf("AGCODE"));
									agname = (String) xianlist.get(i + xianlist.indexOf("AGNAME"));
						%>
						<option value="<%=agcode%>"><%=agname%></option>
						<%}}%>
					</select>
				</td>
				<td> 流程号：</td>
                <td><input type="text" name="liuch" value="<%if(null!=request.getParameter("liuch")) out.print(request.getParameter("liuch")); %>" class="selected_font"/></td>
				
				<td>报账月份：</td>
                        <td> <input type="text" class="selected_font" name="bztime" value="<%if (null != request.getParameter("bztime"))out.print(request.getParameter("bztime"));%>"
		                         	readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
                        </td>
         		<td>财务审核状态：</td>
 				<td><select name="shengzt" class="selected_font" >
	         		<option value="0">未审核</option>
	         		<option value="-1">财务不通过</option>
	         		</select>
	         	</td>
		    			
 			<td> <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%> <%}%>
		        <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;left:55px;float:right;top:0px">
				       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
				       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
				</div></td>
		    </tr>
		   </table>
		  </td>
		</tr>
	</table>
	<table>
		<tr>
			<td height="23" colspan="4">
				<div id="parent" style="display:inline">
              		<div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
        		</div>
        	</td>
        	<td><input id="str" name="str" type="hidden" value="${whereStr}" /></td>
        </tr>
	</table>
	
	<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
		<table width="100%" height="80%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
			<tr height = "23" class="relativeTag">
			 	<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
				<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">流程号</div></td>
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">城市</div></td> 
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>                 
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">打印人</div></td>
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">打印时间</div></td>
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">总金额</div></td>
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">增值税税额(元)</div></td>
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">价税合计(元)</div></td>
			</tr>
		 <c:forEach items="${list}" var="list" varStatus="status">
		  <tr  bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}" >
		   	<td>
       			<div align="center" >${status.count}</div>
       		</td>
       		<td>
             	<div align="center"><input type="checkbox" name="test[]" value="" /></div>
         	</td>
         	<td>
       			<div align="center" ><a href="javascript:lookDetails('${list.liuchenghao}')">${list.liuchenghao}</a></div>
       		</td>
            <td>
       			<div align="center" >${list.city}</div>
       		</td>
       		<td>
       			<div align="center" >${list.xian}</div>
       		</td>
       		<td>
       			<div align="center" >${list.dyr}</div>
       		</td>
       		<td>
       			<div align="center" >${list.drtime}</div>
       		</td>
       		<td>
       			<div align="center" >${list.zje}</div>
       		</td>
       		<td>
       			<div align="center" >${list.zse}</div>
       		</td>
       		<td>
       			<div align="center" >${list.jshj}</div>
       		</td>
       </tr>
      </c:forEach>   
        <% if (intnum==0){
         for(int i=0;i<15;i++){
          	if(i%2==0){
			    color="#FFFFFF" ;
          	}else{
			    color="#DDDDDD";
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
    	</tr>
      <% }}else if(!(intnum > 15)){
    	  for(int i=((intnum-1)%15);i<15;i++){
            if(i%2==0)
			    color="#FFFFFF" ;
            else
			    color="#DDDDDD";
        %>
        <%}}%>	
	</table>
	</div>
	</form>
</body>

<script type="text/javascript">
	var XMLHttpReq1;
	function createXMLHttpRequest1() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq1 = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq1 = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq1 = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
</script>

  <!--多选框选择 -->
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
 
 //改变城市
function changeCity() {
	var sid = document.form1.shi.value;
	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=shi&pid=" + sid, "shi");
	}
}
</script>
<script type="text/javascript">
function lookDetails(liuch){
	var whr = document.form1.str.value; 
    window.open(path+"/servlet/LiuchServlet?command=xiangxi&liuch="+liuch+"&str="+whr);
    document.form1.submit();
}
</script>
<script type="text/javascript">
   	document.form1.shi.value='<%=shi%>';
   	document.form1.xian.value='<%=xian%>';
	document.form1.shengzt.value='<%=shengzt%>';
	document.form1.liuch.value='<%=liuch%>'; 
 	document.form1.bztime.value='<%=bztime%>';
</script>
</html>

