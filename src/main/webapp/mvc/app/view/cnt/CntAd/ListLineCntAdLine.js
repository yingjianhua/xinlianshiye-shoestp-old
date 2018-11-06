Ext.define('mvc.view.cnt.CntAd.ListLineCntAdLine',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [
	 {text : '启用标志',width : 75,dataIndex : 'bean.enabled',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OEnabled')}
	,{text : '广告名称',width : 120,dataIndex : 'bean.name',sortable : true}
	,{text : '广告简介',width : 120,dataIndex : 'bean.brief',sortable : true}
	,{text : '广告图片',width : 100,dataIndex : 'bean.image',sortable : true,renderer : mvc.Tools.imgRenderer(0, 50)}
	,{text : '广告链接',width : 250,dataIndex : 'bean.url',sortable : true}
	,{text : '排序',width : 80,dataIndex : 'bean.sort',sortable : true}
	,{text : '是否主图',width : 100,dataIndex : 'bean.mainImg',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	];
		this.store=Ext.create('mvc.store.cnt.CntAdLine');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
		this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		xtype : 'pagingtoolbar',
		store : this.store,
		dock : 'bottom',
		displayInfo : true,
		displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
		emptyMsg : '没有数据',
		items : [{
				xtype : Ext.create('mvc.tools.ComboxPaging',{myList : this})
			}]
	}];
		this.callParent(arguments);}
});