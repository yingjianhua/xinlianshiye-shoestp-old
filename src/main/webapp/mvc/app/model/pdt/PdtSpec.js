Ext.define('mvc.model.pdt.PdtSpec',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtSpec_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.product',mapping : 'product',type : 'string',outkey : true}
	,{name : 'bean.color',mapping : 'color',type : 'string',outkey : true}
	,{name : 'bean.size',mapping : 'size',type : 'string',outkey : true}
	,{name : 'bean.keyName',mapping : 'keyName',type : 'string'}
	,{name : 'bean.sku',mapping : 'sku',type : 'string'}
	,{name : 'bean.price',mapping : 'price',type : 'float',useNull : true}
	,{name : 'bean.markup',mapping : 'markup',type : 'float',useNull : true}
	,{name : 'bean.storeCount',mapping : 'storeCount',type : 'int',useNull : true}
	,{name : 'bean.weight',mapping : 'weight',type : 'float',useNull : true}
	,{name : 'bean.pics',mapping : 'pics',type : 'string', convert: mvc.Tools.imgConvert()}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});