<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,com.noki.function.HtdMange" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.noki.ammeterdegree.javabean.BargainBean" %>
<%@ page import="java.util.Calendar" %>
<%
	String path = request.getContextPath();
    String id = request.getParameter("id");	
	Account account = (Account)session.getAttribute("account");
     String roleId = account.getRoleId();
     String uuid=request.getParameter("uuid");
    String accountname=account.getAccountName();
%>
<html>
<head>
<title>

</title>
<style>
           
 .style1 {
	color: #FF9900;
	font-weight: bold;
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 #id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
.selected_font1{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
		border-left-width:0px; border-top-width:0px; border-right-width:0px;
}
.selected_font3{
		width:130px;
		text-align: right;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 
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
	function saveDegree(){
	var m=document.getElementById("money").value;
	var fp=document.form1.fp.value;
	
			if(checkNotnull(document.form1.fpje,"发票金额")){
				if((isNaN(document.form1.fpje.value)==false)){ 
					var n=document.getElementById("fpje").value;
					//  alert("实际发票金额："+fp);
					//alert("发票金额(余额)："+n)
				if(Number(m)>=(Number(fp)+Number(n))&&Number(n)>0){
															  if(confirm("您将要修改合同单信息！确认信息正确！")){
												                    document.form1.action=path+"/servlet/NewHtdServlet?action=modify";
												        			document.form1.submit()
													          }
													          }else{
													          alert("您输入的发票金额有误！发票金额必须在0到"+document.form1.money.value+"之间！");
													          }
 			 }else{
	         	alert("您输入的信息有误，金额必须为数字！");
	         }   
	        }
	}
        function showIdList(){
        
               window.open('showBargainList.jsp','','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        function vCode(){
          var accCode = document.form1.workSn.value;
          if(accCode==""){
           	alert("不能为空！")
                   return
          }
               window.open('accountCode.jsp?accountId=0&accountCode='+accCode,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }

        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！")
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
         function getHaodianliang(){
        
          // alert("-===");
        	// var bl=document.form1.mpower.value;
        	// if(bl==null||bl=="")bl="1"
        	var dbili1=document.form1.dbili1.value;
        	var dbili2=document.form1.dbili2.value;
        	var dbili3=document.form1.dbili3.value;
        	var dbili4=document.form1.dbili4.value;
        	var dbili5=document.form1.dbili5.value;
        	var money= document.form1.money.value;
        	if(isNaN(document.form1.money.value)==false){}
        	 //生产
        	 if((dbili1 !="")&&isNaN(document.form1.dbili1.value)==false){
        		document.form1.networkdf.value=((parseFloat(dbili1)/100)*parseFloat(money)).toFixed(2);
 			}else{
 				document.form1.networkdf.value="0.00";
 			}
        	  //销售
        	if((dbili2 !="")&&isNaN(document.form1.dbili2.value)==false){
        	 	document.form1.marketdf.value=((parseFloat(dbili2)/100)*parseFloat(money)).toFixed(2);	
        	}else{
        	 	document.form1.marketdf.value="0.00";
 			}
        	 // ADMINISTRATIVEDF
        	 if((dbili3 !="")&&isNaN(document.form1.dbili3.value)==false){
        	 	document.form1.administrativedf.value=((parseFloat(dbili3)/100)*parseFloat(money)).toFixed(2);
        	 }else{
        	 	document.form1.administrativedf.value="0.00";
 			}
        	  
        	 // INFORMATIZATIONDF
        	 if((dbili4 !="")&&isNaN(document.form1.dbili4.value)==false){	
        	 	document.form1.informatizationdf.value=((parseFloat(dbili4)/100)*parseFloat(money)).toFixed(2);
        	 }else{
        	 	document.form1.informatizationdf.value="0.00";
 			 }
        	 // BUILDDF
        	 if((dbili5 !="")&&isNaN(document.form1.dbili5.value)==false){	
        	 	document.form1.builddf.value=((parseFloat(dbili5)/100)*parseFloat(money)).toFixed(2); 
        	 }else{
        	 	document.form1.builddf.value="0.00";
 			 }       	        	 
         } 
        
	$(function(){
        $("#saveBtn").click(function(){
		   saveDegree();
		});
        $("#resetBtn").click(function() {	
        	$("#form")[0].reset();
        });
         $("#liulan").click(function(){
		   showIdList();
		});
         
		$("#cancelBtn").click(function(){
			window.history.back();
		});
        
	});
</script>
</head>
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean">
</jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
<form action="" name="form1" method="POST" id="form">
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
  	<tr>
    <td width="10"></td>
    <td valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="2"></td>
      </tr>
      
    </table></td>
    <td width="12"></td>
  </tr>

	<tr>
    <td width="10" height="500">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
						<tr>
							<td colspan="4"></td>
						</tr>
						<tr>
							<td colspan="4" width="50" >
								<div style="width:700px;height:50px">
									
									<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">合同单补录发票</span>	
									<input type="hidden" name="accountname"  value="<%=accountname %>">
								</div>
						   </td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
						<tr>
							<td width="4"></td>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="form_label">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1" class="form_label">

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
		<%		
			String money1="",pjsj="",pjlx1="",ye="",pjje1="",accountmonth="",fplx="",fpje="",dflx="",dbname="",memo="",
			stm="",endm="",networkdf1="",zdcode="",jzname="",dbid="",administrativedf1="",marketdf1="",informatizationdf1="",
			builddf1="",dddf1="";
			HtdMange beana = new HtdMange();
			double pjje=0;
			List<HtdMange> list=beana.getBargainDana(uuid);
			for(HtdMange bean:list){
		    String kpsj=bean.getKptime();
			double ddd=bean.getPjje();
			if(kpsj==null||"".equals(kpsj)||"null".equals(kpsj)){
			 kpsj="";
			}
		jzname=	bean.getJzname();
		if(jzname==null||"null".equals(jzname)){
			 jzname="";
			}
			//格式化单价
            String dianfei = bean.getDanjia();
            if(dianfei==null||dianfei.equals("")||dianfei.equals("null")||dianfei.equals("o")){
				dianfei="0.0000";
            }
            DecimalFormat mat1 =new DecimalFormat("0.0000");           
	        dianfei=mat1.format(Double.parseDouble(dianfei));	
			//格式化金额、生产、办公、营业、信息化支撑、建设投资
			DecimalFormat mat =new DecimalFormat("0.00");
			money1=bean.getMoney();
			networkdf1 = bean.getNetworkdf();
			administrativedf1 = bean.getAdministrativedf();
			marketdf1 = bean.getMarketdf();
			informatizationdf1 = bean.getInformatizationdf();
			builddf1 = bean.getBuilddf();
			dddf1 = bean.getDddf();
			String htbh1=bean.getHtbianhao();
			if(null==htbh1||"null".equals(htbh1)){
			htbh1="";
			}
			if(money1==null||"".equals(money1)||"null".equals(money1)||"o".equals(money1)){
				money1="0.00";
		 }
                 if(ddd==0||ddd==0.0){
                 ddd=Double.parseDouble(money1);
                 }                  
	        money1=mat.format(Double.parseDouble(money1));
			if(networkdf1==null||"".equals(networkdf1)||"null".equals(networkdf1)||"o".equals(networkdf1)){
				networkdf1="0.00";
            }         
	        networkdf1=mat.format(Double.parseDouble(networkdf1));
	        if(administrativedf1==null||"".equals(administrativedf1)||"null".equals(administrativedf1)||"o".equals(administrativedf1)){
				administrativedf1="0.00";
            }                       
	        administrativedf1=mat.format(Double.parseDouble(administrativedf1));
			if(marketdf1==null||"".equals(marketdf1)||"null".equals(marketdf1)||"o".equals(marketdf1)){
				marketdf1="0.00";
            }                       
	        marketdf1=mat.format(Double.parseDouble(marketdf1));
	        if(informatizationdf1==null||"".equals(informatizationdf1)||"null".equals(informatizationdf1)||"o".equals(informatizationdf1)){
				informatizationdf1="0.00";
            }                       
	        informatizationdf1=mat.format(Double.parseDouble(informatizationdf1));
	        if(builddf1==null||"".equals(builddf1)||"null".equals(builddf1)||"o".equals(builddf1)){
				builddf1="0.00";
            }                       
	        builddf1=mat.format(Double.parseDouble(builddf1));			
			 dbid=bean.getPrepayment_ammeterid();
			
			
			
			
			AmmeterDegreeFormBean bean1 = new AmmeterDegreeFormBean();
		 	ArrayList listt=new ArrayList();
		    listt=bean1.getStationMhchexkt(dbid);
		    String shuoshuzhuanye="";
		    String dbili="",dbili1="",dbili2="",dbili3="",dbili4="",dbili5="";
		    if(listt!=null)
		{
			int fycount = ((Integer)listt.get(0)).intValue();
			for( int k = fycount;k<listt.size()-1;k+=fycount){
			shuoshuzhuanye = (String)listt.get(k+listt.indexOf("SHUOSHUZHUANYE"));
		    shuoshuzhuanye = shuoshuzhuanye != null ? shuoshuzhuanye : "";
		    dbili = (String)listt.get(k+listt.indexOf("DBILI"));
		    dbili = dbili != null ? dbili : "0";
		    if(shuoshuzhuanye.equals("zylx01")){
		       dbili1=dbili;
		    }
		     if(shuoshuzhuanye.equals("zylx02")){
		      dbili2=dbili;		    
		    }
		     if(shuoshuzhuanye.equals("zylx03")){
		    
		      dbili3=dbili;
		    }
		     if(shuoshuzhuanye.equals("zylx04")){
		      dbili4=dbili;
		    
		    }
		     if(shuoshuzhuanye.equals("zylx05")){
		      dbili5=dbili;
		    
		    }
		    }
		    }   
     zdcode= bean.getZdcode();
     if(null==zdcode||"null".equals(zdcode)){
      zdcode="";
     }
     //dbname="",memo="",stm="",endm="",
    dbname=bean.getDbname();
   if(null==dbname||"null".equals(dbname)){
      dbname="";
     }
    memo=bean.getMemo();
	if(null==memo||"null".equals(memo)){
	memo="";
	}
	stm=bean.getStartmonth();
	if(null==stm||"null".equals(stm)){
	stm="";
	}
	endm=bean.getEndmonth();
	if(null==endm||"null".equals(endm)){
	endm="";
	}
	pjsj=bean.getNotetime();
	if(null==pjsj||"null".equals(pjsj)){
	pjsj="";
	}
	 dflx= bean.getDfzflx();
	 if(null==dflx||"null".equals(dflx)){
	dflx="";
	}
	accountmonth=bean.getAccountmonth();
	 if(null==accountmonth||"null".equals(accountmonth)){
	accountmonth="";
	}
	fplx=bean.getFplx();
	 if(null==fplx||"null".equals(fplx)){
	fplx="";
	}
	fpje=bean.getFpje();
	 if(null==fpje||"null".equals(fpje)){
	fpje="0";
	}
	
	pjlx1=bean.getNotetypeid();
	 if(null==pjlx1||"null".equals(pjlx1)){
	pjlx1="";
	}
	pjje=bean.getPjje();
	  DecimalFormat w =new DecimalFormat("0.00");     
	  pjje1=w.format(pjje);
	  
	  ye=w.format(Double.parseDouble(money1)-Double.parseDouble(fpje));
	  
		%>
		<tr><td>&nbsp;</td></tr>
		<tr bgcolor="F9F9F9">
           <td height="19" colspan="4">　<img src="../../../images/v.gif" width="15" height="15" />基本信息</td>
        </tr>
       	<tr><td>&nbsp;</td></tr>
       <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>站点名称：</div></td>
         <td width="35%"><input type="text" name="stationname" value="<%=jzname%>" class="selected_font1" readonly = true/></td>
          <td height="19" bgcolor="#DDDDDD" width="15%"><div>站点编号：</div></td>
         <td width="35%"><input type="text" name=zdbh value="<%=zdcode%>"  class="selected_font1" readonly = true/></td>
         <input type="hidden" name="zdid" value="<%=bean.getStationid() %>">
      </tr>      
     <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>电表ID：</div></td>
         <td width="35%">
               <input type="text" name="ammeteridFk" value="<%=dbid%>" class="selected_font1" readonly = true/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div>电表名称：</div></td>
         <td><input type="text" name="initialdate" value="<%=dbname%>" class="selected_font1" maxlength="11" readonly = true/></td>  
      </tr>
      <tr>         
      	 <td height="19" bgcolor="#DDDDDD" width="15%"><div>金额：</div></td>
         <td width="35%"><input type="text" name="money"  class="selected_font1" value="<%=money1 %>" readonly = true/>
         </td>
         <input type="hidden" name="id"  value="<%=bean.getId()%>"  class="form">
         <td height="19" bgcolor="#DDDDDD"><div>备注：</div></td>
         <td><input type="text" name="memo" value="<%=memo%>" class="selected_font1" readonly = true/></td>          
      </tr>
      <tr>
         <td height="19" bgcolor="#DDDDDD"><div>起始年月：</div></td>
         <td><input type="text" name="startmonth" value="<%=stm%>"  class="selected_font1" readonly = true/>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>结束年月：</div></td>
         <td><input type="text" name="endmonth" value="<%=endm%>"  class="selected_font1" readonly = true/>
         </td>
      </tr>
      <tr> 
         <td height="19" bgcolor="#DDDDDD"><div>票据时间：</div></td>
         <td><input type="text" name="notetime" value="<%=pjsj%>"  class="selected_font1"  readonly = true maxlength="11"/></td>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>开票时间：</div></td>
         
         <td><input type="text" name="kptime" value="<%=kpsj%>"  class="selected_font1" readonly = true/></td>
      </tr>
      <tr>
		 <td height="19" bgcolor="#DDDDDD"><div>电费支付类型：</div></td>
       <td><input type="text" name="bargain" value="<%=dflx%>"  class="selected_font1" readonly = true/></td> 
          
         <td height="19" bgcolor="#DDDDDD"><div>报账月份：</div></td>
         <td><input type="text" name="accountmonth" value="<%=accountmonth%>"  class="selected_font1" readonly = true/>
         </td>               
      </tr>  

      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>票据类型：</div>
         </td>
         <td><input type="text" name="notetypeid" value="<%=pjlx1%>"  class="selected_font1" readonly = true/></td>
          <td height="19" bgcolor="#DDDDDD"><div>票据金额：</div> </td>
         <td><input type="text" name="pjje" value="<%=pjje1%>"  class="selected_font1" readonly = true/></td>
      </tr>
      <tr><td  height="19" bgcolor="#DDDDDD" width="15%">合同编号</td>
      <td><input type="text" id="htbianhao" name="htbianhao" value="<%=htbh1%>" class="selected_font1" readonly = true></td>
      </tr>
      <tr bgcolor="F9F9F9">
           <td height="19" colspan="4">　<img src="../../../images/v.gif" width="15" height="15" />分摊信息</td>
       </tr> 
      <tr>
      	 <td height="19" bgcolor="#DDDDDD"><div>生产：</div></td>
         <td><input type="text" name="networkdf" value="<%=networkdf1%>" class="selected_font1" readonly = true/></td>    
         <td height="19" bgcolor="#DDDDDD"><div>办公：</div></td>
         <td><input type="text" name="administrativedf" value="<%=administrativedf1%>" class="selected_font1" readonly = true/></td>
      </tr>
      <tr>
      	 <td height="19" bgcolor="#DDDDDD"><div>营业：</div></td>
         <td><input type="text" name="marketdf" value="<%=marketdf1%>" class="selected_font1" readonly = true/></td>
      	 <td height="19" bgcolor="#DDDDDD"><div>信息化支撑：</div></td>
         <td><input type="text" name="informatizationdf" value="<%=informatizationdf1%>" class="selected_font1" readonly = true/></td> 
      </tr>
      <tr>
         <td height="19" bgcolor="#DDDDDD"><div>建设投资：</div></td>
         <td><input type="text" name="builddf" value="<%=builddf1%>" class="selected_font1" readonly = true/></td>
           <td height="19" bgcolor="#DDDDDD"><div>代垫电费：</div></td>
         <td><input type="text" name="dddf" value="<%=dddf1%>" class="selected_font1" readonly = true/></td>
         <td ><input type="hidden" name="dbili1" value="<%=dbili1%>"></td>
         <td ><input type="hidden" name="dbili2" value="<%=dbili2%>"></td>
         <td ><input type="hidden" name="dbili3" value="<%=dbili3%>"></td>
         <td ><input type="hidden" name="dbili4" value="<%=dbili4%>"></td>
         <td ><input type="hidden" name="dbili5" value="<%=dbili5%>"></td>
         <td ><input type="hidden" name="danjia" value="<%=dianfei%>"></td>  
         <td ><input type="hidden" name="uuid" value="<%=uuid%>"></td>    
         <td ><input type="hidden" name="fp" value="<%=fpje%>"></td>       
      </tr>  
        <tr bgcolor="F9F9F9">
           <td height="19" colspan="4">　<img src="../../../images/v.gif" width="15" height="15" />发票信息</td>
       </tr> 
       <tr>
       
        <td height="19" bgcolor="#DDDDDD"><div>发票类型：</div></td>
       <td><input type="text" name="fplx" id="fplx" value="<%=fplx%>" class="selected_font1" readonly = true/></td>  
      <td height="19" bgcolor="#DDDDDD"><div>发票金额：</div></td>
       <td><input type="text" name="fpje" id="fpje" value="<%=ye%>" class="selected_font3"/><font color="red">(含多次)</font></td>  
       </tr>
       <%}%>
      <tr>
        <td colspan=4">
        <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:250px;top: 30px;">
			<img src="<%=path%>/images/quxiao.png" width="100%" height="100%">
			<span style="font-size: 12px; position: absolute; left: 27px; top: 3px; color: white">返回</span>
		</div>
         <div id="resetBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:260px;top: 30px;">
			 <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:3px;color:white">重置</span>      
		</div>
         <div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:270px;top: 30px;">
			 <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:3px;color:white">保存</span>      
		</div>
	</td>
      </tr>

      <%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>

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
</td>
</tr>
</table>      
</form>

</body>
</html>


