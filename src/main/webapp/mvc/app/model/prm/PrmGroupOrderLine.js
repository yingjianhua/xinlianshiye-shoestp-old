Ext.define('mvc.model.prm.PrmGroupOrderLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/prm_PrmGroupOrderLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.orderid',mapping : 'orderid',type : 'string',outkey : true}
	,{name : 'bean.product',mapping : 'product',type : 'string',outkey : true}
	,{name : 'bean.productnum',mapping : 'productnum',type : 'int',useNull : true}
	,{name : 'bean.productprice',mapping : 'productprice',type : 'float',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});