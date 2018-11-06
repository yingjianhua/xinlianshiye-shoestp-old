Ext.define('mvc.store.usr.UsrMessages',{
extend : 'Ext.data.Store',
requires : 'mvc.model.usr.UsrMessages',
model : 'mvc.model.usr.UsrMessages',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrMessages_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});