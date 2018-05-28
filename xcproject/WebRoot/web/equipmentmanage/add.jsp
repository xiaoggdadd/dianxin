<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@page import="com.noki.equipmentmanage.EquipmentmanageViewBean"%>
<%@page import="com.noki.equipmentmanage.EquipmentmanageBean"%>
<%@page import="java.text.*" %>
<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
    String loginName = (String)session.getAttribute("loginName");
    
    Account account = (Account)session.getAttribute("account");
	String accountname=account.getAccountName();
	//System.out.println("登陆名："+accountname);
    String dianBiaoId = request.getParameter("dianBiaoId");
    int intnum=0;//记录条数
    //int jcdid=0;//监测点Id
    double countBili=0;
    double yyBili=0;
    double bgBili=0;
    double xxhzcBili=0;
    double jstzBili=0;
    double dddfBili=0;
    String canshuStr="dianBiaoId="+dianBiaoId+"";
     String canshuStr1="?dianBiaoId="+dianBiaoId;
    String area = request.getParameter("area");
     String yflx = request.getParameter("yflx");
  //   System.out.println("所在地区"+area+"用房"+yflx);
    String color=null;
    String bz=request.getParameter("bz");//标准位   表示 是点击“查询” 还是从别的页面带回来的数据
    
    	String sc=request.getParameter("sc");
    	String yy=request.getParameter("yy");
    	String bg=request.getParameter("bg");
    	String xxhzc=request.getParameter("xxhzc");
    	String jstz=request.getParameter("jstz");
    	String dddf=request.getParameter("dddf");
   
        String de=request.getParameter("de");
        System.out.println("de:"+de);
    
    
    
    
%>
<html>
<head>
<title>
电表监测点信息
</title>
<style>
          
            .style1 {
	color: #FF0000;
	font-weight: bold;
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 #id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
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
}
.bttcn{color:black;font-weight:bold;}
 </style>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
      <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
  <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
<script language="javascript">
var path='<%=path%>';
     //得到根路径
     function getPublicPath(){
		   var path = document.location.pathname;
			var arraypath=path.split("/");
			var mapath="/";
		 	if(arraypath[1]==undefined){
			 arraypath[1]="";
			 mapath="";
		 	}
		  path=mapath+arraypath[1];
		  return path;
	} 
   function waitThenDoIt(){
       var dbid='<%=request.getParameter("dianBiaoId")%>';
       
       if(dbid!="null"&&dbid!=null){
	       getItemSelectedDate(dbid);
	       
       }
	}
    function liulan(){
       //window.open("dianbiaolist.jsp",'_blank','newwindow','height=350, width=630,top=1200,left=300,toolbar=no,menubar=no, scrollbars=yes, resizable=no,location=n o, status=no');
      window.open('dianbiaolist.jsp','','width=1000,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
  
    }
    function getItemSelectedDate(dianBiaoId){
     var bz='<%=bz%>';
     
  // var aa= document.getElementById("dianbiaoid").value;
      document.getElementById("dianbiaoid").value='<%=request.getParameter("dianBiaoId")%>';
        var Url=getPublicPath()+"/servlet/EquipmentmanageServlet?action=selzhan&dianBiaoId="+dianBiaoId;
      	Url = encodeURI(Url);
		new Ajax.Request(Url,{
     	method : 'post',
		asynchronous: false,
		onComplete : function (resp){//回调
			  var result=resp.responseText;//返回数据
			  var rowDate = result.split("|");

			  if(bz=="2"){
			 
			  document.getElementById("dddf").value=rowDate[12];
			  document.getElementById("jstz").value=rowDate[11];
			  document.getElementById("xxhzc").value=rowDate[10];
			  document.getElementById("bg").value=rowDate[9];
			  document.getElementById("yy").value=rowDate[8];
			  document.getElementById("sc").value=rowDate[7];
			  
			  }
			   document.getElementById("area").value=rowDate[6];
               document.getElementById("stationname").value=rowDate[5];	
			   //document.getElementById("jnglmk").value=rowDate[4];
               //document.getElementById("gdfs").value=rowDate[3];	
               //document.getElementById("isbenchmarkstation").value=rowDate[2];
               //document.getElementById("yflx").value=rowDate[1];
               //document.getElementById("stationtypeid").value=rowDate[0];
			   	}
			
	})  
	 if(bz=="1"){
		document.form1.sc.value='<%=sc%>';
		document.form1.yy.value='<%=yy%>';
		document.form1.bg.value='<%=bg%>';
		document.form1.xxhzc.value='<%=xxhzc%>';
		document.form1.jstz.value='<%=jstz%>';
		document.form1.dddf.value='<%=dddf%>';
	  }
    }
$(function(){

	$("#liulan").click(function(){
		liulan();
	});
	$("#xinzengBtn").click(function(){
		 Addjcd();
	});
	$("#cancelBtn").click(function(){
		document.form1.action=path+"/web/equipmentmanage/equipmentmanage.jsp";
		document.form1.submit()	});
	$("#saveBtn").click(function(){
		Savejcd()
	});

});
</script>
</head>

<body  class="body" style="overflow-x:hidden;"onload="waitThenDoIt()">
	<form name="form1">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		 <tr >
			     
					    	<td colspan="4" width="50" >
											 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电表监测点信息</span>	
												</div>
											</td>

	    	        </tr>	    	
		<tr bgcolor="#F9F9F9">
                                  <td height="19" colspan="4">&nbsp;&nbsp;<img src="../../images/v.gif" width="15" height="15"/><font size="2"> &nbsp;站点信息</font></td>
                           
	    	</tr>
			<tr>
				<td colspan="4">
					       	<table width="100%" border="0" cellspacing="1" cellpadding="1">
                             <tr class="form_label">
                            <td height="8%" bgcolor="#DDDDDD" ><div align="left">电表ID：</div></td>  
						       <td  width="25%"><input type="text"  name="dianbiaoid" id="dianbiaoid" class="selected_font">
						        
						       <span class="style1">&nbsp;*</span>
							
					<img id="liulan" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png" style="cursor: pointer"/>

					           </td>
							   <td  height="8%" bgcolor="#DDDDDD" width="15%" ><div>站点名称：</div></td>
				               <td><input type="text" name="stationname"  id="stationname" class="selected_font" style="width:130" /></td>
				                <td  height="8%" bgcolor="#DDDDDD" width="15%" ><div>地区：</div>
						         </td>
						         <td><input type="text" name="area" id="area" class="selected_font" style="130" />
						         </td>
						        <!-- <td height="8%" bgcolor="#DDDDDD" width="15%"><div>集团报表类型：</div></td>
					             <td><input type="text" id="stationtypeid" name="stationtypeid" class="selected_font" style="width:130;"/></td>  -->
				           </tr>
						      <!-- <tr class="form_label">
						      <td  height="8%" bgcolor="#DDDDDD" width="15%" ><div>是否标杆：</div>
						         </td>
						         <td><input type="text" name="isbenchmarkstation"  id="isbenchmarkstation" class="selected_font" style="width:130;"/>
						         </td>
						         
						      <td  height="8%" bgcolor="#DDDDDD" width="15%"><div>房屋类型：</div>
						         </td>
						         <td><input type="text" name="yflx" id="yflx" class="selected_font" style="width:130;"/>
						         </td>
						          <td  height="8%" bgcolor="#DDDDDD" width="15%" ><div>节能管理模块：</div>
						         </td>
						         <td><input type="text" name="jnglmk"  id="jnglmk" class="selected_font"  />
						         </td>
						         
						      <td  height="8%" bgcolor="#DDDDDD" width="15%"><div>供电方式：</div>
						         </td>
						         <td><input type="text" name="gdfs" id="gdfs" class="selected_font" style="width:130;"/>
						         </td>
						      </tr>  -->
						      <tr bgcolor="#F9F9F9">
                                  <td height="19" colspan="4">&nbsp;&nbsp;<img src="../../images/v.gif" width="15" height="15"/><font size="2"> &nbsp;分摊信息</font></td>
	    				      </tr>
						      <tr class="form_label">
						         <td  height="8%" bgcolor="#DDDDDD" width="15%"><div>生产：</div></td>
						         <td><input type="text" name="sc" id="sc" class="selected_font" style="width:130;" value="<%if(null!=request.getParameter("sc")) out.print(request.getParameter("sc")); %>"/></td>
						         <td  height="8%" bgcolor="#DDDDDD" width="15%"><div>营业：</div></td>
						         <td><input type="text" name="yy" id="yy" class="selected_font" style="width:130;"/></td>
						         <td  height="8%" bgcolor="#DDDDDD" width="15%"><div>办公：</div></td>
						         <td><input type="text" name="bg" id="bg" class="selected_font" style="width:130;"/></td>
						         <td  height="8%" bgcolor="#DDDDDD" width="15%"><div>信息化支撑：</div></td>
						         <td><input type="text" name="xxhzc" id="xxhzc" class="selected_font" style="width:130;"/></td>
						       
						      </tr>
						       <tr class="form_label">
						         <td  height="8%" bgcolor="#DDDDDD" width="15%"><div>建设投资：</div></td>
						         <td><input type="text" name="jstz" id="jstz" class="selected_font" style="width:130;"/></td>
						         <td  height="8%" bgcolor="#DDDDDD" width="15%"><div>代垫电费：</div></td>
						         <td><input type="text" name="dddf" id="dddf" class="selected_font" style="width:130;"/></td>
						       </tr>
		                   </table>
</td>
</tr>
</table>
		                     <div style=background-color:#F9F9F9 >&nbsp;&nbsp;<img src="../../images/v.gif" width="15" height="15"/><font size="2"> &nbsp;监测点列表</font>
                             </div> 
	                   <table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">

					           <tr bgcolor="#888888">
					             <td width="3%" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
					             
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">名称</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">规格</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属专业</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所属网元</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">会计科目</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">全成本科目</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">专业明细</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">分摊占比</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">生产厂家</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">资产编码</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">标称耗电</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">备注</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">监测点ID</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">修改</div></td>
					             <td  height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">删除</div></td>
					         </tr>
					      <%	String whereStr="";
					            ArrayList fylist=null;
					            EquipmentmanageBean bean=new EquipmentmanageBean(); 
					           
						    if(dianBiaoId != null && !dianBiaoId.equals("")&&!dianBiaoId.equals("null")){
								whereStr=whereStr+" and d.dbid = '"+dianBiaoId+"'";
						       	fylist = bean.getPageData1(whereStr,account.getAccountId());
					       	 
					        String str="";
							String jzname = "",dianbiaoid = "",sheiebanid = "", mingcheng= "",guige = "",shuoshuzhuanye="",shuoshuwangyuan="",kjkmcode="",qcbcode="",zymxcode="",bili="",sccj="",zcbh="",bccd="",beizhu="";
							String sszy="",kjkm="",qcb="",zymx="";
							
							ArrayList<EquipmentmanageViewBean> list=new ArrayList<EquipmentmanageViewBean>();
							
						  
						    EquipmentmanageViewBean viewbean=null;
							if(fylist!=null&&"1".equals(de)){// 当点击查看和放大镜进入页面时查询数据库里面的数据
								int fycount = ((Integer)fylist.get(0)).intValue();
								for( int k = fycount;k<fylist.size()-1;k+=fycount){
					            viewbean =new EquipmentmanageViewBean();
							     //num为序号，不同页中序号是连续的
								jzname = (String)fylist.get(k+fylist.indexOf("JZNAME"));
							   	dianbiaoid = (String)fylist.get(k+fylist.indexOf("DIANBIAOID"));
							    viewbean.setDianbiaoid(dianbiaoid);
							   sheiebanid = (String)fylist.get(k+fylist.indexOf("XJID"));
							    
							    viewbean.setSheiebanid(sheiebanid);
							    mingcheng = (String)fylist.get(k+fylist.indexOf("MINGCHENG"));
							    viewbean.setMingcheng(mingcheng);
								guige = (String)fylist.get(k+fylist.indexOf("GUIGE"));
								viewbean.setGuige(guige);
								shuoshuzhuanye = (String)fylist.get(k+fylist.indexOf("SHUOSHUZHUANYE"));
								viewbean.setShuoshuzhuanye(shuoshuzhuanye);
								sszy=bean.SelPermissionCconfiguration("SSZY",shuoshuzhuanye);
								viewbean.setSszy(sszy);
								shuoshuwangyuan = (String)fylist.get(k+fylist.indexOf("SHUOSHUWANGYUAN"));
								viewbean.setShuoshuwangyuan(shuoshuwangyuan);
								kjkmcode = (String)fylist.get(k+fylist.indexOf("KJKMCODE"));
								viewbean.setKjkmcode(kjkmcode);
								kjkm=bean.getKJKM1(kjkmcode);
								viewbean.setKjkm(kjkm);
								qcbcode = (String)fylist.get(k+fylist.indexOf("QCBCODE"));
								//System.out.println("全成本code："+qcbcode);
								viewbean.setQcbcode(qcbcode);
								qcb=bean.getQCB12(qcbcode);
								viewbean.setQcb(qcb);
								zymxcode = (String)fylist.get(k+fylist.indexOf("ZYMXCODE"));
								viewbean.setZymxcode(zymxcode);
								zymx=bean.SelPermissionCconfiguration("ZYMX",zymxcode);
								viewbean.setZymx(zymx);
								bili = (String)fylist.get(k+fylist.indexOf("XJBILI"));
								viewbean.setBili(bili);
								sccj = (String)fylist.get(k+fylist.indexOf("SCCJ"));
								viewbean.setSccj(sccj);
								zcbh = (String)fylist.get(k+fylist.indexOf("ZCBH"));
								viewbean.setZcbh(zcbh);
								bccd = (String)fylist.get(k+fylist.indexOf("BCCD"));
								viewbean.setBccd(bccd);
								beizhu = (String)fylist.get(k+fylist.indexOf("BEIZHU"));
								viewbean.setBeizhu(beizhu);
								//System.out.println("vvvvvv>>"+jzname+viewbean);
								/*boolean flag=false;
							    for(EquipmentmanageViewBean view:list){//如果数据库里的id与bean里的id相同  遍历bean里面的数据 否则遍历数据库里面的
								    if(view.sheiebanid.equals(viewbean.sheiebanid)){
								       flag=true;
								    }
								 }*/
								// if(!flag){
								 list.add(viewbean);//放到list里面
								 //}
								
							    }
						     }else{ //当点击新增修改删除时 进入页面读取bean里面的数据   遍历bean
						     if(null!=session.getAttribute(dianBiaoId)){
							
							  list=(ArrayList<EquipmentmanageViewBean>)session.getAttribute(dianBiaoId);//如果session不为空 获取里面的值
							}
						  }
						     if(list.size()>0){
						     
						      request.getSession().removeAttribute(dianbiaoid);//移除上一次session里面的值
					          request.getSession().setAttribute(dianbiaoid,list);//从新将list里面的bean数据添加到session（ dianbiaoid）里面
					          //System.out.println("list长度："+list.size());
						  for(EquipmentmanageViewBean view:list){
							 // String v = view.mingcheng.toString().replace("#","%23");
							  	//	v = view.mingcheng.toString().replace("&","%26");
							  	String e = view.mingcheng;
							  	String i ="#";
							  	String i1="&";
							  	String i2="+";
							  	String i3="%";
							  	int  u = e.indexOf(i);
							  	int  u1 = e.indexOf(i1);
							  	int  u2 = e.indexOf(i2);
							  	int  u3 = e.indexOf(i3);
							  	
							  	System.out.println("uuuuuuuu:"+u+"   名称："+e);
							  	String a="";
							  	if(u!=-1){
							  		 a = e.replace("#","%23");
							  		str="&sheiebanid="+view.sheiebanid+"&shuoshuwangyuan="+view.shuoshuwangyuan+"&mingcheng="+a+"&guige="+view.guige+"&kjkmcode="+view.kjkmcode+"&kjkm="+view.kjkm+"&dianbiaoid="+view.dianbiaoid+"&shuoshuzhuanye="+view.shuoshuzhuanye+"&sszy="+view.sszy+"&qcbcode="+view.qcbcode+"&qcb="+view.qcb+"&zymxcode="+view.zymxcode+"&zymx="+view.zymx+"&bili="+view.bili+"&sccj="+view.sccj+"&zcbh="+view.zcbh+"&bccd="+view.bccd+"&beizhu="+view.beizhu;
							  	}else if(u1!=-1){
							  		a = e.replace("&","%26");
							  		str="&sheiebanid="+view.sheiebanid+"&shuoshuwangyuan="+view.shuoshuwangyuan+"&mingcheng="+a+"&guige="+view.guige+"&kjkmcode="+view.kjkmcode+"&kjkm="+view.kjkm+"&dianbiaoid="+view.dianbiaoid+"&shuoshuzhuanye="+view.shuoshuzhuanye+"&sszy="+view.sszy+"&qcbcode="+view.qcbcode+"&qcb="+view.qcb+"&zymxcode="+view.zymxcode+"&zymx="+view.zymx+"&bili="+view.bili+"&sccj="+view.sccj+"&zcbh="+view.zcbh+"&bccd="+view.bccd+"&beizhu="+view.beizhu;
							  	}else if(u2!=-1){
							  		a = e.replace("+","%2B");
							  		str="&sheiebanid="+view.sheiebanid+"&shuoshuwangyuan="+view.shuoshuwangyuan+"&mingcheng="+a+"&guige="+view.guige+"&kjkmcode="+view.kjkmcode+"&kjkm="+view.kjkm+"&dianbiaoid="+view.dianbiaoid+"&shuoshuzhuanye="+view.shuoshuzhuanye+"&sszy="+view.sszy+"&qcbcode="+view.qcbcode+"&qcb="+view.qcb+"&zymxcode="+view.zymxcode+"&zymx="+view.zymx+"&bili="+view.bili+"&sccj="+view.sccj+"&zcbh="+view.zcbh+"&bccd="+view.bccd+"&beizhu="+view.beizhu;
							  	}else if(u3!=-1){
							  		a = e.replace("%","%25");
							  		str="&sheiebanid="+view.sheiebanid+"&shuoshuwangyuan="+view.shuoshuwangyuan+"&mingcheng="+a+"&guige="+view.guige+"&kjkmcode="+view.kjkmcode+"&kjkm="+view.kjkm+"&dianbiaoid="+view.dianbiaoid+"&shuoshuzhuanye="+view.shuoshuzhuanye+"&sszy="+view.sszy+"&qcbcode="+view.qcbcode+"&qcb="+view.qcb+"&zymxcode="+view.zymxcode+"&zymx="+view.zymx+"&bili="+view.bili+"&sccj="+view.sccj+"&zcbh="+view.zcbh+"&bccd="+view.bccd+"&beizhu="+view.beizhu;
							  	}
							  	else{
							  
							  	//System.out.println("view.mingcheng:"+view.mingcheng);
					            str="&sheiebanid="+view.sheiebanid+"&shuoshuwangyuan="+view.shuoshuwangyuan+"&mingcheng="+e+"&guige="+view.guige+"&kjkmcode="+view.kjkmcode+"&kjkm="+view.kjkm+"&dianbiaoid="+view.dianbiaoid+"&shuoshuzhuanye="+view.shuoshuzhuanye+"&sszy="+view.sszy+"&qcbcode="+view.qcbcode+"&qcb="+view.qcb+"&zymxcode="+view.zymxcode+"&zymx="+view.zymx+"&bili="+view.bili+"&sccj="+view.sccj+"&zcbh="+view.zcbh+"&bccd="+view.bccd+"&beizhu="+view.beizhu;
							  	}
								if(intnum%2==0){
								    color="#FFFFFF";
					
								 }else{
								    color="#DDDDDD" ;
								}
								//System.out.println("222:"+view.bili);	
								if("".equals(view.bili)||view.bili==null){
								   view.bili="0";
								}
								
								
								//判断二级分摊占比 满不满足条件
								if("zylx01".equals(view.shuoshuzhuanye)){
                                  countBili+=Double.parseDouble(view.bili);
								}else if("zylx02".equals(view.shuoshuzhuanye)){
								 yyBili+=Double.parseDouble(view.bili);
								}else if("zylx03".equals(view.shuoshuzhuanye)){
								 bgBili+=Double.parseDouble(view.bili);
								}else if("zylx04".equals(view.shuoshuzhuanye)){
								 xxhzcBili+=Double.parseDouble(view.bili);
								}else if("zylx05".equals(view.shuoshuzhuanye)){
								 jstzBili+=Double.parseDouble(view.bili);
								}else if("zylx06".equals(view.shuoshuzhuanye)){
								 dddfBili+=Double.parseDouble(view.bili);
								}
								
								//格式化
							DecimalFormat la=new DecimalFormat("0.00");
                			countBili =Double.parseDouble(la.format(countBili))	;
                			yyBili =Double.parseDouble(la.format(yyBili))	;
                			bgBili =Double.parseDouble(la.format(bgBili))	;
                			xxhzcBili =Double.parseDouble(la.format(xxhzcBili))	;
                			jstzBili =Double.parseDouble(la.format(jstzBili))	;
                			dddfBili =Double.parseDouble(la.format(dddfBili))	;
                			view.bili=la.format(Double.parseDouble(view.bili));	
                			System.out.println("值"+str);
					       %>
					       <tr bgcolor="<%=color%>">
					       		<td>
					       			<div align="center" ><%=++intnum%></div>
					       		</td>
					       	    
					       		<td>
					       			<div align="left"  ><%=view.mingcheng%></div>
					       		</td>
					       		<td>
					       			<div align="center"  ><%=view.guige%></div>
					       		</td>
					       		<td>
					       			<div align="center"  ><%=view.sszy%></div>
					       		</td>
					       		<td>
					       			<div align="center"  ><%=view.shuoshuwangyuan%></div>
					       		</td>
					       		<td>
					       			<div align="center"  ><%=view.kjkm%></div>
					       		</td>
					       		<td>
					       			<div align="center"  ><%=view.qcb%></div>
					       		</td>
					       		<td>
					       			<div align="center"  ><%=view.zymx%></div>
					       		</td>
					       		<td>
					       			<div align="right"  ><%=view.bili+"%"%></div>
					       		</td>
					       		
					       		<td>
					       			<div align="center"  ><%=view.sccj%></div>
					       		</td>
					       		<td>
					       			<div align="center"  ><%=view.zcbh%></div>
					       		</td>
					       		<td>
					       			<div align="center"  ><%=view.bccd%></div>
					       		</td>
					       		 
					       		<td>
					       			<div align="center"  ><%=view.beizhu%></div>
					       		</td>
					       		<td>
					       			<div align="left"  ><%=view.sheiebanid%></div>
					       		</td>
					       		<td>
					       			<div align="center" ><a href="#"  onclick="xiugai('<%=str%>')">修改</a></div>   
					       		</td>
					       	
					       		
					       		<td>
					       			<div align="center" ><a href="#" onclick="del('<%=view.sheiebanid%>')">删除</a></div>
					       		</td>
					       	
					       </tr>
					     
					       <%}}}%>
					       
					       
					        <% if (intnum==0){
    	  //System.out.println("QQQQ"+intnum);
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
            <td>&nbsp;</td>
            
               
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td> 
            <td>&nbsp;</td>  
            </tr>
      <% }}else if(!(intnum > 15)){
    	  for(int i=((intnum-1)%15);i<15;i++){
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
            <td>&nbsp;</td>
              
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td> 
            <td>&nbsp;</td>  
                         
        </tr>
        <% }}%>
					         </table>
              <table width="100%" border="0" cellspacing="1" cellpadding="1">
                <tr>
                <td align="right">
					  <div id="parent" style="display:inline" align="right">
                          <div style="width:300px;display:inline;" align="right"><hr></div>
                      </div>
			    </td>
											
				</tr>
	            <tr bgcolor="#FFFFFF">
	                <td>
				<div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
					<img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
					<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>      
				</div>
				<div id="xinzengBtn" style="position:relative;width:60px;height:23px;cursor: pointer;right:15px;float:right;">
					<img alt="" src="<%=path %>/images/xinzeng.png" width="100%" height="100%" />
					<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">新增</span>
				</div>
				<div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:26px">
					 <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
					 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>      
				</div>
					</td>
				  </tr>
              </table>
              <input type="hidden" name="accountname" value="<%=accountname%>"/>
              <input type="hidden" name="de" id="de" value="<%=de%>"/>
       
 </form>
</body>
</html>
<script language="javascript">
	var path = '<%=path%>';
		function getkjkm(){
			var divWidth = 800;
			var bodyWidth = $("body").innerWidth();
			var leftSize = 0;//div距离左侧的位置
			if(bodyWidth>divWidth)leftSize = (bodyWidth-divWidth)/2;
			var divStr = "<div id='kjkmDiv' name='kjkm' style='position:absolute;width:"+divWidth+"px;height:400px;border:5px red groove;left:"+leftSize+"px;top:100px;background-color:green'>";
			divStr += "<iframe src='<%=path%>/web/equipmentmanage/kjkm.jsp' name='' id='' width='"+(divWidth-5)+"px' height=100%></iframe>";
			divStr += "</div>";
			$("body").append(divStr);
		}
		function getqcb(){
			var divWidth = 800;
			var bodyWidth = $("body").innerWidth();
			var leftSize = 0;//div距离左侧的位置
			if(bodyWidth>divWidth)leftSize = (bodyWidth-divWidth)/2;
			var divStr = "<div id='qcbDiv' name='qcb' style='position:absolute;width:"+divWidth+"px;height:400px;border:5px red groove;left:"+leftSize+"px;top:100px;background-color:green'>";
			divStr += "<iframe src='<%=path%>/web/equipmentmanage/qcb.jsp' name='' id='' width='"+(divWidth-5)+"px' height=100%></iframe>";
			divStr += "</div>";
			$("body").append(divStr);
		}
function del(id){
	 var sc=document.form1.sc.value;
       var yy=document.form1.yy.value;
       var bg=document.form1.bg.value;
       var xxhzc=document.form1.xxhzc.value;
       var jstz=document.form1.jstz.value;
        var dddf=document.form1.dddf.value;
     var del="0";
     var bz="1";//所属专业的值从数据库取的还是页面上获取的 一个标志位
    
     if(confirm("确定删除监测点"+id)){
       var dbid='<%=request.getParameter("dianBiaoId")%>';
       var params = "&dianbiaoid="+dbid+"&sheiebanid="+id;
       //alert(params);
       var Url="<%=request.getContextPath()%>/servlet/EquipmentmanageServlet?action=delSession";
	    new Ajax.Request(Url,{
	      		method : 'post',
				asynchronous: false,
			    parameters : params,
				onComplete : function (resp){//回调
	 			  var result=resp.responseText;//返回数据
	 		// alert(result+"*");
	 			  var msg = result.split("|");
					if ("msg"==msg[0]){
					  alert(msg[1]);
					  return;
					}
					
					//window.location.reload();
					window.location.href=path+"/web/equipmentmanage/add.jsp?de="+del+"&sc="+sc+"&yy="+yy+"&bg="+bg+"&xxhzc="+xxhzc+"&jstz="+jstz+"&dddf="+dddf+"&bz="+bz+"&dianBiaoId="+dbid;
					
	 			}
	 	});
     }
   }
    function Addjcd(){
       var sc=document.form1.sc.value;
       var yy=document.form1.yy.value;
       var bg=document.form1.bg.value;
       var xxhzc=document.form1.xxhzc.value;
       var jstz=document.form1.jstz.value;
       var dddf=document.form1.dddf.value;
       var jzname=document.form1.stationname.value;//stationname
       
       var dbid='<%=request.getParameter("dianBiaoId")%>';
       if(dbid!="null"&&dbid!=null){
                 var count='<%=countBili%>';
				 //if(count<100){
				    window.location.href=path+"/web/equipmentmanage/addjcd.jsp?sc="+sc+"&yy="+yy+"&bg="+bg+"&xxhzc="+xxhzc+"&jstz="+jstz+"&dddf="+dddf+"&<%=canshuStr%>";
				/*  }
				  else if(count>100){
				   alert("该电表的所属电表占比超过100%,如需添加请修改原有占比");
				  }else{
				   alert("该电表的所属电表占比已为100%,如需添加请修改原有占比");
				  }*/
       }
       else alert("请选择一个电表");
   
 }
 function xiugai(str){
    var sc=document.form1.sc.value;
    var yy=document.form1.yy.value;
    var bg=document.form1.bg.value;
    var xxhzc=document.form1.xxhzc.value;
    var jstz=document.form1.jstz.value;
     var dddf=document.form1.dddf.value;
     
    window.location.href=path+"/web/equipmentmanage/modify.jsp<%=canshuStr1%>&sc="+sc+"&yy="+yy+"&bg="+bg+"&xxhzc="+xxhzc+"&dddf="+dddf+"&jstz="+jstz+""+str+"";
 
 }
 function Savejcd(){
       var sc=document.form1.sc.value;
       var yy=document.form1.yy.value;
       var bg=document.form1.bg.value;
       var xxhzc=document.form1.xxhzc.value;
       var jstz=document.form1.jstz.value;//
       var dddf=document.form1.dddf.value;//
       var accountname=document.form1.accountname.value;//
       var dbid='<%=request.getParameter("dianBiaoId")%>';
       var he=parseFloat(parseFloat(sc)+parseFloat(yy)+parseFloat(bg)+parseFloat(xxhzc)+parseFloat(jstz)+parseFloat(dddf)).toFixed(2);
     alert(he);
       if(dbid!="null"&&dbid!=null){
       if(he==100||he==100.00){
       if(isNaN(document.form1.sc.value)==false&&
       	  isNaN(document.form1.yy.value)==false&&
       	  isNaN(document.form1.xxhzc.value)==false&&
          isNaN(document.form1.bg.value)==false&&
          isNaN(document.form1.jstz.value)==false&&
          isNaN(document.form1.dddf.value)==false){
          if(jstz==100||jstz==0){
                 var count='<%=countBili%>';//一级分摊比例  与二级分摊比例 对比
                 var yybili='<%=yyBili%>';
                 var bgbili='<%=bgBili%>';
                 var xxhzcbili='<%=xxhzcBili%>';
                 var jstzbili='<%=jstzBili%>';
                 var dddfbili='<%=dddfBili%>';
                 //if(sc==0&&count>0){}
                 if(sc>0&&count!=100){
					alert("该电表的所属生产占比不足100%，或所属电表占比大于100%,无法保存,请修改!");
				 }else if(sc==0&&count>0){
				 	alert("生产占比为0,不能添加下级生产分摊，请修改!");
				 }else if(yy>0&&yybili!=100){
				 	alert("该电表的所属营业占比不足100%，或所属电表占比大于100%,无法保存,请修改!");
				 }else if(yy==0&&yybili>0){
				 	alert("营业占比为0,不能添加下级生产分摊，请修改!");
				 }else if(bg>0&&bgbili!=100){
				 	alert("该电表的所属办公占比不足100%，或所属电表占比大于100%,无法保存,请修改!");
				 }else if(bg==0&&bgbili>0){
				 	alert("办公占比为0,不能添加下级生产分摊，请修改!");
				 }else if(xxhzc>0&&xxhzcbili!=100){
				 	alert("该电表的所属信息化支撑占比不足100%，或所属电表占比大于100%,无法保存,请修改!");
				 }else if(xxhzc==0&&xxhzcbili>0){
				 	alert("信息化支撑占比为0,不能添加下级生产分摊，请修改!");
				 }else if(jstz>0&&jstzbili!=100){
				 	alert("该电表的所属建设投资占比不足100%，或所属电表占比大于100%,无法保存,请修改!");
				 }else if(jstz==0&&jstzbili>0){
				 	alert("建设投资占比为0,不能添加下级生产分摊，请修改!");
				 }else if(dddf>0&&dddfbili!=100){
				 	alert("该电表的所属代垫电费占比不足100%，或所属电表占比大于100%,无法保存,请修改!");
				 }else if(dddf==0&&dddfbili>0){
				 	alert("代垫电费占比为0,不能添加下级生产分摊，请修改!");
				 }else{
				 
				       var params = "?&dianBiaoId="+dbid+"&sc="+sc+"&yy="+yy+"&bg="+bg+"&xxhzc="+xxhzc+"&jstz="+jstz+"&dddf="+dddf+"&accountname="+accountname+"";
				       var Url="<%=request.getContextPath()%>/servlet/EquipmentmanageServlet?action=save&";
			           new Ajax.Request(Url,{
			      		method : 'post',
						asynchronous: false,
					    parameters : params,
						onComplete : function (resp){//回调
			 			  var result=resp.responseText;//返回数据
			 			  var msg = result.split("|");
							if ("msg"==msg[0]){
							  alert(msg[1]);
							  return;
							}
							alert(dbid+"监测点保存成功");
							window.location.reload();
			 			}
			 	       });
				
				  }
				 }else{
				  alert("建设投资只能有两种情况100和0");
				 } 
				}else {
				alert("分摊信息必须为数字，请修改为数字");
				}  //
			}else{
				alert("分摊信息总和不等于100，或大于小于100，请修改");
			}
       }
       else alert("请选择一个电表");
     
 }
 function GoBack(){
      document.form1.action=path+"/web/equipmentmanage/equipmentmanage.jsp";
      document.form1.submit();
 }
</script>



