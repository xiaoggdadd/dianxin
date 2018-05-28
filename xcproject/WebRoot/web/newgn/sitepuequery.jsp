<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.noki.util.CTime,com.noki.function.SitePueQuery,com.noki.function.SitePueQueryBean,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.text.*"%>
<%
String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
int intnum=0;
String dbyt = "dbyt01";
String color="";
 String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
 String jzproperty1 = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
 String kongtiao1 = request.getParameter("kongtiao1")!=null?request.getParameter("kongtiao1"):"-1";
 String kongtiao2 = request.getParameter("kongtiao2")!=null?request.getParameter("kongtiao2"):"-1";
 String accountmonth=request.getParameter("accountmonth")!=null?request.getParameter("accountmonth"):"";
 String pue=request.getParameter("pue")!=null?request.getParameter("pue"):"";
 String chaobiao=request.getParameter("chaobiao")!=null?request.getParameter("chaobiao"):"";
 String yzchaobiao=request.getParameter("yzchaobiao")!=null?request.getParameter("yzchaobiao"):"";
 String yhdld=request.getParameter("yhdld")!=null?request.getParameter("yhdld"):"";
 String yhdlx=request.getParameter("yhdlx")!=null?request.getParameter("yhdlx"):"";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sheng = (String)session.getAttribute("accountSheng");
	
		String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
String loginName = (String)session.getAttribute("loginName");
String yuefen=request.getParameter("yue")!=null?request.getParameter("yue"):"";
int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
      String permissionRights="";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stationfees.jsp' starting page</title>
<style type="text/css">
   .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
    }.style1 {
	color: red;
	font-weight: bold;
}
.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}.bttcn{color:BLACK;font-weight:bold;}
 .selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
</style>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript">
var path = '<%=path%>';
	var oCalendarEnny=new PopupCalendarny("oCalendarEnny");	//初始化控件时,请给出实例名称如:oCalendarEn
	oCalendarEnny.Init();
	 
	var oCalendarChsny=new PopupCalendarny("oCalendarChsny");	//初始化控件时,请给出实例名称:oCalendarChs
	oCalendarChsny.weekDaySting=new Array("日","一","二","三","四","五","六");
	oCalendarChsny.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
	oCalendarChsny.oBtnTodayTitle="确定";
	oCalendarChsny.oBtnCancelTitle="取消";
	oCalendarChsny.Init();
	
	 function queryDegree(){	 
			 if(document.getElementById("accountmonth").value==""||document.getElementById("accountmonth").value==null)
		{
	                 alert("报账月份不能为空 ");
	   
	   }else if(document.getElementById("pue").value==""||document.getElementById("pue").value==null){
		   alert("PUE基准值不能为空 ");
	   }else if(document.getElementById("chaobiao").value==""||document.getElementById("chaobiao").value==null){
		   alert("超pue百分比不能为空 ");
	   }else if(document.getElementById("yzchaobiao").value==""||document.getElementById("yzchaobiao").value==null){
		   alert("严重超pue百分比不能为空 ");
	   }else if(document.getElementById("yhdld").value==""||document.getElementById("yhdld").value==null){
		   alert("月设备耗电量范围不能为空 ");
	   }else if(document.getElementById("yhdlx").value==""||document.getElementById("yhdlx").value==null){
		   alert("月设备耗电量范围不能为空 ");
	   }else{
	   		var pue=document.form1.pue.value;
	   		var chaobiao=document.form1.chaobiao.value;
	   		var yzchaobiao=document.form1.yzchaobiao.value;
	   		var yhdld=document.form1.yhdld.value;
	   		var yhdlx=document.form1.yhdlx.value;
	   		
	   		    //var pattern = /(^[0-9]\d+$)|(^\d+\.?\d*$)/;
                // var re= /^[1-9]\d*\.?\d*$/;"?[0-9]*.?[0-9]*" "\\d+([.]\\d+)?"
                //!"NaN".eval(Number(blhd).toString())
                var re=/(^\d+([.]\d+$))|(^\d+$)/;
      if(re.exec(yhdlx)){
    	  if(re.exec(yhdld)){ 
    		  if(re.exec(pue)){
    			  if(re.exec(chaobiao)){
    				   if(re.exec(yzchaobiao)){
    					    document.form1.action=path+"/web/newgn/sitepuequery.jsp";                       
                 document.form1.submit();
    				   }else{
                  alert("您输入的严重超pue百分比有误，请确认后重新输入！");
                }
    			  }else{
                	  alert("您输入的超pue百分比有误，请确认后重新输入！");
                  }
    		  }else{
                  alert("您输入的PUE基准值有误，请确认后重新输入！");
                }
    	  }else{
                  alert("您输入的月设备耗电量范围有误，请确认后重新输入！");
                }
      }
      else{
              alert("您输入的月设备耗电量范围有误，请确认后重新输入！");
           }
      }
			 }
	  $(function(){
		$("#chaxun").click(function(){
			queryDegree();
		});
	});
	  function changeCity(){
	var sid = document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}
}
	
	
		
</script>

  </head>
 <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean> 
 <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
  <body>
  <form action="" name="form1" method="POST">
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">					
	<tr>
<td colspan="4" width="50" >
											 <div style="width:700px;height:50px">
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点PUE查询</span>	
												</div></td>
 </tr>	
	<tr><td height="20" colspan="4" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td>
	    	</tr>
	    	<tr>
	    	<td width="1200px">
	    	<table>
	    	<tr class="form_label">
	    	 <td>月设备耗电量大于： </td>
                             <td><input  type="text" name="yhdld" class="selected_font" value="<% if (null != request.getParameter("yhdld"))out.print(request.getParameter("yhdld"));%>"/>度<span class="style1">&nbsp;*</span></td>        
                               <td>小于等于： </td>
                             <td><input  type="text" name="yhdlx" class="selected_font" value="<% if (null != request.getParameter("yhdlx"))out.print(request.getParameter("yhdlx"));%>"/>度<span class="style1">&nbsp;*</span></td>       
 	
	         		  <td> 报账月份：</td>
		               <td><input type="text" class="selected_font" name="accountmonth" value="<%if (null != request.getParameter("accountmonth"))out.print(request.getParameter("accountmonth"));%>" onFocus="getDatenyString(this,oCalendarChsny)" onpropertychange="endmonthzq()"  class="form" /><span class="style1">&nbsp;*</span></td>
                    <td>PUE基准值： </td>
                        <td><input  type="text" name="pue" class="selected_font" value="<% if (null != request.getParameter("pue"))out.print(request.getParameter("pue"));%>"/><span class="style1">&nbsp;*</span></td>        
                        </tr>
						<tr class="form_label">
						  <td>超pue百分比： </td>
                             <td><input  type="text" name="chaobiao" class="selected_font" value="<% if (null != request.getParameter("chaobiao"))out.print(request.getParameter("chaobiao"));%>"/>%<span class="style1">&nbsp;*</span></td>        
                               <td>严重超pue百分比： </td>
                             <td><input  type="text" name="yzchaobiao" class="selected_font" value="<% if (null != request.getParameter("yzchaobiao"))out.print(request.getParameter("yzchaobiao"));%>"/>%<span class="style1">&nbsp;*</span></td>        
						
			 <td>站点类型：</td><td><select name="zdlx" class="selected_font"/> 
							          <option class="form_label" value="0">请选择</option>
							          <%
								         	ArrayList stationtype = new ArrayList();
							         		stationtype = ztcommon.getSelctOptions("StationType");
								         	if(stationtype!=null){
								         		String code="",name="";
								         		int cscount = ((Integer)stationtype.get(0)).intValue();
								         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
							                    {
							                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
							                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
							                    %>
							                    <option value="<%=code%>"><%=name%></option>
							                    <%}
								         	}
								         %>
                                </select></td>
            <td>站点属性：</td>
         	<td><select name="jzproperty" style="width:130" onchange="kzinfo()" class="selected_font">
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
   	 						<td >
							       <div id="chaxun" style="position:relative;width:59px;height:23px;right:-30px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
							</td>
   </tr>
   <tr class="form_label">
					<td >城市：</td>
		    		
		    		<td ><select name="shi" class="selected_font" onchange="changeCity()">
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
	         		<td>区县：</td><td> <select name="xian" class="selected_font" onchange="changeCountry()">
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
	        <td>空调1：</td>	
         	<td><select name="kongtiao1" style="width:130" class="selected_font">
         	  <option value="-1">请选择</option>
         	  <option value="0">无</option>
         	  <option value="1">有</option>
         	</select></td>
         	<td>空调2：</td>	
         	<td><select name="kongtiao2" style="width:130" class="selected_font">
         	  <option value="-1">请选择</option>
         	  <option value="0">无</option>
         	  <option value="1">有</option>
         	</select></td>	  
   </tr>
   </table>
   </table>
   <table  height="23">
<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>
<div style="width: 100%; height: 150px; overflow-x: auto; overflow-y: auto; border: 1px">
   <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
   	<tr  class="relativeTag" >
   	    <td width="5%"  class="relativeTag" bgcolor='#DDDDDD'   ><div  align="center" class="bttcn">地市</div></td>         
	    <td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  ><div align="center" class="bttcn">站点数</div></td>    
	    <td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  ><div align="center" class="bttcn">超标站点数</div></td> 
		<td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  ><div align="center" class="bttcn">超标比例</div></td> 	
		<td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  ><div align="center" class="bttcn">严重超标站点数</div></td>
		<td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  ><div align="center" class="bttcn">严重超标比例</div></td>
 	</tr>	
				   		
  	 	<%	
  	 	int m=0,jj=0,x=0,p=0;
  	 	Double n=0.00,f1=0.00,ii=0.00,f2=0.00,y=0.00,f3=0.00,q=0.00,f4=0.00;
  	 	String whereStr="",iii="",ff1="",ff2="",ff3="",ff4="",qq="",yy="",nn="";
  	 	if(shi != null && !shi.equals("")&& !shi.equals("0")){
				whereStr=whereStr+" AND Z.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("")&& !xian.equals("0")){
			whereStr=whereStr+" AND Z.XIAN='"+xian+"'";
			}
			if(zdlx!=null&&!zdlx.equals("")&& !zdlx.equals("0")){
			whereStr=whereStr+" AND Z.STATIONTYPE='"+zdlx+"'";
			}
			if (jzproperty1!=null&&!jzproperty1.equals("")&& !jzproperty1.equals("0")) {
				whereStr=whereStr+" AND Z.PROPERTY='" + jzproperty1 + "'";
			}	
			if (kongtiao1!=null&&!kongtiao1.equals("")&& !kongtiao1.equals("-1")) {
				whereStr=whereStr+" AND Z.KT1='" + kongtiao1 + "'";
			}
			if (kongtiao2!=null&&!kongtiao2.equals("")&& !kongtiao2.equals("-1")) {
				whereStr=whereStr+" AND Z.KT2='" + kongtiao2 + "'";
			}
		
		System.out.println("whereStr"+whereStr);
		System.out.println("shishishis"+shi);
		if(null!=accountmonth&&!"".equals(accountmonth))
 		{
 		List<SitePueQueryBean> list=null;
 		SitePueQuery bean = new SitePueQuery();
         String zdsum="",zywf="",zyyc="",xxwc="",xxwf="",xxyc="",shi12="",shicode="";
         double zybl,xxbl;
         int zynum;
         list = bean.getPageData23(whereStr,loginId,pue,chaobiao,yzchaobiao,yhdld,yhdlx,dbyt,accountmonth);
       String s="0",ss="0";String sss=""; 
 			for(SitePueQueryBean listt:list){
 				shi12=listt.getShi();
 				zdsum=listt.getZdsum();
 				zywf=listt.getChaobiaozdsum();
 				zyyc=listt.getYzchaobiaozdsum();
 				shicode=listt.getShicode();
                    DecimalFormat pay4=new DecimalFormat("0.00");
                    xxwc=pay4.format(Double.parseDouble(zywf)/Double.parseDouble(zdsum)*100);   
                    xxwf=pay4.format(Double.parseDouble(zyyc)/Double.parseDouble(zdsum)*100);   
        			if (intnum % 2 == 0) {
        				color = "#FFFFFF";

        			} else {
        				color = "#DDDDDD";
        			}
        			  intnum++;
        %>  		
   	<tr bgcolor="<%=color%>">
   	    <td><div align="center" ><%=shi12%></div></td>	   
	   	<td><div align="right" ><%=zdsum%></div></td>
       	<td><div align="right" ><a href="javascript:modify89('<%=shicode%>','<%=xian%>','<%=accountmonth%>','<%=pue%>','<%=chaobiao%>','<%=dbyt%>','<%=zdlx%>','<%=yhdld%>','<%=yhdlx%>','<%=jzproperty1%>','<%=kongtiao1%>','<%=kongtiao2%>')"><%=zywf%></a></div></td>
   	    <td><div align="right" ><%=xxwc%>%</div></td>
   	    <td><div align="right" ><a href="javascript:modify89('<%=shicode%>','<%=xian%>','<%=accountmonth%>','<%=pue%>','<%=yzchaobiao%>','<%=dbyt%>','<%=zdlx%>','<%=yhdld%>','<%=yhdlx%>','<%=jzproperty1%>','<%=kongtiao1%>','<%=kongtiao2%>')"><%=zyyc%></a></div></td>
   	    <td><div align="right" ><%=xxwf%>%</div></td>
   	   
   	  
   	</tr>
   
  
   	 <%
       	}}
       %>
       
  </table>
  </div>
  <br/>
   <input type="hidden" name="shi2" id="shi2" value=""/>
   <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
  <table border="0" width="100%">
	  <iframe  name="treeMap1" width="100%" frameborder="0" ></iframe>
   </table>
   </form>
    
  </body>
</html>
<script type="text/javascript">
var path = '<%=path%>';
	/*function dfinfo1(shi,yuefen,xlxa){
    	var b=path+"/web/cx/stationfees1.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;			
		 var a = " <a href="+b+" target='treeMap1' id='tmpTree'></a>";
		$("#tmpTree").remove();
		$("body").append(a);
		$("#tmpTree")[0].click();
	}*/
	




	
	function modify89(shi,xian,accountmonth,pue,chaobiao,dbyt,zdlx,yhdld,yhdlx,jzproperty1,kongtiao1,kongtiao2){
    var b=path+"/web/newgn/sitepuequery01.jsp?shi="+shi+"&xian="+xian+"&accountmonth="+accountmonth+"&pue="+pue+"&chaobiao="+chaobiao+"&dbyt="+dbyt+"&zdlx="+zdlx+"&yhdld="+yhdld+"&yhdlx="+yhdlx+"&jzproperty1="+jzproperty1+"&kongtiao1="+kongtiao1+"&kongtiao2="+kongtiao2;		
	 var a = " <a href="+b+" target='treeMap1' id='tmpTree'></a>";
	 $("#tmpTree").remove();
		$("body").append(a);
		$("#tmpTree")[0].click();   
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
  	document.form1.shi.value='<%=shi%>';
	document.form1.xian.value='<%=xian%>';
	document.form1.zdlx.value='<%=zdlx%>';
	document.form1.jzproperty.value='<%=jzproperty1%>';
	document.form1.kongtiao1.value='<%=kongtiao1%>';
	document.form1.kongtiao2.value='<%=kongtiao2%>';
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




//-->
</script>
