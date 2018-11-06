Ext.define('mvc.store.pdt.PdtProduct',{
extend : 'Ext.data.Store',
requires : 'mvc.model.pdt.PdtProduct',
model : 'mvc.model.pdt.PdtProduct',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtProduct_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});