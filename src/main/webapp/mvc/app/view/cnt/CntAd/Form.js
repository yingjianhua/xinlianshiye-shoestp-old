Ext.define('mvc.view.cnt.CntAd.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/cnt_CntAd_',
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
			formFlds.push(
		mvc.Tools.crtComboForm(false,{
					name : 'bean.signage',
					fieldLabel : '系统标识',
					store : Ext.create('mvc.combo.cnt.CntAdOSIGNAGE'),
					value : 0
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.adPosition',
					fieldLabel : '展示位置',
					store : Ext.create('mvc.combo.cnt.CntAdOAdLocation'),
					value : 0
				})
	,
		mvc.Tools.crtComboTrigger(true,'pdt_PdtCat','',{
					name : 'bean.category',
					fieldLabel : '产品类目'
				})
	,{xtype : 'numberfield',name : 'bean.height',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '高',readOnly : !this.insFlag,allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.width',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '宽',readOnly : !this.insFlag,allowDecimals : false}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.displayType',
					fieldLabel : '展示类型',
					store : Ext.create('mvc.combo.cnt.CntAdOAdDisplayType'),
					value : 0
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