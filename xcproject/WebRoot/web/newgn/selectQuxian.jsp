<%@ page contentType="text/html; charset=UTF-8" %>
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
	String bzmonth1 = request.getParameter("bzmonth1")!=null?request.getParameter("bzmonth1"):"";
	String bzmonth2 = request.getParameter("bzmonth2")!=null?request.getParameter("bzmonth2"):"";
	String zdqyzt=request.getParameter("zdqyzt")!=null?request.getParameter("zdqyzt"):"1";
	String dbqyzt = request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    //curpage=Integer.parseInt(s_curpage);
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
 
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <jsp:include page="/web/prePrint.jsp"></jsp:include>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
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
<script>

var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();

var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChsny.oBtnTodayTitle = "确定";
oCalendarChsny.oBtnCancelTitle = "取消";
oCalendarChsny.Init();
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
function selectTree(){
			var a = "<a href='<%=path %>/web/tongjichaxun/dishi.jsp?";
			$("select").each(function(){
				a += $(this).attr("name")+"="+$(this).val()+"&";
			});
			$("input").each(function(){
				a += $(this).attr("name")+"="+$(this).val()+"&";
			});
			a = a.substring(0,a.length-1);
			a += "' target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();
		
				var b = "<a href='<%=path %>/web/tongjichaxun/dishicx.jsp?";
			$("select").each(function(){
				b += $(this).attr("name")+"="+$(this).val()+"&";
			});
			$("input").each(function(){
				b += $(this).attr("name")+"="+$(this).val()+"&";
			});
			b = b.substring(0,b.length-1);
			b += "' target='treeMap1' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(b);
			$("#tmpTree")[0].click();
		}
		
	$(function(){

		$("#query").click(function(){
			selectTree();
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
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">地区月耗电量趋势查询</span>	
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
	    	<td>城市：</td>
		    		
		    		<td><select name="shi" class="selected_font" onchange="changeCity()">
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
	         		 <td>区县：  </td>          
                      <td> <select name="xian" class="selected_font" onchange="changeCountry()">
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
         	                            </select>   </td>  
		    		<td>报账月份：</td>
		    		<td><input type="text" name="bzmonth1" value="<%if (null != request.getParameter("bzmonth1")) out.print(request.getParameter("bzmonth1"));%>" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font" /></td>
		    		<td>至</td>
		    		<td><input type="text" name="bzmonth2" value="<%if (null != request.getParameter("bzmonth2")) out.print(request.getParameter("bzmonth2"));%>" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font" /></td>
         			
			 <td>
				<div id="query" style="position:relative;width:60px;height:23px;cursor: pointer;right:-220px;float:right;top:0;">
					<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
					<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
				</div>

			</td>
			</tr>
	</table>
	</td></tr>
	   
 </table>
  <div style="width:88%;" > 
		<p id="box3" style="display:none">
			<table>
    
		  </table>
		  	</p>
	</div>
  	  	<table>
		     <tr><td height="15" colspan="4"><div id="parent" style="display:inline">
             <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
             </div></td></tr>       	
	    </table>
</form>
 <iframe name="treeMap" width="450px" height="200px" frameborder="0" src="<%=path%>/web/tongjichaxun/dishi.jsp"></iframe>
 
  	<span style="width:20px"></span>
  <iframe name="treeMap1" width="650px" height="200px" frameborder="0" src="<%=path %>/web/tongjichaxun/collectQueryMap.jsp"></iframe>
<br/>
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
document.form1.jztype.value='<%=jztype1%>';
document.form1.jzproperty.value='<%=jzproperty1%>';
     </script>

