Ext.define('mvc.view.prm.PrmGroupPurchase.ListLinePrmGroupPurchaseLine',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '产品',width : 358,dataIndex : 'bean.product',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'pdt',mn : 'view.pdt.PdtProduct.List'}
	,{text : '数量',width : 100,dataIndex : 'bean.count',sortable : true}
	,{text : '已订购数量',width : 100,dataIndex : 'bean.boughtCount',sortable : true}
	,{text : '明细状态',width : 100,dataIndex : 'bean.state',sortable : true,renderer : mvc.Tools.optRenderer('prm','Prm','OSend')}
	];
		this.store=Ext.create('mvc.store.prm.PrmGroupPurchaseLine');
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