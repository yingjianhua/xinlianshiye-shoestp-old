Ext.define('mvc.store.cnt.CntAd',{
extend : 'Ext.data.Store',
requires : 'mvc.model.cnt.CntAd',
model : 'mvc.model.cnt.CntAd',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntAd_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});