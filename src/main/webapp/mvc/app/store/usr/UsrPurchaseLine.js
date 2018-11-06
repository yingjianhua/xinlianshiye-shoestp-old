Ext.define('mvc.store.usr.UsrPurchaseLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.usr.UsrPurchaseLine',
model : 'mvc.model.usr.UsrPurchaseLine',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrPurchaseLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});