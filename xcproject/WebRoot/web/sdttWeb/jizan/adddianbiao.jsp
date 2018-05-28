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
    String id = request.getParameter("id");
    session.setAttribute("dbid", id);
    String zdid = request.getParameter("zdid");
    String bz = request.getParameter("bz");
    SiteModifyBean form = new SiteModifyBean();
    
    String a="", dbbm="", shi="0",xian="0",xiaoqu="0",zdmc="0",dbmc="",cssysj="",beilv="",csds="",dfzf="0";
    if(bz!=null&&bz.equals("1")&&id!=null){
    	String sql = "select dbbm, dbname,cssytime,beilv,csds,dfzflx from dianbiao where id="+id+"";
    	System.out.println(sql.toString());
    	DataBase db = new DataBase();
	    ResultSet rs = null;
	    db.connectDb();
	    rs=db.queryAll(sql.toString());
	    while(rs.next()){
	    	dbmc=rs.getString("dbname")==null?"":rs.getString("dbname");
	    	cssysj=rs.getString("cssytime")==null?"":rs.getString("cssytime");
	    	System.out.println(cssysj.length());
	    	if(cssysj.length()!=0){
	    	 a = cssysj.substring(0, 10);
	    	 }
	    	System.out.println(cssysj);
	    	beilv=rs.getString("beilv")==null?"":rs.getString("beilv");
	    	csds=rs.getString("csds")==null?"":rs.getString("csds");
	    	dfzf=rs.getString("dfzflx");
	    	dbbm=rs.getString("dbbm")==null?"":rs.getString("dbbm");
	    }
	    form = form.getJizhan(zdid, "");
	    shi=form.getShi();
	    xian=form.getXian();
	    xiaoqu=form.getXiaoqu();
	    zdmc=form.getId();
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>

<%if(!"1".equals(bz)){ %>
<title>新增电表信息</title>
<% }else{%>
<title>修改电表信息</title>
<%}%>
<script type="text/javascript">
	var path = '<%=path%>';

	function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = "";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">";

		}
		else
		{
			trObject.style.display = "none";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">";
		}
}
	function saveAccount(){
	                var bl=document.form1.beilv.value;
					var ds = document.form1.csds.value;
					var a = document.form1.dbbm.value;
					if(
						checkNotSelected(document.form1.shi,"城市")&&
						checkNotSelected(document.form1.xian,"区县")&&
						checkNotSelected(document.form1.xiaoqu,"乡镇")&&
						checkNotSelected(document.form1.zdmc,"站点名称")&&
						checkNotnull(document.form1.dbname,"电表名称")&&
						checkNotnull(document.form1.t_sccbsj,"上次抄表时间")&&
						checkNotnull(document.form1.beilv,"倍率")&&
						checkNotnull(document.form1.csds,"初始读数")&&
						checkNotnull(document.form1.dbbm,"电表编码")&&
						checkNotSelected(document.form1.dfzflx,"电费支付类型")
					)
					{
					if(Number(bl)){
						alert("倍率必须为数字！");
						return false;
					}
					if(!isNO(ds)){
						alert("初始读数必须为数字！");
						return false;
					}
					if(!isNO(a)){
						alert("电表编码必须为数字！");
						return false;
					}
					var bz='<%=bz%>';
					if("1"==bz){
						if(confirm("您将要修改电表信息！确认信息正确！")){
					document.form1.action=path+"/servlet/dianbiao?action=addDB&bz="+'<%=bz%>';
					document.form1.submit();
					showdiv("请稍等..............");
						}
					}else{
						if(confirm("您将要添加电表信息！确认信息正确！")){
					document.form1.action=path+"/servlet/dianbiao?action=addDB&bz="+'<%=bz%>';
					document.form1.submit();
					showdiv("请稍等..............");
								}
							}
						}
					}
	
        function vName(){
         	var accName = document.form1.dbid.value;
                 if(accName==""){
           	alert("电表ID不能为空！");
                   return
          }
               window.open('validateDbid.jsp?dbid='+accName+'&id=0','','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
        }
        function vCode(){
          var accCode = document.form1.workSn.value;
          if(accCode==""){
           	alert("不能为空！");
                   return
          }
               window.open('accountCode.jsp?accountId=0&accountCode='+accCode,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
        }

        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！");
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
        }
        function fanhui(){        
              document.form1.action=path+"/web/jizhan/dianbiaolist.jsp";
              document.form1.submit();
        }
        
        
        
        
$(function(){
	$("#liulan").click(function(){
		alert(123);
		shoulist();
	});
	$("#cancelBtn").click(function(){
	    fanhui();
	});
$("#resetBtn").click(function(){
	$.each($("form input[type='text']"),function(){
	  $(this).val("");
          });
	});
// $("#saveBtn").click(function(){
// 	saveAccount();
	
// 	});
$("#vnameBtn").click(function(){
	vName();
	});
});
			
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
				
				<%if(!"1".equals(bz)){ %>
				<div class="tit01">电表新增</div>
				<% }else{%>
				<div class="tit01">电表修改</div>
				<%}%>
				<div class="content01_1">
					<table width="100px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
					<tr>
				         <td align="right" width="100px">城市</td>
							<td width="100px">
								<select name="shi" class="selected_font" onchange="changeCity()">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList shenglist = new ArrayList();
						         	shenglist = commBean.getAgcode(shengId,"0",loginName);
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
					         	</select>&nbsp;
							</td>
							<td align="right" width="100px">区县</td>
							<td width="100px">
								<select name="xian" class="selected_font"    onchange="changeCountry()">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList xianlist = new ArrayList();
						         	xianlist = commBean.getAgcode(form.getShi(),form.getXian(),loginName);
						         	if(xianlist!=null){
						         		String agcode="",agname="";
						         		int scount = ((Integer)xianlist.get(0)).intValue();
						         		for(int i=scount;i<xianlist.size()-1;i+=scount)
					                    {
					                    	agcode=(String)xianlist.get(i+xianlist.indexOf("AGCODE"));
					                    	agname=(String)xianlist.get(i+xianlist.indexOf("AGNAME"));
					                    %>
					                    <option value="<%=agcode%>"><%=agname%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">乡镇</td>
							<td width="100px">
								<select name="xiaoqu" id="xiaoqu" onchange="changezdmc()"  class="selected_font" >
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList xiaoqulist = new ArrayList();
						         	xiaoqulist = commBean.getAgcode(form.getXian(),form.getXiaoqu(),loginName);
						         	if(xiaoqulist!=null){
						         		String agcode="",agname="";
						         		int scount = ((Integer)xiaoqulist.get(0)).intValue();
						         		for(int i=scount;i<xiaoqulist.size()-1;i+=scount)
					                    {
					                    	agcode=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGCODE"));
					                    	agname=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGNAME"));
					                    %>
					                    <option value="<%=agcode%>"><%=agname%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">站点名称</td>
							<td width="180px">
								<select name="zdmc" style="width:180px" onchange="changeDbbm()" >								
         							<option value="0">请选择</option>
         							<%
						         	ArrayList zdmclist = new ArrayList();
						         	zdmclist = commBean.getzdmc(form.getId());
						         	if(zdmclist!=null){
						         		String agcode="",agname="";
						         		int scount = ((Integer)zdmclist.get(0)).intValue();
						         		for(int i=scount;i<zdmclist.size()-1;i+=scount)
					                    {
					                    	agcode=(String)zdmclist.get(i+zdmclist.indexOf("ID"));
					                    	agname=(String)zdmclist.get(i+zdmclist.indexOf("JZNAME"));
					                    %>
					                    <option value="<%=agcode%>"><%=agname%></option>
					                    <%}
						         	}
						         %>
         						</select>
							</td>
				     </tr>  				     
				     <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />电表信息</td>
                    </tr>  
						<tr>
							<td align="right" width="100px">电表名称</td>
							<td width="60px">
								<input type="text" name="dbname" value="<%=dbmc %>" />
							</td>							
							<td align="right" width="100px">初始使用时间</td>
							<td width="100px">
								<input type="text" name="t_sccbsj" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" value="<%=a%>" />
							</td>
							<td align="right" width="100px">倍率</td>
							<td width="25%"><input type="text" name="beilv" value='<%=beilv %>' class="selected_font3"/></td>
							<td align="right" width="100px">初始读数</td>
							<td width="100px">
								<input type="text" name="csds" value="<%=csds %>" class="selected_font3"/>
							</td>	
						</tr>	
						<tr>
						<td align="right" width="100px">电费支付类型</td>
							<td width="100px">
								<select name="dfzflx"  class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList dfzflx = new ArrayList();
	         	dfzflx = ztcommon.getSelctOptions("DFZFLX");
	         	if(dfzflx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)dfzflx.get(0)).intValue();
	         		for(int i=cscount;i<dfzflx.size()-1;i+=cscount)
                    {
                    	code=(String)dfzflx.get(i+dfzflx.indexOf("CODE"));
                    	name=(String)dfzflx.get(i+dfzflx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	
         	</select>
							</td>
							<td align="right" width="100px">电表编码</td>
							<td width="100px">
								<input type="text" name="dbbm" value="<%=dbbm %>" class="selected_font3"/>
							</td>
						</tr>											
						<tr>
							<td align="right" colspan="8" height="60px">
<!-- 								<input name="button2" type="submit" class="btn_c1" id="button2" value="临时保存" />&nbsp;  -->
								<input onclick="saveAccount()" type="button" class="btn_c1" id="button2" value="保存" />&nbsp; 
								<input type="reset" class="btn_c1" id="button2" value="重置" />&nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td>
		</tr>
		</table>
		<input type="hidden" name="zdid" value=""/>
			<script type="text/javascript">
	 function showlist(){
       //window.open("dianbiaolist.jsp",'_blank','newwindow','height=350, width=630,top=1200,left=300,toolbar=no,menubar=no, scrollbars=yes, resizable=no,location=n o, status=no');
      window.open('zhandianselect.jsp','','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
  
    }
	function pageRefsh(){
	document.form1.action=path+"/web/sdttWeb/jizan/adddianbiao.jsp";
    document.form1.submit();
}
var path = '<%=path%>';
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
		}else if(para=="dbbm"){
			XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数			
		}else if(para=="zdmc"){
			XMLHttpReq.onreadystatechange = processResponse_zdmc;
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
        	//var ress=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZd(res);
         // updateUser(ress);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_zdmc() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("ress");
        	//var ress=XMLHttpReq.responseXML.getElementsByTagName("res");
          updatezdmc(res);
         // updateUser(ress);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
	function processResponse_zdlx() {
	//alert("333");
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("ress");
       //   updateZdlx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
    
	}
	//站点类型
// function updateZdlx(res){

// 	var shilist = document.all.dbbm;
// 	shilist.options.length="0";
// 	shilist.add(new Option("请选择","0"));
	
// 	for(var i = 0;i<res.length;i+=2){
// 		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
// 	}
// }
	
	
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
function changezdmc(){
	var sid = document.form1.xiaoqu.value;
	if(sid=="0"){
		var shilist = document.all.zdmc;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=quxian&pid="+sid,"zdmc");
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
function changeDbbm(){
	var sid = document.form1.zdmc.value;
	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=dbbm&pid="+sid,"dbbm");
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
function updatezdmc(res){
	var shilist = document.all.zdmc;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function updateUser(ress){
	var shilist = document.all.username;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	
	for(var i = 0;i<ress.length;i+=2){
		shilist.add(new Option(ress[i+1].firstChild.data,ress[i].firstChild.data));
	}
}
		function isNO(f){
			return f.match(new RegExp("^[0-9]+$")); 
		} 
		function Number(a){
			return a.match(new RegExp("^\d+\.\d+$")); 
		}
document.form1.shi.value='<%=shi%>';
	document.form1.xian.value='<%=xian%>';
	document.form1.xiaoqu.value='<%=xiaoqu%>';
	document.form1.zdmc.value='<%=zdmc%>';
	document.form1.dfzflx.value='<%=dfzf%>';
</script>
	</form>	

</body>

</html>

