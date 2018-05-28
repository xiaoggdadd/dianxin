<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.noki.jizhan.SiteAnalysisSheet,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%
String path = request.getContextPath();
String shengId = (String)session.getAttribute("accountSheng");
String loginName = (String)session.getAttribute("loginName");
Account account=(Account)session.getAttribute("account");
String gsf = request.getParameter("gsf")!=null?request.getParameter("gsf"):"0";
String dfzflx=request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";
String bgsign = request.getParameter("bgsign")!=null?request.getParameter("bgsign"):"-1";
String caiji=request.getParameter("caiji")!=null?request.getParameter("caiji"):"-1";
String loginId=account.getAccountId();
String agcode1="";
if(request.getParameter("shi")==null){
	ArrayList shilist = new ArrayList();
	CommonBean commBean = new CommonBean();
	shilist = commBean.getAgcode(shengId,account.getAccountId());
	if(shilist!=null){
    	int scount = ((Integer)shilist.get(0)).intValue();
        agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
    }
}
String shi=request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
String xian=request.getParameter("xian")!=null?request.getParameter("xian"):"-1";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
  <head>
	<script src="<%=path%>/javascript/tx.js"></script>
	<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
	<style type="text/css">
		select{
			width:90px;
			font-family: 宋体;
			font-size: 12px;
		}
		.form_label{
			
			font-family: 宋体;
			font-size: 12px;
			height:23px
		}
		.sel{
		width:130px;
		 
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
		}
		.seperator{
			width:100%;
			margin-bottom: -20px;
		}
		.seperator .first{
			width:5%;
			position: relative;
			float: left;
		}
		.seperator span{
			position: relative;
			float:left;
			font-family: 宋体;
			font-size: 14px;
		}
		.seperator .second{
			width:30%;
			position: relative;
		}
		.form{
			width:90px;
			height: 18px;
	
		}
	</style>  
  </head>
    <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
  <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
  <body>
  	<form action="" name="form1">
  	     <table width="100%"  border="0" cellspacing="1" cellpadding="1"  >
				   <tr >
					    	<td colspan="4" width="50" >
											 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点分析表</span>	
												</div>
											</td>
	    	        </tr>	    	
				 	<tr><td height="20" colspan="4" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td>
	    	</tr>
				 	<tr >
	  			<td width="1200px">
	  			<table>
	  			<tr class="form_label">
	  			<td>城市:</td>
	  			<td>
		  			<select class="sel" name="shi" onchange="changeCity()">
		         		<option value="-1">请选择</option>
		         		<%
			         	ArrayList<?> shenglist = new ArrayList<String>();
			         	shenglist = commBean.getAgcode(shengId,"0",loginName);
			         	if(shenglist!=null){
			         		String sfid="",sfnm="";
			         		int scount = ((Integer)shenglist.get(0)).intValue();
			         		for(int i=scount;i<shenglist.size()-1;i+=scount)
		                    {
		                    	sfid=(String)shenglist.get(i+shenglist.indexOf("AGCODE"));
		                    	sfnm=(String)shenglist.get(i+shenglist.indexOf("AGNAME"));
		                    %>
		                    <option value="<%=sfid%>"><%=sfnm%></option>
		                    <%}
			         	}
			         %>
		         	</select>
	         	</td>
		        
<td>区县：</td>
		  <td><select  name="xian" class="sel" onchange="changeCountry()">
		         		<option value="-1">请选择</option>
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
		         	</select>
		        </td>	
		     
      
                   	<td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p>
				</td>
	         		<td >
							       <div id="query2" style="position:relative;width:59px;height:23px;right:-415px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
							</td>
                    </tr>
                    </table>
                    </td>
                    </tr>
  		
  		  <tr >
			     <td colspan="5">
					<div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table>
					<tr class="form_label">
		  <td>归属方：</td>
		       <td><select name="gsf" class="sel" >
								         		<option value="0">请选择</option>
								         		<%
									         	ArrayList gsflist = new ArrayList();
								         		gsflist = ztcommon.getSelctOptions("gsf");
									         	if(gsflist!=null){
									         		String code="",name="";
									         		int cscount = ((Integer)gsflist.get(0)).intValue();
									         		for(int i=cscount;i<gsflist.size()-1;i+=cscount)
								                    {
								                    	code=(String)gsflist.get(i+gsflist.indexOf("CODE"));
								                    	name=(String)gsflist.get(i+gsflist.indexOf("NAME"));
								                    %>
								                    <option value="<%=code%>"><%=name%></option>
								                    <%}
									         	}
									         %>
						       </select>
							</td>
		         	
		         	<td>是否标杆：</td>
		         	<td> <select name="bgsign" class="sel" onchange="javascript:document.form1.bgsign2.value=document.form1.bgsign.value">
							         		<option value="-1">请选择</option>
							         		<option value="0">否</option>
							         		<option value="1">是</option>
							         		
							      </select></td>
                     <td>是否采集：</td>
                    <td>
                    <select name="caiji" class="sel" onchange="javascript:document.form1.caiji2.value=document.form1.caiji.value">
							         		<option value="-1">请选择</option>
							         		<option value="0">否</option>
							         		<option value="1">是</option>
							         		
							      </select>
                    
                    </td>
                    <td>电费支付类型：</td>
                    <td>
                    <select name="dfzflx" class="sel">
				         		<option value="0">请选择</option>
				         		<%
					         	ArrayList dfzflxist = new ArrayList();
					         	dfzflxist = ztcommon.getSelctOptions("DFZFLX");
					         	if(dfzflxist!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)dfzflxist.get(0)).intValue();
					         		for(int i=cscount;i<dfzflxist.size()-1;i+=cscount)
				                    {
				                    	code=(String)dfzflxist.get(i+dfzflxist.indexOf("CODE"));
				                    	name=(String)dfzflxist.get(i+dfzflxist.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         %>
				         	
				         	</select>
                    </td>
                    </tr>
	        </table>
	      </p>
	      </div>
	      </td>
	      </tr>
	      </table>
	   <table  height="23">
<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;详细信息&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>     
	        <%
	        String whereStr="";
	        if(null !=shi  && !shi.equals("") && !shi.equals("-1")){
				whereStr=whereStr+"and z.shi='"+shi+"'";
			}
			if(null != xian && !xian.equals("") && !xian.equals("-1")){
				
				whereStr=whereStr+"and z.xian='"+xian+"'";
			}
			if(null != gsf && !gsf.equals("") && !gsf.equals("0")){
				
				whereStr=whereStr+"and z.gsf='"+gsf+"'";
			}
			if(null != bgsign && !bgsign.equals("") && !bgsign.equals("-1")){
				
				whereStr=whereStr+"and z.bgsign='"+bgsign+"'";
			}
			if(null != caiji && !caiji.equals("") && !caiji.equals("-1")){
				
				whereStr=whereStr+"and z.caiji='"+caiji+"'";
			}
			if(null != dfzflx && !dfzflx.equals("") && !dfzflx.equals("0")){
				
				whereStr=whereStr+"and D.DFZFLX='"+dfzflx+"'";
			}
			SiteAnalysisSheet bean=new SiteAnalysisSheet();
			String str1=" and z.property='zdsx01'";
			String str2="  and z.property='zdsx02'";
			String str3=" and z.property='zdsx05'";
			String count1=bean.getCount(whereStr,loginId,str1);
			String count2=bean.getCount(whereStr,loginId,str2);
			String count3=bean.getCount(whereStr,loginId,str3);
			System.out.println("--"+whereStr);
	        %>
	        <table height="5%">
	        <tr>
	          <td><font size="2"><a href="javascript:selectTree('zdsx01','<%=shi%>','<%=xian%>','<%=gsf%>','<%=bgsign%>','<%=caiji%>','<%=dfzflx%>')">机房</a></font></td>
	          <td ><font size="2" color="red"><%= count1%></font><font size="2">个站点|</font></td>
	          <td><font size="2"><a href="javascript:selectTree('zdsx02','<%=shi%>','<%=xian%>','<%=gsf%>','<%=bgsign%>','<%=caiji%>','<%=dfzflx%>')">基站</a> </font></td>
	          <td ><font size="2" color="red"><%= count2%></font><font size="2">个站点|</font></td>
	          <td ><font size="2"><a href="javascript:selectTree('zdsx05','<%=shi%>','<%=xian%>','<%=gsf%>','<%=bgsign%>','<%=caiji%>','<%=dfzflx%>')">接入网</a></font> </td>
	          <td><font size="2" color="red"><%= count3%></font><font size="2">个站点|</font></td>
	        </tr>
	         <tr>
			  <td>
				  <input type="hidden" name="sheng2" id="sheng2" value=""/>
				  <input type="hidden" name="shi2" id="shi2" value=""/>
				  <input type="hidden" name="xian2" id="xian2" value=""/>
				  </td>
			  </tr>
	     </table>
  	<iframe name="treeMap" width="200px" height="400px" frameborder="0" src="<%=path %>/web/jizhan/siteAnalysisSheetleft.jsp"></iframe>
  	<span style="width:20px"></span>
    <iframe name="treeNodeInfo" width="450px" height="400px" frameborder="0" src="<%=path %>/web/jizhan/siteAnalysisSheetright.jsp"></iframe>
    <span style="width:20px"></span>
    <iframe name="treeInfo" width="300px" height="400px" frameborder="0" src="<%=path %>/web/jizhan/siteAnalysisSheetright1.jsp"></iframe>
 </form>
  </body>
  <script type="text/javascript">
		var path = '<%=path%>';
		function changeCity1(){
			var sid = document.form1.shi.value;
			if(sid=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选择","-1"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=shi&pid="+sid,"shi");
			}
		}
		function updateQx1(res){
			var shilist = document.all.xian;
			shilist.options.length="0";
			shilist.add(new Option("请选择","-1"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		
		function selectTree(zdsx,shi,xian,gsf,bgsign,caiji,dfzflx){
			var str=zdsx;
			var shi=shi;
			var b=path+"/web/jizhan/siteAnalysisSheetleft.jsp?str="+str+"&shi="+shi+"&xian="+xian+"&gsf="+gsf+"&bgsign="+bgsign+"&caiji="+caiji+"&dfzflx="+dfzflx;			
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
			
		}
		function query(){
			 document.form1.action=path+"/web/jizhan/siteAnalysisSheet.jsp";
             document.form1.submit();
		}
		$("#query2").click(function(){
			
			query();
			
		});
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.gsf.value='<%=gsf%>';
		document.form1.bgsign.value='<%=bgsign%>';
		document.form1.caiji.value='<%=caiji%>';
		document.form1.dfzflx.value='<%=dfzflx%>';
		
	</script>
<script type="text/javascript">
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
              updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
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
}
</script>
</html>
			