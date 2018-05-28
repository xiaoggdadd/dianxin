<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.lang.Double"%>
<%@ page import="com.noki.database.DataBase"%>
<%@ page import="com.noki.common.OrgAndRole"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.List"%>
<%@ page
	import="java.util.ArrayList,java.util.Date,com.noki.mobi.common.Account,java.text.DecimalFormat"%>
<%@ page
	import="com.noki.util.CTime,com.noki.jizhan.SiteManage,com.noki.mobi.common.CommonBean"%>
<%@ page import="com.noki.jizhan.SiteModifyBean"%>
<%@page import="java.math.BigDecimal"%>
<%
    String path = request.getContextPath();
    String shengId = (String)session.getAttribute("accountSheng");
    String loginName = (String)session.getAttribute("loginName");
    Account account = (Account)session.getAttribute("account");
    
    String accountname = account.getAccountName();
    SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd");
    String entrytime = mat.format(new Date());
    String dbid = request.getParameter("dbid");
    System.out.println("dbid?>?>?>?>?>?>" + dbid);
    SiteManage bean = new SiteManage();
    ArrayList fylist = new ArrayList();
    fylist = bean.getPUEByDbid(dbid);
    List<String> x_addtime=new ArrayList<String>();//时间
    List<String> y_pue=new ArrayList<String>();//pue值
    String addtime_s = "",puezhi_s = "";
    if(fylist!=null)
	{
    	
		int fycount = ((Integer)fylist.get(0)).intValue();
		for( int k = fycount;k<fylist.size()-1;k+=fycount){
			puezhi_s = (String) fylist.get(k + fylist.indexOf("PUEZHI"));
			addtime_s = (String) fylist.get(k + fylist.indexOf("CREATEDATE"));
			if(puezhi_s==null || puezhi_s.equals("")){
				puezhi_s="0";
			}
			y_pue.add(puezhi_s);
			x_addtime.add(addtime_s);
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>PUE曲线</title>
		<script type="text/javascript"
			src="<%=path%>/web/appJS/pageJs/My97DatePicker/WdatePicker.js">
		</script>
		<link href="../css/content.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/javascript/jQuery-ComboSelect/css/combo.select.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/javascript/notnull.js">
		</script>
		<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
		<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	
		</script>
		<script type="text/javascript" src="<%=path%>/javascript/wait.js">
		</script>
		<script type="text/javascript"
			src="<%=path%>/javascript/PopupCalendar.js">
		</script>
		<script type="text/javascript"
			src="<%=path%>/javascript/PopupCalendar_ny.js">
		</script>
<script type="text/javascript" src="<%=path%>/javascript/jquery-3.3.1.min.js"> </script>
<script src="<%=path%>/javascript/jquery-migrate-1.2.1.js"></script>
	
<script type="text/javascript" src="<%=path%>/javascript/jQuery-ComboSelect/js/jquery.combo.select.js"></script>
<script type="text/javascript"src="<%=path%>/javascript/echarts.js"></script>
		<script type="text/javascript">
//		$(function() {
//	$('#rsname').comboSelect();
//});
var path = '<%=path%>';
</script>
	</head>
	<jsp:useBean id="commBean" scope="page"
		class="com.noki.mobi.common.CommonBean">
	</jsp:useBean>
	<jsp:useBean id="diaobiaoBean" scope="page"
		class="com.noki.jizhan.DianBiaoBean">
	</jsp:useBean>
	<jsp:useBean id="ztcommon" scope="page"
		class="com.noki.mobi.common.ZtCommon">
	</jsp:useBean>
	<body>
		<form action="" name="form1" method="post">
			<input type="hidden" name="dbid" value="<%=dbid%>"
				style="box-sizing: border-box; width: 130px" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="top">

					<td width="12">
						<img src="../images/space.gif" width="12" height="17" />
					</td>
					<td>
						<div class="content01">
							<div class="tit01">
								PUE曲线
							</div>
							<div class="content01_1" style="padding:0px 0px;width:100%;height:215px; ">
							  <div id="chart" style="width:100%;height:215px;"></div>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
		var y_pue_js=new Array();
		<%
			for(int j=0;j<y_pue.size();j++){
				String s = y_pue.get(j);
				%>
				y_pue_js[<%=j%>]='<%=s%>';
				<%
			}
		%>
		
		var x_addtime_js=new Array();
		<%
			for(int j=0;j<x_addtime.size();j++){
				String s = x_addtime.get(j);
				%>
				x_addtime_js[<%=j%>]='<%=s%>';
				<%
			}
		%>

    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('chart'));
    options = {
    		 legend:{
    	            data:['PUE值']
    	        },
    		lineStyle:{
                normal:{
                    width:10,  //连线粗细
                    color: "#00BFFF"  //连线颜色
                }
            },
            itemStyle: {
                normal: {
                    borderWidth: 3,
                    borderColor: "#009ACD",
                    color: "#0000EE"
                }
            },
            label: {
            	normal: {
                show: true
            	}
            },
    	    xAxis: {
    	        type: 'category',
            	data:x_addtime_js
    	    },
    	    yAxis: {
    	        type: 'value'
    	    },
    	    series: [
    	    	 {
    	             type: 'line',
    	             data:y_pue_js
    	         }
    	       
    	    ]
    	};

    myChart.setOption(options);
</script>

	</body>
</html>
