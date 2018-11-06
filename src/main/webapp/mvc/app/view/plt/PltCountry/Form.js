Ext.define('mvc.view.plt.PltCountry.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/plt_PltCountry_',
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
({xtype : 'multilanguagefield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '国家'}
	,{xtype : 'textfield',name : 'bean.shortName',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '国家简称',listeners:{change:function() {console.log("shortName change")}}}
	,{xtype : 'textfield',name : 'bean.zone',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '区号'}
	,
		mvc.Tools.crtComboTrigger(true,'plt_PltErate','',{
					name : 'bean.currency',
					fieldLabel : '货币'
				})
	,{xtype : 'imagefield',name : 'bean.nationalFlag',fieldLabel : '国旗',labelWidth : 100,url:base_path+'plt_PltCountry_upload'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.isdefault',
					fieldLabel : '默认国家',
					store : Ext.create('mvc.combo.sys.SysOYn'),
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