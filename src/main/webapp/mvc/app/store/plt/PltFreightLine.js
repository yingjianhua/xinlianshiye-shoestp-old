Ext.define('mvc.store.plt.PltFreightLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.plt.PltFreightLine',
model : 'mvc.model.plt.PltFreightLine',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltFreightLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});