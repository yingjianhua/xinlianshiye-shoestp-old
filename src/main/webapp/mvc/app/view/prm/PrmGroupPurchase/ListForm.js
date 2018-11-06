Ext.define('mvc.view.prm.PrmGroupPurchase.ListForm',{
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
		this.tbar = mainActs;		this.columns =[{text : '产品',width : 100,dataIndex : 'bean.product',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'beantriggercell',bean : 'PdtProduct',beanType : 'pdt',beanName : 'bean.product',grid : this,emptyText : form_empty_text}
		}
	,{text : '数量',width : 100,dataIndex : 'bean.count',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	,{text : '已订购数量',width : 100,dataIndex : 'bean.boughtCount',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	,{text : '明细状态',width : 100,dataIndex : 'bean.state',sortable : true,renderer : mvc.Tools.optRenderer('prm','Prm','OSend'),editor : mvc.Tools.crtComboForm(true,{
						name : 'bean.state',
						store : Ext.create('mvc.combo.prm.PrmOSend')
					})
		}
	];
		this.store=Ext.create('mvc.store.prm.PrmGroupPurchaseLine');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.prm.PrmGroupPurchaseLine');
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