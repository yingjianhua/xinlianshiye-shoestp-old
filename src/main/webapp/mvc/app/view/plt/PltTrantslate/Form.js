Ext.define('mvc.view.plt.PltTrantslate.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/plt_PltTrantslate_',
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
({xtype : 'textfield',name : 'bean.hashcode',fieldLabel : 'HashCode'}
	,{xtype : 'textareafield',name : 'bean.sourceText',fieldLabel : '翻译前内容'}
	,{xtype : 'textfield',name : 'bean.target',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '目标语言'}
	,{xtype : 'textareafield',name : 'bean.targetText',fieldLabel : '翻译后语言'}
	,{xtype : 'datefield',name : 'bean.createdTime',value : 'Env.getTranBeginTime()',fieldLabel : '创建时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
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