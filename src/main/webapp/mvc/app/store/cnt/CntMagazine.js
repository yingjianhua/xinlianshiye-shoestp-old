Ext.define('mvc.store.cnt.CntMagazine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.cnt.CntMagazine',
model : 'mvc.model.cnt.CntMagazine',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntMagazine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});