<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
	String permissionRights="";
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
   	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;//市
	String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";//县
	String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";//乡镇
	String zdlx = request.getParameter("stationtype") != null ? request.getParameter("stationtype") : "0";//站点类型
	String roleId = (String)session.getAttribute("accountRole");
	int intnum = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;
    String color = "";//颜色
%>
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
 </style>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<script language="javascript">
	 var path = '<%=path%>';
	 //增加
	function adddegree(){
	var loginId = '<%=loginId%>';
	var path = '<%=path%>';
	var shi = document.form1.shi.value;	
	if (0 == shi) {
		alert("城市不能为空");
	} else {
		document.form1.action = path + "/servlet/LeaseBargainFeesServlet?loginId="+loginId+"&action=zdName";
		document.form1.submit();
		
	}
}
	 function queryDegree(action){
        document.form1.action=path+"/servlet/LeaseBargainFeesServlet?action=" + action;
        document.form1.submit();
        if(action=="chaxun"){
        	showdiv("请稍等..........");
        }
    }
	     function delad(leasefeeid){
       if(confirm("您确定删除此电费信息？")){
                    document.form1.action=path+"/servlet/LeaseBargainFeesServlet?action=delete&leasefeeid="+leasefeeid
        			document.form1.submit();
        			 showdiv("请稍等..........");
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
	           document.form1.action=path+"/servlet/LeaseBargainFeesServlet?action=delete&leasefeeid="+chooseIdStr;
	           document.form1.submit();
	           showdiv("请稍等..........");
           }else{
           	   alert("请选择删除的信息不要超过50条！");
           }
        }else{
          alert("请选择信息！");
        }
      }
    function modifyad(leasefeeid){
            document.form1.action=path+"/servlet/LeaseBargainFeesServlet?action=getInfo&leasefeeid="+leasefeeid;
            document.form1.submit();
             showdiv("请稍等..........");
    }
	 $(function(){
		
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
</script>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>

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
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">租赁合同费用维护</span>
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
                   <td>报账月份：</td>
                  <td>
                     <input type="text" name="accountmonth" class="selected_font" value="<%if (null != request.getParameter("accountmonth")) out.print(request.getParameter("accountmonth"));%>" 
                     		readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
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
                                   <div id="chaxun" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
		                            <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                           <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
		                           </div>
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
					 <td>合同名称：</td>
					 <td>
					    <input type="text" name="htmc" class="selected_font" value="<%if(null!=request.getParameter("htmc")) out.print(request.getParameter("htmc")); %>" />
					 </td>
					 <td class="form_label">站点类型：</td>
					<td>
						<select name="stationtype" class="selected_font" />
							<option class="form_label" value="0">
								请选择
							</option>
							<%
								ArrayList stationtype = new ArrayList();
								stationtype = ztcommon.getSelctOptions("StationType");
								if (stationtype != null) {
									String code = "", name = "";
									int cscount = ((Integer) stationtype.get(0)).intValue();
									for (int i = cscount; i < stationtype.size() - 1; i += cscount) {
										code = (String) stationtype.get(i
												+ stationtype.indexOf("CODE"));
										name = (String) stationtype.get(i
												+ stationtype.indexOf("NAME"));
							%>
							<option value="<%=code%>"><%=name%></option>
							<%}}%>
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
                      <td width="11%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">地区</div></td>
                      <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                      <td width="8%" height="15" bgcolor="#DDDDDD"><div align="center" class="bttcn">合同名称</div></td>
            		  <td width="8%" height="15" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
                      <td width="8%" height="18" bgcolor="#DDDDDD"><div align="center" class="bttcn">租赁金额</div></td>
                      
                      <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">开始时间</div></td>
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束时间</div></td>
                      <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">经办人</div></td>
                      <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
                      <td width="5%" height="23" bgcolor="#DDDDDD" colspan="2"><div align="center" class="bttcn">操作</div></td>
                  </tr>
					<c:forEach items="${list}"  var="list" varStatus="status">
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       						<td><div align="center" >${status.count}</div></td> 
       						<td>
             				 <div align="center"><input type="checkbox" name="test[]" value="${list.leasefeeid}" /></div>
           					</td>
				           	<td><div align="left" >${list.shi}${list.xian}${list.xiaoqu}</div></td>
				       		<td><div align="left" >${list.zdname}</div></td>
				       		<td><div align="right" >${list.leasename}</div></td>
				       		<td><div align="right" >${list.accountmonth}</div></td>          
				            <td><div align="right" >${list.paymoney}</div></td>
				       		<td><div align="center" >${list.paybegintime}</div></td>
				            <td><div align="center" >${list.payendtime}</div></td>
				       		 <td><div align="center" >${list.payhandler}</div></td>
				       		<td><div align="center" >${list.stationtype}</div></td>
					        <c:if test="${list.countryauditstatus != '1' && list.cityauditstatus != '1'}">
					       		<td><div align="center" ><a href="javascript:modifyad('${list.leasefeeid}')" >修改</a></div></td>
					       		<td><div align="center" ><a href="javascript:delad('${list.leasefeeid}')" >删除</a></div></td>                   		
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
             <td align="right"> 
                <div id="zengjia" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:6px; ">
               		<img alt="" src="<%=path %>/images/xinzeng.png" width="100%" height="100%" />
               		<span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">增加</span>
				</div>
               <div id="daochu" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:9px; ">
                	<img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
                	<span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">导出</span>
				</div>
                <div id="deletes" style="width:76px;height:23px;cursor:pointer;float:right;position:relative;right:11px; ">
                	<img alt="" src="<%=path %>/images/quxiao.png" width="100%" height="100%" />
                	<span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">批量删除</span>
				</div>
            </td>
         </tr>
</table>
</div>
  </form>
</body>
<script type="text/javascript">
//改变城市
function changeCity() {
	var sid = document.form1.shi.value;
	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=shi&pid=" + sid, "shi");
	}
}
//改变乡镇
function changeCountry() {
	var sid = document.form1.xian.value;
	if (sid == "0") {
		var shilist = document.all.xiaoqu;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=xian&pid=" + sid, "xian");
	}
}
//多选框全选
function chooseAll() {
	var qm = document.getElementsByName('test');
	var m = document.getElementsByName('test[]');
	var l = m.length;
	if (qm[0].checked == true) {
		for ( var i = 0; i < l; i++) {
			m[i].checked = true;
		}
	} else {
		for ( var i = 0; i < l; i++) {
			m[i].checked = false;
		}
	}
}
   		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.stationtype.value = '<%=zdlx%>';
 </script>