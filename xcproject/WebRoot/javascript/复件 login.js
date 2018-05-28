﻿

var login = function() {
	Ext.QuickTips.init();
	Ext.lib.Ajax.defaultPostHeader += ";charset=utf-8";
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

	// 实现具体的功能

	var form = new Ext.FormPanel( {
		defaultType : 'textfield',
		labelWidth : 60,
		style : 'background:#ffffff;padding:5px 10px;',
		region : "center",
		defaults : {
			border : false,
			allowBlank : false,
			msgTarget : 'side',
			blankText : '该字段不允许为空'
		},
		waitMsgTarget : true,
		items : [
				{
					fieldLabel : '登录帐号',
					name : 'user',
					value:'',
					regex:/^[0-9a-zA-Z]{2,18}$/,
					allowBlank:false,
					regexText:'只能为两到十八位位的大小写字母。'
				},
				{
					fieldLabel : '登录密码',
					name : 'pass',
					value:'',
					inputType : 'password',
					regex:/^.{4,}$/,
					allowBlank:false,
					regexText:'长度不能少于4位'
				}],
		buttons : [ {
			text : '登陆',
			minWidth:60,
			handler : function() {
				form.getForm().submit( {
					
					
					success : function(f, a) {
						//alert("success//"+a.result.url);
						//OpenFullWin(a.result.url);
						 window.location.href = a.result.url;
						 //window.location.href = "http://127.0.0.1:8080/web/main.html";
						 //window.open('main.jsp','','fullscreen=1');
					},
					//url : 'login!loginOn.action',
					url:'servlet/login?action=loginOn',
					//url:'login.jsp',
					waitMsg : '正在登录，请稍等...'
				});
			}
		}, {
			text : '取消',
			minWidth:60,
			handler : function() {
				form.getForm().reset();
			}
		}]

	});
	var form2 = new Ext.FormPanel( {
		defaultType : 'textfield',
		labelWidth : 60,
		style : 'background:#ffffff;padding:5px 10px;',
		region : "left",
		waitMsgTarget : true,
		items : [
				{
					xtype:'textarea'
					
				}]	

	});
	
	var dengluInfo = new Ext.form.TextArea({
				fieldLabel:'',
				id:'userName',
				allowBlank:true,
				height:300,
				width:350,
				growMax:20,
				wordWrap:true,
				style:'border:0;',
				value:"\r\n系统名称   能耗系统信息管理系统\r\n版本信息   V1.0标准版\r\n山东中邮通信科技有限公司（C）版权所有\r\nCopyRight 2006-2011 All Rights\r\nReserved"
			});

	var panel = new Ext.Panel( {
		renderTo : 'loginpanel',
		layout : "border",
		border:false,
		width : 505,
		height : 110,
		defaults : {
			border : false
		},
		items : [ {
			region : "west",
			width : 290,
			html : '<img src="pic/blank.gif"/>'
		}, dengluInfo,form]
	});

	Ext.get('loginpanel').setStyle('position', 'absolute')
			.center(Ext.getBody());

};

Ext.onReady(login);
 