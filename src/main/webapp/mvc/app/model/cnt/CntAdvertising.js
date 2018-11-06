Ext.define('mvc.model.cnt.CntAdvertising',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntAdvertising_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.number',mapping : 'number',type : 'int',useNull : true}
	,{name : 'bean.pagename',mapping : 'pagename',type : 'string'}
	,{name : 'bean.adposition',mapping : 'adposition',type : 'string'}
	,{name : 'bean.piccount',mapping : 'piccount',type : 'int',useNull : true}
	,{name : 'bean.width',mapping : 'width',type : 'int',useNull : true}
	,{name : 'bean.height',mapping : 'height',type : 'int',useNull : true}
	,{name : 'bean.showtype',mapping : 'showtype',type : 'int',useNull : true}
	,{name : 'bean.picpath',mapping : 'picpath',type : 'string'}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.brief',mapping : 'brief',type : 'string'}
	,{name : 'bean.url',mapping : 'url',type : 'string'}
	,{name : 'bean.createBy',mapping : 'createBy',type : 'string',outkey : true}
	,{name : 'bean.createTime',mapping : 'createTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});