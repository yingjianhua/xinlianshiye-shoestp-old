Ext.define('mvc.combo.cnt.CntAdOAdDisplayType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '渐显'}
	,{value : 1,text : '上滚动'}
	,{value : 2,text : '下滚动'}
	]
});