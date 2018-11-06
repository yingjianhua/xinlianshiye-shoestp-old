Ext.define('mvc.store.lg.LgAccess',{
extend : 'Ext.data.Store',
requires : 'mvc.model.lg.LgAccess',
model : 'mvc.model.lg.LgAccess',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/lg_LgAccess_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});