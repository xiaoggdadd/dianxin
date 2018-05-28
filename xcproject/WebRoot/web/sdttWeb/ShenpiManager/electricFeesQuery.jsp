<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.ElectricFeesQueryBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.util.regex.Pattern"%>
<%
        String KJYF           = request.getParameter("KJYF")!= null ? request.getParameter("KJYF"): "";
        String bztime         = request.getParameter("bztime")!= null ? request.getParameter("bztime"): "";
	    String entryTime1       = request.getParameter("entryTime1") != null ? request.getParameter("entryTime1") : "";
	    String beginTime      = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
        String endTime        = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
	    String title          = request.getParameter("title")!=null?request.getParameter("title"):"";
	    String zdid          = request.getParameter("zdid")!=null?request.getParameter("zdid"):"";
	    String operName       = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	    String dfzflxtj       = request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";//电费支付类型
	     String gdfstj       = request.getParameter("gdfs")!=null?request.getParameter("gdfs"):"0";//供电方式
        String path           = request.getContextPath();
        Account account       = (Account)session.getAttribute("account");
        String loginId        = account.getAccountId();
        String loginId1       = request.getParameter("loginId");
        String loginName = account.getAccountName();
        int intnum=0;
    	String color="";
    	String sheng          = (String)session.getAttribute("accountSheng");
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
		String shi            = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
		String xian           = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
		String xiaoqu         = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
        String entrytimeQS    = request.getParameter("entrytimeQS")!=null?request.getParameter("entrytimeQS"):"";//开始录入时间
        String entrytimeJS    = request.getParameter("entrytimeJS")!=null?request.getParameter("entrytimeJS"):"";//结束录入时间
        String zdname         =request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
        String autoauditstatus = request.getParameter("autoauditstatus")!=null?request.getParameter("autoauditstatus"):"";
        String citystatus     = request.getParameter("citystatus")!=null?request.getParameter("citystatus"):"";
        String manauditstatus = request.getParameter("manauditstatus")!=null?request.getParameter("manauditstatus"):"";
        String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"请选择";
        String zdlx1 = request.getParameter("zdlx1")!=null?request.getParameter("zdlx1"):"";
        String htmlsql        = request.getParameter("htmlsql");
        String entrypensonnel = request.getParameter("entrypensonnel")!=null?request.getParameter("entrypensonnel"):"";//录入人员
         String liuch = request.getParameter("liuch")!=null?request.getParameter("liuch"):"";//流程号
         String blhdl1=request.getParameter("blhdl1")!=null?request.getParameter("blhdl1"):"";
         String dianfeidd=request.getParameter("dianfeidd")!=null?request.getParameter("dianfeidd"):"";
       String qyzt=request.getParameter("qyzt")!=null?request.getParameter("qyzt"):"1";
       String dbqyzt=request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"1";
       String canshuStr      ="";
       String dbname         =request.getParameter("dbname")!=null?request.getParameter("dbname"):"";//电表名称

       
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((entrytimeQS!=null)&&(entrytimeJS!=null))||(zdlx!=null)||(autoauditstatus!=null)||(manauditstatus!=null)||(htmlsql!=null)||(entrypensonnel!=null)||(liuch!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&beginTimeQ="+entrytimeQS+"&endTimeQ="+entrytimeJS+
         "&autoauditstatus="+autoauditstatus+"&manauditstatus="+manauditstatus+"&htmlsql="+htmlsql+"&zdlx"+zdlx+"&entrypersonnel="+entrypensonnel+"&liuch="+liuch;
     }
        String s_curpage      = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
        String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    
    double ds=0.0,dfs=0.0;
    double dfz=0.0,dfzs=0.0;
%>

<html>
<head>


<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
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
	function adddegree(){
          document.form1.action=path+"/web/electricfees/addElectricFees.jsp";
          document.form1.submit();
        }
    function delad(electricfeeId){
       if(confirm("您确定删除此电费信息？")){
                    document.form1.action=path+"/servlet/electricfees?action=del&degreeid="+electricfeeId
        			document.form1.submit()
       }
    }
    function modifyad(electricfeeId){
                    document.form1.action=path+"/web/electricfees/modifyElectricFees.jsp?degreeid="+electricfeeId;
                    document.form1.submit();
       
    }
    
    function queryDegree(){
                
             if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null)
		{
	                 alert("城市不能为空");
	   
	   }else{
	   var blhdl1=document.form1.blhdl1.value;
	   var dianfeidd=document.form1.dianfeidd.value;
	   		    //var pattern = /(^[0-9]\d+$)|(^\d+\.?\d*$)/;
                // var re= /^[1-9]\d*\.?\d*$/;"?[0-9]*.?[0-9]*" "\\d+([.]\\d+)?"
                //!"NaN".eval(Number(blhd).toString())
                var re=/(^\d+([.]\d+$))|(^\d+$)/;
			var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
	   		var et = document.form1.entryTime1.value;
				if(reg1.exec(et)||et==""||et==null){
				if(re.exec(blhdl1)||blhdl1==null||blhdl1==""){
					if(re.exec(dianfeidd)||dianfeidd==null||dianfeidd==""){
                   document.form1.action=path+"/web/query/basequery/electricFeesQuery.jsp?command=chaxun";                       
                   document.form1.submit();
                   showdiv("请稍等..............");
                   }else{
                	   alert("您输入的电费有误，请确认后重新输入！");
                   }
                   }else{
                   alert("您输入的实际用电量有误，请确认后重新输入！");
                   }
                }else{
						alert("您输入的录入月份有误，请确认后重新输入！");
				}
       }
    }
    //查询详细信息
    function information(dbid,dlid){  	
    	window.open(path+"/web/query/basequery/showdfxx.jsp?dbid="+dbid+"&dlid="+dlid,'','width=500,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
    }
      //批量导入
  function exportFData(){
          document.form1.action=path+"/web/electricfees/input.jsp";
                                document.form1.submit();
  }
    function getValue(va,sql){
       var general =document.getElementById("general");
       var htmlsql =document.getElementById("htmlsql");
       general.value=va;
       htmlsql.value = sql;    
    }  
      
      //页面载入方法
    function op(){
    alert("dd");
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian,ammeters,ammeterdegrees,electricfees&tab=zd,am,ad,ef&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
    }
$(function(){    
	$("#query").click(function(){
		queryDegree();
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
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>

<%
permissionRights=commBean.getPermissionRight(roleId,"0504");

%>
<body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
	
    <table  width="100%"  border="0" cellspacing="0" cellpadding="0" height="20%">
	 <tr>
       <td colspan="4" width="50">
												 <div style="width:700px;height:50px">
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电费查询</span>	
												</div>
											</td>
    </tr>
    <tr>
    	<td height="20" colspan="4"><div id="parent" style="display:inline">
        <div style="width:50px;display:inline;"><hr></div><font size="2">过滤条件</font><div style="width:300px;display:inline;"><hr></div>
        </div></td>
    </tr>
  	<tr >
  	<td width="1200px">
  	<table>
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
	         		</select><span class="style1">&nbsp;*</span></td>
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
                                          <td> 乡镇：</td>         
                                       <td> <select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
	         			<td  class="form_label">	
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p>
					</td>
	         		<td >
							       <div id="query" style="position:relative;width:59px;height:23px;right:-250px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
							</td>
					
					</tr>
					</table>
					</td>
					</tr>
   			 <tr >
   		 		<td colspan="8">
					<div style="width:99%;" > 
					  <p id="box3" style="display:none">
					<table>
		    	<tr class="form_label">
                 
                              <td> 市级审核状态：</td>
                              <td><select name="citystatus" class="selected_font" onchange="javascript:document.form1.citystatus2.value=document.form1.citystatus.value">
         		                           <option value="2">请选择</option>
         		                           <option value="0">未通过</option>
         		                           <option value="1">通过</option>
         		                            <option value="-2">不通过</option>
         		
                                     </select>
                              </td> 
                              <td>开始录入时间：</td>
                        <td><input type="text" class="selected_font" name="entrytimeQS" value="<%if(null!=request.getParameter("entrytimeQS")) out.print(request.getParameter("entrytimeQS")); %>" onFocus="getDateString(this,oCalendarChs)" /></td>                 
                            <td>结束录入时间：</td>
                        <td><input type="text" class="selected_font" name="entrytimeJS" value="<%if(null!=request.getParameter("entrytimeJS")) out.print(request.getParameter("entrytimeJS")); %>" onFocus="getDateString(this,oCalendarChs)"/> </td>       
                        <td>  人工审核状态：</td>            
         	                          <td><select name="manauditstatus" class="selected_font" onchange="javascript:document.form1.manauditstatus2.value=document.form1.manauditstatus.value">
         		                         <option value="3">请选择</option>
         		                         <option value="1">人工通过</option>
         		                         <option value="2">财务通过</option>
         		                         <option value="0">人工未通过</option>
         		                         <option value="-2">人工不通过</option>
         		                         <option value="-1">财务未通过</option>
         	                          </select></td>  
                           
                            </tr>	
    <tr class="form_label">
                       
         	   
                                       <td>自动审核状态：</td>
                                        <td><select name="autoauditstatus" id="zd" class="selected_font" onchange="javascript:document.form1.autoauditstatus2.value=document.form1.autoauditstatus.value">
         		                         <option value="2">请选择</option>
         		                         <option value="1">通过</option>
         		                         <option value="0">未通过</option>
         	                          </select></td>
         	                           <td> 报账月份：</td>
                              <td>
                              <input type="text" class="selected_font" name="bztime" value="<%if (null != request.getParameter("bztime"))out.print(request.getParameter("bztime"));%>"
		                         onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
                              </td> 
							<td>站点类型：</td><td>
						      	<div id="div1">
					      			<p><input type="text" class="selected_font" readonly= "true" name="zdlx" value="请选择"/></p>
				      				<ul>
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
							                    <li><input type="checkbox" name="CheckboxGroup1" value="<%=code%>,<%=name%>" id="CheckboxGroup1" /><%=name%></li>
							                    <%}
								         	}
								         %>
								         	<li><input type="button" name="queding" id="queding"value="确定" /><input type="button" name="quxiao" id="quxiao" value="取消" /></li>
	                                  </ul>									
		                         </div>	
						     </td>
						     <td>电表启用状态：</td>
                       <td><select name="dbqyzt" id="db" class="selected_font" >
         		             <option value="-1">请选择</option>
         		             <option value="1">是</option>
         		              <option value="0">否</option>
         	                </select></td>
                               </tr>
                               <tr class="form_label">
                                
                          
                            <td>站点名称：</td>
                            <td><input type="text" name="zdname" class="selected_font" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>"/>
                            <td>站点ID：</td>
                            <td><input type="text" name="zdid" class="selected_font" value="<%if(null!=request.getParameter("zdid")) out.print(request.getParameter("zdid")); %>"/>
                            </td>
                            <td>录入月份：</td>
                        	<td><input type="text" name="entryTime1" class="selected_font"  value="<%if (null != request.getParameter("entryTime1")) out.print(request.getParameter("entryTime1"));%>" onFocus="getDatenyString(this,oCalendarChsny)"/></td>
                            <td>录入人员：</td><td><input type="text" class="selected_font" name="entrypensonnel" value="<%if(null!=request.getParameter("entrypensonnel")) out.print(request.getParameter("entrypensonnel")); %>" /></td>
                             </tr>

                           <tr class="form_label">
                             <td>站点启用状态：</td>
                       <td><select name="qyzt" id="zd" class="selected_font" >
         		              <option value="-1">请选择</option>
         		             <option value="1">是</option>
         		              <option value="0">否</option>
         	                </select></td>
                           
                           <td>电费支付类型：</td>
                       <td><select name="dfzflx"  class="selected_font" >
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList dfzflxa = new ArrayList();
			         	dfzflxa = ztcommon.getSelctOptions("DFZFLX");
			         	if(dfzflxa!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)dfzflxa.get(0)).intValue();
			         		for(int i=cscount;i<dfzflxa.size()-1;i+=cscount)
		                    {
		                    	code=(String)dfzflxa.get(i+dfzflxa.indexOf("CODE"));
		                    	name=(String)dfzflxa.get(i+dfzflxa.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
    	
    		         </select></td>	
                       <td> 流程号：</td><td><input type="text" name="liuch" value="<%if(null!=request.getParameter("liuch")) out.print(request.getParameter("liuch")); %>" class="selected_font"/></td>
                         <td>耗电量大于</td>
                        <td><input type="text" id="blhdl1" name="blhdl1" class="selected_font" value="<%if(null!=request.getParameter("blhdl1")) out.print(request.getParameter("blhdl1")); %>">度</td>
                          </tr>
 						<tr class="form_label">
                         <td>电费大于</td>
                        <td><input type="text" id="dianfeidd" name="dianfeidd" class="selected_font" value="<%if(null!=request.getParameter("dianfeidd")) out.print(request.getParameter("dianfeidd")); %>">元</td>
                         
                  
                           <td>供电方式：</td>
                       <td><select name="gdfs"  class="selected_font" >
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList gdfsb = new ArrayList();
			         	gdfsb = ztcommon.getSelctOptions("GDFS");
			         	if(gdfsb!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)gdfsb.get(0)).intValue();
			         		for(int i=cscount;i<gdfsb.size()-1;i+=cscount)
		                    {
		                    	code=(String)gdfsb.get(i+gdfsb.indexOf("CODE"));
		                    	name=(String)gdfsb.get(i+gdfsb.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
    	
    		         </select></td>	
    		          <td> 财务月份：</td>
                      <td>
                          <input type="text" class="selected_font" name="KJYF" value="<%if (null != request.getParameter("KJYF"))out.print(request.getParameter("KJYF"));%>"
		                         onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
                              </td> 
                              
                       <td>电表名称：</td>
                       <td><input type="text" name="dbname" class="selected_font" value="<%if(null!=request.getParameter("dbname")) out.print(request.getParameter("dbname")); %>"/>
                            
                    
         	          
    		         </tr>
                        </table>
                        </p>
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
  String whereStr = "";
  String str="";
  ElectricFeesQueryBean bean = new ElectricFeesQueryBean();
		if(shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr=whereStr+" AND ZD.SHI='"+shi+"'";
			str=str+" AND ZD.SHI='"+shi+"'";
		}
		if(blhdl1 != null && !blhdl1.equals("")){
			whereStr=whereStr+" AND AD.BLHDL >'"+blhdl1+"'";
			str=str+" AND AD.BLHDL >'"+blhdl1+"'";
			
		}
		if(dianfeidd != null && !dianfeidd.equals("")){
			whereStr=whereStr+" AND EF.ACTUALPAY >'"+dianfeidd+"'";
			str=str+" AND EF.ACTUALPAY >'"+dianfeidd+"'";
		
		}
		if(xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr=whereStr+" AND ZD.XIAN='"+xian+"'";
			str=str+" AND ZD.XIAN='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
			whereStr=whereStr+" AND ZD.XIAOQU='"+xiaoqu+"'";
			str=str+" AND ZD.XIAOQU='"+xiaoqu+"'";
		}
		if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
			whereStr=whereStr+" AND ZD.JZNAME LIKE '%"+zdname+"%'";
		}
		if(zdid != null && !zdid.equals("") && !zdid.equals("0")){
			whereStr=whereStr+" AND ZD.ID='"+zdid+"'";
			str=str+" AND ZD.ID='"+zdid+"'";
		}
		if(qyzt!=null && !qyzt.equals("") && !qyzt.equals("-1")){
			whereStr = whereStr+"AND ZD.QYZT='"+qyzt+"'";
			str = str+"AND ZD.QYZT='"+qyzt+"'";
		}
		if(dbqyzt!=null&&!dbqyzt.equals("")&&!dbqyzt.equals("-1")){
			whereStr = whereStr+"AND D.DBQYZT='"+dbqyzt+"'";
			str = str+"AND D.DBQYZT='"+dbqyzt+"'";
		}
		if(dfzflxtj != null && !dfzflxtj.equals("") && !dfzflxtj.equals("0")){
			whereStr=whereStr+" AND D.DFZFLX='"+dfzflxtj+"'";
			str=str+" AND D.DFZFLX='"+dfzflxtj+"'";
		}
		if(gdfstj != null && !gdfstj.equals("") && !gdfstj.equals("0")){
			whereStr=whereStr+" AND ZD.GDFS='"+gdfstj+"'";
			str=str+" AND ZD.GDFS='"+gdfstj+"'";
		}
		
		//录入人员
		if(entrypensonnel != null && !entrypensonnel.equals("")&& !entrypensonnel.equals("0")){
			whereStr=whereStr+" AND (EF.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"+entrypensonnel+"'))";
			str=str+" AND (EF.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"+entrypensonnel+"'))";
		}
		if(entrytimeQS != null && !entrytimeQS.equals("") && !entrytimeQS.equals("0")){
			entrytimeQS = entrytimeQS+" 00:00:00";
			whereStr=whereStr+" AND TO_CHAR(EF.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')>='"+entrytimeQS+"'";
			str=str+" AND TO_CHAR(EF.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')>='"+entrytimeQS+"'";
		}
		if(entrytimeJS != null && !entrytimeJS.equals("") && !entrytimeJS.equals("0")){
			entrytimeJS = entrytimeJS+" 24:00:00";
			whereStr=whereStr+" AND TO_CHAR(EF.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')<='"+entrytimeJS+"'";
			str=str+" AND TO_CHAR(EF.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss')<='"+entrytimeJS+"'";
		}
		if(autoauditstatus != null && !autoauditstatus.equals("") && !autoauditstatus.equals("2")){		     
			whereStr=whereStr+" AND EF.AUTOAUDITSTATUS='"+autoauditstatus+"'";
			str=str+" AND EF.AUTOAUDITSTATUS='"+autoauditstatus+"'";
		}
		if(bztime != null && bztime != "" && !bztime.equals("0")){
				whereStr=whereStr+" AND TO_CHAR(EF.ACCOUNTMONTH,'yyyy-mm') ='"+bztime+"'";
				str=str+" AND TO_CHAR(EF.ACCOUNTMONTH,'yyyy-mm') ='"+bztime+"'";
		}
		if(KJYF != null && KJYF != "" && !KJYF.equals("0")){
				whereStr=whereStr+" AND TO_CHAR(EF.KJYF,'yyyy-mm') ='"+KJYF+"'";
				str=str+" AND TO_CHAR(EF.KJYF,'yyyy-mm') ='"+KJYF+"'";
		}
		if(liuch != null && liuch != "" && !liuch.equals("0")){
			whereStr=whereStr+" AND EF.LIUCHENGHAO = '"+liuch+"'";
			str=str+" AND EF.LIUCHENGHAO = '"+liuch+"'";
		}
		
		if(entryTime1 != null && entryTime1 != "" && !entryTime1.equals("0")){
					int months =0;
					int years =0;
					int month1 = Integer.parseInt(entryTime1.substring(5,7))+1;
					int year1 = Integer.parseInt(entryTime1.substring(0,4));
					if(month1>12){
			        	 years = year1+1;
			        	 months = month1 - 12;
			         }else{
			         	years = year1;
			         	months = month1;
			         }
			         String month = "";
			         if(months<10){
			        	 month="0"+Integer.toString(months);				        	 
			         }else{
			         	 month=Integer.toString(months);
			         }
			         String year=Integer.toString(years);        
        		String entryTime2 = entryTime1 + "-01 00:00:00";
        		String entryTime3 = year+"-" + month + "-01 00:00:00";
			whereStr=whereStr+" AND TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') >='"+entryTime2+"'"+" AND TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') <'"+entryTime3+"'";
			str=str+" AND TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') >='"+entryTime2+"'"+" AND TO_CHAR(AD.ENTRYTIME,'yyyy-mm-dd hh24:mi:ss') <'"+entryTime3+"'";
		}
		if(manauditstatus != null && !manauditstatus.equals("") && !manauditstatus.equals("3")){
		      if("1".equals(manauditstatus)){
			     whereStr=whereStr+" AND EF.MANUALAUDITSTATUS>='"+manauditstatus+"'";
			      str=str+" AND EF.MANUALAUDITSTATUS>='"+manauditstatus+"'";
	    }
       if("2".equals(manauditstatus)){
		    whereStr=whereStr+" AND EF.MANUALAUDITSTATUS='"+manauditstatus+"'";
		    str=str+" AND EF.MANUALAUDITSTATUS='"+manauditstatus+"'";
	   }
      if("0".equals(manauditstatus)){
	       whereStr=whereStr+" AND EF.MANUALAUDITSTATUS='"+manauditstatus+"'";
	      str=str+" AND EF.MANUALAUDITSTATUS='"+manauditstatus+"'";
      }
      if("-2".equals(manauditstatus)){
	      whereStr=whereStr+" AND EF.MANUALAUDITSTATUS='"+manauditstatus+"'";
	      str=str+" AND EF.MANUALAUDITSTATUS='"+manauditstatus+"'";
      }
      if("-1".equals(manauditstatus)){
	       whereStr=whereStr+" AND EF.MANUALAUDITSTATUS<'2' ";
	      str=str+" AND EF.MANUALAUDITSTATUS<'2' ";
      }
		}
		if(zdlx1!=null&&!zdlx1.equals("")&& !zdlx1.equals("0")){
		  whereStr=whereStr+" and ZD.STATIONTYPE IN("+zdlx1+")";
		  str=str+" and ZD.STATIONTYPE IN("+zdlx1+")";
		}
		if(citystatus!=null&&!citystatus.equals("")&&!citystatus.equals("2")){		
			whereStr=whereStr+" AND EF.CITYAUDIT='"+citystatus+"'";
		    str=str+" AND EF.CITYAUDIT='"+citystatus+"'";
		}	
		if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;
       	 	shi="1";
       	 }
		
		if(dbname != null && !dbname.equals("") && !dbname.equals("0")){
			whereStr=whereStr+" AND D.DBNAME LIKE '%"+dbname+"%'";
		}
		//if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){			
			 //String zong="",shen="",weishen="",caiwu="",count5="",count6="";
			// ElectricFeesFormBean bean1=bean.getCount(whereStr,loginId);	
			/* zong = bean1.getCount();
			 zong = zong != null ? zong : "0";
			 
			 shen = bean1.getRg();
			 shen = shen != null ? shen : "0";			 
			 if("".equals(shen)){shen="0";}
			 
			 weishen = bean1.getRgno();
			 weishen = weishen != null ? weishen : "0";
			 if("".equals(weishen)){weishen="0";}
			 
			 caiwu = bean1.getCw();
			 caiwu = caiwu != null ? caiwu : "0";
			 if("".equals(caiwu)){caiwu="0";}
			 
			 count5 = bean1.getBlhdl();
			 count5 = count5 != null ? count5 : "0";
			 if("".equals(count5)){count5="0";}
			 
			 count6 = bean1.getActualpay();
			 count6 = count6 != null ? count6 : "0";
		     if("".equals(count6)){count6="0";}	*/	
		
		/*if(count5==null||count5==""||"".equals(count5)||"null".equals(count5)){
		   count5="0.00";
		}else{
		    DecimalFormat countdl=new DecimalFormat("0.0");
		    count5=countdl.format(Double.parseDouble(count5));
		}
		if(count6==null||count6==""||"".equals(count6)||"null".equals(count6)){
		   count6="0.00";
		}else{
		    DecimalFormat countd2=new DecimalFormat("0.00");
		    count6=countd2.format(Double.parseDouble(count6));
		}*/
  %><%--
		<table height="5%">
          <tr>
  	        <td><font size="2">总共</font></td><td><font size="2" color="red"><%=zong %></font><font size="2">条  |</font></td>
  	        <td><font size="2">未审核</font></td><td><font size="2" color="red"><%=weishen %></font><font size="2">条 |</font></td>
  	        <td><font size="2">人工审核</font></td><td><font size="2" color="red"><%=shen %></font><font size="2">条 |</font></td>
  	        <td><font size="2">财务审核</font></td><td><font size="2" color="red"><%=caiwu %></font><font size="2">条 |</font></td>
  	        <td><font size="2">折算后用电量总和</font></td><td><font size="2" color="red"><%=count5 %></font><font size="2">度 |</font></td>
  	        <td><font size="2">电费总和</font></td><td><font size="2" color="red"><%=count6 %></font><font size="2">元</font></td>
  	       
  	      </tr>
		</table>
	
<%} %>
		--%><div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 	
	 			  <table width="5800px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
         	<tr height = "23" class="relativeTag ">
            <td width="1%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
             <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">区县</div></td>
             <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">乡镇</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>
             <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>
           
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td> 
             <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务月份</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">供电方式</div></td>  
            <td width="1.9%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始电表数</div></td>
             
  			<td width="1.9%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束电表数</div></td>  			
  			<td width="1.9%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td> 
            <td width="1.9%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
            <td width="1.9%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">折算后用电量</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量调整</div></td>
             <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">（电量）备注</div></td>     
             <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">额定耗电量</div></td>         
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始月份</div></td>            
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束月份</div></td>  
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次单价</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费调整</div></td>
             <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">（电费）备注</div></td>
            
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际电费</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结算周期</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">额定电费</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">与额定电费差</div></td>
            <td width="1.9%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">与额定电费比值</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">直流负荷</div></td> 
            <td width="1.9%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">直流负荷估算电量</div></td>
             <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交流负荷</div></td>
            <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入时间</div></td>             
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入人员</div></td> 
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">自动审核状态</div></td>
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核状态</div></td>
  			<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核时间</div></td>
  			
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核员</div></td>  			
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市级审核状态</div></td>
  			<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市级审核时间</div></td>
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市级审核员</div></td>
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务审核状态</div></td>
  			
  			<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务审核时间</div></td>  			
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务审核员</div></td> 
  			
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">倍率</div></td>
             
           
           
  			 
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据时间</div></td>
             
                    
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">开票时间</div></td>  
           
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
           
              
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">是否标杆</div></td> 
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点别名</div></td> 
                         
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">流程号</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据编号</div></td>
            
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据类型</div></td>            
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交费时间</div></td> 
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">缴费操作员</div></td> 
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>

  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">原站点ID</div></td>

  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">自报电用户号</div></td>

        </tr>
       <%
       	 List<ElectricFeesFormBean> fylist = null;
       	 if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;  
       	 	shi="1";
       	 }
       	 if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
       	 String bzw="";
       		 if(!xiaoqu.equals("0")||!"".equals(xiaoqu)){
       		 bzw="1";
       	 }
       	 fylist = bean.getPageData(curpage,whereStr,loginId,bzw);
       	 allpage=bean.getAllPage();
       	 String lastdegree = "", thisdegree = "",liuchenghao="",paydatetime = "";
 		String startmonth="",endmonth="",jzname = "",jzcode = "",danjia="",dftiaozheng="",dianfei="",pjbianhao="",caozuoyuan="",dbid="",pjleixing="",szxian="",szxq="",financialoperator="";
 		String dfzflx = "",bgsign = "",bieming = "",bl = "",lastdatatime = "",thisdatatime = "";
 		String ydltz = "",zshydl = "",dlbz = "",pjsj = "",bzyf = "",kpsj = "",dfbz = "",edhdl = "",jszq="0";
 		String lastdatetime="",thisdatetime="",manualauditstatus1="",manualauditdatetime="",manualauditname="",financialdatetime="",cityaudit="";
		String autoauditstatus1="",kjyf="",jlfh="";//会计月份，交流负荷
 		String lrry="",lrsj="",yid="",gdfs="";
		String zdl="";
		String zlfhgs="";//直流负荷估算电量
		//市级审核时间、市级审核人员、财务审核状态
		String sjtime="";
		String sjshy="";
		String cwzt="";
		//直流负荷
		String zlfh="";
		//电流id
		String dlid="",dbzbdyhh="";
		int countxh=1;
		double df=0.0;
		Double bl1=0.00;
		
		String dbmc="";//新增:电表名称
	if(fylist!=null){		
		for(ElectricFeesFormBean bean1:fylist){
            //=================
          sjshy = bean1.getCityauditpensonnel();
          sjshy=sjshy!=null?sjshy:"";
          if("null".equals(sjshy)){
          	sjshy="";
          }
          sjtime = bean1.getCityaudittime();
          sjtime=sjtime!=null?sjtime:"";
          if("null".equals(sjtime)){
          	sjtime="";
          }
		    jzcode = bean1.getZdcode();
		    jzcode = jzcode != null ? jzcode : "";
		    
		    jzname = bean1.getJzname();
		    jzname = jzname != null ? jzname : "";
		    
		    dbmc = bean1.getDbname();
		    dbmc = dbmc != null ? dbmc : "";
		    
		     yid = bean1.getYid();
		    yid = yid != null ? yid : "";
		    
		    zdl = bean1.getStationtype();
			zdl=zdl!=null?zdl:"";
			
			kjyf=bean1.getKjyf();
			if("null".equals(kjyf)||"".equals(kjyf)||kjyf==null)kjyf="";
			//kjyf=kjyf!=null?kjyf:"";
			
			jlfh=bean1.getJlfh();
			if("null".equals(jlfh)||"".equals(jlfh)||jlfh==null)jlfh="0";
			//System.out.println("-----"+kjyf+jlfh);
			
			dbzbdyhh = bean1.getDbzbdyhh();
			dbzbdyhh=dbzbdyhh!=null?dbzbdyhh:"";
			
		    szxian=bean1.getXian();
		    szxian=szxian!=null?szxian:"";
		    
		    szxq=bean1.getXiaoqu();
		    szxq=szxq!=null?szxq:"";
		    
		    lrry = bean1.getEntrypensonnel();
		    lrry=lrry!=null?lrry:"";
		    if("null".equals(lrry)){
		    	lrry="";
		    }
		    lrsj = bean1.getEntrytime();
		    lrsj = lrsj != null ? lrsj : ""; 
		    if(lrsj=="0"||lrsj.equals("0")||lrsj.equals("null")){
		      lrsj="";
		    }
		     
		    //新加
		    edhdl = bean1.getEdhdl();//额定耗电量
		    edhdl = edhdl != null ? edhdl : "0";
		    if(null==edhdl||"null".equals(edhdl)||edhdl.equals("")||"o".equals(edhdl)||" ".equals(edhdl))edhdl="0";
		    
		    dfzflx = bean1.getDfzflx();
		    dfzflx = dfzflx != null ? dfzflx : ""; 
		    
		   
		    
		    bgsign = bean1.getBgsign();
		    bgsign = bgsign != null ? bgsign : ""; 
		    if("1".equals(bgsign)){
		     	bgsign="是";
		    }else {
		    	bgsign="否";
		    }
		    
		    bieming = bean1.getBieming();
		    bieming = bieming != null ? bieming : "";
		    
		    
		    
		   
		    bl = bean1.getBeilv();
		    bl = bl != null ? bl : ""; 		    
		    DecimalFormat mat1=new DecimalFormat("0.00");
		    if(bl==null||bl==""||bl=="o"||"null".equals(bl)||" ".equals(bl))bl="0";
            bl1=Double.parseDouble(bl);
            bl=mat1.format(bl1);
		    
		    lastdatatime = bean1.getLastdatetime();//上次抄表时间
		    lastdatatime = lastdatatime != null ? lastdatatime : ""; 
		    if(lastdatatime=="0"||lastdatatime.equals("0")||lastdatatime.equals("null")||lastdatatime.equals(" ")){
		      lastdatatime="";
		    }
		    
		    thisdatatime = bean1.getThisdatetime();//本次抄表时间
		    thisdatatime = thisdatatime != null ? thisdatatime : ""; 
		    if(thisdatatime=="0"||thisdatatime.equals("0")||thisdatatime.equals("null")||thisdatatime.equals(" ")){
		      thisdatatime="";
		    }
		    
		    ydltz = bean1.getFloatdegree();
		    ydltz = ydltz != null ? ydltz : "";
		     
		      
		    
		    zshydl = bean1.getBlhdl();
		    zshydl = zshydl != null ? zshydl : ""; 
		    
		   
		    
		    dlbz = bean1.getMemo();
		    dlbz = dlbz != null ? dlbz : "";  
		    
		    pjsj = bean1.getNotetime();
		    pjsj = pjsj != null ? pjsj : "";
		    if(pjsj=="0"||pjsj.equals("o")||pjsj.equals("null")){
		      pjsj="";
		    }   
		    
		    bzyf = bean1.getAccountmonth();
		    bzyf = bzyf != null ? bzyf : ""; 
		   if(bzyf.equals("")||bzyf.equals("null")){
		      bzyf="";
		    }  
		    
		    kpsj = bean1.getKptime();
		    kpsj = kpsj != null ? kpsj : "";
		    if(kpsj=="0"||kpsj.equals("0")||kpsj.equals("null")){
		      kpsj="";
		    }  
		    
		    dfbz = bean1.getMemoam();
		    dfbz = dfbz != null ? dfbz : ""; 
		    
		   
		    
		    paydatetime = bean1.getPaydatetime();
		    paydatetime = paydatetime != null ? paydatetime : "";
		    if(paydatetime=="0"||paydatetime.equals("0")||paydatetime.equals("null")){
		      paydatetime="";
		    }
			lastdegree = bean1.getLastdegree();
			lastdegree = lastdegree != null ? lastdegree : "";
			
		    thisdegree = bean1.getThisdegree();
		    thisdegree = thisdegree != null ? thisdegree : "";

		    dbid = bean1.getDbid();
		    dbid = dbid != null ? dbid : "";
		    
		   	dlid = bean1.getElectricfeeId();
		    dlid = dlid != null ? dlid : "";
		    
		    danjia = bean1.getUnitprice();//单价
		    danjia = danjia != null ? danjia : "0";
		    if("".equals(danjia)||"null".equals(danjia)||"o".equals(danjia)||" ".equals(danjia)||null==danjia){
		     danjia="0.0000";
		    }
		    dftiaozheng = bean1.getFloatpay();
		    dftiaozheng = dftiaozheng != null ? dftiaozheng : "";
		    
			dianfei = bean1.getActualpay();
			dianfei = dianfei != null ? dianfei : "";
			
		    pjbianhao = bean1.getNoteno();
		    pjbianhao = pjbianhao != null ? pjbianhao : "";
		    
		    caozuoyuan = bean1.getPayoperator();
		    caozuoyuan = caozuoyuan != null ? caozuoyuan : "";
		    //直流负荷
		    zlfh = bean1.getZlfh();
		    zlfh = zlfh != null ? zlfh : "0";
		    
		    startmonth = bean1.getStartmonth();
		    startmonth = startmonth != null ? startmonth : "";
		    if(startmonth=="0"||startmonth.equals("0")||startmonth.equals("null")){
		      startmonth="";
		    }
		    
		    endmonth = bean1.getEndmonth();
		    endmonth = endmonth != null ? endmonth : "";
		    if(endmonth=="0"||endmonth.equals("0")||endmonth.equals("null")){
		      endmonth="";
		    }
		    
		    //结算周期
		   	jszq = bean1.getJszq();
		    if("".equals(jszq)||"null".equals(jszq)||jszq==null) jszq="0";
		    //jszq = jszq != null ? jszq : "0";
		    DecimalFormat t = new DecimalFormat("0.0");
		     DecimalFormat e = new DecimalFormat("0.00");
		    autoauditstatus1 = bean1.getAutoauditstatus();
		    autoauditstatus1 = autoauditstatus1 != null ? autoauditstatus1 : "";
		    
		    manualauditstatus1 = bean1.getManualauditstatus();
		    manualauditstatus1 = manualauditstatus1 != null ? manualauditstatus1 : "";
		    if("0".equals(manualauditstatus1)){
		        manualauditstatus1="未通过";
		        cwzt="未通过";
		    }else if("1".equals(manualauditstatus1)){
		    manualauditstatus1="通过";
		    cwzt="未通过";
		    }else if("2".equals(manualauditstatus1)){
		    cwzt=manualauditstatus1;
		      manualauditstatus1="通过";
		    }else if("-1".equals(manualauditstatus1)){
		    cwzt=manualauditstatus1;
		    manualauditstatus1="未通过";
		    }else if("-2".equals(manualauditstatus1)){
		    	cwzt="不通过";
			    manualauditstatus1="不通过";
			}
		    manualauditdatetime = bean1.getManualauditdatetime();
		    manualauditdatetime = manualauditdatetime != null ? manualauditdatetime : "";
		    
		    manualauditname = bean1.getManualauditname();
		    manualauditname = manualauditname != null ? manualauditname : "";
		    if(manualauditname.equals("null")){
		    	manualauditname="";
		    }
		    
		    financialoperator = bean1.getFinancialoperator();
		    financialoperator = financialoperator != null ? financialoperator : "";
		    
		    financialdatetime = bean1.getFinancialdatetime();
		    financialdatetime = financialdatetime != null ? financialdatetime : "";
		    if(financialdatetime=="0"||financialdatetime.equals("0")||financialdatetime.equals("null")){
		      financialdatetime="";
		    }
		    
		    cityaudit = bean1.getCityaudit();
		    cityaudit = cityaudit != null ? cityaudit : "";
		    
		    pjleixing = bean1.getNotetypeid();
		    pjleixing = caozuoyuan != null ? pjleixing : "";
		    
		    liuchenghao = bean1.getLiuchenghao();
		    liuchenghao =liuchenghao!=null ? liuchenghao : "";
		    
		    gdfs = bean1.getGdfs();
		    gdfs =gdfs!=null ? gdfs : "";
		     //计算额定电费
    			     SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    			     String dfsl="0";
    			     double shu;
    			     double zlfhdl;
    			     long quot;
    			 if((lastdatatime!=null&&!lastdatatime.equals("")&&lastdatatime!="")&&(thisdatatime!=null&&!thisdatatime.equals("")&&thisdatatime!="")){
    				 String startn=lastdatatime.substring(0,4);
    			      String endn=thisdatatime.substring(0,4);
    			      String sgang=lastdatatime.substring(4,5);
    			      String egang=thisdatatime.substring(4,5);
    			      Pattern pattern = Pattern.compile("[0-9]*");//判断前四位是否是数字
    			      if( pattern.matcher(startn).matches()==true&&pattern.matcher(endn).matches()==true&&sgang.equals("-")&&egang.equals("-")){
    			    	  //System.out.println(lastdatatime.length()+"rrrr"+thisdatatime.length());
    			     	 if(lastdatatime.length()>=10&&thisdatatime.length()>=10){
    			     	   /* Date date1 = ft.parse( lastdatatime );
        	         		Date date2 = ft.parse( thisdatatime );
        	         		quot = date2.getTime() - date1.getTime();
        	         		quot = quot/1000/60/60/24+1;//计算天数*/
        	         		//System.out.println("Double.parseDouble(jszq):"+Double.parseDouble(jszq)+"Double.parseDouble(edhdl):"+Double.parseDouble(edhdl)+"Double.parseDouble(danjia):"+Double.parseDouble(danjia));
        	         		shu=Double.parseDouble(t.format(Double.parseDouble(edhdl)))*Double.parseDouble(danjia)*Double.parseDouble(jszq);//计算额定电费
        	         		DecimalFormat eddf =new DecimalFormat("0.00");
        	         		
                     		dfsl=eddf.format(shu);//格式化额定电费
                     		if(zlfh!=null&&!zlfh.equals("")&&zlfh!=""){
                     		zlfhdl=Double.parseDouble(zlfh)*54*Double.parseDouble(jszq);//直流负荷估算电量
                     		zlfhgs=eddf.format(zlfhdl);//格式化
                     		}else{
                     		zlfhgs="";
                     		}              		
                     	
		           		}
		      		}
		      		}
			 DecimalFormat la =new DecimalFormat("0.0");
			 if(lastdegree==null||lastdegree.equals("")||lastdegree.equals("null")||lastdegree.equals(" ")||lastdegree.equals("o"))lastdegree="0";
            lastdegree=la.format(Double.parseDouble(lastdegree));
			DecimalFormat th =new DecimalFormat("0.0");
			  if(thisdegree==null||thisdegree.equals("")||thisdegree.equals("null")||thisdegree.equals(" ")||thisdegree.equals("o"))thisdegree="0";
            thisdegree=th.format(Double.parseDouble(thisdegree));
		     DecimalFormat fl=new DecimalFormat("0.0");
		     if(ydltz==null||ydltz.equals("")||ydltz.equals("null")||ydltz.equals(" ")||ydltz.equals("o"))ydltz="0";
            ydltz = fl.format(Double.parseDouble(ydltz));
            
		    DecimalFormat ac=new DecimalFormat("0.0");
		    if(zshydl==null||zshydl.equals("")||zshydl.equals("null")||zshydl.equals(" ")||zshydl.equals("o"))zshydl="0";
            zshydl = ac.format(Double.parseDouble(zshydl));
            dfs=Double.parseDouble(zshydl);
             DecimalFormat dfi =new DecimalFormat("0.00");
             if(dianfei==null||dianfei.equals("")||dianfei.equals("null")||dianfei.equals(" ")||dianfei.equals("o"))dianfei="0";
            dianfei=dfi.format(Double.parseDouble(dianfei));
            dfzs=Double.parseDouble(dianfei);//
            DecimalFormat price=new DecimalFormat("0.0000");
            if(danjia==null||danjia.equals("")||danjia.equals("null")||danjia.equals(" ")||danjia.equals("o"))danjia="0";
            danjia = price.format(Double.parseDouble(danjia));
            DecimalFormat pay=new DecimalFormat("0.00");
            if(dftiaozheng==null||dftiaozheng.equals("")||dftiaozheng.equals("null")||dftiaozheng.equals(" ")||dftiaozheng.equals("o"))dftiaozheng="0";
            dftiaozheng = pay.format(Double.parseDouble(dftiaozheng));
		    //DecimalFormat a=new DecimalFormat("0.0");
		    //if(actualdegree==null||actualdegree.equals("")||actualdegree.equals("null")||actualdegree.equals("o"))actualdegree="0";
            //actualdegree = a.format(Double.parseDouble(actualdegree));
		   DecimalFormat mat =new DecimalFormat("0.00");
		   if(manualauditdatetime==null||"".equals(manualauditdatetime)||manualauditdatetime.equals("null")||manualauditdatetime.equals("0")){
		   manualauditdatetime="";
		   }
		    /*if(csds==null||csds.equals("")||csds.equals("null")||csds.equals(" ")||csds.equals("o"))csds="0";
			csds = t.format(Double.parseDouble(csds));*/
			if(null==dianfei||dianfei.equals("")||dianfei.equals("null")||dianfei.equals(" ")||dianfei.equals("o"))dianfei="0";
			double cha=Double.parseDouble(dfsl)-Double.parseDouble(dianfei);//额定电费与实际电费的差
			DecimalFormat c =new DecimalFormat("0.00");
			double chaa=0;
			if(dfsl.equals("0.00")){
			chaa=0.00;
			}else{
			 chaa=(Double.parseDouble(dianfei)-Double.parseDouble(dfsl))/Double.parseDouble(dfsl)*100;
			}
			if(edhdl==null||edhdl.equals("")||edhdl.equals("null")||edhdl.equals(" ")||edhdl.equals("o"))edhdl="0";
			edhdl=e.format(Double.parseDouble(edhdl));
		String chh=c.format(chaa);
			String ch = c.format(cha);
			
	        if("2".equals(cwzt)){
	        cwzt="通过";
	        }
	        if("-1".equals(cwzt)){
		        cwzt="不通过";
		        }
			if(intnum%2==0){ 
			    color="#FFFFFF" ;
			 }else{
			    color="#DDDDDD";
			}
			//System.out.println("---"+pjleixing+"---"+jtbblx+"------"+gdfs+"---"+dfzflx+"---"+dbyt);
			if(pjleixing.equals("pjlx05")){
				pjleixing="收据";	
			}else if(pjleixing.equals("pjlx03")){
				pjleixing="发票";
			}else{
				pjleixing="";
			}
			/* if(jtbblx.equals("zdlx12")){
				 jtbblx="通信机房";
				}else if(jtbblx.equals("zdlx01")){
					jtbblx="IDC机房";
				}else if(jtbblx.equals("zdlx07")){
					jtbblx="接入网";
				}else if(jtbblx.equals("zdlx08")){
					jtbblx="基站";
				}else if(jtbblx.equals("zdlx19")){
					jtbblx="其他";
				}else{
					jtbblx="";
				}*/
			if(gdfs.equals("gdfs01")){
				gdfs="供电局";
			}else if(gdfs.equals("gdfs02")){
				gdfs="业主";
			}else if(gdfs.equals("gdfs03")){
				gdfs="其他";
			}else if(gdfs.equals("gdfs04")){
				gdfs="直供电";
			}else if(gdfs.equals("gdfs05")){
				gdfs="转供电";
			}else{
				gdfs="";
			}
			/*if(dbyt.equals("dbyt01")){
				dbyt="结算";
			}else if(dbyt.equals("dbyt02")){
				dbyt="采集";
			}else if(dbyt.equals("dbyt03")){
				dbyt="管理";
			}else{
				dbyt="";
			}*/
			if(dfzflx.equals("dfzflx01")){
  		    	 dfzflx="月结";
  		     }else if(dfzflx.equals("dfzflx02")){
  		    	 dfzflx="合同";
  		     }else if(dfzflx.equals("dfzflx03")){
  		    	 dfzflx="预支";
  		     }else if(dfzflx.equals("dfzflx04")){
  		    	 dfzflx="插卡";
  		     }else{
  		      	 dfzflx="";
  		     }
			
		//	System.out.println(": "+dfzflx);
			
			
			
			
			
			
           intnum++;
           dfz=dfz+dfzs;
           ds=ds+dfs;
       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=countxh++%></div>
       		</td>  
       		<td>
       			<div align="left" ><%=szxian%></div>
       		</td>
       		<td>
       			<div align="left" ><%=szxq%></div>
       		</td>     		
       		<td>
       			<div align="left" title="详细信息"><a href="#" onclick="information('<%=dbid%>','<%=dlid%>')"><%=jzname%></a></div>
       		</td> 
       		 
       		 <td>
       			<div align="center"><%=dbmc%></a></div>
       		</td> 
       		 
       		  <td>
       			<div align="center" ><%=dfzflx%></div>
       		</td>   
       		 <td>
       			<div align="center" ><%=bzyf%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=kjyf%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=gdfs%></div>
       		</td>
        	<td>
       			<div align="right" ><%=lastdegree%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=thisdegree%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=lastdatatime%></div>
       		</td>
       		  	<td>
       			<div align="center" ><%=thisdatatime%></div>
       		</td>
       		<td>
       			<div align="right" ><%=zshydl%></div>
       		</td>   	
       		<td>
       			<div align="right" ><%=ydltz%></div>
       		</td> 
       		
       		<td>
       			<div align="center" ><%=dlbz%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=edhdl%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=startmonth%></div>
       		</td>
       		<td>
       			<div align="center" ><%=endmonth%></div>
       		</td>    
       		<td>
       			<div align="right" ><%=danjia%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=dftiaozheng%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=dfbz%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=dianfei%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jszq%></div>
       		</td>  
       		<td>
       			<div align="right" ><%=dfsl%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=ch%></div>
       		</td>
       		<td>
       			<div align="right" ><%=chh%>%</div>
       		</td>
       		<td>
       			<div align="center" ><%=zlfh%></div>
       		</td>  
       		<td>
       			<div align="right" ><%=zlfhgs%></div>
       		</td>
       		<td>
       			<div align="right" ><%=jlfh%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lrsj%></div>
       		</td>    
       		<td>
       			<div align="center" ><%=lrry%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=autoauditstatus1%></div>
       		</td>        		           		          
       		
       		 <td>
       			<div align="center" ><%=manualauditstatus1%></div>
       		</td>   
       		<td>
       			<div align="center" ><%=manualauditdatetime%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=manualauditname%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=cityaudit%></div>
       		</td>  
       		<td>
       			<div align="center" ><%=sjtime%></div>
       		</td>  
       		<td>
       			<div align="center" ><%=sjshy%></div>
       		</td>
       		<td>
       			<div align="center" ><%=cwzt%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=financialdatetime%></div>
       		</td>          
            <td>
       			<div align="center" ><%=financialoperator%></div>
       		</td>
       		    		
            <td>
       			<div align="right" ><%=bl%></div>
       		</td>
       		
       		
       		 
       		<td>
       			<div align="center" ><%=pjsj%></div>
       		</td> 
       		      		      		
       		<td>
       			<div align="center" ><%=kpsj%></div>
       		</td> 
       		          
            <td>
       			<div align="center" ><%=zdl%></div>
       		</td>
                 		      		 
       		
       		  
       		
       		 <td>
       			<div align="center" ><%=bgsign%></div>
       		</td> 

       		 <td>
       			<div align="left" ><%=bieming%></div>
       		</td>  
       		 
       		<td>
       		    <div align="center"><%=liuchenghao%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=pjbianhao%></div>
       		</td>  
       		 <td>
       			<div align="center" ><%=pjleixing%></div>
       		</td> 
       		 <td>
       			<div align="center" ><%=paydatetime%></div>
       		</td>  
       		 <td>
       			<div align="center" ><%=caozuoyuan%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzcode%></div>
       		</td>
       		<td>
       			<div align="left" ><%=dbid%></div>
       		</td>

       		<td>
       			<div align="left" ><%=yid%></div>
       		</td>

       		<td>
       			<div align="left" ><%=dbzbdyhh%></div>
       		</td>

    </tr>
       <%} %>
       <%} %>
       <%}%>
       <% if (intnum==0){
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
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			     
        </tr>
        <% }}%>
         <%
        DecimalFormat price1=new DecimalFormat("0.00");
        String s="";
        String fy="";
        s= price1.format(ds); 
        fy=price1.format(dfz);
        %>
        <tr>
        	<td align="center">合计</td>
        	<td colspan="2" align="center">电量合计：</td>
      		<td><%=s %><font size="2">度 </font></td>
      		<td colspan="2" align="center">电费合计：</td>
      		<td><%=fy %><font size="2">元 </font></td>
        </tr>
  	 		</table> 
  	 	</div>
 		<table width="60%"  border="0" cellspacing="0" cellpadding="0" align="right" height="5%">
  			<tr align="right">
                      <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>
			  <tr>
			   <td align="right">         
                                <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                          
                                <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
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
  					 <input type="hidden" name="autoauditstatus2" id="autoauditstatus2" value=""/>
  					 <input type="hidden" name="manauditstatus2" id="manauditstatus2" value=""/>
  					 <input type="hidden" name="citystatus2" id="citystatus2" value=""/>
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
      		document.form1.action=path+"/web/query/basequery/electricFeesQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/query/basequery/electricFeesQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/query/basequery/electricFeesQuery.jsp?curpage="+pageno+"<%=canshuStr%>";
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
		document.form1.zdlx.value=str2;
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
<script  type="text/javascript" >
function exportad(){
           var curpage = <%=curpage%>;
            var whereStr ="<%=str%>";
            var zdname="<%=zdname%>";
            var dbname="<%=dbname%>";
           var xiaoqu="<%=xiaoqu%>";
            //alert(canshuStr);
        	document.form1.action=path+"/web/query/basequery/电费信息.jsp?curpage="+curpage+"&whereStr="+whereStr+"&zdname="+zdname+"&dbname="+dbname+"&command=daochu"+"&xiaoqu="+xiaoqu;
            document.form1.submit();
   }
    function printad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=whereStr%>";
            //alert(canshuStr);
            window.open(path+"/web/query/basequery/electricfeesquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr,'','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
      
        	//document.form1.action=path+"/web/query/basequery/ammeterdegreesquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr;
            //document.form1.submit();
   }
   
   		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.zdlx.value='<%=zdlx%>';
		document.form1.zdlx1.value=document.form1.ccz.value;
		document.form1.autoauditstatus.value='<%=autoauditstatus%>';
		document.form1.manauditstatus.value='<%=manauditstatus%>';
		document.form1.citystatus.value='<%=citystatus%>';
		document.form1.gdfs.value='<%=gdfstj%>';
		document.form1.dbqyzt.value='<%=dbqyzt%>';
		document.form1.qyzt.value='<%=qyzt%>';
 </script> 
</html>
