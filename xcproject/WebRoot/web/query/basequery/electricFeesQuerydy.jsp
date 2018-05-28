<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,java.util.Calendar,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.ElectricFeesQueryBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.regex.Pattern"%>
<%
        String bztime         = request.getParameter("bztime")!= null ? request.getParameter("bztime"): "";
		String beginTime1     = request.getParameter("beginTime1") != null ? request.getParameter("beginTime1"): "";
	    String endTime1       = request.getParameter("endTime1") != null ? request.getParameter("endTime1") : "";
	    String entryTime1       = request.getParameter("entryTime1") != null ? request.getParameter("entryTime1") : "";
	    String beginTime      = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
        String endTime        = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
	    String title          = request.getParameter("title")!=null?request.getParameter("title"):"";
	    String operName       = request.getParameter("operName")!=null?request.getParameter("operName"):"";
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
        String beginTimeQ     = request.getParameter("beginTimeQ")!=null?request.getParameter("beginTimeQ"):"";
        String endTimeQ       = request.getParameter("endTimeQ")!=null?request.getParameter("endTimeQ"):"";
        String zdname         =request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
        String jzproperty     =request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
        String autoauditstatus = request.getParameter("autoauditstatus")!=null?request.getParameter("autoauditstatus"):"";
        String citystatus     = request.getParameter("citystatus")!=null?request.getParameter("citystatus"):"";
        String manauditstatus = request.getParameter("manauditstatus")!=null?request.getParameter("manauditstatus"):"";
        String zdlx			  =request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
        String zdlx1		  =request.getParameter("zdlx1")!=null?request.getParameter("zdlx1"):"";
        String htmlsql        = request.getParameter("htmlsql");
        String entrypensonnel = request.getParameter("entrypensonnel")!=null?request.getParameter("entrypensonnel"):"";//录入人员
        String dbyt1 = request.getParameter("dbyt")!=null?request.getParameter("dbyt"):"0";//电表用途
        String canshuStr      ="";
        String blhdl1=request.getParameter("blhdl1")!=null?request.getParameter("blhdl1"):"";
        String dzpnh1=request.getParameter("dzpnh1")!=null?request.getParameter("dzpnh1"):"";
        
        String zdqyzt = request.getParameter("zdqyzt")!=null?request.getParameter("zdqyzt"):"1";
            String blhdl2="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((beginTimeQ!=null)&&(endTimeQ!=null))||(zdlx!=null)||(autoauditstatus!=null)||(manauditstatus!=null)||(htmlsql!=null)||(entrypensonnel!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&beginTimeQ="+beginTimeQ+"&endTimeQ="+endTimeQ+
         "&autoauditstatus="+autoauditstatus+"&manauditstatus="+manauditstatus+"&htmlsql="+htmlsql+"&zdlx"+zdlx+"&entrypersonnel="+entrypensonnel;
     }
        String s_curpage      = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
        String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    System.out.println("dfdfd"+zdqyzt);
    
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
 .memo {border: 1px #C8E1FB solid}
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
#div1{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div1 p{ float:left;font-size:12px; width:110px; cursor:pointer;}
.bttcn{color:black;font-weight:bold;}

.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.form_la{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}
</style>
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
    
    function queryDegree(blhdl1){
                
             if(document.getElementById("blhdl1").value==""||document.getElementById("blhdl1").value==null)
		{
	                 alert("月耗电量不能为空 ");
	   
	   }else{
	   		var blhdl1=document.form1.blhdl1.value;
	   		var dzpnh1=document.form1.dzpnh1.value;
	   		    //var pattern = /(^[0-9]\d+$)|(^\d+\.?\d*$)/;
                // var re= /^[1-9]\d*\.?\d*$/;"?[0-9]*.?[0-9]*" "\\d+([.]\\d+)?"
                //!"NaN".eval(Number(blhd).toString())
                var re=/(^\d+([.]\d+$))|(^\d+$)/;
                
			if(re.exec(blhdl1)){
				if(re.exec(dzpnh1)||dzpnh1==null||dzpnh1==""){
			     document.form1.action=path+"/web/query/basequery/electricFeesQuerydy.jsp?command=chaxun";                       
                 document.form1.submit();
                  }else{
                	  alert("您输入的单载频能耗有误，请确认后重新输入！");
                  }
                }else{
                  alert("您输入的月耗电量有误，请确认后重新输入！");
                }
       }
    }
    //查询详细信息
    function information(zdid){  	
    	window.open(path+"/web/query/basequery/showzdmsg.jsp?zdid="+zdid,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
    }
    function informationd(zdid,dlid,dbid){  	
    	window.open(path+"/web/query/basequery/showdfmsg.jsp?zdid="+zdid+"&dlid="+dlid+"&dbid="+dbid,'','width=1300,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
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
	
	var blhdl1=document.form1.blhdl1.value;

		queryDegree(blhdl1);
		showdiv("请稍等..............");
	});
	$("#daochuBtn").click(function(){
		exportad();
	});
	$("#dayinBtn").click(function(){
		printad();
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
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电量电费查询</span>	
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
  	  <td>月耗电量大于</td>
                          <td><input type="text" id="blhdl1" name="blhdl1" class="selected_font" value="<%if(null!=request.getParameter("blhdl1")) out.print(request.getParameter("blhdl1")); %>">度<span class="style1">&nbsp;*</span></td>
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
					<div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table>
    <tr class="form_label">
    
    						 <td>站点名称：</td>
                            <td><input type="text" name="zdname" class="selected_font" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>"/>
                            </td>
                            
                             <td>站点属性：</td>
                           	 <td>
                           	 	
					         	<select name="jzproperty" class="selected_font" onchange="zdsx()">
					         		<option value="0">请选择</option>
						         		<%
							         	ArrayList zdsx = new ArrayList();
							         	zdsx = ztcommon.getSelctOptions("ZDSX");
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
				         	
				         <td>站点类型：</td>
				         <td>
				         	
					         	<select name="zdlx" class="selected_font" >
					         		<option value="0">请选择</option>
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
						      
                              <td> 报账月份：</td>
                              <td>
                              <input type="text" class="selected_font" name="bztime" value="<%if (null != request.getParameter("bztime"))out.print(request.getParameter("bztime"));%>"
		                         onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
                              </td> 
                               </tr>
                               <tr class="form_label">
                        	 <td>起始月份：</td>
		                     <td><input type="text" class="selected_font" name="beginTime1" value="<%if (null != request.getParameter("beginTime1"))out.print(request.getParameter("beginTime1"));%>"
		                         onFocus="getDatenyString(this,oCalendarChsny)" class="form" /></td> 
                               <td>结束月份：</td>
		                     <td><input type="text" name="endTime1" class="selected_font"  value="<%if (null != request.getParameter("endTime1")) out.print(request.getParameter("endTime1"));%>" onFocus="getDatenyString(this,oCalendarChsny)"/></td>
                             <td>单载频能耗大于：</td>
                            <td><input type="text" name="dzpnh1" class="selected_font" value="<%if(null!=request.getParameter("dzpnh1")) out.print(request.getParameter("dzpnh1")); %>"/>度
                            </td>
                            <td>电表用途：</td>
                            <td>
                            <select name="dbyt" class="selected_font" >
                                           <option value="0">请选择</option>
         		                           <option value="dbyt01">结算</option>
         		                           <option value="dbyt03">管理</option>
         		                           </select>
                            </td>
                           </tr>
                           <tr class="form_label">
                           		<td>站点启用状态：</td>
         						<td><select name="zdqyzt" class="selected_font">
         	 					<option value="1">是</option>
         	   					<option value="0">否</option>
         						<option value="-1">请选择</option>
         						</select></td>
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
  String whereStr = "",ts="";
  String str="";
  String sdr="";
  String ssd="";
  String dds="";
  String dfdf="";String s="";
   String xiaoquq="",shiq="",accountm="", property="",dzpnh="",zdnamee="",actualdegree="",money="",stmon="",enmon="",twoG="",thrG="",zlfh="",jlfh="",jzlx="",zpsl="",zssl="",dlid="",dbid="",zdid="";
   String lastdatatime="",thisdatatime="";long quot=0;int mon=0;double baa=0;
 double d=0,d1=0;
  ElectricFeesQueryBean bean = new ElectricFeesQueryBean();
		if(shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr=whereStr+" and zz.shi='"+shi+"'";
			str=str+" and zz.shi='"+shi+"'";
			dds=dds+" and zz.shi='"+shi+"'";
		}
		if(xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr=whereStr+" and zz.xian='"+xian+"'";
			str=str+" and zz.xian='"+xian+"'";
			dds=dds+" and zz.xian='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
			whereStr=whereStr+" and zz.xiaoqu='"+xiaoqu+"'";
			str=str+" and zz.xiaoqu='"+xiaoqu+"'";
			dds=dds+" and zz.xiaoqu='"+xiaoqu+"'";
		}
		if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
			whereStr=whereStr+" and zz.jzname like '%"+zdname+"%'";
			dds=dds+" and zz.jzname like '%"+zdname+"%'";
		}
		
		if(jzproperty != null && !jzproperty.equals("") && !jzproperty.equals("0")){
			whereStr=whereStr+" and ZZ.PROPERTY ='"+jzproperty+"' ";
			
		}
		
		if(bztime != null && bztime != "" && !bztime.equals("0")){
				whereStr=whereStr+" and TO_CHAR(ee.ACCOUNTMONTH,'yyyy-mm') ='"+bztime+"'";
				str=str+" and TO_CHAR(ee.ACCOUNTMONTH,'yyyy-mm') ='"+bztime+"'";
		}
		if(dzpnh1 != null && dzpnh1 != ""){
		
		//  (case when length(aa.endmonth)=7 and length(aa.startmonth)=7 and zz.zpsl>0 then aa.blhdl/((MONTHS_BETWEEN(TO_DATE(aa.endmonth, 'YYYY-MM'),TO_DATE(aa.startmonth, 'YYYY-MM')) + 1)*30)/zz.zpsl end  )
		//whereStr
			whereStr=whereStr+" and  ( case when length(to_char(aa.thisdatetime,'yyyy-mm-dd'))=10 and length(to_char(aa.lastdatetime,'yyyy-mm-dd'))=10 and zz.zpsl>0 then aa.blhdl/to_char(ceil(aa.Thisdatetime -aa.LASTDATETIME)+1)/zz.zpsl end )>'"+dzpnh1+"'";
			str=str+" and  ( case when length(to_char(aa.thisdatetime,'yyyy-mm-dd'))=10 and length(to_char(aa.lastdatetime,'yyyy-mm-dd'))=10 and zz.zpsl>0 then aa.blhdl/to_char(ceil(aa.Thisdatetime - aa.LASTDATETIME)+1)/zz.zpsl end )>'"+dzpnh1+"'";
	}
		if(beginTime1 != null && beginTime1 != "" && !beginTime1.equals("0")){
				whereStr=whereStr+" and TO_CHAR(aa.STARTMONTH,'yyyy-mm') >='"+beginTime1+"'";
				str=str+" and TO_CHAR(aa.STARTMONTH,'yyyy-mm')>='"+beginTime1+"'";
				ssd=ssd+" and TO_CHAR(aa.STARTMONTH,'yyyy-mm') >='"+beginTime1+"'";
			}
			if(endTime1 != null && endTime1 != "" && !endTime1.equals("0")){
				whereStr=whereStr+" and TO_CHAR(aa.ENDMONTH,'yyyy-mm') <='"+endTime1+"'";
				str=str+" and TO_CHAR(aa.ENDMONTH,'yyyy-mm') <='"+endTime1+"'";
				ssd=ssd+" and TO_CHAR(aa.ENDMONTH,'yyyy-mm') <='"+endTime1+"'";
				
			}
		if(zdlx!=null&&!zdlx.equals("")&& !zdlx.equals("0")){
			  whereStr=whereStr+" and ZZ.STATIONTYPE IN('"+zdlx+"')";
			  str=str+" and ZZ.STATIONTYPE IN('"+zdlx+"')";
			  dds=dds+" and ZZ.STATIONTYPE IN('"+zdlx+"')";
		}
		if(dbyt1!=null&&!dbyt1.equals("")&& !dbyt1.equals("0")){
			  whereStr=whereStr+" and dd.dbyt='"+dbyt1+"'";
			  str=str+" and dd.dbyt='"+dbyt1+"'";
			dds=dds+" and dd.dbyt='"+dbyt1+"'";
		}
		if(zdqyzt!=null&&!zdqyzt.equals("")&& !zdqyzt.equals("-1")){
			  whereStr=whereStr+" and zz.qyzt='"+zdqyzt+"'";
			  str=str+" and zz.qyzt='"+zdqyzt+"'";
			dds=dds+" and zz.qyzt='"+zdqyzt+"'";
		}
  %>
	

		<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 	
	 			  <table width="1500px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
         	<tr height = "23" class="relativeTag ">
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">地区</div></td>
            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
            <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际用电量</div></td>
             <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">金额</div></td>
             
  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始月份</div></td>  			
  			<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束月份</div></td> 
  			<td width="7%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交费周期(天数)</div></td>
  			 <td width="5%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">单载频能耗</div></td>
  			  <td width="4%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">载频数量</div></td>
            <td width="5%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">有无2G设备</div></td>
            <td width="5%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">有无3G设备</div></td>
            <td width="4%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">直流负荷</div></td>
            
            <td width="4%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交流负荷</div></td>            
  			<td width="4%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>  
           
            <td width="5%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">载扇数量</div></td>
           
            <td width="5%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
            <td width="5%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>
  			<td width="5%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>
        </tr>
       <%
       	 ArrayList fylist = new ArrayList();
       	 if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;
       	 	shi="1";
       	 }
       	 if(blhdl1 != null && !blhdl1.equals("")){
       		 
  blhdl2=blhdl1;
  
  if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
 // blhdl2="";
 //  dfdf="";
  dfdf="";
  }else{
  //double n=Double.parseDouble(blhdl2);

  }
       	 fylist = bean.getPageDatady(curpage,whereStr,loginId,blhdl2,dfdf);
       	 allpage=bean.getAllPage();
        
		//电流id
		int countxh=1;
		double act=0.0;
		double mm=0.00;
		 if(fylist!=null)
		{		
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
            //=================
          xiaoquq=(String)fylist.get(k+fylist.indexOf("XIAOQU"));
          xiaoquq=xiaoquq!=null?xiaoquq:"";
          if("null".equals(xiaoquq)){
          xiaoquq="";
          }
            zdnamee=(String)fylist.get(k+fylist.indexOf("JZNAME"));
          zdnamee=zdnamee!=null?zdnamee:"";
          if("null".equals(zdnamee)){
          zdnamee="";
          }
          
          property=(String)fylist.get(k+fylist.indexOf("PROPERTY"));
          property=property!=null?property:"";
          if("null".equals(property)){
        	  property="";
          }
          
          
        
          //==耗电量
          actualdegree=(String)fylist.get(k+fylist.indexOf("BLHDL"));
          actualdegree=actualdegree!=null?actualdegree:"0";
          if("null".equals(actualdegree)||"".equals(actualdegree)){
          actualdegree="0";
          }
          
         
          
		    money = (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));
		    money = money != null ? money : "0";
		    if("".equals(money)||"null".equals(money)){
		    money="0";
		    }
		  
		  
		  
		  
		    stmon=(String)fylist.get(k+fylist.indexOf("STARTMONTH"));
			stmon=stmon!=null?stmon:"";
		    enmon = (String)fylist.get(k+fylist.indexOf("ENDMONTH"));
		    enmon = enmon != null ? enmon : "";
		    twoG=(String)fylist.get(k+fylist.indexOf("G2"));
		    if(twoG.equals("1")){
		     twoG="有";
		    }else{
		      twoG="无";
		    }
		   
		    thrG=(String)fylist.get(k+fylist.indexOf("G3"));
		   if(thrG.equals("1")){
		     thrG="有";
		    }else{
		      thrG="无";
		    }
		   // System.out.println(twoG+"2G设备"+thrG+"3G设备");
		    //新加
		     zlfh = (String)fylist.get(k+fylist.indexOf("ZLFH"));
		    zlfh = zlfh != null ? zlfh : ""; 
		    jlfh = (String)fylist.get(k+fylist.indexOf("JLFH"));
		    jlfh = jlfh != null ? jlfh : "";
		  
		   jzlx = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
		    jzlx = jzlx != null ? jzlx : "";
		  
		    zpsl = (String)fylist.get(k+fylist.indexOf("ZPSL"));
		    zpsl = zpsl != null ? zpsl : "0";
		    if("".equals(zpsl)||"null".equals(zpsl)){
		     zpsl="0";
		    }
		     zssl = (String)fylist.get(k+fylist.indexOf("ZSSL"));
		    zssl = zssl != null ? zssl : "0";
		    if("".equals(zssl)||"null".equals(zssl)){
		     zssl="0";
		    }
		     shiq =(String)fylist.get(k+fylist.indexOf("SHI"));
          shiq=shiq!=null?shiq:"";
          if("null".equals(shiq)){
          shiq="";
          }
		    dbid = (String)fylist.get(k+fylist.indexOf("DBID"));
		    dbid = dbid != null ? dbid : "";
		    dlid=(String)fylist.get(k+fylist.indexOf("AMMETERDEGREEID"));
		    
		    zdid = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
		    zdid = zdid != null ? zdid : "";
		    
		 //   dzpnh = (String)fylist.get(k+fylist.indexOf("DZPNH"));
		    dzpnh = dzpnh != null ? dzpnh : "0";
		    if("".equals(dzpnh)||"null".equals(dzpnh)){
		       dzpnh="0";
		    }
		     // ts = (String)fylist.get(k+fylist.indexOf("TS"));
		    ts = ts != null ? ts : "0";
		    if("".equals(ts)||"null".equals(ts)||null==ts){
		       ts="0";
		    }
		    
		    
		    
		      accountm=(String)fylist.get(k+fylist.indexOf("ACCOUNTMONTH"));
          accountm=accountm!=null?accountm:"";
          if("null".equals(accountm)){
          accountm="";
          }
		    
		    DecimalFormat mat1=new DecimalFormat("0.00");
		  actualdegree=mat1.format(Double.parseDouble(actualdegree));
		  DecimalFormat mat2=new DecimalFormat("0.00");
		  money=mat2.format(Double.parseDouble(money));
		  dzpnh=mat2.format(Double.parseDouble(dzpnh));
		  
		  if("".equals(zlfh)||null==zlfh||"null".equals(zlfh)){
		   zlfh="0";
		  }
		  if("".equals(jlfh)||null==jlfh||"null".equals(jlfh)){
		  jlfh="0";
		  }
		  zlfh=mat2.format(Double.parseDouble(zlfh));
		  jlfh=mat2.format(Double.parseDouble(jlfh));
		   if("".equals(zssl)||null==zssl){
		   zssl="0";
		  }
		  lastdatatime=(String)fylist.get(k+fylist.indexOf("LASTDATETIME"));
		   thisdatatime=(String)fylist.get(k+fylist.indexOf("THISDATETIME"));
		  	 SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    			     String dfsl="0";
    			     double shu;
    			     double shu1;
    			     double sjydl;
    			     double dd,ff;
    			     double dbdl1;
    			     String dlbl="0";
    			     
			     if((lastdatatime!=null&&!"0".equals(lastdatatime)&&!"null".equals(lastdatatime)&&!lastdatatime.equals("")&&lastdatatime!="")&&(thisdatatime!=null&&!"0".equals(thisdatatime)&&!"null".equals(thisdatatime)&&!thisdatatime.equals("")&&thisdatatime!="")){
    			     String startn=lastdatatime.substring(0,4);
    			      String endn=thisdatatime.substring(0,4);
    			      String sgang=lastdatatime.substring(4,5);
    			      String egang=thisdatatime.substring(4,5);
    			      
    			     Pattern pattern = Pattern.compile("[0-9]*");//判断前四位是否是数字
    			   if( pattern.matcher(startn).matches()==true&&pattern.matcher(endn).matches()==true&&sgang.equals("-")&&egang.equals("-")){
    			     	if(lastdatatime.length()>=8&&thisdatatime.length()>=8){
    			     	Date date1 = ft.parse( lastdatatime );
        	         	Date date2 = ft.parse( thisdatatime );
        	         	quot = date2.getTime() - date1.getTime();
        	         	
        	         	quot = quot/1000/60/60/24+1;//计算天数
        	         	d1=Double.parseDouble(quot+"");
        	         	if(Integer.parseInt(zpsl)>0){
        	         	d= Double.parseDouble(actualdegree);//电量
        	         	s= (d/d1/Integer.parseInt(zpsl)+"").trim();
        	         	s=mat1.format(Double.parseDouble(s));
        	         	
        	         	}
        	     
        	         	
                    
		           		}
		      		}
		      		}
		      		SimpleDateFormat ftm = new SimpleDateFormat("yyyy-MM");
		      		  if((stmon!=null&&!"0".equals(stmon)&&!"null".equals(stmon)&&!stmon.equals("")&&stmon!="")&&(enmon!=null&&!"0".equals(enmon)&&!"null".equals(enmon)&&!enmon.equals("")&&enmon!="")){
	                       String sty=stmon.substring(0,4);//截取年
	                       String ety=enmon.substring(0,4);//截取年 结束月份
	                        String styh=stmon.substring(4,5);//截取-
	                        String etyh=enmon.substring(4,5);//截取-
	                         Pattern pattern = Pattern.compile("[0-9]*");//判断前四位是否是数字
	                     if( pattern.matcher(sty).matches()==true&&pattern.matcher(ety).matches()==true&&styh.equals("-")&&etyh.equals("-")){     
	                        if(stmon.length()>=6&&enmon.length()>=6){
	                         	
	                               Date dateone = ftm.parse(stmon); //起始月份
	                               Date datetwo = ftm.parse(enmon); //结束月份
	                                   Calendar objCalendarDate1 = Calendar.getInstance();   	
	                                   Calendar objCalendarDate2 = Calendar.getInstance();  
	                                   objCalendarDate1.setTime(dateone);
	                                   objCalendarDate2.setTime(datetwo);  
	                                 if (objCalendarDate2.equals(objCalendarDate1)){
	                                    	mon=1;
	                                   }		
	                 if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR)){ 
	                          mon = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))*12 + objCalendarDate2.get(Calendar.MONTH)- objCalendarDate1.get(Calendar.MONTH))+1; 
                        }else{
	                          mon = (objCalendarDate2.get(Calendar.MONTH)- objCalendarDate1.get(Calendar.MONTH))+1;                    	
		                }  
                  }     	
                  }	
		      		}
		      		
			if(countxh%2==0){
			    color="#FFFFFF" ;
			 }else{
			    color="#DDDDDD";
			}
           intnum++;
           if(mon>0){
           baa=(Double.parseDouble(actualdegree))/mon;
           }
           
           
         
       %>
       <% if(baa>Double.parseDouble(blhdl1)){%>
       <tr bgcolor="<%=color%>">
       
       		<td>
       			<div align="center" ><%=countxh++%></div>
       		</td>     
       		<td>
       			<div align="left" ><%=shiq+xiaoquq%></div>
       		</td>   		
       		<td>
       			<div align="left" title="站点详细信息"><a href="#" onclick="information('<%=zdid%>')"><%=zdnamee%></a></div>
       		</td>
       		<td>
       			<div align="left" ><%=property%></div>
       		</td> 
       		 <td>
       			<div align="right" title="电费详细信息" ><a href="#" onclick="informationd('<%=zdid%>','<%=dlid%>','<%=dbid%>')"><%=actualdegree%></a></div>
       		</td>     
       		<td>
       			<div align="right" ><%=money%></div>
       		</td> 

 <td>
       			<div align="center" ><%=stmon%></div>
       		</td>     
       		<td>
       			<div align="center" ><%=enmon%></div>
       		</td>  
       		<td>
       			<div align="right" ><%=quot%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=s%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=zpsl%></div>
       		</td> 
       		 <td>
       			<div align="center" ><%=twoG%></div>
       		</td>     
       		<td>
       			<div align="center" ><%=thrG%></div>
       		</td>  
       		 <td>
       			<div align="right" ><%=zlfh%></div>
       		</td>     
       		<td>
       			<div align="right" ><%=jlfh%></div>
       		</td>   	
       		
       		<td>
       			<div align="center" ><%=jzlx%></div>
       		</td>  
       		     
       		<td>
       			<div align="right" ><%=zssl%></div>
       		</td>  
       		
       		<td>
       			<div align="center" ><%=accountm%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=dbid%></div>
       		</td>     
       		<td>
       			<div align="center" ><%=zdid%></div>
       		</td> 
    </tr>
       <%} %>
       <%} %>
       <%}%>
       <% if (countxh==0){
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
          
            </tr>
      <% }}else if(!(countxh > 16)){
    	  for(int i=((countxh-1)%16);i<16;i++){
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
                     
        </tr>
        <% }}}%>
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
var path = '<%=path%>';

//站点属性
function zdsx(){
	
	var sid = document.form1.jzproperty.value;
    
	if(sid=="0"){
		var shilist = document.all.zdlx;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=zdsx&pid="+sid,"jzproperty");
	}
}

function sendRequest(url, para) {

	createXMLHttpRequest();

	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数
	XMLHttpReq.send(null);
}

function processResponse_zdlx() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          	updateZdlx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}

//站点类型
function updateZdlx(res){

	var shilist = document.all.zdlx;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
	

</script>


<script  type="text/javascript" >
function exportad(){
           var curpage = <%=curpage%>;
            var blhdl1 ="<%=blhdl1%>";
              var whereStr ="<%=str%>";
            var zdname="<%=zdname%>";
            var endTime1="<%=endTime1%>";
            var zdlx="<%=zdlx%>";
            var shi="<%=shi%>";
            var xian="<%=xian%>";
            var xiaoqu="<%=xiaoqu%>";
           var dbyt='<%=dbyt1%>';
            //alert(canshuStr);
        	document.form1.action=path+"/web/query/basequery/电费电量信息.jsp?curpage="+curpage+"&whereStr="+whereStr+"&zdname="+zdname+"&blhdl1="+blhdl1+"&endTime1"+endTime1+"&zdlx="+zdlx+"&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&dbyt="+dbyt+"&command=daochu";
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
	    document.form1.dbyt.value='<%=dbyt1%>';	
		if('<%=zdlx%>'==""){
		   '<%=zdlx%>'=="0";
		}
		document.form1.zdlx.value='<%=zdlx%>';
		
		document.form1.jzproperty.value='<%=jzproperty%>';
		document.form1.zdlx1.value=document.form1.ccz.value;
		//document.form1.manauditstatus.value='<=manauditstatus%>';
		document.form1.zdqyzt.value='<%=zdqyzt%>';
 </script> 
</html>
