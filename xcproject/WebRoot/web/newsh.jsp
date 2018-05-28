<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.ElectricFeesQueryBean" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage" %>
<%

        Account account = (Account)session.getAttribute("account");
        String accountId = account.getName();
      
        String roleid = account.getRoleId();//权限
        System.out.println("权限"+roleid);
        String path = request.getContextPath();
        String loginId = account.getAccountId();
        System.out.println("路徑"+path);
        String loginName = account.getAccountName();
        ElectricFeesQueryBean beanb = new ElectricFeesQueryBean();
		//ElectricFeesFormBean be=beanb.getCaiji(loginId);
		
		//网络正常与中断
		haodianliangBean bea = new haodianliangBean();
		String flag="1";
		String sum="0";
		String sum2="0";
		String sum3="0";
		ArrayList<haodianliangBean> list=new ArrayList<haodianliangBean>();
		//list =bea.getWarnCount(loginId);
		for(haodianliangBean bean:list){
			sum=bean.getZhengchang();
			sum2=bean.getZhongduan();
		}
		//sum3=be.getCaijizd();
		if("".equals(sum)||null==sum)sum="0";
		if("".equals(sum2)||null==sum2)sum2="0";
		if("".equals(sum3)||null==sum3)sum3="0";
		
        %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
         <!-- 实例化一个类      对象名为bean -->
		<jsp:useBean id="bean" scope="page"      
			class="com.noki.mobi.sys.javabean.NoticeBean">
		</jsp:useBean>

		<title>管理控制台</title>
		<script language="javascript" type="text/javascript">
		    var path = '<%=path%>';
		     var loginId = '<%=loginId%>';
		     var autoauditfees1 = "0";
		    var caiji = "1";
			function infoNotice(noticeId) {
				window.open('sys/infoNotice.jsp?noticeId=' + noticeId, '',
								'width=700,height=500,status=yes,scrollbars=yes,resizable=yes,left=150,top=100')
			}
			
			
            
	        function Warn11(){
				self.parent.frames["main"].location=path+"/web/jizhan/sitemanage.jsp?caiji="+caiji;
            }
            function War2(){
			self.parent.frames["main"].location=path+"/web/query/caijipoint/collectWarn.jsp?str=0";
			}
			function War1(){
	
			self.parent.frames["main"].location=path+"/web/query/caijipoint/collectWarn.jsp?str=1";
			}
			function dfinfo5(id){
    		var url=path+"/web/sys/ggaoxx.jsp?id="+id;		
			var obj = new Object();
			obj.mid='mid';
   			 var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
			}
			function Warntj3() {//新加的对标管理
				self.parent.frames["main"].location = path+"/web/query/sortanalysis/jizhannenghaoQuery.jsp";
			}
			function Warntj4() {
				self.parent.frames["main"].location = path+"/web/query/caijipoint/wanquanpipeiQuery.jsp";
			}
		</script>
		<style>
			.style1 {
				color: #FF9900;
				font-weight: bold;
			}
			
			body {
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
			}
			
			.form_label{
			font-family: 宋体;
			font-size: 12px;
		    }
		</style>
		
	

	</head>

	<body class="body" style="overflow-x: hidden;">
		<script type="text/javascript">
			function ShowHideSearchRegion(trObject, SelfObject) {
				if (trObject.style.display == "none") {
					trObject.style.display = ""
					SelfObject.innerHTML = "<img border=\"0\" src=\"../images/1.gif\">"
			
				} else {
					trObject.style.display = "none"
					SelfObject.innerHTML = "<img border=\"0\" src=\"../images/SearchDown.gif\">"
				}
			}
			
			
		</script>
		<form action="" name="form1" method="post">
		<table style="width: 100%; margin-top: 8px" class="form_label">
		<tr><td colspan="3">
			  <div id="parent" style="display:inline">
               <hr></hr>
               </div></td></tr>
			<tr><td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;公告信息</td></tr>
			<tr>
				<td  width="11%">&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="<%=path %>/web/images/gonggao.jpg" width="40%" height="40%"/>
			    </td >
				<td>
					<table width="80%"  cellspacing="0" cellpadding="0">
			      
			
			<%
			
       SiteManage beans = new SiteManage();
       	 ArrayList fylist = new ArrayList();
        fylist = beans.getGgao();
       	 
       	 
		String bt = "",ggtime = "",id = "", lrr="";
		
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
				id = (String) fylist.get(k + fylist.indexOf("ID"));
       			bt = (String) fylist.get(k + fylist.indexOf("BT"));
       			ggtime = (String) fylist.get(k + fylist.indexOf("GGTIME"));
       			lrr = (String) fylist.get(k + fylist.indexOf("LRR"));
       			
       			if (bt == null)
       				bt = "";
       			if (ggtime == null)
       				ggtime = "";
       		

       		
       %>
       <tr class="form_label">
       		
       		<td >
       			&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:dfinfo5('<%=id%>')"><%=bt%></a>
       		</td>
       		<td >
       			<%=ggtime%>
       		</td >
      
       		<td class="form_label">
       			<%=lrr%> 
       		</td>
       		

       </tr>
       <%
       	}
       %>
        <%
       	}
       %>
       
       <tr><td colspan="3" align="right"><a href="<%=path %>/web/sys/allggao.jsp"><font  style="font-size: 12px;">更多>></font></a></td></tr>
		
			</table></td></tr>
			  <tr><td colspan="3">
			  <div id="parent" style="display:inline">
               <hr></hr>
               </div></td></tr>
		</table>
		<table style="width: 100%; margin-top: 8px" class="form_label" cellspacing="0" cellpadding="0">
						<tr>
						   <td  width="13%">&nbsp;&nbsp;
						     <font size="2" >对标管理：</font>
						   </td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
						    <td  width="10%">
						    <div>&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path%>/web/images/tongjichaxun.jpg" width="30%" height="30%"/></div>
					        </td >
						 	<td width="15%"><div id="jznhbzcx"  style="display:none;"><a href="#" onclick="Warntj3()">基站能耗标准查询 </a></div></td>
  	                     	<td ><div id="cbjzgl"  style="display:none;"><a href="#" onclick="Warntj4()">超标基站管理</a></div></td>
					     </tr> 						
						</table>
						<div id="parent" style="display:inline">
                              <hr></hr>
                        </div>
		<table style="width: 100%; margin-top: 8px" class="form_label">
				
						<tr>
						     <td width="13%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="2" >基础查询：</font></td>
						</tr>
						<tr>
						     <td  width="10%">&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path %>/web/images/jichu.jpg" width="35%" height="35%"/></td >
						 	 <td ><div id="zdcx"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/query/basequery/stationPointQuery.jsp" >站点查询</a></div></td>
  	                         <td ><div id="dlcx"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/query/basequery/ammeterDegreeQuery.jsp" >电量查询	</a></div></td>
  	                         <td ><div id="dfjnmx"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/query/basequery/electricFeesQuery.jsp" >电费查询</a></div></td>
  	                         <!--
						 	 <td ><div id="dfcx"  style="width:100%;height:100%;display:none;"><a href="/energy/web/electricfees/electricFeesInput.jsp" >电费查询</a></div></td>
						     -->
						     <td ><div id="sjtbbsc"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/cx/groupdata_sheng.jsp" >省级集团报表生成</a></div></td>
						     <td ><div id="sjtbbscd"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/cx/groupdata_shi.jsp" >市级集团报表生成</a></div></td>
					         <td ><div id="cjgj"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/query/caijipoint/collectWarn.jsp" >采集告警</a></div></td>
					     </tr> 
						
						</table>
						<div id="parent" style="display:inline">
                              <hr></hr>
                        </div>
						<table style="width: 100%; margin-top: 8px" class="form_label">
						<tr>
							<td width="13%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="2" >统计汇总查询：</font></td>
						</tr>
						<tr>
						    <td  width="10%">&nbsp;&nbsp;&nbsp;&nbsp;
						    <img src="<%=path %>/web/images/tongji.jpg" width="30%" height="30%"/>
					        </td >
					        								
					        
							
							<td ><div id="zdgl"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/jizhan/sitemanage.jsp" >站点管理</a></div></td>
							<td ><div id="bgzdsz"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/sightcing/signmanage.jsp" >标杆站点设置</a></div></td>
							<td ><div id="zdjfqktj"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/cx/sitepaymentstatistics.jsp" >站点缴费情况统计</a></div></td>
							<td ><div id="cbltj"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/cx/chaobiaolv.jsp" >抄表率统计</a></div></td>
							
										
						</tr>
						</table>
						<div id="parent" style="display:inline">
                              <hr></hr>
                        </div>
					<%--<table style="width: 100%; margin-top: 8px" class="form_label">
						<tr>
							<td width="13%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="2" >决策管理：</font></td>
						</tr>
						<tr>
							<td  width="10%">&nbsp;&nbsp;&nbsp;&nbsp;
						    	<img src="<%=path %>/web/images/jueche.jpg" width="30%" height="30%"/>
					        </td >
					        <td ><div id="zdfxb"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/jizhan/siteAnalysisSheet.jsp" >站点分析表</a></div></td>
					        <td ><div id="zddbfx"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/sightcing/sitebenchmarking.jsp" >站点对标分析</a></div></td>
							<td ><div id="zdmxtj"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/cx/stationmx.jsp">站点明细统计</a></div></td>
							<td ><div id="dsjzydlhzfx"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/cx/stationfees.jsp">地市基站用电量汇总分析</a></div></td>
							<td ><div id="cjdltj"  style="width:100%;height:100%;display:none;"><a href="<%=path %>/web/query/caijipoint/collectQuery.jsp">采集电量统计</a></div></td>
					    </tr>
					   </table>
					   <table style="width: 100%; margin-top: 8px" class="form_label" cellspacing="0" cellpadding="0">
					    <tr><td colspan="6">
			            <div id="parent" style="display:inline">
                        <hr></hr>
                        </div></td></tr>
					    <tr>
							<td width="13%">&nbsp;&nbsp;&nbsp;<font size="2" >采集电表通讯状态：</font></td>
						</tr>
					    <tr>
					        <td  width="10%">
						    	<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<%=path %>/web/images/tongxun.jpg" width="33%" height="33%"/></div>
					        </td >
					   	    <td>
					   	    <div id="cjzd"  style="width:100%;height:100%;display:none;">
					   	          采集站点<a href="#" onclick="Warn11()" ><font size="3" color="red"><%=sum3 %></font>条</a></div></td>
					    	<td>
					    	<div id="zc"  style="width:100%;height:100%;display:none;">正常<a href="#" onclick="War1()"><font size="3" color="red"><%=sum %></font></a></div></td>
					    	<td>
					    	<div id="wlzd"  style="width:100%;height:100%;display:none;">
					    	网络中断<a href="#" onclick="War2()"><font size="3" color="red"><%=sum2 %></font></a></div></td>
 						</tr>
					</table>
		--%></form>
	
	<script type="text/javascript">
	var role='<%=roleid%>';
	
      if(role=="64"||role=="63"||role=="65"||role=="102"||role=="1"||role=="104"||role=="81"||role=="82"||role=="83"||role=="84"){
		document.getElementById("zdcx").style.display="block";//站点查询
		document.getElementById("dlcx").style.display="block";//电量查询
		//document.getElementById("dfcx").style.display="block";//电费查询
		document.getElementById("dfjnmx").style.display="block";//电费缴纳明细
		document.getElementById("zdgl").style.display="block";//站点管理
	}

    if(role=="81"||role=="82"||role=="1"||role=="83"||role=="84"){
    document.getElementById("sjtbbsc").style.display="block";//省集团报表生成
    }
    if(role=="63"||role=="64"||role=="65"||role=="1"){
    document.getElementById("sjtbbscd").style.display="block";//市集团报表生成
    //document.getElementById("dsjzydlhzfx").style.display="block";//地市基站用电量汇总分析
    
    }
     if(role=="63"||role=="64"||role=="65"||role=="81"||role=="82"||role=="1"||role=="83"||role=="84"){
     
     document.getElementById("cjgj").style.display="block";//采集告警
     document.getElementById("bgzdsz").style.display="block";//标杆站点设置
     //document.getElementById("zddbfx").style.display="block";//站点对标分析
     //document.getElementById("cjdltj").style.display="block";//采集电量统计
     }
     if(role=="102"||role=="104"||role=="63"||role=="64"||role=="65"||role=="1"){
    
      document.getElementById("zdjfqktj").style.display="block";//站点缴费情况统计
      document.getElementById("cbltj").style.display="block";//抄表率统计
      }
       if(role=="104"||role=="63"||role=="64"||role=="65"||role=="81"||role=="82"||role=="1"||role=="83"||role=="84"){
      
      // document.getElementById("zdfxb").style.display="block";//站点分析表
       //document.getElementById("zdmxtj").style.display="block";//站点明细统计
       }
      
    if(role=="62"||role=="65"||role=="1"||role=="81"||role=="82"||role=="83"||role=="84"){
   // document.getElementById("cjzd").style.display="block";//采集站点
   // document.getElementById("zc").style.display="block";//正常
     //document.getElementById("wlzd").style.display="block";//网络中断
    
    }//新加的对标管理
    if (role == "64" || role == "63" || role == "65" || role == "1" || role == "81" || role == "82"|| role == "83"|| role == "84"|| role == "85") {
	document.getElementById("jznhbzcx").style.display = "block";//基站能耗标准查询
	document.getElementById("cbjzgl").style.display = "block";//超标基站管理
	}

</script>
</body>
</html>
