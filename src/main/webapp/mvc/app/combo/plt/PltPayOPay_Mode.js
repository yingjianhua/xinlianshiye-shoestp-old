Ext.define('mvc.combo.plt.PltPayOPay_Mode',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 5,text : '线下支付'}
	,{value : 10,text : 'TT支付'}
	]
});