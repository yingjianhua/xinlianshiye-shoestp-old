Ext.define('mvc.model.prm.PrmGroupPurchaseLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/prm_PrmGroupPurchaseLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.main',mapping : 'main',type : 'string',outkey : true}
	,{name : 'bean.product',mapping : 'product',type : 'string',outkey : true}
	,{name : 'bean.count',mapping : 'count',type : 'int',useNull : true}
	,{name : 'bean.boughtCount',mapping : 'boughtCount',type : 'int',useNull : true}
	,{name : 'bean.state',mapping : 'state',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});