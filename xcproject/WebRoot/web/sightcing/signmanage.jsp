 <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page  import="java.util.*"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage" %>
<%
String path = request.getContextPath();
System.out.println("路径"+path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String roleId = (String)session.getAttribute("accountRole");
        String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        
        
        String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
          String permissionRights="";
          int intnum=0;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'signmanage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

 
  <style type="text/css">
  .btt{font-weight:bold;}
  
  .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}
  
  </style>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 
<script type="text/javascript">
  var path ='<%= path%>';
  
  function cs(id){
  alert(id);
  document.form1.action=path+"/web/sightcing/modifysign.jsp?id="+id;
      document.form1.submit();
  }






</script>
 <script language="javascript">
  var path = '<%= path%>';
  function modifyjz(id){
      document.form1.action=path+"/web/sightcing/modifysign.jsp?id="+id;
      document.form1.submit();
   
    }
  function addsign(){
  
          document.form1.action=path+"/web/sightcing/addsign.jsp";
          document.form1.submit();
        }
 
	 function delzd(id){
     if(confirm("您确定删除此标杆类型信息？")){
     	
     document.form1.action=path+"/servlet/zhandian?action=delsign&id="+id
      document.form1.submit();
    
    }
    }	
 
  	$(function(){
		$("#add").click(function(){
			addsign();
		});
		
	});
  
  
  </script>
   </head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
  <%
	permissionRights = commBean.getPermissionRight(roleId, "0101");

	System.out.println(">>>>>>>>>>>>>>..." + permissionRights);
%>
  <body>
    <form action="" name="form1" method="post">
    	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4"></td>
						 </tr>
						 <tr>
							<td colspan=1 width="700"
							 background="<%=path%>/images/btt.bmp" height=63>
							<span style="color: black; font-weight: bold;font-size:14px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标杆类型信息列表</span>
							</td>

							<td width="380">&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
					
					<tr>
					<td>
					<div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>
                      </td>
                   </tr>
		 <tr>
			<td>&nbsp;</td>
		</tr>
		</table>			
    <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#666666" class="form_label" >
       
    	<tr>
    	    
    		<td height="15" width="6%"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="btt" >标杆类型标号</div></td>
    		<td height="15" width="6%"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="btt" >标杆类型名称</div></td>
    		<td height="15" width="6%"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="btt" >站点类型</div></td>
    		<td height="15" width="6%"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="btt" >2G</div></td>
    		<td height="15" width="6%"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="btt" >3G</div></td>
    		<td height="15" width="6%"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="btt" >OLT</div></td>
    	    <td height="15" width="6%"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="btt" >空调数量</div></td>
    	    <td height="15" width="6%"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="btt" >地域类型</div></td>
    	    <td height="15" width="6%"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="btt" >操作</div></td>
    		<td height="15" width="6%"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="btt" >操作</div></td>
    		
    
   	 	</tr>
    	 <%
			
       SiteManage bean = new SiteManage();
       	 ArrayList fylist = new ArrayList();
        fylist = bean.getSigntypenum(loginId);
       	 
       	
		int id;
		String name="",sitetype = "",region = "",g2="",g3="",olt="",ktnum="";
		
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
                intnum++;
       			//num为序号，不同页中序号是连续的
       			String d = (String)fylist.get(k + fylist.indexOf("ID"));
       			id=Integer.parseInt(d);
       			name = (String) fylist.get(k + fylist.indexOf("NAME"));
       			sitetype = (String) fylist.get(k + fylist.indexOf("STATIONTYPEID"));
       			//System.out.println("所在地区..."+szdq);
       			region = (String) fylist.get(k
       					+ fylist.indexOf("REGION"));
       			g2 = (String) fylist.get(k + fylist.indexOf("G2"));
       			g3 = (String) fylist.get(k + fylist.indexOf("G3"));
       			olt = (String) fylist.get(k + fylist.indexOf("OLT"));
       			
       			ktnum = (String) fylist.get(k + fylist.indexOf("AIR_CONDITION"));
       			
       			if (name == null)
       				name = "";
       			if (sitetype == null)
       				sitetype = "";
       			
       			/*if (region.equals("1")){
       				region = "沿海";
       			}else if(region.equals("0")||region.equals("")||region==null){
       			region = "内陆";
       			}*/
       			if (g2.equals("1")){
       				g2 = "是";
       			}else if(g2.equals("0")||g2.equals("")||g2==null){
       			g2 = "否";
       			}
       				
       			if (g3.equals("1")){
       				g3 = "是";
       			}else if(g3.equals("0")||g3.equals("")||g3==null){
       			g3 = "否";
       			}
       			if (olt.equals("1")){
       				olt = "是";
       			}else if(olt.equals("0")||olt.equals("")||olt==null){
       			olt = "否";
       			}
       			if (ktnum == null)
       				ktnum = "";
       				 			String color = null;

       			if (intnum % 2 == 0) {
       				color = "#DDDDDD";

       			} else {
       				color = "#FFFFFF";
       			}
       			
       %>
  
       <tr  class="form_label" bgcolor=<%=color%>>

			<td class="form_label">
       			<div align="center" ><%=id%></div>
       		</td>
       		<td class="form_label">
       			<div align="center"  ><%=name%></div>
       		</td>
       		<td class="form_label">
       			<div align="left"  ><%=sitetype%></div>
       		</td>
       		
       		<td class="form_label">
       			<div align="center"  ><%=g2%></div>
       		</td>
       		<td class="form_label">
       			<div align="center"  ><%=g3%></div>
       		</td>
       		<td class="form_label">
       			<div align="center"  ><%=olt%></div>
       		</td>
       		<td class="form_label">
       			<div align="center"  ><%=ktnum%></div>
       		</td>
       		<td class="form_label">
       			<div align="center"  ><%=region%></div>
       		</td>
       		
      
       		<td class="form_label">
       			<div align="center" ><a href="#" onclick="delzd('<%=id%>')"><font  style="font-size: 12px;">删除</font></a></div>
       		</td>
       		<td class="form_label">
       			<div align="center" ><a href="#" onclick="modifyjz('<%=id %>')"><font  style="font-size: 12px;">修改</font></a></div>
       		</td>

       </tr>
       <%
       	}
       %>
       <%
       	}
       %>

    </table>
    <table>
    <tr>
    	<td>&nbsp;</td>
    </tr>
       <tr bgcolor="F9F9F9">
                      <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>
    <tr>
    	<td>  
    	<%
			if (permissionRights.indexOf("PERMISSION_ADD") >= 0) {
		%>
            <div id="add" style="position:relative;width:60px;height:23px;cursor: pointer;left:700px;float:right;">
		       <img alt="" src="<%=request.getContextPath() %>/images/xinzeng.png" width="100%" height="100%" />
		        <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">增加</span>
			</div>
                             
                               
    	<%
    	}
    	%>
    	
    	</td>
    </tr>
    
    </table>
    
    </form>
  </body>
</html>
