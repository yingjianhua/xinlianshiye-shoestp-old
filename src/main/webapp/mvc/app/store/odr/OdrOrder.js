Ext.define('mvc.store.odr.OdrOrder',{
extend : 'Ext.data.Store',
requires : 'mvc.model.odr.OdrOrder',
model : 'mvc.model.odr.OdrOrder',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/odr_OdrOrder_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});