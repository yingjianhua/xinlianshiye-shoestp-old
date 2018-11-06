Ext.define('mvc.model.prm.PrmGroupPurchase',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/prm_PrmGroupPurchase_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.title',mapping : 'title',type : 'string'}
	,{name : 'bean.startTime',mapping : 'startTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.endTime',mapping : 'endTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.status',mapping : 'status',type : 'int',useNull : true}
	,{name : 'bean.preTime',mapping : 'preTime',type : 'int',useNull : true}
	,{name : 'bean.createdBy',mapping : 'createdBy',type : 'string',outkey : true}
	,{name : 'bean.createdTime',mapping : 'createdTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.updatedTime',mapping : 'updatedTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});