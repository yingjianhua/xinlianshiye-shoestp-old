Ext.define('mvc.store.prm.PrmGroupOrderLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.prm.PrmGroupOrderLine',
model : 'mvc.model.prm.PrmGroupOrderLine',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/prm_PrmGroupOrderLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});