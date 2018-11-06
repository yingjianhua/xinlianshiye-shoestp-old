Ext.define('mvc.combo.prm.PrmState',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '未开始售卖'}
	,{value : 1,text : '售卖中'}
	,{value : 2,text : '已售完'}
	,{value : 3,text : '结束售卖'}
	]
});