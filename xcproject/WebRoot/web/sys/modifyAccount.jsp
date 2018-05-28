<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.noki.mobi.sys.javabean.AccountFormBean" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>

<%
	    String accountId = request.getParameter("accountId");
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String sheng = request.getParameter("sheng");
        String shi = request.getParameter("shi");
        String xian = request.getParameter("xian");
        String xiaoqu = request.getParameter("xiaoqu");

        String roleId = account.getRoleId();
        String loginName = (String)session.getAttribute("loginName");
        int sign=1;//标志位  查询角色类型
        if(roleId.equals("1")){
             sign=0;//如果roleid是1管理员权限  查询角色的标志位为0 表示查询全部角色
         }
%>

<html>
<head>
<title>
操作员明细与修改
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
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.style10 {
	font-size: 9pt;
	color: #ff9900;
	font-weight: bold;
}
 .style1 {
	color: #FF9900;
	font-weight: bold;
}

</style>

<script src="<%=path%>/javascript/tx.js"></script>

<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript">
	$(function(){
			
			$("#resetBtn").click(function(){
				$.each($("form input[type='text']"),function(){
					$(this).val("");
				})
			});
			$("#baocun").click(function(){
				baocunxiugai();
			});
			$("#cancelBtn").click(function(){
				guanbi();
			});
			
		});
</script>
</head>
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean">
</jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
	String ssagcode = ztcommon.getLastAgcode(loginName);
%>
<body  class="body" style="overflow-x:hidden;">
	
<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
  	

	<tr>
    
    <td valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">

      <tr>
       
        <td colspan=1 width="400" background="<%=path%>/images/btt2.bmp" height=50><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作员明细与修改</span></td>
							
							<td width="380">&nbsp;</td>
      </tr>
    </table>
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="4"></td>
          <td>
            <table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" >
              <tr>
                <td height="49" bgcolor="#FFFFFF"><form action="" name="form1" method="POST">
	<%
		AccountFormBean bean = new AccountFormBean();
		bean = bean.getAccountInfo(accountId);
		String roles = ","+bean.getRoleId()+",";
	%>
  <table width="100%">
      <tr>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div align="center">账号</div>
         </td>
         <td width="35%"><%=bean.getAccountName()%>

         </td>
         <td height="19" bgcolor="#DDDDDD" width="15%"><div align="center">姓名</div>
         </td>
         <td width="35	%"><input type="text" name="name" value="<%=bean.getName()%>"  class="form" style="width: 130px;"/><span class="style1">&nbsp;*</span>

         </td>
      </tr>
      <tr>
         
         <td height="19" bgcolor="#DDDDDD"><div align="center">市</div>
         </td>
         <td>
						<select name="shi" id="shi" style="width:130" onchange="changeShi()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList shilist = new ArrayList();
	         	shilist = ztcommon.getAgcode(ssagcode,5);
	         	if(shilist!=null){
	         		String agcode="",agname="";
	         		int scount = ((Integer)shilist.get(0)).intValue();
	         		for(int i=scount;i<shilist.size()-1;i+=scount)
                    {
                    	agcode=(String)shilist.get(i+shilist.indexOf("AGCODE"));
                    	agname=(String)shilist.get(i+shilist.indexOf("AGNAME"));
                    %>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%}
	         	}
	         %>
         		</select>
         </td>
      <tr>
      	<tr>
         <td height="19" bgcolor="#DDDDDD"><div align="center">区县</div>
         </td>
         <td><select name="xian" id="xian" style="width:130"  onchange="changeXian()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList xianlist = new ArrayList();
	         	shilist = ztcommon.getAgcode(ssagcode,7);
	         	if(shilist!=null){
	         		String agcode="",agname="";
	         		int scount = ((Integer)shilist.get(0)).intValue();
	         		for(int i=scount;i<shilist.size()-1;i+=scount)
                    {
                    	agcode=(String)shilist.get(i+shilist.indexOf("AGCODE"));
                    	agname=(String)shilist.get(i+shilist.indexOf("AGNAME"));
                    %>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%}
	         	}
	         %>
         	</select>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div align="center">乡镇</div>
         </td>
         <td><select name="xiaoqu" id="xiaoqu" style="width:130">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList xiaoqulist = new ArrayList();
	         	xiaoqulist = ztcommon.getAgcode(ssagcode,9);
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
         	</select>
         </td>
         
      </tr>

      <tr>
         <td height="19" bgcolor="#DDDDDD"><div align="center">座机</div>
         </td>
         <td><input type="text" name="tel" value="<%=bean.getTel()%>"  class="form" style="width: 130px;"/>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div align="center">手机</div>
         </td>
         <td><input type="text" name="mobile" value="<%=bean.getMobile()%>"  maxlength="11" class="form" style="width: 130px;"/>

         </td>
      </tr>
	<%
      	ArrayList list = new ArrayList();
        list = roleBean.getAllRole(sign);
        int countColum = ((Integer)list.get(0)).intValue();
        String roleName2 = "",srole="";
        String roleId2 = "";
      %>
    <tr>
         <td height="19" bgcolor="#DDDDDD"><div align="center">角色</div>
         </td>
         <td>
            <select name="role"  style="width:130" class="form" multiple="multiple">

                                                   <%
               		for(int i = countColum;i<list.size()-1;i+=countColum){
                                  roleId2 = (String)list.get(i+list.indexOf("ROLEID"));
                                  roleName2 = (String)list.get(i+list.indexOf("NAME"));
                                  srole = ","+roleId2+",";
               %>
               			<option value="<%=roleId2%>" <%if(roles.indexOf(srole)>-1){%>selected="selected"<%}%>><%=roleName2%></option>
               <%
               		}
               %>

            </select><span class="style1">&nbsp;*</span>
         </td>
         <td height="19" bgcolor="#DDDDDD"><div align="center">性别</div>
         </td>
         <td>
            <select name="sex"  style="width:130" class="form" >
               <option value="1">男</option>
               <option value="0">女</option>
            </select>
         </td>
          <script>
         		
                        document.form1.sex.value=<%=bean.getSex()%>
         </script>
      </tr>

      <tr>

      </tr>
      <tr>
         <td height="19" bgcolor="#DDDDDD"><div align="center">部门</div>
         </td>

         <td>
           <input type="text" name="bumen" value="<%=bean.getBuMen()%>"  maxlength="19" class="form" style="width: 130px;"/>
           </td>
         </td>
	 <td height="19" bgcolor="#DDDDDD"><div align="center">邮箱</div>
         </td>
         <td><input type="text" name="email" value="<%=bean.getEmail()%>"  class="form" style="width: 130px;"/>
           </td>
      </tr>
      <tr>

      </tr>

     
   <tr>
         <td height="19" bgcolor="#DDDDDD"><div align="center">职位</div>
         </td>
         <td>
	        <select name="position" class="form" > 
                 <option value="0">请选择</option>
            	<%
	         		ArrayList stationtype = new ArrayList();
         			stationtype = ztcommon.getSelctOptions("zw");
	         		if(stationtype!=null){
	         		String code="",name1="";
	         		int cscount = ((Integer)stationtype.get(0)).intValue();
	         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
                   	{
                    code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
                    name1=(String)stationtype.get(i+stationtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name1%></option>
                   	<%}
	         		}
	         		%>
                   </select>
	            </td>
         <td height="19" bgcolor="#DDDDDD"><div align="center">邮编</div>
         </td>
         <td><input type="text" name="zip" value="<%=bean.getZip()%>"  class="form" style="width: 130px;"/>
         </td>
      </tr>
         <tr>
         <td height="19" bgcolor="#DDDDDD"><div align="center">地址</div>
         </td>
         <td colspan="3"><input size="60" type="text" name="address" value="<%=bean.getAddress()%>"  class="form" style="width: 290px;"/>
         </td>
       </tr>
      
      <tr>
          <td height="19" bgcolor="#DDDDDD"><div align="center">备注</div>
         </td>
         <td colspan="3"><input size="60" type="text" name="memo" value="<%=bean.getMemo()%>"  class="form" style="width: 290px;"/>
         </td>
      </tr>



    </table>
    <table width="100%">
      <tr>
      	<td >
      	<div id="cancelBtn"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 14px">
							<img src="<%=path%>/images/quxiao.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">返回</span>
						</div>

						<div id="resetBtn"
							style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 16px">
							<img src="<%=path%>/images/2chongzhi.png" width="100%"
								height="100%">
							<span
								style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">重置</span>
						</div>
						<div id="baocun"
							style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right: 20px">
							<img alt=""
								src="<%=request.getContextPath()%>/images/baocun.png"
								width="100%" height="100%" />
							<span
								style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">保存</span>
						</div>
      </td>
      </tr>
      </table>
<input type="hidden" name = "accountId" value="<%=accountId%>"/>
<input type="hidden" name="sheng" value="<%=sheng%>"/>
</form></td>
              </tr>
          </table></td>
        </tr>
      </table>
</body>
</html>
<SCRIPT  language=javascript>
var path = '<%=path%>';

	function guanbi(){
		//alert("dkjdsl");
           window.close();
	}
	function baocunxiugai(){
               var name = document.form1.name.value;
               if(name==""){
               	alert("姓名不能为空！");
               	document.form1.name.focus();
               	return;	
              }
              var role = document.form1.role.value;
               if(role=="0"){
               	alert("请选择角色！");
               	document.form1.role.focus();
               	return;	
              }
							/*
                 if(document.form1.xian.value=="0"){
                         	alert("请选择区县！");
                                 return
                     	}
                     	if(document.form1.shi.value=="0"){
                         	alert("请选择市！");
                                 return
                     	}
                     	if(document.form1.xiaoqu.value=="0"){
                         	alert("请选择乡镇！");
                                 return
                     	}
							*/
              if(confirm("您将要更新此账户的信息！")){
               	document.form1.action=path+"/servlet/account?action=xiugai";
								document.form1.submit();
               }

        
	}
function changeSheng(){
			var sheng = document.form1.sheng.value;
			if(sheng=="0"){
				var shilist = document.all.shi;
				shilist.options.length="0";
				shilist.add(new Option("请选项","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=sheng&pid="+sheng,"sheng");
			}
		}
		function updateShi(res){
			var shilist = document.all.shi;
			shilist.options.length="0";
			shilist.add(new Option("请选项","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		function changeShi(){
			var shi = document.form1.shi.value;
			var ssagcode='<%=ssagcode%>';
			var xiaoqu = document.all.xiaoqu;
				xiaoqu.options.length="0";
				xiaoqu.add(new Option("请选项","0"));
			if(shi=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选项","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=shitoxian&pid="+shi+"&ssagcode="+ssagcode,"shitoxian");
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
			var ssagcode='<%=ssagcode%>';
			if(shi=="0"){
				var shilist = document.all.xiaoqu;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=xiantoxiaoqu&pid="+shi+"&ssagcode="+ssagcode,"xiantoxiaoqu");
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
document.form1.position.value='<%=bean.getPosition()%>';
if(document.form1.position.value==""||document.form1.position.value==null){
 document.form1.position.value="0";
 }
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
</script>


