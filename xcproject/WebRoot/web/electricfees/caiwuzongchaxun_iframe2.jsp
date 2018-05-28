<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@page import="com.noki.electricfees.javabean.*"%>
<%@ page import="java.text.*"%>
<%
	String sheng = (String)session.getAttribute("accountSheng");
	String roleId = (String)session.getAttribute("accountRole");
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
    String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
	String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";
	String jzproperty1 = request.getParameter("zdsx")!=null?request.getParameter("zdsx"):"0";
	String sitetype = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
	String bzyf = request.getParameter("bzyf")!=null?request.getParameter("bzyf"):"";//报账月份
	String kjkm = request.getParameter("kjkmcode")!=null?request.getParameter("kjkmcode"):"";//会计科目
	String qcb = request.getParameter("qcbcode")!=null?request.getParameter("qcbcode"):"";//全成本
	String zymx = request.getParameter("zymxcode")!=null?request.getParameter("zymxcode"):"";//专业明细
	String sszy = request.getParameter("sszy")!=null?request.getParameter("sszy"):"";//所属专业
	String shzt = request.getParameter("shzt")!=null?request.getParameter("shzt"):"";//
	String bz = request.getParameter("bz")!=null?request.getParameter("bz"):"";//标志位
	     
	String whereStr="";
    String path = request.getContextPath();
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
       
        
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
    curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
          
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<style>
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:20px

		}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
};
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

.bttcn{color:black;font-weight:bold;}

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
  <head>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script> 
<script>
var path='<%=path%>';
  function information(dbid,zdid,bzyf,dfzflx){  	
    	window.open(path+"/web/electricfees/caiwufentanxiangxi_iframe3.jsp?dbid="+dbid+"&dfzflx="+dfzflx+"&bzyf="+bzyf+"&zdid="+zdid,'','width=1100,height=400,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')
  }

</script>  
  </head>

  <body>
    <% if(bz != null && !bz.equals("") && bz.equals("1")){
    %>
    <table width="100%" border="0" cellspacing="1" cellpadding="1" >
           <tr class="relativeTag" >
             <td width="2%" bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">ID</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点名称</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电表ID</div></td>
             <td  bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电费支付类型</div></td>
             <td  bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电表总金额</div></td>
             <td  bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">详细分摊金额</div></td>
             <td  bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">分摊占比</div></td>
         </tr>
       <%
            double jis=0;
            String js="0";
       		String str="";
         	if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and z.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and z.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" and z.xiaoqu='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("")){
				whereStr=whereStr+" and z.jzname like '%"+zdname+"%'";
			}
			if (!jzproperty1.equals("0")) {
			whereStr=whereStr+" and z.property='" + jzproperty1 + "'";
			
			}
			if(sitetype != null && !sitetype.equals("")&&!sitetype.equals("0")){
				whereStr=whereStr+" and z.STATIONTYPE='"+sitetype+"'";
				
			}
			if(bzyf != null && !bzyf.equals("")){
				whereStr=whereStr+" and to_char(e.ACCOUNTMONTH,'yyyy-mm')='"+bzyf+"'";//报账月份
			}
			if(kjkm == null || kjkm.equals("")){
			 	whereStr=whereStr+" and s.KJKMCODE is null";//会计科目
			}else{
				whereStr=whereStr+" and s.KJKMCODE='"+kjkm+"'";//会计科目
			}
			
		
			
			if(qcb == null||qcb.equals("")){
				whereStr=whereStr+" and s.QCBCODE is null";//全成本
			
			}else{
				whereStr=whereStr+" and s.QCBCODE='"+qcb+"'";//全成本
			}
			if(zymx == null || zymx.equals("")){
			whereStr=whereStr+" and s.ZYMXCODE is null";//专业明细
			}else{
				whereStr=whereStr+" and s.ZYMXCODE='"+zymx+"'";//专业明细
			}
			if(sszy == null || sszy.equals("")){
			whereStr=whereStr+" and s.SHUOSHUZHUANYE is null";//所属专业
			}else{
			whereStr=whereStr+" and s.SHUOSHUZHUANYE='"+sszy+"'";//所属专业
			}
			if(shzt != null && !shzt.equals("") && !shzt.equals("3")){
		      	if("1".equals(shzt)){
			     whereStr=whereStr+" and e.MANUALAUDITSTATUS>='"+shzt+"'";
			      //str=str+" and ef.MANUALAUDITSTATUS>='"+manauditstatus+"'";
				}
				if("2".equals(shzt)){
			        whereStr=whereStr+" and e.MANUALAUDITSTATUS='"+shzt+"'";
			      //str=str+" and ef.MANUALAUDITSTATUS='"+shzt+"'";
			      }
			      if("0".equals(shzt)){
			       whereStr=whereStr+" and e.MANUALAUDITSTATUS='"+shzt+"'";
			      //str=str+" and ef.MANUALAUDITSTATUS='"+manauditstatus+"'";
			      }
			      if("-1".equals(shzt)){
			       whereStr=whereStr+" and e.MANUALAUDITSTATUS<'2' ";
			     // str=str+" and ef.MANUALAUDITSTATUS<'2' ";
			      }
			}	
		 CaiwuzongchaxunBean bean=new CaiwuzongchaxunBean();  
       	ArrayList fylist = bean.getCaixiaji(whereStr,loginId);
       	 allpage=bean.getAllPage();
       
		String jzname = "",dianbiaoid = "",bzyf1="",dfft="0",ftzb="",zongje="",zdid="",dfzflx="";
		String zdsx01="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		
			jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
			dianbiaoid = (String)fylist.get(k+fylist.indexOf("AMMETERID_FK"));
			//bzyf1 = (String)fylist.get(k+fylist.indexOf("ACCOUNTMONTH"));//报账月份
			ftzb = (String)fylist.get(k+fylist.indexOf("XJBILI"));//分摊比例
			zongje = (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));//实际金额 a.ammeterdegreeid
			zdid = (String)fylist.get(k+fylist.indexOf("ID"));//站点id
			dfzflx = (String)fylist.get(k+fylist.indexOf("DFZFLX"));//电费支付类型
			
		    if("zylx01".equals(sszy)){
		    	dfft = (String)fylist.get(k+fylist.indexOf("NETWORKDF"));//生产
		    }else if("zylx04".equals(sszy)){
		    	dfft = (String)fylist.get(k+fylist.indexOf("INFORMATIZATIONDF"));//信息化支撑
		    }else if("zylx03".equals(sszy)){
		    	dfft = (String)fylist.get(k+fylist.indexOf("ADMINISTRATIVEDF"));//办公
		    }else if("zylx02".equals(sszy)){
		    	dfft = (String)fylist.get(k+fylist.indexOf("MARKETDF"));//营业
		    }else if("zylx05".equals(sszy)){
		    	dfft = (String)fylist.get(k+fylist.indexOf("BUILDDF"));//建设投资
		    }else if("zylx06".equals(sszy)){
		    	dfft = (String)fylist.get(k+fylist.indexOf("DDDF"));//建设投资
		    }else{
		      String  sc = (String)fylist.get(k+fylist.indexOf("NETWORKDF"));
		      String  yy = (String)fylist.get(k+fylist.indexOf("INFORMATIZATIONDF"));
		      String  bg = (String)fylist.get(k+fylist.indexOf("ADMINISTRATIVEDF"));
		      String  xxhzc = (String)fylist.get(k+fylist.indexOf("MARKETDF"));
		      String  jstz = (String)fylist.get(k+fylist.indexOf("BUILDDF"));
		      String  dddf = (String)fylist.get(k+fylist.indexOf("DDDF"));
		      double df=Double.parseDouble(sc)+Double.parseDouble(yy)+Double.parseDouble(bg)+Double.parseDouble(xxhzc)+Double.parseDouble(jstz)+Double.parseDouble(dddf);
		      dfft=df+"";
		   
		    }
			if(dfft==null||"".equals(dfft)||"null".equals(dfft))dfft="0";
			DecimalFormat pay=new DecimalFormat("0.00");
			dfft = pay.format(Double.parseDouble(dfft));//格式化
			zongje = pay.format(Double.parseDouble(zongje));//格式化
			
			jis=jis+Double.parseDouble(dfft);
			js = pay.format(jis);//格式化
			
			if("".equals(ftzb)||ftzb==null){
				ftzb="0";
			}
			String color=null;
            str="dianBiaoId="+dianbiaoid;
            	if(intnum%2==0){
			    color="#DDDDDD";

			 }else{
			    color="#FFFFFF" ;
			}

       %>
       <tr bgcolor="<%=color%>" class="form_label">
       		<td>
       			<%=intnum++%>
       		</td>
       		<td>
       			<%=jzname%>
       		</td>
       		<td>
       			<div align="left" title="详细分摊信息"><a href="#" onclick="information('<%=dianbiaoid%>','<%=zdid%>','<%=bzyf%>','<%=dfzflx%>')"><%=dianbiaoid%></a></div>
       		</td>
       		<td>
       			<%=dfzflx%>
       		</td>
       		<td>
       			<%=zongje%>
       		</td>
       		<td>
       			<%=dfft%>
       		</td>
       			<td>
       			<%=ftzb%>%
       		</td>
   </tr>
       <%} }%>
     
  <!--<tr bgcolor="#cbd5de" class="form_label" ><td colspan="5" align="center" style="color:black;font-weight:bold;font-size: 12px">所属专业分摊金额合计</td> <td><%=js%></td><td></td></tr>
--> </table>
  	 <%} %>
  </body>
</html>
