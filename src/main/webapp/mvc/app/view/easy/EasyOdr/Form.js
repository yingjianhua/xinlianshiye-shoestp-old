Ext.define('mvc.view.easy.EasyOdr.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/easy_EasyOdr_',
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
		fieldLabel : '采购商',
		bean : 'UsrPurchase',
		beanType : 'usr',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{
		xtype : 'beantrigger',
		name : 'bean.supplier',
		fieldLabel : '供应商',
		bean : 'UsrSupplier',
		beanType : 'usr',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{xtype : 'textfield',name : 'bean.orderNum',fieldLabel : '订单号'}
	,{xtype : 'datefield',name : 'bean.time',value : 'Env.getTranBeginTime()',fieldLabel : '下单时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名字'}
	,{xtype : 'textfield',name : 'bean.phone',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '电话号码'}
	,{xtype : 'textfield',name : 'bean.address',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '收货地址'}
	,{xtype : 'numberfield',name : 'bean.counypd',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '商品总数量',allowDecimals : false}
	// ,{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
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
