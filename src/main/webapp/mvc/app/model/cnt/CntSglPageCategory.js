Ext.define('mvc.model.cnt.CntSglPageCategory',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntSglPageCategory_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.pageName',mapping : 'pageName',type : 'string', convert: mvc.Tools.i18nConvert()}
	,{name : 'bean.tag',mapping : 'tag',type : 'string'}
	,{name : 'bean.pageTypeText',mapping : 'pageTypeText',type : 'string'}
	,{name : 'bean.createBy',mapping : 'createBy',type : 'string',outkey : true}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});