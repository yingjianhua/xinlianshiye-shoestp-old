Ext.define('mvc.view.plt.PltFreight.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/plt_PltFreight_',
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
({xtype : 'textfield',name : 'bean.company',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '快递公司'}
,{xtype : 'imagefield',name : 'bean.logo',fieldLabel : 'LOGO',labelWidth : 100, url:base_path+'/plt_PltFreight_upload'}
	,{xtype : 'textfield',name : 'bean.expressUrl',fieldLabel : '快递单号查询地址'}
	,{xtype : 'numberfield',name : 'bean.sort',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '排序',allowDecimals : false}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.useInterface',
					fieldLabel : '使用接口',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 0
				})
	,{xtype : 'numberfield',name : 'bean.weightMin',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '最小重量/体积/件数',allowDecimals : false}
	,{xtype : 'numberfield',name : 'bean.weightMax',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '最大重量/体积/件数',allowDecimals : false}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.type',
					fieldLabel : '重量计算方式选择',
					store : Ext.create('mvc.combo.plt.PltWeightType'),
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