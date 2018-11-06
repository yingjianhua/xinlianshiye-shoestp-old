Ext.define('mvc.store.plt.PltTrantslate',{
extend : 'Ext.data.Store',
requires : 'mvc.model.plt.PltTrantslate',
model : 'mvc.model.plt.PltTrantslate',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltTrantslate_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});