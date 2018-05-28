<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.jizhan.DianBiaoForm" %>
<%
	String path = request.getContextPath();
	String id = request.getParameter("id");
	String zdid = request.getParameter("zdid");
	DianBiaoForm form = new DianBiaoForm();
	form = form.getInfoData(id);//修改电表信息页面带出的信息
    String countbzw = form.getCountbzw();//"0"---1块电表;"1"---多块电表
	DianBiaoForm form1 = new DianBiaoForm();
	form1 = form1.getQyztCount(id,zdid);//当前站点下的非当前电表的启用电表总数和关闭电表总数
	double qydb = form1.getDbqyzs();//启用电表总数
	double gbdb = form1.getDbgbzs();//关闭电表总数
	double count = qydb + gbdb;
    Account account=(Account)session.getAttribute("account");
    String roleid = account.getRoleId();//权限
    String accountname = account.getAccountName();
%>
<html>
<head>
<title>
addAccount
</title>
<style>
 .style1 {
	color:red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px

}
.form_label1{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
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

.bttcn{color:white;font-weight:bold;}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
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
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>

<script >
var path = '<%=path%>';
	function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = "";
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">";
		}
		else
		{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
}
	function saveAccount(){
		var roleid = <%=roleid%>;
		
/*
          	if(checkNotnull(document.form1.accountName,"账号")&&
                  checkNotnull(document.form1.password,"密码")&&
                   checkNotnull(document.form1.confirmPassword,"确认密码")&&
                   checkNotnull(document.form1.name,"姓名")){
                      
  */                var danjia = parseFloat(document.form1.danjia.value); 
  					//var danjia1 = parseFloat(document.form1.danjia1.value);
  					var zzsl = parseFloat(document.form1.zzsl.value);//增值税率
  					var csds = parseFloat(document.form1.csds.value);
					var beilv = parseFloat(document.form1.beilv.value);
					var linelossvalue = parseFloat(document.form1.linelossvalue.value);
					var bsdl = parseFloat(document.form1.bsdl.value);
					
					//alert(danjia);
					if(checkNotSelected(document.form1.dfzflx,"电费支付类型")&&checkNotnull(document.form1.danjia,"电表单价")&&checkNotnull(document.form1.zzsl,"增值税率")){
						if(isNaN(document.form1.csds.value)==false&&isNaN(document.form1.beilv.value)==false&&
						   //isNaN(document.form1.yhdl.value)==false&&
						   isNaN(document.form1.danjia.value)==false&&
						   isNaN(document.form1.zzsl.value)==false&&isNaN(document.form1.bsdl.value)==false&&
						   isNaN(document.form1.linelossvalue.value)==false){
			                    var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
			                    var cyt = document.form1.cssytime.value;
			                    var cssytime1 = '<%=form.getCssytime()%>';
								var str = "2014-01-01";
								var dbqyzt = document.form1.dbqyzt.value;//"0"---否;"1"---是
								var count = <%=count%>;
								var qydb = <%=qydb%>;//启用电表总数
								var gbdb = <%=gbdb%>;//关闭电表总数
								var countbzw = '<%=countbzw%>';								
								if(cyt==""||cyt==null||reg.exec(cyt)){
						        	if(cyt==cssytime1 || cyt>=str){
						        	//alert(cyt);
						            	if(csds>=0){					            	
				          						if(beilv>=0){
				          							if(linelossvalue>=0){
				          								if(bsdl>=0){
												        	if(0<zzsl&&zzsl<=1){
												            	if(document.form1.dbqyzt.value==""||document.form1.dbqyzt.value==null){
												            		alert("电表启用状态不能为空！");
																}else{
														        	if((dbqyzt=="0"&&count==0)||(dbqyzt=="0"&&countbzw=="1"&&qydb==0)){
												                    	alert("该站点下只有一块结算电表，不允许关闭，请确认！");		                        
														            }else{
														           // alert(dbqyzt);
														            	if(dbqyzt!=null){								   										   
															               	if(roleid == "101" || roleid == "102" || roleid == "104"){//区县管理，区县录入，区县分析
															                	if(danjia!=null){
																	            	if(confirm("您将要修改电表信息！确认信息正确！")){
																						document.form1.action=path+"/servlet/dianbiao?action=modifydianbiao";
																						document.form1.submit();
																	                }
																                }else{
																                    alert("单价修改只能小于上次单价，调高请走信息修改申请！");
																                }
															                }else{
															                	if(confirm("您将要修改电表信息！确认信息正确！")){
																					document.form1.action=path+"/servlet/dianbiao?action=modifydianbiao";
																					document.form1.submit();
																					
																	            }
															                }
															            }
															            else{
															            	alert("不符合电表使用要求,请恢复电表启用状态!");
															            } 
														           	}
														         }
														      }else{
																	alert("增值税率必须为:大于0且小于1！");
														      }
												     		}else{
				          							  			alert("变损电量大于等于0！");
				          							  		}
				          								}else{
				          									alert("线损值大于等于0！");
				          								}
				          							}else{
				          								alert("倍率大于等于0！");
				          							}
				          						}
// 				          						else{
// 				          							alert("单价大于等于0！");
// 				          						}
						          			}else{
						          				alert("初始读数大于等于0！");	
						          			}
						                }else{
						                    alert("电表初始使用时间要大于等于2014-01-01"); 
						              	}
						      		}else{
						           		alert("您输入的初始使用时间有误，请确认后重新输入！");
						           	}
			                 	}else{
			                    	alert("您输入的信息有误，输入的信息必须为数字");
			                	}
		               		}
                    	
        	

        function vName(){
         	var accName = document.form1.dbid.value;
                 if(accName==""){
           	alert("电表ID不能为空！");
                   return
          }
               window.open('validateDbid.jsp?dbid='+accName+'&id='+'<%=id%>','','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
        }
        function vCode(){
          var accCode = document.form1.workSn.value;
          if(accCode==""){
           	alert("不能为空！");
                   return
          }
               window.open('accountCode.jsp?accountId=0&accountCode='+accCode,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
        }

        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！");
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
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
		//showdiv("请稍等..............");
		});
	$("#vnameBtn").click(function(){
		vName();
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
 
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" >
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
						<td colspan="4">
							  <div style="width:700px;height:50px">
<!-- 							        -->
							       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">修改电表信息</span>	
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
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1"  class="form_label">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1" class="form_label">

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
			
			<tr><td>&nbsp;</td></tr>			 
			<tr bgcolor="F9F9F9">
                      <td height="19" colspan="2">&nbsp;&nbsp;<img src="../../images/v.gif" width="15" height="15"/>&nbsp;基本信息</td>
            </tr>
<!--             <tr><td>&nbsp;</td></tr>	                          -->
     <tr>
        
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电表名称：</div>        
         </td>
         <td width="25%">
            <input type="text" name="dbname" value=""  class="selected_font"/> 
        
         	<input type="hidden" name="dbid" value=""  class="form" style="width:130px"/>         				 
         </td>
<!--          <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">所属专业：</div> -->
<!--          </td> -->
<!--          <td width="25%"> -->
<!--          	<select name="sszy" class="selected_font"> -->
<!--          		<option value="0">请选择</option> -->
<!--          		 -->
<!-- 	         	ArrayList sszy = new ArrayList(); -->
<!-- 	         	sszy = ztcommon.getSelctOptions("SSZY"); -->
<!-- 	         	if(sszy!=null){ -->
<!-- 	         		String code="",name=""; -->
<!-- 	         		int cscount = ((Integer)sszy.get(0)).intValue(); -->
<!-- 	         		for(int i=cscount;i<sszy.size()-1;i+=cscount) -->
<!--                     { -->
<!--                     	code=(String)sszy.get(i+sszy.indexOf("CODE")); -->
<!--                     	name=(String)sszy.get(i+sszy.indexOf("NAME")); -->
<!--                     %> -->
<!--                     <option value="=code%>">=name%></option> -->
<!--                     } -->
<!-- 	         	} -->      	
<!--          	</select> -->
<!--          	</td> -->
       
        
        
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电费支付类型：</div>         	
         </td>
         <td width="25%"><input type="text" name="dfzflx" value="<%=form.getDfzflx() %>" class="selected_font" readonly="readonly"/>
         </td>
<!--          <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">用电设备：</div> -->
<!--          </td> -->
<!--          <td width="25%"> -->
<!--          	<select name="ydsb" class="selected_font"> -->
<!--          		<option value="0">请选择</option> -->
<!--          		 -->
<!-- 	         	ArrayList ydsb = new ArrayList(); -->
<!-- 	         	ydsb = ztcommon.getSelctOptions("YDSB"); -->
<!-- 	         	if(ydsb!=null){ -->
<!-- 	         		String code="",name=""; -->
<!-- 	         		int cscount = ((Integer)ydsb.get(0)).intValue(); -->
<!-- 	         		for(int i=cscount;i<ydsb.size()-1;i+=cscount) -->
<!--                     { -->
<!--                     	code=(String)ydsb.get(i+ydsb.indexOf("CODE")); -->
<!--                     	name=(String)ydsb.get(i+ydsb.indexOf("NAME")); -->
<!--                     %> -->
<!--                     <option value="=code%>">=%></option> -->
<!--                     } -->
<!-- 	         	} -->
<!-- 	          -->
<!--          	</select> -->
<!--          	</td> -->
        </tr>
        <tr>
		 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电表用途：</div></td>
         <td width="25%" >
          <span onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseout="this.releaseCapture();">
         	<select name="dbyt" class="selected_font">
         		<%
	         	ArrayList dbyt = new ArrayList();
	         	dbyt = ztcommon.getSelctOptions("DBYT");
	         	if(dbyt!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)dbyt.get(0)).intValue();
	         		for(int i=cscount;i<dbyt.size()-1;i+=cscount)
                    {
                    	code=(String)dbyt.get(i+dbyt.indexOf("CODE"));
                    	name=(String)dbyt.get(i+dbyt.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>         	
         	</select>
         	</span>
         	</td>
         	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">倍率：</div>
         </td>
         <td width="25%"><input type="text" name="beilv" value="" class="selected_font3"/></td>
<!--          	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电流类型</div> -->
<!--          </td> -->
<!--          <td width="25%"> -->
<!--          	<select name="dllx" class="selected_font"> -->
<!--          		<option value="直流">直流</option> -->
<!--          		<option value="交流">交流</option> -->
         	
<!--          	</select>         	 -->
<!--          	</td> -->
      </tr>
      
      <tr>
         
        
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">初始读数：</div>
         </td>
         <td width="26%">
         		<input type="text" name="csds" value=""  class="selected_font3"/>
         </td>
      
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">初始使用时间：</div>
         </td>
         <td width="25%">
         	<input class="selected_font" type="text" name="cssytime"
				value="<%if (null != request.getParameter("cssytime"))   out.print(request.getParameter("cssytime"));%>"
				class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />
         </td>
       </tr>
      
      <tr>
         
<!-- 		 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">月耗电量：</div> -->
<!--          </td> -->
<!--          <td width="25%"> -->
<!--         <input type="text" name="yhdl" value=""  class="selected_font3"/> -->
<!--          </td>		 -->
      </tr>
     <tr>
       <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">线损类型：</div></td>
         <td width="25%">
       		  <select name="linelosstype" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList xslx = new ArrayList();
         		xslx = ztcommon.getSelctXslx("xslx");
	         	if(xslx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)xslx.get(0)).intValue();
	         		for(int i=cscount;i<xslx.size()-1;i+=cscount)
                    {
                    	code=(String)xslx.get(i+xslx.indexOf("CODE"));
                    	name=(String)xslx.get(i+xslx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">线损值：</div></td>
         <td width="25%"><input type="text" name="linelossvalue" value=""  class="selected_font3"/></td>        
      </tr>
      <tr>            	
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">备注：</div></td>
         <td width="25%"><input type="text" name="memo" value=""  class="selected_font"/></td>
              
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电表型号：</div></td>
         <td width="25%"><input type="text" name="dbxh" value=""  class="selected_font"/></td>
    </tr>
<!--     <tr> -->
<!--          <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">MYSQL局站编号：</div></td> -->
<!--          <td width="25%"><input type="text" name="netpoint_id" value=""  class="selected_font"/></td> -->
            
<!--       	 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">MYSQL局站名称：</div></td> -->
<!--          <td width="25%"><input type="text" name="netpoint_name" value=""  class="selected_font"/> -->
<!--              <input type="hidden" name="accountname" value="=accountname %>"/></td> -->
<!--    </tr> -->
    <tr>
    	 
<!--     	 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">自报电用户号：</div></td> -->
<!--          <td width="25%"><input type="text" name="zbdyhh" value="=form.getZbdyhh() %>"  class="selected_font" /></td> -->
    
    </tr>  
    <tr>
    	 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">原电表ID：</div></td>
         <td width="25%"><input type="text" name="ydbid" value="<%=form.getYdbid() %>"  class="selected_font"/></td>
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电表单价：</div></td>
        <td width="25%">
         	<input type="text" name="danjia" value="<%=form.getDanjia() %>"  class="selected_font" title="必填项，根据站点的单价填写"/>
         	<span class="style1">&nbsp;*</span>
        </td>
 
    </tr>
    
    <tr>
    	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">变损电量：</div></td>
        <td width="25%"><input type="text" name="bsdl" value="<%=form.getBsdl() %>"  class="selected_font" /></td>
<!--         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">暂估单价：</div></td> -->
<!--         <td width="25%"><input type="text" name="zgdj" value="=form.getZgdl() %>" class="selected_font" readonly="readonly"/></td> -->
    <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电表启用状态：</div>
         </td>
         
         <%  if(roleid.equals("1")||roleid.equals("61")||roleid.equals("62")||roleid.equals("63")||roleid.equals("64")||roleid.equals("65")
          ||roleid.equals("66")||roleid.equals("67")){ %>
         <td width="25%">
         <select name="dbqyzt" class="selected_font" title="选填项，是否设置此站点为标杆站">
         		<option value="0">否</option>
         		<option value="1">是</option>
         		
         	</select>
         </td>
         <%}else{%>
             <td width="25%">
              <span>
             
             <select name="dbqyzt" class="selected_font"  onfocus="this.defaultIndex=this.selectedIndex;"  onchange="this.selectedIndex=this.defaultIndex;"  style="background-color:#FFFFFF; color:grey"  title="选填项，是否设置此站点为标杆站">
         		<option value="0">否</option>
         		<option value="1">是</option>       		
         	</select></span>
         </td>
         
         <% }%>         
    </tr>
    <tr>
     	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">增值税率：</div></td>
        <td width="25%"><input type="text" name="zzsl" value="<%=form.getZzsl() %>" class="selected_font"/></td>
    </tr>
    
    <tr>
        <td colspan="6">
		<div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:225px;top:15px;">
			<img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
			<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>      
		</div>
        	<div id="resetBtn" style="position:relative;width:60px;height:23px;cursor: pointer;right:236px;float:right;top:15px;">
			<img alt="" src="<%=path %>/images/2chongzhi.png" width="100%" height="100%" />
			<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">重置</span>
		</div>
		<div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:247px;top:15px;">
			 <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>      
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
<!-- 					<p class="form_label1"> -->
<!-- 						<font color="red">说明：<br/> -->
<!-- 											1、若该站点只有一块结算电表，不允许关闭该电表。<br/>		 -->
<!-- 											2、若该站点有多块结算电表，当开启其中一块结算电表时，其他结算电表都会自动关闭。<br/>		 -->
<!-- 							&nbsp;&nbsp;&nbsp;当关闭其中一块结算电表时，则开启的是这个站点下本次抄表时间最大的电表。<br/>		 -->
<!-- 											3、当须增加新的结算电表时，其余的结算电表会自动关闭。<br/>				 -->
<!-- 						</font> -->
<!-- 					</p>   -->
				</td>
			</tr>
		</table>

<input type="hidden" name="id" value="<%=id%>"/>
<input type="hidden" name="zdid" value="<%=zdid%>"/>
<input type="hidden" name="countbzw" value="<%=countbzw%>"/>
<input type="hidden" name="qydb" value="<%=qydb%>"/>
<input type="hidden" name="gbdb" value="<%=gbdb%>"/>
<input type="hidden" name="count" value="<%=count%>"/>
</form>
</body>
</html>
<script type="text/javascript">

document.form1.dbid.value='<%=form.getDbid()%>';
//document.form1.dllx.value='=form.getDllx()%>';
//document.form1.ydsb.value='=form.getYdsb()%>';
//document.form1.sszy.value='=form.getSszy()%>';
document.form1.dbyt.value='<%=form.getDbyt()%>';
document.form1.csds.value='<%=form.getCsds()%>';
document.form1.cssytime.value='<%=form.getCssytime()%>';
document.form1.beilv.value='<%=form.getBeilv()%>';
document.form1.dbxh.value='<%=form.getDbxh()%>';
document.form1.memo.value='<%=form.getMemo()%>';
//document.form1.netpoint_name.value='=form.getNetpoint_name()%>';
//document.form1.netpoint_id.value='=form.getNetpoint_id()%>';
//document.form1.yhdl.value='<%=form.getYhdl()%>';
document.form1.dfzflx.value='<%=form.getDfzflx()%>';
document.form1.dbname.value='<%=form.getDbname()%>';
document.form1.linelosstype.value='<%=form.getLinelosstype()%>';
document.form1.linelossvalue.value='<%=form.getLinelossvalue()%>';
document.form1.dbqyzt.value='<%=form.getDbqyzt()%>';

</script>


