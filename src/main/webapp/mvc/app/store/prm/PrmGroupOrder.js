Ext.define('mvc.store.prm.PrmGroupOrder',{
extend : 'Ext.data.Store',
requires : 'mvc.model.prm.PrmGroupOrder',
model : 'mvc.model.prm.PrmGroupOrder',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/prm_PrmGroupOrder_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});