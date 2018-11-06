Ext.define('mvc.model.usr.UsrPurchase',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrPurchase_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.name',mapping : 'name',type : 'string'}
	,{name : 'bean.surname',mapping : 'surname',type : 'string'}
	,{name : 'bean.sex',mapping : 'sex',type : 'int',useNull : true}
	,{name : 'bean.icon',mapping : 'icon',type : 'string'}
	,{name : 'bean.email',mapping : 'email',type : 'string'}
	,{name : 'bean.regTime',mapping : 'regTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.loginName',mapping : 'loginName',type : 'string'}
	,{name : 'bean.currency',mapping : 'currency',type : 'string',outkey : true}
	,{name : 'bean.telphone',mapping : 'telphone',type : 'string'}
	,{name : 'bean.company',mapping : 'company',type : 'string'}
	,{name : 'bean.address',mapping : 'address',type : 'string'}
	,{name : 'bean.lastLoginTime',mapping : 'lastLoginTime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	,{name : 'bean.country',mapping : 'country',type : 'string',outkey : true}
	,{name : 'bean.facebookUserId',mapping : 'facebookUserId',type : 'string'}
	,{name : 'bean.googleUserId',mapping : 'googleUserId',type : 'string'}
	,{name : 'bean.memberLevel',mapping : 'memberLevel',type : 'string',outkey : true}
	,{name : 'bean.rowVersion',mapping : 'rowVersion',type : 'int',useNull : true}
	]
});