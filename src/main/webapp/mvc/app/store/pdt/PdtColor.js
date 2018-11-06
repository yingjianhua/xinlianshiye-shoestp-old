Ext.define('mvc.store.pdt.PdtColor',{
extend : 'Ext.data.Store',
requires : 'mvc.model.pdt.PdtColor',
model : 'mvc.model.pdt.PdtColor',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtColor_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});