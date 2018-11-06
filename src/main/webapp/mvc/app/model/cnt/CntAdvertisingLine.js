Ext.define('mvc.model.cnt.CntAdvertisingLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntAdvertisingLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.main',mapping : 'main',type : 'string',outkey : true}
	,{name : 'bean.picpath',mapping : 'picpath',type : 'string'}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.brief',mapping : 'brief',type : 'string'}
	,{name : 'bean.url',mapping : 'url',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});