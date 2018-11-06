Ext.define('mvc.combo.odr.OdrCancelType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '缺货'}
	,{value : 1,text : '不是有效订单'}
	,{value : 2,text : '买家要求'}
	,{value : 3,text : '其他原因'}
	]
});