<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%
    String path = request.getContextPath();
    String loginName = (String)session.getAttribute("loginName");
   	Account account = (Account)session.getAttribute("account");         
	String sheng = (String)session.getAttribute("accountSheng");
	String roleId = (String)session.getAttribute("accountRole");
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
    String permissionRights="";         
%>

<html>
<head>
<title>
省级无建议生产标原因
</title>
<style>
         .style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}

 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}

.bttcn{color:BLACK;font-weight:bold;}

.form    {width:130px}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
 
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
  <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
 <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>

<script language="javascript">
var path = '<%=path%>';

function chaxun(){
  		var shi=document.form1.shi.value;
  		var zdsx1=document.form1.jzproperty.value;
  
	    var href = "noAdvScbProReason_iframe1.jsp?shi="+shi+"&zdsx1="+zdsx1+"&bzw=2";
	    var str = "<a id='aa' href='"+href+"' target='test'></a>";
	    $("#aa").remove();
	    $("body").append(str);
	    $("#aa")[0].click();
	    
	    var href1 = "noAdvScbProReason_iframeMap.jsp?shi="+shi+"&zdsx1="+zdsx1+"&bzw=2";
	    var str = "<a id='aa' href='"+href1+"' target='treeNodeInfo1'></a>";
	    $("#aa").remove();
	    $("body").append(str);
	    $("#aa")[0].click();
}

function exportad(){
	var shi=document.form1.shi.value;
  	var zdsx1=document.form1.jzproperty.value;
  	document.form1.action=path+"/web/appJSP/noadvicescb/province/provReasExportad.jsp?shi="+shi+"&jzproperty="+zdsx1;
	document.form1.submit();
}

  $(function(){
		$("#query").click(function(){
			chaxun();
		});
		$("#daochuBtn").click(function(){
	   		exportad();
		});
	});
</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
        permissionRights=commBean.getPermissionRight(roleId,"0101");
%>
<body  class="body" >
		
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" >
	      <tr>
           <td width="50" >
             <div style="width:700px;height:50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">省级无建议生产标原因</span>
	         </div>
	        </td>
		  </tr>
		  <tr>
		   <td height="20" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
            </td>
          </tr>

  		 <tr>
  		   <td width="1200px">
  		   <table>	
  		     <tr class="form_label">
               <td >城市：</td>      
                <td><select name="shi" class="selected_font" id="shi" >
         		<option value="0">全省</option>
         		<%
		         	ArrayList shilist = new ArrayList();
		         	shilist = commBean.getAgcode(sheng,account.getAccountId());
		         	if(shilist!=null){
		         		String agcode="",agname="";
		         		int scount = ((Integer)shilist.get(0)).intValue();
		         		for(int i=scount;i<shilist.size()-1;i+=scount)
	                    {
	                    	agcode=(String)shilist.get(i+shilist.indexOf("AGCODE"));
	                    	agname=(String)shilist.get(i+shilist.indexOf("AGNAME"));
	                    %>
	                    <option value="<%=agcode%>"><%=agname%></option>
	                    <%}
		         	}
		         %>
         	    </select></td>

                <td>
				站点属性：
				</td>
				<td>
					<select name="jzproperty" class="selected_font" >
						<option value="0">请选择</option>
						<%
							ArrayList zdsx = new ArrayList();
							zdsx = ztcommon.getSelctOptions("ZDSX");
							if (zdsx != null) {
								String code = "", name = "";
								int cscount = ((Integer) zdsx.get(0)).intValue();
								for (int i = cscount; i < zdsx.size() - 1; i += cscount) {
								code = (String) zdsx.get(i + zdsx.indexOf("CODE"));
								name = (String) zdsx.get(i + zdsx.indexOf("NAME"));
						%>
						<option value="<%=code%>"><%=name%></option>
						<%
							}
						  }
						%>
					</select>
				</td>
                  
	         	  <td>
                     <div id="query" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
		                  <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                  <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
		             </div>
			      </td>
			    </table>
			 </td>
		</tr>
 </table>
  <table height="23">
     <tr><td height="5"  colspan="4" >
	         <div id="parent" style="display:inline">
             </div><div style="width:50px;display:inline;"><hr></div>
             <font size="2">&nbsp;信息列表&nbsp;</font>
             <div style="width:300px;display:inline;"><hr></div>
          </td></tr>
 </table>

 </form>           
  	<iframe name="test" width="40%" height="400px" frameborder="0" src="<%=path %>/web/appJSP/noadvicescb/province/noAdvScbProReason_iframe1.jsp"></iframe>
    
    <iframe name="treeNodeInfo1" width="60%" height="400px" frameborder="0" src="<%=path %>/web/appJSP/noadvicescb/province/noAdvScbProReason_iframeMap.jsp"></iframe>
  	<%--<iframe name="treeNodeInfo"  background-position-x ="52%" width="60%" height="360px" frameborder="0" src="<%=path %>/web/appJSP/noadvicescb/province/noAdvScbProReason_iframe2.jsp"></iframe>--%>

<table width="100%"  border="0" cellspacing="0" cellpadding="0" align="right" height="5%">
  	<tr align="right">
        <td align="right" height="19" colspan="4">
       		<div id="parent" style="display:inline">
            	<div style="width:50px;display:inline;"><hr></div>
            	<div style="width:300px;display:inline;"><hr></div>
            </div>
        </td>
    </tr>
    <tr>
		<td align="right">                                                                  
            <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
	 			<img src="<%=path%>/images/daochu.png" width="100%" height="100%">
				<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
			</div>
        </td>
  	</tr>
</table>

</body>
</html>

