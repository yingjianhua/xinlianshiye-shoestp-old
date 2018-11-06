Ext.define('mvc.view.odr.OdrOrder.ListLineOdrOrderLine',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '产品',width : 200,dataIndex : 'bean.spec',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '数量',width : 100,dataIndex : 'bean.qty',sortable : true}
	,{text : '小计',width : 100,dataIndex : 'bean.subtotal',sortable : true,align : 'right'}
	];
		this.store=Ext.create('mvc.store.odr.OdrOrderLine');
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