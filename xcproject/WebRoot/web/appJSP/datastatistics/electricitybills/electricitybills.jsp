<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.text.*"%>
<%
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
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
	String zdsx1 = request.getParameter("zdsx")!=null?request.getParameter("zdsx"):"0";
	String qyzt = request.getParameter("qyzt")!=null?request.getParameter("qyzt"):"-1";//站点启用状态
	String dbqyzt = request.getParameter("dbqyzt")!=null?request.getParameter("dbqyzt"):"-1";//电表启用状态
	String manauditstatus = request.getParameter("manauditstatus")!=null?request.getParameter("manauditstatus"):"3";//财务审核状态
	String sjzrsh = request.getParameter("sjzrsh")!=null?request.getParameter("sjzrsh"):"-1";//市级主任审核
	String gdfs = request.getParameter("gdfs")!=null?request.getParameter("gdfs"):"0";//供电方式
	String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";//站点类型
	Date date = new Date(); 
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
	String time = formatter.format(date);
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):time;//报账月份起始月
	String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):time;//报账月份结束月

%>

<html>
<head>
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
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script language="javascript">
var path = '<%=path%>';
function queryDegree(action){
    	var command=action;
	   	document.form1.action=path+"/servlet/ElecBills?command="+command;                       
        document.form1.submit();
        if("chaxun"==command){
        	showdiv("请稍等..............");
        }
}

$(function(){
	
		$("#daochu").click(function(){
			queryDegree("daochu");
		});
		$("#chaxun").click(function(){
			queryDegree("chaxun");
		});
	});
  </script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
<body  class="body" >	
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" >
	      <tr>
           <td width="50" >
             <div style="width:700px;height:50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电费缴纳明细</span>
	         </div>
	        </td>
		  </tr>
		  <tr>
		   <td height="20" >
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
         	    </select></td>


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
                 <td><select name="xiaoqu" class="selected_font" onchange="">
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
					站点属性：
				  </td>
				  <td>
						<select name="zdsx" class="selected_font">
							<option value="0">
								请选择
							</option>
							<%
								ArrayList zdsx = new ArrayList();
								zdsx = ztcommon.getSelctOptions("ZDSX");
								if (zdsx != null) {
									String code = "", name = "";
									int cscount = ((Integer) zdsx.get(0)).intValue();
									for (int i = cscount; i < zdsx.size() - 1; i += cscount) {
									code = (String) zdsx.get(i + zdsx.indexOf("CODE"));
									name = (String) zdsx.get(i + zdsx.indexOf("NAME"));
							%>
							<option value="<%=code%>"><%=name%></option>
							<%
									}
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
                             	<td>站点启用状态：</td>
                       			<td>
                       				<select name="qyzt" id="zd" class="selected_font" >
         		              			<option value="-1">请选择</option>
         		             			<option value="1">是</option>
         		              			<option value="0">否</option>
         	                		</select>
         	                	</td>
         	                	<td>电表启用状态：</td>
                       			<td>
                       				<select name="dbqyzt" id="db" class="selected_font" >
         		             			<option value="-1">请选择</option>
         		             			<option value="1">是</option>
         		              			<option value="0">否</option>
         	                		</select>
         	                	</td>
         	                	<td >报账月份：</td>
		    					<td colspan="3">
									<input type="text" name="beginTime" value="<%=time%>" 
											readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font"/>
									至
	         						<input type="text" name="endTime" value="<%=time%>" 
	         								readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font"/>
	         					</td>
                            </tr>
                            <tr class="form_label">
                            	<td>财务审核状态：</td>            
         	                    <td>
         	                        <select name="manauditstatus" class="selected_font">
         		                        <option value="3">请选择</option>
         		                        <option value="0">未审核</option>
         		                        <option value="2">财务通过</option>
         		                        <option value="-1">财务不通过</option>
         	                       </select>
         	                    </td>
         	                    <td>市主任审核状态：</td>
                       			<td>
                       				<select name="sjzrsh" id="sjzrsh" class="selected_font" >
         		             			<option value="-1">请选择</option>
         		             			<option value="0">未审核</option>
         		             			<option value="1">审核通过</option>
         		              			<option value="2">审核不通过</option>
         	                		</select>
         	                	</td> 
         	                	<td>供电方式：</td>
                       			<td>
                       				<select name="gdfs"  class="selected_font" >
		         						<option value="0">请选择</option>
		         							<%
			         							ArrayList gdfsb = new ArrayList();
			         							gdfsb = ztcommon.getSelctOptions("GDFS");
			         							if(gdfsb != null){
			         								String code = "",name = "";
			         								int cscount = ((Integer)gdfsb.get(0)).intValue();
			         								int size = gdfsb.size() - 1;
			         								int i;
			         								for(i = cscount;i < size;i += cscount){
		                    							code = (String)gdfsb.get(i + gdfsb.indexOf("CODE"));
		                    							name = (String)gdfsb.get(i + gdfsb.indexOf("NAME"));
		                    				%>
		                    			<option value="<%=code%>"><%=name%></option>
		                    				<%
		                    						}
			         							}
			        	 					%>
    	
    		         				</select>
    		         			</td>	 
                            </tr>
                            <tr class="form_label">
                            	<td>站点类型：</td>
				         		<td>
						         	<select name="zdlx" class="selected_font" >
						         		<option value="0">请选择</option>
						         		<%
							         	ArrayList stationtype = new ArrayList();
							         	stationtype = ztcommon.getSelctOptions("StationType");
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
                            </tr>
                 		</table> 
 					</p>
 				 </div>
 			   </td>
 			</tr>
 </table>

  <table height="23">     
     <tr><td height="5"  colspan="4">
      <div id="parent" style="display:inline">
        <div style="width:50px;display:inline;"><hr></div>
        <font size="2">&nbsp;信息列表&nbsp;</font>
        <div style="width:300px;display:inline;"><hr></div>
      </div></td>
     </tr>
  </table>
  <table width="60%" align="left" CellSpacing="0" class="form_label">
		<tr>    
			<td bgcolor="#DDDDDD" class="bttcn"><div align=center>电费合计（元）：</div></td>
			<td width="30%"><input type="text" readonly="readonly" value="${total}" class="form1" /></td>
			<td bgcolor="#DDDDDD" class="bttcn"><div align=center>条数合计（条）：</div></td>
			<td width="30%"><input type="text" readonly="readonly" value="${num}" class="form1" /></td>
		</tr>
  </table>
<br/>
<br/>
  	<div style="width:100%;height:8%;border:1px" >	
	   <table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
         <tr >
            <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
               <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
            </div></td>
          </tr>
          <tr>
             <td align="right"> 
                     <div id="daochu" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:9px; ">
		                 <img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
		                 <span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">导出</span>
					 </div>
             </td>
         </tr>
          <tr>
           <td><input type="hidden" name="command" id="command" value=""/></td>
         </tr>
	  </table>
   </div>

</form>
</body>
</html>

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

</script>
<script language="javascript">
   
   		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.zdsx.value='<%=zdsx1%>';
		document.form1.manauditstatus.value='<%=manauditstatus%>';
		document.form1.gdfs.value='<%=gdfs%>';
		document.form1.dbqyzt.value='<%=dbqyzt%>';
		document.form1.qyzt.value='<%=qyzt%>';
		document.form1.sjzrsh.value='<%=sjzrsh%>';
		document.form1.beginTime.value='<%=beginTime%>';
		document.form1.endTime.value='<%=endTime%>';
		document.form1.zdlx.value='<%=zdlx%>';
 </script>