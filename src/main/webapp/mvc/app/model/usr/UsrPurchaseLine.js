Ext.define('mvc.model.usr.UsrPurchaseLine',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrPurchaseLine_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.surname',mapping : 'surname',type : 'string'}
	,{name : 'bean.emailcode',mapping : 'emailcode',type : 'string'}
	,{name : 'bean.country',mapping : 'country',type : 'string',outkey : true}
	,{name : 'bean.region',mapping : 'region',type : 'string',outkey : true}
	,{name : 'bean.city',mapping : 'city',type : 'string'}
	,{name : 'bean.address',mapping : 'address',type : 'string'}
	,{name : 'bean.phonenumber',mapping : 'phonenumber',type : 'string'}
	,{name : 'bean.isdefault',mapping : 'isdefault',type : 'int',useNull : true}
	,{name : 'bean.purchase',mapping : 'purchase',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	,{name : 'bean.addrsstype',mapping : 'addrsstype',type : 'int',useNull : true}
	]
});