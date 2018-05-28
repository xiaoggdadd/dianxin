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
    //String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";//结束月份
    //String dfzflx = request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";//电费支付类型
    String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";//站点类型
    String htbh = request.getParameter("htbh")!=null?request.getParameter("htbh"):"";//合同编号
    String lrsj = request.getParameter("lrsj")!=null?request.getParameter("lrsj"):"";//录入时间
    
    String path = request.getContextPath();
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String roleId = account.getRoleId();
    String loginId  = account.getAccountId();
    String accountname=account.getAccountName();
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
		var shi = document.form1.shi.value;
		if(shi==""||shi=="0"){
		    alert("请选择城市！");
			return;
		}
		if(bt == ""){
			alert("请选择起始月份！");
			return;
		}
		document.form1.action=path+"/web/newgn/tanxiaochaxun.jsp";
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
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:30px;color:black">摊销静态数据查询</span>
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
         		<td>摊销月份：</td>
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
      			    <td>录入时间：</td>
      			  <td > <input type="text" name="lrsj" class="selected_font" value="<%if (null != request.getParameter("lrsj")) out.print(request.getParameter("lrsj"));%>" onFocus="getDateString(this,oCalendarChs)"  style="width: 130px;"/></td>  
        		 
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
 	
  <table width="1400px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
  	 <tr class="relativeTag" >
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">ID</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点名称</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">所在地区</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电费支付类型</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">合同编号</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">摊销金额</div></td>
  			 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">摊销月份</div></td>
  	 		 <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">录入人</div></td>
      
        
       <%
       String Wst="";//传到下一个页面的条件
       String Str="";//传到下一个页面的条件
     
       if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" AND Z.SHI='"+shi+"'";
				Wst=Wst+" AND Z.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" AND Z.XIAN='"+xian+"'";
				Wst=Wst+" AND Z.XIAN='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" AND Z.XIAOQU='"+xiaoqu+"'";
				Wst=Wst+" AND Z.XIAOQU='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("")){
				whereStr=whereStr+" AND Z.JZNAME LIKE '%"+zdname+"%'";//startmonth   t.endmonth
				Wst=Wst+" AND Z.JZNAME LIKE '%"+zdname+"%'";//
			}
			if(zdlx != null && !zdlx.equals("") && !zdlx.equals("0")){
				whereStr=whereStr+" AND Z.STATIONTYPE='"+zdlx+"'";
				Wst=Wst+" AND Z.STATIONTYPE='"+zdlx+"'";
			}
			if(beginTime != null && !beginTime.equals("") && !beginTime.equals("0")){
				whereStr=whereStr+" AND J.QSYF='"+beginTime+"'";
				str=str+" AND J.QSYF='"+beginTime+"'";
				Str=Str+" AND G.QSYF='"+beginTime+"'";
				
			}
	
			if(htbh != null && !htbh.equals("")){
				whereStr=whereStr+" AND J.HTBH='"+htbh+"'";//合同编号
				str=str+" AND J.HTBH='"+htbh+"'";
				Str=Str+" AND G.HTBH='"+htbh+"'";
				
			}
			if(lrsj != null && !lrsj.equals("")){
				whereStr=whereStr+" AND J.LRSJ LIKE '"+lrsj+"%'";//录入时间
				str=str+" AND J.LRSJ LIKE '"+lrsj+"%'";
				Str=Str+" AND G.LRSJ LIKE '"+lrsj+"%'";
				
			}
		
			
			
			
	     DanZaiPin be=new DanZaiPin();	
       	 List<CityQueryBean> fylist = null;
       	 if(beginTime != null && !beginTime.equals("") && !beginTime.equals("0")){
         fylist = be.getTanXiaoCx(whereStr,str,loginId);
		 String jzname = "",szdq="",dfzflx1="",txje="",htbh1="",qsyf="",jsyf="",txye="",lrren="",heji="";
		 int intnum=xh = pagesize*(curpage-1)+1;
		  List<CityQueryBean> list=new ArrayList<CityQueryBean>();
		double count1=0;
		 if(fylist!=null){
             for (CityQueryBean beans:fylist) { 
              
		     //num为序号，不同页中序号是连续的
			jzname = beans.getJzname();
		
			szdq =  beans.getAddress();
		
			dfzflx1 =  beans.getDfzflx();
		
			txje =  beans.getTxje();
			
			htbh1=beans.getHtbh();
			qsyf=beans.getQsyf();
			jsyf=beans.getJsyf();
		    lrren=beans.getLrren();
			DecimalFormat mat=new DecimalFormat("0.00");
			txje=mat.format(Double.parseDouble(txje));
			count1=count1+Double.valueOf(txje);
			heji=mat.format(count1);
	
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
       			<div align="center" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dfzflx1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=htbh1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=txje%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=qsyf%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lrren%></div>
       		</td>

       </tr>
       <%} %>

       <%}%>
       <tr><td colspan="5" align="center">合计摊销金额</td><td><%=heji%></td></tr>
<%}%>
  
  	 </table> 
  	 </div>
  	 <div id="parent" style="display:inline" class="form_label">
  	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  	   <tr>
  	   		<td>
        		<div id="ftsq" style="position:relative;width:120px;height:23px;cursor: pointer;left:59%;TOP:10PX">
				 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="105%" height="100%" />
		 			<span style="font-size:12px;position: absolute;left:28px;top:3px;color:white">按成本中心分摊</span>
				</div>
        		<div id="fts" style="position:relative;width:100px;height:23px;cursor: pointer;left:78%;TOP:-9PX">
		 		<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
		 		<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">按市分摊</span>
				</div>
			</td>
  	   	
  	   
  	   
  	   
  	  <!--
		  <td>
        <div id="daochu" style="position:relative;width:59px;height:23px;cursor: pointer;left:80%;TOP:-50PX">
		 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
		 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">导出</span>
		</div>
		</td>
    --></tr>
    </table>
    </div>
  
</form>
</body>
</html>
<script language="javascript">
	var path = '<%=path%>';
	$(function(){
	
		$("#daochu").click(function(){
			daochu1();
		});
		$("#ftsq").click(function(){
			ftsq1();
		});
		$("#fts").click(function(){
			fts1();
		});
		
	});
	function ftsq1(){
	       if(document.getElementById("shi").value=="0"||document.getElementById("beginTime").value==""){
	        alert("城市，暂估日期不能为空！请填写");
	       }else{
	        var whereStr="<%=Wst%>";
			var str="<%=Str%>";
			window.open(path+"/web/newgn/shiqufentan.jsp?str="+str+"&whereStr="+whereStr);
			//document.form1.action=path+"/web/newgn/shiqufentan.jsp?str="+str+"&whereStr="+whereStr;
		    //document.form1.submit();
	       }
        }
     function fts1(){
     		 if(document.getElementById("shi").value=="0"||document.getElementById("beginTime").value==""){
	             alert("城市，暂估日期不能为空！请填写");
	         }else{
				var whereStr="<%=Wst%>";
				var str="<%=Str%>";
				window.open(path+"/web/newgn/shifentan.jsp?str="+str+"&whereStr="+whereStr);
				//document.form1.action=path+"/web/newgn/shifentan.jsp?str="+str+"&whereStr="+whereStr;
		    	//document.form1.submit();
		  }
    }
	function daochu1(){
			var whereStr="<%=whereStr%>";
			document.form1.action=path+"/web/newgn/摊销.jsp?whereStr="+whereStr;
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

