
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String shengId = (String)session.getAttribute("accountSheng");
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<script src="<%=path%>/javascript/PopupCalendar.js">
</script>
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
</script>
		<script src="<%=path%>/javascript/tx.js">
</script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
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
		
		if(document.form1.shi.value=="0"){
                         	alert("请选择市！");
                                 return
                     	}
               /*   if(document.form1.xian.value=="0"){
                         	alert("请选择区县！");
                                 return
                     	}
                     	
                     	if(document.form1.xiaoqu.value=="0"){
                         	alert("请选择乡镇！");
                                 return
                     	} */
                     	if (document.form1.role.value == "") {
							alert("请选择角色！");
							return
                    	}
                     	
                    	if (document.form1.msd.value == "") {
							alert("请输入财辅人力账号 @SD，注意英文大小写！");
							return
                    	}
                    	
                    	if(document.form1.bumen.value=="0"){
                         	alert("请选择部门！");
                                 return
                     	}
		//邮箱
		var email = document.form1.email.value;
		if(email!=""){
		//定义正则表达式
   		var reg=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ;
   		if(!reg.test(email)){
   			alert("非法邮箱地址，请重新输入。");
   			return;
   		}
		
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
        		var msd=document.getElementById("msd").value;
        		
        		
        		var zip=document.getElementById("zip").value;
        		var email=document.getElementById("email").value;
        		var position=document.getElementById("position").value;
        		var address=document.getElementById("address").value;
        		var memo=document.getElementById("memo").value;

        		//var canShuStr="showBuMenList.jsp?accountName='"+accountName+"'&name='"+name+"'&tel='"+tel+"'&mobile='"+mobile+"'&zip='"+zip+"'&email='"+email+"'&position='"+position+"'&address='"+address+"'&memo='"+memo+"'";
        		var canShuStr="showBuMenList.jsp?accountName="+accountName+"&name="+name+"&tel="+tel+"&mobile="+mobile+"&zip="+zip+"&email="+email+"&position="+position+"&address="+address+"&memo="+memo+"&CTHRNUMBER="+msd;
        		
               window.open(canShuStr,'','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
         function fanhui(){
			document.form1.action=path+"/web/sdttWeb/sys/accountList.jsp";
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增用户</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
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
	<%
									String accountName = request.getParameter("accountName");
									accountName = accountName == null ? "" : accountName;
									
									String name = request.getParameter("name");
									name = name == null ? "" : name;
									
									String tel = request.getParameter("tel");
									tel = tel == null ? "" : tel;
									
									String mobile = request.getParameter("mobile");
									mobile = mobile == null ? "" : mobile;
									
									String CTHRNUMBER = request.getParameter("CTHRNUMBER");
									CTHRNUMBER = CTHRNUMBER == null ? "" : CTHRNUMBER;
									
									ArrayList list = new ArrayList();
									list = roleBean.getAllRole(sign);
									int countColum = ((Integer) list.get(0)).intValue();
									String roleId2 = "", roleName2 = "";
							 
							  String zip = request.getParameter("zip");
							  zip = zip == null ? "" : zip;
							  
							  String email = request.getParameter("email");
							  email = email == null ? "" : email;
							  
					         String address = request.getParameter("address");
						     address = address == null ? "" : address;		  
							  
					         String memo = request.getParameter("memo");
				             memo = memo == null ? "" : memo;		  
							   
							  
%>
<body>
<form action="" name="form1" method="post">
<input type="hidden" name="sheng" value="137"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
	
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">用户新增</div>
				<div class="content01_1">
					<table width="1000px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="100px">账号</td>
							
							<td width="100px">
								<input type="text" id="accountName" style="width: 130px;" name="accountName"
									value="<%=accountName%>"   onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" maxlength="18"/>
									 <span style="color: #FF0000; font-weight: bold">*</span>
										<img id="jiancha" alt="" src="<%=request.getContextPath()%>/images/jiancha.png" />
							</td>
							<td align="right" width="100px">姓名</td>
							
							<td width="100px">
								<input type="text" name="name" value="<%=name%>"
																			class="form" style="width: 130px;" maxlength="25" />
																	 <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">地市</td>
							<td width="100px">
									<select name="shi" id="shi" style="width:130px;" onchange="changeCity()">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList shenglist = new ArrayList();
						         	shenglist = commBean.getAgcode(shengId,"0",loginName);//
						         	if(shenglist!=null){
						         		String sfid="",sfnm="";
						         		int scount = ((Integer)shenglist.get(0)).intValue();
						         		for(int i=scount;i<shenglist.size()-1;i+=scount)
					                    {
					                    	sfid=(String)shenglist.get(i+shenglist.indexOf("AGCODE"));
					                    	sfnm=(String)shenglist.get(i+shenglist.indexOf("AGNAME"));
					                    %>
					                    <option value="<%=sfid%>"><%=sfnm%></option>
					                    <%}
						         	}
						         %>
					         	</select>
					         	 <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">区县</td>
							
								<td><select name="xian" id="xian" style="width:130px;" onchange="changeCountry()" class="selected_font">
	         					<option value="0">请选择</option>
				         		</select>
				         		<!--  <span style="color: #FF0000; font-weight: bold">*</span> -->
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">乡镇</td>
							<td width="100px">
							 <select name="xiaoqu" id="xiaoqu" style="width:130px">
         						<option value="0">请选择</option>         						
         				     </select>
         				  <!--    <span style="color: #FF0000; font-weight: bold">*</span> -->
							</td>
							<td align="right" width="100px">座机</td>
							<td width="100px">
								<input type="text" name="tel" value="<%=tel%>" style="width: 130px;" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="11" />
							</td>
							<td align="right" width="100px">手机</td>
							<td width="100px">
							<input type="text" name="mobile" value="<%=mobile%>"  style="width: 130px;" onkeyup="value=value.replace(/[^\d]/g,'')"  maxlength="11"  />
							</td>
							<td align="right" width="100px">角色</td>
							<td width="100px">
								<select name="role" style="width: 130" multiple="multiple">
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
							 <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
						</tr>
						
						<tr>
							<td align="right" width="100px">性别</td>
							<td width="100px">
								<select name="sex" style="width: 130px" >
									<option value="1">男</option>
									<option value="0">女</option>
								</select>
							</td>
							<td align="right" width="100px">邮编</td>
							<td width="100px">
								<input type="text" name="zip" value="<%=zip%>" style="width: 130px;" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="6"/>
							</td>
							<td align="right" width="100px">邮箱</td>
							<td width="100px">
								<input type="email" name="email" id="email" value="<%=email%>" style="width: 130px;"  />
							</td>
							<td align="right" width="100px">职位</td>
							<td width="100px">
								<select name="position"  style="width:130px" > 
                                     <option value="0">请选择</option>
									<%
										ArrayList stationtype = new ArrayList();
										stationtype = ztcommon.getSelctOptions("zw");
										if(stationtype!=null){
											String code="",name1="";
											int cscount = ((Integer)stationtype.get(0)).intValue();
											for(int i=cscount;i<stationtype.size()-1;i+=cscount){
												code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
												name1=(String)stationtype.get(i+stationtype.indexOf("NAME"));
											%>
     											<option value="<%=code%>"><%=name1%></option>
									<%  }
									}
									%>
                                </select>
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">部门</td>
							<td width="100px">
							    <select name="bumen" style="width: 130px;">
							        <option value="0">请选择</option>
							    </select>
								<%--<input type="hidden" name="bumen" value="<%=bumenid%>"/>
								<input type="text" name="bumenid" value="<%=bumen%>" style="width: 130px;"/>
								<img id="liulan11" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png " style="cursor: pointer" />
								 --%>
							 <span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">财辅人力账号(@SD)</td>
							<td width="100px">
								<input type="text"  name="msd" ifd="msd" value=""  style="width: 130px;" />
								<span style="color: #FF0000; font-weight: bold">*</span>
							</td>
							<td align="right" width="100px">地址</td>
							<td width="100px">
								<input type="text"  name="address" value="<%=address%>"  style="width: 130px;" />
							</td>
							<td align="right" width="100px">备注</td>
							<td width="100px">
								<input type="text"  name="memo" value="<%=memo%>" style="width: 130px;" />
							</td>
						</tr>
						<tr>
						<td align="right" colspan="8" height="60px">
								<input name="baocun" type="button" class="btn_c1" id="baocun" value="保存" />&nbsp; 
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
            //  window.alert(res);
             
                       
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

	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
	 //alert("11111");
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeCity(){
	var sid = document.form1.shi.value;
	updateDept(sid);
	
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function changeCountry(){
	var sid = document.form1.xian.value;

	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
//地市级联查询部门
function updateDept(sid){
   var deptObj = document.all.bumen;
   deptObj.options.length="0";
   deptObj.add(new Option("请选择","0"));
   //console.log(sid)
   if(sid && sid !="0"){
      $.ajax({
         type:'post',
         url: path + '/servlet/commonBeanServlet?action=dept',
         cache: false,
         data: {
         	shi: sid,
         	fdeptcode: sid
         },
         dataType: 'json',
         success: function(data){
         	//console.log(data)
         	if(data){
	         	for ( var i = 0; i < data.length; i++) {
					var optn = data[i].split("\|");
					deptObj.add(new Option(optn[1],optn[0]));
				}
         	}
         },
         error: function(){
            return;
         }
      });
   }
} 

function openDept(){
	var shiObj = document.form1.shi;
	if(shiObj.value == "0"){
	}else{
	   window.open("deptWin.jsp", "newwindowDept?shi="+shiObj.value, "height=550, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
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
</html>
