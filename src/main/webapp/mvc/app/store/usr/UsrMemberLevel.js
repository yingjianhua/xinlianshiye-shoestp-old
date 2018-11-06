Ext.define('mvc.store.usr.UsrMemberLevel',{
extend : 'Ext.data.Store',
requires : 'mvc.model.usr.UsrMemberLevel',
model : 'mvc.model.usr.UsrMemberLevel',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/usr_UsrMemberLevel_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});