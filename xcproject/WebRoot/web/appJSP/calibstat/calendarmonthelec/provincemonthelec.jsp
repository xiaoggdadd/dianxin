<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.noki.mobi.common.CommonBean,java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat,java.util.Calendar,java.text.ParseException"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	String zdshuxing = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";//站点属性
	String zdlx = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";//站点类型
	String zdqyzt = request.getParameter("zdqyzt")!=null?request.getParameter("zdqyzt"):"1";//站点启用状态
	String dfzflx = request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";//电费支付类型
	String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";//结束月份
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";//起始月份
    String roleId = (String)session.getAttribute("accountRole");
    int intnum = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;
    int l = request.getAttribute("yfcd")!=null?(Integer)request.getAttribute("yfcd"):0;//日期间隔长度
    if(l==0){
    	l=1;
    }
	//转换日期格式
	  DateFormat fmt =new SimpleDateFormat("yyyy-MM");
	  Calendar g = Calendar.getInstance();
	  Date date = new Date(); 
	String dateString = fmt.format(date);
    String yf=beginTime;
    if(yf.equals("")||yf.equals("null")||yf==null){
    	yf=dateString;
    }
    String color = "";
%>

<html>
<head>
<style>   
.style1 {
	color: red;
	font-weight: bold;
}
#wrap {
 PADDING-RIGHT: 5px; PADDING-LEFT: 5px; BACKGROUND: #ffffff; PADDING-BOTTOM: 10px; OVERFLOW: hidden; WIDTH: expression_r(this.offsetParent.clientWidth<800?"770px":"99%"); PADDING-TOP: 2px; min-width: 770px
}

.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
.form_label1{
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
function queryDegree(command){
	
	  var beginyue = document.form1.beginTime.value;//起始月
	  var endyue  = document.form1.endTime.value;//结束月
	  
	 if(beginyue == "" || beginyue == null){
			alert("起始月份不能为空");
		}else if(endyue == "" || endyue == null){
			alert("结束月份不能为空");
		}else if(beginyue>endyue){
			alert("结束月份必须大于等于起始月份");
		}else{
						 document.form1.action=path+"/servlet/ProMonthEle?command="+command;
        				 document.form1.submit();
        				 if(command=="chaxun"){
                         showdiv("请稍等.....");
        				 }
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
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">省级自然月用电统计</span>
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
           <td>起始月份</td>
           <td>
           <input type="text" name="beginTime" value="<%=request.getParameter("beginTime")!=null?request.getParameter("beginTime"):dateString%>" 
										readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font"/>
           </td>
             <td>结束月份</td>
           <td>
           <input type="text" name="endTime" value="<%=request.getParameter("endTime")!=null?request.getParameter("endTime"):dateString%>" 
										readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font"/>
           </td>
 				<td>
				站点属性：
				</td>
				<td>
					<select name="jzproperty" class="selected_font"
							onchange="zdsxCheck(this.value)">
						<option value="0">请选择</option>
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
				站点类型：
				</td>
				<td>
					<select name="zdlx" class="selected_font">
						<option value="0">请选择</option>
						<%
							ArrayList stationtype = new ArrayList();
							stationtype = ztcommon.getZdlx(zdshuxing);
							//stationtype = ztcommon.getSelctOptions("StationType");
							if (stationtype != null) {
								String code = "", name = "";
								int cscount = ((Integer) stationtype.get(0)).intValue();
								int size = stationtype.size() - 1;
								int i;
								for (i = cscount; i < size; i += cscount) {
										code = (String) stationtype.get(i
												+ stationtype.indexOf("CODE"));
										name = (String) stationtype.get(i
												+ stationtype.indexOf("NAME"));
										if(zdlx!=null&&!"".equals(zdlx)){
						%>
						<option value="<%=code%>"><%=name%></option>
						<%
							}
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
							   <td>站点启用状态</td>
							   <td>
							     <select name="zdqyzt" class="selected_font">
							     <option value="-1">全部</option>
							     <option value="1">是</option>
							     <option value="0">否</option>
							     </select>
							   
							   </td>
							   <td>电费支付类型：</td>
				   			    <td>
                       				<select name="dfzflx"  class="selected_font" >
		         							<option value="0">请选择</option>
		         								<%
			         								ArrayList dfzflxa = new ArrayList();
			         								dfzflxa = ztcommon.getSelctOptions("DFZFLX");
			         								if(dfzflxa!=null){
			         									String code="",name="";
			         									int cscount = ((Integer)dfzflxa.get(0)).intValue();
			         									int size = dfzflxa.size()-1;
			         									int i;
			         									for(i = cscount;i < size;i += cscount){
		                    								code = (String)dfzflxa.get(i + dfzflxa.indexOf("CODE"));
		                    								name = (String)dfzflxa.get(i + dfzflxa.indexOf("NAME"));
		                    					%>
		                    				<option value="<%=code%>"><%=name%></option>
		                    					<%
		                    							}
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
	
  <div style="width:100%;overflow-x:auto;overflow-y:auto;border:1px" >  	
			    <table width=""  border="0.5" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                  <tr class="relativeTag">
                  <td  width="2%"  class="relativeTag" bgcolor='#DDDDDD' rowspan="2"><div align="center" class="bttcn">序号</div></td>
                  <td  width="4%"  class="relativeTag" bgcolor='#DDDDDD' rowspan="2"><div align="center" class="bttcn">城市</div></td>
                  
                   <% 
                      for(int i=1;i<=l;i++){
                    %>  
                     <td  width=""  class="relativeTag" bgcolor='#DDDDDD' colspan="4"><div align="center" class="bttcn"><%=yf %></div></td>	  
                    	  
                   <%  
                   //循环月份 递增赋值显示
                   if(yf!=null&&!yf.equals("")){
                   try {
                     g.setTime(fmt.parse(yf));
                      } catch (ParseException e) {
                  		// TODO Auto-generated catch block
                  		e.printStackTrace();
                  	   }
           		     g.add(Calendar.MONTH, 1);
           		     yf = fmt.format(g.getTime());
                   }
                     }
                   %>
             
                 
                  </tr>
                  <tr class="relativeTag">
                  <c:forEach   begin="1" end="<%=l %>" step="1">
                      <td bgcolor='#DDDDDD'  class="relativeTag" width="2%"><div align="center" class="bttcn">站点数</div></td>
                      <td bgcolor='#DDDDDD'  class="relativeTag" width="4%"><div align="center" class="bttcn">电量</div></td>
                      <td bgcolor='#DDDDDD'  class="relativeTag" width="4%"><div align="center" class="bttcn">电费</div></td>
                      <td bgcolor='#DDDDDD'  class="relativeTag" width="2%"><div align="center" class="bttcn">电费单条数</div></td>
                  </c:forEach>
               </tr>
					<c:forEach items="${list}"  var="map" varStatus="status">
					
						<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
						<td>
       							<div align="center" >${status.count}</div>
       						</td>
						<td>
				       			<div align="center" >${map[0]}</div>
				       	</td> 
				       <c:forEach var="i" begin="1"  end="<%=l %>" step="1" varStatus="status1"> 
       						<td>
				       			<div align="center" >${map[status1.index].zds }</div>
				       		</td>
				       		
				           	<td>
				       			<div align="center" >${map[status1.index].dl}</div>
				       		</td>
				       		<td>
				       			<div align="center" >${map[status1.index].df}</div>
				       		</td>
				      	    <td>
				       			<div align="center" >${map[status1.index].dfdts}</div>
				       		</td>
				       	
				       	</c:forEach>
    					<%--
						<td>
				       			<div align="center" >${map[0]}</div>
				       	</td> 
				       <c:forEach var="i" begin="1" end="4" step="1" varStatus="status1"> 
       						<td>
				       			<div align="center" >${map[status1.index].zds }</div>
				       		</td>
				       		
				           	<td>
				       			<div align="center" >${map[status1.index].dl}</div>
				       		</td>
				       		<td>
				       			<div align="center" >${map[status1.index].df}</div>
				       		</td>
				      	    <td>
				       			<div align="center" >${map[status1.index].dfdts}</div>
				       		</td>
				       	
				       	</c:forEach>
    					--%></tr>
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
             
       <c:forEach  begin="1" end="<%=l %>" step="1">
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td> 
        </c:forEach>
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
         <c:forEach   begin="1" end="<%=l %>" step="1">
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>
         </c:forEach>
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
                     <div id="daochu" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:9px; ">
		                 <img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
		                 <span style="font-size:12px;position: absolute;left:25px;top:5px;color:white">导出</span>
					 </div>
             </td>
         </tr>
	  </table>
	
   </div>
</form>
</body>

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
<script language="javascript">
   		
		document.form1.jzproperty.value='<%=zdshuxing%>';
		document.form1.zdlx.value='<%=zdlx%>';
		document.form1.zdqyzt.value='<%=zdqyzt%>';
		document.form1.dfzflx.value='<%=dfzflx%>'; 
 </script>
</html>