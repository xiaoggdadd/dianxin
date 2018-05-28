<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.noki.database.DataBase"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.SiteAnalysisSheet" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%
String path = request.getContextPath();
		int intnum=0;
		String color="";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>详细电费信息
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
<jsp:useBean id="bean" scope="page" class="com.noki.jizhan.SiteAnalysisSheet">
</jsp:useBean>
<body  class="body">
	<table>
		<tr><td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;历史信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
	</table>  	
  <table width=2500px  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
         	<tr height = "23" class="relativeTag ">
            <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点id</div></td>
            <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>            
             <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始电表数</div></td>
  			<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束电表数</div></td>
  			
  			<td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td> 
            <td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量调整</div></td>  
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际用电量</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始月份</div></td>
            
  			<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束月份</div></td>  
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次单价</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费调整</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际电费</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入时间</div></td> 
            
  			<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入人员</div></td>  
  			<td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">初始读数</div></td> 
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">初始使用时间</div></td> 
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">倍率</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属地区</div></td>
            
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电设备</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电流类型</div></td>            
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">抄表时间</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">抄表人</div></td>
            
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交费操作员</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">自动审核状态</div></td>            
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核状态</div></td>
            <td width="3%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">是否有管理类电表</div></td>
        </tr>
       <%
       	String dbid1 = request.getParameter("stationId");    	
       	 ArrayList fylist = new ArrayList();       	 
       if(dbid1 != null && !dbid1.equals("")){	 
       	 fylist = bean.getinformationdb1(dbid1);
       	        	 
       	 String jzcode = "",jzname = "",dbname="",szdq="",lastdegree = "",thisdegree = "",lastdatatime = "",thisdatatime = "",ydltz = "",actualdegree = "",
       	 				startmonth="",endmonth="",danjia="",dftiaozheng="",dianfei="",lrry="",lrsj="",csds = "",cssytime = "",bl = "",dbytpd="";
       	 String jztype="",ydsb="",dllx="",inputoperator="",inputdatetime="",accountmonth="",payoperator="",autoauditstatus="",manualauditstatus="";
		double df=0.0;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
            //=================
		    jzcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
		    jzcode = jzcode != null ? jzcode : "";
		    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
		    jzname = jzname != null ? jzname : "";
		    szdq = (String)fylist.get(k+fylist.indexOf("SZDQ"));
		    szdq = szdq != null ? szdq : "";
		    lrry=(String)fylist.get(k+fylist.indexOf("ENTRYPENSONNEL"));
		    lrry=lrry!=null?lrry:"";
		    if("null".equals(lrry)){
		    lrry="";
		    }
		    lrsj=(String)fylist.get(k+fylist.indexOf("ENTRYTIME"));
		    lrsj=lrsj!=null?lrsj:"";
		    if(lrsj=="0"||lrsj.equals("0")||lrsj.equals("null")){
		      lrsj="";
		    }
		    csds = (String)fylist.get(k+fylist.indexOf("CSDS"));
		    csds = csds != null ? csds : ""; 
		    cssytime = (String)fylist.get(k+fylist.indexOf("CSSYTIME"));
		    cssytime = cssytime != null ? cssytime : ""; 
		    bl = (String)fylist.get(k+fylist.indexOf("BEILV"));
		    bl = bl != null ? bl : "0";
		    if("".equals(bl)||"null".equals("bl")){
		    bl="0";
		    } 
		    
		    lastdatatime = (String)fylist.get(k+fylist.indexOf("LASTDATETIME"));
		    lastdatatime = lastdatatime != null ? lastdatatime : ""; 
		    if(lastdatatime=="0"||lastdatatime.equals("0")||lastdatatime.equals("null")){
		      lastdatatime="";
		    }
		    thisdatatime = (String)fylist.get(k+fylist.indexOf("THISDATETIME"));
		    thisdatatime = thisdatatime != null ? thisdatatime : ""; 
		    if(thisdatatime=="0"||thisdatatime.equals("0")||thisdatatime.equals("null")){
		      thisdatatime="";
		    }
		    ydltz = (String)fylist.get(k+fylist.indexOf("FLOATDEGREE"));
		    ydltz = ydltz != null ? ydltz : "";
		    dbname = (String)fylist.get(k+fylist.indexOf("DBNAME"));
		    dbname = dbname != null ? dbname : "";
			lastdegree = (String)fylist.get(k+fylist.indexOf("LASTDEGREE"));
			lastdegree = lastdegree != null ? lastdegree : "";
		    thisdegree = (String)fylist.get(k+fylist.indexOf("THISDEGREE"));
		    thisdegree = thisdegree != null ? thisdegree : "";
		    actualdegree = (String)fylist.get(k+fylist.indexOf("ACTUALDEGREE"));
		    actualdegree = actualdegree != null ? actualdegree : "";
		    DecimalFormat la1 =new DecimalFormat("0.0");
			 if(actualdegree==null||actualdegree.equals("")||actualdegree.equals("null")||actualdegree.equals("o"))actualdegree="0";
            actualdegree=la1.format(Double.parseDouble(actualdegree));
		    
		    danjia = (String)fylist.get(k+fylist.indexOf("UNITPRICE"));
		    danjia = danjia != null ? danjia : "";
		    dftiaozheng = (String)fylist.get(k+fylist.indexOf("FLOATPAY"));
		    dftiaozheng = dftiaozheng != null ? dftiaozheng : "";
			dianfei = (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));
			dianfei = dianfei != null ? dianfei : "";
		    startmonth = (String)fylist.get(k+fylist.indexOf("STARTMONTH"));
		    startmonth = startmonth != null ? startmonth : "";
		    if(startmonth=="0"||startmonth.equals("0")||startmonth.equals("null")){
		      startmonth="";
		    }
		    endmonth = (String)fylist.get(k+fylist.indexOf("ENDMONTH"));
		    endmonth = endmonth != null ? endmonth : "";
		    if(endmonth=="0"||endmonth.equals("0")||endmonth.equals("null")){
		      endmonth="";
		    }
			 DecimalFormat la =new DecimalFormat("0.0");
			 if(lastdegree==null||lastdegree.equals("")||lastdegree.equals("null")||lastdegree.equals("o"))lastdegree="0";
            lastdegree=la.format(Double.parseDouble(lastdegree));
			DecimalFormat th =new DecimalFormat("0.0");
			  if(thisdegree==null||thisdegree.equals("")||thisdegree.equals("null")||thisdegree.equals("o"))thisdegree="0";
            thisdegree=th.format(Double.parseDouble(thisdegree));
		     DecimalFormat fl=new DecimalFormat("0.0");
		     if(ydltz==null||ydltz.equals("")||ydltz.equals("null")||ydltz.equals("o"))ydltz="0";
            ydltz = fl.format(Double.parseDouble(ydltz));
		    DecimalFormat ac=new DecimalFormat("0.0");
             DecimalFormat dfi =new DecimalFormat("0.00");
             if(dianfei==null||dianfei.equals("")||dianfei.equals("null")||dianfei.equals("o"))dianfei="0";
            dianfei=dfi.format(Double.parseDouble(dianfei));
            DecimalFormat price=new DecimalFormat("0.0000");
            if(danjia==null||danjia.equals("")||danjia.equals("null")||danjia.equals("o"))danjia="0";
            danjia = price.format(Double.parseDouble(danjia));
            DecimalFormat pay=new DecimalFormat("0.00");
            
            if(dftiaozheng==null||dftiaozheng.equals("")||dftiaozheng.equals("null")||dftiaozheng.equals("o"))dftiaozheng="0";
            dftiaozheng = pay.format(Double.parseDouble(dftiaozheng));
		    DecimalFormat a=new DecimalFormat("0.0");
		    
		    jztype = (String)fylist.get(k+fylist.indexOf("JZTYPE"));
		    jztype = jztype != null ? jztype : "";
		    
		    ydsb = (String)fylist.get(k+fylist.indexOf("YDSB"));
		    ydsb = ydsb != null ? ydsb : "";
		    
		    dllx = (String)fylist.get(k+fylist.indexOf("DLLX"));
		    dllx = dllx != null ? dllx : "";
		    if(dllx=="0"||dllx.equals("0")||dllx.equals("null")){
		    	dllx="";
			}
		    
		    inputoperator = (String)fylist.get(k+fylist.indexOf("INPUTOPERATOR"));
		    inputoperator = inputoperator != null ? inputoperator : "";
		    
		    inputdatetime = (String)fylist.get(k+fylist.indexOf("INPUTDATETIME"));
		    inputdatetime = inputdatetime != null ? inputdatetime : "";
		    if(inputdatetime=="0"||inputdatetime.equals("0")||inputdatetime.equals("null")){
		      inputdatetime="";
		    }
		    
		    accountmonth = (String)fylist.get(k+fylist.indexOf("ACCOUNTMONTH"));
		    accountmonth = accountmonth != null ? accountmonth : "";
		    if(accountmonth=="0"||accountmonth.equals("0")||accountmonth.equals("null")){
		      accountmonth="";
		    }
		    
		   	dbytpd = (String)fylist.get(k+fylist.indexOf("DBYTPD"));
		    dbytpd = dbytpd != null ? dbytpd : "";
		    if(dbytpd=="0"||dbytpd.equals("0")||dbytpd.equals("null")){
		      dbytpd="";
		    }
		    
		   	payoperator = (String)fylist.get(k+fylist.indexOf("PAYOPERATOR"));
		    payoperator = payoperator != null ? payoperator : "";
		    
		    manualauditstatus = (String)fylist.get(k+fylist.indexOf("MANUALAUDITSTATUS"));
			manualauditstatus = manualauditstatus != null ? manualauditstatus : ""; 
		    
		    autoauditstatus = (String)fylist.get(k+fylist.indexOf("AUTOAUDITSTATUS"));
			autoauditstatus = autoauditstatus != null ? autoauditstatus : "";	  
			 if(autoauditstatus!=null&&autoauditstatus.equals("1")){
			 autoauditstatus="通过";
       		     }else if(autoauditstatus!=null&&autoauditstatus.equals("0")){
       		     autoauditstatus="未通过";
       		     }
       		

       	

       	    if(manualauditstatus!=null&&manualauditstatus.equals("1")){ 
       	    manualauditstatus="通过";
       	    }else{
       		     manualauditstatus="未通过";
       		     }
       		      DecimalFormat payuuu=new DecimalFormat("0.00");
       		      bl = payuuu.format(Double.parseDouble(bl));
		    
		    if(intnum%2==0){
			    color="#FFFFFF" ;
			 }else{
			    color="#DDDDDD";
			}
           intnum++;
       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=jzcode%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="left" ><%=dbname%></div>
       		</td>   		
        	<td>
       			<div align="right" ><%=lastdegree%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=thisdegree%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=lastdatatime%></div>
       		</td>
       		  	<td>
       			<div align="center" ><%=thisdatatime%></div>
       		</td>   	
       		<td>
       			<div align="right" ><%=ydltz%></div>
       		</td>   	
       		<td>
       			<div align="right" ><%=actualdegree%></div>
       		</td>   
       		<td>
       			<div align="center" ><%=startmonth%></div>
       		</td>
       		<td>
       			<div align="center" ><%=endmonth%></div>
       		</td>    
       		<td>
       			<div align="right" ><%=danjia%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=dftiaozheng%></div>
       		</td> 
       		<td>
       			<div align="right" ><%=dianfei%></div>
       		</td> 
       		<td>
       			<div align="center" ><%=lrsj%></div>
       		</td>    
       		<td>
       			<div align="center" ><%=lrry%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=csds%></div>
       		</td>  
       		 <td>
       			<div align="center" ><%=cssytime%></div>
       		</td>   		
            <td>
       			<div align="right" ><%=bl%></div>
       		</td>
       		<td>
       			<div align="left" ><%=szdq%></div>
       		</td>
       		
       		
       		 <td>
       			<div align="center" ><%=jztype%></div>
       		</td>    
       		<td>
       			<div align="center" ><%=ydsb%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=dllx%></div>
       		</td>  
       		 <td>
       			<div align="center" ><%=inputoperator%></div>
       		</td>   		
            <td>
       			<div align="center" ><%=inputdatetime%></div>
       		</td>
       		<td>
       			<div align="center" ><%=accountmonth%></div>
       		</td> 
       		 <td>
       			<div align="center" ><%=payoperator%></div>
       		</td>   	
       		 <td>
       			<div align="center" ><%=autoauditstatus%></div>
       		</td>  
       		 <td>
       			<div align="center" ><%=manualauditstatus%></div>
       		</td>  	
      		<td>
       			<div align="center" ><%=dbytpd%></div>
       		</td> 
    </tr>
       <%} %>
       <%} %>
       <%}%>
        <% if (intnum==0){
    	  System.out.println("QQQQ"+intnum);
         for(int i=0;i<5;i++){
          if(i%2==0){
			    color="#FFFFFF";
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

</body>
</html>

