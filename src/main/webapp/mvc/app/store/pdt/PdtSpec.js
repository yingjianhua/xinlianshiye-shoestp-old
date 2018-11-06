Ext.define('mvc.store.pdt.PdtSpec',{
extend : 'Ext.data.Store',
requires : 'mvc.model.pdt.PdtSpec',
model : 'mvc.model.pdt.PdtSpec',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/pdt_PdtSpec_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});