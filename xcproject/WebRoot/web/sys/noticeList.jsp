<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.noki.mobi.common.Account"%>
<%
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	 byte[] tmp = null;

	try{tmp = title.getBytes("iso-8859-1");}catch(Exception e){}
	title = new String(tmp,"gb2312");
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String roleId = account.getRoleId();
%>
<html>
<head>
<title>
roleManage
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
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
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

function infoNotice(noticeId){

 	 window.open('infoNotice.jsp?noticeId='+noticeId,'','width=700,height=500,status=yes,scrollbars=yes,resizable=yes,left=150,top=100')
}

function searchnotice(){
 	var title = document.form1.title.value
        document.form1.action=path+"/web/sys/noticeList.jsp?title="+title;
        document.form1.submit();
}

function delNotice(){

	var i = 0;

		for(var j = 0;j < document.form1.elements.length ; j++){
			if (document.form1.elements[j].checked){
				i++;
			}
		}

		if(i>0){
			if(confirm("确定要删除这些公告么！只能删除本部门的公告！不可恢复！")){
                                document.form1.action=path+"/servlet/notice?action=del";
                                document.form1.submit();
                        }else{
                                return;
                        }
		}else{
			alert("请选择要删除的公告！");
			return;
		}
}
function addNotice(){
	document.form1.action=path+"/web/sys/addNotice.jsp";
                                document.form1.submit();
}
</script>
</head>
<jsp:useBean id="bean" scope="page" class="com.noki.mobi.sys.javabean.NoticeBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="../../images/css.css" type="text/css" rel=stylesheet>
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
            <td width="31%" background="../../images/b_14.gif"><span class="STYLE6"><strong><a href="<%=path%>/web/sys/addNotice.jsp">｜发布公告｜</a></strong></span></td>
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
							<td width="169" background="../../images/a_22.gif"><span class="style1"> 公告管理</span></td>
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
												<table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">
<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
 <tr bgcolor="F9F9F9">
                      <td height="19" colspan="3">　<img src="../../images/v.gif" width="8" height="9" />
                      	标题名 : <input type="text" value="<%=title%>" name="title"  onKeyPress="if(window.event.keyCode==13){searchnotice();}"/>&nbsp;&nbsp;<input type="button" name="snotice0" value="查询" id="id1" onclick="searchnotice()"  style="color:#014F8A"/>
                      </td>
                      <td>
                      	<input type="button" name="addnotic1" value="添加" onclick="addNotice()" class="memo" id="id1"  style="color:#014F8A"/>
                      	&nbsp;&nbsp;
                        <input type="button" name="delbut1" value="删除" onclick="delNotice()" class="memo" id="id1"  style="color:#014F8A"/>
                      </td>
                      <td>
                      	&nbsp;
                      </td>
                    </tr>
    </tr>
        <tr>
         <td width="10%" height="23" bgcolor="C8E1FB"><div align="center">序号</div>
         </td>
         <td height="23" width="50%" bgcolor="C8E1FB"><div align="center">标题</div>
         </td>

          <td height="23"  width="15%" bgcolor="C8E1FB"><div align="center">发布时间</div>
         </td>
         <td height="23"  width="15%" bgcolor="C8E1FB"><div align="center">发布人</div>
         </td>
         <td height="23"  width="10%" bgcolor="C8E1FB"><div align="center">选择</div>
         </td>
      </tr>
                                           <%
   	ArrayList list = new ArrayList();
        list = bean.getNotes(title);
        int num = 1;
        int countColum = ((Integer)list.get(0)).intValue();
        String title2 = "",oper = "",time = "",noticeid;
        String color = "F3F3F3";
        for(int i = countColum;i<list.size()-1;i+=countColum){
           title2 = (String)list.get(i+list.indexOf("TITLE"));
           oper = (String)list.get(i+list.indexOf("CREATOR"));
           time = (String)list.get(i+list.indexOf("CREATIME"));
           noticeid = (String)list.get(i+list.indexOf("NOTICEID"));
           if(num%2==0){
							color = "#F2F9FF";
						}else{
							color = "FFFFFF";
						}
   %>
   	<tr>
              <td bgcolor="<%=color%>"><div align="center"><%=num++%></div>
              </td>
              <td bgcolor="<%=color%>"><div align="center">
                	<a href="javascript:infoNotice('<%=noticeid%>')"><%=title2%></a></div>
              </td>
              <td bgcolor="<%=color%>"><div align="center"><%=time%></div>
              </td>
              <td bgcolor="<%=color%>"><div align="center"><%=oper%></div>
              </td>
              </td>
              <td bgcolor="<%=color%>"><div align="center"><input type="checkbox" name="noticeItem" value="<%=noticeid%>" /></div>
              </td>
   	</tr>
   <%}%>

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
                          <td><div align="left">1、标题名称支持模糊查询<br />
2、此页面不支持分页，管理员要定期删除过期的公告，以免产生冗余<br/>
</div></td>
                        </tr>
                      </table>
                  </div></td>
              </tr>
          </table></td>
        </tr>
    </table></td>
  </tr>
</table>
    </td>
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
  <jsp:param name="pageUrl" value="noticeList"/>
  <jsp:param name="roleId" value="<%=roleId%>"/>
</jsp:include>
