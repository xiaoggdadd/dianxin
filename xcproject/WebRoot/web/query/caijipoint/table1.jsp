<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.SimpleDateFormat" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean" %>
<%@ page import="java.sql.ResultSet,java.util.Calendar"%>

<%	    String startmonth = request.getParameter("startmonth")!=null?request.getParameter("startmonth"):"";
        String endmonth = request.getParameter("endmonth")!=null?request.getParameter("endmonth"):"";
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId=account.getAccountId();
        String roleId = account.getRoleId();
        String loginName = (String)session.getAttribute("loginName");
        String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
%>

<html>
<head>
<base target=_self> 
<title>
选择站点
</title>
<style>
            
            .style1 {
	color: #FF9900;
	font-weight: bold;
}
.relativeTag   {   
    z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);      
}; 
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
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.bttcn{color:white;font-weight:bold;}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
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
	var obj = window.dialogArguments
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
		document.form1.action=path+"/web/query/caijipoint/table1.jsp";
		document.form1.submit();
		
	}
	
	function daorujz(){
		alert("请期待！");
	}
	$(function(){

		$("#query").click(function(){
			chaxun();
		});
		$("#cancelBtn").click(function(){
			javascript:window.close();
		});
		

	});

</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">

	
	<form action="" name="form1" id="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">


	<tr>
    <td valign="top">														                    	
  <table width="100%" border="0" cellspacing="1" cellpadding="1">

               <tr  class= "relativeTag ">                            
                   <td nowrap height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold">电表名称</div></td>
                   <td nowrap height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold">电表编号</div></td>
                   <td nowrap height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold">获取时间</div></td>
                   <td nowrap height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold">电表读数</div></td>
                   <td nowrap height="23" bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold">状态</div></td>                              	
              </tr>
       <%  
       if(!endmonth.equals("")){
           SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
           String str=endmonth;
           Date dt=sdf.parse(str);
           Calendar rightNow = Calendar.getInstance();
           rightNow.setTime(dt);
           //rightNow.add(Calendar.YEAR,-1);//日期减1年
           //rightNow.add(Calendar.MONTH,3);//日期加3个月
           rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加1天
           Date dt1=rightNow.getTime();
           endmonth = sdf.format(dt1);
          }
       String whereStr = "";
       if(null != startmonth  &&!startmonth.equals("") && !startmonth.equals("0")){
			whereStr=whereStr+" and t.getdatetime>='"+startmonth+"'";
		}
		if(null != endmonth  && !endmonth.equals("") && !endmonth.equals("0")){
			whereStr=whereStr+" and t.getdatetime<='"+endmonth+"'";
		}
       String dbid=request.getParameter("ammeterid_fk");
       haodianliangBean bean = new haodianliangBean();
       ArrayList fylist = new ArrayList();
       fylist = bean.getPageData_table1(whereStr,dbid);
       allpage=bean.getAllPage();
		String getdatetime="",datavalue="",dbname="",stname="",flag="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
	        dbname=(String)fylist.get(k+fylist.indexOf("DBNAME"));
	        getdatetime = (String)fylist.get(k+fylist.indexOf("GETDATETIME"));
	        datavalue = (String)fylist.get(k+fylist.indexOf("DATAVALUE"));
	        stname = (String)fylist.get(k+fylist.indexOf("STNAME"));
	        datavalue = (String)fylist.get(k+fylist.indexOf("DATAVALUE"));
	        flag = (String)fylist.get(k+fylist.indexOf("FLAG"));
			String color=null;

			if(intnum++%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr bgcolor="<%=color%>">
       		
       		<td>
       			<div align="center" class="form_label"><%=dbname%></div>
       		</td>
       		<td>
       			<div align="center" class="form_label"><%=stname%></div>
       		</td>
       		<td>
       			<div align="center" class="form_label"><%=getdatetime%></div>
       		</td>
       		<td>
       			<div align="center" class="form_label"><%=datavalue%></div>
       		</td>
       		<td>
       			<div align="center" style="font-size:14px"><%=flag%></div>
       		</td>
       </tr>
       <%} %>						
       <%}%>
  	 </table>   	 

    </td>
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
      		document.form1.action=path+"/web/jizhan/zhandianselect.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/jizhan/zhandianselect.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/jizhan/zhandianselect.jsp?curpage="+pageno;
      document.form1.submit();
     }	

     </script>

