Ext.define('mvc.store.usr.UsrSupplierCategory',{
extend : 'Ext.data.Store',
requires : 'mvc.model.usr.UsrSupplierCategory',
model : 'mvc.model.usr.UsrSupplierCategory',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrSupplierCategory_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});