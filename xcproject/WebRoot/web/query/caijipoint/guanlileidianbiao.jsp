
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.noki.query.caijipoint.javabean.GuanLiDB_Hdl" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Calendar" %>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String roleId = account.getRoleId();
	
%>
<html>
<head>
<title>

</title>
<style type="text/css">
   .form {
    background-color: #F7FBFF;
    border-color: #3179CE;
    border-right: 1px solid #3179CE;
    border-style: solid;
    border-width: 1px;
    color: #014F8A;
    font-size: 9pt;
}
}

</style>

<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
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
	
 function showIdList(){
 
        window.open('guanlishowAmmeterIdList.jsp','','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
 }
 
$(function(){
	$("#liulan11").click(function(){
				showIdList();
			});
	$("#query").click(function(){
			chaxun();
		});
});
</script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
<form action="" name="form1" method="POST">
			<div style="float: left">
				<img src="<%=path%>/images/btt.bmp" />
				<span
					style="color: black; font-weight: bold; font-size: 16; position: absolute; left: 40px; top: 22px;">
					管理类电表日耗电趋势 </span>
			</div>
			</br>
<table width="100%">
	<tr>
      <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		 <tr>
		  <td colspan="3">
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
   						<tr>
							<td width="4"></td>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" >
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">
												<div id="parent" style="display:inline">
                          </div><div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>
                      <%
                      	String ammeterid = request.getParameter("ammeterid");
                      	AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
                      	AmmeterDegreeFormBean beanad = new AmmeterDegreeFormBean();
                      	bean = bean.getAmmeterDegreeAllInfoa(ammeterid);//2014-11-13加a
                      	beanad = beanad.getLastAmmeterDegree(ammeterid);
                      	String payzq = bean.getPrepayZq(ammeterid) != null ? bean.getPrepayZq(ammeterid) : "";
                      	String area =( bean.getProvinceid() != null ? bean.getProvinceid(): "") + " " + (bean.getCityid() != null ? bean.getCityid(): "" )+ " " + (bean.getCountryid() != null ? bean.getCountryid() : "");
                      	String lastad = beanad.getLastdegree() != null ? beanad.getLastdegree() : "";
                      	String lastadtime = beanad.getLastdatetime() != null ? beanad.getLastdatetime() : "";
                      	String lastadtime1 = "";
                      	//日期转换
                      	if (!lastadtime.equals("")) {
                      		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                      		String str = lastadtime;
                      		Date dt = sdf.parse(str);
                      		Calendar rightNow = Calendar.getInstance();
                      		rightNow.setTime(dt);
                      		//rightNow.add(Calendar.YEAR,-1);//日期减1年
                      		//rightNow.add(Calendar.MONTH,3);//日期加3个月
                      		rightNow.add(Calendar.DAY_OF_YEAR, 1);//日期加1天
                      		Date dt1 = rightNow.getTime();
                      		lastadtime1 = sdf.format(dt1);
                      	}
                      
                      %>
                      <tr>
         <td width="100%">
         	起始日期：<input type="text" readonly name="beginTime" value="<%if (null != request.getParameter("beginTime"))
				     out.print(request.getParameter("beginTime"));%>" onFocus="getDateString(this,oCalendarChs)" style="width:130px"/>
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	结束日期：<input type="text" readonly name="endTime" value="<%if (null != request.getParameter("endTime"))
				     out.print(request.getParameter("endTime"));%>" onFocus="getDateString(this,oCalendarChs)" style="width:130px"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                    
                                电表ID：<input type="text" name="ammeteridFk" value="<%=bean.getAmmeterid() != null ? bean.getAmmeterid() : ""%>" readonly="true" style="whidth:100" style="width:150; font-weight:bold;" /><span class="style1">&nbsp;*</span>
                  <img id="liulan11" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png " style="cursor: pointer" />
                  <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:66%;TOP:-23PX">
				  <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
				  <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
				  </div>
         </td>
 
      </tr>
       <tr bgcolor="F9F9F9">
          <td height="19" colspan="4"><div id="parent" style="display:inline">
              <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
          </div></td>
        </tr>
      
     
    </table>
  
   </td>
   </tr>
          <tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">            
       <tr>
                 <td height="19" bgcolor="#DDDDDD" width="15%">站点名称：</td>
         <td width="20%"><input id="stationname" type="text" size="25" name="stationname" value="" style="whidth:130" style="width:150; font-weight:bold;" />

         </td>
           <td height="19" bgcolor="#DDDDDD" width="15%">所在地区：</td>
         <td><input id="area" type="text" size="25" name="area" value=""  style="whidth:130" style="width:150; font-weight:bold;" />
         </td>
           <td height="19" bgcolor="#DDDDDD" width="15%" >集团报表类型 ：</td>
         <td width="20%"><input id="stationtypeid" type="text" size="25" name="Stationtypeid" value="" style="whidth:130" style="width:150; font-weight:bold;" maxlength="11"/>
         </td>
         
         </tr>
      <tr>
     
         
      <td height="19" bgcolor="#DDDDDD">是否标杆：</td>
         <td><input id="sfbg" type="text" size="25" name="sfbg" value=""  style="whidth:130" style="width:150; font-weight:bold;" />
         </td>
          <td height="19" bgcolor="#DDDDDD">备注：</td>
         <td><input id="memo" type="text" size="25" name="Memo" value=""  style="whidth:130" style="width:150; font-weight:bold;" />
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
  <div id="tableDiv" style="position:relative;width:450px;height:280px;left:4px;top:10px;z-index: 1;float:left;">
  	<iframe name="test" frameborder="0" width="100%" height="100%" ></iframe>
  </div>
 
  <div id="chartDiv" style="position:relative;width:*;height:280px;left:5px;top:10px;z-index: 1;">
  	<iframe name="chart" frameborder="0" width="100%" height="100%" scrolling="no"></iframe>
  </div>
    
</form>
<script type="text/javascript">
function chaxun(){
	var beginTime = document.form1.beginTime.value;
	var endTime = document.form1.endTime.value;
	var dbid = document.form1.ammeteridFk.value;
	
	
	var href = "guanlileibiaoge.jsp?beginTime="+beginTime+"&endTime="+endTime+"&dbid="+dbid;
	var str = "<a id='aa' href='"+href+"' target='test'></a>";
	$("body").append(str);
	$("#aa")[0].click();
	
	var href = "guanlileichart.jsp?beginTime="+beginTime+"&endTime="+endTime+"&dbid="+dbid;
	var str = "<a id='bb' href='"+href+"' target='chart'></a>";
	$("body").append(str);
	$("#bb")[0].click();
	
	 $("#stationname")[0].value = "<%=bean.getStationname() != null ? bean.getStationname(): ""%>";
		 $("#area")[0].value = "<%=area != null ? area : ""%>";
		 $("#stationtypeid")[0].value = "<%=bean.getStationtypeid() != null ? bean.getStationtypeid(): ""%>";
		 $("#sfbg")[0].value = "<%=bean.getIsbenchmarkstation() != null ? bean.getIsbenchmarkstation(): ""%>";
		 $("#memo")[0].value = "<%=bean.getMemo() != null ? bean.getMemo() : ""%>";
		 
}
		
</script>
 
</body>
</html>

