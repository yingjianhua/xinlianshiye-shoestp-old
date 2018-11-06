Ext.define('mvc.model.usr.UsrProductCategory',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrProductCategory_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.categoryUp',mapping : 'categoryUp',type : 'string',outkey : true}
	,{name : 'bean.supplier',mapping : 'supplier',type : 'string',outkey : true}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.seoTitleEn',mapping : 'seoTitleEn',type : 'string'}
	,{name : 'bean.seoKeywordEn',mapping : 'seoKeywordEn',type : 'string'}
	,{name : 'bean.seoDescriptionEn',mapping : 'seoDescriptionEn',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});