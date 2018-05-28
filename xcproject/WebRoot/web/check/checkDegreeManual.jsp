<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.check.javabean.CheckDegreeBean,com.noki.mobi.common.CommonBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.regex.Pattern"%>

<%	String beginTime1 = request.getParameter("beginTime1") != null ? request
			.getParameter("beginTime1")
			: "";
			String lrrq = request.getParameter("lrrq") != null ? request
			.getParameter("lrrq")
			: "";
	String endTime1 = request.getParameter("endTime1") != null ? request
			.getParameter("endTime1") : "";
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
        String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());
       //System.out.println("logManage.jsp>>"+beginTime);
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    String loginId1 = request.getParameter("loginId");
     String zdqyzt=request.getParameter("zdqyzt") != null ? request.getParameter("zdqyzt") : "1";
	 String dbqyzt=request.getParameter("dbqyzt") != null ? request.getParameter("dbqyzt") : "1";
    //String roleId = account.getRoleId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String cityaudit1=request.getParameter("cityaudit")!=null?request.getParameter("cityaudit"):"0";
    String roleId = (String)session.getAttribute("accountRole");
    //System.out.println("roleId:"+roleId);
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
		String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"0";
		//String manualauditstatus1=request.getParameter("manualauditstatus1")!=null?request.getParameter("manualauditstatus1"):"";
	    //添加站点类型、站点属性、集团报表类型
    	String stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
    	String jzproperty1=request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
     	String jizhan = request.getParameter("zhandian")!=null?request.getParameter("zhandian"):"0";
     	
     	String canshuStr="";
     	//int intnum=xh = pagesize*(curpage-1)+1;
     	int intnum=0;
     	String color=null;
	    if((shi!=null)||(xian!=null)||(xiaoqu!=null)||(title!=null)||(operName!=null)||(stationtype1!=null)||(jzproperty1!=null)||(jizhan!=null)){
			canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&title="+title+"&operName="+operName+"&stationtype1="+stationtype1+"&jzproperty1="+jzproperty1+"&jizhan="+jizhan;
	    }
	    double dlhj = 0.00;
	    String dlhj1="0";
	    String dlhj2;


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
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.bttcn{color:BLACK;font-weight:bold;}
.form    {width:130px}
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
 </style>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
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
					var beginTime = document.form1.beginTime.value
           	if(checkNotnull(document.form1.beginTime,"开始时间")&&
                   checkNotnull(document.form1.endTime,"结束时间")){
 			document.form1.action=path+"/web/sys/logManage.jsp?beginTime="+beginTime
                        document.form1.submit()
                        showdiv("请稍等..............");
        	}
	}
	function delLogs(){
		var beginTime = document.form1.delBeginTime.value
                var endTime = document.form1.delEndTime.value

                if(checkNotnull(document.form1.delBeginTime,"开始时间")&&
                   checkNotnull(document.form1.delEndTime,"结束时间")){
                      if(beginTime>endTime){
                 	alert("开始时间不能大于结束时间！")
                         return
               		 }
                      if(confirm("确定要删除，不可恢复！")){
                 	  document.form1.action=path+"/servlet/log?delBeginTime="+beginTime+"&delEndTime="+endTime
                           document.form1.submit()
               		 }
                }

	}
	
		function payfee(degreeid){
          document.form1.action=path+"/web/dianliang/payElectricFees.jsp?degreeid="+degreeid;
                                document.form1.submit();
        }
    //审核通过
	function passCheck(){
        var m = document.getElementsByName('test[]');   
        var l = m.length; 
        var chooseIdStr = ""; 
        var bz="";
        for (var i = 0; i < l; i++) {  
          if(m[i].checked == true){
             var bz="1";
             chooseIdStr = chooseIdStr + m[i].value +","; 
          }               
        } 
        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);       
        //alert("----");
        if(bz=="1"){
           document.form1.action=path+"/servlet/check?action=checkdm&chooseIdStr="+chooseIdStr;
           document.form1.submit();
        }else{
          alert("请选择信息！");
        }
      }
      
      
      
    function delad(ammeterdegreeid){
       if(confirm("您确定删除此电量信息？")){
                    document.form1.action=path+"/servlet/ammeterdegree?action=del&degreeid="+ammeterdegreeid
        			document.form1.submit()
       }
    }
    function modifyad(ammeterdegreeid){
                    document.form1.action=path+"/web/dianliang/modifyDegree.jsp?degreeid="+ammeterdegreeid;
                    document.form1.submit();
       
    }
    function queryDegree(){
                   /*var sheng = document.form1.sheng2.value;
                   var shi = document.form1.shi2.value;
                   var xian = document.form1.xian2.value;
                   var xiaoqu = document.form1.xiaoqu2.value;
                 //  var jizhan = document.form1.zhandian.value;
                   var beginTime = document.form1.BeginTime.value;
                   var endTime = document.form1.EndTime.value;
                   var zdname = document.form1.zdname.value;
                   var canshuStr = "?sheng="+sheng+"&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&beginTime="+beginTime+"&endTime="+endTime+"&zdname"+zdname;
                  */
                   //alert(canshuStr);
                   document.form1.action=path+"/web/check/checkDegreeManual.jsp?command=chaxun";
                   document.form1.submit();
       
    }
    $(function(){
		 $("#daochu").click(function(){
			exportad();
		});
		$("#tongguo").click(function(){
			passCheck();
		});

		$("#butongguo").click(function(){
			passCheckNo();
		});
		$("#chaxun").click(function(){
			queryDegree();
			showdiv("请稍等..........");
		});
	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0803");

%>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr>
			<td colspan="4">
			<div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">人工电量审核</span>	
			  </div>
		    </td>
			</tr>		
			<tr><td height="30" colspan="4">
	   				<div id="parent" style="display:inline">
	                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
	                </div>
		        </td>
		    </tr>
			<tr><td height="8%" width="1200px">
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
				
				
				<td>区县:</td><td> <select name="xian" class="selected_font" onchange="changeCountry()">
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
				
				
				<td>乡镇：</td><td><select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
         		<option value="0">请选择</option>
         		<%
		         	ArrayList xiaoqulist = new ArrayList();
		         	xiaoqulist = commBean.getAgcode(xian,account.getAccountId());
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
					</p></td>
			<td><%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>
               <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;right:-220px;float:right;top:0;">
		       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 		       
				</div>
		       <%}%>
		       </td>
			</tr>
			</table>			
			</td></tr>		
		</table>
		
	<div style="width:88%;" > 
		<p id="box3" style="display:none">
			<table>
			<tr class="form_label">
				<td>站点名称：</td><td><input type="text" name="zdname" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" class="selected_font"/></td>
				<td>站点类型:</td><td><select name="stationtype" class="selected_font" onchange="kzinfo()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList stationtype = new ArrayList();
         		stationtype = commBean.getSelctOptions("StationType");
	         	if(stationtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)stationtype.get(0)).intValue();
	         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
                    {
                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
	         </select></td>
			<td>站点属性：</td><td><select name="jzproperty" class="selected_font" onchange="kzinfo()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = commBean.getSelctOptionszd("zdsx");
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
         	</select></td>
			</tr>
			<tr class="form_label">
				<td>起始月份:</td><td><input type="text" class="selected_font" name="beginTime1" value="<%if (null != request.getParameter("beginTime1"))out.print(request.getParameter("beginTime1"));%>" 
												readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /></td>
				<td>结束月份:</td><td><input type="text" name="endTime1" class="selected_font" value="<%if (null != request.getParameter("endTime1"))out.print(request.getParameter("endTime1"));%>" 
												readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" /></td>
				 <td>录入日期：</td><td><input type="text" name="lrrq" value="<%if(null!=request.getParameter("lrrq")) out.print(request.getParameter("lrrq")); %>" 
				 								readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="selected_font"/></td>
			</tr>
			<tr class="form_label">
             	<td>人工审核状态：</td><td><select name="cityaudit" class="selected_font" onchange="javascript:document.form1.cityaudit2.value=document.form1.cityaudit.value">
         		
	         		<option value="-1">请选择</option>
	         		<option value="1">审核通过</option>
	         		<option value="0">审核未通过</option>    
	         	</select></td>
	         	<td>
											站点启用状态：
										</td>
										<td>
											<select name="zdqyzt" class="selected_font">
												<option value="-1">
													请选择
												</option>
												<option value="0">
													否
												</option>
												<option value="1">
													是
												</option>

											</select>
										</td>
										
										<td>
											电表启用状态：
										</td>
										<td>
											<select name="dbqyzt" class="selected_font">
												<option value="-1">
													请选择
												</option>
												<option value="0">
													否
												</option>
												<option value="1" selected="selected">
													是
												</option>

											</select>
											</td>
			</tr>
			</table>
		</p>
	</div>	

		<table>
			<tr><td height="23" colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
             </div></td>
            
             
             </tr> 
		</table>
		
		<%
			CheckDegreeBean bean = new CheckDegreeBean();
			String whereStr = "";
         	String str = "";
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and zd.shi='"+shi+"'";
				str=str+" and zd.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and zd.xian='"+xian+"'";
				str=str+" and zd.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" and zd.xiaoqu='"+xiaoqu+"'";
				str=str+" and zd.xiaoqu='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
				whereStr=whereStr+" and zd.jzname like '%"+zdname+"%'";
			}
			
			if(lrrq != null && !lrrq.equals("") && !lrrq.equals("0")){
				whereStr=whereStr+" and to_char(ad.entrytime,'yyyy-mm-dd') like '%"+lrrq+"%'";
			}
			if(beginTime1 != null && beginTime1 != "" && !beginTime1.equals("0")){
				whereStr=whereStr+" and to_char(ad.STARTMONTH,'yyyy-mm') ='"+beginTime1+"'";
				str=str+" and to_char(ad.STARTMONTH,'yyyy-mm') ='"+beginTime1+"'";
			}
			if(endTime1 != null && endTime1 != "" && !endTime1.equals("0")){
				whereStr=whereStr+" and to_char(ad.ENDMONTH,'yyyy-mm') ='"+endTime1+"'";
				str=str+" and to_char(ad.ENDMONTH,'yyyy-mm') ='"+endTime1+"'";
			}
			//站点类型、站点属性、集团报表类型
			if(stationtype1 != null && !stationtype1.equals("") && !stationtype1.equals("0")){
				whereStr=whereStr+" and zd.STATIONTYPE='"+stationtype1+"'";
				str=str+" and zd.STATIONTYPE='"+stationtype1+"'";
			}
			if(jzproperty1 != null && !jzproperty1.equals("") && !jzproperty1.equals("0")){
				whereStr=whereStr+" and zd.PROPERTY='"+jzproperty1+"'";
				str=str+" and zd.PROPERTY='"+jzproperty1+"'";
			}
			if(cityaudit1 != null && !cityaudit1.equals("") && !cityaudit1.equals("-1")){
				whereStr=whereStr+" and ad.MANUALAUDITSTATUS='"+cityaudit1+"'";
				str=str+" and ad.MANUALAUDITSTATUS='"+cityaudit1+"'";
			}if(zdqyzt!= null && zdqyzt != ""&& !zdqyzt.equals("-1")){
				whereStr=whereStr+" AND zd.qyzt='"+zdqyzt+"'";
				str=str+" AND zd.qyzt='"+zdqyzt+"'";
			}if(dbqyzt!= null && dbqyzt != ""&& !dbqyzt.equals("-1")){
				whereStr=whereStr+" AND db.dbqyzt='"+dbqyzt+"'";
				str=str+" AND db.dbqyzt='"+dbqyzt+"'";
			}
			//if(manualauditstatus1 != null && manualauditstatus1 != "" && !manualauditstatus1.equals("-2")){
			//	whereStr=whereStr+" and ad.manualauditstatus='"+manualauditstatus1+"'"
			//}

		System.out.println("AmmeterDegreeBean-whereStr:"+whereStr);
		
		String str1="";
		String str2="";
		if(loginId1!=null&&!loginId1.equals("")){
       	     loginId=loginId1;
       	 	System.out.println("权限："+loginId+"权限2"+loginId1);
       	 	shi="1";
       	 }
		//更改201212-5
		/*String count1="0";
		String count2="0.00";
       	if("chaxun".equals(request.getParameter("command"))||"daochu".equals(request.getParameter("command"))){
            count1=bean.getCount(whereStr,loginId,str1);
            count2=bean.getCountGree(whereStr,loginId,str2);
		//String count1=bean.getCount(whereStr,loginId,str1);
		//String count2=bean.getCountGree(whereStr,loginId,str2);
		if(count1==null||count1==""||count1.equals("")||count1.equals("null")){
		   count1="0";
		}
		if(count2==null||count2==""||count2.equals("")||count2.equals("null")){
		   count2="0.0";
		}else{
		    DecimalFormat countd2=new DecimalFormat("0.0");
		    count2=countd2.format(Double.parseDouble(count2));
		}*/
	%>	
		<%--<table height="5%">
          <tr>
  	        <td><font size="2">总共</font></td><td><font size="2" color="red"><%=count1 %></font><font size="2">条  |</font></td>
  	        <td><font size="2">实际用电量总和</font></td><td><font size="2" color="red"><%=count2 %></font><font size="2">度 |</font></td>  	       
  	      </tr>
		</table>
		<%} %>
		
	--%><div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
		<table width="1800px" height="80%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
			<tr height = "23" class="relativeTag">
					<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
					<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center"  class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
                    <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                    <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次电表读数</div></td>
        			<td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td>
        			
                    <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次电表读数</div></td>                    
                    <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>                    
                    <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量调整</div></td>
                    <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">折算后用电量</div></td>
                    
                    
                    
                      <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电量超标比例</div></td>
                    
                    
                     <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">省级定标电量超标比例</div></td>
                    
                    
                    <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电流类型</div></td>
                    <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电设备</div></td>                    
                    <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>                    
                    <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
                    <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td>                    
                    <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">自动审核状态</div></td>
                    
                    <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核状态</div></td>                    
                    <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td>
			</tr>
	<%        
       	 ArrayList fylist = new ArrayList();
        //更改2012-12-5
       	 if("chaxun".equals(request.getParameter("command"))||"daochu".equals(request.getParameter("command"))){
            fylist = bean.getPageData(curpage,whereStr,loginId);
            allpage=bean.getAllPage();
        } else{
           fylist=null;
        }
       	// fylist = bean.getPageData(curpage,whereStr,loginId);
       	 //allpage=bean.getAllPage();
		String electriccurrenttype_ammeter = "",usageofelectypeid_ammeter = "",
		ammeterdegreeid = "",ammeterid = "",lastdegree = "",jzname="",
		thisdegree = "",lastdatetime = "",thisdatetime = "",
		floatdegree = "",actualdegree = "",autoauditstatus = "",manualauditstatus = "",stationtype2="",jzproperty2 ="",jztype2 ="";
		String edhdl="",unitprice="",blhdl="",qsdbdl="";
		int countxh=1;
		
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		      DecimalFormat mat=new DecimalFormat("0.00");
		      edhdl = (String)fylist.get(k+fylist.indexOf("EDHDL"));
		      qsdbdl = (String)fylist.get(k+fylist.indexOf("DL"));
		      System.out.println("全省定标电量："+qsdbdl+"额定耗电量："+edhdl);
		       blhdl = (String)fylist.get(k+fylist.indexOf("BLHDL"));
		       unitprice = (String)fylist.get(k+fylist.indexOf("DIANFEI"));
		      if("".equals(edhdl)||null==edhdl){edhdl="0";}
		      if("".equals(qsdbdl)||null==qsdbdl){qsdbdl="0";}
		       if("".equals(blhdl)||null==blhdl){blhdl="0";}
		        if("".equals(unitprice)||null==unitprice){unitprice="0";}
		    ammeterdegreeid = (String)fylist.get(k+fylist.indexOf("AMMETERDEGREEID"));
		    electriccurrenttype_ammeter = (String)fylist.get(k+fylist.indexOf("ELECTRICCURRENTTYPE_AMMETER"));
		    electriccurrenttype_ammeter = electriccurrenttype_ammeter != null ? electriccurrenttype_ammeter : "";
		    //usageofelectypeid_ammeter = (String)fylist.get(k+fylist.indexOf("USAGEOFELECTYPEID_AMMETER"));
		    usageofelectypeid_ammeter = (String)fylist.get(k+fylist.indexOf("YDSB"));
		    usageofelectypeid_ammeter = usageofelectypeid_ammeter != null ? usageofelectypeid_ammeter : "";
			ammeterid = (String)fylist.get(k+fylist.indexOf("AMMETERID_FK"));
			ammeterid = ammeterid != null ? ammeterid : "";
			if("".equals(electriccurrenttype_ammeter)||"null".equals(electriccurrenttype_ammeter)||electriccurrenttype_ammeter==null){
			 electriccurrenttype_ammeter="";
			}
			jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
			jzname = jzname != null ? jzname : "";
			
		    lastdegree = (String)fylist.get(k+fylist.indexOf("LASTDEGREE"));
		    lastdegree = lastdegree != null ? lastdegree : "";
		    thisdegree = (String)fylist.get(k+fylist.indexOf("THISDEGREE"));
		    thisdegree = thisdegree != null ? thisdegree : "";
		    lastdatetime = (String)fylist.get(k+fylist.indexOf("LASTDATETIME"));
		    lastdatetime = lastdatetime != null ? lastdatetime : "";
		    thisdatetime = (String)fylist.get(k+fylist.indexOf("THISDATETIME"));
			thisdatetime = thisdatetime != null ? thisdatetime : "";
			
			  SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    			     String dfsl="0";
    			     double shu;
    			     double shu1;
    			     double dbdl1=0.00;
    			     double sjydl;
    			     double dd;
    			     double dbdl=0;
    			     String dlbl="0";
    			     long quot;
		     if((lastdatetime!=null&&!lastdatetime.equals("")&&lastdatetime!="")&&(thisdatetime!=null&&!thisdatetime.equals("")&&thisdatetime!="")){
		      String startn=lastdatetime.substring(0,4);
    			      String endn=thisdatetime.substring(0,4);
    			      String sgang=lastdatetime.substring(4,5);
    			      String egang=thisdatetime.substring(4,5);
    			      
    			     Pattern pattern = Pattern.compile("[0-9]*");//判断前四位是否是数字
    			     
    			      if( pattern.matcher(startn).matches()==true&&pattern.matcher(endn).matches()==true&&sgang.equals("-")&&egang.equals("-")){
    			     	if(lastdatetime.length()>=8&&thisdatetime.length()>=8){
    			     	Date date1 = ft.parse( lastdatetime );
        	         	Date date2 = ft.parse( thisdatetime );
        	         	quot = date2.getTime() - date1.getTime();
        	         	quot = quot/1000/60/60/24+1;//计算天数
        	         	shu=Double.parseDouble(edhdl)*Double.parseDouble(unitprice)*quot;//计算额定电费
        	         	shu1=Double.parseDouble(edhdl)*quot;// 额定电量
        	         	
        	         	sjydl=Double.parseDouble(blhdl);// 实际用电量
        	         	//System.out.println("shu1:"+shu1+"sjydl"+sjydl+"edhdl"+edhdl+"quot"+quot);
        	         	if(!"".equals(qsdbdl)){
        	         	    dbdl1=Double.parseDouble(qsdbdl)*quot;//全省定标电量
        	         		dbdl=((sjydl-dbdl1)/dbdl1)*100;
        	         		qsdbdl=dbdl+"";
        	         		qsdbdl=mat.format(Double.parseDouble(qsdbdl));
        	         	}
        	         	if(shu1!=0){
        	         	dd=((sjydl-shu1)/shu1)*100;
        	         	}else{
        	         	dd=0;
        	         	}
        	        	DecimalFormat eddf =new DecimalFormat("0.00");
        	        	dlbl=eddf.format(dd);
                     	dfsl=eddf.format(shu);//格式化额定电费
		           		}
		      		}
		     
		     }
		     if(lastdatetime=="0"||lastdatetime.equals("0")){
				lastdatetime="";
			}else{
				lastdatetime = lastdatetime != null ? lastdatetime : "";
			}
			
			if(thisdatetime=="0"||thisdatetime.equals("0")){
				thisdatetime="";
			}else{
				thisdatetime = thisdatetime != null ? thisdatetime : "";
			}			
			floatdegree = (String)fylist.get(k+fylist.indexOf("FLOATDEGREE"));
			floatdegree = floatdegree != null ? floatdegree : "";
		    actualdegree = (String)fylist.get(k+fylist.indexOf("BLHDL"));
		    actualdegree = actualdegree != null ? actualdegree : "";
            autoauditstatus = (String)fylist.get(k+fylist.indexOf("AUTOAUDITSTATUS"));
            autoauditstatus = autoauditstatus != null ? autoauditstatus : "";
            manualauditstatus = (String)fylist.get(k+fylist.indexOf("MANUALAUDITSTATUS"));
		    manualauditstatus = manualauditstatus != null ? manualauditstatus : "";
		    //添加站点类型、站点属性、集团报表类型
			stationtype2 = (String)fylist.get(k+fylist.indexOf("STATIONTYPE"));
			stationtype2 = stationtype2 != null ? stationtype2 : "";
			jzproperty2 = (String)fylist.get(k+fylist.indexOf("PROPERTY"));
			jzproperty2 = jzproperty2!= null ? jzproperty2 : "";
			jztype2 = (String)fylist.get(k+fylist.indexOf("JZTYPE"));
			jztype2 = jztype2 != null ? jztype2 : "";   
		   
		   if(lastdegree==null||lastdegree.equals("")||lastdegree.equals("null")) lastdegree="0";
		   DecimalFormat la = new DecimalFormat("0.0");
		   lastdegree = la.format(Double.parseDouble(lastdegree));
		   
		   if(thisdegree==null||thisdegree.equals("")||thisdegree.equals("null")) thisdegree="0";
		   DecimalFormat th = new DecimalFormat("0.0");
		   thisdegree = th.format(Double.parseDouble(thisdegree));
		   
		   if(floatdegree==null||floatdegree.equals("")||floatdegree.equals("null")) floatdegree="0";
		   DecimalFormat fl = new DecimalFormat("0.0");
		   floatdegree = fl.format(Double.parseDouble(floatdegree));
		    
		   if(actualdegree==null||actualdegree.equals("")||actualdegree.equals("null")) actualdegree="0";
		   DecimalFormat ac = new DecimalFormat("0.0");
		   actualdegree = ac.format(Double.parseDouble(actualdegree));
		    
            


			if(intnum%2==0){
			    color="#FFFFFF";
			 }else{
			    color="#DDDDDD";
			}
           intnum++;
           dlhj +=Double.parseDouble(actualdegree);
           if(usageofelectypeid_ammeter.equals("ybsb01")){
        	   usageofelectypeid_ammeter="照明用电";
           }else if(usageofelectypeid_ammeter.equals("ybsb02")){
        	   usageofelectypeid_ammeter="空调用电";
           }else if(usageofelectypeid_ammeter.equals("ybsb03")){
        	   usageofelectypeid_ammeter="设备用电";
           }else if(usageofelectypeid_ammeter.equals("ybsb04")){
        	   usageofelectypeid_ammeter="其它用电设备";
           }else if(usageofelectypeid_ammeter.equals("ybsb05")){
				usageofelectypeid_ammeter="总表";
		   }else if(usageofelectypeid_ammeter.equals("ybsb06")){
				usageofelectypeid_ammeter="综合";
           }else{
        	   usageofelectypeid_ammeter="";
           }
           if(jztype2.equals("zdlx01")){
        	   jztype2="IDC机房";
           }else if(jztype2.equals("zdlx07")){
        	   jztype2="接入网";
           }else if(jztype2.equals("zdlx08")){
        	   jztype2="基站";
           }else if(jztype2.equals("zdlx12")){
        	   jztype2="通信机房";
           }else if(jztype2.equals("zdlx19")){
        	   jztype2="其他";
           }else{
        	   jztype2="";
           }
		   if(jzproperty2.equals("zdsx01")){
			   jzproperty2="局用机房";
		   }else if(jzproperty2.equals("zdsx02")){
			   jzproperty2="基站";
		   }else if(jzproperty2.equals("zdsx03")){
			   jzproperty2="营业网点";
		   }else if(jzproperty2.equals("zdsx04")){
			   jzproperty2="其他";
		   }else if(jzproperty2.equals("zdsx05")){
			   jzproperty2="接入网";
		   }else if(jzproperty2.equals("zdsx06")){
			   jzproperty2="乡镇支局";
		   }else{
			   jzproperty2="";
		   }
       %>
         <tr bgcolor="<%=color%>">
         	 <td>
       			<div align="center" ><%=countxh++%></div>
       		</td>
            <td>
              <div align="center"><input type="checkbox" name="test[]" value="<%=ammeterdegreeid %>" /></div>
            </td>
            <td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="right" ><%=lastdegree%></div>
       		</td>
       		<td>
       			<div align="center" ><%=lastdatetime%></div>
       		</td>
       		<td>
       			<div align="right" ><%=thisdegree%></div>
       		</td>

       		<td>
       			<div align="center" ><%=thisdatetime%></div>
       		</td>          
            <td>
       			<div align="right" ><%=floatdegree%></div>
       		</td>
            <td>
       			<div align="right" ><%=actualdegree%></div>
       		</td>
       		
       		
       		 <td>
       			<div align="right" ><%=dlbl%>%</div>
       		</td>
       			 <td>
       			<div align="right" ><%=qsdbdl%>%</div>
       		</td>
       		
       		 <td>
       			<div align="center" ><%=electriccurrenttype_ammeter%></div>
       		</td>
       		<td>
       			<div align="center" ><%=usageofelectypeid_ammeter%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=stationtype2%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jzproperty2%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jztype2%></div>
       		</td>       		
           <%if(autoauditstatus!=null&&autoauditstatus.equals("1")){ %>
       		<td>
       			<div align="center" >通过</div>
       		</td>
       	   <%}else{ %>
       	    <td>
       			<div align="center" >未通过</div>
       		</td>      	     
       	   <%} %>
       	   <%if(manualauditstatus!=null&&manualauditstatus.equals("1")){ %>
       		<td>
       			<div align="center" >通过</div>
       		</td>
       	   <%}else{ %>
       	    <td>
       			<div align="center" >未通过</div>
       		</td>      	     
       	   <%} %>
       	    <td>
       			<div align="left" ><%=ammeterid%></div>
       		</td>
       </tr>
     
       <%} %>      
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
      <% }}else if(!(intnum > 16)){
    	  for(int i=((intnum-1)%16);i<16;i++){
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
            <td>&nbsp;</td>
            <td>&nbsp;</td>    
            <td>&nbsp;</td>              
        </tr>
        <% }}%>
        <%
        dlhj1=dlhj+"";
        DecimalFormat price1=new DecimalFormat("0.0");
        dlhj2= price1.format(Double.parseDouble(dlhj1)); 
        %>
        <tr>
        	<td align="center">合计</td>
        	<td align="center">电量合计：</td>
      		<td><%=dlhj2 %><font size="2">度 </font></td>
        </tr>
		</table>
	</div>
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  
		  <tr><td>
		  <input type="hidden" name="sheng2" id="sheng2" value=""/>
		  <input type="hidden" name="shi2" id="shi2" value=""/>
		  <input type="hidden" name="xian2" id="xian2" value=""/>
		  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
		  <input type="hidden" name="manualauditstatus2" id="manualauditstatus2" value=""/>
		  </td></tr>
		  <tr bgcolor="F9F9F9">
		                      <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
		                          <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
		                      </div></td>
		                    </tr>
		     <tr>
		     <td align="right" colspan="4">
		                         		<div id="butongguo"
						style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:93px">
						<img alt=""
							src="<%=request.getContextPath()%>/images/baocun.png"
							width="100%" height="100%" />
						<span
							style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">不通过</span>
					</div>      
		          <div id="tongguo"
						style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 100px">
						<img src="<%=path%>/images/2chongzhi.png" width="100%"
							height="100%">
						<span
							style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">通过</span>
					</div>
					<%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                        
					   <div id="daochu" style="position:relative;width:60px;height:23px;cursor: pointer;right:-97px;float:right;">
							<img alt="" src="<%=path %>/images/daochu.png" width="100%" height="100%" />
							<span style="font-size:12px;position: absolute;left:29px;top:7px;color:white">导出</span>
						</div>
					   <%}%> 
		       </td>
		       </tr>
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
      		document.form1.action=path+"/web/check/checkDegreeManual.jsp?curpage="+curpage;
        document.form1.submit();
      }
     }
     function nextpage()
     {
      document.form1.page.value = parseInt(document.form1.page.value) + 1;
      var curpage='<%=(curpage+1)%>';
      		document.form1.action=path+"/web/check/checkDegreeManual.jsp?curpage="+curpage;
         document.form1.submit();
     }
     function gopagebyno(pageno)
     {
      document.form1.page.value = pageno;
     
      		document.form1.action=path+"/web/check/checkDegreeManual.jsp?curpage="+pageno;
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

function changeSheng(){
	var sid = document.form1.sheng.value;
	document.form1.sheng2.value=document.form1.sheng.value;
	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
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

function changeCountry(){
	var sid = document.form1.xian.value;
	document.form1.xian2.value=document.form1.xian.value;
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

//-->
</script>
<script type="text/javascript">
function passCheckNo(){
        var m = document.getElementsByName('test[]');   
        var l = m.length; 
        var chooseIdStr = ""; 
        var bz="";
        for (var i = 0; i < l; i++) {  
          if(m[i].checked == true){
             bz="1";
             chooseIdStr = chooseIdStr + m[i].value +","; 
          }               
        } 
        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
        //alert(chooseIdStr);
        if(bz=="1"){
           document.form1.action=path+"/servlet/check?action=checkdmno&chooseIdStr="+chooseIdStr;
           document.form1.submit();
        }else{
          alert("请选择信息！");
        }
   }
 </script>
 <!--多选框选择 -->
 <script type="text/javascript">
 function chooseAll() {   
        var qm = document.getElementsByName('test');
        //alert(qm[0].checked);  
        var m = document.getElementsByName('test[]');   
        var l = m.length; 
        if(qm[0].checked == true){
          for (var i = 0; i < l; i++) {   
            m[i].checked = true;   
          }  
        }else{
          for (var i = 0; i < l; i++) {   
            //m[i].checked == true ? m[i].checked = false: m[i].checked = true; 
            m[i].checked = false;  
          }   
        }
    }   
    
    function exportad(){
            var curpage = <%=curpage%>;
            var whereStr ="<%=str%>";
            var zdname ="<%=zdname%>";
            var lrrq='<%=lrrq%>';
            var rgsh='<%=cityaudit1%>';
            //alert(canshuStr);
        	document.form1.action=path+"/web/check/人工电量审核.jsp?curpage="+curpage+"&whereStr="+whereStr+"&zdname="+zdname+"&lrrq"+lrrq+"&rgsh="+rgsh+"&command=daochu";
            document.form1.submit();
   }
    	document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.stationtype.value='<%=stationtype1%>';
		document.form1.jzproperty.value='<%=jzproperty1%>';
		document.form1.cityaudit.value='<%=cityaudit1%>';
		document.form1.zdqyzt.value='<%=zdqyzt%>';
		document.form1.dbqyzt.value='<%=dbqyzt%>';
 </script>
</html>
