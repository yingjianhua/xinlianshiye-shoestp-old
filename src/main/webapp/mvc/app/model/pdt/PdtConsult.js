Ext.define('mvc.model.pdt.PdtConsult',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtConsult_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.email',mapping : 'email',type : 'string'}
	,{name : 'bean.image',mapping : 'image',type : 'string'}
	,{name : 'bean.county',mapping : 'county',type : 'string',outkey : true}
	,{name : 'bean.product',mapping : 'product',type : 'string',outkey : true}
	,{name : 'bean.type',mapping : 'type',type : 'string'}
	,{name : 'bean.time',mapping : 'time',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.msg',mapping : 'msg',type : 'int',useNull : true}
	,{name : 'bean.purchase',mapping : 'purchase',type : 'string',outkey : true}
	,{name : 'bean.content',mapping : 'content',type : 'string'}
	,{name : 'bean.count',mapping : 'count',type : 'int',useNull : true}
	,{name : 'bean.prodNum',mapping : 'prodNum',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});