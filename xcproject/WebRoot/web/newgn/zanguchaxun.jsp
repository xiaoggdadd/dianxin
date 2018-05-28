<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.function.*" %>
<%@ page import="java.text.*,java.util.regex.Pattern"%>
<%@ page import="com.noki.mobi.common.CommonBean" %>

<%
String loginName = (String)session.getAttribute("loginName");
Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
String sheng = (String)session.getAttribute("accountSheng");
String roleId = (String)session.getAttribute("accountRole");
String accountname=account.getAccountName();
String agcode1="";

	if(request.getParameter("shi")==null){
		ArrayList shilist = new ArrayList();
		CommonBean commBean = new CommonBean();
		shilist = commBean.getAgcode(sheng,account.getAccountId());
	if(shilist!=null){
  		int scount = ((Integer)shilist.get(0)).intValue();
       agcode1=(String)shilist.get(scount+shilist.indexOf("AGCODE"));
     }
	} 
String shi = request.getParameter("shi")!=null?request.getParameter("shi"):agcode1;

String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
String zdname = request.getParameter("zdname")!=null?request.getParameter("zdname"):"";//站点名称
String stationtype1 = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"0";
String lrsj = request.getParameter("lrsj")!=null?request.getParameter("lrsj"):"";//录入时间
String zgjssj1=request.getParameter("zgsj")!=null?request.getParameter("zgsj"):"";
String jzproperty=request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";

String whereStr="";
String Str="";
String Wstr="";
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String color="";

int intnum =0;

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<style type="text/css">
.style1 {
	color: red;
	font-weight: bold;
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
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
}
.bttcn{color:black;font-weight:bold;}
</style>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/tx.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
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

    function queryDegree(){
    	var path='<%=path%>'
   if(document.getElementById("shi").value=="" || document.getElementById("zgsj").value==""){
	       alert("暂估日期不能为空！请填写");
	  }
	  else{
	       document.form1.action=path+"/web/newgn/zanguchaxun.jsp?command=chaxun";
	       document.form1.submit();
	       //showdiv("请稍等......");
	      showdiv("请稍等..............");
	  }
    }
$(function(){
    
	$("#query").click(function(){
		queryDegree();
	});
	$("#daochuBtn").click(function(){
		exportad();
	});
	$("#zhongxin").click(function(){
		exportadzhongxin();
	});
	$("#wuzhongxin").click(function(){
		exportadwu();
	});

});
</script>
  </head>
  <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean>
  <jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon"></jsp:useBean>
  <body>
   <form action="" name="form1" method="POST">
		<table  width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
			<tr>
				<td colspan="8" width="50" >
                <div style="width:700px;height:50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">暂估历史数据查询</span>
	              </div></td>
			</tr>
			<tr class="form_label">
				<td colspan="8">
                       <div id="parent" style="display:inline"></div>
                       <div style="width:50px;display:inline;"><hr></div>&nbsp;过滤条件&nbsp;<div style="width:300px;display:inline;"><hr></div>
                </td>
            </tr>
            <tr>
            <td>
            <table><tr  class="form_label">
	         	<td>地市：</td>
	         	<td><select name="shi" id="shi" style="width:130" onchange="changeShi()" class="selected_font">
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
	         	</select>
	         	</td>
	         	<td>区、县：</td>
	         	<td><select name="xian" id="xian" style="width:130" onchange="changeXian()" class="selected_font" >
	         		<option value="0">请选择</option>
	         		<%
			         	ArrayList xianlist = new ArrayList();
			         	xianlist = commBean.getAgcode(shi,account.getAccountId());
			         	if(xianlist!=null){
			         		String agcode="",agname="";
			         		int scount = ((Integer)xianlist.get(0)).intValue();
			         		for(int i=scount;i<xianlist.size()-1;i+=scount)
		                    {
		                    	agcode=(String)xianlist.get(i+xianlist.indexOf("AGCODE"));
		                    	agname=(String)xianlist.get(i+xianlist.indexOf("AGNAME"));
		                    %>
		                    <option value="<%=agcode%>"><%=agname%></option>
		                    <%}
			         	}
			         %>
	         	</select></td>
	         	<td>乡镇：</td>
	         	<td><select name="xiaoqu" id="xiaoqu" style="width:130" class="selected_font">
	         		<option value="0">请选择</option>
	         		<%
			         	ArrayList xiaoqulist = new ArrayList();
			         	xiaoqulist = commBean.getAgcode(xian,account.getAccountId());
			         	if(xiaoqulist!=null){
			         		String agcode="",agname="";
			         		int scount = ((Integer)xiaoqulist.get(0)).intValue();
			         		for(int i=scount;i<xiaoqulist.size()-1;i+=scount)
		                    {
		                    	agcode=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGCODE"));
		                    	agname=(String)xiaoqulist.get(i+xiaoqulist.indexOf("AGNAME"));
		                    %>
		                    <option value="<%=agcode%>"><%=agname%></option>
		                    <%}
			         	}
			         %>
	         	</select></td>
	         	<td>暂估结束月份</td>
		        <td > <input type="text" name="zgsj" id="zgsj" class="selected_font" value="<%if (null != request.getParameter("zgsj")) out.print(request.getParameter("zgsj"));%>" 
		        				readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"  style="width: 130px;"/><span class="style1">&nbsp;*</span></td>	 
                <td>
							<p><font size="2">
							 <div title="您可以进行详细的条件筛选" id="query1"  onclick="openShutManager(this,'box3',false)"     style="position:relative;width:17px;height:17px;cursor: pointer;top:10PX">
									<img alt="" src="<%=request.getContextPath() %>/images/gaojichaxun.gif" width="100%" height="100%" />
									<span style="font-size:12px;position: absolute;left:2px;top:0px;color:white">&nbsp;&nbsp;&nbsp;</span>
		                          </div>
							
							</font>
					</p></td>
					
         		<td> 
			         <div id="query" style="position:relative;width:59px;height:23px;cursor: pointer;left:100%;TOP:10PX">
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
		<table border='0'>
           </tr>
           <tr  class="form_label">
            <td> 站点名称：</td>
           <td><input type="text" class="selected_font" name="zdname" value="<%if (null != request.getParameter("zdname")) out.print(request.getParameter("zdname"));%>" style="width: 130px;"/></td>
      		<td>站点类型：</td>
         	<td><select name="stationtype" class="selected_font" onchange="kzinfo()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = ztcommon.getSelctOptions("StationType");
	         	if(zdsx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdsx.get(0)).intValue();
	         		for(int i=cscount;i<zdsx.size()-1;i+=cscount)
                    {
                    	code=(String)zdsx.get(i+zdsx.indexOf("CODE"));
                    	name=(String)zdsx.get(i+zdsx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select></td>
         	<td>站点属性：</td>
         <td>
         	<select name="jzproperty" style="width:130" onchange="kzinfo()" class="selected_font">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsxx = new ArrayList();
	         	zdsxx = ztcommon.getSelctOptions("zdsx");
	         	if(zdsxx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdsxx.get(0)).intValue();
	         		for(int i=cscount;i<zdsxx.size()-1;i+=cscount)
                    {
                    	code=(String)zdsxx.get(i+zdsxx.indexOf("CODE"));
                    	name=(String)zdsxx.get(i+zdsxx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
         	%>
         	</select>
         </td>
           <td>录入时间：</td>
      		<td > <input type="text" name="lrsj" id="lrsj" class="selected_font" value="<%if (null != request.getParameter("lrsj")) out.print(request.getParameter("lrsj"));%>" 
      						readonly="readonly" class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})"  style="width: 130px;"/></td>  
		
	 </tr> 
	</table>
	</p>
	</div>	

  <table>
       <tr><td height="5"  colspan="4"  class="form_label">
	         <div id="parent" style="display:inline"></div>
	         <div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div>
            </td>
       </tr>       
 </table>
 <div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" > 	
 <table width="1400" height="60%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
 	  <tr height = "23" class="relativeTag">
 	    <td width="5%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>	                      	
        <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>          
        <td width="15%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在地区</div></td>
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点类型</div></td>
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">电费支付类型</div></td>
	    <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估起始时间</div></td>	                      	
        <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估结束时间</div></td>          
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估天数</div></td>
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">日电费（元/天）</div></td>
        <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">暂估金额</div></td> 
         <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">录入人</div></td> 
              
 	  </tr>	
 	  <%   
 	        int countxh=1;
 	        
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and z.shi='"+shi+"'";
				Wstr=Wstr+" and z.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and z.xian='"+xian+"'";
				Wstr=Wstr+" and z.xian='"+xian+"'";
			}
			if(xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
				whereStr=whereStr+" and z.xiaoqu='"+xiaoqu+"'";
				Wstr=Wstr+" and z.xiaoqu='"+xiaoqu+"'";
			}
			if(zdname != null && !zdname.equals("") && !zdname.equals("0")){
				whereStr=whereStr+" and z.jzname like '%"+zdname+"%'";
				Wstr=Wstr+" and z.jzname like '%"+zdname+"%'";
			}
			if(stationtype1 != null && !stationtype1.equals("") && !stationtype1.equals("0")){
				whereStr=whereStr+" and z.STATIONTYPE='"+stationtype1+"'";
				Wstr=Wstr+" and z.STATIONTYPE='"+stationtype1+"'";
			}
			if(jzproperty != null && !jzproperty.equals("") && !jzproperty.equals("0")){
				whereStr=whereStr+" and z.PROPERTY='"+jzproperty+"'";
				Wstr=Wstr+" and z.PROPERTY='"+jzproperty+"'";
			}
			if(lrsj != null && !lrsj.equals("")){
			    whereStr=whereStr+" AND G.SAVETIME LIKE '"+lrsj+"%'";
				Str=Str+" AND G.SAVETIME LIKE '"+lrsj+"%'";//录入时间  
				
			}
			if(zgjssj1 != null && !zgjssj1.equals("")){
			    whereStr=whereStr+" AND G.ZGMONTH='"+zgjssj1+"'";//
				Str=Str+" AND G.ZGMONTH='"+zgjssj1+"'";//暂估结束月份
				
			}
		
		
	  zanguservlet bean = new zanguservlet();
	  ArrayList<CityQueryBean> fylist =null;
	  
	  ArrayList<CityQueryBean> listq=new ArrayList<CityQueryBean>();   
	  String jzname="",address="",zgqssj="",zgjssj="",stationtype="",dfzflx1="",tianshu="",danjia="",zgje="",zdid="",lrren="",heji="0";
	 double count1=0;
 	 double danjia1=0.00;
	
		if("daochu".equals(request.getParameter("command"))||"chaxun".equals(request.getParameter("command"))){
    	 fylist = bean.getPageDataZgjt(whereStr,loginId,Str);
    	
    	 if(fylist!=null)
 		{
 			for(CityQueryBean beans:fylist){
 				
 				jzname=beans.getJzname();
 				address=beans.getAddress();
 				zgqssj=beans.getZangustartmonth();
 				
 				if(zgqssj.equals("")||zgqssj==null){
 				zgqssj="";
 				}else{
 				  zgqssj=zgqssj.substring(0,7);
 				}
 				
 				zgjssj=beans.getZanguendmonth();
 				if(zgjssj.equals("")||zgjssj==null){
 				zgjssj="";
 				}else{
 				  zgjssj=zgjssj.substring(0,7);
 				}
 				
 				stationtype=beans.getStationtype();
 				dfzflx1=beans.getDfzflx();
 				tianshu=beans.getTianshu();
 				danjia=beans.getDianfei();
 				zgje=beans.getZangumoney();
 				if(zgje==null||"".equals(zgje))zgje="0";
 				DecimalFormat mat1=new DecimalFormat("0.00");
 		 		zgje=mat1.format(Double.parseDouble(zgje));
 		 		
 				zdid=beans.getZdid();
 				lrren=beans.getLrren();
 				
 				count1=count1+Double.valueOf(zgje);
 		        heji=count1+"";
 	
	     if(intnum%2==0){
			    color="#FFFFFF" ;

			 }else{
			    color="#DDDDDD";
			}
           intnum++; 
    	 
 	  %>
 	   <tr bgcolor="<%=color%>">
       		<td>
       			<div align="center" ><%=countxh++%></div>
       		</td>      
            <td>
       			<div align="left" ><%=jzname%></div>
       		</td>
       		<td>
       			<div align="left" ><%=address%></div>
       		</td>
       		<td>
       			<div align="center" ><%=stationtype%></div>
       		</td>
       		<td>
       			<div align="center" ><%=dfzflx1%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zgqssj%></div>
       		</td>
       		<td>
       			<div align="center" ><%=zgjssj%></div>
       		</td>          
       		 <td>
       			<div align="right" ><%=tianshu%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=danjia%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=zgje%></div>
       		</td>
       		 <td>
       			<div align="right" ><%=lrren%></div>
       		</td>
       </tr>
       <%}%>
 		
 		<%}
 		DecimalFormat mat=new DecimalFormat("0.00");
 		heji=mat.format(Double.parseDouble(heji));
 		
 		%>
 		<tr><td colspan="9" align="center">合计暂估金额</td><td><%=heji %></td></tr>
 		<%}%>
 		
 		<!--
 		 <% if (intnum==0){
         for(int i=0;i<15;i++){
          if(i%2==0){
			    color="#FFFFFF" ;
          }else{
			    color="#DDDDDD";
			}
         %>

        <tr bgcolor="<%=color%>">
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
            <td>&nbsp;</td> 
            
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>                   
            <td>&nbsp;</td>
            <td>&nbsp;</td>
              
            
            </tr>
      <% }}else if(!(intnum > 14)){
    	  for(int i=((intnum-1)%14);i<14;i++){
            if(i%2==0)
			    color="#DDDDDD";
            else
			    color="#FFFFFF" ;
        %>
        <tr bgcolor="<%=color%>">
            <td>&nbsp;</td>  
             <td>&nbsp;</td> 
             <td>&nbsp;</td>  
             <td>&nbsp;</td> 
            <td>&nbsp;</td>
             
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
            <td>&nbsp;</td> 
            <td>&nbsp;</td>                   
            <td>&nbsp;</td>
            <td>&nbsp;</td> 
                           
        </tr>
        <% }}%>   
 	  
 --></table>
 </div>
<div style="width:100%;height:8%;border:1px" >	
	<table width="100%" height="20%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		 <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
	     <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div></div></td>
	  </tr>
	  <tr>  		
	     <td align="right">   			
		     <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px; " >
		     <img src="<%=path %>/images/daoru.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
		     </div>
		     
		     <div id="zhongxin" style="width:120px;height:23px;cursor:pointer;float:right;position:relative;right:15px; " >
		     <img src="<%=path %>/images/2chongzhi.png" width="105%" height="100%">
			 <span style="font-size:12px;position: absolute;left:35px;top:5px;color:white">按中心成本分摊</span>      
		     </div>
	    			
		     <div id="wuzhongxin" style="width:90px;height:23px;cursor:pointer;float:right;position:relative;right:26px; " >
		     <img src="<%=path %>/images/mmcz.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">按市分摊</span>      
		     </div>
		    
	     </td>
	  </tr> 
	  <tr>
	  <td>
	    <font color=red >
	            备注说明：此数据为暂估历史数据，每月3号自动保存上月的暂估数据。
	    </font>
	  </td>
	  
	  </tr>
	</table>
	<input type="hidden" name="lrren" value="<%=accountname %>">
</div>
</form>           
</body>
</html>
<script language="javascript">	
     var path = '<%=path%>';
    function changeShi(){
			var shi = document.form1.shi.value;
			if(shi=="0"){
				var shilist = document.all.xian;
				shilist.options.length="0";
				shilist.add(new Option("请选项","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=shi&pid="+shi,"shi");
			}
		}
		
		function updateQx(res){
			var shilist = document.all.xian;
			shilist.options.length="0";
			shilist.add(new Option("请选项","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
		function changeXian(){
			var shi = document.form1.xian.value;
			if(shi=="0"){
				var shilist = document.all.xiaoqu;
				shilist.options.length="0";
				shilist.add(new Option("请选择","0"));
				return;
			}else{
				sendRequest(path+"/servlet/area?action=xian&pid="+shi,"xian");
			}
		}
		
		function updateXQ(res){
			var shilist = document.all.xiaoqu;
			shilist.options.length="0";
			shilist.add(new Option("请选择","0"));
			
			for(var i = 0;i<res.length;i+=2){
				shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
			}
		}
 function exportad(){
	  if( document.getElementById("zgsj").value==""){
	       alert("暂估日期不能为空！请填写");
	}else{
         var endzgsj=document.getElementById('zgsj').value;
         var lrsj=document.getElementById('lrsj').value;
         var Wstr ="<%=Wstr%>";
     	 document.form1.action=path+"/web/query/basequery/暂估静态数据.jsp?endzgsj="+endzgsj+"&Wstr="+Wstr+"&lrsj="+lrsj+"&command=daochu";         
     	 document.form1.submit();
      }
   }
  function exportadzhongxin(){
	  if(document.getElementById("zgsj").value==""){
	       alert("暂估日期不能为空！请填写");
	  }else{
       	var endzgsj=document.getElementById('zgsj').value;
       	var lrsj=document.getElementById('lrsj').value;
        var Wstr ="<%=Wstr%>";
       //alert(whereStr+"Wstr:"+Wstr+"Str:"+Str+"endzgsj"+endzgsj);
     	window.open(path+"/web/newgn/zanguzhongxin.jsp?zgjssj="+endzgsj+"&Wstr="+Wstr+"&lrsj="+lrsj);         
     	// document.form1.submit();
      }
   }
    function exportadwu(){
	  if(document.getElementById("zgsj").value==""){
	       alert("暂估日期不能为空！请填写");
	  }else{
         var endzgsj=document.getElementById('zgsj').value;
          var lrsj=document.getElementById('lrsj').value;
         var Wstr ="<%=Wstr%>";
        //alert(whereStr+"whereStr"+"Str:"+Str+"endzgsj"+endzgsj);
        //document.form1.action=path+"/web/newgn/zanguwu.jsp?zgjssj="+endzgsj+"&Wstr="+Wstr+"&lrsj="+lrsj;
     	window.open(path+"/web/newgn/zanguwu.jsp?zgjssj="+endzgsj+"&Wstr="+Wstr+"&lrsj="+lrsj);         
     	// document.form1.submit();
      }
   }

		
		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xiaoqu.value='<%=xiaoqu%>';
		document.form1.stationtype.value='<%=stationtype1%>';
		document.form1.jzproperty.value='<%=jzproperty%>';
		
	
</script>
