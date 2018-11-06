Ext.define('mvc.combo.plt.PltPayOPAYMODE',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '未设置'}
	,{value : 5,text : '线下支付'}
	,{value : 10,text : 'TT支付'}
	]
});