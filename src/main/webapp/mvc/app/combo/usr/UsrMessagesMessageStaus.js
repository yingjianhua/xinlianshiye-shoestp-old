Ext.define('mvc.combo.usr.UsrMessagesMessageStaus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '已读'}
	,{value : 0,text : '未读'}
	]
});