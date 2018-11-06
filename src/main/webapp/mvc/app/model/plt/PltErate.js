Ext.define('mvc.model.plt.PltErate',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltErate_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.logo',mapping : 'logo',type : 'string', convert: mvc.Tools.imgConvert()}
	,{name : 'bean.curName',mapping : 'curName',type : 'string'}
	,{name : 'bean.symbol',mapping : 'symbol',type : 'string'}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.siteCur',mapping : 'siteCur',type : 'int',useNull : true}
	,{name : 'bean.rate',mapping : 'rate',type : 'float',useNull : true}
	,{name : 'bean.supCur',mapping : 'supCur',type : 'int',useNull : true}
	,{name : 'bean.nowrate',mapping : 'nowrate',type : 'float',useNull : true}
	,{name : 'bean.createdBy',mapping : 'createdBy',type : 'string',outkey : true}
	,{name : 'bean.createdTime',mapping : 'createdTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});