<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList,com.noki.mobi.common.Account,java.util.Vector" %>
<%@ page import="java.text.DecimalFormat,com.noki.util.CTime"%>
<%

        Account account = new Account();
        account = (Account)session.getAttribute("account");
		CTime time = new CTime();
		String accountId = account.getAccountId();
                String path = request.getContextPath();
		String month = String.valueOf(Integer.parseInt(time.formatShortDate().substring(4, 6)));
		String year = String.valueOf(Integer.parseInt(time.formatShortDate().substring(0,4)));
        String qijian = request.getParameter("qijian")!=null?request.getParameter("qijian"):month;
        String dw = request.getParameter("dw")!=null?request.getParameter("dw"):"0";
        String qyear = request.getParameter("qyear")!=null?request.getParameter("qyear"):year;

%>
<html>
<head>
<title>
individual
</title>
<style>
            .btn {
             BORDER-RIGHT: #7b9ebd 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7b9ebd 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#cecfde); BORDER-LEFT: #7b9ebd
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7b9ebd 1px solid
            }
            .btn1_mouseout {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#B3D997); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn1_mouseover {
             BORDER-RIGHT: #7EBF4F 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #7EBF4F 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#CAE4B6); BORDER-LEFT: #7EBF4F
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #7EBF4F 1px solid
            }
            .btn2 {padding: 2 4 0
            4;font-size:12px;height:23;background-color:#ece9d8;border-width:1;}
            .btn3_mouseout {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mouseover {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn3_mousedown
            {
             BORDER-RIGHT: #FFE400 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #FFE400 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #FFE400
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #FFE400 1px solid
            }
            .btn3_mouseup {
             BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#ffffff, EndColorStr=#C3DAF5); BORDER-LEFT: #2C59AA
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #2C59AA 1px solid
            }
            .btn_2k3 {
             BORDER-RIGHT: #002D96 1px solid; PADDING-RIGHT: 2px; BORDER-TOP:
            #002D96 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER:
            progid:DXImageTransform.Microsoft.Gradient(GradientType=0,
            StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); BORDER-LEFT: #002D96
            1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px;
            BORDER-BOTTOM: #002D96 1px solid
            }
            .style1 {
	color: #FF9900;
	font-weight: bold;
}
 .STYLE6 {color: #FFFFFF; }
 .memo {border: 1px #C8E1FB solid}
 .style7 {font-weight: bold}
 .memo { border: 1px #888888 solid}
 body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
#floater {
position: absolute;
left: 500;
top: 146;
width: 125;
visibility: visible;
z-index: 10;
}
 #id1 {
BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#D7E7FA); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
#plane1 {position:absolute; left:50; top:320; width:150; z-index:0}
#plane2 {position:absolute; left:350; top:320; width:150; z-index:1}
 </style>

</head>
<script language="javascript">
var path = '<%=path%>';
function chaxuns(){
            //var dwxz = document.form1.dwxz.value;
            var qijian = document.form1.qijian.value;
            
            var qyear= document.form1.qyear.value;
            if(qyear=="0"){
            		alert("请选择年");
            		return;
            }
 
    document.form1.action=path+"/web/analysis/huanbiAnalysis.jsp";
      document.form1.submit()
 
	}

        function hbap(){
        	var qijian = document.form1.qijian.value;
        	var qyear= document.form1.qyear.value;
            if(qyear=="0"){
            		alert("请选择年");
            		return;
            }
        	document.form1.action=path+"/web/analysis/huanbi_xml.jsp?qyear="+qyear;
                document.form1.submit();
        }

</script>
<jsp:useBean id="bean" scope="page" class="com.noki.mobi.analysis.javabean.HB">
</jsp:useBean>

<body  class="body" style="overflow-x:yes;">
	<LINK href="../../images/css.css" type="text/css" rel=stylesheet>
<form action="" method="POST" name="form1">

          <table  width="98%"  border="0" cellspacing="0" cellpadding="0">


	<tr>
    <td width="10" height="500" background="../../images/img_10.gif">&nbsp;</td>
    <td valign="top">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                          <td colspan="3">
                                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                                          <tr>
                                                  <td colspan="4">&nbsp;</td>
                                          </tr>
                                          <tr>
                                                  <td width="6" background="../../images/a_22.gif"><img src="../../images/a_21.gif" width="44" height="32" /></td>
                                                  <td width="169" background="../../images/a_22.gif"><span class="style1"> 收支增减变动情况表</span></td>
                                                  <td width="185"><img src="../../images/a_23.gif" width="185" height="32" /></td>
                                                  <td width="380">&nbsp;</td>
                                          </tr>
                                  </table>
                          </td>
                  </tr>
                  <tr>
                          <td colspan="3">
                            <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="4"></td>
                                  <td>
                                    <table width="100%" border="0" align="right" cellpadding="1" cellspacing="1" bgcolor="7C92B7">
                                      <tr>
                                      <td height="49" bgcolor="#FFFFFF">
                                        <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                         <tr bgcolor="F9F9F9">
                      <td height="19" colspan="4">　</td>
                    </tr>
                    <tr>
                      <td colspan="4">
					   会计期间：
					   <select name="qyear" style="width:80" class="form">
					   		<option value="0">选择年</option>
					   		<option value="2008" <%if(qyear.equals("2008")){%> selected="selected" <%}%>>2008</option>
					   		<option value="2009" <%if(qyear.equals("2009")){%> selected="selected" <%}%>>2009</option>
					   		<option value="2010" <%if(qyear.equals("2010")){%> selected="selected" <%}%>>2010</option>
					   		<option value="2011" <%if(qyear.equals("2011")){%> selected="selected" <%}%>>2011</option>
					   		<option value="2012" <%if(qyear.equals("2012")){%> selected="selected" <%}%>>2012</option>
					   		<option value="2013" <%if(qyear.equals("2013")){%> selected="selected" <%}%>>2013</option>
					   		<option value="2014" <%if(qyear.equals("2014")){%> selected="selected" <%}%>>2014</option>
					  </select>
					   <select name="qijian" style="width:50" class="form">
                        	<option value="0">月份</option>
                                <option value="1" <%if(qijian.equals("1")){%> selected="selected" <%}%> >01</option>
                                <option value="2" <%if(qijian.equals("2")){%> selected="selected" <%}%> >02</option>
                                <option value="3" <%if(qijian.equals("3")){%> selected="selected" <%}%> >03</option>
                                <option value="4" <%if(qijian.equals("4")){%> selected="selected" <%}%> >04</option>
                                <option value="5" <%if(qijian.equals("5")){%> selected="selected" <%}%> >05</option>
                                <option value="6" <%if(qijian.equals("6")){%> selected="selected" <%}%> >06</option>
                                <option value="7" <%if(qijian.equals("7")){%> selected="selected" <%}%> >07</option>
                                <option value="8" <%if(qijian.equals("8")){%> selected="selected" <%}%> >08</option>
                                <option value="9" <%if(qijian.equals("9")){%> selected="selected" <%}%> >09</option>
                                <option value="10" <%if(qijian.equals("10")){%> selected="selected" <%}%> >10</option>
                                <option value="11" <%if(qijian.equals("11")){%> selected="selected" <%}%> >11</option>
                                <option value="12" <%if(qijian.equals("12")){%> selected="selected" <%}%> >12</option>
                        </select>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        
                       
                        <input type="button" name="chaxun" value="执行查询" id="id1" onclick="chaxuns()" />
                        &nbsp;
                        <input type="button" name="pr" value="导出" onclick="hbap()" id="id1"/>
                      </td>
                    </tr>
					</table>
					<div align="left">
					<table width="100%" border="0" cellspacing="1" cellpadding="1">


            <tr bgcolor="F9F9F9">
                      <td height="12" colspan="3"><div align="left">编制单位：全部</div></td>
					  <td height="12" colspan="2"><div align="right">单位：元</div>　</td>
                    </tr>
            <tr>
              <td width="20%" bgcolor="C8E1FB" height="25"><div align="center">项目</div>
              </td>
              <td width="20%" bgcolor="C8E1FB"><div align="center">本月</div>
              </td>
              <td width="20%" bgcolor="C8E1FB"><div align="center">上月</div>
              </td>
              <td width="20%" bgcolor="C8E1FB"><div align="center">增减额</div>
              </td>
              <td width="20%" bgcolor="C8E1FB"><div align="center">增减%</div>
              </td>
            </tr>
            <%
            	Vector v = bean.getHuanbi_all(dw,qijian,qyear);
            %>
           <tr bgcolor="FFFFFF" >
              <td bgcolor="C8E1FB" height="25">一、收入
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(0).toString()))%></div>
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(1).toString()))%></div>
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(2).toString()))%></div>
              </td>
              <td><div align="right"><%=v.get(3)%></div>
              </td>
            </tr>
            <tr bgcolor="#F2F9FF" >
              <td bgcolor="C8E1FB" height="25">&nbsp;&nbsp;&nbsp;&nbsp;行政
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(4).toString()))%></div>
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(5).toString()))%></div>
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(6).toString()))%></div>
              </td>
              <td><div align="right"><%=v.get(7)%></div>
              </td>
            </tr>
            <tr bgcolor="FFFFFF" >
              <td bgcolor="C8E1FB" height="25">&nbsp;&nbsp;&nbsp;&nbsp;事业
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(8).toString()))%></div>
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(9).toString()))%></div>
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(10).toString()))%></div>
              </td>
              <td><div align="right"><%=v.get(11)%></div>
              </td>
            </tr>
            <tr bgcolor="#F2F9FF" >
              <td bgcolor="C8E1FB" height="25">二、支出
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(12).toString()))%></div>
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(13).toString()))%></div>
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(14).toString()))%></div>
              </td>
              <td><div align="right"><%=v.get(15)%></div>
              </td>
            </tr>
           <tr bgcolor="FFFFFF" >
              <td bgcolor="C8E1FB" height="25">&nbsp;&nbsp;&nbsp;&nbsp;行政
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(16).toString()))%></div>
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(17).toString()))%></div>
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(18).toString()))%></div>
              </td>
              <td><div align="right"><%=v.get(19)%></div>
              </td>
            </tr>
            <tr bgcolor="#F2F9FF" >
              <td bgcolor="C8E1FB" height="25">&nbsp;&nbsp;&nbsp;&nbsp;事业
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(20).toString()))%></div>
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(21).toString()))%></div>
              </td>
              <td><div align="right"><%=new DecimalFormat("#,##0.00").format(Double.parseDouble(v.get(22).toString()))%></div>
              </td>
              <td><div align="right"><%=v.get(23)%></div>
              </td>
            </tr>

          </table>
		  </div>


                                              </table>
											</td>
										</tr>
									</table>
								</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
      <br />
      </td>

  </tr>

</table>


<jsp:include flush="true" page="huanbiA_g.jsp">
  <jsp:param name="sr1" value="<%=v.get(4).toString()%>"/>
  <jsp:param name="sr2" value="<%=v.get(8).toString()%>"/>
  <jsp:param name="zc1" value="<%=v.get(16).toString()%>"/>
  <jsp:param name="zc2" value="<%=v.get(20).toString()%>"/>
</jsp:include>
</form>

</body>
</html>
<script LANGUAGE="Javascript">
//Modified by the CoffeeCup HTML Editor++
//http://www.coffeecup.com
// Global variables for platform branching
var isNav, isIE
if (parseInt(navigator.appVersion) >= 4) {
    if (navigator.appName == "Netscape") {
        isNav = true
    } else {
        isIE = true
    }
}

// ***Begin CSS custom API Functions***
// Set zIndex property
function setZIndex(obj, zOrder) {
    obj.zIndex = zOrder
}
// Position an object at a specific pixel coordinate
function shiftTo(obj, x, y) {
    if (isNav) {
        obj.moveTo(x,y)
    } else {
        obj.pixelLeft = x
        obj.pixelTop = y
    }
}
// ***End API Functions***

// Global holds reference to selected element
var selectedObj
// Globals hold location of click relative to element
var offsetX, offsetY

// Find out which element has been clicked on
function setSelectedElem(evt) {
    if (isNav) {
        // declare local var for use in upcoming loop
        var testObj
        // make copies of event coords for use in upcoming loop
        var clickX = evt.pageX
        var clickY = evt.pageY
        // loop through all layers (starting with frontmost layer)
        // to find if the event coordinates are in the layer
        for (var i = document.layers.length - 1; i >= 0; i--) {
            testObj = document.layers[i]
            if ((clickX > testObj.left) &&
                (clickX < testObj.left + testObj.clip.width) &&
                (clickY > testObj.top) &&
                (clickY < testObj.top + testObj.clip.height)) {
                    // if so, then set the global to the layer, bring it
                    // forward, and get outa here
                    selectedObj = testObj
                    setZIndex(selectedObj, 100)
                    return
            }
        }
    } else {
        // use IE event model to get the targeted element
        var imgObj = window.event.srcElement
        // make sure it's one of our planes
        if (imgObj.parentElement.id.indexOf("plane") != -1) {
            // then set the global to the style property of the element,
            // bring it forward, and say adios
            selectedObj = imgObj.parentElement.style
            setZIndex(selectedObj,100)
            return
        }
    }
    // the user probably clicked on the background
    selectedObj = null
    return
}
// Drag an element
function dragIt(evt) {
    // operate only if a plane is selected
    if (selectedObj) {
        if (isNav) {
            shiftTo(selectedObj, (evt.pageX - offsetX), (evt.pageY - offsetY))
        } else {
            shiftTo(selectedObj, (window.event.clientX - offsetX), (window.event.clientY - offsetY))
            // prevent further system response to dragging in IE
            return false
        }
    }
}
// Set globals to connect with selected element
function engage(evt) {
    setSelectedElem(evt)
    if (selectedObj) {
        // set globals that remember where the click is in relation to the
        // top left corner of the element so we can keep the element-to-cursor
        // relationship constant throughout the drag
        if (isNav) {
            offsetX = evt.pageX - selectedObj.left
            offsetY = evt.pageY - selectedObj.top
        } else {
            offsetX = window.event.offsetX
            offsetY = window.event.offsetY
        }
    }
    // block mouseDown event from forcing Mac to display
    // contextual menu.
    return false
}
// Restore elements and globals to initial values
function release(evt) {
    if (selectedObj) {
        setZIndex(selectedObj, 0)
        selectedObj = null
    }
}
// Turn on event capture for Navigator
function setNavEventCapture() {
    if (isNav) {
        document.captureEvents(Event.MOUSEDOWN | Event.MOUSEMOVE | Event.MOUSEUP)
    }
}
// Assign event handlers used by both Navigator and IE (called by onLoad)
function init() {
    if (isNav) {
        setNavEventCapture()
    }
    // assign functions to each of the events (works for both Navigator and IE)
    document.onmousedown = engage
    document.onmousemove = dragIt
    document.onmouseup = release
}
</script>
