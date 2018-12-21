Ext.define('mvc.store.usr.UsrSubscribe',{
extend : 'Ext.data.Store',
requires : 'mvc.model.usr.UsrSubscribe',
model : 'mvc.model.usr.UsrSubscribe',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrSubscribe_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});