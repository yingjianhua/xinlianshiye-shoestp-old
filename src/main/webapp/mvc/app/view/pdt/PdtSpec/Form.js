Ext.define('mvc.view.pdt.PdtSpec.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/pdt_PdtSpec_',
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
({xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
	,
		mvc.Tools.crtComboTrigger(false,'pdt_PdtProduct','',{
					name : 'bean.product',
					fieldLabel : '产品'
				})
	,
		mvc.Tools.crtComboTrigger(false,'pdt_PdtColor','',{
					name : 'bean.color',
					fieldLabel : '产品顏色'
				})
	,
		mvc.Tools.crtComboTrigger(false,'pdt_PdtSize','',{
					name : 'bean.size',
					fieldLabel : '产品尺寸'
				})
	,{xtype : 'textfield',name : 'bean.keyName',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '组合属性'}
	,{xtype : 'numberfield',name : 'bean.price',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : 'jiage',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.markup',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '加价',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.storeCount',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '库存数量',decimalPrecision : 4}
	,{xtype : 'numberfield',name : 'bean.weight',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '单价',decimalPrecision : 4}
	,{xtype : 'imagefield',name : 'bean.pics',fieldLabel : '产品颜色关联图片',labelWidth : 100}
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