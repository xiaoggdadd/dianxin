<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean,com.noki.electricfees.javabean.PrepaymentFormBean"%>
<%@ page import="java.text.*"%>
<%
	String bzyf = request.getParameter("bzyf") != null ? request.getParameter("bzyf") : "";//报账月份
	String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):CTime.formatRealDate(new Date());
    String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):CTime.formatRealDate(new Date());      
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";	
	//添加站点类型
    String stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";	
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    String color=null;
    String bargainid="";
    int intnum=0;
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
	String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
    String beginTimeQ = request.getParameter("startmonth")!=null?request.getParameter("startmonth"):"";
    String endTimeQ = request.getParameter("endmonth")!=null?request.getParameter("endmonth"):"";
    String fpsh1=request.getParameter("fpsh1")!=null?request.getParameter("fpsh1"):"-1";
         
           String canshuStr="";
     if((shi!=null)||(xian!=null)||(xiaoqu!=null)||((beginTime!=null)&&(endTime!=null))||(stationtype1!=null)||(zdname!=null)||(fpsh1!=null)){
         canshuStr= "&shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&startmonth="+beginTimeQ+"&endmonth="+endTimeQ+"&stationtype1="+stationtype1+"&zdname="+zdname+"&fpsh1="+fpsh1;
     }
    //String roleId = account.getRoleId();
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
	color: red;
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
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
.bttcn{color:BLACK;font-weight:bold;}
.form    {width:130px}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>

<script >

var oCalendarEnny=new PopupCalendarny("oCalendarEnny");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();


var oCalendarChsny=new PopupCalendarny("oCalendarChsny");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChsny.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChsny.oBtnTodayTitle="确定";
oCalendarChsny.oBtnCancelTitle="取消";
oCalendarChsny.Init();
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
        	}
	}
  function passCheck(){
        var m = document.getElementsByName('test[]');
        var arr = new Array
        var l = m.length; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var bzw=1;
        var bzw1="";
       	var chooseIdStr = ""; 
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       	}
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
	             chooseIdStr = chooseIdStr + m[i].value +",";
	          }
	          bzw1=document.form1.bzw1.value;
		      if(bzw1=="1"){
		          if(count1<=240*n){
		         	  if((bz/240==1)){
				        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
				        passCheck1(chooseIdStr);
			            chooseIdStr = ""; 
		       			bz=0;
			          }
			       }else if(count==count1&&bzw==1){
			          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
				      bzw=0;
			          document.form1.action=path+"/servlet/check?action=checknewhtsh&chooseIdStr="+chooseIdStr;
			          document.form1.submit(); 
			       }
			  }else if(bzw1=="0"){
				document.form1.action=path+"/web/msg.jsp";
				document.form1.submit(); 
				return;  
			  }             
	        } 
        }else{
          alert("请选择信息！");
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
    function queryDegree(){
         if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
	                 alert("城市不能为空");	   
	   	}else{
                   document.form1.action=path+"/web/newgn/hetong/checknewhtsh.jsp?command=chaxun";
                   document.form1.submit();
       }
    }
  $(function(){
  		$("#tongguo").click(function(){
			passCheck();
		});

		$("#butongguo").click(function(){
			passCheckNoPass();
		});
		$("#chaxun").click(function(){
			queryDegree();
		});
	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0302");


%>
<body  class="body">
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr >
			   <td colspan="4" width="50" >
					<div style="width:700px;height:50px">
						
							<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">合同单补录发票审核</span>	
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
				<table >
					<tr class="form_label">
		    		<td >城市： </td>
		    		<td>          
                      <select name="shi" class="selected_font" onchange="changeCity()">
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
	                    <option value="<%=agcode%>"><%=agname%></option>
	                    <%}
		         	}
		         %>
         	</select></select><span class="style1">&nbsp;*</span></td></td>
            <td>区县：</td>
            <td><select name="xian" class="selected_font" onchange="changeCountry()">
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
         	</select> </td>
         	<td>乡镇：</td>
         	<td>
         	  <select name="xiaoqu" class="selected_font" onchange="javascript:document.form1.xiaoqu2.value=document.form1.xiaoqu.value">
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
					</p>
				</td>
	         	<td >
                                  <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>    
                                  <div id="chaxun" style="position:relative;width:59px;height:23px;right:-180px;cursor: pointer;TOP:0PX">
		                          <img alt="" src="<%=path%>/images/chaxun.png" width="100%" height="100%" />
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
					<td>站点名称：</td>
					<td>
					   <input type="text" name="zdname" class="selected_font" value="<%if(null!=request.getParameter("zdname")) out.print(request.getParameter("zdname")); %>" />
					</td>
					<td>起始时间：</td>
					<td>
					   <input type="text" class="selected_font" name="startmonth" value="<%if(null!=request.getParameter("startmonth")) out.print(request.getParameter("startmonth")); %>" onFocus="getDatenyString(this,oCalendarChsny)" />
					</td>
					<td>结束时间：</td>
					<td>
					   <input type="text" class="selected_font" name="endmonth" value="<%if(null!=request.getParameter("endmonth")) out.print(request.getParameter("endmonth")); %>" onFocus="getDatenyString(this,oCalendarChsny)" />
					</td>
				    <td>站点类型：</td>
				    <td>
				      <select name="stationtype" class="selected_font" onchange="kzinfo()">
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
			</select></td></tr>
			<tr class="form_label">
			<td>报账月份:</td><td><input type="text" name="bzyf" value="<%if (null != request.getParameter("bzyf")) out.print(request.getParameter("bzyf"));%>" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font" /></td>
			<td>审核状态：</td><td><select name="fpsh1" class="selected_font">
         		<option value="-1">请选择</option>
         		<option value="1">审核通过</option>  
         		<option value="0">审核未通过</option>
         		<option value="2">审核不通过</option>          		
	         </select></td>
			</tr></table></p></div></td></tr></table>
			<table  height="23">
               <tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
            </table>
					<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
				      <table width="1500" height="80%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                  		<tr height = "10" class="relativeTag"> 
                         <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
                       	 <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
                         <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">地区</div></td>
                         <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">合同编号</div></td>
                         <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
            			 <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>
                         <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">金额</div></td>                  
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">补发票金额</div></td>
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据金额</div></td>
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据类型</div></td>
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始月份</div></td>
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束月份</div></td>
                         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
                         <td width="7%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">补录发票审核状态</div></td>
                  </tr>  
                  <%
         String whereStr = "";
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" AND ZD.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" AND ZD.XIAN='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" AND ZD.XIAOQU='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
				whereStr=whereStr+" AND ZD.JZNAME LIKE'%"+zdname+"%'";
			}			
			if(beginTimeQ != null && beginTimeQ != "" && !beginTimeQ.equals("0")){
				whereStr=whereStr+" AND to_char(P.STARTMONTH,'yyyy-mm')>='"+beginTimeQ+"'";
			}
			if(endTimeQ != null && endTimeQ != "" && !endTimeQ.equals("0")){
				whereStr=whereStr+" AND to_char(P.ENDMONTH,'yyyy-mm')<='"+endTimeQ+"'";
			}
			if(bzyf != null && bzyf != ""){
				whereStr=whereStr+" AND to_char(P.ACCOUNTMONTH,'yyyy-mm') ='"+bzyf+"'"; 
			}
			
			//站点类型
			if(stationtype1 != null && !stationtype1.equals("") && !stationtype1.equals("0")){
				whereStr=whereStr+" AND ZD.STATIONTYPE='"+stationtype1+"'";
			}
			//站点类型
			if(fpsh1 != null && !fpsh1.equals("") && !fpsh1.equals("-1")){
				whereStr=whereStr+" AND P.FPSH='"+fpsh1+"'";
			}	

         ElectricFeesBean bean=new ElectricFeesBean();
         List<PrepaymentFormBean>  list=null;
          String pjje="",szdq="",dflx="",htbh="",uuid="",zdcode="",jzname="",
          accountmonth="",stationid = "",jzcode = "",htdbh,manualauditstatus = "",money="",
          prepayment_ammeterid="",dbname="",startmonth="",endmonth="",id="",stationtype2="",pjlx="",fpje="",fpsh="";
	   double d;
        if("chaxun".equals(request.getParameter("command"))){
         list=bean.getHtdsh(whereStr,loginId);
       	// }
       	 //P.PJJE, P.HTBH,ZD.ZDCODE,ZD.JZNAME,P.ACCOUNTMONTH,
       	 //P.PREPAYMENT_AMMETERID,D.DBNAME,P.MONEY,P.STARTMONTH,P.ENDMONTH,P.ID," +
       	
		intnum=xh = pagesize*(curpage-1)+1;
		int linenum=0;
		Double dianfei=0.0;
		for(PrepaymentFormBean listt:list){
		//票据金额
		d=listt.getPjje();
		
		htbh=listt.getHtbh();		
		zdcode=listt.getZdcode();
		jzname=listt.getJzname();
		accountmonth=listt.getAccountmonth();
		prepayment_ammeterid=listt.getPrepaymentammeterid();
		dbname=listt.getDbname();
		money=listt.getMoney();
		startmonth=listt.getStartmonth();
		endmonth=listt.getEndmonth();
		id=listt.getId();
		szdq=listt.getSzdq();
		stationtype2=listt.getStationtype();
		dflx=listt.getDflx();
		uuid=listt.getUuid();
		
             linenum++;
             htbh = htbh != null ? htbh : "";
	    jzcode = jzcode != null ? jzcode : "";
	    jzname = jzname != null ? jzname : "";
	    id = id != null ? id : "";
	    prepayment_ammeterid = prepayment_ammeterid != null ? prepayment_ammeterid : "";
	    dbname = dbname != null ? dbname : "";
	    accountmonth = accountmonth != null ? accountmonth : "";
	    //添加站点类型
		stationtype2 = stationtype2 != null ? stationtype2 : "";
	    startmonth = startmonth != null ? startmonth : "";
	    endmonth = endmonth != null ? endmonth : "";
		if(money==null||money.equals("")||money.equals("null")||money.equals("o")){
			money="0.00";
        }	
       	DecimalFormat mat =new DecimalFormat("0.00");           
        money=mat.format(Double.parseDouble(money));
        
        //票据类型
       pjlx = listt.getPjlx();
       pjlx = pjlx != null ? pjlx : "";
       
       //补发票金额
        fpje = listt.getFpje();
		if(fpje==null||fpje.equals("")||fpje.equals("null")||fpje.equals("o")){
			fpje="0.00";
        }	           
        fpje = mat.format(Double.parseDouble(fpje)); 
        //补录发票审核状态     
        fpsh = listt.getFpsh();
        fpsh = fpsh != null ? fpsh : "";
        
		if(intnum%2==0){
		    color="#DDDDDD";
		 }else{
		    color="#FFFFFF";
		    }
          intnum++;

       %>
          <tr bgcolor="<%=color%>">
             <td>
       			<div align="center" ><%=intnum-1%></div>
       		</td>
       		<td>
              <div align="center"><input type="checkbox" name="test[]" value="<%=uuid%>" /></div>
            </td>
       		<td>
       			<div align="left" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>       		
       		
       		<td>
       			<div align="center" ><%=htbh%></div>
       		</td>          
            <td>
       			<div align="center" ><%=dflx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=stationtype2%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dbname%></div>
       		</td>
       		<td>
       			<div align="right" ><%=money%></div>
       		</td>
       		<td>
       			<div align="right" ><%=fpje%></div>
       		</td>
       		<td>
       			<div align="right" ><%=d%></div>
       		</td>
       		<td>
       			<div align="center" ><%=pjlx%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=startmonth%></div>
       		</td>
       		<td>
       			<div align="center" ><%=endmonth%></div>
       		</td>
       		<td>
       			<div align="center" ><%=accountmonth%></div>
       		</td>
       		<%if(fpsh!=null&&fpsh.equals("1")){ %>
       		<td>
       			<div align="center" >通过</div>
       		</td>
       	   <%}else if(fpsh!=null&&fpsh.equals("2")){%>
       	    <td>
       			<div align="center" >不通过</div>
       		</td>
       	   <%}else{ %>
       	    <td>
       			<div align="center" >未通过</div>
       		</td>      	     
       	   <%} %>
            <%}}%>
       </tr>
         <% if (intnum==0){
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
           <tr>
              <td>
                <input type="hidden" name="sheng2" id="sheng2" value=""/>
                <input type="hidden" name="shi2" id="shi2" value=""/>
                <input type="hidden" name="xian2" id="xian2" value=""/>
                <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
                <input type="hidden" name="id" id="id" value="<%=id%>"/>
                <input type="hidden" name="bzw1" id="bzw1" value="1"/>
              </td>
           </tr>
           	<tr bgcolor="F9F9F9">
                     <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                         <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                     </div></td>
	           </tr> 
         	   <tr>
                   <td align="right">                             
                    <div id="butongguo" style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:10px">
			          <img alt="" src="<%=request.getContextPath()%>/images/baocun.png" width="100%" height="100%" />
			          <span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">不通过</span>
		         </div>      
                   <div id="tongguo" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
			         <img src="<%=path%>/images/2chongzhi.png" width="100%" height="100%">
			         <span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">通过</span>
		        </div>                           
                          </td>
              </tr> 
           </table></div>
          </form>
         </body>
<script type="text/javascript">
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
		return;s
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
}
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
</script>
<script type="text/javascript">
	var XMLHttpReq1;
	function createXMLHttpRequest1() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq1 = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq1 = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq1 = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	function sendRequest1(url,para) {

		createXMLHttpRequest1();
		XMLHttpReq1.open("POST", url, true);
		if(para=="checkcity1"){
			XMLHttpReq1.onreadystatechange = processResponse_checkcity1;
		}else if(para=="checkcityno1"){
			XMLHttpReq1.onreadystatechange = processResponse_checkcityno1;
		}else{
			XMLHttpReq1.onreadystatechange = processResponse;//指定响应函数
		}
		XMLHttpReq1.send(null);  
	}
	// 处理返回信息函数
    function processResponse() {
    	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
              var res = XMLHttpReq1.responseText;
              window.alert(res);
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
	function passCheck1(chooseIdStr){
			sendRequest1(path+"/servlet/check?action=checknewhtsh1&chooseIdStr="+chooseIdStr,"checkcity1");
	}
	function processResponse_checkcity1() {	
		if (XMLHttpReq1.readyState == 4) { // 判断对象状态
	    	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
	    	document.form1.bzw1.value= XMLHttpReq1.responseText;
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
	function passCheckNo2(chooseIdStr){
			sendRequest1(path+"/servlet/check?action=checknewhtshno11&chooseIdStr="+chooseIdStr,"checkcityno1");
	}
	function processResponse_checkcityno1() {	
		if (XMLHttpReq1.readyState == 4) { // 判断对象状态
	    	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
	        	document.form1.bzw1.value = XMLHttpReq1.responseText;
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
</script>
<script language="javascript">
  function passCheckNoPass(){
       var m = document.getElementsByName('test[]');      
       var l = m.length; 
       var chooseIdStr = ""; 
       var bz=0;
       var n=0;
       var count=0; 
       var count1=0;
       var bzw=1;
       var bzw1="";
      	for(var i = 0; i < l; i++){
      		if(m[i].checked == true){
      			count+=1;
      		}
      	}
      	if(count!=0){
	       	if(count%240==0){
	       		n=count/240-1;
	       	}else{
	       		n=(count-(count%240))/240;
	       	}
	        for (var i = 0; i < l; i++) {  
	          if(m[i].checked == true){
	             bz+=1;
	             count1+=1;
	             chooseIdStr = chooseIdStr + m[i].value +",";
	          }
             bzw1=document.form1.bzw1.value;
	      if(bzw1=="1"){
	          if(count1<=240*n){
	         	  if((bz/240==1)){
			        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
				    passCheckNo2(chooseIdStr);
		            chooseIdStr = ""; 
	       			bz=0;         	
		          }
		       }else if(count==count1&&bzw==1){
		          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
			      bzw=0;
				  document.form1.action=path+"/servlet/check?action=checknewhtshno1&chooseIdStr="+chooseIdStr;
				  document.form1.submit();
		       } 
		  }else if(bzw1=="0"){
			document.form1.action=path+"/web/msg.jsp";
			document.form1.submit(); 
			return;  
		  }            
        } 
     }else{
       alert("请选择信息！");
     }
  }
var path='<%=path%>';
  function checkDF(){
         	if(document.form1.DFCheck.checked){
         		for(var j = 0;j < document.form1.elements.length ; j++){
							document.form1.elements[j].checked=true
						}
         	}else{
         		for(var j = 0;j < document.form1.elements.length ; j++){
							document.form1.elements[j].checked=false
						}
         	}
        }

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
   		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.stationtype.value='<%=stationtype1%>';
		document.form1.fpsh1.value='<%=fpsh1%>';
 </script>
</html>
