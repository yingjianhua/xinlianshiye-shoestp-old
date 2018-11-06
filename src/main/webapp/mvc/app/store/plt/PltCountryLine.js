Ext.define('mvc.store.plt.PltCountryLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.plt.PltCountryLine',
model : 'mvc.model.plt.PltCountryLine',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltCountryLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});