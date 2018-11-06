Ext.define('mvc.store.cnt.CntArticleCategoryLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.cnt.CntArticleCategoryLine',
model : 'mvc.model.cnt.CntArticleCategoryLine',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntArticleCategoryLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});