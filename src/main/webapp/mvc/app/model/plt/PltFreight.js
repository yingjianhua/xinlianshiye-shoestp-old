Ext.define('mvc.model.plt.PltFreight',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltFreight_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.userSys',mapping : 'userSys',type : 'string',outkey : true}
	,{name : 'bean.company',mapping : 'company',type : 'string'}
	,{name : 'bean.logo',mapping : 'logo',type : 'string', convert: mvc.Tools.imgConvert()}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.expressUrl',mapping : 'expressUrl',type : 'string'}
	,{name : 'bean.sort',mapping : 'sort',type : 'int',useNull : true}
	,{name : 'bean.useInterface',mapping : 'useInterface',type : 'int',useNull : true}
	,{name : 'bean.weightMin',mapping : 'weightMin',type : 'int',useNull : true}
	,{name : 'bean.weightMax',mapping : 'weightMax',type : 'int',useNull : true}
	,{name : 'bean.type',mapping : 'type',type : 'int',useNull : true}
	,{name : 'bean.createdBy',mapping : 'createdBy',type : 'string',outkey : true}
	,{name : 'bean.createdTime',mapping : 'createdTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});