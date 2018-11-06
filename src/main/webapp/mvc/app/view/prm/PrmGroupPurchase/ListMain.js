Ext.define('mvc.view.prm.PrmGroupPurchase.ListMain',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
this.columns =[{text : '活动标题',width : 100,dataIndex : 'bean.title',sortable : true}
	,{text : '开始时间',width : 140,dataIndex : 'bean.startTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '结束时间',width : 140,dataIndex : 'bean.endTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '活动状态',width : 137,dataIndex : 'bean.status',sortable : true,renderer : mvc.Tools.optRenderer('prm','Prm','OStatus')}
	,{text : '提前预告',width : 100,dataIndex : 'bean.preTime',sortable : true,renderer : mvc.Tools.optRenderer('prm','Prm','OPreTime')}
	,{text : '供应商',width : 208,dataIndex : 'bean.createdBy',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'usr',mn : 'view.usr.UsrSupplier.List'}
	,{text : '建档时间',width : 140,dataIndex : 'bean.createdTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '更新时间',width : 140,dataIndex : 'bean.updatedTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	];
		this.store=Ext.create('mvc.store.prm.PrmGroupPurchase');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
		this.dockedItems=[{
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