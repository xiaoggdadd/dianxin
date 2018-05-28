<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.Double" %>
<%@ page import="com.noki.database.DataBase" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.*" %>
<%
	String agcode1="";
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	Account account=(Account)session.getAttribute("account");
	String sheng = (String)session.getAttribute("accountSheng");
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;  
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";	
	String accountname=account.getAccountName();
	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM");
	String entrytime=mat.format(new Date());
	String dbcode = request.getParameter("dbbm")!=null?request.getParameter("dbbm"):"0";
	String zdbm = request.getParameter("jzcode")!=null?request.getParameter("jzcode"):"0";
	System.out.println(zdbm);
	String yearmonth="",dbbm="0",sfyz="",sh="0",xx="0",zdmc="0",xiaoqu="0";
	SiteModifyBean form = new SiteModifyBean();
	String danjia="",ftbl="",zdid="0";
	String a="",b="";
	if(!dbcode.equals("0")){
		String s = "select danjia,zdid from dianjia where dbbm='"+dbcode+"' and yearmonth='"+entrytime+"'";
		String q = "select dianxin from ftbl where dbbm='"+dbcode+"' and yearmonth='"+entrytime+"'";
		DataBase db1 = new DataBase();
		DataBase db2 = new DataBase();
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		db1.connectDb();
		rs1=db1.queryAll(s);
		while(rs1.next()){
			danjia=rs1.getString("danjia")==null?"0":rs1.getString("danjia");
			zdid=rs1.getString("zdid");
		}
		db2.connectDb();
		rs2=db2.queryAll(q);
		while(rs2.next()){
			ftbl=rs2.getString("dianxin")==null?"0":rs2.getString("dianxin");
		}
		form = form.getJizhan(zdid, "");
	    sh = form.getShi()!=null?form.getShi():"0";
	    xx = form.getXian()!=null?form.getXian():"0";
	    xiaoqu = form.getXiaoqu()!=null?form.getXiaoqu():"0";
	    zdmc = form.getId()==null?form.getId():"0";
	
	String sql="select max(ENDMONTHOLD),max(THISDEGREE) from AMMETERDEGREES where AMMETERID_FK=(select dbid from dianbiao where dbbm='"+dbcode+"')";
	System.out.println(sql);
	ResultSet rs = null;
	DataBase db = new DataBase();
	rs=db.queryAll(sql.toString());
	while(rs.next()){
		a=rs.getString(1)!=null?rs.getString(1):"";
		b=rs.getString(2)!=null?rs.getString(2):"";
			}
		}
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增预付费信息</title>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
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
				<div class="tit01">预付费信息新增</div>
				<div class="content01_1">
					<table width="1000px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
									<tr bgcolor="F9F9F9" class="selected_font1">
										<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />基本信息</td>
									</tr>
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
								<select name="zdmc" style="width:150px" onchange="changeDbbm()">								
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
							<tr>
							<td align="right" width="100px">站点编码</td>
							<td width="120px">
							<select name="jzcode" >
								<option value="0">请选择</option>
								<%
						         	ArrayList list = new ArrayList();
						         	list = commBean.getzdbm(form.getJzcode());
						         	if(list!=null){
						         		String agcode="",agname="";
						         		int scount = ((Integer)list.get(0)).intValue();
						         		for(int i=scount;i<list.size()-1;i+=scount)
					                    {
					                    	agcode=(String)list.get(i+list.indexOf("JZCODE"));
					                    	agname=(String)list.get(i+list.indexOf("JZCODE"));
					                    %>
					                    <option value="<%=agcode%>"><%=agname%></option>
					                    <%}
						         	}
						         %>
							</select>
							</td>
							<td align="right" width="100px">电表编码</td>
							<td width="120px">
								<select name="dbbm" onchange="shua()">
         							<option value="0">请选择</option>
         							<%
						         	ArrayList dbbmlist = new ArrayList();
						         	dbbmlist = commBean.getdbbm(form.getId());
						         	if(zdmclist!=null){
						         		String agcode="",agname="";
						         		int scount = ((Integer)dbbmlist.get(0)).intValue();
						         		for(int i=scount;i<dbbmlist.size()-1;i+=scount)
					                    {
					                    	agcode=(String)dbbmlist.get(i+dbbmlist.indexOf("DBBM"));
					                    	agname=(String)dbbmlist.get(i+dbbmlist.indexOf("DBBM"));
					                    %>
					                    <option value="<%=agcode%>"><%=agname%></option>
					                    <%}
						         	}
						         %>
         						</select>
							</td>
						</tr>	
						<tr >
							<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15"/>铁塔缴费信息</td>
						</tr>	
						<tr>
							<td align="right" width="80px">缴费起始日期</td>
							<td width="80px" align="left">
								<input type="text" name="t_jfqsrq" value='<%=a %>' onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"/>
							</td>
							<td align="right" width="80px">缴费终止日期</td>
							<td width="80px" align="left">
								<input type="text" name="t_jfzzrq" value='' onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" onchange="jfrqchange()"/>
							</td>
							<td align="right" width="80px">起码</td>
							<td width="80px">
								<input type="text" name="t_qm" value="<%=b %>" class="selected_font"/>
							</td>
							<td align="right" width="80px">止码</td>
							<td width="80px">
								<input type="text" name="t_zm" value="" class="selected_font" onchange="jisdl()"/>
							</td>
						</tr>	
						<tr>
							<td align="right" width="80px">电量</td>
							<td width="80px">
								<input type="text" name="t_dliang" value="" class="selected_font"/>
							</td>
							<td align="right" width="80px">电费单价（元）</td>
							<td width="80px">
								<input type="text" name="t_dj" value="<%=danjia %>" class="selected_font"/>
							</td>
							<td align="right" width="80px">缴费日期</td>
							<td width="80px">
								<input type="text" name="t_jfrq" value=""/>
							</td>
							<td align="right" width="80px">票据类型</td>
							<td width="25%">
								<select name="pjlx">
         							<option value="0">请选择</option>
         							<%
						         	ArrayList s = new ArrayList();
						         	s = commBean.getPjlx("PJLX");
						         	if(s!=null){
						         		String agcode="",agname="";
						         		int scount = ((Integer)s.get(0)).intValue();
						         		for(int i=scount;i<s.size()-1;i+=scount)
					                    {
					                    	agcode=(String)s.get(i+s.indexOf("CODE"));
					                    	agname=(String)s.get(i+s.indexOf("NAME"));
					                    %>
					                    <option value="<%=agcode%>"><%=agname%></option>
					                    <%}
						         	}
						         %>
         						</select>
							</td>
						</tr>	
						<tr>
							<td align="right" width="80px">预估金额</td>
							<td width="80px">
								<input type="text" name="t_yg" value="" class="selected_font" onchange="ftje()"/>
							</td>
						</tr>
						<tr bgcolor="F9F9F9">
							<td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />运营商分摊信息</td>
						</tr>	
						<tr>
							<td align="right" width="80px">分摊比例（%）</td>
							<td width="80px">
								<input type="text"  name="t_ftbl" value="<%=ftbl%>"/>
							</td>
							<td align="right" width="80px">分摊金额</td>
							<td width="80px">
								<input type="text"  name="t_ftje" value="" class="selected_font" />
							</td>
						</tr>		
						<tr>
							<td align="right" colspan="8" height="60px">
<!-- 								<input name="button2" type="submit" class="btn_c1" id="button2" value="临时保存" />&nbsp;  -->
								<input onclick="saveAccount()" type="button" class="btn_c1" id="button2" value="保存" />&nbsp; 
								<input name="button2" type="reset" class="btn_c1" id="button2" value="重置" />&nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td>
		</tr>
		</table>
		</form>
		<script>
			function jisdl(){
				var qima = parseInt(document.form1.t_qm.value);
				var zhima = parseInt(document.form1.t_zm.value);
				document.form1.t_dliang.value=zhima-qima;
				}
			function jfrqchange(){
				document.form1.t_jfrq.value=document.form1.t_jfzzrq.value;
			}
			function ftje(){
				var a = parseInt(document.form1.t_yg.value);
				var b = parseInt(document.form1.t_ftbl.value);
				document.form1.t_ftje.value=a*b/100;
			}
	function saveAccount(){
		          if(checkNotSelected(document.form1.shi,"城市")&&
						checkNotSelected(document.form1.xian,"区县")&&
						checkNotSelected(document.form1.xiaoqu,"乡镇")&&
						checkNotSelected(document.form1.zdmc,"站点名称")&&
						checkNotSelected(document.form1.dbbm,"电表编码")&&
						checkNotSelected(document.form1.jzcode,"站点编码")
					){	
					addzhandian();
					}
			  }
        	function addzhandian(){
        		if(confirm("您将要添加或修改预付费信息！确认信息正确！")){      		
          		document.form1.action=path+"/servlet/YufufeiServlet?action=addyff";
				document.form1.submit();
				showdiv("请稍等..............");
           		 }
        	}
        	function isNa(val){ 
				return val.match(new RegExp("^\d+\.\d+$")); 
			} 
			
	var path = '<%=path%>';
	var XMLHttpReq;
	function shua(){
				document.form1.action=path+"/web/sdttWeb/DianfeiManger/addYuFuFei.jsp";
				document.form1.submit();
			}
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
        	var r = XMLHttpReq.responseXML.getElementsByTagName("res");
         // updateZdlx(res);
          updateJzcode(r);
          updateZdlx(res);     
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
    
	}
	//站点类型
function updateZdlx(res){
	var shilist = document.all.dbbm;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function updateJzcode(res){
	var shilist = document.all.jzcode;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
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
//	document.form1.action=path+"/web/sdttWeb/jichuInfoManager/addFtbl.jsp";
//	document.form1.submit();
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
   	//站点属性
	function zdsx(){
	var sid = document.form1.jzproperty.value;
	if(sid=="0"){
		var shilist = document.all.stationtype;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=zdsx&pid="+sid,"jzproperty");
	}
	
}

   function xsl(){
  // 02xsbl线损比例  01xstz 线损调整 
   		var xsll=document.form1.linelosstype.value;
   		var bl=document.getElementById("xs");
   		var blnr="线损值（%）：";
   		var tz="线损值（度）：";
   		if(xsll=="02xsbl"){
   		 bl.innerHTML=blnr;
   		}else if(xsll=="01xstz"){
   		bl.innerHTML=tz;
   		}
   }


function kzinfo(){
	
}

		function showxuni(){

			if(document.form1.xuni.checked){
				document.getElementById("IDXUNI").style.display="block";
			}else{
				document.getElementById("IDXUNI").style.display="none";
			}
		}
		function liulan(){
		//window.open("zhandianselect.jsp");
		//return;
		var url=path+"/web/jizhan/zhandianselect.jsp";
		//window.open(url);
		//return;
			var obj = new Object();
			obj.mid='mid';
		    var obj=showModalDialog(url,obj,'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no');	
			document.form1.czzdid.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.czzd.value=obj.substring(0,obj.indexOf(","));

	}
	document.form1.shi.value='<%=sh%>';
	document.form1.xian.value='<%=xx%>';
	document.form1.xiaoqu.value='<%=xiaoqu%>';
	document.form1.zdmc.value='<%=zdid%>';
	document.form1.jzcode.value='<%=zdbm%>';
	document.form1.dbbm.value='<%=dbcode%>';
</script>
</body>
</html>
