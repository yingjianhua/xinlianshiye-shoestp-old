Ext.define('mvc.view.odr.OdrOrder.ListForm',{
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
		this.tbar = mainActs;		this.columns =[{text : '产品',width : 200,dataIndex : 'bean.spec',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'beantriggercell',bean : 'PdtSpec',beanType : 'pdt',beanName : 'bean.spec',grid : this,emptyText : form_empty_text}
		}
	,{text : '数量',width : 100,dataIndex : 'bean.qty',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	,{text : '小计',width : 100,dataIndex : 'bean.subtotal',sortable : true,align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 2}
		}
	];
		this.store=Ext.create('mvc.store.odr.OdrOrderLine');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.odr.OdrOrderLine');
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