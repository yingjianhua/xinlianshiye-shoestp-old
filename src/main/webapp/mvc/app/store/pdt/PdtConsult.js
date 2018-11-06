Ext.define('mvc.store.pdt.PdtConsult',{
extend : 'Ext.data.Store',
requires : 'mvc.model.pdt.PdtConsult',
model : 'mvc.model.pdt.PdtConsult',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtConsult_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});