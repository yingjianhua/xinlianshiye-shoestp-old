Ext.define('mvc.store.prm.PrmGroupPurchaseLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.prm.PrmGroupPurchaseLine',
model : 'mvc.model.prm.PrmGroupPurchaseLine',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/prm_PrmGroupPurchaseLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});