Ext.define('mvc.view.prm.PrmGroupOrder.ListForm',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
cellEditing : Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 }),
mainPkey : null,
initComponent : function(){
		var mainActs = [{
		text : '新增',
		iconCls : 'ins-icon',
		scope : this,
		handler : this.onIns
	},{
		text : '删除',
		iconCls : 'del-icon',
		scope : this,
		handler : this.onDel
	}];
		this.tbar = mainActs;		this.columns =[{text : '产品规格',width : 100,dataIndex : 'bean.product',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'beantriggercell',bean : 'PdtSpec',beanType : 'pdt',beanName : 'bean.product',grid : this,emptyText : form_empty_text}
		}
	,{text : '订购数量',width : 100,dataIndex : 'bean.productnum',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	,{text : '订单价格',width : 100,dataIndex : 'bean.productprice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 4}
		}
	];
		this.store=Ext.create('mvc.store.prm.PrmGroupOrderLine');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.prm.PrmGroupOrderLine');
        this.store.insert(0, model);
        this.cellEditing.startEditByPosition({row: 0, column: 0});
},
onDel : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection){
			this.getStore().remove(selection);
		}
}
});