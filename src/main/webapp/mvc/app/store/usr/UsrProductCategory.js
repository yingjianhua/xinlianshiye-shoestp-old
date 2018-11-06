Ext.define('mvc.store.usr.UsrProductCategory',{
extend : 'Ext.data.Store',
requires : 'mvc.model.usr.UsrProductCategory',
model : 'mvc.model.usr.UsrProductCategory',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrProductCategory_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});