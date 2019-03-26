Ext.define('mvc.store.easy.EasyOdr',{
extend : 'Ext.data.Store',
requires : 'mvc.model.easy.EasyOdr',
model : 'mvc.model.easy.EasyOdr',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/easy_EasyOdr_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});