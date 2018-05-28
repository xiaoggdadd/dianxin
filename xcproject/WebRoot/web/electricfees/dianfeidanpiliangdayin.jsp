<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean"%>
<%@ page import="com.noki.electricfees.javabean.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*,java.lang.Math"%>

<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
	Date date=new Date();
	SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
	String dytime=time.format(date);
	String dypeople=account.getName();
	String chooseIdStr1 = request.getParameter("chooseIdStr1") != null ? request.getParameter("chooseIdStr1"): "";
	String chooseIdStr2 = request.getParameter("chooseIdStr2") != null ? request.getParameter("chooseIdStr2"): "";
	String liuch = request.getParameter("lch") != null ? request.getParameter("lch"): "0";		
	String shi1 = request.getParameter("shi") != null ? request.getParameter("shi"): "";	
	String dy=request.getParameter("dayin") != null ? request.getParameter("dayin"): "";
	String dystr = request.getParameter("dystr")!= null ? request.getParameter("dystr"): "";
	boolean isload = false;
%>
<html>
	<head>
	<style>
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
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
 .memo {border: 1px #C8E1FB solid}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}

.bttcn{color:BLACK;font-weight:bold;}

.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.form_la{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
}
</style>
		<script src="<%=path%>/javascript/tx.js">
</script>
		<script src="<%=path%>/javascript/jquery-1.4.2.js">
</script>
		<jsp:include page="/web/prePrint.jsp"></jsp:include>


	</head>
	<body>
	<%
	   ElectricFeesBean bean = new ElectricFeesBean();
    DianfeidandayinBean dfdbean=new DianfeidandayinBean();
    	int lch=0;
    	if(null==liuch||liuch.equals("")||liuch.equals(" "))liuch="0";
    	//System.out.println("liuch"+liuch);
			if("0".equals(liuch)||liuch=="0"){
		//System.out.println(shi1+"11111111111111");
				lch=bean.getMaxlch(shi1);

			}else{
				lch=Integer.parseInt(liuch);
			}
	%>
		<div id="hiddenDiv" style="visibility: hidden" >
			<form id="form1">
				<table id="dayindan" border='1' width='100%' cellspacing='1' cellpadding='1' class="form_label">
					<thead>
					<tr height="30">
					   <td colspan="5" align="center" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;"><b>电费单</b> </td>
					   <td colspan="2" align="right" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;">流程号: </td>
					   <td 			   align="left" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;"><%=lch %> </td>
					   <td colspan="2" align="right" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;">提交人 :</td>
					   <td colspan="2" align="left" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;"><%=dypeople %> </td>
					   <td colspan="7" align="right" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;">提交财务时间:</td>
					   <td colspan="4" align="left" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;"><%=dytime %> </td>
					</tr>
					<tr class="relativeTag" >
				       <td>所在地区</td>
                       <td>站点名称</td>
                       <td>站点类型</td>
            		    <td>上次抄表时间</td>
                        <td>本次抄表时间</td>
                        <td>上次读数</td>
                        <td>本次读数</td>

                        <td>倍率</td>
                        <td>电量调整</td>
                        <td>耗电量</td>
                        <td>电费单价</td>
                        <td>电费调整</td>
                        <td>实付费用</td>
                        <td>报账月份</td>
                        <td>支付类型</td>
                        <td>票据类型</td>
                        <td>票据金额</td>
                        
                        <td>上期电费</td>
                        <td>上期电量</td>
                        <td>上期单价</td>
                        <td>供电方式</td>
                        <td>备注</td>
                        
					</tr>
					</thead>
 <%
       String whereStr="";
 
       if(chooseIdStr1==""){
       	whereStr=chooseIdStr2;
       }else if(chooseIdStr2==""){
       	whereStr=chooseIdStr1;
       }else if(chooseIdStr1==""&&chooseIdStr2==""){
       	whereStr="";
       }else{
       	whereStr = chooseIdStr1+","+chooseIdStr2;
       }
       		List<List<DianfeidandayinBean>> list = bean.getPageDataCaiwu(whereStr,dystr);
       	
		String electricfeeId = "",unitprice = "",floatpay = "", actualpay = "";
		String jzname = "",df="",szdq="",jztype="",inputdatetime="",inputoperator="",lastdatetime="",thisdatetime="",
		accountmonth="",lastdegree="",thisdegree="",actualdegree="",floatdegree="",fffs="",beilv="";
		String dlhj="0",dfhj="0",zjdl1="",zjdf1="",memo="",pjlx="",pjje="";
		//2014-4-15新增--上期电费-上期电量-上期单价---增值税站点
		String lastelecfees = "", lastelecdegree = "", lastunitprice = "",zzszd="",zdf="";
		Integer num=0;
		Double zjdl=0.0,zjdf=0.0,hetong=0.0,zzs=0.0,jshj=0.0;//增值税,价税合计
		DecimalFormat mat =new DecimalFormat("0.00");
        DecimalFormat mat1 =new DecimalFormat("0.0");
		for(List<DianfeidandayinBean> dfdlist: list){
			Double dianfei=0.0,dlheji=0.0,dfheji=0.0;
			for(DianfeidandayinBean bean2 : dfdlist){
				jzname = bean2.getJzname();
			    jzname = jzname != null ? jzname : "";
			    	
			    szdq = bean2.getSzdq();
			    szdq = szdq != null ? szdq : "";
			     
			    electricfeeId =bean2.getElectricfee_id();
			    electricfeeId = electricfeeId != null ? electricfeeId : "";
			    			    
				jztype = bean2.getJztype();
				jztype = jztype != null ? jztype : "";
				
			    
			    lastdatetime = bean2.getLastdatetime();
			    lastdatetime = lastdatetime != null ? lastdatetime : "";
			    
				thisdatetime =bean2.getThisdatetime();
				thisdatetime = thisdatetime != null ? thisdatetime : "";
							
				accountmonth = bean2.getAccountmonth();
				accountmonth = accountmonth != null ? accountmonth : "";
				
			    lastdegree = bean2.getLastdegree();
			    lastdegree = lastdegree != null ? lastdegree : "0";
			    
			    thisdegree = bean2.getThisdegree();
			    thisdegree = thisdegree != null ? thisdegree : "0";
			    
			    actualdegree = bean2.getActualdegree();
			    actualdegree = actualdegree != null ? actualdegree : "0";
			    
			    floatdegree = bean2.getFloatdegree();
			    floatdegree = floatdegree != null ? floatdegree : "0";
			    
			    unitprice = bean2.getUnitprice();
			    unitprice = unitprice != null ? unitprice : "0";
			    
			    floatpay = bean2.getFloatpay();
			    floatpay = floatpay != null ? floatpay : "0";
			    
			    actualpay = bean2.getActualpay();
			    actualpay = actualpay != null ? actualpay : "0";
			    
			    
			    fffs = bean2.getFffs();
			    fffs = fffs != null ? fffs : "";
			    
			    beilv = bean2.getBeilv();
				beilv = beilv != null ? beilv : "";
				
				memo = bean2.getMemo();
				memo = memo != null ? memo : "";
				
				 
			    lastelecfees = bean2.getLastelecfees();
			    lastelecfees = lastelecfees != null ? lastelecfees : "";
			    
			    lastelecdegree = bean2.getLastelecdegree();
			    lastelecdegree = lastelecdegree != null ? lastelecdegree : "";
			    
			    lastunitprice = bean2.getLastunitprice();
			    lastunitprice = lastunitprice != null ? lastunitprice : "";

			    
				pjlx = bean2.getNotetypeid();
				pjlx = pjlx != null ? pjlx : "";
				
				pjje = bean2.getPjje();
				pjje = pjje != null ? pjje : "0";
				    if(pjje==null||pjje.equals("")||pjje.equals(" ")||pjje.equals("null"))pjje="0";
				    if(actualpay==null||actualpay.equals("")||actualpay.equals(" ")||actualpay.equals("null"))actualpay="0";
				    if(actualdegree==null||actualdegree.equals("")||actualdegree.equals(" ")||actualdegree.equals("null"))actualdegree="0";
				    if(lastdegree==null||lastdegree.equals("")||lastdegree.equals(" ")||lastdegree.equals("null"))lastdegree="0";
				    if(thisdegree==null||thisdegree.equals("")||thisdegree.equals(" ")||thisdegree.equals("null"))thisdegree="0";
				    if(unitprice==null||unitprice.equals("")||unitprice.equals(" ")||unitprice.equals("null"))unitprice="0";
				    if(null==floatdegree||floatdegree.equals("")||floatdegree.equals(" ")||floatdegree.equals("null"))floatdegree="0";
				    if(null==floatpay||floatpay.equals("")||floatpay.equals(" ")||floatpay.equals("null"))floatpay="0";
				   // System.out.println("--"+lastdegree);
			    dlheji+=Double.parseDouble(actualdegree);
	            dfheji+=Double.parseDouble(actualpay);
				String color=null;
	            
	            dianfei=Double.parseDouble(actualpay);
	            floatdegree=mat1.format(Double.parseDouble(floatdegree));
	            floatpay=mat.format(Double.parseDouble(floatpay));
	            actualdegree=mat1.format(Double.parseDouble(actualdegree));
	            lastdegree=mat1.format(Double.parseDouble(lastdegree));
	            thisdegree=mat1.format(Double.parseDouble(thisdegree));
	            actualpay=mat.format(dianfei);
				dlhj=mat1.format(dlheji);
				dfhj=mat.format(dfheji);
				pjje=mat.format(Double.parseDouble(pjje));
                
				//if(fffs.equals("合同")){  //合同类预付待摊合计金额 去掉
					
					//hetong+=dianfei;
				//}
				//System.out.println("--"+hetong);
				if(jztype.equals("增值税站点")){//如果是增值税站点   计算 增值税站点的总电费
					zzs+=dianfei;
				}
				%>
				<tr class="relativeTag">
            <td>
       			<div align="center" ><%=szdq%></div>
       		</td>
       		<td>
       			<div align="center" ><%=jzname%></div>
       		</td>   
       		<td>
       			<div align="center" ><%=jztype%></div>
       		</td>          
            <td>
       			<div align="center" ><%=lastdatetime%></div>
       		</td>
            <td>
       			<div align="center" ><%=thisdatetime%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=lastdegree%></div>
       		</td>
       		<td>
       			<div align="center" ><%=thisdegree%></div>
       		</td>
       		<td>
       			<div align="center" ><%=beilv%></div>
       		</td>
       		<td>
       			<div align="center" ><%=floatdegree%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=actualdegree%></div>
       		</td>
       		<td>
       			<div align="center" ><%=unitprice%></div>
       		</td>
       		  <td>
       			<div align="center" ><%=floatpay%></div>
       		</td>
       		  <td>
       			<div align="center" ><%=actualpay%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=accountmonth%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=fffs%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=pjlx%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=pjje%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=lastelecfees%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=lastelecdegree%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=lastunitprice%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=bean2.getGdfs()%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=memo%></div>
       		</td>
         
       </tr>
               <% 
			}
			
			zjdl+=Double.parseDouble(dlhj);
			zjdf+=Double.parseDouble(dfhj);
			num+=dfdlist.size();
			%>
	 <tr class="relativeTag">
         <td>
       		  <div align="center" >合计</div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" >合计条数:<%=dfdlist.size()%></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>   
       		<td>
       			<div align="center" ></div>
       		</td>          
            <td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		 <td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ><%=dlhj%></div>
       		</td>
       		 <td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ><%=dfhj%></div>
       		</td>
       		  <td>
       			<div align="center" ></div>
       		</td>
       		 <td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       </tr>
           <% 
           
           
		}
		
		zjdl1=mat1.format(zjdl);
		zjdf1=mat.format(zjdf);
		
       %>
        <tr class="relativeTag">
         <td>
       		  <div align="center" >总计</div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" >总计条数:<%=num%></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>   
       		<td>
       			<div align="center" ></div>
       		</td>          
            <td>
       			<div align="center" ></div>
       		</td>
            <td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ><%=zjdl1%></div>
       		</td>
       		 <td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ><%=zjdf1%></div>
       		</td>
       		  <td>
       			<div align="center" ></div>
       		</td>
       		 <td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       </tr>
       
       <tr class="relativeTag">
        <td colspan="5" align="center" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;"><b>电费分摊信息</b> </td>
       </tr>
        <tr class="relativeTag">
         <td>
       		  <div align="center" >成本中心</div>
       		</td>
       		<td>
       			<div align="center" >会计科目</div>    
       		</td>
       		<td>
       			<div align="center" >专业明细</div>
       		</td>
       		<td>
       			<div align="center" >全成本</div>
       		</td>   
       		<td>
       			<div align="center" >分摊费用</div>
       		</td>          
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
        List<DianfeidandayinBean> listt=bean.getFentan(whereStr);
        for(DianfeidandayinBean ftbean:listt){
        	i++;
        	shi=ftbean.getShi();
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
           // System.out.println("i222i--"+i+"---list.size--"+listt.size());
            if(i==listt.size()){
            	//System.out.println("ii--"+i+"---list.size--"+listt.size());
            	ftfy=mat.format(zjdf-hetong-ft);//最后一条是总和减去前面条数的和   解决多一分的问题  :总电费 -合同总电费-除了最后一条的分摊总电费
            }else{
            	 ftfy=mat.format(Double.parseDouble(ftfy));
            }
           
            ft+=Double.parseDouble(ftfy);
        %>
        <tr class="relativeTag">
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
       // hj=hj+hetong;//合同类预付待摊合计金额 （去掉）
        heji=mat.format(hj);
       // htje=mat.format(hetong);//合同类预付待摊合计金额（ 去掉）
       %>
       <%--
        <tr class="relativeTag">
         <td colspan="2">
       		  <div align="center" >合同类预付待摊合计金额</div>
       		</td>
       		
       		<td colspan="3">
       			<div align="center" ><%=htje%></div>
       		</td>          
            
       </tr>
       --%>
       <% 
       
      double bj= Double.parseDouble(zjdf1)-Double.parseDouble(heji);//解决多一分还是少一分的问题
       if(Math.abs(bj)<=0.02){
         heji=zjdf1;
      }
        %>
       <tr class="relativeTag">
         <td colspan="2">
       		  <div align="center" >总金额（分摊费用总和）</div>
       		</td>
       		
       		<td colspan="3">
       			<div align="center" ><%=heji%></div>
       		</td>          
            
       </tr>
       <%
       zzszd = mat.format(Math.abs(zzs));//增值税税额：增值税站点总电费 取绝对值 显示正值 
       jshj = Double.parseDouble(heji)-(zzs);//价税合计：总电费-增值税站点总电费
       zdf=mat.format(jshj);//格式化
       
       %>
       <tr class="relativeTag">
         <td colspan="2">
       		  <div align="center" >增值税税额</div>
       		</td>
       		
       		<td colspan="3">
       			<div align="center" ><%=zzszd%></div>
       		</td>          
            
       </tr>
        <tr class="relativeTag">
         <td colspan="2">
       		  <div align="center" >价税合计</div>
       		</td>
       		
       		<td colspan="3">
       			<div align="center" ><%=zdf%></div>
       		</td>          
            
       </tr>
       
				</table>
				<%
			if("1".equals(dy)){
					isload = true;
					isload=bean.insertlch(chooseIdStr1,chooseIdStr2,lch,loginName,shi1);
			 }
				
				%>
		
			
	
	</form>
	</div>
	</body>
	
</html>
<script language="javascript">
var path = '<%=path%>';
var isload = '<%=isload%>';

var tablehtml = "<body>" + document.getElementById("form1").innerHTML
		+ "</body>";

if (isload) {
	var column = "all";//打印所有列
	//var column = [1,2,3,4,5,6,7,8,9,10];//定义表格要打印的列，下标从0开始
	var style = "<style>table {border-collapse:collapse;border-style:double;border-width:3px;border-color:black;font-size:12}</style>";//定义表格样式

	create("电费单      流程号:"+'<%=lch%>' , style + tablehtml);//创建打印样式
	//LODOP.PRINT_DESIGN();
	
	LODOP.PREVIEW();
	window.location.href = path + "/web/electricfees/dianfeidanquery.jsp";
	
	}
	
function create(title, postSetColumn) {
    LODOP.NEWPAGEA();
	LODOP.PRINT_INIT(title);
    
	LODOP.SET_PRINT_STYLE("FontSize",12);
	LODOP.SET_PRINT_PAGESIZE(2,0,0,0);
	//LODOP.ADD_PRINT_TEXT(50, 250, 200, 200, title);
	//LODOP.ADD_PRINT_HTM(80, 20,1050,600, postSetColumn);
	LODOP.ADD_PRINT_TABLE(80,20,"90%",600,postSetColumn);
};

</script>
