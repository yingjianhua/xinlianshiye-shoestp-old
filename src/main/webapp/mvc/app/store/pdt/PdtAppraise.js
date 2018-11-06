Ext.define('mvc.store.pdt.PdtAppraise',{
extend : 'Ext.data.Store',
requires : 'mvc.model.pdt.PdtAppraise',
model : 'mvc.model.pdt.PdtAppraise',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtAppraise_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});