<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.*"%>
<%@page import="com.noki.query.caijipoint.javabean.CaijiPointBean"%>
<%
	String sheng = (String)session.getAttribute("accountSheng");
	String roleId = (String)session.getAttribute("accountRole");
    String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";
    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";
    String ammeterid = request.getParameter("ammeterid")!=null?request.getParameter("ammeterid"):"";
       
	    String zdmc = request.getParameter("zdmc")!=null?request.getParameter("zdmc"):"";
	     String reback = request.getParameter("reback")!=null?request.getParameter("reback"):"0";
	    
	    System.out.println("logManage.jsp>>"+beginTime+endTime+ammeterid+zdmc);
	     String whereStr="";
        String path = request.getContextPath();
        String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
        String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
        curpage=Integer.parseInt(s_curpage);
        String permissionRights="";
          
%>

<html>
<head>
<title>
管理类电表电量异常报告
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
 .btt{ bgcolor:#888888;}
  .bttcn{ color:white;font-weight:bold;}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
  <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
 <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
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
 function getPublicPath(){
		   var path = document.location.pathname;
			var arraypath=path.split("/");
			var mapath="/";
		 	if(arraypath[1]==undefined){
			 arraypath[1]="";
			 mapath="";
		 	}
		  path=mapath+arraypath[1];
		  return path;
	} 
  function chaxun(){
        if(document.getElementById("beginTime").value==""||document.getElementById("endTime").value==""){
	       alert("起始结束时间不能为空");
	   }else {
		document.form1.action=path+"/web/query/caijipoint/exceptionReport.jsp";
		document.form1.submit();
	  }
	}
 function showIdList(){
 
        window.open('showAmmeterIdList.jsp','','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
 }
 $(function(){
	$("#query").click(function(){
			chaxun();
		});
	$("#liulan11").click(function(){
				showIdList();
			});
});
	
</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
        permissionRights=commBean.getPermissionRight(roleId,"0101");
        System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="<%=path%>/images/css.css" type=text/css rel=stylesheet>
<form action="" name="form1" method="POST">
			<div style="float: left">
				<img src="/energy/images/btt.bmp" />
				  <span
					style="color: black; font-weight: bold; font-size: 16; position: absolute; left: 40px; top: 22px;">
					管理类电表异常报告
					 </span>
			</div>
<table  width="100%"  border="0" cellspacing="0" cellpadding="0">

	<tr>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="4"></td>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" >
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1"><br/>
<tr bgcolor="F9F9F9">
                      <td height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>

  		
      <tr>
         <td width="1000">

            起始时间：<input type="text" name="beginTime" value="<%if(null!=beginTime) out.print(beginTime); %>" onFocus="getDateString(this,oCalendarChs)"  class="form" style="width:130px"/>
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
            结束时间：<input type="text" name="endTime" value="<%if(null!=endTime) out.print(endTime); %>" onFocus="getDateString(this,oCalendarChs)"  class="form" style="width:130px" />
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
       
         </td>
      </tr>
      <tr>
        <td>
                         站点名称：<input type="text" name="zdmc" value="<%=zdmc%>" class="form" style="width:130px"/>
                          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                           电表ID &nbsp;&nbsp;：<input type="text" name="ammeterid" value="<%=ammeterid%>" class="form" style="width:130px"/><span class="style1">&nbsp;*</span>
             <img id="liulan11" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png " style="cursor: pointer" />
             <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:55%;TOP:-23PX">
				  <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
				  <span style="font-size:12px;position: absolute;left:25px;top:2px;color:white">查询</span>
			 </div>
        </td>
      </tr>
     
  <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4"><div id="parent" style="display:inline"> 
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>
  </table>

  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">

                      <tr  height = "20">
                           <td width="5%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">序号</div>
                           <td width="25%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">站点名称</div>
                           <td width="15%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">电表ID</div>
                           	<td width="20%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">异常时间</div>
                            <td width="15%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">取值</div></td>
                            <td width="20%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">异常类型</div></td>
                      </tr>
       <%
        
			if(beginTime != null && beginTime != "" && !beginTime.equals("0")){
				whereStr=whereStr+" and t.getdatetime>='"+beginTime+"'";
			}
			if(endTime != null && endTime != "" && !endTime.equals("0")){
				whereStr=whereStr+" and t.getdatetime<='"+endTime+"'";
			}
			if(zdmc != null && zdmc != "" && !zdmc.equals("0")){
				whereStr=whereStr+" and zd.jzname like '%"+zdmc+"%'";
			}
			if(ammeterid != null && ammeterid != "" && !ammeterid.equals("0")){
				whereStr=whereStr+" and db.dbid ='"+ammeterid+"'";
			}
         CaijiPointBean bean = new CaijiPointBean();
       	 ArrayList fylist = new ArrayList();
       	 if((!whereStr.equals(""))&&(reback.equals("0"))){
       	    fylist = bean.getPageDataException(curpage,whereStr,account.getAccountId());
       	    allpage=bean.getAllPage();
       	 }
       	
		String dbid="";
		String jzname="";
		String time="";
		String data="";
	    String type="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist.size()>0)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     
		   
		    
		    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    jzname = jzname != null ? jzname : " ";
		     dbid = (String)fylist.get(k+fylist.indexOf("STNAME"));
		    time = (String)fylist.get(k+fylist.indexOf("GETDATETIME"));
		    time = time != null ? time : " ";
			data = (String)fylist.get(k+fylist.indexOf("DATAVALUE"));
			data = data != null ? data : " ";
		    type = (String)fylist.get(k+fylist.indexOf("NAME"));
		    type = type != null ? type : " ";
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
       			<div align="center" ><%=intnum%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dbid%></div>
       		</td>
       		<td>
       			<div align="center" ><%=time%></div>
       		</td>
       		<td>
       			<div align="center" ><%=data%></div>
       		</td>
       		<td>
       			<div align="center" ><%=type%></div>
       		</td>
       		
       </tr>
       <%} }%>
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
    


  	 </table> 
  	 
  	 
  	 
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
    <td width="4"></td>
    <td><table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" >
    <td>
       <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:94%;TOP:-23PX">
				  <img alt="" src="<%=request.getContextPath() %>/images/dayin.png" width="100%" height="100%" />
				  <span style="font-size:12px;position: absolute;left:25px;top:2px;color:white">打印</span>
	   </div>
	</td>
        <!--
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" name="quxiao0" id="id1" value="取消" onclick="javascript:window.close();"  style="color:#014F8A"/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      -->
      
    </table></td>
  </tr>
   
  <tr>
  <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
    <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
  </tr>

</table>
 <input type="hidden" name = "ammeterid" value=""/>
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
      		document.form1.action=path+"/web/query/caijipoint/exceptionReport.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/query/caijipoint/exceptionReport.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/query/caijipoint/exceptionReport.jsp?curpage="+pageno;
      document.form1.submit();
     }
</script>