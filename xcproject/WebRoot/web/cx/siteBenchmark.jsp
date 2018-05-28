<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@page import="com.noki.mobi.cx.SiteBeanchmark;"%>

<%
	String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
       //System.out.println("logManage.jsp>>"+beginTime);
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	int intnum=0;
	String color="";
	System.out.println("operName="+operName);
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        String loginName = account.getAccountName();
        
           String sheng = (String)session.getAttribute("accountSheng");
		String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
		String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
		String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
         String sptype = request.getParameter("sptype")!=null?request.getParameter("sptype"):"0";
         String chanquan = request.getParameter("chanquan");
         String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
         String zdnamea=request.getParameter("jzaname")!=null?request.getParameter("jzaname"):"";
         String fwlx = request.getParameter("fwlx")!=null?request.getParameter("fwlx"):"0";
         String dbyt=request.getParameter("dbyt")!=null?request.getParameter("dbyt"):"0";
         String htmlsql = request.getParameter("htmlsql");
         String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((sptype!=null)&&(chanquan!=null))||(fwlx!=null)||(htmlsql!=null)||(zdname!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&sptype="+sptype+"&chanquan="+chanquan+"&fwlx="+fwlx+"&htmlsql="+htmlsql+"&zdname="+zdname;
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
 </style>
<style type="text/css" media=print>.noprint{display : none }</style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
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

 
    function queryDegree(){
                    if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null)
		          {
	                   alert("城市不能为空");
	   
	           }else if(document.getElementById("dbyt").value==""||document.getElementById("dbyt").value=="0"||document.getElementById("dbyt").value==null){
	        	   alert("请选择电表用途");
	           }else if(document.getElementById("jzaname").value==""||document.getElementById("jzaname").value=="0"||document.getElementById("jzaname").value==null){
	        	   alert("请选择对标站点");
	           }else{
                   document.form1.action=path+"/web/cx/siteBenchmark.jsp";
                   document.form1.submit();
                   }
       
    }
   
    function getValue(va,sql){
       var general =document.getElementById("general");
       var htmlsql =document.getElementById("htmlsql");
       general.value=va;
       htmlsql.value = sql;    
    }  
   //页面载入方法
    function op(){
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian&tab=jz&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
    }
   $(function(){

	$("#query").click(function(){
		queryDegree();
	});
	$("#liulan").click(function(){
		getjz('a');
	});
});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0501");

System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<body  class="body">
	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
    
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;站点对标</span></td>
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
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">
					<tr bgcolor="F9F9F9">
                      <td height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>

  		 <tr>
         <td>         
          &nbsp;&nbsp;&nbsp;&nbsp;城&nbsp;市&nbsp;：&nbsp;&nbsp;&nbsp;&nbsp;          
         <select name="shi" style="width:110" onchange="changeCity()">
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
        &nbsp;&nbsp;&nbsp;&nbsp;区&nbsp;&nbsp;县&nbsp;&nbsp;：&nbsp;&nbsp;&nbsp;
                
         <select name="xian" style="width:110" onchange="changeCountry()">
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
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乡&nbsp;镇&nbsp;：&nbsp;&nbsp;&nbsp;&nbsp;        
         <select name="xiaoqu" style="width:110" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
         	对标站点：&nbsp;&nbsp;<input type="text" name="jzaname" value="<%=zdnamea %>"  class="form" style="width:110px" readonly/><span class="style1">&nbsp;*</span>
         	<img id="liulan" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png" style="cursor: pointer"/>
        </td>
      </tr>
      <tr>
        <td>
        &nbsp;集团报表类型：         
         <select name="sptype" style="width:110" onchange="javascript:document.form1.sptype2.value=document.form1.sptype.value">
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
        &nbsp;
         房屋类型：
            <select name="fwlx" style="width:110" onchange="javascript:document.form1.fwlx2.value=document.form1.fwlx.value">
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
         	&nbsp;&nbsp;&nbsp;
         	     站点名称：<input type="text" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" class="form" style="width:110px"/>
         电表用途：
            <select name="dbyt" style="width:110" onchange="javascript:document.form1.dbyt2.value=document.form1.dbyt.value">
         		<option value="0">请选择</option>
	         <%
	         	ArrayList dbytlist = new ArrayList();
	         dbytlist = commBean.getDbyt();
	         	if(dbytlist!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)dbytlist.get(0)).intValue();
	         		for(int i=scount;i<dbytlist.size()-1;i+=scount)
                    {
                    	sfid=(String)dbytlist.get(i+dbytlist.indexOf("CODE"));
                    	sfnm=(String)dbytlist.get(i+dbytlist.indexOf("NAME"));
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
         	</select>
          
      </td>
      </tr>
      <tr>
       <td width="600">起始月份：<input type="text" name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" 
       									readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" style="width:110px" class="form"/>
          		&nbsp;结束月份：<input type="text" name="endTime" value="<%if(null!=request.getParameter("endTime")) out.print(request.getParameter("endTime")); %>" 
          							  readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" style="width:110px" class="form"/>
           &nbsp;&nbsp;&nbsp;
        <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:80%;margin:-23px 0px 0px 0px">
			<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
			<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
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
                         <td width="8%" height="23" bgcolor="#888888"><div align="center" class="bttcn">站点代码</div></td>
                         <td width="12%" height="23" bgcolor="#888888"><div align="center" class="bttcn">站点名称</div></td>
                         <td width="12%" height="23" bgcolor="#888888"><div align="center" class="bttcn">所在地区</div></td>
                         <td width="8%" height="23" bgcolor="#888888"><div align="center" class="bttcn">站点属性</div></td>
				         <td width="8%" height="23" bgcolor="#888888"><div align="center" class="bttcn">集团报表类型</div></td>
				         <td width="8%" height="23" bgcolor="#888888"><div align="center" class="bttcn">用房类型</div></td>
                         <td width="8%" height="23" bgcolor="#888888"><div align="center" class="bttcn">供电方式</div></td>
                         <td width="8%" height="23" bgcolor="#888888"><div align="center" class="bttcn">耗电量</div></td>
                         <td width="6%" height="23" bgcolor="#888888"><div align="center" class="bttcn">电量对标值</div></td>
                         <td width="8%" height="23" bgcolor="#888888"><div align="center" class="bttcn">电费</div></td>
                         <td width="6%" height="23" bgcolor="#888888"><div align="center" class="bttcn">电费对标值</div></td>
                         
                      </tr>
                <%
                      DecimalFormat mat=new DecimalFormat("0.0");
          		      DecimalFormat mat1=new DecimalFormat("0.00");
				    String str="";
				    SiteBeanchmark bean = new SiteBeanchmark();
				   if(beginTime != null && !beginTime.equals("")&& !beginTime.equals("0")){
						str=str+" and to_char(a.startmonth,'yyyy-mm') >='"+beginTime+"'";
					 }
					if(endTime != null && !endTime.equals("")&& !endTime.equals("0")){
						str=str+" and to_char(a.startmonth,'yyyy-mm')<='"+endTime+"'";
					}	 
				   if(zdnamea != null && !zdnamea.equals("")&& !zdnamea.equals("0")){
					 str=str+"and z.jzname='"+zdnamea+"'";
					 
				    bean=bean.getInformation(str);
				    bean=bean.getlist(zdnamea);
				    String ydl=bean.getHdl();
				    String ydf=bean.getDf();
				    	 if(ydl.equals(""))ydl="0";
						 if(ydf.equals(""))ydf="0";
				    	ydl=mat.format(Double.parseDouble(ydl));
					    ydf=mat1.format(Double.parseDouble(ydf));
				%>
				
                      <tr  height = "20">
                         <td width="8%" height="23" ><div align="center" ><%=bean.getZdcode() %></div></td>
                         <td width="12%" height="23" ><div align="center" ><%=bean.getZdname() %></div></td>
                         <td width="12%" height="23" ><div align="center" ><%=bean.getSzdq() %></div></td>
                         <td width="8%" height="23" ><div align="center" ><%=bean.getZdsx() %></div></td>
				         <td width="8%" height="23" ><div align="center" ><%=bean.getZdlx() %></div></td>
				         <td width="8%" height="23" ><div align="center" ><%=bean.getYflx() %></div></td>
                         <td width="8%" height="23" ><div align="center" ><%=bean.getGdfs() %></div></td>
                         <td width="8%" height="23" ><div align="center" ><%=ydl %></div></td>
                         <td width="6%" height="23" ><div align="center" ></div></td>
                         <td width="8%" height="23" ><div align="center" ><%=ydf %></div></td>
                         <td width="6%" height="23" ><div align="center" ></div></td>
                         
                      </tr>
       <%}
         String whereStr = "";
         System.out.println("dianliangListjsp--------------:"+sptype+chanquan+fwlx);
			if(shi != null && !shi.equals("")&& !shi.equals("0")){
				whereStr=whereStr+" and z.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("")&& !xian.equals("0")){
				whereStr=whereStr+" and z.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("")&& !xiaoqu.equals("0")){
				whereStr=whereStr+" and z.xiaoqu='"+xiaoqu+"'";
			}
			if(sptype != null && !sptype.equals("")&& !sptype.equals("0")){
				whereStr=whereStr+" and z.JZTYPE='"+sptype+"'";
			}
			if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
				whereStr=whereStr+" and z.JZNAME like '%"+zdname+"%'";
			}
			if(fwlx != null && !fwlx.equals("")&& !fwlx.equals("0")){
				whereStr=whereStr+" and z.YFLX='"+fwlx+"'";
			}
			if(dbyt.equals("dbyt01")){
				if(beginTime != null && !beginTime.equals("")&& !beginTime.equals("0")){
					whereStr=whereStr+" and to_char(a.startmonth,'yyyy-mm') >='"+beginTime+"'";
				}
				if(endTime != null && !endTime.equals("")&& !endTime.equals("0")){
					whereStr=whereStr+" and to_char(a.startmonth,'yyyy-mm')<='"+endTime+"'";
				}
			}else{
				if(beginTime != null && !beginTime.equals("")&& !beginTime.equals("0")){
					whereStr=whereStr+" and to_char(a.thisdatetime,'yyyy-mm-dd') >='"+beginTime+"-01"+"'";
				}
				if(endTime != null && !endTime.equals("")&& !endTime.equals("0")){
					whereStr=whereStr+" and to_char(a.thisdatetime,'yyyy-mm-dd')<='"+endTime+"-31"+"'";
				}
			}
			
			
        
       	 ArrayList fylist = new ArrayList();
       	 if(shi != null && !shi.equals("")&& !shi.equals("0")){
       	 fylist = bean.getlist(curpage,whereStr,loginId,dbyt);
       	
		String id = "";
		String shengl = "";
		String shil = "";
		String xianl = "";
		String jzname = "";
		String jzcode = "";
		String szdq="",jztype="",property="",yflx="",jnglmk="",gdfs="",df="",dl="",dldb="",dfdb="";
        String duibiaodl=bean.getHdl();
        String duibiaodf=bean.getDf();
        if(duibiaodf.equals(""))duibiaodf="0";
		if(duibiaodl.equals(""))duibiaodl="0";
		intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     
		    id = (String)fylist.get(k+fylist.indexOf("ID"));
		  
			
		    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    jzname = jzname != null ? jzname : "";
		    
		    jzcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
		    jzcode = jzcode != null ? jzcode : "";
		    
		    szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		    szdq = szdq != null ? szdq : "";
		    
		    gdfs = (String)fylist.get(k+fylist.indexOf("GDFS"));
		    gdfs = gdfs != null ? gdfs : "";
			
			property = (String)fylist.get(k+fylist.indexOf("PROPERTY"));
			property = property != null ? property : "";
			
			jztype = (String)fylist.get(k+fylist.indexOf("JZTYPE"));
			jztype = jztype != null ? jztype : "";
			
			yflx = (String)fylist.get(k+fylist.indexOf("YFLX"));
			yflx = yflx != null ? yflx : "";
			
			dl = (String)fylist.get(k+fylist.indexOf("HDL"));
			dl = dl != null ? dl : "";
			
			df = (String)fylist.get(k+fylist.indexOf("DF"));
			df = df != null ? df : "";
			
			if(df.equals(""))df="0";
			if(dl.equals(""))dl="0";
			
		   
            dl=mat.format(Double.parseDouble(dl));
            df=mat1.format(Double.parseDouble(df));
			dldb=mat1.format((Double.parseDouble(dl)-Double.parseDouble(duibiaodl))/Double.parseDouble(duibiaodl)*100);
            dfdb=mat1.format((Double.parseDouble(df)-Double.parseDouble(duibiaodf))/Double.parseDouble(duibiaodf)*100);
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
       			<div align="center" ><%=szdq%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=property%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jztype%></div>
       		</td>
       		<td>
       			<div align="center" ><%=yflx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=gdfs%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dl%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dldb%>%</div>
       		</td>
       		<td>
       			<div align="center" ><%=df%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dfdb%>%</div>
       		</td>

       </tr>
       
       <%} %>
       <%} %>
       <%}%>
      <% if (intnum==0){
    	  System.out.println("QQQQ"+intnum);
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
           
                   
        </tr>
      <% }}
         
        %>
      
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
  <tr bgcolor="F9F9F9">
                      <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>
  <tr>
  
  </tr>
  <tr>
  <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
  <input type="hidden" name="sptype2" id="sptype2" value=""/>
  <input type="hidden" name="chanquan2" id="chanquan2" value=""/>
  <input type="hidden" name="fwlx2" id="fwlx2" value=""/>
    <input type="hidden" name="dbyt2" id="dbyt2" value=""/>
  <input type="hidden" value="" id="htmlsql" name="htmlsql" />
  </tr>
</table>

</td>
</tr>
</table>

</form>
</body>
</html>
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
var path = '<%=path%>';
		function getjz(par){
			var divWidth = 800;
			var bodyWidth = $("body").innerWidth();
			var leftSize = 0;//div距离左侧的位置
			if(bodyWidth>divWidth)leftSize = (bodyWidth-divWidth)/2;
			var divStr = "<div id='areaDiv' name='"+par+"' style='position:absolute;width:"+divWidth+"px;height:400px;border:5px #cad5db groove;left:"+leftSize+"px;top:100px;background-color:green'>";
			divStr += "<iframe src='<%=path%>/web/cx/siteBeanchmark_selectSite.jsp' name='' id='' width='"+(divWidth-5)+"px' height=100%></iframe>";
			divStr += "</div>";
			$("body").append(divStr);
		}
        document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.fwlx.value='<%=fwlx%>';
		document.form1.sptype.value='<%=sptype%>';
		document.form1.dbyt.value='<%=dbyt%>';
		
 </script>