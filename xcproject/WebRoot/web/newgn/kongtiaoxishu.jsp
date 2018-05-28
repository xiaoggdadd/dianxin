 <%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.noki.newfunction.dao.*,com.noki.newfunction.javabean.*" %>

<%
	
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();

     String canshuStr="";
    int intnum =0;
    int intnum1 =0;
    String color=""; 
    String permissionRights="";
    String roleId = (String)session.getAttribute("accountRole");
    //System.out.println("roleId:"+roleId);
%>

<html>
<head>
<title>
</title>
<style>
            .btn {
             BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7b9ebd 1px solid
            }
            .btn1_mouseout {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#B3D997); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn1_mouseover {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#CAE4B6); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn2 {padding: 2 4 0
            4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;}
            .btn3_mouseout {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mouseover {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mousedown
            {
             BORDER-RIGHT: #FFE400 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #FFE400 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #FFE400
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #FFE400 1px solid
            }
            .btn3_mouseup {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn_2k3 {
             BORDER-RIGHT: #002D96 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #002D96 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); BORDER-LEFT: #002D96
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #002D96 1px solid
            }
            .style1 {
	color: #FF9900;
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
bttcn{color:white;font-weight:bold;font-size:14px}
 .form    {width:130px}
 .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop); 
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px    
}; 
 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>

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
    function modifyad(zdcode,startmonth,endmonth,jzname){
    	var b=path+"/web/query/caijipoint/collectQueryright.jsp?zdcode="+zdcode+"&startmonth="+startmonth+"&endmonth="+endmonth+"&";			
			b = b.substring(0,b.length-1);
			 var a = " <a href="+b+" target='treeNodeInfo' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
		var c=path+"/web/query/caijipoint/collectQueryMap.jsp?zdcode="+zdcode+"&startmonth="+startmonth+"&endmonth="+endmonth+"&jzname="+jzname+"&";			
			c = c.substring(0,c.length-1);
			 var a = " <a href="+c+" target='treeMap1' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();  
    } 
    
$(function(){
	$("#baocun").click(function(){
		baocun();
	});
});

 function baocun(){
	 var path='<%=path%>';
	 document.form1.action=path+"/web/newgn/ktxs.jsp?command=chaxun";
	 document.form1.submit();
 }
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0301");

System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<body  class="body" >
	
	<form action="" name="form1" method="POST">
  		<table  width="100%"  border="0" cellspacing="0" cellpadding="0" height="15%">
			<tr>
				<td colspan="8" width="50" >
                <div style="width:500px;height=50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">空调系数</span>
	              </div></td>
			</tr>
			</table>
<div style="width:100%;height:200px;overflow-x:auto;overflow-y:auto;border:1px">
  <table width="100%"   border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">

     <tr height = "20" class="relativeTag">
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">城市</div></td>     
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">一月</div></td>
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">二月</div></td>                     
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">三月</div></td>
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">四月</div></td>
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">五月</div></td>
    	 <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">六月</div></td>
    	 <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">七月</div></td>
    	 <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">八月</div></td>
    	 <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">九月</div></td>
    	 <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">十月</div></td>
    	 <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">十一月</div></td>
    	 <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">十二月</div></td>
    	
     </tr>
       <%
        
       String whereStr = "";
       String str="";


        KtxsDao bean = new KtxsDao();
       List<Ktxs> fylist = new ArrayList<Ktxs>();
       	fylist = bean.getyfxs();
       
		String id="",shi="",ymonth="",emonth="",smonth="",simonth="",wmonth="",lmonth="",qmonth="",bmonth="",
		jmonth="",shimonth="",symonth="",semonth="";
		int linenum=0;
		double df=0.0;
		 if(fylist!=null)
		{
			for(Ktxs beans:fylist){
				id=beans.getYfxsid();
				shi=beans.getShiname();
				ymonth=beans.getYmonth();
				emonth=beans.getEmonth();
				smonth=beans.getSmonth();
				simonth=beans.getSimonth();
				wmonth=beans.getWmonth();
				lmonth=beans.getLmonth();
				qmonth=beans.getQmonth();
				bmonth=beans.getBmonth();
				jmonth=beans.getJmonth();
				shimonth=beans.getShimonth();
				symonth=beans.getSymonth();
				semonth=beans.getSemonth();
				DecimalFormat mat=new DecimalFormat("0.00");
				ymonth=mat.format(Double.parseDouble(ymonth)); 
				emonth=mat.format(Double.parseDouble(emonth)); 
				smonth=mat.format(Double.parseDouble(smonth)); 
				simonth=mat.format(Double.parseDouble(simonth)); 
				wmonth=mat.format(Double.parseDouble(wmonth)); 
				lmonth=mat.format(Double.parseDouble(lmonth)); 
				qmonth=mat.format(Double.parseDouble(qmonth)); 
				bmonth=mat.format(Double.parseDouble(bmonth)); 
				jmonth=mat.format(Double.parseDouble(jmonth)); 
				shimonth=mat.format(Double.parseDouble(shimonth)); 
				symonth=mat.format(Double.parseDouble(symonth)); 
				semonth=mat.format(Double.parseDouble(semonth)); 
			
			if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
          intnum++;

       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		<td>
       			<div align="left"><%=shi%></div>
       		</td>
       		<td>
       			<div align="right"><%=ymonth%></div>
       		</td>
       		<td>
       			<div align="right"><%=emonth%></div>
       		</td>
       		<td>
       			<div align="right"><%=smonth%></div>
       		</td>
       		<td>
       			<div align="right"><%=simonth%></div>
       		</td>
       			<td>
       			<div align="right"><%=wmonth%></div>
       		</td>
       			<td>
       			<div align="right"><%=lmonth%></div>
       		</td>
       			<td>
       			<div align="right"><%=qmonth%></div>
       		</td>
       			<td>
       			<div align="right"><%=bmonth%></div>
       		</td>
       			<td>
       			<div align="right"><%=jmonth%></div>
       		</td>
       			<td>
       			<div align="right"><%=shimonth%></div>
       		</td>
       			<td>
       			<div align="right"><%=symonth%></div>
       		</td>
       			<td>
       			<div align="right"><%=semonth%></div>
       		</td>
       		                          		
           
       </tr>
       <%}%>
				<% }%>
				
  	 </table> 										
	</div>	
	<div style="width:100%;height:200px;overflow-x:auto;overflow-y:auto;border:1px">
	<table> 
		<tr>
		<td>
		<div style="width:100%;height:200px;overflow-x:auto;overflow-y:auto;border:1px">
  			<table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
     		<tr height = "20" class="relativeTag">
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">设备直流负荷</div></td>     
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">基站空调系数</div></td>
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">接入网空调系数</div></td>                     
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">乡镇支局（营业网点）空调系数</div></td>
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">局用机房空调系数</div></td>
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">其他空调系数</div></td>
    	 <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">IDC机房空调系数</div></td>

    	
     </tr>
       <%
  

        KtxsDao bean1 = new KtxsDao();
       List<Ktxs> fylist1 = new ArrayList<Ktxs>();
       	fylist1 = bean.getktxs();
       String kszlfh="",jszlfh="",jz="",jrw="",xzzj="",jyjf="",qt="",idcjf="",id1="",zlfh="";
		 if(fylist1!=null)
		{
			for(Ktxs bean2:fylist1){
			   id1=bean2.getKtxsid();
				kszlfh=bean2.getKszlfh();
				jszlfh=bean2.getJszlfh();
				jz=bean2.getJzktxs();
				jrw=bean2.getJrwktxs();
				xzzj=bean2.getXzzjktxs();
				jyjf=bean2.getJyjfktxs();
				qt=bean2.getQtktxs();
				idcjf=bean2.getIdcjfktxs();
				DecimalFormat mat=new DecimalFormat("0.00");
				//kszlfh=mat.format(Double.parseDouble(kszlfh)); 
				//jszlfh=mat.format(Double.parseDouble(jszlfh)); 
				jz=mat.format(Double.parseDouble(jz)); 
				jrw=mat.format(Double.parseDouble(jrw)); 
				xzzj=mat.format(Double.parseDouble(xzzj)); 
				jyjf=mat.format(Double.parseDouble(jyjf)); 
				qt=mat.format(Double.parseDouble(qt)); 
				idcjf=mat.format(Double.parseDouble(idcjf)); 
				
				if(id1.equals("6")){
				zlfh=kszlfh+"A以上";
				}else{
			     zlfh=kszlfh+"-"+jszlfh+"A";
				}
			if(intnum1%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
          intnum1++;

       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		<td>
       			<div align="left"><%=zlfh%></div>
       		</td>
       		<td>
       			<div align="right"><%=jz%></div>
       		</td>
       		<td>
       			<div align="right"><%=jrw%></div>
       		</td>
       		<td>
       			<div align="right"><%=xzzj%></div>
       		</td>
       		<td>
       			<div align="right"><%=jyjf%></div>
       		</td>
       			<td>
       			<div align="right"><%=qt%></div>
       		</td>
       			<td>
       			<div align="right"><%=idcjf%></div>
       		</td>

       		                          		
          
       </tr>
       <%}%>
				<% }%>
				
  	 </table> 										
	</div>	</td>
		<td><div style="width:100%;height:200px;overflow-x:auto;overflow-y:auto;border:1px">
  <table width="100%"   border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">

     <tr height = "20" class="relativeTag">
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">用房类型</div></td>     
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">系数</div></td>
         <td width="6%" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold;">基础系数</div></td>                     

    	
     </tr>
       <%
  

        KtxsDao bean2 = new KtxsDao();
       List<Ktxs> fylist2 = new ArrayList<Ktxs>();
       	fylist2 = bean.getfwxs();
       String fwid="",fwlx="",xs="",jcxs="",fwbzw="";
		 if(fylist1!=null)
		{
			for(Ktxs bean3:fylist2){
			
			
			fwid=bean3.getFwxsid();
			fwlx=bean3.getYfname();
			xs=bean3.getFxxs();
			jcxs=bean3.getJcxs();
			fwbzw=bean3.getFwsjbzw();
			
			
		DecimalFormat mat=new DecimalFormat("0.00");
				xs=mat.format(Double.parseDouble(xs)); 
				jcxs=mat.format(Double.parseDouble(jcxs)); 
			
			if(intnum1%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}
          intnum1++;

       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		<td>
       			<div align="left"><%=fwlx%></div>
       		</td>
       		<td>
       			<div align="right"><%=xs%></div>
       		</td>
       		<td>
       			<div align="right"><%=jcxs%></div>
       		</td>
       </tr>
       <%}%>
				<% }%>
  	 </table> 										
	</div>		
	</td>
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
		     
		     <div id="baocun" style="width:100px;height:23px;cursor:pointer;float:right;position:relative;right:37px; " >
		     <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存静态数据</span>      
		     </div>
	     </td>
	  </tr> 
	</table>

</div>	
<div style="width:100%;height:250px;overflow-x:auto;overflow-y:auto;border:1px">
<table width="100%" cellspacing="1" cellpadding="1" >
<tr>
<td width="40%" >
<div style="width:100%;height:250px;overflow-x:auto;overflow-y:auto;border:1px">
<table height = "200"  border = '0'  class="relativeTag"  bgcolor="#cbd5de" class="form_label">
     <tr>
         <td width="6%" bgcolor="#FFFFFF"><div align="left">基站省定标电量=站点省生产电量*（基础系数+月份系数*基站空调系数+房型系数）</div></td>                       
     </tr>
     <tr>
         <td width="6%" bgcolor="#FFFFFF"><div align="left">接入省定标电量=站点省生产电量*基础系数+月份系数*接入网空调值</div></td>     
     </tr>
     <tr>
         <td width="6%" bgcolor="#FFFFFF"><div align="left">乡镇省定标电量=站点省生产电量*（基础系数+月份系数*乡镇支局空调系数）</div></td>                       
     </tr>
     <tr>
         <td width="6%" bgcolor="#FFFFFF"><div align="left">局用机房省定标电量=站点省生产电量*（基础系数+月份系数*局用机房空调系数）</div></td>                       
     </tr>
     <tr>
         <td width="6%" bgcolor="#FFFFFF"><div align="left">其他能耗省定标电量=站点省生产电量*（基础系数+月份系数*其他空调系数）</div></td>                       
     </tr>
     <tr>
         <td width="6%" bgcolor="#FFFFFF"><div align="left">IDC机房能耗省定标电量=站点省生产电量*（基础系数+月份系数*IDC机房空调系数）</div></td>                       
     </tr>
     <tr>
         <td width="6%" bgcolor="#FFFFFF"><div align="left">营业网点能耗定标=站点省生产电量*基础系数+月份系数*营业网点空调值</div></td>                       
     </tr>
</table>
</div>
</td>
<td width="60%"  valign="top">
<div style="width:100%;height:250px;overflow-x:auto;overflow-y:auto;border:1px">
<table height = "200"  border = '0' class="relativeTag" bgcolor="#cbd5de" class="form_label">
     <tr>
         <td width="20%" bgcolor="#FFFFFF"><div align="left">接入网空调值转换系数</div></td> <td width="80%" bgcolor="#FFFFFF"><div align="left">本地定标、生产标、交直流负荷换算电量取小值       直流电量=直流*1.52 交流电量=交流*5.28 取小值 </div></td>                      
     </tr>
     <tr>
         <td  bgcolor="#FFFFFF"><div align="left">营业网点空调值转换系数</div></td> <td  bgcolor="#FFFFFF"><div align="left">地定标、生产标、交直流负荷换算电量取小值          直流电量=直流*1.52 交流电量=交流*5.28 取小值</div></td>    
     </tr>
     <tr>
         <td rowSpan = "3" bgcolor="#FFFFFF"><div align="left">接入网空调值转换系数</div></td><td  bgcolor="#FFFFFF"><div align="left">接入网空调值（A)/（本地定标、生产标、值A取小值）非零、非空</div></td>     
     </tr>
     <tr>
         <td  bgcolor="#FFFFFF"><div align="left">值A(直流电量与交流电量较小值)非零、非空</div></td>     
     </tr>
     <tr>
         <td  bgcolor="#FFFFFF"><div align="left">直流电量=直流负荷*1.52  交流电量=交流负荷*5.28</div></td>     
     </tr>
     <tr>
         <td  bgcolor="#FFFFFF"><div align="left"></div></td>  <td  bgcolor="#FFFFFF"><div align="left">如果三项（本地定标、生产标、值A取小值）都为空、为0 标记</div></td>     
     </tr>
</table>
</div>
</td>
</tr>
</table>
</div>				
</form>
</body>
</html>
<script type="text/javascript">

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
