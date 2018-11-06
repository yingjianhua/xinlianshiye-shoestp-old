Ext.define('mvc.store.pdt.PdtAttr',{
extend : 'Ext.data.Store',
requires : 'mvc.model.pdt.PdtAttr',
model : 'mvc.model.pdt.PdtAttr',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtAttr_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});