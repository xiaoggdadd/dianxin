<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean,com.noki.mobi.common.AccountJzqaS,com.noki.mobi.common.zdbzbeanB" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String startmonth = request.getParameter("startmonth")!=null?request.getParameter("startmonth"):"";
String endmonth = request.getParameter("endmonth")!=null?request.getParameter("endmonth"):"";
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
curpage=Integer.parseInt(s_curpage);
String permissionRights="";
// zp,zs,cj,sb,jz,g2,g3,shi,xian,xiaoqu,bzyf,bla
String roleId = (String)session.getAttribute("accountRole");

// function modifyad(zp,zs,cj,g2,g3,shi,xian,bla,bd,yf,bzmonth,dyt,sjyf){

String zp1=request.getParameter("zp");
String zs1=request.getParameter("zs");
String cj1=request.getParameter("cj");
String g2=request.getParameter("g2");
String g3=request.getParameter("g3".trim());
String shiaa=request.getParameter("shi");
String xianaa=request.getParameter("xian");
String bla=request.getParameter("bl");
String bd=request.getParameter("bd");//标准电量
String yf=request.getParameter("yf");
String bzmonth=request.getParameter("bzmonth");
String dbyt=request.getParameter("dyt");
String sjyf=request.getParameter("sjyf");
String gxbzw=request.getParameter("gxbzw");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'haodianliangleft.jsp' starting page</title>   
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
bttcn{color:white;font-weight:bold;}
 .form    {width:130px}

#id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}; 
 </style> 
  </head> 
  <body class="body" style="overflow-x:hidden;">

  <table border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
   <tr height = "20" class= "relativeTag">
   <td colspan="7" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">站点/电量信息</div></td>
   
   </tr>
     <tr height = "20" class= "relativeTag">
       <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">序号</div></td>
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">地市</div></td>
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">区县</div></td>        
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">乡镇</div></td>
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">站点名称</div></td>
         <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">实际电量</div></td>
     <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" style="font-weight:bold">超标比例</div></td>
     </tr><% 
                 	String whereStr = "";
                     String str="",str1="",str2="";
                     
              		if(null !=shiaa  && !shiaa.equals("") && !shiaa.equals("0")){
              	whereStr=whereStr+" and zd1.shi='"+shiaa+"'";
              		}
              		if(null != xianaa && !xianaa.equals("") && !xianaa.equals("0")){
              	
              	whereStr=whereStr+" and zd1.xian='"+xianaa+"'";
              		}	
              			
              		if(null!=yf&&!yf.equals("")){
                    if("3".equals(yf)){
                  if(null!=sjyf&&!"".equals(sjyf)){
                 	whereStr=whereStr+" and to_char(accountmonth,'yyyy-mm')='"+sjyf+"'";
                 }
              }
              if("2".equals(yf)){
              if(null!=sjyf&&!"".equals(sjyf)){
                 	whereStr=whereStr+" and to_char(startmonth,'yyyy-mm')='"+sjyf+"'";
                 }
              }
              
              
           }
              		str1=str1+" AND ZD.g2='"+g2+"'";
              		str1=str1+" AND ZD.g3='"+g3+"'";
              		str1=str1+" AND ZD.ZP='"+zp1+"'";
              		str1=str1+" AND ZD.ZS='"+zs1+"'";
              		str1=str1+" and ZD.CHANGJIA='"+cj1+"'";
              		
              		if(bla!=null&&!"".equals(bla)&&null!=yf&&!"".equals(yf)){
              		 DecimalFormat mat=new DecimalFormat("0.00");
                  AccountJzqaS  bean = new AccountJzqaS();
                 	List<zdbzbeanB> fylist = null;
                  fylist=bean.getPageDatapa(whereStr,str1,bla,yf,bzmonth,dbyt,gxbzw);
                  
                 	//fylist = bean.getCollectQuery(curpage,whereStr,zdid);
            		String last = "",thisd = "",id="",actualdegree="",actualpay="",llhdl="",szdcode="",stationtype1="",jzproperty1="",jztype1="",lrrq="",shi1="",xian1="",xiaoqu1="";
            		String shia="",xiana="",xiaoqua="",sname="",bili="0.00",dbid="",acmonth="";
            		String blhdl="";//倍率耗电量 
            		String entrytime="";//录入时间
            		int intnum=xh = pagesize*(curpage-1)+1;
            		int linenum=0;
            		double df=0.0;
            		double df1=0.0;
            		 if(fylist!=null)
            		{
            		for(zdbzbeanB bean1:fylist){
            		 dbid=bean1.getDbid();
            		 acmonth=bean1.getAccountmonth();
            		   shia=bean1.getSHI();
            		   xiana=bean1.getXian();
            		   xiaoqua=bean1.getXiaoqu();
            		   sname=bean1.getZdname();
            		   shi1=bean1.getShi1();
            		   xian1=bean1.getXian1();
            		   xiaoqu1=bean1.getXiaoqu1();
            		   blhdl=bean1.getBlhdl();
            		   entrytime=bean1.getEntrytime();
            		   bili=bean1.getBl();
            		   id=bean1.getId();
            		   if("".equals(bili)||null==bili){bili="0";}
            		   bili=mat.format(Double.parseDouble(bili));
            		   
            		   if("".equals(blhdl)||null==blhdl){blhdl="0";}
            		   blhdl=mat.format(Double.parseDouble(blhdl));
            	        String color=null;          
            	if(intnum%2==0){
            	    color="#DDDDDD";

            	 }else{
            	    color="#FFFFFF" ;
            	}
                    intnum++;
      %>
      <tr bgcolor="<%=color%>" class="form_label">
          <td><div align="center" ><%=intnum-1%></div></td> 
            <td><div align="center" ><%=shi1%></div></td> 
            <td><div align="center" ><%=xian1%></div></td>
            <td><div align="center" ><%=xiaoqu1%></div></td> 
            <td><div align="left"  ><a href="javascript:lookDetails('<%=id%>')"><%=sname%></a></div></div></td> 
            <td><div align="center" ><%=blhdl%></div></td> 
            <td><div align="right" ><a href="javascript:dfinfo9('<%=dbyt%>','<%=dbid%>','<%=yf%>','<%=bzmonth%>','<%=sjyf%>','<%=shia%>','<%=xiana%>','<%=xiaoqua%>','<%=id%>','<%=entrytime%>')"><%=bili%>%</a></div></td>        
        </tr>   
        <%
       }}}%>     
        
   </table>
  </body>
</html>
<script language="javascript">
var path = '<%=path%>';
function ShowHideSearchRegion(trObject,SelfObject)
{
		if(trObject.style.display == "none")
		{
			trObject.style.display = ""
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/1.gif\">"

		}
		else
		{
			trObject.style.display = "none"
			SelfObject.innerHTML = "<img border=\"0\" src=\"../../images/SearchDown.gif\">"
		}
}

	function delLogs(){
		

	}
	
    function dfinfo8(id){
    var url=path+"/web/query/caijipoint/stationfees8.jsp?id="+id;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}
   function dfinfo9(dbyt,dbid,yf,bzmonth,sjyf,shia,xiana,xiaoqua,id,entrytime){
    var url=path+"/web/query/caijipoint/showdianfeixx.jsp?dbyt="+dbyt+"&dbid="+dbid+"&yf="+yf+"&bzmonth="+bzmonth+"&sjyf="+sjyf+"&shi="+shia+"&xiana="+xiana+"&xiaoqu="+xiaoqua+"&zdid="+id+"&entrytime="+entrytime;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}
	 function lookDetails(zdcode){ 
	  window.open(path+"/web/query/caijipoint/shenhemodifsite.jsp?id="+zdcode,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
    }
</script>
<script type="text/javascript">
function lookDetailss(id,dbid){ 
	 var path='<%=path%>';   
	 window.open(path+"/web/check/showdfxx.jsp?dbid="+dbid+"&id="+id,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
    }
    
  
    
</script>
<script type="text/javascript">
	window.parent.CloseDiv();
</script>

