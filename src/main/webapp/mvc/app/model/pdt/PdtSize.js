Ext.define('mvc.model.pdt.PdtSize',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtSize_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.productCategory',mapping : 'productCategory',type : 'string',outkey : true}
	,{name : 'bean.createBy',mapping : 'createBy',type : 'string',outkey : true}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});