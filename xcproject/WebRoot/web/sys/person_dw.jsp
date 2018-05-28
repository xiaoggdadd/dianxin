<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%
	String accountId = request.getParameter("account")!=null?request.getParameter("account"):"0";
        String bm = request.getParameter("bm")!=null?request.getParameter("bm"):"0";
        String path = request.getContextPath();

Account account = (Account)session.getAttribute("account");
String role_id = account.getRoleId();
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
 </style>
 <script type="text/javascript">
 	var path = '<%=path%>';
 </script>
<script language="javascript">

	function bmChange(){
          var account = document.form1.account.value;
                if(account=="0"){
                 	alert("请选择人员！");
                        return;
                }
                 document.form1.action=path+"/web/sys/person_dw.jsp";
                 document.form1.submit()
	}

        function saveAccount(){
          	var account = document.form1.account.value;
                if(account=="0"){
                 	alert("请选择人员！");
                        return;
                }
                var bm = document.form1.bm.value;
                if(bm=="0"){
                 	alert("请选择部门！");
                        return;
                }
          	document.form1.action=path+"/servlet/pd";
                 document.form1.submit()
        }

        function accountChange(){
         	document.form1.bm.value="0";
                 var obj_wap = document.getElementById("pdw")
           obj_wap.style.display="none"
        }

        function checkPage(){
         	if(document.form1.pageCheck.checked){
         		for(var j = 0;j < document.form1.elements.length ; j++){
							document.form1.elements[j].checked=true
						}
         	}else{
         		for(var j = 0;j < document.form1.elements.length ; j++){
							document.form1.elements[j].checked=false
						}
         	}
        }


</script>
</head>
<jsp:useBean id="bean" scope="page" class="com.noki.mobi.sys.javabean.Person_DWBean">
</jsp:useBean>

<body  class="body" style="overflow-x:hidden;">
	<LINK href="../../images/css.css" type="text/css" rel="stylesheet">
<form action="" name="form1" method="POST">
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
  	<tr>
    <td width="10"><img src="../../images/img_04.gif" width="12" height="37" /></td>
    <td valign="top" background="../../images/img_05.gif"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="2"></td>
      </tr>
      <%--
      <tr>
        <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="58%">&nbsp;</td>
            <td width="6%" bgcolor="#FFFFFF"><img src="../../images/b_13.gif" width="85" height="26" /></td>
            <td width="31%" background="../../images/b_14.gif"><span class="STYLE6"><!--<a href="<%=path%>/web/sys/rightRW.jsp">读写设置｜</a>--></span></td>
            <td width="5%"><img src="../../images/b_15.gif" width="40" height="26" /></td>
          </tr>
        </table></td>
      </tr>
      --%>
    </table></td>
    <td width="12"><img src="../../images/img_06.gif" width="12" height="37" /></td>
  </tr>

	<tr>
    <td width="10" height="500" background="../../images/img_10.gif">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td width="6" background="../../images/a_22.gif"><img src="../../images/a_21.gif" width="44" height="32" /></td>
							<td width="169" background="../../images/a_22.gif"><span class="style1"> 人员单位关系</span></td>
							<td width="185"><img src="../../images/a_23.gif" width="185" height="32" /></td>
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
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" bgcolor="7C92B7">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="80%" border="0" cellspacing="1" cellpadding="1" align="center">

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
			 <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4">　</td>
                    </tr>
        <tr>

 <%
      	ArrayList list = new ArrayList();
        list = bean.getAccounts();
        int countColum = ((Integer)list.get(0)).intValue();
        String accountid= "",name = "";
      %>
         <td height="19" bgcolor="C8E1FB"><div align="center">人　员</div>
         </td>
         <td>
            <select name="account"  style="width:145"  class="form"  onchange="accountChange()">

               			<option value="0">选择人员</option>
                                                   <%
               		for(int i = countColum;i<list.size()-1;i+=countColum){
                                  accountid = (String)list.get(i+list.indexOf("ACCOUNTID"));

                                  name = (String)list.get(i+list.indexOf("NAME"));
               %>
               			<option value="<%=accountid%>" <%if(accountid.equals(accountId)){%> selected="selected" <%}%>><%=name%></option>
               <%
               		}
               %>

            </select>
         </td>
         <%
      	ArrayList listbm = new ArrayList();
        listbm = bean.getBM();
        int countbm = ((Integer)listbm.get(0)).intValue();
        String bmdm= "",bmmc = "";
      %>
         <td height="19" bgcolor="C8E1FB"><div align="center">部　门</div>
         </td>
         <td>
            <select name="bm"  style="width:145"  class="form"  onchange="bmChange()">

               			<option value="0">选择部门</option>
                                                   <%
               		for(int i = countbm;i<listbm.size()-1;i+=countbm){
                                  bmdm = (String)listbm.get(i+listbm.indexOf("BMDM"));

                                  bmmc = (String)listbm.get(i+listbm.indexOf("BMMC"));
               %>
               			<option value="<%=bmdm%>" <%if(bmdm.equals(bm)){%> selected="selected" <%}%>><%=bmmc%></option>
               <%
               		}
               %>

            </select>
         </td>

      </tr>
    </table>
	<table width="80%" border="0" cellspacing="1" cellpadding="1" align="center">
		<tr>
                      <td bgcolor="FFFFFF"><div align="left"><input type="checkbox"  value="0"  name="pageCheck"  onClick="checkPage()" /></div>
                      </td>

                </tr>
       <%
           	ArrayList rightList = new ArrayList();
                rightList = bean.getDW(accountId,bm);
                if(!rightList.isEmpty()){
                int k = 1;
                int count = ((Integer)rightList.get(0)).intValue();
                String dwdm="",dwjc="",account_id="";
                String color = "F3F3F3";
                for(int i = count;i<rightList.size()-1;i+=count){
                   dwdm = (String)rightList.get(i+rightList.indexOf("DWDM"));
                   dwjc = (String)rightList.get(i+rightList.indexOf("DWJC"));
                   account_id = (String)rightList.get(i+rightList.indexOf("ACCOUNTID"));
                   account_id = account_id!=null?account_id:"";
                   if(k%2==0){
											color = "FFFFFF";
										}else{
											color = "#F2F9FF";
										}
								           %>
           	<tr>
                      <td bgcolor="<%=color%>"><div align="left"><input type="checkbox" <%if(account_id.equals(accountId)){%> checked="checked"<%}%> value="<%=dwdm%>"  name="itemSelected"/><%=dwjc%></div>
                      </td>

                </tr>
           <%k++;}}%>

      <tr>
        <td>
          <div align="center">
  <input type="button" name="baocun1" value="保存" onclick="saveAccount()"  id="id1" class="memo" style="color:#014F8A"/>
&nbsp;　&nbsp;
            <input type="reset" name="chongzhi0" value="重置" class="memo" id="id1"  style="color:#014F8A"/>
          </div></td>
      </tr>

      <%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
      </table>
      <table width="80%" border="0" cellspacing="1" cellpadding="1" align="center">
      	<tr bgcolor="F9F9F9">
                      <td height="19">该人员已经分配的单位--选择人员后，任意选择一个部门可以查看　</td>
        <tr>
      </table>
      <div id="pdw">

      <table width="80%" border="0" cellspacing="1" cellpadding="1" align="center">
         <%
           	ArrayList pdlist = new ArrayList();
                pdlist = bean.getPersonDW(accountId);
                if(!pdlist.isEmpty()){
                int pdk = 1;
                int pdcount = ((Integer)pdlist.get(0)).intValue();
                String pddwjc="",pdbm="",temp = "";
                String pdcolor = "F3F3F3";
                for(int i = pdcount;i<pdlist.size()-1;i+=pdcount){
                   pddwjc = (String)pdlist.get(i+pdlist.indexOf("DWJC"));
                   pdbm = (String)pdlist.get(i+pdlist.indexOf("BMMC"));
                   if(pdbm.equals(temp)){
                   	pdbm="";
                  }else{
                  	temp=pdbm;
                  	}
                   if(pdk%2==0){
											pdcolor = "FFFFFF";
										}else{
											pdcolor = "#F2F9FF";
										}
								           %>
           	<tr>
                     <td bgcolor="<%=pdcolor%>" width="30%"><div align="left"><%=pdbm%></div>
                      </td>
                      <td bgcolor="<%=pdcolor%>"><div align="left"><%=pddwjc%></div>
                      </td>

                </tr>
           <%pdk++;}}%>

        </table>
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
      <br />
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="4"></td>
          <td><table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="7C92B7">
              <tr>
                <td height="49" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr bgcolor="F9F9F9">
                      <td height="28" colspan="2" background="../../images/bg.jpg"><label> 　　<img src="../../images/ling.jpg" width="17" height="16" />　温馨提示</label></td>
                    </tr>
                    <tr>
                      <td height="51" colspan="2"><div align="center"></div>
                          <div align="center"> </div>
                          <div align="center"> </div>
                          <div align="center" class="style7"></div>
                          <div align="center" class="style7"> </div>
                          <div align="center"></div>
                          <div align="center">
                            <table width="85%"  border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td><div align="left"></div></td>
                              </tr>
                            </table>
                        </div></td>
                    </tr>
                </table></td>
              </tr>
          </table></td>
        </tr>
      </table></td>
    <td background="../../images/img_13.gif">&nbsp;</td>
  </tr>
  <tr>
    <td><img src="../../images/img_23.gif" width="12" height="19" /></td>
    <td background="../../images/img_24.gif">&nbsp;</td>
    <td><img src="../../images/img_25.gif" width="12" height="19" /></td>
  </tr>
</table>
</form>
</body>
</html>
<jsp:include flush="true" page="../common/rwdSub.jsp">
  <jsp:param name="pageUrl" value="person_dw"/>
  <jsp:param name="roleId" value="<%=role_id%>"/>
</jsp:include>
