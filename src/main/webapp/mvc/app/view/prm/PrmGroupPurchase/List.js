Ext.define('mvc.view.prm.PrmGroupPurchase.List',{
extend : 'Ext.panel.Panel',
oldId : 'PrmGroupPurchase_list_',
loadMask : true,
multiSelect : true,
roles : '',
layout : 'border',
lock : true,
mdSearch : null,
mdAct : null,
mdMain : null,
mdMainTable : null,
mdLineTable : null,
initComponent : function(){
var mainActs = [];		this.items =[{
		region : 'north',
		xtype : 'panel',
		border : false,
		items : [{
				xtype : 'toolbar',
				itemId : this.oldId+'search',
				items : [{
						xtype : 'label',
						text : '活动标题：'
					},{
						xtype : 'textfield',
						name : 'title'
					},'',{
						xtype : 'label',
						text : '活动状态：'
					},{
						xtype : 'combo',
						name : 'status',
						mode : 'local',
						valueField : 'value',
						triggerAction : 'all',
						forceSelection : true,
						typeAhead : true,
						editable : false,
						emptyText : form_empty_text,
						store : Ext.create('mvc.combo.prm.PrmOStatus')
					},'',{
						xtype : 'label',
						text : '供应商：'
					},{
						xtype : 'beantrigger',
						name : 'createdBy',
						bean : 'UsrSupplier',
						beanType : 'usr',
						emptyText : form_empty_text
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
				xtype : 'toolbar',
				itemId : this.oldId+'act',
				items : mainActs
			},{
				xtype : 'form',
				itemId :  this.oldId+'main',
				bodyPadding : '5 5 0 5',
				fieldDefaults : {
					anchor : '100%',
					labelWidth : 100,
					width : 275,
					labelAlign : 'right',
					readOnly : true
				},
				items : [{
						xtype : 'fieldset',
						title : '联合采购信息',
						collapsible : true,
						layout : {
							type : 'table',
							columns : 3
						},
						items : [{xtype : 'textfield',name : 'bean.title',fieldLabel : '活动标题'}
							]
					}]
			}]
	},{
		region : 'center',
		xtype : 'tabpanel',
		tabBar : {
			style : 'background:#fff'
		},
		items : [{
				xtype : Ext.create('mvc.view.prm.PrmGroupPurchase.ListMain',{
							title : '联合采购',
							itemId : this.oldId+'maintable',
							iconCls : 'tab-user-icon',
							roles : this.roles,
							listeners : 							 {
								scope : this,
				                selectionchange: function(model, records) {
				                    if (records.length === 1){
				                        this.mdMain.getForm().loadRecord(records[0]);
        								this.mdLineTable.store.filter([{'id':'filter', 'property':'main','value':records[0].get('bean.pkey')}]);
    									if (this.roles.indexOf('upd') != -1)
											this.down('#'+this.oldId+'upd').setDisabled(false);
										if (this.roles.indexOf('del') != -1)
											this.down('#'+this.oldId+'del').setDisabled(false);
				                    }else{
				                    	this.mdMain.getForm().reset();
				                    	this.mdLineTable.store.removeAll();
				                    	if (this.roles.indexOf('upd') != -1)
											this.down('#'+this.oldId+'upd').setDisabled(true);
										if (this.roles.indexOf('del') != -1)
											this.down('#'+this.oldId+'del').setDisabled(true);
				                    }
				                }
			                }

						})
			},{
				xtype : Ext.create('mvc.view.prm.PrmGroupPurchase.ListLinePrmGroupPurchaseLine',{
							title : '联合采购商品',
							itemId : this.oldId+'linetable',
							iconCls : 'tab-user-icon'
						})
			}]
	}];
		this.callParent(arguments);
		this.mdSearch = this.down('#'+this.oldId+'search');
		this.mdAct = this.down('#'+this.oldId+'act');
		this.mdMain = this.down('#'+this.oldId+'main');
		this.mdMainTable = this.down('#'+this.oldId+'maintable');
		this.mdLineTable = this.down('#'+this.oldId+'linetable');
		mvc.Tools.onENTER2SearchBar(this.mdSearch,this);
		if (mainActs.length == 0)
			this.down('[region=north]').remove(this.mdAct);
},
getStore : function(){
		return this.mdMainTable.store;
},
onSearchCancel : function(){
		this.mdMainTable.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.mdSearch);
		this.mdMainTable.store.clearFilter();
},
onSearch : function(){
		var array = mvc.Tools.searchValues(this.mdSearch);
		this.onSearchDo(array);
},
onSearchAdv : function(){
		var win = Ext.create('mvc.view.prm.PrmGroupPurchase.WinSearch',{
			title : this.title+'>高级搜索',
			listCmp : this
		});
		win.show();
},
onSearchDo : function(array){
		this.mdMainTable.getSelectionModel().deselectAll();
		if (array.length == 0){
			this.mdMainTable.store.clearFilter();
			return;
		}
		this.mdMainTable.store.clearFilter(true);
		this.mdMainTable.store.filter(array);
}
});