Ext.define('mvc.view.plt.PltFreight.ListLinePltFreightLine',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '区间',width : 100,dataIndex : 'bean.section',sortable : true}
	,{text : '简介',width : 200,dataIndex : 'bean.brief',sortable : true}
	,{text : '免运费',width : 100,dataIndex : 'bean.free',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '免运费价格',width : 100,dataIndex : 'bean.freePrice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right'}
	,{text : '附加费用',width : 100,dataIndex : 'bean.extraPrice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right'}
	,{text : '首重区间',width : 100,dataIndex : 'bean.weightSection',sortable : true}
	,{text : '续重区间',width : 100,dataIndex : 'bean.aggravateSection',sortable : true}
	,{text : '首重价格',width : 100,dataIndex : 'bean.weightPrice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right'}
	,{text : '加重价格',width : 100,dataIndex : 'bean.aggravatePrice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right'}
	];
		this.store=Ext.create('mvc.store.plt.PltFreightLine');
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