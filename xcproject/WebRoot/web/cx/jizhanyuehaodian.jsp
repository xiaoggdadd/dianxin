<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.mobi.cx.JiZhanYueNengHao" %>
<%@ page import="java.sql.ResultSet"%>

<%
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
        String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
       
	String sheng = request.getParameter("sheng")!=null?request.getParameter("sheng"):"0";
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
        String path = request.getContextPath();
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
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
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
					
	}
	function delLogs(){
		

	}
	function addJz(){
		document.form1.action=path+"/web/cx/jizhanyuehaodian.jsp";
		document.form1.submit();
	}
	function daorujz(){
		alert("请期待！");
	}

</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="<%=path%>/images/css.css" type=text/css rel=stylesheet>
	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">


	<tr>
    <td width="10" height="500" background="../../images/img_10.gif">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
					
						<tr>
							<td colspan=3 width="600" background="<%=path%>/images/btt.bmp" height=40><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基站月耗电趋势</span></td>
							
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
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" bgcolor="7C92B7">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">
<div id="parent" style="display:inline">
                          </div><div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>

  		 <tr>
         <td width="800">起始月份&nbsp;<input type="text" name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" onFocus="getDateString(this,oCalendarChs)"  class="form"/>
          结束月份 <input type="text" name="endTime" value="<%if(null!=request.getParameter("endTime")) out.print(request.getParameter("endTime")); %>" onFocus="getDateString(this,oCalendarChs)"  class="form"/>
         </td>
         <td>
         </td>
      </tr>
 <tr>
         <td >省：
         	<select name="sheng"  style="width:130" onchange="changeSheng()"()>
         		<option value="0">请选择</option>
         		<%
	         	ArrayList shenglist = new ArrayList();
	         	shenglist = commBean.getSheng();
	         	if(shenglist!=null){
	         		String agcode="",agname="";
	         		int scount = ((Integer)shenglist.get(0)).intValue();
	         		for(int i=scount;i<shenglist.size()-1;i+=scount)
                    {
                    	agcode=(String)shenglist.get(i+shenglist.indexOf("AGCODE"));
                    	agname=(String)shenglist.get(i+shenglist.indexOf("AGNAME"));
                    %>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%}
	         	}
	         %>
         	</select>
         	&nbsp;市：
         	<select name="shi"  style="width:130" onchange="changeShi()">
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
         	
         	&nbsp;区、县
         	
         	<select name="xian" style="width:130"  onchange="changeXian()">
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
         	<select name="xiaoqu"  style="width:130">
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
        <td>&nbsp;</td>
         <div align="right">
         	<td><input type="button" name="shanchulogs1" id="id1" class="memo" value="查询" onclick="addJz()"  style="color:#014F8A"/>
         	&nbsp;&nbsp;&nbsp;&nbsp;
        
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
             <td width="5%" height="23" bgcolor="#888888"><div align="center" class="bttcn">序号</div>
                                </td>
                               
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">基站编号</div>
            			</td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">基站名称</div>
            			</td>

                                    <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">合计用电量</div>
            			</td>
            			<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">抄表次数</div>
            			</td>
				<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">合计交费数额</div>
            			</td>
            			<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">交费次数</div>
            			</td>
				
            </tr>
       <%
       JiZhanYueNengHao bean = new JiZhanYueNengHao();
       	 ArrayList fylist = new ArrayList();
       fylist = bean.getPageData(curpage,beginTime,endTime,sheng,shi,xian,xiaoqu);
       	 allpage=bean.getAllPage();
		String jzcode="",jzname = "",ydcount = "",cbcount = "", jfcount= "",jfcs="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     jzcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
				jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    cbcount = (String)fylist.get(k+fylist.indexOf("CBCOUNT"));
		    ydcount = (String)fylist.get(k+fylist.indexOf("YDCOUNT"));
		    jfcount = (String)fylist.get(k+fylist.indexOf("JFCOUNT"));
		    jfcs = (String)fylist.get(k+fylist.indexOf("JFCS"));
				
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
       			<div align="center" ><%=jzname%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=ydcount%></div>
       		</td>
       		<td>
       			<div align="center" ><%=cbcount%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jfcount%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jfcs%></div>
       		</td>
       		

       </tr>
       <%} %>
       <tr bgcolor="#ffffff"  >
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
												

												<div align="right">
												<input type="button" name="shanchulogs1" id="id1" class="memo" value="导出" onclick="daorujz()"  style="color:#014F8A"/>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="button" name="shanchulogs1" id="id1" class="memo" value="打印" onclick="dayinpage()"  style="color:#014F8A"/>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							        </div>
											</td>
										</tr>
					</table>
				</td>
			</tr>
		</table>
      <br />

    </td>
    <td background="../../images/img_13.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="../../images/img_23.gif" width="12" height="19" /></td>
    <td background="../../images/img_24.gif">&nbsp;</td>
    <td><img src="../../images/img_25.gif" width="12" height="19" /></td>
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
      		document.form1.action=path+"/web/cx/jizhanyuehaodian.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/cx/jizhanyuehaodian.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/cx/jizhanyuehaodian.jsp?curpage="+pageno;
      document.form1.submit();
     }
     
    function changeSheng(){
			var sheng = document.form1.sheng.value;
			if(sheng=="0"){
				var shilist = document.all.shi;
				shilist.options.length="0";
				shilist.add(new Option("请选项","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=sheng&pid="+sheng,"sheng");
			}
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
		function updateShi(res){
			var shilist = document.all.shi;
			shilist.options.length="0";
			shilist.add(new Option("请选项","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
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
		document.getElementById("sheng").value='<%=sheng%>';
		document.getElementById("shi").value='<%=shi%>';
		document.getElementById("xian").value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		function dayinpage(){
		var tt = 'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no';
		var sheng = '<%=sheng%>';
		var shi = '<%=shi%>';
		var xian = '<%=xian%>';
		var xiaoqu = '<%=xiaoqu%>';
		var beginTime = '<%=beginTime%>';
		var endTime = '<%=endTime%>';
		var url = path+"/web/cx/jizhanyuehaodian_dy.jsp?sheng="+sheng+"&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&beginTime="+beginTime+"&endTime="+endTime
		window.open(url,"nenghaodayin",tt);
	}
	document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
     </script>

