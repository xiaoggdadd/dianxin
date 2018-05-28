<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.ptac.app.electricmanage.liuchenghaobill.bean.LiuchBean"%>
<%@ page import="com.ptac.app.electricmanage.liuchenghaobill.dao.LiuchDaoImp"%>
<%
	String liuch = (String)request.getAttribute("liuch");	
	String zjesum = (String)request.getAttribute("zjesum");//总金额sum
	String zsesum = (String)request.getAttribute("zsesum");//增值税额sum
	Double zjesum1 = Double.valueOf(zjesum);
	Double zsesum1 = Double.valueOf(zsesum);
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

.form {width:130px}
.bttcn{color:black;font-weight:bold;}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}

 </style>
</head>

<body  class="body">
	<table>
		<tr><td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;电费信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
	</table>  	
  <table width="100%" height="400px"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
         	<tr height = "23" class="relativeTag ">
  			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">地区</div></td> 
         	<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
  			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td> 
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次电表读数</div></td>
  			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次电表读数</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">倍率</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量调整</div></td>  
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账电量</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">单价</div></td>           
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费调整</div></td> 
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账电费</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td> 
  			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">支付类型</div></td> 
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">票据金额</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期电费</div></td>
  			<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期电量</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上期单价</div></td>
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">供电方式</div></td>  
            <td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">备注</div></td>           
        </tr>
     <c:forEach items="${list}"  var="list" varStatus="status">
		<tr bgcolor="${(status.count)%2==0?'#DDDDDD':'#FFFFFF'}">
       		<td>
				<div align="center">${list.diqu}</div>
			</td>
       		<td>
				<div align="center">${list.zdname}</div>
			</td>
			<td>
				<div align="center">${list.zdlx}</div>
			</td>
       		<td>
       			<div align="center" >${list.lasttime}</div>
       		</td>   		
        	<td>
       			<div align="center" >${list.thistime}</div>
       		</td>
       		<td>
				<div align="center">${list.lastdegree}</div>
			</td>
			<td>
				<div align="center">${list.thisdegree}</div>
			</td>
			<td>
       			<div align="center" >${list.beilv}</div>
       		</td>
       		  	<td>
       			<div align="center" >${list.floatdegree}</div>
       		</td>  
       		<td>
       			<div align="center" >${list.blhdl}</div>
       		</td>
       		  	<td>
       			<div align="center" >${list.price}</div>
       		</td>   	
       		<td>
       			<div align="center" >${list.floatpay}</div>
       		</td>   
       		<td>
				<div align="center">${list.actualpay}</div>
			</td>
       		<td>
       			<div align="center" >${list.accountmonth}</div>
       		</td>   		
        	<td>
       			<div align="center" >${list.zflx}</div>
       		</td>
       		<td>
				<div align="center">${list.pjje}</div>
			</td>
			<td>
				<div align="center">${list.sqdf}</div>
			</td>
			<td>
       			<div align="center" >${list.sqdl}</div>
       		</td>
       		  	<td>
       			<div align="center" >${list.lastprice}</div>
       		</td>  
       		<td>
       			<div align="center" >${list.gdfs}</div>
       		</td>
       		  	<td>
       			<div align="center" >${list.memo}</div>
       		</td>   	
    	</tr>
    </c:forEach>
  </table>
  <table>
  	<tr>
  		<td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;电费分摊信息&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
  </table>  	
  <table width="100%" height="400px"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
     	<tr height = "23" class="relativeTag ">
         	<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">成本中心</div></td>
       		<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">会计科目</div></td>
       		<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">专业明细</div></td>
       		<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">全成本</div></td>   
       		<td width="" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">分摊费用</div></td>          
       </tr>
        <% 
         String shi="";
    	 String xian="";
    	 String NETWORKDF="";            //网络运营线电费（生产）
    	 String INFORMATIZATIONDF="";     //信息化支撑线电费
    	 String ADMINISTRATIVEDF="";     //行政综合线电费（办公）
    	 String MARKETDF="";             //市场营销线电费（营业）
    	 String BUILDDF="";               //建设投资线电费
    	 String dddf="";                 //代垫电费
    	 String qcb="";
    	 String kjkm="";
    	 String zymx="";
    	 String sszy="";
    	 String ftfy="",heji="",htje="";
    	 double hj=0;
    	 double ft=0.0;
    	 int i=0;
    	 
    	 DecimalFormat mat =new DecimalFormat("0.00");
    	 LiuchDaoImp dao = new LiuchDaoImp();
         List<LiuchBean> listt= dao.xiangxift(liuch);
         for(LiuchBean ftbean:listt){
        	i++;
        	shi=ftbean.getCity();
        	xian=ftbean.getXian();
        	NETWORKDF=ftbean.getNETWORKDF();
            INFORMATIZATIONDF=ftbean.getINFORMATIZATIONDF();
            ADMINISTRATIVEDF=ftbean.getADMINISTRATIVEDF();
            MARKETDF=ftbean.getMARKETDF();
            BUILDDF=ftbean.getBUILDDF();
            dddf=ftbean.getDddf();
            qcb=ftbean.getQcb();
            kjkm=ftbean.getKjkm();
            zymx=ftbean.getZymx();
            sszy=ftbean.getSszy();
            if("zylx01".equals(sszy)){
            	ftfy=NETWORKDF;
		    	//生产networkdf
		    }else if("zylx02".equals(sszy)){
		    	ftfy=MARKETDF;
		    	//营业marketdf
		    }else if("zylx03".equals(sszy)){
		    	ftfy=ADMINISTRATIVEDF;
		    	//办公administrativedf
		    }else if("zylx04".equals(sszy)){
		    	ftfy=INFORMATIZATIONDF;
		    	//信息化支撑informatizationdf
		    }else if("zylx05".equals(sszy)){
		    	ftfy=BUILDDF;
		    	//建设投资builddf
		    }else if("zylx06".equals(sszy)){
		    	ftfy=dddf;
		    	//代垫电费
		    }
            if(null==qcb||qcb.equals("")||qcb.equals(" "))qcb="";
            if(null==kjkm||kjkm.equals("")||kjkm.equals(" "))kjkm="";
            if(null==zymx||zymx.equals("")||zymx.equals(" "))zymx="";
            if(null==ftfy||ftfy.equals("")||ftfy.equals(" "))ftfy="0";
           
            if(i==listt.size()){
            	ftfy=mat.format(zjesum1-ft);
            }else{
            	 ftfy=mat.format(Double.parseDouble(ftfy));
            }
            ft+=Double.parseDouble(ftfy);
            String color = i%2==0?"#DDDDDD":"#FFFFFF";
        %>
        <tr bgcolor='<%=color%>'>
         	<td>
       		  <div align="center" ><%=shi+xian%></div>  
       		</td>
       		<td>                                      
       			<div align="center" ><%=kjkm%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zymx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=qcb%></div>
       		</td>   
    		<td>
       			<div align="center" ><%=ftfy%></div>
       		</td>          
       </tr>
       <%
         hj+=Double.parseDouble(ftfy);
        }
        heji=mat.format(hj);
       %>
      <% 
      	double bj= zjesum1-Double.parseDouble(heji);//解决多一分还是少一分的问题
       	if(Math.abs(bj)<=0.02){
         	heji=zjesum;
      }
        %>
       <tr class="relativeTag" bgcolor="#DDDDDD">
         	<td colspan="4">
       		  <div align="center" >总金额（分摊费用总和）</div>
       		</td>
       		<td >
       			<div align="center" ><%=heji%></div>
       		</td>          
       </tr>
       <%
        String zzszd = "",zdf="";; 
       	Double jshj=0.0;//增值税,价税合计
       	zzszd = mat.format(Math.abs(zsesum1));//增值税税额：增值税站点总电费 取绝对值 显示正值 
       	jshj = Double.parseDouble(heji)-(zsesum1);//价税合计：总电费-增值税站点总电费
       	zdf=mat.format(jshj);//格式化
       %>
       <tr class="relativeTag" bgcolor="#DDDDDD">
         	<td colspan="4">
       		  <div align="center" >增值税税额</div>
       		</td>
       		<td >
       			<div align="center" ><%=zzszd%></div>
       		</td>          
       </tr>
       <tr class="relativeTag" bgcolor="#DDDDDD">
         	<td colspan="4">
       			<div align="center" >价税合计</div>
       		</td>
       		<td >
       			<div align="center" ><%=zdf%></div>
       		</td>          
       </tr>
  </table>
</body>
</html>




