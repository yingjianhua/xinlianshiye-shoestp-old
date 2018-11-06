Ext.define('mvc.model.cnt.CntAdLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntAdLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.main',mapping : 'main',type : 'string',outkey : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.brief',mapping : 'brief',type : 'string'}
	,{name : 'bean.image',mapping : 'image',type : 'string'}
	,{name : 'bean.url',mapping : 'url',type : 'string'}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'bean.mainImg',mapping : 'mainImg',type : 'int',useNull : true}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});