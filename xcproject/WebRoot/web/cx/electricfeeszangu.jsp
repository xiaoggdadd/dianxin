<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="java.sql.ResultSet"%>

<%
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    
    String color=null;
    int intnum=0;
    
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
    String zdbh = request.getParameter("zdbh");
    String dbid=request.getParameter("dbid");
     //添加站点类型
    String stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
        
     String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||(zdbh!=null)||(dbid!=null)||(stationtype1!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&zdbh="+zdbh+"&dbid="+dbid+"&stationtype1="+stationtype1;
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
</title>
<style>
           
.style1 {
	color: red;
	font-weight: bold;
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.form{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px

		}
.bttcn{color:BLACK;font-weight:bold;}

 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
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
 			document.form1.action=path+"/web/sys/logManage.jsp?beginTime="+beginTime
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
                }
	}
    function queryDegree(){
       if(document.getElementById("beginTimeQ").value=="" || document.getElementById("endTimeQ").value==""){
	       alert("暂估日期不能为空！请填写");
	  }
	   else{
	       document.form1.action=path+"/web/cx/electricfeeszangu.jsp?command=chaxun";
	       document.form1.submit();
	   }
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
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0301");
%>
<body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
	
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		<tr>
		<td colspan="4">
			  <div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电费暂估</span>	
			  </div>
	    </td>
		</tr>		
		<tr><td height="30" colspan="4">
   				<div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	        </td>
	    </tr>
	    <tr><td width="1200">
	    	<table border="0">
	    	<tr class="form">
		    		<td>城市：</td><td><select name="shi" class="form" onchange="changeCity()">
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
         		</select></td>
         		<td>暂估起始时间：</td>
         		<td><input type="text" name="beginTimeQ" value="<%if(null!=request.getParameter("beginTimeQ")) out.print(request.getParameter("beginTimeQ")); %>" onFocus="getDateString(this,oCalendarChs)"  class="form"/><span class="style1">&nbsp;*</span></td>
         		<td> 暂估结束时间：</td>
         		<td><input type="text" name="endTimeQ" value="<%if(null!=request.getParameter("endTimeQ")) out.print(request.getParameter("endTimeQ")); %>" onFocus="getDateString(this,oCalendarChs)"  class="form"/><span class="style1">&nbsp;*</span></td>				
         			<td>
							<p><font size="2">
								 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p></td>
			<td><%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
                <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;right:-220px;float:right;top:0;">
			       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
			       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
				</div>
		        <%}%>				
			</td>
		</tr>
	</table>
	</td></tr>	
 </table>
	
	
	
<div style="width:88%;" > 
	<p id="box3" style="display:none">
		<table>
		  <tr class="form">
		  <td>区县：</td><td> <select name="xian" class="form" onchange="changeCountry()">
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
				<td>乡镇：</td><td><select name="xiaoqu" class="form" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
         <td> 站点名称：<td>
		 <td><input type="text" name="zdbh" value="<%if(null!=request.getParameter("zdbh")) out.print(request.getParameter("zdbh")); %>" class="form"/></td>
		 <td>电表ID：</td>
		 <td><input type="text" name="dbid" value="<%if(null!=request.getParameter("dbid")) out.print(request.getParameter("dbid")); %>" class="form"/></td>
      
         <td>站点类型：</td>
         <td><select name="stationtype" class="form" onchange="kzinfo()">
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
         </tr>
           </table>
		</p>
	</div>	
    <table>
      </tr>
      <tr bgcolor="F9F9F9">
             <td height="19" colspan="4"><div id="parent" style="display:inline">
                 <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
             </div></td>
      </tr>
  </table>
  <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
		<table width="100%" height="80%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
			<tr height = "23" class="relativeTag" bgcolor="#DDDDDD">
						<td width="5%" height="23"><div align="center" class="bttcn" >序号</div></td>                        
                        <td width="12%" height="23"><div align="center" class="bttcn">站点名称</div> </td>                        
            			<td width="9%" height="23"><div align="center" class="bttcn">上次抄表时间</div></td>
                        <td width="9%" height="23"><div align="center" class="bttcn">本次抄表时间</div></td>
                        <td width="8%" height="23"><div align="center" class="bttcn">实际缴费金额</div></td>
                        <td width="7%" height="23"><div align="center" class="bttcn">单价(元/天)</div></td>
                        <td width="8%" height="23"><div align="center" class="bttcn">电费暂估金额</div></td>
                        <td width="12%" height="23"><div align="center" class="bttcn" >所在地区</div></td>
                        <td width="7%" height="23"><div align="center" class="bttcn">站点类型</div> </td>
                        <td width="7%" height="23"><div align="center" class="bttcn">电表名称</div></td> 
                        <td width="7%" height="23"><div align="center" class="bttcn">站点编号</div></td>
                        <td width="6%" height="23"><div align="center" class="bttcn">电表ID</div></td>
                      </tr>
                     		  					  					  
       <%
         String whereStr = "";
         String str = "";
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and zd.shi='"+shi+"'";
				str=str+" and zd.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and zd.xian='"+xian+"'";
				str=str+" and zd.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" and zd.xiaoqu='"+xiaoqu+"'";
				str=str+" and zd.xiaoqu='"+xiaoqu+"'";
			}
			if(zdbh != null && zdbh != "" && !zdbh.equals("0")){
				whereStr=whereStr+" and jzname like '%"+zdbh+"%'";
			}
			if(dbid != null && dbid != "" && !dbid.equals("0")){
				whereStr=whereStr+" and ad.ammeterid_fk like '%"+dbid+"%'";
			}
			//站点类型
			if(stationtype1 != null && !stationtype1.equals("") && !stationtype1.equals("0")){
				whereStr=whereStr+" and zd.STATIONTYPE='"+stationtype1+"'";
				str=str+" and zd.STATIONTYPE='"+stationtype1+"'";
			}
			
	        ElectricFeesBean bean = new ElectricFeesBean();
	       	ArrayList fylist = new ArrayList();
	       	if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
       	    	fylist = bean.getZanguPageData(curpage,whereStr,loginId);
       	    }
       	    allpage=bean.getAllPage();
			String ammeterid_fk = "",lastdatetime = "", thisdatetime = "",actualpay = "",danjia = "",dianfeizangu = "",stationtype2="";
			String jzname = "",jzcode = "",lastdatetime1 = "", thisdatetime1 = "",heji="",dbname="",szdq="";
			// int intnum=xh = pagesize*(curpage-1)+1;
			lastdatetime1=request.getParameter("beginTimeQ");
			thisdatetime1=request.getParameter("endTimeQ");
			int linenum=0;
			int countxh=1;
			double zanguheji = 0;
			 if(fylist.size()!=0)
			{
				int fycount = ((Integer)fylist.get(0)).intValue();
				for( int k = fycount;k<fylist.size()-1;k+=fycount){
	             linenum++;
			     //num为序号，不同页中序号是连续的
			     
			    szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
			    szdq = szdq != null ? szdq : "";
			     
			    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
			    jzname = jzname != null ? jzname : "";
			    
			    //添加站点类型
			    stationtype2 = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
			    stationtype2 = stationtype2 != null ? stationtype2 : "";
			    	
			    jzcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));	
			    jzcode = jzcode != null ? jzcode : "";
			     
			    ammeterid_fk = (String)fylist.get(k+fylist.indexOf("AMMETERID_FK"));	
			    ammeterid_fk = ammeterid_fk != null ? ammeterid_fk : "";
			    
			    dbname = (String)fylist.get(k+fylist.indexOf("DBNAME"));
			    dbname = dbname != null ? dbname : "";
			    
				lastdatetime = (String)fylist.get(k+fylist.indexOf("LASTDATETIMEA"));
				lastdatetime = lastdatetime != null ? lastdatetime : "";
				
			    thisdatetime = (String)fylist.get(k+fylist.indexOf("THISDATETIMEA"));
			    thisdatetime = thisdatetime != null ? thisdatetime : "";			    

			    actualpay = (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));
			    actualpay = actualpay != null ? actualpay : "";
			   
			   
			    if(dianfeizangu==null||dianfeizangu.equals("")||dianfeizangu.equals("null")||dianfeizangu.equals("null")) dianfeizangu="0";
			    if(actualpay==null||actualpay.equals("")||actualpay.equals("null")) actualpay="0";
			    if(danjia==null||danjia.equals("")||danjia.equals("null")) danjia="0";
			    DecimalFormat mat=new DecimalFormat("0.00");
			    
			  if(
			     (!"0".equals(lastdatetime)&&!"".equals(lastdatetime))
			      
			  ){		  
			   Calendar lastTime1 = Calendar.getInstance();//获取Calendar对象
			    lastTime1.setTime(DateFormat.getDateInstance().parse(lastdatetime1));//  parse（）从给定字符串的开始解析文本，以生成一个日期
			    Calendar thisTime1 = Calendar.getInstance();
			    thisTime1.setTime(DateFormat.getDateInstance().parse(thisdatetime1));
			    Long temp1 = thisTime1.getTimeInMillis()-lastTime1.getTimeInMillis();
			    Double day1 = Math.ceil(temp1/1000/60/60/24.0);
			    day1=day1+1.0;
			    Calendar lastTime = Calendar.getInstance();
			    lastTime.setTime(DateFormat.getDateInstance().parse(lastdatetime));
			    Calendar thisTime = Calendar.getInstance();
			    thisTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
			    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
			    Double days = Math.ceil(temp/1000/60/60/24.0);
			    days=days+1.0;
			   // if(heji==null||heji.equals("")) heji="0";
			    Double danjia1 = Double.parseDouble(actualpay)/days;
			   
			    DecimalFormat dj=new DecimalFormat("0.0000");
			    
			    danjia=dj.format(danjia1);
			    
			    actualpay=mat.format(Double.parseDouble(actualpay));
				dianfeizangu=mat.format(Double.parseDouble(danjia)*day1);
				dianfeizangu = dianfeizangu != null ? dianfeizangu : ""; 
			}else{
			lastdatetime="";			
			}
				
				zanguheji += Double.parseDouble(dianfeizangu);
				
				heji=mat.format(zanguheji);
				// String color=null;
	
				if(intnum%2==0){
				    color="#FFFFFF" ;
				 }else{
					color="#DDDDDD"; 
				}
	          intnum++;
	       %>
       <tr bgcolor="<%=color%>" class="form">
       		<td>
       			<div align="center" ><%=countxh++%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>       		
       		<td>
       			<div align="center" ><%=lastdatetime%></div>
       		</td>          
            <td>
       			<div align="center" ><%=thisdatetime%></div>
       		</td>
            <td>
       			<div align="right" ><%=actualpay%></div>
       		</td>
       		<td>
       			<div align="right" ><%=danjia%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=dianfeizangu%></div>
       		</td>
       		<td>
       			<div align="left" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center" ><%=stationtype2%></div>
       		</td>
       		<td>
       			<div align="left" ><%=dbname%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzcode%></div>
       		</td>
       		<td>
       			<div align="left" ><%=ammeterid_fk%></div>
       		</td>
       </tr>
       <%}%>     
       <%}%>
      <% if (intnum==0){
         for(int i=0;i<15;i++){
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
      <% }}else if(!(intnum > 14)){
    	  for(int i=((intnum-1)%14);i<14;i++){
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
        
          <tr bgcolor="#ffffff" onMouseOut="this.style.backgroundColor=''" onMouseOver="this.style.backgroundColor='#BFDFFF'" class="from">
					<td colspan="8" align="center">
					合计
					</td>
					<td align="right"><%=heji%>元</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
				</tr>    
  	 </table> 
  	 </div>
											
<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
  <tr><td>
  <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
  </td></tr>
     <tr bgcolor="F9F9F9">
                      <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                      </div></td>
     </tr>
    			 <tr>
                       <td>
                              <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                        
                               <div id="daochu" style="position:relative;width:60px;height:23px;cursor: pointer;right:9px;float:right;">
		                            <img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">导出</span>
								</div>
                               <%}%> 
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
      		document.form1.action=path+"/web/cx/electricfeeszangu.jsp?curpage="+curpage+"<%=canshuStr%>";
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/cx/electricfeeszangu.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/cx/electricfeeszangu.jsp?curpage="+pageno+"<%=canshuStr%>";
      document.form1.submit();
     }
     </script>
<script type="text/javascript">
<!--
var XMLHttpReq;
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

//添加导出命令
   function exportad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=str%>";
            var lastdatetime1 ="<%=lastdatetime1%>";
            var thisdatetime1 ="<%=thisdatetime1%>";
            var zdbh ="<%=zdbh%>";
            var dbid ="<%=dbid%>";
            
            //alert(canshuStr);
        	document.form1.action=path+"/web/electricfees/电费暂估.jsp?curpage="+curpage+"&whereStr="+whereStr+"&lastdatetime1="+lastdatetime1+"&thisdatetime1="+thisdatetime1+"&zdbh="+zdbh+"&dbid="+dbid+"&command=daochu";
            document.form1.submit();
  }
 
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.stationtype.value='<%=stationtype1%>';
</script>
</html>

