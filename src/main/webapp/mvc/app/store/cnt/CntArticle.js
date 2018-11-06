Ext.define('mvc.store.cnt.CntArticle',{
extend : 'Ext.data.Store',
requires : 'mvc.model.cnt.CntArticle',
model : 'mvc.model.cnt.CntArticle',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntArticle_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});