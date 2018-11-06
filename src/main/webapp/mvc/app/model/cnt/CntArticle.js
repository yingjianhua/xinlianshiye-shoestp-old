Ext.define('mvc.model.cnt.CntArticle',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntArticle_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.articleCategory',mapping : 'articleCategory',type : 'string',outkey : true}
	,{name : 'bean.url',mapping : 'url',type : 'string'}
	,{name : 'bean.rewriteUrl',mapping : 'rewriteUrl',type : 'string'}
	,{name : 'bean.images',mapping : 'images',type : 'string'}
	,{name : 'bean.date',mapping : 'date',type : 'date'}
	,{name : 'bean.tag',mapping : 'tag',type : 'string'}
	,{name : 'bean.keyword',mapping : 'keyword',type : 'string'}
	,{name : 'bean.intro',mapping : 'intro',type : 'string'}
	,{name : 'bean.detail',mapping : 'detail',type : 'string'}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.createBy',mapping : 'createBy',type : 'string',outkey : true}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});