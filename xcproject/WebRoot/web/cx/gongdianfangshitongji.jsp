<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,java.text.*" %>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.mobi.cx.GongDianFangShiTongJi"%>

<%
	String sheng = (String)session.getAttribute("accountSheng");
	String roleId = (String)session.getAttribute("accountRole");
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
    String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
    String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
    String zdsx = request.getParameter("zdsx")!=null?request.getParameter("zdsx"):"0";
    String sfbg = request.getParameter("sfbg")!=null?request.getParameter("sfbg"):"0";
    String whereStr="";
    String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||(zdsx!=null)||(zdlx!=null)||sfbg!=null){
            canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&zdsx="+zdsx+"&zdlx="+zdlx+"&sfbg="+sfbg;
        }
        String path = request.getContextPath();
        String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
        String permissionRights="";
          
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

   function chaxun(){
     
        document.form1.action=path+"/web/cx/gongdianfangshitongji.jsp";
        document.form1.submit();
       
    }

</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="<%=path%>/images/css.css" type=text/css rel=stylesheet>
<form action="" name="form1" method="POST">
			<div style="float: left">
				<img src="/energy/images/btt.bmp" />
				  <span
					style="color: black; font-weight: bold; font-size: 16; position: absolute; left: 60px; top: 15px;">
					供电方式占比统计
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
         <td width="100%">
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;市：
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
         	
         &nbsp;&nbsp;&nbsp;&nbsp;区、县：
         	
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
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乡镇：
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
         </td>
      </tr>
      <tr>
        <td>
	              集团报表类型：
	           <select name="zdlx" id="zdlx" style="width:130">
         		<option value="0">请选择</option>
	      <%
	         	ArrayList feetypelist = new ArrayList();
	         	feetypelist = commBean.getCommBoxType("ZDLX");
	         	if(feetypelist!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)feetypelist.get(0)).intValue();
	         		for(int i=scount;i<feetypelist.size()-1;i+=scount)
                    {
                    	sfid=(String)feetypelist.get(i+feetypelist.indexOf("CODE"));
                    	sfnm=(String)feetypelist.get(i+feetypelist.indexOf("NAME"));
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
	         </select>
	            
	              站点属性：
	           <select name="zdsx" id="zdsx" style="width:130">
         		<option value="0">请选择</option>
	      <%
	         	ArrayList zdsxlist = new ArrayList();
	         	feetypelist = commBean.getCommBoxType("ZDSX");
	         	if(feetypelist!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)feetypelist.get(0)).intValue();
	         		for(int i=scount;i<feetypelist.size()-1;i+=scount)
                    {
                    	sfid=(String)feetypelist.get(i+feetypelist.indexOf("CODE"));
                    	sfnm=(String)feetypelist.get(i+feetypelist.indexOf("NAME"));
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
	         </select>
	                  是否标杆：
	           <select name="sfbg" id="sfbg" style="width:130">
         		<option value="0">请选择</option>
	            <option value="1">标杆</option>
                <option value="2">非标杆</option>
	        
	         </select>
            <input type="button" name="query1" id="id1" class="memo" value="查询" onclick="chaxun()"  style="color:#014F8A"/>
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
                           <td width="20%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">供电方式</div>
                           <td width="15%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">站点数</div>
                           <td width="15%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">占比数</div>
                           <td width="15%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">自动抄表数</div>
                           <td width="15%" height="23" bgcolor="#888888"><div align="center" style="color:white;font-weight:bold;">自动抄表占比</div>
               
                      </tr>
       <%
            if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and zd.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and zd.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" and zd.xiaoqu='"+xiaoqu+"'";
			}
			if(zdlx != null && zdlx != "" && !zdlx.equals("0")){
				whereStr=whereStr+" and zd.jztype ='"+zdlx+"'";
			}
			if(zdsx != null && zdsx != "" && !zdsx.equals("0")){
				whereStr=whereStr+" and zd.property ='"+zdsx+"'";
			}
			if(sfbg != null && sfbg != "" && !sfbg.equals("0")){
			  if(sfbg.equals("2")){
			     whereStr=whereStr+" and zd.bgsign ='0'";
			  }
			  else{
			  whereStr=whereStr+" and zd.bgsign ='"+sfbg+"'";}
				
			}
         GongDianFangShiTongJi bean = new GongDianFangShiTongJi();
       	 ArrayList fylist = new ArrayList();
       	 if(!whereStr.equals("")){
       	    fylist = bean.getPageData(whereStr);
       	 }
       	 
       	
		String gdfs="";
	    String zds="";
	    String bili="";
	    String zds1="";
	    String bili1="";
		int intnum=0;
		 if(fylist.size()>0)
		{
		   // System.out.println(fylist.toArray().toString());
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
		    gdfs = (String)fylist.get(k+fylist.indexOf("GDFS"));
		    gdfs = gdfs != null ? gdfs : " ";
		    zds = (String)fylist.get(k+fylist.indexOf("ZDS"));
		    zds = zds != "" ? zds : "0";
		    bili = (String)fylist.get(k+fylist.indexOf("ZDBL"));
		    bili = bili != "" ? bili : "0";
		    zds1 = (String)fylist.get(k+fylist.indexOf("ZDS1"));
		    zds1 = zds1 != "" ? zds1 : "0";
		    bili1 = (String)fylist.get(k+fylist.indexOf("ZDBL1"));
		    bili1 = bili1 != "" ? bili1 : "0";
		    DecimalFormat mat=new DecimalFormat("0.00");
			bili=mat.format(Double.parseDouble(bili));
			bili1=mat.format(Double.parseDouble(bili1));
			
			
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
       			<div align="center" ><%=gdfs%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zds%></div>
       		</td>
       		<td>
       			<div align="center" ><%=bili%>%</div>
       		</td>
       		<td>
       			<div align="center" ><%=zds1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=bili1%>%</div>
       		</td>
       		
       </tr>
       <%} }%>
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
                             
                           </td>
  
  </tr>
    </table></td>
  </tr>
   
  <tr>
  <input type="hidden" name="sheng2" id="sheng2" value=""/>
  <input type="hidden" name="shi2" id="shi2" value=""/>
  <input type="hidden" name="xian2" id="xian2" value=""/>
    <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
  </tr>

</table>
</form>
</body>
</html>

<script language="javascript">
	var path = '<%=path%>';
	function changeShi(){
		var shi = document.form1.shi.value;
		if(shi=="0"){
			var shilist = document.all.xian;
			shilist.options.length="0";
			shilist.add(new Option("请选项","0"));
			return;
		}else{
		   // alert(path+shi);
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
		document.form1.zdsx.value='<%=zdsx%>';
		document.form1.zdlx.value='<%=zdlx%>';
		document.form1.sfbg.value='<%=sfbg%>';
		
		
</script>