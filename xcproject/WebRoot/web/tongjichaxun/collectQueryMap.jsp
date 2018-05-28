<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.query.caijipoint.javabean.haodianliangBean,com.noki.mobi.common.zdbzbean,com.noki.tongjichaxu.Jzhdl" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.text.*"%>
<%@ page import="java.net.URLEncoder"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String startmonth = request.getParameter("startmonth")!=null?request.getParameter("startmonth"):"";
String endmonth = request.getParameter("endmonth")!=null?request.getParameter("endmonth"):"";
System.out.println(startmonth+"时间");
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
curpage=Integer.parseInt(s_curpage);
String permissionRights="";
// zp,zs,cj,sb,jz,g2,g3,shi,xian,xiaoqu,bzyf,bla
String roleId = (String)session.getAttribute("accountRole");

StringBuilder paiming=new StringBuilder();

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
String jzname1=request.getParameter("jzname");
String id=request.getParameter("id");
String dbid=request.getParameter("dbid");

// System.out.println(" 显示在这里  "+"shi:"+shi+" xian:"+xian+" xiaoqu="+xiaoqu+" bla="+bla+" g3:"+g3+" yf:"+yf);
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

    <% 
                 	String whereStr = "";
                     String str="",str1="",str2="";
                     
              		if(null !=id  && !id.equals("")){
              	whereStr=whereStr+" and zdd.id='"+id+"'";
              		}
              		if(null != dbid && !dbid.equals("")){
              	
              	whereStr=whereStr+" and dbb.dbid='"+dbid+"'";
              		}	
              			
              	
              		
              		
              		
              		
              		if(id!=null&&!"".equals(id)&&null!=dbid&&!"".equals(dbid)){
              		 DecimalFormat mat=new DecimalFormat("0.00");
                  Jzhdl  bean = new Jzhdl();
                 
                  int j=1;
                 	List<zdbzbean> fylist = null;
                 	
                  fylist=bean.getPageDatapa(whereStr);
                  
          //    for(int i=0;i<fylist.size();i++){   
	       //    if(j%2==0){
	           
	       //    paiming.append(fylist.get(i).toString().split(",")[1]+",");
	       //    paiming.append(fylist.get(i).toString().split(",")[0]+";");
	       //   }
	        //   else{
	       ///    paiming.append(fylist.get(i).toString().split(",")[1]+",");
	       //    paiming.append(fylist.get(i).toString().split(",")[0]+";");
	         //  }
	       //      j++;
	     //   }
                  
                  String hdl="",m="",mon="";
                 	//fylist = bean.getCollectQuery(curpage,whereStr,zdid);
            		String last = "",thisd = "",id1="",actualdegree="",actualpay="",llhdl="",szdcode="",stationtype1="",jzproperty1="",jztype1="",lrrq="",shi1="",xian1="",xiaoqu1="";
            		String shia="",xiana="",xiaoqua="",sname="",bili="0.00",dbid1="",acmonth="";
            		int intnum=xh = pagesize*(curpage-1)+1;
            		int linenum=0;
            		double df=0.0;
            		double df1=0.0;
            		
            		
            		for(zdbzbean bean1:fylist){
            		hdl=bean1.getHdl();
            		m=bean1.getMoney();
            		mon=bean1.getMonth();
            		if("".equals(hdl)||null==hdl){hdl="0";}
            		if("".equals(m)||null==m){m="0";}
            		m=mat.format(Double.parseDouble(m));
            		hdl=mat.format(Double.parseDouble(hdl));
            	        String color=null;       
            	       paiming.append(mon+",");
	                   paiming.append(hdl+",");
	                   paiming.append(";");
            	      //  paiming.append(hdl+",");   
            	      //   paiming.append(mon+";");
            	if(intnum%2==0){
            	    color="#DDDDDD";

            	 }else{
            	    color="#FFFFFF" ;
            	}
                    intnum++;
      %>
      
        <%
       }
       %> 
    
   
  	 <% if(fylist.size()>0) {%>
  	  <div><img src="<%=path%>/servlet/Yhdl?bz=1&fylist=<%=URLEncoder.encode(paiming.toString(),"utf-8") %>"/></div>
       <%}%>
         <%}%>
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
   function dfinfo9(dbyt,dbid,bl,yf,bzmonth,sjyf,shia,xiana,xiaoqua,bili){
    var url=path+"/web/query/caijipoint/stationfees9.jsp?dbyt="+dbyt+"&dbid="+dbid+"&bl="+bl+"&yf="+yf+"&bzmonth="+bzmonth+"&sjyf="+sjyf+"&shi="+shia+"&xiana="+xiana+"&xiaoqu="+xiaoqua+"&bili="+bili;		
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
