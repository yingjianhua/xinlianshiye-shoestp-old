Ext.define('mvc.view.odr.OdrOrder.ListMain',{
extend : 'mvc.tools.RowexpanderGrid',
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
//viewConfig : {enableTextSelection : true},
initComponent : function(){
this.columns =[{text : '用户',width : 120,dataIndex : 'bean.purchase',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'usr',mn : 'view.usr.UsrPurchase.List'}
	,{text : '订单号',width : 200,dataIndex : 'bean.orderNum',sortable : true}
	,{text : '运单号',width : 100,dataIndex : 'bean.expressNum',sortable : true,expandCol : true,hidden : true}
	,{text : '发货方式',width : 100,dataIndex : 'bean.delivery',sortable : true,expandCol : true,hidden : true}
	,{text : '包裹备注',width : 100,dataIndex : 'bean.pagRemarks',sortable : true,expandCol : true,hidden : true}
	,{text : '订单备注',width : 100,dataIndex : 'bean.odrRemarks',sortable : true,expandCol : true,hidden : true}
	,{text : '下单时间',width : 140,dataIndex : 'bean.time',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),expandCol : true}
	,{text : '订单状态',width : 100,dataIndex : 'bean.state',sortable : true,renderer : mvc.Tools.optRenderer('odr','Odr','OdrState')}
	,{text : '订单类型',width : 100,dataIndex : 'bean.type',sortable : true,renderer : mvc.Tools.optRenderer('odr','Odr','OdrType')}
	,{text : '运费',width : 100,dataIndex : 'bean.freightPrice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right',expandCol : true,hidden : true}
	,{text : '保险费',width : 100,dataIndex : 'bean.insurancePrice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right',expandCol : true,hidden : true}
	,{text : '产品总价',width : 100,dataIndex : 'bean.prodPrice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right',expandCol : true,hidden : true}
	,{text : '总价',width : 100,dataIndex : 'bean.priceTotal',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right',expandCol : true,hidden : true}
	,{text : '货币',width : 100,dataIndex : 'bean.currency',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'plt',mn : 'view.plt.PltErate.List',expandCol : true}
	,{text : '地址',width : 100,dataIndex : 'bean.address',sortable : true,expandCol : true,hidden : true}
	,{text : '名字',width : 100,dataIndex : 'bean.name',sortable : true,expandCol : true,hidden : true}
	,{text : '邮政编码',width : 100,dataIndex : 'bean.postalcode',sortable : true,expandCol : true,hidden : true}
	,{text : '电话号码',width : 100,dataIndex : 'bean.phone',sortable : true,expandCol : true,hidden : true}
	,{text : '支付内容',width : 100,dataIndex : 'bean.paycontent',sortable : true,expandCol : true,hidden : true}
	,{text : '取消原因',width : 100,dataIndex : 'bean.odrCancel',sortable : true,renderer : mvc.Tools.optRenderer('odr','Odr','CancelType'),expandCol:true,hidden : true}
	,{text : '支付方式',width : 100,dataIndex : 'bean.payType',sortable : true,renderer : mvc.Tools.optRenderer('odr','Odr','PayType'),expandCol : true,hidden : true}
	,{width : 300}
	];
		this.store=Ext.create('mvc.store.odr.OdrOrder');
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