<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.noki.TaiZhang.TaiZhangManager,java.text.DecimalFormat" %>
<%@page import="java.util.ArrayList,com.noki.mobi.common.CommonBean,com.noki.mobi.common.Account" %>
<head>
<title>无标题文档</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<script>
	<%
    String path = request.getContextPath();    
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
   		CommonBean comm =new CommonBean();
   		shilist = comm.getAgcode(sheng,account.getAccountId());
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
	String keyword = request.getParameter("yearmonth")!=null?request.getParameter("yearmonth"):"";
    
    String rgsh=request.getParameter("rgsh");//站点审核通过   首页传的值
    String rgsh2=request.getParameter("caiji");// 采集站点
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
    int count=0,pagesize=10,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
   	String permissionRights="";         
%>
</script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<form action="" name="form1" method="post">
<table width="1060" border="0" cellspacing="0" cellpadding="0">
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
		if (!xiaoqu.equals("0")) {
			whereStr=whereStr+" and zd.xiaoqu='" + xiaoqu + "'";
			str=str+" and zd.xiaoqu='" + xiaoqu + "'";
		    } else if (!xian.equals("0")) {
			whereStr=whereStr+" and z.xian='" + xian + "'";
			str=str+" and zd.xian='" + xian + "'";
		    } else if (!shi.equals("0")) {
			whereStr=whereStr+" and zd.shi='" + shi + "'";
			str=str+" and zd.shi='" + shi + "'";
		    } else if (!sheng.equals("0")) {
			whereStr=whereStr+" and zd.sheng='" + sheng + "'";
			str=str+" and zd.sheng='" + sheng + "'";
		    }
		if (keyword != "0"&&!keyword.equals("0")&&keyword!=""&&!keyword.equals("")) {
			whereStr=whereStr+" and ef.accountmonth =to_date('"+keyword+"','yyyy-mm')";
			str=str+" and ef.accountmonth ='"+keyword+"'";
		}

   %>
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">区域电费信息校验表</div>
				<div class="content01_1">
					<table width="800px" border="0" cellpadding="1" cellspacing="0" class="tb_select" >
						<tr>
						<td align="right" width="60px">城市：</td>
							<td align="left" width="60px">
							<select name="shi" id="shi" style="width:80px;" onchange="changeCity()" >
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
								<select name="xian" id="xian" style="width:80px;" onchange="changeCountry()" class="selected_font">
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
								<select name="xiaoqu" id="xiaoqu" style="width:120px;" class="selected_font">
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
							<td align="right" width="60px">年月：</td>
							<td><input type="text" id="yearmonth" name="yearmonth" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" style="width:80px;"/></td>
						</tr>
						<tr>
							<td align="right" colspan="8">
								<input name="button2" type="button" onclick="chaxun()" class="btn_c1" id="button2" value="查询" />&nbsp; 
							</td>
						</tr>
					</table>
					<div class="tbtitle01"><b>区域电费信息校验表一览</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
									<tr align="right">
									     <td colspan="13" align="right" valign="middle">
										<input name="button2" type="button" onclick="daochu()" class="btn_c1" id="button2" value="Excel导出" />&nbsp; 
									     </td>
									</tr>
						</table>
						<div style="overflow:scroll; width:1060px; height:260px;">
						<table width="1400px"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
						<tr>
							<td colspan="8" height="23" align="center" style="background-color: #c2d69a;"><div align="center" class="bttcn">站点基础信息</div></td>
							<td colspan="8" height="23" align="center" style="background-color: #c2d69a;"><div align="center" class="bttcn">电量电费信息</div></td>
							<td colspan="3" height="23" align="center" style="background-color: #e6c3ad;"><div align="center" class="bttcn">移动</div></td>
							<td colspan="3" height="23" align="center" style="background-color: #e6c3ad;"><div align="center" class="bttcn">联通</div></td>
							<td colspan="3" height="23" align="center" style="background-color: #e6c3ad;"><div align="center" class="bttcn">电信</div></td>
						</tr>
									<tr align="center">
									    <th width="10">序号</th>
									    <th>地市</th>
										<th>区县</th>
										<th>乡镇</th>
										<th>站点名称</th>
										<th>站点编码</th>
										<th>供电方式</th>
										<th>结算方式</th>
										<th>起码</th>
										<th>止码</th>
										<th>电量（度）</th>
										<th>电费单价（元/度）</th>
										<th>缴费金额</th>
										<th>缴费日期</th>
										<th>缴费票据类型</th>
										<th>供电方/业主名称</th>
										<th>分摊比例（%）</th>
										<th>税负因子（%）</th>
										<th>分摊金额</th>
										<th>分摊比例（%）</th>
										<th>税负因子（%）</th>
										<th>分摊金额</th>
										<th>分摊比例（%）</th>
										<th>税负因子（%）</th>
										<th>分摊金额</th>
									</tr>
										<%
										TaiZhangManager manager = new TaiZhangManager();
										ArrayList fylist = new ArrayList();
										fylist=manager.getPageData(curpage,loginName,loginId,whereStr);
										allpage=manager.getAllPage();
										DecimalFormat df1 = new DecimalFormat("0.00");
										DecimalFormat df2 = new DecimalFormat("0.0000");
										int intnum = pagesize*(curpage-1)+1;
										double a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0;
										String chengshi="",quxian="",xiangzhen="",zdmc="",zdbm="",gdfs="",jsfs="",qm="",zm="",dl="",dj="",jfje="",jfrq="",pjlx="";
										String gdfname="",ydftbl="",sfyz="",ydftje="",ltftbl="",ltftje="",dxftbl="",dxftje="";
										if(fylist!=null){
										int fycount = ((Integer)fylist.get(0)).intValue();
										for( int k = fycount;k<fylist.size()-1;k+=fycount){
											chengshi=(String) fylist.get(k + fylist.indexOf("SHI"));
											quxian=(String) fylist.get(k + fylist.indexOf("XIAN"));
											xiangzhen=(String) fylist.get(k + fylist.indexOf("XIAOQU"));
											zdmc=(String) fylist.get(k + fylist.indexOf("JZNAME"));
											zdbm=(String) fylist.get(k + fylist.indexOf("JZCODE"));
											gdfs=(String) fylist.get(k + fylist.indexOf("GDFS"));
											jsfs=(String) fylist.get(k + fylist.indexOf("JSFS"));
											qm=(String) fylist.get(k + fylist.indexOf("LASTDEGREE"));
											zm=(String) fylist.get(k + fylist.indexOf("THISDEGREE"));
											dl=(String) fylist.get(k + fylist.indexOf("BLHDL"));
											dj=(String) fylist.get(k + fylist.indexOf("UNITPRICE"));
											jfje=(String) fylist.get(k + fylist.indexOf("ACTUALPAY"));
											jfrq=(String) fylist.get(k + fylist.indexOf("THISDATE"));
											pjlx=(String) fylist.get(k + fylist.indexOf("PJLX"));
											gdfname=(String) fylist.get(k + fylist.indexOf("GDFNAME"));
											ydftbl=(String) fylist.get(k + fylist.indexOf("FTBL_Y"));
											ydftje=(String) fylist.get(k + fylist.indexOf("ACTUALPAY_Y"));
											ltftbl=(String) fylist.get(k + fylist.indexOf("FTBL_L"));
											ltftje=(String) fylist.get(k + fylist.indexOf("ACTUALPAY_L"));
											dxftbl=(String) fylist.get(k + fylist.indexOf("FTBL_D"));
											dxftje=(String) fylist.get(k + fylist.indexOf("ACTUALPAY_D"));
											sfyz=(String) fylist.get(k + fylist.indexOf("SFYZ"));
											a=Double.valueOf(jfje);
											b=Double.valueOf(ydftje);
											c=Double.valueOf(ltftje);
											d=Double.valueOf(dxftje);
											e=Double.valueOf(dj);
											f=Double.valueOf(sfyz);
											System.out.println(sfyz);
										 %>
									<tr>
										<td width="10"><%=intnum++ %></td>
										<td align="left"><%=chengshi %></td>
										<td align="left"><%=quxian %></td>
										<td align="right"><%=xiangzhen %></td>
										<td align="right"><%=zdmc %></td>
										<td align="right"><%=zdbm %></td>
										<td align="right"><%=gdfs %></td>
										<td align="right"><%=jsfs %></td>
										<td align="right"><%=qm %></td>
										<td align="right"><%=zm %></td>
										<td align="right"><%=dl %></td>
										<td align="right"><%=df2.format(e) %></td>
										<td align="right"><%=df1.format(a) %></td>
										<td align="left"><%=jfrq %></td>
										<td align="left"><%=pjlx %></td>
										<td align="right"><%=gdfname %></td>
										<td align="right"><%=ydftbl %></td>
										<td align="right"><%=df2.format(f) %></td>
										<td align="right"><%=df1.format(b) %></td>
										<td align="right"><%=ltftbl %></td>
										<td align="right"><%=df2.format(f) %></td>
										<td align="right"><%=df1.format(c) %></td>
										<td align="right"><%=dxftbl %></td>
										<td align="right"><%=df2.format(f) %></td>
										<td align="right"><%=df1.format(d) %></td>
									</tr><%} %>
									<tr bgcolor="#ffffff">
					<td colspan="25" align="left" >
						<div align="left">
<!-- 							<font color='#1E90FF'>&nbsp;页次:</font> -->
<!-- 							&nbsp;&nbsp; -->
<!-- 							 <b><font color=red><%=curpage%></font></b> -->

<!-- 							 <font color='#1E90FF'>/<b><%=allpage%></b>&nbsp;</font> -->
							 &nbsp;&nbsp;
							 <font color='#1E90FF'>
						     <%
						     	if (curpage != 1) {
						     			out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
						     		}
						     %>
					     </font>
					     &nbsp;&nbsp;
							 <font color='#1E90FF'>
						     <%
						     	if (curpage != 1) {
						     			out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
						     		}
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
						     <%
						     	if (allpage != 0 && (curpage < allpage)) {
						     			out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");
						     		}
						     %>
             				</font>
							&nbsp;&nbsp;							
							<font color='#1E90FF'>
					     <%
					     	if (allpage != 0 && (curpage < allpage)) {
					     			out.print("<a href='javascript:gopagebyno(" + allpage+ ")'><img src='../images/page-last.gif'></a>");
					     		} else {
					     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
					     		}
					     %>
            </font>
            &nbsp;&nbsp;
<!-- 						<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" style="width:70px;font-family: 宋体;font-size:12px;line-height:120%;" > -->
<!-- 					     < -->
<!-- 					     	for (int i = 1; i <= allpage; i++) { -->
<!-- 					     			if (curpage == i) { -->
<!-- 					     				out.print("<option value='" + i -->
<!-- 					     						+ "' selected='selected'>" + i + "</option>"); -->
<!-- 					     			} else { -->
<!-- 					     				out.print("<option value='" + i + "'>" + i -->
<!-- 					     						+ "</option>"); -->
<!-- 					     			} -->
<!-- 					     		} -->
<!-- 					     %> -->
<!-- 				    </select> -->


						</div>
					</td>
				</tr>
									<%  }%>
								</table>
								</div>
						<div class="space_h_10"></div>
				</div>
			</div>
		</td>
		</tr>
		</table>
		</form>
		<script>
			var path = '<%=path%>';
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
      		document.form1.action=path+"/web/sdttWeb/jichuInfoManager/sitemanage.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      		document.form1.action=path+"/web/sdttWeb/jichuInfoManager/sitemanage.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = pageno;   
      document.form1.action=path+"/web/sdttWeb/jichuInfoManager/sitemanage.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+pageno;
      document.form1.submit();
     }
     function daochu(){
     	document.form1.action=path+"/servlet/TaiZhangDownload?bz=quyu";
     	document.form1.submit();
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
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选择","0"));	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function chaxun(){
	document.form1.action=path+"/web/sdttWeb/TaizhangManager/DianfeiQuerenTable.jsp";
	document.form1.submit();
}
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
document.form1.yearmonth.value='<%=keyword%>';
		</script>
</body>
</html>
