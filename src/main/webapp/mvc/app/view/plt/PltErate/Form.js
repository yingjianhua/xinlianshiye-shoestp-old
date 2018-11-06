Ext.define('mvc.view.plt.PltErate.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/plt_PltErate_',
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
({xtype : 'imagefield',name : 'bean.logo',fieldLabel : 'LOGO',labelWidth : 100, url:base_path+'plt_PltErate_upload'}
	,{xtype : 'textfield',name : 'bean.curName',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '货币简称'}
	,{xtype : 'textfield',name : 'bean.symbol',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '符号'}
	,{xtype : 'numberfield',name : 'bean.rate',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '美元汇率',decimalPrecision : 4}
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