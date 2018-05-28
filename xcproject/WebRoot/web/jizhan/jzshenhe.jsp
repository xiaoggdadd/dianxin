<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%
        String path = request.getContextPath();
        String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
        String accountname=account.getAccountName();
        String loginId=account.getAccountId();
		String sheng = (String)session.getAttribute("accountSheng");
		String roleId = (String)session.getAttribute("accountRole");
		
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
    	String shi="",xian ="",xiaoqu="",sname = "",szdcode ="",stationtype1 ="",jzproperty1 ="",jztype1 ="",lrrq="";
    	
 	String bzwsh = request.getParameter("bzwsh");
    	if("2".equals(bzwsh)){   //有可能修改了传过去的查询条件
    		shi = request.getParameter("shi2");
    		xian = request.getParameter("xian2");
    		xiaoqu = request.getParameter("xiaoqu2");
    		sname = request.getParameter("sname2");
    		szdcode = request.getParameter("szdcode2");
    		stationtype1 = request.getParameter("stationtype2");
    		jzproperty1 = request.getParameter("jzproperty2");
    		jztype1 = request.getParameter("jztype2");
    		lrrq = request.getParameter("lrrq2");
    	
    	
    	}else{
    	
        shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;
        xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
        xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
       //System.out.println("logManage.jsp>>"+beginTime); 
		sname = request.getParameter("sname")!=null?request.getParameter("sname"):"";
		szdcode = request.getParameter("szdcode")!=null?request.getParameter("szdcode"):"";
		stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
		jzproperty1 = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
		jztype1 = request.getParameter("jztype")!=null?request.getParameter("jztype"):"0";
		lrrq = request.getParameter("lrrq")!=null?request.getParameter("lrrq"):"";
       }
		String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
		int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
	          curpage=Integer.parseInt(s_curpage);
	          String permissionRights="";
	          String color=null;
          
%>

<html>
<head>
<title>
站点列表
</title>
<style>
.style1 {
	color: #FF9900;
	font-weight: bold;
}
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
		line-height:120%;
}.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
 .btt{ bgcolor:#888888;}
  .bttcn{ color:black;font-weight:bold;}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
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
					document.form1.action=path+"/web/jizhan/jzshenhe.jsp?command=chaxun";
					document.form1.submit();
					showdiv("请稍等..............");
	}
	function delLogs(){
		

	}
	 function lookDetails(zdcode){ 
	
	  var shi ='<%=shi%>';
	  var xian='<%=xian%>';
	  var xiaoqu='<%=xiaoqu%>';
	  var sname='<%=sname%>';//站点名称
	  var szdcode='<%=szdcode%>';//站点代码
	  var stationtype='<%=stationtype1%>';//站点类型
	  var jzproperty='<%=jzproperty1%>';//站点属性
	  var jztype='<%=jztype1%>';//集团报表类型
	  var lrrq='<%=lrrq%>';//录入日期
	 var path='<%=path%>';   	
    	//window.open(path+"/web/jizhan/shenhemodifsite.jsp?id="+zdcode,'','width=1100,height=600px,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
       document.form1.action=path+"/web/jizhan/shenhemodifsite.jsp?shi1="+shi+"&xian1="+xian+"&xiaoqu1="+xiaoqu+"&sname1="+sname+"&szdcode1="+szdcode+"&stationtype1="+stationtype+"&jzproperty1="+jzproperty+"&jztype1="+jztype+"&lrrq1="+lrrq+"&id="+zdcode;
      document.form1.submit();
    }
	
	
	function delsj(shsign){
     
     		var i = 0;

				for(var j = 0;j < document.form1.elements.length ; j++){
					if (document.form1.elements[j].checked){
						i++;
					}
				}

				if(i>0){
					
		       document.form1.action=path+"/servlet/zhandian?action=shenhe&shsign="+shsign;
		       document.form1.submit();
		       showdiv("请稍等..............");
		                       
				}else{
					alert("请选择要审核的站点");
					return;
				}
     
    }

    $(function(){
		 $("#daochu").click(function(){
			exportad();
		});

		$("#tongguo").click(function(){
			delsj('1');
		});

		$("#butongguo").click(function(){
			delsj('2');
		});                                                                                                                                                                                
		$("#chaxun").click(function(){
			chaxun();
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
%>

<body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0" >

 <tr>
					    	<td colspan="5" width="50">
												 <div style="width:700px;height:50px">
											<!--   --> 
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点审核信息列表</span>	
												</div>
												<input type="hidden" name="accountname" value="<%=accountname %>"/>
											</td>
					    	
	    	        </tr>	   
	    	        <tr><td height="20" colspan="5" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	    	</td>
	    	</tr>
	    	     <tr>
	    	     <td width="1200px">
	    	     <table>
	    	     <tr class="form_label"> 
	    	      <td >城市：</td>
         
         <td class="form_label">
         <select name="shi" id="shi" class="selected_font" onchange="changeCity()">
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
                    <option value="<%=agcode%>" selected="selected"><%=agname%></option>
                    <%}
	         	}
	         %>
         		</select></td>
        <td class="form_label">区县：</td>
        <td >
         <select name="xian" id="xian" class="selected_font" onchange="changeCountry()">
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
         	<td >乡镇：</td>
         	<td>
         	<select name="xiaoqu" id="xiaoqu" class="selected_font">
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
				<td></td>
				
	         		<td>
							       <div id="chaxun" style="position:relative;width:59px;height:23px;right:-190px;cursor: pointer;TOP:0PX">
									<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
							</td>
						
	    	        </tr>
	    	        </table>
	    	        </td>
	    	        </tr>
	    	        </table>
	    	        
	    	        
	    	        
	    	        
	    	        
	    	        
	    	        <div style="width:90%;" > 
					  <p id="box3" style="display:none">
					<table  class="form_label" >
				<tr class="form_label">
         			<td class="form_label">站点名称：</td>
         			<td >
         			<input class="selected_font"  type="text" size="16" name="sname" value="<%=sname%>"/>
                  </td>
                    <td >	
         站点ＩＤ：</td>
         <td>
         <input class="selected_font" type="text" size="16" name="szdcode" value="<%=szdcode%>"/>
         </td>
         	 <td>录入日期：</td><td><input type="text" name="lrrq" value="<%=lrrq%>" 
         								readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" 
         								 class="selected_font"/></td>
        </tr>
  </table>
  </p>
  </div>


			

<table  height="23">
<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>
<% 
JiZhanBean bean = new JiZhanBean();

		
			//String count1=bean.
			String str1=" and z.SHSIGN='1'";
			String str2=" and z.SHSIGN='0'";
			String str3=" and z.SHSIGN='2'";
			
		  	 //更改2012-12-5
        String count1="0";
	    String count2="0";
	    String count3="0";
	    String count4="0";
       	if("chaxun".equals(request.getParameter("command"))||"daochu".equals(request.getParameter("command"))){
            count1=bean.getCountt(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,lrrq);
            count2=bean.getCountt2(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,str1,lrrq);
            count3=bean.getCountt2(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,str2,lrrq);
            count4=bean.getCountt2(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,str3,lrrq);        
		//String count1=bean.getCountt(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,lrrq);
		//String count2=bean.getCountt2(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,str1,lrrq);
		//String count3=bean.getCountt2(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,str2,lrrq);
		//String count4=bean.getCountt2(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,str3,lrrq);
		if(count1==null||count1==""||count1.equals("")||count1.equals("null")){
		   count1="0";
		}
		if(count2==null||count2==""||count2.equals("")||count2.equals("null")){
		   count2="0";
		}
		if(count3==null||count3==""||count3.equals("")||count3.equals("null")){
		   count3="0";
		}
		if(count4==null||count4==""||count4.equals("")||count4.equals("null")){
		   count4="0";
		}
		%>
		
		<%} %>
			
					 
     <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >   
  		<table width="1500px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
            	<tr height = "10" class="relativeTag" >
             			<td width="3%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
             			<td width="4%" height="20"  bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="pageCheck" onClick="checkPage()" />全选</div></td>                        
                         <td width="10%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                        <!-- <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">单价</div></td>   -->                     
                        <td width="11%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
                        
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
                        <td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
						<!-- <td width="5%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td> --> 
						<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">用房类型</div></td>						
            			<!--<td width="4%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">节能设备</div></td> --> 
            			
                        <td width="5%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">供电方式</div></td>                        
                      	<td width="5%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点面积</div></td>
                        <!--<td width="5%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">虚拟站点</div></td>--> 
                        <td width="5%" height="20" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>                                
                       
            </tr>
       <%
         
       	 ArrayList fylist = new ArrayList();
       	//更改2012-12-5
       	if("chaxun".equals(request.getParameter("command"))||"daochu".equals(request.getParameter("command"))){
            fylist=null; //= bean.getPageData1(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,lrrq);
       	    allpage=bean.getAllPage();
        } else{
            fylist=null;
        }
       	 //fylist = bean.getPageData1(sheng,shi,xian,xiaoqu,sname,szdcode,loginName,jzproperty1,jztype1,stationtype1,"3",loginId,lrrq);
       	 //allpage=bean.getAllPage();
		String jzname = "",szdq = "",jzproperty = "", jztype= "",stationtype2= "",yflx = "",jnglmk="",gdfs="",area="",id="",dianfei="",zdcode="",xunisign="",shsignxs="",provauditstatus="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
				id = (String)fylist.get(k+fylist.indexOf("ID"));
				jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
				jzname = jzname != null ? jzname : "";
		    	szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
				szdq = szdq != null ? szdq : "";
		    	jzproperty = (String)fylist.get(k+fylist.indexOf("PROPERTY"));
				jzproperty = jzproperty != null ? jzproperty : "";
		    	jztype = (String)fylist.get(k+fylist.indexOf("JZTYPE"));
				jztype = jztype != null ? jztype : "";
		    	//添加站点类型
		    	stationtype2 = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
				stationtype2 = stationtype2 != null ? stationtype2 : "";
				yflx = (String)fylist.get(k+fylist.indexOf("YFLX"));
				yflx = yflx != null ? yflx : "";
				jnglmk = (String)fylist.get(k+fylist.indexOf("JNGLMK"));
				jnglmk = jnglmk != null ? jnglmk : "";
				
				if("1".equals(jnglmk)){
				 jnglmk="有";
				}else{
				 jnglmk="无";
				}
				
				gdfs = (String)fylist.get(k+fylist.indexOf("GDFS"));
				gdfs = gdfs != null ? gdfs : "";
				area = (String)fylist.get(k+fylist.indexOf("AREA"));
				area = area != null ? area : "";
				dianfei = (String)fylist.get(k+fylist.indexOf("DIANFEI"));
				dianfei = dianfei != null ? dianfei : "0";
				if(dianfei.equals("null")||dianfei.equals("")){
				dianfei="0";
				}
				zdcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
				zdcode = zdcode != null ? zdcode : "";
				xunisign = (String)fylist.get(k+fylist.indexOf("XUNISIGN"));
				xunisign = xunisign != null ? xunisign : "";
				shsignxs = (String)fylist.get(k+fylist.indexOf("SHSIGN"));
				shsignxs = shsignxs != null ? shsignxs : "";
				DecimalFormat price = new DecimalFormat("0.0000");
			dianfei = price.format(Double.parseDouble(dianfei));
			
			provauditstatus = (String)fylist.get(k+fylist.indexOf("PROVAUDITSTATUS"));
			provauditstatus = provauditstatus != null ? provauditstatus : "";

			if(intnum%2==0){
			    color="#FFFFFF" ;

			 }else{
			    color="#DDDDDD";
			}
			if(shsignxs.equals("0")){
				color="#FFFF33" ;
			}else if(shsignxs.equals("2")){
				color="#FF3333" ;
			}else if(provauditstatus.equals("2")){
				color="#00CED1" ;
			}

       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=intnum++%></div>
       		</td>
       		<td>
       		<div align="center"  >
       			<input type="checkbox" name="itemSelected" value="<%=id%>"/>
       			</div>
       		</td>
       		<td>
       			<div align="left"  ><a href="javascript:lookDetails('<%=id%>')"><%=jzname%></a></div>
       		</td>
       		<!-- <td>
       			<div align="right"  ><%=dianfei%></div>
       		</td>
       		--> 
       		<td>
       			<div align="left"  ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=stationtype2%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=jzproperty%></div>
       		</td>
       		<!-- <td>
       			<div align="center"  ><%=jztype%></div>
       		</td>--> 
       		<td>
       			<div align="center"  ><%=yflx%></div>
       		</td>
       <!-- 		<td>
       			<div align="center"  ><%=jnglmk%></div>
       		</td>
       		-->
       		<td>
       			<div align="center"  ><%=gdfs%></div>
       		</td>
       		<td>
       			<div align="center"  ><%=area%></div>
       		</td>
       		<!--<td>
       			<div align="center"  ><%=xunisign%><% if(xunisign.trim().equals("是")){ %><a href="#" onclick="viewxn('<%=id%>')">[查看]</a><% } %></div>
       		</td>
       		-->
       		<td>
       			<div align="left"><%=id%></div>
       		</td>
       </tr>
       <%}%>
       <%}%>
         <% if (intnum==0){
    	  System.out.println("QQQQ"+intnum);
         for(int i=0;i<17;i++){
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
              
           
            </tr>
      <% }}else if(!(intnum > 17)){
    	  for(int i=((intnum-1)%17);i<17;i++){
            if(i%2==0)
			    color="#FFFFFF" ;
            else
			    color="#DDDDDD";
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
              
                         
        </tr>
        <% }}%>
  	 </table> 
  	 </div>
  	 <div style="width:100%;height:8%;border:1px" >	
			<table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
  	 
										<tr bgcolor="#FFFFFF">
											
											<td align="right">
												<div id="parent" style="display:inline" align="right">
                          <div style="width:300px;display:inline;" align="right"><hr></div>
                      </div>
											</td>
											
										</tr>
										
										<tr bgcolor="#FFFFFF">
											
											<td>
										<%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
												
									<div id="butongguo"
												style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:10px">
												<img alt=""
													src="<%=request.getContextPath()%>/images/baocun.png"
													width="100%" height="100%" />
												<span
													style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">不通过</span>
											</div>      
								          <div id="tongguo"
												style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
												<img src="<%=path%>/images/2chongzhi.png" width="100%"
													height="100%">
												<span
													style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">通过</span>
											</div>											

			   <%}%>
										
							
      <br />  
</td>
<!-- <td background="../../images/img_13.gif">&nbsp;</td> -->
</tr>
<tr>
<td>
<p>
 <font color="red" size="2">说明：市级审核不通过的站点显示为：红色<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 						市级未审核的站点显示为：黄色</font>
</p>
</td>
</tr>

</table>
</div>

 

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
      		document.form1.action=path+"/web/jizhan/jzshenhe.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/jizhan/jzshenhe.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/jizhan/jzshenhe.jsp?curpage="+pageno;
      document.form1.submit();
     }
     function delzd(id){
     	<%
			if(permissionRights.indexOf("PERMISSION_DELETE")>=0){
			%>
     		document.form1.action=path+"/servlet/zhandian?action=del&id="+id
      document.form1.submit();
      <%
    }else{
      %>
      alert("您没有删除站点的权限");
    <%}%>
    }
    function dfinfo(id){
    	<%
			if(permissionRights.indexOf("PERMISSION_EDIT")>=0){
			%>
     		document.form1.action=path+"/web/jizhan/dfinfo.jsp?id="+id
      document.form1.submit();
      <%
    }else{
      %>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }
    function zlinfo(id){
    	<%
			if(permissionRights.indexOf("PERMISSION_EDIT")>=0){
			%>
     		document.form1.action=path+"/web/jizhan/zlinfo.jsp?id="+id
      document.form1.submit();
      <%
    }else{
      %>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
    }
    function modifyjz(id){
    	<%
			if(permissionRights.indexOf("PERMISSION_EDIT")>=0){
			%>
     		document.form1.action=path+"/web/jizhan/modifyjz.jsp?id="+id
      document.form1.submit();
      <%
    }else{
      %>
      alert("您没有编辑站点信息的权限");
    <%}%>
     		
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
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		//document.form1.stationtype.value='<%=stationtype1%>';
		//document.form1.jzproperty.value='<%=jzproperty1%>';
		//document.form1.jztype.value='<%=jztype1%>';
     </script>


