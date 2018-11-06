Ext.define('mvc.store.cnt.CntLink',{
extend : 'Ext.data.Store',
requires : 'mvc.model.cnt.CntLink',
model : 'mvc.model.cnt.CntLink',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntLink_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});