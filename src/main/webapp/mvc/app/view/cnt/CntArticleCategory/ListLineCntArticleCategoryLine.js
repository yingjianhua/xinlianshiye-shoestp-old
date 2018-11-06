Ext.define('mvc.view.cnt.CntArticleCategory.ListLineCntArticleCategoryLine',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '子项名称',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '启用标志',width : 75,dataIndex : 'bean.enabled',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OEnabled')}
	,{text : '建档员',width : 75,dataIndex : 'bean.createBy',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'sys',mn : 'view.sys.SysUser.List'}
	,{text : '建档时间',width : 140,dataIndex : 'bean.createTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	];
		this.store=Ext.create('mvc.store.cnt.CntArticleCategoryLine');
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