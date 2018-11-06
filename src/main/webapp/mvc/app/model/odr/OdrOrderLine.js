Ext.define('mvc.model.odr.OdrOrderLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/odr_OdrOrderLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.spec',mapping : 'spec',type : 'string',outkey : true, convert: mvc.Tools.i18nConvert(true)}
	,{name : 'bean.qty',mapping : 'qty',type : 'int',useNull : true}
	,{name : 'bean.subtotal',mapping : 'subtotal',type : 'float',useNull : true}
	,{name : 'bean.main',mapping : 'main',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});