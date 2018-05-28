<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String shengId = (String)session.getAttribute("accountSheng");
String loginName = (String)session.getAttribute("loginName");
String startmonth=request.getParameter("startmonth");
String endmonth=request.getParameter("endmonth");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="StyleSheet" href="<%=path%>/css/atatonic/zp-type.css" type="text/css" />
	
	<script src="<%=path%>/javascript/tx.js"></script>
	<script src="<%=path%>/javascript/PopupCalendar.js"></script>
	<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
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
	<style type="text/css">
		select{
			width:90px;
			font-family: 宋体;
			font-size: 12px;
		}
		.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
		}
		.seperator{
			width:100%;
			margin-bottom: -20px;
		}
		.seperator .first{
			width:5%;
			position: relative;
			float: left;
		}
		.seperator span{
			position: relative;
			float:left;
			font-family: 宋体;
			font-size: 14px;
		}
		.seperator .second{
			width:30%;
			position: relative;
		}
		.form{
			width:90px;
			height: 18px;
	
		}
	</style>  
  </head>
  
  <body>
  <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
  <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
  	<div style="position:relative;height:30px;width:800px;top:0px;">
  		
  		<span style="color:black;font-weight:bold;font-size=14;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;采集点电表耗电量明细</b></span>
  	</div>
  	<div class="seperator">
  		<div class="first"><hr></div><span ><font size="2">过滤条件</font></span><div class="second"><hr></div>
  	</div>
  	<form action="<%=request.getContextPath() %>/web/query/caijipoint/haodianliangleft.jsp" name="form1">
  		<table cellpadding="0" border="0" class="form_label">
  			<tr height="20" class="form_label">
	  			<td class="form_label">城&nbsp;&nbsp;市&nbsp;：&nbsp;
	  			
		  			<select name="shi" onchange="changeCity()">
		         		<option value="-1">请选择</option>
		         		<%
			         	ArrayList<?> shenglist = new ArrayList<String>();
			         	shenglist = commBean.getAgcode(shengId,"0",loginName);
			         	if(shenglist!=null){
			         		String sfid="",sfnm="";
			         		int scount = ((Integer)shenglist.get(0)).intValue();
			         		for(int i=scount;i<shenglist.size()-1;i+=scount)
		                    {
		                    	sfid=(String)shenglist.get(i+shenglist.indexOf("AGCODE"));
		                    	sfnm=(String)shenglist.get(i+shenglist.indexOf("AGNAME"));
		                    %>
		                    <option value="<%=sfid%>"><%=sfnm%></option>
		                    <%}
			         	}
			         %>
		         	</select><font color="red">&nbsp;*</font>
	         	</td>
		        <td class="form_label">&nbsp;区&nbsp;县&nbsp;：&nbsp;
		  			<select name="xian">
		         		<option value="-1">请选择</option>
		         	</select>
		        </td>		       
		       
		       <%
			Calendar cal=Calendar.getInstance();   
			String yf="",rq="";
			int y=cal.get(Calendar.YEAR);    
			String m=cal.get(Calendar.MONTH)+1+"";   
			String d=cal.get(Calendar.DATE)+"";   
			if(m.length()==1&&d.length()==1){
				yf=y+"-0"+m+"-01";
				rq=y+"-0"+m+"-0"+d;
			}if(m.length()==1&&d.length()==2){
				yf=y+"-0"+m+"-01";
				rq=y+"-0"+m+"-"+d;
			}if(m.length()==2&&d.length()==2){
				yf=y+"-"+m+"-01";
				rq=y+"-"+m+"-"+d;
			}if(m.length()==2&&d.length()==1){
				yf=y+"-"+m+"-01";
				rq=y+"-"+m+"-0"+d;
			}
			

			%>
		       
		         <td class="form_label">站点名称：
		         	<input type="text" name="stationname" value=""  class="form" />
		        </td>
		         <td class="form_label">起始时间：
		         	<input type="text" name="startmonth" value="<%=yf %>"  class="form" onFocus="getDateString(this,oCalendarChs)" />
		        </td>
		         <td class="form_label">结束时间：
		         	<input type="text" name="endmonth" value="<%=rq %>"  class="form" onFocus="getDateString(this,oCalendarChs)"/>
		        </td>
				<td colspan=2>
					<div id="query2" style="position:relative;width:59px;height:23px;cursor:pointer; float: left; left: 10px;">
						 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
						 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
				    </div> 
				</td>
	        </tr>
	        <tr class="form_label">
	        	<td  colspan="9" class="form_label">
	        		<div class="seperator">
				  		<div class="first"><hr></div><span><font size="2">详细信息</font></span><div class="second"><hr></div>
				  	</div>
	        	</td>
	        </tr>
	     </table>
  	</form>
  	
  	<iframe name="treeMap" width="600px" height="200px" frameborder="0" src="<%=path %>/web/query/caijipoint/collectQueryleft.jsp"></iframe>
  	<span style="width:20px"></span>
    <iframe name="treeNodeInfo" width="450px" height="200px" frameborder="0" src="<%=path %>/web/query/caijipoint/collectQueryright.jsp"></iframe>
    <br/>
   <iframe name="treeMap1" width="800px" height="400px" frameborder="0" src="<%=path %>/web/query/caijipoint/collectQueryMap.jsp"></iframe>   
  </body>
  <script type="text/javascript">
		var path = '<%=path%>';
		function changeCity(){
			var sid = document.form1.shi.value;
			if(sid=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选择","-1"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=shi&pid="+sid,"shi");
			}
		}
		function updateQx(res){
			var shilist = document.all.xian;
			shilist.options.length="0";
			shilist.add(new Option("请选择","-1"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		function selectTree(){
			
			var a = "<a href='<%=path %>/web/query/caijipoint/collectQueryleft.jsp?";
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
		}
		$("#query2").click(function(){
			
			if(document.getElementById("startmonth").value=="" ||document.getElementById("endmonth").value=="" )
		{
	   alert("日期不能为空");
	   
	   }else{
			selectTree();
			}
			
		});
		
var start='<%=startmonth%>';
var s="null";
if(start==s){
document.form1.startmonth.value='<%=yf%>';
}else if(start!=s){
document.form1.startmonth.value='<%=startmonth%>';
}
if(start==s){
document.form1.endmonth.value='<%=rq%>';
}else if(start!=s){
document.form1.endmonth.value='<%=endmonth%>';
}
		
	</script>
</html>
			