﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.mobi.cx.JiZhanNengHao" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*" %>

<%
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"0";
        String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"0";
        
	String sheng = (String)session.getAttribute("accountSheng");
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
        String path = request.getContextPath();
         String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
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
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.bttcn{color:white;font-weight:bold;}
.form{width:130px}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
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
					
	}
	function delLogs(){
		

	}
	function addJz(){
		var bt = document.form1.beginTime.value;
		var et = document.form1.endTime.value;
		if(bt == ""){
			alert("请选择开始时间！");
			return;
		}
		if(et == ""){
			alert("请选择结束时间！");
			return;
		}
		document.form1.action=path+"/web/cx/jizhannenghao.jsp";
		document.form1.submit();
	}
	function daorujz(){
		var tt = 'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no';
		var sheng = '<%=sheng%>';
		var shi = '<%=shi%>';
		var xian = '<%=xian%>';
		var xiaoqu = '<%=xiaoqu%>';
		var beginTime = '<%=beginTime%>';
		var endTime = '<%=endTime%>';
		var url = path+"/web/cx/jizhannenghao_dc.jsp?sheng="+sheng+"&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&beginTime="+beginTime+"&endTime="+endTime
		window.open(url,"nenghaodayin",tt);
	}
$(function(){

	$("#query").click(function(){
		addJz();
	});
	
	$("#dayinBtn").click(function(){
		dayinpage('专业类型能耗统计');
	});
	
	$("#daochuBtn").click(function(){
		daorujz();
	});
});

</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp站点能耗查询</span></td>
							
							<td width="380">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
					 <tr><td>&nbsp;</td></tr>
					  <tr><td colspan="3"><div id="parent" style="display:inline">
                          </div><div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div></td></tr>
						<tr>
							<td width="4"></td>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">
<tr bgcolor="F9F9F9">
                     
                    </tr>

  		 <tr>
         <td width="800">起始月份：<input type="text" name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form"/>
        &nbsp;结束月份：<input type="text" name="endTime" value="<%if(null!=request.getParameter("endTime")) out.print(request.getParameter("endTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form"/>
         </td>
         <td>
         </td>
      </tr>
 <tr>
         <td >
         &nbsp;&nbsp;&nbsp;&nbsp;市&nbsp;&nbsp;&nbsp;：&nbsp;&nbsp;&nbsp;&nbsp;
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
         	</select>
         	
         	&nbsp;&nbsp;区&nbsp;、县：&nbsp;
         	
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
         	乡镇：
         	<select name="xiaoqu" id="xiaoqu" style="width:130">
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
                <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:80%;TOP:-23PX">
					<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
					<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
				</div> 
         </td>
         </div>
      </tr>


  </table>
<div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>
  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">
       </tr>
                     <tr>
						 <td width="5%" height="23" bgcolor="#888888"><div align="center" class="bttcn">序号</div></td>                               
                         <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点编号</div></td>
                         <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点名称</div></td>
            			 <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">所在地区</div></td>
                         <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">合计用电量</div></td>
            			 <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">抄表次数</div></td>
				         <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">合计交费数额</div></td>
            			 <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">交费次数</div></td>				
                    </tr>
       <%
       JiZhanNengHao bean = new JiZhanNengHao();
       	 ArrayList fylist = new ArrayList();
       	 if(beginTime != null && !beginTime.equals("") && !beginTime.equals("0")){
           fylist = bean.getPageData(curpage,beginTime,endTime,shi,xian,xiaoqu);
         
       	 allpage=bean.getAllPage();
		String jzcode="",jzname = "",ydcount = "",cbcount = "", jfcount= "",jfcs="",szdq="";
		double df=0;
		String dianfei="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     jzcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
				jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
				szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		    cbcount = (String)fylist.get(k+fylist.indexOf("NUMDL"));
		    ydcount = (String)fylist.get(k+fylist.indexOf("DEGREE"));
		    jfcount = (String)fylist.get(k+fylist.indexOf("PAY"));
		    jfcs = (String)fylist.get(k+fylist.indexOf("NUMDF"));
				if(jfcount==null||jfcount=="")jfcount="0";
				if(ydcount==null||ydcount=="")ydcount="0";
			DecimalFormat mat=new DecimalFormat("0.00");
			DecimalFormat mat1=new DecimalFormat("0.0");
			df=Double.parseDouble(jfcount);
			dianfei=mat.format(df);
			ydcount=mat1.format(Double.parseDouble(ydcount));
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
       			<div align="center" ><%=jzcode%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="right" ><%=ydcount%></div>
       		</td>
       		<td>
       			<div align="right" ><%=cbcount%></div>
       		</td>
       		<td>
       			<div align="right" ><%=dianfei%></div>
       		</td>
       		<td>
       			<div align="right" ><%=jfcs%></div>
       		</td>
       		

       </tr>
       <%} %>
       <tr bgcolor="#ffffff" >
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
						<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" class="form" >
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
<%}%>

  	 </table> 
  	 
  	 
  	 
											</td>
										</tr>
									</table>
								</td>
						</tr>
						<tr bgcolor="#FFFFFF">
											
											<td align="right" colspan="10">
												<div id="parent" style="display:inline" align="right">
                          <div style="width:300px;display:inline;" align="right"><hr></div>
                      </div>
											</td>
											
										</tr>
										
										<tr bgcolor="#FFFFFF">
											
											<td colspan="10">
												

												<div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
	 												<img src="<%=path %>/images/daochu.png" width="100%" height="100%">
													<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
												</div>
												<div id="dayinBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:15px">
														<img src="<%=path %>/images/dayin.png" width="100%" height="100%">
														<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">打印</span>      
												</div>
											</td>
										</tr>
					</table>
				</td>
			</tr>
		</table>
      <br />

    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
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
      		document.form1.action=path+"/web/cx/jizhannenghao.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/cx/jizhannenghao.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/cx/jizhannenghao.jsp?curpage="+pageno;
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
			
     </script>

