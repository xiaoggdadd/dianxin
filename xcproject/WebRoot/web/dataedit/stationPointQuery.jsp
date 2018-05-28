<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesFormBean,com.noki.dataedit.bean.*" %>
<%@ page import="java.sql.ResultSet,java.util.List"%>
<%@ page import="java.text.*"%>
<%@page import="com.noki.query.basequery.javabean.StationPointQueryBean,com.noki.mobi.common.CommonBean,com.noki.mobi.common.ZtCommon"%>

<%
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	
    String zdqyzt = request.getParameter("zdqyzt")!= null ? request.getParameter("zdqyzt") : "1";
    
    String yflx="";
    
	int intnum=0;
	String color="";
        String path = request.getContextPath();
        Account account = (Account)session.getAttribute("account");
        String loginId = account.getAccountId();
        String loginName = account.getAccountName();
        String loginId1 = request.getParameter("loginId");
        
        String bgsign = request.getParameter("bgsign")!=null?request.getParameter("bgsign"):"-1";
        String gsf = request.getParameter("gsf")!=null?request.getParameter("gsf"):"0";
        
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
         String sptype = request.getParameter("sptype")!=null?request.getParameter("sptype"):"0";
         String rgshzt1=request.getParameter("manualauditstatus")!=null?request.getParameter("manualauditstatus"):"-1";
         String gdfs=request.getParameter("gdfs")!=null?request.getParameter("gdfs"):"-1";
         String chanquan = request.getParameter("chanquan");
         String zdname=request.getParameter("zdname")!=null?request.getParameter("zdname"):"0";
         String fwlx = request.getParameter("fwlx")!=null?request.getParameter("fwlx"):"0";
         String htmlsql = request.getParameter("htmlsql");
         String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"请选择";
         String zdlx1=request.getParameter("zdlx1")!=null?request.getParameter("zdlx1"):"";
         String entrytimeQS    = request.getParameter("entrytimeQS")!=null?request.getParameter("entrytimeQS"):"";//开始录入时间
         String entrytimeJS    = request.getParameter("entrytimeJS")!=null?request.getParameter("entrytimeJS"):"";//结束录入时间         
         String sydateks=request.getParameter("sydateks")!=null?request.getParameter("sydateks"):"";//投入使用开始日期
         String sydatejs=request.getParameter("sydatejs")!=null?request.getParameter("sydatejs"):"";//投入使用结束日期
         String entrypensonnel = request.getParameter("entrypensonnel")!=null?request.getParameter("entrypensonnel"):"";//录入人员
       
         String canshuStr="";
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    HttpSession se = request.getSession();
  
    List<zhandianbean> ls=null;
    ls=(List)se.getAttribute("lst");
  
    System.out.println("ls:"+ls);
    
    String yfllxx="";
    
    ArrayList gsflist = new ArrayList();
    ArrayList gdfsaa = new ArrayList();		
    	   ArrayList zdcqaa = new ArrayList();	
    	      ArrayList zdlxaa = new ArrayList();	
    	         ArrayList zdsxaa = new ArrayList();	
    	          ArrayList gxxxaa = new ArrayList();			         	
	gsflist = new ZtCommon().getSelctOptions("FWLX");
	gdfsaa = new ZtCommon().getSelctOptions("GDFS");
	zdcqaa = new ZtCommon().getSelctOptions("ZDCQ");
	zdlxaa = new ZtCommon().getSelctOptions("stationtype");
	zdsxaa = new ZtCommon().getSelctOptions("ZDSX");
	gxxxaa = new ZtCommon().getSelctOptions("gxxx");
%>

<html>
<head>
<title>

</title>
<style>
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
.selected_font{
		width:80px;
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
#div1{width:130px;height:18px; border:1px solid  #0000FF; position:relative;}
ul{ list-style:none; margin:0; padding:0; position:absolute;top:30px;left:-1px;background-color:white; border:1px solid #0000FF;width:130px; display:none;}
#div1 p{ float:left;font-size:12px; width:110px; cursor:pointer;}
#box,#box2,#box3,#box4{padding:0px;border:1px;}
.bttcn{color:BLACK;font-weight:bold;}
 </style>
<style type="text/css" media=print>.noprint{display : none }
a {text-decoration:none}
</style>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type='text/javascript' src='/energy/dwr/util.js'></script>
<script type='text/javascript' src='/energy/dwr/interface/InsertSession.js'></script>
  <script type='text/javascript' src='/energy/dwr/engine.js'></script>

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
			var beginTime = document.form1.beginTime.value
           	if(checkNotnull(document.form1.beginTime,"开始时间")&&checkNotnull(document.form1.endTime,"结束时间")){
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
	function adddegree(){
          document.form1.action=path+"/web/dianliang/addDegree.jsp";
                                document.form1.submit();
        }
        
        function insert(){
        alert("-----");
        var c=document.getElementById(zl);
        alert(c.value);
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
                     
                    if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null)
		          {
	                   alert("城市不能为空");
	   
	           }else{
		           	var reg1 = /^(\d{4})-(0\d{1}|1[0-2])$/;//yyyy-mm
		   			var et = document.form1.entryTime1.value;
					if(reg1.exec(et)||et==""||et==null){
	                   document.form1.action=path+"/web/query/basequery/stationPointQuery.jsp?command=chaxun";
	                   document.form1.submit();
	                }else{
		        		alert("您输入的录入月份有误，请确认后重新输入！");
		        	}
               }
       
    }
  
   //页面载入方法
    function op(){
    	window.open(path+'/InquiryServlet?quiryTaeble=zhandian&tab=jz&flg=into', 'newwindow', 'height=500, width=700,top=0,left=200,toolbar=no,menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
    }
      function exportFData(){
          document.form1.action=path+"/web/dataedit/inputdegreefees.jsp";
                                document.form1.submit();
  }
   $(function(){
   $("#daoru").click(function(){
			exportFData();
		});
   
   

	$("#query").click(function(){
		queryDegree();
		showdiv("请稍等..........");
	});
	$("#baocun").click(function(){
		insert();
	});
	function calltest(data){
	  alert(data);
	  
	  
	}
	$("#dayinBtn").click(function(){
		printad();
	});
	$("#queding").click(function(){
		queding();		
	});
	$("#quxiao").click(function(){
		quxiao();		
	});
});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0501");

%>
<body  class="body">
		<form action="" name="form1" method="POST">
		
			<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			        <tr >
			     
					    	<td colspan="4" width="50" >
											 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:30px;color:black">数据更新</span>	
												</div>
											</td>

	    	        </tr>	    	
					</table>			       
<table  height="23">
<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                	</div></td></tr>
</table>
<% 
/*市	   区县 	乡镇	    站点id	  站点名称   是否启用	
用房类型	  供电方式 单价   站点产权   交流负荷   直流负荷	
宽带设备实占端口数	
语音设备实占端口	
空调1匹数	
空调2匹数 	
共享信息	
远供rru数量	
移动共享设备数量	
电信共享设备数量	
站点属性	
站点类型*/
%>
			<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >  	
			
					
				      <table width="2300px" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
                  		<tr height = "10" class="relativeTag">
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								序号
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								市
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								区县
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								乡镇
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点代码
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点名称
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								是否启用
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								用房类型
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								供电方式
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								单价
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点产权
							</div>
						</td>
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								交流负荷
							</div>
						</td>
						<td width="2%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								直流负荷
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								宽带设备实占端口数
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								语音设备实占端口数
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								空调1匹数
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								空调2匹数
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								共享信息
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								远供rru数量
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								移动共享设备数量
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								电信共享设备数量
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点属性
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								站点类型
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								全省定标电量
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								额定耗电量
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								交流电量占比
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								直流电量占比
							</div>
						</td>
						<td width="3%" height="23" bgcolor="#DDDDDD">
							<div align="center" class="bttcn">
								备注
							</div>
						</td>
					</tr>
	<%
       	 List<ElectricFeesFormBean> fylist = null;
       	 	int countxh=0;
       	 	//彩钢板/砖混/其他
       	 	String shia,xiana,xiaoqua,jzname,qyzt,gdfsa,dianfei,zdcq,zlfh,jlfh,gxxx,ed,id;
       	 	double dka=0,yydk=0,ktyp=0,ktep=0,qsdb,rru=0,ydgxsb=0,dxgxsb=0;
       	 	String zdsx,zdlxa,qs;
       	 	String d,y,kty,kte,rru1,yd1,dx1;
       	 	String dj;
       	 	double jlbl=0;
       	 	String jl;
       	 	double zlbl=0;
       	 	String zl;
       	 if(ls!=null){
       	 for(zhandianbean lst:ls){
       	
       	 id=lst.getId();
       	shia= lst.getShi();
       	 xiana=lst.getXian();
       	 xiaoqua=lst.getXiaoqu();
       jzname=	 lst.getJzname();
       	qyzt= lst.getQyzt();
       	yflx= lst.getYflx();
       	gdfsa= lst.getGdfs();
       	 DecimalFormat df = new DecimalFormat("0.0000");
       dianfei=	 lst.getDianfei();
        dianfei=df.format(Double.parseDouble(dianfei));
       	zdcq= lst.getZdcq();
       	 DecimalFormat dff = new DecimalFormat("0.00");
       	
       	zlfh= lst.getZlfh();
       	jlfh=lst.getJflx();
       	zlfh=dff.format(Double.parseDouble(zlfh));
       		jlfh=dff.format(Double.parseDouble(jlfh));
       		//计算直流电量    直流负荷*54*24/1000/0.85     (（直流电量-本地定标）/本地定标)*100%小于20%
       		double dd=0;
       	dd=	(Double.parseDouble(zlfh))*54*24/1000/0.85;
       	
       		//交流电量               交流负荷*220*24/1000           (（交流电量-本地定标）/本地定标)*100%小于25%
       	double ff=0;
       	ff=Double.parseDouble(jlfh)*220*24/1000;	
       		
       			 DecimalFormat dfff = new DecimalFormat("0");
       	dka= lst.getKdsbdk();
       	yydk=lst.getYysbdk();
       d=	dfff.format(dka);
       y=	dfff.format(yydk);
        DecimalFormat ktk = new DecimalFormat("0.0");
     ktyp=lst.getKtyps();
        kty= ktk.format(ktyp);
       	 ktep=lst.getKteps();
       	 kte=ktk.format(ktep);
       	gxxx= lst.getGxxx();
       	 DecimalFormat ktkt = new DecimalFormat("0");
       	rru= lst.getRru();
       ydgxsb=lst.getYdgxsbsl();
       dxgxsb=lst.getDxgxsbsl();
       rru1=ktkt.format(rru);
       yd1=ktkt.format(ydgxsb);
       dx1=ktkt.format(dxgxsb);
     
       	zdsx =lst.getZdsx();
       	zdlxa= lst.getZdlx();
       	qsdb= lst.getQsdbdl();
       	ed= lst.getEdhdl();
       	ed= dff.format(Double.parseDouble(ed));
      zlbl=((dd-Double.parseDouble(ed))/(Double.parseDouble(ed)))*100;
       	
       	zl=dff.format(zlbl);
       	jlbl=((ff-Double.parseDouble(ed))/(Double.parseDouble(ed)))*100;
      jl=dff.format(jlbl);       	
       	
       	qs=dff.format(qsdb);
    	if(intnum%2==0){
			    color="#FFFFFF";

			 }else{
			    color="#DDDDDD" ;
			}
           intnum++;
           countxh++;
         %>
           <tr bgcolor="<%=color%>">
            <td>
       			<div align="center" ><%=countxh%></div>
       		</td>
       		<td>
       			<div align="left" ><%=shia%></div>
       		</td>
       		<td>
       			<div align="left" ><%=xiana%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=xiaoqua%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=id%></div>
       		</td>
       		
       		
       		<td>
       			<div align="center" ><%=jzname%></div>
       		</td>
       		<td>
       		<select class="selected_font" >
       		<option value="<%=qyzt%>"><%=qyzt%></option>
       		<%if("是".equals(qyzt)){%>
       		<option value="否">否</option>
       		<%}else{%>
       		<option value="是">是</option>
       		<% }%>
       			</select>
       		</td>
       		<td>
       		<select class="selected_font" id="yf<%=countxh%>" name="yflx">
       	    
       	<%
									         	
									         	if(gsflist!=null){
									         		String code="",name="";
									         		int cscount = ((Integer)gsflist.get(0)).intValue();
									         		for(int i=cscount;i<gsflist.size()-1;i+=cscount)
								                    {
								                    	code=(String)gsflist.get(i+gsflist.indexOf("CODE"));
								                    	name=(String)gsflist.get(i+gsflist.indexOf("NAME"));
								                    	
								                    %>
								                    <%if(yflx.equals(name)){ %>
								                     <option value="<%=code%>" selected="selected"><%=name%></option>
								                     
								                    <%} else{
								                    %>
								                    <option value="<%=code%>"><%=name%></option>
								                    <%} %>
								                    <%}
									         	}
									         %>
       			</select>
       		</td>
       		<td>
       		<select class="selected_font" >
       				 	<%
									         	
									         	if(gdfsaa!=null){
									         		String code="",name="";
									         		int cscount = ((Integer)gdfsaa.get(0)).intValue();
									         		for(int i=cscount;i<gdfsaa.size()-1;i+=cscount)
								                    {
								                    	code=(String)gdfsaa.get(i+gdfsaa.indexOf("CODE"));
								                    	name=(String)gdfsaa.get(i+gdfsaa.indexOf("NAME"));
								                    	
								                    %>
								                    <%if(gdfsa.equals(name)){ %>
								                     <option value="<%=code%>" selected="selected"><%=name%></option>
								                     
								                    <%} else{
								                    %>
								                    <option value="<%=code%>"><%=name%></option>
								                    <%} %>
								                    <%}
									         	}
									         %>
       			</select>
       		</td>
       		<td>
       			<div align="right" ><%=dianfei%></div>
       		</td>
       		<td>
       		<select class="selected_font" >
       					<%
									         	if(zdcqaa!=null){
									         		String code="",name="";
									         		int cscount = ((Integer)zdcqaa.get(0)).intValue();
									         		for(int i=cscount;i<zdcqaa.size()-1;i+=cscount)
								                    {
								                    	code=(String)zdcqaa.get(i+zdcqaa.indexOf("CODE"));
								                    	name=(String)zdcqaa.get(i+zdcqaa.indexOf("NAME"));
								                    	
								                    %>
								                    <%if(zdcq.equals(name)){ %>
								                     <option value="<%=code%>" selected="selected"><%=name%></option>
								                     
								                    <%} else{
								                    %>
								                    <option value="<%=code%>"><%=name%></option>
								                    <%} %>
								                    <%}
									         	}
									         %>
       			</select>
       		</td>
       			<td>
       			<input width="5%" type="text" id="j<%=countxh%>" name="jlfh" value="<%=jlfh%>" class="selected_font" onchange="countzf01(<%=countxh%>)"/>
       		</td>
       		<td>
       			<input type="text" id="z<%=countxh%>" name="zlfh" value="<%=zlfh%>" class="selected_font" onchange="countzf01(<%=countxh%>)"/>
       		</td>
       		<td>
       		<input type="text" id="kd<%=countxh%>" name="kd" value="<%=d%>" class="selected_font" />
       		</td>
       		<td>
       			<input type="text" id="yy<%=countxh%>" name="yy" value="<%=y%>" class="selected_font" />
       		</td>
       		<td>
       		<input type="text" id="kty<%=countxh%>" name="kty" value="<%=kty%>" class="selected_font" />
       		</td>
       		
       		<td>
       		<input type="text" id="kte<%=countxh%>" name="kte" value="<%=kte%>" class="selected_font" />
       		</td>
       		
       		<td>
       		<select class="selected_font">
       			<option value="<%=gxxx%>" ><%=gxxx%></option>
       				<%
									         	if(zdcqaa!=null){
									         		String code="",name="";
									         		int cscount = ((Integer)zdcqaa.get(0)).intValue();
									         		for(int i=cscount;i<zdcqaa.size()-1;i+=cscount)
								                    {
								                    	code=(String)zdcqaa.get(i+zdcqaa.indexOf("CODE"));
								                    	name=(String)zdcqaa.get(i+zdcqaa.indexOf("NAME"));
								                    	
								                    %>
								                    <%if(gxxx.equals(name)){ %>
								                     <option value="<%=code%>" selected="selected"><%=name%></option>
								                     
								                    <%} else{
								                    %>
								                    <option value="<%=code%>"><%=name%></option>
								                    <%} %>
								                    <%}
									         	}
									         %>
       		</select>
       		</td>
       		
       		<td>
       		<input type="text" id="rru<%=countxh%>" name="rrt" value="<%=rru1%>" class="selected_font" />
       		</td>
       		
       		<td>
       		<input type="text" id="yd<%=countxh%>" name="yd" value="<%=yd1%>" class="selected_font" />
       		</td>
       		
       		<td>
       		<input type="text" id="dx<%=countxh%>" name="dx" value="<%=dx1%>" class="selected_font" />
       		</td>
       		
       		<td>
       		<select class="selected_font">
       			<%
									         	
									         	if(zdsxaa!=null){
									         		String code="",name="";
									         		int cscount = ((Integer)zdsxaa.get(0)).intValue();
									         		for(int i=cscount;i<zdsxaa.size()-1;i+=cscount)
								                    {
								                    	code=(String)zdsxaa.get(i+zdsxaa.indexOf("CODE"));
								                    	name=(String)zdsxaa.get(i+zdsxaa.indexOf("NAME"));
								                    	
								                    %>
								                    <%if(zdsx.equals(name)){ %>
								                     <option value="<%=code%>" selected="selected"><%=name%></option>
								                     
								                    <%} else{
								                    %>
								                    <option value="<%=code%>"><%=name%></option>
								                    <%} %>
								                    <%}
									         	}
									         %>
       			</select>
       		</td>

             <td>
             <select class="selected_font">
       			<%
									         	
									         	if(zdlxaa!=null){
									         		String code="",name="";
									         		int cscount = ((Integer)zdlxaa.get(0)).intValue();
									         		for(int i=cscount;i<zdlxaa.size()-1;i+=cscount)
								                    {
								                    	code=(String)zdlxaa.get(i+zdlxaa.indexOf("CODE"));
								                    	name=(String)zdlxaa.get(i+zdlxaa.indexOf("NAME"));
								                    	
								                    %>
								                    <%if(zdlxa.equals(name)){ %>
								                     <option value="<%=code%>" selected="selected"><%=name%></option>
								                     
								                    <%} else{
								                    %>
								                    <option value="<%=code%>"><%=name%></option>
								                    <%} %>
								                    <%}
									         	}
									         %>
       			</select>
       		</td>
       		<td>
       			<div align="right" ><%=qs%></div>
       		</td>
       		<td>
       			<input type="text" id="e<%=countxh%>" name="" value="<%=ed%>"  class="selected_font" onchange="countzf01(<%=countxh%>)"/>
       		</td>
 			<td>
       			<input type="text" id="jl<%=countxh%>" name="" value="<%=jl%>%" class="selected_font"  />
       		</td>
       		<td>
       		<input type="text" id="zl<%=countxh%>" name="" value="<%=zl%>%" class="selected_font"  />
       		</td>
       		<td>
       			<div align="center" ><a href="#" onclick="deletea(<%=countxh%>)">删除</a></div>
       		</td>
       </tr>
       <%} }%>
 
      
     
  	 </table> 
	</div>
			<div style="width:100%;height:8%;border:1px" >	
			<table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
               		<div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
          		</div></td>
			</tr>
 
  		<tr>  		
   			<td align="right">   			
             <!-- <%if(permissionRights.indexOf("PERMISSION_PRINT")>=0){%>                   
                                <div id="dayinBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
	 								<img src="<%=path %>/images/dayin.png" width="100%" height="100%">
	 								<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">打印</span>      
								</div> 
                               
                               <%} %>   -->
                              
                               
                              <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                        
		                             <div id="baocun" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:2px">
										 <img src="<%=path %>/images/daoru.png" width="100%" height="100%">
			 							 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>      
									</div>                               
									<%} %> 
									
									
                                  <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%>                                                                                      
                                <div id="daoru" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:6px; " >
	 								 <img src="<%=path %>/images/daoru.png" width="100%" height="100%">
									 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导入</span>      
								</div>
                               <%} %> 
                               
                               
                             
                           </td>  
  					</tr>
  <tr>
  <td>

  </td>
  </tr>
</table>
			</div>
		</form>
	</body>
	
     

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
function exportad(){
            var   zdname="<%=zdname%>";
            
        	document.form1.action=path+"/web/query/basequery/站点信息.jsp?zdname="+zdname+"&whereStr="+whereStr+"&command=daochu";
            document.form1.submit();
   }
   function printad(){
            var curpage = <%=curpage%>;
            //alert(canshuStr);
            window.open(path+"/web/query/basequery/stationpointquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr,'','width=800,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
      
        	//document.form1.action=path+"/web/query/basequery/ammeterdegreesquery_print.jsp?curpage="+curpage+"&whereStr="+whereStr;
            //document.form1.submit();
   }
      
 </script>
 <script type="text/javascript">
function countzf01(num){
var jl="j"+num;//交流负荷id 
var ed="e"+num; //额定耗电量id
var jlbl="jl"+num;//交流比例的id
var zl="z"+num;//直流负荷的id
var zlbl="zl"+num;
var zlfh=document.getElementById(zl);
var edhdl=document.getElementById(ed);
var jlfh=document.getElementById(jl);
var jlbl=document.getElementById(jlbl);
var zlbl=document.getElementById(zlbl);

 Number(jlfh.value)*220*24/1000//交流电量
Number(edhdl.value)//额定耗电量
 jlbl.value=((((Number(jlfh.value)*220*24/1000)-(Number(edhdl.value)))/((Number(edhdl.value))))*100).toFixed(2)+"%";
Number(zlfh.value)*54*24/1000/0.85 //直流电量
 zlbl.value=((((Number(zlfh.value)*54*24/1000/0.85)-(Number(edhdl.value)))/(Number(edhdl.value)))*100).toFixed(2)+"%";
}
 </script>
  <script type="text/javascript">
  function deletea(num){
  
    if(confirm("您确定删除此条信息？")){
    
    
    
    }
  }
 </script>
 
 
 
</html>
