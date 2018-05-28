<%@ page contentType="text/html; charset=utf-8" %><%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.*"%>

<%@ page import="com.noki.mobi.common.Right" %>
<%@ page import="com.noki.mobi.common.Account" %>

<%

//得到业务员信息
//String path = request.getContextPath();
Account account = (Account)session.getAttribute("account");
        String accountId = account.getName();//用户登陆名称
Right right = new Right();
ArrayList rightList = new ArrayList();
rightList = right.selectWebRightByAccountId(account.getAccountId());
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<link rel="StyleSheet" href="tree/dtree.css" type="text/css" />
	<script type="text/javascript" src="tree/dtree.js"></script>
	<script type="text/javascript" src="../javascript/jquery-1.4.2.js"></script>
</head>
<body style="overflow-x:visible;overflow-y:visible" leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">

<script src="<%=path%>/javascript/wait.js"></script>
	  <script type="text/javascript">
    d = new dTree('d','tree/img/');
	d.add(0,-1,'首页',"","","");
<%
    int j=0;
    
  String parent="0",block="0",rightId="";
	for(int i=j;i<rightList.size();i++){
	
	     right=(Right)rightList.get(i);
		 String url = right.getUrl();
		 if(url==null)
		    url="";
		 
		 String rightName = right.getName();
		 block = right.getBlock();
		 rightId = right.getRightId();
		 String divlable = rightId.substring(2,4);
		 if(!block.trim().equals("0")){
			if(!block.equals("0")){ 
				if(rightId.substring(2).equals("00")){//id后俩位是 00 的是父节点 
					parent="0";
				}else if(rightId.length()==4&&!rightId.substring(2).equals("00")){
					parent=rightId.substring(0,2)+"00";//如果不是父节点，取 前两位 加00 就是 该子节点的父节点  id号 
				}else if(rightId.length()==6){
					parent=rightId.substring(0,4);
				}
%>
						d.add('<%=rightId%>','<%=parent%>','<%=rightName%>',null,null,null,"tree/treeimg/folder_close.gif","tree/treeimg/folder_open.gif",false);

<%
      }else{
 %>
      	   d.add('<%=rightId%>',0,"<%=rightName%>","\'\'",null,null,null,null,false);
<%
    	}
     
	} else{
			if(rightId.length()==4){
				parent=rightId.substring(0,2)+"00";
			}else if(rightId.length()==6){
				parent=rightId.substring(0,4);
			}else if(rightId.length()==8){
				parent=rightId.substring(0,6);
			}
		%>
			<%
			String tempPath = path;
			if (url.indexOf("UEAnalyze")>0){
				tempPath = url;
			}else{
				tempPath += url;
			}%>
		  
		   d.add('<%=rightId%>','<%=parent%>','<%=rightName%>','rightWindow.jsp','','rightWindow',"tree/treeimg/node.gif","tree/treeimg/node.gif",false);
	<%}

}%>
	d.s=function(nodeId){ //添加节点点击事件   
	   var node = this.aNodes[nodeId];
       var n_name = this.aNodes[nodeId].name;
       var n_id = this.aNodes[nodeId].id;
       var n_fid = this.aNodes[nodeId].pid;
       $.post(
    					"rightWindow.jsp",
    					{name:n_name,id:n_id,fid:n_fid},
    					function(data,status){
							
						}
    				);
    };
 document.write(d);
	</script>
</body>
</html>
