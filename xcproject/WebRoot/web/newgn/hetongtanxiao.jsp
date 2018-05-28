<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*" %>
<%@ page import="com.noki.electricfees.javabean.*" %>
<%@ page import="com.noki.function.*" %>
<%@ page import="java.util.ArrayList,java.util.List" %>
<%
	
    
	String sheng = (String)session.getAttribute("accountSheng");
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
    String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
    String zdname = request.getParameter("zdmc")!=null?request.getParameter("zdmc"):"";//站点名称
    String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";//起始月份
    String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";//站点类型
    String htbh = request.getParameter("htbh")!=null?request.getParameter("htbh"):"";//合同编号
    String path = request.getContextPath();
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String roleId = account.getRoleId();
    String loginId  = account.getAccountId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
      String whereStr="";
       String str="";
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
			height:20px

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
};
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
 <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script >

var oCalendarEnny=new PopupCalendarny("oCalendarEnny");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();


var oCalendarChsny=new PopupCalendarny("oCalendarChsny");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChsny.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChsny.oBtnTodayTitle="确定";
oCalendarChsny.oBtnCancelTitle="取消";
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
		var bt = document.form1.beginTime.value;
		
		if(bt == ""){
			alert("请选择查询月份！");
			return;
		}
		
		document.form1.action=path+"/web/newgn/hetongtanxiao.jsp";
		document.form1.submit();
	}
  	$(function(){
		
		$("#query").click(function(){
			chaxun();
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
<body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="11" width="50" >
                <div style="width:700px;height:50px">
                <br><img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:30px;color:black">合同摊销明细</span>
	              </div></td>
			</tr>
			
			<tr class="form_label">
				<td colspan="11">
				
                       <div id="parent" style="display:inline">
                          </div><div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                    
                      </td>
            </tr>

     <tr>
     <td>
     <table>
     
     
     
     
       <tr class="form_label">
          <td>城市：</td>
          <td><select name="shi" id="shi" style="width:130" onchange="changeShi()" >
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
         	</select><span class="style1">&nbsp;*</span>
         	</td>
         		<td>起始月份：</td>
          			<td>
          				<input type="text" name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form" style="width:130px"/>
          				<span class="style1">&nbsp;*</span></td>
          			   
         	<td  class="form_label">	
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p>
					</td>
         <td>
        <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:50%;TOP:0PX">
		 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
		 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		</div>
		</td>
      </tr> 
      </table>
     </td>
    </tr>     
      <tr>
      	<td>
          <div style="width:99%;" > 
			<p id="box3" style="display:none">
			  <table>
      			<tr class="form_label">
      			 <td>  区、县：</td>
         	
         	<td><select name="xian" id="xian" style="width:130" onchange="changeXian()">
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
         	</select>
         	</td>
         	<td>乡镇：</td>
         	<td><select name="xiaoqu" id="xiaoqu" style="width:130">
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
         	</select>
         </td>
          		
      				<td>站点类型：</td>
      				<td><select name="zdlx" class="selected_font" > 
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
  
      			 </tr>
     			 <tr  class="form_label">
              		<td>站点名称：</td>
        			<td>
          				<input type="text" name="zdmc" value="<%if(null!=request.getParameter("zdmc")) out.print(request.getParameter("zdmc")); %>" style="width:130px"/></td>
    
      	    		<td>合同编号：</td>      
        			<td>
          			<input type="text" name="htbh" value="<%if(null!=request.getParameter("htbh")) out.print(request.getParameter("htbh")); %>" style="width:130px"/></td>
      			
        	 </tr>
       		   </table>
	         </p>
	       </div>
	     </td>
       </tr>
  </table>
<div id="parent" style="display:inline" class="form_label"><br>
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>
 
 <div style="width:100%;height:320px;overflow-x:auto;overflow-y:auto;border:1px" >
 	
  <table width="1100px" height="40%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
  	 <tr class="relativeTag" >
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">ID</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">所在地区</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点名称</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电费支付类型</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">起始月份</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">结束月份</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">合同总金额</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">本次摊销金额</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">摊销余额</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">累计付费</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">累计摊销金额</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">累计收据金额</div></td>
              <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">累计发票金额</div></td>
  	
      
        
       <%
      // String whereStr="";
       //String str="";
       String where="";
       String wh="";
       String pd="";//前台页面做判断有的条件
       if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" AND Z.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" AND Z.XIAN='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" AND Z.XIAOQU='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("")){
				whereStr=whereStr+" AND Z.JZNAME LIKE '%"+zdname+"%'";//startmonth   t.endmonth
			}
			if(zdlx != null && !zdlx.equals("") && !zdlx.equals("0")){
				whereStr=whereStr+" AND Z.STATIONTYPE='"+zdlx+"'";
			}
			if(beginTime != null && !beginTime.equals("") && !beginTime.equals("0")){
				where=where+" AND to_char(PP.STARTMONTH,'yyyy-mm')='"+beginTime+"'";
				str=str+" AND to_char(PP.ENDMONTH,'yyyy-mm')<='"+beginTime+"'";
				//pd=pd+" AND PP.STARTMONTH<='"+beginTime+"'"; 
				//pd=pd+" AND PP.ENDMONTH>='"+beginTime+"'"; 
				pd=pd+" AND ((to_char(PP.STARTMONTH,'yyyy-mm')<='"+beginTime+"' AND to_char(PP.ENDMONTH,'yyyy-mm')>='"+beginTime+"') OR to_char(pp.accountmonth,'yyyy-mm')>='"+beginTime+"')";
	
				
			}
			if(htbh != null && !htbh.equals("")){
				whereStr=whereStr+" AND P.HTBH='"+htbh+"'";
				where=where+" AND PP.HTBH='"+htbh+"'";
				str=str+" AND PP.HTBH='"+htbh+"'";
				wh=wh+" AND PP.HTBH='"+htbh+"'";
				pd=pd+" AND PP.HTBH='"+htbh+"'";
			}
	
			System.out.println("1122"+whereStr);
			
			
	     DanZaiPin be=new DanZaiPin();	
       	 List<CityQueryBean> fylist = null;
       	 if(beginTime != null && !beginTime.equals("") && !beginTime.equals("0")){
         fylist = be.getHtTanXiao(whereStr,str,where,wh,loginId,pd);
		 String jzname = "",szdq="",dfzflx1="",txje="0",qsyf="",jsyf="",htzje="0",txye="0",
		 ljff="0",ljtxje="0",ljsj="0",ljfp="0",fp="0",bfp="0",ksyf1="",jsyf1="",bzyf="",je="0",zje="0";
		 java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM"); //日期格式类型初始化
		 int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null){
             for (CityQueryBean beans:fylist) {   
              je="0";//初始化
			  txye="0";
			  ljtxje="0";
		     //num为序号，不同页中序号是连续的
			jzname = beans.getJzname();
			szdq =  beans.getAddress();
			dfzflx1 =  beans.getDfzflx();
			txje =  beans.getTxje();
			qsyf=beans.getQsyf();//最小起始月份
			jsyf=beans.getJsyf();//最大结束月份
			htzje=beans.getHtzje();
			ljff=beans.getLjff();
			//ljtxje=beans.getLjtxje();
			ljsj=beans.getLjsj();
			fp=beans.getLjfp();
			bfp=beans.getBfp();
			ksyf1=beans.getKsyf1();//本次摊销的起始月份  做判断
			jsyf1=beans.getJsyf1();//本次摊销的结束月份  做判断
			bzyf=beans.getBzyf();//本次摊销的报账月份        做判断
			zje=beans.getBchtzje();//本次合同总金额
			
			
			
		     if("".equals(txje)||txje==null)txje="0";
		     if("".equals(htzje)||htzje==null)htzje="0";
		     if("".equals(ljff)||ljff==null)ljff="0";
		     if("".equals(ljsj)||htzje==null)ljsj="0";
		     if("".equals(fp)||fp==null)fp="0";
		     if("".equals(bfp)||bfp==null)bfp="0";
		     if("".equals(zje)||zje==null)zje="0";
		     
			DecimalFormat mat=new DecimalFormat("0.00");
			ljff=mat.format(Double.parseDouble(ljff));//累计付费
			fp=mat.format(Double.parseDouble(fp));//发票金额
			bfp=mat.format(Double.parseDouble(bfp));//补发票金额
			
			double fp1=Double.parseDouble(fp)+Double.parseDouble(bfp);
			ljfp=fp1+"";
			htzje=mat.format(Double.parseDouble(htzje));//合同总金额
			txje=mat.format(Double.parseDouble(txje));//本次摊销金额
			ljfp=mat.format(Double.parseDouble(ljfp));//累计发票金额
			ljsj=mat.format(Double.parseDouble(ljsj));//累计收据金额
		
			//判断
			//转换为日期格式
			if(!"".equals(bzyf)&&!"".equals(ksyf1)&&!"".equals(jsyf1)){//月份不为空就执行下面的功能
			Date bzyf1=formatter.parse(bzyf);//报账月份
			Date beginTime1=formatter.parse(beginTime);//本次摊销月份
			Date qsyf1=formatter.parse(ksyf1);//本次合同起始月份
			Date jsyf2=formatter.parse(jsyf1);//本次合同结束月份
			int month = bzyf1.getYear()*12+bzyf1.getMonth()-qsyf1.getYear()*12-qsyf1.getMonth()+1;//相差几个月  报账月份-起始月份
			int db=beginTime1.getYear()*12+beginTime1.getMonth()-bzyf1.getYear()*12-bzyf1.getMonth();//摊销金额的判断条件(查询月份减报账月份)
			int tt=beginTime1.getYear()*12+beginTime1.getMonth()-qsyf1.getYear()*12-qsyf1.getMonth();//摊销余额乘的数     查询月份-起始月份
			  String dd="0";//判断数据显示的标志位
	        //第一种情况  报账月份在 起始月份和结束月份之间
	        if(qsyf1.getTime()<=bzyf1.getTime()&&bzyf1.getTime()<=jsyf2.getTime()){
	        	if(beginTime1.getTime()<bzyf1.getTime()){  //查询月份小于报账月份  //不显示 待处理？？？？
	        	  	dd="1";//判断显示不显示标志位
	        	 }else if (beginTime1.getTime()==bzyf1.getTime()){//查询月份等于报账月份
	        	 			if(beginTime1.getTime()==jsyf2.getTime()){
	        	 	  			je=zje;//摊销金额
	        	 	  			txye="0";//摊销余额
	        	 			}else{
	        	 				double tx=Double.valueOf(txje)*month;//摊销金额  为 每月平均摊销金额乘以报账月份之前有几个月
	        	 				double yee=Double.parseDouble(zje)-tx;
			           			txye=yee+"";//摊销余额
			           			je=tx+"";//本次摊销金额
	        	 	        }
	        	 
	        	 }else if(beginTime1.getTime()>bzyf1.getTime()){//查询月份大于报账月份
	        	      je=txje;//本次摊销金额
	        	      System.out.println("33331");
				      double ye=Double.parseDouble(zje)-Double.valueOf(txje)-Double.parseDouble(txje)*tt;//总金额-本次摊销-前几个月的摊销
				      System.out.println(Double.parseDouble(zje)+"qqq"+Double.valueOf(txje)+"eee"+Double.parseDouble(txje)*tt);
				      txye=ye+"";//摊销余额
	        	 
	        	 }else{
	        	 	dd="5";
	        	 }
			
			}else if(bzyf1.getTime()>jsyf2.getTime()){  //第二种情况  报账月份大于 起始月份和结束月份   (有可能查到不需要的数据？？？)
						if(beginTime1.getTime()<bzyf1.getTime()){//不显示 带处理
							dd="2";
						}else if (beginTime1.getTime()==bzyf1.getTime()){
							    je=zje;//摊销金额
	        	 	  			txye="0";//摊销余额
						}else if(beginTime1.getTime()>bzyf1.getTime()){//不显示 带处理
							dd="3";	
						}else{
							dd="6";
						}
			
			}else if(bzyf1.getTime()<qsyf1.getTime()){//第三种情况  报账月份小于起始结束月份
						if(beginTime1.getTime()<bzyf1.getTime()){//查不到 待处理
							dd="4";	
						}else if(bzyf1.getTime()<=beginTime1.getTime()&&beginTime1.getTime()<qsyf1.getTime()){
						        je="0";//摊销金额
	        	 	  			txye=zje;//摊销余额
						}else if (qsyf1.getTime()<=beginTime1.getTime()&&beginTime1.getTime()<=jsyf2.getTime()){
								je=txje;//摊销金额
							 	double ye=Double.parseDouble(zje)-Double.valueOf(txje)-Double.parseDouble(txje)*tt;//总金额-本次摊销-前几个月的摊销
	        	 	  			txye=zje;//摊销余额
						}else{
							dd="7";
						}
			
			}
			if("0".equals(dd)){
	        double qq=Double.parseDouble(ljff)-Double.parseDouble(txye);
	           ljtxje=qq+"";//累计摊销金额	
	        }
		}
		
		
		
		
		
		
		
			
		
		je=mat.format(Double.parseDouble(je));//本次摊销金额
		txye=mat.format(Double.parseDouble(txye));//摊销余额
		ljtxje=mat.format(Double.parseDouble(ljtxje));//累计摊销金额	
	
			String color=null;

			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		<td>
       			<div align="center" ><%=intnum++%></div>
       		</td>
       		<td>
       			<div align="center" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dfzflx1%></div>
       		</td>
       	
         	<td>
       			<div align="center" ><%=qsyf%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jsyf%></div>
       		</td>
       		<td>
       			<div align="center" ><%=htzje%></div>
       		</td>
       		<td>
       			<div align="center" ><%=je%></div>
       		</td>
       			<td>
       			<div align="center" ><%=txye%></div>
       		</td>
       			<td>
       			<div align="center" ><%=ljff%></div>
       		</td>
       		<td>
       			<div align="center" ><%=ljtxje%></div>
       		</td>
       			<td>
       			<div align="center" ><%=ljsj%></div>
       		</td>
       			<td>
       			<div align="center" ><%=ljfp%></div>
       		</td>
       		
       		

       </tr>
       <%} %>

       <%}%>
<%}%>
  
  	 </table> 
  	 </div>
  	 <div id="parent" style="display:inline" class="form_label">
  	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  	   <tr>
		  <td>
        <div id="daochu" style="position:relative;width:59px;height:23px;cursor: pointer;left:85%;TOP:5PX">
		 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
		 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">导出</span>
		</div>
		</td>
    </tr>
    </table>
    </div>
</form>
</body>
</html>
<script language="javascript">
	var path = '<%=path%>';
	$(function(){
		$("#daochu").click(function(){
			daoc();
		});
		
	});
	function daoc(){
			var whereStr="<%=whereStr%>";
			var where="<%=where%>";
			var str="<%=str%>";
			var wh="<%=wh%>";
			var pd="<%=pd%>";
			var beginTime="<%=beginTime%>";
			//alert(whereStr+"---"+where+"---"+str+"---"+wh);
			document.form1.action=path+"/web/newgn/合同摊销明细导出.jsp?where="+where+"&str="+str+"&wh="+wh+"&pd="+pd+"&beginTime="+beginTime+"&whereStr="+whereStr;
		    document.form1.submit();
        }

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
		
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.zdlx.value='<%=zdlx%>';
		
		
			
     </script>

