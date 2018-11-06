Ext.define('mvc.model.plt.PltCountry',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltCountry_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.shortName',mapping : 'shortName',type : 'string'}
	,{name : 'bean.zone',mapping : 'zone',type : 'string'}
	,{name : 'bean.currency',mapping : 'currency',type : 'string',outkey : true}
	,{name : 'bean.nationalFlag',mapping : 'nationalFlag',type : 'string', convert: mvc.Tools.imgConvert()}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.hot',mapping : 'hot',type : 'int',useNull : true}
	,{name : 'bean.isdefault',mapping : 'isdefault',type : 'int',useNull : true}
	,{name : 'bean.createdBy',mapping : 'createdBy',type : 'string',outkey : true}
	,{name : 'bean.createdTime',mapping : 'createdTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});