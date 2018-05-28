<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" %>
<%@ page import="com.noki.util.CTime,java.util.List,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.query.basequery.javabean.AmmeterQueryBean"%>
<%@ page import="java.text.*"%>
<%
		String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        String loginName = account.getAccountName();
		String dbname = request.getParameter("dbname")!=null?request.getParameter("dbname"):"0";
		String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
	    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
	   // String entryTime1 = request.getParameter("entryTime1") != null ? request.getParameter("entryTime1") : "";	
        String zdqyzt=request.getParameter("zdqyzt") != null ? request.getParameter("zdqyzt") : "1";
        String dbqyzt=request.getParameter("dbqyzt") != null ? request.getParameter("dbqyzt") : "1";
        int intnum=0;
        String color="";
      //  String sheng = request.getParameter("sheng");
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
        //String sszy = request.getParameter("sszy")!=null?request.getParameter("sszy"):"0";
        //String dbyt = request.getParameter("dbyt")!=null?request.getParameter("dbyt"):"0";
       // String dllx = request.getParameter("dllx")!=null?request.getParameter("dllx"):"0";
       // String ydsb = request.getParameter("ydsb")!=null?request.getParameter("ydsb"):"0";
        String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"0";
       // String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"请选择";
        String zdlx1=request.getParameter("zdlx1")!=null?request.getParameter("zdlx1"):"";
       // String entrypensonnel = request.getParameter("entrypensonnel")!=null?request.getParameter("entrypensonnel"):"";
        //String entrytimeQS    = request.getParameter("entrytimeQS")!=null?request.getParameter("entrytimeQS"):"";//开始录入时间
        //String entrytimeJS    = request.getParameter("entrytimeJS")!=null?request.getParameter("entrytimeJS"):"";//结束录入时间        
        String canshuStr="";
          String loginId1 = request.getParameter("loginId");
//         if((shi!=null)||(xian!=null)||(xiaoqu!=null)||(sszy!=null)||(dbyt!=null)||(zdlx!=null)||(ydsb!=null)||(zdname!=null)||(dbname!=null)||(entrypensonnel!=null)||(entryTime1!=null)||((entrytimeQS!=null)&&(entrytimeJS!=null))){
//             canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&sszy="+sszy+"&dbyt="+dbyt+"&ydsb="+ydsb+"&zdname"+zdname+"&dbname"+dbname+"&zdlx"+zdlx+"&entrypersonnel="+entrypensonnel+"&entryTime1="+entryTime1+"&beginTimeQ="+entrytimeQS+"&endTimeQ="+entrytimeJS;
//         }
        String htmlsql = request.getParameter("htmlsql");
		String title = request.getParameter("title")!=null?request.getParameter("title"):"";
		String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
		
		String zdid1 = request.getParameter("zdid1")!=null?request.getParameter("zdid1"):"";//站点ID //2014-4-9新增
		String dbid1 = request.getParameter("dbid1")!=null?request.getParameter("dbid1"):"";//电表ID //2014-4-9新增

    //String roleId = account.getRoleId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
%>

<html>
<head>
<title>
logMange
</title>
<style>
         
            .style1 {
	color: red;
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
.relativeTag{     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
#div1{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div1 p{ float:left;font-size:12px; width:110px; cursor:pointer;}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.bttcn{color:BLACK;font-weight:bold;}
 </style>
<style type="text/css" media=print>.noprint{display : none }</style>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
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
	function delLogs(){
		var beginTime = document.form1.delBeginTime.value
                var endTime = document.form1.delEndTime.value

                if(checkNotnull(document.form1.delBeginTime,"开始时间")&&
                   checkNotnull(document.form1.delEndTime,"结束时间")){
                      if(beginTime>endTime){
                 	alert("开始时间不能大于结束时间！")
                         return
               		 }
                      if(confirm("确定要删除，不可恢复！")){
                 	  document.form1.action=path+"/servlet/log?delBeginTime="+beginTime+"&delEndTime="+endTime
                           document.form1.submit()
               		 }
                }

	}
	
		function payfee(degreeid){
          document.form1.action=path+"/web/dianliang/payElectricFees.jsp?degreeid="+degreeid;
                                document.form1.submit();
        }
	function adddegree(){
          document.form1.action=path+"/web/dianliang/addDegree.jsp";
                                document.form1.submit();
        }
    function delad(ammeterdegreeid){
       if(confirm("您确定删除此电量信息？")){
                    document.form1.action=path+"/servlet/ammeterdegree?action=del&degreeid="+ammeterdegreeid
        			document.form1.submit();
       }
    }
    function modifyad(ammeterdegreeid){
                    document.form1.action=path+"/web/dianliang/modifyDegree.jsp?degreeid="+ammeterdegreeid;
                    document.form1.submit();
       
    }
    function queryDegree(){ 
    	
                   if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null)
 		{
 		alert(document.getElementByName("shi").value);
	                 alert("城市不能为空");
	   
	   }
	   else
	   {
		  
                   document.form1.action=path+"/web/query/basequery/ammeterQuery.jsp?command=chaxun";
                   document.form1.submit();
            
       }
       
    }
    function getValue(va,sql){
       var general =document.getElementById("general");
       var htmlsql =document.getElementById("htmlsql");
       general.value=va;
       htmlsql.value = sql;    
    }  
   //页面载入方法
    function op(){
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian,ammeters&tab=jz,am&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
    }
   $(function(){

	$("#query").click(function(){
		queryDegree();
		showdiv("请稍等..........");
	});
	$("#daochuBtn").click(function(){
		exportad();
	});
	$("#dayinBtn").click(function(){
		printad();		
	});
 	$("#queding").click(function(){
		queding();		
	});
	$("#quxiao").click(function(){
		quxiao();		
	});
});
</script>
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
</head>

<jsp:useBean id="commaccountBean" scope="page" class="com.noki.mobi.common.CommonAccountBean">
</jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0502");

%>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		<tr >
					<td colspan="4" width="50"
												>
												 <div style="width:700px;height:50px">
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电表查询</span>	
												</div>
											</td>
		</tr>
				 <tr class="form_label">
								   <td height="20" colspan="4">
									<div id="parent" style="display: inline"><div style="width: 50px; display: inline;"><hr></div>
										&nbsp;过滤条件&nbsp;
										<div style="width: 300px; display: inline;"><hr></div>
									</div>
								   </td>
							    </tr>
							  		<tr>
							  		<td width="1200px">
							  		<table>
							  		<tr class="form_label">
		    		<td >城市：</td>
		    		
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
	         		</select><span class="style1">&nbsp;*</span></td>
	         		 <td>区县：</td>
									<td>
											          <select name="xian" class="selected_font" onchange="changeCountry()">
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
											         	</select>        
							          
							          </td>
							            <td>乡镇：</td>
							              <td>
							                   <select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
         	                                 </select>
							              </td>
	         		
	         			<td   class="form_label">	
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p>
					</td>
	         		<td>
							       <div id="query" style="position:relative;width:59px;height:23px;right:-160px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
							</td>
					</tr>
</table>
</td>
</tr>
						
						<tr>
						  <td colspan="5">
						  <div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table >

							    <tr class="form_label">					     
							    <td>电表名称：</td>
							      <td>
							           <input type="text" name="dbname" value="<%if(null!=request.getParameter("dbname")) out.print(request.getParameter("dbname")); %>" class="selected_font"/>
							     </td>
							           <td>站点名称：</td>
							              <td>
							              <input type="text" class="selected_font" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" class="form" 
                                               />
							              </td>
							              <td>
											站点启用状态：
										</td>
										<td>
											<select name="zdqyzt" class="selected_font">
												<option value="-1">
													请选择
												</option>
												<option value="0">
													否
												</option>
												<option value="1" selected="selected">
													是
												</option>

											</select>
										</td>
										
									<tr class="form_label">
										<td>
											电表启用状态：
										</td>
										<td>
											<select name="dbqyzt" class="selected_font">
												<option value="-1">
													请选择
												</option>
												<option value="0">
													否
												</option>
												<option value="1" selected="selected">
													是
												</option>

											</select>
										</td>
										
										<td>站点ＩＤ：</td>
                            			<td>
                            				<input type="text" name="zdid1" class="selected_font" 
                            					value="<%if(null!=request.getParameter("zdid1")) out.print(request.getParameter("zdid1")); %>"/>
                            			</td>
                            			
                            			<td>电表ＩＤ：</td>
					                    <td>
					                      	<input type="text" name="dbid1" class="selected_font" 
					                      		value="<%if(null!=request.getParameter("dbid1")) out.print(request.getParameter("dbid1")); %>" />
					                    </td>
									</tr>
		         </table>	
		     
		         </div>			
				 </td>
				</tr>
		</table>
		<table  height=23>
<tr><td height="20"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>
       <%
          AmmeterQueryBean bean = new AmmeterQueryBean();
         String whereStr = "";
         String str = "";          
           
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" AND SHI='"+shi+"'";
				str=str+" AND SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" AND XIAN='"+xian+"'";
				str=str+" AND XIAN='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" AND XIAOQU='"+xiaoqu+"'";
				str=str+" AND XIAOQU='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
				whereStr=whereStr+" AND JZ.JZNAME LIKE '%"+zdname+"%'";
			}
// 			if(sszy != null && sszy != "" && !sszy.equals("0")){
// 				whereStr=whereStr+" AND SSZY='"+sszy+"'";
// 				str=str+" AND SSZY='"+sszy+"'";
// 			}
// 			if(dbyt != null && dbyt != "" && !dbyt.equals("0")){
// 				whereStr=whereStr+" AND DBYT='"+dbyt+"'";
// 				str=str+" AND DBYT='"+dbyt+"'";
// 			}
// 			if(dllx != null && dllx != ""&& !dllx.equals("0")){
// 				whereStr=whereStr+" AND DLLX='"+dllx+"'";
// 				str=str+" AND DLLX='"+dllx+"'";
// 			}
			if(zdqyzt!= null && zdqyzt != ""&& !zdqyzt.equals("-1")){
				whereStr=whereStr+" AND jz.qyzt='"+zdqyzt+"'";
				str=str+" AND jz.qyzt='"+zdqyzt+"'";
			}if(dbqyzt!= null && dbqyzt != ""&& !dbqyzt.equals("-1")){
				whereStr=whereStr+" AND db.dbqyzt='"+dbqyzt+"'";
				str=str+" AND db.dbqyzt='"+dbqyzt+"'";
			}							
			//录入月份				
		
			if(dbname != null && dbname != "" && !dbname.equals("0")){
				whereStr=whereStr+" AND DBNAME LIKE '%"+dbname+"%'";
			}
			if(zdlx1!=null&&!zdlx1.equals("")&& !zdlx1.equals("0")){
			  whereStr=whereStr+" AND JZ.STATIONTYPE IN("+zdlx1+")";
			  str=str+" AND JZ.STATIONTYPE IN("+zdlx1+")";
			}
			if (zdid1 != null && !zdid1.equals("") && !zdid1.equals("0")) {
				whereStr = whereStr + " AND JZ.ID='" + zdid1 + "' ";
			}
			
			if(dbid1 != null && dbid1 != "" && !dbid1.equals("0")){
				whereStr = whereStr + " AND DB.DBID='" + dbid1 + "' ";
			}
			
		if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;
       	 	 shi="1";
       	 }
		if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
			ElectricFeesFormBean bean1 = bean.getCountt(whereStr,loginId);
			String count1 = bean1.getCount();
			String sumjs = bean1.getJsdb();
			String sumcj = bean1.getCaijizd();
			String sumgl = bean1.getGldb();
		%>
	
		
			<% }%>
			<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 	
	 			  <table width="2000px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">

                       <tr height = "23px" class="relativeTag">
                       <td width="2%" height="23"  bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>         			
                        <td width="2%" height="23"  bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
            			<td width="2%" height="23"  bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
                        <td width="2%" height="23"  bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>
                        <td width="2%" height="23"  bgcolor="#DDDDDD"><div align="center" class="bttcn">小区</div></td>
            			<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">初始读数</div></td>            			
            			<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">初始使用时间</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">倍率</div></td>
                        <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表型号</div></td> 
            			<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>
            			<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>
            			</tr>
     <%         
       	 List<ElectricFeesFormBean> fylist = null;
      if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
       fylist = bean.getPageDatapp(whereStr,loginId);
       	 allpage=bean.getAllPage();
       	// System.out.println(allpage);
		String id = "";
		//添加站点ID
		String zdid ="";
		String shil = "";
		String xianl = "";
		//添加乡镇
		String xiaoqu1 = "";
		String jzname = "";
		String professionaltypeid = "";
		String ammeteruse = "";
		String electriccurrenttype_ammeter = "";
		String usageofelectypeid_ammeter = "";
		String initialdegree = "";
		String initialdate = "";
		String multiplyingpower = "";
		String danjia = "";
		String ammetertype = "";
		String dbname1 = "";
		//String szdq = "";
		
		String xian2="";
		String xiaoqu2="";
		//电费支付类型
		String paylx = "";
		//线损类型
		String xslx = "";
		//线损值
		String xsz = "";
		//录入时间
		String lrtime = "";
		//录入人员
		String lrperson = "";
		//站点类型,站点启用状态
		String zdl="",zdqyzt1="";
		String dbqyzt1 = "",zbdyh="";
		int countxh=1;
		//int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			for(ElectricFeesFormBean bean1:fylist){

		     //num为序号，不同页中序号是连续的
		     
		    id = bean1.getDbid();
		    
		    zdid = bean1.getZdcode();
		    zdid = zdid != null ? zdid : "";
		    
		   zdl = bean1.getStationtype();
		    zdl = zdl != null ? zdl : "";
			
		    jzname = bean1.getJzname();
		    jzname = jzname != null ? jzname : "";
		    
		    professionaltypeid = bean1.getProfessionaltypeid();
		    professionaltypeid = professionaltypeid != null ? professionaltypeid : "";
		    
		    ammeteruse = bean1.getDbyt();
		    ammeteruse = ammeteruse != null ? ammeteruse : "";
		    
			electriccurrenttype_ammeter = bean1.getDllx();
			electriccurrenttype_ammeter = electriccurrenttype_ammeter != null ? electriccurrenttype_ammeter : "";
			if(electriccurrenttype_ammeter.equals("null")){
				electriccurrenttype_ammeter="";
			}
						
			usageofelectypeid_ammeter = bean1.getYdsb();
			usageofelectypeid_ammeter = usageofelectypeid_ammeter != null ? usageofelectypeid_ammeter : "";
			
			initialdegree = bean1.getCsds();
			initialdegree = initialdegree != null ? initialdegree : "";
			
			initialdate = bean1.getCssytime();
			initialdate = initialdate != null ? initialdate : "";
			if(initialdate=="0"||initialdate.equals("0")||initialdate.equals("null")){
		      initialdate="";
		    }			
			multiplyingpower = bean1.getBeilv();
			multiplyingpower = multiplyingpower != null ? multiplyingpower : "0";
			if(multiplyingpower==null||multiplyingpower.equals("")||multiplyingpower.equals("null")){
			multiplyingpower="0";
			}
			DecimalFormat prd = new DecimalFormat("0.00");
			multiplyingpower=prd.format(Double.parseDouble(multiplyingpower));			
			danjia = bean1.getUnitprice();
			//danjia = danjia != null ? danjia : "0.0000";
			if(danjia==null||danjia.equals("")||danjia.equals("null")){
				danjia="0.0000";
			}
			DecimalFormat dj = new DecimalFormat("0.0000");
			danjia = dj.format(Double.parseDouble(danjia));
			
			ammetertype = bean1.getDbxh();
			ammetertype = ammetertype != null ? ammetertype : "";
			
			dbname1 = bean1.getDbname();
			dbname1 = dbname1 != null ? dbname1 : "";
			
			//szdq = bean1.getSzdq();
			//szdq = szdq != null ? szdq : "";
			xian2 = bean1.getXian();
			xian2 = xian2 != null ? xian2 : "";
			
			xiaoqu2 = bean1.getXiaoqu();
			xiaoqu2 = xiaoqu2 != null ? xiaoqu2 : "";
			
			if(initialdegree==null||"".equals(initialdegree)||("null").equals(initialdegree)) 
			initialdegree="0";
			
			DecimalFormat price = new DecimalFormat("0.0");
			initialdegree = price.format(Double.parseDouble(initialdegree));
			//电费支付类型	
			paylx = bean1.getDfzflx();
			paylx = paylx != null ? paylx : "";
			if(paylx.equals("null")){
			paylx="";
			}
			
		    //线损类型
		    xslx = bean1.getLinelosstype();
			xslx = xslx != null ? xslx : "";
			if(xslx.equals("null")){
			xslx="";
			}
		    //线损值
		    xsz = bean1.getLinelossvalue();
			xsz = xsz != null ? xsz : "";
			if(xsz.equals("null")){
			xsz="";
			}
		    //录入时间
		    lrtime = bean1.getEntrytime();
			lrtime = lrtime != null ? lrtime : "";
			if(lrtime=="0"||lrtime.equals("0")||lrtime.equals("null")){
		      lrtime="";
		    }
		    //录入人员
		    //lrperson = bean1.getEntrypensonnel();
			lrperson = lrperson != null ? lrperson : "";
			if(lrperson.equals("null")){
			lrperson="";
			}	
			zdqyzt1 = bean1.getZdqyzt();
			if(zdqyzt1.equals("1")){
				zdqyzt1="启用";
			}else if(zdqyzt1.equals("0")){
				zdqyzt1="不启用";
			}else {
				zdqyzt1="";
			}
			
			dbqyzt1 = bean1.getDbqyzt();
			if(dbqyzt1.equals("1")){
				dbqyzt1="启用";
			}else if(dbqyzt1.equals("0")){
				dbqyzt1="不启用";
			}else {
				dbqyzt1="";
			}
			
			zbdyh = bean1.getDbzbdyhh();
			zbdyh = zbdyh != null ? zbdyh : "";
			if(zbdyh=="0"||zbdyh.equals("0")||zbdyh.equals("null")||zbdyh==null||"".equals(zbdyh)){
				zbdyh="";
			}
			
			if(intnum%2==0){
			    color="#FFFFFF";
			 }else{
			    color="#DDDDDD" ;
			}
           intnum++;
       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=countxh++%></div>
       		</td>      		
       		
       		<td>
       			<div align="left" ><%=dbname1%></div>
       		</td>
       		
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdl%></div>
       		</td>
       		<td>
       			<div align="left" ><%=xian2%></div>
       		</td>
       		<td>
       			<div align="left" ><%=xiaoqu2%></div>
       		</td>
       		<td>
       			<div align="right" ><%=initialdegree%></div>
       		</td>
       	    <td>
       			<div align="center" ><%=initialdate%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=multiplyingpower%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=ammetertype%></div>
       		</td>
       		<td>
       			<div align="center" ><%=id%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdid%></div>
       		</td>

       </tr>
       <%} %>
       <%} %>
       <%}%>
      <% if (intnum==0){
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
        </tr>
        <% }}%>
  	 </table> 
		</div>
		      <table width="100%" height="10%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
                    <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                        <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                    </div></td>
		      </tr>
			  <tr>
			   <td align="right">         
   							<!--  <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                      
                                <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px; " >
	 								 <img src="<%=path %>/images/daoru.png" width="100%" height="100%">
									 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
								</div>
                               <%} %>  --> 
                               <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                        
                               <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px;">
	 								 <img src="<%=path %>/images/daochu.png" width="100%" height="100%">
									 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
								</div>
                               <%} %> 
                           </td> 
  </tr>
  <tr>
  <td>
  <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
  <input type="hidden" name="sszy2" id="sszy2" value=""/>
  <input type="hidden" name="dbyt2" id="dbyt2" value=""/>
  <input type="hidden" name="dllx2" id="dllx2" value=""/>
  <input type="hidden" name="ydsb2" id="ydsb2" value=""/>
  <input type="hidden" name="dbname2" id="dbname2" value=""/>
  <input type="hidden" name="zdlx1" id="zdlx1" value=""/>
  <input type="hidden" name="ccz" id="ccz" value="<%=zdlx1%>"/>
  <input type="hidden" value="" id="htmlsql" name="htmlsql" />
  </td>
  </tr>
</table>
</form>
</body>
<script language="javascript">
	var path = '<%=path%>';
	function gopage()
     {
      document.form1.submit();
     }
     function previouspage()
     {
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage-1)%>';
      	document.form1.action=path+"/web/query/basequery/ammeterQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/query/basequery/ammeterQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;    
      		document.form1.action=path+"/web/query/basequery/ammeterQuery.jsp?curpage="+pageno+"<%=canshuStr%>";
      document.form1.submit();
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
	window.onload=function()
	{
		var oDiv=document.getElementById('div1');
		var oUl=oDiv.getElementsByTagName('ul')[0];
		var oP=oDiv.getElementsByTagName('p')[0];
		var bSwitch=false;
		
		oP.onclick=function()
		{
			if(bSwitch){
				oUl.style.display='none';
				bSwitch=false;
			}else{
				var obj=document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
				var zdlx2 = document.form1.zdlx1.value;
				if(zdlx2!=""&&zdlx2!=null){
					var zdlx3 = zdlx2.split(",");
					for(var i=0;i<obj.length;i++){
						var m = obj[i].value.toString().indexOf(",");
						var bm = obj[i].value.toString().substring(0,m);
						for(var j=0;j<zdlx3.length;j++){
							var zdlx4 = zdlx3[j].toString().substring(1,zdlx3[j].length-1);
							if(bm==zdlx4){
								obj[i].checked = true; 					
							}
						} 
			 
					} 
				} 
				oUl.style.display='block';
				bSwitch=true;
			}
		};

	};
	function queding(){
		var oDiv=document.getElementById('div1');
		var oUl=oDiv.getElementsByTagName('ul')[0];
		var obj=document.getElementsByName("CheckboxGroup1");//根据自己的多选框名称修改下
		var str1="";
		var str2="";
		for(var i=0;i< obj.length;i++){
			if(obj[i].checked){
				var m = obj[i].value.toString().indexOf(",");
				var bm = obj[i].value.toString().substring(0,m);
				var mc = obj[i].value.toString().substring(m+1,obj[i].value.toString().length);
				str1 = str1+"'"+bm+"',";
				str2 = str2+mc+",";
			}
		}
		str1=str1.substring(0,str1.length-1);
		str2=str2.substring(0,str2.length-1);
		document.form1.zdlx1.value=str1;
		//document.form1.zdlx.value=str2;
		oUl.style.display='none';
	}
	//取消选中   
	function quxiao(){ 
		var oDiv=document.getElementById('div1');
		var oUl=oDiv.getElementsByTagName('ul')[0];  
		var obj = document.getElementsByName("CheckboxGroup1");
		if(obj.length){
			for(var i=0;i<obj.length;i++){ 
				obj[i].checked = false;   
			}
			oUl.style.display='none';   
		}else{   
			obj.checked = false; 
			oUl.style.display='none';   
		}   
	}   

</script>
<script type="text/javascript">
function exportad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=str%>";
            var zdname = "<%=zdname%>";
            var dbname = "<%=dbname%>";
        	document.form1.action=path+"/web/query/basequery/电表信息.jsp?curpage="+curpage+"&whereStr="+whereStr+"&zdname="+zdname+"&dbname="+dbname+"&command=daochu";
            document.form1.submit();
   }
   function printad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=whereStr%>";
            //alert(canshuStr);
            window.open(path+"/web/query/basequery/ammeterquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr,'','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
      
        	//document.form1.action=path+"/web/query/basequery/ammeterdegreesquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr;
            //document.form1.submit();
   }
    document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.zdlx1.value=document.form1.ccz.value;	
		document.form1.zdqyzt.value='<%=zdqyzt%>';
		document.form1.dbqyzt.value='<%=dbqyzt%>';
 </script>
</html>