<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account"%>
<%@ page
	import="com.noki.util.CTime"%>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.regex.Pattern,com.noki.newfunction.dao.*,com.noki.newfunction.javabean.*"%>

<%
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
     String loginId1 = request.getParameter("loginId");
	String accountmonth = request.getParameter("accountmonth") != null ? request.getParameter("accountmonth") : "";//报账月份
	String pch1 = request.getParameter("pch")!=null?request.getParameter("pch"):"";
	String gdh1= request.getParameter("gdh")!=null?request.getParameter("gdh"):"";
	String shzt1= request.getParameter("shzt")!=null?request.getParameter("shzt"):"5";
	int pagesize=15,curpage=1,xh=1;
    String permissionRights="";
    String color=null;
    String roleId = (String)session.getAttribute("accountRole");
    String property1 = request.getParameter("property")!=null?request.getParameter("property"):"0";
%>

<html>
<head>
	<title></title>
	<style>
		.style1 {
			color: #FF9900;
			font-weight: bold;
		}		
		.style1 {
			color: red;
			font-weight: bold;
		}
		
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		
		.bttcn {
			color: BLACK;
			font-weight: bold;
		}
		
		.form_label {
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height: 23px
		}
		
		.selected_font {
			width: 130px;
			font-family: 宋体;
			font-size: 12px;
			line-height: 120%;
		}
		
		.relativeTag {
			z-index: 10;
			position: relative;
			top: expression(this.offsetParent.scrollTop);
		}
	</style>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
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
	<script>
		
		var oCalendarEn = new PopupCalendar("oCalendarEn"); //初始化控件时,请给出实例名称如:oCalendarEn
		oCalendarEn.Init();
		
		var oCalendarChs = new PopupCalendar("oCalendarChs"); //初始化控件时,请给出实例名称:oCalendarChs
		oCalendarChs.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
		oCalendarChs.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
				"八月", "九月", "十月", "十一月", "十二月");
		oCalendarChs.oBtnTodayTitle = "今天";
		oCalendarChs.oBtnCancelTitle = "取消";
		oCalendarChs.Init();
		</script>
	<script language="javascript">
		var path='<%=path%>';
		function queryDegree() {
		         //alert(111);
				document.form1.action = path+ "/web/jzcbnewfunction/xfshengshenhe.jsp?command=chaxun";
				document.form1.submit();
		
		}
		$(function() {
			$("#chaxun").click(function() {
			//alert("111");
				queryDegree();
			});
			$("#butongguo").click(function() {
			  // alert("111");
				passCheckNo();
				
			});
			$("#tongguo").click(function(){
			//alert("111");
				passCheck();
			});
			$("#chehui").click(function(){
			//alert("111");
				chehui();
			});
		});
	</script>

</head>

	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
<%
	permissionRights=commBean.getPermissionRight(roleId,"0804");
%>
	<body class="body" style="overflow-x: hidden;">
		<form action="" name="form1" method="POST">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="18%">
				<tr>
			    	<td colspan="4" width="50" >
						 <div style="width:700px;height:50px">
						  
						  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">省级整改审核</span>	
						 </div>
					</td>
				</tr>
				<tr>
					<td height="30" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;过滤条件&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td height="8%" width="1200">
						<table>
							<tr class="form_label">
								<td>
									城市：
								</td>
								<td>
									<select name="shi" class="selected_font"
										onchange="changeCity()">
										<option value="0">
											请选择
										</option>
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
								</td>
								<td>
									区县：
								</td>
								<td>
									<select name="xian" class="selected_font"
										onchange="changeCountry()">
										<option value="0">
											请选择
										</option>
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
								<td>
									对标月份：
								</td>
								<td>
									<input type="text" name="accountmonth"
										value="<%if (null != request.getParameter("accountmonth"))out.print(request.getParameter("accountmonth"));%>"
										onFocus="getDatenyString(this,oCalendarChsny)"
										class="selected_font" />
								</td>
								<td>站点属性：
								</td>
								<td>
									<select name="property" style="width: 130"
										class="selected_font">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList zdsx = new ArrayList();
											zdsx = ztcommon.getSelctOptions("zdsx");
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
								
									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; right: -240px; float: right; top: 0px">
										<img alt="" src="<%=path %>/images/chaxun.png" width="100%"
											height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>

									</div>
								</td>
								
							</tr>
								<tr class="form_label">
								<td>
									批次号：
								</td>
								<td>
									<input type="text" name="pch"
										value="<%if (null != request.getParameter("pch"))out.print(request.getParameter("pch"));%>" class="selected_font" />
								</td>
								<td>
									工单号：
								</td>
								<td>
									<input type="text" name="gdh"
										value="<%if (null != request.getParameter("gdh"))out.print(request.getParameter("gdh"));%>" class="selected_font" />
								</td>
							    <td>
									省审核状态：
								</td>
								<td>
								<select name="shzt"  class="selected_font" >
								<option value="5">请选择</option>
								<option value="0">未审核</option>
								<option value="1">结单</option>
								<option value="2">审核不通过</option>
								
								</select>
								
								
								</td>
							
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td height="23" colspan="4">
						<div id="parent" style="display: inline">
							<div style="width: 50px; display: inline;">
								<hr>
							</div>
							<font size="2">&nbsp;信息列表&nbsp;</font>
							<div style="width: 300px; display: inline;">
								<hr>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<% 
	 cbzddao  bean=new cbzddao();
	
         String whereStr = "";
         String str="";
        
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and z.shi='"+shi+"'";
				str=str+" and z.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and z.xian='"+xian+"'";
				str=str+" and z.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" and z.xiaoqu='"+xiaoqu+"'";
				str=str+" and z.xiaoqu='"+xiaoqu+"'";
			}

			if(accountmonth != null && accountmonth != "" && !accountmonth.equals("0")){
				whereStr=whereStr+" AND c.CBSJ ='"+accountmonth+"'";
				str=str+" AND c.CBSJ ='"+accountmonth+"'";
			}
				if(pch1 != null && pch1 != "" && !pch1.equals("0")){
				whereStr=whereStr+" AND x.yppch  like '%"+pch1+"%'";
				str=str+" AND x.yppch  like '%"+pch1+"%'";
			}
				if(gdh1 != null && gdh1 != "" && !gdh1.equals("0")){
				whereStr=whereStr+" AND x.dgpch like '%"+gdh1+"%'";
				str=str+" AND x.dgpch like '%"+gdh1+"%'";
			}
				if(shzt1 != null && shzt1 != "" && !shzt1.equals("5")){
				whereStr=whereStr+" AND x.SHENGJSHBZ ='"+shzt1+"'";
				str=str+" AND x.SHENGJSHBZ ='"+shzt1+"'";
			}
				if(property1 != null && property1 != "" && !property1.equals("0")){
					whereStr=whereStr+" AND z.property='"+property1+"'";
			}
			

		if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;
       	 	 shi="1";
       	 }
		 String count1="0",count2="0",count3="0",count4="0.00";
		
		%>
			<div
				style="width: 100%; height: 300px; overflow-x: auto; overflow-y: auto; border: 1px">
				<table width="100%" height="80%" border="0" cellspacing="1"
					cellpadding="1" bgcolor="#cbd5de" class="form_label">
					<tr height="23" class="relativeTag">
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								<input type="checkbox" name="test" onclick="chooseAll()" />
								全选
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								标题
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								批次号
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								工单号
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点编号
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								所在区域
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								对标月份
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								超标比例
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								当月省定标
							</div>
						</td>
						<td width="9%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								省定标(不含空调)
							</div>
						</td>
						<td width="8%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								完成说明
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								完成时间
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								整改责任人
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								审核状态
							</div>
						</td>
						<td width="5%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								详细
							</div>
						</td>
					</tr>
	<%
         List<Zdinfo> list=null;         
		if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
		       list	=bean.getxfshengshenhe(whereStr,loginId);
		}else{
			list=null;
		}
		String id="";
		String zdid = "";//站点编号
		String zdname = "";//站点名称
		String szdq = "";//所属地域
		String shi1="";//市
		String xian1="";//县
		String xiaoqu1="";//小区
		String cbmonth = "";//超标月份(报账月份、对标月份)
		String cbbili = "";//超表比例
		
		String csr="";//整改责任人
		String status = "";//审核状态
		String shzt = "";//审核状态
		String zgyq = "";//整改要求
		String wcsj = "";//完成时间
		String Idd="";//第二张表的id
		String bt="";//标题
		String pch="";//批次号
		String gdh="";//工单号
		String nhbz="";//能耗标准（当月省定标）
		String scb="";//生产标（省定标（不含空调））
		int intnum=0;
		if(list!=null){
			for(Zdinfo bean2:list){
				id=bean2.getId();
				zdid=bean2.getZdid();
				zdname=bean2.getZdname();
				shi1=bean2.getShi();
				xian1=bean2.getXian();
				xiaoqu1=bean2.getXiaoqu();
				cbmonth=bean2.getCbsj();
				cbbili=bean2.getCbbl();
				zgyq=bean2.getXfzgyq();
				wcsj=bean2.getWcsj();
				csr=bean2.getCszrr();		
				status=bean2.getShijsh();
				Idd=bean2.getIdd();
				bt=bean2.getBt();
				pch=bean2.getYppch();
				gdh=bean2.getDgpch();
				nhbz=bean2.getNhbz();
				scb=bean2.getScb();
				szdq=shi1+xian1+xiaoqu1;
				
				if(wcsj==null ||"null".equals(wcsj)||"".equals(wcsj)||" ".equals(wcsj)){
					wcsj="";
				}else{
					wcsj=wcsj.substring(0,10);
				}
				
				if(nhbz==null ||"null".equals(nhbz)||"".equals(nhbz)||" ".equals(nhbz)){
					nhbz="";
				}
				if(scb==null ||"null".equals(scb)||"".equals(scb)||" ".equals(scb)){
					scb="";
				}
			if(zdid==null ||zdid.equals("null")||zdid.equals("")||zdid.equals(" ")||zdid==""){
				zdid="";
			}
				if(zdname==null ||zdname.equals("null")||zdname.equals("")||zdname.equals(" ")||zdname==""){
				zdname="";
			}
				if(shi1==null ||shi1.equals("null")||shi1.equals("")||shi1.equals(" ")||shi1==""){
				shi1="";
			}
				if(xian1==null ||xian1.equals("null")||xian1.equals("")||xian1.equals(" ")||xian1==""){
				xian1="";
			}
				if(xiaoqu1==null ||xiaoqu1.equals("null")||xiaoqu1.equals("")||xiaoqu1.equals(" ")||xiaoqu1==""){
				xiaoqu1="";
			}
				if(cbmonth==null ||cbmonth.equals("null")||cbmonth.equals("")||cbmonth.equals(" ")||cbmonth==""){
				cbmonth="";
			}
				if(cbbili==null ||cbbili.equals("null")||cbbili.equals("")||cbbili.equals(" ")||cbbili==""){
				cbbili="0";
			}
			 DecimalFormat mat=new DecimalFormat("0.00");
			 cbbili=mat.format(Double.parseDouble(cbbili)*100)+"%";
			
				if(csr==null ||csr.equals("null")||csr.equals("")||csr.equals(" ")||csr==""){
				csr="";
			}
				if(status==null ||status.equals("null")||status.equals("")||status.equals(" ")||status==""){
				status="0";
			}
				if(szdq==null ||szdq.equals("null")||szdq.equals("")||szdq.equals(" ")||szdq==""){
				szdq="";
			}
					if(Idd==null ||Idd.equals("null")||Idd.equals("")||Idd.equals(" ")||Idd==""){
				Idd="";
			}
				if(bt==null ||bt.equals("null")||bt.equals("")||bt.equals(" ")||bt==""){
				bt="";
			}
			if(pch==null ||pch.equals("null")||pch.equals("")||pch.equals(" ")||pch==""){
				pch="";
			}
			if(gdh==null ||gdh.equals("null")||gdh.equals("")||gdh.equals(" ")||gdh==""){
				gdh="";
			}
			if(status != null&& status.equals("1")){
				shzt="结单";
			}else if(status != null	&& status.equals("2")){
				shzt="不通过";
			} else if(status != null	&& status.equals("0")){
				shzt="未审核";
			}
			
				if (intnum % 2 == 0) {
					color = "#FFFFFF";

				} else {
					color = "#DDDDDD";
				}
		System.out.println("222:"+bean2.getBzpld());
		intnum++;
     %>

					<tr bgcolor="<%=color %>">
						<td>
							<div align="center"><%=intnum%></div>
						</td>
						<td>
							<div align="center">
								<input type="checkbox" name="test[]" value="<%=id%>" />
								<input type="hidden" type="checkbox" name="test2[]" value="<%=zdid%>" />
							</div>
						</td>
						<td>
							<div align="left"><%=bt%></div>
						</td>
						<td>
							<div align="left"><%=pch%></div>
						</td>
						<td>
							<div align="left"><%=gdh%></div>
						</td>
						
						<td>
							<div align="left"><%=zdid%></div>
						</td>
						<td>
							<div align="left"><%=zdname%></div>
						</td>
						<td>
							<div align="left"><%=szdq%></div>
						</td>
						<td>
							<div align="center"><%=cbmonth%></div>
						</td>
						<td>
							<div align="right"><%=cbbili%></div>
						</td>
						<td>
							<div align="right"><%=nhbz%></div>
						</td>
						<td>
							<div align="right"><%=scb%></div>
						</td>
						<td>
							<div align="center"><%=zgyq%>
							</div>
						</td>
						<td>
							<div align="center"><%=wcsj%></div>
						</td>
						<td>
							<div align="center"><%=csr%></div>
						</td>
						<td>
							<div align="center"><%=shzt%></div>
						</td>
					
					
							<td>
							<div align="center">
								<a
									href="javascript:lookDetailssz('<%=Idd%>')">查看详细</a>
							</div>
						</td>
					</tr>
					<%}} %>
				<input type="hidden" name="cbmonth" value="<%=cbmonth %>" />
				</table>
			</div>

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				
					<td align="right">
						<div id="tongguo" style="width:85px;height:23px;cursor:pointer;float:right;position: relative; right:-10px; top: 0px">
						<img  src="<%=path %>/images/mmcz.png" width="100%" height="100%">
						 <span style="font-size: 12px; position: absolute; left: 35px; top: 5px; color: white">结单</span>
						
						</div>
						<div id="butongguo"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: 10px; top: 0px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">不通过</span>
						</div>
						<div id="chehui" style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
					         <img src="<%=path%>/images/2chongzhi.png" width="100%" height="100%">
					         <span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">取消审核</span>
				        </div>
					</td>
				
				</tr>
			</table>
						<input type="hidden" name="sheng2" id="sheng2" value="" />
						<input type="hidden" name="shi2" id="shi2" value="" />
						<input type="hidden" name="xian2" id="xian2" value="" />
		</form>
	</body>
<script type="text/javascript">	
	function lookDetailssz(id) {
		var path = '<%=path%>';
		window.open(path + "/web/jzcbnewfunction/shengshenhexx.jsp?id=" + id, '','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
	}
</script>
<script type="text/javascript">
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
	            var res = XMLHttpReq.responseText;
	            window.alert(res);
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
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
	
	//审核通过
	  function passCheck(){
	  var loginName='<%=loginName%>';
        var m = document.getElementsByName('test[]');
         var zdid = document.getElementsByName('test2[]');
          var cbsj=document.form1.cbmonth.value;
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr1 = ""; 
       	var chooseIdStr2 = ""; 
       	var zdid1="";
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       	}
       	if(count!=0){
		       	if(count%240==0){
		       		n=count/240-1;
		       	}else{
		       		n=(count-(count%240))/240;
		       	}
		        for(var i = 0; i < l; i++){
		         if(m[i].checked == true){
		             bz+=1;
		             count1+=1;
		           var chooseIdStr3 = m[i].value.toString();
			             chooseIdStr1 = chooseIdStr1 +"'" +chooseIdStr3 +"',";
			                var zd=zdid[i].value.toString();
			         zdid1=zdid1+"'"+zd+"',";
		        }
		        }
		          chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
		           zdid1=zdid1.substring(0,zdid1.length-1);
		          document.form1.action=path+"/servlet/Cbzdsjshenhe?action=xfshengsh&chooseIdStr1="+chooseIdStr1+"&loginName="+loginName+"&zdid1="+zdid1+"&cbsj="+cbsj;
					document.form1.submit();
       }else{
        alert("请选择信息！");
       }
   }
	//审核不通过
	 function passCheckNo(){
        var m = document.getElementsByName('test[]');
        var loginName='<%=loginName%>';
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr1 = ""; 
       	var chooseIdStr2 = ""; 
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       	}
       	if(count!=0){
	       	if(count%240==0){
	       		n=count/240-1;
	       	}else{
	       		n=(count-(count%240))/240;
	       	}
	        for(var i = 0; i < l; i++){
	         if(m[i].checked == true){
	             bz+=1;
	             count1+=1;
	              var chooseIdStr3 = m[i].value.toString();
			      chooseIdStr1 = chooseIdStr1 +"'" +chooseIdStr3 +"',";
			      }
			      }
	              chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
	                window.open(path+"/web/jzcbnewfunction/showzgplsjzg.jsp?idad="+chooseIdStr1,'','width=1230,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
		          //document.form1.action=path+"/servlet/Cbzdsjshenhe?action=xfshengshno&chooseIdStr1="+chooseIdStr1+"&loginName="+loginName;
				  document.form1.submit();
        }else{
          alert("请选择信息！");
        }
   }
   
   	//审核通过
	  function chehui(){
	  var loginName='<%=loginName%>';
        var m = document.getElementsByName('test[]');
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr1 = ""; 
       	var chooseIdStr2 = ""; 
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       	}
       	if(count!=0){
		       	if(count%240==0){
		       		n=count/240-1;
		       	}else{
		       		n=(count-(count%240))/240;
		       	}
		        for(var i = 0; i < l; i++){
		         if(m[i].checked == true){
		             bz+=1;
		             count1+=1;
		           var chooseIdStr3 = m[i].value.toString();
			             chooseIdStr1 = chooseIdStr1 +"'" +chooseIdStr3 +"',";
		        }
		        }
		          chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
		          document.form1.action=path+"/servlet/Cbzdsjshenhe?action=xfshengtuihui&chooseIdStr1="+chooseIdStr1+"&loginName="+loginName;
					document.form1.submit();
       }else{
        alert("请选择信息！");
       }
   }
</script>
<!--多选框选择 -->
<script type="text/javascript">
		function chooseAll() {
			var qm = document.getElementsByName('test');
			//alert(qm[0].checked);  
			var m = document.getElementsByName('test[]');
			var l = m.length;
			if (qm[0].checked == true) {
				for ( var i = 0; i < l; i++) {
					m[i].checked = true;
				}
			} else {
				for ( var i = 0; i < l; i++) {
					//m[i].checked == true ? m[i].checked = false: m[i].checked = true; 
					m[i].checked = false;
				}
			}
		}
		
		document.form1.shi.value = '<%=shi%>';
		document.form1.xian.value = '<%=xian%>';
		document.form1.accountmonth.value = '<%=accountmonth%>';
		document.form1.shzt.value = '<%=shzt1%>';
		document.form1.property.value='<%= property1%>';
	</script>
</html>

