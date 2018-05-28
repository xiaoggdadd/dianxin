<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.mobi.cx.QuXianNengHaoFenXi" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*" %>

<%
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date()).substring(0,7);
        String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date()).substring(0,7);
      
	String sheng = (String)session.getAttribute("accountSheng");
	String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
String roleId = account.getRoleId();
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
%>

<html>
<head>
<title>
logMange
</title>
<jsp:include page="/web/prePrint.jsp"></jsp:include>
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
.form{width:130px}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
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

  function chaxun(){
					
	}
	function delLogs(){
		

	}
	function addJz(){
		if(document.getElementById("beginTime").value=="" ){
			alert("起始月份不能为空");
		}else if(document.getElementById("endTime").value==""){
			alert("结束月份不能为空");
		}else if(document.getElementById("shi").value=="0"){
			alert("市不能为空");
		}else{
			document.form1.action=path+"/web/cx/quxiannenghaofenxi.jsp";
			document.form1.submit();		
		}
	}
	function daorujz(){
		var tt = 'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no';
		var sheng = '<%=sheng%>';
		var shi = '<%=shi%>';
		
		var beginTime = '<%=beginTime%>';
		var endTime = '<%=endTime%>';
		var url = path+"/web/cx/quxiannenghaofenxi_dc.jsp?sheng="+sheng+"&shi="+shi+"&beginTime="+beginTime+"&endTime="+endTime
		window.open(url,"nenghaodayin",tt);
	}
	$(function(){

	$("#query").click(function(){
		addJz();
	});
	
	$("#dayinBtn").click(function(){
		dayinpage('专业类型能耗统计');
	});
	
	$("#daochuBtn").click(function(){
		daorujz();
	});
});
	

</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">

	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">


	<tr>
    <td width="10" height="500">&nbsp;</td>
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区县能耗比例分析</span></td>
							
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
 					  <tr><td>&nbsp;</td></tr>
					  <tr><td colspan="3"><div id="parent" style="display:inline"></div>
					  	  <div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                          </div>
                      </td></tr>

  		 <tr>
         <td width="800">起始月份：<input type="text" name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" 
         								 readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"  class="form"/>
         				 结束月份： <input type="text" name="endTime" value="<%if(null!=request.getParameter("endTime")) out.print(request.getParameter("endTime")); %>" 
         				 				 readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"  class="form"/>
        
         	&nbsp;市：
         	<select name="shi" id="shi" style="width:130">
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
       		    <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:57%;TOP:-23PX">
					<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
					<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
				</div>        	
         </td>        
      </tr>
  </table>
<div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>
  	<div id="tableInfo">
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">
                           <tr>
             <td width="5%" height="23" bgcolor="#888888"><div align="center" class="bttcn">序号</div>
                                </td>
                               
                                
            			<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">所在地区</div>
            			</td>

                                    <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">电量合计</div>
            			</td>
            			<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">费用合计</div>
            			</td>
			
            </tr>
       <%
       QuXianNengHaoFenXi bean = new QuXianNengHaoFenXi();
       	 ArrayList fylist = new ArrayList();
       	 System.out.println(beginTime+endTime);
       	
       	if(shi != null && !shi.equals("") && !shi.equals("0")){
       fylist = bean.getPageData(curpage,beginTime,endTime,shi);
       
       	 allpage=bean.getAllPage();
		String agname="",dlcount = "",countfy = "",sumdl="",sumdf="",dianfei="",hejidf="";
		double df=0,dl=0;
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     agname = (String)fylist.get(k+fylist.indexOf("XIAN"));
		     dlcount = (String)fylist.get(k+fylist.indexOf("DL"));
			 countfy = (String)fylist.get(k+fylist.indexOf("DF"));
			DecimalFormat mat=new DecimalFormat("0.0");
			DecimalFormat mat1=new DecimalFormat("0.00");
		    if(dlcount==null||dlcount=="")dlcount="0";
			if(countfy==null||countfy=="")countfy="0";
			
			dlcount=mat.format(Double.parseDouble(dlcount));
			countfy=mat1.format(Double.parseDouble(countfy));
			dl+=Double.parseDouble(dlcount);
			df+=Double.parseDouble(countfy);
			sumdl=mat.format(dl);
			hejidf=mat1.format(df);
			String color=null;

			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=intnum++%></div>
       		</td>
       <td>
       			<div align="center" ><%=agname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dlcount%></div>
       		</td>
       		<td>
       			<div align="center" ><%=countfy%></div>
       		</td>
       		

       </tr>
       
      
       <%} %>
       
     <tr bgcolor="F2F9FF">      		
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" >合计</div>
       		</td>
       		<td>
       			<div align="center" ><%=sumdl%></div>
       		</td>
       		<td>
       			<div align="center" ><%=hejidf%></div>
       		</td>
       		
       		   		      		
       </tr>
       <%} %>
<%}%>
  	 </table> 
  	 </div>
  	 
  	 
											</td>
										</tr>
									</table>
								</td>
						</tr>
						<tr bgcolor="#FFFFFF">
											
											<td align="right" colspan="10">
												<div id="parent" style="display:inline" align="right">
                          <div style="width:300px;display:inline;" align="right"><hr></div>
                      </div>
											</td>
											
										</tr>
										
										<tr bgcolor="#FFFFFF">
											
											<td colspan="10">
												

												<div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
	 												<img src="<%=path %>/images/daochu.png" width="100%" height="100%">
													<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
												</div>
												<div id="dayinBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:15px">
														<img src="<%=path %>/images/dayin.png" width="100%" height="100%">
														<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">打印</span>      
												</div>
											</td>
										</tr>
					</table>
				</td>
			</tr>
		</table>
      <br />

    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>&nbsp;</td>
    <td></td>
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
		document.getElementById("shi").value='<%=shi%>';
			function dayinpage(){
		var tt = 'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no';
		var sheng = '<%=sheng%>';
		var shi = '<%=shi%>';
		
		var beginTime = '<%=beginTime%>';
		var endTime = '<%=endTime%>';
		var url = path+"/web/cx/quxiannenghaofenxi_dy.jsp?sheng="+sheng+"&shi="+shi+"&beginTime="+beginTime+"&endTime="+endTime
		window.open(url,"nenghaodayin",tt);
	}
	function dayinpage(title) {
			var column = "all";//打印所有列
//			var column = [0,1,4,6];//定义表格要打印的列，下标从0开始
			var style="<style>table {border-collapse:collapse;border-style:double;border-width:3px;border-color:black;width:700px}</style>";//定义表格样式
			 
			create(title,setColumn(column,style));//创建打印样式
		};
		function setLayout(title,tableHTML){
			LODOP.SET_PRINT_STYLE("FontSize",18);
			LODOP.ADD_PRINT_TEXT(30,280,200,30,title);
			LODOP.ADD_PRINT_HTM(50,50,750,550,tableHTML); 
		}
     </script>

