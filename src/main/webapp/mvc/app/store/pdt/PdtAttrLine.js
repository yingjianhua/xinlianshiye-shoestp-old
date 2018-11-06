Ext.define('mvc.store.pdt.PdtAttrLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.pdt.PdtAttrLine',
model : 'mvc.model.pdt.PdtAttrLine',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtAttrLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});