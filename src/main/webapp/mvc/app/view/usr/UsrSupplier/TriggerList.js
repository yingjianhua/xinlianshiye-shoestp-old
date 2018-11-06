Ext.define('mvc.view.usr.UsrSupplier.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
searchField : null,
initComponent : function(){
this.columns = [{text : '名称',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '供应商角色',width : 100,dataIndex : 'bean.role',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '登录账号',width : 100,dataIndex : 'bean.loginName',sortable : true}
	,{text : '供应商分类',width : 100,dataIndex : 'bean.category',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '企业法人',width : 100,dataIndex : 'bean.entity',sortable : true}
	,{text : '企业类型',width : 100,dataIndex : 'bean.companyType',sortable : true,renderer : mvc.Tools.JsonRenderer()}
	,{text : '企业性质',width : 100,dataIndex : 'bean.companyNature',sortable : true,renderer : mvc.Tools.JsonRenderer()}
	];
		this.store=Ext.create('mvc.store.usr.UsrSupplier');this.dockedItems = [{
		dock : 'top',
		xtype : 'toolbar',
		items : ["搜索：",{
				xtype : 'combo',
				mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				forceSelection : true,
				typeAhead : true,
				editable : false,
				width : 150,
				value : 'name',
				store:	Ext.create('Ext.data.Store',{
							fields :  ['value', 'text'],
							data : [{value : 'name',text : '名称'}
									,{value : 'loginName',text : '登录账号'}
									,{value : 'entity',text : '企业法人'}]
						}),
				listeners : {
					scope : this,
					change : function(field,newv,oldv,opts) {	this.searchField.flds = newv; }
				}
			},'=',{
				width : 250,
				xtype : 'irilleSearchfield',
				flds : 'name',
				store : this.store
			},'->',{
				xtype : 'button',
				text : '确定',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onTriggerList
			}]
	},{
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
		this.callParent(arguments);
		this.searchField = this.down('irilleSearchfield');
},
listeners : {
	itemdblclick : function(){
			this.onTriggerList();	
}
},
onTriggerList : function(){
			var selection = this.getView().getSelectionModel().getSelection()[0];
		if (selection){
			this.fireEvent('trigger', selection.get('bean.pkey') + bean_split + selection.get('bean.name'), null);
		}
}
});