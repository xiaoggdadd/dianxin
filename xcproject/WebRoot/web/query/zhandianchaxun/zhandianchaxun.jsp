<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*,com.noki.mobi.common.Account" %>
<%@ page import="com.noki.zhandian.*" %>
<%@ page import="java.text.*"%>
<%
	String path = request.getContextPath();
	Account account = (Account)session.getAttribute("account");
        String roleId = account.getRoleId();
    String ammeterid = request.getParameter("ammeterid");
    DecimalFormat mat=new DecimalFormat("0.00");
 
%>
<html>
<head>
<title>
addDegree
</title>

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
.selected_font{
		width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.form{
	//	width:130px;
		font-family: 宋体;
		font-size:12px;
		line-height:120%;
		
}


bttcn{color:white;font-weight:bold;}
 </style>
 <script src="<%=path%>/javascript/PopupCalendar.js"></script>
 <script src="<%=path%>/javascript/PopupCalendar_ny.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>
 <script type="text/javascript" src="<%=path%>/javascript/notnull.js"></script>
 <script src="<%=path%>/javascript/jquery-1.4.2.js"></script>

<script language="javascript">
var path = '<%=path%>';
       function showIdList(){
          window.open('zhandianliulan.jsp','','width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=200,top=200')
        }
        $(function(){
			$("#liulan").click(function(){
				showIdList();
			});
		});
</script>
</head>
<jsp:useBean id="roleBean" scope="page" class="com.noki.mobi.sys.javabean.RoleBean">
</jsp:useBean>
<jsp:useBean id="commBean" scope="page" class="com.noki.mobi.common.CommonBean">
</jsp:useBean>
<body  class="body" style="overflow-x:hidden;">

<form action="" name="form1" method="POST">
<% 
			String address="";
			String jzname = "";
			String zdid="";
			String diqu="";
			String zdtype="";
			String zdgs="";
			String gdfs="";
			String begin="";
			String dbbl="";
			String fzr="";
			String jfcs="";
			String skdw="";
			String jffs="";
			String dfz="";
			String end="";
			String sf="";
			String whereStr = "";
			String scjf="";
			String zhjf="";
			String dd="";
			String al="";
	if(ammeterid!=null && ammeterid!=""){
	whereStr = whereStr + " and zd.id ='" + ammeterid + "'";
			ZhanDianDao dao = new ZhanDianDao();
			ZhandianBean zb = new ZhandianBean();
			List<ZhandianBean> list = dao.getZD(whereStr);
			
			for(ZhandianBean z:list){
				address=z.getAddress();
				zdid=z.getZhandianID();
				jzname = z.getZdname();
				diqu = z.getXian();
				zdtype=z.getZdtype();
				zdgs=z.getZdgs();
				gdfs=z.getGdfs();
				begin=z.getBegin();
				dbbl=z.getDbbl();
				fzr=z.getFzr();
				jfcs=z.getJfcs();
				skdw=z.getSkdw();
				jffs=z.getJffs();
				dfz=z.getDfz();
				end=z.getEnd();
				scjf=z.getScjf();
				zhjf=z.getZhjf();
				
				
				if("null".equals(zdid)||zdid==null){
				zdid="";
				}
				if("null".equals(jzname)||jzname==null){
				jzname="";
				}
				if("null".equals(diqu)||diqu==null){
				diqu="";
				}
				if("null".equals(zdtype)||zdtype==null){
				zdtype="";
				}
				if("null".equals(zdgs)||zdgs==null){
				zdgs="";
				}
				if("null".equals(gdfs)||gdfs==null){
				gdfs="";
				}
				if("null".equals(jfcs)||jfcs==null){
				jfcs="";
				}
				if("null".equals(fzr)||fzr==null){
				fzr="";
				}
				if("null".equals(skdw)||skdw==null){
				skdw="";
				}
				if("null".equals(address)||address==null){
				address="";
				}
				if(jfcs!=""&&jfcs!=null&&!jfcs.equals("null")){
					sf="是";
				}else{
					sf="否";
				}
				if("null".equals(scjf)||scjf==null){
				scjf="";
				}
				if("null".equals(zhjf)||zhjf==null){
				zhjf="";
				}
				 
				al=mat.format(Double.parseDouble(dfz));
				
			}
	}
{
%>
  <table  width="100%"  border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan=3 width="600" height=63>
				<div style="width:700px;height:50px">
			       
			       <span style="font-size:14px;font-weight:bold;position: absolute;left:25px;top:18px;color:black">站点信息一键查询</span>	
				</div></td>
	   </tr>
	   <tr bgcolor="F9F9F9">
	     <td height="19" colspan="4"><font size="2">   &nbsp;  基本信息</font></td>
	   </tr>
   </table>
                    
		   <table width="100%" border="0" cellspacing="1" cellpadding="1" class="form_label">          
		      <tr>
		         <td height="19" bgcolor="#DDDDDD" width="10%"><div>站点ID：</div></td>
		         <td width="13%"><input type="text" name="ammeteridFk" value="<%=zdid %>" class="form" readonly="false" style="width:60%"/><span class="style1">&nbsp;*</span>
		           <img id="liulan" alt="浏览" src="<%=request.getContextPath()%>/images/liulan1.png" style="cursor: pointer；width:15px;height: 15px;"/>
		         </td>
		         <td height="19" bgcolor="#DDDDDD" width="10%"><div>站点名称：</div></td>
		         <td width="38%" colspan="3"><input type="text" name="jzname" value="<%=jzname %>" readonly="false" class="form" style="width:96%" /></td>
		         <td height="19" bgcolor="#DDDDDD" width="10%"><div>缴费次数：</div></td>
		         <td width="13%"><input type="text" name="jfcs" value="<%=jfcs %>" readonly="false" class="form" style="width:90%"/></td>
		      </tr>
		
		      <tr>
		         <td height="19" bgcolor="#DDDDDD" width="12%"><div>区县：</div></td>
		         <td width="13%"><input type="text" name="quxian" value="<%=diqu %>"  class="form" readonly="false" style="width:90%"/></td>
		         <td height="19" bgcolor="#DDDDDD" width="12%"><div>基站位置：</div></td>
		         <td width="38%" colspan="3"><input type="text" name="address" value="<%=address %>" readonly="false" class="form" style="width:96%"/></td>
		         <td height="19" bgcolor="#DDDDDD" width="15%"><div>起始时间：</div></td>
		         <td width="35%"><input type="text" name="begin" value="<%=begin %>" readonly="false" class="form" style="width:90%"/></td>
		       </tr>
		      <tr>
			      <td height="19" bgcolor="#DDDDDD" width="12%"><div>收款单位：</div></td>
			      <td width="38%" colspan="3"><input type="text" name="skdw" value="<%=skdw %>"  class="form" readonly="false" style="width:96%"/></td>
			      <td height="19" bgcolor="#DDDDDD" width="12%"><div>负责人：</div></td>
			      <td><input type="text" name="fzr" value="<%=fzr %>"  class="form"  readonly="false" style="width:90%"/></td>
			      <td height="19" bgcolor="#DDDDDD" width="12%"><div>结束时间：</div></td>
			      <td width="13%"><input type="text" name="end" value="<%=end %>" readonly="false" class="form" style="width:90%" /></td>
		      </tr>
		        <tr>
		      	 <td></td>
		         <td></td>
		         <td></td>
		         <td></td>
		         <td></td>
		         <td></td>
		      	 <td height="12" bgcolor="#DDDDDD" width="8%"><div>电费总额：</div></td>
		         <td width="13%"><input type="text" name="dfz" value="<%=al %>" readonly="false" class="form" style="width:90%" /></td>
		      </tr>
		       
		     <tr>
		     	 <td height="19" bgcolor="#DDDDDD" width="12%"><div>交电费否：</div></td>
		         <td width="13%"><input type="text" name="sfjf" value="<%=sf %>" readonly="false" class="form" style="width:90%"/></td>
		         <td height="19" bgcolor="#DDDDDD" width="12%"><div>站点类别：</div></td>
		         <td width="13%"><input type="text" name="zdtype" value="<%=zdtype %>" readonly="false" class="form" style="width:90%"/></td>
		      	 <td height="19" bgcolor="#DDDDDD" width="12%"><div>供电方式：</div></td>
		         <td width="13%"><input type="text" name="gdfs" value="<%=gdfs %>" readonly="false" class="form" style="width:90%"/></td>
		          <td height="19" bgcolor="#DDDDDD" width="12%"><div>首次缴费：</div></td>
		         <td width="13%"><input type="text" name="scjf" value="<%=scjf %>" readonly="false" class="form" style="width:90%" /></td>
		      </tr>
		      <tr>
		      	 <td height="19" bgcolor="#DDDDDD" width="12%"><div>站点归属：</div></td>
		         <td width="13%"><input type="text" name="zdgs" value="<%=zdgs %>" readonly="false" class="form" style="width:90%"/> </td>
		         <td height="19" bgcolor="#DDDDDD" width="12%"><div>电表倍率：</div></td>
		         <td width="13%"><input type="text" name="dbbl" value="<%=dbbl %>" readonly="false" class="form" style="width:90%"/> </td>
		          <td height="19" bgcolor="#DDDDDD" width="12%"><div>缴费方式：</div></td>
		         <td width="13%"><input type="text" name="jffs" value="<%=jffs %>" readonly="false" class="form" style="width:90%" /></td>
		          <td height="19" bgcolor="#DDDDDD" width="12%"><div>最后缴费：</div></td>
		         <td width="13%"><input type="text" name="zhjf" value="<%=zhjf %>" readonly="false" class="form" style="width:90%" /></td>
		      </tr> 
		</table>
		<%}%>
		<hr/>
		 <table width="100%" border="0" cellspacing="1" cellpadding="1" class="form_label">
		 	<tr>
				<td align="left" width="40%" >费用汇总</td>
				<td align="left" width="60%">缴费明细</td>
			</tr>
			<tr>
				<td valign="top">
					 <table width="100%" border="0" cellspacing="1" cellpadding="1" class="form_label" >		
						<tr>
							<td width="15%" height="23" bgcolor="#DDDDDD"><div align="center">年月</div></td>
							<td width="14%" height="23" bgcolor="#DDDDDD"><div align="center">实交金额</div></td>
							<td width="14%" height="23" bgcolor="#DDDDDD"><div align="center">实交度数</div></td>
							<td width="14%" height="23" bgcolor="#DDDDDD"><div align="center">实交天数</div></td>
							<td width="14%" height="23" bgcolor="#DDDDDD"><div align="center">日均度数</div></td>
							<td width="14%" height="23" bgcolor="#DDDDDD"><div align="center">日均金额</div></td>
							<td width="15%" height="23" bgcolor="#DDDDDD"><div align="center">ID</div></td>
						</tr>
						<%
								String time="";
								double jine=0.0;
								double dushu=0.0;
								int day=0;
								double mds=0.0;
								double mje=0.0;
								int intnum=0;
								String color="";
								String amid ="";
								if(ammeterid!=null && ammeterid!=""){
									ZhanDianDao dao = new ZhanDianDao();
									List<ZDFeiyongBean> list = dao.getFYHZ(ammeterid);
									System.out.println(list);
									for(ZDFeiyongBean zz:list){
										time=zz.getTime();
										jine=zz.getSjje();
										dushu=zz.getSjds();
										day=zz.getDays();
										mje=jine/day;									
										mds=dushu/day;
										amid=zz.getAmid();
										if(intnum%2==0){
			   							 color="#FFFFFF";

										 }else{
			  							  color="#DDDDDD" ;
										}
           									intnum++;
									
							 %>
						<tr bgcolor="<%=color%>" >
							<td height="23">
								<div align="right"><%=time%></div>
							</td>
							<td height="23">
								<div align="right"><%=mat.format(jine)%></div>
							</td>
							<td height="23">
								<div align="right"><%=mat.format(dushu)%></div>
							</td>
							<td height="23">
								<div align="right"><%=day%></div>
							</td>
							<td height="23">
								<div align="right"><%=mat.format(mds)%></div>
							</td>
							<td height="23">
								<div align="right"><%=mat.format(mje)%></div>
							</td>
							<td height="23">
								<div align="right"><a target="treeNodeInfo" name="test" href=<%=path+"/web/query/zhandianchaxun/zdchaxunright.jsp?ammeterid="+ammeterid+"&time="+time+"&amid="+amid+"&bzw=1"%>><%=ammeterid%></a></div>
							</td>
							
						</tr>
			 
						<%} }%>
					</table>
				</td>
				<td><!-- background-position-x ="52%"  450px -->
					<iframe name="treeNodeInfo"   width="100%" height="200px" frameborder="0" src="<%=path %>/web/query/zhandianchaxun/zdchaxunright.jsp"></iframe>
				</td>				
			</tr>
		</table>
		
  </form>		
    	
  
    
  </body>
</html>
<script language="javascript">
var path = '<%=path%>';    
    function modifyad(ammeterid,time){
    	var b=path+"/web/query/zhandianchaxun/zdchaxunright.jsp?ammeterid="+ammeterid+"&bzw=1&time="+time+"&";			
			b = b.substring(0,b.length-1);
			 var a = " <a href="+b+" target='treeNodeInfo' id='tmpTree'></a>";
			$("#tmpTree").remove();
			$("body").append(a);
			$("#tmpTree")[0].click(); 
    } 
</script>
