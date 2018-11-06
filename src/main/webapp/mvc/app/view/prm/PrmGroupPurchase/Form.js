Ext.define('mvc.view.prm.PrmGroupPurchase.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/prm_PrmGroupPurchase_',
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
({xtype : 'textfield',name : 'bean.title',fieldLabel : '活动标题'}
	,{xtype : 'datefield',name : 'bean.startTime',value : 'Env.getTranBeginTime()',fieldLabel : '开始时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'datefield',name : 'bean.endTime',value : 'Env.getTranBeginTime()',fieldLabel : '结束时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.preTime',
					fieldLabel : '提前预告',
					store : Ext.create('mvc.combo.prm.PrmOPreTime'),
					value : 30
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