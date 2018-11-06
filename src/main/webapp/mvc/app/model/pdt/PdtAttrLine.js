Ext.define('mvc.model.pdt.PdtAttrLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtAttrLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string',convert: mvc.Tools.i18nConvert(false)}
	,{name : 'bean.main',mapping : 'main',type : 'string',outkey : true}
	,{name : 'bean.createBy',mapping : 'createBy',type : 'string',outkey : true}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});