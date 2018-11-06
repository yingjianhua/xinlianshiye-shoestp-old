Ext.define('mvc.combo.usr.UsrOStatus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '未审核'}
	,{value : 1,text : '已审核'}
	]
});