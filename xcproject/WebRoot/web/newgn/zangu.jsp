<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.function.*" %>
<%@ page import="java.text.*,java.util.regex.Pattern"%>
<%@ page import="com.noki.mobi.common.CommonBean" %>
<%@ page import="com.ptac.app.electricmanageUtil.Format" %>

<%
String loginName = (String)session.getAttribute("loginName");
Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
String sheng = (String)session.getAttribute("accountSheng");
String roleId = (String)session.getAttribute("accountRole");
String accountname=account.getAccountName();
String agcode1="";
String thisdatetime1=request.getParameter("zgsj");
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
String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";//站点名称
String stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
String dfzflxx1 = request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";
String zgsj= request.getParameter("zgsj")!=null?request.getParameter("zgsj"):"";
String zt= request.getParameter("zt")!=null?request.getParameter("zt"):"1";
String zgqssj= request.getParameter("zgqssj")!=null?request.getParameter("zgqssj"):"";//
String jzproperty= request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";//站点属性
String whereStr="";
String Str="";
String Wstr="";
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String bijiao="";
String color="";

int intnum =0;
String ff="";

if(thisdatetime1!=null){
bijiao=thisdatetime1.substring(0,7);
System.out.println("bijiao:"+bijiao+"  shi:"+shi);
zanguBean beann=new zanguBean();
boolean flag = beann.getRepeat(bijiao,shi);
		if(flag){
			ff="you";
		}  
}
//if(thisdatetime1!=null){
//	bijiao=thisdatetime1.substring(0,7);
	//System.out.println("bijiao:"+bijiao+"  shi:"+shi);
//	zanguBean beann=new zanguBean();
//	List<CityQueryBean> lisst=new ArrayList<CityQueryBean>();
//	lisst=beann.getRepeat(loginId);
//	String qs="";
//		for(CityQueryBean beans:lisst){
//		     qs=beans.getEndtime();
//			if(bijiao.equals(qs)){
//				ff="you";
//				break;
//			}  
//		}
//}
String datetime=new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
	   //日期默认为本月月底
        Date tt1 = new Date(); //得到当前时间
       // tt1 = new Date(tt1.getYear(), tt1.getMonth(), 0); //得到本月最后一天
  		tt1 = new Date(tt1.getYear(), tt1.getMonth() + 1, 0); //得到本月最后一天
  		String s=datetime+"-"+tt1.getDate();
  		if(zgsj==null||"".equals(zgsj)){
  			zgsj=s;
  		
  		}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<style type="text/css">
.style1 {
	color: red;
	font-weight: bold;
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
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
.bttcn{color:black;font-weight:bold;}
</style>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script >
//var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
//oCalendarEn.Init();
//var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
//oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
//oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
//oCalendarChs.oBtnTodayTitle="今天";
//oCalendarChs.oBtnCancelTitle="取消";
//oCalendarChs.Init();
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

    function queryDegree(){
    	var path='<%=path%>';
    	var bt = document.form1.zgsj.value;
		var et = document.form1.shi.value;
		if(bt == ""){
			alert("请选择暂估结束月份！");
			return;
		}
	
	       document.form1.action=path+"/web/newgn/zangu.jsp?command=chaxun";
	       document.form1.submit();
	  	   showdiv("请稍等..............");
    }
$(function(){
    
	$("#query").click(function(){
		queryDegree();
	});
	$("#daochuBtn").click(function(){
		exportad();
	});
	$("#zhongxin").click(function(){
		exportadzhongxin();
	});
	$("#wuzhongxin").click(function(){
		exportadwu();
	});
	$("#baocun").click(function(){
		baocun();
	});
});
</script>
  </head>
  <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
  <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
  <body>
   <form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr>
				<td colspan="8" width="50" >
                <div style="width:700px;height:50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">暂估查询</span>
	              </div></td>
			</tr>
			<tr class="form_label">
				<td colspan="8">
                       <div id="parent" style="display:inline"></div>
                       <div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                </td>
            </tr>
            <tr>
            <td>
            <table><tr  class="form_label">
	         	<td>地市：</td>
	         	<td><select name="shi" id="shi" style="width:130" onchange="changeShi()" class="selected_font">
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
	         	</select>
	         	</td>
	         	<td>区、县：</td>
	         	<td><select name="xian" id="xian" style="width:130" onchange="changeXian()" class="selected_font" >
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
	         	<td>审核截止点</td>
	         	<td>
	         		<select  name="zt" id="zt" style="width:130" class="selected_font" >
	         			<option value="1">业务审核通过</option>
	         		</select>
	         	
	         	</td>
	    
	         	<td>暂估结束时间</td>
		        <td > <input type="text" name="zgsj" class="selected_font" value="" readonly="readonly"  onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"  style="width: 130px;"/><span class="style1">&nbsp;*</span></td>	 
                <td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p></td>
					
         		<td> 
			         <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:100%;TOP:10PX">
					 <img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
					 <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
					</div>
			    </td> 
         					
			</tr>
	</table>
	</td></tr>	
 </table>
 
 <div style="width:88%;" > 
		<p id="box3" style="display:none">
		<table border='0'>
           
           <tr  class="form_label">
                	<td>乡镇：</td>
	         	<td><select name="xiaoqu" id="xiaoqu" style="width:130" class="selected_font">
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
            <td> 站点名称：</td>
           <td><input type="text" class="selected_font" name="zdname" value="<%if (null != request.getParameter("zdname")) out.print(request.getParameter("zdname"));%>" style="width: 130px;"/></td>
      		<td>站点类型：</td>
         	<td><select name="stationtype" class="selected_font" onchange="kzinfo()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = ztcommon.getSelctOptions("StationType");
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
         	<td class="form_label">电费支付类型：</td>
         	<td><select name="dfzflx" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList dfzflx = new ArrayList();
	         	dfzflx = ztcommon.getSelctOptions("DFZFLX");
	         	if(dfzflx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)dfzflx.get(0)).intValue();
	         		for(int i=cscount;i<dfzflx.size()-1;i+=cscount)
                    {
                    	code=(String)dfzflx.get(i+dfzflx.indexOf("CODE"));
                    	name=(String)dfzflx.get(i+dfzflx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	
         	</select></td>
	 </tr> 
	 <tr  class="form_label">
	 	<td>暂估起始时间</td>
	 	<td><input type="text" name="zgqssj" class="selected_font" value=""  onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"  style="width: 130px;"/></td>
		<td>站点属性：</td>
         <td>
         	<select name="jzproperty" style="width:130" onchange="kzinfo()" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsxx = new ArrayList();
	         	zdsxx = ztcommon.getSelctOptions("zdsx");
	         	if(zdsxx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdsxx.get(0)).intValue();
	         		for(int i=cscount;i<zdsxx.size()-1;i+=cscount)
                    {
                    	code=(String)zdsxx.get(i+zdsxx.indexOf("CODE"));
                    	name=(String)zdsxx.get(i+zdsxx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
         	%>
         	</select>
         </td>
	 </tr>

	</table>
	</p>
	</div>	

  <table>
       <tr><td height="5"  colspan="4"  class="form_label">
	         <div id="parent" style="display:inline"></div>
	         <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
            </td>
       </tr>       
 </table>
 <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 	
 <table width="1400" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
 	  <tr height = "23" class="relativeTag">
 	    <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>	                      	
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>  
         <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点ID</div></td>  
        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表ID</div></td> 
        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>      
        <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
         <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td> 
        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估使用时间标志</div></td>
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">最近一次报账抄表时间</div></td>
	    <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估起始时间</div></td>	                      	
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估结束时间</div></td>          
        <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估天数</div></td>
        <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">单价（元/天）</div></td>
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估金额</div></td>   
           
 	  </tr>	
 	  <%   
 	        int countxh=1;
 	        String Wdfshi="";//电费财务，市级 审核通过
 	        String Wyfshi="";//预付费财务，市级审核通过
 	  	
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and zd.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and zd.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" and zd.xiaoqu='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
				whereStr=whereStr+" and zd.jzname like '%"+zdname+"%'";
			}
			if(stationtype1 != null && !stationtype1.equals("") && !stationtype1.equals("0")){
				whereStr=whereStr+" and zd.STATIONTYPE='"+stationtype1+"'";
			}
			if(dfzflxx1 != null && !dfzflxx1.equals("") && !dfzflxx1.equals("0")){
				whereStr=whereStr+" and db.dfzflx = '"+dfzflxx1+"'";
			}
			if(zt != null && !zt.equals("") && !zt.equals("0")){
				Wdfshi=Wdfshi+" and DF.CITYZRAUDITSTATUS = '"+zt+"'";
				Wyfshi=Wyfshi+" and YY.CITYZRAUDITSTATUS = '"+zt+"'";
			}
			if(jzproperty != null && !jzproperty.equals("") && !jzproperty.equals("0")){
				whereStr=whereStr+" and zd.PROPERTY='"+jzproperty+"'";
			}
	  zanguservlet bean = new zanguservlet();
	  ArrayList<CityQueryBean> fylist =null;
	  CityQueryBean beans=null;
	  List<CityQueryBean> listq=new ArrayList<CityQueryBean>();   
	  String jzname="",address="",lastdatetime="",thisdatetime="",tianshu="",danjia="",daye="",stationtype="",dfzflxx="",
 	  dianfeizangu ="",actualpay="",cssytime="",dbid="",edhdl="",ww="",zdid="",heji="",dbname="",property="",qsdbdl="";
 	  String bzw1="";
 	  
 	  double count1=0.0;//合计
 	 double danjia1=0.0;
		if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
    	 fylist = bean.getPageData(whereStr,loginId,Str,Wstr,Wdfshi,Wyfshi,shi);
    	
    	 if(fylist!=null)
 		{
 			for(CityQueryBean list:fylist){
 			thisdatetime1=request.getParameter("zgsj");
 			String bzw="0";//标志着用那个日期进行的暂估   1---最近一次抄表时间  2----电表初始使用时间   3----暂估起始月份
 			beans=new CityQueryBean();
 		    jzname =list.getJzname(); //站点名称		   
 		    stationtype=list.getStationtype();//站点类型
 		    address =list.getAddress();//地区
 		    dfzflxx=list.getDfzflx();//电费支付类型
 		    lastdatetime=list.getStarttime();//上次抄表时间    起始月份
 		    thisdatetime=list.getEndtime();//本次抄表时间         结束月份   （最大值）
 		    cssytime=list.getCssytime();//电表初始使用时间
 		    danjia=list.getDianfei();//电表电费单价
 		    actualpay=list.getActualpay();//实际电费金额
 		    edhdl=list.getEdhdl();//额定耗电量
 		    zdid=list.getZdid();//站点id
 		    property = list.getProperty();//站点属性
 		    qsdbdl = list.getQsdbdl();
 		   if(qsdbdl==null||qsdbdl.equals("")||qsdbdl.equals("null")) qsdbdl="0";
            dbname=list.getDbname();
            if(dbname==null||dbname.equals("null")||dbname.equals(" ")){
             dbname="";
            }
 		    
 		if(null!=thisdatetime&&!"".equals(thisdatetime)&&!" ".equals(thisdatetime)&&!"0".equals(thisdatetime)&&!thisdatetime.equals("20101-21")){
 		//判断预付合同类型 的  结束月份	后面加上天数
 	
 	  if(thisdatetime.length()<=7&&thisdatetime.length()>0&&!thisdatetime.equals("20101-21")){ 
 		    int y,m,tian; 
 		    y=Integer.parseInt(thisdatetime.substring(0,thisdatetime.indexOf("-"))); //截取年份
 		    m=Integer.parseInt(thisdatetime.substring(thisdatetime.indexOf("-")+1,thisdatetime.length())); //截取月份
 		   tian=y+(y-1)/4-(y-1)/100+(y-1)/400;//没有用  可以注释掉
 		   //判断月份的天数
 		    if(m==4||m==6||m==9||m==11){   
 		    	tian=30; 
 		    }else if(m==2){ 
 		    	if((y%4==0&&y%100!=0)||(y%400==0)){
					tian=29; 
				}else{
					tian= 28 ;
				}			
			}else{
				tian=31; 		 	
			} 
 		   String mm="";
 		   if(m<=9){
				mm="0"+m;
			}else{
				mm=m+"";
			}
			  
			  thisdatetime=y+"-"+mm+"-"+tian;
				
 		}}
 		   
 		    if(actualpay==null||actualpay.equals("")||actualpay.equals("null")) actualpay="0";
		    if(danjia==null||danjia.equals("")||danjia.equals("null")) danjia="0";
		    if(edhdl==null||edhdl.equals("")||edhdl.equals("null")) edhdl="0";
		    DecimalFormat mat=new DecimalFormat("0.00");
		   
		    double day1=0.0,days=0.0;//day1--周期
		    
		   //计算单价（元/天） 
		  if(!"0".equals(qsdbdl)&&!"".equals(qsdbdl)&&null!=qsdbdl){
			    //	Calendar lastTime1 = Calendar.getInstance();//获取Calendar对象
	               // lastTime1.setTime(DateFormat.getDateInstance().parse(lastdatetime));//  parse（）从给定字符串的开始解析文本，以生成一个日期格式
	                //
	              //  Calendar thisTime1 = Calendar.getInstance();
				   // thisTime1.setTime(DateFormat.getDateInstance().parse(thisdatetime));
				    //
				   // Long temp1 = thisTime1.getTimeInMillis()-lastTime1.getTimeInMillis();//日期相减
				   // day1 = Math.ceil(temp1/1000/60/60/24.0);//ceil()将小数部分一律向整数部分进位。   计算天数
				   // day1=day1+1.0;//天数加一
				   
				   
			        danjia1 = Double.parseDouble(qsdbdl)*Double.parseDouble(danjia);//单价（元/天）：生产标*电表单价
		    
		  }else{
			  danjia1 =Format.str_d(danjia)*Format.str_d(edhdl) ;// 单价（元/天）：电表单价*额定耗电量
			  day1=0;
			  daye="0";
		  }
		  
		  
		   if("".equals(danjia1)||" ".equals(danjia1)){
			   danjia1=0.0;
		   }
		   //站点类型为小灵通基站、小灵通基站下电暂估结束时间固定为“2014-05-17” 不进行暂估（暂估天数为负数的不进行暂估）
		   if("小灵通基站".equals(stationtype)||"小灵通基站下电".equals(stationtype)){
			   thisdatetime1 ="2014-05-17";
		   }
		   
		   
		   
		  //计算的时间 暂估天数
		  //如果符合这些判断就进入计算暂估天数
		   if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime&&!"0".equals(thisdatetime)&&!" ".equals(thisdatetime)&&!thisdatetime.equals("20101-21")){
			   
			   		  if(!"0".equals(zgqssj)&&!"".equals(zgqssj)&&!" ".equals(zgqssj)&&null!=zgqssj){
				 // if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime&&!thisdatetime.equals("20101-21")){
				  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				  		Date d = sdf.parse(thisdatetime);
				  		boolean flag = d.before(DateFormat.getDateInstance().parse(zgqssj));//和页面上填写的暂估起始时间比较大小，谁大用谁做暂估起始时间
				  		if(flag){
				  		thisdatetime=zgqssj;
				  		 bzw="4";//标志是用的那个时间进行的暂估
				  		}
				  		//}
				  }
			   
			    //暂估开始时间
		   	Calendar lastTime = Calendar.getInstance();
		    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
			    //暂估结束时间
			   Calendar thisTime = Calendar.getInstance();
			   //System.out.println("22222222:"+thisdatetime);
			   thisTime.setTime(DateFormat.getDateInstance().parse(thisdatetime1));//生成日期格式
			    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
			    //暂估天数
			    days = Math.ceil(temp/1000/60/60/24.0);
			    days=days+1.0;  //暂估天数
			    DecimalFormat day2=new DecimalFormat("0");
			    daye=day2.format(days);  
			     bzw="1";//标志是用的那个时间进行的暂估
		   }else{
				  thisdatetime=cssytime;//如果没有上次抄表时间--暂估起始时间=电表初始使用时间
				   bzw="2";//标志是用的那个时间进行的暂估
				  //如果暂估起始时间不为空，并且大于电表初始使用时间，就从暂估起始时间进行暂估数据
				  //System.out.println("2334"+countxh);
				  if(!"0".equals(zgqssj)&&!"".equals(zgqssj)&&!" ".equals(zgqssj)&&null!=zgqssj){
				  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime&&!thisdatetime.equals("20101-21")){
				  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				  		Date d = sdf.parse(thisdatetime);
				  		boolean flag = d.before(DateFormat.getDateInstance().parse(zgqssj));//比较大小
				  		if(flag){
				  			//System.out.println("thisdatetime"+thisdatetime+" zgqssj"+zgqssj);
				  		thisdatetime=zgqssj;
				  		 bzw="3";//标志是用的那个时间进行的暂估
				  		}
				  		}
				  }
				  //如果符合判断条件  进行计算 暂估天数
				  if(!"0".equals(thisdatetime)&&!"".equals(thisdatetime)&&null!=thisdatetime &&!thisdatetime.equals("20101-21")){
					 	Calendar lastTime = Calendar.getInstance();
					    lastTime.setTime(DateFormat.getDateInstance().parse(thisdatetime));
						    //暂估结束时间
						   Calendar thisTime = Calendar.getInstance();
						   thisTime.setTime(DateFormat.getDateInstance().parse(thisdatetime1));
						    Long temp = thisTime.getTimeInMillis()-lastTime.getTimeInMillis();
						    //暂估天数
						    days = Math.ceil(temp/1000/60/60/24.0);
						    days=days+1.0;
						    DecimalFormat day2=new DecimalFormat("0");
						    daye=day2.format(days);
				  }else{ //否则暂估天数为0
					  daye="0";
					  days=0;
					  
				  }
			  
		   }
		  String ddd="0";//暂估天数
		  //如果暂估天数不等于0  进行判断
		 // System.out.println("1111111thisdatetime1:"+thisdatetime1+"  day:"+daye);
		  if(daye!="0"){
			  int dddd=Integer.parseInt(daye)-1;
			  if(dddd<0){ //如果小于0 暂估天数就为0
				dddd=0;
				days=0;
			  }
			   ddd=dddd+"";//暂估天数
			  // System.out.println("ddd:"+ddd); 
		  }
		 
		    DecimalFormat dj=new DecimalFormat("0.0000");
		   
		    danjia=dj.format(danjia1);	 
		    //System.out.println("danjia1"+danjia1+"  danjia:"+Double.parseDouble(danjia)+"  daye"+daye+"  days:"+days+"  ddd:"+Double.parseDouble(ddd));  		    
			 //System.out.println("danjia:"+danjia+" ddd"+ddd);
			dianfeizangu=mat.format(Double.parseDouble(danjia)*Double.parseDouble(ddd));//暂估金额
			dianfeizangu = dianfeizangu != null ? dianfeizangu : ""; 
			String lastadtimew="";
			//日期加一天 暂估起始时间
	     if(!thisdatetime.equals("")&&!thisdatetime.equals("20101-21")){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date dt=sdf.parse(thisdatetime);
			Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加1天
            Date dt1=rightNow.getTime();
            lastadtimew = sdf.format(dt1);
		 }
	        dbid=list.getDbid(); 	
	
	   if(bzw.equals("1")){
	   		bzw1="最近一次报账抄表时间";
	   }else if(bzw.equals("2")){
	   		bzw1="电表初始使用时间";
	   }else if(bzw.equals("3")){
	   		bzw1="暂估起始时间";
	   }else if(bzw.equals("4")){
	   		bzw1="暂估起始时间(填写)";
	   }
             //暂估金额为空的不显示  天数为负数的也不显示
       if(!"0.00".equals(dianfeizangu)&&!"".equals(dianfeizangu)&&!"0".equals(ddd)){
       		//把显示的数据存到list里面 保存到静态数据表
       	    beans.setDbid(dbid);
		    beans.setJzname(jzname);
		    beans.setStationtype(stationtype);
		    beans.setAddress(address);
		    beans.setDfzflx(dfzflxx);
		    beans.setLastaccountmonth(thisdatetime);
		    beans.setZangustartmonth(lastadtimew);
		    beans.setZanguendmonth(thisdatetime1);
		    beans.setDianfei(danjia);
		    beans.setTianshu(ddd);
		    beans.setZangumoney(dianfeizangu);
		    beans.setZdid(zdid);
		    beans.setDbname(dbname);
		    beans.setShi(shi);
		    beans.setZgmonth(bijiao);
		    count1=count1+Double.valueOf(dianfeizangu);
		    heji=mat.format(count1);
		   
	     listq.add(beans);
	     request.getSession().removeAttribute("jzname");
		 request.getSession().setAttribute("jzname",listq);
	   //  System.out.println("ddd:"+ddd);
             
             intnum++; 
             if(intnum%2==0){
			    color="#DDDDDD" ;

			 }else{
			    color="#FFFFFF";
			}
    	 
 	  %>
 	   <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=countxh++%></div>
       		</td>      
            <td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		   <td>
       			<div align="left" ><%=zdid%></div>
       		</td>   
            <td>
       			<div align="left" ><%=dbid%></div>
       		</td>
       		     
            <td>
       			<div align="left" ><%=dbname%></div>
       		</td>
       		<td>
       			<div align="left" ><%=address%></div>
       		</td>
       		 <td>
       			<div align="left" ><%=property%></div>
       		</td>
       		<td>
       			<div align="center" ><%=stationtype%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dfzflxx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=bzw1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=thisdatetime%></div>
       		</td>
			<td>
       			<div align="center" ><%=lastadtimew%></div>
       		</td>
       		<td>
       			<div align="center" ><%=thisdatetime1%></div>
       		</td>          
            <td>
       			<div align="right" ><%=ddd %></div>
       		</td>
       		 <td>
       			<div align="right" ><%=danjia%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=dianfeizangu%></div> 
       		</td>
       		
       		
       </tr>
       <%}%>
       <%}%>
 		
 		<%}%>
 		<tr><td colspan="10" align="center">合计暂估金额</td><td align="right"><%=heji %></td></tr>
 		<%}%>
 		
 		<!--
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
              
            
            </tr>
      <% }}else if(!(intnum > 14)){
    	  for(int i=((intnum-1)%14);i<14;i++){
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
                           
        </tr>
        <% }
       
        
        }%>   
 	   -->
 	   <tr>
 	   <td><input type="hidden" name="ff" value="<%=ff %>" /></td>
 	   <td><input type="hidden" name="shi1" value="<%=shi %>" /></td>
 	   <td><input type="hidden" name="zgmonth" value="<%=bijiao %>" /></td>
 	   
 	   </tr>
 </table>
 </div>
<div style="width:100%;height:8%;border:1px" >	
	<table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		 <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
	     <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div></div></td>
	  </tr>
	  <tr>  		
	     <td align="right">   			
		     <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px; " >
		     <img src="<%=path %>/images/daoru.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
		     </div><!--
	     			
		     <div id="zhongxin" style="width:80px;height:23px;cursor:pointer;float:right;position:relative;right:15px; " >
		     <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">中心成本</span>      
		     </div>
	    			
		     <div id="wuzhongxin" style="width:90px;height:23px;cursor:pointer;float:right;position:relative;right:26px; " >
		     <img src="<%=path %>/images/mmcz.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">无中心成本</span>      
		     </div>
		     -->
		     <% 
		     	if(roleId.equals("66")||roleId.equals("1")){   //判断 如果权限是管理员和市财务就显示保存静态数据按钮
		     		Calendar c = Calendar.getInstance();//
		     		int date = c.get(Calendar.DATE); 
		     		//System.out.println("jjjjjjj:"+date);
		     		if(1<=date&&date<=5){//每个月1号到五号显示保存按钮
		     %>
		     <div id="baocun" style="width:100px;height:23px;cursor:pointer;float:right;position:relative;right:37px; " >
		     <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存静态数据</span>      
		     </div>
		     <%}} %>
	     </td>
	  </tr> 
	</table>
	<input type="hidden" name="lrren" value="<%=accountname %>">
</div>
</form>           
</body>
</html>
<script language="javascript">	
     var path = '<%=path%>';
    function changeShi(){
			var shi = document.form1.shi.value;
			if(shi=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选项","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=shi&pid="+shi,"shi");
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
 function exportad(){
	  if(document.getElementById("shi").value=="" || document.getElementById("zgsj").value==""){
	       alert("暂估日期不能为空！请填写");
	  }else{
         var endzgsj=document.getElementById('zgsj').value;
         var whereStr ="<%=whereStr%>";
         var Wstr ="<%=Wstr%>";
         var Str ="<%=Str%>";
         var Wdfshi="<%=Wdfshi%>";
         var Wyfshi="<%=Wyfshi%>";
         var zgqssj='<%=zgqssj%>';
         var shi = document.getElementById("shi").value;
         
          //alert(whereStr+"Wstr:"+Wstr+"Str:"+Str+"endzgsj"+endzgsj);
     	 document.form1.action=path+"/web/query/basequery/暂估信息.jsp?endzgsj="+endzgsj+"&whereStr="+whereStr+"&Wstr="+Wstr+"&Wdfshi="+Wdfshi+"&Wyfshi="+Wyfshi+"&Str="+Str+"&zgqssj="+zgqssj+"&shi="+shi+"&command=daochu";         
     	 document.form1.submit();
      }
   }
  function exportadzhongxin(){
	  if(document.getElementById("shi").value=="" || document.getElementById("zgsj").value==""){
	       alert("暂估日期不能为空！请填写");
	  }else{
         var endzgsj=document.getElementById('zgsj').value;
         var whereStr ="<%=whereStr%>";
         var Wstr ="<%=Wstr%>";
         var Str ="<%=Str%>";
       //alert(whereStr+"Wstr:"+Wstr+"Str:"+Str+"endzgsj"+endzgsj);
     	window.open(path+"/web/newgn/zanguzhongxin.jsp?endzgsj="+endzgsj+"&whereStr="+whereStr+"&Wstr="+Wstr+"&Str="+Str);         
     	// document.form1.submit();
      }
   }
    function exportadwu(){
	  if(document.getElementById("zgsj").value==""){
	      alert("暂估日期不能为空！请填写");
	  }else{
         var endzgsj=document.getElementById('zgsj').value;
         var whereStr ="<%=whereStr%>";
         var Wstr ="<%=Wstr%>";
         var Str ="<%=Str%>";
        //alert(whereStr+"Wstr:"+Wstr+"Str:"+Str+"endzgsj"+endzgsj);
     	window.open(path+"/web/newgn/zanguwu.jsp?endzgsj="+endzgsj+"&whereStr="+whereStr+"&Wstr="+Wstr+"&Str="+Str);         
     	 // document.form1.submit();
      }
   }
     function baocun(){
    	 if(document.getElementById("ff").value==""){
    		   if(document.getElementById("zgsj").value==""){
			       alert("暂估日期不能为空！请填写");
			  }else{
		          document.form1.action=path+"/servlet/zangu?action=save";
				  document.form1.submit();
				  showdiv("请稍等..............");
		      }
    		 
    	 }else{
    		 if(confirm("您本月已添加！确认覆盖上次信息！")){
    			if(document.getElementById("zgsj").value==""){
			       alert("暂估日期不能为空！请填写");
			  }else{
		          document.form1.action=path+"/servlet/zangu?action=save";
				  document.form1.submit();
				  showdiv("请稍等..............");
		      }
    		 }
    		  
    	 }
	 
   }
		
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.stationtype.value='<%=stationtype1%>';
		document.form1.dfzflx.value='<%=dfzflxx1%>';
		document.form1.zgsj.value='<%=zgsj%>';
		document.form1.zt.value='<%=zt%>';
		document.form1.zgqssj.value='<%=zgqssj%>';
		document.form1.jzproperty.value='<%=jzproperty%>';
		
	
</script>
