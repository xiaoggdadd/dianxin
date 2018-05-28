<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.jizhan.JiZhanBean" %>
<%
	String path = request.getContextPath();
    Account account=(Account)session.getAttribute("account");
    String accountname = account.getAccountName();
	String zdid="";
	try{zdid=java.net.URLDecoder.decode(request.getParameter("zdid"),"utf-8");}catch(Exception e){System.out.println("传输的是空值 ");}
   	String jzname= "";
   	try{jzname=java.net.URLDecoder.decode(request.getParameter("jzname"),"utf-8");}catch(Exception e){System.out.println("传输的是空值 ");}
   	String szdq= "";
   	try{szdq=java.net.URLDecoder.decode(request.getParameter("szdq"),"utf-8");}catch(Exception e){System.out.println("传输的是空值 ");}
   	String jztype="";
   		try{jztype=java.net.URLDecoder.decode(request.getParameter("jztype"),"utf-8");}catch(Exception e){System.out.println("传输的是空值 ");}
   	String sfbg=  "";
   		try{sfbg=java.net.URLDecoder.decode(request.getParameter("sfbg"),"utf-8");}catch(Exception e){System.out.println("传输的是空值 ");}
   	String bieming= "";
   		try{bieming=java.net.URLDecoder.decode(request.getParameter("bieming"),"utf-8");}catch(Exception e){System.out.println("传输的是空值 ");}
   	String tshi= "";
   		try{tshi=java.net.URLDecoder.decode(request.getParameter("tshi"),"utf-8");}catch(Exception e){System.out.println("传输的是空值 ");}
   	String countbzw =  "";
   		try{countbzw=java.net.URLDecoder.decode(request.getParameter("countbzw"),"utf-8");}catch(Exception e){System.out.println("传输的是空值 ");}
   	
   	JiZhanBean bean = new JiZhanBean();
   	String thistime = "";
   	int cssjbzw =0;
	thistime = bean.getThisTime(zdid);
	if(thistime=="0"||thistime.equals("0")||thistime.equals("null")||thistime.equals(" ")){
		thistime = "";
	}
	if("0".equals(countbzw) && !"".equals(thistime)){ 
   	 	cssjbzw=1;
   	}
%>
<html>
<head>
<title>
addAccount
</title>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
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
	function saveAccount(){
		var zzsl = parseFloat(document.form1.zzsl.value);//增值税率
		var csds = parseFloat(document.form1.csds.value);
		var danjia = parseFloat(document.form1.danjia.value);
		var beilv = parseFloat(document.form1.beilv.value);
		var linelossvalue = parseFloat(document.form1.linelossvalue.value);
		var bsdl = parseFloat(document.form1.bsdl.value);
		var str = "2014-01-01";
		var countbzw = document.form1.countbzw.value;//"0"---1块电表;"1"---多块电表
		var dbyt = document.form1.dbyt.value;
		var cssjbzw1 =<%=cssjbzw%>;
		
          	if( checkNotnull(document.form1.zdid,"站点信息")&&
          	    checkNotSelected(document.form1.dfzflx,"电费支付类型")&&checkNotnull(document.form1.cssytime,"初始使用时间")&&
          	    checkNotnull(document.form1.csds,"初始读数")&&checkNotnull(document.form1.danjia,"电表单价")&&
          	    checkNotnull(document.form1.bsdl,"变损电量")&&checkNotnull(document.form1.zzsl,"增值税率")){
          		if(isNaN(document.form1.danjia.value)==false){
          		if(isNaN(document.form1.csds.value)==false){
          			//if(isNaN(document.form1.yhdl.value)==false){
          				if(isNaN(document.form1.beilv.value)==false){
          					if(isNaN(document.form1.linelossvalue.value)==false){
	          					if(isNaN(document.form1.bsdl.value)==false){
	          						if(isNaN(document.form1.zzsl.value)==false){
	          							if(csds>=0){
	          								if(danjia>=0){
	          									if(beilv>=0){
	          										if(linelossvalue>=0){
	          											if(bsdl>=0){
							          							if(0<zzsl&&zzsl<=1){
													   				  var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd
													                  var cyt = document.form1.cssytime.value;			                      
													                  if(cyt==""||cyt==null||reg.exec(cyt)){
													                		if(cssjbzw1!=1){
													                		 	if(cyt<str){
													                			 	alert("电表初始使用时间要大于等于2014-01-01");
													                		     	return;
													                		 	}
													                	 	}
													                	  //if(cyt>=str){
														                  		if(document.form1.dbqyzt.value==""||document.form1.dbqyzt.value==null){
															                    	 alert("电表启用状态不能为空！");
															                      }else{
															                    	  if(countbzw == "0" && dbyt == "dbyt01"){
															                    			if(confirm("您将要添加电表信息！确认信息正确！")){
																                    			document.form1.action=path+"/servlet/dianbiao?action=addZhanDian"
																								document.form1.submit()
																								showdiv("请稍等..............");
															                    			}
															                    	  }else{
																	                        if(confirm("您将要添加电表信息！确认信息正确！")){
																								document.form1.action=path+"/servlet/dianbiao?action=addZhanDian"
																								document.form1.submit()
																								showdiv("请稍等..............");
																	                        }
															                    	  }
															   		 			}
													                  		//}else{
													                  			// alert("电表初始使用时间要大于等于2014-01-01");
													                  		//}
															   		 	}else{
																			  	alert("您输入的初始使用时间有误，请确认后重新输入！");
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
	          									}else{
	          										alert("单价大于等于0！");
	          									}
			          					  }else{
			          							alert("初始读数大于等于0！");	
			          				      }
					                  }else{
					                	  alert("您输入的信息有误，增值税率必须为数字！");
					                  }
			                  	}else{
									alert("您输入的信息有误，变损电量必须为数字！");
								}
          					}else{
          						alert("您输入的信息有误，线损值必须为数字！");
          					}
						}else{
							alert("您输入的信息有误，倍率必须为数字！");
						}
					}else{
						alert("您输入的信息有误，月耗电量必须为数字！");
					}	
   		 	    }else{
		           alert("您输入的信息有误，初始读数必须为数字！");
                }
          		}else{
		           alert("您输入的信息有误，电表单价必须为数字！");
                }
             }
		  
       

        function vName(){
         	var accName = document.form1.dbid.value;
                 if(accName==""){
           	alert("电表ID不能为空！")
                   return
          }
               window.open('validateDbid.jsp?dbid='+accName+'&id=0','','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
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
        function fanhui(){        
              document.form1.action=path+"/web/jizhan/dianbiaolist.jsp";
              document.form1.submit();
        }
        
$(function(){
	$("#liulan").click(function(){
	alert(123);
		shoulist();
	});
	$("#cancelBtn").click(function(){
	    fanhui();
	});
$("#resetBtn").click(function(){
	$.each($("form input[type='text']"),function(){
	  $(this).val("");
          })
	});
$("#saveBtn").click(function(){
	saveAccount()
	
	});
$("#vnameBtn").click(function(){
	vName()
	});
});

</script>
</head>

<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">	
<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
		<tr>				
			<td>
			<div style="width:700px;height:50px">
			
				       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电表管理</span>	
				</div>
		    </td>
		   </tr>
		</table>
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">

			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="form_label">
						<tr>
							<td></td>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" class="form_label">

			<tr height="23px"><td>&nbsp;</td></tr> 
			<tr bgcolor="F9F9F9" class="selected_font">
                      <td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />站点信息</td>
            </tr>
            <tr><td>&nbsp;</td></tr> 
          <tr class="selected_font">
         <td bgcolor="#DDDDDD" width="15%"><div align="left">站点：</div>
         </td>
         <td width="25%"><input type="text" name="t_jzname"  class="selected_font1" readonly/><span class="style1">&nbsp;*</span>
         	<img id="liulan" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png" style="cursor: pointer;width:15px; height:15px "/>
         </td>
         <td bgcolor="#DDDDDD" width="15%"><div align="left">地区：</div>
         </td>

         <td width="25%"><input type="text" name="t_jzlx" value="" class="selected_font1" readonly/></td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点代码：</div>
         </td>
       
         <td width="25%"><input type="text" name="t_bieming" value="" class="selected_font1" readonly/>
         <input type="hidden" name="t_shi" value=""  class="form" />
         <input type="hidden" name="countbzw" value="<%=countbzw%>"  class="form" />
         </td>
      </tr>                    
                     <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />电表信息</td>
                    </tr>                
     <tr>
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电表名称：</div>
         </td>
         <td width="25%">
        <input type="text" name="dbname" value="" class="selected_font"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电表用途：</div>
         </td>
         <td width="25%">
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
         	</td>
         	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电费支付类型：</div>
         </td>
         <td width="25%">
         	<select name="dfzflx" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList dfzflx = new ArrayList();
	         	dfzflx = ztcommon.getSelctOptions("DFZFLX");
	         	if(dfzflx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)dfzflx.get(0)).intValue();
	         		for(int i=cscount;i<dfzflx.size()-1;i+=cscount)
                    {
                    	code=(String)dfzflx.get(i+dfzflx.indexOf("CODE"));
                    	name=(String)dfzflx.get(i+dfzflx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	
         	</select>
         	<span class="style1">&nbsp;*</span>
         	</td>	
<!--          	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电流类型：</div> -->
<!--          </td> -->
<!--          <td width="25%"> -->
<!--          	<select name="dllx" class="selected_font"> -->
<!--          		<option value="直流">直流</option> -->
<!--          		<option value="交流">交流</option> -->
         	
<!--          	</select> -->
         	
<!--           	</td>  -->
<!--          	  <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">所属专业：</div> -->
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
<!-- 	         	} 
	         %> -->
         	
<!--          	</select> -->
<!--          	</td> -->
      </tr>
      
      <tr>
         
<!--          <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">用电设备：</div> -->
<!--          </td> -->
<!--          <td width="25%"> -->
<!--          	<select name="ydsb" class="selected_font"> -->
<!--          		<option value="0">请选择</option> -->
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
			
<!--           <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">月耗电量：</div> -->
<!--          </td> -->
<!--          <td width="25%"> -->
<!--         <input type="text" name="yhdl" value="" class="selected_font3"/> -->
<!--          </td> -->
      </tr>
      
      <tr>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">初始使用时间：</div>
         </td>
         <td width="25%">
         <%if("0".equals(countbzw) && !"".equals(thistime)){%>
         	<input class="selected_font" type="text" name="cssytime"
					value="<%=thistime%>"
					readonly="readonly" class="Wdate" style="background-color:#FFFFFF; color:g                                                                                                                                                                                                                                                                                                               rey"/>
			<span class="style1">&nbsp;*</span>
         <%}else{ %>
        	<input class="selected_font" type="text" name="cssytime"
					value="<%if (null != request.getParameter("cssytime"))	out.print(request.getParameter("cssytime"));%>"
					readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" />          
			<span class="style1">&nbsp;*</span>
         <%}%>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">倍率：</div>
         </td>
         <td width="25%"><input type="text" name="beilv" value="1" class="selected_font3"/></td>
	     <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">线损类型：</div>
         </td>
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
      </tr>
     
      <tr>
      	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电表型号：</div>
         </td>
         <td width="25%"><input type="text" name="dbxh" value=""  class="selected_font"/></td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">备注：</div>
         </td>
         <td width="25%">
        <input type="text" name="memo" value="" class="selected_font"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">线损值：</div>
         </td>
         <td width="25%"><input type="text" name="linelossvalue" value="" class="selected_font3"/></td>	
      </tr>
      <tr>
<!--       	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">MYSQL局站名称：</div> -->
<!--          </td> -->
<!--          <td width="25%"><input type="text" name="netpoint_name" value="" class="selected_font"/> -->
<!--          </td> -->
<!--          <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">MYSQL局站编号：</div> -->
<!--          </td> -->
<!--          <td width="25%"> -->
<!--         <input type="text" name="netpoint_id" value="" class="selected_font"/> -->
<!--         <input type="hidden" name="accountname" value="=accountname %>"/> -->
<!--          </td> -->
         
      </tr>
    <tr>
     	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电表单价：</div>
         </td>
         <td width="25%"><input type="text" name="danjia" value="" class="selected_font"/>
         <span class="style1">&nbsp;*</span>
         </td>
<!--          <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">自报电用户号：</div> -->
<!--          </td> -->
<!--          <td width="25%"><input type="text" name="zbdyhh" value="" class="selected_font"/> -->
<!--          </td> -->
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">原电表ID：</div>
         </td>
         <td width="25%"><input type="text" name="ydbid" value="" class="selected_font"/>
         </td>
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">初始读数：</div>
         </td>
         <td width="26%">
         		<input type="text" name="csds" value="" class="selected_font3"/><span class="style1">&nbsp;*</span>
         </td>
    </tr>
    
    <tr>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">变损电量：</div>
         </td>
         <td width="25%"><input type="text" name="bsdl" value="" class="selected_font"/>
     	 </td>
<!--      	  <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">暂估单价：</div> -->
<!--          </td> -->
<!--          <td width="25%"><input type="text" name="zgdj" value="" class="selected_font" readonly="readonly"/> -->
<!--      	 </td> -->
     	  <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">增值税率：</div>
         </td>
         <td width="25%"><input type="text" name="zzsl" value="" class="selected_font"/>
     	 </td>
     	 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电表启用状态：</div>
         </td>
         <td width="25%">
         	<select name="dbqyzt" class="selected_font">
         		<option value="1">是</option>
         		<option value="0">否</option>
         	</select>        	
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
			<span style="font-size:12px;position: absolute;left:28px;top:5px;color:white">重置</span>
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
      <br />
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
</table>
<input type="hidden" name="zdid" value=""/>
</form>
</body>
<script type="text/javascript">
    function shoulist(){
       //window.open("dianbiaolist.jsp",'_blank','newwindow','height=350, width=630,top=1200,left=300,toolbar=no,menubar=no, scrollbars=yes, resizable=no,location=n o, status=no');
      window.open('zhandianselect.jsp','','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200');
  
    }
	function liulan(){
// 		window.open("zhandianselect.jsp");
// 		return;
		//alert(123);
		var url=path+"/web/jizhan/zhandianselect.jsp"
// 		window.open(url);
// 		return;
			var obj = new Object();
			obj.mid='mid';
		    var obj=showModalDialog(url,obj,'dialogWidth:1000px;dialogHeight:650px;status:no;scroll:yes');	
		   
			document.form1.zdid.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_jzname.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
// 			document.form1.t_area.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_jzlx.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
// 			document.form1.t_bgsign.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_bieming.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_shi.value=obj.substring(0,obj);
			
	}
	document.form1.zdid.value='<%=zdid%>';
	document.form1.t_jzname.value='<%=jzname%>';
// 	document.form1.t_area.value='=szdq%>';
	document.form1.t_jzlx.value='<%=jztype%>';
// 	document.form1.t_bgsign.value='=sfbg%>';
	document.form1.t_bieming.value='<%=bieming%>';
	document.form1.t_shi.value='<%=tshi%>';
</script>
</html>


