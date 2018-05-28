<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime,java.math.BigDecimal"%>
<%
	CTime time = new CTime();
        Account account = new Account();
        account = (Account)session.getAttribute("account");
		String accountId = account.getAccountId();
                String path = request.getContextPath();
	String dwxz = request.getParameter("dwxz")!=null?request.getParameter("dwxz"):"0";
        String srzc = request.getParameter("srzc")!=null?request.getParameter("srzc"):"1";
		String month = String.valueOf(Integer.parseInt(time.formatShortDate().substring(4, 6)));
        String qijian = request.getParameter("qijian")!=null?request.getParameter("qijian"):month;

		BigDecimal d = new BigDecimal("00000000000000.00");
		BigDecimal dc = new BigDecimal("00000000000000.00");
%>
<html>
<head>
<title>
individual
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
</head>
<script language="javascript">
var path = '<%=path%>';
function chaxuns(){
            	document.form1.action=path+"/web/analysis/topAnalysis.jsp"
                document.form1.submit()
	}

</script>
<jsp:useBean id="bean" scope="page" class="com.noki.mobi.analysis.javabean.TopAnalysisBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="../../images/css.css" type="text/css" rel=stylesheet>
<form action="" method="POST" name="form1">

          <table  width="100%"  border="0" cellspacing="0" cellpadding="0">


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
                                                  <td width="169" background="../../images/a_22.gif"><span class="style1"> 收入支出排名分析</span></td>
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
                                        <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                         <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4">　</td>
                    </tr>
                    <tr>
                      <td colspan="4" height="25">
					  会计期间：<select name="qijian" style="width:80" class="form">
                        	<option value="0">选择月份</option>
                                <option value="1" <%if(qijian.equals("1")){%> selected="selected" <%}%> >2008-01</option>
                                <option value="2" <%if(qijian.equals("2")){%> selected="selected" <%}%> >2008-02</option>
                                <option value="3" <%if(qijian.equals("3")){%> selected="selected" <%}%> >2008-03</option>
                                <option value="4" <%if(qijian.equals("4")){%> selected="selected" <%}%> >2008-04</option>
                                <option value="5" <%if(qijian.equals("5")){%> selected="selected" <%}%> >2008-05</option>
                                <option value="6" <%if(qijian.equals("6")){%> selected="selected" <%}%> >2008-06</option>
                                <option value="7" <%if(qijian.equals("7")){%> selected="selected" <%}%> >2008-07</option>
                                <option value="8" <%if(qijian.equals("8")){%> selected="selected" <%}%> >2008-08</option>
                                <option value="9" <%if(qijian.equals("9")){%> selected="selected" <%}%> >2008-09</option>
                                <option value="10" <%if(qijian.equals("10")){%> selected="selected" <%}%> >2008-10</option>
                                <option value="11" <%if(qijian.equals("11")){%> selected="selected" <%}%> >2008-11</option>
                                <option value="12" <%if(qijian.equals("12")){%> selected="selected" <%}%> >2008-12</option>
                        </select>
						&nbsp;&nbsp;
                        单位性质：<select name="dwxz" style="width:120" class="form">
                        	<option value="0">全部</option>
                                <option value="1" <% if(dwxz.equals("1")){%> selected="selected"<%}%>>行政单位</option>
                                <option value="2" <% if(dwxz.equals("2")){%> selected="selected"<%}%>>事业单位</option>
                        </select>
                        &nbsp;&nbsp;
                        收入支出：<select name="srzc" style="width:120" class="form">
                        	<option value="1">收入</option>
                                <option value="2" <% if(srzc.equals("2")){%> selected="selected"<%}%>>支出</option>
                        </select>
                        &nbsp;&nbsp;
                        <input type="button" name="chaxun" value="执行查询" id="id1" onclick="chaxuns()" />
                      </td>
                    </tr>

      <tr>
        <td width="5%" bgcolor="C8E1FB" height="25"><div align="center">序号</div>
        </td>
         <td height="19" bgcolor="C8E1FB" width="40%"><div align="center">预算单位</div></td>
         <td bgcolor="C8E1F8" width="25%"><div align="center">收&nbsp;&nbsp;&nbsp;入</div>
           </td>
        <td height="19" bgcolor="C8E1FB" width="25%"><div align="center">支&nbsp;&nbsp;&nbsp;出</div>
         </td>
      </tr>

      <%
      	ArrayList list = new ArrayList();
        list = bean.getTopAnalysis(dwxz,srzc,qijian);
        int k = 1;
        String color = "F3F3F3";
        int countColum = ((Integer)list.get(0)).intValue();
      %>

       <%	String sr = "",zc = "";
              for(int i = countColum;i<list.size()-1;i+=countColum){
                sr = (String)list.get(i+list.indexOf("SR"));
                zc = (String)list.get(i+list.indexOf("ZC"));
				
                sr = sr!=null?sr:"0";
                zc = zc!=null?zc:"0";
				d = d.add(new BigDecimal(Double.parseDouble(sr)));
				dc = dc.add(new BigDecimal(Double.parseDouble(zc)));
              if(k%2==0){
                          color = "#F2F9FF";
                  }else{
                          color = "FFFFFF";
                  }
       %>
       		<tr bgcolor="<%=color%>" onMouseOut="this.className='tdMenuNormal1'" onMouseOver="this.className='tdMenuSelect'">
      			<td><div align="center"><%=k++%></div>
      			</td>
                        <td><div align="left"><%=(String)list.get(i+list.indexOf("DWMC"))%></div>
                        </td>
                        <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(sr))%></div>
                        </td>
                        <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(zc))%></div>
                        </td>
        	 </tr>
       <%
              }
       %>
	   <tr bgcolor="<%=color%>" onMouseOut="this.className='tdMenuNormal1'" onMouseOver="this.className='tdMenuSelect'">
      			<td><div align="center"></div>
      			</td>
                        <td><div align="center">总&nbsp;&nbsp;&nbsp;计</div>
                        </td>
                        <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(d.toString()))%></div>
                        </td>
                        <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(dc.toString()))%></div>
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
                                <td><div align="left">1、</div></td>
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
