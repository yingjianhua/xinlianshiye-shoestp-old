Ext.define('mvc.combo.usr.UsrOAddress',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '收货地址'}
	,{value : 1,text : '账单地址'}
	]
});