﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.noki.mobi.common.Account"%>
<%@ page session="true"%>
<%
	String path = request.getContextPath();

        //System.out.println("accountList.jsp>>"+orgId);
         Account account = (Account)session.getAttribute("account");
         String roleId = account.getRoleId();
%>

<html>
<head>
<title>
accountList
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
function saccount(){////

  var sname=document.form1.sname.value
           var srole = document.form1.srole.value
           var sorg = document.form1.sorg.value
           //alert(sorg);
         	document.form1.action=path+"/web/sys/accountList.jsp?doQuery=1&san=1"
		document.form1.submit();
	}
	function editacc(id){
         	window.open('modifyAccount.jsp?accountId='+id,'infoAccount','width=750,height=400,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
	}
        function delacc(){
		
		
			if(confirm("确定要删除全部数据么！")){
          document.form1.action=path+"/servlet/deldata";
          document.form1.submit();
      }else{
              return;
      }
		
	}
        
      
        function resetPass(){
           var i = 0;

		for(var j = 0;j < document.form1.elements.length ; j++){
			if (document.form1.elements[j].checked){
				i++;
			}
		}
		//alert(i)
		if(i>0){
			if(confirm("确定要重置这些账户的密码么？重置后系统将会自动用短信通知对方！")){
                                document.form1.action=path+"/servlet/account?action=resetPass";
                                document.form1.submit();
                        }else{
                                return;
                        }
		}else{
			alert("请选择要重置密码的账户");
			return;
		}
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
        function addacc(){
          document.form1.action=path+"/web/sys/addAccount.jsp";
                                document.form1.submit();
        }
function exportAccount(){
	document.form1.action=path+"/web/up/input.jsp";
                                document.form1.submit();
}
</script>
<jsp:useBean id="bean" scope="page" class="com.noki.mobi.up.InData">
</jsp:useBean>

</head>
<body  class="body" >
	<LINK href="../../images/css.css" type="text/css" rel=stylesheet>
	<form action="accountList.jsp" name="form1" method="POST">

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="10"><img src="../../images/img_04.gif" width="12" height="37" /></td>
    <td valign="top" background="../../images/img_05.gif"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="2"></td>
      </tr>
      
    </table></td>
    <td width="12"><img src="../../images/img_06.gif" width="12" height="37" /></td>
  </tr>
  <tr>
    <td width="10" height="507" background="../../images/img_10.gif">&nbsp;</td>
    <td valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">


    </table>
      <table width="99%" border="0" align="center" cellpadding="1" cellspacing="1" >

        <tr>
          <td height="49" bgcolor="#FFFFFF">
<table width="100%" border="0" cellspacing="1" cellpadding="1"  bgcolor="#cbd5de">
            <tr bgcolor="F9F9F9">
              <td height="22" colspan="9"><label> 　　<img src="../../images/v.gif" width="8" height="9" />导入演示</label></td>
            </tr>
            <tr>
              <td height="26" colspan="9"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr bgcolor="#FFFFFF">
                 <td align="right">
	
          <input type="button" name="del1" value="删除" onclick="delacc()" class="memo" id="id1"  style="color:#014F8A"/>
        
        &nbsp;&nbsp;
          <input type="button" name="matchroles0" value="导入数据" onclick="exportAccount()" class="memo" id="id1" style="color:#014F8A"/>
         
      </td>
                </tr>
              </table></td>
            </tr>


            <tr>
             <td width="5%" height="23" bgcolor="C8E1FB"><div align="center">序号</div>
                                </td>
                                <td width="10%" height="23" bgcolor="C8E1FB"><div align="center">名称</div>
            			</td>
                                <td width="10%" height="23" bgcolor="C8E1FB"><div align="center">数量</div>
            			</td>

                                    
                

				<td width="5%" height="23" bgcolor="C8E1FB"><div align="center"><input type="checkbox" name="pageCheck" onClick="checkPage()" /></div>
             			 </td>
            </tr>
              <%
   	ArrayList list = new ArrayList();
        list = bean.getAllAccount();
        int intnum = 1;
        int countColum = ((Integer)list.get(0)).intValue();
        String accountId ="",accountName = "",pcount = "",mobile = "",roleName = "",sign = "",tel = "";
        String color = "F3F3F3";
        for(int i = countColum;i<list.size()-1;i+=countColum){
           accountId = (String)list.get(i+list.indexOf("ID"));
           accountName = (String)list.get(i+list.indexOf("PNAME"));
           pcount = (String)list.get(i+list.indexOf("PCOUNT"));
          
           if(intnum%2==0){
							color = "#F2F9FF";
						}else{
							color = "FFFFFF";
						}
   %>
            <tr bgcolor="<%=color%>" onMouseOut="this.className='tdMenuNormal1'" onMouseOver="this.className='tdMenuSelect'">
              <td>
       			<div align="center" ><%=intnum++%></div>
       		</td>
       		<td>
       			<div align="center" ><%=accountName%></div>
       		</td>
       		<td>
       			<div align="center" ><%=pcount%></div>
       		</td>
       		
           
       		<td  align = "center" >
       			<input type="checkbox" name="itemSelected" value="<%=accountId%>"/>
       		</td>
            </tr>
           <%}%>
         </table></td>
        </tr>
    	<tr>
    		<td>
    			&nbsp;
    		</td>
    	</tr>
        <tr>

    <td>

    	<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1"  bgcolor="7C92B7">
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
                          <td><div align="left">1、<br/>
</div></td>
                        </tr>
                      </table>
                  </div></td>
              </tr>
          </table></td>
        </tr>
    </table>

    </td>
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

