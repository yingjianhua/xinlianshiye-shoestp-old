Ext.define('mvc.combo.prm.PrmOPreTime',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 3,text : '提前三天'}
	,{value : 7,text : '提前七天'}
	,{value : 15,text : '提前十五天'}
	,{value : 30,text : '提前一个月'}
	]
});