<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.ZLInfoForm" %>
<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String zdid = request.getParameter("id");
	ZLInfoForm form = new ZLInfoForm();
	form = form.getJizhan(zdid);
	System.out.println(">>>>>>>>>>>>>>"+form.getId());
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
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: 

progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px 

solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
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
			               var qssj=document.form1.qstime.value;
			               var sxsj=document.form1.sxtime.value;
			               var xqsj=document.form1.xqtime.value;
			               var sxtime=document.form1.offtime.value;
			               var fzzhjnsj=document.form1.zhjntime.value;
			               if(reg.exec(qssj)||qssj==null||qssj==""){
			                if(reg.exec(sxsj)||sxsj==null||sxsj==""){
			                 if(reg.exec(xqsj)||xqsj==null||xqsj==""){
			                  if(reg.exec(sxtime)||sxtime==null||sxtime==""){
			                  	if(reg.exec(fzzhjnsj)||fzzhjnsj==null||fzzhjnsj==""){
			                    //alert("111");
								document.form1.action=path+"/servlet/zhandian?action=zlinfo"
								document.form1.submit();
							   }else{alert("房租最后缴纳时间输入有误，请输入正确的日期格式！")}
							  }else{alert("失效时间输入有误，请输入正确的日期格式！")}
							 }else{alert("续签时间输入有误，请输入正确的日期格式！")}
							}else{alert("生效时间输入有误，请输入正确的日期格式！")}
             			   }else{alert("签署时间输入有误，请输入正确的日期格式！")}
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
							<td colspan=1 width="600" background="<%=path%>/images/btt.bmp" 

height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 基站租赁信息

</span></td>
							
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
												<table width="100%" align="left" border="0" cellspacing="1" cellpadding="1">
			 
			 <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4">&nbsp;&nbsp;<img src="../../images/v.gif" width="15" height="15" />&nbsp;基本信息</td>
                    </tr>
    		 <tr><td>&nbsp;</td></tr>
      
      <tr class="form_label">
         <td height="25%" bgcolor="#DDDDDD" width="12%"><div align="left">合同ID：</div>
         </td>
         <td width="13%">
         	<input type="text" name="htid" value=""  class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">合同类型：</div>
         </td>
         <td width="13%">
         	<select name="htlx" style="width:130">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList htlx = new ArrayList();
	         	htlx = ztcommon.getSelctOptions("HTLX");
	         	if(htlx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)htlx.get(0)).intValue();
	         		for(int i=cscount;i<htlx.size()-1;i+=cscount)
                    {
                    	code=(String)htlx.get(i+htlx.indexOf("CODE"));
                    	name=(String)htlx.get(i+htlx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>

         	</select>
         	</td>
        </tr>
        <tr class="form_label">
					<td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">基站地址：</div>
         </td>
         <td width="13%">
         		<input type="text" name="jzdz" value=""  class="form" style="width:130px"/>
         </td>
      
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">房屋面积：</div>
         </td>
         <td width="13%"><input type="text" name="fwmj" value=""  class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">楼层：</div>
         </td>
         <td width="13%"><input type="text" name="louc" value=""  class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">签约单位：</div>
         </td>
         <td width="13%"><input type="text" name="qydw" value=""  class="form" style="width:130px"/>
         </td>
      </tr>
      
       <tr class="form_label">
					<td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">付款方式：</div>
         </td>
         <td width="13%">
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
      
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">收款单位名称：</div>
         </td>
         <td width="13%"><input type="text" name="skdwmc" value=""  class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">收款单位银行账号：</div>
         </td>
         <td width="13%"><input type="text" name="skdwzh" value=""  class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">收款单位开户银行：</div>
         </td>
         <td width="13%"><input type="text" name="skdwyh" value=""  class="form" style="width:130px"/>
         </td>
      </tr>
     
       <tr class="form_label">
		 <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">联通签约人：</div></td>
         <td width="13%">
         	<input type="text" name="ltqyr" value=""  class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">合同方联系人：</div>
         </td>
         <td width="13%"><input type="text" name="htlxr" value=""  class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">合同方联系电话：</div>
         </td>
         <td width="13%"><input type="text" name="htlxtel" value=""  class="form" style="width:130px"/>
         </td>
        
       </tr>
       <tr class="form_label">
		 <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">房租基数金额：</div></td>
         <td width="13%"><input type="text" name="fzjsjine" value=""  class="form" style="width:130px"/></td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">房租是否开票：</div> </td>
         <td width="13%"><input type="checkbox" name="fzsfkp"  /></td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">房租增长率：</div></td>
         <td width="13%"><input type="text" name="fzzzl" value=""  class="form" style="width:130px"/></td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">房租最后交纳时间：</div></td>
         <td width="13%"><input type="text" name="zhjntime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" style="width:130px"/></td>
      </tr>
       <tr class="form_label">
		 <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">签署时间：</div></td>
         <td width="13%">
         	<input type="text" name="qstime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">生效时间：</div>
         </td>
         <td width="13%"><input type="text" name="sxtime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">续签时间：</div>
         </td>
         <td width="13%"><input type="text" name="xqtime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">失效时间：</div>
         </td>
         <td width="13%"><input type="text" name="offtime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" style="width:130px"/>
         </td>
      </tr>
       <tr class="form_label">
		 <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">合同续签预警值：</div></td>
         <td width="13%">
         		<input type="text" name="xqyjz" value=""  class="form" style="width:130px"/>
         </td>
      
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">缴纳房租预警值：</div>
         </td>
         <td width="13%"><input type="text" name="fzyjz" value=""  class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="12%"><div align="left">备注：</div>
         </td>
         <td width="13%"><input type="text" name="memo" value=""  class="form" style="width:130px"/>
         </td>
      
      </tr>
    <tr class="form_label">
        <td colspan="8">
		<div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:14px">
			<img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
			<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>      
		</div>
		<div id="resetBtn" style="position:relative;width:60px;height:23px;cursor: pointer;right:25px;float:right;">
			<img alt="" src="<%=path %>/images/2chongzhi.png" width="100%" height="100%" />
			<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">重置</span>
		</div>
		<div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:36px">
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
<input type="hidden" name="id" value="<%=form.getId()%>"/>
</form>
</body>
</html>
<script type="text/javascript">
 
<!--
var id  = '<%=form.getId()%>';
if(id!='0'){
		document.form1.htid.value='<%=form.getHtid()%>';
    document.form1.htlx.value='<%=form.getHtlx()%>';
    document.form1.jzdz.value='<%=form.getJzdz()%>';
    document.form1.fwmj.value='<%=form.getFwmj()%>';
    document.form1.louc.value='<%=form.getLouc()%>';
    document.form1.qydw.value='<%=form.getQydw()%>';
    document.form1.fkfs.value='<%=form.getFkfs()%>';
    document.form1.skdwmc.value='<%=form.getSkdwmc()%>';
    document.form1.skdwzh.value='<%=form.getSkdwzh()%>';
    document.form1.skdwyh.value='<%=form.getSkdwyh()%>';
    document.form1.ltqyr.value='<%=form.getLtqyr()%>';
    document.form1.htlxr.value='<%=form.getHtlxr()%>';
    document.form1.htlxtel.value='<%=form.getHtlxtel()%>';
    document.form1.fzjsjine.value='<%=form.getFzjsjine()%>';
    var sfkp = '<%=form.getFzsfkp()%>';
    if(sfkp=="1"){
    	document.form1.fzsfkp.checked="checked";
  	}
    document.form1.fzzzl.value='<%=form.getFzzzl()%>';
    document.form1.zhjntime.value='<%=form.getZhjntime()%>';
    document.form1.sxtime.value='<%=form.getSxtime()%>';
    document.form1.xqtime.value='<%=form.getXqtime()%>';
    document.form1.offtime.value='<%=form.getOfftime()%>';
    document.form1.xqyjz.value='<%=form.getXqyjz()%>';
    document.form1.fzyjz.value='<%=form.getFzyjz()%>';
    document.form1.memo.value='<%=form.getMemo()%>';
    document.form1.qstime.value='<%=form.getQstime()%>';
  }
	///////////////////////////////////////////////////////////
	
//-->
</script>

