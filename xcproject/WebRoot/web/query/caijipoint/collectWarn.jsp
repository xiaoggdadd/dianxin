<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>

<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	String topstr=request.getParameter("str");
	
   Account account = (Account)session.getAttribute("account");
   String loginId = account.getAccountId();
   String shi=request.getParameter("shi")!=null?request.getParameter("shi"):"0";
   String zt=request.getParameter("zt")!=null?request.getParameter("zt"):"-1";
   String jzxlx1=request.getParameter("jzxlx")!=null?request.getParameter("jzxlx"):"0";
   String zdlx1=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
   String jrwtype1=request.getParameter("jrwtype")!=null?request.getParameter("jrwtype"):"0";
   String jflx1=request.getParameter("jflx")!=null?request.getParameter("jflx"):"0";
   String zdname=request.getParameter("stationname");
    //String roleId = account.getRoleId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    //System.out.println("roleId:"+roleId);
%>

<html>
<head>
<title>

</title>
<style>
        .form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
		}
		.form{
			width:90px;
			height: 18px;
	
		}
            .style1 {
	color: #FF9900;
	font-weight: bold;
}
.sel{
		width:100px;
		 
			
			font-family: 宋体;
			font-size: 12px;
			line-height:120%;
		}
		.relativeTag{     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px ##888888 solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.bttcn{
        color:BLACK;
       font-weight:bold;
}
 </style>
 <LINK href="<%=path%>/images/css.css" type=text/css rel=stylesheet>

<script src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
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
					var beginTime = document.form1.beginTime.value
           	if(checkNotnull(document.form1.beginTime,"开始时间")&&
                   checkNotnull(document.form1.endTime,"结束时间")){
 			document.form1.action=path+"/web/sys/logManage.jsp?beginTime="+beginTime
                        document.form1.submit()
        	}
	}
	
	 //审核通过
	function passCheck(){
        var m = document.getElementsByName('test[]');
       
        var l = m.length; 
        var chooseIdStr = ""; 
        var bz="";   
        for(var i = 0; i < l; i++){
          if(m[i].checked == true){
             var bz="1";
             chooseIdStr = chooseIdStr + m[i].value +","; 
          }             
        } 
        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
        if(bz=="1"){
        	
          document.form1.action=path+"/servlet/check?action=checkcity&chooseIdStr="+chooseIdStr;
          document.form1.submit();
        }else{
          alert("请选择信息！");
        }
    }
  function cancel(){
  document.form1.action=path+"/web/newright.jsp";
  document.form1.submit();
  }
    
    function queryDegree(){
           
            document.form1.action=path+"/web/query/caijipoint/collectWarn.jsp";
            document.form1.submit();
       
    }
    $(function(){
		

		$("#tongguo").click(function(){
			passCheck();
		});

		$("#butongguo").click(function(){
			passCheckNo();
		});
		$("#chaxun").click(function(){
			queryDegree();
		});
			$("#cancelBtn").click(function(){
				cancel();
			});
	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0804");

%>
<body  class="body">
	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0"  cellspacing="0" cellpadding="0" >
		          <tr class="form_label">
					   <td  colspan="8">
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:black;font-weight:bold;font-size=14;">采集警告</span>
					   </td>
				 </tr>
         
            </table>
               <table width="70%"  border="0"  cellspacing="0" cellpadding="0" class="form_label" >
                     <tr class="form_label" >
                          <td height="19" colspan="8" class="form_label">
                            <div id="parent" style="display:inline"> <div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div></div>
                         </td>
                    </tr>
                  <tr>
                       <td class="form_label">城市:</td>
                   <td> <select name="shi" class="sel"  onchange="changeCity()">
					         	     	<option value="0">请选择</option>
					         		<%
							         	ArrayList shilist = new ArrayList();
							         	shilist = commBean.getAgcode(shengId,account.getAccountId());
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
					         	</select>
					         	</td>
                  <td class="form_label">状态：</td>
                  <td>
		  			<select name="zt" class="sel" >
		         		<option value="-1">请选择</option>
		         		<option value="1">正常</option>
		         		<option value="0">网络中断</option>
		         		
		         	</select>
	         	</td>
                  
	         	<td class="form_label">基站类型：</td>
	         	<td>
<select name="jzxlx" class="sel" >
		         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = ztcommon.getSelctOptions("xlx");
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
</select>
	         	</td>
	         	
	         	<td class="form_label">站点类型：</td>
										<td>
										       <select name="zdlx" class="sel" > 
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
			                                   </select>
										  </td>
                
                  </tr>
                  <tr  class="form_label">
                <td class="form_label">
               		局房类型：
         		</td>
		         <td>  
		         		<select name="jflx" class="sel">
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList jflx = new ArrayList();
			         	jflx = ztcommon.getSelctOptions("JFLX");
			         	if(jflx!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)jflx.get(0)).intValue();
			         		for(int i=cscount;i<jflx.size()-1;i+=cscount)
		                    {
		                    	code=(String)jflx.get(i+jflx.indexOf("CODE"));
		                    	name=(String)jflx.get(i+jflx.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
		         	</select>
		         </td>
							 <td class="form_label">
               		<div align="left">接入网类型：</div>
         		</td>
		         <td>  
		         		<select name="jrwtype" class="sel">
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList jrwtype2 = new ArrayList();
		         		jrwtype2 = ztcommon.getSelctOptions("JRWLX");
			         	if(jflx!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)jrwtype2.get(0)).intValue();
			         		for(int i=cscount;i<jrwtype2.size()-1;i+=cscount)
		                    {
		                    	code=(String)jrwtype2.get(i+jrwtype2.indexOf("CODE"));
		                    	name=(String)jrwtype2.get(i+jrwtype2.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
		         	</select>
		         </td>
		          <td class="form_label">站点名称：</td>
							<td >
		         	<input type="text" name="stationname" value="<%if(null!=request.getParameter("stationname")) out.print(request.getParameter("stationname")); %>"  class="sel" />
		        </td>
		       
                  </tr>
                  
                  <tr class="form_label">
                         <td height="19" colspan="8" class="form_label">
                         <div id="parent" style="display:inline">
                                   <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                          </div>
                      </td>
                </tr>
      
           <% 
             String str="",wherestr="",str1="";
             
         	if(shi != null && !shi.equals("") && !shi.equals("0")){
				str=str+" and z.shi='"+shi+"'";
			}if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
				str=str+" and z.jzname like'%"+zdname+"%'";
			}if(zdlx1!=null&&!zdlx1.equals("")&&!zdlx1.equals("0")){
			 str=str+" and z.STATIONTYPE='"+zdlx1+"'";
			}if(jflx1!=null&&!jflx1.equals("")&&!jflx1.equals("0")){
			 str=str+" and z.jflx='"+jflx1+"'";
			}
			if( jzxlx1!=null&&!jzxlx1.equals("")&&!jzxlx1.equals("0")){
			 str=str+" and z.xlx='"+jzxlx1+"'";
			}
			if(jflx1!=null&&!jflx1.equals("")&&!jflx1.equals("0")){
			 str=str+" and z.jflx='"+jflx1+"'";
			}
			if(jrwtype1 != null && !jrwtype1.equals("") && !jrwtype1.equals("0")){
				str=str+" and z.jrwtype ='"+jrwtype1+"'";
			}if(topstr!=null&& !topstr.equals("") && !topstr.equals(" ")){
				if(topstr.equals("1")){
					wherestr=" and aa.flag ='正常'";
					str1="1";
				}else{
					wherestr=" and aa.flag ='网络中断'";
					str1="0";
				}
			}else if(zt != null && !zt.equals("") && !zt.equals("-1")){
				
				if(zt.equals("1")){
					wherestr=" and aa.flag ='正常'";
					str1="1";
				}else{
					wherestr=" and aa.flag ='网络中断'";
					str1="0";
				}
				
			}
			//System.out.println("wherestr"+wherestr+"jzxlx"+jzxlx1+"--");
             haodianliangBean bean = new haodianliangBean();
             String sum=bean.getWarn(str,str1,loginId);
             %>
      
</table>
<table width="100%"  border="0"  cellspacing="0" cellpadding="0" >
 <tr >
  <td colspan="4">采集周期：1小时</td>
			<td>  站点总数：<%=sum %></td>
			<td></td>
			<td></td>
			<td>
            <div id="cancelBtn" style="position:relative;width:60px;height:23px;cursor: pointer;float:right;">
				       <img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
				<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">返回</span>
			</div>
			   <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;right:20%;float:right;">
		       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		        <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">刷新</span>
		      </div>
       </td>
       </tr>
</table>
<div style="width:100%;height:290px;overflow-x:auto;overflow-y:auto;border:1px" >  
  <table width="100%" border="0" class="form_label" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">
<!-- 序号 地市 站点名称 站点类型 通讯状态 最后取数时间 最后读数 -->
<tr class="relativeTag"  height = "100%" >
                        <td  width="5%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" class="btcnn">序号</div>
                        </td>
                        <td width="8%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center">地市</div>
                        </td>
                        <td width="15%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center">站点名称</div>
            			</td>
            			<td width="9%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center">站点类型</div>
            			</td>
            			<td width="8%" height="23" bgcolor="#DDDDDD" class="form_label" ><div align="center" >通讯状态</div>
            			</td>            			
                        <td width="13%" height="23" bgcolor="#DDDDDD" class="form_label"><div align="center">最后读取时间</div></td>                       
</tr>
                  
       <%
          ArrayList fylist = new ArrayList();
       	  fylist = bean.getCollectWarn(str,wherestr,loginId);
       	 allpage=bean.getAllPage();
		String jzname = "",zdcode="",flag = "",time = "",zhuangtai="";
		Double df=0.00;
		String szdq="",stationtype3="",datavalue="";
		int intnum=xh = pagesize*(curpage-1)+1;
		
		 if(fylist!=null){
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
		     //num为序号，不同页中序号是连续的
		    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		     jzname = jzname != null ? jzname : "";	
		    zdcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
		    zdcode = zdcode != null ? zdcode : "";	
		    flag = (String)fylist.get(k+fylist.indexOf("FLAG"));
		    flag = flag != null ? flag : "";	
		    time = (String)fylist.get(k+fylist.indexOf("GETDATETIME"));
		    time = time != null ? time : "";	
		    stationtype3 =(String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
		    stationtype3 = stationtype3 != null ? stationtype3 : "";
		    
		    szdq = (String)fylist.get(k+fylist.indexOf("SHI"));
		    szdq = szdq != null ? szdq : "";	
		  
		    
		    
		    
		    if(stationtype3.equals("null")){
		     stationtype3="";
		    }
		    if(szdq.equals("null")){
		     szdq="";
		    }
		    
			String color=null;
             if(time.equals("")||time==null){
            	 time="中断超过一天";
             }
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
           intnum++;

       %>
      
       <tr class="form_label" bgcolor="<%=color%>">
           <td class="form_label">
       			<div align="center" ><%=intnum-1%></div>
       		</td>
       		<td class="form_label">
       			<div align="center" ><%=szdq%></div>
       		</td>
       		
       		<td >
       			<div align="left" class="form_label"><a href="javascript:dfinfo2('<%=zdcode%>','<%=jzname%>')"><font size="2"><%=jzname%></font></a></div>
       		</td>
       		<td class="form_label">
       			<div align="center" ><%=stationtype3%></div>
       		</td>
       		
       		<td class="form_label">
       			<div align="center" ><%=flag%></div>
       		</td>
       		<td class="form_label">
       			<div align="center" ><%=time%></div>
       		</td>
       		
       </tr>
       <% }%>
       <% }%>
  	 </table> 
  	 </div>

</form>
</body>
</html>
<script type="text/javascript">
function dfinfo2(zdcode,jzname){
    var url=path+"/web/query/caijipoint/collectWarn1.jsp?zdcode="+zdcode+"&jzname="+jzname;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1000px;dialogHeight:1000px;status:auto;scroll:auto');	
}

</script>


<script language="javascript">
	var path = '<%=path%>';
	function lookDetails(dfid,dfzflx){
	
   var dfid1=dfid;
   var dfzflx1=dfzflx;
   
	var href = "FeesDetails.jsp?dfid="+dfid1+"&dfzflx="+dfzflx1;
	var str = "<a id='aa' href='"+href+"' target='details'></a>";
	$("body").append(str);
	$("#aa")[0].click();
	$("#aa").remove();
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


//-->
</script>
<script type="text/javascript">


function passCheckNo(){
        var m = document.getElementsByName('test[]');   
       
        var l = m.length; 
        var chooseIdStr = ""; 
        var bz="";
        for (var i = 0; i < l; i++) {  
          if(m[i].checked == true){
             var bz="1";
             chooseIdStr = chooseIdStr + m[i].value +","; 
          }               
        } 
        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
        //alert(chooseIdStr);       
        if(bz=="1"){
          document.form1.action=path+"/servlet/check?action=checkfmno&chooseIdStr="+chooseIdStr;
          document.form1.submit();
        }else{
          alert("请选择信息！");
        }
   }

document.form1.shi.value='<%=shi%>';
document.form1.zt.value='<%=zt%>';
document.form1.jzxlx.value='<%=jzxlx1%>';
document.form1.zdlx.value='<%=zdlx1%>';
document.form1.jflx.value='<%=jflx1%>';
document.form1.jrwtype.value='<%=jrwtype1%>';

 </script>
