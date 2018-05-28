<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.noki.jizhan.DianBiaoBean"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>中国电信山东分公司能耗管理系统</title>

<!-- basic styles -->

<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

<!-- page specific plugin styles -->

<link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css" />
<link rel="stylesheet" href="assets/css/datepicker.css" />
<link rel="stylesheet" href="assets/css/ui.jqgrid.css" />

<!-- fonts -->

<!--  -->

<!-- ace styles -->

<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />

<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->

<script src="assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
</head>

<body onLoad="goPage(1,10);">
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
						<li class="active">抄表预警</li>
					</ul>
					<!-- .breadcrumb -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>
							抄表预警 <small> <i class="icon-double-angle-right"></i> 查看 </small>
						</h1>
					</div>
					<!-- /.page-header -->
					<div class="row">
						<div class="col-xs-12">
							<div class="table-responsive">
								<table id="sample-table-1"
									class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center"><label> <input
													type="checkbox" class="ace" /> <span class="lbl"></span> </label>
											</th>
											<th>序号<br></th>
											<th>站点名称<br></th>
											<th class="hidden-480">电表编码<br></th>

											<th>上次抄表时间&nbsp;</th>
										</tr>
									</thead>

									<tbody>
										<%  DianBiaoBean bean = new DianBiaoBean();
										int no=1;
											ArrayList list = new ArrayList();  
											list  = bean.getYJDataapp();
												for(int i=0;i<list.size();i+=3){
								String dbname = list.get(i).toString();
								if(dbname==""||dbname.equals("")){
									break;
								}
	        				String dbbm = list.get(i+1).toString();
		    				String	lasttime = list.get(i+2).toString();
		    					
								if(lasttime==""||lasttime.equals("")){
									break;
								}
								
							
											 %>
										<tr>
											<td class="center"><label> <input
													type="checkbox" class="ace" /> <span class="lbl"></span> </label>
											</td>

											<td><%=no%></td>
											<td><%=dbname%><br>
											</td>
											<td class="hidden-480"><%=dbbm%></td>
											<td><%=lasttime%></td>
										</tr>
										<%no++;}%>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
			<!-- /#ace-settings-container -->
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
								+ "<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/typeahead-bs2.min.js"></script>

	<!-- page specific plugin scripts -->

	<script src="assets/js/date-time/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/jqGrid/jquery.jqGrid.min.js"></script>
	<script src="assets/js/jqGrid/i18n/grid.locale-en.js"></script>

	<!-- ace scripts -->

	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->

	<script type="text/javascript">
		/**
		 *很任性的方法
		 *
		 */
		
		var grid_data = [ {
			xh : "1",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "2",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "3",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "4",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "5",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "6",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "7",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "8",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "9",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "10",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "11",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "12",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "13",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "14",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "15",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "16",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "17",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "18",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "19",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "20",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "21",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "22",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "23",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		}, {
			xh : "24",
			jzname : "测试基站1",
			dccbsj : "2007-11-03",
			sccbsj : "2007-12-03",
			dccbds : "100",
			sccbds : "50",
			bgz : "40"
		} ];

		jQuery(function($) {
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";

			jQuery(grid_selector).jqGrid(
					{
						//direction: "rtl",

						data : grid_data,
						datatype : "local",
						height : 250,
						colNames : [ '操作', '序号', '站点名称', '当次抄表时间', '上次抄表时间',
								'当次抄表电量', '上次抄表电量', '标杆值' ],
						colModel : [ {
							name : 'cz',
							index : '',
							width : 80,
							fixed : true,
							sortable : false,
							resize : false,
							formatter : 'actions',
							formatoptions : {
								keys : true,

								delOptions : {
									recreateForm : true,
									beforeShowForm : beforeDeleteCallback
								},
							//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
							}
						}, {
							name : 'xh',
							index : 'xh',
							width : 60,
							sorttype : "int",
							editable : true
						}, {
							name : 'jzname',
							index : 'jzname',
							width : 90,
							editable : true,
							editoptions : {
								size : "20",
								maxlength : "30"
							}
						}, {
							name : 'dccbsj',
							index : 'dccbsj',
							width : 150,
							editable : true,
							sorttype : "date",
							unformat : pickDate
						}, {
							name : 'sccbsj',
							index : 'sccbsj',
							width : 70,
							editable : true,
							sorttype : "date",
							unformat : pickDate
						}, {
							name : 'dccbds',
							index : 'dccbds',
							width : 90,
							editable : true,
							sorttype : "int",
						}, {
							name : 'sccbds',
							index : 'sccbds',
							width : 150,
							sortable : false,
							sorttype : "int",
						}, {
							name : 'bgz',
							index : 'bgz',
							width : 150,
							sortable : false,
							sorttype : "int",
						} ],

						viewrecords : true,
						rowNum : 10,
						rowList : [ 10, 20, 30 ],
						pager : pager_selector,
						altRows : true,
						//toppager: true,

						multiselect : true,
						//multikey: "ctrlKey",
						multiboxonly : true,

						loadComplete : function() {
							var table = this;
							setTimeout(function() {
								styleCheckbox(table);
								updateActionIcons(table);
								updatePagerIcons(table);
								enableTooltips(table);
							}, 0);
						},

						editurl : $path_base + "/dummy.html",//nothing is saved
						caption : "基站标杆预警",

						autowidth : true

					});

			//enable search/filter toolbar
			//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})

			//switch element when editing inline
			function aceSwitch(cellvalue, options, cell) {
				setTimeout(function() {
					$(cell).find('input[type=checkbox]').wrap(
							'<label class="inline" />').addClass(
							'ace ace-switch ace-switch-5').after(
							'<span class="lbl"></span>');
				}, 0);
			}
			//enable datepicker
			function pickDate(cellvalue, options, cell) {
				setTimeout(function() {
					$(cell).find('input[type=text]').datepicker({
						format : 'yyyy-mm-dd',
						autoclose : true
					});
				}, 0);
			}

			//navButtons
			jQuery(grid_selector).jqGrid(
					'navGrid',
					pager_selector,
					{ //navbar options
						edit : true,
						editicon : 'icon-pencil blue',
						add : true,
						addicon : 'icon-plus-sign purple',
						del : true,
						delicon : 'icon-trash red',
						search : true,
						searchicon : 'icon-search orange',
						refresh : true,
						refreshicon : 'icon-refresh green',
						view : true,
						viewicon : 'icon-zoom-in grey',
					},
					{
						//edit record form
						//closeAfterEdit: true,
						recreateForm : true,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find(
									'.ui-jqdialog-titlebar').wrapInner(
									'<div class="widget-header" />');
							style_edit_form(form);
						}
					},
					{
						//new record form
						closeAfterAdd : true,
						recreateForm : true,
						viewPagerButtons : false,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find(
									'.ui-jqdialog-titlebar').wrapInner(
									'<div class="widget-header" />');
							style_edit_form(form);
						}
					},
					{
						//delete record form
						recreateForm : true,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							if (form.data('styled'))
								return false;

							form.closest('.ui-jqdialog').find(
									'.ui-jqdialog-titlebar').wrapInner(
									'<div class="widget-header" />');
							style_delete_form(form);

							form.data('styled', true);
						},
						onClick : function(e) {
							alert(1);
						}
					},
					{
						//search form
						recreateForm : true,
						afterShowSearch : function(e) {
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find(
									'.ui-jqdialog-title').wrap(
									'<div class="widget-header" />');
							style_search_form(form);
						},
						afterRedraw : function() {
							style_search_filters($(this));
						},
						multipleSearch : true,
					/**
					multipleGroup:true,
					showQuery: true
					 */
					},
					{
						//view record form
						recreateForm : true,
						beforeShowForm : function(e) {
							var form = $(e[0]);
							form.closest('.ui-jqdialog').find(
									'.ui-jqdialog-title').wrap(
									'<div class="widget-header" />');
						}
					});

			function style_edit_form(form) {
				//enable datepicker on "sdate" field and switches for "stock" field
				form.find('input[name=sdate]').datepicker({
					format : 'yyyy-mm-dd',
					autoclose : true
				}).end().find('input[name=stock]').addClass(
						'ace ace-switch ace-switch-5').wrap(
						'<label class="inline" />').after(
						'<span class="lbl"></span>');

				//update buttons classes
				var buttons = form.next().find('.EditButton .fm-button');
				buttons.addClass('btn btn-sm').find('[class*="-icon"]')
						.remove();//ui-icon, s-icon
				buttons.eq(0).addClass('btn-primary').prepend(
						'<i class="icon-ok"></i>');
				buttons.eq(1).prepend('<i class="icon-remove"></i>');

				buttons = form.next().find('.navButton a');
				buttons.find('.ui-icon').remove();
				buttons.eq(0).append('<i class="icon-chevron-left"></i>');
				buttons.eq(1).append('<i class="icon-chevron-right"></i>');
			}

			function style_delete_form(form) {
				var buttons = form.next().find('.EditButton .fm-button');
				buttons.addClass('btn btn-sm').find('[class*="-icon"]')
						.remove();//ui-icon, s-icon
				buttons.eq(0).addClass('btn-danger').prepend(
						'<i class="icon-trash"></i>');
				buttons.eq(1).prepend('<i class="icon-remove"></i>');
			}

			function style_search_filters(form) {
				form.find('.delete-rule').val('X');
				form.find('.add-rule').addClass('btn btn-xs btn-primary');
				form.find('.add-group').addClass('btn btn-xs btn-success');
				form.find('.delete-group').addClass('btn btn-xs btn-danger');
			}
			function style_search_form(form) {
				var dialog = form.closest('.ui-jqdialog');
				var buttons = dialog.find('.EditTable');
				buttons.find('.EditButton a[id*="_reset"]').addClass(
						'btn btn-sm btn-info').find('.ui-icon').attr('class',
						'icon-retweet');
				buttons.find('.EditButton a[id*="_query"]').addClass(
						'btn btn-sm btn-inverse').find('.ui-icon').attr(
						'class', 'icon-comment-alt');
				buttons.find('.EditButton a[id*="_search"]').addClass(
						'btn btn-sm btn-purple').find('.ui-icon').attr('class',
						'icon-search');
			}

			function beforeDeleteCallback(e) {
				var form = $(e[0]);
				if (form.data('styled'))
					return false;

				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
						.wrapInner('<div class="widget-header" />');
				style_delete_form(form);

				form.data('styled', true);
			}

			function beforeEditCallback(e) {
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
						.wrapInner('<div class="widget-header" />');
				style_edit_form(form);
			}

			//it causes some flicker when reloading or navigating grid
			//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
			//or go back to default browser checkbox styles for the grid
			function styleCheckbox(table) {
				/**
					$(table).find('input:checkbox').addClass('ace')
					.wrap('<label />')
					.after('<span class="lbl align-top" />')
				
				
					$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
					.find('input.cbox[type=checkbox]').addClass('ace')
					.wrap('<label />').after('<span class="lbl align-top" />');
				 */
			}

			//unlike navButtons icons, action icons in rows seem to be hard-coded
			//you can change them like this in here if you want
			function updateActionIcons(table) {
				/**
				var replacement = 
				{
					'ui-icon-pencil' : 'icon-pencil blue',
					'ui-icon-trash' : 'icon-trash red',
					'ui-icon-disk' : 'icon-ok green',
					'ui-icon-cancel' : 'icon-remove red'
				};
				$(table).find('.ui-pg-div span.ui-icon').each(function(){
					var icon = $(this);
					var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
					if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
				})
				 */
			}

			//replace icons with FontAwesome icons like above
			function updatePagerIcons(table) {
				var replacement = {
					'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
					'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
					'ui-icon-seek-next' : 'icon-angle-right bigger-140',
					'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
				};
				$(
						'.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
						.each(
								function() {
									var icon = $(this);
									var $class = $.trim(icon.attr('class')
											.replace('ui-icon', ''));

									if ($class in replacement)
										icon.attr('class', 'ui-icon '
												+ replacement[$class]);
								});
			}

			function enableTooltips(table) {
				$('.navtable .ui-pg-button').tooltip({
					container : 'body'
				});
				$(table).find('.ui-pg-div').tooltip({
					container : 'body'
				});
			}

			//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

		});
	</script>
</body>
</html>