<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>

<%
	String bztime         = request.getParameter("bztime")!= null ? request.getParameter("bztime"): "";
	String lrrq         = request.getParameter("lrrq")!= null ? request.getParameter("lrrq"): "";
	String beginTime1 = request.getParameter("beginTime1") != null ? request
			.getParameter("beginTime1")
			: "";
	String endTime1 = request.getParameter("endTime1") != null ? request
			.getParameter("endTime1") : "";
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginId1 = request.getParameter("loginId");
    String loginName = account.getAccountName();    
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
	
	//添加站点类型、站点属性、集团报表类型
    String stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
    String jzproperty1=request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
	String jztype1 = request.getParameter("jztype")!=null?request.getParameter("jztype"):"0";
	
	String beginTimeQ = request.getParameter("BeginTime")!=null?request.getParameter("BeginTime"):"";
    String endTimeQ = request.getParameter("EndTime")!=null?request.getParameter("EndTime"):"";
    String jizhan = request.getParameter("zhandian")!=null?request.getParameter("zhandian"):"0";
    
     String autoauditfees = request.getParameter("autoauditstatus");
     String autoauditfees1 = request.getParameter("autoauditfees1");
    
  	String canshuStr="";
  	int intnum=0;
  	String color=null;
	if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((beginTimeQ!=null)&&(endTime!=null))||(title!=null)||(operName!=null)||(stationtype1!=null)||(jzproperty1!=null)||(jztype1!=null)||(jizhan!=null)){
	canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&beginTimeQ="+beginTimeQ+"&endTimeQ="+endTimeQ+"&title="+title+"&operName="+operName+"&stationtype1="+stationtype1+"&jzproperty1="+jzproperty1+"&jztype1="+jztype1+"&jizhan="+jizhan;
	}
        
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

</title>
<style>
            .btn {
             BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7b9ebd 1px solid
            }
            .btn1_mouseout {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#B3D997); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn1_mouseover {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#CAE4B6); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn2 {padding: 2 4 0
            4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;}
            .btn3_mouseout {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mouseover {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mousedown
            {
             BORDER-RIGHT: #FFE400 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #FFE400 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #FFE400
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #FFE400 1px solid
            }
            .btn3_mouseup {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn_2k3 {
             BORDER-RIGHT: #002D96 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #002D96 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); BORDER-LEFT: #002D96
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #002D96 1px solid
            }
  .style1 {
	color: red;
	font-weight: bold;
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
.bttcn{color:BLACK;font-weight:bold;}
.form    {width:130px}

#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
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
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
 </style>
 
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
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
    function modifyad(electricfeeId,feebz){
            //alert(feebz);
                  if(feebz=='0'){
                    document.form1.action=path+"/web/check/modifyElectricFees.jsp?degreeid="+electricfeeId;
                    document.form1.submit();
                  }else{
                    document.form1.action=path+"/web/check/modifyElectricFeesDegree.jsp?degreeid="+electricfeeId;
                    document.form1.submit();
                  }
       
    }
    
    function queryDegree(){
                  /* var sheng = document.form1.sheng2.value;
                   var shi = document.form1.shi2.value;
                   var xian = document.form1.xian2.value;
                   var xiaoqu = document.form1.xiaoqu2.value;
                   var jizhan = document.form1.zhandian.value;
                   var beginTime = document.form1.BeginTime.value;
                   var endTime = document.form1.EndTime.value;
                   var autoauditstatus = document.form1.autoauditstatus2.value;
                   var canshuStr = "?sheng="+sheng+"&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&jizhan="+jizhan+"&beginTime="+beginTime+"&endTime="+endTime+"&autoauditstatus="+autoauditstatus;
                   */
                   //alert(canshuStr);
                     if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null)
		          {
	                   alert("城市不能为空");
	   
	           }else{
                   document.form1.action=path+"/web/check/checkFeesAuto.jsp?command=chaxun";
                   document.form1.submit();
       }
    }
    $(function(){
		$("#chaxun").click(function(){
			queryDegree();
		});
		$("#daochu").click(function(){
			exportad();
		});
	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0802");
%>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr>
			<td colspan="4">
			<div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">自动电费审核</span>	
			  </div>
		    </td>
			</tr>		
			<tr><td height="30" colspan="4">
	   				<div id="parent" style="display:inline">
	                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
	                </div>
		        </td>
		    </tr>
		    <tr><td height="8%" width="1200">
		    	<table>
			    	<tr class="form_label">
			    		<td>城市：</td><td><select name="shi" class="selected_font" onchange="changeCity()">
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
		                    <option value="<%=agcode%>" selected="selected"><%=agname%></option>
		                    <%}
			         	}
			         %>
	         		</select><span class="style1">&nbsp;*</span></td>
					
					
					<td>区县:</td><td> <select name="xian" class="selected_font" onchange="changeCountry()">
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
					
					
					<td>乡镇：</td><td><select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
	         		<td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p></td>
	         		<td><%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
	               <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;right:-220px;float:right;top:0;">
			       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
			       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 			       
					</div>
			       <%}%></td></tr> 
		    </table>		    
		    </td></tr>		
		</table>
		
		<div style="width:88%;" > 
		<p id="box3" style="display:none">
			<table>
			<tr class="form_label">
				<td>站点名称：</td><td><input type="text" name="zhandian" value="<%if(null!=request.getParameter("zhandian")) out.print(request.getParameter("zhandian")); %>" class="selected_font"/></td>
	        	<td>审核状态：</td><td><select name="autoauditstatus" class="selected_font" onchange="javascript:document.form1.autoauditstatus2.value=document.form1.autoauditstatus.value">
         		<option value="-1">请选择</option>
         		<option value="0">未通过</option>
         		<option value="1">通过</option>
         		</select></td>
         		<td>起始月份:</td><td><input type="text" class="selected_font" name="beginTime1" value="<%if (null != request.getParameter("beginTime1"))out.print(request.getParameter("beginTime1"));%>" onFocus="getDatenyString(this,oCalendarChsny)" /></td>
	         	<td>结束月份:</td><td><input type="text" name="endTime1" class="selected_font" value="<%if (null != request.getParameter("endTime1"))out.print(request.getParameter("endTime1"));%>" onFocus="getDatenyString(this,oCalendarChsny)" /></td>
         	</tr>
	        <tr class="form_label">
	         	<td>交费起始时间:</td><td><input type="text" name="BeginTime" value="<%if(null!=request.getParameter("BeginTime")) out.print(request.getParameter("BeginTime")); %>" onFocus="getDateString(this,oCalendarChs)"  class="selected_font"/></td>
	         	<td>交费结束时间：</td><td><input type="text" name="EndTime" value="<%if(null!=request.getParameter("EndTime")) out.print(request.getParameter("EndTime")); %>" onFocus="getDateString(this,oCalendarChs)"  class="selected_font"/></td>

	         	<td>站点类型:</td><td><select name="stationtype" class="selected_font" onchange="kzinfo()">
         		<option value="0">请选择</option>
         		<%
		         	ArrayList stationtype = new ArrayList();
	         		stationtype = commBean.getSelctOptions("StationType");
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
		         <td>站点属性：</td><td><select name="jzproperty" class="selected_font" onchange="kzinfo()">
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList zdsx = new ArrayList();
			         	zdsx = commBean.getSelctOptionszd("zdsx");
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
	         	</tr>
	         	<tr class="form_label">
	         	<td>集团报表类型：</td><td><select name="jztype" class="selected_font" onchange="kzinfo()">
		        		<option value="0">请选择</option>          		
		         		<%
			         	ArrayList zdtype = new ArrayList();
			         	zdtype = commBean.getSelctOptionsjt("zdlx");
			         	if(zdtype!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)zdtype.get(0)).intValue();
			         		for(int i=cscount;i<zdtype.size()-1;i+=cscount)
		                    {
		                    	code=(String)zdtype.get(i+zdtype.indexOf("CODE"));
		                    	name=(String)zdtype.get(i+zdtype.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
		         	</select></td>
  <td> 报账月份：</td>
                              <td>
                              <input type="text" class="selected_font" name="bztime" value="<%if (null != request.getParameter("bztime"))out.print(request.getParameter("bztime"));%>"
		                         onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
                              </td> 
                              <td>录入日期:</td><td><input type="text" name="lrrq" value="<%if(null!=request.getParameter("lrrq")) out.print(request.getParameter("lrrq")); %>" onFocus="getDateString(this,oCalendarChs)"  class="selected_font"/></td>
	         </tr>
			</table>
		</p>
	</div>
		
		<table>
			<tr><td height="23" colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
             </div></td></tr>
		</table>
		
		<%
			ElectricFeesBean bean = new ElectricFeesBean();
			 if(autoauditfees1!=null){
         		autoauditfees=autoauditfees1;
	         }
	         String whereStr = "";
	         String str = "";
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and zd.shi='"+shi+"'";
				str=str+" and zd.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and zd.xian='"+xian+"'";
				str=str+" and zd.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" and zd.xiaoqu='"+xiaoqu+"'";
				str=str+" and zd.xiaoqu='"+xiaoqu+"'";
			}
			if(jizhan != null && !jizhan.equals("") && !jizhan.equals("0")){
				whereStr=whereStr+" and zd.jzname like '%"+jizhan+"%'";
			}
			
			if(lrrq != null && !lrrq.equals("") && !lrrq.equals("null")){
				whereStr=whereStr+" and to_char(ef.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
			}
			
			if(beginTimeQ != null && beginTimeQ != "" && !beginTimeQ.equals("0")){
				whereStr=whereStr+" and to_char(ef.paydatetime,'yyyy-mm-dd')>='"+beginTimeQ+"'";
				str=str+" and to_char(ef.paydatetime,'yyyy-mm-dd')>='"+beginTimeQ+"'";
			}
			if(endTimeQ != null && endTimeQ != "" && !endTimeQ.equals("0")){
				whereStr=whereStr+" and to_char(ef.paydatetime,'yyyy-mm-dd')<='"+endTimeQ+"'";
				str=str+" and to_char(ef.paydatetime,'yyyy-mm-dd')<='"+endTimeQ+"'";
			}
			if(bztime != null && bztime != "" && !bztime.equals("0")){
				whereStr=whereStr+" and to_char(ef.ACCOUNTMONTH,'yyyy-mm') ='"+bztime+"'";
				str=str+" and to_char(ef.ACCOUNTMONTH,'yyyy-mm') ='"+bztime+"'";
		}
		   if(autoauditfees != null && autoauditfees != "" && !autoauditfees.equals("0")){
				whereStr=whereStr+" and ef.autoauditstatus='"+autoauditfees+"'";
				str=str+" and ef.autoauditstatus='"+autoauditfees+"'";
			}
			if(beginTime1 != null && beginTime1 != "" && !beginTime1.equals("0")){
				whereStr=whereStr+" and to_char(ad.STARTMONTH,'yyyy-mm') ='"+beginTime1+"'";
				str=str+" and to_char(ad.STARTMONTH,'yyyy-mm') ='"+beginTime1+"'";
			}
			if(endTime1 != null && endTime1 != "" && !endTime1.equals("0")){
				whereStr=whereStr+" and to_char(ad.ENDMONTH,'yyyy-mm') ='"+endTime1+"'";
				str=str+" and to_char(ad.ENDMONTH,'yyyy-mm') ='"+endTime1+"'";
			}
			//站点类型、站点属性、集团报表类型
			if(stationtype1 != null && !stationtype1.equals("") && !stationtype1.equals("0")){
				whereStr=whereStr+" and zd.STATIONTYPE='"+stationtype1+"'";
				str=str+" and zd.STATIONTYPE='"+stationtype1+"'";
			}
			if(jzproperty1 != null && !jzproperty1.equals("") && !jzproperty1.equals("0")){
				whereStr=whereStr+" and zd.PROPERTY='"+jzproperty1+"'";
				str=str+" and zd.PROPERTY='"+jzproperty1+"'";
			}
			if(jztype1 != null && !jztype1.equals("") && !jztype1.equals("0")){
				whereStr=whereStr+" and zd.JZTYPE='"+jztype1+"'";
				str=str+" and zd.JZTYPE='"+jztype1+"'";
			}		
			String str1="";
			String str2="";
			if(loginId1!=null&&!loginId1.equals("")){
	       	    loginId=loginId1;
	       	 	shi="1";
	       	 }
	       	 
	     //更改2012-12-5
        String count1="0";
		String count2="0.00";
       	if("chaxun".equals(request.getParameter("command"))||"daochu".equals(request.getParameter("command"))){
       		 ElectricFeesBean beana=new ElectricFeesBean();
		 List<ElectricFeesFormBean> list=beana.getCountt(whereStr,loginId);
		   for(ElectricFeesFormBean ls:list){
		   count1=ls.getCount();
		     count2=ls.getActualpay();
		     
		    }
           // count2=bean.getCountGreezddf(whereStr,loginId,str2);
        
	    
		if(count2==null||count2==""||count2.equals("")||count2.equals("null")){
			   count2="0.00";
		}else{
		    DecimalFormat countdl=new DecimalFormat("0.00");
		    count2=countdl.format(Double.parseDouble(count2));
		}
	 %>
	 	<table  height="5%">
          <tr>
  	        <td><font size="2">总共</font></td><td><font size="2" color="red"><%=count1%></font><font size="2">条  |</font></td>
  	         <td><font size="2">实际电费金额总和</font></td><td><font size="2" color="red"><%=count2%></font><font size="2">元  |</font></td>
  	      </tr>
		</table>
		<%} %> 
		<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
			<table width="1400px" height="80%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
		        <tr height = "23" class="relativeTag">
		        	  <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>		        	  
                      <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次单价</div></td>
          			  <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电费用调整</div></td>
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际电费金额</div></td>
                       <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td>                      
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电流类型</div></td>
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电设备</div></td>
                                            
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据类型</div></td>
                      <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据编号</div></td>
                      <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交费操作员</div></td>
                      <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交费时间</div></td>
                      <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电量流水号</div></td>
                      
<%--                      <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">修改</div></td>--%>
		        </tr>
	<%
       	  List<ElectricFeesFormBean> list=null;
       	 //更改2012-12-5
       	 if("chaxun".equals(request.getParameter("command"))||"daochu".equals(request.getParameter("command"))){
       	   list=bean.getPageDataCheck2(whereStr,loginId);
          //  fylist = bean.getPageDataCheck2();
        } else{
           list=null;
        }
        
       	 //fylist = bean.getPageDataCheck2(curpage,whereStr,loginId);
       	 //allpage=bean.getAllPage();
		String electricfeeId = "",
		unitprice = "",floatpay = "", actualpay = "",
		notetypeid = "",noteno = "",payoperator = "",bzyf="",
		paydatetime = "",ammeterdegreeid = "",autoauditstatus = "";
		String jzname = "",dllx = "",ydsb = "",jzcode = "",autoauditdescription = "",dlshenhe="",dlshenhezhuangtai="",manualauditstatus = "",feebz="",stationtype2="",jzproperty2 ="",jztype2 ="";

		Double tz=0.00,df=0.00;
		int countxh=1;
	if(list!=null){
		for(ElectricFeesFormBean listt:list){		   

		     //num为序号，不同页中序号是连续的
		    jzname = listt.getJzname();
		    jzname = jzname != null ? jzname : "";	
		    
		    bzyf = listt.getAccountmonth();
		    bzyf = bzyf != null ? bzyf : "";	
		     //(String)fylist.get(k+fylist.indexOf("ELECTRICCURRENTTYPE_AMMETER"));
		    
		    dllx = listt.getDllx();
		    dllx = dllx != null ? dllx : "";
		    ydsb = listt.getYdsb();
		    ydsb = ydsb != null ? ydsb : "";
		    

		    autoauditstatus = listt.getAutoauditstatus();
		    autoauditstatus = autoauditstatus != null ? autoauditstatus : "";
		    dlshenhezhuangtai = listt.getDlshenhezhuangtai();
		    dlshenhezhuangtai = dlshenhezhuangtai != null ? dlshenhezhuangtai : "";
		    ammeterdegreeid = listt.getAmmeterdegreeid();
		    ammeterdegreeid = ammeterdegreeid != null ? ammeterdegreeid : "";
		    electricfeeId = listt.getElectricfeeId();	
		    electricfeeId = electricfeeId != null ? electricfeeId : "";			    
			unitprice = listt.getUnitprice();
			unitprice = unitprice != null ? unitprice : "";
		    floatpay = listt.getFloatpay();
		    floatpay = floatpay != null ? floatpay : "";
		    actualpay = listt.getActualpay();
		    actualpay = actualpay != null ? actualpay : "";
		    notetypeid = listt.getNotetypeid();
		    notetypeid = notetypeid != null ? notetypeid : "";
			noteno = listt.getNoteno();	
			noteno = noteno != null ? noteno : "";		
			payoperator = listt.getPayoperator();
			payoperator = payoperator != null ? payoperator : "";
		    paydatetime = listt.getPaydatetime();
		    paydatetime = paydatetime != null ? paydatetime : "";
		    if(paydatetime=="0"||paydatetime.equals("0")){
				paydatetime="";
			}else{
				paydatetime = paydatetime != null ? paydatetime : "";
			}
            autoauditdescription = listt.getAutoauditdescription();
		    autoauditdescription = autoauditdescription != null ? autoauditdescription : "";
		    dlshenhe =listt.getDlshenhe();
		    dlshenhe = dlshenhe != null ? dlshenhe : "";
            manualauditstatus =listt.getManualauditstatus();
		    manualauditstatus = manualauditstatus != null ? manualauditstatus : "";
		    autoauditdescription=autoauditdescription+dlshenhe;
		    //添加站点类型、站点属性、集团报表类型
			stationtype2 = listt.getStationtype();
			stationtype2 = stationtype2 != null ? stationtype2 : "";
			jzproperty2 = listt.getProperty();
			jzproperty2 = jzproperty2!= null ? jzproperty2 : "";
			jztype2 = listt.getJztype();
			jztype2 = jztype2 != null ? jztype2 : "";   
		   
		    //feebz = (String)fylist.get(k+fylist.indexOf("FEEBZ"));
		    //feebz = feebz != null ? feebz : "";
		    DecimalFormat b= new DecimalFormat("0.0000");
            if(unitprice==null||unitprice.equals("")||unitprice.equals("null")) unitprice="0";
            unitprice = b.format(Double.parseDouble(unitprice));
            
		    DecimalFormat mat=new DecimalFormat("0.00");		    
		    if(floatpay==null||floatpay.equals("")||floatpay.equals("null")) floatpay="0";
		    if(actualpay==null||actualpay.equals("")||actualpay.equals("null")) actualpay="0";
		    tz=Double.parseDouble(floatpay);
		    df=Double.parseDouble(actualpay);
		    floatpay=mat.format(tz);
		    actualpay=mat.format(df);


			if(intnum%2==0){
			    color="#FFFFFF";
			 }else{
			    color="#DDDDDD";
			}
           intnum++;

       %>
       <%if(manualauditstatus!=null&&manualauditstatus.equals("1")){ %>
       
       <%}else{ %>
       <%if(autoauditstatus!=null&&autoauditstatus.equals("1")&&dlshenhezhuangtai!=null&&dlshenhezhuangtai.equals("1")){ %>
         <tr bgcolor="<%=color%>" title="<%=autoauditdescription%>">
         	<td>
       			<div align="center" ><%=countxh++%></div>
       		</td>       		
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="right" ><%=unitprice%></div>
       		</td>
       		<td>
       			<div align="right" ><%=floatpay%></div>
       		</td>          
            <td>
       			<div align="right" ><%=actualpay%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=bzyf%></div>
       		</td>
       		<td>
       			<div align="center" ><%=stationtype2%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jzproperty2%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jztype2%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dllx%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=ydsb%></div>
       		</td>
            <td>
       			<div align="center" ><%=notetypeid%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=noteno%></div>
       		</td>
       		<td>
       			<div align="center" ><%=payoperator%></div>
       		</td>
       		<td>
       			<div align="center" ><%=paydatetime%></div>
       		</td>
       		<td>
       			<div align="center" ><%=ammeterdegreeid%></div>
       		</td>
       		<!-- <td>
       			<div align="center" >审核通过</div>
       	    </td>   	 -->	
         </tr>
         <%} else { %>
         <tr bgcolor="red" title="<%=autoauditdescription%>">
            <td>
       			<div align="center" ><%=countxh++%></div>
       		</td>         		
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="right" ><%=unitprice%></div>
       		</td>
       		<td>
       			<div align="right" ><%=floatpay%></div>
       		</td>          
            <td>
       			<div align="right" ><%=actualpay%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=bzyf%></div>
       		</td>
       		<td>
       			<div align="center" ><%=stationtype2%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jzproperty2%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jztype2%></div>
       		</td>
       		  	
       		<td>
       			<div align="center" ><%=dllx%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=ydsb%></div>
       		</td>
            <td>
       			<div align="center" ><%=notetypeid%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=noteno%></div>
       		</td>
       		<td>
       			<div align="center" ><%=payoperator%></div>
       		</td>
       		<td>
       			<div align="center" ><%=paydatetime%></div>
       		</td>
			<td>
       			<div align="center" ><%=ammeterdegreeid%></div>
       		</td> 
         </tr>
         <%} %>
         <%} %>
          
       <%}}%>
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
            
                      
        </tr>
        <% }}%>
			</table>
		</div>
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">  
		  <tr><td>
		  <input type="hidden" name="sheng2" id="sheng2" value=""/>
		  <input type="hidden" name="shi2" id="shi2" value=""/>
		  <input type="hidden" name="xian2" id="xian2" value=""/>
		  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
		  <input type="hidden" name="autoauditstatus2" id="autoauditstatus2" value=""/>
		  </td></tr>
		   	<tr bgcolor="F9F9F9">
			  <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
				  <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
			  </div></td>
			</tr>
			   <tr>
				 <td >					   
					   <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                        
					   <div id="daochu" style="position:relative;width:60px;height:23px;cursor: pointer;right:9px;float:right;">
							<img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
							<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">导出</span>
						</div>
					   <%}%>			  
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
      		document.form1.action=path+"/web/check/checkFeesAuto.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/check/checkFeesAuto.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/check/checkFeesAuto.jsp?curpage="+pageno;
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
function exportad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=str%>";
            var jizhan ="<%=jizhan%>";
            var lrrq='<%=lrrq%>';
            //alert(canshuStr);
        	document.form1.action=path+"/web/check/自动电费审核.jsp?curpage="+curpage+"&whereStr="+whereStr+"&jizhan="+jizhan+"&lrrq="+lrrq+"&command=daochu";
            document.form1.submit();
   }
   		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.autoauditstatus.value='<%=autoauditfees%>';
		document.form1.stationtype.value='<%=stationtype1%>';
		document.form1.jzproperty.value='<%=jzproperty1%>';
		document.form1.jztype.value='<%=jztype1%>';
 </script>
</html>
