Ext.define('mvc.combo.pdt.PdtOState',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '上架'}
	,{value : 0,text : '下架'}
	,{value : 2,text : '删除'}
	]
});