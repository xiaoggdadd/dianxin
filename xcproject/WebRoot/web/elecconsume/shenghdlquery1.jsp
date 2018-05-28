<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.ZhanDianForm,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.text.*"%>
<%
String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
int intnum=0;
String jzproperty1 = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
String color="",bztime="";
 bztime  = request.getParameter("bztime")!= null ? request.getParameter("bztime"): "";
 String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script type="text/javascript">
var path = '<%=path%>';
	
	 function queryDegree(){	 
			  document.form1.action=path+"/web/elecconsume/shenghdlquery1.jsp?command=chaxun";
                   document.form1.submit();      
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
	
	function lookDetailss(sss,xian,jzproperty1,zdlx,bztime){ 
	 var path='<%=path%>';   
	 window.open(path+"/web/elecconsume/shenghdl.jsp?sss="+sss+"&xian="+xian+"&jzproperty1="+jzproperty1+"&zdlx="+zdlx+"&bztime="+bztime,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
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
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">专业线及分摊情况汇总</span>	
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
						<td>站点属性：</td>
         	<td>
         	<select name="jzproperty" style="width:130" class="selected_font">
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
                                 <td>报账月份：</td>
                              <td>
                              <input type="text" class="selected_font" name="bztime" value="<%if (null != request.getParameter("bztime"))out.print(request.getParameter("bztime"));%>"
		                         	 readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="form" />
                              </td> 														
   	 <td >
							       <div id="chaxun" style="position:relative;width:59px;height:23px;right:-100px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
							</td>
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
   	    <td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  rowspan="2" ><div  align="center" class="bttcn">地市</div></td>         
	    <td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  rowspan="2"><div align="center" class="bttcn">站点数</div></td>    
	    <td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  colspan="4"><div align="center" class="bttcn">分摊专业线</div></td> 
		<td width="5%"  class="relativeTag" bgcolor='#DDDDDD'  colspan="4"><div align="center" class="bttcn">详细分摊</div></td> 	
    </tr> 
		   		   <tr  class="relativeTag"  >
				   		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">完成</div></td>
				   		<td bgcolor='#DDDDDD' class="relativeTag"  width="5%"><div align="center" class="bttcn">未分</div></td>
				   		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">异常</div></td>
				   		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">完成比例</div></td>
				   		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">完成</div></td>
				   		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">未分</div></td>
				   		<td bgcolor='#DDDDDD'  class="relativeTag" width="5%"><div align="center" class="bttcn">异常</div></td>
				   		<td bgcolor='#DDDDDD' class="relativeTag"  width="5%"><div align="center" class="bttcn">完成比例</div></td>
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
			if (!jzproperty1.equals("0")&&jzproperty1!=null&&!jzproperty1.equals("")) {
			whereStr=whereStr+" AND Z.PROPERTY='" + jzproperty1 + "'";
			}
		   if (bztime!=null&&!bztime.equals("")) {
			whereStr=whereStr+" and d.dbid in (select du.ammeterid_fk from dianfeiview df,dianduview du where  df.ammeterdegreeid_fk=du.ammeterdegreeid and to_char(df.accountmonth,'yyyy-mm')='" + bztime + "')";
			}
		System.out.println("whereStr"+whereStr);
		System.out.println("shishishis"+shi);
		if("chaxun".equals(request.getParameter("command")))
 		{
 		List<ZhanDianForm> list=null;
   	     JiZhanBean bean = new JiZhanBean();
         String zywc="",zywf="",zyyc="",xxwc="",xxwf="",xxyc="",shi12="";
         double zybl,xxbl;
         int zynum;
         list = bean.getPageData23(whereStr,loginId,loginName);
      
       	String s="0",ss="0";String sss="";
      
 		for(ZhanDianForm listt:list){
 
        sss=listt.getShicode();
        shi12=listt.getShi();
        if(shi12.equals("")||"null".equals(shi12)||null==shi12){shi12="";}
        zynum=listt.getZynum();
       
        zywc=listt.getZywc();zywf=listt.getZywf();zyyc=listt.getZyyc(); zybl=listt.getZybl();
        xxwc=listt.getXxwc();xxwf=listt.getXxwf();xxyc=listt.getXxyc(); xxbl=listt.getXxbl();
        			//num为序号，不同页中序号是连续的
        		
                    DecimalFormat pay4=new DecimalFormat("0.00");
                 s=pay4.format(zybl);
                  ss= pay4.format(xxbl);
 intnum++;
        			if (intnum % 2 == 0) {
        				color = "#DDDDDD";

        			} else {
        				color = "#FFFFFF";
        			}
        			 
        %>  		
   	<tr bgcolor="<%=color%>">
   	    <td><div align="center" ><a href="javascript:modify89('<%=sss%>','<%=xian%>','<%=jzproperty1%>','<%=zdlx%>','<%=bztime%>')"><%=shi12%></a></div></td>	   
	    <td><div align="right" ><%=zynum%></div></td>
	   	<td><div align="right" ><%=zywc%></div></td>
       	<td><div align="right" ><%=zywf%></div></td>
   	    <td><div align="right" ><%=zyyc%></div></td>
   	    <td><div align="right" ><%=s%>%</div></td>
   	   
   	    <td><div align="right" ><a href="javascript:lookDetailss('<%=sss%>','<%=xian%>','<%=jzproperty1%>','<%=zdlx%>','<%=bztime%>')"><%=xxwc%></a></div></td>
   	    <td><div align="right" ><%=xxwf%></div></td>
   	    <td><div align="right" ><%=xxyc%></div></td>
   	    <td><div align="right" ><%=ss%>%</div></td>
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
	
function dfinfo1(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees1.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function dfinfo2(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees2.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function dfinfo3(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees3.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function dfinfo4(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees4.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function modify89(sss,xian,jzproperty1,zdlx,bztime){
    var b=path+"/web/elecconsume/shenghdlquery2.jsp?sss="+sss+"&xian="+xian+"&jzproperty1="+jzproperty1+"&zdlx="+zdlx+"&bztime="+bztime;		
	 var a = " <a href="+b+" target='treeMap1' id='tmpTree'></a>";
	 $("#tmpTree").remove();
		$("body").append(a);
		$("#tmpTree")[0].click();   
}	
	function dfinfo6(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees6.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function dfinfo7(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees7.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function dfinfo8(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees8.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
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
document.form1.jzproperty.value='<%=jzproperty1%>';
	document.form1.zdlx.value='<%=zdlx%>';
	document.form1.bztime.value='<%=bztime%>';
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
