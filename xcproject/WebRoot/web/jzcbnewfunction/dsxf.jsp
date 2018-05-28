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
		String pch1 = request.getParameter("pch")!=null?request.getParameter("pch"):"";
		String gdh1= request.getParameter("gdh")!=null?request.getParameter("gdh"):"";
		String shzt1= request.getParameter("shzt")!=null?request.getParameter("shzt"):"-1";
		String shizt1= request.getParameter("shizt")!=null?request.getParameter("shizt"):"-1";
		String property1 = request.getParameter("property")!=null?request.getParameter("property"):"0";
    String canshuStr="";
    String color=null;
    int intnum = 0;

    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
   
    
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
				document.form1.action=path+"/web/jzcbnewfunction/dsxf.jsp?command=chaxun";
                document.form1.submit();
    }
	function lookDetailss(id){ 
		//alert("11111"+id);
	 var path='<%=path%>';   
	 window.open(path+"/web/jzcbnewfunction/shishxx.jsp?zdid="+id,'','width=1230,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
   
	
	}
   	$(function(){
		$("#chaxun").click(function(){
			//alert("1111111");
			queryDegree();
			//alert("1111111");
			//showdiv("请稍等..........");
		});
		$("#paidan").click(function(){
			paidan();
			
		});
		$("#chehui").click(function(){
			chehui();
			
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
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">市级整改派单</span>	
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
					<td> <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
				             <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;left:60%;float:left;top:0px;right:-440px">
						       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
						       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
						     </div>
				        <%}%>		
					</td>
					
					</tr>
						<tr class="form_label">
								<td>
									批次号：
								</td>
								<td>
									<input type="text" name="pch"
										value="<%if (null != request.getParameter("pch"))out.print(request.getParameter("pch"));%>" class="selected_font" />
								</td>
								<td>
									工单号：
								</td>
								<td>
									<input type="text" name="gdh"
										value="<%if (null != request.getParameter("gdh"))out.print(request.getParameter("gdh"));%>" class="selected_font" />
								</td>
							    <td>
									省审核状态：
								</td>
								<td>
								<select name="shzt"  class="selected_font" >
								<option value="-1">请选择</option>
								<option value="0">未下发</option>
								<option value="1">已下发</option>
								
								
								</select>
								
								
								</td>
							   <td>
									市审核状态：
								</td>
								<td>
								<select name="shizt"  class="selected_font" >
								<option value="-1">请选择</option>
								<option value="0">未下发</option>
								<option value="1">已下发</option>
								
								
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
						whereStr=whereStr+" AND z.shi='"+shi+"'";
					}
					if(xian != null && !xian.equals("") && !xian.equals("0")){
						whereStr=whereStr+" AND z.xian='"+xian+"'";
					}	
					if(beginTime1 != null && beginTime1 != "" && !beginTime1.equals("0")){
						whereStr=whereStr+" AND cc.cbsj='"+beginTime1+"'";
					}	
					if(pch1 != null && pch1 != "" && !pch1.equals("0")){
						whereStr=whereStr+" AND zz.yppch  like '%"+pch1+"%'";
					}
					if(gdh1 != null && gdh1 != "" && !gdh1.equals("0")){
					whereStr=whereStr+" AND zz.dgpch like '%"+gdh1+"%'";
				
					}
					if(shzt1 != null && shzt1 != "" && !shzt1.equals("-1")){
					whereStr=whereStr+" AND zz.SJXF ='"+shzt1+"'";
				
					}
					if(shizt1 != null && shizt1 != "" && !shizt1.equals("-1")){
						whereStr=whereStr+" AND ZZ.SHIJXF ='"+shizt1+"'";
					
						}
					if(property1 != null && property1 != "" && !property1.equals("0")){
						whereStr=whereStr+" AND z.property='"+property1+"'";
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
				        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
                		<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">标题</div></td>
						<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">批次号</div></td>
						<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">工单号</div></td>       
                        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点编号</div></td>
                        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属区域</div></td>
                         <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">对标月份</div></td> 
                        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超标比例</div></td>
                        <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">分析原因</div></td>
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">测试人</div></td>
                         <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">整改意见</div></td>
                        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">完成时间</div></td>
                        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">详细</div></td>
            			 <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">状态</div></td>
				</tr>
				
		<%		         
		List<Zdinfo> fylist=null;
		     
       	 if("chaxun".equals(request.getParameter("command"))){
       		ShiSignDao dao2 = new ShiSignDao();
       		System.out.println("11234511");
 			 fylist = dao2.getdsxf(whereStr,loginId);
        		System.out.println(fylist);

        } else{
           fylist=null;
        }
		   
				int countxh=1;
				String jzid="";//站点id
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
				String shijxf="";//提交状态
				String cszrr="";
				String id="";//cbzd表主键id

				String qxtjsh="";//区县提交审核标志

				String bt="";//标题
				String pch="";//批次号
				String gdh="";//工单号
				if(fylist!=null){
				  for(Zdinfo z:fylist){
					  
		             jzid = z.getZdid();
		             jzname = z.getZdname();
		             quxian = z.getXian();
		             xiaoq = z.getXiaoqu();
		             cbyf=z.getCbsj();
		             bili = z.getCbbl();
		            // csms = z.getCsms();
		             fxyy = z.getYyfx();
		             zgyj = z.getXfzgyq();
		             wcsj=z.getWcsj();
		             cszrr=z.getCszrr();
		             bt=z.getBt();
		             pch=z.getYppch();
		             gdh=z.getDgpch();
		             shijxf = z.getShijxf();
		             qxtjsh=z.getQxtjsh();
		             if("1".equals(shijxf)){
		            	 shijxf="已派单";
		             }else {
		            	 
		            	 
		            	 shijxf="未派单";
		             }
		             id=z.getId();
		             DecimalFormat price1=new DecimalFormat("0.00");
	                  if(null!=bili&&!"".equals(bili)){
	                	  bili=price1.format(Double.parseDouble(bili)*100)+"%";
	                	  
	                  }else{
	                	  
	                	  bili="";
	                  }
	                  if(wcsj==null||wcsj.equals("")||wcsj.equals("null")||wcsj.equals(" ")||wcsj==""){
	     				 wcsj="";
	     			 } 
	                  if(cszrr==null||cszrr.equals("")||cszrr.equals("null")||cszrr.equals(" ")||cszrr==""){
	     				 cszrr="";
	     			 } 
					 if("null".equals(fxyy)||fxyy==null){
						 fxyy="";
					 }
					 if("null".equals(zgyj)||zgyj==null){
						 zgyj="";
					 }

					 if(qxtjsh==null||qxtjsh.equals("")||qxtjsh.equals("null")||qxtjsh.equals(" ")||qxtjsh==""){
						 qxtjsh="0";
	     			 } 
					 System.out.println("99999"+qxtjsh+"999");

					 if("null".equals(bt)||bt==null){
						 bt="";
					 }
					 if("null".equals(pch)||pch==null){
						 pch="";
					 }
					  if("null".equals(gdh)||gdh==null){
						 gdh="";
					 }



					 if(intnum%2==0){
						
					    color="#FFFFFF" ;
		
					 }else{
					    color="#DDDDDD";
					}
		           intnum++;
		
		       %>
		      
		       <tr bgcolor="<%=color%>" title="">
		       		<td>
		       			<div align="center" ><%=countxh++%></div>
		       		</td>
		            <td>
		              <div align="center"><input type="checkbox" name="zdid[]" value="<%=id%>" /><input type="hidden" type="checkbox" name="test1[]" value="<%=qxtjsh %>" /></div>
		            </td>
		            <td>
		       			<div align="center" ><%=bt %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=pch%></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=gdh %></div>
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
		       			<div align="right" ><%=bili %></div>
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
		       			<div align="center" ><%=wcsj %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><a href="javascript:lookDetailss('<%=id%>')">查看详细</a></div>
		       		</td>	
		       <td>
		       			<div align="center" ><%=shijxf %></div>
		       			
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
            <td>&nbsp;</td>                   
            <td>&nbsp;</td> 
             <td>&nbsp;</td> 
             
        </tr>
        <% }}%>
			</table>
		</div>
		<table width="100%" height="8%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		<td>
			<div id="paidan"
						style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:10px">
						<img alt=""
							src="<%=request.getContextPath()%>/images/baocun.png"
							width="100%" height="100%" />
						<span
							style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">派单</span></div>
							
							 <div id="chehui"
						style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:15px">
						<img alt=""
							src="<%=request.getContextPath()%>/images/baocun.png"
							width="100%" height="100%" />
						<span
							style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">撤回</span>
				  </div>
		</td>
		</tr>
		
		</table>
	                	<input type="hidden" name="sheng2" id="sheng2" value="" />
						<input type="hidden" name="shi2" id="shi2" value="" />
						<input type="hidden" name="xian2" id="xian2" value="" />
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
 function paidan(){
    	var m = document.getElementsByName('zdid[]');
    	var mm = document.getElementsByName('test1[]');
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
        var countct=0;
       	var chooseIdStr = ""; 
       	var month = mm[0].value;
       	//alert(month);
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
       		 alert("有信息已整改,请确认！");
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
	         // alert(chooseIdStr);
	          if(count1<=240*n||((bz+count2)>=239&&(bz+count2)<=241)){
	         	  if(((bz+count2)/240==1)||((bz+count2)>=239&&(bz+count2)<=241)){
			        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
			        alert(chooseIdStr);
			        document.form1.action=path+"/servlet/PDServlet?action=paidan1&chooseIdStr="+chooseIdStr;
		            chooseIdStr = ""; 
	       			bz=0;
	       			count2=0;
		            document.form1.submit();	          	
		          }
		       }else if(count==count1&&bzw==1){
		          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
			      bzw=0;
		          document.form1.action=path+"/servlet/PDServlet?action=paidan1&chooseIdStr="+chooseIdStr;
		          document.form1.submit(); 
		       }            
	        } 
        }else{
          alert("请选择信息！");
        }
      }
    }
  function chehui(){
    	var m = document.getElementsByName('zdid[]');
    	var mm = document.getElementsByName('test1[]');
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
        var countct=0;
       	var chooseIdStr = ""; 
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
       		 alert("有信息已整改,不允许撤回！");
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
	         // alert(chooseIdStr);
	          if(count1<=240*n||((bz+count2)>=239&&(bz+count2)<=241)){
	         	  if(((bz+count2)/240==1)||((bz+count2)>=239&&(bz+count2)<=241)){
			        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
			        alert(chooseIdStr);
			        document.form1.action=path+"/servlet/PDServlet?action=chehui&chooseIdStr="+chooseIdStr;
		            chooseIdStr = ""; 
	       			bz=0;
	       			count2=0;
		            document.form1.submit();	          	
		          }
		       }else if(count==count1&&bzw==1){
		          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
			      bzw=0;
		          document.form1.action=path+"/servlet/PDServlet?action=chehui&chooseIdStr="+chooseIdStr;
		          document.form1.submit(); 
		       }            
	        } 
        }else{
          alert("请选择信息！");
        }
      }
    }

//-->
</script>
  <!--多选框选择 -->
 <script type="text/javascript">
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
    
    	document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.shzt.value = '<%=shzt1%>';
		document.form1.shizt.value = '<%=shizt1%>';
		document.form1.property.value='<%= property1%>';
 </script>

</html>

