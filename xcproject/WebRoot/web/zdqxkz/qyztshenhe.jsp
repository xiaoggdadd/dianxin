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
	String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
	String zdid=request.getParameter("zdid")!=null?request.getParameter("zdid"):"";
	String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"请选择";
    String zdlx1=request.getParameter("zdlx1")!=null?request.getParameter("zdlx1"):"";//站点类型,隐藏域
    String zdsx1 = request.getParameter("zdsx1")!=null?request.getParameter("zdsx1"):"";//站点属性,隐藏域
	String jzproperty = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"请选择";
 	String shengzt = request.getParameter("shengzt")!=null?request.getParameter("shengzt"):"0";
	String sqnr = request.getParameter("sqnr")!=null?request.getParameter("sqnr"):"-1";//申请内容
 	
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

#div1{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div1 p{ float:left;font-size:12px; width:110px; cursor:pointer;}

#div2{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div2 p{float:left;font-size:12px; width:110px; cursor:pointer;}

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
              document.form1.action=path+"/servlet/QyztServlet?command=chaxun";
              document.form1.submit();
              showdiv("请稍等..............");
    }
    
    $(function(){
		$("#chaxun").click(function(){
			queryDegree();
		});
		$("#butongguo").click(function(){
			butongguo();
		});
		$("#tongguo").click(function(){
			tongguo();
		});
		$("#chehui").click(function(){
			chehui();
		});
		$("#queding").click(function(){
			queding();		
		});
		$("#quxiao").click(function(){
			quxiao();		
		});
		$("#queding2").click(function(){
			queding2();		
		});
		$("#quxiao2").click(function(){
			quxiao2();		
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
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">省申请启用状态审核</span>	
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
		    	<td>城市：</td><td><select name="shi" class="selected_font" >
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
         		</select></td>
         		<td>申请内容：</td>
 				<td><select name="sqnr" class="selected_font" >
	         		<option value="-1">请选择</option>
	         		<option value="0">由启用改关闭</option>
	         		<option value="1">由关闭改启用</option>
	         		</select>
	         	</td>	
         		<td>审核状态：</td>
 				<td><select name="shengzt" class="selected_font" >
	         		<option value="0">未审核</option>
	         		<option value="1">审核通过</option>
	         		</select>
	         	</td>	
		     	<td>站点名称：</td><td><input type="text" class="selected_font" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" /></td>
		    			
 			<td> <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%> <%}%>
		        <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;left:100px;float:right;top:0px">
				       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
				       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
				</div></td>
		    </tr>
		    <tr class="form_label">
				<td>站点ID：</td><td><input type="text" class="selected_font" name="zdid" value="<%if(null!=request.getParameter("zdid")) out.print(request.getParameter("zdid")); %>" /></td>
		   		<td>站点属性：</td>
				<td>
					<div id="div2">
			      		<p><input type="text" class="selected_font" readonly= "readonly" name="jzproperty" value="请选择"/></p>
			      			<ul>
							<%
								ArrayList zdsx = new ArrayList();
								zdsx = ztcommon.getSelctOptions("ZDSX");
								if (zdsx != null) {
									String code = "", name = "";
									int cscount = ((Integer) zdsx.get(0)).intValue();
									for (int i = cscount; i < zdsx.size() - 1; i += cscount) {
										code = (String) zdsx.get(i + zdsx.indexOf("CODE"));
										name = (String) zdsx.get(i + zdsx.indexOf("NAME"));
							%>
								<li><input type="checkbox" name="CheckboxGroup2" value="<%=code%>,<%=name%>" id="CheckboxGroup2" /><%=name%></li>
							<%}}%>
								<li><input type="button" name="queding2" id="queding2"value="确定" /><input type="button" name="quxiao2" id="quxiao2" value="取消" /></li>
							</ul>
             		</div>
              	</td>
                <td>站点类型：</td>
				<td>
					<div id="div1">
			      		<p><input type="text" class="selected_font" readonly= "readonly" name="zdlx" value="请选择"/></p>
			      			<ul>
							<%
								ArrayList stationtype = new ArrayList();
								stationtype = "".equals(zdsx1)?null:ztcommon.getZdlx2(zdsx1);
								if (stationtype != null) {
									String code = "", name = "";
									int cscount = ((Integer) stationtype.get(0)).intValue();
									int size = stationtype.size() - 1;
									int i;
									for (i = cscount; i < size; i += cscount) {
										code = (String) stationtype.get(i + stationtype.indexOf("CODE"));
										name = (String) stationtype.get(i + stationtype.indexOf("NAME"));
							%>
								<li><input type="checkbox" name="CheckboxGroup1" value="<%=code%>,<%=name%>" id="CheckboxGroup1" /><%=name%></li>
							<%}}%>
								<li><input type="button" name="queding" id="queding"value="确定" /><input type="button" name="quxiao" id="quxiao" value="取消" /></li>
							</ul>
              		</div>	
				</td>
		    </tr>
		   </table>
		  </td>
		</tr>
	</table>
	<table>
		<tr><td height="23" colspan="4"><div id="parent" style="display:inline">
              <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
        </div></td></tr>
	</table>
	
	<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
		<table width="100%" height="80%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
			<tr height = "23" class="relativeTag">
			 	<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
				<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">城市</div></td> 
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>                 
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">申请前</div></td>
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">申请后</div></td>
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">附件</div></td>
                <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">乡镇</div></td> 
     			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>        
     			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">审核状态</div></td>         
			</tr>
		 <c:forEach items="${list}" var="list" varStatus="status">
		  <tr  bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}" title="">
		   	<td>
       			<div align="center" >${status.count}</div>
       		</td>
       		<td>
             	<div align="center"><input type="checkbox" name="test[]" value="${list.id}" />
             						<input type="hidden" type="checkbox" name="test1[]" value="${list.zdid}"/>
             						<input type="hidden" type="checkbox" name="test2[]" value="${list.state}" /></div>
         	</td>
            <td>
       			<div align="center" >${list.city}</div>
       		</td>
       		<td>
       			<div align="center" >${list.xian}</div>
       		</td>
       		<td>
       			<div align="center" >${list.zdname}</div>
       		</td>
       		<td>
       			<div align="center" >${list.oldqyzt}</div>
       		</td>
       		<td>
       			<div align="center" >${list.newqyzt}</div>
       		</td>
       		<td>
       			<div>
					<c:choose>
						<c:when test="${list.fjnr eq true}">
						<a href="#" onclick="modifyjz2('${list.id}','2');return false;">
							<font  style="font-size: 15px;color: red">下载附件</font></a>
						</c:when>
						<c:when test="${list.fjnr eq false}">
							<font  style="font-size: 15px;color: red">无附件</font>
						</c:when>
					</c:choose>
				</div>
       		</td>
       		<td>
       			<div align="center" >${list.xiaoqu}</div>
       		</td>
       		<td>
       			<div align="center" >${list.zdid}</div>
       		</td>
       		<td>
				<c:if test="${list.state eq 0}">
					<div align="center">未审核</div>
				</c:if>
				<c:if test="${list.state eq 1}">
					<div align="center">审核通过</div>
				</c:if>
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
	
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		<tr>
	  		<td align="right">
		    	<div id="chehui" style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right:0px;top: 0px">
					<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
					<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">取消审核</span>
				</div>
				<div id="tongguo" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
					 <img src="<%=path%>/images/2chongzhi.png" width="100%" height="100%">
					 <span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">通过</span>
				</div>
				<div id="butongguo" style="width: 80px; height: 23px; cursor: pointer; float: right; position: relative; right: 40px">
					<img src="<%=path%>/images/2chongzhi.png" width="100%" height="100%">
					<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">不通过</span>
				</div>
			</td>
		</tr>
	</table>
	  	<input type="hidden" name="bzw1" id="bzw1" value="1" />
	  	<input type="hidden" name="zdlx1" id="zdlx1" value="<%=zdlx1%>"/>
  		<input type="hidden" name="ccz" id="ccz" value="<%=zdlx1%>"/>
  		<input type="hidden" name="zdsx1" id="zdsx1" value="<%=zdsx1%>"/>
  		<input type="hidden" name="tidc" value="0"/>
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
    
//载入执行，站点属性，站点类型的加载
		var bSwitch2=false;
		var bSwitch=false;
		window.onload=function(){
			var oDiv2=document.getElementById('div2');
			var oUl2=oDiv2.getElementsByTagName('ul')[0];
			var oP2=oDiv2.getElementsByTagName('p')[0];
			oP2.onclick=function(){
				if(bSwitch2){
					oUl2.style.display='none';
					bSwitch2=false;
				}else{
					var obj=document.getElementsByName("CheckboxGroup2");//根据自己的多选框名称修改下
					var zdlx2 =document.form1.zdsx1.value;//获取隐藏域等站点属性值
					if(zdlx2!=""&&zdlx2!=null){
						var zdlx3 = zdlx2.split(",");
						for(var i=0;i<obj.length;i++){
							var m = obj[i].value.toString().indexOf(",");
							var bm = obj[i].value.toString().substring(0,m);
							for(var j=0;j<zdlx3.length;j++){
								var zdlx4 = zdlx3[j].toString().substring(1,zdlx3[j].length-1);
								if(bm==zdlx4){
									obj[i].checked = true; 					
								}
							} 
						} 
					} 
					oUl2.style.display='block';
					bSwitch2=true;
				}
			};
			
			var oDiv=document.getElementById('div1');
			var oUl=oDiv.getElementsByTagName('ul')[0];
			var oP=oDiv.getElementsByTagName('p')[0];
			oP.onclick=function(){
				if(bSwitch){
					oUl.style.display='none';
					bSwitch=false;
				}else{
					var obj=document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
					var zdlx2 = document.form1.zdlx1.value;//获取隐藏域等站点类型值
					if(zdlx2!=""&&zdlx2!=null){
						var zdlx3 = zdlx2.split(",");
						for(var i=0;i<obj.length;i++){
							var m = obj[i].value.toString().indexOf(",");
							var bm = obj[i].value.toString().substring(0,m);
							for(var j=0;j<zdlx3.length;j++){
								var zdlx4 = zdlx3[j].toString().substring(1,zdlx3[j].length-1);
								if(bm==zdlx4){
									obj[i].checked = true; 					
								}
							} 
						} 
					} 
					oUl.style.display='block';
					bSwitch=true;
				}
			};

		};
		//确定选中(站点类型)
		function queding(){
			var oDiv=document.getElementById('div1');
			var oUl=oDiv.getElementsByTagName('ul')[0];
			var obj=document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
			var str1="";
			var str2="";
			for(var i=0;i< obj.length;i++){
				if(obj[i].checked){
					var m = obj[i].value.toString().indexOf(",");
					var bm = obj[i].value.toString().substring(0,m);
					var mc = obj[i].value.toString().substring(m+1,obj[i].value.toString().length);
					str1 = str1+"'"+bm+"',";
					str2 = str2+mc+",";
				}
			}
			str1=str1.substring(0,str1.length-1);
			str2=str2.substring(0,str2.length-1);
			document.form1.zdlx1.value=str1;
			
			if(str2==""){
				str2="请选择";
			}
			document.form1.zdlx.value=str2;
			oUl.style.display='none';
			bSwitch=false;
		}
		//取消选中 (站点类型) 
		function quxiao(){ 
			var oDiv=document.getElementById('div1');
			var oUl=oDiv.getElementsByTagName('ul')[0];  
			var obj = document.getElementsByName("CheckboxGroup1");
			if(obj.length){
				for(var i=0;i<obj.length;i++){ 
					obj[i].checked = false;   
				}
				oUl.style.display='none';   
			}else{   
				obj.checked = false; 
				oUl.style.display='none';   
			}  
			document.form1.zdlx.value="请选择";
			document.form1.zdlx1.value="";
			bSwitch=false;
		} 
		//确定选中(站点属性)
		function queding2(){
			var oDiv=document.getElementById('div2');
			var oUl=oDiv.getElementsByTagName('ul')[0];
			var obj=document.getElementsByName("CheckboxGroup2");//根据自己的多选框名称修改下
			var str1="";
			var str2="";
			for(var i=0;i< obj.length;i++){
				if(obj[i].checked){
					var m = obj[i].value.toString().indexOf(",");
					var bm = obj[i].value.toString().substring(0,m);
					var mc = obj[i].value.toString().substring(m+1,obj[i].value.toString().length);
					str1 = str1+"'"+bm+"',";
					str2 = str2+mc+",";
				}
			}
			str1=str1.substring(0,str1.length-1);
			str2=str2.substring(0,str2.length-1);
			if(str2==""){
				str2="请选择";
			}
			document.form1.zdsx1.value=str1;
			document.form1.jzproperty.value=str2;
			zdsxCheck(str1);
			oUl.style.display='none';
			bSwitch2=false;
		}
		//取消选中 (站点属性) 
		function quxiao2(){ 
			zdsxCheck("");
			var oDiv=document.getElementById('div2');
			var oUl=oDiv.getElementsByTagName('ul')[0];  
			var obj = document.getElementsByName("CheckboxGroup2");
			if(obj.length){
				for(var i=0;i<obj.length;i++){ 
					obj[i].checked = false;   
				}
				oUl.style.display='none';   
			}else{   
				obj.checked = false; 
				oUl.style.display='none';   
			}  
			document.form1.jzproperty.value="请选择";
			document.form1.zdsx1.value="";
			bSwitch2=false;
		} 

</script>

<script type="text/javascript">
//站点属性
function zdsxCheck(obj) {
	var sid = obj;
	document.form1.zdlx1.value="";
	sendRequest2(path + "/servlet/garea?action=zdsxa&pid=" + sid,
				"jzproperty");
}

function sendRequest2(url, para) {

	createXMLHttpRequest();

	XMLHttpReq.open("GET", url, true);
	if(para=="jzproperty"){
	 	XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数
	}
	XMLHttpReq.send(null);
}

function processResponse_zdlx() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			updateZdlx(res);
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}

}

function updateZdlx(res) {

	var str="<p><input type='text' class='selected_font' readonly= 'readonly' name='zdlx' value='请选择'/></p><ul>";
	for ( var i = 0; i < res.length; i += 2) {
		str+="<li><input type='checkbox' name='CheckboxGroup1' value='"+res[i].firstChild.data+","+res[i + 1].firstChild.data+"' id='CheckboxGroup1' />"+res[i + 1].firstChild.data+"</li>";
	}
		str+="<li><input type='button' onclick='queding();' value='确定' /><input type='button'  onclick='quxiao();'  value='取消' /></li></ul>";
	
	document.getElementById("div1").innerHTML=str;
	var oDiv=document.getElementById('div1');
	var oUl=oDiv.getElementsByTagName('ul')[0];
	var oP=oDiv.getElementsByTagName('p')[0];
	bSwitch=false;
	oP.onclick=function(){
		if(bSwitch){
			oUl.style.display='none';
			bSwitch=false;
		}else{
			oUl.style.display='block';
			bSwitch=true;
		}
	};
}
</script>
<script type="text/javascript">
var path = '<%=path%>';
var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
  function fileChange(target) { 
	document.form1.tidc.value=0;
    var fileSize = 0;           
    if (isIE && !target.files) {       
      var filePath = target.value;       
      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");          
      var file = fileSystem.GetFile (filePath);       
      fileSize = file.Size;      
    } else {      
     fileSize = target.files[0].size;       
     }     
     var size = fileSize / 1024;
     if(size>6144){    
      	alert("附件不能大于6M");  
      	document.form1.tidc.value=1;
     }    
}

function modifyjz2(id,bzw){
     document.form1.action=path+"/servlet/DownloadServlet?id="+id+"&bzw="+bzw;
     document.form1.submit();
}
</script>
<script type="text/javascript">
//审核通过
function tongguo() {
   
	var m = document.getElementsByName('test[]');
	var mm = document.getElementsByName('test1[]');
	var nn = document.getElementsByName('test2[]');
	var l = m.length;
	var chooseIdStr = "";
	var chooseIdStr1 = "";
	var bz = 0;
	var n = 0;
	var count = 0;
	var countt = 0;
	var count1 = 0;
	var count2 = 0;
	var bzw = 1;
	var bzw1 = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
		if(m[i].checked == true){
			if(nn[i].value=='1'){
				countt +=1;
			}
		}
	}
	if(countt>0){
		alert("不能重复通过,请确认");
		return;
	}else{
		if (count != 0) {
			var abc = showdiv("请稍等..............");
			alert(abc);
			if (count % 120 == 0) {
				n = count / 120 - 1;
			} else {
				n = (count - (count % 120)) / 120;
			}
			for ( var i = 0; i < l; i++) {
				if (m[i].checked == true) {
					bz += 1;
					count1 += 1;
					 chooseIdStr = chooseIdStr +"'" +m[i].value +"',"; 
		             chooseIdStr1 = chooseIdStr1 +"'" +mm[i].value +"',";
				}
				bzw1 = document.form1.bzw1.value;
				if (bzw1 == "1") {
					if (count1 <= 120 * n
							|| ((bz + count2) >= 119 && (bz + count2) <= 121)) {
						if (((bz + count2) / 120 == 1)
								|| ((bz + count2) >= 119 && (bz + count2) <= 121)) {
							chooseIdStr = chooseIdStr.substring(0,
									chooseIdStr.length - 1);
							chooseIdStr1 = chooseIdStr1.substring(0,
									chooseIdStr1.length - 1);
							shenhetg(chooseIdStr, chooseIdStr1);
							chooseIdStr = "";
							chooseIdStr1 = "";
							bz = 0;
							count2 = 0;	          	
						}
					} else if (count == count1 && bzw == 1) {
						chooseIdStr = chooseIdStr.substring(0,
								chooseIdStr.length - 1);
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						bzw = 0;
						document.form1.action = path
								+ "/servlet/QyztServlet?command=tongguo1&chooseIdStr="
								+ chooseIdStr + "&chooseIdStr1=" +chooseIdStr1;
						document.form1.submit();
					}
				} else if (bzw1 == "0") {
					document.form1.action = path + "/web/msg.jsp";
					document.form1.submit();
					return;
				}
			}
		} else {
			closediv();
			alert("请选择信息！");
		}
	}
}

//审核不通过
 function butongguo() {
	var m = document.getElementsByName('test[]');
	var mm = document.getElementsByName('test1[]');
	var nn = document.getElementsByName('test2[]');
	var l = m.length;
	var chooseIdStr = "";
	var chooseIdStr1 = "";
	var bz = 0;
	var n = 0;
	var count = 0;
	var countt = 0;
	var count1 = 0;
	var count2 = 0;
	var count3 = 0;
	var bzw = 1;
	var bzw1 = "";
	for ( var i = 0; i < l; i++) {
		if (m[i].checked == true) {
			count += 1;
		}
		if(m[i].checked == true){
			if(nn[i].value=='1'){
				countt +=1;
			}
		}
	}
	if(countt>0){
		alert("有已通过的单子,请先取消审核再不通过！");
		return;
	}else{
		if (count != 0) {
			var cause=prompt("请输入不通过的原因","");//将输入的内容赋给变量 name ，
			if(cause){
			var abc = showdiv("请稍等..............");
			alert(abc);
				if (count % 120 == 0) {
					n = count / 120 - 1;
				} else {
					n = (count - (count % 120)) / 120;
				}
				for ( var i = 0; i < l; i++) {
					if (m[i].checked == true) {
						bz += 1;
						count1 += 1;
						 chooseIdStr = chooseIdStr +"'" +m[i].value +"',"; 
			             chooseIdStr1 = chooseIdStr1 +"'" +mm[i].value +"',";
					}
					bzw1 = document.form1.bzw1.value;
					if (bzw1 == "1") {
						if (count1 <= 120 * n || ((bz) >= 119 && (bz) <= 121)) {
							if (((bz) / 120 == 1)|| ((bz) >= 119 && (bz) <= 121)) {
							        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
							        chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
						            butongguo1(chooseIdStr, chooseIdStr1,cause);
							        chooseIdStr = "";
						            chooseIdStr1 = "";
					       			bz=0;
					       			count2=0;
							}
						} else if (count == count1 && bzw == 1) {
					          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
					          chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
						      bzw=0;
						      document.form1.action=path+"/servlet/QyztServlet?command=butongguo1&chooseIdStr="+chooseIdStr+"&chooseIdStr1="+chooseIdStr1+"&cause="+cause;
					          document.form1.submit(); 
						}
					} else if (bzw1 == "0") {
						document.form1.action = path + "/web/msg.jsp";
						document.form1.submit();
						return;
					}
				}
			}else{
    			alert("请输入不通过的原因");
    		}
		} else {
			alert("请选择信息！");
		}
	}
}

 //取消审核  （撤回）
 function chehui(){
  
	var m = document.getElementsByName('test[]');
	var mm = document.getElementsByName('test1[]');
	var l = m.length;
	var chooseIdStr = "";
	var chooseIdStr1 = "";
	var bz = 0;
	var n = 0;
	var count = 0;
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
			var abc = showdiv("请稍等..............");
			alert(abc);
		if (count % 120 == 0) {
			n = count / 120 - 1;
		} else {
			n = (count - (count % 120)) / 120;
		}
		for ( var i = 0; i < l; i++) {
			if (m[i].checked == true) {
				bz += 1;
				count1 += 1;
				 chooseIdStr = chooseIdStr +"'" +m[i].value +"',"; 
	             chooseIdStr1 = chooseIdStr1 +"'" +mm[i].value +"',";
			}
			bzw1 = document.form1.bzw1.value;
			if (bzw1 == "1") {
				if (count1 <= 120 * n
						|| ((bz + count2) >= 119 && (bz + count2) <= 121)) {
					if (((bz + count2) / 120 == 1)
							|| ((bz + count2) >= 119 && (bz + count2) <= 121)) {
						chooseIdStr = chooseIdStr.substring(0,
								chooseIdStr.length - 1);
						chooseIdStr1 = chooseIdStr1.substring(0,
								chooseIdStr1.length - 1);
						chehuiqx(chooseIdStr, chooseIdStr1);
						chooseIdStr = "";
						chooseIdStr1 = "";
						bz = 0;
						count2 = 0;	          	
					}
				} else if (count == count1 && bzw == 1) {
					chooseIdStr = chooseIdStr.substring(0,
							chooseIdStr.length - 1);
					chooseIdStr1 = chooseIdStr1.substring(0,
							chooseIdStr1.length - 1);
					bzw = 0;
					document.form1.action = path
							+ "/servlet/QyztServlet?command=chehui1&chooseIdStr="
							+ chooseIdStr + "&chooseIdStr1=" + chooseIdStr1
							;
					document.form1.submit();
				}
			} else if (bzw1 == "0") {
				document.form1.action = path + "/web/msg.jsp";
				document.form1.submit();
				return;
			}
		}
	} else {
		alert("请选择信息！");
	} 
}

//审核通过
function shenhetg(chooseIdStr, chooseIdStr1) {
	sendRequest1(path + "/servlet/QyztServlet?command=tongguo2&chooseIdStr="
			+ chooseIdStr + "&chooseIdStr1=" + chooseIdStr1, "checkcityno1");
}

//审核不通过
function butongguo1(chooseIdStr, chooseIdStr1,cause) {
	sendRequest1(path + "/servlet/QyztServlet?command=butongguo2&chooseIdStr="
	       + chooseIdStr + "&chooseIdStr1=" + chooseIdStr1+"&cause="+cause, "checkcityno2");
}
 
//取消审核（撤回）
function chehuiqx(chooseIdStr, chooseIdStr1) {
	sendRequest1(path + "/servlet/QyztServlet?command=chehui2&chooseIdStr="
			+ chooseIdStr + "&chooseIdStr1=" + chooseIdStr1, "checkcityno3");
}

function sendRequest1(url,para) {

		createXMLHttpRequest1();
		XMLHttpReq1.open("POST", url, false);
		if(para=="checkcityno3"){
			XMLHttpReq1.onreadystatechange = processResponse_checkcity1;
		}else if(para=="checkcityno1"){
			XMLHttpReq1.onreadystatechange = processResponse_checkcityno1;
		}else if(para=="checkcityno2"){
			XMLHttpReq1.onreadystatechange = processResponse_checkcityno2;
		}else{
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
	
	//审核通过
	function processResponse_checkcityno1() {	
		if (XMLHttpReq1.readyState == 4) { // 判断对象状态
	    	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
	        	document.form1.bzw1.value = XMLHttpReq1.responseText;
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
	
	//审核不通过
	function processResponse_checkcityno2() {	
		if (XMLHttpReq1.readyState == 4) { // 判断对象状态
	    	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
	        	document.form1.bzw1.value = XMLHttpReq1.responseText;
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
	
	//取消审核（撤回）
	function processResponse_checkcity1() {	
		if (XMLHttpReq1.readyState == 4) { // 判断对象状态
	    	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
	    	document.form1.bzw1.value= XMLHttpReq1.responseText;
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
</script>
<script type="text/javascript">
   	document.form1.shi.value='<%=shi%>';
	document.form1.shengzt.value='<%=shengzt%>';
	document.form1.jzproperty.value='<%=jzproperty%>';
	document.form1.zdlx.value='<%=zdlx%>';
	document.form1.zdlx1.value=document.form1.ccz.value;
	document.form1.sqnr.value='<%=sqnr%>';
	document.form1.zdid.value='<%=zdid%>';
	document.form1.zdname.value='<%=zdname%>';
</script>
</html>

