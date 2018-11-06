Ext.define('mvc.combo.pdt.PdtSatisfaction',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '一星评级'}
	,{value : 2,text : '二星评级'}
	,{value : 3,text : '三星评级'}
	,{value : 4,text : '四星评级'}
	,{value : 5,text : '五星评级'}
	]
});