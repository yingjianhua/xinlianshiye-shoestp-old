Ext.define('mvc.store.cnt.CntArticleCategory',{
extend : 'Ext.data.Store',
requires : 'mvc.model.cnt.CntArticleCategory',
model : 'mvc.model.cnt.CntArticleCategory',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntArticleCategory_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});