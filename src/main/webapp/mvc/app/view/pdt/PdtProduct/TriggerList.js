Ext.define('mvc.view.pdt.PdtProduct.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
searchField : null,
initComponent : function(){
this.columns = [{text : '产品图片',width : 100,dataIndex : 'bean.picture',sortable : true,renderer : mvc.Tools.imgRenderer(50,50)}
	,{text : '供应商',width : 100,dataIndex : 'bean.supplier',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '名称',width : 100,dataIndex : 'bean.name',sortable : true,renderer : mvc.Tools.JsonRenderer()}
	,{text : '产品类目',width : 100,dataIndex : 'bean.category',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '编号',width : 100,dataIndex : 'bean.code',sortable : true}
	,{text : 'SKU（出厂编码）',width : 100,dataIndex : 'bean.sku',sortable : true}
	];
		this.store=Ext.create('mvc.store.pdt.PdtProduct');this.dockedItems = [{
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
				width : 180,
				value : 'name',
				store:	Ext.create('Ext.data.Store',{
							fields :  ['value', 'text'],
							data : [{value : 'name',text : '名称'}
								,{value : 'sku',text : 'SKU（出厂编码）'}
								,{value : 'code',text : '编号'}
								,{value : 'supplier',text : '供应商'}
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