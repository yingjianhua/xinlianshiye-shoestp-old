Ext.define('mvc.store.odr.OdrOrderLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.odr.OdrOrderLine',
model : 'mvc.model.odr.OdrOrderLine',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/odr_OdrOrderLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});