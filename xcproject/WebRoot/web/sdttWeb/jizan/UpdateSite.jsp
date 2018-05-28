<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.*" %>
<%@ page import="java.lang.Double" %>
<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	Account account=(Account)session.getAttribute("account");
	String jzproperty=request.getParameter("jzproperty");//获取新站点的属性  
	String roleid = account.getRoleId();
	String accountname=account.getAccountName();
	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd");
	String entrytime=mat.format(new Date());
	System.out.println("系统时间"+entrytime+"  shengId:"+shengId+"  loginName:"+loginName+"  accountname:"+accountname+" jzproperty:"+jzproperty);
	String id = request.getParameter("id");
	SiteModifyBean form = new SiteModifyBean();
	form = form.getJizhan(id, "");
	System.out.println("站点属性"+form.getJzproperty()+"权限"+roleid);
	
	String typename="";
	typename=form.getTypename();
	System.out.println("电表*****id："+form.getLyjhjgs()+"站点属性"+form.getJzproperty());
	System.out.println(" changjia:"+form.getChangjia());
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
	String ycq=form.getZdcq();
	if(ycq==null||"null".equals(ycq)){
		ycq="";
	}
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改站点</title>
<link href="../css/content.css" rel="stylesheet" type="text/css" />
<script language="javascript">

var path = '<%=path%>';
	function JZtypes(sxbzw){   //基站类型/接入网类型
	alert("444888");
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
			
			    var str = "2014-01-01";
                       if(
                          checkNotSelected(document.form1.shi,"城市")&&
                          checkNotSelected(document.form1.xian,"区县")&&
          				  checkNotSelected(document.form1.xiaoqu,"乡镇")&&
          				  checkNotSelected(document.form1.jzproperty,"站点属性")&&
          				  checkNotnull(document.form1.jzproperty,"站点属性")&&
                      	  checkNotSelected(document.form1.stationtype,"站点类型")&&
                       	  checkNotnull(document.form1.jzname,"站点名称")&&
                    	  checkNotnull(document.form1.gdfs,"供电方式")&&
                     	  checkNotnull(document.form1.jzcode,"站点编码")&&
                     	  checkNotnull(document.form1.qyzt,"站点启用状态")&&
                     	  checkNotSelected(document.form1.gxxx,"共享信息")&&
          		          checkNotnull(document.form1.jingdu,"经度")&&
          			      checkNotnull(document.form1.weidu,"纬度"&&
          			      checkNotSelected(document.form1.yflx,"房屋类型")
          			      )
                       ){
                        
          		     
          		 	 		if( 
          		 	 			isNaN(document.form1.area.value)==false&&
		                        isNaN(document.form1.jingdu.value)==false&&
		                        isNaN(document.form1.weidu.value)==false
          		 	 		
          		 	 		){
          		 		  					if(document.form1.area.value<0){
						     					 alert("站点面积不能为负数，最小为0!");
						     				  }
											 
			    		 				if (confirm("您将要修改站点信息！确认信息正确！")){
	                                                   
														document.form1.action=path+"/servlet/zhandian?action=modifySite"
														document.form1.submit();
														showdiv("请稍等..............");
								    			}
					     				  	
			  		}else{
			  			
			  			alert("您输入站点信息有误：站点面积 ，经度、纬度 必须为数字！")}                                       
       	
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
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body>
<form action="" name="form1" method="post" id="form">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr valign="top">
	
		<td width="12"><img src="../images/space.gif" width="12" height="17" /></td>
		<td>
			<div class="content01">
				<div class="tit01">站点修改</div>
				<div class="content01_1">
					<table width="1000px" border="0" cellpadding="2" cellspacing="0" class="tb_select" >
						<tr>
							<td align="right" width="100px">城市</td>
							<td width="100px">
								<select name="shi" class="selected_font" disabled="true" onchange="changeCity()" >
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
					         	</select>&nbsp;
							</td>
							<td align="right" width="100px">区县</td>
							<td width="100px">
								<select name="xian" class="selected_font" disabled="disabled" onchange="changeCountry()">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList xianlist = new ArrayList();
						         	xianlist = commBean.getAgcode(form.getShi(),form.getXian(),loginName);
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
					         	</select>
							</td>
							<td align="right" width="100px">乡镇</td>
							<td width="100px">
								<select name="xiaoqu" id="xiaoqu" disabled="disabled"  class="selected_font" >
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList xiaoqulist = new ArrayList();
						         	xiaoqulist = commBean.getAgcode(form.getXian(),form.getXiaoqu(),loginName);
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
					         	</select>
							</td>
							<td align="right" width="100px">站点属性</td>
							<%if(roleid.equals("81")||roleid.equals("82")||roleid.equals("83")||roleid.equals("84")||roleid.equals("85")||roleid.equals("1")){ %> 
					         <td width="25%">
					         	<select name="jzproperty" class="selected_font" onchange="JZtypes(1)" title="必填项，根据下拉值选择填写">
					         		<option value="0">请选择</option>
					         		<% 
						         	ArrayList zdsx = new ArrayList();
						         	zdsx = ztcommon.getSelctOptions("zdsx");
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
					         	</select><span class="style1">&nbsp;*</span>
					         	</td>
							<% }else {%>
							        <td width="25%">
					         	<select name="jzproperty"  onfocus="this.defaultIndex=this.selectedIndex;"  onchange="this.selectedIndex=this.defaultIndex;"  style="background-color:#FFFFFF; color:grey" 
					         			class="selected_font" title="必填项，根据下拉值选择填写">
					         	<option value="0">请选择</option>
					         		<%
						         	ArrayList zdsx = new ArrayList();
						         	zdsx = ztcommon.getSelctOptions("zdsx");
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
					             
					         	</select>
					         	</td>
							<%} %> 
						</tr>
						<tr>
							<td align="right" width="100px">站点类型</td>
							<%if(roleid.equals("81")||roleid.equals("82")||roleid.equals("83")||roleid.equals("84")||roleid.equals("85")||roleid.equals("1")){ %>
						         <td width="25%">
						         	<select name="stationtype"   class="selected_font" onchange="kzinfo()" title="必填项，根据下拉值选择填写">
						         		<option value="0">请选择</option>
						         		<%
							         	ArrayList station = new ArrayList();
						         		station = ztcommon.getZdlx(form.getJzproperty());////form.getJzproperty()
							         	if(station!=null){
							         		String code="",name="";
							         		int cscount = ((Integer)station.get(0)).intValue();
							         		for(int i=cscount;i<station.size()-1;i+=cscount)
						                    {
						                    	code=(String)station.get(i+station.indexOf("CODE"));
						                    	name=(String)station.get(i+station.indexOf("NAME"));
						                    %>
						                    <option value="<%=code%>"><%=name%></option>
						                    <%}
							         	}
							         %>
						         	</select><span class="style1">&nbsp;*</span>
						         </td>
						         <% }else {%>
						         
						               <td width="25%">
						         	<select name="stationtype"  class="selected_font" onfocus="this.defaultIndex=this.selectedIndex;"  onchange="this.selectedIndex=this.defaultIndex;"  style="background-color:#FFFFFF; color:grey"  title="必填项，根据下拉值选择填写">
						         		<option value="0">请选择</option>
						         		<%
							         	ArrayList station = new ArrayList();
						         		station = ztcommon.getZdlx(form.getJzproperty());////form.getJzproperty()
							         	if(station!=null){
							         		String code="",name="";
							         		int cscount = ((Integer)station.get(0)).intValue();
							         		for(int i=cscount;i<station.size()-1;i+=cscount)
						                    {
						                    	code=(String)station.get(i+station.indexOf("CODE"));
						                    	name=(String)station.get(i+station.indexOf("NAME"));
						                    %>
						                    <option value="<%=code%>"><%=name%></option>
						                    <%}
							         	}
							         %>
						         	</select><span class="style1">&nbsp;*</span>
						         </td>
						         <%} %>
							<td align="right" width="100px">站点名称</td>
							<td width="100px">
							<input type="text" name="jzname" value="<%=form.getJzname() %>" class="selected_font" title="必填项,根据站点所在建筑物名称填写 例如：软件科技园D座楼"/><span class="style1">&nbsp;*</span></td>							</td>
							<td align="right" width="100px">供电方式</td>
							<td width="100px">
									<select name="gdfs" class="selected_font" title="必填项，根据下拉值选择填写">
						         		<option value="0">请选择</option>
						         		<%
							         	ArrayList gdfs = new ArrayList();
							         	gdfs = ztcommon.getSelctOptions("gdfs");
							         	if(gdfs!=null){
							         		String code="",name="";
							         		int cscount = ((Integer)gdfs.get(0)).intValue();
							         		for(int i=cscount;i<gdfs.size()-1;i+=cscount)
						                    {
						                    	code=(String)gdfs.get(i+gdfs.indexOf("CODE"));
						                    	name=(String)gdfs.get(i+gdfs.indexOf("NAME"));
						                    %>
						                    <option value="<%=code%>"><%=name%></option>
						                    <%}
							         	}
							         %>
						
						         	</select>
							</td>
							<td align="right" width="100px">站点编码</td>
							<td width="100px">
      						<input type="text" name="jzcode" value="<%=form.getJzcode()==null?"":form.getJzcode() %>" class="selected_font" /><span class="style1">&nbsp;*</span>
							</td>
						</tr>
						
						<tr>
							<td align="right" width="100px">共享信息</td>
							<td width="100px">
								<select name="gxxx" style="width:130">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList gxxx = new ArrayList();
					         		gxxx = ztcommon.getSelctOptions("gxxx");
						         	if(gxxx!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)gxxx.get(0)).intValue();
						         		for(int i=cscount;i<gxxx.size()-1;i+=cscount)
					                    {
					                    	code=(String)gxxx.get(i+gxxx.indexOf("CODE"));
					                    	name=(String)gxxx.get(i+gxxx.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">经度</td>
							<td width="100px">
							<input type="text" name="jingdu" value="<%=form.getLongitude()%>" class="selected_font" /><span class="style1">&nbsp;*</span></td>
							<td align="right" width="100px">纬度</td>
							<td width="100px">
								<input type="text" name="weidu" value='<%=form.getLatitude() %>' style="width:130px"/>
							</td>
							<td align="right" width="100px">房屋类型</td>
							<td width="100px">
								<select name="yflx" class="selected_font" onchange="JZtypes(2)" title="根据下拉值选择填写，用房类型包括：砖混、彩钢板+直流负荷 来判断基站或接入网的22个基站类型和接入网类型">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList fwlx = new ArrayList();
						         	fwlx = ztcommon.getSelctOptions("fwlx");
						         	if(fwlx!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)fwlx.get(0)).intValue();
						         		for(int i=cscount;i<fwlx.size()-1;i+=cscount)
					                    {
					                    	code=(String)fwlx.get(i+fwlx.indexOf("CODE"));
					                    	name=(String)fwlx.get(i+fwlx.indexOf("NAME"));
					                    	if(name==null||"null".equals(name)){
					                    		name="";
					                    	}
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>&nbsp;
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">原产权方</td>
							<td width="100px">
								<select name="ycq" id="ycq" style="width:130">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList ycqf = new ArrayList();
					         		ycqf = ztcommon.getSelctOptions("ycq");
						         	if(ycq!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)ycqf.get(0)).intValue();
						         		for(int i=cscount;i<ycqf.size()-1;i+=cscount)
					                    {
					                    	code=(String)ycqf.get(i+ycqf.indexOf("CODE"));
					                    	name=(String)ycqf.get(i+ycqf.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					
					         	</select>
							</td>
							<td align="right" width="100px">物理站点编码</td>
							<td width="100px">
								<input type="text" name="wlzdbm" value='<%=wlzdbm %>' style="width:130px"/>
							</td>
							<td align="right" width="100px">站址类型</td>
							<td width="100px">
								<select name="zzlx" id="zzlx" style="width:130">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList zzlxa = new ArrayList();
					         		zzlxa = ztcommon.getSelctOptions("zzlx");
						         	if(zzlx!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)zzlxa.get(0)).intValue();
						         		for(int i=cscount;i<zzlxa.size()-1;i+=cscount)
					                    {
					                    	code=(String)zzlxa.get(i+zzlxa.indexOf("CODE"));
					                    	name=(String)zzlxa.get(i+zzlxa.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
					         	</select>
							</td>
							<td align="right" width="100px">对应联通区县</td>
							<td width="100px">
								<input type="text" name="ltqx" value="<%=ltqx%>" class="selected_font" />&nbsp;
							</td>
						</tr>
						<tr>
							<td align="right" width="100px">对应移动区县</td>
							<td width="100px">
								<input type="text" name="ydqx" value="<%=ydqz%>" class="selected_font" />
							</td>
							<td align="right" width="100px">联通能耗系统ID</td>
							<td width="100px">
								<input type="text" name="xtid" value="<%=nhxtid%>" class="selected_font" />
							</td>
							<td align="right" width="100px">站点地址</td>
							<td width="100px">
								<input type="text" name="address" value="<%=address%>" class="selected_font"  title="选填项，站点所在的具体物理位置"/>
							</td>
							<td align="right" width="100px">站点面积（㎡）</td>
							<td width="25%">
					          <%if(roleid.equals("81")||roleid.equals("82")||roleid.equals("83")||roleid.equals("84")||roleid.equals("85")||roleid.equals("1")){ %>
					         	<input type="text" name="area" value="<%=area%>" class="selected_font" title="选填项，根据站点所在地的具体面积填写 单位：平方米"/>
					         <%}else{ %>
					       		<input type="text" name="area" value="<%=area%>" class="selected_font" title="选填项，根据站点所在地的具体面积填写 单位：平方米"/>
					         <% }%>
					       </td>
						</tr>
						<tr>
							<td align="right" width="100px">负责人联系方式</td>
							<td width="100px">
								<input type="text" name="phone" value="<%=fzrphone%>" class="selected_font"/>
							</td>
							<td align="right" width="100px">供电方名称</td>
							<td width="100px">
								<input type="text" name="gdfname" value='' style="width:130px"/>
							</td>
							<td align="right" width="100px">部门</td>
							<td width="100px">
								<input type="text" name="bumen" value="<%=bm%>" class="selected_font"/>
							</td>
							<td align="right" width="100px">站点责任人</td>
							<td width="100px">
								<input type="text" name="fuzeren"  style="width:130px" title="选填项，站点具体负责的人员"/>&nbsp;
							</td>
						</tr>						
						<tr>
							<td align="right" colspan="8" height="60px">
								<input name="button2" type="submit" class="btn_c1" id="button2" value="临时保存" />&nbsp; 
								<input onclick="saveAccount()" type="submit" class="btn_c1" id="button2" value="提交审批" />&nbsp; 
								<input name="button2" type="submit" class="btn_c1" id="button2" value="重置" />&nbsp; 
							</td>
						</tr>
					</table>
				</div>
			</div>
		</td></tr></table>
		</form>
</body>
</html>
<script type="text/javascript">
		function saveAccount(){
			    if (confirm("您将要修改站点信息！确认信息正确！")){
					document.form1.action=path+"/servlet/zhandian?action=modifySite&id="+'<%=id%>'
					document.form1.submit();
				}
	}

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
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=shi&pid="+sid,"shi");
	}
	}
	function changeCountry(){
	var sid = document.form1.xian.value;
	alert(sid);
	if(sid=="0"){
		var shilist = document.all.xiaoqu;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=xian&pid="+sid,"xian");
	}
	}
	function updateQx(res){
		var shilist = document.all.xian;
		shilist.options.length="0";
		shilist.add(new Option("请选择","0"));
	
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
	shilist.add(new Option("请选择","0"));
	
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
		shilist.add(new Option("请选择","0"));
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
	shilist.add(new Option("请选择","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
} 	
	
document.form1.shi.value='<%= form.getShi()%>';// 市
document.form1.xian.value='<%= form.getXian()%>';//区县
document.form1.xiaoqu.value='<%=form.getXiaoqu()%>';//小区
// document.form1.fuzeren.value='<%=form.getFuzeren() %>';//站点负责人
// if(document.form1.fuzeren.value==null||document.form1.fuzeren.value=="null"){
// 	document.form1.fuzeren.value="";
// }

// document.form1.qyzt.value='<%=form.getQyzt()%>';//站点启用状态

// if(document.form1.qyzt.value==""||document.form1.qyzt.value==null){
// document.form1.qyzt.value="1";
// }
document.form1.stationtype.value='<%=form.getStationtype()%>';//站点类型
if(document.form1.stationtype.value==""||document.form1.stationtype.value==null){
document.form1.stationtype.value="0";
}

 document.form1.jzproperty.value='<%=form.getJzproperty()%>';//站点属性
 if(document.form1.jzproperty.value==""||document.form1.jzproperty.value==null){
 	document.form1.jzproperty.value="0";
 }


document.form1.yflx.value='<%=form.getYflx()%>';//用房类型
if(document.form1.yflx.value=="" || document.form1.yflx.value==null){
document.form1.yflx.value="0";
}



document.form1.address.value='<%=form.getAddress()%>';
document.form1.gdfs.value='<%=form.getGdfs()%>';//供电方式
if(document.form1.gdfs.value==""||document.form1.gdfs.value==null){
document.form1.gdfs.value="0";
}

document.form1.gxxx.value='<%=form.getGxxx()%>';//共享信息
if(document.form1.gxxx.value=="" || document.form1.gxxx.value==null){
document.form1.gxxx.value="0";
}

document.form1.ycq.value='<%=form.getZdcq()%>';//原产权 
if(document.form1.ycq.value=="" || document.form1.ycq.value==null){
document.form1.ycq.value="0";
}
document.form1.zzlx.value='<%=form.getZzlx()%>';//原产权 
if(document.form1.zzlx.value=="" || document.form1.zzlx.value==null){
document.form1.zzlx.value="0";
}
function kzinfo(){
    var jzproperty = document.form1.jzproperty.value; 
}
var typecode='<%=form.getJztype()%>';
var code='<%=form.getJzproperty()%>';
</script>