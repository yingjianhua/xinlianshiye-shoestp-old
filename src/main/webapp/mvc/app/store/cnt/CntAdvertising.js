Ext.define('mvc.store.cnt.CntAdvertising',{
extend : 'Ext.data.Store',
requires : 'mvc.model.cnt.CntAdvertising',
model : 'mvc.model.cnt.CntAdvertising',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntAdvertising_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});