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
	String jzproperty1 = request.getParameter("zdsx1")!=null?request.getParameter("zdsx1"):"0";
	 String sitetype = request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"0";
	String bzyf = request.getParameter("bzyf")!=null?request.getParameter("bzyf"):"";//报账月份
	String kyiji = request.getParameter("yiji")!=null?request.getParameter("yiji"):"0";//会计科目一级
	String kerji = request.getParameter("erji")!=null?request.getParameter("erji"):"0";//会计科目二级
	String ksanji = request.getParameter("sanji")!=null?request.getParameter("sanji"):"0";//会计科目三级
	String ksiji = request.getParameter("siji")!=null?request.getParameter("siji"):"0";//会计科目四级
	String qyiji = request.getParameter("qcbyiji")!=null?request.getParameter("qcbyiji"):"0";//全成本一级科目
	String qerji = request.getParameter("qcberji")!=null?request.getParameter("qcberji"):"0";//全成本二级科目
	String qsanji = request.getParameter("qcbsanji")!=null?request.getParameter("qcbsanji"):"0";//全成本三级科目
	String zymx = request.getParameter("zymx")!=null?request.getParameter("zymx"):"0";//专业明细
	String sszy = request.getParameter("sszy")!=null?request.getParameter("sszy"):"0";//所属专业
	String shzt = request.getParameter("shzt")!=null?request.getParameter("shzt"):"3";//所属专业
	String bzw = request.getParameter("bzw")!=null?request.getParameter("bzw"):"";//bzw
	String qcb="0";
	if(ksiji!=null&&!"0".equals(ksiji)){
		qcb=ksiji;
	}
	if(ksanji!=null&&!"0".equals(ksanji)&&"0".equals(ksiji)){
	  qcb=ksanji;
	}
	 if((kerji!=null&&!"0".equals(kerji))&&"0".equals(ksanji)){
	  qcb=kerji;
	}
	 if((kyiji!=null&&!"0".equals(kyiji))&&"0".equals(kerji)){
	  qcb=kyiji;
	}
	String kjkm="0";
	if(qsanji!=null&&!"0".equals(qsanji)){
		kjkm=qsanji;
	}
	if(qerji!=null&&!"0".equals(qerji)&&"0".equals(qsanji)){
	  kjkm=qerji;
	}
	 if(qyiji!=null&&!"0".equals(qyiji)&&"0".equals(qerji)){
	  kjkm=qyiji;
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
    <% if(bzw != null && !bzw.equals("") && !bzw.equals("0")){ %>
    <table width="100%" border="0" cellspacing="1" cellpadding="1">       
           <tr class="relativeTag" >
             <td  width="2%"  bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">ID</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">所属专业</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">全成本</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">会计科目</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">专业明细</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">总金额</div></td>
             <td   bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn" style="color:black;font-weight:bold;font-size: 12px">条数</div></td>
         </tr>
       <%
       		
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
			if(qcb != null && !qcb.equals("")&&!qcb.equals("0")){
				whereStr=whereStr+" and s.KJKMCODE like '"+qcb+"%'";//会计科目
			}
			if(kjkm != null && !kjkm.equals("")&&!kjkm.equals("0")){
				whereStr=whereStr+" and s.QCBCODE like '"+kjkm+"%'";//全成本
			}
			if(zymx != null && !zymx.equals("")&& !zymx.equals("0")){
				whereStr=whereStr+" and s.ZYMXCODE='"+zymx+"'";//专业明细
			}
			if(sszy != null && !sszy.equals("")&& !sszy.equals("0")){
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
		 if(bzw!= null && !bzw.equals("") && !bzw.equals("0")){
       	ArrayList fylist = bean.getCaiwu(whereStr,loginId);
       	 allpage=bean.getAllPage();
       String str="";
		String jzname = "",dianbiaoid = "",jstz="0",sc="0",yy="0",bg="0",xxhzc="0",tiaoshu="",sszy1="",hj="";
		String qcbcode1="",kjkmcode1="",zymxcode1="",qcbcode2="",kjkmcode2="",zymxcode2="",sszy2="",df="";
		String zdsx01="";
		int intnum=xh = pagesize*(curpage-1)+1;
		 if(fylist!=null)
		{
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){

			tiaoshu = (String)fylist.get(k+fylist.indexOf("TIAOSHU"));
			
			qcbcode1= (String)fylist.get(k+fylist.indexOf("QCBCODE"));//全成本
			kjkmcode1= (String)fylist.get(k+fylist.indexOf("KJKMCODE"));//会计科目
			zymxcode1= (String)fylist.get(k+fylist.indexOf("ZYMXCODE"));//专业明细
			sszy1= (String)fylist.get(k+fylist.indexOf("SHUOSHUZHUANYE"));//所属专业
			
			sszy2= (String)fylist.get(k+fylist.indexOf("SSZY"));//所属专业编译后显示文字
			qcbcode2= (String)fylist.get(k+fylist.indexOf("QCB"));//全成本编译后显示文字
			kjkmcode2= (String)fylist.get(k+fylist.indexOf("KJKM"));//会计科目编译后显示文字
			zymxcode2= (String)fylist.get(k+fylist.indexOf("ZYMX"));//专业明细编译后显示文字
			
			
			if("zylx01".equals(sszy1)){
		    	df = (String)fylist.get(k+fylist.indexOf("E"));//生产networkdf
		    }else if("zylx02".equals(sszy1)){
		    	df = (String)fylist.get(k+fylist.indexOf("C"));//营业marketdf
		    }else if("zylx03".equals(sszy1)){
		    	df = (String)fylist.get(k+fylist.indexOf("B"));//办公administrativedf
		    }else if("zylx04".equals(sszy1)){
		    	df = (String)fylist.get(k+fylist.indexOf("A"));//信息化支撑informatizationdf
		    }else if("zylx05".equals(sszy1)){
		    	df = (String)fylist.get(k+fylist.indexOf("D"));//建设投资builddf
		    }else if("zylx06".equals(sszy1)){
		    	df = (String)fylist.get(k+fylist.indexOf("F"));//建设投资builddf
		    }
			if(df==null||"".equals(df)||"null".equals(df)) df="0";
			DecimalFormat pay=new DecimalFormat("0.00");
			df = pay.format(Double.parseDouble(df));//格式化
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
       			<%=sszy2%>
       		</td>
       		<td>
       			<%=qcbcode2%>
       		</td>
       		<td>
       			<%=kjkmcode2%>
       		</td>
       		<td>
       			<%=zymxcode2%>
       		</td>
       		<td>
       			<%=df%>
       		</td>
       		<td>
       			<a target="treeNodeInfo" name="dianbiao" href=<%=path+"/web/electricfees/caiwuzongchaxun_iframe2.jsp?shi="+shi+"&xian="+xian+"&xiaoqu="+xiaoqu+"&zdname="+zdname+"&zdlx="+sitetype+"&zdsx="+jzproperty1+"&bzyf="+bzyf+"&sszy="+sszy1+"&zymxcode="+zymxcode1+"&qcbcode="+qcbcode1+"&shzt="+shzt+"&kjkmcode="+kjkmcode1+"&bz=1"%>><%=tiaoshu%></a>
       		</td>
       </tr>
       <%} }}%>
  	 </table> 
  	 <%} %>
  </body>
</html>
