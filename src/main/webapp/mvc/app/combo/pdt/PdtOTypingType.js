Ext.define('mvc.combo.pdt.PdtOTypingType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '手工录入'}
	,{value : 1,text : '列表选择'}
	]
});