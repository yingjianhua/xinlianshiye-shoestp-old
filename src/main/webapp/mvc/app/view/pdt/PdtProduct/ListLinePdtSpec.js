Ext.define('mvc.view.pdt.PdtProduct.ListLinePdtSpec',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '产品颜色关联图片',width : 227,dataIndex : 'bean.pics',sortable : true,renderer : mvc.Tools.imgRenderer(80)}
	,{text : '产品顏色',width : 100,dataIndex : 'bean.color',sortable : true,renderer : mvc.Tools.JsonRenderer(),md : 'pdt',mn : 'view.pdt.PdtColor.List'}
	,{text : '产品尺寸',width : 174,dataIndex : 'bean.size',sortable : true,renderer : mvc.Tools.JsonRenderer(),md : 'pdt',mn : 'view.pdt.PdtSize.List'}
	,{text : '组合属性',width : 203,dataIndex : 'bean.keyName',sortable : true,renderer : mvc.Tools.JsonRenderer()}
	,{text : 'SKU（出厂编码）',width : 245,dataIndex : 'bean.sku',sortable : true}
	,{text : '价格',width : 100,dataIndex : 'bean.price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '加价',width : 100,dataIndex : 'bean.markup',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '库存数量',width : 100,dataIndex : 'bean.storeCount',sortable : true}
	,{text : '单价',width : 100,dataIndex : 'bean.weight',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right'}
	];
		this.store=Ext.create('mvc.store.pdt.PdtSpec');
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