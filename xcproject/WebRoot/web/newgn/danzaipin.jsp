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
    String zdtype = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";//站点类型
    String baozhang = request.getParameter("baozhang")!=null?request.getParameter("baozhang"):"";//报账月份
    String luru = request.getParameter("luru")!=null?request.getParameter("luru"):"";//录入月份
    String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";//站点名称
    String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";//起始结束月份
    
    
    String path = request.getContextPath();
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String roleId = account.getRoleId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
%>

<html>
<head>
<title>
logMange
</title>
<style>

            .style1 {
	color: #FF9900;
	font-weight: bold;
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 .form_label {
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height: 23px
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
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
		/*if(bt == ""){
			alert("请选择起始结束时间！");
			return;
		}*/
		if(shi== "0"){
			alert("请选择市！");
			return;
		}
		
		document.form1.action=path+"/web/newgn/danzaipin.jsp";
		document.form1.submit();
	}
	$(function(){
		
		$("#query").click(function(){
			chaxun();
		});
		
	});
</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="<%=path%>/images/css.css" type=text/css rel=stylesheet>
	<form action="" name="form1" method="POST">
	
		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="18%">
			 <tr>
				<td colspan="4" width="50" >
					<div style="width:700px;height:50px">
						
						<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">单载频能耗分析</span>	
					</div>
				</td>

	    	 </tr>
			<tr><td height="20" colspan="4" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td>
	    	</tr>
       
         <tr><td><table>
          <tr >
         	<td >市:</td>
         	<td>
         	<select name="shi" id="shi" style="width:130" onchange="changeShi()">
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
         	</select><span><font color="red">*</font></span>
         	</td>
         	<td>区、县：</td>
         	<td>
         	<select name="xian" id="xian" style="width:130" onchange="changeXian()">
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
         <td>站点名称</td>
         <td><input type="text" id="zdname" name="zdname" style="width:130"/></td>
     
      </tr>
  		 <tr >
         <td >站点类型：</td>
         <td>
         	<select name="stationtype"  onchange="kzinfo()" style="width:130">
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
         	</select>
         </td>
          <td>起始结束月份：</td>
         <td>
         	<input type="text" name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form" style="width: 130px"/></td>
         <td>报账月份：</td>
         <td>
         	<input type="text" name="baozhang" value="<%if(null!=request.getParameter("baozhang")) out.print(request.getParameter("baozhang")); %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form" style="width: 130px"/></td>
     	 <td>录入月份：</td>
         <td>
         	<input type="text" name="luru" value="<%if(null!=request.getParameter("luru")) out.print(request.getParameter("luru")); %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form" style="width: 130px"/></td>
      </tr>
      <tr>
      	 <td>&nbsp;</td>
        <td>
        <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:555%;TOP:-0PX">
		 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
		 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		</div>
		</td>
      </tr>
      </table></td></tr>
  </table>
  
  	
  	<% 
  	String whereStr="";
  	String str="";
  		if(shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr=whereStr+" and Z.shi='"+shi+"'";
			str=str+" and Z.shi='"+shi+"'";
		}
  	    if(xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr=whereStr+" and Z.xian='"+xian+"'";
			str=str+" and Z.xian='"+xian+"'";
		}
		if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
			whereStr=whereStr+" and Z.xiaoqu='"+xiaoqu+"'";
			str=str+" and Z.xiaoqu='"+xiaoqu+"'";
		}
		if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
			whereStr=whereStr+" and Z.jzname like '%"+zdname+"%'";
		}
		if(zdtype != null && !zdtype.equals("") && !zdtype.equals("0")){
			whereStr=whereStr+" and Z.STATIONTYPE='"+zdtype+"'";
		}
		if(luru != null && !luru.equals("") && !luru.equals("0")){
			whereStr=whereStr+" and E.ENTRYTIME like '"+luru+"%'";
			str=str+" and E.ENTRYTIME like '"+luru+"%'";
		}
		if(baozhang != null && !baozhang.equals("") && !baozhang.equals("0")){
			whereStr=whereStr+" and E.ACCOUNTMONTH='"+baozhang+"'";
			str=str+" and E.ACCOUNTMONTH='"+baozhang+"'";
		}
		
		if(beginTime != null && !beginTime.equals("") && !beginTime.equals("0")){
			whereStr=whereStr+" and A.startmonth='"+beginTime+"'";
			str=str+" and A.startmonth='"+beginTime+"'";
		}
  		
  	
  	%>
  	  <table  height="23">
        <tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>

     </table>
  <table  width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">
               
                <tr>
                <td width="5%" height="23" bgcolor="#888888"><div align="center" class="bttcn">序号</div></td>
                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">单位</div></td>
                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">部门</div></td>
                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点类型</div></td>
            	<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">数量</div></td>
                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">用电量</div></td>
            	<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">应付金额</div></td>
				<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">实付数额</div></td>
				<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">载频数</div></td>
				<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">单载频能耗</div></td>
				<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">年化单载频能耗</div></td>
				<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">平均单站载频数</div></td>
				<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">单站能耗</div></td>
				<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">单站能耗（年化）</div></td>
				
            </tr>
       <%
         DanZaiPin bean=new DanZaiPin();
         List<CityQueryBean> fylist = null;
       	 //ArrayList fylist = new ArrayList();
       	 if(shi != null && !shi.equals("") && !shi.equals("0")){
           fylist = bean.getDanzaipin(whereStr,loginId);
         
       	 //allpage=bean.getAllPage();
       	System.out.println("--"+allpage);
		String xian1 = "", xiaoqu1= "",zdtype1="",zdcount="",ydl="",yfjine="",sfjine="",zps="",dzpnh="",nhdzpnh="",pjdzzps="",dznh="",dznhnh="";
		int intnum=xh = pagesize*(curpage-1)+1;
		
		if(fylist!=null){
			//int fycount = ((Integer)fylist.get(0)).intValue();
			//System.out.println(fycount);
			//fycount=(fycount+1)*2-1;
			//for( int k = fycount;k<fylist.size()-1;k+=fycount)
			for (CityQueryBean beans:fylist) {

		     //num为序号，不同页中序号是连续的
		     xian1=beans.getXian();//县
		     xiaoqu1=beans.getXiaoqu();//乡镇
		     zdtype1=beans.getZdtype();//站点类型
		     
		     zdcount=beans.getZdcount();//数量
		     zdcount = zdcount != null ? zdcount : "0";
		     
		     ydl=beans.getYdl();//用电量
		     ydl = ydl != null ? ydl : "0";
		     
		     yfjine=beans.getYfjine();//应付金额
		     yfjine = yfjine != null ? yfjine : "0";
		     
		     sfjine=beans.getSfjine();//实付金额
		     sfjine = sfjine != null ? sfjine : "0";
		     
		     zps=beans.getZaipins();//载频数
		     zps = zps != null ? zps : "0";
			
			if(zps=="0"||"0".equals(zps)){
				dzpnh="0";
			}else{
			double dzp=Double.parseDouble(sfjine)/Double.parseDouble(zps);
			dzpnh=String.valueOf(dzp);//单载频能耗
			}
			nhdzpnh=String.valueOf(Double.parseDouble(dzpnh)/7*12);//年化单载频能耗
			if(zdcount=="0"||"0".equals(zdcount)){
				pjdzzps="0";
			}else{
			 pjdzzps=String.valueOf(Double.parseDouble(zps)/Double.parseDouble(zdcount));//平均单站载频数
			}
			if(zdcount=="0"||"0".equals(zdcount)){
				dznh="0";
			}else{
			dznh=String.valueOf(Double.parseDouble(sfjine)/Double.parseDouble(zdcount));//单站能耗
			}
			dznhnh=String.valueOf(Double.parseDouble(dznh)/7*12);//单站能耗（年化）
			
			DecimalFormat mat=new DecimalFormat("0.00");
			yfjine=mat.format(Double.parseDouble(yfjine));
			sfjine=mat.format(Double.parseDouble(sfjine));
			dzpnh=mat.format(Double.parseDouble(dzpnh));
			nhdzpnh=mat.format(Double.parseDouble(nhdzpnh));
			pjdzzps=mat.format(Double.parseDouble(pjdzzps));
			dznh=mat.format(Double.parseDouble(dznh));
			dznhnh=mat.format(Double.parseDouble(dznhnh));
			
			String color=null;

			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=intnum++%></div>
       		</td>
       		<td>
       			<div align="center" ><%=xian1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=xiaoqu1%></div>
       		</td>
       			<td>
       			<div align="center" ><%=zdtype1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdcount%></div>
       		</td>
       		<td>
       			<div align="center" ><%=ydl%></div>
       		</td>
       		<td>
       			<div align="center" ><%=yfjine%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sfjine%></div>
       		</td>
       			<td>
       			<div align="center" ><%=zps%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dzpnh%></div>
       		</td>
       		<td>
       			<div align="center" ><%=nhdzpnh%></div>
       		</td>
       		<td>
       			<div align="center" ><%=pjdzzps%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dznh%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dznhnh%></div>
       		</td>
       		
       		

       </tr>
       <%} %>

       <%}%>
<%}%>

  	 </table> 


</form>
</body>
</html>
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
      		document.form1.action=path+"/web/electricfees/zhichumingxi.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/electricfees/zhichumingxi.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/electricfees/zhichumingxi.jsp?curpage="+pageno;
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
		document.form1.stationtype.value='<%=zdtype%>';//站点类型
		document.form1.baozhang.value='<%=baozhang%>';//报账月份
		document.form1.luru.value='<%=luru%>';//录入月份
		document.form1.beginTime.value='<%=beginTime%>';//起始结束月份
			
     </script>

