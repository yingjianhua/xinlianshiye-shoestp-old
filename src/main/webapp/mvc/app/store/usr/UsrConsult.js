Ext.define('mvc.store.usr.UsrConsult',{
extend : 'Ext.data.Store',
requires : 'mvc.model.usr.UsrConsult',
model : 'mvc.model.usr.UsrConsult',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrConsult_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});