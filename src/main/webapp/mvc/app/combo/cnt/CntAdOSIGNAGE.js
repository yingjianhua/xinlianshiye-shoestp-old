Ext.define('mvc.combo.cnt.CntAdOSIGNAGE',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '移动端'}
	,{value : 1,text : 'PC端'}
	,{value : 2,text : 'IOS'}
	,{value : 3,text : 'ANDROID'}
	,{value : 4,text : '其他'}
	]
});