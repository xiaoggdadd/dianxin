<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="com.noki.jizhan.StationRelationshipTreeBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="StyleSheet" href="<%=path%>/css/atatonic/zp-compressed.css" type="text/css" />
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
bttcn{color:white;font-weight:bold;font-size:14px}
 .form    {width:130px}
#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 </style>
  </head>
  <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
  <body>
  <form action="" name="form1" method="POST">
 <table width="100%" border="1" cellspacing="1" cellpadding="1" >

    
  	 <%
  		
  	    String stationName=request.getParameter("stationName");
  	    String shengbb=request.getParameter("shengbb");
  	    System.out.println("--"+shengbb);
  	    String benbu="";
  	    String shibb=request.getParameter("shibb");
  	    String xianbb=request.getParameter("xianbb");
  	    String startmonth=request.getParameter("startmonth");
  	    String endmonth=request.getParameter("endmonth");
  	    String sszy=request.getParameter("fentanName");
  	    String fentan=ztcommon.getsszy(sszy);
  	    if(null!=shengbb&&!shengbb.equals("")){
  	    	if(shengbb.equals("省本部")){
  	    		benbu="gsf01";
  	    	}else{
  	    		benbu="gsf05";
  	    	}
  	    	
  	    }else if(null!=shibb&&!shibb.equals("")){
  	    	if(shibb.equals("市本部")){
  	    		benbu="gsf02";
  	    	}else{
  	    		benbu="gsf06";
  	    	}
	    }else if(null!=xianbb&&!xianbb.equals("")){
	    	if(xianbb.equals("县本部")){
  	    		benbu="gsf03";
  	    	}else{
  	    		benbu="gsf07";
  	    	}
	    }
  	    String city=request.getParameter("cityGrade");
  	    String xian=request.getParameter("countryGrade");
  	    String xiaoqu=request.getParameter("stationType");
  	    System.out.println(stationName+benbu+city+xian+xiaoqu);
  	    String str="";
  	  if(null !=city  && !city.equals("") && !city.equals("-1")){
  		    
			str=str+" and z.shi=g.agcode and g.agname='"+city+"'";
		}
  	if(null !=xian  && !xian.equals("") && !xian.equals("-1")){
		str=str+" and z.xian=g.agcode and g.agname='"+xian+"'";
	}
  	if(null !=xiaoqu  && !xiaoqu.equals("") && !xiaoqu.equals("-1")){
		str=str+" and z.xiaoqu=g.agcode and g.agname='"+xiaoqu+"'";
	}if(str==""){
		str="and z.sheng=g.agcode and g.agname='山东省'"; 
	}
  	if(null !=benbu  && !benbu.equals("") && !benbu.equals("-1")){
		str=str+" and z.gsf='"+benbu+"'";
	}else{
		str=str+" and z.gsf='gsf04'";
	}
  	if(null !=stationName  && !stationName.equals("") && !stationName.equals("-1")){
		str=str+" and i.name='"+stationName+"'";
	}
	if(null !=startmonth  && !startmonth.equals("") && !startmonth.equals("null")){
		str=str+" and dl.startmonth>='"+startmonth+"'";
	}
	if(null !=endmonth  && !endmonth.equals("") && !endmonth.equals("null")){
		str=str+" and dl.startmonth<='"+endmonth+"'";
	}if(null !=fentan  && !fentan.equals("") && !fentan.equals("null")){
		str=str+" and s.shuoshuzhuanye='"+fentan+"'";
	}
	
  	  StationRelationshipTreeBean bean=new StationRelationshipTreeBean();
  	ArrayList fylist = new ArrayList();
   	fylist = bean.getCollect(str);
   
    %>
    <tr height = "20">
         <td width="8%" height="23"  bgcolor="#888888"><div align="left" style="font-size:14px;">站点编号</div></td>     
         <td width="15%" height="23"  bgcolor="#888888"><div align="left" style="font-size:14px;">站点名称</div></td>
         <td width="8%" height="23" bgcolor="#888888"><div align="right" style="font-size:14px;">总耗电量</div></td>
         <td width="8%" height="23" bgcolor="#888888"><div align="right" style="font-size:14px;">总电费</div></td>
         <td width="8%" height="23" bgcolor="#888888"><div align="right" style="font-size:14px;">分摊比例</div></td>
         <td width="8%" height="23" bgcolor="#888888"><div align="right" style="font-size:14px;">分摊费用</div></td>
     </tr>
    <% 
	String jzname = "",actualdegree="",ammeterid_fk="",actualpay="",zdcode="",dbid="",dl="",df="";
	int intnum=0;
	int linenum=0;
	String ftbl="",ftfy="";
	String dlhj="",dfhj="",fentanhj="";
	double dlhjd=0.0,dfhjd=0.0,fentanhjd=0.0;
	
	 if(fylist!=null)
	{
		int fycount = ((Integer)fylist.get(0)).intValue();
		for( int k = fycount;k<fylist.size()-1;k+=fycount){
         linenum++;
	     //num为序号，不同页中序号是连续的
	    jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
	    jzname = jzname != null ? jzname : "";
	    
	    actualdegree = (String)fylist.get(k+fylist.indexOf("DL"));	
	    actualdegree = actualdegree != null ? actualdegree : "";
	    
	    zdcode = (String)fylist.get(k+fylist.indexOf("ZDCODE"));	
	    zdcode = zdcode != null ? zdcode : "";
	    
	    df=(String)fylist.get(k+fylist.indexOf("DF"));
	    df = df != null ? df : "";
	    
	    ftbl=(String)fylist.get(k+fylist.indexOf("DBILI"));
	    ftbl = ftbl != null ? ftbl : "";
	    
		String color=null;
        if(df==null||df.equals(""))df="0";
        if(actualdegree==null||actualdegree.equals(""))actualdegree="0";
        DecimalFormat mat=new DecimalFormat("0.00");
        DecimalFormat mat1=new DecimalFormat("0.0");
        df=mat.format(Double.parseDouble(df));
        actualdegree=mat1.format(Double.parseDouble(actualdegree));
        ftfy=mat.format(Double.parseDouble(df)*Double.parseDouble(ftbl)/100);
        
        dlhjd+=Double.parseDouble(actualdegree);
        dfhjd+=Double.parseDouble(df);
        fentanhjd+=Double.parseDouble(ftfy);
        
        dfhj=mat.format(dfhjd);
        dlhj=mat1.format(dlhjd);
        fentanhj=mat.format(fentanhjd);
        
		if(intnum%2==0){
		    color="#DDDDDD";

		 }else{
		    color="#FFFFFF" ;
		}
      intnum++;

   %>
   <tr bgcolor="<%=color%>">
   		<td>
   			<div align="left" style="font-size:14px;"><%=zdcode%></div>
   		</td>
   		<td>
   			<div align="left" style="font-size:14px;"><%=jzname%></div>
   		</td>
   		<td>
   			<div align="right" style="font-size:14px;"><%=actualdegree%></div>
   		</td>
   		<td>
   			<div align="right" style="font-size:14px;"><%=df%></div>
   		</td>
   		<td>
   			<div align="right" style="font-size:14px;"><%=ftbl+"%"%></div>
   		</td>
   		<td>
   			<div align="right" style="font-size:14px;"><%=ftfy%></div>
   		</td>
   </tr>
   <%}%>
   <tr>
        <td>
   			<div align="left" style="font-size:14px;">合计</div>
   		</td>
   		<td>
   			<div align="left" style="font-size:14px;"></div>
   		</td><td>
   			<div align="right" style="font-size:14px;"><%=dlhj%></div>
   		</td>
   		<td>
   			<div align="right" style="font-size:14px;"><%=dfhj%></div>
   		</td>
   		<td>
   			<div align="left" style="font-size:14px;"></div>
   		</td>
   		<td>
   			<div align="right" style="font-size:14px;"><%=fentanhj%></div>
   		</td>
   </tr>
			<% }%>
			
  			
  		</table>	
  	</form>
  </body>
</html>
