<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.lang.Double" %>

<%
	String path = request.getContextPath();
	String shengId = (String)session.getAttribute("accountSheng");
	String loginName = (String)session.getAttribute("loginName");
	Account account=(Account)session.getAttribute("account");
	String accountname=account.getAccountName();
	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd");
	String entrytime=mat.format(new Date());
	System.out.println("系统时间"+entrytime);
	String jzproperty=request.getParameter("jzproperty");
	if(null==jzproperty){
		
		jzproperty="";
	}
	
%>
<html>
<head>
<title>
addAccount
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
 </style>
 <style type="text/css">
		.seperator{
			width:100%;
			margin-top:10px;
			margin-bottom: -10px;
		}
		.seperator .first{
			width:5%;
			position: relative;
			float: left;
		}
		.seperator span{
			position: relative;
			float:left;
			/*font-family: 微软雅黑;*/
			font-size: 16px;
		}
		.seperator .second{
			width:30%;
			position: relative;
		}
		.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			width:200px;
		}
		.fsx{
			
			font-size: 12px;
			
		}
		
</style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/tx.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script >

var oCalendarEn=new PopupCalendar("oCalendarEn");	//初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEn.Init();


var oCalendarChs=new PopupCalendar("oCalendarChs");	//初始化控件时,请给出实例名称:oCalendarChs
oCalendarChs.weekDaySting=new Array("日","一","二","三","四","五","六");
oCalendarChs.monthSting=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
oCalendarChs.oBtnTodayTitle="今天";
oCalendarChs.oBtnCancelTitle="取消";
oCalendarChs.Init();
</script>
<script language="javascript">
var path = '<%=path%>';
  function JZtypes(sxbzw){   //基站类型/接入网类型
  
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
		}else if((jzpro=="zdsx02"||jzpro=="zdsx05") && yf=="fwlx03"){
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
			var sc=document.form1.sc.value;
			var jstz=document.form1.jstz.value;
			var xxhzc=document.form1.xxhzc.value;
			var bg=document.form1.bg.value;
			var yy=document.form1.yy.value;
			var bu=document.form1.bbu.value;
			var ru=document.form1.rru.value;
			var yd=document.form1.ydshebei.value;
			var gx=document.form1.gxgwsl.value;
			var g21=document.form1.g2xqs.value;
			var g31 = document.form1.g3sqsl.value;
			var yd1=document.form1.ydgxsbsl.value;
			var dx=document.form1.dxgxsbsl.value;
			var dddf=document.form1.dddf.value;
			var ys= document.form1.ysjts.value;
			var wj= document.form1.wjts.value;
			var yybg= document.form1.yybgkt.value;
			var jf= document.form1.jfsckt.value;
			var kt1=document.form1.ktyps.value;
			var kt2=document.form1.kteps.value;
			var kt=document.form1.ktps.value;
			if(sc==""||sc==null){
				document.form1.sc.value="0";
			}
			if(bg==""||bg==null){
				document.form1.bg.value="0";
			}
			if(yy==""||yy==null){
				document.form1.yy.value="0";
			}
			if(xxhzc==""||xxhzc==null){
				document.form1.xxhzc.value="0";
			}
			if(jstz==""||jstz==null){
				document.form1.jstz.value="0";
			}
			if(dddf==""||dddf==null){
				document.form1.dddf.value="0";
			}
			if(bu==""||bu==null){
				document.form1.bbu.value="0";
			}
			if(ru==""||ru==null){
				document.form1.rru.value="0";
			}
			if(yd==""||yd==null){
				document.form1.ydshebei.value="0";
			}
			if(gx==""||gx==null){
				document.form1.gxgwsl.value="0";
			}
			if(g21==""||g21==null){
				document.form1.g2xqs.value="0";
			}
			if(g31==""||g31==null){
				document.form1.g3sqsl.value="0";
			}
			if(yd1==""||yd1==null){
				document.form1.ydgxsbsl.value="0";
			}
			if(dx==""||dx==null){
				document.form1.dxgxsbsl.value="0";
			}
			if(ys==""||ys==null){
				document.form1.ysjts.value="0";
			}
			if(wj==""||wj==null){
				document.form1.wjts.value="0";
			}
			if(yybg==""||yybg==null){
				document.form1.yybgkt.value="0";
			}
			if(jf==""||jf==null){
				document.form1.jfsckt.value="0";
			}
			if(kt1==""||kt1==null){
				document.form1.ktyps.value="0";
			}
			if(kt2==""||kt2==null){
				document.form1.kteps.value="0";
			}
			if(kt==""||kt==null){
				document.form1.ktps.value="0";
			}
			var num=parseFloat(document.form1.jstz.value)+parseFloat(document.form1.xxhzc.value)+parseFloat(document.form1.bg.value)+parseFloat(document.form1.yy.value)+parseFloat(document.form1.sc.value)+parseFloat(document.form1.dddf.value);
			 num=Math.round(num*100)/100;//保留两位小数
			
			 var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd  日期格式
		     var cyt = document.form1.cssytime.value;	//初始使用时间		                      
		     var csds = document.form1.csds.value;	            
			//var stime = document.form1.sydate.value;	// 投入使用时间
			var jskssj = document.form1.jskssj.value;	// 建设开始时间
			var jsjssj = document.form1.jsjssj.value;	//建设结束时间
			
			//alert(jstz);
		
          	if(checkNotnull(document.form1.jzname,"站点名称")&&   //判断是否为空
          			checkNotnull(document.form1.danjia,"单价")&&
          			checkNotnull(document.form1.zlfh,"直流负荷")&&
          			checkNotnull(document.form1.jlfh,"设备交流负荷")&&
          			checkNotnull(document.form1.beilv,"倍率")&&
          			checkNotSelected(document.form1.shi,"城市")&&
          			checkNotSelected(document.form1.xian,"区县")&&
          			checkNotSelected(document.form1.xiaoqu,"乡镇")&&
          			checkNotSelected(document.form1.jztype,"集团报表类型")&&         			
          			checkNotSelected(document.form1.yflx,"用房类型")&&
          			checkNotSelected(document.form1.stationtype,"站点类型")&&
          		    checkNotSelected(document.form1.gdfs,"供电方式")&&
          		    checkNotSelected(document.form1.dfzflx,"电费支付类型")&&
          		    checkNotnull(document.form1.csds,"初始读数")&&
          		    checkNotnull(document.form1.cssytime,"初始使用时间")&&
          			checkNotSelected(document.form1.jzproperty,"站点属性")&&
          			checkNotSelected(document.form1.fkzq,"付款周期")&&
          			checkNotnull(document.form1.edhdl,"额定耗电量")){
          			
          		
          		  if(isNaN(document.form1.jstz.value)==false&&
          		 	 isNaN(document.form1.xxhzc.value)==false&&
          		 	 isNaN(document.form1.bg.value)==false&&
          		     isNaN(document.form1.yy.value)==false&&
          		     isNaN(document.form1.sc.value)==false&&
          		     isNaN(document.form1.dddf.value)==false){     //判断是否是数字，是数字返回false  
          		     
          		 	 if(isNaN(document.form1.danjia.value)==false&&
          		     	isNaN(document.form1.zlfh.value)==false&&
          		  	 	isNaN(document.form1.jlfh.value)==false&&
          		     	isNaN(document.form1.edhdl.value)==false&&
          		     	isNaN(document.form1.lyjhjgs.value)==false&&
          		     	isNaN(document.form1.area.value)==false){
          		     
          		  		if(isNaN(document.form1.beilv.value)==false&&
          		 	 	   isNaN(document.form1.linelossvalue.value)==false&&
          		  	 	   isNaN(document.form1.csds.value)==false){
          		  	 
          		  			if(isNaN(document.form1.zpslzd.value)==false&&
          		  			   isNaN(document.form1.zssl.value)==false&&
          		   			   isNaN(document.form1.kdsbsl.value)==false&&
          		  			   isNaN(document.form1.yysbsl.value)==false){
          		  			   if(jstz=="0"||jstz=="100"){
          		  			   if(reg.exec(jsjssj)||jsjssj==null||jsjssj==""){
          		  				if(reg.exec(jskssj)||jskssj==null||jskssj==""){
          					  //  if(reg.exec(stime)||stime==null||stime==""){
          		 				 if(num>100){
			    					 alert("分摊比例大于100%，请重新输入");
		     					 }else if(num<100){
			     					 alert("分摊比例小于100%，请重新输入");
			     				 } else if(sc<0||jstz<0||xxhzc<0||bg<0||yy<0||dddf<0){
			       					 alert("生成，办公，营业，建设投资，信息化支撑,代垫电费  必须为正数");
			     				 }else if(csds<0){
			     					 alert("初始读数不能为负数，最小为0");
			     				 }else if(isNaN(bu)==true){
			    		 			 alert("拉远bbu必须为数字!");
			    		 		 }else if(isNaN(ru)==true){
			    		 			 alert("远供rru必须为数字")
			    		 		}else if(isNaN(yd)==true){
			    		 			 alert("移动设备数量必须为数字")
			    		 		}else if(isNaN(gx)==true){
			    		 			alert("共享固网设备数量必须为数字")
			     				 }else if(isNaN(g21)==true){
			     					 alert("2G小区数必须为数字");
			     				 }else if(isNaN(g31)==true){
			     					 alert("3G扇区数量必须为数字");
			    		 		}else if(isNaN(yd1)==true){
			     					 alert("移动共享设备数量必须为数字");
			    		 		}else if(isNaN(dx)==true){
			     					 alert("电信共享设备数量必须为数字");
			    		 		}else if(isNaN(ys)==true){
									alert("饮水机台数必须为数字");
								}else if(isNaN(wj)==true){
									alert("微机台数必须为数字");
								}else if(isNaN(yybg)==true){
									alert("营业办公空调数量必须为数字");
								}else if(isNaN(jf)==true){
									alert("机房生产空调数量必须为数字");
								}else if(isNaN(kt1)==true){
									alert("空调一匹数必须为数字");
								}else if(isNaN(kt2)==true){
									alert("空调二匹数必须为数字");
								}else if(isNaN(kt)==true){
									alert("空调匹数必须为数字");
								}else if(!reg.exec(cyt)){
			    					 alert("初始使用时间，请输入正确的日期格式");
			     				 }else{   addzhandian(); 
			     				         showdiv("请稍等.............."); 
			     				  }   
			     
			     				// }else{
			       					//alert("投资使用时间，请输入正确的日期格式");
			      				 //}
			      			   }else{
			       					alert("建设开始时间，请输入正确的日期格式");
			      				}
			      			  }else{
			       					alert("建设结束时间，请输入正确的日期格式");
			      				}
			      			  }else{alert("建设投资有两种情况100和0，请输入正确的数字！")}
			      			}else{alert("您输入站点附加信息有误：   载频数量, 载扇数量,  宽带端口实占数量,  语音端口实占数量，   必须为数字！")}
			    		}else{alert("您输入结算信息有误：   线损值,  倍率,   须为数字！")}
			  		}else{alert("您输入站点信息有误：   单价,  直流负荷,  设备交流负荷,   额定耗电量,  站点面积 、楼宇交换机个数，必须为数字！")}                                       
          		}else{alert("您输入管理及分摊信息有误： 生成，  办公，  营业，  建设投资，  信息化支撑,代垫电费  必须为数字");
          				
			}
            	
        }
          
   }
        	
        	function addzhandian(){
        		if(confirm("您将要添加站点信息！确认信息正确！")){
          		document.form1.action=path+"/servlet/zhandian?action=newaddSite";
							document.form1.submit();
            }
        	}

       
        function retVname(){
        	addzhandian();
        }
       
        function vMobile(){
        	 var accMobile = document.form1.mobile.value;
                  if(accMobile==""){
           	alert("不能为空！")
                   return
          }

           window.open('accountMobile.jsp?accountId=0&accountMobile='+accMobile,'','width=470,height=300,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        
        
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
 function fanhui(){        
      document.form1.action=path+"/web/jizhan/Newsitemanage.jsp";
      document.form1.submit();
  }
 function reset(){
	document.form1.jzname.value="";
	document.form1.address.value="";
	document.form1.fuzeren.value="";
	document.form1.danjia.value="";
	document.form1.area.value="";
	document.form1.edhdl.value="";
	document.form1.jlfh.value="";
	document.form1.zlfh.value="";
	document.form1.jskssj.value="";
	document.form1.jsjssj.value="";
	document.form1.xmh.value="";
	document.form1.zbdyhh.value="";
	document.form1.ydbid.value="";
	document.form1.linelossvalue.value="";
	document.form1.csds.value="";
	document.form1.cssytime.value="";
	document.form1.bm.value="";
	
	document.form1.lyjhjgs.value="0";
	document.form1.zpslzd.value="0";
	document.form1.zssl.value="0";
	document.form1.beilv.value="1"
	
	document.form1.sc.value="100";
	document.form1.jstz.value="0";
	document.form1.xxhzc.value="0";
	document.form1.bg.value="0";
	document.form1.yy.value="0";
	document.form1.dddf.value="0";
	
	
	document.form1.kdsbsl.value="0";
	document.form1.yysbsl.value="0";
	document.form1.bbu.value="0";
	document.form1.rru.value="0";
	document.form1.ydshebei.value="0";
	document.form1.gxgwsl.value="0";
	document.form1.g2xqs.value="0";
	document.form1.g3sqsl.value="0";
	document.form1.ydgxsbsl.value="0";
	document.form1.dxgxsbsl.value="0";
	document.form1.changjia.value="";
	document.form1.jzlx.value="";
	document.form1.shebei.value="";
	document.form1.twgx.value="";
	document.form1.kts.value="0";
	document.form1.ktzgl.value="0";
	document.form1.ysjts.value="0";
	document.form1.wjts.value="0";
	document.form1.yybgkt.value="0";
	document.form1.jfsckt.value="0";
	document.form1.ktyps.value="0";
	document.form1.kteps.value="0";
	document.form1.ktps.value="0";
 }
    $(function(){
		$("#cancelBtn").click(function(){
			fanhui();
		});
		$("#resetBtn").click(function(){
				//$.each($("form input[type='text']"),function(){
				//	$(this).val("");
				//})
			reset();
		});
		$("#saveBtn").click(function(){
			saveAccount();	
		});
		$("#liulan").click(function(){
			liulan();
		});
		$("#ming").click(function(){
			 document.getElementById("jznamezs").style.display="block";
		});
	});
		
</script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;" >
<form action="" name="form1" method="POST">
  
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" >
		
	                      <tr><td>
	                      <table width="100%"  border="0" cellspacing="0" cellpadding="0" align="left">
	                        <tr>
							<td colspan=1 width="700" background="<%=path%>/images/btt2.bmp" height=37 ><span style="color:black;font-weight:bold;font-size=15;">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 添加站点</span></td>
							<input type="hidden" name="accountname" value="<%=accountname %>"/>
							<td width="380">&nbsp;</td>
							</tr>
							</table>
							</td>
						</tr>
			<tr class="form_label">
				<td colspan="3">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
						<tr>
								<td>
									<table width="100%" border="0" align="right" cellpadding="1" cellspacing="1">
										<tr>
											<td height="49" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="1" cellpadding="1">

			
			<tr bgcolor="F9F9F9" class="form_label">
                      <td height="19" colspan="4" ><img src="../../images/v.gif" width="15" height="15" />基础条件　</td>
                    </tr>
             
            
      <tr class="form_label">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">市：</div>
         </td>
         <td width="25%">
         <select name="shi" style="width:130;" onchange="changeCity()"()>
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
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">区、县：</div>
         </td>
         <td width="26%">
         <select name="xian" style="width:130" onchange="changeCountry()">
         		<option value="0">请选择</option>
         	</select><span class="style1">&nbsp;*</span>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">乡镇：</div>
         </td>
         <td width="26%">
         <select name="xiaoqu" id="xiaoqu" style="width:130">
         		<option value="0">请选择</option>
         	</select><span class="style1">&nbsp;*</span>
         </td>
      </tr>
      
      <tr class="form_label">
         
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left" >站点属性：</div>
         </td>
         <td width="25%">
         	<select name="jzproperty" style="width:130" onchange="JZtypes(1)" title="必填项，根据下拉值选择填写">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdsx = new ArrayList();
	         	zdsx = ztcommon.getSelctOptions("ZDSX");
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
		
         <td height="8%" bgcolor="#DDDDDD" width="15%">
         <div align="left" >集团报表类型：</div>
         </td>
         <td width="25%">
         	<select name="jztype" style="width:130" title="必填项，根据下拉值选择填写，相应类型生成对应报表，包括：IDC机房 对应报表为 IDC用电量汇总表和IDC机房节能技术应用情况汇总表，基站 对应报表为 地市基站用电量信息汇总表和基站用电量汇总分析表，通信机房 对应的报表为 通信局房用电量汇总表和通信机房环境温度管理节能情况汇总表，接入网 对应的报表为 地市接入网机房用电量信息汇总">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList zdtype = new ArrayList();
	         	zdtype = ztcommon.getSelctOptions("ZDLX");
	         	if(zdtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)zdtype.get(0)).intValue();
	         		for(int i=cscount;i<zdtype.size()-1;i+=cscount)
                    {
                    	code=(String)zdtype.get(i+zdtype.indexOf("CODE"));
                    	name=(String)zdtype.get(i+zdtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left" >站点类型：</div>
         </td>
         <td width="25%">
         	<select name="stationtype" style="width:130" onchange="kzinfo()" title="必填项，根据下拉值选择填写">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList stationtype = new ArrayList();
         		stationtype = ztcommon.getSelctOptions(jzproperty);
	         	if(stationtype!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)stationtype.get(0)).intValue();
	         		for(int i=cscount;i<stationtype.size()-1;i+=cscount)
                    {
                    	code=(String)stationtype.get(i+stationtype.indexOf("CODE"));
                    	name=(String)stationtype.get(i+stationtype.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         </td>
      </tr>
      
      <tr class="form_label">
         <td height="8%" bgcolor="#DDDDDD" width="15%" ><div align="left" id="ming" >站点名称：</div>
         </td>
         <td width="25%"><input type="text" name="jzname" value="" style="width:130px" title="必填项,根据站点所在建筑物名称填写 例如：软件科技园D座楼"/><span class="style1">&nbsp;*</span></td>
          <%--<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点别名：</div></td>
         <td width="25%">
         <input type="text" name="bieming" value="" style="width:130px" title="选填项，根据站点所在建筑物名称自定义填写"/></td>				     
        --%><%--<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">归属方：</div>
				         </td>
				         <td width="25%">
				         	<select name="gsf" style="width:130" title="必填项，根据站点的支付方确定选择下拉填写，例如：付费方不经报账有地市自行缴纳费用 则为 市本部，其他 无此种类似情况，则视为标准即可。">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList gsflist = new ArrayList();
					         		gsflist = ztcommon.getSelctOptions("gsf");
						         	if(gsflist!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)gsflist.get(0)).intValue();
						         		for(int i=cscount;i<gsflist.size()-1;i+=cscount)
					                    {
					                    	code=(String)gsflist.get(i+gsflist.indexOf("CODE"));
					                    	name=(String)gsflist.get(i+gsflist.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						       </select><span class="style1">&nbsp;*</span>
				              </td>
      --%><td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">是否标杆：</div>
         </td>
         <td width="25%"><select name="bgsign" style="width:130" title="选填项，是否设置此站点为标杆站">
         		<option value="0">否</option>
         		<option value="1">是</option>
         		
         	</select>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">地址：</div>
         </td>
         <td width="25%"><input type="text" name="address" value="" style="width:130px" title="选填项，站点所在的具体物理位置 "/></td>
      </tr><%--
     
      <tr class="form_label">
      	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">是否标杆：</div>
         </td>
         <td width="25%"><select name="bgsign" style="width:130" title="选填项，是否设置此站点为标杆站">
         		<option value="0">否</option>
         		<option value="1">是</option>
         		
         	</select>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">是否节能：</div>
         </td>
         <td width="25%">
         	<select name="jnglmk" style="width:130" title="选填项，是否节能设备">
         		<option value="0">否</option>
         		<option value="1">是</option>
         		
         	</select>
         </td>
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">地址：</div>
         </td>
         <td width="25%"><input type="text" name="address" value="" style="width:130px" title="选填项，站点所在的具体物理位置 "/></td>
      </tr>
    
      --%><tr class="form_label">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">供电方式：</div>
         </td>
         <td width="25%">
         	<select name="gdfs" style="width:130" title="必填项，根据下拉值选择填写">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList gdfs = new ArrayList();
	         	gdfs = ztcommon.getSelctOptions("GDFS");
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

         	</select><span class="style1">&nbsp;*</span>
         	
         </td>
         	<td height="8%" bgcolor="#DDDDDD" width="15%">
         	<div align="left" >用房类型：</div>
         </td>
         <td width="26%">  
         		
         		<select name="yflx" style="width:130" onchange="JZtypes(2)" title="根据下拉值选择填写，用房类型包括：砖混、彩钢板+直流负荷 来判断基站或接入网的22个基站类型和接入网类型">
         		<option value="fwlx03">请选择</option>
         		<%
	         	ArrayList fwlx = new ArrayList();
	         	fwlx = ztcommon.getSelctOptions("FWLX");
	         	if(fwlx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)fwlx.get(0)).intValue();
	         		for(int i=cscount;i<fwlx.size()-1;i+=cscount)
                    {
                    	code=(String)fwlx.get(i+fwlx.indexOf("CODE"));
                    	name=(String)fwlx.get(i+fwlx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点面积（㎡）：</div>
         </td>
         <td width="25%">
         	<input type="text" name="area" style="width:130px" title="选填项，根据站点所在地的具体面积填写 单位：平方米"/>
       
      </tr>
    
   
    
      <tr class="form_label">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点责任人：</div>
         </td>
         <td width="25%">
         	<input type="text" name="fuzeren" value="" style="width:130px" title="选填项，站点具体负责的人员"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">单价：</div>
         </td>
         <td width="25%">
         	<input type="text" name="danjia" value="" style="width:130px" title="必填项，根据站点的单价填写"/>
         	<span class="style1">*</span>
         </td>
        <td colspan="2">
         <div id="JZLXKZ"  style="width:100%;height:100%;display:none;">
          <table width="100%" cellspacing="0" cellpadding="0">
          	<tr>
               <td height="8%" bgcolor="#DDDDDD" width="15%"  class="form_label">
               		<div align="left">基站类型1：</div>
         		</td>
		         <td width="25%">  
		         		<select name="jzxlx" style="width:130px" onchange="JZtypes(2)">
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList jzxlx = new ArrayList();
			         	jzxlx = ztcommon.getSelctOptions("XLX");
			         	if(jzxlx!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)jzxlx.get(0)).intValue();
			         		for(int i=cscount;i<jzxlx.size()-1;i+=cscount)
		                    {
		                    	code=(String)jzxlx.get(i+jzxlx.indexOf("CODE"));
		                    	name=(String)jzxlx.get(i+jzxlx.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
		         	</select><span class="style1">&nbsp;*</span>
		         </td>
		         </tr>
		         </table>
         </div>
         </td>
      </tr>
      <tr class="form_label">
      	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点产权：</div>
				         </td>
				         <td width="25%">
				         	<select name="zdcq" style="width:130">
					         		<option value="zdcq04">其他</option>
					         		<%
						         	ArrayList zdcqlist = new ArrayList();
						         	zdcqlist = ztcommon.getSelctOptions("ZDCQ");
						         	if(zdcqlist!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)zdcqlist.get(0)).intValue();
						         		for(int i=cscount;i<zdcqlist.size()-1;i+=cscount)
					                    {
					                    	code=(String)zdcqlist.get(i+zdcqlist.indexOf("CODE"));
					                    	name=(String)zdcqlist.get(i+zdcqlist.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						       </select>
				              </td>
				                    	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">共享信息：</div>
				         </td>
				             <td width="25%">
				         	<select name="gxxx" style="width:130">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList gxxx = new ArrayList();
						         	gxxx = ztcommon.getSelctOptions("gxxx");
						         	if(gxxx!=null){
						         		String code1="",name1="";
						         		int cscount = ((Integer)gxxx.get(0)).intValue();
						         		for(int i=cscount;i<gxxx.size()-1;i+=cscount)
					                    {
					                    	code1=(String)gxxx.get(i+gxxx.indexOf("CODE"));
					                    	name1=(String)gxxx.get(i+gxxx.indexOf("NAME"));
					                    %>
					                    <option value="<%=code1%>"><%=name1%></option>
					                    <%}
						         	}
						         %>
						       </select>
				              </td>
				     
				               

                <td colspan="2">
         <div id="JFLXKZ"  style="width:100%;height:100%;display:none;">
          <table width="100%" cellspacing="0" cellpadding="0">
            <tr class="form_label">
               <td bgcolor="#DDDDDD" width="55%" height="8%">
               		<div align="left">局房类型：</div>
         		</td>
		         <td width="25%">  
		         		<select name="jflx" style="width:130px">
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList jflx = new ArrayList();
			         	jflx = ztcommon.getSelctOptions("JFLX");
			         	if(jflx!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)jflx.get(0)).intValue();
			         		for(int i=cscount;i<jflx.size()-1;i+=cscount)
		                    {
		                    	code=(String)jflx.get(i+jflx.indexOf("CODE"));
		                    	name=(String)jflx.get(i+jflx.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
		         	</select><span class="style1">&nbsp;*</span>
		         </td>
		         </tr>
		         </table>
         </div>
         <div id="JRWTYPE"  style="width:100%;height:100%;display:none;">
          <table width="100%" cellspacing="0" cellpadding="0">
            <tr class="form_label">
               <td bgcolor="#DDDDDD" width="55%" height="8%">
               		<div align="left">接入网类型：</div>
         		</td>
		         <td width="25%">  
		         		<select name="jrwtype" style="width:130px" onchange="JZtypes(2)">
		         		<option value="0">请选择</option>
		         		<%
			         	ArrayList jrwtype = new ArrayList();
		         		jrwtype = ztcommon.getSelctOptions("JRWLX");
			         	if(jflx!=null){
			         		String code="",name="";
			         		int cscount = ((Integer)jrwtype.get(0)).intValue();
			         		for(int i=cscount;i<jrwtype.size()-1;i+=cscount)
		                    {
		                    	code=(String)jrwtype.get(i+jrwtype.indexOf("CODE"));
		                    	name=(String)jrwtype.get(i+jrwtype.indexOf("NAME"));
		                    %>
		                    <option value="<%=code%>"><%=name%></option>
		                    <%}
			         	}
			         %>
		         	</select><span class="style1">&nbsp;*</span>
		         </td>
		         </tr>
		         </table>
              </div>
             </td>
         </tr>
           <tr class="form_label">
				       
		 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">额定耗电量（度/天）：</div>
         </td>
         <td width="25%">
           <input type="text" name="edhdl" value="" style="width:130px" title="选填项，填写之后，市级二级审核，会根据审核条件“本次电量上下浮动超过站点额定耗电量计算值的XX%”进行判断，判断通不过会进入市级二级审核" /><span class="style1">&nbsp;*</span></td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">设备交流负荷（A）：</div>
         </td>
         <td width="25%"><input type="text" name="jlfh" value="" style="width:130px" /><span class="style1">&nbsp;*</span></td>
         
         <!--<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">标杆类型标号:</div>
         </td>
         <td width="25%">
				         	<select name="signtypenum" style="width:130">
					         		<option value="0">请选择</option>
					         		<%
			                       ArrayList signtypenum = new ArrayList();
						         	signtypenum = ztcommon.getSigntypenum();
						         	if(signtypenum!=null){
						         		String id="",name="";
						         		int cscount = ((Integer)signtypenum.get(0)).intValue();
						         		for(int i=cscount;i<signtypenum.size()-1;i+=cscount)
					                    {
					                    	id=(String)signtypenum.get(i+signtypenum.indexOf("ID"));
					                    	name=(String)signtypenum.get(i+signtypenum.indexOf("NAME"));
					                    %>
					                    <option value="<%=id%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						       </select>
				              </td>	      
				      -->
			 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">直流负荷（A）：</div></td>
				         	
			<td width="25%">
			<input type="text" name="zlfh" value="" onchange="JZtypes(2)" style="width:130px" title="直流负荷+用房类型确定此站点的基站类型（22个类型）或者接入网（12个类型）"/><span class="style1">*</span></td>
				         	      
			</tr>
		 
			   <tr class="form_label">
		 		<%--<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">投入使用时间：</div>
                </td>
               <td width="25%"><input type="text" name="sydate" value="<%=entrytime %>" style="width:130px" onFocus="getDateString(this,oCalendarChs)"/></td>
			   --%><td height="8%" bgcolor="#DDDDDD" width="15%">楼宇交换机个数：</td>
        	    <td width="25%"><input type="text" name="lyjhjgs" value="0" style="width:130px" /></td>
               <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点启用状态：</div>
               </td>
               <td width="25%">
         	    <select name="qyzt" style="width:130">
         		<option value="1">是</option>
         		<option value="0">否</option>
         	   </select>
              </td>
              <td height="8%" bgcolor="#DDDDDD" width="15%">项目号</td>
				<td width="25%">
          			<input type="text" name="xmh" value="" style="width:130px" />
         		</td>
			</tr>
						<tr class="form_label">
			
				<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">建设开始时间：</div></td>
        		<td width="25%">
          			<input type="text" name="jskssj" value="" style="width:130px" onFocus="getDateString(this,oCalendarChs)" title="必填项，初始的电表使用时间" />
         		</td>
         		<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">建设结束时间：</div></td>
        		<td width="25%">
          			<input type="text" name="jsjssj" value="" style="width:130px" onFocus="getDateString(this,oCalendarChs)" title="必填项，初始的电表使用时间" />
         		</td>
				<%--<td height="8%" bgcolor="#DDDDDD" width="15%">项目号</td>
				<td width="25%">
          			<input type="text" name="xmh" value="" style="width:130px" />
         		</td>
			--%></tr>
			<tr class="form_label">
				<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">远供电：</div></td>
                <td width="25%">
         	    	<select name="ygd" style="width:130">
         	    	    <option value="0">否</option>
         				<option value="1">是</option>
         				
         	   		</select>
                </td><%--
                <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">远受电：</div></td>
                <td width="25%">
         	    	<select name="ysd" style="width:130">
         	    		<option value="0">否</option>
         				<option value="1">是</option>
         				
         	   		</select>
                </td>
			--%></tr>
			
			
			
			
			<tr class="form_label"><td>&nbsp;</td></tr>  
			<tr bgcolor="F9F9F9" class="form_label">
                     <td height="19" colspan="4" ><img src="../../images/v.gif" width="15" height="15" />结算信息　</td>
            </tr>	      
			    
		<tr class="form_label">
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电费支付类型：</div>
         </td>
         <td width="25%">
         	<select name="dfzflx" style="width:130" title="必填项，根据电费支付类型在下拉值中进行选择，其中月结 表示“普通后付费站点的电表” 插卡、预支、合同 表示“预付费的三种类型”">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList dfzflx = new ArrayList();
	         	dfzflx = ztcommon.getSelctOptions("dfzflx");
	         	if(dfzflx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)dfzflx.get(0)).intValue();
	         		for(int i=cscount;i<dfzflx.size()-1;i+=cscount)
                    {
                    	code=(String)dfzflx.get(i+dfzflx.indexOf("CODE"));
                    	name=(String)dfzflx.get(i+dfzflx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	
         	</select>
         	<span class="style1">&nbsp;*</span>
         	</td>	
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">付款方式：</div>
         </td>
         <td width="25%">
         	<select name="fkfs" style="width:130">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList fkfs = new ArrayList();
	         	fkfs = ztcommon.getSelctOptions("FKFS");
	         	if(fkfs!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)fkfs.get(0)).intValue();
	         		for(int i=cscount;i<fkfs.size()-1;i+=cscount)
                    {
                    	code=(String)fkfs.get(i+fkfs.indexOf("CODE"));
                    	name=(String)fkfs.get(i+fkfs.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         	</td>
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">付款周期（月）：</div>
         </td>
         <td width="26%">
         		<select name="fkzq" style="width:130" title="必填项，根据下拉值来选择付款的周期">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList fkzq = new ArrayList();
	         	fkzq = ztcommon.getSelctOptions("FKZQ");
	         	if(fkzq!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)fkzq.get(0)).intValue();
	         		for(int i=cscount;i<fkzq.size()-1;i+=cscount)
                    {
                    	code=(String)fkzq.get(i+fkzq.indexOf("CODE"));
                    	name=(String)fkzq.get(i+fkzq.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         </td>
         </tr>
         <tr class="form_label">
           <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">自报电用户号：</div>
         </td>
         <td width="25%">
         	<input type="text" name="zbdyhh" value=""  class="form" style="width:130px"/>
         </td>
         
         <%--<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">套表计费：</div>
         
         </td>
         <td width="25%">
         	<select name="watchcost" style="width:130">
         	    <option value="0">请选择</option>
         		<option value="1">否</option>
         		<option value="2">是</option>
         	</select>
         </td>
          --%><td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">倍率：</div>
         </td>
         <td width="25%"><input type="text" name="beilv" value="1"  class="form" style="width:130px" title="必填项，根据电表的实际情况进行填写"/>
         <span class="style1">*</span>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">原电表ID：</div>
         </td>
         <td width="26%">
         		<input type="text" name="ydbid" value=""  class="form" style="width:130px"/>
         </td>
         </tr>
      <tr class="form_label">
	     <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">线损类型：</div>
         </td>
         <td width="25%">
         	<select name="linelosstype" style="width:130" onchange="xsl()">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList xslx = new ArrayList();
         		xslx = ztcommon.getSelctXslx("xslx");
	         	if(xslx!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)xslx.get(0)).intValue();
	         		for(int i=cscount;i<xslx.size()-1;i+=cscount)
                    {
                    	code=(String)xslx.get(i+xslx.indexOf("CODE"));
                    	name=(String)xslx.get(i+xslx.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         	}
	         %>
         	</select>
         	</td>
             <td height="8%" bgcolor="#DDDDDD" width="15%" ><div align="left" id="xs">线损值：</div>
         </td>
         <td width="25%"><input type="text" name="linelossvalue" value=""  class="form" style="width:130px"/></td><%--
     
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电表用途：</div>
         </td>
         <td width="25%">
        
         <select name="dbyt" style="width:130" title="必填项，根据电表的用途在下拉值中进行选择，其中 管理 表示“不涉及费用，只做抄表的站点电表” 结算 表示“涉及费用支出的站点电表”">
         	    <option value="dbyt01">结算</option>
         	</select>
         	</td>
      --%></tr>
      <tr class="form_label">
      	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">初始读数：</div>
         </td>
         <td width="26%">
         		<input type="text" name="csds" value=""  class="form" style="width:130px" title="必填项，初始的电表读数"/>
         		<span class="style1">&nbsp;*</span>
         </td>
       <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">初始使用时间：</div>
         </td>
         <td width="25%">
          <input type="text" name="cssytime" value="" style="width:130px" onFocus="getDateString(this,oCalendarChs)" title="必填项，初始的电表使用时间" />
          <span class="style1">&nbsp;*</span>
         </td>
        
        <%--<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">原电表ID：</div>
         </td>
         <td width="26%">
         		<input type="text" name="ydbid" value=""  class="form" style="width:130px"/><span class="style1">&nbsp;*</span>
         </td>
      --%></tr>
      <tr>
      <td>&nbsp;</td>
      
      </tr>
      <tr class="form_label">
      <td height="19" colspan="4" ><img src="../../images/v.gif" width="15" height="15" />管理及分摊　</td>
      
      </tr>
      <tr class="form_label">
      <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">管理电表：</div>
         </td>
         <td width="25%"><input type="checkbox" name="gldb" checked="true" />
         </td>
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">生产(%)：</div>
         </td>
         <td width="25%"><input type="text" name="sc" value="100" style="width:130px"/></td>
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">信息化支撑(%)：</div>
         </td>
         <td width="25%"><input type="text" name="xxhzc" value="0" style="width:130px"/></td>
      </tr>
      <tr class="form_label">
      	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">办公(%)：</div>
         </td>
         <td width="25%"><input type="text" name="bg" value="0" style="width:130px"/></td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">营业(%)：</div>
         </td>
         <td width="25%"><input type="text" name="yy" value="0" style="width:130px"/></td>
         
            <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">建设投资(%)：</div>
         </td>
         <td width="25%"><input type="text" name="jstz" value="0" style="width:130px"/></td>
      </tr>  
      <tr class="form_label">
      	 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">代垫电费(%)：</div></td>
         <td width="25%"><input type="text" name="dddf" value="0" style="width:130px"/></td>
      </tr> 
      
      
   <tr class="form_label"><td>&nbsp;</td></tr>  
 

   <tr><td colspan="24"><div style="width:100%;"><table width="100%"><tr><td colspan="24">
   <div class="Txx"><table>
			<tr bgcolor="F9F9F9" class="form_label">
                     <td height="19" colspan="4" ><img src="../../images/v.gif" width="15" height="15" />站点附加信息<a href="#" onclick="openShutManager(this,'fs',false,'点击关闭','点击展开')">点击展开</a>　</td>
           			
            </tr>
            </table>
          </div>
          
          
           <div id="fs" class="fs" align="center" style="display:none;">
           <table width="100%">
			    <tr class="form_label">
			    <td height="8%" bgcolor="#DDDDDD" width="15%">地域属性：
              	</td>
         		<td width="25%">
         		<select name="dytype" style="width:130">
         		<option value="0">请选择</option>
                <%
	         	ArrayList dy = new ArrayList();
         		dy = ztcommon.getSelctXslx("dytype");
	         	if(dy!=null){
	         		String code="",name="";
	         		int cscount = ((Integer)dy.get(0)).intValue();
	         		for(int i=cscount;i<dy.size()-1;i+=cscount)
                    {
                    	code=(String)dy.get(i+dy.indexOf("CODE"));
                    	name=(String)dy.get(i+dy.indexOf("NAME"));
                    %>
                    <option value="<%=code%>"><%=name%></option>
                    <%}
	         		}
	         	%>
         		</select>
         		</td> 
 
         		<td height="8%" bgcolor="#DDDDDD" width="15%">2G:
        		 </td>
        		 <td width="25%">
         	  	 <select name="g2" style="width:130">
         		 <option value="0">否</option>
         		 <option value="1">是</option>
         		
         		</select>
         		</td>
       		  <td height="8%" bgcolor="#DDDDDD" width="15%">3G：
         	  </td>
         	 <td width="25%">
         	 <select name="g3" style="width:130">
         		<option value="0">否</option>
         		<option value="1">是</option>
         		
         	 </select>
             </td>  
			</tr>
			<tr class="form_label">
				<td height="8%" bgcolor="#DDDDDD" width="15%">宽带设备：
        		 </td>
        		 <td width="25%">
         	  	 <select name="kdsb" style="width:130">
         		 <option value="0">无</option>
         		 <option value="1">有</option>
         		
         		</select>
         		</td>
       		  <td height="8%" bgcolor="#DDDDDD" width="15%">语音设备：
         	  </td>
         	 <td width="25%">
         	 <select name="yysb" style="width:130">
         		<option value="0">无</option>
         		<option value="1">有</option>
         		
         	 </select>
             </td>  
			<td height="8%" bgcolor="#DDDDDD" width="15%">载频数量：</td>
        	 <td width="25%"><input type="text" name="zpslzd" value="0" style="width:130px" /></td>
			
			</tr>
			<tr class="form_label">
				<td height="8%" bgcolor="#DDDDDD" width="15%">载扇数量：
         		 </td>
        		 <td width="25%"><input type="text" name="zssl" value="0" style="width:130px" /></td>
        		 <td height="8%" bgcolor="#DDDDDD" width="15%">宽带端口实占数量：
         		 </td>
        		 <td width="25%"><input type="text" name="kdsbsl" value="0" style="width:130px" /></td>
        		 <td height="8%" bgcolor="#DDDDDD" width="15%">语音端口实占 数量：
         		 </td>
        		 <td width="25%"><input type="text" name="yysbsl" value="0" style="width:130px" /></td>
			
			</tr>
			
			<tr class="form_label">
				<%--<td height="8%" bgcolor="#DDDDDD" width="15%">空调1：
        		 </td>
        		 <td width="25%">
         	  	 <select name="kt1" style="width:130">
         		 <option value="0">无</option>
         		 <option value="1">有</option>
         		
         		 </select>
         		 </td>
       		  	 <td height="8%" bgcolor="#DDDDDD" width="15%">空调2：
         	 	 </td>
         		 <td width="25%">
         		 <select name="kt2" style="width:130">
         		 <option value="0">无</option>
         		 <option value="1">有</option>
         		
         		 </select>
                 </td>  
                 --%>
                 <td height="8%" bgcolor="#DDDDDD" width="15%">空调数：</td>
        		 <td width="25%"><input type="text" name="kts" value="0" style="width:130px" /></td>
        		 <td height="8%" bgcolor="#DDDDDD" width="15%">空调总功率：</td>
        		 <td width="25%"><input type="text" name="ktzgl" value="0" style="width:130px" /></td>
                 <td height="8%" bgcolor="#DDDDDD" width="15%">直供电：
         	 	 </td>
         		 <td width="25%">
         		 <select name="zgd" style="width:130">
         		 <option value="0">否</option>
         		 <option value="1">是</option>
         		
         		 </select>
                 </td>  
                 
                    <tr class="form_label">
                 	<td height="8%" bgcolor="#DDDDDD" width="15%">厂家：
         		 </td>
        		 <td width="25%"><input type="text" name="changjia" value="" style="width:130px" /></td>
        		 <td  height="8%" bgcolor="#DDDDDD" width="15%">基站类型2：</td>
        		 <td width="25%"><input type="text" name="jzlx" value="" style="width:130px" /></td>
						      <!-- 	<div id="div1">
					      			<p><input type="text" class="selected_font" readonly= "true" name="zdlx" value="请选择"/></p>
					      				<ul>
								          <%
									         	ArrayList stationtype1 = new ArrayList();
								         		stationtype1 = ztcommon.getSelctOptions("stationtype");
									         	if(stationtype1!=null){
									         		String code="",name="";
									         		int cscount = ((Integer)stationtype1.get(0)).intValue();
									         		for(int i=cscount;i<stationtype1.size()-1;i+=cscount)
								                    {
								                    	code=(String)stationtype1.get(i+stationtype1.indexOf("CODE"));
								                    	name=(String)stationtype1.get(i+stationtype1.indexOf("NAME"));
								                    %>
								                    <li><input type="checkbox" name="CheckboxGroup1" value="<%=code%>,<%=name%>" id="CheckboxGroup1" /><%=name%></li>
								                    <%}
									         	}
									         %>
									         	<li><input type="button" name="queding" id="queding"value="确定" /><input type="button" name="quxiao" id="quxiao" value="取消" /></li>
		                                         </ul>
									
		                         </div>	 -->
		                          <td  height="8%" bgcolor="#DDDDDD" width="15%">设备类型：</td>
        		 		<td width="25%"><input type="text" name="shebei" value="" style="width:130px" /></td>
        		 		 </tr>  
        		 		 
        		 		<tr class="form_label">
        		 			<td  height="8%" bgcolor="#DDDDDD" width="15%">拉远bbu：</td>
        		 			<td width="25%"><input type="text" name="bbu" value="0" style="width:130px" /></td>
        		 
        		  			<td  height="8%" bgcolor="#DDDDDD" width="15%">远供rru：</td>
        		 			<td width="25%"><input type="text" name="rru" value="0" style="width:130px" /></td>
        		 
        		  			<td  height="8%" bgcolor="#DDDDDD" width="15%">他网共享设备数量：</td>
        		 			<td width="25%"><input type="text" name="ydshebei" value="0" style="width:130px" /></td>
        		 			</tr>
        		 			
        		 			<tr class="form_label">
        		 
        		 				 <td  height="8%" bgcolor="#DDDDDD" width="15%">固移共享设备数量：</td>
        		 					<td width="25%"><input type="text" name="gxgwsl" value="0" style="width:130px" /></td>
        		 					
        		 					<td  height="8%" bgcolor="#DDDDDD" width="15%">他网共享：</td>
        		 					<td width="25%"><input type="text" name="twgx" value="" style="width:130px" /></td>
        		 					
        		 					<td  height="8%" bgcolor="#DDDDDD" width="15%">部门：</td>
        		 					<td width="25%"><input type="text" name="bm" value="" style="width:130px" /></td>
        		 					</tr>
        		 					
        		 			<tr class="form_label">
        		 				<td  height="8%" bgcolor="#DDDDDD" width="15%">2G小区数量：</td>
        		 					<td width="25%"><input type="text" name="g2xqs" value="0" style="width:130px" /></td>
        		 					
        		 					<td  height="8%" bgcolor="#DDDDDD" width="15%">3G扇区数量：</td>
        		 					<td width="25%"><input type="text" name="g3sqsl" value="0" style="width:130px" /></td>
        		 					
        		 					<td  height="8%" bgcolor="#DDDDDD" width="15%">移动共享设备数量：</td>
        		 					<td width="25%"><input type="text" name="ydgxsbsl" value="0" style="width:130px" /></td>
        		 			</tr>
        		 			<tr class="form_label">
        		 				<td  height="8%" bgcolor="#DDDDDD" width="15%">电信共享设备数量：</td>
        		 					<td width="25%"><input type="text" name="dxgxsbsl" value="0" style="width:130px" /></td>
									<td  height="8%" bgcolor="#DDDDDD" width="15%">饮水机台数：</td>
        		 					<td width="25%"><input type="text" name="ysjts" value="0" style="width:130px" /></td>
									<td  height="8%" bgcolor="#DDDDDD" width="15%">微机台数：</td>
        		 					<td width="25%"><input type="text" name="wjts" value="0" style="width:130px" /></td>
        		 			</tr>
							<tr class="form_label">
        		 				<td  height="8%" bgcolor="#DDDDDD" width="15%">营业办公空调数：</td>
        		 					<td width="25%"><input type="text" name="yybgkt" value="0" style="width:130px" /></td>
									<td  height="8%" bgcolor="#DDDDDD" width="15%">机房生产空调数：</td>
        		 					<td width="25%"><input type="text" name="jfsckt" value="0" style="width:130px" /></td>
									<td  height="8%" bgcolor="#DDDDDD" width="15%">空调一匹数：</td>
        		 					<td width="25%"><input type="text" name="ktyps" value="0" style="width:130px" /></td>
        		 			</tr>
							<tr class="form_label">
								<td  height="8%" bgcolor="#DDDDDD" width="15%">空调二匹数：</td>
        		 				<td width="25%"><input type="text" name="kteps" value="0" style="width:130px" /></td>
								<td  height="8%" bgcolor="#DDDDDD" width="15%">空调匹数：</td>
        		 				<td width="25%"><input type="text" name="ktps" value="0" style="width:130px" /></td>
							<tr>
        		 			
		                         
			</tr>
			</table>
			</div>
			</td></tr></table></div></td></tr>
      <tr class="form_label">
      	<td colspan=6>
      		<div align="center" id="IDCJFKZ" style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;<font size="2.5">IDC机房扩展信息</font>&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr class="form_label">
      					<td>出口宽带：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="ckkd" value="" style="width:130px"/></td>
      					<td>已使用面积：&nbsp;&nbsp;<input type="text" name="idcysymj" value="" style="width:130px"/></td>
      					<td>机柜个数：<input type="text" name="jggs" value="" style="width:130px"/></td>
      					<td>已使用个数：&nbsp;<input type="text" name="ysygs" value="" style="width:130px"/></td>	
      				</tr>
      				<tr class="form_label">
      					<td>IDC机房星级：
      						<select name="txj" style="width:130">
      						<option value="0">请选择</option>
				         		<%
					         	ArrayList txj = new ArrayList();
					         	txj = ztcommon.getSelctOptions("TXJ");
					         	if(txj!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)txj.get(0)).intValue();
					         		for(int i=cscount;i<txj.size()-1;i+=cscount)
				                    {
				                    	code=(String)txj.get(i+txj.indexOf("CODE"));
				                    	name=(String)txj.get(i+txj.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         %>
					         </select>
      					</td>
      					<td>IDC节能技术：
      						<select name="jnjslx" style="width:130">
      						<option value="0">请选择</option>
				         		<%
					         	ArrayList jnjslx = new ArrayList();
					         	jnjslx = ztcommon.getSelctOptions("JNJSLX");
					         	if(jnjslx!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)jnjslx.get(0)).intValue();
					         		for(int i=cscount;i<jnjslx.size()-1;i+=cscount)
				                    {
				                    	code=(String)jnjslx.get(i+jnjslx.indexOf("CODE"));
				                    	name=(String)jnjslx.get(i+jnjslx.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         %>
					         </select>
      					</td>		
      					<td>U个数：&nbsp;&nbsp;&nbsp;<input type="text" name="ugs" value="" style="width:130px"/></td>
      					<td>已使用U个数：<input type="text" name="ysyugs" value="" style="width:130px"/></td>	
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr class="form_label">
      	<td colspan=6>
      		<div align="center" id="ZHJFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;<font size="2.5">综合机房扩展信息</font>&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr class="form_label">
      					<td>机房高度：<input type="text" name="jfgd" value="" style="width:130px"></td>
      					<td>设定温度：<input type="text" name="sdwd" value="" style="width:130px"/></td>
      					<td>送风方式：
      						<select name="sffs" style="width:130">
      						<option value="0">请选择</option>
				         		<%
					         	ArrayList sffs = new ArrayList();
					         	sffs = ztcommon.getSelctOptions("SFFS");
					         	if(sffs!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)sffs.get(0)).intValue();
					         		for(int i=cscount;i<sffs.size()-1;i+=cscount)
				                    {
				                    	code=(String)sffs.get(i+sffs.indexOf("CODE"));
				                    	name=(String)sffs.get(i+sffs.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         %>
					         </select>
      					</td>
      					<td>冷源方式：
      						<select name="lyfs" style="width:130">
      						<option value="0">请选择</option>
					         		<%
						         	ArrayList lyfs = new ArrayList();
						         	lyfs = ztcommon.getSelctOptions("LYFS");
						         	if(lyfs!=null){
						         		String code="",name="";
						         		int cscount = ((Integer)lyfs.get(0)).intValue();
						         		for(int i=cscount;i<lyfs.size()-1;i+=cscount)
					                    {
					                    	code=(String)lyfs.get(i+lyfs.indexOf("CODE"));
					                    	name=(String)lyfs.get(i+lyfs.indexOf("NAME"));
					                    %>
					                    <option value="<%=code%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						         </select>
      					</td>		
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr class="form_label">
      	<td colspan=6>
      		<div align="center" id="JZKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;<font size="2.5">基站扩展信息</font>&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr class="form_label">
      					<td>共站情况：<input type="text" name="gzqk" value="" style="width:130px"></td>
      					<td>能耗占比：<input type="text" name="nhzb" value="" style="width:130px"/></td>
      					<td>载频数量：<input type="text" name="zpsl" value="" style="width:130px"/></td>
      					<td >逻辑站数：<input type="text" name="ljzs" value="" style="width:130px"/></td>
      					
      					
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr class="form_label">
      	<td colspan=6>
      		<div align="center" id="GLYFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;<font size="2.5">管理用房扩展信息</font>&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr class="form_label">
      					<td>在岗人员：<input type="text" name="glzgry" value="" style="width:130px"/></td>
      					<td>空调数量：<input type="text" name="glktsl" value="" style="width:130px"/></td>
      					<td>PC数量：<input type="text" name="glpcsl" value="" style="width:130px"/></td>
      				
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr class="form_label">
      	<td colspan=6>
      		<div align="center" id="QDYFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;<font size="2.5">渠道用房扩展信息</font>&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr class="form_label">
      					<td>在岗人员：<input type="text" name="qdzgry" value="" style="width:130px"/></td>
      					<td>空调数量：<input type="text" name="qdktsl" value="" style="width:130px"/></td>
      					<td>PC数量：<input type="text" name="qdpcsl" value="" style="width:130px"/></td>
      					<td>人流量（天/人）：<input type="text" name="rll" value="" style="width:130px"/></td>
      				
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr class="form_label">
      	<td colspan=6>
      		<div align="center" id="FSCYFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;<font size="2.5">通信机房扩展信息</font>&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr class="form_label">
      					<td>在岗人员：<input type="text" name="zgry" value="" style="width:130px"/></td>
      					<td>空调数量：<input type="text" name="ktsl" value="" style="width:130px"/></td>
      					<td>已使用面积：<input type="text" name="ysymj" value="" style="width:130px"/></td>
      					<td>PC数量：<input type="text" name="pcsl" value="" style="width:130px"/></td>
      				    <td>用电类型：
      						<select name="ydlx" style="width:130">
      						<option value="0">请选择</option>
				         		<%
					         	ArrayList ydlx = new ArrayList();
					         	ydlx = ztcommon.getSelctOptions("YDLX");
					         	if(ydlx!=null){
					         		String code="",name="";
					         		int cscount = ((Integer)ydlx.get(0)).intValue();
					         		for(int i=cscount;i<ydlx.size()-1;i+=cscount)
				                    {
				                    	code=(String)ydlx.get(i+ydlx.indexOf("CODE"));
				                    	name=(String)ydlx.get(i+ydlx.indexOf("NAME"));
				                    %>
				                    <option value="<%=code%>"><%=name%></option>
				                    <%}
					         	}
					         %>
					         </select>
      					</td>
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
       
    <tr class="form_label">
        <td colspan="6">
          <div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:14px">
	           <img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>
          </div>
            
          <div id="resetBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:16px">
	           <img src="<%=path %>/images/2chongzhi.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">重置</span>
          </div>
          
          <div id="saveBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:18px">
	           <img src="<%=path %>/images/baocun.png" width="100%" height="100%">
	           <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">保存</span>
          </div>
        </td>
      </tr>



      <%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>

      </table>
											</td>
										</tr>
									</table>
								</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
      <br />
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
 
</table>

		<input type="hidden" name="czzdid" value=""/>
		<input type="hidden" type="text" name="bieming" value="" style="width:130px" />
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


function changeSheng(){
	var sid = document.form1.sheng.value;

	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
	 //alert("11111");
		sendRequest(path+"/servlet/garea?action=sheng&pid="+sid,"sheng");

	}
}
function updateShi(res){
	var shilist = document.all.shi;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
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
function updateQx(res){
	var shilist = document.all.xian;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
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
function updateZd(res){
	var shilist = document.all.xiaoqu;
	shilist.options.length="0";
	shilist.add(new Option("请选项","0"));
	
	for(var i = 0;i<res.length;i+=2){
		shilist.add(new Option(res[i+1].firstChild.data,res[i].firstChild.data));
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


function kzinfo(){
	//var jztype = document.form1.jztype.value;
	var jzproperty = document.form1.jzproperty.value;
	/*if(jztype=="zdlx01"){//IDC机房
		document.getElementById("IDCJFKZ").style.display="block";
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx03"){//综合机房
		document.getElementById("ZHJFKZ").style.display="block";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx08"){//站点
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="block";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx10"){//管理用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="block";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx11"){//渠道用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="block";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(jztype=="zdlx12"){//非生产用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="block";
	}else{
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
		
	}*/
	 if(jzproperty=="zdsx01"){//局房类型
		
		//$(".JFLXKZ").css("display","block");
		document.getElementById("JFLXKZ").style.display="block";
		document.getElementById("JZLXKZ").style.display="none";
		document.getElementById("JRWTYPE").style.display="none";
	}else if(jzproperty=="zdsx02"){//基站类型
		
		//$(".JFLXKZ").css("display","none");
		document.getElementById("JFLXKZ").style.display="none";
		document.getElementById("JZLXKZ").style.display="block";
		document.getElementById("JRWTYPE").style.display="none";
	}else if(jzproperty=="zdsx05"){//基站类型
		
		//$(".JFLXKZ").css("display","none");
		document.getElementById("JFLXKZ").style.display="none";
		document.getElementById("JZLXKZ").style.display="none";
		document.getElementById("JRWTYPE").style.display="block";
	}else{
	
		// $(".JFLXKZ").css("display","none");
	     document.getElementById("JFLXKZ").style.display="none";
		 document.getElementById("JZLXKZ").style.display="none";
		 document.getElementById("JRWTYPE").style.display="none";
	}
}

		function showxuni(){

			if(document.form1.xuni.checked){
				document.getElementById("IDXUNI").style.display="block";
			}else{
				document.getElementById("IDXUNI").style.display="none";
			}
		}
		function liulan(){
		//window.open("zhandianselect.jsp");
		//return;
		var url=path+"/web/jizhan/zhandianselect.jsp"
		//window.open(url);
		//return;
			var obj = new Object();
			obj.mid='mid';
		    var obj=showModalDialog(url,obj,'dialogWidth:900px;dialogHeight:650px;status:no;scroll:no');	
			document.form1.czzdid.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.czzd.value=obj.substring(0,obj.indexOf(","));
			/*
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_area.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_jzlx.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_bgsign.value=obj.substring(0,obj.indexOf(","));
			obj = obj.substring(obj.indexOf(",")+1);
			document.form1.t_bieming.value=obj.substring(obj);
			*/
	}
//-->
</script>

