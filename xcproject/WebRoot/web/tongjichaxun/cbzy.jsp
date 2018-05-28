<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.ZhanDianForm,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.Account,com.noki.mobi.common.CommonBean" %>
<%@ page import="com.noki.tongjichaxu.*"%>

<%@ page import="java.text.*"%>

<%
String path = request.getContextPath();
String beginTime = request.getParameter("beginTime")!=null?request.getParameter("beginTime"):"";
String endTime = request.getParameter("endTime")!=null?request.getParameter("endTime"):"";
String jzsx = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
String bzw = request.getParameter("bzw")!=null?request.getParameter("bzw"):"";
String fy = request.getParameter("feiyongtype")!=null?request.getParameter("feiyongtype"):"0";
String zflx= request.getParameter("zflx")!=null?request.getParameter("zflx"):"0";
String shenhe= request.getParameter("shenhe")!=null?request.getParameter("shenhe"):"2";


Account account = (Account) session.getAttribute("account");
String roleId = account.getRoleId();
String s_curpage = request.getParameter("curpage") != null ? request.getParameter("curpage"): "1";
	int count = 0, pagesize = 15, curpage = 1, nextpage = 1, prepage = 1, allpage = 1, xh = 1;
	String b1=request.getParameter("");
	curpage = Integer.parseInt(s_curpage);   
	int intnum=0;
	String color="";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'cbzy.jsp' starting page</title>
 <style type="text/css">
   .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
    }.style1 {
	color: red;
	font-weight: bold;
}
.relativeTag {
	z-index: 10;
	position: relative;
	top: expression(this.offsetParent.scrollTop);
}.bttcn{color:BLACK;font-weight:bold;}
 .selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
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

</style>
<script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script language="javascript" >

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

 function showhb(hStr){
     //alert(hStr);
     window.open("huanbi_hdl_city.jsp?dataStr="+hStr,'','width=600,height=400,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')

 }

function chaxun() {
var bz1=document.form1.beginTime.value;
var bz2=document.form1.endTime.value;
var jzsx=document.form1.jzproperty.value;
	if (document.getElementById("endTime").value == ""
			|| document.getElementById("beginTime").value == "") {
		alert("日期不能为空");
	}else{
		showdiv("请稍等..............");
		document.form1.action = path + "/web/tongjichaxun/cbzy.jsp?bzw=1";
		document.form1.submit();
	}
}

function exportad(){
	var bz1=document.form1.beginTime.value;
	var bz2=document.form1.endTime.value;
	var jzsx=document.form1.jzproperty.value;
		if (document.getElementById("endTime").value == ""
				|| document.getElementById("beginTime").value == "") {
			alert("日期不能为空");
		}else{
			document.form1.action = path + "/web/tongjichaxun/cbzyexport.jsp?bzw=1";
			document.form1.submit();
		}
	}


$(function() {
	$("#query").click(function() {
		chaxun();
	});
	$("#daochuBtn").click(function(){
		exportad();
	});
})
</script>
 </head>
  <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>   
  <body  class="body" style="overflow-x:hidden;">
	 <form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr>
				<td colspan="4" width="50" >
					<div style="width:700px;height:50px">
					
					<span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">全省费用及报账对照统计</span>	
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
		    				<td >报账月份：</td>
		    				<td >
								<input type="text" name="beginTime" value="<%if(null!=request.getParameter("beginTime")) out.print(request.getParameter("beginTime")); %>" 
										readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font"/>
							</td>
	         				<td>至</td>
	         				<td>
	         					<input type="text" name="endTime" value="<%if(null!=request.getParameter("endTime")) out.print(request.getParameter("endTime")); %>" 
	         							readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="selected_font"/>
	         					<span class="style1">*&nbsp;</span>
	         				</td>
	         				<td>站点属性：</td>
	         				<td><select name="jzproperty" style="width:130" class="selected_font">
         							<option value="0">请选择</option>
         							<% ArrayList zdsx = new ArrayList();
	      						 		zdsx = ztcommon.getSelctOptions("zdsx");
	     						 		if(zdsx!=null){
	         								String code="",name="";
	        								int cscount = ((Integer)zdsx.get(0)).intValue();
	        								for(int i=cscount;i<zdsx.size()-1;i+=cscount){
                 								code=(String)zdsx.get(i+zdsx.indexOf("CODE"));
                								name=(String)zdsx.get(i+zdsx.indexOf("NAME"));
                   					%>
                					<option value="<%=code%>"><%=name%></option>
                  					<%}}%></select><span class="style1">&nbsp;</span>
         					</td>
         					<td>费用属性：</td>
         					<td><select name="feiyongtype" style="width:130" class="selected_font">
         							<option value="0">请选择</option>
         							<option value="-1">收入</option>
         							<option value="1">支出</option>
         						</select>
         					</td>
         					<td>支付类型：</td>
         					<td>
                       			<select name="zflx"  class="selected_font" >
		         						<option value="0">请选择</option>
		         								<%
			         								ArrayList dfzflxa = new ArrayList();
			         								dfzflxa = ztcommon.getSelctOptions("DFZFLX");
			         								if(dfzflxa!=null){
			         									String code="",name="";
			         									int cscount = ((Integer)dfzflxa.get(0)).intValue();
			         									int size = dfzflxa.size()-1;
			         									int i;
			         									for(i = cscount;i < size;i += cscount){
		                    								code = (String)dfzflxa.get(i + dfzflxa.indexOf("CODE"));
		                    								name = (String)dfzflxa.get(i + dfzflxa.indexOf("NAME"));
		                    					%>
		                    				<option value="<%=code%>"><%=name%></option>
		                    					<%
		                    							}
			         								}
			         							%>
    	
    		         			</select>
    		         		</td>	
	         				<td >
								<div id="query" style="position:relative;width:59px;height:23px;left:20px;cursor: pointer;TOP:0PX">
								<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
								<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		               			 </div>
							</td>
						</tr>
						<tr class="form_label">
						<td>审核状态：</td>
						<td><select name="shenhe" style="width:130" class="selected_font">
         							<option value="2">财务审核通过</option>
         							<option value="1">业务审核通过</option>
         						</select>
         					</td>
						
						</tr>
					</table>
				</td>
			</tr>
		</table>		
		<table  height="23">
			<tr><td height="5"  colspan="4"><div id="parent" style="display:inline">
                  <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
               </div></td></tr>
  		</table>
  <div style="width: 100%; height: 290px; overflow-x: auto; overflow-y: auto; border: 1px">
   <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
   	<tr class="relativeTag">
   	    <td width="5%" class="relativeTag" bgcolor='#DDDDDD' rowspan="2"><div align="center" class="bttcn">地市</div></td>         
	    <td width="5%" class="relativeTag" bgcolor='#DDDDDD' colspan="6"><div align="center" class="bttcn">核对信息</div></td>    
	    <td width="5%" class="relativeTag" bgcolor='#DDDDDD' colspan="7"><div align="center" class="bttcn">业务活动</div></td> 
		<td width="5%" class="relativeTag" bgcolor='#DDDDDD' colspan="8"><div align="center" class="bttcn">专业</div></td> 	
    </tr> 
	<tr class="relativeTag">
	<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">站点总数</div></td>
	<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">结算周期</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">费用条数</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">业务审核金额</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">财务确认金额</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">增值税金额</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="2%"><div align="center" class="bttcn">比例</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">网络运营</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">市场经营</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">行政综合</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">信息化支撑</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">建设投资</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">代垫电费</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">比例</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">移动专业-2G</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">移动专业-3G</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">固网专业</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">固移共同专业</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">移动共同专业</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">不可分摊专业</div></td>
		<td bgcolor='#DDDDDD' class="relativeTag" width="5%"><div align="center" class="bttcn">比例</div></td>
	</tr>
		<%
		if(bzw.equals("1")){
			String whereStr = "",str="";
			//String whereStr1="",str1="";
			if(beginTime != null && !beginTime.equals("") && !beginTime.equals("0")){
				whereStr=whereStr+" AND to_char(e.accountmonth,'yyyy-mm')>='"+beginTime+"'";
				str=str+" AND to_char(ee.accountmonth,'yyyy-mm')>='"+beginTime+"'";
				//whereStr1=whereStr1+" AND e.accountmonth>='"+beginTime+"'";
				//str1=str1+" AND ee.accountmonth>='"+beginTime+"'";
			}
			if(endTime != null && !endTime.equals("") && !endTime.equals("0")){
				whereStr=whereStr+" AND to_char(e.accountmonth,'yyyy-mm')<='"+endTime+"'";
				str=str+" AND to_char(ee.accountmonth,'yyyy-mm')<='"+endTime+"'";
				//whereStr1=whereStr1+" AND e.accountmonth<='"+endTime+"'";
				//str1=str1+" AND ee.accountmonth<='"+endTime+"'";
			}
			if(jzsx != null && !jzsx.equals("") && !jzsx.equals("0")){
				whereStr=whereStr+" AND z.property='"+jzsx+"'";
				str=str+" AND zz.property='"+jzsx+"'";
				//whereStr1=whereStr1+" AND z.property='"+jzsx+"'";
				//str1=str1+" AND zz.property='"+jzsx+"'";
			}
			if(fy != null && !fy.equals("") && !fy.equals("0")){
				if(fy.equals("1")){
					whereStr = whereStr+"AND e.actualpay >= '0'";
					str = str+"AND ee.actualpay >= '0'";
					//whereStr1 = whereStr1+"AND e.money >= '0'";
					//str1 = str1+"AND ee.money >= '0'";
				}
			if(fy.equals("-1")){
					whereStr = whereStr+"AND e.actualpay < '0'";
					str = str+"AND ee.actualpay < '0'";
					//whereStr1 = whereStr1+"AND e.money < '0'";
					//str1 = str1+"AND ee.money < '0'";
				}
			}
			
			if (zflx != null && !zflx.equals("") && !zflx.equals("0")) {
				whereStr = whereStr + " AND d.DFZFLX='" + zflx + "'";
				str=str+" AND dd.DFZFLX='"+zflx+"'";
			}
			if(shenhe != null && !shenhe.equals("") && !shenhe.equals("0")){
				if(shenhe.equals("2")){
					whereStr = whereStr+"AND e.MANUALAUDITSTATUS = '2'";
					str = str+"AND ee.MANUALAUDITSTATUS = '2'";
					//whereStr1 = whereStr1+"AND e.money >= '0'";
					//str1 = str1+"AND ee.money >= '0'";
				}
			if(shenhe.equals("1")){
					whereStr = whereStr+"AND e.CITYZRAUDITSTATUS = '1'";
					str = str+"AND ee.CITYZRAUDITSTATUS = '1'";
					//whereStr1 = whereStr1+"AND e.money < '0'";
					//str1 = str1+"AND ee.money < '0'";
				}
			}
       	FeiyongbaozhangDao dao = new FeiyongbaozhangDao();
       	HashMap<String,YuejieBean> hs=null;
       	HashMap<String,YuefufeiBean> hm=null;
		HashMap<String,ZHBean> hh = new HashMap<String,ZHBean>();
		ArrayList<String> li = new ArrayList<String>();
		li=dao.getShi();
		DecimalFormat mat=new DecimalFormat("0.00");
		int counts=0,jszqhj=0,diszdidhj=0;
		double ywz=0.0,cwz=0.0,qz=0.0,bl1=0.0,bl2=0.0,cqz=0.0,wlz=0.0,
		scz=0.0,xzz=0.0,xxz=0.0,jsz=0.0,dddfz=0.0,qtz=0.0,g2z=0.0,
		g3z=0.0,gwz=0.0,gyz=0.0,ydz=0.0,bkz=0.0,bl3=0.0,qt1=0.0,zy1=0.0,qt2=0.0,zy2=0.0;
		//遍历hs的key
       	//if(zflx.equals("-1")){
    		 hs = dao.getYJ(whereStr,str);
    		 System.out.println("月结---------------");
    		 if(!hs.isEmpty()){//判断月结Hashmap中是否为空,
    			Set<String> valu = hs.keySet();
    			for(String sss:valu){
    				ZHBean zb = new ZHBean();
    				qt1 = hs.get(sss).getWlyy()+hs.get(sss).getScjy()+hs.get(sss).getXzzh()+hs.get(sss).getXxhzc()+hs.get(sss).getTzjs()+hs.get(sss).getDddf();
    				zb.setCityname(sss);
    				zb.setCount(hs.get(sss).getCount());
    				zb.setYwmoney(hs.get(sss).getYwmoney());
    				zb.setCwmoney(hs.get(sss).getCwmoney());
    				zb.setBili((hs.get(sss).getCwmoney())/(hs.get(sss).getYwmoney()));
    				zb.setWlyy(hs.get(sss).getWlyy());
    				zb.setScjy(hs.get(sss).getScjy());
    				zb.setXzzh(hs.get(sss).getXzzh());
    				zb.setXxhzc(hs.get(sss).getXxhzc());
    				zb.setTzjs(hs.get(sss).getTzjs());
    				zb.setDddf(hs.get(sss).getDddf());
    				zb.setQt(qt1);
    				zb.setYwhdbl(qt1/hs.get(sss).getYwmoney());
    				zb.setYdzy1(hs.get(sss).getYdzy1());
    				zb.setYdzy2(hs.get(sss).getYdzy2());
    				zb.setGwzy(hs.get(sss).getGwzy());
    				zb.setGygtzy(hs.get(sss).getGygtzy());
    				zb.setYdgtzy(hs.get(sss).getYdgtzy());
    				zb.setBkftzy(hs.get(sss).getBkftzy());
    				zy1 = hs.get(sss).getYdzy1()+hs.get(sss).getYdzy2()+hs.get(sss).getGwzy()+hs.get(sss).getGygtzy()+hs.get(sss).getYdgtzy()+hs.get(sss).getBkftzy();
    				zb.setYwbl(zy1/hs.get(sss).getYwmoney());
    				zb.setJszq(hs.get(sss).getJszq());
    				zb.setDiszdid(hs.get(sss).getDiszdid());
    				zb.setZzsje(hs.get(sss).getZzsje());
    				
    				hh.put(sss,zb);
    				}	
    			}
       		//}else if(zflx.equals("1")){
 //   		 	hm = dao.getYFF(whereStr1,str1);
 //   		 	 System.out.println("预支---------------");
 //   		 	if(!hm.isEmpty()){//判断预付费是否为空
 //					Set<String> val = hm.keySet();
 //					for(String ss:val){//遍历城市名
 //					ZHBean zb = new ZHBean();
 //						qt2 = hm.get(ss).getWlyy()+hm.get(ss).getScjy()+hm.get(ss).getXzzh()+hm.get(ss).getXxhzc()+hm.get(ss).getTzjs()+hm.get(ss).getDddf();
 //						zb.setCityname(ss);
 //						zb.setCount(hm.get(ss).getCount());
 //						zb.setYwmoney(hm.get(ss).getYwmoney());
 //						zb.setCwmoney(hm.get(ss).getCwmoney());
 //						zb.setBili((hm.get(ss).getCwmoney())/(hm.get(ss).getYwmoney()));
 //						zb.setWlyy(hm.get(ss).getWlyy());
 //						zb.setScjy(hm.get(ss).getScjy());
 //						zb.setXzzh(hm.get(ss).getXzzh());
 //						zb.setXxhzc(hm.get(ss).getXxhzc());
 //						zb.setTzjs(hm.get(ss).getTzjs());
 //						zb.setDddf(hm.get(ss).getDddf());
 //						zb.setQt(qt2);
 //						zb.setYwhdbl(qt2/hm.get(ss).getYwmoney());
 //						zb.setYdzy1(hm.get(ss).getYdzy1());
 //						zb.setYdzy2(hm.get(ss).getYdzy2());
 //						zb.setGwzy(hm.get(ss).getGwzy());
 //						zb.setGygtzy(hm.get(ss).getGygtzy());
 //						zb.setYdgtzy(hm.get(ss).getYdgtzy());
 //						zb.setBkftzy(hm.get(ss).getBkftzy());
 //						zy2 = hm.get(ss).getYdzy1()+hm.get(ss).getYdzy2()+hm.get(ss).getGwzy()+hm.get(ss).getGygtzy()+hm.get(ss).getYdgtzy()+hm.get(ss).getBkftzy();
 //						zb.setYwbl(zy2/hm.get(ss).getYwmoney());
 //						zb.setJszq(hm.get(ss).getJszq());
 //	    				zb.setDiszdid(hm.get(ss).getDiszdid());
 //						hh.put(ss,zb);
 //					}
 //				}
       		//}else{
//       		 	hs = dao.getYJ(whereStr,str);
//       			hm = dao.getYFF(whereStr1,str1);
//       		 	System.out.println("月结--------预支-------");
//       			Set<String> values = hs.keySet();
//    			Iterator<String> it = values.iterator();
//    			while(it.hasNext()){
//    				String name=(String)it.next();//得到key 城市名
//    				if(hm.containsKey(name)){//判断hm的key是否在hs中存在(一个城市是有2种都有),如果存在相应属性加起来
//    					ZHBean zb = new ZHBean(); //创建总和的Bean
 //   					counts=hs.get(name).getCount()+hm.get(name).getCount();//总条数
//    					ywz = hs.get(name).getYwmoney()+hm.get(name).getYwmoney();//总业务审核金额
//    					cwz = hs.get(name).getCwmoney()+hm.get(name).getCwmoney();//总业务审核金额
//    					bl1 = cwz/ywz;//比例
//    					wlz = hs.get(name).getWlyy()+hm.get(name).getWlyy();//网络运营总金额
//    					scz = hs.get(name).getScjy()+hm.get(name).getScjy();//市场经营总金额
//    					xzz = hs.get(name).getXzzh()+hm.get(name).getXzzh();//行政综合总金额
//    					xxz = hs.get(name).getXxhzc()+hm.get(name).getXxhzc();//信息化支撑总金额
//    					jsz = hs.get(name).getTzjs()+hm.get(name).getTzjs();//投资建设总金额
//    					dddfz = hs.get(name).getDddf()+hm.get(name).getDddf();//代垫电费总金额
//    					qtz = wlz+scz+xzz+xxz+jsz+dddfz;//其他
//    					bl2 = qtz/ywz;//业务活动比例
//    					g2z = hs.get(name).getYdzy1()+hm.get(name).getYdzy1();//移动专业2G总金额
//    					g3z = hs.get(name).getYdzy2()+hm.get(name).getYdzy2();//移动专业3G总金额
//    					gwz = hs.get(name).getGwzy()+hm.get(name).getGwzy();//固网专业总金额
//    					gyz = hs.get(name).getGygtzy()+hm.get(name).getGygtzy();//固移共同专业总金额
//    					ydz = hs.get(name).getYdgtzy()+hm.get(name).getYdgtzy();//移动共同专业总金额
//    					bkz = hs.get(name).getBkftzy()+hm.get(name).getBkftzy();//不可分摊总金额
//    					bl3 = (g2z+g3z+gwz+gyz+ydz+bkz)/ywz;//专业比例
//    					
//    					jszqhj=hs.get(name).getJszq()+hm.get(name).getJszq();//结算周期
//    					diszdidhj=hs.get(name).getDiszdid()+hm.get(name).getDiszdid();//站点总条数
//    					zb.setCityname(name); 
//    					zb.setCount(counts);
//    					zb.setYwmoney(ywz);
//    					zb.setCwmoney(cwz);
//    					zb.setBili(bl1);
//   					zb.setWlyy(wlz);
//    					zb.setScjy(scz);
//    					zb.setXzzh(xzz);
//    					zb.setXxhzc(xxz);
//    					zb.setTzjs(jsz);
//    					zb.setDddf(dddfz);
//    					zb.setQt(qtz);
//    					zb.setYwhdbl(bl2);
//    					zb.setYdzy1(g2z);
//    					zb.setYdzy2(g3z);
//    					zb.setGwzy(gwz);
//    					zb.setGygtzy(gyz);
//    					zb.setYdgtzy(ydz);
//    					zb.setBkftzy(bkz);
//    					zb.setYwbl(bl3);
//    					zb.setJszq(jszqhj);
//    					zb.setDiszdid(diszdidhj);
//    					hh.put(name,zb);//put进第3个HashMap中
//    					it.remove();
//    					hm.remove(name);
//    				}
//    			}
//    			if(!hs.isEmpty()){//判断月结Hashmap中是否为空,
//    				Set<String> valu = hs.keySet();
//    				for(String sss:valu){
//	    				ZHBean zb = new ZHBean();
//	    				qt1 = hs.get(sss).getWlyy()+hs.get(sss).getScjy()+hs.get(sss).getXzzh()+hs.get(sss).getXxhzc()+hs.get(sss).getTzjs()+hs.get(sss).getDddf();
//	    				zb.setCityname(sss);
//	    				zb.setCount(hs.get(sss).getCount());
//	    				zb.setYwmoney(hs.get(sss).getYwmoney());
//	    				zb.setCwmoney(hs.get(sss).getCwmoney());
//	    				zb.setBili((hs.get(sss).getCwmoney())/(hs.get(sss).getYwmoney()));
//	    				zb.setWlyy(hs.get(sss).getWlyy());
//	    				zb.setScjy(hs.get(sss).getScjy());
//	    				zb.setXzzh(hs.get(sss).getXzzh());
//	    				zb.setXxhzc(hs.get(sss).getXxhzc());
//	    				zb.setTzjs(hs.get(sss).getTzjs());
//	    				zb.setDddf(hs.get(sss).getDddf());
//	    				zb.setQt(qt1);
//	    				zb.setYwhdbl(qt1/hs.get(sss).getYwmoney());
//	    				zb.setYdzy1(hs.get(sss).getYdzy1());
//	    				zb.setYdzy2(hs.get(sss).getYdzy2());
//	    				zb.setGwzy(hs.get(sss).getGwzy());
//	    				zb.setGygtzy(hs.get(sss).getGygtzy());
//	    				zb.setYdgtzy(hs.get(sss).getYdgtzy());
//	    				zb.setBkftzy(hs.get(sss).getBkftzy());
//	    				zb.setJszq(hs.get(sss).getJszq());
//    					zb.setDiszdid(hs.get(sss).getDiszdid());
//	    				zy1 = hs.get(sss).getYdzy1()+hs.get(sss).getYdzy2()+hs.get(sss).getGwzy()+hs.get(sss).getGygtzy()+hs.get(sss).getYdgtzy()+hs.get(sss).getBkftzy();
//	    				zb.setYwbl(zy1/hs.get(sss).getYwmoney());
//	    				hh.put(sss,zb);
//    				}	
//    			}
//    			if(!hm.isEmpty()){//判断预付费是否为空
//    				Set<String> val = hm.keySet();
//    				for(String ss:val){//遍历城市名
//    					ZHBean zb = new ZHBean();
//    					qt2 = hm.get(ss).getWlyy()+hm.get(ss).getScjy()+hm.get(ss).getXzzh()+hm.get(ss).getXxhzc()+hm.get(ss).getTzjs()+hm.get(ss).getDddf();
//    					zb.setCityname(ss);
//    					zb.setCount(hm.get(ss).getCount());
//    					zb.setYwmoney(hm.get(ss).getYwmoney());
//    					zb.setCwmoney(hm.get(ss).getCwmoney());
//    					zb.setBili((hm.get(ss).getCwmoney())/(hm.get(ss).getYwmoney()));
//    					zb.setWlyy(hm.get(ss).getWlyy());
//    					zb.setScjy(hm.get(ss).getScjy());
//    					zb.setXzzh(hm.get(ss).getXzzh());
//    					zb.setXxhzc(hm.get(ss).getXxhzc());
//    					zb.setTzjs(hm.get(ss).getTzjs());
//    					zb.setDddf(hm.get(ss).getDddf());
//    					zb.setQt(qt2);
//    					zb.setYwhdbl(qt2/hm.get(ss).getYwmoney());
//    					zb.setYdzy1(hm.get(ss).getYdzy1());
//    					zb.setYdzy2(hm.get(ss).getYdzy2());
//    					zb.setGwzy(hm.get(ss).getGwzy());
//    					zb.setGygtzy(hm.get(ss).getGygtzy());
//    					zb.setYdgtzy(hm.get(ss).getYdgtzy());
//    					zb.setBkftzy(hm.get(ss).getBkftzy());
//    					zb.setJszq(hm.get(ss).getJszq());
//    					zb.setDiszdid(hm.get(ss).getDiszdid());
//    					zy2 = hm.get(ss).getYdzy1()+hm.get(ss).getYdzy2()+hm.get(ss).getGwzy()+hm.get(ss).getGygtzy()+hm.get(ss).getYdgtzy()+hm.get(ss).getBkftzy();
//  					zb.setYwbl(zy2/hm.get(ss).getYwmoney());
//  					hh.put(ss,zb);
//   				}
//    			}
      	 	//}
     	Set<String> keys = hh.keySet();
			for(String i:li){
				for(String ii:keys){
           			if(i.equals(ii)){
					ZHBean zz = hh.get(i);	
				if(intnum%2==0){
		   		 	color="#FFFFFF";
		 		}else{
		    		color="#DDDDDD" ;
				}intnum++;
       %>
       <tr bgcolor="<%=color%>">
        
            <td>
       			<div align="center" ><%=zz.getCityname()%></div>
       		</td>
       			<td>
       			<div align="center" ><%=zz.getDiszdid()%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=zz.getJszq()%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=zz.getCount()%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getYwmoney())%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getCwmoney())%></div>
       		</td>
       		<td>
       			<div align="right" ><%=mat.format(zz.getZzsje())%></div>
       		</td>
       		<td>
       			<div align="right" ><%=mat.format(zz.getBili()*100)%>%</div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getWlyy())%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getScjy())%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getXzzh())%></div>
       		</td>
       		<td>
       			<div align="right" ><%=mat.format(zz.getXxhzc())%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getTzjs())%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getDddf())%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getYwhdbl()*100)%>%</div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getYdzy1())%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getYdzy2())%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getGwzy())%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getGygtzy())%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getYdgtzy())%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getBkftzy())%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=mat.format(zz.getYwbl()*100)%>%</div>
       		</td>
       </tr>
   		<%}}} }%> 
   </table> 
  	 	</div>
 		<table width="60%"  border="0" cellspacing="0" cellpadding="0" align="right" height="5%">
  			<tr align="right">
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
               </td>
  			</tr>
  			<tr>
  			<td >
  			<p >
			<font color="red" size="2" >
  			站点总数：为该地市符合条件的交费的站点总数（不含增值税站点）<br/>
                                费用条数：为该地市符合条件的电费单条数（不含增值税站点的报账单）<br/>
                                结算周期：为该地市符合条件的电费单交费周期（不含增值税站点的报账单）<br/>
                                增值税税额：为该地市符合条件的站点类型为增值税站点的报账电费
             </font>
			</p>
  			</td>
  			
  			</tr>
		</table>
   </div>
</form>
  </body>
  <script type="text/javascript">
	document.form1.zflx.value='<%=zflx%>';
	document.form1.feiyongtype.value='<%=fy%>';
	document.form1.jzproperty.value='<%=jzsx%>';
	document.form1.shenhe.value='<%=shenhe%>';
  </script>
</html>
