Ext.define('mvc.view.pdt.PdtSpec.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
searchField : null,
initComponent : function(){
this.columns = [{text : '名称',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '产品',width : 100,dataIndex : 'bean.product',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '产品顏色',width : 100,dataIndex : 'bean.color',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '产品尺寸',width : 100,dataIndex : 'bean.size',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '组合属性',width : 100,dataIndex : 'bean.keyName',sortable : true}
	,{text : 'jiage',width : 100,dataIndex : 'bean.price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '加价',width : 100,dataIndex : 'bean.markup',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '库存数量',width : 100,dataIndex : 'bean.storeCount',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right'}
	,{text : '单价',width : 100,dataIndex : 'bean.weight',sortable : true,renderer : mvc.Tools.numberRenderer(4),align : 'right'}
	,{text : '产品颜色关联图片',width : 100,dataIndex : 'bean.pics',sortable : true,renderer : mvc.Tools.imgRenderer()}
	];
		this.store=Ext.create('mvc.store.pdt.PdtSpec');this.dockedItems = [{
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
				width : 100,
				value : 'name',
				store:	Ext.create('Ext.data.Store',{
							fields :  ['value', 'text'],
							data : [{value : 'name',text : '名称'}
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
			this.fireEvent('trigger', selection.get('bean.pkey') + bean_split + selection.get('bean.keyName'), null);
		}
}
});