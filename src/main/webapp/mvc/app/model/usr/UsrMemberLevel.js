Ext.define('mvc.model.usr.UsrMemberLevel',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrMemberLevel_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.icon',mapping : 'icon',type : 'string', convert: mvc.Tools.imgConvert()}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.discount',mapping : 'discount',type : 'float',useNull : true}
	,{name : 'bean.levelUp',mapping : 'levelUp',type : 'float',useNull : true}
	,{name : 'bean.createdBy',mapping : 'createdBy',type : 'string',outkey : true}
	,{name : 'bean.createdTime',mapping : 'createdTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.updatedBy',mapping : 'updatedBy',type : 'string',outkey : true}
	,{name : 'bean.updatedTime',mapping : 'updatedTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});