<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    String dybzw="";
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
        
         String bztime = request.getParameter("bztime")!=null?request.getParameter("bztime"):"";
         String stationtype = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
         String dfzflx = request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";
         String zdmc = request.getParameter("zdmc")!=null?request.getParameter("zdmc"):"";
         String zdsx =request.getParameter("zdsx")!=null?request.getParameter("zdsx"):"0";
          String liuch = request.getParameter("liuch")!=null?request.getParameter("liuch"):"";
          String lururen =request.getParameter("lururen")!=null?request.getParameter("lururen"):"";
          String cwsh= request.getParameter("wcshenhe")!=null?request.getParameter("wcshenhe"):"0";
          String PJLX =request.getParameter("pjlx")!=null?request.getParameter("pjlx"):"";
          String gdfs=request.getParameter("gdfs")!=null?request.getParameter("gdfs"):"-1";
           String canshuStr="",color="";
     
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

.bttcn{color:BLACK;font-weight:bold;}

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
<script src="<%=path%>/javascript/wait.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script>

//var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
//oCalendarEnny.Init();

//var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
//oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
//oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
//		"八月", "九月", "十月", "十一月", "十二月");
//oCalendarChsny.oBtnTodayTitle = "确定";
//oCalendarChsny.oBtnCancelTitle = "取消";
//oCalendarChsny.Init();
</script>
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
    function queryDegree(){
    
                   document.form1.action=path+"/web/electricfees/dianfeidanquery.jsp?command=chaxun";
                   document.form1.submit();
                   showdiv("请稍等..........");
       
    }
    
  
    
    
    
    
    
    function checkDF(){
         	if(document.form1.DFCheck.checked){
         		for(var j = 0;j < document.form1.elements.length ; j++){
							document.form1.elements[j].checked=true
						}
         	}else{
         		for(var j = 0;j < document.form1.elements.length ; j++){
							document.form1.elements[j].checked=false
						}
         	}
        }
    $(function(){

$("#daochu").click(function(){
			exportad();
		});

		$("#dayin").click(function(){
			dayin();
			showdiv("请稍等..............");
		});
		$("#qxdayin").click(function(){
			qxdayin();
			//showdiv("请稍等..............");
		});
		$("#chaxun").click(function(){
			if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
	                   alert("城市不能为空");	   
	        }else{
				queryDegree();
				
			}
		});
	});
</script>

</head>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0302");

%>
<body  class="body" >
	<form action="" name="form1" method="POST">
			<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			        <tr >
					   <td colspan="4" width="50" >
						   <div style="width:700px;height:50px">
							  
						 		<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电费单打印</span>
						    </div>
						</td>
	    	        </tr>	  
	    	        <tr><td height="20" colspan="4" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td>
	    	</tr>
   		  	<tr>
   		  	  <td width="1200px">
				<table >
					<tr class="form_label">
		    		<td >城市：</td>
                    <td><select name="shi" id="shi" class="selected_font" onchange="changeCity()">
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
         	                            
                      <td>区县:</td>          
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
         	     </select></td>     
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
         	              <td >
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p>
				</td>
				  <td ><%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
                                 <div id="chaxun" style="position:relative;width:59px;height:23px;cursor: pointer;TOP:0PX">
			                        <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
			                        <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                        </div>      <%}%>
		                        
                  </td>
                 </tr>	
              </table>
              </td></tr>
			   
			   
			   <tr>
			      <td colspan="5">
					<div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table>
					  <tr class="form_label">
	         		   <td>报账月份：</td>
                        <td> <input type="text" class="selected_font" name="bztime" value="<%if (null != request.getParameter("bztime"))out.print(request.getParameter("bztime"));%>"
		                         	readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
                        </td> 
                        <td>站点类型：</td>
                        <td><select name="zdlx" class="selected_font" > 
                                    <option value="0">请选择</option>
          <%
          ArrayList stationtype1 = new ArrayList();
         		stationtype1 = ztcommon.getSelctOptions("StationType");
	         	if(stationtype1!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)stationtype1.get(0)).intValue();
	         		for(int i=cscount;i<stationtype1.size()-1;i+=cscount)
                    {
                    	code=(String)stationtype1.get(i+stationtype1.indexOf("CODE"));
                    	name=(String)stationtype1.get(i+stationtype1.indexOf("NAME"));
                    %>
                                   <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
                                </select></td>
                  <td> 流程号：</td>
                  <td><input type="text" name="liuch" value="<%if(null!=request.getParameter("liuch")) out.print(request.getParameter("liuch")); %>" class="selected_font"/></td>
                  <td>电费支付类型:</td>
                  <td><select name="dfzflx" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList dfzflx1 = new ArrayList();
         		dfzflx1 = ztcommon.getSelctOptions("dfzflx");
	         	if(dfzflx1!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)dfzflx1.get(0)).intValue();
	         		for(int i=cscount;i<dfzflx1.size()-1;i+=cscount)
                    {
                    	code=(String)dfzflx1.get(i+dfzflx1.indexOf("CODE"));
                    	name=(String)dfzflx1.get(i+dfzflx1.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>  
         	</td>
           </tr>
           <tr class="form_label">
              <td>站点名称：</td>
				<td><input type="text" name="zdmc" class="selected_font" value="<%if(null!=request.getParameter("zdmc")) out.print(request.getParameter("zdmc")); %>" /></td>
              <td>站点属性：</td>
              <td><select name="zdsx" class="selected_font" > 
                      <option value="0">请选择</option>
              <%
                ArrayList listzdsx = new ArrayList();
                listzdsx = ztcommon.getSelctOptions("zdsx");
         		
	         	if(listzdsx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)listzdsx.get(0)).intValue();
	         		for(int i=cscount;i<listzdsx.size()-1;i+=cscount)
                    {
                    	code=(String)listzdsx.get(i+listzdsx.indexOf("CODE"));
                    	name=(String)listzdsx.get(i+listzdsx.indexOf("NAME"));
                    %>
                                   <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
                                </select></td>
                <td>录入人:</td>
                <td><input type="text" name="lururen"  value="<%if(null!=request.getParameter("lururen"))out.print(request.getParameter("lururen")); %>" class="selected_font"/></td>
                <td>审核状态：</td>
                <td><select name="wcshenhe" class="selected_font" > 
                	<option value="0">人工市级审核通过</option>
                    <option value="-1">财务不通过</option>
                    
                </select></td>
           </tr>
           <tr class="form_label">
           		<td>票据类型</td>
           		<td><select name="pjlx" class="selected_font" > 
                      <option value="0">请选择</option>
              <%
                ArrayList listpjlx = new ArrayList();
              listpjlx = ztcommon.getSelctOptions("pjlx");
         		
	         	if(listpjlx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)listpjlx.get(0)).intValue();
	         		for(int i=cscount;i<listpjlx.size()-1;i+=cscount)
                    {
                    	code=(String)listpjlx.get(i+listpjlx.indexOf("CODE"));
                    	name=(String)listpjlx.get(i+listpjlx.indexOf("NAME"));
                    %>
                                   <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
              </select></td>
              
              <td>供电方式：</td>
              <td><select name="gdfs" class="selected_font" onchange="javascript:document.form1.manauditstatus2.value=document.form1.manauditstatus.value">         		
			         	<option value="-1">请选择</option>
			         	<option value="gdfs01">供电局</option>
			         	<option value="gdfs02">业主</option>  
			         	<option value="gdfs03">其他</option>   
			         	<option value="gdfs04">直供电</option>
			         	<option value="gdfs05">转供电</option>		
		         </select>
		      </td>
           </tr>
         </table>
	    </p>
		</div></td></tr></table>
 <table  height="23">
<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
       <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>
 <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >  	 	
				      <table width="100%" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                  		<tr height = "10" class="relativeTag">            
                      <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="DFCheck" onClick="checkDF()" /></div> </td>
                      <td  width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td> 
                       <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td> 
                       <td  width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                       <td  width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
            			<td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td>
                        <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
                        <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次读数</div></td>
                        <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次读数</div></td>
                        <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电量调整</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">耗电量</div></td>
                         
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费单价</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费调整</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实付费用</div></td>
                          <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">支付类型</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据类型</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据金额</div></td>
                          
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期电费</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期电量</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期单价</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">供电方式</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">备注</div></td>
                      </tr>
       <%
         
         String whereStr = "",str="",dystr="";
          
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" AND ZD.SHI='"+shi+"'";
				str=str+" AND ZD.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" AND ZD.XIAN='"+xian+"'";
				str=str+" AND ZD.XIAN='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" AND ZD.XIAOQU='"+xiaoqu+"'";
				str=str+" AND ZD.XIAOQU='"+xiaoqu+"'";
			}
			if(bztime != null && bztime != "" && !bztime.equals("0")){
				whereStr=whereStr+" AND TO_CHAR(DY.ACCOUNTMONTH,'yyyy-mm')='"+bztime+"'";
				str=str+" AND TO_CHAR(DY.ACCOUNTMONTH,'yyyy-mm')='"+bztime+"'";
			}
			if(dfzflx != null && dfzflx != "" && !dfzflx.equals("0")){
				whereStr=whereStr+" AND AM.DFZFLX='"+dfzflx+"'";
				str=str+" AND AM.DFZFLX='"+dfzflx+"'";
			}
			if(zdsx != null && zdsx != "" && !"0".equals(zdsx)){
			    whereStr=whereStr+" AND ZD.PROPERTY='"+zdsx+"'";
			    str=str+" AND ZD.PROPERTY='"+zdsx+"'";
			}
			if(zdmc !=null && zdmc != "" && !"0".equals(zdmc)){
			    whereStr=whereStr+" AND ZD.JZNAME LIKE '%"+zdmc+"%'";
			    str=str+" AND ZD.JZNAME LIKE '%"+zdmc+"%'";
			}
			if(lururen!=null&&lururen!=""&&!"0".equals(lururen)){
			 whereStr=whereStr+"  AND DY.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"+lururen+"')";
			 str=str+"  AND DY.ENTRYPENSONNEL IN (SELECT ACCOUNTNAME FROM ACCOUNT WHERE DELSIGN = 1 AND NAME='"+lururen+"')";
			}
			if(stationtype != null && stationtype != "" && !stationtype.equals("0")){
				whereStr=whereStr+" AND ZD.STATIONTYPE='"+stationtype+"'";
				str=str+" AND ZD.STATIONTYPE='"+stationtype+"'";
			}if(liuch != null && liuch != "" && !liuch.equals("0")){
				whereStr=whereStr+" AND DY.LIUCHENGHAO='"+liuch+"'";
				str=str+" AND DY.LIUCHENGHAO='"+liuch+"'";
			}else{
			      whereStr=whereStr+" AND DY.LIUCHENGHAO is null ";
			      str=str+" AND DY.LIUCHENGHAO is null ";
			      dybzw="1";
			}
			if(cwsh !=null && cwsh != "" && !"0".equals(cwsh)){
				 whereStr=whereStr+" AND DY.MANUALAUDITSTATUS = '-1'";
			     str=str+"  AND DY.FINANCEAUDIT='-1'";
			     dystr=dystr+" AND DY.MANUALAUDITSTATUS = '-1'";

			}else{
				 whereStr=whereStr+" AND DY.MANUALAUDITSTATUS >= '1'";
				 dystr=dystr+" AND DY.MANUALAUDITSTATUS >= '1'";
			}
			if(PJLX != null && PJLX != "" && !PJLX.equals("0")){
				whereStr=whereStr+" AND DY.NOTETYPEID='"+PJLX+"'";
				str=str+" AND DY.NOTETYPEID='"+PJLX+"'";
			}
			
			if(gdfs != null && !gdfs.equals("") && !gdfs.equals("-1")){
			    if(gdfs.equals("0")){
					whereStr=whereStr+" AND ZD.GDFS='"+gdfs+"'"+"OR ZD.GDFS IS NULL)";
					str=str+" AND ZD.GDFS='"+gdfs+"'"+"OR ZD.GDFS IS NULL)";
			    }else{
					whereStr=whereStr+" AND ZD.GDFS='"+gdfs+"'";
					str=str+" AND ZD.GDFS='"+gdfs+"'";
				}
			}
			
        ElectricFeesBean bean = new ElectricFeesBean();
        List<ElectricFeesFormBean> fylist=null;
        if("chaxun".equals(request.getParameter("command"))){
			fylist= bean.getPageDataCaiwu(whereStr,str,loginId);
		}else{
			fylist=null;
		}
       	allpage=bean.getAllPage();
		String electricfeeId = "",unitprice = "",floatpay = "", actualpay = "";
		String jzname = "",df="",szdq="",jztype="",inputdatetime="",inputoperator="",lastdatetime="",thisdatetime="",uuid="",
		accountmonth="",lastdegree="",thisdegree="",actualdegree="",floatdegree="",fffs="",memo="",pjlx="",pjje="",zt="";
		//2014-4-15新增--上期电费-上期电量-上期单价---
		String lastelecfees = "", lastelecdegree = "", lastunitprice = "",dfbzw = "";
		int intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		int jnum=1;
		Double dianfei=0.0;	
		 if(fylist!=null)
		{
			//int fycount = ((Integer)fylist.get(0)).intValue();
			for(ElectricFeesFormBean bean1:fylist){
              linenum++;
		     //num为序号，不同页中序号是连续的
		    jzname = bean1.getJzname();
		    jzname = jzname != null ? jzname : "";
		    	
		    szdq = bean1.getSzdq();	
		    szdq = szdq != null ? szdq : "";
		     
		    electricfeeId = bean1.getElectricfeeId();	
		    electricfeeId = electricfeeId != null ? electricfeeId : "";
		    
		 	uuid = bean1.getDfuuid();	
		    uuid = uuid != null ? uuid : "";
		    			    
			jztype = bean1.getJztype();
			jztype = jztype != null ? jztype : "";			
		    
		    lastdatetime = bean1.getLastdatetime();
		    lastdatetime = lastdatetime != null ? lastdatetime : "";
		    
			thisdatetime = bean1.getThisdatetime();
			thisdatetime = thisdatetime != null ? thisdatetime : "";
						
			accountmonth = bean1.getAccountmonth();
			accountmonth = accountmonth != null ? accountmonth : "";
			
		    lastdegree = bean1.getLastdegree();
		    lastdegree = lastdegree != null ? lastdegree : "0";
		    
		    thisdegree = bean1.getThisdegree();
		    thisdegree = thisdegree != null ? thisdegree : "0";
		    
		    actualdegree = bean1.getBlhdl();
		    actualdegree = actualdegree != null ? actualdegree : "0";
		    
		    floatdegree = bean1.getFloatdegree();
		    floatdegree = floatdegree != null ? floatdegree : "0";
		    
		    unitprice = bean1.getUnitprice();
		    unitprice = unitprice != null ? unitprice : "0";
		    
		    floatpay = bean1.getFloatpay();
		    floatpay = floatpay != null ? floatpay : "0";
		    
		    actualpay = bean1.getActualpay();
		    actualpay = actualpay != null ? actualpay : "0";
		    
		    fffs = bean1.getDfzflx();
		    fffs = fffs != null ? fffs : "";
		    
		    memo = bean1.getMemo();
		    memo = memo != null ? memo : "";
		    
		    lastelecfees = bean1.getLastelecfees();
		    lastelecfees = lastelecfees != null ? lastelecfees : "";
		    
		    lastelecdegree = bean1.getLastelecdegree();
		    lastelecdegree = lastelecdegree != null ? lastelecdegree : "";
		    
		    lastunitprice = bean1.getLastunitprice();
		    lastunitprice = lastunitprice != null ? lastunitprice : "";
		    
		    pjlx = bean1.getNotetypeid();
		    pjlx = pjlx != null ? pjlx : "";
		    pjje = bean1.getPjjedy();
		    pjje = pjje != null ? pjje : "0";
		    
		    zt=bean1.getManualauditstatus();
		    if(null==pjje||pjje.equals("")||pjje.equals(" ")||pjje.equals("null"))pjje="0";
		    if(null==actualpay||actualpay.equals("")||actualpay.equals(" ")||actualpay.equals("null"))actualpay="0";
		    if(null==actualdegree||actualdegree.equals("")||actualdegree.equals(" ")||actualdegree.equals("null"))actualdegree="0";
		    if(null==lastdegree||lastdegree.equals("")||lastdegree.equals(" ")||lastdegree.equals("null"))lastdegree="0";
		    if(null==thisdegree||thisdegree.equals("")||thisdegree.equals(" ")||thisdegree.equals("null"))thisdegree="0";
		    if(null==unitprice||unitprice.equals("")||unitprice.equals(" ")||unitprice.equals("null"))unitprice="0";
		    if(null==floatdegree||floatdegree.equals("")||floatdegree.equals(" ")||floatdegree.equals("null"))floatdegree="0";
		    if(null==floatpay||floatpay.equals("")||floatpay.equals(" ")||floatpay.equals("null"))floatpay="0";
		    
            DecimalFormat mat =new DecimalFormat("0.00");
            pjje=mat.format(Double.parseDouble(pjje));
            actualpay=mat.format(Double.parseDouble(actualpay));
            floatpay=mat.format(Double.parseDouble(floatpay));
            
            DecimalFormat dlmat=new DecimalFormat("0.0");
            actualdegree=dlmat.format(Double.parseDouble(actualdegree));
            lastdegree=dlmat.format(Double.parseDouble(lastdegree));
            thisdegree=dlmat.format(Double.parseDouble(thisdegree));
            floatdegree=dlmat.format(Double.parseDouble(floatdegree));
            
            DecimalFormat price=new DecimalFormat("0.0000");
            unitprice = price.format(Double.parseDouble(unitprice));
            dfbzw = bean1.getBzw();
            
          
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
          intnum++;

       %>
       <tr bgcolor="<%=color%>">
            <td>
       			<div align="center" >
       			<input type="checkbox" name="itemSelected" value="<%=uuid%>ssss=<%=dfbzw%>" />
       			<input type="hidden" type="checkbox" name="test1[]" value="<%=zt %>" /></div>

       		</td>
       		<td>
       			<div align="center" ><%=jnum++%></div>
       		</td>
            <td>
       			<div align="left" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>   
       		<td>
       			<div align="center" ><%=jztype%></div>
       		</td>          
            <td>
       			<div align="center" ><%=lastdatetime%></div>
       		</td>
            <td>
       			<div align="center" ><%=thisdatetime%></div>
       		</td>
       		
       		<td>
       			<div align="right" ><%=lastdegree%></div>
       		</td>
       		       		<td>
       			<div align="right" ><%=thisdegree%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=floatdegree%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=actualdegree%></div>
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
       			<div align="center" ><%=accountmonth%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=fffs%></div>
       		</td>
       		<td>
       			<div align="center" ><%=pjlx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=pjje%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lastelecfees%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lastelecdegree%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lastunitprice%></div>
       		</td>
       		<td>
       			<div align="center" ><%=bean1.getGdfs()%></div>
       		</td>
       		<td>
       			<div align="center" ><%=memo%></div>
       		</td>
         
       </tr>
       <%}}%>
       
 <% if (intnum==0){
         for(int i=0;i<17;i++){
          if(i%2==0){
			    color="#DDDDDD" ;
          }else{
			    color="#FFFFFF";
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
        </tr>     
        <% }}else if(!(intnum > 16)){
    	  for(int i=((intnum-1)%16);i<16;i++){
            if(i%2==0)
			    color="#FFFFFF" ;
            else
			    color="#DDDDDD";
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
        </tr>
        <% }}%>
  	 </table> 
  	 </div>
  	 

      <div style="width:100%;height:8%;border:1px" >	
			<table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
               		<div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
          		</div></td>
			</tr>
 
  		<tr>  		
   			<td align="right">  
   			 <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                        
                               <div id="daochu" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:6px; ">
		                            <img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">导出</span>
								</div>
                               <%}%>  						
                  <%if(permissionRights.indexOf("PERMISSION_PRINT")>=0){%> 
				   <div id="dayin" style="width:90px;height:23px;cursor:pointer;float:right;position:relative;right:9px; ">
		              <img alt="" src="<%=path %>/images/dayin.png" width="100%" height="100%" />
		                  <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">确认流程号</span>
					</div>
                      <%}%>
                       						
                  <%if(permissionRights.indexOf("PERMISSION_PRINT")>=0){%> 
				   <div id="qxdayin" style="width:80px;height:23px;cursor:pointer;float:right;position:relative;right:14px; ">
		              <img alt="" src="<%=path %>/images/dayin.png" width="100%" height="100%" />
		                  <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">取消打印</span>
					</div>
                      <%}%> 
            </td>
  		</tr>
  <tr>
  <td>
   <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
  </td></tr>
  
</table>
</div>
</form>
</body>
</html>
<script language="javascript">
	var path = '<%=path%>';
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
	 function dayin(){//打印流程号
		 var shi='<%=shi%>';
		var  dayin='<%=dybzw%>';
        var m = document.getElementsByName('itemSelected');  
        var lch=document.form1.liuch.value;
        var l = m.length; 
        var chooseIdStr1 = ""; 
        var chooseIdStr2 = ""; 
        var  dystr="<%=dystr%>";
        var bz=0;        
        for (var i = 0; i < l; i++) {  
        if(m[i].checked == true){
              bz+=1;
             var j = m[i].value.toString().indexOf("ssss=");//截取电费支付类型
             var chooseIdStr3 = m[i].value.toString().substring(0,j);
             var zflx1 = m[i].value.toString().substring(j+5,m[i].value.toString().length);
             if(zflx1=="1"){
	             chooseIdStr1 = chooseIdStr1 + "'"+chooseIdStr3 +"',";
             }else if(zflx1=="2"){
             	 chooseIdStr2 = chooseIdStr2 +"'"+ chooseIdStr3 +"',";
             } 
          }               
        } 
        chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
        chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
	    if(bz<=240){
	        if(bz>=1){
	        if(dayin=="1"){
	        lch="";
	        }
	            //document.form1.action=path+"/web/electricfees/dianfeidandayin.jsp?chooseIdStr="+chooseIdStr;
	            //document.form1.submit();
	            
	            alert("打印："+dayin);
	           document.form1.action=path+"/web/electricfees/dianfeidanpiliangdayin.jsp?chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2+"&lch="+lch+"&shi="+shi+"&dystr="+dystr+"&dayin="+dayin;
	          //  alert(document.form1.action);
	           document.form1.submit(); 
	       	}else{
				alert("请选择信息！");
	        }
	    }else{
	      	alert("您选择信息条数超过240条，信息量过大，请确定后重新执行！");
	    }        	
   }
   function qxdayin(){//取消流程号
        var m = document.getElementsByName('itemSelected');
        var c = document.getElementsByName('test1[]');
        var lch=document.form1.liuch.value;
        var countct=0;
        var l = m.length; 
        var chooseIdStr1 = ""; 
        var chooseIdStr2 = ""; 
        var bz=0;        
        for (var i = 0; i < l; i++) {  
        if(m[i].checked == true){
              bz+=1;
             var j = m[i].value.toString().indexOf("ssss=");//截取电费支付类型
             var chooseIdStr3 = m[i].value.toString().substring(0,j);
             var zflx1 = m[i].value.toString().substring(j+5,m[i].value.toString().length);
			if(zflx1=="1"){
           		 chooseIdStr1 = chooseIdStr1 + "'"+chooseIdStr3 +"',";
             }else if(zflx1=="2"){
             	 chooseIdStr2 = chooseIdStr2 +"'"+ chooseIdStr3 +"',";
             } 
          }
        if(m[i].checked == true){
       			if(c[i].value=='2'){
       				countct++;
       			}
       		}
        
        
        } 
        chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
        chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
       if(countct!=0){
       	alert("财务审核已通过 ！不允许取消打印 ！");
       	}else{
	    if(bz<=240){
	        if(bz>=1){
	           document.form1.action=path+"/servlet/electricfees?action=qxdayin&chooseIdStr1="+chooseIdStr1+"&dybzw=1&chooseIdStr2="+chooseIdStr2;
	           document.form1.submit();
	       	}else{
				alert("请选择信息！");
				
	        }
	    }else{
	      	alert("您选择信息条数超过240条，信息量过大，请确定后重新执行！");
	    }        	
   }
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
		return;s
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
     function exportad(){
         var   zdname="<%=zdmc%>";
           var whereStr="<%=whereStr%>";
            var Str ="<%=str%>";
        	document.form1.action=path+"/web/electricfees/电费单打印导出.jsp?&whereStr="+whereStr+"&str="+Str+"&zdname="+zdname+"&command=daochu";
            document.form1.submit();
   }
</script>
<script language="javascript">

var path='<%=path%>';
   		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.zdlx.value='<%=stationtype%>';
		document.form1.dfzflx.value='<%=dfzflx%>';
		document.form1.zdmc.value='<%=zdmc%>';
		document.form1.zdsx.value='<%=zdsx%>';
		document.form1.pjlx.value='<%=PJLX%>';
		document.form1.gdfs.value='<%=gdfs%>';
 </script>