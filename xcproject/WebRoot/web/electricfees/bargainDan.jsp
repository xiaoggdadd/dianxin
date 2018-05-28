<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%
	String bzyf = request.getParameter("bzyf") != null ? request.getParameter("bzyf") : "";//报账月份
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());      
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";	
	//添加站点类型
    String stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";	
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    String color=null;
    String bargainid="";
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
	String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
    String beginTimeQ = request.getParameter("startmonth")!=null?request.getParameter("startmonth"):"";
    String endTimeQ = request.getParameter("endmonth")!=null?request.getParameter("endmonth"):"";
         
           String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((beginTime!=null)&&(endTime!=null))||(stationtype1!=null)||(zdname!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&startmonth="+beginTimeQ+"&endmonth="+endTimeQ+"&stationtype1="+stationtype1+"&zdname="+zdname;
     }
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
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
.bttcn{color:BLACK;font-weight:bold;}
.form    {width:130px}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
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
                      if(confirm("确定要删除，不可恢复！")){
                 	  document.form1.action=path+"/servlet/log?delBeginTime="+beginTime+"&delEndTime="+endTime
                           document.form1.submit()
               		 }
                }

	}
	function adddegree(){
          document.form1.action=path+"/web/electricfees/bargainDanInput.jsp";
                                document.form1.submit();
                                  showdiv("请稍等..............");
        }
	
	
    function delad(id){
       if(confirm("您确定删除此合同单信息？")){
                    document.form1.action=path+"/servlet/bargain?action=delete&deladfee=deladfee&id="+id;
        			document.form1.submit();
        			showdiv("请稍等..............");
       }
    }
    function modifyad(id){
                    document.form1.action=path+"/web/electricfees/modifyBargain.jsp?id="+id;
                    document.form1.submit();
                     showdiv("请稍等..............");
       
    }
    
    function queryDegree(){
                   document.form1.action=path+"/web/electricfees/bargainDan.jsp";
                   document.form1.submit();
                   showdiv("请稍等..............");
    }
      //批量导入
  function exportFData(){
          document.form1.action=path+"/web/electricfees/inputcontsht.jsp";
                                document.form1.submit();
  }
  $(function(){
		$("#daoru").click(function(){
			exportFData();
		});
		$("#zengjia").click(function(){
			adddegree();
	
		});
		$("#dayin").click(function(){
			dayin();
		});
		$("#chaxun").click(function(){
			queryDegree();
		});
	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0302");


%>
<body  class="body">
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr >
			   <td colspan="4" width="50" >
					<div style="width:700px;height:50px">
						
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">合同单管理</span>	
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
		    		<td >城市： </td>
		    		<td>          
                      <select name="shi" class="selected_font" onchange="changeCity()">
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
            <td>区县：</td>
            <td><select name="xian" class="selected_font" onchange="changeCountry()">
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
         	</select> </td>
         	<td>乡镇：</td>
         	<td>
         	  <select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
         	<td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p>
				</td>
	         	<td >
                                  <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0||true){//测试用的true %>   
                                  <div id="chaxun" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
		                          <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                          <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
		                          </div>
                                  <%}%>
		         </td>  
							
							</tr>
					</table>
					</td>
			</tr>
			<tr>
			  <td colspan="5">
					<div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table>
					<tr class="form_label">
					<td>站点名称：</td>
					<td>
					   <input type="text" name="zdname" class="selected_font" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" />
					</td>
					<td>起始时间：</td>
					<td>
					   <input type="text" class="selected_font" name="startmonth" value="<%if(null!=request.getParameter("startmonth")) out.print(request.getParameter("startmonth")); %>" onFocus="getDatenyString(this,oCalendarChsny)" />
					</td>
					<td>结束时间：</td>
					<td>
					   <input type="text" class="selected_font" name="endmonth" value="<%if(null!=request.getParameter("endmonth")) out.print(request.getParameter("endmonth")); %>" onFocus="getDatenyString(this,oCalendarChsny)" />
					</td>
				    <td>站点类型：</td>
				    <td>
				      <select name="stationtype" class="selected_font" onchange="kzinfo()">
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
			</select></td></tr>
			<tr class="form_label">
			<td>报账月份:</td><td><input type="text" name="bzyf" value="<%if (null != request.getParameter("bzyf")) out.print(request.getParameter("bzyf"));%>" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font" /></td>
			</tr></table></p></div></td></tr></table>
			<table  height="23">
               <tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
            </table>
					<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
				      <table width="100%" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                  		<tr height = "10" class="relativeTag"> 
                          <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
                         <td width="20%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                         <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
            			 <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>
                         <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">金额</div></td>
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始时间</div></td>
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束时间</div></td>
                          <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
                         <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点编号</div></td>
                         <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表编号</div></td>
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">合同编号</div></td>                          
                         <td width="7%" height="23" bgcolor="#DDDDDD" colspan="2"><div align="center" class="bttcn">操作</div></td>
                  </tr>  
                  <%
         
         String whereStr = "";
          
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and zd.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and zd.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" and zd.xiaoqu='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
				whereStr=whereStr+" and zd.jzname like '%"+zdname+"%'";
			}			
			if(beginTimeQ != null && beginTimeQ != "" && !beginTimeQ.equals("0")){
				whereStr=whereStr+" and to_char(p.startmonth,'yyyy-mm')>='"+beginTimeQ+"'";
			}
			if(endTimeQ != null && endTimeQ != "" && !endTimeQ.equals("0")){
				whereStr=whereStr+" and to_char(p.endmonth,'yyyy-mm')<='"+endTimeQ+"'";
			}
			if(bzyf != null && bzyf != ""){
						whereStr=whereStr+" and to_char(p.ACCOUNTMONTH,'yyyy-mm') ='"+bzyf+"'"; 
					}
			
			//站点类型
			if(stationtype1 != null && !stationtype1.equals("") && !stationtype1.equals("0")){
				whereStr=whereStr+" and zd.STATIONTYPE='"+stationtype1+"'";
			}
		//}		

         ElectricFeesBean bean = new ElectricFeesBean();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getBargainDan(curpage,whereStr,loginId);
       	 allpage=bean.getAllPage();
       	 
       	 String stationid = "",jzcode = "",accountmonth="",htdbh,manualauditstatus = "",jzname="",money="",prepayment_ammeterid="",dbname="",startmonth="",endmonth="",id="",stationtype2="";
		intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		Double dianfei=0.0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
              linenum++;
		     //num为序号，不同页中序号是连续的
		    jzcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));	
		    jzcode = jzcode != null ? jzcode : "";
		    
		    
		    
		    htdbh = (String)fylist.get(k+fylist.indexOf("HTBH"));	
		    htdbh = htdbh != null ? htdbh : "";
		    
		    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));	
		    jzname = jzname != null ? jzname : "";
		    
		    id = (String)fylist.get(k+fylist.indexOf("ID"));	
		    id = id != null ? id : "";
		    
		    money = (String)fylist.get(k+fylist.indexOf("MONEY"));	
		    money = money != null ? money : "";
		     
		    prepayment_ammeterid = (String)fylist.get(k+fylist.indexOf("PREPAYMENT_AMMETERID"));	
		    prepayment_ammeterid = prepayment_ammeterid != null ? prepayment_ammeterid : "";
		    	
		    dbname = (String)fylist.get(k+fylist.indexOf("DBNAME"));	
		    dbname = dbname != null ? dbname : "";
		    
		    accountmonth = (String)fylist.get(k+fylist.indexOf("ACCOUNTMONTH"));	
		    accountmonth = accountmonth != null ? accountmonth : "";
		    
		    //添加站点类型
			stationtype2 = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
			stationtype2 = stationtype2 != null ? stationtype2 : "";
		    
		    startmonth = (String)fylist.get(k+fylist.indexOf("STARTMONTH"));	
		    startmonth = startmonth != null ? startmonth : "";
		    
		    endmonth = (String)fylist.get(k+fylist.indexOf("ENDMONTH"));	
		    endmonth = endmonth != null ? endmonth : "";
			
			if(money==null||money.equals("")||money.equals("null")||money.equals("o")){
				money="0.00";
            }	
            DecimalFormat mat =new DecimalFormat("0.00");           
	        money=mat.format(Double.parseDouble(money));
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF";
			}
          intnum++;

       %>
          <tr bgcolor="<%=color%>">
             <td>
       			<div align="center" ><%=intnum-1%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=stationtype2%></div>
       		</td>       		
       		
       		<td>
       			<div align="left" ><%=dbname%></div>
       		</td>          
            <td>
       			<div align="right" ><%=money%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=startmonth%></div>
       		</td>
       		<td>
       			<div align="center" ><%=endmonth%></div>
       		</td>
       		<td>
       			<div align="center" ><%=accountmonth%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzcode%></div>
       		</td>
       		<td>
       			<div align="left" ><%=prepayment_ammeterid%></div>
       		</td>
       		<td>
       			<div align="left" ><%=htdbh%></div>
       		</td>
       		<td>
       			<div align="center" ><a href="javascript:modifyad('<%=id%>')" >修改</a></div>
       		</td>
       		<td>
       		  <%if(permissionRights.indexOf("PERMISSION_DELETE")>=0){%>
       			<div align="center" ><a href="javascript:delad('<%=id%>')" >删除</a></div>
       		  <%}%>
       		</td>                   		
            <%}%>
       </tr>
     
       
       <% if (intnum==0){
    	  
         for(int i=0;i<15;i++){
          if(i%2==0){
			    color="#DDDDDD";
          }else{
			    color="#FFFFFF" ;
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
        </tr> 
        
      <% }}else if(!(intnum > 15)){
    	  for(int i=((intnum-1)%15);i<15;i++){
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
        </tr>
        <% }}%>  
				  <%}%>
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
   			 <%if(permissionRights.indexOf("PERMISSION_ADD")>=0){%> 	
                                      							
				<div id="daoru" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px; ">
		         <img alt="" src="<%=path %>/images/daoru.png" width="100%" height="100%" />
		         <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导入</span>
				</div>
               <%}%> 
              <%if(permissionRights.indexOf("PERMISSION_ADD")>=0){%>    
                               <div id="zengjia" style="position:relative;width:60px;height:23px;cursor: pointer;right:6px;float:right;">
		                            <img alt="" src="<%=path %>/images/xinzeng.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">增加</span>
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
                <input type="hidden" name="id" id="id" value="<%=id%>"/>
              </td>
           </tr>  
           </table></div>
          </form>
         </body>
 <!--  <script language="javascript">
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
      		document.form1.action=path+"/web/electricfees/bargainDan.jsp?curpage="+curpage+"<%=canshuStr%>";
            document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/electricfees/bargainDan.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/electricfees/bargainDan.jsp?curpage="+pageno+"<%=canshuStr%>";
      document.form1.submit();
     }
</script> -->
<script type="text/javascript">
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
</script>
<script language="javascript">
var path='<%=path%>';
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
   
   function exportModel(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=whereStr%>";
            //alert(canshuStr);
        	//document.form1.action=path+"/web/electricfees/电费批量导入模板.jsp?curpage="+curpage+"&whereStr="+whereStr;
            document.form1.action=path+"/servlet/download?action=download&templetname="+encodeURIComponent("电量电费批量导入模板");
            document.form1.submit();
   }
  
   
   
   		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.stationtype.value='<%=stationtype1%>';
 </script>
</html>
