<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account"%>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean"%>
<%@ page import="com.noki.electricfees.javabean.*"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	Account account = (Account) session.getAttribute("account");
	String loginId = account.getAccountId();
	String loginName = account.getAccountName();
	String shi1 = request.getParameter("shi") != null ? request.getParameter("shi"): "";	
	Date date=new Date();
	SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
	String dytime=time.format(date);
	String dypeople=account.getName();
	
	String chooseIdStr2 = request.getParameter("chooseIdStr2") != null ? request
			.getParameter("chooseIdStr2")
			: "";
	String liuch = request.getParameter("lch") != null ? request
			.getParameter("lch")
			: "0";		
			String dy=request.getParameter("dayin") != null ? request.getParameter("dayin"): "";	
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
    /*	int lch=0;
    	if(liuch==null||liuch.equals("")||liuch.equals(" "))liuch="0";
    	System.out.println("liuch"+liuch);
			if(liuch=="0"){
				String id=bean.getMaxlch();
				String id1=bean.getMaxlchyz();
				int a = Integer.parseInt(id)-Integer.parseInt(id1);
				if(a>0){
					lch=Integer.parseInt(id)+1;
				}else{
					lch=Integer.parseInt(id1)+1;
				}
			}else{
				lch=Integer.parseInt(liuch);
			}
			*/	
		int lch=0;
    	if(liuch==null||liuch.equals("")||liuch.equals(" "))liuch="0";
    	System.out.println("liuch"+liuch);
			if(liuch=="0"){
		System.out.println(shi1+"11111111111111");
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
					   <td colspan="4" align="center" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;"><b>预付费</b> </td>
					   <td colspan="1" align="right" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;">流程号: </td><td align="left" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;"><%=lch %> </td>
					   <td colspan="1" align="right" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;">提交人 :</td><td colspan="1" align="left" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;"><%=dypeople %> </td>
					   <td colspan="1" align="right" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;">提交财务时间:</td><td colspan="2" align="left" style="border-left:#FF0000 solid 1px;border-right:#FFFFFF solid 1px;"><%=dytime %> </td>
					</tr>
					<tr class="relativeTag" >
				        <td align="center">所在地区</td>
                        <td align="center">站点名称</td>
                        <td align="center">站点类型</td>
                        <td align="center">本次预付费金额</td>
                        <td align="center">报账月份</td>
                        <td align="center">电费支付类型</td>
                        <td align="center">票据类型</td>
                        <td align="center">票据金额</td>
                        <td align="center">预付费余额</td>
                        <td align="center">备注</td>
                        
					</tr>
					</thead>
 <%
       String whereStr="";
       if(chooseIdStr2==""){
       	  whereStr="";
       }else{
       	whereStr=chooseIdStr2;
       }
       
       	List<List<yufufeidayinBean>> list = bean.getPageDataYfdy(whereStr);
       	
		String actualpay = "",accountmonth="",jzname = "",szdq="",jztype="",fffs="";
		String yehj="0",dfhj="0",zjye1="",zjdf1="",memo="",pjlx="",pjje="",scye="",pjjehj="",zjpjje1="";
		Integer num=0;
		Double zjye=0.0,zjdf=0.0,zjpjje=0.0;
		DecimalFormat mat =new DecimalFormat("0.00");
        DecimalFormat mat1 =new DecimalFormat("0.0");
		for(List<yufufeidayinBean> dfdlist: list){
			Double dfheji=0.0,yeheji=0.0,pjjeheji=0.0;
			for(yufufeidayinBean bean2 : dfdlist){
				jzname = bean2.getJzname();
			    jzname = jzname != null ? jzname : "";
			    	
			    szdq = bean2.getSzdq();
			    szdq = szdq != null ? szdq : "";
			    			    
				jztype = bean2.getJztype();
				jztype = jztype != null ? jztype : "";
							
				accountmonth = bean2.getAccountmonth();
				accountmonth = accountmonth != null ? accountmonth : "";
			    
			    actualpay = bean2.getMoney();
			    actualpay = actualpay != null ? actualpay : "0";
			    fffs=bean2.getDfzflx();
			    fffs = fffs != null ? fffs : "";
			    
				memo = bean2.getMemo();
				memo = memo != null ? memo : "";
				
				scye=bean2.getYe();
				scye = scye != null ? scye : "0";
				
				pjlx = bean2.getPjlx();
				pjlx = pjlx != null ? pjlx : "";
				
				pjje = bean2.getPjje();
				pjje = pjje != null ? pjje : "0";
				
				if(pjje==null||pjje.equals("")||pjje.equals(" ")||pjje.equals("null"))pjje="0";
				if(actualpay==null||actualpay.equals("")||actualpay.equals(" ")||actualpay.equals("null"))actualpay="0";
				if(scye==null||scye.equals("")||scye.equals(" ")||scye.equals("null"))scye="0";
				 
			  
	            dfheji+=Double.parseDouble(actualpay);
			    yeheji+=Double.parseDouble(scye);
			    pjjeheji+=Double.parseDouble(pjje);
	          
	            actualpay=mat.format(Double.parseDouble(actualpay));
				
				dfhj=mat.format(dfheji);
				yehj=mat.format(yeheji);
				pjjehj=mat.format(pjjeheji);
				pjje=mat.format(Double.parseDouble(pjje));
				scye=mat.format(Double.parseDouble(scye));
                
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
       			<div align="center" ><%=scye%></div>
       		</td>
       		 <td>
       			<div align="center" ><%=memo%></div>
       		</td>
         
       </tr>
               <% 
			}
			zjpjje+=Double.parseDouble(pjjehj);
		    zjye+=Double.parseDouble(yehj);
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
       			<div align="center" ><%=pjjehj%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=yehj%></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       	
       </tr>
           <% 
           
           
		}
		zjpjje1=mat.format(zjpjje);
		zjye1=mat.format(zjye);
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
       			<div align="center" ><%=zjpjje1%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=zjye1 %></div>
       		</td>
       		<td>
       			<div align="center" ></div>
       		</td>
       		
          </tr>

	</table>
		<%	if("1".equals(dy)){
			isload = true;
			isload=bean.insertlch("",chooseIdStr2,lch,loginName,shi1);
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
	window.location.href = path + "/web/electricfees/yufufeiquery.jsp";
	
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
