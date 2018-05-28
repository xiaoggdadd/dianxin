<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean,com.noki.newfunction.javabean.Zdinfo,com.noki.newfunction.dao.CbzdQuery"%>
<%@ page import="java.text.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<% 
		int intnum=0;
		String color="";
		String zdid="";
		String cbyf="";
		String wjid=request.getParameter("wjid");
		String path = request.getContextPath();
		 CbzdQuery bean=new CbzdQuery();
		  DecimalFormat mat=new DecimalFormat("0.00");
		  String zdid1="",zdnamea="",szdq="",cbyf1="",cbdf="",cbdl="",lrr="",lrsj="",zt="",ydgxsb="",dxgxsb="",g2xq="",g3sq="";
		  // CD.G2,CD.G3,CD.CHANGJIA,CD.ZP,CD.ZS,CD.JZTYPE,CD.SHEBEI,CD.BBU,CD.RRU,CD.YDSHEBEI,CD.GXGWSL
		  String g2="",g3="",changjia="",zp="",zs="",jztype="",shebei="",bbu="",rru="",ydshebei="",gxgwsl="";
		if(wjid!=null){
		List<Zdinfo> fylist=null;
		fylist=bean.seachDatag2(wjid);
			if(fylist!=null){
		for(Zdinfo ls:fylist){
		ydgxsb=ls.getYdgxsb();
		dxgxsb=ls.getDxgxsb();
		g2xq=ls.getG2xq();
		g3sq=ls.getG3sq();
		if("".equals(ydgxsb)||null==ydgxsb){ydgxsb="0";}
		if("".equals(dxgxsb)||null==dxgxsb){dxgxsb="0";}
		if("".equals(g2xq)||null==g2xq){g2xq="0";}
		if("".equals(g3sq)||null==g3sq){g3sq="0";}
		// wjid=ls.getId();
		zdid=ls.getZdid();
		cbdl=ls.getCbdl();
		if(null!=cbdl&&!"".equals(cbdl)){
		cbdl=mat.format(Double.parseDouble(cbdl)*100)+"%";
		}else{
		cbdl="";
		}
		
		zdnamea=ls.getZdname();
		szdq=ls.getShi()+ls.getXian()+ls.getXiaoqu();
		cbyf=ls.getCbsj();
		
		
		//lrr=ls.getSjlrr();
		//if(null==lrr){lrr="";}
		//lrsj=ls.getSjlrsj();
		//if(null==lrsj){lrsj="";}
		g2=ls.getG2();
		if("1".equals(g2)){g2="是";}else{g2="否";}
		g3=ls.getG3();
			if("1".equals(g3)){g3="是";}else{g3="否";}
		changjia=ls.getChangjia();
		if("".equals(changjia)||null==changjia){changjia="";}
		zp=ls.getZp();
		if("".equals(zp)||null==zp){zp="0";}
		zs=ls.getZs();
		if("".equals(zs)||null==zs){zs="0";}
		jztype=ls.getJztype();
		if("".equals(jztype)||null==jztype){jztype="";}
		
		shebei=ls.getShebei();//bbu="",rru="",ydshebei="",gxgwsl="";
		if("".equals(shebei)||null==shebei){shebei="";}
		bbu=ls.getBbu();
		if("".equals(bbu)||null==bbu){bbu="0";}
		rru=ls.getRru();
		if("".equals(rru)||null==rru){rru="0";}
		ydshebei=ls.getYdshebei();
		if("".equals(ydshebei)||null==ydshebei){ydshebei="0";}
		gxgwsl=ls.getGxgwsl();
		if("".equals(gxgwsl)||null==gxgwsl){gxgwsl="0";}
		
		}
		}
		
		}
%>
<head>
<title>整改信息
</title>
<style>
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
.form1{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;   
   height:19px;
   width:130px;
   
}
.form2{
text-align: right;
    border-left-width:0px;
     border-top-width:0px; 
     border-right-width:0px;   
     height:19px;
     width:130px;
   
}

.form {width:130px}
.bttcn{color:black;font-weight:bold;}
 </style>
  <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
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
 <script type="text/javascript">
 var path = '<%=path%>';
		function paidan(){
			var wjid='<%=wjid%>';
			document.form1.action=path+"/servlet/UploadJtReportServlet1?action=spd&chooseIdStr1="+wjid;
	        document.form1.submit(); 
		}
 
 </script>

</head>
<body  class="body">
<form id="form1"  name="form1"  action="" method="post" enctype="multipart/form-data">
	<table>
		<tr><td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;整改信息&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
	</table>  	
  <table width="100%" border="0" cellspacing="1" cellpadding="1" >
<tr>
<td bgcolor="#DDDDDD"   class="form_label">站点编号：</td>
<td><input type="text" id="zdbh" name="zdbh" value="<%=zdid%>" class="form1" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">站点名称：</td>
<td><input type="text" id="zdname" name="zdname" value="<%=zdnamea%>" class="form1" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">所属区域：</td>
<td><input type="text" id="ssdq" name="ssdq" value="<%=szdq%>" class="form1" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">对标月份：</td>
<td><input type="text" id="dbyf" name="dbyf" value="<%=cbyf%>" class="form1" readonly="readonly"></td>
</tr>
<tr>
<td bgcolor="#DDDDDD"   class="form_label">报账月份：</td>
<td><input type="text" id="bzyf" name="bzyf" value="<%=cbyf%>" class="form1" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">超标比例：</td>
<td><input type="text" id="cbbl" name="cbbl" value="<%=cbdl%>" class="form2" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">录入人：</td>
<td><input type="text" id="lrr" name="lrr" value="<%=lrr%>" class="form1" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">录入时间：</td>
<td><input type="text" id="lrsj" name="lrsj" value="<%=lrsj%>" class="form1" readonly="readonly"></td>
</tr>
<tr>
<td bgcolor="#DDDDDD"   class="form_label">2G设备：</td>
<td><input type="text" id="bzyf" name="bzyf" value="<%=g2%>" class="form1" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">3G设备：</td>
<td><input type="text" id="cbbl" name="cbbl" value="<%=g3%>" class="form1" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">厂家：</td>
<td><input type="text" id="lrr" name="lrr" value="<%=changjia%>" class="form1" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">载频：</td>
<td><input type="text" id="lrsj" name="lrsj" value="<%=zp%>" class="form2" readonly="readonly"></td>
</tr>
<tr>
<td bgcolor="#DDDDDD"   class="form_label">载扇：</td>
<td><input type="text" id="bzyf" name="bzyf" value="<%=zs%>" class="form2" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">基站类型：</td>
<td><input type="text" id="cbbl" name="cbbl" value="<%=jztype%>" class="form1" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">设备类型：</td>
<td><input type="text" id="lrr" name="lrr" value="<%=shebei%>" class="form1" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">拉远BBU：</td>
<td><input type="text" id="lrsj" name="lrsj" value="<%=bbu%>" class="form2" readonly="readonly"></td>
</tr>
<tr>
<td bgcolor="#DDDDDD"   class="form_label">远供RRU：</td>
<td><input type="text" id="bzyf" name="bzyf" value="<%=rru%>" class="form2" readonly="readonly"></td>

<td bgcolor="#DDDDDD"   class="form_label">固移共享设备数量：</td>
<td><input type="text" id="lrr" name="lrr" value="<%=gxgwsl%>" class="form2" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">移动共享设备数量：</td>
<td><input type="text" id="cbbl" name="cbbl" value="<%=ydgxsb%>" class="form2" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">电信共享设备数量：</td>
<td><input type="text" id="cbbl" name="cbbl" value="<%=dxgxsb%>" class="form2" readonly="readonly"></td>
</tr>
<tr>
<td bgcolor="#DDDDDD"   class="form_label">2G小区：</td>
<td><input type="text" id="cbbl" name="cbbl" value="<%=g2xq%>" class="form2" readonly="readonly"></td>
<td bgcolor="#DDDDDD"   class="form_label">3G扇区：</td>
<td><input type="text" id="cbbl" name="cbbl" value="<%=g3sq%>" class="form2" readonly="readonly"></td>
</tr>
<tr>
<td bgcolor="#DDDDDD"   class="form_label">整改要求说明</td>
<td colspan="10">
<textarea name="yj"  id="yj" rows="5">

</textarea>
</td>
</tr>
<tr>
<td bgcolor="#DDDDDD" class="form_label"><div>完成时间：</div></td>
							<td><input type="text" class="form" id="entrytimeQS" name="entrytimeQS" value="<%if(null!=request.getParameter("entrytimeQS")) out.print(request.getParameter("entrytimeQS")); %>" onFocus="getDateString(this,oCalendarChs)" /></td>

</tr>
<!-- 
<tr>
<td bgcolor="#DDDDDD"   class="form_label">整改要求说明</td>
<td colspan="10">
<input type="text" id="yj" name="yj" value=""/>
</td>
</tr>
 -->
</table>
<table>
<tr>
<td colspan="9">
         <div style="width: 370px; height: 25px; float:left;" >
			  请选择上传文件：
		      <input type="file" id="fileUp" name="fileUp" height="25px">
		 </div>
	      <div id="uploading" style="position: relative; width: 60px; height: 20px; float:left; pointer;" >
				<img alt=""src="<%=request.getContextPath() %>/images/shangchuan.png" width="100%" height="100%" />
				<span style="font-size: 12px; position: absolute; left: 26px; top: 3px; color: white">派单</span>
		 </div>
		
		<div id="errorInfo" style="width:200px;height:50px;position:relative;font-size: 12px;color:red">
				<c:forEach items="${requestScope.statusInfo}" var="info">
					<br />${info}
				</c:forEach>
		</div>
		
		</td>
</tr>

</table>
<input type="hidden" id="wjid" name="wjid" value='<%=wjid%>'>
</form>
</body>
</html>
	<script language="javascript">
	/*	function shangchuan(){
		// /servlet/qxfj
		var path='<%=path%>';
		var entrytimeQS=document.form1.entrytimeQS.value;
		var sjyq=document.form1.yj.value;
		var id=document.form1.wjid.value;
		//alert(id);
				$("#errorInfo").text("");
				document.form1.action=path+"/servlet/qxfj?id="+id+"&sjyq="+sjyq+"&action=8"+"&bzw=2"+"&entrytimeQS="+entrytimeQS;
				document.form1.submit()
			}
    $(function(){
$("#uploading").click(function(){
			shangchuan();
		});
		
	});
	*/
		</script>
 	<script language="javascript">
// 这是原来的上传文件 到文件夹里的
        function shangchuan(){
		// /servlet/qxfj
		var path='<%=path%>';
		//alert(path);
		var entrytimeQS=document.form1.entrytimeQS.value;
		var sjyq=document.form1.yj.value;
		var id=document.form1.wjid.value;
		//alert(id);
				$("#errorInfo").text("");
				document.form1.action=path+"/UploadB?id="+id+"&sjyq="+sjyq+"&action=8"+"&bzw=1"+"&entrytimeQS="+entrytimeQS;
				document.form1.submit()
			}
    $(function(){
$("#uploading").click(function(){
			shangchuan();
		});
		
	});
		</script>
