Ext.define('mvc.model.cnt.CntMagazine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntMagazine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.specialPictures',mapping : 'specialPictures',type : 'string', convert: mvc.Tools.imgConvert()}
	,{name : 'bean.time',mapping : 'time',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.cycle',mapping : 'cycle',type : 'int',useNull : true}
	,{name : 'bean.content',mapping : 'content',type : 'string'}
	,{name : 'bean.contenturl',mapping : 'contenturl',type : 'string'}
	,{name : 'bean.createdBy',mapping : 'createdBy',type : 'string',outkey : true}
	,{name : 'bean.createdTime',mapping : 'createdTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});