Ext.define('mvc.model.plt.PltConfig',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltConfig_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.variable',mapping : 'variable',type : 'string'}
	,{name : 'bean.value',mapping : 'value',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});