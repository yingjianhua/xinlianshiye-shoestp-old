Ext.define('mvc.view.plt.PltFreight.ListForm',{
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
		this.tbar = mainActs;		this.columns =[{text : '区间',width : 100,dataIndex : 'bean.section',sortable : true,editor : {}
		}
	,{text : '简介',width : 100,dataIndex : 'bean.brief',sortable : true,editor : {}
		}
	,{text : '免运费',width : 100,dataIndex : 'bean.free',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),editor : mvc.Tools.crtComboForm(true,{
						name : 'bean.free',
						store : Ext.create('mvc.combo.sys.SysOYn')
					})
		}
	,{text : '免运费价格',width : 100,dataIndex : 'bean.freePrice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 4}
		}
	,{text : '附加费用',width : 100,dataIndex : 'bean.extraPrice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 4}
		}
	,{text : '首重区间',width : 100,dataIndex : 'bean.weightSection',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	,{text : '续重区间',width : 100,dataIndex : 'bean.aggravateSection',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	,{text : '首重价格',width : 100,dataIndex : 'bean.weightPrice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 4}
		}
	,{text : '加重价格',width : 100,dataIndex : 'bean.aggravatePrice',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 4}
		}
	];
		this.store=Ext.create('mvc.store.plt.PltFreightLine');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.plt.PltFreightLine');
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