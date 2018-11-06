Ext.define('mvc.store.cnt.CntSglPageCategory',{
extend : 'Ext.data.Store',
requires : 'mvc.model.cnt.CntSglPageCategory',
model : 'mvc.model.cnt.CntSglPageCategory',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/cnt_CntSglPageCategory_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});