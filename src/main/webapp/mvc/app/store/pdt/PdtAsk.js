Ext.define('mvc.store.pdt.PdtAsk',{
extend : 'Ext.data.Store',
requires : 'mvc.model.pdt.PdtAsk',
model : 'mvc.model.pdt.PdtAsk',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtAsk_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});