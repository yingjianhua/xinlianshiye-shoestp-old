Ext.define('mvc.store.pdt.PdtSize',{
extend : 'Ext.data.Store',
requires : 'mvc.model.pdt.PdtSize',
model : 'mvc.model.pdt.PdtSize',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtSize_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});