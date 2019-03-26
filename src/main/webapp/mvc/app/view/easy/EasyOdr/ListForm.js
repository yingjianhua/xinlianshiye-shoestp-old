Ext.define('mvc.view.easy.EasyOdr.ListForm',{
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
		this.tbar = mainActs;		this.columns =[{text : '商品规格',width : 100,dataIndex : 'bean.spec',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'beantriggercell',bean : 'PdtSpec',beanType : 'pdt',beanName : 'bean.spec',grid : this,emptyText : form_empty_text}
		}
	,{text : '商品图片',width : 100,dataIndex : 'bean.iamge',sortable : true,renderer : mvc.Tools.imgRenderer(),editor : {}
		}
	,{text : '商品名称',width : 100,dataIndex : 'bean.productname',sortable : true,renderer : mvc.Tools.JsonRenderer(),editor : {}
		}
	,{text : '颜色',width : 100,dataIndex : 'bean.color',sortable : true,renderer : mvc.Tools.JsonRenderer(),editor : {}
		}
	,{text : '尺码',width : 100,dataIndex : 'bean.size',sortable : true,renderer : mvc.Tools.JsonRenderer(),editor : {}
		}
	,{text : '数量',width : 100,dataIndex : 'bean.num',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	,{text : '备注',width : 100,dataIndex : 'bean.remarks',sortable : true,editor : {}
		}
	];
		this.store=Ext.create('mvc.store.easy.EasyOdrline');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.easy.EasyOdrline');
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