<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.Calendar"%>
<%  
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    String loginId1 = request.getParameter("loginId");
    String beginTime1 = request.getParameter("beginTime1") != null ? request
			.getParameter("beginTime1")
			: "";
	String endTime1 = request.getParameter("endTime1") != null ? request
			.getParameter("endTime1") : "";
	String dbmc = request.getParameter("dbmc")!=null?request.getParameter("dbmc"):"";
	String rgshzt = request.getParameter("rgshzt")!=null?request.getParameter("rgshzt"):"2";

    String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";    
    //String roleId = account.getRoleId();
    //添加站点类型
    String stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
    
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
     String beginTimeQ = request.getParameter("beginTimeQ")!=null?request.getParameter("beginTimeQ"):"";
     String endTimeQ = request.getParameter("endTimeQ")!=null?request.getParameter("endTimeQ"):"";
     String ammeterid = request.getParameter("ammeterid")!=null?request.getParameter("ammeterid"):"";
     String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
     String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((beginTimeQ!=null)&&(endTimeQ!=null))||(ammeterid!=null)||(zdname!=null)||(stationtype1!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&beginTimeQ="+beginTimeQ+"&endTimeQ="+endTimeQ+"&ammeterid="+ammeterid+"&zdname="+zdname+"&stationtype1="+stationtype1;
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
            
           
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}




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
.bttcn{color:BLACK;font-weight:bold;}
 }
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}


 </style>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
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
	
		function payfee(degreeid,ammeterid){
          //document.form1.action=path+"/web/dianliang/addElectricFeesAll.jsp?degreeid="+degreeid+"&ammeterid="+ammeterid;
          document.form1.action=path+"/web/electricfees/addElectricFeesAll.jsp?degreeid="+degreeid+"&ammeterid="+ammeterid;
                                document.form1.submit();
        }
	function adddegree(){
          document.form1.action=path+"/web/dianliang/addDegree.jsp";
                                document.form1.submit();
        }
    function delad(ammeterdegreeid){
       if(confirm("您确定删除此电量信息？")){
                    document.form1.action=path+"/servlet/ammeterdegree?action=del&degreeid="+ammeterdegreeid
        			document.form1.submit()
        			showdiv("请稍等..............");
       }
    }
    function modifyad(ammeterdegreeid){
                    document.form1.action=path+"/web/dianliang/modifyDegree.jsp?degreeid="+ammeterdegreeid;
                    document.form1.submit();
                    showdiv("请稍等..............");
                    
       
    }
    function queryDegree(){
                   document.form1.action=path+"/web/dianliang/dianliangList.jsp?command=chaxun"; 
                   document.form1.submit(); 
       
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
    
  //选择所有按钮
    function chooseAll() {   
       var qm = document.getElementsByName('test');
       //alert(qm[0].checked);  
       var m = document.getElementsByName('test[]');   
       var l = m.length; 
       if(qm[0].checked == true){
         for (var i = 0; i < l; i++) {   
           m[i].checked = true;   
         }  
       }else{
         for (var i = 0; i < l; i++) {   
           //m[i].checked == true ? m[i].checked = false: m[i].checked = true; 
           m[i].checked = false;  
         }   
       }
   }  
  
    
  //点击批量删除按钮
    function deletes(){
       var m = document.getElementsByName('test[]');   
       var l = m.length; 
       var chooseIdStr = ""; 
       var bz=0;
       for (var i = 0; i < l; i++) {  
         if(m[i].checked == true){
            bz=bz+1;
            chooseIdStr = chooseIdStr + m[i].value +","; 
         }               
       } 
       chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
      
       if(bz>0){
       	if(bz<=50){
	           document.form1.action=path+"/servlet/ammeterdegree?action=deletes&chooseIdStr="+chooseIdStr;
	           document.form1.submit();
	           showdiv("请稍等..........");
          }else{
          	   alert("请选择删除的信息不要超过50条！");
          }
       }else{
         alert("请选择信息！");
       }
     }
  
  
    //批量导入
  function exportMData(){
          document.form1.action=path+"/web/dianliang/input.jsp";
                                document.form1.submit();
  }
          $(function(){
			$("#daoru").click(function(){
				exportMData();
				showdiv("请稍等..............");
			});
			$("#zengjia").click(function(){
				adddegree();
		        showdiv("请稍等..............");
			});
			$("#daochu").click(function(){
				exportad();
				
			});
			$("#dayin").click(function(){
				dayinpage('电量管理');
			});
			$("#chaxun").click(function(){
				queryDegree();
			});
			$("#deletes").click(function(){
				deletes();
			});
		});
</script>
<jsp:include page="/web/prePrint.jsp"></jsp:include>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0201");


%>
<body  class="body" >
	<form action="" name="form1" method="POST">
		
			<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="15%">
			        <tr >
			     
					    	<td colspan="4" width="50" >
											 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:33px;color:black">电量管理</span>
						                    </div>
						                  </td>
							
					</tr>	    	
	    	        <tr><td height="15" colspan="4" >
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
                           <td> <select name="shi" class="selected_font" onchange="changeCity()">
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
         	</select></td>
         	  <td>乡镇：</td>   
              <td><select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
							      
		                          <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>    
                                  <div id="chaxun" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
		                          <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                          <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
		                          </div>
                                  <%}%>
					</td>
				</tr>
				</table></td></tr>
				
				
				<tr>
				   <td colspan="5">
					<div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table>
					<tr class="form_label">
					
	         		<td>电表名称：</td>
         	           <td><input type="text" name="dbmc" class="selected_font" value="<%if(null!=request.getParameter("dbmc")) out.print(request.getParameter("dbmc")); %>"/>        
                       </td>
         	        <td>电表ID：</td>
                    <td>
                      <input type="text" name="ammeterid" class="selected_font" value="<%if(null!=request.getParameter("ammeterid")) out.print(request.getParameter("ammeterid")); %>" />
                    </td>
                    <td>上次抄表时间：</td>
                    <td>
                       <input type="text" name="beginTimeQ" class="selected_font" value="<%if(null!=request.getParameter("beginTimeQ")) out.print(request.getParameter("beginTimeQ")); %>" 
								readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
                    </td>
                    <td>本次抄表时间：</td>
                    <td>
                       <input type="text" name="endTimeQ" class="selected_font" value="<%if(null!=request.getParameter("endTimeQ")) out.print(request.getParameter("endTimeQ")); %>" 
                       			readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
                    </td>
                 </tr>
                 <tr class="form_label">
                   <td>人工审核状态：</td>
                   <td><select name="rgshzt" class="selected_font" onchange="javascript:document.form1.rgshzt2.value=document.form1.rgshzt.value">
         		       <option value="2">请选择</option> 
         		       <option value="0">不通过</option> 
	     		       <option value="1">通过</option> 
         	           </select>
         	       </td>
         	       <td> 站点名称：</td>
         	       <td>
         	          <input type="text" class="selected_font" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>"/>
         	       </td>
         	       <td>站点类型: </td>
         	       <td>
         	         <select name="stationtype"  class="selected_font" onchange="kzinfo()">
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
         	       <td>起始月份:</td>
         	       <td>
         	          <input type="text" class="selected_font"  name="beginTime1" value="<%if (null != request.getParameter("beginTime1"))out.print(request.getParameter("beginTime1"));%>"
		 						readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
         	       </td>
         	       </tr>
         	       <tr class="form_label">
         	         <td>结束月份:</td>
         	         <td>
         	           <input type="text" name="endTime1" class="selected_font"
		                 value="<%if (null != request.getParameter("endTime1"))
		                 out.print(request.getParameter("endTime1"));%>"
	                     readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
         	         </td>
         	       </tr></table></p></div></td></tr></table>
                   
           

         
<table  height="15">
<tr><td height="4"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>
<%      String whereStr = "";
		String str ="";
 
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
	if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
		whereStr=whereStr+" and zd.jzname like '%"+zdname+"%'";
	}
	
	if(beginTimeQ != null && beginTimeQ != "" && !beginTimeQ.equals("0")){
		whereStr=whereStr+" AND TO_CHAR(AD.LASTDATETIME,'yyyy-mm-dd')>='"+beginTimeQ+"'";
		str=str+" AND TO_CHAR(AD.LASTDATETIME,'yyyy-mm-dd')>='"+beginTimeQ+"'";
	}
	if(endTimeQ != null && endTimeQ != "" && !endTimeQ.equals("0")){
		whereStr=whereStr+" and TO_CHAR(AD.THISDATETIME,'yyyy-mm-dd')<='"+endTimeQ+"'";
		str=str+" and TO_CHAR(AD.THISDATETIME,'yyyy-mm-dd')<='"+endTimeQ+"'";
	}
	
	
	if(beginTime1 != null && beginTime1 != "" && !beginTime1.equals("0")){
		whereStr=whereStr+" and TO_CHAR(ad.STARTMONTH,'yyyy-mm') ='"+beginTime1+"'";
		str=str+" and TO_CHAR(ad.STARTMONTH,'yyyy-mm') ='"+beginTime1+"'";
	}
	if(endTime1 != null && endTime1 != "" && !endTime1.equals("0")){
		whereStr=whereStr+" and TO_CHAR(AD.ENDMONTH,'yyyy-mm') ='"+endTime1+"'";
		str=str+" and TO_CHAR(AD.ENDMONTH,'yyyy-mm') ='"+endTime1+"'";
	}
	if(ammeterid != null && ammeterid != "" && !ammeterid.equals("0")){
		whereStr=whereStr+" and AMMETERID_FK like '%"+ammeterid+"%'";
	}
	if(dbmc != null && !dbmc.equals("") && !dbmc.equals("0")){
		whereStr=whereStr+" and db.dbname like '%"+dbmc+"%'";
	}
	
	if(rgshzt != null && !rgshzt.equals("") && !rgshzt.equals("2")){
		if(rgshzt.equals("1")){
			whereStr=whereStr+" and ad.MANUALAUDITSTATUS='"+rgshzt+"'";
		}else{
			whereStr=whereStr+" and (ad.MANUALAUDITSTATUS='"+rgshzt+"' or ad.MANUALAUDITSTATUS is null) ";
		}
		str=str+" and ad.MANUALAUDITSTATUS='"+rgshzt+"'";
	}
	//站点类型
	if(stationtype1 != null && !stationtype1.equals("") && !stationtype1.equals("0")){
		whereStr=whereStr+" and zd.STATIONTYPE='"+stationtype1+"'";
		str=str+" and zd.STATIONTYPE='"+stationtype1+"'";
	}
	 

	AmmeterDegreeBean bean = new AmmeterDegreeBean();

	    if(loginId1!=null&&!loginId1.equals("")){
      	     loginId=loginId1;
      	 	
      	 	shi="1";
      	 }
		
	    %>  
	    <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >  	
   	   <table width="100%" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
     	 <tr height = "10" class="relativeTag"> 
     	 <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序列</div></td>
     	 <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
           <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
           <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电流类型</div></td>
           <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电设备</div></td>
           <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
           <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次电表读数</div></td>
		   <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td>
           <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次电表读数</div></td>
           <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
           <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量调整</div></td>
           <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">折算后用电量</div></td>
           <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>
           <td width="10%" height="23" bgcolor="#DDDDDD" colspan="2"><div align="center" class="bttcn">操作</div></td>             
         </tr>
       <%if("chaxun".equals(request.getParameter("command"))){
       
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageData3(whereStr,loginId);
       	 allpage=bean.getAllPage();
		String electriccurrenttype_ammeter = "",usageofelectypeid_ammeter = "",
		ammeterdegreeid = "",ammeterid1 = "",lastdegree = "", thisdegree = "",
		lastdatetime = "",thisdatetime = "",floatdegree = "",actualdegree = "",ammeteruse = "",stationtype2="";
		String feesbz = "",manualauditstatus = "",zdmc="";
		int intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		int xl=1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
              linenum++;
		     //num为序号，不同页中序号是连续的
		     
		    ammeterdegreeid = (String)fylist.get(k+fylist.indexOf("AMMETERDEGREEID"));
		    ammeterdegreeid = ammeterdegreeid != null ? ammeterdegreeid : "";
		    
		    
		    electriccurrenttype_ammeter = (String)fylist.get(k+fylist.indexOf("ELECTRICCURRENTTYPE_AMMETER"));
		    electriccurrenttype_ammeter = electriccurrenttype_ammeter != null ? electriccurrenttype_ammeter : "";
		    
		    usageofelectypeid_ammeter = (String)fylist.get(k+fylist.indexOf("USAGEOFELECTYPEID_AMMETER"));
		    usageofelectypeid_ammeter = usageofelectypeid_ammeter != null ? usageofelectypeid_ammeter : "";
		    
			ammeterid1 = (String)fylist.get(k+fylist.indexOf("AMMETERID_FK"));
			ammeterid1 = ammeterid1 != null ? ammeterid1 : "";
			
			
			zdmc = (String)fylist.get(k+fylist.indexOf("JZNAME"));
			zdmc = zdmc != null ? zdmc : "";
			
			//添加站点类型
			stationtype2 = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
			stationtype2 = stationtype2 != null ? stationtype2 : "";
			
		    lastdegree = (String)fylist.get(k+fylist.indexOf("LASTDEGREE"));
		    lastdegree = lastdegree != null ? lastdegree : "";
		    
		    thisdegree = (String)fylist.get(k+fylist.indexOf("THISDEGREE"));
		    thisdegree = thisdegree != null ? thisdegree : "";
		    
		    lastdatetime = (String)fylist.get(k+fylist.indexOf("LASTDATETIME"));		        
		    if(lastdatetime=="0"||lastdatetime.equals("0")){
		    lastdatetime="";
		    }else{
		    lastdatetime = lastdatetime != null ? lastdatetime : "";
		    }
		    
		    thisdatetime = (String)fylist.get(k+fylist.indexOf("THISDATETIME"));
		    if(thisdatetime=="0"||thisdatetime.equals("0")){
		    thisdatetime="";
		    }else{
			thisdatetime = thisdatetime != null ? thisdatetime : "";
			}			
						
			floatdegree = (String)fylist.get(k+fylist.indexOf("FLOATDEGREE"));
			floatdegree = floatdegree != null ? floatdegree : "";
			
		    actualdegree = (String)fylist.get(k+fylist.indexOf("BLHDL"));
		    actualdegree = actualdegree != null ? actualdegree : "";
		    
		    manualauditstatus = (String)fylist.get(k+fylist.indexOf("MANUALAUDITSTATUS"));
		    manualauditstatus = manualauditstatus != null ? manualauditstatus : "";
		    
		    feesbz = (String)fylist.get(k+fylist.indexOf("FEESBZ"));
		    feesbz = feesbz != null ? feesbz : "";
		    
		    if(floatdegree==null||floatdegree.equals("")||floatdegree.equals("null")) floatdegree="0";
		    DecimalFormat fl=new DecimalFormat("0.0");
            floatdegree = fl.format(Double.parseDouble(floatdegree));
		    
		    if(actualdegree==null||actualdegree.equals("")||actualdegree.equals("null")) actualdegree="0";
		    DecimalFormat ac=new DecimalFormat("0.0");
            actualdegree = ac.format(Double.parseDouble(actualdegree));
            
             if(lastdegree==null||lastdegree.equals("")||lastdegree.equals("null")) lastdegree="0";
		    DecimalFormat la=new DecimalFormat("0.0");
            lastdegree = la.format(Double.parseDouble(lastdegree));
		    
		    if(thisdegree==null||thisdegree.equals("")||thisdegree.equals("null")) thisdegree="0";
		   // DecimalFormat la=new DecimalFormat("0.0");
            thisdegree = la.format(Double.parseDouble(thisdegree));
            if(electriccurrenttype_ammeter==null||electriccurrenttype_ammeter.equals("")||electriccurrenttype_ammeter.equals("null")) electriccurrenttype_ammeter="";

			String color=null;

			if(intnum%2==0){
			    color="#DDDDDD" ;
			 }else{
				 color="#FFFFFF";
			}
           intnum++;
       %>
       <tr bgcolor="<%=color%>">
         	<td>
       			<div align="left" ><%=xl++%></div>
       		</td>
       		<td>
             	<div align="center"><input type="checkbox" name="test[]" value="<%=ammeterdegreeid%>" /></div>
           		</td>
       		<td>
       			<div align="left" ><%=zdmc%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=electriccurrenttype_ammeter%></div>
       		</td>
       		<td>
       			<div align="center" ><%=usageofelectypeid_ammeter%></div>
       		</td>
       		<td>
       			<div align="center" ><%=stationtype2%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lastdegree%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lastdatetime%></div>
       		</td>
       		<td>
       			<div align="center" ><%=thisdegree%></div>
       		</td>

       		<td>
       			<div align="center" ><%=thisdatetime%></div>
       		</td>          
            <td>
       			<div align="center" ><%=floatdegree%></div>
       		</td>
            <td>
       			<div align="center" ><%=actualdegree%></div>
       		</td>
       		<td>
       			<div align="left" ><%=ammeterid1%></div>
       		</td>
       		<td>
       			<div align="center" ><a href="javascript:modifyad('<%=ammeterdegreeid%>')" >修改</a></div>
       		</td>
       		 
       		<td>
       		   <%if(permissionRights.indexOf("PERMISSION_DELETE")>=0){%>
       			<div align="center" ><a href="javascript:delad('<%=ammeterdegreeid%>')" >删除</a></div>
       		   <%}%>
       		</td> 
       </tr>
       <%}
       for(int k=15;k>linenum;k--){%>
       <% String color=null;

			if(intnum%2==0){
			    color="#DDDDDD" ;
			 }else{
				 color="#FFFFFF";
			}
           intnum++;%>
       <tr bgcolor="<%=color %>">
       <td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" >&nbsp;</div>
       		</td>
       		<td>
       			<div align="center"  ></div>
       		</td>
       		<td>
       			<div align="center"  ></div>
       		</td>
       		<td>
       			<div align="center"  ></div>
       		</td>
       		<td>
       			<div align="center"  ></div>
       		</td>
       		<td>
       			<div align="center"  ></div>
       		</td>
       		<td>
       			<div align="center"  ></div>
       		</td>
       		<td>
       			<div align="center"  ></div>
       		</td>
       		<td>
       			<div align="center"  ></div>
       		</td>
       		<td>
       			<div align="center"  ></div>
       		</td>
       	    <td>
       			<div align="center"  ></div>
       		</td>
       		<td>
       			<div align="center"  ></div>
       		</td>
       		<td>
       			<div align="center"  ></div>
       		</td>   
       		<td>
       			<div align="center"  ></div>
       		</td>     
  		
       </tr>
       <%}}}%>
     
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
								<div id="daoru" style="position:relative;width:60px;height:23px;cursor: pointer;right:3px;float:right;">
		                            <img alt="" src="<%=path %>/images/daoru.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">导入</span>
								</div>
                               
                               <%}%> 
                            
                               <%if(permissionRights.indexOf("PERMISSION_ADD")>=0){%>    
                               <div id="zengjia" style="position:relative;width:60px;height:23px;cursor: pointer;right:6px;float:right;">
		                            <img alt="" src="<%=path %>/images/xinzeng.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">增加</span>
								</div>
                              
                               <%}%> 
                               
                               <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                        
                               <div id="daochu" style="position:relative;width:60px;height:23px;cursor: pointer;right:9px;float:right;">
		                            <img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">导出</span>
								</div>
                               <%}%>
                               
                               <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                        
                               <div id="deletes" style="width:76px;height:23px;cursor:pointer;right:11px;float:right;position:relative;">
		                            <img alt="" src="<%=path %>/images/quxiao.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">批量删除</span>
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
                                  <input type="hidden" name="rgshzt2" id="rgshzt2" value=""/>
                               </td>
                               </tr>
                             
   			
                            </table>
                            </div></form></body></html>
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
      		document.form1.action=path+"/web/dianliang/dianliangList.jsp?curpage="+curpage+"<%=canshuStr%>";
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      //alert(parseInt(document.form1.page.value));
      //alert(document.form1.page.value);
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/dianliang/dianliangList.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/dianliang/dianliangList.jsp?curpage="+pageno+"<%=canshuStr%>";
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
	 //alert("11111");
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
function exportad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=str%>";
            var zdname ="<%=zdname%>";
            var ammeterid="<%=ammeterid%>";
            var dbmc ="<%=dbmc%>";
            //alert(canshuStr);
        	document.form1.action=path+"/web/dianliang/电量信息.jsp?curpage="+curpage+"&whereStr="+whereStr+"&zdname="+zdname+"&ammeterid="+ammeterid+"&dbmc="+dbmc;
            document.form1.submit();
   }
   function exportModel(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=whereStr%>";
            //alert(path);
             document.form1.action=path+"/servlet/download?action=download&templetname=电量批量导入模板";
        	//document.form1.action=path+"/servlet/download?action=download&templetname="+encodeURIComponent("电量批量导入模板");
        	//document.form1.action=path+"/web/dianliang/电量批量导入模板.jsp";
            document.form1.submit();
   }
        document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.dbmc.value='<%=dbmc%>';
		document.form1.rgshzt.value='<%=rgshzt%>';
        document.form1.stationtype.value='<%=stationtype1%>'
	
	function dayinpage(title) {
			var column = "all";//打印所有列
//			var column = [0,1,4,6];//定义表格要打印的列，下标从0开始
			var style="<style>table {border-collapse:collapse;border-style:double;border-width:3px;border-color:black;width:700px}</style>";//定义表格样式
			 
			create(title,setColumn(column,style));//创建打印样式
		};
		function setLayout(title,tableHTML){
			LODOP.SET_PRINT_STYLE("FontSize",18);
			LODOP.ADD_PRINT_TEXT(30,280,200,30,title);
			LODOP.ADD_PRINT_HTM(50,50,750,550,tableHTML); 
		}
 </script>
