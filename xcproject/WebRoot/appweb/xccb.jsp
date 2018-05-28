<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.noki.biaogan.model.CbUserBean"%>
<%@ page import="com.noki.zwhd.model.ZhandianBean"%>
<%@ page import="com.noki.biaogan.CBUserManage"%>
<%
	//String path = request.getContextPath();
//接受全局变量的地方，进行接收登陆名字
String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/dianxin";
String value = (String)session.getAttribute("value");
if(value == null)value = "";
CbUserBean sessionUser = (CbUserBean)session.getAttribute("user");
String userid = sessionUser.getID();
%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta name="viewport"
	content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<meta charset="utf-8" />

<meta name="viewport"
	content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<title>中国电信山东分公司能耗管理系统</title>

<!-- basic styles -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
<link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.custom.min.css" />
<link rel="stylesheet" href="assets/css/chosen.css" />
<link rel="stylesheet" href="assets/css/datepicker.css" />
<link rel="stylesheet" href="assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="assets/css/daterangepicker.css" />
<link rel="stylesheet" href="assets/css/colorpicker.css" />
<!-- page specific plugin styles -->

<!-- fonts -->

<!-- ace styles -->

<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />

<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
<style type="text/css">
.file {
	top: 10px;
	position: relative;
	display: inline-block;
	background: #D0EEFF;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	color: #1E88C7;
	text-decoration: none;
	text-indent: 0;
	line-height: 20px;
}

.file input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
}

.file:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}
</style>
<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
</head>

<body>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed');
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small>
						中国电信山东分公司能耗管理系统 </small> </a>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header -->
		</div>
		<!-- /.container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed');
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span> </a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed');
					} catch (e) {
					}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="icon-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="icon-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="icon-group"></i>
						</button>

						<button class="btn btn-danger">
							<i class="icon-cogs"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span>

						<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
					</div>
				</div>
				<!-- #sidebar-shortcuts -->

				<ul class="nav nav-list">
					<li class="active"><a href="index.html"> <i
							class="icon-dashboard"></i> <span class="menu-text"> 控制台 </span>
					</a></li>

					<li><a href="xccb.jsp"> <i class="icon-text-width"></i> <span
							class="menu-text"> 现场抄表 </span> </a></li>

					<li><a href="cbyj.jsp"> <i class="icon-desktop"></i> <span
							class="menu-text"> 抄表预警 </span> </a></li>

					<li><a href="dzjc.html"> <i class="icon-list"></i> <span
							class="menu-text"> 电子稽核 </span> </a></li>

					<li><a href="jfyj.html"> <i class="icon-tag"></i> <span
							class="menu-text"> 缴费预警 </span> </a></li>

					<li><a href="jzxz.html"> <i class="icon-file-alt"></i> <span
							class="menu-text"> 局站修正 </span> </a></li>

					<li><a href="gdpf.html"> <i class="icon-list-alt"></i> <span
							class="menu-text"> 工单派发 </span> </a></li>
				</ul>
				<!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed');
					} catch (e) {
					}
				</script>
			</div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed');
						} catch (e) {
						}
					</script>

					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
						<li class="active">现场抄表</li>
					</ul>
					<!-- .breadcrumb -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>现场抄表</h1>
					</div>
					<!-- /.page-header -->
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<form class="form-horizontal" role="form"
								enctype="multipart/form-data" method="post" id="form"
								action="../CBUserSendMessageServlet">
								<div class="form-group">

									<label class="width-10" for="form-field-1">&nbsp;&nbsp;&nbsp;抄表基站
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									<!--<input list="cars"  id="form-field-select-3" onchange="doSearchZhandian()"/>-->
									<input list="cars" id="form-field-select-3" placeholder="请选择基站"
										onchange="doSearchZhandian()" class="width-40" />
									<datalist id="cars">
										<%
												//页面开始时进行通过userid查找站点，会首先进行执行进行显示
																					CBUserManage userManage = new CBUserManage();
																					List<ZhandianBean> zdList = userManage.searchZdByUserid(userid);	
																					for(int i = 0;i<zdList.size();i++){
																					if(zdList.get(i).getJZNAME()!=null||zdList.get(i).getJZNAME()!=""){
											%>
										<option value="<%=zdList.get(i).getJZNAME()%>" />
										<%
												}}
											%>
									</datalist>


								</div>
								<div class="form-group">
									<div class="col-sm-9">
										<label class="width-10" for="form-field-1">
											抄表电表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <select
											id="form-field-select-1" name="dbid" class="width-40"
											onchange="doSearchDb();">
											<option value="">请选择</option>
										</select><span class="help-button" data-rel="popover"
											data-trigger="hover" data-placement="left" data-content="必填项"
											title="必填项">*</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9">
										<label class="width-10" for="form-field-1">上次抄表时间&nbsp;&nbsp;&nbsp; </label><input
											name="lastCBTime" id="form-field-1" type="text"
											placeholder="上次抄表时间"  class="width-40"
											style="box-sizing:border-box;width:130px" name="HIRE_TIME"
											value="" /><span class="help-button" data-rel="popover"
											data-trigger="hover" data-placement="left" data-content="必填项"
											title="必填项">*</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9">
										<label class="width-10" for="form-field-1">上次抄表读数 &nbsp;</label> <input
											readonly="true" type="text" id="form-field-2"
											placeholder="上次抄表读数" name="lastDlValue" class="width-40" /><span class="help-button" data-rel="popover"
											data-trigger="hover" data-placement="left" data-content="必填项"
											title="必填项">*</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9">
										<label class="width-10" for="form-field-gs">抄表读数(估算)</label> <input
											 type="text" id="form-field-gs"
											placeholder="抄表读数(估算)" name="lastDlgs" class="width-40" /><span class="help-button" data-rel="popover"
											data-trigger="hover" data-placement="left" data-content="必填项"
											title="必填项">*</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9">
										<label class="width-10" for="form-field-pl">偏离值&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input
											readonly="true" type="text" id="form-field-pl"
											placeholder="偏离值" name="lastDlplz" class="width-40" /><span
											class="help-button" data-rel="popover" data-trigger="hover"
											data-placement="left" data-content="必填项" title="必填项">*</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9">
										<label class="width-10" for="form-field-1"> 本次抄表读数 &nbsp;</label> <input
											type="text" id="form-field-3" placeholder="本次抄表读数"
											name="dlValue" class="width-30" /> <a href="javascript:;"
											class="file">选择文件 <input type="file" accept="image/*"
											id="capture1" capture="camera" name="path1" /> </a> <span
											class="help-button" data-rel="popover" data-trigger="hover"
											data-placement="left" data-content="必填项" title="必填项">*</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9">
										<label class="width-10" for="form-field-1">机房信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</label> <input type="text" id="form-field-4" placeholder="机房信息"
											name="jfxx" class="width-30" /> <a href="javascript:;"
											class="file">选择文件 <input type="file" accept="image/*"
											id="capture2" capture="camera" name="path2" /> </a>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9">
										<label class="width-10" for="form-field-1">电表串号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</label> <input type="text" id="form-field-5" placeholder="电表串号"
											name="dbch" class="width-30" /> <a href="javascript:;"
											class="file">选择文件 <input type="file" accept="image/*"
											id="capture3" capture="camera" name="path3" /> </a> <span
											class="help-button" data-rel="popover" data-trigger="hover"
											data-placement="left" data-content="必填项" title="必填项">*</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9">
										<img id="imgPath1" src="" style="width: 100px;height: 80px;">
										<img id="imgPath2" src="" style="width: 100px;height: 80px;">
										<img id="imgPath3" src="" style="width: 100px;height: 80px;">
									</div>
								</div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<!-- 提交时的隐藏 -->

										<input id="createdate" name="createdate" type="hidden"
											value="" /><input id="gsfieldgs" name="gsfieldgs" type="hidden"
											value="" /> <input id="userid" name="userid" type="hidden"
											value="<%=sessionUser.getID()%>" /> <input id="zduserid"
											name="zduserid" type="hidden" value=""> <input
											id="zdjing" name="zdjing" type="hidden" value=""> <input
											id="zdwei" name="zdwei" type="hidden" value=""> <input
											id="userdbid" name="userdbid" type="hidden" value="">
										<input id="longitude" name="longitude" type="hidden" value="" />
										<input id="latitude" name="latitude" type="hidden" value="" />
										<!-- 提交时隐藏的结束 -->
										<button class="btn btn-info" type="button" id="sendForm">
											<i class="icon-ok bigger-110"></i> 提交
										</button>

										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="reset" id="resetForm">
											<i class="icon-undo bigger-110"></i> 重置
										</button>
									</div>
								</div>
							</form>
							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
		</div>
		<!-- /.main-container-inner -->
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<!--[if !IE]> -->


	<!-- <![endif]-->

	<!--[if IE]>
<![endif]-->

	<!--[if !IE]> -->

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>
<![endif]-->

	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/typeahead-bs2.min.js"></script>

	<!-- page specific plugin scripts -->

	<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
	<script src="assets/js/chosen.jquery.min.js"></script>
	<script src="assets/js/fuelux/fuelux.spinner.min.js"></script>
	<script src="assets/js/date-time/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/date-time/bootstrap-timepicker.min.js"></script>
	<script src="assets/js/date-time/daterangepicker.min.js"></script>
	<script src="assets/js/bootstrap-colorpicker.min.js"></script>
	<script src="assets/js/jquery.knob.min.js"></script>
	<script src="assets/js/jquery.autosize.min.js"></script>
	<script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
	<script src="assets/js/jquery.maskedinput.min.js"></script>
	<script src="assets/js/bootstrap-tag.min.js"></script>
	<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
	<script src="assets/js/jquery.slimscroll.min.js"></script>
	<script src="assets/js/jquery.easy-pie-chart.min.js"></script>
	<script src="assets/js/jquery.sparkline.min.js"></script>
	<script src="assets/js/flot/jquery.flot.min.js"></script>
	<script src="assets/js/flot/jquery.flot.pie.min.js"></script>
	<script src="assets/js/flot/jquery.flot.resize.min.js"></script>

	<!-- ace scripts -->

	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>

	<%if(value!=""){%>
	<script type="text/javascript">
alert("<%=value%>");
</script>
	<%}%>
	<!--<script src="assets/js/jquery-1.4.2.js"></script> -->
	<script type="text/javascript" src="assets/appjs/xccb.js"></script>
	<script type="text/javascript">
		jQuery(function($) {
			$('.easy-pie-chart.percentage')
					.each(
							function() {
								var $box = $(this).closest('.infobox');
								var barColor = $(this).data('color')
										|| (!$box.hasClass('infobox-dark') ? $box
												.css('color')
												: 'rgba(255,255,255,0.95)');
								var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)'
										: '#E2E2E2';
								var size = parseInt($(this).data('size')) || 50;
								$(this)
										.easyPieChart(
												{
													barColor : barColor,
													trackColor : trackColor,
													scaleColor : false,
													lineCap : 'butt',
													lineWidth : parseInt(size / 10),
													animate : /msie\s*(8|7|6)/
															.test(navigator.userAgent
																	.toLowerCase()) ? false
															: 1000,
													size : size
												});
							});

			$('.sparkline').each(
					function() {
						var $box = $(this).closest('.infobox');
						var barColor = !$box.hasClass('infobox-dark') ? $box
								.css('color') : '#FFF';
						$(this).sparkline('html', {
							tagValuesAttribute : 'data-values',
							type : 'bar',
							barColor : barColor,
							chartRangeMin : $(this).data('min') || 0
						});
					});

			var placeholder = $('#piechart-placeholder').css({
				'width' : '90%',
				'min-height' : '150px'
			});
			var data = [ {
				label : "social networks",
				data : 38.7,
				color : "#68BC31"
			}, {
				label : "search engines",
				data : 24.5,
				color : "#2091CF"
			}, {
				label : "ad campaigns",
				data : 8.2,
				color : "#AF4E96"
			}, {
				label : "direct traffic",
				data : 18.6,
				color : "#DA5430"
			}, {
				label : "other",
				data : 10,
				color : "#FEE074"
			} ];
			function drawPieChart(placeholder, data, position) {
				$.plot(placeholder, data, {
					series : {
						pie : {
							show : true,
							tilt : 0.8,
							highlight : {
								opacity : 0.25
							},
							stroke : {
								color : '#fff',
								width : 2
							},
							startAngle : 2
						}
					},
					legend : {
						show : true,
						position : position || "ne",
						labelBoxBorderColor : null,
						margin : [ -30, 15 ]
					},
					grid : {
						hoverable : true,
						clickable : true
					}
				});
			}
			drawPieChart(placeholder, data);

			/**
			we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
			so that's not needed actually.
			 */
			placeholder.data('chart', data);
			placeholder.data('draw', drawPieChart);

			var $tooltip = $(
					"<div class='tooltip top in'><div class='tooltip-inner'></div></div>")
					.hide().appendTo('body');
			var previousPoint = null;

			placeholder.on('plothover', function(event, pos, item) {
				if (item) {
					if (previousPoint != item.seriesIndex) {
						previousPoint = item.seriesIndex;
						var tip = item.series['label'] + " : "
								+ item.series['percent'] + '%';
						$tooltip.show().children(0).text(tip);
					}
					$tooltip.css({
						top : pos.pageY + 10,
						left : pos.pageX + 10
					});
				} else {
					$tooltip.hide();
					previousPoint = null;
				}

			});

			var d1 = [];
			for ( var i = 0; i < Math.PI * 2; i += 0.5) {
				d1.push([ i, Math.sin(i) ]);
			}

			var d2 = [];
			for ( var i = 0; i < Math.PI * 2; i += 0.5) {
				d2.push([ i, Math.cos(i) ]);
			}

			var d3 = [];
			for ( var i = 0; i < Math.PI * 2; i += 0.2) {
				d3.push([ i, Math.tan(i) ]);
			}

			var sales_charts = $('#sales-charts').css({
				'width' : '100%',
				'height' : '220px'
			});
			$.plot("#sales-charts", [ {
				label : "Domains",
				data : d1
			}, {
				label : "Hosting",
				data : d2
			}, {
				label : "Services",
				data : d3
			} ], {
				hoverable : true,
				shadowSize : 0,
				series : {
					lines : {
						show : true
					},
					points : {
						show : true
					}
				},
				xaxis : {
					tickLength : 0
				},
				yaxis : {
					ticks : 10,
					min : -2,
					max : 2,
					tickDecimals : 3
				},
				grid : {
					backgroundColor : {
						colors : [ "#fff", "#fff" ]
					},
					borderWidth : 1,
					borderColor : '#555'
				}
			});

			$('#recent-box [data-rel="tooltip"]').tooltip({
				placement : tooltip_placement
			});
			function tooltip_placement(context, source) {
				var $source = $(source);
				var $parent = $source.closest('.tab-content');
				var off1 = $parent.offset();
				var w1 = $parent.width();

				var off2 = $source.offset();
				var w2 = $source.width();

				if (parseInt(off2.left) < parseInt(off1.left)
						+ parseInt(w1 / 2))
					return 'right';
				return 'left';
			}

			$('.dialogs,.comments').slimScroll({
				height : '300px'
			});

			//Android's default browser somehow is confused when tapping on label which will lead to dragging the task
			//so disable dragging when clicking on label
			var agent = navigator.userAgent.toLowerCase();
			if ("ontouchstart" in document && /applewebkit/.test(agent)
					&& /android/.test(agent))
				$('#tasks').on('touchstart', function(e) {
					var li = $(e.target).closest('#tasks li');
					if (li.length == 0)
						return;
					var label = li.find('label.inline').get(0);
					if (label == e.target || $.contains(label, e.target))
						e.stopImmediatePropagation();
				});

			$('#tasks').sortable({
				opacity : 0.8,
				revert : true,
				forceHelperSize : true,
				placeholder : 'draggable-placeholder',
				forcePlaceholderSize : true,
				tolerance : 'pointer',
				stop : function(event, ui) {//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
					$(ui.item).css('z-index', 'auto');
				}
			});
			$('#tasks').disableSelection();
			$('#tasks input:checkbox').removeAttr('checked').on('click',
					function() {
						if (this.checked)
							$(this).closest('li').addClass('selected');
						else
							$(this).closest('li').removeClass('selected');
					});

		});
	</script>

</body>
</html>

