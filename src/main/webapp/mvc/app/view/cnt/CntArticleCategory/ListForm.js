Ext.define('mvc.view.cnt.CntArticleCategory.ListForm',{
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
		this.tbar = mainActs;		this.columns =[{text : '名称',width : 100,dataIndex : 'bean.name',sortable : true,renderer : mvc.Tools.JsonRenderer(),editor : {}
		}
	,{text : '语言标识',width : 100,dataIndex : 'bean.lang',sortable : true,editor : {}
		}
	,{text : '启用标志',width : 75,dataIndex : 'bean.enabled',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OEnabled'),editor : mvc.Tools.crtComboForm(true,{
						name : 'bean.enabled',
						store : Ext.create('mvc.combo.sys.SysOEnabled')
					})
		}
	,{text : '建档员',width : 75,dataIndex : 'bean.createBy',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'beantriggercell',bean : 'SysUser',beanType : 'sys',beanName : 'bean.createBy',grid : this,emptyText : form_empty_text}
		}
	,{text : '建档时间',width : 140,dataIndex : 'bean.createTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),editor : {xtype : 'datefield',format : 'Y-m-d H:i:s'}
		}
	];
		this.store=Ext.create('mvc.store.cnt.CntArticleCategory');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.cnt.CntArticleCategory');
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