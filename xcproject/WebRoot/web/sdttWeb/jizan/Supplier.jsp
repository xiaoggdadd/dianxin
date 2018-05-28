<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@page import="com.ptac.app.util.Page"%>
<%@page import="com.noki.util.Sys"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
    String path = request.getContextPath();
	String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	String color=null;
	String code = request.getParameter("code")!=null?request.getParameter("code"):"";
	String name = request.getParameter("name")!=null?request.getParameter("name"):"";
	
	 
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
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
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<body>
	<form action="" name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">供应商选择</div>
				<div class="content01_1">
					<table width="800px" border="0" cellpadding="1" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="60px">供应商编码：</td>
							<td align="left" width="60px">
							       <input type="text" name="code" style="width: 130px;" value="<%=code %>"/>
				         		</td>
				         		<td align="right" width="60px">供应商名称：</td>
								<td align="left" width="60px">
									<input type="text" name="name" style="width: 130px;" value="<%=name %>" />
				         		</td>
				         		<td><input onclick="query()" type="submit" class="btn_c1"   value="查询" /> </td>
						</tr>
					</table>
					
					<div class="tbtitle01"><b>供应商查询统计一览</b></div>
						<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="tb_list1" >
									<tr align="right">
										<td colspan="9" align="right">
										</td>
									</tr>
									
									
									<tr align="center">
									    <th width="10">序号</th>
										<th width="120">供应商编码</th>
										<th width="100">供应商名称</th>
										<th>操作</th>
									</tr>
      <%
       	 ArrayList costlist = new ArrayList();
         int totalRow = commBean.getSupplierTotalRow(code, name);
      	Page pager = new Page(totalRow,10); 
      	 pager.setPage(Integer.parseInt(s_curpage));
         int curpage = pager.getPage();
         if(code.equals("")&&name.equals("")){       	 
        	 costlist=null;
         }else{
        	 costlist = commBean.getSupplierPage(code, name ,pager);
        	 totalRow= commBean.getSupplierTotalRow(code, name);
        	 
         }
         
        
      	
      	int totalPage = pager.getTotalPage();
      	int intnum = pager.getPageSize()*(pager.getPage()-1)+1;
		String pcode = "",pname = "";
		 if(costlist!=null)
		{
			int costcount = ((Integer)costlist.get(0)).intValue();
			for( int k = costcount;k<costlist.size()-1;k+=costcount){

		     	//num为序号，不同页中序号是连续的
		     	pcode = (String)costlist.get(k+costlist.indexOf("CODE"));
		     	pname = (String)costlist.get(k+costlist.indexOf("NAME"));
		    	
				

       %>
									<!-- 数据加载  Start-->
									<tr align="center">
										<td width="10"><%=intnum++%></td>
										<td align="left"><%=pcode%></td>
										<td align="left"><%=pname%></td>
										<td width="80">
											<a href="#" onclick="selectObj('<%=pcode%>','<%=pname %>')">选择</a>
										</td>
									</tr>
 									<% } %>
									<!-- 数据加载 End -->
									
									<tr bgcolor="#ffffff"  >
					<td colspan="16" >
						<div align="left">
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
					     	for (int i = 1; i <= totalPage; i++) {
					     			if (curpage == i) {
					     				out.print("<option value='" + i
					     						+ "' selected='selected'>" + i + "</option>");
					     			} else {
					     				out.print("<option value='" + i + "'>" + i
					     						+ "</option>");
					     			}
					     		}
					     %>
				    </select>&nbsp;&nbsp;共 <font color='#1E90FF'><b><%=totalPage%></b>&nbsp;</font>页 &nbsp;&nbsp;
						   
							<font color='#1E90FF'>
						     <% if(totalPage!=0&&(curpage<totalPage))
						         {out.print("<a href='javascript:nextpage()'><img src='../images/page-next.gif'></a>");}
						         else
						        {out.print("<font size='2' style='font-family:微软雅黑'><img src='../images/page-next-disabled.gif'></font>");}
						     %>
             </font>
							&nbsp;&nbsp;
							<font color='#1E90FF'>
					     <% if(totalPage!=0&&(curpage<totalPage))
					         {out.print("<a href='javascript:gopagebyno("+totalPage+")'><img src='../images/page-last.gif'></a>");}
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
	function gopage(){
      document.form1.submit();
    }
    function previouspage(){
    	var curPage = "<%=pager.getPreviousPage() %>";
     	document.form1.action=path+"/web/sdttWeb/jizan/Supplier.jsp?curpage="+curPage;
        document.form1.submit();
    }
    function nextpage(){
        var curPage = "<%=pager.getNextPage() %>";
        document.form1.action=path+"/web/sdttWeb/jizan/Supplier.jsp?curpage="+curPage;
        document.form1.submit();
    }
    function gopagebyno(pageno){
       document.form1.action=path+"/web/sdttWeb/jizan/Supplier.jsp?curpage="+pageno;
       document.form1.submit();
    }


function query(){
	document.form1.action=path+"/web/sdttWeb/jizan/Supplier.jsp";
	document.form1.submit();
}
function selectObj(code, name){
	opener.document.form1.gysmc.value=name;
	opener.document.form1.gysbm.value=code;
	window.close();
}
</script>


