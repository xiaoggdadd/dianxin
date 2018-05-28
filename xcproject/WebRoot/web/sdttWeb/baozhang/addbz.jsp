<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.Double"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="com.noki.common.OrgAndRole"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="com.noki.jizhan.SiteModifyBean"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="com.noki.baobiaohuizong.qitabean.GYS"%>
<%
    String path = request.getContextPath();
    String shengId = (String)session.getAttribute("accountSheng");
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String jzproperty = request.getParameter("jzproperty");//获取新站点的属性  
    
    String accountname = account.getAccountName();
    String accountName=account.getCthrnumber();
    
    System.out.println("报账人："+accountName);
    SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd");
    String entrytime = mat.format(new Date());
    System.out.println("系统时间" + entrytime + "  shengId:" + shengId + "  loginName:" + loginName
            + "  accountname:" + accountname + " jzproperty:" + jzproperty);
    
    String ids = request.getParameter("ids");
    System.out.println("ids?>?>?>?>?>?>" + ids);
    Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
	int year = c.get(Calendar.YEAR); 
	int month = c.get(Calendar.MONTH)+1; 
	String date_str="";
			if(month<10){
				date_str = ""+year+"-0"+month;
			}else{
				date_str = ""+year+"-"+month;
			}
    //	ArrayList listBz = (ArrayList)session.getAttribute("listBz");
    //	System.out.println("listBz>>>>>>>>>>>>>>>>>>>>>>>>>"+listBz.size());
    
    //将遍历表格的内容拿到上面 ，获取其对应的DBBM
    
    SiteManage bean = new SiteManage();
    
	ArrayList fylist = new ArrayList();
	
	ArrayList<GYS> gysal = new ArrayList<GYS>();    //供应商信息返回集合
	
	fylist = bean.getListByIds(ids);
	
	String DBBM = "";
	String GYSBM = "";
	String GYSMC = "";
	
	if (fylist != null){
		int fylistcount = 0;
		System.out.println("fylist.size():" + fylist.size());
		if (fylist.size() > 0){
		
			 fylistcount = ((Integer)fylist.get(0)).intValue();
			 
			}
			for (int l = fylistcount; l < fylist.size() - 1; l += fylistcount){
			
				DBBM = (String)fylist.get(l + fylist.indexOf("DBCODE"));
				 
			}
				System.out.println("电表编码："+DBBM);
			
			//通过电表编码获取供应商名称和供应商编码
			gysal = bean.SELECTGYS(DBBM);
			
	}
		if (gysal != null){
		for(int z = 0; z<gysal.size(); z++){
		GYSBM = gysal.get(z).getGysbm()!= null ? gysal.get(z).getGysbm():" ";
		GYSMC = gysal.get(z).getGysmc()!= null ? gysal.get(z).getGysmc():" ";
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<!--[if gt IE 8]><!-->
<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
<!--<![endif]-->
<!--[if lte IE 8]>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-browser.js"></script>
<![endif]-->
		<title>提交报账信息</title>
		<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/javascript/jQuery-ComboSelect/css/combo.select.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
		<script type="text/javascript"></script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
		<script type="text/javascript" src="<%=path%>/javascript/PopupCalendar.js"></script>
		<script type="text/javascript" src="<%=path%>/javascript/PopupCalendar_ny.js"></script>

	
	
<script type="text/javascript" src="<%=path%>/javascript/jQuery-ComboSelect/js/jquery.combo.select.js"></script>

		<script type="text/javascript">
//		$(function() {
//	$('#rsname').comboSelect();
//});
var path = '<%=path%>';
</script>
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="diaobiaoBean" scope="page"
		class="com.noki.jizhan.DianBiaoBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body>
		<form action="" name="form1" method="post">
			<input type="hidden" name="ids" value="<%=ids%>"
				style="box-sizing: border-box; width: 130px" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="top">

					<td width="12">
						<img src="../images/space.gif" width="12" height="17" />
					</td>
					<td>
						<div class="content01">
							<div class="tit01">
								提交报账信息
							</div>
							<div class="content01_1">
								<table width="1000px" border="0" cellpadding="2" cellspacing="0"
									class="tb_select">
									<tr bgcolor="F9F9F9">
										<td height="19" colspan="8">
											<img src="../../images/v.gif" width="15" height="15" />
											总信息
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											<span style="color: #FF0000; font-weight: bold">*</span>报账方式
										</td>
										<td width="100px">
											<select name="bzfs" id="bzfs"
												style="box-sizing: border-box; width: 130px;">
												<%
												    ArrayList bzfs = new ArrayList();
												    bzfs = ztcommon.getSelctOptions("bzfs_dict");
												    if (bzfs != null)
												    {
												        String code = "", name = "";
												        int cscount = ((Integer)bzfs.get(0)).intValue();
												        for (int i = cscount; i < bzfs.size() - 1; i += cscount)
												        {
												            code = (String)bzfs.get(i + bzfs.indexOf("CODE"));
												            name = (String)bzfs.get(i + bzfs.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
												    }
												    }
												%>

											</select>
											
										</td>
										<td align="right" width="100px">
											<span style="color: #FF0000; font-weight: bold">*</span>报账时间
										</td>
										<td>
											<%--<input
												onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"
												name="costtime" value=''
												style="box-sizing: border-box; width: 130px" />--%>
											<input name="costtime" value='<%=date_str%>' readonly="readonly" style="box-sizing: border-box; width: 130px"  />
										</td>
										<td align="right" width="100px">
											<span style="color: #FF0000; font-weight: bold">*</span>报账类型
										</td>
										<td width="100px">
											<select name="bztype" id="bztype"
												style="box-sizing: border-box; width: 130px;">
												<%
												    ArrayList bztype = new ArrayList();
												    bztype = ztcommon.getSelctOptions("bztype_dict");
												    if (bztype != null)
												    {
												        String code = "", name = "";
												        int cscount = ((Integer)bztype.get(0)).intValue();
												        for (int i = cscount; i < bztype.size() - 1; i += cscount)
												        {
												            code = (String)bztype.get(i + bztype.indexOf("CODE"));
												            name = (String)bztype.get(i + bztype.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
												    }
												    }
												%>

											</select>
											
										</td>
										<td align="right" width="100px"></td>
										<td width="100px">
										</td>
									</tr>

									<tr>
										<td align="right" width="100px">
											选择岗位
										</td>
										<td width="100px">
											<select name="gangwei" id="gangwei"
												style="box-sizing: border-box; width: 130px;">
												<option value="0">
													请选择
												</option>
												<%
												List<OrgAndRole> listoar = new ArrayList<OrgAndRole>();
												listoar = ztcommon.getGangwei(accountName);
												    if (listoar != null)
												    {
												        String code = "", name = "";
												        for (int i = 0; i < listoar.size(); i++)
												        {
												            code = listoar.get(i).getRoleId();
												            name = listoar.get(i).getRoleName();
														%>
														<option value="<%=code%>"><%=name%></option>
														<%
												    }
												    }
												%>

											</select>
										</td>
										<td align="right" width="100px">
											报账业务发生时间
										</td>
										<td width="100px">
											<select name="bzywsj" id="bzywsj"
												style="box-sizing: border-box; width: 130px;">
												<%
												    ArrayList bzywsj = new ArrayList();
												    bzywsj = ztcommon.getSelctOptions("bzywsj_dict");
												    if (bzywsj != null)
												    {
												        String code = "", name = "";
												        int cscount = ((Integer)bzywsj.get(0)).intValue();
												        for (int i = cscount; i < bzywsj.size() - 1; i += cscount)
												        {
												            code = (String)bzywsj.get(i + bzywsj.indexOf("CODE"));
												            name = (String)bzywsj.get(i + bzywsj.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
												    }
												    }
												%>

											</select>
										</td>
										<td align="right" width="100px">
											选择经济事项
										</td>
										<td width="100px">

											<select name="jjsx" id="jjsx"
												style="box-sizing: border-box; width: 130px;">
												<%
												    ArrayList jjsx = new ArrayList();
												    jjsx = ztcommon.getSelctOptions("jjsx_dict");
												    if (jjsx != null)
												    {
												        String code = "", name = "";
												        int cscount = ((Integer)jjsx.get(0)).intValue();
												        for (int i = cscount; i < jjsx.size() - 1; i += cscount)
												        {
												            code = (String)jjsx.get(i + jjsx.indexOf("CODE"));
												            name = (String)jjsx.get(i + jjsx.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
												    }
												    }
												%>

											</select>
										</td>
										<td align="right" width="100px">
											用途
										</td>
										<td width="100px">

											<select name="yongtu" id="yongtu"
												style="box-sizing: border-box; width: 130px;">
												<%
												    ArrayList yongtu = new ArrayList();
												    yongtu = ztcommon.getSelctOptions("yongtu_dict");
												    if (yongtu != null)
												    {
												        String code = "", name = "";
												        int cscount = ((Integer)yongtu.get(0)).intValue();
												        for (int i = cscount; i < yongtu.size() - 1; i += cscount)
												        {
												            code = (String)yongtu.get(i + yongtu.indexOf("CODE"));
												            name = (String)yongtu.get(i + yongtu.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
												    }
												    }
												%>

											</select>
										</td>
									</tr>

									<tr bgcolor="F9F9F9">
										<td height="19" colspan="8">
											<img src="../../images/v.gif" width="15" height="15" />
											报账封装信息
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											<span style="color: #FF0000; font-weight: bold">*</span>供应商名称
										</td>
										<td width="100px">
											<%--<select name="rsname" id="rsname" onchange="changeSupplier()"
												style="box-sizing: border-box; width: 130px">
												<option value="0">请选择</option>
												<%
												    ArrayList supplist = new ArrayList();
												    supplist = commBean.getSuppliers();
												    if (supplist != null)
												    {
												        String code = "", name = "";
												        int suppcount = ((Integer)supplist.get(0)).intValue();
												        for (int i = suppcount; i < supplist.size() - 1; i += suppcount)
												        {
												            code = (String)supplist.get(i + supplist.indexOf("CODE"));
												            name = (String)supplist.get(i + supplist.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
												    }
												    }
												%>


											</select>
											
											--%>
											
											<input type="text" name="gysmc" id="rsname" value="<%=GYSMC%>" onclick="openSupplier()"
												readonly="readonly"
												style="box-sizing: border-box; width: 130px" />
												
												<input type="hidden" name="rsname1" value="" />
										</td>
										<td align="right" width="100px">
											供应商编码
										</td>
										<td width="100px">
											<input type="text" id="gysbmm" name="gysbm" value="<%=GYSBM%>"
												style="box-sizing: border-box; width: 130px"
												readonly="readonly" class="selected_font3" />
										</td>
										<td width="100px">
											※备注：供应商名称可输入关键字查询
										</td>
									</tr>
									<tr>
										<td align="right" width="100px">
											收支方式
										</td>
										<td width="100px">
											<select name="paytype" id="paytype"
												style="box-sizing: border-box; width: 130px;">
												<%
												    ArrayList paytype = new ArrayList();
												    paytype = ztcommon.getSelctOptions("paytype_dict");
												    if (paytype != null)
												    {
												        String code = "", name = "";
												        int cscount = ((Integer)paytype.get(0)).intValue();
												        for (int i = cscount; i < paytype.size() - 1; i += cscount)
												        {
												            code = (String)paytype.get(i + paytype.indexOf("CODE"));
												            name = (String)paytype.get(i + paytype.indexOf("NAME"));
												%>
												<option value="<%=code%>"><%=name%></option>
												<%
												    }
												    }
												%>

											</select>
											<span style="color: #FF0000; font-weight: bold">*</span>
										</td>
									</tr>
									<tr bgcolor="F9F9F9">
										<td height="19" colspan="8">
											<img src="../../images/v.gif" width="15" height="15" />
											报账详细信息
										</td>
									</tr>
									<tr>
										<td colspan="8">
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0" class="tb_list1">
												<tr align="center">
													<th width="10">
														序号
													</th>
													<th width="100">
														公司主体
													</th>
													<th width="80">
														费用编号
													</th>
													<th width="80">
														业务类型
													</th>
													<th width="50">
														开始日期
													</th>
													<th width="50">
														结束日期
													</th>
													<th width="80">
														记账成本中心
													</th>
													<th width="50">
														预算项目
													</th>
													<th width="50">
														金额
													</th>
													<th width="50">
														税额
													</th>
												</tr>
												<%
												    DecimalFormat df1 = new DecimalFormat("0.00");
												    String id = "", state = "", compname = "", dbname = "", dbcode = "", zdname = "", zdcode = "";
												    String fptype = "", jffs = "", starttime = "", endtime = "", rcname = "", rccode = "";
												    String money = "", allmoney = "", faxmoney = "", dianliang = "", price = "", isbz = "", isbzname = "";
												    String ywname = "", ywcode = "", ysname = "", yscode = "",compcode = "";
												    double sumMoney = 0, sumFaxmoney = 0;
												    if (fylist != null)
												    {
												        int fycount = 0;
												        System.out.println("fylist.size():" + fylist.size());
												        if (fylist.size() > 0)
												        {
												            fycount = ((Integer)fylist.get(0)).intValue();
												            
												        }
												        
												        int intnum = 1;
												        for (int k = fycount; k < fylist.size() - 1; k += fycount)
												        {
												            
												            id = (String)fylist.get(k + fylist.indexOf("ID"));
												            state = (String)fylist.get(k + fylist.indexOf("STATE"));
												            compname = (String)fylist.get(k + fylist.indexOf("COMPNAME"));
												            compcode = (String)fylist.get(k + fylist.indexOf("COMPCODE"));
												            dbname = (String)fylist.get(k + fylist.indexOf("DBNAME"));
												            dbcode = (String)fylist.get(k + fylist.indexOf("DBCODE"));
												            zdname = (String)fylist.get(k + fylist.indexOf("ZDNAME"));
												            zdcode = (String)fylist.get(k + fylist.indexOf("ZDCODE"));
												            fptype = (String)fylist.get(k + fylist.indexOf("FPTYPE"));
												            jffs = (String)fylist.get(k + fylist.indexOf("JFFS"));
												            starttime = (String)fylist.get(k + fylist.indexOf("STARTTIME"));
												            endtime = (String)fylist.get(k + fylist.indexOf("ENDTIME"));
												            rcname = (String)fylist.get(k + fylist.indexOf("RCNAME"));
												            rccode = (String)fylist.get(k + fylist.indexOf("RCCODE"));
												            money = (String)fylist.get(k + fylist.indexOf("MONEY"));
												            allmoney = (String)fylist.get(k + fylist.indexOf("ALLMONEY"));
												            faxmoney = (String)fylist.get(k + fylist.indexOf("FAXMONEY"));
												            dianliang = (String)fylist.get(k + fylist.indexOf("DIANLIANG"));
												            price = (String)fylist.get(k + fylist.indexOf("PRICE"));
												            isbz = (String)fylist.get(k + fylist.indexOf("ISBZ"));
												            ywname = (String)fylist.get(k + fylist.indexOf("YWTYPE"));
												            ywcode = (String)fylist.get(k + fylist.indexOf("YWCODE"));
												            ysname = (String)fylist.get(k + fylist.indexOf("YSNAME"));
												            yscode = (String)fylist.get(k + fylist.indexOf("YSCODE"));
												            if (allmoney != "")
												            {
												                sumMoney = sumMoney + Double.valueOf(allmoney).doubleValue();
												            }
												            else
												            {
												                allmoney = "0.00";
												            }
												            if (faxmoney != "")
												            {
												                sumFaxmoney = sumFaxmoney + Double.valueOf(faxmoney).doubleValue();
												            }
												            else
												            {
												                faxmoney = "0.00";
												            }
												%>

												<!-- 数据加载  Start-->
												<tr align="center">
													<td width="10"><%=intnum++%></td>
													<td align="center"><%=compname%></td>
													<td align="center"><%=dbcode%></td>
													<td align="center"><%=ywname%></td>
													<td align="center"><%=starttime.substring(0, 10)%></td>
													<td align="center"><%=endtime.substring(0, 10)%></td>
													<td align="center"><%=rcname%></td>
													<td align="center"><%=ysname%></td>
													<td align="center"><%=allmoney%></td>
													<td align="center"><%=faxmoney%></td>

												</tr>
												<%
												    }
												    }
												%>


											</table>

										</td>
									</tr>
									<tr>
										<td align="left" colspan="8" height="60px">
											总金额：<%=df1.format(sumMoney)%>,总税额：<%=df1.format(sumFaxmoney)%>
										</td>
									</tr>
									<tr>
										<td align="right" colspan="8" height="60px">
											<input onclick="saveBz()" type="button" class="btn_c1"
												id="button2" value="提交" />
											<%--&nbsp; 
								<input type="reset" class="btn_c1" value="重置" />&nbsp; 
							--%>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">

var path = '<%=path%>';
function saveBz() {
	if (checkNotnull(document.form1.rsname, "供应商") && checkNotnull(document.form1.costtime, "报账期间")) {
			//获取供应商和供应商编码
			var gysbmm= $('#gysbmm').val();		//选择后的供应商编码
			var rsname= $('#rsname').val();		//选择后的供应商名称
			//电表编码
			var DBBM  ='<%=DBBM%>';		
			//初始供应商编码		
			var GYSBM ='<%=GYSBM%>';
			//初始供应商名称			
			var GYSMC ='<%=GYSMC%>';
			//判断初始编码和选择后的供应商编码是否相同
			if(gysbmm == GYSBM){
				//执行推送财辅方法
				addbz();
			}else{
				//执行ajax修改供应商属性后再执行推送财辅方法
				UpdateGYS(DBBM,gysbmm,rsname)
			}
	};

}
function addbz() {
	if (confirm("您将要把报账信息推送到财辅系统,确认信息正确！")) {
		var bztype_dict_str = $("#bztype").val();
		if(bztype_dict_str=="bztype_dict_1"){
			document.form1.action = path + "/servlet/zhandian?action=tsbz_1";
		}else if(bztype_dict_str=="bztype_dict_2"){
			document.form1.action = path + "/servlet/zhandian?action=tsbz_2";
		}else if(bztype_dict_str=="bztype_dict_3"){
			document.form1.action = path + "/servlet/zhandian?action=tsbz_3";
		}else{
			document.form1.action = "";
			alert("报账类型错误！")
		}
		
		document.form1.submit();
		showdiv("请稍等..............");
	}
}
function isNaN2(val) {
	return val.match(new RegExp("^[0-9]+$"));
}
function ismoney(str) {
	if (/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(str))
		return true;
	return false;
}
function isNa(val) {
	return val.match(new RegExp("^\d+\.\d+$"));
}
function openSupplier(){
	window.open("../jizan/Supplier.jsp", "newwindowSupp", "height=550, width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
}
/**供应商信息*/
function changeSupplier(){
	
	var codeReq = document.form1.rsname.value;
	$.ajax({
		type: 'post',
		url: path + '/servlet/commonBeanServlet?action=gys',
		cache:false,
		data: {
			code: codeReq
		},
		dataType: 'json',
		success: function(data){
			if(data){
				var count = data[0];
				var code="";
				var name="";
				if(data.length > count + 1){
					code = data[count + data.indexOf("CODE")];
					name = data[count + data.indexOf("NAME")];
				}
				document.form1.rscode.value = code;
				document.form1.rsname1.value = name;
				
			}
			
		},
		error: function(){
			return;
		}
	});
}
function UpdateGYS(DBBM,gysbmm,rsname){	//AJAX修改电表供应商属性

	if(confirm("检测您修改了供应商属性，我们将同步到电表信息中，是否继续推送？")){
	
		$.ajax({
   	 		//直接"post"或者"get",不需要"doPost","doGet"，该函数到后端接收缓冲区会自动匹配
        	type : "post",      
       		//servlet文件名为Calculator，需要提前在web.xml里面注册
        	url :"<%=path%>/servlet/UpdateGYS",
        	dataType : "text",  //数据类型，可以为json，xml等等，自己百度
        	data :
        	{
            	"DBBM":DBBM,				//电表编码
             	"gysbmm":gysbmm,    		//供应商编码
             	"rsname":rsname,    		//供应商名称
        	},
        	success : function(Result)
        	{
               //Result为后端post函数传递来的数据，这里写结果操作代码
               //alert("供应商修改成功!");
				addbz();
        	},
        	error : function(xhr, status, errMsg)
        	{
             	alert("供应商修改失败，请与管理员联系！");
       		}
    	});
    	
	}
}
</script>
	</body>
</html>
