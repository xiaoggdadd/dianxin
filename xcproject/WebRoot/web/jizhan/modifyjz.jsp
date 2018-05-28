<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.ZhanDianForm" %>
<%@ page import="com.noki.mobi.common.Account" %>
<%
	String path = request.getContextPath();
	String id = request.getParameter("id");
	ZhanDianForm form = new ZhanDianForm();
	form = form.getJizhan(id);
	System.out.println("标杆类型编号"+form.getSigntypenum());
	System.out.println("集团报类型"+form.getJztype()+"是否标杆"+form.getBgsign()+"站点类型"+form.getStationtype());
	System.out.println("gczt=="+form.getGczt()+"<<");
	String typename="";
	typename=form.getTypename();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
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

 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
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
<script language="javascript">
var path = '<%=path%>';

	
	
	function JZtypes(){   //基站类型/接入网类型
  
    kzinfo();
  
  
	var jzpro=document.form1.jzproperty.value;
	var yf=document.form1.yflx.value;
	var zl=Number(document.form1.zlfh.value);
	
	var jzty=document.form1.jzxlx.value;
	
	
	if(jzpro=="zdsx02" && yf=="fwlx01"){
		if(zl>=0&&zl<=10){
		document.form1.jzxlx.value="jzlx01";
		}else if(10<zl&&zl<=20){
		document.form1.jzxlx.value="jzlx02";
		}else if(20<zl&&zl<=30){
		document.form1.jzxlx.value="jzlx03";
		}else if(30<zl&&zl<=40){
		document.form1.jzxlx.value="jzlx04";
		}else if(40<zl&&zl<=50){
		document.form1.jzxlx.value="jzlx05";
		}else if(50<zl&&zl<=60){
		document.form1.jzxlx.value="jzlx06";
		}else if(60<zl&&zl<=70){
		document.form1.jzxlx.value="jzlx07";
		}else if(70<zl&&zl<=80){
		document.form1.jzxlx.value="jzlx08";
		}else if(80<zl&&zl<=90){
		document.form1.jzxlx.value="jzlx09";
		}else if(90<zl&&zl<=100){
		document.form1.jzxlx.value="jzlx10";
		}else if(zl>100){
		document.form1.jzxlx.value="jzlx11";
		}else{
		document.form1.jzxlx.value="0";
		}
	}else if(jzpro=="zdsx02" && yf=="fwlx02"){
				
	    if(zl>=0&&zl<=10){
		document.form1.jzxlx.value="jzlx12";
		}else if(10<zl&&zl<=20){
		document.form1.jzxlx.value="jzlx13";
		}else if(20<zl&&zl<=30){
		document.form1.jzxlx.value="jzlx14";
		}else if(30<zl&&zl<=40){
		document.form1.jzxlx.value="jzlx15";
		}else if(40<zl&&zl<=50){
		document.form1.jzxlx.value="jzlx16";
		}else if(50<zl&&zl<=60){
		document.form1.jzxlx.value="jzlx17";
		}else if(60<zl&&zl<=70){
		document.form1.jzxlx.value="jzlx18";
		}else if(70<zl&&zl<=80){
		document.form1.jzxlx.value="jzlx19";
		}else if(80<zl&&zl<=90){
		document.form1.jzxlx.value="jzlx20";
		}else if(90<zl&&zl<=100){
		document.form1.jzxlx.value="jzlx21";
		}else if(zl>100){
		document.form1.jzxlx.value="jzlx22";
		}else{
		document.form1.jzxlx.value="0";
		}
	}else if(jzpro=="zdsx05" && yf=="fwlx01"){
			if(zl>=0&&zl<=20){
		        document.form1.jrwtype.value="jrwlx01";
		        }else if(20<zl&&zl<=40){
		        document.form1.jrwtype.value="jrwlx02";
		        }else if(40<zl&&zl<=60){
				document.form1.jrwtype.value="jrwlx03";
				}else if(60<zl&&zl<=80){
				document.form1.jrwtype.value="jrwlx04";
				}else if(80<zl&&zl<=100){
				document.form1.jrwtype.value="jrwlx05";
				}else if(zl>100){
				document.form1.jrwtype.value="jrwlx06";
				}else{
				document.form1.jrwtype.value="0";
				}
	}else if(jzpro=="zdsx05" && yf=="fwlx02"){
			if(zl>=0&&zl<=20){
		        document.form1.jrwtype.value="jrwlx07";
		        }else if(20<zl&&zl<=40){
		        document.form1.jrwtype.value="jrwlx08";
		        }else if(40<zl&&zl<=60){
				document.form1.jrwtype.value="jrwlx09";
				}else if(60<zl&&zl<=80){
				document.form1.jrwtype.value="jrwlx10";
				}else if(80<zl&&zl<=100){
				document.form1.jrwtype.value="jrwlx11";
				}else if(zl>100){
				document.form1.jrwtype.value="jrwlx12";
				}else{
				document.form1.jrwtype.value="0";
				}
		}else if((jzpro=="zdsx02"||jzpro=="zdsx05") && (yf=="fwlx03"||yf=="0")){
		document.form1.jzxlx.value="0";
		document.form1.jrwtype.value="0";
		}
	}










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
	function saveAccount(){
                       if(checkNotSelected(document.form1.shi,"城市")&&
                          checkNotSelected(document.form1.xian,"区县")&&
          			checkNotSelected(document.form1.xiaoqu,"乡镇")&&
          			checkNotSelected(document.form1.jzproperty,"站点属性")&&checkNotnull(document.form1.jzproperty,"站点属性")&&
          			checkNotnull(document.form1.jztype,"集团报表类型")&&checkNotSelected(document.form1.jztype,"集团报表类型")&&
                       checkNotSelected(document.form1.stationtype,"站点类型")&&checkNotnull(document.form1.stationtype,"站点类型")&&
                      checkNotnull(document.form1.gsf,"归属方")&&checkNotSelected(document.form1.gsf,"归属方")&&
                     checkNotnull(document.form1.gdfs,"供电方式")&&checkNotSelected(document.form1.gdfs,"供电方式")&&
                     checkNotnull(document.form1.dianfei,"单价")
                       ){
	                       if(isNaN(document.form1.dianfei.value)==false){
	                             
			                        if(confirm("您将要修改站点信息！确认信息正确！")){
											document.form1.action=path+"/servlet/zhandian?action=modifyZhanDian"
											document.form1.submit()
								    }	
							}else{
							alert("您输入的信息有误，单价必须为数字！");
							}
					}
                         
                   
      }

        function vName(){
         	var accName = document.form1.accountName.value;
                 if(accName==""){
           	alert("不能为空！")
                   return
          }
               window.open('accountName.jsp?accountId=0&accountName='+accName,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
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
			$("#cancelBtn").click(function(){
			window.history.back();
			});
		  $("#resetBtn").click(function(){
			$.each($("form input[id='a']"),function(){
			  $(this).val("");
		          })
			});
		  $("#saveBtn").click(function(){
			saveAccount();
			});
		  $("#liulan").click(function(){
		      liulan();
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;站点信息修改</span></td>
							
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
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1>
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">
			 <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4">&nbsp;&nbsp;<img src="../../images/v.gif" width="15" height="15" />&nbsp;基本信息</td>
            </tr>
            </table>
            </td>
            </tr>
            <tr><td>&nbsp;</td></tr>
              
      <tr>
         
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">市：</div>
         </td>
         <td width="25%">
         <select name="shi" style="width:130" onchange="changeCity()"()>
         		<option value="0">请选择</option>
         		<%
	         	ArrayList shenglist = new ArrayList();
	         	shenglist = commBean.getAgcode(shengId,"0",loginName);
	         	if(shenglist!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)shenglist.get(0)).intValue();
	         		for(int i=scount;i<shenglist.size()-1;i+=scount)
                    {
                    	sfid=(String)shenglist.get(i+shenglist.indexOf("AGCODE"));
                    	sfnm=(String)shenglist.get(i+shenglist.indexOf("AGNAME"));
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         </td>
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">区、县：</div>
         </td>
         <td width="26%">
         <select name="xian" style="width:130" onchange="changeXian()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList xianlist = new ArrayList();
	         	xianlist = commBean.getAgcode(form.getShi(),form.getXian(),loginName);
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
         	</select><span class="style1">&nbsp;*</span>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">乡镇：</div>
         </td>
         <td width="26%">
         <select name="xiaoqu" id="xiaoqu" style="width:130">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList xiaoqulist = new ArrayList();
	         	xiaoqulist = commBean.getAgcode(form.getXian(),form.getXiaoqu(),loginName);
	         	if(xiaoqulist!=null){
	         		String agcode="",agname="";
	         		int scount = ((Integer)xiaoqulist.get(0)).intValue();
	         		for(int i=scount;i<xiaoqulist.size()-1;i+=scount)
                    {
                    	agcode=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGCODE"));
                    	agname=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGNAME"));
                    %>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         </td>
      </tr>
      
      
      <tr>
        
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点属性：</div>
         </td>
         <td width="25%">
         	<select name="jzproperty" style="width:130" onchange="JZtypes()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = ztcommon.getSelctOptions("zdsx");
	         	if(zdsx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdsx.get(0)).intValue();
	         		for(int i=cscount;i<zdsx.size()-1;i+=cscount)
                    {
                    	code=(String)zdsx.get(i+zdsx.indexOf("CODE"));
                    	name=(String)zdsx.get(i+zdsx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         	</td>
			 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点类型：</div>
         </td>
         <td width="25%">
         	<select name="stationtype" style="width:130"  onchange="kzinfo()">
         	<option value=0>请选择</option>
         		<%
	         	ArrayList stationtype = new ArrayList();
         		stationtype = ztcommon.getSelctOptions("StationType");
	         	if(stationtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)stationtype.get(0)).intValue();
	         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
                    {
                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         </td> <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">集团报表类型：</div>
         </td>
         <td width="25%">
         	<select name="jztype" style="width:130"  onchange="kzinfo()">
         	<option value=0>请选择</option>
         		<%
	         	ArrayList zdtype = new ArrayList();
	         	zdtype = ztcommon.getSelctOptions("zdlx");
	         	if(zdtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdtype.get(0)).intValue();
	         		for(int i=cscount;i<zdtype.size()-1;i+=cscount)
                    {
                    	code=(String)zdtype.get(i+zdtype.indexOf("CODE"));
                    	name=(String)zdtype.get(i+zdtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         </td>
      </tr>
      
      <tr>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点名称：</div>
         </td>
         <td width="25%"><input type="text" name="jzname" value=""  class="form" style="width:130px"/>
         <span class="style1">&nbsp;*</span></td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">别名：</div></td>
         <td width="25%"><input id="a" type="text" name="bieming" value=""  class="form" style="width:130px"/></td>				
         <input type="hidden" name="zdcode" value=""  class="form" style="width:130px"/>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">归属方：</div>
				         </td>
				         <td width="25%">
				         	<select name="gsf" style="width:130">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList gsflist = new ArrayList();
					         		gsflist = ztcommon.getSelctOptions("gsf");
						         	if(gsflist!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)gsflist.get(0)).intValue();
						         		for(int i=cscount;i<gsflist.size()-1;i+=cscount)
					                    {
					                    	code=(String)gsflist.get(i+gsflist.indexOf("CODE"));
					                    	name=(String)gsflist.get(i+gsflist.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						       </select><span class="style1">&nbsp;*</span>
				         	</td>
      </tr>
     
      <tr>
      	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">是否标杆：</div>
         </td>
         <td width="25%"><input type="checkbox" name="bgsign"  />
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">是否节能：</div>
         </td>
         <td width="25%">
         	<select name="jnglmk" style="width:130">
         		<option value="1">是</option>
         		<option value="0">否</option>
         	</select>
         </td>
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">地址：</div>
         </td>
         <td width="25%"><input id="a" type="text" name="address" value=""  class="form" style="width:130px"/></td>
      </tr>
    
      <tr>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">供电方式：</div>
         </td>
         <td width="25%">
         	<select name="gdfs" style="width:130">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList gdfs = new ArrayList();
	         	gdfs = ztcommon.getSelctOptions("gdfs");
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
         	</select><span class="style1">&nbsp;*</span>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点面积：</div>
         </td>
         <td width="25%">
         	<input id="a" type="area" name="area"  class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">用房类型：</div>
         </td>
         <td width="26%">
         		<select name="yflx" style="width:130" onchange="JZtypes()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList fwlx = new ArrayList();
	         	fwlx = ztcommon.getSelctOptions("fwlx");
	         	if(fwlx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)fwlx.get(0)).intValue();
	         		for(int i=cscount;i<fwlx.size()-1;i+=cscount)
                    {
                    	code=(String)fwlx.get(i+fwlx.indexOf("CODE"));
                    	name=(String)fwlx.get(i+fwlx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         </td>
			
      </tr>
    
   
    
      <tr>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">基站责任人：</div>
         </td>
         <td width="25%">
         	<input type="text" name="fzr" value=""  class="form" style="width:130px"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">单价：</div>
         </td>
         <td width="25%">
         	<input type="text" name="dianfei" value=""  class="form" style="width:130px"/>
        
        <span class="style1">&nbsp;*</span> </td>
         <td colspan="2">
         <div id="JZLXKZ"  style="width:100%;height:100%;display:none;">
          <table width="100%">
          		<tr>
               <td bgcolor="#DDDDDD" width="47%">
               		<div align="left">基站类型：</div>
         		</td>
		         <td>  
		         		<select name="jzxlx" style="width:130px" onchange="JZtypes()">
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList jzxlx = new ArrayList();
			         	jzxlx = ztcommon.getSelctOptions("XLX");
			         	
			         	if(jzxlx!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)jzxlx.get(0)).intValue();
			         		for(int i=cscount;i<jzxlx.size()-1;i+=cscount)
		                    {
		                    	code=(String)jzxlx.get(i+jzxlx.indexOf("CODE"));
		                    	name=(String)jzxlx.get(i+jzxlx.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
		                    
			         	}
			         %>
		         	</select><span class="style1">&nbsp;*</span>
		         </td>
		         </tr>
		         </table>
         </div>
         </td>
                 
      </tr>
      <tr>
      	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">虚拟站点：</div>
         </td>
         <td width="25%"><input type="checkbox" name="xuni" />
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">是否采集点：</div>
         </td>
         <td width="25%">
         	<select name="caiji"  style="width:130">
				         		<option value="0">否</option>
				         		<option value="1">是</option>
				         	</select>
         </td>
         <td colspan="2">
         <div id="JFLXKZ"  style="width:100%;height:100%;display:none;">
          <table width="100%">
            <tr>
               <td bgcolor="#DDDDDD" width="47%">
               		<div align="left">局房类型：</div>
         		</td>
		         <td>  
		         		<select name="jflx" style="width:130px">
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList jflx = new ArrayList();
			         	jflx = ztcommon.getSelctOptions("JFLX");
			         	if(jflx!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)jflx.get(0)).intValue();
			         		for(int i=cscount;i<jflx.size()-1;i+=cscount)
		                    {
		                    	code=(String)jflx.get(i+jflx.indexOf("CODE"));
		                    	name=(String)jflx.get(i+jflx.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
		         	</select>
		         </td>
		         </tr>
		         </table>
         </div>
         <div id="JRWTYPE"  style="width:100%;height:100%;display:none;">
          <table width="100%">
            <tr>
               <td bgcolor="#DDDDDD" width="47%">
               		<div align="left">接入网类型：</div>
         		</td>
		         <td>  
		         		<select name="jrwtype" style="width:130px" onchange="JZtypes()">
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList jrwtype = new ArrayList();
		         		jrwtype = ztcommon.getSelctOptions("JRWLX");
			         	if(jflx!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)jrwtype.get(0)).intValue();
			         		for(int i=cscount;i<jrwtype.size()-1;i+=cscount)
		                    {
		                    	code=(String)jrwtype.get(i+jrwtype.indexOf("CODE"));
		                    	name=(String)jrwtype.get(i+jrwtype.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
		         	</select><span class="style1">&nbsp;*</span>
		         </td>
		         </tr>
		         </table>
         </div>
         </td>
      </tr>
     
      				<tr>

								<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">参照站点：</div>
				         </td>
				         <td width="25%"><input id="a" type="text" name="zzjgbm" value=""  class="form" style="width:100px"/>
					<img id="liulan" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png" style="cursor: pointer"/>
	
				         	</td>
				         	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点产权：</div>
				         </td>
				         <td width="25%">
				         	<select name="zdcq" style="width:130">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList zdcqlist = new ArrayList();
						         	zdcqlist = ztcommon.getSelctOptions("ZDCQ");
						         	if(zdcqlist!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)zdcqlist.get(0)).intValue();
						         		for(int i=cscount;i<zdcqlist.size()-1;i+=cscount)
					                    {
					                    	code=(String)zdcqlist.get(i+zdcqlist.indexOf("CODE"));
					                    	name=(String)zdcqlist.get(i+zdcqlist.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						       </select>
				         	</td>
				         	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">节能技术描述：</div>
				         </td>
				         <td width="25%"><input id="a" type="text" name="jnjsms" value=""  class="form" style="width:130px"/></td>
				      </tr>
				      <tr>

				         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">直流负荷：</div>
				         </td>
				         <td width="25%"><input id="a" type="text" name="zlfh" value="" onchange="JZtypes()" class="form" style="width:130px"/></td>
							 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">有无空调：</div>
         </td>
         <td width="25%">
         	<select name="kongtiao"  style="width:130">
				         		<option value="无">无</option>
				         		<option value="有">有</option>
				         	</select>
         </td>	
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">备注：</div>
         </td>
         <td width="26%"><input id="a" type="text" name="memo" value=""  class="form" style="width:130px"/>
         </td>
				      </tr>

      <tr>

				
				<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">额定耗电量：</div>
         </td>
         <td width="25%"><input id="a" type="text" name="edhdl" value="<%=form.getEdhdl()%>"  class="form" style="width:130px"/></td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">标杆类型标号:</div>
         </td>
         <td width="25%">
				         	<select name="signtypenum" style="width:130">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList signtypenum = new ArrayList();
						         	signtypenum = ztcommon.getSigntypenum();
						         	if(signtypenum!=null){
						         		String num="",name="";
						         		int cscount = ((Integer)signtypenum.get(0)).intValue();
						         		for(int i=cscount;i<signtypenum.size()-1;i+=cscount)
					                    {
					                    	num=(String)signtypenum.get(i+signtypenum.indexOf("ID"));
					                    	name=(String)signtypenum.get(i+signtypenum.indexOf("NAME"));
					                    %>
					                    <option value="<%=num%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						       </select>
				              </td>	      
         
      </tr>
    
      <tr>
      	<td colspan=6>
      		<div align="center" id="IDCJFKZ" style="display:none;">
      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;IDC机房扩展信息&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr>
      					<td>出口宽带：<input type="text" name="ckkd" value="<%=form.getCkkd()%>"></td>
      					<td>已使用面积：<input type="text" name="idcysymj" value="<%=form.getYsymj()%>"/></td>
      					<td>机柜个数：<input type="text" name="jggs" value="<%=form.getJggs()%>"/></td>
      					<td>已使用个数：<input type="text" name="ysygs" value="<%=form.getYsygs()%>"/></td>		
      				</tr>
      				<tr>
      				<td>IDC机房星级：
      						<select name="txj" style="width:130">
      						<option value="0">请选择</option>
				         		<%
					         	ArrayList txj = new ArrayList();
					         	txj = ztcommon.getSelctOptions("TXJ");
					         	if(txj!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)txj.get(0)).intValue();
					         		for(int i=cscount;i<txj.size()-1;i+=cscount)
				                    {
				                    	code=(String)txj.get(i+txj.indexOf("CODE"));
				                    	name=(String)txj.get(i+txj.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         %>
					         </select>
      					</td>
      					<td>IDC节能技术：
      						<select name="jnjslx" style="width:130">
      						<option value="0">请选择</option>
				         		<%
					         	ArrayList jnjslx = new ArrayList();
					         	jnjslx = ztcommon.getSelctOptions("JNJSLX");
					         	if(jnjslx!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)jnjslx.get(0)).intValue();
					         		for(int i=cscount;i<jnjslx.size()-1;i+=cscount)
				                    {
				                    	code=(String)jnjslx.get(i+jnjslx.indexOf("CODE"));
				                    	name=(String)jnjslx.get(i+jnjslx.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         %>
					         </select>
      					</td>
      					<td>U个数：<input type="text" name="ugs" value="<%=form.getUgs()%>"/></td>
      					<td>已使用U个数：<input type="text" name="ysyugs" value="<%=form.getYsyugs()%>"/></td>		
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr>
      	<td colspan=6>
      		<div align="center" id="ZHJFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;综合机房扩展信息&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr>
      					<td>机房高度：<input type="text" name="jfgd" value="<%=form.getJfgd()%>"></td>
      					<td>设定温度：<input type="text" name="sdwd" value="<%=form.getSdwd()%>"/></td>
      					<td>送风方式：
      						<select name="sffs" style="width:130">
      						<option value="0">请选择</option>
				         		<%
					         	ArrayList sffs = new ArrayList();
					         	sffs = ztcommon.getSelctOptions("SFFS");
					         	if(sffs!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)sffs.get(0)).intValue();
					         		for(int i=cscount;i<sffs.size()-1;i+=cscount)
				                    {
				                    	code=(String)sffs.get(i+sffs.indexOf("CODE"));
				                    	name=(String)sffs.get(i+sffs.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         %>
					         </select>
      					</td>
      					<td>冷源方式：
      						<select name="lyfs" style="width:130">
      						<option value="0">请选择</option>
					         		<%
						         	ArrayList lyfs = new ArrayList();
						         	lyfs = ztcommon.getSelctOptions("LYFS");
						         	if(lyfs!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)lyfs.get(0)).intValue();
						         		for(int i=cscount;i<lyfs.size()-1;i+=cscount)
					                    {
					                    	code=(String)lyfs.get(i+lyfs.indexOf("CODE"));
					                    	name=(String)lyfs.get(i+lyfs.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						         </select>
      					</td>		
      				</tr>
      				<script>
      					document.form1.sffs.value='<%=form.getSffs()%>';
      					document.form1.jnjslx.value='<%=form.getJnjslx()%>';
      					document.form1.txj.value='<%=form.getTxj()%>';
      					document.form1.lyfs.value='<%=form.getLyfs()%>';
      					</script>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr>
      	<td colspan=6>
      		<div align="center" id="JZKZ"  style="display:none;">
      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;基站扩展信息&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr>
      					<td>共站情况：<input type="text" name="gzqk" value="<%=form.getGzqk()%>"></td>
      					<td>能耗占比：<input type="text" name="nhzb" value="<%=form.getNhzb()%>"/></td>
      					<td>载频数量：<input type="text" name="zpsl" value="<%=form.getZpsl()%>"/></td>
      				<td >逻辑站数：<input type="text" name="ljzs" value="<%=form.getLjzs()%>" /></td>
      				
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr>
      	<td colspan=6>
      		<div align="center" id="GLYFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;管理用房扩展信息&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr>
      					<td>在岗人员：<input type="text" name="glzgry" value="<%=form.getZgry()%>"></td>
      					<td>空调数量：<input type="text" name="glktsl" value="<%=form.getKtsl()%>"/></td>
      					<td>PC数量：<input type="text" name="glpcsl" value="<%=form.getPcsl()%>"/></td>
      				    
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr>
      	<td colspan=6>
      		<div align="center" id="QDYFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;渠道用房扩展信息&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr>
      					<td>在岗人员：<input type="text" name="qdzgry" value="<%=form.getZgry()%>"></td>
      					<td>空调数量：<input type="text" name="qdktsl" value="<%=form.getKtsl()%>"/></td>
      					<td>PC数量：<input type="text" name="qdpcsl" value="<%=form.getPcsl()%>"/></td>
      					<td>人流量（天/人）：<input type="text" name="rll" value="<%=form.getRll()%>"/></td>
      				
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr>
      	<td colspan=6>
      		<div align="center" id="FSCYFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;通信机房扩展信息&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr>
      					<td>在岗人员：<input type="text" name="zgry" value="<%=form.getZgry()%>"></td>
      					<td>空调数量：<input type="text" name="ktsl" value="<%=form.getKtsl()%>"/></td>
      					<td>已使用面积：<input type="text" name="ysymj" value="<%=form.getYsymj()%>"/></td>
      					<td>PC数量：<input type="text" name="pcsl" value="<%=form.getPcsl()%>"/></td>
      				    <td>用电类型：
      						<select name="ydlx" style="width:130">
      						<option value="0">请选择</option>
					         		<%
						         	ArrayList ydlx = new ArrayList();
						         	ydlx = ztcommon.getSelctOptions("YDLX");
						         	if(ydlx!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)ydlx.get(0)).intValue();
						         		for(int i=cscount;i<ydlx.size()-1;i+=cscount)
					                    {
					                    	code=(String)ydlx.get(i+ydlx.indexOf("CODE"));
					                    	name=(String)ydlx.get(i+ydlx.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						         </select>
      					</td>		
      				</tr>
      				<script>
      					
      					document.form1.ydlx.value='<%=form.getYdlx()%>';
      					</script>
      			</table>
      		</div>
      	</td>
      </tr>
       
    <tr>
        <td colspan="6">
        	<div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
			<img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
			<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>      
		</div>
		<div id="resetBtn" style="position:relative;width:60px;height:23px;cursor: pointer;right:15px;float:right;">
			<img alt="" src="<%=path %>/images/2chongzhi.png" width="100%" height="100%" />
			<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">重置</span>
		</div>
		<div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:26px">
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
     
<input type="hidden" name="id" value="<%=id%>"/>
<input type="hidden" name="kzid" value="<%=form.getKzid()%>"/>
<input type="hidden" name="czzdid" value="<%=form.getCzzdid()%>"/>
</form>
</body>
</html>
<script type="text/javascript">
<!--


function changeCity(){
	var sid = document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/area?action=shi&pid="+sid,"shi");
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
function changeXian(){
			var shi = document.form1.xian.value;
			if(shi=="0"){
				var shilist = document.all.xiaoqu;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=xian&pid="+shi,"xian");
			}
		}
		
		function updateXQ(res){
			var shilist = document.all.xiaoqu;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		
		
		function liulan(){
		//window.open("zhandianselect.jsp");
		//return;
		var url=path+"/web/jizhan/zhandianselect.jsp"
		//window.open(url);
		//return;
			var obj = new Object();
			obj.mid='mid';
		    var obj=showModalDialog(url,obj,'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no');	
			document.form1.czzdid.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.czzd.value=obj.substring(0,obj.indexOf(","));
	}
	
	
document.form1.shi.value='<%=form.getShi()%>';
document.form1.xian.value='<%=form.getXian()%>';
document.form1.xiaoqu.value='<%=form.getXiaoqu()%>';
document.form1.signtypenum.value='<%=form.getSigntypenum()%>';

if(document.form1.jztype.value==""){
document.form1.jztype.value="0";
}
document.form1.jztype.value='<%=form.getJztype()%>';
document.form1.jzproperty.value='<%=form.getJzproperty()%>';
document.form1.yflx.value='<%=form.getYflx()%>';
document.form1.jzname.value='<%=form.getJzname()%>';
document.form1.bieming.value='<%=form.getBieming()%>';
document.form1.zdcode.value='<%=form.getZdcode()%>';
if('<%=form.getBgsign()%>'=="1"){
document.form1.bgsign.checked=true;
}
document.form1.jnglmk.value='<%=form.getJnglmk()%>';
document.form1.address.value='<%=form.getAddress()%>';
document.form1.gdfs.value='<%=form.getGdfs()%>';
document.form1.area.value='<%=form.getArea()%>';
document.form1.memo.value='<%=form.getMemo()%>';
document.form1.fzr.value='<%=form.getFzr()%>';
document.form1.dianfei.value='<%=form.getDianfei()%>';
//pue
if('<%=form.getXuni()%>'=='1'){
		document.form1.xuni.checked=true;
		document.getElementById("IDXUNI").style.display="block";
}
document.form1.zzjgbm.value='<%=form.getZzjgbm()%>';

document.form1.czzdid.value='<%=form.getCzzdid()%>';

document.form1.zdcq.value='<%=form.getZdcq()%>';

document.form1.zlfh.value='<%=form.getZlfh()%>';
document.form1.jnjsms.value='<%=form.getJnjsms()%>';

document.form1.caiji.value='<%=form.getCaiji()%>';
document.form1.jflx.value='<%=form.getJflx()%>';
document.form1.jzxlx.value='<%=form.getJzxlx()%>';
document.form1.jrwtype.value='<%=form.getJrwtype()%>';
document.form1.kongtiao.value='<%=form.getKongtiao()%>';
document.form1.gsf.value='<%=form.getGsf()%>';
document.form1.stationtype.value='<%=form.getStationtype()%>';

function kzinfo(){
    var jzproperty = document.form1.jzproperty.value; 
	var jztype = document.form1.jztype.value;
	
	if(jztype=="zdlx01"){//IDC机房
		document.getElementById("IDCJFKZ").style.display="block";
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx03"){//综合机房
		document.getElementById("ZHJFKZ").style.display="block";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx08"){//基站
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="block";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx10"){//管理用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="block";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx11"){//渠道用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="block";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx12"){//非生产用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="block";
	}else{
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}
	if(jzproperty=="zdsx01"){//局房类型
		
		document.getElementById("JFLXKZ").style.display="block";
		document.getElementById("JZLXKZ").style.display="none";
		document.getElementById("JRWTYPE").style.display="none";
	}else if(jzproperty=="zdsx02"){//基站类型
		
		document.getElementById("JFLXKZ").style.display="none";
		document.getElementById("JZLXKZ").style.display="block";
		document.getElementById("JRWTYPE").style.display="none";
	}else if(jzproperty=="zdsx05"){//基站类型
		
		document.getElementById("JFLXKZ").style.display="none";
		document.getElementById("JZLXKZ").style.display="none";
		document.getElementById("JRWTYPE").style.display="block";
	}else{
	     document.getElementById("JFLXKZ").style.display="none";
		 document.getElementById("JZLXKZ").style.display="none";
		 document.getElementById("JRWTYPE").style.display="none";
	}
}
var typecode='<%=form.getJztype()%>';
var code='<%=form.getJzproperty()%>';

if(typecode=="zdlx01"){//IDC机房
		document.getElementById("IDCJFKZ").style.display="block";
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx03"){//综合机房
		document.getElementById("ZHJFKZ").style.display="block";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx08"){//基站
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="block";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx10"){//管理用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="block";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx11"){//渠道用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="block";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx12"){//非生产用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="block";
	}else{
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}
	if(code=="zdsx01"){//局房类型
		
		document.getElementById("JFLXKZ").style.display="block";
		document.getElementById("JZLXKZ").style.display="none";
		document.getElementById("JRWTYPE").style.display="none";
	}else if(code=="zdsx02"){//基站类型
		
		document.getElementById("JFLXKZ").style.display="none";
		document.getElementById("JZLXKZ").style.display="block";
		document.getElementById("JRWTYPE").style.display="none";
	}else if(code=="zdsx05"){//基站类型
		
		document.getElementById("JFLXKZ").style.display="none";
		document.getElementById("JZLXKZ").style.display="none";
		document.getElementById("JRWTYPE").style.display="block";
	}else{
	     document.getElementById("JFLXKZ").style.display="none";
		 document.getElementById("JZLXKZ").style.display="none";
		 document.getElementById("JRWTYPE").style.display="none";
	}
	
	function showxuni(){

			if(document.form1.xuni.checked){
				document.getElementById("IDXUNI").style.display="block";
			}else{
				document.getElementById("IDXUNI").style.display="none";
			}
		}
</script>

