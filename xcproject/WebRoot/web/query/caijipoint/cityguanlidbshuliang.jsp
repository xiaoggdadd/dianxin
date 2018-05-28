<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.*" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*" %>

<%
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";
        String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";
      
	String sheng = (String)session.getAttribute("accountSheng");
	String fwlx=request.getParameter("fwlx")!=null?request.getParameter("fwlx"):"0";
	String sptype=request.getParameter("sptype")!=null?request.getParameter("sptype"):"0";
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
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
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
				document.form1.action=path+"/web/query/caijipoint/cityguanlidbshuliang.jsp";
		document.form1.submit();	
	}
	
	
 $(function(){
	$("#chaxun-1").click(function(){
			chaxun();
		});
	$("#dayin-1").click(function(){
				dayinpage();
			});
	
});
</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="<%=path%>/images/css.css" type=text/css rel=stylesheet>
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
							<td colspan=3 width="600" background="<%=path%>/images/btt.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地区管理类电表数量统计</span></td>
							
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
<div id="parent" style="display:inline"><br/>
                          </div><div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>
  		 <tr>
         <td width="800">起始月份：&nbsp;<input type="text" name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form" style="width:130px;"/>
          结束月份：<input type="text" name="endTime" value="<%if(null!=request.getParameter("endTime")) out.print(request.getParameter("endTime")); %>" onFocus="getDatenyString(this,oCalendarChsny)"  class="form" style="width:130px;"/>
         <br>
         集团报表类型：&nbsp;<select name="sptype" style="width:130" onchange="javascript:document.form1.sptype2.value=document.form1.sptype.value" style="width:130px;">
         		<option value="0">请选择</option>
	         <%
	         	ArrayList sptypelist = new ArrayList();
	         	sptypelist = commBean.getStationPointType();
	         	if(sptypelist!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)sptypelist.get(0)).intValue();
	         		for(int i=scount;i<sptypelist.size()-1;i+=scount)
                    {
                    	sfid=(String)sptypelist.get(i+sptypelist.indexOf("CODE"));
                    	sfnm=(String)sptypelist.get(i+sptypelist.indexOf("NAME"));
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
	         </select>         
         房屋类型：<select name="fwlx" style="width:130" onchange="javascript:document.form1.fwlx2.value=document.form1.fwlx.value" style="width:130px;">
         		<option value="0">请选择</option>
	         <%
	         	ArrayList yftypelist = new ArrayList();
	         	yftypelist = commBean.getUseHouseType();
	         	if(yftypelist!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)yftypelist.get(0)).intValue();
	         		for(int i=scount;i<yftypelist.size()-1;i+=scount)
                    {
                    	sfid=(String)yftypelist.get(i+yftypelist.indexOf("CODE"));
                    	sfnm=(String)yftypelist.get(i+yftypelist.indexOf("NAME"));
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
	        
         	</select>

	         <div id="chaxun-1" style="position: relative; width: 60px; height: 23px; cursor: pointer; float: right; right: 120px">
				<img alt=""src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
				<span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>
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
                               
                                
            			<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">所在地区</div>
            			</td>

                                    <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点合计</div>
            			</td>
            			<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">电表合计</div>
            			</td>
            			<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">电量合计</div>
            			</td>
			
            </tr>
       <%
       CaijiPointBean bean=new CaijiPointBean();
       	 ArrayList fylist = new ArrayList();
       	 System.out.println(beginTime+endTime);
       	String str="";
       	if(fwlx != null && !fwlx.equals("")&& !fwlx.equals("0")){
       	   str=str+"and Z.YFLX='"+fwlx+"'";
       	}if(sptype != null && !sptype.equals("")&& !sptype.equals("0")){
				str=str+" and Z.JZTYPE='"+sptype+"'";
			}
        if(beginTime != null && !beginTime.equals("")&& !beginTime.equals("0")||endTime != null && !endTime.equals("")&& !endTime.equals("0")){
       fylist = bean.getAgcode(sheng,account.getAccountId(),beginTime,endTime,str);
       
       	 allpage=bean.getAllPage();
		String agname="",zdcount = "",dbcount = "",dlcount="",dl="";
		int sumzd=0,sumdb=0,sumzd1=0,sumdb1=0;
		double sumdl=0.0,sumdl1=0.0;
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     agname = (String)fylist.get(k+fylist.indexOf("AGNAME"));
				zdcount = (String)fylist.get(k+fylist.indexOf("ZDCOUNT"));
				dbcount = (String)fylist.get(k+fylist.indexOf("DBCOUNT"));
				dlcount = (String)fylist.get(k+fylist.indexOf("YDCOUNT"));
				if(dlcount==null||dlcount=="")dlcount="0.0";
				if(zdcount==null||zdcount=="")zdcount="0";
				if(dbcount==null||dbcount=="")dbcount="0";
				sumzd1=Integer.parseInt(zdcount);
				sumzd+=sumzd1;
				sumdb1=Integer.parseInt(dbcount);
				sumdb+=sumdb1;
                sumdl1=Double.parseDouble(dlcount);
                sumdl+=sumdl1;
			    DecimalFormat mat=new DecimalFormat("0.00");
			     dl=mat.format(sumdl); 
			     
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
       			<div align="center" ><%=agname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zdcount%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dbcount%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dlcount%></div>
       		</td>
       </tr>
       <%} %>
     <tr bgcolor="F2F9FF">      		
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" >合计</div>
       		</td>
       		<td>
       			<div align="center" ><%=sumzd%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sumdb%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dl%></div>
       		</td>
       </tr>
       <%} %>
  <%} %>
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
							        <div id="dayin-1" style="position: relative; width: 60px; height: 23px; cursor: pointer; float: right; right: 8px">
				                         <img alt=""src="<%=request.getContextPath() %>/images/dayin.png" width="100%" height="100%" />
				                         <span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">打印</span>
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

<script type="text/javascript">
         document.form1.fwlx.value='<%=fwlx%>';
		 document.form1.sptype.value='<%=sptype%>';
</script>