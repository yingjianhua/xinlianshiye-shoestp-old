Ext.define('mvc.view.cnt.CntArticleCategory.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/cnt_CntArticleCategory_',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			if (this.insFlag)
				this.url = this.url + 'ins';
			else
				this.url = this.url + 'upd';
			var formFlds = [];
			formFlds.push
({xtype : 'multilanguagefield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
	,
		mvc.Tools.crtComboTrigger(true,'cnt_CntArticleCategory','',{
					name : 'bean.rootid',
					fieldLabel : '文章类型'
				})
	,{xtype : 'textfield',name : 'bean.lang',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '语言标识'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.enabled',
					fieldLabel : '启用标志',
					store : Ext.create('mvc.combo.sys.SysOEnabled'),
					value : 1
				})
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