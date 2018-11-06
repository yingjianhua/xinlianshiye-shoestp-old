Ext.define('mvc.view.cnt.CntArticle.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/cnt_CntArticle_',
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
({xtype : 'multilanguagefield',name : 'bean.title',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '标题'}
	,
		mvc.Tools.crtComboTrigger(false,'cnt_CntArticleCategory','',{
					name : 'bean.articleCategory',
					fieldLabel : '分类'
				})
	,{xtype : 'textfield',name : 'bean.url',fieldLabel : 'URL'}
	,{xtype : 'textfield',name : 'bean.rewriteUrl',fieldLabel : '自定义URL地址'}
	,{xtype : 'imagefield',name : 'bean.images',fieldLabel : '图片',labelWidth : 100, url:base_path+'cnt_CntArticle_upload'}
	,{xtype : 'datefield',name : 'bean.date',fieldLabel : '日期',format : 'Y-m-d'}
	,{xtype : 'textfield',name : 'bean.tag',fieldLabel : 'TAG'}
	,{xtype : 'textfield',name : 'bean.keyword',fieldLabel : '关键词'}
	,{xtype : 'multilanguagefield',name : 'bean.intro',fieldLabel : '简述'}
	,{xtype : 'multilanguagefield',name : 'bean.detail',fieldLabel : '详细描述'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.enabled',
					fieldLabel : '显示',
					store : Ext.create('mvc.combo.sys.SysOEnabled'),
					value : 1
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