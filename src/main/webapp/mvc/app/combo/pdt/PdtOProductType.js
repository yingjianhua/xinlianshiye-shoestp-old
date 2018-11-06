Ext.define('mvc.combo.pdt.PdtOProductType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '普通产品'}
	,{value : 1,text : '联合采购产品'}
	]
});