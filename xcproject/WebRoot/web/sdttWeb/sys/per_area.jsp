<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.mobi.sys.javabean.Per_area" %>
<%@ page import="java.sql.ResultSet"%>

<%
	
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
		String accountid = request.getParameter("accountid");
		String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
		int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
        curpage=Integer.parseInt(s_curpage);
        Per_area bean = new Per_area();
        String parea = bean.getParea(accountid);
  		System.out.println(parea);        
  		String loginName = (String)session.getAttribute("loginName");
  		String loginId=account.getAccountId();
%>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
	//String ssagcode = ztcommon.getLastAgcode(loginName);
%>
<html>
<head>
<title>分配负责区域</title>
<style>
            .style1 {
	color: #FF9900;
	font-weight: bold;
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
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.menubuttonbg30          {
	width: 122px;
	color: #333;
	text-align: center;
	background-image: url(<%=path%>/images/menubutton6.jpg);
	vertical-align: text-bottom;
	padding-top: 0px;
	height: 23px;
}
.menubuttonbg30 a:link     {
	color: #333;
	text-align: center;
	vertical-align: text-bottom;
	background-position: bottom;
	width: 122px;
	height: 23px;
	padding-top: 0px;
}
.menubuttonbg30 a:visited  {
	color: #464646;
	background-image: url(<%=path%>/images/menubutton6.jpg);
	width: 122px;
	text-align: center;
	vertical-align: text-bottom;
	height: 23px;
	padding-top: 0px;
}
.menubuttonbg30 a:hover    {
	color: #C2050C;
	background-image: url(<%=path%>/images/menubutton32.jpg);
	width: 122px;
	text-align: center;
	vertical-align: text-bottom;
	height: 23px;
	padding-top: 0px;
}	
.menubuttonbg2           {
	height: 23px;
	width: 122px;
	color: #333333;
	text-align: center;
	padding-right: 0px;
	vertical-align: text-bottom;
	padding-top: 0px;
}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/tx.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8]>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
	<![endif]-->
	
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
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
	$(function(){
			$("#baocun").click(function(){
				//saveAccount();
				saveAgrea();
			});
			$("#cancelBtn").click(function(){
				window.history.back();
			});
			
		});

</script>
	<LINK href="<%=path%>/images/css.css" type=text/css rel=stylesheet>
<link rel="stylesheet" href="<%=path%>/static/plugins/zTree/3.5/zTreeStyle.css" type="text/css"></link>
<script type="text/javascript" src="<%=path%>/static/js/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/plugins/zTree/3.5/jquery.ztree.excheck-3.5.min.js"></script>
</head>
<body class="body" style="overflow-x:hidden;">
<script type="text/javascript">
	var setting = {
	    check: {
	        enable: true,
	        nocheckInherit: false
	    },
	    data: {
	        simpleData: {
	            enable: true
	        }
	    }
	};
	//ztree用于初始化的静态数据        
	var zNodes = [];
	//页面加载后初始化zTree数据且默认展开所有节点
	$(document).ready(function () {
		var loginName = '<%=loginName %>';
		var parea = '<%=parea %>';
		//console.log(parea)
		if(loginName){
			$.ajax({
				type: 'post',
				url: path + '/servlet/commonBeanServlet?action=agrea',
				cache: false,
				data: {
				   loginName: loginName
				},
				dataType: 'json',
				success: function(data){
					//console.log(data);
					if(data){
						//[5, "AGID", "AGCODE", "AGNAME", "FAGCODE", "AGRADE"
						var agid="",agcode="",agname="",fagcode="",agrade="";
						var count = data[0];
						for(var i=count; i< data.length-1; i+=count){
							agid = data[i + data.indexOf("AGID")];
							agcode = data[i + data.indexOf("AGCODE")];
							agname = data[i + data.indexOf("AGNAME")];
							fagcode = data[i + data.indexOf("FAGCODE")];
							agrade = data[i + data.indexOf("AGRADE")];
							zNodes.push({
								id: agcode,
								pId: fagcode,
								name: agname + '['+agcode+']',
								nocheck: false
							});
						}
					}
					//ztree初始化
					$.fn.zTree.init($("#ztree"), setting, zNodes).expandAll(false);
				    var treeObj = $.fn.zTree.getZTreeObj("ztree");
				    var nodes = treeObj.getNodes();
				    for(var k=0;k<nodes.length;k++){
				    	treeObj.expandNode(nodes[k], true, false, false);//ztree默认展开根节点
				    }
				    var pareaArr = parea.split(",");
				    //console.log(pareaArr)
				    for(var j=0; j<pareaArr.length; j++){
				    	if(pareaArr[j]){
				    		var node = treeObj.getNodeByParam("id",pareaArr[j]); 
				    		//console.log(node)
				    		if(node){
					    		//treeObj.selectNode(node, true);//指定选中ID的节点  
					    		treeObj.checkNode(node, true);
			                    //treeObj.expandNode(node, true, false);//指定选中ID节点展开  
				    		}
				    	}
				    }
				},
				error: function(){
					return;
				}
			});
		}
	});
	
    function saveAgrea(){
    	var agcodelist = new Array();
    	var treeObj = $.fn.zTree.getZTreeObj("ztree");
		//console.log(treeObj)
		var nodes = treeObj.getCheckedNodes(true);
		for(var i=0;i<nodes.length;i++){
            //alert(nodes[i].id); //获取每个节点的id
            agcodelist.push(nodes[i].id);
        }
		
		if(agcodelist.length == 0){
			alert("请选择负责区域");
			return;
		}
		
		document.form1.action=path+"/servlet/account?action=parea";
       	document.form1.agcodelist.value = agcodelist;
       	document.form1.submit()
    }
//-->
</script>

<form action="" name="form1" method="POST">
<table  width="60%"  border="0" cellspacing="0" cellpadding="0">
<tr>
 <td width="10" height="500" background="../../images/img_10.gif">&nbsp;</td>
 <td valign="top">
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
	     <td colspan="3">
			 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td colspan="4">&nbsp;</td>
			  </tr>
			   <tr>
				<td colspan=1 width="200" background="<%=path%>/images/btt2.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分配负责区域</span></td>
				</tr>
			</table><br>
		</td>
	</tr>
	<tr>
      <td colspan="3">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="4"></td>
				<td>
					<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" >
						<tr>
							<td height="49" bgcolor="#FFFFFF">
								<%--table width="100%" border="0" cellspacing="1" cellpadding="1">
							        <tr bgcolor="F9F9F9">
				                     	<td>
				                      	<select name="changeTitle" onchange="changefz()">
				                      		<option value="1">分配负责区域</option>
				                      		<option value="2">分配负责站点</option>
				                      		<option value="3">分配负责地市</option>
				                      	</select>
				                      	</td>
				                     </tr>
							  		 <tr>
								  		 <!--
								        
													&nbsp;标题：<input type="text" name="title" value="" class="form" style="width:100" onKeyPress="if(window.event.keyCode==13){chaxun();}"/>
													&nbsp;操作人：<input type="text" name="operName" value="" class="form" style="width:100" onKeyPress="if(window.event.keyCode==13){chaxun();}"/>
								         </td>
								         <td><input type="button" name="chaxunlogs0" id="id1" value="查询" onclick="chaxun()"  style="color:#014F8A"/>
								         </td>-->
							          </tr>
									    </table><br> --%>
										<table width="100%" border="0" cellspacing="1" cellpadding="1" >
										  <tr>
										     <td><ul id="ztree" class="ztree"></ul></td>
										  </tr>
										  <%--
       
									       	 ArrayList fylist = new ArrayList();
									       	 fylist = bean.getPageData(loginName);
									       	 allpage=bean.getAllPage();
											String agid="",agcode="",agname="",fagcode="",agrade="";
											int intnum=xh = pagesize*(curpage-1)+1;
											 if(fylist!=null)
											{
												int fycount = ((Integer)fylist.get(0)).intValue();
												for( int k = fycount;k<fylist.size()-1;k+=fycount){
									
											     //num为序号，不同页中序号是连续的
											     agid = (String)fylist.get(k+fylist.indexOf("AGID"));
											     System.out.println(agid);
													agcode = (String)fylist.get(k+fylist.indexOf("AGCODE"));
													System.out.println(agcode);
											    agname = (String)fylist.get(k+fylist.indexOf("AGNAME"));
											    System.out.println(agname);
											    fagcode = (String)fylist.get(k+fylist.indexOf("FAGCODE"));
											    agrade = (String)fylist.get(k+fylist.indexOf("AGRADE"));
											    String blankstr="";
													if(agrade.equals("1")){
													blankstr="&nbsp;";
												}else if(agrade.equals("2")){
													blankstr="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
												}else if(agrade.equals("3")){
													blankstr="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
												}else if(agrade.equals("4")){
													blankstr="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
												}
												String color=null;
									
												if(intnum%2==0){
												    color="#DDDDDD";
									
												 }else{
												    color="#FFFFFF" ;
												}
									
									        %>
									       <tr bgcolor="<%=color%>">
									       		
									       		
									       		<td  align = "left" >
									       			<%=blankstr%><input type="checkbox" name="rightItem"  value="<%=agcode%>" id="<%=agcode%>" onClick="fzqy('<%=agcode%>','<%=accountid%>',checked)" <%if(parea.indexOf(","+agcode+",")>-1){%>checked=true<%}%>/>
									       			<%=agname%>
									       		</td>
									
									       </tr>
									       <%} %>
									   
									       <%}--%>
							               <tr>
							                   <td>  
											       <div id="baocun" style="position: relative; width: 63px; height: 23px; cursor: pointer; float: left; left: 40%">
														<img alt=""src="<%=request.getContextPath() %>/images/baocun.png" width="100%" height="100%" />
														<span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">保存</span>
													</div>
													</td>
											      </tr>
	 	 									    </table> 
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
				   </td>
			  </tr>
		 </table>
      <br />
    </td>
    <td background="../../images/img_13.gif">&nbsp;</td>
  </tr>
</table>
<input type="hidden" name="accountid" value="<%=accountid%>"/>
<input type="hidden" name="agcodelist" value=""/>
</form>
</body>
<script language="javascript">
	var path = '<%=path%>';
	
    function fzqy(agcode,accountid,s){
    	//alert(agcode+"//"+accountid+"//"+s);
    	var ad="0";
			if(s){
				ad="1";
			}
			
    	//sendRequest(path+"/servlet/account?action=parea&agcode="+agcode+"&accountid="+accountid+"&ad="+ad,"parea");
    	discheck(agcode);
    }
    function changefz(){
    	var accountid='<%=accountid%>';
    	var i=document.form1.changeTitle.value;
    	if(i==2){
	    	document.form1.action=path+"/web/sys/per_zhandian.jsp?accountid="+accountid;
	        document.form1.submit();
        }else if(i==3){
        	document.form1.action=path+"/web/sys/per_areads.jsp?accountid="+accountid;
	        document.form1.submit();
        }
    }
    
    function discheck(agcode){
    	var len = agcode.length;
    	//alert(len);
    	if(document.getElementById(agcode).checked==true){
    		
    		//alert(agcode);
    		for(var j = 0;j < document.form1.elements.length ; j++){
    			if(document.form1.elements[j].value.substring(0,len-2)==agcode.substring(0,len-2)&&document.form1.elements[j].value.length<len&&document.form1.elements[j].value.length>4){
    				
    				//alert(document.form1.elements[j].value.length+"---");
    				document.form1.elements[j].checked=true;
    			}
    			if(document.form1.elements[j].value.substring(0,len-4)==agcode.substring(0,len-4)&&document.form1.elements[j].value.length<len-2&&document.form1.elements[j].value.length>4){
    				
    				//alert(document.form1.elements[j].value.length+"---");
    				document.form1.elements[j].checked=true;
    			}
          		if(document.form1.elements[j].value.substring(0,len)==agcode&&document.form1.elements[j].value.length>len){
          			//alert(document.form1.elements[j].value.substring(0,len));
          			//document.form1.elements[j].disabled=true;
          			document.form1.elements[j].checked=true;
          			
          		}
          	
           }
    	}else{
    		for(var j = 0;j < document.form1.elements.length ; j++){
          		if(document.form1.elements[j].value.substring(0,len)==agcode){
          			//document.form1.elements[j].disabled=false;
          			document.form1.elements[j].checked=false;
          		}
          	
           }
    	}
    }
    
    function clickNote(agcode,accountid,s){

         var len = agcode.length;
         if(document.getElementById(agcode).checked==true){
          	for(var j = 0;j < document.form1.elements.length ; j++){
          		if(document.form1.elements[j].value.substring(0,len)==agcode){
          			document.form1.elements[j].checked=true;
          		}
          		/*
          		if (document.form1.elements[j].length >=len && document.form1.elements[j].substring(0,len)==agcode){
          			
          			document.form1.elements[j].checked=true;
              }
              */
           }
           if(len>5){
           		var len2 = len-2;
           		while(len2>=5){
           			document.getElementById(agcode.substring(0,len2)).checked=true;
           			len2=len2-2;
           		}
          	}
         }else{
         	for(var j = 0;j < document.form1.elements.length ; j++){
          		if(document.form1.elements[j].value.substring(0,len)==agcode){
          			document.form1.elements[j].checked=false;
          		}
        	}
      }
          
	}//end function
	function saveAccount(){
	     var agcodelist=new Array();
	      for(var j = 0;j < document.form1.elements.length ; j++){
          		if(document.form1.elements[j].checked){
          			var agcode = document.form1.elements[j].value;
          			 agcodelist.push(agcode);
          			
          		}
           }
	       if(agcodelist.length==0){
        	  alert("请选择负责区域");
        	  return;
           }
           <%--sendRequest(path+"/servlet/account?action=parea&agcodelist="+agcodelist+"&accountid="+<%=accountid%>,"parea");--%>
          //	document.form1.action=path+"/servlet/account?action=per_area";
          
         	document.form1.action==path+"/servlet/account?action=parea";
         	document.form1.agcodelist.value = agcodelist;
         	document.form1.submit();
        };
     </script>
</html>


