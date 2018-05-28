<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.net.URLEncoder" %>
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
    	<% if(bzw != null && !bzw.equals("") && !bzw.equals("0")){       		
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
				}
				if("2".equals(shzt)){
			        whereStr=whereStr+" and e.MANUALAUDITSTATUS='"+shzt+"'";
			      }
			      if("0".equals(shzt)){
			       whereStr=whereStr+" and e.MANUALAUDITSTATUS='"+shzt+"'";
			      }
			      if("-1".equals(shzt)){
			       whereStr=whereStr+" and e.MANUALAUDITSTATUS<'2' ";
			      }
			}
		CaiwuzongchaxunBean bean=new CaiwuzongchaxunBean();  
       	ArrayList fylist = bean.getCaiwu(whereStr,loginId);
		String sszy1="",networkdf="",marketdf="",administrativedf="",informatizationdf="",builddf="",dddf="";
		double df1=0.00,df2=0.00,df3=0.00,df4=0.00,df5=0.00,df6=0.00;
		 if(fylist!=null){
			int fycount = ((Integer)fylist.get(0)).intValue();
			for( int k = fycount;k<fylist.size()-1;k+=fycount){
				String df="";
				sszy1= (String)fylist.get(k+fylist.indexOf("SHUOSHUZHUANYE"));//所属专业	
				
				DecimalFormat pay=new DecimalFormat("0.00");
				
				if("zylx01".equals(sszy1)){
					df = (String)fylist.get(k+fylist.indexOf("E"));//生产networkdf
					if(df==null||"".equals(df)||"null".equals(df)){
						df = "0.00";
					} 
					df1 = df1+Double.parseDouble(df);
			    }else if("zylx02".equals(sszy1)){			    	
			    	df = (String)fylist.get(k+fylist.indexOf("C"));//营业marketdf
					if(df==null||"".equals(df)||"null".equals(df)){
						df = "0.00";
					} 
					df2 = df2+Double.parseDouble(df);
			    }else if("zylx03".equals(sszy1)){
			    	df = (String)fylist.get(k+fylist.indexOf("B"));//办公administrativedf
			    	if(df==null||"".equals(df)||"null".equals(df)){
						df = "0.00";
					} 
					df3 = df3+Double.parseDouble(df);
			    }else if("zylx04".equals(sszy1)){
			    	df = (String)fylist.get(k+fylist.indexOf("A"));//信息化支撑informatizationdf
			    	if(df==null||"".equals(df)||"null".equals(df)){
						df = "0.00";
					} 
					df4 = df4+Double.parseDouble(df);
			    }else if("zylx05".equals(sszy1)){
			    	df = (String)fylist.get(k+fylist.indexOf("D"));//建设投资builddf
			    	if(df==null||"".equals(df)||"null".equals(df)){
						df = "0.00";
					} 
					df5 = df5+Double.parseDouble(df);
			    }else if("zylx06".equals(sszy1)){
			    	df = (String)fylist.get(k+fylist.indexOf("F"));//建设投资builddf
			    	if(df==null||"".equals(df)||"null".equals(df)){
						df = "0.00";
					} 
					df6 = df6+Double.parseDouble(df);
			    }
			    networkdf = pay.format(df1);//格式化
			    marketdf = pay.format(df2);//格式化
			    administrativedf = pay.format(df3);//格式化
			    informatizationdf = pay.format(df4);//格式化
			    builddf = pay.format(df5);//格式化
			    dddf = pay.format(df6);//格式化
       } 
       		String hStr = networkdf + ";" + marketdf + ";" + administrativedf	+ ";" + informatizationdf + ";"+ builddf + ";"+ dddf + ";";
       		String str = "生产;营业;办公;信息化支撑;建设投资;代垫电费;";%>

       		<div>
			<img src="<%=path%>/servlet/huanbi_sr?cityStr=<%=str%>&dataStr=<%=hStr%>&bz=2">
			</div>
      <%}} %>
  </body>
</html>
