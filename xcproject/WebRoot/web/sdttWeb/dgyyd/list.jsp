<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.DianBiaoBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script>
	<% 	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
	String loginId=account.getAccountId();
	String bgsign=request.getParameter("bgsign")!=null?request.getParameter("bgsign"):"-1";//获取当前页 是否标杆 
    String dfzflx1=request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";//获取当前页面 电费支付类型 dfzflx的值
    String dbyt=request.getParameter("dbyt")!=null?request.getParameter("dbyt"):"0";//获取当前页 电表的 用途 dbyt
     
    String jzproperty=request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";//获取站点属性 
    String sptype = request.getParameter("sptype")!=null?request.getParameter("sptype"):"0";//
    String dbqyzt = request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"-1";//电表启用状态  
    
    String zdqyzt= request.getParameter("zdqyzt")!=null?request.getParameter("zdqyzt"):"1";//站点启用状态
    
	String sheng = (String)session.getAttribute("accountSheng"); 
	//String sheng = "137";
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
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
    String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
       //System.out.println("logManage.jsp>>"+beginTime);
	String sdbid = request.getParameter("sdbid")!=null?request.getParameter("sdbid"):"";
  	String zdmc=request.getParameter("zdmc")!=null?request.getParameter("zdmc"):"";
  	String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
  	String dbyt01 = request.getParameter("dbyt01");//结算电表
   	String dbyt03 = request.getParameter("dbyt03");//管理电表
   	System.out.println("结算"+dbyt01+"管理"+dbyt03);
    if(dbyt01!=null){
	   	dbyt=dbyt01;
	}
	if(dbyt03!=null){
	   	dbyt=dbyt03;
	}
           String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
		   int count=0,pagesize=10,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
		   String color=null;
           curpage=Integer.parseInt(s_curpage);
           String loginName = (String)session.getAttribute("loginName");
		   String roleId = (String)session.getAttribute("accountRole");
		   System.out.println("@@@@@@@@@@@@@@电表"+roleId);
           String permissionRights="";
%>
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<jsp:useBean id="bean" scope="page" class="com.noki.dgyydgl.javabean.DgyydBean">   
</jsp:useBean>
<body>
	<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">大工业用电管理</div>
				<div class="content01_1">
					<table width="800px" border="0" cellpadding="1" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="60px">城市：</td>
							<td align="left" width="60px">
							<select name="shi" id="shi" style="box-sizing: border-box; width: 130px;" onchange="changeCity()" >
	         					<option value="0">请选择</option>
				         		<%
				         			ArrayList shilist = new ArrayList();
				         			shilist = commBean.getAgcode(sheng, shi, loginName);
				         			if (shilist != null) {
				         				String agcode = "", agname = "";
				         				int scount = ((Integer) shilist.get(0)).intValue();
				         				for (int i = scount; i < shilist.size() - 1; i += scount) {
				         			 	agcode = (String) shilist
				         							.get(i + shilist.indexOf("AGCODE"));
				         					agname = (String) shilist
				         							.get(i + shilist.indexOf("AGNAME"));
				         		%>
				                    <option value="<%=agcode%>"><%=agname%></option>
				                    <%
				                    	}
				                    }
				                    %>
				         		</select>
				         		</td>
				         		<td align="right" width="60px">区县：</td>
								<td align="left" width="60px">
								<select name="xian" id="xian" style="box-sizing: border-box; width: 130px;" onchange="changeCountry()" class="selected_font">
	         					<option value="0">请选择</option>
				         		<%
				         			ArrayList xianlist = new ArrayList();
				         		xianlist = commBean.getAgcode(shi, xian, loginName);
				         			if (xianlist != null) {
				         				String agcode = "", agname = "";
				         				int scount = ((Integer) xianlist.get(0)).intValue();
				         				for (int i = scount; i < xianlist.size() - 1; i += scount) {
				         					agcode = (String) xianlist
				         							.get(i + xianlist.indexOf("AGCODE"));
				         					agname = (String) xianlist
				         							.get(i + xianlist.indexOf("AGNAME"));
				         		%>
				                    <option value="<%=agcode%>"><%=agname%></option>
				                    <%
				                    	}
				                    	}
				                    %>
				         		</select>
				         	</td>
				         	<td align="right" width="60px">乡镇：</td>
							<td align="left" width="60px">
								<select name="xiaoqu" id="xiaoqu" style="box-sizing: border-box; width: 130px;" class="selected_font">
			         		<option value="0">请选择</option>             
			         		<%
			         			ArrayList xiaoqulist = new ArrayList();   //下拉列表
			         			xiaoqulist = commBean.getAgcode(xian, xian, loginName);
			         			if (xiaoqulist != null) {
			         				String agcode = "", agname = "";
			         				int scount = ((Integer) xiaoqulist.get(0)).intValue();
			         				for (int i = scount; i < xiaoqulist.size() - 1; i += scount) {
			         					agcode = (String) xiaoqulist.get(i
			         							+ xiaoqulist.indexOf("AGCODE"));
			         					agname = (String) xiaoqulist.get(i
			         							+ xiaoqulist.indexOf("AGNAME"));
			         		%>
			                    <option value="<%=agcode%>"><%=agname%></option>
			                    <%
			                    	}
			                    	}
			                    %>
			         			</select>
							</td>
							<!--  <td align="right" width="60px">关键字：</td>
							<td align="left" width="60px">
							<input type="text" name="txtKeyword" id="txtKeyword" value="" style="width:80px" />
							</td>
						</tr>
						<tr>
							<td align="left" colspan="8">
								※关键字包括：基站姓名、基站编号
							</td>-->
						</tr>
						<tr>
							<td align="right" colspan="8">
								<input onclick="query()" type="submit" class="btn_c1"   value="查询" /> 
							</td>
						</tr>
					</table>
					
					<div class="tbtitle01"><b>大工业用电查询统计一览</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
									<tr align="right">
										<td colspan="12" align="right">
										    <input name="button2" type="submit" onclick="addjz()" class="btn_c1" id="button2" value="新增" />&nbsp;&nbsp;
											<input name="button2" type="submit" onclick="daochu()" class="btn_c1" id="button2" value="导出Excel" />
										</td>
									</tr>
									<tr align="center">
									    <th width="10">序号</th>
										<th width="120">变压器容量（KVA）</th>
										<th width="100">功率因素</th>
										<th width="80">一般工商及其他电价（元）</th>
										<th width="80">大工业峰谷平综合电价（元）</th>
										<th width="80">价差</th>
										<th width="80">最大需量40%基本电价（元）</th>
										<th width="80">平衡点电量（KWH）</th>
										<th width="80">平衡点电量折算容量</th>
										<th width="80">占变压器比</th>
										<th>操作</th>
									</tr>
      <%
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageData(curpage,shi,xian,xiaoqu);
       	 allpage=bean.getAllPage();
		 String id="";
		 String byqrl = "",glys ="",dj = "";
		 String zhdj= "",jc="",zddj="",phddl="",rl="",zb="";
		 int intnum=xh = pagesize*(curpage-1)+1;
		 Double bl=0.00;
		 //System.out.println(dj+"==="+byqrl+"=="+glys);
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     	//num为序号，不同页中序号是连续的
		     	id = (String)fylist.get(k+fylist.indexOf("ID"));
		     	byqrl =(String)fylist.get(k+fylist.indexOf("BYQRL"));
		     	glys = (String)fylist.get(k+fylist.indexOf("GLYS"));
		     	dj=(String)fylist.get(k+fylist.indexOf("DJ"));
		     	zhdj=(String)fylist.get(k+fylist.indexOf("ZHDJ"));
		     	jc=(String)fylist.get(k+fylist.indexOf("JC"));
		     	zddj=(String)fylist.get(k+fylist.indexOf("ZDDJ"));
		     	phddl=(String)fylist.get(k+fylist.indexOf("PHDDL"));
		     	rl=(String)fylist.get(k+fylist.indexOf("RL"));
		     	zb=(String)fylist.get(k+fylist.indexOf("ZB"));
				if(intnum%2==0){
				    color="#DDDDDD";
	
				}else{
				    color="#FFFFFF" ;
				}

       %>
									<!-- 数据加载  Start-->
									<tr align="center">
										<td width="10"><%=intnum++%></td>
										<td align="left"><%=byqrl%></td>
										<td align="left"><%=glys%></td>
										<td align="center"><%=dj%></td>
										<td align="center"><%=zhdj%></td>
										<td align="center"><%=jc%></td>
										<td align="center"><%=zddj%></td>
										<td align="center"><%=phddl%></td>
										<td align="center"><%=rl%></td>
										<td align="center"><%=zb%>%</td>
										<td width="80">
											<a href="#" onclick="xiangxi('<%=id%>')"><img src="../images/lefticon3.png" width="16" height="16"  title="详细" /></a>
											<a href="#" onclick="update('<%=id%>')"><img src="../images/accept.png" width="16" height="16"  title="修改" /></a>
											<a href="#" onclick="delzd('<%=id%>')"><img src="../images/delete.png" width="16" height="16"  title="删除" /></a>
										</td>
									</tr>
 									<% } %>
									<!-- 数据加载 End -->
									
									<tr bgcolor="#ffffff"  >
					<td colspan="16" >
						<div align="left">
<!-- 							<font color='#1E90FF'>&nbsp;页次:</font> -->
<!-- 							&nbsp;&nbsp; -->
<!-- 							 <b><font color=red><%=curpage%></font></b> -->

<!-- 							 <font color='#1E90FF'>/<b><%=allpage%></b>&nbsp;</font> -->
							 &nbsp;&nbsp;
							 <font color='#1E90FF'>
						     <% if (curpage!=1)
						       {out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");}
						      else
						      {out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");}
						      %>
					     </font>
					     &nbsp;&nbsp;
							 <font color='#1E90FF'>
						     <%if(curpage!=1)
						          {out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");}
						         else
						       {out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");}
						      %>
						   </font>
						   &nbsp;&nbsp;
						    转到 <select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" style="width:40px;font-family: 宋体;font-size:12px;line-height:120%;" >
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
				    </select>&nbsp;&nbsp;共 <font color='#1E90FF'><b><%=allpage%></b>&nbsp;</font>页 &nbsp;&nbsp;
						   
							<font color='#1E90FF'>
						     <% if(allpage!=0&&(curpage<allpage))
						         {out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");}
						         else
						        {out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");}
						     %>
             </font>
							&nbsp;&nbsp;
							<font color='#1E90FF'>
					     <% if(allpage!=0&&(curpage<allpage))
					         {out.print("<a href='javascript:gopagebyno("+allpage+")'><img src='../images/page-last.gif'></a>");}
					        else
					        {out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");}
					     %>
            </font>
            &nbsp;&nbsp;
<!-- 						<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" style="width:70px;font-family: 宋体;font-size:12px;line-height:120%;"> -->
<!-- 					     <for(int i=1;i<=allpage;i++) -->
<!-- 					         {if(curpage==i){out.print("<option value='"+i+"' selected='selected'>"+i+"</option>");} -->
<!-- 					      else{out.print("<option value='"+i+"'>"+i+"</option>");} -->
<!-- 					         } -->
<!-- 					     %> -->
<!-- 				    </select> -->
						</div>
					</td>
				</tr>
								<%}%>
								</table>
						<div class="space_h_10"></div>
				</div>
			</div>
		</td>
		</tr>
		<!-- else {%>-->
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
	var path = '<%=path%>';
	function gopage()
     {
      document.form1.submit();
     }
     function previouspage()
     {
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage-1)%>';
      		document.form1.action=path+"/web/sdttWeb/jizan/dianbiaolist.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/sdttWeb/jizan/dianbiaolist.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/sdttWeb/jizan/dianbiaolist.jsp?curpage="+pageno;
      document.form1.submit();
     }
     function delzd(id){		
		if(confirm("您确认要删除本条电表信息？")){
		     		document.form1.action=path+"/servlet/dgyyd?action=del&id="+id;
		      		document.form1.submit();
		      		showdiv("请稍等..............");
		      	}
		      	}

function daochu(){
    document.form1.action=path+"/servlet/CbDownload?bz=DGYYD";
	document.form1.submit();
}
    function xiangxi(id){
     	  window.open("xxquery.jsp?id="+id, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
     	
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
				}catch (e){}
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
            };
        };
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
	//alert(sid);
	if(sid=="0"){
	//alert(sid);
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
function addjz(){
	window.open("add.jsp", "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
}
function update(id,zdid){
     window.open("add.jsp?bz=1&id="+id, "newwindow", "height=600, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
     document.form1.submit();
    }
function query(){
	document.form1.action=path+"/web/sdttWeb/dgyyd/list.jsp";
}
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';

</script>


