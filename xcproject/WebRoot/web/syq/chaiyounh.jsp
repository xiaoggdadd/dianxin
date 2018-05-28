<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.syq.SyqPageBean" %>
<%@ page import="java.sql.ResultSet"%>

<%
	String s_yuef = request.getParameter("s_yuef")!=null?request.getParameter("s_yuef"):"";
	String roleId = (String)session.getAttribute("accountRole");
        String s_xinghao = request.getParameter("s_xinghao")!=null?request.getParameter("s_xinghao"):"";
        String s_bzh = request.getParameter("s_bzh")!=null?request.getParameter("s_bzh"):"";
        String s_nhyt = request.getParameter("s_nhyt")!=null?request.getParameter("s_nhyt"):"0";
       //System.out.println("logManage.jsp>>"+beginTime);
	String s_zdid = request.getParameter("s_zdid")!=null?request.getParameter("s_zdid"):"";
	String s_zdname = request.getParameter("s_zdname")!=null?request.getParameter("s_zdname"):"";
        String path = request.getContextPath();
        String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
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
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <script src="<%=path%>/javascript/syq.js"></script>
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
					document.form1.action=path+"/web/syq/chaiyounh.jsp";
					document.form1.submit();
	}
	function delLogs(){
		

	}
	function addJz(){
		document.form1.action=path+"/web/syq/addyongshui.jsp?nhlx=03";
		document.form1.submit();
	}
	function daorujz(){
		//document.form1.action=path+"/web/jizhan/zhandiandaoru.jsp?servletname=zhandiandaoru&action=upzhandian";
		//document.form1.submit();
		alert("开发中");
	}

</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0101");

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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;柴油能耗列表</span></td>
							
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
<div id="parent" style="display:inline">
                          </div><div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>
				<tr>
         <td colspan=3>
         
         月份：
         <input type="text" name="s_yuef" value="<%=s_yuef%>" onfocus="setMonth(this);" size=8/>
         		型号：
         <input type="text" name="s_xinghao" value="<%=s_xinghao%>" size=16/>
         标志号：
         <input type="text" name="s_bzh" value="<%=s_bzh%>" size=16/>
         	能耗用途：
         	<select name="s_nhyt" style="width:100">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = ztcommon.getSelctOptions("NHYT");
	         	if(zdsx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdsx.get(0)).intValue();
	         		for(int i=cscount;i<zdsx.size()-1;i+=cscount)
                    {
                    	code=(String)zdsx.get(i+zdsx.indexOf("CODE"));
                    	name=(String)zdsx.get(i+zdsx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         		
        
         	</select>
         		
         			站点ID：
         <input type="text" name="s_zdname" value="<%=s_zdname%>" readonly size=12/><input type="button" name="baocun1" value="浏览" onclick="liulan()" id="id1"  style="color:#014F8A"/>
          &nbsp;
          <input type="button" name="baocun1" value="清空站点" onclick="qingkong()" id="id1"  style="color:#014F8A"/>
         <input type="hidden" name="s_zdid" value="<%=s_zdid%>">
       &nbsp;&nbsp;
         		<input type="button" name="chaxunlogs0" id="id1" value="查询" onclick="chaxun()"  style="color:#014F8A"/>
       
         		&nbsp;&nbsp;&nbsp;&nbsp;
         		
         </td>
        
      </tr>


  </table>
<div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>

  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#666666">
       </tr>
                           <tr >
             <td width="35" height="23" bgcolor="#888888"><div align="center" class="bttcn">序号</div>
                                </td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">月份</div>
            			</td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">标志号</div>
            			</td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">能耗类型</div>
            			</td>

                                    <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">能耗用途</div>
            			</td>
				<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">能耗数量</div>
            			</td>
				<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">调整数量</div>
            			</td>
            			<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">实际数量</div>
                                </td>
                                	<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">单价</div>
                                </td>
                                	<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">能耗金额</div>
                                </td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">调整金额</div>
                                </td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">实际金额</div>
                                </td>
                                 
                                
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">修改</div>
                                </td>
                                <td width="25" height="23"  bgcolor="#888888"><div align="center"><input type="checkbox" name="pageCheck" onClick="checkPage()" /></div>
             			 </td>
            </tr>
       <%
       SyqPageBean bean = new SyqPageBean();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageData(curpage,"03",s_yuef,s_xinghao,s_bzh,s_nhyt,s_zdid);
       	 allpage=bean.getAllPage();
		String yuef = "",bzh = "",nhlx = "", nhyt= "",nhsl = "",tzsl="",sjsl="",danjia="",nhje="",tzje="",sjje="",id="",shsign="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     id = (String)fylist.get(k+fylist.indexOf("ID"));
				yuef = (String)fylist.get(k+fylist.indexOf("YUEF"));
		    bzh = (String)fylist.get(k+fylist.indexOf("BZH"));
		    nhlx = (String)fylist.get(k+fylist.indexOf("NHLX"));
		    nhyt = (String)fylist.get(k+fylist.indexOf("NHYT"));
				nhsl = (String)fylist.get(k+fylist.indexOf("NHSL"));
				tzsl = (String)fylist.get(k+fylist.indexOf("TZSL"));
				sjsl = (String)fylist.get(k+fylist.indexOf("SJSL"));
				danjia = (String)fylist.get(k+fylist.indexOf("DANJIA"));
				nhje = (String)fylist.get(k+fylist.indexOf("NHJE"));
				tzje = (String)fylist.get(k+fylist.indexOf("TZJE"));
				sjje = (String)fylist.get(k+fylist.indexOf("SJJE"));
				shsign = (String)fylist.get(k+fylist.indexOf("SHSIGN"));
				
			String color=null;

			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
			if(shsign.equals("0")){
				color="#FFFF33" ;
			}else if(shsign.equals("2")){
				color="#FF3333" ;
			}

       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=intnum++%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=yuef%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=bzh%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=nhlx%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=nhyt%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=nhsl%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=tzsl%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=sjsl%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=danjia%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=nhje%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=tzje%></div>
       		</td>
       	<td>
       			<div align="center"  ><%=sjje%></div>
       		</td>
      
       		<td>
       			<div align="center" ><a href="#" onclick="modifyjz('<%=id%>')">修改</a></div>
       		</td>
       		<td  align = "center" >
       			<input type="checkbox" name="itemSelected" value="<%=id%>"/>
       		</td>

       </tr>
       <%} %>
       <tr bgcolor="#ffffff"  >
					<td colspan="15" >
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
										<tr bgcolor="#FFFFFF">
											
											<td align="right">
												<div id="parent" style="display:inline" align="right">
                          <div style="width:300px;display:inline;" align="right"><hr></div>
                      </div>
											</td>
											
										</tr>
										
										<tr bgcolor="#FFFFFF">
											
											<td>
												

												<div align="right">
												
												<input type="button" name="shanchulogs1" id="id1" class="memo" value="新增" onclick="addJz()"  style="color:#014F8A"/>
							         	&nbsp;&nbsp;&nbsp;&nbsp;
							         <input type="button" name="shanchulogs1" id="id1" class="memo" value="删除" onclick="delsj()"  style="color:#014F8A"/>
							         	&nbsp;&nbsp;&nbsp;&nbsp;
							        	<input type="button" name="shanchulogs1" id="id1" class="memo" value="导入" onclick="daorujz()"  style="color:#014F8A"/>
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
     
    </td>
    <td background="../../images/img_13.gif">&nbsp;</td>
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
      		document.form1.action=path+"/web/syq/chaiyounh.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/syq/chaiyounh.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/syq/chaiyounh.jsp?curpage="+pageno;
      document.form1.submit();
     }
     function delsj(){
     
     		var i = 0;

				for(var j = 0;j < document.form1.elements.length ; j++){
					if (document.form1.elements[j].checked){
						i++;
					}
				}

				if(i>0){
					if(confirm("确定要删除这些柴油能耗数据么！")){
		                                document.form1.action=path+"/servlet/syq?action=del_ys&pagelx=03";
		                                document.form1.submit();
		                        }else{
		                                return;
		                        }
				}else{
					alert("请选择要删除的柴油能耗数据");
					return;
				}
     
    }
    function dfinfo(id){
     		document.form1.action=path+"/web/jizhan/dfinfo.jsp?id="+id
      document.form1.submit();
    }
    function zlinfo(id){
     		document.form1.action=path+"/web/jizhan/zlinfo.jsp?id="+id
      document.form1.submit();
    }
    function modifyjz(id){
    
     		document.form1.action=path+"/web/syq/modify_yongshui.jsp?id="+id+"&pagelx=03";
      document.form1.submit();
     
    }
    function getValue(va,sql){
       var general =document.getElementById("general");
       var htmlsql =document.getElementById("htmlsql");
       general.value=va;
       htmlsql.value = sql;    
    }
    //页面载入方法
    function op(){
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian,ammeters,ammeterdegrees,electricfees&tab=zd,am,ad,ef&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
    }
    function changeSheng(){
			var sheng = document.form1.sheng.value;
			var xianlist = document.all.xian;
				xianlist.options.length="0";
				xianlist.add(new Option("请选择","0"));
			if(sheng=="0"){
				var shilist = document.all.shi;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				
				return;
			}else{
				sendRequest(path+"/servlet/area?action=sheng&pid="+sheng,"sheng");
			}
		}
		function updateShi(res){
			var shilist = document.all.shi;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		function changeShi(){
			var shi = document.form1.shi.value;
			var xiaoqu = document.all.xiaoqu;
				xiaoqu.options.length="0";
				xiaoqu.add(new Option("请选项","0"));
			if(shi=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=shi&pid="+shi,"shi");
			}
		}
		function updateQx(res){
			var shilist = document.all.xian;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
			function changeXian(){
			var shi = document.form1.xian.value;
			if(shi=="0"){
				var shilist = document.all.xiaoqu;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=xian&pid="+shi,"xian");
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
		function viewxn(zdid){
			document.form1.action=path+"/web/jizhan/viewxuni.jsp?id="+zdid
      document.form1.submit();
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
        function liulan(){
		//window.open("zhandianselect.jsp");
		//return;
		var url=path+"/web/jizhan/zhandianselect.jsp"
		//window.open(url);
		//return;
			var obj = new Object();
			obj.mid='mid';
		    var obj=showModalDialog(url,obj,'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no');	
			document.form1.s_zdid.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.s_zdname.value=obj.substring(0,obj.indexOf(","));
			
	}
		function qingkong(){
		document.form1.s_zdid.value="";
		document.form1.s_zdname.value="";
	}
document.form1.s_nhyt.value='<%=s_nhyt%>';
     </script>


