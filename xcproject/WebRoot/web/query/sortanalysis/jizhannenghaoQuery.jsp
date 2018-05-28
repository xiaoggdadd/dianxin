<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.ElectricFeesQueryBean,com.noki.mobi.common.CommonBean,com.noki.mobi.common.zdbzbean,com.noki.mobi.common.AccountJzq" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.awt.datatransfer.*"%>
<%@ page import="java.util.*"%>

<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime,com.noki.ammeterdegree.javabean.AmmeterDegreeBean,com.noki.mobi.common.CommonBean,com.noki.mobi.common.zdbzbean,com.noki.mobi.common.AccountJzq"%>
<%@ page import="java.sql.ResultSet,java.util.List"%>
<%@ page import="java.text.*"%>
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
		String nian = request.getParameter("nian") != null ? request.getParameter("nian") : "0";
		String xian           = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
		String xiaoqu         = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
        String beginTimeQ     = request.getParameter("beginTimeQ")!=null?request.getParameter("beginTimeQ"):"";
        String endTimeQ       = request.getParameter("endTimeQ")!=null?request.getParameter("endTimeQ"):"";
        String zdname         =request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
        String autoauditstatus = request.getParameter("autoauditstatus")!=null?request.getParameter("autoauditstatus"):"";
        String citystatus     = request.getParameter("citystatus")!=null?request.getParameter("citystatus"):"";
        String manauditstatus = request.getParameter("manauditstatus")!=null?request.getParameter("manauditstatus"):"";
        String zdlx			  =request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"请选择";
        String zdlx1		  =request.getParameter("zdlx1")!=null?request.getParameter("zdlx1"):"";
        String htmlsql        = request.getParameter("htmlsql");
        String entrypensonnel = request.getParameter("entrypensonnel")!=null?request.getParameter("entrypensonnel"):"";//录入人员
        String canshuStr      ="";
        String blhdl1=request.getParameter("blhdl1")!=null?request.getParameter("blhdl1"):"";
        String dzpnh1=request.getParameter("dzpnh1")!=null?request.getParameter("dzpnh1"):"";
            String blhdl2="";
        String s_curpage      = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
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
 			document.form1.action=path+"/web/sys/logManage.jsp?beginTime="+beginTime+"&command=chaxun";
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
 function queryDegree(){
          var nian = document.form1.nian.value
                   document.form1.action=path+"/web/query/sortanalysis/jizhannenghaoQuery.jsp?command=chaxun";
                   document.form1.submit();
                   showdiv("请稍等..............");
                  
       
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
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>

<%
permissionRights=commBean.getPermissionRight(roleId,"0504");

%>
<% Calendar cal = Calendar.getInstance(); 
	int year = cal.get(Calendar.YEAR);
	String s = year+"";
%>
<body  class="body" style="overflow-x:hidden;" onload="showTwo()">
	<form action="" name="form1" method="POST">
	
    <table  width="100%"  border="0" cellspacing="0" cellpadding="0" height="20%">
	 <tr>
       <td colspan="4" width="50">
												 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">基站能耗标准查询</span>	
												</div>
											</td>
    </tr>
    <tr>
   
    	<td height="20" colspan="4"><div id="parent" style="display:inline">
        <div style="width:50px;display:inline;"><hr></div><font size="2">过滤条件</font><div style="width:300px;display:inline;"><hr></div>
        </div></td>
      
    </tr>
  	<tr >
  	<td width="100%">
  	<table>
  	<tr class="form_label">
  
		    		<td>选择年份：</td>
		    		
		    		<td><select name="nian" class="selected_font" onChange="changeCity()">
	         		<option value="0"> <%= s %></option>
				<option value="2000">2000</option>
				<option value="2001">2001</option>
				<option value="2002">2002</option>
				<option value="2003">2003</option>
				<option value="2004">2004</option>
				<option value="2005">2005</option>
				<option value="2006">2006</option>
				<option value="2007">2007</option>
				<option value="2008">2008</option>
				<option value="2009">2009</option>
				<option value="2010">2010</option>
				<option value="2011">2011</option>
				<option value="2012">2012</option>
				<option value="2013">2013</option>
				<option value="2014">2014</option>
				<option value="2015">2015</option>
				<option value="2016">2016</option>
				<option value="2017">2017</option>
				<option value="2018">2018</option>
				<option value="2019">2019</option>
				<option value="2020">2020</option>
				
	         		</select></td>
	         		     <td>&nbsp;</td>          
                      <td>&nbsp;</td>     
                                          <td>&nbsp;</td>         
                                       <td>&nbsp;</td>
   			<td  class="form_label">	
							<p>
			  </p>			  </td>
	         		<td >
							       <div id="chaxun" style="position:relative;width:59px;height:23px;right:-250px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
			</td>
					
		  </tr>
					</table>
					</td>
					</tr>
   			 <tr >
	 		   <td colspan="8">&nbsp;</td>
    </tr>
          </table>
          	<table  height=23>
<tr><td height="20"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>
          
          
              
	<%
				AccountJzq bean = new AccountJzq();
				String whereStr = "";
				//====ad.entrytime
				if (nian != null && !nian.equals("") && !nian.equals("0")) {
					whereStr = whereStr + " where year='" + nian + "'";
				}

				//站点类型、站点属性、集团报表类型
				//更改2012-12-5
				//String count1=bean.getCountt(whereStr,loginId,str1);
				//String count2=bean.getCountGree(whereStr,loginId,str2);
			%>
	

		<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 	
	 			  <table width="2200px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
				  <tr class="relativeTag " >
				  <Td colspan="6" align="center" bgcolor="#DDDDDD"><div align="center" class="bttcn">基站分类</div></Td>
				  <Td colspan="12" align="center" bgcolor="#DDDDDD"><div align="center" class="bttcn">能耗标准（度/天）</div></Td>
				  </tr>
         	<tr height = "23" class="relativeTag ">
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">2G</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">3G</div></td>
            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">厂家</div></td>
             <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">载频</div></td>
             
  			<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">载扇</div></td>  			
  			<td width="6%" height="23" bgcolor="#DDDDDD">
  				 
			<table border="0"   class="form_label" width="100%">
			<tr><td bgcolor="#DDDDDD" align="center"  colspan="2"><div align="center" class="bttcn">1月</div></td> </tr>
			<tr><td  bgcolor="#DDDDDD" align="center" ><div  align="center" class="bttcn">非共享</div></td>
			<td bgcolor="#DDDDDD" align="center" ><div  align="center" class="bttcn">共享</div></td>
			</tr>
			</table>
			</td> 
  			<td width="6%"  height="23" bgcolor="#DDDDDD">
  			
  				 <div align="center" class="bttcn">
			<table border="0"   class="form_label" width="100%">
			<tr><td bgcolor="#DDDDDD" align="center"  colspan="2"><div class="bttcn">2月</div></td> </tr>
			<tr><td  bgcolor="#DDDDDD" align="center" ><div class="bttcn">非共享</div></td>
			<td bgcolor="#DDDDDD" align="center" ><div class="bttcn">共享</div></td>
			</tr>
			</table>
			</div>
  			
  			
  			</td>
  			 <td width="6%"  height="23" bgcolor="#DDDDDD">
  			 <div align="center" class="bttcn">
			<table border="0"    class="form_label" width="100%">
			<tr><td bgcolor="#DDDDDD" align="center"  colspan="2"><div class="bttcn">3月</div></td> </tr>
			<tr><td  bgcolor="#DDDDDD" align="center" ><div class="bttcn">非共享</div></td>
			<td bgcolor="#DDDDDD" align="center" ><div class="bttcn">共享</div></td>
			</tr>
			</table>
			</div>
	</td>
  			  <td width="6%"  height="23" bgcolor="#DDDDDD">
  			
  			 <div align="center"    class="bttcn">
			<table border="0"    class="form_label" width="100%">
			<tr><td bgcolor="#DDDDDD" align="center"  colspan="2"><div class="bttcn">4月</div></td> </tr>
			<tr><td  bgcolor="#DDDDDD" align="center" ><div class="bttcn">非共享</div></td>
			<td bgcolor="#DDDDDD" align="center" ><div class="bttcn">共享</div></td>
			</tr>
			</table>
			</div>
  			  
  			  
  			  </td>
            <td width="6%"  height="23" bgcolor="#DDDDDD">  <div align="center" class="bttcn">
			<table border="0"    class="form_label" width="100%">
			<tr><td bgcolor="#DDDDDD" align="center"  colspan="2"><div class="bttcn">5月</div></td> </tr>
			<tr><td  bgcolor="#DDDDDD" align="center" ><div class="bttcn">非共享</div></td>
			<td bgcolor="#DDDDDD" align="center" ><div class="bttcn">共享</div></td>
			</tr>
			</table>
			</div>
  			  
            
            </td>
           <td width="6%"  height="23" bgcolor="#DDDDDD">  <div align="center" class="bttcn">
			<table border="0"   class="form_label" width="100%">
			<tr><td bgcolor="#DDDDDD" align="center"  colspan="2"><div class="bttcn">6月</div></td> </tr>
			<tr><td  bgcolor="#DDDDDD" align="center" ><div class="bttcn">非共享</div></td>
			<td bgcolor="#DDDDDD" align="center" ><div class="bttcn">共享</div></td>
			</tr>
			</table>
			</div></td>
            <td width="6%"  height="23" bgcolor="#DDDDDD"> <div align="center" class="bttcn">
			<table border="0"  class="form_label" width="100%">
			<tr><td bgcolor="#DDDDDD" align="center"  colspan="2"><div class="bttcn">7月</div></td> </tr>
			<tr><td  bgcolor="#DDDDDD" align="center" ><div class="bttcn">非共享</div></td>
			<td bgcolor="#DDDDDD" align="center" ><div class="bttcn">共享</div></td>
			</tr>
			</table>
			</div></td>
            
            <td width="6%"  height="23" bgcolor="#DDDDDD"> <div align="center" class="bttcn">
			<table border="0"   class="form_label" width="100%">
			<tr><td bgcolor="#DDDDDD" align="center"  colspan="2"><div class="bttcn">8月</div></td> </tr>
			<tr><td  bgcolor="#DDDDDD" align="center" ><div class="bttcn">非共享</div></td>
			<td bgcolor="#DDDDDD" align="center" ><div class="bttcn">共享</div></td>
			</tr>
			</table>
			</div></td>            
  			<td width="6%"  height="23" bgcolor="#DDDDDD"> <div align="center" class="bttcn">
			<table border="0"   class="form_label" width="100%">
			<tr><td bgcolor="#DDDDDD" align="center"  colspan="2"><div class="bttcn">9月</div></td> </tr>
			<tr><td  bgcolor="#DDDDDD" align="center" ><div class="bttcn">非共享</div></td>
			<td bgcolor="#DDDDDD" align="center" ><div class="bttcn">共享</div></td>
			</tr>
			</table>
			</div></td>  
           
            <td width="6%"  height="23" bgcolor="#DDDDDD"> <div align="center" class="bttcn">
			<table border="0"    class="form_label" width="100%">
			<tr><td bgcolor="#DDDDDD" align="center"  colspan="2"><div class="bttcn">10月</div></td> </tr>
			<tr><td  bgcolor="#DDDDDD" align="center" ><div class="bttcn">非共享</div></td>
			<td bgcolor="#DDDDDD" align="center" ><div class="bttcn">共享</div></td>
			</tr>
			</table>
			</div></td>
           
            <td width="6%"  height="23" bgcolor="#DDDDDD"> <div align="center" class="bttcn">
			<table border="0"   class="form_label" width="100%">
			<tr><td bgcolor="#DDDDDD" align="center"  colspan="2"><div class="bttcn">11月</div></td> </tr>
			<tr><td  bgcolor="#DDDDDD" align="center" ><div class="bttcn">非共享</div></td>
			<td bgcolor="#DDDDDD" align="center" ><div class="bttcn">共享</div></td>
			</tr>
			</table>
			</div></td>
            <td width="6%"  height="23" bgcolor="#DDDDDD"> <div align="center" class="bttcn">
			<table border="0"    class="form_label" width="100%">
			<tr><td bgcolor="#DDDDDD" align="center"  colspan="2"><div class="bttcn">12月</div></td> </tr>
			<tr><td  bgcolor="#DDDDDD" align="center" ><div class="bttcn">非共享</div></td>
			<td bgcolor="#DDDDDD" align="center" ><div class="bttcn">共享</div></td>
			</tr>
			</table>
			</div></td>
        </tr>
      <%
						List<zdbzbean> fylist = null;
						    String command= request.getParameter("command")!=null?request.getParameter("command"):"";
						if(command.equals("chaxun")){
							fylist = bean.getPageDatap(whereStr);

							String zdl = "", gsf1 = "", isbg = "", zl = "", edhd = "", kt = "", lrry = "", lrsj = "";
							String cj = "", j2 = "", j3 = "", jt = "", sb = "", y1 = "", y2 = "", y3 = "", y4 = "", y5 = "", y6 = "", y7 = "", y8 = "", y9 = "", y10 = "", y11 = "", y12 = "";
							int zp = 0, zs = 0;
							String y111="",y122="",y13="",y14="",y15="",y16="",y17="",y18="",y19="",y20="",y21="",y22="";
							String rgshzt = "", rgshsj = "", rgshry = "";
							int countxh = 1;
							if (fylist != null) {
								for (zdbzbean bean1 : fylist) {
									cj = bean1.getCHANGJIA();
									if (cj == null) {
										cj = "";
									}
									zp = bean1.getZP();
									if (cj == null) {
										cj = "";
									}
									zs = bean1.getZS();
									
									j2 = bean1.getG2();
									j3 = bean1.getG3();
									if (j2.equals("1")) {
										j2 = "是";
									} else {
										j2 = "否";
									}
									if (j3.equals("1")) {
										j3 = "是";
									} else {
										j3 = "否";
									}
									jt = bean1.getJZTYPE();
									if(jt==null)
									{
									   jt = "";
									}
									sb = bean1.getSHEBEI();
									if(sb==null)
									{
									   sb = "";
									}
										//页面上显示共享标准的时候就是=非共享标准+23*套数
								      // 	y3+23*"套数"
									y1 = bean1.getJANUARY();
									if (y1 == null) {
										y1 = "";
										y111="";
									}
									
									y2 = bean1.getFEBRUARY();
									if (y2 == null) {
										y2 = "";
									}
									y3 = bean1.getMARCH();
									if (y3 == null) {
										y3 = "";
										y13="";
									}else{
									y13=y3+"+"+23+"*套数";
									}
									//页面上显示共享标准的时候就是=非共享标准+23*套数
								// 	y3+23*"套数"
									y4 = bean1.getAPRIL();
									if (y4 == null) {
										y4 = "";
										y14="";
									}else{
									y14=y4+"+"+23+"*套数";
									}
									y5 = bean1.getMAY();
									if (y5 == null) {
										y5 = "";
									}
									y6 = bean1.getJUNE();
									if (y6 == null) {
										y6 = "";
									}
									y7 = bean1.getJULY();
									if (y7 == null) {
										y7 = "";
									}
									y8 = bean1.getAUGUST();
									if (y8 == null) {
										y8 = "";
									}
									y9 = bean1.getSEPTEMBER();
									if (y9 == null) {
										y9 = "";
									}
									y10 = bean1.getOCTOBER();
									if (y10 == null) {
										y10 = "";
									}
									y11 = bean1.getNOVEMBER();
									if (y11 == null) {
										y11 = "";
									}
									y12 = bean1.getDECEMBER();
									if (y12 == null) {
										y12 = "";
									}

									if (intnum % 2 == 0) {
										color = "#FFFFFF";

									} else {
										color = "#DDDDDD";
									}
									intnum++;
					%>
      <tr bgcolor="<%=color%>" align="center">
						<td>
							<div align="center"><%=countxh++%></div>				</td>
						<td>
							<div align="center"><%=j2%></div>						</td>
						<td>
							<div align="center"><%=j3%></div>						</td>
						<td>
							<div align="center"><%=cj%></div>						</td>


						<td>
							<div align="center"><%=zp%></div>						</td>
						<td>
							<div align="center"><%=zs%></div>						</td>
						
						<td>
						<table >
						<tr>
						<td><div align="center"><%=y1%></div></td>
						<td><div align="center"><%=y1%></div></td>
						</tr>
						</table>
													</td>
						<td>
							<div align="center"><%=y2%></div>						</td>
						<td>
							<table  class="form_label">
						<tr >
						<td width="4%" height="23"><div align="center"><%=y3%></div></td>
						<td width="4%" height="23"><div align="center"></div></td>
						</tr>
						</table>					</td>
						<td>
							<table  class="form_label">
						<tr >
						<td width="4%" height="23"><div align="center"><%=y4%></div></td>
						<td width="4%" height="23"><div align="center"><%=y14%></div></td>
						</tr>
						</table>						</td>
						<td>
							<div align="center"><%=y5%></div>						</td>

						<td>
							<div align="center"><%=y6%></div>						</td>

						<td>
							<div align="center"><%=y7%></div>						</td>

						<td>
							<div align="center"><%=y8%></div>						</td>

						<td>
							<div align="center"><%=y9%></div>						</td>

						<td>
							<div align="center"><%=y10%></div>						</td>

						<td>
							<div align="center"><%=y11%></div>						</td>

						<td>
							<div align="center"><%=y12%></div>						</td>
					</tr>
       <%} %>
       <%} %>
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
            </tr>
      <% }}}else if(!(intnum > 16)){
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
        </tr>
        <% }}%>
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
                               
                               <%} %> 
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
        document.form1.nian.value = '<%=nian%>';
 </script> 
</html>
