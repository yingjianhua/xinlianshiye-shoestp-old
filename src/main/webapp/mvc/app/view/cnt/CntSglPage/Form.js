Ext.define('mvc.view.cnt.CntSglPage.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/cnt_CntSglPage_',
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
({xtype : 'textfield',name : 'bean.title',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '标题'}
	,
		mvc.Tools.crtComboTrigger(false,'cnt_CntSglPageCategory','',{
					name : 'bean.pageType',
					fieldLabel : '单页类型'
				})
	,{xtype : 'textfield',name : 'bean.brief',fieldLabel : '简述'}
	,{xtype : 'umeditorfield',name : 'bean.descrip',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '详细描述', imageUrl:"cnt_CntSglPage_upload"}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.enabled',
					fieldLabel : '启用标志',
					store : Ext.create('mvc.combo.sys.SysOEnabled'),
					value : 1
				})
	,{xtype : 'numberfield',name : 'bean.sort',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '排序号',allowDecimals : false}
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