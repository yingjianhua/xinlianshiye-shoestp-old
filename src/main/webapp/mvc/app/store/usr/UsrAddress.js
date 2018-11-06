Ext.define('mvc.store.usr.UsrAddress',{
extend : 'Ext.data.Store',
requires : 'mvc.model.usr.UsrAddress',
model : 'mvc.model.usr.UsrAddress',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrAddress_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});