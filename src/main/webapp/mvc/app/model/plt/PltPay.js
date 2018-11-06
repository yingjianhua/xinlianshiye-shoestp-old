Ext.define('mvc.model.plt.PltPay',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltPay_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.mode',mapping : 'mode',type : 'int',useNull : true}
	,{name : 'bean.supplier',mapping : 'supplier',type : 'string',outkey : true}
	,{name : 'bean.poundage',mapping : 'poundage',type : 'float',useNull : true}
	,{name : 'bean.extraCosts',mapping : 'extraCosts',type : 'float',useNull : true}
	,{name : 'bean.enabled',mapping : 'enabled',type : 'int',useNull : true}
	,{name : 'bean.paysetting',mapping : 'paysetting',type : 'int',useNull : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});