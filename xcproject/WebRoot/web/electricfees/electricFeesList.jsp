<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>

<%
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
        String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
       //System.out.println("logManage.jsp>>"+beginTime);
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	System.out.println("operName="+operName);
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    //String roleId = account.getRoleId();
    
    
     
String beginTimeQ = request.getParameter("beginTimeQ") != null ? request.getParameter("beginTimeQ"):"";
	String endTimeQ = request.getParameter("endTimeQ") != null ? request.getParameter("endTimeQ"):"";
   String sheng = (String)session.getAttribute("accountSheng");
		String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
		String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
		String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
	String zdbh = request.getParameter("zdbh")!=null?request.getParameter("zdbh"):"";
	//添加站点类型
    String stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
     String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((beginTime!=null)&&(endTime!=null))||(zdbh!=null)||(stationtype1!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&beginTimeQ="+beginTimeQ+"&endTimeQ="+endTimeQ+"&zdbh="+zdbh+"&stationtype1="+stationtype1;
     }
     System.out.println("beginTime!!!!!!:"+beginTime);
      System.out.println("shi!!!!!!:"+shi);
       System.out.println("zdbh!!!!!!:"+zdbh);
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    //System.out.println("roleId:"+roleId);
%>

<html>
<head>
<title>
</title>
<style>
            .btn {
             BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7b9ebd 1px solid
            }
            .btn1_mouseout {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#B3D997); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn1_mouseover {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#CAE4B6); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn2 {padding: 2 4 0
            4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;}
            .btn3_mouseout {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mouseover {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mousedown
            {
             BORDER-RIGHT: #FFE400 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #FFE400 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #FFE400
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #FFE400 1px solid
            }
            .btn3_mouseup {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn_2k3 {
             BORDER-RIGHT: #002D96 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #002D96 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); BORDER-LEFT: #002D96
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #002D96 1px solid
            }
            .style1 {
	color: #FF9900;
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
bttcn{color:white;font-weight:bold;}
 .form    {width:130px}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
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
          document.form1.action=path+"/web/electricfees/addElectricFees.jsp";
                                document.form1.submit();
        }
    function delad(electricfeeId){
       if(confirm("您确定删除此电费信息？")){
                    document.form1.action=path+"/servlet/electricfees?action=del&degreeid="+electricfeeId
        			document.form1.submit()
       }
    }
    function modifyad(electricfeeId){
                    document.form1.action=path+"/web/electricfees/modifyElectricFees.jsp?degreeid="+electricfeeId;
                    document.form1.submit();
       
    }
    
    function queryDegree(){
                   document.form1.action=path+"/web/electricfees/electricFeesList.jsp";
                   document.form1.submit();
       
    }
      //批量导入
  function exportFData(){
          document.form1.action=path+"/web/electricfees/input.jsp";
                                document.form1.submit();
  }
   $(function(){
			$("#daoru").click(function(){
				exportFData();
			});
			$("#zengjia").click(function(){
				adddegree();
		
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
<%
permissionRights=commBean.getPermissionRight(roleId,"0301");

System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">


	<tr>
    <td width="10" height="500">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4"></td>
						</tr>
						<tr>
							<td colspan=3 width="600" background="<%=path%>/images/btt.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电费管理</span></td>

							
							<td width="380">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="4"></td>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" >
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">
												
											

<tr bgcolor="F9F9F9">
                      <td height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>

  		 <tr>
         <td height="8%" width="1200">         
         &nbsp;&nbsp;&nbsp;
             城市：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;               
         <select name="shi" style="width:130" onchange="changeCity()">
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
         	</select>
         &nbsp;&nbsp;&nbsp;
			区县：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                
         <select name="xian" style="width:130" onchange="changeCountry()">
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
         &nbsp;&nbsp;&nbsp;
         乡镇：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         
         <select name="xiaoqu" style="width:130" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
      </tr>
      <tr>
         <td width="1000">
          &nbsp;&nbsp;&nbsp;
            站点名称：<input type="text" name="zdbh" value="<%if(null!=request.getParameter("zdbh")) out.print(request.getParameter("zdbh")); %>" class="form"/>
           &nbsp;&nbsp;&nbsp;
            上次抄表时间：<input type="text" name="beginTimeQ" value="<%if(null!=request.getParameter("beginTimeQ")) out.print(request.getParameter("beginTimeQ")); %>" onFocus="getDateString(this,oCalendarChs)"  class="form"/>
           &nbsp;&nbsp;&nbsp;
            本次抄表时间：<input type="text" name="endTimeQ" value="<%if(null!=request.getParameter("endTimeQ")) out.print(request.getParameter("endTimeQ")); %>" onFocus="getDateString(this,oCalendarChs)"  class="form"/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       
         </td>
          </tr>
          <tr>
          <td>
               &nbsp;&nbsp;站点类型：
         	<select name="stationtype" style="width:130" onchange="kzinfo()">
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
	         </td> 
          <td> 
		<%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>    
        <!--   <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;right:300px;float:right;">
		       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
		       
		</div>
		 -->
        <%}%>
		</td> 
     </tr>
     <!--  <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4"><div id="parent" style="display:inline"><br>
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>
                     -->

  </table>

  	
  <!--  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#666666">

                      <tr  height = "20">
                        <td width="7%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">站点编号</div>
                        <td width="15%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">站点名称</div>   </td>
                         <td width="8%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">站点类型</div>   </td>
                        <td width="6%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">电表编号</div>   </td>
                        <td width="7%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">本次单价</div></td>
            			<td width="8%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">用电费用调整</div></td>
                        <td width="8%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">实际电费金额</div></td>
                        <td width="8%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">票据类型</div></td>
                        <td width="8%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">票据编号</div></td>
                        <td width="6%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">交费操作员</div></td>
                        <td width="8%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">交费时间</div></td>
                        <td width="6%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;"></div></td>
                        <td width="6%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;"></div></td>
                      </tr>
       <%
        
         String whereStr = "";
          String str = "";
			if(null !=shi  && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and zd.shi='"+shi+"'";
				str=str+" and zd.shi='"+shi+"'";
			}
			if(null != xian && !xian.equals("") && !xian.equals("0")){				
				whereStr=whereStr+" and zd.xian='"+xian+"'";
				str=str+" and zd.xian='"+xian+"'";
			}
			if(null != xiaoqu && !xiaoqu.equals("") && !xiaoqu.equals("0")){				
				whereStr=whereStr+" and zd.xiaoqu='"+xiaoqu+"'";
				str=str+" and zd.xiaoqu='"+xiaoqu+"'";
			}
			
			if(null != beginTimeQ  &&!beginTimeQ.equals("") && !beginTimeQ.equals("0")){
				whereStr=whereStr+" and ef.PAYDATETIME>='"+beginTimeQ+"'";
				str=str+" and ef.PAYDATETIME>='"+beginTimeQ+"'";
			}
			if(null != endTimeQ  && !endTimeQ.equals("") && !endTimeQ.equals("0")){
				whereStr=whereStr+" and ef.PAYDATETIME<='"+endTimeQ+"'";
				str=str+" and ef.PAYDATETIME<='"+endTimeQ+"'";
			}
			if( null != zdbh && !zdbh.equals("") && !zdbh.equals("0")){
				whereStr=whereStr+" and jzname like '%"+zdbh+"%'";
			}
			//站点类型
			if(stationtype1 != null && !stationtype1.equals("") && !stationtype1.equals("0")){
				whereStr=whereStr+" and zd.STATIONTYPE='"+stationtype1+"'";
				str=str+" and zd.STATIONTYPE='"+stationtype1+"'";
			}

         ElectricFeesBean bean = new ElectricFeesBean();
       	 ArrayList fylist = new ArrayList();
       	  fylist = bean.getPageData(curpage,whereStr,loginId);
       	 allpage=bean.getAllPage();
       	 System.out.println("ALLLLLLL"+allpage);
		String electricfeeId = "",unitprice = "",floatpay = "", actualpay = "",notetypeid = "",noteno = "",payoperator = "",paydatetime = "",stationtype2="";
		String jzname = "",jzcode = "",manualauditstatus = "",dianbiaoID="";
		int intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		double df=0.0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
             linenum++;
		     //num为序号，不同页中序号是连续的
		    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    jzname = jzname != null ? jzname : "";
		    	
		    jzcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));	
		    jzcode = jzcode != null ? jzcode : "";
		    
		    dianbiaoID = (String)fylist.get(k+fylist.indexOf("AMMETERID"));	
		    dianbiaoID = dianbiaoID != null ? dianbiaoID : "";
		    
		    //添加站点类型
			stationtype2 = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
			stationtype2 = stationtype2 != null ? stationtype2 : "";
		     
		    electricfeeId = (String)fylist.get(k+fylist.indexOf("ELECTRICFEE_ID"));	
		    electricfeeId = electricfeeId != null ? electricfeeId : "";
		    			    
			unitprice = (String)fylist.get(k+fylist.indexOf("UNITPRICE"));
			unitprice = unitprice != null ? unitprice : "";
		    if(unitprice.equals("null")){
			unitprice="0.0";
			}
			
		    floatpay = (String)fylist.get(k+fylist.indexOf("FLOATPAY"));
		    floatpay = floatpay != null ? floatpay : "";
		    if(floatpay.equals("null")){
			floatpay="0.0";
			}
		    
		    actualpay = (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));
		    actualpay = actualpay != null ? actualpay : "";
		    if(actualpay.equals("null")){
			actualpay="0.0";
			}
		    
		    notetypeid = (String)fylist.get(k+fylist.indexOf("NOTETYPEID"));
		    notetypeid = notetypeid != null ? notetypeid : "";
		    
			noteno = (String)fylist.get(k+fylist.indexOf("NOTENO"));
			noteno = noteno != null ? noteno : "";
						
			payoperator = (String)fylist.get(k+fylist.indexOf("PAYOPERATOR"));
			payoperator = payoperator != null ? payoperator : "";
			
		    paydatetime = (String)fylist.get(k+fylist.indexOf("PAYDATETIME"));
		    paydatetime = paydatetime != null ? paydatetime : "";
      
            manualauditstatus = (String)fylist.get(k+fylist.indexOf("MANUALAUDITSTATUS"));
		    manualauditstatus = manualauditstatus != null ? manualauditstatus : "";
			String color=null;
            
            DecimalFormat mat=new DecimalFormat("0.00");//格式化  显示几位小数点
            df=Double.parseDouble(actualpay);
            actualpay=mat.format(df);
            
            DecimalFormat price=new DecimalFormat("0.0000");
            unitprice = price.format(Double.parseDouble(unitprice));
            
            DecimalFormat pay=new DecimalFormat("0.00");
            floatpay = pay.format(Double.parseDouble(floatpay));
            
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
          intnum++;

       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=jzcode%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=stationtype2%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dianbiaoID%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=unitprice%></div>
       		</td>
       		<td>
       			<div align="center" ><%=floatpay%></div>
       		</td>          
            <td>
       			<div align="center" ><%=actualpay%></div>
       		</td>
            <td>
       			<div align="center" ><%=notetypeid%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=noteno%></div>
       		</td>
       		<td>
       			<div align="center" ><%=payoperator%></div>
       		</td>
       		       		<td>
       			<div align="center" ><%=paydatetime%></div>
       		</td>
           <%if(manualauditstatus!=null&&manualauditstatus.equals("2")){ %>
           <td>
       			<div align="center" ><a href="javascript:modifyad('<%=electricfeeId%>')" ></a></div>
       		</td>
       		<td>
       			<div align="center" ><a href="javascript:delad('<%=electricfeeId%>')" ></a></div>
       		</td>  
            <%}else{ %>
       		<td>
       			<div align="center" ><a href="javascript:modifyad('<%=electricfeeId%>')" >修改</a></div>
       		</td>
       		<td>
       		   <%if(permissionRights.indexOf("PERMISSION_DELETE")>=0){%>
       			<div align="center" ><a href="javascript:delad('<%=electricfeeId%>')" >删除</a></div>
       		   <%}%>
       		</td>                   		
            <%}%>
       </tr>
       <%}
       for(int k=15;k>linenum;k--){%>
       <tr bgcolor="#FFFFFF">
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
       </tr>
       <%} %>
      <tr bgcolor="#ffffff" onMouseOut="this.style.backgroundColor=''" onMouseOver="this.style.backgroundColor='#BFDFFF'" >
					<td colspan="13" >
						<div align="center">
							<font color='#000080'>&nbsp;页次:</font>
							&nbsp;&nbsp;
							 <b><font color=red><%=curpage%></font></b>

							 <font color='#000080'>/<b><%=allpage%></b>&nbsp;</font>
							 &nbsp;&nbsp;
							 <font color='#000080'>
						     <% if (curpage!=1)
						       {out.print("<a href='javascript:gopagebyno(1)'>首页</a>");}
						      else
						      {out.print("首页");}
						      %>
					     </font>
					     &nbsp;&nbsp;
							 <font color='#000080'>
						     <%if(curpage!=1)
						          {out.print("<a href='javascript:previouspage()'>上页</a>");}
						         else
						       {out.print("上页");}
						      %>
						   </font>
						   &nbsp;&nbsp;
							<font color='#000080'>
						     <% if(allpage!=0&&(curpage<allpage))
						         {out.print("<a href='javascript:nextpage()'>下页</a>");}
						         else
						        {out.print("下页");}
						     %>
             </font>
							&nbsp;&nbsp;
							<font color='#000080'>
					     <% if(allpage!=0&&(curpage<allpage))
					         {out.print("<a href='javascript:gopagebyno("+allpage+")'>尾页</a>");}
					        else
					        {out.print("尾页");}
					     %>
            </font>
            &nbsp;&nbsp;
						<select style="width:60px"	name="page" onChange="javascript:gopagebyno(document.form1.page.value)" class="form" >
					     <%for(int i=1;i<=allpage;i++)
					         {if(curpage==i){out.print("<option value='"+i+"' selected='selected'>"+i+"</option>");}
					      else{out.print("<option value='"+i+"'>"+i+"</option>");}
					         }
					     %>
				    </select>


						</div>
					</td>
				</tr>
       <%}%>



  	 </table> 
  	 -->
  	 
  	 
  	 
											</td>
										</tr>
									</table>
								</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
      <br />
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
  <td align="right">          
<%--                                 <%if(permissionRights.indexOf("PERMISSION_ADD")>=0){%> --%>
<%--                               <div id="daoru" style="position:relative;width:60px;height:23px;cursor: pointer;right:3px;float:right;">--%>
<%--		                            <img alt="" src="<%=path %>/images/daoru.png" width="100%" height="100%" />--%>
<%--		                            <span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">导入</span>--%>
<%--								</div>--%>
<%--                               --%>
<%--                               <%}%> --%>
                               <%if(permissionRights.indexOf("PERMISSION_ADD")>=0){%>  
                               
                                <div id="zengjia" style="position:relative;width:60px;height:23px;cursor: pointer;right:6px;float:right;">
		                            <img alt="" src="<%=path %>/images/xinzeng.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">新增</span>
								</div>
                               
                               <%} %>
                               <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%> 
                               
                               <div id="daochu" style="position:relative;width:60px;height:23px;cursor: pointer;right:9px;float:right;">
		                            <img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">导出</span>
								</div>
                                <%}%> 
                           </td>
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
      		document.form1.action=path+"/web/electricfees/electricFeesList.jsp?curpage="+curpage+"<%=canshuStr%>";
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      //alert(parseInt(document.form1.page.value));
      //alert(document.form1.page.value);
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/electricfees/electricFeesList.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/electricfees/electricFeesList.jsp?curpage="+pageno+"<%=canshuStr%>";
      document.form1.submit();
     }
    
     </script>
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

//
</script>
<script type="text/javascript">
function exportad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=str%>";
            var zdbh ="<%=zdbh%>";
            //alert(canshuStr);
        	document.form1.action=path+"/web/electricfees/电费信息.jsp?curpage="+curpage+"&whereStr="+whereStr+"&zdbh="+zdbh;
            document.form1.submit();
   }
    function exportModel(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=whereStr%>";
            //alert(canshuStr);
        	//document.form1.action=path+"/web/electricfees/电费批量导入模板.jsp?curpage="+curpage+"&whereStr="+whereStr;
            document.form1.action=path+"/servlet/download?action=download&templetname="+encodeURIComponent("电费批量导入模板");
            document.form1.submit();
   }
   document.form1.shi.value='<%=shi%>';
   document.form1.xian.value='<%=xian%>';
   document.form1.xiaoqu.value='<%=xiaoqu%>';
   document.form1.stationtype.value='<%=stationtype1%>';
 </script>