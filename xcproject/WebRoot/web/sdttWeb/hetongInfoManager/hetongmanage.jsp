<%@ page import="com.noki.hetongguanli.Dao.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.noki.hetongguanli.Dao.*"%>
<%@ page import="com.noki.hetongguanli.Model.HetongModel"%>
<%@ page import="com.noki.hetongguanli.Dao.ShenPiDao"%>
<%@ page import="com.noki.hetongguanli.javabean.ShenPiClass"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>合同管理</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script>
	<%String path = request.getContextPath();    
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
	String roleId = (String)session.getAttribute("accountRole");    
	System.out.println("################站点"+roleId);
	String jztype1 = request.getParameter("jztype")!=null?request.getParameter("jztype"):"0";
    String jzproperty1 = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
    String bgsign = request.getParameter("bgsign")!=null?request.getParameter("bgsign"):"-1";
    String qyzt = request.getParameter("qyzt")!=null?request.getParameter("qyzt"):"-1";//站点启用状态

    String caijid = request.getParameter("caijidian")!=null?request.getParameter("caijidian"):"-1";
    String sitetype = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
    String color = null;
	String sheng = (String)session.getAttribute("accountSheng");
   	String agcode1="";
   	if(request.getParameter("shi")==null){
   		ArrayList shilist = new ArrayList();
   		CommonBean commBean =new CommonBean();
   		HetongDao nullBean =new HetongDao();
   		shilist = commBean.getAgcode(sheng,account.getAccountId());
		if(shilist!=null){
      		int scount = ((Integer)shilist.get(0)).intValue();
           agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
         }
   	} 	
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;    
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
    String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
	String sname = request.getParameter("sname")!=null?request.getParameter("sname"):"";
	String szdcode = request.getParameter("szdcode")!=null?request.getParameter("szdcode"):"";
	String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"";
    
    String rgsh=request.getParameter("rgsh");//站点审核通过   首页传的值 不用改直接用
    String rgsh2=request.getParameter("caiji");// 采集站点
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
    int count=0,pagesize=5,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
   	String permissionRights="";%>
</script>
<%
	//获取DAO中TBL_HETONG_SHENPI表的值
	ShenPiDao dao = new ShenPiDao();
	ShenPiClass sp = new ShenPiClass();
	ArrayList<ShenPiClass> al =  null;
	al = dao.Select();
	//获取文本框的值
	String partya = request.getParameter("partya");	
	String partyb = request.getParameter("partyb");
	String contractname = request.getParameter("contractname");	
	String keywordd = request.getParameter("keywordd"); 
	String zhuangtai = "";
	//判断下拉列表的值并进行查询方法的选择 
	String yeshu = request.getParameter("curpage"); //获取分页查询页数 
	System.out.println(allpage);
	if(yeshu != null && !yeshu.equals("") && !yeshu.equals("0")){//不为空则重新赋值

		curpage = Integer.valueOf(yeshu).intValue();//强转int

		}
	allpage  = dao.SelectCount(partya, partyb, contractname, zhuangtai, keywordd);
	al = dao.SelectHeTong(curpage,partya, partyb, contractname,zhuangtai,keywordd);
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
 
</head>
<jsp:useBean id="javcbean" scope="page"
	class="com.noki.hetongguanli.hetong.hetongBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page"
	class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
	<form action="" name="form1" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<%
				String whereStr="";
									  String str="";
									  String xuni="0";
									         /*  if(sheng != null && !sheng.equals("")&& !sheng.equals("0")){
											whereStr=whereStr+" and JZTYPE='"+sheng+"'";
											str=str+" and JZTYPE='"+sheng+"'";
										}
										if(sheng != null && !sheng.equals("")&& !sheng.equals("0")){
											 whereStr=whereStr+" and JZ.JZNAME like '%"+sheng+"%'";
											
										}*/
											if (xuni.equals("3")) {
										whereStr=whereStr+" and shsign!='1'";
											}
											if (!xiaoqu.equals("0")) {
										whereStr=whereStr+" and z.xiaoqu='" + xiaoqu + "'";
										str=str+" and z.xiaoqu='" + xiaoqu + "'";
											    } else if (!xian.equals("0")) {
										whereStr=whereStr+" and z.xian='" + xian + "'";
										str=str+" and z.xian='" + xian + "'";
											    } else if (!shi.equals("0")) {
										whereStr=whereStr+" and z.shi='" + shi + "'";
										str=str+" and z.shi='" + shi + "'";
											    } else if (!sheng.equals("0")) {
										whereStr=whereStr+" and z.sheng='" + sheng + "'";
										str=str+" and z.sheng='" + sheng + "'";
											    }
											if (keyword.length() > 0 && keyword != null) {
										whereStr=whereStr+" and z.jzname like '%" + keyword + "%'";
										str=str+" and z.jzname like '%" + keyword + "%'";
											}
											if (szdcode.length() > 0 && szdcode != null) {
										whereStr=whereStr+" and z.id='" + szdcode + "'";
										str=str+" and z.id='" + szdcode + "'";
											}
										
											if (!jztype1.equals("0")) {
										whereStr=whereStr+" and z.jztype='" + jztype1 + "'";
										str=str+" and z.jztype='" + jztype1 + "'";
										}
											if (!jzproperty1.equals("0")) {
										whereStr=whereStr+" and z.property='" + jzproperty1 + "'";
										str=str+" and z.property='" + jzproperty1 + "'";
										}
											if (!bgsign.equals("-1")) {
										whereStr=whereStr+" and z.bgsign='" + bgsign + "'";
										str=str+" and z.bgsign='" + bgsign + "'";
										}
											if (!qyzt.equals("-1")) {//站点启用状态
										whereStr=whereStr+" and z.qyzt='" + qyzt + "'";
										str=str+" and z.qyzt='" + qyzt + "'";
										}
											
											if (rgsh!=null&&!rgsh.equals("null")) {//首页传的值 审核通过的
										whereStr=whereStr+" and z.SHSIGN='" + rgsh + "'";
										str=str+" and z.SHSIGN='" + rgsh + "'";
										}
											System.out.println("采集"+rgsh2);
											if (rgsh2!=null&&!rgsh2.equals("null")) {//首页传的值 采集站点
										whereStr=whereStr+" and z.caiji='" + rgsh2 + "'";
										str=str+" and z.caiji='" + rgsh2 + "'";
										}
										
									       
									  		if(caijid != null && !caijid.equals("")&&!caijid.equals("-1")){
											whereStr=whereStr+" and CAIJI='"+caijid+"'";
											str=str+" and CAIJI='"+caijid+"'";
										}
											if(sitetype != null && !sitetype.equals("")&&!sitetype.equals("0")){
											whereStr=whereStr+" and STATIONTYPE='"+sitetype+"'";
											str=str+" and STATIONTYPE='"+sitetype+"'";
										}
										//进行查询
										HetongDao bean = new HetongDao();
								String hetongpan =request.getParameter("hetong");
								String agcodepan =request.getParameter("agcode");
								String PARTYBpan =request.getParameter("PARTYB");
							SiteManage bean1 = new SiteManage();
							ArrayList<HetongModel> fylist=null;
					
						
						if(hetongpan != null && !hetongpan.equals("") && !hetongpan.equals("0")){
							 fylist = bean.Selectoff(hetongpan);
						}else
						if(agcodepan != null && !agcodepan.equals("") && !agcodepan.equals("0")){
						fylist = bean.Selectoff1(agcodepan);
						}else
						if(PARTYBpan != null && !PARTYBpan.equals("") && !PARTYBpan.equals("0")){
						fylist = bean.Selectoff2(PARTYBpan);
						}else{
					fylist = bean.Select();
						}
			%>
			<tr valign="top">
				<td width="12"><img src="../images/space.gif" width="12"
					height="17" /></td>
				<td>
					<div class="content01">
						<div class="tit01">合同信息</div>
						<div class="content01_1">
							<table width="800px" border="0" cellpadding="1" cellspacing="0"
								class="tb_select">
								<tr>
									<td align="right" width="60px">甲方：</td>
									<td align="left" width="60px"><input type="text"
										name="partya" id="partya" value="" style="width:130px" /></td>
									<td align="right" width="60px">乙方：</td>
									<td align="left" width="60px"><input type="text"
										name="partyb" id="partyb" value="" style="width:130px" /></td>
									<td align="right" width="60px">合同名称：</td>
									<td align="left" width="60px"><input type="text"
										name="contractname" id="contractname" value="" style="width:130px" /></td>
									<td align="right" width="60px">关键字：</td>
									<td align="left" width="60px"><input type="text"
										name="keywordd" id="keywordd" value="" style="width:130px" />
									</td>
									<td align="left">※关键字:甲方/乙方/合同名称</td>
								</tr>
								<tr>
									<td align="right" colspan="8"><input type="submit"
										class="btn_c1" onclick="query()" value="查询" />
									</td>
								</tr>
							</table>

							<div class="tbtitle01">
								<b>合同信息一览</b>
							</div>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="tb_list1">
								<tr align="right">
									<td colspan="8" align="right"><input name="button2"
										type="button" onclick="addjz()" class="btn_c1" value="新增" />&nbsp;&nbsp;
										<input name="button2" type="submit" onclick="daochu()"
										class="btn_c1" id="button2" value="导出Excel" />
									</td>
								</tr>
								<tr align="center">
									<th width="10">序号</th>
									<th width="100">开始时间</th>
									<th width="120">结束时间</th>
									<th width="100">甲方</th>
									<th width="80">乙方</th>
									<th width="80">合同名称</th>
									<th width="80">项目金额</th>
									<th width="80">操作</th>
								</tr>
								<%
														int intnum = pagesize * (curpage - 1) + 1;
														if (fylist != null) {

														for (int k = 0; k < al.size(); k++) {
														int xuhao = k+1;	
														String st = al.get(k).getStarttime();
														String et = al.get(k).getEndtime();
														String pa = al.get(k).getPartya();
														String pb = al.get(k).getPartyb();
														String cn = al.get(k).getContractname();
														String je = al.get(k).getProjectamonnt();
														String cl = al.get(k).getContractdetail();
														String zts= al.get(k).getZhuangtai();

														int id = fylist.get(k).getID();
														System.out.println(id);
														session.setAttribute("idears", id);
								%>

								<!-- 数据加载  Start-->
								<tr align="center">
									<td width="10"><%=xuhao%></td>
									<td align="left"><%=st%></td>
									<td align="right"><%=et%></td>
									<td align="right"><%=pa%></td>
									<td align="center"><%=pb%></td>
									<td align="center"><%=cn%></td>
									<td align="right"><%=je%></td>
									<td width="80"><a href="#" onclick="xiangxi('<%=id%>')"><img
											src="../images/lefticon3.png" width="16" height="16"
											title="详细" /> </a>
									</a> 
									<%	if(zts.equals("0")){
									//修改未审核
								%>
									 <a href="#" onclick="update('<%=id%>')"><img src="../images/accept.png" width="16" height="16" title="修改" />
								<%	}else if(zts.equals("1")||zts.equals("-1")){
									//审核通过
								%>
									 <a href="#" onclick="over()"><img src="../images/accept.png" width="16" height="16" title="修改" />
								<%}%>
								
								
								<%	if(zts.equals("0")){
									//删除未审核
								%>
									<a href="#" onclick="delzd('<%=id%>')"><img src="../images/delete.png" width="16" height="16" title="删除" />
								<%	}else if(zts.equals("1")||zts.equals("-1")){
									//审核通过
								%>
									<a href="#" onclick="over1()"><img src="../images/delete.png" width="16" height="16" title="删除" />
								<%}%>
									</td>
								</tr>
								<%
									}
								%>

								<!-- 数据加载 End -->

								<tr bgcolor="#ffffff">
									<td colspan="10" align="left">
										<div align="left">

											&nbsp;&nbsp; <font color='#1E90FF'> <%
 	if (curpage != 1) {
 				     			out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
 				     		} else {
 				     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
 				     		}
 %> </font> &nbsp;&nbsp; <font color='#1E90FF'> <%
 	if (curpage != 1) {
 				     			out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
 				     		} else {
 				     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
 				     		}
 %> </font> &nbsp;&nbsp; 转到 <select name="page"
												onChange="javascript:gopagebyno(document.form1.page.value)"
												style="width:40px;font-family: 宋体;font-size:12px;line-height:120%;">
												<%
													for (int i = 1; i <= allpage; i++) {
															     			if (curpage == i) {
															     				out.print("<option value='" + i
															     						+ "' selected='selected'>" + i + "</option>");
															     			} else {
															     				out.print("<option value='" + i + "'>" + i
															     						+ "</option>");
															     			}
															     		}
												%>
											</select>&nbsp;&nbsp;共 <font color='#1E90FF'><b><%=allpage%></b>&nbsp;</font>页
											&nbsp;&nbsp; <font color='#1E90FF'> <%
 	if (allpage != 0 && (curpage < allpage)) {
 				     			out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");
 				     		} else {
 				     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");
 				     		}
 %> </font> &nbsp;&nbsp; <font color='#1E90FF'> <%
 	if (allpage != 0 && (curpage < allpage)) {
 			     			out.print("<a href='javascript:gopagebyno(" + allpage+ ")'><img src='../images/page-last.gif'></a>");
 			     		} else {
 			     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
 			     		}
 %> </font> &nbsp;&nbsp;
										</div>
									</td>
								</tr>

							</table>
							<div class="space_h_10"></div>
						</div>
					</div>
				</td>
			</tr>
			<%
				}
			%><!-- else {%>-->
			<!--  <tr align="center" >
			<td align="left" colspan="9">
			当前无数据。
			</td>
		</tr>-->

		</table>
	</form>
</body>
</html>
<script>
function over(){
	alert("已审核，不能修改");
}
function over1(){
	alert("已审核，不能删除");
}
	var path = '<%=path%>';
	function gopage()
     {
      document.form1.submit();
     }
     function previouspage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage - 1)%>';
      		document.form1.action=path+"/web/sdttWeb/hetongInfoManager/hetongmanage.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      		document.form1.action=path+"/web/sdttWeb/hetongInfoManager/hetongmanage.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = pageno;   
      document.form1.action=path+"/web/sdttWeb/hetongInfoManager/hetongmanage.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+pageno;
      document.form1.submit();
     }
     function delzd(id){
    // alert(id);
     if(confirm("您确定删除此条信息？")){
     	//<if (permissionRights.indexOf("PERMISSION_DELETE") >= 0) {%> //权限
     		document.form1.action=path+"/Delete?action=deletedianjia&id="+id;
      		document.form1.submit();
      //<} else {%>
      //alert("您没有删除站点的权限");
    //<}%>
    }
    }
    function xiangxi(id){
    	window.open("../hetongInfoManager/tonggaoxiangxi.jsp?id="+id);  
    }
    function dfinfo(id){
    
    	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/dfinfo.jsp?id="+id;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }
    function zlinfo(id){
    	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/zlinfo.jsp?id="+id;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }

    function modifyjz(id,intnum){
    //alert(intnum);
  	<%if (permissionRights.indexOf("PERMISSION_EDIT") >= 0) {%>
     		document.form1.action=path+"/web/jizhan/modifysite.jsp?id="+id+"&nums="+intnum;
      document.form1.submit();
      <%} else {%>
      alert("您没有编辑站点信息的权限");
    <%}%>
       
    }
    function getValue(va,sql){
       var general =document.getElementById("general");
       var htmlsql =document.getElementById("htmlsql");
       general.value=va;
       htmlsql.value = sql;    
    }
    //页面载入方法
    function op(){
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian,ammeters,ammeterdegrees,electricfees&tab=zd,am,ad,ef&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
    }
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
function addjz(id){
	window.open("../hetongInfoManager/addHeTong.jsp?action=0&id="+id);  
}
function daochu(){
	document.form1.action=path+"/servlet/CbDownload?bz=danjia";
	document.form1.submit();
}
function update(id){
	window.open("../hetongInfoManager/addHeTong.jsp?action=1&id="+id);  
}
function query(){
	document.form1.action=path+"/web/sdttWeb/hetongInfoManager/hetongmanage.jsp";
	document.form1.submit();
}

document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value = '<%=xiaoqu%>';
document.form1.txtKeyword.value = '<%=keyword%>';
</script>


