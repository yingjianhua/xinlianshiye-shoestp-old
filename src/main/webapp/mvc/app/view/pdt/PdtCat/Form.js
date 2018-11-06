Ext.define('mvc.view.pdt.PdtCat.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/pdt_PdtCat_',
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
({xtype : 'multilanguagefield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '标题'}
	,
		mvc.Tools.crtComboTrigger(true,'pdt_PdtCat','',{
					name : 'bean.categoryUp',
					fieldLabel : '隶属分类'
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.enabled',
					fieldLabel : '启用标志',
					store : Ext.create('mvc.combo.sys.SysOEnabled'),
					value : 1
				})
	,{xtype : 'textfield',name : 'bean.seoTitleEn',fieldLabel : '标题'}
	,{xtype : 'textfield',name : 'bean.seoKeywordEn',fieldLabel : '关键词'}
	,{xtype : 'textfield',name : 'bean.seoDescriptionEn',fieldLabel : '简述'}
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