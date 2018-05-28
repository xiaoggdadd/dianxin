<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String table=request.getParameter("quiryTaeble");
String tab=request.getParameter("tab");
List infoList = (ArrayList)request.getAttribute("intoList");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>通用查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=path%>/javascript/prototype.js"></script>
	<LINK href="images/css.css" type=text/css rel=stylesheet>
	<script type="text/javascript">
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
	
	
	function initDate(){

	  var quiryTaeble = document.getElementById("seachTable").value;
	  var seachtab = document.getElementById("seachtab").value;
	  var Url = getPublicPath()+"/servlet/InitInquiryServlet?action=InitInquiryServlet&quiryTaeble="+quiryTaeble;
	   new Ajax.Request(Url,{
	      		method : 'post',
				asynchronous: false,
				onComplete : function (resp){
	 			   var result=resp.responseText;
	 		      if(result){
	 			   var results ="";
	               var Url=getPublicPath()+"/InquiryServlet?action=InquiryServle&quiryTaeble="+quiryTaeble;
			       new Ajax.Request(Url,{
			      		method : 'post',
						asynchronous: false,
						onComplete : function (resp){
						  results=resp.responseText;
						}
				   })	 			     
	 			     var resultArr = result.split("||");
	 			     for(var i=0;i<resultArr.length;i++){
	 			    	var date = resultArr[i].split(',');
	 			        addDate(results,date[0],date[1],date[2],date[3],date[4],date[5]);
	 			     }
	 			 }
	 			 }
	 })
	}
	
	   function toGetDate(){
	       var quiryTaeble = document.getElementById("seachTable").value;
	       var seachtab = document.getElementById("seachtab").value;
	       var Url=getPublicPath()+"/InquiryServlet?action=InquiryServle&quiryTaeble="+quiryTaeble;
	       new Ajax.Request(Url,{
	      		method : 'post',
				asynchronous: false,
				onComplete : function (resp){
	 			     var result=resp.responseText;
	 			     var oTable =  document.getElementById("inquiry");//得到表格
	 			     var oRow = oTable.rows;//得到行
	 			     var oRowLen = oRow.length;//得到行
	 			     var trNode = oTable.insertRow(oRowLen);//插入一行
	 			     trNode.setAttribute("bgcolor","F2F9FF");
	 			     var ret = result.split("||");
	 			     //第一列括号
 			         var tdNode=trNode.insertCell(0);//插入一列
 			         var dirNode = document.createElement("div");//创建一个层
 			         dirNode.setAttribute("align","center");//层居中
 			         var selectNode = document.createElement("select");//创建一个下拉列表
 			         var optionNode = document.createElement("option");
   				     optionNode.innerHTML = "";
   				     optionNode.value = "0";
   				     var optionNode1 = document.createElement("option");
   				     optionNode1.innerHTML = "(";
   				     optionNode1.value = "1";
   				     selectNode.appendChild(optionNode);
   				     selectNode.appendChild(optionNode1);
   				     dirNode.appendChild(selectNode);//将下拉表加入层
 			         tdNode.appendChild(dirNode);//将层节点加入列
	 			         
	 			     var tdNode1=trNode.insertCell(1);//插入一列
	 			     var dirNode1 = document.createElement("div");//创建一个层
	 			     dirNode1.setAttribute("align","center");//层居中
	 			     var selectNode1 = document.createElement("select");//创建一个下拉列表 
	 			       
	 			     for(var i=0;i<ret.length;i++){
	 			         var date = ret[i].split(",");
	 			         var key = date[0]; 
	 			         var value = date[1]; 
	 			         var optionNode2 = document.createElement("option");
	 			         optionNode2.innerHTML = value;
   				   	     optionNode2.value = key;
	 			         selectNode1.appendChild(optionNode2);
	 			         dirNode1.appendChild(selectNode1);//层中加入select
	 			         tdNode1.appendChild(dirNode1);//将层节点加入列
	 			     }
	 			  
	 			  var  condition =['大于','小于','等于','包含'];
	 			  var tdNode2=trNode.insertCell(2);//插入一列
	 			  var dirNode2 = document.createElement("div");//创建一个层
	 			  dirNode2.setAttribute("align","center");//层居中
	 			  var selectNode2 = document.createElement("select");//创建一个下拉列表      
	 			  for(var j=0;j<condition.length;j++){  
                      var con =condition[j];
                      var optionNode3 = document.createElement("option");
	 			      optionNode3.innerHTML = con;
	 			      optionNode3.value = j;
	 			      selectNode2.appendChild(optionNode3);
	 			      dirNode2.appendChild(selectNode2);//层中加入select
	 			      tdNode2.appendChild(dirNode2);//将层节点加入列	 			  
	 			  } 
	 			   
	 			  var tdNode3=trNode.insertCell(3);//插入一列
	 			  var dirNode3 = document.createElement("div");//创建一个层
	 			  dirNode3.setAttribute("align","center");//层居中
	 			  var inputNode = document.createElement("input");
	 			  dirNode3.appendChild(inputNode);
	 			  tdNode3.appendChild(dirNode3);//将层节点加入列	 
	 			   
	 			  var gx = ["and","or"]; 
	 			  var tdNode4=trNode.insertCell(4);//插入一列
	 			  var dirNode4 = document.createElement("div");//创建一个层
	 			  dirNode4.setAttribute("align","center");//层居中
	 			  var selectNode3 = document.createElement("select");//创建一个下拉列表     
	 			  for(var k=0;k<gx.length;k++){   
	 			      var guxi =gx[k];
	 			      var optionNode4 = document.createElement("option");
	 			      optionNode4.innerHTML = guxi;
	 			      optionNode4.value = k;
	 			      selectNode3.appendChild(optionNode4);
	 			      dirNode4.appendChild(selectNode3);//层中加入select
	 			      tdNode4.appendChild(dirNode4);//将层节点加入列	
	 			  }
	 			  
	 			     var tdNode5=trNode.insertCell(5);//插入一列
 			         var dirNode5 = document.createElement("div");//创建一个层
 			         dirNode5.setAttribute("align","center");//层居中
 			         var selectNode4 = document.createElement("select");//创建一个下拉列表
 			         var optionNode5 = document.createElement("option");
 			         optionNode5.innerHTML = "";
   				     optionNode5.value = "0";
   				     var optionNode6 = document.createElement("option");
 			         optionNode6.innerHTML = ")";
   				     optionNode6.value = "1";
   				     selectNode4.appendChild(optionNode5);
   				     selectNode4.appendChild(optionNode6);
   				     dirNode5.appendChild(selectNode4);//将下拉表加入层
 			         tdNode5.appendChild(dirNode5);//将层节点加入列
	 			  
				}
			})
	   }
	   
	   
	   
	   function addDate(result,kuohao,item,fuhao,vl,guanxi,rkuohao){
	         var oTable =  document.getElementById("inquiry");//得到表格
		     var oRow = oTable.rows;//得到行
		     var oRowLen = oRow.length;//得到行
		     var trNode = oTable.insertRow(oRowLen);//插入一行
		     trNode.setAttribute("bgcolor","F2F9FF");
		     var ret = result.split("||");
		     //第一列括号
	         var tdNode=trNode.insertCell(0);//插入一列
	         var dirNode = document.createElement("div");//创建一个层
	         dirNode.setAttribute("align","center");//层居中
	         var selectNode = document.createElement("select");//创建一个下拉列表
	         var optionNode = document.createElement("option");
		     optionNode.innerHTML = "";
		     optionNode.value = "0";
		     var optionNode1 = document.createElement("option");
		     optionNode1.innerHTML = "(";
		     optionNode1.value = "1";
		     if(kuohao=='0'){
		       optionNode.selected=true;
		     }else {
		       optionNode1.selected=true;
		     }
		     
		     selectNode.appendChild(optionNode);
		     selectNode.appendChild(optionNode1);
		     dirNode.appendChild(selectNode);//将下拉表加入层
	         tdNode.appendChild(dirNode);//将层节点加入列
		         
		     var tdNode1=trNode.insertCell(1);//插入一列
		     var dirNode1 = document.createElement("div");//创建一个层
		     dirNode1.setAttribute("align","center");//层居中
		     var selectNode1 = document.createElement("select");//创建一个下拉列表 
		       
		     for(var i=0;i<ret.length;i++){
		         var date = ret[i].split(",");
		         var key = date[0]; 
		         var value = date[1]; 
		         var optionNode2 = document.createElement("option");
		         optionNode2.innerHTML = value;
				 optionNode2.value = key;
				 if(value==item){
				   optionNode2.selected=true;
				 }
		         selectNode1.appendChild(optionNode2);
		         dirNode1.appendChild(selectNode1);//层中加入select
		         tdNode1.appendChild(dirNode1);//将层节点加入列
		     }
		  
		  var  condition =['大于','小于','等于','包含','不包含'];
		  var tdNode2=trNode.insertCell(2);//插入一列
		  var dirNode2 = document.createElement("div");//创建一个层
		  dirNode2.setAttribute("align","center");//层居中
		  var selectNode2 = document.createElement("select");//创建一个下拉列表      
		  for(var j=0;j<condition.length;j++){  
              var con =condition[j];
              var optionNode3 = document.createElement("option");
		      optionNode3.innerHTML = con;
		      optionNode3.value = j;
		      if(fuhao==j){
		          optionNode3.selected = true;
		      }
		      selectNode2.appendChild(optionNode3);
		      dirNode2.appendChild(selectNode2);//层中加入select
		      tdNode2.appendChild(dirNode2);//将层节点加入列	 			  
		  } 
		   
		  var tdNode3=trNode.insertCell(3);//插入一列
		  var dirNode3 = document.createElement("div");//创建一个层
		  dirNode3.setAttribute("align","center");//层居中
		  var inputNode = document.createElement("input");
		  inputNode.value=vl;
		  dirNode3.appendChild(inputNode);
		  tdNode3.appendChild(dirNode3);//将层节点加入列	 
		   
		  var gx = ["and","or"]; 
		  var tdNode4=trNode.insertCell(4);//插入一列
		  var dirNode4 = document.createElement("div");//创建一个层
		  dirNode4.setAttribute("align","center");//层居中
		  var selectNode3 = document.createElement("select");//创建一个下拉列表     
		  for(var k=0;k<gx.length;k++){   
		      var guxi =gx[k];
		      var optionNode4 = document.createElement("option");
		      optionNode4.innerHTML = guxi;
		      optionNode4.value = k;
		      if(guanxi==k){
		         optionNode4.selected =true;
		      }
		      selectNode3.appendChild(optionNode4);
		      dirNode4.appendChild(selectNode3);//层中加入select
		      tdNode4.appendChild(dirNode4);//将层节点加入列	
		  }
		  
		     var tdNode5=trNode.insertCell(5);//插入一列
	         var dirNode5 = document.createElement("div");//创建一个层
	         dirNode5.setAttribute("align","center");//层居中
	         var selectNode4 = document.createElement("select");//创建一个下拉列表
	         var optionNode5 = document.createElement("option");
	         optionNode5.innerHTML = "";
			 optionNode5.value = "0";
			 var optionNode6 = document.createElement("option");
	         optionNode6.innerHTML = ")";
		     optionNode6.value = "1";
		     if(rkuohao=='0'){
		       optionNode5.selected = true;
		     }else {
		       optionNode6.selected = true;
		     }
		     selectNode4.appendChild(optionNode5);
		     selectNode4.appendChild(optionNode6);
		     dirNode5.appendChild(selectNode4);//将下拉表加入层
	         tdNode5.appendChild(dirNode5);//将层节点加入列
	   }
	   
	   function toGetValue(){
	        var quiryTaeble = document.getElementById("seachTable").value;
	        var quirytab = document.getElementById("seachtab").value;
	        var searchTaeble =quiryTaeble.split(",");
	        var searchTabl=quirytab.split(",");
	        var retDate ="";
	        var retSql = "";
	        var dbitem = "";
	        var oTable =  document.getElementById("inquiry");//得到表格
	        var oRow = oTable.rows;//得到行
	 	    var oRowLen = oRow.length;//得到行
	 	    chekleftflg = "";
	 	    chekrigetflg = "";
	 	    for(var i=1;i<oRowLen;i++){
	 	       var oCell =oRow[i].cells;
	 	       var oCellLen = oCell.length;
	 	       var flg = "0";
	 	       var subRetSql = "";
	 	       var subDbitem= "";
	 	       for(var j=0;j<oCellLen;j++){
	 	          var child = oCell[j].firstChild.firstChild;
	 	          if(child.nodeName=='SELECT'){
	 	             var index=child.selectedIndex;
	 	             retDate = retDate+child.options[index].text+" ";
	 	             
	 	             if (j==0){
	 	               subRetSql = subRetSql+child.options[index].text+" "
	 	               if (""==child.options[index].text || child.options[index].text == null){
	 	                chekleftflg = "0";
	 	               }else{
	 	               chekleftflg = "1";
	 	               }
	 	               subDbitem = child.options[index].value;
	 	             }else{
	 	               subDbitem = subDbitem+"/"+child.options[index].value;
	 	             }
	 	            if (j==1){
	 	              if (searchTaeble.length>1){
	 	              var itemText =(child.options[index].text).split(":");
                       for(var m =0;m<searchTaeble.length;m++){
                          if ((searchTaeble[m]).toLocaleUpperCase()==itemText[0]){
                            subRetSql = subRetSql +searchTabl[m]+"."+child.options[index].value+" ";
                          }
                       }
	 	              }else{
	 	                 subRetSql = subRetSql +searchTabl[0]+"."+child.options[index].value+" ";
	 	              }

	 	             }
	 	            if (j==2){
	 	               if (index==0){
	 	                  subRetSql = subRetSql +">  '";
	 	               }
	 	               if (index==1){
	 	                  subRetSql = subRetSql +"<  '";
	 	               }
	 	               if (index==2){
	 	                  subRetSql = subRetSql +"=  '";
	 	               }
	 	               if (index==3){
	 	                  subRetSql = subRetSql +"like '";
	 	                  flg = "1";
	 	               }
	 	             }
	 	            if (j==4){
	 	               subRetSql = child.options[index].text +" "+ subRetSql +" ";
	 	             }	 	
	 	            if (j==5){
	 	               subRetSql =  subRetSql +child.options[index].text +" ";
	 	             if (""==child.options[index].text || child.options[index].text == null){
	 	                chekrigetflg = "0";
	 	               }else{
	 	               chekrigetflg = "1";
	 	               }
	 	             }	 	                          
	 	          }else{
	 	             retDate = retDate+child.value+" ";
	 	             subDbitem = subDbitem+"/"+child.value;
	 	             if ("1"==flg){
	 	              subRetSql = subRetSql+child.value+"%' ";
	 	             }else{
	 	               subRetSql = subRetSql+child.value+"' ";
	 	             }
	 	             
	 	          }
	 	       }
	 	       retSql = retSql+" "+subRetSql;
	 	       if (i==1){
	 	         dbitem =subDbitem;
	 	       }else{
	 	         dbitem =dbitem +","+subDbitem;
	 	       }
	 	    }
	 	    if ((chekleftflg == "1" && chekrigetflg == "0")||
	 	    (chekleftflg == "0" && chekrigetflg == "1")){
	 	       alert("请选择左右括号");
	 	    }else{
	 	       var Url=getPublicPath()+"/SeachServlet?dbitem="+encodeURI(dbitem)+"&quiryTaeble="+quiryTaeble+"&seachtab="+seachtab;
	 	     	new Ajax.Request(Url,{
	      		method : 'post',
				asynchronous: false,
				onComplete : function (resp){
				}
			   }
			  )
	 	    opener.getValue(retDate,retSql);
	 	    window.close();
	 	    }

	   }
	   
	</script>
  </head>
  
  <body onload="initDate();">
  <div>
	  <table width='100%' border='0' cellspacing='1' cellpadding='1' bgcolor='#cbd5de' id='inquiry'>
	        <tr height = '20'>
	        	<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>左括号</div></td>
	        	<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>查询条件</div></td>
	        	<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>条件</div></td>
	         	<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>查询值</div></td>
	         	<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>连接符</div></td>
	         	<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>右括号</div>
	         	</td>
	        </tr>
	   </table>
    </div>
    <div>
       <input type="button" value="增加" onclick="toGetDate();"/>
       <input type="button" value="确定" onclick ="toGetValue();"/>
       <input type="hidden" value="<%=table%>" id="seachTable" name="seachTable" class="form"/>
       <input type="hidden" value="<%=tab%>" id="seachtab" name="seachtab" class="form"/>
    </div>
  </body>
</html>
