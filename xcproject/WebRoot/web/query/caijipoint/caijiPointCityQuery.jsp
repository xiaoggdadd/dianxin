<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime" %>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.query.caijipoint.javabean.CaijiPointBean;"%>

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
String roleId = account.getRoleId();
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
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
 .memo {border: 1px ##888888 solid}
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
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script src="<%=path%>/javascript/Date_ny.js"></script>
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
<!-- 年月日期控件 -->
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

   function queryDegree(){
        
                   //var hntype = document.form1.hntype.value;
                   var bTime = document.form1.BeginTime.value;
                   //alert(bTime);
                   beginTime = bTime;//.substr(0,4) + "." + bTime.substr(5,6); 
                   //alert(beginTime);
                   //var endTime = document.form1.EndTime.value;
                   var shi = document.form1.shi.value;
                   var canshuStr = "?beginTime="+beginTime+"&shi="+shi; 

                   //alert(canshuStr);
                   document.form1.action=path+"/web/query/caijipoint/caijiPointCityQuery.jsp"+canshuStr;
                   document.form1.submit();
       
    }

</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonAccountBean">
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;按地市统计查询</span></td>
							
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
<tr bgcolor="F9F9F9">
                      <td height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>

  		 <tr>
         <td height="8%" width="1000">
         &nbsp;&nbsp;&nbsp;
            时间段：<input type="text" name="BeginTime" value="" onFocus="getDatenyString(this,oCalendarChsny)"  class="form"/>          
	       &nbsp;&nbsp;&nbsp;&nbsp;
	       城市：             
         <select name="shi" style="width:130" onchange="">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList shenglist = new ArrayList();
	         	shenglist = commBean.getShi(loginId);
	         	if(shenglist!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)shenglist.get(0)).intValue();
	         		for(int i=scount;i<shenglist.size()-1;i+=scount)
                    {
                    	sfid=(String)shenglist.get(i+shenglist.indexOf("AGCODE"));
                    	sfnm=(String)shenglist.get(i+shenglist.indexOf("AGNAME"));
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
         	</select>
         <!--img src="../../images/search.gif" width="18" height="20" /-->
         <input type="button" name="chaxunlogs0" id="id1" value="查询" onclick="queryDegree()"  style="color:#014F8A"/>
         </td>
      </tr>
<tr bgcolor="F9F9F9">
                      <td height="19" colspan="4"><div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div></td>
                    </tr>
  </table>
 	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">
           <tr>
             <td width="10%" height="23" bgcolor="#888888"><div align="center">省份</div>
                                </td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center">地市</div>
            			        </td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center">时间段</div>
            			        </td>
                                
            			                           <td nowrap height="23" bgcolor="#888888">
                                <div align="center">采集点数量</div>                                                        
            			        </td>
            			                           <td nowrap height="23" bgcolor="#888888">
                                <div align="center">采集点覆盖的机房数量</div>                                                        
            			        </td>
            			                           <td nowrap height="23" bgcolor="#888888">
                                <div align="center">机房数量</div>                                                        
            			        </td>
                                                                                
            </tr>
       <%

         String chooseIdStr = "";
         String chooseIdWhere = "";
         String whereStr = "";
  	     String[] chooseId = new String[8];
         if(request.getParameter("chooseIdStr") != null && request.getParameter("chooseIdStr") != ""){
            chooseIdStr = request.getParameter("chooseIdStr");
            chooseId = chooseIdStr.split(",");
            System.out.println("chooseId:"+chooseId[0]);
         for(int k=0;k<chooseId.length;k++){
           chooseIdWhere=chooseIdWhere+" t.housetype="+chooseId[k]+" or ";
         }
           chooseIdWhere=" and ("+chooseIdWhere.substring(0,chooseIdWhere.length()-3)+")";
           whereStr=whereStr+chooseIdWhere;
        }
         
         System.out.println("ammeterDegreeQueryList:"+chooseIdWhere);                
           String beginTimeQ = request.getParameter("beginTime");
			if(beginTimeQ != null && !beginTimeQ.equals("")){
				whereStr=whereStr+" and cjpoint.sj='"+beginTimeQ+"'";
			}
	     String shicode = request.getParameter("shi");
			if(shicode != null && !shicode.equals("")&& !shicode.equals("0")){
				whereStr=whereStr+" and cjpoint.shi='"+shicode+"'";
			}
			      
         CaijiPointBean bean = new CaijiPointBean();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageDataCity(curpage,whereStr);
       	 allpage=bean.getAllPage();
		String id="",
		sheng = "",
		shi = "",
		agname = "",
		sj = "",
		housename = "", 
		cjmun= "",
		cjfghousenum = "",
		housenum="";
         		
         
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		    id = (String)fylist.get(k+fylist.indexOf("ID"));
			sheng = (String)fylist.get(k+fylist.indexOf("SHENG"));
			sheng = sheng != null ? sheng : "";
		    shi = (String)fylist.get(k+fylist.indexOf("SHI"));
		    shi = shi != null ? shi : "";
		    sj = (String)fylist.get(k+fylist.indexOf("SJ"));
		    sj = sj != null ? sj : "";
		    housename = (String)fylist.get(k+fylist.indexOf("HOUSENAME"));	
		    housename = housename != null ? housename : "";	    
		    cjmun = (String)fylist.get(k+fylist.indexOf("CJNUM"));
		    cjmun = cjmun != null ? cjmun : "";
			cjfghousenum = (String)fylist.get(k+fylist.indexOf("CJFGHOUSENUM"));
			cjfghousenum = cjfghousenum != null ? cjfghousenum : "";
			housenum = (String)fylist.get(k+fylist.indexOf("HOUSENUM"));
			housenum = housenum != null ? housenum : "";
            
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
       			<div align="center" ><%=sheng%></div>
       		</td>
       		<td>
       			<div align="center" ><%=shi%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sj%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=cjmun%></div>
       		</td>
       		<td>
       			<div align="center" ><%=cjfghousenum%></div>
       		</td>
       		<td>
       			<div align="center" ><%=housenum%></div>
       		</td>        		 		
       </tr>
       <%} 	
       %>
      
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
       <%}%>


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
   <td align="right">                              
                               <input type="button" name= "print" id="id1" value= "打印"  onclick= "printad()" style="color:#014F8A"/> 
                               &nbsp;&nbsp;&nbsp;                                                           
                               <input type="button" name="exportad1" id="id1" value="导出" onclick="exportad()"  style="color:#014F8A"/>
                           </td>
  
  </tr>
  <tr>
  <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
  <input type="hidden" name="sptype2" id="sptype2" value=""/>
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
      		document.form1.action=path+"/web/query/caijipoint/caijiPointCityQuery.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/query/caijipoint/caijiPointCityQuery.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/query/caijipoint/caijiPointCityQuery.jsp?curpage="+pageno;
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
		var shilist = document.all.zhandian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.zhandian;
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
            var whereStr ="<%=whereStr%>";
            //alert(canshuStr);
        	document.form1.action=path+"/web/query/caijipoint/按地市统计查询.jsp?curpage="+curpage+"&whereStr="+whereStr;
            document.form1.submit();
   }
   function printad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=whereStr%>";
            //alert(canshuStr);
            window.open(path+"/web/query/caijipoint/caijiPointCityQuery_print.jsp?curpage="+curpage+"&whereStr="+whereStr,'','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
      
        	//document.form1.action=path+"/web/query/basequery/ammeterdegreesquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr;
            //document.form1.submit();
   }
 </script>