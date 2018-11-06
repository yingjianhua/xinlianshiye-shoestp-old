Ext.define('mvc.store.plt.PltConfig',{
extend : 'Ext.data.Store',
requires : 'mvc.model.plt.PltConfig',
model : 'mvc.model.plt.PltConfig',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltConfig_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});