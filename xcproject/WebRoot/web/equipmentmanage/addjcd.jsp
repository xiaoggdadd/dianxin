<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.*"%>

<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String dianBiaoId = request.getParameter("dianBiaoId");
    String canshuStr="?dianBiaoId="+dianBiaoId;
    System.out.println("电表ID"+canshuStr);
    int jcdid = 0;
   double rad = Math.random()*1000;//随机数
    DecimalFormat df = new DecimalFormat( "000"); 
    String xx =df.format(rad);
    String result = dianBiaoId+xx;
    
    String sc=request.getParameter("sc");
    String yy=request.getParameter("yy");
    String bg=request.getParameter("bg");
    String xxhzc=request.getParameter("xxhzc");
    String jstz=request.getParameter("jstz");
   String dddf=request.getParameter("dddf");
    
    String s=" and bzw='1'";
    String ss=" and bzw='2'";
    String sss="";
    String shuoshuzhuanye = request.getParameter("shuoshuzhuanye");
    String qcbcode = request.getParameter("qcbb");
      String kjkmcode = request.getParameter("kjkmcode");

    //String zb = request.getParameter("zb");
    //String kzb = request.getParameter("kzb");
   // System.out.print("占比"+zb+kzb);
%>

<html>
<head>
<title>
添加监测点
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
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
.selected_font1{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
		border-left-width:0px; border-top-width:0px; border-right-width:0px;
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
 </style>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script type="text/javascript" src="<%=path%>/web/javascript/prototype.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <script type="text/javascript" src="<%=path%>/web/javascript/jquery-1.4.2.js"></script>
<script language="javascript">
     
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
	    if (window.document.readyState){//IE
		      if (window.document.readyState=='complete'){ 
                 selDianBiaoId();
                // SelPermissionCconfiguration();
                 SelPermissionCconfiguration1();
		      }else{
		        setTimeout("waitThenDoIt()",10);
		      }
	   	 } 
	}
function selDianBiaoId(){
    document.getElementById("dianbiaoid").value='<%=dianBiaoId%>';
    var Url=getPublicPath()+"/servlet/EquipmentmanageServlet?action=yulan&dianBiaoId="+'<%=dianBiaoId%>';
   		 Url = encodeURI(Url);
        new Ajax.Request(Url,{
      		method : 'post',
			asynchronous: false,
			onComplete : function (resp){//回调
 			  var result=resp.responseText;//返回数据
				  var rowDate = result.split("|");
				  var date=100-rowDate[0];	
				  var f = parseFloat(date);   
				 // alert(date);
				  date=Math.round(date*100)/100;//保留两位小数
				  //alert(date);
				 if(rowDate[0].toString()==""){
				    document.getElementById("ysbili").innerHTML=100+"%";
				     document.getElementById("bili").value=100;
				  }
				  else{
				   document.getElementById("ysbili").innerHTML=date+"%";
				    document.getElementById("bili").value=date;
				  }
				  
 			   	}
 	})				
	
	}
	
	function Bili(){
	//alert("2222222");
	 	document.getElementById("dianbiaoid").value='<%=dianBiaoId%>';
        var Url=getPublicPath()+"/servlet/EquipmentmanageServlet?action=yulan&dianBiaoId="+'<%=dianBiaoId%>';
   		Url = encodeURI(Url);
        new Ajax.Request(Url,{
      		method : 'post',
			asynchronous: false,
			onComplete : function (resp){//回调
 			var result=resp.responseText;//返回数据
			var rowDate = result.split("|");	
			var bi = parseFloat(document.getElementById("bili").value);
			var yb = parseFloat(bi+rowDate[0]);
			var bili = parseFloat(rowDate[0])+bi;
			//alert("---"+bb+"---"+bi+"---"+aaa);
				 if(bili>100){
				 alert("该电表的所属电表占比大于100%，请重新输入");
				 return;
				 
				 }
 			   	}
 	})				
	
	}
	
	
	
	function SelPermissionCconfiguration1(){
		        var Url=getPublicPath()+"/servlet/EquipmentmanageServlet?action=SelPermissionCconfiguration1&roleFlg=PERMISSION_SEARCH";
			    new Ajax.Request(Url,{
			      		method : 'post',
						asynchronous: false,
						onComplete : function (resp){//回调
			 			  var result=resp.responseText;//返回数据
			 			 var msg = result.split("|");
							if ("msg"==msg[0]){
							  alert(msg[1]);
							  return;
							}
			 			   	dataType = document.getElementById("zymxcode");
							  var rowDate = result.split(",");	
							  
							  for(var i=0;i<rowDate.length;i++){
								var cellDate = rowDate[i].split("|");//有多少列
					 			 var op =document.createElement("option");
				 			 	 op.innerHTML = cellDate[2];
				 			 	 op.value = cellDate[0];
				 			 	 dataType.appendChild(op);
				              }
			 			   	}
			 	})
	}	
/**
	function addDate(){
        
       var dianbiaoid = document.getElementById("dianbiaoid").value; 
       var sheiebanid = document.getElementById("sheiebanid").value; 
      
       if(sheiebanid==undefined||sheiebanid==""){
        alert( "监测点ID不能为空!"); 
        return;
       }
       var mingcheng = document.getElementById("mingcheng").value;    
       var guige = document.getElementById("guige").value;  
       var shuoshuzhuanye = document.getElementById("shuoshuzhuanye").value; 
        var kjkmcode = document.getElementById("kjkmcode").value;  
       var qcbcode = document.getElementById("qcbcode").value; 
       var shuoshuwangyuan = document.getElementById("shuoshuwangyuan").value;  
       var bili = document.getElementById("bili").value; 
		//var   type="^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"; 
		var type ="^[0-9]*[1-9][0-9]*$";
		 var   re   =   new   RegExp(type); 
       if(bili!="" && bili.match(re)==null) 
        { 
         alert( "请输入大于零的数字!"); 
        return;
       }
       if (bili>100){
        alert( "请输入不大于100的数字!"); 
         return;
       }
       if (bili==""){
         bili=100;
       }
       var sccj = document.getElementById("sccj").value;  
       var zcbh = document.getElementById("zcbh").value;   
       var bccd = document.getElementById("bccd").value;  
       var   bccdtype="^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"; 
       var   bccdre   =   new   RegExp(bccdtype); 
       if(bccd !="" && bccd.match(bccdre)==null) 
        { 
         alert( "请输入数字!"); 
        return;
       }
      var dianbiaoid = document.getElementById("dianbiaoid").value;
      var inbili = document.getElementById("bili").value;
       var sheiebanid = document.getElementById("sheiebanid").value;
      var qcbcode = document.getElementById("qcbcode").value;
      var kjkmcode = document.getElementById("kjkmcode").value;
      var zymxcode = document.getElementById("zymxcode").value;
      if(dianbiaoid!=null && dianbiaoid!=""){ //判断比例是否100
	      var Url=getPublicPath()+"/servlet/EquipmentmanageServlet?action=chekBili&dianbiaoid="+dianbiaoid+"&flg=1&inbili="+inbili;
	      var result ="";
	      		    Url = encodeURI(Url);
				    new Ajax.Request(Url,{
				      		method : 'post',
							asynchronous: false,
							onComplete : function (resp){//回调
				 			result=resp.responseText;//返回数据
									
				 			   	}
				})
			if (""!=result){
			  if("小于100"==result){
			     
			   }
			   else{
				    alert(result);
				   return;
			   }
			  
			}
      }
	  var beizhu = document.getElementById("beizhu").value;  
	   var zymxcode = document.getElementById("zymxcode").value;
	   var params="&dianbiaoid="+dianbiaoid+"&sheiebanid="+sheiebanid+"&mingcheng="+mingcheng+"&guige="+guige+"&shuoshuzhuanye="+shuoshuzhuanye+"&shuoshuwangyuan="+shuoshuwangyuan+"&kjkmcode="+kjkmcode+"&qcbcode="+qcbcode+"&bili="+bili+"&sccj="+sccj+"&zcbh="+zcbh+"&bccd="+bccd+"&beizhu="+beizhu+"&zymxcode="+zymxcode;
      // alert(dianbiaoid+sheiebanid);
        var Url=getPublicPath()+"/servlet/EquipmentmanageServlet?action=updataSession&roleFlg=PERMISSION_EDIT";
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
	 			}
	 	})
       Goback();
    }
*/
function  Goback(){
       var bz="1";
       var de="0";
       var sc='<%=sc%>';
       var yy='<%=yy%>';
       var bg='<%=bg%>';
       var xxhzc='<%=xxhzc%>';
       var jstz='<%=jstz%>';
       var dddf='<%=dddf%>';
     window.location.href=path+"/web/equipmentmanage/add.jsp<%=canshuStr%>&sc="+sc+"&yy="+yy+"&bg="+bg+"&xxhzc="+xxhzc+"&jstz="+jstz+"&dddf="+dddf+"&bz="+bz+"&de="+de+"";
}

function updata(){
	if(checkNotnull(document.form1.bili,"所属电表占比")){
	   if(isNaN(document.form1.bili.value)==false){
       var params ="&key=value";
       var dianbiaoid = document.getElementById("dianbiaoid").value; 
       var sheiebanid = document.getElementById("sheiebanid").value; 
       var mingcheng1 = document.getElementById("mingcheng").value; 
        // var mingcheng=mingcheng1.toString().replace(/\#/g,"%23");
         var test = new RegExp("#","g");
    	   var test1= new RegExp("&","g");
    	   var test2= new RegExp("%","g");
    	   var mingcheng;
    	   if(mingcheng1.match(test)){
    	    mingcheng = mingcheng1.toString().replace(/\#/g,"%23");
      
    	   }else if(mingcheng1.match(test1)){
    	    mingcheng = mingcheng1.toString().replace(/\&/g,"%26");
    	   
      
    	   }else if(mingcheng1.match(test2)){
    	    mingcheng = mingcheng1.toString().replace(/\%/g,"%25");
    	   
     
    	   }else{
    		  mingcheng= mingcheng1;
    	   }
       var guige = document.getElementById("guige").value;  
       var shuoshuzhuanye = document.getElementById("shuoshuzhuanye").value;  
       var shuoshuwangyuan = document.getElementById("shuoshuwangyuan").value;  
       var bili = document.getElementById("bili").value; 
       //如果上级分摊为0不允许添加下级分摊
       var bz="1";
       var sc='<%=sc%>';
       var yy='<%=yy%>';
       var bg='<%=bg%>';
       var xxhzc='<%=xxhzc%>';
       var jstz='<%=jstz%>';
       var dddf='<%=dddf%>';
       if(shuoshuzhuanye=="zylx01"&&sc=="0"){   
         alert("分摊信息里生产占比为0,不能添加下级分摊,请返回上级页面进行修改");
         return;
       }else if(shuoshuzhuanye=="zylx02"&&yy=="0"){
         alert("分摊信息里营业占比为0,不能添加下级分摊,请返回上级页面进行修改");
         return;
       }else if(shuoshuzhuanye=="zylx03"&&bg=="0"){
         alert("分摊信息里办公占比为0,不能添加下级分摊,请返回上级页面进行修改");
         return;
       }else if(shuoshuzhuanye=="zylx04"&&xxhzc=="0"){
         alert("分摊信息里信息化支撑占比为0,不能添加下级分摊,请返回上级页面进行修改");
         return;
       }else if(shuoshuzhuanye=="zylx05"&&jstz=="0"){
         alert("分摊信息里建设投资占比为0,不能添加下级分摊,请返回上级页面进行修改");
         return;
       }else if(shuoshuzhuanye=="zylx06"&&dddf=="0"){
         alert("分摊信息里代垫电费占比为0,不能添加下级分摊,请返回上级页面进行修改");
         return;
       }
      // if(sheiebanid==undefined||sheiebanid==""){
        //alert( "监测点ID不能为空!"); 
        //return;
     //  }
		var   type="^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"; 
		//var type ="^[0-9]*[1-9][0-9]*$";
		 var   re   =   new   RegExp(type); 
		 
       if(bili !="" && bili.match(re)==null) 
        { 
         alert( "请输入大于零的数字!"); 
        return;
       }
       if (bili>100){
        alert( "请输入不大于100的数字!"); 
         return;
       }
        var qcbcode = document.getElementById("qcbcode").value;
      var kjkmcode = document.getElementById("kjkmcode").value;
      var zymxcode = document.getElementById("zymxcode").value;
       if(shuoshuzhuanye==null||shuoshuzhuanye==""||shuoshuzhuanye=="0"){alert("所属专业科目不能为空！");return;}
      if(qcbcode==null||qcbcode==""||qcbcode=="0"){alert("全成本科目不能为空！");return;}
      if(kjkmcode==null||kjkmcode==""||kjkmcode=="0"){alert("会计科目不能为空！");return;}
      if(zymxcode==null||zymxcode==""||zymxcode=="0"){alert("专业明细科目不能为空！");return;}
        if(mingcheng1==null||mingcheng1==""){alert("名称不能为空！");return;}
       
       	if(confirm("您将要修改监测信息！确认信息正确！")){
       var sccj = document.getElementById("sccj").value;  
       var zcbh = document.getElementById("zcbh").value;   
       var bccd = document.getElementById("bccd").value;  
       var   bccdtype="^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"; 
       var   bccdre   =   new   RegExp(bccdtype); 
       if(bccd !="" && bccd.match(bccdre)==null) 
        { 
         alert( "请输入数字!"); 
        return;
       }
      var dianbiaoid = document.getElementById("dianbiaoid").value;
      var inbili = document.getElementById("bili").value;
      var sheiebanid = document.getElementById("sheiebanid").value;
     
      if(dianbiaoid!=null && dianbiaoid!=""){
	      var Url=getPublicPath()+"/servlet/EquipmentmanageServlet?action=chekBili&dianbiaoid="+dianbiaoid+"&flg=2&inbili="+inbili+"&shebeiId="+sheiebanid;
	      var result ="";
	      		    Url = encodeURI(Url);
				    new Ajax.Request(Url,{
				      		method : 'post',
							asynchronous: false,
							onComplete : function (resp){//回调
				 			result=resp.responseText;//返回数据
									
				 			   	}
				})
			if (""!=result){
			   alert(result);
			   return; 
			}
      }
      var de="0";//删除时用到的一个标志位 从bean里
       var beizhu = document.getElementById("beizhu").value;  
       params = "&dianbiaoid="+dianbiaoid+"&sheiebanid="+sheiebanid+"&sheiebanid="+sheiebanid+"&mingcheng="+mingcheng+"&guige="+guige+
       "&shuoshuzhuanye="+shuoshuzhuanye+"&shuoshuwangyuan="+shuoshuwangyuan+"&bili="+bili+"&sccj="+sccj+"&zcbh="+zcbh+"&bccd="+bccd
       +"&beizhu="+beizhu+"&qcbcode="+qcbcode+"&kjkmcode="+kjkmcode+"&zymxcode="+zymxcode;
            var Url=getPublicPath()+"/servlet/EquipmentmanageServlet?action=updataSession&roleFlg=PERMISSION_EDIT";
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
		 			}
		 	})
		 	if((undefined!='<%=dianBiaoId%>')&&('null'!='<%=dianBiaoId%>'))
		 	{ 
		 	    window.location.href=path+"/web/equipmentmanage/add.jsp<%=canshuStr%>&sc="+sc+"&yy="+yy+"&bg="+bg+"&xxhzc="+xxhzc+"&jstz="+jstz+"&dddf="+dddf+"&bz="+bz+"&de="+de+"";
		 	}
		 	else{
		 	   window.location.href=path+"/web/equipmentmanage/equipmentmanage.jsp";
		 	}
		    }
		    }//
		    }
	
	}
function  chekId(){
     var sheiebanid = document.getElementById("sheiebanid").value;

     var Url=getPublicPath()+"/servlet/EquipmentmanageServlet?action=chekId&sheiebanid="+sheiebanid;
      		    Url = encodeURI(Url);
			    new Ajax.Request(Url,{
			      		method : 'post',
						asynchronous: false,
						onComplete : function (resp){//回调
			 			  var result=resp.responseText;//返回数据
							 alert(result);	
			 			   	}
			 	})
   }
   function chekname(){
	     var mingcheng = document.getElementById("mingcheng").value
	     var Url=getPublicPath()+"/servlet/EquipmentmanageServlet?action=chekname&mingcheng="+mingcheng;
	      		    Url = encodeURI(Url);
				    new Ajax.Request(Url,{
				      		method : 'post',
							asynchronous: false,
							onComplete : function (resp){//回调
				 			  var result=resp.responseText;//返回数据
								 alert(result);	
				 			   	}
				 	})
   }
   
   
   //下拉框
   
   $(function(){

	$("#tijiaoBtn").click(function(){
		updata();
	});
	
	$("#fanhuiBtn").click(function(){
		Goback();
	});
	$("#liulan1").click(function(){
		getkjkm();
	});
	$("#liulan2").click(function(){
		getqcb();
	});
	$("#idcf").click(function(){
		chekId();
	});
	$("#namecf").click(function(){
		chekname();
	});
});
</script>
</head>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" >
<%
 String zb = request.getParameter("zb");
    String kzb = request.getParameter("kzb");
    System.out.print("占比"+zb+kzb);

 %>
<form name="form1">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
			 <input type="hidden" name="ybi" id="ybi" value=""/>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						  <tr >
					    	<td colspan="4" width="50" >
											 <div style="width:700px;height:50px">
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">增加监测点</span>	
												</div>
											</td>
	    	        </tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
					       	<table width="100%" border="0" cellspacing="1" cellpadding="1">
					       	 <tr bgcolor="F9F9F9">
                                   <td height="19" colspan="4">　<img src="../../images/v.gif" width="15" height="15" /><font size="3">基本信息</font></td>  
                   			 </tr>
							<tr bgcolor="F9F9F9" class="form_label">
							 <td bgcolor="#DDDDDD"><div>名称:</div></td>
			       <td><div id="inputdiv" style="width:150px;height:23px;position:relative;float:left;">
			       			<input type="text" id="mingcheng" name="mingcheng" class="form" value="默认">
			       	   </div> 		
					   <span style="color: #FF0000;font-weight: bold">&nbsp;*</span> 
			       </td>
                    <td bgcolor="#DDDDDD"><div>电表ID:</div></td>
			       <td><input type="text" id="dianbiaoid" name="dianbiaoid" value="<%=dianBiaoId%>" readonly="readonly" class="selected_font1"/>
			       
			      
			    </tr>
			    <tr  bgcolor="F9F9F9" class="form_label">
			     <td bgcolor="#DDDDDD">所属专业</td>
			      
			    <td><select name="shuoshuzhuanye" id="shuoshuzhuanye" style="width:155" class="selected_font" onchange="zhuanye()">
         		<option value="0">请选择</option>             
         		<%
         			ArrayList xiaoqulist = new ArrayList();   //下拉列表
         			xiaoqulist = commBean.getQcb("0",sss);
         			if (xiaoqulist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) xiaoqulist.get(0)).intValue();
         				for (int i = scount; i < xiaoqulist.size() - 1; i += scount) {
         					agcode = (String) xiaoqulist.get(i
         							+ xiaoqulist.indexOf("QCBCODE"));
         					agname = (String) xiaoqulist.get(i
         							+ xiaoqulist.indexOf("QCBNAME"));
         		%>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    	}
                    %>
         	</select><span style="color: #FF0000;font-weight: bold">&nbsp;*</span></td>
         	 <td bgcolor="#DDDDDD"><div>监测点ID:</div></td>
			       <td> <div id="inputdiv" style="width:150px;height:23px;position:relative;float:left;"> 
			       			<input type="text" id="sheiebanid" name="sheiebanid" value="<%=result %>" readonly="readonly" class="selected_font1" />
			       		</div>  	
			       </td>
			    <tr bgcolor="F9F9F9" class="form_label">
			         <td bgcolor="#DDDDDD">全成本</td>
			    <td><select name="qcbcode" id="qcbcode" style="width:155" onchange="qcbjl()" class="selected_font">
         		<option value="0">请选择</option>             
         		<%
         			ArrayList qcblist = new ArrayList();   //下拉列表
         			qcblist = commBean.getQcb(shuoshuzhuanye,s);
         			if (qcblist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) qcblist.get(0)).intValue();
         				for (int i = scount; i < qcblist.size() - 1; i += scount) {
         					agcode = (String) qcblist.get(i
         							+ qcblist.indexOf("QCBCODE"));
         					agname = (String) qcblist.get(i
         							+ qcblist.indexOf("QCBNAME"));
         		%>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    	}
                    %>
         	</select><span style="color: #FF0000;font-weight: bold">&nbsp;*</span></td>
			       <td bgcolor="#DDDDDD"><div>规格:</div></td>
			       <td><input type="text" id="guige" name="guige"class="form"/></td>
		
			    </tr>
			    <tr bgcolor="F9F9F9" class="form_label">
			      
			     
			    </tr>
			    <tr  bgcolor="F9F9F9" class="form_label">
        	       <td bgcolor="#DDDDDD" >会计科目</td>
        	       
			    <td><select name="kjkmcode" id="kjkmcode" onchange="zymx()" style="width:340" class="selected_font">
         		<option value="0">请选择</option>             
         		<%
         			ArrayList kjlist = new ArrayList();   //下拉列表
         			kjlist = commBean.getQcb(qcbcode,s);
         			if (kjlist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) kjlist.get(0)).intValue();
         				for (int i = scount; i < kjlist.size() - 1; i += scount) {
         					agcode = (String) kjlist.get(i
         							+ kjlist.indexOf("QCBCODE"));
         					agname = (String) kjlist.get(i
         							+ kjlist.indexOf("QCBNAME"));
         		%>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    	}
                    %>
         	</select><span style="color: #FF0000;font-weight: bold">&nbsp;*</span></td>
	        	   <td bgcolor="#DDDDDD" class="form_label"><div>所属网元:</div></td>
			       <td><input type="text" id="shuoshuwangyuan" name="shuoshuwangyuan" class="form"></td>
	        	 
         	</tr>
			    <tr bgcolor="F9F9F9" class="form_label">
			    <td bgcolor="#DDDDDD"><div>专业明细:</div></td>
			        <td><select name="zymxcode" id="zymxcode"  style="width:155"  class="selected_font">
         		<option value="0">请选择</option>             
         		<%
         			ArrayList xzymxlist = new ArrayList();   //下拉列表
         			xzymxlist = commBean.getQcb(shuoshuzhuanye,ss);
         			if (xzymxlist != null) {
         				String agcode = "", agname = "";
         				int scount = ((Integer) xzymxlist.get(0)).intValue();
         				for (int i = scount; i < xzymxlist.size() - 1; i += scount) {
         					agcode = (String) xzymxlist.get(i
         							+ xzymxlist.indexOf("QCBCODE"));
         					agname = (String) xzymxlist.get(i
         							+ xzymxlist.indexOf("QCBNAME"));
         		%>
                    <option value="<%=agcode%>"><%=agname%></option>
                    <%
                    	}
                    	}
                    %>
         	</select><span style="color: #FF0000;font-weight: bold">&nbsp;*</span></td>
         	 <td bgcolor="#DDDDDD"><div>生产厂家:</div></td>
			       <td><input type="text" id="sccj" name="sccj" class="form"></td>
         	
			      
			    </tr>
			     <tr bgcolor="F9F9F9" class="form_label">
			      <td bgcolor="#DDDDDD"><div>所属电表占比:</div></td>
			       <!--<td><input type="text" id="bili" name="bili" onblur="Bili()" style="width:30"/>&nbsp;<font color="red">*</font>(可用占比 <label id="ysbili"></label>) </td>
			       -->
			       <td><input type="text" id="bili" name="bili"/>(%)<span style="color: #FF0000;font-weight: bold">&nbsp;*</span></td>
			          <td bgcolor="#DDDDDD"><div>资产编码:</div></td>
			       <td><input type="text" id="zcbh" name="zcbh"  class="form"/></td>
			      
			      
			    </tr>
			     <tr bgcolor="F9F9F9" class="form_label">
			     
			       <td bgcolor="#DDDDDD"><div>备注:</div></td>
			       <td><input type="text" id="beizhu" name="beizhu" class="form"/></td>
			        <td bgcolor="#DDDDDD"><div>标称耗电（度/天）:</div></td>
			       <td><input type="text" id="bccd" name="bccd" class="form"></td>
			    </tr>
			    <tr>
			    
			   
			  
			    
			    </tr>
			    <tr>	   
			   
         		   
         	
         	
         	
         	</tr>
		    <tr>
		     <td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
		    </tr>
		    <tr>
		    <td colspan="4">
		    
	         <div id="fanhuiBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:90px">
				<img alt="" src="<%=request.getContextPath() %>/images/quxiao.png" width="100%" height="100%" />
				<span style="font-size:12px;position: absolute;left:20px;top:7px;color:white">返回</span>
			</div>
			<div id="tijiaoBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:150px">
				<img alt="" src="<%=request.getContextPath() %>/images/tijiao.png" width="100%" height="100%" />
				<span style="font-size:12px;position: absolute;left:27px;top:3px;color:white">提交</span>
			</div>
			</td>
		    </tr>
		    </table>
          </td>
         </tr>
       </table>
       </td>
       </tr>
       </table>
      <input type="hidden" name="s1" id="s1" value="1">
        <input type="hidden" name="q1" id="q1" value="1">
      <input type="hidden" name="k1" id="k1" value="1">
      <input type="hidden" name="z1" id="z1" value="2">
       <input type="hidden" id="zb" name="zb" onblur="Bili()"/>
       <input type="hidden" id="kzb" name="kzb"  onblur="Bili()"/>
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
			var divStr = "<div id='kjkmDiv' name='kjkm' style='position:absolute;width:"+divWidth+"px;height:400px;border:5px #cbd6db groove;left:"+leftSize+"px;top:100px;background-color:green'>";
			divStr += "<iframe src='<%=path%>/web/equipmentmanage/kjkm.jsp' name='' id='' width='"+(divWidth-5)+"px' height=100%></iframe>";
			divStr += "</div>";
			$("body").append(divStr);
		}
		function getqcb(){
			var divWidth = 800;
			var bodyWidth = $("body").innerWidth();
			var leftSize = 0;//div距离左侧的位置
			if(bodyWidth>divWidth)leftSize = (bodyWidth-divWidth)/2;
			var divStr = "<div id='qcbDiv' name='qcb' style='position:absolute;width:"+divWidth+"px;height:400px;border:5px #cbd6db groove;left:"+leftSize+"px;top:100px;background-color:green'>";
			divStr += "<iframe src='<%=path%>/web/equipmentmanage/qcb.jsp' name='' id='' width='"+(divWidth-5)+"px' height=100%></iframe>";
			divStr += "</div>";
			$("body").append(divStr);
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
		
		if(para=="shuoshuzhuanye"){
			XMLHttpReq.onreadystatechange = processResponse_qcb;//指定响应函数
			
		}else if(para=="qcbcode"){
			XMLHttpReq.onreadystatechange = processResponse_kjkm;
		}else if(para=="kjkmcode"){
			XMLHttpReq.onreadystatechange = processResponse_zymx;
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


function processResponse_qcb() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateQcb(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
function processResponse_kjkm() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateKjkm(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}	
		function processResponse_zymx() {
	
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		//alert(XMLHttpReq.status);
    	if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
        	//var res=XMLHttpReq.responseXML.getElementsByTagName("res")[0].firstChild.data;
        	//var res = XMLHttpReq.responseText;
        	var res=XMLHttpReq.responseXML.getElementsByTagName("res");
          updateZymx(res);
                   
        } else { //页面不正常
            window.alert("您所请求的页面有异常。");
        }
    }
}
	
	//所属专业级联	
function zhuanye(){
	var sid = document.form1.shuoshuzhuanye.value;
    var s1=document.form1.s1.value;
    
	if(sid=="0"){
		var shilist = document.all.qcbcode;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=sszy&pid="+sid+"&s1="+s1,"shuoshuzhuanye");
	}
	
}

//全成本级联
function updateQcb(res){
	var shilist = document.all.qcbcode;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
} 
function qcbjl(){
   
	var sid = document.form1.qcbcode.value;
    var q1=document.form1.q1.value;
	if(sid=="0"){ 
		var shilist = document.all.kjkmcode;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=qcb&pid="+sid+"&q1="+q1,"qcbcode");
	}
}


//会计科目
function updateKjkm(res){
	var shilist = document.all.kjkmcode;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
}
function zymx(){
   
	var sid = document.form1.shuoshuzhuanye.value;
  var z1=document.form1.z1.value;
	if(sid=="0"){ 
		var shilist = document.all.zymxcode;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
		sendRequest(path+"/servlet/garea?action=zymx&pid="+sid+"&z1="+z1,"kjkmcode");
	}
}
//zymx
function updateZymx(res){
	var shilist = document.all.zymxcode;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
	}
} 
</script>


