Ext.define('mvc.model.usr.UsrSupplierCategory',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrSupplierCategory_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.showName',mapping : 'showName',type : 'string'}
	,{name : 'bean.categoryUp',mapping : 'categoryUp',type : 'string',outkey : true}
	,{name : 'bean.createBy',mapping : 'createBy',type : 'string',outkey : true}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});