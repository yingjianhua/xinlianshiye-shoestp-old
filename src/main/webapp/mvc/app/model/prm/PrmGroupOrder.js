Ext.define('mvc.model.prm.PrmGroupOrder',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/prm_PrmGroupOrder_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.orderid',mapping : 'orderid',type : 'string'}
	,{name : 'bean.purchalise',mapping : 'purchalise',type : 'string',outkey : true}
	,{name : 'bean.activity',mapping : 'activity',type : 'string',outkey : true}
	,{name : 'bean.productnum',mapping : 'productnum',type : 'int',useNull : true}
	,{name : 'bean.price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'bean.createdTime',mapping : 'createdTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});