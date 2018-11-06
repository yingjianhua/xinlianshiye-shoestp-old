Ext.define('mvc.view.pdt.PdtProduct.ListForm',{
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
		this.tbar = mainActs;		this.columns =[{text : '产品顏色',width : 100,dataIndex : 'bean.color',sortable : true,renderer : mvc.Tools.JsonRenderer(),editor : {xtype : 'combotriggercell',mode : 'local',valueField : 'value',triggerAction : 'all',typeAhead : true,editable : false,store : Ext.create('mvc.store.ComboTrigger',{actUrl:'pdt_PdtColor',actWhere:''}),emptyText : form_empty_text}
		}
	,{text : '产品尺寸',width : 100,dataIndex : 'bean.size',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'combotriggercell',mode : 'local',valueField : 'value',triggerAction : 'all',typeAhead : true,editable : false,store : Ext.create('mvc.store.ComboTrigger',{actUrl:'pdt_PdtSize',actWhere:''}),emptyText : form_empty_text}
		}
	,{text : '组合属性',width : 100,dataIndex : 'bean.keyName',sortable : true,renderer : mvc.Tools.JsonRenderer(),editor : {}
		}
	,{text : 'SKU（出厂编码）',width : 100,dataIndex : 'bean.sku',sortable : true,editor : {}
		}
	,{text : '价格',width : 100,dataIndex : 'bean.price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 2}
		}
	,{text : '加价',width : 100,dataIndex : 'bean.markup',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 2}
		}
	,{text : '库存数量',width : 100,dataIndex : 'bean.storeCount',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	,{text : '单价',width : 100,dataIndex : 'bean.weight',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 4}
		}
	,{text : '产品颜色关联图片',width : 100,dataIndex : 'bean.pics',sortable : true,renderer : mvc.Tools.imgRenderer(),editor : {}
		}
	];
		this.store=Ext.create('mvc.store.pdt.PdtSpec');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.pdt.PdtSpec');
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