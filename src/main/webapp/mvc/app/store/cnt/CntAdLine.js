Ext.define('mvc.store.cnt.CntAdLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.cnt.CntAdLine',
model : 'mvc.model.cnt.CntAdLine',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntAdLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});