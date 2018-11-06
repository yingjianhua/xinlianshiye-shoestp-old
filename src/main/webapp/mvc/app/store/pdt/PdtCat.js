Ext.define('mvc.store.pdt.PdtCat',{
extend : 'Ext.data.Store',
requires : 'mvc.model.pdt.PdtCat',
model : 'mvc.model.pdt.PdtCat',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtCat_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});