Ext.define('mvc.store.easy.EasyOdrline',{
extend : 'Ext.data.Store',
requires : 'mvc.model.easy.EasyOdrline',
model : 'mvc.model.easy.EasyOdrline',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/easy_EasyOdrline_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});