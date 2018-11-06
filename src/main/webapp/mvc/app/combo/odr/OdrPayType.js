Ext.define('mvc.combo.odr.OdrPayType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : 'TT'}
	,{value : 1,text : '支付宝'}
	,{value : 2,text : '微信'}
	]
});