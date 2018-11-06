Ext.define('mvc.store.usr.UsrSupplierRole',{
extend : 'Ext.data.Store',
requires : 'mvc.model.usr.UsrSupplierRole',
model : 'mvc.model.usr.UsrSupplierRole',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrSupplierRole_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});