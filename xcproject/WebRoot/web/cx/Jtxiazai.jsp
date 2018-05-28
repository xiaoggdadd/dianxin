 <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.noki.jtreport.servlet.FilesDownload"%>
<%@ page import="java.text.SimpleDateFormat,java.util.Date"%>


<%
    String filenamee = request.getParameter("filename")!=null?request.getParameter("filename"):"0";
    String uploaddatee = request.getParameter("uploaddate")!=null?request.getParameter("uploaddate"):"";
    String actualmonthh = request.getParameter("actualmonth")!=null?request.getParameter("actualmonth"):"";
  
    String color = null;
	String sheng = (String)session.getAttribute("accountSheng");
	String roleId = (String)session.getAttribute("accountRole");
        String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
       
        String path = request.getContextPath();
        
        String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        
        String rgsh=request.getParameter("rgsh");//站点审核通过   首页传的值
        String rgsh2=request.getParameter("caiji");/// 采集站点
        String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
	    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
          String permissionRights="";
          
      
          
          
          
%>

<html>
<head>
<title>
报表列表
</title>
<style type="text/css">
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	
}
.btt{ bgcolor:#888888;font-weight:bold;}
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
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
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
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
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
					document.form1.action=path+"/web/cx/Jtxiazai.jsp";
					document.form1.submit();
	}
	$(function(){
		$("#add").click(function(){
			addJz();
		});
		
		$("#query").click(function(){
			chaxun();
		});
	});
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
<%
	permissionRights = commBean.getPermissionRight(roleId, "0101");

%>
<body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
	
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		 <tr>
					    	<td colspan="4" width="50" >
											 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">报表列表</span>	
												</div>
											</td>

	    	        </tr>	    	
		     <tr><td height="20" colspan="4" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td>
	    	</tr>
			<tr>
				<td>
					<table>
						<tr class="form_label">
							<td >城市：</td>
                   <td> <select name="shi" id="shi" style="width:130;" onchange="changeCity()" class="selected_font">
         		<option value="0">请选择</option>
         		<%
         			ArrayList shilist = new ArrayList();
         			shilist = commBean.getAgcode(sheng, shi, loginName);
         			if (shilist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) shilist.get(0)).intValue();
         				for (int i = scount; i < shilist.size() - 1; i += scount) {
         					agcode = (String) shilist
         							.get(i + shilist.indexOf("AGCODE"));
         					agname = (String) shilist
         							.get(i + shilist.indexOf("AGNAME"));
         		%>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    	}
                    %>
         		</select></td>
    	    <td>上传时间： </td>
            <td>
              <input type="text" name=uploaddate class="selected_font" value="<%if(null!=request.getParameter("beginTimeQ")) out.print(request.getParameter("beginTimeQ")); %>" onFocus="getDateString(this,oCalendarChs)"  />
            </td>
            <td>报表月份</td>
            <td>
               <input type="text" name="actualmonth" class="selected_font" value="<%if (null != request.getParameter("beginTime1")) out.print(request.getParameter("beginTime1"));%>" onFocus="getDatenyString(this,oCalendarChsny)"   />
            </td>
         	<td>报表名称</td>
         	<td><select name="filename" id="xiaoqu" style="width:200" class="selected_font">
         				<option value="0">请选择</option>
					<option value="附件1：地市基站用电量信息汇总表.xls">附件1：地市基站用电量信息汇总表</option>
					<option value="附件2：基站用电量汇总分析表.xls">附件2：基站用电量汇总分析表</option>
					<option value="附件3：基站节能减排技术应用情况统计表.xls">附件3：基站节能减排技术应用情况统计表</option>
					<option value="附件4：地市接入网机房用电量信息汇总.xls">附件4：地市接入网机房用电量信息汇总表</option>
					<option value="附件5：接入网机房节能减排技术应用情况.xls">附件5：接入网机房节能减排技术应用情况统计表</option>
					<option value="附件6：IDC用电量汇总表.xls">附件6：IDC用电量汇总表（月报）</option>
					<option value="附件7：通信局房用电量汇总表.xls">附件7：通信局房用电量汇总表（月报增加生产用电电费）</option>
					<option value="附件8：通信机房节能技术应用信息统计表.xls">附件8：通信机房节能技术应用信息统计</option>
					<option value="附件9：节能减排工作成效报表.xls">附件9：节能减排工作成效报表</option>
					<option value="附件10：节能设备后评价报表.xls">附件10：节能设备后评价报表</option>
					<option value="节能减排季报(集团原始表）.xls">节能减排季报表（集团原始表）</option>
     
         	</select></td>
         			
	         		<td >
				       <div id="query" style="position:relative;width:59px;height:23px;right:-40px;cursor: pointer;TOP:0PX">
							<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
							<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
                        </div>
					</td>
				</table>
			</td>
		</tr>
  </table>
 
             <table  height="23">
<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>

</table>
  <%
  String whereStr="";
        if (!shi.equals("0")) {
		    shi=shi;
	         }
		
		if (!filenamee.equals("0")) {
			whereStr=whereStr+" and t.filename='" + filenamee + "'";
		    }
		if (!uploaddatee.equals("")) {
			whereStr=whereStr+" and t.uploaddate=to_date('"+uploaddatee+"','yyyy-mm-dd')";
		    }
		if (!actualmonthh.equals("")) {
			whereStr=whereStr+" and t.actualmonth='" + actualmonthh + "'";
		    }
	

  
  
   %>	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">

             <tr height="23">
		          <td  height="23" width="3%"  bgcolor="#DDDDDD"><div align="center" class="btt" >序号</div></td>
                  <td  height="23" width="8%" bgcolor="#DDDDDD" ><div align="center" class="btt">上传日期</div></td>
                  <td  height="23" width="10%" bgcolor="#DDDDDD" ><div align="center" class="btt">报表名称</div></td>
                  <td  height="23" width="6%" bgcolor="#DDDDDD"><div align="center"  class="btt">报表月份</div><i/td>
                  <td  height="23" width="5%" bgcolor="#DDDDDD"><div align="center" class="btt">下载</div></td>
                  <td  height="23" width="5%" bgcolor="#DDDDDD"><div align="center" class="btt">删除</div></td>
            </tr>
       <%
		 FilesDownload load=new FilesDownload();
       	 ArrayList fylist = new ArrayList();
         fylist = load.getPageData3(loginName,shi,whereStr);
       	 
       	 allpage=load.getAllPage();
		String uploaddate="",filename="",actualmonth="";
		Date datee=null;
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

       			//num为序号，不同页中序号是连续的
       			uploaddate = (String) fylist.get(k + fylist.indexOf("UPLOADDATE"));
       			filename = (String) fylist.get(k + fylist.indexOf("FILENAME"));
       			actualmonth = (String) fylist.get(k + fylist.indexOf("ACTUALMONTH"));
       			uploaddate=uploaddate.substring(0,10);
       			
       			if (intnum % 2 == 0) {
       				color = "#DDDDDD";

       			} else {
       				color = "#FFFFFF";
       			}
       %>
       <tr bgcolor="<%=color%>">
       		<td >
       			<div align="center" ><%=intnum++%></div>
       		</td>
       		
       		<td >
       			<div align="center"  ><%=uploaddate%></div>
       		</td>
       		<td >
       			<div align="left"  ><%=filename%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=actualmonth%></div>
       		</td>
       		<td >
       			<div align="center" ><a href="#" onclick="modifyjz('<%=filename%>','<%=actualmonth %>','<%=shi %>')"><font  style="font-size: 12px;">下载</font></a></div>
       		</td>
       		<td >
       			<div align="center" ><a href="#" onclick="delzd('<%=filename%>','<%=actualmonth %>','<%=shi %>')"><font  style="font-size: 12px;">删除</font></a></div>
       		</td>
       		
       </tr>
       <%
       	}
       %>
        <% if (intnum==0){
    	  System.out.println("QQQQ"+intnum);
         for(int i=0;i<15;i++){
          if(i%2==0){
			    color="#DDDDDD" ;
          }else{
			    color="#FFFFFF";
			}
         %>

        <tr bgcolor="<%=color%>">
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
			    color="#FFFFFF";
            else
			    color="#DDDDDD";
        %>
        <tr bgcolor="<%=color%>">
             <td>&nbsp;</td> 
             <td>&nbsp;</td>  
             <td>&nbsp;</td>
             <td>&nbsp;</td> 
             <td>&nbsp;</td>  
             <td>&nbsp;</td>  
      </tr>
        <% }}%>
       <%
       	}
       %>

  	 </table> 
</form>

</body>
</html>

<script language="javascript">
	var path = '<%=path%>';
	function gopage()
     {
      document.form1.submit();
     }
     function previouspage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage - 1)%>';
      		document.form1.action=path+"/web/cx/Jtxiazai.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage + 1)%>';
      		document.form1.action=path+"/web/cx/Jtxiazai.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
     var rgsh2='<%=rgsh2%>';
      var rgsh='<%=rgsh%>';
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/cx/Jtxiazai.jsp?rgsh="+rgsh+"&caiji="+rgsh2+"&curpage="+pageno;
      document.form1.submit();
     }
     function delzd(filename,actualmonth,shi){
     if(confirm("您确定删除此报表信息？")){
     	<%if (permissionRights.indexOf("PERMISSION_DELETE") >= 0) {%> //权限
     		document.form1.action=path+"/servlet/baobiao?action=delsite&filename="+filename+"&actualmonth="+actualmonth+"&shi="+shi;
      document.form1.submit();
      <%} else {%>
      alert("您没有删除站点的权限");
    <%}%>
    }
    }
    function modifyjz(filename,actualmonth,shi){
   // alert(path);
     		document.form1.action=path+"/servlet/xiazai?filename="+filename+"&actualmonth="+actualmonth+"&shi="+shi;
     		 document.form1.submit();

    }
    //页面载入方法
    function op(){
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian,ammeters,ammeterdegrees,electricfees&tab=zd,am,ad,ef&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
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
		
document.form1.shi.value='<%=shi%>';
document.form1.uploaddate.value='<%=uploaddatee%>';
document.form1.actualmonth.value='<%=actualmonthh%>';
document.form1.filename.value='<%=filenamee%>';
     </script>


