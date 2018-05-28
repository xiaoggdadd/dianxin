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
		String accountCthrnumber=account.getCthrnumber();
		if(accountCthrnumber==null){
		accountCthrnumber="";
		}
	 String bgsign=request.getParameter("bgsign")!=null?request.getParameter("bgsign"):"-1";//获取当前页 是否标杆 
   	 String dfzflx1=request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";//获取当前页面 电费支付类型 dfzflx的值
   	 String dbyt=request.getParameter("dbyt")!=null?request.getParameter("dbyt"):"0";//获取当前页 电表的 用途 dbyt
     
    String jzproperty=request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";//获取站点属性 
    String sptype = request.getParameter("sptype")!=null?request.getParameter("sptype"):"0";//
    String dbqyzt = request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"-1";//电表启用状态  
    
    String zdqyzt= request.getParameter("zdqyzt")!=null?request.getParameter("zdqyzt"):"1";//站点启用状态
    
	String sheng = (String)session.getAttribute("accountSheng"); 
	//String sheng = "137";
	String agcode1="0";
   	if(request.getParameter("shi")==null){
   		ArrayList shilist = new ArrayList();
   		CommonBean commBean = new CommonBean();
   		shilist = commBean.getAgcode(sheng,account.getAccountId());
		if(shilist!=null){
       		int scount = ((Integer)shilist.get(0)).intValue();
       		if(shilist.size()> (scount+1)){
            	agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
       		}
        }
   	}  
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
    String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
    String txtDianbiao = request.getParameter("txtDianbiao")!=null?request.getParameter("txtDianbiao"):"";
     String zt = request.getParameter("zt")!=null?request.getParameter("zt"):"";
    String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"";
    String dianbiaozt = request.getParameter("dianbiaozt")!=null?request.getParameter("dianbiaozt"):"";
   
	String sdbid = request.getParameter("sdbid")!=null?request.getParameter("sdbid"):"";
  	String zdmc=request.getParameter("zdmc")!=null?request.getParameter("zdmc"):"";
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
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
<link rel="stylesheet" href="<%=path %>/javascript/jquery-fileupload/css/jquery.fileupload.css" type="text/css"/>
<script type="text/javascript" src="<%=path %>/javascript/jquery-fileupload/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=path %>/javascript/jquery-fileupload/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="<%=path %>/javascript/jquery-fileupload/js/jquery.fileupload.js"></script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<body>
<ul class="tab">
  <li class="first"></li>
  <li><a href="sitemanage.jsp">局站管理</a></li>
  <li class="cur"><a href="dianbiaolist.jsp">电表管理</a></li>
  <li><a href="siteDBChaxun.jsp">局站电表查询</a></li>
  <li class="end"></li>
</ul>
<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<%
  String whereStr="";
  String str="";
  String xuni="0";
  
		if (zt != "") {
			whereStr=whereStr+" and d.shzt  = '" + zt + "'";				
		}
		if (dianbiaozt != "") {
			whereStr=whereStr+" and d.dbqyzt  = '" + dianbiaozt + "'";				
		}
		if(accountCthrnumber!=""){
			whereStr = whereStr + " and d.BZR= '"+accountCthrnumber + "'";
		}
		
   %>
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">电表信息</div>
				<div class="content01_1">
					<table width="100%" border="0" cellpadding="1" cellspacing="0" class="tb_select" >
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
							
							<td align="right" width="100px">
											电表状态
										</td>
										<td width="100px">
											<select name="dianbiaozt"
												style="box-sizing: border-box; width: 130px;"
												class="selected_font">
												<option value="">
													请选择
												</option>
												<%
													ArrayList dbztlist = new ArrayList();
													dbztlist = ztcommon.getSelctQyzt("dbzt");
													if (dbztlist != null) {
														String code = "", name = "";
														int cscount = ((Integer) dbztlist.get(0)).intValue();
														for (int i = cscount; i < dbztlist.size() - 1; i += cscount) {
															code = (String) dbztlist.get(i + dbztlist.indexOf("CODE"));
															name = (String) dbztlist.get(i + dbztlist.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
													}
													}
												%>

											</select>
										</td>
										</tr>
										<tr>
							<td align="right" width="100px">
											审核状态
										</td>
										<td width="100px">
											<select name="zt"
												style="box-sizing: border-box; width:130px"
												class="selected_font">
												<option value="">
													请选择
												</option>
												<option value="1">
													待审核
												</option>
												<option value="2">
													已通过
												
												<option value="0">
													未上报
												</option>
												
											</select>
										</td>
										<td align="right" width="60px">电表名称：</td>
							<td align="left" width="60px">
							<input type="text" name="txtDianbiao" id="txtDianbiao" style="box-sizing: border-box; width: 130px;" />
							</td>
							<td align="right" width="60px">实体名称：</td>
							<td align="left" width="60px">
							<input type="text" name="txtKeyword" id="txtKeyword" value="" style="box-sizing: border-box; width: 130px;" />
							
							</td>
							
										<td align="right">
								<input onclick="query()" type="submit" class="btn_c1"   value="查询" /> 
							</td>
						</tr>
						
						
							
						
					</table>
					
					<div class="tbtitle01"><b>电表查询统计一览</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
									<tr align="right">
										<td colspan="12" align="right">
										   <%-- <span class="btn_c1 fileinput-button">
												<span style="color: #fff; line-height: 22px;">导入Excel</span>
												<input type="file" name="file" id="importExcel">
										    </span>
										    <input name="button2" type="button" onclick="downloadTemp()" class="btn_c1" id="button2" value="下载模板" />&nbsp;&nbsp; --%>
										    <input name="button2" type="button" onclick="addjz()" class="btn_c1" id="button2" value="新增" />&nbsp;&nbsp;
											<input name="button2" type="submit" onclick="daochu()" class="btn_c1" id="button2" value="导出Excel" />
										</td>
									</tr>
									
									
									<tr align="center">
									    <th width="10">序号</th>
										<th width="120">电表编码</th>
										<th width="100">电表名称</th>
										<th width="80">分公司</th>
										<th width="80">区县分公司</th>
										<th width="80">实体名称</th>
										<th width="80">用户编号</th>
										<th width="80">责任人</th>
										<th width="80">报账人</th>
										<th width="80">电表状态</th>
										<!--  <th width="80">审核状态</th>-->
										<th width="80">当前审核</th>
										<th>操作</th>
									</tr>
      <%
       	DianBiaoBean bean = new DianBiaoBean();
       	 ArrayList fylist = new ArrayList();
       	 System.out.println("--------------"+zdqyzt);
       	 fylist = bean.getPageData(curpage,sheng,shi,xian,xiaoqu,sdbid,loginName,zdmc,loginId,sptype,jzproperty,dbyt,dfzflx1,bgsign,dbqyzt,zdqyzt,keyword,txtDianbiao,whereStr);
       	 allpage=bean.getAllPage();
		String dbid = "",zdname = "",area = "", sszy= "",dbyt3="",csds="",cssytime="",beilv="",id="",dbxh="",memo="",dfzflx2="",zdlx="",zdid="",dbbm="",wlbm="",dbname="",dbzt="",yhbh="",zrr="",bzr="",fgs="",qxfgs="",shzt="";
		String sh="",bzrname="",zrrname="";
		int intnum=xh = pagesize*(curpage-1)+1;
		Double bl=0.00;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     	//num为序号，不同页中序号是连续的
		     	id = (String)fylist.get(k+fylist.indexOf("ID"));
		     	dbbm = (String)fylist.get(k+fylist.indexOf("DBBM"));
		     	wlbm = (String)fylist.get(k+fylist.indexOf("WLBM"));
		     	dbname = (String)fylist.get(k+fylist.indexOf("DBNAME"));
				dbid = (String)fylist.get(k+fylist.indexOf("DBID"));
		    	zdname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    	zdid=(String)fylist.get(k+fylist.indexOf("ZDID"));
		    	area = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		    	zdlx = (String)fylist.get(k+fylist.indexOf("ZDLX"));
		  		//  System.out.println("所在地区..."+area);
		    	sszy = (String)fylist.get(k+fylist.indexOf("SSZY"));
				dbyt3 = (String)fylist.get(k+fylist.indexOf("DBYT"));
				csds = (String)fylist.get(k+fylist.indexOf("CSDS"));
				cssytime = (String)fylist.get(k+fylist.indexOf("CSSYTIME"));
				beilv = (String)fylist.get(k+fylist.indexOf("BEILV"));
				dbxh = (String)fylist.get(k+fylist.indexOf("DBXH"));
				memo = (String)fylist.get(k+fylist.indexOf("MEMO"));
				dfzflx2 = (String)fylist.get(k+fylist.indexOf("DFZFLX"));
				dbzt = (String)fylist.get(k+fylist.indexOf("DBZT"));
				yhbh = (String)fylist.get(k+fylist.indexOf("YHBH"));
				zrr = (String)fylist.get(k+fylist.indexOf("ZRR"));
				bzr = (String)fylist.get(k+fylist.indexOf("BZR"));
				zrrname = (String)fylist.get(k+fylist.indexOf("ZRRNAME"));
				bzrname = (String)fylist.get(k+fylist.indexOf("BZRNAME"));
				fgs = (String)fylist.get(k+fylist.indexOf("SHI"));
				qxfgs = (String)fylist.get(k+fylist.indexOf("XIAN"));
				if(csds==null||csds.equals(""))csds="0";
				DecimalFormat price = new DecimalFormat("0.00");
				csds = price.format(Double.parseDouble(csds));			
			
				if(cssytime==null)cssytime="";
				if(beilv==null)beilv="";
				DecimalFormat mat=new DecimalFormat("0.00");
			    if(beilv==null||beilv==""||beilv=="o")beilv="0";
	            bl=Double.parseDouble(beilv);
	            beilv=mat.format(bl);
				if(dbxh==null)dbxh="";
				if(memo==null)memo="";
				if(sszy==null)sszy="";
				if(dbyt3==null)dbyt3="";
				if(dfzflx2==null)dfzflx2="";
				if(intnum%2==0){
				    color="#DDDDDD";
	
				 }else{
				    color="#FFFFFF" ;
				}
				shzt = (String)fylist.get(k+fylist.indexOf("SHZT"));
				System.out.println("--------------"+shzt);
				if(shzt.equals("0")){
					sh="未上报";
				}else if(shzt.equals("2")){
					sh="已通过";
				}else if(shzt.equals("1")) {
					sh=(String)fylist.get(k+fylist.indexOf("ACCOUNTNAME"));
				}
       %>
									<!-- 数据加载  Start-->
									<tr align="center">
										<td width="10"><%=intnum++%></td>
										<td align="center"><%=dbbm%></td>
										<td align="center"><%=dbname%></td>
										<td align="center"><%=fgs%></td>
										<td align="center"><%=qxfgs%></td>
										<td align="center"><%=zdname%></td>
										<td align="center"><%=yhbh%></td>
										<td align="center"><%=zrrname%></td>
										<td align="center"><%=bzrname%></td>
										<td align="center"><%=dbzt%></td>
										<td align="center"><%=sh%></td>
										<td width="80">
											<a href="#" onclick="xiangxi('<%=id%>','<%=zdid%>')"><img src="../images/lefticon3.png" width="16" height="16"  title="详细" /></a>
											<a href="#" onclick="tj('<%=id%>','<%=zdid%>','<%=shzt%>')"><img src="../images/icon08.png" width="16" height="16"  title="提交审批" />
											<a href="#" onclick="update('<%=id%>','<%=zdid%>','<%=shzt%>')"><img src="../images/accept.png" width="16" height="16"  title="修改" /></a>
											<a href="#" onclick="updateSX('<%=id%>','<%=shzt%>')"><img src="../images/lefticon_01.png" width="16" height="16"  title="修改属性" /></a>
											<a href="#" onclick="delzd('<%=id%>','<%=shzt%>')"><img src="../images/delete.png" width="16" height="16"  title="删除" /></a>
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
	<input type="hidden" name="whereStr"/>
		</table>
		<input type="hidden"  name="templetname" value="电表批量导入模板"/>
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
      		document.form1.action=path+"/web/sdttWeb/jichuInfoManager/dianbiaolist.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/sdttWeb/jichuInfoManager/dianbiaolist.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/sdttWeb/jichuInfoManager/dianbiaolist.jsp?curpage="+pageno;
      document.form1.submit();
     }
     function delzd(id,s){	
    	 if(s!=0){
    			alert("数据审核中或已通过，无法进行此操作");
    			return;
    			
    		}
     
		if(confirm("您确认要删除本条电表信息？")){
		     		document.form1.action=path+"/servlet/dianbiao?action=del&id="+id;
		      		document.form1.submit();
		      		showdiv("请稍等..............");
		      	}
     }      	

    function xiangxi(id,zdid){
     	  window.open("../jizan/dbxxquery_new.jsp?bz=1&id="+id+"&zdid="+zdid, "newwindow", "height=600, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
     	
     }	
    function tj(id,zdid,s){
	if(s!=0){
		alert("数据审核中或已通过，无法进行此操作");
		return;
		
	}
	window.open("../jizan/dianbiaoSub.jsp?id="+id+"&zdid="+zdid, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
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
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateQx(res);                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
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
	window.open("../jizan/adddianbiao_new.jsp", "newwindow", "height=600, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}
function daochu(){
	document.form1.action=path+"/servlet/CbDownload?bz=dianbiao";
	document.form1.whereStr.value="<%=whereStr%>";
	document.form1.submit();
}
function update(id,zdid,s){
	if(s==1){
		alert("数据审核中，无法进行此操作");
		return;
	}
     window.open("../jizan/adddianbiao_new.jsp?bz=1&id="+id+"&zdid="+zdid+"&shzt="+s, "newwindow", "height=600, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
     document.form1.submit();
    }
function updateSX(id,s){
	if(s!=2){
		alert("数据审核中或未上报，无法进行此操作");
		return;
		
	}
     window.open("../jizan/updateDianbiaoSx.jsp?id="+id, "newwindow", "height=500, width=1200, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
     document.form1.submit();
    }
function query(){
	document.form1.action=path+"/web/sdttWeb/jichuInfoManager/dianbiaolist.jsp";
}
function downloadTemp(){
   document.form1.action=path+"/servlet/download?action=download";
   document.form1.submit();
}
function openUpload(){
	window.open("uploadEx.jsp", "newwindowUp", "height=550, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
}
$(function(){
	$('#importExcel').fileupload({
		url: '<%=path%>/servlet/download?action=upload',
		sequentialUploads: true
	}).bind('fileuploadadd', function(e, data){
		var reg = /^.*\.(?:xls|xlsx)$/i;
		if(!reg.test(data.files[0].name)){
			alert("文件格式错误, 仅允许导入“xls”或“xlsx”格式文件");
		    return false;
		}
		//console.log(data.files[0].size)
		if(data.files[0].size > 5242880){
			alert("文件过大, 仅允许导入5M大小文件");
		    return false;
		}
		data.submit()
		.success(function(result, textStatus, jqXHR){ 
			
		console.log(result);
		    alert("导入成功");
		})
		.error(function(jqXHR, textStatus, errorThrown){ 
			alert("导入失败");
		});
	}).bind('fileuploadprogress', function(e, data){
		
	});	

	});

document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
document.form1.zt.value='<%=zt%>';
document.form1.dianbiaozt.value='<%=dianbiaozt%>';
document.form1.txtDianbiao.value='<%=txtDianbiao%>';
document.form1.txtKeyword.value='<%=keyword%>';
</script>


