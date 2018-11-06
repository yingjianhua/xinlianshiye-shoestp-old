Ext.define('mvc.model.odr.OdrOrder',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/odr_OdrOrder_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.purchase',mapping : 'purchase',type : 'string',outkey : true}
	,{name : 'bean.orderNum',mapping : 'orderNum',type : 'string'}
	,{name : 'bean.expressNum',mapping : 'expressNum',type : 'string'}
	,{name : 'bean.delivery',mapping : 'delivery',type : 'string'}
	,{name : 'bean.pagRemarks',mapping : 'pagRemarks',type : 'string'}
	,{name : 'bean.odrRemarks',mapping : 'odrRemarks',type : 'string'}
	,{name : 'bean.time',mapping : 'time',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.state',mapping : 'state',type : 'int',useNull : true}
	,{name : 'bean.type',mapping : 'type',type : 'int',useNull : true}
	,{name : 'bean.freightPrice',mapping : 'freightPrice',type : 'float',useNull : true}
	,{name : 'bean.insurancePrice',mapping : 'insurancePrice',type : 'float',useNull : true}
	,{name : 'bean.prodPrice',mapping : 'prodPrice',type : 'float',useNull : true}
	,{name : 'bean.priceTotal',mapping : 'priceTotal',type : 'float',useNull : true}
	,{name : 'bean.currency',mapping : 'currency',type : 'string',outkey : true}
	,{name : 'bean.purchaseline',mapping : 'purchaseline',type : 'string',outkey : true}
	,{name : 'bean.paycontent',mapping : 'paycontent',type : 'string'}
	,{name : 'bean.odrCancel',mapping : 'odrCancel',type : 'int',useNull : true}
	,{name : 'bean.payType',mapping : 'payType',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});