<jsp:useBean id="rwdBean" scope="page" class="com.noki.mobi.common.RWDBean">
</jsp:useBean>
<%

      String param = request.getParameter("pageUrl");
      String roleId = request.getParameter("roleId");
      int j = rwdBean.getRWD(param,roleId);
%>
<script type="text/javascript" language="javascript">

		var oEle = document.getElementsByName('id1')
		var j = "<%=j%>";
		if(j==0){
			for(var i = 0;i<oEle.length;i++){
				if(oEle[i].name.charAt(oEle[i].name.length-1)!="0"){
						oEle[i].disabled="disabled";
				}
			}
		}else if(j==1){
			for(var i = 0;i<oEle.length;i++){
				if(oEle[i].name.charAt(oEle[i].name.length-1)>1){
						oEle[i].disabled="disabled";
				}
			}
		}else if(j==2){
			for(var i = 0;i<oEle.length;i++){
				if(oEle[i].name.charAt(oEle[i].name.length-1)!=2){
						oEle[i].disabled="disabled";
				}
			}
		}else if(j==2){
			for(var i = 0;i<oEle.length;i++){
				if(oEle[i].name.charAt(oEle[i].name.length-1)>3){
						oEle[i].disabled="disabled";
				}
			}
		}
		

</script>
