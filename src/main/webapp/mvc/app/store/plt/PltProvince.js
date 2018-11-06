Ext.define('mvc.store.plt.PltProvince',{
extend : 'Ext.data.Store',
requires : 'mvc.model.plt.PltProvince',
model : 'mvc.model.plt.PltProvince',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltProvince_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});