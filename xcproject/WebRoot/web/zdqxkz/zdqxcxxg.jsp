<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="com.noki.zdqxkz.dao.ShiQuery,com.noki.zdqxkz.javabean.Zdqxkz"%>
<%@ page import="com.noki.zdqxkz.dao.Zdqxcxxg"%>
<%@ page import="java.text.*"%>
<%
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
	String sheng = (String)session.getAttribute("accountSheng");
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
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
	String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
	String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
	String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"0";
	String zdida=request.getParameter("zdida")!=null?request.getParameter("zdida"):"";
	String shenhe=request.getParameter("shenhe")!=null?request.getParameter("shenhe"):"-1";
    String loginId1 = request.getParameter("loginId");
	int count=0;
    String permissionRights="";
    String color=null;
    String roleId = (String)session.getAttribute("accountRole");
    double ddf=0.0;
    int intnum=0;
%>

<html>
<head>
<title>

</title>
<style>
            
            .style1 {
	color: #FF9900;
	font-weight: bold;
}
 .style1 {
	color: red;
	font-weight: bold;
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.bttcn{color:BLACK;font-weight:bold;}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px

		}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script language="javascript">
	var path = '<%=path%>';  
    function queryDegree(){
              document.form1.action=path+"/web/zdqxkz/zdqxcxxg.jsp?command=chaxun";
              document.form1.submit();
    }
    
   	function zengjia(){
         document.form1.action=path+"/web/zdqxkz/zdgjxxxg.jsp";
         document.form1.submit();
    }
    
    $(function(){
		$("#chaxun").click(function(){
			queryDegree();
		});
		$("#zengjia").click(function(){
			zengjia();
			showdiv("请稍等..........");
		});
	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0804");
%>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		<tr>
			<td colspan="4">
			<div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">区县查询</span>	
			 </div>
		    </td>
			</tr>		
			<tr><td height="30" colspan="4">
	   				<div id="parent" style="display:inline">
	                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
	                </div>
		        </td>
		    </tr>
		    <tr><td height="8%" width="1200">
		    <table>
		    	<tr class="form_label">
		    	<td>城市：</td><td><select name="shi" class="selected_font" onchange="changeCity()">
         		<option value="0">请选择</option>
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
		    	<td>区县：</td><td><select name="xian" class="selected_font" onchange="changeCountry()">
         		<option value="0">请选择</option>
         		<%
		         	ArrayList xianlist = new ArrayList();
		         	xianlist = commBean.getAgcode(shi,account.getAccountId());
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
         		</select></td>
         			<td>乡镇：</td><td><select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
	         		<option value="0">请选择</option>
	         		<%
			         	ArrayList xiaoqulist = new ArrayList();
			         	xiaoqulist = commBean.getAgcode(xian,account.getAccountId());
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
	         		</select></td>
	         		<td>站点名称：</td>
	         		<td><input type="text" class="selected_font" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" /></td>
	         		<td>站点ID</td>
		    		<td><input type="text" class="selected_font" name="zdida" value="<%if(null!=request.getParameter("zdida")) out.print(request.getParameter("zdida")); %>" /></td>
 			<td> <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%> 
 					<%}%>
		        <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;right:-50px;float:right;top:0px">
				       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
				       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
				       
				</div></td>
		    	</tr>
		    	<tr class="form_label">
		    	<td>市级审核状态：</td>
         			<td><select name="shenhe"  class="selected_font">
         							<option value="-1">请选择</option>
         							<option value="0">未审核</option>
         							<option value="1">通过</option>
         							<option value="2">不通过</option>
         			</select></td>
		    	</tr>
		    </table>
		    </td></tr>
	</table>
	<table>
		<tr><td height="23" colspan="4"><div id="parent" style="display:inline">
              <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
        </div></td></tr>
	</table>
	<% 
	 		Zdqxcxxg bean=new Zdqxcxxg();
	
	        String whereStr = "";
        
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" AND ZD.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" AND ZD.XIAN='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
				whereStr=whereStr+" AND ZD.XIAOQU='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
				whereStr=whereStr+" AND ZD.JZNAME LIKE '%"+zdname+"%'";				
			}
			if(zdida != null && !zdida.equals("")){
				whereStr=whereStr+" AND ZD.ID = '"+zdida+"'";				
			}
			if(shenhe != null && shenhe != "" && !shenhe.equals("-1")){
				if(shenhe.equals("1")){				
					whereStr=whereStr+" AND (QX.SHISHBZ='"+shenhe+"' or QX.SHENGSHBZ='"+shenhe+"')";
				}else{
					whereStr=whereStr+" and qx.shishbz != '1' and qx.shengshbz != '1' AND ((QX.QXPDBZ='1' AND QX.SHISHBZ='"+shenhe+"') or (QX.QXPDBZ='2' AND QX.SHENGSHBZ='"+shenhe+"'))";
				}
			}
		%>
		
	
	<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
		<table width="100%" height="80%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
			<tr height = "23" class="relativeTag">
			 	 <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
				 <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
                 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">城市</div></td> 
                 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>                 
                 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">乡镇</div></td> 
                 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
     			 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>    
     			 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>              
                 <td width="8%" height="23" bgcolor="#DDDDDD" colspan="2"><div align="center" class="bttcn">操作</div></td>
                 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市审核不通过原因</div></td>
			     <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">标杆等级</div></td>    
     			 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">不通过原因</div></td>  
			</tr>
		<%
          List<Zdqxkz> fylist=null;
        String zdid="",zdnamea="",shi1="",xian1="",xiaoqu1="",dbid="";
        String id = "";
        String shishbz = "";
        String shengshbz = "";
        String sjly = "";
        String bgdj="",noreason="";//标杆等级  标杆降级
		if("chaxun".equals(request.getParameter("command"))){
		 fylist=bean.getZdqxcx(whereStr,loginId);
		
		if(fylist!=null){
		for(Zdqxkz ls:fylist){
           bgdj=ls.getBghs();
           if(null==bgdj||"null".equals(bgdj)){bgdj="";}
			noreason=ls.getNoreason();
			if(null==noreason||"null".equals(noreason)){noreason="";}
			dbid=ls.getDbid();
			if(null==dbid||"null".equals(dbid)){dbid="";}
			sjly=ls.getSjly();
			if(null==sjly||"null".equals(sjly)){sjly="";}
			
			shi1=ls.getShi();
			if(null==shi1||"null".equals(shi1)){shi1="";}

			xian1=ls.getXian();
			if(null==xian1||"null".equals(xian1)){xian1="";}

			xiaoqu1=ls.getXiaoqu();
			if(null==xiaoqu1||"null".equals(xiaoqu1)){xiaoqu1="";}
			
			zdnamea=ls.getZdname();
			if(null==zdnamea||"null".equals(zdnamea)){zdnamea="";}
					
			zdid = ls.getZdid();
			if(null==zdid||"null".equals(zdid)){zdid="";}
			
			id = ls.getId();
			if(null==id||"null".equals(id)){id="";}
			
			shishbz = ls.getShishbz();
			if(null==shishbz||"null".equals(shishbz)){shishbz="";}
			
			shengshbz = ls.getShengshbz();
			if(null==shengshbz||"null".equals(shengshbz)){shengshbz="";}
		
			if(intnum%2==0){
			    color="#FFFFFF";
			 }else{
			    color="#DDDDDD" ;
			}
	     intnum++;
       %>
      <% 
      if("2".equals(shishbz)){
      %>
       <tr bgcolor=yellow>
       <td>
       			<div align="center" ><%=intnum%></div>
       		</td>
       		<td>
              <div align="center"><input type="checkbox" name="test[]" value="<%=zdid%>" /></div>
            </td>
           <td>
       			<div align="center" ><%=shi1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=xian1%></div>
       		</td>
            <td>
       			<div align="center" ><%=xiaoqu1%></div>
       		</td>
       		<td>
       			<div align="center" ><a href="javascript:lookDetailss('<%=id%>')"><%=zdnamea%></a></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=zdid%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dbid%></div>
       		</td>
       	
       		<%if(shishbz=="1"||"1".equals(shishbz)||shengshbz=="1"||"1".equals(shengshbz)){ %>
	       		<td colspan="2">
	       			<div align="center" >已审核</div>
	       		</td>
       		<%}else{%>
	       		<td>
	       			<div align="center" ><a href="javascript:modifyzdxx('<%=id%>')">修改</a></div>
	       		</td>
	       		<td>
	       			<div align="center" ><a href="javascript:deletezdxx('<%=id%>')">删除</a></div>
	       		</td>
       		<%} %>
       		<td>
       			<div align="center" ><%=sjly%></div>
       		</td>
       			<td>
       			<div align="center" ><%=bgdj%></div>
       		</td>
       		<td>
       			<div align="center" ><%=noreason%></div>
       		</td>
       </tr>
     <%}else{%>
      <tr bgcolor=<%=color%>>
       <td>
       			<div align="center" ><%=intnum%></div>
       		</td>
       		<td>
              <div align="center"><input type="checkbox" name="test[]" value="<%=zdid%>" /></div>
            </td>
           <td>
       			<div align="center" ><%=shi1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=xian1%></div>
       		</td>
            <td>
       			<div align="center" ><%=xiaoqu1%></div>
       		</td>
       		<td>
       			<div align="center" ><a href="javascript:lookDetailss('<%=id%>')"><%=zdnamea%></a></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdid%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dbid%></div>
       		</td>
       		<%if(shishbz=="1"||"1".equals(shishbz)||shengshbz=="1"||"1".equals(shengshbz)){ %>
	       		<td colspan="2">
	       			<div align="center" >通过</div>
	       		</td>
       		<%}else{%>
	       		<td>
	       			<div align="center" ><a href="javascript:modifyzdxx('<%=id%>')">修改</a></div>
	       		</td>
	       		<td>
	       			<div align="center" ><a href="javascript:deletezdxx('<%=id%>')">删除</a></div>
	       		</td>
       		<%} %>
       		<td>
       			<div align="center" ><%=sjly%></div>
       		</td>
       			<td>
       			<div align="center" ><%=bgdj%></div>
       		</td>
       		<td>
       			<div align="center" ><%=noreason%></div>
       		</td>
       </tr>
     
     
     
 <%   } }}}%>
              <% if (intnum==0){
    	  System.out.println("QQQQ"+intnum);
         for(int i=0;i<15;i++){
          if(i%2==0){
			    color="#FFFFFF" ;
          }else{
			    color="#DDDDDD";
			}
         %>

        <tr bgcolor="<%=color%>">
             <td>&nbsp;</td>  
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            <td>&nbsp;</td>
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
               <td>&nbsp;</td>              
                  <td>&nbsp;</td> 
               <td>&nbsp;</td>   
          </tr>
      <% }}else if(!(intnum > 15)){
    	  for(int i=((intnum-1)%15);i<15;i++){
            if(i%2==0)
			    color="#DDDDDD";
            else
			    color="#FFFFFF" ;
        %>
        <tr bgcolor="<%=color%>">
             <td>&nbsp;</td>  
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            <td>&nbsp;</td>
            <td>&nbsp;</td> 
            <td>&nbsp;</td>
             <td>&nbsp;</td>
                <td>&nbsp;</td>  
                   <td>&nbsp;</td> 
               <td>&nbsp;</td>   
        </tr>
        <%}}%>	
	</table>
	</div>
	
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	               <tr>
	               <td align="right">
				             <div id="zengjia"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right:0px;top: 0px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">增加</span>
						</div>
	                </td>
	               </tr>
	</table>
	  		<input type="hidden" name="sheng2" id="sheng2" value=""/>
			<input type="hidden" name="shi2" id="shi2" value=""/>
			<input type="hidden" name="xian2" id="xian2" value=""/>
			<input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
	</form>
</body>
<script language="javascript">
	var path = '<%=path%>';
 	function lookDetailss(id){ 
	  window.open(path+"/web/zdqxkz/shenheMessage.jsp?zdid="+id,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
    }
    function deletezdxx(zdid){
       if(confirm("您确定删除此电费信息？")){
            document.form1.action=path+"/servlet/zdqxcxxg?action=deleteqx&zdid="+zdid;
   			document.form1.submit();
   			showdiv("请稍等..........");
       }
    }
    function modifyzdxx(id){
             document.form1.action=path+"/web/zdqxkz/modifyzdgjxxxgxx.jsp?id="+id;
             document.form1.submit();
             showdiv("请稍等..........");       
    }
</script>
<script type="text/javascript">
<!--
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
	document.form1.sheng2.value=document.form1.sheng.value;
	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeCity(){
	var sid = document.form1.shi.value;
	document.form1.shi2.value=document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function changeCountry(){
	var sid = document.form1.xian.value;
	document.form1.xian2.value=document.form1.xian.value;
	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

//-->
</script>
<script type="text/javascript">
	var XMLHttpReq1;
	function createXMLHttpRequest1() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq1 = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq1 = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq1 = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	function sendRequest1(url,para) {

		createXMLHttpRequest1();
		XMLHttpReq1.open("POST", url, true);
		if(para=="checkcity1"){
			XMLHttpReq1.onreadystatechange = processResponse_checkcity1;
		}else if(para=="checkcityno1"){
			XMLHttpReq1.onreadystatechange = processResponse_checkcityno1;
		}else{
			XMLHttpReq1.onreadystatechange = processResponse;//指定响应函数
		}
		XMLHttpReq1.send(null);  
	}
	// 处理返回信息函数
    function processResponse() {
    	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
              var res = XMLHttpReq1.responseText;
              window.alert(res);
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
	function passCheck1(chooseIdStr1,chooseIdStr2){
			sendRequest1(path+"/servlet/check?action=checkcity1&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2,"checkcity1");
	}
	function processResponse_checkcity1() {	
		if (XMLHttpReq1.readyState == 4) { // 判断对象状态
	    	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
	    	document.form1.bzw1.value= XMLHttpReq1.responseText;
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
	function passCheckNo1(chooseIdStr1,chooseIdStr2){
	alert(chooseIdStr1+" "+chooseIdStr2);
	window.open(path+"/web/jzcbnewfunction/showzgpl.jsp?zdid="+chooseIdStr1+"&cbyf="+chooseIdStr2,'','width=1230,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
		//  sendRequest1(path+"/servlet/check?action=checkcityno1&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2,"checkcityno1");
	}
	
	function processResponse_checkcityno1() {	
		if (XMLHttpReq1.readyState == 4) { // 判断对象状态
	    	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
	        	document.form1.bzw1.value = XMLHttpReq1.responseText;
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
</script>
<script type="text/javascript">
function passCheckNo(){
        var m = document.getElementsByName('test[]');      
        var l = m.length; 
        var chooseIdStr1 = ""; 
        var chooseIdStr2 = ""; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var count2=0;
        var bzw=1;
        var bzw1="";
        var c=0;
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       		
       	}
       	if(bzw=="1"&&count>1){
		        for (var i = 0; i < l; i++) {  
		          if(m[i].checked == true){
		             bz+=1;
	                 count1+=1;
		            
		             var chooseIdStr3 = m[i].value.toString();
		             chooseIdStr1 = chooseIdStr1 +"'" + chooseIdStr3 +"',";
		          }
		      if(bzw=="1"){
		         if(count==count1&&bzw==1){
			          chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
				      window.open(path+"/web/jzcbnewfunction/showzgpl.jsp?idad="+chooseIdStr1,'','width=1230,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
				      bzw=0;
			     
			  }else if(bzw1=="0"){
				document.form1.action=path+"/web/msg.jsp";
				document.form1.submit(); 
				return;  
			  }            
	        } 
	        }
      }else if(count==1||c==1){
      	alert("批量整改需要两条及两条以上信息！");
      return;
      }else{
        alert("请选择信息！");
        return;
      }
   }
 
 </script>
  <!--多选框选择 -->
 <script type="text/javascript">
 function chooseAll() { 
        var qm = document.getElementsByName('test');
        var m = document.getElementsByName('test[]');   
        var l = m.length; 
        if(qm[0].checked == true){
          for (var i = 0; i < l; i++) {   
            m[i].checked = true;   
          }  
        }else{
          for (var i = 0; i < l; i++) {   
            m[i].checked = false;  
          }   
        }        
    }   
    
    	document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.shenhe.value='<%=shenhe%>';
		
 </script>
</html>

