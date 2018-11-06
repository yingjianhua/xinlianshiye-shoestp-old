Ext.define('mvc.store.usr.UsrSupplier',{
extend : 'Ext.data.Store',
requires : 'mvc.model.usr.UsrSupplier',
model : 'mvc.model.usr.UsrSupplier',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrSupplier_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});