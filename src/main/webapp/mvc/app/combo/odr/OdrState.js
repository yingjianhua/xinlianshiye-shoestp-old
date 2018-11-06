Ext.define('mvc.combo.odr.OdrState',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '联合采购订单'}
	,{value : 1,text : '普通订单'}
	]
});