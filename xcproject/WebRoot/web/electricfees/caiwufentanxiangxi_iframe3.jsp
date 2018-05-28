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
    String dbid = request.getParameter("dbid")!=null?request.getParameter("dbid"):"";
    String zdid = request.getParameter("zdid")!=null?request.getParameter("zdid"):"";
	String bzyf1 = request.getParameter("bzyf")!=null?request.getParameter("bzyf"):"";
	String dfzflx = request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"";
	System.out.println("电表id "+dbid+"站点id"+zdid+"电费支付类型"+dfzflx);  
	
     String dfzf="";
     if("月结".equals(dfzflx)){
     dfzf="dfzflx01";
     }else if("预支".equals(dfzflx)){
     dfzf="dfzflx03";
     }else if("合同".equals(dfzflx)){
     dfzf="dfzflx02";
     }else if("插卡".equals(dfzflx)){
     dfzf="dfzflx04";
     }
	     
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
</head>

  <body>
   
    <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de">
   
           <tr class="relativeTag">
             <td width="2%" bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">ID</div></td>
             <td bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">站点名称</div></td>
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电表ID</div></td>
               <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">电费支付类型</div></td>
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">报账月份</div></td>
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">所属专业</div></td>
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">全成本</div></td>
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">会计科目</div></td>
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">专业明细</div></td>
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">总金额</div></td>
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">所属专业总金额</div></td>
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">所属专业占比</div></td>
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">分摊金额</div></td>
             <td bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">分摊占比</div></td>
             
         </tr>
       <%
       		
    
			if(bzyf1 != null && !bzyf1.equals("")){
				whereStr=whereStr+" and to_char(e.ACCOUNTMONTH,'yyyy-mm')='"+bzyf1+"'";//报账月份
			}
			if(dfzflx != null && !dfzflx.equals("")){
				whereStr=whereStr+" and d.dfzflx='"+dfzf+"'";//电费支付类型
			}
			
			//if(zymx != null && !zymx.equals("")&& !zymx.equals("0")){}
				whereStr=whereStr+" and d.dbid='"+dbid+"'";//专业明细
			
			//if(sszy != null && !sszy.equals("")&& !sszy.equals("0")){}
				whereStr=whereStr+" and z.id='"+zdid+"'";//所属专业
			
			
			
			
			
			
		 System.out.println("财务总查询条件:"+whereStr);
		 CaiwuzongchaxunBean bean=new CaiwuzongchaxunBean();  
		
		
       	ArrayList fylist = bean.getFentan(whereStr,loginId,dfzflx);
       	 allpage=bean.getAllPage();
       String str="";
		String ftzb="",zongje="",sszy1="",sszy2="",qcbcode2="",kjkmcode2="",zymxcode2="",dfft="",ftje="";
		String jzname="",dbid1="",bzyf="",dbili="",zje="",dfzflx1="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

		     //num为序号，不同页中序号是连续的
		     jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));//
		      dbid1 = (String)fylist.get(k+fylist.indexOf("AMMETERID_FK"));//ammeterid_fk
			ftzb = (String)fylist.get(k+fylist.indexOf("XJBILI"));//分摊比例
			bzyf = (String)fylist.get(k+fylist.indexOf("ACCOUNTMONTH"));//报账月份,e.accountmonth
			dfzflx1 = (String)fylist.get(k+fylist.indexOf("DFZFLX"));//电费支付类型
			sszy1= (String)fylist.get(k+fylist.indexOf("SHUOSHUZHUANYE"));//所属专业
			sszy2= (String)fylist.get(k+fylist.indexOf("SSZY"));//所属专业编译后显示文字
			qcbcode2= (String)fylist.get(k+fylist.indexOf("QCB"));//全成本编译后显示文字
			kjkmcode2= (String)fylist.get(k+fylist.indexOf("KJKM"));//会计科目编译后显示文字
			zymxcode2= (String)fylist.get(k+fylist.indexOf("ZYMX"));//专业明细编译后显示文字  
			dbili= (String)fylist.get(k+fylist.indexOf("DBILI"));//所属专业比例actualpay
			zje= (String)fylist.get(k+fylist.indexOf("ACTUALPAY"));//总金额
			
		  System.out.println("分摊"+sszy1);
		  String ftdf="";
		     if("zylx01".equals(sszy1)){
		    	dfft = (String)fylist.get(k+fylist.indexOf("NETWORKDF"));//生产   所属专业总金额
		    	
		    }else if("zylx04".equals(sszy1)){
		    	dfft = (String)fylist.get(k+fylist.indexOf("INFORMATIZATIONDF"));//信息化支撑分摊总金额
		    	System.out.println("电费分摊"+dfft);
		    }else if("zylx03".equals(sszy1)){
		    	dfft = (String)fylist.get(k+fylist.indexOf("ADMINISTRATIVEDF"));//办公分摊总金额
		    }else if("zylx02".equals(sszy1)){
		    	dfft = (String)fylist.get(k+fylist.indexOf("MARKETDF"));//营业分摊总金额
		    }else if("zylx05".equals(sszy1)){
		    	dfft = (String)fylist.get(k+fylist.indexOf("BUILDDF"));//建设投资分摊总金额
		    }else if("zylx06".equals(sszy1)){
		    	dfft = (String)fylist.get(k+fylist.indexOf("DDDF"));//代垫电费分摊总金额
		    }else{
		      String  sc = (String)fylist.get(k+fylist.indexOf("NETWORKDF"));
		      String  yy = (String)fylist.get(k+fylist.indexOf("INFORMATIZATIONDF"));
		      String  bg = (String)fylist.get(k+fylist.indexOf("ADMINISTRATIVEDF"));
		      String  xxhzc = (String)fylist.get(k+fylist.indexOf("MARKETDF"));
		      String  jstz = (String)fylist.get(k+fylist.indexOf("BUILDDF"));
		      String  dddf = (String)fylist.get(k+fylist.indexOf("DDDF"));
		      //System.out.println("分摊"+sc+yy+bg+xxhzc+jstz);
		      double df=Double.parseDouble(sc)+Double.parseDouble(yy)+Double.parseDouble(bg)+Double.parseDouble(xxhzc)+Double.parseDouble(jstz)+Double.parseDouble(dddf);
		      dfft=df+"";
		   
		    }
		    
		    if("zylx01".equals(sszy1)){
		    	ftje = (String)fylist.get(k+fylist.indexOf("E"));//生产networkdf
		    }else if("zylx02".equals(sszy1)){
		    	ftje = (String)fylist.get(k+fylist.indexOf("C"));//营业marketdf
		    }else if("zylx03".equals(sszy1)){
		    	ftje = (String)fylist.get(k+fylist.indexOf("B"));//办公administrativedf
		    }else if("zylx04".equals(sszy1)){
		    	ftje = (String)fylist.get(k+fylist.indexOf("A"));//信息化支撑informatizationdf
		    }else if("zylx05".equals(sszy1)){
		    	ftje = (String)fylist.get(k+fylist.indexOf("D"));//建设投资builddf
		    }else if("zylx06".equals(sszy1)){
		    	ftje = (String)fylist.get(k+fylist.indexOf("F"));//代垫电费dddf
		    }
		    
			DecimalFormat pay=new DecimalFormat("0.00");
			if(dfft==null||"".equals(dfft))dfft="0";
			dfft = pay.format(Double.parseDouble(dfft));//格式化
			if(ftje==null||"".equals(ftje))ftje="0";
			ftje = pay.format(Double.parseDouble(ftje));//格式化
			if(ftzb==null||"".equals(ftzb))ftzb="0";
			if(zje==null||"".equals(zje))zje="0";
			zje = pay.format(Double.parseDouble(zje));
			
			double df=Double.parseDouble(dfft)*Double.parseDouble(ftzb)/100;
			ftdf=df+"";
            ftdf = pay.format(Double.parseDouble(ftdf));//格式化
            
            
			String color=null;
       
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
       			<%=dbid1%>
       		</td>
       		<td>
       			<%=dfzflx%>
       		</td>
       		<td>
       			<%=bzyf%>
       		</td>
       		<td>
       			<%=sszy2%>
       		</td>
       		<td>
       			<%=qcbcode2%>
       		</td>
       		<!--
       		站点名称   电表ID   报账月份  所属专业    全成本    会计科目   专业明细    总金额   所属专业总金额    所属专业占比    分摊金额   分摊占比
       		-->
       		<td>
       			<%=kjkmcode2%>
       		</td>
       		<td>
       			<%=zymxcode2%>
       		</td>
       		<td>
       			<%=zje%>
       		</td>
       		<td>
       			<%=dfft%>
       		</td>
       			<td>
       			<%=dbili%>%
       		</td>
       		<td>
       			<%=ftje%>
       		</td>
       				<td>
       			<%=ftzb%>%
       		</td>
   </tr>
       <%} }%>
  	 </table> 
	
  </body>
</html>
