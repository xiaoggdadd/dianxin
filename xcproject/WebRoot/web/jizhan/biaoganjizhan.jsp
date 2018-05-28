﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>

<%
	String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId=account.getAccountId();
	String jztype1 = request.getParameter("jztype")!=null?request.getParameter("jztype"):"0";
    String jzproperty1 = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
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
	String sname = request.getParameter("sname")!=null?request.getParameter("sname"):"";
	String szdcode = request.getParameter("szdcode")!=null?request.getParameter("szdcode"):"";
	String caiji=request.getParameter("caiji")!=null?request.getParameter("caiji"):"-1";
	String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String loginName = (String)session.getAttribute("loginName");
    String roles=(String)session.getAttribute("accountRole");
	String allids="";
    String roleId = (String)session.getAttribute("accountRole");
    String loginId1 = request.getParameter("loginId");
    String permissionRights="";
    String color=null;
    int intnum=0;
%>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0103");
%>

<html>
<head>
<title>
logMange
</title>
<style>
 .bttcn{color:black;font-weight:bold;}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			

		}
		.style1 {
	color: red;
	font-weight: bold;
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
 <script type="text/javascript">
//=点击展开关闭效果=

function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
var openTip = oOpenTip || "";
var shutTip = oShutTip || "";
if(targetObj.style.display!="none"){
   if(shutAble) return;
   targetObj.style.display="none";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = shutTip; 
   }
} else {
   targetObj.style.display="block";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = openTip; 
   }
}
}
</script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <jsp:include page="/web/prePrint.jsp"></jsp:include>
<script >

var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();


var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init();
</script>
<script language="javascript">
var path = '<%=path%>';
function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

		}
		else
		{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
}

  function chaxun(){
	               if(document.form1.shi.value==null||document.form1.shi.value==""||document.form1.shi.value=="0"){
	            	   alert("请选择城市");
	              }else{
					document.form1.action=path+"/web/jizhan/biaoganjizhan.jsp?command=chaxun";
					document.form1.submit();
					}
	}
	function delLogs(){
		

	}
	function addJz(){
		document.form1.action=path+"/web/jizhan/addjz.jsp";
		document.form1.submit();
	}
	function daorujz(){
		document.form1.action=path+"/web/jizhan/zhandiandaoru.jsp";
		document.form1.submit();
	}
	$(function(){

		$("#query").click(function(){
			chaxun();
		});
		$("#saveBtn").click(function(){
			baocunbg();
		});
		$("#dayinBtn").click(function(){
			dayinpage('标杆站点列表')
		});


	});

</script>

</head>

<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
			<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="5%">
		<tr>
		<td colspan="4">
			  <div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">标杆站点</span>	
			  </div>
	    </td>
		</tr>		
		<tr><td height="15" colspan="4">
   				<div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	        </td>
	    </tr>
	    <tr><td>
	    	<table border="0">
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
         		</select><span class="style1">&nbsp;*</span></td>
         						
         		<td>区县:</td><td> <select name="xian" class="selected_font" onchange="changeCountry()">
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
				<td>乡镇：</td><td><select name="xiaoqu" id="xiaoqu" style="width:130" class="selected_font" >
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
         			<td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p></td>
			 <td><%
					if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){
				%>
				<div id="query" style="position:relative;width:60px;height:23px;cursor: pointer;right:-220px;float:right;top:0;">
					<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
					<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
				</div>

         		<%}%>  				
			</td>
			</tr>
	</table>
	</td></tr>	
 </table>
  <div style="width:88%;" > 
		<p id="box3" style="display:none">
			<table>
			<tr class="form_label">
  			<td>集团报表类型：</td>
         	<td><select name="jztype" style="width:130" class="selected_font" onchange="kzinfo()">
	         	<option value="0">请选择</option>
	         		<%
		         	ArrayList zdtype = new ArrayList();
		         	zdtype = ztcommon.getSelctOptions("zdlx");
		         	if(zdtype!=null){
		         		String code="",name="";
		         		int cscount = ((Integer)zdtype.get(0)).intValue();
		         		for(int i=cscount;i<zdtype.size()-1;i+=cscount)
	                    {
	                    	code=(String)zdtype.get(i+zdtype.indexOf("CODE"));
	                    	name=(String)zdtype.get(i+zdtype.indexOf("NAME"));
	                    %>
	                    <option value="<%=code%>"><%=name%></option>
	                    <%}
		         	}
		         %>
         	</select></td>
         	<td>站点属性：</td>
         	<td><select name="jzproperty" class="selected_font" onchange="kzinfo()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = ztcommon.getSelctOptions("zdsx");
	         	if(zdsx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdsx.get(0)).intValue();
	         		for(int i=cscount;i<zdsx.size()-1;i+=cscount)
                    {
                    	code=(String)zdsx.get(i+zdsx.indexOf("CODE"));
                    	name=(String)zdsx.get(i+zdsx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select></td>
         <td>站点名称：</td>
         <td><input type="text" name="sname" value="<%=sname%>" class="selected_font"/></td>
         <td>站点代码：</td>
         <td><input type="text" name="szdcode" value="<%=szdcode%>" class="selected_font"/></td>
     </tr>
     <tr class="form_label">
         <td>是否采集：</td>
       	 <td><select name="caiji" class="selected_font">
       		<option value="-1">请选择</option>
       		<option value="1">是</option>
       		<option value="0">否</option>
        </select></td>
		     </tr>
		  </table>
		  	</p>
	</div>
  	  	<table>
		     <tr><td height="15" colspan="4"><div id="parent" style="display:inline">
             <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
             </div></td></tr>       	
	    </table>
	    <%
	    if(loginId1!=null&&!loginId1.equals("")){
      	    loginId=loginId1;
      	 	shi="1";
      	 }
		if("chaxun".equals(request.getParameter("command"))){
	   		JiZhanBean bean1 = new JiZhanBean();		
			String count1=bean1.getCountt1(curpage,sheng,shi,xian,xiaoqu,sname,szdcode,loginName,roles,loginId,jztype1,jzproperty1,caiji);
	    %>
	    
	    
		<table  height="4%">
          <tr>
  	        <td><font size="2">总共</font></td><td><font size="2" color="red"><%=count1%></font><font size="2">条  </font></td>
  	      </tr>
		</table>
		<%}%>
			 <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
				<table width="100%" height="80%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height = "23" class="relativeTag" bgcolor="#DDDDDD">
    					<td width="5%" height="23" ><div align="center" class="bttcn"><input type="checkbox" name="pageCheck" onClick="checkPage()" /></div></td>
                        <td width="12%" height="23"><div align="center" class="bttcn">站点名称</div></td>
                        <td width="10%" height="23"><div align="center" class="bttcn">所在地区</div></td>
						<td width="8%" height="23"><div align="center" class="bttcn">站点属性</div></td>
						<td width="8%" height="23"><div align="center" class="bttcn">集团报表类型</div></td>
						<td width="8%" height="23"><div align="center" class="bttcn">用房类型</div></td>
		   				<td width="8%" height="23"><div align="center" class="bttcn">节能设备</div></td>
		   				<td width="8%" height="23"><div align="center" class="bttcn">站点面积</div></td>
                        <td width="8%" height="23"><div align="center" class="bttcn">单价</div></td>
                        <td width="8%" height="23"><div align="center" class="bttcn">供电方式</div> </td>
                        <td width="8%" height="23" ><div align="center" class="bttcn">站点代码</div></td>
                        <td width="8%" height="23"><div align="center" class="bttcn">详细信息</div></td>
                  </tr>
       <%
       JiZhanBean bean = new JiZhanBean();
       String bgidstr="";
       	 ArrayList fylist = new ArrayList();
       	 if("chaxun".equals(request.getParameter("command"))){
       	 fylist = bean.getPageData_bg(curpage,sheng,shi,xian,xiaoqu,sname,szdcode,loginName,roles,loginId,jztype1,jzproperty1,caiji);
       	 allpage=bean.getAllPage();
		String jzname = "",szdq = "",jzproperty = "", jztype= "",yflx = "",jnglmk="",gdfs="",area="",id="",dianfei="",zdcode="",bgsign="";
		
		//System.out.println(fylist);
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		        id = (String)fylist.get(k+fylist.indexOf("ID"));
				jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
			    szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
			    jzproperty = (String)fylist.get(k+fylist.indexOf("PROPERTY"));
			    jztype = (String)fylist.get(k+fylist.indexOf("JZTYPE"));
				yflx = (String)fylist.get(k+fylist.indexOf("YFLX"));
				jnglmk = (String)fylist.get(k+fylist.indexOf("JNGLMK"));
				if(jnglmk.equals("0")){
					 jnglmk="否";
					}else if(jnglmk.equals("1")){
					jnglmk="是";
					}
				gdfs = (String)fylist.get(k+fylist.indexOf("GDFS"));
				area = (String)fylist.get(k+fylist.indexOf("AREA"));
				dianfei = (String)fylist.get(k+fylist.indexOf("DIANFEI"));
				zdcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
				bgsign = (String)fylist.get(k+fylist.indexOf("BGSIGN"));
				if(area==null||area.equals("null"))area="";
				if(dianfei==null||dianfei.equals("null"))dianfei="";
			

			if(intnum++%2==0){
			    color="#FFFFFF" ;
			 }else{
				color="#DDDDDD";
			}
			if(bgsign.equals("1")){
			color="#00AA00";
			bgidstr+=id+",";
			}
			if(k== fycount){
				allids=id;
				}else{
					allids+=","+id;
			}
       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><input type="checkbox" name="itemSelected" value="<%=id%>" <%if(bgsign.equals("1")){%>checked="checked" <%}%>/></div>
       		</td>
       		
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="left" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jzproperty%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jztype%></div>
       		</td>
       		<td>
       			<div align="center" ><%=yflx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jnglmk%></div>
       		</td>
       		<td>
       			<div align="right" ><%=area%></div>
       		</td>
        	<td>
       			<div align="right" ><%=dianfei%></div>
       		</td>
       		<td>
       			<div align="center" ><%=gdfs%></div>
       		</td>
       		
       		<td>
       			<div align="left" ><%=zdcode%></div>
       		</td>
       		<td>
       			<div align="center" ><a href="#" onclick="modifyjz('<%=id%>')">查看</a></div>
       		</td>
       		

       </tr>
       
       <%} %>
        <%} %>
       
       <%}%>
        <% if (intnum==0){
    	  System.out.println("QQQQ"+intnum);
         for(int i=0;i<17;i++){
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
                   
        </tr> 
        <% }}else if(!(intnum > 16)){
    	  for(int i=((intnum-1)%16);i<16;i++){
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
        </tr>  
       <%} %>
       
       <%}%>
  	 </table> 
         
  	 </div>
		
		
		
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  			 <tr bgcolor="F9F9F9">
                      <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>
                    <tr>
                           <td>         
                            <%
								if(permissionRights.indexOf("PERMISSION_EDIT")>=0){
							%>
                               <div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
									<img src="<%=path %>/images/baocun.png" width="100%" height="100%">
									<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span></div>	  		
							 <%}%> 
                               
                              <div id="dayinBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:15px">
							 		<img src="<%=path %>/images/dayin.png" width="100%" height="100%">
							 		<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">打印</span>      
									
								</div>
                                                       
                              
                           </td>
                           </tr>
</table>


		

  	 
											
<input type="hidden" name="bgidstr" value="<%=bgidstr%>"/>
<input type="hidden" name="allids" value="<%=allids%>"/>
</form>
</body>
</html>
<script language="javascript">
	var path = '<%=path%>';

     function modifyjz(id){
     		document.form1.action=path+"/web/jizhan/zhandianinfo.jsp?id="+id
      document.form1.submit();
    }
    function checkPage(){
         	if(document.form1.pageCheck.checked){
         		for(var j = 0;j < document.form1.elements.length ; j++){
							document.form1.elements[j].checked=true
						}
         	}else{
         		for(var j = 0;j < document.form1.elements.length ; j++){
							document.form1.elements[j].checked=false
						}
         	}
        }
   function baocunbg(){

   	document.form1.action=path+"/servlet/zhandian?action=baocunbg"
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
		shilist.add(new Option("请选项","0"));
		return;
	}else{
	 //alert("11111");
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

		function dayinpage(title) {
//			var column = "all";//打印所有列
			var column = [1,2,3,4,5,6,7,8,9,10];//定义表格要打印的列，下标从0开始
			var style="<style>table {border-collapse:collapse;border-style:double;border-width:3px;border-color:black;width:720px;font-size:14}</style>";//定义表格样式
			 
			create(title,setColumn(column,style));//创建打印样式
		};
		function setLayout(title,tableHTML){
			LODOP.SET_PRINT_STYLE("FontSize",18);
			LODOP.ADD_PRINT_TEXT(50,320,200,30,title);
			LODOP.SET_PRINT_STYLEA(0,"ItemType",1);	
			LODOP.ADD_PRINT_TABLE(100,30,"95%","80%",tableHTML); 
			LODOP.NewPageA();
			
			LODOP.SET_PRINT_STYLE("FontSize",9);
			LODOP.ADD_PRINT_TEXT(8,653,135,20,"总页号：第#页/共&页");
			LODOP.SET_PRINT_STYLEA(0,"ItemType",2);
			LODOP.SET_PRINT_STYLEA(0,"Horient",1);	
			LODOP.ADD_PRINT_TEXT(8,34,196,20,title);
			LODOP.SET_PRINT_STYLEA(0,"ItemType",1);	
			LODOP.ADD_PRINT_TEXT("95%","90%",135,20,"<%=account.getAccountName()%>");
			LODOP.SET_PRINT_STYLEA(0,"ItemType",1);	
		}
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
document.form1.jztype.value='<%=jztype1%>';
document.form1.jzproperty.value='<%=jzproperty1%>';
document.form1.caiji.value='<%=caiji%>';
     </script>

