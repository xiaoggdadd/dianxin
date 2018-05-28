<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%

	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
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
    String beginTimeQ = request.getParameter("beginTimeQ")!=null?request.getParameter("beginTimeQ"):"";
    String endTimeQ = request.getParameter("endTimeQ")!=null?request.getParameter("endTimeQ"):"";
    String zdbh = request.getParameter("zdbh")!=null?request.getParameter("zdbh"):"";
    String beginTime1 = request.getParameter("beginTime1") != null ? request.getParameter("beginTime1"): "";
  	String endTime1 = request.getParameter("endTime1") != null ? request.getParameter("endTime1") : "";
	String accountmonth = request.getParameter("accountmonth") != null ? request.getParameter("accountmonth") : "";
	String zdqyzt = request.getParameter("zdqyzt") != null ? request.getParameter("zdqyzt") : "1";    
     //添加站点类型
    String stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
           String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((beginTime!=null)&&(endTime!=null))||(zdbh!=null)||(stationtype1!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&beginTimeQ="+beginTimeQ+"&endTimeQ="+endTimeQ+"&zdbh="+zdbh+"&stationtype1="+stationtype1;
     }
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    String whereStr = (String)request.getAttribute("whereStr");
    String str = (String)request.getAttribute("str");
    int intnum = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;
    String color = "";//颜色
%>

<html>
<head>
<title>

</title>
<style>   
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
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
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}

.bttcn{color:BLACK;font-weight:bold;}

.form    {width:130px}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>

<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
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
	function adddegree(){
	var loginId = '<%=loginId%>';
	var path = '<%=path%>';
	var shi = document.form1.shi.value;	
	if (0 == shi) {
		alert("城市不能为空");
	} else {
		document.form1.action = path + "/servlet/ElecBillServlet?loginId="+loginId+"&action=zdName";
		document.form1.submit();
		
	}
}

	
    function delad(electricfeeId){
       if(confirm("您确定删除此电费信息？")){
                    document.form1.action=path+"/servlet/electricfees?action=delete1&deladfee=newdelete&degreeid="+electricfeeId
        			document.form1.submit();
        			 showdiv("请稍等..........");
       }
    }
    function modifyad(electricfeeId){
                    document.form1.action=path+"/servlet/ElecModifyServlet?action=getInfo&degreeid="+electricfeeId;
                    document.form1.submit();
                     showdiv("请稍等..........");
       
    }
    
    function queryDegree(action){
    	
    	var command=action;
        if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
	             alert("城市不能为空");
	   	}else{
	   		   var comm = document.getElementById("command");
	   		   comm.value = command;
               document.form1.action=path+"/servlet/ElecQueryServlet";
               document.form1.submit();
       		}
       
    }
    //选择所有按钮
     function chooseAll() {   
        var qm = document.getElementsByName('test');
        var m = document.getElementsByName('test[]');   
        var l = m.length; 
        if(qm[0].checked == true){
          for (var i = 0; i < l; i++) {   
            m[i].checked = true;   
          }  
        }else{
          for (var i = 0; i < l; i++) {   
            m[i].checked = false;  
          }   
        }
    }  
     //点击批量删除按钮
     function deletes(){
        var m = document.getElementsByName('test[]');   
        var l = m.length; 
        var chooseIdStr = ""; 
        var bz=0;
        for (var i = 0; i < l; i++) {  
          if(m[i].checked == true){
             bz=bz+1;
             chooseIdStr = chooseIdStr + m[i].value +","; 
          }               
        } 
        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
       
        if(bz>0){
        	if(bz<=50){
	           document.form1.action=path+"/servlet/electricfees?action=deletes&biaozhi=xin&chooseIdStr="+chooseIdStr;
	           document.form1.submit();
	           showdiv("请稍等..........");
           }else{
           	   alert("请选择删除的信息不要超过50条！");
           }
        }else{
          alert("请选择信息！");
        }
      }
      //批量导入
  function exportFData(){
          document.form1.action=path+"/web/appJSP/electricmanage/electricitybill/inputEleBill.jsp";
                                document.form1.submit();
  }
  $(function(){
		$("#daoru").click(function(){
			exportFData();
		});
		$("#zengjia").click(function(){
			adddegree();
	
		});
		$("#daochu").click(function(){
			queryDegree("daochu");
		});
		$("#deletes").click(function(){
			deletes();
		});

		$("#chaxun").click(function(){
			   if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null)
		          {
	                   alert("城市不能为空");
	   
	           }else{
			queryDegree("chaxun");
			showdiv("请稍等..............");
			}
		});
	});
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
</script>
<script src="<%=path%>/javascript/wait.js"></script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0302");


%>
<body  class="body" >	
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" >
	      <tr>
           <td width="50" >
             <div style="width:700px;height:50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电费单管理</span>
	         </div></td>
		</tr>
		<tr><td height="20" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
             </td>
            </tr>

  		 <tr>
  		   <td width="1200px">
  		   <table>	
  		     <tr class="form_label">
               <td >城市：</td>      
                <td><select name="shi" class="selected_font" id="shi" onchange="changeCity()">
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


                 <td>区县：</td>
                 <td><select name="xian" class="selected_font" onchange="changeCountry()">
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
                 </td>
                 <td>乡镇：</td>
                 <td><select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
		                          <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>    
                                   <div id="chaxun" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
		                            <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                           <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
		                           </div>
        <%}%>
			      </td>
			      </table>
			      </td>
			      </tr>
                  
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
					 <td>站点类型：</td>
					 <td>
					    <select name="stationtype"  class="selected_font" onchange="kzinfo()">
					    <option value="0">请选择</option>
         		        <%
	         	ArrayList stationtype = new ArrayList();
         		stationtype = commBean.getSelctOptions("StationType");
	         	if(stationtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)stationtype.get(0)).intValue();
	         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
                    {
                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
	                   </select>
					 </td>
                     <td>上次抄表时间：</td>
                     <td>
                       <input type="text" name="beginTimeQ" class="selected_font" value="<%if(null!=request.getParameter("beginTimeQ")) out.print(request.getParameter("beginTimeQ")); %>" 
                       			readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
                     </td>
                     <td>本次抄表时间：</td>
                     <td>
                       <input type="text" name="endTimeQ" class="selected_font" value="<%if(null!=request.getParameter("endTimeQ")) out.print(request.getParameter("endTimeQ")); %>" 
                       			readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
                     </td>
                  </tr>
                  <tr class="form_label">
                  <td>起始月份：</td>
                  <td>
                     <input type="text" name="beginTime1" class="selected_font" value="<%if (null != request.getParameter("beginTime1")) out.print(request.getParameter("beginTime1"));%>" 
                     		readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
                  </td>
                  <td>结束月份：</td>
                  <td>
                     <input type="text" name="endTime1" class="selected_font" value="<%if (null != request.getParameter("endTime1")) out.print(request.getParameter("endTime1"));%>" 
                     		readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
                  </td>
                   <td>报账月份：</td>
                  <td>
                     <input type="text" name="accountmonth" class="selected_font" value="<%if (null != request.getParameter("accountmonth")) out.print(request.getParameter("accountmonth"));%>" 
                     		readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
                  </td>
                  <td>站点启用状态</td>
                  <td>
                  	<select  name="zdqyzt"    class="selected_font">
                  		<option value="-1">请选择</option>
                  		<option value="1">启用</option>
                  		<option value="0">未启用</option>
                  	</select>
                  </td>
                  </tr>
                  
                 </table> 
 
 </p>
 </div></td>
 </tr></table>

  <table height="23">     
     <tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
      <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                     </div></td></tr>
  </table>


 	
  <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >  	
			    <table width="100%" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                  <tr height = "10" class="relativeTag">
                      <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
                      <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
                      <td width="11%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
                      <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                      <td width="6%" height="15" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次单价</div></td>
            		  <td width="8%" height="15" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电费用调整</div></td>
                      <td width="8%" height="18" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际电费金额</div></td>
                      
                      <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据类型</div></td>
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据编号</div></td>
                      <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交费操作员</div></td>
                      <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交费时间</div></td>
                      <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点编号</div></td>
                      <td width="5%" height="23" bgcolor="#DDDDDD" colspan="2"><div align="center" class="bttcn">操作</div></td>
                  </tr>
					<c:forEach items="${list}"  var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       						<td><div align="center" >${status.count}</div></td> 
       						<td>
             				 <div align="center"><input type="checkbox" name="test[]" value="${list.ammeterdegreeid}" /></div>
           					</td>
				           	<td>
				       			<div align="left" >${list.area}</div>
				       		</td>
				       		<td>
				       			<div align="left" >${list.jzname}</div>
				       		</td>
				       		<td>
				       			<div align="right" >${list.unitprice}</div>
				       		</td>
				       		<td>
				       			<div align="right" >${list.floatpay}</div>
				       		</td>          
				            <td>
				       			<div align="right" >${list.actualpay}</div>
				       		</td>
				       		<td>
				       			<div align="center" >${list.stationtype}</div>
				       		</td>
				            <td>
				       			<div align="center" >${list.notetypeid}</div>
				       		</td>
				       		 <td>
				       			<div align="center" >${list.noteno}</div>
				       		</td>
				       		<td>
				       			<div align="center" >${list.payoperator}</div>
				       		</td>
				       		<td>
				       			<div align="center" >${list.paydatetime}</div>
				       		</td>
				       		<td>
				       			<div align="left" >${list.zdcode}</div>
				       		</td>
				       		<c:if test="${list.manualauditstatus eq '1'}">
					           <td>
					       			<div align="center" ></div>
					       		</td>
					       		<td>
					       			<div align="center" ></div>
					       		</td>  
					           </c:if>
					        <c:if test="${list.manualauditstatus != '1'}">
					       		<td>
					       			<div align="center" ><a href="javascript:modifyad('${list.electricfeeId}')" >修改</a></div>
					       		</td>
					       		<td>
					       		  <%if(permissionRights.indexOf("PERMISSION_DELETE")>=0){%>
					       			<div align="center" ><a href="javascript:delad('${list.electricfeeId}')" >删除</a></div>
					       		  <%}%>
					       		</td>                   		
					         </c:if>
    					</tr>
					</c:forEach>
       
        <% if (intnum==0){
         for(int i=0;i<17;i++){
          if(i%2==0){
			    color="#FFFFFF" ;
          }else{
			    color="#DDDDDD";
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
            </tr>
      <% }}else if(!(intnum > 16)){
    	  for(int i=((intnum-1)%16);i<16;i++){
            if(i%2==0)
			    color="#DDDDDD";
            else
			    color="#FFFFFF" ;
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
        </tr>
        <% }}%>
      </table>
      </div>
      
       
  

  	<div style="width:100%;height:8%;border:1px" >	
	   <table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
         <tr >
            <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
               <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
            </div></td>
          </tr>
          <tr>
             <td align="right"> <%if(permissionRights.indexOf("PERMISSION_ADD")>=0){%> 	
                                      							
				<div id="daoru" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px; ">
		         <img alt="" src="<%=path %>/images/daoru.png" width="100%" height="100%" />
		         <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导入</span>
				</div>
               <%}%> 
                            
                               <%if(permissionRights.indexOf("PERMISSION_ADD")>=0){%>    
                               <div id="zengjia" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:6px; ">
		                            <img alt="" src="<%=path %>/images/xinzeng.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">增加</span>
								</div>
                              
                               <%}%> 
                               
                               <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                        
                               <div id="daochu" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:9px; ">
		                            <img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">导出</span>
								</div>
                               <%}%> 
 							  <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                        
                               <div id="deletes" style="width:76px;height:23px;cursor:pointer;float:right;position:relative;right:11px; ">
		                            <img alt="" src="<%=path %>/images/quxiao.png" width="100%" height="100%" />
		                            <span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">批量删除</span>
								</div>
                               <%}%> 

                           </td>
         </tr>
         <tr>
           <td>
           <input type="hidden" name="sheng2" id="sheng2" value=""/>
           <input type="hidden" name="shi2" id="shi2" value=""/>
           <input type="hidden" name="xian2" id="xian2" value=""/>
           <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
          <input type="hidden" name="command" id="command" value=""/>
           </td>
          
         </tr>
</table>
</div>

</form>
</body>
</html>

<script type="text/javascript">
<!--
var XMLHttpReq;
	 
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
		  
		XMLHttpReq.send(null);  
	}
	 
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		 
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	 
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
                updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		 
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	 
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateQx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		 
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	 
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

//-->
</script>
<script language="javascript">
var path='<%=path%>';
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
 
   function exportModel(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=whereStr%>";
            document.form1.action=path+"/servlet/download?action=download&templetname="+encodeURIComponent("电量电费批量导入模板");
            document.form1.submit();
   }
   
   		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.stationtype.value='<%=stationtype1%>';
		document.form1.zdqyzt.value='<%=zdqyzt%>';
 </script>