<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.*" %>
<%@ page import="com.noki.mobi.common.Account,java.text.DecimalFormat" %>
<%
System.out.println("3333333333333333333333333333");
	String path = request.getContextPath();
	String id = request.getParameter("id");
	//String dbid = request.getParameter("dbid");
	SiteModifyBean form = new SiteModifyBean();
	SiteModifyBean form2= new SiteModifyBean();
	//String numb1=request.getParameter("nums");
	Account account=(Account)session.getAttribute("account");
	String shengId = (String)session.getAttribute("accountSheng");
	String accountname=account.getAccountName();
	String login=account.getAccountId();
	String roleid = account.getRoleId();//权限
	System.out.println("权限" + roleid);
	System.out.println("id:"+id);
	 String dfwhszj="",dfwhszj_byhs="",dhszj="",dhszj_yysh="",dhszj_ykp="",dfhs_yysa="",dfhs_hdzb="",dfhs_kpzb=""
	 ,entrytimea="",updatetime="",shia="",entryperson="",updateperson="",dhszj_yyshd="";
	//System.out.println(form.getJzcode());
	//int nums=Integer.parseInt(numb1);
	//int numss=nums+1;
	 //id=form.getzdid(numss,login,shengId);
	 	//form = form.dbid(id,accountname);//如果没有电表就添加电表和相应的信息
	//String gdbid=form.getDbid();
	 SiteManage st=new SiteManage();
	
	System.out.println("电表*****id："+form.getLyjhjgs());
	//form = form.getJizhan(id,"");
	
	String typename="";
	typename=form.getTypename();
	
	
	String loginName = (String)session.getAttribute("loginName");
	
	
	// String jzproperty = request.getParameter("jzproperty")!= null ? request.getParameter("jzproperty") : "0";

	 ArrayList fylist = new ArrayList();
	 DecimalFormat df1 = new DecimalFormat("0.00");
	 if(id!=null&&!id.equals("")){
		 id=" AND Z.ID="+id;
	 }
	 fylist= st.getPageDatadfid(0,"","",id);
	 if(fylist!=null){
		 int fycount = ((Integer)fylist.get(0)).intValue();
		 for( int k = fycount;k<fylist.size()-1;k+=fycount){
			 id = (String) fylist.get(k + fylist.indexOf("ID"));
				dfwhszj = (String) fylist.get(k + fylist.indexOf("DFWHSZJ"));
				dfwhszj=df1.format(Double.parseDouble(dfwhszj));
				dfwhszj_byhs = (String) fylist.get(k + fylist.indexOf("DFWHSZJ_BYHS"));
				dfwhszj_byhs=df1.format(Double.parseDouble(dfwhszj_byhs));
				dhszj = (String) fylist.get(k + fylist.indexOf("DHSZJ"));
				dhszj=df1.format(Double.parseDouble(dhszj));
			    dhszj_yyshd = (String) fylist.get(k + fylist.indexOf("DHSZJ_YYSHD"));
				dhszj_yyshd=df1.format(Double.parseDouble(dhszj_yyshd));
				dhszj_ykp = (String) fylist.get(k + fylist.indexOf("DHSZJ_YKP"));
				dhszj_ykp=df1.format(Double.parseDouble(dhszj_ykp));
				dfhs_yysa = (String) fylist.get(k + fylist.indexOf("DFHS_YYS"));
    			
				dfhs_hdzb=(String) fylist.get(k+fylist.indexOf("DFHS_HDZB"));
				dfhs_hdzb=df1.format(Double.parseDouble(dfhs_hdzb));
				dfhs_kpzb = (String) fylist.get(k + fylist.indexOf("DFHS_KPZB"));
				dfhs_kpzb=df1.format(Double.parseDouble(dfhs_kpzb));
				shia = (String) fylist.get(k+ fylist.indexOf("SHI"));
				entrytimea=(String)fylist.get(k+fylist.indexOf("ENTRYTIME"));
		 }
	 }
	String cj=form.getChangjia();
	if(null==cj||"null".equals(cj)){
		
		cj="";
	}
	
	String jz = form.getJzlx();
	if(null==jz||"null".equals(jz)){
		jz="";
	}
	String sb = form.getShebei();
	if(null==sb||"null".equals(sb)){
		sb="";
	}
	String bu = form.getBbu();
	if(null==bu||"null".equals(bu)){
		bu ="";
	}
	String ru = form.getRru();
	if(null==ru||"null".equals(ru)){
		ru="";
	}
	String yd = form.getYdshebei(); //YDSHEBEI
	if(null==yd||"null".equals(yd)){
		yd="";
	}
	String gx = form.getGxgwsl();//GXGWSL
	if(null==gx||"null".equals(gx)){
		gx="";
	}
	String tw = form.getTwgx();
	if(null==tw||"null".equals(tw)){
		tw="";
	}
	String bm = form.getBm();
	if(null==bm||"null".equals(bm)){
		bm="";
	}
	String g2 = form.getG2xqs();
		if(null==g2||"null".equals(g2)){
			g2="";
	}
		String g3 = form.getG3sqsl();
		if(null==g3||"null".equals(g3)){
			g3="";
	}
		String yd1 = form.getYdgxsbsl();
		if(null==yd1||"null".equals(yd1)){
			yd1="";
	}
		String dx = form.getDxgxsbsl();
		if(null==dx||"null".equals(dx)){
			dx="";
	}
		String ys=form.getYsjts();
		if(null==ys||"null".equals(ys)){
			ys="0";
		}
		String wj=form.getWjts();
		if(null==wj||"null".equals(wj)){
			wj="0";
		}
		String yybg=form.getYybgkt();
		if(null==yybg||"null".equals(yybg)){
			
			yybg="0";
		}
		String jf=form.getJfsckt();
		if(null==jf||"null".equals(jf)){
			jf="0";
		}
		String kt1=form.getKtyps();
		String kt2=form.getKteps();
		String ktps=form.getKtps();
		if(null==kt1||"null".equals(kt1)){
			kt1="0";
		}
		if(null==kt2||"null".equals(kt2)){
			kt2="0";
		}
		if(null==ktps||"null".equals(ktps)){
			ktps="0";
		}
		
	
	
	String zg=form.getZgry();//在岗人数
	if("null".equals(zg)){
	zg="";
	}
	String kt=form.getKtsl();//空调数量
	if("null".equals(kt)){
		kt="";
	}
	
	String pc=form.getPcsl();//pc数量
	if("null".equals(pc)){
		pc="";
	}
		String ysymj=form.getYsymj();//已使用面积
	if("null".equals(ysymj)){
		ysymj="";
	}
	//判断分摊
	String sc1=form.getSc();
	if(null==sc1){
		sc1="0";
	}
    String bg1=form.getBg();
	if(null==bg1){
		bg1="0";
	}
	String yy1=form.getYy();
	if(null==yy1){
		yy1="0";
	}
	String xxhzc1=form.getXxhzc();
	if(null==xxhzc1){
		xxhzc1="0";
	}
	String jstz1=form.getJstz();
	if(null==jstz1){
		jstz1="0";
	}
	String dddf1=form.getDddf();
	if(null==dddf1){
		dddf1="0";
	}
	String dzdl=form.getQsdbdl();
	if(dzdl==null||dzdl.equals("null")||dzdl.equals(" ")){
		dzdl="";
	}
	String ltqx=form.getLtqx();
	//System.out.println("联通区县："+form.getLtqx()+"null".equals(ltqx));
	if(ltqx==null||"null".equals(ltqx)){
		ltqx="";
	}
	String address=form.getAddress();
	if(address==null||"null".equals(address)){
		address="";
	}
	//String ycq=form.getZdcq();
	//if(ycq==null||"null".equals(ycq)){
	//	ycq="";
	//}
	String wlzdbm=form.getWlzdbm();
	if(wlzdbm==null||"null".equals(wlzdbm)){
		wlzdbm="";
	}
	String zzlx =form.getZzlx();
//	if(null==zzlx||"null".equals(zzlx)){
	//	zzlx="";
	//}
String ydqz=form.getYdqx();
	if(ydqz==null||"null".equals(ydqz)){
	ydqz="";
}
	String nhxtid=form.getNhxtid();
	if(nhxtid==null||"null".equals(nhxtid)){
		nhxtid="";
	}
	String area=form.getArea();
	if(area==null||"null".equals(area)){
		area="";
	}
	String fzrphone=form.getFzrphone();
	if(null==fzrphone){
		fzrphone="";
	}
%>
<html>
<head>
<title>
addAccount
</title>
<style type="text/css">
           
            .selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
}
            .style1 {
	color:red;
	font-weight: bold;
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
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
			width:200px;
		}

 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript"
	src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
 <script >

//var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
//oCalendarEn.Init();

//var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
//oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
//oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
//oCalendarChs.oBtnTodayTitle="今天";
//oCalendarChs.oBtnCancelTitle="取消";
//oCalendarChs.Init();
</script>
<script language="javascript">

var path = '<%=path%>';
	function JZtypes(sxbzw){   //基站类型/接入网类型
	//alert("444888");
    kzinfo();
   
	var jzpro=document.form1.jzproperty.value;
	var yf=document.form1.yflx.value;
	var zl=Number(document.form1.zlfh.value);
	var jzty=document.form1.jzxlx.value;
	
	if(jzpro=="zdsx02" && yf=="fwlx01"){
		if(zl>=0&&zl<=10){
		document.form1.jzxlx.value="jzlx01";
		}else if(10<zl&&zl<=20){
		document.form1.jzxlx.value="jzlx02";
		}else if(20<zl&&zl<=30){
		document.form1.jzxlx.value="jzlx03";
		}else if(30<zl&&zl<=40){
		document.form1.jzxlx.value="jzlx04";
		}else if(40<zl&&zl<=50){
		document.form1.jzxlx.value="jzlx05";
		}else if(50<zl&&zl<=60){
		document.form1.jzxlx.value="jzlx06";
		}else if(60<zl&&zl<=70){
		document.form1.jzxlx.value="jzlx07";
		}else if(70<zl&&zl<=80){
		document.form1.jzxlx.value="jzlx08";
		}else if(80<zl&&zl<=90){
		document.form1.jzxlx.value="jzlx09";
		}else if(90<zl&&zl<=100){
		document.form1.jzxlx.value="jzlx10";
		}else if(zl>100){
		document.form1.jzxlx.value="jzlx11";
		}else{
		document.form1.jzxlx.value="0";
		}
	}else if(jzpro=="zdsx02" && yf=="fwlx02"){
				
	    if(zl>=0&&zl<=10){
		document.form1.jzxlx.value="jzlx12";
		}else if(10<zl&&zl<=20){
		document.form1.jzxlx.value="jzlx13";
		}else if(20<zl&&zl<=30){
		document.form1.jzxlx.value="jzlx14";
		}else if(30<zl&&zl<=40){
		document.form1.jzxlx.value="jzlx15";
		}else if(40<zl&&zl<=50){
		document.form1.jzxlx.value="jzlx16";
		}else if(50<zl&&zl<=60){
		document.form1.jzxlx.value="jzlx17";
		}else if(60<zl&&zl<=70){
		document.form1.jzxlx.value="jzlx18";
		}else if(70<zl&&zl<=80){
		document.form1.jzxlx.value="jzlx19";
		}else if(80<zl&&zl<=90){
		document.form1.jzxlx.value="jzlx20";
		}else if(90<zl&&zl<=100){
		document.form1.jzxlx.value="jzlx21";
		}else if(zl>100){
		document.form1.jzxlx.value="jzlx22";
		}else{
		document.form1.jzxlx.value="0";
		}
	}else if(jzpro=="zdsx05" && yf=="fwlx01"){
			if(zl>=0&&zl<=20){
		        document.form1.jrwtype.value="jrwlx01";
		        }else if(20<zl&&zl<=40){
		        document.form1.jrwtype.value="jrwlx02";
		        }else if(40<zl&&zl<=60){
				document.form1.jrwtype.value="jrwlx03";
				}else if(60<zl&&zl<=80){
				document.form1.jrwtype.value="jrwlx04";
				}else if(80<zl&&zl<=100){
				document.form1.jrwtype.value="jrwlx05";
				}else if(zl>100){
				document.form1.jrwtype.value="jrwlx06";
				}else{
				document.form1.jrwtype.value="0";
				}
	}else if(jzpro=="zdsx05" && yf=="fwlx02"){
			if(zl>=0&&zl<=20){
		        document.form1.jrwtype.value="jrwlx07";
		        }else if(20<zl&&zl<=40){
		        document.form1.jrwtype.value="jrwlx08";
		        }else if(40<zl&&zl<=60){
				document.form1.jrwtype.value="jrwlx09";
				}else if(60<zl&&zl<=80){
				document.form1.jrwtype.value="jrwlx10";
				}else if(80<zl&&zl<=100){
				document.form1.jrwtype.value="jrwlx11";
				}else if(zl>100){
				document.form1.jrwtype.value="jrwlx12";
				}else{
				document.form1.jrwtype.value="0";
				}
		}else if((jzpro=="zdsx02"||jzpro=="zdsx05") && (yf=="fwlx03"||yf=="0")){
		document.form1.jzxlx.value="0";
		document.form1.jrwtype.value="0";
		}
		if(sxbzw=="1"){
		zdsx();
		}
		 
	}
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
	function saveAccount(){
          	if(   
          	        checkNotSelected(document.form1.shi,"城市")&&
          	        checkNotnull(document.form1.dfwhszj,"垫付未回收资金")&&   //判断是否为空
          			checkNotnull(document.form1.dfwhszj_byhs,"垫付未回收：已回款")&&
          			checkNotnull(document.form1.dhszj,"待回收资金")&&
          		    checkNotnull(document.form1.dhszj_yyshd,"待回收资金：已和运营商完成明细核对")&&
          		    checkNotnull(document.form1.dhszj_ykp,"待回收资金：已开票")&&
          			checkNotSelected(document.form1.dfhs_yys,"运营商")
          	 )
          			{
		            if(
		            isNaN(document.form1.dfwhszj.value)==false
		           
		            ){
		            if(
		            		
		             isNaN(document.form1.dfwhszj_byhs.value)==false
		            ){	
		            if(
		            	 isNaN(document.form1.dhszj.value)==false	
		            ){
		            	
		           if(
		        	  isNaN(document.form1.dhszj_yyshd.value)==false	   
		           ){
		            if(
		              isNaN(document.form1.dhszj_ykp.value)==false		
		            ){
					      				addzhandian();
					      				}else{
					      					
					      					alert("您输入信息有误：待回收资金：已开票 必须为数字");
					      				}
					      				}else{
					      					
					      					alert("您输入信息有误：待回收资金：已和运营商完成明细核对 必须为数字");
					      				}
					      				}else{
					      					
					      					alert("您输入信息有误：待回收资金 必须为数字");
					      				}
					      				}else {
					      					
					      					alert("您输入信息有误：垫付未回收：已回款 必须为数字！");
					      				}
					 }else {
						 alert("您输入信息有误：垫付未回收资金 必须为数字！");
					 }     			
			                                      
        }
	}
		function addzhandian(){
        		if(confirm("您将要添加站点信息！确认信息正确！")){
        		
          		document.form1.action=path+"/servlet/zhandian?action=modifySitedf";
							document.form1.submit();
							 showdiv("请稍等..............");
            }
        	}
	
        function vName(){
         	var accName = document.form1.accountName.value;
                 if(accName==""){
           	alert("不能为空！")
                   return
          }
               window.open('accountName.jsp?accountId=0&accountName='+accName,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        function vCode(){
          var accCode = document.form1.workSn.value;
          if(accCode==""){
           	alert("不能为空！")
                   return
          }
               window.open('accountCode.jsp?accountId=0&accountCode='+accCode,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
         function chaxun(){
					document.form1.action=path+"/web/jizhan/modifysite.jsp";
					document.form1.submit();
	}
        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！")
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
		  $(function(){
			$("#cancelBtn").click(function(){
			window.history.back();
			});
		 $("#resetBtn").click(function(){
				$("#form")[0].reset();
			});
		  $("#saveBtn").click(function(){
			saveAccount();
			
			});
		   $("#query").click(function(){
			chaxun();
			});
			
			/////////////

			
		});
		
</script>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
</head>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST" id="form">
		<table width="100%"  border="0" cellspacing="1" cellpadding="1"  >
							<tr>
							<td colspan="4" width="50" >
											 <div style="width:700px;height:50px">
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">电费垫付回收修改</span>	
												</div>
											</td>
							<td width="500">&nbsp;</td>
						</tr>
			
			
		<tr class="selected_font">
         <td  bgcolor="#DDDDDD" width="15%"><div align="left">市：</div>
         </td>
         <td width="25%">
         <select name="shi" class="selected_font"   onchange="changeCity()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList shenglist = new ArrayList();
	         	shenglist = commBean.getAgcode(shengId,"0",loginName);
	         	if(shenglist!=null){
	         		String sfid="",sfnm="";
	         		int scount = ((Integer)shenglist.get(0)).intValue();
	         		for(int i=scount;i<shenglist.size()-1;i+=scount)
                    {
                    	sfid=(String)shenglist.get(i+shenglist.indexOf("AGCODE"));
                    	sfnm=(String)shenglist.get(i+shenglist.indexOf("AGNAME"));
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         </td>
		
           <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">月底前垫付未收回资金：</div>
         </td>
         <td width="25%"><input type="text" name="dfwhszj" onchange="changehszj()" value="<%=dfwhszj%>" class="selected_font"  title="自动计算，不可修改"/><span class="style1">&nbsp;*</span></td>
      	    <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">月底前垫付未收回资金：月已回款</div>
         </td>
         <td width="25%"><input type="text" name="dfwhszj_byhs" onchange="changehszj()" value="<%=dfwhszj_byhs%>" class="selected_font" /><span class="style1">&nbsp;*</span></td>
      </tr>
  
		
		<tr class="form_label">
		    <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">待回收资金：</div>
         </td>
         <td width="25%"><input type="text" readonly="readonly"  name="dhszj" onchange="changebl()" title="自动计算，不可修改" class="selected_font" value="<%=dhszj%>"  /><span class="style1">&nbsp;*</span></td>
       <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">待回收资金：已和运营商完成明细核对：</div>
         </td>
         <td width="25%"><input type="text" name="dhszj_yyshd" onchange="changebl()" value="<%=dhszj_yyshd%>" class="selected_font" /><span class="style1">&nbsp;*</span></td>
		    <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">待回收资金：已开票</div>
         </td>
         <td width="25%"><input type="text" name="dhszj_ykp" onchange="changebl()" value="<%=dhszj_ykp%>" class="selected_font" /><span class="style1">&nbsp;*</span></td>
		  
		
         </tr>
         <tr class="form_label">
      <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">运营商：</div>
         </td>
         <td width="25%">
         	<select name="dfhs_yys" id="dfhs_yys" style="width:130">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList ycq = new ArrayList();
         		ycq = ztcommon.getSelctOptions("yys");
	         	if(ycq!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)ycq.get(0)).intValue();
	         		for(int i=cscount;i<ycq.size()-1;i+=cscount)
                    {
                    	code=(String)ycq.get(i+ycq.indexOf("CODE"));
                    	name=(String)ycq.get(i+ycq.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         </td>
                 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">已核对占比：</div></td>     
       <td width="25%"><input type="text" readonly="readonly"  name="dfhs_hdzb" title="自动计算，不可修改" value="<%=dfhs_hdzb%>" class="selected_font" /><span class="style1">&nbsp;*</span></td>
       
               <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">已开票占比：</div></td>     
       <td width="25%"><input type="text" readonly="readonly"  name="dfhs_kpzb" title="自动计算，不可修改" value="<%=dfhs_kpzb%>" class="selected_font" /><span class="style1">&nbsp;*</span></td>
       
     </tr>
 
    <tr class="form_label" >
     <td colspan="2">
   <font color="red" size="2">
说明(保留两位小数)：<br/>
待回收资金：【垫付未回收资金】-【垫付未回收资金：已回款】<br/>
已核对占比：【待回收资金：已和运营商完成明细核对】/【待回收资金】<br/>
已开票占比：【待回收资金：已开票】/【待回收资金】
 						</font>

  </td>
        <td colspan="6">
        
        	<div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
			<img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
			<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>      
		</div>
		<div id="resetBtn" style="position:relative;width:60px;height:23px;cursor: pointer;right:15px;float:right;">
			<img alt="" src="<%=path %>/images/2chongzhi.png" width="100%" height="100%" />
			<span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">重置</span>
		</div>
		<div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:26px">
			 <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
			 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>      
		</div>
        </td>
      </tr>
									 
			
			
			  
</table>
	<input type="hidden" name="accountname" value="<%=accountname %>"/>
	<input type="hidden" name="id" value="<%=id%>"/>
	<input type="hidden" name="gsf" value="" />
	<input type="hidden" name="jnglmk" value="0" />
	<input type="hidden" name="sydate" value="" />
	<input type="hidden" name="ysd" value="" />
	<input type="hidden" name="watchcost" value="" />
	<input type="hidden" name="dbyt" value="dbyt01" />
	<input type="hidden" name="kt1" value="0" />
	<input type="hidden" name="kt2" value="0" />
 </form>
</body>
</html>

<script type="text/javascript">
      					
   function xsl(){
  // 02xsbl线损比例  01xstz 线损调整 
   		var xsll=document.form1.linelosstype.value;
   		var bl=document.getElementById("xs");
   		var blnr="线损值（%）：";
   		var tz="线损值（度）：";
   		if(xsll=="02xsbl"){
   		 bl.innerHTML=blnr;
   		}else if(xsll=="01xstz"){
   		bl.innerHTML=tz;
   		}
   }
   
  var XMLHttpReq;
	//XMLHttpReq = createXMLHttpRequest();
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	
	///////////////////////////////////////////////////////////
	function sendRequest(url,para) {

		createXMLHttpRequest();
	
	
		XMLHttpReq.open("GET", url, true);
		
		if(para=="sheng"){
			XMLHttpReq.onreadystatechange = processResponse_sheng;//指定响应函数
		}else if(para=="shi"){
			XMLHttpReq.onreadystatechange = processResponse_shi;
		}else if(para=="xian"){
			XMLHttpReq.onreadystatechange = processResponse_xian;
		}else if(para=="jzproperty"){
			XMLHttpReq.onreadystatechange = processResponse_zdlx;//指定响应函数
			
		}
		else{
			XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		}
		//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded")  
		XMLHttpReq.send(null);  
	}
	/////////////////////////////////////////////////////////////
	// 处理返回信息函数
    function processResponse() {
    	
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		//alert(XMLHttpReq.status);
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
            	var res = XMLHttpReq.responseText;
              window.alert(res);
             
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
    
function processResponse_sheng() {
    	if (XMLHttpReq.readyState == 4) { // 判断对象状态
    		
        	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
            	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
            	
            	//var res = XMLHttpReq.responseText;
            
              updateShi(res);
                       
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }

	function processResponse_shi() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateQx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
	}
	function processResponse_xian() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZd(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
	}

	function processResponse_zdlx() {
	//alert("333");
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZdlx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
	}
   function changeCity(){
	var sid = document.form1.shi.value;

	if(sid=="0"){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
	}
	function changeCountry(){
	var sid = document.form1.xian.value;

	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
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
	function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
	}
	
		
	//站点属性
	function zdsx(){
	//alert("111");
	var sid = document.form1.jzproperty.value;
    //var s1=document.form1.s1.value;
    
	if(sid=="0"){
		var shilist = document.all.stationtype;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=zdsx&pid="+sid,"jzproperty");
	}
	
}
//站点类型
function updateZdlx(res){
// alert("444");
	var shilist = document.all.stationtype;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
} 	
	
document.form1.shi.value='<%=shia%>';// 市
document.form1.dfhs_yys.value='<%=dfhs_yysa%>';
//document.form1.xian.value='<%= form.getXian()%>';//区县
//document.form1.xiaoqu.value='<%=form.getXiaoqu()%>';//小区
//document.form1.fuzeren.value='<%=form.getFuzeren() %>';//站点负责人
//if(document.form1.fuzeren.value==null||document.form1.fuzeren.value=="null"){
	//document.form1.fuzeren.value="";
//}

//document.form1.qyzt.value='<%=form.getQyzt()%>';//站点启用状态

//if(document.form1.qyzt.value==""||document.form1.qyzt.value==null){
//document.form1.qyzt.value="1";
//}
//document.form1.stationtype.value='<%=form.getStationtype()%>';//站点类型
//if(document.form1.stationtype.value==""||document.form1.stationtype.value==null){
//document.form1.stationtype.value="0";
//}

//document.form1.jzproperty.value='<%=form.getJzproperty()%>';//站点属性
//if(document.form1.jzproperty.value==""||document.form1.jzproperty.value==null){
//	document.form1.jzproperty.value="0";
//}


//document.form1.yflx.value='<%=form.getYflx()%>';//用房类型
//if(document.form1.yflx.value=="" || document.form1.yflx.value==null){
//document.form1.yflx.value="0";
//}



//document.form1.address.value='<%=form.getAddress()%>';
//document.form1.gdfs.value='<%=form.getGdfs()%>';//供电方式
//if(document.form1.gdfs.value==""||document.form1.gdfs.value==null){
//document.form1.gdfs.value="0";
//}

//document.form1.gxxx.value='<%=form.getGxxx()%>';//共享信息
//if(document.form1.gxxx.value=="" || document.form1.gxxx.value==null){
//document.form1.gxxx.value="0";
//}

//document.form1.ycq.value='<%=form.getZdcq()%>';//原产权 
//if(document.form1.ycq.value=="" || document.form1.ycq.value==null){
//document.form1.ycq.value="0";
//}
//document.form1.zzlx.value='<%=form.getZzlx()%>';//原产权 
///if(document.form1.zzlx.value=="" || document.form1.zzlx.value==null){
//document.form1.zzlx.value="0";
//}
//function kzinfo(){
  //  var jzproperty = document.form1.jzproperty.value; 
//}
//var typecode='<%=form.getJztype()%>';
//var code='<%=form.getJzproperty()%>';

function changehszj(){
	var a=document.form1.dfwhszj.value;
	var d= new Number(a);
	document.form1.dfwhszj.value=d.toFixed(2); 
	var b=document.form1.dfwhszj_byhs.value;
	var e=new Number(b);
	document.form1.dfwhszj_byhs.value=e.toFixed(2); 
	 var f=d.toFixed(2)-e.toFixed(2);
	 var f=new Number(f);
    document.form1.dhszj.value=f.toFixed(2);
	//alert(c);
}

function changebl(){
	var a=document.form1.dhszj.value;
	var e=new Number(a);
	var b=document.form1.dhszj_yyshd.value;
	var f=new Number(b);
	var aa=f/e*100;
	var bb=new Number(aa);
	//alert(aa.toFixed(2));
	document.form1.dhszj_yyshd.value=f.toFixed(2);
	var c=document.form1.dhszj_ykp.value;
	var g=new Number(c);
	document.form1.dhszj_ykp.value=g.toFixed(2);
	
	//var d=document.form1.dfhs_hdzb.value;
 document.form1.dfhs_hdzb.value=aa.toFixed(2);
	//var e=document.form1.dfhs_kpzb.value;
	var ee=new Number(g.toFixed(2));
	var cc=ee/e*100;
	var dd=new Number(cc);
	document.form1.dfhs_kpzb.value=dd.toFixed(2);
	
}


</script>