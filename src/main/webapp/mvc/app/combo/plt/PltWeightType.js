Ext.define('mvc.combo.plt.PltWeightType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '重量'}
	,{value : 1,text : '体积'}
	,{value : 2,text : '件数'}
	]
});