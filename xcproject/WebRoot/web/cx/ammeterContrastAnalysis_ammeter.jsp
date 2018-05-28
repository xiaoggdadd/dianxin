<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>

<%
	String sheng = (String)session.getAttribute("accountSheng");
        String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
        String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
        String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
       //System.out.println("logManage.jsp>>"+beginTime);
	String sname = request.getParameter("sname")!=null?request.getParameter("sname"):"";
	String szdcode = request.getParameter("szdcode")!=null?request.getParameter("szdcode"):"";
	
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId=account.getAccountId();
String roleId = account.getRoleId();
String loginName = (String)session.getAttribute("loginName");
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
%>

<html>
<head>
<style type="text/css">
.bttcn{color:white;font-weight:bold;}
.form{width:130px}
</style>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>

<script src="<%=path%>/javascript/tx.js"></script>
<script language="javascript">
	var obj = window.dialogArguments
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
		document.form1.action=path+"/web/cx/ammeterContrastAnalysis_ammeter.jsp";
		document.form1.submit();
	}
  $(function(){

	$("#query").click(function(){
		chaxun();
	});
	$("#quxiaoBtn").click(function(){
		quxiao();
	});
	$("#quedingBtn").click(function(){
		delacc();
	});
});

</script>

</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" id="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
    <td width="10" height="500" background="../../images/img_10.gif">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="600"></td>
						</tr>
						<tr>
							<td  width="600" background="<%=path%>/images/btt.bmp" height=63><span style="color:black;font-weight:bold;font-size=16;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电表信息列表</span></td>
							
							
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
					 <tr><td>&nbsp;</td></tr>
					 <tr><td><div id="parent" style="display:inline">
                             </div><div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                             </div>
                      </td></tr>
<tr>
         <td colspan=3>
         
      &nbsp;&nbsp;&nbsp;&nbsp;城&nbsp;市&nbsp;：&nbsp;&nbsp;&nbsp;     
         <select name="shi" id="shi" style="width:130;" onchange="changeShi()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList shilist = new ArrayList();
	         	shilist = commBean.getAgcode(sheng,shi,loginName);
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
  &nbsp;&nbsp;&nbsp;区&nbsp;&nbsp;县&nbsp;&nbsp;：&nbsp;&nbsp;
         <select name="xian" id="xian" style="width:130;"  onchange="changeXian()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList xianlist = new ArrayList();
	         	shilist = commBean.getAgcode(shi,xian,loginName);
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
          &nbsp;&nbsp;&nbsp;乡&nbsp;镇&nbsp;：   
         	<select name="xiaoqu" id="xiaoqu" style="width:130">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList xiaoqulist = new ArrayList();
	         	xiaoqulist = commBean.getAgcode(xian,xian,loginName);
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
         
      
         
         	<td colspan=3>
         			站点名称：
         <input type="text" name="sname" value="<%=sname%>" class="form"/>
     
        <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:82%;TOP:-23PX">
			<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
			<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		</div>

         </td>
        
      </tr>
 

  </table>
<div id="parent" style="display:inline">
                          <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                      </div>
  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">
       </tr>
                           <tr>
             <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn"></div>
             			 </td>
                                </td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">站点名称</div>
            			</td>
            			</td>
            			<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">电表ID</div>
            			</td>
            			</td>
            			<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">电表名称</div>
            			</td>
                                <td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">所在地区</div>
            			</td>
				<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">集团报表类型</div>
            			</td>
				<td nowrap height="23" bgcolor="#888888"><div align="center" class="bttcn">是否标杆</div>
            			
            		
                                	
            </tr>
       <%
       JiZhanBean bean = new JiZhanBean();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageData_select(curpage,sheng,shi,xian,xiaoqu,sname,loginName,loginId);
       	 allpage=bean.getAllPage();
		String jzname = "",szdq = "",jztype= "",sfbg = "",bieming="",id="",returnvalue="";
		String dbid="",dbname="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     id = (String)fylist.get(k+fylist.indexOf("ID"));
		     dbid = (String)fylist.get(k+fylist.indexOf("DBID"));
		     dbname = (String)fylist.get(k+fylist.indexOf("DBNAME"));
				jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		    jztype = (String)fylist.get(k+fylist.indexOf("JZTYPE"));
				sfbg = (String)fylist.get(k+fylist.indexOf("SFBG"));
				bieming = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
				returnvalue=id+","+jzname+","+szdq+","+jztype+","+sfbg+","+dbid+","+dbname;
			String color=null;

			if(intnum++%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr bgcolor="<%=color%>">
       		<td  align = "center" >
       			<!--<input type="button" name="shanchulogs1" id="id1" class="memo" value="确定" onclick="delacc('<%=returnvalue%>')"  style="color:#014F8A"/>-->
       			<input type="radio" name="shanchulogs1" value="<%=returnvalue%>"/>
       		</td>
       		<td>
       			<div align="center" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dbid%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dbname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=szdq%></div>
       		</td>
       
       		<td>
       			<div align="center" ><%=jztype%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sfbg%></div>
       		</td>
       		
       
       		

       </tr>
       <%} %>
       <tr bgcolor="#ffffff" onMouseOut="this.style.backgroundColor=''" onMouseOver="this.style.backgroundColor='#BFDFFF'" >
					<td colspan="11" >
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
  	 <div id="parent" style="display:inline">
                          <div style="width:300px;display:inline;"><hr></div>
                      </div>
                      <br/>
                       <div id="quxiaoBtn" style="position:relative;width:60px;height:23px;cursor: pointer;right:4px;float:right;">
							<img alt="" src="<%=path %>/images/quxiao.png" width="100%" height="100%" />
							<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">返回</span>
					  </div>
                      <div id="quedingBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:15px">
							<img src="<%=path %>/images/tijiao.png" width="100%" height="100%">
							<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">确定</span>      
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
  <tr>
    <td><img src="../../images/img_23.gif" width="12" height="19" /></td>
    <td background="../../images/img_24.gif">&nbsp;</td>
    <td><img src="../../images/img_25.gif" width="12" height="19" /></td>
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
      		document.form1.action=path+"/web/cx/ammeterContrastAnalysis_ammeter.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/cx/ammeterContrastAnalysis_ammeter.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/cx/ammeterContrastAnalysis_ammeter.jsp?curpage="+pageno;
      document.form1.submit();
     }
    function delacc(){
    	var returnvalue="";
    	var jj=0;
    		for(var k = 0;k < document.form1.elements.length;k++){
    			if(document.form1.elements[k].checked){
    				jj++;
    				returnvalue = document.form1.elements[k].value;
    			}
    		}
				if(jj==0){
					alert("请选择电表");
					return;
				}else{
				var currentDiv = window.parent.$("#areaDiv");
				var array = new Array();
				array = returnvalue.split(",");
				
				var dbid = array[5];
				
				var dbname = array[1];
				var par = currentDiv.attr("name");
				if(par == "a"){
					window.parent.form1.jzaname.value = dbid;
					window.parent.form1.jzacode.value = dbid;
				}
				else if(par == "b"){
					window.parent.form1.jzbname.value = dbid;
					window.parent.form1.jzbcode.value = dbid;
				}
				closeDiv();
	          }           
		}
		function quxiao(){
		closeDiv();
		}
	function closeDiv(){
		var currentDiv = window.parent.$("#areaDiv");
		currentDiv.remove();
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
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
     </script>

