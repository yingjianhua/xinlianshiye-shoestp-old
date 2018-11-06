Ext.define('mvc.combo.usr.UsrOIsAuth',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '未认证'}
	,{value : 1,text : '已认证'}
	]
});