Ext.define('mvc.view.plt.PltPay.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/plt_PltPay_',
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
(
		mvc.Tools.crtComboForm(false,{
					name : 'bean.mode',
					fieldLabel : '支付设置',
					store : Ext.create('mvc.combo.plt.PltPayOPay_Mode'),
					value : 5
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.supplier',
		fieldLabel : '供应商',
		bean : 'UsrSupplier',
		beanType : 'usr',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{xtype : 'numberfield',name : 'bean.poundage',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '手续费',decimalPrecision : 4}
	,{xtype : 'numberfield',name : 'bean.extraCosts',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '附加费用',decimalPrecision : 4}
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