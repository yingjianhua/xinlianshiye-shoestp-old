Ext.define('mvc.store.cnt.CntSglPage',{
extend : 'Ext.data.Store',
requires : 'mvc.model.cnt.CntSglPage',
model : 'mvc.model.cnt.CntSglPage',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntSglPage_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});