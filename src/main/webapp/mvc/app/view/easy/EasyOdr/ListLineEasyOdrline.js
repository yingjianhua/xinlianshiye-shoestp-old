Ext.define('mvc.view.easy.EasyOdr.ListLineEasyOdrline',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '商品规格',width : 100,dataIndex : 'bean.spec',sortable : true,renderer : mvc.Tools.JsonRenderer()}
	,{text : '商品图片',width : 100,dataIndex : 'bean.iamge',sortable : true,renderer : mvc.Tools.imgRenderer(100,100,"?x-oss-process=image/resize,m_fixed,h_100,w_100")}
	,{text : '商品名称',width : 100,dataIndex : 'bean.productname',sortable : true,renderer : mvc.Tools.JsonRenderer()}
	,{text : '颜色',width : 100,dataIndex : 'bean.color',sortable : true,renderer : mvc.Tools.JsonRenderer()}
	,{text : '尺码',width : 200,dataIndex : 'bean.size',sortable : true,renderer : mvc.Tools.JsonRenderer()}
	,{text : '数量',width : 100,dataIndex : 'bean.num',sortable : true}
	,{text : '备注',width : 100,dataIndex : 'bean.remarks',sortable : true}
	];
		this.store=Ext.create('mvc.store.easy.EasyOdrline');
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
