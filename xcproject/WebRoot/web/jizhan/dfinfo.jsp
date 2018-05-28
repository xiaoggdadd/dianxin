<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.ZddfInfoForm" %>
<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String zdid = request.getParameter("id");
	ZddfInfoForm form = new ZddfInfoForm();
	form = form.getJizhan(zdid);//调用getJizhan（）方法  获取数据库里面的数据
%>
<html>
<head>
<title>
addAccount
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
		line-height:120%;
}
	
	.bttcn{color:white;font-weight:bold;}
 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
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
<script language="javascript">
var path = '<%=path%>';
	
	function saveAccount(){
	
		var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd  日期格式
	    var scjfsj=document.form1.scjftime.value;
	    var qdrq=document.form1.signdate.value;
	    var htqsrq=document.form1.origindate.value;
	    var htzzrq=document.form1.stopdate.value;
	    		if( reg.exec(scjfsj)||scjfsj==null||scjfsj==""
	   			   ){
	   			   if(reg.exec(qdrq)||qdrq==null||qdrq==""){
	   			    if(reg.exec(htqsrq)||htqsrq==null||htqsrq==""){
	   			    if(reg.exec(htzzrq)||htzzrq==null||htzzrq==""){
	   			    if(isNaN(document.form1.dfdj.value)==false){
	   			        //alert("111");
						document.form1.action=path+"/servlet/zhandian?action=zddfinfo"
						document.form1.submit()
						 }else{alert("单价信息输入有误，单价必须为数字！")}
						}else{alert("合同终止时间输入有误，请输入正确的日期格式！")}  
					  }else{alert("合同起始时间输入有误，请输入正确的日期格式！")}  
					}else{alert("签订日期输入有误，请输入正确的日期格式！")}  	
                  }else{alert("上次缴费时间输入有误，请输入正确的日期格式！")}     
        }
      $(function(){
		$("#cancelBtn").click(function(){
			window.history.back();
		});
		$("#resetBtn").click(function(){
			$.each($("form input[type='text']"),function(){
				$(this).val("");
			})
		});
		$("#saveBtn").click(function(){
			saveAccount();
		});
		});

       
</script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">

<form action="" name="form1" method="POST">
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
  	

	<tr>
    
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4"></td>
						</tr>
						
						<tr>
							<td colspan=1 width="600" background="<%=path%>/images/btt.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 修改站点电费表信息</span></td>
							
							<td width="380">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
						<tr>
							<td width="4"><br></td>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="64%" align="left" border="0" cellspacing="1" cellpadding="1">
			 <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />&nbsp;基本信息</td>
             </tr>
             <tr><td>&nbsp;</td></tr>
   
      
      <tr>
        <td height="8%" bgcolor="#DDDDDD" width="15%" class="form_label"><div align="left">付款方式：</div>
         </td>
         <td width="25%">
         	<select name="fkfs" style="width:130">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList fkfs = new ArrayList();
	         	fkfs = ztcommon.getSelctOptions("FKFS");
	         	if(fkfs!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)fkfs.get(0)).intValue();
	         		for(int i=cscount;i<fkfs.size()-1;i+=cscount)
                    {
                    	code=(String)fkfs.get(i+fkfs.indexOf("CODE"));
                    	name=(String)fkfs.get(i+fkfs.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>

         	</select>
         	</td>
         <td height="8%" bgcolor="#DDDDDD" width="25%" class="form_label"><div align="left">供电方式：</div>
         </td>
         <td width="25%">
         	<select name="gdfs" style="width:130">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList gdfs = new ArrayList();
	         	gdfs = ztcommon.getSelctOptions("GDFS");
	         	if(gdfs!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)gdfs.get(0)).intValue();
	         		for(int i=cscount;i<gdfs.size()-1;i+=cscount)
                    {
                    	code=(String)gdfs.get(i+gdfs.indexOf("CODE"));
                    	name=(String)gdfs.get(i+gdfs.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>

         	</select>
         	</td>
        </tr>
        <tr class="form_label">
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">付款周期：</div>
         </td>
         <td width="26%">
         		<select name="fkzq" style="width:130">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList fkzq = new ArrayList();
	         	fkzq = ztcommon.getSelctOptions("FKZQ");
	         	if(fkzq!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)fkzq.get(0)).intValue();
	         		for(int i=cscount;i<fkzq.size()-1;i+=cscount)
                    {
                    	code=(String)fkzq.get(i+fkzq.indexOf("CODE"));
                    	name=(String)fkzq.get(i+fkzq.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         </td>
      
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电费单价：</div>
         </td>
         <td width="25%"><input type="text" name="dfdj" value=""  class="form" style="width:130px"/>
         </td>
         </tr>
     
     
      <tr class="form_label">
      	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">收款单位名称：</div>
         </td>
         <td width="25%"><input type="text" name="skdwmc" value=""  class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">收款单位银行账号：</div>
         </td>
         <td width="25%">
         <input type="text" name="skdwzh" value=""  class="form" style="width:130px"/>
         </td>
            </tr>
    
      <tr class="form_label">
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">收款单位开户银行：</div>
         </td>
         <td width="25%"><input type="text" name="skdwyh" value=""  class="form" style="width:130px"/></td>
   
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">自报电用户号：</div>
         </td>
         <td width="25%">
         	<input type="text" name="zbdyhh" value=""  class="form" style="width:130px"/>
         </td>
            </tr>
    
      <tr class="form_label">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">上次缴费时间：</div>
         </td>
         <td width="25%">
         	<input type="text" name="scjftime" value="" onFocus="getDateString(this,oCalendarChs)"  class="form" style="width:130px"/>
         </td>
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">备注：</div>
         </td>
         <td width="26%"><input type="text" name="memo" value=""  class="form" style="width:130px"/>
         </td>
      </tr>
      
      
    
      <tr class="form_label">
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">合同编号：</div>
         </td>
         <td width="25%"><input type="text" name="agreementid" value=""  class="form" style="width:130px"/></td>
   
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">签订日期：</div>
         </td>
         <td width="25%">
         	<input type="text" name="signdate" value="" onFocus="getDateString(this,oCalendarChs)" class="form" style="width:130px"/>
         </td>
       </tr>
        <tr class="form_label">
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">合同起始日期：</div>
         </td>
         <td width="25%"><input type="text" name="origindate" value="" onFocus="getDateString(this,oCalendarChs)" class="form" style="width:130px"/></td>
   
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">合同终止日期：</div>
         </td>
         <td width="25%">
         	<input type="text" name="stopdate" value="" onFocus="getDateString(this,oCalendarChs)" class="form" style="width:130px"/>
         </td>
            </tr>    
         <tr class="form_label">
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">用电单位：</div>
         </td>
         <td width="25%"><input type="text" name="powerunit" value=""  class="form" style="width:130px"/></td>
   
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">用电单位联系人：</div>
         </td>
         <td width="25%">
         	<input type="text" name="unitlinkman" value=""  class="form" style="width:130px"/>
         </td>
            </tr>    
            
         <tr class="form_label">
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">用电单位联系电话：</div>
         </td>
         <td width="25%"><input type="text" name="unitphone" value=""  class="form" style="width:130px"/></td>
   
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">套表计费：</div>
         
         </td>
         <td width="25%">
         	<select name="watchcost" style="width:130">
         	    <option value="0">请选择</option>
         		<option value="1">否</option>
         		<option value="2">是</option>
         	</select>
         </td>
            </tr>    
            
      
    
    <tr><td>&nbsp;</td></tr>
    
    <tr class="form_label">
        <td colspan="6">
        <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:40px">
	           <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>      
          </div>
        <div id="resetBtn" style="position:relative;width:60px;height:23px;cursor: pointer;right:51px;float:right;">
			<img alt="" src="<%=path %>/images/2chongzhi.png" width="100%" height="100%" />
			<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">重置</span>
	    </div>
        
          <div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:62px">
	           <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>      
          </div>
          
        </td>
       
      </tr>
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
	   </td>
    </tr>
</table>
     
<input type="hidden" name="zdid" value="<%=zdid%>"/>
<input type="hidden" name="infoid" value="<%=form.getId()%>"/>
</form>
</body>
</html>
<script type="text/javascript">
<!--


document.form1.gdfs.value='<%=form.getGdfs()%>';
document.form1.fkzq.value='<%=form.getFkzq()%>';
document.form1.dfdj.value='<%=form.getDfdj()%>';

document.form1.fkfs.value='<%=form.getFkfs()%>';
document.form1.skdwmc.value='<%=form.getSkdwmc()%>';
document.form1.skdwzh.value='<%=form.getSkdwzh()%>';
document.form1.skdwyh.value='<%=form.getSkdwyh()%>';
document.form1.zbdyhh.value='<%=form.getZbdyhh()%>';


document.form1.agreementid.value='<%=form.getAgreementid()%>';


document.form1.powerunit.value='<%=form.getPowerunit()%>';
document.form1.unitlinkman.value='<%=form.getUnitlinkman()%>';
document.form1.unitphone.value='<%=form.getUnitphone()%>';
document.form1.watchcost.value='<%=form.getWatchcost()%>';

var jftime='<%=form.getScjftime()%>';
if(jftime!=""){
	jftime=jftime.substring(0,10);
}
document.form1.scjftime.value=jftime;
document.form1.memo.value='<%=form.getMemo()%>';





var sign='<%=form.getSigndate()%>';
if(sign!=""){
	sign=sign.substring(0,10);
}
document.form1.signdate.value=sign;

var origin='<%=form.getOrigindate()%>';
if(origin!=""){
	origin=origin.substring(0,10);
}
document.form1.origindate.value=origin;

var stop='<%=form.getStopdate()%>';
if(stop!=""){
	stop=stop.substring(0,10);
}
document.form1.stopdate.value=stop;


	
	///////////////////////////////////////////////////////////
	
//-->
</script>

