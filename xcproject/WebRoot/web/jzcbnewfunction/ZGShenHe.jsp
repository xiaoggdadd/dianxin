<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.electricfees.javabean.ElectricFeesFormBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.regex.Pattern"%>
<%@ page import="com.noki.newfunction.dao.*" %>
<%@ page import="com.noki.newfunction.javabean.*" %>
<%	
	String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginId1 = request.getParameter("loginId");
    String loginName = account.getAccountName();
    System.out.println("--------"+loginId+"======="+loginName+"=======");
	String beginTime1 = request.getParameter("beginTime1") != null ? request.getParameter("beginTime1"): "";
	String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"0";
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
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
		String property1 = request.getParameter("property")!=null?request.getParameter("property"):"0";
    String canshuStr="";
    String color=null;
    int intnum = 0;

    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    String shenhe = request.getParameter("shenhe")!=null?request.getParameter("shenhe"):"5";
   
    
%>

<html>
<head>
<title>

</title>
<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
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
		line-height:120%;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px ##888888 solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.bttcn{color:black;font-weight:bold;}
.form    {width:130px}

#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
 
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">

</script>

<script>

var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();

var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChsny.oBtnTodayTitle = "确定";
oCalendarChsny.oBtnCancelTitle = "取消";
oCalendarChsny.Init();
</script>

<script language="javascript">
	var path = '<%=path%>';
	function ShowHideSearchRegion(trObject,SelfObject){
		if(trObject.style.display == "none"){
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"
		}else{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
	}
    
    function queryDegree(){					
				document.form1.action=path+"/web/jzcbnewfunction/ZGShenHe.jsp?command=chaxun";
                document.form1.submit();
    }
	function lookDetailss(id,cid,sjshbz){ 
	 var path='<%=path%>';   
	 if(sjshbz=='1'){
	 	alert("该信息市级已审核,不允许修改！");
	 }else{
	 	window.open(path+"/web/jzcbnewfunction/ZGXX.jsp?id="+id+"&cid="+cid,'','width=1230,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
   	 }
	}
	function daochu(){
            var shi="<%=shi%>";
            var xian ="<%=xian%>";
            var beginTime1='<%=beginTime1%>';
            var zdname='<%=zdname%>';
            //alert(zdname);
        	document.form1.action=path+"/web/jzcbnewfunction/站点整改.jsp?shi="+shi+"&xian="+xian+"&beginTime1="+beginTime1+"&zdname="+zdname+"&command=daochu";
        	
            document.form1.submit();
   }
   	$(function(){
		$("#chaxun").click(function(){
			queryDegree();
			showdiv("请稍等..........");
		});	
		$("#chexiao").click(function(){
			chexiao();
		});
		$("#daochuBtn").click(function(){
			daochu();
		});
		
	});
</script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0804");

%>

<body  class="body" style="overflow-x:hidden;">
   <form action="" name="form1" method="POST">

		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr><td colspan="4">
			<div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点整改</span>	
			 </div>
	    	</td></tr>	    	
	    	<tr><td height="19" colspan="4">
   				<div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td></tr>
	    	<tr><td height="8%" width="1200">
	    		<table>
		    		<tr class="form_label">
		    		<td>城市：</td><td><select name="shi" class="selected_font" onchange="changeCity()">
	         		<option value="0">请选择</option>
	         		<%
			         	ArrayList shilist = new ArrayList();
			         	shilist = commBean.getAgcode(sheng,account.getAccountId());
			         	if(shilist!=null){
			         		String agcode="",agname="";
			         		int scount = ((Integer)shilist.get(0)).intValue();
			         		for(int i=scount;i<shilist.size()-1;i+=scount)
		                    {
		                    	agcode=(String)shilist.get(i+shilist.indexOf("AGCODE"));
		                    	agname=(String)shilist.get(i+shilist.indexOf("AGNAME"));
		                    %>
		                    <option value="<%=agcode%>" selected="selected"><%=agname%></option>
		                    <%}
			         	}
			         %>
	         		</select></td>
					
					
					<td>区县：</td><td> <select name="xian" class="selected_font" onchange="changeCountry()">
	         		<option value="0">请选择</option>
	         		 <%
			         	ArrayList xianlist = new ArrayList();
			         	xianlist = commBean.getAgcode(shi,account.getAccountId());
			         	if(xianlist!=null){
			         		String agcode="",agname="";
			         		int scount = ((Integer)xianlist.get(0)).intValue();
			         		for(int i=scount;i<xianlist.size()-1;i+=scount)
		                    {
		                    	agcode=(String)xianlist.get(i+xianlist.indexOf("AGCODE"));
		                    	agname=(String)xianlist.get(i+xianlist.indexOf("AGNAME"));
		                    %>
		                    <option value="<%=agcode%>"><%=agname%></option>
		                    <%}
			         	}
			         %>
	         		</select></td>
  					<td>对标月份：</td>
					<td><input type="text" name="beginTime1" value="<%if (null != request.getParameter("beginTime1")) out.print(request.getParameter("beginTime1"));%>" onFocus="getDatenyString(this,oCalendarChsny)"  class="selected_font" /></td>
					<td>市级审核状态：</td>
         						<td><select name="shenhe" class="selected_font">
         							<option value="5">请选择</option>
         							<option value="0">未审核</option>
         							<option value="1">审核通过</option>
         							<option value="2">审核不通过</option>
         							</select></td>
         							
					<td> <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
				             <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;left:70%;float:left;top:0px;right:-240px">
						       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
						       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
						     </div>
				        <%}%>		
					</td>
					</tr>
					<tr class="form_label">
					<td>站点名称：</td>
	         		<td><input type="text" class="selected_font" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" /></td>
	         		<td>站点属性：
								</td>
								<td>
									<select name="property" style="width: 130"
										class="selected_font">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList zdsx = new ArrayList();
											zdsx = ztcommon.getSelctOptions("zdsx");
											if (zdsx != null) {
												String code = "", name = "";
												int cscount = ((Integer) zdsx.get(0)).intValue();
												for (int i = cscount; i < zdsx.size() - 1; i += cscount) {
													code = (String) zdsx.get(i + zdsx.indexOf("CODE"));
													name = (String) zdsx.get(i + zdsx.indexOf("NAME"));
										%>
										<option value="<%=code%>"><%=name%></option>
										<%
											}
											}
										%>
									</select>
								</td>
					</tr>
	    		</table>
	    	</td></tr>		
		</table>
		
	<table>
			 <tr><td colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
              </div></td></tr>
		</table>
		
			<%	       
        		String whereStr = "";
		        Zdinfo zd = new Zdinfo();
					if(shi != null && !shi.equals("") && !shi.equals("0")){
						whereStr=whereStr+" AND zd.shi='"+shi+"'";
					}
					if(xian != null && !xian.equals("") && !xian.equals("0")){
						whereStr=whereStr+" AND zd.xian='"+xian+"'";
					}	
					if(beginTime1 != null && beginTime1 != "" && !beginTime1.equals("0")){
						whereStr=whereStr+" AND cc.cbsj='"+beginTime1+"'";
					}	
					if(shenhe!=null && shenhe!="" && !shenhe.equals("5")){
						whereStr=whereStr+" AND zz.SJSHBZ ='"+shenhe+"'";
					}
					if(property1 != null && property1 != "" && !property1.equals("0")){
						whereStr=whereStr+" AND zd.property='"+property1+"'";
					}
					if(zdname != null && !zdname.equals("")&& !zdname.equals("0")){
				whereStr=whereStr+" AND ZD.JZNAME LIKE '%"+zdname+"%'";				
			}
		if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;
       	 	shi="1";
       	 }
	%>
		
		
		<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px">
			<table width="100%" height="70%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
			    <tr height = "23" class="relativeTag">
			    <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
			    <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
			    <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点编号</div></td>
                <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属区域</div></td>
                <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超标月份</div></td> 
                <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超标比例</div></td>
                <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">分析原因</div></td>
                <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">测试人</div></td>
                <td width="11%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">整改要求</div></td>
                <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">完成时间</div></td>
                <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">操作</div></td>
            	<td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">状态</div></td>
			</tr>
				
		<%		         
		List<Zdinfo> fylist=null;
		     
       	 if("chaxun".equals(request.getParameter("command"))){
       		ZGShenHeDao dao2 = new ZGShenHeDao();
       		System.out.println("11234511");
 			 fylist = dao2.getZdinfo(whereStr);
        		System.out.println(fylist);

        } else{
           fylist=null;
        }
		   

			int countxh=1;
			String jzid="",al="";//站点id
			String jzname="";//站点名称
			String manualauditstatus="";
			String quxian="";//区县
			String xiaoq="";//小区
			String cbyf="";//超标月份
			String bili="";//超标比例
			//String csms="";//测试描述
			String fxyy="";//分析原因
			String zgyj="";//整改意见
			String wcsj="";//完成时间
			String tjzt="";//提交状态
			String cszrr="";//测试人
			String id="";//cbzd表主键id
			String cid="";//cbzdxx 主键id
			String sjshbz="";//市级审核标志
			if(fylist!=null){
			  for(Zdinfo z:fylist){
	             jzid=z.getZdid();
	             jzname=z.getZdname();
	             quxian=z.getXian();
	             xiaoq=z.getXiaoqu();
	             cbyf=z.getCbsj();
	             al=z.getCbbl();
	             //csms=z.getCsms();
	             fxyy=z.getYyfx();
	             zgyj=z.getXfzgyq();
	             wcsj=z.getWcsj();
	             tjzt=z.getQxtjsh();
	             sjshbz=z.getSjshbz();
	            // System.out.println("sssss"+tjzt);
	             if("1".equals(tjzt)){
	            	 
	            	 tjzt="已提交";
	             }else{
	            	 tjzt="未提交";
	             }
	             cszrr=z.getCszrr();
	             id=z.getId();
	             cid=z.getCid();
	             System.out.println("sss"+id+"ddd"+cid);
	             
	             if(null==al||"".equals(al)){
	            	 al="0";
	             }
	             
				 DecimalFormat mat=new DecimalFormat("0.00");
				al=mat.format(Double.parseDouble(al)*100)+"%";
				
				 if("null".equals(fxyy)||fxyy==null){
					 fxyy="";
				 }
				 if("null".equals(zgyj)||zgyj==null){
					 zgyj="";
				 }
				 if("null".equals(cszrr)||cszrr==null){
					 cszrr="";
				 }
				 if("null".equals(tjzt)||tjzt==null){
					 tjzt="0";
				 }
				 if("null".equals(wcsj)||wcsj==null){
					 wcsj="";
				 }
				 if(sjshbz==null||sjshbz.equals("")||sjshbz.equals("null")||sjshbz.equals(" ")||sjshbz==""){
					 sjshbz="0";
     			 } 
				
					 if(intnum%2==0){
						
					    color="#FFFFFF" ;
		
					 }else{
					    color="#DDDDDD";
					}
					 if("2".equals(sjshbz)){
						 color="yellow";
					 }
		           intnum++;
		
		       %>
		      
		       <tr bgcolor="<%=color%>" title="">
		       		<td>
		       			<div align="center" ><%=countxh++%></div>
		       		</td>
		       		 <td>
		              <div align="center"><input type="checkbox" name="zdid[]" value="<%=cid%>" /><input type="hidden" type="checkbox" name="test1[]" value="<%=sjshbz%>" /></div>
		            </td>
		       		<td>
		       			<div align="center" ><%=jzid %></div>
		       		</td>
					 <td>
		       			<div align="center" ><%=jzname %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=quxian %>,<%=xiaoq %></div>
		       		</td>	
		       		<td>
		       			<div align="center" ><%=cbyf %></div>
		       		</td>	       		
		       		<td>
		       			<div align="right" ><%=al%></div>
		       		</td>
		       			<td>
		       			<div align="center" ><%=fxyy %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=cszrr %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=zgyj %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=wcsj%></div>
		       		</td>		       		
					<td>
		       			<div align="center" ><a href="#" onclick="javascript:lookDetailss('<%=id%>','<%=cid %>','<%=sjshbz %>')" >执行整改</a></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=tjzt%></div>
		       		</td>
		       	  
		       </tr>
		     
		       <%} %>		      
		       <%}%>
		         <% if (intnum==0){
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
			    color="#DDDDDD";
            else
			    color="#FFFFFF" ;
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
			</table>
		</div>
		<table width="100%" height="8%" border="0" cellspacing="0" cellpadding="0">
			 <tr><td>
			  <input type="hidden" name="sheng2" id="sheng2" value=""/>
			  <input type="hidden" name="shi2" id="shi2" value=""/>
			  <input type="hidden" name="xian2" id="xian2" value=""/>
			  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
			  <input type="hidden" name="sptype2" id="sptype2" value=""/>
			  <input type="hidden" name="manualauditstatus2" id="manualauditstatus2" value=""/>
			 </td></tr>
		   	 <tr>
                    <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                        <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                    </div></td>
		    </tr>
		    <tr>
		    <td align="right">
		    <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
							 <img src="<%=path %>/images/daochu.png" width="100%" height="100%">
						 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
				  </div>    
				  <div id="chexiao" style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:16px">
						<img alt="" src="<%=request.getContextPath()%>/images/shangchuan.png" width="100%" height="100%" />
						<span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">撤销</span>
				  </div>   
				     	
		      </td></tr>		                         
		</table>
		
			
	</form>
	
</body>


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
      		document.form1.action=path+"/web/check/checkFeesManual.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/check/checkFeesManual.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/check/checkFeesManual.jsp?curpage="+pageno;
      document.form1.submit();
     }
     </script>
<script type="text/javascript">
<!--
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
	document.form1.shi2.value=document.form1.shi.value;
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
	 function chooseAll() { 
        var qm = document.getElementsByName('test');
        var m = document.getElementsByName('zdid[]');   
        var l = m.length; 
        if(qm[0].checked == true){
          for (var i = 0; i < l; i++) {   
            m[i].checked = true;   
          }  
        }else{
          for (var i = 0; i < l; i++) {   
            m[i].checked = false;  
          }   
        }        
    } 
	  function chexiao(){ //完成整改提交待审撤销
		var m = document.getElementsByName('zdid[]');
	  	var mm = document.getElementsByName('test1[]');
        var arr = new Array
        var l = m.length;
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var countct=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr = "";
       	var chooseIdStr1="";
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       		if(m[i].checked == true){
       			if(mm[i].value=='1'){
       				countct++;
       			}
       		}
       	}
       	if(countct!=0){
       		 alert("有信息市级已审核,不允许撤回！");
       	}else{
       	if(count!=0){
	       	if(count%240==0){
	       		n=count/240-1;
	       	}else{
	       		n=(count-(count%240))/240;
	       	}
	        for(var i = 0; i < l; i++){
	          if(m[i].checked == true){
	             bz+=1;
	             count1+=1;
	             chooseIdStr = chooseIdStr +"'" +m[i].value +"',"; 
	          }
       	
	          if(count1<=240*n||((bz+count2)>=239&&(bz+count2)<=241)){
	         	  if(((bz+count2)/240==1)||((bz+count2)>=239&&(bz+count2)<=241)){
			        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
			        //alert(chooseIdStr+"222");
			        document.form1.action=path+"/servlet/TuiDanServlet?action=SHchexiao&chooseIdStr="+chooseIdStr;
		            chooseIdStr = ""; 
	       			bz=0;
	       			count2=0;
		            document.form1.submit();	          	
		          }
		       }else if(count==count1&&bzw==1){
		          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
		          //alert(chooseIdStr+"111");
			      bzw=0;
		          document.form1.action=path+"/servlet/TuiDanServlet?action=SHchexiao&chooseIdStr="+chooseIdStr;
		          document.form1.submit(); 
		       }            
	        } 
        }else{
          alert("请选择信息！");
        }
       	}
      }
	 
    	document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.property.value='<%= property1%>';

//-->
</script>

</html>

