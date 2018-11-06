Ext.define('mvc.view.plt.PltCountry.ListLinePltProvince',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '省份名称',width : 100,dataIndex : 'bean.name',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '省份缩写',width : 100,dataIndex : 'bean.shortName',sortable : true}
	];
		this.store=Ext.create('mvc.store.plt.PltProvince');
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