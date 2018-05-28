<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeBean" %>

<%
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
    String roleId = account.getRoleId();
    String accountname = account.getAccountName(); 
        
        
        String degreeid = request.getParameter("degreeid");
		AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
	    bean = bean.getAmmeterDegreeInfo(degreeid);
	    AmmeterDegreeFormBean beanad = new AmmeterDegreeFormBean();
	    beanad = beanad.getLastAmmeterModfiyDegree(bean.getAmmeteridFk(),degreeid);
	    String lastad = beanad.getLastdegree();
	    if(lastad.equals("")){
	       System.out.println("上次电表读数为空！");
	       AmmeterDegreeFormBean beanada = new AmmeterDegreeFormBean();
	       beanada = beanada.getStartAmmeterModfiyDegree(bean.getAmmeteridFk());
	       lastad = beanada.getInitialdegree();
	    }
	    
	    
	    AmmeterDegreeBean beann=new AmmeterDegreeBean();
        ArrayList listt=new ArrayList();
        // String db=request.getParameter("ammeteridFk");
        String db=bean.getAmmeteridFk();
        System.out.println("电表id："+db);
        listt=beann.selectXSX(db);
        String linelosstype="",linelossvalue="";
        String beilv="",zlfh = "",jlfh = "",property = "",edhdl = "",scb = "",shi = "",qsdbdl = "",stationtype = "";
        int fycount = ((Integer)listt.get(0)).intValue();
        for( int k = fycount;k<listt.size()-1;k+=fycount){
		   
		    linelosstype = (String)listt.get(k+listt.indexOf("LINELOSSTYPE"));
		    linelosstype = linelosstype != null ? linelosstype : "";
		    
		    beilv = (String)listt.get(k+listt.indexOf("BEILV"));
		    beilv = beilv != null ? beilv : "";
		    
		    linelossvalue = (String)listt.get(k+listt.indexOf("LINELOSSVALUE"));
		    linelossvalue = linelossvalue != null ? linelossvalue : "";
		    
		    zlfh = (String)listt.get(k+listt.indexOf("ZLFH"));
		    zlfh = zlfh != null ? zlfh : "";
		    jlfh = (String)listt.get(k+listt.indexOf("JLFH"));
		    jlfh = jlfh != null ? jlfh : "";
		    property = (String)listt.get(k+listt.indexOf("PROPERTY"));
		    property = property != null ? property : "";
		    edhdl = (String)listt.get(k+listt.indexOf("EDHDL"));
		    edhdl = edhdl != null ? edhdl : "";
		    scb = (String)listt.get(k+listt.indexOf("SCB"));
		    scb = scb != null ? scb : "";
		    shi = (String)listt.get(k+listt.indexOf("SHI"));
		    shi = shi != null ? shi : "";
		    qsdbdl = (String)listt.get(k+listt.indexOf("QSDBDL"));
		    qsdbdl = qsdbdl != null ? qsdbdl : "";
		    stationtype = (String)listt.get(k+listt.indexOf("STATIONTYPE"));
		    stationtype = stationtype != null ? stationtype : "";
        }
%>
<html>
<head>
<title>
addDegree
</title>
<style>
            
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
.form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	

		}
.form{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;		
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
 
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
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
	function modifyDegree(){
		var edhdl =  document.form1.edhdl.value;
		var zlfh = document.form1.zlfh.value;
		var jlfh = document.form1.jlfh.value;
		var scb = document.form1.scb.value;
		var property = document.form1.property.value;
		var qsdbdl = document.form1.qsdbdl.value;
	
	     var ad2_bz="";//AuditDegree2状态标志
	       if(document.form1.lastad.value != document.form1.lastdegree.value){
	         //alert(document.form1.lastad.value);
	         ad2_bz="1";
	       }
	        if(0== Number(edhdl)){
				alert("系统中的额定耗电量是空值,请先填写额定耗电量！！！");	
			}else{
				if(0==Number(qsdbdl)){
					alert("系统中的全省定标电量空值，请先填写全省定标电量！！！");	
				}else{
				if(0==Number(zlfh)){
					alert("系统中的直流负荷是空值，请先填写直流负荷！！！");	
				}else{
					if(0==Number(jlfh)){
						alert("系统中的交流负荷是空值，请先填写交流负荷！！！");	
					}else{
						if(0==Number(scb)){
							alert("系统中的生产标是空值，请先填写生产标！！！");	
						}else{
							if(property==""||property=="null"){
								alert("系统中的站点属性为空值，请先填写站点属性！！！");	
							}else{
	         if(checkNotnull(document.form1.startmonth,"起始时间")&&checkNotnull(document.form1.endmonth,"结束时间")&&checkNotnull(document.form1.thisdatetime,"本次抄表的时间")&&checkNotnull(document.form1.thisdegree,"本次电表读数")){
				 var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
                 var tdt = document.form1.thisdatetime.value;
                 var ldt = document.form1.lastdatetime.value;
                 var ipt = document.form1.inputdatetime.value;
                 var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
                 var em = document.form1.endmonth.value;
                 var sm = document.form1.startmonth.value;
                 if((isDate(ipt)==true||ipt==""||ipt==null)){
                 	if(isDate(ldt)==true||ldt==""||ldt==null){
                 		if(isDate(tdt)==true){
                 			if(reg1.exec(sm)){
                 				if(reg1.exec(em)){
						                 if(ldt<=tdt){
						                	if(em>=sm){
						                		if(isNaN(document.form1.blhdl.value)==false){
									                if(confirm("您将要修改电量信息！确认信息正确！")){
									                document.form1.action=path+"/servlet/ammeterdegree?action=modify&ad2_bz="+ad2_bz;
									    			document.form1.submit();
									    			showdiv("请稍等..............");
									                }
									             }else{
								                  alert("您输入的信息有误，折算后用电量必须为数字！");
								                 }
								             }else {
								                   alert("您输入的信息有误，结束年月必须大于等于起始年月！");
								             }
						                 }else{
						                     alert("您输入的信息有误，本次抄表的时间必须大于等于上次抄表的时间！");
						                 }
								 }else{
								 	alert("您输入的结束时间有误，请确认后重新输入！");
								 }
							}else{
								alert("您输入的起始时间有误，请确认后重新输入！");
							}
						}else{
							alert("您输入的本次抄表时间有误，请确认后重新输入！");
						}
					}else{
						alert("您输入的上次抄表时间有误，请确认后重新输入！");
					}
			     }else{
			     	alert("您输入的当前时间有误，请确认后重新输入！");
			     }
             }	
	         		}
				}
			}
		}
	}
}
	}
					function isDate(str){
 var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
 if(reg.test(str)){
       return true;
  }else{
  
 return false;
 
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
          function getHaodianliang(){
        	  
        	 var bl=document.form1.beilv.value;
        	 if(bl==null||bl=="")bl="1"
        	 var thisdegree=document.form1.thisdegree.value;
             var lastdatetime=document.form1.lastdegree.value;
			 var floatdegree=document.form1.floatdegree.value;
			 var linelossvalue=document.form1.linelossvalue.value;
			 var actualdegree=document.form1.actualdegree.value;
			 document.form1.actualdegree.value=(parseFloat(Number(document.form1.thisdegree.value)-Number(document.form1.lastdegree.value))+parseFloat(Number(document.form1.floatdegree.value)));
        	 if(document.form1.linelosstype.value=="01xstz"){	
        		document.form1.blhdl.value=((Number(thisdegree)-Number(lastdatetime)+Number(linelossvalue)+Number(floatdegree))*bl).toFixed(2);
        	 }
        	 else{
        		
        		 if(linelossvalue==null||linelossvalue=="")linelossvalue="1";
        		document.form1.blhdl.value=((parseFloat(Number(thisdegree)-Number(lastdatetime))*parseFloat(Number(linelossvalue))+parseFloat(Number(floatdegree)))*bl).toFixed(2);
        	    
        	 }
        	 //  document.form1.actualpay.value=(parseFloat(document.form1.unitprice.value)*parseFloat(document.form1.blhdl.value)+parseFloat(Number(document.form1.floatpay.value))).toFixed(2);       	        	 
          
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
				$.each($("form input[type='text']"),function(){
					$(this).val("");
				})
			});
			$("#baocun").click(function(){
				modifyDegree();
				
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
	
<form action="" name="form1" method="POST">
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
							<td colspan="4" width="50" >
											 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">修改电量</span>	
												</div>
											</td>
							</tr>
					</table>
					
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
						
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" >
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
		<%
			
            
            
		%>
		
			 <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" /><font size="2">修改信息</font></td>
                    </tr>
          </table>
         
     <table width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
     <tr><td>
		 <input type="hidden" name="lastad" value="<%=lastad %>"  class="form" />
		 <input type="hidden" name="linelosstype" value="<%=linelosstype %>">
		 <input type="hidden" name="linelossvalue" value="<%=linelossvalue %>"/>
		 <input type="hidden" name="beilv" value="<%=beilv %>"/>
		 <input type="hidden" name="accountname" value="<%=accountname%>"/>
		 <input type="hidden" name="zlfh" value="<%=zlfh %>"/>
		   <input type="hidden" name="jlfh" value="<%=jlfh %>"/>
		   <input type="hidden" name="property" value="<%=property %>"/>
		   <input type="hidden" name="edhdl" value="<%=edhdl %>"/>
		   <input type="hidden" name="scb" value="<%=scb %>"/>
			<input type="hidden" name="shi" value="<%=shi %>"/>
			<input type="hidden" name="qsdbdl" value="<%=qsdbdl %>"/>
			<input type="hidden" name="stationtype" value="<%=stationtype %>"/>
		</td></tr>
     
      <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>电表ID：</div>
         </td>
         <td width="35%"><input type="text" name="ammeteridFk" value="<%=bean.getAmmeteridFk()%>" readonly="true" class="selected_font1" /><span class="style1">&nbsp;*</span>
           
         </td>
 
      </tr>
        <tr>

      <tr>
                 <td height="19" bgcolor="#DDDDDD" width="15%"><div>上次电表读数：</div>
         </td>
         <td width="35	%"><input type="text" name="lastdegree" value="<%=bean.getLastdegree() %>" onChange="getHaodianliang()" class="selected_font3" />

         </td>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>本次电表读数：</div>
         </td>
         <td><input type="text" name="thisdegree" value="<%=bean.getThisdegree() %>" onChange="getHaodianliang()" class="selected_font3" />
        <span class="style1">&nbsp;*</span>
         </td>
         

      <tr>

      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>上次抄表时间：</div>
         </td>
         <td>
			<input type="text" name="lastdatetime" class="form" value="<%=bean.getLastdatetime() %>" 
				   class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" maxlength="11"/>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>本次抄表时间：</div>
         </td>
         <td><input type="text" name="thisdatetime" class="form" value="<%=bean.getThisdatetime() %>" 
         			class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" align="right"/>
        <span class="style1">&nbsp;*</span>
         </td>
         
      </tr>
        <tr>


      <tr>
      <td height="19" bgcolor="#DDDDDD"><div>用电量调整：</div>
         </td>
         <td><input type="text" name="floatdegree" value="<%=bean.getFloatdegree() %>" onChange="getHaodianliang()" class="selected_font3" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>用电量：</div>
         </td>
         <td><input type="text" name="actualdegree" value="<%=bean.getActualdegree() %>" readonly="true" class="selected_font3" />
           </td>
      </tr>
      
          <tr>
      <td height="19" bgcolor="#DDDDDD"><div>抄表操作员：</div>
         </td>
         <td><input type="text" name="inputoperator" value="<%=bean.getInputoperator() %>"  class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>折算后用电量：</div>
         </td>
         <td><input type="text" name="blhdl" value="<%=bean.getBlhdl() %>" readonly="readonly" class="selected_font3" />
           </td>
      </tr>
     <tr>
     
      <td height="19" bgcolor="#DDDDDD"><div>起始时间：</div>
         </td>
         <td><input type="text" name="startmonth" class="form" value="<%=bean.getStartmonth() %>" 
         			class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
        <span class="style1">&nbsp;*</span>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>结束时间：</div>
         </td>
         <td><input type="text" name="endmonth" class="form" value="<%=bean.getEndmonth() %>"
         			class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" />
          <span class="style1">&nbsp;*</span>
           </td>
      </tr>
      <tr>
      <tr>
         <td height="19" bgcolor="#DDDDDD" ><div>备注：</div>
         </td>
         <td><input type="text" size="50" name="memo" value="<%=bean.getMemo() %>"  class="form" />
         </td>
          <td height="19" bgcolor="#DDDDDD"><div>当前时间：</div>
         </td>
         <td><input type="text" name="inputdatetime" class="form" value="<%=bean.getInputdatetime() %>" 
         			class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
         </td>
         <td><input type="hidden" size="50" name="autoauditstatus" value="<%=bean.getAutoauditstatus() %>"  class="form" />
         </td>
         <td><input type="hidden" size="50" name="autoauditdescription" value="<%=bean.getAutoauditdescription() %>"  class="form" />
         </td>
      </tr>
	  <tr><td>&nbsp;</td></tr>
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
     
<input type="hidden" name = "ammeterdegreeid" value="<%=degreeid%>"/>
</form>
</body>
</html>

