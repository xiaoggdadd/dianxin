 <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>铁塔报账</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />

<script>
	<%   
	//获取PAth
    String path = request.getContextPath();    
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String accountCthrnumber=account.getCthrnumber();
	if(accountCthrnumber==null){
		accountCthrnumber="";
	}
    //获取
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
   	if(request.getParameter("shi")==null&&request.getParameter("shi")==""&&request.getParameter("shi")=="0"){
   		ArrayList shilist = new ArrayList();
   		CommonBean commBean =new CommonBean();
   		shilist = commBean.getAgcode(sheng,account.getAccountId());
		if(shilist!=null){
      		int scount = ((Integer)shilist.get(0)).intValue();
           agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
         }
   	} 	
	String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"";
	String txtDianbiao = request.getParameter("txtDianbiao")!=null?request.getParameter("txtDianbiao"):"";
    String zdname_key = request.getParameter("zdname_key")!=null?request.getParameter("zdname_key"):"";    
    String jiaofeifs = request.getParameter("jiaofeifs")!=null?request.getParameter("jiaofeifs"):"";
        String zt = request.getParameter("zt")!=null?request.getParameter("zt"):"";
    
    String rgsh=request.getParameter("rgsh");//站点审核通过   首页传的值
    String rgsh2=request.getParameter("caiji");// 采集站点
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
    int count=0,pagesize=10,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
   	String permissionRights="";         
%>
</script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
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
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<body>
<ul class="tab">
  <li class="first"></li>
  <li  class="cur"><a href="baozhang.jsp">铁塔报账信息</a></li>
 
  <!-- <li><a href="allbz.jsp">可报账记录</a></li>
  <li><a href="allcost.jsp">报账查询</a></li> -->
  <li class="end"></li>
</ul>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<%
  String whereStr="";
  String str="";
  String xuni="0";
  
	
		
  if (txtDianbiao != "") {
		whereStr=whereStr+" and d.DBNAME like '%" + txtDianbiao + "%'";
		//str=str+" and d.DBNAME like '%" + txtDianbiao + "%'";
	}
		//whereStr="and 2=2";
	if (zdname_key != "") {
			whereStr=whereStr+" and z.JZNAME like '%" + zdname_key + "%'";
			//str=str+" and d.ZDNAME like '%" + zdname_key + "%'";
			//z.id=d.zdid and b.DIANBIAOID = d.ID  and z.JZNAME like '%高密仁河%'		
		}
		if (jiaofeifs != "") {
			whereStr=whereStr+" and d.DFZFLX = '" + jiaofeifs + "'";				
		}
		if (zt != "") {
			whereStr=whereStr+" and b.STATE  = '" + zt + "'";				
		}
		if(accountCthrnumber!="" && accountCthrnumber!=null){
			
			whereStr = whereStr + " and d.BZR='"+accountCthrnumber+"' ";
		}
   %>
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">铁塔报账信息</div>
				<div class="content01_1">
					<table border="0" cellpadding="1" cellspacing="0" class="tb_select" >
						<tr>
						<td align="right" width="60px">电表名称：</td>
							<td align="left" width="60px">
							<input type="text" name="txtDianbiao" id="txtDianbiao" style="box-sizing: border-box; width: 130px;" />
							</td>
							<td align="right" width="60px">实体名称：</td>
							<td align="left" width="60px">
								<input type="text" name="zdname_key" id="zdname_key" style="box-sizing: border-box; width: 130px;" />
				         	</td>
				         	<td align="right" width="100px">
											缴费方式
										</td>
										<td width="100px">
											<select name="jiaofeifs"
												style="box-sizing: border-box; width: 130px;"
												class="selected_font">
												<option value="">
													请选择
												</option>
												<%
													ArrayList dfzflx = new ArrayList();
													dfzflx = ztcommon.getSelctOptions("FKFS");
													if (dfzflx != null) {
														String code = "", name = "";
														int cscount = ((Integer) dfzflx.get(0)).intValue();
														for (int i = cscount; i < dfzflx.size() - 1; i += cscount) {
															code = (String) dfzflx.get(i + dfzflx.indexOf("CODE"));
															name = (String) dfzflx.get(i + dfzflx.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
													}
													}
												%>

											</select>
										</td>
										<td align="right" width="100px">
											审核状态
										</td>
										<td width="100px">
											<select name="zt"
												style="box-sizing: border-box; width: 130px"
												class="selected_font">
												<option value="">
													请选择
												</option>
												<option value="1">
													待审核
												</option>
												<option value="2">
													已通过
												</option>
												<option value="3">
													未通过
												</option>
												<option value="0">
													未上报
												</option>
												
											</select>
										</td>
							<td align="right" colspan="8">
								<input type="submit" class="btn_c1" onclick="query()" value="查询" /> 
							</td>
							
						</tr>
					</table>
					
					<div class="tbtitle01"><b>报账信息统计一览</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
									<tr align="right">
										<td colspan="15" align="right">
										    <input name="button2" type="button" onclick="addbz()" class="btn_c1" value="新增" />&nbsp;&nbsp;
											<input name="button2" type="submit" onclick="daochu()" class="btn_c1" id="button2" value="导出Excel" />
										</td>
									</tr>
									<tr align="center">
									    <th width="10">序号</th>
										<th width="100">实体名称</th>
										<th width="80">电表名称</th>
										<th width="80">缴费方式</th>
										<th width="80">开始日期</th>
										<th width="80">结束日期</th>
										<th width="80">报账金额</th>
										<th width="80">电量</th>
										<th width="80">单价</th>
										<th width="80">状态</th>
										<th>操作</th>
									</tr>
						<%
		 SiteManage bean = new SiteManage();
       	 ArrayList fylist = new ArrayList();
         fylist = bean.getPageData_Tietabaozhang(curpage,loginName,loginId,whereStr);
       	 DecimalFormat df1 = new DecimalFormat("0.00");
       	 allpage=bean.getAllPage();
       	double a=0;
		String ID="", DIANBIAOID="",CBZX="", STARTTIME = "",ENDTIME = "",SQDS = "", BQDS= "",BEILV = "";
		String DIANLIANG="",ALLMONEY="",DIANSUN="",MONEY="",TAX="",SQDJ="",PRICE="",FTXS="",DBNAME="",STATE="";
		String DBBM="",ZDNAME="",PJLX="",JFFS="",GSZT="",STATENAME="";
		int intnum = pagesize*(curpage-1)+1;
		 System.out.println(intnum);
		 if(fylist!=null)
			{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
       			
				ID = (String) fylist.get(k + fylist.indexOf("ID"));
				DIANBIAOID = (String) fylist.get(k + fylist.indexOf("DIANBIAOID"));
				DBNAME = (String) fylist.get(k + fylist.indexOf("DBNAME"));
				CBZX = (String) fylist.get(k + fylist.indexOf("CBZX"));
				STARTTIME = (String) fylist.get(k + fylist.indexOf("STARTTIME"));
				ENDTIME = (String) fylist.get(k + fylist.indexOf("ENDTIME"));
				SQDS = (String) fylist.get(k + fylist.indexOf("SQDS"));
				BQDS = (String) fylist.get(k + fylist.indexOf("BQDS"));
				BEILV = (String) fylist.get(k + fylist.indexOf("BEILV"));
				DIANLIANG = (String) fylist.get(k + fylist.indexOf("DIANLIANG"));//电量
				ALLMONEY = (String) fylist.get(k + fylist.indexOf("ALLMONEY"));//总金额
				DIANSUN = (String) fylist.get(k + fylist.indexOf("DIANSUN"));
				MONEY = (String) fylist.get(k + fylist.indexOf("MONEY"));//金额
				TAX = (String) fylist.get(k + fylist.indexOf("TAX"));//税额
				SQDJ = (String) fylist.get(k + fylist.indexOf("SQDJ"));
				PRICE = (String) fylist.get(k + fylist.indexOf("PRICE"));//单价
				FTXS = (String) fylist.get(k + fylist.indexOf("FTXS"));
				STATE = (String) fylist.get(k + fylist.indexOf("STATE"));
				if(STATE.equals("1")){
				  //  STATENAME="待审核";   
					  STATENAME=(String)fylist.get(k+fylist.indexOf("ACCOUNTNAME")); 
				}else if(STATE.equals("2")){
				    STATENAME="已通过";   
				}else if(STATE.equals("3")){
				    STATENAME="未通过";   
				}else if(STATE.equals("4")){
				    STATENAME="已生成sap凭证编号";   
				}else{
				    STATENAME="未上报";   
				}
				
				DBBM = (String) fylist.get(k + fylist.indexOf("DBBM"));//电表编码
				ZDNAME = (String) fylist.get(k + fylist.indexOf("ZDNAME"));//站点名称
				PJLX = (String) fylist.get(k + fylist.indexOf("PJLX"));//发票类型
				JFFS = (String) fylist.get(k + fylist.indexOf("JFFS"));//缴费方式
				GSZT = (String) fylist.get(k + fylist.indexOf("GSZT"));//公司主体
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
				String STARTTIMEStr="",ENDTIMEStr="";
				if(STARTTIME!="" && !STARTTIME.equals("")){
					java.util.Date STARTTIMEDate=sdf.parse(STARTTIME);
					STARTTIMEStr = sdf.format(STARTTIMEDate); //开始日期
				}
				if(ENDTIME!="" && !ENDTIME.equals("")){
					java.util.Date ENDTIMEDate=sdf.parse(ENDTIME);
					ENDTIMEStr = sdf.format(ENDTIMEDate);
				}
				

       			if (intnum % 2 == 0) {
       				color = "#DDDDDD";

       			} else {
       				color = "#FFFFFF";
       			} 
       			
       %>
      
									<!-- 数据加载  Start-->
									<tr align="center">
										<td width="10"><%=intnum++%></td>
										<td align="center"><%=ZDNAME%></td>
										<td align="center"><%=DBNAME%></td>
										<td align="center"><%=JFFS%></td>
										<td align="center"><%=STARTTIMEStr%></td>
										<td align="center"><%=ENDTIMEStr%></td>
										<td align="right"><%=ALLMONEY %></td>
										<td align="right"><%=DIANLIANG%></td>
										<td align="right"><%=PRICE%></td>
										<td align="center"><%=STATENAME%></td>
										<td width="80">
									<a href="#" onclick="ch('<%=ID%>','<%=STATE%>')"><img src="../images/ch.png" width="16" height="16"  title="撤回" /></a>										
											<a href="#" onclick="rulu('<%=ID%>','<%=STATE%>')"><img src="../images/lefticon_13.png" width="16" height="16"  title="录入" /></a>
											<a href="#" onclick="xiangxi('<%=ID%>','<%=STATE%>')"><img src="../images/lefticon3.png" width="16" height="16"  title="详细" /></a>
											<a href="#" onclick="update('<%=ID%>','<%=STATE%>')"><img src="../images/accept.png" width="16" height="16"  title="修改" /></a>
											<%--<a href="#" onclick="tijiao('<%=ID%>','<%=STATE%>','<%=DIANBIAOID%>')"><img src="../images/icon08.png" width="16" height="16"  title="提交" /></a>
										--%>
											<a href="#" onclick="delbz('<%=ID%>','<%=STATE%>')"><img src="../images/delete.png" width="16" height="16"  title="删除" /></a>
										</td>
									</tr><% } %>
 
									<!-- 数据加载 End -->
									
									<tr bgcolor="#ffffff">
					<td colspan="15" align="left" >
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


						</div>
					</td>
				</tr>
								
								</table>
						<div class="space_h_10"></div>
				</div>
			</div>
		</td></tr>
		<% } %>
	<input type="hidden" name="whereStr"/>
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
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage - 1)%>';
      		document.form1.action=path+"/web/sdttWeb/baozhang/TietaBaozhang.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      		document.form1.action=path+"/web/sdttWeb/baozhang/TietaBaozhang.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = pageno;   
      document.form1.action=path+"/web/sdttWeb/baozhang/TietaBaozhang.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+pageno;
      document.form1.submit();
     }
     function delbz(id,s){
    	  if(s!=0){
	   alert("对不起，您只能删除未上报的报账信息！");
    			return;
   }
     else if(confirm("您确定删除此报账信息？")){    	
     		document.form1.action=path+"/servlet/zhandian?action=delttbaozhang&id="+id;
      		document.form1.submit();   
    }
    }
     //新增撤回功能
     function ch(id,s){	
    	 // alert("报账："+id);
    	 if(s!=1){   		   
    			alert("对不起！您只能撤回审核中的数据！");
        			return;   			
    		}
     
		if(confirm("您确认要撤回本条信息？")){
		     		document.form1.action=path+"/servlet/dianbiao?action=chttbz&id="+id;
		      		document.form1.submit();
		      		showdiv("请稍等..............");
		      	}
     }  
    
    function rulu(id,s){
    if(s=="2"){
		alert("审核通过的不能再进入录入");
	}else if(s=="1"){
		alert("已提交审核的不能再进入录入");
	}else if(s=="4"){
		alert("已生成sap凭证编号的不能再进入录入");
	}
	else{
    	window.open("../baozhang/TietaLR.jsp?id="+id, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
    }
    }
    function xiangxi(id){
    	window.open("../baozhang/TietaXiangx.jsp?id="+id, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
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

function addbz(){
		window.open("../baozhang/Addtietabz.jsp", "newwindow", "height=460, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
	
}
function daochu(){
	document.form1.action=path+"/servlet/CbDownload?bz=tietabaozhang";
	document.form1.whereStr.value="<%=whereStr%>";
	document.form1.submit();
}
function update(id,s){
	if(s=="2"){
		alert("审核通过的不能再修改");
	}else if(s=="1"){
		alert("已提交审核的不能再修改");
	}else if(s=="4"){
		alert("已生成sap凭证编号的不能再修改");
	}
	else{
		window.open("../baozhang/UpdateTietaBz.jsp?id="+id, "newwindow", "height=460, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,scrollbars=yes");  
	}
}
function xinzeng(id,s,dianbiaoid){
	if(s=="2"){
		alert("审核通过的不能再次提交审批");
	}else if(s=="1"){
		alert("已提交审核的不能再次提交审批");
	}else if(s=="4"){
		alert("已生成sap凭证编号的不能再次提交审批");
	}
	else{
		  if(confirm("您确定提交此报账信息？")){
     	//<if (permissionRights.indexOf("PERMISSION_DELETE") >= 0) {%> //权限
     		document.form1.action=path+"/servlet/zhandian?action=tijiaobaozhang&id="+id+"&dianbiaoid="+dianbiaoid;
      		document.form1.submit();
	      //<} else {%>
	      //alert("您没有删除站点的权限");
	    //<}%>
	    }
	}
}
function query(){
	document.form1.action=path+"/web/sdttWeb/baozhang/TietaBaozhang.jsp";
	document.form1.submit();
}
document.form1.jiaofeifs.value='<%=jiaofeifs%>';
document.form1.zt.value='<%=zt%>';
document.form1.zdname_key.value='<%=zdname_key%>';
document.form1.txtDianbiao.value='<%=txtDianbiao%>';
</script>


