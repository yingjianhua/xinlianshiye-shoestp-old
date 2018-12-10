Ext.define('mvc.model.easy.EasyOdrline',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/easy_EasyOdrline_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.orderId',mapping : 'orderId',type : 'string',outkey : true}
	,{name : 'bean.spec',mapping : 'spec',type : 'string',outkey : true}
	,{name : 'bean.iamge',mapping : 'iamge',type : 'string'}
	,{name : 'bean.productname',mapping : 'productname',type : 'string'}
	,{name : 'bean.color',mapping : 'color',type : 'string'}
	,{name : 'bean.size',mapping : 'size',type : 'string'}
	,{name : 'bean.num',mapping : 'num',type : 'int',useNull : true}
	,{name : 'bean.remarks',mapping : 'remarks',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});