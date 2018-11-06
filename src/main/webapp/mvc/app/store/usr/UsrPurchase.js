Ext.define('mvc.store.usr.UsrPurchase',{
extend : 'Ext.data.Store',
requires : 'mvc.model.usr.UsrPurchase',
model : 'mvc.model.usr.UsrPurchase',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrPurchase_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});