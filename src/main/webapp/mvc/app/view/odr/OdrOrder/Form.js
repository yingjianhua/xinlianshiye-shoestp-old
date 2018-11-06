Ext.define('mvc.view.odr.OdrOrder.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/odr_OdrOrder_',
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
({
		xtype : 'beantrigger',
		name : 'bean.purchase',
		fieldLabel : '用户',
		bean : 'UsrPurchase',
		beanType : 'usr',
		emptyText : form_empty_text
	},{xtype : 'textfield',name : 'bean.expressNum',fieldLabel : '运单号'}
	,{xtype : 'textfield',name : 'bean.delivery',fieldLabel : '发货方式'}
	,{xtype : 'textfield',name : 'bean.pagRemarks',fieldLabel : '包裹备注'}
	,{xtype : 'textfield',name : 'bean.odrRemarks',fieldLabel : '订单备注'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.state',
					fieldLabel : '订单状态',
					store : Ext.create('mvc.combo.odr.OdrOdrState'),
					value : 0
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.type',
					fieldLabel : '订单类型',
					store : Ext.create('mvc.combo.odr.OdrOdrType'),
					value : 1
				})
	,{xtype : 'numberfield',name : 'bean.freightPrice',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '运费',decimalPrecision : 4}
	,{xtype : 'numberfield',name : 'bean.insurancePrice',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '保险费',decimalPrecision : 4}
	,
		mvc.Tools.crtComboTrigger(false,'plt_PltErate','',{
					name : 'bean.currency',
					fieldLabel : '货币'
				})
	,{xtype : 'textfield',name : 'bean.address',fieldLabel : '地址'}
	,{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名字'}
	,{xtype : 'textfield',name : 'bean.postalcode',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '邮政编码'}
	,{xtype : 'textfield',name : 'bean.phone',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '电话号码'}
	,{xtype : 'textfield',name : 'bean.paycontent',fieldLabel : '支付内容'}
	,
		mvc.Tools.crtComboForm(true,{
					name : 'bean.odrCancel',
					fieldLabel : '取消原因',
					store : Ext.create('mvc.combo.odr.OdrCancelType'),
					value : 0
				})
	,
		mvc.Tools.crtComboForm(true,{
					name : 'bean.payType',
					fieldLabel : '支付方式',
					store : Ext.create('mvc.combo.odr.OdrPayType'),
					value : 0
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