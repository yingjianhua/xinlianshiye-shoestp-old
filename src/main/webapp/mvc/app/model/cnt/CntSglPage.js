Ext.define('mvc.model.cnt.CntSglPage',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntSglPage_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.title',mapping : 'title',type : 'string', convert: mvc.Tools.i18nConvert()}
	,{name : 'bean.pageType',mapping : 'pageType',type : 'string',outkey : true, convert: mvc.Tools.i18nConvert(true)}
	,{name : 'bean.brief',mapping : 'brief',type : 'string'}
	,{name : 'bean.descrip',mapping : 'descrip',type : 'string', convert: mvc.Tools.i18nConvert()}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'bean.createBy',mapping : 'createBy',type : 'string',outkey : true}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});