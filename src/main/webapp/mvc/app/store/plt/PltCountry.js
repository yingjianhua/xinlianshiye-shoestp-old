Ext.define('mvc.store.plt.PltCountry',{
extend : 'Ext.data.Store',
requires : 'mvc.model.plt.PltCountry',
model : 'mvc.model.plt.PltCountry',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltCountry_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});