Ext.define('mvc.model.cnt.CntArticleCategoryLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntArticleCategoryLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.main',mapping : 'main',type : 'string',outkey : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.createBy',mapping : 'createBy',type : 'string',outkey : true}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});