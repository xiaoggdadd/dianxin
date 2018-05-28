<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@page import="java.text.*" %>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%
	String path = request.getContextPath();
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
	String roleId = (String)session.getAttribute("accountRole");
    
	String dbname = request.getParameter("dbname")!=null?request.getParameter("dbname"):"";
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
    //System.out.println("logManage.jsp>>"+beginTime);
    String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
    String whereStr="";
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1,intnum=0;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String color=null;
%>

<html>
<head>
<title>
监测点列表
</title>
<style>
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

.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 .btt{ bgcolor:#888888;}
  .bttcn{ color:black;font-weight:bold;}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
  <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
 <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
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
 function getPublicPath(){
		   var path = document.location.pathname;
			var arraypath=path.split("/");
			var mapath="/";
		 	if(arraypath[1]==undefined){
			 arraypath[1]="";
			 mapath="";
		 	}
		  path=mapath+arraypath[1];
		  return path;
	} 
  function chaxun(){
        if(document.getElementById("shi").value=="0"||document.getElementById("shi").value==""){
	   alert("市不能为空");
	   }else{
		document.form1.action=path+"/web/equipmentmanage/equipmentmanage.jsp?command=chaxun";
		document.form1.submit();
	  }
	}
function addjcd(){
        var bz="2";//获取分摊值的标志位
		document.form1.action=path+"/web/equipmentmanage/add.jsp?bz="+bz+"";
		document.form1.submit();
	}
$(function(){

	$("#query").click(function(){
		chaxun();
	});

	$("#xinzengBtn").click(function(){
		addjcd();
	});
});
</script>
<script type="text/javascript">
//=点击展开关闭效果=

function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
var openTip = oOpenTip || "";
var shutTip = oShutTip || "";
if(targetObj.style.display!="none"){
   if(shutAble) return;
   targetObj.style.display="none";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = shutTip; 
   }
} else {
   targetObj.style.display="block";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = openTip; 
   }
}
}
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
        permissionRights=commBean.getPermissionRight(roleId,"0111");
        System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0">
 <tr >
			     
					    	<td colspan="4" width="50" >
											 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">监测点信息列表</span>	
												</div>
											</td>

	    	        </tr>	 
	    	        <tr><td height="20" colspan="4" >
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
         <td><select name="shi" id="shi" class="selected_font" onchange="changeCity()">
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
         		</select><span style="color: #FF0000;font-weight: bold">&nbsp;*</span></td>
        <td> 区县：</td>
       <td>  <select name="xian" id="xian" class="selected_font"  onchange="changeCountry()">
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
         	<td>乡镇：</td>
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
         	</select></td>
         	<td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p>
				</td>
	         		<td >
							          <% if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){ %>
        <div id="query" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
         <%}%>
							</td>
	    	</tr>	
	    </table>
	    </td>
	    </tr>
				   <tr>
			     <td colspan="5">
					<div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table>
					<tr class="form_label">
 
         	<td>电表名称：</td><td><input type="text" name="dbname" value="<%=dbname%>" class="selected_font"/></td>
         		
         			<td>站点名称：</td><td><input type="text" name="zdname" value="<%=zdname%>" class="selected_font"/></td>
      </tr>
      </table>
      </p>
      </div>
      </td>
      </tr>
  </table>
<table  height="23">
<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>

  	<div style="width:100%;overflow-x:auto;overflow-y:auto;border:1px" >  
  <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
           <tr bgcolor="#888888">
              <td width="3%" bgcolor="#DDDDDD" ><div align="center" class="bttcn">序号</div></td>
             <td width="14%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
             <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
             <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
             <td width="7%"  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
             <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td>
             <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表单价</div></td>
             <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表用途</div></td>
             <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>
             <td width="9%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>
             <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">倍率</div></td>
              <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>
             <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">查看</div></td>
         </tr>
       <%
         	if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and z.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and z.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" and z.xiaoqu='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("")){
				whereStr=whereStr+" and z.jzname like '%"+zdname+"%'";
			}
			
			if(dbname != null && !dbname.equals("")){
				whereStr=whereStr+" and d.dbname like '%"+dbname+"%'";
			}
		 EquipmentmanageBean bean=new EquipmentmanageBean();  
		 DecimalFormat la=new DecimalFormat("0.0000");
		 if("chaxun".equals(request.getParameter("command"))){
		 
       	ArrayList fylist = bean.getPageData(curpage,whereStr,account.getAccountId());
       	 allpage=bean.getAllPage();
       String str="";
		String jzname = "",szdq="",zdlx="",zdsx="",jztype="",dianfei="",beilv="",dbyt="",dfzflx="",dianbiaoid = "",dbnamee="",sheiebanid = "", mingcheng= "",guige = "",shuoshuzhuanye="",shuoshuwangyuan="",kjkmcode="",qcbcode="",zymxcode="",bili="",sccj="",zcbh="",bccd="",beizhu="";
		String sszy="",kjkm="",qcb="",zymx="";
		intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
			jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
			szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
			zdlx = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
			zdsx = (String)fylist.get(k+fylist.indexOf("PROPERTY"));
			jztype = (String)fylist.get(k+fylist.indexOf("JZTYPE"));
			dianfei = (String)fylist.get(k+fylist.indexOf("DANJIA"));
			if("".equals(dianfei)||null==dianfei||"null".equals(dianfei)){dianfei="0";}
			dianfei=la.format(Double.parseDouble(dianfei));
			
			
			dbyt = (String)fylist.get(k+fylist.indexOf("DBYT"));
			dbnamee = (String)fylist.get(k+fylist.indexOf("DBNAME"));
			dfzflx = (String)fylist.get(k+fylist.indexOf("DFZFLX"));
			beilv = (String)fylist.get(k+fylist.indexOf("BEILV"));
			DecimalFormat laa=new DecimalFormat("0.0");
			//System.out.println("倍率："+beilv);
			if("".equals(beilv)||null==beilv||"null".equals(beilv)){beilv="0";}
			beilv=laa.format(Double.parseDouble(beilv));
		    dianbiaoid = (String)fylist.get(k+fylist.indexOf("DBID"));
		    sheiebanid = (String)fylist.get(k+fylist.indexOf("SHEIEBANID"));
			bili = (String)fylist.get(k+fylist.indexOf("DBILI"));
			if(bili.equals("")||bili.equals("null")||bili==null){
			 bili="0";
			}
			sccj = (String)fylist.get(k+fylist.indexOf("SCCJ"));
			zcbh = (String)fylist.get(k+fylist.indexOf("ZCBH"));
			bccd = (String)fylist.get(k+fylist.indexOf("BCCD"));
			beizhu = (String)fylist.get(k+fylist.indexOf("BEIZHU"));
			// System.out.println("监测点---"+sszy+"5"+kjkm+"5"+zymx+"+---"+shuoshuzhuanye+"6"+kjkmcode+"6"+qcbcode);
			
            str="dianBiaoId="+dianbiaoid;
            	if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
			

       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=intnum++%></div>
       		</td>
       		<td>
       			<div align="left"  ><%=jzname%></div>
       		</td>
       		
       		<td>
       			<div align="left"  ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=zdlx%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=zdsx%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=jztype%></div>
       		</td>
       		<td>
       			<div align="right"  ><%=dianfei%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=dbyt%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=dbnamee%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=dfzflx%></div>
       		</td>
       		
       		<td>
       			<div align="right"  ><%=beilv%></div>
       		</td>
       		<td>
       			<div align="left"  ><%=dianbiaoid%></div>
       		</td>
       		<td>
       			<div align="center" ><a href="#" onclick="modify('<%=str%>')">查看</a></div>
       		</td>
       </tr>
       
       
       <%}}}%>
       <% if (intnum==0){
    	  System.out.println("QQQQ"+intnum);
         for(int i=0;i<15;i++){
          if(i%2==0){
			    color="#FFFFFF" ;
          }else{
			    color="#DDDDDD";
			}
         %>

        <tr bgcolor="<%=color%>">
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>                   
            <td>&nbsp;</td>
              
            <td>&nbsp;</td> 
            <td>&nbsp;</td>   
            <td>&nbsp;</td>   
			<td>&nbsp;</td>
			<td>&nbsp;</td>
            </tr>
      <% }}else if(!(intnum > 15)){
    	  for(int i=((intnum-1)%15);i<15;i++){
            if(i%2==0)
			    color="#FFFFFF";
            else
			    color="#DDDDDD" ;
        %>
        <tr bgcolor="<%=color%>">
             <td>&nbsp;</td>  
               <td>&nbsp;</td> 
             <td>&nbsp;</td> 
            <td>&nbsp;</td>
             <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>                   
            <td>&nbsp;</td>
              
            <td>&nbsp;</td> 
            <td>&nbsp;</td>   
            <td>&nbsp;</td>  
        </tr>
        <% }}%>
       <tr bgcolor="#ffffff"  >
					<td colspan="14" >
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
					</td>
				</tr>
     
  	 </table> 
  	 
  	 </div>
  	 <table align="right">
			<tr>
			<td >							
												<div id="parent" style="display:inline" align="right">
                          <div style="width:300px;display:inline;" align="right"><hr></div>
                      </div>
											
												<div align="right">
									</td>
									</tr><tr><td>		
													<%
												if(permissionRights.indexOf("PERMISSION_ADD")>=0){
												%>
<div id="xinzengBtn"style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
	 <img src="<%=path %>/images/xinzeng.png" width="100%" height="100%">
	 <span style="font-size:12px;position: absolute;left:29px;top:3px;color:white">新增</span>      
</div>
							        	<%}%>
							       
					</td>
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
      		document.form1.action=path+"/web/equipmentmanage/equipmentmanage.jsp?curpage="+curpage+"&command=chaxun";
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/equipmentmanage/equipmentmanage.jsp?curpage="+curpage+"&command=chaxun";
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/equipmentmanage/equipmentmanage.jsp?curpage="+pageno+"&command=chaxun";
      document.form1.submit();
     }
   function del(id){
     if(confirm("确定删除"+id)){
       var params = "?&sheiebanid="+id;
       var Url="<%=request.getContextPath()%>/servlet/EquipmentmanageServlet?action=del";
	    new Ajax.Request(Url,{
	      		method : 'post',
				asynchronous: false,
			    parameters : params,
				onComplete : function (resp){//回调
	 			  var result=resp.responseText;//返回数据
	 			  var msg = result.split("|");
					if ("msg"==msg[0]){
					  alert(msg[1]);
					  return;
					}
					window.location.reload();
	 			}
	 	});
     }
   }
function modify(str){
    	<%
			if(permissionRights.indexOf("PERMISSION_EDIT")>=0){
			%>
			var bz="2";//标志位 所属专业分摊数据从数据库还是从当前页面 获取值
			var de="1";//标志位  删除时从bean里遍历数据  
     		document.form1.action=path+"/web/equipmentmanage/add.jsp?"+str+"&bz="+bz+"&de="+de+"";
            document.form1.submit();
      <%
    }else{
      %>
      alert("您没有修改监测点信息的权限");
    <%}%>
     		
    }
    var XMLHttpReq;
	//XMLHttpReq = createXMLHttpRequest();
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	
	///////////////////////////////////////////////////////////
	function sendRequest(url,para) {

		createXMLHttpRequest();
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="sheng"){
			XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
	}
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		//alert(XMLHttpReq.status);
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
            	var res = XMLHttpReq.responseText;
              window.alert(res);
             
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    
function processResponse_sheng() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            	
            	//var res = XMLHttpReq.responseText;
            
              updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateQx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZd(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}

function changeSheng(){
	var sid = document.form1.sheng.value;
	
	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
	 //alert("11111");
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function changeCity(){
	var sid = document.form1.shi.value;
	
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}

function changeCountry(){
	var sid = document.form1.xian.value;

	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
}
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
document.form1.shi.value='<%=shi%>';
document.form1.xian.value='<%=xian%>';
document.form1.xiaoqu.value='<%=xiaoqu%>';
</script>