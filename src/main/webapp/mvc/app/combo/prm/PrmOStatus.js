Ext.define('mvc.combo.prm.PrmOStatus',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '未开始'}
	,{value : 1,text : '即将开始'}
	,{value : 2,text : '进行中'}
	,{value : 3,text : '即将结束'}
	,{value : 4,text : '已结束'}
	,{value : 5,text : '逻辑删除'}
	]
});