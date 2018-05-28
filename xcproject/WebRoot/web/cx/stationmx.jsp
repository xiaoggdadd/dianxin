<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.jizhan.JiZhanBean,com.noki.jizhan.ZhanDianForm"%>
<%@ page import="java.text.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sheng = (String)session.getAttribute("accountSheng");
Account account = (Account)session.getAttribute("account");
String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String citystatus = request.getParameter("citystatus")!=null?request.getParameter("citystatus"):"2";
String manauditstatus = request.getParameter("manauditstatus")!=null?request.getParameter("manauditstatus"):"3";
String times = request.getParameter("times")!=null?request.getParameter("times"):"";
String bztimes = request.getParameter("bztimes");
String dfzflx = request.getParameter("dfzflx")!=null?request.getParameter("dfzflx"):"0";
String loginId=account.getAccountId();
String color="";
int intnum=0;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}
.form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
	height:23px

}
.selected_font{
		width:100px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
.form{
	width:130px;
}
 .bttcn{ color:black;font-weight:bold;}
</style>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script type="text/javascript">
//=点击展开关闭效果=

function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
var openTip = oOpenTip || "";
var shutTip = oShutTip || "";
if(targetObj.style.display!="none"){
   if(shutAble) return;
   targetObj.style.display="none";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = shutTip; 
   }
} else {
   targetObj.style.display="block";
   if(openTip  &&  shutTip){
    sourceObj.innerHTML = openTip; 
   }
}
}
</script>
<script language="javascript">
var path = '<%=path%>';
  function queryDegree(){
                
    
                   document.form1.action=path+"/web/cx/stationmx.jsp";
                       
                   document.form1.submit();
       
    }
$(function(){
    
	$("#query").click(function(){
	
	//var shi=document.form1.shi.value;

		queryDegree();
	});

});

var path = '<%=path%>';    
    function modifyad(citystatus,code,dfzflx,manauditstatus,times,bztimes){
    	var b=path+"/web/cx/stationmxn.jsp?citystatus="+citystatus+"&month="+times+"&manauditstatus="+manauditstatus+"&code="+code+"&dfzflx="+dfzflx+"&bztimes="+bztimes;			
			 var a = " <a href="+b+" target='treeMap' id='tmpTree'></a>";
			// alert(a);
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click();   
    } 
</script>
  </head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>  
 <body  class="body" style="overflow-x:hidden;">
	
	<form action="" name="form1" method="POST">
	
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		<tr>
		<td colspan="4">
			  <div style="width:700px;height:50px">
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点明细统计</span>	
			  </div>
	    </td>
		</tr>		
		<tr><td height="23" colspan="4">
   				<div id="parent" style="display:inline">
                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
	        </td>
	    </tr>
	    <tr><td width="1200">
	    	<table>
	    	<tr class="form_label">
		    		<td>城市：</td><td><select name="shi" class="selected_font" onchange="changeCity()">
         		<option value="0">请选择</option>
         		<%
		         	ArrayList shilist = new ArrayList();
		         	shilist = commBean.getAgcode(sheng,account.getAccountId());
		         	if(shilist!=null){
		         		String agcode="",agname="";
		         		int scount = ((Integer)shilist.get(0)).intValue();
		         		for(int i=scount;i<shilist.size()-1;i+=scount)
	                    {
	                    	agcode=(String)shilist.get(i+shilist.indexOf("AGCODE"));
	                    	agname=(String)shilist.get(i+shilist.indexOf("AGNAME"));
	                    %>
	                    <option value="<%=agcode%>"><%=agname%></option>
	                    <%}
		         	}
		         %>
         		</select></td>
         						
         		 <td>电费支付类型：</td>
                 <td><select name="dfzflx"  class="selected_font" >
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList dfzflxa = new ArrayList();
			         	dfzflxa = ztcommon.getSelctOptions("DFZFLX");
			         	if(dfzflxa!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)dfzflxa.get(0)).intValue();
			         		for(int i=cscount;i<dfzflxa.size()-1;i+=cscount)
		                    {
		                    	code=(String)dfzflxa.get(i+dfzflxa.indexOf("CODE"));
		                    	name=(String)dfzflxa.get(i+dfzflxa.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
    	
    		         </select></td>			
		             <td>查询月份：</td>
                     <td><input type="text" class="selected_font" name="times" value="<%if (null != request.getParameter("beginTime1"))out.print(request.getParameter("beginTime1"));%>"
                         readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="form" /></td>
                    
                     <td>报账月份：</td>
                     <td><input type="text" class="selected_font" name="bztimes" value="<%if (null != request.getParameter("beginTime1"))out.print(request.getParameter("beginTime1"));%>"
                         readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})" class="form" /></td>
                     
		                        
         			<td>
						<p><font size="2">
								 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
						
						</font>
					</p></td>
					 <td>
						       <div width="60%" id="query" style="position:relative;width:60px;height:23px;cursor: pointer;right:-220px;float:right;top:0;">
								<img alt="" src="<%=request.getContextPath() %>/images/chaxun.png" width="100%" height="100%" />
								<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span>
		                          </div>
					</td>
			    			
			
			</tr>
	</table>
	
	</td></tr>	
 </table>
	
	
					<div style="width:88%;" > 
						<p id="box3" style="display:none">
							<table>
							<tr class="form_label">
					          <td> 市级审核状态：</td>
                              <td><select name="citystatus" class="selected_font" onchange="javascript:document.form1.citystatus2.value=document.form1.citystatus.value">
         		                           <option value="2">请选择</option>
         		                           <option value="0">未通过</option>
         		                           <option value="1">通过</option>
         		
                                   </select></td> 	
                             <td>  人工审核状态：</td>            
   	                          <td><select name="manauditstatus" class="selected_font" onchange="javascript:document.form1.manauditstatus2.value=document.form1.manauditstatus.value">
   		                         <option value="3">请选择</option>
   		                         <option value="1">人工通过</option>
   		                         <option value="2">财务通过</option>
   		                         <option value="0">人工未通过</option>
   		                         <option value="-1">财务未通过</option>
   	                          </select></td> 
   	                        
         								
							
			        </tr>
			</table>
		</p>
	</div>	
			<table class="form_label">
					<tr>
                        <td colspan="8">
                        <div style="width:50px;display:inline;"><hr></div>&nbsp;城市信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
                        </td>							
			       </tr>
			</table>
			<table class="form_label" width="100%" height="240px" bgcolor="#cbd5de"  cellspacing="1" cellpadding="1">
			<tr>
				<td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">编号</div></td>
	            <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">城市</div></td>
	            <td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">审核通过站点总数</div></td>
	            <td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">系统结算电表数</div></td>
	            <td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电表交费次数</div></td>
	            <td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">缴费率</div></td>
	            <td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">自动审核条数</div></td>
	            <td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">自动审核率</div></td>
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核条数</div></td>
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">人工审核率</div></td> 
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市级审核条数</div></td>
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">市级审核率</div></td> 
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务审核条数</div></td>
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">财务审核率</div></td> 
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">打印条数</div></td>
	  			<td width="6.5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">打印率</div></td> 
	  			
            </tr>
            <%
            String whereStr="";
            String where="";
            String str="";
            if(shi != null && !shi.equals("") && !shi.equals("0")){
    			whereStr=whereStr+" and z.shi='"+shi+"'";
    			str=" and zd.shi='"+shi+"'";
    		}
    		if(citystatus != null && !citystatus.equals("") && !citystatus.equals("2")){
    			whereStr=whereStr+" and yf.cityaudit='"+citystatus+"'";
    		}
    		if(manauditstatus != null && !manauditstatus.equals("") && !manauditstatus.equals("3")){
    			if(manauditstatus.equals("-1")){
    				manauditstatus="2";
    				whereStr=whereStr+" and yf.manualauditstatus<>'"+manauditstatus+"'";
    			}else{
    				whereStr=whereStr+" and yf.manualauditstatus='"+manauditstatus+"'";
    			}
    		}
    		if(times != null && !times.equals("") && !times.equals("0")){
    			whereStr=whereStr+" and to_char(yf.startmonth,'yyyy-mm')> = '"+times+"'and to_char(yf.startmonth,'yyyy-mm')< = '"+times+"'";
    		}
    		if(bztimes != null && !bztimes.equals("") && !bztimes.equals("0")){
    			whereStr=whereStr+" and to_char(yf.ACCOUNTMONTH,'yyyy-mm') = '"+bztimes+"'";
    		}
    		if(dfzflx != null && !dfzflx.equals("") && !dfzflx.equals("0")){
    			where=" and d.dfzflx = '"+dfzflx+"'";
    		}
    		
    		
    		//co审核通过站点总数 cou系统结算电表数 coun电表交费次数 counn电费人工审核条数
            
          	 ArrayList<ZhanDianForm> fylist = null;
             JiZhanBean bean=new JiZhanBean();
            
             String code="",id="",chengshi="",zdshu="",dbshu="",jfshu="",jfl="",cwshu="",shishu="",cwlv="",shilv="",liuchshu="",liuchlv="";
             String rgshu="",rglv="",zishu="",zilv="";
             System.out.println("到底是什么："+shi);
             if(shi!="0"){
            	 fylist=bean.getbean1(whereStr,str,dfzflx,where,loginId); 
            	for(ZhanDianForm list:fylist){
	             chengshi = list.getShi();
	             chengshi = chengshi != null ? chengshi : "";
	             
	             code = list.getCode();
	             code = code != null ? code : "";
	             
	             zdshu =list.getZhan();
	             zdshu = zdshu != "" ? zdshu : "0";
	             
	             dbshu = list.getDianbiao();
	             dbshu = dbshu != "" ? dbshu : "0";
	             
	             jfshu = list.getJiaofei();
	             jfshu = jfshu != "" ? jfshu : "0";
	             
	             cwshu =list.getCaiwu();
	             cwshu = cwshu != "" ? cwshu : "0";
	             
	             shishu = list.getShiji();
	             shishu = shishu != "" ? shishu : "0";
	             
	             liuchshu = list.getLiuchenghao();
	             liuchshu = liuchshu != "" ? liuchshu : "0";
	             
	             rgshu = list.getRengong();
	             rgshu = rgshu != "" ? rgshu : "0";
	             
	             zishu = list.getZidong();
	             zishu = zishu != "" ? zishu : "0";
	             
	             DecimalFormat pay2=new DecimalFormat("0.00");
	             Double db=0.0,jf=0.0,shia=0.0,cw=0.0,liucha=0.0,zi=0.0,rg=0.0;
	             if(jfshu!=""){
	            	 jf=Double.parseDouble(jfshu); 
	             }
	             if(dbshu!=""){
		             db=Double.parseDouble(dbshu);
	             } 
	             if(zishu!=""&&zishu!=" "&&zishu!=null&&zishu!="null"){
	            	  zi=Double.parseDouble(zishu);
	             }
	             if(rgshu!=""&&rgshu!=" "&&rgshu!=null&&rgshu!="null"){
	            	  rg=Double.parseDouble(rgshu);
	             }
	             if(cwshu!=""){
	            	 cw=Double.parseDouble(cwshu);
	             }
	             if(shishu!=""){
	            	 shia=Double.parseDouble(shishu);
	             }
	             if(liuchshu!=""){
	            	 liucha=Double.parseDouble(liuchshu);
	             }
	           
	           
     			Double avg=jf/db*100;
                jfl = pay2.format(avg);
                
                Double avg1=zi/jf*100;
	            zilv=pay2.format(avg1);
                
                Double avg11=rg/jf*100;
	            rglv=pay2.format(avg11);
	            
                Double avg4=shia/jf*100;
                shilv=pay2.format(avg4);
             
                Double avg3=cw/jf*100;
                cwlv=pay2.format(avg3);
                
                Double avg44=liucha/jf*100;
                liuchlv=pay2.format(avg44);
               if(jf==0){
            	   jfl="0.00";
            	   zilv="0.00";
            	   rglv="0.00";
            	   shilv="0.00";
            	   cwlv="0.00";
            	   liuchlv="0.00";
               }
	           	
	          
	            
             if(intnum%2==0){
 			    color="#FFFFFF" ;
 			 }else{
 			    color="#DDDDDD";
 			 }
               intnum++;
            %>
            <tr bgcolor="<%=color%>">
	            <td><div align="center" ><input type="hidden" name="code" value="<%=code %>"/><%=intnum%></div></td>
	       		<td><div align="center" ><a href="javascript:modifyad('<%=citystatus%>','<%=code %>','<%=dfzflx %>','<%=manauditstatus %>','<%=times %>','<%=bztimes %>')" ><%=chengshi%></a></div></td> 
	       		<td><div align="right" ><%=zdshu%></div></td> 
       		    <td><div align="right" ><%=dbshu%></div></td>
	       		<td><div align="right" ><%=jfshu%></div></td> 
	       		<td><div align="right" ><%=jfl%>%</div></td> 
	       		<td><div align="right" ><%=zishu%></div></td>
	       		<td><div align="right" ><%=zilv%>%</div></td> 
	       		<td><div align="right" ><%=rgshu%></div></td>
	       		<td><div align="right" ><%=rglv%>%</div></td> 
	       		<td><div align="right" ><%=shishu%></div></td>
	       		<td><div align="right" ><%=shilv%>%</div></td> 
	       		<td><div align="right" ><%=cwshu%></div></td>
	       		<td><div align="right" ><%=cwlv%>%</div></td> 
	       		<td><div align="right" ><%=liuchshu%></div></td>
	       		<td><div align="right" ><%=liuchlv%>%</div></td> 
	       		
       		</tr>
       		 <%}
 			 }%>
           
			</table>	
			<iframe name="treeMap" width="100%" height="270px" frameborder="0"></iframe> 	
			</form>
			<%
			Calendar cal=Calendar.getInstance();   
			String yf="";
			int y=cal.get(Calendar.YEAR);    
			String m=cal.get(Calendar.MONTH)+1+"";   
			if(m.length()==1){
				yf=y+"-0"+m;
			}else{
				yf=y+"-"+m;
			}
			

			%>
  </body>
</html>
<script type="text/javascript">
	document.form1.shi.value='<%=shi%>';
	document.form1.citystatus.value='<%=citystatus%>';
	document.form1.manauditstatus.value='<%=manauditstatus%>';
	document.form1.times.value='<%=times%>';
	document.form1.dfzflx.value='<%=dfzflx%>';
var start='<%=bztimes%>';
var s="null";
if(start==s){
document.form1.bztimes.value='<%=yf%>';
}else if(start!=s){
document.form1.bztimes.value='<%=bztimes%>';
}
	

</script>
