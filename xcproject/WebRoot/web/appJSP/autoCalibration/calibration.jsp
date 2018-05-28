<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.mobi.common.CommonBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.text.*"%>
<%
    String path = request.getContextPath();
    Account account = (Account)session.getAttribute("account");
    String loginId = account.getAccountId();
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
	String zdsx = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
	Date date = new Date(); 
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
	String dateString = formatter.format(date);
	String year = request.getParameter("year")!=null?request.getParameter("year"):dateString;
    String roleId = (String)session.getAttribute("accountRole");
    int intnum = request.getAttribute("num")!=null?(Integer)request.getAttribute("num"):0;
    String color = "";
%>

<html>
<head>
<style>   
.style1 {
	color: red;
	font-weight: bold;
}
.form_label{
			text-align: left;
			font-family: 宋体;
			font-size: 12px;
			height:23px
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:100%;
}
.relativeTag   {     
	z-index:10;   
	position:relative;     
	top:expression(this.offsetParent.scrollTop);     
}

.bttcn{color:BLACK;font-weight:bold;}

 </style>
<script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
<script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/wait.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/someJs/page.js"></script>
<script type="text/javascript" src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
</script>
<script language="javascript">
var path = '<%=path%>';
function queryDegree(action){
    	
    	var command=action;
    	if(checkNotnull(document.form1.year,"年份")){
	           var comm = document.getElementById("command");
	   		   comm.value = command;
               document.form1.action=path+"/servlet/DetailServlet";
               document.form1.submit();
	   }
}

$(function(){
	    //$("#auto02").attr("disabled", true); 
	   // $("#auto03").attr("disabled", true); 
	   // $("#auto04").attr("disabled", true); 
		
	    $("#auto01").click(function(){
	    	$("#auto02").attr("disabled", false); 
	    	$.post(path+"/servlet/calibrationServerlet?action=auto01",{
           // name:"Donald Duck",
           // city:"Duckburg"
         },
         function(data,status){
           alert('基础数据整理完成。');
        });
	    });

			
		
		$("#auto02").click(function(){
			//$("#auto02").attr("disabled", false);
			
	    	$.post(path+"/servlet/calibrationServerlet?action=auto02",{
             name:$("#shi").val()
         },
         function(data,status){
           alert('详细信息数据生成结束。');
           $("#auto03").attr("disabled", false);
        });
		});
		$("#auto03").click(function(){
			$.post(path+"/servlet/calibrationServerlet?action=auto03",{
             name:$("#shi1").val()
         },
         function(data,status){
           alert('建议生产标生成结束');
           $("#auto04").attr("disabled", false);
        });
			//alert("d");
		});
		$("#auto04").click(function(){
				$.post(path+"/servlet/calibrationServerlet?action=auto04",{
             name:$("#shi2").val()
         },
         function(data,status){
           alert('数据转移成功');
        });
		});
		$("#chaxun").click(function(){
			   	if(document.getElementById("shi").value==""||document.getElementById("shi").value=="0"||document.getElementById("shi").value==null){
	               	alert("城市不能为空");
	   			}else{
					queryDegree("chaxun");
				}
		});
	});
  </script>
</head>

<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" >	
	<form action="" name="form1" method="POST">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0" >
	      <tr>
           <td width="50" >
             <div style="width:700px;height:50px">
                <img src="<%=path%>/images/btt.bmp" width="100%" height="100%" />
			     <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:15px;color:black">自动定标管理</span>
	         </div>
	        </td>
		  </tr>
		  
		  
		  <tr>
		   <td height="20" >
   				<div id="parent" style="display:inline">
                   <div style="width:50px;display:inline;"><hr></div><font color="red" size="2">&nbsp;自动定标功能处理数据量庞大，请勿在系统使用高峰期执行。&nbsp;</font><div style="width:300px;display:inline;"><hr></div>
                </div>
            </td>
          </tr>
          
          
          
         

  		 <tr>
  		   <td width="1200px">
  		   <table>
  		  
  		   <tr>
  		   
  		  
  		   
  		   <td >
                     <div id="auto01" style="position:relative;width:100px;height:23px;right:-20px;cursor: pointer;TOP:0PX">
		                  <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                  <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">生成基础数据</span> 
		             </div>
			      </td>
			      
			       <tr><td height="5"  colspan="4">
      <div id="parent" style="display:inline">
        <div style="width:50px;display:inline;"><hr></div>
        <font size="2">&nbsp;分地市完成日均耗电量，能耗基础值，实际值以及相应的占比信息</font>
        <div style="width:265px;display:inline;"><hr></div>
      </div></td>
     </tr>
			      
			      
  		     <tr class="form_label">
               <td >城市：</td>      
                <td><select name="shi" class="selected_font" id="shi" onchange="changeCity()">
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
         	    </select><span class="style1">&nbsp;*</span></td>


                 <td >
                     <div id="auto02" style="position:relative;width:100px;height:23px;right:0px;cursor: pointer;TOP:0PX">
		                  <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                  <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">生成详细信息</span> 
		             </div>
			      </td>
	         	  
			   </table>
			 </td>
		 </tr>
		 
		 
		 
 </table>
 
 
 
 
 

  <table height="23">     
     <tr><td height="5"  colspan="4">
      <div id="parent" style="display:inline">
        <div style="width:50px;display:inline;"><hr></div>
        <font size="2">&nbsp;生成站点详细信息，完成建议生产标&nbsp;</font>
        <div style="width:430px;display:inline;"><hr></div>
      </div></td>
     </tr>
  </table>
	
   <table>
   <tr class="form_label">
               <td >城市：</td>      
                <td><select name="shi1" class="selected_font" id="shi1" onchange="changeCity()">
         		<option value="0">请选择</option>
         		<%
		         	ArrayList shilist1 = new ArrayList();
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
         	    </select><span class="style1">&nbsp;*</span></td>
   <td>
                   <div id="auto03" style="position:relative;width:100px;height:23px;right:-20px;cursor: pointer;TOP:0PX">
		                  <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                  <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">生成建议生产标</span> 
		             </div>
			      </td>
	         	  
			   </table>
			    
			
		
   
                    
			      
			      
  <table height="23">     
     <tr><td height="5"  colspan="4">
      <div id="parent" style="display:inline">
        <div style="width:50px;display:inline;"><hr></div>
        <font size="2">&nbsp;完成后期分析数据转移&nbsp;</font>
        <div style="width:500px;display:inline;"><hr></div>
      </div></td>
     </tr>
  </table>
	
  <table>
   <tr class="form_label">
               <td >城市：</td>      
                <td><select name="shi2" class="selected_font" id="shi2" onchange="changeCity()">
         		<option value="0">请选择</option>
         		<%
		         	ArrayList shilist2 = new ArrayList();
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
         	    </select><span class="style1">&nbsp;*</span></td>
   <td>
                     <div id="auto04" style="position:relative;width:100px;height:23px;right:-20px;cursor: pointer;TOP:0PX">
		                  <img alt="" src="<%=path %>/images/chaxun.png" width="100%" height="100%" />
		                  <span style="font-size:12px;position: absolute;left:25px;top:3px;color:white">生成转移数据</span> 
		             </div>
			      </td>
			      </tr></table>
  	

</form>
</body>
</html>

<script type="text/javascript">

//改变城市
function changeCity() {
	var sid = document.form1.shi.value;
	if (sid == "0") {
		var shilist = document.all.xian;
		shilist.options.length = "0";
		shilist.add(new Option("请选项", "0"));
		return;
	} else {
		sendRequest(path + "/servlet/garea?action=shi&pid=" + sid, "shi");
	}
}

</script>
<script language="javascript">
   
   		document.form1.shi.value='<%=shi%>';
		document.form1.xian.value='<%=xian%>';
		document.form1.xian.value='<%=zdsx%>';
		document.form1.year.value='<%=year%>';
 </script>