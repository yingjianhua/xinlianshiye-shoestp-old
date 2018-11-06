Ext.define('mvc.model.cnt.CntAd',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntAd_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.signage',mapping : 'signage',type : 'int',useNull : true}
	,{name : 'bean.adPosition',mapping : 'adPosition',type : 'int',useNull : true}
	,{name : 'bean.category',mapping : 'category',type : 'string',outkey : true,convert: mvc.Tools.i18nConvert(true)}
	,{name : 'bean.height',mapping : 'height',type : 'int',useNull : true}
	,{name : 'bean.width',mapping : 'width',type : 'int',useNull : true}
	,{name : 'bean.displayType',mapping : 'displayType',type : 'int',useNull : true}
	,{name : 'bean.lang',mapping : 'lang',type : 'string'}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.createdBy',mapping : 'createdBy',type : 'string',outkey : true}
	,{name : 'bean.createdTime',mapping : 'createdTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});