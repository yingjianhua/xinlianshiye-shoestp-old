Ext.define('mvc.store.plt.PltPay',{
extend : 'Ext.data.Store',
requires : 'mvc.model.plt.PltPay',
model : 'mvc.model.plt.PltPay',
pageSize : 20,
remoteSort : false,
autoLoad : true,
proxy : {
	type : 'ajax',
	url : base_path+'/plt_PltPay_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});