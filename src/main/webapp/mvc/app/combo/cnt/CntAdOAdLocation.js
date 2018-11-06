Ext.define('mvc.combo.cnt.CntAdOAdLocation',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '首页轮播图'}
	,{value : 5,text : '首页左侧上部'}
	,{value : 6,text : '首页左侧下部'}
	,{value : 10,text : '首页中间'}
	,{value : 15,text : '产品分类广告'}
	,{value : 20,text : '供应商列表头部'}
	]
});