<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.ptac.app.print.printpaperbill.bean.QueryPaperBill" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%
	String path = request.getContextPath();//项目路径
	Account account = (Account)session.getAttribute("account");//账户
	String sheng = (String)session.getAttribute("accountSheng");//省
	//省市县三级联动参数
	String agcode1="";
	if(request.getParameter("shi")==null){
		List shilist = new ArrayList();
		CommonBean commBean = new CommonBean();
		shilist = commBean.getAgcode(sheng,account.getAccountId());
		if(shilist!=null){
			int scount = ((Integer)shilist.get(0)).intValue();
	    	agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
	 	}
	}    
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    String dybzw="";
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
		
        String bztime = request.getParameter("bztime")!=null?request.getParameter("bztime"):"";
        String stationtype = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
        String liuch = request.getParameter("liuch")!=null?request.getParameter("liuch"):"";
        String dfzflx = request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";
        
        String zdmc = request.getParameter("zdmc")!=null?request.getParameter("zdmc"):"";
        String zdsx =request.getParameter("zdsx")!=null?request.getParameter("zdsx"):"0";
         String lururen =request.getParameter("lururen")!=null?request.getParameter("lururen"):"";
         String pjlx1 =request.getParameter("pjlx")!=null?request.getParameter("pjlx"):"";
         
           String canshuStr="",color="";
     
    //String roleId = account.getRoleId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
%>

<html>
<head>
<title>
打印纸质单据
</title>
<style>
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: right;
			font-family: 微软雅黑;
			font-size: 12px;
			height:23px
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
 .memo {border: 1px #C8E1FB solid}
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

.bttcn{color:BLACK;font-weight:bold;}

.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.form_la{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}
</style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/wait.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script>

var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();

var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChsny.oBtnTodayTitle = "确定";
oCalendarChsny.oBtnCancelTitle = "取消";
oCalendarChsny.Init();
</script>
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
    function queryDegree(){
                   document.form1.action = path+"/servlet/QueryPaperBillServlet?command=chaxun";
                   document.form1.submit();
                   showdiv("请稍等..........");
    }
        function exportad(){
                   document.form1.action = path+"/servlet/QueryPaperBillServlet?command=daochu";
                   document.form1.submit();
    }
    
    
    function checkDF(){
         	if(document.form1.DFCheck.checked){
         		for(var j = 0;j < document.form1.elements.length ; j++){
							document.form1.elements[j].checked=true
						}
         	}else{
         		for(var j = 0;j < document.form1.elements.length ; j++){
							document.form1.elements[j].checked=false
						}
         	}
        }
    $(function(){

$("#daochu").click(function(){
				var checkShi = document.getElementById("shi").value;
			var checkLiuch = document.getElementById("liuch").value;
 			if(checkShi==""||checkShi=="0"||checkShi==null){
	            alert("城市 不能为空!");
	        }else if(checkLiuch==""||checkLiuch==null){
	        	alert("流程号 不能为空!");
	        }else{
				exportad();
			}
		});

		$("#dayin").click(function(){
			dayin();
			showdiv("请稍等..............");
		});
		$("#qxdayin").click(function(){
			qxdayin();
			//showdiv("请稍等..............");
		});
		$("#chaxun").click(function(){
			var checkShi = document.getElementById("shi").value;
			var checkLiuch = document.getElementById("liuch").value;
 			if(checkShi==""||checkShi=="0"||checkShi==null){
	            alert("城市 不能为空!");
	        }else if(checkLiuch==""||checkLiuch==null){
	        	alert("流程号 不能为空!");
	        }else{
				queryDegree();
			}
		});
	});
</script>

</head>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">   
</jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0302");

%>
<body  class="body" >
	<form action="" name="form1" method="POST">
			<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			        <tr >
					   <td colspan="4" width="50" >
						   <div style="width:700px;height:50px">
							  
						 		<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">打印纸质单据</span>
						    </div>
						</td>
	    	        </tr>	  
	    	        <tr><td height="20" colspan="4" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td>
	    	</tr>
   		  	<tr>
   		  	  <td width="1400px">
				<table >
					<tr class="form_label">
		    		<td >城市:</td>
                    <td><select name="shi" id="shi" class="selected_font" onchange="changeCity()">
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
         	          </select><span class="style1">&nbsp;*</span></td>
         	                            
                     	<td>区县:</td>          
                     			<td> 
                     				<select name="xian" class="selected_font" onchange="changeCountry()">
         		                         <option value="0">请选择</option>
         		 						<%
		         							ArrayList xianlist = new ArrayList();
		         							xianlist = commBean.getAgcode(shi,account.getAccountId());
		         							if(xianlist != null){
		         								String agcode = "",agname = "";
		         								int scount = ((Integer)xianlist.get(0)).intValue();
		         								int size = xianlist.size()-1;
		         								int i;
		         								for(i = scount;i < size;i += scount){
	                    							agcode = (String)xianlist.get(i + xianlist.indexOf("AGCODE"));
	                    							agname = (String)xianlist.get(i + xianlist.indexOf("AGNAME"));
	                    				%>
	                                     <option value="<%=agcode%>"><%=agname%></option>
	                    				<%		}
		         							}
		         						%>
         	                           </select>   
								</td>     
								<td> 乡镇:</td>         
								<td>
									<select name="xiaoqu" class="selected_font">
         		                    	<option value="0">请选择</option>
         								<%
		         							ArrayList xiaoqulist = new ArrayList();
		         							xiaoqulist = commBean.getAgcode(xian,account.getAccountId());
		         							if(xiaoqulist!=null){
		         								String agcode = "",agname = "";
		         								int scount = ((Integer)xiaoqulist.get(0)).intValue();
		         								int size = xiaoqulist.size()-1;
		         								int i;
		         								for(i = scount;i < size;i += scount){
	                    							agcode = (String)xiaoqulist.get(i + xiaoqulist.indexOf("AGCODE"));
	                    							agname = (String)xiaoqulist.get(i + xiaoqulist.indexOf("AGNAME"));
	                    				%>
	                                     <option value="<%=agcode%>"><%=agname%></option>
	                    				<%
	                   							}
		         							}
		         						%>
									</select>
								</td>
								
								
		         	       <td> 流程号:</td>
		                  <td><input type="text" name="liuch" value="<%if(null!=request.getParameter("liuch")) out.print(request.getParameter("liuch")); %>" class="selected_font"/><span class="style1">&nbsp;*</span></td>
		                  
                  
         	              <td >
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p>
				</td>
				  <td ><%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
                                 <div id="chaxun" style="position:relative;width:59px;height:23px;right:-50px;cursor: pointer;TOP:0PX">
			                        <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
			                        <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                        </div>      <%}%>
		                        
                  </td>
                 </tr>	
              </table>
              </td></tr>
			   
			   
			   <tr>
			      <td colspan="5">
					<div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table>
					  <tr class="form_label">
	         		   <td>报账月份:</td>
                        <td> <input type="text" class="selected_font" name="bztime" value="<%if (null != request.getParameter("bztime"))out.print(request.getParameter("bztime"));%>"
		                         onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
                        </td> 
                        <td>站点类型:</td>
                        <td><select name="zdlx" class="selected_font" > 
                                    <option value="0">请选择</option>
          <%
          ArrayList stationtype1 = new ArrayList();
         		stationtype1 = ztcommon.getSelctOptions("StationType");
	         	if(stationtype1!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)stationtype1.get(0)).intValue();
	         		for(int i=cscount;i<stationtype1.size()-1;i+=cscount)
                    {
                    	code=(String)stationtype1.get(i+stationtype1.indexOf("CODE"));
                    	name=(String)stationtype1.get(i+stationtype1.indexOf("NAME"));
                    %>
                                   <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
                                </select></td>
                  <td>电费支付类型:</td>
                  <td><select name="dfzflx" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList dfzflx1 = new ArrayList();
         		dfzflx1 = ztcommon.getSelctOptions("dfzflx");
	         	if(dfzflx1!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)dfzflx1.get(0)).intValue();
	         		for(int i=cscount;i<dfzflx1.size()-1;i+=cscount)
                    {
                    	code=(String)dfzflx1.get(i+dfzflx1.indexOf("CODE"));
                    	name=(String)dfzflx1.get(i+dfzflx1.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>  
         	</td>
           </tr>
           <tr class="form_label">
              <td>站点名称:</td>
				<td><input type="text" name="zdmc" class="selected_font" value="<%if(null!=request.getParameter("zdmc")) out.print(request.getParameter("zdmc")); %>" /></td>
              <td>站点属性:</td>
              <td><select name="zdsx" class="selected_font" > 
                      <option value="0">请选择</option>
              <%
                ArrayList listzdsx = new ArrayList();
                listzdsx = ztcommon.getSelctOptions("zdsx");
         		
	         	if(listzdsx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)listzdsx.get(0)).intValue();
	         		for(int i=cscount;i<listzdsx.size()-1;i+=cscount)
                    {
                    	code=(String)listzdsx.get(i+listzdsx.indexOf("CODE"));
                    	name=(String)listzdsx.get(i+listzdsx.indexOf("NAME"));
                    %>
                                   <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
                                </select></td>
               
           		<td>票据类型:</td>
           		<td><select name="pjlx" class="selected_font" > 
                      <option value="0">请选择</option>
              <%
                ArrayList listpjlx = new ArrayList();
              listpjlx = ztcommon.getSelctOptions("pjlx");
         		
	         	if(listpjlx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)listpjlx.get(0)).intValue();
	         		for(int i=cscount;i<listpjlx.size()-1;i+=cscount)
                    {
                    	code=(String)listpjlx.get(i+listpjlx.indexOf("CODE"));
                    	name=(String)listpjlx.get(i+listpjlx.indexOf("NAME"));
                    %>
                                   <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
             </select></td>
             
              <td>录入人:</td>
                <td><input type="text" name="lururen"  value="<%if(null!=request.getParameter("lururen"))out.print(request.getParameter("lururen"));%>" class="selected_font"/></td>
             
             
           </tr>
         </table>
	    </p>
		</div></td></tr></table>
	
	        <table  height=23>
				<tr>
					<td height="20"  colspan="4">
						<div id="parent" style="display:inline">
                     		<div style="width:50px;display:inline;"><hr></div>
                     		<font size="2">&nbsp;信息列表&nbsp;</font>
                     		<div style="width:300px;display:inline;"><hr></div>
                		</div>
                	</td>
               	</tr>
			</table>
				
			<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;" > 		
				<table width="1400px" height="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                  	<tr height = "23" class="relativeTag">            
                      <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="DFCheck" onClick="checkDF()" /></div> </td>
                      <td  width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td> 
                       <td width="9%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td> 
                       <td  width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                       <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
            			<td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td>
                        <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
                        <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次读数</div></td>
                        <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次读数</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">耗电量</div></td>
                         
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费单价</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实付费用</div></td>
                          <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">支付类型</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据类型</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据金额</div></td>
                         
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期电费</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期电量</div></td>
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期单价</div></td>
                         
                         <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">备注</div></td>
                      </tr>
       <%
         
        
			
		List<QueryPaperBill> fylist = new ArrayList<QueryPaperBill>();	
        fylist = (List)request.getAttribute("list");

        
        
        
		String electricfeeId = "",unitprice = "",actualpay = "";
		String jzname = "",df="",szdq="",jztype="",inputdatetime="",inputoperator="",lastdatetime="",thisdatetime="",uuid="",
		accountmonth="",lastdegree="",thisdegree="",actualdegree="",fffs="",memo="",pjlx="",pjje="",zt="";
		//2014-4-15新增--上期电费-上期电量-上期单价---
		String lastelecfees = "", lastelecdegree = "", lastunitprice = "";
		int intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		int jnum=1;
		Double dianfei=0.0;	
		 if(fylist!=null)
		{
			//int fycount = ((Integer)fylist.get(0)).intValue();
			for(QueryPaperBill bean1:fylist){
              linenum++;
		     //num为序号，不同页中序号是连续的
		    jzname = bean1.getJzname();
		    jzname = jzname != null ? jzname : "";
		    	
		    szdq = bean1.getSzdq();	
		    szdq = szdq != null ? szdq : "";
		     
		    electricfeeId = bean1.getElectricfeeId();	
		    electricfeeId = electricfeeId != null ? electricfeeId : "";
		    
		 	uuid = bean1.getDfuuid();	
		    uuid = uuid != null ? uuid : "";
		    			    
			jztype = bean1.getJztype();
			jztype = jztype != null ? jztype : "";			
		    
		    lastdatetime = bean1.getLastdatetime();
		    lastdatetime = lastdatetime != null ? lastdatetime : "";
		    
			thisdatetime = bean1.getThisdatetime();
			thisdatetime = thisdatetime != null ? thisdatetime : "";
						
			accountmonth = bean1.getAccountmonth();
			accountmonth = accountmonth != null ? accountmonth : "";
			
		    lastdegree = bean1.getLastdegree();
		    lastdegree = lastdegree != null ? lastdegree : "0";
		    
		    thisdegree = bean1.getThisdegree();
		    thisdegree = thisdegree != null ? thisdegree : "0";
		    
		    actualdegree = bean1.getBlhdl();
		    actualdegree = actualdegree != null ? actualdegree : "0";

		    unitprice = bean1.getUnitprice();
		    unitprice = unitprice != null ? unitprice : "0";

		    actualpay = bean1.getActualpay();
		    actualpay = actualpay != null ? actualpay : "0";
		    
		    fffs = bean1.getDfzflx();
		    fffs = fffs != null ? fffs : "";
		    
		    memo = bean1.getMemo();
		    memo = memo != null ? memo : "";
		    
		    lastelecfees = bean1.getLastelecfees();
		    lastelecfees = lastelecfees != null ? lastelecfees : "";
		    
		    lastelecdegree = bean1.getLastelecdegree();
		    lastelecdegree = lastelecdegree != null ? lastelecdegree : "";
		    
		    lastunitprice = bean1.getLastunitprice();
		    lastunitprice = lastunitprice != null ? lastunitprice : "";
		    
		    pjlx = bean1.getNotetypeid();
		    pjlx = pjlx != null ? pjlx : "";
		    pjje = bean1.getPjjedy();
		    pjje = pjje != null ? pjje : "0";
		    
		    zt=bean1.getManualauditstatus();
		    if(null==pjje||pjje.equals("")||pjje.equals(" ")||pjje.equals("null"))pjje="0";
		    if(null==actualpay||actualpay.equals("")||actualpay.equals(" ")||actualpay.equals("null"))actualpay="0";
		    if(null==actualdegree||actualdegree.equals("")||actualdegree.equals(" ")||actualdegree.equals("null"))actualdegree="0";
		    if(null==lastdegree||lastdegree.equals("")||lastdegree.equals(" ")||lastdegree.equals("null"))lastdegree="0";
		    if(null==thisdegree||thisdegree.equals("")||thisdegree.equals(" ")||thisdegree.equals("null"))thisdegree="0";
		    if(null==unitprice||unitprice.equals("")||unitprice.equals(" ")||unitprice.equals("null"))unitprice="0";
		    
            DecimalFormat mat =new DecimalFormat("0.00");
            pjje=mat.format(Double.parseDouble(pjje));
            actualpay=mat.format(Double.parseDouble(actualpay));
            
            DecimalFormat dlmat=new DecimalFormat("0.0");
            actualdegree=dlmat.format(Double.parseDouble(actualdegree));
            lastdegree=dlmat.format(Double.parseDouble(lastdegree));
            thisdegree=dlmat.format(Double.parseDouble(thisdegree));
            
            DecimalFormat price=new DecimalFormat("0.0000");
            unitprice = price.format(Double.parseDouble(unitprice));
            
          
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
          intnum++;

       %>
       <tr bgcolor="<%=color%>">
            <td>
       			<div align="center" ><input type="checkbox" name="itemSelected" value="<%=uuid%>ssss=<%=fffs%>" /></div>
       		</td>
       		<td>
       			<div align="center" ><%=jnum++%></div>
       		</td>
            <td>
       			<div align="left" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>   
       		<td>
       			<div align="center" ><%=jztype%></div>
       		</td>          
            <td>
       			<div align="center" ><%=lastdatetime%></div>
       		</td>
            <td>
       			<div align="center" ><%=thisdatetime%></div>
       		</td>
       		
       		<td>
       			<div align="right" ><%=lastdegree%></div>
       		</td>
       		<td>
       			<div align="right" ><%=thisdegree%></div>
       		</td>
       		
       		 <td>
       			<div align="right" ><%=actualdegree%></div>
       		</td>
       		
       		<td>
       			<div align="right" ><%=unitprice%></div>
       		</td>
       		  <td>
       			<div align="right" ><%=actualpay%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=accountmonth%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=fffs%></div>
       		</td>
       		<td>
       			<div align="center" ><%=pjlx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=pjje%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=lastelecfees%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lastelecdegree%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lastunitprice%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=memo%></div>
       		</td>
         
       </tr>
       <%}}%>
       
 <% if (intnum==0){
         for(int i=0;i<13;i++){
          if(i%2==0){
			    color="#DDDDDD" ;
          }else{
			    color="#FFFFFF";
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
        <% }}else if(!(intnum > 12)){
    	  for(int i=((intnum-1)%12);i<12;i++){
            if(i%2==0)
			    color="#FFFFFF" ;
            else
			    color="#DDDDDD";
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
        <% }}%>
  	 </table> 
  	 </div>
  	 

      <div style="width:100%;height:8%;border:1px" >	
			<table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
               		<div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
          		</div></td>
			</tr>
 
  		<tr>  		
   			<td align="right">  
   			 <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                        
                               <div id="daochu" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:6px; ">
		                            <img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">导出</span>
								</div>
                               <%}%>  						
                  <%if(permissionRights.indexOf("PERMISSION_PRINT")>=0){%> 
				   <div id="dayin" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:9px; ">
		              <img alt="" src="<%=path %>/images/dayin.png" width="100%" height="100%" />
		                  <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">打印</span>
					</div>
                      <%}%>
                  
            </td>
  		</tr>
  <tr>
  <td>
  <input type="hidden" name="loginId" id="loginId" value="<%=loginId%>"/>
  </td></tr>
  
</table>
</div>
</form>
</body>
</html>
<script language="javascript">
	var path = '<%=path%>';
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
 function dayin(){
	 var shi='<%=shi%>';
	var  dayin='<%=dybzw%>';
       var m = document.getElementsByName('itemSelected');  
       var lch=document.form1.liuch.value;
       var l = m.length; 
       var chooseIdStr1 = ""; 
       var chooseIdStr2 = ""; 
       var bz=0;        
       for (var i = 0; i < l; i++) {  
       if(m[i].checked == true){
             bz+=1;
            var j = m[i].value.toString().indexOf("ssss=");//截取电费支付类型
            var chooseIdStr3 = m[i].value.toString().substring(0,j);
            var zflx1 = m[i].value.toString().substring(j+5,m[i].value.toString().length);
            if(zflx1=="月结"||zflx1=="预支"){
             chooseIdStr1 = chooseIdStr1 + "'"+chooseIdStr3 +"',";
            }else if(zflx1=="合同"||zflx1=="插卡"){
            	 chooseIdStr2 = chooseIdStr2 +"'"+ chooseIdStr3 +"',";
            } 
         }               
       } 
       chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
       chooseIdStr2=chooseIdStr2.substring(0,chooseIdStr2.length-1);
    if(bz<=240){
        if(bz>=1){
        if(dayin=="1"){
        lch="";
        }
           document.form1.action=path+"/servlet/PrintPaperBillServlet?chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2+"&lch="+lch+"&shi="+shi+"&dayin="+dayin;
           document.form1.submit(); 
       	}else{
			alert("请选择信息！");
        }
    }else{
      	alert("您选择信息条数超过240条，信息量过大，请确定后重新执行！");
    }        	
  }
</script>


	<script type="text/javascript">
		var path = '<%=path%>';
		var XMLHttpReq;
		//选择浏览器
		function createXMLHttpRequest(){
			if(window.XMLHttpRequest){ 
				XMLHttpReq = new XMLHttpRequest();
			}else if (window.ActiveXObject){ 
				try{
					XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
				}catch(e){
					try {
						XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
					}catch(e){
					}
				}
			}
		}
		//指定响应函数
		function sendRequest(url,para){
			createXMLHttpRequest();
			XMLHttpReq.open("GET", url, false);
			if(para=="sheng"){
				XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
			}else if(para=="shi"){
				XMLHttpReq.onreadystatechange = processResponse_shi;
			}else if(para=="xian"){
				XMLHttpReq.onreadystatechange = processResponse_xian;
			}else{
				XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
			}
		  
			XMLHttpReq.send(null);  
		}
	
		// 处理返回信息函数
    	function processResponse(){
    		if (XMLHttpReq.readyState == 4){ // 判断对象状态
        		if (XMLHttpReq.status == 200){ // 信息已经成功返回，开始处理信息
            		var res = XMLHttpReq.responseText;
              		window.alert(res);
            	}else{//页面不正常
                	window.alert("您所请求的页面有异常。");
            	}
        	}
    	}
    	//改变省的响应，更新市
		function processResponse_sheng() {
    		if (XMLHttpReq.readyState == 4){ // 判断对象状态
        		if (XMLHttpReq.status == 200){ // 信息已经成功返回，开始处理信息
            		var res=XMLHttpReq.responseXML.getElementsByTagName("res");
             		 updateShi(res);        
            	}else{ //页面不正常
                	window.alert("您所请求的页面有异常。");
           	 	}
        	}
   	 	}
		//改变市的响应，更新区县
		function processResponse_shi(){
			if(XMLHttpReq.readyState == 4){ // 判断对象状态
    			if(XMLHttpReq.status == 200){ // 信息已经成功返回，开始处理信息
    				
        			var res=XMLHttpReq.responseXML.getElementsByTagName("res");
    			
          			updateQx(res);
        		}else{//页面不正常
            		window.alert("您所请求的页面有异常。");
        		}
    		}
		}
		//改变区县的响应，更新乡镇
		function processResponse_xian(){
			if(XMLHttpReq.readyState == 4){ // 判断对象状态
    			if(XMLHttpReq.status == 200){// 信息已经成功返回，开始处理信息
        			var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          			updateZd(res);
        		}else{//页面不正常
            		window.alert("您所请求的页面有异常。");
        		}
    		}
		}

		//改变省
		function changeSheng(){
			var sid = document.form1.sheng.value;
			if(sid=="0"){
				var shilist = document.all.shi;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");
			}
		}
		//更新市
		function updateShi(res){
			var shilist = document.all.shi;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		//改变城市
		function changeCity(){
			var sid = document.form1.shi.value;
			if(sid=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
			}
		}
		//更新区县
		function updateQx(res){
			var shilist = document.all.xian;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		//改变区县
		function changeCountry(){
			var sid = document.form1.xian.value;
			if(sid=="0"){
				var shilist = document.all.xiaoqu;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
			}
		}
		//更新乡镇
		function updateZd(res){
			var shilist = document.all.xiaoqu;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}

		var path='<%=path%>';
   		document.form1.shi.value='<%=shi%>';

		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		
		document.form1.bztime.value='<%=bztime%>';
		document.form1.zdlx.value='<%=stationtype%>';
		document.form1.liuch.value='<%=liuch%>';
		document.form1.dfzflx.value='<%=dfzflx%>';
		
		document.form1.zdmc.value='<%=zdmc%>';
		document.form1.zdsx.value='<%=zdsx%>';
		document.form1.lururen.value='<%=lururen%>';
		document.form1.pjlx.value='<%=pjlx1%>';
</script>
<script type="text/javascript">
//站点属性
function zdsxCheck(obj) {
	var sid = obj;
	if (sid == "0") {
		var shilist = document.all.zdlx;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest2(path + "/servlet/garea?action=zdsx&pid=" + sid,
				"jzproperty");
	}

}

function sendRequest2(url, para) {

	createXMLHttpRequest();

	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数
	XMLHttpReq.send(null);
}

function processResponse_zdlx() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var res = XMLHttpReq.responseXML.getElementsByTagName("res");
			updateZdlx(res);
		} else { //页面不正常
			window.alert("您所请求的页面有异常。");
		}
	}

}

function updateZdlx(res) {

	var shilist = document.all.zdlx;
	shilist.options.length = "0";
	shilist.add(new Option("请选项", "0"));

	for ( var i = 0; i < res.length; i += 2) {
		shilist.add(new Option(res[i + 1].firstChild.data,
				res[i].firstChild.data));
	}
}
</script>
</html>
