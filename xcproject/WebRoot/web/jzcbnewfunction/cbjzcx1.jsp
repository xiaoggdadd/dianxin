<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,java.util.List,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.util.CTime,com.noki.electricfees.javabean.ElectricFeesBean,com.noki.electricfees.javabean.ElectricFeesFormBean" %>
<%@ page import="java.sql.ResultSet,com.noki.mobi.common.CommonBean,com.noki.newfunction.javabean.Zdinfo,com.noki.newfunction.dao.CbzdQuery"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.regex.Pattern"%>

<%
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
    String loginName = account.getAccountName();
    String beginTime1 = request.getParameter("beginTime1") != null ? request.getParameter("beginTime1"): "";
	String title = request.getParameter("title")!=null?request.getParameter("title"):"";
	String operName = request.getParameter("operName")!=null?request.getParameter("operName"):"";
	String sheng = (String)session.getAttribute("accountSheng");
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
    String beginTimeQ = request.getParameter("beginTime");
  String jzproperty=request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"0";
   // String citysh = request.getParameter("rgsh1");
     String loginId1 = request.getParameter("loginId");
     String pdzt=request.getParameter("pdzt") != null ? request.getParameter("pdzt") : "0";
     String property1 = request.getParameter("property")!=null?request.getParameter("property"):"0";
   // System.out.println("审核"+citysh);
  //  if(citysh!=null){
   // 	cityaudit1=citysh;
  //  }
    //String roleId = account.getRoleId();
    String s_curpage = request.getParameter("curpage")!=null?request.getParameter("curpage"):"1";
	int count=0,pagesize=15,curpage=1,nextpage=1,prepage=1,allpage=1,xh=1;
          curpage=Integer.parseInt(s_curpage);
    String permissionRights="";
    String color=null;
    String roleId = (String)session.getAttribute("accountRole");
    double ddf=0.0;
    int intnum=0;
    //System.out.println("roleId:"+roleId);
%>

<html>
<head>
<title>

</title>
<style>
            
            .style1 {
	color: #FF9900;
	font-weight: bold;
}
 .style1 {
	color: red;
	font-weight: bold;
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.bttcn{color:BLACK;font-weight:bold;}
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
 </style>
 <script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/PopupCalendar.js"></script>
<script src="<%=path%>/javascript/PopupCalendar_ny.js">
</script>
	
<script>

var oCalendarEnny = new PopupCalendarny("oCalendarEnny"); //初始化控件时,请给出实例名称如:oCalendarEn
oCalendarEnny.Init();

var oCalendarChsny = new PopupCalendarny("oCalendarChsny"); //初始化控件时,请给出实例名称:oCalendarChs
oCalendarChsny.weekDaySting = new Array("日", "一", "二", "三", "四", "五", "六");
oCalendarChsny.monthSting = new Array("一月", "二月", "三月", "四月", "五月", "六月", "七月",
		"八月", "九月", "十月", "十一月", "十二月");
oCalendarChsny.oBtnTodayTitle = "确定";
oCalendarChsny.oBtnCancelTitle = "取消";
oCalendarChsny.Init();
</script>
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

  function chaxun(){
					var beginTime = document.form1.beginTime.value
           	if(checkNotnull(document.form1.beginTime,"开始时间")&&
                   checkNotnull(document.form1.endTime,"结束时间")){
 			document.form1.action=path+"/web/sys/logManage.jsp?beginTime="+beginTime
                        document.form1.submit()
        	}
	}
   function chehui(){
   //   alert("1111chehui");
	  	var m = document.getElementsByName('test[]');
		var mm = document.getElementsByName('test1[]');
        var arr = new Array
        var l = m.length;
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var countct=0;
        var bzw=1;
        var count2=0;
       	var chooseIdStr = "";
       	var chooseIdStr1="";
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}if(m[i].checked == true){
       			if(mm[i].value=='1'){
       				countct++;
       			}
       		}
       	}
       	if(countct!=0){
       		 alert("有信息地市已下派,不允许撤回！");
       	}else{
       	
       	if(count!=0){
	       	if(count%240==0){
	       		n=count/240-1;
	       	}else{
	       		n=(count-(count%240))/240;
	       	}
	        for(var i = 0; i < l; i++){
	          if(m[i].checked == true){
	             bz+=1;
	             count1+=1;
	             chooseIdStr = chooseIdStr +"'" +m[i].value +"',"; 
	           //   alert(chooseIdStr);
	          }
       	
	          if(count1<=240*n||((bz+count2)>=239&&(bz+count2)<=241)){
	         	  if(((bz+count2)/240==1)||((bz+count2)>=239&&(bz+count2)<=241)){
			        chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
			        //alert(chooseIdStr+"222");
			        document.form1.action=path+"/UploadB?action=chehui1&chooseIdStr="+chooseIdStr;
		            chooseIdStr = ""; 
	       			bz=0;
	       			count2=0;
		            document.form1.submit();	          	
		          }
		       }else if(count==count1&&bzw==1){
		          chooseIdStr=chooseIdStr.substring(0,chooseIdStr.length-1);
		          //alert(chooseIdStr+"111");
			      bzw=0;
		          document.form1.action=path+"/UploadB?action=chehui1&chooseIdStr="+chooseIdStr;
		          document.form1.submit(); 
		       }            
	        } 
        }else{
          alert("请选择信息！");
        }
       	}
     }
 
    
    function queryDegree(){
              document.form1.action=path+"/web/jzcbnewfunction/cbjzcx1.jsp?command=chaxun";
              document.form1.submit();
    }
    $(function(){
		$("#daochuBtn").click(function(){
		 exportad();
		 
		});

		$("#tongguo").click(function(){
			passCheck();
			showdiv("请稍等..............");
		});

		$("#chehui").click(function(){
			chehui();
		});
		$("#chaxun").click(function(){
			queryDegree();
		});
		$("#quxiao").click(function(){
			passCheckNo();
		});
	});
</script>

</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<jsp:useBean id="ztcommon" scope="page" class="com.noki.mobi.common.ZtCommon">
</jsp:useBean>
<%
permissionRights=commBean.getPermissionRight(roleId,"0804");

System.out.println(">>>>>>>>>>>>>>..."+permissionRights);
%>
<body  class="body" style="overflow-x:hidden;">
	<form action="" name="form1" method="POST">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" height="18%">
		<tr>
			<td colspan="4">
			<div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">省级整改派单</span>	
			 </div>
		    </td>
			</tr>		
			<tr><td height="30" colspan="4">
	   				<div id="parent" style="display:inline">
	                     <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;过滤条件&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
	                </div>
		        </td>
		    </tr>
		    <tr><td height="8%" width="1200">
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
		    	<td>区县：</td><td><select name="xian" class="selected_font" onchange="changeCountry()">
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
         		<!--<td>报账月份：</td><td><input type="text" name="beginTime2" value="<%if (null != request.getParameter("beginTime2"))out.print(request.getParameter("beginTime2"));%>" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font" /></td>
		    	--><td>对标月份：</td><td><input type="text" name="beginTime1" value="<%if (null != request.getParameter("beginTime1"))out.print(request.getParameter("beginTime1"));%>" onFocus="getDatenyString(this,oCalendarChsny)" class="selected_font" /></td>
				<td>派单状态：</td>
				<td><select name="pdzt" class="selected_font">
					<option value="-1">请选择</option>
					<option value="0">未下发</option>
					<option value="1">已下发</option>
					</select>
				</td> 	
				<td>站点属性：
								</td>
								<td>
									<select name="property" style="width: 130"
										class="selected_font">
										<option value="0">
											请选择
										</option>
										<%
											ArrayList zdsx = new ArrayList();
											zdsx = ztcommon.getSelctOptions("zdsx");
											if (zdsx != null) {
												String code = "", name = "";
												int cscount = ((Integer) zdsx.get(0)).intValue();
												for (int i = cscount; i < zdsx.size() - 1; i += cscount) {
													code = (String) zdsx.get(i + zdsx.indexOf("CODE"));
													name = (String) zdsx.get(i + zdsx.indexOf("NAME"));
										%>
										<option value="<%=code%>"><%=name%></option>
										<%
											}
											}
										%>
									</select>
								</td>		
 			<td> <%if(permissionRights.indexOf("PERMISSION_SEARCH")>=0){%> 
 					<%}%>
		        <div id="chaxun" style="position:relative;width:60px;height:23px;cursor: pointer;right:-40px;float:right;top:0px">
				       <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
				       <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">查询</span> 
				       
				</div></td>
		    	</tr>
		    </table>
		    </td></tr>
	</table>
	<table>
		<tr><td height="23" colspan="4"><div id="parent" style="display:inline">
              <div style="width:50px;display:inline;"><hr></div><font size="2">&nbsp;信息列表&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
        </div></td></tr>
	</table>
	<% 
	 CbzdQuery bean=new CbzdQuery();
	
       double dfdfd=0;
         String whereStr = "";
         String str="";
        
			if(shi != null && !shi.equals("") && !shi.equals("0")){
				whereStr=whereStr+" and zd.shi='"+shi+"'";
			}
			if(xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr=whereStr+" and zd.xian='"+xian+"'";
			}
			//beginTime1
			if(beginTime1 != null && !beginTime1.equals("") && !beginTime1.equals("0")){
				whereStr=whereStr+" and cd.cbsj='"+beginTime1+"'";
			}
			if(property1 != null && property1 != "" && !property1.equals("0")){
				whereStr=whereStr+" AND zd.property='"+property1+"'";
			}
			if(pdzt!= null && pdzt != ""&& !pdzt.equals("-1")){
				if("1".equals(pdzt)){
					whereStr=whereStr+" AND cx.sjxf='"+pdzt+"'";
				}else if("0".equals(pdzt)){
					whereStr=whereStr+" AND cx.sjxf='"+pdzt+"'";
				}
			}
			
			whereStr=whereStr+" and cd.SHENGJSH='2'";
		%>
		
	
	<div style="width:100%;height:300px;overflow-x:auto;overflow-y:auto;border:1px" >
		<table width="100%" height="80%"  border="0" cellspacing="1" cellpadding="1" bgcolor="#cbd5de" class="form_label">
			<tr height = "23" class="relativeTag">
			 	<td width="4%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">序号</div></td>
				<td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn"><input type="checkbox" name="test" onclick="chooseAll()"/>全选</div></td>
                  <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点编号</div></td> 
                 <td width="10%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">站点名称</div></td>                 
                 <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">所在区域</div></td> 
                 <td width="8%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">超标比例</div></td>

                 <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">原因分析</div></td>
     			 <td width="12%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">测试人</div></td>                 
                 <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">状态</div></td>
                 <td width="6%" height="23" bgcolor="#DDDDDD"><div align="center" class="bttcn">操作</div></td>
			</tr>
		<%

		System.out.println("ElectricFeesBean-whereStr:"+whereStr);

          List<Zdinfo> fylist=null;
        String zdid="",zdnamea="",szdq="",cbyf="",cbdf="",cbdl="",lrr="",lrsj="",zt="",sjpd="",sjpd1="",idd="";
        String wjid="",yyfx="",bzpld="",csr="",csms="",shijxf="";
		if("chaxun".equals(request.getParameter("command"))){
		 fylist=bean.seachDatada(whereStr,loginId);
		
		if(fylist!=null){
		for(Zdinfo ls:fylist){
		wjid=ls.getId();
		zdid=ls.getZdid();
		zdnamea=ls.getZdname();
		szdq=ls.getShi()+ls.getXian()+ls.getXiaoqu();
		cbyf=ls.getCbsj();
		yyfx=ls.getYyfx();
		idd=ls.getIdd();
		shijxf=ls.getShijxf();
		if(null==yyfx){yyfx="";}
		bzpld=ls.getCbbl();
		if("".equals(bzpld)||null==bzpld){
		bzpld="0";
		}
		DecimalFormat mat=new DecimalFormat("0.00");
		bzpld=mat.format((Double.parseDouble(bzpld))*100)+"%";
		
		csr=ls.getCszrr();
		if(null==csr){csr="";}
		csms=ls.getCsms();
		if(null==csms){csms="";}
		sjpd=ls.getSjpd();
		if(sjpd==null||sjpd.equals("null")||sjpd.equals("")||sjpd.equals("2")){
		sjpd="0";
		}
		if("0".equals(sjpd)){
		 sjpd1="未下发";
		}else if("1".equals(sjpd)){
		sjpd1="已下发";
		}
		if(shijxf==null||shijxf.equals("null")||shijxf.equals("")||shijxf.equals(" ")){
			shijxf="0";
		}
		
		System.out.println("----------"+sjpd);
		//===============xin===========
		if(intnum%2==0){
			    color="#FFFFFF";

			 }else{
			    color="#DDDDDD" ;
			}
	     intnum++;
		double dfdf=0;
       %>
      
       <tr bgcolor="<%=color%>">
       <td>
       			<div align="center" ><%=intnum%></div>
       		</td>
       		<td>
              <div align="center"><input type="checkbox" name="test[]" value="<%=wjid%>" /><input type="hidden" type="checkbox" name="test1[]" value="<%=sjpd %>" /></div>
            </td>
           <td>
       			<div align="center" ><%=zdid%></div>
       		</td>
       		<td>
       			<div align="center" ><a href="javascript:lookDetailszz('<%=idd%>')"><%=zdnamea%></a></div>
       		</td>
            <td>
       			<div align="center" ><%=szdq%></div>
       		</td>
       			<td>
       			<div align="right" ><%=bzpld%></div>
       		</td>
			<td>
       			<div align="center" ><%=yyfx%></div>
       		</td>
       		<td>
       			<div align="center" ><%=csr%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><%=sjpd1%></div>
       		</td>
       		
       		<td>
       			<div align="center" ><a href="javascript:lookDetailssz('<%=wjid%>','<%=sjpd %>')">整改</a></div>
       		</td>
       	   
       </tr>
     <%}}}%>
              <% if (intnum==0){
    	  System.out.println("QQQQ"+intnum);
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
            </tr>
      <% }}else if(!(intnum > 15)){
    	  for(int i=((intnum-1)%15);i<15;i++){
            if(i%2==0)
			    color="#DDDDDD" ;
            else
			    color="#FFFFFF";
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
        </tr>
        <%}}%>	
	</table>
	</div>
	
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		<!--   <tr bgcolor="F9F9F9">
                     <td align="right" height="19" colspan="4"><div id="parent" style="display:inline">
                         <div style="width:50px;display:inline;"><hr></div><div style="width:300px;display:inline;"><hr></div>
                     </div></td>
	             </tr>
	             -->
	               <tr>
	               <td>
	                  <!--      
	                          <div id="daochuBtn" style="width:60px;height:23px;cursor:pointer;float:right;position:relative;right:4px">
		 								 <img src="<%=path %>/images/daochu.png" width="100%" height="100%">
										 <span style="font-size:12px;position: absolute;left:27px;top:5px;color:white">导出</span>      
									</div>     
	                               
	                     <div id="butongguo" style="position: relative; width: 63px; height: 23px; cursor: pointer; float: right; right:10px">
					          <img alt="" src="<%=request.getContextPath()%>/images/baocun.png" width="100%" height="100%" />
					          <span style="font-size: 12px; position: absolute; left: 25px; top: 3px; color: white">不通过</span>
				         </div>      
	                    <div id="tongguo" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
					         <img src="<%=path%>/images/2chongzhi.png" width="100%" height="100%">
					         <span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">通过</span>
				        </div>
				        -->
				        
				        <div id="quxiao"
							style="width: 85px; height: 23px; cursor: pointer; float: right; position: relative; right:0px;top: 0px">
							<img src="<%=path%>/images/mmcz.png" width="100%" height="100%">
							<span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">批量整改</span>
						</div>
	                              <div id="chehui" style="width: 60px; height: 23px; cursor: pointer; float: right; position: relative; right: 20px">
					         <img src="<%=path%>/images/2chongzhi.png" width="100%" height="100%">
					         <span style="font-size: 12px; position: absolute; left: 27px; top: 5px; color: white">撤回</span>
				        </div>   
	                           </td>
	               </tr>
	</table>
	 <input type="hidden" name="sheng2" id="sheng2" value=""/>
			  <input type="hidden" name="shi2" id="shi2" value=""/>
			  <input type="hidden" name="xian2" id="xian2" value=""/>
			  <input type="hidden" name="xiaoqu2" id="xiaoqu2" value=""/>
			  <input type="hidden" name="sptype2" id="sptype2" value=""/>
	</form>
</body>
<script language="javascript">
	var path = '<%=path%>';
 function lookDetailszz(zdcode){ 
	  window.open(path+"/web/query/caijipoint/shenhemodifsite.jsp?id="+zdcode,'','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
    }
</script>
<script language="javascript">
	var path = '<%=path%>';
	function lookDetails(dfid,dfzflx){
	
   var dfid1=dfid;
   var dfzflx1=dfzflx;
   
	var href = "FeesDetails.jsp?dfid="+dfid1+"&dfzflx="+dfzflx1;
	var str = "<a id='aa' href='"+href+"' target='details'></a>";
	$("body").append(str);
	$("#aa")[0].click();
	$("#aa").remove();
   }
</script>
<script type="text/javascript">
function lookDetailss(zdid,cbyf){ 
	 var path='<%=path%>';   
	 window.open(path+"/web/jzcbnewfunction/showdfxx.jsp?zdid="+zdid+"&cbyf="+cbyf,'','width=1230,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
    }
    
    function lookDetailssz(wjid,shijxf){ 
	 var path='<%=path%>';   
	 if(shijxf!='0'){
		 alert("该信息已下派,不允许下派！");
	 }else{
		 window.open(path+"/web/jzcbnewfunction/cbjzxx.jsp?&wjid="+wjid,'','width=1100,height=400,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
     }
	}
</script>
<script type="text/javascript">
<!--
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

function changeSheng(){
	var sid = document.form1.sheng.value;
	document.form1.sheng2.value=document.form1.sheng.value;
	if(sid=="0"){
		var shilist = document.all.shi;
		shilist.options.length="0";
		shilist.add(new Option("请选项","0"));
		return;
	}else{
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
	document.form1.shi2.value=document.form1.shi.value;
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
	document.form1.xian2.value=document.form1.xian.value;
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

//-->
</script>
<script type="text/javascript">
	var XMLHttpReq1;
	function createXMLHttpRequest1() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq1 = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq1 = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq1 = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	function sendRequest1(url,para) {

		createXMLHttpRequest1();
		XMLHttpReq1.open("POST", url, true);
		if(para=="checkcity1"){
			XMLHttpReq1.onreadystatechange = processResponse_checkcity1;
		}else if(para=="checkcityno1"){
			XMLHttpReq1.onreadystatechange = processResponse_checkcityno1;
		}else{
			XMLHttpReq1.onreadystatechange = processResponse;//指定响应函数
		}
		XMLHttpReq1.send(null);  
	}
	// 处理返回信息函数
    function processResponse() {
    	if (XMLHttpReq1.readyState == 4) { // 判断对象状态
        	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
              var res = XMLHttpReq1.responseText;
              window.alert(res);
            } else { //页面不正常
                window.alert("您所请求的页面有异常。");
            }
        }
    }
	function passCheck1(chooseIdStr1,chooseIdStr2){
			sendRequest1(path+"/servlet/check?action=checkcity1&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2,"checkcity1");
	}
	function processResponse_checkcity1() {	
		if (XMLHttpReq1.readyState == 4) { // 判断对象状态
	    	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
	    	document.form1.bzw1.value= XMLHttpReq1.responseText;
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
	function passCheckNo1(chooseIdStr1,chooseIdStr2){
	alert(chooseIdStr1+" "+chooseIdStr2);
	window.open(path+"/web/jzcbnewfunction/showzgpl.jsp?zdid="+chooseIdStr1+"&cbyf="+chooseIdStr2,'','width=1230,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
		//  sendRequest1(path+"/servlet/check?action=checkcityno1&chooseIdStr1="+chooseIdStr1+"&chooseIdStr2="+chooseIdStr2,"checkcityno1");
	}
	
	function processResponse_checkcityno1() {	
		if (XMLHttpReq1.readyState == 4) { // 判断对象状态
	    	if (XMLHttpReq1.status == 200) { // 信息已经成功返回，开始处理信息
	        	document.form1.bzw1.value = XMLHttpReq1.responseText;
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
</script>
<script type="text/javascript">
function passCheckNo(){
        var m = document.getElementsByName('test[]');  
        var mm = document.getElementsByName('test1[]');
        var l = m.length; 
        var chooseIdStr1 = ""; 
        var chooseIdStr2 = ""; 
        var bz=0;
        var n=0;
        var count=0; 
        var count1=0;
        var count2=0;
        var countct=0;
        var bzw=1;
        var bzw1="";
        var c=0;
       	for(var i = 0; i < l; i++){
       		if(m[i].checked == true){
       			count+=1;
       		}
       		if(m[i].checked == true){
       			if(mm[i].value=='1'){
       				countct++;
       			}
       		}
       	}
       	if(countct!=0){
       		 alert("有信息已下派,不允许重复下派！请确认！");
       	}else{
       	if(bzw=="1"&&count>1){
		        for (var i = 0; i < l; i++) {  
		          if(m[i].checked == true){
		             bz+=1;
	                 count1+=1;
		            
		             var chooseIdStr3 = m[i].value.toString();
		             chooseIdStr1 = chooseIdStr1 +"'" + chooseIdStr3 +"',";
		          }
		          	
            //   bzw1=document.form1.bzw1.value;
		      if(bzw=="1"){
		         if(count==count1&&bzw==1){
			          chooseIdStr1=chooseIdStr1.substring(0,chooseIdStr1.length-1);
				      window.open(path+"/web/jzcbnewfunction/cbjzpl.jsp?idad="+chooseIdStr1,'','width=1230,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100')	
				      bzw=0;
			     
			  }else if(bzw1=="0"){
				document.form1.action=path+"/web/msg.jsp";
				document.form1.submit(); 
				return;  
			  }            
	        } 
	        }
      }else if(count==1||c==1){
      alert("批量整改需要两条及两条以上信息！");
      return;
      }else{
        alert("请选择信息！");
        return;
      }
     }
   }
 
 </script>
  <!--多选框选择 -->
 <script type="text/javascript">
 function chooseAll() { 
        var qm = document.getElementsByName('test');
        //alert(qm[0].checked);  
        var m = document.getElementsByName('test[]');   
        var l = m.length; 
        if(qm[0].checked == true){
          for (var i = 0; i < l; i++) {   
            m[i].checked = true;   
          }  
        }else{
          for (var i = 0; i < l; i++) {   
            //m[i].checked == true ? m[i].checked = false: m[i].checked = true; 
            m[i].checked = false;  
          }   
        }        
    }   
    
    	document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
	    document.form1.beginTime1.value='<%=beginTime1%>';
	    document.form1.pdzt.value='<%=pdzt%>';
		document.form1.property.value='<%= property1%>';
 </script>
</html>

