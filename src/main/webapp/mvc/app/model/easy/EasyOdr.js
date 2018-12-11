Ext.define('mvc.model.easy.EasyOdr',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/easy_EasyOdr_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.purchase',mapping : 'purchase',type : 'string',outkey : true}
	,{name : 'bean.supplier',mapping : 'supplier',type : 'string',outkey : true}
	,{name : 'bean.orderNum',mapping : 'orderNum',type : 'string'}
	,{name : 'bean.time',mapping : 'time',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.phone',mapping : 'phone',type : 'string'}
	,{name : 'bean.address',mapping : 'address',type : 'string',useNull : true}
	,{name : 'bean.counypd',mapping : 'counypd',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});
