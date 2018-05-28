<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean" %>
<%@ page import="java.sql.ResultSet"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@page import="com.noki.electricfees.javabean.*"%>
<%@page import="com.noki.function.*"%>
<%
	String sheng = (String)session.getAttribute("accountSheng");
	String roleId = (String)session.getAttribute("accountRole");
    String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
        String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
	    String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";//站点名称
	    String zdsx=request.getParameter("zdsx")!=null?request.getParameter("zdsx"):"0";//站点属性
	    String bzyf=request.getParameter("bzyf")!=null?request.getParameter("bzyf"):""; //报账月份
	    String xxft=request.getParameter("xxft")!=null?request.getParameter("xxft"):"0";//详细分摊
	    String fzyx=request.getParameter("fzyx")!=null?request.getParameter("fzyx"):"0";//分专业线
	      String bzw=request.getParameter("bzw")!=null?request.getParameter("bzw"):"";//标志位
	      String zdlx=request.getParameter("zdlx")!=null?request.getParameter("zdlx"):"";//
	     String whereStr="";
	     String whereyc="";
	     String wherebz="",ww="",dd="";
        String path = request.getContextPath();
        String loginName = (String)session.getAttribute("loginName");
        Account account = (Account)session.getAttribute("account");
        String loginId  = account.getAccountId();
        
         String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	    int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
          
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
  </head>
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
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
 <script type="text/javascript">
 function zd(zdid){ 
	 var path='<%=path%>';   
	 window.open(path+"/web/query/basequery/sitepaymentstatisticscentreyf.jsp?zdid="+zdid,'','width=500,height=400,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
    }
 
 
 </script> 
  <body>
    <% if(bzw != null && !bzw.equals("") && !bzw.equals("0")){
    %>
    <table width="100%" border="0" cellspacing="1" cellpadding="1">
           <tr class="relativeTag" >
             <td width="2%" bgcolor="#DDDDDD" class="form_label"><div align="center" class="bttcn">ID</div></td>
              <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">所在地区</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">站点名称</div></td>
             <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">站点属性</div></td>
              <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">站点类型</div></td>
              <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">站点负责人</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">分专业线状态</div></td>
                <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">详细分摊状态</div></td>
               <td  bgcolor="#DDDDDD" class="form_label" ><div align="center" class="bttcn">站点详细</div></td>
         </tr>
       <%
         	if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" AND Z.SHI='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" AND Z.XIAN='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" AND Z.XIAOQU='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("")){
				whereStr=whereStr+" AND Z.JZNAME LIKE '%"+zdname+"%'";
			}
			if(zdsx != null && !zdsx.equals("") && !zdsx.equals("0")){
				whereStr=whereStr+" AND Z.PROPERTY  ='"+zdsx+"'";//站点属性
			}
			if(zdlx != null && zdlx != "" && !zdlx.equals("0")){
				whereStr=whereStr+" AND Z.STATIONTYPE='"+zdlx+"'";
			}
			//if(bzyf != null && !bzyf.equals("") && !bzyf.equals("0")){
			 //ww=",dianduview dv,dianfeiview dfv";
			 //dd="AND d.DBID = dv.AMMETERID_FK AND dv.AMMETERDEGREEID = dfv.AMMETERDEGREEID_FK";
				////whereStr=whereStr+" AND E.ACCOUNTMONTH  ='"+bzyf+"'";//报账月份
				////wherebz=" and d.dbid in(select a.ammeterid_fk from dianduview a,dianfeiview e where a.ammeterdegreeid=e.ammeterdegreeid_fk  and e.accountmonth='"+bzyf+"')";
			//wherebz="and dfv.accountmonth='"+bzyf+"'";
			//}
			if(xxft!= null && !xxft.equals("") && !xxft.equals("0")){
				//whereStr=whereStr+" AND   ='"+xxft+"'";//
				if("1".equals(xxft)){
				whereyc=whereyc+"AND wc1='1'";
				}else if("2".equals(xxft)){
				whereyc=whereyc+"AND wf1='1'";
				}else if("3".equals(xxft)){
				whereyc=whereyc+"AND yc1='1'";
				}
			}
			if(fzyx != null && !fzyx.equals("") && !fzyx.equals("0")){
				//whereStr=whereStr+" AND   ='"+fzyx+"'";//
				if("1".equals(fzyx)){
				whereyc=whereyc+"AND wc='1'";
				}else if("2".equals(fzyx)){
				whereyc=whereyc+"AND wf='1'";
				}else if("3".equals(fzyx)){
				whereyc=whereyc+"AND yc='1'";
				}
			}
			
		 System.out.println("监测点-whereStr:"+whereStr);
		 Zhandianfentanmx bean=new Zhandianfentanmx();  
		 EquipmentmanageBean bean1=new EquipmentmanageBean();
		if(bzw != null && !bzw.equals("") && !bzw.equals("0")){
		 List<CityQueryBean> fylist = null;
		System.out.println("1111222"); 
       	 fylist = bean.getFt(whereStr,loginId,wherebz,whereyc,ww,dd);
       	 System.out.println("5555"); 
       	 allpage=bean.getAllPage();
       String str="";
		String zdname1="",szdq="",zdid="",fzr="",zdsx1="",zdlx1="",fzyx1="",xxft1="",dianbiaoid="",wc="",wf="",yc="",wc1="",wf1="",yc1="";
		String zdsx01="";
		int intnum=xh = pagesize*(curpage-1)+1;
		  if(fylist!=null){
             for (CityQueryBean beans:fylist) {   

		     //num为序号，不同页中序号是连续的
		     szdq=beans.getAddress();//所在地区
		      zdid=beans.getZdid();//所在地区
			zdname1 = beans.getJzname();
			zdsx1 =beans.getZdsx();//站点属性
			zdlx1 = beans.getZdtype();//站点类型
			fzr = beans.getFzr();//负责人
			wc = beans.getWc();//分专业线
			wf=beans.getWf();
			yc=beans.getYc();
			if("1".equals(wc)){
				fzyx1="完成";
			}else if("1".equals(wf)){
				fzyx1="未分";
			}else if("1".equals(yc)){
				fzyx1="异常";
			}
			
			wc1 = beans.getWc1();//分专业线
			wf1=beans.getWf1();
			yc1=beans.getYc1();
			if("1".equals(wc1)){
				xxft1="完成";
			}else if("1".equals(wf1)){
				xxft1="未分";
			}else if("1".equals(yc1)){
				xxft1="异常";
			}
			dianbiaoid = beans.getDbid();
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
       			<%=szdq%>
       		</td>
       		<td>
       			<a target="treeNodeInfo" name="dianbiao" href=<%=path+"/web/newgn/fentanchaxun_iframe2.jsp?dianbiaoid="+dianbiaoid%>><%=zdname1%></a>
       		</td>
       		<td>
       			<%=zdsx1%>
       		</td>
       		<td>
       			<%=zdlx1%>
       		</td>
       		<td>
       			<%=fzr%>
       		</td>
       		<td>
       			<%=fzyx1%>
       		</td>
       		<td>
       			<%=xxft1%>
       		</td>
       		<td><a href="javascript:zd('<%=zdid%>')">
       			详细</a>
       		</td>
       		
       		
       </tr>
       <%} 
       
       }}%>
     
     
  	 </table> 
  	 <%} %>
  </body>
</html>
