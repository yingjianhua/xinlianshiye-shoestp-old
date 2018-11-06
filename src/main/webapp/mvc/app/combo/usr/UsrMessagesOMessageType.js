Ext.define('mvc.combo.usr.UsrMessagesOMessageType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '系统消息'}
	,{value : 0,text : '用户消息'}
	]
});