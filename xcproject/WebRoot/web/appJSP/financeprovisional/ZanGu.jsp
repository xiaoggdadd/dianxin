<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.function.*" %>
<%@ page import="java.text.*,java.util.regex.Pattern"%>
<%@ page import="com.noki.mobi.common.CommonBean" %>
<%@ page import="com.ptac.app.electricmanageUtil.Format" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String loginName = (String)session.getAttribute("loginName");
Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
String sheng = (String)session.getAttribute("accountSheng");
String roleId = (String)session.getAttribute("accountRole");
String accountname=account.getAccountName();
String agcode1="";
String thisdatetime1=request.getParameter("zgsj");
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
String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";//站点名称
String stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
String dfzflxx1 = request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";
String zgsj= request.getParameter("zgsj")!=null?request.getParameter("zgsj"):"";
String zt= request.getParameter("zt")!=null?request.getParameter("zt"):"1";
String zgqssj= request.getParameter("zgqssj")!=null?request.getParameter("zgqssj"):"";//
String jzproperty= request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";//站点属性
String whereStr="";
String Str="";
String Wstr="";
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String bijiao="";
int numcolor = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;//条数 ,查出数据的条数，用于颜色设置
String color = "";//颜色
String shuju= request.getParameter("zangushuju")!=null?request.getParameter("zangushuju"):"";//
int intnum =0;
String ff="";
String datetime=new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
	   //日期默认为本月月底
        Date tt1 = new Date(); //得到当前时间
        int mm1 = tt1.getMonth();
       // tt1 = new Date(tt1.getYear(), tt1.getMonth(), 0); //得到本月最后一天
  		tt1 = new Date(tt1.getYear(), tt1.getMonth() + 1, 0); //得到本月最后一天
  		
  		String s=datetime+"-"+tt1.getDate();
  		if(zgsj==null||"".equals(zgsj)){
  			zgsj=s;
  		
  		}
  		if(zgsj!=null){
  			bijiao=zgsj.substring(0,7);
  			zanguBean beann=new zanguBean();
  			boolean flag = beann.getZanGu(bijiao,shi);
  					if(flag){
  						ff="you";
  					}  
  			}	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<style type="text/css">
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
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px

		}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.bttcn{color:black;font-weight:bold;}
</style>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script >
//var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
//oCalendarEn.Init();
//var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
//oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
//oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
//oCalendarChs.oBtnTodayTitle="今天";
//oCalendarChs.oBtnCancelTitle="取消";
//oCalendarChs.Init();
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

    function queryDegree(){
    	var path='<%=path%>';
    	var bt = document.form1.zgsj.value;
		var et = document.form1.shi.value;
		if(bt == ""){
			alert("请选择暂估结束月份！");
			return;
		}
	       document.form1.action=path+"/servlet/ZanGuServlet?action=zanguchaxun&chaxun=chaxun";
	       document.form1.submit();
	  	   showdiv("请稍等..............");
    }
    //暂估数据分析
    function zanGuShuJu(){
    	var path = '<%=path%>';
    	var bt = document.form1.zgsj.value;
		var et = document.form1.shi.value;
		if(et=="0"||et==""){
			alert("请选择要暂估的地市");
			return;
		}
		if(bt == ""){
			alert("请选择暂估结束月份！");
			return;
		}
    	 document.form1.action=path+"/servlet/ZanGuServlet?action=zangu";
	     document.form1.submit();
	  	 showdiv("正在进行大批量暂估数据分析并保存请稍等..............");
    	
    }
$(function(){
    
	$("#query").click(function(){
		queryDegree();
	});
	$("#daochuBtn").click(function(){
		exportad();
	});
	$("#zhongxin").click(function(){
		exportadzhongxin();
	});
	$("#wuzhongxin").click(function(){
		exportadwu();
	});
	$("#baocun").click(function(){
		baocun();
	});
	$("#zangushuju").click(function(){
		zanGuShuJu();
		});
});
</script>
  </head>
  <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
  <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
  <body>
   <form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr>
				<td colspan="8" width="50" >
                <div style="width:200px;height=50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">暂估查询 </span>
	              </div>
	              
	              
	              </td>
			</tr>
			<tr class="form_label">
				<td colspan="8">
                       <div id="parent" style="display:inline"></div>
                       <div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
               <div class="bttcn">  
                <% 
		     		if(shuju.equals("1")){
		     	%>
			 <span style="font-size:12px;color:red">本地市的暂估数据已保存成功，请点击"查询"查看详细信息</span>      
		     	<%
		     		}else if(shuju.equals("1")){%>
		     		<span style="font-size:12px;color:red">本地市的暂估数据没有保存成功，请联系管理员分析原因</span>    	
		       <%} %>
		     		
		     		
		     		
		     		
		     		
		     		</div> 
                
                
                </td>
    
            </tr>
            <tr>
            <td>
            <table><tr  class="form_label">
	         	<td>地市：</td>
	         	<td><select name="shi" id="shi" style="width:130" onchange="changeShi()" class="selected_font">
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
	         	</select>
	         	</td>
	         	<td>区、县：</td>
	         	<td><select name="xian" id="xian" style="width:130" onchange="changeXian()" class="selected_font" >
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
	         	<td>审核截止点</td>
	         	<td>
	         		<select  name="zt" id="zt" style="width:130" class="selected_font" >
	         			<option value="1">财务审核通过</option>
	         		</select>
	         	
	         	</td>
	    
	         	<td>暂估结束时间</td>
		        <td > <input type="text" name="zgsj" class="selected_font" value="" readonly="readonly"  onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"  style="width: 130px;"/><span class="style1">&nbsp;*</span></td>	 
                <td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p></td>
					
         		<td align="right"> 
         			
			         <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:100%;right:4px;">
					 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
					 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
					</div>
					
			    </td> 
			    <% 
		     		if(roleId.equals("66")||roleId.equals("1")||roleId.equals("65")){   //判断 如果权限是管理员和市财务就显示保存静态数据按钮
		   
		     		%>
		     		<td align="right"> 
					<div id="zangushuju" style="position:relative;width:100px;height:23px;cursor: pointer;left:100%;right:30px;">
					 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
					 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">暂估数据分析</span>
					</div>
					</td>
					<%} %>
         					
			</tr>
	</table>
	</td></tr>	
 </table>
 
 <div style="width:88%;" > 
		<p id="box3" style="display:none">
		<table border='0'>
           
           <tr  class="form_label">
                	<td>乡镇：</td>
	         	<td><select name="xiaoqu" id="xiaoqu" style="width:130" class="selected_font">
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
            <td> 站点名称：</td>
           <td><input type="text" class="selected_font" name="zdname" value="<%if (null != request.getParameter("zdname")) out.print(request.getParameter("zdname"));%>" style="width: 130px;"/></td>
      		<td>站点类型：</td>
         	<td><select name="stationtype" class="selected_font" onchange="kzinfo()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = ztcommon.getSelctOptions("StationType");
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
         	<td class="form_label">电费支付类型：</td>
         	<td><select name="dfzflx" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList dfzflx = new ArrayList();
	         	dfzflx = ztcommon.getSelctOptions("DFZFLX");
	         	if(dfzflx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)dfzflx.get(0)).intValue();
	         		for(int i=cscount;i<dfzflx.size()-1;i+=cscount)
                    {
                    	code=(String)dfzflx.get(i+dfzflx.indexOf("CODE"));
                    	name=(String)dfzflx.get(i+dfzflx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	
         	</select></td>
	 </tr> 
	 <tr  class="form_label">
		 <%--
	 	<td>暂估起始时间</td>
	 	<td><input type="text" name="zgqssj" class="selected_font" value=""  onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"  style="width: 130px;"/></td>
		--%>
		<td>站点属性：</td>
		
         <td>
         	<select name="jzproperty" style="width:130" onchange="kzinfo()" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsxx = new ArrayList();
	         	zdsxx = ztcommon.getSelctOptions("zdsx");
	         	if(zdsxx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdsxx.get(0)).intValue();
	         		for(int i=cscount;i<zdsxx.size()-1;i+=cscount)
                    {
                    	code=(String)zdsxx.get(i+zdsxx.indexOf("CODE"));
                    	name=(String)zdsxx.get(i+zdsxx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
         	%>
         	</select>
         </td>
	 </tr>

	</table>
	</p>
	</div>	

  <table>
       <tr><td height="5"  colspan="4"  class="form_label">
	         <div id="parent" style="display:inline"></div>
	         <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
            </td>
       </tr>       
 </table>
 <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 	
 <table width="1400" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
 	  <tr height = "23" class="relativeTag">
 	    <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>	                      	
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>  
         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>  
        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td> 
        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>      
        <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
         <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td> 
        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估使用时间标志</div></td>
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">最近一次报账抄表时间</div></td>
	    <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估起始时间</div></td>	                      	
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估结束时间</div></td>          
        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估天数</div></td>
        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">日电费（元/天）</div></td>
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估金额</div></td>   
 	  </tr>
 	  	<c:forEach items="${list}"  var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       						<td><div align="center" >${status.count}</div></td>  
       						<td><div align="center" >${list.jzname}</div></td>  
       						<td><div align="center" >${list.zdid}</div></td>
       						<td><div align="center" >${list.dbid}</div></td>     		
       						<td><div align="center">${list.dbname}</div></td> 
							<td><div align="center" >${list.xian}</div></td>
       		  				<td><div align="center" >${list.property}</div></td>
       						<td><div align="right" >${list.stationtype}</div></td> 
       						<td><div align="right" >${list.dfzflx}</div></td> 
       						<td><div align="right" >${list.bzw}</div></td> 
       						<td><div align="right" >${list.lastaccountmonth}</div></td> 
       						<td><div align="center" >${list.zangustartmonth}</div></td> 
       						<td><div align="right" >${list.zanguendmonth}</div></td>
       						<td><div align="center" >${list.tianshu}</div></td>
       						<td><div align="center" >${list.dianfei}</div></td> 
       		 				<td><div align="center" >${list.zangumoney}</div></td>  
    					</tr>
					</c:forEach>
					<% 
						int i = 0;
						int j = 0;//f用于循环
						if (numcolor==0){
							for(i=0;i<17;i++){
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
      				<% 
							}
						}else if(!(numcolor > 16)){
    	  					int n = (numcolor-1)%16;
							for(j=n;j<16;j++){
            					if(j%2==0)
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
        			<% 
        					}
						}
					%>
 	  			<tr>
        				<td align="center">合计</td>
        				<td colspan="2" align="center">暂估金额合计：</td>
      					<td><font size="2">${zgmoneyhj}元 </font></td>
      					
        		</tr>
 	  
 	  
 	   <tr>
 	   <td><input type="hidden" name="ff" value="<%=ff %>" /></td>
 	   <td><input type="hidden" name="shi1" value="<%=shi %>" /></td>
 	   <td><input type="hidden" name="zgmonth" value="<%=bijiao %>" /></td>
 	   </tr>
 </table>
 </div>
<div style="width:100%;height:8%;border:1px" >	
	<table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		 <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
	     <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div></div></td>
	  </tr>
	  <tr>  
	  <td class="relativeTag" style="font-family: 宋体;font-size: 12px;height:23px;color:red;">
	  备注：有权限人员先点击“暂估数据分析”，在点击“查询”按钮时才会查出暂估数据信息<br>
	  此数据为实时数据，会随着站点启用状态、电表启用状态、电费单状态等的改变而改变，此数据仅供参考！
	  
	  
	  </td>
	     <td align="right">   
	    			
		     <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px; " >
		     <img src="<%=path %>/images/daoru.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
		     </div>
		   
	     </td>
	  </tr> 
	</table>
	<input type="hidden" name="lrren" value="<%=accountname %>">
</div>
</form>           
</body>
</html>
<script language="javascript">	
     var path = '<%=path%>';
    function changeShi(){
			var shi = document.form1.shi.value;
			if(shi=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选项","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=shi&pid="+shi,"shi");
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
		function changeXian(){
			var shi = document.form1.xian.value;
			if(shi=="0"){
				var shilist = document.all.xiaoqu;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=xian&pid="+shi,"xian");
			}
		}
		
		function updateXQ(res){
			var shilist = document.all.xiaoqu;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
 function exportad(){
	  if(document.getElementById("shi").value=="" || document.getElementById("zgsj").value==""){
	       alert("暂估日期不能为空！请填写");
	  }else{
     	 document.form1.action=path+"/servlet/ZanGuServlet?action=zanguchaxun&chaxun=daochu";        
     	 document.form1.submit();
      }
   }
  function exportadzhongxin(){
	  if(document.getElementById("shi").value=="" || document.getElementById("zgsj").value==""){
	       alert("暂估日期不能为空！请填写");
	  }else{
         var endzgsj=document.getElementById('zgsj').value;
         var whereStr ="<%=whereStr%>";
         var Wstr ="<%=Wstr%>";
         var Str ="<%=Str%>";
       //alert(whereStr+"Wstr:"+Wstr+"Str:"+Str+"endzgsj"+endzgsj);
     	window.open(path+"/web/newgn/zanguzhongxin.jsp?endzgsj="+endzgsj+"&whereStr="+whereStr+"&Wstr="+Wstr+"&Str="+Str);         
     	// document.form1.submit();
      }
   }
    function exportadwu(){
	  if(document.getElementById("zgsj").value==""){
	      alert("暂估日期不能为空！请填写");
	  }else{
         var endzgsj=document.getElementById('zgsj').value;
         var whereStr ="<%=whereStr%>";
         var Wstr ="<%=Wstr%>";
         var Str ="<%=Str%>";
        //alert(whereStr+"Wstr:"+Wstr+"Str:"+Str+"endzgsj"+endzgsj);
     	window.open(path+"/web/newgn/zanguwu.jsp?endzgsj="+endzgsj+"&whereStr="+whereStr+"&Wstr="+Wstr+"&Str="+Str);         
     	 // document.form1.submit();
      }
   }
     function baocun(){
    	 if(document.getElementById("ff").value==""){
    		   if(document.getElementById("zgsj").value==""){
			       alert("暂估日期不能为空！请填写");
			  }else{
		          document.form1.action=path+"/servlet/zangu?action=save";
				  document.form1.submit();
				  showdiv("请稍等..............");
		      }
    		 
    	 }else{
    		 if(confirm("您本月已添加！确认覆盖上次信息！")){
    			if(document.getElementById("zgsj").value==""){
			       alert("暂估日期不能为空！请填写");
			  }else{
		          document.form1.action=path+"/servlet/zangu?action=save";
				  document.form1.submit();
				  showdiv("请稍等..............");
		      }
    		 }
    		  
    	 }
	 
   }
		
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.stationtype.value='<%=stationtype1%>';
		document.form1.dfzflx.value='<%=dfzflxx1%>';
		document.form1.zgsj.value='<%=zgsj%>';
		document.form1.zt.value='<%=zt%>';
		document.form1.zgqssj.value='<%=zgqssj%>';
		document.form1.jzproperty.value='<%=jzproperty%>';
		
	
</script>
