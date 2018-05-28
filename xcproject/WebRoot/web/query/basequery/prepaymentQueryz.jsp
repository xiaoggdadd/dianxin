<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.basequery.javabean.PrepaymentQueryBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="java.text.*"%>

<%
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	String dfzflx  = request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";//电费支付类型
    String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"请选择";
    String zdlx1=request.getParameter("zdlx1")!=null?request.getParameter("zdlx1"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginId1 = request.getParameter("loginId");
    String loginName = account.getAccountName();
    int intnum=0;
	String color="";
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
    String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
    //String entrypensonnel1 = request.getParameter("entrypensonnel1")!=null?request.getParameter("entrypensonnel1"):"";//录入人员
    String canshuStr="";
    if((shi!=null)||(xian!=null)||(xiaoqu!=null)||(zdname!=null)){
        canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&zdname="+zdname;
    }
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
#div1{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div1 p{ float:left;font-size:12px; width:110px; cursor:pointer;}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
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
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
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


	function adddegree(){
          document.form1.action=path+"/web/electricfees/addPrepayment.jsp";
                                document.form1.submit();
        }
    function delad(electricfeeId){
       if(confirm("您确定删除此预付费信息？")){
                    document.form1.action=path+"/servlet/prepayment?action=del&degreeid="+electricfeeId
        			document.form1.submit()
       }
    }
    function modifyad(prepayId){
                    //alert(prepayId);
                    document.form1.action=path+"/web/electricfees/modifyPrepayment.jsp?prepayId="+prepayId;
                    document.form1.submit();
       
    }
    
    function queryDegree(){
      if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null)
		{
	                 alert("城市不能为空");
	   
	   }else{
                   document.form1.action=path+"/web/query/basequery/prepaymentQueryz.jsp?command=chaxun";
                   document.form1.submit();
       }
    }
 function getValue(va,sql){
       var general =document.getElementById("general");
       var htmlsql =document.getElementById("htmlsql");
       general.value=va;
       htmlsql.value = sql;    
    }  
 
 function dfinfo5(dbid,dlid){
	 window.open(path+"/web/query/basequery/yfftan.jsp?dbid="+dbid+"&dlid="+dlid,'','width=500,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
  
}	
   //页面载入方法
    function op(){
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian,prepayment&tab=zd,pr&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
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
permissionRights=commBean.getPermissionRight(roleId,"0505");

%>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="20%">
		<tr>
			<td colspan="4" width="50">
												 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">预支充减查询</span>	
												</div>
											</td>
		</tr>
		<tr>
		   <td height="20" colspan="4"><div id="parent" style="display:inline">
               <div style="width:50px;display:inline;"><hr></div><font size="2">过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
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
         			<td ><p><font size="2">
						 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
								<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
								<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
	                      </div></font></p>
					</td>
	         		<td >
				       <div id="query" style="position:relative;width:59px;height:23px;right:-220px;cursor: pointer;TOP:0PX">
							<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
							<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
                        </div>
					</td>					
					</tr></table>
				</td></tr>
   			 <tr>		
   		 		<td colspan="8">
					<div style="width:90%;" ><p id="box3" style="display:none">
						<table>
			   		 		<tr class="form_label">
			   		 			<td>站点名称：</td><td><input type="text" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname"));%>" class="selected_font"/></td>
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
			             </tr>
			   		   </table>
   		 			</p></div>
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
         PrepaymentQueryBean bean = new PrepaymentQueryBean();
          
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" AND ZD.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" AND ZD.XIAN='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" AND ZD.XIAOQU='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
				whereStr=whereStr+" AND ZD.JZNAME LIKE '%"+zdname+"%'";
			}
			if(zdlx1!=null&&!zdlx1.equals("")&& !zdlx1.equals("0")){
			  whereStr=whereStr+" and ZD.STATIONTYPE IN("+zdlx1+")";
			}
			if(dfzflx != null && !dfzflx.equals("") && !dfzflx.equals("0")){
				whereStr=whereStr+" and D.DFZFLX='"+dfzflx+"'";
			}
	 %>
	 
	<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 	
	 			  <table width="1100px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
   	                 <tr height = "23" class="relativeTag ">
                       	<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td> 
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>
                        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">最近一次预付款核销金额</div></td>

                        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">累计预付金额</div> </td>
                        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">累计报账金额</div> </td>
                        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">累计收据金额</div> </td>
                        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">累计发票金额</div> </td>
                        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">最后一次抄表时间</div> </td>
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">余额</div> </td>
                    </tr>
       <%

       	 List<ElectricFeesFormBean> fylist=null;
       	 if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
       	  fylist = bean.getPageDataz(curpage,whereStr,loginId);
       	 allpage=bean.getAllPage();
		String money = "",yfmoney="",cemoney="",prepaymentammeterid = "",szdq="";
		String zdid="",jzname="",zdlxl="",pjjeyf="",pjjedf="",pjje="",thisdatetime="",
		thisactualpay="",dfzflx1="",sjyf="",sjdf="",sjhj="",fphj="";
		int countxh=1;
      	 double money1=0;
		 if(fylist!=null)
		{
			for(ElectricFeesFormBean bean1:fylist){

		     //num为序号，不同页中序号是连续的
		    jzname = bean1.getJzname();//站点名称
		    jzname = jzname != null ? jzname : "";
		     
		    prepaymentammeterid = bean1.getPrepayment_ammeterid();//电表id
		    prepaymentammeterid = prepaymentammeterid != null ? prepaymentammeterid : "";
		    
		    zdid=bean1.getZdcode();//站点id
		    zdid = zdid != null ? zdid : "";		    
		    
		    DecimalFormat mat=new DecimalFormat("0.00");
		    yfmoney = bean1.getMoney();//预付金额
		    yfmoney = yfmoney != null ? yfmoney : "";
		    if(yfmoney==null||yfmoney==""||"".equals(yfmoney)||"null".equals(yfmoney)||"o".equals(yfmoney)){
				yfmoney="0.00";
			}
		    yfmoney=mat.format(Double.parseDouble(yfmoney));  
		    
		    money = bean1.getActualpay();//报账金额
		    money = money != null ? money : "";
		    if(money==null||money==""||"".equals(money)||"null".equals(money)||"o".equals(money)){
				money="0.00";
			}
			money=mat.format(Double.parseDouble(money));
			
		    pjjeyf = bean1.getPjjeyf();//预付费发票金额
		    pjjeyf = pjjeyf != null ? pjjeyf : "0";
		    if(pjjeyf==null||pjjeyf==""||"".equals(pjjeyf)||"null".equals(pjjeyf)||"o".equals(pjjeyf)){
				pjjeyf="0.00";
			}
			pjjeyf=mat.format(Double.parseDouble(pjjeyf));
			
			pjjedf = bean1.getPjjedf();//电费单发票金额
		    pjjedf = pjjedf != null ? pjjedf : "0";
		    if(pjjedf==null||pjjedf==""||"".equals(pjjedf)||"null".equals(pjjedf)||"o".equals(pjjedf)){
				pjjedf="0.00";
			}
			pjjedf=mat.format(Double.parseDouble(pjjedf));
			double fphe=Double.parseDouble(pjjeyf)+Double.parseDouble(pjjedf);
			fphj=fphe+"";
			fphj=mat.format(Double.parseDouble(fphj));
			sjyf=bean1.getSjyf();
			sjyf = sjyf != null ? sjyf : "0";
			sjdf=bean1.getSjdf();
			sjdf = sjdf != null ? sjdf : "0";
			double sjhe=Double.parseDouble(sjyf)+Double.parseDouble(sjdf);
			sjhj=sjhe+"";
			sjhj=mat.format(Double.parseDouble(sjhj));
			//pjje=mat.format(Double.parseDouble(pjjeyf)+Double.parseDouble(pjjedf));//票据金额之和
			
			zdlxl = bean1.getStationtype();//站点类型
		    zdlxl = zdlxl != null ? zdlxl : "";
		    
		    dfzflx1 = bean1.getDfzflx();//电费支付类型
		    dfzflx1 = dfzflx1 != null ? dfzflx1 : "";
		    
		    thisdatetime = bean1.getThisdatetime();//本次抄表时间
		    thisdatetime = thisdatetime != null ? thisdatetime : "";
		    if("null".equals(thisdatetime)||"o".equals(thisdatetime)||"0".equals(thisdatetime)){
				pjjedf="";
			}
			
		    thisactualpay = bean1.getThisactualpay();//本次报账金额
		    thisactualpay = thisactualpay != null ? thisactualpay : "";
		    if(thisactualpay==null||thisactualpay==""||"".equals(thisactualpay)||"null".equals(thisactualpay)||"o".equals(thisactualpay)){
				thisactualpay="0.00";
			}
			thisactualpay=mat.format(Double.parseDouble(thisactualpay));	
			
			cemoney = bean1.getCe();//预付费金额与报账金额差
		    cemoney = cemoney != null ? cemoney : "";
		    if(cemoney==null||cemoney==""||"".equals(cemoney)||"null".equals(cemoney)||"o".equals(cemoney)){
				cemoney="0.00";
			}
			cemoney=mat.format(Double.parseDouble(cemoney));		
			
			szdq = bean1.getSzdq();//所在地区
		    szdq = szdq != null ? szdq : "";
		    
		    
		    
			if(intnum%2==0){
			    color="#FFFFFF";
			 }else{
			    color="#DDDDDD";
			}
           intnum++;

       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=countxh++%></div>
       		</td>
       		<td>
       			<div align="left" ><a href="javascript:modifyad('<%=zdid%>','<%=prepaymentammeterid%>')" ><%=jzname%></a></div>
       		</td>
       		<td>
       			<div align="left" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdlxl%></div>
       		</td>
       		<td>
       			<div align="right" ><%=dfzflx1%></div>
       		</td>
       		<td>
       			<div align="right" ><%=thisactualpay%></div>
       		</td>  
       		<td>
       			<div align="right" ><%=yfmoney%></div>
       		</td>
       		<td>
       			<div align="right" ><%=money%></div>
       		</td>
       		<td>
       			<div align="right" ><%=sjhj%></div>
       		</td>
       		<td>
       			<div align="right" ><%=fphj%></div>
       		</td>
       		<td>
       			<div align="center" ><%=thisdatetime%></div>
       		</td>
       		<td>
       			<div align="right" ><%=cemoney%></div>
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
        </tr>
        <% }}%>
   	</table>
</div>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
                      <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>
  <tr><td>
  <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
  <input type="hidden" name="zdlx1" id="zdlx1" value=""/>
  <input type="hidden" name="ccz" id="ccz" value="<%=zdlx1%>"/>
  <input type="hidden" value="" id="htmlsql" name="htmlsql" />  
  </td></tr>  
</table>
<table width="100%">
	<tr><td height="400px" width="20%">
		<iframe name="treeMap" width="100%" height="400px" frameborder="0" src="<%=path %>/web/query/basequery/sitepaymentstatisticscentreyf.jsp"></iframe>
	</td>
  		<!-- <span style="width:20px"></span> -->
  	<td width="75%" height="400px"><table width="100%">
  		<tr><td height="200px">
    		<iframe name="treeNodeInfo" width="100%" height="200px" frameborder="0" src="<%=path %>/web/query/basequery/sitepaymentstatisticsrightyf.jsp"></iframe>
		</td></tr>
		<tr><td height="200px">
    		<iframe name="treeNodeInfo1" width="100%" height="200px" frameborder="0" src="<%=path %>/web/query/basequery/sitepaymentstatisticsrightcj.jsp"></iframe>
		</td></tr>
	</table></td></tr>
</table>
</form>
</body>

<script language="javascript">
var path = '<%=path%>';
 function modifyad(zdid,prepaymentammeterid){
    	var b=path+"/web/query/basequery/sitepaymentstatisticscentreyf.jsp?zdid="+zdid+"&";			
			b = b.substring(0,b.length-1);
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();
			
		 var c=path+"/web/query/basequery/sitepaymentstatisticsrightyf.jsp?dbid="+prepaymentammeterid+"&";			
			c = c.substring(0,c.length-1);
			 var a = " <a href="+c+" target='treeNodeInfo' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();
			
		var d=path+"/web/query/basequery/sitepaymentstatisticsrightcj.jsp?dbid="+prepaymentammeterid+"&";			
			d = d.substring(0,d.length-1);
			 var a = " <a href="+d+" target='treeNodeInfo1' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();  
			  
    } 

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
      		document.form1.action=path+"/web/query/basequery/prepaymentQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
      		
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/query/basequery/prepaymentQuery.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/query/basequery/prepaymentQuery.jsp?curpage="+pageno+"<%=canshuStr%>";
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
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
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
              	updateShi(res);                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          	updateQx(res);                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
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
<script type="text/javascript">
   function printad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=whereStr%>";
            window.open(path+"/web/query/basequery/prepaymentquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr,'','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
   }
  		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.zdlx.value='<%=zdlx%>';
		document.form1.zdlx1.value=document.form1.ccz.value;
		document.form1.dfzflx.value='<%=dfzflx%>';
 </script>
</html>
