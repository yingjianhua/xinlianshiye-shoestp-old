Ext.define('mvc.view.usr.UsrProductCategory.List',{
extend : 'mvc.tools.RowexpanderGrid',
oldId : 'btn_UsrProductCategory',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
//viewConfig : {enableTextSelection : true},
initComponent : function(){
var mainActs = [];this.columns = [{text : '标题',width : 160,dataIndex : 'bean.name',sortable : true,renderer : mvc.Tools.JsonRenderer()}
	,{text : '隶属分类',width : 160,dataIndex : 'bean.categoryUp',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'pdt',mn : 'view.usr.UsrProductCategory.List'}
	,{text : '所属供应商',width : 280,dataIndex : 'bean.supplier',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'usr',mn : 'view.usr.UsrSupplier.List'}
	,{text : '启用标志',width : 75,dataIndex : 'bean.enabled',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OEnabled')}
	,{text : '标题',width : 100,dataIndex : 'bean.seoTitleEn',sortable : true,expandCol:true,hidden:true}
	,{text : '关键词',width : 100,dataIndex : 'bean.seoKeywordEn',sortable : true,expandCol:true,hidden:true}
	,{text : '简述',width : 100,dataIndex : 'bean.seoDescriptionEn',sortable : true,expandCol:true,hidden:true}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.usr.UsrProductCategory'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '标题：'
			},{
				xtype : 'textfield',
				name : 'name'
			},'',{
				xtype : 'label',
				text : '所属供应商：'
			},
			{
				xtype : 'beantrigger',
				name : 'createdBy',
				bean : 'UsrSupplier',
				beanType : 'usr',
				emptyText : form_empty_text
			},'',{
				xtype : 'label',
				text : '隶属分类：'
			},
				mvc.Tools.crtComboTrigger(true,'usr_UsrProductCategory','',{
							name : 'categoryUp'
						})
			,'',{
				xtype : 'label',
				text : '启用标志：'
			},{
				xtype : 'combo',
				name : 'enabled',
				mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				forceSelection : true,
				typeAhead : true,
				editable : false,
				emptyText : form_empty_text,
				store : Ext.create('mvc.combo.sys.SysOEnabled')
			},'',{
				xtype : 'label',
				text : '关键词：'
			},{
				xtype : 'textfield',
				name : 'seoKeywordEn'
			},'',{
				xtype : 'button',
				text : '撤销',
				scope : this,
				iconCls : 'win-close-icon',
				handler : this.onSearchCancel
			},{
				xtype : 'splitbutton',
				text : '搜索',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onSearch,
				menu : [{text:'高级搜索',iconCls : 'win-ok-icon', scope : this,handler: this.onSearchAdv}]
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
		this.callParent(arguments);		mvc.Tools.onENTER2SearchBar(this.down('[dock=top]'),this);},
listeners : {
	selectionchange : function(selModel, selected){
}
},
onSearchCancel : function(){
		this.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.down('toolbar'));
		this.store.clearFilter();
},
onSearch : function(){
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		this.onSearchDo(array);
},
onSearchAdv : function(){
		var win = Ext.create('mvc.view.usr.UsrProductCategory.WinSearch',{
			title : this.title+'>高级搜索',
			listCmp : this
		});
		win.show();
},
onSearchDo : function(array){
		this.getSelectionModel().deselectAll();
		if (array.length == 0){
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
}
});