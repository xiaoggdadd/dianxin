﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.ptac.app.systemconfiguration.EnhanceEleConfig.dao.*" %>
<%@ page import="java.sql.ResultSet"%>

<%
    String path = request.getContextPath();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
%>

<html>
<head>
<title>
logMange
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
    //编辑
  function toedit(ItemID,curpage){
          document.form1.action=path+"/web/appJSP/systemconfiguration/enhanceEleConfig.jsp?ItemID="+ItemID+"&curpage1="+curpage;
                                document.form1.submit();
  }
    //取消
  function quxiao(ItemID,curpage){
          document.form1.action=path+"/web/appJSP/systemconfiguration/enhanceEleConfig.jsp"+"?curpage1="+curpage;
                                document.form1.submit();
  }
  //修改
   function modifysys(ItemID,curpage){
          document.form1.action=path+"/servlet/EnhanceEleConfigurationServlet?action=modify&ItemID="+ItemID+"&curpage1="+curpage;
          document.form1.submit();
    }
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>

<body  class="body" style="overflow-x:hidden;">
<LINK href="<%=path%>/images/css.css" type=text/css rel=stylesheet>
	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
    <td width="10" height="500">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						
					
						<tr>
							<td colspan=3 width="600" background="<%=path%>/images/btt12.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;自动审核配置</span></td>
							
							<td width="380" align="right">
							
								</td>
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
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">


 
  </table><br>

  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">

                      <tr  height = "20">
                           <td width="10%" height="23" bgcolor="#888888"><div align="center" class="bttcn">配置项</div>
                           	<td width="7%" height="23" bgcolor="#888888"><div align="center" class="bttcn">配置值</div>
                              
                                
                        <td width="30%" height="23" bgcolor="#888888"><div align="center" class="bttcn">说明</div></td>
                       
                           <td width="5%" height="23" bgcolor="#888888"><div align="center" class="bttcn">编辑</div></td>
                      </tr>
       <%
        String whereStr = "";
        String ItemID2 = request.getParameter("ItemID");
        String curpage2 = request.getParameter("curpage1");
		System.out.println("SysSetBean-ItemID2:"+ItemID2);
		if(curpage2 == null){
		  
		}else{
		  curpage = Integer.parseInt(curpage2);
		}
        EnhanceEleConfigurationDao bean = new EnhanceEleConfigurationDaoImpl();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageData(curpage,whereStr);
		String ItemID = "",
		ItemName = "",
		ItemValue = "",
		ItemDescription = "";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     
		    ItemID = (String)fylist.get(k+fylist.indexOf("ITEMID"));
		    ItemName = (String)fylist.get(k+fylist.indexOf("ITEMNAME"));
		    ItemValue = (String)fylist.get(k+fylist.indexOf("ITEMVALUE"));
			ItemDescription = (String)fylist.get(k+fylist.indexOf("ITEMDESCRIPTION"));
		    
			String color=null;

			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
			//intnum++;
       //被编辑项比较
       if(ItemID.equals(ItemID2)){
       %>
        <tr bgcolor="<%=color%>">
       		 <td>
       			<div align="center" ><%=ItemName%></div>
       		</td>
       		<td>
       			<input type="text" name="ItemValue" value="<%=ItemValue %>"  class="form" />
       		</td>
       		<td>
       			<div align="center" ><%=ItemDescription%></div>
       		</td>
       		      		
       		<td>
       			<div align="center" ><a href="javascript:modifysys('<%=ItemID%>','<%=curpage%>')" >更新</a>&nbsp;&nbsp;&nbsp;<a href="javascript:quxiao('<%=ItemID%>','<%=curpage%>')" >取消</a></div>
       		</td>      	             		
            
       </tr>
       <%intnum++;}else{ %>
       <tr bgcolor="<%=color%>">
       		 <td>
       			<div align="center" ><%=ItemName%></div>
       		</td>
       		<td>
       			<div align="right" ><%=ItemValue%></div>
       		</td>
       		<td>
       			<div align="center" ><%=ItemDescription%></div>
       		</td>
       		      		
       		<td>
       			<div align="center" ><a href="javascript:toedit('<%=ItemID%>','<%=curpage%>')" >编辑</a></div>
       		</td>      	             		
            
       </tr>
       <%intnum++;}
       } %>
       <tr bgcolor="#ffffff"  >
					<td colspan="12" >
						<div align="center">
							<font color='#000080'>&nbsp;页次:</font>
							&nbsp;&nbsp;
							 <b><font color=red><%=curpage%></font></b>

							 <font color='#000080'>/<b><%=allpage%></b>&nbsp;</font>
							 &nbsp;&nbsp;
							 <font color='#000080'>
						     <% if (curpage!=1)
						       {out.print("<a href='javascript:gopagebyno(1)'>首页</a>");}
						      else
						      {out.print("首页");}
						      %>
					     </font>
					     &nbsp;&nbsp;
							 <font color='#000080'>
						     <%if(curpage!=1)
						          {out.print("<a href='javascript:previouspage()'>上页</a>");}
						         else
						       {out.print("上页");}
						      %>
						   </font>
						   &nbsp;&nbsp;
							<font color='#000080'>
						     <% if(allpage!=0&&(curpage<allpage))
						         {out.print("<a href='javascript:nextpage()'>下页</a>");}
						         else
						        {out.print("下页");}
						     %>
             </font>
							&nbsp;&nbsp;
							<font color='#000080'>
					     <% if(allpage!=0&&(curpage<allpage))
					         {out.print("<a href='javascript:gopagebyno("+allpage+")'>尾页</a>");}
					        else
					        {out.print("尾页");}
					     %>
            </font>
            &nbsp;&nbsp;
						<select name="page" onChange="javascript:gopagebyno(document.form1.page.value)" class="form" >
					     <%for(int i=1;i<=allpage;i++)
					         {if(curpage==i){out.print("<option value='"+i+"' selected='selected'>"+i+"</option>");}
					      else{out.print("<option value='"+i+"'>"+i+"</option>");}
					         }
					     %>
				    </select>


						</div>
					</td>
				</tr>
       <%}%>


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
  
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td>&nbsp;</td>
    <td></td>
  </tr>
</table>
</form>
</body>
</html>
<script language="javascript">
	var path = '<%=path%>';
	function gopage()
     {
      document.form1.submit();
     }
     function previouspage()
     {
      if ( (parseInt(document.form1.page.value) ) < 1)
        document.form1.page.value = 1;
      else
      {
        document.form1.page.value = parseInt(document.form1.page.value) - 1;
        var curpage='<%=(curpage-1)%>';
      		document.form1.action=path+"/web/sysconfig/autoAuditList.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      //alert(parseInt(document.form1.page.value));
      //alert(document.form1.page.value);
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/sysconfig/autoAuditList.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/sysconfig/autoAuditList.jsp?curpage="+pageno;
      document.form1.submit();
     }
</script>


