<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.mobi.sys.javabean.Per_zhandian" %>
<%@ page import="java.sql.ResultSet"%>

<%
	
        String path = request.getContextPath();
        String loginName = (String)session.getAttribute("loginName");
		String accountid = request.getParameter("accountid");
		String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
		int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
        curpage=Integer.parseInt(s_curpage);
        Per_zhandian bean = new Per_zhandian();
         
%>

<html>
<head>
<title>

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
.menubuttonbg30          {
	width: 122px;
	color: #333;
	text-align: center;
	background-image: url(<%=path%>/images/menubutton6.jpg);
	vertical-align: text-bottom;
	padding-top: 0px;
	height: 23px;
}
.menubuttonbg30 a:link     {
	color: #333;
	text-align: center;
	vertical-align: text-bottom;
	background-position: bottom;
	width: 122px;
	height: 23px;
	padding-top: 0px;
}
.menubuttonbg30 a:visited  {
	color: #464646;
	background-image: url(<%=path%>/images/menubutton6.jpg);
	width: 122px;
	text-align: center;
	vertical-align: text-bottom;
	height: 23px;
	padding-top: 0px;
}
.menubuttonbg30 a:hover    {
	color: #C2050C;
	background-image: url(<%=path%>/images/menubutton32.jpg);
	width: 122px;
	text-align: center;
	vertical-align: text-bottom;
	height: 23px;
	padding-top: 0px;
}	
.menubuttonbg2           {
	height: 23px;
	width: 122px;
	color: #333333;
	text-align: center;
	padding-right: 0px;
	vertical-align: text-bottom;
	padding-top: 0px;
}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/tx.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
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

  function chaxun(){
					
	}
	function delLogs(){
		

	}
	function addJz(){
		document.form1.action=path+"/web/jizhan/addjz.jsp";
		document.form1.submit();
	}
	function daorujz(){
		alert("请期待！");
	}

</script>

</head>
<body  class="body" style="overflow-x:hidden;">
	<LINK href="<%=path%>/images/css.css" type=text/css rel=stylesheet>
	<form action="" name="form1" method="POST">
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
							<td colspan=1 width="600" background="<%=path%>/images/btt.bmp" height=40><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分配负责站点</span></td>
							
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
                      <td height="19" colspan="4">　<img src="../../images/v.gif" width="8" height="9" />
                      	&nbsp;&nbsp;
                      	<select name="changeTitle" onchange="changefz()">
                      		<option value="1">分配负责区域</option>
                      		<option value="2" selected=selected>分配负责站点</option>
                      		<option value="3">分配负责地市</option>
                      	</select>
                      	</td>
                    </tr>

  		 <tr><!--
        
					&nbsp;标题：<input type="text" name="title" value="" class="form" style="width:100" onKeyPress="if(window.event.keyCode==13){chaxun();}"/>
					&nbsp;操作人：<input type="text" name="operName" value="" class="form" style="width:100" onKeyPress="if(window.event.keyCode==13){chaxun();}"/>
         </td>
         <td><input type="button" name="chaxunlogs0" id="id1" value="查询" onclick="chaxun()"  style="color:#014F8A"/>
         </td>-->
      </tr>


  </table>

  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">
      
                           <tr>
             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">姓名</div>
                                </td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">已分配站点范围</div>
            			</td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">删除</div>
            			</td>
                                
                              
            </tr>
       <%
       
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPer_Zhandian(accountid);
       	 System.out.println("per_zhandian::"+fylist);
		String pname="",pid="",pbegincode="",pendcode="";
		int intnum=0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     pid = (String)fylist.get(k+fylist.indexOf("ID"));
				pname = (String)fylist.get(k+fylist.indexOf("NAME"));
		    pbegincode = (String)fylist.get(k+fylist.indexOf("BEGINCODE"));
		    pendcode = (String)fylist.get(k+fylist.indexOf("ENDCODE"));
		   
			String color=null;

			if(intnum++%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr  bgcolor="<%=color%>">
       		<td >
       			<div align="center" ><%=pname%></div>
       		</td>
       		<td >
       			<div align="center" ><%=pbegincode%> —— <%=pendcode%></div>
       		</td>
       		<td>
       			<div align="center" ><a href="#" onclick="delfz('<%=pid%>')">删除</a></div>
       		</td>
       		
       	

       </tr>
       <%} %>
      
       <%} if(intnum==0){%>
					<td colspan=8 bgcolor="#dddddd">
       			<div align="center" >没有数据</div>
       		</td>
			<%}%>
  	 </table> 
  	</br>
  	 <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#dddddd">
  	 	
          <tr>
             <td width="100%" height="23" bgcolor="#888888"><div align="center" class="bttcn">添加新的负责站点</div>
             </td>
                               
          </tr>
          <tr>
          	<td  width="100%" >
          		<table id="newzd" width="80%">
          			<tr>
          				<td  colspan=2>
			          			&nbsp;开始站点代码：
			          			<input type="text" name="begincode" value="">
			          		</td>
			          		<td colspan=2>
			          			结束站点代码：
			          			<input type="text" name="endcode" value="">
			          		
			          	</td>
			          	<td >
			          		
			          		<td>
	          		</tr>
          		</table>
          	</td>
          </tr>
          <tr>
          	<td  width="100%">
          		<div id="newzd" align="center">
          			<input type="button" name="addnewrow1" value="增加一行" onclick="addRow()"/>
          			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          			<input type="button" name="addtjrows" value="提交" onclick="tijiao()"/>
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
				</td>
			</tr>
		</table>
      <br />
     
    </td>
    <td background="../../images/img_13.gif">&nbsp;</td>
  </tr>

</table>
<input type="hidden" name="accountid" value="<%=accountid%>"/>
</form>
</body>
<script language="javascript">
	var path = '<%=path%>';
	
   
    function tijiao(){
    	document.form1.action=path+"/servlet/account?action=pzhandian";
      document.form1.submit();
    
    }
    function changefz(){
    	var accountid='<%=accountid%>';
    	var i=document.form1.changeTitle.value;
    	if(i==1){
	    	document.form1.action=path+"/web/sys/per_area.jsp?accountid="+accountid;
	      	document.form1.submit();
      	}else if(i==3){
	    	document.form1.action=path+"/web/sys/per_areads.jsp?accountid="+accountid;
	      	document.form1.submit();      	
      	}
    }
    function addRow(){
		    var testTbl = document.getElementById("newzd");
		    var newTr = testTbl.insertRow();
		    var newTd1 = newTr.insertCell();
				var newTd0 = newTr.insertCell();
		
				var newTd2 = newTr.insertCell();
				var newTd3 = newTr.insertCell();
		
		
				//设置列内容和属性
				
				newTd0.innerHTML = '开始站点代码：&nbsp;&nbsp;<input type="text" name="begincode" value="">';
				newTd2.innerHTML = '结束站点代码：&nbsp;&nbsp;<input type="text" name="endcode" value="">';
				newTd3.innerHTML = '<input type=button class="inputbutton"  id="delbutton" value="删除" onClick="javascript:delRow(this.parentElement.parentElement)">'
		}
	function delRow(src){
		src.parentElement.deleteRow(src.rowIndex);
		//alert(testTbl.rowIndex)
		//testTbl.deleteRow(testTbl.rowIndex);
	}
	function delfz(id){
		document.form1.action=path+"/servlet/account?action=delfz&id="+id+"&accountid="+'<%=accountid%>';
      document.form1.submit();
	}
</script>
</html>


