<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.noki.util.CTime,com.noki.jizhan.JiZhanBean,com.noki.mobi.common.Account" %>
<%@ page import="java.text.*"%>
<%
String path = request.getContextPath();
int intnum=0;
String color="";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sheng = (String)session.getAttribute("accountSheng");
String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
String loginName = (String)session.getAttribute("loginName");
String yuefen=request.getParameter("yue")!=null?request.getParameter("yue"):"";
Account account = (Account)session.getAttribute("account");
String loginId = account.getAccountId();
String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";//分页
int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
      curpage=Integer.parseInt(s_curpage);
      String permissionRights="";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'stationfees.jsp' starting page</title>
<style type="text/css">
   .form_label{
	text-align: left;
	font-family: 宋体;
	font-size: 12px;
    }
</style>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script type="text/javascript">
var path = '<%=path%>';
	
	 function queryDegree(){	 
		 if(checkNotSelected(document.form1.shi,"市")&&checkNotnull(document.form1.yue,"月份")){
			  document.form1.action=path+"/web/cx/stationfees.jsp";
                   document.form1.submit();      
         }
      }
	  $(function(){
		$("#chaxun").click(function(){
			queryDegree();
		});
	});
	  function changeCity(){
	var sid = document.form1.shi.value;
	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;s
	}
}
	
	
		
</script>

  </head>
 <jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean"></jsp:useBean> 
  <body>
  <form action="" name="form1" method="POST">
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">					
	<tr>
			<span style="color: black; font-weight: bold;">&nbsp;&nbsp;&nbsp;地市基站用电量信息汇总表&nbsp;&nbsp;</span>
		</td>
		<td width='380'></td>
	</tr>
	<tr>
		<td colspan="4"> <div style="width:50px;display:inline;"><hr></div><font size='2'>过滤条件</font><div style="width:300px;display:inline;"><hr></div></td>
	</tr>
 	<tr class='form_label'><td>城市：<select name="shi" id="shi" style="width:130;height:10" onchange="changeCity()">
         		<option value="0">请选择</option>
         		<%
         			ArrayList shilist = new ArrayList();
         			shilist = commBean.getAgcode(sheng, shi, loginName);
         			if (shilist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) shilist.get(0)).intValue();
         				for (int i = scount; i < shilist.size() - 1; i += scount) {
         					agcode = (String) shilist.get(i + shilist.indexOf("AGCODE"));
         					agname = (String) shilist.get(i + shilist.indexOf("AGNAME"));
         		%>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    	}
                    %>
         		</select><font color="red">&nbsp;*</font>
   		          月份：<input type="text" style="width:130;height:18" id="yue" name="yue" readonly="readonly" 
   		          		class="Wdate" onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM'})"/><font color="red">&nbsp;*</font>
   	   </td>
   	   <td>
   	   		 <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;right:300px;float:right;">
		       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
		</div>
   	   </td>
   </tr>
   <tr class='form_label'>
		<td><div id="parent" style="display:inline"><div style="width:50px;display:inline;"><hr></div>&nbsp;信息列表&nbsp;<div style="width:300px;display:inline;"><hr></div></div>
		</td>
   </tr>
   </table>
   <table width="100%" border="0" cellspacing="1" cellpadding="1" class="form_label">
   	<tr bgcolor="#DDDDDD" style="color:black;font-weight:bold;" align="center">
   	    <td scope="2"></td>         
	
				   	    <td colspan="4">未应用节能技术的标杆基站</td> 
				   		<td colspan="4">应用节能技术的标杆基站</td> 	
				   		<td colspan="4">未应用节能技术的非标杆基站</td> 	
				   		<td colspan="4">应用节能技术的非标杆基站</td> 
		   		 
		   		   <tr bgcolor="#DDDDDD" style="color:black;font-weight:bold;" align="center">
		   		   <td scope="2" width="9%">类型</td>
				   		<td width="5%">基站数量（个）</td>
				   		<td width="5%">总用电量实际抄表数（度）</td>
				   		<td width="5%">平均用电量（度）</td>
				   		<td width="5%">实际电费金额（元）</td>
				   		<td width="5%">基站数量（个）</td>
				   		<td width="5%">总用电量实际抄表数（度）</td>
				   		<td width="5%">平均用电量（度）</td>
				   		<td width="5%">实际电费金额（元）</td>
				   		<td width="5%">基站数量（个）</td>
				   		<td width="5%">总用电量实际抄表数（度）</td>
				   		<td width="5%">平均用电量（度）</td>
				   		<td width="5%">实际电费金额（元）</td>
				   		<td width="5%">基站数量（个）</td>
				   		<td width="5%">总用电量实际抄表数（度）</td>
				   		<td width="5%">平均用电量（度）</td>
				   		<td width="5%">实际电费金额（元）</td>
				   	</tr>	   		
  	 	<%	
  	 	int m=0,jj=0,x=0,p=0;
  	 	Double n=0.00,f1=0.00,ii=0.00,f2=0.00,y=0.00,f3=0.00,q=0.00,f4=0.00;
  	 	String whereStr="",iii="",ff1="",ff2="",ff3="",ff4="",qq="",yy="",nn="";
  	 	if(shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr=whereStr+" and zd.shi='"+shi+"'";
			
		}
		if(yuefen != null && !yuefen.equals("") && !yuefen.equals("0")){
			whereStr=whereStr+" and to_char(am.endmonth,'yyyy-mm')='"+yuefen+"'";
			
		}
		System.out.println("whereStr"+whereStr);
		System.out.println("shishishis"+shi);
		if(shi!="0")
 		{
   	     JiZhanBean bean = new JiZhanBean();
       	 ArrayList fylist = new ArrayList();
         String xlxa="",xlx="",avge1="",avge2="",avge3="",avge4="",jzsl1="",zcbs1="",money1="",jzsl2="",zcbs2="",money2="",jzsl3="",zcbs3="",money3="",jzsl4="",zcbs4="",money4="";
         Double avg1=0.00,avg2=0.00,avg3=0.00,avg4=0.00;
         fylist = bean.getPageData2(whereStr,loginId,loginName);
       //int intnum=xh = pagesize*(curpage-1)+1;
       	
       
 			int fycount = ((Integer)fylist.get(0)).intValue();
 			for( int k = fycount;k<fylist.size()-1;k+=fycount){
        			//num为序号，不同页中序号是连续的
        			xlxa=(String) fylist.get(k + fylist.indexOf("CODE"));
        			xlx=(String) fylist.get(k + fylist.indexOf("NAME"));
        			jzsl1 = (String) fylist.get(k + fylist.indexOf("ID1"));
        			zcbs1 = (String) fylist.get(k + fylist.indexOf("DL1"));
        			//avg1 = (String) fylist.get(k + fylist.indexOf("DL1"));
        			money1 = (String) fylist.get(k+ fylist.indexOf("DF1"));
        			if(jzsl1==null||jzsl1.equals("")||jzsl1.equals("null"))jzsl1="0";
        			if(zcbs1==null||zcbs1.equals("")||zcbs1.equals("null"))zcbs1="0";
        			DecimalFormat pay1=new DecimalFormat("0.00");
                    zcbs1 = pay1.format(Double.parseDouble(zcbs1));
                    
                    //求平均数
        			Double zcbs=Double.parseDouble(zcbs1);
        			Double jzsl=Double.parseDouble(jzsl1);        			
        			if(jzsl!=0){
        				avg1=zcbs/jzsl;
        			}else{
        				avg1=0.00;
        			}
        			//求总抄表数
        			n=n+zcbs;
        			nn=pay1.format(n);
        			//求总电量数
        			m=m+Integer.parseInt(new java.text.DecimalFormat("0").format(jzsl));
        			//将平均数格式化
        			DecimalFormat pay2=new DecimalFormat("0.00");
                    avge1 = pay2.format(avg1);
                    
                        
                    if(money1==null||money1.equals("")||money1.equals("null"))money1="0";
                    DecimalFormat pay3=new DecimalFormat("0.00");
                    Double money=Double.parseDouble(money1);
                    money1 = pay3.format(money);
                    f1=f1+money;
                    ff1=pay3.format(f1);
                   
        			
        			
        			jzsl2 = (String) fylist.get(k + fylist.indexOf("ID2"));
        			zcbs2 = (String) fylist.get(k + fylist.indexOf("DL2"));
        			//avg2 = (String) fylist.get(k + fylist.indexOf("DL2"));
        			money2 = (String) fylist.get(k+ fylist.indexOf("DF2"));
        			if(jzsl2==null||jzsl2.equals("")||jzsl2.equals("null"))jzsl2="0";
       			    if(zcbs2==null||zcbs2.equals("")||zcbs2.equals("null"))zcbs2="0";
                    DecimalFormat pay4=new DecimalFormat("0.00");
                    zcbs2 = pay4.format(Double.parseDouble(zcbs2));
                    
                    
                    Double zcbs21=Double.parseDouble(zcbs2);
        			Double jzsl21=Double.parseDouble(jzsl2);
        			ii=ii+zcbs21;
        			iii=pay4.format(ii);
        			jj=jj+Integer.parseInt(new java.text.DecimalFormat("0").format(jzsl21));
        			if(jzsl21!=0){
        				avg2=zcbs21/jzsl21;
        			}else{
        				avg2=0.00;
        			}
        			
        			DecimalFormat pay5=new DecimalFormat("0.00");
                    avge2 = pay5.format(avg2);
                                                          
                    if(money2==null||money2.equals("")||money2.equals("null"))money2="0";
                    DecimalFormat pay6=new DecimalFormat("0.00");
                                       
                    Double mone=Double.parseDouble(money2);
                    money2 = pay6.format(mone);
                    f2=f2+mone;
                    ff2=pay6.format(f2);
                     
        			
        			jzsl3 = (String) fylist.get(k + fylist.indexOf("ID3"));
        			zcbs3 = (String) fylist.get(k + fylist.indexOf("DL3"));
        			//avg3 = (String) fylist.get(k + fylist.indexOf("DL3"));
        			money3 = (String) fylist.get(k+ fylist.indexOf("DF3"));
        			if(jzsl3==null||jzsl3.equals("")||jzsl3.equals("null"))jzsl3="0";
       			    if(zcbs3==null||zcbs3.equals("")||zcbs3.equals("null"))zcbs3="0";
                    DecimalFormat pay7=new DecimalFormat("0.00");
                    zcbs3 = pay7.format(Double.parseDouble(zcbs3));
                    
                    
                    Double zcbs31=Double.parseDouble(zcbs3);
        			Double jzsl31=Double.parseDouble(jzsl3);
        			x=x+Integer.parseInt(new java.text.DecimalFormat("0").format(jzsl31));
        			y=y+zcbs31;
        			yy=pay7.format(y);
        			if(jzsl31!=0){
        				avg3=zcbs31/jzsl31;
        			}else{
        				avg3=0.00;
        			}
                    
        			DecimalFormat pay8=new DecimalFormat("0.00");
                    avge3 = pay8.format(avg3);
                    
                    if(money3==null||money3.equals("")||money3.equals("null"))money3="0";
                    DecimalFormat pay9=new DecimalFormat("0.00");
                    Double mon1=Double.parseDouble(money3);
                    money3 = pay9.format(mon1);
                    f3=f3+mon1;
                    ff3=pay9.format(f3);
        			
        			
        			jzsl4 = (String) fylist.get(k + fylist.indexOf("ID4"));
        			zcbs4 = (String) fylist.get(k + fylist.indexOf("DL4"));
        			//avg4 = (String) fylist.get(k + fylist.indexOf("DL4"));
        			money4 = (String) fylist.get(k+ fylist.indexOf("DF4"));  
        			if(jzsl4==null||jzsl4.equals("")||jzsl4.equals("null"))jzsl4="0";
                    if(zcbs4==null||zcbs4.equals("")||zcbs4.equals("null"))zcbs4="0";
                    DecimalFormat pay10=new DecimalFormat("0.00");
                    zcbs4 = pay10.format(Double.parseDouble(zcbs4));
                    
                    Double zcbs41=Double.parseDouble(zcbs4);
        			Double jzsl41=Double.parseDouble(jzsl4);
        			p=p+Integer.parseInt(new java.text.DecimalFormat("0").format(jzsl41));
        			q=q+zcbs41;
        			qq=pay10.format(q);
        			
        			if(jzsl41!=0){
        				avg4=zcbs41/jzsl41;
        			}else{
        				avg4=0.00;
        			}
                    
        			DecimalFormat pay11=new DecimalFormat("0.00");
                    avge4 = pay11.format(avg4);
                    
                    if(money4==null||money4.equals("")||money4.equals("null"))money4="0";
                    DecimalFormat pay12=new DecimalFormat("0.00");
                    Double mon2=Double.parseDouble(money4);
                    money4 = pay12.format(mon2);
                    f4=f4+mon2;
                    ff4=pay12.format(f4);
                    
                    
        			
        			
        	

        			if (intnum % 2 == 0) {
        				color = "#DDDDDD";

        			} else {
        				color = "#FFFFFF";
        			}
        			  intnum++;
        %>  		
   	<tr bgcolor="<%=color%>">
   	    <td><div align="center" ><%=xlx%></div></td>	   
	   	<td><div align="right" ><a href="javascript:dfinfo5('<%=shi %>','<%=yuefen %>','<%=xlxa %>')"><%=jzsl1%></a></div></td>
       	<td><div align="right" ><%=zcbs1%></div></td>
   	    <td><div align="right" ><%=avge1%></div></td>
   	    <td><div align="right" ><a href="javascript:dfinfo1('<%=shi %>','<%=yuefen %>','<%=xlxa %>')"><%=money1%></a></div></td>
   	    <td><div align="right" ><a href="javascript:dfinfo6('<%=shi %>','<%=yuefen %>','<%=xlxa %>')"><%=jzsl2%></a></div></td>
   	    <td><div align="right" ><%=zcbs2%></div></td>
   	    <td><div align="right" ><%=avge2%></div></td>
   	    <td><div align="right" ><a href="javascript:dfinfo2('<%=shi %>','<%=yuefen %>','<%=xlxa %>')"><%=money2%></a></div></td>
   	    <td><div align="right" ><a href="javascript:dfinfo7('<%=shi %>','<%=yuefen %>','<%=xlxa %>')"><%=jzsl3%></a></div></td>
   	    <td><div align="right" ><%=zcbs3%></div></td>
   	    <td><div align="right" ><%=avge3%></div></td>
   	    <td><div align="right" ><a href="javascript:dfinfo3('<%=shi %>','<%=yuefen %>','<%=xlxa %>')"><%=money3%></a></div></td>
   	    <td><div align="right" ><a href="javascript:dfinfo8('<%=shi %>','<%=yuefen %>','<%=xlxa %>')"><%=jzsl4%></a></div></td>
   	    <td><div align="right" ><%=zcbs4%></div></td>
   	    <td><div align="right" ><%=avge4%></div></td>
   	    <td><div align="right" ><a href="javascript:dfinfo4('<%=shi %>','<%=yuefen %>','<%=xlxa %>')"><%=money4%></a></div></td>
   	</tr>
   
  
   	 <%
       	}
 		}
      
       if (intnum==0){
      	  System.out.println("QQQQ"+intnum);
           for(int i=0;i<15;i++){
            if(i%2==0){
  			    color="#DDDDDD";
            }else{
  			    color="#FFFFFF" ;
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
              <td>&nbsp;</td> 
              <td>&nbsp;</td>                   
              <td>&nbsp;</td>  
              <td>&nbsp;</td> 
              <td>&nbsp;</td> 
              <td>&nbsp;</td> 
                     
          </tr>   
          <% }}     
 		
       %>
       	<tr align="center"><td align="center">合计</td><td align="right"><%=m %></td><td align="right"><%=nn %></td><td>&nbsp;</td><td align="right"><%=ff1 %></td>
       												   <td align="right"><%=jj %></td><td align="right"><%=iii %></td><td>&nbsp;</td><td align="right"><%=ff2 %></td>
       												   <td align="right"><%=x %></td><td align="right"><%=yy %></td><td>&nbsp;</td><td align="right"><%=ff3 %></td>
       												   <td align="right"><%=p %></td><td align="right"><%=qq %></td><td>&nbsp;</td><td align="right"><%=ff4 %></td>
        </tr>
  </table>
	   	</td>		
   	</tr>
   </table>
    <!-- <iframe  name="treeMap1" width="45%" height="150px" frameborder="0" src="<%=path %>/web/cx/stationfees1.jsp"></iframe>  --> 	
   
   </form>
   
  </body>
</html>
<script type="text/javascript">
var path = '<%=path%>';
	/*function dfinfo1(shi,yuefen,xlxa){
    	var b=path+"/web/cx/stationfees1.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;			
		 var a = " <a href="+b+" target='treeMap1' id='tmpTree'></a>";
		$("#tmpTree").remove();
		$("body").append(a);
		$("#tmpTree")[0].click();
	}*/
	
function dfinfo1(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees1.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function dfinfo2(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees2.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function dfinfo3(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees3.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function dfinfo4(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees4.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function dfinfo5(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees5.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function dfinfo6(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees6.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function dfinfo7(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees7.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
	function dfinfo8(shi,yuefen,xlxa){
    var url=path+"/web/cx/stationfees8.jsp?shi="+shi+"&yuefen="+yuefen+"&xlxa="+xlxa;		
	var obj = new Object();
	obj.mid='mid';
    var obj=showModalDialog(url,obj,'dialogWidth:1200px;dialogHeight:500px;status:auto;scroll:auto');	
}	
document.form1.shi.value='<%=shi%>';
document.form1.yue.value='<%=yuefen%>';

</script>
