Ext.define('mvc.store.plt.PltErate',{
extend : 'Ext.data.Store',
requires : 'mvc.model.plt.PltErate',
model : 'mvc.model.plt.PltErate',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltErate_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});