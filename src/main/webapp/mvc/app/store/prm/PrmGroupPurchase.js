Ext.define('mvc.store.prm.PrmGroupPurchase',{
extend : 'Ext.data.Store',
requires : 'mvc.model.prm.PrmGroupPurchase',
model : 'mvc.model.prm.PrmGroupPurchase',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/prm_PrmGroupPurchase_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});