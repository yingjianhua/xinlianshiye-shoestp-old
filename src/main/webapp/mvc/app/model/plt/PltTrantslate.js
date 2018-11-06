Ext.define('mvc.model.plt.PltTrantslate',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltTrantslate_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.hashcode',mapping : 'hashcode',type : 'string'}
	,{name : 'bean.sourceText',mapping : 'sourceText',type : 'string'}
	,{name : 'bean.target',mapping : 'target',type : 'string'}
	,{name : 'bean.targetText',mapping : 'targetText',type : 'string'}
	,{name : 'bean.createdTime',mapping : 'createdTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});