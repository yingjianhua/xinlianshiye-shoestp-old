Ext.define('mvc.store.pdt.PdtAttrCat',{
extend : 'Ext.data.Store',
requires : 'mvc.model.pdt.PdtAttrCat',
model : 'mvc.model.pdt.PdtAttrCat',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtAttrCat_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});