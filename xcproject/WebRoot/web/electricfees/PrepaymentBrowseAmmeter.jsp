<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%
	    String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
        String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
	    String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	    String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
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
		String beginTimeQ = request.getParameter("BeginTime")!=null?request.getParameter("BeginTime"):"";
        String endTimeQ = request.getParameter("EndTime")!=null?request.getParameter("EndTime"):"";
        String zdbh = request.getParameter("zdbh")!=null?request.getParameter("zdbh"):"0";
        String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||(zdbh!=null)||(beginTimeQ!=null)||(endTimeQ!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&zdbh="+zdbh+"&beginTimeQ="+beginTimeQ+"&endTimeQ="+endTimeQ;
     }
         
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    String roleId = account.getRoleId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
%>

<html>
<head>
<title>
浏览电表
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
		line-height:100%;
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
.form    {width:130px}
bttcn{color:black;font-weight:bold;}
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
	
    function queryDegree(){
                 
                   document.form1.action=path+"/web/electricfees/PrepaymentBrowseAmmeter.jsp";
                   document.form1.submit();
       
    }
    $(function(){
		$("#queding").click(function(){
			queding();
		});
		$("#cancelBtn").click(function(){
			javascript:window.close();
	
		});
		$("#chaxun").click(function(){
			queryDegree();
		});

	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>

<body class="body">
		<form action="" name="form1" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr>
			    <td colspan="4">
			  <div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电表信息</span>	
			  </div>
	    </td>	</tr>   
				<tr><td height="20" colspan="4" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td>
	    	</tr>

  		 <tr>
	    	<td width="1200px">
	    	<table>
	    	<tr class="form_label">
	    	<td>
             城市：</td>
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
         	</select>
         	</td>
         <td>
			区县： </td>
                <td>
         <select name="xian" class="selected_font" onchange="changeCountry()"> 
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
         	</select>    </td>    
         <td>
         乡镇：      </td>
        <td> <select name="xiaoqu" style="width:130" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
							       <div id="chaxun" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                             <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
		                          </div>
							</td>
      </tr>
      </table></td></tr>
      <tr>
			     <td colspan="5">
					<div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table>
					<tr class="form_label">
						<td>站点名称：</td>
						<td>
						<input type="text" name="zdbh" class="selected_font" value="<%if(null!=request.getParameter("zdbh")) out.print(request.getParameter("zdbh")); %>" />
						</td>
						<td>
						抄表起始时间：
						</td>
						<td>
						 <input type="text" name="BeginTime" class="selected_font" value="<%if(null!=request.getParameter("BeginTime")) out.print(request.getParameter("BeginTime")); %>" onFocus="getDateString(this,oCalendarChs)"  />
						</td>
						<td>
						  抄表结束时间：
						</td>
						<td>
						<input type="text" name="EndTime" class="selected_font" value="<%if(null!=request.getParameter("EndTime")) out.print(request.getParameter("EndTime")); %>" onFocus="getDateString(this,oCalendarChs)" />
						</td>
						</tr>
				 <tr>
				 </table>
				 </p></div></td></tr>
				 </table>
         
      

<table  height="23">
<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>

  	
  <div style="width:100%;border:1px" >
						<table width="100%" border="0" cellspacing="1"
							cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr>
					<td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">选择</div></td>
                        
                        <td width="16%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">地区</div></td>
                        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属专业</div></td>
            			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>
            			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表用途</div></td>
            			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">初始读数</div></td>
            			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">初始使用时间</div></td>
                        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">倍率</div></td>
                        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表型号</div></td>
                        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>
                      </tr>
       <%
        
         String whereStr = "";
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and jz.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and jz.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" and jz.xiaoqu='"+xiaoqu+"'";
			}
			if(beginTimeQ != null && beginTimeQ != "" && !beginTimeQ.equals("0")){
				whereStr=whereStr+" and am.initialdate>='"+beginTimeQ+"'";
			}
			if(endTimeQ != null && endTimeQ != "" && !endTimeQ.equals("0")){
				whereStr=whereStr+" and am.initialdate<='"+endTimeQ+"'";
			}
			if(zdbh != null && zdbh != "" && !zdbh.equals("0")){
				whereStr=whereStr+" and jzname like '%"+zdbh+"%'";
			}
         ElectricFeesBean bean = new ElectricFeesBean();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageDataAmmeter12(curpage,whereStr,loginId);
       	 allpage=bean.getAllPage();
		String ammeterid="";
		String jzname="";
		String sheng2="";
		String shi2="";
		String xian2="";
		String professionaltypeid="";
		String ammeteruse="";
	    String initialdegree="";
	    String initialdate="";
	    String multiplyingpower="";
	    String ammetertype="";
	    String dfzflx="";
		int intnum=xh = pagesize*(curpage-1)+1;
		String szdq="";
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     
		    ammeterid = (String)fylist.get(k+fylist.indexOf("DBID"));
		    
		   szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		    szdq = szdq != null ? szdq : "";
		    if(szdq.equals("null")||szdq==null){
		    szdq="";
		    }
		    professionaltypeid = (String)fylist.get(k+fylist.indexOf("PROFESSIONALTYPEID"));
		    professionaltypeid = professionaltypeid != null ? professionaltypeid : "";
		    ammeteruse = (String)fylist.get(k+fylist.indexOf("AMMETERUSE"));
		    ammeteruse = ammeteruse != null ? ammeteruse : "";
			initialdegree = (String)fylist.get(k+fylist.indexOf("CSDS"));
			initialdegree = initialdegree != null ? initialdegree : "0";	
			if(initialdegree.equals("null")||initialdegree.equals("")){
			initialdegree="0";
			}
			 DecimalFormat s=new DecimalFormat("0.0");
			 initialdegree=s.format(Double.parseDouble(initialdegree));
					
			initialdate = (String)fylist.get(k+fylist.indexOf("CSSYTIME"));
			initialdate = initialdate != null ? initialdate : "";
		    multiplyingpower = (String)fylist.get(k+fylist.indexOf("BEILV"));
		    multiplyingpower = multiplyingpower != null ? multiplyingpower : "0";
		    if(multiplyingpower.equals("null")||multiplyingpower.equals("")){
			multiplyingpower="0";
			}
			 DecimalFormat ss=new DecimalFormat("0.00");
			 multiplyingpower=ss.format(Double.parseDouble(multiplyingpower));
		    
		    
		    ammetertype = (String)fylist.get(k+fylist.indexOf("DBXH"));
		    ammetertype = ammetertype != null ? ammetertype : "";
		    dfzflx = (String)fylist.get(k+fylist.indexOf("DFZFLX"));
		    dfzflx = dfzflx != null ? dfzflx : "";
		    
		     jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    jzname = jzname != null ? jzname : "";
		    if(jzname.equals("null")||jzname==null){
		    jzname="";
		    }
			String color=null;

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
            <td  align = "center" >
       			<input type="checkbox" name="cbox" value="<%=ammeterid%>" onClick="chooseOne(this);"/>
       		</td>
       		
       		 <td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="left" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center" ><%=professionaltypeid%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dfzflx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=ammeteruse%></div>
       		</td>
       		<td>
       			<div align="right" ><%=initialdegree%></div>
       		</td>

       		<td>
       			<div align="center" ><%=initialdate%></div>
       		</td>          
            <td>
       			<div align="right" ><%=multiplyingpower%></div>
       		</td>
            <td>
       			<div align="center" ><%=ammetertype%></div>
       		</td>
     <td>
       			<div align="left" ><%=ammeterid%></div>
       		</td>
       </tr>
       <%} %>
       <tr bgcolor="#ffffff" onMouseOut="this.style.backgroundColor=''" onMouseOver="this.style.backgroundColor='#BFDFFF'" >
					<td colspan="12" >
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
						<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)"  >
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

</table></div>
  	 
  	 
  	 
  	 
							
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="4"></td>
    <td><table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" >

                  <td colspan="4" align="right">

         <div id="cancelBtn" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 2px">
				<img src="<%=path%>/images/quxiao.png" width="100%"
					height="100%">
				<span
					style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">取消</span>
			</div>

			<div id="queding"
				style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:7px">
				<img alt=""
					src="<%=path%>/images/tijiao.png"
					width="100%" height="100%" />
				<span
					style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">确定</span>
			</div>
        </td>
    </table></td>
  </tr>
   
  <tr>
  <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
    <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
  </tr>

</table>
 <input type="hidden" id = "ammeterid" value=""/>
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
      		document.form1.action=path+"/web/electricfees/PrepaymentBrowseAmmeter.jsp?curpage="+curpage+"<%=canshuStr%>";
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/electricfees/PrepaymentBrowseAmmeter.jsp?curpage="+curpage+"<%=canshuStr%>";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/electricfees/PrepaymentBrowseAmmeter.jsp?curpage="+pageno+"<%=canshuStr%>";
      document.form1.submit();
     }
     
     
      //chooseOne()函式，參數為觸發該函式的元素本身   
     function chooseOne(cb){   
         //先取得同name的chekcBox的集合物件   
         var obj = document.getElementsByName("cbox");  
         for (i=0; i<obj.length; i++){   
             //判斷obj集合中的i元素是否為cb，若否則表示未被點選   
             if (obj[i]!=cb){
              obj[i].checked = false;   
             } else {
              obj[i].checked = true;
              document.getElementById("ammeterid").value = cb.value;
             // alert(document.getElementById("ammeterid").value);
             }                     
         } 
     }   
     function queding(){
       if(document.getElementById("ammeterid").value==undefined){
         alert("请选择一个电表！");
       } else {
         window.opener.document.form1.action=path+"/web/electricfees/addPrepaymentAll.jsp?ammeterid="+document.getElementById("ammeterid").value;
         window.opener.document.form1.submit();
         window.close();
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
        document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
//-->
</script>