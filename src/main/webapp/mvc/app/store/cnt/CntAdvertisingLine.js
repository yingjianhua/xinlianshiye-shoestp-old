Ext.define('mvc.store.cnt.CntAdvertisingLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.cnt.CntAdvertisingLine',
model : 'mvc.model.cnt.CntAdvertisingLine',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntAdvertisingLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});