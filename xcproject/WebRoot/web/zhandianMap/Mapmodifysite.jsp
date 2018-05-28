<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.jizhan.SiteModifyBean" %>
<%@ page import="com.noki.mobi.common.Account" %>
<%
	String path = request.getContextPath();
	String id = request.getParameter("id");
	SiteModifyBean form = new SiteModifyBean();
	String numb1=request.getParameter("nums");
	Account account=(Account)session.getAttribute("account");
	String shengId = (String)session.getAttribute("accountSheng");
	
	String login=account.getAccountId();
	int nums=Integer.parseInt(numb1);
	int numss=nums+1;
	 //id=form.getzdid(numss,login,shengId);
	 	form = form.dbid(id,"");
	String gdbid=form.getDbid();
	System.out.println("电表*****id："+form.getLyjhjgs()+"nums"+nums);
	form = form.getJizhan(id,gdbid);	
	String typename="";
	typename=form.getTypename();
	
	
	String loginName = (String)session.getAttribute("loginName");
	System.out.println("电表*****id："+form.getLyjhjgs()+"shengId"+shengId);
	
	String accountname=account.getAccountName();
	
	
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
	String sc1=form.getSc();//分摊
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
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
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
	function JZtypes(){   //基站类型/接入网类型
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
	            var num=parseFloat(document.form1.jstz.value)+parseFloat(document.form1.xxhzc.value)+parseFloat(document.form1.bg.value)+parseFloat(document.form1.yy.value)+parseFloat(document.form1.sc.value);
			        num=Math.round(num*100)/100;//保留两位小数
			    
			    var reg = /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;//yyyy-mm-dd  日期格式
		        var cyt = document.form1.cssytime.value;			                      
		        var csds = document.form1.csds.value;	
		        var stime = document.form1.sydate.value;	// 投入使用时间
		        
                       if(checkNotSelected(document.form1.shi,"城市")&&
                          checkNotSelected(document.form1.xian,"区县")&&
          				  checkNotSelected(document.form1.xiaoqu,"乡镇")&&
          				  checkNotSelected(document.form1.jzproperty,"站点属性")&&
          				  checkNotnull(document.form1.jzproperty,"站点属性")&&
          				  checkNotSelected(document.form1.jztype,"集团报表类型")&&
          				  checkNotnull(document.form1.jztype,"集团报表类型")&&
                      	  checkNotSelected(document.form1.stationtype,"站点类型")&&
                       	  checkNotnull(document.form1.stationtype,"站点类型")&&
                      	  checkNotnull(document.form1.gsf,"归属方")&&
                      	  checkNotSelected(document.form1.gsf,"归属方")&&
                    	  checkNotnull(document.form1.gdfs,"供电方式")&&
                     	  checkNotSelected(document.form1.gdfs,"供电方式")&&
                    	  checkNotSelected(document.form1.dfzflx,"电费支付类型")&&
                     	  checkNotnull(document.form1.dfzflx,"电费支付类型")&&
                     	  checkNotnull(document.form1.bgsign,"是否标杆")&&
                     	  checkNotnull(document.form1.qyzt,"站点启用状态")&&
                     	  checkNotnull(document.form1.zlfh,"直流负荷")&&
          				  checkNotnull(document.form1.jlfh,"交流负荷")&&
          				  checkNotnull(document.form1.beilv,"倍率")&&
          				  checkNotnull(document.form1.csds,"初始读数")&&
          				  checkNotnull(document.form1.cssytime,"初始使用时间")&&
                     	  checkNotnull(document.form1.danjia,"单价")
                       ){
                        
                        if( isNaN(document.form1.jstz.value)==false&&
          		 	 		isNaN(document.form1.xxhzc.value)==false&&
          		 		    isNaN(document.form1.bg.value)==false&&
          		     		isNaN(document.form1.yy.value)==false&&
          		     		isNaN(document.form1.sc.value)==false){     //判断是否是数字，是数字返回false  
          		     
          		 	 		if( isNaN(document.form1.danjia.value)==false&&
          		     			isNaN(document.form1.zlfh.value)==false&&
          		  	 			isNaN(document.form1.jlfh.value)==false&&
          		     			isNaN(document.form1.edhdl.value)==false&&
          		     			isNaN(document.form1.lyjhjgs.value)==false&&
          		     			isNaN(document.form1.area.value)==false){
          		     
          		  				if( isNaN(document.form1.beilv.value)==false&&
          		 	 	   			isNaN(document.form1.linelossvalue.value)==false&&
          		  	 	  	 		isNaN(document.form1.csds.value)==false){
          		  	 
          		  					if( isNaN(document.form1.zpslzd.value)==false&&
          		  			   			isNaN(document.form1.zssl.value)==false&&
          		   			   			isNaN(document.form1.kdsbsl.value)==false&&
          		  			   			isNaN(document.form1.yysbsl.value)==false){
          		 		  
          		 		 				 if(reg.exec(stime)||stime==null||stime==""){
          		 		  					if(num>100){
			    		  						alert("分摊比例大于100%，请重新输入");
		     			  					  }else if(num<100){
			   			  						alert("分摊比例小于100%，请重新输入");
			             					  }else if(sc<0||jstz<0||xxhzc<0||bg<0||yy<0){
			   			  						alert("生成，办公，营业，建设投资，信息化支撑  必须为正数");
			             					  }else if(csds<0){
			     			 					alert("初始读数不能为负数，最小为0");
			    		 					  }else if(!reg.exec(cyt)){
			    								alert("请输入正确的日期格式");
			    		 					  }else if(confirm("您将要修改站点信息！确认信息正确！")){
                                                   
													document.form1.action=path+"/servlet/zhandian?action=modifySite"
													document.form1.submit()
							    			}
											}else{
												alert("请输入正确的日期格式");
											}
							}else{alert("您输入站点附加信息有误：   载频数量, 载扇数量,  宽带设备数量,  语音设备数量，   必须为数字！")}
			    		}else{alert("您输入结算信息有误：   线损值,  倍率,   须为数字！")}
			  		}else{alert("您输入站点信息有误：   单价,  直流负荷,  交流负荷,   额定耗电量,  站点面积 、楼宇交换机个数，必须为数字！")}                                       
          		}else{alert("您输入管理及分摊信息有误： 生成，  办公，  营业，  建设投资，  信息化支撑,  必须为数字");
       	
			}
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
											  
											  <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">站点信息列表</span>	
												</div>
											</td>
							<td width="500">&nbsp;</td>
						</tr>
			<tr class="form_label">
				<td>站点信息</td>
			</tr>
			
		<tr class="selected_font">
         <td  bgcolor="#DDDDDD" width="15%"><div align="left">市：</div>
         </td>
         <td width="25%">
         <select name="shi" class="selected_font" onchange="changeCity()">
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
                    	System.out.println("------"+sfid+"---"+sfnm);
                    %>
                    <option value="<%=sfid%>"><%=sfnm%></option>
                    <%}
	         	}
	         %>
         	</select><span class="style1">&nbsp;*</span>
         </td>
			<td  bgcolor="#DDDDDD" width="15%"><div align="left">区、县：</div>
         </td>
         <td width="25%">
         <select name="xian" class="selected_font" onchange="changeCountry()">
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
         	</select><span class="style1">&nbsp;*</span>
         </td>
         <td  bgcolor="#DDDDDD" width="15%" class="selected_font"><div align="left">乡镇：</div>
         </td>
         <td width="25%">
         <select name="xiaoqu" id="xiaoqu" class="selected_font" >
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
         	</select><span class="style1">&nbsp;*</span>
         </td>
      </tr>
     
      <tr class="form_label">
         
         <td bgcolor="#DDDDDD" width="15%"><div align="left">站点属性：</div>
         </td>
         <td width="25%">
         	<select name="jzproperty" class="selected_font" onchange="JZtypes()" title="必填项，根据下拉值选择填写">
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
		
         <td  bgcolor="#DDDDDD" width="15%"><div align="left">集团报表类型：</div>
         </td>
         <td width="25%">
         	<select name="jztype" class="selected_font"  onchange="kzinfo()" title="必填项，根据下拉值选择填写，相应类型生成对应报表，包括：IDC机房 对应报表为 IDC用电量汇总表和IDC机房节能技术应用情况汇总表，基站 对应报表为 地市基站用电量信息汇总表和基站用电量汇总分析表，通信机房 对应的报表为 通信局房用电量汇总表和通信机房环境温度管理节能情况汇总表，接入网 对应的报表为 地市接入网机房用电量信息汇总">
         	<option value="0">请选择</option>
         		<%
	         	ArrayList zdtype = new ArrayList();
	         	zdtype = ztcommon.getSelctOptions("zdlx");
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
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点类型：</div>
         </td>
         <td width="25%">
         	<select name="stationtype" class="selected_font" onchange="kzinfo()" title="必填项，根据下拉值选择填写">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList station = new ArrayList();
         		station = ztcommon.getSelctOptions("StationType");
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
      </tr>
		
		<tr class="form_label">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点名称：</div>
         </td>
         <td width="25%"><input type="text" name="jzname" value="<%=form.getJzname() %>" class="selected_font" title="必填项,根据站点所在建筑物名称填写 例如：软件科技园D座楼"/><span class="style1">&nbsp;*</span></td>
          <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点别名：</div></td>
         <td width="25%"><input type="text" name="bieming" value="<%=form.getBieming() %>" class="selected_font"  title="选填项，根据站点所在建筑物名称自定义填写"/></td>				     
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">归属方：</div>
				         </td>
				         <td width="25%">
				         	<select name="gsf" class="selected_font" title="必填项，根据站点的支付方确定选择下拉填写，例如：付费方不经报账有地市自行缴纳费用 则为 市本部，其他 无此种类似情况，则视为标准即可。">
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
      </tr>	
      
       <tr class="form_label">
      	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">是否标杆：</div>
         </td>
         <td width="25%">
         <select name="bgsign" class="selected_font" title="选填项，是否设置此站点为标杆站">
         		<option value="0">否</option>
         		<option value="1">是</option>
         		
         	</select>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">是否节能：</div>
         </td>
         <td width="25%">
         	<select name="jnglmk" class="selected_font" title="选填项，是否节能设备">
         		<option value="0">否</option>
         		<option value="1">是</option>
         		
         	</select>
         </td>
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">地址：</div>
         </td>
         <td width="25%">
         <input type="text" name="address" value="<%=form.getAddress() %>" class="selected_font"  title="选填项，站点所在的具体物理位置"/>
         </td>
      </tr>
      
      <tr class="form_label">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">供电方式：</div>
         </td>
         <td width="25%">
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

         	</select><span class="style1">&nbsp;*</span>
         	
         </td>
         	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">用房类型：</div>
         </td>
         <td width="26%">  
         		<select name="yflx" class="selected_font" onchange="JZtypes()" title="根据下拉值选择填写，用房类型包括：砖混、彩钢板+直流负荷 来判断基站或接入网的22个基站类型和接入网类型">
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
         	<input type="text" name="area" value="<%=form.getArea() %>" class="selected_font" title="选填项，根据站点所在地的具体面积填写 单位：平方米"/>
       
      </tr>
      
       <tr class="form_label">
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点责任人：</div>
         </td>
         <td width="25%">
         	<input type="text" name="fuzeren" value="" class="selected_font" title="选填项，站点具体负责的人员"/>
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">单价：</div>
         </td>
         <td width="25%">
         	<input type="text" name="danjia" value="<%=form.getDanjia() %>" class="selected_font" title="必填项，根据站点的单价填写"/>
         	<span class="style1">*</span>
         </td>
        <td colspan="2">
         <div id="JZLXKZ"  style="width:100%;height:100%;display:none;">
          <table width="100%">
              <tr>
               <td bgcolor="#DDDDDD" width="47%" class="form_label">
               		<div align="left">基站类型：</div>
         		</td>
		         <td>  
		         		<select name="jzxlx" class="selected_font" onchange="JZtypes()">
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
				         	<select name="zdcq" class="selected_font">
					         		<option value="0">请选择</option>
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
         <table width="100%">
            <tr class="form_label">
               <td height="8%" bgcolor="#DDDDDD" width="50%">
               		<div align="left">局房类型：</div>
         		</td>
		         <td>  
		         		<select name="jflx" class="selected_font">
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
          <table width="100%">
            <tr class="form_label">
               <td bgcolor="#DDDDDD" width="47%">
               		<div align="left">接入网类型：</div>
         		</td>
		         <td>  
		         		<select name="jrwtype" class="selected_font" onchange="JZtypes()">
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
         <input type="text" name="edhdl" value="<%=form.getEdhdl() %>" class="selected_font" title="选填项，填写之后，市级二级审核，会根据审核条件“本次电量上下浮动超过站点额定耗电量计算值的XX%”进行判断，判断通不过会进入市级二级审核" />
         </td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">交流负荷（A）：</div>
         </td>
         <td width="25%"><input type="text" name="jlfh" value="" class="selected_font" /><span class="style1">&nbsp;*</span></td>
          <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">直流负荷（A）：</div> </td>
		 <td width="25%">
		 <input type="text" name="zlfh" value="<%=form.getZlfh() %>" onchange="JZtypes()" class="selected_font" title="直流负荷+用房类型确定此站点的基站类型（22个类型）或者接入网（12个类型）"/>
		 <span class="style1">*</span></td>
				         
          <!--<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">标杆类型标号:</div>
         </td>
         <td width="25%">
				         	<select name="signtypenum" style="width:130">
					         		<option value="0">请选择</option>
					         		<%
						         	ArrayList signtypenum = new ArrayList();
						         	signtypenum = ztcommon.getSigntypenum();
						         	if(signtypenum!=null){
						         		String num="",name="";
						         		int cscount = ((Integer)signtypenum.get(0)).intValue();
						         		for(int i=cscount;i<signtypenum.size()-1;i+=cscount)
					                    {
					                    	num=(String)signtypenum.get(i+signtypenum.indexOf("ID"));
					                    	name=(String)signtypenum.get(i+signtypenum.indexOf("NAME"));
					                    %>
					                    <option value="<%=num%>"><%=name%></option>
					                    <%}
						         	}
						         %>
						       </select>
				              </td>	      
	  -->
	  </tr>  
	 <tr class="form_label">
	 	 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">投入使用时间：</div>
         </td>
         <td width="25%"><input type="text" name="sydate" value="<%=form.getSydate() %>" style="width:130px" onFocus="getDateString(this,oCalendarChs)"/></td>
	 	 <td height="8%" bgcolor="#DDDDDD" width="15%">楼宇交换机个数：</td>
         <td width="25%"><input type="text" name="lyjhjgs" value="<%= form.getLyjhjgs()%>" style="width:130px" /></td>
	 	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">站点启用状态：</div></td>
         <td width="25%">
         	    <select name="qyzt" style="width:130">
         		<option value="1">是</option>
         		<option value="0">否</option>
         	   </select>
          </td>
	 </tr>
	  
	  
	  <tr class="form_label">
           <td>&nbsp;</td>
       </tr>
       
       <tr class="form_label">
      <td height="19" colspan="4" ><img src="../../images/v.gif" width="15" height="15" />分摊信息　</td>
      
      </tr>
      <tr class="form_label">
        <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">生产(%)：</div>
         </td>
         <td width="25%"><input type="text" name="sc" value="<%=sc1 %>" style="width:130px"/></td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">办公(%)：</div>
         </td>
         <td width="25%"><input type="text" name="bg" value="<%=bg1 %>" style="width:130px"/></td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">建设投资(%)：</div>
         </td>
         <td width="25%"><input type="text" name="jstz" value="<%=jstz1 %>" style="width:130px"/></td>
      </tr>
      <tr class="form_label">
      	
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">营业(%)：</div>
         </td>
         <td width="25%"><input type="text" name="yy" value="<%=yy1 %>" style="width:130px"/></td>
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">信息化支撑(%)：</div>
         </td>
         <td width="25%"><input type="text" name="xxhzc" value="<%=xxhzc1 %>" style="width:130px"/></td>
      </tr> 
       <tr class="form_label">
           <td>&nbsp;</td>
       </tr> 
	  <tr bgcolor="F9F9F9" class="form_label">
           <td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />结算电表信息　</td>
       </tr>
       <tr class="form_label">
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电费支付类型：</div>
         </td>
         <td width="25%">
         	<select name="dfzflx" class="selected_font" title="必填项，根据电费支付类型在下拉值中进行选择，其中月结 表示“普通后付费站点的电表” 插卡、预支、合同 表示“预付费的三种类型”">
         		<option value="0">请选择</option>
         		<%
	         	ArrayList dfzflx = new ArrayList();
	         	dfzflx = ztcommon.getSelctOptions("DFZFLX");
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
         	<select name="fkfs" class="selected_font">
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
         		<select name="fkzq" class="selected_font" title="必填项，根据下拉值来选择付款的周期">
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
         	</select>
         </td>
         </tr>
         <tr class="form_label">
           <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">自报电用户号：</div>
         </td>
         <td width="25%">
        
         	<input type="text" name="zbdyhh" value="<%=form.getZbdyhh() !=null ? form.getZbdyhh():"" %>"   class="selected_font"/>
         </td>
         
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">套表计费：</div>
         
         </td>
         <td width="25%">
         	<select name="watchcost" class="selected_font">
         	    <option value="0">请选择</option>
         		<option value="1">否</option>
         		<option value="2">是</option>
         	</select>
         </td>
          <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">倍率：</div>
         </td>
         <td width="25%"><input type="text" name="beilv" value="<%=form.getBeilv() !=null ? form.getBeilv():""%>"  class="selected_font" title="必填项，根据电表的实际情况进行填写"/>
         <span class="style1">*</span>
         </td>
         </tr>
         
         <tr class="form_label">
	     <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">线损类型：</div>
         </td>
         <td width="25%">
         	<select name="linelosstype" class="selected_font" onchange="xsl()">
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
             <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left" id="xs">线损值：</div>
         </td>
         <td width="25%"><input type="text" name="linelossvalue" value="<%=form.getLinelossvalue() !=null ? form.getLinelossvalue():""%>"   class="selected_font"/></td>
     
         <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">电表用途：</div>
         </td>
         <td width="25%">
        
         <select name="dbyt" class="selected_font" title="必填项，根据电表的用途在下拉值中进行选择，其中 管理 表示“不涉及费用，只做抄表的站点电表” 结算 表示“涉及费用支出的站点电表”">
         	    <option value="dbyt01">结算</option>
         	</select>
         	</td>
      </tr>	 
       <tr class="form_label">
      	<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">初始读数：</div>
         </td>
         <td width="26%">
         		<input type="text" name="csds" value="<%=form.getCsds()!=null ? form.getCsds():"0" %>"  class="form" style="width:130px" title="必填项，初始的电表读数"/>
         		<span class="style1">&nbsp;*</span>
         </td>
       <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">初始使用时间：</div>
         </td>
         <td width="25%">
         <input type="text" name="cssytime" value="<%=form.getCssytime()!=null ? form.getCssytime():"" %>" style="width:130px" onFocus="getDateString(this,oCalendarChs)" title="必填项，初始的电表使用时间" />
         <span class="style1">&nbsp;*</span>
         </td>
       <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">原电表ID：</div>
         </td>
         <td width="26%">
         		<input type="text" name="ydbid" value="<%=form.getYdbid()!=null ? form.getYdbid():"" %>"  class="form" style="width:130px"/>
         </td>
      </tr>
       <tr class="form_label"><td>&nbsp;</td></tr>  
       <tr bgcolor="F9F9F9" class="form_label">
           <td height="19" colspan="4"><img src="../../images/v.gif" width="15" height="15" />站点附属信息</td>
       </tr>
			<!--  <tr bgcolor="F9F9F9" class="form_label">
                     <td height="19" colspan="4" ><img src="../../images/v.gif" width="15" height="15" /><div class="fsxx">站点附属信息</div>　</td>
            </tr>
          -->
            <div class="xx">
			<tr class="form_label">
			    <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">地域属性：</div>
              	</td>
         		<td width="25%">
         		<select name="dytype" class="selected_font">
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
 
         		<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">2G设备：</div>
        		 </td>
        		 <td width="25%">
         	  	 <select name="g2" class="selected_font">
         		 <option value="0">无</option>
         		 <option value="1">有</option>
         		
         		</select>
         		</td>
       		  <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">3G设备：</div>
         	  </td>
         	 <td width="25%">
         	 <select name="g3" class="selected_font">
         		<option value="0">无</option>
         		<option value="1">有</option>
         		
         	 </select>
             </td>  
			</tr>
			<tr class="form_label">
				<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">宽带设备：</div>
        		 </td>
        		 <td width="25%">
         	  	 <select name="kdsb" class="selected_font">
         		 <option value="0">无</option>
         		 <option value="1">有</option>
         		
         		</select>
         		</td>
       		  <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">语音设备：</div>
         	  </td>
         	 <td width="25%">
         	 <select name="yysb" class="selected_font">
         		<option value="0">无</option>
         		<option value="1">有</option>
         		
         	 </select>
             </td>  
			<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">载频数量：</div>
         	</td>
        	 <td width="25%"><input type="text" name="zpslzd" value="<%= form.getZpslzd()%>" class="selected_font" /></td>
			
			</tr>
			<tr class="form_label">
				<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">载扇数量：</div>
         		 </td>
        		 <td width="25%"><input type="text" name="zssl" value="<%= form.getZssl()%>" class="selected_font" /></td>
        		 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">宽带设备数量：</div>
         		 </td>
        		 <td width="25%"><input type="text" name="kdsbsl" value="" class="selected_font" /></td>
        		 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">语音设备数量：</div>
         		 </td>
        		 <td width="25%"><input type="text" name="yysbsl" value="" class="selected_font" /></td>
			
			</tr>
			
			<tr class="form_label">
				<td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">空调1：</div>
        		 </td>
        		 <td width="25%">
         	  	 <select name="kt1" class="selected_font">
         		 <option value="0">无</option>
         		 <option value="1">有</option>
         		
         		 </select>
         		 </td>
       		  	 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">空调2：</div>
         	 	 </td>
         		 <td width="25%">
         		 <select name="kt2" class="selected_font">
         		 <option value="0">无</option>
         		 <option value="1">有</option>
         		
         		 </select>
                 </td>  
                 <td height="8%" bgcolor="#DDDDDD" width="15%"><div align="left">直供电：</div>
         	 	 </td>
         		 <td width="25%">
         		 <select name="zgd" class="selected_font">
         		 <option value="0">否</option>
         		 <option value="1">是</option>
         		
         		 </select>
                 </td>  
			</tr>
			</div>
       <tr>
       	<td>&nbsp;</td>
       </tr>
	<tr class="form_label">
      	<td colspan=6>
      		<div align="center" id="IDCJFKZ" style="display:none;">
      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;<font size="">IDC机房扩展信息</font>&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr class="form_label">
      					<td>出口宽带：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="ckkd" value="<%=form.getCkkd()%>" class="selected_font"></td>
      					<td>已使用面积：&nbsp;&nbsp;<input type="text" name="idcysymj" value="<%=ysymj%>" class="selected_font"/></td>
      					<td>机柜个数：<input type="text" name="jggs" value="<%=form.getJggs()%>" class="selected_font"/></td>
      					<td>已使用个数：<input type="text" name="ysygs" value="<%=form.getYsygs()%>" class="selected_font"/></td>		
      				</tr>
      				<tr class="form_label">
      				<td>IDC机房星级：
      						<select name="txj" class="selected_font">
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
      						<select name="jnjslx" class="selected_font">
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
      					<td>U个数：&nbsp;&nbsp;&nbsp;<input type="text" name="ugs" value="<%=form.getUgs()%>" class="selected_font"/></td>
      					<td>已使用U个数:<input type="text" name="ysyugs" value="<%=form.getYsyugs()%>" class="selected_font"/></td>		
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
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;综合机房扩展信息&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr class="form_label">
      					<td>机房高度：<input type="text" name="jfgd" value="<%=form.getJfgd()%>" class="selected_font"></td>
      					<td>设定温度：<input type="text" name="sdwd" value="<%=form.getSdwd()%>" class="selected_font"/></td>
      					<td>送风方式：
      						<select name="sffs" class="selected_font">
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
      						<select name="lyfs" class="selected_font">
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
      				<script type="text/javascript">
      					document.form1.sffs.value='<%=form.getSffs()%>';//送风方式
      					document.form1.jnjslx.value='<%=form.getJnjslx()%>';//IDC节能技术
      					document.form1.txj.value='<%=form.getTxj()%>';//IDC机房星级：
      					document.form1.lyfs.value='<%=form.getLyfs()%>';//冷源方式
      					</script>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr class="form_label">
      	<td colspan=6>
      		<div align="center" id="JZKZ"  style="display:none;">
      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;基站扩展信息&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr class="form_label">
      					<td>共站情况：<input type="text" name="gzqk" value="<%=form.getGzqk()%>" class="selected_font"></td>
      					<td>能耗占比：<input type="text" name="nhzb" value="<%=form.getNhzb()%>" class="selected_font"/></td>
      					<td>载频数量：<input type="text" name="zpsl" value="<%=form.getZpsl()%>" class="selected_font"/></td>
      				<td >逻辑站数：<input type="text" name="ljzs" value="<%=form.getLjzs()%>" class="selected_font" /></td>
      				
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
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;管理用房扩展信息&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr class="form_label">
      					<td>在岗人员：<input type="text" name="glzgry" value="<%=form.getZgry()%>" class="selected_font"></td>
      					<td>空调数量：<input type="text" name="glktsl" value="<%=form.getKtsl()%>" class="selected_font"/></td>
      					<td>PC数量：<input type="text" name="glpcsl" value="<%=form.getPcsl()%>" class="selected_font"/></td>
      				    
      				</tr>
      			</table>
      		</div>
      	</td>
      </tr>
      <tr class="form_label"> 
      	<td colspan=6><br>
      		<div align="center" id="QDYFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:50px;display:inline;"><hr></div>&nbsp;渠道用房扩展信息&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr class="form_label">
      					<td>在岗人员：<input type="text" name="qdzgry" value="<%=form.getZgry()%>" class="selected_font"/></td>
      					<td>空调数量：<input type="text" name="qdktsl" value="<%=form.getKtsl()%>" class="selected_font"/></td>
      					<td>PC数量：<input type="text" name="qdpcsl" value="<%=form.getPcsl()%>" class="selected_font"/></td>
      					<td>人流量（天/人）：<input type="text" name="rll" value="<%=form.getRll()%>" class="selected_font"/></td>
      				
      				</tr>
      			</table>
      		</div>
      	<br></td>
      </tr>
      <tr class="form_label">
      	<td colspan=6>
      		<div align="center" id="FSCYFKZ"  style="display:none;">
      			

      			<table width="100%">
      				<div id="parent" style="display:inline">
               <div style="width:15px;display:inline"></div><div style="width:50px;display:inline;"><hr></div>&nbsp;通信机房扩展信息&nbsp;<div style="width:300px;display:inline;"><hr></div>
            	</div>
      				<tr class="form_label">

						<td>在岗人员：<input type="text" name="zgry" value="<%=zg%>"  class="selected_font"></td>
      					<td>空调数量：<input type="text" name="ktsl" value="<%=kt%>"  class="selected_font"/></td>
      					<td>已使用面积：<input type="text" name="ysymj" value="<%=ysymj%>" style="width:130px" class="selected_font"/></td>
      					<td>PC数量：<input type="text" name="pcsl" value="<%=pc%>" /></td>

							<td>用电类型：
      						<select name="ydlx" class="selected_font">
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
      				<script>
      					
      					document.form1.ydlx.value='<%=form.getYdlx()%>';
      					</script>
      			</table>
      		</div>
      	</td>
      </tr>
       
    <tr class="form_label" >
        <td colspan="8">
        
        	<div id="cancelBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
			<img src="<%=path %>/images/quxiao.png" width="100%" height="100%">
			<span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">返回</span>      
		</div>
        </td>
      </tr>
									 
			
			
			  
</table>
<input type="hidden" name="accountname" value="<%=accountname %>"/>
	<input type="hidden" name="id" value="<%=id%>"/>
	<input type="hidden" name="nums" value="<%=nums+1%>"/>
	<input type="hidden" name="dbid" value="<%=gdbid%>"/>
<input type="hidden" name="kzid" value="<%=form.getKzid()%>"/>
<input type="hidden" name="czzdid" value="<%=form.getCzzdid()%>"/>

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
		
document.form1.shi.value='<%= form.getShi()%>';// 市
document.form1.xian.value='<%= form.getXian()%>';//区县
document.form1.xiaoqu.value='<%=form.getXiaoqu()%>';//小区
document.form1.fuzeren.value='<%=form.getFuzeren() %>';//站点负责人
if(document.form1.fuzeren.value==null||document.form1.fuzeren.value=="null"){
	document.form1.fuzeren.value="";
}
//document.form1.signtypenum.value='<%=form.getSigntypenum()%>';//标杆类型编号
document.form1.qyzt.value='<%=form.getQyzt()%>';//站点启用状态
if(document.form1.jztype.value==""){
document.form1.jztype.value="0";
}
if(document.form1.qyzt.value==""||document.form1.qyzt.value==null){
document.form1.qyzt.value="1";
}
document.form1.jztype.value='<%=form.getJztype()%>';//集团报表类型
if(document.form1.jztype.value==""||document.form1.jztype.value==null){
 document.form1.jztype.value="0";
}
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

document.form1.bgsign.value='<%=form.getBgsign()%>';//是否标杆
if(document.form1.bgsign.value==""||document.form1.bgsign.value==null||document.form1.bgsign.value=="null"){
document.form1.bgsign.value="0";//是否标杆
}


document.form1.jnglmk.value='<%=form.getJnglmk()%>';//是否节能
document.form1.address.value='<%=form.getAddress()%>';
document.form1.gdfs.value='<%=form.getGdfs()%>';//供电方式
if(document.form1.gdfs.value==""||document.form1.gdfs.value==null){
document.form1.gdfs.value="0";
}

document.form1.gxxx.value='<%=form.getGxxx()%>';//共享信息
if(document.form1.gxxx.value=="" || document.form1.gxxx.value==null){
document.form1.gxxx.value="0";
}

document.form1.dfzflx.value='<%=form.getDfzflx()%>';   //电费支付类型 
if(document.form1.dfzflx.value=="null"||document.form1.dfzflx.value==""||document.form1.dfzflx.value==null){
document.form1.dfzflx.value="0";
}

document.form1.fkfs.value='<%=form.getFkfs()%>';//付款方式
if(document.form1.fkfs.value==""||document.form1.fkfs.value==null){
document.form1.fkfs.value="0";
}
document.form1.fkzq.value='<%=form.getFkzq()%>';//付款周期
if(document.form1.fkzq.value==""||document.form1.fkzq.value==null){
document.form1.fkzq.value="0";
}
document.form1.watchcost.value='<%=form.getWatchcost()%>';  //套表计费 
 if(document.form1.watchcost.value==""||document.form1.watchcost.value==null){
    document.form1.watchcost.value="0";
 }

 document.form1.beilv.value='<%=form.getBeilv()%>';//倍率
 
 document.form1.linelosstype.value='<%=form.getLinelosstype()%>';//线损类型
 
 if(document.form1.linelosstype.value==""||document.form1.linelosstype.value==null){
 ocument.form1.linelosstype.value="0";
 }
 
 document.form1.linelossvalue.value='<%=form.getLinelossvalue()%>';//线损值
 document.form1.dbyt.value='<%=form.getDbyt()%>';//电表用途
document.form1.zdcq.value='<%=form.getZdcq()%>';//站点产权
document.form1.jflx.value='<%=form.getJflx()%>';//局房类型
document.form1.jzxlx.value='<%=form.getJzxlx()%>';//基站类型
document.form1.jrwtype.value='<%=form.getJrwtype()%>';//接入网类型
document.form1.jlfh.value='<%=form.getJlfh()%>';//交流负荷
document.form1.gsf.value='<%=form.getGsf()%>';//归属方
if(document.form1.gsf.value==""||document.form1.gsf.value==null){
document.form1.gsf.value="0";
}
document.form1.dytype.value='<%=form.getDytype()%>';//地域属性
if(document.form1.dytype.value==""||document.form1.dytype.value==null){
 document.form1.dytype.value="0";
 }
document.form1.g2.value='<%=form.getG2zd()%>';//2G设备
if(document.form1.g2.value==""||document.form1.g2.value==null){
 document.form1.g2.value="0";
 }
document.form1.g3.value='<%=form.getG3zd()%>';//3G设备
if(document.form1.g3.value==""||document.form1.g3.value==null){
 document.form1.g3.value="0";
 }
document.form1.kdsb.value='<%=form.getKdsb()%>';//宽带设备
if(document.form1.kdsb.value==""||document.form1.kdsb.value==null){
 document.form1.kdsb.value="0";
 }
document.form1.yysb.value='<%=form.getYysb()%>';//语音设备
if(document.form1.yysb.value==""||document.form1.yysb.value==null){
 document.form1.yysb.value="0";
 }

document.form1.kdsbsl.value='<%=form.getKdsbsl()%>';//宽带设备数量
document.form1.yysbsl.value='<%=form.getYysbsl()%>';//语音设备数量
document.form1.kt1.value='<%=form.getKt1()%>';//空调1
if(document.form1.kt1.value==""||document.form1.kt1.value==null){
 document.form1.kt1.value="0";
 }
document.form1.kt2.value='<%=form.getKt2()%>';//空调2
if(document.form1.kt2.value==""||document.form1.kt2.value==null){
 document.form1.kt2.value="0";
 }
document.form1.zgd.value='<%=form.getZgd()%>';//直供电
if(document.form1.zgd.value==""||document.form1.zgd.value==null){
 document.form1.zgd.value="0";
 }



//
function kzinfo(){
    var jzproperty = document.form1.jzproperty.value; 
	var jztype = document.form1.jztype.value;
	
	if(jztype=="zdlx01"){//IDC机房
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
	}else if(jztype=="zdlx08"){//基站
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
	}
	if(jzproperty=="zdsx01"){//局房类型
		
		document.getElementById("JFLXKZ").style.display="block";
		document.getElementById("JZLXKZ").style.display="none";
		document.getElementById("JRWTYPE").style.display="none";
	}else if(jzproperty=="zdsx02"){//基站类型
		
		document.getElementById("JFLXKZ").style.display="none";
		document.getElementById("JZLXKZ").style.display="block";
		document.getElementById("JRWTYPE").style.display="none";
	}else if(jzproperty=="zdsx05"){//基站类型
		
		document.getElementById("JFLXKZ").style.display="none";
		document.getElementById("JZLXKZ").style.display="none";
		document.getElementById("JRWTYPE").style.display="block";
	}else{
	     document.getElementById("JFLXKZ").style.display="none";
		 document.getElementById("JZLXKZ").style.display="none";
		 document.getElementById("JRWTYPE").style.display="none";
	}
}
var typecode='<%=form.getJztype()%>';
var code='<%=form.getJzproperty()%>';

if(typecode=="zdlx01"){//IDC机房
		document.getElementById("IDCJFKZ").style.display="block";
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx03"){//综合机房
		document.getElementById("ZHJFKZ").style.display="block";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx08"){//基站
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="block";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx10"){//管理用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="block";
		document.getElementById("QDYFKZ").style.display="none";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx11"){//渠道用房
		document.getElementById("ZHJFKZ").style.display="none";
		document.getElementById("IDCJFKZ").style.display="none";
		document.getElementById("JZKZ").style.display="none";
		document.getElementById("GLYFKZ").style.display="none";
		document.getElementById("QDYFKZ").style.display="block";
		document.getElementById("FSCYFKZ").style.display="none";
	}else if(typecode=="zdlx12"){//非生产用房
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
	}
	if(code=="zdsx01"){//局房类型
		
		document.getElementById("JFLXKZ").style.display="block";
		document.getElementById("JZLXKZ").style.display="none";
		document.getElementById("JRWTYPE").style.display="none";
	}else if(code=="zdsx02"){//基站类型
		
		document.getElementById("JFLXKZ").style.display="none";
		document.getElementById("JZLXKZ").style.display="block";
		document.getElementById("JRWTYPE").style.display="none";
	}else if(code=="zdsx05"){//基站类型
		
		document.getElementById("JFLXKZ").style.display="none";
		document.getElementById("JZLXKZ").style.display="none";
		document.getElementById("JRWTYPE").style.display="block";
	}else{
	     document.getElementById("JFLXKZ").style.display="none";
		 document.getElementById("JZLXKZ").style.display="none";
		 document.getElementById("JRWTYPE").style.display="none";
	}
	
	function showxuni(){

			if(document.form1.xuni.checked){
				document.getElementById("IDXUNI").style.display="block";
			}else{
				document.getElementById("IDXUNI").style.display="none";
			}
		}	
</script>