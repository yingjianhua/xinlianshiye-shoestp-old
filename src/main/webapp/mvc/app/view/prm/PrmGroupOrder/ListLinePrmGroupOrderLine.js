Ext.define('mvc.view.prm.PrmGroupOrder.ListLinePrmGroupOrderLine',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '产品规格',width : 100,dataIndex : 'bean.product',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '订购数量',width : 100,dataIndex : 'bean.productnum',sortable : true}
	,{text : '订单价格',width : 100,dataIndex : 'bean.productprice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right'}
	];
		this.store=Ext.create('mvc.store.prm.PrmGroupOrderLine');
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