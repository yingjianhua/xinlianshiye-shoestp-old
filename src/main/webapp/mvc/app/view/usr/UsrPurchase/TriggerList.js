Ext.define('mvc.view.usr.UsrPurchase.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
searchField : null,
initComponent : function(){
this.columns = [{text : '头像',width : 100,dataIndex : 'bean.icon',sortable : true,renderer : mvc.Tools.imgRenderer(82)}
	,{text : '名字',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '姓氏',width : 100,dataIndex : 'bean.surname',sortable : true}
	,{text : '性别',width : 100,dataIndex : 'bean.sex',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OSex')}
	,{text : '邮箱',width : 100,dataIndex : 'bean.email',sortable : true}
	,{text : '登录账号',width : 100,dataIndex : 'bean.loginName',sortable : true}
	,{text : '手机号码',width : 100,dataIndex : 'bean.telphone',sortable : true}
	,{text : '公司名称',width : 100,dataIndex : 'bean.company',sortable : true}
	,{text : '国家',width : 100,dataIndex : 'bean.country',sortable : true,renderer : mvc.Tools.JsonRenderer()}
	];
		this.store=Ext.create('mvc.store.usr.UsrPurchase');this.dockedItems = [{
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
				width : 140,
				value : 'name',
				store:	Ext.create('Ext.data.Store',{
							fields :  ['value', 'text'],
							data : [{value : 'name',text : '名字'}
								,{value : 'loginName',text : '登录账号'}
								,{value : 'telphone',text : '手机号码'}
								,{value : 'company',text : '公司名称'}
								]
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