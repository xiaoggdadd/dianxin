<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean,com.noki.mobi.common.AccountJzqa,com.noki.mobi.common.zdbzbeanB" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.*" %>
<%
String dbid=request.getParameter("dbid")!=null?request.getParameter("dbid"):"";
String bzmonth=request.getParameter("bzmonth")!=null?request.getParameter("bzmonth"):"";
String sjyf=request.getParameter("sjyf")!=null?request.getParameter("sjyf"):"";
String shi=request.getParameter("shi")!=null?request.getParameter("shi"):"";
String xian=request.getParameter("xian")!=null?request.getParameter("xian"):"";
String xiaoqu=request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"";
String yf=request.getParameter("yf")!=null?request.getParameter("yf"):"";
String dbyt=request.getParameter("dbyt")!=null?request.getParameter("dbyt"):"";
String zdid=request.getParameter("zdid")!=null?request.getParameter("zdid"):"";
String entrytime=request.getParameter("entrytime")!=null?request.getParameter("entrytime"):"";
 if(null !=shi  && !shi.equals("") && !shi.equals("0")){
  	 			String whereStr="";
				if(null !=shi  && !shi.equals("") && !shi.equals("0")){
          			whereStr=whereStr+" and zd.shi='"+shi+"'";
          		}
          		if(null != xian && !xian.equals("") && !xian.equals("0")){              	
          			whereStr=whereStr+" and zd.xian='"+xian+"'";
          		}
          		if(null != zdid && !zdid.equals("") && !zdid.equals("0")){              	
          			whereStr=whereStr+" and d.zdid='"+zdid+"'";
          		}		
          		if(null != dbid && !dbid.equals("") && !dbid.equals("0")){              	
          			whereStr=whereStr+" and d.dbid='"+dbid+"'";
          		}
          		if(null != dbyt && !dbyt.equals("") && !dbyt.equals("0")){
          			 if("3".equals(dbyt)){
          			 	whereStr=whereStr+" and d.dbyt='dbyt01'";
          			 }else if("2".equals(dbyt)){
          			 	whereStr=whereStr+" and d.dbyt='dbyt03'";
          			 }             	
          			
          		}

//如果电量管理的合理，在添加电费单的的时候  电量，本次抄表时间会自动带出
	String path = request.getContextPath();
	AccountJzqa beanxx = new AccountJzqa();
	Account account = (Account)session.getAttribute("account");
        String roleId = account.getRoleId();
        String accountid=account.getAccountName();
          if(dbid!=null){       	
          // 站点信息
            AmmeterDegreeFormBean bean = new AmmeterDegreeFormBean();
        	/*
        	 获取站点和电表的信息
        	
        	**/
        	bean = beanxx.getAmmeterDegreeAllInfo1(whereStr);
        	String diqu=bean.getDiqu();
        	String stationtype=bean.getStationtype();
        	String gsf=bean.getGsf();
        	String jztype=bean.getJztype();
        	String dfzflx=bean.getDfzflx();
        	String beilv=bean.getMultiplyingpower();
        	String linelosstype=bean.getLinelosstype();
        	String linelossvalue=bean.getLinelossvalue();
        	String fkzq=bean.getFkzq();
        	String dianfei=bean.getDianfei();
        	String ammeterid=bean.getAmmeterid();
        	String edhdl=bean.getEdhdl();
        	String itemvalue=bean.getItemvalue();
        	String shifou=bean.getShifou();
        	AmmeterDegreeFormBean beanad = new AmmeterDegreeFormBean();	
            if(ammeterid!=null){
	          if("3".equals(yf)){
            /*
                 获取上次电表读数
            **/
            	beanad = beanxx.getLastAmmeterDegree1(zdid,dbid,sjyf,entrytime);
            }else if("2".equals(yf)){
            	beanad = beanxx.getLastAmmeterDegree2(zdid,dbid,sjyf,entrytime);
            }
            DecimalFormat mat =new DecimalFormat("0.0");
            DecimalFormat mat1 =new DecimalFormat("0.00");
            /*
                  获取管理电表的本次电表读数 
            **/
            //上次电表读数
            String scdbds=beanad.getLastdegree();
        	scdbds = scdbds != null ? scdbds : "0";
        	//上次次抄表时间
            String scdbsj=beanad.getLastdatetime();
            scdbsj = scdbsj !=null ? scdbsj : "";
            //本次电表读数
            String bcdbds=beanad.getThisdegree();
            bcdbds = bcdbds !=null ? bcdbds : "0";
            //本次抄表时间
            String bcdbsj=beanad.getThisdatetime();
            bcdbsj = bcdbsj !=null ? bcdbsj : "";
            //用电量   
             String ydl = beanad.getYdl();
             if(ydl==null||"".equals(ydl)) ydl="0.0";
             ydl = mat.format(Double.parseDouble(ydl));
            //开始月份
            String startmonth = beanad.getStartmonth();
            startmonth = startmonth !=null ? startmonth : "";
            //结束月份
            String endmonth = beanad.getEndmonth();
            endmonth = endmonth !=null ? endmonth : "";

        	//实际电费
        	String actualpay = beanad.getActualpay();
        	actualpay = actualpay!=null ? actualpay : "0.00";        	
        	if("".equals(actualpay)||"null".equals(actualpay)){
        	 actualpay="0.00";
        	}
        	//实际电量
        	String blhdl = beanad.getBlhdl();
        	blhdl = blhdl!=null ? blhdl : "0.0";        	
        	if("".equals(blhdl)||"null".equals(blhdl)){
        	 	blhdl="0.0";
        	}
        	blhdl = mat.format(Double.parseDouble(blhdl));
        	//电量调整 
        	String floatdegree = beanad.getFloatdegree();
        	floatdegree = floatdegree!=null ? floatdegree : "0.0";        	
        	if("".equals(floatdegree)||"null".equals(floatdegree)){
        	 	floatdegree="0.0";
        	}
        	floatdegree = mat.format(Double.parseDouble(floatdegree));
        	//报账月份
        	String accountmonth = beanad.getAccountmonth();
            accountmonth = accountmonth !=null ? accountmonth : "";
            //电量备注 
            String memodl = beanad.getMemo();
            memodl = memodl !=null ? memodl : "";
            //电费备注
            String memodf = beanad.getMemodf();
            memodf = memodf !=null ? memodf : "";
            //本次录入时间
            String entime= beanad.getEntrytime();
            entime = entime != null ? entime : "";
            //票据类型 
            String notetypeid=beanad.getNotetypeid();
            notetypeid = notetypeid != null ? notetypeid : "";
            //票据编号
            String noteno=beanad.getNoteno();
            noteno = noteno != null ? noteno : "";
            //票据时间 
            String notetime=beanad.getNotetime();
            notetime = notetime != null ? notetime : "";
            //抄表操作员
            String inputoperator=beanad.getInputoperator();
            inputoperator = inputoperator != null ? inputoperator : "";
            //交费时间
            String paydatetime=beanad.getPaydatetime();
            paydatetime = paydatetime != null ? paydatetime : "";
            //交费操作员
            String payoperator = beanad.getPayoperator();
            payoperator = payoperator != null ? payoperator : "";
            //票据金额
        	double pjje=beanad.getPjje();
        	//网络运营线电量（生产）
        	String scdl = beanad.getScdl();
        	scdl = scdl!=null ? scdl : "0.0";        	
        	if("".equals(scdl)||"null".equals(scdl)){
        	 	scdl="0.0";
        	}
        	scdl = mat.format(Double.parseDouble(scdl));
        	//信息化支撑线电量
        	String xxhdl = beanad.getXxhdl();
        	xxhdl = xxhdl!=null ? xxhdl : "0.0";        	
        	if("".equals(xxhdl)||"null".equals(xxhdl)){
        	 	xxhdl="0.0";
        	}
        	xxhdl = mat.format(Double.parseDouble(xxhdl));
        	//行政综合线电量（办公）
        	String bgdl = beanad.getBgdl();
        	bgdl = bgdl!=null ? bgdl : "0.0";        	
        	if("".equals(bgdl)||"null".equals(bgdl)){
        	 	bgdl="0.0";
        	}
        	bgdl = mat.format(Double.parseDouble(bgdl));
        	//市场营销线电量（营业）
        	String yydl = beanad.getYydl();
        	yydl = yydl!=null ? yydl : "0.0";        	
        	if("".equals(yydl)||"null".equals(yydl)){
        	 	yydl="0.0";
        	}
        	yydl = mat.format(Double.parseDouble(yydl));
        	//建设投资线电量
        	String jstzdl = beanad.getJstzdl();
        	jstzdl = jstzdl!=null ? jstzdl : "0.0";        	
        	if("".equals(jstzdl)||"null".equals(jstzdl)){
        	 	jstzdl="0.0";
        	}
        	jstzdl = mat.format(Double.parseDouble(jstzdl));
        	//代垫电费电量
        	String dddfdl = beanad.getDddfdl();
        	dddfdl = dddfdl!=null ? dddfdl : "0.0";        	
        	if("".equals(dddfdl)||"null".equals(dddfdl)){
        	 	dddfdl="0.0";
        	}
        	dddfdl = mat.format(Double.parseDouble(dddfdl));
 			////网络运营线电量（生产）
 			String scdf = beanad.getScdf();
        	scdf = scdf!=null ? scdf : "0.00";        	
        	if("".equals(scdf)||"null".equals(scdf)){
        	 	scdf="0.00";
        	}
        	scdf = mat1.format(Double.parseDouble(scdf));
 			//信息化支撑线电费
 			String xxhdf = beanad.getXxhdf();
        	xxhdf = xxhdf!=null ? xxhdf : "0.00";        	
        	if("".equals(xxhdf)||"null".equals(xxhdf)){
        	 	xxhdf="0.00";
        	}
        	xxhdf = mat1.format(Double.parseDouble(xxhdf));
 			//行政综合线电费（办公）
 			String bgdf = beanad.getBgdf();
        	bgdf = bgdf!=null ? bgdf : "0.00";        	
        	if("".equals(bgdf)||"null".equals(bgdf)){
        	 	bgdf="0.00";
        	}
        	bgdf = mat1.format(Double.parseDouble(bgdf));
 			//市场营销线电费（营业）
 			String yydf = beanad.getYydf();
        	yydf = yydf!=null ? yydf : "0.00";        	
        	if("".equals(yydf)||"null".equals(yydf)){
        	 	yydf="0.00";
        	}
        	yydf = mat1.format(Double.parseDouble(yydf));
 			//建设投资线电费
 			String jstzdf = beanad.getJstzdf();
        	jstzdf = jstzdf!=null ? jstzdf : "0.00";        	
        	if("".equals(jstzdf)||"null".equals(jstzdf)){
        	 	jstzdf="0.00";
        	}
        	jstzdf = mat1.format(Double.parseDouble(jstzdf));
 			//代垫电费
 			String dddfdf = beanad.getDddfdf();
        	dddfdf = dddfdf!=null ? dddfdf : "0.00";        	
        	if("".equals(dddfdf)||"null".equals(dddfdf)){
        	 	dddfdf="0.00";
        	}
        	dddfdf = mat1.format(Double.parseDouble(dddfdf));          

              //直流负荷
              String zlfh = bean.getZlfh();
              if(zlfh==null||"".equals(zlfh)) zlfh="0";
              DecimalFormat mat2 =new DecimalFormat("0.00");
              zlfh = mat2.format(Double.parseDouble(zlfh));
              //交流负荷
              String jlfh = bean.getJlfh();
              if(jlfh==null||"".equals(jlfh)) jlfh="0";
              DecimalFormat mat3 =new DecimalFormat("0.00");
              jlfh = mat3.format(Double.parseDouble(jlfh));
              //pue
              String pue = beanad.getPue();
              if(pue==null||"".equals(pue)) pue="0";
              DecimalFormat mat4 =new DecimalFormat("0.00");
              pue = mat4.format(Double.parseDouble(pue));  
             //费用 调整 
 			String floatpay = beanad.getFloatpay();
        	floatpay = floatpay!=null ? floatpay : "0.00";        	
        	if("".equals(floatpay)||"null".equals(floatpay)){
        	 	floatpay="0.00";
        	}
        	floatpay = mat1.format(Double.parseDouble(floatpay));     
%>
<html>
<head>
<title>

</title>

<style>

.style1 {
	color: black;
	font-weight: bold;
}
.form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	width:130px;
}
.form_labell{
	font-family: 宋体;
	font-size: 12px;
	width:70px;
}
 .form_label2{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
 }
.formm{
   text-align: right;
   height:19px;
   width:80px;
}
.formr{
   text-align: right;
   height:19px;
   width:130px;
}
.form1{
    border-left-width:0px; border-top-width:0px; border-right-width:0px;   
   height:19px;
   width:130px;
   
   
}
.form2{
   border-left-width:0px; border-top-width:0px; border-right-width:0px;
   text-align: right;
   height:19px;
   width:130px;   
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
</head>
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean"></jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<body  class="body" style="overflow-x:hidden;">
	<br><LINK href="../../../images/css.css" type="text/css" rel=stylesheet>
	<form action="" name="form1" method="POST">

  <table border="0" width="100%">
  	<tr>
  		<td width="83%" scope="2">
  <table width="100%" border="0" cellspacing="1" cellpadding="1" >
      <tr>        
        <td bgcolor="#DDDDDD" class="form_label"> <input type="hidden" name="accountid" value="<%=accountid %>" class="form1" /><div>地区：</div></td>
        <td><input type="hidden" name="ammeteridFk" value="<%=ammeterid %>"/><input type="text" name="shi"  readonly="true" value="<%=diqu%>"  class="form1" /></td>
		<td bgcolor="#DDDDDD"   class="form_label"><div>站点类型：</div></td>
        <td><input type="text" name="stationtype"  readonly="true" value="<%=stationtype%>"  class="form1" /></td>
        <td bgcolor="#DDDDDD"   class="form_label"><div>归属方：</div></td>
        <td><input type="text" name="gsf"  readonly="true" value="<%=gsf%>"  class="form1" /></td>
     </tr>
     <tr>
     	<td bgcolor="#DDDDDD"   class="form_label"><div>集团报表类型：</div></td>
        <td><input type="text" name="jztype"  readonly="true" value="<%=jztype%>"  class="form1" /></td>
        <td bgcolor="#DDDDDD"   class="form_label"><div>结算周期：</div></td>
        <td><input type="text" name="gsf"  readonly="true" value="<%=fkzq %>"  class="form1" /></td>
        <td bgcolor="#DDDDDD"   class="form_label"><div>电费支付类型：</div></td>
        <td><input type="text" name="jztype"  readonly="true" value="<%=dfzflx %>"  class="form1" /></td>

     </tr>
     <tr>
        <td bgcolor="#DDDDDD"   class="form_label"><div>倍率：</div></td>
        <td><input type="text" name="mpower"  readonly="true" value="<%=beilv%>"   class="form1" /></td>
        <td bgcolor="#DDDDDD"  class="form_label"><div>线损类型：</div></td>
        <td><input type="text" name="linelosstype"  readonly="true" value="<%=linelosstype%>"   class="form1" /></td>
        <td bgcolor="#DDDDDD"   class="form_label"><div>线损值：</div></td>
        <td><input type="text" name="linelossvalue"  readonly="true" value="<%=linelossvalue%>"   class="form1"/></td>
     </tr>
     <tr><td colspan="6" class="form_label"><img src="../../images/v.gif" width="15" height="15" />电费信息</td>
     </tr>

  	 <tr>
         <td bgcolor="#DDDDDD"   class="form_label"><div>上次电表读数：</div></td>        
         <td><input type="text" name="lastdegree" value="<%=scdbds%>" readonly="true" class="form2"/></td>
         <td bgcolor="#DDDDDD"   class="form_label"><div>本次电表读数：</div></td>
        <td><input type="text" name="thisdegree" value="<%=bcdbds%>" readonly="true"  class="form2" />
         
          <td bgcolor="#DDDDDD" class="form_label"><div>用电量：</div> </td>
         <td><input type="text" name="actualdegree" value="<%=ydl%>" readonly="true"  class="form2" /></td>
        
     </tr>
     <tr>
          <td bgcolor="#DDDDDD" class="form_label"><div>上次抄表时间：</div></td>  
         <td><input type="text" id="lastdatetime" name="lastdatetime" value="<%=scdbsj%>" readonly="true"  class="form1"/> </td>
          
         <td bgcolor="#DDDDDD" class="form_label"><div>本次抄表时间：</div></td>
         <td><input type="text" id="thisdatetime" name="thisdatetime" value="<%=bcdbsj %>" readonly="true"  class="form1"/></td>  
        
         <td bgcolor="#DDDDDD" class="form_label"><div>用电量调整：</div></td>
         <td><input type="text" name="floatdegree" value="<%=floatdegree%>" readonly="true"  class="form2" /></td>        
        
     </tr>
     <tr>
        <td bgcolor="#DDDDDD"   class="form_label"><div>本次单价：</div></td>
         <td><input type="text" name="unitprice"  readonly="true" value="<%=dianfei%>" class="form2" /></td>
         <td bgcolor="#DDDDDD" class="form_label"><div>起始年月：</div></td>
         <td><input type="text" name="startmonth" value="<%=startmonth%>" readonly="true"  class="form1" /></td>           
          <td bgcolor="#DDDDDD" class="form_label"><div>实际用电量：</div></td>
         <td><input type="text" id="blhdl" name="blhdl" value="<%=blhdl%>" readonly="true"  class="form2"/></td>    
     </tr>
     <tr>
         <td height="19" bgcolor="#DDDDDD" class="form_label"><div>电量备注：</div></td>
         <td><input type="text" size="20" name="memoam" value="<%=memodl%>" readonly="true"  class="form1" /></td>
         <td bgcolor="#DDDDDD" class="form_label"><div>结束年月：</div></td>
         <td><input type="text" name="endmonth" value="<%=endmonth%>"  readonly="true"  class="form1"/></td>

         <td bgcolor="#DDDDDD"   class="form_label"><div>费用调整：</div></td>
         <td><input type="text" name="floatpay" value="<%=floatpay%>"  readonly="true"  class="form2" /></td>
         
     </tr>
     <tr>
        <td bgcolor="#DDDDDD" class="form_label"><div>电费备注：</div></td>
         <td><input type="text" name="memo" value="<%=memodf%>"  readonly="true"  class="form1" /></td>
         <td bgcolor="#DDDDDD" class="form_label"><div>报账月份：</div></td>
	      <td><input type="text" name="accountmonth" value="<%=accountmonth%>" readonly="true"  class="form1" /></td>
          <td bgcolor="#DDDDDD" class="form_label"><div>实际电费金额：</div></td>
         <td><input type="text" name="actualpay" value="<%=actualpay%>" readonly="true"  class="form2"/></td>
     </tr>
     <tr>
          <td class="form_label"><div></div></td>
         <td height="19px"><div></div></td>
     </tr>
     <tr>
         <td bgcolor="#DDDDDD" class="form_label"><div>票据类型：</div> </td>
		<td><input type="text" name="notetypeid" value="<%=notetypeid%>" readonly="true"  class="form2" /></td>
          <td bgcolor="#DDDDDD" class="form_label"><div>票据编号：</div></td>
	      <td><input type="text" name="noteno" value="<%=noteno%>" readonly="true"  class="form1" /></td>
	      <td bgcolor="#DDDDDD" class="form_label"><div>票据时间：</div></td>
         <td><input type="text" name="notetime" value="<%=notetime%>" readonly="true"  class="form1" /></td> 
     </tr>
     <tr> 
         <td bgcolor="#DDDDDD" class="form_label"><div>抄表操作员：</div></td>
         <td><input type="text" name="inputoperator" value="<%=inputoperator%>" readonly="true"  class="form1" /></td>     
          <td bgcolor="#DDDDDD" class="form_label"><div>交费时间：</div></td>
	      <td><input type="text" name="paydatetime" value="<%=paydatetime%>" readonly="true"  class="form1" /></td>
	      <td bgcolor="#DDDDDD" class="form_label"><div>交费操作员：</div></td>
	      <td><input type="text" name="payoperator" value="<%=payoperator%>" readonly="true"  class="form1" /></td>	  
     </tr>
     <tr>
     <td bgcolor="#DDDDDD" class="form_label">票据金额：</td>
     <td><input type="text" id="pjje" name="pjje" value="<%=pjje%>" readonly="true"  class="form2"></td>
     </tr>
     <tr><td colspan="6" class="form_label"><img src="../../images/v.gif" width="15" height="15" />辅助信息</td>
     </tr>
     <tr>
      <td bgcolor="#DDDDDD"   class="form_label"><div>录入时间：</div></td>
         <td><input type="text" name="sclrsj"  readonly="true" value="<%=entime%>"  readonly="true"  class="form1"/></td>
       <td bgcolor="#DDDDDD"   class="form_label"><div>直流负荷：</div></td>
         <td><input type="text" name="zlfh"  readonly="true" value="<%=zlfh%>"  readonly="true"  class="form2" /></td>
      <td bgcolor="#DDDDDD"   class="form_label"><div>交流负荷：</div></td>
         <td><input type="text" name="jlfh"  readonly="true" value="<%=jlfh%>"  readonly="true"  class="form2" /></td>
     </tr>
     <tr>
      <td bgcolor="#DDDDDD"   class="form_label"><div>PUE值：</div></td>
         <td><input type="text" name="pue"  readonly="true" value="<%=pue%>" readonly="true"  class="form2" /></td>
     </tr>
  </table>
  </td>
  <td width="17%">
			 <table border="0" width="100%">
			 	<tr>
			 		<td>
			 		<fieldset id="regist" >
				 		<table border="0" width="100%">
				 			<tr>
						 		<td colspan="2"><div align="center"><b><font size="2">电量分摊</font></b><hr/></div></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">生产</td><td><input type="text" name="scdl" value="<%=scdl%>" readonly="true" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">办公</td><td><input type="text" name="bgdl" value="<%=bgdl%>"  readonly="true" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">营业</td><td><input type="text" name="yydl" value="<%=yydl%>" readonly="true" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">信息化支撑</td><td><input type="text" name="xxhdl" value="<%=xxhdl%>" readonly="true" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">建设投资</td><td><input type="text" name="jstzdl" value="<%=jstzdl%>" readonly="true" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">代垫电量</td><td><input type="text" name="dddfdl" value="<%=dddfdl%>" readonly="true" class="formm"/></td>
						 	</tr>
						 </table>
					 </fieldset>
					 </td>
			 	</tr>
			 	<tr>
			 		<td>
			 		<fieldset id="regist">
			 			<table border="0" width="100%">
				 			<tr align="center">
						 		<td colspan="2"><div align="center"><b><font size="2">电费分摊</font></b><hr/></div></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">生产</td><td><input type="text" name="scdff" value="<%=scdf%>" readonly="true" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">办公</td><td><input type="text" name="bgdf" value="<%=bgdf%>" readonly="true" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">营业</td><td><input type="text" name="yydf" value="<%=yydf%>" readonly="true" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">信息化支撑</td><td><input type="text" name="xxhdf" value="<%=xxhdf%>" readonly="true" class="formm" /></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">建设投资</td><td><input type="text" name="jstzdf" value="<%=jstzdf%>" readonly="true" class="formm"/></td>
						 	</tr>
						 	<tr>
						 		<td class="form_labell">代垫电费</td><td><input type="text" name="dddfdf" value="<%=dddfdf%>" readonly="true" class="formm"/></td>
						 	</tr>
						 </table>
					   </fieldset>
			 		</td>
			 	</tr>
			 </table>
		</td>
	</tr>
</table>
 
  </form> 
  <%}}}%>
    <table width="100%" border="0" cellspacing="1" cellpadding="1" class="form_label2">
	<tr class="form_label2">
		<td colspan="13"><br/><div id="parent" style="display:inline"><div style="width:50px;display:inline;"><hr></div>&nbsp;历史信息&nbsp;<div style="width:300px;display:inline;"><hr></div></div>	</td>
    </tr>
   	<tr bgcolor="#DDDDDD" style="color:black;font-weight:bold;" align="center">
   	 	<th>站点名称</th> 
   	 	<th>站点ID</th>
   	 	<th>电表名称</th>
   	 	<th>电表ID</th>
   	 	<th>上次抄表时间</th>
   	 	<th>上次抄表读数</th>
   	 	<th>本次抄表时间</th>
   	 	<th>本次抄表读数</th>
   	 	<th>周期</th>
   	 	<th>实际用电量</th>			
   	 	<th>实际日均耗电量</th>
   	 	<th>开始月份</th>
   	 	<th>结束月份</th>
 	</tr>		
  	 	<%
	  	 		AccountJzqa   bean = new AccountJzqa();
		  	 	List<zdbzbeanB> fylist = null;
		  	 	int intnum=0;String color="";
		  	 	fylist=bean.getlishixinxi(zdid,dbid,yf,entrytime);
		  	 	        String jzname="",zdcode="";
		  	 	       	 String dbname="",dbid1="",sccbsj="",sccb="",bccbsj="",bccb="",zq="",blhdl="",rjdl="",startmonth="",endmonth="";
		  	 	       	 DecimalFormat ww =new DecimalFormat("0.0");
		  	 	       	if(fylist!=null){
		  	 	        		for(zdbzbeanB bean1:fylist){	
		  	 	        			jzname=bean1.getZdname();
		  	 	        			if(null==jzname||"null".equals(jzname)){
		  	 	        			 	jzname="";
		  	 	        			}
		  	 	        			zdcode=bean1.getId();
		  	 	        			dbname=bean1.getDbname();
		  	 	        			if(null==dbname||"null".equals(dbname)){
		  	 	        			 dbname="";
		  	 	        			}
		  	 	        			dbid1=bean1.getDbid();
		  	 	        			if(null==dbid1||"null".equals(dbid1)){
		  	 	        			  dbid1="";
		  	 	        			}
		  	 	        			sccbsj=bean1.getSccbsj();
		  	 	        			if(null==sccbsj||"".equals(sccbsj)){
		  	 	        			 sccbsj="";
		  	 	        			}
		  	 	        			sccb=bean1.getSccb();
		  	 	        			if(null==sccb||"".equals(sccb)){
		  	 	        			 sccb="0.0";
		  	 	        			}
		  	 	        			sccb=ww.format(Double.parseDouble(sccb));
		  	 	        					  	 	        			
		  	 	        			bccbsj=bean1.getBccbsj();
		  	 	        			if(null==bccbsj||"".equals(bccbsj)){
		  	 	        			 bccbsj="";
		  	 	        			}
		  	 	        			
		  	 	        			bccb=bean1.getBccb();
		  	 	        			if(null==bccb||"".equals(bccb)){
		  	 	        			 bccb="0.0";
		  	 	        			}		  	 	        			
		  	 	        			bccb=ww.format(Double.parseDouble(bccb));
		  	 	        			
		  	 	        			zq=bean1.getJs();
		  	 	        			if(null==zq||"".equals(zq)){
		  	 	        			 zq="";
		  	 	        			}	
		  	 	        					  	 	        			
		  	 	        			blhdl=bean1.getBlhdl();
		  	 	        			if(null==blhdl||"".equals(blhdl)){
		  	 	        			 blhdl="0.0";
		  	 	        			}
		  	 	        			blhdl=ww.format(Double.parseDouble(blhdl));
		  	 	        			
			  	 	        		rjdl=bean1.getRjdl();
			  	 	        		if(null==rjdl||"null".equals(rjdl)){
			  	 	        		  rjdl="0.0";
			  	 	        		}
			  	 	        		rjdl=ww.format(Double.parseDouble(rjdl));
			  	 	        		
			  	 	        		startmonth=bean1.getStartmonth();
		  	 	        			if(null==startmonth||"".equals(startmonth)){
		  	 	        			 	startmonth="";
		  	 	        			}	
		  	 	        			
		  	 	        			endmonth=bean1.getEndmonth();
		  	 	        			if(null==endmonth||"".equals(endmonth)){
		  	 	        			 	endmonth="";
		  	 	        			}	
			  	 	        		
		  	 	        			if (intnum % 2 == 0) {
		  	 	        				color = "#FFFFFF";

		  	 	        			} else {
		  	 	        				color = "#DDDDDD";
		  	 	        			}
		  	 	        			  intnum++;
		  	 	%>  		
    <tr bgcolor="<%=color%>">  
	   	<td class="form_label2"><div align="left" ><%=jzname%></div></td>
	   	<td class="form_label2"><div align="center" ><%=zdcode%></div></td>
	   	<td class="form_label2"><div align="left" ><%=dbname%></div></td>
	   	<td class="form_label2"><div align="center" ><%=dbid1%></div></td>
	   	<td class="form_label2"><div align="center" ><%=sccbsj%></div></td>
	   	<td class="form_label2"><div align="right" ><%=sccb%></div></td>
   	    <td class="form_label2"><div align="center" ><%=bccbsj%></div></td>
   	    <td class="form_label2"><div align="right" ><%=bccb%></div></td>
       	<td class="form_label2"><div align="right" ><%=zq%></div></td>
   	    <td class="form_label2"><div align="right" ><%=blhdl%></div></td>
   	    <td class="form_label2"><div align="right" ><%=rjdl%></div></td>
   	    <td class="form_label2"><div align="center" ><%=startmonth%></div></td>
   	    <td class="form_label2"><div align="center" ><%=endmonth%></div></td>
   	</tr>
  
   	 <%}}%>
         
  </table>
  </body>
</html>