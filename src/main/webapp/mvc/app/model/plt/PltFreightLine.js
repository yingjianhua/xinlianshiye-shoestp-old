Ext.define('mvc.model.plt.PltFreightLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltFreightLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.main',mapping : 'main',type : 'string',outkey : true}
	,{name : 'bean.section',mapping : 'section',type : 'string'}
	,{name : 'bean.brief',mapping : 'brief',type : 'string'}
	,{name : 'bean.free',mapping : 'free',type : 'int',useNull : true}
	,{name : 'bean.freePrice',mapping : 'freePrice',type : 'float',useNull : true}
	,{name : 'bean.extraPrice',mapping : 'extraPrice',type : 'float',useNull : true}
	,{name : 'bean.weightSection',mapping : 'weightSection',type : 'int',useNull : true}
	,{name : 'bean.aggravateSection',mapping : 'aggravateSection',type : 'int',useNull : true}
	,{name : 'bean.weightPrice',mapping : 'weightPrice',type : 'float',useNull : true}
	,{name : 'bean.aggravatePrice',mapping : 'aggravatePrice',type : 'float',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});