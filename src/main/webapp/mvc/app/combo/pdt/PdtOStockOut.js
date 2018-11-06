Ext.define('mvc.combo.pdt.PdtOStockOut',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '脱销'}
	,{value : 1,text : '到货通知'}
	]
});