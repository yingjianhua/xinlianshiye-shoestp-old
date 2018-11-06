Ext.define('mvc.view.pdt.PdtAppraise.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/pdt_PdtAppraise_',
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
		name : 'bean.goodsPkey',
		fieldLabel : '产品',
		bean : 'PdtProduct',
		beanType : 'pdt',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},
		mvc.Tools.crtComboTrigger(false,'usr_UsrPurchase','',{
					name : 'bean.usersPkey',
					fieldLabel : '采购商'
				})
	,{xtype : 'textfield',name : 'bean.appraiseContent',fieldLabel : '评论内容'}
	,{xtype : 'imagefield',name : 'bean.image1',fieldLabel : '图片描述1',labelWidth : 100}
	,{xtype : 'imagefield',name : 'bean.image2',fieldLabel : '图片描述2',labelWidth : 100}
	,{xtype : 'imagefield',name : 'bean.image3',fieldLabel : '图片描述3',labelWidth : 100}
	,{xtype : 'imagefield',name : 'bean.image4',fieldLabel : '图片描述4',labelWidth : 100}
	,{xtype : 'imagefield',name : 'bean.image5',fieldLabel : '图片描述5',labelWidth : 100}
	,{xtype : 'imagefield',name : 'bean.image6',fieldLabel : '图片描述6',labelWidth : 100}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.goodsSatisfaction',
					fieldLabel : '产品满意度',
					store : Ext.create('mvc.combo.pdt.PdtSatisfaction'),
					value : 3
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.supplierSatisfaction',
					fieldLabel : '供应商满意度',
					store : Ext.create('mvc.combo.pdt.PdtSatisfaction'),
					value : 3
				})
	,{xtype : 'datefield',name : 'bean.appraiseTime',value : 'Env.getTranBeginTime()',fieldLabel : '评论时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
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