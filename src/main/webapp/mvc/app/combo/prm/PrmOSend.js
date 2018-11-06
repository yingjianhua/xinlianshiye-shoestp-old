Ext.define('mvc.combo.prm.PrmOSend',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '未发送'}
	,{value : 1,text : '已发送'}
	]
});