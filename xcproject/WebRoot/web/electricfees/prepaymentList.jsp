<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.PrepaymentBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*" %>

<%
//在函数中使用到了，没在 正文中看到
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
        String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
       //者两个 没看到有用到
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    String loginId1 = request.getParameter("loginId");
    String color=null;
    String sheng = (String)session.getAttribute("accountSheng");
    String agcode1="";
   	if(request.getParameter("shi")==null){
   		ArrayList shilist = new ArrayList();
   		CommonBean commBean = new CommonBean();
   		shilist = commBean.getAgcode(sheng,account.getAccountId());//获取市
		if(shilist!=null){
      		int scount = ((Integer)shilist.get(0)).intValue();
           agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
         }
   	} 
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
	String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
	String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
	String zdl = request.getParameter("zdl")!=null?request.getParameter("zdl"):"0";
	//页面中的开始时间
       String beginTimeQ = request.getParameter("beginTimeQ")!=null?request.getParameter("beginTimeQ"):"";
        if("null".equals(beginTimeQ)){
         beginTimeQ="";//
       }
       
       String endTimeQ = request.getParameter("endTimeQ")!=null?request.getParameter("endTimeQ"):"";
       if("null".equals(endTimeQ)){
         endTimeQ="";
       }
         
         String ammeterid1 = request.getParameter("ammeterid1")!=null?request.getParameter("ammeterid1"):"";
         if("null".equals(ammeterid1)){
           ammeterid1="";
         }
         String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
         
         if("null".equals(zdname)){
           zdname="";
         }
          String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||(zdl!=null)||((beginTimeQ!=null)&&(endTimeQ!=null))||(ammeterid1!=null)||(zdname!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&beginTimeQ="+beginTimeQ+"&endTimeQ="+endTimeQ+"&ammeterid1="+ammeterid1+"&zdname="+zdname+"&zdl="+zdl;
     }
    
    //String roleId = account.getRoleId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    String qyzt = request.getParameter("qyzt")!=null?request.getParameter("qyzt"):"1";//站点启用状态
   
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
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.bttcn{color:BLACK;font-weight:bold;}

 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
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
<script >var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();


var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六")oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init()
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
          document.form1.action=path+"/web/electricfees/addPrepayment.jsp";
                                document.form1.submit();
        }
    function delad(electricfeeId){
       if(confirm("您确定删除此预付费信息？")){
                    document.form1.action=path+"/servlet/prepayment?action=del&degreeid="+electricfeeId
        			document.form1.submit()
        			showdiv("请稍等..............");
       }
    }
    function modifyad(prepayId,stationid){
                    //alert(prepayId);
                    document.form1.action=path+"/web/electricfees/modifyPrepayment.jsp?prepayId="+prepayId+"&stationid="+stationid;
                    document.form1.submit();
                    showdiv("请稍等..............");
       
    }
    function queryDegree(){
                   document.form1.action=path+"/web/electricfees/prepaymentList.jsp?command=chaxun";
                   document.form1.submit();
       
    }
      //批量导入
	  function exportFData(){
	      document.form1.action=path+"/web/electricfees/inputprepay.jsp";
	      document.form1.submit();
	  }
   $(function(){
			$("#daoru").click(function(){
				exportFData();
			});
			$("#zengjia").click(function(){
				adddegree();
		        showdiv("请稍等..............");
			});
			$("#daochu").click(function(){
				exportad();
			});

			$("#chaxun").click(function(){
				queryDegree();
			});
		});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0302");


%>
<body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		<tr>
		<td colspan="4">
			  <div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">预付费管理</span>	
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
	    	<table>
	    	<tr class="form_label">
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
         						
         		<td>区县:</td><td> <select name="xian" class="form" onchange="changeCountry()">
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
			  <tr class="form_label">
         	 	 <td>站点名称：</td>
		         <td><input type="text" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" class="form"/></td>      
		         <td>电表ID：</td>
		         <td><input type="text" name="ammeterid1" value="<%if(null!=request.getParameter("ammeterid1"))out.print(request.getParameter("ammeterid1")); %>" class="form"/> </td>       
		         <td> 上次抄表时间：</td>
		         <td><input type="text" name="beginTimeQ" value="<%if(null!=request.getParameter("beginTimeQ")) out.print(request.getParameter("beginTimeQ")); %>" 
		        			readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="form"/></td>
		         <td>本次抄表时间：</td>
		         <td><input type="text" name="endTimeQ" value="<%if(null!=request.getParameter("endTimeQ")) out.print(request.getParameter("endTimeQ")); %>" 
		         			readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="form"/></td>
	         </tr>
	         <tr class="form_label">
	          <td>站点类型：</td>
         	  <td><select name="zdl" class="form" > 
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
                </select></td>
		        <td>站点启用状态：</td>
                <td>
                   <select name="qyzt" id="zd" class="form" >
         		   		<option value="-1">请选择</option>
         		   		<option value="1">启用</option>
         		        <option value="0">未启用</option>
         	       </select>
         	   </td> 
             </tr>
           </table>
		</p>
	</div>	
    <table>
		 <tr><td height="23" colspan="4"><div id="parent" style="display:inline">
             <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
             </div></td>
        </tr>       	
	</table>
	<% 
    
    String whereStr = "";
    String str="";
   
     
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
		}	if(zdname != null && zdname!="" && !zdname.equals("0")&&!"null".equals(zdname)){
			whereStr=whereStr+" and zd.jzname like '%"+zdname+"%'";
		}
		if(beginTimeQ != null && beginTimeQ != "" && !beginTimeQ.equals("0")&&!"null".equals(beginTimeQ)){
			whereStr=whereStr+" and to_char(pr.startdate,'yyyy-mm-dd')>='"+beginTimeQ+"'";
			str=str+" and to_char(pr.startdate,'yyyy-mm-dd')>='"+beginTimeQ+"'";
		}
		if(endTimeQ != null && endTimeQ != "" && !endTimeQ.equals("0")&&!"null".equals(endTimeQ)){
			whereStr=whereStr+" and to_char(pr.startdate,'yyyy-mm-dd')<='"+endTimeQ+"'";
			str=str+" and to_char(pr.startdate,'yyyy-mm-dd')<='"+endTimeQ+"'";
		}
		if(ammeterid1 != null && ammeterid1 != "" && !ammeterid1.equals("0")&&!"null".equals(ammeterid1)){
			whereStr=whereStr+" and prepayment_ammeterid like '%"+ammeterid1+"%'";
		}if(zdl != null && !zdl.equals("") && !zdl.equals("0")){
			whereStr=whereStr+" and zd.STATIONTYPE='"+zdl+"'";
			str=str+" and zd.STATIONTYPE='"+zdl+"'";
		}
		if (qyzt != null && !qyzt.equals("") && !qyzt.equals("-1")) {
			whereStr = whereStr + " and zd.QYZT='" + qyzt + "'";
			str=str+" and zd.QYZT='"+qyzt+"'";
		}
	//}
	

    PrepaymentBean bean = new PrepaymentBean();
    if("chaxun".equals(request.getParameter("command"))){
    if(loginId1!=null&&!loginId1.equals("")){
  	     loginId=loginId1;
  	 
  	 	shi="1";
  	 }
    
 
	if(shi != null && !shi.equals("") && !shi.equals("0")){
		
	
	String count1=bean.getCountt1(whereStr,loginId);
	String count2=bean.getCountt2(whereStr,loginId);
	if(count2==null){
		count2=0+"";
	}else{
		DecimalFormat s=new DecimalFormat("0.00");
		count2=s.format(Double.parseDouble(count2));
	}
	
	%>
		<table  height="5%">
     <tr>
	        <td><font size="2">总共</font></td><td><font size="2" color="red"><%=count1%></font><font size="2">条  |</font></td>
	         <td><font size="2">总金额共</font></td><td><font size="2" color="red"><%=count2%></font><font size="2">元 </font></td>
	         
	      </tr>
	</table>
	
		<% }}%>
		
  	
  <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
		<table width="100%" height="80%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
			<tr height = "23" class="relativeTag" >
               <td width="3%" bgcolor="#DDDDDD" height="23"><div align="center" class="bttcn">序号</div></td>
                 <td width="19%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                 <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
                 <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">费用类型</div></td>
                 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>
                 <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">金额</div></td>
                 <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始电表数</div></td>
                 <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">终止电表数</div></td>
                 <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际用电量</div></td>
                 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始时间</div></td>
                 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">预计结束时间</div></td>
                 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>
                 <td width="8%" height="23" colspan="2" bgcolor="#DDDDDD"><div align="center" class="bttcn">操作</div></td>
            </tr>
      <% 
      
      if("chaxun".equals(request.getParameter("command"))){
       	 ArrayList fylist = new ArrayList();
       	
       	 fylist = bean.getPageData(curpage,whereStr,loginId);
       	 
       	 
       	 allpage=bean.getAllPage();
		String Id = "",
		feetypeid = "",
		zdname1 = "",
		ammeterdegreeidFk = "", 
		money = "",
		money2="",
		startdegree = "",
		stopdegree = "",
		startdate = "",
		enddate = "",
		dfzflx="",
		prepaymentammeterid = "",
		financeaudit="",
		stationid = "";
		String zdlx="",actualdegree="";
       double money1=0;
		int intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
             linenum++;
		     //num为序号，不同页中序号是连续的
		    Id = (String)fylist.get(k+fylist.indexOf("ID"));	
		    prepaymentammeterid = (String)fylist.get(k+fylist.indexOf("PREPAYMENT_AMMETERID"));	
		    prepaymentammeterid = prepaymentammeterid != null ? prepaymentammeterid : "";
		    
		     zdname1 = (String)fylist.get(k+fylist.indexOf("JZNAME"));
			zdname1 = zdname1 != null ? zdname1 : "";
			
		    feetypeid = (String)fylist.get(k+fylist.indexOf("NAME"));	
		    feetypeid = feetypeid != null ? feetypeid : "";
		    
		   
		    			    
			ammeterdegreeidFk = (String)fylist.get(k+fylist.indexOf("AMMETERDEGREEID_FK"));
			ammeterdegreeidFk = ammeterdegreeidFk != null ? ammeterdegreeidFk : "";
			
		  
		    DecimalFormat mat=new DecimalFormat("0.00");
		    money = (String)fylist.get(k+fylist.indexOf("MONEY"));
		    money = money != null ? money : "";
		    if(money.equals("null")){
			money="0.0";
			}
		    money1=Double.parseDouble(money);
		    money2=mat.format(money1);
		    
		    startdegree = (String)fylist.get(k+fylist.indexOf("STARTDEGREE"));
		    startdegree = startdegree != null ? startdegree : "";
		    
		    stopdegree = (String)fylist.get(k+fylist.indexOf("STOPDEGREE"));
		    stopdegree = stopdegree != null ? stopdegree : "";
		    
		    
		    startdate = (String)fylist.get(k+fylist.indexOf("STARTDATE"));
		    startdate = startdate != null ? startdate : "";
		    
			enddate = (String)fylist.get(k+fylist.indexOf("ENDDATE"));
			enddate = enddate != null ? enddate : "";
			
			stationid = (String)fylist.get(k+fylist.indexOf("STATIONID"));
			stationid = stationid != null ? stationid : "";		
			
			financeaudit=(String)fylist.get(k+fylist.indexOf("FINANCEAUDIT"));
			financeaudit = financeaudit != null ? financeaudit : "";	
			
			
			dfzflx=(String)fylist.get(k+fylist.indexOf("DFZFLX"));
			dfzflx = dfzflx != null ? dfzflx : "";		
			
			zdlx=(String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
			zdlx = zdlx != null ? zdlx : "";	
			
			
			 if(stopdegree==null||stopdegree.equals("")) stopdegree="0";
		     if(stopdegree.equals("null")){
			 stopdegree="0.0";
			 }
		     DecimalFormat s=new DecimalFormat("0.0");
			 stopdegree=s.format(Double.parseDouble(stopdegree));
			 
			 if(startdegree==null||startdegree.equals("")) startdegree="0";
		    // DecimalFormat s=new DecimalFormat("0.0");
		     if(startdegree.equals("null")){
			 startdegree="0.0";
			 }
			 startdegree=s.format(Double.parseDouble(startdegree));
			 
			 actualdegree=(String)fylist.get(k+fylist.indexOf("ACTUALDEGREE"));
			 actualdegree = actualdegree != null ? actualdegree : "";
		     if(actualdegree.equals("null")||actualdegree==null||actualdegree.equals("")){
			 actualdegree="0.0";
			 }
			 actualdegree=s.format(Double.parseDouble(actualdegree));
			

				if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
          intnum++;

       %>
       <tr bgcolor="<%=color%>">
       		
       		<td>
       			<div align="center" ><%=intnum-1%></div>
       		</td>
       		<td>
       			<div align="left" ><%=zdname1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdlx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=feetypeid%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dfzflx%></div>
       		</td>
       		<td>
       			<div align="right" ><%=money2%></div>
       		</td>
       		<!-- 
       		<td>
       			<div align="right" ><%=ammeterdegreeidFk%></div>
       		</td>
       		-->
       		<td>
       			<div align="right" ><%=startdegree%></div>
       		</td>			
       		<td>
       			<div align="right" ><%=stopdegree%></div>
       		</td>          
			<td>
       			<div align="right" ><%=actualdegree%></div>
       		</td>        
            <td>
       			<div align="center" ><%=startdate%></div>
       		</td>
            <td>
       			<div align="center" ><%=enddate%></div>
       		</td>
       		<td>
       			<div align="left" ><%=prepaymentammeterid%></div>
       		</td>
       		
              <%if(financeaudit.equals("2")){  %>
           <td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>  
            <%}else{ %>
       		<td>
       			<div align="center" ><a href="javascript:modifyad('<%=Id%>','<%=stationid%>')" >修改</a></div>
       		</td>
       		<td>
       		  <%if(permissionRights.indexOf("PERMISSION_DELETE")>=0){%>
       			<div align="center" ><a href="javascript:delad('<%=Id%>')" >删除</a></div>
       		  <%} %>
       		</td>                   		
            <%} %>
       </tr>
       <%}
       if (intnum==0){
    	
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
            </tr>
      <% }}else if(!(intnum > 15)){
    	  for(int i=((intnum-1)%15);i<15;i++){
            if(i%2==0)
			    color="#FFFFFF";
            else
			    color="#DDDDDD" ;
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
        </tr>
        <% }}}}%>

  	 </table> 
  	 
  	 </div>
  	 
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
  <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
  </tr>
   <tr bgcolor="F9F9F9">
                      <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>
                    <tr>
                           <td>         
                               <%if(permissionRights.indexOf("PERMISSION_ADD")>=0){%>    
                               <div id="zengjia" style="position:relative;width:60px;height:23px;cursor: pointer;right:56px;float:right;">
		                            <img alt="" src="<%=path %>/images/xinzeng.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">增加</span>
								</div>
                              
                               <%}%> 
                               
                               <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                        
                               <div id="daochu" style="position:relative;width:60px;height:23px;cursor: pointer;right:59px;float:right;">
		                            <img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">导出</span>
								</div>
                               <%}%>
                               <%if(permissionRights.indexOf("PERMISSION_ADD")>=0){%> 	
                                      							
								<div id="daoru" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:-128px; ">
						         <img alt="" src="<%=path %>/images/daoru.png" width="100%" height="100%" />
						         <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导入</span>
								</div>
				               <%}%>
                           </td>
                           </tr>
</table>
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
      		document.form1.action=path+"/web/electricfees/prepaymentList.jsp?curpage="+curpage+"<%=canshuStr%>";
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/electricfees/prepaymentList.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/electricfees/prepaymentList.jsp?curpage="+pageno+"<%=canshuStr%>";
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

           var whereStr="<%=str%>";
            var zdname="<%=zdname%>";
           var ammeterid1="<%=ammeterid1%>";
           
            var curpage ="<%=curpage%>";
           
            //alert(canshuStr);
        	document.form1.action=path+"/web/electricfees/预付费信息.jsp?curpage="+curpage+"&zdname="+zdname+"&ammeterid1="+ammeterid1+"&whereStr="+whereStr;
           //alert(document.form1.action);
            document.form1.submit();
   }
    function exportModel(){
            var curpage = <%=curpage%>;
          
            var whereStr ="<%=whereStr%>";
            //alert(canshuStr);
        	//document.form1.action=path+"/web/electricfees/预付费批量导入模板.jsp?curpage="+curpage+"&whereStr="+whereStr;
            document.form1.action=path+"/servlet/download?action=download&templetname="+encodeURIComponent("预付费批量导入模板");           
            document.form1.submit();
   }
   document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.zdl.value='<%=zdl%>';
		document.form1.qyzt.value='<%=qyzt%>';
 </script>