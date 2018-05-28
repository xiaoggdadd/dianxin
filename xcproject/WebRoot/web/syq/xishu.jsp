<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.syq.SyqPageBean" %>
<%@ page import="java.sql.ResultSet"%>

<%
	
	String roleId = (String)session.getAttribute("accountRole");
       

        String path = request.getContextPath();
        String loginName = (String)session.getAttribute("loginName");
      
          String permissionRights="";
          
%>

<html>
<head>
<title>
站点列表
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
 .btt{ bgcolor:#888888;}
  .bttcn{ color:white;font-weight:bold;}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>

 <script src="<%=path%>/javascript/tx.js"></script>


</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"2105");

System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="../../images/css.css" type=text/css rel=stylesheet>
	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">


	<tr>
   
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr>
							<td colspan=1 width="400" background="<%=path%>/images/btt.bmp" height=40><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;能源折标煤系数表</span></td>
							
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
									<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" bgcolor="7C92B7">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">

				<tr>
         <td colspan=3>
       
         </td>
        
      </tr>


  </table>
<div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>

  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#666666">
       </tr>
                           <tr >
             <td width="50" height="23" bgcolor="#888888"><div align="center" class="bttcn">编号</div>
                                </td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">名称</div>
            			</td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">标准单位</div>
            			</td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">标煤单位</div>
            			</td>

                                    <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">标煤折算系数</div>
            			</td>
			                                
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">修改</div>
                                </td>
                               
            </tr>
       <%
       SyqPageBean bean = new SyqPageBean();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getZbxs();

		String bh = "",name = "",bzunit = "", bmunit= "",xishu = "";
		int intnum=1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     bh = (String)fylist.get(k+fylist.indexOf("BH"));
				name = (String)fylist.get(k+fylist.indexOf("NAME"));
		    bzunit = (String)fylist.get(k+fylist.indexOf("BZUNIT"));
		    bmunit = (String)fylist.get(k+fylist.indexOf("BMUNIT"));
		    xishu = (String)fylist.get(k+fylist.indexOf("XISHU"));
				
				
			String color=null;

			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
			intnum++;
       %>
       <tr bgcolor="<%=color%>">
       		
       		<td>
       			<div align="center"  ><%=bh%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=name%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=bzunit%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=bmunit%></div>
       		</td>
       		<td>
       			<div align="center"  id="div<%=bh%>"><%=xishu%></div>
       		</td>

       		<td>
       			<div align="center"id="butt<%=bh%>" ><a href="#" onclick="modifyjz('<%=bh%>','<%=xishu%>')">修改</a></div>
       		</td>
       		

       </tr>
       <%} %>

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
    <td background="../../images/img_13.gif">&nbsp;</td>
  </tr>
 
</table>
<input type="hidden" id="hid" name="hid" value=""/>
</form>
</body>
</html>

<script language="javascript">
	var path = '<%=path%>';
function modifyjz(id,xishu){

     		var divid="div"+id;
      var buttid = "butt"+id;
      var inputname="name"+id;
      document.getElementById(divid).innerHTML='<input type="text" id="'+inputname+'" name="'+inputname+'" value=\''+xishu+'\' size=5/>';
      var bhtml='<input type="button" name="updatedf" value="更新" onclick="updatedfei(\''+id+'\',\''+xishu+'\')">';
      bhtml+='<input type="button" name="canceldf" value="取消" onclick="canceldfei(\''+id+'\',\''+xishu+'\')">';
      //alert(bhtml);
      document.getElementById(buttid).innerHTML=bhtml;
      
   }
   
   function updatedfei(id,olddata){
     	
    	 var inputname="name"+id;
    	 var inputvalue=document.getElementById(inputname).value
    	 var url = path+"/servlet/syq?action=xishu&bh="+id+"&newdata="+inputvalue+"&olddata="+olddata;
    	 document.getElementById("hid").value=id;
    	// alert(url);
			sendRequest(url,"modifyxs");
			
     }
     
     function canceldfei(id,dianfei){
     	
     	var divid="div"+id;
      var buttid = "butt"+id;
			//alert(dianfei);
      document.getElementById(divid).innerHTML=dianfei;
     var bhtml='<a href="#" onclick="modifyjz(\''+id+'\',\''+dianfei+'\')">修改</a>';
     
      //alert(bhtml);
      document.getElementById(buttid).innerHTML=bhtml;
      
     	
     }
     
     function yemianhuifu(res){
     //alert(res);
    	 

    	 var id = document.getElementById("hid").value;//res.substring(res.indexOf(":")+1,res.indexOf("<"));
   			var inputname="name"+id;
    	 var inputvalue=document.getElementById(inputname).value
    	 //var dianfei = res.substring(res.indexOf("修改为")+4);
    	 canceldfei(id,inputvalue);
    	
     }

     </script>


