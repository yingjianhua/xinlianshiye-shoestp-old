Ext.define('mvc.model.usr.UsrConsult',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrConsult_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.image',mapping : 'image',type : 'string'}
	,{name : 'bean.country',mapping : 'country',type : 'string',outkey : true}
	,{name : 'bean.product',mapping : 'product',type : 'string',outkey : true}
	,{name : 'bean.haveNewMsg',mapping : 'haveNewMsg',type : 'int',useNull : true}
	,{name : 'bean.content',mapping : 'content',type : 'string'}
	,{name : 'bean.count',mapping : 'count',type : 'int',useNull : true}
	,{name : 'bean.quantity',mapping : 'quantity',type : 'int',useNull : true}
	,{name : 'bean.purchase',mapping : 'purchase',type : 'string',outkey : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.email',mapping : 'email',type : 'string'}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});