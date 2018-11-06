Ext.define('mvc.view.cnt.CntMagazine.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/cnt_CntMagazine_',
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
	,{xtype : 'imagefield',name : 'bean.specialPictures',fieldLabel : '图片',labelWidth : 100,url:base_path+'/cnt_CntMagazine_upload'}
	,{xtype : 'datefield',name : 'bean.time',fieldLabel : '发布时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'numberfield',name : 'bean.cycle',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '周期',allowDecimals : false}
	,{xtype : 'textfield',name : 'bean.content',fieldLabel : '内容'}
	,{xtype : 'textfield',name : 'bean.contenturl',fieldLabel : 'URL'}
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