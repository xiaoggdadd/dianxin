<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@page import="com.noki.electricfees.javabean.PrepaymentFormBean"%>
<%
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
        String roleId = account.getRoleId();
%>
<html>
<head>
<title>
</title>
<style>
.style1 {
	color: red;
	font-weight: bold;
}

.form{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
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
	        if(
                  checkNotnull(document.form1.amid,"电表ID")&&
                  checkNotSelected(document.form1.feetype,"费用类型")&&
                  checkNotnull(document.form1.money,"金额")&&
        		  checkNotnull(document.form1.startdegree,"起始电表读数")&&
        		  checkNotnull(document.form1.stopdegree,"终止电表读数")&&
        		  checkNotnull(document.form1.startdate,"起始时间")
          	){
                        if(confirm("您将要添加费用信息！确认信息正确！")){
                    document.form1.action=path+"/servlet/prepayment?action=add"
        			document.form1.submit()
                         }
            }
                      
        	

	}
        function showIdList(){

       
               window.open('PrepaymentBrowseAmmeter.jsp','','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')

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
              document.form1.action=path+"/web/electricfees/prepaymentList.jsp";
              document.form1.submit();
         }
          $(function(){
			
			$("#resetBtn").click(function(){
				$.each($("form input[type='text']"),function(){
					$(this).val("");
				})
			});
			$("#baocun").click(function(){
				saveDegree();
			});

			$("#cancelBtn").click(function(){
				fanhui();
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
	
<form action="" name="form1" method="POST">

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="4" width="50" >
					<div style="width:700px;height:50px">
						
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">添加预付费</span>	
					</div>
			   </td>
	</tr>
</table> 
	<table width="100%" border="0" cellspacing="1" cellpadding="1">

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>

		<tr bgcolor="F9F9F9">
            <td height="19" colspan="4">　<img src="../../images/v.gif" width="15" height="15" /><font size="2">基本信息</font></td>
        </tr>

      <tr class="form"> 
          <td height="19" bgcolor="#DDDDDD" width="15%"><div>电表ID：</div>
         </td> 
         <td><input type="text" name="amid" value=""  class="form" /><span class="style1">&nbsp;*</span>
	         <img id="showid" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png" style="cursor: pointer"/>
          </td> 

          
      </tr>
      <tr class="form">
      <td height="19" bgcolor="#DDDDDD"><div>起始电表数：</div>
         </td>
         <td><input type="text" name="startdegree" value=""  class="form" /><span class="style1">&nbsp;*</span>
         </td> 
      	 <td height="19" bgcolor="#DDDDDD"><div>终止电表数：</div>
         </td>
         <td><input type="text" name="stopdegree" value=""  class="form" /><span class="style1">&nbsp;*</span>
         </td> 
      
      
      </tr>

       <tr class="form"> 
          <td height="19" bgcolor="#DDDDDD" width="15%"><div>费用类型：</div>
         </td>  
         <td>   
         <select name="feetype" class="form">
         		<option value="0">请选择</option>
	         <%
	         	ArrayList feetypelist = new ArrayList();
	         	feetypelist = commBean.getFeeType();
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
	         </select><span class="style1">&nbsp;*</span>
          </td>  
           <td height="19" bgcolor="#DDDDDD"><div>金额：</div>
         </td>
         <td><input type="text" name="money" value=""  class="form" /><span class="style1">&nbsp;*</span>
         </td>
      </tr>
          <tr class="form">    
         <td height="19" bgcolor="#DDDDDD"><div>起始时间：</div>
         </td>
         <td><input type="text" name="startdate" value="" onFocus="getDateString(this,oCalendarChs)" onpropertychange="changePreenddata()" class="form" /><span class="style1">&nbsp;*</span>
           </td>
            <td height="19" bgcolor="#DDDDDD"><div>预计结束时间：</div>
         </td>
         <td><input type="text" name="preenddate" value="" onFocus="getDateString(this,oCalendarChs)" class="form" />
           </td>
      </tr>
      
       <tr class="form">
      <td height="19" bgcolor="#DDDDDD"><div>票据类型：</div>
         </td>
          <td>   
         <select name="notetypeid" class="form">
         		<option value="0">请选择</option>
	         <%
	         	ArrayList feetypelist1 = new ArrayList();
	         	feetypelist1 = commBean.getNoteType();
	         	if(feetypelist1!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)feetypelist1.get(0)).intValue();
	         		for(int i=scount;i<feetypelist1.size()-1;i+=scount)
                    {
                    	sfid=(String)feetypelist1.get(i+feetypelist1.indexOf("CODE"));
                    	sfnm=(String)feetypelist1.get(i+feetypelist1.indexOf("NAME"));
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
      <tr class="form">
         <td height="19" bgcolor="#DDDDDD"><div>票据时间：</div>
         </td>
         <td><input type="text" name="notetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" maxlength="11"/>

         </td>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div>开票时间：</div>
         </td>
         <td><input type="text" name="kptime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" />
         </td>
         
      </tr>          
     
      <tr class="form">
        <td height="19" bgcolor="#DDDDDD"><div>交费操作员：</div>
         </td>
         <td><input type="text" name="payoperator" value=""  class="form" />
         </td>
         <td height="19" bgcolor="#DDDDDD"><div>交费时间：</div>
         </td>
         <td><input type="text" name="paydatetime" value="" onFocus="getDateString(this,oCalendarChs)" class="form" />
           </td>
      </tr>
       <tr class="form" >    
         <td height="19" bgcolor="#DDDDDD"><div>报账月份：</div>
         </td>
         <td><input type="text" name="accountmonth" value="" onFocus="getDatenyString(this,oCalendarChsny)" class="form" />
         </td> 
         <td height="19" bgcolor="#DDDDDD"><div>备注：</div>
         </td>
         <td><input type="text" name="memo" value=""  class="form" />
         </td>       
      </tr>
      
      <tr class="form">

         <td colspan="4" align="right">

         <div id="cancelBtn" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 255px">
				<img src="<%=path%>/images/quxiao.png" width="100%"
					height="100%">
				<span
					style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
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
<!--
var XMLHttpReq;
	//XMLHttpReq = createXMLHttpRequest();
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	
	///////////////////////////////////////////////////////////
	function sendRequest(url,para) {
		createXMLHttpRequest();	
		XMLHttpReq.open("POST", url, true);	
		if(para == 'amid'){			
           XMLHttpReq.onreadystatechange = processResponse;//指定响应函数		
        }else{
           XMLHttpReq.onreadystatechange = processResponse_preenddata;//指定响应函数
        }
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
	}
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseText;  	
            	 //alert(res);
            	 document.form1.startdegree.value=res;
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    function processResponse_preenddata() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseText;  	
            	 //alert(res);
            	 document.form1.preenddate.value=res;
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function changeAmid(){
	var amid = document.form1.amid.value;
	//document.form1.sheng2.value=document.form1.sheng.value;
	//alert(amid);
	if(amid=="0"){
	    alert("请选择电表ID！");
		return;
	}else{
		sendRequest(path+"/servlet/prepayment?action=startdegree&amid="+amid,"amid");

	}
}
function changePreenddata(){
	var startdate = document.form1.startdate.value;
	var amid = document.form1.amid.value;
	var money = document.form1.money.value;
	//alert(startdate);
	if(startdate=="0"){
	    alert("请选择初始时间！");
		return;
	}else{
		sendRequest(path+"/servlet/prepayment?action=preenddata&startdate="+startdate+"&amid="+amid+"&money="+money,"preenddata");

	}
}
//-->
</script>
