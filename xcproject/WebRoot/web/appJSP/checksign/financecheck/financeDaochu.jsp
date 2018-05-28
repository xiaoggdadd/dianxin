<%@page contentType="application/vnd.ms-excel;charset=UTF-8"
	language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page
	import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account,java.util.Vector"%>
<%@ page
	import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean"%>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean"%>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
<%
	Account account = new Account();
	account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
	String qyear = request.getParameter("qyear");
	CTime time = new CTime();
	String accountId = account.getAccountId();
	String path = request.getContextPath();
	String dwxz = request.getParameter("dwxz") != null ? request
			.getParameter("dwxz") : "2";
	String month = String.valueOf(Integer.parseInt(time
			.formatShortDate().substring(4, 6)));
	String qijian = request.getParameter("qijian") != null ? request
			.getParameter("qijian") : month;
	String dw = request.getParameter("dw") != null ? request
			.getParameter("dw") : "0";
%>
<html xmlns:v="urn:schemas-microsoft-com:vml"
	xmlns:o="urn:schemas-microsoft-com:office:office"
	xmlns:x="urn:schemas-microsoft-com:office:excel"
	xmlns="http://www.w3.org/TR/REC-html40">

	<head>
		<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
		<meta name=ProgId content=Excel.Sheet>
		<meta name=Generator content="Microsoft Excel 11">
		<!--[if !mso]>
<style>
v\:* {behavior:url(#default#VML);}
o\:* {behavior:url(#default#VML);}
x\:* {behavior:url(#default#VML);}
.shape {behavior:url(#default#VML);}
</style>
<![endif]-->
		<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Created>1996-12-17T01:32:42Z</o:Created>
  <o:LastSaved>2008-08-11T02:22:29Z</o:LastSaved>
  <o:Version>11.9999</o:Version>
 </o:DocumentProperties>
 <o:OfficeDocumentSettings>
  <o:RemovePersonalInformation/>
 </o:OfficeDocumentSettings>
</xml><![endif]-->
		<style>
<!--
table {
	mso-displayed-decimal-separator: "\.";
	mso-displayed-thousand-separator: "\,";
}

@page {
	margin: 1.0in .75in 1.0in .75in;
	mso-header-margin: .5in;
	mso-footer-margin: .5in;
}

tr {
	mso-height-source: auto;
	mso-ruby-visibility: none;
}

col {
	mso-width-source: auto;
	mso-ruby-visibility: none;
}

br {
	mso-data-placement: same-cell;
}

.style0 {
	mso-number-format: General;
	text-align: general;
	vertical-align: bottom;
	white-space: nowrap;
	mso-rotate: 0;
	mso-background-source: auto;
	mso-pattern: auto;
	color: windowtext;
	font-size: 12.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	border: none;
	mso-protection: locked visible;
	mso-style-name: 常规;
	mso-style-id: 0;
}

td {
	mso-style-parent: style0;
	padding: 0px;
	mso-ignore: padding;
	color: windowtext;
	font-size: 12.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-number-format: General;
	text-align: general;
	vertical-align: bottom;
	border: none;
	mso-background-source: auto;
	mso-pattern: auto;
	mso-protection: locked visible;
	white-space: nowrap;
	mso-rotate: 0;
}

.xl24 {
	mso-style-parent: style0;
	text-align: center;
}

.xl25 {
	mso-style-parent: style0;
	text-align: center;
	border: .5pt solid windowtext;
}

.xl26 {
	mso-style-parent: style0;
	border: .5pt solid windowtext;
}

ruby {
	ruby-align: left;
}

rt {
	color: windowtext;
	font-size: 9.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-char-type: none;
	display: none;
}
-->
</style>
		<!--[if gte mso 9]><xml>
 <x:ExcelWorkbook>
  <x:ExcelWorksheets>
   <x:ExcelWorksheet>
    <x:Name>Sheet1</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:CodeName>Sheet1</x:CodeName>
     <x:Selected/>
     <x:Panes>
      <x:Pane>
       <x:Number>3</x:Number>
       <x:ActiveRow>6</x:ActiveRow>
       <x:ActiveCol>6</x:ActiveCol>
      </x:Pane>
     </x:Panes>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
   <x:ExcelWorksheet>
    <x:Name>Sheet2</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:CodeName>Sheet2</x:CodeName>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
   <x:ExcelWorksheet>
    <x:Name>Sheet3</x:Name>
    <x:WorksheetOptions>
     <x:DefaultRowHeight>285</x:DefaultRowHeight>
     <x:CodeName>Sheet3</x:CodeName>
     <x:ProtectContents>False</x:ProtectContents>
     <x:ProtectObjects>False</x:ProtectObjects>
     <x:ProtectScenarios>False</x:ProtectScenarios>
    </x:WorksheetOptions>
   </x:ExcelWorksheet>
  </x:ExcelWorksheets>
  <x:WindowHeight>4530</x:WindowHeight>
  <x:WindowWidth>8505</x:WindowWidth>
  <x:WindowTopX>480</x:WindowTopX>
  <x:WindowTopY>120</x:WindowTopY>
  <x:AcceptLabelsInFormulas/>
  <x:ProtectStructure>False</x:ProtectStructure>
  <x:ProtectWindows>False</x:ProtectWindows>
 </x:ExcelWorkbook>
</xml><![endif]-->
		<!--[if gte mso 9]><xml>
 <o:shapedefaults v:ext="edit" spidmax="1028"/>
</xml><![endif]-->
		<!--[if gte mso 9]><xml>
 <o:shapelayout v:ext="edit">
  <o:idmap v:ext="edit" data="1"/>
 </o:shapelayout></xml><![endif]-->
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>


	<body link=blue vlink=purple>
		<table x:str border=1 cellpadding=0 cellspacing=0 width=705
			style='border-collapse: collapse; table-layout: fixed; width: 530pt'>
			<col width=142
				style='mso-width-source: userset; mso-width-alt: 4544; width: 107pt'>
			<col width=152
				style='mso-width-source: userset; mso-width-alt: 4864; width: 114pt'>
			<col width=174
				style='mso-width-source: userset; mso-width-alt: 5568; width: 131pt'>
			<col width=165
				style='mso-width-source: userset; mso-width-alt: 5280; width: 124pt'>
			<col width=72 style='width: 54pt'>
			<tr class=xl24 height=19 style='height: 14.25pt'>
				<td class=xl25 width=142 height=19
					style='height: 14.25pt; width: 166pt'>
					所在地区
				</td>

				<td class=xl25 width=142 height=19
					style='height: 14.25pt; width: 107pt'>
					站点名称
				</td>
				<td class=xl25 width=142 height=19
					style='height: 14.25pt; width: 166pt'>
					站点类型
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					上次抄表时间
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					本次抄表时间
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					上次读数
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					本次读数
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					起始月份
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					结束月份
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					倍率
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					电量调整
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					耗电量
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					电费单价
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					电费调整
				</td>



				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					实际电费金额
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					报账月份
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					电费支付类型
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					票据类型
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					票据金额
				</td>

				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					流程号
				</td>
				<td class=xl25 width=165 style='border-left: none; width: 124pt'>
					站点属性
				</td>

				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					电流类型
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					用电设备
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					票据编号
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					自动审核状态
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					人工审核状态
				</td>
				<td class=xl25 width=72 style='border-left: none; width: 54pt'>
					财务审核状态
				</td>
				<td class=xl25 width=152 style='border-left: none; width: 114pt'>
					电表ID
				</td>
				<td class=xl25 width=152 style='border-left: none; width: 114pt'>
					人工审核通过原因
				</td>
			</tr>
			<c:forEach items="${ls}" var="m" varStatus="s">
				<tr height=19 style='height: 14.25pt'>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.szdq}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.jzname}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.stationtype}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						&nbsp;${m.lastdatetime}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						&nbsp;${m.thisdatetime}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.lastdegree}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.thisdegree}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						&nbsp;${m.startmonth}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						&nbsp;${m.endmonth}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.beilv}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.floatdegree}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.blhdl}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.unitprice}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.floatpay}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.actualpay}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						&nbsp;${m.accountmonth}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.dfzflx}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.notetypeid}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.pjjedf}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.liuchenghao}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.property}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.dllx}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.ydsb}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.noteno}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.autoauditstatus}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						<c:if test="${m.manualauditstatus==1}">通过</c:if>
						<c:if test="${m.manualauditstatus==2}">通过</c:if>
						<c:if test="${m.manualauditstatus!=1&&m.manualauditstatus!=2}">未通过</c:if>
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						<c:if test="${m.manualauditstatus==1}">未通过</c:if>
						<c:if test="${m.manualauditstatus==2}">通过</c:if>
						<c:if test="${m.manualauditstatus!=1&&m.manualauditstatus!=2}">不通过</c:if>
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.dbid}
					</td>
					<td class=xl26 align=right
						style='border-top: none; border-left: none' x:num>
						${m.rgshtgyy}
					</td>

				</tr>
			</c:forEach>
			<tr>

				<td colspan="18">
					<div align="center">
						<font color='#000080'>&nbsp;导出条数:</font>
						<b><font color=red>${size}条；</font> </b>
						<font color='#000080'>&nbsp;实际电费金额总计:</font>
						<b><font color=red>&nbsp;${sumdf}元</font> </b>
					</div>
				</td>
			</tr>

		</table>

	</body>

</html>
