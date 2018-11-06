Ext.define('mvc.view.pdt.PdtConsult.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/pdt_PdtConsult_',
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
	,{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名字'}
	,{xtype : 'textfield',name : 'bean.email',fieldLabel : '邮箱'}
	,{xtype : 'imagefield',name : 'bean.image',fieldLabel : '图片',labelWidth : 100}
	,
		mvc.Tools.crtComboTrigger(false,'plt_PltCountry','',{
					name : 'bean.county',
					fieldLabel : '国家管理'
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.product',
		fieldLabel : '产品',
		bean : 'PdtProduct',
		beanType : 'pdt',
		emptyText : form_empty_text
	},{xtype : 'datefield',name : 'bean.time',value : 'Env.getTranBeginTime()',fieldLabel : '询盘创建时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{
		xtype : 'beantrigger',
		name : 'bean.purchase',
		fieldLabel : '采购商',
		bean : 'UsrPurchase',
		beanType : 'usr',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{xtype : 'textfield',name : 'bean.content',fieldLabel : '内容'}
	,{xtype : 'numberfield',name : 'bean.count',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '已抢单次数',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.prodNum',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '商品数量',allowDecimals : false}
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