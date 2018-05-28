<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
         <!-- 实例化一个类      对象名为bean -->
		<jsp:useBean id="bean" scope="page"      
			class="com.noki.mobi.sys.javabean.NoticeBean">
		</jsp:useBean>

		<title>管理控制台</title>
		<script language="javascript">
			function infoNotice(noticeId) {
				window.open('sys/infoNotice.jsp?noticeId=' + noticeId, '',
								'width=700,height=500,status=yes,scrollbars=yes,resizable=yes,left=150,top=100')
			}
		</script>
		<style>
			.btn {
				BORDER-RIGHT: #7b9ebd 1px solid;
				PADDING-RIGHT: 2px;
				BORDER-TOP: #7b9ebd 1px solid;
				PADDING-LEFT: 2px;
				FONT-SIZE: 12px;
				FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
					StartColorStr = #ffffff, EndColorStr = #cecfde );
				BORDER-LEFT: #7b9ebd 1px solid;
				CURSOR: hand;
				COLOR: black;
				PADDING-TOP: 2px;
				BORDER-BOTTOM: #7b9ebd 1px solid
			}
			
			.btn1_mouseout {
				BORDER-RIGHT: #7EBF4F 1px solid;
				PADDING-RIGHT: 2px;
				BORDER-TOP: #7EBF4F 1px solid;
				PADDING-LEFT: 2px;
				FONT-SIZE: 12px;
				FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
					StartColorStr = #ffffff, EndColorStr = #B3D997 );
				BORDER-LEFT: #7EBF4F 1px solid;
				CURSOR: hand;
				COLOR: black;
				PADDING-TOP: 2px;
				BORDER-BOTTOM: #7EBF4F 1px solid
			}
			
			.btn1_mouseover {
				BORDER-RIGHT: #7EBF4F 1px solid;
				PADDING-RIGHT: 2px;
				BORDER-TOP: #7EBF4F 1px solid;
				PADDING-LEFT: 2px;
				FONT-SIZE: 12px;
				FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
					StartColorStr = #ffffff, EndColorStr = #CAE4B6 );
				BORDER-LEFT: #7EBF4F 1px solid;
				CURSOR: hand;
				COLOR: black;
				PADDING-TOP: 2px;
				BORDER-BOTTOM: #7EBF4F 1px solid
			}
			
			.btn2 {
				padding: 2 4 0 4;
				font-size: 12px;
				height: 23;
				background-color: #ece9d8;
				border-width: 1;
			}
			
			.btn3_mouseout {
				BORDER-RIGHT: #2C59AA 1px solid;
				PADDING-RIGHT: 2px;
				BORDER-TOP: #2C59AA 1px solid;
				PADDING-LEFT: 2px;
				FONT-SIZE: 12px;
				FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
					StartColorStr = #ffffff, EndColorStr = #C3DAF5 );
				BORDER-LEFT: #2C59AA 1px solid;
				CURSOR: hand;
				COLOR: black;
				PADDING-TOP: 2px;
				BORDER-BOTTOM: #2C59AA 1px solid
			}
			
			.btn3_mouseover {
				BORDER-RIGHT: #2C59AA 1px solid;
				PADDING-RIGHT: 2px;
				BORDER-TOP: #2C59AA 1px solid;
				PADDING-LEFT: 2px;
				FONT-SIZE: 12px;
				FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
					StartColorStr = #ffffff, EndColorStr = #D7E7FA );
				BORDER-LEFT: #2C59AA 1px solid;
				CURSOR: hand;
				COLOR: black;
				PADDING-TOP: 2px;
				BORDER-BOTTOM: #2C59AA 1px solid
			}
			
			.btn3_mousedown {
				BORDER-RIGHT: #FFE400 1px solid;
				PADDING-RIGHT: 2px;
				BORDER-TOP: #FFE400 1px solid;
				PADDING-LEFT: 2px;
				FONT-SIZE: 12px;
				FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
					StartColorStr = #ffffff, EndColorStr = #C3DAF5 );
				BORDER-LEFT: #FFE400 1px solid;
				CURSOR: hand;
				COLOR: black;
				PADDING-TOP: 2px;
				BORDER-BOTTOM: #FFE400 1px solid
			}
			
			.btn3_mouseup {
				BORDER-RIGHT: #2C59AA 1px solid;
				PADDING-RIGHT: 2px;
				BORDER-TOP: #2C59AA 1px solid;
				PADDING-LEFT: 2px;
				FONT-SIZE: 12px;
				FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
					StartColorStr = #ffffff, EndColorStr = #C3DAF5 );
				BORDER-LEFT: #2C59AA 1px solid;
				CURSOR: hand;
				COLOR: black;
				PADDING-TOP: 2px;
				BORDER-BOTTOM: #2C59AA 1px solid
			}
			
			.btn_2k3 {
				BORDER-RIGHT: #002D96 1px solid;
				PADDING-RIGHT: 2px;
				BORDER-TOP: #002D96 1px solid;
				PADDING-LEFT: 2px;
				FONT-SIZE: 12px;
				FILTER: progid : DXImageTransform.Microsoft.Gradient ( GradientType = 0,
					StartColorStr = #FFFFFF, EndColorStr = #9DBCEA );
				BORDER-LEFT: #002D96 1px solid;
				CURSOR: hand;
				COLOR: black;
				PADDING-TOP: 2px;
				BORDER-BOTTOM: #002D96 1px solid
			}
			
			.style1 {
				color: #FF9900;
				font-weight: bold;
			}
			
			body {
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
			}
		</style>

	</head>

	<body class="body" style="overflow-x: hidden;">
		<script type="text/javascript">
			function ShowHideSearchRegion(trObject, SelfObject) {
				if (trObject.style.display == "none") {
					trObject.style.display = ""
					SelfObject.innerHTML = "<img border=\"0\" src=\"../images/1.gif\">"
			
				} else {
					trObject.style.display = "none"
					SelfObject.innerHTML = "<img border=\"0\" src=\"../images/SearchDown.gif\">"
				}
			}
		</script>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top" bgcolor="FBFBFD">
					<table width="728" height="500" border="0" align="center"
						cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="5">
								<img src="../images/index_l.jpg" width="360" height="320" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
