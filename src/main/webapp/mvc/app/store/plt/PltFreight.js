Ext.define('mvc.store.plt.PltFreight',{
extend : 'Ext.data.Store',
requires : 'mvc.model.plt.PltFreight',
model : 'mvc.model.plt.PltFreight',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltFreight_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});