Ext.define('mvc.view.usr.UsrPurchase.ListForm',{
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
		this.tbar = mainActs;		this.columns =[{text : '名字',width : 100,dataIndex : 'bean.name',sortable : true,editor : {}
		}
	,{text : '姓氏',width : 100,dataIndex : 'bean.surname',sortable : true,editor : {}
		}
	,{text : '邮件编码',width : 100,dataIndex : 'bean.emailcode',sortable : true,editor : {}
		}
	,{text : '国家管理',width : 100,dataIndex : 'bean.country',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'combotriggercell',mode : 'local',valueField : 'value',triggerAction : 'all',typeAhead : true,editable : false,store : Ext.create('mvc.store.ComboTrigger',{actUrl:'plt_PltCountry',actWhere:''}),emptyText : form_empty_text}
		}
	,{text : '省份',width : 100,dataIndex : 'bean.region',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'combotriggercell',mode : 'local',valueField : 'value',triggerAction : 'all',typeAhead : true,editable : false,store : Ext.create('mvc.store.ComboTrigger',{actUrl:'plt_PltProvince',actWhere:''}),emptyText : form_empty_text}
		}
	,{text : '城市',width : 100,dataIndex : 'bean.city',sortable : true,editor : {}
		}
	,{text : '具体地址',width : 100,dataIndex : 'bean.address',sortable : true,editor : {}
		}
	,{text : '联系电话',width : 100,dataIndex : 'bean.phonenumber',sortable : true,editor : {}
		}
	,{text : '默认地址',width : 100,dataIndex : 'bean.isdefault',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn'),editor : mvc.Tools.crtComboForm(true,{
						name : 'bean.isdefault',
						store : Ext.create('mvc.combo.sys.SysOYn')
					})
		}
	,{text : '收货地址类型',width : 100,dataIndex : 'bean.addrsstype',sortable : true,renderer : mvc.Tools.optRenderer('usr','Usr','OAddress'),editor : mvc.Tools.crtComboForm(true,{
						name : 'bean.addrsstype',
						store : Ext.create('mvc.combo.usr.UsrOAddress')
					})
		}
	];
		this.store=Ext.create('mvc.store.usr.UsrPurchaseLine');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.usr.UsrPurchaseLine');
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