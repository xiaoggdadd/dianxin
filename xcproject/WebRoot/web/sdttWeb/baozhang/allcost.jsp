
<%@page import="com.noki.util.Sys"%>
<%@page import="java.util.Date,java.util.Calendar"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean"%>
<%@ page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script>
	<%
    String path = request.getContextPath();    
    
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
	String roleId = (String)session.getAttribute("accountRole");    
	String cthrnumber=account.getCthrnumber();//(String)session.getAttribute("cthrnumber");    
	
   	 
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
   		shilist = commBean.getAgcode(sheng,account.getAccountId());
		if(shilist!=null){
      		int scount = ((Integer)shilist.get(0)).intValue();
      		//[2, AGCODE, AGNAME] update by guol2017112
			if(shilist.size()>(scount + 1)){
           		agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
			}
         }
   	} 	
   System.out.println("cthrnumber:"+cthrnumber);
	System.out.println("################站点"+roleId);
	/* 获取城市、区县、乡镇 */
	/*  String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
  	  String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
   	 String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";  */
   	 
   	 
   	 //获取年月日
   	 
   	/*  SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");//可以方便地修改日期格式
	String hehe = dateFormat.format(now);
	System.out.println("hehe:" + hehe); */
	Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH) + 1;
	String ymr = year + "-" + month;
   	 
   	   
    
     
    String zdname_key = request.getParameter("zdname_key")!=null?request.getParameter("zdname_key"):"";    
    String dbname_key = request.getParameter("dbname_key")!=null?request.getParameter("dbname_key"):"";
    String rcname_key = request.getParameter("rcname_key")!=null?request.getParameter("rcname_key"):"";
	String zdcode_key = request.getParameter("zdcode_key")!=null?request.getParameter("zdcode_key"):"";
	String dbcode_key = request.getParameter("dbcode_key")!=null?request.getParameter("dbcode_key"):"";
	String state_key = request.getParameter("state_key")!=null?request.getParameter("state_key"):"";
	String rccode_key = request.getParameter("rccode_key")!=null?request.getParameter("rccode_key"):"";
	String fptype_key = request.getParameter("fptype_key")!=null?request.getParameter("fptype_key"):"pjlx01";
	String bzqj_key   = request.getParameter("bzqj_key")!=null?request.getParameter("bzqj_key"):"";
    
    //时间
      String startTime = request.getParameter("startTime")!=null?request.getParameter("startTime"):"";
	String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";
    
    String rgsh=request.getParameter("rgsh");//站点审核通过   首页传的值
    String rgsh2=request.getParameter("caiji");// 采集站点
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
    int count=0,pagesize=10,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
   	String permissionRights="";         
%>
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!--[if gt IE 8]><!-->
	<script type="text/javascript"
		src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	<script type="text/javascript" src="<%=path%>/javascript/tx.js"></script>
</head>
<jsp:useBean id="commBean" scope="page"
	class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page"
	class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
	<ul class="tab" style="width: 1720px">
		<li class="first"></li>
		<!-- <li><a href="baozhang.jsp">报账信息</a></li> -->

		<li><a href="allbz.jsp">可报账记录</a></li>
		<li class="cur"><a href="allcost.jsp">报账查询</a></li>
		<li class="end"></li>


	</ul>
	<form action="" name="form1" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<%
  //and b.allcostid in(select max(b.allcostid) from ac_solo b group by b.bzid)
  String whereStr=" ";
			
		if (zdname_key != "") {
			whereStr=whereStr+" and b.zdname like '%" + zdname_key + "%'";
		}
		if (dbname_key != "") {
			whereStr=whereStr+" and b.dbname like '%" + dbname_key + "%'";
		}
		
		if (zdcode_key != "") {
			whereStr=whereStr+" and b.zdcode like '%" + zdcode_key + "%'";
		}
		if (dbcode_key != "") {
			whereStr=whereStr+" and b.dbcode like '%" + dbcode_key + "%'";
		}
		if (bzqj_key != "") {
			whereStr=whereStr+" and a.costtime like '%" + bzqj_key + "%'";
		}
		if (state_key != "") {
			whereStr=whereStr+" and a.coststate like '%" + state_key + "%'";
		}
		
		/* if (shi != "") {
			whereStr=whereStr+" and zd.shi like '%" + shi + "%'";
		}
		if (xian != "") {
			whereStr=whereStr+" and zd.xian like '%" + xian + "%'";
		}
		if (xiaoqu != "") {
			whereStr=whereStr+" and zd.xiaoqu like '%" + xiaoqu + "%'";
		} */
		//城市、区县、乡镇
		/* if (!shi.equals("0")) {
			whereStr=whereStr+" and zd.shi ='" + shi + "'";
		}
		
		if (!xian.equals("0")) {
			whereStr=whereStr+" and zd.xian='" + xian + "'";
		}
		if (!xiaoqu.equals("0")) {
			whereStr=whereStr+" and zd.xiaoqu='" + xiaoqu + "'";
		} */

		//时间
		if (startTime != "") {
			if(endTime == ("")){
				endTime = ymr;
				whereStr=whereStr+" and a.costtime between '" + startTime + "' and '" + endTime + "'";
			}else{
				whereStr=whereStr+" and a.costtime between '" + startTime + "' and '" + endTime + "'";				
			}
		}
		
		
		System.out.println("where(page:151:)"+whereStr);
		
		
   %>
			<tr valign="top">
				<td width="12"><img src="../images/space.gif" width="12"
					height="17" /></td>
				<td>
					<div class="content01">
						<div class="tit01">报账查询</div>
						<div class="content01_1">
							<table width="800px" border="0" cellpadding="1" cellspacing="0"
								class="tb_select">

								<!-- 添加城市、区县、乡镇 -->
								<!-- 需导入变量：sheng、shi、xian  -->
							<tr>
							
							<td align="right" width="60px">实体名称：</td>
										<td align="left" width="60px">
											<input type="text"
												name="zdname_key" id="zdname_key"
												style="box-sizing: border-box; width: 130px;" />
										</td>
										<td align="right" width="60px">实体编码：</td>
										<td align="left" width="60px"><input type="text"
											name="zdcode_key" id="zdcode_key"
											style="box-sizing: border-box; width: 130px;" />
										</td>
										
										<td align="right" width="60px">状态：</td>
									<td align="left" width="100px"><select name="state_key"
										id="state_key" style="box-sizing: border-box; width:130px"
										class="selected_font">
											<option value="">请选择</option>
											<option value="2">生成报账单成功</option>
											<option value="3">已生成SAP凭证</option>
											<option value="-1">报账单删除</option>
											<option value="-2">退单</option>
											<option value="-3">报账单发起失败</option>
											<option value="-4">等待生成报账单中</option>
									</select></td>
										
									<!-- 添加城市、区县、乡镇条件 -->
							<%-- 
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
				         							
											System.out.println("agname:"+agname);
				         		%>
				                    <option value="<%=agcode%>" id="op"><%=agname%></option>
				                    <%
				                    	}
				                    }
				                    %>
				         		</select>
				         		</td>
									<td align="right" width="60px">区县：</td>
									<td align="left" width="60px"><select name="xian"
										id="xian" style="box-sizing: border-box; width: 130px;"
										onchange="changeCountry()" class="selected_font">
											<option value="0">请选择</option>
										<%
				         			ArrayList xianlist = new ArrayList();
				         			System.out.println("shi:"+shi);
				         			System.out.println("xian:"+xian);
				         		xianlist = commBean.getAgcode(shi, xian, loginName);
				         		System.out.println("xianlist:"+xianlist);
				         			if (xianlist != null) {
				         				String agcode = "", agname = "";
				         				int scount = ((Integer) xianlist.get(0)).intValue();
				         				for (int i = scount; i < xianlist.size() - 1; i += scount) {
				         					agcode = (String) xianlist
				         							.get(i + xianlist.indexOf("AGCODE"));
				         					agname = (String) xianlist
				         							.get(i + xianlist.indexOf("AGNAME"));
				         							
											System.out.println("区县：agname:"+agname);
				         		%>
											<option value="<%=agcode%>"><%=agname%></option>
											<%
				                    	}
				                    	}
				                    %> 
									</select></td>
									<td align="right" width="60px">乡镇：</td>
									<td  id="td1" align="left">
									<select name="xiaoqu"
										id="xiaoqu" style="box-sizing: border-box; width: 130px;"
										class="selected_font">
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
									</select></td> --%>
									<!-- 以上是添加城市、区县、乡镇 -->
								
								<tr>
								
									<td align="right" width="60px">电表编码：</td>
									<td align="left" width="60px"><input type="text"
										name="dbcode_key" id="dbcode_key"
										style="box-sizing: border-box; width: 130px;" /></td>
										
										<td align="right" width="60px">电表名称：</td>
									<td align="left" width="60px"><input type="text"
										name="dbname_key" id="dbname_key"
										style="box-sizing: border-box; width: 130px;" /></td>
										
									
									<%--
				         	-4-等待生成报账单中;-3-报账单发起失败；-1-报账单删除，－2-退单；2-生成报账单成功；3-已生成SAP凭证
						--%>
									<td align="right" width="60px">报账期间：</td>
									<!-- <td width="100px">
										显示时间的input <input class="selected_font" type="text"
										name="bzqj_key" value="" readonly="readonly" class="Wdate"
										style=" background-color: #FFFFFF; color: grey"
										onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
									</td> -->
									
									<td align="left" width="60px">
										<input type="text" name="startTime" value="<%=startTime %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" style="box-sizing: border-box; width: 130px;"/>-
										<input type="text" name="endTime" value="<%=endTime %>" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" style="box-sizing: border-box; width: 130px;"/>
				         				</td>
									
									
									<td align="right" width="60px"><input type="submit"
										class="btn_c1" onclick="query()" value="查询" /></td>
								</tr>
							</table>

							<div class="tbtitle01">
								<b>报账记录查询统计一览</b>
							</div>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="tb_list1">
								<tr align="left">
									<td colspan="18" align="left"><input name="button2"
										type="button" onclick="daochu()" class="btn_c1" value="导出" />&nbsp;&nbsp;


										<input name="button2" type="button" onclick="updateConent()"
										class="btn_c1" value="更新" />&nbsp;&nbsp;</td>
								</tr>
								<tr align="center">
								<th width="10"><div>
											勾选<input type="checkbox" name="pageCheck"
												onclick="checkPage()" />
										</div></th>
									<th width="10">序号</th>
									<th width="50">状态</th>
									<th width="100">分公司名称</th>
									<th width="80">费用对象编码</th>
									<th width="80">费用对象名称</th>
									<th width="80">票据类型</th>
									<th width="80">实体编码</th>
									<th width="50">实体名称</th>
									<th width="50">费用类型</th>
									<th width="50">报账期间</th>
									<th width="50">报账人</th>
									<th width="50">开始日期</th>
									<th width="50">结束日期</th>
									<th width="50">报账单号</th>
									<th width="50">报账类型</th>
									<th width="50">业务类型</th>
									<th width="50">sap凭证编号</th>
									
								</tr>
								<%
		 SiteManage bean = new SiteManage();
       	 ArrayList fylist = new ArrayList();
         fylist = bean.getAllcost(curpage,loginName,loginId,whereStr,cthrnumber);
       	 DecimalFormat df1 = new DecimalFormat("0.00");
       	 allpage=bean.getAllPage();
       	 
        
       	String id="",coststate="",coststatename="",shiname="",dbname="",dbcode="",zdname="",zdcode="",bzname="";
       	String fptype="",fpname="",costtype="",costtypename="",costtime="",costusername="",starttime="",endtime="",costnum="",bztype="",ywtype="",appnum="";
       	
       	 
       	 
		int intnum = pagesize*(curpage-1)+1;
		 System.out.println(intnum);
		 if(fylist!=null)
			{
		     int fycount =0;
		     System.out.println("fylist.size():"+fylist.size());
		     if(fylist.size()>0){
		         fycount = ((Integer)fylist.get(0)).intValue();
		        
		     }
		     System.out.println("fycount:"+fycount);
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
       			
			    id = (String) fylist.get(k + fylist.indexOf("ID"));
			    coststate = (String) fylist.get(k + fylist.indexOf("COSTSTATE"));
			    if(coststate.equals("-4")){
			    	coststatename="等待生成报账单中";
			    }else if(coststate.equals("-3")){
			    	coststatename="报账单发起失败";
			    }else if(coststate.equals("-1")){
			    	coststatename="报账单删除";
			    }else if(coststate.equals("-2")){
			    	coststatename="退单";
			    }else if(coststate.equals("2")){
			    	coststatename="生成报账单成功";
			    }else if(coststate.equals("3")){
			    	coststatename="已生成SAP凭证";
			    }
			    shiname = (String) fylist.get(k + fylist.indexOf("SHINAME"));
			    dbname = (String) fylist.get(k + fylist.indexOf("DBNAME"));
			    dbcode = (String) fylist.get(k + fylist.indexOf("DBCODE"));
			    zdname = (String) fylist.get(k + fylist.indexOf("ZDNAME"));
			    zdcode = (String) fylist.get(k + fylist.indexOf("ZDCODE"));
			    fptype = (String) fylist.get(k + fylist.indexOf("FPTYPE"));
			    fpname = (String) fylist.get(k + fylist.indexOf("FPNAME"));
			    costtype = (String) fylist.get(k + fylist.indexOf("COSTTYPE"));
			    costtypename = (String) fylist.get(k + fylist.indexOf("COSTTYPENAME"));
			    costtime = (String) fylist.get(k + fylist.indexOf("COSTTIME"));
			    costusername = (String) fylist.get(k + fylist.indexOf("COSTUSERNAME"));
			    starttime = (String) fylist.get(k + fylist.indexOf("STARTTIME"));
			    endtime = (String) fylist.get(k + fylist.indexOf("ENDTIME"));
			    costnum = (String) fylist.get(k + fylist.indexOf("COSTNUM"));
			    bztype = (String) fylist.get(k + fylist.indexOf("BZTYPE"));
			    bzname = (String) fylist.get(k + fylist.indexOf("BZNAME"));
			    ywtype = (String) fylist.get(k + fylist.indexOf("YWTYPE"));
			    appnum = (String) fylist.get(k + fylist.indexOf("APPNUM"));
       			
       			
       %>

								<!-- 数据加载  Start-->
								<tr align="center">
								
								<td>
								<%if(!coststate.equals("3")){%>
								<input type="checkbox" name="itemSelected" value="<%=id%>" /></td>
								<%} %>
									<td width="10"><%=intnum++%></td>
									<td align="center"><%=coststatename%></td>
									<td align="center"><%=shiname%></td>
									<td align="center"><%=dbcode%></td>
									<td align="center"><%=dbname%></td>
									<td align="center"><%=fpname%></td>
									<td align="center"><%=zdcode%></td>
									<td align="center"><%=zdname%></td>
									<td align="center"><%=costtypename%></td>
									<td align="center"><%=costtime%></td>
									<td align="center"><%=costusername%></td>
									<td align="center"><%=starttime.substring(0,10)%></td>
									<td align="center"><%=endtime.substring(0,10)%></td>
									<td align="center"><%=costnum%></td>
									<td align="center"><%=bzname%></td>
									<td align="center"><%=ywtype%></td>
									<td align="center"><%=appnum%></td>
									
								</tr>
								<% } %>

								<!-- 数据加载 End -->

								<tr bgcolor="#ffffff">
									<td colspan="18" align="left">
										<div align="left">
											<!-- 							<font color='#1E90FF'>&nbsp;页次:</font> -->
											<!-- 							&nbsp;&nbsp; -->
											<!-- 							 <b><font color=red><%=curpage%></font></b> -->

											<!-- 							 <font color='#1E90FF'>/<b><%=allpage%></b>&nbsp;</font> -->
											&nbsp;&nbsp; <font color='#1E90FF'> <%
						     	if (curpage != 1) {
						     			out.print("<a href='javascript:gopagebyno(1)'><img src='../images/page-first.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-first-disabled.gif'></font>");
						     		}
						     %>
											</font> &nbsp;&nbsp; <font color='#1E90FF'> <%
						     	if (curpage != 1) {
						     			out.print("<a href='javascript:previouspage()'><img src='../images/page-prev.gif'></a>");
						     		} else {
						     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-prev-disabled.gif'></font>");
						     		}
						     %>
											</font> &nbsp;&nbsp; 转到 <select name="page"
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
						     %>
											</font> &nbsp;&nbsp; <font color='#1E90FF'> <%
					     	if (allpage != 0 && (curpage < allpage)) {
					     			out.print("<a href='javascript:gopagebyno(" + allpage+ ")'><img src='../images/page-last.gif'></a>");
					     		} else {
					     			out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-last-disabled.gif'></font>");
					     		}
					     %>
											</font> &nbsp;&nbsp;
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

							</table>
							<div class="space_h_10"></div>
						</div>
					</div>
				</td>
			</tr>
			<% } %><!-- else {%>-->
			<!--  <tr align="center" >
			<td align="left" colspan="9">
			当前无数据。
			</td>
		</tr>-->
			<input type="hidden" name="whereStr" />
		</table>
	</form>
</body>
</html>
<script type="text/javascript">
	
	
	var path = '<%=path%>';
	function checkPage() {
	if (document.form1.pageCheck.checked) {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = true
		}
	} else {
		for ( var j = 0; j < document.form1.elements.length; j++) {
			document.form1.elements[j].checked = false
		}
	}
}
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
      		document.form1.action=path+"/web/sdttWeb/baozhang/allcost.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      		document.form1.action=path+"/web/sdttWeb/baozhang/allcost.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = pageno;   
      document.form1.action=path+"/web/sdttWeb/baozhang/allcost.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+pageno;
      document.form1.submit();
     }
   
function query(){
	document.form1.action=path+"/web/sdttWeb/baozhang/allcost.jsp?";
	document.form1.submit();
}
function daochu(){
	
	document.form1.action=path+"/servlet/CbDownload?bz=allcost";	
	document.form1.whereStr.value="<%=whereStr%>";
	document.form1.submit();
}

function updateDept(obj){}
//更新报账单
function updateConent(){
	var obj=document.getElementsByName('itemSelected');
	var s='';  var isComp=0; var isRc=0;
	for(var k=0; k<obj.length; k++){ 
		if(s==""){
			if(obj[k].checked) s+=obj[k].value; //如果选中，将value添加到变量s中 
		}else{
			if(obj[k].checked) s+=","+obj[k].value; //如果选中，将value添加到变量s中 
		}
	} 
	if(s!=""){
		var arrs = s.split(",");
		for(var j=1; j<arrs.length; j++){
				if($("#c"+arrs[j]).val() != $("#c"+arrs[j-1]).val()){
					isComp=1;
					break;
				}
				if($("#r"+arrs[j]).val() != $("#r"+arrs[j-1]).val()){
					isRc=1;
					break;
				}
		} 
	}
	
	 if (s!="") {
		if (confirm("确定要对这些信息进行更新吗？")) {
			document.form1.action=path+"/servlet/zhandian?action=updateAllcost";
			document.form1.submit();
		//	window.open("addbz.jsp?ids="+s, "newwindow", "height=530, width=1250, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
			
		} else {
			return;
		}
	} else {
		alert("请选择要更新报账的信息！");
		return;
	}
}
/* 城市、区县、乡镇 */
<%-- document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>'; --%>
document.form1.zdname_key.value='<%=zdname_key%>';
document.form1.dbname_key.value='<%=dbname_key%>';
document.form1.zdcode_key.value='<%=zdcode_key%>';
document.form1.dbcode_key.value='<%=dbcode_key%>';
document.form1.startTime.value='<%=startTime%>';
document.form1.endTime.value='<%=endTime%>';
document.form1.state_key.value='<%=state_key%>';
</script>


