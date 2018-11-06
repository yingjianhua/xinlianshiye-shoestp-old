Ext.define('mvc.view.usr.UsrSupplier.FormMarketing',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
bodyPadding : '5 5 5 5',
url : base_path+'/usr_UsrSupplier_updMarketing',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			var formFlds = [];
			formFlds.push
({xtype : 'textareafield',name : 'bean.traceCode',fieldLabel : '跟踪代码'}
	,{xtype : 'textfield',name : 'bean.webSizeTitle',fieldLabel : '自定义链接名称'}
	,{xtype : 'textfield',name : 'bean.webSite',fieldLabel : '网址'}
	,{xtype : 'textfield',name : 'bean.tongjiUrl',fieldLabel : '第三方统计地址'}
	,{xtype : 'textfield',name : 'bean.tongjiPwd',fieldLabel : '第三方统计密码'}
	,{xtype : 'datefield',name : 'bean.updateTime',value : 'Env.getTranBeginTime()',fieldLabel : '更新时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{
		xtype : 'hiddenfield',
		name : 'bean.pkey'
	});
	this.items = [{
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		border : false,
		items : formFlds
	}];
	this.callParent(arguments);
}
});