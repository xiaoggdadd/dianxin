<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<html>
<% 
		int intnum=0;
		String color="";
%>
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
<jsp:useBean id="bean" scope="page" class="com.noki.electricfees.javabean.ElectricFeesBean">
</jsp:useBean>
<body  class="body">
	<table>
		<tr><td colspan="20"  height="30"><div id="parent" style="display:inline">
            <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;历史信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
       	</div></td></tr>
	</table>  	
  <table width="2800px" height="400px"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
         	<tr height = "23" class="relativeTag ">
         	<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">城市</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点id</div></td>
            <td width="2.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点属性</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表名称</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">省定标</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">省定标电费</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超省定标电费额</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">周期</div></td>            
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始电表数</div></td>
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束电表数</div></td>
  			
  			<td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">上次抄表时间</div></td> 
            <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次抄表时间</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量调整</div></td>  
            
             <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电量备注</div></td>  
            
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际用电量</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">起始月份</div></td>
            
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">结束月份</div></td>  
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">本次单价</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费调整</div></td>
             <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费备注</div></td>
            
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">实际电费</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入时间</div></td> 
            
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入人员</div></td>  
  			<td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">初始读数</div></td> 
            <td width="2%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">初始使用时间</div></td> 
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">倍率</div></td>
            <td width="2.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属地区</div></td>
            
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">集团报表类型</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">用电设备</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电流类型</div></td>            
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">抄表时间</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">抄表人</div></td>
            
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">报账月份</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">交费操作员</div></td>
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">自动审核状态</div></td>            
            <td width="1.8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核状态</div></td>
             
        </tr>
       <%
       	String dbid1 = (String)request.getAttribute("dbid");  
       	String dfzflx=(String)request.getAttribute("dfzflx");  
       

       
       	String dfbzw=(String)request.getAttribute("dfbzw");
       	String bzyf=(String)request.getAttribute("bzyf");

       	 ArrayList fylist = new ArrayList();       	 
       if(dbid1 != null && !dbid1.equals("")){
       	  List<ElectricFeesFormBean> list=bean.getinformationdb(dbid1,bzyf,dfzflx,dfbzw);      	 
       	 String jzcode = "",dftzbz="",jzname = "",ydlbz="",dbname="",szdq="",lastdegree = "",thisdegree = "",lastdatatime = "",thisdatatime = "",ydltz = "",actualdegree = "",
       	 				startmonth="",endmonth="",danjia="",dftiaozheng="",dianfei="",lrry="",lrsj="",csds = "",cssytime = "",bl = "";
       	 String jztype="",ydsb="",dlsh="",dllx="",inputoperator="",inputdatetime="",accountmonth="",payoperator="",autoauditstatus="",manualauditstatus="";
		 String shi="",property="",qsdbdl="",jszq="",sdbdf="",csdbdfe="";
		   
       	 double df=0.0;
		 if(list!=null)
		 for(ElectricFeesFormBean listt:list){
		{
			
            //=================
            jzcode=listt.getZdcode();
		   // jzcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));
		    jzcode = jzcode != null ? jzcode : "";
		    jzname = listt.getJzname();
		    jzname = jzname != null ? jzname : "";
		    
		    shi = listt.getShi();
		    shi = shi != null ? shi : "";
		    
		    property = listt.getProperty();
		    property = property != null ? property : "";
		    
		    qsdbdl = listt.getQsdbdl();
		    qsdbdl = qsdbdl != null ? qsdbdl : "";
		    
		    jszq = listt.getJszq();
		    jszq = jszq != null ? jszq : "";
		    
		    sdbdf = listt.getSdbdf();
		    sdbdf = sdbdf != null ? sdbdf : "";
		    
		    csdbdfe = listt.getCsdbdfe();
		    csdbdfe = csdbdfe != null ? csdbdfe : "";
		    
		    szdq = listt.getSzdq();
		    szdq = szdq != null ? szdq : "";
		    lrry=listt.getEntrypensonnel();
		    lrry=lrry!=null?lrry:"";
		    if("null".equals(lrry)){
		    lrry="";
		    }
		    lrsj=listt.getEntrytime();
		    lrsj=lrsj!=null?lrsj:"";
		    if(lrsj=="0"||lrsj.equals("0")||lrsj.equals("null")){
		      lrsj="";
		    }
		    csds = listt.getCsds();
		    csds = csds != null ? csds : ""; 
		    cssytime = listt.getCssytime();
		    cssytime = cssytime != null ? cssytime : ""; 
		    bl = listt.getBeilv();
		    bl = bl != null ? bl : ""; 
		    DecimalFormat b2 =new DecimalFormat("0.00");		    
		    if(bl==null||bl==""||bl=="o")bl="0";
             df=Double.parseDouble(bl);
             bl=b2.format(df);
		    
		    
		    
		    lastdatatime = listt.getLastdatetime();
		    lastdatatime = lastdatatime != null ? lastdatatime : ""; 
		    if(lastdatatime=="0"||lastdatatime.equals("0")||lastdatatime.equals("null")){
		      lastdatatime="";
		    }
		    thisdatatime = listt.getThisdatetime();
		    thisdatatime = thisdatatime != null ? thisdatatime : ""; 
		    if(thisdatatime=="0"||thisdatatime.equals("0")||thisdatatime.equals("null")){
		      thisdatatime="";
		    }
		    ydltz =listt.getFloatdegree();//  (String)fylist.get(k+fylist.indexOf("FLOATDEGREE"));
		    ydltz = ydltz != null ? ydltz : "";
		    
		    ydlbz = listt.getYdlbz();//(String)fylist.get(k+fylist.indexOf("YDLBZ"));
		    ydlbz = ydlbz != null ? ydlbz : "";
		    
		    
		    dbname =listt.getDbname();//  (String)fylist.get(k+fylist.indexOf("DBNAME"));
		    dbname = dbname != null ? dbname : "";
			lastdegree = listt.getLastdegree();// (String)fylist.get(k+fylist.indexOf("LASTDEGREE"));
			lastdegree = lastdegree != null ? lastdegree : "";
		    thisdegree = listt.getThisdegree();// (String)fylist.get(k+fylist.indexOf("THISDEGREE"));
		    thisdegree = thisdegree != null ? thisdegree : "";
		    actualdegree = listt.getActualdegree();// (String)fylist.get(k+fylist.indexOf("ACTUALDEGREE"));
		    actualdegree = actualdegree != null ? actualdegree : "";
		    DecimalFormat la1 =new DecimalFormat("0.0");
			 if(actualdegree==null||actualdegree.equals("")||actualdegree.equals("null")||actualdegree.equals("o"))actualdegree="0";
            actualdegree=la1.format(Double.parseDouble(actualdegree));
		    
		    danjia =listt.getUnitprice();// (String)fylist.get(k+fylist.indexOf("UNITPRICE"));
		    danjia = danjia != null ? danjia : "";
		    dftiaozheng = listt.getFloatpay();// (String)fylist.get(k+fylist.indexOf("FLOATPAY"));
		    dftiaozheng = dftiaozheng != null ? dftiaozheng : "";
		    
		    dftzbz = listt.getDftzbz();// (String)fylist.get(k+fylist.indexOf("DFTZBZ"));
		    dftzbz = dftzbz != null ? dftzbz : "";
		    
			dianfei = listt.getActualpay();// (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));
			dianfei = dianfei != null ? dianfei : "";
		    startmonth =listt.getStartmonth();//  (String)fylist.get(k+fylist.indexOf("STARTMONTH"));
		    startmonth = startmonth != null ? startmonth : "";
		    if(startmonth=="0"||startmonth.equals("0")||startmonth.equals("null")){
		      startmonth="";
		    }
		    endmonth = listt.getEndmonth();// (String)fylist.get(k+fylist.indexOf("ENDMONTH"));
		    endmonth = endmonth != null ? endmonth : "";
		    if(endmonth=="0"||endmonth.equals("0")||endmonth.equals("null")){
		      endmonth="";
		    }
			 DecimalFormat la =new DecimalFormat("0.0");
			 if(lastdegree==null||lastdegree.equals("")||lastdegree.equals("null")||lastdegree.equals("o")||lastdegree.equals(" "))lastdegree="0";
			 
           lastdegree=la.format(Double.parseDouble(lastdegree));
			DecimalFormat th =new DecimalFormat("0.0");
			  if(thisdegree==null||thisdegree.equals("")||thisdegree.equals("null")||thisdegree.equals("o")||thisdegree.equals(" "))thisdegree="0";
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
		    
		    jztype = listt.getJztype();// (String)fylist.get(k+fylist.indexOf("JZTYPE"));
		    jztype = jztype != null ? jztype : "";
		    
		    ydsb =listt.getYdsb();//  (String)fylist.get(k+fylist.indexOf("YDSB"));
		    ydsb = ydsb != null ? ydsb : "";
		    
		    dllx = listt.getDllx();// (String)fylist.get(k+fylist.indexOf("DLLX"));
		    dllx = dllx != null ? dllx : "";
		    
		    inputoperator = listt.getInputoperator();// (String)fylist.get(k+fylist.indexOf("INPUTOPERATOR"));
		    inputoperator = inputoperator != null ? inputoperator : "";
		    
		    inputdatetime =listt.getInputdatetime();//  (String)fylist.get(k+fylist.indexOf("INPUTDATETIME"));
		    inputdatetime = inputdatetime != null ? inputdatetime : "";
		    if(inputdatetime=="0"||inputdatetime.equals("0")||inputdatetime.equals("null")){
		      inputdatetime="";
		    }
		    
		    accountmonth = listt.getAccountmonth();// (String)fylist.get(k+fylist.indexOf("ACCOUNTMONTH"));
		    accountmonth = accountmonth != null ? accountmonth : "";
		    if(accountmonth=="0"||accountmonth.equals("0")||accountmonth.equals("null")){
		      accountmonth="";
		    }
		    
		   	payoperator = listt.getPayoperator();// (String)fylist.get(k+fylist.indexOf("PAYOPERATOR"));
		    payoperator = payoperator != null ? payoperator : "";
		    
		    manualauditstatus =listt.getManualauditstatus();//  (String)fylist.get(k+fylist.indexOf("MANUALAUDITSTATUS"));
			manualauditstatus = manualauditstatus != null ? manualauditstatus : ""; 
		    
		    autoauditstatus = listt.getAutoauditstatus();// (String)fylist.get(k+fylist.indexOf("AUTOAUDITSTATUS"));
			autoauditstatus = autoauditstatus != null ? autoauditstatus : "";	    
		    
		     dlsh = listt.getDlsh();// (String)fylist.get(k+fylist.indexOf("AUTOAUDITSTATUS"));
			dlsh = dlsh != null ? dlsh : "";	    
		    
		    
		    if(intnum%2==0){
			    color="#FFFFFF" ;
			 }else{
			    color="#DDDDDD";
			}
           intnum++;
       %>
       <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=shi%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jzcode%></div>
       		</td>
       		<td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=property%></div>
       		</td>
       		<td>
       			<div align="left" ><%=dbname%></div>
       		</td>
       		<td>
       			<div align="center" ><%=qsdbdl%></div>
       		</td>
       		<td>
       			<div align="center" ><%=sdbdf%></div>
       		</td>
       		<td>
       			<div align="center" ><%=csdbdfe%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jszq%></div>
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
       			<div align="right" ><%=ydlbz%></div>
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
       			<div align="right" ><%=dftzbz%></div>
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
          	<%if(autoauditstatus!=null&&autoauditstatus.equals("1")&&dlsh!=null&&dlsh.equals("1")){ %>
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
    </tr>
       
       <%} }%>
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




