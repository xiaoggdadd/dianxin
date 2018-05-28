<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean" %>

<%
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
        String roleId = account.getRoleId();
        String accountname=account.getAccountName();
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
bttcn{color:white;font-weight:bold;}

.form    {width:130px}
 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 
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
	 if(checkNotnull(document.form1.actualpay,"实际电费金额")&&checkNotnull(document.form1.startmonth,"起始年月")&&checkNotnull(document.form1.endmonth,"结束年月")&&checkNotnull(document.form1.thisdatetime,"本次抄表的时间")&&checkNotnull(document.form1.floatpay,"费用调整")&&checkNotnull(document.form1.actualdegree,"实际用电")&&checkNotnull(document.form1.unitprice,"本次单价")&&checkNotnull(document.form1.accountmonth,"报账月份")&&checkNotnull(document.form1.paydatetime,"交费时间")){
           var qsny = document.form1.startmonth.value;
           var jsny = document.form1.endmonth.value;
           var tdt = document.form1.thisdatetime.value;
           var ldt = document.form1.lastdatetime.value;
           var idt = document.form1.inputdatetime.value;
           
           if(qsny<=jsny&&qsny<=idt&&ldt<=tdt&&tdt<=idt){
		       if(isNaN(document.form1.actualpay.value)==false){
				        if(confirm("您将要添加电费信息！确认信息正确！")){
				            document.form1.action=path+"/servlet/electricfees?action=add"
						     document.form1.submit()
				         } 
	            } else{
	            alert("您输入的信息有误，实际电费金额必须为数字！");
	            }
            }else{
            alert("您输入的时间信息有误，请重新输入！");
            }            
      }
	}
        function showIdList(){
        
               window.open('showDegreeIdList.jsp','','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
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
          
        $(function(){
			
			$("#resetBtn").click(function(){
				$("#form")[0].reset();
			});
			$("#baocun").click(function(){
				saveDegree();
			});
			$("#cancelBtn").click(function(){
				window.history.back();
			});
			$("#showid").click(function(){
				showIdList();
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
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
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
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						
						<tr>
							<td colspan=3 width="600" background="<%=path%>/images/btt2.bmp" height="63"><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加电费</span></td>
							
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

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
		<%
			String degreeid = request.getParameter("degreeid");
			ElectricFeesFormBean bean = new ElectricFeesFormBean();
		    bean = bean.getElectricFeesAllInfo(degreeid);
		    ElectricFeesFormBean beanprice = new ElectricFeesFormBean();
		    beanprice = beanprice.getJizhanPrice(degreeid);
		    
		    String proviceid=bean.getProvinceid() != null ? bean.getProvinceid() : "";
		    proviceid = proviceid != null ? proviceid : "";
		    String cityid=bean.getCityid() != null ? bean.getCityid() : "";
		    cityid = cityid != null ? cityid : "";
		    String countryid=bean.getCountryid() != null ? bean.getCountryid() : "";
		    countryid = countryid != null ? countryid : "";
		    String area = proviceid+" "+cityid+" "+countryid;
		    String stationname=bean.getStationname();
		    stationname = stationname != null ? stationname : "";
		    String zdcode=bean.getZdcode();
		    zdcode = zdcode != null ? zdcode : "";
		    String stationtype=bean.getStationtypeid();
		    stationtype = stationtype != null ? stationtype : "";
		    String isbenchmarkstation=bean.getIsbenchmarkstation();
		    isbenchmarkstation = isbenchmarkstation != null ? isbenchmarkstation : "";
		    String stationaliasname=bean.getStationaliasname();
		    stationaliasname = stationaliasname != null ? stationaliasname : "";
		    String ammeterdegreeid=bean.getAmmeterdegreeid();
		    ammeterdegreeid = ammeterdegreeid != null ? ammeterdegreeid : "";
		    String ammererid=bean.getAmmererid();
		    ammererid = ammererid != null ? ammererid : "";
		    String professionaltypeid=bean.getProfessionaltypeid();
		    professionaltypeid = professionaltypeid != null ? professionaltypeid : "";
		    String multiplyingpower=bean.getMultiplyingpower();
		    multiplyingpower = multiplyingpower != null ? multiplyingpower : "";
		    String ammerertype=bean.getAmmerertype();
		    ammerertype = ammerertype != null ? ammerertype : "";
		    String initialdegree=bean.getInitialdegree();
		    initialdegree = initialdegree != null ? initialdegree : "";
		    String initialdate=bean.getInitialdate();
		    initialdate = initialdate != null ? initialdate : "";
		    String floatdegree=bean.getFloatdegree();
		    floatdegree = floatdegree != null ? floatdegree : "";
		    String actualdegree=bean.getActualdegree();
		    actualdegree = actualdegree != null ? actualdegree : "";
		    
		    String lastdegree=bean.getLastdegree();
		    lastdegree = lastdegree != null ? lastdegree : "";
		    String lastdatetime=bean.getLastdatetime();
		    lastdatetime = lastdatetime != null ? lastdatetime : "";
		    String thisdegree=bean.getThisdegree();
		    thisdegree = thisdegree != null ? thisdegree : "";
		    String thisdatetime=bean.getThisdatetime();
		    thisdatetime = thisdatetime != null ? thisdatetime : "";
		    String inputoperatoram=bean.getInputoperatoram();
		    inputoperatoram = inputoperatoram != null ? inputoperatoram : "";
		    String inputdatetimeam=bean.getInputdatetimeam();
		    inputdatetimeam = inputdatetimeam != null ? inputdatetimeam : "";
		    String startmonth=bean.getStartmonth();
		    startmonth = startmonth != null ? startmonth : "";
		    String endmonth=bean.getEndmonth();
		    endmonth = endmonth != null ? endmonth : "";
		    String memoam=bean.getMemoam();
		    memoam = memoam != null ? memoam : "";
		    
		%>
		<br/>
		<tr bgcolor="F9F9F9">
           <td height="19" colspan="4">　<img src="../../images/v.gif" width="15" height="15" />基本信息</td>
           <input type="hidden" name="accountname" value="<%=accountname %>"/>
       </tr>
       	<tr><td>&nbsp;</td></tr>	
       <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>站点编号：</div>
         </td>
         <td width="35	%"><input type="text" name="zdcode" readonly="true" value="<%=zdcode %>"  class="form" />

         </td>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>基站名称：</div>
         </td>
         <td width="35	%"><input type="text" name="stationname" readonly="true" value="<%=stationname %>"  class="form" />
         </td>

      <tr>

      <tr>
       
          <td height="19" bgcolor="#DDDDDD" width="15%"><div>电表ID：</div>
         </td>
         <td width="35	%"><input type="text" name="ammererid"  readonly="true" value="<%=ammererid %>"  class="form" />

         </td>
         
      <td height="19" bgcolor="#DDDDDD"><div>所属专业：</div>
         </td>
         <td><input type="text" name="professionaltypeid"  readonly="true" value="<%=professionaltypeid %>"  class="form" maxlength="11"/>
         </td>
                 
      </tr>
      
     <!-- <tr>
      <td height="19" bgcolor="#DDDDDD" width="15%"><div align="center">是否标杆</div>
         </td>
         <td><input type="text" name="isbenchmarkstation" value="<%=isbenchmarkstation %>"  class="form" />
         </td>
         
      <td height="19" bgcolor="#DDDDDD"><div align="center">基站别名</div>
         </td>
         <td><input type="text" name="stationaliasname" value="<%=stationaliasname %>"  class="form" maxlength="11"/>
         </td>
         
      </tr> --> 
       
        <tr>
          <td height="15" colspan="4"></td>
      </tr>
      <tr>
          <td height="3" colspan="2"></td>
      </tr>
      <tr>
          <td height="15" colspan="4"></td>
      </tr>
       
      <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>电量流水ID：</div>
         </td>
         <td width="35%"><input type="text" name="ammeterdegreeid"  readonly="true" value="<%=ammeterdegreeid %>" readonly="true"  class="form" /><span class="style1">&nbsp;*</span>
          

         </td>
         <td>  <div id="showid" style="width:21px;height:21px;cursor:pointer;float:right;position:relative;right:360px;">
	           <img src="<%=path %>/images/liulan1.png" width="100%" height="100%">
          </div></td>
        
      </tr>

      <tr>
                 <td height="19" bgcolor="#DDDDDD" width="15%"><div>上次电表读数：</div>
         </td>
         <td width="35	%"><input type="text" name="lastdegree" readonly="true" value="<%=lastdegree %>" onChange="javascript:document.form1.actualdegree.value=(parseFloat(document.form1.thisdegree.value-document.form1.lastdegree.value)+parseFloat(document.form1.floatdegree.value))" class="form" />

         </td>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>本次电表读数：</div>
         </td>
         <td><input type="text" name="thisdegree"  readonly="true" value="<%=thisdegree %>" onChange="javascript:document.form1.actualdegree.value=(parseFloat(document.form1.thisdegree.value-document.form1.lastdegree.value)+parseFloat(document.form1.floatdegree.value)).toFixed(2);
         
         document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.actualdegree.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2)" class="form" />
         </td>
         

      <tr>

      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>上次抄表时间：</div>
             <% 
         if(lastdatetime.equals("0")){
         %>
         <td><input type="text" name="lastdatetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" maxlength="11"/> </td>
<% 
}else{
%>
 <td><input type="text" name="lastdatetime" value="<%=lastdatetime%>" onFocus="getDateString(this,oCalendarChs)" class="form" maxlength="11"/> </td>
       <%
       }
        %> 
         <td height="19" bgcolor="#DDDDDD"><div>本次抄表时间：</div>
         </td>
         <td><input type="text" name="thisdatetime"  readonly="true" value="<%=thisdatetime %>" onFocus="getDateString(this,oCalendarChs)" class="form"  align="right"/>
         </td>
         
      </tr>


      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>用电量调整：</div>
         </td>
         <td><input type="text" name="floatdegree"  readonly="true" value="<%=floatdegree %>" onChange="javascript:document.form1.actualdegree.value=(parseFloat(document.form1.thisdegree.value-document.form1.lastdegree.value)+parseFloat(document.form1.floatdegree.value)).toFixed(2);
         document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.actualdegree.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2)" class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>实际用电量：</div>
         </td>
         <td><input type="text" name="actualdegree"  readonly="true" value="<%=actualdegree %>"   onchange="javascript:document.form1.floatdegree.value=(parseFloat(document.form1.actualdegree.value-parseFloat(document.form1.thisdegree.value-document.form1.lastdegree.value))).toFixed(2);
         document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.actualdegree.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2)" class="form" />
           </td>
      </tr>

          <tr>
      <td height="19" bgcolor="#DDDDDD"><div>抄表操作员：</div>
         </td>
         <td><input type="text" name="inputoperator" value="<%=inputoperatoram %>"  class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>当前时间：</div>
         </td>
         <td><input type="text" name="inputdatetime" value="<%=inputdatetimeam %>" onFocus="getDateString(this,oCalendarChs)" class="form" />
           </td>
      </tr>
     <tr>
      <td height="19" bgcolor="#DDDDDD"><div>起始时间：</div>
         </td>
         <td><input type="text" name="startmonth" value="<%=startmonth %>" onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>结束时间：</div>
         </td>
         <td><input type="text" name="endmonth" value="<%=endmonth %>" onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
           </td>
      </tr>
    
      <tr>
         <td height="19" bgcolor="#DDDDDD" ><div>备注：</div>
         </td>
         <td colspan="2"><input type="text" size="50" name="memo" value="<%=memoam %>"  class="form" />
         </td>        
      </tr>
      
       <tr>
          <td height="15" colspan="4"></td>
      </tr>
      <tr>
          <td height="3" colspan="2"></td>
      </tr>
      <tr>
          <td height="15" colspan="4"></td>
      </tr>
      
      <tr>
                 <td height="19" bgcolor="#DDDDDD" width="15%"><div>本次单价：</div>
         </td>
         <td width="35	%"><input type="text" name="unitprice" readonly="true"  value="<%=beanprice.getSysprice() %>" onChange="javascript:document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.actualdegree.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2)" class="form" />

         </td>
      <td height="19" bgcolor="#DDDDDD" width="15%"><div>费用调整：</div>
         </td>
         <td><input type="text" name="floatpay" value="0" onChange="javascript:document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.actualdegree.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2)" class="form" />
         </td>
     <tr>

      <tr> 
      <td height="19" bgcolor="#DDDDDD"><div>实际电费金额：</div>
         </td>
         <td><input type="text" name="actualpay" value=""  class="form" maxlength="11"/>

         </td>
         <td height="19" bgcolor="#DDDDDD"><div>票据时间：</div>
         </td>
         <td><input type="text" name="notetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" maxlength="11"/>

         </td>
         
      </tr>


      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>票据类型：</div>
         </td>
         <td>   
         <select name="notetypeid" style="width:130">
         		<option value="pjlx03">请选择</option>
	         <%
	         	ArrayList feetypelist = new ArrayList();
	         	feetypelist = commBean.getNoteType();
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
          </td>  
      <td height="19" bgcolor="#DDDDDD"><div>票据编号：</div>
         </td>
         <td><input type="text" name="noteno" value=""  class="form" />
         </td>
      </tr>

          <tr>
      <td height="19" bgcolor="#DDDDDD"><div>交费操作员：</div>
         </td>
         <td><input type="text" name="payoperator" value=""  class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>交费时间：</div>
         </td>
         <td><input type="text" name="paydatetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" />
         <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
           </td>
      </tr>
     <tr>
      <td height="19" bgcolor="#DDDDDD"><div>报账月份：</div>
         </td>
         <td><input type="text" name="accountmonth" value="" onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
         <span style="color: #FF0000;font-weight: bold">&nbsp;*</span>
         </td>
          <td height="19" bgcolor="#DDDDDD" width="15%"><div>开票时间：</div>
         </td>
         <td><input type="text" name="kptime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" />
         </td>
      </tr>
       <tr>
         <td height="19" bgcolor="#DDDDDD"><div>备注：</div>
         </td>
         <td><input type="text" name="memo" value=""  class="form" />
           </td>
      </tr>
      <tr>
          <td height="15" colspan="4"></td>
      </tr>

        <tr>
        <td colspan="4" align="right">

         <div id="cancelBtn" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 260px">
				<img src="<%=path%>/images/quxiao.png" width="100%"
					height="100%">
				<span
					style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
			</div>

			<div id="resetBtn"
				style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 270px">
				<img src="<%=path%>/images/2chongzhi.png" width="100%"
					height="100%">
				<span
					style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
			</div>
			<div id="baocun"
				style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:280px">
				<img alt=""
					src="<%=request.getContextPath()%>/images/baocun.png"
					width="100%" height="100%" />
				<span
					style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">保存</span>
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
     
</form>
</body>
</html>
<script type="text/javascript">
   document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.actualdegree.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2);
</script>

