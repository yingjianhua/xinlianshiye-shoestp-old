Ext.define('mvc.combo.odr.OdrOdrType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '联合采购订单'}
	,{value : 1,text : '普通订单'}
	]
});