Ext.define('mvc.model.plt.PltProvince',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltProvince_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.main',mapping : 'main',type : 'string',outkey : true}
	,{name : 'bean.name',mapping : 'name',type : 'string', convert: mvc.Tools.i18nConvert(false)}
	,{name : 'bean.shortName',mapping : 'shortName',type : 'string'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});