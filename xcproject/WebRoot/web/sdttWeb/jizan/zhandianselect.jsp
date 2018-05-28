<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>

<%
		String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId=account.getAccountId();
        String roleId = account.getRoleId();
        String loginName = (String)session.getAttribute("loginName");
        
	    String sheng = (String)session.getAttribute("accountSheng");
    	String agcode1="";
    	if(request.getParameter("shi")==null){
    		ArrayList shilist = new ArrayList();
    		CommonBean commBean = new CommonBean();
    		shilist = commBean.getAgcode(sheng,account.getAccountId());
			if(shilist!=null){
       		int scount = ((Integer)shilist.get(0)).intValue();
            agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
          }
    	} 
        String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
        String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
        String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
	    String sname = request.getParameter("sname")!=null?request.getParameter("sname"):"";
	    String szdcode = request.getParameter("szdcode")!=null?request.getParameter("szdcode"):"";
	

        String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
%>

<html>
<head>
<base target=_self> 
<title>
选择站点
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
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}



#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.bttcn{color:black;font-weight:bold;}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
	<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	
 <script src="<%=path%>/javascript/tx.js"></script>
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
  	/*
  	alert(window.name);
  	if(window.name==""){
  		document.getElementById("form1").target="dialogFrame";
  	}else if(window.name=="dialogFrame"){
  		document.getElementById("form1").target="dialogFrame";
  	}
  	*/
  	//alert(window.name);
		document.form1.action=path+"/web/jizhan/zhandianselect.jsp";
		document.form1.submit();
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
	$(function(){

		$("#query").click(function(){
			chaxun();
		});
		$("#qeudingBtn").click(function(){			
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
							
							<td colspan="4" width="50" >
											 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点信息列表</span>	
												</div>
											</td>
						</tr>
<tr>
<td height="20" colspan="4" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td>
	    	</tr>
	    	<tr>
	    	<td width="1200px">
	    	<table>
<tr class="form_label">
         <td>城市：</td>
         
        <td>
         <select name="shi" id="shi" class="selected_font" onchange="changeShi()">
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
         		</select></td>
         <td>区县：</td>
         <td>
         <select name="xian" id="xian" class="selected_font"  onchange="changeXian()">
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
         	</select></td>
         	<td>小区：</td>
         	<td><select name="xiaoqu" id="xiaoqu" class="selected_font">
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
         <td>站点名称：</td>
         <td><input type="text" name="sname" value="<%=sname%>" class="selected_font"/></td>
      </tr>
      <tr class="form_label">
         	<td>站点ID：</td>
         <td><input type="text" name="szdcode" value="<%=szdcode%>" class="selected_font"/>
         </td>
         <td>
        <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:550px;TOP:0PX">
		<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
		<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
	</div>

         </td>
      </tr>
      </table>
      </td>
      </tr>
 
 


  </table>
<table  height="23">
<tr ><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>
  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" class="form_label" bgcolor="#cbd5de">
       
               <tr>
                   <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"></div></td>                               
                   <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                   <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
                   <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td>
                   <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">是否标杆</div></td>
      			   <td nowrap height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>                               	
              </tr>
       <%
       JiZhanBean bean = new JiZhanBean();
       	 ArrayList fylist = new ArrayList();
       	 fylist = bean.getPageData_select(curpage,sheng,shi,xian,xiaoqu,sname,szdcode,loginName,loginId);
       	 allpage=bean.getAllPage();
		String jzname = "",szdq = "",jztype= "",sfbg = "",bieming="",id="",returnvalue="",tshi="",countbzw="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		        id = (String)fylist.get(k+fylist.indexOf("ID"));
				jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		        szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		        jztype = (String)fylist.get(k+fylist.indexOf("JZTYPE"));
				sfbg = (String)fylist.get(k+fylist.indexOf("SFBG"));				
				bieming = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
				tshi = (String)fylist.get(k+fylist.indexOf("SHI"));
				countbzw = (String)fylist.get(k+fylist.indexOf("COUNTBZW"));
				if(jzname.equals("")||jzname.equals("null")||jzname==null){
				jzname="";
				}
				if(szdq.equals("")||szdq.equals("null")||szdq==null){
				szdq="";
				}
				if(jztype.equals("")||jztype.equals("null")||jztype==null){
				jztype="";
				}
				if(bieming.equals("")||bieming.equals("null")||bieming==null){
				bieming="";
				}
				if(jztype.equals("")||jztype.equals("null")||jztype==null){
				jztype="";
				}
				if(tshi.equals("")||tshi.equals("null")||tshi==null){
				tshi="";
				}
				if(countbzw.equals("")||countbzw.equals("null")||countbzw==null){
					countbzw="0";
				}
				//System.out.println("获得市————————"+tshi);
				returnvalue=id+","+jzname+","+szdq+","+jztype+","+sfbg+","+bieming+","+tshi+","+countbzw;
				System.out.println("所有"+returnvalue);
			String color=null;

			if(intnum++%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr bgcolor="<%=color%>">
       		<td  align = "center" >
       			<input type="checkbox"  name="cbox" value="<%=returnvalue%>" onClick="chooseOne(this);"/>
       		</td>
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="left" ><%=szdq%></div>
       		</td>
       
       		<td>
       			<div align="center" ><%=jztype%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sfbg%></div>
       		</td>
       		<td>
       			<div align="left" ><%=id%></div>
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
								<table align="right">
			<tr><td colspan="6">
							<div id="qeudingBtn" style="position:relative;width:59px;height:23px;cursor: pointer;right：200px;TOP:0PX">
				                <img alt="" src="<%=request.getContextPath() %>/images/tijiao.png" width="100%" height="100%" />
				                <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">确定</span>
			                 </div>
						</td></tr>
	

</table>
<input type="hidden" name = "returnvalue" id = "returnvalue" value=""/>
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
      		document.form1.action=path+"/web/jizhan/zhandianselect.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/jizhan/zhandianselect.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/jizhan/zhandianselect.jsp?curpage="+pageno;
      document.form1.submit();
     }
     function delzd(id){
     		document.form1.action=path+"/servlet/zhandian?action=del&id="+id
      document.form1.submit();
    }
      function chooseOne(cb){ 
     //alert(cb.value);  
         //先取得同name的chekcBox的集合物件   
         var obj = document.getElementsByName("cbox");  
         for (i=0; i<obj.length; i++){   
             //判斷obj集合中的i元素是否為cb，若否則表示未被點選   
             if (obj[i]!=cb){
              obj[i].checked = false;   
             } else {
              obj[i].checked = true;
              document.getElementsByName("returnvalue").value = obj[i].value;
             }                     
         } 
     }   
    function delacc(){
    	var returnvalue="";
    	var zdid="";
    	var jzname="";
    	var szdp="";
    	var jztype="";
    	var sfbg="";
    	var bieming="";
    	var tshi="";
    	var countbzw="";
    	var jj=0;
    		for(var k = 0;k < document.form1.elements.length;k++){
    			if(document.form1.elements[k].checked){
    				jj++;
    				returnvalue = document.form1.elements[k].value;
    				zdid=returnvalue.substring(0,returnvalue.indexOf(","));
					returnvalue = returnvalue.substring(returnvalue.indexOf(",")+1);
					jzname=returnvalue.substring(0,returnvalue.indexOf(","));
					returnvalue = returnvalue.substring(returnvalue.indexOf(",")+1);
    				szdq=returnvalue.substring(0,returnvalue.indexOf(","));
					returnvalue = returnvalue.substring(returnvalue.indexOf(",")+1);
    				jztype=returnvalue.substring(0,returnvalue.indexOf(","));
					returnvalue = returnvalue.substring(returnvalue.indexOf(",")+1);
    				sfbg=returnvalue.substring(0,returnvalue.indexOf(","));
					returnvalue = returnvalue.substring(returnvalue.indexOf(",")+1);
    				bieming=returnvalue.substring(0,returnvalue.indexOf(","));
					returnvalue = returnvalue.substring(returnvalue.indexOf(",")+1);
    				tshi=returnvalue.substring(0,returnvalue.indexOf(","));
    				returnvalue = returnvalue.substring(returnvalue.indexOf(",")+1);
    				countbzw=returnvalue.substring(0);
    			}
    		}
    		
				if(jj==0){
					alert("请选择站点");
					return;
				}else{
					window.opener.location.href = encodeURI(encodeURI(
					path+ "/web/sdttWeb/jizan/adddianbiao.jsp?zdid=" + zdid+ 
					"&jzname="+jzname+"&szdq=" + szdq + "&jztype=" + jztype+ 
					"&sfbg=" + sfbg+ "&bieming=" + bieming+ "&tshi=" + tshi + "&countbzw=" + countbzw));                   
					// window.opener.document.form1.submit();
					window.close();
					
		     //window.returnValue=returnvalue;
		     //window.close();
	          }           
		
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

