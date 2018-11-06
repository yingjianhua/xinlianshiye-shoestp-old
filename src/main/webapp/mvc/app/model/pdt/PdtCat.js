Ext.define('mvc.model.pdt.PdtCat',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtCat_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.categoryUp',mapping : 'categoryUp',type : 'string',outkey : true}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.seoTitleEn',mapping : 'seoTitleEn',type : 'string'}
	,{name : 'bean.seoKeywordEn',mapping : 'seoKeywordEn',type : 'string'}
	,{name : 'bean.seoDescriptionEn',mapping : 'seoDescriptionEn',type : 'string'}
	,{name : 'bean.createBy',mapping : 'createBy',type : 'string',outkey : true}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});