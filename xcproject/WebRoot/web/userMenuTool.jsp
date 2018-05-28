<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  </head>
  
  <body style="overflow:-Scroll;overflow-y:hidden;position:absolute; width:100%px; left: 0px; top: 0px; padding:0px;background-color: #d6e3f2;border-bottom-color: #99bbe8;border-bottom-style: solid;border-bottom-width: 0px;border-left-color: #99bbe8;border-left-style: solid;border-left-width: 0px;border-top-color: #99bbe8;border-top-style: solid;border-top-width: 0px;border-right-color: #99bbe8;border-right-style: solid;border-right-width: 0px;margin: 0px;">
   <table style="width: 400px;height: 599px;" cellpadding="0"
					cellspacing="0">
			<tr>
				<td style="width: 100%;height: 26px;" align="center" colspan="3">
					<label style="color:#ffffff;font-size: 12px;font-family: 微软雅黑">常用菜单设置窗口</label>
				</td>
			</tr>
			<tr>
				<td style="width: 200px;height: 100%;background-color: #FFFFFF;" align="center">
					<iframe src="leftWindow.jsp" name="leftWindow" style="width: 100%;height: 100%" name="left" frameborder="0" noresize scrolling="auto"  marginwidth=0 ></iframe>
				</td>
				<td style="width: 200px;height: 100%;background-color: #FFFFFF;" align="center">
					<iframe src="rightWindow.jsp" name="rightWindow"  style="width: 100%;height: 100%" name="left" frameborder="0" noresize scrolling="auto"  marginwidth=0 ></iframe>
				</td>
			</tr>
		</table>
  </body>
</html>
