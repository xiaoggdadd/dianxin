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
		String zdnamea=request.getParameter("zdnamea")!=null?request.getParameter("zdnamea"):"0";
     String loginId1 = request.getParameter("loginId");
	String accountmonth = request.getParameter("accountmonth") != null ? request
			.getParameter("accountmonth") : "";//报账月份
	String property1 = request.getParameter("property")!=null?request.getParameter("property"):"0";
	int pagesize=15,curpage=1,xh=1;
    String permissionRights="";
    String color=null;
    String roleId = (String)session.getAttribute("accountRole");
    String dgpch = request.getParameter("dgpch")!=null?request.getParameter("dgpch"):"";
    String yppch = request.getParameter("yppch")!=null?request.getParameter("yppch"):"";
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
				document.form1.action = path+ "/web/jzcbnewfunction/cbzdcx.jsp?command=chaxun";
				document.form1.submit();
		
		}
		function lookDetails(id,cid){ 
 		    var path = '<%=path%>';
	  		window.open(path+"/web/jzcbnewfunction/Cbzdxx.jsp?id="+id+"&cid="+cid,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
    }
		
	function daochuBtn(){
            var shi="<%=shi%>";
            var xian ="<%=xian%>";
            var xiaoqu='<%=xiaoqu%>';
            var accountmonth='<%=accountmonth%>';
            var dgpch ='<%=dgpch%>';
            var yppch = '<%=yppch%>';
            var zdname='<%=zdnamea%>';
        	document.form1.action=path+"/web/jzcbnewfunction/工单状态查询.jsp?shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&accountmonth="+accountmonth+"&dgpch="+dgpch+"&yppch="+yppch+"&zdname="+zdname+"&command=daochu";
        	
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
			$("#daochuBtn").click(function(){
			//alert("111");
				daochuBtn();
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
						  
						  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">工单状态查询</span>	
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
								<td>
									工单号：
								</td>
								<td>
									<input type="text" name="dgpch"
										value="<%if (null != request.getParameter("dgpch"))out.print(request.getParameter("dgpch"));%>"
										class="selected_font1" />
								</td>
								<td>
									批次号：
								</td>
								<td>
									<input type="text" name="yppch"
										value="<%if (null != request.getParameter("yppch"))out.print(request.getParameter("yppch"));%>"
										class="selected_font1" />
								</td>
								<td>
								
									<div id="chaxun"
										style="position: relative; width: 60px; height: 23px; cursor: pointer; left: 30px; float: right; top: 0px">
										<img alt="" src="<%=path %>/images/chaxun.png" width="100%"
											height="100%" />
										<span
											style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">查询</span>

									</div>
								</td>
							</tr>
							<tr class="form_label">
											<td>站点名称：</td>
	         		<td><input type="text" class="selected_font1" name="zdnamea" value="<%if(null!=request.getParameter("zdnamea")) out.print(request.getParameter("zdnamea")); %>" /></td>
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
	 cbzddao bean=new cbzddao();
	
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
			
			if(dgpch != null && dgpch != "" && !dgpch.equals("")){
				whereStr=whereStr+" AND X.DGPCH like '%"+dgpch+"%'";
				str=str+" AND X.DGPCH ='"+dgpch+"'";
			}
			if(yppch != null && yppch != "" && !yppch.equals("")){
				whereStr=whereStr+" AND X.YPPCH  like '%"+yppch+"%'";
				str=str+" AND X.YPPCH ='"+yppch+"'";
			}
			if(zdnamea != null && !zdnamea.equals("")&& !zdnamea.equals("0")){
				whereStr=whereStr+" AND Z.JZNAME LIKE '%"+zdnamea+"%'";				
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
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="10%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="10%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点ID
							</div>
						</td>
						<td width="12%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								所在区域
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								超标月份
							</div>
						</td>
						<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								超标比例
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								省派单
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市签收
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市派单
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县签收
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县派单
							</div>
						</td>
						<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县测试
							</div>
						</td>
							<td width="4%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市审核
							</div>
						</td>
							<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								省审核
							</div>
						</td>
							<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								省下发要求
							</div>
						</td>
							<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								地市下发
							</div>
						</td>
							<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								整改完成状态
							</div>
						</td>
							<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市审核
							</div>
						</td>
							<td width="6%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								省审核
							</div>
						</td>
						
					</tr>
	<%
         List<Zdinfo> list=null;         
		if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
		       list	=bean.getchaxun(whereStr,loginId);
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
		String cbmonth = "";//超标月份
		String cbbili = "";//超表比例
	    String shengpd="";//省派单 
	    String dsqs="";//地市签收
	    String dspd="";//地市派单
	    String qxqs="";//区县签收
	    String qxpd="";//区县派单
	    String qxcstj="";//区县测试 提交标志
	    String shish="";//市级审核
	    String shengsh="";//省级审核
	    String shengxf="";//省级下发(下发)
	    String dsxf="";//地市下发(下发)
	    String zgwczt="";//整改完成状态(下发)
	    String sjsh="";//市级审核(下发)
	    String shengjsh="";//省级审核(下发)
	    String cid="";//第二张表里的id
	    int intnum=0;
	    cbzddao  dao=new cbzddao();
	    cbzddao  dao1=new cbzddao();
	    int r = dao.CheckQx(id);
	    int rr=dao1.CheckZg(cid);
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
				szdq=shi1+xian1+xiaoqu1;
				shengpd=bean2.getSjpd();
				dsqs=bean2.getDsqs();
				dspd=bean2.getDspd();
				qxqs=bean2.getQxqs();
				qxpd=bean2.getQxpd();
				qxcstj=bean2.getQxtjsh();
				shish=bean2.getShijsh();
				shengsh=bean2.getShengjsh();
				shengxf=bean2.getSjxf();
				dsxf=bean2.getShijxf();
				zgwczt=bean2.getQxzgtj();
				sjsh=bean2.getSjshbz();
				shengjsh=bean2.getShengjshbz();
				cid=bean2.getCid();
				
			 DecimalFormat form=new DecimalFormat("0.0000");
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
			DecimalFormat price1=new DecimalFormat("0.00");
				if(cbbili==null ||cbbili.equals("null")||cbbili.equals("")||cbbili.equals(" ")||cbbili==""){
				cbbili="";
			}else{
				double b = Double.parseDouble(cbbili);
				cbbili=price1.format(Double.parseDouble(cbbili)*100)+"%";
			}
		
				if(szdq==null ||szdq.equals("null")||szdq.equals("")||szdq.equals(" ")||szdq==""){
				szdq="";
			}
			
			
			//////////
				if(shengpd==null ||shengpd.equals("null")||shengpd.equals("")||shengpd.equals(" ")||shengpd==""){
				shengpd="0";
			}
				if(dsqs==null ||dsqs.equals("null")||dsqs.equals("")||dsqs.equals(" ")||dsqs==""){
				dsqs="0";
			}
				if(dspd==null ||dspd.equals("null")||dspd.equals("")||dspd.equals(" ")||dspd==""){
				dspd="0";
			}
				if(qxpd==null ||qxpd.equals("null")||qxpd.equals("")||qxpd.equals(" ")||qxpd==""){
				qxpd="0";
			}
				if(qxqs==null ||qxqs.equals("null")||qxqs.equals("")||qxqs.equals(" ")||qxqs==""){
				qxqs="0";
			}
				if(qxcstj==null ||qxcstj.equals("null")||qxcstj.equals("")||qxcstj.equals(" ")||qxcstj==""){
				qxcstj="0";
			}
				if(shish==null ||shish.equals("null")||shish.equals("")||shish.equals(" ")||shish==""){
				shish="0";
			}
				if(shengsh==null ||shengsh.equals("null")||shengsh.equals("")||shengsh.equals(" ")||shengsh==""){
				shengsh="0";
			}
				if(shengxf==null ||shengxf.equals("null")||shengxf.equals("")||shengxf.equals(" ")||shengxf==""){
				shengxf="0";
			}
				if(dsxf==null ||dsxf.equals("null")||dsxf.equals("")||dsxf.equals(" ")||dsxf==""){
				dsxf="0";
			}
				if(zgwczt==null ||zgwczt.equals("null")||zgwczt.equals("")||zgwczt.equals(" ")||zgwczt==""){
				zgwczt="0";
			}
				if(sjsh==null ||sjsh.equals("null")||sjsh.equals("")||sjsh.equals(" ")||sjsh==""){
				sjsh="0";
			}
				if(shengjsh==null ||shengjsh.equals("null")||shengjsh.equals("")||shengjsh.equals(" ")||shengjsh==""){
				shengjsh="0";
			}
				if(cid==null ||cid.equals("null")||cid.equals("")||cid.equals(" ")||cid==""){
				cid="";
			}
				String spcol="";
				String sjqscol="";
				String sjpdcol="";
				String qxqscol="";
				String qxpdcol="";
				String qxcscol="";
				String sjshcol="";
				String sshcol="";
				String sxfcol="";
				String sjxfcol="";
				String zgcol="";
				String sshcol2="";
				String sjshcol2="";
			if(shengpd != null&& shengpd.equals("2")){
				shengpd="地市退单";
				spcol="yellow";
			}else if(shengpd != null	&& shengpd.equals("1")){
				shengpd="已派单";
				spcol="yellow";
			} else if(shengpd != null	&& shengpd.equals("0")){
				shengpd="未派单";
				spcol="red";
			}
			if(dsqs != null&& dsqs.equals("0")){
				dsqs="未签收";
				sjqscol="red";
			}else if(dsqs != null	&& dsqs.equals("1")){
				dsqs="已签收";
				sjqscol="yellow";
			} 
			if(dspd != null&& dspd.equals("2")){
				dspd="区县退单";
				sjpdcol="yellow";
			}else if(dspd != null	&& dspd.equals("1")){
				dspd="已派单";
				sjpdcol="yellow";
			} else if(dspd != null	&& dspd.equals("0")){
				dspd="未派单";
				sjpdcol="red";
			}else if(dspd != null	&& dspd.equals("3")){
				dspd="地市退单";
				sjpdcol="yellow";
			}
				if(qxqs != null&& qxqs.equals("0")){
				qxqs="未签收";
				qxqscol="red";
			}else if(qxqs != null&& qxqs.equals("1")){
				qxqs="已签收";
				qxqscol="yellow";
			}
				if(qxpd != null	&& qxpd.equals("1")){
				qxpd="已派单";
				qxpdcol="yellow";
			} else if(qxpd != null	&& qxpd.equals("0")){
				qxpd="未派单";
				qxpdcol="red";
			}else if(qxpd != null	&& qxpd.equals("2")){
				qxpd="退单";
				qxpdcol="yellow";
			}
				if(qxcstj != null	&& qxcstj.equals("1")){
				qxcstj="已核实";
				qxcscol="yellow";
			} else if(qxcstj != null	&& qxcstj.equals("0")){
				qxcstj="未核实";
				qxcscol="red";
			}
				if(shish != null	&& shish.equals("1")){
				shish="审核不通过";
				sjshcol="yellow";
			} else if(shish != null	&& shish.equals("2")){
				shish="审核通过";
				sjshcol="yellow";
			}else if(shish != null	&& shish.equals("0")){
				shish="未审核";
				sjshcol="red";
			}
				if(shengsh != null	&& shengsh.equals("1")){
				shengsh="退单";
				sshcol="yellow";
			} else if(shengsh != null	&& shengsh.equals("2")){
				shengsh="整改";
				sshcol="yellow";
			}else if(shengsh != null	&& shengsh.equals("0")){
				shengsh="未审核";
				sshcol="red";
			}else if(shengsh != null	&& shengsh.equals("3")){
				shengsh="结单";
				sshcol="yellow";
			}
				if(shengxf != null	&& shengxf.equals("1")){
				shengxf="已下发";
				sxfcol="yellow";
			} else if(shengxf != null	&& shengxf.equals("0")){
				shengxf="未下发";
				sxfcol="red";
			}
			
			if(dsxf != null	&& dsxf.equals("1")){
				dsxf="已下发";
				sjxfcol="yellow";
			} else if(dsxf != null	&& dsxf.equals("0")){
				dsxf="未下发";
				sjxfcol="red";
			}
			if(zgwczt != null	&& zgwczt.equals("1")){
				zgwczt="已提交";
				zgcol="yellow";
			} else if(zgwczt != null	&& zgwczt.equals("0")){
				zgwczt="未提交";
				zgcol="red";
			}
				if(sjsh != null	&& sjsh.equals("1")){
				sjsh="审核通过";
				sjshcol2="yellow";
			} else if(sjsh != null	&& sjsh.equals("0")){
				sjsh="未审核";
				sjshcol2="red";
			}else if(sjsh != null	&& sjsh.equals("2")){
				sjsh="审核不通过";
				sjshcol2="yellow";
			}
				if(shengjsh != null	&& shengjsh.equals("1")){
				shengjsh="结单";
				sshcol2="yellow";
			} else if(shengjsh != null	&& shengjsh.equals("0")){
				shengjsh="未审核";
				sshcol2="red";
			}else if(shengjsh != null	&& shengjsh.equals("2")){
				shengjsh="审核不通过";
				sshcol2="yellow";
			}
			
		
		//int intnum=xh = pagesize*(curpage-1)+1;
			if (intnum % 2 == 0) {
					color = "#FFFFFF";

				} else {
					color = "#DDDDDD";
				}
	
		intnum++;
     %>

					<tr bgcolor = "<%=color%>">
						<td>
							<div align="center"><%=intnum%></div>
						</td>
						
						<td>
							<div align="left"><a href="javascript:lookDetails('<%=id%>','<%=cid%>')"><%=zdname%></a></div>
						</td>
						<td>
							<div align="left"><%=zdid%></div>
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
						<td style="background-color:<%=spcol%>">
							<div align="center"><%=shengpd%></div>
						</td>
						<td style="background-color:<%=sjqscol%>">
							<div align="center"><%=dsqs%></div>
						</td>
						<td style="background-color:<%=sjpdcol%>">
							<div align="center"><%=dspd%></div>
						</td>
						<td style="background-color:<%=qxqscol%>">
							<div align="center"><%=qxqs%></div>
						</td>
						<td style="background-color:<%=qxpdcol%>">
							<div align="center"><%=qxpd%></div>
						</td>
						<td style="background-color:<%=qxcscol%>">
							<div align="center"><%=qxcstj%></div>
						</td>
						<td style="background-color:<%=sjshcol%>">
							<div align="center"><%=shish%></div>
						</td>
						<td style="background-color:<%=sshcol%>">
							<div align="center"><%=shengsh%></div>
						</td>
						<td style="background-color:<%=sxfcol%>">
							<div align="center"><%=shengxf%></div>
						</td>
						<td style="background-color:<%=sjxfcol%>">
							<div align="center"><%=dsxf%></div>
						</td>
						<td style="background-color:<%=zgcol%>">
							<div align="center"><%=zgwczt%></div>
						</td>
						<td style="background-color:<%=sjshcol2%>">
							<div align="center"><%=sjsh%></div>
						</td>
						<td style="background-color:<%=sshcol2%>">
							<div align="center"><%=shengjsh%></div>
						</td>
						<!--
							<td>
							<div align="center">
								<a
									href="javascript:lookDetailssz('<%=id%>')">查看详细</a>
							</div>
						</td>
					--></tr>
					<%}} %>
				
				</table>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<input type="hidden" name="sheng2" id="sheng2" value="" />
						<input type="hidden" name="shi2" id="shi2" value="" />
						<input type="hidden" name="xian2" id="xian2" value="" />
					</td>
				</tr>
				<tr>
					<td align="right">
					 <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
							 <img src="<%=path %>/images/daochu.png" width="100%" height="100%">
						 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
				  </div> 
					</td>
				</tr>
				<tr>
					<td>
						<div id="detailsDiv"
							style="position: relative; width: 99%; height: 200px; left: 0px; top: 10px; z-index: 1; float: left; overflow-y: auto;">

							<iframe name="details" frameborder="0" width="100%" height="100%"
								scrolling="no"></iframe>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
<script type="text/javascript">	
	function lookDetailssz(id) {
		var path = '<%=path%>';
		window
				.open(path + "/web/jzcbnewfunction/shishxx.jsp?zdid=" + id, '',
						'width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
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
		document.form1.property.value='<%= property1%>';
	</script>
</html>

