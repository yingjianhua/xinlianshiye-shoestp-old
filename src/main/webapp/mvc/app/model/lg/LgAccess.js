Ext.define('mvc.model.lg.LgAccess',{
extend : 'Ext.data.Model',
idProperty : 'bean.pkey',
proxy : {
	type : 'ajax',
	url : base_path+'/lg_LgAccess_load'
},
fields : [{name : 'bean.pkey',mapping : 'pkey',type : 'int',useNull : true}
	,{name : 'bean.user',mapping : 'user',type : 'string'}
	,{name : 'bean.loginname',mapping : 'loginname',type : 'string'}
	,{name : 'bean.remoteaddr',mapping : 'remoteaddr',type : 'string'}
	,{name : 'bean.requesturl',mapping : 'requesturl',type : 'string'}
	,{name : 'bean.params',mapping : 'params',type : 'string'}
	,{name : 'bean.success',mapping : 'success',type : 'int'}
	,{name : 'bean.remark',mapping : 'remark',type : 'string'}
	,{name : 'bean.processtime',mapping : 'processtime',type : 'int',useNull : true}
	,{name : 'bean.requesttime',mapping : 'requesttime',type : 'date',dateFormat : 'Y-m-d H:i:s'}
	]
});