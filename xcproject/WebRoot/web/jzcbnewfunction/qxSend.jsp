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
    String manualauditstatus="";
    String loginName = account.getAccountName();
   // System.out.println("--------"+loginId+"======="+loginName+"=======");
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
		String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
		//String bt=request.getParameter("bt")!=null?request.getParameter("bt"):"0";
		String yppch=request.getParameter("yppch")!=null?request.getParameter("yppch"):"";
		String shenhe=request.getParameter("shenhe")!=null?request.getParameter("shenhe"):"2";
		String zlch=request.getParameter("zlch")!=null?request.getParameter("zlch"):"";
		String bzw1="";
		String property1 = request.getParameter("property")!=null?request.getParameter("property"):"0";

    String canshuStr="";
    String color=null;
    int intnum = 0;

    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
   
    String bz = request.getParameter("bz")!=null?request.getParameter("bz"):"0";
    int s;
    XianSignDao dao = new XianSignDao();//查询是否有可签收的派单
    s = dao.PDChenk(loginId);
    if("1".equals(bz)){
     	XianSignDao dao1 = new XianSignDao();//更改为区县已签收
     	s = dao1.PDUpdate(loginId,loginName);
    }
    
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
		width:90px;
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

	function qianshou(){
		document.form1.action=path+"/web/jzcbnewfunction/qxSend.jsp?bz=1"
        document.form1.submit()
	}
    
    function queryDegree(){					
			document.form1.action=path+"/web/jzcbnewfunction/qxSend.jsp?command=chaxun";
            document.form1.submit();
    }

	function lookDetailss(jzid,cbyf){ 
	 		var path='<%=path%>';   
			 window.open(path+"/web/jzcbnewfunction/showdfxx.jsp?zdid="+jzid+"&cbyf="+cbyf,'','width=1230,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
    }
	
    function modifyjz(id){
    		var path='<%=path%>';
     		document.form1.action=path+"/servlet/cbzdfjxiazai?id="+id+"&bzw=1";
     		document.form1.submit();
    }
 	function lookDetails(zdcode){ 
 		    var path = '<%=path%>';
	  		window.open(path+"/web/query/caijipoint/shenhemodifsite.jsp?id="+zdcode,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
    }
   	$(function(){
		$("#tuidan").click(function(){
			tuidan();
		});
		$("#paidan").click(function(){
			paidan();
		});
		$("#chaxun").click(function(){
			queryDegree();
			showdiv("请稍等..........");
		});
		$("#qianshou").click(function(){
			qianshou();
		});	
		$("#chexiao").click(function(){
			chexiao();
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
<% if(s==1){%>
 	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
 	 	<tr>
 	 		<td align="left" height="100px"><div id="qianshou" style="position:relative;width:70px;height:23px;cursor: pointer;left:25%;float:left;top:0px;right:-240px">
				<img alt="" src="<%=path %>/images/baocun.png" width="100%" height="100%" /><span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">签收</span></div>
			</td>
		</tr>
  		<tr>
    		<td width="10"><img src="../images/img_04.gif" width="12" height="37" /></td>
    		<td background="../images/img_05.gif">&nbsp;</td>
    		<td width="12"><img src="../images/img_06.gif" width="12" height="37" /></td>
  		</tr>
  		<tr>
    		<td width="10" height="300px" background="../images/img_10.gif">&nbsp;</td>
    		<td valign="top" ><br/>
     		 <br/>    
       			<table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
     			 <tr>
        			<td colspan="3">
        				<table width="100%"  border="0" cellspacing="0" cellpadding="0">
          					<tr>
            	 				<td>
            						<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" bgcolor="7C92B7">
              							<tr>
                							<td height="49" bgcolor="#FFFFFF">
                								<table width="100%" border="0" cellspacing="1" cellpadding="1">
                 									<tr>
                    									<td height="29" colspan="2">
                    										<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    										<tr><td height="29" background="../images/bei.gif"><img src="../images/v.gif" width="8" height="9" />　　　　　　　　　　　　　　　　　　　　　　　　　<span class="style7">提示信息！</span></td></tr>
                        									</table>
                    									</td>
                  									</tr>
                 			 						<tr>
                   						 				<td width="42%" height="87" bgcolor="#FFFFFF"><div align="center"><img src="../images/2.gif" width="133" height="134" /></div></td>
                    					 				<td width="58%" bgcolor="#FFFFFF"><p>请签收需要整改的站点</p></td>
                  									</tr>
                  									<tr><td colspan="2" bgcolor="F2F9FF">&nbsp;</td></tr>
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
    	  </td>
    	   <td background="../images/img_13.gif">&nbsp;</td>
  	   </tr>
  	   <tr>
    	 <td><img src="../images/img_23.gif" width="12" height="19" /></td>
    	 <td background="../images/img_24.gif">&nbsp;</td>
    	 <td><img src="../images/img_25.gif" width="12" height="19" /></td>
  	   </tr>
    </table>
<% }else{%>
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr><td colspan="4">
			<div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">区县签收</span>	
			 </div>
	    	</td></tr>	    	
	    	<tr><td height="19" colspan="4">
   				<div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td></tr>
	    	<tr><td height="8%" width="1500">
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
  					<td>对标月份：</td>
					<td><input type="text" name="beginTime1" value="<%if (null != request.getParameter("beginTime1")) out.print(request.getParameter("beginTime1"));%>" onFocus="getDatenyString(this,oCalendarChsny)"  class="selected_font" /></td>
					</tr>
					<tr class="form_label">
					<td>批次号：</td>
        		 	<td><input type="text" name="yppch" value="" /></td>
        		 	<td>工单号：</td>
        		 	<td><input type="text" name="zlch" value=""  /></td>
        		 	<td>审核状态：</td>
         			<td><select name="shenhe"  class="selected_font">
         							<option value="2">请选择</option>
         							<option value="0">未派单</option>
         							<option value="1">已派单</option>
         			</select></td>
         			
         			
					<td> <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
				             <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;left:500%;float:left;top:0px;right:-240px">
						       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
						       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
						     </div>
				        <%}%>		
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
				
					if(yppch != null && yppch != "" && !yppch.equals("0")){
						whereStr=whereStr+" AND zz.yppch='"+yppch+"'";
					}
					if(shenhe != null && shenhe != "" && !shenhe.equals("2")){
						whereStr=whereStr+" AND cc.qxpd='"+shenhe+"'";
					}
					if(zlch != null && zlch != "" && !zlch.equals("0")){
						whereStr=whereStr+" AND zz.Dgpch='"+zlch+"'";
					}
					if(property1 != null && property1 != "" && !property1.equals("0")){
						whereStr=whereStr+" AND zd.property='"+property1+"'";
					}
					
		if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;
       	 	shi="1";
       	 }
	%>
		
		
		<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px">
			<table width="1800px" height="70%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
				<tr height = "23" class="relativeTag">
						<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
				        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
				        <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">标题</div></td>
				        <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点批次号</div></td>
				        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">工单号</div></td>
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点编号</div></td>
                        
                        <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                        <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属区域</div></td>
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超标月份</div></td>
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">省定标</div></td> 
                        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超标比例</div></td>
                        <td width="15%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">整改要求说明</div></td>
                        <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">附件</div></td>
                         
                        <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入人</div></td>
                        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入时间</div></td>
            			<td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">状态</div></td>
				</tr>
				
		<%		         
		List<Zdinfo> fylist=null;
		     
       	 if("chaxun".equals(request.getParameter("command"))){
       		XianSignDao dao2 = new XianSignDao();
       		//System.out.println("11234511");
 			 fylist = dao2.getZdinfo(whereStr);
 			//System.out.println("----------------------");
        		//System.out.println(fylist);

        } else{
           fylist=null;
        }
		   
				int countxh=1;
				String jzid="";//站点id
				String jzname="";//站点名称
			    String qxtjsh="";//区县提交审核 标志位 
				String quxian="";//区县
				String xiaoq="";//小区
				String cbyf="";//超标月份
				String bili="";//超标比例
				String zgyq="";//整改要求
				String lch="";//流程号
				String qxlrr="";//区县录入人
				String qxlrsj="";//区县录入时间
				String qxqs="";//区县上传zhuangtai
				String id="";//cbzd表主键id
				String sjname="";//省级名称
				String zdcode="";
				String zbt="";
				String zyppch="";
				String nhbz="";//省定标
				if(fylist!=null){
				  for(Zdinfo z:fylist){
		             jzid=z.getZdid();
		             jzname=z.getZdname();
		   			 quxian=z.getXian();
					 xiaoq=z.getXiaoqu();
					 cbyf=z.getCbsj();
					 bili=z.getCbbl();
					 zgyq=z.getZgyq();
					 lch=z.getLch();
					 qxlrr=z.getQxlrr();
					 qxlrsj=z.getQxlrsj();
					 qxqs=z.getQxpd();
					 id=z.getId();
					 zdcode=z.getIdd();
					 zbt=z.getBt();
					 zyppch=z.getYppch();
					 sjname=z.getSjname();
					 qxtjsh=z.getQxtjsh();
					 nhbz=z.getNhbz();
					 if(null==nhbz||" ".equals(nhbz)||"".equals(nhbz)||"null".equals(nhbz)){
						 nhbz="";
					 }
					 if(null==qxtjsh||"".equals(qxtjsh)||"null".equals(qxtjsh)){
					 qxtjsh="0";
					 }
					 if(null==sjname||"".equals(sjname)||"null".equals(sjname)){sjname="";}
					// System.out.println("sjname:"+sjname);
					 DecimalFormat mat=new DecimalFormat("0.00");
					 if(null==bili||"".equals(bili)||"null".equals(bili)){
						bili="0";
					  }else{
						bili=mat.format(Double.parseDouble(bili)*100)+"%";
					        }
						
					
					 if(qxqs==null||qxqs.equals("")||qxqs.equals("null")||qxqs.equals(" ")||qxqs==""){
						 qxqs="0";
					 }
					 if("null".equals(lch)||lch==null){
						lch="";
					 }
					 if("null".equals(qxlrr)||qxlrr==null){
						 qxlrr="";
					 }
					 if("null".equals(qxlrsj)||qxlrsj==null){
						 qxlrsj="";
					 }else{
						 qxlrsj=qxlrsj.substring(0,10);
					 }

					 if("null".equals(zgyq)||zgyq==null){
						 zgyq="";
					 }
					 if("null".equals(zbt)||zbt==null){
						 zbt="";
					 }
					 if("null".equals(zyppch)||zyppch==null){
						 zyppch="";
					 }
					 if(qxqs!=null&&!qxqs.equals("")&&!qxqs.equals("null")){
					 manualauditstatus=qxqs.trim();
					 }
					 if("1".equals(manualauditstatus)){
						 manualauditstatus="已派单";
					 }else if("0".equals(manualauditstatus)){
						 manualauditstatus="未派单";
					 }else if("2".equals(manualauditstatus)){
						 manualauditstatus="退单";
					 }
					// System.out.println("jzid"+jzid+"city"+quxian+"quxian"+xiaoq+"zdcode"+zdcode+"~~~~~~");
					// System.out.println(manualauditstatus.trim().equals("0"));
                  bzw1=z.getQxpd();
                  if(null==bzw1||"".equals(bzw1)||"null".equals(bzw1)){
                    bzw1="0";
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
		              <div align="center">
		              <input type="checkbox" name="zdid[]" value="<%=id%>" />
		              <input type="hidden" type="checkbox" name="zdid1[]" value="<%=bzw1%>" />
		              <input type="hidden" type="checkbox" name="zdid2[]" value="<%=qxtjsh%>" />
		              </div>
		            </td>
		            <td>
		       			<div align="center" ><%=zbt %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=zyppch %></div>
		       		</td>
		            <td>
		       			<div align="center" ><%=lch %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=jzid %></div>
		       		</td>
		       		
					 <td>
		       			<div align="center" ><a href="javascript:lookDetails('<%=zdcode%>')"><%=jzname %></a></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=quxian %>,<%=xiaoq %></div>
		       		</td>	
		       		<td>
		       			<div align="center" ><%=cbyf %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=nhbz %></div>
		       		</td>	       		
		       		<td>
		       			<div align="right" ><a href="javascript:lookDetailss('<%=jzid%>','<%=cbyf %>')"><%=bili %></a></div>
		       		</td>
		       			<td>
		       			<div align="center" ><%=zgyq %></div>
		       		</td>
		       		<%if(sjname!=null&&!"".equals(sjname)&&!"null".equals(sjname)){%>
		       		<td>
		       			<div align="center" ><a href="#" onclick="modifyjz('<%=id %>')" >下载</a></div>
		       		</td>
		       		<%}else{%>
		       		<td>
		       			<div align="center" >无</div>
		       		</td>
		       		<%}%>
		       		<td>
		       			<div align="center" ><%=qxlrr %></div>
		       		</td>
		       		<td>
		       			<div align="center" ><%=qxlrsj %></div>
		       		</td>		       		

		       		<td>
		       			<div align="center" ><%=manualauditstatus %></div>
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
		    <tr><td align="right">
		      	  <div id="tuidan" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
							 <img src="<%=path %>/images/mmcz.png" width="100%" height="100%">
						 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">退单</span>      
				  </div>  
		          <div id="paidan" style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:10px">
						<img alt="" src="<%=request.getContextPath()%>/images/shangchuan.png" width="100%" height="100%" />
						<span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">派单</span>
				  </div>
				  
				  <div id="chexiao" style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:16px">
						<img alt="" src="<%=request.getContextPath()%>/images/shangchuan.png" width="100%" height="100%" />
						<span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">撤销</span>
				  </div>       	
		      </td></tr>		                         
		</table>
		<%} %>		
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


//-->
</script>
<script type="text/javascript">
	function tuidan(){ //区县退单
		var m = document.getElementsByName('zdid[]');
			var mc = document.getElementsByName('zdid2[]');
        var arr = new Array
        var l = m.length;
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr = "";
       	var comt=0;
       	var chooseIdStr1="";
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       		if(m[i].checked == true){
       			if(mc[i].value=='1'){
       			comt++;
       			}
       		}
       	}if(comt!=0){
       	alert("区县已核实不允许此操作！");
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
			       // alert(chooseIdStr+"222");
			        document.form1.action=path+"/servlet/TuiDanServlet?action=tuidan&chooseIdStr="+chooseIdStr;
		            chooseIdStr = ""; 
	       			bz=0;
	       			count2=0;
		            document.form1.submit();	          	
		          }
		       }else if(count==count1&&bzw==1){
		          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
		          //alert(chooseIdStr+"111");
			      bzw=0;
		          document.form1.action=path+"/servlet/TuiDanServlet?action=tuidan&chooseIdStr="+chooseIdStr;
		          document.form1.submit(); 
		       }            
	        } 
        }else{
          alert("请选择信息！");
        }
        }
      }
   function paidan(){
  
	  var m = document.getElementsByName('zdid[]');
	  var mm = document.getElementsByName('zdid1[]');
	  var mmc = document.getElementsByName('zdid2[]');
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
       	var chooseIdStr1="";
       	var cnn=0;
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       		if(m[i].checked == true){
       			if(mm[i].value=='1'){
       				countct++;
       			}
       		}
       		if(m[i].checked == true){
       			if(mmc[i].value=='1'){
       				cnn++;
       			}
       		}
       	}if(cnn!=0){
       	alert("区县已核实不允许此操作！");
       	return;
       	}else{
       	if(countct!=0){
       	alert("选择的信息已派过单！请确认！");
       	return;
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
			        document.form1.action=path+"/servlet/TuiDanServlet?action=paidan&chooseIdStr="+chooseIdStr;
		            chooseIdStr = ""; 
	       			bz=0;
	       			count2=0;
		            document.form1.submit();	          	
		          }
		       }else if(count==count1&&bzw==1){
		          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
		          //alert(chooseIdStr+"111");
			      bzw=0;
		          document.form1.action=path+"/servlet/TuiDanServlet?action=paidan&chooseIdStr="+chooseIdStr;
		          document.form1.submit(); 
		       }            
	        } 
        }else{
          alert("请选择信息！");
        }
        }
        }
     }
   function chexiao(){ //区县派单撤销
		var m = document.getElementsByName('zdid[]');
		var mqx = document.getElementsByName('zdid2[]');
        var arr = new Array
        var l = m.length;
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr = "";
       	var chooseIdStr1="";
       	var cn=0;
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       		if(m[i].checked == true){
       			if(mqx[i].value=='1'){
       			cn++;
       			}
       		}
       	}if(cn!=0){
       	alert("区县已核实不允许此操作！");
       	return;
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
			       // alert(chooseIdStr+"222");
			        document.form1.action=path+"/servlet/TuiDanServlet?action=chexiao&chooseIdStr="+chooseIdStr;
		            chooseIdStr = ""; 
	       			bz=0;
	       			count2=0;
		            document.form1.submit();	          	
		          }
		       }else if(count==count1&&bzw==1){
		          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
		          //alert(chooseIdStr+"111");
			      bzw=0;
		          document.form1.action=path+"/servlet/TuiDanServlet?action=chexiao&chooseIdStr="+chooseIdStr;
		          document.form1.submit(); 
		       }            
	        } 
        }else{
          alert("请选择信息！");
        }
        }
      }
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
		document.form1.yppch.value='<%= yppch%>';
		document.form1.shenhe.value='<%= shenhe%>';
		document.form1.zlch.value='<%= zlch%>';
		document.form1.property.value='<%= property1%>';
 </script>

</html>

