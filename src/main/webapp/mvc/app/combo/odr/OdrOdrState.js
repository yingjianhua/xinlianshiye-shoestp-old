Ext.define('mvc.combo.odr.OdrOdrState',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '待付款'}
	,{value : 1,text : '等待确认付款'}
	,{value : 2,text : '付款错误'}
	,{value : 3,text : '等待发货'}
	,{value : 4,text : '已发货'}
	,{value : 5,text : '完成订单'}
	,{value : 6,text : '已取消订单'}
	]
});